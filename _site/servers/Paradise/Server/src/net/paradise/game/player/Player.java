package net.paradise.game.player;

import java.util.Calendar;
import java.util.GregorianCalendar;

import net.paradise.game.ObjectHandler;
import net.paradise.game.item.Item4;
import net.paradise.game.item.ItemHandler;
import net.paradise.game.npc.NPC;
import net.paradise.game.npc.NPCHandler;
import net.paradise.net.StreamBuffer;
import net.paradise.util.MethodCollection;

public abstract class Player
{

	public int keepItem = 0;
	public int keepItem2 = 0;
	public int keepItem3 = 0;
	public int keepItemAmount = 0;
	public int keepItemAmount2 = 0;
	public int keepItemAmount3 = 0;
	public int DDStimer = 0;
	public int SpecialDelay = 0;
	public boolean DDS2Damg = false;
	public int Doubletimer = 0;
	public int SpecialDelay2 = 0;
	public int SpecDelay2 = 0;
	public boolean DoubleDamg = false;
	public boolean secondTradeWindow = false;
	public int userID;
	public int autoKick = 0;
	public boolean getKicked = false;
	public int bankPin;
	public boolean pinEntered = false;
	public String clanName = "none", clanLeader = "nobody";
	public String[] clanMembers = new String[17];
	public int[] CCID = { 11941, 4287, 4288, 4289, 4290, 11134, 4291, 4292,
			4293, 4294, 4295, 4296, 8935, 4297, 4298, 4299, 4300 };
	public int clanRights = 0;
	public boolean newhptype = false;
	public int hptype = 0;
	public boolean poisondmg = false;
	public int combat = 0;

	public Player(int _playerId)
	{
		playerId = _playerId;
		playerRights = 0;
		for (int i = 0; i < playerItems.length; i++)
		{
			playerItems[i] = 0;
		}
		for (int i = 0; i < playerItemsN.length; i++)
		{
			playerItemsN[i] = 0;
		}
		for (int i = 0; i < playerLevel.length; i++)
		{
			if (i == 3)
			{
				playerLevel[i] = 98;
				playerXP[i] = 200;
			}
			else
			{
				playerLevel[i] = 1;
				playerXP[i] = 0;
			}
		}
		for (int i = 0; i < playerBankSize; i++)
		{
			bankItems[i] = 0;
		}
		for (int i = 0; i < playerBankSize; i++)
		{
			bankItemsN[i] = 0;
		}
		for (int i = 0; i < playerEquipment.length; i++)
		{
			playerEquipment[i] = -1;
			playerEquipmentN[i] = 0;
		}

		Calendar cal = new GregorianCalendar();
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int month = cal.get(Calendar.MONTH);
		int year = cal.get(Calendar.YEAR);
		int calc = ((year * 10000) + (month * 100) + day);
		playerLastLogin = calc;
		playerLastConnect = "";
		playerIsMember = 0;
		playerMessages = 0;

		playerLook[0] = 0;
		playerLook[1] = 0;
		playerLook[2] = 1;
		playerLook[3] = 2;
		playerLook[4] = 0;
		playerLook[5] = 0;

		playerEquipment[playerHat] = 6858;
		playerEquipment[playerCape] = 14095;
		playerEquipment[playerAmulet] = 6585;
		playerEquipment[playerChest] = 14538;
		playerEquipment[playerShield] = 6889;
		playerEquipment[playerLegs] = 2497;
		playerEquipment[playerHands] = 7462;
		playerEquipment[playerFeet] = 3105;
		playerEquipment[playerRing] = 6735;
		playerEquipment[playerArrows] = -1;
		playerEquipment[playerWeapon] = 4587;

		/*
		 * 0-9: male head 10-17: male beard 18-25: male torso 26-32: male arms
		 * 33-35: male hands 36-41: male legs 42-44: male feet
		 * 
		 * 45-55: fem head 56-60: fem torso 61-66: fem arms 67-69: fem hands
		 * 70-78: fem legs 79-81: fem feet
		 */

		pHead = 4;
		pTorso = 25;
		pArms = 29;
		pHands = 35;
		pLegs = 39;
		pFeet = 44;
		pBeard = -1;

		heightLevel = 0;
		teleportToX = 2907;
		teleportToY = 9714;

		absX = absY = -1;
		mapRegionX = mapRegionY = -1;
		currentX = currentY = 0;
		resetWalkingQueue();
	}

	void destruct()
	{
		playerListSize = 0;
		for (int i = 0; i < maxPlayerListSize; i++)
		{
			playerList[i] = null;
		}
		npcListSize = 0;
		for (int i = 0; i < maxNPCListSize; i++)
		{
			npcList[i] = null;
		}
		absX = absY = -1;
		mapRegionX = mapRegionY = -1;
		currentX = currentY = 0;
		resetWalkingQueue();
	}

	public int FocusPointX = -1, FocusPointY = -1;

	private void appendSetFocusDestination(StreamBuffer str)
	{
		str.putWordBigEndianA(FocusPointX);
		str.putShortBE(FocusPointY);
	}

	public void TurnPlayerTo(int pointX, int pointY)
	{
		FocusPointX = 2 * pointX + 1;
		FocusPointY = 2 * pointY + 1;
	}

	public int pkilledinpit;
	public boolean inwildy = false;
	public boolean inwildy2 = false;
	public static int brocount;
	public boolean isNpc;
	public int npcId;
	public boolean initialized = false, disconnected = false, savefile = true;
	public boolean isActive = false;
	public boolean isKicked = false;
	public boolean CrackerMsg = false;
	public boolean CrackerForMe = false;
	public boolean IsGhost = false;
	public int actionTimer = 0;
	public int actionAmount = 0;
	public String actionName = "";
	public int theifTimer = 0;
	public int TakeMiscTimer = 0;
	public String connectedFrom = "";
	public String globalMessage = "";
	public static int killcount = 0;
	public int deathcount = 0;
	public int pkpoints = 0;
	public String lastKill = "";
	public int AttackingOn = 0;
	public int OptionObject = -1;
	public boolean Climbing = false;
	public int ClimbStage = -1;
	public int hiddenPoints; // number of places found ;)
	public int foundz[] = new int[100]; // used for secret places being found ;)
	public int[] clueItems = new int[28];
	public int[] clueItemsN = new int[28];
	public int ActionType = -1;
	public int destinationX = -1;
	public int destinationY = -1;
	public int destinationID = -1;
	public int destinationRange = 1;
	public boolean WalkingTo = false;
	public int TreeHP = 20;
	public int TreeX = 0;
	public int TreeY = 0;
	public int TreeTimer = 0;
	public int WCTimer = 0;
	public int logID = 0;
	public int logAmount = 0;
	public int WCxp = 0;
	public int playerAxe = -1;
	public boolean IsWcing = false;
	public boolean[] IsFireShowed = new boolean[ObjectHandler.MaxObjects];
	public boolean[] FireDelete = new boolean[ObjectHandler.MaxObjects];
	public boolean IsFireing = false;
	public boolean IsMakingFire = false;
	public int duelWith = 0;
	public int duelStatus = -1; // 0 = Requesting duel, 1 = in duel screen, 2 =
								// waiting for other player to accept, 3 = in
								// duel, 4 = won
	public int duelChatStage = -1;
	public int duelChatTimer = -1;
	public int duelItems[] = new int[28];
	public int duelItemsN[] = new int[28];
	public int otherDuelItems[] = new int[28];
	public int otherDuelItemsN[] = new int[28];
	public boolean duelRule[] = new boolean[28];
	public boolean winDuel = false;
	public boolean startDuel = false;
	public int tradeRequest = 0;
	public int tradeDecline = 0;
	public int tradeWith = 0;
	public int tradeWaitingTime = 0;
	public int tradeStatus = 0;
	public boolean tradeUpdateOther = false;
	public boolean tradeOtherDeclined = false;
	public int[] playerTItems = new int[28]; // player Trade Items
	public int[] playerTItemsN = new int[28];
	public int[] playerOTItems = new int[28]; // player Other Trade Items
	public int[] playerOTItemsN = new int[28];
	// Quest1 stuff @@@@@@@@@@@@@@@@@@@@@@@@@@@
	// public int Guard = 0;
	// public boolean Killedqueen = false;
	// end of quest1 stuff @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
	public boolean takeAsNote = false;

	public abstract void initialize();

	public abstract void update();

	public int playerId = -1; // -1 denotes world is full, otherwise this is the
								// playerId
	// corresponds to the index in Player players[]
	public String username = null; // name of the connecting client
	public String password = null; // name of the connecting client
	public boolean isRunning2 = true;
	public boolean stoprunning = false;
	public int playerRights; // 0=normal player, 1=player mod, 2=real mod,
								// 3=admin?
	public PlayerHandler handler = null;
	public int maxItemAmount = /* 999999999 */2147000000;
	public int[] playerItems = new int[28];
	public int[] playerItemsN = new int[28];
	public int playerBankSize = 350;
	public int[] bankItems = new int[800];
	public int[] bankItemsN = new int[800];
	public boolean bankNotes = false;
	// Default appearance
	public int pHead;
	public int pTorso;
	public int pArms;
	public int pHands;
	public int pLegs;
	public int pFeet;
	public int pBeard;
	public int pEmote = 0x328; // this being the original standing state
	public int pWalk = 0x333; // original walking animation
	public boolean apset = false;
	public int headIcon = 0;
	public int[] playerEquipment = new int[14];
	public int[] playerEquipmentN = new int[14];
	public static int playerHat = 0;
	public static int playerCape = 1;
	public static int playerAmulet = 2;
	public static int playerWeapon = 3;
	public static int playerChest = 4;
	public static int playerShield = 5;
	public static int playerLegs = 7;
	public static int playerHands = 9;
	public static int playerFeet = 10;
	public static int playerRing = 12;
	public static int playerArrows = 13;

	public static int ATTACK = 0;
	public static int DEFENCE = 1;
	public static int STRENGTH = 2;
	public static int HITPOINTS = 3;
	public static int RANGED = 4;
	public static int PRAYER = 5;
	public static int MAGIC = 6;
	public static int COOKING = 7;
	public static int WOODCUTTING = 8;
	public static int FLETCHING = 9;
	public static int FISHING = 10;
	public static int FIREMAKING = 11;
	public static int CRAFTING = 12;
	public static int SMITHING = 13;
	public static int MINING = 14;
	public static int HERBLORE = 15;
	public static int AGILITY = 16;
	public static int THIEVING = 17;
	public static int SLAYER = 18;
	public static int FARMING = 19;
	public static int RUNECRAFTING = 20;

	public int i = 0;
	public int[] playerLevel = new int[25];
	public int[] playerXP = new int[25];
	public int currentHealth = playerLevel[HITPOINTS];
	public int maxHealth = playerLevel[HITPOINTS];
	public int summonLevel = 1;
	public int summonXP = 0;
	public int summonedNPCS = 0;
	public final static int maxPlayerListSize = PlayerHandler.maxPlayers;
	public Player playerList[] = new Player[maxPlayerListSize];
	public int playerListSize = 0;
	public byte playerInListBitmap[] = new byte[(PlayerHandler.maxPlayers + 7) >> 3];
	public static final int maxNPCListSize = NPCHandler.maxNPCs;
	public NPC npcList[] = new NPC[maxNPCListSize];
	public int npcListSize = 0;
	public byte npcInListBitmap[] = new byte[(NPCHandler.maxNPCs + 7) >> 3];

	public boolean withinDistance(Player player)
	{
		if (heightLevel != player.heightLevel)
		{
			return false;
		}
		int deltaX = player.absX - absX, deltaY = player.absY - absY;
		return deltaX <= 15 && deltaX >= -16 && deltaY <= 15 && deltaY >= -16;
	}

	public boolean withinDistance(NPC npc)
	{
		if (heightLevel != npc.heightLevel)
		{
			return false;
		}
		if (npc.NeedRespawn == true)
		{
			return false;
		}
		int deltaX = npc.absX - absX, deltaY = npc.absY - absY;
		return deltaX <= 15 && deltaX >= -16 && deltaY <= 15 && deltaY >= -16;
	}

	public int mapRegionX;
	public int mapRegionY;
	public int absX;
	public int absY;
	public int currentX, currentY;
	public int heightLevel;
	public boolean updateRequired = true;
	public static final int walkingQueueSize = 50;
	public int walkingQueueX[] = new int[walkingQueueSize],
			walkingQueueY[] = new int[walkingQueueSize];
	public int wQueueReadPtr = 0;
	public int wQueueWritePtr = 0;
	public boolean isRunning = true;
	public int teleportToX = -1, teleportToY = -1;

	public void resetWalkingQueue()
	{
		wQueueReadPtr = wQueueWritePtr = 0;
		for (int i = 0; i < walkingQueueSize; i++)
		{
			walkingQueueX[i] = currentX;
			walkingQueueY[i] = currentY;
		}
	}

	public void addToWalkingQueue(int x, int y)
	{
		int next = (wQueueWritePtr + 1) % walkingQueueSize;
		if (next == wQueueWritePtr)
		{
			return;
		}
		walkingQueueX[wQueueWritePtr] = x;
		walkingQueueY[wQueueWritePtr] = y;
		wQueueWritePtr = next;
	}

	/*
	 * public void getNextPlayerMovement() { mapRegionDidChange = false;
	 * didTeleport = false; dir1 = dir2 = -1;
	 * 
	 * if (teleportToX != -1 && teleportToY != -1) { mapRegionDidChange = true;
	 * if (mapRegionX != -1 && mapRegionY != -1) { // check, whether destination
	 * is within current map region int relX = teleportToX - mapRegionX * 8,
	 * relY = teleportToY - mapRegionY * 8;
	 * 
	 * if (relX >= 2 * 8 && relX < 11 * 8 && relY >= 2 * 8 && relY < 11 * 8) {
	 * mapRegionDidChange = false; } } if (mapRegionDidChange) { // after map
	 * region change the relative coordinates range between 48 - 55 mapRegionX =
	 * (teleportToX >> 3) - 6; mapRegionY = (teleportToY >> 3) - 6;
	 * 
	 * // playerListSize = 0; // completely rebuild playerList after teleport
	 * AND map region change }
	 * 
	 * currentX = teleportToX - 8 * mapRegionX; currentY = teleportToY - 8 *
	 * mapRegionY; absX = teleportToX; absY = teleportToY; resetWalkingQueue();
	 * 
	 * teleportToX = teleportToY = -1; didTeleport = true; } else { dir1 =
	 * getNextWalkingDirection(); if (dir1 == -1) { return; } // standing
	 * 
	 * if (isRunning) { dir2 = getNextWalkingDirection(); }
	 * 
	 * // check, if we're required to change the map region int deltaX = 0,
	 * deltaY = 0;
	 * 
	 * if (currentX < 2 * 8) { deltaX = 4 * 8; mapRegionX -= 4;
	 * mapRegionDidChange = true; } else if (currentX >= 11 * 8) { deltaX = -4 *
	 * 8; mapRegionX += 4; mapRegionDidChange = true; } if (currentY < 2 * 8) {
	 * deltaY = 4 * 8; mapRegionY -= 4; mapRegionDidChange = true; } else if
	 * (currentY >= 11 * 8) { deltaY = -4 * 8; mapRegionY += 4;
	 * mapRegionDidChange = true; }
	 * 
	 * if (mapRegionDidChange) { // have to adjust all relative coordinates
	 * currentX += deltaX; currentY += deltaY; for (int i = 0; i <
	 * walkingQueueSize; i++) { walkingQueueX[i] += deltaX; walkingQueueY[i] +=
	 * deltaY; } }
	 * 
	 * } }
	 */

	public boolean didTeleport = false;
	public boolean mapRegionDidChange = false;
	public int dir1 = -1, dir2 = -1;
	public int poimiX = 0, poimiY = 0;

	public int getNextWalkingDirection()
	{
		if (wQueueReadPtr == wQueueWritePtr)
		{
			return -1;
		}
		// walking queue empty
		int dir;

		do
		{
			dir = MethodCollection.direction(currentX, currentY,
					walkingQueueX[wQueueReadPtr], walkingQueueY[wQueueReadPtr]);
			if (dir == -1)
			{
				wQueueReadPtr = (wQueueReadPtr + 1) % walkingQueueSize;
			}
			else if ((dir & 1) != 0)
			{
				resetWalkingQueue();
				return -1;
			}
		} while (dir == -1 && wQueueReadPtr != wQueueWritePtr);
		if (dir == -1)
		{
			return -1;
		}
		dir >>= 1;
		currentX += MethodCollection.directionDeltaX[dir];
		currentY += MethodCollection.directionDeltaY[dir];
		absX += MethodCollection.directionDeltaX[dir];
		absY += MethodCollection.directionDeltaY[dir];
		return dir;
	}

	public void getNextPlayerMovement()
	{
		mapRegionDidChange = false;
		didTeleport = false;
		dir1 = dir2 = -1;

		if (teleportToX != -1 && teleportToY != -1)
		{
			mapRegionDidChange = true;
			if (mapRegionX != -1 && mapRegionY != -1)
			{
				// check, whether destination is within current map region
				int relX = teleportToX - mapRegionX * 8, relY = teleportToY
						- mapRegionY * 8;

				if (relX >= 2 * 8 && relX < 11 * 8 && relY >= 2 * 8
						&& relY < 11 * 8)
				{
					mapRegionDidChange = false;
				}
			}
			if (mapRegionDidChange)
			{
				// after map region change the relative coordinates range
				// between 48 - 55
				mapRegionX = (teleportToX >> 3) - 6;
				mapRegionY = (teleportToY >> 3) - 6;

				// playerListSize = 0; // completely rebuild playerList after
				// teleport AND map region change
			}

			currentX = teleportToX - 8 * mapRegionX;
			currentY = teleportToY - 8 * mapRegionY;
			absX = teleportToX;
			absY = teleportToY;
			resetWalkingQueue();

			teleportToX = teleportToY = -1;
			didTeleport = true;
		}
		else
		{
			dir1 = getNextWalkingDirection();
			if (dir1 == -1)
			{
				return;
			} // standing

			if (isRunning)
			{
				dir2 = getNextWalkingDirection();
			}

			// check, if we're required to change the map region
			int deltaX = 0, deltaY = 0;

			if (currentX < 2 * 8)
			{
				deltaX = 4 * 8;
				mapRegionX -= 4;
				mapRegionDidChange = true;
			}
			else if (currentX >= 11 * 8)
			{
				deltaX = -4 * 8;
				mapRegionX += 4;
				mapRegionDidChange = true;
			}
			if (currentY < 2 * 8)
			{
				deltaY = 4 * 8;
				mapRegionY -= 4;
				mapRegionDidChange = true;
			}
			else if (currentY >= 11 * 8)
			{
				deltaY = -4 * 8;
				mapRegionY += 4;
				mapRegionDidChange = true;
			}

			if (mapRegionDidChange)
			{
				// have to adjust all relative coordinates
				currentX += deltaX;
				currentY += deltaY;
				for (int i = 0; i < walkingQueueSize; i++)
				{
					walkingQueueX[i] += deltaX;
					walkingQueueY[i] += deltaY;
				}
			}

		}
	}

	public void updateThisPlayerMovement(StreamBuffer stream)
	{
		if (mapRegionDidChange)
		{
			stream.createFrame(73);
			stream.putWordA(mapRegionX + 6);
			stream.putShort(mapRegionY + 6);
		}
		if (didTeleport == true)
		{
			stream.createFrameVarSizeWord(81);
			stream.initBitAccess();
			stream.writeBits(1, 1);
			stream.writeBits(2, 3); // updateType
			stream.writeBits(2, heightLevel);
			stream.writeBits(1, 1);
			stream.writeBits(1, (updateRequired) ? 1 : 0);
			stream.writeBits(7, currentY);
			stream.writeBits(7, currentX);
			if (IsDead == true && IsDeadTimer == true
					&& IsDeadTeleporting == true)
			{
				IsDead = false;
				IsDeadTimer = false;
				SafeMyLife = false;
				IsUsingSkill = false;
			}
			return;
		}
		if (dir1 == -1)
		{
			// don't have to update the character position, because we're just
			// standing
			stream.createFrameVarSizeWord(81);
			stream.initBitAccess();
			if (updateRequired)
			{
				// tell client there's an update block appended at the end
				stream.writeBits(1, 1);
				stream.writeBits(2, 0);
			}
			else
			{
				stream.writeBits(1, 0);
			}
			if (DirectionCount < 50)
			{
				DirectionCount++;
			}
		}
		else
		{
			DirectionCount = 0;
			stream.createFrameVarSizeWord(81);
			stream.initBitAccess();
			stream.writeBits(1, 1);
			if (dir2 == -1)
			{
				// send "walking packet"
				stream.writeBits(2, 1); // updateType
				stream.writeBits(3,
						MethodCollection.xlateDirectionToClient[dir1]);
				if (updateRequired)
				{
					stream.writeBits(1, 1);
				} // tell client there's an update block appended at the end
				else
				{
					stream.writeBits(1, 0);
				}
			}
			else
			{
				// send "running packet"
				stream.writeBits(2, 2); // updateType
				stream.writeBits(3,
						MethodCollection.xlateDirectionToClient[dir1]);
				stream.writeBits(3,
						MethodCollection.xlateDirectionToClient[dir2]);
				if (updateRequired)
				{
					stream.writeBits(1, 1);
				} // tell client there's an update block appended at the end
				else
				{
					stream.writeBits(1, 0);
				}
			}
		}
	}

	public void updatePlayerMovement(StreamBuffer str)
	{
		if (dir1 == -1)
		{
			if (updateRequired || chatTextUpdateRequired)
			{
				str.writeBits(1, 1);
				str.writeBits(2, 0);
			}
			else
			{
				str.writeBits(1, 0);
			}
		}
		else if (dir2 == -1)
		{
			// send "walking packet"
			str.writeBits(1, 1);
			str.writeBits(2, 1);
			str.writeBits(3, MethodCollection.xlateDirectionToClient[dir1]);
			str.writeBits(1, (updateRequired || chatTextUpdateRequired) ? 1 : 0);
		}
		else
		{
			// send "running packet"
			str.writeBits(1, 1);
			str.writeBits(2, 2);
			str.writeBits(3, MethodCollection.xlateDirectionToClient[dir1]);
			str.writeBits(3, MethodCollection.xlateDirectionToClient[dir2]);
			str.writeBits(1, (updateRequired || chatTextUpdateRequired) ? 1 : 0);
		}
	}

	public boolean dropsitem = false;

	public void removeequipped()
	{
		dropsitem = true;
	}

	public void setPlrAnimation(int i)
	{
		pEmote = i;
		updateRequired = true;
		appearanceUpdateRequired = true;
	}

	public int GetPlrBlockAnim(int id)
	{
		if (id == 4755) // veracs flail
		{
			return 2063;
		}
		if (id == 4153) // maul
		{
			return 1666;
		}
		else
		{
			return 1834;
		}
	}

	public byte cachedPropertiesBitmap[] = new byte[(PlayerHandler.maxPlayers + 7) >> 3];

	public void addNewNPC(NPC npc, StreamBuffer str, StreamBuffer updateBlock)
	{
		int id = npc.index;
		npcInListBitmap[id >> 3] |= 1 << (id & 7); // set the flag
		npcList[npcListSize++] = npc;
		str.writeBits(14, id); // client doesn't seem to like id=0
		int z = npc.absY - absY;
		if (z < 0)
		{
			z += 32;
		}
		str.writeBits(5, z); // y coordinate relative to thisPlayer
		z = npc.absX - absX;
		if (z < 0)
		{
			z += 32;
		}
		str.writeBits(5, z); // x coordinate relative to thisPlayer
		str.writeBits(1, 0); // something??
		str.writeBits(12, npc.id);
		boolean savedUpdateRequired = npc.updateRequired;
		npc.updateRequired = true;
		npc.appendNPCUpdateBlock(updateBlock);
		npc.updateRequired = savedUpdateRequired;
		str.writeBits(1, 1); // update required
	}

	public void addNewPlayer(Player plr, StreamBuffer str,
			StreamBuffer updateBlock)
	{
		int id = plr.playerId;
		playerInListBitmap[id >> 3] |= 1 << (id & 7);
		playerList[playerListSize++] = plr;
		str.writeBits(11, id);
		str.writeBits(1, 1);
		boolean savedFlag = plr.appearanceUpdateRequired;
		boolean savedUpdateRequired = plr.updateRequired;
		plr.appearanceUpdateRequired = true;
		plr.updateRequired = true;
		plr.appendPlayerUpdateBlock(updateBlock);
		plr.appearanceUpdateRequired = savedFlag;
		plr.updateRequired = savedUpdateRequired;
		str.writeBits(1, 1);
		int z = plr.absY - absY;
		if (z < 0)
		{
			z += 32;
		}
		str.writeBits(5, z);
		z = plr.absX - absX;
		if (z < 0)
		{
			z += 32;
		}
		str.writeBits(5, z);
	}

	public boolean appearanceUpdateRequired = true;
	protected static StreamBuffer playerProps;
	static
	{
		playerProps = new StreamBuffer(new byte[100]);
	}

	protected void appendPlayerAppearance(StreamBuffer str)
	{
		playerProps.offset = 0;
		playerProps.put(playerLook[0]);
		playerProps.put(headIcon);
		if (isNpc == false)
		{
			if (playerEquipment[playerHat] > 1)
			{
				playerProps.putShort(0x200 + playerEquipment[playerHat]);
			}
			else
			{
				playerProps.put(0);
			}
			if (playerEquipment[playerCape] > 1)
			{
				playerProps.putShort(0x200 + playerEquipment[playerCape]);
			}
			else
			{
				playerProps.put(0);
			}
			if (playerEquipment[playerAmulet] > 1)
			{
				playerProps.putShort(0x200 + playerEquipment[playerAmulet]);
			}
			else
			{
				playerProps.put(0);
			}
			if (playerEquipment[playerWeapon] > 1 && !IsWcing)
			{
				playerProps.putShort(0x200 + playerEquipment[playerWeapon]);
			}
			else if (playerAxe > 0 && IsWcing)
			{
				playerProps.putShort(0x200 + playerAxe);
			}
			else
			{
				playerProps.put(0);
			}
			if (playerEquipment[playerChest] > 1)
			{
				playerProps.putShort(0x200 + playerEquipment[playerChest]);
			}
			else
			{
				playerProps.putShort(0x100 + pTorso);
			}
			if (playerEquipment[playerShield] > 1 && !IsWcing)
			{
				playerProps.putShort(0x200 + playerEquipment[playerShield]);
			}
			else
			{
				playerProps.put(0);
			}
			if (!Item4.isPlate(playerEquipment[playerChest]))
			{
				playerProps.putShort(0x100 + pArms);
			}
			else
			{
				playerProps.put(0);
			}
			if (playerEquipment[playerLegs] > 1)
			{
				playerProps.putShort(0x200 + playerEquipment[playerLegs]);
			}
			else
			{
				playerProps.putShort(0x100 + pLegs);
			}
			if (!Item4.isFullHelm(playerEquipment[playerHat])
					&& !Item4.isFullMask(playerEquipment[playerHat]))
			{
				playerProps.putShort(0x100 + pHead); // head
			}
			else
			{
				playerProps.put(0);
			}
			if (playerEquipment[playerHands] > 1)
			{
				playerProps.putShort(0x200 + playerEquipment[playerHands]);
			}
			else
			{
				playerProps.putShort(0x100 + pHands);
			}
			if (playerEquipment[playerFeet] > 1)
			{
				playerProps.putShort(0x200 + playerEquipment[playerFeet]);
			}
			else
			{
				playerProps.putShort(0x100 + pFeet);
			}
			playerProps.put(0);
		}
		else
		{
			playerProps.putShort(-1);
			playerProps.putShort(npcId);
		}
		// array of 5 bytes defining the colors
		playerProps.put(playerLook[1]); // hair color
		playerProps.put(playerLook[2]); // torso color.
		playerProps.put(playerLook[3]); // leg color
		playerProps.put(playerLook[4]); // feet color
		playerProps.put(playerLook[5]); // skin color (0-6)
		playerProps.putShort(pEmote); // standAnimIndex
		playerProps.putShort(0x337); // standTurnAnimIndex
		playerProps.putShort(playerSEW); // walkAnimIndex
		playerProps.putShort(0x334); // turn180AnimIndex
		playerProps.putShort(0x335); // turn90CWAnimIndex
		playerProps.putShort(0x336); // turn90CCWAnimIndex
		playerProps.putShort(playerSER); // runAnimIndex
		playerProps.putLong(MethodCollection.playerNameToInt64(username));
		// Stat fix, combat decreases when your hp or any of these skills get
		// lowerd, this fixes that problem.
		/*
		 * int att = (int)((double)(getLevelForXP(playerXP[0])) * 0.325); int
		 * def = (int)((double)(getLevelForXP(playerXP[1])) * 0.25); int str =
		 * (int)((double)(getLevelForXP(playerXP[2])) * 0.325); int hit =
		 * (int)((double)(getLevelForXP(playerXP[3])) * 0.25); int mag =
		 * (int)((double)(getLevelForXP(playerXP[4])) * 0.4875); int pra =
		 * (int)((double)(getLevelForXP(playerXP[5])) * 0.125); int ran =
		 * (int)((double)(getLevelForXP(playerXP[6])) * 0.4875);
		 */
		/*
		 * int mag = (int)((double)(getLevelForXP(playerXP[4])) * 1.5); int ran
		 * = (int)((double)(getLevelForXP(playerXP[6])) * 1.5); int attstr =
		 * (int)((double)(getLevelForXP(playerXP[0])) +
		 * (double)(getLevelForXP(playerXP[2])));
		 * 
		 * int combatLevel = 0; if (ran > attstr) { combatLevel =
		 * (int)(((double)(getLevelForXP(playerXP[1])) * 0.25) +
		 * ((double)(getLevelForXP(playerXP[3])) * 0.25) +
		 * ((double)(getLevelForXP(playerXP[5])) * 0.125) +
		 * ((double)(getLevelForXP(playerXP[6])) * 0.4875)); } else if (mag >
		 * attstr) { combatLevel = (int)(((double)(getLevelForXP(playerXP[1])) *
		 * 0.25) + ((double)(getLevelForXP(playerXP[3])) * 0.25) +
		 * ((double)(getLevelForXP(playerXP[5])) * 0.125) +
		 * ((double)(getLevelForXP(playerXP[4])) * 0.4875)); } else {
		 * combatLevel = (int)(((double)(getLevelForXP(playerXP[1])) * 0.25) +
		 * ((double)(getLevelForXP(playerXP[3])) * 0.25) +
		 * ((double)(getLevelForXP(playerXP[5])) * 0.125) +
		 * ((double)(getLevelForXP(playerXP[0])) * 0.325) +
		 * ((double)(getLevelForXP(playerXP[2])) * 0.325)); }
		 * playerProps.writeByte(combatLevel); // combat level
		 * playerProps.writeWord(0); // incase != 0, writes skill-%d
		 * 
		 * str.writeByteC(playerProps.currentOffset); // size of player
		 * appearance block str.writeBytes(playerProps.buffer,
		 * playerProps.currentOffset, 0); }
		 */
		int mag = (int) ((double) (getLevelForXP(playerXP[4])) * 1.5);
		int ran = (int) ((double) (getLevelForXP(playerXP[6])) * 1.5);
		int attstr = (int) ((double) (getLevelForXP(playerXP[0])) + (double) (getLevelForXP(playerXP[2])));
		int combatLevel = 0;
		if (ran > attstr)
		{
			combatLevel = (int) (((double) (getLevelForXP(playerXP[1])) * 0.25)
					+ ((double) (getLevelForXP(playerXP[3])) * 0.25)
					+ ((double) (getLevelForXP(playerXP[5])) * 0.125) + ((double) (getLevelForXP(playerXP[6])) * 0.4875));
		}
		else if (mag > attstr)
		{
			combatLevel = (int) (((double) (getLevelForXP(playerXP[1])) * 0.25)
					+ ((double) (getLevelForXP(playerXP[3])) * 0.25)
					+ ((double) (getLevelForXP(playerXP[5])) * 0.125) + ((double) (getLevelForXP(playerXP[4])) * 0.4875));
		}
		else
		{
			combatLevel = (int) (((double) (getLevelForXP(playerXP[1])) * 0.25)
					+ ((double) (getLevelForXP(playerXP[3])) * 0.25)
					+ ((double) (getLevelForXP(playerXP[5])) * 0.125)
					+ ((double) (getLevelForXP(playerXP[0])) * 0.325) + ((double) (getLevelForXP(playerXP[2])) * 0.325));
		}
		combat = combatLevel;
		playerProps.put(combatLevel); // combat level
		playerProps.putShort(0); // incase != 0, writes skill-%d
		str.putByteC(playerProps.offset); // size of player appearance
											// block
		str.put(playerProps.buffer, playerProps.offset, 0);
	}

	public boolean chatTextUpdateRequired = false;
	public byte chatText[] = new byte[4096];
	public byte chatTextSize = 0;
	public int chatTextEffects = 0;
	public int chatTextColor = 0;

	protected void appendPlayerChatText(StreamBuffer str)
	{
		str.putShortBE(((chatTextColor & 0xFF) << 8) + (chatTextEffects & 0xFF));
		str.put(playerRights);
		str.putByteC(chatTextSize); // no more than 256 bytes!!!
		str.writeBytes_reverse(chatText, chatTextSize, 0);
	}

	public boolean update1Required = false;
	public int update1_1 = 0;
	public int update1_2 = 0;

	protected void appendUpdate1(StreamBuffer str)
	{
		str.putShortBE(update1_1);
		str.putByteC(update1_2);
	}

	public void appendPlayerUpdateBlock(StreamBuffer str)
	{
		if (!updateRequired && !chatTextUpdateRequired)
		{
			return;
		} // nothing required
		int updateMask = 0;
		if (mask400update)
		{
			updateMask |= 0x400;
		} // Xerozcheez: Very interesting update mask!
		if (mask100update)
		{
			updateMask |= 0x100;
		} // Xerozcheez: Graphics on player update mask
		if (animationRequest != -1)
		{
			updateMask |= 8;
		}
		if (string4UpdateRequired)
		{
			updateMask |= 4;
		}
		if (chatTextUpdateRequired)
		{
			updateMask |= 0x80;
		}
		if (faceNPCupdate)
		{
			updateMask |= 1;
		}
		if (appearanceUpdateRequired)
		{
			updateMask |= 0x10;
		}
		if (FocusPointX != -1)
		{
			updateMask |= 2;
		}
		if (hitUpdateRequired)
		{
			updateMask |= 0x200;
		}
		if (dirUpdateRequired)
		{
			updateMask |= 0x40;
		}
		if (dirUpdate2Required)
		{
			updateMask |= 2;
		}
		if (animationRequest != -1)
		{
			updateMask |= 8;
		}
		if (updateMask >= 0x100)
		{
			// byte isn't sufficient
			updateMask |= 0x40; // indication for the client that updateMask is
								// stored in a word
			str.put(updateMask & 0xFF);
			str.put(updateMask >> 8);
		}
		else
		{
			str.put(updateMask);
		}
		// now writing the various update blocks itself - note that their order
		// crucial
		if (mask400update)
		{
			appendMask400Update(str);
		} // Xerozcheez: Very interesting update mask!
		if (mask100update)
		{
			appendMask100Update(str);
		} // Xerozcheez: Graphics on player update mask
		if (animationRequest != -1)
		{
			appendAnimationRequest(str);
		}
		if (string4UpdateRequired)
		{
			appendString4(str);
		}
		if (chatTextUpdateRequired)
		{
			appendPlayerChatText(str);
		}
		if (faceNPCupdate)
		{
			appendFaceNPCUpdate(str);
		}
		if (appearanceUpdateRequired)
		{
			appendPlayerAppearance(str);
		}
		if (FocusPointX != -1)
		{
			appendSetFocusDestination(str);
		}
		if (hitUpdateRequired)
		{
			appendHitUpdate(str);
		}
		if (dirUpdateRequired)
		{
			appendDirUpdate(str);
		}
		if (dirUpdate2Required)
		{
			appendDirUpdate2(str);
		}
	}

	public void clearUpdateFlags()
	{
		FocusPointX = FocusPointY = -1;
		updateRequired = false;
		string4UpdateRequired = false;
		chatTextUpdateRequired = false;
		appearanceUpdateRequired = false;
		hitUpdateRequired = false;
		dirUpdateRequired = false;
		animationRequest = -1;
		dirUpdate2Required = false;
		faceNPCupdate = false;
		faceNPC = 65535;
		mask100update = false;
		update1Required = false;
		IsStair = false;
	}

	protected static int newWalkCmdX[] = new int[walkingQueueSize];
	protected static int newWalkCmdY[] = new int[walkingQueueSize];
	protected static int tmpNWCX[] = new int[walkingQueueSize];
	protected static int tmpNWCY[] = new int[walkingQueueSize];
	protected static int newWalkCmdSteps = 0;
	protected static boolean newWalkCmdIsRunning = false;
	protected static int travelBackX[] = new int[walkingQueueSize];
	protected static int travelBackY[] = new int[walkingQueueSize];
	protected static int numTravelBackSteps = 0;

	public void preProcessing()
	{
		newWalkCmdSteps = 0;
	}

	// is being called regularily every 500ms - do any automatic player actions
	// herein
	public abstract void process();

	public abstract boolean packetSending();

	public boolean following = false;

	public void postProcessing()
	{
		if (newWalkCmdSteps > 0)
		{
			int OldcurrentX = currentX;
			int OldcurrentY = currentY;
			for (i = 0; i < playerFollow.length; i++)
			{
				if (playerFollow[i] != -1 && following == true)
				{
					Player.newWalkCmdSteps = (newWalkCmdSteps + 1);
					for (int j = 0; j < newWalkCmdSteps; j++)
					{
						Player.newWalkCmdX[(j + 1)] = newWalkCmdX[j];
						Player.newWalkCmdY[(j + 1)] = newWalkCmdY[j];
					}
					Player.newWalkCmdX[0] = OldcurrentX;
					Player.newWalkCmdY[0] = OldcurrentY;
					PlayerHandler.players[playerFollow[i]].poimiX = OldcurrentX;
					PlayerHandler.players[playerFollow[i]].poimiY = OldcurrentY;
				}
				// place this into walking queue
				// care must be taken and we can't just append this because
				// usually the starting point (clientside) of
				// this packet and the current position (serverside) do not
				// coincide. Therefore we might have to go
				// back in time in order to find a proper connecting vertex.
				// This is also the origin of the character
				// walking back and forth when there's noticeable lag and we
				// keep on seeding walk commands.
				int firstX = newWalkCmdX[0], firstY = newWalkCmdY[0]; // the
																		// point
																		// we
																		// need
																		// to
																		// connect
																		// to
																		// from
																		// our
																		// current
																		// position...
				// travel backwards to find a proper connection vertex
				int lastDir = 0;
				boolean found = false;
				numTravelBackSteps = 0;
				int ptr = wQueueReadPtr;
				int dir = MethodCollection.direction(currentX, currentY,
						firstX, firstY);
				if (dir != -1 && (dir & 1) != 0)
				{
					// we can't connect first and current directly
					do
					{
						lastDir = dir;
						if (--ptr < 0)
						{
							ptr = walkingQueueSize - 1;
						}
						travelBackX[numTravelBackSteps] = walkingQueueX[ptr];
						travelBackY[numTravelBackSteps++] = walkingQueueY[ptr];
						dir = MethodCollection.direction(walkingQueueX[ptr],
								walkingQueueY[ptr], firstX, firstY);
						if (lastDir != dir)
						{
							found = true;
							break; // either of those two, or a vertex between
									// those is a candidate
						}
					} while (ptr != wQueueWritePtr);
				}
				else
				{
					found = true;
				} // we didn't need to go back in time because the current
					// position
				// already can be connected to first
				if (!found)
				{
					// println_debug("Fatal: couldn't find connection vertex! Dropping packet.");
					disconnected = true;
				}
				else
				{
					wQueueWritePtr = wQueueReadPtr; // discard any yet
													// unprocessed waypoints
													// from queue
					addToWalkingQueue(currentX, currentY); // have to add this
															// in order to keep
															// consistency in
															// the queue
					if (dir != -1 && (dir & 1) != 0)
					{
						// need to place an additional waypoint which lies
						// between walkingQueue[numTravelBackSteps-2] and
						// walkingQueue[numTravelBackSteps-1] but can be
						// connected to firstX/firstY
						for (int i = 0; i < numTravelBackSteps - 1; i++)
						{
							addToWalkingQueue(travelBackX[i], travelBackY[i]);
						}
						int wayPointX2 = travelBackX[numTravelBackSteps - 1], wayPointY2 = travelBackY[numTravelBackSteps - 1];
						int wayPointX1, wayPointY1;
						if (numTravelBackSteps == 1)
						{
							wayPointX1 = currentX;
							wayPointY1 = currentY;
						}
						else
						{
							wayPointX1 = travelBackX[numTravelBackSteps - 2];
							wayPointY1 = travelBackY[numTravelBackSteps - 2];
						}
						// we're coming from wayPoint1, want to go in direction
						// wayPoint2 but only so far that
						// we get a connection to first
						// the easiest, but somewhat ugly way:
						// maybe there is a better way, but it involves shitload
						// of different
						// cases so it seems like it isn't anymore
						dir = MethodCollection.direction(wayPointX1,
								wayPointY1, wayPointX2, wayPointY2);
						if (dir == -1 || (dir & 1) != 0)
						{
							// println_debug("Fatal: The walking queue is corrupt! wp1=("
							// + wayPointX1
							// + ", "
							// + wayPointY1
							// + "), "
							// + "wp2=("
							// + wayPointX2
							// + ", "
							// + wayPointY2
							// + ")");
						}
						else
						{
							dir >>= 1;
							found = false;
							int x = wayPointX1, y = wayPointY1;
							while (x != wayPointX2 || y != wayPointY2)
							{
								x += MethodCollection.directionDeltaX[dir];
								y += MethodCollection.directionDeltaY[dir];
								if ((MethodCollection.direction(x, y, firstX,
										firstY) & 1) == 0)
								{
									found = true;
									break;
								}
							}
							if (!found)
							{
								// println_debug("Fatal: Internal error: unable to determine connection vertex!"
								// + "  wp1=("
								// + wayPointX1
								// + ", "
								// + wayPointY1
								// + "), wp2=("
								// + wayPointX2
								// + ", "
								// + wayPointY2
								// + "), "
								// + "first=("
								// + firstX + ", " + firstY + ")");
							}
							else
							{
								addToWalkingQueue(wayPointX1, wayPointY1);
							}
						}
					}
					else
					{
						for (int i = 0; i < numTravelBackSteps; i++)
						{
							addToWalkingQueue(travelBackX[i], travelBackY[i]);
						}
					}
					// now we can finally add those waypoints because we made
					// sure about the connection to first
					for (int i = 0; i < newWalkCmdSteps; i++)
					{
						addToWalkingQueue(newWalkCmdX[i], newWalkCmdY[i]);
					}
				}
				isRunning = newWalkCmdIsRunning || isRunning2;
				for (i = 0; i < playerFollow.length; i++)
				{
					if (playerFollow[i] != -1
							&& PlayerHandler.players[playerFollow[i]] != null)
					{
						PlayerHandler.players[playerFollow[i]].postProcessing();
					}
				}
			}
		}
	}

	public void kick()
	{
		isKicked = true;
	}

	public int hitDiff = 0;
	public boolean hitUpdateRequired = false;
	public boolean IsDead = false;
	protected int NewHP;
	public boolean SafeMyLife = false;
	protected boolean IsStair = false;
	protected boolean IsDeadTeleporting = false;
	protected boolean IsDeadTimer = false;

	protected void appendHitUpdate2(StreamBuffer str)
	{
		try
		{
			str.put(hitDiff); // What the perseon got 'hit' for
			if (hitDiff > 0 && newhptype == false && poisondmg == false)
			{
				str.putByteA(1); // 0: red hitting - 1: blue hitting
			}
			else if (hitDiff > 0 && poisondmg == true)
			{
				str.putByteA(0); // 0: red hitting - 1: blue hitting 2: poison
									// 3: orange
			}
			else if (hitDiff > 0 && newhptype == true)
			{
				str.putByteA(hptype); // 0: red hitting - 1: blue hitting
			}
			else
			{
				str.putByteA(0); // 0: red hitting - 1: blue hitting
			}
			NewHP = (playerLevel[HITPOINTS] - hitDiff);
			if (NewHP <= 0)
			{
				NewHP = 0;
				IsDead = true;
			}
			str.putByteC(NewHP); // Their current hp, for HP bar
			str.put(getLevelForXP(playerXP[HITPOINTS])); // Their
															// max
															// hp,
															// for
															// HP
															// bar
			poisondmg = false;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	protected void appendHitUpdate(StreamBuffer str)
	{
		try
		{
			str.put(hitDiff); // What the perseon got 'hit' for
			if (hitDiff > 0 && newhptype == false && poisondmg == false)
			{
				str.putByteS(1); // 0: red hitting - 1: blue hitting
			}
			else if (hitDiff > 0 && poisondmg == true)
			{
				str.putByteS(2); // 0: red hitting - 1: blue hitting 2: poison
									// 3: orange
			}
			else if (hitDiff > 0 && newhptype == true)
			{
				str.putByteS(hptype); // 0: red hitting - 1: blue hitting
			}
			else
			{
				str.putByteS(0); // 0: red hitting - 1: blue hitting
			}
			NewHP = (playerLevel[HITPOINTS] - hitDiff);
			if (NewHP <= 0)
			{
				NewHP = 0;
				IsDead = true;
			}
			str.put(NewHP); // Their current hp, for HP bar
			str.putByteC(getLevelForXP(playerXP[HITPOINTS])); // Their
																// max
																// hp,
																// for
																// HP
																// bar
			poisondmg = false;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public int getLevelForXP(int exp)
	{
		int points = 0;
		int output = 0;
		for (int lvl = 1; lvl <= 99; lvl++)
		{
			points += Math.floor((double) lvl + 300.0
					* Math.pow(2.0, (double) lvl / 7.0));
			output = (int) Math.floor(points / 4);
			if (output >= exp)
			{
				return lvl;
			}
		}
		return 99;
	}

	public int animationRequest = -1, animationWaitCycles = 0;
	protected boolean animationUpdateRequired = false;

	public void startAnimation(int animIdx)
	{
		animationRequest = animIdx;
		animationWaitCycles = 0;
	}

	public void appendAnimationRequest(StreamBuffer str)
	{
		str.putShortBE((animationRequest == -1) ? 65535 : animationRequest);
		str.putByteC(animationWaitCycles);
	}

	public int playerEmotionReq = -1;
	public int playerEmotionDelay = 0;

	public void appendEmotionUpdate(StreamBuffer str)
	{
		str.putShortBE((playerEmotionReq == -1) ? 65535 : playerEmotionReq);
		str.putByteC(playerEmotionDelay);
	}

	public int mask1var = 0;
	protected boolean mask1update = false;

	public void appendMask1Update(StreamBuffer str)
	{
		str.putShortBE(mask1var);
	}

	public void faceNPC(int index)
	{
		faceNPC = index;
		faceNPCupdate = true;
		updateRequired = true;
	}

	public boolean faceNPCupdate = false;
	public int faceNPC = -1;

	public void appendFaceNPCUpdate(StreamBuffer str)
	{
		str.putShortBE(faceNPC);
	}

	public int mask100var1 = 0;
	public int mask100var2 = 0;
	protected boolean mask100update = false;

	public void appendMask100Update(StreamBuffer str)
	{
		str.putShortBE(mask100var1);
		str.putInt(mask100var2);
	}

	public int m4001 = 0;
	public int m4002 = 0;
	public int m4003 = 0;
	public int m4004 = 0;
	public int m4005 = 0;
	public int m4006 = 0;
	public int m4007 = 0;
	protected boolean mask400update = false;

	public void appendMask400Update(StreamBuffer str)
	{ // Xerozcheez: Something to do
		// with direction
		str.putByteA(m4001);
		str.putByteA(m4002);
		str.putByteA(m4003);
		str.putByteA(m4004);
		str.putWordA(m4005);
		str.putWordBigEndianA(m4006);
		str.putByteA(m4007); // direction
	}

	public String txt4 = "testing update mask string";
	protected boolean string4UpdateRequired = false;

	public void appendString4(StreamBuffer str)
	{ // Xerozcheez: Interesting mask,
		// looks like to do with chat
		str.putString(txt4);
	}

	public void appendDirUpdate2(StreamBuffer str)
	{
		str.putWordBigEndianA(viewToX);
		str.putShortBE(viewToY);
	}

	public void appendDirUpdate(StreamBuffer str)
	{
		if (playerMD != -1)
		{
			/*
			 * str.writeBits(2, 1); // updateType str.writeBits(3,
			 * misc.xlateDirectionToClient[playerMD]); if(updateRequired) {
			 * str.writeBits(1, 1); // tell client there's an update block
			 * appended at the end } else { str.writeBits(1, 0); }
			 */
			str.putShort(playerMD);
			playerMD = -1;
		}
	}

	public boolean[] IsDropped = new boolean[ItemHandler.MaxDropItems];
	public boolean[] MustDelete = new boolean[ItemHandler.MaxDropItems];
	public boolean IsDropping = false;

	// PM Stuff
	public abstract boolean isinpm(long l);

	public abstract void loadpm(long l, int world);

	public abstract void pmupdate(int pmid, int world);

	public int Privatechat = 0;

	public abstract void sendpm(long name, int rights, byte[] chatmessage,
			int messagesize);

	public long friends[] = new long[200];
	public long ignores[] = new long[100];
	public boolean IsPMLoaded = false;
	public int playerIsMember;
	public int playerMessages;
	public String playerLastConnect;
	public int playerLastLogin;
	public int playerEnergy;
	public int playerEnergyGian;
	public int playerLook[] = new int[6];
	public boolean IsUsingSkill = false;
	public int playerBonus[] = new int[12];
	public int StrPotion = 0;
	public int StrPrayer = 0;
	public int FightType = 1;
	public int playerMaxHit = 0;
	public int playerSE = 0x328; // SE = Standard Emotion
	public int playerSEW = 0x333; // SEW = Standard Emotion Walking
	public int playerSER = 0x338; // SER = Standard Emotion Run
	public int playerSEA = 0x326; // SEA = Standard Emotion Attack
	public int playerMD = -1;
	public int viewToX = -1;
	public int viewToY = -1;
	protected boolean dirUpdateRequired = false;
	protected boolean dirUpdate2Required = false;
	public boolean IsCutting = false;
	public boolean WannePickUp = false;
	public boolean IsInWilderness = false;
	public boolean IsAttacking = false;
	public boolean IsMining = false;
	public boolean IsAttackingNPC = false;
	public int attacknpc = -1;
	public int Essence;
	public boolean IsShopping = false;
	public int MyShopID = 0;
	public boolean UpdateShop = false;
	public boolean RebuildNPCList = false;
	public int IsSnowing = 0;
	public int NpcDialogue = 0;
	public int NpcTalkTo = 0;
	public boolean NpcDialogueSend = false;
	public int NpcWanneTalk = 0;
	public boolean IsBanking = false;
	public int WanneTrade = 0;
	public int WanneTradeWith = 0;
	public boolean TradeConfirmed = false;
	public boolean AntiTradeScam = false;
	public int playerFollow[] = new int[PlayerHandler.maxPlayers];
	public int playerFollowID = -1;
	public int DirectionCount = 0;
	public boolean playerAncientMagics = false;
	public String playerServer;
	public int playerGameTime;
	public int playerGameCount;
	public boolean ChangeDoor[] = new boolean[ObjectHandler.MaxObjects];
}