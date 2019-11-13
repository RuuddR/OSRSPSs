package server.players.Saving;

import server.*;
import server.util.*;
import server.npcs.*;
import server.items.*;
import server.world.*;
import server.players.*;
import server.players.Saving.*;
//import server.players.Quests.*;

import java.io.*;

public class SaveGame {

	public client client;
	
	public SaveGame(client c) {
		client = c;
	}

	public int loadgame(client c, String playerName, String playerPass) {
		for (Integer bUid : server.bannedUid) {
			if (c.uid == bUid.intValue()) {
				return 4;
			}
		}

		long start = System.currentTimeMillis();
		String line = "";
		String token = "";
		String token2 = "";
		String[] token3 = new String[3];
		boolean EndOfFile = false;
		int ReadMode = 0;
		BufferedReader characterfile = null;
		boolean charFileFound = false;
		int[] playerLooks = new int[19];

		try {
			characterfile = new BufferedReader(new FileReader("./bin/characters/"
					+ playerName + ".txt"));
			charFileFound = true;
		} catch (FileNotFoundException fileex1) {
		}

		if (charFileFound == false) {
			misc.println(playerName + ": character file not found.");
			return 0;
		}

		try {
			line = characterfile.readLine();
		} catch (IOException ioexception) {
			misc.println(playerName + ": error loading file.");
			return 3;
		}
		while ((EndOfFile == false) && (line != null)) {
			line = line.trim();
			int spot = line.indexOf("=");
			if (spot > -1) {
				token = line.substring(0, spot);
				token = token.trim();
				token2 = line.substring(spot + 1);
				token2 = token2.trim();
				token3 = token2.split("\t");
				switch (ReadMode) {
				case 1:
					if (token.equals("character-username")) {
						if (playerName.equalsIgnoreCase(token2)) {
						} else {
							c.saveNeeded = false;
							c.validClient = false;
							return 3;
						}
					} else if (token.equals("character-password")) {
						if (c.playerPass.equalsIgnoreCase(token2)) {
						} else {
							c.saveNeeded = false;
							c.validClient = false;
							return 3;
						}
					}
					break;
				case 2:
					if (token.equals("character-height")) {
						c.heightLevel = Integer.parseInt(token2);
					} else if (token.equals("character-posx")) {
						c.teleportToX = Integer.parseInt(token2) == -1 ? Constants.STARTING_SPAWN_POINT_X_CITY : Integer.parseInt(token2);
					} else if (token.equals("character-posy")) {
						c.teleportToY = Integer.parseInt(token2) == -1 ? Constants.STARTING_SPAWN_POINT_Y_CITY : Integer.parseInt(token2);
					} else if (token.equals("character-rights")) {
						c.playerRights = Integer.parseInt(token2);
					} else if (token.equals("character-ismember")) {
						c.playerIsMember = Integer.parseInt(token2);
					} else if (token.equals("character-messages")) {
						c.playerMessages = Integer.parseInt(token2);
					} else if (token.equals("character-lastconnection")) {
						c.playerLastConnect = token2;
					} else if (token.equals("character-lastlogin")) {
						c.playerLastLogin = Integer.parseInt(token2);
					} else if (token.equals("character-energy")) {
						c.playerEnergy = Integer.parseInt(token2);
					} else if (token.equals("character-gametime")) {
						c.playerGameTime = Integer.parseInt(token2);
					} else if (token.equals("character-gamecount")) {
						c.playerGameCount = Integer.parseInt(token2);
					} else if (token.equals("character-ancients")) {
						c.ancients = Integer.parseInt(token2);
					} else if (token.equals("character-rating")) {
						c.rating = Integer.parseInt(token2);
					} else if (token.equals("character-tqp")) {
						c.QH().tqp = Integer.parseInt(token2);
					} else if (token.equals("character-q1")) {
						c.QH().q1 = Integer.parseInt(token2);
					} else if (token.equals("character-q2")) {
						c.QH().q2 = Integer.parseInt(token2);
					} else if (token.equals("character-q3")) {
						c.QH().q3 = Integer.parseInt(token2);
					} else if (token.equals("skullTimer")) {
						c.skullTimer = Integer.parseInt(token2);
					}
					break;
				case 3:
					if (token.equals("character-equip")) {
						c.playerEquipment[Integer.parseInt(token3[0])] = Integer
								.parseInt(token3[1]);
						c.playerEquipmentN[Integer.parseInt(token3[0])] = Integer
								.parseInt(token3[2]);
					}
					break;
				case 4:
					if (token.equals("character-look")) {
						playerLooks[Integer.parseInt(token3[0])] = Integer
								.parseInt(token3[1]);
					}
					break;
				case 5:
					if (token.equals("character-skill")) {
						c.playerLevel[Integer.parseInt(token3[0])] = Integer
								.parseInt(token3[1]);
						c.playerXP[Integer.parseInt(token3[0])] = Integer
								.parseInt(token3[2]);

						int level = 0;
						if (c.playerXP[Integer.parseInt(token3[0])] > 13040000) {
							level = 99;
						} else {
							level = c.getLevelForXP(c.playerXP[Integer
									.parseInt(token3[0])]);
						}
						c.playerLevel[Integer.parseInt(token3[0])] = level;
						c.setSkillLevel(Integer.parseInt(token3[0]), level,
								c.playerXP[Integer.parseInt(token3[0])]);
						if (Integer.parseInt(token3[0]) == 3) {
							c.currentHealth = level;
							c.maxHealth = level;
						}
					}
					break;
				case 6:
					if (token.equals("character-item")) {
						c.playerItems[Integer.parseInt(token3[0])] = Integer
								.parseInt(token3[1]);
						c.playerItemsN[Integer.parseInt(token3[0])] = Integer
								.parseInt(token3[2]);
					}
					break;
				case 7:
					if (token.equals("character-bank")) {
						c.bankItems[Integer.parseInt(token3[0])] = Integer
								.parseInt(token3[1]);
						c.bankItemsN[Integer.parseInt(token3[0])] = Integer
								.parseInt(token3[2]);
					}
					break;
				case 8:
					if (token.equals("character-friend")) {
						c.friends[Integer.parseInt(token3[0])] = Long
								.parseLong(token3[1]);
					}
					break;
				case 9:
					if (token.equals("character-ignore")) {
						c.ignores[Integer.parseInt(token3[0])] = Long
								.parseLong(token3[1]);
					}
					break;
				}
			} else {
				if (line.equals("[ACCOUNT]"))
					ReadMode = 1;
				else if (line.equals("[QUESTS]"))
					ReadMode = 2;
				else if (line.equals("[CHARACTER]"))
					ReadMode = 2;
				else if (line.equals("[MUSIC]"))
					ReadMode = 2;
				else if (line.equals("[EQUIPMENT]"))
					ReadMode = 3;
				else if (line.equals("[LOOK]"))
					ReadMode = 4;
				else if (line.equals("[SKILLS]"))
					ReadMode = 5;
				else if (line.equals("[ITEMS]"))
					ReadMode = 6;
				else if (line.equals("[BANK]"))
					ReadMode = 7;
				else if (line.equals("[FRIENDS]"))
					ReadMode = 8;
				else if (line.equals("[IGNORES]"))
					ReadMode = 9;
				else if (line.equals("[EOF]")) {
					// end of file now do whatever you set.
					try {
						characterfile.close();
					} catch (IOException ioexception) {
					}
					c.setLook(playerLooks);
					c.updateRequired = true;
					c.appearanceUpdateRequired = true;
					long end = System.currentTimeMillis() - start;
					c.println("<"+ (c.playerRights > 0 ? "STAFF" : "PLAYER") +"> Connection accepted, Process time: " + end + "ms.");
					return 0;
				}
			}

			// Exception occured @ some line
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
		c.println("Failed to load player: " + playerName);
		return 13;
	}







		public void savegame(client c, boolean logout) {

		if ((c.playerName == null) || !c.validClient) {
			c.saveNeeded = false;
			return;
		}

		if (logout) {
			if (c.fightId > 0) {
				client f = (client) server.playerHandler.players[c.fightId];
				if (f != null) {
					f.fighting = false;
					f.hits = 0;
				}
			}

		}
		//if (logout && inTrade) {
		//	c.sendMessage("declined trade, logging out");
			//declineTrade();
		//}

		BufferedWriter characterfile = null;
		try {
			characterfile = new BufferedWriter(new FileWriter("./bin/characters/"
					+ c.playerName + ".txt"));
			/* ACCOUNT */
			characterfile.write("[ACCOUNT]", 0, 9);
			characterfile.newLine();
			characterfile.write("character-username = ", 0, 21);
			characterfile.write(c.playerName, 0, c.playerName.length());
			characterfile.newLine();
			characterfile.write("character-password = ", 0, 21);
			characterfile.write((c.playerPass), 0, (c.playerPass).length());
			characterfile.newLine();
			characterfile.newLine();
			/* QUESTS */
			characterfile.write("[QUESTS]", 0, 8);
			characterfile.newLine();
			characterfile.write("character-tqp = ", 0, 16);
			characterfile.write(Integer.toString(c.QH().tqp), 0, Integer.toString(c.QH().tqp).length());
			characterfile.newLine();
			characterfile.write("character-q1 = ", 0, 15);
			characterfile.write(Integer.toString(c.QH().q1), 0, Integer.toString(c.QH().q1).length());
			characterfile.newLine();
			characterfile.write("character-q2 = ", 0, 15);
			characterfile.write(Integer.toString(c.QH().q2), 0, Integer.toString(c.QH().q2).length());
			characterfile.newLine();
			characterfile.write("character-q3 = ", 0, 15);
			characterfile.write(Integer.toString(c.QH().q3), 0, Integer.toString(c.QH().q3).length());
			characterfile.newLine();
			characterfile.write("skulled = ", 0, 10);
			characterfile.write(Integer.toString(c.skullTimer), 0, Integer.toString(c.skullTimer).length());
			//characterfile.write("" + c.skullTimer, 0, Integer.toString(c.skullTimer).length());
			characterfile.newLine();
			characterfile.newLine();

			/* CHARACTER */
			characterfile.write("[CHARACTER]", 0, 11);
			characterfile.newLine();
			characterfile.write("character-height = ", 0, 19);
			characterfile.write(Integer.toString(c.heightLevel), 0, Integer.toString(c.heightLevel).length());
			characterfile.newLine();
			if (Constants.STORY_MODE == true && c.QH().q1 < 15) {
			characterfile.write("character-posx = ", 0, 17);
			characterfile.write(Integer.toString(c.absX == -1 ? Constants.STARTING_SPAWN_POINT_X_CITY : c.absX), 0, Integer.toString(c.absX == -1 ? Constants.STARTING_SPAWN_POINT_X : c.absX).length());
			characterfile.newLine();
			characterfile.write("character-posy = ", 0, 17);
			characterfile.write(Integer.toString(c.absY == -1 ? Constants.STARTING_SPAWN_POINT_Y_CITY : c.absY), 0, Integer.toString(c.absY == -1 ? Constants.STARTING_SPAWN_POINT_Y : c.absY).length());
			characterfile.newLine();
			} else {
			characterfile.write("character-posx = ", 0, 17);
			characterfile.write(Integer.toString(c.absX == -1 ? Constants.STARTING_SPAWN_POINT_X_CITY : c.absX), 0, Integer.toString(c.absX == -1 ? Constants.STARTING_SPAWN_POINT_X_CITY : c.absX).length());
			characterfile.newLine();
			characterfile.write("character-posy = ", 0, 17);
			characterfile.write(Integer.toString(c.absY == -1 ? Constants.STARTING_SPAWN_POINT_Y_CITY : c.absY), 0, Integer.toString(c.absY == -1 ? Constants.STARTING_SPAWN_POINT_Y_CITY : c.absY).length());
			characterfile.newLine();
			}
			characterfile.write("character-rights = ", 0, 19);
			characterfile.write(Integer.toString(c.playerRights), 0, Integer.toString(c.playerRights).length());
			characterfile.newLine();
			characterfile.write("character-ismember = ", 0, 21);
			characterfile.write(Integer.toString(c.playerIsMember), 0, Integer.toString(c.playerIsMember).length());
			characterfile.newLine();
			characterfile.write("character-messages = ", 0, 21);
			characterfile.write(Integer.toString(c.playerMessages), 0, Integer.toString(c.playerMessages).length());
			characterfile.newLine();
			characterfile.write("character-lastconnection = ", 0, 27);
			characterfile.write(c.playerLastConnect, 0, c.playerLastConnect.length());
			characterfile.newLine();
			characterfile.write("character-lastlogin = ", 0, 22);
			characterfile.write(Integer.toString(c.playerLastLogin), 0, Integer.toString(c.playerLastLogin).length());
			characterfile.newLine();
			characterfile.write("character-energy = ", 0, 19);
			characterfile.write(Integer.toString(c.playerEnergy), 0, Integer.toString(c.playerEnergy).length());
			characterfile.newLine();
			characterfile.write("character-gametime = ", 0, 21);
			characterfile.write(Integer.toString(c.playerGameTime), 0, Integer.toString(c.playerGameTime).length());
			characterfile.newLine();
			characterfile.write("character-gamecount = ", 0, 22);
			characterfile.write(Integer.toString(c.playerGameCount), 0, Integer.toString(c.playerGameCount).length());
			characterfile.newLine();
			characterfile.write("character-ancients = ", 0, 21);
			characterfile.write(Integer.toString(c.ancients), 0, Integer.toString(c.ancients).length());
			characterfile.newLine();
			characterfile.write("character-rating = ", 0, 19);
			characterfile.write(Integer.toString(c.rating), 0, Integer.toString(c.rating).length());
			characterfile.newLine();
			characterfile.newLine();
			/* EQUIPMENT */
			characterfile.write("[EQUIPMENT]", 0, 11);
			characterfile.newLine();
			for (int i = 0; i < c.playerEquipment.length; i++) {
				characterfile.write("character-equip = ", 0, 18);
				characterfile.write(Integer.toString(i), 0, Integer.toString(i).length());
				characterfile.write("	", 0, 1);
				characterfile.write(Integer.toString(c.playerEquipment[i]), 0, Integer.toString(c.playerEquipment[i]).length());
				characterfile.write("	", 0, 1);
				characterfile.write(Integer.toString(c.playerEquipmentN[i]), 0, Integer.toString(c.playerEquipmentN[i]).length());
				characterfile.write("	", 0, 1);
				characterfile.newLine();
			}
			characterfile.newLine();
			/* LOOK */
			characterfile.write("[LOOK]", 0, 6);
			characterfile.newLine();
			int[] Looks = c.getLook();
			for (int i = 0; i < Looks.length; i++) {
				characterfile.write("character-look = ", 0, 17);
				characterfile.write(Integer.toString(i), 0, Integer.toString(i).length());
				characterfile.write("	", 0, 1);
				characterfile.write(Integer.toString(Looks[i]), 0, Integer.toString(Looks[i]).length());
				characterfile.newLine();
			}
			characterfile.newLine();
			/* SKILLS */
			characterfile.write("[SKILLS]", 0, 8);
			characterfile.newLine();
			for (int i = 0; i < c.playerLevel.length; i++) {
				characterfile.write("character-skill = ", 0, 18);
				characterfile.write(Integer.toString(i), 0, Integer.toString(i).length());
				characterfile.write("	", 0, 1);
				characterfile.write(Integer.toString(c.playerLevel[i]), 0, Integer.toString(c.playerLevel[i]).length());
				characterfile.write("	", 0, 1);
				characterfile.write(Integer.toString(c.playerXP[i]), 0, Integer.toString(c.playerXP[i]).length());
				characterfile.newLine();
			}
			characterfile.newLine();
			/* ITEMS */
			characterfile.write("[ITEMS]", 0, 7);
			characterfile.newLine();
			for (int i = 0; i < c.playerItems.length; i++) {
				if (c.playerItems[i] > 0) {
					characterfile.write("character-item = ", 0, 17);
					characterfile.write(Integer.toString(i), 0, Integer.toString(i).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Integer.toString(c.playerItems[i]), 0, Integer.toString(c.playerItems[i]).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Integer.toString(c.playerItemsN[i]), 0, Integer.toString(c.playerItemsN[i]).length());
					characterfile.newLine();
				}
			}
			characterfile.newLine();
			/* BANK */
			characterfile.write("[BANK]", 0, 6);
			characterfile.newLine();
			for (int i = 0; i < c.bankItems.length; i++) {
				if (c.bankItems[i] > 0) {
					characterfile.write("character-bank = ", 0, 17);
					characterfile.write(Integer.toString(i), 0, Integer.toString(i).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Integer.toString(c.bankItems[i]), 0, Integer.toString(c.bankItems[i]).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Integer.toString(c.bankItemsN[i]), 0, Integer.toString(c.bankItemsN[i]).length());
					characterfile.newLine();
				}
			}
			characterfile.newLine();
			/* FRIENDS */
			characterfile.write("[FRIENDS]", 0, 9);
			characterfile.newLine();
			for (int i = 0; i < c.friends.length; i++) {
				if (c.friends[i] > 0) {
					characterfile.write("character-friend = ", 0, 19);
					characterfile.write(Integer.toString(i), 0, Integer.toString(i).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Long.toString(c.friends[i]), 0, Long.toString(c.friends[i]).length());
					characterfile.newLine();
				}
			}
			characterfile.newLine();
			/* IGNORES */
			characterfile.write("[IGNORES]", 0, 9);
			characterfile.newLine();
			for (int i = 0; i < c.ignores.length; i++) {
				if (c.ignores[i] > 0) {
					characterfile.write("character-ignore = ", 0, 19);
					characterfile.write(Integer.toString(i), 0, Integer.toString(i).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Long.toString(c.ignores[i]), 0, Long.toString(c.ignores[i]).length());
					characterfile.newLine();
				}
			}
			characterfile.newLine();
			/* EOF */
			characterfile.write("[EOF]", 0, 5);
			characterfile.newLine();
			characterfile.newLine();
			characterfile.close();
		} catch (IOException ioexception) {
			misc.println(c.playerName + ": error writing file.");
		}
		c.saveNeeded = false;
	}

}
