import java.io.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

public abstract class Player
{
	public void println_debug(String str)
	{
		System.out.println("[player-"+playerId+"]: "+str);
	}
	public void println(String str)
	{
		System.out.println("[player-"+playerId+"]: "+str);
	}

	public boolean newhptype = false;

	public int hptype = 0;

	public boolean poisondmg = false;

	public int combat = 0;

	public Player(int _playerId)
	{
		playerId = _playerId;
		playerRights = 0; //player rights
		for (int i = 0; i < playerItems.length; i++)
		{
			playerItems[i] = 0;
		}
		for (int i = 0; i<playerItemsN.length; i++)
		{
			playerItemsN[i] = 0;
		}

		for (int i=0; i<playerLevel.length; i++)
		{
			if (i == 3)
			{
				playerLevel[i] = 10;
				playerXP[i] = 1155;
			}
			else
			{
				playerLevel[i] = 1;
				playerXP[i] = 0;
			}
		}
		for (int i = 0; i< playerBankSize; i++)
		{
			bankItems[i] = 0;
		}

		for (int i = 0; i < playerBankSize; i++)
		{
			bankItemsN[i] = 0;
		}
//Setting Welcomescreen Information
		Calendar cal = new GregorianCalendar();
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int month = cal.get(Calendar.MONTH);
		int year = cal.get(Calendar.YEAR);
		int calc = ((year * 10000) + (month * 100) + day);
		playerLastLogin = calc;
		playerLastConnect = "";
		playerIsMember = 1;
		playerMessages = 0;
// Setting The Standard Player Look
		playerLook[0] = 0;
		playerLook[1] = 7;
		playerLook[2] = 8;
		playerLook[3] = 9;
		playerLook[4] = 5;
		playerLook[5] = 0;
// Giving Player A Unique Look
		playerEquipment[playerHat]=Item.randomHat();
		playerEquipment[playerCape]=Item.randomCape();
		playerEquipment[playerAmulet]=Item.randomAmulet();
		playerEquipment[playerChest]=Item.randomBody();
		playerEquipment[playerShield]=Item.randomShield();
		playerEquipment[playerLegs]=Item.randomLegs();
		playerEquipment[playerHands]=Item.randomGloves();
		playerEquipment[playerFeet]=Item.randomBoots();
		playerEquipment[playerRing]=Item.randomRing();
		playerEquipment[playerArrows]=Item.randomArrows();
		playerEquipment[playerWeapon]=1275;
/*
0-9: male head
10-17: male beard
18-25: male torso
26-32: male arms
33-35: male hands
36-41: male legs
42-44: male feet

45-55: fem head
56-60: fem torso
61-66: fem arms
67-69: fem hands
70-78: fem legs
79-81: fem feet
*/
		pHead=7;
		pTorso=25;
		pArms=29;
		pHands=35;
		pLegs=39;
		pFeet=44;
		pBeard=14;
/*
pHead=3;
pTorso=25;
pArms=26;
pHands=34;
pLegs=36;
pFeet=42;
pBeard=0;
*/
// initial x and y coordinates of the player
		heightLevel = 0;
// the first call to updateThisPlayerMovement() will craft the proper initialization packet
		teleportToX = 3222;//3072;
		teleportToY = 3219;//3312;
// client initially doesn't know those values yet
		absX = absY = -1;
		mapRegionX = mapRegionY = -1;
		currentX = currentY = 0;
		resetWalkingQueue();
	}

	void destruct()
	{
		//playerListSize = 0;
		for(int i = 0; i < maxPlayerListSize; i++) playerList[i] = null;
		npcListSize = 0;
		for(int i = 0; i < maxNPCListSize; i++) npcList[i] = null;

		absX = absY = -1;
		mapRegionX = mapRegionY = -1;
		currentX = currentY = 0;
		resetWalkingQueue();
	}

	public boolean isNpc;

	public int npcId;

	public boolean initialized = false, disconnected = false, savefile = true;

	public boolean isActive = false;

	public boolean isKicked = false;

	public boolean CrackerMsg = false;

	public boolean CrackerForMe = false;

	public int actionTimer = 0;

	public int actionAmount = 0;

	public String actionName = "";

	public String connectedFrom = "";

	public String globalMessage = "";

	public int tradeRequest = 0;

	public int tradeDecline = 0;

	public int tradeWith = 0;

	public int tradeWaitingTime = 0;

	public int tradeStatus = 0;

	public boolean tradeUpdateOther = false;

	public boolean tradeOtherDeclined = false;

	public int[] playerTItems = new int[28]; //player Trade Items

	public int[] playerTItemsN = new int[28];

	public int[] playerOTItems = new int[28]; //player Other Trade Items

	public int[] playerOTItemsN = new int[28];
// Start Quest1 Stuff
	public int Guard = 0;
	public boolean Killedqueen = false;
// End Quest1 Stuff

	public boolean takeAsNote = false;
	
	public abstract void initialize();

	public abstract void update();

	public int playerId = -1;

	public String playerName = null; // Name of the connecting client

	public String playerPass = null; // Name of the connecting client

	public boolean isRunning2 = false;

	public int playerRights; // 0=normal player, 1=player mod, 2=real mod, 3=admin?

	public PlayerHandler handler = null;

	public int maxItemAmount = 2000000000;

	public int[] playerItems = new int[28];

	public int[] playerItemsN = new int[28];

	public int playerBankSize = 350;

	public int[] bankItems = new int[800];

	public int[] bankItemsN = new int[800];

	public boolean bankNotes = false;

	//Default appearance
	public int pHead;

	public int pTorso;

	public int pArms;

	public int pHands;

	public int pLegs;

	public int pFeet;

	public int pBeard;

	public int pEmote = 0x328; // this being the original standing state

	public int pWalk = 0x333; // original walking animation

	public int[] playerEquipment = new int[14];

	public int[] playerEquipmentN = new int[14];
	
	public int playerHat = 0;

	public int playerCape = 1;

	public int playerAmulet = 2;

	public int playerWeapon = 3;

	public int playerChest = 4;

	public int playerShield = 5;

	public int playerLegs = 7;

	public int playerHands = 9;

	public int playerFeet = 10;

	public int playerRing = 12;

	public int playerArrows = 13;

	public int playerAttack = 0;

	public int playerDefence = 1;

	public int playerStrength = 2;

	public int playerHitpoints = 3;

	public int playerRanged = 4;

	public int playerPrayer = 5;

	public int playerMagic = 6;

	public int playerCooking = 7;

	public int playerWoodcutting = 8;

	public int playerFletching = 9;

	public int playerFishing = 10;

	public int playerFiremaking = 11;

	public int playerCrafting = 12;

	public int playerSmithing = 13;

	public int playerMining = 14;

	public int playerHerblore = 15;

	public int playerAgility = 16;

	public int playerThieving = 17;

	public int playerSlayer = 18;

	public int playerFarming = 19;

	public int playerRunecrafting = 20;

	public int i = 0;

	public int[] playerLevel = new int[25];

	public int[] playerXP = new int[25];

	public int currentHealth = playerLevel[playerHitpoints];

	public int maxHealth = playerLevel[playerHitpoints];

	public final static int maxPlayerListSize = PlayerHandler.maxPlayers;

	public Player playerList[] = new Player[maxPlayerListSize];

	public int playerListSize = 0;

	public byte playerInListBitmap[] = new byte[(PlayerHandler.maxPlayers+7) >> 3];

	public final static int maxNPCListSize = NPCHandler.maxNPCs;

	public NPC npcList[] = new NPC[maxNPCListSize];

	public int npcListSize = 0;

	public byte npcInListBitmap[] = new byte[(NPCHandler.maxNPCs+7) >> 3];

	public boolean withinDistance(Player otherPlr)
	{
		if(heightLevel != otherPlr.heightLevel) return false;
		int deltaX = otherPlr.absX-absX, deltaY = otherPlr.absY-absY;
		return deltaX <= 15 && deltaX >= -16 && deltaY <= 15 && deltaY >= -16;
	}

	public boolean withinDistance(NPC npc)
	{
		if (heightLevel != npc.heightLevel) return false;
		if (npc.NeedRespawn == true) return false;
		int deltaX = npc.absX-absX, deltaY = npc.absY-absY;
		return deltaX <= 15 && deltaX >= -16 && deltaY <= 15 && deltaY >= -16;
	}


	public int mapRegionX, mapRegionY;

	public int absX, absY;

	public int currentX, currentY;

	public int heightLevel;

	public boolean updateRequired = true;

	public static final int walkingQueueSize = 50;

	public int walkingQueueX[] = new int[walkingQueueSize], walkingQueueY[] = new int[walkingQueueSize];

	public int wQueueReadPtr = 0;

	public int wQueueWritePtr = 0;

	public boolean isRunning = false;

	public int teleportToX = -1, teleportToY = -1;

	public void resetWalkingQueue()
	{
		wQueueReadPtr = wQueueWritePtr = 0;
		for(int i = 0; i < walkingQueueSize; i++)
		{
			walkingQueueX[i] = currentX;
			walkingQueueY[i] = currentY;
		}
	}

	public void addToWalkingQueue(int x, int y)
	{
		int next = (wQueueWritePtr+1) % walkingQueueSize;
		if(next == wQueueWritePtr) return;
		walkingQueueX[wQueueWritePtr] = x;
		walkingQueueY[wQueueWritePtr] = y;
		wQueueWritePtr = next; 
	}

	public int getNextWalkingDirection()
	{
		if(wQueueReadPtr == wQueueWritePtr) return -1;
		int dir;
		do
		{
			dir = misc.direction(currentX, currentY, walkingQueueX[wQueueReadPtr], walkingQueueY[wQueueReadPtr]);
			if(dir == -1) wQueueReadPtr = (wQueueReadPtr+1) % walkingQueueSize;
			else if((dir&1) != 0)
			{
				println_debug("Invalid waypoint in walking queue!");
				resetWalkingQueue();
				return -1;
			}
		}
		while(dir == -1 && wQueueReadPtr != wQueueWritePtr);
		if(dir == -1) return -1;
		dir >>= 1;
		currentX += misc.directionDeltaX[dir];
		currentY += misc.directionDeltaY[dir];
		absX += misc.directionDeltaX[dir];
		absY += misc.directionDeltaY[dir];
		return dir;
	}

// calculates directions of player movement, or the new coordinates when teleporting
	public boolean didTeleport = false;

	public boolean mapRegionDidChange = false;

	public int dir1 = -1, dir2 = -1;

	public int poimiX = 0, poimiY = 0;

	public void getNextPlayerMovement()
	{
		mapRegionDidChange = false;
		didTeleport = false;
		dir1 = dir2 = -1;
		if(teleportToX != -1 && teleportToY != -1)
		{
			mapRegionDidChange = true;
			if(mapRegionX != -1 && mapRegionY != -1)
			{
				// check, whether destination is within current map region
				int relX = teleportToX-mapRegionX*8, relY = teleportToY-mapRegionY*8;
				if(relX >= 2*8 && relX < 11*8 && relY >= 2*8 && relY < 11*8)
					mapRegionDidChange = false;
			}
			if(mapRegionDidChange)
			{
				mapRegionX = (teleportToX>>3)-6;
				mapRegionY = (teleportToY>>3)-6;
				playerListSize = 0;
			}
			currentX = teleportToX - 8*mapRegionX;
			currentY = teleportToY - 8*mapRegionY;
			absX = teleportToX;
			absY = teleportToY;
			resetWalkingQueue();
			teleportToX = teleportToY = -1;
			didTeleport = true;
		}
		else
		{
			dir1 = getNextWalkingDirection();
			if(dir1 == -1) return;
			if(isRunning)
			{
				dir2 = getNextWalkingDirection();
			}
			int deltaX = 0, deltaY = 0;
			if(currentX < 2*8)
			{
				deltaX = 4*8;
				mapRegionX -= 4;
				mapRegionDidChange = true;
			}
			else if(currentX >= 11*8)
			{
				deltaX = -4*8;
				mapRegionX += 4;
				mapRegionDidChange = true;
			}
			if(currentY < 2*8)
			{
				deltaY = 4*8;
				mapRegionY -= 4;
				mapRegionDidChange = true;
			}
			else if(currentY >= 11*8)
			{
				deltaY = -4*8;
				mapRegionY += 4;
				mapRegionDidChange = true;
			}
			if(mapRegionDidChange)
			{
				currentX += deltaX;
				currentY += deltaY;
				for(int i = 0; i < walkingQueueSize; i++)
				{
					walkingQueueX[i] += deltaX;
					walkingQueueY[i] += deltaY;
				}
			}

		}
	}

	public void updateThisPlayerMovement(stream str)
	{
		if(mapRegionDidChange)
		{
			str.createFrame(73);
			str.writeWordA(mapRegionX+6);
			str.writeWord(mapRegionY+6);
		}
		if(didTeleport == true)
		{
			str.createFrameVarSizeWord(81);
			str.initBitAccess();
			str.writeBits(1, 1);
			str.writeBits(2, 3);
			str.writeBits(2, heightLevel);
			str.writeBits(1, 1);
			str.writeBits(1, (updateRequired) ? 1 : 0);
			str.writeBits(7, currentY);
			str.writeBits(7, currentX);
			if (IsDead == true && IsDeadTimer == true && IsDeadTeleporting == true)
			{
				IsDead = false;
				IsDeadTimer = false;
				SafeMyLife = false;
				IsUsingSkill = false;
			}
			return ;
		}

		if(dir1 == -1)
		{
			str.createFrameVarSizeWord(81);
			str.initBitAccess();
			if(updateRequired)
			{
				str.writeBits(1, 1);
				str.writeBits(2, 0);
			}
			else
			{
				str.writeBits(1, 0);
			}
			if (DirectionCount < 50)
			{
				DirectionCount++;
			}
		}
		else
		{
			DirectionCount = 0;
			str.createFrameVarSizeWord(81);
			str.initBitAccess();
			str.writeBits(1, 1);
			if(dir2 == -1)
			{
				str.writeBits(2, 1);
				str.writeBits(3, misc.xlateDirectionToClient[dir1]);
				if(updateRequired) str.writeBits(1, 1);
				else str.writeBits(1, 0);
			}
			else
			{
				str.writeBits(2, 2);
				str.writeBits(3, misc.xlateDirectionToClient[dir1]);
				str.writeBits(3, misc.xlateDirectionToClient[dir2]);
				if(updateRequired) str.writeBits(1, 1);
				else str.writeBits(1, 0);
				if (playerEnergy > 0)
				{
					playerEnergy -= 1;
				}
				else
				{
					isRunning2 = false;
				}
			}
		}
	}

	public void updatePlayerMovement(stream str)
	{
		if(dir1 == -1)
		{
			if(updateRequired || chatTextUpdateRequired)
			{
				str.writeBits(1, 1);
				str.writeBits(2, 0);
			}
			else str.writeBits(1, 0);
		}
		else if(dir2 == -1)
		{
			str.writeBits(1, 1);
			str.writeBits(2, 1);
			str.writeBits(3, misc.xlateDirectionToClient[dir1]);
			str.writeBits(1, (updateRequired || chatTextUpdateRequired) ? 1: 0);
		}
		else
		{
			str.writeBits(1, 1);
			str.writeBits(2, 2);
			str.writeBits(3, misc.xlateDirectionToClient[dir1]);
			str.writeBits(3, misc.xlateDirectionToClient[dir2]);
			str.writeBits(1, (updateRequired || chatTextUpdateRequired) ? 1: 0);
		}
	}

	public static client client = null;

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
		if(id == 4755)
		{
			return 2063;
		}
		if(id == 4153)
		{
			return 1666;
		}
		else
		{
			return 1834;
		}
	}

	public byte cachedPropertiesBitmap[] = new byte[(PlayerHandler.maxPlayers+7) >> 3];

	public void addNewNPC(NPC npc, stream str, stream updateBlock)
	{
		int id = npc.npcId;
		npcInListBitmap[id >> 3] |= 1 << (id&7);
		npcList[npcListSize++] = npc;
		str.writeBits(14, id);
		int z = npc.absY-absY;
		if(z < 0) z += 32;
		str.writeBits(5, z);
		z = npc.absX-absX;
		if(z < 0) z += 32;
		str.writeBits(5, z);
		str.writeBits(1, 0);
		str.writeBits(12, npc.npcType);
		boolean savedUpdateRequired = npc.updateRequired;
		npc.updateRequired = true;
		npc.appendNPCUpdateBlock(updateBlock);
		npc.updateRequired = savedUpdateRequired;	
		str.writeBits(1, 1); // update required
	}
		
	public void addNewPlayer(Player plr, stream str, stream updateBlock)
	{
		int id = plr.playerId;
		playerInListBitmap[id >> 3] |= 1 << (id&7);
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
		int z = plr.absY-absY;
		if(z < 0) z += 32;
		str.writeBits(5, z);
		z = plr.absX-absX;
		if(z < 0) z += 32;
		str.writeBits(5, z);
	}

	protected boolean appearanceUpdateRequired = true;

	protected static stream playerProps;

	static
	{
		playerProps = new stream(new byte[100]);
	}

	protected void appendPlayerAppearance(stream str)
	{
		playerProps.currentOffset = 0;
		playerProps.writeByte(playerLook[0]);
		playerProps.writeByte(1 & 1 >> 2);
		if (isNpc == false)
		{
			if (playerEquipment[playerHat] > 1)
			{
				playerProps.writeWord(0x200 + playerEquipment[playerHat]);
			}
			else
			{
				playerProps.writeByte(0);
			}
			if (playerEquipment[playerCape] > 1)
			{
				playerProps.writeWord(0x200 + playerEquipment[playerCape]);
			}
			else
			{
				playerProps.writeByte(0);
			}
			if (playerEquipment[playerAmulet] > 1)
			{
				playerProps.writeWord(0x200 + playerEquipment[playerAmulet]);
			}
			else
			{
				playerProps.writeByte(0);
			}
			if (playerEquipment[playerWeapon] > 1)
			{
				playerProps.writeWord(0x200 + playerEquipment[playerWeapon]);
			}
			else
			{
				playerProps.writeByte(0);
			}
			if (playerEquipment[playerChest] > 1)
			{
				playerProps.writeWord(0x200 + playerEquipment[playerChest]);
			} else {
				playerProps.writeWord(0x100+pTorso);
			}
			if (playerEquipment[playerShield] > 1)
			{
				playerProps.writeWord(0x200 + playerEquipment[playerShield]);
			}
			else
			{
				playerProps.writeByte(0);
			}
			if (!Item.isPlate(playerEquipment[playerChest]))
			{
				playerProps.writeWord(0x100+pArms);
			}
			else
			{
				playerProps.writeByte(0);
			}
			if (playerEquipment[playerLegs] > 1)
			{
				playerProps.writeWord(0x200 + playerEquipment[playerLegs]);
			}
			else
			{
					playerProps.writeWord(0x100+pLegs);
			}
			if (!Item.isFullHelm(playerEquipment[playerHat]) && !Item.isFullMask(playerEquipment[playerHat]))
			{
				playerProps.writeWord(0x100 + pHead);		// head
			}
			else
			{
				playerProps.writeByte(0);
			}
			if (playerEquipment[playerHands] > 1)
			{
				playerProps.writeWord(0x200 + playerEquipment[playerHands]);
			}
			else
			{
				playerProps.writeWord(0x100+pHands);
			}
			if (playerEquipment[playerFeet] > 1)
			{
				playerProps.writeWord(0x200 + playerEquipment[playerFeet]);
			}
			else
			{
				playerProps.writeWord(0x100+pFeet);
			}
			playerProps.writeByte(0);
		}
		else
		{
			playerProps.writeWord(-1);
			playerProps.writeWord(npcId);

		}
		playerProps.writeByte(playerLook[1]);
		playerProps.writeByte(playerLook[2]);
		playerProps.writeByte(playerLook[3]);
		playerProps.writeByte(playerLook[4]);
		playerProps.writeByte(playerLook[5]);
		playerProps.writeWord(pEmote);
		playerProps.writeWord(0x337);
		playerProps.writeWord(playerSEW);
		playerProps.writeWord(0x334);
		playerProps.writeWord(0x335);
		playerProps.writeWord(0x336);
		playerProps.writeWord(playerSER);
		playerProps.writeQWord(misc.playerNameToInt64(playerName));
		int mag = (int)((double)(getLevelForXP(playerXP[4])) * 1.5);
		int ran = (int)((double)(getLevelForXP(playerXP[6])) * 1.5);
		int attstr = (int)((double)(getLevelForXP(playerXP[0])) + (double)(getLevelForXP(playerXP[2])));
		int combatLevel = 0;
		if (ran > attstr)
		{
			combatLevel = (int)(((double)(getLevelForXP(playerXP[1])) * 0.25) + ((double)(getLevelForXP(playerXP[3])) * 0.25) + ((double)(getLevelForXP(playerXP[5])) * 0.125) + ((double)(getLevelForXP(playerXP[6])) * 0.4875));
		}
		else if (mag > attstr)
		{
			combatLevel = (int)(((double)(getLevelForXP(playerXP[1])) * 0.25) + ((double)(getLevelForXP(playerXP[3])) * 0.25) + ((double)(getLevelForXP(playerXP[5])) * 0.125) + ((double)(getLevelForXP(playerXP[4])) * 0.4875));
		}
		else
		{
			combatLevel = (int)(((double)(getLevelForXP(playerXP[1])) * 0.25) + ((double)(getLevelForXP(playerXP[3])) * 0.25) + ((double)(getLevelForXP(playerXP[5])) * 0.125) + ((double)(getLevelForXP(playerXP[0])) * 0.325) + ((double)(getLevelForXP(playerXP[2])) * 0.325));
		}
		combat = combatLevel;
		playerProps.writeByte(combatLevel);
		playerProps.writeWord(0);
		str.writeByteC(playerProps.currentOffset);
		str.writeBytes(playerProps.buffer, playerProps.currentOffset, 0);
	}

	protected boolean chatTextUpdateRequired = false;

	protected byte chatText[] = new byte[4096], chatTextSize = 0;

	protected int chatTextEffects = 0, chatTextColor = 0;

	protected void appendPlayerChatText(stream str)
	{
		str.writeWordBigEndian(((chatTextColor&0xFF) << 8) + (chatTextEffects&0xFF));
		str.writeByte(playerRights);
		str.writeByteC(chatTextSize);
		str.writeBytes_reverse(chatText, chatTextSize, 0);
	}

	public void appendPlayerUpdateBlock(stream str)
	{
		if(!updateRequired && !chatTextUpdateRequired) return ;
		int updateMask = 0;
		if(chatTextUpdateRequired) updateMask |= 0x80;
		if(appearanceUpdateRequired) updateMask |= 0x10;
		if(hitUpdateRequired) updateMask |= 0x200;
		if(dirUpdateRequired) updateMask |= 0x40;
		if(updateMask >= 0x100)
		{
			updateMask |= 0x40;
			str.writeByte(updateMask & 0xFF);
			str.writeByte(updateMask >> 8);
		}
		else str.writeByte(updateMask);
		if(chatTextUpdateRequired) appendPlayerChatText(str);
		if(appearanceUpdateRequired) appendPlayerAppearance(str);
		if(hitUpdateRequired) appendHitUpdate(str);
		if(dirUpdateRequired) appendDirUpdate(str);
	}

	public void clearUpdateFlags()
	{
		updateRequired = false;
		chatTextUpdateRequired = false;
		appearanceUpdateRequired = false;
		hitUpdateRequired = false;
		dirUpdateRequired = false;
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

// is being called regularily every 500ms - do any automatic player actions herein
	public abstract boolean process();

	public boolean following = false;

	public void postProcessing()
	{
		if(newWalkCmdSteps > 0)
		{
			int firstX = newWalkCmdX[0], firstY = newWalkCmdY[0];
			int lastDir = 0;
			boolean found = false;
			numTravelBackSteps = 0;
			int ptr = wQueueReadPtr;
			int dir = misc.direction(currentX, currentY, firstX, firstY);
			if(dir != -1 && (dir&1) != 0)
			{
				do
				{
					lastDir = dir;
					if(--ptr < 0) ptr = walkingQueueSize-1;
					travelBackX[numTravelBackSteps] = walkingQueueX[ptr];
					travelBackY[numTravelBackSteps++] = walkingQueueY[ptr];
					dir = misc.direction(walkingQueueX[ptr], walkingQueueY[ptr], firstX, firstY);
					if(lastDir != dir)
					{
						found = true;
						break;
					}

				}
				while(ptr != wQueueWritePtr);
			}
			else found = true;
			if(!found)
			{
				println_debug("Fatal: couldn't find connection vertex! Dropping packet.");
				disconnected = true;
			}
			else
			{
				wQueueWritePtr = wQueueReadPtr;

				addToWalkingQueue(currentX, currentY);

				if(dir != -1 && (dir&1) != 0)
				{
					for(int i = 0; i < numTravelBackSteps-1; i++)
					{
						addToWalkingQueue(travelBackX[i], travelBackY[i]);
					}
					int wayPointX2 = travelBackX[numTravelBackSteps-1], wayPointY2 = travelBackY[numTravelBackSteps-1];
					int wayPointX1, wayPointY1;
					if(numTravelBackSteps == 1)
					{
						wayPointX1 = currentX;
						wayPointY1 = currentY;
					}
					else
					{
						wayPointX1 = travelBackX[numTravelBackSteps-2];
						wayPointY1 = travelBackY[numTravelBackSteps-2];
					}
					dir = misc.direction(wayPointX1, wayPointY1, wayPointX2, wayPointY2);
					if(dir == -1 || (dir&1) != 0)
					{
						println_debug("Fatal: The walking queue is corrupt! wp1=("+wayPointX1+", "+wayPointY1+"), "+
							"wp2=("+wayPointX2+", "+wayPointY2+")");
					}
					else
					{
						dir >>= 1;
						found = false;
						int x = wayPointX1, y = wayPointY1;
						while(x != wayPointX2 || y != wayPointY2)
						{
							x += misc.directionDeltaX[dir];
							y += misc.directionDeltaY[dir];
							if((misc.direction(x, y, firstX, firstY)&1) == 0)
							{
								found = true;
								break;
							}
						}
						if(!found)
						{
							println_debug("Fatal: Internal error: unable to determine connection vertex!"+
							"  wp1=("+wayPointX1+", "+wayPointY1+"), wp2=("+wayPointX2+", "+wayPointY2+"), "+
								"first=("+firstX+", "+firstY+")");
						}
						else addToWalkingQueue(wayPointX1, wayPointY1);
					}
				}
				else
				{
					for(int i = 0; i < numTravelBackSteps; i++)
					{
						addToWalkingQueue(travelBackX[i], travelBackY[i]);
					}
				}
				for(int i = 0; i < newWalkCmdSteps; i++)
				{
					addToWalkingQueue(newWalkCmdX[i], newWalkCmdY[i]);
				}
			}
			isRunning = newWalkCmdIsRunning || isRunning2;
		}
	}

	public void kick()
	{
		isKicked = true;
	}

	protected int hitDiff = 0;

	protected boolean hitUpdateRequired = false;

	protected boolean IsDead = false;

	protected int NewHP = 100;

	protected boolean SafeMyLife = false;

	protected boolean IsStair = false;

	protected boolean IsDeadTeleporting = false;

	protected boolean IsDeadTimer = false;

	protected void appendHitUpdate(stream str)
	{		
		try
		{
			str.writeByte(hitDiff);
			if (hitDiff > 0 && newhptype == false && poisondmg == false)
			{
				str.writeByteS(1);
			}
			else if (hitDiff > 0 && poisondmg == true)
			{
				str.writeByteS(2);
			}
			else if (hitDiff > 0 && newhptype == true)
			{
				str.writeByteS(hptype);
			}
			else
			{
				str.writeByteS(0);
			}
			NewHP = (playerLevel[playerHitpoints] - hitDiff);
			if (NewHP <= 0)
			{
				NewHP = 0;
				IsDead = true;
			}
			str.writeByte(NewHP);
			str.writeByteC(getLevelForXP(playerXP[playerHitpoints]));
			poisondmg = false;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

	public int getLevelForXP(int exp)
	{
		int points = 0;
		int output = 0;
		for (int lvl = 1; lvl <= 150; lvl++)
		{
			points += Math.floor((double)lvl + 300.0 * Math.pow(2.0, (double)lvl / 7.0));
			output = (int)Math.floor(points / 4);
			if (output >= exp)
			{
				return lvl;
			}
		}
		return 0;
	}

	public void appendDirUpdate(stream str)
	{
		if (playerMD != -1)
		{
			/*str.writeBits(2, 1);		// updateType
			str.writeBits(3, misc.xlateDirectionToClient[playerMD]);
			if(updateRequired) {
				str.writeBits(1, 1);		// tell client there's an update block appended at the end
			} else {
				str.writeBits(1, 0);
			}*/
str.writeWord(playerMD);
			playerMD = -1;
		}
	}

	public boolean[] IsDropped = new boolean[server.itemHandler.MaxDropItems];

	public boolean[] MustDelete = new boolean[server.itemHandler.MaxDropItems];

	public boolean IsDropping = false;

	public abstract boolean isinpm(long l);

	public abstract void loadpm(long l, int world);

	public abstract void pmupdate(int pmid, int world);

	public int Privatechat = 0;

	public abstract void sendpm(long name,int rights,byte[] chatmessage, int messagesize);

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

	public int playerSE = 0x328;

	public int playerSEW = 0x333;

	public int playerSER = 0x338;

	public int playerSEA = 0x326;

	public int playerMD = -1;

	protected boolean dirUpdateRequired = false;

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
}