package server.players;

import server.*;
import server.npcs.*;
import server.util.*;
import server.items.*;
import server.world.*;
import server.players.Combat.*;
import server.players.*;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class PlayerHandler {
    public static client clients[] = new client[1500];
	// Remark: the player structures are just a temporary solution for now
	// Later we will avoid looping through all the players for each player
	// by making use of a hash table maybe based on map regions (8x8 granularity
	// should be ok)
	public static int cycle = 0;
	public static boolean kickAllPlayers = false;
	public static String kickNick = "";
	public static long lastChest = 0, lastChest2 = 0;
	public static final int maxPlayers = 512;
	public static String messageToAll = "";
	public static int playerCount = 0;
	public static Player players[] = new Player[maxPlayers];
	public static String playersCurrentlyOn[] = new String[maxPlayers];
	public static boolean updateAnnounced;
	private static stream updateBlock = new stream(new byte[10000]);
	public static boolean updateRunning;
	public static int updateSeconds;
	public static long updateStartTime;

	public static int getPlayerCount() {
		int count = 0;
		for (int i = 0; i < players.length; i++) {
			if ((players[i] != null) && !players[i].disconnected) {
				count++;
			}
		}
		return count;
	}

	public static int getPlayerID(String playerName) {
		for (int i = 0; i < maxPlayers; i++) {
			if (playersCurrentlyOn[i] != null) {
				if (playersCurrentlyOn[i].equalsIgnoreCase(playerName))
					return i;
			}
		}
		return -1;
	}

	public static boolean isPlayerOn(String playerName) { // Gnarly: bug fixed by gnarly, in your server you might have to change the 				Constants.MAX_PLAYERS to whatever your max players constant is.
		for(int i = 0; i < maxPlayers; i++) {
			if(playersCurrentlyOn[i] != null) {
				if(playersCurrentlyOn[i].equalsIgnoreCase(playerName) && !(playerName.length() == 0))
					return true;
			}
		}
		return false;
	}

	// should actually be moved to client.java because it's very client specific
	public static void updatePlayer(Player plr, stream str) {
		updateBlock.currentOffset = 0;

		if (updateRunning && !updateAnnounced) {
			str.createFrame(114);
			str.writeWordBigEndian(updateSeconds * 50 / 30);
		}

		// update thisPlayer
		plr.updateThisPlayerMovement(str); // handles walking/running and
		// teleporting
		// do NOT send chat text back to thisPlayer!
		boolean saveChatTextUpdate = plr.chatTextUpdateRequired;
		plr.chatTextUpdateRequired = false;
		plr.appendPlayerUpdateBlock(updateBlock);
		plr.chatTextUpdateRequired = saveChatTextUpdate;

		str.writeBits(8, plr.playerListSize);
		int size = plr.playerListSize;

		// update/remove players that are already in the playerList
		plr.playerListSize = 0; // we're going to rebuild the list right away
		for (int i = 0; i < size; i++) {
			// this update packet does not support teleporting of other players
			// directly
			// instead we're going to remove this player here and readd it right
			// away below
			if ((plr.playerList[i].didTeleport == false)
					&& (plr.didTeleport == false)
					&& (plr.withinDistance(plr.playerList[i]) == true)) {
				plr.playerList[i].updatePlayerMovement(str);
				plr.playerList[i].appendPlayerUpdateBlock(updateBlock);
				plr.playerList[plr.playerListSize++] = plr.playerList[i];
			} else {
				int id = plr.playerList[i].playerId;
				plr.playerInListBitmap[id >> 3] &= ~(1 << (id & 7)); // clear
				// the
				// flag
				str.writeBits(1, 1);
				str.writeBits(2, 3); // tells client to remove this char
				// from list
			}
		}

		// iterate through all players to check whether there's new players to
		// add
		for (int i = 0; i < maxPlayers; i++) {
			if ((players[i] == null) || (players[i].isActive == false)
					|| (players[i] == plr)) {
				// not existing, not active or you are that player
			} else {
				int id = players[i].playerId;
				if ((plr.didTeleport == false)
						&& ((plr.playerInListBitmap[id >> 3] & (1 << (id & 7))) != 0)) {
					// player already in playerList
				} else if (plr.withinDistance(players[i]) == false) {
					// out of sight
				} else {
					plr.addNewPlayer(players[i], str, updateBlock);
				}
			}
		}

		if (updateBlock.currentOffset > 0) {
			str.writeBits(11, 2047); // magic EOF - needed only when player
			// updateblock follows
			str.finishBitAccess();

			// append update block
			str.writeBytes(updateBlock.buffer, updateBlock.currentOffset, 0);
		} else {
			str.finishBitAccess();
		}
		str.endFrameVarSizeWord();
	}

	public int lastchatid = 1; // PM System

	public int playerSlotSearchStart = 1; // where we start searching

	// at when adding a new
	// player

	public PlayerHandler() {
		for (int i = 0; i < maxPlayers; i++) {
			players[i] = null;
		}
	}

	public void destruct() {
		for (int i = 0; i < maxPlayers; i++) {
			if (players[i] == null)
				continue;
			players[i].destruct();
			players[i] = null;
		}
	}

	// a new client connected
	public void newPlayerClient(java.net.Socket s, String connectedFrom) {
		// first, search for a free slot
		// int slot = -1, i = playerSlotSearchStart;
		int slot = -1;
		for (int i = 1; i < players.length; i++) {
			if ((players[i] == null) || players[i].disconnected) {
				slot = i;
				break;
			}
		}

		client newClient = new client(s, slot);
		newClient.handler = this;
		(new Thread(newClient)).start();
		if (slot == -1)
			return; // no free slot found - world is full
		players[slot] = newClient;
		players[slot].connectedFrom = connectedFrom;
		players[slot].ip = s.getInetAddress().hashCode();
		Player.localId = slot;
		players[slot].lastPacket = System.currentTimeMillis();
		// start at next slot when issuing the next search to speed it up
		playerSlotSearchStart = slot + 1;
		if (playerSlotSearchStart > maxPlayers)
			playerSlotSearchStart = 1;
		// Note that we don't use slot 0 because playerId zero does not function
		// properly with the client.
	}

	public void println_debug(String str, int ID, String Name) {
		System.out.println("[" + Name + "]: " + str);
	}
	public void process() {
		int current = -1;
		long currentTime = System.currentTimeMillis();
		try {
			updatePlayerNames();

			if (kickAllPlayers == true) {
				int kickID = 1;
				do {
					if (players[kickID] != null) {
						players[kickID].isKicked = true;
					}
					kickID++;
				} while (kickID < maxPlayers);
				kickAllPlayers = false;
			}
			if (cycle > 10000) {
				cycle = 0;
			}
			cycle++;
			
			for (int i = 0; i < maxPlayers; i++) {
				try {
					if (players[i] == null)
						continue;
					if (!players[i].disconnected && !players[i].isActive) {
						if (players[i].violations > 20) {
							removePlayer(players[i]);
							players[i] = null;
							continue;
						} else {
							players[i].violations++;
							continue;
						}
					}
					if (players[i].disconnected)
						continue;
					if (System.currentTimeMillis() - players[i].lastPacket >= 15000) {
						players[i].disconnected = true;
					}
					players[i].preProcessing();
					while (players[i].process());
					players[i].postProcessing();
					players[i].timers();	
					players[i].playerProcess();
					players[i].getNextPlayerMovement();
					if (players[i].playerName.equalsIgnoreCase(kickNick)) {
						players[i].kick();
						kickNick = "";
					}
					if (players[i].disconnected) {
						if(players[i].inTrade) {
							client o = (client) server.playerHandler.players[players[i].tradeWith];
							if(o != null) {
								o.getTrade().declineTrade();
							}
						}
						client p = (client) players[i];
						removePlayer(players[i]);
						players[i] = null;
					}
				} catch (Exception e) {
					if (players[i].playerName != null)
						
					e.printStackTrace();
				}
			}

			
			for(int i = 0; i < maxPlayers; i++) {
				try {
					if(players[i] == null || !players[i].isActive) continue;

					if(players[i].disconnected) {
						if(saveGame(players[i])){ System.out.println("Game saved for player "+players[i].playerName); } else { System.out.println("Could not save for "+players[i].playerName); };
						removePlayer(players[i]);
						players[i] = null;
					}
					else {
						if(!players[i].initialized) {
							players[i].initialize();
							players[i].initialized = true;
						}
						else players[i].update();
					}
				} catch (Exception ex) {
				}
			}
			// post processing
			for (int i = 0; i < maxPlayers; i++) {
				try {
					if ((players[i] == null) || !players[i].isActive)
						continue;

					players[i].clearUpdateFlags();
				} catch (Exception ex) {
				}
			}
		} catch (Exception e) {
			misc.println(e.getMessage());
		}
	}
	public void removePlayer(Player plr) {
		if (plr == null)
			return;
		if (plr.Privatechat != 2) {
            		for (int i = 1; i < maxPlayers; i++) {
                		if ((players[i] == null) || (players[i].isActive == false) || (players[i].playerName == null))
                    			continue;
                	players[i].pmupdate(plr.playerId, 0);
            		}
        	}
       		plr.destruct();
    	}
	public void removePlayer2(Player plr) {
		if (plr == null)
			return;
		if (plr.Privatechat != 2) { // PM System
			for (int i = 1; i < maxPlayers; i++) {
				if ((players[i] == null) || (players[i].isActive == false)
						|| (players[i].playerName == null))
					continue;
				players[i].pmupdate(plr.playerId, 0);
			}
		}
		// anything can be done here like unlinking this player structure from
		// any of the other existing structures
		saveGame(plr);
		plr.destruct();
	}

	public boolean saveGame(Player plr) {
		client saving = (client) plr;
		if (saving == null)
			return false;
		saving.logout();
		return true;
	}

	public void updateNPC(Player plr, stream str) {
		updateBlock.currentOffset = 0;

		str.createFrameVarSizeWord(65);
		str.initBitAccess();

		str.writeBits(8, plr.npcListSize);
		int size = plr.npcListSize;
		plr.npcListSize = 0;
		for (int i = 0; i < size; i++) {
			if ((plr.RebuildNPCList == false)
					&& (plr.withinDistance(plr.npcList[i]) == true)) {
				plr.npcList[i].updateNPCMovement(str);
				plr.npcList[i].appendNPCUpdateBlock(updateBlock);
				plr.npcList[plr.npcListSize++] = plr.npcList[i];
			} else {
				int id = plr.npcList[i].npcId;
				plr.npcInListBitmap[id >> 3] &= ~(1 << (id & 7)); // clear the
				// flag
				str.writeBits(1, 1);
				str.writeBits(2, 3); // tells client to remove this npc
				// from list
			}
		}

		// iterate through all npcs to check whether there's new npcs to add
		for (int i = 0; i < NPCHandler.maxNPCSpawns; i++) {
			if (server.npcHandler.npcs[i] != null) {
				int id = server.npcHandler.npcs[i].npcId;
				if ((plr.RebuildNPCList == false)
						&& ((plr.npcInListBitmap[id >> 3] & (1 << (id & 7))) != 0)) {
					// npc already in npcList
				} else if (plr.withinDistance(server.npcHandler.npcs[i]) == false) {
					// out of sight
				} else {
					plr.addNewNPC(server.npcHandler.npcs[i], str, updateBlock);
				}
			}
		}

		plr.RebuildNPCList = false;

		if (updateBlock.currentOffset > 0) {
			str.writeBits(14, 16383); // magic EOF - needed only when npc
			// updateblock follows
			str.finishBitAccess();

			// append update block
			str.writeBytes(updateBlock.buffer, updateBlock.currentOffset, 0);
		} else {
			str.finishBitAccess();
		}
		str.endFrameVarSizeWord();
	}

	public void updatePlayerNames() {
		playerCount = 0;
		for (int i = 0; i < maxPlayers; i++) {
			if (players[i] != null) {
				playersCurrentlyOn[i] = players[i].playerName;
				playerCount++;
			} else
				playersCurrentlyOn[i] = "";
		}
	}

	public void yell(String message) {
		for (Player p : players) {
			if ((p == null) || !p.isActive)
				continue;
			client temp = (client) p;
			if ((temp.absX > 0) && (temp.absY > 0))
				if ((temp != null) && !temp.disconnected && p.isActive)
					temp.CAM().sendMessage(message);
		}
	}
}
