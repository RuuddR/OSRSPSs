package server.npcs;

import server.*;
import server.players.Skills.*;
import server.players.Quests.*;
import server.players.*;
import server.npcs.*;
import server.items.*;
import server.util.*;
import server.world.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class NPCHandler {

	public static boolean IsDropping = false;
	public static int maxNPCs = 10000;
	public static int maxListedNPCs = 10000;
	public static int maxNPCSpawns = 10000;
	public int[] combatLevel = new int[3851];
	public NPCList NpcList[] = new NPCList[maxListedNPCs];
	public NPC npcs[] = new NPC[maxNPCSpawns];
	public int remove = 2;
	public int[] respawnTime = new int[3851];

	public NPCHandler() {
		for (int i = 0; i < maxNPCSpawns; i++) {
			npcs[i] = null;
		}
		for (int i = 0; i < maxListedNPCs; i++) {
			NpcList[i] = null;
		}
		loadNPCList("npc.cfg");
		loadAutoSpawn("autospawn.cfg");
		loadNPCsDefinitions("npcConfig.cfg");
	}
	
	//created by killamess (handles all npcs combat via the configuration file, ftw?)
	public void loadNPCDefinitions(int npc, int combatType, int maxHit, int def, int att, int attDelay, int damageDelay, int agg, int aggDistance, int handAnimations, int handGraphics, int projectile, int endGraphics, int size) {  
		try {
			for (int i = 0 ; i < maxNPCSpawns; i++) {
				if (npcs[i] != null && npcs[i].npcType == npc) {
					NPC n = npcs[i];
					n.npcType = npc;
					n.combatType = combatType;
					n.maxHit = misc.random(maxHit);
					n.defenceLevel = def;
					n.attackLevel = att;
					n.attackDelay = attDelay;
					n.damageDelay2 = damageDelay;
					if (agg == 1) {	
						n.aggressive = true;
					} else {
						n.aggressive = false;		
					}
					n.aggressiveDistance = aggDistance;
					n.handAnimation = handAnimations;
					n.handGFX = handGraphics;
					n.npcProjectile = projectile;
					n.endGFX = endGraphics;
					n.npcSize = size;
				}
			}
			
		} catch(Exception loadNPC) {
			println(loadNPC+" error in npcDefs.config.");
		}
	}
	public boolean loadNPCsDefinitions(String FileName) {
		String line = "";
		String token = "";
		String token2 = "";
		String token2_2 = "";
		String[] token3 = new String[14];
		boolean EndOfFile = false;
		BufferedReader characterfile = null;
		try {
			characterfile = new BufferedReader(new FileReader("./server/npcs/combat/config/"+ FileName));
		} catch (FileNotFoundException fileex) {
			misc.println(FileName + ": file not found.");
			return false;
		}
		try {
			line = characterfile.readLine();
		} catch (IOException ioexception) {
			misc.println(FileName + ": error loading file.");
			return false;
		}
		while ((EndOfFile == false) && (line != null)) {
			line = line.trim();
			int spot = line.indexOf("=");
			if (spot > -1) {
				token = line.substring(0, spot);
				token = token.trim();
				token2 = line.substring(spot + 1);
				token2 = token2.trim();
				token2_2 = token2.replaceAll("\t\t", "\t");
				token2_2 = token2_2.replaceAll("\t\t", "\t");
				token2_2 = token2_2.replaceAll("\t\t", "\t");
				token2_2 = token2_2.replaceAll("\t\t", "\t");
				token2_2 = token2_2.replaceAll("\t\t", "\t");
				token3 = token2_2.split("\t");
				if (token.equals("id")) {
					loadNPCDefinitions(
						Integer.parseInt(token3[0]), 
						Integer.parseInt(token3[1]), 
						Integer.parseInt(token3[2]),
						Integer.parseInt(token3[3]), 
						Integer.parseInt(token3[4]), 
						Integer.parseInt(token3[5]),
						Integer.parseInt(token3[6]), 
						Integer.parseInt(token3[7]), 
						Integer.parseInt(token3[8]),
 						Integer.parseInt(token3[9]), 
						Integer.parseInt(token3[10]), 
						Integer.parseInt(token3[11]),
 						Integer.parseInt(token3[12]), 
						Integer.parseInt(token3[13])
					);
				}
			} else {
				if (line.equals("[ENDOFSPAWNLIST]")) {
					try {
						characterfile.close();
					} catch (IOException ioexception) {
					}
					return true;
				}
			}
			try {
				line = characterfile.readLine();
			} catch (IOException ioexception1) {
				EndOfFile = true;
			}
		}
		try {
			characterfile.close();
		} catch (IOException ioexception) {
		}
		return false;
	}
	public void spawnANPC(int npcID, int absX, int absY, int height, boolean respawns) {
		server.npcHandler.newNPC(npcID, (absX), (absY), height, absX + 3, absY + 3, absX - 3, absY - 3, 0, server.npcHandler.GetNpcListHP(npcID), respawns);
	}
	public void runDamage(int npc) {
		try {
			if (npc < 1) {
				return;
			}
			int damage = 0;
			int player = npcs[npc].hitPlayerId;
			if (player < 1) {
				return;
			}
			client thisPLAYER = (client) server.playerHandler.players[player];
			if (thisPLAYER == null) {
				return;
			}
			NPC thisNPC = (NPC) server.npcHandler.npcs[npc];

			if (thisNPC.hitMage == false && thisNPC.combatType == 3) {
				thisNPC.hitMage = false;
				return;
			}
			if (thisNPC.hitPlayer(player)) {

				damage = thisNPC.maxHit;
			} else {
				if (thisNPC.combatType == 3) {
					return;
				} 
				damage = 0;
			}
			thisNPC.hitMage = false;	
			if (thisNPC.IsDead) {
				return;
			}
			if (thisPLAYER.currentHealth == 0 || thisPLAYER == null) {
				return;
			}
			if (thisNPC.damageDelay == 0 && thisNPC.waiting) {
				thisNPC.waiting = false;	
				if (thisPLAYER.protMelee && thisNPC.combatType == 1) {
					damage = 0;
				}
				if (thisPLAYER.protRange && thisNPC.combatType == 2) {
					damage = 0;
				}
				if (thisPLAYER.protMage && thisNPC.combatType == 3) {
					return;
				}
				if (!thisPLAYER.hitUpdateRequired) {
					thisPLAYER.dealDamage(damage);
					thisPLAYER.hitDiff = damage;
					thisPLAYER.updateRequired = true;
					thisPLAYER.hitUpdateRequired = true;
					thisPLAYER.appearanceUpdateRequired = true;
				} else if (!thisPLAYER.hitUpdateRequired2) {
					thisPLAYER.dealDamage(damage);
					thisPLAYER.hitDiff2 = damage;
					thisPLAYER.updateRequired = true;
					thisPLAYER.hitUpdateRequired2 = true;
					thisPLAYER.appearanceUpdateRequired = true;
				}
			}
		} catch(Exception e) {
			NPC thisNPC = (NPC) server.npcHandler.npcs[npc];
			thisNPC.cB().destructNPC();
			return;
		}
	}
	public int calcRespawn(int npcid) {
		return respawnTime[npcid];
	}
	public int GetNpcListHP(int NpcID) {
		for (int i = 0; i < maxListedNPCs; i++) {
			if (NpcList[i] != null) {
				if (NpcList[i].npcId == NpcID) {
					return NpcList[i].npcHealth;
				}
			}
		}
		return 0;
	}
	public boolean GoodDistance(int objectX, int objectY, int playerX, int playerY, int distance) {
		for (int i = 0; i <= distance; i++) {
		  for (int j = 0; j <= distance; j++) {
			if ((objectX + i) == playerX && ((objectY + j) == playerY || (objectY - j) == playerY || objectY == playerY)) {
				return true;
			} else if ((objectX - i) == playerX && ((objectY + j) == playerY || (objectY - j) == playerY || objectY == playerY)) {
				return true;
			} else if (objectX == playerX && ((objectY + j) == playerY || (objectY - j) == playerY || objectY == playerY)) {
				return true;
			}
		  }
		}
		return false;
	}
	public boolean IsInRange(int NPCID, int MoveX, int MoveY) {
		int NewMoveX = (npcs[NPCID].absX + MoveX);
		int NewMoveY = (npcs[NPCID].absY + MoveY);
		if ((NewMoveX <= npcs[NPCID].moverangeX1) && (NewMoveX >= npcs[NPCID].moverangeX2) && (NewMoveY <= npcs[NPCID].moverangeY1) && (NewMoveY >= npcs[NPCID].moverangeY2)) {
			if (npcs[NPCID].walkingType == 1 || npcs[NPCID].walkingType == 2) {
				if (MoveX == MoveY) {
					return false;
				}
				return true;
			}
		}
		return false;
	}
	public boolean loadAutoSpawn(String FileName) {
		server.NpcAnimHandler.loadanim();
		String line = "";
		String token = "";
		String token2 = "";
		String token2_2 = "";
		String[] token3 = new String[10];
		boolean EndOfFile = false;
		BufferedReader characterfile = null;
		try {
			characterfile = new BufferedReader(new FileReader("./bin/config/"
					+ FileName));
		} catch (FileNotFoundException fileex) {
			misc.println(FileName + ": file not found.");
			return false;
		}
		try {
			line = characterfile.readLine();
		} catch (IOException ioexception) {
			misc.println(FileName + ": error loading file.");
			return false;
		}
		while ((EndOfFile == false) && (line != null)) {
			line = line.trim();
			int spot = line.indexOf("=");
			if (spot > -1) {
				token = line.substring(0, spot);
				token = token.trim();
				token2 = line.substring(spot + 1);
				token2 = token2.trim();
				token2_2 = token2.replaceAll("\t\t", "\t");
				token2_2 = token2_2.replaceAll("\t\t", "\t");
				token2_2 = token2_2.replaceAll("\t\t", "\t");
				token2_2 = token2_2.replaceAll("\t\t", "\t");
				token2_2 = token2_2.replaceAll("\t\t", "\t");
				token3 = token2_2.split("\t");
				if (token.equals("spawn")) {
					newNPC(
						Integer.parseInt(token3[0]), 
						Integer.parseInt(token3[1]), 
						Integer.parseInt(token3[2]),
						Integer.parseInt(token3[3]), 
						Integer.parseInt(token3[4]), 
						Integer.parseInt(token3[5]), 
						Integer.parseInt(token3[6]), 
						Integer.parseInt(token3[7]), 
						Integer.parseInt(token3[8]), 
						GetNpcListHP(Integer.parseInt(token3[0])
					), true);
				}
			} else {
				if (line.equals("[ENDOFSPAWNLIST]")) {
					try {
						characterfile.close();
					} catch (IOException ioexception) {
					}
					return true;
				}
			}
			try {
				line = characterfile.readLine();
			} catch (IOException ioexception1) {
				EndOfFile = true;
			}
		}
		try {
			characterfile.close();
		} catch (IOException ioexception) {
		}
		return false;
	}

	public boolean loadNPCList(String FileName) {
		String line = "";
		String token = "";
		String token2 = "";
		String token2_2 = "";
		String[] token3 = new String[10];
		boolean EndOfFile = false;
		BufferedReader characterfile = null;
		try {
			characterfile = new BufferedReader(new FileReader("./bin/config/" + FileName));
		} catch (FileNotFoundException fileex) {
			misc.println(FileName + ": file not found.");
			return false;
		}
		try {
			line = characterfile.readLine();
		} catch (IOException ioexception) {
			misc.println(FileName + ": error loading file.");
			return false;
		}
		while ((EndOfFile == false) && (line != null)) {
			line = line.trim();
			int spot = line.indexOf("=");
			if (spot > -1) {
				token = line.substring(0, spot);
				token = token.trim();
				token2 = line.substring(spot + 1);
				token2 = token2.trim();
				token2_2 = token2.replaceAll("\t\t", "\t");
				token2_2 = token2_2.replaceAll("\t\t", "\t");
				token2_2 = token2_2.replaceAll("\t\t", "\t");
				token2_2 = token2_2.replaceAll("\t\t", "\t");
				token2_2 = token2_2.replaceAll("\t\t", "\t");
				token3 = token2_2.split("\t");
				if (token.equals("npc")) {
					newNPCList(
						Integer.parseInt(token3[0]), token3[1],
						Integer.parseInt(token3[2]),
						Integer.parseInt(token3[3])
					);
					combatLevel[Integer.parseInt(token3[0])] = Integer.parseInt(token3[2]);
					if (token3.length == 4) {
						respawnTime[Integer.parseInt(token3[0])] = 0;
					} else {
						respawnTime[Integer.parseInt(token3[0])] = Integer.parseInt(token3[4]);
					}
				}
			} else {
				if (line.equals("[ENDOFNPCLIST]")) {
					try {
						characterfile.close();
					} catch (IOException ioexception) {
					}
					return true;
				}
			}
			try {
				line = characterfile.readLine();
			} catch (IOException ioexception1) {
				EndOfFile = true;
			}
		}
		try {
			characterfile.close();
		} catch (IOException ioexception) {
		}
		return false;
	}

	public void newNPC(int npcType, int x, int y, int heightLevel, int rangex1, int rangey1, int rangex2, int rangey2, int WalkingType, int HP, boolean Respawns) {
		// first, search for a free slot
		int slot = -1;
		for (int i = 1; i < maxNPCs; i++) {
			if (npcs[i] == null) {
				slot = i;
				break;
			}
		}

		if(slot == -1) return;		// no free slot found
                 if(HP <= 0) {
                  HP = 0;
                }
		NPC newNPC = new NPC(slot, npcType);
		newNPC.absX = x;
		newNPC.absY = y;
		newNPC.makeX = x;
		newNPC.makeY = y;
		newNPC.moverangeX1 = rangex1;
		newNPC.moverangeY1 = rangey1;
		newNPC.moverangeX2 = rangex2;
		newNPC.moverangeY2 = rangey2;
		newNPC.walkingType = WalkingType;
		newNPC.HP = HP;
		newNPC.MaxHP = HP;
		newNPC.MaxHit = (int)(combatLevel[npcType] / 3);
		if (newNPC.MaxHit < 1) {
			newNPC.MaxHit = 1;
		}
		newNPC.heightLevel = heightLevel;
                newNPC.Respawns = Respawns;
		npcs[slot] = newNPC;
	}
 	public void newNPCList(int npcType, String npcName, int combat, int HP) {
		// first, search for a free slot
		int slot = -1;
		for (int i = 0; i < maxListedNPCs; i++) {
			if (NpcList[i] == null) {
				slot = i;
				break;
			}
		}
		if (slot == -1) return; // no free slot found

		NPCList newNPCList = new NPCList(npcType);
		newNPCList.npcName = npcName;
		newNPCList.npcCombat = combat;
		newNPCList.npcHealth = HP;
		NpcList[slot] = newNPCList;
	}
	public void println(String str) {
		System.out.println(str);
	}
	public void process() {
		for (int i = 0; i < maxNPCSpawns; i++) {
			if (npcs[i] == null)
				continue;
			npcs[i].clearUpdateFlags();
		}
		for (int i = 0; i < maxNPCSpawns; i++) {
			if (npcs[i] != null) {
				if (npcs[i].damageDelay > 0) {
					npcs[i].damageDelay--;
				}
				if (npcs[i].combatTimer > 0) {
					npcs[i].inCombat = true;
					npcs[i].combatTimer--;
				} 
				if (npcs[i].combatTimer == 0) {
					npcs[i].inCombat = false;
					npcs[i].combatTimer = -1;
				}
				if (npcs[i].playerId > 0 && npcs[i].playerName.length() > 0 && !npcs[i].needsWalkBack) {
					npcs[i].cB().mainMethod();
					npcs[i].cB().process();
				} 
				if (npcs[i].inCombat == false && npcs[i].aggressive == true && !npcs[i].needsWalkBack) {
					npcs[i].cB().aggressiveNPC();
				}
				if (npcs[i].waiting && npcs[i].damageDelay == 0) {
					runDamage(i);
				}	
				if (npcs[i].needsWalkBack) {
					npcs[i].cB().walkBack();
				}
				if (npcs[i].actionTimer > 0) {
					npcs[i].actionTimer--;
				}
				if (npcs[i].freezeTimer > 0) {
					npcs[i].freezeTimer--;
				}
				if (npcs[i].IsDead == false) {

					if ((npcs[i].actionTimer == 0) && (npcs[i].IsClose == true)) {
						for (int j = 1; j < server.playerHandler.maxPlayers; j++) {
							if (server.playerHandler.players[j] != null) {
								server.playerHandler.players[j].RebuildNPCList = true;
							}
						}
						if (npcs[i].Respawns) {
							int old1 = npcs[i].npcType;
							int old2 = npcs[i].makeX;
							int old3 = npcs[i].makeY;
							int old4 = npcs[i].heightLevel;
							int old5 = npcs[i].moverangeX1;
							int old6 = npcs[i].moverangeY1;
							int old7 = npcs[i].moverangeX2;
							int old8 = npcs[i].moverangeY2;
							int old9 = npcs[i].walkingType;
							int old10 = npcs[i].MaxHP;							
							npcs[i] = null;
							newNPC(old1, old2, old3, old4, old5, old6, old7, old8, old9, old10, true);
							loadNPCsDefinitions("npcConfig.cfg");	
						}
					} else if (npcs[i].RandomWalk == true && !npcs[i].needsWalkBack && misc.random2(4) == 1 && npcs[i].moverangeX1 > 0 && npcs[i].moverangeY1 > 0 && npcs[i].moverangeX2 > 0 && npcs[i].moverangeY2 > 0) { //Move NPC
						int MoveX = misc.random(1);
						int MoveY = misc.random(1);
						int Rnd = misc.random2(4);
						if (Rnd == 1) {
							MoveX = -(MoveX);
							MoveY = -(MoveY);
						} else if (Rnd == 2) {
							MoveX = -(MoveX);
						} else if (Rnd == 3) {
							MoveY = -(MoveY);
						}
						if (IsInRange(i, MoveX, MoveY) == true) {
							npcs[i].moveX = MoveX;
							npcs[i].moveY = MoveY;
						}
						npcs[i].updateRequired = true;
					}
					if (npcs[i].RandomWalk == true) {
						npcs[i].getNextNPCMovement();
						
					}				
				} else if (npcs[i].IsDead == true) {
					loadNPCsDefinitions("npcConfig.cfg");
					if (npcs[i].actionTimer == 0 && npcs[i].DeadApply == false && npcs[i].NeedRespawn == false) {
						npcs[i].waiting = false;
						npcs[i].animNumber = server.NpcAnimHandler.die[npcs[i].npcType];
						npcs[i].updateRequired = true;
						npcs[i].animUpdateRequired = true;
						npcs[i].DeadApply = true;
						npcs[i].actionTimer = 4;
					} else if ((npcs[i].actionTimer == 0) && (npcs[i].DeadApply == true) && (npcs[i].NeedRespawn == false) && (npcs[i] != null)) {
						client temp = (client) server.playerHandler.players[npcs[i].getKiller()];
						if ((temp != null) && !temp.disconnected) {
							dropItems(i); // npc drops items!
							temp.attackedNpc = false;
							temp.attackedNpcId = -1;
						}
						npcs[i].NeedRespawn = true;
						npcs[i].actionTimer = calcRespawn(npcs[i].npcType);
						npcs[i].absX = npcs[i].makeX;
						npcs[i].absY = npcs[i].makeY;
						npcs[i].animNumber = 0x328;
						npcs[i].HP = npcs[i].MaxHP;
						npcs[i].updateRequired = true;
						npcs[i].animUpdateRequired = true;

					} else if ((npcs[i].actionTimer == 0) && (npcs[i].NeedRespawn == true)) {
						for (int j = 1; j < server.playerHandler.maxPlayers; j++) {
							if (server.playerHandler.players[j] != null) {
								server.playerHandler.players[j].RebuildNPCList = true;
							}
						}
                                                if(npcs[i].Respawns) {
							int old1 = npcs[i].npcType;
							int old2 = npcs[i].makeX;
							int old3 = npcs[i].makeY;
							int old4 = npcs[i].heightLevel;
							int old5 = npcs[i].moverangeX1;
							int old6 = npcs[i].moverangeY1;
							int old7 = npcs[i].moverangeX2;
							int old8 = npcs[i].moverangeY2;
							int old9 = npcs[i].walkingType;
							int old10 = npcs[i].MaxHP;							
							npcs[i] = null;
							newNPC(old1, old2, old3, old4, old5, old6, old7, old8, old9, old10, true);
							loadNPCsDefinitions("npcConfig.cfg");
						}
					}
				}
			}
		}
	}
	public void dropItems(int i) {
		int npc = 0;
		client c = (client)server.playerHandler.players[npcs[i].getKiller()];
		if(c != null) {
			for(npc = 0; npc < server.npcsdrops.drops.length; npc++){
				if(npcs[i].npcType == server.npcsdrops.drops[npc][0]) {
					if(misc.random(server.npcsdrops.drops[npc][3]) == 0) {
						ItemHandler.addItem(server.npcsdrops.drops[npc][1], npcs[i].absX, npcs[i].absY, server.npcsdrops.drops[npc][2], c.playerId, false);
					}
				}
			}
		}
	}
}