package net.paradise.game.player;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.Socket;
import java.util.Calendar;
import java.util.GregorianCalendar;

import net.paradise.Server;
import net.paradise.game.Processable;
import net.paradise.game.npc.NPCHandler;
import net.paradise.net.StreamBuffer;
import net.paradise.util.MethodCollection;

public class PlayerHandler implements Processable
{

	private static PlayerHandler singleton;

	public static final int maxPlayers = 200;
	public static Player players[] = new Player[maxPlayers];
	public int playerSlotSearchStart = 1;
	public static String kickNick = "";
	public static boolean kickAllPlayers = false;
	public static String messageToAll = "";
	public static int playerCount = 0;
	public static String playersCurrentlyOn[] = new String[maxPlayers];
	public static boolean updateAnnounced;
	public static boolean updateRunning;
	public static int updateSeconds;
	public static long updateStartTime;
	public static int pcWaitTimer = 300;

	public PlayerHandler()
	{
		singleton = this;

		for (int i = 0; i < maxPlayers; i++)
		{
			players[i] = null;
		}
	}

	public static PlayerHandler getSingleton()
	{
		return singleton;
	}

	public void registerSocket(Socket socket, String connectedFrom)
	{
		int slot = -1, i = 1;

		do
		{
			if (players[i] == null)
			{
				slot = i;
				break;
			}
			i++;
			if (i >= maxPlayers)
			{
				i = 0;
			}
		} while (i <= maxPlayers);

		Client newClient = new Client(socket, slot);

		newClient.handler = this;
		(new Thread(newClient)).start();
		if (slot == -1)
		{
			return;
		}
		players[slot] = newClient;
		players[slot].connectedFrom = connectedFrom;
		updatePlayerNames();
		playerSlotSearchStart = slot + 1;
		if (playerSlotSearchStart > maxPlayers)
		{
			playerSlotSearchStart = 1;
		}
	}

	public int getFreeSlot()
	{
		int slot = -1, i = 1;
		do
		{
			if (players[i] == null)
			{
				slot = i;
				break;
			}
			i++;
			if (i >= maxPlayers)
				i = 0;
		} while (i <= maxPlayers);
		return slot;
	}

	public void addClient(int slot, Client newClient)
	{
		if (newClient == null)
			return;
		players[slot] = newClient;
		playerSlotSearchStart = slot + 1;
		if (playerSlotSearchStart > maxPlayers)
			playerSlotSearchStart = 1;
	}

	public void destruct()
	{
		for (int i = 0; i < maxPlayers; i++)
		{
			if (players[i] == null)
			{
				continue;
			}
			players[i].destruct();
			players[i] = null;
		}
	}

	public static int getPlayerCount()
	{
		return playerCount;
	}

	public void updatePlayerNames()
	{
		playerCount = 0;
		for (int i = 0; i < maxPlayers; i++)
		{
			if (players[i] != null)
			{
				playersCurrentlyOn[i] = players[i].username;
				playerCount++;
			}
			else
			{
				playersCurrentlyOn[i] = "";
			}
		}
	}

	public static boolean isPlayerOn(String playerName)
	{
		for (int i = 0; i < maxPlayers; i++)
		{
			if (playersCurrentlyOn[i] != null)
			{
				if (playersCurrentlyOn[i].equalsIgnoreCase(playerName))
				{
					return true;
				}
			}
		}
		return false;
	}

	public static Client getClient(String username)
	{
		for (int i = 0; i < players.length; i++)
		{
			if ((players[i] != null)
					&& (players[i].username.equalsIgnoreCase(username)))
			{
				return (Client) players[i];
			}
		}

		return null;
	}

	public static int getPlayerID(String playerName)
	{
		for (int i = 0; i < maxPlayers; i++)
		{
			if (playersCurrentlyOn[i] != null)
			{
				if (playersCurrentlyOn[i].equalsIgnoreCase(playerName))
				{
					return i;
				}
			}
		}
		return -1;
	}

	public void process()
	{
		try
		{
			if (messageToAll.length() > 0)
			{
				int msgTo = 1;

				do
				{
					if (players[msgTo] != null)
					{
						players[msgTo].globalMessage = messageToAll;
					}
					msgTo++;
				} while (msgTo < maxPlayers);
				messageToAll = "";
			}
			if (pcWaitTimer > 0)
			{
				pcWaitTimer -= 1;
			}
			if (pcWaitTimer == 0)
			{
				pcWaitTimer = 300;
			}
			if (kickAllPlayers == true)
			{
				int kickID = 1;

				do
				{
					if (players[kickID] != null)
					{
						players[kickID].isKicked = true;
					}
					kickID++;
				} while (kickID < maxPlayers);
				kickAllPlayers = false;
			}

			// at first, parse all the incoming data
			// this has to be seperated from the outgoing part because we have
			// to keep all the player data
			// static, so each client gets exactly the same and not the one
			// clients are ahead in time
			// than the other ones.
			for (int i = 0; i < maxPlayers; i++)
			{
				if (players[i] == null || !players[i].isActive)
				{
					continue;
				}

				players[i].actionAmount--;

				players[i].preProcessing();
				players[i].process();
				while (players[i].packetSending())
				{
					;
				}
				players[i].postProcessing();

				players[i].getNextPlayerMovement();

				if (players[i].username.equalsIgnoreCase(kickNick))
				{
					players[i].kick();
					kickNick = "";
				}

				if (players[i].disconnected)
				{
					for (int i2 = 0; i2 < NPCHandler.maxNPCs; i2++)
					{
						if (NPCHandler.npcs[i2] != null && players[i] != null)
						{
							if (NPCHandler.npcs[i2].followPlayer == players[i].playerId
									&& NPCHandler.npcs[i2] != null)
							{
								NPCHandler.npcs[i2].IsDead = true;
							}
						}
					}

					removePlayer(players[i]);
					players[i] = null;
				}
			}

			for (int i = 0; i < maxPlayers; i++)
			{
				if (players[i] == null || !players[i].isActive)
				{
					continue;
				}

				Calendar cal = new GregorianCalendar();
				int day = cal.get(Calendar.DAY_OF_MONTH);
				int month = cal.get(Calendar.MONTH);
				int year = cal.get(Calendar.YEAR);
				int calc = ((year * 10000) + (month * 100) + day);

				players[i].playerLastLogin = calc;
				if (players[i].disconnected)
				{
					for (int i3 = 0; i3 < NPCHandler.maxNPCs; i3++)
					{
						if (NPCHandler.npcs[i3] != null && players[i] != null)
						{
							if (NPCHandler.npcs[i3].followPlayer == players[i].playerId)
							{
								NPCHandler.npcs[i3].IsDead = true;
							}
						}
					}

					removePlayer(players[i]);
					players[i] = null;
				}
				else
				{
					if (!players[i].initialized)
					{
						players[i].initialize();
						players[i].initialized = true;
					}
					else
					{
						players[i].update();
					}
				}
			}

			if (updateRunning && !updateAnnounced)
			{
				updateAnnounced = true;
			}

			if (updateRunning
					&& System.currentTimeMillis() - updateStartTime > (updateSeconds * 1000))
			{
				kickAllPlayers = true;
				Server.ShutDown = true;
			}

			// post processing
			for (int i = 0; i < maxPlayers; i++)
			{
				if (players[i] == null || !players[i].isActive)
				{
					continue;
				}

				players[i].clearUpdateFlags();
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void updateNPC(Player plr, StreamBuffer str)
	{
		updateBlock.offset = 0;

		str.createFrameVarSizeWord(65);
		str.initBitAccess();

		str.writeBits(8, plr.npcListSize);
		int size = plr.npcListSize;

		plr.npcListSize = 0;
		for (int i = 0; i < size; i++)
		{
			if (plr.RebuildNPCList == false
					&& plr.withinDistance(plr.npcList[i]) == true)
			{
				plr.npcList[i].updateNPCMovement(str);
				plr.npcList[i].appendNPCUpdateBlock(updateBlock);
				plr.npcList[plr.npcListSize++] = plr.npcList[i];
			}
			else
			{
				int id = plr.npcList[i].index;

				plr.npcInListBitmap[id >> 3] &= ~(1 << (id & 7)); // clear the
																	// flag
				str.writeBits(1, 1);
				str.writeBits(2, 3); // tells client to remove this npc from
										// list
			}
		}

		// iterate through all npcs to check whether there's new npcs to add
		for (int i = 0; i < NPCHandler.maxNPCs; i++)
		{
			if (NPCHandler.npcs[i] != null)
			{
				int id = NPCHandler.npcs[i].index;

				if (plr.RebuildNPCList == false
						&& (plr.npcInListBitmap[id >> 3] & (1 << (id & 7))) != 0)
				{
				}
				else if (plr.withinDistance(NPCHandler.npcs[i]) == false)
				{
				}
				else
				{
					plr.addNewNPC(NPCHandler.npcs[i], str, updateBlock);
				}
			}
		}

		plr.RebuildNPCList = false;

		if (updateBlock.offset > 0)
		{
			str.writeBits(14, 16383); // magic EOF - needed only when npc
										// updateblock follows
			str.finishBitAccess();

			// append update block
			str.put(updateBlock.buffer, updateBlock.offset, 0);
		}
		else
		{
			str.finishBitAccess();
		}
		str.endFrameVarSizeWord();
	}

	private StreamBuffer updateBlock = new StreamBuffer(new byte[10000]);

	// should actually be moved to client.java because it's very client specific
	public void updatePlayer(Player plr, StreamBuffer str)
	{
		updateBlock.offset = 0;

		if (updateRunning && !updateAnnounced)
		{
			str.createFrame(114);
			str.putShortBE(updateSeconds * 50 / 30);
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
		for (int i = 0; i < size; i++)
		{
			// this update packet does not support teleporting of other players
			// directly
			// instead we're going to remove this player here and readd it right
			// away below
			if (!plr.didTeleport && !plr.playerList[i].didTeleport
					&& plr.withinDistance(plr.playerList[i]))
			{
				plr.playerList[i].updatePlayerMovement(str);
				plr.playerList[i].appendPlayerUpdateBlock(updateBlock);
				plr.playerList[plr.playerListSize++] = plr.playerList[i];
			}
			else
			{
				int id = plr.playerList[i].playerId;

				plr.playerInListBitmap[id >> 3] &= ~(1 << (id & 7)); // clear
																		// the
																		// flag
				str.writeBits(1, 1);
				str.writeBits(2, 3); // tells client to remove this char from
										// list
			}
		}

		// iterate through all players to check whether there's new players to
		// add
		for (int i = 0; i < maxPlayers; i++)
		{
			if (players[i] == null || players[i].isActive == false
					|| players[i] == plr)
			{// not existing, not active or you are that player
			}
			else
			{
				int id = players[i].playerId;

				if (plr.didTeleport == false
						&& (plr.playerInListBitmap[id >> 3] & (1 << (id & 7))) != 0)
				{// player already in playerList
				}
				else if (plr.withinDistance(players[i]) == false)
				{// out of sight
				}
				else
				{
					plr.addNewPlayer(players[i], str, updateBlock);
				}
			}
		}

		if (updateBlock.offset > 0)
		{
			str.writeBits(11, 2047); // magic EOF - needed only when player
										// updateblock follows
			str.finishBitAccess();

			// append update block
			str.put(updateBlock.buffer, updateBlock.offset, 0);
		}
		else
		{
			str.finishBitAccess();
		}
		str.endFrameVarSizeWord();
	}

	public int lastchatid = 1; // PM System

	private void removePlayer(Player plr)
	{
		if (plr.Privatechat != 2)
		{ // PM System
			for (int i = 1; i < maxPlayers; i++)
			{
				if (players[i] == null || players[i].isActive == false)
				{
					continue;
				}
				players[i].pmupdate(plr.playerId, 0);
			}
		}
		// anything can be done here like unlinking this player structure from
		// any of the other existing structures
		plr.destruct();
	}

	public boolean savechar(Player plr)
	{
		BufferedWriter characterfile = null;

		try
		{
			characterfile = new BufferedWriter(new FileWriter("./characters/"
					+ plr.username + ".txt"));

			/* ACCOUNT */
			characterfile.write("[ACCOUNT]", 0, 9);
			characterfile.newLine();
			characterfile.write("character-username = ", 0, 21);
			characterfile.write(plr.username, 0, plr.username.length());
			characterfile.newLine();
			characterfile.write("character-password = ", 0, 21);
			characterfile.write(plr.password, 0, plr.password.length());
			characterfile.newLine();
			characterfile.newLine();

			/* CHARACTER */
			characterfile.write("[CHARACTER]", 0, 11);
			characterfile.newLine();
			characterfile.write("character-bankpin =  ", 0, 20);
			characterfile.write(Integer.toString(plr.bankPin), 0, Integer
					.toString(plr.bankPin).length());
			characterfile.newLine();
			characterfile.write("character-height = ", 0, 19);
			characterfile.write(Integer.toString(plr.heightLevel), 0, Integer
					.toString(plr.heightLevel).length());
			characterfile.newLine();
			characterfile.write("character-posx = ", 0, 17);
			characterfile.write(Integer.toString(plr.absX), 0, Integer
					.toString(plr.absX).length());
			characterfile.newLine();
			characterfile.write("character-posy = ", 0, 17);
			characterfile.write(Integer.toString(plr.absY), 0, Integer
					.toString(plr.absY).length());
			characterfile.newLine();
			characterfile.write("character-rights = ", 0, 19);
			characterfile.write(Integer.toString(plr.playerRights), 0, Integer
					.toString(plr.playerRights).length());
			characterfile.newLine();
			characterfile.write("character-ismember = ", 0, 21);
			characterfile.write(Integer.toString(plr.playerIsMember), 0,
					Integer.toString(plr.playerIsMember).length());
			characterfile.newLine();
			characterfile.write("character-messages = ", 0, 21);
			characterfile.write(Integer.toString(plr.playerMessages), 0,
					Integer.toString(plr.playerMessages).length());
			characterfile.newLine();
			characterfile.write("character-lastconnection = ", 0, 27);
			characterfile.write(plr.playerLastConnect, 0,
					plr.playerLastConnect.length());
			characterfile.newLine();
			characterfile.write("character-lastlogin = ", 0, 22);
			characterfile.write(Integer.toString(plr.playerLastLogin), 0,
					Integer.toString(plr.playerLastLogin).length());
			characterfile.newLine();
			characterfile.write("character-energy = ", 0, 19);
			characterfile.write(Integer.toString(plr.playerEnergy), 0, Integer
					.toString(plr.playerEnergy).length());
			characterfile.newLine();
			characterfile.write("character-gametime = ", 0, 21);
			characterfile.write(Integer.toString(plr.playerGameTime), 0,
					Integer.toString(plr.playerGameTime).length());
			characterfile.newLine();
			characterfile.write("character-gamecount = ", 0, 22);
			characterfile.write(Integer.toString(plr.playerGameCount), 0,
					Integer.toString(plr.playerGameCount).length());
			characterfile.newLine();
			characterfile.newLine();

			/* EQUIPMENT */
			characterfile.write("[EQUIPMENT]", 0, 11);
			characterfile.newLine();
			for (int i = 0; i < plr.playerEquipment.length; i++)
			{
				characterfile.write("character-equip = ", 0, 18);
				characterfile.write(Integer.toString(i), 0, Integer.toString(i)
						.length());
				characterfile.write("	", 0, 1);
				characterfile.write(Integer.toString(plr.playerEquipment[i]),
						0, Integer.toString(plr.playerEquipment[i]).length());
				characterfile.write("	", 0, 1);
				characterfile.write(Integer.toString(plr.playerEquipmentN[i]),
						0, Integer.toString(plr.playerEquipmentN[i]).length());
				characterfile.write("	", 0, 1);
				characterfile.newLine();
			}
			characterfile.newLine();

			/* LOOK */
			characterfile.write("[LOOK]", 0, 6);
			characterfile.newLine();
			for (int i = 0; i < plr.playerLook.length; i++)
			{
				characterfile.write("character-look = ", 0, 17);
				characterfile.write(Integer.toString(i), 0, Integer.toString(i)
						.length());
				characterfile.write("	", 0, 1);
				characterfile.write(Integer.toString(plr.playerLook[i]), 0,
						Integer.toString(plr.playerLook[i]).length());
				characterfile.newLine();
			}
			characterfile.newLine();

			/* SKILLS */
			characterfile.write("[SKILLS]", 0, 8);
			characterfile.newLine();
			for (int i = 0; i < plr.playerLevel.length; i++)
			{
				characterfile.write("character-skill = ", 0, 18);
				characterfile.write(Integer.toString(i), 0, Integer.toString(i)
						.length());
				characterfile.write("	", 0, 1);
				characterfile.write(Integer.toString(plr.playerLevel[i]), 0,
						Integer.toString(plr.playerLevel[i]).length());
				characterfile.write("	", 0, 1);
				characterfile.write(Integer.toString(plr.playerXP[i]), 0,
						Integer.toString(plr.playerXP[i]).length());
				characterfile.newLine();
			}
			characterfile.newLine();

			/* ITEMS */
			characterfile.write("[ITEMS]", 0, 7);
			characterfile.newLine();
			for (int i = 0; i < plr.playerItems.length; i++)
			{
				if (plr.playerItems[i] > 0)
				{
					characterfile.write("character-item = ", 0, 17);
					characterfile.write(Integer.toString(i), 0, Integer
							.toString(i).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Integer.toString(plr.playerItems[i]),
							0, Integer.toString(plr.playerItems[i]).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Integer.toString(plr.playerItemsN[i]),
							0, Integer.toString(plr.playerItemsN[i]).length());
					characterfile.newLine();
				}
			}
			characterfile.newLine();

			/* BANK */
			characterfile.write("[BANK]", 0, 6);
			characterfile.newLine();
			for (int i = 0; i < plr.bankItems.length; i++)
			{
				if (plr.bankItems[i] > 0)
				{
					characterfile.write("character-bank = ", 0, 17);
					characterfile.write(Integer.toString(i), 0, Integer
							.toString(i).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Integer.toString(plr.bankItems[i]), 0,
							Integer.toString(plr.bankItems[i]).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Integer.toString(plr.bankItemsN[i]), 0,
							Integer.toString(plr.bankItemsN[i]).length());
					characterfile.newLine();
				}
			}
			characterfile.newLine();

			/* FRIENDS */
			characterfile.write("[FRIENDS]", 0, 9);
			characterfile.newLine();
			for (int i = 0; i < plr.friends.length; i++)
			{
				if (plr.friends[i] > 0)
				{
					characterfile.write("character-friend = ", 0, 19);
					characterfile.write(Integer.toString(i), 0, Integer
							.toString(i).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Long.toString(plr.friends[i]), 0, Long
							.toString(plr.friends[i]).length());
					characterfile.newLine();
				}
			}
			characterfile.newLine();

			/* IGNORES */
			characterfile.write("[IGNORES]", 0, 9);
			characterfile.newLine();
			for (int i = 0; i < plr.ignores.length; i++)
			{
				if (plr.ignores[i] > 0)
				{
					characterfile.write("character-ignore = ", 0, 19);
					characterfile.write(Integer.toString(i), 0, Integer
							.toString(i).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Long.toString(plr.ignores[i]), 0, Long
							.toString(plr.ignores[i]).length());
					characterfile.newLine();
				}
			}
			characterfile.newLine();

			/* EOF */
			characterfile.write("[EOF]", 0, 5);
			characterfile.newLine();
			characterfile.newLine();
			characterfile.close();
		}
		catch (IOException ioexception)
		{
			MethodCollection.println(plr.username + ": error writing file.");
			return false;
		}
		return true;
	}

}