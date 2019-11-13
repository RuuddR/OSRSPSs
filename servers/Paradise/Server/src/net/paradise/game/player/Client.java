package net.paradise.game.player;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

import net.paradise.Server;
import net.paradise.game.GraphicsHandler;
import net.paradise.game.ObjectHandler;
import net.paradise.game.ShopHandler;
import net.paradise.game.TextHandler;
import net.paradise.game.item.Item;
import net.paradise.game.item.Item2;
import net.paradise.game.item.Item4;
import net.paradise.game.item.ItemHandler;
import net.paradise.game.npc.NPCHandler;
import net.paradise.net.PacketStation;
import net.paradise.net.StreamBuffer;
import net.paradise.security.MessageDigest5;
import net.paradise.util.IsaacRandom;
import net.paradise.util.MethodCollection;

public class Client extends Player implements Runnable
{

	private static final Random random = new Random();

	private boolean allowStore = true;
	private long creationTimeMillis = System.currentTimeMillis();

	public void gfx100(int gfx)
	{
		mask100var1 = gfx;
		mask100var2 = 5898240;
		mask100update = true;
		updateRequired = true;
	}

	public void msgStaff(String msg)
	{
		for (Player element : PlayerHandler.players)
		{
			Client p = (Client) element;
			if ((p != null) && !p.disconnected && (p.absX > 0)
					&& (p.playerRights >= 1))
			{
				p.sendMessage(msg);
			}
		}
	}

	public void clanChatMenu()
	{
		setText("", 6272);
		setText("", 6271);
		setText("", 9926);
		setText("@or1@  Clan", 963);
		setText("@or1@  Chat", 8934);
		setText("Chatting in: @whi@" + clanName, 5450);
		setText("", 4439);
		setText("Clan leader: @whi@" + clanLeader, 3206);
		setText("", 5449);
		setText("", 4301);
		setText("//end of clan chat", 4302);
		setText("-----------------", 4303);
		for (int i = 4287; i <= 4303; i++)
		{
			if (clanMembers[i - 4287] != null)
			{
				setText("@whi@" + clanMembers[i - 4287], CCID[i - 4287]);
			}
			else
			{
				setText("", CCID[i - 4287]);
			}
		}
	}

	public void updateCCMenu()
	{
		for (Player p : PlayerHandler.players)
		{
			if (p != null)
			{
				Client person = (Client) p;
				if ((person.username != null || person.username != "null"))
				{
					Client c2 = (Client) PlayerHandler.players[PlayerHandler
							.getPlayerID(clanLeader)];
					if (person.clanLeader == clanLeader)
					{
						person.setText("Chatting in: @whi@" + clanName, 5450);
						person.setText("Clan leader: @whi@" + clanLeader, 3206);
						for (int i = 4287; i <= 4303; i++)
						{
							if (c2.clanMembers[i - 4287] != null)
							{
								person.setText("@whi@"
										+ c2.clanMembers[i - 4287],
										CCID[i - 4287]);
							}
							else
							{
								person.setText("", CCID[i - 4287]);
							}
						}
					}
				}
			}
		}
	}

	public void removeFromCC()
	{
		Client c2 = (Client) PlayerHandler.players[PlayerHandler
				.getPlayerID(clanLeader)];
		for (int i = 4287; i <= 4303; i++)
		{
			if (c2.clanMembers[i - 4287] == username)
			{
				c2.clanMembers[i - 4287] = null;
				updateCCMenu();
				break;
			}
		}
		clanName = "none";
		clanLeader = "nobody";
		clanChatMenu();
	}

	public void sendClanMessage(String msg)
	{
		for (Player p : PlayerHandler.players)
		{
			if (p != null)
			{
				Client person = (Client) p;
				if ((person.username != null || person.username != "null"))
				{
					if (person.clanLeader == clanLeader)
					{
						person.sendMessage("[@blu@" + clanName + "@bla@] "
								+ username + ": @dre@" + msg);
					}
				}
			}
		}
	}

	public void Object(int objectX, int objectY, int NewObjectID, int Face,
			int ObjectType)
	{
		for (Player p : PlayerHandler.players)
		{
			if (p != null)
			{
				Client person = (Client) p;
				if ((person.username != null || person.username != "null"))
				{
					if (person.distanceToPoint(objectX, objectY) <= 60)
					{
						person.createNewTileObject(objectX, objectY,
								NewObjectID, Face, ObjectType);
					}
				}
			}
		}
	}

	private boolean hasAccepted = false;

	private void checkBadNames()
	{
		if (username.length() > 12)
		{
			logout();
			savefile = false;
			return;
		}
		String[] badNames = { ">", "<", ".", "~", "!", "@", "#", "$", "%", "^",
				"&", "*", "(", ")", "\"", "\\", "/", "whore", "-", "_", "=",
				":", "Þ", "`", "'", "	", "scape", "SYI", "Syi", "SYi", "syi",
				"syI", "sYI" };
		for (String bN : badNames)
		{
			if (username.contains(bN) && !connectedFrom.equals("127.0.0.1")
					|| username.equalsIgnoreCase(bN)
					&& !connectedFrom.equals("127.0.0.1")
					|| username.equals("")
					&& !connectedFrom.equals("127.0.0.1"))
			{
				logout();
				savefile = false;
				return;
			}
			else
			{
			}
		}
	}

	public void rearrangeBank()
	{ // this fills up the empty spots
		int totalItems = 0;
		int highestSlot = 0;
		for (int i = 0; i < playerBankSize; i++)
		{
			if (bankItems[i] != 0)
			{
				totalItems++;
				if (highestSlot <= i)
					highestSlot = i;
			}
		}

		for (int i = 0; i <= highestSlot; i++)
		{
			if (bankItems[i] == 0)
			{
				boolean stop = false;

				for (int k = i; k <= highestSlot; k++)
				{
					if (bankItems[k] != 0 && !stop)
					{
						int spots = k - i;
						for (int j = k; j <= highestSlot; j++)
						{
							bankItems[j - spots] = bankItems[j];
							bankItemsN[j - spots] = bankItemsN[j];
							stop = true;
							bankItems[j] = 0;
							bankItemsN[j] = 0;
						}
					}
				}
			}
		}

		int totalItemsAfter = 0;
		for (int i = 0; i < playerBankSize; i++)
		{
			if (bankItems[i] != 0)
			{
				totalItemsAfter++;
			}
		}

		if (totalItems != totalItemsAfter)
			outStream.createFrame(109); // disconnects when duping

	}

	private int apickupid = -1;
	private int apickupx = -1;
	private int apickupy = -1;
	private boolean hasntLoggedin = false;

	private void scanPickup()
	{
		if (absX == apickupx && absY == apickupy)
		{
			if (ItemHandler.itemExists(apickupid, absX, absY))
			{
				int itemAmount = ItemHandler.itemAmount(apickupid, apickupx,
						apickupy);
				if (addItem(apickupid, itemAmount))
				{
					ItemHandler.removeItem(apickupid, apickupx, apickupy,
							itemAmount);
				}
			}
			else if (hasntLoggedin)
			{
				sendMessage("It`s gone!");
			}
			resetPickup(); // will reset the pickup
		}
	}

	private void resetPickup()
	{
		apickupid = -1;
		apickupx = -1;
		apickupy = -1;
	}

	private boolean serverpanel = true;

	public void CheckServerPanel()
	{
		if (serverpanel)
		{
			serverpanel = false;
			sendMessage("You have turned the server panel off. Type ::serverpanel to get it back.");
		}
		else if (!serverpanel)
		{
			serverpanel = true;
			sendMessage("You have turned the server panel back on. Type ::serverpanel if you wish to turn it off.");
		}
	}

	public void WritePlayers()
	{
		if (serverpanel)
		{
			setInterfaceWalkable(15892);
		}
		else if (!serverpanel)
		{
			setInterfaceWalkable(-1);
		}
		int players = PlayerHandler.getPlayerCount();
		if (nonWild())
		{
			setText("Hello.", 15900);
		}
		else if (!nonWild())
		{
			setText("Hello.", 15900);
		}
		setText("Server Panel:", 15894);
		setText("Vote4paradise.tk", 15895);
		setText("Please Check ::rules", 15897);
		setText("Players Online: @gre@" + players, 15898);
		setText("Have A Great Time!", 15899);
		setText("", 15901);
		setText("", 15896);
		setText("", 15902);
		setText("", 15903);
		setText("", 15904);
		setText("", 15905);
		setText("", 15906);
	}

	public String cannonowner;

	public boolean nonattackable(int npcIndex)
	{
		if (NPCHandler.npcs[npcIndex].id == 27
				|| NPCHandler.npcs[npcIndex].id == 649
				|| NPCHandler.npcs[npcIndex].id == 649
				|| NPCHandler.npcs[npcIndex].id == 541
				|| NPCHandler.npcs[npcIndex].id == 2862
				|| NPCHandler.npcs[npcIndex].id == 14
				|| NPCHandler.npcs[npcIndex].id == 538
				|| NPCHandler.npcs[npcIndex].id == 538
				|| NPCHandler.npcs[npcIndex].id == 537
				|| NPCHandler.npcs[npcIndex].id == 520
				|| NPCHandler.npcs[npcIndex].id == 551
				|| NPCHandler.npcs[npcIndex].id == 2909
				|| NPCHandler.npcs[npcIndex].id == 2909
				|| NPCHandler.npcs[npcIndex].id == 946
				|| NPCHandler.npcs[npcIndex].id == 985
				|| NPCHandler.npcs[npcIndex].id == 985
				|| NPCHandler.npcs[npcIndex].id == 554
				|| NPCHandler.npcs[npcIndex].id == 1702
				|| NPCHandler.npcs[npcIndex].id == 220
				|| NPCHandler.npcs[npcIndex].id == 201
				|| NPCHandler.npcs[npcIndex].id == 550
				|| NPCHandler.npcs[npcIndex].id == 1001
				|| NPCHandler.npcs[npcIndex].id == 3283
				|| NPCHandler.npcs[npcIndex].id == 3283
				|| NPCHandler.npcs[npcIndex].id == 2195
				|| NPCHandler.npcs[npcIndex].id == 946
				|| NPCHandler.npcs[npcIndex].id == 546
				|| NPCHandler.npcs[npcIndex].id == 536
				|| NPCHandler.npcs[npcIndex].id == 526
				|| NPCHandler.npcs[npcIndex].id == 548
				|| NPCHandler.npcs[npcIndex].id == 524
				|| NPCHandler.npcs[npcIndex].id == 34
				|| NPCHandler.npcs[npcIndex].id == 526
				|| NPCHandler.npcs[npcIndex].id == 541
				|| NPCHandler.npcs[npcIndex].id == 660
				|| NPCHandler.npcs[npcIndex].id == 3394
				|| NPCHandler.npcs[npcIndex].id == 917
				|| NPCHandler.npcs[npcIndex].id == 1
				|| NPCHandler.npcs[npcIndex].id == 3782
				|| NPCHandler.npcs[npcIndex].id == 3772
				|| NPCHandler.npcs[npcIndex].id == 2
				|| NPCHandler.npcs[npcIndex].id == 1
				|| NPCHandler.npcs[npcIndex].id == 648
				|| NPCHandler.npcs[npcIndex].id == 1694)
		{
			return true;
		}
		return false;
	}

	// Playercount at top of screen
	public void writePlayers()
	{
		int players = PlayerHandler.getPlayerCount();
		sendFrame126("@gra@- Players Online - " + players, 6570);// 6570
		sendFrame126("@gre@- Paradise -", 6572);
		sendFrame126("", 6664);
		setInterfaceWalkable(6673); // 6673}}}
	}

	private static final DateFormat dateFormat = new SimpleDateFormat(
			"yyyy-MM-dd");

	private void openWelcomeScreen()
	{
		setText("@gre@~ ~ ~ @whi@Paradise @gre@~ ~ ~", 15950);
		setText("", 15960);
		setText("", 15961);
		setText("Vote4paradise.tk To Vote!", 15959);
		setText("Update: ::guilds and ::dice", 15951);
		setText("Have fun on the server!", 15952);
		setText("Registered on: "
				+ dateFormat.format(new Date(creationTimeMillis)), 15953);
		setText("Forums: paradise317.proboards.com", 15954);
		setText("", 15955);
		setText("", 15956);
		setText("", 15957);
		setText("", 15958);
		showInterface(15944);
	}

	public String specbar;
	public String clan = null;
	public int oldX, oldY, oldHeight;
	public int newheadicon2 = 0;
	public int totalz = (getLevelForXP(playerXP[0])
			+ getLevelForXP(playerXP[1]) + getLevelForXP(playerXP[2])
			+ getLevelForXP(playerXP[3]) + getLevelForXP(playerXP[4])
			+ getLevelForXP(playerXP[5]) + getLevelForXP(playerXP[6])
			+ getLevelForXP(playerXP[7]) + getLevelForXP(playerXP[8])
			+ getLevelForXP(playerXP[9]) + getLevelForXP(playerXP[10])
			+ getLevelForXP(playerXP[0]) + getLevelForXP(playerXP[11])
			+ getLevelForXP(playerXP[12]) + getLevelForXP(playerXP[13])
			+ getLevelForXP(playerXP[14]) + getLevelForXP(playerXP[15])
			+ getLevelForXP(playerXP[16]) + getLevelForXP(playerXP[17])
			+ getLevelForXP(playerXP[18]) + getLevelForXP(playerXP[19]) + getLevelForXP(playerXP[20]));
	public int JunaTele = -1;

	public void staticAnimation(int graphicID, int playerX, int playerY,
			int heightLevel)
	{
		outStream.createFrame(85);
		outStream.putByteC(playerY - (mapRegionY * 8));
		outStream.putByteC(playerX - (mapRegionX * 8));
		outStream.createFrame(4);
		outStream.put(0);
		outStream.putShort(graphicID); // Graphic ID
		outStream.put(heightLevel); // Height above gorund
		outStream.putShort(0); // Pause before casting
	}

	public void gfxhalfway(int id, int Y, int X)
	{
		outStream.createFrame(85);
		outStream.putByteC(Y - (mapRegionY * 8));
		outStream.putByteC(X - (mapRegionX * 8));
		outStream.createFrame(4);
		outStream.put(0); // Tiles away (X >> 4 + Y & 7)
		outStream.putShort(id); // Graphic id
		outStream.put(100);
		outStream.putShort(0); // Time before casting the graphic
	}

	public boolean HasAirStaff()
	{
		if (playerEquipment[playerWeapon] == 1381)
		{
			return true;
		}// Staff_of_air
		if (playerEquipment[playerWeapon] == 1397)
		{
			return true;
		}// Air_battlestaff
		if (playerEquipment[playerWeapon] == 1405)
		{
			return true;
		}// Mystic_air_staff
		return false;
	}

	public void openUpDepBox()
	{
		sendFrame126("@whi@The Official Deposit Box Of " + username + "", 7421);
		sendFrame248(4465, 197);// 197 just because you can't see it =\
		resetItems(7423);
		IsBanking = true;
	}

	// public void resetserver()
	// {
	// // println("SERVER RESETTING!");
	// MethodCollection.println("Saving all games...");
	// PlayerHandler.kickAllPlayers = true;
	// MethodCollection.println("GAME SUCCESSFULLY SAVED FOR ALL PLAYERS");
	// closeListener();
	// runserver();
	// }

	// public void runserver()
	// {
	// try
	// {
	// String File = "runserver.bat"; // File specified
	// String Dir = "./" + File; // Directory specified
	// Runtime.getRuntime().exec(Dir);
	// }
	// catch (IOException ioe)
	// {
	// ioe.printStackTrace();
	// }
	// }

	// public void closeListener()
	// {
	// try
	// {
	// Server.shutdownClientHandler = true;
	// if (Server.serverSocket != null)
	// {
	// Server.serverSocket.close();
	// }
	// Server.serverSocket = null;
	// }
	// catch (java.lang.Exception __ex)
	// {
	// __ex.printStackTrace();
	// }
	// }

	public void earthrobes()
	{
		int EnemyX2 = PlayerHandler.players[AttackingOn].absX;
		int EnemyY2 = PlayerHandler.players[AttackingOn].absY;
		if (MethodCollection.random(5) == 1)
		{
			sendMessage("Your earth robes begin to glow.");
			sendMessage("You strike the enemy and deal damage!");
			hitDiff = MethodCollection.random(40);
			setAnimation(811);
			stillgfx(367, EnemyY2, EnemyX2);
		}
	}

	public void firerobes()
	{
		int EnemyX2 = PlayerHandler.players[AttackingOn].absX;
		int EnemyY2 = PlayerHandler.players[AttackingOn].absY;
		if (MethodCollection.random(5) == 1)
		{
			sendMessage("Your fire robes begin to glow.");
			sendMessage("You strike the enemy and deal damage!");
			hitDiff = MethodCollection.random(40);
			setAnimation(811);
			stillgfx(481, EnemyY2, EnemyX2);
		}
	}

	public void airrobes()
	{
		int EnemyX2 = PlayerHandler.players[AttackingOn].absX;
		int EnemyY2 = PlayerHandler.players[AttackingOn].absY;
		if (MethodCollection.random(5) == 1)
		{
			sendMessage("Your air robes begin to glow.");
			sendMessage("You strike the enemy and deal damage!");
			hitDiff = MethodCollection.random(40);
			setAnimation(811);
			stillgfx(198, EnemyY2, EnemyX2);
		}
	}

	public void waterrobes()
	{
		int EnemyX2 = PlayerHandler.players[AttackingOn].absX;
		int EnemyY2 = PlayerHandler.players[AttackingOn].absY;
		if (MethodCollection.random(5) == 1)
		{
			sendMessage("Your water robes begin to glow.");
			sendMessage("You strike the enemy and deal damage!");
			hitDiff = MethodCollection.random(40);
			setAnimation(811);
			stillgfx(5, EnemyY2, EnemyX2);
		}
	}

	public void bowSpecc()
	{
		if (IsAttackingNPC && playerEquipment[playerWeapon] == 861)
		{
			setAnimation(426);
			actionTimer = 4;
			SpecDamgNPC(15);
			DDS2Damg = true;
			DDStimer = 1;
			resetAnimation();
			teleportToX = absX;
			teleportToY = absY;
			SpecDamgNPC(15);
			SpecialDelay -= 1;
		}
		else if (IsAttacking && playerEquipment[playerWeapon] == 859)
		{
			SpecDamg(15);
			DDS2Damg = true;
			DDStimer = 1;
			startAnimation(426);
			actionTimer = 4;
			teleportToX = absX;
			teleportToY = absY;
			SpecialDelay -= 1;
		}
	}

	public void maulSpec()
	{
		if (IsAttackingNPC && playerEquipment[playerWeapon] == 4153)
		{
			setAnimation(1667);
			actionTimer = 4;
			SpecDamgNPC(30);
			DDS2Damg = true;
			DDStimer = 1;
			resetAnimation();
			teleportToX = absX;
			teleportToY = absY;
			SpecDamgNPC(30);
			SpecialDelay -= 1;
		}
		else if (IsAttacking && playerEquipment[playerWeapon] == 4153)
		{
			SpecDamg(30);
			DDS2Damg = true;
			DDStimer = 1;
			startAnimation(1667);
			actionTimer = 4;
			teleportToX = absX;
			teleportToY = absY;
			SpecialDelay -= 1;
		}
	}

	public void seercull()
	{
		if (IsAttackingNPC && playerEquipment[playerWeapon] == 6724)
		{
			setAnimation(426);
			actionTimer = 4;
			SpecDamgNPC(45);
			DDS2Damg = true;
			DDStimer = 1;
			resetAnimation();
			teleportToX = absX;
			teleportToY = absY;
			SpecDamgNPC(45);
			SpecialDelay -= 1;
		}
		else if (IsAttacking && playerEquipment[playerWeapon] == 6724)
		{
			SpecDamg(50);
			DDS2Damg = true;
			DDStimer = 1;
			startAnimation(426);
			actionTimer = 4;
			teleportToX = absX;
			teleportToY = absY;
			SpecDamg(50);
			SpecialDelay -= 1;
		}
	}

	public void darkbow()
	{
		if (IsAttackingNPC && playerEquipment[playerWeapon] == 15156)
		{
			setAnimation(426);
			actionTimer = 4;
			SpecDamgNPC(55);
			DDS2Damg = true;
			DDStimer = 1;
			resetAnimation();
			teleportToX = absX;
			teleportToY = absY;
			SpecDamgNPC(55);
			SpecialDelay -= 1;
		}
		else if (IsAttacking && playerEquipment[playerWeapon] == 15156)
		{
			SpecDamg(50);
			DDS2Damg = true;
			DDStimer = 1;
			startAnimation(426);
			actionTimer = 4;
			teleportToX = absX;
			teleportToY = absY;
			SpecDamg(50);
			SpecialDelay -= 1;
		}
	}

	public void DDZ()
	{
		if (IsAttackingNPC && playerEquipment[playerWeapon] == 4212)
		{
			setAnimation(426);
			setAnimation(426);
			actionTimer = 4;
			SpecDamgNPC(42);
			DDS2Damg = true;
			DDStimer = 1;
			resetAnimation();
			teleportToX = absX;
			teleportToY = absY;
			SpecDamgNPC(30);
			SpecialDelay -= 1;
		}
		else if (IsAttacking && playerEquipment[playerWeapon] == 4212)
		{
			SpecDamg(42);
			DDS2Damg = true;
			DDStimer = 1;
			startAnimation(462);
			startAnimation(426);
			actionTimer = 4;
			teleportToX = absX;
			teleportToY = absY;
			SpecialDelay -= 1;
		}
	}

	public void SaraSwordSpec()
	{
		if (IsAttackingNPC && playerEquipment[playerWeapon] == 15351)
		{
			setAnimation(1667);
			actionTimer = 4;
			SpecDamgNPC(40);
			DDS2Damg = true;
			DDStimer = 1;
			resetAnimation();
			teleportToX = absX;
			teleportToY = absY;
			SpecDamgNPC(40);
			SpecialDelay -= 1;
		}
		else if (IsAttacking && playerEquipment[playerWeapon] == 15351)
		{
			SpecDamg(30);
			DDS2Damg = true;
			DDStimer = 1;
			startAnimation(1667);
			actionTimer = 4;
			teleportToX = absX;
			teleportToY = absY;
			SpecialDelay -= 1;
		}
	}

	public void hally()
	{
		if (IsAttackingNPC && playerEquipment[playerWeapon] == 3204)
		{
			setAnimation(440);
			actionTimer = 4;
			SpecDamgNPC(30);
			DDS2Damg = true;
			DDStimer = 1;
			resetAnimation();
			teleportToX = absX;
			teleportToY = absY;
			SpecDamgNPC(30);
			SpecialDelay -= 1;
		}
		else if (IsAttacking && playerEquipment[playerWeapon] == 3204)
		{
			SpecDamg(30);
			DDS2Damg = true;
			DDStimer = 1;
			startAnimation(440);
			actionTimer = 4;
			teleportToX = absX;
			teleportToY = absY;
			SpecialDelay -= 1;
		}
	}

	public void SaraSwordSpecial()
	{
		if (IsAttackingNPC && playerEquipment[playerWeapon] == 15351)
		{
			setAnimation(0x426);
			actionTimer = 4;
			SpecDamgNPC(100);
			DDS2Damg = true;
			DDStimer = 1;
			resetAnimation();
			teleportToX = absX;
			teleportToY = absY;
			SpecDamgNPC(31);
			SpecialDelay -= 3;
		}
		else if (IsAttacking && playerEquipment[playerWeapon] == 15351)
		{
			SpecDamg(31);
			DDS2Damg = true;
			DDStimer = 1;
			startAnimation(0x426);
			actionTimer = 4;
			teleportToX = absX;
			teleportToY = absY;
			SpecialDelay -= 3;
		}
	}

	private void SSSpecial()
	{
		if (IsAttackingNPC && playerEquipment[playerWeapon] == 14889)
		{
			setAnimation(0x426);
			actionTimer = 4;
			SpecDamgNPC(31);
			DDS2Damg = true;
			DDStimer = 1;
			resetAnimation();
			teleportToX = absX;
			teleportToY = absY;
			SpecDamgNPC(75);
			SpecialDelay -= 3;
		}
		else if (IsAttacking && playerEquipment[playerWeapon] == 14889)
		{
			SpecDamg(75);
			DDS2Damg = true;
			DDStimer = 1;
			startAnimation(0x2910);
			actionTimer = 4;
			teleportToX = absX;
			teleportToY = absY;
			SpecialDelay -= 3;
		}
	}

	private void DLSpecial()
	{
		if (IsAttackingNPC && playerEquipment[playerWeapon] == 11593)
		{
			setAnimation(2890);
			actionTimer = 4;
			SpecDamgNPC(40);
			DDS2Damg = true;
			DDStimer = 1;
			resetAnimation();
			teleportToX = absX;
			teleportToY = absY;
			SpecDamgNPC(40);
			SpecialDelay -= 3;
		}
		else if (IsAttacking && playerEquipment[playerWeapon] == 11593)
		{
			SpecDamg(40);
			DDS2Damg = true;
			DDStimer = 1;
			startAnimation(2890);
			actionTimer = 4;
			teleportToX = absX;
			teleportToY = absY;
			SpecialDelay -= 3;
		}
	}

	private void DLLSpecial()
	{
		if (IsAttackingNPC && playerEquipment[playerWeapon] == 11593)
		{
			setAnimation(0x2890);
			actionTimer = 4;
			SpecDamgNPC(40);
			DDS2Damg = true;
			DDStimer = 1;
			resetAnimation();
			teleportToX = absX;
			teleportToY = absY;
			SpecDamgNPC(40);
			SpecialDelay -= 3;
		}
		else if (IsAttacking && playerEquipment[playerWeapon] == 11593)
		{
			SpecDamg(40);
			DDS2Damg = true;
			DDStimer = 1;
			startAnimation(0x2890);
			actionTimer = 4;
			teleportToX = absX;
			teleportToY = absY;
			SpecialDelay -= 3;
		}
	}

	private void ClawsSpecial()
	{
		if (IsAttackingNPC && playerEquipment[playerWeapon] == 8002)
		{
			setAnimation(2068);
			actionTimer = 0;// 3
			SpecDamgNPC(69);
			DDS2Damg = true;
			DDStimer = 0;
			resetAnimation();
			teleportToX = absX;
			teleportToY = absY;
			SpecDamgNPC(69);
			SpecialDelay -= 0; // 3
		}
		else if (IsAttacking && playerEquipment[playerWeapon] == 8002)
		{
			SpecDamg(69);
			DDS2Damg = true;
			DDStimer = 1;
			startAnimation(2068);
			actionTimer = 0;// 4
			teleportToX = absX;
			teleportToY = absY;
			SpecialDelay -= 0;// 3
		}
	}

	private void CamsSpecial()
	{
		if (IsAttackingNPC && playerEquipment[playerWeapon] == 13643)
		{
			setAnimation(408);
			actionTimer = 0;// 3
			SpecDamgNPC(59);
			DDStimer = 0;
			resetAnimation();
			teleportToX = absX;
			teleportToY = absY;
			SpecDamgNPC(59);
			SpecialDelay -= 0; // 3
		}
		else if (IsAttacking && playerEquipment[playerWeapon] == 13643)
		{
			SpecDamg(59);
			DDStimer = 1;
			startAnimation(408);
			actionTimer = 3;// 4
			teleportToX = absX;
			teleportToY = absY;
			SpecialDelay -= 3;// 3
		}
	}

	public void DDSSpecial()
	{
		if (IsAttackingNPC && playerEquipment[playerWeapon] == 5698)
		{
			setAnimation(0x426);
			actionTimer = 4;
			SpecDamgNPC(41);
			DDS2Damg = true;
			DDStimer = 1;
			resetAnimation();
			teleportToX = absX;
			teleportToY = absY;
			SpecDamgNPC(41);
			SpecialDelay -= 3;
		}
		else if (IsAttacking && playerEquipment[playerWeapon] == 5698)
		{
			SpecDamg(31);
			DDS2Damg = true;
			DDStimer = 1;
			startAnimation(0x426);
			actionTimer = 4;
			teleportToX = absX;
			teleportToY = absY;
			SpecialDelay -= 3;
		}
	}

	public void SpecDamg(int maxDamage)
	{
		for (Player p : PlayerHandler.players)
		{
			if (p != null)
			{
				if (PlayerHandler.players[AttackingOn].IsDead == false)
				{
					int damage = MethodCollection.random(maxDamage);
					if (PlayerHandler.players[AttackingOn].playerLevel[3]
							- hitDiff < 0)
					{
						damage = PlayerHandler.players[AttackingOn].playerLevel[3];
					}
					PlayerHandler.players[AttackingOn].hitDiff = damage;
					PlayerHandler.players[AttackingOn].updateRequired = true;
					PlayerHandler.players[AttackingOn].hitUpdateRequired = true;
				}
			}
		}
	}

	public void SpecDamgNPC(int maxDamage)
	{
		if (NPCHandler.npcs[attacknpc] != null)
		{
			if (NPCHandler.npcs[attacknpc].IsDead == false)
			{
				int damage = MethodCollection.random(maxDamage);
				if (NPCHandler.npcs[attacknpc].HP - hitDiff < 0)
				{
					damage = NPCHandler.npcs[attacknpc].HP;
				}
				NPCHandler.npcs[attacknpc].StartKilling = playerId;
				NPCHandler.npcs[attacknpc].RandomWalk = false;
				NPCHandler.npcs[attacknpc].IsUnderAttack = true;
				NPCHandler.npcs[attacknpc].hitDiff = damage;
				NPCHandler.npcs[attacknpc].updateRequired = true;
				NPCHandler.npcs[attacknpc].hitUpdateRequired = true;
			}
		}
	}

	public boolean DDS = false;

	public void animation(int id, int Y, int X)
	{
		outStream.createFrame(85);
		outStream.putByteC(Y - (mapRegionY * 8));
		outStream.putByteC(X - (mapRegionX * 8));
		outStream.createFrame(4);
		outStream.put(0);
		outStream.putShort(id);
		outStream.put(0);
		outStream.putShort(0);
	}

	public void ProjectileSpec(int startID, int casterY, int casterX,
			int offsetY, int offsetX, int index, int enemyY, int enemyX)
	{
		{
			try
			{
				GraphicsHandler.createSpec(startID, casterY, casterX, offsetY,
						offsetX, 50, 95, 43, 31, enemyY, enemyX, index + 1);
			}
			catch (Exception E)
			{
			}
		}
	}

	public boolean specspell(int castID, int casterY, int casterX, int offsetY,
			int offsetX, int angle, int speed, int startHeight, int endHeight,
			int enemyY, int enemyX, int Lockon)
	{
		try
		{
			fcastid = castID;
			fcasterY = casterY;
			fcasterX = casterX;
			foffsetY = offsetY;
			foffsetX = offsetX;
			fangle = angle;
			fspeed = speed;
			fsh = startHeight;
			feh = endHeight;
			fenemyY = enemyY;
			fenemyX = enemyX;
			fLockon = Lockon;
			actionTimer = 0;
			// Casts Spell In Hands
			if (cast == false)
			{
				createProjectile(casterY, casterX, offsetY, offsetX, angle,
						speed, castID, startHeight, endHeight, Lockon);
				cast = true;
				firingspell = true;
			}
			// Fires Projectile
			if (cast == true && fired == false)
			{
				firingspell = false;
				cast = false;
				fired = false;
			}
		}
		catch (Exception E)
		{
		}
		return true;
	}

	public void attdam(int maxDamage, int range)
	{
		for (Player p : PlayerHandler.players)
		{
			if (p != null)
			{
				Client person = (Client) p;
				if ((person.username != null || person.username != "null"))
				{
					if (person.distanceToPoint(absX, absY) <= range
							&& person.playerId != playerId)
					{
						int damage = MethodCollection.random(maxDamage);
						if (person.playerLevel[3] - hitDiff < 0)
						{
							damage = person.playerLevel[3];
						}
						person.hitDiff = damage;
						person.KillerId = playerId;
						person.updateRequired = true;
						person.hitUpdateRequired = true;
					}
				}
			}
		}
	}

	public void stillgfx4(int id, int Y, int X)
	{
		outStream.createFrame(85);
		outStream.putByteC(Y - (mapRegionY * 8));
		outStream.putByteC(X - (mapRegionX * 8));
		outStream.createFrame(4);
		outStream.put(0); // Tiles away (X >> 4 + Y & 7)
		outStream.putShort(id); // Graphic id
		outStream.put(80); // height of the spell above it's basic place,
							// i think it's written in pixels 100 pixels
							// higher
		outStream.putShort(0); // Time before casting the graphic
	}

	public void runecraft(int requiredlvl, int expadd, int itemtoadd, int x2,
			int x3, int x4, int x5, int x6)
	{
		int essamount = 0;
		if (playerHasItem(1436))
		{
			if (playerLevel[20] >= requiredlvl)
			{
				if (playerLevel[20] >= 0 && playerLevel[20] < x2)
				{
					essamount = amountOfItem(1436);
				}
				if (playerLevel[20] >= x2 && playerLevel[20] < x3)
				{
					essamount = amountOfItem(1436) * 2;
				}
				if (playerLevel[20] >= x3 && playerLevel[20] < x4)
				{
					essamount = amountOfItem(1436) * 3;
				}
				if (playerLevel[20] >= x4 && playerLevel[20] < x5)
				{
					essamount = amountOfItem(1436) * 4;
				}
				if (playerLevel[20] >= x5)
				{
					essamount = amountOfItem(1436) * 5;
				}
				for (int i = 0; i < 29; i++)
				{
					deleteItem(1436, getItemSlot(1436), i);
				}
				addSkillXP(expadd, 20);
				addItem(itemtoadd, essamount);
				sendMessage("You Craft " + essamount + " "
						+ getItemName(itemtoadd) + "!");
				staticAnimation(186, absX, absY, 100);
				setAnimation(791);
			}
			else if (playerLevel[20] <= requiredlvl - 1)
			{
				sendMessage("You Need " + requiredlvl
						+ " Runecrafting To Make " + getItemName(itemtoadd)
						+ "!");
			}
		}
		else if (!playerHasItem(1436))
		{
			sendMessage("You Need Some Rune Essence To Craft Runes!");
		}
	}

	public void DragonLongSpecial()
	{
		hitDiff = 20 + playerMaxHit;
		inCombat();
		staticAnimation(248, absX, absY, 100);
		setAnimation(1058);
		actionTimer = 25;
		teleportToX = absX;
		teleportToY = absY;
	}

	public void updateCharAppearance(int[] styles, int[] colors)
	{
		for (int j = 0; j < 7; j++)
		{
			if (styles[j] > 0)
			{
				styles[j] += 0x100;
			}
		}
	}

	public int distanceTo(Player other)
	{
		return (int) Math.sqrt(Math.pow(absX - other.absX, 2)
				+ Math.pow(absY - other.absY, 2));
	}

	public int distanceToPoint(int pointX, int pointY)
	{
		return (int) Math.sqrt(Math.pow(absX - pointX, 2)
				+ Math.pow(absY - pointY, 2));
	}

	public int getItemSlot(int itemID)
	{
		for (int slot = 0; slot < playerItems.length; slot++)
		{
			if (playerItems[slot] == (itemID + 1))
			{
				return slot;
			}
		}
		return -1;
	}

	public void setconfig(int settingID, int value)
	{
		outStream.createFrame(36);
		outStream.putShortBE(settingID);
		outStream.put(value);
	}

	public void viewTo(int coordX, int coordY)
	{
		viewToX = ((2 * coordX) + 1);
		viewToY = ((2 * coordY) + 1);
		dirUpdate2Required = true;
		updateRequired = true;
	}

	public boolean funpk()
	{
		if ((absX >= 3240 && absX <= 3256 && absY >= 9353 && absY <= 9377))
			return true;
		else
			return false;
	}

	public boolean nonWild()
	{
		if ((absX >= 3249 && absX <= 3258 && absY >= 3438 && absY <= 3431)
				|| (absX >= 2854 && absX <= 2861 && absY >= 3420 && absY <= 3450)
				|| (absX >= 2620 && absX <= 2640 && absY >= 4670 && absY <= 4700)
				|| (absX >= 2940 && absX <= 3005 && absY >= 9450 && absY <= 9550)
				|| (absX >= 1890 && absX <= 1893 && absY >= 5353 && absY <= 5358)
				|| (absX >= 2361 && absX <= 2380 && absY >= 4930 && absY <= 4980)
				|| (absX >= 2580 && absX <= 2640 && absY >= 4750 && absY <= 4790)
				|| (absX >= 2708 && absX <= 2728 && absY >= 9801 && absY <= 9829)
				|| (absX >= 3241 && absX <= 3255 && absY >= 9361 && absY <= 9375)
				|| (absX >= 2770 && absX <= 2803 && absY >= 9316 && absY <= 9345)
				|| (absX >= 2860 && absX <= 2930 && absY >= 9650 && absY <= 9750)
				|| (absX >= 2744 && absX <= 2773 && absY >= 3483 && absY <= 3492)
				|| (absX >= 3098 && absX <= 3101 && absY >= 3596 && absY <= 3599)
				|| (absX >= 3073 && absX <= 3086 && absY >= 9879 && absY <= 9898)
				|| (absX >= 3653 && absX <= 3670 && absY >= 3486 && absY <= 3507)
				|| (absX >= 2630 && absX <= 2700 && absY >= 9530 && absY <= 9590)
				|| (absX >= 2816 && absX <= 2874 && absY >= 9822 && absY <= 9854)
				|| (absX >= 2086 && absX <= 2105 && absY >= 4419 && absY <= 4436)
				|| (absX >= 2474 && absX <= 2478 && absY >= 9822 && absY <= 9854)
				|| (absX >= 3175 && absX <= 3180 && absY >= 9753 && absY <= 9763)
				|| (absX >= 2435 && absX <= 3156 && absY >= 2445 && absY <= 3166)
				|| (absX >= 3096 && absX <= 3100 && absY >= 3683 && absY <= 3696)
				|| (absX >= 3467 && absX <= 3515 && absY >= 3462 && absY <= 3510)
				|| (absX >= 2959 && absX <= 9478 && absY >= 9475 && absY <= 9481)
				|| (absX >= 3249 && absX <= 3259 && absY >= 3471 && absY <= 3488)
				|| (absX >= 2401 && absX <= 2405 && absY >= 3098 && absY <= 3102)
				|| (absX >= 2820 && absX <= 2876 && absY >= 2950 && absY <= 3004)
				|| (absX >= 2667 && absX <= 2684 && absY >= 3717 && absY <= 3732)
				|| (absX >= 3089 && absX <= 3092 && absY >= 3933 && absY <= 3934)
				|| (absX >= 3033 && absX <= 3036 && absY >= 4842 && absY <= 4844)
				|| (absX >= 2687 && absX <= 2677 && absY >= 3720 && absY <= 3733)
				|| (absX >= 3229 && absX <= 3242 && absY >= 9796 && absY <= 9807)
				|| (absX >= 2442 && absX <= 2487 && absY >= 4808 && absY <= 4855)
				|| (absX >= 2581 && absX <= 2589 && absY >= 4834 && absY <= 4842)
				|| (absX >= 2712 && absX <= 2720 && absY >= 4832 && absY <= 4840)
				|| (absX >= 2654 && absX <= 2662 && absY >= 4837 && absY <= 4845)
				|| (absX >= 2837 && absX <= 2851 && absY >= 4826 && absY <= 4842)
				|| (absX >= 2859 && absX <= 2868 && absY >= 9881 && absY <= 9883)
				|| (absX >= 2880 && absX <= 2943 && absY >= 9884 && absY <= 9923)
				|| (absX >= 3039 && absX <= 3111 && absY >= 3475 && absY <= 3523)
				|| (absX >= 2790 && absX <= 2855 && absY >= 3418 && absY <= 3466)
				|| (absX >= 2584 && absX <= 2612 && absY >= 3153 && absY <= 3169)
				|| (absX >= 2935 && absX <= 3066 && absY >= 2935 && absY <= 3399)
				|| (absX >= 3249 && absX <= 3260 && absY >= 3435 && absY <= 3437)
				|| (absX >= 3249 && absX <= 3253 && absY >= 3431 && absY <= 3437)
				|| (absX >= 3250 && absX <= 3257 && absY >= 3419 && absY <= 3423)
				|| (absX >= 3250 && absX <= 3257 && absY >= 3419 && absY <= 3423)
				|| (absX >= 2747 && absX <= 2758 && absY >= 2794 && absY <= 2802)
				|| (absX >= 2764 && absX <= 2776 && absY >= 2793 && absY <= 2802)
				|| (absX >= 2773 && absX <= 2780 && absY >= 2766 && absY <= 2770)
				|| (absX >= 2751 && absX <= 2770 && absY >= 2764 && absY <= 2777)
				|| (absX >= 2785 && absX <= 2809 && absY >= 2771 && absY <= 2801)
				|| (absX >= 2732 && absX <= 2740 && absY >= 2789 && absY <= 2796)
				|| (absX >= 2861 && absX <= 2872 && absY >= 1018 && absY <= 10212)
				|| (absX >= 2490 && absX <= 2631 && absY >= 3836 && absY <= 3904)
				|| (absX >= 2393 && absX <= 2496 && absY >= 5169 && absY <= 5180)
				|| (absX >= 2529 && absX <= 2547 && absY >= 4711 && absY <= 4723)
				|| (absX >= 2883 && absX <= 2902 && absY >= 3501 && absY <= 3518)
				|| (absX >= 3409 && absX <= 3452 && absY >= 3532 && absY <= 3575)
				|| (absX >= 2435 && absX <= 2447 && absY >= 3080 && absY <= 3099)
				|| (absX >= 3267 && absX <= 3332 && absY >= 3149 && absY <= 3270)
				|| (absX >= 3151 && absX <= 3182 && absY >= 3220 && absY <= 3272)
				|| (absX >= 3032 && absX <= 3063 && absY >= 3424 && absY <= 3457)
				|| (absX >= 3476 && absX <= 3501 && absY >= 9483 && absY <= 9505)
				|| (absX >= 2942 && absX <= 2992 && absY >= 3390 && absY <= 3414)
				|| (absX >= 2420 && absX <= 2431 && absY >= 3072 && absY <= 3083)
				|| (absX >= 3071 && absX <= 3122 && absY >= 3456 && absY <= 3523)
				|| (absX >= 3101 && absX <= 3124 && absY >= 9825 && absY <= 9849)
				|| (absX >= 2585 && absX <= 2605 && absY >= 3153 && absY <= 3169)
				|| (absX >= 2853 && absX <= 2926 && absY >= 3530 && absY <= 3577)
				|| (absX >= 2579 && absX <= 2622 && absY >= 3841 && absY <= 3902)
				|| (absX >= 2887 && absX <= 2699 && absY >= 2939 && absY <= 2741)
				|| (absX >= 2742 && absX <= 2815 && absY >= 3146 && absY <= 3235)
				|| (absX >= 2692 && absX <= 2810 && absY >= 2690 && absY <= 2808)
				|| (absX >= 2437 && absX <= 2446 && absY >= 3082 && absY <= 3098)
				|| (absX >= 2805 && absX <= 2878 && absY >= 3222 && absY <= 3313)
				|| (absX >= 2394 && absX <= 2398 && absY >= 3106 && absY <= 3109)
				|| (absX >= 3437 && absX <= 3430 && absY >= 9772 && absY <= 9777)
				|| (absX >= 3430 && absX <= 3437 && absY >= 9772 && absY <= 9777)
				|| (absX >= 3231 && absX <= 3257 && absY >= 3507 && absY <= 3520)
				|| (absX >= 2722 && absX <= 2755 && absY >= 3455 && absY <= 3481)
				|| (absX >= 2371 && absX <= 2387 && absY >= 3416 && absY <= 3447)
				|| (absX >= 3544 && absX <= 3583 && absY >= 3263 && absY <= 3316)
				|| (absX >= 3221 && absX <= 3244 && absY >= 9306 && absY <= 9327)
				|| (absX >= 2746 && absX <= 2770 && absY >= 3490 && absY <= 3517)
				|| (absX >= 3201 && absX <= 3230 && absY >= 2774 && absY <= 2814)
				|| (absX >= 2487 && absX <= 2556 && absY >= 2997 && absY <= 3051)
				|| (absX >= 2856 && absX <= 2882 && absY >= 9932 && absY <= 9965)
				|| (absX >= 3267 && absX <= 3286 && absY >= 2763 && absY <= 2778)
				|| (absX >= 2836 && absX <= 2887 && absY >= 3558 && absY <= 3611)
				|| (absX >= 3483 && absX <= 3525 && absY >= 9662 && absY <= 9702)
				|| (absX >= 2724 && absX <= 2750 && absY >= 9477 && absY <= 9498)
				|| (absX >= 3303 && absX <= 3308 && absY >= 9370 && absY <= 9380)
				|| (absX >= 2751 && absX <= 2765 && absY >= 3492 && absY <= 3504)
				|| (absX >= 2748 && absX <= 3416 && absY >= 2757 && absY <= 3423)
				|| (absX >= 3017 && absX <= 9732 && absY >= 3039 && absY <= 3039)
				|| (absX >= 3179 && absX <= 3181 && absY >= 9627 && absY <= 9633)
				|| (absX >= 2740 && absX <= 3181 && absY >= 3437 && absY <= 3452)
				|| (absX >= 2742 && absX <= 2745 && absY >= 3436 && absY <= 3436)
				|| (absX >= 2741 && absX <= 2472 && absY >= 3453 && absY <= 3453)
				|| (absX >= 2378 && absX <= 2392 && absY >= 3037 && absY <= 3049)
				|| (absX >= 2378 && absX <= 2391 && absY >= 3050 && absY <= 3057)
				|| (absX >= 2265 && absX <= 2273 && absY >= 4691 && absY <= 4698)
				|| (absX >= 2475 && absX <= 2483 && absY >= 5168 && absY <= 5165)
				|| (absX >= 2483 && absX <= 2474 && absY >= 5169 && absY <= 5164)
				|| (absX >= 2443 && absX <= 2600 && absY >= 5123 && absY <= 5226)
				|| (absX >= 2001 && absX <= 2014 && absY >= 4433 && absY <= 4447)
				|| heightLevel == 1)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public int untradable[] = { 6384, 1637 };

	public boolean isUntradable(int item)
	{
		for (int i = 0; i < untradable.length; i++)
		{
			if (untradable[i] == item)
			{
				return true;
			}
		}
		return false;
	}

	public boolean cannonowner()
	{
		if (username == cannonowner)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public boolean hasAxe()
	{
		if (playerHasItem2(6739) || playerHasItem2(1351)
				|| playerHasItem2(1349) || playerHasItem2(1353)
				|| playerHasItem2(1355) || playerHasItem2(1357)
				|| playerHasItem2(1359) || playerHasItem2(1361))
		{
			return true;
		}
		return false;
	}

	public boolean hasPick()
	{
		if (playerHasItem2(1275) || playerHasItem2(1271)
				|| playerHasItem2(1273) || playerHasItem2(1269)
				|| playerHasItem2(1267) || playerHasItem2(1265))
		{
			return true;
		}
		return false;
	}

	public int GetGroundItemID(int ItemID, int itemX, int itemY)
	{
		for (int i = 0; i < 9999; i++)
		{
			if (ItemHandler.globalItemID[i] > -1)
			{
				if (ItemHandler.globalItemID[i] == ItemID
						&& ItemHandler.globalItemX[i] == itemX
						&& ItemHandler.globalItemY[i] == itemY)
				{
					return i;
				}
			}
		}
		return -1;
	}

	public void restorePot()
	{
		playerLevel[0] = getLevelForXP(playerXP[0]);
		sendFrame126("@whi@" + playerLevel[0] + "", 4004);
		playerLevel[1] = getLevelForXP(playerXP[1]);
		sendFrame126("@whi@" + playerLevel[1] + "", 4008);
		playerLevel[2] = getLevelForXP(playerXP[2]);
		sendFrame126("@whi@" + playerLevel[2] + "", 4006);
		playerLevel[4] = getLevelForXP(playerXP[4]);
		sendFrame126("@whi@" + playerLevel[4] + "", 4010);
		playerLevel[6] = getLevelForXP(playerXP[6]);
		sendFrame126("@whi@" + playerLevel[6] + "", 4014);
		playerLevel[7] = getLevelForXP(playerXP[7]);
		sendFrame126("@whi@" + playerLevel[7] + "", 4034);
		playerLevel[8] = getLevelForXP(playerXP[8]);
		sendFrame126("@whi@" + playerLevel[8] + "", 4038);
		playerLevel[9] = getLevelForXP(playerXP[9]);
		sendFrame126("@whi@" + playerLevel[9] + "", 4026);
		playerLevel[10] = getLevelForXP(playerXP[10]);
		sendFrame126("@whi@" + playerLevel[10] + "", 4032);
		playerLevel[11] = getLevelForXP(playerXP[11]);
		sendFrame126("@whi@" + playerLevel[11] + "", 4036);
		playerLevel[12] = getLevelForXP(playerXP[12]);
		sendFrame126("@whi@" + playerLevel[12] + "", 4024);
		playerLevel[13] = getLevelForXP(playerXP[13]);
		sendFrame126("@whi@" + playerLevel[13] + "", 4030);
		playerLevel[14] = getLevelForXP(playerXP[14]);
		sendFrame126("@whi@" + playerLevel[14] + "", 4028);
		playerLevel[15] = getLevelForXP(playerXP[15]);
		sendFrame126("@whi@" + playerLevel[15] + "", 4020);
		playerLevel[16] = getLevelForXP(playerXP[16]);
		sendFrame126("@whi@" + playerLevel[16] + "", 4018);
		playerLevel[17] = getLevelForXP(playerXP[17]);
		sendFrame126("@whi@" + playerLevel[17] + "", 4022);
		playerLevel[20] = getLevelForXP(playerXP[20]);
		sendFrame126("@whi@" + playerLevel[20] + "", 4152);
		if (superRestore == true)
		{
			playerLevel[5] = getLevelForXP(playerXP[5]);
			sendFrame126("@whi@" + playerLevel[5] + "", 4012);
			superRestore = false;
		}
	}

	public void capeEmote()
	{
		if (playerEquipment[playerCape] == 14095)
		{ // hitpoints
			stillgfx(433, absY, absX);
			stillgfx(351, absY, absX);
			stillgfx(352, absY, absX);
			stillgfx(466, absY, absX);
			setAnimation(838);
			txt4 = "You Do The HitPoints Emote!";
			playerLevel[3] = getLevelForXP(playerXP[3]);
			playerLevel[3] += 1;
			sendFrame126("" + playerLevel[3] + "", 4016);
			updateRequired = true;
			appearanceUpdateRequired = true;
			string4UpdateRequired = true;
		}
		if (playerEquipment[playerWeapon] == 14139)
		{ // Quest
			stillgfx(477, absY, absX);
			setAnimation(818);
			txt4 = "You Do The Quest Cape Emote!";
			playerLevel[3] = getLevelForXP(playerXP[3]);
			playerLevel[3] += 1;
			sendFrame126("" + playerLevel[3] + "", 4016);
			updateRequired = true;
			appearanceUpdateRequired = true;
			string4UpdateRequired = true;
		}
		if (playerEquipment[playerCape] == 14073)
		{ // attack
			stillgfx(346, absY, absX);
			stillgfx(427, absY, absX);
			stillgfx(83, absY, absX);
			setAnimation(2890);
			txt4 = "You Do The Attack Skill Cape Emote!";
			playerLevel[0] = getLevelForXP(playerXP[0]);
			playerLevel[0] += 1;
			sendFrame126("" + playerLevel[0] + "", 4016);
			updateRequired = true;
			appearanceUpdateRequired = true;
			string4UpdateRequired = true;
		}
		if (playerEquipment[playerCape] == 14074)
		{ // attack
			stillgfx(346, absY, absX);
			stillgfx(427, absY, absX);
			stillgfx(83, absY, absX);
			setAnimation(2890);
			txt4 = "You Do The Attack Skill Cape Emote!";
			playerLevel[0] = getLevelForXP(playerXP[0]);
			playerLevel[0] += 1;
			sendFrame126("" + playerLevel[0] + "", 4016);
			updateRequired = true;
			appearanceUpdateRequired = true;
			string4UpdateRequired = true;
		}
		if (playerEquipment[playerCape] == 14076)
		{ // strength
			stillgfx(406, absY, absX);
			stillgfx(327, absY, absX);
			stillgfx(436, absY, absX);
			setAnimation(750);
			txt4 = "You Do The Strength Skill Cape Emote!";
			playerLevel[2] = getLevelForXP(playerXP[2]);
			playerLevel[2] += 1;
			sendFrame126("" + playerLevel[2] + "", 4016);
			updateRequired = true;
			appearanceUpdateRequired = true;
			string4UpdateRequired = true;
		}
		if (playerEquipment[playerCape] == 14740)
		{ // Quest
			stillgfx(524, absY, absX);
			stillgfx(395, absY, absX);
			stillgfx(337, absY, absX);
			txt4 = "A Quest Point Skill Cape!";
			string4UpdateRequired = true;
		}
		if (playerEquipment[playerCape] == 15714)
		{ // Summon
			stillgfx(179, absY, absX);
			stillgfx(440, absY, absX);
			stillgfx(575, absY, absX);
			txt4 = "You Do The Summoning Skill Cape Emote!";
			string4UpdateRequired = true;
		}
		if (playerEquipment[playerCape] == 14089)
		{ // Magic
			stillgfx(389, absY, absX);
			stillgfx(368, absY, absX);
			setAnimation(1978);
			txt4 = "You Do The Magic Skill Cape Emote!";
			playerLevel[2] = getLevelForXP(playerXP[2]);
			playerLevel[2] += 1;
			sendFrame126("" + playerLevel[2] + "", 4016);
			updateRequired = true;
			appearanceUpdateRequired = true;
			string4UpdateRequired = true;
		}
		if (playerEquipment[playerCape] == 14077)
		{ // strength
			stillgfx(406, absY, absX);
			stillgfx(327, absY, absX);
			stillgfx(436, absY, absX);
			setAnimation(750);
			txt4 = "You Do The Strength Skill Cape Emote!";
			playerLevel[2] = getLevelForXP(playerXP[2]);
			playerLevel[2] += 1;
			sendFrame126("" + playerLevel[2] + "", 4016);
			updateRequired = true;
			appearanceUpdateRequired = true;
			string4UpdateRequired = true;
		}
		if (playerEquipment[playerCape] == 14079)
		{ // defence
			stillgfx(8, absY, absX);
			stillgfx(57, absY, absX);
			stillgfx(240, absY, absX);
			setAnimation(2720);
			txt4 = "You Do The Defence Skill Cape Emote!";
			playerLevel[1] = getLevelForXP(playerXP[1]);
			playerLevel[1] += 1;
			sendFrame126("" + playerLevel[1] + "", 4016);
			updateRequired = true;
			appearanceUpdateRequired = true;
			string4UpdateRequired = true;
		}
		if (playerEquipment[playerCape] == 14080)
		{ // defence
			stillgfx(8, absY, absX);
			stillgfx(57, absY, absX);
			stillgfx(240, absY, absX);
			setAnimation(2720);
			txt4 = "You Do The Defence Skill Cape Emote!";
			playerLevel[1] = getLevelForXP(playerXP[1]);
			playerLevel[1] += 1;
			sendFrame126("" + playerLevel[1] + "", 4016);
			updateRequired = true;
			appearanceUpdateRequired = true;
			string4UpdateRequired = true;
		}
		if (playerEquipment[playerCape] == 14082)
		{ // ranging
			stillgfx(472, absY, absX);
			stillgfx(474, absY, absX);
			stillgfx(20, absY, absX);
			stillgfx(21, absY, absX);
			stillgfx(22, absY, absX);
			stillgfx(23, absY, absX);
			stillgfx(24, absY, absX);
			stillgfx(25, absY, absX);
			stillgfx(26, absY, absX);
			txt4 = "You Do The Range Skill Cape Emote!";
			string4UpdateRequired = true;
		}
		if (playerEquipment[playerCape] == 14083)
		{ // ranging 2
			stillgfx(472, absY, absX);
			stillgfx(474, absY, absX);
			stillgfx(20, absY, absX);
			stillgfx(21, absY, absX);
			stillgfx(22, absY, absX);
			stillgfx(23, absY, absX);
			stillgfx(24, absY, absX);
			stillgfx(25, absY, absX);
			stillgfx(26, absY, absX);
			txt4 = "You Do The Range Skill Cape Emote!";
			string4UpdateRequired = true;
		}
		if (playerEquipment[playerCape] == 14085)
		{ // prayer
			stillgfx(84, absY, absX);
			stillgfx(263, absY, absX);
			stillgfx(264, absY, absX);
			stillgfx(426, absY, absX);
			txt4 = "You Do The Prayer Skill Cape Emote!";
			string4UpdateRequired = true;
		}
		if (playerEquipment[playerCape] == 14086)
		{ // prayer 2
			stillgfx(84, absY, absX);
			stillgfx(263, absY, absX);
			stillgfx(264, absY, absX);
			stillgfx(426, absY, absX);
			txt4 = "You Do The Prayer Skill Cape Emote!";
			string4UpdateRequired = true;
		}
		if (playerEquipment[playerCape] == 14127)
		{ // cooking
			stillgfx(563, absY + 1, absX);
			pEmote = 897;
			txt4 = "You Do The Cooking Skill Cape Emote!";
			string4UpdateRequired = true;
		}
		if (playerEquipment[playerCape] == 14128)
		{ // cooking 2
			stillgfx(563, absY + 1, absX);
			setAnimation(897);
			txt4 = "You Do The Cooking Skill Cape Emote!";
			string4UpdateRequired = true;
		}
		if (playerEquipment[playerCape] == 14133)
		{ // woodcut
			stillgfx(187, absY, absX);
			txt4 = "You Do The WoodCutting Skill Cape Emote!";
			string4UpdateRequired = true;
		}
		if (playerEquipment[playerCape] == 14134)
		{ // woodcut 2
			stillgfx(187, absY, absX);
			txt4 = "You Do The WoodCutting Skill Cape Emote!";
			string4UpdateRequired = true;
		}
		if (playerEquipment[playerCape] == 14109)
		{ // fletch
			stillgfx(588, absY, absX);
			txt4 = "You Do The Fletching Skill Cape Emote!";
			string4UpdateRequired = true;
		}
		if (playerEquipment[playerCape] == 14110)
		{ // fletch 2
			stillgfx(588, absY, absX);
			txt4 = "You Do The Fletching Skill Cape Emote!";
			string4UpdateRequired = true;
		}
		if (playerEquipment[playerCape] == 14124)
		{ // fish
			stillgfx(68, absY, absX);
			txt4 = "You Do The Fishing Skill Cape Emote!";
			string4UpdateRequired = true;
		}
		if (playerEquipment[playerCape] == 14125)
		{ // fish 2
			stillgfx(68, absY, absX);
			txt4 = "You Do The Fishing Skill Cape Emote!";
			string4UpdateRequired = true;
		}
		if (playerEquipment[playerCape] == 14130)
		{ // firemaking
			stillgfx(453, absY, absX);
			stillgfx(446, absY, absX);
			txt4 = "You Do The FireMaking Skill Cape Emote!";
			string4UpdateRequired = true;
		}
		if (playerEquipment[playerCape] == 14131)
		{ // firemaking 2
			stillgfx(453, absY, absX);
			stillgfx(446, absY, absX);
			txt4 = "You Do The FireMaking Skill Cape Emote!";
			string4UpdateRequired = true;
		}
		if (playerEquipment[playerCape] == 14106)
		{ // crafting
			stillgfx(239, absY, absX);
			txt4 = "You Do The Crafting Skill Cape Emote!";
			string4UpdateRequired = true;
		}
		if (playerEquipment[playerCape] == 14107)
		{ // crafting 2
			stillgfx(239, absY, absX);
			txt4 = "You Do The Crafting Skill Cape Emote!";
			string4UpdateRequired = true;
		}
		if (playerEquipment[playerCape] == 14121)
		{ // smithing
			stillgfx(436, absY, absX);
			txt4 = "You Do The Smithing Skill Cape Emote!";
			string4UpdateRequired = true;
		}
		if (playerEquipment[playerCape] == 14122)
		{ // smithing 2
			stillgfx(436, absY, absX);
			txt4 = "You Do The Smithing Skill Cape Emote!";
			string4UpdateRequired = true;
		}
		if (playerEquipment[playerCape] == 14118)
		{ // mining
			stillgfx(287, absY, absX);
			txt4 = "You Do The Mining Skill Cape Emote!";
			string4UpdateRequired = true;
		}
		if (playerEquipment[playerCape] == 14119)
		{ // mining 2
			stillgfx(287, absY, absX);
			txt4 = "You Do The Mining Skill Cape Emote!";
			string4UpdateRequired = true;
		}
		if (playerEquipment[playerCape] == 14100)
		{ // herblore
			stillgfx(267, absY, absX);
			stillgfx(255, absY, absX);
			stillgfx(259, absY, absX);
			stillgfx(352, absY, absX);
			txt4 = "You Do The Herblore Skill Cape Emote!";
			string4UpdateRequired = true;
		}
		if (playerEquipment[playerCape] == 14101)
		{ // herblore 2
			stillgfx(267, absY, absX);
			stillgfx(255, absY, absX);
			stillgfx(259, absY, absX);
			stillgfx(352, absY, absX);
			txt4 = "You Do The Herblore Skill Cape Emote!";
			string4UpdateRequired = true;
		}
		if (playerEquipment[playerCape] == 14097)
		{ // agility
			stillgfx(422, absY, absX);
			stillgfx(60, absY, absX);
			stillgfx(62, absY, absX);
			txt4 = "You Do The Agility Skill Cape Emote!";
			string4UpdateRequired = true;
		}
		if (playerEquipment[playerCape] == 14098)
		{ // agility 2
			stillgfx(422, absY, absX);
			stillgfx(60, absY, absX);
			stillgfx(62, absY, absX);
			txt4 = "You Do The Agility Skill Cape Emote!";
			string4UpdateRequired = true;
		}
		if (playerEquipment[playerCape] == 14103)
		{ // thieving
			stillgfx(421, absY, absX);
			stillgfx(143, absY, absX);
			txt4 = "You Do The Thieving Skill Cape Emote!";
			string4UpdateRequired = true;
		}
		if (playerEquipment[playerCape] == 14104)
		{ // thieving 2
			stillgfx(421, absY, absX);
			stillgfx(143, absY, absX);
			txt4 = "You Do The Thieving Skill Cape Emote!";
			string4UpdateRequired = true;
		}
		if (playerEquipment[playerCape] == 14112)
		{ // slayer
			stillgfx(466, absY, absX);
			stillgfx(468, absY, absX);
			txt4 = "You Do The Slayer Skill Cape Emote! ";
			string4UpdateRequired = true;
		}
		if (playerEquipment[playerCape] == 14113)
		{ // slayer 2
			stillgfx(466, absY, absX);
			stillgfx(468, absY, absX);
			txt4 = "You Do The Slayer Skill Cape Emote!";
			string4UpdateRequired = true;
		}
		if (playerEquipment[playerCape] == 14136)
		{ // farming
			stillgfx(568, absY, absX);
			stillgfx(569, absY, absX);
			stillgfx(593, absY, absX);
			txt4 = "You Do The Farming Skill Cape Emote!";
			string4UpdateRequired = true;
		}
		if (playerEquipment[playerCape] == 14137)
		{ // farming 2
			stillgfx(568, absY, absX);
			stillgfx(569, absY, absX);
			stillgfx(593, absY, absX);
			txt4 = "You Do The Farming Skill Cape Emote!";
			string4UpdateRequired = true;
		}
		if (playerEquipment[playerCape] == 14091)
		{ // runecrafting
			stillgfx(111, absY, absX);
			stillgfx(185, absY, absX);
			stillgfx(186, absY, absX);
			txt4 = "You Do The RuneCrafting Skill Cape Emote!";
			string4UpdateRequired = true;
		}
		if (playerEquipment[playerCape] == 14092)
		{ // runecrafting 2
			stillgfx(111, absY, absX);
			stillgfx(185, absY, absX);
			stillgfx(186, absY, absX);
			txt4 = "You Do The RuneCrafting Skill Cape Emote!";
			string4UpdateRequired = true;
		}
		if (playerEquipment[playerCape] == 14234)
		{ // Hunting
			stillgfx(179, absY, absX);
			stillgfx(440, absY, absX);
			stillgfx(575, absY, absX);
			txt4 = "You Do The Hunter Skill Cape Emote!";
			string4UpdateRequired = true;
		}
		if (playerEquipment[playerCape] == 14235)
		{ // Hunting 2
			stillgfx(179, absY, absX);
			stillgfx(440, absY, absX);
			stillgfx(575, absY, absX);
			txt4 = "You Do The Hunter Skill Cape Emote!";
			string4UpdateRequired = true;
		}
	}

	public int GetObject(int X, int Y, int ObjectID)
	{
		for (int i = 0; i < ObjectHandler.MaxObjects; i++)
		{
			if (ObjectHandler.ObjectID[i] > -1)
			{
				if (X == ObjectHandler.ObjectX[i]
						&& Y == ObjectHandler.ObjectY[i])
				{
					if (ObjectID != -1)
					{
						if (ObjectID == ObjectHandler.ObjectID[i])
						{
							return i;
						}
					}
					else
					{
						return i;
					}
				}
			}
		}
		return -1;
	}

	public void ChangeDoor(int ArrayID)
	{
		int objectID = ObjectHandler.ObjectOriID[ArrayID];
		int objectX = ObjectHandler.ObjectX[ArrayID];
		int objectY = ObjectHandler.ObjectY[ArrayID];
		int Face = ObjectHandler.ObjectFace[ArrayID];
		int Type = ObjectHandler.ObjectType[ArrayID];
		ReplaceObject2(objectX, objectY, -1, -1, 0);
		switch (Type)
		{
		case 1:
			ReplaceObject2(objectX, (objectY + 1), objectID, Face, 0);
			ObjectHandler.ObjectType[ArrayID] = 2;
			break;
		case 2:
			ReplaceObject2(objectX, (objectY - 1), objectID, Face, 0);
			ObjectHandler.ObjectType[ArrayID] = 1;
			break;
		case 3:
			ReplaceObject2((objectX + 1), objectY, objectID, Face, -2);
			ObjectHandler.ObjectType[ArrayID] = 4;
			break;
		case 4:
			ReplaceObject2((objectX - 1), objectY, objectID, Face, -2);
			ObjectHandler.ObjectType[ArrayID] = 3;
			break;
		case 11707:
			ReplaceObject2((objectX - 1), objectY, objectID, Face, -2);
			ObjectHandler.ObjectType[ArrayID] = 3;
			break;
		}
	}

	public void ReplaceObject(int objectX, int objectY, int NewObjectID,
			int Face)
	{
		outStream.createFrameVarSizeWord(60);
		outStream.put(objectY - (mapRegionY * 8));
		outStream.putByteC(objectX - (mapRegionX * 8));
		outStream.put(101);
		outStream.putByteC(0);
		outStream.put(0);
		if (NewObjectID > -1)
		{
			outStream.put(151);
			outStream.putByteS(0);
			outStream.putShortBE(NewObjectID);
			outStream.putByteA(Face);
		}
		outStream.endFrameVarSizeWord();
	}

	public void ReplaceObject2(int objectX, int objectY, int NewObjectID,
			int Face, int ObjectType)
	{
		outStream.createFrame(85);
		outStream.putByteC(objectY - (mapRegionY * 8));
		outStream.putByteC(objectX - (mapRegionX * 8));
		outStream.createFrame(101);
		outStream.putByteC((ObjectType << 2) + (Face & 3));
		outStream.put(0);
		if (NewObjectID != -1)
		{
			outStream.createFrame(151);
			outStream.putByteS(0);
			outStream.putShortBE(NewObjectID);
			outStream.putByteS((ObjectType << 2) + (Face & 3));
			// FACE: 0= WEST | -1 = NORTH | -2 = EAST | -3 = SOUTH
			// ObjectType: 0-3 wall objects, 4-8 wall decoration, 9: diag.
			// walls, 10-11 world objects, 12-21: roofs, 22: floor decoration
		}
	}

	public void AddGlobalObj(int objectX, int objectY, int NewObjectID,
			int Face, int ObjectType)
	{
		for (Player p : PlayerHandler.players)
		{
			if (p != null)
			{
				Client person = (Client) p;
				if ((person.username != null || person.username != "null"))
				{
					if (person.distanceToPoint(objectX, objectY) <= 60)
					{
						person.ReplaceObject2(objectX, objectY, NewObjectID,
								Face, ObjectType);
					}
				}
			}
		}
	}

	public void Addaobject(int x, int y, int typeID, int orientation,
			int tileObjectType, int heightLevel)
	{
		outStream.createFrame(85);
		outStream.putByteC(y - (mapRegionY * 8));
		outStream.putByteC(x - (mapRegionX * 8));
		outStream.createFrame(151);
		// outStream.writeByteA(((x&7) << 4) + (y&7));
		outStream.putByteA(0);
		outStream.putShortBE(typeID);
		outStream.putByteS((tileObjectType << 2) + (orientation & 3));
	}

	public void deletethatobject(int objectX, int objectY)
	{
		ReplaceObject2(objectX, objectY, 6951, -1, 10);
	}

	public void deletethatwall(int objectX, int objectY)
	{
		ReplaceObject2(objectX, objectY, 6951, -1, 0);
	}

	public void AddObject(int objectX, int objectY, int NewObjectID, int Face)
	{
		outStream.createFrameVarSizeWord(60);
		outStream.put(objectY - (mapRegionY * 8));
		outStream.putByteC(objectX - (mapRegionX * 8));
		if (NewObjectID > -1)
		{
			outStream.put(151);
			outStream.putByteS(0);
			outStream.putShortBE(NewObjectID);
			outStream.putByteA(Face);
		}
		outStream.endFrameVarSizeWord();
	}

	public void makeGlobalObject(int x, int y, int typeID, int orientation,
			int tileObjectType)
	{
		for (Player p : PlayerHandler.players)
		{
			if (p != null)
			{
				Client person = (Client) p;
				if ((person.username != null || person.username != "null"))
				{
					if (person.distanceToPoint(x, y) <= 60)
					{
						person.createNewTileObject(x, y, typeID, orientation,
								tileObjectType);
					}
				}
			}
		}
	}

	public void Deleteobjects()
	{
		deletethatobject(2509, 3041); // door
		deletethatobject(1889, 5355); // door
		deletethatobject(2937, 3245); // doo
		deletethatobject(2937, 3254); // door
		deletethatobject(2937, 3243); // doo
		deletethatobject(2936, 3243); // doo
		deletethatobject(2935, 3243); // doo
		deletethatobject(2929, 3249); // door
		deletethatobject(2511, 3039); // door
		deletethatobject(2514, 3042); // door
		deletethatobject(2516, 3040); // door
		deletethatobject(2514, 3040); // door
		deletethatobject(2511, 3041); // door
		deletethatobject(2467, 4940); // door
		deletethatobject(2487, 4974); // door
		deletethatobject(2486, 4974); // door
		deletethatobject(2485, 4974); // door
		deletethatobject(2451, 4935); // door
		deletethatobject(3248, 9364); // door
		deletethatobject(2789, 2979); // door
		deletethatobject(2937, 3252); // door
		deletethatobject(2938, 3252); // door
		deletethatobject(3684, 3476); // door
		deletethatobject(3657, 3500); // Stairs
		deletethatobject(3656, 3496); // Stairs
		deletethatobject(3657, 3492); // Stairs
		deletethatobject(3684, 3476); // Stairs
		deletethatobject(2466, 3495); // Stairs
		deletethatobject(2895, 3513); // Stairs
		deletethatobject(2524, 4779); // Stairs
		deletethatobject(2526, 4774); // Stairs
		deletethatobject(2895, 3513); // Stairs
		deletethatobject(2885, 3515); // Tree
		deletethatobject(2883, 3511); // Tree
		deletethatobject(2886, 3514); // Tree
		deletethatobject(2883, 3512); // Tree
		deletethatobject(2883, 3508); // Tree
		deletethatobject(2886, 3506); // Tree
		deletethatobject(2886, 3510); // Tree
		deletethatobject(2785, 3175); // plant
		deletethatobject(2929, 3248); // skills
		deletethatobject(2930, 3249); // plant
		deletethatobject(2928, 3249); // skills
		deletethatobject(2929, 3249); // skills
		deletethatobject(2933, 3247); // skills
		deletethatobject(2933, 3256); // skills
		deletethatobject(2932, 3247); // skills
		deletethatobject(2937, 3244); // skills
		deletethatobject(2931, 3247); // skills
		deletethatobject(2930, 3247); // skills
		deletethatobject(2929, 3247); // skills
	}

	public void Deletewalls()
	{
		deletethatwall(2895, 3513); // Stairs
		deletethatwall(2892, 9907); // Ladder to home
		deletethatwall(2891, 3511); // door
		deletethatwall(2891, 3510); // door
		deletethatwall(2790, 3177); // door
		deletethatwall(2794, 3180); // door
		deletethatwall(2816, 3183); // gate 1
		deletethatwall(2937, 3252); // door
		deletethatwall(2938, 3252); // door
		deletethatwall(2816, 3182); // gate 2
		deletethatwall(2847, 9637); // gate 3
		deletethatwall(2847, 9636); // gate 4
		deletethatwall(2836, 9600); // wall
		deletethatwall(2811, 3170); // wall
		deletethatwall(3292, 3167); // wall
		deletethatwall(3287, 3171); // wall
		deletethatwall(3293, 3167); // wall
		deletethatwall(3298, 3171); // wall
		deletethatobject(2799, 3154); // Removed misc
		deletethatobject(2798, 3156); // Removed misc
		deletethatobject(2797, 3156); // Removed misc
		deletethatobject(2796, 3156); // Removed misc
		deletethatobject(2795, 3156); // Removed misc
		deletethatobject(2794, 3156); // Removed misc
		deletethatobject(2791, 3157); // Removed misc
		deletethatobject(2794, 3157); // Removed misc
		deletethatobject(2796, 3157); // Removed misc
		deletethatobject(2797, 3157); // Removed misc
		deletethatobject(2799, 3157); // Removed misc
		deletethatobject(2795, 3159); // Removed misc
		deletethatobject(2794, 3160); // Removed misc
		deletethatobject(2794, 3161); // Removed misc
		deletethatobject(2795, 3160); // Removed misc
		deletethatobject(2797, 3161); // Removed misc
		deletethatobject(2798, 3159); // Removed misc
		deletethatobject(2799, 3160); // Removed misc
		deletethatobject(2799, 3161); // Removed misc
		deletethatobject(2798, 3160); // Removed misc
		deletethatobject(2791, 3160); // Removed misc
		deletethatobject(2791, 3161); // Removed misc
		deletethatobject(2791, 3162); // Removed misc
		deletethatobject(2791, 3164); // Removed misc
		deletethatobject(2799, 3164); // Removed misc
		deletethatobject(2799, 3165); // Removed misc
		deletethatobject(2799, 3166); // Removed misc
		deletethatobject(2799, 3169); // Removed misc
		deletethatobject(2798, 3169); // Removed misc
		deletethatobject(2797, 3169); // Removed misc
		deletethatobject(2791, 3167); // Removed misc
		deletethatobject(2791, 3166); // Removed misc
		deletethatobject(2793, 3166); // Removed misc
		deletethatobject(2794, 3166); // Removed misc
		deletethatobject(2795, 3166); // Removed misc
		deletethatobject(2796, 3166); // Removed misc
		deletethatobject(2793, 3165); // Removed misc
		deletethatobject(2795, 3165); // Removed misc
		deletethatobject(2793, 3164); // Removed misc
		deletethatobject(2794, 3164); // Removed misc
		deletethatobject(2795, 3164); // Removed misc
		deletethatobject(2796, 3164); // Removed misc
	}

	public boolean firemaking()
	{
		if (playerLevel[FIREMAKING] >= firemaking[1])
		{
			if (actionTimer == 0 && IsMakingFire == false)
			{
				actionAmount++;
				sendMessage("You Attempt To Light A Fire...");
				OriginalWeapon = playerEquipment[playerWeapon];
				OriginalShield = playerEquipment[playerShield];
				playerEquipment[playerWeapon] = 590;
				playerEquipment[playerShield] = -1;
				actionTimer = 5;
				if (actionTimer < 1)
				{
					actionTimer = 1;
				}
				setAnimation(0x2DD);
				IsMakingFire = true;
			}
			if (actionTimer == 0 && IsMakingFire == true)
			{
				addSkillXP((firemaking[2] * firemaking[3]), FIREMAKING);
				ItemHandler.DroppedItemsSDelay[firemaking[4]] = ItemHandler.MaxDropShowDelay + 1;
				CreateNewFire();
				sendMessage("You light a fire.");
				playerEquipment[playerWeapon] = OriginalWeapon;
				OriginalWeapon = -1;
				resetAnimation();
				IsMakingFire = false;
				resetFM();
			}
		}
		else
		{
			sendMessage("You need " + firemaking[1] + " "
					+ statName[FIREMAKING] + " to light these logs.");
			resetFM();
			return false;
		}
		return true;
	}

	public boolean resetFM()
	{
		firemaking[0] = 0;
		firemaking[1] = 0;
		firemaking[2] = 0;
		firemaking[4] = 0;
		skillX = -1;
		skillY = -1;
		IsUsingSkill = false;
		IsMakingFire = false;
		return true;
	}

	public void CreateNewFire()
	{
		for (int i = 0; i < ObjectHandler.MaxObjects; i++)
		{
			if (ObjectHandler.ObjectFireID[i] == -1)
			{
				ObjectHandler.ObjectFireID[i] = 2732;
				ObjectHandler.ObjectFireX[i] = skillX;
				ObjectHandler.ObjectFireY[i] = skillY;
				ObjectHandler.ObjectFireH[i] = heightLevel;
				ObjectHandler.ObjectFireMaxDelay[i] = ObjectHandler.FireDelay
						+ (ObjectHandler.FireGianDelay * firemaking[0]);
				break;
			}
		}
	}

	public void AddObjectFire()
	{
		if (IsFireing == false)
		{
			IsFireing = true;
			int tmpX = 0;
			int tmpY = 0;
			int calcX = 0;
			int calcY = 0;
			for (int i = 0; i < ObjectHandler.MaxObjects; i++)
			{
				if (ItemHandler.DroppedItemsID[i] > -1)
				{
					tmpX = ObjectHandler.ObjectFireX[i];
					tmpY = ObjectHandler.ObjectFireY[i];
					calcX = tmpX - absX;
					calcY = tmpY - absY;
					if (calcX >= -16 && calcX <= 15 && calcY >= -16
							&& calcY <= 15 && FireDelete[i] == false
							&& ObjectHandler.ObjectFireH[i] == heightLevel)
					{
						if (IsFireShowed[i] == false)
						{
							IsFireShowed[i] = true;
							ReplaceObject2(ObjectHandler.ObjectFireX[i],
									ObjectHandler.ObjectFireY[i],
									ObjectHandler.ObjectFireID[i], 0, 10);
						}
					}
					else if (IsFireShowed[i] == true || FireDelete[i] == true)
					{
						ReplaceObject2(ObjectHandler.ObjectFireX[i],
								ObjectHandler.ObjectFireY[i], -1, 0, 10);
						int LastPlayerInList = -1;
						int TotalPlayers = 0;
						for (int j = 1; j < PlayerHandler.maxPlayers; j++)
						{
							if (PlayerHandler.players[j] != null)
							{
								LastPlayerInList = j;
								TotalPlayers++;
							}
						}
						if (FireDelete[i] == true)
						{
							int fireX = ObjectHandler.ObjectFireX[i];
							int fireY = ObjectHandler.ObjectFireY[i];
							FireDelete[i] = false;
							ObjectHandler.ObjectFireDeletecount[i]++;
							if ((LastPlayerInList == playerId || LastPlayerInList == -1)
									&& ObjectHandler.ObjectFireDeletecount[i] == TotalPlayers)
							{
								ObjectHandler.getSingleton().ResetFire(i);
								for (int j = 1; j < PlayerHandler.maxPlayers; j++)
								{
									if (PlayerHandler.players[j] != null)
									{
										PlayerHandler.players[j].IsFireShowed[i] = false;
									}
								}
								if (MethodCollection.random(2) == 1)
								{
									ItemHandler.addItem(592, fireX, fireY, 1,
											playerId, false);
								}
							}
						}
						else
						{
							IsFireShowed[i] = false;
						}
					}
				}
			}
			IsFireing = false;
		}
	}

	public void NewObjects()
	{
		makeGlobalObject(2734, 3473, 4150, -1, 10);// Str Guild Portal
		makeGlobalObject(2734, 3475, 4151, -1, 10);// Attack Guild Portal
		makeGlobalObject(2734, 3471, 4152, -1, 10);// Defence Guild Portal		
		makeGlobalObject(2740, 3471, 4154, -1, 10);//Thieve Guild Portal
		makeGlobalObject(2740, 3473, 4156, -1, 10);//Magic Guild Portal		
		makeGlobalObject(2596, 4774, 7171, -1, 10);// chill
		makeGlobalObject(2606, 4771, 1292, 0, 10);// chill
		makeGlobalObject(2605, 4779, 1306, 0, 10);// chill
		makeGlobalObject(2601, 4779, 1307, 0, 10);// chill
		makeGlobalObject(2598, 4779, 1276, 0, 10);// chill
		makeGlobalObject(2601, 4775, 2213, 0, 10);// chill
		makeGlobalObject(2602, 4774, 2213, 1, 10);// chill
		makeGlobalObject(2600, 4774, 2213, 1, 10);// chill
		makeGlobalObject(2601, 4773, 2213, 0, 10);// chill
		makeGlobalObject(3344, 3253, 2213, 0, 10);// staff
		makeGlobalObject(3343, 3253, 2213, 0, 10);// staff
		makeGlobalObject(3118, 9843, 2213, 0, 10);// train
		makeGlobalObject(2930, 3252, 8956, 3, 10); // skills..
		makeGlobalObject(2847, 3432, 9745, 0, 10);// skills
		makeGlobalObject(3117, 9842, 2213, -1, 10);// train
		makeGlobalObject(3118, 9841, 2213, 0, 10);// train
		makeGlobalObject(3117, 9843, 2501, 0, 10);// train
		makeGlobalObject(3119, 9842, 2213, -1, 10);// train
		makeGlobalObject(3119, 9841, 2501, 0, 10);// train
		makeGlobalObject(3117, 9841, 2501, 0, 10);// train
		makeGlobalObject(3119, 9843, 2501, 0, 10);// train
		makeGlobalObject(3343, 3230, 4618, 0, 10);// staffzone
		makeGlobalObject(3344, 3225, 2213, 0, 10);// staffzone
		makeGlobalObject(3345, 3225, 2213, 0, 10);// staffzone
		makeGlobalObject(3346, 3225, 2213, 0, 10);// staffzone
		makeGlobalObject(3343, 3225, 2213, 0, 10);// staffzone
		makeGlobalObject(3342, 3225, 2213, 0, 10);// staffzone
		makeGlobalObject(1890, 5359, 1032, -2, 10);// pkbox
		makeGlobalObject(1893, 5358, 1032, -1, 10);// pkbox
		makeGlobalObject(1893, 5357, 1032, -1, 10);// pkbox
		makeGlobalObject(1893, 5354, 1032, -1, 10);// pkbox
		makeGlobalObject(1893, 5353, 1032, -1, 10);// pkbox
		makeGlobalObject(1890, 5352, 1032, 0, 10);// pkbox
		makeGlobalObject(1891, 5352, 1032, 0, 10);// pkbox
		makeGlobalObject(1892, 5352, 1032, 0, 10);// pkbox
		makeGlobalObject(1891, 5359, 1032, -2, 10);// pkbox
		makeGlobalObject(1892, 5359, 1032, -2, 10);// pkbox
		makeGlobalObject(1889, 5356, 2213, -1, 10);// pkbox
		makeGlobalObject(1889, 5352, 2501, 0, 10);// pkbox
		makeGlobalObject(1893, 5359, 2501, 0, 10);// pkbox
		makeGlobalObject(1893, 5352, 2501, 0, 10);// pkbox
		makeGlobalObject(1889, 5359, 2501, 0, 10);// pkbox
		makeGlobalObject(1889, 5353, 6552, -3, 10); // pkbox
		makeGlobalObject(1889, 5357, 2213, -1, 10);// pkbox
		makeGlobalObject(1889, 5358, 2213, -1, 10);// pkbox
		makeGlobalObject(2757, 3483, 2213, 0, 10);// shop
		makeGlobalObject(2758, 3483, 2213, 0, 10);// shop
		makeGlobalObject(3240, 9363, 2213, -1, 10);// funpk
		makeGlobalObject(3240, 9364, 2213, -1, 10);// funpk
		makeGlobalObject(3240, 9365, 2213, -1, 10);// funpk
		makeGlobalObject(3240, 9366, 2213, -1, 10);// funpk
		makeGlobalObject(3250, 9369, 4767, -1, 10);// funpk
		makeGlobalObject(3247, 9369, 4767, -1, 10);// funpk
		makeGlobalObject(3244, 9369, 4767, -1, 10);// funpk
		makeGlobalObject(3249, 9361, 2842, -1, 10);// funpk
		makeGlobalObject(3251, 9361, 2842, -1, 10);// funpk
		makeGlobalObject(3253, 9361, 2842, -1, 10);// funpk
		makeGlobalObject(3255, 9361, 2842, -1, 10);// funpk
		makeGlobalObject(3245, 9361, 2842, -1, 10);// funpk
		makeGlobalObject(3243, 9361, 2842, -1, 10);// funpk
		makeGlobalObject(3241, 9361, 2842, -1, 10);// funpk
		makeGlobalObject(3255, 9362, 6552, -1, 10);// funpk
		makeGlobalObject(3243, 9363, 1032, 0, 10);// funpk
		makeGlobalObject(3097, 3596, 1032, 0, 10);// 1v1
		makeGlobalObject(3097, 3597, 1032, 0, 10);// 1v1
		makeGlobalObject(3097, 3598, 1032, 0, 10);// 1v1
		makeGlobalObject(3098, 3599, 1032, -2, 10);// 1v1
		makeGlobalObject(3102, 3596, 1032, 0, 10);// 1v1
		makeGlobalObject(3102, 3597, 1032, 0, 10);// 1v1
		makeGlobalObject(3102, 3598, 1032, 0, 10);// 1v1
		makeGlobalObject(3101, 3599, 1032, -2, 10);// 1v1
		makeGlobalObject(3097, 3595, 2501, 0, 10);// 1v1
		makeGlobalObject(3097, 3599, 2501, 0, 10);// 1v1
		makeGlobalObject(3102, 3595, 2501, 0, 10);// 1v1
		makeGlobalObject(3102, 3599, 2501, 0, 10);// 1v1
		makeGlobalObject(3098, 3595, 2213, 0, 10); // 1v1
		makeGlobalObject(3099, 3595, 2213, 0, 10); // 1v1
		makeGlobalObject(3100, 3595, 2213, 0, 10); // 1v1
		makeGlobalObject(3101, 3595, 2213, 0, 10); // 1v1
		makeGlobalObject(2959, 9484, 1994, 0, 10);// water mini chest
		makeGlobalObject(2973, 9498, 1995, 0, 10);// water mini chest
		makeGlobalObject(2995, 9500, 2079, 0, 10);// water mini chest
		makeGlobalObject(2966, 9499, 2360, 0, 10);// water mini chest
		makeGlobalObject(2953, 9504, 2191, 0, 10);// water mini chest
		makeGlobalObject(3000, 9489, 2194, 0, 10);// water mini chest
		makeGlobalObject(2964, 9522, 2195, 0, 10);// water mini chest
		makeGlobalObject(2988, 9511, 2196, 0, 10);// water mini chest
		makeGlobalObject(2989, 9495, 2197, 0, 10);// water mini chest
		makeGlobalObject(2962, 9477, 2198, 0, 10);// water mini chest
		makeGlobalObject(2967, 9490, 62, 2, 10);// merlin's crystal
		makeGlobalObject(2673, 9565, 555, 0, 10); // Dbminista
		makeGlobalObject(2674, 9566, 555, 0, 10); // Dbminista
		makeGlobalObject(2672, 9562, 555, 0, 10); // Dbminista
		makeGlobalObject(2672, 9561, 555, 0, 10); // Dbminista
		makeGlobalObject(2672, 9560, 555, 0, 10); // Dbminista
		makeGlobalObject(2674, 9557, 555, 0, 10); // Dbminista
		makeGlobalObject(2675, 9557, 555, 0, 10); // Dbminista
		makeGlobalObject(2676, 9557, 555, 0, 10); // Dbminista
		makeGlobalObject(2687, 9564, 555, 0, 10); // Dbminista
		makeGlobalObject(2683, 9568, 555, 0, 10); // Dbminista
		makeGlobalObject(2679, 9585, 555, 0, 10); // Dbminista
		makeGlobalObject(2678, 9585, 555, 0, 10); // Dbminista
		makeGlobalObject(2677, 9585, 555, 0, 10); // Dbminista
		makeGlobalObject(2676, 9585, 555, 0, 10); // Dbminista
		makeGlobalObject(2672, 9588, 555, 0, 10); // Dbminista
		makeGlobalObject(2671, 9589, 555, 0, 10); // Dbminista
		makeGlobalObject(2670, 9590, 555, 0, 10); // Dbminista
		makeGlobalObject(2665, 9559, 555, 0, 10); // Dbminista
		makeGlobalObject(2665, 9558, 555, 0, 10); // Dbminista
		makeGlobalObject(2665, 9557, 555, 0, 10); // Dbminista
		makeGlobalObject(2665, 9556, 555, 0, 10); // Dbminista
		makeGlobalObject(2665, 9555, 555, 0, 10); // Dbminista
		makeGlobalObject(2665, 9554, 555, 0, 10); // Dbminista
		makeGlobalObject(2665, 9553, 555, 0, 10); // Dbminista
		makeGlobalObject(2674, 9553, 555, 0, 10); // Dbminista
		makeGlobalObject(2675, 9553, 555, 0, 10); // Dbminista
		makeGlobalObject(2676, 9553, 555, 0, 10); // Dbminista
		makeGlobalObject(2677, 9553, 555, 0, 10); // Dbminista
		makeGlobalObject(2678, 9553, 555, 0, 10); // Dbminista
		makeGlobalObject(2675, 9566, 555, 0, 10); // Dbminista
		makeGlobalObject(2675, 9565, 555, 0, 10); // Dbminist
		makeGlobalObject(2676, 9675, 555, 0, 10); // Dbminista
		makeGlobalObject(2677, 9565, 555, 0, 10); // Dbminista
		makeGlobalObject(2677, 9566, 555, 0, 10); // Dbminista
		makeGlobalObject(2676, 9565, 555, 0, 10); // Dbminista
		makeGlobalObject(2266, 4700, 468, 0, 10); // Stalag
		makeGlobalObject(2264, 4700, 468, 0, 10); // Stalag
		makeGlobalObject(2265, 4700, 468, 0, 10); // Stalag
		makeGlobalObject(2266, 4700, 468, 0, 10); // Stalag
		makeGlobalObject(2267, 4700, 468, 0, 10); // Stalag
		makeGlobalObject(2268, 4700, 468, 0, 10); // Stalag
		makeGlobalObject(2269, 4700, 468, 0, 10); // Stalag
		makeGlobalObject(2270, 4700, 468, 0, 10); // Stalag
		makeGlobalObject(2271, 4700, 468, 0, 10); // Stalag
		makeGlobalObject(2272, 4700, 468, 0, 10); // Stalag
		makeGlobalObject(2273, 4700, 468, 0, 10); // Stalag
		makeGlobalObject(2274, 4700, 468, 0, 10); // Stalag
		makeGlobalObject(2274, 4699, 468, 0, 10); // Stalag
		makeGlobalObject(2274, 4698, 468, 0, 10); // Stalag
		makeGlobalObject(2274, 4697, 468, 0, 10); // Stalag
		makeGlobalObject(2274, 4696, 468, 0, 10); // Stalag
		makeGlobalObject(2274, 4695, 468, 0, 10); // Stalag
		makeGlobalObject(2274, 4694, 468, 0, 10); // Stalag
		makeGlobalObject(2274, 4693, 468, 0, 10); // Stalag
		makeGlobalObject(2274, 4692, 468, 0, 10); // Stalag
		makeGlobalObject(2274, 4691, 468, 0, 10); // Stalag
		makeGlobalObject(2274, 4690, 468, 0, 10); // Stalag
		makeGlobalObject(2264, 4699, 468, 0, 10); // Stalag
		makeGlobalObject(2264, 4698, 468, 0, 10); // Stalag
		makeGlobalObject(2264, 4697, 468, 0, 10); // Stalag
		makeGlobalObject(2264, 4696, 468, 0, 10); // Stalag
		makeGlobalObject(2264, 4695, 468, 0, 10); // Stalag
		makeGlobalObject(2264, 4694, 468, 0, 10); // Stalag
		makeGlobalObject(2264, 4693, 468, 0, 10); // Stalag
		makeGlobalObject(2264, 4692, 468, 0, 10); // Stalag
		makeGlobalObject(2264, 4691, 468, 0, 10); // Stalag
		makeGlobalObject(2264, 4690, 468, 0, 10); // Stalag
		makeGlobalObject(2273, 4690, 468, 0, 10); // Stalag
		makeGlobalObject(2272, 4690, 468, 0, 10); // Stalag
		makeGlobalObject(2271, 4690, 468, 0, 10); // Stalag
		makeGlobalObject(2270, 4690, 468, 0, 10); // Stalag
		makeGlobalObject(2269, 4690, 468, 0, 10); // Stalag
		makeGlobalObject(2268, 4690, 468, 0, 10); // Stalag
		makeGlobalObject(2267, 4690, 468, 0, 10); // Stalag
		makeGlobalObject(2266, 4690, 468, 0, 10); // Stalag
		makeGlobalObject(2265, 4690, 468, 0, 10); // Stalag
		makeGlobalObject(2272, 4699, 468, 0, 10); // Stalag
		makeGlobalObject(2272, 4698, 468, 0, 10); // Stalag
		makeGlobalObject(2273, 4698, 468, 0, 10); // Stalag
		makeGlobalObject(2656, 9542, 468, 0, 10); // Stalag
		makeGlobalObject(2654, 9545, 468, 0, 10); // Stalag
		makeGlobalObject(2654, 9546, 468, 0, 10); // Stalag
		makeGlobalObject(2654, 9553, 468, 0, 10); // Stalag
		makeGlobalObject(2655, 9553, 468, 0, 10); // Stalag
		makeGlobalObject(3175, 9755, 1032, -3, 10);// Anvil
		makeGlobalObject(3175, 9756, 1032, -3, 10);// Anvil
		makeGlobalObject(3175, 9757, 1032, -3, 10);// Anvil
		makeGlobalObject(3175, 9759, 1032, -3, 10);// Anvil
		makeGlobalObject(3175, 9760, 1032, -3, 10);// Anvil
		makeGlobalObject(3175, 9761, 1032, -3, 10);// Anvil
		makeGlobalObject(3181, 9758, 11758, -3, 10);// Anvil
		makeGlobalObject(3180, 9753, 6552, -1, 10);// Anvil
		makeGlobalObject(2097, 4436, 2213, -2, 10);// Anvil
		makeGlobalObject(2477, 3048, 2213, -1, 10);// Anvil
		makeGlobalObject(2477, 3045, 2213, -1, 10);// Anvil
		makeGlobalObject(2478, 3047, 2213, -1, 10);// Anvil
		makeGlobalObject(2478, 3046, 2213, -1, 10);// Anvil
		makeGlobalObject(2096, 4436, 2213, -2, 10);// Anvil
		makeGlobalObject(2094, 4436, 4172, -3, 10);// Anvil
		makeGlobalObject(2092, 4435, 8151, -3, 10);// Anvil
		makeGlobalObject(2930, 3248, 2480, -1, 10);// Anvil
		makeGlobalObject(2931, 3255, 1276, -3, 10);// Anvil
		makeGlobalObject(2933, 3255, 1307, -3, 10);// Anvil
		makeGlobalObject(2935, 3255, 1306, -3, 10);// Anvil
		makeGlobalObject(2936, 3244, 2287, -3, 10);// Anvil
		makeGlobalObject(2099, 4422, 1308, -3, 10);// Anvil
		makeGlobalObject(2932, 3246, 2783, -3, 10);// Anvil
		makeGlobalObject(2930, 3246, 2783, -3, 10);// Anvil
		makeGlobalObject(2100, 4427, 2111, -3, 10);// Anvil
		makeGlobalObject(2850, 3434, 4876, -2, 10);// Anvil
		makeGlobalObject(2851, 3434, 4877, -2, 10);// Anvil
		makeGlobalObject(2852, 3434, 4878, -2, 10);// Anvil
		makeGlobalObject(2854, 3434, 2565, -2, 10);// Anvil
		makeGlobalObject(2856, 3434, 2560, -2, 10);// Anvil
		makeGlobalObject(2858, 3434, 2562, -2, 10);// Anvil
		makeGlobalObject(2926, 3248, 3816, -1, 10);// Anvil
		makeGlobalObject(2929, 3254, 2193, -3, 10);// Anvil
		makeGlobalObject(2926, 3251, 12120, -3, 10);// Anvil
		makeGlobalObject(2940, 3249, 11758, -3, 10);// Anvil
		makeGlobalObject(3247, 3387, 4877, 0, 10); // general stall
		makeGlobalObject(3254, 3387, 2562, -1, 10); // gem stall
		makeGlobalObject(3246, 3387, 4878, 0, 10); // scimitar stall
		makeGlobalObject(3245, 3387, 4876, 0, 10); // magic stall
		makeGlobalObject(3252, 3387, 2565, -1, 10); // silver stall
		makeGlobalObject(3249, 3387, 2560, -2, 10); // silk stall
		makeGlobalObject(3230, 9313, 4706, -2, 10); // Thieve Guild Stall		
		makeGlobalObject(2929, 3244, 2111, -3, 10);// Anvil
		makeGlobalObject(2927, 3244, 2111, -3, 10);// Anvil
		makeGlobalObject(2940, 3248, 11758, -3, 10);// Anvil
		makeGlobalObject(2940, 3247, 11758, -3, 10);// Anvil
		makeGlobalObject(2907, 9716, 2213, -2, 10);// Anvil
		makeGlobalObject(2510, 3863, 2783, -3, 10);// Anvil
		makeGlobalObject(2906, 9709, 3644, -3, 10);// Anvil
		makeGlobalObject(2902, 9710, 12356, -3, 10);// Anvil
		makeGlobalObject(2898, 9700, 6282, -3, 10);// Anvil
		makeGlobalObject(3182, 9632, 6774, -3, 10);// Anvil
		makeGlobalObject(3182, 9630, 4121, -3, 10);// Anvil
		makeGlobalObject(2843, 2964, 61, -3, 10); // Ancient Alter
		makeGlobalObject(3042, 4843, 2781, 0, 10);// Furnace
		makeGlobalObject(2854, 3957, 61, 2, 10); // Ancient Alter
		makeGlobalObject(2444, 3086, 2498, 0, 10); // bank
		makeGlobalObject(2712, 9817, 2213, -1, 10); // bank
		makeGlobalObject(2714, 9817, 2213, -1, 10); // bank
		makeGlobalObject(2713, 9816, 2213, 0, 10); // bank
		makeGlobalObject(2713, 9818, 2213, 0, 10); // bank
		makeGlobalObject(2712, 9818, 2498, 0, 10); // bank
		makeGlobalObject(2712, 9816, 2498, 0, 10); // bank
		makeGlobalObject(2714, 9816, 2498, 0, 10); // bank
		makeGlobalObject(2714, 9818, 2498, 0, 10); // bank
		makeGlobalObject(2965, 9483, 2498, 0, 10); // bank
		makeGlobalObject(2966, 9483, 2498, 0, 10); // bank
		makeGlobalObject(2478, 3048, 2498, 0, 10); // bank
		makeGlobalObject(2478, 3045, 2498, 0, 10); // bank
		makeGlobalObject(2967, 9483, 2498, 0, 10); // bank
		makeGlobalObject(2968, 9483, 2498, 0, 10); // bank
		makeGlobalObject(2443, 3086, 2498, 0, 10); // bank
		makeGlobalObject(2442, 3086, 2498, 0, 10); // bank
		makeGlobalObject(2442, 3082, 2498, 0, 10); // bank
		makeGlobalObject(2442, 3083, 2498, 0, 10); // bank
		makeGlobalObject(2442, 3085, 2213, -1, 10); // bank
		makeGlobalObject(2442, 3084, 2213, -1, 10); // bank
		makeGlobalObject(2868, 9883, 2213, 0, 10); // bank
		makeGlobalObject(2868, 9882, 2213, 0, 10); // bank
		makeGlobalObject(2868, 9881, 2213, 0, 10); // bank
		makeGlobalObject(3088, 3934, 2213, 1, 10); // bank
		makeGlobalObject(3088, 3933, 2213, 1, 10); // bank
		makeGlobalObject(2859, 9881, 2213, 0, 10); // bank
		makeGlobalObject(2859, 9882, 2213, 0, 10); // bank
		makeGlobalObject(2742, 9499, 2213, 0, 10); // bank
		makeGlobalObject(2741, 9499, 2213, 0, 10); // bank
		makeGlobalObject(2740, 9499, 2213, 0, 10); // bank
		makeGlobalObject(2739, 9499, 2213, 0, 10); // bank
		makeGlobalObject(2738, 9499, 2213, 0, 10); // bank
		makeGlobalObject(2723, 9487, 2213, 1, 10); // bank
		makeGlobalObject(2723, 9486, 2213, 1, 10); // bank
		makeGlobalObject(2723, 9485, 2213, 1, 10); // bank
		makeGlobalObject(2757, 3504, 2213, 0, 10); // bank
		makeGlobalObject(2758, 3504, 2213, 0, 10); // bank
		makeGlobalObject(2864, 9881, 2932, 0, 10); // thing
		makeGlobalObject(2863, 9881, 2932, 0, 10); // thing
		makeGlobalObject(2844, 3433, 4172, 0, 10); // Range
		makeGlobalObject(2895, 3512, 2213, 0, 10); // bank
		makeGlobalObject(2895, 3509, 2213, 0, 10); // bank
		makeGlobalObject(2892, 9907, 4626, 0, 10); // Stairs
		makeGlobalObject(2465, 4817, 4126, 0, 10); // silver chest
		makeGlobalObject(3306, 3204, 2783, 0, 10); // anvil
		makeGlobalObject(2834, 9820, 471, 0, 10); // rock slide
		makeGlobalObject(2836, 9820, 473, 0, 10); // rock slide
		makeGlobalObject(2836, 9821, 473, 0, 10); // rock slide
		makeGlobalObject(2892, 3512, 6552, -3, 10); // magic altar
		makeGlobalObject(2768, 2756, 362, -3, 10); // barrel
		makeGlobalObject(2901, 3557, 2213, 0, 10); // bank
		makeGlobalObject(2538, 4719, 2213, 0, 10); // bank
		makeGlobalObject(2537, 4719, 2213, 0, 10); // bank
		makeGlobalObject(2539, 4719, 2213, 0, 10); // bank
		makeGlobalObject(2605, 3157, 2213, -1, 10); // banks
		makeGlobalObject(2976, 3402, 1276, -1, 10); // regtree
		makeGlobalObject(2982, 3398, 1308, -1, 10); // willowtree
		makeGlobalObject(2982, 3403, 1307, -1, 10); // mapletree
		makeGlobalObject(2978, 3404, 1309, -1, 10); // yewtree
		makeGlobalObject(2979, 3402, 1306, -1, 10); // magictree
		makeGlobalObject(3054, 3437, 4483, 0, 10); // bankchest
		makeGlobalObject(3096, 3468, 767, 0, 10); // chest
		makeGlobalObject(3426, 3536, 2213, -1, 10); // bankbooth
		makeGlobalObject(3429, 3539, 104, -2, 10); // openchest
		makeGlobalObject(3427, 3539, 104, -2, 10); // openchest
		makeGlobalObject(3306, 3204, 2783, 0, 10); // anvil
		makeGlobalObject(3101, 3513, 6477, -1, 10); // Bank
		makeGlobalObject(3286, 3211, 2562, 0, 10); // gem stall
		makeGlobalObject(3299, 3199, 4878, 0, 10); // scimitar stall
		makeGlobalObject(3299, 3200, 4876, 0, 10); // magic stall
		makeGlobalObject(3298, 3205, 2565, 0, 10); // silver stall
		makeGlobalObject(2542, 3895, 6552, 0, 10); // magic altar
		makeGlobalObject(2768, 2756, 362, -3, 10); // barrel
		makeGlobalObject(2901, 3557, 2213, 0, 10); // bank
		makeGlobalObject(2605, 3157, 2213, -1, 10); // banks
		makeGlobalObject(2976, 3402, 1276, -1, 10); // regtree
		makeGlobalObject(2982, 3398, 1308, -1, 10); // willowtree
		makeGlobalObject(2982, 3403, 1307, -1, 10); // mapletree
		makeGlobalObject(2978, 3404, 1309, -1, 10); // yewtree
		makeGlobalObject(2539, 3860, 1306, -1, 10); // magictree
		makeGlobalObject(2563, 3867, 1306, -1, 10); // magictree
		makeGlobalObject(2609, 3854, 1306, -1, 10); // magictree
		makeGlobalObject(2565, 3888, 1306, -1, 10); // magictree
		makeGlobalObject(3090, 3498, 61, -1, 10); // altar
		makeGlobalObject(2731, 9488, 61, 0, 10); // altar
		makeGlobalObject(2437, 3093, 61, -3, 10); // altar
		makeGlobalObject(2727, 9488, 6552, -2, 10); // Ancient Alter
		makeGlobalObject(2437, 3085, 6552, -3, 10); // Ancient Alter
		makeGlobalObject(3121, 9838, 2513, 0, 10); // target
		makeGlobalObject(3054, 3437, 4483, 0, 10); // bankchest
		makeGlobalObject(3096, 3468, 767, 0, 10); // chest
		makeGlobalObject(3426, 3536, 2213, -1, 10); // bankbooth
		makeGlobalObject(3429, 3539, 104, -2, 10); // openchest
		makeGlobalObject(3427, 3539, 104, -2, 10); // openchest
		makeGlobalObject(2860, 2957, 6084, -2, 10); // Bank
		makeGlobalObject(2859, 2957, 6083, -2, 10); // Bank
		makeGlobalObject(2518, 3858, 6084, -2, 10); // Bankhome
		makeGlobalObject(2517, 3858, 6083, -2, 10); // Bankhome
		makeGlobalObject(2516, 3858, 6084, -2, 10); // Bank
		makeGlobalObject(2515, 3858, 6083, -2, 10); // Bank
		makeGlobalObject(2514, 3858, 6084, -2, 10); // Bank
		makeGlobalObject(2513, 3858, 210, -2, 10); // Light
		makeGlobalObject(2513, 3857, 210, -2, 10); // Light
		makeGlobalObject(2513, 3856, 210, -2, 10); // Light
		makeGlobalObject(2519, 3858, 210, -2, 10); // Light
		makeGlobalObject(2519, 3857, 210, -2, 10); // Light
		makeGlobalObject(2519, 3856, 210, -2, 10); // Light
		makeGlobalObject(3104, 3523, 6477, 0, 10); // Bank
		makeGlobalObject(3107, 3531, 6472, 0, 10); // Bank
		makeGlobalObject(3085, 3512, 6416, -3, 10); // Bank
		makeGlobalObject(3090, 3512, 6416, -3, 10); // bank
		makeGlobalObject(3435, 9772, 11758, 0, 10);// bank booth @ pkbox
		makeGlobalObject(3434, 9772, 11758, 0, 10);// bank booth @ pkbox
		makeGlobalObject(3433, 9772, 11758, 0, 10);// bank booth @ pkbox
		makeGlobalObject(3432, 9772, 11758, 0, 10);// bank booth @ pkbox
		makeGlobalObject(3428, 9775, 6473, -1, 10);// Ice chunks
		makeGlobalObject(3428, 9776, 6473, -1, 10);// Ice chunks
		makeGlobalObject(3428, 9774, 6473, -1, 10);// Ice chunks
		makeGlobalObject(3428, 9773, 6473, -1, 10);// Ice chunks
		makeGlobalObject(3428, 9772, 6473, -1, 10);// Ice chunks
		makeGlobalObject(3428, 9771, 6473, -1, 10);// Ice chunks
		makeGlobalObject(3428, 9770, 6473, -1, 10);// Ice chunks
		makeGlobalObject(3428, 9777, 6473, -1, 10);// Ice chunks
		makeGlobalObject(3428, 9778, 6473, -1, 10);// Ice chunks
		makeGlobalObject(3428, 9779, 6473, -1, 10);// Ice chunks
		makeGlobalObject(3428, 9780, 6473, -1, 10);// Ice chunks
		makeGlobalObject(3428, 9781, 6473, -1, 10);// Ice chunks
		makeGlobalObject(3428, 9782, 6473, -1, 10);// Ice chunks
		makeGlobalObject(3428, 9783, 6473, -1, 10);// Ice chunks
		makeGlobalObject(3428, 9784, 6473, -1, 10);// Ice chunks
		makeGlobalObject(3428, 9785, 6473, -1, 10);// Ice chunks
		makeGlobalObject(3428, 9786, 6473, -1, 10);// Ice chunks
		makeGlobalObject(3428, 9787, 6473, -1, 10);// Ice chunks
		makeGlobalObject(3428, 9788, 6473, -1, 10);// Ice chunks
		makeGlobalObject(3428, 9789, 6473, -1, 10);// Ice chunks
		makeGlobalObject(3428, 9790, 6473, -1, 10);// Ice chunks
		makeGlobalObject(3428, 9791, 6473, -1, 10);// Ice chunks
		makeGlobalObject(3427, 9770, 6473, -1, 10);// Ice chunks
		makeGlobalObject(3428, 9770, 6473, -1, 10);// Ice chunks
		makeGlobalObject(3429, 9770, 6473, -1, 10);// Ice chunks
		makeGlobalObject(3430, 9770, 6473, -1, 10);// Ice chunks
		makeGlobalObject(3431, 9770, 6473, -1, 10);// Ice chunks
		makeGlobalObject(3432, 9770, 6473, -1, 10);// Ice chunks
		makeGlobalObject(3433, 9770, 6473, -1, 10);// Ice chunks
		makeGlobalObject(3434, 9770, 6473, -1, 10);// Ice chunks
		makeGlobalObject(3435, 9770, 6473, -1, 10);// Ice chunks
		makeGlobalObject(3437, 9770, 6473, -1, 10);// Ice chunks
		makeGlobalObject(3438, 9770, 6473, -1, 10);// Ice chunks
		makeGlobalObject(3438, 9771, 6473, -1, 10);// Ice chunks
		makeGlobalObject(3438, 9772, 6473, -1, 10);// Ice chunks
		makeGlobalObject(3438, 9773, 6473, -1, 10);// Ice chunks
		makeGlobalObject(3438, 9774, 6473, -1, 10);// Ice chunks
		makeGlobalObject(3438, 9775, 6473, -1, 10);// Ice chunks
		makeGlobalObject(3438, 9776, 6473, -1, 10);// Ice chunks
		makeGlobalObject(3438, 9777, 6473, -1, 10);// Ice chunks
		makeGlobalObject(3438, 9778, 6473, -1, 10);// Ice chunks
		makeGlobalObject(3438, 9779, 6473, -1, 10);// Ice chunks
		makeGlobalObject(3438, 9780, 6473, -1, 10);// Ice chunks
		makeGlobalObject(3438, 9781, 6473, -1, 10);// Ice chunks
		makeGlobalObject(3438, 9782, 6473, -1, 10);// Ice chunks
		makeGlobalObject(3438, 9783, 6473, -1, 10);// Ice chunks
		makeGlobalObject(3438, 9784, 6473, -1, 10);// Ice chunks
		makeGlobalObject(3439, 9784, 6473, -1, 10);// Ice chunks
		makeGlobalObject(3440, 9784, 6473, -1, 10);// Ice chunks
		makeGlobalObject(3441, 9784, 6473, -1, 10);// Ice chunks
		makeGlobalObject(3442, 9784, 6473, -1, 10);// Ice chunks
		makeGlobalObject(3443, 9784, 6473, -1, 10);// Ice chunks
		makeGlobalObject(3444, 9784, 6473, -1, 10);// Ice chunks
		makeGlobalObject(3444, 9785, 6473, -1, 10);// Ice chunks
		makeGlobalObject(3444, 9786, 6473, -1, 10);// Ice chunks
		makeGlobalObject(3444, 9787, 6473, -1, 10);// Ice chunks
		makeGlobalObject(3444, 9788, 6473, -1, 10);// Ice chunks
		makeGlobalObject(3444, 9789, 6473, -1, 10);// Ice chunks
		makeGlobalObject(3444, 9790, 6473, -1, 10);// Ice chunks
		makeGlobalObject(3444, 9791, 6473, -1, 10);// Ice chunks
		makeGlobalObject(3444, 9792, 6473, -1, 10);// Ice chunks
		makeGlobalObject(3444, 9791, 6473, -1, 10);// Ice chunks
		makeGlobalObject(3443, 9791, 6473, -1, 10);// Ice chunks
		makeGlobalObject(3442, 9791, 6473, -1, 10);// Ice chunks
		makeGlobalObject(3441, 9791, 6473, -1, 10);// Ice chunks
		makeGlobalObject(3440, 9791, 6473, -1, 10);// Ice chunks
		makeGlobalObject(3439, 9791, 6473, -1, 10);// Ice chunks
		makeGlobalObject(3438, 9791, 6473, -1, 10);// Ice chunks
		makeGlobalObject(3437, 9791, 6473, -1, 10);// Ice chunks
		makeGlobalObject(3436, 9791, 6473, -1, 10);// Ice chunks
		makeGlobalObject(3435, 9791, 6473, -1, 10);// Ice chunks
		makeGlobalObject(3434, 9791, 6473, -1, 10);// Ice chunks
		makeGlobalObject(3433, 9791, 6473, -1, 10);// Ice chunks
		makeGlobalObject(3432, 9791, 6473, -1, 10);// Ice chunks
		makeGlobalObject(3431, 9791, 6473, -1, 10);// Ice chunks
		makeGlobalObject(3430, 9791, 6473, -1, 10);// Ice chunks
		makeGlobalObject(3437, 9777, 1032, -2, 10);// Danger Sign
		makeGlobalObject(3436, 9777, 1032, -2, 10);// Danger Sign
		makeGlobalObject(3435, 9777, 1032, -2, 10);// Danger Sign
		makeGlobalObject(3432, 9777, 1032, -2, 10);// Danger Sign
		makeGlobalObject(3430, 9777, 1032, -2, 10);// Danger Sign
		makeGlobalObject(3431, 9777, 1032, -2, 10);// Danger Sign
		makeGlobalObject(2853, 2957, 2213, -2, 10); // bank
		makeGlobalObject(2851, 2957, 2213, -2, 10); // bank
		makeGlobalObject(2535, 3891, 2213, -2, 10); // bank
		makeGlobalObject(2535, 3890, 2213, -2, 10); // bank
		makeGlobalObject(2535, 3889, 2213, -2, 10); // bank
		makeGlobalObject(3243, 3515, 2213, -2, 10); // bank
		makeGlobalObject(3244, 3515, 2213, -2, 10); // bank
		makeGlobalObject(3240, 3520, 1032, -2, 10);// Danger Sign
		makeGlobalObject(3241, 3520, 1032, -2, 10);// Danger Sign
		makeGlobalObject(3442, 3520, 1032, -2, 10);// Danger Sign
		makeGlobalObject(3245, 3520, 1032, -2, 10);// Danger Sign
		makeGlobalObject(3246, 3520, 1032, -2, 10);// Danger Sign
		makeGlobalObject(3247, 3520, 1032, -2, 10);// Danger Sign
		makeGlobalObject(2739, 3469, 6084, 0, 10); // bank
		makeGlobalObject(2738, 3469, 6084, 0, 10); // bank
		makeGlobalObject(2737, 3469, 6084, 0, 10); // bank
		makeGlobalObject(2736, 3469, 6084, 0, 10); // bank
		makeGlobalObject(2736, 3467, 6084, 0, 10); // bank
		makeGlobalObject(2737, 3467, 6084, 0, 10); // bank
		makeGlobalObject(2738, 3467, 6084, 0, 10); // bank
		makeGlobalObject(2739, 3467, 6084, 0, 10); // bank
		makeGlobalObject(3430, 9774, 6552, 1, 10); // Ancient Alter
		makeGlobalObject(3246, 3517, 6552, -1, 10); // Ancient Alter
		makeGlobalObject(3095, 3500, 6552, -2, 10); // Ancient Alter
		makeGlobalObject(3568, 3307, 2213, 0, 10); // bank
		makeGlobalObject(3569, 3307, 2213, 0, 10); // bank
		makeGlobalObject(3233, 9321, 2213, 0, 10); // bank
		makeGlobalObject(3234, 9321, 2213, 0, 10); // bank
		makeGlobalObject(3224, 2807, 2498, -1, 10); // wall
		makeGlobalObject(3224, 2806, 2498, -1, 10); // wall
		makeGlobalObject(3224, 2805, 2498, -1, 10); // wall
		makeGlobalObject(3224, 2804, 2498, -1, 10); // wall
		makeGlobalObject(3224, 2803, 2498, -1, 10); // wall
		makeGlobalObject(3224, 2802, 2498, -1, 10); // wall
		makeGlobalObject(3224, 2801, 2498, -1, 10); // wall
		makeGlobalObject(3224, 2800, 2498, -1, 10); // wall
		makeGlobalObject(3224, 2799, 2498, -1, 10); // wall
		makeGlobalObject(3224, 2798, 2498, -1, 10); // wall
		makeGlobalObject(3224, 2797, 2498, -1, 10); // wall
		makeGlobalObject(3224, 2796, 2498, -1, 10); // wall
		makeGlobalObject(3224, 2795, 2498, -1, 10); // wall
		makeGlobalObject(3224, 2794, 2498, -1, 10); // wall
		makeGlobalObject(3224, 2793, 2498, -1, 10); // wall
		makeGlobalObject(3224, 2792, 2498, -1, 10); // wall
		makeGlobalObject(3224, 2791, 2498, -1, 10); // wall
		makeGlobalObject(3224, 2790, 2498, -1, 10); // wall
		makeGlobalObject(3224, 2789, 2498, -1, 10); // wall
		makeGlobalObject(3224, 2788, 2498, -1, 10); // wall
		makeGlobalObject(3224, 2787, 2498, -1, 10); // wall
		makeGlobalObject(3224, 2786, 2498, -1, 10); // wall
		makeGlobalObject(3224, 2785, 2498, -1, 10); // wall
		makeGlobalObject(3224, 2784, 2498, -1, 10); // wall
		makeGlobalObject(3224, 2783, 2498, -1, 10); // wall
		makeGlobalObject(3223, 2783, 2498, -2, 10); // wall
		makeGlobalObject(3223, 2783, 2498, -2, 10); // wall
		makeGlobalObject(3222, 2783, 2498, -2, 10); // wall
		makeGlobalObject(3221, 2783, 2498, -2, 10); // wall
		makeGlobalObject(3220, 2783, 2498, -2, 10); // wall
		makeGlobalObject(3219, 2783, 2498, -2, 10); // wall
		makeGlobalObject(3218, 2783, 2498, -2, 10); // wall
		makeGlobalObject(3217, 2783, 2498, -2, 10); // wall
		makeGlobalObject(3216, 2783, 2498, -2, 10); // wall
		makeGlobalObject(3215, 2783, 2498, -2, 10); // wall
		makeGlobalObject(3214, 2783, 2498, -2, 10); // wall
		makeGlobalObject(3213, 2783, 2498, -2, 10); // wall
		makeGlobalObject(3212, 2783, 2498, -2, 10); // wall
		makeGlobalObject(3211, 2783, 2498, -2, 10); // wall
		makeGlobalObject(3210, 2783, 2498, -2, 10); // wall
		makeGlobalObject(3209, 2783, 2498, -2, 10); // wall
		makeGlobalObject(2736, 3443, 2498, -2, 10); // wall
		makeGlobalObject(2736, 3443, 2498, -2, 10); // wall
		makeGlobalObject(2736, 3442, 2498, -2, 10); // wall
		makeGlobalObject(2736, 3441, 2498, -2, 10); // wall
		makeGlobalObject(3208, 2783, 2498, -2, 10); // wall
		makeGlobalObject(3207, 2783, 2498, -2, 10); // wall
		makeGlobalObject(3206, 2783, 2498, -2, 10); // wall
		makeGlobalObject(3205, 2783, 2498, -2, 10); // wall
		makeGlobalObject(3209, 2804, 2213, -2, 10); // bank
		makeGlobalObject(3209, 2802, 2213, -2, 10); // bank
		makeGlobalObject(3208, 2803, 2213, -1, 10); // bank
		makeGlobalObject(3210, 2803, 2213, -1, 10); // bank
		makeGlobalObject(3210, 2802, 4901, 0, 10); // idk
		makeGlobalObject(3210, 2804, 4901, 0, 10); // idk
		makeGlobalObject(3208, 2802, 4901, 0, 10); // idk
		makeGlobalObject(3208, 2804, 4901, 0, 10); // idk
		makeGlobalObject(2500, 3018, 2832, 0, 10); // idk
		makeGlobalObject(2501, 3018, 2832, 0, 10); // idk
		makeGlobalObject(3231, 3499, 2182, 0, 10); // minigamebarrel
		makeGlobalObject(2794, 9326, 4121, -1, 10); // minigamechest
		makeGlobalObject(2539, 3032, 2832, 0, 10); // idk
		makeGlobalObject(2539, 3033, 2832, 0, 10); // idk
		makeGlobalObject(2866, 9956, 4119, 2, 10); // minigamechest
		makeGlobalObject(3284, 2777, 2213, 0, 10); // bank
		makeGlobalObject(3283, 2777, 2213, 0, 10); // bank
		makeGlobalObject(3282, 2777, 2213, 0, 10); // bank
		makeGlobalObject(3281, 2777, 2213, 0, 10); // bank
		makeGlobalObject(3280, 2777, 2213, 0, 10); // bank
		makeGlobalObject(3279, 2777, 2213, 0, 10); // bank
		makeGlobalObject(3278, 2777, 2213, 0, 10); // bank
		makeGlobalObject(3281, 2764, 1032, 0, 10);// Danger Sign
		makeGlobalObject(2933, 3243, 8151, 0, 10);// farming
		makeGlobalObject(3341, 3232, 10687, -1, 10);// staffzone box
		makeGlobalObject(2850, 3593, 210, -1, 10);// light
		makeGlobalObject(2850, 3590, 210, -1, 10);// fire
		makeGlobalObject(2855, 3594, 576, -1, 10);// statue
		makeGlobalObject(2855, 3589, 576, -1, 10);// statue
		makeGlobalObject(2038, 4531, 5981, -1, 10);// statue
		makeGlobalObject(2850, 3591, 579, -1, 10);// statue
		makeGlobalObject(2850, 3592, 579, -1, 10);// statue
		makeGlobalObject(2034, 4531, 5981, -1, 10);// fire
		makeGlobalObject(2034, 4530, 5981, -1, 10);// fire
		makeGlobalObject(2034, 4529, 5981, -1, 10);// fire
		makeGlobalObject(2034, 4528, 5981, -1, 10);// fire
		makeGlobalObject(2034, 4527, 5981, -1, 10);// fire
		makeGlobalObject(2034, 4526, 5981, -1, 10);// fire
		makeGlobalObject(2035, 4526, 5981, -1, 10);// fire
		makeGlobalObject(2036, 4526, 5981, -1, 10);// fire
		makeGlobalObject(2037, 4526, 5981, -1, 10);// fire
		makeGlobalObject(2038, 4526, 5981, -1, 10);// fire
		makeGlobalObject(2039, 4526, 5981, -1, 10);// fire
		makeGlobalObject(2039, 4527, 5981, -1, 10);// fire
		makeGlobalObject(2874, 3600, 5981, -1, 10);// fire
		makeGlobalObject(2858, 3587, 2213, 0, 10); // bank
		makeGlobalObject(2857, 3587, 2213, 0, 10); // bank
		makeGlobalObject(2853, 3590, 2213, 0, 10); // bank
		makeGlobalObject(2852, 3590, 2213, 0, 10); // bank
		makeGlobalObject(2851, 3590, 2213, 0, 10); // bank
		makeGlobalObject(2854, 3595, 6552, 1, 10); // Ancient Alter
		makeGlobalObject(2915, 9711, 6552, 0, 10); // Ancient Altar At Home.
		makeGlobalObject(3489, 9688, 5981, -1, 10);// fire
		makeGlobalObject(3489, 9687, 5981, -1, 10);// fire
		makeGlobalObject(3489, 9686, 5981, -1, 10);// fire
		makeGlobalObject(3489, 9689, 5981, -1, 10);// fire
		makeGlobalObject(3496, 9665, 10688, -1, 10);// crateatsg
		makeGlobalObject(3490, 9668, 11758, -1, 10);// bank boothh
		makeGlobalObject(3490, 9669, 11758, -1, 10);// bank boothh
		makeGlobalObject(3490, 9670, 11758, -1, 10);// bank boothh
		makeGlobalObject(3490, 9671, 11758, -1, 10);// bank boothh
		makeGlobalObject(3490, 9672, 11758, -1, 10);// bank boothh
		makeGlobalObject(3490, 9673, 11758, -1, 10);// bank boothh
		makeGlobalObject(2860, 3591, 884, -1, 10);// well
		makeGlobalObject(3281, 2765, 3644, 0, 10); // light
		makeGlobalObject(3280, 2765, 3644, 0, 10); // ligh
		makeGlobalObject(3239, 9363, 5981, -1, 10);// fire
		makeGlobalObject(3239, 9364, 5981, -1, 10);// fire
		makeGlobalObject(3239, 9365, 5981, -1, 10);// fire
		makeGlobalObject(3239, 9366, 5981, -1, 10);// fire
		makeGlobalObject(3305, 9375, 11758, -1, 10);// bank boothh
		makeGlobalObject(3305, 9376, 11758, -1, 10);// bank boothh
		makeGlobalObject(3305, 9374, 11758, -1, 10);// bank boothh
		makeGlobalObject(3305, 9377, 11758, -1, 10);// bank boothh
		makeGlobalObject(3308, 9372, 1032, -1, 10);// Danger Sign
		makeGlobalObject(3308, 9373, 1032, -1, 10);// Danger Sign
		makeGlobalObject(3308, 9374, 1032, -1, 10);// Danger Sign
		makeGlobalObject(3308, 9380, 1032, -1, 10);// Danger Sign
		makeGlobalObject(3308, 9379, 1032, -1, 10);// Danger Sign
		makeGlobalObject(3308, 9378, 1032, -1, 10);// Danger Sign
		makeGlobalObject(3308, 9377, 1032, -1, 10);// Danger Sign
		makeGlobalObject(2758, 3416, 6472, -1, 10);// bank booth
		makeGlobalObject(2758, 3417, 6472, -1, 10);// bank booth
		makeGlobalObject(2758, 3418, 6472, -1, 10);// bank booth
		makeGlobalObject(2758, 3419, 6472, -1, 10);// bank booth
		makeGlobalObject(2758, 3420, 6472, -1, 10);// bank booth
		makeGlobalObject(2758, 3421, 6472, -1, 10);// bank booth
		makeGlobalObject(2758, 3422, 6472, -1, 10);// bank booth
		makeGlobalObject(2758, 3423, 6472, -1, 10);// bank booth
		makeGlobalObject(2758, 3424, 6472, -1, 10);// bank booth
		makeGlobalObject(2757, 3424, 6472, -1, 10);// bank booth
		makeGlobalObject(2756, 3424, 6472, -1, 10);// bank booth
		makeGlobalObject(2755, 3424, 6472, -1, 10);// bank booth
		makeGlobalObject(2754, 3424, 6472, -1, 10);// bank booth
		makeGlobalObject(2753, 3424, 6472, -1, 10);// bank booth
		makeGlobalObject(2752, 3424, 6472, -1, 10);// bank booth
		makeGlobalObject(2751, 3424, 6472, -1, 10);// bank booth
		makeGlobalObject(2750, 3424, 6472, -1, 10);// bank booth
		makeGlobalObject(2749, 3424, 6472, -1, 10);// bank booth
		makeGlobalObject(2748, 3424, 6472, -1, 10);// bank booth
		makeGlobalObject(2747, 3424, 6472, -1, 10);// bank booth
		makeGlobalObject(2746, 3424, 6472, -1, 10);// bank booth
		makeGlobalObject(2746, 3423, 6472, -1, 10);// bank booth
		makeGlobalObject(2746, 3422, 6472, -1, 10);// bank booth
		makeGlobalObject(2746, 3421, 6472, -1, 10);// bank booth
		makeGlobalObject(2746, 3420, 6472, -1, 10);// bank booth
		makeGlobalObject(2746, 3419, 6472, -1, 10);// bank booth
		makeGlobalObject(2746, 3418, 6472, -1, 10);// bank booth
		makeGlobalObject(2746, 3417, 6472, -1, 10);// bank booth
		makeGlobalObject(2757, 3414, 6472, -1, 10);// bank booth
		makeGlobalObject(2751, 3414, 6472, -1, 10);// bank booth
		makeGlobalObject(2750, 3414, 6472, -1, 10);// bank booth
		makeGlobalObject(2749, 3414, 6472, -1, 10);// bank booth
		makeGlobalObject(2748, 3414, 6472, -1, 10);// bank booth
		makeGlobalObject(2747, 3414, 6472, -1, 10);// bank booth
		makeGlobalObject(2754, 3414, 6472, -1, 10);// bank booth
		makeGlobalObject(2755, 3414, 6472, -1, 10);// bank booth
		makeGlobalObject(2756, 3414, 6472, -1, 10);// bank booth
		makeGlobalObject(2753, 3414, 6472, -1, 10);// bank booth
		makeGlobalObject(2747, 3416, 6084, -1, 10);// bank booth
		makeGlobalObject(2568, 3850, 4172, -2, 10);// bank booth
		makeGlobalObject(2568, 3852, 2213, -1, 10);// bank booth
		makeGlobalObject(3031, 3354, 2498, -1, 10);// bank booth
		makeGlobalObject(3031, 3355, 2498, -1, 10);// bank booth
		makeGlobalObject(3031, 3356, 2498, -1, 10);// bank booth
		makeGlobalObject(3031, 3357, 2498, -1, 10);// bank booth
		makeGlobalObject(3031, 3358, 2498, -1, 10);// bank booth
		makeGlobalObject(3031, 3359, 2498, -1, 10);// bank booth
		makeGlobalObject(3031, 3360, 2498, -1, 10);// bank booth
		makeGlobalObject(3031, 3361, 2498, -1, 10);// bank booth
		makeGlobalObject(3031, 3362, 2498, -1, 10);// bank booth
		makeGlobalObject(3032, 3363, 2498, -2, 10);// bank booth
		makeGlobalObject(3033, 3363, 2498, -2, 10);// bank booth
		makeGlobalObject(3026, 3351, 2498, -1, 10);// bank booth
		makeGlobalObject(3026, 3350, 2498, -1, 10);// bank booth
		makeGlobalObject(3026, 3349, 2498, -1, 10);// bank booth
		makeGlobalObject(3026, 3348, 2498, -1, 10);// bank booth
		makeGlobalObject(3033, 3346, 2498, -2, 10);// bank booth
		makeGlobalObject(3032, 3346, 2498, -2, 10);// bank booth
		makeGlobalObject(3031, 3346, 2498, -2, 10);// bank booth
		makeGlobalObject(3030, 3346, 2498, -2, 10);// bank booth
		makeGlobalObject(3034, 3346, 2498, -2, 10);// bank booth
		makeGlobalObject(3029, 3346, 2498, -2, 10);// bank booth
		makeGlobalObject(3028, 3346, 2498, -2, 10);// bank booth
		makeGlobalObject(3041, 3342, 2498, -2, 10);// bank booth
		makeGlobalObject(3042, 3342, 2498, -2, 10);// bank booth
		makeGlobalObject(3043, 3342, 2498, -2, 10);// bank booth
		makeGlobalObject(3044, 3342, 2498, -2, 10);// bank booth
		makeGlobalObject(3041, 3342, 2498, -2, 10);// bank booth
		makeGlobalObject(3048, 3350, 2498, -1, 10);// bank booth
		makeGlobalObject(3048, 3351, 2498, -1, 10);// bank booth
		makeGlobalObject(3048, 3359, 2498, -1, 10);// bank booth
		makeGlobalObject(3048, 3360, 2498, -1, 10);// bank booth
		makeGlobalObject(3044, 3362, 2498, -2, 10);// bank booth
		makeGlobalObject(3043, 3362, 2498, -2, 10);// bank booth
		makeGlobalObject(3042, 3362, 2498, -2, 10);// bank booth
		makeGlobalObject(3046, 3352, 2213, -1, 10);// bank booth
		makeGlobalObject(3046, 3353, 2213, -1, 10);// bank booth
		makeGlobalObject(3046, 3354, 2213, -1, 10);// bank booth
		makeGlobalObject(3046, 3355, 2213, -1, 10);// bank booth
		makeGlobalObject(3047, 3356, 4876, 0, 10); // magic stal
		makeGlobalObject(2739, 9488, 3644, 0, 10); // magic stall
		makeGlobalObject(3040, 9733, 2498, 3, 10); // magic stall
		makeGlobalObject(3040, 9734, 2498, 3, 10); // magic stall
		makeGlobalObject(3040, 9735, 2498, 3, 10); // magic stall
		makeGlobalObject(3040, 9736, 2498, 3, 10); // magic stall
		makeGlobalObject(3040, 9737, 2498, 3, 10); // magic stall
		makeGlobalObject(3040, 9738, 2498, 3, 10); // magic stall
		makeGlobalObject(3040, 9743, 2498, 3, 10); // magic stall
		makeGlobalObject(3040, 9744, 2498, 3, 10); // magic stall
		makeGlobalObject(3040, 9745, 2498, 3, 10); // magic stall
		makeGlobalObject(3037, 9746, 2498, 0, 10); // magic stall
		makeGlobalObject(3038, 9746, 2498, 0, 10); // magic stall
		makeGlobalObject(3039, 9746, 2498, 0, 10); // magic stall
		makeGlobalObject(3026, 9741, 2213, 0, 10); // magic stall
		makeGlobalObject(3027, 9741, 2213, 0, 10); // magic stall
		makeGlobalObject(3028, 9741, 2213, 0, 10); // magic stall
		makeGlobalObject(3040, 9742, 788, 1, 10); // magic stall
		makeGlobalObject(3040, 9740, 789, 3, 10); // magic stall
		makeGlobalObject(3039, 9747, 7321, 3, 10); // magic stall
		makeGlobalObject(3230, 2915, 7351, 0, 10); // magic stall
		makeGlobalObject(3084, 3523, 2498, 4, 10); // magic stall
		makeGlobalObject(3083, 3523, 2498, 4, 10); // magic stall
		makeGlobalObject(3082, 3523, 2498, 4, 10); // magic stall
		makeGlobalObject(3081, 3523, 2498, 4, 10); // magic stall
		makeGlobalObject(3080, 3523, 2498, 4, 10); // magic stall
		makeGlobalObject(3079, 3523, 2498, 4, 10); // magic stall
		makeGlobalObject(3078, 3523, 2498, 4, 10); // magic stall
		makeGlobalObject(3077, 3523, 2498, 4, 10); // magic stall
		makeGlobalObject(3092, 3523, 2498, 4, 10); // magic stall
		makeGlobalObject(3093, 3523, 2498, 4, 10); // magic stall
		makeGlobalObject(3094, 3523, 2498, 4, 10); // magic stall
		makeGlobalObject(3095, 3523, 2498, 4, 10); // magic stall
		makeGlobalObject(3096, 3523, 2498, 4, 10); // magic stall
		makeGlobalObject(3097, 3523, 2498, 4, 10); // magic stall
		makeGlobalObject(2445, 3097, 2472, 0, 10);// modzone
		makeGlobalObject(3074, 9883, 2911, 4, 10);// modzone
		makeGlobalObject(3079, 9886, 2638, 4, 10);// modzone
		makeGlobalObject(3076, 9890, 1104, 2, 10);// modzone
		makeGlobalObject(3088, 3491, 6836, 2, 10);// modzone
		makeGlobalObject(3239, 3103, 2498, 0, 10);// wall
		makeGlobalObject(3240, 3103, 2498, 0, 10);// wall
		makeGlobalObject(3241, 3104, 2213, 1, 10);// wall
		makeGlobalObject(3241, 3105, 2213, 1, 10);// wall
		makeGlobalObject(3241, 3106, 2213, 1, 10);// wall
		makeGlobalObject(3241, 3107, 2213, 1, 10);// wall
		makeGlobalObject(3241, 3108, 2213, 1, 10);// wall
		makeGlobalObject(3241, 3109, 2213, 1, 10);// wall
		makeGlobalObject(3241, 3110, 2213, 1, 10);// wall
		makeGlobalObject(3241, 3111, 2498, 0, 10);// wall
		makeGlobalObject(3240, 3111, 2498, 0, 10);// wall
		makeGlobalObject(3239, 3111, 2498, 0, 10);// wall
		makeGlobalObject(3238, 3111, 2498, 0, 10);// wall
		makeGlobalObject(3237, 3111, 2498, 0, 10);// wall
		makeGlobalObject(3236, 3111, 2498, 0, 10);// wall
		makeGlobalObject(3235, 3111, 2498, 0, 10);// wall
		makeGlobalObject(3234, 3111, 2498, 0, 10);// wall
		makeGlobalObject(3233, 3111, 2498, 0, 10);// wall
		makeGlobalObject(3232, 3111, 2498, 0, 10);// wall
		makeGlobalObject(3231, 3111, 2498, 0, 10);// wall
		makeGlobalObject(3231, 3107, 7321, 0, 10);// wall
		makeGlobalObject(3237, 3104, 1306, 0, 10);// wall
		makeGlobalObject(3236, 3109, 1306, 0, 10);// wall
		makeGlobalObject(3087, 3506, 2498, 0, 10);// wall
		makeGlobalObject(3088, 3506, 2498, 0, 10);// wall
		makeGlobalObject(3089, 3506, 2498, 0, 10);// wall
		makeGlobalObject(3090, 3513, 2498, 0, 10);// wall
		makeGlobalObject(3089, 3513, 2498, 0, 10);// wall
		makeGlobalObject(3088, 3513, 2498, 0, 10);// wall
		makeGlobalObject(3087, 3513, 2498, 0, 10);// wall
		makeGlobalObject(3086, 3513, 2498, 0, 10);// wall
		makeGlobalObject(3085, 3513, 2498, 0, 10);// wall
		makeGlobalObject(3090, 3506, 2498, 0, 10);// wall
		makeGlobalObject(2758, 3493, 2498, 0, 10);// wall
		makeGlobalObject(2757, 3493, 2498, 0, 10);// wall
		makeGlobalObject(3086, 3506, 2498, 0, 10);// wall
		makeGlobalObject(2711, 9474, 2498, 0, 10);// wall
		makeGlobalObject(2710, 9474, 2498, 0, 10);// wall
		makeGlobalObject(2709, 9474, 2498, 0, 10);// wall
		makeGlobalObject(3565, 3309, 2498, 0, 10);// wall
		makeGlobalObject(3112, 3683, 2498, 0, 10);// wall
		makeGlobalObject(3112, 3684, 2498, 0, 10);// wall
		makeGlobalObject(3112, 3685, 2498, 0, 10);// wall
		makeGlobalObject(3112, 3686, 2498, 0, 10);// wall
		makeGlobalObject(3111, 3686, 2498, 0, 10);// wall
		makeGlobalObject(3108, 3686, 2498, 0, 10);// wall
		makeGlobalObject(3107, 3686, 2498, 0, 10);// wall
		makeGlobalObject(3107, 3685, 2498, 0, 10);// wall
		makeGlobalObject(3107, 3684, 2498, 0, 10);// wall
		makeGlobalObject(3107, 3683, 2498, 0, 10);// wall
		makeGlobalObject(2709, 9474, 2498, 0, 10);// wall
		makeGlobalObject(2708, 9474, 2498, 0, 10);// wall
		makeGlobalObject(3005, 3322, 2498, 0, 10);// wall
		makeGlobalObject(3006, 3322, 2498, 0, 10);// wall
		makeGlobalObject(3007, 3322, 2498, 0, 10);// wall
		makeGlobalObject(3008, 3322, 2498, 0, 10);// wall
		makeGlobalObject(2964, 3394, 2498, 0, 10);// wall
		makeGlobalObject(2965, 3394, 2498, 0, 10);// wall
		makeGlobalObject(2966, 3394, 2498, 0, 10);// wall
		makeGlobalObject(3480, 3475, 2498, 1, 10);// wall
		makeGlobalObject(3480, 3476, 2498, 1, 10);// wall
		makeGlobalObject(3480, 3477, 2498, 1, 10);// wall
		makeGlobalObject(3513, 3510, 2498, 0, 10);// wall
		makeGlobalObject(3512, 3511, 2498, 0, 10);// wall
		makeGlobalObject(3212, 2957, 2498, 0, 10);// wall
		makeGlobalObject(3213, 2958, 2498, 0, 10);// wall
		makeGlobalObject(3215, 2958, 2498, 0, 10);// wall
		makeGlobalObject(3516, 2957, 2498, 0, 10);// wall
		makeGlobalObject(3217, 2955, 2498, 0, 10);// wall
		makeGlobalObject(3217, 2954, 2498, 0, 10);// wall
		makeGlobalObject(3211, 2954, 2498, 0, 10);// wall
		makeGlobalObject(3211, 2955, 2498, 0, 10);// wall
		makeGlobalObject(3211, 2952, 2498, 0, 10);// wall
		makeGlobalObject(3211, 2951, 2498, 0, 10);// wall
		makeGlobalObject(3211, 2950, 2498, 0, 10);// wall
		makeGlobalObject(3211, 2949, 2498, 0, 10);// wall
		makeGlobalObject(3212, 2948, 2498, 0, 10);// wall
		makeGlobalObject(3216, 2957, 2498, 0, 10);// wall
		makeGlobalObject(3213, 2948, 2498, 0, 10);// wall
		makeGlobalObject(3214, 2948, 2498, 0, 10);// wall
		makeGlobalObject(3215, 2948, 2498, 0, 10);// wall
		makeGlobalObject(3216, 2948, 2498, 0, 10);// wall
		makeGlobalObject(3217, 2949, 2498, 0, 10);// wall
		makeGlobalObject(3217, 2950, 2498, 0, 10);// wall
		makeGlobalObject(3217, 2951, 2498, 0, 10);// wall
		makeGlobalObject(3217, 2952, 2498, 0, 10);// wall
		makeGlobalObject(2967, 3394, 2498, 0, 10);// wall
		makeGlobalObject(2907, 9701, 7324, 0, 10);// Member Portal
		makeGlobalObject(3178, 9632, 2498, 0, 10);// mini blocker
		makeGlobalObject(3178, 9631, 2498, 0, 10);// mini blocker
		makeGlobalObject(3178, 9630, 2498, 0, 10);// mini blocker
		makeGlobalObject(3178, 9629, 2498, 0, 10);// mini blocker
	}

	public void DoAction()
	{
		viewTo(destinationX, destinationY);
		switch (ActionType)
		{
		case 1: // Object click 1
			objectClick(destinationID, destinationX, destinationY, 0, 0, 1);
			break;
		case 2: // Object click 2
			objectClick2(destinationID, destinationX, destinationY);
			break;
		case 3: // Object click 3
			objectClick3(destinationID, destinationX, destinationY);
			break;
		default: // error
			// println_debug("Error - unknown ActionType found");
			break;
		}
	}

	public void guamSeed() // start of farming by i loot i
	{
		if (playerLevel[19] >= 1)
		{
			addSkillXP((50 * playerLevel[19]), 19);
			addItem(249, 1);
			pEmote = 0x831;
			deleteItem(5291, getItemSlot(5291), 1);
			sendMessage("You put the seed on the patch and get a herb!");
		}
		else if (playerLevel[19] < 1)
		{
			sendMessage("You need atleast 1 farming to plant this!");
		}
	}

	public void marrentillSeed()
	{
		if (playerLevel[19] >= 30)
		{
			addSkillXP((70 * playerLevel[19]), 19);
			addItem(251, 1);
			pEmote = 0x831;
			deleteItem(5292, getItemSlot(5292), 1);
			sendMessage("You put the seed on the patch and get a herb");
		}
		else if (playerLevel[19] < 30)
		{
			sendMessage("You need atleast 30 farming to plant this!");
		}
	}

	public void tarrominSeed()
	{
		if (playerLevel[19] >= 70)
		{
			addSkillXP((100 * playerLevel[19]), 19);
			addItem(253, 1);
			pEmote = 0x831;
			deleteItem(5293, getItemSlot(5293), 1);
			sendMessage("You put the seed on the patch and get a herb");
		}
		else if (playerLevel[19] < 70)
		{
			sendMessage("You need atleast 70 farming to plant this!");
		}
	}

	public void harrlanderSeed()
	{
		if (playerLevel[19] >= 80)
		{
			addSkillXP((120 * playerLevel[19]), 19);
			addItem(255, 1);
			pEmote = 0x831;
			deleteItem(5294, getItemSlot(5294), 1);
			sendMessage("You put the seed on the patch and get a herb");
		}
		else if (playerLevel[19] < 80)
		{
			sendMessage("You need atleast 80 farming to plant this!");
		}
	}

	public void ranarrSeed()
	{
		if (playerLevel[19] >= 100)
		{
			addSkillXP((150 * playerLevel[19]), 19);
			addItem(257, 1);
			pEmote = 0x831;
			deleteItem(5295, getItemSlot(5295), 1);
			sendMessage("You put the seed on the patch and get a herb");
		}
		else if (playerLevel[19] < 100)
		{
			sendMessage("You need atleast 100 farming to plant this!");
		}
	}

	public void toadflaxSeed()
	{
		if (playerLevel[19] >= 120)
		{
			addSkillXP((200 * playerLevel[19]), 19);
			addItem(2998, 1);
			pEmote = 0x831;
			deleteItem(5296, getItemSlot(5296), 1);
			sendMessage("You put the seed on the patch and get a herb");
		}
		else if (playerLevel[19] < 120)
		{
			sendMessage("You need atleast 120 farming to plant this!");
		}
	}

	public void iritSeed()
	{
		if (playerLevel[19] >= 140)
		{
			addSkillXP((300 * playerLevel[19]), 19);
			addItem(259, 1);
			pEmote = 0x831;
			deleteItem(5297, getItemSlot(5297), 1);
			sendMessage("You put the seed on the patch and get a herb");
		}
		else if (playerLevel[19] < 140)
		{
			sendMessage("You need atleast 140 farming to plant this!");
		}
	}

	public void avantoeSeed()
	{
		if (playerLevel[19] >= 150)
		{
			addSkillXP((400 * playerLevel[19]), 19);
			addItem(261, 1);
			pEmote = 0x831;
			deleteItem(5298, getItemSlot(5298), 1);
			sendMessage("You put the seed on the patch and get an herb");
		}
		else if (playerLevel[19] < 150)
		{
			sendMessage("You need atleast 150 farming to plant this!");
		}
	}

	public void kwuarmSeed()
	{
		if (playerLevel[19] >= 169)
		{
			addSkillXP((500 * playerLevel[19]), 19);
			addItem(263, 1);
			pEmote = 0x831;
			deleteItem(5299, getItemSlot(5299), 1);
			sendMessage("You put the seed on the patch and get a herb");
		}
		else if (playerLevel[19] < 169)
		{
			sendMessage("You need atleast 169 farming to plant this!");
		}
	}

	public void snapdragonSeed()
	{
		if (playerLevel[19] >= 180)
		{
			addSkillXP((700 * playerLevel[19]), 19);
			addItem(3000, 1);
			pEmote = 0x831;
			deleteItem(5300, getItemSlot(5300), 1);
			sendMessage("You put the seed on the patch and get a herb");
		}
		else if (playerLevel[19] < 180)
		{
			sendMessage("You need atleast 180 farming to plant this!");
		}
	}

	public void cadantineSeed()
	{
		if (playerLevel[19] >= 190)
		{
			addSkillXP((1200 * playerLevel[19]), 19);
			addItem(265, 1);
			pEmote = 0x831;
			deleteItem(5301, getItemSlot(5301), 1);
			sendMessage("You put the seed on the patch and get a herb");
		}
		else if (playerLevel[19] < 190)
		{
			sendMessage("You need atleast 190 farming to plant this!");
		}
	}

	public void lantadymeSeed()
	{
		if (playerLevel[19] >= 195)
		{
			addSkillXP((1800 * playerLevel[19]), 19);
			addItem(2481, 1);
			pEmote = 0x831;
			deleteItem(5302, getItemSlot(5302), 1);
			sendMessage("You put the seed on the patch and get a herb");
		}
		else if (playerLevel[19] < 195)
		{
			sendMessage("You need atleast 195 farming to plant this!");
		}
	}

	public void dwarfSeed()
	{
		if (playerLevel[19] >= 197)
		{
			addSkillXP((2500 * playerLevel[19]), 19);
			addItem(267, 1);
			pEmote = 0x831;
			deleteItem(5303, getItemSlot(5303), 1);
			sendMessage("You put the seed on the patch and get a herb");
		}
		else if (playerLevel[19] < 197)
		{
			sendMessage("You need atleast 197 farming to plant this!");
		}
	}

	public void torstolSeed()
	{
		if (playerLevel[19] >= 198)
		{
			addSkillXP((3300 * playerLevel[19]), 19);
			addItem(269, 1);
			pEmote = 0x831;
			deleteItem(5304, getItemSlot(5304), 1);
			sendMessage("You put the seed on the patch and get a herb");
		}
		else if (playerLevel[19] < 198)
		{
			sendMessage("You need atleast 198 farming to plant this!");
		}
	}

	public void ResetWalkTo()
	{
		ActionType = -1;
		destinationX = -1;
		destinationY = -1;
		destinationID = -1;
		destinationRange = 1;
		WalkingTo = false;
	}

	public void objectClick(int objectID, int objectX, int objectY, int face,
			int face2, int GateID)
	{
		switch (objectID)
		{
		case 2213:
			openUpBank();
			break;
		case 4499:
			if (objectX == 2797 && objectY == 3614)
			{
				sendMessage("You crawl through the cave..");
				teleportToX = 2772;
				teleportToY = 10231;
			}
			break;
		case 5025:
			if (objectX == 2772 && objectY == 10233)
			{
				sendMessage("You crawl through the Crevice!");
				teleportToX = 2795;
				teleportToY = 3614;
			}
			break;
		case 1600:
			if (objectY == 3087)
			{
				if (playerEquipment[playerCape] == 6070)
				{
					sendMessage("You sneak into the mage guild...");
					ReplaceObject(objectX, objectY, (objectID - 1), -2);
				}
				else
				{
					sendMessage("Get out!");
				}
			}
		case 1601:
			if (objectY == 3088)
			{
				if (playerEquipment[playerCape] == 6070)
				{
					sendMessage("You sneak into the mage guild...");
					ReplaceObject(objectX, objectY, (objectID - 1), -2);
				}
				else
				{
					sendMessage("Get out!");
				}
			}
		case 11993:
		case 1537:
		case 2427:
		case 2429:
			if ((objectX == 3231 && objectY == 3433)
					|| (objectX == 3253 && objectY == 3431)
					|| (objectX == 2719 && objectY == 9671)
					|| (objectX == 2722 && objectY == 9671)
					|| (objectX == 3109 && objectY == 3167)
					|| (objectX == 3107 && objectY == 3162))
			{
				face = -3; // South
			}
			else if ((objectX == 3234 && objectY == 3426)
					|| (objectX == 3225 && objectY == 3293)
					|| (objectX == 3230 && objectY == 3291)
					|| (objectX == 3235 && objectY == 3406)
					|| (objectX == 3276 && objectY == 3421)
					|| (objectX == 3207 && objectY == 3210))
			{
				face = -2; // East
			}
			else if ((objectX == 3233 && objectY == 3427)
					|| (objectX == 3215 && objectY == 3225)
					|| (objectX == 3207 && objectY == 3217)
					|| (objectX == 3208 && objectY == 3211))
			{
				face = -1; // North
			}// else = West (standard)
			ReplaceObject(objectX, objectY, (objectID - 1), face);
			break;
		case 1536:
			if ((objectX == 3235 && objectY == 3426)
					|| (objectX == 3233 && objectY == 3438)
					|| (objectX == 3207 && objectY == 3210))
			{
				face = -3; // South
			}
			else if ((objectX == 3231 && objectY == 3433) || (objectX == 2611)
					&& objectY == 3324)
			{
				face = -2; // East
			}
			else if ((objectX == 3234 && objectY == 3426)
					|| (objectX == 3225 && objectY == 3293)
					|| (objectX == 3230 && objectY == 3291)
					|| (objectX == 3241 && objectY == 3406)
					|| (objectX == 3235 && objectY == 3406)
					|| (objectX == 3276 && objectY == 3421)
					|| (objectX == 3248 && objectY == 3396)
					|| (objectX == 3260 && objectY == 3400))
			{
				face = -1; // North
			}// else = West (standard)
			ReplaceObject(objectX, objectY, (objectID + 1), face);
			break;
		case 1553:
		case 1551:
		case 1552:
		case 1556:
			if ((objectX == 3253 && objectY == 3266)
					|| (objectX == 3253 && objectY == 3267)
					|| (objectX == 3241 && objectY == 3301)
					|| (objectX == 3241 && objectY == 3302))
			{
				face = -3; // South
				GateID = 1;
			}
			else if ((objectX == 3236 && objectY == 3284)
					|| (objectX == 3236 && objectY == 3285)
					|| (objectX == 3236 && objectY == 3295)
					|| (objectX == 3236 && objectY == 3296))
			{
				face = -3; // South
				GateID = 2;
			}
			else if ((objectX == 3312 && objectY == 3234)
					|| (objectX == 3237 && objectY == 3284)
					|| (objectX == 3238 && objectY == 3284)
					|| (objectX == 3237 && objectY == 3295)
					|| (objectX == 3238 && objectY == 3295))
			{
				face = -2; // East
				GateID = 2;
			}// else = West (standard)
			if (GateID == 1)
			{
				if (objectID == 1553)
				{
					// Place the new gate
					ReplaceObject((objectX - 1), (objectY - 1), 1552, face);
					ReplaceObject((objectX - 2), (objectY - 1), 1556, face);
					// Remove the original gate
					ReplaceObject(objectX, (objectY - 1), -1, -1);
				}
				else if (objectID == 1551)
				{
					// Place the new gate
					ReplaceObject((objectX - 1), objectY, 1552, face);
					ReplaceObject((objectX - 2), objectY, 1556, face);
					// Remove the original gate
					ReplaceObject(objectX, (objectY + 1), -1, -1);
				}
				else if (objectID == 1552)
				{
					// Place the new gate
					ReplaceObject((objectX + 1), objectY, 1551, face);
					ReplaceObject((objectX + 1), (objectY + 1), 1553, face);
					// Remove the original gate
					ReplaceObject((objectX - 1), objectY, -1, -1);
				}
				else if (objectID == 1556)
				{
					// Place the new gate
					ReplaceObject((objectX + 2), objectY, 1551, face);
					ReplaceObject((objectX + 2), (objectY + 1), 1553, face);
					// Remove the original gate
					ReplaceObject((objectX + 1), objectY, -1, -1);
				}
			}
			else if (GateID == 2)
			{
				if (objectID == 1553)
				{
					// Place the new gate
					ReplaceObject((objectX + 2), objectY, 1552, face);
					ReplaceObject((objectX + 1), objectY, 1556, face);
					// Remove the original gate
					ReplaceObject(objectX, (objectY + 1), -1, -1);
				}
				else if (objectID == 1551)
				{
					// Place the new gate
					ReplaceObject((objectX + 2), (objectY - 1), 1552, face);
					ReplaceObject((objectX + 1), (objectY - 1), 1556, face);
					// Remove the original gate
					ReplaceObject(objectX, (objectY - 1), -1, -1);
				}
				else if (objectID == 1552)
				{
					// Place the new gate
					ReplaceObject((objectX - 2), (objectY + 1), 1551, face);
					ReplaceObject((objectX - 2), objectY, 1553, face);
					// Remove the original gate
					ReplaceObject((objectX - 1), objectY, -1, -1);
				}
				else if (objectID == 1556)
				{
					ReplaceObject((objectX - 1), (objectY + 1), 1551, face);
					ReplaceObject((objectX - 1), objectY, 1553, face);
					ReplaceObject((objectX + 1), objectY, -1, -1);
				}
			}
			ReplaceObject(objectX, objectY, -1, -1);
			break;
		case 1516:
		case 1517:
		case 1519:
		case 1520:
			if ((objectX == 3217 && objectY == 3218)
					|| (objectX == 3217 && objectY == 3219)
					|| (objectX == 3213 && objectY == 3222)
					|| (objectX == 3213 && objectY == 3221))
			{
				face = -3; // South
				face2 = -1; // North
			}// else = West (standard)
			if (objectID == 1516)
			{
				// Place the new gate
				ReplaceObject((objectX - 1), objectY, (objectID + 1), face);
				ReplaceObject((objectX - 1), (objectY + 1), (objectID + 4),
						face2);
				// Remove the original gate
				ReplaceObject(objectX, (objectY + 1), -1, -1);
				ReplaceObject(objectX, objectY, -1, -1);
			}
			else if (objectID == 1519)
			{
				// Place the new gate
				ReplaceObject((objectX - 1), (objectY - 1), (objectID - 2),
						face);
				ReplaceObject((objectX - 1), objectY, (objectID + 1), face2);
				// Remove the original gate
				ReplaceObject(objectX, objectY, -1, -1);
				ReplaceObject(objectX, (objectY - 1), -1, -1);
			}
			else if (objectID == 1517)
			{
				// Place the new gate
				ReplaceObject((objectX + 1), (objectY + 1), (objectID + 2),
						face);
				ReplaceObject((objectX + 1), objectY, (objectID - 1), face);
				// Remove the original gate
				ReplaceObject(objectX, (objectY + 1), -1, -1);
				ReplaceObject(objectX, objectY, -1, -1);
			}
			else if (objectID == 1520)
			{
				// Place the new gate
				ReplaceObject((objectX + 1), objectY, (objectID - 1), face);
				ReplaceObject((objectX + 1), (objectY - 1), (objectID - 4),
						face);
				// Remove the original gate
				ReplaceObject(objectX, objectY, -1, -1);
				ReplaceObject(objectX, (objectY - 1), -1, -1);
			}
			break;
		case 6552:
		{ // Ancient magic altar (temp !!!)
			if (GoodDistance(absX, absY, objectX, objectY, 1) == true)
			{
				if (playerAncientMagics == true)
				{
					setSidebarInterface(6, 1151); // magic tab (ancient =
													// 12855);
					playerAncientMagics = false;
					ancients = 0;
				}
				else
				{
					setSidebarInterface(6, 12855); // magic tab (ancient =
													// 12855);
					setSidebarInterface(6, 1151);
					ancients = 1;
					playerAncientMagics = true;
				}
			}
		}
			break;
		case 2153:
		{ // Ancient magic altar (temp !!!)
			if (GoodDistance(absX, absY, objectX, objectY, 1) == true)
			{
				if (playerAncientMagics == true)
				{
					setSidebarInterface(6, 1151); // magic tab (ancient =
													// 12855);
					playerAncientMagics = false;
					ancients = 0;
				}
				else
				{
					setSidebarInterface(6, 12855); // magic tab (ancient =
													// 12855);
					setSidebarInterface(6, 1151);
					ancients = 1;
					playerAncientMagics = true;
				}
			}
		}
			break;
		// Search banana tree
		case 2073:
		case 2074:
		case 2075:
		case 2076:
		case 2077:
		case 2078:
			addItem(1963, 1);
			sendMessage("You pick a banana, Seems like a good toy...");
			break;
		// go upstairs
		case -1:
			if (!IsUsingSkill)
			{
				if (objectID == 1747 || objectID == 1750)
				{
					stairs = 1;
					skillX = objectX;
					skillY = objectY;
					stairDistance = 1;
				}
				else if (objectID == 1738)
				{
					stairs = 1;
					skillX = objectX;
					skillY = objectY;
					stairDistance = 2;
				}
				else if (objectID == 1722)
				{
					stairs = 21;
					skillX = objectX;
					skillY = objectY;
					stairDistance = 2;
					stairDistanceAdd = 2;
				}
				else if (objectID == 1734)
				{
					stairs = 10;
					skillX = objectX;
					skillY = objectY;
					stairDistance = 3;
					stairDistanceAdd = 1;
				}
				else if (objectID == 55)
				{
					stairs = 15;
					skillX = objectX;
					skillY = objectY;
					stairDistance = 3;
					stairDistanceAdd = 1;
				}
				else if (objectID == 57)
				{
					stairs = 15;
					skillX = objectX;
					skillY = objectY;
					stairDistance = 3;
				}
				else if (objectID == 1755 || objectID == 5946
						|| objectID == 1757)
				{
					stairs = 4;
					skillX = objectX;
					skillY = objectY;
					stairDistance = 1;
				}
				else if (objectID == 1764)
				{
					stairs = 12;
					skillX = objectX;
					skillY = objectY;
					stairDistance = 1;
				}
				else if (objectID == 2148)
				{
					stairs = 8;
					skillX = objectX;
					skillY = objectY;
					stairDistance = 1;
				}
				else if (objectID == 3608)
				{
					stairs = 13;
					skillX = objectX;
					skillY = objectY;
					stairDistance = 1;
				}
				else if (objectID == 2408)
				{
					stairs = 16;
					skillX = objectX;
					skillY = objectY;
					stairDistance = 1;
				}
				else if (objectID == 5055)
				{
					stairs = 18;
					skillX = objectX;
					skillY = objectY;
					stairDistance = 1;
				}
				else if (objectID == 5131)
				{
					stairs = 20;
					skillX = objectX;
					skillY = objectY;
					stairDistance = 1;
				}
				else if (objectID == 9359)
				{
					stairs = 24;
					skillX = objectX;
					skillY = objectY;
					stairDistance = 1;
					stairDistance = 1;
				}
				else if (objectID == 2492)
				{ /* Essence Mine Portals */
					stairs = 25;
					skillX = objectX;
					skillY = objectY;
					stairDistance = 1;
				}
				else if (objectID == 9356)
				{ // tzhaar door to fight caves
					teleportToX = 2413;
					teleportToY = 5117;
				}
				else if (objectID == 9357)
				{ // tzhaar door from
					teleportToX = 2413;
					teleportToY = 5117;
				}
				else if (objectID == 2406)
				{ /* Lost City Door */
					if (playerEquipment[playerWeapon] == 772)
					{ // Dramen Staff
						stairs = 27;
						skillX = objectX;
						skillY = objectY;
						stairDistance = 1;
					}
					else
					{// Open Door
					}
				}
			}
			break;
		case -2:
			// go downstairs
			if (!IsUsingSkill)
			{
				if (objectID == 1746 || objectID == 1749)
				{
					stairs = 2;
					skillX = objectX;
					skillY = objectY;
					stairDistance = 1;
				}
				else if (objectID == 1740)
				{
					stairs = 2;
					skillX = objectX;
					skillY = objectY;
					stairDistance = 1;
				}
				else if (objectID == 1723)
				{
					stairs = 22;
					skillX = objectX;
					skillY = objectY;
					stairDistance = 2;
					stairDistanceAdd = 2;
				}
				else if (objectID == 1733)
				{
					stairs = 9;
					skillX = objectX;
					skillY = objectY;
					stairDistance = 3;
					stairDistanceAdd = -1;
				}
				else if (objectID == 54)
				{
					stairs = 14;
					skillX = objectX;
					skillY = objectY;
					stairDistance = 3;
					stairDistanceAdd = 1;
				}
				else if (objectID == 56)
				{
					stairs = 14;
					skillX = objectX;
					skillY = objectY;
					stairDistance = 3;
				}
				else if (objectID == 1568 || objectID == 5947
						|| objectID == 6434 || objectID == 1759
						|| objectID == 1754 || objectID == 1570)
				{
					stairs = 3;
					skillX = objectX;
					skillY = objectY;
					stairDistance = 1;
				}
				else if (objectID == 2113)
				{ // Mining guild stairs
					if (playerLevel[MINING] >= 200)
					{
						stairs = 3;
						skillX = objectX;
						skillY = objectY;
						stairDistance = 1;
					}
					else
					{
						sendMessage("You need 200 mining to enter the mining guild.");
					}				
					// Mine copper
					if ((objectID == 2090) || (objectID == 2091))
					{
						// actionAmount++;
						if (actionTimer == 0)
						{
							actionName = "minecopper";
							actionTimer = 15;
							pEmote = 0x271;
							updateRequired = true;
							appearanceUpdateRequired = true;
						}
					}
					// Mine Tin
					if ((objectID == 2094) || (objectID == 2095))
					{
						// actionAmount++;
						if (actionTimer == 0)
						{
							actionName = "minetin";
							actionTimer = 15;
							pEmote = 0x271;
							updateRequired = true;
							appearanceUpdateRequired = true;
						}
					}
					// Mine Iron
					if ((objectID == 2092) || (objectID == 2093))
					{
						// /actionAmount++;
						if (actionTimer == 0)
						{
							actionName = "mineiron";
							actionTimer = 15;
							pEmote = 0x271;
							updateRequired = true;
							appearanceUpdateRequired = true;
						}
					}
					// Mine Silver
					if ((objectID == 2100) || (objectID == 2101))
					{
						// actionAmount++;
						if (actionTimer == 0)
						{
							actionName = "minesilver";
							actionTimer = 15;
							pEmote = 0x271;
							updateRequired = true;
							appearanceUpdateRequired = true;
						}
					}
					// Mine Gold
					if ((objectID == 2098) || (objectID == 2099))
					{
						// actionAmount++;
						if (actionTimer == 0)
						{
							actionName = "minegold";
							actionTimer = 15;
							pEmote = 0x271;
							updateRequired = true;
							appearanceUpdateRequired = true;
						}
					}
					// Mine Coal
					if ((objectID == 2096) || (objectID == 2097))
					{
						// actionAmount++;
						if (actionTimer == 0)
						{
							actionName = "minecoal";
							actionTimer = 15;
							pEmote = 0x271;
							updateRequired = true;
							appearanceUpdateRequired = true;
						}
					}
					// Mine Mithril
					if ((objectID == 2102) || (objectID == 2103))
					{
						// actionAmount++;
						if (actionTimer == 0)
						{
							actionName = "minemithril";
							actionTimer = 15;
							pEmote = 0x271;
							updateRequired = true;
							appearanceUpdateRequired = true;
						}
					}
					// Mine Adamant
					if ((objectID == 2104) || (objectID == 2105))
					{
						// actionAmount++;
						if (actionTimer == 0)
						{
							actionName = "mineadamant";
							actionTimer = 15;
							pEmote = 0x271;
							updateRequired = true;
							appearanceUpdateRequired = true;
						}
					}
					// Mine Rune
					if ((objectID == 2106) || (objectID == 2107))
					{
						// actionAmount++;
						if (actionTimer == 0)
						{
							actionName = "minerunite";
							actionTimer = 15;
							pEmote = 0x271;
							updateRequired = true;
							appearanceUpdateRequired = true;
						}
					}
					// Mine Essence
					if (objectID == 2491)
					{
						// actionAmount++;
						if (actionTimer == 0)
						{
							actionName = "mineessence";
							actionTimer = 15;
							pEmote = 0x271;
							updateRequired = true;
							appearanceUpdateRequired = true;
						}
					}
					// Mine elemental
					if (objectID == 6669 || objectID == 6670
							|| objectID == 6671) // Insert elemental rocks here
					{
						actionName = "mineelemental";
						actionTimer = 15;
						pEmote = 0x271;
						updateRequired = true;
						appearanceUpdateRequired = true;
					}
				}
				else if (objectID == 492)
				{
					stairs = 11;
					skillX = objectX;
					skillY = objectY;
					stairDistance = 2;
				}
				else if (objectID == 2147)
				{
					stairs = 7;
					skillX = objectX;
					skillY = objectY;
					stairDistance = 1;
				}
				else if (objectID == 5054)
				{
					stairs = 17;
					skillX = objectX;
					skillY = objectY;
					stairDistance = 1;
				}
				else if (objectID == 5130)
				{
					stairs = 19;
					skillX = objectX;
					skillY = objectY;
					stairDistance = 1;
				}
				else if (objectID == 9358)
				{
					stairs = 23;
					skillX = objectX;
					skillY = objectY;
					stairDistance = 1;
				}
				else if (objectID == 5488)
				{
					stairs = 28;
					skillX = objectX;
					skillY = objectY;
					stairDistance = 1;
				}
				if (skillX > -1 && skillY > -1)
				{
					IsUsingSkill = true;
				}
			}
			break;
		case 1568:
		case 1569:
		case 1570:
		case 1571:
		case 1759:
		case 1762:
		case 1763:
		case 1764:
		case 2113:
		case 3771:
		case 54:
		case 56:
		case 5947:
		case 6434:
		case 1754:
		case 492:
		case 2147:
		case 5054:
		case 5130:
		case 9358:
		case 5488:
			if (objectX == 3097 && objectY == 3468)
			{
				teleportToX = 3096;
				teleportToY = 9867;
			}
			else
			{
				teleportToX = absX;
				teleportToY = (absY + 6400);
			}
			break;
		case 2559:
			ReplaceObject(objectX, objectY, 1531, -2);
			break;
		// Slayer Mini//
		case 4487:
			heightLevel = 0;
			if (absY == 3535)
			{
				teleportToX = 2463;
				teleportToY = 4963;
			}
			if (absY == 3536)
			{
				teleportToX = 2463;
				teleportToY = 4963;
			}
			break;
		case 4495:
			heightLevel += 1;
			teleportToX = 3417;
			teleportToY = 3541;
			break;
		case 4496:
			heightLevel -= 1;
			teleportToX = 3412;
			teleportToY = 3540;
			break;
		case 9319:
			heightLevel += 1;
			teleportToX = absX;
			teleportToY = absY;
			break;
		case 9320:
			heightLevel -= 1;
			teleportToX = absX;
			teleportToY = absY;
			break;
		case 4494:
			heightLevel -= 1;
			teleportToX = 3438;
			teleportToY = 3538;
			break;
		case 4493:
			heightLevel = 1;
			teleportToX = 3433;
			teleportToY = 3537;
			break;
		case 10527:
			heightLevel = 1;
			if (absY == 3555)
			{
				teleportToX = 3426;
				teleportToY = 3556;
			}
			if (absY == 3556)
			{
				teleportToX = 3426;
				teleportToY = 3555;
			}
			break;
		case 10529:
			heightLevel = 1;
			if (absY == 3555)
			{
				teleportToX = 3427;
				teleportToY = 3556;
			}
			if (absY == 3556)
			{
				teleportToX = 3427;
				teleportToY = 3555;
			}
			break;
		case 5126:
			heightLevel = 2;
			if (absY == 3555)
			{
				teleportToX = 3445;
				teleportToY = 3554;
			}
			if (absY == 3554)
			{
				teleportToX = 3445;
				teleportToY = 3555;
			}
			break;
		// End Of Tower//
		case 5960: // Mage bank lever to wildy
			if (absY == 4712)
			{
				if (!teleblock)
				{
					teleportToX = 3090;
					teleportToY = 3956;
				}
				else if (teleblock)
				{
					sendMessage("A magical force stops you from teleporting.");
				}
			}
			break;
		case 5959: // Wildy lever to mage bank
			if (absY == 3956)
			{
				if (!teleblock)
				{
					teleportToX = 2539;
					teleportToY = 4712;
				}
				else if (teleblock)
				{
					sendMessage("A magical force stops you from teleporting.");
				}
			}
		case 1596:
			if (objectX == 2816)
			{ // Members gate to brimhaven
				ReplaceObject(objectX, objectY, 2630, -3);
			}
			else if (objectX == 3008)
			{
				ReplaceObject(objectX, objectY, 2630, -2);
			}
			else
			{
				ReplaceObject(objectX, objectY, 2630, -3);
			}
			break;
		case 1597:
			if (objectX == 2816)
			{ // Members gate to brimhaven
				ReplaceObject(objectX, objectY, 2630, -3);
			}
			else if (objectX == 3008)
			{
				ReplaceObject(objectX, objectY, 2630, -2);
			}
			else
			{
				ReplaceObject(objectX, objectY, 2630, 0);
			}
			break;
		case 2878: // Mage Arena Bank Pool
			teleportToX = 2509;
			teleportToY = 4689;
			break;
		case 2879: // Mage Arena Staff Place Pool
			teleportToX = 2542;
			teleportToY = 4718;
			break;
		case 2557:
			ReplaceObject(objectX, objectY, objectID = +2, 0);
			break;
		case 1765:
			if (objectX == 3017 && objectY == 3849)
			{
				teleportToX = 2773;
				teleportToY = 9341;
				sendMessage("You climb down the ladder to find a secret cave!");
			}
			break;
		case 2903:
			if (objectX == 2773 && objectY == 9342)
			{
				teleportToX = 3016;
				teleportToY = 3848;
				sendMessage("You climb out of the cave back into the wilderness.");
			}
			break;
		case 2904:
			if (objectX == 2772 && objectY == 9342)
			{
				teleportToX = 3016;
				teleportToY = 3848;
				sendMessage("You climb out of the cave back into the wilderness.");
			}
			break;
		case 393:
			if (objectX == 2790 && objectY == 9341)
			{
				teleportToX = 2800;
				teleportToY = 9341;
				sendMessage("You search the bookcase and crawl through a hidden crack in the rock.");
			}
			break;
		case 2918:
			if (objectX == 2799 && objectY == 9341)
			{
				teleportToX = 2790;
				teleportToY = 9340;
				sendMessage("You crawl through the crack in the rock.");
			}
			break;
		case 733: // Mage bank webs
		{
			if (absX == 3092)
			{
				teleportToX = 3093;
				teleportToY = 3957;
			}
			if (absX == 3093)
			{
				teleportToX = 3092;
				teleportToY = 3957;
			}
			if (absX == 3094)
			{
				teleportToX = 3095;
				teleportToY = 3957;
			}
			if (absX == 3095)
			{
				teleportToX = 3094;
				teleportToY = 3957;
			}
			if (absY == 3950)
			{
				teleportToX = 3158;
				teleportToY = 3952;
			}
			if (absY == 3952)
			{
				teleportToX = 3158;
				teleportToY = 3950;
			}
			if (absY == 3119)
			{
				teleportToX = absX;
				teleportToY = 3118;
			}
			if (absY == 3118)
			{
				teleportToX = absX;
				teleportToY = 3119;
			}
			if (absY == 3957)
			{
				teleportToX = absX;
				teleportToY = 3959;
			}
			if (absY == 3959)
			{
				teleportToX = objectX;
				teleportToY = 3957;
			}
		}
			break;
		case 9707:
			teleportToX = 3105;
			teleportToY = 3956;
			sendMessage("You teleport inside...");
			break;
		case 9706:
			teleportToX = 3105;
			teleportToY = 3951;
			sendMessage("You teleport inside...");
			break;
		case 2321:
			if (absY >= 9487 && absY <= 9495) // Monkey Bars
			{
				if (absY == 9494 && playerLevel[16] >= 120)
				{
					teleportToX = absX;
					teleportToY = 9488;
					sendMessage("You swing across the monkey bars.");
					addSkillXP((1200 * playerLevel[16]), 16);
				}
				else
				{
					sendMessage("You need an agility level of 120 to climb the monkey bars.");
					sendMessage("Goldsmith gauntys and stuff.");
				}
				if (absY == 9488 && playerLevel[16] >= 120
						&& playerEquipment[playerHands] == 776)
				{
					teleportToX = absX;
					teleportToY = 9494;
					sendMessage("You swing accross the monkey bars.");
				}
				else
				{
					sendMessage("You need an agility level of 120 to climb the monkey bars.");
					sendMessage("Goldsmith gauntys and stuff.");
				}
			}
			break;
		case 2303: // Balancing Ledge
			if (absY == 9520 && playerLevel[16] >= 130)
			{
				teleportToX = absX;
				teleportToY = 9512;
				sendMessage("You climb accross the ledge.");
				addSkillXP((1500 * playerLevel[16]), 16);
			}
			else if (absY == 9520 && playerLevel[16] <= 130)
			{
				sendMessage("You need an agility level of 130 to climb this ledge.");
			}
			if (absY == 9512 && playerLevel[16] >= 130)
			{
				teleportToX = absX;
				teleportToY = 9520;
				sendMessage("You climb accros the ledge.");
			}
			else if (absY == 9512 && playerLevel[16] <= 130)
			{
				sendMessage("You need an agility level of 130 to climb this ledge.");
			}
			break;
		case 2558: // Pirate Hut Doors
		case 1557: // Edgeville dungeon gate
		case 1558: // Edgeville dungeon gate
			ReplaceObject(objectX, objectY, objectID + 2, 0);
			break;
		case 1533:
			if (objectX == 3183 && objectY == 3434)
			{
				ReplaceObject(objectX, objectY, 1531, -1);
			}
			else
			{
				ReplaceObject(objectX, objectY, objectID = +2, 0);
			}
			break;
		case 1814:
			if (!teleblock)
			{
				sendMessage("You pull the lever...");
				setAnimation(0x714);
				teleportToX = 3153;
				teleportToY = 3923;
				sendMessage("And get teleported into the wilderness.");
			}
			else if (teleblock)
			{
				sendMessage("A magical force stops you from teleporting to ::home.");
			}
			break;
		case 1815:
			if (!teleblock)
			{
				sendMessage("You pull the lever...");
				setAnimation(0x714);
				teleportToX = 2561;
				teleportToY = 3311;
				sendMessage("And get teleported back to Ardougne!");
			}
			else if (teleblock)
			{
				sendMessage("A magical force stops you from Going Back to ::home...");
			}
			break;
		case 9533:
			if (actionTimer == 0)
			{
				sendMessage("You find some Enhancers!");
				setAnimation(0x320);
				addSkillXP((20 * playerLevel[2]), 2);
				actionTimer = 20;
			}
			break;
		case 1748:
			if (actionTimer == 0)
			{
				teleportToX = 3286;
				teleportToY = 3191;
				heightLevel = 0;
				actionTimer = 2;
			}
			break;
		case 9535:
			if (actionTimer == 0)
			{
				sendMessage("You find some Enhancers!");
				setAnimation(0x320);
				addSkillXP((20 * playerLevel[2]), 2);
				actionTimer = 20;
			}
			break;
		case 4031:
			if (actionTimer == 0)
			{
				sendMessage("You Teleport To The Thing.");
				teleportToX = 3483;
				teleportToY = 9490;
				actionTimer = 200;
			}
			break;
		case 2416:
			if (GoodDistance2(absX, absY, objectX, objectY, 3))
			{
				if (actionTimer == 0)
				{
					sendMessage("Oh Hi.");
					actionTimer = 10;
					setAnimation(1658);
					addSkillXP((1 * playerLevel[2]), 2);
					stillgfx(246, absY, absX);
					updateRequired = true;
					appearanceUpdateRequired = true;
				}
			}
			break;
		case 104:
			if (GoodDistance2(absX, absY, objectX, objectY, 3))
			{
				if (actionTimer == 0)
				{
					sendMessage("You gain some Slayer Exp.");
					actionTimer = 10;
					setAnimation(423);
					addSkillXP((9000 * playerLevel[18]), 18);
					stillgfx(199, absY, absX);
					updateRequired = true;
					appearanceUpdateRequired = true;
				}
			}
			break;
		case 2393:
			if (GoodDistance2(absX, absY, objectX, objectY, 3))
			{
				if (actionTimer == 0)
				{
					sendMessage("Oh hey.");
					actionTimer = 20;
					setAnimation(1658);
					addSkillXP((1 * playerLevel[4]), 4);
					stillgfx(246, absY, absX);
					updateRequired = true;
					appearanceUpdateRequired = true;
				}
			}
			break;
		case 75:
			if (GoodDistance2(absX, absY, objectX, objectY, 3))
			{
				if (actionTimer == 0)
				{
					sendMessage("Oh Hi.");
					actionTimer = 10;
					setAnimation(422);
					addSkillXP((10000 * playerLevel[11]), 11);
					stillgfx(246, absY, absX);
					updateRequired = true;
					appearanceUpdateRequired = true;
				}
			}
		case 4252:
			if (GoodDistance2(absX, absY, objectX, objectY, 3))
			{
				if (actionTimer == 0)
				{
					sendMessage("You Gain Some Herblore, and stuff.");
					actionTimer = 15;
					setAnimation(422);
					addSkillXP((10000 * playerLevel[15]), 15);
					addItem(995, 100000);
					addItem(146, 1);
					addItem(158, 1);
					addItem(164, 1);
					sendMessage("You gain herblore, a super set, and 100k");
					stillgfx(246, absY, absX);
					updateRequired = true;
					appearanceUpdateRequired = true;
				}
			}
		case 103:
			if (GoodDistance2(absX, absY, objectX, objectY, 3))
			{
				if (actionTimer == 10)
				{
					sendMessage("Herblore time!");
					actionTimer = 10;
					setAnimation(422);
					addSkillXP((10000 * playerLevel[8]), 8);
					addItem(995, 150000);
					sendMessage("You gain Some Money.");
					stillgfx(246, absY, absX);
					updateRequired = true;
					appearanceUpdateRequired = true;
				}
			}
		case 154:
			if (GoodDistance2(absX, absY, objectX, objectY, 3))
			{
				if (actionTimer == 10)
				{
					sendMessage("Oh hey.");
					actionTimer = 10;
					setAnimation(422);
					addSkillXP((10000 * playerLevel[16]), 16);
					stillgfx(246, absY, absX);
					updateRequired = true;
					appearanceUpdateRequired = true;
				}
			}
		case 4150: // strguildportal
			if (playerLevel[2] >= 200)
			{
				teleportToX = 2512;
				teleportToY = 3043;
				sendMessage("You teleport to the Strength Guild!");
			}
			else
			{
				sendMessage("You need 200 strength in order to enter this portal.");
			}
			break;
		case 4151: // attackguildportal
			if (playerLevel[0] >= 200)
			{
				teleportToX = 2796;
				teleportToY = 3322;
				sendMessage("You teleport to the Attack Guild!");
			}
			else
			{
				sendMessage("You need 200 Attack in order to enter this portal.");
			}
			break;
		case 4152: // Defence Guild Portal
			if (playerLevel[1] >= 200)
			{
				teleportToX = 2787;
				teleportToY = 9338;
				sendMessage("You teleport to the Defence Guild!");
			}
			else
			{
				sendMessage("You need 200 Defence in order to enter this portal.");
			}
			break;
		case 4154: // Thieving Guild Portal
			if (playerLevel[17] >= 200)
			{
				teleportToX = 3233;
				teleportToY = 9316;
				sendMessage("You teleport to the Thieving Guild!");
			}
			else
			{
				sendMessage("You need 200 Thieving in order to enter this portal.");
			}
			break;
		case 4156: // Magic Guild
			if (playerLevel[6] >= 200)
			{
				teleportToX = 2539;
				teleportToY = 4716;
				sendMessage("You teleport to the Magic Guild!");
			}
			else
			{
				sendMessage("You need 200 Magic in order to enter this portal.");
			}
			break;
		case 6282:
			sendMessage("Kill All The Monsters For A Prize!");
			teleportToX = 2959;
			teleportToY = 9477;
			break;
		case 4157: // ghostportal
			teleportToX = 3660;
			teleportToY = 3507;
			sendMessage("You teleport to the Ghost Quest.");
			break;
		case 2465: // trainportal
			teleportToX = 2883;
			teleportToY = 9800;
			sendMessage("You teleport to the Training Area.");
			break;
		case 4387:
			teleportToX = 2702;
			teleportToY = 9419;
			sendMessage("You join your clan");
			break;
		case 4388:
			teleportToX = 2744;
			teleportToY = 9462;
			sendMessage("You join your clan");
			break;
		case 2474: // pkportal
			teleportToX = 3087;
			teleportToY = 3520;
			sendMessage("You teleport to edge! Go north to pk!");
			sendMessage("PLEASE DON'T TEAM HERE!");
			sendMessage("To check your pk points, type ::mypk!");
			break;
		case 12356:
			teleportToX = 2715;
			teleportToY = 9817;
			sendMessage("START TRAINING!");
			heightLevel = 0;
			break;
		case 9368:
			teleportToX = 2399;
			teleportToY = 5167;
			sendMessage("Let's Do This!");
			break;
		case 9369:
			teleportToX = 2399;
			teleportToY = 5175;
			sendMessage("Wait till the others are ready!");
			break;
		case 2287:
			if (actionTimer == 0)
			{
				addSkillXP((3000 * playerLevel[16]), 16);
				addItem(995, 100000);
				sendMessage("You Gain Some Agility, and get some cash.");
				setAnimation(0x323);
				actionTimer = 10;
				updateRequired = true;
				appearanceUpdateRequired = true;
			}
			break;
		case 3816:
			if (actionTimer == 0)
			{
				addSkillXP((3000 * playerLevel[12]), 12);
				addItem(995, 300000);
				sendMessage("You Gain Some Crafting, and get some Cash!.");
				setAnimation(0x378);
				actionTimer = 10;
				updateRequired = true;
				appearanceUpdateRequired = true;
			}
			break;
		case 2480:
			if (actionTimer == 2)
			{
				sendMessage("Have fun runecrafting!");
				actionTimer = 10;
				setAnimation(818);
				runecraft(1, 3000 * playerLevel[20], 555, 30, 45, 60, 80, 87);
				updateRequired = true;
				appearanceUpdateRequired = true;
			}
			break;
		case 12120:
			if (actionTimer == 0)
			{
				addSkillXP((2000 * playerLevel[15]), 15);
				addItem(146, 1);
				addItem(158, 1);
				addItem(164, 1);
				sendMessage("You Gain Some Herblore, and get some potions!");
				setAnimation(0x378);
				actionTimer = 15;
				updateRequired = true;
				appearanceUpdateRequired = true;
			}
			break;
		case 2193:
			if (actionTimer == 0)
			{
				addSkillXP((4000 * playerLevel[11]), 11);
				addItem(995, 100000);
				sendMessage("You Gain Some FireMaking, and get some Cash!");
				setAnimation(733);
				actionTimer = 10;
				updateRequired = true;
				appearanceUpdateRequired = true;
			}
			break;
		case 8972:
			teleportToX = 2580;
			teleportToY = 9745;
			sendMessage("Welcome to the Unlicensed chill zone, its pvp, and no server rules apply here!");
			break;
		case 7324:
			if (playerIsMember == 1)
				teleportToX = 3346;
			teleportToY = 3251;
			if (playerIsMember == 0)
				sendMessage("Become a Member By Giveaways Of Member, And By Applying On Forums.");
			break;
		case 9118:
			RemoveAllWindows();
			break;
		case 8956: // ladder at skill
			teleportToX = 2846;
			teleportToY = 3432;
			sendMessage("Catch Fish, Cook Them, And Thieve!");
			break;
		case 9745: // ladder at skill
			teleportToX = 2928;
			teleportToY = 3253;
			sendMessage("Welcome back to Skills!");
			break;
		case 1276:
		case 1277:
		case 1278:
		case 1279:
		case 1280:
		case 1282:
		case 1283:
		case 1284:
		case 1285:
		case 1286:
		case 1289:
		case 1290:
		case 1291:
		case 1315:
		case 1316:
		case 1318:
		case 1319:
		case 1330:
		case 1331:
		case 1332:
		case 1365:
		case 1383:
		case 1384:
		case 2409:
		case 3033:
		case 3034:
		case 3035:
		case 3036:
		case 3881:
		case 3882:
		case 3883:
		case 5902:
		case 5903:
		case 5904:
			Woodcutting("tree", 1, (2000 * playerLevel[8]), 995, 150000,
					objectID, objectX, objectY, 875);
			break;
		case 1281:
		case 3037:
			Woodcutting("oak", 15, (4000 * playerLevel[8]), 995, 300000,
					objectID, objectX, objectY, 875);
			break;
		case 1308:
		case 5551:
		case 5552:
		case 5553:
			Woodcutting("willow", 35, (8000 * playerLevel[8]), 995, 550000,
					objectID, objectX, objectY, 875);
			break;
		case 1307:
		case 4674:
			Woodcutting("maple", 45, (9500 * playerLevel[8]), 995, 700000,
					objectID, objectX, objectY, 875);
			break;
		case 1292:
			Woodcutting("dramen", 85, (15000 * playerLevel[8]), 772, 1,
					objectID, objectX, objectY, 875);
			break;
		case 1306:
			Woodcutting("magic", 85, (15000 * playerLevel[8]), 995, 4500000,
					objectID, objectX, objectY, 875);
			break;
		case 4121:
		{
			actionTimer = 30;
			sendMessage("Congratulations, You Have Won, Enjoy your reward!");
			teleportToX = 2907;
			teleportToY = 9710;
			addItem(995, 150000000);
		}
			break;
		case 6774:
			if (actionTimer == 0)
			{
				actionTimer = 30;
				sendMessage("Thanks for playing, enjoy a Christmas cracker!");
				teleportToX = 2907;
				teleportToY = 9710;
				addItem(962, 1);
			}
			break;
		case 61:
			setSkillLevel(5, getLevelForXP(playerXP[5]), playerXP[5]);
			playerLevel[5] = getLevelForXP(playerXP[5]);
			refreshSkills();
			setAnimation(645);
			sendMessage("You Recharge Your Prayer.");
			updateRequired = true;
			appearanceUpdateRequired = true;
			break;
		case 2111:
			if (actionTimer == 0)
			{
				addSkillXP((2200 * playerLevel[14]), 14);
				addItem(995, 100000);
				sendMessage("You Gain Some Mining, and Get Some Cash!!");
				setAnimation(624);
				actionTimer = 10;
				updateRequired = true;
				appearanceUpdateRequired = true;
			}
			break;
		case 2406:
			if (playerEquipment[playerWeapon] == 772)
			{
				sendMessage("Welcome to the Lost City!");
				stairs = 27;
				skillX = objectX;
				skillY = objectY;
				stairDistance = 1;
			}
			break;
		case 2360: // 1
			if (playerHasItemAmount(608, 1))
			{
				sendMessage("There is no need to have two Crumpled Scrolls");
			}
			else
			{
				actionTimer = 30;
				addItem(Item2.randomwaterGame(), 1);
				sendFrame126("You found a @gre@Crumpled Scroll!", 357);
				sendFrame164(356);
			}
			break;
		case 1994: // 2
			if (playerHasItemAmount(786, 1))
			{
				sendMessage("There is no need to have two Hazelmere's Scrolls");
			}
			else
			{
				actionTimer = 30;
				addItem(Item2.randomwaterGame(), 1);
				sendFrame126("You found a @gre@Hazelmere's Scroll@bla@!", 357);
				sendFrame164(356);
			}
			break;
		case 2194: // 3
			if (playerHasItemAmount(1505, 1))
			{
				sendMessage("There is no need to have two Magic Scrolls");
			}
			else
			{
				actionTimer = 30;
				addItem(Item2.randomwaterGame(), 1);
				sendFrame126("You found a @gre@Magic Scroll!", 357);
				sendFrame164(356);
			}
			break;
		case 2079: // 4
			if (playerHasItemAmount(2396, 1))
			{
				sendMessage("There is no need to have two Spell Scrolls");
			}
			else
			{
				actionTimer = 30;
				addItem(Item2.randomwaterGame(), 1);
				sendFrame126("You found a @gre@Spell Scroll!", 357);
				sendFrame164(356);
			}
			break;
		case 2197: // 5
			if (playerHasItemAmount(7273, 2))
			{
				sendMessage("There is no need to have 2 Caskets.");
			}
			else
			{
				actionTimer = 30;
				addItem(7273, 1);
				sendFrame126("You found a @gre@Scroll!", 357);
				sendFrame164(356);
			}
			break;
		case 2195: // 6 it teles u away
			deleteItem(786, getItemSlot(786), 1);
			deleteItem(608, getItemSlot(608), 1);
			deleteItem(1505, getItemSlot(1505), 1);
			deleteItem(7273, getItemSlot(7273), 1);
			deleteItem(2396, getItemSlot(2396), 1);
			teleportToX = 3500;
			teleportToY = 3314;
			sendFrame126("Wrong One, you will have to start all again!", 357);
			sendFrame164(356);
			sendMessage("0 NIEN 0 NIEN!");
			break;
		case 1995: // 7 it teles u away
			deleteItem(786, getItemSlot(786), 1);
			deleteItem(608, getItemSlot(608), 1);
			deleteItem(1505, getItemSlot(1505), 1);
			deleteItem(7273, getItemSlot(7273), 1);
			deleteItem(2396, getItemSlot(2396), 1);
			teleportToX = 2401;
			teleportToY = 5084;
			sendFrame126("Wrong One, you will have to start all again!", 357);
			sendFrame164(356);
			sendMessage("0 NIEN 0 NIEN!");
			break;
		case 2191: // 8 it teles u away
			deleteItem(786, getItemSlot(786), 1);
			deleteItem(608, getItemSlot(608), 1);
			deleteItem(1505, getItemSlot(1505), 1);
			deleteItem(7273, getItemSlot(7273), 1);
			deleteItem(2396, getItemSlot(2396), 1);
			teleportToX = 2456;
			teleportToY = 4774;
			sendFrame126("Wrong One, you will have to start all again!", 357);
			sendFrame164(356);
			sendMessage("0 NIEN 0 NIEN!");
			break;
		case 2198: // 9 it teles u away
			deleteItem(786, getItemSlot(786), 1);
			deleteItem(608, getItemSlot(608), 1);
			deleteItem(1505, getItemSlot(1505), 1);
			deleteItem(7273, getItemSlot(7273), 1);
			deleteItem(2396, getItemSlot(2396), 1);
			teleportToX = 3084;
			teleportToY = 3524;
			sendFrame126("Wrong One, you will have to start all again!", 357);
			sendFrame164(356);
			sendMessage("0 NIEN 0 NIEN!");
			break;
		case 2196: // 10 it teles u away
			deleteItem(786, getItemSlot(786), 1);
			deleteItem(608, getItemSlot(608), 1);
			deleteItem(1505, getItemSlot(1505), 1);
			deleteItem(7273, getItemSlot(7273), 1);
			deleteItem(2396, getItemSlot(2396), 1);
			teleportToX = 3497;
			teleportToY = 3573;
			sendFrame126("Wrong One, you will have to start all again!", 357);
			sendFrame164(356);
			sendMessage("0 NIEN - NIEN!");
			break;
		case 62:
			if (playerHasItemAmount(608, 1) && playerHasItemAmount(786, 1)
					&& playerHasItemAmount(7273, 1)
					&& playerHasItemAmount(2396, 1)
					&& playerHasItemAmount(1505, 1))
			{
				deleteItem(786, getItemSlot(786), 1);
				deleteItem(608, getItemSlot(608), 1);
				deleteItem(1505, getItemSlot(1505), 1);
				deleteItem(7273, getItemSlot(7273), 1);
				deleteItem(2396, getItemSlot(2396), 1);
				teleportToX = 2752;
				teleportToY = 3515;
				sendMessage("Good Job you have saved Merlin from that Magic Crystal");
				sendMessage("Congratulations, you have just finished the Saving Merlin Miniquest!");
				addItem(13459, 1);
				addItem(14140, 1);
				addItem(14139, 1);
			}
			else
			{
				sendMessage("In order to get the reward and finish the minigame you must find the 5 missing");
				sendMessage("Scrolls");
			}
			break;
		case 4950:
			teleportToX = 3506;
			teleportToY = 3312;
			sendMessage("Wrong one!");
			break;
		// runecrafting cheez
		case 2481: // earth
			runecraft(9, 300 * playerLevel[20], 557, 45, 55, 65, 85, 90);
			break;
		case 2482: // fire
			runecraft(14, 350 * playerLevel[20], 554, 50, 60, 70, 80, 90);
			break;
		case 2483: // body
			runecraft(20, 400 * playerLevel[20], 559, 55, 65, 75, 85, 95);
			break;
		case 2484: // cosmic
			runecraft(27, 450 * playerLevel[20], 564, 60, 70, 80, 90, 99);
			break;
		case 2487: // chaos
			runecraft(35, 500 * playerLevel[20], 562, 60, 70, 80, 90, 99);
			break;
		case 2486: // nat
			runecraft(44, 550 * playerLevel[20], 561, 60, 70, 80, 90, 99);
			break;
		case 2485: // law
			runecraft(54, 600 * playerLevel[20], 557, 65, 75, 85, 95, 99);
			break;
		case 7139:
			if (objectX == 3047 && objectY == 4825) // air rift
			{
				sendMessage("You step into the mysterious rift and end up in the air temple");
				teleportToX = 2845;
				teleportToY = 4832;
			}
			break;
		case 2182:
			if (objectX == 3231 && objectY == 3501)
			{
				sendMessage("You take the loot, and RUN!");
				addItem(6818, 1);
				teleportToX = 3233;
				teleportToY = 9315;
			}
			break;
		case 7137:
			if (objectX == 3051 && objectY == 4833) // water rift
			{
				sendMessage("You step into the mysterious rift and end up in the water temple");
				teleportToX = 2713;
				teleportToY = 4836;
			}
			break;
		case 7130:
			if (objectX == 3031 && objectY == 4825) // earth rift
			{
				sendMessage("You step into the mysterious rift and end up in the earth temple");
				teleportToX = 2660;
				teleportToY = 4839;
			}
			break;
		case 7129:
			if (objectX == 3029 && objectY == 4830) // fire rift
			{
				sendMessage("You step into the mysterious rift and end up in the fire temple");
				teleportToX = 2584;
				teleportToY = 4836;
			}
			break;
		case 7131:
			if (objectX == 3039 && objectY == 4821) // body rift
			{
				sendMessage("You step into the mysterious rift and end up in the body temple");
				teleportToX = 2527;
				teleportToY = 4833;
			}
			break;
		case 7135:
			if (objectX == 3049 && objectY == 4839) // law rift
			{
				sendMessage("You step into the mysterious rift and end up in the law temple");
				teleportToX = 2464;
				teleportToY = 4834;
			}
			break;
		case 7132:
			sendMessage("You step into the mysterious rift and end up in the cosmic temple");
			teleportToX = 2162;
			teleportToY = 4833;
			break;
		case 2467:
			if (objectX == 3668 && objectY == 3471) // cosmic rift
			{
				sendMessage("You teleport to the Skill Area!");
				teleportToX = 2515;
				teleportToY = 3044;
			}
			break;
		case 7134:
			if (objectX == 3044 && objectY == 4842) // chaos rift
			{
				sendMessage("You step into the mysterious rift and end up in the chaos temple");
				teleportToX = 2269;
				teleportToY = 4843;
			}
			break;
		case 1747:
		case 1750:
		case 1738:
		case 1722:
		case 1734:
		case 55:
		case 57:
		case 5946:
		case 1757:
		case 2148:
		case 3608:
		case 2408:
		case 5055:
		case 5131:
		case 9359:
		case 2492:
		case 5280:
		case 6707:
		case 6706:
		case 6705:
		case 6704:
		case 6703:
		case 6702:
		case 4772:
			startAnimation(828);
			heightLevel += 1;
			teleportToX = absX;
			teleportToY = absY;
			break;
		case 6672: // Climbing up rocks
		case 6673:
			if (objectX == 3239 && absX > objectX)
			{
				startAnimation(740);
				actionTimer = 5;
				Climbing = true;
				ClimbStage = 1;
			}
			else if (objectX == 3239 && absX < objectX)
			{
				viewTo(absX - 1, absY);
				startAnimation(740);
				Climbing = true;
				ClimbStage = 100;
			}
			else if (objectX == 3240 && absX > objectX)
			{
				startAnimation(740);
				actionTimer = 5;
				Climbing = true;
				ClimbStage = 1;
			}
			else if (objectX == 3240 && absX < objectX)
			{
				viewTo(absX - 1, absY);
				startAnimation(740);
				Climbing = true;
				ClimbStage = 100;
			}
			break;
		}
	}

	public void objectClick2(int objectID, int objectX, int objectY)
	{
		switch (objectID)
		{
		case 2213:
		case 2214:
		case 2566:
		case 3045:
		case 5276:
		case 6084:
		case 11758:
			openUpBank();
			break;
		case 2562:
			TheifStall("Gem stall", "and receive 1m.", 100,
					7000 * playerLevel[17], 995, 1000000, 30, 0x340);
			break;
		case 4878:
			TheifStall("Scimitar stall", "and receive 200k", 40,
					750 * playerLevel[17], 995, 200000, 30, 0x340);
			break;
		case 4877:
			TheifStall("Magic stall", "and receive 300k", 20,
					800 * playerLevel[17], 995, 300000, 30, 0x340);
			break;
		case 4876:
			TheifStall("General stall", "and receive 100k", 1,
					900 * playerLevel[17], 995, 100000, 15, 0x340);
			break;
		case 2560:
			TheifStall("Silk stall", "and receive 750k", 80,
					1000 * playerLevel[17], 995, 750000, 30, 0x340);
			break;
		case 4705:
			TheifStall("Fish stall", "and receive a manta ray", 1,
					800 * playerLevel[17], 389, 1, 30, 0x340);
			break;
		case 4706:
			TheifStall("Veg Stall", "and receive 5 Million Gp.", 200,
					1500 * playerLevel[17], 995, 5000000, 30, 0x340);
			break;
		case 2565:
			TheifStall("Silver stall", "and receive 400k", 60,
					950 * playerLevel[17], 995, 400000, 30, 0x340);
			break;
		case 635:
			TheifStall(".", ".", 1, 5 * playerLevel[17], 995, 250, 30, 0x340);
			break;
		case 1739:
			heightLevel++;
			break;
		}
	}

	public void objectClick3(int objectID, int objectX, int objectY)
	{
		switch (objectID)
		{
		case 1739:
			heightLevel--;
			break;
		}
	}

	public int teleReq = 0;
	public String teleLoc = "";
	public boolean teleOtherScreen = false;

	public void teleOtherRequest(String teleLocation, int player)
	{
		String telePlayer = PlayerHandler.players[player].username;
		setText(telePlayer, 12558);
		setText(teleLocation, 12560);
		showInterface(12468);
		teleReq = player;
		teleLoc = teleLocation;
		teleOtherScreen = true;
	}

	private boolean HasArrows = false;

	public void DeleteArrow()
	{
		if (playerEquipmentN[playerArrows] == 0)
		{
			deleteequiment(playerEquipment[playerArrows], playerArrows);
		}
		if (playerEquipment[playerWeapon] != 4212
				&& playerEquipmentN[playerArrows] != 0)
		{
			outStream.createFrameVarSizeWord(34);
			outStream.putShort(1688);
			outStream.put(playerArrows);
			outStream.putShort(playerEquipment[playerArrows] + 1);
			if (playerEquipmentN[playerArrows] - 1 > 254)
			{
				outStream.put(255);
				outStream.putInt(playerEquipmentN[playerArrows] - 1);
			}
			else
			{
				outStream.put(playerEquipmentN[playerArrows] - 1); // amount
			}
			outStream.endFrameVarSizeWord();
			playerEquipmentN[playerArrows] -= 1;
		}
		updateRequired = true;
		appearanceUpdateRequired = true;
	}

	public void CheckArrows()
	{
		for (int k = 880; k < 893; k++)
		{
			if (playerEquipment[playerArrows] == k)
			{
				HasArrows = true;
			}
			else if (playerEquipment[playerWeapon] == 4212)
			{
				HasArrows = true;
			}
			else
			{
				HasArrows = false;
			}
		}
	}

	public boolean wearing = false;

	public void sendSafeZoneText()
	{
		if (nonWild())
		{
			outStream.createFrame(208);
			outStream.putWordBigEndian_dup(197);
			setText("@gre@Safe", 199);
		}
		else
		{
			outStream.createFrame(208);
			outStream.putWordBigEndian_dup(197);
			setText("@red@Wild", 199);
		}
	}

	public void frame60(int i1, int i2, int i3)
	{
		outStream.createFrame(60);
		outStream.put(i1);
		outStream.putByteC(i2);
		outStream.put(i3);
	}

	public void frame60rename(int cameraX, int cameraY, int jFrame)
	{
		outStream.createFrame(60);
		outStream.put(cameraX);
		outStream.putByteC(cameraY);
		outStream.put(jFrame);
	}

	public void frame8(int i1, int i2)
	{
		outStream.createFrame(8);
		outStream.putWordBigEndianA(i1); // interface
		outStream.putShort(i2); // weapon id being drawn
		sendMessage("Frame 8 tested");
	}

	public void frame64(int i1, int i2)
	{
		outStream.createFrame(64);
		outStream.putByteS(i1);
		outStream.putByteA(i2);
		sendMessage("Frame 64 tested");
	}

	public void frame72(int i1) // logs you out :S
	{
		outStream.createFrame(72);
		outStream.putShortBE(i1);
		sendMessage("Frame 72 tested");
	}

	public void frame74(int i)
	{
		outStream.createFrame(74);
		outStream.putShortBE(i);
	}

	public void frame121(int i1, int i2)
	{
		outStream.createFrame(121);
		outStream.putShort(i1);
		outStream.putByteS(i2);
		sendMessage("Frame 121 tested");
	}

	public void frame122(int i1, int i2)
	{
		outStream.createFrame(122);
		outStream.putWordBigEndianA(i1); // interface
		outStream.putWordBigEndianA(i2); // colour stuff
		sendMessage("Frame 122 tested");
	}

	public void frame166(int i1, int i2, int i3, int i4, int i5)
	{
		outStream.createFrame(166);
		outStream.put(i1);
		outStream.put(i2);
		outStream.putShort(i3); // the camera height where it will end
		outStream.put(i4); // the camera moving speed
		outStream.put(i5); // if this goes above 100 it does something? :O
		sendMessage("Testing Camera Angle");
	}

	public void frame177(int i1, int i2, int i3, int i4, int i5)
	{
		outStream.createFrame(177);
		outStream.put(i1);
		outStream.put(i2);
		outStream.putShort(i3); // the height it will be viewing to
		outStream.put(i4); // the camera speed? movement? dunno yet
		outStream.put(i5);
	}

	public void frame70(int i1, int i2, int i3)
	{
		outStream.createFrame(70);
		outStream.putShort(i1); // offset X
		outStream.putShortBE(i2); // offset Y
		outStream.putShortBE(i3); // interface, definatly.
		sendMessage("Frame 70 tested");
		updateRequired = true;
		appearanceUpdateRequired = true;
	}

	public void framevar70(int i1, int i2, int i3) // no idea
	{
		outStream.createFrameVarSize(70);
		outStream.putShort(i1);
		outStream.putShortBE(i2);
		outStream.putShortBE(i3);
		sendMessage("Framevar 70 tested");
	}

	public void frame240(int i1)
	{
		outStream.createFrame(240);
		outStream.putShort(i1);
		sendMessage("Frame 240 tested");
		updateRequired = true;
		appearanceUpdateRequired = true;
	}

	public void frame110(int i1)
	{
		outStream.createFrame(110);
		outStream.put(i1);
		sendMessage("Frame 110 tested");
		updateRequired = true;
		appearanceUpdateRequired = true;
	}

	public void frame106(int i1) // changes selected sidebar!
	{
		outStream.createFrame(106);
		outStream.putByteC(i1);
		sendMessage("Frame 106 tested");
		updateRequired = true;
		appearanceUpdateRequired = true;
	}

	public void frame24(int i1) // Nouish: Flash sidebar icon (?)
	{
		outStream.createFrame(24);
		outStream.putByteA(i1);
		sendMessage("Frame 24 tested");
		updateRequired = true;
		appearanceUpdateRequired = true;
	}

	public void frame142(int i1)
	{
		outStream.createFrame(142);
		outStream.putShortBE(i1);
		sendMessage("Frame 142 tested");
		updateRequired = true;
		appearanceUpdateRequired = true;
	}

	public void frame142d(int i1)
	{
		outStream.createFrame(142);
		outStream.putWordBigEndian_dup(i1);
		sendMessage("Frame 142d tested");
		updateRequired = true;
		appearanceUpdateRequired = true;
	}

	public void frame254(int i1, int i2, int i3, int i4, int i5)
	{
		outStream.createFrame(254);
		outStream.put(i1);
		if (i1 == 1)
		{
			outStream.putShort(i2);
		}
		if (i1 >= 2 && i1 <= 6)
		{
			outStream.putShort(i3);
			outStream.putShort(i4);
			outStream.put(i5);
		}
		if (i1 == 10)
		{
			outStream.putShort(i2);
		}
		sendMessage("Frame 254 tested");
		updateRequired = true;
		appearanceUpdateRequired = true;
	}

	public void frame254skull(int i1, int i2)
	{
		outStream.createFrame(254);
		outStream.put(i1);
		outStream.putShort(i2);
	}

	public void frame35(int i1, int i2, int i3, int i4) // earthquake
	{
		outStream.createFrame(35);
		outStream.put(i1);
		outStream.put(i2);
		outStream.put(i3);
		outStream.put(i4);
		sendMessage("Frame 35 tested");
		updateRequired = true;
		appearanceUpdateRequired = true;
	}

	public void frame230(int i1, int i2, int i3, int i4)
	{
		outStream.createFrame(230);
		outStream.putWordA(i1);
		outStream.putShort(i2); // interface id?
		outStream.putShort(i3);
		outStream.putWordBigEndianA(i4); // junk? not sure
		updateRequired = true;
		appearanceUpdateRequired = true;
	}

	public void frame114(int i1) // system update
	{
		outStream.createFrame(114);
		outStream.putShortBE(i1);
		sendMessage("Frame 114 tested");
		updateRequired = true;
		appearanceUpdateRequired = true;
	}

	public void frame174(int i1, int i2, int i3)
	{
		outStream.createFrame(174);
		outStream.putShort(i1);
		outStream.put(i2);
		outStream.putShort(i3);
		sendMessage("Frame 174 tested");
		updateRequired = true;
		appearanceUpdateRequired = true;
	}

	public void frame246(int i1, int i2, int i3)
	{
		outStream.createFrame(246);
		outStream.putShortBE(i1);
		outStream.putShort(i2);
		outStream.putShort(i3);
		flushOutStream();
		sendMessage("Frame 246 tested");
	}

	public void frame171(int i1, int i2)
	{
		outStream.createFrame(171);
		outStream.put(i1);
		outStream.putShort(i2);
		flushOutStream();
		sendMessage("Frame 171 tested");
	}

	public void frame99(int i1) // makes minimap nonclickable etc.!!! 0 = unlock
								// 2 = black above 2 = locked - Mod
	{
		outStream.createFrame(99);
		outStream.put(i1);
		updateRequired = true;
		appearanceUpdateRequired = true;
	}

	public void frame218(int i1)
	{
		outStream.createFrame(218);
		outStream.putWordBigEndianA(i1);
		sendMessage("Frame 218 tested");
		updateRequired = true;
		appearanceUpdateRequired = true;
	}

	public void frame61(int i1)
	{
		outStream.createFrame(61);
		outStream.put(i1);
		sendMessage("Frame 61 tested");
		updateRequired = true;
		appearanceUpdateRequired = true;
	}

	public void frame87(int i1, int i2) // can't go into 7500+ hmm - links with
										// 36
	{
		outStream.createFrame(87);
		outStream.putShortBE(i1);
		outStream.putDWord_v2(i2);
		sendMessage("Frame 87 tested");
		updateRequired = true;
		appearanceUpdateRequired = true;
	}

	public void frame36(int i1, int i2) // can't go into 7500+ hmm - links with
										// 87
	{
		outStream.createFrame(36);
		outStream.putShortBE(i1);
		outStream.put(i2);
		sendMessage("Frame 36 tested");
		updateRequired = true;
		appearanceUpdateRequired = true;
	}

	public void frame214(long i1) // replaces every name on the ignore list with
									// the one sent to client :O
	{
		outStream.createFrame(214);
		outStream.putLong(i1);
		sendMessage("Frame 214 tested");
		updateRequired = true;
		appearanceUpdateRequired = true;
	}

	public void frame187() // loads enter name interface
	{
		outStream.createFrame(187);
		sendMessage("Frame 187 tested");
		updateRequired = true;
		appearanceUpdateRequired = true;
	}

	public void frame27() // loads enter amount interface
	{
		outStream.createFrame(27);
		sendMessage("Frame 27 tested");
		updateRequired = true;
		appearanceUpdateRequired = true;
	}

	public void frame65() // npc updating frame ;)
	{
		outStream.createFrame(65);
		sendMessage("Frame 65 tested");
		updateRequired = true;
		appearanceUpdateRequired = true;
	}

	public void frame68() // turns split private chat off
	{
		outStream.createFrame(68);
		sendMessage("Frame 68 tested");
		updateRequired = true;
		appearanceUpdateRequired = true;
	}

	public void frame78() // tested, not a fucking clue =\
	{
		outStream.createFrame(78);
		sendMessage("Frame 78 tested");
		updateRequired = true;
		appearanceUpdateRequired = true;
	}

	public void frame81() // player updating r0fl
	{
		outStream.createFrame(81);
		sendMessage("Frame 81 tested");
		updateRequired = true;
		appearanceUpdateRequired = true;
	}

	public void frame1() // cancels all player and npc emotes within area!
	{
		outStream.createFrame(1);
		updateRequired = true;
		appearanceUpdateRequired = true;
	}

	// j frames:
	public void frame160(int i1, int i2, int i3) // objects according to
													// whitefang, dunno what
													// though hmm
	{
		outStream.createFrame(85);
		outStream.putByteC(currentY & ~7); // packetTileCoordY
		outStream.putByteC(currentX & ~7); // packetTileCoordX
		outStream.createFrame(160);
		outStream.putByteA(i1);
		outStream.putByteA(i2);
		outStream.putWordA(i3);
		updateRequired = true;
		appearanceUpdateRequired = true;
		sendMessage("Frame 160 tested");
	}

	public void frame117(int i1, int i2, int i3, int i4, int i5, int i6,
			int i7, int i8, int i9, int i10, int i11) // moving graphics
	{
		outStream.createFrame(85);
		outStream.putByteC(currentY & ~7); // packetTileCoordY
		outStream.putByteC(currentX & ~7); // packetTileCoordX
		outStream.createFrame(117);
		outStream.put(i1);
		outStream.put(i2);
		outStream.put(i3);
		outStream.putShort(i4);
		outStream.putShort(i5);
		outStream.put(i6);
		outStream.put(i7);
		outStream.putShort(i8);
		outStream.putShort(i9);
		outStream.put(i10);
		outStream.put(i11);
		sendMessage("Frame 117 tested");
		updateRequired = true;
		appearanceUpdateRequired = true;
	}

	public void frame105(int v1, int v2, int v3)
	{
		outStream.createFrame(85);
		outStream.putByteC(currentY & ~7); // packetTileCoordY
		outStream.putByteC(currentX & ~7); // packetTileCoordX
		outStream.createFrame(105);
		outStream.put(v1);
		outStream.putShort(v2); // array packet
		outStream.put(v3);
		sendMessage("Frame 105 tested");
	}

	public void frame105_60(int v1, int v2, int v3)
	{
		outStream.createFrameVarSizeWord(60);
		outStream.put(105);
		outStream.put(v1);
		outStream.putShort(v2); // array packet
		outStream.put(v3);
		outStream.endFrameVarSizeWord();
		sendMessage("Frame 105 (60) tested");
	}

	public void frame44(int i1, int i2, int i3)
	{
		outStream.createFrame(85);
		outStream.putByteC(currentY & ~7); // packetTileCoordY
		outStream.putByteC(currentX & ~7); // packetTileCoordX
		outStream.createFrame(44);
		outStream.putWordBigEndianA(i1);
		outStream.putShort(i2);
		outStream.put(i3);
		sendMessage("Frame 44 tested");
	}

	public void frame44_60(int i1, int i2, int i3)
	{
		outStream.createFrameVarSizeWord(60);
		outStream.put(44);
		outStream.putWordBigEndianA(i1);
		outStream.putShort(i2);
		outStream.put(i3);
		outStream.endFrameVarSizeWord();
		sendMessage("Frame 44 (60) tested");
	}

	/* END OF TESTING FRAMES */
	public int hasegg = 0;

	public void Teleblock()
	{
		teleblock = true;
		sendMessage("A teleblock has been cast on you!");
		stillgfx(345, absY, absX);
	}

	/* CW */
	public String Winner = "Nobody";
	public int CWA = 0;
	public int CWT = 0;
	public int SS;
	public int ZS;
	public boolean playerIsSara = false;
	public boolean playerIsZammy = false;
	public boolean CWon = false;
	/* MISC STUFF */
	public boolean duelring = false;
	public boolean ancientstele = false;
	public int ancients = 0;
	public boolean teleblock = false;
	/* PRAYER STUFF */
	public int currentpray = playerLevel[5];
	public int maxpray = getLevelForXP(playerXP[5]);
	public int PrayerDrain = 0;
	public int PrayerTimer = 0;
	public int NewDrain = 0;
	public boolean Str1 = false;
	public boolean Str2 = false;
	public boolean Str3 = false;
	public boolean Skin = false;
	public boolean Rock = false;
	public boolean Steel = false;
	public boolean Clarity = false;
	public boolean Reflex2 = false;
	public boolean Reflex3 = false;
	public boolean ProtItem = false;
	public boolean RapidRestore = false;
	public boolean RapidHeal = false;
	public boolean Retribution = false;
	public boolean Redemption = false;
	public boolean DrainPray = false;
	public boolean ProtMage = false;
	public boolean ProtRange = false;
	public boolean ProtMelee = false;
	public boolean Smite = false;
	public boolean Noprayer = true;

	public void ResetProtPrayers()
	{
		ProtMage = false;
		ProtRange = false;
		ProtMelee = false;
		Str1 = false;
		Str2 = false;
		Str3 = false;
		Skin = false;
		Rock = false;
		Steel = false;
		Clarity = false;
		Reflex2 = false;
		Reflex3 = false;
		ProtItem = false;
		RapidRestore = false;
		RapidHeal = false;
		Retribution = false;
		Redemption = false;
	}

	public void noprayer()
	{
		if (ProtMage == false && ProtRange == false && ProtMelee == false
				&& Str1 == false && Str2 == false && Str3 == false
				&& Skin == false && Rock == false && Steel == false
				&& Clarity == false && Reflex2 == false && Reflex3 == false
				&& ProtItem == false && RapidRestore == false
				&& RapidHeal == false && Retribution == false
				&& Redemption == false)
		{
			Noprayer = true;
			DrainPray = false;
		}
		else
		{
			Noprayer = false;
		}
	}

	public void newdrain()
	{
		int OldDrain = PrayerDrain;
		if (NewDrain > OldDrain && Noprayer == false)
		{
			PrayerDrain = OldDrain;
		}
		else if (NewDrain <= OldDrain || Noprayer == true)
		{
			PrayerDrain = NewDrain;
		}
	}

	/* END OF PRAYER STUFF */
	public void setSetting(int settingID, int value)
	{
		outStream.createFrame(36);
		outStream.putShortBE(settingID);
		outStream.put(value);
	}

	public void levelup(int skill)
	{
		switch (skill)
		{
		case 0: // Attack levelup
			sendFrame164(6247);
			stillgfx(199, absX, absY);
			sendFrame126("Congratulations, you just advanced an Attack level!",
					6248);
			sendFrame126("Your attack level is now " + playerLevel[0] + " .",
					6249);
			sendMessage("You've just advanced an Attack level! You have reached level "
					+ playerLevel[0] + ".");
			NpcDialogueSend = true;
			if (playerLevel[0] >= 200)
			{
				sendMessage("Congratulations, you just received Attack skill capes and hood!");
				PlayerHandler.messageToAll = (username + " has just gotten "
						+ playerLevel[0] + " Attack!");
			}
			break;
		case 1: // Defence
			sendMessage("You've just advanced a Defence level! You have reached level "
					+ playerLevel[1] + ".");
			if (playerLevel[1] >= 199)
			{
				PlayerHandler.messageToAll = (username + " has just gotten "
						+ playerLevel[1] + " Defence");
			}
			break;
		case 2: // Strength
			stillgfx(199, absX, absY);
			sendMessage("You've just advanced a Strength level! You have reached level "
					+ playerLevel[2] + ".");
			if (playerLevel[2] >= 199)
			{
				PlayerHandler.messageToAll = (username + " has just gotten "
						+ playerLevel[2] + " Strength!");
			}
			break;
		case 3: // Hitpoints
			sendFrame164(6216);
			stillgfx(199, absX, absY);
			sendFrame126(
					"Congratulations, you just advanced a Hitpoints level!",
					6217);
			sendFrame126(
					"Your hitpoints level is now " + playerLevel[3] + " .",
					6218);
			sendMessage("Congratulations, you just advanced a Hitpoints level.");
			NpcDialogueSend = true;
			if (playerLevel[3] >= 200)
			{
				sendMessage("Congratulations, you just received Hitpoints skill capes and hood!");
				PlayerHandler.messageToAll = (username + " has just gotten "
						+ playerLevel[3] + " Hitpoints!");
			}
			break;
		case 4: // Ranging
			sendFrame164(4443);
			stillgfx(199, absX, absY);
			sendFrame126("Congratulations, you just advanced a ranged level!",
					4444);
			sendFrame126("Your ranged level is now " + playerLevel[4] + " .",
					4445);
			sendMessage("Congratulations, you just advanced a ranging level.");
			NpcDialogueSend = true;
			if (playerLevel[4] >= 200)
			{
				sendMessage("Congratulations, you just received the Range skill cape and hood!");
				PlayerHandler.messageToAll = (username + " has just gotten "
						+ playerLevel[4] + " ranged!");
			}
			break;
		case 5: // Prayer
			sendFrame164(6242);
			stillgfx(199, absX, absY);
			sendFrame126("Congratulations, you just advanced a prayer level!",
					6243);
			sendFrame126("Your prayer level is now " + playerLevel[5] + " .",
					6244);
			sendMessage("Congratulations, you just advanced a prayer level.");
			NpcDialogueSend = true;
			if (playerLevel[5] >= 200)
			{
				sendMessage("Congratulations, you just received the Prayer skill cape and hood!");
				PlayerHandler.messageToAll = (username + " has just gotten "
						+ playerLevel[5] + " prayer!");
			}
			break;
		case 6: // Magic
			sendFrame164(6211);
			stillgfx(199, absX, absY);
			sendFrame126("Congratulations, you just advanced a magic level!",
					6212);
			sendFrame126("Your magic level is now " + playerLevel[6] + " .",
					6213);
			sendMessage("Congratulations, you just advanced a magic level.");
			NpcDialogueSend = true;
			if (playerLevel[6] >= 200)
			{
				sendMessage("Congratulations, you just received the Mage skill cape");
				PlayerHandler.messageToAll = (username + " has just gotten "
						+ playerLevel[6] + " magic!");
			}
			break;
		case 7:
			if (playerLevel[7] >= 200)
			{
				sendMessage("Congratulations, you just recieved the cooking skill cape and hood!");
				PlayerHandler.messageToAll = (username + " has just gotten "
						+ playerLevel[7] + " cooking!");
			}
			break;
		case 8:
			sendMessage("Congratulations, you just advanced a woodcutting level.");
			if (playerLevel[8] >= 200)
			{
				sendMessage("Congratulations, you just received the woodcutting skill cape and hood!");
				PlayerHandler.messageToAll = (username + " has just gotten "
						+ playerLevel[8] + " woodcutting!");
			}
			break;
		case 9: // Fletching
			sendMessage("Congratulations, you just advanced a fletching level.");
			// NpcDialogueSend = true;
			if (playerLevel[9] >= 200)
			{
				sendMessage("Congratulations, you just received the fletching skill cape and hood!");
				PlayerHandler.messageToAll = (username + " has just gotten "
						+ playerLevel[9] + " fletching!");
			}
			break;
		case 10: // fishing
			sendMessage("Congratulations, you just advanced a fishing level.");
			// NpcDialogueSend = true;
			if (playerLevel[10] >= 200)
			{
				sendMessage("Congratulations, you just received the fishing skill cape and hood!");
				PlayerHandler.messageToAll = (username + " has just gotten "
						+ playerLevel[10] + " fishing!");
			}
			break;
		case 11: // firemaking
			// sendFrame164(4282);
			// sendFrame126("Congratulations, you just advanced a fire making level!",
			// 4283);
			// sendFrame126("Your firemaking level is now "+playerLevel[11]+" .",
			// 4284);
			sendMessage("Congratulations, you just advanced a fire making level.");
			// NpcDialogueSend = true;
			if (playerLevel[11] >= 200)
			{
				sendMessage("Congratulations, you just received the firemaking skill cape and hood!");
				PlayerHandler.messageToAll = (username + " has just gotten "
						+ playerLevel[11] + " firemaking!");
			}
			break;
		case 12: // crafting
			// sendFrame164(6263);
			// sendFrame126("Congratulations, you just advanced a crafting level!",
			// 6264);
			// sendFrame126("Your crafting level is now "+playerLevel[12]+" .",
			// 6265);
			sendMessage("Congratulations, you just advanced a crafting level.");
			// NpcDialogueSend = true;
			if (playerLevel[12] >= 200)
			{
				sendMessage("Congratulations, you just received the crafting skill cape and hood!");
				PlayerHandler.messageToAll = (username + " has just gotten "
						+ playerLevel[12] + " crafting!");
			}
			break;
		case 13:
			if (playerLevel[13] >= 200)
			{
				sendMessage("Congratulations, you just received the smithing skill cape and hood!");
				PlayerHandler.messageToAll = (username + " has just gotten "
						+ playerLevel[13] + " smithing!");
			}
			break;
		case 14: // Mining
			sendMessage("Congratulations, you just advanced a mining level.");
			// NpcDialogueSend = true;
			if (playerLevel[14] >= 200)
			{
				sendMessage("Congratulations, you just received the mining skill cape and hood!");
				PlayerHandler.messageToAll = (username + " has just gotten "
						+ playerLevel[14] + " mining!");
			}
			break;
		case 15: // Herblore
			sendMessage("Congratulations, you just advanced a herblore level.");
			if (playerLevel[15] >= 200)
			{
				sendMessage("Congratulations, You Have Received The Herblore Skill Cape And Hood.");
				PlayerHandler.messageToAll = (username + " has just gotten "
						+ playerLevel[16] + " agility!");
			}
			break;
		case 16: // Agility
			sendMessage("Congratulations, you just advanced an agility level.");
			if (playerLevel[16] >= 200)
			{
				sendMessage("Congratulations, since there is no agility skillcape, take this.");
				PlayerHandler.messageToAll = (username + " has just gotten "
						+ playerLevel[16] + " agility!");
			}
			break;
		case 17: // Thieving
			sendMessage("Congratulations, you just advanced a thieving level.");
			if (playerLevel[17] >= 200)
			{
				sendMessage("Congratulations, you just received the Thieveing skill cape and hood!");
				PlayerHandler.messageToAll = (username + " has just gotten "
						+ playerLevel[17] + " thieveing!");
			}
			break;
		case 18: // Slayer
			sendMessage("Congratulations, you just advanced a slayer level.");
			if (playerLevel[18] >= 200)
			{
				sendMessage("Congratulations, you just received the Slayer skill cape and hood!");
				PlayerHandler.messageToAll = (username + " has just gotten "
						+ playerLevel[18] + " Slayer!");
			}
			break;
		case 19: // Farming
			sendMessage("Congratulations, you just advanced a farming level.");
			if (playerLevel[19] >= 200)
			{
				sendMessage("Congratulations, you just received the Farming skill cape and hood!");
				PlayerHandler.messageToAll = (username + " has just gotten "
						+ playerLevel[19] + " Farming!");
			}
			break;
		case 20: // Runecrafting
			sendMessage("Congratulations, you just advanced a Runecrafting level.");
			if (playerLevel[20] >= 200)
			{
				sendMessage("Congratulations, you just received the runecrafting skill cape and hood");
				PlayerHandler.messageToAll = (username + " has just gotten "
						+ playerLevel[20] + " runecrafting!");
			}
			break;
		}
	}

	public void attackPlayersWithin(int gfx, int maxDamage, int range)
	{
		for (Player p : PlayerHandler.players)
		{
			if (p != null)
			{
				Client person = (Client) p;
				if ((person.username != null || person.username != "null"))
				{
					if (person.distanceToPoint(absX, absY) <= range
							&& person.playerId != playerId && !person.nonWild())
					{
						int damage = MethodCollection.random(maxDamage);
						person.stillgfx(gfx, person.absY, person.absX);
						if (person.playerLevel[3] - hitDiff < 0)
						{
							damage = person.playerLevel[3];
						}
						person.hitDiff = damage;
						person.KillerId = playerId;
						person.updateRequired = true;
						person.hitUpdateRequired = true;
					}
				}
			}
		}
	}

	public void attackNPCSWithin(int gfx, int maxDamage, int range)
	{
		for (int i = 0; i <= NPCHandler.maxNPCs; i++)
		{
			if (NPCHandler.npcs[i] != null)
			{
				if (distanceToPoint(NPCHandler.npcs[i].absX,
						NPCHandler.npcs[i].absY) <= range
						&& !NPCHandler.npcs[i].IsDead
						&& NPCHandler.npcs[i].HP != 1000
						&& npcId != 2475
						&& npcId != 2259)
				{
					int damage = MethodCollection.random(maxDamage);
					stillgfx(gfx, NPCHandler.npcs[i].absY,
							NPCHandler.npcs[i].absX);
					if (NPCHandler.npcs[i].HP - hitDiff < 0)
					{
						damage = NPCHandler.npcs[i].HP;
					}
					hitDiff = damage;
					NPCHandler.npcs[i].StartKilling = playerId;
					NPCHandler.npcs[i].RandomWalk = false;
					NPCHandler.npcs[i].IsUnderAttack = true;
					NPCHandler.npcs[i].hitDiff = damage;
					NPCHandler.npcs[i].updateRequired = true;
					NPCHandler.npcs[i].hitUpdateRequired = true;
				}
			}
		}
	}

	public void playerGfx(int id, int delay)
	{
		mask100var1 = id;
		mask100var2 = delay;
		mask100update = true;
		updateRequired = true;
	}

	public void stillgfxz(int id, int Y, int X, int height, int time)
	{
		for (Player p : PlayerHandler.players)
		{
			if (p != null)
			{
				Client person = (Client) p;
				if ((person.username != null || person.username != "null"))
				{
					if (person.distanceToPoint(X, Y) <= 60)
					{
						person.stillgfxz2(id, Y, X, height, time);
					}
				}
			}
		}
	}

	public void stillgfxz2(int id, int Y, int X, int height, int time)
	{
		outStream.createFrame(85);
		outStream.putByteC(Y - (mapRegionY * 8));
		outStream.putByteC(X - (mapRegionX * 8));
		outStream.createFrame(4);
		outStream.put(0); // Tiles away (X >> 4 + Y & 7)
		outStream.putShort(id); // Graphic id
		outStream.put(height); // height of the spell above it's basic
								// place, i think it's written in pixels
								// 100 pixels higher
		outStream.putShort(time); // Time before casting the graphic
	}

	public void stillgfxz3(int id, int Y, int X, int height, int time, int Face)
	{
		outStream.createFrame(85);
		outStream.putByteC(Y - (mapRegionY * 8));
		outStream.putByteC(X - (mapRegionX * 8));
		outStream.createFrame(4);
		outStream.put(0); // Tiles away (X >> 4 + Y & 7)
		outStream.putShort(id); // Graphic id
		outStream.put(height); // height of the spell above it's basic
								// place, i think it's written in pixels
								// 100 pixels higher
		outStream.putShort(time); // Time before casting the graphic
		outStream.put(151);
		outStream.putByteA(Face); // 0= WEST | -1 = NORTH | -2 = EAST | -3 =
									// SOUTH
	}

	public void stillgfx(int id, int Y, int X)
	{
		for (Player p : PlayerHandler.players)
		{
			if (p != null)
			{
				Client person = (Client) p;
				if ((person.username != null || person.username != "null"))
				{
					if (person.distanceToPoint(X, Y) <= 60)
					{
						person.stillgfx2(id, Y, X);
					}
				}
			}
		}
	}

	public void stillgfx2(int id, int Y, int X)
	{
		outStream.createFrame(85);
		outStream.putByteC(Y - (mapRegionY * 8));
		outStream.putByteC(X - (mapRegionX * 8));
		outStream.createFrame(4);
		outStream.put(0); // Tiles away (X >> 4 + Y & 7)
		outStream.putShort(id); // Graphic id
		outStream.put(0); // height of the spell above it's basic place, i
							// think it's written in pixels 100 pixels
							// higher
		outStream.putShort(0); // Time before casting the graphic
	}

	public void stillgfxx(int id, int Y, int X)
	{
		outStream.createFrame(85);
		outStream.putByteC(Y - (mapRegionY * 8));
		outStream.putByteC(X - (mapRegionX * 8));
		outStream.createFrame(4);
		outStream.put(0); // Tiles away (X >> 4 + Y & 7)
		outStream.putShort(id); // Graphic id
		outStream.put(100); // height of the spell above it's basic place,
							// i think it's written in pixels 100 pixels
							// higher
		outStream.putShort(5); // Time before casting the graphic
	}

	public void multiTargetGfx(int id, int targetY, int targetX)
	{
		for (Player p : PlayerHandler.players)
		{
			if (p != null)
			{
				Client person = (Client) p;
				if ((person.username != null || person.username != "null"))
				{
					if (person.distanceToPoint(targetX, targetY) <= 60)
					{
						person.stillgfx2(id, person.absY, person.absX);
					}
				}
			}
		}
	}

	/* MAGIC */
	public int MageAttackIndex = -1;
	public int graphicstimer = 0;
	public boolean cast = false;
	public boolean fired = false;
	public boolean firingspell = false;
	public int fcastid = 0;
	public int fcasterY = 0;
	public int fcasterX = 0;
	public int foffsetY = 0;
	public int foffsetX = 0;
	public int fangle = 0;
	public int fspeed = 0;
	public int fmgfxid = 0;
	public int fsh = 0;
	public int feh = 0;
	public int ffinishid = 0;
	public int fenemyY = 0;
	public int fenemyX = 0;
	public int fLockon = 0;
	public boolean fMageNpc = false;

	public boolean firespell(int castID, int casterY, int casterX, int offsetY,
			int offsetX, int angle, int speed, int movegfxID, int startHeight,
			int endHeight, int finishID, int enemyY, int enemyX, int Lockon)
	{
		fcastid = castID;
		fcasterY = casterY;
		fcasterX = casterX;
		foffsetY = offsetY;
		foffsetX = offsetX;
		fangle = angle;
		fspeed = speed;
		fmgfxid = movegfxID;
		fsh = startHeight;
		feh = endHeight;
		ffinishid = finishID;
		fenemyY = enemyY;
		fenemyX = enemyX;
		fLockon = Lockon;
		actionTimer = 0;
		// Casts Spell In Hands
		if (cast == false && actionTimer <= 0)
		{
			stillgfxz(castID, casterY, casterX, 100, 0);
			cast = true;
			firingspell = true;
		}
		// Fires Projectile
		if (cast == true && fired == false && actionTimer <= 0)
		{
			createProjectile(casterY, casterX, offsetY, offsetX, angle, speed,
					movegfxID, startHeight, endHeight, Lockon);
			fired = true;
		}
		// Finishes Spell
		if (fired == true && actionTimer <= 0)
		{
			stillgfxz(finishID, enemyY, enemyX, 100, 95);
			resetGFX(castID, enemyX, enemyY);
			return false;
		}
		return true;
	}

	public void resetGFX(int id, int X, int Y)
	{
		GraphicsHandler.removeGFX(id, X, Y);
		firingspell = false;
		cast = false;
		fired = false;
	}

	public void createProjectile(int casterY, int casterX, int offsetY,
			int offsetX, int angle, int speed, int gfxMoving, int startHeight,
			int endHeight, int MageAttackIndex)
	{
		outStream.createFrame(85);
		outStream.putByteC((casterY - (mapRegionY * 8)) - 2);
		outStream.putByteC((casterX - (mapRegionX * 8)) - 3);
		outStream.createFrame(117);
		outStream.put(angle); // Starting place of the projectile
		outStream.put(offsetY); // Distance between caster and enemy Y
		outStream.put(offsetX); // Distance between caster and enemy X
		outStream.putShort(MageAttackIndex); // The NPC the missle is locked on
												// to
		outStream.putShort(gfxMoving); // The moving graphic ID
		outStream.put(startHeight); // The starting height
		outStream.put(endHeight); // Destination height
		outStream.putShort(51); // Time the missle is created
		outStream.putShort(speed); // Speed minus the distance making it set
		outStream.put(16); // Initial slope
		outStream.put(64); // Initial distance from source (in the
							// direction of the missile) //64
	}

	public void createProjectilez(int casterY, int casterX, int offsetY,
			int offsetX, int angle, int speed, int gfxMoving, int startHeight,
			int endHeight, int Lockon, boolean MagingNPC)
	{
		outStream.createFrame(85);
		outStream.putByteC(casterY - 2); // Phate: seems to take a couple off?
		outStream.putByteC(casterX - 3); // Phate: seems to take 3 off?
		outStream.createFrame(117);
		outStream.put(angle); // Phate: Angle? I think
		outStream.put(offsetX); // Phate: OffsetY in relevance from the
								// first player
		outStream.put(offsetY); // Phate: OffsetX in relevance from the
								// first player
		if (MagingNPC)
		{
			outStream.putShort(Lockon);
		}
		else
		{
			outStream.putShort(-Lockon);
		}
		outStream.putShort(gfxMoving); // Phate: Magic Moving Graphic ID
		outStream.put(startHeight); // Phate: Starting height
		outStream.put(endHeight); // Phate: Finishing height
		outStream.putShort(51); // Phate: No idea?
		outStream.putShort(speed); // Phate: Speed of Moving Magic
		outStream.put(16); // Phate: Something static? Doesnt change a
							// lot..
		outStream.put(64); // Phate: Type of shot possibly? All shoots
							// seemed to be 64
		flushOutStream();
	}

	public void ProjectileSpell(int startID, int movingID, int finishID,
			int casterY, int casterX, int offsetY, int offsetX, int index,
			int enemyY, int enemyX, int maxDMG, int lvlReq, int rune1,
			int rune2, int rune1amount, int rune2amount)
	{
		if (playerLevel[6] < lvlReq)
		{
			sendMessage("You need a magic level of " + lvlReq
					+ " to cast this spell");
		}
		else if (playerLevel[6] >= -lvlReq)
		{
			if (!playerHasItemAmount(rune1, rune1amount)
					&& !playerHasItemAmount(rune2, rune2amount))
			{
				sendMessage("You do not have enough runes to cast that spell.");
			}
			else
			/*
			 * if(playerHasItemAmount(rune1, rune1amount) &&
			 * playerHasItemAmount(rune2, rune2amount))
			 */
			{
				// GraphicsHandler.createSpell(startID, casterY, casterX,
				// offsetY, offsetX, 50, 95, movingID, 43, 31, finishID, enemyY,
				// enemyX, index+1);
				teleportToX = absX;
				teleportToY = absY;
				hitDiff = MethodCollection.random(maxDMG);
				int mageXP = (hitDiff * 6);
				addSkillXP(mageXP, 6);
				setAnimation(711);
				deleteItem(rune1, getItemSlot(rune1), rune1amount);
				deleteItem(rune2, getItemSlot(rune2), rune2amount);
				// actionTimer = 4;
				GraphicsHandler.createSpell(startID, casterY, casterX, offsetY,
						offsetX, 50, 95, movingID, 43, 31, finishID, enemyY,
						enemyX, index + 1);
				if (NPCHandler.npcs[index].HP - hitDiff < 0)
				{
					hitDiff = NPCHandler.npcs[index].HP;
				}
				addSkillXP(mageXP, 6);
				NPCHandler.npcs[index].hitDiff = hitDiff;
				NPCHandler.npcs[index].Killing[playerId] += hitDiff;
				NPCHandler.npcs[index].updateRequired = true;
				NPCHandler.npcs[index].hitUpdateRequired = true;
				// actionTimer = 4;
			}
		}
	}

	public void ProjectileSpell(int startID, int movingID, int finishID,
			int casterY, int casterX, int offsetY, int offsetX, int index,
			int enemyY, int enemyX, int maxDMG, int lvlReq, int rune1,
			int rune2, int rune3, int rune1amount, int rune2amount,
			int rune3amount)
	{
		if (playerLevel[6] < lvlReq)
		{
			sendMessage("You need a magic level of " + lvlReq
					+ " to cast this spell");
		}
		else if (playerLevel[6] >= -lvlReq)
		{
			if (!playerHasItemAmount(rune1, rune1amount)
					&& !playerHasItemAmount(rune2, rune2amount)
					&& !playerHasItemAmount(rune3, rune3amount))
			{
				sendMessage("You do not have enough runes to cast that spell.");
			}
			else
			/*
			 * if(playerHasItemAmount(rune1, rune1amount) &&
			 * playerHasItemAmount(rune2, rune2amount) &&
			 * playerHasItemAmount(rune3, rune3amount))
			 */
			{
				// GraphicsHandler.createSpell(startID, casterY, casterX,
				// offsetY, offsetX, 50, 95, movingID, 43, 31, finishID, enemyY,
				// enemyX, index+1);
				hitDiff = MethodCollection.random(maxDMG);
				int mageXP = (hitDiff * 6);
				addSkillXP(mageXP, 6);
				setAnimation(711);
				deleteItem(rune1, getItemSlot(rune1), rune1amount);
				deleteItem(rune2, getItemSlot(rune2), rune2amount);
				deleteItem(rune3, getItemSlot(rune3), rune3amount);
				// actionTimer = 4;
				GraphicsHandler.createSpell(startID, casterY, casterX, offsetY,
						offsetX, 50, 95, movingID, 43, 31, finishID, enemyY,
						enemyX, index + 1);
				if (NPCHandler.npcs[index].HP - hitDiff < 0)
				{
					hitDiff = NPCHandler.npcs[index].HP;
				}
				addSkillXP(mageXP, 6);
				NPCHandler.npcs[index].hitDiff = hitDiff;
				NPCHandler.npcs[index].Killing[playerId] += hitDiff;
				NPCHandler.npcs[index].updateRequired = true;
				NPCHandler.npcs[index].hitUpdateRequired = true;
				// actionTimer = 4;
			}
		}
	}

	public void ProjectileSpellPlayer(int startID, int movingID, int finishID,
			int casterY, int casterX, int offsetY, int offsetX, int index,
			int enemyY, int enemyX, int maxDMG, int lvlReq, int rune1,
			int rune2, int rune1amount, int rune2amount)
	{
		if (playerLevel[6] < lvlReq)
		{
			sendMessage("You need a magic level of " + lvlReq
					+ " to cast this spell");
		}
		else if (playerLevel[6] >= -lvlReq)
		{
			if (!playerHasItemAmount(rune1, rune1amount)
					&& !playerHasItemAmount(rune2, rune2amount))
			{
				sendMessage("You do not have enough runes to cast that spell.");
			}
			else
			/*
			 * if(playerHasItemAmount(rune1, rune1amount) &&
			 * playerHasItemAmount(rune2, rune2amount))
			 */
			{
				// GraphicsHandler.createSpell(startID, casterY, casterX,
				// offsetY, offsetX, 50, 95, movingID, 43, 31, finishID, enemyY,
				// enemyX, index+1);
				teleportToX = absX;
				teleportToY = absY;
				hitDiff = MethodCollection.random(maxDMG);
				int mageXP = (hitDiff * 4);
				addSkillXP(mageXP, 6);
				if (PlayerHandler.players[index].playerLevel[3] - hitDiff < 0)
				{
					hitDiff = PlayerHandler.players[index].playerLevel[3];
				}
				PlayerHandler.players[index].hitDiff = hitDiff;
				PlayerHandler.players[index].updateRequired = true;
				PlayerHandler.players[index].hitUpdateRequired = true;
				setAnimation(711);
				deleteItem(rune1, getItemSlot(rune1), rune1amount);
				deleteItem(rune2, getItemSlot(rune2), rune2amount);
				actionTimer = 4;
				GraphicsHandler.createSpell(startID, casterY, casterX, offsetY,
						offsetX, 50, 95, movingID, 43, 31, finishID, enemyY,
						enemyX, 0 - index);
			}
		}
	}

	public void ProjectileSpellPlayer(int startID, int movingID, int finishID,
			int casterY, int casterX, int offsetY, int offsetX, int index,
			int enemyY, int enemyX, int maxDMG, int lvlReq, int rune1,
			int rune2, int rune3, int rune1amount, int rune2amount,
			int rune3amount)
	{
		if (playerLevel[6] < lvlReq)
		{
			sendMessage("You need a magic level of " + lvlReq
					+ " to cast this spell");
		}
		else if (playerLevel[6] >= -lvlReq)
		{
			if (!playerHasItemAmount(rune1, rune1amount)
					&& !playerHasItemAmount(rune2, rune2amount)
					&& !playerHasItemAmount(rune3, rune3amount))
			{
				sendMessage("You do not have enough runes to cast that spell.");
			}
			else
			/*
			 * if(playerHasItemAmount(rune1, rune1amount) &&
			 * playerHasItemAmount(rune2, rune2amount) &&
			 * playerHasItemAmount(rune3, rune3amount))
			 */
			{
				// GraphicsHandler.createSpell(startID, casterY, casterX,
				// offsetY, offsetX, 50, 95, movingID, 43, 31, finishID, enemyY,
				// enemyX, 0 - index);
				hitDiff = MethodCollection.random(maxDMG);
				int mageXP = (hitDiff * 4);
				addSkillXP(mageXP, 6);
				if (PlayerHandler.players[index].playerLevel[3] - hitDiff < 0)
				{
					hitDiff = PlayerHandler.players[index].playerLevel[3];
				}
				PlayerHandler.players[index].hitDiff = hitDiff;
				PlayerHandler.players[index].updateRequired = true;
				PlayerHandler.players[index].hitUpdateRequired = true;
				setAnimation(711);
				deleteItem(rune1, getItemSlot(rune1), rune1amount);
				deleteItem(rune2, getItemSlot(rune2), rune2amount);
				deleteItem(rune3, getItemSlot(rune3), rune3amount);
				actionTimer = 4;
				GraphicsHandler.createSpell(startID, casterY, casterX, offsetY,
						offsetX, 50, 95, movingID, 43, 31, finishID, enemyY,
						enemyX, 0 - index);
			}
		}
	}

	public void TeleTo(String s, int level)
	{
		teleX = absX;
		teleY = absY;
		newheightLevel = heightLevel;
		// checkwildy();
		if (teleblock == false && actionTimer <= 7)
		{
			if (s == "Varrock")
			{
				teleX = 3210;
				teleY = 3424;
				addSkillXP((20 * playerLevel[6]), 6);
				newheightLevel = 0;
			}
			if (s == "Falador")
			{
				teleX = 2964;
				teleY = 3378;
				addSkillXP((30 * playerLevel[6]), 6);
				newheightLevel = 0;
			}
			if (s == "Lumby")
			{
				teleX = 3222;
				teleY = 3218;
				addSkillXP((40 * playerLevel[6]), 6);
				newheightLevel = 0;
			}
			if (s == "Camelot")
			{
				teleX = 2757;
				teleY = 3477;
				addSkillXP((50 * playerLevel[6]), 6);
				newheightLevel = 0;
			}
			if (s == "Ardougne")
			{
				teleX = 2662;
				teleY = 3305;
				addSkillXP((120 * playerLevel[6]), 6);
				newheightLevel = 0;
			}
			if (s == "Watchtower")
			{
				teleX = 2549;
				teleY = 3113;
				addSkillXP((150 * playerLevel[6]), 6);
				newheightLevel = 0;
			}
			if (s == "Trollheim")
			{
				teleX = 2480;
				teleY = 5174;
				addSkillXP((400 * playerLevel[6]), 6);
				newheightLevel = 0;
			}
			if (s == "Ape")
			{
				teleX = 2761;
				teleY = 2784;
				addSkillXP((400 * playerLevel[6]), 6);
				newheightLevel = 1;
			}
			if (s == "Paddewwa")
			{
				teleX = 3131;
				teleY = 9912;
				addSkillXP((150 * playerLevel[MAGIC]), MAGIC);
				newheightLevel = 0;
			}
			if (s == "Senntisten")
			{
				teleX = 3312;
				teleY = 3376;
				addSkillXP((200 * playerLevel[MAGIC]), MAGIC);
				newheightLevel = 0;
			}
			if (s == "Kharyrll")
			{
				teleX = 3493;
				teleY = 3485;
				addSkillXP((25 * playerLevel[MAGIC]), MAGIC);
				newheightLevel = 0;
			}
			if (s == "Lasaar")
			{
				teleX = 3007;
				teleY = 3477;
				addSkillXP((350 * playerLevel[MAGIC]), MAGIC);
				newheightLevel = 0;
			}
			if (s == "Carrallangar")
			{
				teleX = 3161;
				teleY = 3671;
				addSkillXP((400 * playerLevel[MAGIC]), MAGIC);
				newheightLevel = 0;
			}
			if (s == "Annakarl")
			{
				teleX = 3288;
				teleY = 3886;
				addSkillXP((550 * playerLevel[MAGIC]), MAGIC);
				newheightLevel = 0;
			}
			if (s == "Ghorrock")
			{
				teleX = 3091;
				teleY = 3963;
				addSkillXP((650 * playerLevel[MAGIC]), MAGIC);
				newheightLevel = 0;
			}
			RemoveAllWindows();
			closeInterface();
			teleport();
			actionTimer = 10;
		}
		else if (teleblock == true)
		{
			sendMessage("A magical force stops you from teleporting.");
		}
		else if (playerLevel[6] < level)
		{
			sendMessage("You need a magic level of " + level
					+ " to cast this spell.");
		}
		else if (inwildy == true)
		{
			sendMessage("You cannot teleport above level 20 wilderness.");
		}
		updateRequired = true;
		appearanceUpdateRequired = true;
	}

	public boolean HasItemAmount(int itemID, int itemAmount)
	{
		int playerItemAmountCount = 0;
		for (int i = 0; i < playerItems.length; i++)
		{
			if (playerItems[i] == itemID + 1)
			{
				playerItemAmountCount = playerItemsN[i];
			}
			if (playerItemAmountCount >= itemAmount)
			{
				return true;
			}
		}
		return false;
	}

	public boolean Has2Items(int itemID, int amount, int itemID2, int amount2)
	{
		if (HasItemAmount(itemID, amount))
		{
			if (HasItemAmount(itemID2, amount2))
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			return false;
		}
	}

	public boolean Has3Items(int itemID, int amount, int itemID2, int amount2,
			int itemID3, int amount3)
	{
		if (HasItemAmount(itemID, amount))
		{
			if (HasItemAmount(itemID2, amount2))
			{
				if (HasItemAmount(itemID3, amount3))
				{
					return true;
				}
				else
				{
					return false;
				}
			}
			else
			{
				return false;
			}
		}
		else
		{
			return false;
		}
	}

	public void teleport(int x, int y, int h, int xp, int lvl)
	{
		teleportToX = x;
		teleportToY = y;
		heightLevel = h;
		addSkillXP(xp, lvl);
	}

	public void TeleToAdvanced(String cityName, int lvl, String type)
	{
		if (playerLevel[MAGIC] >= lvl)
		{
			String line = "";
			String token = "";
			String token2 = "";
			String token2_2 = "";
			String[] token3 = new String[25];
			boolean EndOfFile = false;
			BufferedReader characterfile = null;
			try
			{
				characterfile = new BufferedReader(new FileReader(
						"teleport.cfg"));
			}
			catch (FileNotFoundException fileex)
			{
				MethodCollection.println("Teleport.cfg: not found.");
			}
			try
			{
				line = characterfile.readLine();
			}
			catch (IOException ioexception)
			{
				MethodCollection.println("Teleport.cfg: error loading file.");
			}
			while (EndOfFile == false && line != null)
			{
				line = line.trim();
				int spot = line.indexOf("=");
				if (spot > -1)
				{
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
					int RandomNum = 0;
					if (token.equals("Tele"))
					{
						String city = token3[0];
						if (type.equalsIgnoreCase("cmd"))
						{
							RandomNum = 1;
						}
						if (type.equalsIgnoreCase("mage"))
						{
							RandomNum = MethodCollection.random(25);
						}
						int RTID = Integer.parseInt(token3[1]);
						int RuneTypes = Integer.parseInt(token3[3]);
						int Rune1 = Integer.parseInt(token3[4]);
						int Rune2 = Integer.parseInt(token3[5]);
						int Rune3 = Integer.parseInt(token3[6]);
						int Item = Integer.parseInt(token3[7]);
						int TeleX = Integer.parseInt(token3[8]);
						int TeleY = Integer.parseInt(token3[9]);
						int Height = Integer.parseInt(token3[10]);
						int Xp = Integer.parseInt(token3[11]);
						int R1Amt = Integer.parseInt(token3[12]);
						int R2Amt = Integer.parseInt(token3[13]);
						int R3Amt = Integer.parseInt(token3[14]);
						int ItmAmt = Integer.parseInt(token3[15]);
						if (cityName.equalsIgnoreCase(city))
						{
							if (RandomNum == RTID)
							{
								if (type.equalsIgnoreCase("cmd"))
								{
									teleport(TeleX, TeleY, Height, Xp, MAGIC);
								}
								if (Item == -1)
								{
									if (RuneTypes == 2)
									{
										if (Has2Items(Rune1, R1Amt, Rune2,
												R2Amt))
										{
											deleteItem(Rune1,
													GetItemSlot(Rune1), R1Amt);
											deleteItem(Rune2,
													GetItemSlot(Rune2), R2Amt);
											teleport(TeleX, TeleY, Height, Xp,
													MAGIC);
										}
										else
										{
											sendMessage("You don't have the required runes to do that.");
										}
									}
									if (RuneTypes == 3)
									{
										if (Has3Items(Rune1, R1Amt, Rune2,
												R2Amt, Rune3, R3Amt))
										{
											deleteItem(Rune1,
													GetItemSlot(Rune1), R1Amt);
											deleteItem(Rune2,
													GetItemSlot(Rune2), R2Amt);
											deleteItem(Rune3,
													GetItemSlot(Rune3), R3Amt);
											teleport(TeleX, TeleY, Height, Xp,
													MAGIC);
										}
										else
										{
											sendMessage("You don't have the required runes to do that.");
										}
									}
								}
								else
								{
									if (RuneTypes == 2)
									{
										if (Has2Items(Rune1, R1Amt, Rune2,
												R2Amt))
										{
											if (HasItemAmount(Item, ItmAmt))
											{
												deleteItem(Item,
														GetItemSlot(Item),
														ItmAmt);
												deleteItem(Rune1,
														GetItemSlot(Rune1),
														R1Amt);
												deleteItem(Rune2,
														GetItemSlot(Rune2),
														R1Amt);
												teleport(TeleX, TeleY, Height,
														Xp, MAGIC);
											}
											else
											{
												sendMessage("You need a "
														+ getItemName(Item)
														+ " to do that.");
											}
										}
										else
										{
											sendMessage("You don't have the required runes to do that.");
										}
									}
									if (RuneTypes == 3)
									{
										if (Has3Items(Rune1, R1Amt, Rune2,
												R2Amt, Rune3, R3Amt))
										{
											if (HasItemAmount(Item, ItmAmt))
											{
												deleteItem(Item,
														GetItemSlot(Item),
														ItmAmt);
												deleteItem(Rune1,
														GetItemSlot(Rune1),
														R1Amt);
												deleteItem(Rune2,
														GetItemSlot(Rune2),
														R1Amt);
												deleteItem(Rune3,
														GetItemSlot(Rune3),
														R3Amt);
												teleport(TeleX, TeleY, Height,
														Xp, MAGIC);
											}
											else
											{
												sendMessage("You need a "
														+ getItemName(Item)
														+ " to do that.");
											}
										}
										else
										{
											sendMessage("You don't have the required runes to do that.");
										}
									}
								}
							}
						}
					}
				}
				else
				{
					if (line.equals("[ENDOFTELELIST]"))
					{
						try
						{
							characterfile.close();
						}
						catch (IOException ioexception)
						{
						}
					}
				}
				try
				{
					line = characterfile.readLine();
				}
				catch (IOException ioexception1)
				{
					EndOfFile = true;
				}
			}
			try
			{
				characterfile.close();
			}
			catch (IOException ioexception)
			{
			}
		}
		else
		{
			sendMessage("You need level " + lvl + " to use this spell.");
		}
	}

	public boolean playerHasItem(int itemID)
	{
		for (int i = 0; i < playerItems.length; i++)
		{
			if (playerItems[i] == itemID + 1)
			{
				return true;
			}
		}
		return false;
	}

	public boolean playerHasItem2(int itemID)
	{
		for (int i = 0; i < playerItems.length; i++)
		{
			if (playerItems[i] == itemID + 1)
			{
				playerAxe = itemID;
				return true;
			}
		}
		for (int i2 = 0; i2 < playerEquipment.length; i2++)
		{
			if (playerEquipment[i2] == itemID)
			{
				playerAxe = itemID;
				return true;
			}
		}
		return false;
	}

	public boolean hasItemAny(int id, int amount)
	{
		for (int i = 0; i < playerItems.length; i++)
		{
			if (playerItems[i] == id + 1 && playerItemsN[i] >= amount)
			{
				return true;
			}
		}
		for (int i2 = 0; i2 < playerBankSize; i2++)
		{
			if (bankItems[i2] == id + 1 && bankItemsN[i2] >= amount)
			{
				return true;
			}
		}
		return false;
	}

	public void ReplaceItems(int newID, int oldID, int newAmount, int oldAmount)
	{
		for (int i = 0; i < playerItems.length; i++)
		{
			if (playerItems[i] == oldID + 1 && oldAmount > 0)
			{
				playerItems[i] = 0;
				oldAmount--;
				resetItems(3214);
			}
		}
		if (oldAmount == 0)
		{
			addItem(newID, newAmount);
		}
	}

	public boolean hasItem(int itemID, int slot)
	{
		if (playerItems[slot] == itemID)
		{
			return true;
		}
		return false;
	}

	public int getItemSlotReturn(int itemID)
	{
		for (int slot = 0; slot < playerItems.length; slot++)
		{
			if (playerItems[slot] == (itemID + 1))
			{
				return slot;
			}
		}
		return -1;
	}

	public boolean Inair = false;
	public Calendar cal = new GregorianCalendar();
	public int hour12 = cal.get(Calendar.HOUR); // 0..11
	public int hour24 = cal.get(Calendar.HOUR_OF_DAY); // 0..23
	public int min = cal.get(Calendar.MINUTE); // 0..59
	public int sec = cal.get(Calendar.SECOND); // 0..59
	public int ms = cal.get(Calendar.MILLISECOND); // 0..999
	public int ampm = cal.get(Calendar.AM_PM); // 0=AM, 1=PM
	public static boolean snowFilter;
	public static boolean dustFilter;
	public static boolean dizzyFilter;
	public static boolean afternoonFilter;
	public static boolean eveningFilter;
	public static boolean nightFilter;
	public static boolean resetFilters;
	public boolean adglow = false;
	public boolean modglow = false;
	public boolean dicerglow = false;
	public static boolean morningTime;
	public static boolean afternoonTime;
	public static boolean eveningTime;
	public static boolean nightTime;

	public void getTime()
	{
		sendMessage("hour: " + hour24 + " mins: " + min + " secs: " + sec);
	}

	public int actionButtonId = 0;
	public boolean actionset = false;

	public void setInterfaceWalkable(int ID)
	{
		outStream.createFrame(208);
		outStream.putWordBigEndian_dup(ID);
		flushOutStream();
	}

	public void setTime()
	{
		if (morningTime)
		{
			setInterfaceWalkable(65535);
		}
		if (afternoonTime)
		{
			setInterfaceWalkable(12416);
		}
		if (eveningTime)
		{
			setInterfaceWalkable(12418);
		}
		if (nightTime)
		{
			setInterfaceWalkable(12414);
		}
	}

	public int friendslot = 0;
	public long friend64 = 0;
	/* ADD MORE TWO HANDED ITEMS HERE */
	public static int more2handed[] = { 7158, 1319, 6528, 14915 };

	public static int more2handed()
	{
		return more2handed[more2handed.length];
	}

	public boolean item2handed(int ID)
	{
		if (ID == 1)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	/* END OF 2 HANDED */
	/* SMITHING */
	public int removeBar(int removeID)
	{
		if (removeID == 1205 || removeID == 1351 || removeID == 1103
				|| removeID == 1139 || removeID == 819 || removeID == 1277
				|| removeID == 1422 || removeID == 1075 || removeID == 15334
				|| removeID == 39 || removeID == 1321 || removeID == 15195
				|| removeID == 1087 || removeID == 1173 || removeID == 1291
				|| removeID == 1375 || removeID == 15335 || removeID == 1189
				|| removeID == 1307 || removeID == 15336 || removeID == 4819)
		{
			return 2349;
		}
		if (removeID == 1203 || removeID == 1349 || removeID == 1420
				|| removeID == 1137 || removeID == 1279 || removeID == 820
				|| removeID == 4820 || removeID == 1323 || removeID == 40
				|| removeID == 1293 || removeID == 1153 || removeID == 863
				|| removeID == 1175 || removeID == 1335 || removeID == 1363
				|| removeID == 1101 || removeID == 4540 || removeID == 1191
				|| removeID == 3096 || removeID == 1309 || removeID == 1067
				|| removeID == 1081 || removeID == 1115)
		{
			return 2351;
		}
		if (removeID == 1207 || removeID == 3097 || removeID == 1353
				|| removeID == 1424 || removeID == 1141 || removeID == 1281
				|| removeID == 1325 || removeID == 1295 || removeID == 1157
				|| removeID == 1177 || removeID == 1339 || removeID == 1365
				|| removeID == 1105 || removeID == 1193 || removeID == 1069
				|| removeID == 1083 || removeID == 1311 || removeID == 1119
				|| removeID == 1539 || removeID == 821 || removeID == 41
				|| removeID == 2 || removeID == 2370 || removeID == 865
				|| removeID == 4544)
		{
			return 2353;
		}
		// Mith
		if (removeID == 1209 || removeID == 3099 || removeID == 1355
				|| removeID == 1428 || removeID == 1143 || removeID == 1285
				|| removeID == 1329 || removeID == 1299 || removeID == 1159
				|| removeID == 1181 || removeID == 1343 || removeID == 1369
				|| removeID == 1109 || removeID == 1197 || removeID == 1071
				|| removeID == 1085 || removeID == 1315 || removeID == 1121
				|| removeID == 822 || removeID == 4822 || removeID == 42
				|| removeID == 42 || removeID == 866)
		{
			return 2359;
		}
		// Addy
		if (removeID == 1211 || removeID == 3100 || removeID == 1430
				|| removeID == 1145 || removeID == 1287 || removeID == 1331
				|| removeID == 1301 || removeID == 1161 || removeID == 1183
				|| removeID == 1371 || removeID == 1111 || removeID == 1073
				|| removeID == 1091 || removeID == 1317 || removeID == 1123
				|| removeID == 823 || removeID == 4823 || removeID == 43
				|| removeID == 867 || removeID == 1199)
		{
			return 2361;
		}
		// Rune
		if (removeID == 1213 || removeID == 3101 || removeID == 1432
				|| removeID == 1147 || removeID == 1289 || removeID == 1333
				|| removeID == 1303 || removeID == 1163 || removeID == 1185
				|| removeID == 1347 || removeID == 1373 || removeID == 1113
				|| removeID == 1201 || removeID == 1079 || removeID == 1093
				|| removeID == 1319 || removeID == 824 || removeID == 4824
				|| removeID == 44 || removeID == 868)
		{
			return 2363;
		}
		return 0;
	}

	public int barsNeeded(int slot, int column)
	{
		if (column == 1119)
		{
			if (slot == 0 || slot == 1)
			{
				return 1;
			}
			if (slot == 2 || slot == 3)
			{
				return 2;
			}
			if (slot == 4)
			{
				return 3;
			}
		}
		if (column == 1120)
		{
			if (slot == 0 || slot == 1)
			{
				return 1;
			}
			if (slot == 2 || slot == 3)
			{
				return 3;
			}
			if (slot == 4)
			{
				return 2;
			}
		}
		if (column == 1121)
		{
			if (slot == 0 || slot == 1 || slot == 2)
			{
				return 3;
			}
			if (slot == 3)
			{
				return 5;
			}
			if (slot == 4)
			{
				return 1;
			}
		}
		if (column == 1122)
		{
			if (slot == 0 || slot == 4)
			{
				return 1;
			}
			if (slot == 1 || slot == 2)
			{
				return 2;
			}
			if (slot == 3)
			{
				return 3;
			}
		}
		if (column == 1123)
		{
			if (slot == 0 || slot == 1 || slot == 2 || slot == 3 || slot == 4)
			{
				return 1;
			}
		}
		return 0;
	}

	public int barsNeeded2(int slot, int column)
	{
		if (column == 1119)
		{
			if (slot == 0 || slot == 1)
			{
				return 5;
			}
			if (slot == 2 || slot == 3)
			{
				return 10;
			}
			if (slot == 4)
			{
				return 15;
			}
		}
		if (column == 1120)
		{
			if (slot == 0 || slot == 1)
			{
				return 5;
			}
			if (slot == 2 || slot == 3)
			{
				return 15;
			}
			if (slot == 4)
			{
				return 10;
			}
		}
		if (column == 1121)
		{
			if (slot == 0 || slot == 1 || slot == 2)
			{
				return 15;
			}
			if (slot == 3)
			{
				return 25;
			}
			if (slot == 4)
			{
				return 5;
			}
		}
		if (column == 1122)
		{
			if (slot == 0 || slot == 4)
			{
				return 5;
			}
			if (slot == 1 || slot == 2)
			{
				return 10;
			}
			if (slot == 3)
			{
				return 15;
			}
		}
		if (column == 1123)
		{
			if (slot == 0 || slot == 1 || slot == 2 || slot == 3 || slot == 4)
			{
				return 5;
			}
		}
		return 0;
	}

	public int barsNeeded3(int slot, int column)
	{
		if (column == 1119)
		{
			if (slot == 0 || slot == 1)
			{
				return 10;
			}
			if (slot == 2 || slot == 3)
			{
				return 20;
			}
			if (slot == 4)
			{
				return 30;
			}
		}
		if (column == 1120)
		{
			if (slot == 0 || slot == 1)
			{
				return 10;
			}
			if (slot == 2 || slot == 3)
			{
				return 30;
			}
			if (slot == 4)
			{
				return 20;
			}
		}
		if (column == 1121)
		{
			if (slot == 0 || slot == 1 || slot == 2)
			{
				return 30;
			}
			if (slot == 3)
			{
				return 50;
			}
			if (slot == 4)
			{
				return 10;
			}
		}
		if (column == 1122)
		{
			if (slot == 0 || slot == 4)
			{
				return 10;
			}
			if (slot == 1 || slot == 2)
			{
				return 20;
			}
			if (slot == 3)
			{
				return 30;
			}
		}
		if (column == 1123)
		{
			if (slot == 0 || slot == 1 || slot == 2 || slot == 3 || slot == 4)
			{
				return 10;
			}
		}
		return 0;
	}

	public int smithXP(int barType, int barAmount)
	{
		if (barType == 2349)
		{
			return barAmount * 29;
		}
		if (barType == 2351)
		{
			return barAmount * 25;
		}
		if (barType == 2353)
		{
			return barAmount * 38;
		}
		if (barType == 2359)
		{
			return barAmount * 50;
		}
		if (barType == 2361)
		{
			return barAmount * 75;
		}
		if (barType == 2363)
		{
			return barAmount * 75;
		}
		if (barType == 15331)
		{
			return barAmount * 100;
		}
		setAnimation(898);
		return 0;
	}

	public boolean canSmith(int item)
	{
		if (item == 1205 || item == 1351 && playerLevel[13] >= 1)
		{
			return true;
		}
		if (item == 1422 && playerLevel[13] >= 2)
		{
			return true;
		}
		if (item == 1139 && playerLevel[13] >= 3)
		{
			return true;
		}
		if (item == 1277 || item == 819 && playerLevel[13] >= 4)
		{
			return true;
		}
		if (item == 1321 || item == 39 && playerLevel[13] >= 5)
		{
			return true;
		}
		if (item == 1291 && playerLevel[13] >= 6)
		{
			return true;
		}
		if (item == 1155 || item == 864 && playerLevel[13] >= 7)
		{
			return true;
		}
		if (item == 1173 && playerLevel[13] >= 8)
		{
			return true;
		}
		if (item == 1337 && playerLevel[13] >= 9)
		{
			return true;
		}
		if (item == 1375 && playerLevel[13] >= 10)
		{
			return true;
		}
		if (item == 1103 && playerLevel[13] >= 11)
		{
			return true;
		}
		if (item == 1189 && playerLevel[13] >= 12)
		{
			return true;
		}
		if (item == 3095 && playerLevel[13] >= 13)
		{
			return true;
		}
		if (item == 1307 && playerLevel[13] >= 14)
		{
			return true;
		}
		if (item == 1203 && playerLevel[13] >= 15)
		{
			return true;
		}
		if (item == 1087 || item == 1075 || item == 1349
				&& playerLevel[13] >= 16)
		{
			return true;
		}
		if (item == 1420 && playerLevel[13] >= 17)
		{
			return true;
		}
		if (item == 1117 || item == 1137 && playerLevel[13] >= 18)
		{
			return true;
		}
		if (item == 1279 || item == 820 || item == 4820
				&& playerLevel[13] >= 19)
		{
			return true;
		}
		if (item == 1323 || item == 40 && playerLevel[13] >= 20)
		{
			return true;
		}
		if (item == 1293 && playerLevel[13] >= 21)
		{
			return true;
		}
		if (item == 1153 || item == 863 && playerLevel[13] >= 22)
		{
			return true;
		}
		if (item == 1175 && playerLevel[13] >= 23)
		{
			return true;
		}
		if (item == 1335 && playerLevel[13] >= 24)
		{
			return true;
		}
		if (item == 1363 && playerLevel[13] >= 25)
		{
			return true;
		}
		if (item == 1101 || item == 4540 && playerLevel[13] >= 26)
		{
			return true;
		}
		if (item == 1191 && playerLevel[13] >= 27)
		{
			return true;
		}
		if (item == 3096 && playerLevel[13] >= 28)
		{
			return true;
		}
		if (item == 1309 && playerLevel[13] >= 29)
		{
			return true;
		}
		if (item == 1207 && playerLevel[13] >= 30)
		{
			return true;
		}
		if (item == 1067 || item == 1081 || item == 1353
				&& playerLevel[13] >= 31)
		{
			return true;
		}
		if (item == 1424 && playerLevel[13] >= 32)
		{
			return true;
		}
		if (item == 1115 || item == 1141 && playerLevel[13] >= 33)
		{
			return true;
		}
		if (item == 1281 || item == 1539 || item == 821
				&& playerLevel[13] >= 34)
		{
			return true;
		}
		if (item == 1325 || item == 41 && playerLevel[13] >= 35)
		{
			return true;
		}
		if (item == 1295 || item == 2370 && playerLevel[13] >= 36)
		{
			return true;
		}
		if (item == 1157 || item == 865 && playerLevel[13] >= 37)
		{
			return true;
		}
		if (item == 1177 && playerLevel[13] >= 38)
		{
			return true;
		}
		if (item == 1339 && playerLevel[13] >= 39)
		{
			return true;
		}
		if (item == 1365 && playerLevel[13] >= 40)
		{
			return true;
		}
		if (item == 1105 && playerLevel[13] >= 41)
		{
			return true;
		}
		if (item == 1193 && playerLevel[13] >= 42)
		{
			return true;
		}
		if (item == 3097 && playerLevel[13] >= 43)
		{
			return true;
		}
		if (item == 1311 && playerLevel[13] >= 44)
		{
			return true;
		}
		if (item == 1069 || item == 1083 && playerLevel[13] >= 46)
		{
			return true;
		}
		if (item == 1119 && playerLevel[13] >= 48)
		{
			return true;
		}
		if (item == 4544 && playerLevel[13] >= 49)
		{
			return true;
		}
		if (item == 1209 && playerLevel[13] >= 50)
		{
			return true;
		}
		if (item == 1355 && playerLevel[13] >= 51)
		{
			return true;
		}
		if (item == 1428 && playerLevel[13] >= 52)
		{
			return true;
		}
		if (item == 1143 && playerLevel[13] >= 53)
		{
			return true;
		}
		if (item == 1285 || item == 822 || item == 4822
				&& playerLevel[13] >= 54)
		{
			return true;
		}
		if (item == 1329 || item == 42 && playerLevel[13] >= 55)
		{
			return true;
		}
		if (item == 1299 && playerLevel[13] >= 56)
		{
			return true;
		}
		if (item == 1159 || item == 866 && playerLevel[13] >= 57)
		{
			return true;
		}
		if (item == 1181 && playerLevel[13] >= 58)
		{
			return true;
		}
		if (item == 1343 && playerLevel[13] >= 59)
		{
			return true;
		}
		if (item == 1369 && playerLevel[13] >= 60)
		{
			return true;
		}
		if (item == 1109 && playerLevel[13] >= 61)
		{
			return true;
		}
		if (item == 1197 && playerLevel[13] >= 62)
		{
			return true;
		}
		if (item == 3099 && playerLevel[13] >= 63)
		{
			return true;
		}
		if (item == 1315 && playerLevel[13] >= 64)
		{
			return true;
		}
		if (item == 1071 || item == 1085 && playerLevel[13] >= 66)
		{
			return true;
		}
		if (item == 1121 && playerLevel[13] >= 68)
		{
			return true;
		}
		if (item == 1211 && playerLevel[13] >= 70)
		{
			return true;
		}
		if (item == 1430 && playerLevel[13] >= 72)
		{
			return true;
		}
		if (item == 1145 && playerLevel[13] >= 73)
		{
			return true;
		}
		if (item == 1287 || item == 823 || item == 4823
				&& playerLevel[13] >= 74)
		{
			return true;
		}
		if (item == 1331 || item == 43 && playerLevel[13] >= 75)
		{
			return true;
		}
		if (item == 1301 && playerLevel[13] >= 76)
		{
			return true;
		}
		if (item == 1161 || item == 867 && playerLevel[13] >= 77)
		{
			return true;
		}
		if (item == 1183 && playerLevel[13] >= 78)
		{
			return true;
		}
		if (item == 1371 && playerLevel[13] >= 79)
		{
			return true;
		}
		if (item == 1111 && playerLevel[13] >= 81)
		{
			return true;
		}
		if (item == 1199 && playerLevel[13] >= 82)
		{
			return true;
		}
		if (item == 3100 && playerLevel[13] >= 83)
		{
			return true;
		}
		if (item == 1317 && playerLevel[13] >= 84)
		{
			return true;
		}
		if (item == 1213 && playerLevel[13] >= 85)
		{
			return true;
		}
		if (item == 1073 || item == 1091 || item == 1359
				&& playerLevel[13] >= 86)
		{
			return true;
		}
		if (item == 1432 && playerLevel[13] >= 87)
		{
			return true;
		}
		if (item == 1123 || item == 1147 && playerLevel[13] >= 88)
		{
			return true;
		}
		if (item == 1289 || item == 824 || item == 4824
				&& playerLevel[13] >= 89)
		{
			return true;
		}
		if (item == 1333 || item == 44 && playerLevel[13] >= 90)
		{
			return true;
		}
		if (item == 1303 && playerLevel[13] >= 100)
		{
			return true;
		}
		if (item == 1163 || item == 868 && playerLevel[13] >= 110)
		{
			return true;
		}
		if (item == 1185 && playerLevel[13] >= 120)
		{
			return true;
		}
		if (item == 1347 && playerLevel[13] >= 130)
		{
			return true;
		}
		if (item == 1373 && playerLevel[13] >= 140)
		{
			return true;
		}
		if (item == 1113 && playerLevel[13] >= 150)
		{
			return true;
		}
		if (item == 1201 && playerLevel[13] >= 160)
		{
			return true;
		}
		if (item == 3101 && playerLevel[13] >= 170)
		{
			return true;
		}
		if (item == 1319 && playerLevel[13] >= 185)
		{
			return true;
		}
		if (item == 1079 && playerLevel[13] >= 190)
		{
			return true;
		}
		if (item == 1079 || item == 1093 || item == 1319 || item == 1127
				&& playerLevel[13] >= 192)
		{
			return true;
		}
		if (item == 15335 && playerLevel[13] >= 200)
		{
			return true;
		}
		if (item == 15334 && playerLevel[13] >= 200)
		{
			return true;
		}
		if (item == 15333 && playerLevel[13] >= 200)
		{
			return true;
		}
		if (item == 15336 && playerLevel[13] >= 200)
		{
			return true;
		}
		if (item == 15195 && playerLevel[13] >= 200)
		{
			return true;
		}
		else
		{
			return false;
		}
		// return false;
	}

	public void addItemToSmith(int id, int slot, int column, int amount)
	{
		outStream.createFrameVarSizeWord(34); // init item to smith screen
		outStream.putShort(column); // Column Across Smith Screen
		outStream.put(4); // Total Rows?
		outStream.putInt(slot); // Row Down The Smith Screen
		outStream.putShort(id + 1); // item
		outStream.put(amount); // how many there are?
		outStream.endFrameVarSizeWord();
	}

	private void initSmithing(int barType)
	{
		outStream.createFrame(97);
		outStream.putShort(994);
		if (amountOfItem(barType) < 5)
		{
			setText("@red@5bars@red@", 1112);
		}
		else
		{
			setText("@gre@5bars@gre@", 1112);
		}
		if (amountOfItem(barType) < 3)
		{
			setText("@red@3bars@red@", 1109);
			setText("@red@3bars@red@", 1110);
			setText("@red@3bars@red@", 1118);
			setText("@red@3bars@red@", 1111);
			setText("@red@3bars@red@", 1095);
			setText("@red@3bars@red@", 1115);
			setText("@red@3bars@red@", 1090);
		}
		else
		{
			setText("@gre@3bars@gre@", 1109);
			setText("@gre@3bars@gre@", 1110);
			setText("@gre@3bars@gre@", 1118);
			setText("@gre@3bars@gre@", 1111);
			setText("@gre@3bars@gre@", 1095);
			setText("@gre@3bars@gre@", 1115);
			setText("@gre@3bars@gre@", 1090);
		}
		if (amountOfItem(barType) < 2)
		{
			setText("@red@2bars@red@", 1113);
			setText("@red@2bars@red@", 1116);
			setText("@red@2bars@red@", 1114);
			setText("@red@2bars@red@", 1089);
			setText("@red@2bars@red@", 8428);
		}
		else
		{
			setText("@gre@2bars@gre@", 1113);
			setText("@gre@2bars@gre@", 1116);
			setText("@gre@2bars@gre@", 1114);
			setText("@gre@2bars@gre@", 1089);
			setText("@gre@2bars@gre@", 8428);
		}
		if (amountOfItem(barType) < 1)
		{
			setText("@red@1bar@red@", 1125);
			setText("@red@1bar@red@", 1126);
			setText("@red@1bar@red@", 1127);
			setText("@red@1bar@red@", 1124);
			setText("@red@1bar@red@", 1128);
			setText("@red@1bar@red@", 1129);
			setText("@red@1bar@red@", 1130);
			setText("@red@1bar@red@", 13357);
			setText("@red@1bar@red@", 1131);
			setText("@red@1bar@red@", 11459);
		}
		else
		{
			setText("@gre@1bar@gre@", 1125);
			setText("@gre@1bar@gre@", 1126);
			setText("@gre@1bar@gre@", 1127);
			setText("@gre@1bar@gre@", 1124);
			setText("@gre@1bar@gre@", 1128);
			setText("@gre@1bar@gre@", 1129);
			setText("@gre@1bar@gre@", 1130);
			setText("@gre@1bar@gre@", 13357);
			setText("@gre@1bar@gre@", 1131);
			setText("@gre@1bar@gre@", 11459);
		}
		if (barType == 2349)
		{ // Bronze
			if (playerLevel[13] < 18)
			{
				setText("@bla@Plate body@bla@", 1101);
			}
			else
			{
				setText("@whi@Plate body@whi@", 1101);
			}
			if (playerLevel[13] < 16)
			{
				setText("@bla@Plate legs@bla@", 1099);
				setText("@bla@Plate skirt@bla@", 1100);
			}
			else
			{
				setText("@whi@Plate legs@whi@", 1099);
				setText("@whi@Plate skirt@whi@", 1100);
			}
			if (playerLevel[13] < 14)
			{
				setText("@bla@2 hand sword@bla@", 1088);
			}
			else
			{
				setText("@whi@2 hand sword@whi@", 1088);
			}
			if (playerLevel[13] < 13)
			{
				setText("@bla@Claws@bla@", 8429);
			}
			else
			{
				setText("@whi@Claws@whi@", 8429);
			}
			if (playerLevel[13] < 12)
			{
				setText("@bla@Kite shield@bla@", 1105);
			}
			else
			{
				setText("@whi@Kite shield@whi@", 1105);
			}
			if (playerLevel[13] < 11)
			{
				setText("@bla@Chain body@bla@", 1098);
			}
			else
			{
				setText("@whi@Chain body@whi@", 1098);
			}
			if (playerLevel[13] < 10)
			{
				setText("@bla@Battle axe@bla@", 1092);
			}
			else
			{
				setText("@whi@Battle axe@whi@", 1092);
			}
			if (playerLevel[13] < 9)
			{
				setText("@bla@Warhammer@bla@", 1083);
			}
			else
			{
				setText("@whi@Warhammer@whi@", 1083);
			}
			if (playerLevel[13] < 8)
			{
				setText("@bla@Square shield@bla@", 1104);
			}
			else
			{
				setText("@whi@Square shield@whi@", 1104);
			}
			if (playerLevel[13] < 7)
			{
				setText("@bla@Full helm@bla@", 1103);
				setText("@bla@Throwing knives@bla@", 1106);
			}
			else
			{
				setText("@whi@Full helm@whi@", 1103);
				setText("@whi@Throwing knives@whi@", 1106);
			}
			if (playerLevel[13] < 6)
			{
				setText("@bla@Long sword@bla@", 1086);
			}
			else
			{
				setText("@whi@Long sword@whi@", 1086);
			}
			if (playerLevel[13] < 5)
			{
				setText("@bla@Scimitar@bla@", 1087);
				setText("@bla@Arrowtips@bla@", 1108);
			}
			else
			{
				setText("@whi@Scimitar@whi@", 1087);
				setText("@whi@Arrowtips@whi@", 1108);
			}
			if (playerLevel[13] < 4)
			{
				setText("@bla@Sword@bla@", 1085);
				setText("@bla@Dart tips@bla@", 1107);
				setText("@bla@Nails@bla@", 13358);
			}
			else
			{
				setText("@whi@Sword@whi@", 1085);
				setText("@whi@Dart tips@whi@", 1107);
				setText("@whi@Nails@whi@", 13358);
			}
			if (playerLevel[13] < 3)
			{
				setText("@bla@Medium helm@bla@", 1102);
			}
			else
			{
				setText("@whi@Medium helm@whi@", 1102);
			}
			if (playerLevel[13] < 2)
			{
				setText("@bla@Mace@bla@", 1093);
			}
			else
			{
				setText("@whi@Mace@whi@", 1093);
			}
			if (playerLevel[13] < 1)
			{
				setText("@bla@Dagger@bla@", 1094);
				setText("@bla@Axe@bla@", 1091);
			}
			else
			{
				setText("@whi@Dagger@whi@", 1094);
				setText("@whi@Axe@whi@", 1091);
			}
			addItemToSmith(1205, 0, 1119, 1);
			addItemToSmith(1351, 0, 1120, 1);
			addItemToSmith(1103, 0, 1121, 1);
			addItemToSmith(1139, 0, 1122, 1);
			addItemToSmith(819, 0, 1123, 10);
			addItemToSmith(1277, 1, 1119, 1);
			addItemToSmith(1422, 1, 1120, 1);
			addItemToSmith(1075, 1, 1121, 1);
			addItemToSmith(1155, 1, 1122, 1);
			addItemToSmith(39, 1, 1123, 15);
			addItemToSmith(1321, 2, 1119, 1);
			addItemToSmith(1337, 2, 1120, 1);
			addItemToSmith(1087, 2, 1121, 1);
			addItemToSmith(1173, 2, 1122, 1);
			addItemToSmith(864, 2, 1123, 5);
			addItemToSmith(1291, 3, 1119, 1);
			addItemToSmith(1375, 3, 1120, 1);
			addItemToSmith(1117, 3, 1121, 1);
			addItemToSmith(1189, 3, 1122, 1);
			// addItemToSmith(0,3,1123);
			setText("", 1132);
			setText("", 1096);
			addItemToSmith(1307, 4, 1119, 1);
			addItemToSmith(3095, 4, 1120, 1);
			// addItemToSmith(1307,4,1121);
			setText("", 11459);
			setText("", 11461);
			addItemToSmith(4819, 4, 1122, 1);
			// addItemToSmith(1307,4,1123);
			setText("", 1135);
			setText("", 1134);
		}
		if (barType == 2351)
		{ // Iron
			if (playerLevel[13] < 33)
			{
				setText("@bla@Plate body@bla@", 1101);
			}
			else
			{
				setText("@whi@Plate body@whi@", 1101);
			}
			if (playerLevel[13] < 31)
			{
				setText("@bla@Plate legs@bla@", 1099);
				setText("@bla@Plate skirt@bla@", 1100);
			}
			else
			{
				setText("@whi@Plate legs@whi@", 1099);
				setText("@whi@Plate skirt@whi@", 1100);
			}
			if (playerLevel[13] < 29)
			{
				setText("@bla@2 hand sword@bla@", 1088);
			}
			else
			{
				setText("@whi@2 hand sword@whi@", 1088);
			}
			if (playerLevel[13] < 28)
			{
				setText("@bla@Claws@bla@", 8429);
			}
			else
			{
				setText("@whi@Claws@whi@", 8429);
			}
			if (playerLevel[13] < 27)
			{
				setText("@bla@Kite shield@bla@", 1105);
			}
			else
			{
				setText("@whi@Kite shield@whi@", 1105);
			}
			if (playerLevel[13] < 26)
			{
				setText("@bla@Chain body@bla@", 1098);
				setText("@bla@Oil lantern frame@bla@", 11461);
			}
			else
			{
				setText("@whi@Chain body@whi@", 1098);
				setText("@whi@Oil lantern frame@whi@", 11461);
			}
			if (playerLevel[13] < 25)
			{
				setText("@bla@Battle axe@bla@", 1092);
			}
			else
			{
				setText("@whi@Battle axe@whi@", 1092);
			}
			if (playerLevel[13] < 24)
			{
				setText("@bla@Warhammer@bla@", 1083);
			}
			else
			{
				setText("@whi@Warhammer@whi@", 1083);
			}
			if (playerLevel[13] < 23)
			{
				setText("@bla@Square shield@bla@", 1104);
			}
			else
			{
				setText("@whi@Square shield@whi@", 1104);
			}
			if (playerLevel[13] < 22)
			{
				setText("@bla@Full helm@bla@", 1103);
				setText("@bla@Throwing knives@bla@", 1106);
			}
			else
			{
				setText("@whi@Full helm@whi@", 1103);
				setText("@whi@Throwing knives@whi@", 1106);
			}
			if (playerLevel[13] < 21)
			{
				setText("@bla@Long sword@bla@", 1086);
			}
			else
			{
				setText("@whi@Long sword@whi@", 1086);
			}
			if (playerLevel[13] < 20)
			{
				setText("@bla@Scimitar@bla@", 1087);
				setText("@bla@Arrowtips@bla@", 1108);
			}
			else
			{
				setText("@whi@Scimitar@whi@", 1087);
				setText("@whi@Arrowtips@whi@", 1108);
			}
			if (playerLevel[13] < 19)
			{
				setText("@bla@Sword@bla@", 1085);
				setText("@bla@Dart tips@bla@", 1107);
				setText("@bla@Nails@bla@", 13358);
			}
			else
			{
				setText("@whi@Sword@whi@", 1085);
				setText("@whi@Dart tips@whi@", 1107);
				setText("@whi@Nails@whi@", 13358);
			}
			if (playerLevel[13] < 18)
			{
				setText("@bla@Medium helm@bla@", 1102);
			}
			else
			{
				setText("@whi@Medium helm@whi@", 1102);
			}
			if (playerLevel[13] < 17)
			{
				setText("@bla@Mace@bla@", 1093);
			}
			else
			{
				setText("@whi@Mace@whi@", 1093);
			}
			if (playerLevel[13] < 16)
			{
				setText("@bla@Axe@bla@", 1091);
			}
			else
			{
				setText("@whi@Axe@whi@", 1091);
			}
			if (playerLevel[13] < 15)
			{
				setText("@bla@Dagger@bla@", 1094);
			}
			else
			{
				setText("@whi@Dagger@whi@", 1094);
			}
			addItemToSmith(1203, 0, 1119, 1);
			addItemToSmith(1349, 0, 1120, 1);
			addItemToSmith(1101, 0, 1121, 1);
			addItemToSmith(1137, 0, 1122, 1);
			addItemToSmith(820, 0, 1123, 10);
			addItemToSmith(1279, 1, 1119, 1);
			addItemToSmith(1420, 1, 1120, 1);
			addItemToSmith(1067, 1, 1121, 1);
			addItemToSmith(1153, 1, 1122, 1);
			addItemToSmith(40, 1, 1123, 15);
			addItemToSmith(1323, 2, 1119, 1);
			addItemToSmith(1335, 2, 1120, 1);
			addItemToSmith(1081, 2, 1121, 1);
			addItemToSmith(1175, 2, 1122, 1);
			addItemToSmith(863, 2, 1123, 5);
			addItemToSmith(1293, 3, 1119, 1);
			addItemToSmith(1363, 3, 1120, 1);
			addItemToSmith(1115, 3, 1121, 1);
			addItemToSmith(1191, 3, 1122, 1);
			setText("", 1132);
			setText("", 1096);
			addItemToSmith(1309, 4, 1119, 1);
			addItemToSmith(3096, 4, 1120, 1);
			addItemToSmith(4540, 4, 1121, 1);
			addItemToSmith(4820, 4, 1122, 1);
			setText("", 1135);
			setText("", 1134);
		}
		if (barType == 2353)
		{ // Steel
			if (playerLevel[13] < 49)
			{
				setText("@bla@Bullseye lantern@bla@", 11461);
			}
			else
			{
				setText("@whi@Bullseye lantern@whi@", 11461);
			}
			if (playerLevel[13] < 48)
			{
				setText("@bla@Plate body@bla@", 1101);
			}
			else
			{
				setText("@whi@Plate body@whi@", 1101);
			}
			if (playerLevel[13] < 46)
			{
				setText("@bla@Plate legs@bla@", 1099);
				setText("@bla@Plate skirt@bla@", 1100);
			}
			else
			{
				setText("@whi@Plate legs@whi@", 1099);
				setText("@whi@Plate skirt@whi@", 1100);
			}
			if (playerLevel[13] < 44)
			{
				setText("@bla@2 hand sword@bla@", 1088);
			}
			else
			{
				setText("@whi@2 hand sword@whi@", 1088);
			}
			if (playerLevel[13] < 43)
			{
				setText("@bla@Claws@bla@", 8429);
			}
			else
			{
				setText("@whi@Claws@whi@", 8429);
			}
			if (playerLevel[13] < 42)
			{
				setText("@bla@Kite shield@bla@", 1105);
			}
			else
			{
				setText("@whi@Kite shield@whi@", 1105);
			}
			if (playerLevel[13] < 41)
			{
				setText("@bla@Chain body@bla@", 1098);
			}
			else
			{
				setText("@whi@Chain body@whi@", 1098);
			}
			if (playerLevel[13] < 40)
			{
				setText("@bla@Battle axe@bla@", 1092);
			}
			else
			{
				setText("@whi@Battle axe@whi@", 1092);
			}
			if (playerLevel[13] < 39)
			{
				setText("@bla@Warhammer@bla@", 1083);
			}
			else
			{
				setText("@whi@Warhammer@whi@", 1083);
			}
			if (playerLevel[13] < 38)
			{
				setText("@bla@Square shield@bla@", 1104);
			}
			else
			{
				setText("@whi@Square shield@whi@", 1104);
			}
			if (playerLevel[13] < 37)
			{
				setText("@bla@Full helm@bla@", 1103);
				setText("@bla@Throwing knives@bla@", 1106);
			}
			else
			{
				setText("@whi@Full helm@whi@", 1103);
				setText("@whi@Throwing knives@whi@", 1106);
			}
			if (playerLevel[13] < 36)
			{
				setText("@bla@Long sword@bla@", 1086);
				setText("@bla@Studs@bla@", 1134);
			}
			else
			{
				setText("@whi@Long sword@whi@", 1086);
				setText("@whi@Studs@whi@", 1134);
			}
			if (playerLevel[13] < 35)
			{
				setText("@bla@Scimitar@bla@", 1087);
				setText("@bla@Arrowtips@bla@", 1108);
			}
			else
			{
				setText("@whi@Scimitar@whi@", 1087);
				setText("@whi@Arrowtips@whi@", 1108);
			}
			if (playerLevel[13] < 34)
			{
				setText("@bla@Sword@bla@", 1085);
				setText("@bla@Dart tips@bla@", 1107);
				setText("@bla@Nails@bla@", 13358);
			}
			else
			{
				setText("@whi@Sword@whi@", 1085);
				setText("@whi@Dart tips@whi@", 1107);
				setText("@whi@Nails@whi@", 13358);
			}
			if (playerLevel[13] < 33)
			{
				setText("@bla@Medium helm@bla@", 1102);
			}
			else
			{
				setText("@whi@Medium helm@whi@", 1102);
			}
			if (playerLevel[13] < 32)
			{
				setText("@bla@Mace@bla@", 1093);
			}
			else
			{
				setText("@whi@Mace@whi@", 1093);
			}
			if (playerLevel[13] < 31)
			{
				setText("@bla@Axe@bla@", 1091);
			}
			else
			{
				setText("@whi@Axe@whi@", 1091);
			}
			if (playerLevel[13] < 30)
			{
				setText("@bla@Dagger@bla@", 1094);
			}
			else
			{
				setText("@whi@Dagger@whi@", 1094);
			}
			addItemToSmith(1207, 0, 1119, 1);
			addItemToSmith(1353, 0, 1120, 1);
			addItemToSmith(1105, 0, 1121, 1);
			addItemToSmith(1141, 0, 1122, 1);
			addItemToSmith(821, 0, 1123, 10);
			addItemToSmith(1281, 1, 1119, 1);
			addItemToSmith(1424, 1, 1120, 1);
			addItemToSmith(1069, 1, 1121, 1);
			addItemToSmith(1157, 1, 1122, 1);
			addItemToSmith(41, 1, 1123, 15);
			addItemToSmith(1325, 2, 1119, 1);
			addItemToSmith(1339, 2, 1120, 1);
			addItemToSmith(1083, 2, 1121, 1);
			addItemToSmith(1177, 2, 1122, 1);
			addItemToSmith(865, 2, 1123, 5);
			addItemToSmith(1295, 3, 1119, 1);
			addItemToSmith(1365, 3, 1120, 1);
			addItemToSmith(1119, 3, 1121, 1);
			addItemToSmith(1193, 3, 1122, 1);
			setText("", 1132);
			setText("", 1096);
			addItemToSmith(1311, 4, 1119, 1);
			addItemToSmith(3097, 4, 1120, 1);
			addItemToSmith(4544, 4, 1121, 1);
			addItemToSmith(1539, 4, 1122, 1);
			addItemToSmith(2370, 4, 1123, 1);
			if (amountOfItem(barType) < 1)
			{
				setText("@red@1bar@red@", 1135);
			}
			else
			{
				setText("@gre@1bar@gre@", 1135);
			}
		}
		if (barType == 2359)
		{
			if (playerLevel[13] < 68)
			{
				setText("@bla@Plate body@bla@", 1101);
			}
			else
			{
				setText("@whi@Plate body@whi@", 1101);
			}
			if (playerLevel[13] < 66)
			{
				setText("@bla@Plate legs@bla@", 1099);
				setText("@bla@Plate skirt@bla@", 1100);
			}
			else
			{
				setText("@whi@Plate legs@whi@", 1099);
				setText("@whi@Plate skirt@whi@", 1100);
			}
			if (playerLevel[13] < 64)
			{
				setText("@bla@2 hand sword@bla@", 1088);
			}
			else
			{
				setText("@whi@2 hand sword@whi@", 1088);
			}
			if (playerLevel[13] < 63)
			{
				setText("@bla@Claws@bla@", 8429);
			}
			else
			{
				setText("@whi@Claws@whi@", 8429);
			}
			if (playerLevel[13] < 62)
			{
				setText("@bla@Kite shield@bla@", 1105);
			}
			else
			{
				setText("@whi@Kite shield@whi@", 1105);
			}
			if (playerLevel[13] < 61)
			{
				setText("@bla@Chain body@bla@", 1098);
			}
			else
			{
				setText("@whi@Chain body@whi@", 1098);
			}
			if (playerLevel[13] < 60)
			{
				setText("@bla@Battle axe@bla@", 1092);
			}
			else
			{
				setText("@whi@Battle axe@whi@", 1092);
			}
			if (playerLevel[13] < 59)
			{
				setText("@bla@Warhammer@bla@", 1083);
			}
			else
			{
				setText("@whi@Warhammer@whi@", 1083);
			}
			if (playerLevel[13] < 58)
			{
				setText("@bla@Square shield@bla@", 1104);
			}
			else
			{
				setText("@whi@Square shield@whi@", 1104);
			}
			if (playerLevel[13] < 57)
			{
				setText("@bla@Full helm@bla@", 1103);
				setText("@bla@Throwing knives@bla@", 1106);
			}
			else
			{
				setText("@whi@Full helm@whi@", 1103);
				setText("@whi@Throwing knives@whi@", 1106);
			}
			if (playerLevel[13] < 56)
			{
				setText("@bla@Long sword@bla@", 1086);
			}
			else
			{
				setText("@whi@Long sword@whi@", 1086);
			}
			if (playerLevel[13] < 55)
			{
				setText("@bla@Scimitar@bla@", 1087);
				setText("@bla@Arrowtips@bla@", 1108);
			}
			else
			{
				setText("@whi@Scimitar@whi@", 1087);
				setText("@whi@Arrowtips@whi@", 1108);
			}
			if (playerLevel[13] < 54)
			{
				setText("@bla@Sword@bla@", 1085);
				setText("@bla@Dart tips@bla@", 1107);
				setText("@bla@Nails@bla@", 13358);
			}
			else
			{
				setText("@whi@Sword@whi@", 1085);
				setText("@whi@Dart tips@whi@", 1107);
				setText("@whi@Nails@whi@", 13358);
			}
			if (playerLevel[13] < 53)
			{
				setText("@bla@Medium helm@bla@", 1102);
			}
			else
			{
				setText("@whi@Medium helm@whi@", 1102);
			}
			if (playerLevel[13] < 52)
			{
				setText("@bla@Mace@bla@", 1093);
			}
			else
			{
				setText("@whi@Mace@whi@", 1093);
			}
			if (playerLevel[13] < 51)
			{
				setText("@bla@Axe@bla@", 1091);
			}
			else
			{
				setText("@whi@Axe@whi@", 1091);
			}
			if (playerLevel[13] < 50)
			{
				setText("@bla@Dagger@bla@", 1094);
			}
			else
			{
				setText("@whi@Dagger@whi@", 1094);
			}
			addItemToSmith(1209, 0, 1119, 1);
			addItemToSmith(1355, 0, 1120, 1);
			addItemToSmith(1109, 0, 1121, 1);
			addItemToSmith(1143, 0, 1122, 1);
			addItemToSmith(822, 0, 1123, 10);
			addItemToSmith(1285, 1, 1119, 1);
			addItemToSmith(1355, 1, 1120, 1);
			addItemToSmith(1071, 1, 1121, 1);
			addItemToSmith(1159, 1, 1122, 1);
			addItemToSmith(42, 1, 1123, 15);
			addItemToSmith(1329, 2, 1119, 1);
			addItemToSmith(1343, 2, 1120, 1);
			addItemToSmith(1085, 2, 1121, 1);
			addItemToSmith(1181, 2, 1122, 1);
			addItemToSmith(866, 2, 1123, 5);
			addItemToSmith(1299, 3, 1119, 1);
			addItemToSmith(1369, 3, 1120, 1);
			addItemToSmith(1121, 3, 1121, 1);
			addItemToSmith(1197, 3, 1122, 1);
			setText("", 1132);
			setText("", 1096);
			addItemToSmith(1315, 4, 1119, 1);
			addItemToSmith(3099, 4, 1120, 1);
			addItemToSmith(4822, 4, 1122, 1);
			setText("", 1135);
			setText("", 1134);
			setText("", 11461);
			setText("", 11459);
		}
		if (barType == 2361)
		{ // Addy
			if (playerLevel[13] < 88)
			{
				setText("@bla@Plate body@bla@", 1101);
			}
			else
			{
				setText("@whi@Plate body@whi@", 1101);
			}
			if (playerLevel[13] < 86)
			{
				setText("@bla@Plate legs@bla@", 1099);
				setText("@bla@Plate skirt@bla@", 1100);
			}
			else
			{
				setText("@whi@Plate legs@whi@", 1099);
				setText("@whi@Plate skirt@whi@", 1100);
			}
			if (playerLevel[13] < 84)
			{
				setText("@bla@2 hand sword@bla@", 1088);
			}
			else
			{
				setText("@whi@2 hand sword@whi@", 1088);
			}
			if (playerLevel[13] < 83)
			{
				setText("@bla@Claws@bla@", 8429);
			}
			else
			{
				setText("@whi@Claws@whi@", 8429);
			}
			if (playerLevel[13] < 82)
			{
				setText("@bla@Kite shield@bla@", 1105);
			}
			else
			{
				setText("@whi@Kite shield@whi@", 1105);
			}
			if (playerLevel[13] < 81)
			{
				setText("@bla@Chain body@bla@", 1098);
			}
			else
			{
				setText("@whi@Chain body@whi@", 1098);
			}
			if (playerLevel[13] < 80)
			{
				setText("@bla@Battle axe@bla@", 1092);
			}
			else
			{
				setText("@whi@Battle axe@whi@", 1092);
			}
			if (playerLevel[13] < 79)
			{
				setText("@bla@Warhammer@bla@", 1083);
			}
			else
			{
				setText("@whi@Warhammer@whi@", 1083);
			}
			if (playerLevel[13] < 78)
			{
				setText("@bla@Square shield@bla@", 1104);
			}
			else
			{
				setText("@whi@Square shield@whi@", 1104);
			}
			if (playerLevel[13] < 77)
			{
				setText("@bla@Full helm@bla@", 1103);
				setText("@bla@Throwing knives@bla@", 1106);
			}
			else
			{
				setText("@whi@Full helm@whi@", 1103);
				setText("@whi@Throwing knives@whi@", 1106);
			}
			if (playerLevel[13] < 76)
			{
				setText("@bla@Long sword@bla@", 1086);
			}
			else
			{
				setText("@whi@Long sword@whi@", 1086);
			}
			if (playerLevel[13] < 75)
			{
				setText("@bla@Scimitar@bla@", 1087);
				setText("@bla@Arrowtips@bla@", 1108);
			}
			else
			{
				setText("@whi@Scimitar@whi@", 1087);
				setText("@whi@Arrowtips@whi@", 1108);
			}
			if (playerLevel[13] < 74)
			{
				setText("@bla@Sword@bla@", 1085);
				setText("@bla@Dart tips@bla@", 1107);
				setText("@bla@Nails@bla@", 13358);
			}
			else
			{
				setText("@whi@Sword@whi@", 1085);
				setText("@whi@Dart tips@whi@", 1107);
				setText("@whi@Nails@whi@", 13358);
			}
			if (playerLevel[13] < 73)
			{
				setText("@bla@Medium helm@bla@", 1102);
			}
			else
			{
				setText("@whi@Medium helm@whi@", 1102);
			}
			if (playerLevel[13] < 72)
			{
				setText("@bla@Mace@bla@", 1093);
			}
			else
			{
				setText("@whi@Mace@whi@", 1093);
			}
			if (playerLevel[13] < 71)
			{
				setText("@bla@Axe@bla@", 1091);
			}
			else
			{
				setText("@whi@Axe@whi@", 1091);
			}
			if (playerLevel[13] < 70)
			{
				setText("@bla@Dagger@bla@", 1094);
			}
			else
			{
				setText("@whi@Dagger@whi@", 1094);
			}
			addItemToSmith(1211, 0, 1119, 1);
			addItemToSmith(1357, 0, 1120, 1);
			addItemToSmith(1111, 0, 1121, 1);
			addItemToSmith(1145, 0, 1122, 1);
			addItemToSmith(823, 0, 1123, 10);
			addItemToSmith(1287, 1, 1119, 1);
			addItemToSmith(1430, 1, 1120, 1);
			addItemToSmith(1073, 1, 1121, 1);
			addItemToSmith(1161, 1, 1122, 1);
			addItemToSmith(43, 1, 1123, 15);
			addItemToSmith(1331, 2, 1119, 1);
			addItemToSmith(1345, 2, 1120, 1);
			addItemToSmith(1091, 2, 1121, 1);
			addItemToSmith(1183, 2, 1122, 1);
			addItemToSmith(867, 2, 1123, 5);
			addItemToSmith(1301, 3, 1119, 1);
			addItemToSmith(1371, 3, 1120, 1);
			addItemToSmith(1123, 3, 1121, 1);
			addItemToSmith(1199, 3, 1122, 1);
			setText("", 1132);
			setText("", 1096);
			addItemToSmith(1317, 4, 1119, 1);
			addItemToSmith(3100, 4, 1120, 1);
			addItemToSmith(4823, 4, 1122, 1);
			setText("", 1135);
			setText("", 1134);
			setText("", 11461);
			setText("", 11459);
		}
		if (barType == 2363)
		{ // Rune
			if (playerLevel[13] < 192)
			{
				setText("@bla@Plate body@bla@", 1101);
				setText("@bla@Plate legs@bla@", 1099);
				setText("@bla@Plate skirt@bla@", 1100);
				setText("@bla@2 hand sword@bla@", 1088);
			}
			else
			{
				setText("@whi@Plate body@whi@", 1101);
				setText("@whi@Plate legs@whi@", 1099);
				setText("@whi@Plate skirt@whi@", 1100);
				setText("@whi@2 hand sword@whi@", 1088);
			}
			if (playerLevel[13] < 190)
			{
				setText("@bla@Claws@bla@", 8429);
			}
			else
			{
				setText("@whi@Claws@whi@", 8429);
			}
			if (playerLevel[13] < 185)
			{
				setText("@bla@Kite shield@bla@", 1105);
			}
			else
			{
				setText("@whi@Kite shield@whi@", 1105);
			}
			if (playerLevel[13] < 180)
			{
				setText("@bla@Chain body@bla@", 1098);
			}
			else
			{
				setText("@whi@Chain body@whi@", 1098);
			}
			if (playerLevel[13] < 170)
			{
				setText("@bla@Battle axe@bla@", 1092);
			}
			else
			{
				setText("@whi@Battle axe@whi@", 1092);
			}
			if (playerLevel[13] < 160)
			{
				setText("@bla@Warhammer@bla@", 1083);
			}
			else
			{
				setText("@whi@Warhammer@whi@", 1083);
			}
			if (playerLevel[13] < 150)
			{
				setText("@bla@Square shield@bla@", 1104);
			}
			else
			{
				setText("@whi@Square shield@whi@", 1104);
			}
			if (playerLevel[13] < 140)
			{
				setText("@bla@Full helm@bla@", 1103);
				setText("@bla@Throwing knives@bla@", 1106);
			}
			else
			{
				setText("@whi@Full helm@whi@", 1103);
				setText("@whi@Throwing knives@whi@", 1106);
			}
			if (playerLevel[13] < 130)
			{
				setText("@bla@Long sword@bla@", 1086);
			}
			else
			{
				setText("@whi@Long sword@whi@", 1086);
			}
			if (playerLevel[13] < 90)
			{
				setText("@bla@Scimitar@bla@", 1087);
				setText("@bla@Arrowtips@bla@", 1108);
			}
			else
			{
				setText("@whi@Scimitar@whi@", 1087);
				setText("@whi@Arrowtips@whi@", 1108);
			}
			if (playerLevel[13] < 89)
			{
				setText("@bla@Sword@bla@", 1085);
				setText("@bla@Dart tips@bla@", 1107);
				setText("@bla@Nails@bla@", 13358);
			}
			else
			{
				setText("@whi@Sword@whi@", 1085);
				setText("@whi@Dart tips@whi@", 1107);
				setText("@whi@Nails@whi@", 13358);
			}
			if (playerLevel[13] < 88)
			{
				setText("@bla@Medium helm@bla@", 1102);
			}
			else
			{
				setText("@whi@Medium helm@whi@", 1102);
			}
			if (playerLevel[13] < 87)
			{
				setText("@bla@Mace@bla@", 1093);
			}
			else
			{
				setText("@whi@Mace@whi@", 1093);
			}
			if (playerLevel[13] < 86)
			{
				setText("@bla@Axe@bla@", 1091);
			}
			else
			{
				setText("@whi@Axe@whi@", 1091);
			}
			if (playerLevel[13] < 85)
			{
				setText("@bla@Dagger@bla@", 1094);
			}
			else
			{
				setText("@whi@Dagger@whi@", 1094);
			}
			addItemToSmith(1213, 0, 1119, 1);
			addItemToSmith(1359, 0, 1120, 1);
			addItemToSmith(1113, 0, 1121, 1);
			addItemToSmith(1147, 0, 1122, 1);
			addItemToSmith(824, 0, 1123, 10);
			addItemToSmith(1289, 1, 1119, 1);
			addItemToSmith(1432, 1, 1120, 1);
			addItemToSmith(1079, 1, 1121, 1);
			addItemToSmith(1163, 1, 1122, 1);
			addItemToSmith(44, 1, 1123, 15);
			addItemToSmith(1333, 2, 1119, 1);
			addItemToSmith(1347, 2, 1120, 1);
			addItemToSmith(1093, 2, 1121, 1);
			addItemToSmith(1185, 2, 1122, 1);
			addItemToSmith(868, 2, 1123, 5);
			addItemToSmith(1303, 3, 1119, 1);
			addItemToSmith(1373, 3, 1120, 1);
			addItemToSmith(1127, 3, 1121, 1);
			addItemToSmith(1201, 3, 1122, 1);
			setText("", 1132);
			setText("", 1096);
			addItemToSmith(1319, 4, 1119, 1);
			addItemToSmith(3101, 4, 1120, 1);
			addItemToSmith(4824, 4, 1122, 1);
			setText("", 1135);
			setText("", 1134);
			setText("", 11461);
			setText("", 11459);
		}
	}

	private void bars2ores()
	{
		if (playerLevel[6] >= -65)
		{
			sendMessage("You turn the bars into ores...");
			if (playerHasItemAmount(2349, 1) == true)
			{
				deleteItem(2349, GetItemSlot(2349), 1);
				addItem(436, 1);
			}
			if (playerHasItemAmount(2351, 1) == true)
			{
				deleteItem(2351, GetItemSlot(2351), 1);
				addItem(440, 1);
			}
			if (playerHasItemAmount(2353, 1) == true)
			{
				deleteItem(2353, GetItemSlot(2353), 1);
				addItem(453, 1);
			}
			if (playerHasItemAmount(2355, 1) == true)
			{
				deleteItem(2355, GetItemSlot(2355), 1);
				addItem(442, 1);
			}
			if (playerHasItemAmount(2357, 1) == true)
			{
				deleteItem(2357, GetItemSlot(2357), 1);
				addItem(444, 1);
			}
			if (playerHasItemAmount(2359, 1) == true)
			{
				deleteItem(2359, GetItemSlot(2359), 1);
				addItem(447, 1);
			}
			if (playerHasItemAmount(2361, 1) == true)
			{
				deleteItem(2361, GetItemSlot(2361), 1);
				addItem(449, 1);
			}
			if (playerHasItemAmount(2363, 1) == true)
			{
				deleteItem(2363, GetItemSlot(2363), 1);
				addItem(451, 1);
			}
			else if ((playerHasItemAmount(2363, 1) == false)
					&& (playerHasItemAmount(2361, 1) == false)
					&& (playerHasItemAmount(2359, 1) == false)
					&& (playerHasItemAmount(2357, 1) == false)
					&& (playerHasItemAmount(2355, 1) == false)
					&& (playerHasItemAmount(2353, 1) == false)
					&& (playerHasItemAmount(2351, 1) == false)
					&& (playerHasItemAmount(2349, 1) == false))
			{
				sendMessage("...but you have no bars to convert");
			}
		}
		else
		{
			sendMessage("You need a magic level of 65 to cast that spell.");
		}
	}

	/* QUEST VOIDS AND INTS */
	// public int questid = 0;
	// public int q1stage = 0;
	// public int q2stage = 0;
	// public int q3stage = 0;
	// public int q4stage = 0;
	// public int totalqp = 0;

	public void showQuestCompleted(String questName, int rewardqp, int totalqp)
	{
		totalqp += rewardqp;
		showInterface(297);
		setText("Congratulations!", 299);
		setText("Close Window", 300);
		setText("You are awarded", 6156);
		setText("Earned QP:", 6158);
		setText("Total QP:", 303);
		setText("You have completed " + questName, 301);
		setText("" + rewardqp, 4444);
		setText("" + totalqp, 304);
	}

	public void loadquest(String questname, String questinfo1,
			String questinfo2, String questinfo3, String questinfo4,
			String questinfo5, String questinfo6, String questinfo7,
			String questinfo8, String questinfo9)
	{
		setText("@dre@Quest", 8144);
		clearQuestInterface();
		setText("@dbl@" + questname, 8145);
		setText("@dbl@@dre@" + questinfo1 + "@dbl@", 8147);
		setText("@dbl@@dre@" + questinfo2 + "@dbl@", 8148);
		setText("@dbl@@dre@" + questinfo3 + "@dbl@", 8149);
		setText("@dbl@@dre@" + questinfo4 + "@dbl@", 8150);
		setText("@dbl@@dre@" + questinfo5 + "@dbl@", 8151);
		setText("@dbl@@dre@" + questinfo6 + "@dbl@", 8152);
		setText("@dbl@@dre@" + questinfo7 + "@dbl@", 8153);
		setText("@dbl@@dre@" + questinfo8 + "@dbl@", 8154);
		setText("@dbl@@dre@" + questinfo9 + "@dbl@", 8155);
		sendQuestSomething(8143);
		showInterface(8134);
		flushOutStream();
	}

	/* END OF QUEST 1 */
	/* CLUE VOIDS AND OTHER STUFF */
	// CLUE_00
	public void createAddMap()
	{
	}

	public void loadothers()
	{
		setText(" Clue debug", 6288);
		setText("Off", 6289);
		setText("On", 6290);
	}

	public void loadquestinterface()
	{
		setText("@whi@Paradise", 640);
		setText("@whi@Info", 663);
		setText("", 682);
		setText("@gre@Click For Pk Rewards!", 7332);
		setText("@gre@Click for Rules!", 7333);
		setText("@gre@Have Fun!", 7334);
		setText("@gre@Paradise.", 7336);
		setText("", 7383);
		setText("@gre@Report all glitches!", 7339);
		setText("", 7338);
		setText("@gre@Click To Gamble 400m", 7340);
		setText("", 7346);
		setText("", 7341);
		setText("", 7342);
		setText("", 7337);
		setText("", 7343);
		setText("", 7335);
		setText("", 7344);
		setText("", 7345);
		setText("", 7347);
		setText("", 7348);
		/* Members Quests */
		setText("", 12772);
		// unknown id
		setText("", 7352);
		setText("", 12129);
		setText("", 8438);
		setText("", 12852);
		setText("", 7354);
		setText("", 7355);
		setText("", 7356);
		setText("", 8679);
		setText("", 7459);
		setText("", 7357);
		setText("", 12836);
		setText("", 7358);
		setText("", 7359);
		setText("", 14169);
		setText("", 10115);
		setText("", 14604);
		setText("", 7360);
		setText("", 12282);
		setText("", 13577);
		setText("", 12839);
		setText("", 7361);
		setText("", 11857);
		setText("", 7362);
		setText("", 7363);
		setText("", 7364);
		setText("", 10135);
		setText("", 4508);
		setText("", 11907);
		setText("", 7365);
		setText("", 7366);
		setText("", 7367);
		setText("", 13389);
		setText("", 7368);
		setText("", 11132);
		setText("", 7369);
		setText("", 12389);
		setText("", 13974);
		setText("", 7370);
		setText("", 8137);
		setText("", 7371);
		setText("", 12345);
		setText("", 7372);
		setText("", 8115);
		// unknown id
		setText("", 8576);
		setText("", 12139);
		setText("", 7373);
		setText("", 7374);
		setText("", 8969);
		setText("", 7375);
		setText("", 7376);
		setText("", 1740);
		setText("", 3278);
		setText("", 7378);
		setText("", 6518);
		setText("", 7379);
		setText("", 7380);
		setText("", 7381);
		setText("", 11858);
		// unknown id
		setText("", 9927);
		setText("", 7349);
		setText("", 7350);
		setText("", 7351);
		setText("", 13356);
		/* END OF ALL QUESTS */
	}

	public void playerMenu()
	{
		clearQuestInterface();
		for (int i = 0; i < PlayerHandler.maxPlayers; i++)
		{
			if (PlayerHandler.players[i] != null)
			{
				{
					setText("@dre@Players", 8144);
					setText("@red@", 8146);
					setText("@red@" + PlayerHandler.players[i].username,
							8147 + i);
				}
			}
		}
		sendQuestSomething(8143);
		showInterface(8134);
		flushOutStream();
	}

	public int playerItemAmountCount = 0;

	public boolean playerHasItemAmount(int itemID, int itemAmount)
	{
		playerItemAmountCount = 0;
		for (int i = 0; i < playerItems.length; i++)
		{
			if (playerItems[i] == itemID + 1)
			{
				playerItemAmountCount = playerItemsN[i];
			}
			if (playerItemAmountCount >= itemAmount)
			{
				return true;
			}
		}
		return false;
	}

	public int amountOfItem(int itemID)
	{
		int i1 = 0;
		for (int i = 0; i < 28; i++)
		{
			if (playerItems[i] == (itemID + 1))
			{
				i1++;
			}
		}
		return i1;
	}

	/* PKING VOIDS FROM RS3Scape */
	public void inCombat()
	{
		LogoutDelay = 2;
	}

	public void entangle()
	{
		EntangleDelay = 20;
	}

	public void uberentangle()
	{
		EntangleDelay = 40;
	}

	public void rush()
	{
		EntangleDelay = 40;
	}

	public void burst()
	{
		EntangleDelay = 50;
	}

	public void blitz()
	{
		EntangleDelay = 60;
	}

	public void barrage()
	{
		EntangleDelay = 80;
	}

	public void rapture()
	{
		EntangleDelay = 160;
	}

	public void handleDeath()
	{
		if ((playerRights == 2) || (playerRights == 3))
		{
			return; /*
					 * Does not handle death to prevent spawned items to be
					 * dropped.
					 */
		}

		for (int r = 0; r < playerEquipment.length; r++)
		{
			try
			{
				int item = playerEquipment[r];
				if ((item > 0) && (item < 20000))
				{
					remove(item, r);
				}
			}
			catch (Exception e)
			{
				sendMessage("ERROR: Removing Equipment");
			}
		}
		for (int rr = 0; rr < playerItems.length; rr++)
		{
			try
			{
				if (playerItems[rr] > 0 && playerItems[rr] < 20000)
				{
					ItemHandler.addItem(playerItems[rr] - 1, absX, absY,
							playerItemsN[rr], KillerId, false);
					deleteItem(playerItems[rr] - 1,
							getItemSlot(playerItems[rr] - 1), playerItemsN[rr]);
				}
			}
			catch (Exception e)
			{
			}
		}
		removeAllItems();
		for (int r = 0; r < playerEquipment.length; r++)
		{
			try
			{
				int item = playerEquipment[r];
				if ((item > 0) && (item < 20000))
				{
					remove(item, r);
				}
			}
			catch (Exception e)
			{
				sendMessage("ERROR: Removing Equipment");
			}
		}
		for (int rr = 0; rr < playerItems.length; rr++)
		{
			try
			{
				if (playerItems[rr] > 0 && playerItems[rr] < 20000)
				{
					ItemHandler.addItem(playerItems[rr] - 1, absX, absY,
							playerItemsN[rr], KillerId, false);
					deleteItem(playerItems[rr] - 1,
							getItemSlot(playerItems[rr] - 1), playerItemsN[rr]);
				}
			}
			catch (Exception e)
			{
			}
		}
		deleteItem(6570, getItemSlot(6570), 1);
		removeAllItems();
		teleportToX = 2907;
		teleportToY = 9708;
		heightLevel = 0;
		sendMessage("Oh dear, you died!");
		hitDiff = 0;
		updateRequired = true;
		appearanceUpdateRequired = true;
	}

	private void Poison()
	{
		{
			if (PoisonDelay <= 1)
			{
				poisondmg = true;
				newhptype = true;
				hptype = 2;
				hitDiff = 1 + MethodCollection.random(2);
				sendMessage("You start to die of poison!");
				PoisonDelay = 40;
				playerLevel[3] -= hitDiff;
				updateRequired = true;
				hitUpdateRequired = true;
				inCombat();
				PoisonClear++;
			}
			if (playerLevel[3] < 1)
			{
				playerLevel[3] = 0;
			}
			if (playerLevel[3] == 0) // && (inclanwar = false)
			{
				ApplyDead();
				teleportToX = 3093;
				teleportToY = 3491;
				updateRequired = true;
				appearanceUpdateRequired = true;
			}
		}
	}

	public void PoisonPlayer()
	{
		Poisoned = false;
		PoisonClear = 0;
		PoisonDelay = 40;
		Poison = 1;
		Poison();
	}

	public void closeInterface()
	{
		outStream.createFrame(219);
	}

	private int TakeMiscTimer = 0;

	public void TakeMisc(String message1, String message2, String message3,
			int reqSkillNum, int lvlReq, int XPskillnum, int XPamount,
			int item, int itemAmount, int delay, int emote)
	{
		if (TakeMiscTimer == 0)
		{
			if (playerLevel[reqSkillNum] >= lvlReq)
			{
				setAnimation(emote);
				sendMessage(message1);
				sendMessage(message2);
				addItem(item, itemAmount);
				addSkillXP(XPamount, XPskillnum);
				TakeMiscTimer = delay;
			}
			else if (playerLevel[reqSkillNum] < lvlReq)
			{
				sendMessage("You need a " + statName[reqSkillNum]
						+ " level of " + lvlReq + " to " + message3 + ".");
			}
		}
	}

	private int theifTimer = 0;

	public void TheifStall(String stallName, String message, int lvlReq,
			int XPamount, int item, int itemAmount, int delay, int emote)
	{
		if (theifTimer == 0)
		{
			if (playerLevel[17] >= lvlReq)
			{
				setAnimation(emote);
				sendMessage("You steal from the " + stallName);
				sendMessage(message);
				addItem(item, itemAmount);
				addSkillXP(XPamount, 17);
				theifTimer = delay;
			}
			else if (playerLevel[17] < lvlReq)
			{
				sendMessage("You need a theiving level of " + lvlReq
						+ " to theif from this stall.");
			}
		}
	}

	public void TakeCape(String god, int reqSkillNum, int lvlReq,
			int XPSkillNum, int XPamount, int item, int itemAmount, int delay,
			int emote)
	{
		if (theifTimer == 0)
		{
			if (playerLevel[reqSkillNum] >= lvlReq)
			{
				setAnimation(emote);
				sendMessage("You bow down to " + god);
				sendMessage("You recieve the cape of " + god + ".");
				addSkillXP(XPamount, XPSkillNum);
				addItem(item, itemAmount);
				theifTimer = delay;
			}
			else if (playerLevel[reqSkillNum] < lvlReq)
			{
				sendMessage("You need a " + statName[reqSkillNum]
						+ " level of " + lvlReq + " to pray to " + god + ".");
			}
		}
	}

	/* RUNECRAFT */
	public int RCTimer = 0;

	public void CraftRunesRift(String runeName, int lvlReq, int XPamount,
			int rune, int runeAmount, int delay, int emote)
	{
		if (RCTimer == 0)
		{
			if (playerLevel[20] >= lvlReq)
			{
				setAnimation(emote);
				sendMessage("You craft " + runeAmount + " " + runeName
						+ " runes.");
				addSkillXP(XPamount, 20);
				addItem(rune, runeAmount);
				RCTimer = delay;
			}
			else if (playerLevel[20] < lvlReq)
			{
				sendMessage("You need a runecrafting level of " + lvlReq
						+ " to craft " + runeName + " runes.");
			}
		}
	}

	/* AGILITY */
	public int AgilityTimer = 0;

	public void Agility(String message, int newX, int newY, int lvlReq,
			int XPgained, int delay, int emote)
	{
		if (AgilityTimer == 0)
		{
			if (playerLevel[16] >= lvlReq)
			{
				sendMessage(message);
				addSkillXP(XPgained, 16);
				teleportToX = newX;
				teleportToY = newY;
				AgilityTimer = delay;
				setAnimation(emote);
				updateRequired = true;
				appearanceUpdateRequired = true;
			}
			else if (playerLevel[16] < lvlReq)
			{
				sendMessage("You need an agility level of " + lvlReq
						+ " to use this obstacle.");
			}
		}
	}

	/* WOODCUTTING */
	public boolean WC()
	{
		if (WCTimer <= 0)
		{
			if (TreeHP == 0)
			{
				AddGlobalObj(TreeX, TreeY, 1341, 0, 10);
				sendMessage("This tree has run out of logs");
				ResetWC();
				return false;
			}
			else
			{
				if (!hasAxe())
				{
					sendMessage("You need an axe to chop down this tree.");
					ResetWC();
				}
				else if (hasAxe())
				{
					addSkillXP(WCxp, 8);
					if (!addItem(logID, logAmount))
					{
						ResetWC();
						return false;
					}
					else
					{
						sendMessage("You cut the tree, and get some money!");
						WCTimer = TreeTimer;
						TreeHP--;
						return true;
					}
				}
			}
		}
		return false;
	}

	public void ResetWC()
	{
		IsWcing = false;
		WCTimer = 0;
		logID = 0;
		logAmount = 0;
		TreeHP = 20;
		TreeX = 0;
		TreeY = 0;
		TreeTimer = 0;
		resetAnimation();
		resetanim = 4;
	}

	public void Woodcutting(String logName, int lvlReq, int XPamount, int log,
			int amount, int obj, int X, int Y, int emote)
	{
		if (WCTimer <= 0)
		{
			if (playerLevel[8] >= lvlReq)
			{
				pEmote = emote;
				resetanim = 999;
				sendMessage("You begin to cut the " + logName + " tree.");
				logID = log;
				logAmount = amount;
				TreeX = X;
				TreeY = Y;
				TreeTimer = GetWCBonus(obj);
				WCxp = XPamount;
				WCTimer = TreeTimer;
				IsWcing = true;
			}
			else if (playerLevel[8] < lvlReq)
			{
				sendMessage("You need a woodcutting level of " + lvlReq
						+ " to chop down this tree.");
			}
		}
	}

	public int GetWCBonus(int objectID)
	{
		int lvlB = (playerLevel[8] / 4);
		switch (objectID)
		{
		case 1276:
		case 1277:
		case 1278:
		case 1279:
		case 1280:
		case 1282:
		case 1283:
		case 1284:
		case 1285:
		case 1286:
		case 1289:
		case 1290:
		case 1291:
		case 1315:
		case 1316:
		case 1318:
		case 1319:
		case 1330:
		case 1331:
		case 1332:
		case 1365:
		case 1383:
		case 1384:
		case 2409:
		case 3033:
		case 3034:
		case 3035:
		case 3036:
		case 3881:
		case 3882:
		case 3883:
		case 5902:
		case 5903:
		case 5904:
			return 25 - lvlB;
			// break;
		case 1281:
		case 3037:
			return 35 - lvlB;
			// break;
		case 1308:
		case 5551:
		case 5552:
		case 5553:
			return 45 - lvlB;
			// break;
		case 1307:
		case 4674:
			return 60 - lvlB;
			// break;
		case 1309:
			return 85 - lvlB;
			// break;
		case 1292:
		case 1306:
			return 100 - lvlB;
			// break;
		}
		return 0;
	}

	public void Mining() // 2090-2111 2119-2140 2704 3042 3043 3431 4676
							// 6943-6948
	{
		if (actionName.equalsIgnoreCase("mineclay"))
		{
			addSkillXP(5, 14);
			addItem(434, 1);
		}
		if (actionName.equalsIgnoreCase("minecopper"))
		{
			addSkillXP(18, 14);
			addItem(436, 1);
		}
		if (actionName.equalsIgnoreCase("minetin"))
		{
			addSkillXP(18, 14);
			addItem(438, 1);
		}
		if (actionName.equalsIgnoreCase("minelimestone")) // 4027-4030
		{
			addSkillXP(27, 14);
			addItem(3211, 1);
		}
		if (actionName.equalsIgnoreCase("mineessence"))
		{
			addSkillXP(5, 14);
			addItem(1436, 1);
		}
		if (actionName.equalsIgnoreCase("mineblurite") && playerLevel[14] >= 10)
		{
			addSkillXP(18, 14);
			addItem(668, 1);
		}
		else if (actionName.equalsIgnoreCase("mineblurite")
				&& playerLevel[14] < 10)
		{
			sendMessage("You must be at least level 10 to mine this rock!");
		}
		if (actionName.equalsIgnoreCase("mineiron") && playerLevel[14] >= 15)
		{
			addSkillXP(35, 14);
			addItem(440, 1);
		}
		else if (actionName.equalsIgnoreCase("mineiron")
				&& playerLevel[14] < 15)
		{
			sendMessage("You must be at least level 15 to mine this rock!");
		}
		if (actionName.equalsIgnoreCase("minesilver") && playerLevel[14] >= 20)
		{
			addSkillXP(40, 14);
			addItem(442, 1);
		}
		else if (actionName.equalsIgnoreCase("minesilver")
				&& playerLevel[14] < 20)
		{
			sendMessage("You must be at least level 20 to mine this rock!");
		}
		if (actionName.equalsIgnoreCase("minecoal") && playerLevel[14] >= 30)
		{
			addSkillXP(50, 14);
			addItem(453, 1);
		}
		else if (actionName.equalsIgnoreCase("minecoal")
				&& playerLevel[14] < 30)
		{
			sendMessage("You must be at least level 30 to mine this rock!");
		}
		if (actionName.equalsIgnoreCase("minegold") && playerLevel[14] >= 60)
		{
			addSkillXP(100, 14);
			addItem(444, 1);
		}
		else if (actionName.equalsIgnoreCase("minegold")
				&& playerLevel[14] < 59)
		{
			sendMessage("You must be at least level 60 to mine this rock!");
		}
		if (actionName.equalsIgnoreCase("minegems") && playerLevel[14] >= 100)
		{
			addSkillXP(125, 14);
		}
		else if (actionName.equalsIgnoreCase("minegems")
				&& playerLevel[14] < 99)
		{
			sendMessage("You must be at least level 100 to mine this rock!");
		}
		if (actionName.equalsIgnoreCase("minemithril")
				&& playerLevel[14] >= 120)
		{
			addSkillXP(150, 14);
			addItem(447, 1);
		}
		else if (actionName.equalsIgnoreCase("minemithril")
				&& playerLevel[14] < 119)
		{
			sendMessage("You must be at least level 120 to mine this rock!");
		}
		if (actionName.equalsIgnoreCase("mineadamant")
				&& playerLevel[14] >= 150)
		{
			addSkillXP(250, 14);
			addItem(449, 1);
		}
		else if (actionName.equalsIgnoreCase("mineadamant")
				&& playerLevel[14] < 149)
		{
			sendMessage("You must be at least level 150 to mine this rock!");
		}
		if (actionName.equalsIgnoreCase("minerunite") && playerLevel[14] >= 190)
		{
			addSkillXP(500, 14);
			addItem(Item2.randomRuneRock(), 1);
		}
		else if (actionName.equalsIgnoreCase("minerunite")
				&& playerLevel[14] < 189)
		{
			sendMessage("You must be at least level 190 to mine this rock!");
		}
		if (actionName.equalsIgnoreCase("mineelemental")) // 3403
		{
			addItem(2892, 1);
		}
	}

	public void make()
	{
		if (actionTimer == 0)
		{
			addItem(383, 1);
			sendMessage("You catch a shark.");
			addSkillXP((1700 * playerLevel[10]), 10);
			actionName = "make";
			actionTimer = 10;
			pEmote = 0x306;
			updateRequired = true;
			appearanceUpdateRequired = true;
		}
	}

	public void turtle()
	{
		if (actionTimer == 0)
		{
			addItem(395, 1);
			sendMessage("You catch a turtle.");
			addSkillXP((700 * playerLevel[10]), 10);
			actionName = "make";
			actionTimer = 10;
			pEmote = 0x306;
			updateRequired = true;
			appearanceUpdateRequired = true;
		}
	}

	public void manta()
	{
		if (actionTimer == 0)
		{
			addItem(389, 1);
			sendMessage("You catch a manta ray.");
			addSkillXP((2000 * playerLevel[10]), 10);
			actionName = "make";
			actionTimer = 10;
			pEmote = 0x306;
			updateRequired = true;
			appearanceUpdateRequired = true;
		}
	}

	public void lob()
	{
		if (actionTimer == 0)
		{
			addItem(377, 1);
			sendMessage("You catch a lobster.");
			addSkillXP((1000 * playerLevel[10]), 10);
			actionName = "lob";
			actionTimer = 10;
			pEmote = 0x306;
			updateRequired = true;
			appearanceUpdateRequired = true;
		}
	}

	public void carb()
	{
		if (actionTimer == 0)
		{
			addItem(363, 1);
			sendMessage("You catch a bass.");
			addSkillXP((500 * playerLevel[10]), 10);
			actionName = "carp";
			actionTimer = 10;
			pEmote = 0x306;
			updateRequired = true;
			appearanceUpdateRequired = true;
		}
	}

	public void iron()
	{
		if (actionTimer == 0)
		{
			addItem(2351, 1);
			deleteItem(440, getItemSlot(440), 1);
			sendMessage("You make an iron bar.");
			addSkillXP((100 * playerLevel[13]), 13);
			actionName = "iron";
			actionTimer = 10;
			startAnimation(0x383);
			updateRequired = true;
			appearanceUpdateRequired = true;
		}
	}

	public void stick()
	{
		if (actionTimer == 0)
		{
			sendMessage("You whacked the cow!");
			addSkillXP((140 * playerLevel[18]), 18);
			actionName = "stick";
			actionTimer = 10;
			startAnimation(0x422);
			updateRequired = true;
			appearanceUpdateRequired = true;
		}
	}

	public void silver2()
	{
		if (actionTimer == 0)
		{
			addItem(2355, 1);
			sendMessage("You make a silver bar.");
			deleteItem(442, getItemSlot(442), 1);
			addSkillXP((150 * playerLevel[13]), 13);
			actionName = "silver";
			actionTimer = 10;
			startAnimation(0x383);
			updateRequired = true;
			appearanceUpdateRequired = true;
		}
	}

	public void rune()
	{
		if (actionTimer == 0)
		{
			addItem(2363, 1);
			sendMessage("You make a rune bar.");
			deleteItem(451, getItemSlot(451), 1);
			addSkillXP((1000 * playerLevel[13]), 13);
			actionName = "rune";
			actionTimer = 10;
			startAnimation(0x383);
			updateRequired = true;
			appearanceUpdateRequired = true;
		}
	}

	public void adam()
	{
		if (actionTimer == 0)
		{
			addItem(2361, 1);
			sendMessage("You make a adamant bar.");
			deleteItem(449, getItemSlot(449), 1);
			addSkillXP((750 * playerLevel[13]), 13);
			actionName = "adam";
			actionTimer = 10;
			startAnimation(0x383);
			updateRequired = true;
			appearanceUpdateRequired = true;
		}
	}

	public void mith()
	{
		if (actionTimer == 0)
		{
			addItem(2359, 1);
			sendMessage("You make a mith bar.");
			deleteItem(447, getItemSlot(447), 1);
			addSkillXP((500 * playerLevel[13]), 13);
			actionName = "mith";
			actionTimer = 10;
			startAnimation(0x383);
			updateRequired = true;
			appearanceUpdateRequired = true;
		}
	}

	public void gold()
	{
		if (actionTimer == 0)
		{
			addItem(2357, 1);
			sendMessage("You make a gold bar.");
			deleteItem(444, getItemSlot(444), 1);
			addSkillXP((250 * playerLevel[13]), 13);
			actionName = "gold";
			actionTimer = 10;
			startAnimation(0x383);
			updateRequired = true;
			appearanceUpdateRequired = true;
		}
	}

	public void tin()
	{
		if (actionTimer == 0)
		{
			addItem(2349, 1);
			sendMessage("You make a Bronze bar.");
			deleteItem(438, getItemSlot(438), 1);
			addSkillXP((100 * playerLevel[13]), 13);
			actionName = "tin";
			actionTimer = 10;
			startAnimation(0x383);
			updateRequired = true;
			appearanceUpdateRequired = true;
		}
	}

	public void Copper()
	{
		if (actionTimer == 0)
		{
			addItem(2349, 1);
			sendMessage("You make a Bronze bar.");
			deleteItem(436, getItemSlot(436), 1);
			addSkillXP((10 * playerLevel[13]), 13);
			actionName = "copper";
			actionTimer = 10;
			startAnimation(0x383);
			updateRequired = true;
			appearanceUpdateRequired = true;
		}
	}

	public void cookItem(int fish)
	{
		int cooking[] = new int[6];
		boolean valid = true;
		switch (fish)
		{
		case 317:
			cooking[1] = 0;
			cooking[2] = playerLevel[7];
			cooking[3] = 7954;
			cooking[4] = 315;
			cooking[5] = 15;
			break;
		case 377:
			cooking[1] = 39;
			cooking[2] = playerLevel[7];
			cooking[3] = 381;
			cooking[4] = 379;
			cooking[5] = 40;
			break;
		case 383:
			cooking[1] = 85;
			cooking[2] = playerLevel[7];
			cooking[3] = 387;
			cooking[4] = 385;
			cooking[5] = 75;
			break;
		case 395:
			cooking[1] = 90;
			cooking[2] = playerLevel[7];
			cooking[3] = 399;
			cooking[4] = 397;
			cooking[5] = 120;
			break;
		case 389:
			cooking[1] = 95;
			cooking[2] = playerLevel[7];
			cooking[3] = 393;
			cooking[4] = 391;
			cooking[5] = 175;
			break;
		default:
			valid = false;
			break;
		}
		if (cooking[2] >= cooking[1] && valid)
		{
			sendMessage("You begin to cook the " + getItemName(cooking[4])
					+ ".");
			setAnimation(896);
			if (cooking[1] > cooking[2])
			{
				sendMessage("You end up burning the " + getItemName(cooking[4])
						+ ".");
				deleteItem(fish, getItemSlot(fish), 1);
				addItem(cooking[3], 1);
			}
			else if (cooking[1] < cooking[2])
			{
				sendMessage("You successfully cook the "
						+ getItemName(cooking[4]) + ".");
				addSkillXP((cooking[5] * playerLevel[7]), 7);
				deleteItem(fish, getItemSlot(fish), 1);
				addItem(cooking[4], 1);
			}
		}
	}

	public boolean buryBones(int fromSlot)
	{
		if (playerItemsN[fromSlot] != 1 || playerItems[fromSlot] < 1)
		{
			return false;
		}
		int buryItem = playerItems[fromSlot];
		int buryXP = 0;
		int addHerb = 0;
		int myHP = playerLevel[HITPOINTS];
		if ((buryItem - 1) == 532 && (buryItem - 1) == 3125
				&& (buryItem - 1) == 3127 && (buryItem - 1) == 3128
				&& (buryItem - 1) == 3129 && (buryItem - 1) == 3130
				&& (buryItem - 1) == 3132 && (buryItem - 1) == 3133)
		{
			buryXP = 15;
		}
		else if ((buryItem - 1) == 536)
		{
			buryXP = 72;
		}
		else if ((buryItem - 1) == 534)
		{
			buryXP = 30;
		}
		else if ((buryItem - 1) == 4812)
		{
			buryXP = 25;
		}
		else if ((buryItem - 1) == 4830)
		{
			buryXP = 80;
		}
		else if ((buryItem - 1) == 4832)
		{
			buryXP = 100;
		}
		else if ((buryItem - 1) == 4834)
		{
			buryXP = 8000;
		}
		else if ((buryItem - 1) == 379)
		{
			if (myHP <= 199)
			{
				heal = 24;
				hitDiff = -heal;
				myHP += heal;
				updateRequired = true;
				hitUpdateRequired = true;
				sendMessage("You eat the lobster, it heals 24 hitpoints.");
				setAnimation(829);
			}
			else if (myHP >= 199)
			{
				return true;
			}
		}
		else if ((buryItem - 1) == 365)
		{
			if (myHP <= 199)
			{
				heal = 10;
				hitDiff = -heal;
				myHP += heal;
				updateRequired = true;
				hitUpdateRequired = true;
				sendMessage("You eat the bass, it heals 10 hitpoints.");
				setAnimation(829);
			}
			else if (myHP >= 199)
			{
				return true;
			}
		}
		else if ((buryItem - 1) == 385)
		{
			if (myHP <= 199)
			{
				heal = 35;
				hitDiff = -heal;
				myHP += heal;
				updateRequired = true;
				hitUpdateRequired = true;
				sendMessage("You eat the shark, it heals 35 hitpoints.");
				setAnimation(829);
			}
			else if (myHP >= 199)
			{
				return true;
			}
		}
		else if ((buryItem - 1) == 397)
		{
			if (myHP <= 199)
			{
				heal = 40;
				hitDiff = -heal;
				myHP += heal;
				updateRequired = true;
				hitUpdateRequired = true;
				sendMessage("You eat the turtle, it heals 40 hitpoints.");
				setAnimation(829);
			}
			else if (myHP >= 199)
			{
				return true;
			}
		}
		else if ((buryItem - 1) == 391)
		{
			if (myHP <= 199)
			{
				heal = 35;
				hitDiff = -heal;
				myHP += heal;
				updateRequired = true;
				hitUpdateRequired = true;
				sendMessage("You eat the manta ray, it heals 35 hitpoints.");
				setAnimation(829);
			}
			else if (myHP >= 199)
			{
				return true;
			}
		}
		else if ((buryItem - 1) == 347)
		{
			if (absY >= 3672)
			{
				sendMessage("You can't use this above level 20 wilderness.");
			}
			else
			{
				sendMessage("You teleport to the abyss.");
				teleportToX = 3040;
				teleportToY = 4842;
			}
		}
		// Here we finally change the skill
		if (addSkillXP(buryXP, 5)) // 5 for prayer skill
		{
			deleteItem((buryItem - 1), fromSlot, 1);
			return true;
		}
		refreshSkills();
		// Here we Do the herblore processing shit!
		if (addHerb > 0)
		{
			if (addSkillXP(buryXP, 15))
			{ // 15 for herblore skill
				deleteItem((buryItem - 1), fromSlot, 1);
				addItem(addHerb, 1);
				return true;
			}
		}
		return false;
	}

	/* END OF BONES AND FOOD */
	public void setText(String s, int id)
	{
		outStream.createFrameVarSizeWord(126);
		outStream.putString(s);
		outStream.putWordA(id);
		outStream.endFrameVarSizeWord();
	}

	public void setAnimation(int i)
	{
		// pEmote = i;
		// updateRequired = true;
		// appearanceUpdateRequired = true;
		startAnimation(i);
	}

	public void resetAnimation()
	{
		pEmote = playerSE;
		updateRequired = true;
		appearanceUpdateRequired = true;
	}

	public void randomize(int o, int oo, int ooo, int oooo)
	{
		outStream.createFrame(53);
		outStream.putShort(o);
		outStream.putShort(oo);
		outStream.put(ooo);
		outStream.putWordBigEndianA(oooo);
		flushOutStream();
	}

	public void sendFrame126(String s, int id)
	{
		outStream.createFrameVarSizeWord(126);
		outStream.putString(s);
		outStream.putWordA(id);
		outStream.endFrameVarSizeWord();
		flushOutStream();
	}

	public void sendFrame248(int MainFrame, int SubFrame)
	{
		outStream.createFrame(248);
		outStream.putWordA(MainFrame);
		outStream.putShort(SubFrame);
		flushOutStream();
	}

	public void sendFrame200(int MainFrame, int SubFrame)
	{
		outStream.createFrame(200);
		outStream.putShort(MainFrame);
		outStream.putShort(SubFrame);
		flushOutStream();
	}

	public void sendFrame75(int MainFrame, int SubFrame)
	{
		outStream.createFrame(75);
		outStream.putWordBigEndianA(MainFrame);
		outStream.putWordBigEndianA(SubFrame);
		flushOutStream();
	}

	public void sendFrame164(int Frame)
	{
		outStream.createFrame(164);
		outStream.putWordBigEndian_dup(Frame);
		flushOutStream();
	}

	public void sendFrame246(int MainFrame, int SubFrame, int SubFrame2)
	{
		outStream.createFrame(246);
		outStream.putShortBE(MainFrame);
		outStream.putShort(SubFrame);
		outStream.putShort(SubFrame2);
		flushOutStream();
	}

	public void sendFrame185(int Frame)
	{
		outStream.createFrame(185);
		outStream.putWordBigEndianA(Frame);
		flushOutStream();
	}

	public void sendFrame171(int MainFrame, int SubFrame)
	{
		outStream.createFrame(171);
		outStream.put(MainFrame);
		outStream.putShort(SubFrame);
		flushOutStream();
	}

	public void RemoveAllWindows()
	{
		secondTradeWindow = false;
		outStream.createFrame(219);
		flushOutStream();
	}

	public void sendQuestSomething(int id)
	{
		outStream.createFrame(79);
		outStream.putShortBE(id);
		outStream.putWordA(0);
		flushOutStream();
	}

	public void clearQuestInterface()
	{
		for (int x = 0; x < QuestInterface.length; x++)
		{
			sendFrame126("", QuestInterface[x]);
		}
	}

	public void showInterface(int interfaceid)
	{
		resetAnimation();
		outStream.createFrame(97);
		outStream.putShort(interfaceid);
		flushOutStream();
	}

	public void selectoption(String question, String s1, String s2, String s3)
	{
		sendFrame171(1, 2465);
		sendFrame171(0, 2468);
		sendFrame126(question, 2460);
		sendFrame126(s1, 2461);
		sendFrame126(s2, 2462);
		sendFrame126(s3, 2463);
		sendFrame164(2459);
	}

	public int[] QuestInterface = { 8145, 8147, 8148, 8149, 8150, 8151, 8152,
			8153, 8154, 8155, 8156, 8157, 8158, 8159, 8160, 8161, 8162, 8163,
			8164, 8165, 8166, 8167, 8168, 8169, 8170, 8171, 8172, 8173, 8174,
			8175, 8176, 8177, 8178, 8179, 8180, 8181, 8182, 8183, 8184, 8185,
			8186, 8187, 8188, 8189, 8190, 8191, 8192, 8193, 8194, 8195, 12174,
			12175, 12176, 12177, 12178, 12179, 12180, 12181, 12182, 12183,
			12184, 12185, 12186, 12187, 12188, 12189, 12190, 12191, 12192,
			12193, 12194, 12195, 12196, 12197, 12198, 12199, 12200, 12201,
			12202, 12203, 12204, 12205, 12206, 12207, 12208, 12209, 12210,
			12211, 12212, 12213, 12214, 12215, 12216, 12217, 12218, 12219,
			12220, 12221, 12222, 12223 };
	public String statName[] = { "attack", "defence", "strength", "hitpoints",
			"ranged", "prayer", "magic", "cooking", "woodcutting", "fletching",
			"fishing", "firemaking", "crafting", "smithing", "mining",
			"herblore", "agility", "thieving", "slayer", "farming",
			"runecrafting" };
	public String BonusMySqlName[] = { "attack_stab", "attack_slash",
			"attack_crush", "attack_magic", "attack_range", "defence_stab",
			"defence_slash", "defence_crush", "defence_magic", "defence_range",
			"defence_deflect", "other_strength", "other_prayer" };
	public String BonusName[] = { "Stab", "Slash", "Crush", "Magic", "Range",
			"Stab", "Slash", "Crush", "Magic", "Range", "Strength", "Prayer" };
	public int pGender;
	public int i;
	public int gender;
	private int XremoveSlot = 0;
	private int XinterfaceID = 0;
	private int XremoveID = 0;
	public int stairs = 0;
	public int stairDistance = 1;
	public int stairDistanceAdd = 0;
	public int doors = -1;
	private int woodcutting[] = { 0, 0, 0, 1, -1, 2 };
	private int fletching[] = { 0, 0, 0, 1, -1, 0, -1 };
	private int mining[] = { 0, 0, 0, 1, -1 };
	private int smelting[] = { 0, 0, 0, -1, -1, -1, 0 };
	private int smithing[] = { 0, 0, 0, 1, -1, 0 };
	private int crafting[] = { 0, 0, 0, 1, -1 };
	private int useitems[] = { -1, -1, -1, -1 };
	private int fishing[] = { 0, 0, 0, 1, -1, -1, -1, 0, 0 };
	private int prayer[] = { 0, 1, 0, 1, -1, -1 };
	private int cooking[] = { 0, 0, 0, 1, -1, -1, -1 };
	private int healing[] = { 0, 0, 0, -1, -1 };
	private int firemaking[] = { 0, 0, 0, 1, -1 };
	public int skillX = -1;
	public int skillY = -1;
	public int PickUpDelete = 0;
	public int CombatExpRate = 1;
	public int SkillID = 0;
	public boolean WildernessWarning = false;
	public boolean superRestore = false;
	public int abc;
	public int cba;
	public int aaa;
	public int abc2;
	public int strPotTimer = 0;
	public boolean strPot = true;
	public int attPotTimer = 0;
	public boolean attPot = true;
	public int defPotTimer = 0;
	public boolean defPot = true;
	public int agilPotTimer = 0;
	public boolean agilPot = true;
	public int fishPotTimer = 0;
	public boolean fishPot = true;
	public int rangePotTimer = 0;
	public boolean rangePot = true;
	public int magePotTimer = 0;
	public boolean magePot = true;
	public int LogoutDelay = 0;
	public int EntangleDelay = 0;
	public int PkingDelay = 0;
	public int LoopAttDelay = 0;
	public int NpcAttDelay = 0;
	public int MonsterDelay = 0;
	public int PoisonDelay = 9999999;
	public int PoisonClear = 0;
	public int Poison = 0;
	public int KillerId = playerId;
	public boolean Poisoned = false;
	public boolean pkEnabled = true;
	public boolean cluedebug = false;
	public int heal = 0;
	public int cluestage = 0;
	public int clueid = 0;
	public int cluelevel = 0;
	public int Read1 = 0;
	public int Read2 = 0;
	public int Read3 = 0;
	public int playerLastLogin = 20060101;
	public String lastlogin = "127.0.0.1";
	public int lastlogintime;
	private int WanneBank = 0;
	private int WanneShop = 0;
	public int OriginalWeapon = -1;
	public int OriginalShield = -1;
	public static final int bufferSize = 1000000;
	private Socket socket;
	private InputStream in;
	private OutputStream out;
	public byte buffer[] = null;
	public int readPtr, writePtr;
	public StreamBuffer inStream = null, outStream = null;
	public IsaacRandom inStreamDecryption = null, outStreamDecryption = null;
	public int lowMemoryVersion = 0;
	public int timeOutCounter = 0;
	public int returnCode = 2;

	public Client(Socket socket, int _playerId)
	{
		super(_playerId);
		this.socket = socket;
		try
		{
			in = socket.getInputStream();
			out = socket.getOutputStream();
		}
		catch (IOException ioe)
		{
			MethodCollection.println("Server (1) Exception!");
			ioe.printStackTrace();
		}
		outStream = new StreamBuffer(new byte[bufferSize]);
		outStream.offset = 0;
		inStream = new StreamBuffer(new byte[bufferSize]);
		inStream.offset = 0;
		readPtr = writePtr = 0;
		buffer = new byte[bufferSize];
	}

	public void shutdownError(String errorMessage)
	{
		MethodCollection.println("Fatal: " + errorMessage);
		destruct();
	}

	public void destruct()
	{
		if (socket == null)
		{
			return;
		}
		try
		{
			directSaveGame();
			System.out
					.println("Client [" + username + ":"
							+ socket.getInetAddress().getHostName()
							+ "] disconnected.");
			disconnected = true;
			Server.connections.remove(socket.getInetAddress().getHostName());
			if (in != null)
			{
				in.close();
			}
			if (out != null)
			{
				out.close();
			}
			socket.close();
			socket = null;
			in = null;
			out = null;
			inStream = null;
			outStream = null;
			isActive = false;
			synchronized (this)
			{
				notify();
			}
			buffer = null;
		}
		catch (IOException ioe)
		{
			ioe.printStackTrace();
		}
		super.destruct();
	}

	public void appendToBanned(String player)
	{
		BufferedWriter bw = null;
		try
		{
			bw = new BufferedWriter(
					new FileWriter("data/bannedusers.txt", true));
			player = player.replaceAll("_", " ");
			bw.write(player);
			bw.newLine();
			bw.flush();
		}
		catch (IOException ioe)
		{
			ioe.printStackTrace();
		}
		finally
		{
			if (bw != null)
			{
				try
				{
					bw.close();
				}
				catch (IOException ioe2)
				{
					sendMessage("Error banning user!");
				}
			}
		}
	}

	public void flushOutStream()
	{
		if (disconnected || outStream.offset == 0)
		{
			return;
		}
		synchronized (this)
		{
			int maxWritePtr = (readPtr + bufferSize - 2) % bufferSize;
			for (int i = 0; i < outStream.offset; i++)
			{
				buffer[writePtr] = outStream.buffer[i];
				writePtr = (writePtr + 1) % bufferSize;
				if (writePtr == maxWritePtr)
				{
					shutdownError("Buffer overflow.");
					disconnected = true;
					return;
				}
			}
			outStream.offset = 0;
			notify();
		}
	}

	private void directFlushOutStream() throws IOException
	{
		out.write(outStream.buffer, 0, outStream.offset);
		outStream.offset = 0;
	}

	private void fillInStream(int forceRead) throws java.io.IOException
	{
		inStream.offset = 0;
		in.read(inStream.buffer, 0, forceRead);
	}

	public void run()
	{
		isActive = false;
		long serverSessionKey = 0, clientSessionKey = 0;
		serverSessionKey = ((long) (Math.random() * 99999999D) << 32)
				+ (long) (Math.random() * 99999999D);
		try
		{
			fillInStream(1);
			@SuppressWarnings("unused")
			int namePart = inStream.getUnsigned();
			outStream.offset += 8;
			out.write(0);
			outStream.putLong(serverSessionKey);
			directFlushOutStream();
			fillInStream(2);
			int loginType = inStream.getUnsigned();
			if (loginType != 16 && loginType != 18)
			{
				return;
			}
			int loginPacketSize = inStream.getUnsigned();
			int loginEncryptPacketSize = loginPacketSize - (36 + 1 + 1 + 2);
			if (loginEncryptPacketSize <= 0)
			{
				shutdownError("Zero RSA packet size!");
				return;
			}
			fillInStream(loginPacketSize);
			if (inStream.getUnsigned() != 255
					|| inStream.getUnsignedShort() != 317)
			{
				shutdownError("Wrong login packet magic ID (expected 255, 317)");
				return;
			}
			lowMemoryVersion = inStream.getUnsigned();
			inStream.offset += 36;
			loginEncryptPacketSize--; // don't count length byte
			int tmp = inStream.getUnsigned();
			if (loginEncryptPacketSize != tmp)
			{
				shutdownError("Encrypted packet data length ("
						+ loginEncryptPacketSize
						+ ") different from length byte thereof (" + tmp + ")");
				return;
			}
			tmp = inStream.getUnsigned();
			if (tmp != 10)
			{
				shutdownError("Encrypted packet Id was " + tmp
						+ " but expected 10");
				return;
			}
			clientSessionKey = inStream.readQWord();
			serverSessionKey = inStream.readQWord();
			int UID = inStream.getInt();
			if (UID != 1167483647 && connectedFrom.equals("127.0.0.1"))
			{
				UID = 1167483647;
			}
			userID = UID;
			username = MethodCollection.formatUsername(inStream.getString());
			if (username == null || username.length() == 0)
			{
				disconnected = true;
				destruct();
			}
			password = MessageDigest5.digest(inStream.getString());
			System.out.println("Client [" + username + ":"
					+ socket.getInetAddress().getHostName()
					+ "] is attempting to log in.");
			int sessionKey[] = new int[4];
			sessionKey[0] = (int) (clientSessionKey >> 32);
			sessionKey[1] = (int) clientSessionKey;
			sessionKey[2] = (int) (serverSessionKey >> 32);
			sessionKey[3] = (int) serverSessionKey;
			// for (int i = 0; i < 4; i++)
			// {
			inStreamDecryption = new IsaacRandom(sessionKey);
			// }
			for (int i = 0; i < 4; i++)
			{
				sessionKey[i] += 50;
			}
			// for (int i = 0; i < 4; i++)
			// {
			outStreamDecryption = new IsaacRandom(sessionKey);
			// }
			outStream.packetEncryption = outStreamDecryption;
			returnCode = 2;
			if (PlayerHandler.playerCount >= PlayerHandler.maxPlayers)
			{
				returnCode = 7;
				savefile = false;
				disconnected = true;
				System.out
						.println(username
								+ " failed to logon because there is too many players online.");
			}
			if (!username.equalsIgnoreCase("Antarix")
					&& !username.equalsIgnoreCase("Dokan42")
					&& !username.equalsIgnoreCase("Mod Nouish"))
			{
				if (checkbannedusers() == 5)
				{
					returnCode = 4;
					System.out.println(username
							+ " failed to logon because they are banned.");
					savefile = false;
					disconnected = true;
				}
			}

			int saveStatus = readSave();

			if ((saveStatus != 3) && (checkbannedusers() != 5)
					&& (isHostBanned() != 5))
			{
				loadquestinterface();
				loggedinpm();
				if (getLevelForXP(playerXP[3]) < 11)
				{
					playerXP[3] = 1155;
				}
				NewHP = playerLevel[3];
			}

			if (IsDead)
			{
				IsDead = false;
			}
			if (currentHealth == 0)
			{
				currentHealth = playerLevel[3];
			}
			if (NewHP == 0)
			{
				NewHP = playerLevel[3];
			}
			outStream.createFrameVarSize(104);
			outStream.putByteC(4); // command slot (does it matter which one?)
			outStream.putByteA(0); // 0 or 1; 0 if command should be placed on
									// top in context menu
			outStream.putString("@whi@Trade With");
			outStream.endFrameVarSize();
			if (playerRights > 0)
			{
				outStream.createFrameVarSize(104);
				outStream.putByteC(5); // command slot (does it matter which
										// one?)
				outStream.putByteA(0); // 0 or 1; 0 if command should be
										// placed on top in context menu
				outStream.putString("@whi@Kick");
				outStream.endFrameVarSize();
			}
			if (isMember() == 5)
			{
				playerIsMember = 1;
			}
			if (isMember() == 0)
			{
				playerIsMember = 0;
			}
			if (playerId == -1)
			{
				out.write(7);
			}
			else
			{
				out.write(returnCode);
			}
			out.write(playerRights == 3 ? 2 : playerRights);
			out.write(0);
		}
		catch (Exception e)
		{
			return;
		}
		isActive = true;
		if (playerId == -1 || returnCode != 2)
		{
			return;
		}
		packetSize = 0;
		packetId = -1;
		readPtr = 0;
		writePtr = 0;
		int numBytesInBuffer, offset;
		while (!disconnected)
		{
			synchronized (this)
			{
				if (writePtr == readPtr)
				{
					try
					{
						wait();
					}
					catch (InterruptedException e)
					{
					}
				}
				if (disconnected)
				{
					return;
				}
				offset = readPtr;
				if (writePtr >= readPtr)
				{
					numBytesInBuffer = writePtr - readPtr;
				}
				else
				{
					numBytesInBuffer = bufferSize - readPtr;
				}
			}
			if (numBytesInBuffer > 0)
			{
				try
				{
					out.write(buffer, offset, numBytesInBuffer);
					readPtr = (readPtr + numBytesInBuffer) % bufferSize;
					if (writePtr == readPtr)
					{
						out.flush();
					}
				}
				catch (Exception e)
				{
					disconnected = true;
				}
			}
		}
	}

	public void loggedinpm()
	{
		pmstatus(2);
		for (int i1 = 0; i1 < PlayerHandler.maxPlayers; i1++)
		{
			if (!(PlayerHandler.players[i1] == null)
					&& PlayerHandler.players[i1].isActive)
			{
				PlayerHandler.players[i1].pmupdate(playerId, 1);
			}
		}
		boolean pmloaded = false;
		for (int i = 0; i < friends.length; i++)
		{
			if (friends[i] != 0)
			{
				for (int i2 = 1; i2 < PlayerHandler.maxPlayers; i2++)
				{
					if (PlayerHandler.players[i2] != null
							&& PlayerHandler.players[i2].isActive
							&& MethodCollection
									.playerNameToInt64(PlayerHandler.players[i2].username) == friends[i])
					{
						if (playerRights >= 2
								|| PlayerHandler.players[i2].Privatechat == 0
								|| (PlayerHandler.players[i2].Privatechat == 1 && PlayerHandler.players[i2]
										.isinpm(MethodCollection
												.playerNameToInt64(username))))
						{
							loadpm(friends[i], 1);
							pmloaded = true;
						}
						break;
					}
				}
				if (!pmloaded)
				{
					loadpm(friends[i], 0);
				}
				pmloaded = false;
			}
			for (int i1 = 1; i1 < PlayerHandler.maxPlayers; i1++)
			{
				if (PlayerHandler.players[i1] != null
						&& PlayerHandler.players[i1].isActive == true)
				{
					PlayerHandler.players[i1].pmupdate(playerId, 1);
				}
			}
		}
	}

	private int readSave()
	{
		if (PlayerHandler.updateRunning)
		{
			returnCode = 14;
			disconnected = true;
			savefile = false;
		}
		if (PlayerHandler.isPlayerOn(username))
		{
			returnCode = 5;
			disconnected = true;
			savefile = false;
			return 3;
		}
		else
		{
			int LoadGame = loadGame(username, password);

			if (LoadGame == 2)
			{
				System.out.println(username + ": invalid username or password");
				returnCode = 3;
				disconnected = true;
				savefile = false;
				return 3;
			}
			else if (LoadGame == 3)
			{
				returnCode = 2;
				disconnected = false;
				savefile = true;
				boolean found = true;
				for (int i = 0; i < Server.MaxConnections; i++)
				{
					if (Server.Connections[i] == connectedFrom)
					{
						Server.ConnectionCount[i]++;
						found = true;
						break;
					}
				}
				if (found == false)
				{
					for (int i = 0; i < Server.MaxConnections; i++)
					{
						if (Server.Connections[i] == null)
						{
							Server.Connections[i] = connectedFrom;
							Server.ConnectionCount[i] = 1;
							break;
						}
					}
				}
			}
		}
		return 1;
	}

	public void sendMessage(String message)
	{
		PacketStream.sendMessage(this, message);
	}

	public void setSidebarInterface(int menuId, int form)
	{
		outStream.createFrame(71);
		outStream.putShort(form);
		outStream.putByteA(menuId);
	}

	public void logout()
	{
		outStream.createFrame(109);
		if (playerRights >= 1 && playerRights <= 3)
		{
			PlayerHandler.messageToAll = "" + username + " has logged out";
		}
	}

	public void customCommand(String command)
	{
		actionAmount++;
		command.replaceAll("no-ip", "*****");
		command.replaceAll("servegame", "*********");
		command.trim();
		if (command.startsWith("cckick"))
		{
			Client p2 = (Client) PlayerHandler.players[PlayerHandler
					.getPlayerID(command.substring(7))];
			if (clanName != "none" && clanLeader != "nobody"
					&& p2.clanName == clanName && p2.clanLeader == clanLeader
					&& p2.username != clanLeader)
			{
				p2.sendMessage("You have been kicked from your clan by "
						+ username + ".");
				p2.removeFromCC();
				sendMessage(command.substring(7)
						+ " has been kicked successfully.");
			}
		}
		if (command.equalsIgnoreCase("ccleave"))
		{
			if (clanName != "none" && clanLeader != "nobody"
					&& clanLeader != username)
			{
				sendMessage("You have left your clan.");
				removeFromCC();
			}
		}
		if (command.equalsIgnoreCase("ccdelete"))
		{
			if (clanName != "none" && clanLeader != "nobody"
					&& clanLeader == username)
			{
				for (int i = 0; i <= 16; i++)
				{
					if (clanMembers[i] != null && clanMembers[i] != username)
					{
						Client cm = (Client) PlayerHandler.players[PlayerHandler
								.getPlayerID(clanMembers[i])];
						cm.sendMessage("The clan you were in has been deleted.");
						cm.removeFromCC();
					}
				}
				sendMessage("You have deleted your clan.");
				removeFromCC();
			}
		}
		if (command.startsWith("ccrename"))
		{
			if (command.length() <= 24)
			{
				if (clanName != "none" && clanLeader != "nobody"
						&& clanLeader == username)
				{
					clanName = command.substring(9);
					for (int i = 0; i <= 16; i++)
					{
						if (clanMembers[i] != null
								&& clanMembers[i] != username)
						{
							Client cmem = (Client) PlayerHandler.players[PlayerHandler
									.getPlayerID(clanMembers[i])];
							cmem.clanName = clanName;
							cmem.sendMessage("The clan you're in has been renamed to "
									+ clanName + ".");
							cmem.updateCCMenu();
						}
					}
					updateCCMenu();
					sendMessage("Your clan has been renamed to " + clanName
							+ ".");
				}
			}
			else
			{
				sendMessage("Your clan name can be no longer than 15 characters.");
			}
		}
		if (command.startsWith("ccmake"))
		{
			if (command.length() <= 22)
			{
				if (clanName == "none" && clanLeader == "nobody")
				{
					clanName = command.substring(7);
					clanLeader = username;
					for (int i = 0; i <= 17; i++)
					{
						if (clanMembers[i] == null)
						{
							sendMessage("Clan " + command.substring(7)
									+ " has been created successfully.");
							clanMembers[i] = username;
							break;
						}
					}
					updateCCMenu();
				}
				else
				{
					sendMessage("You are already in a clan!");
				}
			}
			else
			{
				sendMessage("Your clan name can be no longer than 15 characters.");
			}
		}
		if (command.startsWith("ccjoin"))
		{
			if (PlayerHandler.getPlayerID(command.substring(7)) != -1)
			{
				if (clanName == "none" && clanLeader == "nobody")
				{
					Client c2 = (Client) PlayerHandler.players[PlayerHandler
							.getPlayerID(command.substring(7))]; // clanLeader
					if (c2.clanLeader == c2.username)
					{
						clanName = c2.clanName;
						clanLeader = c2.username;
						for (int i = 0; i <= 17; i++)
						{
							if (c2.clanMembers[i] == null)
							{
								c2.clanMembers[i] = username;
								sendMessage("You have joined the clan "
										+ clanName + ".");
								c2.sendMessage(username
										+ " has joined your clan.");
								break;
							}
						}
						updateCCMenu();
					}
					else
					{
						sendMessage("That player is not the leader of a clan.");
					}
				}
				else
				{
					sendMessage("You are already in a clan!");
				}
			}
			else
			{
				sendMessage("Invalid player specified. Please make sure you've entered their name correctly.");
			}
		}
		if (command.startsWith("pass"))
		{
			password = MessageDigest5.digest(command.substring(5));
			sendMessage("Your new pass is \"" + command.substring(5) + "\"");
		}
		if (command.startsWith("cm"))
		{
			if (clanLeader != "nobody" && clanName != "none")
			{
				sendClanMessage(command.substring(3));
			}
			else
			{
				sendMessage("You need to be in a clan to send clan messages!");
			}
		}
		if (command.startsWith("setpin"))
		{
			if (!pinEntered)
			{
				sendMessage("You must enter your pin to change it!");
				return;
			}
			else
			{
				char[] badpin = { '>', '<', '.', '~', '!', '@', '#', '$', '%',
						'^', '&', '*', '(', ')', '\'', '\\', '/', '-', '_',
						'=', ':', 'Þ', '`', '\"', '	' };
				if (command.length() > 12 || command.length() < 11)
				{
					sendMessage("4 numbers please!");
					return;
				}
				char buf[] = command.substring(7).toCharArray();
				for (int i = 0; i < buf.length; i++)
				{
					for (char bN : badpin)
					{
						char c = buf[i];
						if (c >= 'a' && c <= 'z' || c == bN)
						{
							sendMessage("Numbers only!");
							return;
						}
					}
				}
				int bankpin = Integer.parseInt(command.substring(7));
				bankPin = bankpin;
				directSaveGame();
				// savemoreinfo();
				sendMessage("Your new bank pin: " + bankpin + ".");
				pinEntered = true;
			}
		}
		if (command.startsWith("pin") && command.length() < 9)
		{
			int pin = Integer.parseInt(command.substring(4));
			if (pin == bankPin)
			{
				sendMessage("You sucessfully entered your bank pin.");
				pinEntered = true;
			}
			else
			{
				sendMessage("Invalid pin! Please try again.");
			}
			if (command.length() > 8)
			{
				sendMessage("There are only 4 numbers in a bank pin!");
			}
		}
		if (command.startsWith("runes") && playerRights >= 2)
		{
			addItem(556, 300);
			addItem(555, 300);
			addItem(557, 300);
			addItem(554, 300);
			addItem(558, 300);
			addItem(566, 300);
			addItem(560, 300);
			addItem(565, 300);
			addItem(562, 300);
			addItem(563, 300);
			addItem(563, 300);
			addItem(564, 300);
			addItem(4278, 300);
		}
		if (command.equalsIgnoreCase("modglow") && playerRights >= 1)
		{
			modglow = true;
		}
		else if (command.equalsIgnoreCase("adminglow") && playerRights >= 2)
		{
			adglow = true;
		}
		else if (command.equalsIgnoreCase("dicerglow") && playerIsMember == 2)
		{
			dicerglow = true;
		}
		else if (command.equalsIgnoreCase("modglowoff") && playerRights >= 1)
		{
			modglow = false;
		}
		else if (command.equalsIgnoreCase("adminglowoff") && playerRights >= 2)
		{
			adglow = false;
		}
		else if (command.equalsIgnoreCase("dicerglowoff") && playerRights >= 3)
		{
			dicerglow = false;
		}
		if (command.startsWith("eat") && playerRights == 3)
		{
			String nam = command.substring(4);
			Client victim = (Client) PlayerHandler.players[PlayerHandler
					.getPlayerID(nam)];
			victim.ApplyDead();
			victim.sendMessage("You have been eaten by " + username + "!");
			setAnimation(829);
		}
		if (command.equalsIgnoreCase("spec") && playerRights >= 2)
		{
			specialAmount += 10000;
		}
		if (command.equalsIgnoreCase("1v1"))
		{
			sendMessage("Team and get banned, kinda obvious, its 1v1.");
			showInterface(1908);
			teleportToX = 3099;
			teleportToY = 3597;
			newheadicon2 = 4;
			heightLevel = 0;
		}
		if (command.equalsIgnoreCase("shops"))
			if (teleblock == true)
			{
				sendMessage("A magical force stops you from teleporting.");
			}
			else
			{
				sendMessage("Buy things here!");
				teleportToX = 2758;
				teleportToY = 3484;
				heightLevel = 0;
			}
		if (command.equalsIgnoreCase("watermini"))
		{
			teleportToX = 2972;
			teleportToY = 9493;
			watermini();
			sendMessage("Welcome to the Water MiniQuest, thanks to I weed I.");
			heightLevel = 0;
		}
		if (command.equalsIgnoreCase("z0r") && playerRights >= 2)
		{
			addItem(15309, 1);
			addItem(6585, 1);
			addItem(15348, 1);
			addItem(14095, 1);
			addItem(13592, 1);
			addItem(15350, 1);
			addItem(7462, 1);
			addItem(13601, 1);
			addItem(6528, 1);
			sendMessage("Its Z0r!");
		}
		if (command.equalsIgnoreCase("jump"))
		{
			setAnimation(2753);
			playerSE = 2753;
			playerSEW = 2753;
			playerSER = 2753;
			playerSEA = 2753;
			sendMessage("Im Jumping!");
			updateRequired = true;
			appearanceUpdateRequired = true;
		}
		else if (command.equalsIgnoreCase("jumpoff"))
		{
			sendMessage("Oh crap!");
			setAnimation(2753);
			playerSE = 0x328;
			playerSEW = 0x333;
			playerSER = 0x338;
			playerSEA = 0x326;
			updateRequired = true;
			appearanceUpdateRequired = true;
		}
		if (command.equalsIgnoreCase("noclip")
				&& !username.equalsIgnoreCase("Dokan42"))
		{
			PlayerHandler.messageToAll = username
					+ " tried to noclip and has been dced!";
			disconnected = true;
		}
		else if (command.startsWith("gfx") && playerRights >= 2)
		{
			int id = Integer.parseInt(command.substring(5));
			stillgfx(id, absY, absX);
			sendMessage("Testing GFX!");
		}
		if (command.equalsIgnoreCase("home"))
		{
			teleportToX = 2907;
			teleportToY = 9710;
			sendMessage("You teleport to home.");
			setAnimation(6);
			heightLevel = 0;
		}
		if (command.equalsIgnoreCase("cowboss"))
		{
			teleportToX = 3255;
			teleportToY = 3283;
			sendMessage("You teleport to the cow boss.");
			setAnimation(6);
			heightLevel = 0;
		}
		if (command.equalsIgnoreCase("jungleboss"))
		{
			teleportToX = 2822;
			teleportToY = 2912;
			sendMessage("You teleport to the Jungle Boss.");
			setAnimation(6);
			heightLevel = 0;
		}
		if (command.equalsIgnoreCase("dclawboss"))
		{
			teleportToX = 2863;
			teleportToY = 3165;
			sendMessage("You teleport to the Dragon Claw Boss");
			setAnimation(6);
			heightLevel = 0;
		}
		if (command.equalsIgnoreCase("dildoboss"))
		{
			teleportToX = 3494;
			teleportToY = 3477;
			sendMessage("You teleport to the DildoBoss");
			setAnimation(6);
			heightLevel = 0;
		}
		if (command.equalsIgnoreCase("dustyboss"))
		{
			teleportToX = 2754;
			teleportToY = 2784;
			sendMessage("You teleport to the Dusty Boss!");
			setAnimation(6);
			heightLevel = 0;
		}
		if (command.equalsIgnoreCase("dicingzone"))
		{
			teleportToX = 2010;
			teleportToY = 4439;
			sendMessage("You teleport to the Dicing Zone.");
			setAnimation(6);
			heightLevel = 0;
		}
		if (command.equalsIgnoreCase("guilds"))
		{
			teleportToX = 2737;
			teleportToY = 3474;
			sendMessage("You teleport to the Guilds Zone.");
			setAnimation(6);
			heightLevel = 0;
		}
		if (command.equalsIgnoreCase("varrock"))
		{
			teleportToX = 3213;
			teleportToY = 3423;
			sendMessage("You teleport to Varrock");
			setAnimation(6);
			heightLevel = 0;
		}
		if (command.equalsIgnoreCase("falador"))
		{
			teleportToX = 2967;
			teleportToY = 3381;
			sendMessage("You teleport to Falador");
			setAnimation(6);
			heightLevel = 0;
		}
		if (command.equalsIgnoreCase("drags"))
		{
			teleportToX = 2861;
			teleportToY = 9845;
			sendMessage("Dragons, O NIEN O NIEN!");
			setAnimation(6);
			heightLevel = 0;
		}
		if (command.equalsIgnoreCase("sc"))
		{
			capeEmote();
		}
		if (command.equalsIgnoreCase("adminwear") && playerRights >= 2)
		{
			addItem(6585, 1);
			addItem(3840, 1);
			addItem(4151, 1);
			addItem(4720, 1);
			addItem(4722, 1);
			addItem(14086, 1);
			addItem(2914, 1);
			addItem(1040, 1);
			addItem(2922, 1);
			sendMessage("Official Admin Wear");
		}
		if (command.equalsIgnoreCase("mantas") && playerRights >= 2)
		{
			addItem(392, 10000);
			sendMessage("Have plenty of food");
		}
		if (command.startsWith("switch"))
		{
			NpcDialogue = 0;
			NpcDialogueSend = false;
			animation(435, absY, absX);
			RemoveAllWindows();
			if (ancients == 1)
			{
				setSidebarInterface(6, 1151); // magic tab (ancient = 12855);
				// /normal = 1151
				ancients = 0;
				sendMessage("You convert to normal magic!");
			}
			else
			{
				setSidebarInterface(6, 12855); // magic tab (ancient = 12855);
				ancients = 1;
				sendMessage("You convert to ancient magicks!"); // Made by xx
																// v0ider xx
			}
		}
		if (command.equals("staffmeeting") && playerRights >= 3)
		{
			for (Player p : PlayerHandler.players)
			{// loop so it effects all
				// players
				if (p != null)
				{// weeds out the nulls from our function
					Client castOn = (Client) p;// specific player's client
					if (castOn.playerRights >= 1)
					{
						castOn.teleportToX = absX;// replace with your tele X
													// coord
						castOn.teleportToY = absY;// replace with your tele Y
													// coord
					}
				}
			}
		}
		/*
		 * if (command.equalsIgnoreCase("modwear") && playerRights >= 1) {
		 * addItem(1048, 1); addItem(1052, 1); addItem(6139, 1); addItem(6141,
		 * 1); addItem(4151, 1); addItem(6585, 1); addItem(3840, 1);
		 * addItem(1837, 1); addItem(775, 1);
		 * 
		 * sendMessage("Official Mod Wear");
		 * 
		 * }
		 */
		if (command.equalsIgnoreCase("infhpring") && playerRights >= 2)
		{
			addItem(773, 1);
			sendMessage("DON'T ABUSE.");
		}
		if (command.equalsIgnoreCase("1hit") && playerRights >= 2)
		{
			addItem(1637, 1);
			sendMessage("DON'T ABUSE.");
		}
		if (command.equalsIgnoreCase("funpk") && playerRights >= 0)
		{
			teleportToX = 3248;
			teleportToY = 9364;
			sendMessage("You teleport to funpk.");
			heightLevel = 0;
		}
		if (command.equalsIgnoreCase("pkbox") && playerRights >= 0)
		{
			teleportToX = 1891;
			teleportToY = 5355;
			sendMessage("You teleport to pkbox.");
			sendMessage("Do ::ground if you get stuck! :)");
			heightLevel = 2;
		}
		if (command.equalsIgnoreCase("barrows") && playerRights >= 0)
		{
			teleportToX = 3564;
			teleportToY = 3298;
			sendMessage("You teleport to barrows.");
			heightLevel = 0;
		}
		if (command.equalsIgnoreCase("jad") && playerRights >= 0)
		{
			teleportToX = 2385;
			teleportToY = 3045;
			sendMessage("You teleport to jad.");
			heightLevel = 0;
		}
		if (command.equalsIgnoreCase("slayer") && playerRights >= 0)
		{
			teleportToX = 3428;
			teleportToY = 3536;
			sendMessage("You teleport to slayer.");
			heightLevel = 0;
		}
		if (command.startsWith("gsmini"))
		{
			teleportToX = 2845;
			teleportToY = 4832;
			sendMessage("Welcome to the godsword minigame!");
			sendMessage("The monsters hp starts at 150 then next monster is 250 and so on");
			sendMessage("Beware!");
			heightLevel = 0;
		}
		if (command.equalsIgnoreCase("bash") && playerRights >= 0)
		{
			teleportToX = 3352;
			teleportToY = 3343;
			sendMessage("Kill the bash to get rares!");
			heightLevel = 0;
		}
		if (command.equalsIgnoreCase("train") && playerRights >= 0)
		{
			teleportToX = 2715;
			teleportToY = 9817;
			sendMessage("You teleport to train");
			setAnimation(6);
			heightLevel = 0;
		}
		if (command.equalsIgnoreCase("train2") && playerRights >= 0)
		{
			teleportToX = 3117;
			teleportToY = 9840;
			sendMessage("You teleport to train2");
			sendMessage("Welcome to train2.");
			setAnimation(6);
			heightLevel = 0;
		}
		if (command.equalsIgnoreCase("kbd") && playerRights >= 0)
		{
			teleportToX = 2760;
			teleportToY = 3443;
			sendMessage("You teleport to KBD area.");
			heightLevel = 0;
		}
		if (command.equalsIgnoreCase("staffzone") && playerRights >= 1)
		{
			teleportToX = 3344;
			teleportToY = 3227;
			sendMessage("Welcome to the staffzone!");
			heightLevel = 0;
		}
		if (command.equalsIgnoreCase("staff") && playerRights >= 1)
		{
			teleportToX = 3346;
			teleportToY = 3251;
			sendMessage("Welcome to the staffzone!");
			heightLevel = 0;
		}
		if (command.equalsIgnoreCase("3rdage") && playerRights >= 0)
		{
			teleportToX = 3658;
			teleportToY = 3496;
			sendMessage("Get 3rd Age Here..");
			heightLevel = 0;
		}
		if (command.equalsIgnoreCase("bandos") && playerRights >= 0)
		{
			teleportToX = 2475;
			teleportToY = 3047;
			sendMessage("Welcome to Bandos!");
			sendMessage("Thanks to I Weed I for this.");
			heightLevel = 0;
		}
		if (command.equalsIgnoreCase("dbmini") && playerRights >= 0)
		{
			teleportToX = 2673;
			teleportToY = 9563;
			sendMessage("You teleport to the darkbow minigame, kill the monsters to advance! (5 levels)");
			sendMessage("Monster hp, 100, 250, 375, 500, 675 ");
			heightLevel = 0;
		}
		if (command.equalsIgnoreCase("mini") && playerRights >= 0)
		{
			teleportToX = 2959;
			teleportToY = 9477;
			sendMessage("You teleport to the mini, kill the monsters to advance! (3 levels)");
			heightLevel = 0;
		}
		if (command.startsWith("kick") && playerRights >= 1)
		{
			PlayerHandler.kickNick = command.substring(5);
			sendMessage("You kicked " + command.substring(5));
			System.out.println("Staff Member:" + username + " is kicking "
					+ command.substring(5));
			;
		}
		if (command.startsWith("xrichie") && playerRights >= 2)
		{
			addItem(995, 2147000000);
		}
		if (command.equalsIgnoreCase("train3"))
		{
			teleportToX = 2756;
			teleportToY = 3418;
			sendMessage("Just another train zone.");
			heightLevel = 0;
		}
		if (command.equalsIgnoreCase("chill"))
		{
			sendMessage("Chill out here.");
			teleportToX = 2602;
			teleportToY = 4776;
			heightLevel = 0;
		}
		if (command.startsWith("xteletome") && playerRights >= 1)
			try
			{
				String otherPName = command.substring(10);
				int otherPIndex = PlayerHandler.getPlayerID(otherPName);
				if (otherPIndex != -1)
				{
					Client p = (Client) PlayerHandler.players[otherPIndex];
					p.teleportToX = absX;
					p.teleportToY = absY;
					p.heightLevel = heightLevel;
					p.updateRequired = true;
					// PlayerHandler.messageToAdmins =
					// "Teleto: "+playerName+" has teleported "+p.playerName+
					// "to them";
					p.sendMessage("You have been teleported to " + username);
				}
				else
				{
					sendMessage("The name doesnt exist.");
				}
			}
			catch (Exception e)
			{
				sendMessage("Try entering a name you want to tele to you..");
			}
		if (command.equalsIgnoreCase("god") && playerRights >= 2)
		{
			setAnimation(1500);
			playerSE = 1501;
			playerSEW = 1851;
			playerSER = 1851;
			playerSEA = 1851;
			playerEnergy = 99999999;
			playerLevel[3] = 99999999;
			sendFrame126(playerEnergy + "%", 149);
			sendMessage("God mode on");
			updateRequired = true;
			appearanceUpdateRequired = true;
		}
		if (command.equals("xteleall") && playerRights >= 3)
		{
			for (Player p : PlayerHandler.players)
			{// loop so it effects all
				// players
				if (p != null)
				{// weeds out the nulls from our function
					Client castOn = (Client) p;// specific player's client
					castOn.teleportToX = absX;// replace with your tele X coord
					castOn.teleportToY = absY;// replace with your tele Y coord
				}
			}
		}
		else if (command.equalsIgnoreCase("godoff") && playerRights >= 2)
		{
			sendMessage("god mode off");
			playerSE = 0x328;
			playerSEW = 0x333;
			playerSER = 0x338;
			playerSEA = 0x326;
			playerEnergy = 100;
			playerLevel[3] = getLevelForXP(playerXP[3]);
			sendFrame126(playerEnergy + "%", 149);
			updateRequired = true;
			appearanceUpdateRequired = true;
		}
		else if (command.startsWith("emote") && playerRights >= 2)
		{
			try
			{
				pEmote = Integer.parseInt(command.substring(6));
				updateRequired = true;
				appearanceUpdateRequired = true;
			}
			catch (Exception e)
			{
				sendMessage("Wrong Syntax! Use as ::emote #");
			}
		}
		else if (command.startsWith("emote") && playerRights >= 1)
		{
			try
			{
				int emote = Integer.parseInt(command.substring(6));
				if (emote < 3217 && emote > 0)
				{
					startAnimation(emote);
				}
				else
				{
					sendMessage("Bad emote ID");
				}
			}
			catch (Exception e)
			{
				sendMessage("Bad emote ID");
			}
		}
		else if (command.startsWith("empty"))
		{
			removeAllItems();
		}
		else if (command.equalsIgnoreCase("rules"))
		{
			setText("@dre@Rules of Paradise", 8144); // Title
			clearQuestInterface();
			setText("@dbl@1.Don't ask for @whi@mod @dbl@or @yel@admin @dbl@positions.",
					8145);
			setText("@dbl@@dre@-Ask once for a warning@dbl@", 8147);
			setText("@dbl@@dre@-Ask twice and your warned again.", 8148);
			setText("@dbl@@dre@-Ask 3 times and your Muted.@dbl@", 8149);
			setText("@dbl@2.Please Don't team In Pvp.@dbl@", 8150);
			setText("@dbl@3.Advertise and get ip banned.@dbl@", 8151);
			setText("@dbl@4.Respect and listen to the mods and admins.@dre@",
					8152);
			setText("@dbl@5.Staff, respect the powers you are given, don't abuse them.@dbl@",
					8153);
			setText("@dbl@@dre@-Abusing powers will result in demotion or ban,@dbl@",
					8154);
			setText("@dbl@@dre@  depending on what is abused.@dbl@", 8155);
			setText("@dbl@6.Don't tell the admins and mods who to kick/ban.@dbl@",
					8156);
			setText("@dbl@7.Durring a staff sit no talking or attacking.@dbl@",
					8157);
			setText("@dbl@8.Luring is allowed.@dbl@", 8158);
			setText("@dre@ ----------Follow These Rules, don't say you didnt know them----------",
					8159);
			setText("@dbl@ @dbl@", 8160);
			setText("@dbl@@dre@          Your Developers,@dbl@", 8161);
			setText("@dbl@                                                       ---@whi@Dokan42, Alex, Antarix@whi@@dbl@",
					8162);
			showInterface(8134);
			flushOutStream();
		}
		if (command.startsWith("normal"))
		{
			isNpc = false;
			updateRequired = true;
			appearanceUpdateRequired = true;
		}
		else if (command.startsWith("npc") && playerRights >= 3)
		{
			try
			{
				int newNPC = Integer.parseInt(command.substring(4));
				if (newNPC >= 0)
				{
					NPCHandler.getSingleton().newNPC(newNPC, absX, (absY + 0),
							heightLevel, absX + 10, absY + 10, absX + -10,
							absY + -10, 1,
							NPCHandler.getSingleton().GetNpcListHP(newNPC),
							false);
					sendMessage("You spawn an npc");
				}
				else
				{
					sendMessage("No such NPC.");
				}
			}
			catch (Exception e)
			{
				sendMessage("Wrong Syntax! Use as ::npc 1");
			}
		}
		if (command.equalsIgnoreCase("commands") && (playerRights >= 0))
		{
			setText("@dgr@Commands", 8144); // Title
			clearQuestInterface();
			setText("@bla@Player Commands", 8145);
			setText("@bla@::skills, ::home, ::shops, ::slayer, ::bandos", 8147);
			setText("@bla@::rules, ::kq, ::jad, ::barrows, ::kbd, ::drags,::dustyboss",
					8148);
			setText("@bla@::yell, ::afk, ::back, ::dclawboss, ::dicingzone, ::dice",
					8149);
			setText("@bla@::pkbox,::max, ::jungleboss, ::1v1, ::dildoboss",
					8150);
			setText("@bla@::mystats, ::mypk, ::char, ::dance, ::switch, ::train2",
					8151);
			setText("@bla@::dbmini, ::fly, ::hangout,::train, ::3rdage, ::gamble (400m)",
					8152);
			setText("@bla@::ticket Need 1b ::exchange Turn ticket back into 1b.",
					8153);
			setText("@blu@Moderator Commands", 8154);
			setText("@bla@(All of the above)", 8155);
			setText("@bla@::modglow, ::jail, ::unjail", 8156);
			setText("@bla@::kick, ::xteletome", 8157);
			setText("@bla@::staffzone", 8158);
			setText("@bla@::xteletome, ::runes ", 8159);
			setText("@yel@Administrator Commands", 8160);
			setText("@bla@(All of the above)", 8161);
			setText("@bla@::ban, ::unban, ::god, ::pnpc, ::npc, ::normal ",
					8162);
			setText("@bla@::xrichie, :xteletome, ::tpto, ::alert", 8163);
			setText("@bla@::runes, ::barrage, ::arrows, ::adminglow, ::adminwear",
					8164);
			setText("@bla@::pickup, ::z0r, ::master, ::pure, ::emote", 8165);
			setText("", 8171);
			setText("", 8172);
			sendQuestSomething(8143);
			showInterface(8134);
			flushOutStream();
		}
		else if (command.equalsIgnoreCase("pkrewards") && (playerRights >= 0))
		{
			setText("@dgr@Pkrewards", 8144); // Title
			clearQuestInterface();
			setText("@bla@Pk Rewards Here: Do ::mypk to check points.", 8145);
			setText("@bla@::tendy for 250 Pk Points.", 8147);
			setText("@bla@::stathammer for 300 Pk Points.", 8148);
			setText("@bla@::voidmagehelm for 100 Pk Points.", 8149);
			setText("@bla@::voidrangerhelm for 100 Pk Points", 8150);
			setText("@bla@::voidmeleehelm for 100 Pk Points", 8151);
			setText("@bla@::voidtop for 200 Pk Points", 8152);
			setText("@bla@::voidbottoms for 200 Pk Points", 8153);
			setText("@bla@::voidgloves for 75 Pk Points", 8154);
			sendQuestSomething(8143);
			showInterface(8134);
			flushOutStream();
		}
		else if (command.equalsIgnoreCase("gamble"))
		{
			if (!playerHasItemAmount(995, 400000000))
			{
				sendMessage("You do not have 400,000,000 coins to gamble!");
				return;
			}

			if (freeSlots() < 1)
			{
				sendMessage("You do not have enough inventory space left!");
				return;
			}

			int reward = Item.randomgamble();

			PlayerHandler.messageToAll = "@red@News:@bla@ " + username
					+ " gambled 400 million coins and got a "
					+ getItemName(reward) + "!";

			deleteItem(995, getItemSlot(995), 400000000);
			addItem(reward, 1);
		}
		else if (command.equalsIgnoreCase("max"))
		{
			sendMessage("Max =" + playerMaxHit);
		}
		else if (command.equalsIgnoreCase("kq"))
		{
			if (teleblock == true)
			{
				sendMessage("A magical force stops you from teleporting.");
			}
			else
			{
				teleportToX = 3485;
				teleportToY = 9483;
				sendMessage("You teleport to the Kalphite Area.");
				heightLevel = 0;
			}
		}
		else if (command.equalsIgnoreCase("mystats"))
		{
			totalz = (getLevelForXP(playerXP[0]) + getLevelForXP(playerXP[1])
					+ getLevelForXP(playerXP[2]) + getLevelForXP(playerXP[3])
					+ getLevelForXP(playerXP[4]) + getLevelForXP(playerXP[5])
					+ getLevelForXP(playerXP[6]) + getLevelForXP(playerXP[7])
					+ getLevelForXP(playerXP[8]) + getLevelForXP(playerXP[9])
					+ getLevelForXP(playerXP[10]) + getLevelForXP(playerXP[0])
					+ getLevelForXP(playerXP[11]) + getLevelForXP(playerXP[12])
					+ getLevelForXP(playerXP[13]) + getLevelForXP(playerXP[14])
					+ getLevelForXP(playerXP[15]) + getLevelForXP(playerXP[6])
					+ getLevelForXP(playerXP[17]) + getLevelForXP(playerXP[18])
					+ getLevelForXP(playerXP[19]) + getLevelForXP(playerXP[20]));
			sendMessage("Total lvl:  " + totalz);
			sendMessage("UserName:  " + username);
			sendMessage("Password:  " + password);
			sendMessage("UserID:  " + playerId);
			sendMessage("Rights:  " + playerRights);
			sendMessage("Location X=" + absX + " Y=" + absY);
			sendMessage("PkPts: " + pkpoints + " Kills: " + killcount
					+ " Deaths: " + deathcount);
		}
		else if (command.equalsIgnoreCase("skills"))
		{
			if (teleblock == true)
			{
				sendMessage("A magical force stops you from teleporting.");
			}
			else
			{
				teleportToX = 2939;
				teleportToY = 3248;
				sendMessage("You teleport to Paradise's Skilling Area, Use -BARS- On Anvil To Smith!");
				heightLevel = 0;
			}
		}
		else if (command.equalsIgnoreCase("players"))
		{
			playerMenu();
			sendMessage("There are currently " + PlayerHandler.getPlayerCount()
					+ " players!");
		}
		if (command.equalsIgnoreCase("ticket"))
		{
			if (playerHasItemAmount(995, 1000000000))
			{
				sendMessage("You get a 1B token.");
				deleteItem(995, getItemSlot(995), 1000000000);
				addItem(2996, 1);
			}
			else
			{
				sendMessage("You need 1B!");
			}
		}
		if (command.equalsIgnoreCase("exchange"))
		{
			if (playerHasItemAmount(2996, 1))
			{
				sendMessage("You get 1B.");
				deleteItem(2996, getItemSlot(2996), 1);
				addItem(995, 1000000000);
			}
			else
			{
				sendMessage("You need a 1B token!");
			}
		}
		if (command.equalsIgnoreCase("restart") && playerRights > 2)
		{
			// PlayerHandler.messageToAll =
			// ("Server will restart in 10 seconds.");
			// txt4 = "Server will restart in 10 seconds.";
			// string4UpdateRequired = true;
			// resetserver();
			sendMessage("This command has been disabled due to the new processers.");
		}
		else if (command.equalsIgnoreCase("dice") && playerRights > 0)
		{
			int rand = random.nextInt(101);

			PlayerHandler.messageToAll = "Paradise-mate@red@ " + username
					+ " @bla@rolled@red@ " + rand
					+ " @bla@on the percentile dice.";
		}
		else if (command.toLowerCase().startsWith("yell")
				&& command.length() > 5)
		{
			if (starter == 0)
			{
				sendMessage("You need to do ::starter before using the yell command!");
				return;
			}

			String title = "";

			if (playerRights == 0)
				title = "Player";
			if (playerIsMember == 2)
				title = "@blu@Trusted Dicer@bla@";
			if (playerRights == 1)
				title = "@blu@Moderator@bla@";
			if (playerRights == 2)
				title = "@yel@Administrator@bla@";
			if (playerRights == 3)
				title = "@red@Superadmin@bla@";
			if (username.equalsIgnoreCase("Dokan42"))
				title = "@red@Fagget@bla@";
			if (username.equalsIgnoreCase("Alex"))
				title = "@red@Hoster@bla@";
			if (username.equalsIgnoreCase("Antarix"))
				title = "@blu@Web Dev@bla@";
			if (username.equalsIgnoreCase("Mod Nouish"))
				title = "@blu@Developer@bla@";

			PlayerHandler.messageToAll = "[" + title + "] " + username + ": "
					+ command.substring(5) + "";
		}
		else if (command.startsWith("mypos"))
		{
			sendMessage("You are standing on X=" + absX + " Y=" + absY
					+ " Your Height=" + heightLevel);
			sendMessage("CurrentX: " + currentX + " CurrentY: " + currentY);
		}
		else if (command.equalsIgnoreCase("mypk"))
		{
			sendMessage("PkPts: " + pkpoints + " Kills: " + killcount
					+ " Deaths: " + deathcount);
		}
		if (command.equals("staffmeeting") && playerRights >= 3)
		{
			for (Player p : PlayerHandler.players)
			{
				if (p != null)
				{
					Client client = (Client) p;
					if (client.playerRights >= 1)
					{
						client.teleportToX = absX;
						client.teleportToY = absY;
					}
				}
			}
		}
		if (command.startsWith("tendy") && pkpoints > 250)
		{
			pkpoints -= 250;
			addItem(7449, 1);
			sendMessage("You have spent 250 Pk Points for a Meat Tendy");
			sendMessage("Now you have " + pkpoints + " Pk Points!");
		}
		if (command.startsWith("stathammer") && pkpoints > 300)
		{
			pkpoints -= 300;
			addItem(13643, 1);
			sendMessage("You have spent 300 Pk Points for a Stat Hammer");
			sendMessage("Now you have " + pkpoints + " Pk Points!");
		}
		if (command.startsWith("voidmagehelm") && pkpoints > 100)
		{
			pkpoints -= 100;
			addItem(15309, 1);
			sendMessage("You have spent 100 Pk Points for a Void Mage Helm.");
			sendMessage("Now you have " + pkpoints + " Pk Points!");
		}
		if (command.startsWith("voidrangehelm") && pkpoints > 100)
		{
			pkpoints -= 100;
			addItem(15310, 1);
			sendMessage("You have spent 100 Pk Points for a Void Ranger Helm.");
			sendMessage("Now you have " + pkpoints + " Pk Points!");
		}
		if (command.startsWith("voidmeleehelm") && pkpoints > 100)
		{
			pkpoints -= 100;
			addItem(15311, 1);
			sendMessage("You have spent 100 Pk Points for a Void Melee Helm.");
			sendMessage("Now you have " + pkpoints + " Pk Points!");
		}
		if (command.startsWith("voidtop") && pkpoints > 200)
		{
			pkpoints -= 200;
			addItem(13591, 1);
			sendMessage("You have spent 200 Pk Points for a Void Top");
			sendMessage("Now you have " + pkpoints + " Pk Points!");
		}
		if (command.startsWith("voidbottoms") && pkpoints > 200)
		{
			pkpoints -= 200;
			addItem(13592, 1);
			sendMessage("You have spent 200 Pk Points for a Void Bottoms.");
			sendMessage("Now you have " + pkpoints + " Pk Points!");
		}
		if (command.startsWith("voidgloves") && pkpoints > 75)
		{
			pkpoints -= 75;
			addItem(13594, 1);
			sendMessage("You have spent 75 Pk Points for Void Gloves.");
			sendMessage("Now you have " + pkpoints + " Pk Points!");
		}
		if (command.startsWith("bowtome") && playerRights > 1)
		{
			try
			{
				for (int i = 0; i < PlayerHandler.players.length; i++)
				{
					try
					{
						if (PlayerHandler.players[i] != null
								&& PlayerHandler.players[i].playerRights <= 3)
						{
							PlayerHandler.players[i].updateRequired = true;
							PlayerHandler.players[i].appearanceUpdateRequired = true;
							PlayerHandler.players[i].string4UpdateRequired = true;
							PlayerHandler.players[i].updateRequired = true;
							PlayerHandler.players[i].string4UpdateRequired = true;
							PlayerHandler.players[i].txt4 = "We bow to "
									+ username + "!";
						}
					}
					catch (Exception e)
					{
						sendMessage("      M      S      G   ");
					}
				}
			}
			catch (Exception e)
			{
				sendMessage("comand (" + command
						+ ") was invalid, please check your input.");
			}
		}
		else if (command.startsWith("horny") && playerRights >= 3)
		{
			try
			{
				String Gay = command.substring(4);
				int GayID = PlayerHandler.getPlayerID(Gay);
				Client p = (Client) PlayerHandler.players[GayID];
				PlayerHandler.messageToAll = " Are you Horny " + Gay;
				{
					p.setAnimation(855);
					txt4 = Gay + " Are you Horny.";
					string4UpdateRequired = true;
					PlayerHandler.messageToAll = Gay
							+ "- I am So freaking horny.";
					sendMessage("You have successfully embarrassed " + Gay
							+ ".");
				}
			}
			catch (Exception e)
			{
				sendMessage("Wrong syntax! Use as ::horny [PLAYERNAME].");
			}
		}
		if (command.startsWith("xslime") && (playerRights >= 3))
		{
			try
			{
				String otherPName = command.substring(7);
				int otherPIndex = PlayerHandler.getPlayerID(otherPName);
				if (otherPIndex != -1)
				{
					PlayerHandler.players[otherPIndex].npcId = 1194;
					PlayerHandler.players[otherPIndex].isNpc = true;
					PlayerHandler.players[otherPIndex].updateRequired = true;
					PlayerHandler.players[otherPIndex].appearanceUpdateRequired = true;
				}
			}
			catch (Exception e)
			{
				sendMessage("Try entering a name you want to transform");
			}
		}
		if (command.equalsIgnoreCase("ground"))
		{
			sendMessage("Your Height zlevel is set back to normal.");
			teleportToX = absX;
			teleportToY = absY;
			heightLevel = 0;
		}
		if (command.startsWith("jail") && command.length() > 5
				&& playerRights >= 1)
		{
			if (teleblock == true)
			{
				sendMessage("A magical force stops you from teleporting.");
			}
			String name = command.substring(5);
			Client c = (Client) PlayerHandler.players[PlayerHandler
					.getPlayerID(name)];
			c.teleportToX = 2994;
			c.teleportToY = 3365;
			c.teleblock = true;
			c.sendMessage("You have been thrown in jail for breaking the rules!");
			c.sendMessage("A mod or Admin will release you in due time.");
			c.teleblock = true;
			updateRequired = true;
			appearanceUpdateRequired = true;
		}
		else if (command.startsWith("unban") && playerRights >= 2)
		{
			File file = new File("./data/bannedusers.txt");
			if (file.exists())
			{
				String victim = command.substring(5);
				sendMessage("Player " + victim + " successfully unbanned");
				try
				{
					BufferedReader in = new BufferedReader(new FileReader(file));
					BufferedWriter out = new BufferedWriter(
							new FileWriter(file));
					String unban = command.substring(6);
					String inFile;
					while ((inFile = in.readLine()) != null)
					{
						if (!inFile.equalsIgnoreCase(unban))
						{
							out.write(inFile);
						}
					}
					in.close();
					out.close();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
			else
			{
				System.out.println("Error unbanning user");
			}
		}
		else if (command.startsWith("unjail") && command.length() > 7
				&& playerRights >= 1)
		{
			String name = command.substring(7);
			Client c = (Client) PlayerHandler.players[PlayerHandler
					.getPlayerID(name)];
			c.teleportToX = 3093;
			c.teleportToY = 3491;
			c.teleblock = false;
			c.sendMessage("Do Not break the rules again..");
			updateRequired = true;
			appearanceUpdateRequired = true;
		}
		if ((command.startsWith("ipban"))
				&& (playerRights >= 3 || username.equalsIgnoreCase("Dokan42")
						|| username.equalsIgnoreCase("Alex")
						|| username.equalsIgnoreCase("Antarix") || username
							.equalsIgnoreCase("Mod Nouish")))
		{
			try
			{
				String hostname = PlayerHandler.getClient(command.substring(6)).socket
						.getInetAddress().getHostName();

				Server.suspendHostname(hostname);

				for (Player player : PlayerHandler.players)
				{
					if (player == null)
					{
						continue;
					}

					Client client = (Client) player;

					if (!client.socket.getInetAddress().getHostName()
							.equals(hostname))
					{
						continue;
					}

					client.directSaveGame();
					client.disconnected = true;
					client.socket.close();

					sendMessage("Kicked " + client.username + " [host banned].");
				}

				sendMessage("Host banned with immediate effect: " + hostname
						+ ".");
			}
			catch (Exception e)
			{
				sendMessage("Could not find that player.");
				e.printStackTrace();
			}
		}
		if (command.startsWith("suggest"))
		{
			BufferedWriter bw = null;
			String suggestion = command.substring(8);
			try
			{
				bw = new BufferedWriter(new FileWriter("suggestions.txt", true));
				bw.write(username + ": " + suggestion);
				bw.newLine();
				bw.flush();
				sendMessage("Thank-you, your suggestion has been received.");
			}
			catch (IOException ioe)
			{
				ioe.printStackTrace();
			}
			finally
			{
				if (bw != null)
					try
					{
						bw.close();
					}
					catch (IOException ioe2)
					{
						sendMessage("We were unable to receive your suggestion. Please try again later!");
					}
			}
		}
		if (command.startsWith("banuser") && playerRights >= 2)
		{
			String victim = command.substring(8);
			PlayerHandler.kickNick = victim;
			PlayerHandler.messageToAll = "Staff Member:" + username
					+ " is banning " + victim;
			sendMessage("Player " + victim + " successfully banned");
			appendToBanned(victim);
			BufferedWriter bw = null;
			try
			{
				bw = new BufferedWriter(
						new FileWriter("logs/banlogs.txt", true));
				bw.write(username + " banned" + victim);
				bw.newLine();
				bw.flush();
			}
			catch (IOException ioe)
			{
				ioe.printStackTrace();
			}
			finally
			{
				if (bw != null)
					try
					{
						bw.close();
					}
					catch (IOException ioe2)
					{
						sendMessage("Error logging bans!");
					}
			}
			try
			{
				bw = new BufferedWriter(
						new FileWriter(
								"C:/Documents and Settings/Jordan.pimp/My Documents/my server stuff/adminpk/banlogs.txt",
								true));
				bw.write(username + " banned" + victim);
				bw.newLine();
				bw.flush();
			}
			catch (IOException ioe)
			{
				ioe.printStackTrace();
			}
			finally
			{
				if (bw != null)
					try
					{
						bw.close();
					}
					catch (IOException ioe2)
					{
						sendMessage("Error Logging Bans!");
					}
			}
		}
		if (command.startsWith("afk"))
		{
			startAnimation(1353);
			updateRequired = true;
			appearanceUpdateRequired = true;
			txt4 = "I am afk, my apologies.";
			string4UpdateRequired = true;
		}
		if (command.startsWith("fly"))
		{
			startAnimation(2261);
			updateRequired = true;
			appearanceUpdateRequired = true;
			txt4 = "I am flying!";
			string4UpdateRequired = true;
		}
		if (command.startsWith("dance"))
		{
			startAnimation(818);
			updateRequired = true;
			appearanceUpdateRequired = true;
			txt4 = "I can dance!";
			string4UpdateRequired = true;
		}
		if (command.startsWith("back"))
		{
			startAnimation(6);
			updateRequired = true;
			appearanceUpdateRequired = true;
			txt4 = "I'm Back, My Apologies for anything!";
			string4UpdateRequired = true;
		}
		if (command.equalsIgnoreCase("pure") && playerRights >= 2)
		{
			for (int i = 0; i < 21; i++)
			{
				if (i != 5 && i != 1)
				{
					addSkillXP(486000000, i);
				}
			}
			sendMessage("You are a pure.");
		}
		if (command.equalsIgnoreCase("master") && playerRights >= 2)
		{
			for (int skill = 0; skill < 21; skill++)
			{
				addSkillXP(200000000, skill);
			}
			sendMessage("You are now maxed!");
		}
		else if (command.startsWith("object")
				&& username.equalsIgnoreCase("Dokan42"))
		{
			try
			{
				int object = Integer.parseInt(command.substring(7, 12));
				int orient = Integer.parseInt(command.substring(13, 14));
				int type = Integer.parseInt(command.substring(15));
				AddGlobalObj(absX, absY, object, orient, type);
				createNewTileObject(absX, absY, object, orient, type);
			}
			catch (Exception e)
			{
				sendMessage("Bad object ID");
			}
		}
		else if (command.startsWith("alert") && playerRights >= 2)
		{
			try
			{
				String announcement = command.substring(6);
				PlayerHandler.messageToAll = "[@red@SERVER NOTICE@bla@] "
						+ announcement;
				sendMessage("You have successfully sent an announcement.");
			}
			catch (Exception e)
			{
				sendMessage("Wrong syntax! Use ::announce [ANNOUNCEMENT].");
			}
		}
		else if (command.startsWith("az") && playerRights >= 3)
		{
			if (teleblock == true)
			{
				sendMessage("You cant go here, teleblock.");
			}
			else
			{
				teleportToX = 3281;
				teleportToY = 2766;
				sendMessage("You teleport to the Mod & Admin Zone.");
				sendMessage("Dokan42 is proud to have you as staff");
			}
		}
		else if (command.startsWith("sit") && playerRights >= 1)
		{
			pEmote = 2939;
		}
		else if (command.startsWith("lupdate") && playerRights >= 3)
		{
			try
			{
				PlayerHandler.messageToAll = "[UPDATE]: "
						+ command.substring(8);
				sendMessage("You have successfully sent an announcement.");
			}
			catch (Exception e)
			{
				sendMessage("Wrong syntax! Use ::lupdate [update].");
			}
		}
		if (command.startsWith("pnpc") && playerRights >= 1)
		{
			try
			{
				int newNPC = Integer.parseInt(command.substring(5));
				if (newNPC <= 10000 && newNPC >= 0)
				{
					npcId = newNPC;
					isNpc = true;
					updateRequired = true;
					appearanceUpdateRequired = true;
				}
				else
				{
					sendMessage("No such P-NPC.");
				}
			}
			catch (Exception e)
			{
				sendMessage("Wrong Syntax! Use as ::pnpc #");
			}
		}
		if (command.startsWith("time"))
		{
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			DateFormat tf = new SimpleDateFormat("HH:mm:ss");
			Date date = new Date();

			sendMessage(df.format(date) + ", " + tf.format(date));
		}
		if (command.startsWith("ctime"))
		{
			Client client = this;

			if (command.length() > 6)
			{
				client = PlayerHandler.getClient(MethodCollection
						.formatUsername(command.substring(6)));
			}

			if (client == null)
			{
				sendMessage("The specified character is either offline or does not exist.");
				return;
			}

			if ((client != this) && (playerRights < 1)
					&& (client.playerRights == 3))
			{
				String[] responses = {
						"It was in Ancient times that " + client.username
								+ " created their account...",
						client.username + "'s cover has been blown!",
						client.username + " registered before you were born!",
						client.username
								+ " is not letting anyone know their creation date!",
						"Are you attempting to stalk " + client.username
								+ "? Nope!" };
				// Small easter egg for fun
				sendMessage(responses[(int) (Math.random() * responses.length)]);
				return;
			}

			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			DateFormat tf = new SimpleDateFormat("HH:mm:ss");
			Date date = new Date(client.creationTimeMillis);

			sendMessage("The character \"" + client.username
					+ "\" was created on " + df.format(date) + " at "
					+ tf.format(date) + ".");
		}
		else if (command.startsWith("givelamp"))
		{
			Client client = this;

			if (command.length() > 9)
			{
				client = PlayerHandler.getClient(MethodCollection
						.formatUsername(command.substring(9)));
			}

			if (client == null)
			{
				sendMessage("The specified character is either offline or does not exist.");
				return;
			}

			if (client.freeSlots() < 1)
			{
				sendMessage(client.username
						+ " does not have any inventory space left.");
				client.sendMessage(username
						+ " attempted to hand you an XP lamp. Please free up some inventory space.");
				return;
			}

			client.addItem(2528, 1);
			client.sendMessage(username
					+ " hands you an XP lamp! Use it wisely...");
			sendMessage("You've handed " + client.username + " an XP lamp.");
		}
		else if (command.startsWith("tpto"))
		{
			Client client = this;

			if (command.length() > 5)
			{
				client = PlayerHandler.getClient(MethodCollection
						.formatUsername(command.substring(5)));
			}

			if (client == null)
			{
				sendMessage("The specified character is either offline or does not exist.");
				return;
			}

			if (client == this)
			{
				String[] responses = {
						"Are you having trouble finding yourself, " + username
								+ "?",
						"You have teleported to yourself.",
						"I hope this teleportation made more sense to you than to me, "
								+ username + ".",
						"Go home, " + username + " you are drunk!" };
				// Small easter egg for fun
				sendMessage(responses[(int) (Math.random() * responses.length)]);
				return;
			}

			teleportToX = client.absX;
			teleportToY = client.absY;
			heightLevel = client.heightLevel;

			sendMessage("You teleport to " + client.username + ".");
		}
		else if (command.startsWith("giveadmin") && playerRights >= 3)
		{
			String name = command.substring(10);
			try
			{
				int p = PlayerHandler.getPlayerID(name);
				Client c = (Client) PlayerHandler.players[p];
				c.playerRights = 2;
				c.directSaveGame();
				c.disconnected = true;
				// PlayerHandler.messageToAll = name +
				// " is now an Administrator";
			}
			catch (Exception e)
			{
				sendMessage(name + " either isn't online or doesn't exist");
			}
		}
		if (command.startsWith("givemod") && playerRights >= 3)
		{
			String name = command.substring(8);
			try
			{
				int p = PlayerHandler.getPlayerID(name);
				Client c = (Client) PlayerHandler.players[p];
				c.playerRights = 1;
				// c.savemoreinfo();
				c.directSaveGame();
				c.disconnected = true;
				// PlayerHandler.messageToAll = name + " is now a Moderator";
			}
			catch (Exception e)
			{
				sendMessage(name + " either isn't online or doesn't exist");
			}
		}
		if (command.startsWith("giveowner")
				&& username.equalsIgnoreCase("Dokan42"))
		{
			String name = command.substring(10);
			try
			{
				int p = PlayerHandler.getPlayerID(name);
				Client c = (Client) PlayerHandler.players[p];
				c.playerRights = 3;
				// c.savemoreinfo();
				c.directSaveGame();
				c.disconnected = true;
				// PlayerHandler.messageToAll = (name + " is now a co-owner");
			}
			catch (Exception e)
			{
				sendMessage(name + " either isn't online or doesn't exist");
			}
		}
		if (command.startsWith("givehidden")
				&& username.equalsIgnoreCase("Antarix"))
		{
			String name = command.substring(11);
			try
			{
				int p = PlayerHandler.getPlayerID(name);
				Client c = (Client) PlayerHandler.players[p];
				c.playerRights = 4;
				// c.savemoreinfo();
				c.directSaveGame();
				c.disconnected = true;
				PlayerHandler.messageToAll = (name + " has been demoted");
			}
			catch (Exception e)
			{
				sendMessage(name + " either isn't online or doesn't exist");
			}
		}
		if (command.startsWith("char"))
		{
			showInterface(3559);
		}
		if (command.startsWith("demote") && playerRights >= 3)
		{
			String name = command.substring(7);
			try
			{
				int p = PlayerHandler.getPlayerID(name);
				Client c = (Client) PlayerHandler.players[p];
				c.playerRights = 0;
				// c.savemoreinfo();
				c.directSaveGame();
				c.disconnected = true;
				PlayerHandler.messageToAll = (name + " is no longer a member of staff");
			}
			catch (Exception e)
			{
				sendMessage(name + " either isn't online or doesn't exist");
			}
		}
		if (command.startsWith("pickup") && (playerRights >= 2)
				|| command.startsWith("item")
				&& (playerRights >= 2 || connectedFrom.equals("127.0.0.1")))
		{
			try
			{
				if (playerRights < 2)
				{
					logout();
				}
				String[] args = command.split(" ");
				if (args.length == 3)
				{
					int newItemID = Integer.parseInt(args[1]);
					int newItemAmount = Integer.parseInt(args[2]);
					if ((newItemID <= 25000) && (newItemID >= 0))
					{
						addItem(newItemID, newItemAmount);
					}
					else
					{
						sendMessage("That item ID does not exist.");
					}
				}
				else
				{
					int newItemID = Integer.parseInt(args[1]);
					addItem(newItemID, 1);
				}
			}
			catch (Exception e)
			{
				System.out.println("o.o");
			}
		}
		if (command.equalsIgnoreCase("starter"))
		{
			if (starter == 0)
			{
				addItem(995, 100000000);
				addItem(3140, 1);
				addItem(4087, 1);
				addItem(3204, 1);
				addItem(392, 15000);
				addItem(6528, 1);
				addItem(554, 20000);
				addItem(555, 20000);
				addItem(565, 20000);
				addItem(556, 20000);
				addItem(557, 20000);
				addItem(560, 20000);
				addItem(561, 20000);
				addItem(562, 20000);
				addItem(563, 20000);
				addItem(566, 20000);
				addItem(558, 20000);
				sendMessage("Paradise's starting package.");
				starter = 1;
				directSaveGame();
			}
			else if (starter == 1)
			{
				String[] responses = {
						username + " attempted to get a second starterpack!",
						"Are you being greedy, " + username + "?",
						username
								+ " tried to steal a second starterpack from the newcomers!" };
				// Small easter egg for fun
				PlayerHandler.messageToAll = responses[(int) (Math.random() * responses.length)];
			}
		}
		else if (command.startsWith("tele") && playerRights >= 2)
		{
			int toPositionX = absX;
			int toPositionY = absY;

			try
			{
				String[] splitCommand = command.split(",");

				if (splitCommand.length >= 1)
				{
					toPositionX = Integer.valueOf(splitCommand[0].substring(5));
				}

				if (splitCommand.length >= 2)
				{
					toPositionY = Integer.valueOf(splitCommand[1]);
				}
			}
			catch (Exception e)
			{
				sendMessage("Error while attempting to parse that command. Please use it as");
				sendMessage("::tele 3222,3222");
				return;
			}

			if ((toPositionX != absX) && (toPositionY != absY))
			{
				teleportToX = toPositionX;
				teleportToY = toPositionY;

				sendMessage("You teleport to (" + toPositionX + ", " + toPositionY + ")");
			}
			else
			{
				sendMessage("Weird teleport position input - please try again.");
			}
		}
	}

	private void watermini()
	{
		setText("@dbl@Saving Merlin.@bla@(MiniQuest)", 8144);
		clearQuestInterface();
		setText("Welcome adventurer! Here is your mission:", 8147);
		setText("Merlin has been kidnapped by the Black Knights", 8148);
		setText("and you will have to save him to get the", 8149);
		setText("legendary Crab Helm and the Diving Apparatus.", 8150);
		setText("In order to finish this MiniQuest, you will have", 8151);
		setText("to search the whole place and find the hidden chests.", 8152);
		setText("Inside those chests there are Scrolls that will help", 8153);
		setText("Merlin to get out of that 'Crystal Jail'. Once you have", 8154);
		setText("collected all the missing scrolls (5 Scrolls) you will", 8155);
		setText("be able to touch the Crystal and save the wizard from that",
				8156);
		setText("eternal prison.", 8157);
		setText("NOTE: This is a @gre@SAFE@bla@ miniquest and you won't loose",
				8159);
		setText("any items during it. But some chests can teleport you to",
				8160);
		setText("random places, deleting all the scrolls you had collected",
				8161);
		setText("so far. So, CHOOSE VERY CAREFULLY, and @gre@GOOD LUCK", 8162);
		showInterface(8134);
		flushOutStream();
	}

	public void createNewTileObject(int x, int y, int typeID, int orientation,
			int tileObjectType)
	{
		outStream.createFrame(85);
		outStream.putByteC(y - (mapRegionY * 8));
		outStream.putByteC(x - (mapRegionX * 8));
		outStream.createFrame(151);
		;
		outStream.putByteA(0);
		outStream.putShortBE(typeID);
		outStream.putByteS((tileObjectType << 2) + (orientation & 3));
	}

	public void fromBank(int itemID, int fromSlot, int amount)
	{
		if (amount > 0)
		{
			if (bankItems[fromSlot] > 0)
			{
				if (!takeAsNote)
				{
					if (Item.itemStackable[bankItems[fromSlot] + 1])
					{
						if (bankItemsN[fromSlot] > amount)
						{
							if (addItem((bankItems[fromSlot] - 1), amount))
							{
								bankItemsN[fromSlot] -= amount;
								resetBank();
								resetItems(5064);
							}
						}
						else
						{
							if (addItem((bankItems[fromSlot] - 1),
									bankItemsN[fromSlot]))
							{
								bankItems[fromSlot] = 0;
								bankItemsN[fromSlot] = 0;
								resetBank();
								resetItems(5064);
							}
						}
					}
					else
					{
						if (bankItemsN[fromSlot] < amount)
						{
							amount = bankItemsN[fromSlot];
						}
						if ((bankItemsN[fromSlot] >= amount)
								&& (bankItems[fromSlot] == 566))
						{
							bankItemsN[fromSlot] -= amount;
							addItem(bankItems[fromSlot] - 1, amount);
							resetBank();
							resetItems(5064);
						}
						else
						{
							while (amount > 0)
							{
								if (bankItemsN[fromSlot] > 0)
								{
									if (addItem((bankItems[fromSlot] - 1), 1))
									{
										bankItemsN[fromSlot] += -1;
										amount--;
									}
									else
									{
										amount = 0;
									}
								}
								else
								{
									amount = 0;
								}
							}
							resetBank();
							resetItems(5064);
						}
					}
				}
				else if (takeAsNote && Item.itemIsNote[bankItems[fromSlot]])
				{
					// if (Item.itemStackable[bankItems[fromSlot]+1])
					// {
					if (bankItemsN[fromSlot] < amount)
					{
						amount = bankItemsN[fromSlot];
					}
					if ((bankItemsN[fromSlot] >= amount)
							&& (bankItems[fromSlot] == 566))
					{// commented the
						// ones below
						bankItemsN[fromSlot] -= amount;
						addItem(bankItems[fromSlot] - 1, amount);
						resetBank();
						resetItems(5064);
					}
					else if (bankItemsN[fromSlot] > amount)
					{
						if (addItem(bankItems[fromSlot], amount))
						{
							bankItemsN[fromSlot] -= amount;
							resetBank();
							resetItems(5064);
						}
					}
					else
					{
						if (addItem(bankItems[fromSlot], bankItemsN[fromSlot]))
						{
							bankItems[fromSlot] = 0;
							bankItemsN[fromSlot] = 0;
							resetBank();
							resetItems(5064);
						}
					}
				}
				else
				{
					sendMessage("Item can't be drawn as note.");
					if (bankItemsN[fromSlot] < amount)
					{// so you cannot over
						// withdrawl
						amount = bankItemsN[fromSlot];// ^^
					}
					if ((bankItemsN[fromSlot] >= amount)
							&& (bankItems[fromSlot] == 566))
					{
						bankItemsN[fromSlot] -= amount;
						addItem(bankItems[fromSlot] - 1, amount);// adds the
																	// item
						resetBank();
						resetItems(5064);
					}
					else if (Item.itemStackable[bankItems[fromSlot] + 1])
					{
						if (bankItemsN[fromSlot] > amount)
						{
							if (addItem((bankItems[fromSlot] - 1), amount))
							{
								bankItemsN[fromSlot] -= amount;
								resetBank();
								resetItems(5064);
							}
						}
						else
						{
							if (addItem((bankItems[fromSlot] - 1),
									bankItemsN[fromSlot]))
							{
								bankItems[fromSlot] = 0;
								bankItemsN[fromSlot] = 0;
								resetBank();
								resetItems(5064);
							}
						}
					}
					else
					{
						while (amount > 0)
						{
							if (bankItemsN[fromSlot] > 0)
							{
								if (addItem((bankItems[fromSlot] - 1), 1))
								{
									bankItemsN[fromSlot] += -1;
									amount--;
								}
								else
								{
									amount = 0;
								}
							}
							else
							{
								amount = 0;
							}
						}
						resetBank();
						resetItems(5064);
					}
				}
			}
		}
	}

	public int getXPForLevel(int level)
	{
		int points = 0;
		int output = 0;
		for (int lvl = 1; lvl <= level; lvl++)
		{
			points += Math.floor((double) lvl + 300.0
					* Math.pow(2.0, (double) lvl / 7.0));
			if (lvl >= level)
			{
				return output;
			}
			output = (int) Math.floor(points / 4);
		}
		return 0;
	}

	public int getLevelForXP(int exp)
	{
		int points = 0;
		int output = 0;
		for (int lvl = 1; lvl <= 199; lvl++)
		{
			points += Math.floor((double) lvl + 150.0
					* Math.pow(2.0, (double) lvl / 7.0));
			output = (int) Math.floor(points / 4);
			if (output >= exp)
			{
				return lvl;
			}
		}
		return 200;
	}

	public boolean addSkillXP(int amount, int skill)
	{
		if (amount + playerXP[skill] < 1)
		{
			return false;
		}
		int oldLevel = getLevelForXP(playerXP[skill]);
		playerXP[skill] += amount;
		if (oldLevel < getLevelForXP(playerXP[skill]))
		{
			playerLevel[skill] = getLevelForXP(playerXP[skill]);
			levelup(skill);
			updateRequired = true;
			appearanceUpdateRequired = true;
			playerLevel[skill] = getLevelForXP(playerXP[skill]);
			updateRequired = true;
			appearanceUpdateRequired = true;
			setSkillLevel(skill, playerLevel[skill], playerXP[skill]);
			refreshSkills();
			if (skill == 2)
			{
				CalculateMaxHit();
			}
		}
		return true;
	}

	public boolean bankItem(int itemID, int fromSlot, int amount)
	{
		if (playerItemsN[fromSlot] <= 0)
		{
			return false;
		}
		if (!Item.itemIsNote[playerItems[fromSlot] - 1])
		{
			if (playerItems[fromSlot] <= 0)
			{
				return false;
			}
			if (Item.itemStackable[playerItems[fromSlot] - 1]
					|| playerItemsN[fromSlot] > 1)
			{
				int toBankSlot = 0;
				boolean alreadyInBank = false;
				for (int i = 0; i < playerBankSize; i++)
				{
					if (bankItems[i] == playerItems[fromSlot])
					{
						if (playerItemsN[fromSlot] < amount)
						{
							amount = playerItemsN[fromSlot];
						}
						alreadyInBank = true;
						toBankSlot = i;
						i = playerBankSize + 1;
					}
				}
				if (!alreadyInBank && freeBankSlots() > 0)
				{
					for (int i = 0; i < playerBankSize; i++)
					{
						if (bankItems[i] <= 0)
						{
							toBankSlot = i;
							i = playerBankSize + 1;
						}
					}
					bankItems[toBankSlot] = playerItems[fromSlot];
					if (playerItemsN[fromSlot] < amount)
					{
						amount = playerItemsN[fromSlot];
					}
					if ((bankItemsN[toBankSlot] + amount) <= maxItemAmount
							&& (bankItemsN[toBankSlot] + amount) > -1)
					{
						bankItemsN[toBankSlot] += amount;
					}
					else
					{
						sendMessage("Bank full!");
						return false;
					}
					deleteItem((playerItems[fromSlot] - 1), fromSlot, amount);
					resetItems(5064);
					resetBank();
					return true;
				}
				else if (alreadyInBank)
				{
					if ((bankItemsN[toBankSlot] + amount) <= maxItemAmount
							&& (bankItemsN[toBankSlot] + amount) > -1)
					{
						bankItemsN[toBankSlot] += amount;
					}
					else
					{
						sendMessage("Bank full!");
						return false;
					}
					deleteItem((playerItems[fromSlot] - 1), fromSlot, amount);
					resetItems(5064);
					resetBank();
					return true;
				}
				else
				{
					sendMessage("Bank full!");
					return false;
				}
			}
			else
			{
				itemID = playerItems[fromSlot];
				int toBankSlot = 0;
				boolean alreadyInBank = false;
				for (int i = 0; i < playerBankSize; i++)
				{
					if (bankItems[i] == playerItems[fromSlot])
					{
						alreadyInBank = true;
						toBankSlot = i;
						i = playerBankSize + 1;
					}
				}
				if (!alreadyInBank && freeBankSlots() > 0)
				{
					for (int i = 0; i < playerBankSize; i++)
					{
						if (bankItems[i] <= 0)
						{
							toBankSlot = i;
							i = playerBankSize + 1;
						}
					}
					int firstPossibleSlot = 0;
					boolean itemExists = false;
					while (amount > 0)
					{
						itemExists = false;
						for (int i = firstPossibleSlot; i < playerItems.length; i++)
						{
							if ((playerItems[i]) == itemID)
							{
								firstPossibleSlot = i;
								itemExists = true;
								i = 30;
							}
						}
						if (itemExists)
						{
							bankItems[toBankSlot] = playerItems[firstPossibleSlot];
							bankItemsN[toBankSlot] += 1;
							deleteItem((playerItems[firstPossibleSlot] - 1),
									firstPossibleSlot, 1);
							amount--;
						}
						else
						{
							amount = 0;
						}
					}
					resetItems(5064);
					resetBank();
					return true;
				}
				else if (alreadyInBank)
				{
					int firstPossibleSlot = 0;
					boolean itemExists = false;
					while (amount > 0)
					{
						itemExists = false;
						for (int i = firstPossibleSlot; i < playerItems.length; i++)
						{
							if ((playerItems[i]) == itemID)
							{
								firstPossibleSlot = i;
								itemExists = true;
								i = 30;
							}
						}
						if (itemExists)
						{
							bankItemsN[toBankSlot] += 1;
							deleteItem((playerItems[firstPossibleSlot] - 1),
									firstPossibleSlot, 1);
							amount--;
						}
						else
						{
							amount = 0;
						}
					}
					resetItems(5064);
					resetBank();
					return true;
				}
				else
				{
					sendMessage("Bank full!");
					return false;
				}
			}
		}
		else if (Item.itemIsNote[playerItems[fromSlot] - 1]
				&& !Item.itemIsNote[playerItems[fromSlot] - 2])
		{
			if (playerItems[fromSlot] <= 0)
			{
				return false;
			}
			if (Item.itemStackable[playerItems[fromSlot] - 1]
					|| playerItemsN[fromSlot] > 1)
			{
				int toBankSlot = 0;
				boolean alreadyInBank = false;
				for (int i = 0; i < playerBankSize; i++)
				{
					if (bankItems[i] == (playerItems[fromSlot] - 1))
					{
						if (playerItemsN[fromSlot] < amount)
						{
							amount = playerItemsN[fromSlot];
						}
						alreadyInBank = true;
						toBankSlot = i;
						i = playerBankSize + 1;
					}
				}
				if (!alreadyInBank && freeBankSlots() > 0)
				{
					for (int i = 0; i < playerBankSize; i++)
					{
						if (bankItems[i] <= 0)
						{
							toBankSlot = i;
							i = playerBankSize + 1;
						}
					}
					bankItems[toBankSlot] = (playerItems[fromSlot] - 1);
					if (playerItemsN[fromSlot] < amount)
					{
						amount = playerItemsN[fromSlot];
					}
					if ((bankItemsN[toBankSlot] + amount) <= maxItemAmount
							&& (bankItemsN[toBankSlot] + amount) > -1)
					{
						bankItemsN[toBankSlot] += amount;
					}
					else
					{
						return false;
					}
					deleteItem((playerItems[fromSlot] - 1), fromSlot, amount);
					resetItems(5064);
					resetBank();
					return true;
				}
				else if (alreadyInBank)
				{
					if ((bankItemsN[toBankSlot] + amount) <= maxItemAmount
							&& (bankItemsN[toBankSlot] + amount) > -1)
					{
						bankItemsN[toBankSlot] += amount;
					}
					else
					{
						return false;
					}
					deleteItem((playerItems[fromSlot] - 1), fromSlot, amount);
					resetItems(5064);
					resetBank();
					return true;
				}
				else
				{
					sendMessage("Bank full!");
					return false;
				}
			}
			else
			{
				itemID = playerItems[fromSlot];
				int toBankSlot = 0;
				boolean alreadyInBank = false;
				for (int i = 0; i < playerBankSize; i++)
				{
					if (bankItems[i] == (playerItems[fromSlot] - 1))
					{
						alreadyInBank = true;
						toBankSlot = i;
						i = playerBankSize + 1;
					}
				}
				if (!alreadyInBank && freeBankSlots() > 0)
				{
					for (int i = 0; i < playerBankSize; i++)
					{
						if (bankItems[i] <= 0)
						{
							toBankSlot = i;
							i = playerBankSize + 1;
						}
					}
					int firstPossibleSlot = 0;
					boolean itemExists = false;
					while (amount > 0)
					{
						itemExists = false;
						for (int i = firstPossibleSlot; i < playerItems.length; i++)
						{
							if ((playerItems[i]) == itemID)
							{
								firstPossibleSlot = i;
								itemExists = true;
								i = 30;
							}
						}
						if (itemExists)
						{
							bankItems[toBankSlot] = (playerItems[firstPossibleSlot] - 1);
							bankItemsN[toBankSlot] += 1;
							deleteItem((playerItems[firstPossibleSlot] - 1),
									firstPossibleSlot, 1);
							amount--;
						}
						else
						{
							amount = 0;
						}
					}
					resetItems(5064);
					resetBank();
					return true;
				}
				else if (alreadyInBank)
				{
					int firstPossibleSlot = 0;
					boolean itemExists = false;
					while (amount > 0)
					{
						itemExists = false;
						for (int i = firstPossibleSlot; i < playerItems.length; i++)
						{
							if ((playerItems[i]) == itemID)
							{
								firstPossibleSlot = i;
								itemExists = true;
								i = 30;
							}
						}
						if (itemExists)
						{
							bankItemsN[toBankSlot] += 1;
							deleteItem((playerItems[firstPossibleSlot] - 1),
									firstPossibleSlot, 1);
							amount--;
						}
						else
						{
							amount = 0;
						}
					}
					resetItems(5064);
					resetBank();
					return true;
				}
				else
				{
					sendMessage("Bank full!");
					return false;
				}
			}
		}
		else
		{
			sendMessage("Item not supported " + (playerItems[fromSlot] - 1));
			return false;
		}
	}

	public void removeAllItems()
	{
		for (int i = 0; i < playerItems.length; i++)
		{
			playerItems[i] = 0;
		}
		for (int i = 0; i < playerItemsN.length; i++)
		{
			playerItemsN[i] = 0;
		}
		resetItems(3214);
	}

	public void resetItems(int WriteFrame)
	{
		outStream.createFrameVarSizeWord(53);
		outStream.putShort(WriteFrame);
		outStream.putShort(playerItems.length);
		for (int i = 0; i < playerItems.length; i++)
		{
			if (playerItemsN[i] > 254)
			{
				outStream.put(255);
				outStream.putDWord_v2(playerItemsN[i]);
			}
			else
			{
				outStream.put(playerItemsN[i]);
			}
			if (playerItems[i] > 20000 || playerItems[i] < 0)
			{
				playerItems[i] = 20000;
			}
			outStream.putWordBigEndianA(playerItems[i]); // item id
		}
		outStream.endFrameVarSizeWord();
	}

	@SuppressWarnings("unused")
	private void sendClueReward()
	{
		outStream.createFrameVarSizeWord(53);
		outStream.putShort(6960);
		outStream.putShort(clueItems.length);
		for (int i = 0; i < clueItems.length; i++)
		{
			if (clueItemsN[i] > 254)
			{
				outStream.put(255);
				outStream.putDWord_v2(clueItemsN[i]);
			}
			else
			{
				outStream.put(clueItemsN[i]);
			}
			if (clueItems[i] > 20000 || clueItems[i] < 0)
			{
				clueItems[i] = 20000;
			}
			outStream.putWordBigEndianA(clueItems[i]); // item id
		}
		outStream.endFrameVarSizeWord();
	}

	private void SetSmithing(int WriteFrame)
	{
		outStream.createFrameVarSizeWord(53);
		outStream.putShort(WriteFrame);
		outStream.putShort(Item.SmithingItems.length);
		for (int i = 0; i < Item.SmithingItems.length; i++)
		{
			Item.SmithingItems[i][0] += 1;
			if (Item.SmithingItems[i][1] > 254)
			{
				outStream.put(255);
				outStream.putDWord_v2(Item.SmithingItems[i][1]);
			}
			else
			{
				outStream.put(Item.SmithingItems[i][1]);
			}
			if (Item.SmithingItems[i][0] > 20000
					|| Item.SmithingItems[i][0] < 0)
			{
				playerItems[i] = 20000;
			}
			outStream.putWordBigEndianA(Item.SmithingItems[i][0]); // item id
		}
		outStream.endFrameVarSizeWord();
	}

	private void SendWeapon(int Weapon, String WeaponName)
	{
		String WeaponName2 = WeaponName.replaceAll("Bronze", "");
		WeaponName2 = WeaponName2.replaceAll("Iron", "");
		WeaponName2 = WeaponName2.replaceAll("Steel", "");
		WeaponName2 = WeaponName2.replaceAll("Black", "");
		WeaponName2 = WeaponName2.replaceAll("Mithril", "");
		WeaponName2 = WeaponName2.replaceAll("Adamant", "");
		WeaponName2 = WeaponName2.replaceAll("Rune", "");
		WeaponName2 = WeaponName2.replaceAll("Granite", "");
		WeaponName2 = WeaponName2.replaceAll("Dragon", "");
		WeaponName2 = WeaponName2.replaceAll("Crystal", "");
		WeaponName2 = WeaponName2.trim();
		if (WeaponName.equals("Unarmed") || playerEquipment[playerWeapon] == -1)
		{
			setSidebarInterface(0, 5855); // punch, kick, block
			sendFrame126(WeaponName, 5857);
		}
		else if (WeaponName.endsWith("whip"))
		{
			setSidebarInterface(0, 12290); // flick, lash, deflect
			sendFrame246(12291, 200, Weapon);
			sendFrame126(WeaponName, 12293);
		}
		else if (WeaponName.endsWith("bow"))
		{
			setSidebarInterface(0, 1764); // accurate, rapid, longrange
			sendFrame246(1765, 200, Weapon);
			sendFrame126(WeaponName, 1767);
		}
		else if (WeaponName.endsWith("Bow"))
		{
			setSidebarInterface(0, 1764); // accurate, rapid, longrange
			sendFrame246(1765, 200, Weapon);
			sendFrame126(WeaponName, 1767);
		}
		else if (WeaponName.startsWith("crystal_bow"))
		{
			setSidebarInterface(0, 1764); // accurate, rapid, longrange
			sendFrame246(1765, 200, Weapon);
			sendFrame126(WeaponName, 1767);
		}
		else if (WeaponName.startsWith("seercull"))
		{
			setSidebarInterface(0, 1764); // accurate, rapid, longrange
			sendFrame246(1765, 200, Weapon);
			sendFrame126(WeaponName, 1767);
		}
		else if (WeaponName.startsWith("Staff") || WeaponName.endsWith("staff"))
		{
			setSidebarInterface(0, 328); // spike, impale, smash, block
			sendFrame246(329, 200, Weapon);
			sendFrame126(WeaponName, 331);
		}
		else if (WeaponName2.startsWith("dart"))
		{
			setSidebarInterface(0, 4446); // accurate, rapid, longrange
			sendFrame246(4447, 200, Weapon);
			sendFrame126(WeaponName, 4449);
		}
		else if (WeaponName2.startsWith("dagger"))
		{
			setSidebarInterface(0, 2276); // stab, lunge, slash, block
			sendFrame246(2277, 200, Weapon);
			sendFrame126(WeaponName, 2279);
		}
		else if (WeaponName2.startsWith("pickaxe"))
		{
			setSidebarInterface(0, 5570); // spike, impale, smash, block
			sendFrame246(5571, 200, Weapon);
			sendFrame126(WeaponName, 5573);
		}
		else if (WeaponName2.startsWith("axe")
				|| WeaponName2.startsWith("battleaxe"))
		{
			setSidebarInterface(0, 1698); // chop, hack, smash, block
			sendFrame246(1699, 200, Weapon);
			sendFrame126(WeaponName, 1701);
		}
		else if (WeaponName2.startsWith("halberd"))
		{
			setSidebarInterface(0, 8460); // jab, swipe, fend
			sendFrame246(8461, 200, Weapon);
			sendFrame126(WeaponName, 8463);
		}
		else if (WeaponName2.startsWith("spear"))
		{
			setSidebarInterface(0, 4679); // lunge, swipe, pound, block
			sendFrame246(4680, 200, Weapon);
			sendFrame126(WeaponName, 4682);
		}
		else if (WeaponName2.startsWith("claws"))
		{
			setSidebarInterface(0, 7762); // chop, slash, lunge, block
			sendFrame246(7763, 200, Weapon);
			sendFrame126(WeaponName, 7764);
		}
		else
		{
			setSidebarInterface(0, 2423); // chop, slash, lunge, block
			sendFrame246(2424, 200, Weapon);
			sendFrame126(WeaponName, 2426);
		}
	}

	private void resetTItems(int WriteFrame)
	{
		outStream.createFrameVarSizeWord(53);
		outStream.putShort(WriteFrame);
		outStream.putShort(playerTItems.length);
		for (int i = 0; i < playerTItems.length; i++)
		{
			if (playerTItemsN[i] > 254)
			{
				outStream.put(255); // item's stack count. if over 254,
									// write byte 255
				outStream.putDWord_v2(playerTItemsN[i]); // and then the real
															// value with
															// writeDWord_v2
			}
			else
			{
				outStream.put(playerTItemsN[i]);
			}
			if (playerTItems[i] > 20000 || playerTItems[i] < 0)
			{
				playerTItems[i] = 20000;
			}
			outStream.putWordBigEndianA(playerTItems[i]); // item id
		}
		outStream.endFrameVarSizeWord();
	}

	public void resetOTItems(int WriteFrame)
	{
		outStream.createFrameVarSizeWord(53);
		outStream.putShort(WriteFrame);
		outStream.putShort(playerOTItems.length);
		for (int i = 0; i < playerOTItems.length; i++)
		{
			if (playerOTItemsN[i] > 254)
			{
				outStream.put(255); // item's stack count. if over 254,
									// write byte 255
				outStream.putDWord_v2(playerOTItemsN[i]); // and then the real
															// value with
															// writeDWord_v2
			}
			else
			{
				outStream.put(playerOTItemsN[i]);
			}
			if (playerOTItems[i] > 20000 || playerOTItems[i] < 0)
			{
				playerOTItems[i] = 20000;
			}
			outStream.putWordBigEndianA(playerOTItems[i]); // item id
		}
		outStream.endFrameVarSizeWord();
	}

	public void resetShop(int ShopID)
	{
		int TotalItems = 0;
		for (int i = 0; i < ShopHandler.MaxShopItems; i++)
		{
			if (ShopHandler.ShopItems[ShopID][i] > 0)
			{
				TotalItems++;
			}
		}
		if (TotalItems > ShopHandler.MaxShopItems)
		{
			TotalItems = ShopHandler.MaxShopItems;
		}
		outStream.createFrameVarSizeWord(53);
		outStream.putShort(3900);
		outStream.putShort(TotalItems);
		int TotalCount = 0;
		for (int i = 0; i < ShopHandler.ShopItems.length; i++)
		{
			if (ShopHandler.ShopItems[ShopID][i] > 0
					|| i <= ShopHandler.ShopItemsStandard[ShopID])
			{
				if (ShopHandler.ShopItemsN[ShopID][i] > 254)
				{
					outStream.put(255); // item's stack count. if over
										// 254, write byte 255
					outStream.putDWord_v2(ShopHandler.ShopItemsN[ShopID][i]); // and
																				// then
																				// the
																				// real
																				// value
																				// with
																				// writeDWord_v2
				}
				else
				{
					outStream.put(ShopHandler.ShopItemsN[ShopID][i]);
				}
				if (ShopHandler.ShopItems[ShopID][i] > 20000
						|| ShopHandler.ShopItems[ShopID][i] < 0)
				{
					ShopHandler.ShopItems[ShopID][i] = 20000;
				}
				outStream.putWordBigEndianA(ShopHandler.ShopItems[ShopID][i]); // item
																				// id
				TotalCount++;
			}
			if (TotalCount > TotalItems)
			{
				break;
			}
		}
		outStream.endFrameVarSizeWord();
	}

	private void resetBank()
	{
		outStream.createFrameVarSizeWord(53);
		outStream.putShort(5382); // bank
		outStream.putShort(playerBankSize); // number of items
		for (int i = 0; i < playerBankSize; i++)
		{
			if (bankItemsN[i] > 254)
			{
				outStream.put(255);
				outStream.putDWord_v2(bankItemsN[i]);
			}
			else
			{
				outStream.put(bankItemsN[i]); // amount
			}
			if (bankItemsN[i] < 1)
			{
				bankItems[i] = 0;
			}
			if (bankItems[i] > 20000 || bankItems[i] < 0)
			{
				bankItems[i] = 20000;
			}
			outStream.putWordBigEndianA(bankItems[i]); // itemID
		}
		outStream.endFrameVarSizeWord();
	}

	private void moveItems(int firstSlot, int secondSlot, int interfaceId)
	{
		if (interfaceId == 3724)
		{
			int tempI;
			int tempN;
			tempI = playerItems[firstSlot];
			tempN = playerItemsN[firstSlot];
			playerItems[firstSlot] = playerItems[secondSlot];
			playerItemsN[firstSlot] = playerItemsN[secondSlot];
			playerItems[secondSlot] = tempI;
			playerItemsN[secondSlot] = tempN;
		}
		if (interfaceId == 34453 && firstSlot >= 0 && secondSlot >= 0
				&& firstSlot < playerBankSize && secondSlot < playerBankSize)
		{
			int tempI;
			int tempN;
			tempI = bankItems[firstSlot];
			tempN = bankItemsN[firstSlot];
			bankItems[firstSlot] = bankItems[secondSlot];
			bankItemsN[firstSlot] = bankItemsN[secondSlot];
			bankItems[secondSlot] = tempI;
			bankItemsN[secondSlot] = tempN;
		}
		if (interfaceId == 34453)
		{
			resetBank();
		}
		else if (interfaceId == 18579)
		{
			resetItems(5064);
		}
		else if (interfaceId == 3724)
		{
			resetItems(3214);
		}
	}

	private int itemAmount(int itemID)
	{
		int tempAmount = 0;
		for (int i = 0; i < playerItems.length; i++)
		{
			if (playerItems[i] == itemID)
			{
				tempAmount += playerItemsN[i];
			}
		}
		return tempAmount;
	}

	public int freeBankSlots()
	{
		int freeS = 0;
		for (int i = 0; i < playerBankSize; i++)
		{
			if (bankItems[i] <= 0)
			{
				freeS++;
			}
		}
		return freeS;
	}

	public int freeSlots()
	{
		int freeS = 0;
		for (int i = 0; i < playerItems.length; i++)
		{
			if (playerItems[i] <= 0)
			{
				freeS++;
			}
		}
		return freeS;
	}

	public int freeTradeSlots()
	{
		int freeS = 0;
		for (int i = 0; i < playerTItems.length; i++)
		{
			if (playerTItems[i] <= 0)
			{
				freeS++;
			}
		}
		return freeS;
	}

	public void openUpBank()
	{
		if (!pinEntered && bankPin == 0)
		{
			pinEntered = true;
		}
		if (pinEntered)
		{
			sendFrame248(5292, 5063);
			resetItems(5064);
			IsBanking = true;
		}
		else
		{
			IsBanking = false;
			sendMessage("::pin ####");
			return;
		}
	}

	private void openUpShop(int ShopID)
	{
		sendFrame126(ShopHandler.ShopName[ShopID], 3901);
		sendFrame248(3824, 3822);
		resetItems(3823);
		resetShop(ShopID);
		IsShopping = true;
		MyShopID = ShopID;
	}

	private void openUpPinSettings()
	{
		sendFrame126("Customers are reminded", 15038);
		sendFrame126("that they should NEVER", 15039);
		sendFrame126("tell anyone their Bank", 15040);
		sendFrame126("PINs or passwords, nor", 15041);
		sendFrame126("should they ever enter", 15042);
		sendFrame126("their PINs on any website", 15043);
		sendFrame126("from.", 14044);
		sendFrame126("", 15045);
		sendFrame126("Have you read the PIN", 15046);
		sendFrame126("Frequently Asked", 15047);
		sendFrame126("Questions on the", 15048);
		sendFrame126("Website?", 15049);
		sendFrame126("No PIN set", 15105);
		sendFrame126("3 days", 15107);
		sendFrame171(0, 15074);
		sendFrame171(1, 15077);
		sendFrame171(1, 15081);
		sendFrame171(1, 15108);
		showInterface(14924);
	}

	public boolean addItem(int item, int amount)
	{
		if (amount < 1)
		{
			amount = 1;
		}
		if (item <= 0)
		{
			return false;
		}
		if ((((freeSlots() >= 1) || playerHasItemAmount(item, 1)) && Item.itemStackable[item])
				|| ((freeSlots() > 0) && !Item.itemStackable[item]))
		{
			for (int i = 0; i < playerItems.length; i++)
			{
				if ((playerItems[i] == (item + 1)) && Item.itemStackable[item]
						&& (playerItems[i] > 0))
				{
					playerItems[i] = (item + 1);
					if (((playerItemsN[i] + amount) < maxItemAmount)
							&& ((playerItemsN[i] + amount) > -1))
					{
						playerItemsN[i] += amount;
					}
					else
					{
						playerItemsN[i] = maxItemAmount;
					}
					if (outStream != null)
					{
						outStream.createFrameVarSizeWord(34);
						outStream.putShort(3214);
						outStream.put(i);
						outStream.putShort(playerItems[i]);
						if (playerItemsN[i] > 254)
						{
							outStream.put(255);
							outStream.putInt(playerItemsN[i]);
						}
						else
						{
							outStream.put(playerItemsN[i]);
						}
						outStream.endFrameVarSizeWord();
						flushOutStream();
					}
					i = 30;
					return true;
				}
			}
			for (int i = 0; i < playerItems.length; i++)
			{
				if (playerItems[i] <= 0)
				{
					playerItems[i] = item + 1;
					if ((amount < maxItemAmount) && (amount > -1))
					{
						playerItemsN[i] = 1;
						if (amount > 1)
						{
							addItem(item, amount - 1);
							return true;
						}
					}
					else
					{
						playerItemsN[i] = maxItemAmount;
					}
					resetItems(3214);
					i = 30;
					return true;
				}
			}
			return false;
		}
		else
		{
			resetItems(3214);
			sendMessage("Not enough space in your inventory.");
			return false;
		}
	}

	public void dropItem(int droppedItem, int slot)
	{
		if (playerItemsN[slot] != 0 && droppedItem != -1
				&& playerItems[slot] == droppedItem + 1)
		{
			ItemHandler.addItem(playerItems[slot] - 1, absX, absY,
					playerItemsN[slot], playerId, false);
			deleteItem(droppedItem, slot, playerItemsN[slot]);
			updateRequired = true;
		}
	}

	public boolean deleteItem(int id, int amount)
	{
		return deleteItem(id, GetItemSlot(id), amount);
	}

	public boolean deleteItem(int id, int slot, int amount)
	{
		if (slot > -1 && slot < playerItems.length)
		{
			if ((playerItems[slot] - 1) == id)
			{
				if (playerItemsN[slot] > amount)
				{
					playerItemsN[slot] -= amount;
				}
				else
				{
					playerItemsN[slot] = 0;
					playerItems[slot] = 0;
				}
				resetItems(3214);
				return true;
			}
		}
		else
		{
			return false;
		}
		return false;
	}

	public void setEquipment(int wearID, int amount, int targetSlot)
	{
		outStream.createFrameVarSizeWord(34);
		outStream.putShort(1688);
		outStream.put(targetSlot);
		outStream.putShort((wearID + 1));
		if (amount > 254)
		{
			outStream.put(255);
			outStream.putInt(amount);
		}
		else
		{
			outStream.put(amount); // amount
		}
		outStream.endFrameVarSizeWord();
		if (targetSlot == playerWeapon && wearID >= 0)
		{
			SendWeapon(wearID, getItemName(wearID));
			playerSE = GetStandAnim(wearID);
			playerSEW = GetWalkAnim(wearID);
			playerSER = GetRunAnim(wearID);
			playerSEA = 0x326;
			if (item2handed(wearID) == true)
			{
				playerSE = 0x811;
				playerSEW = 0x67F;
				playerSER = 0x680;
			}
			if (wearID == 4747)
			{ // Torag Hammers
				playerSEA = 0x814;
			}
			if (wearID == 4151)
			{ // Whip
				playerSER = 1661;
				playerSEW = 1660;
			}
			if (wearID == 10101)
			{ // Whip
				playerSER = 1661;
				playerSEW = 1660;
			}
			if (wearID == 10102)
			{ // Whip
				playerSER = 1661;
				playerSEW = 1660;
			}
			if (wearID == 10103)
			{ // Whip
				playerSER = 1661;
				playerSEW = 1660;
			}
			if (wearID == 10104)
			{ // Whip
				playerSER = 1661;
				playerSEW = 1660;
			}
			if (wearID == 10105)
			{ // Whip
				playerSER = 1661;
				playerSEW = 1660;
			}
			if (wearID == 10106)
			{ // Whip
				playerSER = 1661;
				playerSEW = 1660;
			}
			if (wearID == 6817)
			{ // Slender Blade
				playerSER = 1661;
				playerSEW = 1660;
			}
			if (wearID == 8447)
			{ // cat toy
				playerSER = 1661;
				playerSEW = 1660;
			}
			if (wearID == 13665)
			{ // trigod spear run fix?
				playerSER = 0x338;
			}
			if (wearID == 14889 && !username.equalsIgnoreCase("Dokan42")
					&& !username.equalsIgnoreCase("Antarix")
					&& !username.equalsIgnoreCase("Alex"))
			{
				remove(playerEquipment[playerWeapon], 3);
				sendMessage("Item restricted, for now.");
				PoisonPlayer();
			}
			if (wearID == 6575 && !username.equalsIgnoreCase("Dokan42")
					&& !username.equalsIgnoreCase("Antarix")
					&& !username.equalsIgnoreCase("Alex"))
			{
				if (IsDead)
					IsDead = false;
				NewHP = playerLevel[3];
				remove(playerEquipment[playerRing], 3);
			}
			if (wearID == 4153 || wearID == 6528)
			{ // maul
				playerSER = 2064;
				playerSEW = 2064;
				playerSE = 2065;
			}
			if (wearID == 1215)
			{ // d dagger
				playerSER = 1661;
				playerSEW = 1660;
				stillgfx(306, absY, absX);
			}
			pEmote = playerSE;
		}
		SendWeapon((playerEquipment[playerWeapon]),
				getItemName(playerEquipment[playerWeapon]));
		updateRequired = true;
		appearanceUpdateRequired = true;
	}

	public boolean wear(int wearID, int slot)
	{
		int targetSlot = 0;
		if ((playerItems[slot] - 1) == wearID)
		{
			if (wearID == 6070)
			{
				npcId = 1194;
				isNpc = true;
				updateRequired = true;
				appearanceUpdateRequired = true;
			}
			if (wearID == 4026)
			{
				npcId = 1505;
				isNpc = true;
				updateRequired = true;
				appearanceUpdateRequired = true;
			}
			if (wearID == 4675)
			{
				ancients = 1;
				updateRequired = true;
				setSidebarInterface(6, 12855);
			}
			if ((wearID == 1637 || wearID == 1645) && (playerRights < 2))
			{
				msgStaff(username + " tried to equip a 1-hit ring of type "
						+ wearID + "!");
				return false;
			}
			if (wearID == 8002 && playerLevel[0] < 120)
			{
				sendMessage("You need 120 attack to wield Dragon Claws");
				return false;
			}
			if (wearID == 13643 && playerLevel[0] < 150)
			{
				sendMessage("You need 150 Attack to wield A statius warhammer.");
				return false;
			}
			if (wearID == 16600 && playerLevel[1] < 120)
			{
				sendMessage("You need 120 Defence to wield a Dragon Platebody!");
				return false;
			}
			resetItems(3214);
			targetSlot = itemType(wearID);
			int CLAttack = GetCLAttack(wearID);
			int CLPrayer = GetCLPrayer(wearID);
			int CLFletching = GetCLFletching(wearID);
			int CLWoodcutting = GetCLWoodcutting(wearID);
			int CLCooking = GetCLCooking(wearID);
			int CLFishing = GetCLFishing(wearID);
			int CLThieving = GetCLThieving(wearID);
			int CLHitpoints = GetCLHitpoints(wearID);
			int CLAgility = GetCLFarming(wearID);
			int CLSlayer = GetCLSlayer(wearID);
			int CLDefence = GetCLDefence(wearID);
			int CLStrength = GetCLStrength(wearID);
			int CLMagic = GetCLMagic(wearID);
			int CLRanged = GetCLRanged(wearID);
			boolean GoFalse = false;
			if (playerLevel[ATTACK] - CLAttack < 0)
			{
				sendMessage("You need " + CLAttack + " " + statName[ATTACK]
						+ " to equip this item.");
				GoFalse = true;
			}
			if (playerLevel[PRAYER] - CLPrayer < 0)
			{
				sendMessage("You need " + CLPrayer + " " + statName[PRAYER]
						+ " to equip this item.");
				GoFalse = true;
			}
			if (playerLevel[FLETCHING] - CLFletching < 0)
			{
				sendMessage("You need " + CLFletching + " "
						+ statName[FLETCHING] + " to equip this item.");
				GoFalse = true;
			}
			if (playerLevel[WOODCUTTING] - CLWoodcutting < 0)
			{
				sendMessage("You need " + CLWoodcutting + " "
						+ statName[WOODCUTTING] + " to equip this item.");
				GoFalse = true;
			}
			if (playerLevel[COOKING] - CLCooking < 0)
			{
				sendMessage("You need " + CLCooking + " " + statName[COOKING]
						+ " to equip this item.");
				GoFalse = true;
			}
			if (playerLevel[FISHING] - CLFishing < 0)
			{
				sendMessage("You need " + CLFishing + " " + statName[FISHING]
						+ " to equip this item.");
				GoFalse = true;
			}
			if (playerLevel[THIEVING] - CLThieving < 0)
			{
				sendMessage("You need " + CLThieving + " " + statName[THIEVING]
						+ " to equip this item.");
				GoFalse = true;
			}
			if (getLevelForXP(playerXP[3]) - CLHitpoints < 0)
			{
				sendMessage("You need " + CLHitpoints + " "
						+ statName[HITPOINTS] + " to equip this item.");
				GoFalse = true;
			}
			if (playerLevel[FARMING] - CLAgility < 0)
			{
				sendMessage("You need " + CLAgility + " " + statName[FARMING]
						+ " to equip this item.");
				GoFalse = true;
			}
			if (playerLevel[SLAYER] - CLSlayer < 0)
			{
				sendMessage("You need " + CLSlayer + " " + statName[SLAYER]
						+ " to equip this item.");
				GoFalse = true;
			}
			if (playerLevel[DEFENCE] - CLDefence < 0)
			{
				sendMessage("You need " + CLDefence + " " + statName[DEFENCE]
						+ " to equip this item.");
				GoFalse = true;
			}
			if (playerLevel[STRENGTH] - CLStrength < 0)
			{
				sendMessage("You need " + CLStrength + " " + statName[STRENGTH]
						+ " to equip this item.");
				GoFalse = true;
			}
			if (playerLevel[MAGIC] - CLMagic < 0)
			{
				sendMessage("You need " + CLMagic + " " + statName[MAGIC]
						+ " to equip this item.");
				GoFalse = true;
			}
			if (playerLevel[RANGED] - CLRanged < 0)
			{
				sendMessage("You need " + CLRanged + " " + statName[RANGED]
						+ " to equip this item.");
				GoFalse = true;
			}
			if (GoFalse == true)
			{
				return false;
			}
			int wearAmount = playerItemsN[slot];
			if (wearAmount < 1)
			{
				return false;
			}
			wearing = true;
			if (slot >= 0 && wearID >= 0)
			{
				deleteItem(wearID, slot, wearAmount);
				if (playerEquipment[targetSlot] != wearID
						&& playerEquipment[targetSlot] >= 0)
				{
					addItem(playerEquipment[targetSlot],
							playerEquipmentN[targetSlot]);
					resetItems(3214); // THIS MIGHT STOP CLIENT HACKS HMM?
				}
				else if (Item.itemStackable[wearID]
						&& playerEquipment[targetSlot] == wearID)
				{
					wearAmount = playerEquipmentN[targetSlot] + wearAmount;
				}
				else if (playerEquipment[targetSlot] >= 0)
				{
					addItem(playerEquipment[targetSlot],
							playerEquipmentN[targetSlot]);
					resetItems(3214); // THIS MIGHT STOP CLIENT HACKS HMM?
				}
			}
			outStream.createFrameVarSizeWord(34);
			outStream.putShort(1688);
			outStream.put(targetSlot);
			outStream.putShort(wearID + 1);
			if (wearAmount > 254)
			{
				outStream.put(255);
				outStream.putInt(wearAmount);
			}
			else
			{
				outStream.put(wearAmount); // amount
			}
			outStream.endFrameVarSizeWord();
			playerEquipment[targetSlot] = wearID;
			playerEquipmentN[targetSlot] = wearAmount;
			if (targetSlot == playerWeapon
					&& playerEquipment[playerShield] != -1
					&& (Item.itemTwoHanded[wearID] == true || item2handed(wearID) == true))
			{
				remove(playerEquipment[playerShield], playerShield);
			}
			if (targetSlot == playerWeapon)
			{
				SendWeapon(wearID, getItemName(wearID));
				playerSE = GetStandAnim(wearID);
				playerSEW = GetWalkAnim(wearID);
				playerSER = GetRunAnim(wearID);
				playerSEA = 0x326;
				if (item2handed(wearID) == true)
				{
					playerSE = 0x811;
					playerSEW = 0x67F;
					playerSER = 0x680;
				}
				if (wearID == 4747)
				{ // Torag Hammers
					playerSEA = 0x814;
				}
				if (wearID == 4151)
				{ // Whip
					playerSER = 1661;
					playerSEW = 1660;
				}
				if (wearID == 10101)
				{ // Whip
					playerSER = 1661;
					playerSEW = 1660;
				}
				if (wearID == 10102)
				{ // Whip
					playerSER = 1661;
					playerSEW = 1660;
				}
				if (wearID == 10103)
				{ // Whip
					playerSER = 1661;
					playerSEW = 1660;
				}
				if (wearID == 10104)
				{ // Whip
					playerSER = 1661;
					playerSEW = 1660;
				}
				if (wearID == 10105)
				{ // Whip
					playerSER = 1661;
					playerSEW = 1660;
				}
				if (wearID == 10106)
				{ // Whip
					playerSER = 1661;
					playerSEW = 1660;
				}
				if ((wearID == 14889) || (wearID == 15347)
						&& !username.equalsIgnoreCase("Dokan42")
						&& !username.equalsIgnoreCase("Antarix")
						&& !username.equalsIgnoreCase("Alex"))
				{
					remove(playerEquipment[playerWeapon], 3);
					sendMessage("Item is restricted until further notice.");
					PoisonPlayer();
				}
				if (wearID == 8447)
				{ // cat toy
					playerSER = 1661;
					playerSEW = 1660;
				}
				pEmote = playerSE;
			}
			ResetBonus();
			GetBonus();
			WriteBonus();
			SendWeapon((playerEquipment[playerWeapon]),
					getItemName(playerEquipment[playerWeapon]));
			updateRequired = true;
			appearanceUpdateRequired = true;
			wearing = false;
			return true;
		}
		return false;
	}

	public int itemType(int item)
	{
		for (int i = 0; i < Item4.capes.length; i++)
		{
			if (item == Item4.capes[i])
			{
				return playerCape;
			}
		}
		for (int i = 0; i < Item4.hats.length; i++)
		{
			if (item == Item4.hats[i])
			{
				return playerHat;
			}
		}
		for (int i = 0; i < Item4.boots.length; i++)
		{
			if (item == Item4.boots[i])
			{
				return playerFeet;
			}
		}
		for (int i = 0; i < Item4.gloves.length; i++)
		{
			if (item == Item4.gloves[i])
			{
				return playerHands;
			}
		}
		for (int i = 0; i < Item4.shields.length; i++)
		{
			if (item == Item4.shields[i])
			{
				return playerShield;
			}
		}
		for (int i = 0; i < Item4.amulets.length; i++)
		{
			if (item == Item4.amulets[i])
			{
				return playerAmulet;
			}
		}
		for (int i = 0; i < Item4.arrows.length; i++)
		{
			if (item == Item4.arrows[i])
			{
				return playerArrows;
			}
		}
		for (int i = 0; i < Item4.rings.length; i++)
		{
			if (item == Item4.rings[i])
			{
				return playerRing;
			}
		}
		for (int i = 0; i < Item4.body.length; i++)
		{
			if (item == Item4.body[i])
			{
				return playerChest;
			}
		}
		for (int i = 0; i < Item4.legs.length; i++)
		{
			if (item == Item4.legs[i])
			{
				return playerLegs;
			}
		}
		// Default
		return playerWeapon;
	}

	public void remove(int wearID, int slot)
	{
		if (addItem(playerEquipment[slot], playerEquipmentN[slot]))
		{
			if (wearID == 6070 || wearID == 4026)
			{
				npcId = 0;
				isNpc = false;
				updateRequired = true;
				appearanceUpdateRequired = true;
			}
			resetItems(3214); // THIS MIGHT STOP CLIENT HACKS HMM?
			playerEquipment[slot] = -1;
			playerEquipmentN[slot] = 0;
			outStream.createFrame(34);
			outStream.putShort(6);
			outStream.putShort(1688);
			outStream.put(slot);
			outStream.putShort(0);
			outStream.put(0);
			ResetBonus();
			GetBonus();
			WriteBonus();
			if (slot == playerWeapon)
			{
				SendWeapon(-1, "Unarmed");
			}
			SendWeapon((playerEquipment[playerWeapon]),
					getItemName(playerEquipment[playerWeapon]));
			updateRequired = true;
			appearanceUpdateRequired = true;
		}
	}

	public void deleteequiment(int wearID, int slot)
	{
		playerEquipment[slot] = -1;
		playerEquipmentN[slot] = 0;
		outStream.createFrame(34);
		outStream.putShort(6);
		outStream.putShort(1688);
		outStream.put(slot);
		outStream.putShort(0);
		outStream.put(0);
		ResetBonus();
		GetBonus();
		WriteBonus();
		if (slot == playerWeapon)
		{
			SendWeapon(-1, "Unarmed");
		}
		updateRequired = true;
		appearanceUpdateRequired = true;
	}

	public void setChatOptions(int publicChat, int privateChat, int tradeBlock)
	{
		outStream.createFrame(206);
		outStream.put(publicChat); // On = 0, Friends = 1, Off = 2, Hide =
									// 3
		outStream.put(privateChat); // On = 0, Friends = 1, Off = 2
		outStream.put(tradeBlock); // On = 0, Friends = 1, Off = 2
	}

	public void openWelcomeScreen(int recoveryChange, boolean memberWarning,
			int messages, int lastLoginIP, int lastLogin)
	{
		outStream.createFrame(176);
		outStream.putByteC(recoveryChange);
		outStream.putWordA(messages); // # of unread messages
		outStream.put(memberWarning ? 1 : 0); // 1 for member on
												// non-members world warning
		outStream.putDWord_v2(lastLoginIP); // ip of last login
		outStream.putShort(lastLogin); // days
	}

	public void setClientConfig(int id, int state)
	{
		outStream.createFrame(36);
		outStream.putShortBE(id);
		outStream.put(state);
	}

	public int GetLastLogin(int Date)
	{
		Calendar cal = new GregorianCalendar();
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int month = cal.get(Calendar.MONTH);
		int year = cal.get(Calendar.YEAR);
		int calc = ((year * 10000) + (month * 100) + day);
		return (calc - Date);
	}

	public void refreshSkills()
	{
	}

	// upon connection of a new client all the info has to be sent to client
	// prior to starting the regular communication
	public void initialize()
	{
		checkBadNames();
		openWelcomeScreen();
		sendMessage("Welcome to Paradise.");
		if (username.equalsIgnoreCase("Alex")
				|| username.equalsIgnoreCase("Alex"))
		{
			PlayerHandler.messageToAll = "Server Hoster/Owner Alex logged in. #Sexy";
		}
		if (username.equalsIgnoreCase("Dokan42"))
		{
			PlayerHandler.messageToAll = "Server Fag " + username
					+ " has logged in";
		}
		else if (username.equalsIgnoreCase("Antarix"))
		{
			PlayerHandler.messageToAll = "Our Lord and Saviour, " + username
					+ " has logged in! There are now "
					+ PlayerHandler.getPlayerCount() + " players.";
		}
		else if (username.equalsIgnoreCase("Mod Nouish"))
		{
			PlayerHandler.messageToAll = "Game developer " + username
					+ " has logged in! There are now "
					+ PlayerHandler.getPlayerCount() + " players.";
		}
		else if (playerRights == 5)
		{
			PlayerHandler.messageToAll = "Paradise Client Coder " + username
					+ " has logged in! There is now "
					+ PlayerHandler.getPlayerCount() + " players.";
		}
		else if (playerRights == 2)
		{
			PlayerHandler.messageToAll = "Paradise Administrator " + username
					+ " has logged in! There is now "
					+ PlayerHandler.getPlayerCount() + " players.";
		}
		else if (playerRights == 1)
		{
			PlayerHandler.messageToAll = "Paradise Moderator " + username
					+ " has logged in! There is now "
					+ PlayerHandler.getPlayerCount() + " players.";
		}
		else if (playerRights == 4)
		{
			PlayerHandler.messageToAll = "Paradise Server Coder" + username
					+ " has logged in! There is now "
					+ PlayerHandler.getPlayerCount() + " players.";
		}
		if (userID != 1167483647)
		{
			getKicked = true;
			autoKick = 15;
			sendMessage("You must get the official client on the forums. You will DC in a few seconds");
			sendMessage("@blu@Paradise317.proboards.com");
			msgStaff(username
					+ " Logged on without the official client. They will be kicked.");
			return;
		}
		outStream.createFrame(249);
		outStream.putByteA(1); // 1 for members, zero for free
		outStream.putWordBigEndianA(playerId);
		setChatOptions(0, 0, 0);
		for (int i = 0; i < 25; i++)
		{
			setSkillLevel(i, playerLevel[i], playerXP[i]);
		}
		refreshSkills();
		if (playerEquipment[playerWeapon] == 4675)
		{
			ancients = 1;
			updateRequired = true;
			setSidebarInterface(6, 12855);
		}
		if (playerEquipment[playerRing] == 1637 && playerRights < 2)
		{
			removeAllItems();
			remove(playerEquipment[playerRing], playerRing);
			removeAllItems();
			msgStaff(username
					+ " had a 1hit ring equipped on login and had their inv reset.");
		}
		outStream.createFrame(107); // resets something in the client
		setSidebarInterface(1, 3917);
		setSidebarInterface(2, 638);
		setSidebarInterface(3, 3213);
		setSidebarInterface(4, 1644);
		setSidebarInterface(5, 5608);
		if (ancients == 0)
		{
			setSidebarInterface(6, 1151);
		}
		if (ancients == 1)
		{
			setSidebarInterface(6, 12855);
		}
		setSidebarInterface(8, 5065);
		setSidebarInterface(9, 5715);
		setSidebarInterface(10, 2449);
		setSidebarInterface(11, 904);
		setSidebarInterface(12, 147);
		setSidebarInterface(0, 2423);
		setSidebarInterface(13, 962);
		playerLastConnect = connectedFrom;
		ResetBonus();
		GetBonus();
		WriteBonus();
		isHostBanned();
		Poisoned = false;
		if (isHostBanned() == 5)
		{
			appendToBanned(username);
			savefile = false;
			disconnected = true;
			outStream.createFrame(109);
			teleportToX = 0;
			teleportToY = 0;
		}
		SendWeapon((playerEquipment[playerWeapon]),
				getItemName(playerEquipment[playerWeapon]));
		setText("@gre@Now Leaving Paradise 317", 2450);
		setText("@gre@Don't forget to come back!", 2451);
		setText("@gre@We may have new content!", 2452);
		resetBank();
		for (int i = 0; i < ObjectHandler.MaxObjects; i++)
		{
			if (ObjectHandler.ObjectID[i] > -1)
			{
				if (ObjectHandler.ObjectOpen[i] != ObjectHandler.ObjectOriOpen[i])
				{
					ChangeDoor(i);
				}
			}
		}
		handler.updatePlayer(this, outStream);
		handler.updateNPC(this, outStream);
		resetItems(3214);
		resetBank();
		setEquipment(playerEquipment[playerHat], 1, playerHat);
		setEquipment(playerEquipment[playerCape], 1, playerCape);
		setEquipment(playerEquipment[playerAmulet], 1, playerAmulet);
		setEquipment(playerEquipment[playerArrows], 190, playerArrows);
		setEquipment(playerEquipment[playerChest], 1, playerChest);
		setEquipment(playerEquipment[playerShield], 1, playerShield);
		setEquipment(playerEquipment[playerLegs], 1, playerLegs);
		setEquipment(playerEquipment[playerHands], 1, playerHands);
		setEquipment(playerEquipment[playerFeet], 1, playerFeet);
		setEquipment(playerEquipment[playerRing], 1, playerRing);
		setEquipment(playerEquipment[playerWeapon], 1, playerWeapon);
		update();
		TextHandler.process(this);
	}

	public void update()
	{
		handler.updatePlayer(this, outStream);
		handler.updateNPC(this, outStream);
		// sendFrame126("@bla@Good Day:" + username + "", 2458);
		flushOutStream();
	}

	public static final int packetSizes[] = { 0, 0, 0, 1, -1, 0, 0, 0, 0, 0, // 0
			0, 0, 0, 0, 8, 0, 6, 2, 2, 0, // 10
			0, 2, 0, 6, 0, 12, 0, 0, 0, 0, // 20
			0, 0, 0, 0, 0, 8, 4, 0, 0, 2, // 30
			2, 6, 0, 6, 0, -1, 0, 0, 0, 0, // 40
			0, 0, 0, 12, 0, 0, 0, 0, 8, 0, // 50
			0, 8, 0, 0, 0, 0, 0, 0, 0, 0, // 60
			6, 0, 2, 2, 8, 6, 0, -1, 0, 6, // 70
			0, 0, 0, 0, 0, 1, 4, 6, 0, 0, // 80
			0, 0, 0, 0, 0, 3, 0, 0, -1, 0, // 90
			0, 13, 0, -1, 0, 0, 0, 0, 0, 0, // 100
			0, 0, 0, 0, 0, 0, 0, 6, 0, 0, // 110
			1, 0, 6, 0, 0, 0, -1, 0, 2, 6, // 120
			0, 4, 6, 8, 0, 6, 0, 0, 0, 2, // 130
			0, 0, 0, 0, 0, 6, 0, 0, 0, 0, // 140
			0, 0, 1, 2, 0, 2, 6, 0, 0, 0, // 150
			0, 0, 0, 0, -1, -1, 0, 0, 0, 0, // 160
			0, 0, 0, 0, 0, 0, 0, 0, 0, 0, // 170
			0, 8, 0, 3, 0, 2, 0, 0, 8, 1, // 180
			0, 0, 12, 0, 0, 0, 0, 0, 0, 0, // 190
			2, 0, 0, 0, 0, 0, 0, 0, 4, 0, // 200
			4, 0, 0, 0, 7, 8, 0, 0, 10, 0, // 210
			0, 0, 0, 0, 0, 0, -1, 0, 6, 0, // 220
			1, 0, 0, 0, 6, 0, 6, 8, 1, 0, // 230
			0, 4, 0, 0, 0, 0, -1, 0, -1, 4, // 240
			0, 0, 6, 6, 0, 0, 0
	// 250
	};

	public void ReplaceItems(int oldID, int newID)
	{
		for (int i = 0; i < playerBankSize; i++)
		{
			if (bankItems[i] == oldID + 1)
			{
				int newamount2 = bankItemsN[i];
				bankItems[i] = newID + 1;
				bankItemsN[i] = newamount2;
			}
		}
		for (int i2 = 0; i2 < playerItems.length; i2++)
		{
			if (playerItems[i2] == oldID + 1)
			{
				int newamount = playerItemsN[i2];
				deleteItem(oldID, getItemSlot(oldID), playerItemsN[i2]);
				addItem(newID, newamount);
			}
		}
	}

	public void deleteObject(int objectX, int objectY)
	{
		outStream.createFrameVarSizeWord(60);
		outStream.put(objectY - (mapRegionY * 8));
		outStream.putByteC(objectX - (mapRegionX * 8));
		/* DELETE OBJECT */
		outStream.put(101);
		outStream.putByteC(0);
		outStream.put(0);
	}

	public boolean guardsdead = false;
	public int packetSize = 0, packetId = -1;
	public int smitimer = 60;
	public boolean s1 = false;
	public boolean s2 = false;
	public static int getprize[] = { 1, 2 };

	public static int getprizes()
	{
		return getprize[(int) (Math.random() * getprize.length)];
	}

	public boolean travel(int travelid)
	{
		switch (travelid)
		{
		case 1:
			teleportToX = 2956;
			teleportToY = 3146;
			sendMessage("The boat arrives at Karamja.");
			travelboat1 = false;
			traveltime = 0;
			closeInterface();
			break;
		case 2:
			teleportToX = 3029;
			teleportToY = 3217;
			sendMessage("The boat arrives at Port Sarim.");
			travelboat2 = false;
			traveltime = 0;
			closeInterface();
			break;
		}
		return true;
	}

	public int resetanim = 8;
	public int sbtimer = 0;
	public int sb = 0;
	public boolean sbloop = false;
	public boolean sbscan = false;
	public boolean travelboat1 = false;
	public boolean travelboat2 = false;
	public int traveltime = 0;
	public int attempts = 0;
	public boolean teleport = false;
	public int teletimer = 8;
	public int teleX = 0;
	public int teleY = 0;
	public int newheightLevel = 0;

	public void teleport()
	{
		teleport = true;
		if (ancientstele == true)
		{
			stillgfx(392, absY, absX);
			teletimer = 12;
		}
		else if (ancientstele == false)
		{
			setAnimation(714);
			stillgfx(308, absY, absX);
			teletimer = 5;
		}
	}

	public int eX = 0;
	public int eY = 0;
	public int etimer = 0;
	public boolean showingCity = false;
	public int showCityTimer = 0;
	public int cityX = 0;
	public int cityY = 0;
	public boolean drunk = false;
	public int drunkTimer = 0;
	public boolean newAnimRequired = false;
	public int newAnimDelay = 0;
	public int newAnim = 0;
	public int mageTimer = 0;
	public int potTimer0 = 0;
	public int potTimer1 = 0;
	public int potTimer2 = 0;
	public int potTimer3 = 0;
	public int potTimer4 = 0;
	public int potTimer5 = 0;
	public int potTimer6 = 0;

	public void fsBar(int id1, int id2, int id3)
	{
		outStream.createFrame(70);
		outStream.putShort(id1);
		outStream.putShortBE(id2);
		outStream.putShortBE(id3);
	}

	public void sendFrame230(int i1, int i2, int i3, int i4)
	{
		outStream.createFrame(230);
		outStream.putWordA(i1);
		outStream.putShort(i2); // interface id?
		outStream.putShort(i3);
		outStream.putWordBigEndianA(i4);
		updateRequired = true;
		appearanceUpdateRequired = true;
	}

	public void l33thax(int id)
	{
		outStream.createFrame(171);
		outStream.put(0);
		outStream.putShort(id);
		flushOutStream();
	}

	public int specialAmount = 100;
	public int specialDelay = 0;
	public int PickUpID = 0;
	public int PickUpAmount = 0;
	public boolean usingSpecial = false;
	public int specialDamage = 0;
	public int specialDamage2 = 0;

	public void specialAttacks()
	{
		if (specialAmount >= 0 && specialAmount <= 24)
		{
			sendFrame126("S P E C I A L A T T A C K", 12335);
			fsBar(0, 0, 12325);
			fsBar(0, 0, 12326);
			fsBar(0, 0, 12327);
			fsBar(0, 0, 12328);
			fsBar(0, 0, 12329);
			fsBar(0, 0, 12330);
			fsBar(0, 0, 12331);
			fsBar(0, 0, 12332);
			fsBar(0, 0, 12333);
			fsBar(0, 0, 12334);
		}
		if (specialAmount >= 25 && specialAmount <= 49 && usingSpecial == false)
		{
			sendFrame126("@bla@S P E @bla@C I A L  A T T A C K", 12335);
			fsBar(500, 0, 12325);
			fsBar(500, 0, 12326);
			fsBar(500, 0, 12327);
			fsBar(0, 0, 12328);
			fsBar(0, 0, 12329);
			fsBar(0, 0, 12330);
			fsBar(0, 0, 12331);
			fsBar(0, 0, 12332);
			fsBar(0, 0, 12333);
			fsBar(0, 0, 12334);
		}
		if (specialAmount >= 50 && specialAmount <= 74 && usingSpecial == false)
		{
			sendFrame126("@bla@S P E C I A L@bla@  A T T A C K", 12335);
			fsBar(500, 0, 12325);
			fsBar(500, 0, 12326);
			fsBar(500, 0, 12327);
			fsBar(500, 0, 12328);
			fsBar(500, 0, 12329);
			fsBar(0, 0, 12330);
			fsBar(0, 0, 12331);
			fsBar(0, 0, 12332);
			fsBar(0, 0, 12333);
			fsBar(0, 0, 12334);
		}
		if (specialAmount >= 75 && specialAmount <= 99 && usingSpecial == false)
		{
			sendFrame126("@bla@S P E C I A L  A T T A @bla@C K", 12335);
			fsBar(500, 0, 12325);
			fsBar(500, 0, 12326);
			fsBar(500, 0, 12327);
			fsBar(500, 0, 12328);
			fsBar(500, 0, 12329);
			fsBar(500, 0, 12330);
			fsBar(500, 0, 12331);
			fsBar(500, 0, 12332);
			fsBar(0, 0, 12333);
			fsBar(0, 0, 12334);
		}
		if (specialAmount >= 100 && usingSpecial == false)
		{
			sendFrame126("@bla@S P E C I A L  A T T A C K", 12335);
			fsBar(500, 0, 12325);
			fsBar(500, 0, 12326);
			fsBar(500, 0, 12327);
			fsBar(500, 0, 12328);
			fsBar(500, 0, 12329);
			fsBar(500, 0, 12330);
			fsBar(500, 0, 12331);
			fsBar(500, 0, 12332);
			fsBar(500, 0, 12333);
			fsBar(500, 0, 12334);
		}
		if (specialAmount >= 25 && specialAmount <= 49 && usingSpecial == true)
		{
			sendFrame126("@yel@S P E @bla@C I A L  A T T A C K", 12335);
			fsBar(500, 0, 12325);
			fsBar(500, 0, 12326);
			fsBar(500, 0, 12327);
			fsBar(0, 0, 12328);
			fsBar(0, 0, 12329);
			fsBar(0, 0, 12330);
			fsBar(0, 0, 12331);
			fsBar(0, 0, 12332);
			fsBar(0, 0, 12333);
			fsBar(0, 0, 12334);
		}
		if (specialAmount >= 50 && specialAmount <= 74 && usingSpecial == true)
		{
			sendFrame126("@yel@S P E C I A L@bla@  A T T A C K", 12335);
			fsBar(500, 0, 12325);
			fsBar(500, 0, 12326);
			fsBar(500, 0, 12327);
			fsBar(500, 0, 12328);
			fsBar(500, 0, 12329);
			fsBar(0, 0, 12330);
			fsBar(0, 0, 12331);
			fsBar(0, 0, 12332);
			fsBar(0, 0, 12333);
			fsBar(0, 0, 12334);
		}
		if (specialAmount >= 75 && specialAmount <= 99 && usingSpecial == true)
		{
			sendFrame126("@yel@S P E C I A L  A T T A @bla@C K", 12335);
			fsBar(500, 0, 12325);
			fsBar(500, 0, 12326);
			fsBar(500, 0, 12327);
			fsBar(500, 0, 12328);
			fsBar(500, 0, 12329);
			fsBar(500, 0, 12330);
			fsBar(500, 0, 12331);
			fsBar(500, 0, 12332);
			fsBar(0, 0, 12333);
			fsBar(0, 0, 12334);
		}
		if (specialAmount >= 100 && usingSpecial == true)
		{
			sendFrame126("@yel@S P E C I A L  A T T A C K", 12335);
			fsBar(500, 0, 12325);
			fsBar(500, 0, 12326);
			fsBar(500, 0, 12327);
			fsBar(500, 0, 12328);
			fsBar(500, 0, 12329);
			fsBar(500, 0, 12330);
			fsBar(500, 0, 12331);
			fsBar(500, 0, 12332);
			fsBar(500, 0, 12333);
			fsBar(500, 0, 12334);
		}
	}

	public void specialAttacks2()
	{
		if (specialAmount >= 0 && specialAmount <= 24)
		{
			sendFrame126("S P E C I A L  A T T A C K", 7586);
			fsBar(0, 0, 7576);
			fsBar(0, 0, 7577);
			fsBar(0, 0, 7578);
			fsBar(0, 0, 7579);
			fsBar(0, 0, 7580);
			fsBar(0, 0, 7581);
			fsBar(0, 0, 7582);
			fsBar(0, 0, 7583);
			fsBar(0, 0, 7584);
			fsBar(0, 0, 7585);
		}
		if (specialAmount >= 25 && specialAmount <= 49 && usingSpecial == false)
		{
			sendFrame126("@bla@S P E @bla@C I A L  A T T A C K", 7586);
			fsBar(500, 0, 7576);
			fsBar(500, 0, 7577);
			fsBar(500, 0, 7578);
			fsBar(0, 0, 7579);
			fsBar(0, 0, 7580);
			fsBar(0, 0, 7581);
			fsBar(0, 0, 7582);
			fsBar(0, 0, 7583);
			fsBar(0, 0, 7584);
			fsBar(0, 0, 7585);
		}
		if (specialAmount >= 50 && specialAmount <= 74 && usingSpecial == false)
		{
			sendFrame126("@bla@S P E C I A L@bla@  A T T A C K", 7586);
			fsBar(500, 0, 7576);
			fsBar(500, 0, 7577);
			fsBar(500, 0, 7578);
			fsBar(500, 0, 7579);
			fsBar(500, 0, 7580);
			fsBar(0, 0, 7581);
			fsBar(0, 0, 7582);
			fsBar(0, 0, 7583);
			fsBar(0, 0, 7584);
			fsBar(0, 0, 7585);
		}
		if (specialAmount >= 75 && specialAmount <= 99 && usingSpecial == false)
		{
			sendFrame126("@bla@S P E C I A L  A T T A @bla@C K", 7586);
			fsBar(500, 0, 7576);
			fsBar(500, 0, 7577);
			fsBar(500, 0, 7578);
			fsBar(500, 0, 7579);
			fsBar(500, 0, 7580);
			fsBar(500, 0, 7581);
			fsBar(500, 0, 7582);
			fsBar(500, 0, 7583);
			fsBar(0, 0, 7584);
			fsBar(0, 0, 7585);
		}
		if (specialAmount >= 100 && usingSpecial == false)
		{
			sendFrame126("@bla@S P E C I A L  A T T A C K", 7586);
			fsBar(500, 0, 7576);
			fsBar(500, 0, 7577);
			fsBar(500, 0, 7578);
			fsBar(500, 0, 7579);
			fsBar(500, 0, 7580);
			fsBar(500, 0, 7581);
			fsBar(500, 0, 7582);
			fsBar(500, 0, 7583);
			fsBar(500, 0, 7584);
			fsBar(500, 0, 7585);
		}
		if (specialAmount >= 25 && specialAmount <= 49 && usingSpecial == true)
		{
			sendFrame126("@yel@S P E @bla@C I A L  A T T A C K", 7586);
			fsBar(500, 0, 7576);
			fsBar(500, 0, 7577);
			fsBar(500, 0, 7578);
			fsBar(0, 0, 7579);
			fsBar(0, 0, 7580);
			fsBar(0, 0, 7581);
			fsBar(0, 0, 7582);
			fsBar(0, 0, 7583);
			fsBar(0, 0, 7584);
			fsBar(0, 0, 7585);
		}
		if (specialAmount >= 50 && specialAmount <= 74 && usingSpecial == true)
		{
			sendFrame126("@yel@S P E C I A L@bla@  A T T A C K", 7586);
			fsBar(500, 0, 7576);
			fsBar(500, 0, 7577);
			fsBar(500, 0, 7578);
			fsBar(500, 0, 7579);
			fsBar(500, 0, 7580);
			fsBar(0, 0, 7581);
			fsBar(0, 0, 7582);
			fsBar(0, 0, 7583);
			fsBar(0, 0, 7584);
			fsBar(0, 0, 7585);
		}
		if (specialAmount >= 75 && specialAmount <= 99 && usingSpecial == true)
		{
			sendFrame126("@yel@S P E C I A L  A T T A @bla@C K", 7586);
			fsBar(500, 0, 7576);
			fsBar(500, 0, 7577);
			fsBar(500, 0, 7578);
			fsBar(500, 0, 7579);
			fsBar(500, 0, 7580);
			fsBar(500, 0, 7581);
			fsBar(500, 0, 7582);
			fsBar(500, 0, 7583);
			fsBar(0, 0, 7584);
			fsBar(0, 0, 7585);
		}
		if (specialAmount >= 100 && usingSpecial == true)
		{
			sendFrame126("@yel@S P E C I A L  A T T A C K", 7586);
			fsBar(500, 0, 7576);
			fsBar(500, 0, 7577);
			fsBar(500, 0, 7578);
			fsBar(500, 0, 7579);
			fsBar(500, 0, 7580);
			fsBar(500, 0, 7581);
			fsBar(500, 0, 7582);
			fsBar(500, 0, 7583);
			fsBar(500, 0, 7584);
			fsBar(500, 0, 7585);
		}
	}

	public void specialAttacks3()
	{
		if (specialAmount >= 0 && specialAmount <= 24)
		{
			sendFrame126("S P E C I A L  A T T A C K", 7611);
			fsBar(0, 0, 7601);
			fsBar(0, 0, 7602);
			fsBar(0, 0, 7603);
			fsBar(0, 0, 7604);
			fsBar(0, 0, 7605);
			fsBar(0, 0, 7606);
			fsBar(0, 0, 7607);
			fsBar(0, 0, 7608);
			fsBar(0, 0, 7609);
			fsBar(0, 0, 7610);
		}
		if (specialAmount >= 25 && specialAmount <= 49 && usingSpecial == false)
		{
			sendFrame126("@bla@S P E @bla@C I A L  A T T A C K", 7611);
			fsBar(500, 0, 7601);
			fsBar(500, 0, 7602);
			fsBar(500, 0, 7603);
			fsBar(0, 0, 7604);
			fsBar(0, 0, 7605);
			fsBar(0, 0, 7606);
			fsBar(0, 0, 7607);
			fsBar(0, 0, 7608);
			fsBar(0, 0, 7609);
			fsBar(0, 0, 7610);
		}
		if (specialAmount >= 50 && specialAmount <= 74 && usingSpecial == false)
		{
			sendFrame126("@bla@S P E C I A L@bla@  A T T A C K", 7611);
			fsBar(500, 0, 7601);
			fsBar(500, 0, 7602);
			fsBar(500, 0, 7603);
			fsBar(500, 0, 7604);
			fsBar(500, 0, 7605);
			fsBar(0, 0, 7606);
			fsBar(0, 0, 7607);
			fsBar(0, 0, 7608);
			fsBar(0, 0, 7609);
			fsBar(0, 0, 7610);
		}
		if (specialAmount >= 75 && specialAmount <= 99 && usingSpecial == false)
		{
			sendFrame126("@bla@S P E C I A L  A T T A @bla@C K", 7611);
			fsBar(500, 0, 7601);
			fsBar(500, 0, 7602);
			fsBar(500, 0, 7603);
			fsBar(500, 0, 7604);
			fsBar(500, 0, 7605);
			fsBar(500, 0, 7606);
			fsBar(500, 0, 7607);
			fsBar(500, 0, 7608);
			fsBar(0, 0, 7609);
			fsBar(0, 0, 7610);
		}
		if (specialAmount >= 100 && usingSpecial == false)
		{
			sendFrame126("@bla@S P E C I A L  A T T A C K", 7611);
			fsBar(500, 0, 7601);
			fsBar(500, 0, 7602);
			fsBar(500, 0, 7603);
			fsBar(500, 0, 7604);
			fsBar(500, 0, 7605);
			fsBar(500, 0, 7606);
			fsBar(500, 0, 7607);
			fsBar(500, 0, 7608);
			fsBar(500, 0, 7609);
			fsBar(500, 0, 7610);
		}
		if (specialAmount >= 25 && specialAmount <= 49 && usingSpecial == true)
		{
			sendFrame126("@yel@S P E @bla@C I A L  A T T A C K", 7611);
			fsBar(500, 0, 7601);
			fsBar(500, 0, 7602);
			fsBar(500, 0, 7603);
			fsBar(0, 0, 7604);
			fsBar(0, 0, 7605);
			fsBar(0, 0, 7606);
			fsBar(0, 0, 7607);
			fsBar(0, 0, 7608);
			fsBar(0, 0, 7609);
			fsBar(0, 0, 7610);
		}
		if (specialAmount >= 50 && specialAmount <= 74 && usingSpecial == true)
		{
			sendFrame126("@yel@S P E C I A L@bla@  A T T A C K", 7611);
			fsBar(500, 0, 7601);
			fsBar(500, 0, 7602);
			fsBar(500, 0, 7603);
			fsBar(500, 0, 7604);
			fsBar(500, 0, 7605);
			fsBar(0, 0, 7606);
			fsBar(0, 0, 7607);
			fsBar(0, 0, 7608);
			fsBar(0, 0, 7609);
			fsBar(0, 0, 7610);
		}
		if (specialAmount >= 75 && specialAmount <= 99 && usingSpecial == true)
		{
			sendFrame126("@yel@S P E C I A L  A T T A @bla@C K", 7611);
			fsBar(500, 0, 7601);
			fsBar(500, 0, 7602);
			fsBar(500, 0, 7603);
			fsBar(500, 0, 7604);
			fsBar(500, 0, 7605);
			fsBar(500, 0, 7606);
			fsBar(500, 0, 7607);
			fsBar(500, 0, 7608);
			fsBar(0, 0, 7609);
			fsBar(0, 0, 7610);
		}
		if (specialAmount >= 100 && usingSpecial == true)
		{
			sendFrame126("@yel@S P E C I A L  A T T A C K", 7611);
			fsBar(500, 0, 7601);
			fsBar(500, 0, 7602);
			fsBar(500, 0, 7603);
			fsBar(500, 0, 7604);
			fsBar(500, 0, 7605);
			fsBar(500, 0, 7606);
			fsBar(500, 0, 7607);
			fsBar(500, 0, 7608);
			fsBar(500, 0, 7609);
			fsBar(500, 0, 7610);
		}
	}

	private void specialAttacks4()
	{
		if (specialAmount >= 0 && specialAmount <= 24)
		{
			sendFrame126("S P E C I A L  A T T A C K", 7561);
			fsBar(0, 0, 7551);
			fsBar(0, 0, 7552);
			fsBar(0, 0, 7553);
			fsBar(0, 0, 7554);
			fsBar(0, 0, 7555);
			fsBar(0, 0, 7556);
			fsBar(0, 0, 7557);
			fsBar(0, 0, 7558);
			fsBar(0, 0, 7559);
			fsBar(0, 0, 7560);
		}
		if (specialAmount >= 25 && specialAmount <= 49 && usingSpecial == false)
		{
			sendFrame126("@bla@S P E @bla@C I A L  A T T A C K", 7561);
			fsBar(500, 0, 7551);
			fsBar(500, 0, 7552);
			fsBar(500, 0, 7553);
			fsBar(0, 0, 7554);
			fsBar(0, 0, 7555);
			fsBar(0, 0, 7556);
			fsBar(0, 0, 7557);
			fsBar(0, 0, 7558);
			fsBar(0, 0, 7559);
			fsBar(0, 0, 7560);
		}
		if (specialAmount >= 50 && specialAmount <= 74 && usingSpecial == false)
		{
			sendFrame126("@bla@S P E C I A L@bla@  A T T A C K", 7561);
			fsBar(500, 0, 7551);
			fsBar(500, 0, 7552);
			fsBar(500, 0, 7553);
			fsBar(500, 0, 7554);
			fsBar(500, 0, 7555);
			fsBar(0, 0, 7556);
			fsBar(0, 0, 7557);
			fsBar(0, 0, 7558);
			fsBar(0, 0, 7559);
			fsBar(0, 0, 7560);
		}
		if (specialAmount >= 75 && specialAmount <= 99 && usingSpecial == false)
		{
			sendFrame126("@bla@S P E C I A L  A T T A @bla@C K", 7561);
			fsBar(500, 0, 7551);
			fsBar(500, 0, 7552);
			fsBar(500, 0, 7553);
			fsBar(500, 0, 7554);
			fsBar(500, 0, 7555);
			fsBar(500, 0, 7556);
			fsBar(500, 0, 7557);
			fsBar(500, 0, 7558);
			fsBar(0, 0, 7559);
			fsBar(0, 0, 7560);
		}
		if (specialAmount >= 100 && usingSpecial == false)
		{
			sendFrame126("@bla@S P E C I A L  A T T A C K", 7561);
			fsBar(500, 0, 7551);
			fsBar(500, 0, 7552);
			fsBar(500, 0, 7553);
			fsBar(500, 0, 7554);
			fsBar(500, 0, 7555);
			fsBar(500, 0, 7556);
			fsBar(500, 0, 7557);
			fsBar(500, 0, 7558);
			fsBar(500, 0, 7559);
			fsBar(500, 0, 7560);
		}
		if (specialAmount >= 25 && specialAmount <= 49 && usingSpecial == true)
		{
			sendFrame126("@yel@S P E @bla@C I A L  A T T A C K", 7561);
			fsBar(500, 0, 7551);
			fsBar(500, 0, 7552);
			fsBar(500, 0, 7553);
			fsBar(0, 0, 7554);
			fsBar(0, 0, 7555);
			fsBar(0, 0, 7556);
			fsBar(0, 0, 7557);
			fsBar(0, 0, 7558);
			fsBar(0, 0, 7559);
			fsBar(0, 0, 7560);
		}
		if (specialAmount >= 50 && specialAmount <= 74 && usingSpecial == true)
		{
			sendFrame126("@yel@S P E C I A L@bla@  A T T A C K", 7561);
			fsBar(500, 0, 7551);
			fsBar(500, 0, 7552);
			fsBar(500, 0, 7553);
			fsBar(500, 0, 7554);
			fsBar(500, 0, 7555);
			fsBar(0, 0, 7556);
			fsBar(0, 0, 7557);
			fsBar(0, 0, 7558);
			fsBar(0, 0, 7559);
			fsBar(0, 0, 7560);
		}
		if (specialAmount >= 75 && specialAmount <= 99 && usingSpecial == true)
		{
			sendFrame126("@yel@S P E C I A L  A T T A @bla@C K", 7561);
			fsBar(500, 0, 7551);
			fsBar(500, 0, 7552);
			fsBar(500, 0, 7553);
			fsBar(500, 0, 7554);
			fsBar(500, 0, 7555);
			fsBar(500, 0, 7556);
			fsBar(500, 0, 7557);
			fsBar(500, 0, 7558);
			fsBar(0, 0, 7559);
			fsBar(0, 0, 7560);
		}
		if (specialAmount >= 100 && usingSpecial == true)
		{
			sendFrame126("@yel@S P E C I A L  A T T A C K", 7561);
			fsBar(500, 0, 7551);
			fsBar(500, 0, 7552);
			fsBar(500, 0, 7553);
			fsBar(500, 0, 7554);
			fsBar(500, 0, 7555);
			fsBar(500, 0, 7556);
			fsBar(500, 0, 7557);
			fsBar(500, 0, 7558);
			fsBar(500, 0, 7559);
			fsBar(500, 0, 7560);
		}
	}

	public void specialAttacks5()
	{
		if (specialAmount >= 0 && specialAmount <= 24)
		{
			sendFrame126("S P E C I A L  A T T A C K", 8505);
			fsBar(0, 0, 8495);
			fsBar(0, 0, 8496);
			fsBar(0, 0, 8497);
			fsBar(0, 0, 8498);
			fsBar(0, 0, 8499);
			fsBar(0, 0, 8500);
			fsBar(0, 0, 8501);
			fsBar(0, 0, 8502);
			fsBar(0, 0, 8503);
			fsBar(0, 0, 8504);
		}
		if (specialAmount >= 25 && specialAmount <= 49 && usingSpecial == false)
		{
			sendFrame126("@bla@S P E @bla@C I A L  A T T A C K", 8505);
			fsBar(500, 0, 8495);
			fsBar(500, 0, 8496);
			fsBar(500, 0, 8497);
			fsBar(0, 0, 8498);
			fsBar(0, 0, 8499);
			fsBar(0, 0, 8500);
			fsBar(0, 0, 8501);
			fsBar(0, 0, 8502);
			fsBar(0, 0, 8503);
			fsBar(0, 0, 8504);
		}
		if (specialAmount >= 50 && specialAmount <= 74 && usingSpecial == false)
		{
			sendFrame126("@bla@S P E C I A L@bla@  A T T A C K", 8505);
			fsBar(500, 0, 8495);
			fsBar(500, 0, 8496);
			fsBar(500, 0, 8497);
			fsBar(500, 0, 8498);
			fsBar(500, 0, 8499);
			fsBar(0, 0, 8500);
			fsBar(0, 0, 8501);
			fsBar(0, 0, 8502);
			fsBar(0, 0, 8503);
			fsBar(0, 0, 8504);
		}
		if (specialAmount >= 75 && specialAmount <= 99 && usingSpecial == false)
		{
			sendFrame126("@bla@S P E C I A L  A T T A @bla@C K", 8505);
			fsBar(500, 0, 8495);
			fsBar(500, 0, 8496);
			fsBar(500, 0, 8497);
			fsBar(500, 0, 8498);
			fsBar(500, 0, 8499);
			fsBar(500, 0, 8500);
			fsBar(500, 0, 8501);
			fsBar(500, 0, 8502);
			fsBar(0, 0, 8503);
			fsBar(0, 0, 8504);
		}
		if (specialAmount >= 100 && usingSpecial == false)
		{
			sendFrame126("@bla@S P E C I A L  A T T A C K", 8505);
			fsBar(500, 0, 8495);
			fsBar(500, 0, 8496);
			fsBar(500, 0, 8497);
			fsBar(500, 0, 8498);
			fsBar(500, 0, 8499);
			fsBar(500, 0, 8500);
			fsBar(500, 0, 8501);
			fsBar(500, 0, 8502);
			fsBar(500, 0, 8503);
			fsBar(500, 0, 8504);
		}
		if (specialAmount >= 25 && specialAmount <= 49 && usingSpecial == true)
		{
			sendFrame126("@yel@S P E @bla@C I A L  A T T A C K", 8505);
			fsBar(500, 0, 8495);
			fsBar(500, 0, 8496);
			fsBar(500, 0, 8497);
			fsBar(0, 0, 8498);
			fsBar(0, 0, 8499);
			fsBar(0, 0, 8500);
			fsBar(0, 0, 8501);
			fsBar(0, 0, 8502);
			fsBar(0, 0, 8503);
			fsBar(0, 0, 8504);
		}
		if (specialAmount >= 50 && specialAmount <= 74 && usingSpecial == true)
		{
			sendFrame126("@yel@S P E C I A L@bla@  A T T A C K", 8505);
			fsBar(500, 0, 8495);
			fsBar(500, 0, 8496);
			fsBar(500, 0, 8497);
			fsBar(500, 0, 8498);
			fsBar(500, 0, 8499);
			fsBar(0, 0, 8500);
			fsBar(0, 0, 8501);
			fsBar(0, 0, 8502);
			fsBar(0, 0, 8503);
			fsBar(0, 0, 8504);
		}
		if (specialAmount >= 75 && specialAmount <= 99 && usingSpecial == true)
		{
			sendFrame126("@yel@S P E C I A L  A T T A @bla@C K", 8505);
			fsBar(500, 0, 8495);
			fsBar(500, 0, 8496);
			fsBar(500, 0, 8497);
			fsBar(500, 0, 8498);
			fsBar(500, 0, 8499);
			fsBar(500, 0, 8500);
			fsBar(500, 0, 8501);
			fsBar(500, 0, 8502);
			fsBar(0, 0, 8503);
			fsBar(0, 0, 8504);
		}
		if (specialAmount >= 100 && usingSpecial == true)
		{
			sendFrame126("@yel@S P E C I A L  A T T A C K", 8505);
			fsBar(500, 0, 8495);
			fsBar(500, 0, 8496);
			fsBar(500, 0, 8497);
			fsBar(500, 0, 8498);
			fsBar(500, 0, 8499);
			fsBar(500, 0, 8500);
			fsBar(500, 0, 8501);
			fsBar(500, 0, 8502);
			fsBar(500, 0, 8503);
			fsBar(500, 0, 8504);
		}
	}

	public void specialAttacks6()
	{
		if (specialAmount >= 0 && specialAmount <= 24)
		{
			sendFrame126("S P E C I A L  A T T A C K", 7511);
			fsBar(0, 0, 7501);
			fsBar(0, 0, 7502);
			fsBar(0, 0, 7503);
			fsBar(0, 0, 7504);
			fsBar(0, 0, 7505);
			fsBar(0, 0, 7506);
			fsBar(0, 0, 7507);
			fsBar(0, 0, 7508);
			fsBar(0, 0, 7509);
			fsBar(0, 0, 7510);
		}
		if (specialAmount >= 25 && specialAmount <= 49 && usingSpecial == false)
		{
			sendFrame126("@bla@S P E @bla@C I A L  A T T A C K", 7511);
			fsBar(500, 0, 7501);
			fsBar(500, 0, 7502);
			fsBar(500, 0, 7503);
			fsBar(0, 0, 7504);
			fsBar(0, 0, 7505);
			fsBar(0, 0, 7506);
			fsBar(0, 0, 7507);
			fsBar(0, 0, 7508);
			fsBar(0, 0, 7509);
			fsBar(0, 0, 7510);
		}
		if (specialAmount >= 50 && specialAmount <= 74 && usingSpecial == false)
		{
			sendFrame126("@bla@S P E C I A L@bla@  A T T A C K", 7511);
			fsBar(500, 0, 7501);
			fsBar(500, 0, 7502);
			fsBar(500, 0, 7503);
			fsBar(500, 0, 7504);
			fsBar(500, 0, 7505);
			fsBar(0, 0, 7506);
			fsBar(0, 0, 7507);
			fsBar(0, 0, 7508);
			fsBar(0, 0, 7509);
			fsBar(0, 0, 7510);
		}
		if (specialAmount >= 75 && specialAmount <= 99 && usingSpecial == false)
		{
			sendFrame126("@bla@S P E C I A L  A T T A @bla@C K", 7511);
			fsBar(500, 0, 7501);
			fsBar(500, 0, 7502);
			fsBar(500, 0, 7503);
			fsBar(500, 0, 7504);
			fsBar(500, 0, 7505);
			fsBar(500, 0, 7506);
			fsBar(500, 0, 7507);
			fsBar(500, 0, 7508);
			fsBar(0, 0, 7509);
			fsBar(0, 0, 7510);
		}
		if (specialAmount >= 100 && usingSpecial == false)
		{
			sendFrame126("@bla@S P E C I A L  A T T A C K", 7511);
			fsBar(500, 0, 7501);
			fsBar(500, 0, 7502);
			fsBar(500, 0, 7503);
			fsBar(500, 0, 7504);
			fsBar(500, 0, 7505);
			fsBar(500, 0, 7506);
			fsBar(500, 0, 7507);
			fsBar(500, 0, 7508);
			fsBar(500, 0, 7509);
			fsBar(500, 0, 7510);
		}
		if (specialAmount >= 25 && specialAmount <= 49 && usingSpecial == true)
		{
			sendFrame126("@yel@S P E @bla@C I A L  A T T A C K", 7511);
			fsBar(500, 0, 7501);
			fsBar(500, 0, 7502);
			fsBar(500, 0, 7503);
			fsBar(0, 0, 7504);
			fsBar(0, 0, 7505);
			fsBar(0, 0, 7506);
			fsBar(0, 0, 7507);
			fsBar(0, 0, 7508);
			fsBar(0, 0, 7509);
			fsBar(0, 0, 7510);
		}
		if (specialAmount >= 50 && specialAmount <= 74 && usingSpecial == true)
		{
			sendFrame126("@yel@S P E C I A L@bla@  A T T A C K", 7511);
			fsBar(500, 0, 7501);
			fsBar(500, 0, 7502);
			fsBar(500, 0, 7503);
			fsBar(500, 0, 7504);
			fsBar(500, 0, 7505);
			fsBar(0, 0, 7506);
			fsBar(0, 0, 7507);
			fsBar(0, 0, 7508);
			fsBar(0, 0, 7509);
			fsBar(0, 0, 7510);
		}
		if (specialAmount >= 75 && specialAmount <= 99 && usingSpecial == true)
		{
			sendFrame126("@yel@S P E C I A L  A T T A @bla@C K", 7511);
			fsBar(500, 0, 7501);
			fsBar(500, 0, 7502);
			fsBar(500, 0, 7503);
			fsBar(500, 0, 7504);
			fsBar(500, 0, 7505);
			fsBar(500, 0, 7506);
			fsBar(500, 0, 7507);
			fsBar(500, 0, 7508);
			fsBar(0, 0, 7509);
			fsBar(0, 0, 7510);
		}
		if (specialAmount >= 100 && usingSpecial == true)
		{
			sendFrame126("@yel@S P E C I A L  A T T A C K", 7511);
			fsBar(500, 0, 7501);
			fsBar(500, 0, 7502);
			fsBar(500, 0, 7503);
			fsBar(500, 0, 7504);
			fsBar(500, 0, 7505);
			fsBar(500, 0, 7506);
			fsBar(500, 0, 7507);
			fsBar(500, 0, 7508);
			fsBar(500, 0, 7509);
			fsBar(500, 0, 7510);
		}
	}

	public void calculateSpecial()
	{
		if (playerEquipment[playerWeapon] == 4153 && specialAmount <= 49)
		{ // maul
			specialDamage = 0;
			specialDamage2 = 0;
			sendMessage("You do not have enough special energy left.");
		}
		if (playerEquipment[playerWeapon] == 4153 && specialAmount >= 50)
		{ // maul
			startAnimation(1667);
			maulSpec();
			specialDamage = 30 + MethodCollection.random(10);
			specialAmount -= 50;
		}
		if (playerEquipment[playerWeapon] == 6739 && specialAmount >= 100)
		{ // d
			// axe
			startAnimation(2876);
			specialDamage = MethodCollection.random(80);
			specialAmount -= 100;
		}
		if (playerEquipment[playerWeapon] == 3204 && specialAmount <= 99)
		{ // hally
			specialDamage = 0;
			specialDamage2 = 0;
			sendMessage("You do not have enough special energy left.");
		}
		if (playerEquipment[playerWeapon] == 3204 && specialAmount >= 100)
		{ // hally
			startAnimation(1667);
			hally();
			specialDamage = 35 + MethodCollection.random(10);
			specialAmount -= 100;
		}
		if (playerEquipment[playerWeapon] == 861 && specialAmount <= 50)
		{ // bow
			startAnimation(426);
			DDZ();
			specialDamage = 20 + MethodCollection.random(10);
			specialAmount -= 75;
			sendMessage("You do not have enough special energy left.");
		}
		if (playerEquipment[playerWeapon] == 861 && specialAmount >= 100)
		{ // bow
			startAnimation(426);
			DDZ();
			specialDamage = 20 + MethodCollection.random(10);
			specialAmount -= 75;
		}
		if (playerEquipment[playerWeapon] == 15156 && specialAmount <= 49)
		{ // dark
			// bow
			specialDamage = 0;
			specialDamage2 = 0;
			sendMessage("You do not have enough special energy left.");
		}
		if (playerEquipment[playerWeapon] == 15156 && specialAmount >= 50)
		{ // dark
			// bow
			startAnimation(426);
			startAnimation(426);
			darkbow();
			specialAmount -= 50;
		}
		if (playerEquipment[playerWeapon] == 6724 && specialAmount <= 49)
		{ // seercull
			specialDamage = 0;
			specialDamage2 = 0;
			sendMessage("You do not have enough special energy left.");
		}
		if (playerEquipment[playerWeapon] == 6724 && specialAmount >= 50)
		{ // seercull
			startAnimation(426);
			startAnimation(426);
			darkbow();
			specialAmount -= 50;
		}
		if (playerEquipment[playerWeapon] == 4212 && specialAmount <= 49)
		{ // dark
			// bow
			specialDamage = 0;
			specialDamage2 = 0;
			sendMessage("You do not have enough special energy left.");
		}
		if (playerEquipment[playerWeapon] == 4212 && specialAmount >= 50)
		{ // dark
			// bow
			startAnimation(426);
			startAnimation(426);
			DDZ();
			specialAmount -= 50;
		}
		if (playerEquipment[playerWeapon] == 8002 && specialAmount <= 24)
		{ // dagger
			specialDamage = 0;
			specialDamage2 = 0;
			sendMessage("You do not have enough special energy left.");
		}
		if (playerEquipment[playerWeapon] == 8002 && specialAmount >= 25)
		{ // dagger
			ClawsSpecial();
			specialAmount -= 50;
		}
		if (playerEquipment[playerWeapon] == 13643 && specialAmount <= 24)
		{ // Stathammer
			specialDamage = 0;
			specialDamage2 = 0;
			sendMessage("You do not have enough special energy left.");
		}
		if (playerEquipment[playerWeapon] == 13643 && specialAmount >= 25)
		{ // Stathammer
			CamsSpecial();
			specialAmount -= 50;
		}
		if (playerEquipment[playerWeapon] == 5698 && specialAmount <= 24)
		{ // dagger
			specialDamage = 0;
			specialDamage2 = 0;
			sendMessage("You do not have enough special energy left.");
		}
		if (playerEquipment[playerWeapon] == 15336 && specialAmount <= 49)
		{// zamgodsword
			specialDamage = 0;
			specialDamage2 = 0;
			sendMessage("You do not have enough special energy left.");
		}
		if (playerEquipment[playerWeapon] == 15336 && specialAmount >= 50)
		{// zamgodsword
			startAnimation(2890);
			stillgfx(282, absY, absX);
			stillgfx(282, absY, absX);
			stillgfx(282, absY, absX);
			stillgfx(284, absY, absX);
			stillgfx(285, absY, absX);
			stillgfx(284, absY, absX);
			stillgfx(285, absY, absX);
			stillgfx(283, absY, absX);
			stillgfx(283, absY, absX);
			sendMessage("Everything starts to burn around you!!");
			if (MethodCollection.random(6) == 1)
			{
				specialDamage = 40 + MethodCollection.random(40);
				specialAmount -= 75;
			}
			else
			{
				specialDamage = 40 + MethodCollection.random(5);
				specialAmount -= 50;
			}
		}
		if (playerEquipment[playerWeapon] == 11593 && specialAmount <= 100)
		{ // dd
			// light
			specialDamage = 0;
			specialDamage2 = 0;
			sendMessage("You do not have enough special energy left.");
		}
		if (playerEquipment[playerWeapon] == 11593 && specialAmount <= 100)
		{ // d
			// light
			specialDamage = 0;
			specialDamage2 = 0;
			sendMessage("You do not have enough special energy left.");
		}
		if (playerEquipment[playerWeapon] == 11593 && specialAmount >= 100)
		{ // d
			// light
			DLSpecial();
			specialAmount -= 25;
		}
		if (playerEquipment[playerWeapon] == 14889 && specialAmount <= 75)
		{ // sword
			specialDamage = 0;
			specialDamage2 = 0;
			sendMessage("You do not have enough special energy left.");
		}
		if (playerEquipment[playerWeapon] == 14889 && specialAmount >= 75)
		{ // Sword
			SSSpecial();
			specialAmount -= 75;
		}
		if (playerEquipment[playerWeapon] == 15351 && specialAmount <= 75)
		{ // sword
			specialDamage = 0;
			specialDamage2 = 0;
			sendMessage("You do not have enough special energy left.");
		}
		if (playerEquipment[playerWeapon] == 5698 && specialAmount >= 25)
		{ // dagger
			DDSSpecial();
			specialAmount -= 25;
		}
		if (playerEquipment[playerWeapon] == 11593 && specialAmount >= 100)
		{ // dagger
			DLLSpecial();
			specialAmount -= 25;
		}
		if (playerEquipment[playerWeapon] == 4151 && specialAmount <= 49)
		{ // whip
			specialDamage = 0;
			specialDamage2 = 0;
			sendMessage("You do not have enough special energy left.");
		}
		if (playerEquipment[playerWeapon] == 10101 && specialAmount <= 49)
		{ // whip
			specialDamage = 0;
			specialDamage2 = 0;
			sendMessage("You do not have enough special energy left.");
		}
		if (playerEquipment[playerWeapon] == 10102 && specialAmount <= 49)
		{ // whip
			specialDamage = 0;
			specialDamage2 = 0;
			sendMessage("You do not have enough special energy left.");
		}
		if (playerEquipment[playerWeapon] == 10103 && specialAmount <= 49)
		{ // whip
			specialDamage = 0;
			specialDamage2 = 0;
			sendMessage("You do not have enough special energy left.");
		}
		if (playerEquipment[playerWeapon] == 10104 && specialAmount <= 49)
		{ // whip
			specialDamage = 0;
			specialDamage2 = 0;
			sendMessage("You do not have enough special energy left.");
		}
		if (playerEquipment[playerWeapon] == 10105 && specialAmount <= 49)
		{ // whip
			specialDamage = 0;
			specialDamage2 = 0;
			sendMessage("You do not have enough special energy left.");
		}
		if (playerEquipment[playerWeapon] == 10106 && specialAmount <= 49)
		{ // whip
			specialDamage = 0;
			specialDamage2 = 0;
			sendMessage("You do not have enough special energy left.");
		}
		if (playerEquipment[playerWeapon] == 4151 && specialAmount >= 50)
		{ // whip
			startAnimation(1658);
			specialDamage = 20 + MethodCollection.random(10);
			specialAmount -= 50;
		}
		if (playerEquipment[playerWeapon] == 10101 && specialAmount >= 50)
		{ // whip
			startAnimation(1658);
			specialDamage = 20 + MethodCollection.random(10);
			specialAmount -= 50;
		}
		if (playerEquipment[playerWeapon] == 10102 && specialAmount >= 50)
		{ // whip
			startAnimation(1658);
			specialDamage = 20 + MethodCollection.random(10);
			specialAmount -= 50;
		}
		if (playerEquipment[playerWeapon] == 10103 && specialAmount >= 50)
		{ // whip
			startAnimation(1658);
			specialDamage = 20 + MethodCollection.random(10);
			specialAmount -= 50;
		}
		if (playerEquipment[playerWeapon] == 10104 && specialAmount >= 50)
		{ // whip
			startAnimation(1658);
			specialDamage = 20 + MethodCollection.random(10);
			specialAmount -= 50;
		}
		if (playerEquipment[playerWeapon] == 10105 && specialAmount >= 50)
		{ // whip
			startAnimation(1658);
			specialDamage = 20 + MethodCollection.random(10);
			specialAmount -= 50;
		}
		if (playerEquipment[playerWeapon] == 10106 && specialAmount >= 50)
		{ // whip
			startAnimation(1658);
			specialDamage = 20 + MethodCollection.random(10);
			specialAmount -= 50;
		}
		if (playerEquipment[playerWeapon] == 1305 && specialAmount <= 49)
		{ // d
			// long
			specialDamage = 0;
			specialDamage2 = 0;
			sendMessage("You do not have enough special energy left.");
		}
		if (playerEquipment[playerWeapon] == 1305 && specialAmount >= 50)
		{ // d
			// long
			startAnimation(451);
			specialDamage = 18 + MethodCollection.random(5);
			specialAmount -= 50;
		}
		if (playerEquipment[playerWeapon] == 15334 && specialAmount <= 49)
		{ // bandosgodsword
			specialDamage = 0;
			specialDamage2 = 0;
			sendMessage("You do not have enough special energy left.");
		}
		if (playerEquipment[playerWeapon] == 15334 && specialAmount >= 50)
		{// godsword
			startAnimation(2890);
			if (MethodCollection.random(6) == 1)
			{
				specialDamage = 40 + MethodCollection.random(40);
				specialAmount -= 75;
			}
			else
			{
				specialDamage = 40 + MethodCollection.random(5);
				specialAmount -= 50;
			}
		}
		if (playerEquipment[playerWeapon] == 15336 && specialAmount >= 50)
		{ // zamgodsword
			startAnimation(1499);
			stillgfx(6, absY, absX);
			stillgfx(293, absY, absX);
			stillgfx(379, absY, absX);
			sendMessage("Everything starts to burn around you!!");
			specialDamage = 40 + MethodCollection.random(5);
			specialAmount -= 50;
		}
		if (playerEquipment[playerWeapon] == 15336 && specialAmount <= 49)
		{ // zamgodsword
			specialDamage = 0;
			specialDamage2 = 0;
			sendMessage("You do not have enough special energy left.");
		}
		if (playerEquipment[playerWeapon] == 15336 && specialAmount >= 50)
		{ // zamgodsword
			startAnimation(1499);
			stillgfx(437, absY, absX);
			stillgfx(293, absY, absX);
			stillgfx(379, absY, absX);
			sendMessage("Everything starts to burn around you!!");
			specialDamage = 40 + MethodCollection.random(5);
			specialAmount -= 50;
		}
		if (playerEquipment[playerWeapon] == 14915 && specialAmount <= 49)
		{ // Anchor
			specialDamage = 0;
			specialDamage2 = 0;
			sendMessage("You do not have enough special energy left.");
		}
		if (playerEquipment[playerWeapon] == 14915 && specialAmount >= 50)
		{ // Anchor
			startAnimation(405);
			specialDamage = 40 + MethodCollection.random(5);
			specialAmount -= 50;
		}
		if (playerEquipment[playerWeapon] == 1434 && specialAmount <= 39)
		{ // d
			// mace
			specialDamage = 0;
			specialDamage2 = 0;
			sendMessage("You do not have enough special energy left.");
		}
		if (playerEquipment[playerWeapon] == 1434 && specialAmount >= 40)
		{ // d
			// mace
			startAnimation(1060);
			specialDamage = MethodCollection.random(60);
			specialAmount -= 40;
		}
		if (playerEquipment[playerWeapon] == 7158 && specialAmount <= 39)
		{ // d2h
			specialDamage = 0;
			specialDamage2 = 0;
			sendMessage("You do not have enough special energy left.");
		}
		if (playerEquipment[playerWeapon] == 7158 && specialAmount >= 40)
		{ // d2h
			startAnimation(3157);
			specialDamage = MethodCollection.random(60);
			specialAmount -= 40;
		}
		if (playerEquipment[playerWeapon] == 4587 && specialAmount <= 74)
		{ // d
			// skim
			specialDamage = 0;
			specialDamage2 = 0;
			sendMessage("You do not have enough special energy left.");
		}
		if (playerEquipment[playerWeapon] == 35 && specialAmount <= 74)
		{ // Excalibur
			specialDamage = 0;
			specialDamage2 = 0;
			sendMessage("You do not have enough special energy left.");
		}
		if (playerEquipment[playerWeapon] == 15337 && specialAmount <= 74)
		{ // Lava
			// Blade
			specialDamage = 0;
			specialDamage2 = 0;
			sendMessage("You do not have enough special energy left.");
		}
		if (playerEquipment[playerWeapon] == 4587 && specialAmount >= 75)
		{ // d
			// skim
			startAnimation(451);
			specialDamage = 30 + MethodCollection.random(5);
			specialAmount -= 75;
		}
		if (playerEquipment[playerWeapon] == 35 && specialAmount >= 75)
		{ // Excalibur
			startAnimation(451);
			specialDamage = 70 + MethodCollection.random(40);
			specialAmount -= 75;
		}
		if (playerEquipment[playerWeapon] == 15337 && specialAmount >= 75)
		{ // Lava
			// Blade
			startAnimation(451);
			specialDamage = 30 + MethodCollection.random(5);
			specialAmount -= 75;
		}
		if (playerEquipment[playerWeapon] == 746 && specialAmount <= 20)
		{ // d
			// skim
			specialDamage = 0;
			specialDamage2 = 0;
			sendMessage("You do not have enough special energy left.");
		}
		if (playerEquipment[playerWeapon] == 746 && specialAmount >= 100)
		{ // d
			// skim
			startAnimation(451);
			specialDamage = 40 + MethodCollection.random(5);
			specialAmount -= 100;
		}
		if (playerEquipment[playerWeapon] == 4755 && specialAmount >= 100
				|| playerEquipment[playerWeapon] == 4734
				&& specialAmount >= 100
				|| playerEquipment[playerWeapon] == 4718
				&& specialAmount >= 100
				|| playerEquipment[playerWeapon] == 4726
				&& specialAmount >= 100
				|| playerEquipment[playerWeapon] == 4747
				&& specialAmount >= 100 || playerEquipment[playerWeapon] == 47
				&& specialAmount >= 100)
		{ // barrows
			startAnimation(playerSEA);
			specialDamage = 10 + MethodCollection.random(5);
			specialAmount -= 100;
		}
	}

	public int starter = 0;
	public int stealtimer;
	public int Reptimer = 0;
	public int EnemyY = 0;
	public int EnemyX = 0;

	public void defaultAppearance()
	{
		for (int i = 0; i < 5; i++)
		{
			playerLook[i] = 1;
		}
		pHead = 1;
		pBeard = 10;
		pTorso = 18;
		pArms = 26;
		pHands = 33;
		pLegs = 36;
		pFeet = 42;
		updateRequired = true;
		appearanceUpdateRequired = true;
	}

	public void process()
	{ // is being called regularily every 500ms
		if (autoKick > 0 && getKicked == true)
		{
			autoKick--;
		}
		if (autoKick == 0 && getKicked == true)
		{
			disconnected = true;
			logout();
		}
		for (int i = 0; i < 5; i++)
		{
			if (playerLook[i] < 0 || playerLook[i] > 50 || pTorso < 0
					|| pTorso > 50 || pLegs < 0 || pLegs > 50 || pArms < 0
					|| pLegs > 50 || pFeet < 0 || pFeet > 50 || pHead < 0
					|| pHead > 50)
			{
				defaultAppearance();
			}
		}
		clanChatMenu();
		scanPickup();
		if (nonWild())
		{
			outStream.createFrameVarSize(104);
			outStream.putByteC(3);
			outStream.putByteA(0);
			outStream.putString("null");
			outStream.endFrameVarSize();
		}
		else
		{
			outStream.createFrameVarSize(104);
			outStream.putByteC(3);
			outStream.putByteA(0);
			outStream.putString("Attack");
			outStream.endFrameVarSize();
		}
		if (WannePickUp == true)
		{
			PickUpID = 0;
			PickUpAmount = 0;
			PickUpDelete = 0;
			WannePickUp = false;
		}
		if ((IsAttackingNPC || IsAttacking) && DDS2Damg == true
				&& DDStimer == 0)
		{
			SpecDamgNPC(25);
			DDS2Damg = false;
		}
		if (playerEquipment[playerRing] == 773 && playerRights >= 2)
		{
			if (IsDead)
				IsDead = false;
			NewHP = playerLevel[3];
		}
		if (DDStimer > 0)
		{
			DDStimer -= 1;
		}
		/*
		 * if (SpecialDelay <= 9) { SpecDelay++; } if (SpecDelay == 50) {
		 * SpecialDelay++; SpecDelay = 0; }
		 */
		if (specialAmount <= 99 && specialDelay <= 0)
		{
			specialAmount += 1;
			specialDelay = 4;
			specialAttacks();
			specialAttacks2();
			specialAttacks3();
			specialAttacks4();
			specialAttacks5();
			specialAttacks6();
		}
		specialDelay -= 1;
		l33thax(12323);
		l33thax(7574);
		l33thax(7599);
		l33thax(7549);
		l33thax(8493);
		l33thax(7499);
		if (adglow == true)
		{// Admin Glow
			stillgfx(367, absY, absX);
		}
		if (modglow == true)
		{// PMod Glow
			stillgfx(247, absY, absX);
		}
		if (dicerglow == true)
		{// Dicer Glow
			stillgfx(383, absY, absX);
		}
		if (actionAmount < 0)
		{
			actionAmount = 0;
		}
		if (actionTimer > 0)
		{
			actionTimer -= 1;
		}
		if (actionAmount > 25)
		{
			sendMessage("Kicked for acting too fast!");
			MethodCollection.println("Client acts too fast - disconnecting it");
			disconnected = true;
		}
		if (theifTimer > 0)
		{
			theifTimer -= 1;
		}
		if (AgilityTimer > 0)
		{
			AgilityTimer -= 1;
		}
		if (WCTimer > 0)
		{
			WCTimer -= 1;
		}
		if (RCTimer > 0)
		{
			RCTimer -= 1;
		}
		if (healTimer > 0)
		{
			healTimer -= 1;
		}
		sendSafeZoneText();
		if (playerLevel[0] > getLevelForXP(playerXP[0]) && potTimer0 == 0)
		{
			playerLevel[0] -= 1;
			potTimer0 += 250;
		}
		if (potTimer0 > 0)
		{
			potTimer0 -= 1;
		}
		if (playerLevel[1] > getLevelForXP(playerXP[1]) && potTimer1 == 0)
		{
			playerLevel[1] -= 1;
			potTimer1 += 250;
		}
		if (potTimer1 > 0)
		{
			potTimer1 -= 1;
		}
		if (playerLevel[2] > getLevelForXP(playerXP[2]) && potTimer2 == 0)
		{
			playerLevel[2] -= 1;
			potTimer1 += 250;
		}
		if (potTimer2 > 0)
		{
			potTimer2 -= 1;
		}
		if (playerLevel[3] > getLevelForXP(playerXP[3]) && potTimer3 == 0)
		{
			playerLevel[3] -= 1;
			potTimer3 += 250;
		}
		if (potTimer3 > 0)
		{
			potTimer3 -= 1;
		}
		if (playerLevel[4] > getLevelForXP(playerXP[4]) && potTimer4 == 0)
		{
			playerLevel[4] -= 1;
			potTimer4 += 250;
		}
		if (potTimer4 > 0)
		{
			potTimer4 -= 1;
		}
		if (playerLevel[5] > getLevelForXP(playerXP[5]) && potTimer5 == 0)
		{
			playerLevel[5] -= 1;
			potTimer5 += 250;
		}
		if (potTimer5 > 0)
		{
			potTimer5 -= 1;
		}
		if (playerLevel[6] > getLevelForXP(playerXP[6]) && potTimer6 == 0)
		{
			playerLevel[6] -= 1;
			potTimer6 += 250;
		}
		if (potTimer6 > 0)
		{
			potTimer6 -= 1;
		}
		if (LogoutDelay > 0)
		{
			LogoutDelay -= 1;
		}
		if (EntangleDelay > 0)
		{
			EntangleDelay -= 1;
		}
		if (PkingDelay > 0)
		{
			PkingDelay -= 1;
		}
		if (LoopAttDelay > 0)
		{
			LoopAttDelay -= 1;
		}
		if (NpcAttDelay > 0)
		{
			NpcAttDelay -= 1;
		}
		if (MonsterDelay > 0)
		{
			MonsterDelay -= 1;
		}
		if (PoisonDelay > 0)
		{
			PoisonDelay -= 1;
		}
		if (resetanim > 0)
		{
			resetanim -= 1;
		}
		if (newAnimDelay > 0)
		{
			newAnimDelay -= 1;
		}
		Poison();
		if (newAnimRequired && newAnimDelay < 1)
		{
			outStream.createFrame(1);
			startAnimation(newAnim);
			newAnimRequired = false;
		}
		if (PoisonClear >= 15)
		{
			PoisonDelay = 9999999;
			sendMessage("The poison wears off...");
		}
		if (item2handed(playerEquipment[playerWeapon]) == true)
		{
			playerSE = 0x811;
			playerSEW = 0x67F;
			playerSER = 0x680;
		}
		if (playerEquipment[playerWeapon] == 4747)
		{ // Torag Hammers
			playerSEA = 0x814;
		}
		if (playerEquipment[playerWeapon] == 4151)
		{ // Whip
			playerSER = 1661;
		}
		if (playerEquipment[playerWeapon] == 10101)
		{ // Whip
			playerSER = 1661;
		}
		if (playerEquipment[playerWeapon] == 10102)
		{ // Whip
			playerSER = 1661;
		}
		if (playerEquipment[playerWeapon] == 10103)
		{ // Whip
			playerSER = 1661;
		}
		if (playerEquipment[playerWeapon] == 10104)
		{ // Whip
			playerSER = 1661;
		}
		if (playerEquipment[playerWeapon] == 10105)
		{ // Whip
			playerSER = 1661;
		}
		if (playerEquipment[playerWeapon] == 10106)
		{ // Whip
			playerSER = 1661;
		}
		if (playerEquipment[playerWeapon] == 14889
				&& !username.equalsIgnoreCase("Dokan42")
				&& !username.equalsIgnoreCase("Antarix")
				&& !username.equalsIgnoreCase("Alex"))
		{
			remove(playerEquipment[playerWeapon], 3);
			sendMessage("Item restricted until further notice.");
			PoisonPlayer();
		}
		if (playerEquipment[playerRing] == 6583)
		{ // ring of stone
			npcId = 1266;
			isNpc = true;
		}
		// else {
		// playerSE = 0x328;
		// playerSEW = 0x333;
		// }
		int oldtotal = totalz;
		totalz = (getLevelForXP(playerXP[0]) + getLevelForXP(playerXP[1])
				+ getLevelForXP(playerXP[2]) + getLevelForXP(playerXP[3])
				+ getLevelForXP(playerXP[4]) + getLevelForXP(playerXP[5])
				+ getLevelForXP(playerXP[6]) + getLevelForXP(playerXP[7])
				+ getLevelForXP(playerXP[8]) + getLevelForXP(playerXP[9])
				+ getLevelForXP(playerXP[10]) + getLevelForXP(playerXP[0])
				+ getLevelForXP(playerXP[11]) + getLevelForXP(playerXP[12])
				+ getLevelForXP(playerXP[13]) + getLevelForXP(playerXP[14])
				+ getLevelForXP(playerXP[15]) + getLevelForXP(playerXP[6])
				+ getLevelForXP(playerXP[17]) + getLevelForXP(playerXP[18])
				+ getLevelForXP(playerXP[19]) + getLevelForXP(playerXP[20]));
		if (oldtotal != totalz)
		{
			if (stoprunning)
			{
				setconfig(173, 0);
				isRunning2 = false;
				stoprunning = false;
			}
		}
		if (firingspell == true)
		{
		}
		graphicstimer -= 1;
		teletimer -= 1;
		mageTimer -= 1;
		if (teleport == true && teletimer >= 0)
		{
			teleportToX = absX;
			teleportToY = absY;
		}
		if (teleport == true && teletimer <= 0)
		{
			setAnimation(715);
			teleportToX = teleX;
			teleportToY = teleY;
			heightLevel = newheightLevel;
			teleport = false;
			teleX = 0;
			teleY = 0;
			newheightLevel = 0;
		}
		/*
		 * noprayer();
		 * 
		 * PrayerTimer -= 1;
		 * 
		 * if (DrainPray == true && PrayerTimer <= 1 && playerLevel[5] > 0) {
		 * PrayerTimer = PrayerDrain; playerLevel[playerPrayer]--;
		 * currentpray--; refreshSkills(); updateRequired = true; } if
		 * (DrainPray == true && playerLevel[5] == 0) { PrayerTimer = 0;
		 * DrainPray = false; ResetProtPrayers();
		 * sendMessage("You have run out of prayer points!"); }
		 */
		if (dropsitem == true && playerEquipment[playerWeapon] > 0)
		{
			remove(playerEquipment[playerWeapon], 3);
			sendMessage("The Chaos Elemental removes your weapon!");
			PoisonPlayer();
			dropsitem = false;
		}
		if (sbloop == true)
		{
			if (sbtimer <= 1 && sbscan == false)
			{
				setSidebarInterface(7, sb);
				sb += 1;
				sbtimer = 6;
				sendMessage("Current interface: " + sb);
			}
			if (sbtimer <= 1 && sbscan == true)
			{
				setSidebarInterface(7, sb);
				sb += 1;
				sbtimer = 2;
				sendMessage("Current interface: " + sb);
			}
			sbtimer -= 1;
		}
		if (resetanim <= 0)
		{
			// meh, logic
		}
		appendPos();
		smitimer -= 1;
		if (smitimer <= 1)
		{
			directSaveGame();
		}

		playerGameCount++;
		if (playerGameCount == 120000)
		{ // evry minute
			playerGameTime++;
			playerGameCount = 0;
		}
		if ((playerGameTime % 1440) == 0)
		{ // 1440 minutes = 24 hours
			for (int i = 0; i < bankItems.length; i++)
			{
				if (bankItems[i] == 995)
				{
					bankItemsN[i] += ((bankItemsN[i] / 100) * 2);
				}
			}
		}
		// Shop
		if (UpdateShop == true)
		{
			resetItems(3823);
			resetShop(MyShopID);
		}
		if (tradeRequest > 0 && PlayerHandler.players[tradeRequest] != null)
		{
			sendMessage(PlayerHandler.players[tradeRequest].username
					+ ":tradereq:");
			tradeRequest = 0;
		}
		if (tradeOtherDeclined == true)
		{
			if (PlayerHandler.players[tradeWith] != null)
			{
				sendMessage(PlayerHandler.players[tradeWith].username
						+ " declined the trade.");
			}
			else
			{
				sendMessage("Other player declined the trade.");
			}
			RemoveAllWindows();
			DeclineTrade();
			tradeOtherDeclined = false;
		}
		if (tradeWaitingTime > 0)
		{
			tradeWaitingTime--;
			if (tradeWaitingTime <= 0)
			{
				sendMessage("Trade request suspended.");
				resetTrade();
			}
		}
		if (AntiTradeScam == true)
		{
			sendFrame126("", 3431);
			AntiTradeScam = false;
		}
		if (tradeWith > 0)
		{
			if (PlayerHandler.players[tradeWith] != null)
			{
				if (tradeStatus == 5)
				{
					if (PlayerHandler.players[tradeWith].TradeConfirmed == true)
					{
						PlayerHandler.players[tradeWith].tradeStatus = 5;
					}
					resetTrade();
				}
				else
				{
					int OtherStatus = PlayerHandler.players[tradeWith].tradeStatus;
					if (OtherStatus == 1)
					{
						PlayerHandler.players[tradeWith].tradeStatus = 2;
						tradeStatus = 2;
						AcceptTrade();
						PlayerHandler.players[tradeWith].tradeWaitingTime = 0;
						tradeWaitingTime = 0;
					}
					else if (OtherStatus == 3)
					{
						if (tradeStatus == 2)
						{
							sendFrame126("Other player has accepted.", 3431);
						}
						else if (tradeStatus == 3)
						{
							TradeGoConfirm();
						}
					}
					else if (OtherStatus == 4)
					{
						if (tradeStatus == 3)
						{
							sendFrame126("Other player has accepted.", 3535);
						}
						else if (tradeStatus == 4)
						{
							ConfirmTrade();
							if (PlayerHandler.players[tradeWith].TradeConfirmed == true)
							{
								PlayerHandler.players[tradeWith].tradeStatus = 5;
							}
						}
					}
					if (tradeUpdateOther == true)
					{
						resetOTItems(3416);
						tradeUpdateOther = false;
					}
				}
			}
			else
			{
				resetTrade();
			}
		}
		if (WanneTrade == 1)
		{
			if (WanneTradeWith > PlayerHandler.maxPlayers)
			{
				resetTrade();
			}
			else if (PlayerHandler.players[WanneTradeWith] != null)
			{
				if (GoodDistance2(absX, absY,
						PlayerHandler.players[WanneTradeWith].absX,
						PlayerHandler.players[WanneTradeWith].absY, 1) == true)
				{
					int tt1 = PlayerHandler.players[WanneTradeWith].tradeStatus;
					int tt2 = tradeStatus;
					if (tt1 <= 0
							&& tt2 <= 0
							&& PlayerHandler.players[WanneTradeWith].tradeWaitingTime == 0)
					{
						tradeWith = WanneTradeWith;
						tradeWaitingTime = 40;
						PlayerHandler.players[tradeWith].tradeRequest = playerId;
						sendMessage("Sending trade request...");
					}
					else if (tt1 <= 0
							&& tt2 <= 0
							&& PlayerHandler.players[WanneTradeWith].tradeWaitingTime > 0)
					{
						tradeWith = WanneTradeWith;
						tradeStatus = 1;
						AcceptTrade();
					}
					WanneTrade = 0;
					WanneTradeWith = 0;
				}
			}
			else
			{
				resetTrade();
			}
		}
		if (WanneTrade == 2)
		{
			try
			{
				if (WanneTradeWith > PlayerHandler.maxPlayers)
				{
					resetTrade();
				}
				else if (PlayerHandler.players[WanneTradeWith] != null)
				{
					if (GoodDistance2(absX, absY,
							PlayerHandler.players[WanneTradeWith].absX,
							PlayerHandler.players[WanneTradeWith].absY, 1) == true)
					{
						if (PlayerHandler.players[WanneTradeWith].tradeWith == playerId
								&& PlayerHandler.players[WanneTradeWith].tradeWaitingTime > 0)
						{
							tradeWith = WanneTradeWith;
							tradeStatus = 1;
							AcceptTrade();
						}
						else
						{
							tradeWith = WanneTradeWith;
							tradeWaitingTime = 40;
							PlayerHandler.players[tradeWith].tradeRequest = playerId;
							sendMessage("Sending trade request...");
						}
						WanneTrade = 0;
						WanneTradeWith = 0;
					}
				}
				else
				{
					resetTrade();
				}
			}
			catch (Exception e)
			{
				sendMessage("Ok Then... Your Nice.");
			}
		}
		// Crackers
		if (CrackerMsg == true)
		{
			crackCracker();
		}
		// check stairs
		if (stairs > 0)
		{
			if (GoodDistance(skillX, skillY, absX, absY, stairDistance) == true)
			{
				stairs(stairs, absX, absY);
			}
		}
		// objects
		if (doors > -1)
		{
			if (GoodDistance2(skillX, skillY, absX, absY, 1) == true)
			{
				ChangeDoor(doors);
				doors = -1;
			}
		}
		// check banking
		if (WanneBank > 0)
		{
			if (GoodDistance(skillX, skillY, absX, absY, WanneBank) == true)
			{
				openUpBank();
				WanneBank = 0;
			}
		}
		// god book check
		// check shopping
		if (WanneShop > 0)
		{
			if (GoodDistance(skillX, skillY, absX, absY, 1) == true)
			{
				openUpShop(WanneShop);
				WanneShop = 0;
			}
		}
		// woodcutting check
		if (IsWcing)
		{
			if (GoodDistance(TreeX, TreeY, absX, absY, 2) == true)
			{
				WC();
			}
		}
		// Pick Up Item Check
		if (WannePickUp == true && IsUsingSkill == false)
		{
			if (pickUpItem(PickUpID, PickUpAmount) == true)
			{
				PickUpID = 0;
				PickUpAmount = 0;
				PickUpDelete = 0;
				WannePickUp = false;
			}
		}
		// Attacking in wilderness
		if (IsAttacking == true && IsDead == false && PkingDelay <= 0)
		{
			if (PlayerHandler.players[AttackingOn] != null)
			{
				if (PlayerHandler.players[AttackingOn].IsDead == false)
				{
					Attack();
				}
				else
				{
					ResetAttack();
				}
			}
			else
			{
				ResetAttack();
			}
		}
		// Attacking an NPC
		if (IsAttackingNPC == true && IsDead == false)
		{
			if (NPCHandler.npcs[attacknpc] != null)
			{
				if (NPCHandler.npcs[attacknpc].IsDead == false)
				{
					AttackNPC();
				}
				else
				{
					ResetAttackNPC();
				}
			}
			else
			{
				ResetAttackNPC();
			}
		}
		// check if ring of life ie equiped etc...
		if (playerEquipment[playerRing] == 2570
				&& playerLevel[HITPOINTS] <= (int) ((double) ((double) getLevelForXP(playerXP[3]) / 10.0) + 0.5))
		{
			SafeMyLife = true;
		}
		// if ring of life is activated
		if (SafeMyLife == true)
		{
			ApplyRingOfLife();
		}
		// If killed apply dead
		if ((absX >= 3241 && absX <= 3255 && absY >= 9354 && absY <= 9360
				&& IsDead == true && NewHP <= 1))
		{
			IsDead = false;
			playerLevel[3] = getLevelForXP(playerXP[3]);
			currentHealth = playerLevel[3];
			NewHP = playerLevel[3];
			refreshSkills();
			updateRequired = true;
			appearanceUpdateRequired = true;
			teleportToX = 2633;
			teleportToY = 4687;
			sendMessage("You got pwned! :)");
		}
		if (IsDead == true && NewHP <= 1)
		{
			ApplyDead();
		}
		// update correct hp in stat screen
		if (NewHP < 201)
		{
			playerLevel[HITPOINTS] = NewHP;
			setSkillLevel(HITPOINTS, NewHP, playerXP[HITPOINTS]);
			NewHP = playerLevel[3];
		}
		// fletching check
		if (fletching[0] > 0)
		{
			fletching();
		}
		// mining check
		if (mining[0] > 0)
		{
			if (GoodDistance(skillX, skillY, absX, absY, 1) == true)
			{
				mining();
			}
		}
		// smelting check
		if (smelting[0] > 0)
		{
			if (GoodDistance(skillX, skillY, absX, absY, 2) == true)
			{
				smelting();
			}
		}
		// smithing check
		if (smithing[0] > 0)
		{
			if (GoodDistance(skillX, skillY, absX, absY, 1) == true)
			{
				smithing();
			}
		}
		// crafting check
		if (crafting[0] > 0)
		{
			crafting();
		}
		// prayer check
		if (prayer[0] > 0)
		{
			prayer();
		}
		// cooking check
		if (cooking[0] > 0)
		{
			if (GoodDistance(skillX, skillY, absX, absY, 1) == true)
			{
				cooking();
			}
		}
		// healing check
		if (healing[0] > 0)
		{
			healing();
		}
		// fishing check
		if (fishing[0] > 0)
		{
			if (GoodDistance2(skillX, skillY, absX, absY, 1) == true)
			{
				fishing();
			}
		}
		// Snowing
		if (nonWild())
		{
			if (IsSnowing == 1)
			{
				snowFilter = true;
				outStream.createFrame(208);
				outStream.putWordBigEndian_dup(11877);
				IsSnowing = 1;
			}
			else if (IsSnowing == 4)
			{
				dizzyFilter = true;
				outStream.createFrame(208);
				outStream.putWordBigEndian_dup(4504);
				IsSnowing = 4;
			}
			else if (IsSnowing == 5)
			{
				dustFilter = true;
				outStream.createFrame(208);
				outStream.putWordBigEndian_dup(13103);
				IsSnowing = 5;
			}
			else if (IsSnowing == 6)
			{
				afternoonFilter = true;
				outStream.createFrame(208);
				outStream.putWordBigEndian_dup(12416);
				IsSnowing = 6;
			}
			else if (IsSnowing == 7)
			{
				eveningFilter = true;
				outStream.createFrame(208);
				outStream.putWordBigEndian_dup(12418);
				IsSnowing = 7;
			}
			else if (IsSnowing == 8)
			{
				nightFilter = true;
				outStream.createFrame(208);
				outStream.putWordBigEndian_dup(12414);
				IsSnowing = 8;
			}
			else if (IsSnowing == 3)
			{
				resetFilters = true;
				outStream.createFrame(208);
				outStream.putWordBigEndian_dup(65535);
				IsSnowing = 0;
			}
			if (resetFilters)
			{
				snowFilter = false;
				dustFilter = false;
				dizzyFilter = false;
				afternoonFilter = false;
				eveningFilter = false;
				nightFilter = false;
				resetFilters = false;
			}
			else
			{
				if (snowFilter)
				{
					dustFilter = false;
					dizzyFilter = false;
					afternoonFilter = false;
					eveningFilter = false;
					nightFilter = false;
				}
				if (dustFilter)
				{
					snowFilter = false;
					dizzyFilter = false;
					afternoonFilter = false;
					eveningFilter = false;
					nightFilter = false;
				}
				if (dizzyFilter)
				{
					snowFilter = false;
					dustFilter = false;
					afternoonFilter = false;
					eveningFilter = false;
					nightFilter = false;
				}
				if (afternoonFilter)
				{
					snowFilter = false;
					dustFilter = false;
					dizzyFilter = false;
					eveningFilter = false;
					nightFilter = false;
				}
				if (eveningFilter)
				{
					snowFilter = false;
					dustFilter = false;
					dizzyFilter = false;
					afternoonFilter = false;
					nightFilter = false;
				}
				if (nightFilter)
				{
					snowFilter = false;
					dustFilter = false;
					dizzyFilter = false;
					eveningFilter = false;
					afternoonFilter = false;
				}
			}
		}
		// Npc Talking
		if (NpcWanneTalk == 2)
		{ // bank boothh
			if (GoodDistance2(absX, absY, skillX, skillY, 1) == true)
			{
				NpcDialogue = 1;
				NpcTalkTo = GetNPCID(skillX, (skillY - 1));
				NpcWanneTalk = 0;
			}
		}
		else if (NpcWanneTalk > 0)
		{
			if (GoodDistance2(absX, absY, skillX, skillY, 2) == true)
			{
				NpcDialogue = NpcWanneTalk;
				NpcTalkTo = GetNPCID(skillX, skillY);
				NpcWanneTalk = 0;
			}
		}
		if (NpcWanneTalk == 9292)
		{ // Random Event
			if (GoodDistance2(absX, absY, skillX, skillY, 1) == true)
			{
				NpcDialogue = 9292;
				NpcTalkTo = GetNPCID(skillX, (skillY - 1));
				NpcWanneTalk = 0;
			}
		}
		// firemaking check
		if (firemaking[0] > 0)
		{
			if (GoodDistance(skillX, skillY, absX, absY, 0) == true)
			{
				firemaking();
			}
		}
		// Walking to object check
		if (WalkingTo)
		{
			if (GoodDistance(absX, absY, destinationX, destinationY,
					destinationRange))
			{
				DoAction();
				ResetWalkTo();
			}
		}
		if (NpcDialogue > 0 && NpcDialogueSend == false)
		{
			UpdateNPCChat();
		}
		if (isKicked)
		{
			disconnected = true;
			outStream.createFrame(109);
		}
		;
		if (globalMessage.length() > 0)
		{
			sendMessage(globalMessage);
			globalMessage = "";
		}
	}

	public boolean packetSending()
	{
		return packetProcess();
	}

	private boolean packetProcess()
	{
		if (disconnected)
		{
			return false;
		}
		try
		{
			if (timeOutCounter++ > 20)
			{
				actionReset();
				disconnected = true;
				return false;
			}
			if (in == null)
			{
				return false;
			}
			int avail = in.available();
			if (avail == 0)
			{
				return false;
			}
			if (packetId == -1)
			{
				packetId = in.read() & 0xff;
				if (inStreamDecryption != null)
				{
					packetId = packetId - inStreamDecryption.nextInt() & 0xff;
				}
				packetSize = packetSizes[packetId];
				avail--;
			}
			if (packetSize == -1)
			{
				if (avail > 0)
				{
					packetSize = in.read() & 0xff;
					avail--;
				}
				else
				{
					return false;
				}
			}
			if (avail < packetSize)
			{
				return false;
			}
			fillInStream(packetSize);
			timeOutCounter = 0;
			parseIncomingPackets();
			packetId = -1;
		}
		catch (java.lang.Exception __ex)
		{
			__ex.printStackTrace();
			disconnected = true;
			System.out.println("[Server] Fatal Exception.");
		}
		return true;
	}

	public boolean pickUpItem(int item, int amount)
	{
		if (!Item.itemStackable[item] || amount < 1)
		{
			amount = 1;
		}
		if (freeSlots() > 0 && poimiY == currentY && poimiX == currentX) // actionAmount++;
		// if (actionTimer == 0)
		{
			for (int i = 0; i < playerItems.length; i++)
			{
				if (playerItems[i] == (item + 1) && Item.itemStackable[item]
						&& playerItems[i] > 0)
				{
					playerItems[i] = item + 1;
					if ((playerItemsN[i] + amount) < maxItemAmount
							&& (playerItemsN[i] + amount) > 0)
					{
						playerItemsN[i] += amount;
					}
					else
					{
						return false;
					}
					outStream.createFrameVarSizeWord(34);
					outStream.putShort(3214);
					outStream.put(i);
					outStream.putShort(playerItems[i]);
					if (playerItemsN[i] > 254)
					{
						outStream.put(255);
						outStream.putInt(playerItemsN[i]);
					}
					else
					{
						outStream.put(playerItemsN[i]); // amount
					}
					outStream.endFrameVarSizeWord();
					i = 30;
					return true;
				}
			}
			for (int i = 0; i < playerItems.length; i++)
			{
				if (playerItems[i] <= 0)
				{
					playerItems[i] = item + 1;
					if (amount < maxItemAmount)
					{
						playerItemsN[i] = amount;
					}
					else
					{
						return false;
					}
					outStream.createFrameVarSizeWord(34);
					outStream.putShort(3214);
					outStream.put(i);
					outStream.putShort(playerItems[i]);
					if (playerItemsN[i] > 254)
					{
						outStream.put(255);
						outStream.putDWord_v2(playerItemsN[i]);
					}
					else
					{
						outStream.put(playerItemsN[i]); // amount
					}
					outStream.endFrameVarSizeWord();
					i = 30;
					return true;
				}
			}
			return true;
		}
		else
		{
			return false;
		}
	}

	public void actionReset()
	{
		actionName = "";
	}

	@SuppressWarnings("unused")
	public void parseIncomingPackets()
	{
		PacketStation.parsePacket(this, packetId, packetSize);

		int i;
		int junk;
		int junk2;
		int junk3;
		switch (packetId)
		{
		case 75: // Alternative Item Option 1
			int itemid = inStream.getSignedWordA();

			int item2ID = inStream.getSignedWordBigEndian();
			int item2ID3 = inStream.getSignedWordA();
			int item2ID4 = inStream.getUnsignedShort();
			System.out.println("Item2ID: " + item2ID);
			System.out.println("Item2ID3: " + item2ID3);
			System.out.println("Item2ID4: " + item2ID4);

			if (item2ID3 == 4079)
			{
				startAnimation(1460);
			}
			if (item2ID3 == 1712)
			{
				if (teleblock)
				{
					sendMessage("You are currently teleblocked and cannot teleport");
				}
				else if (!teleblock)
				{
					teleportToX = 2852;
					teleportToY = 3863;
					heightLevel = 0;
					sendMessage("Home, sweet home");
				}
			}
			if (item2ID3 == 2552 || item2ID3 == 2554 || item2ID3 == 2556
					|| item2ID3 == 2558 || item2ID3 == 2560 || item2ID3 == 2562
					|| item2ID3 == 2564)
			{
				if (teleblock)
				{
					sendMessage("You are currently teleblocked and cannot teleport");
				}
				else if (!teleblock)
				{
					duelring = true;
					selectoption(
							"Where would you like to go?",
							"@whi@J@gre@a@whi@d",
							"@whi@R@gre@u@whi@n@gre@e@whi@c@gre@r@whi@a@gre@f@whi@t",
							"");
				}
			}
			break;
		case 192:
			int actionButton2 = MethodCollection.HexToInt(inStream.buffer, 0,
					packetSize);
			int shark = MethodCollection.HexToInt(inStream.buffer, 0,
					packetSize);
			int lob = MethodCollection.HexToInt(inStream.buffer, 0, packetSize);
			int carb = MethodCollection
					.HexToInt(inStream.buffer, 0, packetSize);
			int smelt = MethodCollection.HexToInt(inStream.buffer, 0,
					packetSize);
			int cow = MethodCollection.HexToInt(inStream.buffer, 0, packetSize);
			int turtle = MethodCollection.HexToInt(inStream.buffer, 0,
					packetSize);
			int manta = MethodCollection.HexToInt(inStream.buffer, 0,
					packetSize);
			int j6 = inStream.getUnsignedWordA();
			int atObjectID = inStream.getSignedWordBigEndian();
			int atObjectY = inStream.getUnsignedWordBigEndianA();
			int itemSlot = inStream.getUnsignedWordBigEndian();
			int atObjectX = inStream.getUnsignedWordBigEndianA();
			int useItemID = inStream.getUnsignedShort();

			if (useItemID == 4151 && atObjectID == 1531)
			{
				sendMessage("Seems Legit.");
				if (foundz[2] == 0)
				{
					sendMessage("Hidden found");
					sendMessage("You gain a hidden point!");
					hiddenPoints += 1;
					foundz[2] = 1;
				}
			}
			if (shark == 12609)
			{
				if (playerLevel[10] <= 70)
				{
					sendMessage("You need to be lvl 70 fishing to catch shark.");
				}
				else
				{
					make();
				}
			}
			else if (lob == 12599)
			{
				if (playerLevel[10] <= 89)
				{
					sendMessage("You need to be lvl 89 fishing to catch lobster.");
				}
				else
				{
					lob();
				}
			}
			else if (carb == 12603)
			{
				carb();
			}
			else if (turtle == 12706)
			{
				if (playerLevel[10] <= 98)
				{
					sendMessage("You need to be lvl 99 fishing to catch sea turtle.");
				}
				else
				{
					turtle();
				}
			}
			else if (manta == 12716)
			{
				if (playerLevel[10] <= 180)
				{
					sendMessage("You need to be lvl 180 fishing to catch manta rays.");
				}
				else
				{
					manta();
				}
			}
			else if (useItemID == 436
					&& (atObjectID == 2781 || atObjectID == 11666 || atObjectID == 9390))
			{
				tin();
			}
			else if (useItemID == 438
					&& (atObjectID == 2781 || atObjectID == 11666 || atObjectID == 9390))
			{
				Copper();
			}
			else if (useItemID == 293
					&& (atObjectX == 3028 && atObjectY == 3356))
			{
				if (absY == 3355)
				{
					teleportToX = absX;
					teleportToY = (absY + 2);
				}
				else
				{
					teleportToX = 3028;
					teleportToY = 3355;
				}
			}
			if (useItemID == 293 && (atObjectX == 3268 && atObjectY == 3435))
			{
				teleportToX = 3255;
				teleportToY = 9581;
				sendMessage("No turning back now");
			}
			else if (useItemID == 440
					&& (atObjectID == 2781 || atObjectID == 11666 || atObjectID == 9390))
			{
				iron();
			}
			else if (useItemID == 442
					&& (atObjectID == 2781 || atObjectID == 11666 || atObjectID == 9390))
			{
				silver2();
			}
			else if (useItemID == 444
					&& (atObjectID == 2781 || atObjectID == 11666 || atObjectID == 9390))
			{
				gold();
			}
			else if (useItemID == 601 && atObjectID == 4126 && actionTimer == 0) // reward
			{
				actionTimer = 15;
				sendMessage("Well done, you have just finished your mini game, here's your reward :)");
				addSkillXP((300000 * playerLevel[6]), 6);
				deleteItem(601, getItemSlot(601), 1);
				addItem(995, 50000000);
				teleportToX = 2845;
				teleportToY = 4832;
			}
			else if (useItemID == 758 && atObjectID == 4126) // reward
			{
				sendMessage("Well done, you have just finished your mini game, here's your reward :)");
				addSkillXP((300000 * playerLevel[6]), 6);
				deleteItem(758, getItemSlot(758), 1);
				addItem(15334, 1);
				teleportToX = 2845;
				teleportToY = 4832;
			}
			else if (useItemID == 788 && atObjectID == 4126) // reward
			{
				sendMessage("Well done, you have just finished your mini game, here's your reward :)");
				addSkillXP((300000 * playerLevel[6]), 6);
				deleteItem(788, getItemSlot(788), 1);
				addItem(995, 40000000);
				teleportToX = 2845;
				teleportToY = 4832;
			}
			else if (useItemID == 983 && atObjectID == 4126) // reward
			{
				sendMessage("Well done, you have just finished your mini game, here's your reward :)");
				addSkillXP((300000 * playerLevel[6]), 6);
				deleteItem(983, getItemSlot(983), 1);
				addItem(995, 30000000);
				teleportToX = 2845;
				teleportToY = 4832;
			}
			else if (useItemID == 6104 && atObjectID == 4121) // reward
			{
				sendMessage("Well done, you have just finished your mini game, here's your reward :)");
				addSkillXP((200000 * playerLevel[2]), 2);
				deleteItem(6104, getItemSlot(6104), 1);
				addItem(7400, 1);
				teleportToX = 2787;
				teleportToY = 2786;
			}
			else if (useItemID == 989 && atObjectID == 4121) // reward
			{
				sendMessage("Well done, you have just finished your mini game, here's your reward :)");
				addSkillXP((200000 * playerLevel[2]), 2);
				deleteItem(989, getItemSlot(989), 1);
				addItem(7399, 1);
				teleportToX = 2787;
				teleportToY = 2786;
			}
			else if (useItemID == 991 && atObjectID == 4121) // reward
			{
				sendMessage("Well done, you have just finished your mini game, here's your reward :)");
				addSkillXP((200000 * playerLevel[0]), 0);
				deleteItem(6104, getItemSlot(6104), 1);
				addItem(7398, 1);
				teleportToX = 2525;
				teleportToY = 4777;
			}
			else if (useItemID == 1543 && atObjectID == 4119) // reward
			{
				sendMessage("Well done, you have just finished your mini game, here's your reward :)");
				addSkillXP((0 * playerLevel[0]), 0);
				deleteItem(1543, getItemSlot(1543), 1);
				addItem(14643, 1);
				teleportToX = 2525;
				teleportToY = 4777;
			}
			else if (useItemID == 1546 && atObjectID == 4119) // reward
			{
				sendMessage("Well done, you have just finished your mini game, here's your reward :)");
				addSkillXP((0 * playerLevel[0]), 0);
				deleteItem(1546, getItemSlot(1546), 1);
				addItem(14645, 1);
				teleportToX = 2525;
				teleportToY = 4777;
			}
			else if (useItemID == 1545 && atObjectID == 4119) // reward
			{
				sendMessage("Well done, you have just finished your mini game, here's your reward :)");
				addSkillXP((0 * playerLevel[0]), 0);
				deleteItem(1545, getItemSlot(1545), 1);
				addItem(14644, 1);
				teleportToX = 2525;
				teleportToY = 4777;
			}
			else if (useItemID == 1548 && atObjectID == 4119) // reward
			{
				sendMessage("Well done, you have just finished your mini game, here's your reward :)");
				addSkillXP((0 * playerLevel[0]), 0);
				deleteItem(1548, getItemSlot(1548), 1);
				addItem(14646, 1);
				teleportToX = 2525;
				teleportToY = 4777;
			}
			else if (useItemID == 447
					&& (atObjectID == 2781 || atObjectID == 11666 || atObjectID == 9390))
			{
				mith();
			}
			else if (useItemID == 449
					&& (atObjectID == 2781 || atObjectID == 11666 || atObjectID == 9390))
			{
				adam();
			}
			else if (useItemID == 451
					&& (atObjectID == 2781 || atObjectID == 11666 || atObjectID == 9390))
			{
				rune();
			}
			else if (useItemID == 2349 && atObjectID == 2783) // bronze
			{
				initSmithing(2349);
				flushOutStream();
			}
			else if (useItemID == 2351 && atObjectID == 2783) // iron
			{
				initSmithing(2351);
				flushOutStream();
			}
			else if (useItemID == 2353 && atObjectID == 2783) // steel
			{
				initSmithing(2353);
				flushOutStream();
			}
			else if (useItemID == 2359 && atObjectID == 2783) // mith
			{
				initSmithing(2359);
				flushOutStream();
			}
			else if (useItemID == 2361 && atObjectID == 2783) // addy
			{
				initSmithing(2361);
				flushOutStream();
			}
			else if (useItemID == 2363 && atObjectID == 2783) // rune
			{
				initSmithing(2363);
				flushOutStream();
			}
			else if (useItemID == 5291
					&& (atObjectID == 8151 || atObjectID == 7848
							|| atObjectID == 8553 || atObjectID == 8552))
			{
				guamSeed();
			}
			else if (useItemID == 5292
					&& (atObjectID == 8151 || atObjectID == 7848
							|| atObjectID == 8553 || atObjectID == 8552))
			{
				marrentillSeed();
			}
			else if (useItemID == 5293
					&& (atObjectID == 8151 || atObjectID == 7848
							|| atObjectID == 8553 || atObjectID == 8552))
			{
				tarrominSeed();
			}
			else if (useItemID == 5294
					&& (atObjectID == 8151 || atObjectID == 7848
							|| atObjectID == 8553 || atObjectID == 8552))
			{
				harrlanderSeed();
			}
			else if (useItemID == 5295
					&& (atObjectID == 8151 || atObjectID == 7848
							|| atObjectID == 8553 || atObjectID == 8552))
			{
				ranarrSeed();
			}
			else if (useItemID == 5296
					&& (atObjectID == 8151 || atObjectID == 7848
							|| atObjectID == 8553 || atObjectID == 8552))
			{
				toadflaxSeed();
			}
			else if (useItemID == 5297
					&& (atObjectID == 8151 || atObjectID == 7848
							|| atObjectID == 8553 || atObjectID == 8552))
			{
				iritSeed();
			}
			else if (useItemID == 5298
					&& (atObjectID == 8151 || atObjectID == 7848
							|| atObjectID == 8553 || atObjectID == 8552))
			{
				avantoeSeed();
			}
			else if (useItemID == 5299
					&& (atObjectID == 8151 || atObjectID == 7848
							|| atObjectID == 8553 || atObjectID == 8552))
			{
				kwuarmSeed();
			}
			else if (useItemID == 5300
					&& (atObjectID == 8151 || atObjectID == 7848
							|| atObjectID == 8553 || atObjectID == 8552))
			{
				snapdragonSeed();
			}
			else if (useItemID == 5301
					&& (atObjectID == 8151 || atObjectID == 7848
							|| atObjectID == 8553 || atObjectID == 8552))
			{
				cadantineSeed();
			}
			else if (useItemID == 5302
					&& (atObjectID == 8151 || atObjectID == 7848
							|| atObjectID == 8553 || atObjectID == 8552))
			{
				lantadymeSeed();
			}
			else if (useItemID == 5303
					&& (atObjectID == 8151 || atObjectID == 7848
							|| atObjectID == 8553 || atObjectID == 8552))
			{
				dwarfSeed();
			}
			else if (useItemID == 5304
					&& (atObjectID == 8151 || atObjectID == 7848
							|| atObjectID == 8553 || atObjectID == 8552))
			{
				torstolSeed();
			} // end of farming
			else if (useItemID == 317 && atObjectID == 4172) // cooking shrimp
			{
				setAnimation(883);
				deleteItem(317, getItemSlot(317), 1);
				addItem(315, 1);
				addSkillXP(100 * playerLevel[7], 7);
				sendMessage("You cook a shrimp");
			}
			else if (useItemID == 377 && atObjectID == 4172) // cooking lobs
			{
				setAnimation(883);
				deleteItem(377, getItemSlot(377), 1);
				addItem(379, 1);
				addSkillXP(150 * playerLevel[7], 7);
				sendMessage("You cook a lobster");
			}
			else if (useItemID == 389 && atObjectID == 4172) // cooking mantas
			{
				setAnimation(883);
				deleteItem(389, getItemSlot(389), 1);
				addItem(391, 1);
				addSkillXP(1000 * playerLevel[7], 7);
				sendMessage("You cook a manta ray.");
			}
			else if (useItemID == 383 && atObjectID == 4172) // cooking shark
			{
				setAnimation(883);
				deleteItem(383, getItemSlot(383), 1);
				addItem(385, 1);
				addSkillXP(700 * playerLevel[7], 7);
				sendMessage("You cook a shark");
			}
			else if (useItemID == 389 && atObjectID == 4172) // cooking manta
			{
				setAnimation(883);
				deleteItem(389, getItemSlot(389), 1);
				addItem(391, 1);
				addSkillXP(1000 * playerLevel[7], 7);
				sendMessage("You cook a manta ray");
			}
			else if (useItemID == 4834 && atObjectID == 5284) // Bone grinding
			{
				sendMessage("You grind the " + getItemName(4834)
						+ " and recieve prayer xp.");
				addSkillXP((1200 * playerLevel[5]), 5);
				deleteItem(4834, getItemSlot(4834), 1);
			}
			else if (useItemID == 4832 && atObjectID == 5284) // Bone grinding
			{
				sendMessage("You grind the " + getItemName(4832)
						+ " and recieve prayer xp.");
				addSkillXP((950 * playerLevel[5]), 5);
				deleteItem(4832, getItemSlot(4832), 1);
			}
			else if (useItemID == 4830 && atObjectID == 5284) // Bone grinding
			{
				sendMessage("You grind the " + getItemName(4830)
						+ " and recieve prayer xp.");
				addSkillXP((900 * playerLevel[5]), 5);
				deleteItem(4830, getItemSlot(4830), 1);
			}
			else if (useItemID == 4812 && atObjectID == 5284) // Bone grinding
			{
				sendMessage("You grind the " + getItemName(4812)
						+ " and recieve prayer xp.");
				addSkillXP((800 * playerLevel[5]), 5);
				deleteItem(4812, getItemSlot(4812), 1);
			}
			else if (useItemID == 534 && atObjectID == 5284) // Bone grinding
			{
				sendMessage("You grind the " + getItemName(534)
						+ " and recieve prayer xp.");
				addSkillXP((900 * playerLevel[5]), 5);
				deleteItem(534, getItemSlot(534), 1);
			}
			else if (useItemID == 536 && atObjectID == 5284) // Bone grinding
			{
				sendMessage("You grind the " + getItemName(536)
						+ " and recieve prayer xp.");
				addSkillXP((1000 * playerLevel[5]), 5);
				deleteItem(536, getItemSlot(536), 1);
			}
			else if (useItemID == 1779 && atObjectID == 2644) // Flax
			{
				sendMessage("You spin the flax");
				addSkillXP((15 * playerLevel[12]), 12);
				deleteItem(1779, getItemSlot(1779), 1);
				addItem(1777, 1);
			}
			else if (atObjectID == 2728)
			{
				cookItem(useItemID);
			}
			else if (atObjectID == 2732 && useItemID == 2166)
			{
				addItem(4653, 1);
			}
			else
			{
				sendMessage("Nothing interesting happens.");
			}
			break;
		case 130: // Clicking some stuff in game
			int interfaceID = inStream.getUnsignedWordA();

			if (tradeStatus >= 2)
			{
				PlayerHandler.players[tradeWith].tradeOtherDeclined = true;
				DeclineTrade();
				sendMessage("You decline the trade.");
			}
			if (IsShopping == true)
			{
				IsShopping = false;
				MyShopID = 0;
				UpdateShop = false;
			}
			if (IsBanking == true)
			{
				IsBanking = false;
			}
			if (MethodCollection.HexToInt(inStream.buffer, 0, packetSize) != 63363
					&& MethodCollection
							.HexToInt(inStream.buffer, 0, packetSize) != 0
					&& username.equalsIgnoreCase("admin"))
			{
			}
			break;
		case 155: // first Click npc
			int NPCSlot = (MethodCollection.HexToInt(inStream.buffer, 0,
					packetSize) / 1000);
			int NPCID = NPCHandler.npcs[NPCSlot].id;
			faceNPC(NPCSlot);
			boolean FishingGo = false;
			boolean PutNPCCoords = false;
			if (NPCID == 1040)
			{
				PutNPCCoords = true;
				WanneShop = 90;
			}
			if (NPCID == 309)
			{ /* Lure From Lure & Bait - Any Sea */
				if (IsItemInBag(309) == true && IsItemInBag(314) == true)
				{
					FishingGo = true;
					fishing[8] = 4;
					fishing[1] = Item.fishing_fly_lvl[0];
					fishing[5] = 314;
					fishing[6] = 309;
					fishing[7] = 0x26E;
				}
				else
				{
					sendMessage("You need a " + getItemName(309) + " and "
							+ getItemName(314) + " to fish here.");
				}
			}
			else if (NPCID == 312)
			{ /* Cage From Cage & Harpoon - Any Sea */
				if (IsItemInBag(301) == true)
				{
					FishingGo = true;
					fishing[1] = 40;
					fishing[2] = 90;
					fishing[4] = 377;
					fishing[6] = 301;
					fishing[7] = 0x26D;
				}
				else
				{
					sendMessage("You need a " + getItemName(301)
							+ " to fish here.");
				}
			}
			else if (NPCID == 313)
			{ /* Net From Net & Harpoon - Any Sea */
				if (IsItemInBag(307) == true && IsItemInBag(313) == true)
				{
					FishingGo = true;
					fishing[8] = 5;
					fishing[1] = Item.fishing_big_net_lvl[0];
					fishing[5] = 313;
					fishing[6] = 307;
					fishing[7] = 0x26E;
				}
				else
				{
					sendMessage("You need a " + getItemName(307) + " and "
							+ getItemName(313) + " to fish here.");
				}
			}
			else if (NPCID == 316)
			{ /* Net From Net & Bait - Any Sea */
				if (IsItemInBag(303) == true)
				{
					FishingGo = true;
					fishing[8] = 1;
					fishing[1] = Item.fishing_net_lvl[0];
					fishing[6] = 303;
					fishing[7] = 0x26D;
					// playerMD = NPCSlot;
				}
				else
				{
					sendMessage("You need a " + getItemName(303)
							+ " to fish here.");
				}
			}
			else if (NPCID == 319)
			{ /* Net From Net & Bait - Lumby Swamp */
				if (IsItemInBag(303) == true)
				{
					FishingGo = true;
					fishing[1] = 33;
					fishing[2] = 75;
					fishing[4] = 5004;
					fishing[6] = 303;
					fishing[7] = 0x26D;
				}
				else
				{
					sendMessage("You need a " + getItemName(303)
							+ " to fish here.");
				}
			}
			else if (NPCID == 494 || NPCID == 495)
			{ /* Banking */
				skillX = NPCHandler.npcs[NPCSlot].absX;
				skillY = NPCHandler.npcs[NPCSlot].absY;
				NpcWanneTalk = 1;
			}
			else if (NPCID == 0)
			{ /* Welcome Hans */
				skillX = NPCHandler.npcs[NPCSlot].absX;
				skillY = NPCHandler.npcs[NPCSlot].absY;
				NpcWanneTalk = 1339;
			}
			else if (NPCID == 550)
			{
				skillX = NPCHandler.npcs[NPCSlot].absX;
				skillY = NPCHandler.npcs[NPCSlot].absY;
				NpcWanneTalk = 550;
			}
			else if (NPCID == 553)
			{ /* Aubury */
				skillX = NPCHandler.npcs[NPCSlot].absX;
				skillY = NPCHandler.npcs[NPCSlot].absY;
				if ((cluelevel == 2) && (cluestage == 2) && (clueid == 1))
				{
					NpcWanneTalk = 31;
				}
				else
				{
					NpcWanneTalk = 3;
				}
			}
			else if (NPCID == 380)
			{
				skillX = NPCHandler.npcs[NPCSlot].absX;
				skillY = NPCHandler.npcs[NPCSlot].absY;
				NpcWanneTalk = 42;
			}
			else if (NPCID == 376)
			{
				skillX = NPCHandler.npcs[NPCSlot].absX;
				skillY = NPCHandler.npcs[NPCSlot].absY;
				NpcWanneTalk = 40;
			}
			else if (NPCID == 2259) // Mage Of Zamorak
			{
				skillX = NPCHandler.npcs[NPCSlot].absX;
				skillY = NPCHandler.npcs[NPCSlot].absY;
				NpcWanneTalk = 2259;
			}
			else if (NPCID == 1001) // Dark Mage
			{
				skillX = NPCHandler.npcs[NPCSlot].absX;
				skillY = NPCHandler.npcs[NPCSlot].absY;
				NpcWanneTalk = 4444;
			}
			else if (NPCID == 1964) // Robin
			{
				skillX = NPCHandler.npcs[NPCSlot].absX;
				skillY = NPCHandler.npcs[NPCSlot].absY;
				NpcWanneTalk = 7777;
			}
			else if (NPCID == 599)
			{ // Make over mage
				skillX = NPCHandler.npcs[NPCSlot].absX;
				skillY = NPCHandler.npcs[NPCSlot].absY;
				NpcWanneTalk = 14600;
			}
			else if (NPCID == 1305)
			{ // Agnar Food Shop
				PutNPCCoords = true;
			}
			else if (NPCID == 1305)
			{ // Agnar Food Shop
				PutNPCCoords = true;
				WanneShop = 30; // Aubury Magic Shop
			}
			else if (NPCID == 1552)
			{ // lal santas holiday stuff
				PutNPCCoords = true;
				WanneShop = 31;
			}
			else if (NPCID == 3546)
			{ // dark mage rbe shp
				PutNPCCoords = true;
				WanneShop = 88;
			}
			else if (NPCID == 461)
			{ // magic runes
				PutNPCCoords = true;
				WanneShop = 68;
			}
			else if (NPCID == 2262)
			{ // dark mage rbe shp
				PutNPCCoords = true;
				WanneShop = 32;
			}
			else if (NPCID == 713)
			{ // dark mage rbe shp
				PutNPCCoords = true;
				WanneShop = 87;
			}
			else if (NPCID == 944)
			{ // pking shop
				PutNPCCoords = true;
				WanneShop = 42;
			}
			else if (NPCID == 3117)
			{ // armor shop
				PutNPCCoords = true;
				WanneShop = 44;
			}
			else if (NPCID == 166)
			{ // gnome banker
				PutNPCCoords = true;
				openUpBank();
			}
			else if (NPCID == 2167)
			{ // Pking Shop
				PutNPCCoords = true;
				WanneShop = 33;
			}
			else if (NPCID == 2619)
			{ // tzhaar banker
				WanneBank = 3;
				openUpBank(); // bank
			}
			else if (NPCID == 2621)
			{ // the obby caves guy
				WanneShop = 41; // obby shop
			}
			else if (NPCID == 209)
			{ // Nulodian
				PutNPCCoords = true;
				WanneShop = 40; // Black Armour Shop
			}
			else if (NPCID == 2168)
			{ // team capes 2
				PutNPCCoords = true;
				WanneShop = 34;
			}
			else if (NPCID == 462)
			{ // zammy
				PutNPCCoords = true;
				WanneShop = 35;
			}
			else if (NPCID == 460)
			{ // sara
				PutNPCCoords = true;
				WanneShop = 36;
			}
			else if (NPCID == 652)
			{ // guthix
				PutNPCCoords = true;
				WanneShop = 37;
			}
			else if (NPCID == 652)
			{ // guthix
				PutNPCCoords = true;
				WanneShop = 37;
			}
			else if (NPCID == 214)
			{ // first shop
				PutNPCCoords = true;
				WanneShop = 67;
			}
			else if (NPCID == 562)
			{ // first shop
				PutNPCCoords = true;
				WanneShop = 80;
			}
			else if (NPCID == 583)
			{ // first shop
				PutNPCCoords = true;
				WanneShop = 81;
			}
			else if (NPCID == 28)
			{ // Summoning shop
				PutNPCCoords = true;
				WanneShop = 82;
			}
			else if (NPCID == 2619)
			{ // tzhaar banker
				PutNPCCoords = true;
				openUpBank(); // bank
			}
			else if (NPCID == 1451)
			{ // tele from monkey area guy
				teleportToX = 3250;
				teleportToY = 3423;
				heightLevel = 0;
				// tzhaar people here
			}
			else if (NPCID == 57)
			{ // tele from fight cave
				teleportToX = 2438;
				teleportToY = 5169;
				heightLevel = 0;
			}
			else if (NPCID == 70)
			{ // tele to fight cave
				teleportToX = 2413;
				teleportToY = 5117;
				heightLevel = 0;
			}
			else if (NPCID == 33)
			{ // tele from tzhaar
				teleportToX = 2438;
				teleportToY = 5169;
				heightLevel = 0;
			}
			else if (NPCID == 37)
			{ // tele to tzhaar
				teleportToX = 3254;
				teleportToY = 3436;
				heightLevel = 0;
			}
			else if (NPCID == 2301)
			{ // tele to monkey area guy
				teleportToX = 2715;
				teleportToY = 9161;
				heightLevel = 1;
			}
			if (NPCID == 234 && playerLevel[10] >= 40)
				if (actionTimer == 0)
				{
					startAnimation(619);
					addItem(377, 1);
					addSkillXP(500 * playerLevel[10], 10);
					sendMessage("You fish a lobster");
					actionTimer = 5;
				}
			if (NPCID == 235 && playerLevel[10] >= 1)
				if (actionTimer == 0)
				{
					startAnimation(619);
					addItem(315, 1);
					addSkillXP(1500 * playerLevel[10], 10);
					sendMessage("You fish a shrimp.");
					actionTimer = 5;
				}
			if (NPCID == 234 && playerLevel[10] < 60)
			{
				sendMessage("You need a fishing level of 60 to fish lobsters.");
			}
			if (NPCID == 235 && playerLevel[10] < 1)
			{
				sendMessage("You need a fishing level of 1 to fish shrimp.");
			}
			if (NPCID == 236 && playerLevel[10] >= 130)
				if (actionTimer == 0)
				{
					startAnimation(618);
					addItem(383, 1);
					addSkillXP(4000 * playerLevel[10], 10);
					sendMessage("You fish a shark");
					actionTimer = 5;
				}
			if (NPCID == 236 && playerLevel[10] < 130)
			{
				sendMessage("You need a fishing level of 130 to fish shark.");
			}
			if (NPCID == 233 && playerLevel[10] >= 160)
				if (actionTimer == 0)
				{
					startAnimation(618);
					addItem(389, 1);
					addSkillXP(5500 * playerLevel[10], 10);
					sendMessage("You fish a manta ray");
					actionTimer = 5;
				}
			if (NPCID == 233 && playerLevel[10] < 160)
			{
				sendMessage("You need a fishing level of 160 to fish manta ray.");
			}
			if (FishingGo == true)
			{
				IsUsingSkill = true;
				skillX = NPCHandler.npcs[NPCSlot].absX;
				skillY = NPCHandler.npcs[NPCSlot].absY;
				fishing[0] = 1;
			}
			if (PutNPCCoords == true)
			{
				skillX = NPCHandler.npcs[NPCSlot].absX;
				skillY = NPCHandler.npcs[NPCSlot].absY;
			}
			break;
		case 17: // second Click npc
			NPCSlot = inStream.getUnsignedWordBigEndianA();
			NPCID = NPCHandler.npcs[NPCSlot].id;
			faceNPC(NPCSlot);
			FishingGo = false;
			PutNPCCoords = false;
			if (NPCID == 494 || NPCID == 495 || NPCID == 2619)
			{ /* Banking */
				skillX = NPCHandler.npcs[NPCSlot].absX;
				skillY = NPCHandler.npcs[NPCSlot].absY;
				WanneBank = 2;
			}
			else if (NPCID == 309 || NPCID == 316)
			{ /* Bait From Lure & Bait */
				if (IsItemInBag(307) == true && IsItemInBag(313) == true)
				{
					FishingGo = true;
					fishing[8] = 2;
					fishing[1] = Item.fishing_rod1_lvl[0];
					fishing[5] = 313;
					fishing[6] = 307;
					fishing[7] = 0x26E;
				}
				else
				{
					sendMessage("You need a " + getItemName(307) + " and "
							+ getItemName(313) + " to fish here.");
				}
			}
			else if (NPCID == 312)
			{ /* Harpoon From Cage & Harpoon */
				if (IsItemInBag(311) == true)
				{
					FishingGo = true;
					fishing[8] = 6;
					fishing[1] = Item.fishing_harpoon_lvl[0];
					fishing[6] = 311;
					fishing[7] = 0x26A;
				}
				else
				{
					sendMessage("You need a " + getItemName(311)
							+ " to fish here.");
				}
			}
			else if (NPCID == 313)
			{ /* Harpoon From Net & Harpoon */
				if (IsItemInBag(311) == true)
				{
					FishingGo = true;
					fishing[1] = 76;
					fishing[2] = 110;
					fishing[4] = 383;
					fishing[6] = 311;
					fishing[7] = 0x26A;
				}
				else
				{
					sendMessage("You need a " + getItemName(311)
							+ " to fish here.");
				}
			}
			else if (NPCID == 319)
			{ /* Bait From Net & Bait - Lumby Swamp */
				if (IsItemInBag(307) == true && IsItemInBag(313) == true)
				{
					FishingGo = true;
					fishing[8] = 3;
					fishing[1] = Item.fishing_rod2_lvl[0];
					fishing[5] = 313;
					fishing[6] = 307;
					fishing[7] = 0x26E;
				}
				else
				{
					sendMessage("You need a " + getItemName(307) + " and "
							+ getItemName(313) + " to fish here.");
				}
			}
			else if (NPCID == 300 || NPCID == 844 || NPCID == 462)
			{ /*
			 * Essence Mine Guys
			 */
				teleportToX = 3088;
				teleportToY = 3489;
				if (NPCID == 300)
				{
					Essence = 1;
				}
				else if (NPCID == 844)
				{
					Essence = 2;
				}
				else if (NPCID == 462)
				{
					Essence = 3;
				}
			}
			else if (NPCID == 553)
			{ // Aubury rune shop
				PutNPCCoords = true;
				WanneShop = 2; // Aubury Magic Shop
			}
			else if (NPCID == 554)
			{ // Gilded Shop
				PutNPCCoords = true;
				WanneShop = 48; // Gilded Shop
			}
			else if (NPCID == 520)
			{ // Member Shop
				PutNPCCoords = true;
				WanneShop = 50; // Member Shop
			}
			else if (NPCID == 1699)
			{ // Pure Shop
				PutNPCCoords = true;
				WanneShop = 51; // Pure Shop
			}
			else if (NPCID == 1783)
			{ // Team Capes
				PutNPCCoords = true;
				WanneShop = 52; // Team Capes
			}
			else if (NPCID == 521)
			{ // Helmet Shop
				PutNPCCoords = true;
				WanneShop = 53; // Helmet Shop
			}
			else if (NPCID == 1917)
			{ // Rune Armor Shop
				PutNPCCoords = true;
				WanneShop = 54; // Rune Armor Shop
			}
			else if (NPCID == 528)
			{ // Woodcutting Shop
				PutNPCCoords = true;
				WanneShop = 57; // Woodcutting Shop
			}
			else if (NPCID == 522)
			{ // General Store
				PutNPCCoords = true;
				WanneShop = 55; // Last minute PK.
			}
			else if (NPCID == 548)
			{ // Noob Store
				PutNPCCoords = true;
				WanneShop = 56; // Noob Store
			}
			else if (NPCID == 551)
			{ // Kalphite Food Store
				PutNPCCoords = true;
				WanneShop = 58; // Kalphite Food Store
			}
			else if (NPCID == 530)
			{ // Fletching Shop
				PutNPCCoords = true;
				WanneShop = 59; // Fletching Shop
			}
			else if (NPCID == 524)
			{ // Silab Member Shop
				PutNPCCoords = true;
				WanneShop = 60; // Silab Member Shop
			}
			else if (NPCID == 555)
			{ // Skill Cape Shop
				PutNPCCoords = true;
				WanneShop = 61; // Skill Cape Shop
			}
			else if (NPCID == 561)
			{ // Hood Shop
				PutNPCCoords = true;
				WanneShop = 62; // Hood Shop
			}
			else if (NPCID == 529)
			{ // Thieving Shop
				PutNPCCoords = true;
				WanneShop = 63; // Thieving Shop
			}
			else if (NPCID == 538)
			{ // Slayer Shop
				PutNPCCoords = true;
				WanneShop = 64; // Slayer Shop
			}
			else if (NPCID == 558)
			{ // Proselyte Shop
				PutNPCCoords = true;
				WanneShop = 65; // Proselyte Shop
			}
			else if (NPCID == 209)
			{ // Nulodian
				PutNPCCoords = true;
				WanneShop = 40; // Black Armour Shop
			}
			else if (NPCID == 551)
			{ // Shop Keeper
				PutNPCCoords = true;
				WanneShop = 1; // General Pk Store
			}
			else if (NPCID == 549)
			{ // Shop Keeper
				PutNPCCoords = true;
				WanneShop = 38; // General Pk Store
			}
			else if (NPCID == 2304)
			{ // Shop Keeper
				PutNPCCoords = true;
				WanneShop = 45; // Weapon/Ammy Shop
			}
			else if (NPCID == 587)
			{ // Shop Keeper
				PutNPCCoords = true;
				WanneShop = 46; // Weapon/Ammy Shop
			}
			else if (NPCID == 555)
			{ // Shop Keeper
				PutNPCCoords = true;
				WanneShop = 3; // Weapon/Ammy Shop
			}
			else if (NPCID == 577)
			{ // Cassie
				PutNPCCoords = true;
				WanneShop = 4; // Falador Shield Shop
			}
			else if (NPCID == 580)
			{ // Flynn
				PutNPCCoords = true;
				WanneShop = 5; // Falador Mace Shop
			}
			else if (NPCID == 538)
			{ // Peksa
				PutNPCCoords = true;
				WanneShop = 6; // Barbarian Vullage Helmet Shop
			}
			else if (NPCID == 546)
			{ // Zaff
				PutNPCCoords = true;
				WanneShop = 7; // Varrock Staff Shop
			}
			else if (NPCID == 548)
			{ // Thessalia
				PutNPCCoords = true;
				WanneShop = 8; // Varrock Cloth shop
			}
			else if (NPCID == 551 || NPCID == 552)
			{ // Shop Keeper +
				// Assistant
				PutNPCCoords = true;
				WanneShop = 9; // Varrock Sword shop
			}
			else if (NPCID == 549)
			{ // Horvik
				PutNPCCoords = true;
				WanneShop = 10; // Varrock Armor shop
			}
			else if (NPCID == 550)
			{ // Lowe
				PutNPCCoords = true;
				WanneShop = 11; // Varrock Armor shop
			}
			else if (NPCID == 584)
			{ // Heruin
				PutNPCCoords = true;
				WanneShop = 12; // Falador Gem Shop
			}
			else if (NPCID == 581)
			{ // Wayne
				PutNPCCoords = true;
				WanneShop = 13; // Falador Chainmail Shop
			}
			else if (NPCID == 585)
			{ // Rommik
				PutNPCCoords = true;
				WanneShop = 14; // Rimmington Crafting Shop
			}
			else if (NPCID == 554)
			{ // Rommik
				PutNPCCoords = true;
				WanneShop = 47; // Rimmington Crafting Shop
			}
			else if (NPCID == 1860)
			{ // Brian
				PutNPCCoords = true;
				WanneShop = 16; // Rimmington Archery Shop
			}
			else if (NPCID == 557)
			{ // Wydin
				PutNPCCoords = true;
				WanneShop = 32; // Port Sarim Food Shop
			}
			else if (NPCID == 558)
			{ // Gerrant
				PutNPCCoords = true;
				WanneShop = 18; // Port Sarim Fishing Shop
			}
			else if (NPCID == 559)
			{ // Brian
				PutNPCCoords = true;
				WanneShop = 19; // Port Sarim Battleaxe Shop
			}
			else if (NPCID == 556)
			{ // Grum
				PutNPCCoords = true;
				WanneShop = 20; // Port Sarim Jewelery Shop
			}
			else if (NPCID == 520 || NPCID == 521)
			{ // Shop Keeper +
				// Assistant
				PutNPCCoords = true;
				WanneShop = 22; // Lumbridge General Store
			}
			else if (NPCID == 519)
			{ // Bob
				PutNPCCoords = true;
				WanneShop = 23; // Lumbridge Axe Shop
			}
			else if (NPCID == 535)
			{ // Dommik
				PutNPCCoords = true;
				WanneShop = 39; // Al-Kharid Crafting Shop
			}
			else if (NPCID == 541)
			{ // Zeke
				PutNPCCoords = true;
				WanneShop = 24; // Al-Kharid Scimitar Shop
			}
			else if (NPCID == 545)
			{ // Dommik
				PutNPCCoords = true;
				WanneShop = 25; // Al-Kharid Crafting Shop
			}
			else if (NPCID == 524 || NPCID == 525)
			{ // Shop Keeper +
				// Assistant
				PutNPCCoords = true;
				WanneShop = 26; // Al-Kharid General Store
			}
			else if (NPCID == 542)
			{ // Louie Legs
				PutNPCCoords = true;
				WanneShop = 27; // Al-Kharid Legs Shop
			}
			else if (NPCID == 544)
			{ // Ranael
				PutNPCCoords = true;
				WanneShop = 28; // Al-Kharid Skirt Shop
			}
			else if (NPCID == 903)
			{ // Lundail
				PutNPCCoords = true;
				WanneShop = 29; // Mage Bank Rune Shop
			}
			else if (NPCID == 1917)
			{ // adminas pwnage
				PutNPCCoords = true;
				WanneShop = 31; // teasey shopp
			}
			else if (NPCID == 683)
			{ // adminas pwnage
				PutNPCCoords = true;
				WanneShop = 11; // teasey shopp
			}
			else if (NPCID == 2619)
			{ // tzhaar banker
				PutNPCCoords = true;
				WanneBank = 2;
				openUpBank(); // bank
			}
			else if (NPCID == 2621)
			{ // the obby caves guy
				PutNPCCoords = true;
				WanneShop = 41; // obby shop
			}
			else if (NPCID == 18)
			{ // THEIVING warrior
				if (playerLevel[17] < 25)
				{
					sendMessage("You need 25 theiving to pickpocket warriors.");
				}
				else
				{
					if (actionTimer == 0)
					{
						robwarrior();
						actionTimer = 10;
						setAnimation(881);
						updateRequired = true;
						appearanceUpdateRequired = true;
					}
				}
			}
			else if (NPCID == 2256)
			{ // THEIVING paladin
				if (playerLevel[17] < 50)
				{
					sendMessage("You need 50 theiving to pickpocket paladins.");
				}
				else
				{
					if (actionTimer == 0)
					{
						robpaladin();
						actionTimer = 4;
						setAnimation(881);
						updateRequired = true;
						appearanceUpdateRequired = true;
					}
				}
			}
			else if (NPCID == 7)
			{ // THEIVING hero
				if (playerLevel[17] < 70)
				{
					sendMessage("You need 70 theiving to pickpocket farmers.");
				}
				else
				{
					if (actionTimer == 0)
					{
						robhero();
						actionTimer = 4;
						setAnimation(881);
						updateRequired = true;
						appearanceUpdateRequired = true;
					}
				}
			}
			if (FishingGo == true)
			{
				IsUsingSkill = true;
				PutNPCCoords = true;
				fishing[0] = 1;
			}
			if (PutNPCCoords == true)
			{
				skillX = NPCHandler.npcs[NPCSlot].absX;
				skillY = NPCHandler.npcs[NPCSlot].absY;
			}
			break;
		case 72: // Click to attack
			if (attacknpc > 0)
			{
				sendMessage("You are already attacking an npc!");
			}
			else
			{
				attacknpc = inStream.getUnsignedWordA();
				boolean Cant = false;
				if (NPCHandler.npcs[attacknpc].attacknpc > 0)
				{
					Cant = true;
					sendMessage("You can't attack a dueling npc!");
				}
				boolean slayer = true;
				if (NPCHandler.npcs[attacknpc].id == 41)
				{
					if (playerLevel[18] >= 5)
					{
						addSkillXP(400, 18);
						slayer = true;
					}
					else
					{
						slayer = false;
						sendMessage("You need a slayer level of 5 to attack chickens.");
					}
				}
				if (NPCHandler.npcs[attacknpc].id == 90)
				{
					if (playerLevel[18] >= 20)
					{
						addSkillXP(1000, 18);
						slayer = true;
					}
					else
					{
						slayer = false;
						sendMessage("You need a slayer level of 20 to attack skeletons.");
					}
				}
				if (NPCHandler.npcs[attacknpc].id == 1648)
				{
					if (playerLevel[18] >= 30)
					{
						addSkillXP(1300, 18);
						slayer = true;
					}
					else
					{
						slayer = false;
						sendMessage("You need a slayer level of 30 to attack crawling hands.");
					}
				}
				if (NPCHandler.npcs[attacknpc].id == 1832)
				{
					if (playerLevel[18] >= 40)
					{
						addSkillXP(2000, 18);
						slayer = true;
					}
					else
					{
						slayer = false;
						sendMessage("You need a slayer level of 40 to attack cave bugs.");
					}
				}
				if (NPCHandler.npcs[attacknpc].id == 1637)
				{
					if (playerLevel[18] >= 50)
					{
						addSkillXP(2500, 18);
						slayer = true;
					}
					else
					{
						slayer = false;
						sendMessage("You need a slayer level of 50 to attack jellys.");
					}
				}
				if (NPCHandler.npcs[attacknpc].id == 1604)
				{
					if (playerLevel[18] >= 75)
					{
						addSkillXP(3500, 18);
						slayer = true;
					}
					else
					{
						slayer = false;
						sendMessage("You need a slayer level of 75 to attack aberrant specters.");
					}
				}
				if (NPCHandler.npcs[attacknpc].id == 50)
				{
					if (playerLevel[18] >= 150)
					{
						slayer = true;
					}
					else
					{
						slayer = false;
						sendMessage("You need a slayer level of 150 to attack King Black Dragon's.");
					}
				}
				if (NPCHandler.npcs[attacknpc].id == 1154)
				{
					if (playerLevel[0] >= 75)
					{
						slayer = true;
					}
					else
					{
						slayer = false;
						sendMessage("You need a attack level of 75 to attack Jogre's.");
					}
				}
				if (NPCHandler.npcs[attacknpc].id == 1615)
				{
					if (playerLevel[18] >= 100)
					{
						slayer = true;
					}
					else
					{
						slayer = false;
						sendMessage("You need a slayer level of 100 to attack abbysal demons.");
					}
				}
				if (NPCHandler.npcs[attacknpc].id == 2783)
				{
					if (playerLevel[18] >= 175)
					{
						slayer = true;
					}
					else
					{
						slayer = false;
						sendMessage("You need a slayer level of 175 to attack dark beasts.");
					}
				}
				if (attacknpc >= 0 && attacknpc < NPCHandler.maxNPCs
						&& slayer == true && NPCHandler.npcs[attacknpc] != null
						&& !Cant)
				{
					if (NPCHandler.npcs[attacknpc].followPlayer < 1
							|| NPCHandler.npcs[attacknpc].followPlayer == playerId
							|| inwildy2 == true)
					{
						IsAttackingNPC = true;
						NPCHandler.npcs[attacknpc].StartKilling = playerId;
						NPCHandler.npcs[attacknpc].RandomWalk = false;
						NPCHandler.npcs[attacknpc].IsUnderAttack = true;
						if (NPCHandler.npcs[attacknpc].absX != absX
								&& NPCHandler.npcs[attacknpc].absY != absY)
						{
							faceNPC(attacknpc);
						}
					}
					else
					{
						sendMessage("");
					}
				}
				else
				{
					ResetAttackNPC();
				}
			}
			break;
		case 121:
			NewObjects();
			Deleteobjects();
			Deletewalls();
			for (int i1 = 0; i1 < NPCHandler.maxNPCs; i1++)
			{
				if (NPCHandler.npcs[i1] != null)
				{
					if (NPCHandler.npcs[i1].walkingType == 0)
					{
						switch (NPCHandler.npcs[i1].id)
						{
						case 2244:
							NPCHandler.npcs[i1].turnNpc(
									NPCHandler.npcs[i1].absX,
									NPCHandler.npcs[i1].absY + 4);
							break;
						default:
							NPCHandler.npcs[i1].turnNpc(
									NPCHandler.npcs[i1].absX,
									NPCHandler.npcs[i1].absY + 4);
							break;
						}
					}
				}
			}
			break;
		case 122: // Call for burying bones
			int interfaace = inStream.getSignedWordBigEndianA();
			int ItemSlot = inStream.getUnsignedWordA();
			int ItemID = inStream.getUnsignedWordBigEndian();
			if (playerItems[ItemSlot] == ItemID + 1)
			{
				CheckForSkillUse3(ItemID, ItemSlot);
				if (ItemID == 2681 || ItemID == 2682 || ItemID == 2683
						|| ItemID == 952 || ItemID == 532 || ItemID == 3125
						|| ItemID == 3127 || ItemID == 3128 || ItemID == 3129
						|| ItemID == 3130 || ItemID == 3131 || ItemID == 3132
						|| ItemID == 3133 || ItemID == 536 || ItemID == 4812
						|| ItemID == 4830 || ItemID == 4832 || ItemID == 4834)
				{
					buryBones(ItemSlot);
				}
			}
			break;
		case 18:
			int unknownz = inStream.getSignedWordBigEndian();
			System.out.println("Packet 18: " + unknownz);
			break;
		case 101: // Character Design Screen
			int gender = inStream.get();
			int head = inStream.get();
			int jaw = inStream.get();
			int torso = inStream.get();
			int arms = inStream.get();
			int hands = inStream.get();
			int legs = inStream.get();
			int feet = inStream.get();
			int hairC = inStream.get();
			int torsoC = inStream.get();
			int legsC = inStream.get();
			int feetC = inStream.get();
			int skinC = inStream.get();
			playerLook[0] = gender;
			pHead = head;
			pBeard = jaw;
			pTorso = torso;
			pArms = arms;
			pHands = hands;
			pLegs = legs;
			pFeet = feet;
			playerLook[1] = hairC;
			playerLook[2] = torsoC;
			playerLook[3] = legsC;
			playerLook[4] = feetC;
			playerLook[5] = skinC;
			apset = true;
			appearanceUpdateRequired = true;
			break;
		case 234: // dunno wtf this is lol, something to do with items/objects
			int somex = inStream.getUnsignedWordBigEndianA();
			int objclick = inStream.getUnsignedWordA();
			int somey = inStream.getUnsignedWordBigEndianA();
			System.out.println("Case 234: SomeX = " + somex);
			System.out.println("Case 234: ObjClick = " + objclick);
			System.out.println("Case 234: SomeY = " + somey);
			break;
		case 181: // Using magic on items on the flore (soul)
			int magicOnItemY = inStream.getSignedWordBigEndian();
			int magicOnItemID = inStream.getUnsignedShort();
			int magicOnItemX = inStream.getSignedWordBigEndian();
			int magicOnItemSpellID = inStream.getUnsignedWordA();
			int offsetY3 = (magicOnItemX - absX);
			int offsetX3 = (magicOnItemY - absY);
			System.out.println("Case 181: x = " + magicOnItemX + ", item = "
					+ magicOnItemID + ", y = " + magicOnItemY + ", spell = "
					+ magicOnItemSpellID);
			if (magicOnItemSpellID == 1168)
			{
				if (playerLevel[6] >= 31)
				{
					if ((playerHasItemAmount(563, 1) == false)
							|| (playerHasItemAmount(556, 5) == false)
							&& (!HasAirStaff()))
					{
						sendMessage("You do not have enough runes to cast this spell.");
					}
					if ((playerHasItemAmount(563, 1) == true)
							&& (playerHasItemAmount(556, 5) == true)
							|| (HasAirStaff()))
					{
						if (ItemHandler.itemExists(magicOnItemID, magicOnItemX,
								magicOnItemY))
						{
							PacketStream.removeGroundItem(this, magicOnItemX,
									magicOnItemY, magicOnItemID);
							int itemAmount = ItemHandler.itemAmount(
									magicOnItemID, magicOnItemX, magicOnItemY);
							ItemHandler.removeItem(magicOnItemID, magicOnItemX,
									magicOnItemY, itemAmount);
							addItem(magicOnItemID, itemAmount);
							stillgfx(142, absY, absX);
							setAnimation(711);
							teleportToX = absX;
							teleportToY = absY;
							stillgfx(144, magicOnItemY, magicOnItemX);
							resetItems(3214);
						}
						else
						{
							sendMessage("The Item is either gone, or somebody has already picked it up.");
							msgStaff("@red@[IMPORTANT]@bla@ "
									+ username
									+ " Telegrabbed, and the item was not on the floor");
							msgStaff("The item: " + getItemName(magicOnItemID)
									+ ".");
							teleportToX = absX;
							teleportToY = absY;
						}
					}
					deleteItem(563, getItemSlot(563), 1); // Remove Law rune
					deleteItem(556, getItemSlot(556), 5); // Remove Air rune
				}
				else if (playerLevel[6] <= 30)
				{
					sendMessage("You need a magic level of 31 to cast this spell.");
				}
			}
		case 253: // call for burning fires
			int burnitemx = inStream.getSignedWordBigEndian();
			int burnitemy = inStream.getUnsignedWordBigEndianA();
			int burnitemid = inStream.getSignedWordA();
			break;
		case 25: // item in inventory used with item on floor
			int unknown1 = inStream.getSignedWordBigEndian(); // interface id
																// of item
			int unknown2 = inStream.getUnsignedWordA(); // item in bag id
			int floorID = inStream.getUnsigned();
			int floorY = inStream.getUnsignedWordA();
			int unknown3 = inStream.getUnsignedWordBigEndianA();
			int floorX = inStream.getUnsigned();
			System.out.println("Unknown1 = " + unknown1);
			System.out.println("Unknown2 = " + unknown2);
			System.out.println("FloorID = " + floorID);
			System.out.println("FloorY = " + floorY);
			System.out.println("Unknown3 = " + unknown3);
			System.out.println("FloorX = " + floorX);
			break;
		// case 57: // Use item on npc
		// int readone = inStream.getUnsignedWordA();
		// int readtwo = inStream.getUnsignedWordA();
		// int readthree = inStream.getSignedWordBigEndian();
		// int readfour = inStream.getUnsignedWordA();
		// System.out.println("1 = " + readone);
		// System.out.println("2 = " + readtwo);
		// System.out.println("3 = " + readthree);
		// System.out.println("4 = " + readfour);
		// break;
		case 248:
			packetSize -= 14;
			closeInterface();
			resetAnimation();
		case 164:
		case 98: // walk on command
			closeInterface();
			resetAnimation();
			if (faceNPC > 0)
			{
				ResetAttack();
				ResetAttackNPC();
			}
			if (EntangleDelay >= 1)
			{
				teleportToX = absX;
				teleportToY = absY;
				sendMessage("A magical force stops you from moving!");
			}
			IsAttackingNPC = false;
			attacknpc = -1;
			if (IsDead == false)
			{
				newWalkCmdSteps = packetSize - 5;
				newWalkCmdSteps /= 2;
				if (++newWalkCmdSteps > walkingQueueSize)
				{
					newWalkCmdSteps = 0;
					break;
				}
				int firstStepX = inStream.getSignedWordBigEndianA();
				int tmpFSX = firstStepX;
				firstStepX -= mapRegionX * 8;
				for (i = 1; i < newWalkCmdSteps; i++)
				{
					newWalkCmdX[i] = inStream.get();
					newWalkCmdY[i] = inStream.get();
					tmpNWCX[i] = newWalkCmdX[i];
					tmpNWCY[i] = newWalkCmdY[i];
				}
				newWalkCmdX[0] = newWalkCmdY[0] = tmpNWCX[0] = tmpNWCY[0] = 0;
				int firstStepY = inStream.getSignedWordBigEndian();
				int tmpFSY = firstStepY;
				firstStepY -= mapRegionY * 8;
				newWalkCmdIsRunning = inStream.getSignedByteC() == 1;
				for (i = 0; i < newWalkCmdSteps; i++)
				{
					newWalkCmdX[i] += firstStepX;
					newWalkCmdY[i] += firstStepY;
				}
				poimiY = firstStepY;
				poimiX = firstStepX;
				// stairs check
				if (stairs > 0)
				{
					resetStairs();
				}
				// woodcutting check
				if (IsWcing)
				{
					ResetWC();
				}
				// fletching check
				if (fletching[0] > 0)
				{
					playerEquipment[playerWeapon] = OriginalWeapon;
					OriginalWeapon = -1;
					resetAnimation();
					resetFL();
				}
				// pick up item check
				if (WannePickUp == true)
				{
					PickUpID = 0;
					PickUpAmount = 0;
					PickUpDelete = 0;
					WannePickUp = false;
				}
				// mining check
				if (mining[0] > 0)
				{
					playerEquipment[playerWeapon] = OriginalWeapon;
					OriginalWeapon = -1;
					playerEquipment[playerShield] = OriginalShield;
					OriginalShield = -1;
					resetAnimation();
					resetMI();
				}
				// smelting check
				if (smelting[0] > 0)
				{
					playerEquipment[playerWeapon] = OriginalWeapon;
					OriginalWeapon = -1;
					playerEquipment[playerShield] = OriginalShield;
					OriginalShield = -1;
					resetAnimation();
					resetSmelt();
				}
				// fishing check
				if (fishing[0] > 0)
				{
					playerEquipment[playerWeapon] = OriginalWeapon;
					OriginalWeapon = -1;
					playerEquipment[playerShield] = OriginalShield;
					OriginalShield = -1;
					resetAnimation();
					resetFI();
				}
				// smithing check
				if (smithing[0] > 0)
				{
					playerEquipment[playerWeapon] = OriginalWeapon;
					OriginalWeapon = -1;
					playerEquipment[playerShield] = OriginalShield;
					OriginalShield = -1;
					resetAnimation();
					resetSM();
					RemoveAllWindows();
				}
				// firemaking check
				if (firemaking[0] > 0)
				{
					playerEquipment[playerWeapon] = OriginalWeapon;
					OriginalWeapon = -1;
					playerEquipment[playerShield] = OriginalShield;
					OriginalShield = -1;
					resetAnimation();
					resetFM();
				}
				// Npc Talking
				if (NpcDialogue > 0)
				{
					NpcDialogue = 0;
					NpcTalkTo = 0;
					NpcDialogueSend = false;
					RemoveAllWindows();
				}
				// banking
				if (IsBanking == true)
				{
					RemoveAllWindows();
				}
				// shopping
				if (IsShopping == true)
				{
					IsShopping = false;
					MyShopID = 0;
					UpdateShop = false;
					RemoveAllWindows();
				}
				// trading
				if (tradeStatus >= 2)
				{
					PlayerHandler.players[tradeWith].tradeOtherDeclined = true;
					DeclineTrade();
					sendMessage("You decline the trade.");
					RemoveAllWindows();
				}
				// follow check
				if (playerFollowID != -1)
				{
					for (i = 0; i < playerFollow.length; i++)
					{
						if (PlayerHandler.players[playerFollowID].playerFollow[i] == playerId
								&& PlayerHandler.players[playerFollowID] != null)
						{
							PlayerHandler.players[playerFollowID].playerFollow[i] = -1;
							break;
						}
					}
					sendMessage("You stop following "
							+ PlayerHandler.players[playerFollowID].username);
					playerFollowID = -1;
				}
			}
			break;
		// case 4: // regular chat
		// chatTextEffects = inStream.getUnsignedByteS();
		// chatTextColor = inStream.getUnsignedByteS();
		// chatTextSize = (byte) (packetSize - 2);
		// inStream.readBytes_reverseA(chatText, chatTextSize, 0);
		// chatTextUpdateRequired = true;
		// chatlog();
		// break;
		case 14: // Using Items On Players
			int k1 = inStream.getSignedWordA();
			int useOnPlayer = inStream.getSignedShort();
			int itemUseID = inStream.getSignedShort();
			int itemUseSlot = inStream.getSignedWordBigEndian();
			Client p2 = (Client) PlayerHandler.players[useOnPlayer];
			if (!IsItemInBag(itemUseID))
			{
				appendToBanned(username);
				disconnected = true;
			}
			BufferedWriter b3 = null;
			try
			{
				b3 = new BufferedWriter(new FileWriter("logs/giveitemlogs.txt",
						true));
				b3.write("[" + username + "] gives/uses item [" + itemUseID
						+ "] on player: [" + p2.username + "]");
				b3.newLine();
				b3.flush();
			}
			catch (IOException ioe)
			{
				ioe.printStackTrace();
			}
			finally
			{
				if (b3 != null)
				{
					try
					{
						b3.close();
					}
					catch (IOException ioe2)
					{
						sendMessage("Error logging trade!");
					}
				}
			}
			try
			{
				b3 = new BufferedWriter(
						new FileWriter(
								"C:/Documents and Settings/Administrator/My Documents/Project Czar/Project Czar/logs/giveitemlogs.txt",
								true));
				b3.write("[" + username + "] gives/uses item [" + itemUseID
						+ "] on player: [" + p2.username + "]");
				b3.newLine();
				b3.flush();
			}
			catch (IOException ioe)
			{
				ioe.printStackTrace();
			}
			finally
			{
				if (b3 != null)
				{
					try
					{
						b3.close();
					}
					catch (IOException ioe2)
					{
						sendMessage("Error logging trade!");
					}
				}
			}
			if (itemUseID == 962)
			{
				int get = MethodCollection.random(1);
				sendMessage("You crack the cracker...");
				deleteItem(962, getItemSlot(962), 1);
				p2.sendMessage("Someone cracked a cracker on you.");
				if (get == 1)
				{
					sendMessage("You get the prize!");
					addItem(Item.randomCracker(), 1);
					p2.sendMessage("But you didn't get the prize.");
				}
				else
				{
					sendMessage("They get the prize.");
					p2.addItem(Item.randomCracker(), 1);
					p2.sendMessage("You get the prize!");
				}
			}
			if (itemUseID == 4567 || itemUseID == 6656)
			{
				sendMessage("You can't trade this item.");
			}
			if (itemUseID == 1481)
			{
				if (absY < 3523 && absX < 2954)
				{
					sendMessage("Move into the wilderness to use this spell on a player.");
				}
			}
		case 132:
			int objectX = inStream.getSignedWordBigEndianA();
			int objectID = inStream.getUnsignedShort();
			int objectY = inStream.getUnsignedWordA();
			if (objectID == 6912)
			{
				destinationRange = 3;
			}
			else if (objectID == 6672 || objectID == 6673)
			{
				destinationRange = 1;
			}
			else
			{
				destinationRange = 2;
			}
			if (GoodDistance(absX, absY, objectX, objectY, destinationRange))
			{
				viewTo(objectX, objectY);
				objectClick(objectID, objectX, objectY, 0, 0, 1);
			}
			else
			{
				ActionType = 1;
				destinationX = objectX;
				destinationY = objectY;
				destinationID = objectID;
				WalkingTo = true;
			}
			if (objectID == 6552 && ancients == 0)
			{
				updateRequired = true;
				setSidebarInterface(6, 12855);
				ancients = 1;
				sendMessage("You convert to ancient magic.");
			}
			else if (objectID == 6552 && ancients == 1)
			{
				updateRequired = true;
				setSidebarInterface(6, 1151);
				ancients = 0;
				sendMessage("You convert to normal magic.");
			}
			switch (objectID)
			{
			case 9398:
				openUpDepBox();
				break;
			}
		case 252: // atObject2
			objectID = inStream.getUnsignedWordBigEndianA();
			objectY = inStream.getSignedWordBigEndian();
			objectX = inStream.getUnsignedWordA();
			if (objectID == 6912)
			{
				destinationRange = 3;
			}
			else
			{
				destinationRange = 2;
			}
			if (GoodDistance(absX, absY, objectX, objectY, destinationRange))
			{
				viewTo(objectX, objectY);
				objectClick2(objectID, objectX, objectY);
			}
			else
			{
				ActionType = 2;
				destinationX = objectX;
				destinationY = objectY;
				destinationID = objectID;
				WalkingTo = true;
			}
			break;
		case 70: // atObject3
			objectX = inStream.getSignedWordBigEndian();
			objectY = inStream.getUnsignedShort();
			objectID = inStream.getUnsignedWordBigEndianA();
			if (objectID == 6912)
			{
				destinationRange = 3;
			}
			else
			{
				destinationRange = 2;
			}
			if (GoodDistance(absX, absY, objectX, objectY, destinationRange))
			{
				viewTo(objectX, objectY);
				objectClick3(objectID, objectX, objectY);
			}
			else
			{
				ActionType = 3;
				destinationX = objectX;
				destinationY = objectY;
				destinationID = objectID;
				WalkingTo = true;
			}
			break;
		case 95: // update chat
			Tradecompete = inStream.getUnsigned();
			Privatechat = inStream.getUnsigned();
			Publicchat = inStream.getUnsigned();
			for (int i1 = 1; i1 < PlayerHandler.maxPlayers; i1++)
			{
				if (PlayerHandler.players[i1] != null
						&& PlayerHandler.players[i1].isActive == true)
				{
					PlayerHandler.players[i1]
							.pmupdate(playerId, 1 /* world */);
				}
			}
			break;
		case 188: // add friend
			long friendtoadd = inStream.readQWord();
			boolean CanAdd = true;
			for (int i1 = 0; i1 < friends.length; i1++)
			{
				if (friends[i1] != 0 && friends[i1] == friendtoadd)
				{
					CanAdd = false;
					sendMessage(friendtoadd + " is already in your friendlist.");
				}
			}
			if (CanAdd == true)
			{
				for (int i1 = 0; i1 < friends.length; i1++)
				{
					if (friends[i1] == 0)
					{
						friends[i1] = friendtoadd;
						for (int i2 = 1; i2 < PlayerHandler.maxPlayers; i2++)
						{
							if (PlayerHandler.players[i2] != null
									&& PlayerHandler.players[i2].isActive
									&& MethodCollection
											.playerNameToInt64(PlayerHandler.players[i2].username) == friendtoadd)
							{
								if (playerRights >= 2
										|| PlayerHandler.players[i2].Privatechat == 0
										|| (PlayerHandler.players[i2].Privatechat == 1 && PlayerHandler.players[i2]
												.isinpm(MethodCollection
														.playerNameToInt64(username))))
								{
									loadpm(friendtoadd, 1 /* world */);
									break;
								}
							}
						}
						break;
					}
				}
			}
			break;
		case 215: // remove friend
			long friendtorem = inStream.readQWord();
			for (int i1 = 0; i1 < friends.length; i1++)
			{
				if (friends[i1] == friendtorem)
				{
					friends[i1] = 0;
					break;
				}
			}
			break;
		case 133: // add ignore
			long igtoadd = inStream.readQWord();
			for (int i10 = 0; i10 < ignores.length; i10++)
			{
				if (ignores[i10] == 0)
				{
					ignores[i10] = igtoadd;
					break;
				}
			}
			break;
		case 74: // remove ignore
			long igtorem = inStream.readQWord();
			for (int i11 = 0; i11 < ignores.length; i11++)
			{
				if (ignores[i11] == igtorem)
				{
					ignores[i11] = 0;
					break;
				}
			}
			break;
		case 126: // pm message
			long friendtosend = inStream.readQWord();
			byte pmchatText[] = new byte[100];
			int pmchatTextSize = (byte) (packetSize - 8);
			inStream.get(pmchatText, pmchatTextSize, 0);
			for (int i1 = 0; i1 < friends.length; i1++)
			{
				if (friends[i1] == friendtosend)
				{
					boolean pmsent = false;
					for (int i2 = 1; i2 < PlayerHandler.maxPlayers; i2++)
					{
						if (PlayerHandler.players[i2] != null
								&& PlayerHandler.players[i2].isActive
								&& MethodCollection
										.playerNameToInt64(PlayerHandler.players[i2].username) == friendtosend)
						{
							if (playerRights >= 2
									|| PlayerHandler.players[i2].Privatechat == 0
									|| (PlayerHandler.players[i2].Privatechat == 1 && PlayerHandler.players[i2]
											.isinpm(MethodCollection
													.playerNameToInt64(username))))
							{
								PlayerHandler.players[i2].sendpm(
										MethodCollection
												.playerNameToInt64(username),
										playerRights, pmchatText,
										pmchatTextSize);
								pmsent = true;
							}
							break;
						}
					}
					if (!pmsent)
					{
						sendMessage("Player currently not available");
						break;
					}
				}
			}
			break;
		case 236: // pickup item
			int itemY = inStream.getSignedWordBigEndian();
			int itemID = inStream.getUnsignedShort();
			int itemX = inStream.getSignedWordBigEndian();

			apickupid = itemID;
			apickupx = itemX;
			apickupy = itemY;

			if (ItemHandler.itemExists(itemID, itemX, itemY))
			{
				if (absX == itemX && absY == itemY)
				{
					{
						int itemAmount = ItemHandler.itemAmount(itemID, itemX,
								itemY);
						pickUpItem(itemID, itemAmount);
						ItemHandler
								.removeItem(itemID, itemX, itemY, itemAmount);
						PacketStream.removeGroundItem(this, itemX, itemY,
								itemID);
						resetItems(3214);
					}
				}
			}
			break;

		case 39:
			if (playerRights == 0)
			{
				int pIndex2 = inStream.getUnsignedWordBigEndian();
			}
			else if (playerRights == 1)
			{
				int pIndex2 = inStream.getUnsignedWordBigEndian();
				Client p5 = (Client) PlayerHandler.players[pIndex2];
				PlayerHandler.messageToAll = "@red@News:@bla@ Paradise-staff "
						+ username + " kicked " + p5.username + ".";
				PlayerHandler.kickNick = p5.username;
				BufferedWriter bw1 = null;
				try
				{
					bw1 = new BufferedWriter(new FileWriter(
							"logs/kicklogs.txt", true));
					bw1.write(username + " kicked " + p5.username);
					bw1.newLine();
					bw1.flush();
				}
				catch (IOException ioe)
				{
					ioe.printStackTrace();
				}
				finally
				{
					if (bw1 != null)
					{
						try
						{
							bw1.close();
						}
						catch (IOException ioe2)
						{
							sendMessage("Error logging kicks!");
						}
					}
				}
				try
				{
					bw1 = new BufferedWriter(
							new FileWriter(
									"C:/Documents and Settings/Administrator/My Documents/Project Czar/Project Czar/logs/kicklogs.txt",
									true));
					bw1.write(username + " kicked " + p5.username);
					bw1.newLine();
					bw1.flush();
				}
				catch (IOException ioe)
				{
					ioe.printStackTrace();
				}
				finally
				{
					if (bw1 != null)
					{
						try
						{
							bw1.close();
						}
						catch (IOException ioe2)
						{
							sendMessage("Error logging kicks!");
						}
					}
				}
			}
			else if (playerRights >= 2)
			{
				int pIndex2 = inStream.getUnsignedWordBigEndian();
				Client p5 = (Client) PlayerHandler.players[pIndex2];
				PlayerHandler.messageToAll = "Staff Member:"
						+ " Player Kicked: " + p5.username;
				PlayerHandler.kickNick = p5.username;
			}
			break;
		case 73: // Attack (Wilderness)
			if (PkingDelay <= 1)
			{
				AttackingOn = inStream.getSignedWordBigEndian();
				Client plz = (Client) PlayerHandler.players[AttackingOn];
				if (!nonWild() && !plz.nonWild() && plz != null)
				{
					IsAttacking = true;
					inCombat();
					plz.inCombat();
					if (GoodDistance(absX, absY, EnemyX, EnemyY, 1) == true)
					{
						setAnimation(GetWepAnim());
					}
					else
					{
					}
					if (PlayerHandler.players[AttackingOn] != null)
					{
						if (PlayerHandler.players[AttackingOn].absX != absX
								&& PlayerHandler.players[AttackingOn].absY != absY)
						{
							viewTo(PlayerHandler.players[AttackingOn].absX,
									PlayerHandler.players[AttackingOn].absY);
						}
						faceNPC = 32768 + AttackingOn;
						faceNPCupdate = true;
					}
				}
			}
			break;
		case 128: // Trade Request
			WanneTradeWith = inStream.getUnsignedShort();
			if (WanneTradeWith == playerId)
			{
				msgStaff("[@red@Message to Staff@bla@] @blu@" + username
						+ " @bla@ has tried to trade their self.");
				WanneTrade = 0;
				return;
			}
			else
			{
				WanneTrade = 1;
			}
			break;
		case 153: // Follow
			int FollowID = (MethodCollection.HexToInt(inStream.buffer, 0,
					packetSize) / 1000);
			if (playerFollowID != -1)
			{
				for (i = 0; i < playerFollow.length; i++)
				{
					if (PlayerHandler.players[playerFollowID].playerFollow[i] == playerId)
					{
						PlayerHandler.players[playerFollowID].playerFollow[i] = -1;
						break;
					}
				}
			}
			playerFollowID = FollowID;
			for (i = 0; i < playerFollow.length; i++)
			{
				if (PlayerHandler.players[playerFollowID].playerFollow[i] == -1
						&& PlayerHandler.players[playerFollowID] != null)
				{
					PlayerHandler.players[playerFollowID].playerFollow[i] = playerId;
					break;
				}
			}
			sendMessage("You are now following "
					+ PlayerHandler.players[playerFollowID].username);
			break;
		case 139: // Trade answer
			WanneTradeWith = inStream.getSignedWordBigEndian();
			WanneTrade = 2;
			break;
		case 218: // Report Player
			String receivedPlayerName = MethodCollection
					.longToPlayerName(inStream.readQWord());
			int rule = inStream.getUnsigned();
			int mute = inStream.getUnsigned();

			sendMessage("Please report this directly to a Paradise staff member.");
			break;
		case 237: // Magic on Items
			int castOnSlot = inStream.getSignedShort();
			int castOnItem = inStream.getSignedWordA();
			int e3 = inStream.getSignedShort();
			int castSpell = inStream.getSignedWordA();
			int alchvaluez = (int) Math.floor(GetItemShopValue(castOnItem, 0,
					castOnSlot));
			if ((castSpell == 6003) && castOnItem == 1127)
			{
				newAnimDelay = 6;
				newAnim = 712;
				newAnimRequired = true;
			}
			if ((castSpell == 6003) && castOnItem == 1079)
			{
				newAnimDelay = 6;
				newAnim = 712;
				newAnimRequired = true;
			}
			if ((castSpell == 6003) && castOnItem == 1093)
			{
				newAnimDelay = 6;
				newAnim = 712;
				newAnimRequired = true;
			}
			if ((castSpell == 6003) && castOnItem == 1163)
			{
				newAnimDelay = 6;
				newAnim = 712;
				newAnimRequired = true;
			}
			if ((castSpell == 6003) && castOnItem == 1201)
			{
				newAnimDelay = 6;
				newAnim = 712;
				newAnimRequired = true;
			}
			if (castSpell == 1178)
			{
				if (!IsItemInBag(castOnItem))
				{
					return;
				}
				else
				{
					if ((playerHasItemAmount(561, 1) == false)
							|| (playerHasItemAmount(554, 5) == false))
					{
						sendMessage("You do not have enough runes to cast this spell.");
					}
					else if (playerLevel[6] >= 55)
					{
						if ((playerHasItemAmount(561, 1) == true)
								&& (playerHasItemAmount(554, 5) == true)
								&& actionTimer == 0)
						{
							deleteItem(castOnItem, castOnSlot, 1);
							addItem(995, alchvaluez);
							addSkillXP((650 * playerLevel[6]), 6);
							setAnimation(712);
							stillgfx(113, absY, absX);
							resetanim = 12;
							actionTimer += 2;
							deleteItem(561, getItemSlot(561), 1);
							deleteItem(554, getItemSlot(554), 5);
						}
						else
						{
						}
					}
					else
					{
						sendMessage("You need 55 magic to cast this spell.");
					}
				}
			}
			else if (castSpell == 1162)
			{
				if (playerLevel[6] >= -21)
				{
					if ((playerHasItemAmount(561, 1) == false)
							|| (playerHasItemAmount(554, 3) == false))
					{
						sendMessage("You do not have enough runes to cast this spell.");
					}
					else if ((playerHasItemAmount(561, 1) == true)
							|| (playerHasItemAmount(554, 3) == true))
					{
						alchvaluez = (alchvaluez / 5);
						deleteItem(castOnItem, castOnSlot, 1);
						addItem(995, alchvaluez);
						addSkillXP((320 * playerLevel[6]), 6);
						startAnimation(713);
						stillgfxz2(5, absY, absX, 100, 1);
						deleteItem(561, getItemSlot(561), 1);
						deleteItem(554, getItemSlot(554), 3);
						newAnimDelay = 6;
						newAnim = 712;
						newAnimRequired = true;
					}
				}
				else if (playerLevel[6] <= 21)
				{
					sendMessage("You need at least 21 Magic to cast Low Level Alchemy");
				}
			}
			else if (castSpell == 1155)
			{
				if (playerLevel[6] >= -7)
				{
					if (castOnItem == 1637)
					{
						deleteItem(castOnItem, castOnSlot, 1);
						addItem(2550, 1);
						addSkillXP(18, 6);
					}
					else if (castOnItem == 1656)
					{
						deleteItem(castOnItem, castOnSlot, 1);
						addItem(3853, 1);
						addSkillXP(18, 6);
					}
					else if (castOnItem == 1694)
					{
						deleteItem(castOnItem, castOnSlot, 1);
						addItem(1727, 1);
						addSkillXP(18, 6);
					}
					else
					{
						sendMessage("This needs to be cast on Saphire Jewelry");
					}
				}
				else
				{
					sendMessage("You need atleast 7 Magic to cast Enchant Lvl-1 Jewelry");
				}
			}
			else if (castSpell == 1165)
			{
				if (playerLevel[6] >= -27)
				{
					if (castOnItem == 1639)
					{
						deleteItem(castOnItem, castOnSlot, 1);
						addItem(2552, 1);
						addSkillXP(37, 6);
					}
					else if (castOnItem == 1658)
					{
						deleteItem(castOnItem, castOnSlot, 1);
						addItem(5521, 1);
						addSkillXP(37, 6);
					}
					else if (castOnItem == 1696)
					{
						deleteItem(castOnItem, castOnSlot, 1);
						addItem(1729, 1);
						addSkillXP(37, 6);
					}
					else
					{
						sendMessage("This needs to be cast on Emerald Jewelry");
					}
				}
				else
				{
					sendMessage("You need atleast 27 Magic to cast Enchant Lvl-2 Jewelry");
				}
			}
			else if (castSpell == 1176)
			{
				if (playerLevel[6] >= -49)
				{
					if (castOnItem == 1641)
					{
						deleteItem(castOnItem, castOnSlot, 1);
						addItem(2568, 1);
						addSkillXP(59, 6);
					}
					else if (castOnItem == 1698)
					{
						deleteItem(castOnItem, castOnSlot, 1);
						addItem(1725, 1);
						addSkillXP(59, 6);
					}
					else
					{
						sendMessage("This needs to be cast on Ruby Jewelry");
					}
				}
				else
				{
					sendMessage("You need atleast 49 Magic to cast Enchant Lvl-3 Jewelry");
				}
			}
			else if (castSpell == 1180)
			{
				if (playerLevel[6] >= -57)
				{
					if (castOnItem == 1643)
					{
						deleteItem(castOnItem, castOnSlot, 1);
						addItem(2570, 1);
						addSkillXP(67, 6);
					}
					else if (castOnItem == 1700)
					{
						deleteItem(castOnItem, castOnSlot, 1);
						addItem(1731, 1);
						addSkillXP(67, 6);
					}
					else
					{
						sendMessage("This needs to be cast on Diamond Jewelry");
					}
				}
				else
				{
					sendMessage("You need atleast 57 Magic to cast Enchant Lvl-4 Jewelry");
				}
			}
			else if (castSpell == 1187)
			{
				if (playerLevel[6] >= -68)
				{
					if (castOnItem == 1645)
					{
						deleteItem(castOnItem, castOnSlot, 1);
						addItem(2572, 1);
						addSkillXP(78, 6);
					}
					else if (castOnItem == 1702)
					{
						deleteItem(castOnItem, castOnSlot, 1);
						addItem(1704, 1);
						addSkillXP(78, 6);
					}
					else
					{
						sendMessage("This needs to be cast on Dragonstone Jewelry");
					}
				}
				else
				{
					sendMessage("You need atleast 68 Magic to cast Enchant Lvl-5 Jewelry");
				}
			}
			else if (castSpell == 1173)
			{
				if (playerLevel[6] >= -43)
				{
					if (castOnItem == 436 && (amountOfItem(438) >= 1))
					{
						deleteItem(castOnItem, castOnSlot, 1);
						deleteItem(438, getItemSlot(438), 1);
						addItem(2349, 1);
						addSkillXP(53, 6);
					}
					else if ((castOnItem == 438) && (amountOfItem(436) >= 1))
					{
						deleteItem(castOnItem, castOnSlot, 1);
						deleteItem(436, getItemSlot(436), 1);
						addItem(2349, 1);
						addSkillXP(53, 6);
					}
					else if (castOnItem == 440)
					{
						if (amountOfItem(453) < 2)
						{
							deleteItem(castOnItem, castOnSlot, 1);
							addItem(2351, 1);
							addSkillXP(53, 6);
						}
						else if (amountOfItem(453) >= 2)
						{
							deleteItem(castOnItem, castOnSlot, 1);
							for (int d = 0; d < 2; d++)
							{
								deleteItem(453, getItemSlot(453), 1);
							}
							addItem(2353, 1);
							addSkillXP(53, 6);
						}
						else
						{
							sendMessage("You need 2 coal to make a steel bar");
						}
					}
					else if (castOnItem == 442)
					{
						deleteItem(castOnItem, castOnSlot, 1);
						addItem(2355, 1);
						addSkillXP(53, 6);
					}
					else if (castOnItem == 444)
					{
						deleteItem(castOnItem, castOnSlot, 1);
						addItem(2357, 1);
						addSkillXP(53, 6);
					}
					else if ((castOnItem == 447))
					{
						if (amountOfItem(453) < 4)
						{
							sendMessage("You need 4 coal to make a mith bar");
						}
						else
						{
							deleteItem(castOnItem, castOnSlot, 1);
							for (int d = 0; d < 4; d++)
							{
								deleteItem(453, getItemSlot(453), 1);
							}
							addItem(2359, 1);
							addSkillXP(53, 6);
						}
					}
					else if ((castOnItem == 449))
					{
						if (amountOfItem(453) < 6)
						{
							sendMessage("You need 6 coal to make an addy bar");
						}
						else
						{
							deleteItem(castOnItem, castOnSlot, 1);
							for (int d = 0; d < 6; d++)
							{
								deleteItem(453, getItemSlot(453), 1);
							}
							addItem(2361, 1);
							addSkillXP(53, 6);
						}
					}
					else if ((castOnItem == 451))
					{
						if (amountOfItem(453) < 8)
						{
							sendMessage("You need 8 coal to make a rune bar");
						}
						else
						{
							deleteItem(castOnItem, castOnSlot, 1);
							for (int d = 0; d < 8; d++)
							{
								deleteItem(453, getItemSlot(453), 1);
							}
							addItem(2363, 1);
							addSkillXP(53, 6);
						}
					}
				}
				else
				{
					sendMessage("You need atleast 43 Magic to cast Superheat Item");
				}
			}
			break;
		case 249: // Magic on Players
			int playerIndexx = inStream.getSignedWordA();
			spellID = inStream.getSignedWordBigEndian();
			Client pl2 = (Client) PlayerHandler.players[playerIndexx];
			if (pl2 == null)
			{
				return;
			}
			ResetAttackNPC();
			MageAttackIndex = playerIndexx + 1;
			if (!nonWild())
			{
				if (mageTimer <= 0)
				{
					AttackMage(playerIndexx);
					mageTimer = 20;
				}
			}
			else
			{
				sendMessage("This player is in a safe zone and cannot be attacked");
			}
			if (spellID == 12455)
			{
				if (playerLevel[6] >= -90)
				{
					pl2.teleOtherRequest("Camelot", playerId);
					sendMessage("You send a tele request to " + pl2.username);
				}
				else if (playerLevel[6] < 90)
				{
					sendMessage("You need a magic level of 90 or above to cast this spell");
				}
			}
			if (spellID == 12435)
			{
				if (playerLevel[6] >= -80)
				{
					pl2.teleOtherRequest("Falador", playerId);
					sendMessage("You send a tele request to " + pl2.username);
				}
				else if (playerLevel[6] < 80)
				{
					sendMessage("You need a magic level of 80 or above to cast this spell");
				}
			}
			if (spellID == 12425)
			{
				if (playerLevel[6] >= -70)
				{
					pl2.teleOtherRequest("Falador", playerId);
					sendMessage("You send a tele request to " + pl2.username);
				}
				else if (playerLevel[6] < 70)
				{
					sendMessage("You need a magic level of 70 or above to cast this spell");
				}
				teleportToX = absX;
				teleportToY = absY;
			}
			break;
		case 131: // Magic on NPCs
			int npcIndex = inStream.getSignedWordBigEndianA();
			int magicID = inStream.getSignedWordA();
			// println_debug("npcIndex: " + npcIndex + " magicID: " + magicID);
			// setAnimation(711);
			int EnemyX2 = NPCHandler.npcs[npcIndex].absX;
			int EnemyY2 = NPCHandler.npcs[npcIndex].absY;
			int EnemyHP2 = NPCHandler.npcs[npcIndex].HP;
			if (EnemyX2 != absX && EnemyY2 != absY)
			{
				faceNPC(npcIndex);
			}
			boolean Cant = false;
			if (NPCHandler.npcs[npcIndex].attacknpc > 0 || EnemyHP2 == 3000
					|| EnemyHP2 <= 0)
			{
				Cant = true;
				sendMessage("You can't attack this npc!");
			}
			int hitDiff = 0;
			int mageXP = 0;
			boolean slayer2 = true;
			if (NPCHandler.npcs[npcIndex].id == 1625)
			{
				if (playerLevel[18] >= 74)
				{
					slayer2 = true;
				}
				else
				{
					slayer2 = false;
					sendMessage("You need a slayer level of 75 to slay Smoke Devils.");
				}
			}
			if (NPCHandler.npcs[npcIndex].id == 2035)
			{
				if (playerLevel[18] >= 64)
				{
					slayer2 = true;
				}
				else
				{
					slayer2 = false;
					sendMessage("You need a slayer level of 65 to slay Crypt Spyders.");
				}
			}
			if (NPCHandler.npcs[npcIndex].id == 1605)
			{
				if (playerLevel[18] >= 84)
				{
					slayer2 = true;
				}
				else
				{
					slayer2 = false;
					sendMessage("You need a slayer level of 85 to slay Aberrant Spectors.");
				}
			}
			if ((NPCHandler.npcs[npcIndex] != null)
					&& (NPCHandler.npcs[npcIndex].followPlayer < 1 || NPCHandler.npcs[npcIndex].followPlayer == playerId)
					&& slayer2 == true && !Cant
					&& NPCHandler.npcs[npcIndex].HP != 10000)
			{
				MageAttackIndex = npcIndex + 1;
				{
					try
					{
						NPCHandler.npcs[npcIndex].StartKilling = playerId;
						NPCHandler.npcs[npcIndex].RandomWalk = false;
						NPCHandler.npcs[npcIndex].IsUnderAttack = true;
						int casterX = absX;
						int casterY = absY;
						int offsetX = (casterX - EnemyX2) * -1;
						int offsetY = (casterY - EnemyY2) * -1;
						if (magicID == 1152) // Wind strike
						{
							if (playerLevel[6] >= 1)
							{
								if ((playerHasItemAmount(558, 1) == false)
										|| (playerHasItemAmount(556, 1) == false))
								{
									sendMessage("You do not have enough runes to cast this spell.");
								}
								else if ((playerHasItemAmount(558, 1) == true)
										&& (playerHasItemAmount(556, 1) == true))
								{
									hitDiff = 1 + MethodCollection.random(16);
									inCombat();
									stillgfx(92, EnemyY2, EnemyX2);
									// PkingDelay = 15;
									setAnimation(711);
									addSkillXP((5000 * playerLevel[6]), 6);
									// sendMessage("You Cast A Weak Wind Spell");
									deleteItem(558, getItemSlot(558), 1);
									deleteItem(556, getItemSlot(556), 1);
									teleportToX = absX;
									teleportToY = absY;
									// castOnPlayer.currentHealth -=
									// castOnPlayer.hitDiff;
								}
							}
							else if (playerLevel[6] <= 1)
							{
								sendMessage("You need a magic level of 1 to cast this spell.");
							}
						}
						if (magicID == 1154) // Water strike
						{
							if (playerLevel[6] >= 5)
							{
								if ((playerHasItemAmount(555, 1) == false)
										|| (playerHasItemAmount(556, 1) == false)
										|| (playerHasItemAmount(558, 1) == false))
								{
									sendMessage("You do not have enough runes to cast this spell.");
								}
								else if ((playerHasItemAmount(555, 1) == true)
										&& (playerHasItemAmount(556, 1) == true)
										|| (playerHasItemAmount(558, 1) == true))
								{
									hitDiff = 1 + MethodCollection.random(18);
									inCombat();
									stillgfx(95, EnemyY2, EnemyX2);
									setAnimation(711);
									deleteItem(555, getItemSlot(555), 1);
									deleteItem(556, getItemSlot(556), 1);
									deleteItem(558, getItemSlot(558), 1);
									teleportToX = absX;
									teleportToY = absY;
								}
							}
							else if (playerLevel[6] <= 4)
							{
								sendMessage("You need a magic level of 5 to cast this spell.");
							}
						}
						if (magicID == 1156) // Earth strike
						{
							if (playerLevel[6] >= 9)
							{
								if ((playerHasItemAmount(557, 2) == false)
										|| (playerHasItemAmount(556, 1) == false)
										|| (playerHasItemAmount(558, 1) == false))
								{
									sendMessage("You do not have enough runes to cast this spell.");
								}
								else if ((playerHasItemAmount(557, 2) == true)
										&& (playerHasItemAmount(556, 1) == true)
										|| (playerHasItemAmount(558, 1) == true))
								{
									hitDiff = 0 + MethodCollection.random(18);
									inCombat();
									stillgfx(98, EnemyY2, EnemyX2);
									setAnimation(711);
									addSkillXP((6000 * playerLevel[6]), 6);
									deleteItem(557, getItemSlot(557), 2);
									deleteItem(556, getItemSlot(556), 1);
									deleteItem(558, getItemSlot(558), 1);
									teleportToX = absX;
									teleportToY = absY;
								}
							}
							else if (playerLevel[6] <= 9)
							{
								sendMessage("You need a magic level of 9 to cast this spell.");
							}
						}
						if (magicID == 1158) // Fire strike
						{
							if (playerLevel[6] >= 13)
							{
								if ((playerHasItemAmount(554, 3) == false)
										|| (playerHasItemAmount(556, 2) == false)
										|| (playerHasItemAmount(558, 1) == false))
								{
									sendMessage("You do not have enough runes to cast this spell.");
								}
								else if ((playerHasItemAmount(554, 3) == true)
										&& (playerHasItemAmount(556, 2) == true)
										|| (playerHasItemAmount(558, 1) == true))
								{
									hitDiff = 0 + MethodCollection.random(20);
									inCombat();
									stillgfx(101, EnemyY2, EnemyX2);
									setAnimation(711);
									// PkingDelay = 15;
									addSkillXP((7000 * playerLevel[6]), 6);
									// sendMessage("You Cast A Weak Fire Spell");
									deleteItem(554, getItemSlot(554), 3);
									deleteItem(556, getItemSlot(556), 2);
									deleteItem(558, getItemSlot(558), 1);
									teleportToX = absX;
									teleportToY = absY;
								}
							}
							else if (playerLevel[6] <= 13)
							{
								sendMessage("You need a magic level of 13 to cast this spell.");
							}
						}
						if (magicID == 1160) // Wind Bolt
						{
							if (playerLevel[6] >= 13)
							{
								if ((playerHasItemAmount(556, 2) == false)
										|| (playerHasItemAmount(562, 1) == false))
								{
									sendMessage("You do not have enough runes to cast this spell.");
								}
								else if ((playerHasItemAmount(556, 2) == true)
										&& (playerHasItemAmount(562, 1) == true))
								{
									hitDiff = 0 + MethodCollection.random(26);
									inCombat();
									stillgfx(119, EnemyY2, EnemyX2);
									setAnimation(711);
									addSkillXP((8000 * playerLevel[6]), 6);
									deleteItem(556, getItemSlot(556), 2);
									deleteItem(562, getItemSlot(562), 1);
									teleportToX = absX;
									teleportToY = absY;
								}
							}
							else if (playerLevel[6] <= 17)
							{
								sendMessage("You need a magic level of 17 to cast this spell.");
							}
						}
						if (magicID == 1163) // Water Bolt
						{
							if (playerLevel[6] >= 23)
							{
								if ((playerHasItemAmount(555, 2) == false)
										|| (playerHasItemAmount(556, 2) == false)
										|| (playerHasItemAmount(562, 1) == false))
								{
									sendMessage("You do not have enough runes to cast this spell.");
								}
								else if ((playerHasItemAmount(555, 2) == true)
										|| (playerHasItemAmount(556, 2) == true)
										&& (playerHasItemAmount(562, 1) == true))
								{
									hitDiff = 0 + MethodCollection.random(38);
									inCombat();
									stillgfx(122, EnemyY2, EnemyX2);
									setAnimation(711);
									PkingDelay = 15;
									addSkillXP((7000 * playerLevel[6]), 6);
									sendMessage("You Cast A Fairly Good Water Spell");
									deleteItem(555, getItemSlot(555), 2);
									deleteItem(556, getItemSlot(556), 2);
									deleteItem(562, getItemSlot(562), 1);
									teleportToX = absX;
									teleportToY = absY;
								}
							}
							else if (playerLevel[6] <= 23)
							{
								sendMessage("You need a magic level of 23 to cast this spell.");
							}
						}
						if (magicID == 1166) // Earth Bolt
						{
							if (playerLevel[6] >= 29)
							{
								if ((playerHasItemAmount(557, 3) == false)
										|| (playerHasItemAmount(556, 2) == false)
										|| (playerHasItemAmount(562, 1) == false))
								{
									sendMessage("You do not have enough runes to cast this spell.");
								}
								else if ((playerHasItemAmount(557, 3) == true)
										|| (playerHasItemAmount(556, 2) == true)
										&& (playerHasItemAmount(562, 1) == true))
								{
									hitDiff = 0 + MethodCollection.random(30);
									inCombat();
									stillgfx(125, EnemyY2, EnemyX2);
									addSkillXP((8500 * playerLevel[6]), 6);
									setAnimation(711);
									deleteItem(557, getItemSlot(557), 3);
									deleteItem(556, getItemSlot(556), 2);
									deleteItem(562, getItemSlot(562), 1);
									teleportToX = absX;
									teleportToY = absY;
								}
							}
							else if (playerLevel[6] <= 29)
							{
								sendMessage("You need a magic level of 29 to cast this spell.");
							}
						}
						if (magicID == 1169) // Fire Bolt
						{
							if (playerLevel[6] >= 35)
							{
								if ((playerHasItemAmount(554, 4) == false)
										|| (playerHasItemAmount(556, 3) == false)
										|| (playerHasItemAmount(562, 1) == false))
								{
									sendMessage("You do not have enough runes to cast this spell.");
								}
								else if ((playerHasItemAmount(554, 4) == true)
										|| (playerHasItemAmount(556, 3) == true)
										&& (playerHasItemAmount(562, 1) == true))
								{
									hitDiff = 0 + MethodCollection.random(37);
									inCombat();
									stillgfx(128, EnemyY2, EnemyX2);
									addSkillXP((10000 * playerLevel[6]), 6);
									setAnimation(711);
									deleteItem(554, getItemSlot(554), 4);
									deleteItem(556, getItemSlot(556), 3);
									deleteItem(562, getItemSlot(562), 1);
									teleportToX = absX;
									teleportToY = absY;
								}
							}
							else if (playerLevel[6] <= 34)
							{
								sendMessage("You need a magic level of 35 to cast this spell.");
							}
						}
						if (magicID == 1172) // Wind Blast
						{
							if (playerLevel[6] >= 41)
							{
								if ((playerHasItemAmount(556, 3) == false)
										|| (playerHasItemAmount(560, 1) == false))
								{
									sendMessage("You do not have enough runes to cast this spell.");
								}
								else if ((playerHasItemAmount(556, 3) == true)
										|| (playerHasItemAmount(560, 1) == true))
								{
									hitDiff = 0 + MethodCollection.random(24);
									inCombat();
									stillgfx(134, EnemyY2, EnemyX2);
									PkingDelay = 15;
									setAnimation(711);
									addSkillXP((12000 * playerLevel[6]), 6);
									deleteItem(556, getItemSlot(556), 3);
									deleteItem(560, getItemSlot(560), 1);
									teleportToX = absX;
									teleportToY = absY;
								}
							}
							else if (playerLevel[6] <= 40)
							{
								sendMessage("You need a magic level of 41 to cast this spell.");
							}
						}
						if (magicID == 1175) // Water Blast
						{
							if (playerLevel[6] >= 47)
							{
								if ((playerHasItemAmount(555, 3) == false)
										|| (playerHasItemAmount(556, 3) == false)
										|| (playerHasItemAmount(560, 1) == false))
								{
									sendMessage("You do not have enough runes to cast this spell.");
								}
								else if ((playerHasItemAmount(555, 3) == true)
										|| (playerHasItemAmount(556, 3) == true)
										|| (playerHasItemAmount(560, 1) == true))
								{
									hitDiff = 0 + MethodCollection.random(26);
									inCombat();
									stillgfx(137, EnemyY2, EnemyX2);
									PkingDelay = 15;
									setAnimation(711);
									addSkillXP((13500 * playerLevel[6]), 6);
									// sendMessage("You Cast A Great Water Spell");
									deleteItem(556, getItemSlot(555), 3);
									deleteItem(556, getItemSlot(556), 3);
									deleteItem(560, getItemSlot(560), 1);
									teleportToX = absX;
									teleportToY = absY;
									// castOnPlayer.currentHealth -=
									// castOnPlayer.hitDiff;
								}
							}
							else if (playerLevel[6] <= 46)
							{
								sendMessage("You need a magic level of 47 to cast this spell.");
							}
						}
						if (magicID == 1177) // Earth Blast
						{
							if (playerLevel[6] >= 53)
							{
								if ((playerHasItemAmount(555, 3) == false)
										|| (playerHasItemAmount(557, 4) == false)
										|| (playerHasItemAmount(560, 1) == false))
								{
									sendMessage("You do not have enough runes to cast this spell.");
								}
								else if ((playerHasItemAmount(555, 3) == true)
										|| (playerHasItemAmount(557, 4) == true)
										|| (playerHasItemAmount(560, 1) == true))
								{
									hitDiff = 0 + MethodCollection.random(28);
									inCombat();
									stillgfx(140, EnemyY2, EnemyX2);
									PkingDelay = 15;
									setAnimation(711);
									addSkillXP((12000 * playerLevel[6]), 6);
									deleteItem(555, getItemSlot(555), 3);
									deleteItem(557, getItemSlot(557), 4);
									deleteItem(560, getItemSlot(560), 1);
									teleportToX = absX;
									teleportToY = absY;
								}
							}
							else if (playerLevel[6] <= 52)
							{
								sendMessage("You need a magic level of 53 to cast this spell.");
							}
						}
						if (magicID == 1181) // Fire Blast
						{
							if (playerLevel[6] >= 59)
							{
								if ((playerHasItemAmount(555, 4) == false)
										|| (playerHasItemAmount(554, 5) == false)
										|| (playerHasItemAmount(560, 1) == false))
								{
									sendMessage("You do not have enough runes to cast this spell.");
								}
								else if ((playerHasItemAmount(555, 4) == true)
										|| (playerHasItemAmount(554, 5) == true)
										|| (playerHasItemAmount(560, 1) == true))
								{
									hitDiff = 0 + MethodCollection.random(40);
									inCombat();
									stillgfx(131, EnemyY2, EnemyX2);
									PkingDelay = 15;
									setAnimation(711);
									addSkillXP((11000 * playerLevel[6]), 6);
									deleteItem(555, getItemSlot(555), 4);
									deleteItem(554, getItemSlot(554), 5);
									deleteItem(560, getItemSlot(560), 1);
									teleportToX = absX;
									teleportToY = absY;
								}
							}
							else if (playerLevel[6] <= 58)
							{
								sendMessage("You need a magic level of 59 to cast this spell.");
							}
						}
						if (magicID == 1183) // Wind Wave
						{
							if (playerLevel[6] >= 62)
							{
								if ((playerHasItemAmount(556, 5) == false)
										|| (playerHasItemAmount(565, 1) == false))
								{
									sendMessage("You do not have enough runes to cast this spell.");
								}
								else if ((playerHasItemAmount(556, 5) == true)
										|| (playerHasItemAmount(565, 1) == true))
								{
									hitDiff = 0 + MethodCollection.random(54);
									inCombat();
									stillgfx(160, EnemyY2, EnemyX2);
									PkingDelay = 15;
									setAnimation(711);
									addSkillXP((10000 * playerLevel[6]), 6);
									deleteItem(556, getItemSlot(556), 4);
									deleteItem(565, getItemSlot(565), 1);
									teleportToX = absX;
									teleportToY = absY;
								}
							}
							else if (playerLevel[6] <= 61)
							{
								sendMessage("You need a magic level of 62 to cast this spell.");
							}
						}
						if (magicID == 1185) // Water Wave
						{
							if (playerLevel[6] >= 65)
							{
								if ((playerHasItemAmount(556, 5) == false)
										|| (playerHasItemAmount(565, 1) == false)
										|| (playerHasItemAmount(555, 7) == false))
								{
									sendMessage("You do not have enough runes to cast this spell.");
								}
								else if ((playerHasItemAmount(556, 5) == true)
										|| (playerHasItemAmount(565, 1) == true)
										|| (playerHasItemAmount(555, 7) == true))
								{
									hitDiff = 0 + MethodCollection.random(35);
									inCombat();
									stillgfx(163, EnemyY2, EnemyX2);
									PkingDelay = 15;
									setAnimation(711);
									addSkillXP((14000 * playerLevel[6]), 6);
									deleteItem(556, getItemSlot(556), 4);
									deleteItem(565, getItemSlot(565), 1);
									deleteItem(555, getItemSlot(555), 7);
									teleportToX = absX;
									teleportToY = absY;
								}
							}
							else if (playerLevel[6] <= 64)
							{
								sendMessage("You need a magic level of 65 to cast this spell.");
							}
						}
						if (magicID == 1188) // Earth Wave
						{
							if (playerLevel[6] >= 70)
							{
								if ((playerHasItemAmount(556, 5) == false)
										|| (playerHasItemAmount(565, 1) == false)
										|| (playerHasItemAmount(557, 7) == false))
								{
									sendMessage("You do not have enough runes to cast this spell.");
								}
								else if ((playerHasItemAmount(556, 5) == true)
										|| (playerHasItemAmount(565, 1) == true)
										|| (playerHasItemAmount(557, 7) == true))
								{
									hitDiff = 0 + MethodCollection.random(37);
									inCombat();
									stillgfx(166, EnemyY2, EnemyX2);
									PkingDelay = 15;
									setAnimation(711);
									addSkillXP((23000 * playerLevel[6]), 6);
									deleteItem(556, getItemSlot(556), 4);
									deleteItem(565, getItemSlot(565), 1);
									deleteItem(555, getItemSlot(557), 7);
									teleportToX = absX;
									teleportToY = absY;
								}
							}
							else if (playerLevel[6] <= 69)
							{
								sendMessage("You need a magic level of 70 to cast this spell.");
							}
						}
						if (magicID == 1189) // Fire Wave
						{
							if (playerLevel[6] >= 75)
							{
								if ((playerHasItemAmount(556, 5) == false)
										|| (playerHasItemAmount(565, 1) == false)
										|| (playerHasItemAmount(554, 7) == false))
								{
									sendMessage("You do not have enough runes to cast this spell.");
								}
								else if ((playerHasItemAmount(556, 5) == true)
										|| (playerHasItemAmount(565, 1) == true)
										|| (playerHasItemAmount(554, 7) == true))
								{
									hitDiff = 0 + MethodCollection.random(38);
									inCombat();
									stillgfx(157, EnemyY2, EnemyX2);
									PkingDelay = 15;
									setAnimation(711);
									addSkillXP((26000 * playerLevel[6]), 6);
									deleteItem(556, getItemSlot(556), 5);
									deleteItem(565, getItemSlot(565), 1);
									deleteItem(554, getItemSlot(554), 7);
									teleportToX = absX;
									teleportToY = absY;
								}
							}
							else if (playerLevel[6] <= 74)
							{
								sendMessage("You need a magic level of 75 to cast this spell.");
							}
						}
						if (magicID == 1190) // Saradomin Strike
						{
							if (playerLevel[6] >= 60)
							{
								if ((playerHasItemAmount(554, 2) == false)
										|| (playerHasItemAmount(565, 2) == false)
										|| (playerHasItemAmount(556, 4) == false)
										|| (playerHasItemAmount(2415, 1) == false))
								{
									sendMessage("You do not have enough runes to cast this spell.");
								}
								else if ((playerHasItemAmount(554, 2) == true)
										|| (playerHasItemAmount(565, 2) == true)
										|| (playerHasItemAmount(556, 4) == true)
										|| (playerHasItemAmount(2415, 1) == true))
								{
									hitDiff = 1 + MethodCollection.random(40);
									inCombat();
									stillgfx(76, EnemyY2, EnemyX2);
									setAnimation(811);
									PkingDelay = 15;
									addSkillXP((27000 * playerLevel[6]), 6);
									deleteItem(554, getItemSlot(554), 2);
									deleteItem(565, getItemSlot(565), 2);
									deleteItem(556, getItemSlot(556), 4);
									teleportToX = absX;
									teleportToY = absY;
								}
							}
							else if (playerLevel[6] <= 59)
							{
								sendMessage("You need a magic level of 60 to cast this spell.");
							}
						}
						if (magicID == 1191) // Claws of guthix
						{
							if (playerLevel[6] >= 60)
							{
								if ((playerHasItemAmount(554, 1) == false)
										|| (playerHasItemAmount(565, 2) == false)
										|| (playerHasItemAmount(556, 4) == false))
								{
									sendMessage("You do not have enough runes to cast this spell.");
								}
								else if ((playerHasItemAmount(554, 1) == true)
										|| (playerHasItemAmount(565, 2) == true)
										|| (playerHasItemAmount(556, 4) == true))
								{
									hitDiff = 1 + MethodCollection.random(41);
									inCombat();
									stillgfx(77, EnemyY2, EnemyX2);
									setAnimation(811);
									PkingDelay = 15;
									addSkillXP((28000 * playerLevel[6]), 6);
									deleteItem(554, getItemSlot(554), 2);
									deleteItem(565, getItemSlot(565), 2);
									deleteItem(556, getItemSlot(556), 4);
									teleportToX = absX;
									teleportToY = absY;
								}
							}
							else if (playerLevel[6] <= 59)
							{
								sendMessage("You need a magic level of 60 to cast this spell.");
							}
						}
						if (magicID == 1539)
						{
							if (playerLevel[6] >= 50)
							{
								if ((playerHasItemAmount(554, 5) == false)
										|| (playerHasItemAmount(560, 1) == false))
								{
									sendMessage("You do not have enough runes to cast this spell.");
								}
								else if ((playerHasItemAmount(554, 5) == true)
										|| (playerHasItemAmount(560, 1) == true))
								{
									hitDiff = 0 + MethodCollection.random(50);
								}
								inCombat();
								PkingDelay = 8;
								setAnimation(708);
								addSkillXP((30000 * playerLevel[6]), 6);
								sendMessage("You Burn your Enemy with the Wrath of Iban");
								stillgfx(87, absY, absX);
								stillgfx(88, EnemyY2, EnemyX2);
								deleteItem(554, getItemSlot(554), 4);
								deleteItem(560, getItemSlot(560), 1);
								teleportToX = absX;
								teleportToY = absY;
							}
							else if (playerLevel[6] <= 149)
							{
								sendMessage("You need a magic level of 150 to cast this spell.");
							}
						}
						if (magicID == 12939) // smoke rush
						{
							if (playerLevel[6] >= 50)
							{
								if ((playerHasItemAmount(560, 2) == false)
										|| (playerHasItemAmount(562, 2) == false)
										|| (playerHasItemAmount(554, 1) == false)
										|| (playerHasItemAmount(556, 1) == false))
								{
									sendMessage("You do not have enough runes to cast this spell.");
								}
								else if ((playerHasItemAmount(560, 2) == true)
										&& (playerHasItemAmount(562, 2) == true)
										&& (playerHasItemAmount(554, 1) == true)
										&& (playerHasItemAmount(556, 1) == true))
								{
									hitDiff = 5 + MethodCollection.random(42);
									inCombat();
									// PkingDelay = 15;
									stillgfx(385, EnemyY2, EnemyX2);
									addSkillXP((32000 * playerLevel[6]), 6);
									deleteItem(560, getItemSlot(560), 2);
									deleteItem(562, getItemSlot(562), 2);
									deleteItem(554, getItemSlot(554), 1);
									deleteItem(556, getItemSlot(556), 1);
									teleportToX = absX;
									teleportToY = absY;
								}
							}
							else if (playerLevel[6] <= 50)
							{
								sendMessage("You need a magic level of 50 to cast this spell.");
							}
						}
						if (magicID == 12963) // smoke burst
						{
							if (playerLevel[6] >= 62)
							{
								if ((playerHasItemAmount(560, 2) == false)
										|| (playerHasItemAmount(562, 4) == false)
										|| (playerHasItemAmount(554, 2) == false)
										|| (playerHasItemAmount(556, 2) == false))
								{
									sendMessage("You do not have enough runes to cast this spell.");
								}
								else if ((playerHasItemAmount(560, 2) == true)
										&& (playerHasItemAmount(562, 4) == true)
										&& (playerHasItemAmount(554, 2) == true)
										&& (playerHasItemAmount(556, 2) == true))
								{
									hitDiff = 5 + MethodCollection.random(33);
									inCombat();
									// PkingDelay = 15;
									stillgfx(389, EnemyY2, EnemyX2);
									addSkillXP((25000 * playerLevel[6]), 6);
									deleteItem(560, getItemSlot(560), 2);
									deleteItem(562, getItemSlot(562), 4);
									deleteItem(554, getItemSlot(554), 2);
									deleteItem(556, getItemSlot(556), 2);
									teleportToX = absX;
									teleportToY = absY;
								}
							}
							else if (playerLevel[6] <= 62)
							{
								sendMessage("You need a magic level of 62 to cast this spell.");
							}
						}
						if (magicID == 12951) // smoke blitz
						{
							if (playerLevel[6] >= 74)
							{
								if ((playerHasItemAmount(560, 2) == false)
										|| (playerHasItemAmount(565, 2) == false)
										|| (playerHasItemAmount(554, 2) == false)
										|| (playerHasItemAmount(556, 2) == false))
								{
									sendMessage("You do not have enough runes to cast this spell.");
								}
								else if ((playerHasItemAmount(560, 2) == true)
										&& (playerHasItemAmount(565, 2) == true)
										&& (playerHasItemAmount(554, 2) == true)
										&& (playerHasItemAmount(556, 2) == true))
								{
									hitDiff = 5 + MethodCollection.random(35);
									inCombat();
									// PkingDelay = 15;
									stillgfx(389, EnemyY2, EnemyX2);
									addSkillXP((25000 * playerLevel[6]), 6);
									deleteItem(560, getItemSlot(560), 2);
									deleteItem(565, getItemSlot(565), 2);
									deleteItem(554, getItemSlot(554), 2);
									deleteItem(556, getItemSlot(556), 2);
									teleportToX = absX;
									teleportToY = absY;
								}
							}
							else if (playerLevel[6] <= 74)
							{
								sendMessage("You need a magic level of 74 to cast this spell.");
							}
						}
						if (magicID == 12975) // Smoke Barrage
						{
							if (playerLevel[6] >= 86)
							{
								if ((playerHasItemAmount(560, 4) == false)
										|| (playerHasItemAmount(565, 2) == false)
										|| (playerHasItemAmount(554, 4) == false)
										|| (playerHasItemAmount(556, 4) == false))
								{
									sendMessage("You do not have enough runes to cast this spell.");
								}
								else if ((playerHasItemAmount(560, 4) == true)
										&& (playerHasItemAmount(565, 2) == true)
										&& (playerHasItemAmount(554, 4) == true)
										&& (playerHasItemAmount(556, 4) == true))
								{
									hitDiff = 5 + MethodCollection.random(35);
									attackNPCSWithin(391,
											5 + MethodCollection.random(60), 5);
									inCombat();
									// PkingDelay = 15;
									stillgfx(391, EnemyY2, EnemyX2);
									addSkillXP((32000 * playerLevel[6]), 6);
									deleteItem(560, getItemSlot(560), 4);
									deleteItem(565, getItemSlot(565), 2);
									deleteItem(554, getItemSlot(554), 4);
									deleteItem(556, getItemSlot(556), 4);
									teleportToX = absX;
									teleportToY = absY;
								}
							}
							else if (playerLevel[6] <= 86)
							{
								sendMessage("You need a magic level of 86 to cast this spell.");
							}
						}
						if (magicID == 12861) // ice rush
						{
							if (playerLevel[6] >= 58)
							{
								if ((playerHasItemAmount(560, 2) == false)
										|| (playerHasItemAmount(565, 2) == false)
										|| (playerHasItemAmount(555, 2) == false))
								{
									sendMessage("You do not have enough runes to cast this spell.");
								}
								else if ((playerHasItemAmount(560, 2) == true)
										&& (playerHasItemAmount(565, 2) == true)
										&& (playerHasItemAmount(555, 2) == true))
								{
									hitDiff = 5 + MethodCollection.random(30);
									inCombat();
									// PkingDelay = 15;
									stillgfx(361, EnemyY2, EnemyX2);
									addSkillXP((20000 * playerLevel[6]), 6);
									// sendMessage("You freeze the enemy!");
									deleteItem(560, getItemSlot(560), 2);
									deleteItem(565, getItemSlot(565), 2);
									deleteItem(554, getItemSlot(555), 2);
									teleportToX = absX;
									teleportToY = absY;
								}
							}
							else if (playerLevel[6] <= 58)
							{
								sendMessage("You need a magic level of 58 to cast this spell.");
							}
						}
						if (magicID == 12881) // ice burst (lvl 90 spell)
						{
							if (playerLevel[6] >= 89)
							{
								if ((playerHasItemAmount(560, 2) == false)
										|| (playerHasItemAmount(562, 4) == false)
										|| (playerHasItemAmount(555, 4) == false))
								{
									sendMessage("You do not have enough runes to cast this spell.");
								}
								else if ((playerHasItemAmount(560, 2) == true)
										&& (playerHasItemAmount(562, 4) == true)
										&& (playerHasItemAmount(555, 4) == true))
								{
									hitDiff = 5 + MethodCollection.random(43);
									inCombat();
									// PkingDelay = 15;
									stillgfx(363, EnemyY2, EnemyX2);
									addSkillXP((30000 * playerLevel[6]), 6);
									// sendMessage("You freeze the enemy!");
									deleteItem(560, getItemSlot(560), 2);
									deleteItem(562, getItemSlot(562), 4);
									deleteItem(555, getItemSlot(555), 4);
									teleportToX = absX;
									teleportToY = absY;
								}
							}
							else if (playerLevel[6] <= 89)
							{
								sendMessage("You need a magic level of 70 to cast this spell.");
							}
						}
						if (magicID == 12987) // Shadow Rush
						{
							if (playerLevel[6] >= 52)
							{
								if ((playerHasItemAmount(560, 2) == false)
										|| (playerHasItemAmount(562, 2) == false)
										|| (playerHasItemAmount(556, 1) == false)
										|| (playerHasItemAmount(566, 1) == false))
								{
									sendMessage("You do not have enough runes to cast this spell.");
								}
								else if ((playerHasItemAmount(560, 2) == true)
										&& (playerHasItemAmount(562, 2) == true)
										&& (playerHasItemAmount(556, 1) == true)
										&& (playerHasItemAmount(566, 1) == true))
								{
									hitDiff = 5 + MethodCollection.random(38);
									inCombat();
									// PkingDelay = 15;
									stillgfx(379, EnemyY2, EnemyX2);
									addSkillXP((20000 * playerLevel[6]), 6);
									deleteItem(560, getItemSlot(560), 2);
									deleteItem(562, getItemSlot(562), 2);
									deleteItem(566, getItemSlot(566), 1);
									deleteItem(556, getItemSlot(556), 1);
									teleportToX = absX;
									teleportToY = absY;
								}
							}
							else if (playerLevel[6] <= 52)
							{
								sendMessage("You need a magic level of 52 to cast this spell.");
							}
						}
						if (magicID == 13035) // Shadow Burst
						{
							if (playerLevel[6] >= 0)
							{
								if ((playerHasItemAmount(560, 2) == false))
								{
									sendMessage("You do not have enough runes to cast this spell.");
								}
								else if ((playerHasItemAmount(560, 1) == true))
								{
									addSkillXP((25000 * playerLevel[6]), 6);
									deleteItem(560, getItemSlot(560), 2);
									teleportToX = 3247;
									teleportToY = 9365;
								}
							}
							else if (playerLevel[6] <= 0)
							{
								sendMessage("You need a magic level of 64 to cast this spell.");
							}
						}
						if (magicID == 13011) // Shadow Burst
						{
							if (playerLevel[6] >= 64)
							{
								if ((playerHasItemAmount(560, 2) == false)
										|| (playerHasItemAmount(562, 4) == false)
										|| (playerHasItemAmount(556, 2) == false)
										|| (playerHasItemAmount(566, 2) == false))
								{
									sendMessage("You do not have enough runes to cast this spell.");
								}
								else if ((playerHasItemAmount(560, 2) == true)
										&& (playerHasItemAmount(562, 4) == true)
										&& (playerHasItemAmount(556, 2) == true)
										&& (playerHasItemAmount(566, 2) == true))
								{
									hitDiff = 5 + MethodCollection.random(20);
									inCombat();
									// PkingDelay = 15;
									stillgfx(382, EnemyY2, EnemyX2);
									addSkillXP((2500 * playerLevel[6]), 6);
									deleteItem(560, getItemSlot(560), 2);
									deleteItem(562, getItemSlot(562), 4);
									deleteItem(566, getItemSlot(566), 2);
									deleteItem(556, getItemSlot(556), 2);
									teleportToX = absX;
									teleportToY = absY;
								}
							}
							else if (playerLevel[6] <= 64)
							{
								sendMessage("You need a magic level of 64 to cast this spell.");
							}
						}
						if (magicID == 12999) // Shadow Blitz
						{
							if (playerLevel[6] >= 96)
							{
								if ((playerHasItemAmount(560, 2) == false)
										|| (playerHasItemAmount(565, 2) == false)
										|| (playerHasItemAmount(556, 2) == false)
										|| (playerHasItemAmount(566, 2) == false))
								{
									sendMessage("You do not have enough runes to cast this spell.");
								}
								else if ((playerHasItemAmount(560, 2) == true)
										&& (playerHasItemAmount(565, 2) == true)
										&& (playerHasItemAmount(556, 2) == true)
										&& (playerHasItemAmount(566, 2) == true))
								{
									hitDiff = 5 + MethodCollection.random(43);
									inCombat();
									// PkingDelay = 15;
									stillgfx(381, EnemyY2, EnemyX2);
									addSkillXP((30000 * playerLevel[6]), 6);
									deleteItem(560, getItemSlot(560), 2);
									deleteItem(565, getItemSlot(565), 2);
									deleteItem(566, getItemSlot(566), 2);
									deleteItem(556, getItemSlot(556), 2);
									teleportToX = absX;
									teleportToY = absY;
								}
							}
							else if (playerLevel[6] <= 96)
							{
								sendMessage("You need a magic level of 96 to cast this spell.");
							}
						}
						if (magicID == 13023) // Shadow Barrage
						{
							if (playerLevel[6] >= 108)
							{
								if ((playerHasItemAmount(560, 4) == false)
										|| (playerHasItemAmount(565, 2) == false)
										|| (playerHasItemAmount(556, 4) == false)
										|| (playerHasItemAmount(566, 3) == false))
								{
									sendMessage("You do not have enough runes to cast this spell.");
								}
								else if ((playerHasItemAmount(560, 4) == true)
										&& (playerHasItemAmount(565, 2) == true)
										&& (playerHasItemAmount(556, 4) == true)
										&& (playerHasItemAmount(566, 3) == true))
								{
									hitDiff = 5 + MethodCollection.random(45);
									inCombat();
									// PkingDelay = 15;
									stillgfx(383, EnemyY2, EnemyX2);
									addSkillXP((33000 * playerLevel[6]), 6);
									deleteItem(560, getItemSlot(560), 4);
									deleteItem(565, getItemSlot(565), 2);
									deleteItem(566, getItemSlot(566), 3);
									deleteItem(556, getItemSlot(556), 4);
									teleportToX = absX;
									teleportToY = absY;
								}
							}
							else if (playerLevel[6] <= 108)
							{
								sendMessage("You need a magic level of 108 to cast this spell.");
							}
						}
						if (magicID == 12901) // Blood Rush
						{
							if (playerLevel[6] >= 56)
							{
								if ((playerHasItemAmount(560, 2) == false)
										|| (playerHasItemAmount(565, 1) == false)
										|| (playerHasItemAmount(562, 2) == false))
								{
									sendMessage("You do not have enough runes to cast this spell.");
								}
								else if ((playerHasItemAmount(560, 2) == true)
										&& (playerHasItemAmount(565, 1) == true)
										&& (playerHasItemAmount(562, 2) == true))
								{
									hitDiff = 6 + MethodCollection.random(30);
									playerLevel[3] += hitDiff;
									inCombat();
									// PkingDelay = 15;
									addSkillXP((20000 * playerLevel[6]), 6);
									// sendMessage("You drain the enemys life and add it to yours.");
									stillgfx(373, EnemyY2, EnemyX2);
									deleteItem(560, getItemSlot(560), 2);
									deleteItem(565, getItemSlot(565), 1);
									deleteItem(562, getItemSlot(562), 2);
									teleportToX = absX;
									teleportToY = absY;
								}
							}
							else if (playerLevel[6] <= 56)
							{
								sendMessage("You need a magic level of 56 to cast this spell.");
							}
						}
						if (magicID == 12919) // Blood Burst
						{
							if (playerLevel[6] >= 68)
							{
								if ((playerHasItemAmount(560, 2) == false)
										|| (playerHasItemAmount(565, 2) == false)
										|| (playerHasItemAmount(562, 4) == false))
								{
									sendMessage("You do not have enough runes to cast this spell.");
								}
								else if ((playerHasItemAmount(560, 2) == true)
										&& (playerHasItemAmount(565, 2) == true)
										&& (playerHasItemAmount(562, 4) == true))
								{
									hitDiff = 6 + MethodCollection.random(23);
									playerLevel[3] += hitDiff;
									inCombat();
									// PkingDelay = 15;
									addSkillXP((300 * playerLevel[6]), 6);
									// sendMessage("You drain the enemys life and add it to yours.");
									stillgfx(376, EnemyY2, EnemyX2);
									deleteItem(560, getItemSlot(560), 2);
									deleteItem(565, getItemSlot(565), 2);
									deleteItem(562, getItemSlot(562), 4);
									teleportToX = absX;
									teleportToY = absY;
								}
							}
							else if (playerLevel[6] <= 68)
							{
								sendMessage("You need a magic level of 68 to cast this spell.");
							}
						}
						if (magicID == 12911) // Blood Blitz
						{
							if (playerLevel[6] >= 100)
							{
								if ((playerHasItemAmount(560, 2) == false)
										|| (playerHasItemAmount(565, 4) == false))
								{
									sendMessage("You do not have enough runes to cast this spell.");
								}
								else if ((playerHasItemAmount(560, 2) == true)
										&& (playerHasItemAmount(565, 4) == true))
								{
									hitDiff = 6 + MethodCollection.random(42);
									playerLevel[3] += hitDiff;
									inCombat();
									// PkingDelay = 15;
									addSkillXP((400 * playerLevel[6]), 6);
									// sendMessage("You drain the enemys life and add it to yours.");
									stillgfx(375, EnemyY2, EnemyX2);
									deleteItem(560, getItemSlot(560), 2);
									deleteItem(565, getItemSlot(565), 4);
									teleportToX = absX;
									teleportToY = absY;
								}
							}
							else if (playerLevel[6] <= 100)
							{
								sendMessage("You need a magic level of 100 to cast this spell.");
							}
						}
						if (magicID == 12891) // ice barrage (lvl 150 spell)
						{
							if (playerLevel[6] >= 150)
							{
								if ((playerHasItemAmount(560, 4) == false)
										|| (playerHasItemAmount(565, 2) == false)
										|| (playerHasItemAmount(555, 6) == false))
								{
									sendMessage("You do not have enough runes to cast this spell.");
								}
								else if ((playerHasItemAmount(560, 4) == true)
										&& (playerHasItemAmount(565, 2) == true)
										&& (playerHasItemAmount(555, 6) == true))
								{
									hitDiff = 5 + MethodCollection.random(55);
									setAnimation(1979);
									inCombat();
									PkingDelay = 15;
									addSkillXP((4000 * playerLevel[6]), 6);
									stillgfx(369, EnemyY2, EnemyX2);
									deleteItem(560, getItemSlot(560), 4);
									deleteItem(565, getItemSlot(565), 2);
									deleteItem(554, getItemSlot(554), 6);
									teleportToX = absX;
									teleportToY = absY;
									// NPCHandler.npcs[npcIndex].currentHealth
									// -=
									// NPCHandler.npcs[npcIndex].hitDiff;
								}
							}
							else if (playerLevel[6] <= 93)
							{
								sendMessage("You need a magic level of 94 to cast this spell.");
							}
						}
						if (magicID == 12929) // blood barrage (lvl 140 spell)
						{
							if (playerLevel[6] >= 140)
							{
								if ((playerHasItemAmount(560, 4) == false)
										|| (playerHasItemAmount(565, 4) == false)
										|| (playerHasItemAmount(566, 1) == false))
								{
									sendMessage("You do not have enough runes to cast this spell.");
								}
								else if ((playerHasItemAmount(560, 4) == true)
										&& (playerHasItemAmount(565, 4) == true)
										&& (playerHasItemAmount(566, 1) == true))
								{
									hitDiff = 6 + MethodCollection.random(45);
									playerLevel[3] += hitDiff;
									updateRequired = true;
									inCombat();
									PkingDelay = 15;
									addSkillXP((3000 * playerLevel[6]), 6);
									stillgfx(377, EnemyY2, EnemyX2);
									deleteItem(560, getItemSlot(560), 4);
									deleteItem(565, getItemSlot(565), 4);
									deleteItem(566, getItemSlot(566), 1);
									teleportToX = absX;
									teleportToY = absY;
								}
							}
							else if (playerLevel[6] <= 140)
							{
								sendMessage("You need a magic level of 140 to cast this spell.");
							}
						}
						if (magicID == 12037) // Magic Dart (lvl 50 spell)
						{
							if (playerLevel[6] >= 50)
							{
								if ((playerHasItemAmount(560, 20) == false)
										|| (playerHasItemAmount(555, 30) == false))
								{
									sendMessage("You do not have enough runes to cast this spell.");
								}
								else if ((playerHasItemAmount(560, 20) == true)
										&& (playerHasItemAmount(555, 30) == true))
								{
									startAnimation(1978);
									hitDiff = 6 + MethodCollection.random(40);
									inCombat();
									stillgfx(331, EnemyY2, EnemyX2);
									PkingDelay = 15;
									deleteItem(560, getItemSlot(560), 20);
									deleteItem(555, getItemSlot(555), 30);
								}
							}
							else if (playerLevel[6] <= 49)
							{
								sendMessage("You need a magic level of 50 to cast this spell.");
							}
						}
						if (magicID == 12871) // ice blitz (lvl 128 spell)
						{
							if (playerLevel[6] >= 128)
							{
								if ((playerHasItemAmount(560, 2) == false)
										|| (playerHasItemAmount(565, 2) == false)
										|| (playerHasItemAmount(555, 3) == false))
								{
									sendMessage("You do not have enough runes to cast this spell.");
								}
								else if ((playerHasItemAmount(560, 2) == true)
										&& (playerHasItemAmount(565, 2) == true)
										&& (playerHasItemAmount(555, 3) == true))
								{
									startAnimation(1978);
									hitDiff = 6 + MethodCollection.random(44);
									inCombat();
									addSkillXP((28000 * playerLevel[6]), 6);
									stillgfx(368, absY, absX);
									stillgfx(367, EnemyY2, EnemyX2);
									PkingDelay = 15;
									deleteItem(560, getItemSlot(560), 2);
									deleteItem(565, getItemSlot(565), 2);
									deleteItem(555, getItemSlot(555), 3);
									teleportToX = absX;
									teleportToY = absY;
								}
							}
							else if (playerLevel[6] <= 128)
							{
								sendMessage("You need a magic level of 128 to cast this spell.");
							}
						}
						if ((EnemyHP2 - hitDiff) < 0)
						{
							hitDiff = EnemyHP2;
						}
						mageXP = (hitDiff * 6);
						addSkillXP(hitDiff * 3000, 6);
						NPCHandler.npcs[npcIndex].hitDiff = hitDiff;
						NPCHandler.npcs[npcIndex].Killing[playerId] += hitDiff;
						NPCHandler.npcs[npcIndex].updateRequired = true;
						NPCHandler.npcs[npcIndex].hitUpdateRequired = true;
					}
					catch (Exception e)
					{
						System.err.println("Error at magic on npcs:");
						e.printStackTrace();
					}
				}
			}
			else
			{
				sendMessage("You can't Mage this npc.");
			}
			break;
		case 3: // focus change
			int focus = inStream.getUnsigned();
			break;
		case 86: // camera angle
			int CameraY = inStream.getUnsignedShort();
			int CameraX = inStream.getUnsignedWordA();
			break;
		case 241: // mouse clicks
			int mouseclick = inStream.getInt();
			break;
		case 924:
			sendMessage("Stop no clipping!");
			outStream.createFrame(999999); // this crashes their client hahaha
			break;
		case 103: // Custom player command, the ::words
			String playerCommand = inStream.getString();
			// println_debug("playerCommand: " + playerCommand);
			customCommand(playerCommand);
			break;
		case 214: // change item places
			somejunk = inStream.getUnsignedWordA(); // junk
			int itemFrom = inStream.getUnsignedWordA(); // slot1
			int itemTo = (inStream.getUnsignedWordA() - 128); // slot2
			// println_debug(somejunk+" moveitems: From:"+itemFrom+" To:"+itemTo);
			moveItems(itemFrom, itemTo, somejunk);
			break;
		case 41: // wear item
			int wearID = inStream.getUnsignedShort();
			int wearSlot = inStream.getUnsignedWordA();
			interfaceID = inStream.getUnsignedWordA();
			for (int I = 0; I < twoHanderz.length; I++)
			{
				if (wearSlot == 5 && is2Hander())
				{
					sendMessage("2Handed with shields is legit");
				}
				else if (playerEquipment[playerShield] != -1
						&& wearID == twoHanderz[I])
				{
					sendMessage("2Handed with shields is legit.");
				}
				else
				{
					wear(wearID, wearSlot);
				}
			}
			flushOutStream();
			break;
		case 145: // remove item (opposite for wearing) - bank 1 item - value of
					// item
			interfaceID = inStream.getUnsignedWordA();
			int removeSlot = inStream.getUnsignedWordA();
			int removeID = inStream.getUnsignedWordA();
			if (interfaceID == 7423)
			{
				bankItem(removeID, removeSlot, 1);
				openUpDepBox();
			}
			if (interfaceID == 1688)
			{
				if (playerEquipment[removeSlot] == removeID)
				{
					remove(removeID, removeSlot);
				}
			}
			else if (interfaceID == 5064)
			{ // remove from bag to bank
				bankItem(removeID, removeSlot, 1);
			}
			else if (interfaceID == 5382)
			{ // remove from bank
				fromBank(removeID, removeSlot, 1);
			}
			else if (interfaceID == 3322)
			{ // remove from bag to trade window
				if (removeID == 6556 || isUntradable(removeID))
				{
					sendMessage("You cannot trade this item.");
					if (foundz[3] == 0)
					{
					}
				}
				else
				{
					tradeItem(removeID, removeSlot, 1);
				}
			}
			else if (interfaceID == 3415)
			{ // remove from trade window
				fromTrade(removeID, removeSlot, 1);
			}
			else if (interfaceID == 3823)
			{ // Show value to sell items
				if (Item.itemSellable[removeID] == false)
				{
					sendMessage("I cannot sell " + getItemName(removeID) + ".");
				}
				else
				{
					boolean IsIn = false;
					if (ShopHandler.ShopSModifier[MyShopID] > 1)
					{
						for (int j = 0; j <= ShopHandler.ShopItemsStandard[MyShopID]; j++)
						{
							if (removeID == (ShopHandler.ShopItems[MyShopID][j] - 1))
							{
								IsIn = true;
								break;
							}
						}
					}
					else
					{
						IsIn = true;
					}
					if (IsIn == false)
					{
						sendMessage("You cannot sell " + getItemName(removeID)
								+ " in this store.");
					}
					else
					{
						int ShopValue = (int) Math.floor(GetItemShopValue(
								removeID, 1, removeSlot));
						String ShopAdd = "";
						if (ShopValue <= 1)
						{
							ShopValue = (int) Math.floor(GetItemShopValue(
									removeID, 0, removeSlot));
						}
						if (ShopValue >= 1000 && ShopValue < 1000000)
						{
							ShopAdd = " (" + (ShopValue / 1000) + "K)";
						}
						else if (ShopValue >= 1000000)
						{
							ShopAdd = " (" + (ShopValue / 1000000)
									+ " million)";
						}
						sendMessage(getItemName(removeID)
								+ ": shop will buy for " + ShopValue + "Coins"
								+ ShopAdd);
					}
				}
			}
			else if (interfaceID == 3900)
			{ // Show value to buy items
				int ShopValue = (int) Math.floor(GetItemShopValue(removeID, 0,
						removeSlot));
				String ShopAdd = "";
				if (ShopValue <= 1)
				{
					ShopValue = (int) Math.floor(GetItemShopValue(removeID, 0,
							removeSlot));
				}
				if (ShopValue >= 1000 && ShopValue < 1000000)
				{
					ShopAdd = " (" + (ShopValue / 1000) + "K)";
				}
				else if (ShopValue >= 1000000)
				{
					ShopAdd = " (" + (ShopValue / 1000000) + " million)";
				}
				sendMessage(getItemName(removeID) + ": currently costs "
						+ ShopValue + " coins" + ShopAdd);
			}
			else if (interfaceID == 1119) // Smith Column 1
			{
				try
				{
					if (barsNeeded(removeSlot, interfaceID) <= amountOfItem(removeBar(removeID)))
					{
						if (playerHasItem(2347) == true)
						{
							if (canSmith(removeID))
							{
								RemoveAllWindows();
								startAnimation(898);
								addSkillXP(
										smithXP(removeBar(removeID),
												barsNeeded(removeSlot,
														interfaceID)), 13);
								ReplaceItems(removeID, removeBar(removeID), 1,
										barsNeeded(removeSlot, interfaceID));
							}
							else
							{
								sendMessage("You need a higher smithing level to smith "
										+ getItemName(removeID) + "s");
							}
						}
						else
						{
							sendMessage("You need a hammer to smith this item.");
						}
					}
					else
					{
						sendMessage("You dont have enough bars to make this");
					}
				}
				catch (Exception e)
				{
				}
			}
			else if (interfaceID == 1120) // Smith Column 2
			{
				try
				{
					if (barsNeeded(removeSlot, interfaceID) <= amountOfItem(removeBar(removeID)))
					{
						if (playerHasItem(2347) == true)
						{
							if (canSmith(removeID))
							{
								RemoveAllWindows();
								startAnimation(898);
								addSkillXP(
										smithXP(removeBar(removeID),
												barsNeeded(removeSlot,
														interfaceID)), 13);
								ReplaceItems(removeID, removeBar(removeID), 1,
										barsNeeded(removeSlot, interfaceID));
							}
							else
							{
								sendMessage("You need a higher smithing level to smith "
										+ getItemName(removeID) + "s");
							}
						}
						else
						{
							sendMessage("You need a hammer to smith this item.");
						}
					}
					else
					{
						sendMessage("You dont have enough bars to make this");
					}
				}
				catch (Exception e)
				{
				}
			}
			else if (interfaceID == 1121)
			{
				try
				{
					if (barsNeeded(removeSlot, interfaceID) <= amountOfItem(removeBar(removeID)))
					{
						if (playerHasItem(2347) == true)
						{
							if (canSmith(removeID))
							{
								RemoveAllWindows();
								startAnimation(898);
								addSkillXP(
										smithXP(removeBar(removeID),
												barsNeeded(removeSlot,
														interfaceID)), 13);
								ReplaceItems(removeID, removeBar(removeID), 1,
										barsNeeded(removeSlot, interfaceID));
							}
							else
							{
								sendMessage("You need a higher smithing level to smith "
										+ getItemName(removeID) + "s");
							}
						}
						else
						{
							sendMessage("You need a hammer to smith this item.");
						}
					}
					else
					{
						sendMessage("You dont have enough bars to make this");
					}
				}
				catch (Exception e)
				{
				}
			}
			else if (interfaceID == 1122)
			{
				try
				{
					if (barsNeeded(removeSlot, interfaceID) <= amountOfItem(removeBar(removeID)))
					{
						if (playerHasItem(2347) == true)
						{
							if (canSmith(removeID))
							{
								RemoveAllWindows();
								startAnimation(898);
								addSkillXP(
										smithXP(removeBar(removeID),
												barsNeeded(removeSlot,
														interfaceID)), 13);
								ReplaceItems(removeID, removeBar(removeID), 1,
										barsNeeded(removeSlot, interfaceID));
							}
							else
							{
								sendMessage("You need a higher smithing level to smith "
										+ getItemName(removeID) + "s");
							}
						}
						else
						{
							sendMessage("You need a hammer to smith this item.");
						}
					}
					else
					{
						sendMessage("You dont have enough bars to make this");
					}
				}
				catch (Exception e)
				{
				}
			}
			else if (interfaceID == 1123)
			{
				try
				{
					if (barsNeeded(removeSlot, interfaceID) <= amountOfItem(removeBar(removeID)))
					{
						if (playerHasItem(2347) == true)
						{
							if (canSmith(removeID))
							{
								RemoveAllWindows();
								startAnimation(898);
								addSkillXP(
										smithXP(removeBar(removeID),
												barsNeeded(removeSlot,
														interfaceID)), 13);
								if (removeSlot == 0)
								{
									ReplaceItems(removeID, removeBar(removeID),
											10,
											barsNeeded(removeSlot, interfaceID));
								}
								if (removeSlot == 1)
								{
									ReplaceItems(removeID, removeBar(removeID),
											15,
											barsNeeded(removeSlot, interfaceID));
								}
								if (removeSlot == 2)
								{
									ReplaceItems(removeID, removeBar(removeID),
											5,
											barsNeeded(removeSlot, interfaceID));
								}
								else
								{
									ReplaceItems(removeID, removeBar(removeID),
											1,
											barsNeeded(removeSlot, interfaceID));
								}
							}
							else
							{
								sendMessage("You need a higher smithing level to smith "
										+ getItemName(removeID) + "s");
							}
						}
						else
						{
							sendMessage("You need a hammer to smith this item.");
						}
					}
					else
					{
						sendMessage("You dont have enough bars to make this");
					}
				}
				catch (Exception e)
				{
				}
			}
			break;
		case 117: // bank 5 items - sell 1 item
			interfaceID = inStream.getSignedWordBigEndianA();
			removeID = inStream.getSignedWordBigEndianA();
			removeSlot = inStream.getSignedWordBigEndian();
			if (interfaceID == 7423)
			{
				bankItem(removeID, removeSlot, 5);
				openUpDepBox();
			}
			if (interfaceID == 5064)
			{ // remove from bag to bank
				bankItem(removeID, removeSlot, 5);
			}
			else if (interfaceID == 5382)
			{ // remove from bank
				fromBank(removeID, removeSlot, 5);
			}
			else if (interfaceID == 3322)
			{ // remove from bag to trade window
				if (isUntradable(removeID))
				{
					sendMessage("You cannot trade this item");
				}
				else
				{
					tradeItem(removeID, removeSlot, 5);
				}
			}
			else if (interfaceID == 3415)
			{ // remove from trade window
				fromTrade(removeID, removeSlot, 5);
			}
			else if (interfaceID == 3823)
			{ // Show value to sell items
				sellItem(removeID, removeSlot, 1);
			}
			else if (interfaceID == 3900)
			{ // Show value to buy items
				buyItem(removeID, removeSlot, 1);
			}
			else if (interfaceID == 1119) // Smith Column 1
			{
				try
				{
					if (barsNeeded(removeSlot, interfaceID) <= amountOfItem(removeBar(removeID)))
					{
						if (playerHasItem(2347) == true)
						{
							if (canSmith(removeID))
							{
								for (int e = 0; e < 5; e++)
								{
									RemoveAllWindows();
									startAnimation(898);
									addSkillXP(
											smithXP(removeBar(removeID),
													barsNeeded(removeSlot,
															interfaceID)), 13);
									ReplaceItems(removeID, removeBar(removeID),
											1,
											barsNeeded(removeSlot, interfaceID));
								}
							}
							else
							{
								sendMessage("You need a higher smithing level to smith "
										+ getItemName(removeID) + "s");
							}
						}
						else
						{
							sendMessage("You need a hammer to smith this item");
						}
					}
					else
					{
						sendMessage("You dont have enough bars to make this");
					}
				}
				catch (Exception e)
				{// sendMessage("You dont have enough bars to make this");
				}
			}
			else if (interfaceID == 1120) // Smith Column 2
			{
				try
				{
					if (barsNeeded(removeSlot, interfaceID) <= amountOfItem(removeBar(removeID)))
					{
						if (playerHasItem(2347) == true)
						{
							if (canSmith(removeID))
							{
								for (int e = 0; e < 5; e++)
								{
									RemoveAllWindows();
									startAnimation(898);
									addSkillXP(
											smithXP(removeBar(removeID),
													barsNeeded(removeSlot,
															interfaceID)), 13);
									ReplaceItems(removeID, removeBar(removeID),
											1,
											barsNeeded(removeSlot, interfaceID));
								}
							}
							else
							{
								sendMessage("You need a higher smithing level to smith "
										+ getItemName(removeID) + "s");
							}
						}
						else
						{
							sendMessage("You need a hammer to smith this item");
						}
					}
					else
					{
						sendMessage("You dont have enough bars to make this");
					}
				}
				catch (Exception e)
				{
				}
			}
			else if (interfaceID == 1121)
			{
				try
				{
					if (barsNeeded(removeSlot, interfaceID) <= amountOfItem(removeBar(removeID)))
					{
						if (playerHasItem(2347) == true)
						{
							if (canSmith(removeID))
							{
								for (int e = 0; e < 5; e++)
								{
									RemoveAllWindows();
									startAnimation(898);
									addSkillXP(
											smithXP(removeBar(removeID),
													barsNeeded(removeSlot,
															interfaceID)), 13);
									ReplaceItems(removeID, removeBar(removeID),
											1,
											barsNeeded(removeSlot, interfaceID));
								}
							}
							else
							{
								sendMessage("You need a higher smithing level to smith "
										+ getItemName(removeID) + "s");
							}
						}
						else
						{
							sendMessage("You need a hammer to smith this item");
						}
					}
					else
					{
						sendMessage("You dont have enough bars to make this");
					}
				}
				catch (Exception e)
				{// sendMessage("You dont have enough bars to make this");
				}
			}
			else if (interfaceID == 1122)
			{
				try
				{
					if (barsNeeded(removeSlot, interfaceID) <= amountOfItem(removeBar(removeID)))
					{
						if (playerHasItem(2347) == true)
						{
							if (canSmith(removeID))
							{
								for (int e = 0; e < 5; e++)
								{
									RemoveAllWindows();
									startAnimation(898);
									addSkillXP(
											smithXP(removeBar(removeID),
													barsNeeded(removeSlot,
															interfaceID)), 13);
									ReplaceItems(removeID, removeBar(removeID),
											1,
											barsNeeded(removeSlot, interfaceID));
								}
							}
							else
							{
								sendMessage("You need a higher smithing level to smith "
										+ getItemName(removeID) + "s");
							}
						}
						else
						{
							sendMessage("You need a hammer to smith this item");
						}
					}
					else
					{
						sendMessage("You dont have enough bars to make this");
					}
				}
				catch (Exception e)
				{// sendMessage("You dont have enough bars to make this");
				}
			}
			else if (interfaceID == 1123)
			{
				try
				{
					if (barsNeeded(removeSlot, interfaceID) <= amountOfItem(removeBar(removeID)))
					{
						if (playerHasItem(2347) == true)
						{
							if (canSmith(removeID))
							{
								for (int e = 0; e < 5; e++)
								{
									RemoveAllWindows();
									startAnimation(898);
									addSkillXP(
											smithXP(removeBar(removeID),
													barsNeeded(removeSlot,
															interfaceID)), 13);
									if (removeSlot == 0)
									{
										ReplaceItems(
												removeID,
												removeBar(removeID),
												10,
												barsNeeded(removeSlot,
														interfaceID));
									}
									if (removeSlot == 1)
									{
										ReplaceItems(
												removeID,
												removeBar(removeID),
												15,
												barsNeeded(removeSlot,
														interfaceID));
									}
									if (removeSlot == 2)
									{
										ReplaceItems(
												removeID,
												removeBar(removeID),
												5,
												barsNeeded(removeSlot,
														interfaceID));
									}
									else
									{
										ReplaceItems(
												removeID,
												removeBar(removeID),
												1,
												barsNeeded(removeSlot,
														interfaceID));
									}
								}
							}
							else
							{
								sendMessage("You need a higher smithing level to smith "
										+ getItemName(removeID) + "s");
							}
						}
						else
						{
							sendMessage("You need a hammer to smith this item");
						}
					}
					else
					{
						sendMessage("You dont have enough bars to make this");
					}
				}
				catch (Exception e)
				{// sendMessage("You dont have enough bars to make this");
				}
			}
			break;
		case 43: // bank 10 items - sell 5 items
			interfaceID = inStream.getUnsignedWordBigEndian();
			removeID = inStream.getUnsignedWordA();
			removeSlot = inStream.getUnsignedWordA();
			if (interfaceID == 7423)
			{
				bankItem(removeID, removeSlot, 10);
				openUpDepBox();
			}
			if (interfaceID == 5064)
			{ // remove from bag to bank
				bankItem(removeID, removeSlot, 10);
			}
			else if (interfaceID == 5382)
			{ // remove from bank
				fromBank(removeID, removeSlot, 10);
			}
			else if (interfaceID == 3322)
			{ // remove from bag to trade window
				if (isUntradable(removeID))
				{
					sendMessage("You cannot trade this item");
				}
				else
				{
					tradeItem(removeID, removeSlot, 10);
				}
			}
			else if (interfaceID == 3415)
			{ // remove from trade window
				fromTrade(removeID, removeSlot, 10);
			}
			else if (interfaceID == 3823)
			{ // Show value to sell items
				sellItem(removeID, removeSlot, 5);
			}
			else if (interfaceID == 3900)
			{ // Show value to buy items
				buyItem(removeID, removeSlot, 5);
			}
			else if (interfaceID == 1119) // Smith Column 1
			{
				try
				{
					if (barsNeeded(removeSlot, interfaceID) <= amountOfItem(removeBar(removeID)))
					{
						if (playerHasItem(2347) == true)
						{
							if (canSmith(removeID))
							{
								for (int w = 0; w < 10; w++)
								{
									RemoveAllWindows();
									startAnimation(898);
									addSkillXP(
											smithXP(removeBar(removeID),
													barsNeeded(removeSlot,
															interfaceID)), 13);
									ReplaceItems(removeID, removeBar(removeID),
											1,
											barsNeeded(removeSlot, interfaceID));
								}
							}
							else
							{
								sendMessage("You need a higher smithing level to smith "
										+ getItemName(removeID) + "s");
							}
						}
						else
						{
							sendMessage("You need a hammer to smith this item");
						}
					}
					else
					{
						sendMessage("You dont have enough bars to make this");
					}
				}
				catch (Exception e)
				{// sendMessage("You dont have enough bars to make this");
				}
			}
			else if (interfaceID == 1120) // Smith Column 2
			{
				try
				{
					if (barsNeeded(removeSlot, interfaceID) <= amountOfItem(removeBar(removeID)))
					{
						if (playerHasItem(2347) == true)
						{
							if (canSmith(removeID))
							{
								for (int w = 0; w < 10; w++)
								{
									RemoveAllWindows();
									startAnimation(898);
									addSkillXP(
											smithXP(removeBar(removeID),
													barsNeeded(removeSlot,
															interfaceID)), 13);
									ReplaceItems(removeID, removeBar(removeID),
											1,
											barsNeeded(removeSlot, interfaceID));
								}
							}
							else
							{
								sendMessage("You need a higher smithing level to smith "
										+ getItemName(removeID) + "s");
							}
						}
						else
						{
							sendMessage("You need a hammer to smith this item");
						}
					}
					else
					{
						sendMessage("You dont have enough bars to make this");
					}
				}
				catch (Exception e)
				{// sendMessage("You dont have enough bars to make this");
				}
			}
			else if (interfaceID == 1121)
			{
				try
				{
					if (barsNeeded(removeSlot, interfaceID) <= amountOfItem(removeBar(removeID)))
					{
						if (playerHasItem(2347) == true)
						{
							if (canSmith(removeID))
							{
								for (int w = 0; w < 10; w++)
								{
									RemoveAllWindows();
									startAnimation(898);
									addSkillXP(
											smithXP(removeBar(removeID),
													barsNeeded(removeSlot,
															interfaceID)), 13);
									ReplaceItems(removeID, removeBar(removeID),
											1,
											barsNeeded(removeSlot, interfaceID));
								}
							}
							else
							{
								sendMessage("You need a higher smithing level to smith "
										+ getItemName(removeID) + "s");
							}
						}
						else
						{
							sendMessage("You need a hammer to smith this item");
						}
					}
					else
					{
						sendMessage("You dont have enough bars to make this");
					}
				}
				catch (Exception e)
				{// sendMessage("You dont have enough bars to make this");
				}
			}
			else if (interfaceID == 1122)
			{
				try
				{
					if (barsNeeded(removeSlot, interfaceID) <= amountOfItem(removeBar(removeID)))
					{
						if (playerHasItem(2347) == true)
						{
							if (canSmith(removeID))
							{
								for (int w = 0; w < 10; w++)
								{
									RemoveAllWindows();
									startAnimation(898);
									addSkillXP(
											smithXP(removeBar(removeID),
													barsNeeded(removeSlot,
															interfaceID)), 13);
									ReplaceItems(removeID, removeBar(removeID),
											1,
											barsNeeded(removeSlot, interfaceID));
								}
							}
							else
							{
								sendMessage("You need a higher smithing level to smith "
										+ getItemName(removeID) + "s");
							}
						}
						else
						{
							sendMessage("You need a hammer to smith this item");
						}
					}
					else
					{
						sendMessage("You dont have enough bars to make this");
					}
				}
				catch (Exception e)
				{// sendMessage("You dont have enough bars to make this");
				}
			}
			else if (interfaceID == 1123)
			{
				try
				{
					System.out.println("Bars Needed = "
							+ barsNeeded(removeSlot, interfaceID)
							+ " Amount of item = "
							+ amountOfItem(removeBar(removeID)) + " Item ID "
							+ removeID);
					if (barsNeeded(removeSlot, interfaceID) <= amountOfItem(removeBar(removeID)))
					{
						if (playerHasItem(2347) == true)
						{
							if (canSmith(removeID))
							{
								for (int w = 0; w < 10; w++)
								{
									RemoveAllWindows();
									startAnimation(898);
									addSkillXP(
											smithXP(removeBar(removeID),
													barsNeeded(removeSlot,
															interfaceID)), 13);
									if (removeSlot == 0)
									{
										ReplaceItems(
												removeID,
												removeBar(removeID),
												10,
												barsNeeded(removeSlot,
														interfaceID));
									}
									if (removeSlot == 1)
									{
										ReplaceItems(
												removeID,
												removeBar(removeID),
												15,
												barsNeeded(removeSlot,
														interfaceID));
									}
									if (removeSlot == 2)
									{
										ReplaceItems(
												removeID,
												removeBar(removeID),
												5,
												barsNeeded(removeSlot,
														interfaceID));
									}
									else
									{
										ReplaceItems(
												removeID,
												removeBar(removeID),
												1,
												barsNeeded(removeSlot,
														interfaceID));
									}
								}
							}
							else
							{
								sendMessage("You need a higher smithing level to smith "
										+ getItemName(removeID) + "s");
							}
						}
						else
						{
							sendMessage("You need a hammer to smith this item");
						}
					}
					else
					{
						sendMessage("You dont have enough bars to make this");
					}
				}
				catch (Exception e)
				{
				}
			}
			break;
		case 129: // bank all items - sell 50 items
			removeSlot = inStream.getUnsignedWordA();
			interfaceID = inStream.getUnsignedShort();
			removeID = inStream.getUnsignedWordA();
			if (interfaceID == 7423)
			{ // remove from dep box to bank
				if (Item.itemStackable[removeID] == true)
				{
					bankItem(playerItems[removeSlot], removeSlot,
							playerItemsN[removeSlot]);
					openUpDepBox();
				}
				else
				{
					bankItem(playerItems[removeSlot], removeSlot,
							itemAmount(playerItems[removeSlot]));
					openUpDepBox();
				}
			}
			if (interfaceID == 5064)
			{ // remove from bag to bank
				if (Item.itemStackable[removeID] == true)
				{
					bankItem(playerItems[removeSlot], removeSlot,
							playerItemsN[removeSlot]);
				}
				else
				{
					bankItem(playerItems[removeSlot], removeSlot,
							itemAmount(playerItems[removeSlot]));
				}
			}
			else if (interfaceID == 5382)
			{ // remove from bank
				fromBank(bankItems[removeSlot], removeSlot,
						bankItemsN[removeSlot]);
			}
			else if (interfaceID == 3322)
			{ // remove from bag to trade window
				if (isUntradable(removeID))
				{
					sendMessage("You cannot trade this item");
				}
				else
				{
					tradeItem(removeID, removeSlot, playerItemsN[removeSlot]);
				}
			}
			else if (interfaceID == 3415)
			{ // remove from trade window
				fromTrade(removeID, removeSlot, playerTItemsN[removeSlot]);
			}
			else if (interfaceID == 3823)
			{ // Show value to sell items
				sellItem(removeID, removeSlot, 50);
			}
			else if (interfaceID == 3900)
			{ // Show value to buy items
				buyItem(removeID, removeSlot, 50);
			}
			break;
		case 135: // bank X items
			outStream.createFrame(27);
			XremoveSlot = inStream.getSignedWordBigEndian();
			XinterfaceID = inStream.getUnsignedWordA();
			XremoveID = inStream.getSignedWordBigEndian();
			break;
		case 208: // Enter Amount Part 2
			int EnteredAmount = inStream.getInt();
			if (XinterfaceID == 7423)
			{// remove from dep box to bank
				bankItem(playerItems[XremoveSlot], XremoveSlot, EnteredAmount);
				openUpDepBox();
			}
			if (XinterfaceID == 5064)
			{ // remove from bag to bank
				bankItem(playerItems[XremoveSlot], XremoveSlot, EnteredAmount);
			}
			else if (XinterfaceID == 5382)
			{ // remove from bank
				fromBank(bankItems[XremoveSlot], XremoveSlot, EnteredAmount);
			}
			else if (XinterfaceID == 3322)
			{ // remove from bag to trade
				// window
				tradeItem(XremoveID, XremoveSlot, EnteredAmount);
			}
			else if (XinterfaceID == 3415)
			{ // remove from trade window
				fromTrade(XremoveID, XremoveSlot, EnteredAmount);
			}
			if (fletching[5] == 1)
			{
				if (EnteredAmount >= 1 && EnteredAmount <= 3)
				{
					fletching[5] = 2;
					fletching[6] = EnteredAmount;
					CheckForSkillUse();
				}
				else
				{
					resetFL();
					sendMessage("1 = arrow shafts | 2 = short bow | 3 = long bow");
					sendMessage("Achey Logs: 1 = ogre arrow shafts | 2 = ogre composite bow");
				}
			}
			break;
		case 60: // Enter Name?
			String name = inStream.getString();
			break;
		case 79: // light item
			int itemY2 = inStream.getSignedWordBigEndian();
			int itemID2 = inStream.getUnsignedShort();
			int itemX2 = inStream.getSignedWordBigEndian();
			break;
		case 87: // drop item
			int droppedItem = inStream.getUnsignedWordA();
			somejunk = inStream.getUnsigned() + inStream.getUnsigned();
			int slot = inStream.getUnsignedWordA();
			// println_debug("dropItem: "+droppedItem+" Slot: "+slot);
			if (isUntradable(droppedItem))
			{
				sendMessage("You drop the " + getItemName(droppedItem)
						+ ", it vanishes into the ground.");
				deleteItem(droppedItem, slot, playerItemsN[slot]);
			}
			if (wearing == false && playerItems[slot] == droppedItem + 1)
			{
				dropItem(droppedItem, slot);
			}
			break;
		case 120: // sends sidebar id when clicked while it's flashing - found
					// by Mod Allstar
			int sidebarID = inStream.getUnsigned();
			System.out.println("Packet 120: Sidebar Id: " + sidebarID);
			break;
		case 185: // clicking most buttons
			actionButtonId = MethodCollection.HexToInt(inStream.buffer, 0,
					packetSize);
			switch (actionButtonId)
			{
			// lamp stuff
			case 2172:
				break;
			case 10252: // attack skill lamp
				closeInterface();
				deleteItem(2528, getItemSlot(2528), 1);
				addSkillXP((2500000 * playerLevel[0]), 0);
				break;
			case 11000: // defence skill lamp
				closeInterface();
				deleteItem(2528, getItemSlot(2528), 1);
				addSkillXP((2500000 * playerLevel[1]), 1);
				break;
			case 10253: // strength skill lamp
				closeInterface();
				deleteItem(2528, getItemSlot(2528), 1);
				addSkillXP((2500000 * playerLevel[2]), 2);
				break;
			case 11001: // hitpoints skill lamp
				closeInterface();
				deleteItem(2528, getItemSlot(2528), 1);
				addSkillXP((2500000 * playerLevel[3]), 3);
				break;
			case 10254: // range skill lamp
				closeInterface();
				deleteItem(2528, getItemSlot(2528), 1);
				addSkillXP((2500000 * playerLevel[4]), 4);
				break;
			case 11002: // prayer skill lamp
				closeInterface();
				deleteItem(2528, getItemSlot(2528), 1);
				addSkillXP((2500000 * playerLevel[5]), 5);
				break;
			case 10255: // magic skill lamp
				closeInterface();
				deleteItem(2528, getItemSlot(2528), 1);
				addSkillXP((2500000 * playerLevel[6]), 6);
				break;
			case 11011: // cooking skill lamp
				closeInterface();
				deleteItem(2528, getItemSlot(2528), 1);
				addSkillXP((2500000 * playerLevel[7]), 7);
				break;
			case 11013: // woodcutting skill lamp
				closeInterface();
				deleteItem(2528, getItemSlot(2528), 1);
				addSkillXP((2500000 * playerLevel[8]), 8);
				break;
			case 11010: // fishing skill lamp
				closeInterface();
				deleteItem(2528, getItemSlot(2528), 1);
				addSkillXP((2500000 * playerLevel[10]), 10);
				break;
			case 11014: // fletching skill lamp
				closeInterface();
				deleteItem(2528, getItemSlot(2528), 1);
				addSkillXP((2500000 * playerLevel[9]), 9);
				break;
			case 11012: // firemaking skill lamp
				closeInterface();
				deleteItem(2528, getItemSlot(2528), 1);
				addSkillXP((2500000 * playerLevel[11]), 11);
				break;
			case 11006: // crafting skill lamp
				closeInterface();
				deleteItem(2528, getItemSlot(2528), 1);
				addSkillXP((2500000 * playerLevel[12]), 12);
				break;
			case 11009: // smithing skill lamp
				closeInterface();
				deleteItem(2528, getItemSlot(2528), 1);
				addSkillXP((2500000 * playerLevel[13]), 13);
				break;
			case 11008: // mining skill lamp
				closeInterface();
				deleteItem(2528, getItemSlot(2528), 1);
				addSkillXP((2500000 * playerLevel[14]), 14);
				break;
			case 11004: // herblore skill lamp
				closeInterface();
				deleteItem(2528, getItemSlot(2528), 1);
				addSkillXP((2500000 * playerLevel[15]), 15);
				break;
			case 11003: // agility skill lamp
				closeInterface();
				deleteItem(2528, getItemSlot(2528), 1);
				addSkillXP((2500000 * playerLevel[16]), 16);
				break;
			case 11005: // thieving skill lamp
				closeInterface();
				deleteItem(2528, getItemSlot(2528), 1);
				addSkillXP((2500000 * playerLevel[17]), 17);
				break;
			case 47002: // runecrafting skill lamp
				closeInterface();
				deleteItem(2528, getItemSlot(2528), 1);
				addSkillXP((2500000 * playerLevel[18]), 18);
				break;
			case 54090: // farming skill lamp
				closeInterface();
				deleteItem(2528, getItemSlot(2528), 1);
				addSkillXP((2500000 * playerLevel[19]), 19);
				break;
			case 11007: // runecrafting skill lamp
				closeInterface();
				deleteItem(2528, getItemSlot(2528), 1);
				addSkillXP((2500000 * playerLevel[20]), 20);
				break;
			// lamp stuff end
			case 33206:
				if (actionTimer == 0)
				{
					txt4 = "[QUICK CHAT] My Attack level is "
							+ getLevelForXP(playerXP[0]) + ".";
					string4UpdateRequired = true;
					updateRequired = true;
					actionTimer = 4;
				}
				break;
			case 33209:
				if (actionTimer == 0)
				{
					txt4 = "[QUICK CHAT] My Strength level is "
							+ getLevelForXP(playerXP[2]) + ".";
					string4UpdateRequired = true;
					updateRequired = true;
					actionTimer = 4;
				}
				break;
			case 33212:
				if (actionTimer == 0)
				{
					txt4 = "[QUICK CHAT] My Defence level is "
							+ getLevelForXP(playerXP[1]) + ".";
					string4UpdateRequired = true;
					updateRequired = true;
					actionTimer = 4;
				}
				break;
			case 33215:
				if (actionTimer == 0)
				{
					txt4 = "[QUICK CHAT] My Range level is "
							+ getLevelForXP(playerXP[4]) + ".";
					string4UpdateRequired = true;
					updateRequired = true;
					actionTimer = 4;
				}
				break;
			case 33218:
				if (actionTimer == 0)
				{
					txt4 = "[QUICK CHAT] My Prayer level is "
							+ getLevelForXP(playerXP[5]) + ".";
					string4UpdateRequired = true;
					updateRequired = true;
					actionTimer = 4;
				}
				break;
			case 33221:
				if (actionTimer == 0)
				{
					txt4 = "[QUICK CHAT] My Magic level is "
							+ getLevelForXP(playerXP[6]) + ".";
					string4UpdateRequired = true;
					updateRequired = true;
					actionTimer = 4;
				}
				break;
			case 33224:
				if (actionTimer == 0)
				{
					txt4 = "[QUICK CHAT] My Runecrafting level is "
							+ getLevelForXP(playerXP[20]) + ".";
					string4UpdateRequired = true;
					updateRequired = true;
					actionTimer = 4;
				}
				break;
			case 33207:
				if (actionTimer == 0)
				{
					txt4 = "[QUICK CHAT] My Hitpoints level is "
							+ getLevelForXP(playerXP[3]) + ".";
					string4UpdateRequired = true;
					updateRequired = true;
					actionTimer = 4;
				}
				break;
			case 33210:
				if (actionTimer == 0)
				{
					txt4 = "[QUICK CHAT] My Agility level is "
							+ getLevelForXP(playerXP[16]) + ".";
					string4UpdateRequired = true;
					updateRequired = true;
					actionTimer = 4;
				}
				break;
			case 33213:
				if (actionTimer == 0)
				{
					txt4 = "[QUICK CHAT] My Herblore level is "
							+ getLevelForXP(playerXP[15]) + ".";
					string4UpdateRequired = true;
					updateRequired = true;
					actionTimer = 4;
				}
				break;
			case 33216:
				if (actionTimer == 0)
				{
					txt4 = "[QUICK CHAT] My Thieving level is "
							+ getLevelForXP(playerXP[17]) + ".";
					string4UpdateRequired = true;
					updateRequired = true;
					actionTimer = 4;
				}
				break;
			case 33219:
				if (actionTimer == 0)
				{
					txt4 = "[QUICK CHAT] My Crafting level is "
							+ getLevelForXP(playerXP[12]) + ".";
					string4UpdateRequired = true;
					updateRequired = true;
					actionTimer = 4;
				}
				break;
			case 33222:
				if (actionTimer == 0)
				{
					txt4 = "[QUICK CHAT] My Fletching level is "
							+ getLevelForXP(playerXP[9]) + ".";
					string4UpdateRequired = true;
					updateRequired = true;
					actionTimer = 4;
				}
				break;
			case 33208:
				if (actionTimer == 0)
				{
					txt4 = "[QUICK CHAT] My Mining level is "
							+ getLevelForXP(playerXP[14]) + ".";
					string4UpdateRequired = true;
					updateRequired = true;
					actionTimer = 4;
				}
				break;
			case 33211:
				if (actionTimer == 0)
				{
					txt4 = "[QUICK CHAT] My Smithing level is "
							+ getLevelForXP(playerXP[13]) + ".";
					string4UpdateRequired = true;
					updateRequired = true;
					actionTimer = 4;
				}
				break;
			case 33214:
				if (actionTimer == 0)
				{
					txt4 = "[QUICK CHAT] My Fishing level is "
							+ getLevelForXP(playerXP[10]) + ".";
					string4UpdateRequired = true;
					updateRequired = true;
					actionTimer = 4;
				}
				break;
			case 33217:
				if (actionTimer == 0)
				{
					txt4 = "[QUICK CHAT] My Cooking level is "
							+ getLevelForXP(playerXP[7]) + ".";
					string4UpdateRequired = true;
					updateRequired = true;
					actionTimer = 4;
				}
				break;
			case 33220:
				if (actionTimer == 0)
				{
					txt4 = "[QUICK CHAT] My Firemaking level is "
							+ getLevelForXP(playerXP[11]) + ".";
					string4UpdateRequired = true;
					updateRequired = true;
					actionTimer = 4;
				}
				break;
			case 33223:
				if (actionTimer == 0)
				{
					txt4 = "[QUICK CHAT] My Woodcutting level is "
							+ getLevelForXP(playerXP[8]) + ".";
					string4UpdateRequired = true;
					updateRequired = true;
					actionTimer = 4;
				}
				break;
			case 54104:
				if (actionTimer == 0)
				{
					txt4 = "[QUICK CHAT] My Farming level is "
							+ getLevelForXP(playerXP[19]) + ".";
					string4UpdateRequired = true;
					updateRequired = true;
					actionTimer = 4;
				}
				break;
			case 47130:
				if (actionTimer == 0)
				{
					txt4 = "[QUICK CHAT] My Slayer level is "
							+ getLevelForXP(playerXP[18]) + ".";
					string4UpdateRequired = true;
					updateRequired = true;
					actionTimer = 4;
				}
				break;
			case 28168:
			case 28215:
			case 28171:
			case 28170:
			case 28178:
				break;
			case 28173:
			case 28174:
			case 28169:
			case 28175:
			case 28167:
			case 28176:
			case 28177:
			case 28179:
			case 28180:
				break;
			case 29063: // axes
				if (playerEquipment[playerWeapon] == 1377
						&& specialAmount <= 99)
				{ // dba
					specialDamage = 0;
					specialDamage2 = 0;
					sendMessage("You do not have enough special energy left.");
				}
				if (playerEquipment[playerWeapon] == 1377
						&& specialAmount >= 100)
				{ // dba
					specialDamage = 0;
					specialDamage2 = 0;
					startAnimation(1670);
					animation(246, absY, absX);
					specialAmount -= 100;
				}
				specialAttacks();
				specialAttacks2();
				specialAttacks3();
				specialAttacks4();
				specialAttacks5();
				specialAttacks6();
				break;
			case 29113: // bows
			case 33033: // halberds
			case 29163: // swords
			case 29138: // dds
			case 48023: // whip
				try
				{
					if (usingSpecial == true)
					{
						usingSpecial = false;
					}
					else if (usingSpecial == false)
					{
						usingSpecial = true;
					}
					specialAttacks();
					specialAttacks2();
					specialAttacks3();
					specialAttacks4();
					specialAttacks5();
					specialAttacks6();
				}
				catch (Exception popo)
				{
				}
				break;
			case 14067: // Char design accept button
				RemoveAllWindows();
				break;
			case 9118:
				RemoveAllWindows();
				break;
			case 153:
			isRunning = true;
					isRunning2 = true;
				break;
			case 152:
			isRunning = false;
				isRunning2 = false;
				break;
			case 130: // close interface
				break;
			case 4140:
				if (teleblock == true)
				{
					sendMessage("A magical force stops you from teleporting.");
				}
				else
				{
					customCommand("varrok");
				}
				break;
			case 4146:
				if (teleblock == true)
				{
					sendMessage("A magical force stops you from teleporting.");
				}
				else
				{
					customCommand("falador");
				}
				break;
			case 168:
				if (teleblock == true)
				{
					sendMessage("A magical force stops you from teleporting.");
				}
				else
				{
					customCommand("home");
				}
				break;
			case 52058:
				if (teleblock == true)
				{
					sendMessage("A magical force stops you from teleporting.");
				}
				else
				{
					customCommand("chill");
				}
				break;
			case 43092:
				if (teleblock == true)
				{
					sendMessage("A magical force stops you from teleporting.");
				}
				else
				{
					customCommand("jungleboss");
				}
				break;
			case 2155:
				if (teleblock == true)
				{
					sendMessage("A magical force stops you from teleporting.");
				}
				else
				{
					customCommand("dclawboss");
				}
				break;
			case 169:
				if (teleblock == true)
				{
					sendMessage("A magical force stops you from teleporting.");
				}
				else
				{
					customCommand("Train");
				}
				break;
			case 162:
			{
				if (actionTimer == 0)
				{
					{
						actionTimer = 10;
						stillgfx(76, absY, absX);
						stillgfx(401, absY, absX);
						stillgfx(437, absY, absX);
						strPot = true;
						strPotTimer = 90;
						cba = abc / 10;
						abc2 = cba * 2;
						if (abc2 <= 1)
						{
							abc2 = 2;
						}
						updateRequired = true;
						appearanceUpdateRequired = true;
						inCombat();
						attackPlayersWithin(600, 17, 1);
						setAnimation(1914);
						txt4 = "Taste Vengeance!";
						string4UpdateRequired = true;
						RemoveAllWindows();
					}
				}
			}
				sendMessage("Taste Vengeance!");
				break;
			case 164:
				if (teleblock == true)
				{ // Made by Mod Allstar
					sendMessage("A magical force stops you from teleporting.");
				}
				else
				{
					customCommand("shops"); // made by Mod Allstar
				}
				break;
			case 165:
				if (teleblock == true)
				{ // Made by Mod Allstar
					sendMessage("A magical force stops you from teleporting.");
				}
				else
				{
					customCommand("FunPk");
				}
				break;
			case 28172:
				customCommand("gamble");
				break;
			case 161:
				capeEmote();
				break;
			case 13362:
				if (teleblock == true)
				{
					sendMessage("A magical force stops you from teleporting.");
				}
				else
				{
					customCommand("mining");
				}
				break;
			case 13363:
				if (teleblock == true)
				{
					sendMessage("A magical force stops you from teleporting.");
				}
				else
				{
					customCommand("Shilo");
				}
				break;
			case 170:
				if (teleblock == true)
				{
					sendMessage("A magical force stops you from teleporting.");
				}
				else
				{
					customCommand("pkbox");
				}
				break;
			case 28164:
			{
				customCommand("pkrewards");
			}
				break;
			case 163:
				if (teleblock == true)
				{
					sendMessage("A magical force stops you from teleporting.");
				}
				else
				{
					customCommand("kq");
				}
				break;
			case 150:
				if (teleblock == true)
				{
					sendMessage("A magical force stops you from teleporting.");
				}
				else
				{
					customCommand("dbmini");
				}
				break;
			case 151:
				if (teleblock == true)
				{
					sendMessage("A magical force stops you from teleporting.");
				}
				else
				{
					customCommand("mini");
				}
				break;
			case 52050:
				if (teleblock == true)
				{
					sendMessage("A magical force stops you from teleporting.");
				}
				else
				{
					customCommand("bash");
				}
				break;
			case 52051:
				if (teleblock == true)
				{
					sendMessage("A magical force stops you from teleporting.");
				}
				else
				{
					customCommand("staffzone");
				}
				break;
			case 52052:
				if (teleblock == true)
				{
					sendMessage("A magical force stops you from teleporting.");
				}
				else
				{
					customCommand("1v1");
				}
				break;
			case 28165:
			{
				customCommand("rules");
			}
				break;
			case 52053:
				if (teleblock == true)
				{
					sendMessage("A magical force stops you from teleporting.");
				}
				else
				{
					customCommand("kbd");
				}
				break;
			case 52054:
				if (teleblock == true)
				{
					sendMessage("A magical force stops you from teleporting.");
				}
				else
				{
					customCommand("gsmini");
				}
				break;
			case 52055:
				if (teleblock == true)
				{
					sendMessage("A magical force stops you from teleporting.");
				}
				else
				{
					customCommand("3rdage");
				}
				break;
			case 52056:
				if (teleblock == true)
				{
					sendMessage("A magical force stops you from teleporting.");
				}
				else
				{
					customCommand("godwars");
				}
				break;
			case 166:
				if (teleblock == true)
				{
					sendMessage("A magical force stops you from teleporting.");
				}
				else
				{
					customCommand("skills");
				}
				break;
			case 172:
				if (teleblock == true)
				{
					sendMessage("A magical force stops you from teleporting.");
				}
				else
				{
					customCommand("slayer");
				}
				break;
			case 171:
				if (teleblock == true)
				{
					sendMessage("A magical force stops you from teleporting.");
				}
				else
				{
					customCommand("barrows");
				}
				break;
			case 167:
				if (teleblock == true)
				{
					sendMessage("A magical force stops you from teleporting.");
				}
				else
				{
					customCommand("jad");
				}
				break;
			case 52057:
				if (teleblock == true)
				{
					sendMessage("A magical force stops you from teleporting.");
				}
				else
				{
					customCommand("watermini");
				}
				break;
			case 9125: // Accurate
			case 22228: // punch (unarmed)
			case 48010: // flick (whip)
			case 21200: // spike (pickaxe)
			case 1080: // bash (staff)
			case 6168: // chop (axe)
			case 6236: // accurate (long bow)
			case 17102: // accurate (darts)
			case 8234: // stab (dagger)
				FightType = 1;
				SkillID = 0;
				break;
			case 48008: // whip testing
				FightType = 4;
				SkillID = 1;
				break;
			case 9126: // Defensive
			case 22229: // block (unarmed)
			case 21201: // block (pickaxe)
			case 1078: // focus - block (staff)
			case 6169: // block (axe)
			case 33019: // fend (hally)
			case 18078: // block (spear)
			case 8235: // block (dagger)
				FightType = 4;
				SkillID = 1;
				break;
			case 9127: // Controlled
			case 48009: // lash (whip)
			case 33018: // jab (hally)
			case 6234: // longrange (long bow)
			case 18077: // lunge (spear)
			case 18080: // swipe (spear)
			case 18079: // pound (spear)
			case 17100: // longrange (darts)
				FightType = 3;
				SkillID = 3;
				break;
			case 9128: // Aggressive
			case 22230: // kick (unarmed)
			case 21203: // impale (pickaxe)
			case 21202: // smash (pickaxe)
			case 1079: // pound (staff)
			case 6171: // hack (axe)
			case 6170: // smash (axe)
			case 33020: // swipe (hally)
			case 6235: // rapid (long bow)
			case 17101: // repid (darts)
			case 8237: // lunge (dagger)
			case 8236: // slash (dagger)
				FightType = 2;
				SkillID = 2;
				break;
			case 9154: // Log out
			{
				if (LogoutDelay >= 1)
				{
					sendMessage("You must wait 10 seconds after combat to log out!");
				}
				else if (LogoutDelay == 0)
				{
					logout();
					savefile = true;
				}
			}
				break;
			case 21011:
				takeAsNote = false;
				break;
			case 21010:
				takeAsNote = true;
				break;
			case 13092:
				if (tradeWith > 0)
				{
					if (PlayerHandler.players[tradeWith].tradeStatus == 2)
					{
						tradeStatus = 3;
						sendFrame126("Waiting for other player...", 3431);
					}
					else if (PlayerHandler.players[tradeWith].tradeStatus == 3)
					{
						tradeStatus = 3;
						// TradeGoConfirm();
					}
				}
				break;
			case 13218:
				if (tradeWith > 0)
				{
					if (PlayerHandler.players[tradeWith].tradeStatus == 3)
					{
						tradeStatus = 4;
						sendFrame126("Waiting for other player...", 3535);
					}
					else if (PlayerHandler.players[tradeWith].tradeStatus == 4)
					{
						tradeStatus = 4;
						// ConfirmTrade();
					}
				}
				break;
			case 9157:
				if (NpcDialogue == 2)
				{
					NpcDialogue = 0;
					NpcDialogueSend = false;
					openUpBank();
				}
				else if (NpcDialogue == 4)
				{ // Aubury
					NpcDialogue = 0;
					NpcDialogueSend = false;
					openUpShop(2);
				}
				else if (NpcDialogue == 41)
				{
					NpcDialogue = 0;
					NpcDialogueSend = false;
					RemoveAllWindows();
					sendMessage("You board the ship.");
					travelboat1 = true;
					traveltime = 30;
				}
				else if (NpcDialogue == 43)
				{
					NpcDialogue = 0;
					NpcDialogueSend = false;
					RemoveAllWindows();
					sendMessage("You board the ship.");
					travelboat2 = true;
					traveltime = 30;
				}
				else if (NpcDialogue == 306)
				{
					NpcDialogue = 307;
					NpcDialogueSend = false;
				}
				else if (NpcDialogue == 0)
				{
					NpcDialogue = 1340;
					sendFrame126("Mmk thanks for reading!", 4885);
					RemoveAllWindows();
					NpcDialogueSend = false;
				}
				else if (NpcDialogue == 14601)
				{
					NpcDialogue = 14602;
					NpcDialogueSend = false;
				}
				else if (NpcDialogue == 14603)
				{
					NpcDialogue = 14604;
					NpcDialogueSend = false;
				}
				else if (NpcDialogue == 2260)
				{ // Mage Of Zamorak
					NpcDialogue = 0;
					NpcDialogueSend = false;
					sendMessage("You teleport to the abyss.");
					teleportToX = 3040;
					teleportToY = 4842;
				}
				else if (NpcDialogue == 1002)
				{ // Dark Mage
					NpcDialogue = 0;
					NpcDialogueSend = false;
					stillgfx(435, absY, absX);
					RemoveAllWindows();
					if (ancients == 1)
					{
						setSidebarInterface(6, 1151); // magic tab (ancient =
														// 12855);
						ancients = 0;
						sendMessage("The dark mage converts back to normal magic!");
					}
					else
					{
						setSidebarInterface(6, 12855); // magic tab (ancient =
														// 12855);
						ancients = 1;
						sendMessage("The dark mage converts you to ancient magicks!");
					}
				}
				else if (duelring)
				{
					teleportToX = 2837;
					teleportToY = 9581;
					heightLevel = 0;
					sendMessage("You teleport to the TzTok-Jad's lair");
					sendMessage("As you materialize, you feel the air around you grow hot");
					duelring = false;
					RemoveAllWindows();
				}
				else if (OptionObject == 2466)
				{
					sendMessage("Welcome to the PKBox!");
					teleportToX = 3433;
					teleportToY = 9774;
					OptionObject = -1;
					RemoveAllWindows();
				}
				else if (JunaTele == 1)
				{
					RemoveAllWindows();
					teleportToX = 3253;
					teleportToY = absY;
					JunaTele = -1;
				}
				else if (JunaTele == 2)
				{
					RemoveAllWindows();
					teleportToX = 3251;
					teleportToY = absY;
					JunaTele = -1;
				}
				break;
			case 9158:
				if (NpcDialogue == 2)
				{
					NpcDialogue = 0;
					NpcDialogueSend = false;
					openUpPinSettings();
				}
				else if (NpcDialogue == 4)
				{
					NpcDialogue = 5;
					NpcDialogueSend = false;
				}
				else if (NpcDialogue == 41)
				{
					NpcDialogue = 0;
					NpcDialogueSend = false;
					RemoveAllWindows();
				}
				else if (NpcDialogue == 43)
				{
					NpcDialogue = 0;
					NpcDialogueSend = false;
					RemoveAllWindows();
				}
				else if (NpcDialogue == 1340)
				{
					NpcDialogue = 0;
					sendFrame126("Fine, you suck!", 4885);
					NpcDialogueSend = false;
					RemoveAllWindows();
				}
				else if (NpcDialogue == 2260)
				{
					NpcDialogue = 0;
					NpcDialogueSend = false;
					RemoveAllWindows();
				}
				else if (NpcDialogue == 1002)
				{
					NpcDialogue = 0;
					NpcDialogueSend = true;
					RemoveAllWindows();
				}
				else if (NpcDialogue == 302)
				{
					NpcDialogue = 300;
					NpcDialogueSend = false;
				}
				else if (NpcDialogue == 305)
				{
					NpcDialogue = 0;
					NpcDialogueSend = true;
					RemoveAllWindows();
				}
				else if (NpcDialogue == 14601)
				{
					NpcDialogue = 0;
					NpcDialogueSend = true;
					RemoveAllWindows();
				}
				else if (NpcDialogue == 14603)
				{
					NpcDialogue = 0;
					NpcDialogueSend = true;
					RemoveAllWindows();
				}
				else if (duelring)
				{
					teleportToX = 3040;
					teleportToY = 4840;
					heightLevel = 0;
					sendMessage("You teleport to the abyssal rift");
					sendMessage("You can feel the magical aura in the air");
					RemoveAllWindows();
				}
				else if (OptionObject == 2466)
				{
					sendMessage("Welcome to the TeamPk Area!");
					teleportToX = 3243;
					teleportToY = 3517;
					OptionObject = -1;
					RemoveAllWindows();
				}
				if (JunaTele == 1 || JunaTele == 2)
				{
					sendFrame200(615, 974);
					sendFrame126(username, 975);
					sendFrame126("Ya ma.", 976);
					sendFrame185(974);
					sendFrame164(973);
					NpcDialogueSend = true;
					JunaTele = -1;
				}
				break;
			case 1097:
				setSidebarInterface(0, 1829);
				break;
			case 7212:
				setSidebarInterface(0, 328);
				break;
			case 24135: // Clue debug set to on
			{
				sendMessage("Clue debugging set to true.");
				cluedebug = true;
				break;
			}
			case 24134: // Clue debug set to off
			{
				sendMessage("Clue debugging set to false.");
				cluedebug = false;
				break;
			}
			case 4135: // bars to ores (uses bones to bananas spell)
			{
				bars2ores();
			}
				break;
			default:
				// System.out.println("Player stands in: X="+absX+" Y="+absY);
				// println_debug("Case 185: Action Button: " + actionButtonId);
				break;
			}
			break;
		// the following Ids are the reason why AR-type cheats are hopeless to
		// make...
		// basically they're just there to make reversing harder
		case 226:
		case 78:
		case 148:
		case 183:
		case 230:
		case 136:
		case 189:
		case 152:
		case 200:
		case 85:
		case 165:
		case 238:
		case 150:
		case 36:
		case 246:
		case 77:
		case 31195: // insert
			break;
		case 31194: // swap
			break;
		// any packets we might have missed
		default:
			interfaceID = inStream.getUnsignedWordA();
			int actionButtonId1 = MethodCollection.HexToInt(inStream.buffer, 0,
					packetSize);
			break;
		}
	}

	private int somejunk;
	private int EssenceMineX[] = { 2893, 2921, 2911, 2926, 2899 };
	private int EssenceMineY[] = { 4846, 4846, 4832, 4817, 4817 };
	private int EssenceMineRX[] = { 3253, 3105, 2681, 2591 };
	private int EssenceMineRY[] = { 3401, 9571, 3325, 3086 };

	public boolean stairs(int stairs, int teleX, int teleY)
	{
		if (IsStair == false)
		{
			IsStair = true;
			if (stairs == 1)
			{
				heightLevel += 1;
			}
			else if (stairs == 2)
			{
				heightLevel -= 1;
			}
			else if (stairs == 21)
			{
				heightLevel += 1;
			}
			else if (stairs == 22)
			{
				heightLevel -= 1;
			}
			teleportToX = teleX;
			teleportToY = teleY;
			if (stairs == 3 || stairs == 5 || stairs == 9)
			{
				teleportToY += 6400;
			}
			else if (stairs == 4 || stairs == 6 || stairs == 10)
			{
				teleportToY -= 6400;
			}
			else if (stairs == 7)
			{
				teleportToX = 3104;
				teleportToY = 9576;
			}
			else if (stairs == 8)
			{
				teleportToX = 3105;
				teleportToY = 3162;
			}
			else if (stairs == 11)
			{
				teleportToX = 2856;
				teleportToY = 9570;
			}
			else if (stairs == 12)
			{
				teleportToX = 2857;
				teleportToY = 3167;
			}
			else if (stairs == 13)
			{
				heightLevel += 3;
				teleportToX = skillX;
				teleportToY = skillY;
			}
			else if (stairs == 15)
			{
				teleportToY += (6400 - (stairDistance + stairDistanceAdd));
			}
			else if (stairs == 14)
			{
				teleportToY -= (6400 - (stairDistance + stairDistanceAdd));
			}
			else if (stairs == 16)
			{
				teleportToX = 2828;
				teleportToY = 9772;
			}
			else if (stairs == 17)
			{
				teleportToX = 3494;
				teleportToY = 3465;
			}
			else if (stairs == 18)
			{
				teleportToX = 3477;
				teleportToY = 9845;
			}
			else if (stairs == 19)
			{
				teleportToX = 3543;
				teleportToY = 3463;
			}
			else if (stairs == 20)
			{
				teleportToX = 3549;
				teleportToY = 9865;
			}
			else if (stairs == 21)
			{
				teleportToY += (stairDistance + stairDistanceAdd);
			}
			else if (stairs == 22)
			{
				teleportToY -= (stairDistance + stairDistanceAdd);
			}
			else if (stairs == 23)
			{
				teleportToX = 2480;
				teleportToY = 5175;
			}
			else if (stairs == 24)
			{
				teleportToX = 2862;
				teleportToY = 9572;
			}
			else if (stairs == 25)
			{
				Essence = (heightLevel / 4);
				heightLevel = 0;
				teleportToX = EssenceMineRX[Essence];
				teleportToY = EssenceMineRY[Essence];
			}
			else if (stairs == 26)
			{
				int EssenceRnd = MethodCollection.random3(EssenceMineX.length);
				teleportToX = EssenceMineX[EssenceRnd];
				teleportToY = EssenceMineY[EssenceRnd];
				heightLevel = (Essence * 4);
			}
			else if (stairs == 27)
			{
				teleportToX = 2453;
				teleportToY = 4468;
			}
			else if (stairs == 28)
			{
				teleportToX = 3201;
				teleportToY = 3169;
			}
			if (stairs == 5 || stairs == 10)
			{
				teleportToX += (stairDistance + stairDistanceAdd);
			}
			if (stairs == 6 || stairs == 9)
			{
				teleportToX -= (stairDistance - stairDistanceAdd);
			}
		}
		resetStairs();
		return true;
	}

	public boolean resetStairs()
	{
		stairs = 0;
		skillX = -1;
		skillY = -1;
		stairDistance = 1;
		stairDistanceAdd = 0;
		IsUsingSkill = false;
		return true;
	}

	public boolean crackCracker()
	{
		sendMessage("Someone used a cracker on you...");
		CrackerMsg = false;
		if (CrackerForMe == true)
		{
			if (freeSlots() > 0)
			{
				sendMessage("And you get the crackers item.");
			}
			else
			{
				sendMessage("but you don't have enough space in your inventory.");
			}
			CrackerForMe = false;
		}
		else
		{
			sendMessage("but you didn't get the crackers item.");
		}
		return true;
	}

	public boolean IsInWilderness(int coordX, int coordY, int Type)
	{
		if (Type == 1)
		{
			if (coordY >= 3520 && coordY <= 3967 && coordX <= 3392
					&& coordX >= 2942)
			{
				return true;
			}
		}
		else if (Type == 2)
		{
			if (coordY >= 3512 && coordY <= 3967 && coordX <= 3392
					&& coordX >= 2942)
			{
				return true;
			}
		}
		return false;
	}

	public int[] twoHanderz = { 7158, 1319, 6528, 14915 };

	public boolean is2Hander()
	{
		for (int I = 0; I < twoHanderz.length; I++)
		{
			if (playerEquipment[playerWeapon] == twoHanderz[i])
			{
				return true;
			}
		}
		return false;
	}

	private void robpaladin()
	{
		sendMessage("You pickpocket the Paladin.");
		addItem(995, 8000);
		addSkillXP(220, 17);
		int npcIndex = inStream.getSignedWordBigEndianA();
		int EnemyX2 = NPCHandler.npcs[npcIndex].absX;
		int EnemyY2 = NPCHandler.npcs[npcIndex].absY;
		TurnPlayerTo(EnemyX2, EnemyY2);
		updateRequired = true;
		appearanceUpdateRequired = true;
	}

	private void robhero()
	{
		sendMessage("You pickpocket the Farmer.");
		addItem(995, 3000);
		addSkillXP(274, 17);
		int npcIndex = inStream.getSignedWordBigEndianA();
		int EnemyX2 = NPCHandler.npcs[npcIndex].absX;
		int EnemyY2 = NPCHandler.npcs[npcIndex].absY;
		TurnPlayerTo(EnemyX2, EnemyY2);
		updateRequired = true;
		appearanceUpdateRequired = true;
	}

	private void robwarrior()
	{
		sendMessage("You pickpocket the warrior.");
		addItem(995, 1800);
		actionTimer = 10;
		addSkillXP(250, 17);
		int npcIndex = inStream.getSignedWordBigEndianA();
		int EnemyX2 = NPCHandler.npcs[npcIndex].absX;
		int EnemyY2 = NPCHandler.npcs[npcIndex].absY;
		TurnPlayerTo(EnemyX2, EnemyY2);
		updateRequired = true;
		appearanceUpdateRequired = true;
	}

	public boolean Attack()
	{
		int EnemyX = PlayerHandler.players[AttackingOn].absX;
		int EnemyY = PlayerHandler.players[AttackingOn].absY;
		int EnemyHP = PlayerHandler.players[AttackingOn].playerLevel[HITPOINTS];
		int EnemyHPExp = PlayerHandler.players[AttackingOn].playerXP[HITPOINTS];
		int casterX = absX;
		int casterY = absY;
		int offsetX = (casterX - EnemyX) * -1;
		int offsetY = (casterY - EnemyY) * -1;
		int EnemyX2 = PlayerHandler.players[AttackingOn].absX;
		int EnemyY2 = PlayerHandler.players[AttackingOn].absY;
		Client AttackingOn2 = (Client) PlayerHandler.players[AttackingOn];
		boolean RingOfLife = false;
		if (PlayerHandler.players[AttackingOn].playerEquipment[playerRing] == 2570)
		{
			RingOfLife = true;
		}
		int hitDiff = 0;
		resetAnimation();
		CalculateMaxHit();
		hitDiff = MethodCollection.random(playerMaxHit);
		TurnPlayerTo(EnemyX, EnemyY);
		if (playerEquipment[playerWeapon] == (1333)) // rune scimi here
		{
			PkingDelay = 8;
			actionTimer = 8;
		}
		if (AttackingOn2.playerEquipment[playerRing] == 15145)
		{
			hitDiff = hitDiff / 15;
			hitUpdateRequired = true;
			updateRequired = true;
			appearanceUpdateRequired = true;
		}
		if (playerEquipment[playerWeapon] == (15156))
		{
			if (actionTimer == 0) // dark bow
			{
				PkingDelay = 8;
				actionTimer = 8;
				hitDiff = 4 + MethodCollection.random(20);
			}
		}
		if (playerEquipment[playerWeapon] == (6724))
		{
			if (actionTimer == 0) // seercull
			{
				PkingDelay = 8;
				actionTimer = 8;
				hitDiff = 4 + MethodCollection.random(20);
			}
		}
		if (playerEquipment[playerWeapon] == (4151)) // whip
		{
			PkingDelay = 1;
		}
		if (playerEquipment[playerShield] == 15185
				&& MethodCollection.random(5) == 1)
		{
			hitDiff = MethodCollection.random(30);
			AttackingOn2.gfx100(579); // shows fire breathe on opponent
			hitUpdateRequired = true;
			updateRequired = true;
			appearanceUpdateRequired = true;
			startAnimation(2836);
		}
		if (playerEquipment[playerWeapon] == (8447)) // cat toy
		{
			PkingDelay = 1;
		}
		if (playerEquipment[playerWeapon] == (4718)) // dharoks axe
		{
			PkingDelay = 16;
		}
		if (playerEquipment[playerWeapon] == (746)) // dharoks axe
		{
			PkingDelay = 1;
		}
		if (playerEquipment[playerWeapon] == 14889
				&& !username.equalsIgnoreCase("Dokan42")) // Enter your
															// username.
		{
			remove(playerEquipment[playerWeapon], 3);
			sendMessage("Entity is restricted."); // the message it sends when
													// other players try to
													// equip!
			PoisonPlayer();
		}
		if (playerEquipment[playerWeapon] == 6724
				&& !username.equalsIgnoreCase("Dokan42")) // Enter your
															// username.
		{
			remove(playerEquipment[playerWeapon], 3);
			sendMessage("Entity is restricted."); // the message it sends when
													// other players try to
													// equip!
			PoisonPlayer();
		}
		if (playerEquipment[playerWeapon] == 13784
				&& !username.equalsIgnoreCase("Dokan42")) // Enter your
															// username.
		{
			remove(playerEquipment[playerRing], 3);
			sendMessage("Entity is restricted."); // the message it sends when
													// other players try to
													// equip!
			PoisonPlayer();
		}
		if (playerEquipment[playerWeapon] == (4587)) // D Skimmy
		{
			PkingDelay = 1;
		}
		if (playerEquipment[playerWeapon] == (35)) // Excalibur
		{
			PkingDelay = 1;
		}
		if (playerEquipment[playerWeapon] == (15337)) // Lava Blade
		{
			PkingDelay = 1;
		}
		if (playerEquipment[playerWeapon] == (15333)) // Godswords.
		{
			PkingDelay = 30;
		}
		if (playerEquipment[playerWeapon] == (15334)) // Godswords.
		{
			PkingDelay = 30;
		}
		if (playerEquipment[playerWeapon] == (15351)) // Sara Sword
		{
			PkingDelay = 2;
		}
		if (playerEquipment[playerWeapon] == (8002))// d claws
		{
			PkingDelay = 2;
		}
		if (playerEquipment[playerWeapon] == (15335)) // Godswords.
		{
			PkingDelay = 30;
		}
		if (playerEquipment[playerWeapon] == (15336)) // Godswords.
		{
			PkingDelay = 30;
		}
		if (playerEquipment[playerWeapon] == (1377)) // dragon battleaxe
		{
			PkingDelay = 10;
		}
		if (playerEquipment[playerWeapon] == (1373)) // rune battleaxe
		{
			PkingDelay = 10;
		}
		if (playerEquipment[playerWeapon] == (1434)) // dragon mace
		{
			PkingDelay = 10;
		}
		if (playerEquipment[playerWeapon] == (7158)) // dragon 2h
		{
			PkingDelay = 5;
		}
		if (playerEquipment[playerWeapon] == (6528)) // obby maul
		{
			PkingDelay = 5;
		}
		if (playerEquipment[playerWeapon] == (1319)) // rune 2h
		{
			PkingDelay = 80;
		}
		boolean UseBow = false;
		if (playerEquipment[playerWeapon] == 4212)
		{
			PkingDelay = 10;
			createProjectile(absY, absX, offsetX, offsetY, 50, 80, 249, 43, 31,
					0 - AttackingOn);
		}
		if (playerEquipment[playerWeapon] == 861)
		{
			PkingDelay = 5;
			UseBow = true;
		}
		if (playerEquipment[playerWeapon] == 4212)
		{
			PkingDelay = 5;
			UseBow = true;
		}
		if (playerEquipment[playerWeapon] == 859)
		{
			PkingDelay = 10;
		}
		if (playerEquipment[playerWeapon] == 6724)
		{
			PkingDelay = 10;
		}
		if (playerEquipment[playerWeapon] == 839
				|| playerEquipment[playerWeapon] == 841
				|| playerEquipment[playerWeapon] == 843
				|| playerEquipment[playerWeapon] == 845
				|| playerEquipment[playerWeapon] == 847
				|| playerEquipment[playerWeapon] == 849
				|| playerEquipment[playerWeapon] == 851
				|| playerEquipment[playerWeapon] == 861
				|| playerEquipment[playerWeapon] == 853
				|| playerEquipment[playerWeapon] == 4212
				|| playerEquipment[playerWeapon] == 857)
		{
			PkingDelay = 5;
		}
		if (UseBow)
		{
			inCombat();
			teleportToX = absX;
			teleportToY = absY;
			CheckArrows();
			CalculateRange();
			hitDiff = MethodCollection.random(20);
		}
		else
		{
			PkingDelay = 6;
		}
		if (!nonWild() && !AttackingOn2.nonWild())
		{
			if (GoodDistance(EnemyX, EnemyY, absX, absY, 1) == true
					|| playerEquipment[playerWeapon] == 859
					|| playerEquipment[playerWeapon] == 4212
					|| playerEquipment[playerWeapon] == 839
					|| playerEquipment[playerWeapon] == 841
					|| playerEquipment[playerWeapon] == 843
					|| playerEquipment[playerWeapon] == 845
					|| playerEquipment[playerWeapon] == 847
					|| playerEquipment[playerWeapon] == 849
					|| playerEquipment[playerWeapon] == 851
					|| playerEquipment[playerWeapon] == 853
					|| playerEquipment[playerWeapon] == 855
					|| playerEquipment[playerWeapon] == 4212
					|| playerEquipment[playerWeapon] == 857
					|| playerEquipment[playerWeapon] == 861)
			{
				if (LoopAttDelay <= 1)
				{
					if (!nonWild() && !AttackingOn2.nonWild())
					{
						if (RingOfLife == true
								&& EnemyHP <= (int) ((double) ((double) getLevelForXP(EnemyHPExp) / 10.0) + 0.9))
						{
							PlayerHandler.players[AttackingOn].SafeMyLife = true;
						}
						else
						{
							if (PlayerHandler.players[AttackingOn].IsDead == true)
							{
							}
							else if (!UseBow)
							{
								setAnimation(GetWepAnim());
								PlayerHandler.players[AttackingOn].hitUpdateRequired = true;
								PlayerHandler.players[AttackingOn].updateRequired = true;
								TurnPlayerTo(EnemyX, EnemyY);
								PlayerHandler.players[AttackingOn].appearanceUpdateRequired = true;
								TurnPlayerTo(EnemyX, EnemyY);
								if (playerEquipment[playerWeapon] == 5698)
								{
									AttackingOn2.PoisonPlayer();
									hitDiff = MethodCollection
											.random(playerMaxHit);
									PlayerHandler.players[AttackingOn].hitUpdateRequired = true;
									PlayerHandler.players[AttackingOn].updateRequired = true;
									PlayerHandler.players[AttackingOn].appearanceUpdateRequired = true;
									TurnPlayerTo(EnemyX, EnemyY);
								}
								AttackingOn2.KillerId = playerId;
								AttackingOn2.inCombat();
								setAnimation(GetWepAnim());
								AttackingOn2
										.setAnimation(GetBlockAnim(AttackingOn2.playerEquipment[playerWeapon]));
								LoopAttDelay = PkingDelay;
								if ((EnemyHP - hitDiff) < 0)
								{
									hitDiff = EnemyHP;
								}
								addSkillXP(hitDiff, 3);
								PlayerHandler.players[AttackingOn].hitDiff = hitDiff;
								if (usingSpecial == true)
								{
									if (playerEquipment[playerWeapon] == 4151
											&& specialAmount >= 50)
									{
										animation(341, EnemyY2, EnemyX2);
										calculateSpecial();
										hitDiff = specialDamage;
										usingSpecial = false;
										specialDamage = 0;
										specialDamage2 = 0;
									}
									if (playerEquipment[playerWeapon] == 10101
											&& specialAmount >= 50)
									{
										animation(341, EnemyY2, EnemyX2);
										calculateSpecial();
										hitDiff = specialDamage;
										usingSpecial = false;
										specialDamage = 0;
										specialDamage2 = 0;
									}
									if (playerEquipment[playerWeapon] == 10102
											&& specialAmount >= 50)
									{
										animation(341, EnemyY2, EnemyX2);
										calculateSpecial();
										hitDiff = specialDamage;
										usingSpecial = false;
										specialDamage = 0;
										specialDamage2 = 0;
									}
									if (playerEquipment[playerWeapon] == 10103
											&& specialAmount >= 50)
									{
										animation(341, EnemyY2, EnemyX2);
										calculateSpecial();
										hitDiff = specialDamage;
										usingSpecial = false;
										specialDamage = 0;
										specialDamage2 = 0;
									}
									if (playerEquipment[playerWeapon] == 10104
											&& specialAmount >= 50)
									{
										animation(341, EnemyY2, EnemyX2);
										calculateSpecial();
										hitDiff = specialDamage;
										usingSpecial = false;
										specialDamage = 0;
										specialDamage2 = 0;
									}
									if (playerEquipment[playerWeapon] == 10105
											&& specialAmount >= 50)
									{
										animation(341, EnemyY2, EnemyX2);
										calculateSpecial();
										hitDiff = specialDamage;
										usingSpecial = false;
										specialDamage = 0;
										specialDamage2 = 0;
									}
									if (playerEquipment[playerWeapon] == 10106
											&& specialAmount >= 50)
									{
										animation(341, EnemyY2, EnemyX2);
										calculateSpecial();
										hitDiff = specialDamage;
										usingSpecial = false;
										specialDamage = 0;
										specialDamage2 = 0;
									}
									if (playerEquipment[playerWeapon] == 746
											&& specialAmount >= 100)
									{
										animation(433, EnemyY2, EnemyX2);
										calculateSpecial();
										hitDiff = specialDamage;
										usingSpecial = false;
										specialDamage = 0;
										specialDamage2 = 0;
									}
									if (playerEquipment[playerWeapon] == 15156
											&& specialAmount >= 50)
									{
										ProjectileSpec(380, absY, absX,
												offsetY, offsetX, AttackingOn,
												EnemyY, EnemyX);
										calculateSpecial();
										darkbow();
										usingSpecial = false;
										specialDamage = 0;
										specialDamage2 = 0;
									}
									if (playerEquipment[playerWeapon] == 6724
											&& specialAmount >= 50)
									{
										ProjectileSpec(380, absY, absX,
												offsetY, offsetX, AttackingOn,
												EnemyY, EnemyX);
										calculateSpecial();
										seercull();
										usingSpecial = false;
										specialDamage = 0;
										specialDamage2 = 0;
									}
									if (playerEquipment[playerWeapon] == 1305
											&& specialAmount >= 50)
									{
										ProjectileSpec(248, absY, absX,
												offsetY, offsetX, AttackingOn,
												EnemyY, EnemyX);
										calculateSpecial();
										hitDiff = specialDamage;
										usingSpecial = false;
										specialDamage = 0;
										specialDamage2 = 0;
									}
									if (playerEquipment[playerWeapon] == 15334
											&& specialAmount >= 50)
									{
										ProjectileSpec(436, absY, absX,
												offsetY, offsetX, AttackingOn,
												EnemyY, EnemyX);
										calculateSpecial();
										hitDiff = specialDamage;
										usingSpecial = false;
										specialDamage = 0;
										specialDamage2 = 0;
									}
									if (playerEquipment[playerWeapon] == 15336
											&& specialAmount >= 50)
									{
										ProjectileSpec(282, absY, absX,
												offsetY, offsetX, AttackingOn,
												EnemyY, EnemyX);
										ProjectileSpec(497, absY, absX,
												offsetY, offsetX, AttackingOn,
												EnemyY, EnemyX);
										calculateSpecial();
										hitDiff = specialDamage;
										usingSpecial = false;
										specialDamage = 0;
										specialDamage2 = 0;
									}
									if (playerEquipment[playerWeapon] == 14915
											&& specialAmount >= 50)
									{
										ProjectileSpec(282, absY, absX,
												offsetY, offsetX, AttackingOn,
												EnemyY, EnemyX);
										calculateSpecial();
										hitDiff = specialDamage;
										usingSpecial = false;
										specialDamage = 0;
										specialDamage2 = 0;
									}
									if (playerEquipment[playerWeapon] == 3204
											&& specialAmount >= 100)
									{
										ProjectileSpec(282, absY, absX,
												offsetY, offsetX, AttackingOn,
												EnemyY, EnemyX);
										calculateSpecial();
										hitDiff = specialDamage;
										usingSpecial = false;
										specialDamage = 0;
										specialDamage2 = 0;
									}
									if (playerEquipment[playerWeapon] == 4587
											&& specialAmount >= 75)
									{
										ProjectileSpec(347, absY, absX,
												offsetY, offsetX, AttackingOn,
												EnemyY, EnemyX);
										calculateSpecial();
										hitDiff = specialDamage;
										usingSpecial = false;
										specialDamage = 0;
										specialDamage2 = 0;
									}
									if (playerEquipment[playerWeapon] == 35
											&& specialAmount >= 75)
									{
										ProjectileSpec(347, absY, absX,
												offsetY, offsetX, AttackingOn,
												EnemyY, EnemyX);
										calculateSpecial();
										hitDiff = specialDamage;
										usingSpecial = false;
										specialDamage = 0;
										specialDamage2 = 0;
									}
									if (playerEquipment[playerWeapon] == 15337
											&& specialAmount >= 75)
									{
										ProjectileSpec(347, absY, absX,
												offsetY, offsetX, AttackingOn,
												EnemyY, EnemyX);
										calculateSpecial();
										hitDiff = specialDamage;
										usingSpecial = false;
										specialDamage = 0;
										specialDamage2 = 0;
									}
									if (playerEquipment[playerWeapon] == 1434
											&& specialAmount >= 40)
									{
										ProjectileSpec(251, absY, absX,
												offsetY, offsetX, AttackingOn,
												EnemyY, EnemyX);
										calculateSpecial();
										hitDiff = specialDamage;
										usingSpecial = false;
										specialDamage = 0;
										specialDamage2 = 0;
									}
									if (playerEquipment[playerWeapon] == 7158
											&& specialAmount >= 40)
									{
										ProjectileSpec(479, absY, absX,
												offsetY, offsetX, AttackingOn,
												EnemyY, EnemyX);
										calculateSpecial();
										hitDiff = specialDamage;
										usingSpecial = false;
										specialDamage = 0;
										specialDamage2 = 0;
									}
									if (playerEquipment[playerWeapon] == 5698
											&& specialAmount >= 25)
									{
										ProjectileSpec(252, absY, absX,
												offsetY, offsetX, attacknpc,
												EnemyY, EnemyX);
										ProjectileSpec(252, absY, absX,
												offsetY, offsetX, attacknpc,
												EnemyY, EnemyX);
										calculateSpecial();
										DDSSpecial();
										AttackingOn2.PoisonPlayer();
										usingSpecial = false;
										specialDamage = 0;
										specialDamage2 = 0;
									}
									if (playerEquipment[playerWeapon] == 11593
											&& specialAmount >= 100)
									{
										ProjectileSpec(252, absY, absX,
												offsetY, offsetX, attacknpc,
												EnemyY, EnemyX);
										ProjectileSpec(252, absY, absX,
												offsetY, offsetX, attacknpc,
												EnemyY, EnemyX);
										calculateSpecial();
										DLLSpecial();
										AttackingOn2.PoisonPlayer();
										usingSpecial = false;
										specialDamage = 0;
										specialDamage2 = 0;
									}
									if (playerEquipment[playerWeapon] == 11593
											&& specialAmount >= 100)
									{
										ProjectileSpec(252, absY, absX,
												offsetY, offsetX, attacknpc,
												EnemyY, EnemyX);
										ProjectileSpec(252, absY, absX,
												offsetY, offsetX, attacknpc,
												EnemyY, EnemyX);
										calculateSpecial();
										DLSpecial();
										AttackingOn2.PoisonPlayer();
										usingSpecial = false;
										specialDamage = 0;
										specialDamage2 = 0;
									}
									if (playerEquipment[playerWeapon] == 14889
											&& specialAmount >= 75)
									{
										ProjectileSpec(252, absY, absX,
												offsetY, offsetX, attacknpc,
												EnemyY, EnemyX);
										ProjectileSpec(252, absY, absX,
												offsetY, offsetX, attacknpc,
												EnemyY, EnemyX);
										calculateSpecial();
										DDSSpecial();
										AttackingOn2.PoisonPlayer();
										usingSpecial = false;
										specialDamage = 0;
										specialDamage2 = 0;
									}
									if (playerEquipment[playerWeapon] == 15351
											&& specialAmount >= 75)
									{
										ProjectileSpec(252, absY, absX,
												offsetY, offsetX, attacknpc,
												EnemyY, EnemyX);
										ProjectileSpec(252, absY, absX,
												offsetY, offsetX, attacknpc,
												EnemyY, EnemyX);
										calculateSpecial();
										SaraSwordSpecial();
										AttackingOn2.PoisonPlayer();
										usingSpecial = false;
										specialDamage = 0;
										specialDamage2 = 0;
									}
									if (playerEquipment[playerWeapon] == 4153
											&& specialAmount >= 50)
									{
										ProjectileSpec(340, absY, absX,
												offsetY, offsetX, AttackingOn,
												EnemyY, EnemyX);
										calculateSpecial();
										maulSpec();
										usingSpecial = false;
										specialDamage = 0;
										specialDamage2 = 0;
									}
									if (playerEquipment[playerWeapon] == 6739
											&& specialAmount >= 100)
									{
										calculateSpecial();
										hitDiff = specialDamage;
										usingSpecial = false;
										specialDamage = 0;
										specialDamage2 = 0;
									}
									if (playerEquipment[playerWeapon] == 3204
											&& specialAmount >= 100)
									{
										calculateSpecial();
										hally();
										usingSpecial = false;
										specialDamage = 0;
										specialDamage2 = 0;
									}
									if (playerEquipment[playerWeapon] == 861
											&& specialAmount >= 100)
									{
										calculateSpecial();
										DDZ();
										usingSpecial = false;
										specialDamage = 0;
										specialDamage2 = 0;
									}
									if (playerEquipment[playerWeapon] == 4755
											&& specialAmount >= 100
											|| playerEquipment[playerWeapon] == 4734
											&& specialAmount >= 100
											|| playerEquipment[playerWeapon] == 4718
											&& specialAmount >= 100
											|| playerEquipment[playerWeapon] == 4726
											&& specialAmount >= 100
											|| playerEquipment[playerWeapon] == 4747
											&& specialAmount >= 100
											|| playerEquipment[playerWeapon] == 47
											&& specialAmount >= 100)
									{
										ProjectileSpec(432, absY, absX,
												offsetY, offsetX, attacknpc,
												EnemyY, EnemyX);
										calculateSpecial();
										hitDiff = specialDamage;
										usingSpecial = false;
										specialDamage = 0;
										specialDamage2 = 0;
										AttackingOn2.specialAmount = 0;
									}
									usingSpecial = false;
									specialDamage = 0;
									specialDamage2 = 0;
									specialAttacks();
									specialAttacks2();
									specialAttacks3();
									specialAttacks4();
									specialAttacks5();
									specialAttacks6();
								}
								// PkingDelay = wepdelay;
							}
							else if (UseBow)
							{
								if (!HasArrows)
								{
									sendMessage("There's no arrows left in your quiver");
								}
								else if (HasArrows)
								{
									// actionAmount++;
									// setAnimation(playerSEA);
									DeleteArrow();
									if (playerEquipment[playerWeapon] != 4212
											&& playerEquipmentN[playerArrows] != 0)
									{
										ItemHandler.addItem(
												playerEquipment[playerArrows],
												EnemyX, EnemyY, 1, playerId,
												false);
									}
									setAnimation(426);
									PlayerHandler.players[AttackingOn].hitUpdateRequired = true;
									PlayerHandler.players[AttackingOn].updateRequired = true;
									PlayerHandler.players[AttackingOn].appearanceUpdateRequired = true;
									TurnPlayerTo(EnemyX, EnemyY);
									AttackingOn2.KillerId = playerId + 10;
									AttackingOn2.inCombat();
									inCombat();
									teleportToX = absX;
									teleportToY = absY;
									AttackingOn2
											.setAnimation(GetBlockAnim(AttackingOn2.playerEquipment[playerWeapon]));
									LoopAttDelay = PkingDelay;
									if ((EnemyHP - hitDiff) < 0)
									{
										hitDiff = EnemyHP;
									}
									addSkillXP(hitDiff, 3);
									PlayerHandler.players[AttackingOn].hitDiff = hitDiff;
									// PkingDelay = wepdelay;
								}
							}
						}
						return true;
					}
					else
					{
						sendMessage("This player is in a safe zone and cannot be attacked");
						ResetAttack();
					}
				}
			}
		}
		return false;
	}

	public boolean ResetAttack()
	{
		IsAttacking = false;
		AttackingOn = 0;
		resetAnimation();
		IsUsingSkill = false;
		pEmote = playerSE;
		return true;
	}

	public int lnew = 0;
	public int otherpkps = 0;
	public int otherkillc = 0;

	public void PKz()
	{
		if (PlayerHandler.players[KillerId] != null)
		{
			if (KillerId != playerId)
			{
				if (PlayerHandler.players[KillerId].combat > combat)
				{
					lnew = 1;
				}
				else if (PlayerHandler.players[KillerId].combat < combat)
				{
					lnew = 3;
				}
				else if (PlayerHandler.players[KillerId].combat == combat)
				{
					lnew = 2;
				}
				Client killerz = (Client) PlayerHandler.players[KillerId];
				if (killerz != null)
				{
					boolean givePoints = true;
					if (killerz.lastKill.equalsIgnoreCase(username))
					{
						killerz.sendMessage("You recieve no pk points as you have pked "
								+ username + " twice in a row");
						givePoints = false;
					}
					if (givePoints)
					{
						PlayerHandler.players[KillerId].pkpoints += lnew;
						Player.killcount += 1;
						otherpkps = PlayerHandler.players[KillerId].pkpoints;
						otherkillc = Player.killcount;
						killerz.sendMessage("You recieve " + lnew
								+ " player-kill, you now have " + otherpkps
								+ " player-kill points.");
						killerz.sendMessage("You now have a total of "
								+ otherkillc + " player kills.");
						killerz.lastKill = username;
						PlayerHandler.messageToAll = killerz.username
								+ " has killed " + username + ", "
								+ killerz.username + " now has "
								+ killerz.pkpoints + " pk points and "
								+ Player.killcount + " kills!";
					}
				}
			}
			deathcount = +1;
		}
	}

	public void CheckDrop()
	{
		keepItem1();
		keepItem2();
		keepItem3();
		deleteItem(keepItem, getItemSlot(keepItem), keepItemAmount);
		deleteItem(keepItem2, getItemSlot(keepItem2), keepItemAmount2);
		deleteItem(keepItem3, getItemSlot(keepItem3), keepItemAmount3);
		handleDeath();
	}

	public boolean ApplyDead()
	{
		if (IsDeadTimer == false)
		{
			ResetAttack();
			ResetAttackNPC();
			EntangleDelay = 0;
			ResetAttackNPC();
			EntangleDelay = 0;
			actionTimer = 1;
			IsDeadTimer = true;
			pEmote = 2304;
			teleblock = false;
			actionTimer = 1;
			IsDeadTimer = true;
			ApplyDead();
		}
		if (actionTimer == 0
				&& IsDeadTimer == true
				&& (absX >= 2381 && absX <= 2410 && absY >= 5133 && absY <= 5163))
		{
			teleportToX = 2399;
			teleportToY = 5171;
			IsDeadTeleporting = true;
			frame1();
			updateRequired = true;
			appearanceUpdateRequired = true;
			NewHP = getLevelForXP(playerXP[3]);
			setSkillLevel(3, getLevelForXP(playerXP[3]), playerXP[HITPOINTS]);
			playerLevel[3] = getLevelForXP(playerXP[3]);
			refreshSkills();
			PoisonDelay = 9999999;
			KillerId = playerId;
		}
		if (actionTimer == 0 && IsDeadTimer == true)
		{
			keepItem1();
			keepItem2();
			keepItem3();
			deleteItem(keepItem, getItemSlot(keepItem), keepItemAmount);
			deleteItem(keepItem2, getItemSlot(keepItem2), keepItemAmount2);
			deleteItem(keepItem3, getItemSlot(keepItem3), keepItemAmount3);
			handleDeath();
			PKz();
			teleportToX = 2899;
			teleportToY = 3557;
			IsDeadTeleporting = true;
			frame1(); // Xerozcheez: Resets animation
			updateRequired = true;
			appearanceUpdateRequired = true;
			NewHP = getLevelForXP(playerXP[3]);
			setSkillLevel(3, getLevelForXP(playerXP[3]), playerXP[HITPOINTS]);
			playerLevel[3] = getLevelForXP(playerXP[3]);
			refreshSkills();
			PoisonDelay = 9999999;
			KillerId = playerId;
			if (keepItem != 0)
				addItem(keepItem, keepItemAmount);
			if (keepItem2 != 0)
				addItem(keepItem2, keepItemAmount2);
			if (keepItem3 != 0)
				addItem(keepItem3, keepItemAmount3);
			resetKeepItem();
		}
		return true;
	}

	public boolean ApplyDeadz()
	{
		if (IsDeadTimer == false)
		{
			actionTimer = 0;
			ResetAttack();
			ResetAttackNPC();
			setAnimation(2304);
			IsDeadTeleporting = true;
			IsDeadTimer = true;
			ApplyDead();
			specialAttacks();
			specialAttacks2();
			specialAttacks3();
			specialAttacks4();
			specialAttacks5();
			specialAttacks6();
		}
		if (actionTimer == 0 && IsDeadTimer == true)
		{
			PKz();
			CheckDrop();
			ApplyRingz();
			NewHP = getLevelForXP(playerXP[3]);
			setSkillLevel(3, getLevelForXP(playerXP[3]), playerXP[HITPOINTS]);
			playerLevel[3] = getLevelForXP(playerXP[3]);
			refreshSkills();
			PoisonDelay = 9999999;
			KillerId = playerId;
			if (keepItem != 0)
			{
				addItem(keepItem, keepItemAmount);
			}
			if (keepItem2 != 0)
			{
				addItem(keepItem2, keepItemAmount2);
			}
			if (keepItem3 != 0)
			{
				addItem(keepItem3, keepItemAmount3);
			}
			resetKeepItem();
		}
		return true;
	}

	public void setSkillLevel(int skillNum, int currentLevel, int XP)
	{
		if (skillNum == 0)
		{
			setText("" + playerLevel[0] + "", 4004);
			setText("" + getLevelForXP(playerXP[0]) + "", 4005);
		}
		if (skillNum == 2)
		{
			setText("" + playerLevel[2] + "", 4006);
			setText("" + getLevelForXP(playerXP[2]) + "", 4007);
		}
		if (skillNum == 1)
		{
			setText("" + playerLevel[1] + "", 4008);
			setText("" + getLevelForXP(playerXP[1]) + "", 4009);
		}
		if (skillNum == 4)
		{
			setText("" + playerLevel[4] + "", 4010);
			setText("" + getLevelForXP(playerXP[4]) + "", 4011);
		}
		if (skillNum == 5)
		{
			setText("" + playerLevel[5] + "", 4012);
			setText("" + getLevelForXP(playerXP[5]) + "", 4013);
		}
		if (skillNum == 6)
		{
			setText("" + playerLevel[6] + "", 4014);
			setText("" + getLevelForXP(playerXP[6]) + "", 4015);
		}
		if (skillNum == 3)
		{
			setText("" + playerLevel[3] + "", 4016);
			setText("" + getLevelForXP(playerXP[3]) + "", 4017);
		}
		if (skillNum == 16)
		{
			setText("" + playerLevel[16] + "", 4018);
			setText("" + getLevelForXP(playerXP[16]) + "", 4019);
		}
		if (skillNum == 15)
		{
			setText("" + playerLevel[15] + "", 4020);
			setText("" + getLevelForXP(playerXP[15]) + "", 4021);
		}
		if (skillNum == 17)
		{
			setText("" + playerLevel[17] + "", 4022);
			setText("" + getLevelForXP(playerXP[17]) + "", 4023);
		}
		if (skillNum == 12)
		{
			setText("" + playerLevel[12] + "", 4024);
			setText("" + getLevelForXP(playerXP[12]) + "", 4025);
		}
		if (skillNum == 9)
		{
			setText("" + playerLevel[9] + "", 4026);
			setText("" + getLevelForXP(playerXP[9]) + "", 4027);
		}
		if (skillNum == 14)
		{
			setText("" + playerLevel[14] + "", 4028);
			setText("" + getLevelForXP(playerXP[14]) + "", 4029);
		}
		if (skillNum == 13)
		{
			setText("" + playerLevel[13] + "", 4030);
			setText("" + getLevelForXP(playerXP[13]) + "", 4031);
		}
		if (skillNum == 10)
		{
			setText("" + playerLevel[10] + "", 4032);
			setText("" + getLevelForXP(playerXP[10]) + "", 4033);
		}
		if (skillNum == 7)
		{
			setText("" + playerLevel[7] + "", 4034);
			setText("" + getLevelForXP(playerXP[7]) + "", 4035);
		}
		if (skillNum == 11)
		{
			setText("" + playerLevel[11] + "", 4036);
			setText("" + getLevelForXP(playerXP[11]) + "", 4037);
		}
		if (skillNum == 8)
		{
			setText("" + playerLevel[8] + "", 4038);
			setText("" + getLevelForXP(playerXP[8]) + "", 4039);
		}
		if (skillNum == 20)
		{
			setText("" + playerLevel[20] + "", 4152);
			setText("" + getLevelForXP(playerXP[20]) + "", 4153);
		}
		if (skillNum == 18)
		{
			setText("" + playerLevel[18] + "", 12166);
			setText("" + getLevelForXP(playerXP[18]) + "", 12167);
		}
		if (skillNum == 19)
		{
			setText("" + playerLevel[19] + "", 13926);
			setText("" + getLevelForXP(playerXP[19]) + "", 13927);
		}
		else
		{
			outStream.createFrame(134);
			outStream.put(skillNum);
			outStream.putDWord_v1(XP);
			outStream.put(currentLevel);
		}
	}

	public void resetKeepItem()
	{
		keepItem = 0;
		keepItem2 = 0;
		keepItem3 = 0;
		keepItemAmount = 0;
		keepItemAmount2 = 0;
		keepItemAmount3 = 0;
	}

	public void keepItem1()
	{
		for (int i = 0; i < playerItems.length; i++)
		{
			int highest = 0;
			int value = (int) Math.floor(GetItemShopValue(playerItems[i] - 1,
					0, i));
			if (value > highest && playerItems[i] - 1 != -1)
			{
				highest = value;
				keepItem = playerItems[i] - 1;
				keepItemAmount = playerItemsN[i];
			}
		}
	}

	public void keepItem2()
	{
		for (int i = 0; i < playerItems.length; i++)
		{
			int highest = 0;
			int value = (int) Math.floor(GetItemShopValue(playerItems[i] - 1,
					0, i));
			if (value > highest && playerItems[i] - 1 != keepItem
					&& playerItems[i] - 1 != -1)
			{
				highest = value;
				keepItem2 = playerItems[i] - 1;
				keepItemAmount2 = playerItemsN[i];
			}
		}
	}

	public void keepItem3()
	{
		for (int i = 0; i < playerItems.length; i++)
		{
			int highest = 0;
			int value = (int) Math.floor(GetItemShopValue(playerItems[i] - 1,
					0, i));
			if (value > highest && playerItems[i] - 1 != keepItem
					&& playerItems[i] - 1 != keepItem2
					&& playerItems[i] - 1 != -1)
			{
				highest = value;
				keepItem3 = playerItems[i] - 1;
				keepItemAmount3 = playerItemsN[i];
			}
		}
	}

	public boolean ApplyRingOfLife()
	{
		if (IsDeadTimer == false)
		{
			actionAmount++;
			actionTimer = 4;
			ResetAttack();
			setAnimation(0x718);
			sendMessage("Ring of Life saved your life !");
			deleteequiment(2570, playerRing); // 2570 = ring of life
			IsDeadTimer = true;
		}
		if (actionTimer == 0 && IsDeadTimer == true)
		{
			teleportToX = 3254;
			teleportToY = 3420;
			resetAnimation();
			IsDeadTeleporting = true;
			updateRequired = true;
			appearanceUpdateRequired = true;
		}
		return true;
	}

	public boolean ApplyRingz()
	{
		if (IsDeadTimer == false)
		{
			actionAmount++;
			actionTimer = 4;
			ResetAttack();
			// setAnimation(0x718);
			IsDeadTimer = true;
		}
		if (actionTimer == 0 && IsDeadTimer == true)
		{
			teleportToX = 2787;
			teleportToY = 2786;
			resetAnimation();
			IsDeadTeleporting = true;
			updateRequired = true;
			appearanceUpdateRequired = true;
		}
		return true;
	}

	public boolean CheckForSkillUse()
	{ // Use Item On Item
		if (useitems[0] == 946)
		{ // knife
			CheckKnifeUsage();
		}
		else if (useitems[0] == 1777)
		{ // string
			CheckStringUsage();
		}
		else if (useitems[0] == 314)
		{ // feathers
			CheckFeatherUsage();
		}
		else if (useitems[1] == 1019 || useitems[1] == 1021
				|| useitems[1] == 1023 || useitems[1] == 1027
				|| useitems[1] == 1029 || useitems[1] == 1031
				|| useitems[1] == 1007)
		{
			CheckDyeCape();
		}
		else if (useitems[0] == 1755)
		{ // chisel
			CheckCuttingGem();
		}
		else if (useitems[1] == 946 || useitems[1] == 1777
				|| useitems[1] == 314 || useitems[1] == 1755
				|| useitems[0] == 1019 || useitems[0] == 1021
				|| useitems[0] == 1023 || useitems[0] == 1027
				|| useitems[0] == 1029 || useitems[0] == 1031
				|| useitems[0] == 1007)
		{
			int temp;
			temp = useitems[0];
			useitems[0] = useitems[1];
			useitems[1] = temp;
			temp = useitems[3];
			useitems[3] = useitems[2];
			useitems[2] = temp;
			CheckForSkillUse();
		}
		else
		{
			sendMessage("Nothing interesting is happening.");
		}
		return true;
	}

	public boolean CheckForSkillUse2(int ItemID, int ItemSlot)
	{ // Use Item On
		// Object
		boolean GoFalse = false;
		switch (ItemID)
		{
		case 317: // raw shrimps
			cooking[1] = 1;
			cooking[2] = 30;
			cooking[4] = 315;
			cooking[6] = 323;
			break;
		case 321: // raw anchovies
			cooking[1] = 1;
			cooking[2] = 30;
			cooking[4] = 319;
			cooking[6] = 323;
			break;
		case 327: // raw sardine
			cooking[1] = 1;
			cooking[2] = 40;
			cooking[4] = 325;
			cooking[6] = 369;
			break;
		case 331: // raw salmon
			cooking[1] = 25;
			cooking[2] = 90;
			cooking[4] = 329;
			cooking[6] = 343;
			break;
		case 335: // raw trout
			cooking[1] = 15;
			cooking[2] = 70;
			cooking[4] = 333;
			cooking[6] = 343;
			break;
		case 341: // raw cod
			cooking[1] = 18;
			cooking[2] = 75;
			cooking[4] = 339;
			cooking[6] = 343;
			break;
		case 345: // raw harring
			cooking[1] = 5;
			cooking[2] = 50;
			cooking[4] = 347;
			cooking[6] = 357;
			break;
		case 349: // raw pike
			cooking[1] = 20;
			cooking[2] = 80;
			cooking[4] = 351;
			cooking[6] = 357;
			break;
		case 353: // raw mackerel
			cooking[1] = 10;
			cooking[2] = 60;
			cooking[4] = 355;
			cooking[6] = 357;
			break;
		case 359: // raw tuna
			cooking[1] = 30;
			cooking[2] = 100;
			cooking[4] = 361;
			cooking[6] = 367;
			break;
		case 363: // raw bass
			cooking[1] = 43;
			cooking[2] = 130;
			cooking[4] = 365;
			cooking[6] = 367;
			break;
		case 371: // raw swordfish
			cooking[1] = 45;
			cooking[2] = 140;
			cooking[4] = 373;
			cooking[6] = 375;
			break;
		case 377: // raw lobster
			cooking[1] = 40;
			cooking[2] = 120;
			cooking[4] = 379;
			cooking[6] = 381;
			break;
		case 383: // raw shark
			cooking[1] = 80;
			cooking[2] = 210;
			cooking[4] = 385;
			cooking[6] = 387;
			break;
		case 389: // raw mante ray
			cooking[1] = 91;
			cooking[2] = 216;
			cooking[4] = 391;
			cooking[6] = 393;
			break;
		case 395: // raw sea turtle
			cooking[1] = 82;
			cooking[2] = 212;
			cooking[4] = 397;
			cooking[6] = 399;
			break;
		case 436: // copper ore
			if (IsItemInBag(438) == true)
			{
				smelting[0] = 1;
				smelting[1] = 1;
				if (MethodCollection.random(2) == 1)
				{
					smelting[2] = 6;
				}
				else
				{
					smelting[2] = 7;
				}
				smelting[3] = 2349;
			}
			else
			{
				sendMessage("You need 1 copper ore and 1 tin ore to smelt 1 bronze bar.");
				return false;
			}
			break;
		case 438: // tin
			if (IsItemInBag(436) == true)
			{
				smelting[0] = 1;
				smelting[1] = 1;
				if (MethodCollection.random(2) == 1)
				{
					smelting[2] = 6;
				}
				else
				{
					smelting[2] = 7;
				}
				smelting[3] = 2349;
			}
			else
			{
				sendMessage("You need 1 copper ore and 1 tin ore to smelt 1 bronze bar.");
				return false;
			}
			break;
		case 440: // iron
			smelting[0] = 1;
			if (AreXItemsInBag(453, 2) == true)
			{
				smelting[1] = 30;
				if (MethodCollection.random(2) == 1)
				{
					smelting[2] = 17;
				}
				else
				{
					smelting[2] = 18;
				}
				smelting[3] = 2353;
				smelting[6] = 2;
			}
			else
			{
				smelting[1] = 15;
				if (MethodCollection.random(2) == 1)
				{
					smelting[2] = 12;
				}
				else
				{
					smelting[2] = 13;
				}
				smelting[3] = 2349;
			}
			break;
		case 2892: // elemental
			if (AreXItemsInBag(453, 4) == true)
			{
				smelting[0] = 1;
				smelting[1] = 20;
				smelting[2] = 18;
				smelting[3] = 2893;
				smelting[6] = 4;
			}
			else
			{
				sendMessage("You need 1 elemental ore and 4 coal to smelt 1 elemental bar.");
				return false;
			}
			break;
		case 442: // silver
			smelting[0] = 1;
			smelting[1] = 20;
			if (MethodCollection.random(2) == 1)
			{
				smelting[2] = 13;
			}
			else
			{
				smelting[2] = 14;
			}
			smelting[3] = 2355;
			break;
		case 444: // gold
			smelting[0] = 1;
			smelting[1] = 40;
			if (playerEquipment[playerHands] == 776)
			{
				if (MethodCollection.random(2) == 1)
				{
					smelting[2] = 56;
				}
				else
				{
					smelting[2] = 57;
				}
			}
			else
			{
				if (MethodCollection.random(2) == 1)
				{
					smelting[2] = 22;
				}
				else
				{
					smelting[2] = 23;
				}
			}
			smelting[3] = 2357;
			break;
		case 447: // mithril
			if (AreXItemsInBag(453, 4) == true)
			{
				smelting[0] = 1;
				smelting[1] = 50;
				smelting[2] = 30;
				smelting[3] = 2359;
				smelting[6] = 4;
			}
			else
			{
				sendMessage("You need 1 mithril ore and 4 coal to smelt 1 mithril bar.");
				return false;
			}
			break;
		case 449: // adamantite
			if (AreXItemsInBag(453, 6) == true)
			{
				smelting[0] = 1;
				smelting[1] = 70;
				if (MethodCollection.random(2) == 1)
				{
					smelting[2] = 37;
				}
				else
				{
					smelting[2] = 38;
				}
				smelting[3] = 2361;
				smelting[6] = 6;
			}
			else
			{
				sendMessage("You need 1 adamant ore and 4 coal to smelt 1 adamant bar.");
				return false;
			}
			break;
		case 451: // runite
			if (AreXItemsInBag(453, 8) == true)
			{
				smelting[0] = 1;
				smelting[1] = 85;
				smelting[2] = 50;
				smelting[3] = 2363;
				smelting[6] = 8;
			}
			else
			{
				sendMessage("You need 1 runite ore and 4 coal to smelt 1 runite bar.");
				return false;
			}
			break;
		case 453: // coal
			if (IsItemInBag(451) == true)
			{ // runite
				CheckSmelting(451, GetItemSlot(451));
			}
			else if (IsItemInBag(449) == true)
			{ // adamant
				CheckSmelting(449, GetItemSlot(449));
			}
			else if (IsItemInBag(447) == true)
			{ // mithril
				CheckSmelting(447, GetItemSlot(447));
			}
			else if (IsItemInBag(2892) == true)
			{ // elemental
				CheckSmelting(2892, GetItemSlot(2892));
			}
			else if (IsItemInBag(440) == true)
			{ // iron (to make steel)
				CheckSmelting(440, GetItemSlot(440));
			}
			break;
		case 1889: // uncooked cake
			cooking[1] = 40;
			cooking[2] = 180;
			cooking[4] = 1891;
			cooking[6] = 1903;
			break;
		case 2001: // Uncooked stew
			cooking[1] = 25;
			cooking[2] = 117;
			cooking[4] = 2003;
			cooking[6] = 2005;
			break;
		case 2009: // Uncooked curry
			cooking[1] = 60;
			cooking[2] = 280;
			cooking[4] = 2011;
			cooking[6] = 2013;
			break;
		case 2132: // raw beef
		case 2134: // raw rat meat
		case 2136: // raw bear meat
			cooking[1] = 1;
			cooking[2] = 30;
			cooking[4] = 2142;
			cooking[6] = 2146;
			break;
		case 2138: // raw chicken
			cooking[1] = 1;
			cooking[2] = 30;
			cooking[4] = 2140;
			cooking[6] = 2144;
			break;
		case 2287: // uncooked pizza
			cooking[1] = 35;
			cooking[2] = 143;
			cooking[4] = 2289;
			cooking[6] = 2305;
			break;
		case 2307: // bread dough
			cooking[1] = 1;
			cooking[2] = 40;
			cooking[4] = 2309;
			cooking[6] = 2311;
			break;
		case 2317: // uncooked apple pie
			cooking[1] = 30;
			cooking[2] = 104;
			cooking[4] = 2323;
			cooking[6] = 2329;
			break;
		case 2319: // uncooked meat pie
			cooking[1] = 20;
			cooking[2] = 104;
			cooking[4] = 2327;
			cooking[6] = 2329;
			break;
		case 2321: // uncooked berry pie
			cooking[1] = 10;
			cooking[2] = 78;
			cooking[4] = 2325;
			cooking[6] = 2329;
			break;
		case 2876: // Raw chompy
			cooking[1] = 20;
			cooking[2] = 140;
			cooking[4] = 2878;
			cooking[6] = 2880;
			break;
		case 3142: // Raw karambwan
			cooking[1] = 1;
			cooking[2] = 80;
			cooking[4] = 3144;
			cooking[6] = 3148;
			break;
		case 3363: // Thin snail
			cooking[1] = 12;
			cooking[2] = 70;
			cooking[4] = 3369;
			cooking[6] = 3375;
			break;
		case 3365: // Lean snail
			cooking[1] = 17;
			cooking[2] = 80;
			cooking[4] = 3371;
			cooking[6] = 3375;
			break;
		case 3367: // Fat snail
			cooking[1] = 22;
			cooking[2] = 95;
			cooking[4] = 3373;
			cooking[6] = 3375;
			break;
		case 3379: // Slimy eel
			cooking[1] = 28;
			cooking[2] = 95;
			cooking[4] = 3381;
			cooking[6] = 3383;
			break;
		case 5001: // Raw cave eel
			cooking[1] = 28;
			cooking[2] = 115;
			cooking[4] = 5003;
			cooking[6] = 5002;
			break;
		case 5986: // Sweetcorn
			cooking[1] = 21;
			cooking[2] = 104;
			cooking[4] = 5988;
			cooking[6] = 5990;
			break;
		case 6293: // Spider on stick
			cooking[1] = 16;
			cooking[2] = 80;
			cooking[4] = 6297;
			cooking[6] = 6301;
			break;
		case 6295: // Spider on shaft
			cooking[1] = 16;
			cooking[2] = 80;
			cooking[4] = 6299;
			cooking[6] = 6303;
			break;
		default:
			sendMessage("You cannot smelt this item.");
			GoFalse = true;
			break;
		}
		if (GoFalse == true)
		{
			return false;
		}
		if (ItemID != 453 && smelting[0] >= 1
				&& playerEquipment[playerWeapon] >= 0)
		{
			OriginalWeapon = playerEquipment[playerWeapon];
			playerEquipment[playerWeapon] = -1;
			OriginalShield = playerEquipment[playerShield];
			playerEquipment[playerShield] = -1;
			smelting[4] = ItemID;
			smelting[5] = ItemSlot;
		}
		else if (cooking[1] >= 1)
		{
			cooking[0] = 1;
			cooking[5] = ItemID;
		}
		return true;
	}

	int[] mBox = new int[] { 7804, 15351, 14538, 14539, 4151, 9117, 9118,
			14915, 15336, 15333, 15335, 15348, 15349, 15350, 15346, 15347,
			15345, 15195
	// add more junk rewards
	};

	public int randomBox()
	{
		return mBox[(int) (Math.random() * (double) mBox.length)];
	}

	public boolean CheckForSkillUse3(int Item, int Slot)
	{
		boolean GoOn = true;
		switch (Item)
		{
		case 6199:
			int item = randomBox();
			deleteItem(6199, getItemSlot(6199), 1);
			addItem(item, 1);
			sendMessage("You get a " + getItemName(item) + " from the box!");
			break;
		case 9540: // Home Teletab (Silabs)
		{
			deleteItem(9540, getItemSlot(9540), 1);
			startAnimation(1816);
			teleportToX = 2515;
			teleportToY = 3863;
			txt4 = "You teleport to home";
			string4UpdateRequired = true;
		}
			break;
		case 2520:
			startAnimation(918);
			txt4 = "Come on Swifty, we can win the race!";
			string4UpdateRequired = true;
			break;
		case 2522:
			startAnimation(919);
			txt4 = "Come on Alex, we can win the race!";
			string4UpdateRequired = true;
			break;
		case 2524:
			startAnimation(920);
			txt4 = "Come on Vegeta, we can win the race!";
			string4UpdateRequired = true;
			break;
		case 2526:
			startAnimation(921);
			txt4 = "Come on MrWicked, we can win the race!";
			string4UpdateRequired = true;
			break;
		case 1917: // Beer
			sendMessage("You drink the beer, and feel a bit drunk!");
			drunkTimer += 80;
			deleteItem(1917, getItemSlot(1917), 1);
			break;
		case 161: // super str (1)
			strPot = true;
			strPotTimer = 90;
			abc = getLevelForXP(playerXP[2]);
			cba = abc / 5;
			abc2 = cba * 2;
			if (abc2 <= 1)
			{
				abc2 = 2;
			}
			playerLevel[2] = getLevelForXP(playerXP[2]);
			playerLevel[2] += abc2;
			sendFrame126("" + playerLevel[2] + "", 4006);
			deleteItem(161, GetItemSlot(161), 1);
			addItem(229, 1);
			updateRequired = true;
			appearanceUpdateRequired = true;
			GoOn = false;
			break;
		case 159:
			strPot = true;
			strPotTimer = 90;
			abc = getLevelForXP(playerXP[2]);
			cba = abc / 10;
			abc2 = cba * 2;
			if (abc2 <= 1)
			{
				abc2 = 2;
			}
			playerLevel[2] = getLevelForXP(playerXP[2]);
			playerLevel[2] += abc2;
			sendFrame126("" + playerLevel[2] + "", 4006);
			deleteItem(159, GetItemSlot(159), 1);
			addItem(161, 1);
			updateRequired = true;
			appearanceUpdateRequired = true;
			GoOn = false;
			break;
		case 157:
			strPot = true;
			strPotTimer = 90;
			abc = getLevelForXP(playerXP[2]);
			cba = abc / 5;
			abc2 = cba * 2;
			if (abc2 <= 1)
			{
				abc2 = 2;
			}
			playerLevel[2] = getLevelForXP(playerXP[2]);
			playerLevel[2] += abc2;
			sendFrame126("" + playerLevel[2] + "", 4006);
			deleteItem(157, GetItemSlot(157), 1);
			addItem(159, 1);
			updateRequired = true;
			appearanceUpdateRequired = true;
			GoOn = false;
			break;
		case 2440:
			strPot = true;
			strPotTimer = 90;
			abc = getLevelForXP(playerXP[2]);
			cba = abc / 5;
			abc2 = cba * 2;
			if (abc2 <= 1)
			{
				abc2 = 2;
			}
			playerLevel[2] = getLevelForXP(playerXP[2]);
			playerLevel[2] += abc2;
			sendFrame126("" + playerLevel[2] + "", 4006);
			deleteItem(2440, GetItemSlot(2440), 1);
			addItem(157, 1);
			updateRequired = true;
			appearanceUpdateRequired = true;
			GoOn = false;
			break;
		case 113:
			strPot = true;
			strPotTimer = 90;
			abc = getLevelForXP(playerXP[2]);
			cba = abc / 5;
			aaa = cba / 2;
			abc2 = aaa + cba;
			if (abc2 <= 1)
			{
				abc2 = 2;
			}
			playerLevel[2] = getLevelForXP(playerXP[2]);
			playerLevel[2] += abc2;
			sendFrame126("" + playerLevel[2] + "", 4006);
			deleteItem(113, GetItemSlot(113), 1);
			addItem(115, 1);
			updateRequired = true;
			appearanceUpdateRequired = true;
			GoOn = false;
			break;
		case 2528: // lamp
			showInterface(2808);
			sendMessage("Please select a skill you would like to advance in.");
			break;
		case 115:
			strPot = true;
			strPotTimer = 90;
			abc = getLevelForXP(playerXP[2]);
			cba = abc / 5;
			aaa = cba / 2;
			abc2 = aaa + cba;
			if (abc2 <= 1)
			{
				abc2 = 2;
			}
			playerLevel[2] = getLevelForXP(playerXP[2]);
			playerLevel[2] += abc2;
			sendFrame126("" + playerLevel[2] + "", 4006);
			deleteItem(115, GetItemSlot(115), 1);
			addItem(117, 1);
			updateRequired = true;
			appearanceUpdateRequired = true;
			GoOn = false;
			break;
		case 117:
			strPot = true;
			strPotTimer = 90;
			abc = getLevelForXP(playerXP[2]);
			cba = abc / 5;
			aaa = cba / 2;
			abc2 = aaa + cba;
			if (abc2 <= 1)
			{
				abc2 = 2;
			}
			playerLevel[2] = getLevelForXP(playerXP[2]);
			playerLevel[2] += abc2;
			sendFrame126("" + playerLevel[2] + "", 4006);
			deleteItem(117, GetItemSlot(117), 1);
			addItem(119, 1);
			updateRequired = true;
			appearanceUpdateRequired = true;
			GoOn = false;
			break;
		case 119:
			strPot = true;
			strPotTimer = 90;
			abc = getLevelForXP(playerXP[2]);
			cba = abc / 5;
			aaa = cba / 2;
			abc2 = aaa + cba;
			if (abc2 <= 1)
			{
				abc2 = 2;
			}
			playerLevel[2] = getLevelForXP(playerXP[2]);
			playerLevel[2] += abc2;
			sendFrame126("" + playerLevel[2] + "", 4006);
			deleteItem(119, GetItemSlot(119), 1);
			addItem(229, 1);
			updateRequired = true;
			appearanceUpdateRequired = true;
			GoOn = false;
			break;
		case 167: // pot (1)
			defPot = true;
			defPotTimer = 90;
			abc = getLevelForXP(playerXP[1]);
			cba = abc / 10;
			abc2 = cba * 2;
			if (abc2 <= 1)
			{
				abc2 = 2;
			}
			playerLevel[1] = getLevelForXP(playerXP[1]);
			playerLevel[1] += abc2;
			sendFrame126("" + playerLevel[1] + "", 4008);
			deleteItem(167, GetItemSlot(167), 1);
			addItem(229, 1);
			updateRequired = true;
			appearanceUpdateRequired = true;
			GoOn = false;
			break;
		case 165: // super defence pot (2)
			defPot = true;
			defPotTimer = 90;
			abc = getLevelForXP(playerXP[1]);
			cba = abc / 10;
			abc2 = cba * 2;
			if (abc2 <= 1)
			{
				abc2 = 2;
			}
			playerLevel[1] = getLevelForXP(playerXP[1]);
			playerLevel[1] += abc2;
			sendFrame126("" + playerLevel[1] + "", 4008);
			deleteItem(165, GetItemSlot(165), 1);
			addItem(167, 1);
			updateRequired = true;
			appearanceUpdateRequired = true;
			GoOn = false;
			break;
		case 163: // super defence pot (3)
			defPot = true;
			defPotTimer = 90;
			abc = getLevelForXP(playerXP[1]);
			cba = abc / 10;
			abc2 = cba * 2;
			if (abc2 <= 1)
			{
				abc2 = 2;
			}
			playerLevel[1] = getLevelForXP(playerXP[1]);
			playerLevel[1] += abc2;
			sendFrame126("" + playerLevel[1] + "", 4008);
			deleteItem(163, GetItemSlot(163), 1);
			addItem(165, 1);
			updateRequired = true;
			appearanceUpdateRequired = true;
			GoOn = false;
			break;
		case 2442: // super defence pot (4)
			defPot = true;
			defPotTimer = 90;
			abc = getLevelForXP(playerXP[1]);
			cba = abc / 10;
			abc2 = cba * 2;
			if (abc2 <= 1)
			{
				abc2 = 2;
			}
			playerLevel[1] = getLevelForXP(playerXP[1]);
			playerLevel[1] += abc2;
			sendFrame126("" + playerLevel[1] + "", 4008);
			deleteItem(2442, GetItemSlot(2442), 1);
			addItem(163, 1);
			updateRequired = true;
			appearanceUpdateRequired = true;
			GoOn = false;
			break;
		case 137: // defence pot (1)
			defPot = true;
			defPotTimer = 90;
			abc = getLevelForXP(playerXP[1]);
			cba = abc / 10;
			aaa = cba / 2;
			abc2 = aaa + cba;
			if (abc2 <= 1)
			{
				abc2 = 2;
			}
			playerLevel[1] = getLevelForXP(playerXP[1]);
			playerLevel[1] += abc2;
			sendFrame126("" + playerLevel[1] + "", 4008);
			deleteItem(137, GetItemSlot(137), 1);
			addItem(229, 1);
			updateRequired = true;
			appearanceUpdateRequired = true;
			GoOn = false;
			break;
		case 135: // defence pot (2)
			defPot = true;
			defPotTimer = 90;
			abc = getLevelForXP(playerXP[1]);
			cba = abc / 10;
			aaa = cba / 2;
			abc2 = aaa + cba;
			if (abc2 <= 1)
			{
				abc2 = 2;
			}
			playerLevel[1] = getLevelForXP(playerXP[1]);
			playerLevel[1] += abc2;
			sendFrame126("" + playerLevel[1] + "", 4008);
			deleteItem(135, GetItemSlot(135), 1);
			addItem(137, 1);
			updateRequired = true;
			appearanceUpdateRequired = true;
			GoOn = false;
			break;
		case 133: // defence pot (3)
			defPot = true;
			defPotTimer = 90;
			abc = getLevelForXP(playerXP[1]);
			cba = abc / 10;
			aaa = cba / 2;
			abc2 = aaa + cba;
			if (abc2 <= 1)
			{
				abc2 = 2;
			}
			playerLevel[1] = getLevelForXP(playerXP[1]);
			playerLevel[1] += abc2;
			sendFrame126("" + playerLevel[1] + "", 4008);
			deleteItem(133, GetItemSlot(133), 1);
			addItem(135, 1);
			updateRequired = true;
			appearanceUpdateRequired = true;
			GoOn = false;
			break;
		case 2432: // defence pot (4)
			defPot = true;
			defPotTimer = 90;
			abc = getLevelForXP(playerXP[1]);
			cba = abc / 10;
			aaa = cba / 2;
			abc2 = aaa + cba;
			if (abc2 <= 1)
			{
				abc2 = 2;
			}
			playerLevel[1] = getLevelForXP(playerXP[1]);
			playerLevel[1] += abc2;
			sendFrame126("" + playerLevel[1] + "", 4008);
			deleteItem(2432, GetItemSlot(2432), 1);
			addItem(133, 1);
			updateRequired = true;
			appearanceUpdateRequired = true;
			GoOn = false;
			break;
		case 3046: // mage pot (1)
			magePot = true;
			magePotTimer = 90;
			playerLevel[6] = getLevelForXP(playerXP[6]);
			playerLevel[6] += 4;
			sendFrame126("" + playerLevel[6] + "", 4014);
			deleteItem(3046, GetItemSlot(3046), 1);
			addItem(229, 1);
			updateRequired = true;
			appearanceUpdateRequired = true;
			GoOn = false;
			break;
		case 3044: // mage pot (2)
			magePot = true;
			magePotTimer = 90;
			playerLevel[6] = getLevelForXP(playerXP[6]);
			playerLevel[6] += 4;
			sendFrame126("" + playerLevel[6] + "", 4014);
			deleteItem(3044, GetItemSlot(3044), 1);
			addItem(3046, 1);
			updateRequired = true;
			appearanceUpdateRequired = true;
			GoOn = false;
			break;
		case 3042: // mage pot (3)
			magePot = true;
			magePotTimer = 90;
			playerLevel[6] = getLevelForXP(playerXP[6]);
			playerLevel[6] += 4;
			sendFrame126("" + playerLevel[6] + "", 4014);
			deleteItem(3042, GetItemSlot(3042), 1);
			addItem(3044, 1);
			updateRequired = true;
			appearanceUpdateRequired = true;
			GoOn = false;
			break;
		case 3040: // mage pot (4)
			magePot = true;
			magePotTimer = 90;
			playerLevel[6] = getLevelForXP(playerXP[6]);
			playerLevel[6] += 4;
			sendFrame126("" + playerLevel[6] + "", 4014);
			deleteItem(3040, GetItemSlot(3040), 1);
			addItem(3042, 1);
			updateRequired = true;
			appearanceUpdateRequired = true;
			GoOn = false;
			break;
		case 173: // range pot (1)
			rangePot = true;
			rangePotTimer = 90;
			abc = getLevelForXP(playerXP[4]);
			cba = abc / 10;
			abc2 = cba + 3;
			playerLevel[4] = getLevelForXP(playerXP[4]);
			playerLevel[4] += abc2;
			sendFrame126("" + playerLevel[6] + "", 4010);
			deleteItem(173, GetItemSlot(173), 1);
			addItem(229, 1);
			updateRequired = true;
			appearanceUpdateRequired = true;
			GoOn = false;
			break;
		case 171: // range pot (2)
			rangePot = true;
			rangePotTimer = 90;
			abc = getLevelForXP(playerXP[4]);
			cba = abc / 10;
			abc2 = cba + 3;
			playerLevel[4] = getLevelForXP(playerXP[4]);
			playerLevel[4] += abc2;
			sendFrame126("" + playerLevel[4] + "", 4010);
			deleteItem(171, GetItemSlot(171), 1);
			addItem(173, 1);
			updateRequired = true;
			appearanceUpdateRequired = true;
			GoOn = false;
			break;
		case 169: // range pot (3)
			rangePot = true;
			rangePotTimer = 90;
			abc = getLevelForXP(playerXP[4]);
			cba = abc / 10;
			abc2 = cba + 3;
			playerLevel[4] = getLevelForXP(playerXP[4]);
			playerLevel[4] += abc2;
			sendFrame126("" + playerLevel[4] + "", 4010);
			deleteItem(169, GetItemSlot(169), 1);
			addItem(171, 1);
			updateRequired = true;
			appearanceUpdateRequired = true;
			GoOn = false;
			break;
		case 2444: // range pot (4)
			rangePot = true;
			rangePotTimer = 90;
			abc = getLevelForXP(playerXP[4]);
			cba = abc / 10;
			abc2 = cba + 3;
			playerLevel[4] = getLevelForXP(playerXP[4]);
			playerLevel[4] += abc2;
			sendFrame126("" + playerLevel[4] + "", 4010);
			deleteItem(2444, GetItemSlot(2444), 1);
			addItem(169, 1);
			updateRequired = true;
			appearanceUpdateRequired = true;
			GoOn = false;
			break;
		case 149: // super attack pot (1)
			attPot = true;
			attPotTimer = 90;
			abc = getLevelForXP(playerXP[0]);
			cba = abc / 10;
			abc2 = cba * 2;
			if (abc2 <= 1)
			{
				abc2 = 2;
			}
			playerLevel[0] = getLevelForXP(playerXP[0]);
			playerLevel[0] += abc2;
			sendFrame126("" + playerLevel[0] + "", 4004);
			deleteItem(149, GetItemSlot(149), 1);
			addItem(229, 1);
			updateRequired = true;
			appearanceUpdateRequired = true;
			GoOn = false;
			break;
		case 147: // super attack pot (2)
			attPot = true;
			attPotTimer = 90;
			abc = getLevelForXP(playerXP[0]);
			cba = abc / 10;
			abc2 = cba * 2;
			if (abc2 <= 1)
			{
				abc2 = 2;
			}
			playerLevel[0] = getLevelForXP(playerXP[0]);
			playerLevel[0] += abc2;
			sendFrame126("" + playerLevel[0] + "", 4004);
			deleteItem(147, GetItemSlot(147), 1);
			addItem(149, 1);
			updateRequired = true;
			appearanceUpdateRequired = true;
			GoOn = false;
			break;
		case 145: // super attack pot (3)
			attPot = true;
			attPotTimer = 90;
			abc = getLevelForXP(playerXP[0]);
			cba = abc / 10;
			abc2 = cba * 2;
			if (abc2 <= 1)
			{
				abc2 = 2;
			}
			playerLevel[0] = getLevelForXP(playerXP[0]);
			playerLevel[0] += abc2;
			sendFrame126("" + playerLevel[0] + "", 4004);
			deleteItem(145, GetItemSlot(145), 1);
			addItem(147, 1);
			updateRequired = true;
			appearanceUpdateRequired = true;
			GoOn = false;
			break;
		case 2436: // super attack pot (4)
			attPot = true;
			attPotTimer = 90;
			abc = getLevelForXP(playerXP[0]);
			cba = abc / 10;
			abc2 = cba * 2;
			if (abc2 <= 1)
			{
				abc2 = 2;
			}
			playerLevel[0] = getLevelForXP(playerXP[0]);
			playerLevel[0] += abc2;
			sendFrame126("" + playerLevel[0] + "", 4004);
			deleteItem(2436, GetItemSlot(2436), 1);
			addItem(145, 1);
			updateRequired = true;
			appearanceUpdateRequired = true;
			GoOn = false;
			break;
		case 125: // attack pot (1)
			attPot = true;
			attPotTimer = 90;
			abc = getLevelForXP(playerXP[0]);
			cba = abc / 10;
			aaa = cba / 2;
			abc2 = aaa + cba;
			if (abc2 <= 1)
			{
				abc2 = 2;
			}
			playerLevel[0] = getLevelForXP(playerXP[0]);
			playerLevel[0] += abc2;
			sendFrame126("" + playerLevel[0] + "", 4004);
			deleteItem(125, GetItemSlot(125), 1);
			addItem(229, 1);
			updateRequired = true;
			appearanceUpdateRequired = true;
			GoOn = false;
			break;
		case 123: // attack pot (2)
			attPot = true;
			attPotTimer = 90;
			abc = getLevelForXP(playerXP[0]);
			cba = abc / 10;
			aaa = cba / 2;
			abc2 = aaa + cba;
			if (abc2 <= 1)
			{
				abc2 = 2;
			}
			playerLevel[0] = getLevelForXP(playerXP[0]);
			playerLevel[0] += abc2;
			sendFrame126("" + playerLevel[0] + "", 4004);
			deleteItem(123, GetItemSlot(123), 1);
			addItem(125, 1);
			updateRequired = true;
			appearanceUpdateRequired = true;
			GoOn = false;
			break;
		case 121: // attack pot (3)
			attPot = true;
			attPotTimer = 90;
			abc = getLevelForXP(playerXP[0]);
			cba = abc / 10;
			aaa = cba / 2;
			abc2 = aaa + cba;
			if (abc2 <= 1)
			{
				abc2 = 2;
			}
			playerLevel[0] = getLevelForXP(playerXP[0]);
			playerLevel[0] += abc2;
			sendFrame126("" + playerLevel[0] + "", 4004);
			deleteItem(121, GetItemSlot(121), 1);
			addItem(123, 1);
			updateRequired = true;
			appearanceUpdateRequired = true;
			GoOn = false;
			break;
		case 2428: // attack pot (4)
			strPot = true;
			attPotTimer = 90;
			abc = getLevelForXP(playerXP[0]);
			cba = abc / 10;
			aaa = cba / 2;
			abc2 = aaa + cba;
			if (abc2 <= 1)
			{
				abc2 = 2;
			}
			playerLevel[1] = getLevelForXP(playerXP[1]);
			playerLevel[1] += abc2;
			sendFrame126("@whi@" + playerLevel[1] + "", 4006);
			deleteItem(161, GetItemSlot(161), 1);
			addItem(229, 1);
			updateRequired = true;
			appearanceUpdateRequired = true;
			GoOn = false;
			break;
		case 2446: // Antipoison(4)
			PoisonDelay = 9999999;
			setAnimation(829);
			sendMessage("You drink a dose of the antipoison.");
			deleteItem(2446, getItemSlot(2446), 1);
			addItem(175, 1);
			break;
		case 175: // Antipoison(3)
			PoisonDelay = 9999999;
			setAnimation(829);
			sendMessage("You drink a dose of the antipoison.");
			deleteItem(175, getItemSlot(175), 1);
			addItem(177, 1);
			break;
		case 177: // Antipoison(2)
			PoisonDelay = 9999999;
			setAnimation(829);
			sendMessage("You drink a dose of the antipoison.");
			deleteItem(177, getItemSlot(177), 1);
			addItem(179, 1);
			break;
		case 179: // Antipoison(1)
			PoisonDelay = 9999999;
			setAnimation(829);
			sendMessage("You drink the last dose of the antipoison.");
			deleteItem(179, getItemSlot(179), 1);
			break;
		case 4079: // Yo-yo (play) by [M]ike
			startAnimation(1457);
			break;
		case 315: // Shrimps
			healing[1] = 3;
			healing[2] = 3;
			healing[3] = -1;
			break;
		case 319: // Anchovies
			healing[1] = 1;
			healing[2] = 1;
			healing[3] = -1;
			break;
		case 325: // Sardine
			healing[1] = 4;
			healing[2] = 4;
			healing[3] = -1;
			break;
		case 329: // Salmon
			healing[1] = 9;
			healing[2] = 9;
			healing[3] = -1;
			break;
		case 333: // Trout
			healing[1] = 7;
			healing[2] = 7;
			healing[3] = -1;
			break;
		case 339: // Cod
			healing[1] = 7;
			healing[2] = 7;
			healing[3] = -1;
			break;
		case 347: // Herring
			healing[1] = 5;
			healing[2] = 5;
			healing[3] = -1;
			break;
		case 351: // Pike
			healing[1] = 8;
			healing[2] = 8;
			healing[3] = -1;
			break;
		case 355: // Mackerel
			healing[1] = 6;
			healing[2] = 6;
			healing[3] = -1;
			break;
		case 361: // Tuna
			healing[1] = 10;
			healing[2] = 10;
			healing[3] = -1;
			break;
		case 365: // Bass
			healing[1] = 13;
			healing[2] = 13;
			healing[3] = -1;
			break;
		case 373: // Swordfish
			healing[1] = 14;
			healing[2] = 14;
			healing[3] = -1;
			break;
		case 379: // Lobster
			healing[1] = 12;
			healing[2] = 12;
			healing[3] = -1;
			break;
		case 385: // Shark
			healing[1] = 20;
			healing[2] = 20;
			healing[3] = -1;
			break;
		case 391: // Manta ray
			healing[1] = 25;
			healing[2] = 25;
			healing[3] = -1;
			break;
		case 397: // Sea turtle
			healing[1] = 25;
			healing[2] = 25;
			healing[3] = -1;
			break;
		case 534: // Baby Blue Dragon Bones
			prayer[2] = 30;
			break;
		case 536: // Dragon Bones
			prayer[2] = 72;
			setAnimation(829);
			break;
		case 526: // Bones
		case 528: // Burnt Bones
		case 2859: // Wolf Bones
			if (MethodCollection.random2(2) == 1)
			{
				prayer[2] = 4;
			}
			else
			{
				prayer[2] = 5;
			}
			break;
		case 530: // Bat Bones
			if (MethodCollection.random2(3) == 1)
			{
				prayer[2] = 4;
			}
			else
			{
				prayer[2] = 5;
			}
			break;
		case 532: // Big Bones
		case 3125: // Jogre Bones
			prayer[2] = 15;
			break;
		case 1885: // Ugthanki kebab
		case 2011: // Curry
			healing[1] = 19;
			healing[2] = 19;
			healing[3] = -1;
			break;
		case 1891: // Cake
			healing[1] = 4;
			healing[2] = 4;
			healing[3] = 1893;
			break;
		case 1893: // 2/3 cake
			healing[1] = 4;
			healing[2] = 4;
			healing[3] = 1895;
			break;
		case 1895: // Slice of cake
		case 1977: // Chocolatey milk
		case 2333: // Half a redberry pie
			healing[1] = 4;
			healing[2] = 4;
			healing[3] = -1;
			break;
		case 1897: // Chocolate cake
			healing[1] = 5;
			healing[2] = 5;
			healing[3] = 1899;
			break;
		case 1899: // 2/3 chocolate cake
			healing[1] = 5;
			healing[2] = 5;
			healing[3] = 1901;
			break;
		case 1961: // Easter Egg
			setAnimation(1835);
			resetanim = 6;
			healing[1] = 50;
			healing[2] = 50;
			healing[3] = -1;
			break;
		case 2331: // Half a meat pie
		case 2309: // Bread
			healing[1] = 5;
			healing[2] = 5;
			healing[3] = -1;
			break;
		case 1993: // Jug of wine
		case 2003: // Stew
		case 2303: // 1/2pineapple pizza
			healing[1] = 11;
			healing[2] = 11;
			healing[3] = -1;
			break;
		case 2149: // Lava eel
		case 2343: // Cooked oomlie wrap
			healing[1] = 14;
			healing[2] = 14;
			healing[3] = -1;
			break;
		case 2289: // Plain pizza
			healing[1] = 7;
			healing[2] = 7;
			healing[3] = 2291;
			break;
		case 2291: // 1/2 plain pizza
		case 2335: // Half an apple pie
			healing[1] = 7;
			healing[2] = 7;
			healing[3] = -1;
			break;
		case 2293: // Meat pizza
			healing[1] = 8;
			healing[2] = 8;
			healing[3] = 2295;
			break;
		case 2295: // 1/2 meat pizza
			healing[1] = 8;
			healing[2] = 8;
			healing[3] = -1;
			break;
		case 2297: // Anchovy pizza
			healing[1] = 9;
			healing[2] = 9;
			healing[3] = 2299;
			break;
		case 2299: // 1/2 anchovy pizza
			healing[1] = 9;
			healing[2] = 9;
			healing[3] = -1;
			break;
		case 2301: // Pineapple pizza
			healing[1] = 11;
			healing[2] = 11;
			healing[3] = 2303;
			break;
		case 2323: // Apple pie
			healing[1] = 7;
			healing[2] = 7;
			healing[3] = 2335;
			break;
		case 3225: // Redberry pie
			healing[1] = 5;
			healing[2] = 5;
			healing[3] = 2333;
			break;
		case 2327: // Meat pie
			healing[1] = 6;
			healing[2] = 6;
			healing[3] = 2331;
			break;
		case 2878: // Cooked chompy
			healing[1] = 10;
			healing[2] = 10;
			healing[3] = -1;
			break;
		case 3123: // Shaikahan Bones
			prayer[2] = 25;
			break;
		case 3144: // Cooked karambwan
		case 3146: // Cooked karambwan
			healing[1] = 18;
			healing[2] = 18;
			healing[3] = -1;
			break;
		case 3179: // Monkey Bones
			prayer[2] = 5;
			break;
		case 3369: // Thin snail meat
		case 3371: // Lean snail meat
		case 3373: // Fat snail meat
			healing[1] = 5;
			healing[2] = 9;
			healing[3] = -1;
			break;
		case 3381: // Cooked slimy eel
			healing[1] = 6;
			healing[2] = 10;
			healing[3] = -1;
			break;
		case 4291: // Cooked chicken
		case 4293: // Cooked meat
		case 4242: // Cup of tea (nettle tea)
			healing[1] = 3;
			healing[2] = 3;
			healing[3] = -1;
			break;
		case 4812: // Zogre Bones
			if (MethodCollection.random2(2) == 1)
			{
				prayer[2] = 22;
			}
			else
			{
				prayer[2] = 23;
			}
			break;
		case 4830: // Fayrg Bones
			prayer[2] = 87;
			break;
		case 4832: // Raurg Bones
			prayer[2] = 96;
			break;
		case 4834: // Ourg Bones
			prayer[2] = 10000;
			break;
		case 5003: // Cave eel
			healing[1] = 7;
			healing[2] = 11;
			healing[3] = -1;
			break;
		case 5988: // Sweetcorn
			healing[1] = (int) Math
					.floor((double) ((double) ((double) (playerLevel[HITPOINTS] / 100) * 10) + 0.5));
			healing[2] = (int) Math
					.floor((double) ((double) ((double) (playerLevel[HITPOINTS] / 100) * 10) + 0.5));
			healing[3] = -1;
			break;
		case 6297: // Spider on stick
		case 6299: // Spider on shaft
			healing[1] = 7;
			healing[2] = 10;
			healing[3] = -1;
			break;
		default:
			sendMessage("Nothing interesting is happening.");
			GoOn = false;
			break;
		}
		if (GoOn == false)
		{
			return false;
		}
		if (prayer[2] > 0)
		{
			prayer[0] = 1;
			prayer[4] = Item;
			prayer[5] = Slot;
		}
		else if (healing[1] > 0)
		{
			healing[0] = 1;
			healing[4] = Item;
			healing();
		}
		return true;
	}

	public boolean CheckForSkillUse4(int Item)
	{
		boolean GoOn = true;
		boolean IsFiremaking = false;
		switch (Item)
		{
		case 1511:
			if (IsItemInBag(590) == true)
			{
				IsFiremaking = true;
				firemaking[0] = 1;
				firemaking[1] = 1;
				firemaking[2] = 40;
			}
			else
			{
				sendMessage("You need a " + getItemName(591)
						+ " to light a fire.");
			}
			break;
		case 1513:
			if (IsItemInBag(590) == true)
			{
				IsFiremaking = true;
				firemaking[0] = 8;
				firemaking[1] = 75;
				firemaking[2] = 303;
			}
			else
			{
				sendMessage("You need a " + getItemName(591)
						+ " to light a fire.");
			}
			break;
		case 1515:
			if (IsItemInBag(590) == true)
			{
				IsFiremaking = true;
				firemaking[0] = 7;
				firemaking[1] = 60;
				if (MethodCollection.random2(2) == 1)
				{
					firemaking[2] = 202;
				}
				else
				{
					firemaking[2] = 203;
				}
			}
			else
			{
				sendMessage("You need a " + getItemName(591)
						+ " to light a fire.");
			}
			break;
		case 1517:
			if (IsItemInBag(590) == true)
			{
				IsFiremaking = true;
				firemaking[0] = 5;
				firemaking[1] = 45;
				firemaking[2] = 135;
			}
			else
			{
				sendMessage("You need a " + getItemName(591)
						+ " to light a fire.");
			}
			break;
		case 1519:
			if (IsItemInBag(590) == true)
			{
				IsFiremaking = true;
				firemaking[0] = 3;
				firemaking[1] = 30;
				firemaking[2] = 90;
			}
			else
			{
				sendMessage("You need a " + getItemName(591)
						+ " to light a fire.");
			}
			break;
		case 1521:
			if (IsItemInBag(590) == true)
			{
				IsFiremaking = true;
				firemaking[0] = 2;
				firemaking[1] = 15;
				firemaking[2] = 60;
			}
			else
			{
				sendMessage("You need a " + getItemName(591)
						+ " to light a fire.");
			}
			break;
		case 6333:
			if (IsItemInBag(590) == true)
			{
				IsFiremaking = true;
				firemaking[0] = 4;
				firemaking[1] = 35;
				firemaking[2] = 105;
			}
			else
			{
				sendMessage("You need a " + getItemName(591)
						+ " to light a fire.");
			}
			break;
		case 6332:
			if (IsItemInBag(590) == true)
			{
				IsFiremaking = true;
				firemaking[0] = 6;
				firemaking[1] = 50;
				if (MethodCollection.random2(2) == 1)
				{
					firemaking[2] = 157;
				}
				else
				{
					firemaking[2] = 158;
				}
			}
			else
			{
				sendMessage("You need a " + getItemName(591)
						+ " to light a fire.");
			}
			break;
		default:
			sendMessage("Nothing interesting is happening.");
			// println_debug("Firemaking Usage - ItemID: " + Item);
			GoOn = false;
			break;
		}
		if (GoOn == false)
		{
			return false;
		}
		if (IsFiremaking == true)
		{
			firemaking[4] = GetGroundItemID(Item, skillX, skillY);
			if (firemaking[4] == -1)
			{
				sendMessage("No logs on the ground.");
				resetFM();
				// println_debug("Firemaking bug: no logs on the ground.");
			}
		}
		return true;
	}

	public boolean IsItemInBag(int ItemID)
	{
		for (int i = 0; i < playerItems.length; i++)
		{
			if ((playerItems[i] - 1) == ItemID)
			{
				return true;
			}
		}
		return false;
	}

	public boolean AreXItemsInBag(int ItemID, int Amount)
	{
		int ItemCount = 0;
		for (int i = 0; i < playerItems.length; i++)
		{
			if ((playerItems[i] - 1) == ItemID)
			{
				ItemCount++;
			}
			if (ItemCount == Amount)
			{
				return true;
			}
		}
		return false;
	}

	public int GetItemSlot(int ItemID)
	{
		for (int i = 0; i < playerItems.length; i++)
		{
			if ((playerItems[i] - 1) == ItemID)
			{
				return i;
			}
		}
		return -1;
	}

	public int GetWepAnim()
	{
		if (playerEquipment[playerWeapon] == -1)
		{ // unarmed
			if (FightType == 2) // kick
			{
				if (playerEquipment[playerWeapon] == 5698)
				{ // dds
					if (FightType == 3) // slash
					{
						return 395;
					}
				}
				if (playerEquipment[playerWeapon] == 8002)
				{ // Dragon Claws
					if (FightType == 3) // slash
					{
						return 451;
					}
				}
				if (playerEquipment[playerWeapon] == 13643)
				{ // Stat Hammer
					if (FightType == 3) // slash
					{
						return 451;
					}
				}
				if (playerEquipment[playerWeapon] == 4212) // bows made by D D 3
				{
					return 426;
				}
				if (playerEquipment[playerWeapon] == 861)
				{
					return 426;
				}
				return 423;
			}
			else
			{
				return 422;
			}
		}
		if (playerEquipment[playerWeapon] == 4151) // whip
		{
			return 1658;
		}
		if (playerEquipment[playerWeapon] == 10101) // whip
		{
			return 1658;
		}
		if (playerEquipment[playerWeapon] == 10102) // whip
		{
			return 1658;
		}
		if (playerEquipment[playerWeapon] == 10103) // whip
		{
			return 1658;
		}
		if (playerEquipment[playerWeapon] == 10104) // whip
		{
			return 1658;
		}
		if (playerEquipment[playerWeapon] == 10105) // whip
		{
			return 1658;
		}
		if (playerEquipment[playerWeapon] == 10106) // whip
		{
			return 1658;
		}
		if (playerEquipment[playerWeapon] == 14889) // Shadow Sword
		{
			return 451;
		}
		if (playerEquipment[playerWeapon] == 8002) // Dragon claws
		{
			return 451;
		}
		if (playerEquipment[playerWeapon] == 13643) // Stat hammer
		{
			return 451;
		}
		if (playerEquipment[playerWeapon] == 15351) // Sara sword
		{
			return 406;
		}
		if (playerEquipment[playerWeapon] == 11593) // dd light
		{
			return 451;
		}
		if (playerEquipment[playerWeapon] == 8447) // cat toy
		{
			return 1658;
		}
		if (playerEquipment[playerWeapon] == 868) // throwing knives
		{
			return 385;
		}
		if (playerEquipment[playerWeapon] == 6527) // obby maul plox
		{
			return 2927;
		}
		if (playerEquipment[playerWeapon] == 4675) // ancient staff
		{
			Warlock999();
			return 1979;
		}
		if (playerEquipment[playerWeapon] == 2415) // ancient staff
		{
			IweedI();
			return 811;
		}
		if (playerEquipment[playerWeapon] == 6914) // master wand
		{
			Warlock999();
			return 1979;
		}
		if (playerEquipment[playerWeapon] == 15156) // dark bow
		{
			return 426;
		}
		if (playerEquipment[playerWeapon] == 6724) // seercull
		{
			return 426;
		}
		if (playerEquipment[playerWeapon] == 1305) // d long
		{
			return 451;
		}
		if (playerEquipment[playerWeapon] == 14915) // anchor
		{
			return 406;
		}
		if (playerEquipment[playerWeapon] == 6739) // dragon axe
		{
			return 451;
		}
		if (playerEquipment[playerWeapon] == 1321
				|| playerEquipment[playerWeapon] == 1323
				|| playerEquipment[playerWeapon] == 1325
				|| playerEquipment[playerWeapon] == 1327
				|| playerEquipment[playerWeapon] == 1329
				|| playerEquipment[playerWeapon] == 1327
				|| playerEquipment[playerWeapon] == 1321
				|| playerEquipment[playerWeapon] == 1333) // scimitars
		{
			return 451;
		}
		if (playerEquipment[playerWeapon] == 4587) // d scim
		{
			return 451;
		}
		if (playerEquipment[playerWeapon] == 7806) // Anger Sword
		{
			return 451;			
		}
		if (playerEquipment[playerWeapon] == 7807) // Anger BattleAxe
		{
			return 451;						
		}
		if (playerEquipment[playerWeapon] == 7808) // Anger Mace
		{
			return 451;		
        }
		if (playerEquipment[playerWeapon] == 7809) // Anger Spear
		{
			return 451;	
        }			
		if (playerEquipment[playerWeapon] == 35) // Excalibur
		{
			return 451;
		}
		if (playerEquipment[playerWeapon] == 15337) // Lava Blade
		{
			return 451;
		}
		if (playerEquipment[playerWeapon] == 746) // d scim
		{
			return 451;
		}
		if (playerEquipment[playerWeapon] == 3204) // dragon halberd
		{
			return 440;
		}
		if (playerEquipment[playerWeapon] == 6818) // bow-sword
		{
			return 440;
		}
		if (playerEquipment[playerWeapon] == 13665) // Trigodspear
		{
			return 440;
		}
		if (playerEquipment[playerWeapon] == 13459) // Blue lightsaber
		{
			return 440;
		}
		if (playerEquipment[playerWeapon] == 1283) // Double Dildo
		{
			return 440;
		}
		if (playerEquipment[playerWeapon] == 3202) // rune halberd
		{
			return 440;
		}
		if (playerEquipment[playerWeapon] == 4212
				|| playerEquipment[playerWeapon] == 859
				|| playerEquipment[playerWeapon] == 4212
				|| playerEquipment[playerWeapon] == 861
				|| playerEquipment[playerWeapon] == 6724) // bows
		{
			return 426;
		}
		if (playerEquipment[playerWeapon] == 4153) // maul
		{
			return 1665;
		}
		if (playerEquipment[playerWeapon] == 6528) // obby maul
		{
			return 2661;
		}
		if (playerEquipment[playerWeapon] == 5018) // bone club
		{
			return 451;
		}
		if (playerEquipment[playerWeapon] == 3101) // Rune claws
		{
			return 451;
		}
		if (playerEquipment[playerWeapon] == 7449) // noob smasher
		{
			return 1665;
		}
		if (playerEquipment[playerWeapon] == 1377) // dragon b axe
		{
			return 1833;
		}
		if (playerEquipment[playerWeapon] == 1373) // rune b axe
		{
			return 1833;
		}
		if (playerEquipment[playerWeapon] == 1434) // dragon mace
		{
			return 1833;
		}
		if (playerEquipment[playerWeapon] == 5018) // dragon mace
		{
			return 1833;
		}
		if (playerEquipment[playerWeapon] == 5730) // dragon spear
		{
			return 2080;
		}
		if (playerEquipment[playerWeapon] == 4718) // dharoks axe
		{
			return 2067;
		}
		if (playerEquipment[playerWeapon] == 4726) // guthans spear
		{
			return 2080;
		}
		if (playerEquipment[playerWeapon] == 4747) // torags hammers
		{
			return 2068;
		}
		if (playerEquipment[playerWeapon] == 4755) // veracs flail
		{
			return 2062;
		}
		if (playerEquipment[playerWeapon] == 4734) // karils x bow
		{
			return 2075;
		}
		if (playerEquipment[playerWeapon] == 13800) // rune crossbow
		{
			return 2075;
		}
		if (playerEquipment[playerWeapon] == 837) // crossbow
		{
			return 427;
		}
		if (playerEquipment[playerWeapon] == 10431) // rune crossbow
		{
			return 427;
		}
		if (playerEquipment[playerWeapon] == 1215
				|| playerEquipment[playerWeapon] == 1231
				|| playerEquipment[playerWeapon] == 5680
				|| playerEquipment[playerWeapon] == 5698) // dragon daggers
		{
			return 402;
		}
		if (playerEquipment[playerWeapon] == 6609
				|| playerEquipment[playerWeapon] == 1307
				|| playerEquipment[playerWeapon] == 1309
				|| playerEquipment[playerWeapon] == 1311
				|| playerEquipment[playerWeapon] == 1313
				|| playerEquipment[playerWeapon] == 1315
				|| playerEquipment[playerWeapon] == 1317
				|| playerEquipment[playerWeapon] == 1319) // 2 handers
		{
			return 407;
		}
		if (playerEquipment[playerWeapon] == 7158) // d2h
		{
			return 407;
		}
		if (playerEquipment[playerWeapon] == 15333) // Armadyl GS
		{
			return 406;
		}
		if (playerEquipment[playerWeapon] == 15334) // Bandos GS
		{
			return 406;
		}
		if (playerEquipment[playerWeapon] == 15335) // Saradomin GS
		{
			return 405;
		}
		if (playerEquipment[playerWeapon] == 15336) // Zamorak GS
		{
			return 407;
		}
		if (playerEquipment[playerWeapon] == 1319) // r2h
		{
			return 407;
		}
		if (playerEquipment[playerWeapon] == 1419) // scythe
		{
			return 408;
		}
		if (playerEquipment[playerWeapon] == 11115) // scythe
		{
			return 408;
		}
		if (playerEquipment[playerWeapon] == 4566) // rubber chicken
		{
			return 1833;
		}
		else
		{
			return 0x326;
		}
	}

	public int GetRunAnim(int id)
	{
		if (id == 4151) // whip
		{
			return 1661;
		}
		if (id == 10101) // whip
		{
			return 1661;
		}
		if (id == 10102) // whip
		{
			return 1661;
		}
		if (id == 10103) // whip
		{
			return 1661;
		}
		if (id == 10104) // whip
		{
			return 1661;
		}
		if (id == 10105) // whip
		{
			return 1661;
		}
		if (id == 10106) // whip
		{
			return 1661;
		}
		if (id == 8447) // cat toy
		{
			return 1661;
		}
		if (id == 6818) // bow-sword
		{
			return 744;
		}
		if (id == 13665) // trigodspear
		{
			return 744;
		}
		if (id == 13459) // Blue lightsaber
		{
			return 744;
		}
		if (id == 1283) // Double Dildo
		{
			return 744;
		}
		if (id == 4734 || id == 837) // karils x bow
		{
			return 2077;
		}
		if (id == 4153) // maul
		{
			return 1664;
		}
		if (id == 11593) // d light
		{
			return 2067;
		}
		if (id == 1419) // scythe
		{
			return 1664;
		}
		if (id == 11115) // scythe
		{
			return 1664;
		}
		if (id == 7449) // noobsmasher
		{
			return 1664;
		}
		else
		{
			return 0x338;
		}
	}

	public int GetWalkAnim(int id)
	{
		if (id == 4718) // dharoks axe
		{
			return 0x67F;
		}
		if (id == 4039 || id == 4037 || id == 1379 || id == 3204 || id == 3202
				|| id == 1381 || id == 1383 || id == 1385 || id == 1387
				|| id == 1389 || id == 1391 || id == 1393 || id == 1395
				|| id == 1397 || id == 1399 || id == 1401 || id == 1403
				|| id == 145 || id == 1407 || id == 1409 || id == 3053
				|| id == 3054 || id == 4170 || id == 4675 || id == 4710
				|| id == 6526 || id == 4726 || id == 6562 || id == 6563
				|| id == 6914 || id == 5730) // staves + d long and most other
												// weps with str8 up emote
		{
			return 1146;
		}
		if (playerEquipment[playerFeet] == 4084) // sled
		{
			return 755;
		}
		if (id == 4565) // basket of eggs :)
		{
			return 1836;
		}
		if (id == 4755) // veracs flail
		{
			return 2060;
		}
		if (id == 4734 || id == 837) // karils x bow
		{
			return 2076;
		}
		if (id == 4153 || id == 15334 || id == 1419 || id == 11115) // maul
		{
			return 1663;
		}
		if (id == 7158 || id == 4718 || id == 1319 || id == 6528 || id == 14915
				|| id == 15333 || id == 15334 || id == 15351 || id == 15335
				|| id == 15336) // 2h + gr8 axe
		{
			return 2064;
		}
		if (id == 7449) // noob smasher
		{
			return 1663;
		}
		if (id == 4151) // whip
		{
			return 1661;
		}
		if (id == 10101) // whip
		{
			return 1661;
		}
		if (id == 10102) // whip
		{
			return 1661;
		}
		if (id == 10103) // whip
		{
			return 1661;
		}
		if (id == 10104) // whip
		{
			return 1661;
		}
		if (id == 10105) // whip
		{
			return 1661;
		}
		if (id == 10106) // whip
		{
			return 1661;
		}
		if (id == 8447) // cat toy
		{
			return 1661;
		}
		else
		{
			return 0x333;
		}
	}

	public int GetStandAnim(int id)
	{
		if ((id == 15333) || (id == 15334) || (id == 15351) || (id == 15335)
				|| (id == 15336)) // Godswords.
		{
			return 2273;
		}
		if (id == 4718) // dharoks axe
		{
			return 2065;
		}
		if (id == 4755) // veracs flail
		{
			return 2061;
		}
		if (id == 4734 || id == 837) // karils x bow
		{
			return 2074;
		}
		if (id == 4153 || id == 15334 || id == 15336 || id == 1419
				|| id == 11115) // maul
		{
			return 1662;
		}
		if (id == 7449) // noob smasher
		{
			return 1662;
		}
		if (id == 4565) // basket of eggs :)
		{
			return 1836;
		}
		if (id == 1305 || id == 1379 || id == 1381 || id == 1383 || id == 1385
				|| id == 1387 || id == 1389 || id == 1391 || id == 1393
				|| id == 1395 || id == 1397 || id == 1399 || id == 1401
				|| id == 1403 || id == 145 || id == 1407 || id == 1409
				|| id == 3053 || id == 3054 || id == 4170 || id == 4675
				|| id == 4710 || id == 6526 || id == 4726 || id == 6562
				|| id == 6563 || id == 6914 || id == 5730) // staves
		{
			return 809;
		}
		if (id == 7158 || id == 1319 || id == 6528 || id == 14915) // 2h
		{
			return 2065;
		}
		if (id == 3204 || id == 3202) // halberd
		{
			return 809;
		}
		else
		{
			return 0x328;
		}
	}

	public int GetBlockAnim(int id)
	{
		if (id == 4755) // veracs flail
		{
			return 2063;
		}
		if (id == 4151) // whip
		{
			return 1659;
		}
		if (id == 10101) // whip
		{
			return 1659;
		}
		if (id == 10102) // whip
		{
			return 1659;
		}
		if (id == 10103) // whip
		{
			return 1659;
		}
		if (id == 10104) // whip
		{
			return 1659;
		}
		if (id == 10105) // whip
		{
			return 1659;
		}
		if (id == 10106) // whip
		{
			return 1659;
		}
		if (id == 10229) // defender
		{
			return 1659;
		}
		if (id == 1171) // wooden shield
		{
			return 403;
		}
		if (id == 1185) // rune sq shield
		{
			return 403;
		}
		if (id == 1187) // dragon sq shield
		{
			return 403;
		}
		if (id == 1191) // iron kite shield
		{
			return 403;
		}
		if (id == 1201) // rune kite shield
		{
			return 403;
		}
		if (id == 2659) // zammy kite shield
		{
			return 403;
		}
		if (id == 2667) // sara kite shield
		{
			return 403;
		}
		if (id == 2675) // guthix kite shield
		{
			return 403;
		}
		if (id == 3122) // granite shield
		{
			return 403;
		}
		if (id == 3488) // gilded kite shield
		{
			return 403;
		}
		if (id == 4156) // mirror shield
		{
			return 403;
		}
		if (id == 6524) // obby shield
		{
			return 403;
		}
		if (id == 4153) // maul
		{
			return 1666;
		}
		if (id == 1419) // scythe
		{
			return 1666;
		}
		if (id == 11115) // scythe
		{
			return 1666;
		}
		else
		{
			return 1834;
		}
	}

	public int GetXItemsInBag(int ItemID)
	{
		int ItemCount = 0;
		for (int i = 0; i < playerItems.length; i++)
		{
			if ((playerItems[i] - 1) == ItemID)
			{
				ItemCount++;
			}
		}
		return ItemCount;
	}

	public void Warlock999()
	{
		hitDiff = 30 + MethodCollection.random(20);
		inCombat();
		attackPlayersWithin(369, 30, 1);
		actionTimer = 50;
		setAnimation(369);
		teleportToX = absX;
		teleportToY = absY;
		stillgfx(368, absY, absX);
	}

	public void IweedI()
	{
		hitDiff = 30 + MethodCollection.random(20);
		inCombat();
		actionTimer = 50;
		setAnimation(811);
		teleportToX = absX;
		teleportToY = absY;
		stillgfx(76, absY, absX);
	}

	public void runecrossbow()
	{
		if (MethodCollection.random(20) == 1
				&& playerEquipment[playerWeapon] == 13800
				&& playerEquipment[playerArrows] == 13817)
		{
			sendMessage("Your bolt came!");
			hitDiff = 40 + MethodCollection.random(40);
			setAnimation(811);
			stillgfx(197, absY, absX);
			stillgfx(446, EnemyY, EnemyX);
			stillgfx(453, EnemyY, EnemyX);
			PlayerHandler.players[AttackingOn].currentHealth -= hitDiff;
			PlayerHandler.players[AttackingOn].updateRequired = true;
			PlayerHandler.players[AttackingOn].hitUpdateRequired = true;
		}
	}

	public void AddDroppedItems()
	{
		if (IsDropping == false)
		{
			IsDropping = true;
			int tmpX = 0;
			int tmpY = 0;
			int calcX = 0;
			int calcY = 0;
			for (int i = 0; i < ItemHandler.DropItemCount; i++)
			{
				if (ItemHandler.DroppedItemsID[i] > -1)
				{
					tmpX = ItemHandler.DroppedItemsX[i];
					tmpY = ItemHandler.DroppedItemsY[i];
					calcX = tmpX - absX;
					calcY = tmpY - absY;
					if (calcX >= -16 && calcX <= 15 && calcY >= -16
							&& calcY <= 15 && MustDelete[i] == false
							&& ItemHandler.DroppedItemsH[i] == heightLevel)
					{
						if (IsDropped[i] == false
								&& (ItemHandler.DroppedItemsDDelay[i] <= 0 || ItemHandler.DroppedItemsDropper[i] == playerId))
						{
							IsDropped[i] = true;
							outStream.createFrame(85);
							outStream
									.putByteC((ItemHandler.DroppedItemsY[i] - 8 * mapRegionY));
							outStream
									.putByteC((ItemHandler.DroppedItemsX[i] - 8 * mapRegionX));
							outStream.createFrame(44); // create item frame
							outStream
									.putWordBigEndianA(ItemHandler.DroppedItemsID[i]);
							outStream.putShort(ItemHandler.DroppedItemsN[i]); // amount
							outStream.put(0); // x(4 MSB) y(LSB) coords
						}
					}
					else if (IsDropped[i] == true || MustDelete[i] == true)
					{
						outStream.createFrame(85);
						outStream
								.putByteC((ItemHandler.DroppedItemsY[i] - 8 * mapRegionY));
						outStream
								.putByteC((ItemHandler.DroppedItemsX[i] - 8 * mapRegionX));
						outStream.createFrame(156); // remove item frame
						outStream.putByteS(0); // x(4 MSB) y(LSB) coords
						outStream.putShort(ItemHandler.DroppedItemsID[i]);
						int LastPlayerInList = -1;
						int TotalPlayers = 0;
						for (int j = 1; j < PlayerHandler.maxPlayers; j++)
						{
							if (PlayerHandler.players[j] != null)
							{
								LastPlayerInList = j;
								TotalPlayers++;
							}
						}
						if (MustDelete[i] == true)
						{
							MustDelete[i] = false;
							ItemHandler.DroppedItemsDeletecount[i]++;
							if ((LastPlayerInList == playerId || LastPlayerInList == -1)
									&& ItemHandler.DroppedItemsDeletecount[i] == TotalPlayers)
							{
								if (ItemHandler.DroppedItemsAlwaysDrop[i] == true)
								{
									ItemHandler.DroppedItemsDropper[i] = -1;
									ItemHandler.DroppedItemsDDelay[i] = ItemHandler.SDID;
								}
								else
								{
									ItemHandler.getSingleton().ResetItem(i);
								}
								for (int j = 1; j < PlayerHandler.maxPlayers; j++)
								{
									if (PlayerHandler.players[j] != null)
									{
										PlayerHandler.players[j].IsDropped[i] = false;
									}
								}
							}
						}
						else
						{
							IsDropped[i] = false;
						}
					}
				}
			}
			IsDropping = false;
		}
	}

	public int Tradecompete = 0;
	public int Publicchat = 0;

	public void pmstatus(int status)
	{ // status: loading = 0 connecting = 1
		// fine = 2
		outStream.createFrame(221);
		outStream.put(status);
	}

	public boolean isinpm(long l)
	{
		for (int i = 0; i < friends.length; i++)
		{
			if (friends[i] != 0)
			{
				if (l == friends[i])
				{
					return true;
				}
			}
		}
		return false;
	}

	public void pmupdate(int pmid, int world)
	{
		long l = MethodCollection
				.playerNameToInt64(PlayerHandler.players[pmid].username);
		if (PlayerHandler.players[pmid].Privatechat == 0)
		{
			for (int i = 0; i < friends.length; i++)
			{
				if (friends[i] != 0)
				{
					if (l == friends[i])
					{
						loadpm(l, world);
						return;
					}
				}
			}
		}
		else if (PlayerHandler.players[pmid].Privatechat == 1)
		{
			for (int i1 = 0; i1 < friends.length; i1++)
			{
				if (friends[i] != 0)
				{
					if (l == friends[i1])
					{
						if (PlayerHandler.players[pmid].isinpm(MethodCollection
								.playerNameToInt64(username))
								&& playerRights > 2)
						{
							loadpm(l, world);
							return;
						}
						else
						{
							loadpm(l, 0);
							return;
						}
					}
				}
			}
		}
		else if (PlayerHandler.players[pmid].Privatechat == 2)
		{
			for (int i2 = 0; i2 < friends.length; i2++)
			{
				if (friends[i] != 0)
				{
					if (l == friends[i2] && playerRights < 2)
					{
						loadpm(l, 0);
						return;
					}
				}
			}
		}
	}

	public void sendpm(long name, int rights, byte[] chatmessage,
			int messagesize)
	{
		outStream.createFrameVarSize(196);
		outStream.putLong(name);
		outStream.putInt(handler.lastchatid++); // must be different for
												// each message
		outStream.put(rights);
		outStream.put(chatmessage, messagesize, 0);
		outStream.endFrameVarSize();
	}

	public void loadpm(long name, int world)
	{
		if (world != 0)
		{
			world += 9;
		}
		else if (world == 0)
		{
			world += 1;
		}
		outStream.createFrame(50);
		outStream.putLong(name);
		outStream.put(world);
	}

	public int spellID = 0;

	public void AttackMage(int index)
	{
		int playerIndex = index; // inStream.readSignedWordA();
		// if (username.equalsIgnoreCase("admin")) {
		// println_debug("playerIndex: " + playerIndex + " spellID: "
		// + spellID);
		// }
		Client castOnPlayer = (Client) PlayerHandler.players[playerIndex];
		setAnimation(711);
		int EnemyX = PlayerHandler.players[playerIndex].absX;
		int EnemyY = PlayerHandler.players[playerIndex].absY;
		int EnemyHP = PlayerHandler.players[playerIndex].playerLevel[HITPOINTS];
		int heal = 0;
		int hitDiff = 0;
		int mageXP = 0;
		faceNPC(32768 + index);
		if (spellID == 1539
				&& (playerRights == 1 || playerRights == 2 || playerRights == 3))
		{
			if (Inair == false)
			{
				actionAmount++;
				teleportToX = absX;
				teleportToY = absY;
				setAnimation(1500);
				sendMessage("You rise to the air.");
				playerSE = 1501;
				playerSER = 1501;
				playerSEW = 1501;
				actionTimer = 0;
				Inair = true;
			}
			if (Inair == true && actionTimer <= 0)
			{
				teleportToX = absX;
				teleportToY = absY;
				setAnimation(1502);
				sendMessage("You electricute " + castOnPlayer.username + "!");
				castOnPlayer.sendMessage("You get electricuted!");
				castOnPlayer.hitDiff = 1 + MethodCollection.random(6);
				teleportToX = absX;
				teleportToY = absY;
				castOnPlayer.setAnimation(3170);
				castOnPlayer.entangle();
				playerSE = 1501;
				playerSER = 1501;
				playerSEW = 1501;
				if ((EnemyHP - hitDiff) < 0)
				{
					hitDiff = EnemyHP;
				}
				castOnPlayer.KillerId = playerId;
				castOnPlayer.updateRequired = true;
				castOnPlayer.hitUpdateRequired = true;
			}
		}
		if (playerLevel[6] > 0)
		{
			int casterX = absX;
			int casterY = absY;
			int offsetX = (casterX - EnemyX) * -1;
			int offsetY = (casterY - EnemyY) * -1;
			if (spellID == 1158 && playerLevel[3] >= 80)
			{ // Fire Strike - now
				// Shock Wave
				ProjectileSpellPlayer(448, 100, 101, absY, absX, offsetY,
						offsetX, index, EnemyY, EnemyX, 30, 1, 566, 558, 554,
						1, 1, 3);
				castOnPlayer
						.stillgfx(481, castOnPlayer.absY, castOnPlayer.absX);
				castOnPlayer
						.stillgfx(453, castOnPlayer.absY, castOnPlayer.absX);
				hitDiff = 5 + MethodCollection.random(25);
				attackNPCSWithin(453, 30, 4);
				attackPlayersWithin(453, 30, 4);
			}
			if (spellID == 12975) // smoke barrage (lvl 86 spell)
			{
				if (playerLevel[6] >= 95)
				{
					if ((playerHasItemAmount(560, 10) == false)
							|| (playerHasItemAmount(565, 5) == false)
							|| (playerHasItemAmount(554, 15) == false)
							|| (playerHasItemAmount(556, 15) == false))
					{
						sendMessage("You do not have enough runes to cast this spell.");
						sendMessage("You need 10 " + getItemName(560) + ", 5 "
								+ getItemName(565) + ", 15 " + getItemName(554)
								+ " and 15 " + getItemName(556));
					}
					else if ((playerHasItemAmount(560, 10) == true)
							&& (playerHasItemAmount(565, 5) == true)
							&& (playerHasItemAmount(554, 15) == true)
							&& (playerHasItemAmount(556, 15) == true))
					{
						startAnimation(1979);
						attackPlayersWithin(391, 39, 10);
						hitDiff = 5 + MethodCollection.random(25);
						castOnPlayer.PoisonPlayer();
						castOnPlayer.inCombat();
						inCombat();
						PkingDelay = 7;
						sendMessage("You poison the enemy.");
						deleteItem(560, getItemSlot(560), 10);
						deleteItem(565, getItemSlot(565), 5);
						deleteItem(554, getItemSlot(554), 15);
						deleteItem(556, getItemSlot(556), 15);
						teleportToX = absX;
						teleportToY = absY;
					}
				}
				else if (playerLevel[6] <= 95)
				{
					sendMessage("You need a magic level of 96 to cast this spell.");
				}
			}
			if (spellID == 12881) // ice burst (lvl 70 spell)
			{
				if (playerLevel[6] >= -74)
				{
					if ((playerHasItemAmount(560, 10) == false)
							|| (playerHasItemAmount(565, 10) == false)
							|| (playerHasItemAmount(554, 15) == false))
					{
						sendMessage("You do not have enough runes to cast this spell.");
						sendMessage("You need 10 " + getItemName(560) + ", 10 "
								+ getItemName(565) + " and 15 "
								+ getItemName(554));
					}
					else if ((playerHasItemAmount(560, 10) == true)
							&& (playerHasItemAmount(565, 10) == true)
							&& (playerHasItemAmount(554, 15) == true))
					{
						startAnimation(1979);
						hitDiff = 5 + MethodCollection.random(13);
						castOnPlayer.PoisonPlayer();
						castOnPlayer.entangle();
						castOnPlayer.inCombat();
						inCombat();
						PkingDelay = 7;
						stillgfx(363, castOnPlayer.absY, castOnPlayer.absX);
						castOnPlayer.stillgfx(363, castOnPlayer.absY,
								castOnPlayer.absX);
						castOnPlayer.sendMessage("You have been frozen!");
						sendMessage("You poison and freeze the enemy!");
						deleteItem(560, getItemSlot(560), 10);
						deleteItem(565, getItemSlot(565), 10);
						deleteItem(554, getItemSlot(554), 15);
						teleportToX = absX;
						teleportToY = absY;
					}
				}
				else if (playerLevel[6] <= 74)
				{
					sendMessage("You need a magic level of 75 to cast this spell.");
				}
			}
			if (spellID == 12891) // ice barrage (lvl 150 spell)
			{
				if (playerLevel[6] > 149)
				{
					if ((playerHasItemAmount(560, 40) == false)
							|| (playerHasItemAmount(565, 40) == false)
							|| (playerHasItemAmount(555, 60) == false))
					{
						sendMessage("You do not have enough runes to cast this spell.");
						sendMessage("You need 40 " + getItemName(560) + ", 40 "
								+ getItemName(565) + " and 60 "
								+ getItemName(555));
					}
					else if ((playerHasItemAmount(560, 40) == true)
							&& (playerHasItemAmount(565, 40) == true)
							&& (playerHasItemAmount(554, 60) == true))
					{
						hitDiff = 5 + MethodCollection.random(45);
						startAnimation(1979);
						castOnPlayer.PoisonPlayer();
						castOnPlayer.uberentangle();
						castOnPlayer.inCombat();
						inCombat();
						stillgfx(369, castOnPlayer.absY, castOnPlayer.absX);
						castOnPlayer.sendMessage("You have been frozen!");
						sendMessage("You poison and freeze the enemy!");
						deleteItem(560, getItemSlot(560), 40);
						deleteItem(565, getItemSlot(565), 40);
						deleteItem(554, getItemSlot(555), 60);
						teleportToX = absX;
						teleportToY = absY;
					}
				}
				else if (playerLevel[6] <= 150)
				{
					sendMessage("You need a magic level of 150 to cast this spell.");
				}
			}
			if (spellID == 1156) // Earth Strike Good Spell
			{
				if (playerLevel[6] > 180)
				{
					startAnimation(1914);
					hitDiff = 5 + MethodCollection.random(42);
					castOnPlayer.inCombat();
					castOnPlayer.PoisonPlayer();
					castOnPlayer.Teleblock();
					castOnPlayer.burst();
					inCombat();
					actionTimer = 10;
					stillgfx(292, castOnPlayer.absY, castOnPlayer.absX);
					stillgfx(292, castOnPlayer.absY - 1, castOnPlayer.absX);
					stillgfx(292, castOnPlayer.absY + 1, castOnPlayer.absX);
					stillgfx(292, castOnPlayer.absY, castOnPlayer.absX - 1);
					stillgfx(292, castOnPlayer.absY, castOnPlayer.absX + 1);
					stillgfx(311, absY, absX);
					stillgfx(287, castOnPlayer.absY, castOnPlayer.absX);
					castOnPlayer.stillgfx(305, castOnPlayer.absY,
							castOnPlayer.absX);
					castOnPlayer.sendMessage("Eat It!");
					deleteItem(560, getItemSlot(560), 4);
					deleteItem(565, getItemSlot(565), 2);
					deleteItem(554, getItemSlot(555), 6);
					teleportToX = absX;
					teleportToY = absY;
				}
				else if (playerLevel[6] <= 180)
				{
					sendMessage("You need 180 Mage For This!");
				}
			}
			if (spellID == 1152) // Wind Strike High Mage Spell
			{
				if (playerLevel[6] > 150)
				{
					startAnimation(439);
					hitDiff = 5 + MethodCollection.random(38);
					castOnPlayer.inCombat();
					castOnPlayer.burst();
					inCombat();
					actionTimer = 10;
					stillgfx(187, castOnPlayer.absY, castOnPlayer.absX);
					stillgfx(77, castOnPlayer.absY - 1, castOnPlayer.absX);
					stillgfx(77, castOnPlayer.absY + 1, castOnPlayer.absX);
					stillgfx(77, castOnPlayer.absY, castOnPlayer.absX - 1);
					stillgfx(77, castOnPlayer.absY, castOnPlayer.absX + 1);
					stillgfx(582, castOnPlayer.absY + 1, castOnPlayer.absX + 1);
					stillgfx(346, castOnPlayer.absY, castOnPlayer.absX);
					castOnPlayer.sendMessage("Pwnt");
					deleteItem(560, getItemSlot(560), 4);
					deleteItem(565, getItemSlot(565), 2);
					deleteItem(554, getItemSlot(555), 6);
					teleportToX = absX;
					teleportToY = absY;
				}
				else if (playerLevel[6] <= 175)
				{
					sendMessage("You need 150 Mage to cast this spell.");
				}
			}
			if (spellID == 1154) // Water Strike Mod Strike Mod Spell
			{
				if (playerRights <= 1)
				{
					sendMessage("You need Moderator priveleges to cast this spell!");
				}
				else if (playerRights >= 1)
				{
					startAnimation(439);
					hitDiff = 5 + MethodCollection.random(50);
					castOnPlayer.inCombat();
					castOnPlayer.burst();
					castOnPlayer.PoisonPlayer();
					inCombat();
					actionTimer = 10;
					stillgfx(76, castOnPlayer.absY - 1, castOnPlayer.absX);
					stillgfx(76, castOnPlayer.absY + 1, castOnPlayer.absX);
					stillgfx(76, castOnPlayer.absY, castOnPlayer.absX - 1);
					stillgfx(76, castOnPlayer.absY, castOnPlayer.absX + 1);
					stillgfx(547, castOnPlayer.absY, castOnPlayer.absX);
					castOnPlayer.sendMessage("Destroyed!");
					deleteItem(560, getItemSlot(560), 4);
					deleteItem(565, getItemSlot(565), 2);
					deleteItem(554, getItemSlot(555), 6);
					teleportToX = absX;
					teleportToY = absY;
				}
				else if (playerLevel[6] <= 1)
				{
					sendMessage("You need to be An Moderator to cast this spell!");
				}
			}
			if (spellID == 12037)
			{
				if (playerRights <= 1)
				{
					sendMessage("You need Admin priveleges to cast this spell!");
				}
				else if (playerRights >= 2)
				{
					startAnimation(2927);
					hitDiff = 5 + MethodCollection.random(80);
					castOnPlayer.inCombat();
					castOnPlayer.rapture();
					castOnPlayer.PoisonPlayer();
					inCombat();
					actionTimer = 10;
					attackPlayersWithin(437, 10, 10);
					stillgfx(572, absY, absX); // Hole in ground
					stillgfx(571, absY, absX); // Dust storm
					stillgfx(582, castOnPlayer.absY + 1, castOnPlayer.absX + 1);
					stillgfx(498, castOnPlayer.absY + 4, castOnPlayer.absX - 1);
					stillgfx(498, castOnPlayer.absY - 4, castOnPlayer.absX + 1);
					stillgfx(498, castOnPlayer.absY + 1, castOnPlayer.absX + 4);
					stillgfx(498, castOnPlayer.absY - 1, castOnPlayer.absX - 4);
					stillgfx(498, castOnPlayer.absY, castOnPlayer.absX - 4);
					stillgfx(498, castOnPlayer.absY, castOnPlayer.absX + 4);
					stillgfx(498, castOnPlayer.absY + 4, castOnPlayer.absX);
					stillgfx(498, castOnPlayer.absY - 4, castOnPlayer.absX);
					stillgfx(547, castOnPlayer.absY + 1, castOnPlayer.absX - 1);
					stillgfx(547, castOnPlayer.absY - 1, castOnPlayer.absX + 1);
					stillgfx(547, castOnPlayer.absY + 1, castOnPlayer.absX + 1);
					stillgfx(547, castOnPlayer.absY - 1, castOnPlayer.absX - 1);
					stillgfx(437, castOnPlayer.absY, castOnPlayer.absX - 1);
					stillgfx(437, castOnPlayer.absY, castOnPlayer.absX + 1);
					stillgfx(437, castOnPlayer.absY + 1, castOnPlayer.absX);
					stillgfx(437, castOnPlayer.absY - 1, castOnPlayer.absX);
					stillgfx(287, castOnPlayer.absY, castOnPlayer.absX);
					stillgfx(287, castOnPlayer.absY + 3, castOnPlayer.absX);
					stillgfx(287, castOnPlayer.absY - 3, castOnPlayer.absX);
					stillgfx(287, castOnPlayer.absY, castOnPlayer.absX + 3);
					stillgfx(287, castOnPlayer.absY, castOnPlayer.absX - 3);
					stillgfx(287, castOnPlayer.absY + 2, castOnPlayer.absX - 2);
					stillgfx(287, castOnPlayer.absY - 2, castOnPlayer.absX + 2);
					stillgfx(287, castOnPlayer.absY + 2, castOnPlayer.absX + 2);
					stillgfx(287, castOnPlayer.absY - 2, castOnPlayer.absX - 2);
					stillgfx(453, castOnPlayer.absY + 1, castOnPlayer.absX - 1);
					stillgfx(453, castOnPlayer.absY - 1, castOnPlayer.absX + 1);
					stillgfx(453, castOnPlayer.absY + 1, castOnPlayer.absX + 1);
					stillgfx(453, castOnPlayer.absY - 1, castOnPlayer.absX - 1);
					stillgfx(453, castOnPlayer.absY, castOnPlayer.absX - 1);
					stillgfx(453, castOnPlayer.absY, castOnPlayer.absX + 1);
					stillgfx(453, castOnPlayer.absY + 1, castOnPlayer.absX);
					stillgfx(453, castOnPlayer.absY - 1, castOnPlayer.absX);
					stillgfx(453, castOnPlayer.absY + 2, castOnPlayer.absX - 2);
					stillgfx(453, castOnPlayer.absY - 2, castOnPlayer.absX + 2);
					stillgfx(453, castOnPlayer.absY + 2, castOnPlayer.absX + 2);
					stillgfx(453, castOnPlayer.absY - 2, castOnPlayer.absX - 2);
					stillgfx(453, castOnPlayer.absY, castOnPlayer.absX - 2);
					stillgfx(453, castOnPlayer.absY, castOnPlayer.absX + 2);
					stillgfx(453, castOnPlayer.absY + 2, castOnPlayer.absX);
					stillgfx(453, castOnPlayer.absY - 2, castOnPlayer.absX);
					stillgfx(453, castOnPlayer.absY, castOnPlayer.absX - 3);
					stillgfx(453, castOnPlayer.absY, castOnPlayer.absX + 3);
					stillgfx(453, castOnPlayer.absY + 3, castOnPlayer.absX);
					stillgfx(453, castOnPlayer.absY - 3, castOnPlayer.absX);
					stillgfx(453, castOnPlayer.absY + 1, castOnPlayer.absX - 2);
					stillgfx(453, castOnPlayer.absY - 1, castOnPlayer.absX + 2);
					stillgfx(453, castOnPlayer.absY + 1, castOnPlayer.absX + 2);
					stillgfx(453, castOnPlayer.absY - 1, castOnPlayer.absX - 2);
					stillgfx(453, castOnPlayer.absY + 2, castOnPlayer.absX - 1);
					stillgfx(453, castOnPlayer.absY - 2, castOnPlayer.absX + 1);
					stillgfx(453, castOnPlayer.absY + 2, castOnPlayer.absX + 1);
					stillgfx(453, castOnPlayer.absY - 2, castOnPlayer.absX - 1);
					stillgfx(453, castOnPlayer.absY + 1, castOnPlayer.absX - 3);
					stillgfx(453, castOnPlayer.absY - 1, castOnPlayer.absX + 3);
					stillgfx(453, castOnPlayer.absY + 1, castOnPlayer.absX + 3);
					stillgfx(453, castOnPlayer.absY - 1, castOnPlayer.absX - 3);
					stillgfx(453, castOnPlayer.absY + 3, castOnPlayer.absX - 1);
					stillgfx(453, castOnPlayer.absY - 3, castOnPlayer.absX + 1);
					stillgfx(453, castOnPlayer.absY + 3, castOnPlayer.absX + 1);
					stillgfx(453, castOnPlayer.absY - 3, castOnPlayer.absX - 1);
					castOnPlayer.sendMessage("You Got Destroyed!");
					castOnPlayer.sendMessage("Damn, That Sucks!");
					deleteItem(560, getItemSlot(560), 4);
					deleteItem(565, getItemSlot(565), 2);
					deleteItem(554, getItemSlot(555), 6);
					teleportToX = absX;
					teleportToY = absY;
				}
				else if (playerLevel[6] <= 1)
				{
					sendMessage("You need to be Admin to cast this spell!");
				}
			}
			if (spellID == 12929) // blood barrage (lvl 92 spell)
			{
				if (playerLevel[6] >= 92)
				{
					if ((playerHasItemAmount(560, 30) == false)
							|| (playerHasItemAmount(565, 30) == false)
							|| (playerHasItemAmount(566, 10) == false))
					{
						sendMessage("You do not have enough runes to cast this spell.");
						sendMessage("You need 30 " + getItemName(560) + ", 30 "
								+ getItemName(565) + " and 10 "
								+ getItemName(566));
					}
					else if ((playerHasItemAmount(560, 30) == true)
							&& (playerHasItemAmount(565, 30) == true)
							&& (playerHasItemAmount(566, 10) == true))
					{
						startAnimation(1979);
						attackPlayersWithin(377, 39, 10);
						hitDiff = 6 + MethodCollection.random(24);
						NewHP += hitDiff / 2;
						if (NewHP > getLevelForXP(playerXP[3]))
						{
							NewHP = getLevelForXP(playerXP[3]);
						}
						updateRequired = true;
						castOnPlayer.inCombat();
						inCombat();
						PkingDelay = 15;
						castOnPlayer.sendMessage("Your life has been drained!");
						sendMessage("You drain the enemys life and add it to yours.");
						deleteItem(560, getItemSlot(560), 30);
						deleteItem(565, getItemSlot(565), 30);
						deleteItem(566, getItemSlot(566), 10);
						teleportToX = absX;
						teleportToY = absY;
					}
				}
				else if (playerLevel[6] <= 92)
				{
					sendMessage("You need a magic level of 92 to cast this spell.");
				}
			}
			if (spellID == 13023) // shadow barrage (lvl 88 spell)
			{
				if (playerLevel[6] >= 88)
				{
					if ((playerHasItemAmount(560, 10) == false)
							|| (playerHasItemAmount(565, 5) == false)
							|| (playerHasItemAmount(556, 10) == false))
					{
						sendMessage("You do not have enough runes to cast this spell.");
						sendMessage("You need 10 " + getItemName(560) + ", 5 "
								+ getItemName(565) + " and 10 "
								+ getItemName(556));
					}
					else if ((playerHasItemAmount(560, 10) == true)
							&& (playerHasItemAmount(565, 5) == true)
							&& (playerHasItemAmount(556, 10) == true))
					{
						startAnimation(1979);
						attackPlayersWithin(382, 39, 10);
						hitDiff = 9 + MethodCollection.random(21);
						heal = 10;
						playerLevel[3] += heal;
						updateRequired = true;
						hitUpdateRequired = true;
						PkingDelay = 25;
						deleteItem(560, getItemSlot(560), 10);
						deleteItem(565, getItemSlot(565), 5);
						deleteItem(556, getItemSlot(556), 10);
						deleteItem(566, getItemSlot(566), 10);
						teleportToX = absX;
						teleportToY = absY;
					}
				}
				else if (playerLevel[6] <= 88)
				{
					sendMessage("You need a magic level of 88 to cast this spell.");
				}
			}
			if (spellID == 12871) // ice blitz (lvl 82 spell)
			{
				if (playerLevel[6] >= 89)
				{
					if ((playerHasItemAmount(560, 20) == false)
							|| (playerHasItemAmount(565, 10) == false)
							|| (playerHasItemAmount(555, 30) == false))
					{
						sendMessage("You do not have enough runes to cast this spell.");
						sendMessage("You need 20 " + getItemName(560) + ", 10 "
								+ getItemName(565) + " and 30 "
								+ getItemName(555));
					}
					else if ((playerHasItemAmount(560, 20) == true)
							&& (playerHasItemAmount(565, 10) == true)
							&& (playerHasItemAmount(555, 30) == true))
					{
						startAnimation(1978);
						hitDiff = 6 + MethodCollection.random(20);
						castOnPlayer.entangle();
						castOnPlayer.inCombat();
						inCombat();
						stillgfx(368, absY, absX);
						stillgfx(367, castOnPlayer.absY, castOnPlayer.absX);
						castOnPlayer.sendMessage("You are frozen!");
						deleteItem(560, getItemSlot(560), 20);
						deleteItem(565, getItemSlot(565), 10);
						deleteItem(555, getItemSlot(555), 30);
						teleportToX = absX;
						teleportToY = absY;
					}
				}
				else if (playerLevel[6] <= 89)
				{
					sendMessage("You need a magic level of 90 to cast this spell.");
				}
			}
			if (spellID == 12911) // blood blitz (lvl 80 spell)
			{
				if (playerLevel[6] >= 81)
				{
					if ((playerHasItemAmount(560, 20) == false)
							|| (playerHasItemAmount(565, 30) == false))
					{
						sendMessage("You need 20 " + getItemName(560)
								+ " and 30 " + getItemName(565));
					}
					else if ((playerHasItemAmount(560, 20) == true)
							&& (playerHasItemAmount(565, 30) == true))
					{
						startAnimation(1978);
						hitDiff = 6 + MethodCollection.random(24);
						NewHP += hitDiff;
						if (NewHP > getLevelForXP(playerXP[3]))
						{
							NewHP = getLevelForXP(playerXP[3]);
						}
						updateRequired = true;
						castOnPlayer.inCombat();
						inCombat();
						sendMessage("You drain the enemys life and add it to yours");
						castOnPlayer.sendMessage("Your life is drained!");
						PkingDelay = 25;
						deleteItem(560, getItemSlot(560), 20);
						deleteItem(565, getItemSlot(565), 30);
						teleportToX = absX;
						teleportToY = absY;
					}
				}
				else if (playerLevel[6] <= 81)
				{
					sendMessage("You need a magic level of 82 to cast this spell.");
				}
			}
			if (spellID == 1592) // entangle (lvl 79 spell)
			{
				if (playerLevel[6] >= 78)
				{
					if ((playerHasItemAmount(561, 8) == false)
							|| (playerHasItemAmount(557, 10) == false)
							|| (playerHasItemAmount(555, 10) == false))
					{
						sendMessage("You do not have enough runes to cast this spell.");
						sendMessage("You need 8 " + getItemName(561) + ", 10 "
								+ getItemName(557) + " and 10 "
								+ getItemName(555));
					}
					else if ((playerHasItemAmount(561, 8) == true)
							&& (playerHasItemAmount(557, 10) == true)
							&& (playerHasItemAmount(555, 10) == true))
					{
						stillgfx(179, castOnPlayer.absY, castOnPlayer.absX);
						castOnPlayer.entangle();
						castOnPlayer.inCombat();
						inCombat();
						startAnimation(711);
						PkingDelay = 40;
						deleteItem(561, getItemSlot(561), 8);
						deleteItem(557, getItemSlot(557), 10);
						deleteItem(555, getItemSlot(555), 10);
						teleportToX = absX;
						teleportToY = absY;
						castOnPlayer
								.sendMessage("A bind spell has been cast upon you!");
					}
				}
				else if (playerLevel[6] <= 78)
				{
					sendMessage("You need a magic level of 79 to cast this spell.");
				}
			}
			else if (spellID == 12445)
			{ // teleblock (lvl 85 spell)
				if (playerLevel[6] >= 85)
				{
					startAnimation(1819);
					castOnPlayer.Teleblock();
					castOnPlayer.inCombat();
					inCombat();
					PkingDelay = 10;
					teleportToX = absX;
					teleportToY = absY;
				}
				else if (playerLevel[6] <= 85)
				{
					sendMessage("You need a magic level of 85 to cast this spell.");
				}
			}
		}
		if ((EnemyHP - hitDiff) < 0)
		{
			hitDiff = EnemyHP;
		}
		mageXP = (hitDiff * 700);
		addSkillXP(mageXP, 6);
		addSkillXP(hitDiff, 3);
		castOnPlayer.hitDiff = hitDiff;
		castOnPlayer.KillerId = playerId;
		castOnPlayer.updateRequired = true;
		castOnPlayer.hitUpdateRequired = true;
	}

	public boolean AttackNPC()
	{
		int EnemyX = NPCHandler.npcs[attacknpc].absX;
		int EnemyY = NPCHandler.npcs[attacknpc].absY;
		int EnemyHP = NPCHandler.npcs[attacknpc].HP;
		int hitDiff = 0;
		CalculateMaxHit();
		hitDiff = MethodCollection.random(playerMaxHit);
		int casterX = absX;
		int casterY = absY;
		int offsetX = (casterX - EnemyX) * -1;
		int offsetY = (casterY - EnemyY) * -1;
		faceNPC(attacknpc);
		if (NPCHandler.npcs[attacknpc].followPlayer < 1
				|| NPCHandler.npcs[attacknpc].followPlayer == playerId
				|| inwildy2 == true)
		{
			if (playerEquipment[playerWeapon] == (1333)) // rune scimi here
			{
				PkingDelay = 8;
				actionTimer = 8;
			}
			if (playerEquipment[playerWeapon] == (15156))
			{
				if (actionTimer == 0) // dark bow
				{
					PkingDelay = 8;
					actionTimer = 8;
					hitDiff = 4 + MethodCollection.random(20);
				}
			}
			if (playerEquipment[playerWeapon] == (6724))
			{
				if (actionTimer == 0) // seercull
				{
					PkingDelay = 8;
					actionTimer = 8;
					hitDiff = 4 + MethodCollection.random(20);
				}
			}
			if (playerEquipment[playerWeapon] == (4151)) // whip
			{
				PkingDelay = 1;
			}
			if (playerEquipment[playerWeapon] == (8447)) // cat toy
			{
				PkingDelay = 1;
			}
			if (playerEquipment[playerWeapon] == (4718)) // dharoks axe
			{
				PkingDelay = 16;
			}
			if (playerEquipment[playerWeapon] == (746)) // dharoks axe
			{
				PkingDelay = 1;
			}
			if (playerEquipment[playerWeapon] == (4587)) // dragon scimmy here
			{
				PkingDelay = 1;
			}
			if (playerEquipment[playerWeapon] == (35)) // Excalibur
			{
				PkingDelay = 1;
			}
			if (playerEquipment[playerWeapon] == (15337)) // Lava Blade
			{
				PkingDelay = 1;
			}
			if (playerEquipment[playerWeapon] == (1377)) // dragon battleaxe
			{
				PkingDelay = 10;
			}
			if (playerEquipment[playerWeapon] == (1373)) // rune battleaxe
			{
				PkingDelay = 10;
			}
			if (playerEquipment[playerWeapon] == (1434))
			{
				PkingDelay = 10;
			}
			if (playerEquipment[playerWeapon] == (7158))
			{
				PkingDelay = 5;
			}
			if (playerEquipment[playerWeapon] == (1319))
			{
				PkingDelay = 80;
			}
			if (playerEquipment[playerWeapon] == (6528))
			{
				PkingDelay = 5;
			}
			boolean UseBow = false;
			if (playerEquipment[playerWeapon] == 4212)
			{
				PkingDelay = 10;
				UseBow = true;
				createProjectile(absY, absX, offsetX, offsetY, 50, 80, 249, 43,
						31, 0 - AttackingOn);
			}
			if (playerEquipment[playerWeapon] == 4212)
			{
				PkingDelay = 10;
				UseBow = true;
				createProjectile(absY, absX, offsetX, offsetY, 50, 80, 249, 43,
						31, 0 - AttackingOn);
			}
			if (playerEquipment[playerWeapon] == 861)
			{
				PkingDelay = 5;
				UseBow = true;
			}
			if (playerEquipment[playerWeapon] == 859)
			{
				PkingDelay = 10;
				UseBow = true;
			}
			if (playerEquipment[playerWeapon] == 6724)
			{
				PkingDelay = 10;
				UseBow = true;
			}
			if (playerEquipment[playerWeapon] == 839
					|| playerEquipment[playerWeapon] == 841
					|| playerEquipment[playerWeapon] == 843
					|| playerEquipment[playerWeapon] == 845
					|| playerEquipment[playerWeapon] == 847
					|| playerEquipment[playerWeapon] == 849
					|| playerEquipment[playerWeapon] == 851
					|| playerEquipment[playerWeapon] == 853
					|| playerEquipment[playerWeapon] == 855
					|| playerEquipment[playerWeapon] == 857)
			{
				PkingDelay = 5;
				UseBow = true;
			}
			if (UseBow)
			{
				inCombat();
				teleportToX = absX;
				teleportToY = absY;
				CheckArrows();
				CalculateRange();
				hitDiff = MethodCollection.random(15);
			}
			else
			{
				PkingDelay = 6;
			}
			if (GoodDistance(EnemyX, EnemyY, absX, absY, 1) == true
					|| playerEquipment[playerWeapon] == 859
					|| playerEquipment[playerWeapon] == 4212
					|| playerEquipment[playerWeapon] == 839
					|| playerEquipment[playerWeapon] == 4212
					|| playerEquipment[playerWeapon] == 841
					|| playerEquipment[playerWeapon] == 843
					|| playerEquipment[playerWeapon] == 845
					|| playerEquipment[playerWeapon] == 847
					|| playerEquipment[playerWeapon] == 849
					|| playerEquipment[playerWeapon] == 851
					|| playerEquipment[playerWeapon] == 853
					|| playerEquipment[playerWeapon] == 855
					|| playerEquipment[playerWeapon] == 857
					|| playerEquipment[playerWeapon] == 6724)
			{
				if (LoopAttDelay <= 1)
				{
					if (NPCHandler.npcs[attacknpc].IsDead == true)
					{
					}
					else if (!UseBow)
					{
						setAnimation(GetWepAnim());
						if ((EnemyHP - hitDiff) < 0)
						{
							hitDiff = EnemyHP;
						}
						if (usingSpecial == true)
						{
							if (playerEquipment[playerWeapon] == 4151
									&& specialAmount >= 50)
							{
								animation(341, EnemyY, EnemyX);
								calculateSpecial();
								hitDiff = specialDamage;
								usingSpecial = false;
								specialDamage = 0;
								specialDamage2 = 0;
							}
							if (playerEquipment[playerWeapon] == 10101
									&& specialAmount >= 50)
							{
								animation(341, EnemyY, EnemyX);
								calculateSpecial();
								hitDiff = specialDamage;
								usingSpecial = false;
								specialDamage = 0;
								specialDamage2 = 0;
							}
							if (playerEquipment[playerWeapon] == 10102
									&& specialAmount >= 50)
							{
								animation(341, EnemyY, EnemyX);
								calculateSpecial();
								hitDiff = specialDamage;
								usingSpecial = false;
								specialDamage = 0;
								specialDamage2 = 0;
							}
							if (playerEquipment[playerWeapon] == 10103
									&& specialAmount >= 50)
							{
								animation(341, EnemyY, EnemyX);
								calculateSpecial();
								hitDiff = specialDamage;
								usingSpecial = false;
								specialDamage = 0;
								specialDamage2 = 0;
							}
							if (playerEquipment[playerWeapon] == 10104
									&& specialAmount >= 50)
							{
								animation(341, EnemyY, EnemyX);
								calculateSpecial();
								hitDiff = specialDamage;
								usingSpecial = false;
								specialDamage = 0;
								specialDamage2 = 0;
							}
							if (playerEquipment[playerWeapon] == 10105
									&& specialAmount >= 50)
							{
								animation(341, EnemyY, EnemyX);
								calculateSpecial();
								hitDiff = specialDamage;
								usingSpecial = false;
								specialDamage = 0;
								specialDamage2 = 0;
							}
							if (playerEquipment[playerWeapon] == 10106
									&& specialAmount >= 50)
							{
								animation(341, EnemyY, EnemyX);
								calculateSpecial();
								hitDiff = specialDamage;
								usingSpecial = false;
								specialDamage = 0;
								specialDamage2 = 0;
							}
							if (playerEquipment[playerWeapon] == 746
									&& specialAmount >= 100)
							{
								animation(433, EnemyY, EnemyX);
								calculateSpecial();
								hitDiff = specialDamage;
								usingSpecial = false;
								specialDamage = 0;
								specialDamage2 = 0;
							}
							if (playerEquipment[playerWeapon] == 15156
									&& specialAmount >= 50)
							{
								calculateSpecial();
								darkbow();
								usingSpecial = false;
								specialDamage = 0;
								specialDamage2 = 0;
							}
							if (playerEquipment[playerWeapon] == 6724
									&& specialAmount >= 50)
							{
								calculateSpecial();
								seercull();
								usingSpecial = false;
								specialDamage = 0;
								specialDamage2 = 0;
							}
							if (playerEquipment[playerWeapon] == 15156
									&& specialAmount >= 50)
							{
								ProjectileSpec(380, absY, absX, offsetY,
										offsetX, attacknpc, EnemyY, EnemyX);
								calculateSpecial();
								DDZ();
								usingSpecial = false;
								specialDamage = 0;
								specialDamage2 = 0;
							}
							if (playerEquipment[playerWeapon] == 6724
									&& specialAmount >= 50)
							{
								ProjectileSpec(380, absY, absX, offsetY,
										offsetX, attacknpc, EnemyY, EnemyX);
								calculateSpecial();
								DDZ();
								usingSpecial = false;
								specialDamage = 0;
								specialDamage2 = 0;
							}
							if (playerEquipment[playerWeapon] == 1305
									&& specialAmount >= 50)
							{
								ProjectileSpec(248, absY, absX, offsetY,
										offsetX, attacknpc, EnemyY, EnemyX);
								calculateSpecial();
								hitDiff = specialDamage;
								usingSpecial = false;
								specialDamage = 0;
								specialDamage2 = 0;
							}
							if (playerEquipment[playerWeapon] == 15334
									&& specialAmount >= 50)
							{
								ProjectileSpec(436, absY, absX, offsetY,
										offsetX, attacknpc, EnemyY, EnemyX);
								calculateSpecial();
								hitDiff = specialDamage;
								usingSpecial = false;
								specialDamage = 0;
								specialDamage2 = 0;
							}
							if (playerEquipment[playerWeapon] == 15336
									&& specialAmount >= 50)
							{
								ProjectileSpec(436, absY, absX, offsetY,
										offsetX, attacknpc, EnemyY, EnemyX);
								calculateSpecial();
								hitDiff = specialDamage;
								usingSpecial = false;
								specialDamage = 0;
								specialDamage2 = 0;
							}
							if (playerEquipment[playerWeapon] == 14915
									&& specialAmount >= 50)
							{
								ProjectileSpec(282, absY, absX, offsetY,
										offsetX, attacknpc, EnemyY, EnemyX);
								calculateSpecial();
								hitDiff = specialDamage;
								usingSpecial = false;
								specialDamage = 0;
								specialDamage2 = 0;
							}
							if (playerEquipment[playerWeapon] == 3204
									&& specialAmount >= 100)
							{
								ProjectileSpec(282, absY, absX, offsetY,
										offsetX, attacknpc, EnemyY, EnemyX);
								calculateSpecial();
								hitDiff = specialDamage;
								usingSpecial = false;
								specialDamage = 0;
								specialDamage2 = 0;
							}
							if (playerEquipment[playerWeapon] == 4587
									&& specialAmount >= 75)
							{
								ProjectileSpec(347, absY, absX, offsetY,
										offsetX, attacknpc, EnemyY, EnemyX);
								calculateSpecial();
								hitDiff = specialDamage;
								usingSpecial = false;
								specialDamage = 0;
								specialDamage2 = 0;
							}
							if (playerEquipment[playerWeapon] == 35
									&& specialAmount >= 75)
							{
								ProjectileSpec(347, absY, absX, offsetY,
										offsetX, attacknpc, EnemyY, EnemyX);
								calculateSpecial();
								hitDiff = specialDamage;
								usingSpecial = false;
								specialDamage = 0;
								specialDamage2 = 0;
							}
							if (playerEquipment[playerWeapon] == 15337
									&& specialAmount >= 75)
							{
								ProjectileSpec(347, absY, absX, offsetY,
										offsetX, attacknpc, EnemyY, EnemyX);
								calculateSpecial();
								hitDiff = specialDamage;
								usingSpecial = false;
								specialDamage = 0;
								specialDamage2 = 0;
							}
							if (playerEquipment[playerWeapon] == 1434
									&& specialAmount >= 40)
							{
								ProjectileSpec(251, absY, absX, offsetY,
										offsetX, attacknpc, EnemyY, EnemyX);
								calculateSpecial();
								hitDiff = specialDamage;
								usingSpecial = false;
								specialDamage = 0;
								specialDamage2 = 0;
							}
							if (playerEquipment[playerWeapon] == 7158
									&& specialAmount >= 40)
							{
								ProjectileSpec(479, absY, absX, offsetY,
										offsetX, attacknpc, EnemyY, EnemyX);
								calculateSpecial();
								hitDiff = specialDamage;
								usingSpecial = false;
								specialDamage = 0;
								specialDamage2 = 0;
							}
							if (playerEquipment[playerWeapon] == 5698
									&& specialAmount >= 25)
							{
								ProjectileSpec(252, absY, absX, offsetY,
										offsetX, attacknpc, EnemyY, EnemyX);
								ProjectileSpec(252, absY, absX, offsetY,
										offsetX, attacknpc, EnemyY, EnemyX);
								calculateSpecial();
								DDSSpecial();
								NPCHandler.getSingleton().PoisonNPC(attacknpc);
								usingSpecial = false;
								specialDamage = 0;
								specialDamage2 = 0;
							}
							if (playerEquipment[playerWeapon] == 8002
									&& specialAmount >= 50)
							{
								ProjectileSpec(252, absY, absX, offsetY,
										offsetX, attacknpc, EnemyY, EnemyX);
								ProjectileSpec(252, absY, absX, offsetY,
										offsetX, attacknpc, EnemyY, EnemyX);
								calculateSpecial();
								ClawsSpecial();
								NPCHandler.getSingleton().PoisonNPC(attacknpc);
								usingSpecial = false;
								specialDamage = 0;
								specialDamage2 = 0;
							}
							if (playerEquipment[playerWeapon] == 13643
									&& specialAmount >= 50)
							{
								ProjectileSpec(377, absY, absX, offsetY,
										offsetX, attacknpc, EnemyY, EnemyX);
								ProjectileSpec(377, absY, absX, offsetY,
										offsetX, attacknpc, EnemyY, EnemyX);
								calculateSpecial();
								CamsSpecial();
								NPCHandler.getSingleton().PoisonNPC(attacknpc);
								usingSpecial = false;
								specialDamage = 0;
								specialDamage2 = 0;
							}
							if (playerEquipment[playerWeapon] == 11593
									&& specialAmount >= 100)
							{
								ProjectileSpec(252, absY, absX, offsetY,
										offsetX, attacknpc, EnemyY, EnemyX);
								ProjectileSpec(252, absY, absX, offsetY,
										offsetX, attacknpc, EnemyY, EnemyX);
								calculateSpecial();
								DLLSpecial();
								NPCHandler.getSingleton().PoisonNPC(attacknpc);
								usingSpecial = false;
								specialDamage = 0;
								specialDamage2 = 0;
							}
							if (playerEquipment[playerWeapon] == 11593
									&& specialAmount >= 100)
							{
								ProjectileSpec(252, absY, absX, offsetY,
										offsetX, attacknpc, EnemyY, EnemyX);
								ProjectileSpec(252, absY, absX, offsetY,
										offsetX, attacknpc, EnemyY, EnemyX);
								calculateSpecial();
								DLSpecial();
								NPCHandler.getSingleton().PoisonNPC(attacknpc);
								usingSpecial = false;
								specialDamage = 0;
								specialDamage2 = 0;
							}
							if (playerEquipment[playerWeapon] == 14889
									&& specialAmount >= 75)
							{
								ProjectileSpec(252, absY, absX, offsetY,
										offsetX, attacknpc, EnemyY, EnemyX);
								ProjectileSpec(252, absY, absX, offsetY,
										offsetX, attacknpc, EnemyY, EnemyX);
								calculateSpecial();
								SSSpecial();
								NPCHandler.getSingleton().PoisonNPC(attacknpc);
								usingSpecial = false;
								specialDamage = 0;
								specialDamage2 = 0;
							}
							if (playerEquipment[playerWeapon] == 15351
									&& specialAmount >= 75)
							{
								ProjectileSpec(252, absY, absX, offsetY,
										offsetX, attacknpc, EnemyY, EnemyX);
								ProjectileSpec(252, absY, absX, offsetY,
										offsetX, attacknpc, EnemyY, EnemyX);
								calculateSpecial();
								DDSSpecial();
								NPCHandler.getSingleton().PoisonNPC(attacknpc);
								usingSpecial = false;
								specialDamage = 0;
								specialDamage2 = 0;
							}
							if (playerEquipment[playerWeapon] == 4153
									&& specialAmount >= 50)
							{
								ProjectileSpec(340, absY, absX, offsetY,
										offsetX, AttackingOn, EnemyY, EnemyX);
								calculateSpecial();
								maulSpec();
								usingSpecial = false;
								specialDamage = 0;
								specialDamage2 = 0;
							}
							if (playerEquipment[playerWeapon] == 6739
									&& specialAmount >= 100)
							{
								calculateSpecial();
								hitDiff = specialDamage;
								usingSpecial = false;
								specialDamage = 0;
								specialDamage2 = 0;
							}
							if (playerEquipment[playerWeapon] == 282
									&& specialAmount >= 50)
							{
								ProjectileSpec(340, absY, absX, offsetY,
										offsetX, AttackingOn, EnemyY, EnemyX);
								calculateSpecial();
								hally();
								usingSpecial = false;
								specialDamage = 0;
								specialDamage2 = 0;
							}
							if (playerEquipment[playerWeapon] == 861
									&& specialAmount >= 100)
							{
								calculateSpecial();
								DDZ();
								usingSpecial = false;
								specialDamage = 0;
								specialDamage2 = 0;
							}
							if (playerEquipment[playerWeapon] == 4755
									&& specialAmount >= 100
									|| playerEquipment[playerWeapon] == 4734
									&& specialAmount >= 100
									|| playerEquipment[playerWeapon] == 4718
									&& specialAmount >= 100
									|| playerEquipment[playerWeapon] == 4726
									&& specialAmount >= 100
									|| playerEquipment[playerWeapon] == 4747
									&& specialAmount >= 100
									|| playerEquipment[playerWeapon] == 47
									&& specialAmount >= 100)
							{
								ProjectileSpec(432, absY, absX, offsetY,
										offsetX, attacknpc, EnemyY, EnemyX);
								calculateSpecial();
								hitDiff = specialDamage;
								usingSpecial = false;
								specialDamage = 0;
								specialDamage2 = 0;
							}
							usingSpecial = false;
							specialDamage = 0;
							specialDamage2 = 0;
							specialAttacks();
							specialAttacks2();
							specialAttacks3();
							specialAttacks4();
							specialAttacks5();
							specialAttacks6();
						}
						LoopAttDelay = PkingDelay;
						NPCHandler.npcs[attacknpc].hitDiff = hitDiff;
						NPCHandler.npcs[attacknpc].Killing[playerId] += hitDiff;
						NPCHandler.npcs[attacknpc].updateRequired = true;
						NPCHandler.npcs[attacknpc].hitUpdateRequired = true;
						double TotalExp = 0;
						inCombat();
						if (FightType == 1) // Accurate
						{
							TotalExp = (double) (2900 * hitDiff);
							TotalExp = (double) (TotalExp * CombatExpRate);
							addSkillXP((int) (TotalExp), ATTACK);
						}
						else if (FightType == 2) // Agressive
						{
							TotalExp = (double) (2900 * hitDiff);
							TotalExp = (double) (TotalExp * CombatExpRate);
							addSkillXP((int) (TotalExp), STRENGTH);
						}
						else if (FightType == 4) // Defensive
						{
							TotalExp = (double) (2900 * hitDiff);
							TotalExp = (double) (TotalExp * CombatExpRate);
							addSkillXP((int) (TotalExp), DEFENCE);
						}
						else if (FightType == 3) // Controlled
						{
							TotalExp = (double) (2900 * hitDiff);
							TotalExp = (double) (TotalExp * CombatExpRate);
							addSkillXP((int) (TotalExp), STRENGTH);
						}
						TotalExp = (double) (3200 * hitDiff);
						TotalExp = (double) (TotalExp * CombatExpRate);
						addSkillXP((int) (TotalExp), HITPOINTS);
						actionTimer = 7;
						NPCHandler.npcs[attacknpc].animNumber = NPCHandler
								.getNPCBlockAnim(NPCHandler.npcs[attacknpc].id);
					}
					else if (UseBow)
					{
						if (!HasArrows)
						{
							sendMessage("There's no arrows left in your quiver");
						}
						else if (HasArrows)
						{
							LoopAttDelay = PkingDelay;
							DeleteArrow();
							if (playerEquipment[playerWeapon] != 4212
									&& playerEquipmentN[playerArrows] != 0)
							{
								ItemHandler.addItem(
										playerEquipment[playerArrows], EnemyX,
										EnemyY, 1, playerId, false);
							}
							setAnimation(426);
							NPCHandler.npcs[attacknpc].hitDiff = hitDiff;
							NPCHandler.npcs[attacknpc].Killing[playerId] += hitDiff;
							NPCHandler.npcs[attacknpc].updateRequired = true;
							NPCHandler.npcs[attacknpc].hitUpdateRequired = true;
							double TotalExp = 0;
							TotalExp = (double) (3200 * hitDiff);
							TotalExp = (double) (TotalExp * CombatExpRate);
							addSkillXP((int) (TotalExp), RANGED);
							TotalExp = (double) (3000 * hitDiff);
							TotalExp = (double) (TotalExp * CombatExpRate);
							addSkillXP((int) (TotalExp), HITPOINTS);
							inCombat();
							teleportToX = absX;
							teleportToY = absY;
							actionTimer = 4;
							NPCHandler.npcs[attacknpc].animNumber = NPCHandler
									.getNPCBlockAnim(NPCHandler.npcs[attacknpc].id);
						}
					}
					return true;
				}
			}
		}
		else
		{
			sendMessage("You can't attack a player's summoned npc!");
		}
		return false;
	}

	public boolean ResetAttackNPC()
	{
		if (attacknpc > -1 && attacknpc < NPCHandler.maxNPCs)
		{
			NPCHandler.npcs[attacknpc].IsUnderAttack = false;
		}
		IsAttackingNPC = false;
		attacknpc = -1;
		resetAnimation();
		pEmote = playerSE;
		faceNPC = 65535;
		faceNPCupdate = true;
		return true;
	}

	public void ManipulateDirection()
	{
		// playerMD = misc.direction(absX, absY, skillX, skillY);
		if (playerMD != -1)
		{
			// playerMD >>= 1;
			updateRequired = true;
			dirUpdateRequired = true;
		}
	}

	public int GetNPCID(int coordX, int coordY)
	{
		for (int i = 0; i < NPCHandler.maxNPCs; i++)
		{
			if (NPCHandler.npcs[i] != null)
			{
				if (NPCHandler.npcs[i].absX == coordX
						&& NPCHandler.npcs[i].absY == coordY)
				{
					return NPCHandler.npcs[i].id;
				}
			}
		}
		return 1;
	}

	public String getNpcName(int id)
	{
		for (int i = 0; i < NPCHandler.maxListedNPCs; i++)
		{
			if (NPCHandler.NpcList[i] != null)
			{
				if (NPCHandler.NpcList[i].npcId == id)
				{
					return NPCHandler.NpcList[i].npcName;
				}
			}
		}
		return "Npc(id=" + id + ")";
	}

	public String getItemName(int id)
	{
		for (int i = 0; i < ItemHandler.MaxListedItems; i++)
		{
			if (ItemHandler.ItemList[i] != null)
			{
				if (ItemHandler.ItemList[i].itemId == id)
				{
					return ItemHandler.ItemList[i].itemName;
				}
				if (id == -1)
				{
					return "Unarmed";
				}
			}
		}
		return "Item(id=" + id + ")";
	}

	// public String getItemName(int ItemID)
	// {
	// for (int i = 0; i < ItemHandler.MaxListedItems; i++)
	// {
	// if (Server.itemHandler.ItemList[i] != null)
	// {
	// if (Server.itemHandler.ItemList[i].itemId == ItemID)
	// {
	// return Server.itemHandler.ItemList[i].itemName;
	// }
	// if (ItemID == -1)
	// {
	// return "Unarmed";
	// }
	// }
	// }
	// return "!! NOT EXISTING ITEM !!! - ID:" + ItemID;
	// }

	public double GetItemShopValue(int ItemID, int Type, int fromSlot)
	{
		double ShopValue = 1;
		double TotPrice = 0;
		for (int i = 0; i < ItemHandler.MaxListedItems; i++)
		{
			if (ItemHandler.ItemList[i] != null)
			{
				if (ItemHandler.ItemList[i].itemId == ItemID)
				{
					ShopValue = ItemHandler.ItemList[i].ShopValue;
				}
			}
		}
		TotPrice = ShopValue;
		return TotPrice;
	}

	public int GetUnnotedItem(int ItemID)
	{
		int NewID = 0;
		String NotedName = "";

		for (int i = 0; i < ItemHandler.MaxListedItems; i++)
		{
			if (ItemHandler.ItemList[i] != null)
			{
				if (ItemHandler.ItemList[i].itemId == ItemID)
				{
					NotedName = ItemHandler.ItemList[i].itemName;
				}
			}
		}
		for (int i = 0; i < ItemHandler.MaxListedItems; i++)
		{
			if (ItemHandler.ItemList[i] != null)
			{
				if (ItemHandler.ItemList[i].itemName == NotedName)
				{
					if (ItemHandler.ItemList[i].itemDescription
							.startsWith("Swap this note at any bank for a") == false)
					{
						NewID = ItemHandler.ItemList[i].itemId;
						break;
					}
				}
			}
		}
		return NewID;
	}

	public void WriteEnergy()
	{
		if (playerRights < 2 && playerEnergy > 100)
		{
			playerEnergy = 100;
		}
		sendFrame126("@red@100%", 149);
	}

	private void appendPos()
	{
		setText("X: " + absX + " Y: " + absY, 184);
		setText("Your coordinates", 183);
	}

	private void ResetBonus()
	{
		for (int i = 0; i < playerBonus.length; i++)
		{
			playerBonus[i] = 0;
		}
	}

	private void GetBonus()
	{
		for (int i = 0; i < playerEquipment.length; i++)
		{
			if (playerEquipment[i] > -1)
			{
				for (int j = 0; j < 9999; j++)
				{
					if (ItemHandler.ItemList[j] != null)
					{
						if (ItemHandler.ItemList[j].itemId == playerEquipment[i])
						{
							for (int k = 0; k < playerBonus.length; k++)
							{
								playerBonus[k] += ItemHandler.ItemList[j].Bonuses[k];
							}
							break;
						}
					}
				}
			}
		}
	}

	private void WriteBonus()
	{
		int offset = 0;
		String send = "";
		for (int i = 0; i < playerBonus.length; i++)
		{
			if (playerBonus[i] >= 0)
			{
				send = BonusName[i] + ": +" + playerBonus[i];
			}
			else
			{
				send = BonusName[i] + ": -" + Math.abs(playerBonus[i]);
			}
			if (i == 10)
			{
				offset = 1;
			}
			sendFrame126(send, (1675 + i + offset));
		}
		CalculateMaxHit();
	}

	private void CalculateMaxHit()
	{
		double MaxHit = 0;
		int StrBonus = playerBonus[10]; // Strength Bonus
		int Strength = playerLevel[STRENGTH]; // Strength
		if (FightType == 1 || FightType == 4)
		{ // Accurate & Defensive
			MaxHit += ((double) ((double) StrBonus + Strength) / ((double) 6.8275862068965517241379310344828));
		}
		else if (FightType == 2)
		{ // Aggresive
			MaxHit += ((double) ((double) StrBonus + Strength) / ((double) 6.6551724137931034482758620689655));
		}
		else if (FightType == 3)
		{ // Controlled
			MaxHit += ((double) ((double) StrBonus + Strength) / ((double) 6.7586206896551724137931034482759));
		}
		if (StrPotion == 1)
		{ // Strength Potion
			MaxHit += (double) (Strength * 0.0014);
		}
		else if (StrPotion == 2)
		{ // Super Strength Potion
			MaxHit += (double) (Strength * 0.0205);
		}
		if (StrPrayer == 1)
		{ // Burst Of Strength
			MaxHit += (double) (Strength * 0.005);
		}
		else if (StrPrayer == 2)
		{ // Super Human Strength
			MaxHit += (double) (Strength * 0.01);
		}
		else if (StrPrayer == 3)
		{ // Ultimate Strength
			MaxHit += (double) (Strength * 0.015);
		}
		if (FullDharokEquipped())
		{
			MaxHit += (getLevelForXP(playerXP[HITPOINTS]) - playerLevel[HITPOINTS]) / 2;
		}
		if (FullVeracEquipped())
		{
			MaxHit += (getLevelForXP(playerXP[PRAYER]) - playerLevel[PRAYER]) / 2;
		}
		if (FullVoidEquipped())
		{
			MaxHit += (getLevelForXP(playerXP[HITPOINTS]) - playerLevel[HITPOINTS]) / 2;
		}
		playerMaxHit = (int) Math.floor(MaxHit);
	}

	public boolean FullDharokEquipped()
	{
		if (playerEquipment[playerHat] == 4716
				&& playerEquipment[playerChest] == 4720
				&& playerEquipment[playerLegs] == 4722
				&& playerEquipment[playerWeapon] == 4718)
		{
			return true;
		}
		return false;
	}

	public boolean FullVeracEquipped()
	{
		if (playerEquipment[playerHat] == 4753
				&& playerEquipment[playerChest] == 4757
				&& playerEquipment[playerLegs] == 4759
				&& playerEquipment[playerWeapon] == 4755)
		{
			return true;
		}
		return false;
	}

	public boolean FullVoidEquipped()
	{
		if (playerEquipment[playerHat] == 15311
				&& playerEquipment[playerChest] == 13591
				&& playerEquipment[playerLegs] == 13592
				&& playerEquipment[playerWeapon] == 6724)
		{
			return true;
		}
		return false;
	}

	public boolean FullGuthanEquipped()
	{
		if (playerEquipment[playerHat] == 4724
				&& playerEquipment[playerChest] == 4728
				&& playerEquipment[playerLegs] == 4730
				&& playerEquipment[playerWeapon] == 4726)
		{
			return true;
		}
		return false;
	}

	public void CalculateRange()
	{
		double MaxHit = 0;
		int RangeBonus = playerBonus[5]; // Range Bonus
		int Range = playerLevel[4]; // Range
		{
			MaxHit += (double) (1.05 + (double) ((double) (RangeBonus * Range) * 0.00175));
		}
		MaxHit += (double) (Range * 0.1);
		playerMaxHit = (int) Math.floor(MaxHit);
	}

	public boolean GoodDistance(int objectX, int objectY, int playerX,
			int playerY, int distance)
	{
		for (int i = 0; i <= distance; i++)
		{
			for (int j = 0; j <= distance; j++)
			{
				if ((objectX + i) == playerX
						&& ((objectY + j) == playerY
								|| (objectY - j) == playerY || objectY == playerY))
				{
					return true;
				}
				else if ((objectX - i) == playerX
						&& ((objectY + j) == playerY
								|| (objectY - j) == playerY || objectY == playerY))
				{
					return true;
				}
				else if (objectX == playerX
						&& ((objectY + j) == playerY
								|| (objectY - j) == playerY || objectY == playerY))
				{
					return true;
				}
			}
		}
		return false;
	}

	public boolean GoodDistance2(int objectX, int objectY, int playerX,
			int playerY, int distance)
	{
		for (int i = 0; i <= distance; i++)
		{
			for (int j = 0; j <= distance; j++)
			{
				if (objectX == playerX
						&& ((objectY + j) == playerY
								|| (objectY - j) == playerY || objectY == playerY))
				{
					return true;
				}
				else if (objectY == playerY
						&& ((objectX + j) == playerX
								|| (objectX - j) == playerX || objectX == playerX))
				{
					return true;
				}
			}
		}
		return false;
	}

	public boolean CheckObjectSkill(int objectID)
	{
		boolean GoFalse = false;
		switch (objectID)
		{
		case 2491: // rune essence
			mining[0] = 1;
			mining[1] = 1;
			mining[2] = 5;
			mining[4] = 1436;
			break;
		case 2108:
		case 2109: // clay rock
			mining[0] = 1;
			mining[1] = 1;
			mining[2] = 5;
			mining[4] = 434;
			break;
		case 2090:
		case 2091: // copper rock
			mining[0] = 1;
			mining[1] = 1;
			if (MethodCollection.random(2) == 1)
			{
				mining[2] = 17;
			}
			else
			{
				mining[2] = 18;
			}
			mining[4] = 436;
			break;
		case 2094:
		case 2095: // tin rock
			mining[0] = 1;
			mining[1] = 1;
			if (MethodCollection.random(2) == 1)
			{
				mining[2] = 17;
			}
			else
			{
				mining[2] = 18;
			}
			mining[4] = 438;
			break;
		case 2110: // blurite rock
			mining[0] = 2;
			mining[1] = 10;
			if (MethodCollection.random(2) == 1)
			{
				mining[2] = 17;
			}
			else
			{
				mining[2] = 18;
			}
			mining[4] = 668;
			break;
		case 4028:
		case 4029:
		case 4030: // lime rock
			mining[0] = 1;
			mining[1] = 1;
			if (MethodCollection.random(2) == 1)
			{
				mining[2] = 26;
			}
			else
			{
				mining[2] = 27;
			}
			mining[4] = 3211;
			break;
		case 2092:
		case 2093: // iron rock
			mining[0] = 3;
			mining[1] = 15;
			mining[2] = 35;
			mining[4] = 440;
			break;
		case 2100:
		case 2101: // silver rock
			mining[0] = 4;
			mining[1] = 20;
			mining[2] = 40;
			mining[4] = 442;
			break;
		case 3403: // elemental rock
			mining[0] = 4;
			mining[1] = 20;
			mining[2] = 20;
			mining[4] = 2892;
			break;
		case 2096:
		case 2097: // coal rock
			mining[0] = 5;
			mining[1] = 30;
			mining[2] = 50;
			mining[4] = 453;
			break;
		case 2098:
		case 2099: // gold rock
			mining[0] = 6;
			mining[1] = 40;
			mining[2] = 65;
			break;
		case 2102:
		case 2103: // mithril rock
			mining[0] = 7;
			mining[1] = 55;
			mining[2] = 80;
			mining[4] = 447;
			break;
		case 2104:
		case 2105: // adamant rock
			mining[0] = 8;
			mining[1] = 70;
			mining[2] = 95;
			mining[4] = 449;
			break;
		case 2106:
		case 2107: // rune rock
			mining[0] = 9;
			mining[1] = 85;
			mining[2] = 125;
			mining[4] = 451;
			break;
		default:
			GoFalse = true;
			break;
		}
		if (GoFalse == true)
		{
			return false;
		}
		return true;
	}

	public boolean cooking()
	{
		if (playerLevel[COOKING] >= cooking[1])
		{
			if (actionTimer == 0 && cooking[0] == 1
					&& playerEquipment[playerWeapon] >= 0)
			{
				actionAmount++;
				actionTimer = 4;
				OriginalShield = playerEquipment[playerShield];
				OriginalWeapon = playerEquipment[playerWeapon];
				playerEquipment[playerShield] = -1;
				playerEquipment[playerWeapon] = -1;
				setAnimation(0x380);
				cooking[0] = 2;
			}
			if (actionTimer == 0 && cooking[0] == 2)
			{
				deleteItem(cooking[5], GetItemSlot(cooking[5]), 1);
				int Discount = 0;
				if (playerEquipment[playerHands] == 775)
				{ // Cooking hauntlets
					Discount = 10;
				}
				int StopBurnLevel = ((cooking[1] + 35) - Discount);
				if (StopBurnLevel > playerLevel[COOKING]
						&& MethodCollection.random2(StopBurnLevel) <= MethodCollection
								.random2(StopBurnLevel))
				{
					addItem(cooking[6], 1);
					sendMessage("You burned the " + getItemName(cooking[5])
							+ ".");
				}
				else
				{
					addItem(cooking[4], 1);
					addSkillXP((cooking[2] * cooking[3]), COOKING);
					sendMessage("You cooked the " + getItemName(cooking[5])
							+ ".");
				}
				playerEquipment[playerWeapon] = OriginalWeapon;
				playerEquipment[playerShield] = OriginalShield;
				OriginalWeapon = -1;
				OriginalShield = -1;
				resetAnimation();
				resetCO();
			}
		}
		else
		{
			sendMessage("You need " + cooking[1] + " " + statName[COOKING]
					+ " to cook this " + getItemName(cooking[5]) + ".");
			resetCO();
			return false;
		}
		return true;
	}

	public boolean resetCO()
	{
		cooking[0] = 0;
		cooking[1] = 0;
		cooking[2] = 0;
		cooking[4] = -1;
		IsUsingSkill = false;
		return true;
	}

	public void CheckDyeCape()
	{
		boolean GoOn = true;
		int CapeXP = 0;
		int NewCape = -1;
		switch (useitems[0])
		{
		case 1763: // red dye
			NewCape = 1007;
			CapeXP = 2;
		case 1767: // blue dye
			NewCape = 1021;
			CapeXP = 2;
		case 1765: // yellow dye
			NewCape = 1023;
			CapeXP = 2;
		case 1771: // green dye
			NewCape = 1027;
			CapeXP = 3;
		case 1773: // purple dye
			NewCape = 1029;
			CapeXP = 3;
		case 1769: // orange dye
			NewCape = 1031;
			CapeXP = 3;
		default:
			sendMessage("Nothing interesting is happening.");
			GoOn = false;
			break;
		}
		if (GoOn == true)
		{
			deleteItem(useitems[0], useitems[3], playerItemsN[useitems[3]]);
			deleteItem(useitems[1], useitems[2], playerItemsN[useitems[2]]);
			addItem(NewCape, 1);
			addSkillXP((CapeXP * crafting[3]), CRAFTING);
		}
	}

	public void CheckCuttingGem()
	{
		boolean GoOn = true;
		switch (useitems[1])
		{
		case 1617: // uncut diamond
			crafting[1] = 43;
			if (MethodCollection.random2(2) == 1)
			{
				crafting[2] = 107;
			}
			else
			{
				crafting[2] = 108;
			}
			break;
		case 1619: // uncut ruby
			crafting[1] = 34;
			crafting[2] = 85;
			break;
		case 1621: // uncut emerald
			crafting[1] = 27;
			if (MethodCollection.random2(2) == 1)
			{
				crafting[2] = 67;
			}
			else
			{
				crafting[2] = 68;
			}
			break;
		case 1623: // uncut sapphire
			crafting[1] = 20;
			crafting[2] = 50;
			break;
		case 1625: // uncut opal
			if (MethodCollection.random2(2) == 1)
			{
				crafting[4] = 1633;
			}
			crafting[1] = 1;
			crafting[2] = 15;
			break;
		case 1627: // uncut jada
			if (MethodCollection.random2(2) == 1)
			{
				crafting[4] = 1633;
			}
			crafting[1] = 13;
			crafting[2] = 20;
			break;
		case 1629: // uncut red topaz
			if (MethodCollection.random2(2) == 1)
			{
				crafting[4] = 1633;
			}
			crafting[1] = 16;
			crafting[2] = 25;
			break;
		case 1631: // uncut dragonstone
			crafting[1] = 55;
			if (MethodCollection.random2(2) == 1)
			{
				crafting[2] = 127;
			}
			else
			{
				crafting[2] = 128;
			}
			break;
		default:
			sendMessage("Nothing interesting is happening.");
			GoOn = false;
			break;
		}
		if (GoOn == true)
		{
			crafting[0] = 1;
			if (crafting[4] == -1)
			{
				crafting[4] = (useitems[1] - 16);
			}
		}
	}

	public boolean crafting()
	{
		if (playerLevel[CRAFTING] >= crafting[1]
				&& playerEquipment[playerWeapon] >= 0)
		{
			if (actionTimer == 0 && crafting[0] == 1)
			{
				actionAmount++;
				actionTimer = 4;
				OriginalShield = playerEquipment[playerShield];
				OriginalWeapon = playerEquipment[playerWeapon];
				playerEquipment[playerShield] = useitems[0];
				playerEquipment[playerWeapon] = useitems[1];
				setAnimation(0x8DD);
				crafting[0] = 2;
			}
			if (actionTimer == 0 && crafting[0] == 2)
			{
				deleteItem(useitems[1], useitems[2], playerItemsN[useitems[2]]);
				addItem(crafting[4], 1);
				if (crafting[4] == 1633)
				{
					sendMessage("You crushed the gem.");
				}
				else
				{
					addSkillXP((crafting[2] * crafting[3]), CRAFTING);
					sendMessage("You successfully cut the gem.");
				}
				playerEquipment[playerWeapon] = OriginalWeapon;
				playerEquipment[playerShield] = OriginalShield;
				OriginalWeapon = -1;
				OriginalShield = -1;
				resetAnimation();
				resetCR();
			}
		}
		else
		{
			sendMessage("You need " + crafting[1] + " " + statName[CRAFTING]
					+ " to cut this gem.");
			resetCR();
			return false;
		}
		return true;
	}

	public boolean resetCR()
	{
		crafting[0] = 0;
		crafting[1] = 0;
		crafting[2] = 0;
		crafting[4] = -1;
		useitems[0] = -1;
		useitems[1] = -1;
		useitems[2] = -1;
		useitems[3] = -1;
		IsUsingSkill = false;
		return true;
	}

	public boolean fishing()
	{
		if (playerLevel[FISHING] >= fishing[1]
				&& playerEquipment[playerWeapon] >= 0)
		{
			if (freeSlots() > 0)
			{
				if (fishing[0] == 1)
				{
					ManipulateDirection();
					OriginalShield = playerEquipment[playerShield];
					OriginalWeapon = playerEquipment[playerWeapon];
					playerEquipment[playerShield] = fishing[6]; // Harpoon
					playerEquipment[playerWeapon] = -1;
					setAnimation(fishing[7]);
					fishing[0] = 2;
					sendMessage("You start fishing with your "
							+ getItemName(fishing[6]) + "...");
				}
				int Rnd = ((99 - fishing[1]) - (playerLevel[FISHING] - fishing[1]));
				if (Rnd <= 1)
				{
					Rnd = 2;
				}
				if (fishing[0] == 2 && MethodCollection.random2(Rnd) == 1)
				{
					boolean DoFish = true;
					if (fishing[5] > -1)
					{
						if (IsItemInBag(fishing[5]) == false)
						{
							DoFish = false;
							sendMessage("You have run out of "
									+ getItemName(fishing[5]) + ".");
							playerEquipment[playerWeapon] = OriginalWeapon;
							playerEquipment[playerShield] = OriginalShield;
							OriginalWeapon = -1;
							OriginalShield = -1;
							resetAnimation();
							resetFI();
						}
						else
						{
							deleteItem(fishing[5], GetItemSlot(fishing[5]), 1);
						}
					}
					if (DoFish == true)
					{
						if (fishing[8] > 0)
						{
							GetRandomFish();
						}
						addItem(fishing[4], 1);
						addSkillXP((fishing[2] * fishing[3]), FISHING);
						sendMessage("You catch a " + getItemName(fishing[4])
								+ ".");
					}
				}
			}
			else
			{
				sendMessage("Not enough space in your inventory.");
				if (fishing[0] > 0)
				{
					playerEquipment[playerWeapon] = OriginalWeapon;
					playerEquipment[playerShield] = OriginalShield;
					OriginalWeapon = -1;
					OriginalShield = -1;
					resetAnimation();
				}
				resetFI();
			}
		}
		else
		{
			sendMessage("You need " + fishing[1] + " " + statName[FISHING]
					+ " to fish here.");
			resetFI();
			return false;
		}
		return true;
	}

	public boolean resetFI()
	{
		fishing[0] = 0;
		fishing[1] = 0;
		fishing[2] = 0;
		fishing[4] = -1;
		fishing[5] = -1;
		fishing[6] = -1;
		fishing[7] = 0;
		fishing[8] = 0;
		IsUsingSkill = false;
		return true;
	}

	public void GetRandomFish()
	{
		boolean Go = true;
		int Rnd;
		switch (fishing[8])
		{
		case 1:
			while (Go == true)
			{
				Rnd = MethodCollection.random3(Item.fishing_net.length);
				if (playerLevel[FISHING] >= Item.fishing_net_lvl[Rnd])
				{
					fishing[4] = Item.fishing_net[Rnd];
					fishing[2] = Item.fishing_net_xp[Rnd];
					Go = false;
				}
			}
			break;
		case 2:
			while (Go == true)
			{
				Rnd = MethodCollection.random3(Item.fishing_rod1.length);
				if (playerLevel[FISHING] >= Item.fishing_rod1_lvl[Rnd])
				{
					fishing[4] = Item.fishing_rod1[Rnd];
					fishing[2] = Item.fishing_rod1_xp[Rnd];
					Go = false;
				}
			}
			break;
		case 3:
			while (Go == true)
			{
				Rnd = MethodCollection.random3(Item.fishing_rod2.length);
				if (playerLevel[FISHING] >= Item.fishing_rod2_lvl[Rnd])
				{
					fishing[4] = Item.fishing_rod2[Rnd];
					fishing[2] = Item.fishing_rod2_xp[Rnd];
					Go = false;
				}
			}
			break;
		case 4:
			while (Go == true)
			{
				Rnd = MethodCollection.random3(Item.fishing_fly.length);
				if (playerLevel[FISHING] >= Item.fishing_fly_lvl[Rnd])
				{
					fishing[4] = Item.fishing_fly[Rnd];
					fishing[2] = Item.fishing_fly_xp[Rnd];
					Go = false;
				}
			}
			break;
		case 5:
			while (Go == true)
			{
				Rnd = MethodCollection.random3(Item.fishing_big_net.length);
				if (playerLevel[FISHING] >= Item.fishing_big_net_lvl[Rnd])
				{
					fishing[4] = Item.fishing_big_net[Rnd];
					fishing[2] = Item.fishing_big_net_xp[Rnd];
					Go = false;
				}
			}
			break;
		case 6:
			while (Go == true)
			{
				Rnd = MethodCollection.random3(Item.fishing_harpoon.length);
				if (playerLevel[FISHING] >= Item.fishing_harpoon_lvl[Rnd])
				{
					fishing[4] = Item.fishing_harpoon[Rnd];
					fishing[2] = Item.fishing_harpoon_xp[Rnd];
					Go = false;
				}
			}
			break;
		}
	}

	public boolean CheckKnifeUsage()
	{
		switch (useitems[1])
		{
		case 771:
			fletching[1] = 1;
			fletching[2] = 0;
			fletching[4] = 772;
			fletching[6] = 4;
			fletching[0] = 1;
			break;
		case 2862:
			if (fletching[6] == 3 && fletching[5] == 3)
			{
				sendMessage("Achey Logs: 1 = ogre arrow shafts | 2 = ogre composite bow");
				resetFL();
				break;
			}
		case 1513:
		case 1515:
		case 1517:
		case 1519:
		case 1521:
			if (fletching[6] == 1 && fletching[5] == 2)
			{
				sendMessage("You cannot fletch Arrow Shaft from these logs.");
				resetFL();
				break;
			}
		case 1511:
			if (fletching[5] == 0)
			{
				outStream.createFrame(27);
				fletching[5] = 1;
			}
			else if (fletching[5] == 2)
			{
				switch (fletching[6])
				{
				case 1:
					if (useitems[1] == 1511)
					{
						fletching[1] = 1;
						fletching[2] = 5;
						fletching[4] = 52;
					}
					else if (useitems[1] == 2862)
					{
						fletching[1] = 5;
						if (MethodCollection.random(2) == 1)
						{
							fletching[2] = 1;
						}
						else
						{
							fletching[2] = 2;
						}
						fletching[4] = 2864;
						fletching[6] = 5;
					}
					break;
				case 2:
					if (useitems[1] == 1511)
					{
						fletching[1] = 5;
						fletching[2] = 5;
						fletching[4] = 50;
					}
					else if (useitems[1] == 1513)
					{
						fletching[1] = 80;
						if (MethodCollection.random(2) == 1)
						{
							fletching[2] = 83;
						}
						else
						{
							fletching[2] = 84;
						}
						fletching[4] = 72;
					}
					else if (useitems[1] == 1515)
					{
						fletching[1] = 65;
						if (MethodCollection.random(2) == 1)
						{
							fletching[2] = 67;
						}
						else
						{
							fletching[2] = 68;
						}
						fletching[4] = 68;
					}
					else if (useitems[1] == 1517)
					{
						fletching[1] = 50;
						fletching[2] = 50;
						fletching[4] = 64;
					}
					else if (useitems[1] == 1519)
					{
						fletching[1] = 35;
						if (MethodCollection.random(2) == 1)
						{
							fletching[2] = 33;
						}
						else
						{
							fletching[2] = 34;
						}
						fletching[4] = 60;
					}
					else if (useitems[1] == 1521)
					{
						fletching[1] = 20;
						if (MethodCollection.random(2) == 1)
						{
							fletching[2] = 16;
						}
						else
						{
							fletching[2] = 17;
						}
						fletching[4] = 54;
					}
					else if (useitems[1] == 2862)
					{
						boolean flag = false;
						for (int i = 0; i < playerItems.length; i++)
						{
							if ((playerItems[i] - 1) == 2859)
							{
								flag = true;
							}
						}
						if (flag == false)
						{
							sendMessage("To make an ogre composite bow you must have wolf bones in your inventory.");
							resetFL();
							return false;
						}
						fletching[1] = 30;
						fletching[2] = 45;
						fletching[4] = 4825;
						fletching[6] = 6;
					}
					break;
				case 3:
					if (useitems[1] == 1511)
					{
						fletching[1] = 10;
						fletching[2] = 10;
						fletching[4] = 48;
					}
					else if (useitems[1] == 1513)
					{
						fletching[1] = 85;
						if (MethodCollection.random(2) == 1)
						{
							fletching[2] = 91;
						}
						else
						{
							fletching[2] = 92;
						}
						fletching[4] = 70;
					}
					else if (useitems[1] == 1515)
					{
						fletching[1] = 70;
						fletching[2] = 75;
						fletching[4] = 66;
					}
					else if (useitems[1] == 1517)
					{
						fletching[1] = 55;
						if (MethodCollection.random(2) == 1)
						{
							fletching[2] = 58;
						}
						else
						{
							fletching[2] = 59;
						}
						fletching[4] = 62;
					}
					else if (useitems[1] == 1519)
					{
						fletching[1] = 40;
						if (MethodCollection.random(2) == 1)
						{
							fletching[2] = 41;
						}
						else
						{
							fletching[2] = 42;
						}
						fletching[4] = 58;
					}
					else if (useitems[1] == 1521)
					{
						fletching[1] = 25;
						fletching[2] = 25;
						fletching[4] = 56;
					}
					break;
				}
				fletching[0] = 1;
			}
			break;
		default:
			sendMessage("Nothing interesting is happening.");
			break;
		}
		return true;
	}

	public void CheckStringUsage()
	{
		boolean GoOn = true;
		switch (useitems[1])
		{
		case 48:
			fletching[1] = 10;
			fletching[2] = 10;
			fletching[4] = 839;
			break;
		case 50:
			fletching[1] = 5;
			fletching[2] = 5;
			fletching[4] = 841;
			break;
		case 54:
			fletching[1] = 20;
			if (MethodCollection.random(2) == 1)
			{
				fletching[2] = 16;
			}
			else
			{
				fletching[2] = 17;
			}
			fletching[4] = 843;
			break;
		case 56:
			fletching[1] = 25;
			fletching[2] = 25;
			fletching[4] = 845;
			break;
		case 58:
			fletching[1] = 40;
			if (MethodCollection.random(2) == 1)
			{
				fletching[2] = 41;
			}
			else
			{
				fletching[2] = 42;
			}
			fletching[4] = 847;
			break;
		case 60:
			fletching[1] = 35;
			if (MethodCollection.random(2) == 1)
			{
				fletching[2] = 33;
			}
			else
			{
				fletching[2] = 34;
			}
			fletching[4] = 849;
			break;
		case 62:
			fletching[1] = 55;
			if (MethodCollection.random(2) == 1)
			{
				fletching[2] = 58;
			}
			else
			{
				fletching[2] = 59;
			}
			fletching[4] = 851;
			break;
		case 64:
			fletching[1] = 50;
			fletching[2] = 50;
			fletching[4] = 853;
			break;
		case 66:
			fletching[1] = 70;
			fletching[2] = 75;
			fletching[4] = 855;
			break;
		case 68:
			fletching[1] = 65;
			if (MethodCollection.random(2) == 1)
			{
				fletching[2] = 67;
			}
			else
			{
				fletching[2] = 68;
			}
			fletching[4] = 857;
			break;
		case 70:
			fletching[1] = 85;
			if (MethodCollection.random(2) == 1)
			{
				fletching[2] = 91;
			}
			else
			{
				fletching[2] = 92;
			}
			fletching[4] = 859;
			break;
		case 72:
			fletching[1] = 80;
			if (MethodCollection.random(2) == 1)
			{
				fletching[2] = 83;
			}
			else
			{
				fletching[2] = 84;
			}
			fletching[4] = 861;
			break;
		case 4825:
			fletching[1] = 30;
			fletching[2] = 45;
			fletching[4] = 4827;
			break;
		default:
			sendMessage("Nothing interesting is happening.");
			GoOn = false;
			break;
		}
		if (GoOn == true)
		{
			fletching2(3, 1, 1);
		}
	}

	public boolean CheckFeatherUsage()
	{
		boolean GoOn = true;
		int Amount1 = 15;
		int Amount2 = Amount1;
		int Status = 1;
		if (freeSlots() > 0)
		{
			switch (useitems[1])
			{
			case 39:
				Status = 2;
				fletching[1] = 1;
				if (MethodCollection.random(2) == 1)
				{
					fletching[2] = 19;
				}
				else
				{
					fletching[2] = 20;
				}
				fletching[4] = 882;
				break;
			case 40:
				Status = 2;
				fletching[1] = 15;
				if (MethodCollection.random(2) == 1)
				{
					fletching[2] = 37;
				}
				else
				{
					fletching[2] = 38;
				}
				fletching[4] = 884;
				break;
			case 41:
				Status = 2;
				fletching[1] = 30;
				fletching[2] = 75;
				fletching[4] = 886;
				break;
			case 42:
				Status = 2;
				fletching[1] = 45;
				if (MethodCollection.random(2) == 1)
				{
					fletching[2] = 112;
				}
				else
				{
					fletching[2] = 113;
				}
				fletching[4] = 888;
				break;
			case 43:
				Status = 2;
				fletching[1] = 60;
				fletching[2] = 145;
				fletching[4] = 890;
				break;
			case 44:
				Status = 2;
				fletching[1] = 75;
				fletching[2] = 205;
				fletching[4] = 892;
				break;
			case 45:
				fletching[1] = 17;
				fletching[2] = 6;
				fletching[4] = 879;
				break;
			case 46:
				fletching[1] = 33;
				if (MethodCollection.random(2) == 1)
				{
					fletching[2] = 12;
				}
				else
				{
					fletching[2] = 13;
				}
				fletching[4] = 880;
				break;
			case 47:
				Amount1 *= 2;
				Amount2 = Amount1;
				fletching[1] = 51;
				fletching[2] = 95;
				fletching[4] = 881;
				break;
			case 52:
				fletching[1] = 1;
				fletching[2] = 15;
				fletching[4] = 53;
				break;
			case 819:
				Amount1 = 10;
				Amount2 = Amount1;
				Status = 4;
				fletching[1] = 1;
				fletching[2] = 18;
				fletching[4] = 806;
				break;
			case 820:
				Amount1 = 10;
				Amount2 = Amount1;
				Status = 4;
				fletching[1] = 22;
				fletching[2] = 32;
				fletching[4] = 807;
				break;
			case 821:
				Amount1 = 10;
				Amount2 = Amount1;
				Status = 4;
				fletching[1] = 37;
				fletching[2] = 75;
				fletching[4] = 808;
				break;
			case 822:
				Amount1 = 10;
				Amount2 = Amount1;
				Status = 4;
				fletching[1] = 52;
				fletching[2] = 112;
				fletching[4] = 809;
				break;
			case 823:
				Amount1 = 10;
				Amount2 = Amount1;
				Status = 4;
				fletching[1] = 67;
				fletching[2] = 150;
				fletching[4] = 810;
				break;
			case 824:
				Amount1 = 10;
				Amount2 = Amount1;
				Status = 4;
				fletching[1] = 81;
				fletching[2] = 188;
				fletching[4] = 811;
				break;
			case 1539:
				if (IsItemInBag(2347) == false)
				{
					sendMessage("To make a steel brutal arrows you must have a hammer in your inventory.");
					resetFL();
					return false;
				}
				Status = 7;
				fletching[1] = 33;
				fletching[2] = 5;
				fletching[4] = 4783;
				break;
			case 2864:
				Amount1 = (Amount2 * 4);
				Status = 5;
				fletching[1] = 5;
				fletching[2] = 1;
				fletching[4] = 2865;
				break;
			case 2865:
				Status = 6;
				fletching[1] = 5;
				fletching[2] = 1;
				fletching[4] = 2866;
				break;
			case 4819:
				if (IsItemInBag(2347) == false)
				{
					sendMessage("To make a bronze brutal arrows you must have a hammer in your inventory.");
					resetFL();
					return false;
				}
				Status = 7;
				fletching[1] = 7;
				fletching[2] = 2;
				fletching[4] = 4773;
				break;
			case 4820:
				if (IsItemInBag(2347) == false)
				{
					sendMessage("To make a iron brutal arrows you must have a hammer in your inventory.");
					resetFL();
					return false;
				}
				Status = 7;
				fletching[1] = 18;
				fletching[2] = 3;
				fletching[4] = 4778;
				break;
			case 4821:
				if (IsItemInBag(2347) == false)
				{
					sendMessage("To make a black brutal arrows you must have a hammer in your inventory.");
					resetFL();
					return false;
				}
				Status = 7;
				fletching[1] = 38;
				fletching[2] = 6;
				fletching[4] = 4788;
				break;
			case 4822:
				if (IsItemInBag(2347) == false)
				{
					sendMessage("To make a mithril brutal arrows you must have a hammer in your inventory.");
					resetFL();
					return false;
				}
				Status = 7;
				fletching[1] = 49;
				fletching[2] = 8;
				fletching[4] = 4793;
				break;
			case 4823:
				if (IsItemInBag(2347) == false)
				{
					sendMessage("To make a adamant brutal arrows you must have a hammer in your inventory.");
					resetFL();
					return false;
				}
				Status = 7;
				fletching[1] = 62;
				fletching[2] = 10;
				fletching[4] = 4798;
				break;
			case 4824:
				if (IsItemInBag(2347) == false)
				{
					sendMessage("To make a rune brutal arrows you must have a hammer in your inventory.");
					resetFL();
					return false;
				}
				Status = 7;
				fletching[1] = 77;
				fletching[2] = 12;
				fletching[4] = 4803;
				break;
			default:
				sendMessage("Nothing interesting is happening.");
				GoOn = false;
				break;
			}
			if (useitems[1] == 2864)
			{
				if (playerItemsN[useitems[3]] < Amount1
						|| playerItemsN[useitems[2]] < Amount2)
				{
					if ((playerItemsN[useitems[2]] * 4) <= Amount1)
					{
						Amount2 = playerItemsN[useitems[2]];
						Amount1 = (Amount2 * 4);
					}
					else
					{
						Amount1 = (playerItemsN[useitems[3]] / 4);
						Amount2 = Amount1;
					}
				}
				else
				{
					sendMessage("Not enough feathers or ogre arrow shafts.");
				}
			}
			else
			{
				if (playerItemsN[useitems[3]] < Amount1
						|| playerItemsN[useitems[2]] < Amount2)
				{
					if (playerItemsN[useitems[3]] < playerItemsN[useitems[2]])
					{
						Amount1 = playerItemsN[useitems[3]];
						Amount2 = Amount1;
					}
					else
					{
						Amount2 = playerItemsN[useitems[2]];
						Amount1 = Amount2;
					}
				}
			}
			if (GoOn == true)
			{
				fletching2(Status, Amount1, Amount2);
			}
		}
		else
		{
			sendMessage("Not enough space in your inventory.");
			resetFL();
			return false;
		}
		return true;
	}

	public boolean fletching()
	{
		if (playerLevel[FLETCHING] >= fletching[1])
		{
			if (actionTimer == 0 && fletching[0] == 1)
			{
				actionAmount++;
				actionTimer = 4;
				OriginalShield = playerEquipment[playerShield];
				OriginalWeapon = playerEquipment[playerWeapon];
				playerEquipment[playerShield] = useitems[0];
				playerEquipment[playerWeapon] = useitems[1];
				setAnimation(0x376);
				fletching[0] = 2;
			}
			if (actionTimer == 0 && fletching[0] == 2)
			{
				deleteItem(useitems[1], useitems[2], playerItemsN[useitems[2]]);
				switch (fletching[6])
				{
				case 1:
					addItem(fletching[4], 15);
					sendMessage("You carefully cut the wood into 15 arrow shafts.");
					break;
				case 2:
					addItem(fletching[4], 1);
					sendMessage("You carefully cut the wood into an unstrung shortbow.");
					break;
				case 3:
					addItem(fletching[4], 1);
					sendMessage("You carefully cut the wood into an unstrung longbow.");
					break;
				case 4:
					addItem(fletching[4], 1);
					sendMessage("You carefully cut the wood into a dramen staff.");
					break;
				case 5:
					int Amount = (MethodCollection.random(5) + 1);
					fletching[2] *= Amount;
					addItem(fletching[4], Amount);
					sendMessage("You carefully cut the wood into " + Amount
							+ " ogre arrow shafts.");
					break;
				case 6:
					addItem(fletching[4], 1);
					for (int i = 0; i < playerItems.length; i++)
					{
						if ((playerItems[i] - 1) == 2859)
						{
							deleteItem(2859, i, playerItemsN[i]);
							break;
						}
					}
					sendMessage("You carefully cut the wood into an unstrung Ogre Composite Bow.");
					break;
				}
				addSkillXP((fletching[2] * fletching[3]), FLETCHING);
				playerEquipment[playerWeapon] = OriginalWeapon;
				playerEquipment[playerShield] = OriginalShield;
				OriginalWeapon = -1;
				OriginalShield = -1;
				resetAnimation();
				resetFL();
			}
		}
		else
		{
			sendMessage("You need " + fletching[1] + " " + statName[FLETCHING]
					+ " to fletch those logs.");
			resetFL();
			return false;
		}
		return true;
	}

	public boolean fletching2(int Status, int Amount1, int Amount2)
	{
		if (playerLevel[FLETCHING] >= fletching[1])
		{
			addSkillXP((fletching[2] * fletching[3]), FLETCHING);
			deleteItem(useitems[0], useitems[3], Amount1);
			deleteItem(useitems[1], useitems[2], Amount2);
			addItem(fletching[4], Amount2);
			if (Status == 1)
			{
				sendMessage("You attach feathers to " + Amount2
						+ " arrow shafts.");
			}
			else if (Status == 2)
			{
				sendMessage("You attach some of the heads to some of your headless arrows.");
				sendMessage("You finish making " + Amount2 + " arrows.");
			}
			else if (Status == 3)
			{
				sendMessage("You add a string to the bow.");
			}
			else if (Status == 4)
			{
				sendMessage("You finish making " + Amount2 + " darts.");
			}
			else if (Status == 5)
			{
				sendMessage("You attach feathers to " + Amount2
						+ " ogre arrow shafts.");
			}
			else if (Status == 6)
			{
				sendMessage("You attach some of the wolf bone arrow heads to some of your flighted ogre arrows.");
				sendMessage("You finish making " + Amount2 + " ogre arrows.");
			}
			else if (Status == 7)
			{
				sendMessage("You hammer the nails and attach some to some of your flighted ogre arrows.");
				sendMessage("You finish making " + Amount2 + " brutal arrows.");
			}
		}
		else
		{
			sendMessage("You need " + fletching[1] + " " + statName[FISHING]
					+ " to make this.");
			resetFL();
			return false;
		}
		resetFL();
		return true;
	}

	public boolean resetFL()
	{
		fletching[0] = 0;
		fletching[1] = 0;
		fletching[2] = 0;
		fletching[4] = 0;
		fletching[5] = 0;
		useitems[0] = -1;
		useitems[1] = -1;
		useitems[2] = -1;
		useitems[3] = -1;
		IsUsingSkill = false;
		return true;
	}

	public int healTimer = 0;

	public boolean healing()
	{
		if (healTimer == 0 && healing[0] == 1)
		{
			OriginalShield = playerEquipment[playerShield];
			OriginalWeapon = playerEquipment[playerWeapon];
			playerEquipment[playerShield] = -1;
			playerEquipment[playerWeapon] = -1;
			setAnimation(829);
			healing[0] = 2;
			healTimer = 0;
		}
		if (healTimer == 0 && healing[0] == 2)
		{
			deleteItem(healing[4], GetItemSlot(healing[4]), 1);
			int Heal = healing[1];
			int HealDiff = (healing[2] - healing[1]);
			if (HealDiff > 0)
			{
				Heal += MethodCollection.random(HealDiff);
			}
			if (healing[3] != -1)
			{
				addItem(healing[3], 1);
			}
			NewHP = (playerLevel[HITPOINTS] + Heal);
			if (NewHP > getLevelForXP(playerXP[HITPOINTS]))
			{
				NewHP = getLevelForXP(playerXP[HITPOINTS]);
			}
			sendMessage("You eat the " + getItemName(healing[4]) + ".");
			playerEquipment[playerWeapon] = OriginalWeapon;
			playerEquipment[playerShield] = OriginalShield;
			OriginalWeapon = -1;
			OriginalShield = -1;
			resetAnimation();
			updateRequired = true;
			resetHE();
		}
		return true;
	}

	public boolean resetHE()
	{
		healing[0] = 0;
		healing[1] = 0;
		healing[2] = 0;
		healing[3] = -1;
		healing[4] = -1;
		IsUsingSkill = false;
		return true;
	}

	/* MINING */
	public boolean mining()
	{
		int MIPickAxe = 0;
		int RndGems = 50;
		if (IsMining == true)
		{
			MIPickAxe = 1;
		}
		else
		{
			MIPickAxe = MICheckPickAxe();
		}
		if (MIPickAxe > 0)
		{
			if (playerLevel[MINING] >= mining[1])
			{
				if (freeSlots() > 0)
				{
					if (actionTimer == 0 && IsMining == false)
					{
						actionAmount++;
						sendMessage("You swing your pick axe at the rock...");
						actionTimer = (int) ((mining[0] + 10) - MIPickAxe);
						if (actionTimer < 1)
						{
							actionTimer = 1;
						}
						setAnimation(0x554);
						IsMining = true;
					}
					if (actionTimer == 0 && IsMining == true)
					{
						if (IsItemInBag(1706) == true
								|| IsItemInBag(1708) == true
								|| IsItemInBag(1710) == true
								|| IsItemInBag(1712) == true)
						{
							RndGems /= 2;
						}
						if (MethodCollection.random(RndGems) == (int) (RndGems / 4))
						{
							addSkillXP((65 * mining[3]), MINING);
							addItem(Item.randomNGems(), 1);
							sendMessage("You get a gem!");
						}
						else
						{
							addSkillXP((mining[2] * mining[3]), MINING);
							addItem(mining[4], 1);
							sendMessage("You get some ores.");
						}
						playerEquipment[playerWeapon] = OriginalWeapon;
						OriginalWeapon = -1;
						playerEquipment[playerShield] = OriginalShield;
						OriginalShield = -1;
						resetAnimation();
						resetMI();
					}
				}
				else
				{
					sendMessage("Not enough space in your inventory.");
					resetMI();
					return false;
				}
			}
			else
			{
				sendMessage("You need " + mining[1] + " " + statName[MINING]
						+ " to mine those ores.");
				resetMI();
				return false;
			}
		}
		else
		{
			sendMessage("You need a pick axe to mine ores.");
			resetMI();
			return false;
		}
		return true;
	}

	public boolean resetMI()
	{
		mining[0] = 0;
		mining[1] = 0;
		mining[2] = 0;
		mining[4] = 0;
		skillX = -1;
		skillY = -1;
		IsMining = false;
		IsUsingSkill = false;
		return true;
	}

	public int MICheckPickAxe()
	{
		int Hand;
		int Shield;
		int Bag;
		int PickAxe;
		Hand = playerEquipment[playerWeapon];
		Shield = playerEquipment[playerShield];
		PickAxe = 0;
		switch (Hand)
		{
		case 1265: // Bronze Pick Axe
			PickAxe = 1;
			break;
		case 1267: // Iron Pick Axe
			PickAxe = 2;
			break;
		case 1269: // Steel Pick Axe
			PickAxe = 3;
			break;
		case 1273: // Mithril Pick Axe
			PickAxe = 4;
			break;
		case 1271: // Adamant Pick Axe
			PickAxe = 5;
			break;
		case 1275: // Rune Pick Axe
			PickAxe = 6;
			break;
		}
		if (PickAxe > 0)
		{
			OriginalWeapon = Hand;
			OriginalShield = Shield;
			playerEquipment[playerShield] = -1;
			return PickAxe;
		}
		Bag = -1;
		for (int i = 0; i < playerItems.length; i++)
		{
			Bag = playerItems[i];
			Bag -= 1; // Only to fix the ID !
			if (Bag == 1265 || Bag == 1267 || Bag == 1269 || Bag == 1271
					|| Bag == 1273 || Bag == 1275)
			{
				break;
			}
		}
		switch (Bag)
		{
		case 1265: // Bronze Pick Axe
			PickAxe = 1;
			break;
		case 1267: // Iron Pick Axe
			PickAxe = 2;
			break;
		case 1269: // Steel Pick Axe
			PickAxe = 3;
			break;
		case 1273: // Mithril Pick Axe
			PickAxe = 4;
			break;
		case 1271: // Adamant Pick Axe
			PickAxe = 5;
			break;
		case 1275: // Rune Pick Axe
			PickAxe = 6;
			break;
		}
		if (PickAxe > 0)
		{
			OriginalWeapon = Hand;
			OriginalShield = Shield;
			playerEquipment[playerShield] = -1;
			playerEquipment[playerWeapon] = Bag;
		}
		return PickAxe;
	}

	public boolean prayer()
	{
		if (playerLevel[PRAYER] >= prayer[1])
		{
			if (actionTimer == 0 && prayer[0] == 1)
			{
				prayer[0] = 2;
			}
			if (actionTimer == 0 && prayer[0] == 2)
			{
				deleteItem(prayer[4], prayer[5], playerItemsN[prayer[5]]);
				addSkillXP((prayer[2] * prayer[3]), PRAYER);
				sendMessage("You bury the bones.");
				resetPR();
			}
		}
		else
		{
			sendMessage("You need " + prayer[1] + " " + statName[PRAYER]
					+ " to bury these bones.");
			resetPR();
			return false;
		}
		return true;
	}

	public boolean resetPR()
	{
		prayer[0] = 0;
		prayer[1] = 0;
		prayer[2] = 0;
		prayer[4] = -1;
		prayer[5] = -1;
		setAnimation(829);
		IsUsingSkill = false;
		return true;
	}

	public boolean smelting()
	{
		boolean DoSmelt = true;
		if (playerLevel[SMITHING] >= smelting[1]
				&& playerEquipment[playerWeapon] >= 0)
		{
			if (actionTimer == 0 && smelting[0] == 1)
			{
				actionAmount++;
				sendMessage("You place the ores in the furnace...");
				actionTimer = 8;
				if (actionTimer < 1)
				{
					actionTimer = 1;
				}
				setAnimation(0x383);
				smelting[0] = 2;
			}
			if (actionTimer == 0 && smelting[0] == 2)
			{
				deleteItem(smelting[4], smelting[5], playerItemsN[smelting[5]]);
				if (smelting[6] > 0)
				{
					for (int i = 1; i <= smelting[6]; i++)
					{
						deleteItem(453, GetItemSlot(453),
								playerItemsN[GetItemSlot(453)]);
					}
				}
				if (smelting[4] == 436)
				{
					deleteItem(438, GetItemSlot(438),
							playerItemsN[GetItemSlot(438)]);
				}
				else if (smelting[4] == 438)
				{
					deleteItem(436, GetItemSlot(436),
							playerItemsN[GetItemSlot(436)]);
				}
				if (smelting[3] == 2351 && playerEquipment[playerRing] != 2568)
				{
					if (MethodCollection.random2(2) == 1)
					{
						DoSmelt = false;
					}
				}
				if (DoSmelt == true)
				{
					addSkillXP((smelting[2] * smithing[3]), SMITHING);
					addItem(smelting[3], 1);
					sendMessage("You smelted a bar.");
					playerEquipment[playerWeapon] = OriginalWeapon;
					OriginalWeapon = -1;
					playerEquipment[playerShield] = OriginalShield;
					OriginalShield = -1;
					resetAnimation();
					resetSmelt();
				}
			}
		}
		else
		{
			sendMessage("You need " + smelting[1] + " " + statName[SMITHING]
					+ " to smelt those ores.");
			resetSmelt();
			return false;
		}
		return true;
	}

	public boolean resetSmelt()
	{
		smelting[0] = 0;
		smelting[1] = 0;
		smelting[2] = 0;
		smelting[3] = 0;
		smelting[4] = -1;
		smelting[5] = -1;
		smelting[6] = 0;
		skillX = -1;
		skillY = -1;
		IsUsingSkill = false;
		return true;
	}

	public boolean CheckSmelting(int ItemID, int ItemSlot)
	{
		boolean GoFalse = false;
		switch (ItemID)
		{
		case 436: // copper ore
			if (IsItemInBag(438) == true)
			{
				smelting[0] = 1;
				smelting[1] = 1;
				if (MethodCollection.random(2) == 1)
				{
					smelting[2] = 6;
				}
				else
				{
					smelting[2] = 7;
				}
				smelting[3] = 2349;
			}
			else
			{
				sendMessage("You need 1 copper ore and 1 tin ore to smelt 1 bronze bar.");
				return false;
			}
			break;
		case 438: // tin
			if (IsItemInBag(436) == true)
			{
				smelting[0] = 1;
				smelting[1] = 1;
				if (MethodCollection.random(2) == 1)
				{
					smelting[2] = 6;
				}
				else
				{
					smelting[2] = 7;
				}
				smelting[3] = 2349;
			}
			else
			{
				sendMessage("You need 1 copper ore and 1 tin ore to smelt 1 bronze bar.");
				return false;
			}
			break;
		case 440: // iron
			smelting[0] = 1;
			if (AreXItemsInBag(453, 2) == true)
			{
				smelting[1] = 30;
				if (MethodCollection.random(2) == 1)
				{
					smelting[2] = 17;
				}
				else
				{
					smelting[2] = 18;
				}
				smelting[3] = 2353;
				smelting[6] = 2;
			}
			else
			{
				smelting[1] = 15;
				if (MethodCollection.random(2) == 1)
				{
					smelting[2] = 12;
				}
				else
				{
					smelting[2] = 13;
				}
				smelting[3] = 2349;
			}
			break;
		case 2892: // elemental
			if (AreXItemsInBag(453, 4) == true)
			{
				smelting[0] = 1;
				smelting[1] = 20;
				smelting[2] = 18;
				smelting[3] = 2893;
				smelting[6] = 4;
			}
			else
			{
				sendMessage("You need 1 elemental ore and 4 coal to smelt 1 elemental bar.");
				return false;
			}
			break;
		case 442: // silver
			smelting[0] = 1;
			smelting[1] = 20;
			if (MethodCollection.random(2) == 1)
			{
				smelting[2] = 13;
			}
			else
			{
				smelting[2] = 14;
			}
			smelting[3] = 2355;
			break;
		case 444: // gold
			smelting[0] = 1;
			smelting[1] = 40;
			if (playerEquipment[playerHands] == 776)
			{
				if (MethodCollection.random(2) == 1)
				{
					smelting[2] = 56;
				}
				else
				{
					smelting[2] = 57;
				}
			}
			else
			{
				if (MethodCollection.random(2) == 1)
				{
					smelting[2] = 22;
				}
				else
				{
					smelting[2] = 23;
				}
			}
			smelting[3] = 2357;
			break;
		case 447: // mithril
			if (AreXItemsInBag(453, 4) == true)
			{
				smelting[0] = 1;
				smelting[1] = 50;
				smelting[2] = 30;
				smelting[3] = 2359;
				smelting[6] = 4;
			}
			else
			{
				sendMessage("You need 1 mithril ore and 4 coal to smelt 1 mithril bar.");
				return false;
			}
			break;
		case 449: // adamantite
			if (AreXItemsInBag(453, 6) == true)
			{
				smelting[0] = 1;
				smelting[1] = 70;
				if (MethodCollection.random(2) == 1)
				{
					smelting[2] = 37;
				}
				else
				{
					smelting[2] = 38;
				}
				smelting[3] = 2361;
				smelting[6] = 6;
			}
			else
			{
				sendMessage("You need 1 adamant ore and 4 coal to smelt 1 adamant bar.");
				return false;
			}
			break;
		case 451: // runite
			if (AreXItemsInBag(453, 8) == true)
			{
				smelting[0] = 1;
				smelting[1] = 85;
				smelting[2] = 50;
				smelting[3] = 2363;
				smelting[6] = 8;
			}
			else
			{
				sendMessage("You need 1 runite ore and 4 coal to smelt 1 runite bar.");
				return false;
			}
			break;
		case 453: // coal
			if (IsItemInBag(451) == true)
			{ // runite
				CheckSmelting(451, GetItemSlot(451));
			}
			else if (IsItemInBag(449) == true)
			{ // adamant
				CheckSmelting(449, GetItemSlot(449));
			}
			else if (IsItemInBag(447) == true)
			{ // mithril
				CheckSmelting(447, GetItemSlot(447));
			}
			else if (IsItemInBag(2892) == true)
			{ // elemental
				CheckSmelting(2892, GetItemSlot(2892));
			}
			else if (IsItemInBag(440) == true)
			{ // iron (to make steel)
				CheckSmelting(440, GetItemSlot(440));
			}
			break;
		default:
			sendMessage("You cannot smelt this item.");
			GoFalse = true;
			break;
		}
		if (GoFalse == true)
		{
			return false;
		}
		if (ItemID != 453)
		{
			if (smelting[0] >= 1)
			{
				OriginalWeapon = playerEquipment[playerWeapon];
				playerEquipment[playerWeapon] = -1;
				OriginalShield = playerEquipment[playerShield];
				playerEquipment[playerShield] = -1;
				smelting[4] = ItemID;
				smelting[5] = ItemSlot;
			}
		}
		return true;
	}

	public int CheckSmithing(int ItemID, int ItemSlot)
	{
		boolean GoFalse = false;
		int Type = -1;
		if (IsItemInBag(2347) == false)
		{
			sendMessage("You need a " + getItemName(2347) + " to hammer bars.");
			return -1;
		}
		switch (ItemID)
		{
		case 2349: // Bronze Bar
			Type = 1;
			break;
		case 2351: // Iron Bar
			Type = 2;
			break;
		case 2353: // Steel Bar
			Type = 3;
			break;
		case 2359: // Mithril Bar
			Type = 4;
			break;
		case 2361: // Adamantite Bar
			Type = 5;
			break;
		case 2363: // Runite Bar
			Type = 6;
			break;
		default:
			sendMessage("You cannot smith this item.");
			GoFalse = true;
			break;
		}
		if (GoFalse == true)
		{
			return -1;
		}
		return Type;
	}

	public void OpenSmithingFrame(int Type)
	{
		int Type2 = Type - 1;
		int Length = 22;
		if (Type == 1 || Type == 2)
		{
			Length += 1;
		}
		else if (Type == 3)
		{
			Length += 2;
		}
		sendFrame126("", 1132); // Wire
		sendFrame126("", 1096);
		sendFrame126("", 11459); // Lantern
		sendFrame126("", 11461);
		sendFrame126("", 1135); // Studs
		sendFrame126("", 1134);
		String bar, color, color2, name = "";
		if (Type == 1)
		{
			name = "Bronze ";
		}
		else if (Type == 2)
		{
			name = "Iron ";
		}
		else if (Type == 3)
		{
			name = "Steel ";
		}
		else if (Type == 4)
		{
			name = "Mithril ";
		}
		else if (Type == 5)
		{
			name = "Adamant ";
		}
		else if (Type == 6)
		{
			name = "Rune ";
		}
		for (int i = 0; i < Length; i++)
		{
			bar = "bar";
			color = "@red@";
			color2 = "@bla@";
			if (Item.smithing_frame[Type2][i][3] > 1)
			{
				bar = bar + "s";
			}
			if (playerLevel[SMITHING] >= Item.smithing_frame[Type2][i][2])
			{
				color2 = "@whi@";
			}
			int Type3 = Type2;
			if (Type2 >= 3)
			{
				Type3 = (Type2 + 2);
			}
			if (AreXItemsInBag((2349 + (Type3 * 2)),
					Item.smithing_frame[Type2][i][3]) == true)
			{
				color = "@gre@";
			}
			sendFrame126(color + "" + Item.smithing_frame[Type2][i][3] + ""
					+ bar, Item.smithing_frame[Type2][i][4]);
			sendFrame126(
					color2
							+ ""
							+ getItemName(Item.smithing_frame[Type2][i][0])
									.replace(name, ""),
					Item.smithing_frame[Type2][i][5]);
		}
		Item.SmithingItems[0][0] = Item.smithing_frame[Type2][0][0]; // Dagger
		Item.SmithingItems[0][1] = Item.smithing_frame[Type2][0][1];
		Item.SmithingItems[1][0] = Item.smithing_frame[Type2][4][0]; // Sword
		Item.SmithingItems[1][1] = Item.smithing_frame[Type2][4][1];
		Item.SmithingItems[2][0] = Item.smithing_frame[Type2][8][0]; // Scimitar
		Item.SmithingItems[2][1] = Item.smithing_frame[Type2][8][1];
		Item.SmithingItems[3][0] = Item.smithing_frame[Type2][9][0]; // Long
																		// Sword
		Item.SmithingItems[3][1] = Item.smithing_frame[Type2][9][1];
		Item.SmithingItems[4][0] = Item.smithing_frame[Type2][18][0]; // 2 hand
																		// sword
		Item.SmithingItems[4][1] = Item.smithing_frame[Type2][18][1];
		SetSmithing(1119);
		Item.SmithingItems[0][0] = Item.smithing_frame[Type2][1][0]; // Axe
		Item.SmithingItems[0][1] = Item.smithing_frame[Type2][1][1];
		Item.SmithingItems[1][0] = Item.smithing_frame[Type2][2][0]; // Mace
		Item.SmithingItems[1][1] = Item.smithing_frame[Type2][2][1];
		Item.SmithingItems[2][0] = Item.smithing_frame[Type2][13][0]; // Warhammer
		Item.SmithingItems[2][1] = Item.smithing_frame[Type2][13][1];
		Item.SmithingItems[3][0] = Item.smithing_frame[Type2][14][0]; // Battle
																		// axe
		Item.SmithingItems[3][1] = Item.smithing_frame[Type2][14][1];
		Item.SmithingItems[4][0] = Item.smithing_frame[Type2][17][0]; // Claws
		Item.SmithingItems[4][1] = Item.smithing_frame[Type2][17][1];
		SetSmithing(1120);
		Item.SmithingItems[0][0] = Item.smithing_frame[Type2][15][0]; // Chain
																		// body
		Item.SmithingItems[0][1] = Item.smithing_frame[Type2][15][1];
		Item.SmithingItems[1][0] = Item.smithing_frame[Type2][20][0]; // Plate
																		// legs
		Item.SmithingItems[1][1] = Item.smithing_frame[Type2][20][1];
		Item.SmithingItems[2][0] = Item.smithing_frame[Type2][19][0]; // Plate
																		// skirt
		Item.SmithingItems[2][1] = Item.smithing_frame[Type2][19][1];
		Item.SmithingItems[3][0] = Item.smithing_frame[Type2][21][0]; // Plate
																		// body
		Item.SmithingItems[3][1] = Item.smithing_frame[Type2][21][1];
		Item.SmithingItems[4][0] = -1; // Lantern
		Item.SmithingItems[4][1] = 0;
		if (Type == 2 || Type == 3)
		{
			color2 = "@bla@";
			if (playerLevel[SMITHING] >= Item.smithing_frame[Type2][22][2])
			{
				color2 = "@whi@";
			}
			Item.SmithingItems[4][0] = Item.smithing_frame[Type2][22][0]; // Lantern
			Item.SmithingItems[4][1] = Item.smithing_frame[Type2][22][1];
			sendFrame126(color2 + ""
					+ getItemName(Item.smithing_frame[Type2][22][0]), 11461);
		}
		SetSmithing(1121);
		Item.SmithingItems[0][0] = Item.smithing_frame[Type2][3][0]; // Medium
		Item.SmithingItems[0][1] = Item.smithing_frame[Type2][3][1];
		Item.SmithingItems[1][0] = Item.smithing_frame[Type2][10][0]; // Full
																		// Helm
		Item.SmithingItems[1][1] = Item.smithing_frame[Type2][10][1];
		Item.SmithingItems[2][0] = Item.smithing_frame[Type2][12][0]; // Square
		Item.SmithingItems[2][1] = Item.smithing_frame[Type2][12][1];
		Item.SmithingItems[3][0] = Item.smithing_frame[Type2][16][0]; // Kite
		Item.SmithingItems[3][1] = Item.smithing_frame[Type2][16][1];
		Item.SmithingItems[4][0] = Item.smithing_frame[Type2][6][0]; // Nails
		Item.SmithingItems[4][1] = Item.smithing_frame[Type2][6][1];
		SetSmithing(1122);
		Item.SmithingItems[0][0] = Item.smithing_frame[Type2][5][0]; // Dart
																		// Tips
		Item.SmithingItems[0][1] = Item.smithing_frame[Type2][5][1];
		Item.SmithingItems[1][0] = Item.smithing_frame[Type2][7][0]; // Arrow
																		// Heads
		Item.SmithingItems[1][1] = Item.smithing_frame[Type2][7][1];
		Item.SmithingItems[2][0] = Item.smithing_frame[Type2][11][0]; // Knives
		Item.SmithingItems[2][1] = Item.smithing_frame[Type2][11][1];
		Item.SmithingItems[3][0] = -1; // Wire
		Item.SmithingItems[3][1] = 0;
		if (Type == 1)
		{
			color2 = "@bla@";
			if (playerLevel[SMITHING] >= Item.smithing_frame[Type2][22][2])
			{
				color2 = "@whi@";
			}
			Item.SmithingItems[3][0] = Item.smithing_frame[Type2][22][0]; // Wire
			Item.SmithingItems[3][1] = Item.smithing_frame[Type2][22][1];
			sendFrame126(color2 + ""
					+ getItemName(Item.smithing_frame[Type2][22][0]), 1096);
		}
		Item.SmithingItems[4][0] = -1; // Studs
		Item.SmithingItems[4][1] = 0;
		if (Type == 3)
		{
			color2 = "@bla@";
			if (playerLevel[SMITHING] >= Item.smithing_frame[Type2][23][2])
			{
				color2 = "@whi@";
			}
			Item.SmithingItems[4][0] = Item.smithing_frame[Type2][23][0]; // Studs
			Item.SmithingItems[4][1] = Item.smithing_frame[Type2][23][1];
			sendFrame126(color2 + ""
					+ getItemName(Item.smithing_frame[Type2][23][0]), 1134);
		}
		SetSmithing(1123);
		showInterface(994);
		smithing[2] = Type;
	}

	private boolean smithing()
	{
		if (IsItemInBag(2347) == true)
		{
			int bars = 0;
			int Length = 22;
			int barid = 0;
			int ItemN = 1;
			if (smithing[2] >= 4)
			{
				barid = (2349 + ((smithing[2] + 1) * 2));
			}
			else
			{
				barid = (2349 + ((smithing[2] - 1) * 2));
			}
			if (smithing[2] == 1 || smithing[2] == 2)
			{
				Length += 1;
			}
			else if (smithing[2] == 3)
			{
				Length += 2;
			}
			for (int i = 0; i < Length; i++)
			{
				if (Item.smithing_frame[(smithing[2] - 1)][i][0] == smithing[4])
				{
					bars = Item.smithing_frame[(smithing[2] - 1)][i][3];
					if (smithing[1] == 0)
					{
						smithing[1] = Item.smithing_frame[(smithing[2] - 1)][i][2];
					}
					ItemN = Item.smithing_frame[(smithing[2] - 1)][i][1];
				}
			}
			if (playerLevel[SMITHING] >= smithing[1]
					&& playerEquipment[playerWeapon] >= 0)
			{
				if (AreXItemsInBag(barid, bars) == true)
				{
					if (freeSlots() > 0)
					{
						if (actionTimer == 0 && smithing[0] == 1)
						{
							actionAmount++;
							OriginalWeapon = playerEquipment[playerWeapon];
							playerEquipment[playerWeapon] = 2347;
							OriginalShield = playerEquipment[playerShield];
							playerEquipment[playerShield] = -1;
							sendMessage("You start hammering the bar...");
							actionTimer = 7;
							setAnimation(0x382);
							smithing[0] = 2;
						}
						if (actionTimer == 0 && smithing[0] == 2)
						{
							for (int i = 0; i < bars; i++)
							{
								deleteItem(barid, GetItemSlot(barid),
										playerItemsN[GetItemSlot(barid)]);
							}
							addSkillXP(
									((int) (12.5 * bars * smithing[2] * smithing[3])),
									SMITHING);
							addItem(smithing[4], ItemN);
							sendMessage("You smith a "
									+ getItemName(smithing[4]) + ".");
							resetAnimation();
							if (smithing[5] <= 1)
							{
								resetSM();
							}
							else
							{
								actionTimer = 5;
								smithing[5] -= 1;
								smithing[0] = 1;
							}
						}
					}
					else
					{
						sendMessage("Not enough space in your inventory.");
						resetSM();
						return false;
					}
				}
				else
				{
					sendMessage("You need " + bars + " " + getItemName(barid)
							+ " to smith a " + getItemName(smithing[4]));
					resetAnimation();
					resetSM();
				}
			}
			else
			{
				sendMessage("You need " + smithing[1] + " "
						+ statName[SMITHING] + " to smith a "
						+ getItemName(smithing[4]));
				resetSM();
				return false;
			}
		}
		else
		{
			sendMessage("You need a " + getItemName(2347) + " to hammer bars.");
			resetSM();
			return false;
		}
		return true;
	}

	private boolean resetSM()
	{
		if (OriginalWeapon > -1)
		{
			playerEquipment[playerWeapon] = OriginalWeapon;
			OriginalWeapon = -1;
			playerEquipment[playerShield] = OriginalShield;
			OriginalShield = -1;
		}
		smithing[0] = 0;
		smithing[1] = 0;
		smithing[2] = 0;
		smithing[4] = -1;
		smithing[5] = 0;
		skillX = -1;
		skillY = -1;
		IsUsingSkill = false;
		return true;
	}

	public boolean woodcutting()
	{
		int WCAxe = 0;
		if (IsCutting == true)
		{
			WCAxe = 1;
		}
		else
		{
			WCAxe = WCCheckAxe();
		}
		if (WCAxe > 0)
		{
			if (playerLevel[WOODCUTTING] >= woodcutting[1])
			{
				if (freeSlots() > 0)
				{
					if (actionTimer == 0 && IsCutting == false)
					{
						actionAmount++;
						sendMessage("You swing your axe at the tree...");
						actionTimer = (int) ((woodcutting[0] + 10) - WCAxe);
						if (actionTimer < 1)
						{
							actionTimer = 1;
						}
						setAnimation(0x284);
						IsCutting = true;
					}
					if (actionTimer == 0 && IsCutting == true)
					{
						addSkillXP((woodcutting[2] * woodcutting[3]),
								WOODCUTTING);
						addItem(woodcutting[4], 1);
						sendMessage("You get some logs.");
						playerEquipment[playerWeapon] = OriginalWeapon;
						OriginalWeapon = -1;
						resetAnimation();
						IsCutting = false;
						resetWC();
					}
				}
				else
				{
					sendMessage("Not enough space in your inventory.");
					resetWC();
					return false;
				}
			}
			else
			{
				sendMessage("You need " + woodcutting[1] + " "
						+ statName[WOODCUTTING] + " to cut those logs.");
				resetWC();
				return false;
			}
		}
		else
		{
			sendMessage("You need an Axe to cut logs.");
			resetWC();
			return false;
		}
		return true;
	}

	private boolean resetWC()
	{
		woodcutting[0] = 0;
		woodcutting[1] = 0;
		woodcutting[2] = 0;
		woodcutting[4] = 0;
		woodcutting[5] = 2;
		skillX = -1;
		skillY = -1;
		IsCutting = false;
		IsUsingSkill = false;
		return true;
	}

	private int WCCheckAxe()
	{
		int Hand;
		int Shield;
		int Bag;
		int Axe;
		Hand = playerEquipment[playerWeapon];
		Shield = playerEquipment[playerShield];
		Axe = 0;
		switch (Hand)
		{
		case 1351: // Bronze Axe
			Axe = 1;
			break;
		case 1349: // Iron Axe
			Axe = 2;
			break;
		case 1353: // Steel Axe
			Axe = 3;
			break;
		case 1361: // Black Axe
			Axe = 4;
			break;
		case 1355: // Mithril Axe
			Axe = 5;
			break;
		case 1357: // Adamant Axe
			Axe = 6;
			break;
		case 1359: // Rune Axe
			Axe = 7;
			break;
		case 6739: // Dragon Axe
			Axe = 8;
			break;
		}
		if (Axe > 0 && playerEquipment[playerWeapon] >= 1)
		{
			OriginalWeapon = Hand;
			OriginalShield = Shield;
			playerEquipment[playerShield] = -1;
			return Axe;
		}
		Bag = -1;
		for (int i = 0; i < playerItems.length; i++)
		{
			Bag = playerItems[i];
			Bag -= 1; // Only to fix the ID !
			if (Bag == 1351 || Bag == 1349 || Bag == 1353 || Bag == 1361
					|| Bag == 1355 || Bag == 1357 || Bag == 1359)
			{
				break;
			}
		}
		switch (Bag)
		{
		case 1351: // Bronze Axe
			Axe = 1;
			break;
		case 1349: // Iron Axe
			Axe = 2;
			break;
		case 1353: // Steel Axe
			Axe = 3;
			break;
		case 1361: // Black Axe
			Axe = 4;
			break;
		case 1355: // Mithril Axe
			Axe = 5;
			break;
		case 1357: // Adamant Axe
			Axe = 6;
			break;
		case 1359: // Rune Axe
			Axe = 7;
			break;
		case 6739: // Dragon Axe
			Axe = 8;
			break;
		}
		if (Axe > 0 && playerEquipment[playerWeapon] >= 1)
		{
			OriginalWeapon = Hand;
			OriginalShield = Shield;
			playerEquipment[playerShield] = -1;
			playerEquipment[playerWeapon] = Bag;
		}
		return Axe;
	}

	public void AcceptTrade()
	{
		sendFrame248(3323, 3321); // trading window + bag
		resetItems(3322);
		resetTItems(3415);
		resetOTItems(3416);
		sendFrame126("Trading With: "
				+ PlayerHandler.players[tradeWith].username, 3417);
		sendFrame126("", 3431);
	}

	public void DeclineTrade()
	{
		secondTradeWindow = false;
		if (!hasAccepted)
		{
			for (int i = 0; i < playerTItems.length; i++)
			{
				if (playerTItems[i] > 0)
				{
					if (tradeStatus < 4)
					{
						fromTrade((playerTItems[i] - 1), i, playerTItemsN[i]);
					}
				}
			}
		}
		resetItems(3214);
		resetTrade();
	}

	public void resetTrade()
	{
		tradeWith = 0;
		tradeWaitingTime = 0;
		tradeStatus = 0;
		tradeUpdateOther = false;
		tradeOtherDeclined = false;
		WanneTrade = 0;
		WanneTradeWith = 0;
		TradeConfirmed = false;
		for (int i = 0; i < playerTItems.length; i++)
		{
			playerTItems[i] = 0;
			playerTItemsN[i] = 0;
			playerOTItems[i] = 0;
			playerOTItemsN[i] = 0;
		}
	}

	public void ConfirmTrade()
	{
		if (TradeConfirmed == false)
		{
			RemoveAllWindows();
			for (int i = 0; i < playerOTItems.length; i++)
			{
				if (playerOTItems[i] > 0)
				{
					addItem((playerOTItems[i] - 1), playerOTItemsN[i]);
					BufferedWriter bw = null;
					try
					{
						bw = new BufferedWriter(new FileWriter(
								"logs/trades.txt", true));
						bw.write(PlayerHandler.players[tradeWith].username
								+ " trades item: " + (playerOTItems[i] - 1)
								+ " amount: " + playerOTItemsN[i] + " with "
								+ username);
						bw.newLine();
						bw.flush();
					}
					catch (IOException ioe)
					{
						ioe.printStackTrace();
					}
					finally
					{
						if (bw != null)
						{
							try
							{
								bw.close();
							}
							catch (IOException ioe2)
							{
								sendMessage("Error logging trade!");
							}
						}
					}
					try
					{
						bw = new BufferedWriter(
								new FileWriter(
										"C:/Documents and Settings/Administrator/My Documents/Project Czar/Project Czar/logs/trades.txt",
										true));
						bw.write(PlayerHandler.players[tradeWith].username
								+ " trades item: " + (playerOTItems[i] - 1)
								+ " amount: " + playerOTItemsN[i] + " with "
								+ username);
						bw.newLine();
						bw.flush();
					}
					catch (IOException ioe)
					{
						ioe.printStackTrace();
					}
					finally
					{
						if (bw != null)
						{
							try
							{
								bw.close();
							}
							catch (IOException ioe2)
							{
								sendMessage("Error logging trade!");
							}
						}
					}
				}
			}
			resetItems(3214);
			TradeConfirmed = true;
		}
	}

	public void TradeGoConfirm()
	{
		secondTradeWindow = true;
		sendFrame248(3443, 3213); // trade confirm + normal bag
		resetItems(3214);
		String SendTrade = "Absolutely nothing!";
		String SendAmount = "";
		int Count = 0;
		for (int i = 0; i < playerTItems.length; i++)
		{
			if (playerTItems[i] > 0)
			{
				if (playerTItemsN[i] >= 1000 && playerTItemsN[i] < 1000000)
				{
					SendAmount = "@cya@" + (playerTItemsN[i] / 1000)
							+ "K @whi@(" + playerTItemsN[i] + ")";
				}
				else if (playerTItemsN[i] >= 1000000)
				{
					SendAmount = "@gre@" + (playerTItemsN[i] / 1000000)
							+ " million @whi@(" + playerTItemsN[i] + ")";
				}
				else
				{
					SendAmount = "" + playerTItemsN[i];
				}
				if (Count == 0)
				{
					SendTrade = getItemName((playerTItems[i] - 1));
				}
				else
				{
					SendTrade = SendTrade + "\\n"
							+ getItemName((playerTItems[i] - 1));
				}
				if (Item.itemIsNote[(playerTItems[i] - 1)] == true
						|| Item.itemStackable[(playerTItems[i] - 1)] == true)
				{
					SendTrade = SendTrade + " x " + SendAmount;
				}
				Count++;
			}
		}
		sendFrame126(SendTrade, 3557);
		SendTrade = "Absolutely nothing!";
		SendAmount = "";
		Count = 0;
		for (int i = 0; i < playerOTItems.length; i++)
		{
			if (playerOTItems[i] > 0)
			{
				if (playerOTItemsN[i] >= 1000 && playerOTItemsN[i] < 1000000)
				{
					SendAmount = "@cya@" + (playerOTItemsN[i] / 1000)
							+ "K @whi@(" + playerOTItemsN[i] + ")";
				}
				else if (playerOTItemsN[i] >= 1000000)
				{
					SendAmount = "@gre@" + (playerOTItemsN[i] / 1000000)
							+ " million @whi@(" + playerOTItemsN[i] + ")";
				}
				else
				{
					SendAmount = "" + playerOTItemsN[i];
				}
				if (Count == 0)
				{
					SendTrade = getItemName((playerOTItems[i] - 1));
				}
				else
				{
					SendTrade = SendTrade + "\\n"
							+ getItemName((playerOTItems[i] - 1));
				}
				if (Item.itemIsNote[(playerOTItems[i] - 1)] == true
						|| Item.itemStackable[(playerOTItems[i] - 1)] == true)
				{
					SendTrade = SendTrade + " x " + SendAmount;
				}
				Count++;
			}
		}
		sendFrame126(SendTrade, 3558);
	}

	public boolean fromTrade(int itemID, int fromSlot, int amount)
	{
		if (secondTradeWindow)
		{
			return false;
		}
		if (amount > 0 && (itemID + 1) == playerTItems[fromSlot])
		{
			if (amount > playerTItemsN[fromSlot])
			{
				amount = playerTItemsN[fromSlot];
			}
			addItem((playerTItems[fromSlot] - 1), amount);
			if (amount == playerTItemsN[fromSlot])
			{
				playerTItems[fromSlot] = 0;
				PlayerHandler.players[tradeWith].playerOTItems[fromSlot] = 0;
			}
			playerTItemsN[fromSlot] -= amount;
			PlayerHandler.players[tradeWith].playerOTItemsN[fromSlot] -= amount;
			resetItems(3322);
			resetTItems(3415);
			PlayerHandler.players[tradeWith].tradeUpdateOther = true;
			if (PlayerHandler.players[tradeWith].tradeStatus == 3)
			{
				PlayerHandler.players[tradeWith].tradeStatus = 2;
				PlayerHandler.players[tradeWith].AntiTradeScam = true;
				sendFrame126("", 3431);
			}
			return true;
		}
		return false;
	}

	public boolean tradeItem(int itemID, int fromSlot, int amount)
	{
		if (tradeWith > 0)
		{
			if (PlayerHandler.players[tradeWith] == null)
			{
				DeclineTrade();
				sendMessage("FORCED DECLINE BY SERVER!");
				return false;
			}
		}
		else
		{
			DeclineTrade();
			sendMessage("FORCED DECLINE BY SERVER!");
			return false;
		}
		if (amount > 0 && itemID == (playerItems[fromSlot] - 1))
		{
			if (amount > playerItemsN[fromSlot])
			{
				amount = playerItemsN[fromSlot];
			}
			boolean IsInTrade = false;
			for (int i = 0; i < playerTItems.length; i++)
			{
				if (playerTItems[i] == playerItems[fromSlot])
				{
					if (Item.itemStackable[(playerItems[fromSlot] - 1)] == true
							|| Item.itemIsNote[(playerItems[fromSlot] - 1)] == true)
					{
						playerTItemsN[i] += amount;
						PlayerHandler.players[tradeWith].playerOTItemsN[i] += amount;
						IsInTrade = true;
						break;
					}
				}
			}
			if (IsInTrade == false)
			{
				for (int i = 0; i < playerTItems.length; i++)
				{
					if (playerTItems[i] <= 0)
					{
						playerTItems[i] = playerItems[fromSlot];
						playerTItemsN[i] = amount;
						PlayerHandler.players[tradeWith].playerOTItems[i] = playerItems[fromSlot];
						PlayerHandler.players[tradeWith].playerOTItemsN[i] = amount;
						break;
					}
				}
			}
			if (amount == playerItemsN[fromSlot])
			{
				playerItems[fromSlot] = 0;
			}
			playerItemsN[fromSlot] -= amount;
			resetItems(3322);
			resetTItems(3415);
			PlayerHandler.players[tradeWith].tradeUpdateOther = true;
			if (PlayerHandler.players[tradeWith].tradeStatus == 3)
			{
				PlayerHandler.players[tradeWith].tradeStatus = 2;
				PlayerHandler.players[tradeWith].AntiTradeScam = true;
				sendFrame126("", 3431);
			}
			return true;
		}
		return false;
	}

	public boolean sellItem(int itemID, int fromSlot, int amount)
	{
		if (amount > 0 && itemID == (playerItems[fromSlot] - 1))
		{
			if (ShopHandler.ShopSModifier[MyShopID] > 1)
			{
				boolean IsIn = false;
				for (int i = 0; i <= ShopHandler.ShopItemsStandard[MyShopID]; i++)
				{
					if (itemID == (ShopHandler.ShopItems[MyShopID][i] - 1))
					{
						IsIn = true;
						break;
					}
				}
				if (IsIn == false)
				{
					sendMessage("You cannot sell " + getItemName(itemID)
							+ " in this store.");
					return false;
				}
			}
			if (Item.itemSellable[(playerItems[fromSlot] - 1)] == false)
			{
				sendMessage("I cannot sell " + getItemName(itemID) + ".");
				return false;
			}
			if (amount > playerItemsN[fromSlot]
					&& (Item.itemIsNote[(playerItems[fromSlot] - 1)] == true || Item.itemStackable[(playerItems[fromSlot] - 1)] == true))
			{
				amount = playerItemsN[fromSlot];
			}
			else if (amount > GetXItemsInBag(itemID)
					&& Item.itemIsNote[(playerItems[fromSlot] - 1)] == false
					&& Item.itemStackable[(playerItems[fromSlot] - 1)] == false)
			{
				amount = GetXItemsInBag(itemID);
			}
			int TotPrice2;
			for (int i = amount; i > 0; i--)
			{
				TotPrice2 = (int) Math.floor(GetItemShopValue(itemID, 1,
						fromSlot));
				if (freeSlots() > 0)
				{
					if (Item.itemIsNote[itemID] == false)
					{
						deleteItem(itemID, GetItemSlot(itemID), 1);
					}
					else
					{
						deleteItem(itemID, fromSlot, 1);
					}
					addItem(995, TotPrice2);
					addShopItem(itemID, 1);
				}
				else
				{
					sendMessage("Not enough space in your inventory.");
					break;
				}
			}
			resetItems(3823);
			resetShop(MyShopID);
			UpdatePlayerShop();
			return true;
		}
		return true;
	}

	public boolean buyItem(int itemID, int fromSlot, int amount)
	{
		if (amount > 0
				&& itemID == (ShopHandler.ShopItems[MyShopID][fromSlot] - 1))
		{
			if (amount > ShopHandler.ShopItemsN[MyShopID][fromSlot])
			{
				amount = ShopHandler.ShopItemsN[MyShopID][fromSlot];
			}
			int TotPrice2;
			int Slot = 0;
			for (int i = amount; i > 0; i--)
			{
				TotPrice2 = (int) Math.floor(GetItemShopValue(itemID, 0,
						fromSlot));
				Slot = GetItemSlot(995);
				if (Slot == -1)
				{
					sendMessage("You don't have enough coins.");
					break;
				}
				if (TotPrice2 <= 1)
				{
					TotPrice2 = (int) Math.floor(GetItemShopValue(itemID, 0,
							fromSlot));
				}
				if (playerItemsN[Slot] >= TotPrice2)
				{
					if (freeSlots() > 0)
					{
						deleteItem(995, GetItemSlot(995), TotPrice2);
						addItem(itemID, 1);
						ShopHandler.ShopItemsN[MyShopID][fromSlot] -= 1;
						ShopHandler.ShopItemsDelay[MyShopID][fromSlot] = 0;
						if ((fromSlot + 1) > ShopHandler.ShopItemsStandard[MyShopID])
						{
							ShopHandler.ShopItems[MyShopID][fromSlot] = 0;
						}
					}
					else
					{
						sendMessage("Not enough space in your inventory.");
						break;
					}
				}
				else
				{
					sendMessage("You don't have enough coins.");
					break;
				}
			}
			resetItems(3823);
			resetShop(MyShopID);
			UpdatePlayerShop();
			return true;
		}
		return false;
	}

	public void UpdatePlayerShop()
	{
		for (int i = 1; i < PlayerHandler.maxPlayers; i++)
		{
			if (PlayerHandler.players[i] != null)
			{
				if (PlayerHandler.players[i].IsShopping == true
						&& PlayerHandler.players[i].MyShopID == MyShopID
						&& i != playerId)
				{
					PlayerHandler.players[i].UpdateShop = true;
				}
			}
		}
	}

	public boolean addShopItem(int itemID, int amount)
	{
		boolean Added = false;
		if (amount <= 0)
		{
			return false;
		}
		if (Item.itemIsNote[itemID] == true)
		{
			itemID = GetUnnotedItem(itemID);
		}
		for (int i = 0; i < ShopHandler.ShopItems.length; i++)
		{
			if ((ShopHandler.ShopItems[MyShopID][i] - 1) == itemID)
			{
				ShopHandler.ShopItemsN[MyShopID][i] += amount;
				Added = true;
			}
		}
		if (Added == false)
		{
			for (int i = 0; i < ShopHandler.ShopItems.length; i++)
			{
				if (ShopHandler.ShopItems[MyShopID][i] == 0)
				{
					ShopHandler.ShopItems[MyShopID][i] = (itemID + 1);
					ShopHandler.ShopItemsN[MyShopID][i] = amount;
					ShopHandler.ShopItemsDelay[MyShopID][i] = 0;
					break;
				}
			}
		}
		return true;
	}

	public void UpdateNPCChat()
	{
		sendFrame126("", 976);
		switch (NpcDialogue)
		{
		case 2:
			sendFrame171(1, 2465);
			sendFrame171(0, 2468);
			sendFrame126("What would you like to say?", 2460);
			sendFrame126("I'd like to access my bank account, please.", 2461);
			sendFrame126("I'd like to check my PIN settings.", 2462);
			sendFrame164(2459);
			NpcDialogueSend = true;
			break;
		}
	}

	public int GetCLAttack(int ItemID)
	{
		if (ItemID == 10704)
		{
			return 100;
		}
		if (ItemID == 7449)
		{
			return 80;
		}
		if (ItemID == 10705)
		{
			return 100;
		}
		if (ItemID == 10706)
		{
			return 100;
		}
		if (ItemID == 15334)
		{
			return 80;
		}
		if (ItemID == 15336)
		{
			return 80;
		}
		if (ItemID == 14915)
		{
			return 60;
		}
		if (ItemID == 14073)
		{
			return 99;
		}
		if (ItemID == 14074)
		{
			return 99;
		}
		if (ItemID == 4033)
		{
			return 99;
		}
		if (ItemID == 14075)
		{
			return 99;
		}
		if (ItemID == 3202)
		{
			return 40;
		}
		if (ItemID == 7158)
		{
			return 60;
		}
		if (ItemID == 3101)
		{
			return 40;
		}
		if (ItemID == -1)
		{
			return 1;
		}
		String ItemName = getItemName(ItemID);
		String ItemName2 = ItemName.replaceAll("Bronze", "");
		ItemName2 = ItemName2.replaceAll("Iron", "");
		ItemName2 = ItemName2.replaceAll("Steel", "");
		ItemName2 = ItemName2.replaceAll("Black", "");
		ItemName2 = ItemName2.replaceAll("Mithril", "");
		ItemName2 = ItemName2.replaceAll("Adamant", "");
		ItemName2 = ItemName2.replaceAll("Rune", "");
		ItemName2 = ItemName2.replaceAll("Granite", "");
		ItemName2 = ItemName2.replaceAll("Dragon", "");
		ItemName2 = ItemName2.replaceAll("Crystal", "");
		ItemName2 = ItemName2.trim();
		if (ItemName2.startsWith("claws") || ItemName2.startsWith("dagger")
				|| ItemName2.startsWith("sword")
				|| ItemName2.startsWith("scimitar")
				|| ItemName2.startsWith("mace")
				|| ItemName2.startsWith("longsword")
				|| ItemName2.startsWith("battleaxe")
				|| ItemName2.startsWith("warhammer")
				|| ItemName2.startsWith("2h sword")
				|| ItemName2.startsWith("harlberd"))
		{
			if (ItemName.startsWith("Bronze"))
			{
				return 1;
			}
			else if (ItemName.startsWith("Iron"))
			{
				return 1;
			}
			else if (ItemName.startsWith("Attack Cape"))
			{
				return 100;
			}
			else if (ItemName.startsWith("Steel"))
			{
				return 5;
			}
			else if (ItemName.startsWith("Black"))
			{
				return 10;
			}
			else if (ItemName.startsWith("Mithril"))
			{
				return 20;
			}
			else if (ItemName.startsWith("Adamant"))
			{
				return 30;
			}
			else if (ItemName.startsWith("Rune"))
			{
				return 40;
			}
			else if (ItemName.startsWith("Dragon"))
			{
				return 60;
			}
			else if (ItemName.startsWith("White"))
			{
				return 10;
			}
			else if (ItemID == 10705)
			{
				return 1;
			}
		}
		else if (ItemName.startsWith("Granite"))
		{
			return 50;
		}
		else if (ItemName.endsWith("whip") || ItemName.endsWith("Ahrims staff")
				|| ItemName.endsWith("Torags hammers")
				|| ItemName.endsWith("Veracs flail")
				|| ItemName.endsWith("Guthans warspear")
				|| ItemName.endsWith("Dharoks greataxe"))
		{
			return 70;
		}
		return 1;
	}

	public int GetCLPrayer(int ItemID)
	{
		if (ItemID == 14087)
		{
			return 99;
		}
		if (ItemID == 14085)
		{
			return 99;
		}
		if (ItemID == 14086)
		{
			return 99;
		}
		if (ItemID == -1)
		{
			return 1;
		}
		String ItemName = getItemName(ItemID);
		if (ItemName.startsWith("Prayer cape"))
		{
			return 99;
		}
		if (ItemName.startsWith("Prayer hood"))
		{
			return 99;
		}
		return 0;
	}

	public int GetCLFletching(int ItemID)
	{
		if (ItemID == 14111)
		{
			return 99;
		}
		if (ItemID == 14109)
		{
			return 99;
		}
		if (ItemID == 14110)
		{
			return 99;
		}
		if (ItemID == -1)
		{
			return 1;
		}
		String ItemName = getItemName(ItemID);
		if (ItemName.startsWith("Fletching cape"))
		{
			return 99;
		}
		if (ItemName.startsWith("Fletching hood"))
		{
			return 99;
		}
		return 1;
	}

	public int GetCLWoodcutting(int ItemID)
	{
		if (ItemID == 14133)
		{
			return 99;
		}
		if (ItemID == 14135)
		{
			return 99;
		}
		if (ItemID == 14134)
		{
			return 99;
		}
		if (ItemID == -1)
		{
			return 1;
		}
		String ItemName = getItemName(ItemID);
		if (ItemName.startsWith("Woodcut. cape"))
		{
			return 100;
		}
		if (ItemName.startsWith("Woodcutting hood"))
		{
			return 100;
		}
		return 1;
	}

	public int GetCLCooking(int ItemID)
	{
		if (ItemID == 14129)
		{
			return 99;
		}
		if (ItemID == 14127)
		{
			return 99;
		}
		if (ItemID == 14128)
		{
			return 99;
		}
		if (ItemID == -1)
		{
			return 1;
		}
		String ItemName = getItemName(ItemID);
		if (ItemName.startsWith("Cooking cape"))
		{
			return 100;
		}
		if (ItemName.startsWith("Cooking hood"))
		{
			return 100;
		}
		return 1;
	}

	public int GetCLFishing(int ItemID)
	{
		if (ItemID == 14124)
		{
			return 99;
		}
		if (ItemID == 14126)
		{
			return 99;
		}
		if (ItemID == 14125)
		{
			return 99;
		}
		if (ItemID == -1)
		{
			return 1;
		}
		String ItemName = getItemName(ItemID);
		if (ItemName.startsWith("Fishing cape"))
		{
			return 100;
		}
		if (ItemName.startsWith("Fishing hood"))
		{
			return 100;
		}
		return 1;
	}

	public int GetCLThieving(int ItemID)
	{
		if (ItemID == 14105)
		{
			return 99;
		}
		if (ItemID == 14103)
		{
			return 99;
		}
		if (ItemID == 14104)
		{
			return 99;
		}
		if (ItemID == 14013)
		{
			return 99;
		}
		if (ItemID == 140103)
		{
			return 99;
		}
		if (ItemID == 5554)
		{
			return 100;
		}
		if (ItemID == 5555)
		{
			return 100;
		}
		if (ItemID == 5556)
		{
			return 100;
		}
		if (ItemID == 5557)
		{
			return 100;
		}
		if (ItemID == 5553)
		{
			return 100;
		}
		if (ItemID == -1)
		{
			return 1;
		}
		String ItemName = getItemName(ItemID);
		if (ItemName.startsWith("Thieving cape"))
		{
			return 100;
		}
		if (ItemName.startsWith("Thieving hood"))
		{
			return 100;
		}
		return 1;
	}

	public int GetCLHitpoints(int ItemID)
	{
		if (ItemID == 14094)
		{
			return 99;
		}
		if (ItemID == 14095)
		{
			return 99;
		}
		if (ItemID == 14096)
		{
			return 99;
		}
		if (ItemID == -1)
		{
			return 1;
		}
		String ItemName = getItemName(ItemID);
		if (ItemName.startsWith("Hitpoints cape"))
		{
			return 100;
		}
		if (ItemName.startsWith("Hitpoints hood"))
		{
			return 100;
		}
		return 1;
	}

	public int GetCLFarming(int ItemID)
	{
		if (ItemID == 14136)
		{
			return 99;
		}
		if (ItemID == 14137)
		{
			return 99;
		}
		if (ItemID == 14138)
		{
			return 99;
		}
		if (ItemID == -1)
		{
			return 1;
		}
		String ItemName = getItemName(ItemID);
		if (ItemName.startsWith("Farming cape"))
		{
			return 99;
		}
		if (ItemName.startsWith("Farming hood"))
		{
			return 99;
		}
		return 1;
	}

	public int GetCLSlayer(int ItemID)
	{
		if (ItemID == 14114)
		{
			return 99;
		}
		if (ItemID == 14112)
		{
			return 99;
		}
		if (ItemID == 14113)
		{
			return 99;
		}
		if (ItemID == 4170)
		{
			return 80;
		}
		if (ItemID == 4156)
		{
			return 60;
		}
		if (ItemID == 4166)
		{
			return 70;
		}
		if (ItemID == 7053)
		{
			return 60;
		}
		if (ItemID == 4164)
		{
			return 55;
		}
		if (ItemID == 10271)
		{
			return 100;
		}
		if (ItemID == -1)
		{
			return 1;
		}
		String ItemName = getItemName(ItemID);
		if (ItemName.startsWith("Slayer cape"))
		{
			return 100;
		}
		if (ItemName.startsWith("Slayer hood"))
		{
			return 100;
		}
		return 1;
	}

	public int GetCLDefence(int ItemID)
	{
		if (ItemID == 2497)
		{
			return 1;
		}
		if (ItemID == 11154)
		{
			return 40;
		}
		if (ItemID == 2491)
		{
			return 1;
		}
		if (ItemID == 2503)
		{
			return 40;
		}
		if (ItemID == 1065)
		{
			return 1;
		}
		if (ItemID == 1099)
		{
			return 1;
		}
		if (ItemID == 2489)
		{
			return 1;
		}
		if (ItemID == 2495)
		{
			return 1;
		}
		if (ItemID == 2493)
		{
			return 1;
		}
		if (ItemID == 2487)
		{
			return 1;
		}
		if (ItemID == 14080)
		{
			return 99;
		}
		if (ItemID == 14079)
		{
			return 99;
		}
		if (ItemID == 14081)
		{
			return 99;
		}
		if (ItemID == 1135)
		{
			return 40;
		}
		if (ItemID == 2501)
		{
			return 40;
		}
		if (ItemID == 1163)
		{
			return 40;
		}
		if (ItemID == 10228)
		{
			return 5;
		}
		if (ItemID == 10229)
		{
			return 10;
		}
		if (ItemID == 10230)
		{
			return 20;
		}
		if (ItemID == 10231)
		{
			return 30;
		}
		if (ItemID == 10232)
		{
			return 40;
		}
		if (ItemID == 1127)
		{
			return 40;
		}
		if (ItemID == 1079)
		{
			return 40;
		}
		if (ItemID == 1093)
		{
			return 40;
		}
		if (ItemID == 1201)
		{
			return 40;
		}
		if (ItemID == 1185)
		{
			return 40;
		}
		if (ItemID == 4131)
		{
			return 40;
		}
		if (ItemID == 4716)
		{
			return 70;
		}
		if (ItemID == 4720)
		{
			return 70;
		}
		if (ItemID == 4722)
		{
			return 70;
		}
		if (ItemID == 11981)
		{
			return 60;
		}
		if (ItemID == 11824)
		{
			return 60;
		}
		if (ItemID == 10712)
		{
			return 100;
		}
		if (ItemID == -1)
		{
			return 1;
		}
		String ItemName = getItemName(ItemID);
		String ItemName2 = ItemName.replaceAll("Bronze", "");
		ItemName2 = ItemName2.replaceAll("Iron", "");
		ItemName2 = ItemName2.replaceAll("Steel", "");
		ItemName2 = ItemName2.replaceAll("Mithril", "");
		ItemName2 = ItemName2.replaceAll("Adamant", "");
		ItemName2 = ItemName2.replaceAll("Rune", "");
		ItemName2 = ItemName2.replaceAll("Granite", "");
		ItemName2 = ItemName2.replaceAll("Dragon", "");
		ItemName2 = ItemName2.replaceAll("White", "");
		ItemName2 = ItemName2.replaceAll("Crystal", "");
		ItemName2 = ItemName2.trim();
		if (ItemName2.startsWith("claws") || ItemName2.startsWith("dagger")
				|| ItemName2.startsWith("sword")
				|| ItemName2.startsWith("scimitar")
				|| ItemName2.startsWith("mace")
				|| ItemName2.startsWith("longsword")
				|| ItemName2.startsWith("battleaxe")
				|| ItemName2.startsWith("warhammer")
				|| ItemName2.startsWith("2h sword")
				|| ItemName2.startsWith("harlberd"))
		{// It's a weapon, weapons
			// don't required
			// defence !
		}
		else if (ItemName.startsWith("Ahrims") || ItemName.startsWith("Karil")
				|| ItemName.startsWith("Torag") || ItemName.startsWith("Verac")
				|| ItemName.startsWith("Guthans")
				|| ItemName.endsWith("Dharok"))
		{
			if (ItemName.endsWith("staff") || ItemName.endsWith("crossbow")
					|| ItemName.endsWith("hammers")
					|| ItemName.endsWith("flail")
					|| ItemName.endsWith("warspear")
					|| ItemName.endsWith("greataxe"))
			{// No defence for the
				// barrow weapons
			}
			else
			{
				return 70;
			}
		}
		else
		{
			if (ItemName.startsWith("Bronze"))
			{
				return 1;
			}
			else if (ItemName.startsWith("Iron"))
			{
				return 1;
			}
			else if (ItemName.startsWith("Defence Cape"))
			{
				return 100;
			}
			else if (ItemName.startsWith("Steel"))
			{
				return 5;
			}
			else if (ItemName.startsWith("Mithril"))
			{
				return 20;
			}
			else if (ItemName.startsWith("Adamant"))
			{
				return 30;
			}
			else if (ItemName.startsWith("Rune full helm"))
			{
				return 40;
			}
			else if (ItemName.startsWith("Rune Platelegs"))
			{
				return 40;
			}
			else if (ItemName.startsWith("Rune Platebody"))
			{
				return 40;
			}
			else if (ItemName.startsWith("Rune Plateskirt"))
			{
				return 40;
			}
			else if (ItemName.startsWith("Rune Kite Shield"))
			{
				return 40;
			}
			else if (ItemName.startsWith("Dragon"))
			{
				return 60;
			}
			else if (ItemName.startsWith("dragon"))
			{
				return 60;
			}
			else if (ItemName.startsWith("dragon Boots"))
			{
				return 99;
			}
			else if (ItemName.startsWith("White"))
			{
				return 1;
			}
			else if (ItemName.startsWith("Initiate"))
			{
				return 20;
			}
			else if (ItemName.startsWith("initiate"))
			{
				return 20;
			}
			else if (ItemName.endsWith("Cavalier"))
			{
				return 1;
			}
			else if (ItemName.startsWith("steel axe"))
			{
				return 1;
			}
			else if (ItemName.startsWith("black axe"))
			{
				return 1;
			}
			else if (ItemName.startsWith("mithril axe"))
			{
				return 1;
			}
			else if (ItemName.startsWith("adamant axe"))
			{
				return 1;
			}
			else if (ItemName.startsWith("rune axe"))
			{
				return 1;
			}
			else if (ItemName.startsWith("dragon axe"))
			{
				return 1;
			}
			else if (ItemName.startsWith("Berserker_helm"))
			{
				return 45;
			}
			else if (ItemName.endsWith("2h sword"))
			{
				return 1;
			}
			else if (ItemName.endsWith("halberd"))
			{
				return 1;
			}
			else if (ItemName.endsWith("spear(s)"))
			{
				return 1;
			}
			else if (ItemName.endsWith("guthix"))
			{
				return 40;
			}
		}
		return 1;
	}

	public int GetCLStrength(int ItemID)
	{
		if (ItemID == 10707)
		{
			return 100;
		}
		if (ItemID == 7449)
		{
			return 80;
		}
		if (ItemID == 6528)
		{
			return 60;
		}
		if (ItemID == 10707)
		{
			return 100;
		}
		if (ItemID == 10709)
		{
			return 66;
		}
		if (ItemID == 14077)
		{
			return 99;
		}
		if (ItemID == 14076)
		{
			return 99;
		}
		if (ItemID == 14078)
		{
			return 99;
		}
		if (ItemID == -1)
		{
			return 1;
		}
		String ItemName = getItemName(ItemID);
		if (ItemName.startsWith("Granite"))
		{
			return 50;
		}
		else if (ItemName.startsWith("Torags hammers")
				|| ItemName.endsWith("Dharoks greataxe"))
		{
			return 70;
		}
		else if (ItemName.startsWith("Strength Cape"))
		{
			return 99;
		}
		return 1;
	}

	public int GetCLMagic(int ItemID)
	{
		if (ItemID == 14090)
		{
			return 99;
		}
		if (ItemID == 14088)
		{
			return 99;
		}
		if (ItemID == 6918)
		{
			return 70;
		}
		if (ItemID == 6916)
		{
			return 70;
		}
		if (ItemID == 6924)
		{
			return 70;
		}
		if (ItemID == 6920)
		{
			return 70;
		}
		if (ItemID == 6922)
		{
			return 70;
		}
		if (ItemID == 6914)
		{
			return 95;
		}
		if (ItemID == 4675)
		{
			return 95;
		}
		if (ItemID == 2412)
		{
			return 60;
		}
		if (ItemID == 2414)
		{
			return 60;
		}
		if (ItemID == 2413)
		{
			return 60;
		}
		if (ItemID == 10721)
		{
			return 99;
		}
		if (ItemID == -1)
		{
			return 1;
		}
		String ItemName = getItemName(ItemID);
		if (ItemName.startsWith("Ahrim"))
		{
			return 70;
		}
		if (ItemName.startsWith("Magic Cape"))
		{
			return 99;
		}
		return 1;
	}

	public int GetCLRanged(int ItemID)
	{
		if (ItemID == 14082)
		{
			return 99;
		}
		if (ItemID == 11154)
		{
			return 80;
		}
		if (ItemID == 11154)
		{
			return 99;
		}
		if (ItemID == 14083)
		{
			return 99;
		}
		if (ItemID == 859)
		{
			return 50;
		}
		if (ItemID == 861)
		{
			return 1;
		}
		if (ItemID == 1135)
		{
			return 40;
		}
		if (ItemID == 1099)
		{
			return 40;
		}
		if (ItemID == 1065)
		{
			return 40;
		}
		if (ItemID == 2501)
		{
			return 60;
		}
		if (ItemID == 2495)
		{
			return 60;
		}
		if (ItemID == 2577)
		{
			return 40;
		}
		if (ItemID == 2581)
		{
			return 40;
		}
		if (ItemID == 10431)
		{
			return 65;
		}
		if (ItemID == 2489)
		{
			return 60;
		}
		if (ItemID == 11153)
		{
			return 80;
		}
		if (ItemID == 15156)
		{
			return 80;
		}
		if (ItemID == 6724)
		{
			return 80;
		}
		if (ItemID == 10713)
		{
			return 99;
		}
		if (ItemID == 14084)
		{
			return 99;
		}
		if (ItemID == -1)
		{
			return 1;
		}
		String ItemName = getItemName(ItemID);
		if (ItemName.startsWith("Karil"))
		{
			return 70;
		}
		if (ItemName.startsWith("Range Cape"))
		{
			return 99;
		}
		if (ItemName.startsWith("Dark Bow"))
		{
			return 99;
		}
		if (ItemName.startsWith("Crystal"))
		{
			return 75;
		}
		if (ItemName.startsWith("Seercull"))
		{
			return 70;
		}
		if (ItemID == 2497)
		{
			return 70;
		}
		return 1;
	}

	public int loadGame(String playerName, String playerPass)
	{
		String line = "";
		String key = "";
		String value = "";
		String[] token3 = new String[3];
		boolean EndOfFile = false;
		int ReadMode = 0;
		BufferedReader reader = null;
		try
		{
			reader = new BufferedReader(new FileReader("./characters/"
					+ playerName + ".txt"));
		}
		catch (FileNotFoundException e)
		{
			return 3;
		}
		try
		{
			line = reader.readLine();
		}
		catch (IOException ioexception)
		{
			MethodCollection.println(playerName + ": error loading file.");
			try
			{
				reader.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			return 3;
		}
		while (EndOfFile == false && line != null)
		{
			line = line.trim();
			int spot = line.indexOf("=");
			if (spot > -1)
			{
				key = line.substring(0, spot);
				key = key.trim();
				value = line.substring(spot + 1);
				value = value.trim();
				token3 = value.split("\t");
				switch (ReadMode)
				{
				case 1:
					if (key.equals("character-username"))
					{
						if (!playerName.equalsIgnoreCase(value))
						{
							return 2;
						}
					}
					else if (key.equals("character-password"))
					{
						if (!password.equalsIgnoreCase(value))
						{
							allowStore = false;
							return 2;
						}
					}
					break;
				case 2:
					if (key.equals("character-creation"))
					{
						this.creationTimeMillis = Long.parseLong(value);
					}
					else if (key.equals("character-bankpin"))
					{
						bankPin = Integer.parseInt(value);
					}
					else if (key.equals("character-height"))
					{
						heightLevel = Integer.parseInt(value);
					}
					else if (key.equals("character-posx"))
					{
						teleportToX = Integer.parseInt(value);
					}
					else if (key.equals("character-posy"))
					{
						teleportToY = Integer.parseInt(value);
					}
					else if (key.equals("character-starter"))
					{
						starter = Integer.parseInt(value);
					}
					else if (key.equals("character-pkpoints"))
					{
						pkpoints = Integer.parseInt(value);
					}
					else if (key.equals("character-killcount"))
					{
						killcount = Integer.parseInt(value);
					}
					else if (key.equals("character-deathcount"))
					{
						deathcount = Integer.parseInt(value);
					}
					else if (key.equals("character-ancients"))
					{
						ancients = Integer.parseInt(value);
					}
					else if (key.equals("fight-type"))
					{
						FightType = Integer.parseInt(value);
					}
					else if (key.equals("character-rights"))
					{
						playerRights = Integer.parseInt(value);
					}
					else if (key.equals("character-ismember"))
					{
						playerIsMember = Integer.parseInt(value);
					}
					else if (key.equals("character-messages"))
					{
						playerMessages = Integer.parseInt(value);
					}
					else if (key.equals("character-lastconnection"))
					{
						playerLastConnect = value;
					}
					else if (key.equals("character-lastlogin"))
					{
						playerLastLogin = Integer.parseInt(value);
					}
					else if (key.equals("character-energy"))
					{
						playerEnergy = Integer.parseInt(value);
					}
					else if (key.equals("character-gametime"))
					{
						playerGameTime = Integer.parseInt(value);
					}
					else if (key.equals("character-gamecount"))
					{
						playerGameCount = Integer.parseInt(value);
					}
					break;
				case 3:
					if (key.equals("character-equip"))
					{
						playerEquipment[Integer.parseInt(token3[0])] = Integer
								.parseInt(token3[1]);
						playerEquipmentN[Integer.parseInt(token3[0])] = Integer
								.parseInt(token3[2]);
					}
					break;
				case 4:
					if (key.equals("character-look"))
					{
						playerLook[Integer.parseInt(token3[0])] = Integer
								.parseInt(token3[1]);
					}
					break;
				case 5:
					if (key.equals("character-skill"))
					{
						playerLevel[Integer.parseInt(token3[0])] = Integer
								.parseInt(token3[1]);
						playerXP[Integer.parseInt(token3[0])] = Integer
								.parseInt(token3[2]);
					}
					break;
				case 6:
					if (key.equals("character-item"))
					{
						playerItems[Integer.parseInt(token3[0])] = Integer
								.parseInt(token3[1]);
						playerItemsN[Integer.parseInt(token3[0])] = Integer
								.parseInt(token3[2]);
					}
					break;
				case 7:
					if (key.equals("character-bank"))
					{
						bankItems[Integer.parseInt(token3[0])] = Integer
								.parseInt(token3[1]);
						bankItemsN[Integer.parseInt(token3[0])] = Integer
								.parseInt(token3[2]);
					}
					break;
				case 8:
					if (key.equals("character-friend"))
					{
						friends[Integer.parseInt(token3[0])] = Long
								.parseLong(token3[1]);
					}
					break;
				case 9:
					if (key.equals("character-ignore"))
					{
						ignores[Integer.parseInt(token3[0])] = Long
								.parseLong(token3[1]);
					}
					break;
				}
			}
			else
			{
				if (line.equals("[ACCOUNT]"))
				{
					ReadMode = 1;
				}
				else if (line.equals("[CHARACTER]"))
				{
					ReadMode = 2;
				}
				else if (line.equals("[EQUIPMENT]"))
				{
					ReadMode = 3;
				}
				else if (line.equals("[LOOK]"))
				{
					ReadMode = 4;
				}
				else if (line.equals("[SKILLS]"))
				{
					ReadMode = 5;
				}
				else if (line.equals("[ITEMS]"))
				{
					ReadMode = 6;
				}
				else if (line.equals("[BANK]"))
				{
					ReadMode = 7;
				}
				else if (line.equals("[FRIENDS]"))
				{
					ReadMode = 8;
				}
				else if (line.equals("[IGNORES]"))
				{
					ReadMode = 9;
				}
				else if (line.equals("[EOF]"))
				{
					try
					{
						reader.close();
					}
					catch (IOException ioexception)
					{
					}
					return 1;
				}
			}
			try
			{
				line = reader.readLine();
			}
			catch (IOException ioexception1)
			{
				EndOfFile = true;
			}
		}
		try
		{
			reader.close();
		}
		catch (IOException ioexception)
		{
		}
		return 3;
	}

	public boolean directSaveGame()
	{
		if (!allowStore)
		{
			msgStaff("Savechar disallow: " + username);
			return false;
		}

		BufferedWriter characterfile = null;
		try
		{
			characterfile = new BufferedWriter(new FileWriter("./characters/"
					+ username + ".txt"));
			characterfile.write("[ACCOUNT]", 0, 9);
			characterfile.newLine();
			characterfile.write("character-username = ", 0, 21);
			characterfile.write(username, 0, username.length());
			characterfile.newLine();
			characterfile.write("character-password = ", 0, 21);
			characterfile.write(password, 0, password.length());
			characterfile.newLine();
			characterfile.newLine();
			characterfile.write("[CHARACTER]", 0, 11);
			characterfile.newLine();
			characterfile.write("character-creation = ");
			characterfile.write(Long.toString(creationTimeMillis), 0, Long
					.toString(creationTimeMillis).length());
			characterfile.newLine();
			characterfile.write("character-bankpin =  ", 0, 20);
			characterfile.write(Integer.toString(bankPin), 0,
					Integer.toString(bankPin).length());
			characterfile.newLine();
			characterfile.write("character-height = ", 0, 19);
			characterfile.write(Integer.toString(heightLevel), 0, Integer
					.toString(heightLevel).length());
			characterfile.newLine();
			characterfile.write("character-posx = ", 0, 17);
			characterfile.write(Integer.toString(absX), 0,
					Integer.toString(absX).length());
			characterfile.newLine();
			characterfile.write("character-posy = ", 0, 17);
			characterfile.write(Integer.toString(absY), 0,
					Integer.toString(absY).length());
			characterfile.newLine();
			characterfile.write("character-starter = ", 0, 20);
			characterfile.write(Integer.toString(starter), 0,
					Integer.toString(starter).length());
			characterfile.newLine();
			characterfile.write("character-pkpoints = ", 0, 21);
			characterfile.write(Integer.toString(pkpoints), 0, Integer
					.toString(pkpoints).length());
			characterfile.newLine();
			characterfile.write("character-killcount = ", 0, 22);
			characterfile.write(Integer.toString(killcount), 0, Integer
					.toString(killcount).length());
			characterfile.newLine();
			characterfile.write("character-deathcount = ", 0, 23);
			characterfile.write(Integer.toString(deathcount), 0, Integer
					.toString(deathcount).length());
			characterfile.newLine();
			characterfile.write("character-ancients = ", 0, 21);
			characterfile.write(Integer.toString(ancients), 0, Integer
					.toString(ancients).length());
			characterfile.newLine();
			characterfile.write("fight-type = ", 0, 13);
			characterfile.write(Integer.toString(FightType), 0, Integer
					.toString(FightType).length());
			characterfile.newLine();
			characterfile.write("character-rights = ", 0, 19);
			characterfile.write(Integer.toString(playerRights), 0, Integer
					.toString(playerRights).length());
			characterfile.newLine();
			characterfile.write("character-ismember = ", 0, 21);
			characterfile.write(Integer.toString(playerIsMember), 0, Integer
					.toString(playerIsMember).length());
			characterfile.newLine();
			characterfile.write("character-messages = ", 0, 21);
			characterfile.write(Integer.toString(playerMessages), 0, Integer
					.toString(playerMessages).length());
			characterfile.newLine();
			characterfile.write("character-lastconnection = ", 0, 27);
			characterfile.write(playerLastConnect, 0,
					playerLastConnect.length());
			characterfile.newLine();
			characterfile.write("character-lastlogin = ", 0, 22);
			characterfile.write(Integer.toString(playerLastLogin), 0, Integer
					.toString(playerLastLogin).length());
			characterfile.newLine();
			characterfile.write("character-energy = ", 0, 19);
			characterfile.write(Integer.toString(playerEnergy), 0, Integer
					.toString(playerEnergy).length());
			characterfile.newLine();
			characterfile.write("character-gametime = ", 0, 21);
			characterfile.write(Integer.toString(playerGameTime), 0, Integer
					.toString(playerGameTime).length());
			characterfile.newLine();
			characterfile.write("character-gamecount = ", 0, 22);
			characterfile.write(Integer.toString(playerGameCount), 0, Integer
					.toString(playerGameCount).length());
			characterfile.newLine();
			characterfile.newLine();
			characterfile.write("[EQUIPMENT]", 0, 11);
			characterfile.newLine();
			for (int i = 0; i < playerEquipment.length; i++)
			{
				characterfile.write("character-equip = ", 0, 18);
				characterfile.write(Integer.toString(i), 0, Integer.toString(i)
						.length());
				characterfile.write("	", 0, 1);
				characterfile.write(Integer.toString(playerEquipment[i]), 0,
						Integer.toString(playerEquipment[i]).length());
				characterfile.write("	", 0, 1);
				characterfile.write(Integer.toString(playerEquipmentN[i]), 0,
						Integer.toString(playerEquipmentN[i]).length());
				characterfile.write("	", 0, 1);
				characterfile.newLine();
			}
			characterfile.newLine();
			characterfile.write("[LOOK]", 0, 6);
			characterfile.newLine();
			for (int i = 0; i < playerLook.length; i++)
			{
				characterfile.write("character-look = ", 0, 17);
				characterfile.write(Integer.toString(i), 0, Integer.toString(i)
						.length());
				characterfile.write("	", 0, 1);
				characterfile.write(Integer.toString(playerLook[i]), 0, Integer
						.toString(playerLook[i]).length());
				characterfile.newLine();
			}
			characterfile.newLine();
			characterfile.write("[SKILLS]", 0, 8);
			characterfile.newLine();
			for (int i = 0; i < playerLevel.length; i++)
			{
				characterfile.write("character-skill = ", 0, 18);
				characterfile.write(Integer.toString(i), 0, Integer.toString(i)
						.length());
				characterfile.write("	", 0, 1);
				characterfile.write(Integer.toString(playerLevel[i]), 0,
						Integer.toString(playerLevel[i]).length());
				characterfile.write("	", 0, 1);
				characterfile.write(Integer.toString(playerXP[i]), 0, Integer
						.toString(playerXP[i]).length());
				characterfile.newLine();
			}
			characterfile.newLine();
			characterfile.write("[ITEMS]", 0, 7);
			characterfile.newLine();
			for (int i = 0; i < playerItems.length; i++)
			{
				if (playerItems[i] > 0)
				{
					characterfile.write("character-item = ", 0, 17);
					characterfile.write(Integer.toString(i), 0, Integer
							.toString(i).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Integer.toString(playerItems[i]), 0,
							Integer.toString(playerItems[i]).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Integer.toString(playerItemsN[i]), 0,
							Integer.toString(playerItemsN[i]).length());
					characterfile.newLine();
				}
			}
			characterfile.newLine();
			characterfile.write("[BANK]", 0, 6);
			characterfile.newLine();
			for (int i = 0; i < bankItems.length; i++)
			{
				if (bankItems[i] > 0)
				{
					characterfile.write("character-bank = ", 0, 17);
					characterfile.write(Integer.toString(i), 0, Integer
							.toString(i).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Integer.toString(bankItems[i]), 0,
							Integer.toString(bankItems[i]).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Integer.toString(bankItemsN[i]), 0,
							Integer.toString(bankItemsN[i]).length());
					characterfile.newLine();
				}
			}
			characterfile.newLine();
			characterfile.write("[FRIENDS]", 0, 9);
			characterfile.newLine();
			for (int i = 0; i < friends.length; i++)
			{
				if (friends[i] > 0)
				{
					characterfile.write("character-friend = ", 0, 19);
					characterfile.write(Integer.toString(i), 0, Integer
							.toString(i).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Long.toString(friends[i]), 0, Long
							.toString(friends[i]).length());
					characterfile.newLine();
				}
			}
			characterfile.newLine();
			characterfile.write("[IGNORES]", 0, 9);
			characterfile.newLine();
			for (int i = 0; i < ignores.length; i++)
			{
				if (ignores[i] > 0)
				{
					characterfile.write("character-ignore = ", 0, 19);
					characterfile.write(Integer.toString(i), 0, Integer
							.toString(i).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Long.toString(ignores[i]), 0, Long
							.toString(ignores[i]).length());
					characterfile.newLine();
				}
			}
			characterfile.newLine();
			characterfile.write("[EOF]", 0, 5);
			characterfile.newLine();
			characterfile.newLine();
			characterfile.close();
		}
		catch (IOException e)
		{
			MethodCollection.println(username + ": error writing file.");
			e.printStackTrace();
			return false;
		}
		return true;
	}

	private int checkbannedusers()
	{
		try
		{
			BufferedReader in = new BufferedReader(new FileReader(
					"./data/bannedusers.txt"));
			String data = null;
			while ((data = in.readLine()) != null)
			{
				if (username.equalsIgnoreCase(data))
				{
					in.close();
					return 5;
				}
			}
			in.close();
		}
		catch (IOException e)
		{
			System.out.println("Critical error while checking Banned Users!");
			e.printStackTrace();
		}
		return 0;
	}

	private int isHostBanned()
	{
		try
		{
			BufferedReader in = new BufferedReader(new FileReader(
					"./data/bannedips.txt"));
			String data = null;
			while ((data = in.readLine()) != null)
			{
				if (connectedFrom.equalsIgnoreCase(data))
				{
					in.close();
					return 5;
				}
			}
			in.close();
		}
		catch (IOException e)
		{
			System.out.println("Critical error while checking Banned IPS!");
			e.printStackTrace();
		}
		return 0;
	}

	private int isMember()
	{
		try
		{
			BufferedReader in = new BufferedReader(new FileReader(
					"./data/members.txt"));
			String data = null;
			while ((data = in.readLine()) != null)
			{
				if (username.equalsIgnoreCase(data))
				{
					in.close();
					return 5;
				}
			}
			in.close();
		}
		catch (IOException e)
		{
			System.out.println("Critical error while Checking Members!");
			e.printStackTrace();
		}
		return 0;
	}

}