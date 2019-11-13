import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

//2400,4446

public class client extends Player implements Runnable {
	public static boolean AutoSave = false;
	public static final int bufferSize = 1000000;
	public static boolean comeback = false;
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
			0, 0, 6, 6, 0, 0, 0 // 250
	};
	public static boolean threats;
	public int aaa;
	public int abc;
	public int abc2;
	public int actionButtonId = 0;
	public boolean adding = false, emoting = false;
	/* ALLIGNMENT(Good - Evil - Creds to Runite) */
	public int allignment = 0;
	public boolean alreadyChose = false;
	public int[] ancientButton = { 51133, 51185, -1, 51091, 24018, -1, 51159,
			51211, -1, 51111, 51069, -1, 51146, 51198, -1, 51102, 51058, -1,
			51172, 51224, -1, 51122, 51080, -1 };
	public int[] ancientId = { 12939, 12987, 0, 12901, 12861, 0, 12963, 13011,
			0, 12919, 12881, 0, 12951, 12999, 0, 12911, 12871, 0, 12975, 13023,
			0, 12929, 12891, 0 };
	public int ancients = 1;
	public boolean ancientstele = false;
	public int[] ancientType = { 0, 0, 1, 2, 3, 1, 0, 0, 1, 2, 3, 1, 0, 0, 1,
			2, 3, 1, 0, 0, 1, 2, 3, 1 };
	public int angle = 250;
	long animationReset = 0, lastButton = 0;
	public boolean AnimationReset; // Resets Animations With The Use Of The
	public int AntiTeleDelay;
	public int arenaSpellTimer;
	public int attackedNpcId = -1;
	public int AttackingOn = 0;
	public int autocast_spellIndex = -1;

	public int barTimer = 0, saveTimer = 0;
	public int[] baseDamage = { 1, 2, 0, 3, 4, 0, 5, 6, 0, 7, 8, 0, 9, 10, 0,
			11, 12, 0, 13, 14, 0, 15, 16, 0 };
	public String BonusMySqlName[] = { "attack_stab", "attack_slash",
			"attack_crush", "attack_magic", "attack_range", "defence_stab",
			"defence_slash", "defence_crush", "defence_magic", "defence_range",
			"other_strength", "other_prayer" };

	public String BonusName[] = { "Stab", "Slash", "Crush", "Magic", "Range",
			"Stab", "Slash", "Crush", "Magic", "Range", "Str", "Spell Dmg" };
	public byte buffer[] = null;
	int cAmount = 0;
	public int[] casketItems = { 4724, 4726, 4728, 4730, 1050, 1053, 1055,
			1057, 1037, 3107, 4708, 4710, 4712, 4714, 2904, 777, 2414 };
	public boolean cast = false; // Part Of The Create Spell Code
	public boolean castleWarsOn = false;

	public boolean castSpell, isStillSpell;
	public int cba;
	int cExp = 0;
	int cItem = -1;
	int cLevel = 1;
	public int clickIndex = 0;
	public int[] clicks = new int[50];
	public int CombatExpRate = 1;
	public String converse;
	private int cooking[] = { 0, 0, 0, 1, -1, -1, -1 };
	public long[] coolDown = { 5000, 5000, 2500, 5000 };
	public int[] coolDownGroup = { 2, 2, 1, 2, 3, 1, 2, 2, 1, 2, 3, 1, 2, 2, 1,
			2, 3, 1, 2, 2, 1, 2, 3, 1 };
	// Devolution: crafting
	boolean crafting = false;
	int cSelected = -1, cIndex = -1;
	public int currentButton = 0, currentStatus = 0;
	public int cwAmount = 0;
	public int cwTimer = 0;
	// Devolution: smelting
	boolean dialog = true, spinning = false;
	int dialogInterface = 2459, dialogId = 1;
	public String dMsg = "";
	public int doors = -1;

	public int duel_with = 0;

	public boolean duelAccept1 = false;
	public boolean duelAccept2 = false;
	public int[] duelButtons = { 26069, 26070, 26071, 30136, 2158, 26065,
			26072, 26073, 26074, 26066, 26076 };
	public int[] duelItems = new int[28];
	public int[] duelItemsN = new int[28];
	public boolean[] duelItemsNoted = new boolean[28];
	public int[] duelLine = { 6698, 6699, 6697, 7817, 669, 6696, 6701, 6702,
			6703, 6704, 6731 };
	public String[] duelNames = { "No Ranged", "No Melee", "No Magic",
			"No Gear Change", "Fun Weapons", "No Retreat", "No Drinks",
			"No Food", "No prayer", "No Movement", "Obstacles" };
	public int duelpartner = 0;
	public boolean duelReq = false;
	public boolean duelRequested = false, inDuel = false,
			duelConfirmed = false, duelConfirmed2 = false,
			duelResetNeeded = false, duelFight = false;
	public boolean[] duelRule = { false, false, false, false, false, true,
			false, true, false, true, false };
	public int duelWho = 0;
	public int[] effects = new int[10];
	private int emotes = 0;
	public int enemyId = -1, enemyX = -1, enemyY = -1, attackTimer = 0;
	public int EntangleDelay = 0;
	/*
	 * [0] North West [1] North East [2] Center [3] South East [4] South West
	 */
	public int EssenceMineRX[] = { 3253, 3105, 2681, 2591 };
	public int EssenceMineRY[] = { 3401, 9571, 3325, 3086 };
	public int EssenceMineX[] = { 2893, 2921, 2911, 2926, 2899 };
	public int EssenceMineY[] = { 4846, 4846, 4832, 4817, 4817 };

	public int fangle = 0;
	public int fcasterX = 0;
	public int fcasterY = 0;
	// ActionTimer
	public int fcastid = 0;
	public int feh = 0;
	public int fenemyX = 0;

	public int fenemyY = 0;
	public int ffinishid = 0;
	public boolean fired = false; // Part Of The Create Spell Code
	private int firemaking[] = { 0, 0, 0, 1, -1 };
	public boolean firingspell = false; // Part Of The Create Spell Code

	boolean fishing = false;

	// Devolution: fishing
	int fishTries, fishId;

	public int fletchId = -1, fletchAmount = -1, fletchLog = -1,
			originalW = -1, originalS = -1, fletchExp = 0;

	public boolean fletching = false;

	// These are temp data
	public int fletchTime;

	public int fmgfxid = 0;

	public int foffsetX = 0;

	public int foffsetY = 0;

	public boolean friendUpdate = false, lookUpdate = false;

	public int fsh = 0;

	public int fspeed = 0;

	public long[] globalCooldown = new long[10];

	/* RANGE */
	public boolean HasArrows = false;

	/* MISC */
	public int hasset = 0;

	private int healing[] = { 0, 0, 0, -1, -1 };

	public int i;

	public boolean iceBarrage = false;

	/* WALKING TO OBJECT BEFORE DOING ACTION */

	public int iceTimer = 0;

	/* DUELING */

	private java.io.InputStream in;

	public stream inStream = null, outStream = null;

	public Cryption inStreamDecryption = null, outStreamDecryption = null;

	public boolean isSpellNPC; // added check weather magic attack player or

	public int KillerId = playerId;

	public long lastAttack = 0;

	String[] lastMessage = new String[3];

	public long lastMouse = 0;
	// Devolution: fletching

	private long lastPickup = 0;

	public long lastProcess = 0;
	public int loginDelay = 1;
	public boolean lookNeeded = false;

	/* SUMMONING */

	public int lowMemoryVersion = 0;
	public int MageAttackIndex = -1; // -1
	public String MBBC;
	public String MBHT;
	public String MBID;

	/*
	 * --------MOD BOT----------- // MB = MOD BOT // -TC = Text Censor // -BC =
	 * Bad Command // -HT = Help Text // -ID = Item Duping
	 */
	public String MBTC;

	public boolean member = false;
	private int mining[] = { 0, 0, 0, 1, -1 };
	private java.net.Socket mySock;

	public int newheightLevel = 0;

	public int[] noTrade = { 1543, 1544 };

	public int NPCID; // GLOBALLY NOW last clicked npcID -bakatool

	public int NPCSlot; // GLOBALLY NOW last clicked npc slot -bakatool

	public boolean oddDeath = false;

	public CopyOnWriteArrayList<GameItem> offeredItems = new CopyOnWriteArrayList<GameItem>();

	public boolean officialClient = false;
	public int oldclick = 0;
	public int OriginalShield = -1;
	public int OriginalWeapon = -1;
	public CopyOnWriteArrayList<GameItem> otherOfferedItems = new CopyOnWriteArrayList<GameItem>();
	private java.io.OutputStream out;
	public int packetSize = 0, packetType = -1;

	public int pCArms;

	public int pCBeard;
	public int pCFeet;
	public int pCHands;
	public int pCHead;

	public int pCLegs;
	public int pColor;

	public int pCTorso;

	public int PickUpAmount = 0;

	public int PickUpDelete = 0;

	public int PickUpID = 0;

	public int playerClass = 0; // 0 = undecided, 1 = warrior, 2 = mage, 3 =

	public boolean playerIsSaradomin = false;

	public boolean playerIsZamorak = false;

	public String playerstatus = "";
	private int prayer[] = { 0, 1, 0, 1, -1, -1 };
	// public int[] killers = new int[server.playerHandler.maxPlayers];
	public String properName = "";

	public int Publicchat = 0;
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
	public int random_skill = -1, npcId = -1;
	public int readPtr, writePtr;
	public int[] requiredLevel = { 1, 10, 25, 38, 50, 60, 62, 64, 66, 68, 70,
			72, 74, 76, 78, 80, 82, 84, 86, 88, 90, 92, 94, 96 };

	public int resetanim = 8;

	public int restart = 0;

	/* PRAYER */
	public boolean Retribution = false;

	public int returnCode = 2; // Tells the client if the login was successfull

	public int rune1, rune1Am, rune2, rune2Am, rune3, rune3Am, rune4, rune4Am,
			spellXP;

	public int sameclick = 0;

	public boolean SaradominStrike, GuthixClaws, ZamorakFlames;

	public int saraScore;

	public int savecounter = 0;

	public boolean saveNeeded = true;

	public boolean shafting = false;

	public int SkillID = 0;

	public int skillX = -1;

	public int skillY = -1;

	public int smelt_id, smeltCount;

	public boolean smelting = false;

	private int smithing[] = { 0, 0, 0, 1, -1, 0 };

	private int somejunk;

	public boolean spamButton = false;

	// -bakatool
	public int spellHit; // also same added so it won't be static ROFL
	// -bakatool

	public int spellHitTimer;
	// npc. -bakatool
	public int spellNpcIndex = -1; // added so it won't be static ROFL..
	// -bakatool
	public int spellPlayerIndex = -1; // same added so it won't be static ROFL
	public boolean spellSet = false;
	public int stairDistance = 1;
	public int stairDistanceAdd = 0;
	public int stairs = 0;
	public int[] statId = { 10252, 11000, 10253, 11001, 10254, 11002, 10255,
			11011, 11013, 11014, 11010, 11012, 11006, 11009, 11008, 11004,
			11003, 11005, 47002, 54090, 11007 };
	public String statName[] = { "attack", "defence", "strength", "hitpoints",
			"range", "prayer", "magic", "cooking", "woodcutting", "fletching",
			"fishing", "firemaking", "crafting", "smithing", "mining",
			"herblore", "agility", "thieving", "slayer", "farming",
			"runecrafting" };
	public int stealtimer;
	public int stillSpellGFX;
	public int summonLevel = 250;
	public int summonXP = 9999999;
	public String teleLoc = "";
	public boolean teleOtherScreen = false;
	public boolean teleport = false;
	public int teleReq = 0;
	public int teletimer = 0;

	public int teleX = 0;

	public int teleY = 0;

	public int timeOutCounter = 0; // to detect timeouts on the connection to
	// the client

	public int trade_reqId = 0, trade_other;

	public int Tradecompete = 0;
	public boolean tradeRequested = false, inTrade = false, canOffer = true,
			tradeConfirmed = false, tradeConfirmed2 = false,
			tradeResetNeeded = false;
	long tTime = 0;
	// Devolution: teleports
	int tX = 0, tY = 0, tStage = 0, tH = 1;
	private int useitems[] = { -1, -1, -1, -1 };

	// ranger
	public String[] userGroup = { "Unknown group", "Guest", "Registered User",
			"Awaiting Confirmation", "COPPA User", "Super Moderator",
			"Administrator", "Moderator", "Banned User", "Unknown group",
			"Veteran" };

	public boolean validClient = true, muted = false, attackedNpc = false;

	public boolean validLogin = false;
	// public int[] restrictedItem = { 4716, 4718, 4720, 4722, 4724, 4726, 4728,
	// 4730};
	private int WanneBank = 0;
	private int WanneShop = 0;

	public boolean wearing = false;

	public boolean WildernessWarning = false;

	public String Winner = "Nobody";

	private int woodcutting[] = { 0, 0, 0, 1, -1, 2 };

	private int XinterfaceID = 0;

	private int XremoveID = 0;

	private int XremoveSlot = 0;

	public int zammyScore;

	public client(java.net.Socket s, int _playerId) {
		super(_playerId);
		mySock = s;
		try {
			in = s.getInputStream();
			out = s.getOutputStream();
		} catch (java.io.IOException ioe) {
			misc.println("Devolution Server (1): Exception!");
			server.logError(ioe.getMessage());
		}

		outStream = new stream(new byte[bufferSize]);
		outStream.currentOffset = 0;
		inStream = new stream(new byte[bufferSize]);
		inStream.currentOffset = 0;

		readPtr = writePtr = 0;
		buffer = buffer = new byte[bufferSize];
	}

	public void actionReset() {
		actionName = "";
	}

	public void addClick(int ms) {
		println("adding click " + ms + " (clickindex = " + clickIndex + " )");
		if (clickIndex <= clicks.length - 1) {
			clicks[clickIndex] = ms;
			clickIndex++;
		} else {
			int[] precision = new int[50];
			int average = average(clicks);
			for (int i = 0; i < precision.length; i++) {
				precision[i] = Math.abs(clicks[i] - average);
				println("precision[" + i + "]=" + precision[i]);
			}
			resetArray(clicks);
			clickIndex = 0;
		}
	}

	public void AddDroppedItems() {
		if (IsDropping == false) {
			IsDropping = true;
			int tmpX = 0;
			int tmpY = 0;
			int calcX = 0;
			int calcY = 0;

			for (int i = 0; i < server.itemHandler.DropItemCount; i++) {
				if (server.itemHandler.DroppedItemsID[i] > -1) {
					tmpX = server.itemHandler.DroppedItemsX[i];
					tmpY = server.itemHandler.DroppedItemsY[i];
					calcX = tmpX - absX;
					calcY = tmpY - absY;
					if ((calcX >= -16)
							&& (calcX <= 15)
							&& (calcY >= -16)
							&& (calcY <= 15)
							&& (MustDelete[i] == false)
							&& (server.itemHandler.DroppedItemsH[i] == heightLevel)) {
						if ((IsDropped[i] == false)
								&& ((server.itemHandler.DroppedItemsDDelay[i] <= 0) || (server.itemHandler.DroppedItemsDropper[i] == playerId))) {
							IsDropped[i] = true;
							outStream.createFrame(85);
							outStream
									.writeByteC((server.itemHandler.DroppedItemsY[i] - 8 * mapRegionY));
							outStream
									.writeByteC((server.itemHandler.DroppedItemsX[i] - 8 * mapRegionX));
							outStream.createFrame(44); // create item frame
							outStream
									.writeWordBigEndianA(server.itemHandler.DroppedItemsID[i]);
							outStream
									.writeWord(server.itemHandler.DroppedItemsN[i]); // amount
							outStream.writeByte(0); // x(4 MSB) y(LSB) coords
						}
					} else if ((IsDropped[i] == true)
							|| (MustDelete[i] == true)) {
						outStream.createFrame(85);
						outStream
								.writeByteC((server.itemHandler.DroppedItemsY[i] - 8 * mapRegionY));
						outStream
								.writeByteC((server.itemHandler.DroppedItemsX[i] - 8 * mapRegionX));
						outStream.createFrame(156); // remove item frame
						outStream.writeByteS(0); // x(4 MSB) y(LSB) coords
						outStream
								.writeWord(server.itemHandler.DroppedItemsID[i]);
						int LastPlayerInList = -1;
						int TotalPlayers = 0;

						for (int j = 1; j < PlayerHandler.maxPlayers; j++) {
							if (PlayerHandler.players[j] != null) {
								LastPlayerInList = j;
								TotalPlayers++;
							}
						}
						if (MustDelete[i] == true) {
							MustDelete[i] = false;
							server.itemHandler.DroppedItemsDeletecount[i]++;
							if (((LastPlayerInList == playerId) || (LastPlayerInList == -1))
									&& (server.itemHandler.DroppedItemsDeletecount[i] == TotalPlayers)) {
								if (server.itemHandler.DroppedItemsAlwaysDrop[i] == true) {
									server.itemHandler.DroppedItemsDropper[i] = -1;
									server.itemHandler.DroppedItemsDDelay[i] = server.itemHandler.SDID;
								} else {
									server.itemHandler.ResetItem(i);
								}
								for (int j = 1; j < PlayerHandler.maxPlayers; j++) {
									if (PlayerHandler.players[j] != null) {
										PlayerHandler.players[j].IsDropped[i] = false;
									}
								}
							}
						} else {
							IsDropped[i] = false;
						}
					}
				}
			}
			IsDropping = false;
		}
	}

	public void AddGlobalObj(int objectX, int objectY, int NewObjectID,
			int Face, int ObjectType) {
		// for (Player p : server.playerHandler.players) {
		// Linux (java 1.4.2-compatible) change - Devolution
		for (Player p : server.playerHandler.players) {
			if (p != null) {
				client person = (client) p;

				if (person.playerName != null) {
					if (person.distanceToPoint(objectX, objectY) <= 60) {
						person.ReplaceObject2(objectX, objectY, NewObjectID,
								Face, ObjectType);
					}
				}
			}
		}
	}

	public boolean addItem(int item, int amount) {
		if (item >= Item.itemStackable.length) {
			return false;
		}
		if ((item < 0) || (amount < 1)) {
			return false;
		}
		if (!Item.itemStackable[item] || (amount < 1)) {
			amount = 1;
		}

		if (((freeSlots() >= amount) && !Item.itemStackable[item])
				|| (freeSlots() > 0)) {
			for (int i = 0; i < playerItems.length; i++) {
				if ((playerItems[i] == (item + 1)) && Item.itemStackable[item]
						&& (playerItems[i] > 0)) {
					playerItems[i] = (item + 1);
					if (((playerItemsN[i] + amount) < maxItemAmount)
							&& ((playerItemsN[i] + amount) > -1)) {
						playerItemsN[i] += amount;
					} else {
						playerItemsN[i] = maxItemAmount;
					}
					outStream.createFrameVarSizeWord(34);
					outStream.writeWord(3214);
					outStream.writeByte(i);
					outStream.writeWord(playerItems[i]);
					if (playerItemsN[i] > 254) {
						outStream.writeByte(255);
						outStream.writeDWord(playerItemsN[i]);
					} else {
						outStream.writeByte(playerItemsN[i]); // amount
					}
					outStream.endFrameVarSizeWord();
					i = 30;
					return true;
				}
			}
			for (int i = 0; i < playerItems.length; i++) {
				if (playerItems[i] <= 0) {
					playerItems[i] = item + 1;
					if ((amount < maxItemAmount) && (amount > -1)) {
						playerItemsN[i] = amount;
					} else {
						playerItemsN[i] = maxItemAmount;
					}
					outStream.createFrameVarSizeWord(34);
					outStream.writeWord(3214);
					outStream.writeByte(i);
					outStream.writeWord(playerItems[i]);
					if (playerItemsN[i] > 254) {
						outStream.writeByte(255);
						outStream.writeDWord(playerItemsN[i]);
					} else {
						outStream.writeByte(playerItemsN[i]); // amount
					}
					outStream.endFrameVarSizeWord();
					i = 30;
					return true;
				}
			}
			return false;
		} else {
			sendMessage("Not enough space in your inventory.");
			return false;
		}
	}

	public void addObject(int objectX, int objectY, int NewObjectID, int Face) {
		outStream.createFrameVarSizeWord(60);
		outStream.writeByte(objectY - (mapRegionY * 8));
		outStream.writeByteC(objectX - (mapRegionX * 8));

		/* CREATE OBJECT */
		if (NewObjectID > -1) {
			outStream.writeByte(151);
			outStream.writeByteS(0);
			outStream.writeWordBigEndian(NewObjectID);
			outStream.writeByteA(Face); // 0= WEST | -1 = NORTH | -2 = EAST | -3
			// = SOUTH
		}
		outStream.endFrameVarSizeWord();
	}

	public void AddObjectFire() {
		if (IsFireing == false) {
			IsFireing = true;
			int tmpX = 0;
			int tmpY = 0;
			int calcX = 0;
			int calcY = 0;

			for (int i = 0; i < server.objectHandler.MaxObjects; i++) {
				if (server.itemHandler.DroppedItemsID[i] > -1) {
					tmpX = server.objectHandler.ObjectFireX[i];
					tmpY = server.objectHandler.ObjectFireY[i];
					calcX = tmpX - absX;
					calcY = tmpY - absY;
					if ((calcX >= -16)
							&& (calcX <= 15)
							&& (calcY >= -16)
							&& (calcY <= 15)
							&& (FireDelete[i] == false)
							&& (server.objectHandler.ObjectFireH[i] == heightLevel)) {
						if (IsFireShowed[i] == false) {
							IsFireShowed[i] = true;
							ReplaceObject(server.objectHandler.ObjectFireX[i],
									server.objectHandler.ObjectFireY[i],
									server.objectHandler.ObjectFireID[i], 0, 10);
						}
					} else if ((IsFireShowed[i] == true)
							|| (FireDelete[i] == true)) {
						ReplaceObject(server.objectHandler.ObjectFireX[i],
								server.objectHandler.ObjectFireY[i], -1, 0, 10);
						int LastPlayerInList = -1;
						int TotalPlayers = 0;

						for (int j = 1; j < PlayerHandler.maxPlayers; j++) {
							if (PlayerHandler.players[j] != null) {
								LastPlayerInList = j;
								TotalPlayers++;
							}
						}
						if (FireDelete[i] == true) {
							FireDelete[i] = false;
							server.objectHandler.ObjectFireDeletecount[i]++;
							if (((LastPlayerInList == playerId) || (LastPlayerInList == -1))
									&& (server.objectHandler.ObjectFireDeletecount[i] == TotalPlayers)) {
								server.objectHandler.ResetFire(i);
								for (int j = 1; j < PlayerHandler.maxPlayers; j++) {
									if (PlayerHandler.players[j] != null) {
										PlayerHandler.players[j].IsFireShowed[i] = false;
									}
								}
								if (misc.random(2) == 1) {
									createItem(592);
								}
							}
						} else {
							IsFireShowed[i] = false;
						}
					}
				}
			}
			IsFireing = false;
		}
	}

	public boolean addShopItem(int itemID, int amount) {
		boolean Added = false;

		if (amount <= 0) {
			return false;
		}
		if (Item.itemIsNote[itemID] == true) {
			itemID = GetUnnotedItem(itemID);
		}
		for (int i = 0; i < server.shopHandler.ShopItems.length; i++) {
			if ((server.shopHandler.ShopItems[MyShopID][i] - 1) == itemID) {
				server.shopHandler.ShopItemsN[MyShopID][i] += amount;
				Added = true;
			}
		}
		if (Added == false) {
			for (int i = 0; i < server.shopHandler.ShopItems.length; i++) {
				if (server.shopHandler.ShopItems[MyShopID][i] == 0) {
					server.shopHandler.ShopItems[MyShopID][i] = (itemID + 1);
					server.shopHandler.ShopItemsN[MyShopID][i] = amount;
					server.shopHandler.ShopItemsDelay[MyShopID][i] = 0;
					break;
				}
			}
		}
		return true;
	}

	public boolean addSkillXP(int amount, int skill) {
		if (randomed) {
			sendMessage("You must answer the genie before you can gain experience!");
			return false;
		}

		int oldLevel = getLevelForXP(playerXP[skill]);
		// int[] statId = {4004, 4008, 4006, 4016, 4010, 4012, 4014, 4034, 4038,
		// 4026, 4032, 4036, 4024, 4030, 4028, 4020, 4018, 4022, 4152};
		playerXP[skill] += amount;
		if (oldLevel < getLevelForXP(playerXP[skill])) {
			// if(oldLevel >= 85)
			animation(199, absY, absX);
			playerLevel[skill] = getLevelForXP(playerXP[skill]);
			// stillgfx(623, absY, absX);
			// levelup(skill);
			updateRequired = true;
			appearanceUpdateRequired = true;
			sendMessage("Congratulations, you just advanced a "
					+ statName[skill] + " level.");
			if (playerLevel[skill] > 90)
				yell(playerName + "'s " + statName[skill] + " level is now "
						+ playerLevel[skill] + "!");
			// sendFrame126(playerName + " (" + combatLevel + ")", 6572);
			setSkillLevel(skill, playerLevel[skill], playerXP[skill]);
		}
		// setSkillLevel(skill, playerLevel[skill], playerXP[skill]);
		refreshSkills();
		if (skill == 2) {
			CalculateMaxHit();
		}
		return true;

	}

	public int amountOfItem(int itemID) {
		int i1 = 0;

		for (int i = 0; i < 28; i++) {
			if (playerItems[i] == (itemID + 1)) {
				i1++;
			}
		}
		return i1;
	}

	public void animation(int id, int Y, int X) {
		// ANIMATIONS AT GROUND HEIGHT
		// for (Player p : server.playerHandler.players) {
		for (Player p : server.playerHandler.players) {
			if (p != null) {
				client person = (client) p;

				if (person.playerName != null) {
					if (person.distanceToPoint(X, Y) <= 60) {
						person.animation2(id, Y, X);
					}
				}
			}
		}
	}

	public void animation2(int id, int Y, int X) {
		// ANIMATIONS AT GROUND HEIGHT
		outStream.createFrame(85);
		outStream.writeByteC(Y - (mapRegionY * 8));
		outStream.writeByteC(X - (mapRegionX * 8));
		outStream.createFrame(4);
		outStream.writeByte(0); // Tiles away (X >> 4 + Y & 7)
		outStream.writeWord(id); // Graphic id
		outStream.writeByte(0); // height of the spell above it's basic place, i
		// think it's written in pixels 100 pixels high
		outStream.writeWord(0); // Time before casting the graphic
	}

	public void animation3(int id, int Y, int X) {
		// ANIMATIONS AT MIDDLE HEIGHT
		// for (Player p : server.playerHandler.players) {
		for (Player p : server.playerHandler.players) {
			if (p != null) {
				client person = (client) p;

				if (person.playerName != null) {
					if (person.distanceToPoint(X, Y) <= 60) {
						person.animation4(id, Y, X);
					}
				}
			}
		}
	}

	public void animation4(int id, int Y, int X) {
		// ANIMATIONS AT GROUND HEIGHT
		outStream.createFrame(85);
		outStream.writeByteC(Y - (mapRegionY * 8));
		outStream.writeByteC(X - (mapRegionX * 8));
		outStream.createFrame(4);
		outStream.writeByte(0); // Tiles away (X >> 4 + Y & 7)
		outStream.writeWord(id); // Graphic id
		outStream.writeByte(0); // height of the spell above it's basic place, i
		// think it's written in pixels 100 pixels high
		outStream.writeWord(0); // Time before casting the graphic
	}

	public boolean antiHax() {
		if (System.currentTimeMillis() - lastMouse > 5000) {
			println("Suspicious activity!");
			sendMessage("Client hack detected!");
			sendMessage("The only supported clients are the devolution client and moparscape");
			return false;
		}
		return true;
	}

	/*
	 * ========================================== Damage npc and delete runes
	 * when the last GFX has hit the npc, if the hit is succesful.
	 * ==========================================
	 */
	public void appendHitToNpc(int index, int hitDiff, boolean stillSpell) // Used
	// for
	// MagicHandler
	{
		if (stillSpell) {
			isStillSpell = false;
			stillgfx2(stillSpellGFX, MagicHandler.graphicHeight,
					MagicHandler.npcY, MagicHandler.npcX, 2);
			stillSpellGFX = -1;
		}
		if (MagicHandler.npcHP - hitDiff < 0) {
			hitDiff = MagicHandler.npcHP;
		}
		if (MagicHandler.itHeals) {
			if (misc.random(2) == 1) {
				NewHP = (playerLevel[playerHitpoints] + (hitDiff / 4));
				if (NewHP >= getLevelForXP(playerXP[playerHitpoints])) {
					NewHP = getLevelForXP(playerXP[playerHitpoints]);
				}
				sendMessage("You drain the enemies health.");
				refreshSkills();
			}
		}
		if (MagicHandler.itFreezes) {
			sendMessage("" + getFrozenMessage(MagicHandler.spellID) + "");
			if (server.npcHandler.npcs[index].freezeTimer <= 0) {
				server.npcHandler.npcs[index].freezeTimer = getFreezeTimer(MagicHandler.spellID);
			}
		}
		if (MagicHandler.itReducesAttack) {
			if (misc.random(2) == 1) {
				if (server.npcHandler.npcs[index].reducedAttack <= 0) {
					sendMessage("You reduce the enemies attack power.");
					server.npcHandler.npcs[index].MaxHit -= (hitDiff / 2);
					if ((server.npcHandler.npcs[index].MaxHit - (hitDiff / 2)) < server.npcHandler.npcs[index].MaxHit) {
						server.npcHandler.npcs[index].MaxHit = 0;
					}
					server.npcHandler.npcs[index].reducedAttack = getAttackTimer(MagicHandler.spellID);
				}
			}
		}
		if (MagicHandler.itPoisons) {
			if (misc.random(2) == 1) {
				if (server.npcHandler.npcs[index].poisonTimer <= 0) {
					sendMessage("You poison the enemy.");
					server.npcHandler.npcs[index].poisonTimer = 120;
					server.npcHandler.npcs[index].poisonDmg = true;
					server.npcHandler.poisonNpc(index);
				}
			}
		}
		if (rune1 != -1) // fixed delete bug -bakatool
			deleteItem(rune1, getItemSlot(rune1), rune1Am);
		if (rune2 != -1) // fixed delete bug -bakatool
			deleteItem(rune2, getItemSlot(rune2), rune2Am);
		if (rune3 != -1) // //fixed delete bug -bakatool
			deleteItem(rune3, getItemSlot(rune3), rune3Am);
		if (rune4 != -1) // //fixed delete bug -bakatool
			deleteItem(rune4, getItemSlot(rune4), rune4Am);
		addSkillXP((spellXP), 6);
		addSkillXP((spellXP / 2), 3);
		if (hitDiff != 0) {
			server.npcHandler.npcs[index].hitDiff = hitDiff;
			server.npcHandler.npcs[index].Killing[playerId] += hitDiff;
			server.npcHandler.npcs[index].updateRequired = true;
			server.npcHandler.npcs[index].hitUpdateRequired = true;
		}
	}

	public void appendHitToPlayer(int index, int hitDiff, boolean stillSpell) // Used
	// for
	// MagicHandler
	{
		try {
			if (server.playerHandler.players[index] != null) {
				if (stillSpell) {
					isStillSpell = false;
					stillgfx2(stillSpellGFX, MagicHandler.graphicHeight,
							MagicHandler.playerY, MagicHandler.playerX, 2);
					stillSpellGFX = -1;
				}
				if (MagicHandler.playerHP - hitDiff < 0) {
					hitDiff = MagicHandler.playerHP;
				}
				if (MagicHandler.itHeals) {
					if (misc.random(2) == 1) {
						NewHP = (playerLevel[playerHitpoints] + (hitDiff / 4));
						if (NewHP >= getLevelForXP(playerXP[playerHitpoints])) {
							NewHP = getLevelForXP(playerXP[playerHitpoints]);
						}
						sendMessage("You drain the enemies health.");
						refreshSkills();
					}
				}
				if (MagicHandler.itFreezes) {
					sendMessage("" + getFrozenMessage(MagicHandler.spellID)
							+ "");
					if (server.playerHandler.players[index].freezeTimer <= 0) {
						server.playerHandler.players[index].freezeTimer = getFreezeTimer(MagicHandler.spellID);
					}
				}
				if (MagicHandler.itReducesAttack) {
					if (misc.random(2) == 1) {
						if (server.playerHandler.players[index].reducedAttack <= 0) {
							sendMessage("You reduce the enemies attack power.");

							server.playerHandler.players[i].playerLevel[playerAttack] -= (hitDiff / 2);
							if ((server.playerHandler.players[i].playerLevel[playerAttack] - (hitDiff / 2)) < server.playerHandler.players[i].playerLevel[playerAttack]) {
								server.playerHandler.players[i].playerLevel[playerAttack] = 0;
							}
							server.playerHandler.players[i].reducedAttack = getAttackTimer(MagicHandler.spellID);
						}
					}
				}
				if (MagicHandler.itPoisons) {
					if (misc.random(2) == 1) {
						if (server.playerHandler.players[i].poisonTimer <= 0) {
							sendMessage("You poison the enemy.");
							server.playerHandler.players[i].poisonTimer = 120;
							server.playerHandler.players[i].poisonDmg = true;
							server.playerHandler.players[i].applyPoisonToMe();
						}
					}
				}
				if (rune1 != -1) // fixed delete bug -bakatool
					deleteItem(rune1, getItemSlot(rune1), rune1Am);
				if (rune2 != -1) // fixed delete bug -bakatool
					deleteItem(rune2, getItemSlot(rune2), rune2Am);
				if (rune3 != -1) // //fixed delete bug -bakatool
					deleteItem(rune3, getItemSlot(rune3), rune3Am);
				if (rune4 != -1) // //fixed delete bug -bakatool
					deleteItem(rune4, getItemSlot(rune4), rune4Am);
				addSkillXP((spellXP), 6);
				addSkillXP((spellXP / 2), 3);
				if (hitDiff != 0) {
					server.playerHandler.players[index].dealDamage(hitDiff);
					server.playerHandler.players[index].hitDiff = hitDiff;
					server.playerHandler.players[index].updateRequired = true;
					server.playerHandler.players[index].hitUpdateRequired = true;
				}
			}
		} catch (Exception e) {
		}
	}

	public void appendToBanned(String player) {

		BufferedWriter bw = null;

		try {
			bw = new BufferedWriter(
					new FileWriter("data/bannedusers.dat", true));
			bw.write(player);
			bw.newLine();
			bw.flush();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			if (bw != null) {
				try {
					bw.close();
				} catch (IOException ioe2) {
					sendMessage("Error banning user!");
				}
			}
		}

	}

	public boolean AreXItemsInBag(int ItemID, int Amount) {
		int ItemCount = 0;

		for (int pItem : playerItems) {
			if ((pItem - 1) == ItemID) {
				ItemCount++;
			}
			if (ItemCount == Amount) {
				return true;
			}
		}
		return false;
	}

	// pk: 2726 9193
	public boolean Attack() {
		if (!(AttackingOn > 0)
				|| !(AttackingOn < server.playerHandler.players.length)) {
			ResetAttack();
			return false;
		}

		if (playerId < 1) {
			sendMessage("Error: Your player id is invalid. Please try again or logout and back in");
		}

		if ((AttackingOn > 0) && !server.pking) {
			sendMessage("Pking has been disabled ask admin to type ::enablepk -bakatool");
			ResetAttack();
			return false;
		}

		boolean UseBow = false;
		if (playerEquipment[playerWeapon] == 4212) {
			UseBow = true;
		}
		if (playerEquipment[playerWeapon] == 2883) {
			UseBow = true;
		}
		if (playerEquipment[playerWeapon] == 6082) {
			UseBow = true;
		}
		if (playerEquipment[playerWeapon] == 4214) {
			UseBow = true;
		}
		if (playerEquipment[playerWeapon] == 767) {
			UseBow = true;
		}
		if (playerEquipment[playerWeapon] == 837) {
			UseBow = true;
		}
		if (playerEquipment[playerWeapon] == 861) {
			UseBow = true;
		}
		if (playerEquipment[playerWeapon] == 4734) {
			UseBow = true;
		}
		if (playerEquipment[playerWeapon] == 4214) {
			UseBow = true;
		}
		if (playerEquipment[playerWeapon] == 861) {
			UseBow = true;
		}
		if (playerEquipment[playerWeapon] == 859) {
			UseBow = true;
		}

		if (UseBow) {
			if (!duelRule[0]) {
				teleportToX = absX;
				teleportToY = absY;
				CheckArrows();
				CalculateRange();
				hitDiff = misc.random(playerMaxHit);
			} else {
				sendMessage("You can't range in this duel!");
				return false;
			}
		}

		if (!UseBow && duelRule[1]) {
			sendMessage("You can't melee in this duel!");
			ResetAttack();
			return false;
		}

		int EnemyX = PlayerHandler.players[AttackingOn].absX;
		int EnemyY = PlayerHandler.players[AttackingOn].absY;
		int EnemyHP = PlayerHandler.players[AttackingOn].playerLevel[playerHitpoints];
		int EnemyHPExp = PlayerHandler.players[AttackingOn].playerXP[playerHitpoints];
		client AttackingOn2 = (client) server.playerHandler.players[AttackingOn];
		TurnPlayerTo(EnemyX, EnemyY);
		updateRequired = true;
		appearanceUpdateRequired = true;
		if (PlayerHandler.players[AttackingOn].playerEquipment[playerRing] == 2570) {
			// RingOfLife = true;
		}
		int hitDiff = 0;
		int aBonus = 0;
		int rand_att = misc.random(playerLevel[0]);
		int rand_def = (int) (0.65 * misc.random(AttackingOn2.playerLevel[1]));
		if (FightType == 1)
			aBonus += (int) (playerBonus[1] / 20);
		int random_u = misc.random(playerBonus[1] + aBonus) * 2;
		int dBonus = 0;
		if (AttackingOn2.FightType == 4)
			dBonus += (int) (AttackingOn2.playerBonus[6] / 20);
		int random_def = misc.random(AttackingOn2.playerBonus[6] + dBonus);
		if ((random_u >= random_def) && (rand_att > rand_def)) {
			hitDiff = misc.random(playerMaxHit);
		} else {
			hitDiff = 0;
		}
		if (FightType == 2)
			hitDiff = (int) (hitDiff * 1.20);
		long thisAttack = System.currentTimeMillis();
		if (GoodDistance(EnemyX, EnemyY, absX, absY, 1) == true) {
			if (thisAttack - lastAttack >= 2000) {
				inCombat = true;
				lastCombat = System.currentTimeMillis();
				if (isInWilderness(EnemyX, EnemyY, 1)
						|| (duelFight && (AttackingOn == duel_with))
						|| ((matchId == handler.players[AttackingOn].matchId) && (matchId >= 0))) {
					if (duelFight && (AttackingOn == duel_with)) {
						enemyX = PlayerHandler.players[AttackingOn].absX;
						enemyY = PlayerHandler.players[AttackingOn].absY;
					}
					if (handler.players[AttackingOn].matchLives < 1) {
						sendMessage("This player has been eliminated");
						return false;
					}
					if (PlayerHandler.players[AttackingOn].deathStage > 0) {
						ResetAttack();
						sendMessage("That player is dead!");
					} else {
						if (!duelFight && inRange(EnemyX, EnemyY)) {
							if (thisAttack - lastAttack >= 4000)
								((client) PlayerHandler.players[AttackingOn])
										.sendMessage("You are under attack!");
							PlayerHandler.players[AttackingOn]
									.resetWalkingQueue();
							PlayerHandler.players[AttackingOn].hits++;
							PlayerHandler.players[AttackingOn].fighting = true;
							PlayerHandler.players[AttackingOn].fightId = localId;
							PlayerHandler.players[AttackingOn].IsAttacking = true;
							((client) PlayerHandler.players[AttackingOn])
									.Attack();
							fightId = AttackingOn;
							fighting = true;
							resetWalkingQueue();
						}
						if (playerEquipment[playerWeapon] == 4151) {
							animation(341, EnemyY, EnemyX);
						}
						actionAmount++;
						setAnimation(playerSEA);
						PlayerHandler.players[AttackingOn].hitUpdateRequired = true;
						PlayerHandler.players[AttackingOn].updateRequired = true;
						PlayerHandler.players[AttackingOn].appearanceUpdateRequired = true;
						if ((EnemyHP - hitDiff) < 0) {
							hitDiff = EnemyHP;
						}
						PlayerHandler.players[AttackingOn].dealDamage(hitDiff);
						PlayerHandler.players[AttackingOn].hitDiff = hitDiff;
						PlayerHandler.players[AttackingOn].killers[localId] += hitDiff;
						attackTimer = 7;
						lastAttack = System.currentTimeMillis();
						// sendMessage("You hit " + hitDiff + ", time=" +
						// lastAttack);
					}
					return true;
				} else {
					sendMessage("This player is not in a Pking Area/Match.");
					ResetAttack();
				}
			}
		}

		if (GoodDistance(EnemyX, EnemyY, absX, absY, 1) == false) {
			if (thisAttack - lastAttack >= 2000) {
				if ((isInWilderness(EnemyX, EnemyY, 1) == true)
						|| (IsInCW(EnemyX, EnemyY) == true)
						|| (IsInTz(EnemyX, EnemyY) == true)) {
					if (PlayerHandler.players[AttackingOn].deathStage > 0) {
						ResetAttack();
					} else if (playerEquipment[playerWeapon] == 4675) {
						TurnPlayerTo(EnemyX, EnemyY);
						PlayerHandler.players[AttackingOn].hitDiff = 0 + misc
								.random(22);
						animation(369, EnemyY, EnemyX);
						AnimationReset = true;
						updateRequired = true;
						appearanceUpdateRequired = true;
						actionAmount++;
						teleportToX = absX;
						teleportToY = absY;
						actionTimer = 15;
						setAnimation(1979);
						AnimationReset = true;
						PlayerHandler.players[AttackingOn].hitUpdateRequired = true;
						PlayerHandler.players[AttackingOn].updateRequired = true;
						PlayerHandler.players[AttackingOn].appearanceUpdateRequired = true;
						if ((EnemyHP - hitDiff) <= 0) {
							hitDiff = EnemyHP;
							// animation(437, absY, absX);
							attackPlayersPrayer(15, 2);
						}
					} else if (UseBow) {
						actionAmount++;
						setAnimation(playerSEA);
						PlayerHandler.players[AttackingOn].hitUpdateRequired = true;
						PlayerHandler.players[AttackingOn].updateRequired = true;
						PlayerHandler.players[AttackingOn].appearanceUpdateRequired = true;
						if ((EnemyHP - hitDiff) < 0) {
							hitDiff = EnemyHP;
						}
						PlayerHandler.players[AttackingOn].dealDamage(hitDiff);
						PlayerHandler.players[AttackingOn].hitDiff = hitDiff;
						attackTimer = 7;
						lastAttack = System.currentTimeMillis();
					}
					return true;
				} else {
					sendMessage("This player is not in a Pking Area/Match.");
					ResetAttack();
				}
			}
		}
		return false;
	}

	public boolean AttackNPC() {

		int EnemyX = server.npcHandler.npcs[attacknpc].absX;
		int EnemyY = server.npcHandler.npcs[attacknpc].absY;
		int EnemyHP = server.npcHandler.npcs[attacknpc].HP;
		int hitDiff = 0;
		if (EnemyHP < 1) {
			sendMessage("You can't attack that monster!");
			return false;
		}
		int type = server.npcHandler.npcs[attacknpc].npcType;
		int[] staffs = { 2415, 2416, 2417 };
		int[] arrowIds = { 882, 884, 886, 888, 890, 892 };
		int[] arrowGfx = { 10, 9, 11, 12, 13, 15 };
		int[] prem = { 1643, 158, 49, 1613 };
		for (int element : prem) {
			if ((element == type) && !premium) {
				resetPos();
				return false;
			}
		}
		if (type == 1125) {
			if (combatLevel < 70) {
				sendMessage("You must be level 70 or higher to attack Dad");
				ResetAttackNPC();
				return false;
			}
		}
		if ((type == 110) || (type == 936)) {
			if (!playerHasItem(1543)) {
				resetPos();
				ResetAttackNPC();
				return false;
			}
		}
		if ((type == 221) || (type == 1961)) {
			if (!playerHasItem(1544)) {
				resetPos();
				ResetAttackNPC();
				return false;
			}
		}
		if (((type == 941) || (type == 55)) && !premium) {
			resetPos();
			return false;
		}
		for (int element : staffs) {
			if ((playerEquipment[playerWeapon] == element)
					&& (autocast_spellIndex >= 0)) {
				if (System.currentTimeMillis() - lastAttack < coolDown[coolDownGroup[autocast_spellIndex]]) {
					return false;
				}
				inCombat = true;
				lastCombat = System.currentTimeMillis();
				lastAttack = System.currentTimeMillis();
				if (playerLevel[6] >= requiredLevel[autocast_spellIndex]) {
					int roll = misc.random(100);
					int hit = misc.random(baseDamage[autocast_spellIndex]
							+ playerBonus[11]);
					hitDiff = hit;
					setAnimation(1979);
					updateRequired = true;
					// AnimationReset = true;
					// teleportToX = absX;
					// teleportToY = absY;
					resetWalkingQueue();
					if (ancientType[autocast_spellIndex] == 3) {
						// coolDown[coolDownGroup[autocast_spellIndex]] = 35;
						server.npcHandler.npcs[attacknpc].effects[0] = 15;
						stillgfx(369, EnemyY, EnemyX);
					} else if (ancientType[autocast_spellIndex] == 2) {
						stillgfx(377, EnemyY, EnemyX);
						// coolDown[coolDownGroup[autocast_spellIndex]] = 12;
						currentHealth += (int) (hit / 2);
						if (currentHealth > playerLevel[playerHitpoints])
							currentHealth = playerLevel[playerHitpoints];
					} else
						animation(78, EnemyY, EnemyX);
				} else {
					sendMessage("You need a magic level of "
							+ requiredLevel[autocast_spellIndex]);
				}
				// coolDown[coolDownGroup[autocast_spellIndex]] = 12;
				TurnPlayerTo(EnemyX, EnemyY);
				addSkillXP(90 * hitDiff, playerMagic);
				addSkillXP(hitDiff * CombatExpRate, playerHitpoints);
				teleportToX = absX;
				teleportToY = absY;
				server.npcHandler.npcs[attacknpc].StartKilling = localId;
				server.npcHandler.npcs[attacknpc].RandomWalk = false;
				server.npcHandler.npcs[attacknpc].IsUnderAttack = true;
				server.npcHandler.npcs[attacknpc].hitDiff = hitDiff;
				server.npcHandler.npcs[attacknpc].Killing[playerId] += hitDiff;
				server.npcHandler.npcs[attacknpc].updateRequired = true;
				server.npcHandler.npcs[attacknpc].hitUpdateRequired = true;
				server.npcHandler.npcs[attacknpc].hit = true;
				// server.npcHandler.npcs[attacknpc].TurnNPCTo(absX,absY);
				// server.npcHandler.npcs[attacknpc].FocusUpdateRequired = true;
				return true;
			}
		}
		long thisTime = System.currentTimeMillis();

		hitDiff = misc.random(playerMaxHit);
		if (FightType == 2)
			hitDiff = (int) (hitDiff * 1.20);
		boolean UseBow = false;

		for (int i = 0; i < shortbow.length; i++) {
			if ((playerEquipment[playerWeapon] == shortbow[i])
					|| (playerEquipment[playerWeapon] == longbow[i])) {
				UseBow = true;
				break;
			}
		}
		int arrowgfx = 10;
		for (int i1 = 0; i1 < arrowIds.length; i1++) {
			if (playerEquipment[playerArrows] == arrowIds[i1]) {
				arrowgfx = arrowGfx[i1];
			}
		}

		if (UseBow && (thisTime - lastAttack >= 1800)) {
			teleportToX = absX;
			teleportToY = absY;
			CalculateRange();
			hitDiff = misc.random(maxRangeHit());
			if (DeleteArrow()) {
				int offsetX = (absY - EnemyY) * -1;
				int offsetY = (absX - EnemyX) * -1;
				for (int a = 0; a < handler.maxPlayers; a++) {
					client temp = (client) handler.players[a];
					if ((temp != null) && (temp.absX > 0) && !temp.disconnected
							&& (Math.abs(absX - temp.absX) < 60)
							&& (Math.abs(absY - temp.absY) < 60)) {
						temp.createProjectile(absY, absX, offsetY, offsetX, 50,
								90, arrowgfx, 43, 35, attacknpc + 1);
					}
				}
				server.npcHandler.npcs[attacknpc].hitDiff = hitDiff;
				server.npcHandler.npcs[attacknpc].Killing[playerId] += hitDiff;
				server.npcHandler.npcs[attacknpc].updateRequired = true;
				server.npcHandler.npcs[attacknpc].hitUpdateRequired = true;
				server.npcHandler.npcs[attacknpc].hit = true;
				if ((hitDiff == 0)
						&& (server.npcHandler.npcs[attacknpc].npcType == 941)) {
					server.npcHandler.npcs[attacknpc].animNumber = 89;
					server.npcHandler.npcs[attacknpc].animUpdateRequired = true;
					server.npcHandler.npcs[attacknpc].updateRequired = true;
				}
				updateRequired = true;
				appearanceUpdateRequired = true;
			} else {
				ResetAttackNPC();
				sendMessage("You're out of arrows!");
				return false;
			}
		}
		if ((playerEquipment[playerWeapon] == 4151)
				&& (thisTime - lastAttack >= 1600)) {
			inCombat = true;
			lastCombat = System.currentTimeMillis();
		} else if (thisTime - lastAttack >= 2000) {
			inCombat = true;
			lastCombat = System.currentTimeMillis();
		} else {
			return false;
		}
		if (UseBow || (GoodDistance(EnemyX, EnemyY, absX, absY, 1) == true)) {
			if (server.npcHandler.npcs[attacknpc].IsDead == true) {
				ResetAttackNPC();
			} else {
				if (playerEquipment[playerWeapon] == 4151) {
					animation(341, EnemyY, EnemyX);
				}
				TurnPlayerTo(EnemyX, EnemyY);
				updateRequired = true;
				appearanceUpdateRequired = true;
				actionAmount++;
				setAnimation(playerSEA);
				if ((EnemyHP - hitDiff) < 0) {
					hitDiff = EnemyHP;
				}
				server.npcHandler.npcs[attacknpc].hitDiff = hitDiff;
				server.npcHandler.npcs[attacknpc].Killing[playerId] += hitDiff;
				server.npcHandler.npcs[attacknpc].updateRequired = true;
				server.npcHandler.npcs[attacknpc].hitUpdateRequired = true;
				server.npcHandler.npcs[attacknpc].hit = true;
				if ((hitDiff == 0)
						&& (server.npcHandler.npcs[attacknpc].npcType == 941)) {
					server.npcHandler.npcs[attacknpc].animNumber = 89;
					server.npcHandler.npcs[attacknpc].animUpdateRequired = true;
					server.npcHandler.npcs[attacknpc].updateRequired = true;
				}
				attackedNpc = true;
				attackedNpcId = attacknpc;
				double TotalExp = 0;
				if (!UseBow)
					animationReset = System.currentTimeMillis() + 1200;
				if (server.npcHandler.npcs[attacknpc].npcType != 1472) {
					if (UseBow) {
						TotalExp = (double) (60 * hitDiff * 2); // added *2 for
						// faster
						// leveling
						// -bakatool
						TotalExp = (double) (TotalExp * CombatExpRate);
						addSkillXP((int) (TotalExp), 4);
					} else if (FightType != 3) {
						TotalExp = (double) (60 * hitDiff * 2); // added *2 for
						// faster
						// leveling
						// -bakatool
						TotalExp = (double) (TotalExp * CombatExpRate);
						addSkillXP((int) (TotalExp), SkillID);
					} else {
						TotalExp = (double) (60 * hitDiff * 2); // added *2 for
						// faster
						// leveling
						// -bakatool2
						TotalExp = (double) (TotalExp * CombatExpRate);
						addSkillXP((int) (TotalExp), playerAttack);
						addSkillXP((int) (TotalExp), playerDefence);
						addSkillXP((int) (TotalExp), playerStrength);
					}
					TotalExp = (double) (20 * hitDiff * 2); // added *2 for
					// faster leveling
					// -bakatool
					TotalExp = (double) (TotalExp * CombatExpRate);
					addSkillXP((int) (TotalExp), playerHitpoints);
					attackTimer = 7;
					if (debug)
						sendMessage("hitDiff=" + hitDiff + ", elapsed="
								+ (thisTime - lastAttack));
					lastAttack = System.currentTimeMillis();
				}
				return true;

			}
		}
		return false;
	}

	public void attackNPCSWithin(int gfx, int maxDamage, int range) {
		for (int i = 0; i <= server.npcHandler.maxNPCSpawns; i++) {
			if (server.npcHandler.npcs[i] != null) {
				if ((distanceToPoint(server.npcHandler.npcs[i].absX,
						server.npcHandler.npcs[i].absY) <= range)
						&& !server.npcHandler.npcs[i].IsDead) {
					int damage = misc.random(maxDamage);

					animation(gfx, server.npcHandler.npcs[i].absY,
							server.npcHandler.npcs[i].absX);
					if (server.npcHandler.npcs[i].HP - damage < 0) {
						damage = server.npcHandler.npcs[i].HP;
					}
					server.npcHandler.npcs[i].StartKilling = playerId;
					server.npcHandler.npcs[i].RandomWalk = false;
					server.npcHandler.npcs[i].hitDiff = damage;
					server.npcHandler.npcs[i].updateRequired = true;
					server.npcHandler.npcs[i].hitUpdateRequired = true;
					server.npcHandler.npcs[i].hit = true;
				}
			}
		}
	}

	public void attackPlayersPrayer(int maxDamage, int range) {
		// for (Player p : server.playerHandler.players) {
		// Linux (java 1.4.2-compatible) change - Devolution
		for (Player p : server.playerHandler.players) {
			if (p != null) {
				client person = (client) p;

				if (person.playerName != null) {
					if ((person.distanceToPoint(absX, absY) <= range)
							&& (person.playerId != playerId)) {
						int damage = misc.random(maxDamage);

						if (person.playerLevel[3] - damage < 0) {
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

	public void attackPlayersWithin(int gfx, int maxDamage, int range) {
		// for (Player p : server.playerHandler.players) {
		// Linux (java 1.4.2-compatible) change - Devolution
		for (Player p : server.playerHandler.players) {
			if (p != null) {
				client person = (client) p;

				if (person.playerName != null) {
					if ((person.distanceToPoint(absX, absY) <= range)
							&& (person.playerId != playerId)) {
						int damage = misc.random(maxDamage);

						person.animation(gfx, person.absY, person.absX);
						if (person.playerLevel[3] - damage < 0) {
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

	public boolean Attackrange() {
		client q = (client) server.playerHandler.players[AttackingOn];
		int EnemyX = PlayerHandler.players[AttackingOn].absX;
		int EnemyY = PlayerHandler.players[AttackingOn].absY;
		int EnemyHP = PlayerHandler.players[AttackingOn].playerLevel[playerHitpoints];
		int EnemyHPExp = PlayerHandler.players[AttackingOn].playerXP[playerHitpoints];
		boolean RingOfLife = false;

		if (PlayerHandler.players[AttackingOn].playerEquipment[playerRing] == 2570) {
			RingOfLife = true;
		}
		int hitDiff = 0;

		hitDiff = misc.random(playerMaxHit);

		if (GoodDistance(EnemyX, EnemyY, absX, absY, 20) == true) {
			teleportToX = absX;
			teleportToY = absY;
			if (actionTimer == 0) {
				if (isInWilderness(EnemyX, EnemyY, 1) == true) {
					if (false) {
					} else {
						if (PlayerHandler.players[AttackingOn].deathStage > 0) {
							ResetAttack();
						} else {
							actionAmount++;
							setAnimation(playerSEA);
							PlayerHandler.players[AttackingOn].hitUpdateRequired = true;
							PlayerHandler.players[AttackingOn].updateRequired = true;
							PlayerHandler.players[AttackingOn].appearanceUpdateRequired = true;
							hitDiff = EnemyHP;

							PlayerHandler.players[AttackingOn].hitDiff = hitDiff;
							actionTimer = 7;
						}
					}
					return true;
				} else {
					sendMessage("This player is not in the Wilderness.");
					ResetAttack();
				}
			}
		}
		return false;
	}

	public int average(int[] array) {
		int total = 0;
		for (int element : array) {
			total += element;
		}
		int average = (int) (total / array.length);
		println("total=" + total + ", average=" + average);
		return average;
	}

	public boolean bankItem(int itemID, int fromSlot, int amount) {
		if (!IsBanking)
			return false;
		if (playerItemsN[fromSlot] <= 0) {
			return false;
		}
		if (!Item.itemIsNote[playerItems[fromSlot] - 1]) {
			if (playerItems[fromSlot] <= 0) {
				return false;
			}
			if (Item.itemStackable[playerItems[fromSlot] - 1]
					|| (playerItemsN[fromSlot] > 1)) {
				int toBankSlot = 0;
				boolean alreadyInBank = false;

				for (int i = 0; i < playerBankSize; i++) {
					if (bankItems[i] == playerItems[fromSlot]) {
						if (playerItemsN[fromSlot] < amount) {
							amount = playerItemsN[fromSlot];
						}
						alreadyInBank = true;
						toBankSlot = i;
						i = playerBankSize + 1;
					}
				}

				if (!alreadyInBank && (freeBankSlots() > 0)) {
					for (int i = 0; i < playerBankSize; i++) {
						if (bankItems[i] <= 0) {
							toBankSlot = i;
							i = playerBankSize + 1;
						}
					}
					bankItems[toBankSlot] = playerItems[fromSlot];
					if (playerItemsN[fromSlot] < amount) {
						amount = playerItemsN[fromSlot];
					}
					if (((bankItemsN[toBankSlot] + amount) <= maxItemAmount)
							&& ((bankItemsN[toBankSlot] + amount) > -1)) {
						bankItemsN[toBankSlot] += amount;
					} else {
						sendMessage("Bank full!");
						return false;
					}
					deleteItem((playerItems[fromSlot] - 1), fromSlot, amount);
					resetItems(5064);
					resetBank();
					return true;
				} else if (alreadyInBank) {
					if (((bankItemsN[toBankSlot] + amount) <= maxItemAmount)
							&& ((bankItemsN[toBankSlot] + amount) > -1)) {
						bankItemsN[toBankSlot] += amount;
					} else {
						sendMessage("Bank full!");
						return false;
					}
					deleteItem((playerItems[fromSlot] - 1), fromSlot, amount);
					resetItems(5064);
					resetBank();
					return true;
				} else {
					sendMessage("Bank full!");
					return false;
				}
			} else {
				itemID = playerItems[fromSlot];
				int toBankSlot = 0;
				boolean alreadyInBank = false;

				for (int i = 0; i < playerBankSize; i++) {
					if (bankItems[i] == playerItems[fromSlot]) {
						alreadyInBank = true;
						toBankSlot = i;
						i = playerBankSize + 1;
					}
				}
				if (!alreadyInBank && (freeBankSlots() > 0)) {
					for (int i = 0; i < playerBankSize; i++) {
						if (bankItems[i] <= 0) {
							toBankSlot = i;
							i = playerBankSize + 1;
						}
					}
					int firstPossibleSlot = 0;
					boolean itemExists = false;

					while (amount > 0) {
						itemExists = false;
						for (int i = firstPossibleSlot; i < playerItems.length; i++) {
							if ((playerItems[i]) == itemID) {
								firstPossibleSlot = i;
								itemExists = true;
								i = 30;
							}
						}
						if (itemExists) {
							bankItems[toBankSlot] = playerItems[firstPossibleSlot];
							bankItemsN[toBankSlot] += 1;
							deleteItem((playerItems[firstPossibleSlot] - 1),
									firstPossibleSlot, 1);
							amount--;
						} else {
							amount = 0;
						}
					}
					resetItems(5064);
					resetBank();
					return true;
				} else if (alreadyInBank) {
					int firstPossibleSlot = 0;
					boolean itemExists = false;

					while (amount > 0) {
						itemExists = false;
						for (int i = firstPossibleSlot; i < playerItems.length; i++) {
							if ((playerItems[i]) == itemID) {
								firstPossibleSlot = i;
								itemExists = true;
								i = 30;
							}
						}
						if (itemExists) {
							bankItemsN[toBankSlot] += 1;
							deleteItem((playerItems[firstPossibleSlot] - 1),
									firstPossibleSlot, 1);
							amount--;
						} else {
							amount = 0;
						}
					}
					resetItems(5064);
					resetBank();
					return true;
				} else {
					sendMessage("Bank full!");
					return false;
				}
			}
		} else if (Item.itemIsNote[playerItems[fromSlot] - 1]
				&& !Item.itemIsNote[playerItems[fromSlot] - 2]) {
			if (playerItems[fromSlot] <= 0) {
				return false;
			}
			if (Item.itemStackable[playerItems[fromSlot] - 1]
					|| (playerItemsN[fromSlot] > 1)) {
				int toBankSlot = 0;
				boolean alreadyInBank = false;

				for (int i = 0; i < playerBankSize; i++) {
					if (bankItems[i] == (playerItems[fromSlot] - 1)) {
						if (playerItemsN[fromSlot] < amount) {
							amount = playerItemsN[fromSlot];
						}
						alreadyInBank = true;
						toBankSlot = i;
						i = playerBankSize + 1;
					}
				}

				if (!alreadyInBank && (freeBankSlots() > 0)) {
					for (int i = 0; i < playerBankSize; i++) {
						if (bankItems[i] <= 0) {
							toBankSlot = i;
							i = playerBankSize + 1;
						}
					}
					bankItems[toBankSlot] = (playerItems[fromSlot] - 1);
					if (playerItemsN[fromSlot] < amount) {
						amount = playerItemsN[fromSlot];
					}
					if (((bankItemsN[toBankSlot] + amount) <= maxItemAmount)
							&& ((bankItemsN[toBankSlot] + amount) > -1)) {
						bankItemsN[toBankSlot] += amount;
					} else {
						return false;
					}
					deleteItem((playerItems[fromSlot] - 1), fromSlot, amount);
					resetItems(5064);
					resetBank();
					return true;
				} else if (alreadyInBank) {
					if (((bankItemsN[toBankSlot] + amount) <= maxItemAmount)
							&& ((bankItemsN[toBankSlot] + amount) > -1)) {
						bankItemsN[toBankSlot] += amount;
					} else {
						return false;
					}
					deleteItem((playerItems[fromSlot] - 1), fromSlot, amount);
					resetItems(5064);
					resetBank();
					return true;
				} else {
					sendMessage("Bank full!");
					return false;
				}
			} else {
				itemID = playerItems[fromSlot];
				int toBankSlot = 0;
				boolean alreadyInBank = false;

				for (int i = 0; i < playerBankSize; i++) {
					if (bankItems[i] == (playerItems[fromSlot] - 1)) {
						alreadyInBank = true;
						toBankSlot = i;
						i = playerBankSize + 1;
					}
				}
				if (!alreadyInBank && (freeBankSlots() > 0)) {
					for (int i = 0; i < playerBankSize; i++) {
						if (bankItems[i] <= 0) {
							toBankSlot = i;
							i = playerBankSize + 1;
						}
					}
					int firstPossibleSlot = 0;
					boolean itemExists = false;

					while (amount > 0) {
						itemExists = false;
						for (int i = firstPossibleSlot; i < playerItems.length; i++) {
							if ((playerItems[i]) == itemID) {
								firstPossibleSlot = i;
								itemExists = true;
								i = 30;
							}
						}
						if (itemExists) {
							bankItems[toBankSlot] = (playerItems[firstPossibleSlot] - 1);
							bankItemsN[toBankSlot] += 1;
							deleteItem((playerItems[firstPossibleSlot] - 1),
									firstPossibleSlot, 1);
							amount--;
						} else {
							amount = 0;
						}
					}
					resetItems(5064);
					resetBank();
					return true;
				} else if (alreadyInBank) {
					int firstPossibleSlot = 0;
					boolean itemExists = false;

					while (amount > 0) {
						itemExists = false;
						for (int i = firstPossibleSlot; i < playerItems.length; i++) {
							if ((playerItems[i]) == itemID) {
								firstPossibleSlot = i;
								itemExists = true;
								i = 30;
							}
						}
						if (itemExists) {
							bankItemsN[toBankSlot] += 1;
							deleteItem((playerItems[firstPossibleSlot] - 1),
									firstPossibleSlot, 1);
							amount--;
						} else {
							amount = 0;
						}
					}
					resetItems(5064);
					resetBank();
					return true;
				} else {
					sendMessage("Bank full!");
					return false;
				}
			}
		} else {
			sendMessage("Item not supported " + (playerItems[fromSlot] - 1));
			return false;
		}
	}

	public boolean banned(String host) {
		try {
			BufferedReader in = new BufferedReader(new FileReader(
					"data/bannedusers.dat"));
			String data = null;

			while ((data = in.readLine()) != null) {
				if (host.equalsIgnoreCase(data)) {
					return true;
				}
			}
		} catch (IOException e) {
			System.out.println("Unable to ban player");
			server.logError(e.getMessage());
		}
		return false;
	}

	public void buryItem(int item, int slot) {
		if (duelRule[7] && inDuel && duelFight) {
			sendMessage("Food has been disabled for this duel");
			return;
		}
		boolean used = true;
		if (playerHasItem(item)) {
			switch (item) {
			case 526:
				prayerMessage(70);
				break;
			case 532:
				prayerMessage(115);
				break;
			case 3125:
				prayerMessage(300);
				break;
			case 536:
				prayerMessage(450);
				break;
			case 315:
				setAnimation(0x33D);
				animationReset = System.currentTimeMillis() + 750;
				currentHealth += 5;
				if (currentHealth > playerLevel[playerHitpoints])
					currentHealth = playerLevel[playerHitpoints];
				sendMessage("You eat the shrimps");
				break;
			case 379:
				setAnimation(0x33D);
				animationReset = System.currentTimeMillis() + 750;
				currentHealth += 12;
				if (currentHealth > playerLevel[playerHitpoints])
					currentHealth = playerLevel[playerHitpoints];
				sendMessage("You eat the lobster");
				break;
			case 385:
				setAnimation(0x33D);
				animationReset = System.currentTimeMillis() + 750;
				currentHealth += 75;
				if (currentHealth > playerLevel[playerHitpoints])
					currentHealth = playerLevel[playerHitpoints];
				sendMessage("You eat the Shark!");
				break;
			case 391:
				setAnimation(0x33D);
				animationReset = System.currentTimeMillis() + 750;
				currentHealth += 100;
				if (currentHealth > playerLevel[playerHitpoints])
					currentHealth = playerLevel[playerHitpoints];
				sendMessage("You eat the Manta Ray!");
				break;
			default:
				used = false;
				break;
			}
		}
		if (used)
			deleteItem(item, slot, 1);
	}

	public boolean buyItem(int itemID, int fromSlot, int amount) {
		if ((amount > 0)
				&& (itemID == (server.shopHandler.ShopItems[MyShopID][fromSlot] - 1))) {
			if (amount > server.shopHandler.ShopItemsN[MyShopID][fromSlot]) {
				amount = server.shopHandler.ShopItemsN[MyShopID][fromSlot];
			}
			double ShopValue;
			double TotPrice;
			int TotPrice2;
			int Overstock;
			int Slot = 0;
			if (!canUse(itemID)) {
				sendMessage("You must be a premium member to buy this item");
				sendMessage("Visit moparscape.org/smf to subscribe");
				return false;
			}
			for (int i = amount; i > 0; i--) {
				TotPrice2 = (int) Math.floor(GetItemShopValue(itemID, 0,
						fromSlot));
				Slot = GetItemSlot(995);
				if (Slot == -1) {
					sendMessage("You don't have enough coins.");
					break;
				}
				if (playerItemsN[Slot] >= TotPrice2) {
					if (freeSlots() > 0) {
						deleteItem(995, GetItemSlot(995), TotPrice2);
						addItem(itemID, 1);
						server.shopHandler.ShopItemsN[MyShopID][fromSlot] -= 1;
						server.shopHandler.ShopItemsDelay[MyShopID][fromSlot] = 0;
						if ((fromSlot + 1) > server.shopHandler.ShopItemsStandard[MyShopID]) {
							server.shopHandler.ShopItems[MyShopID][fromSlot] = 0;
						}
					} else {
						sendMessage("Not enough space in your inventory.");
						break;
					}
				} else {
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

	public void CalculateMaxHit() {
		double MaxHit = 0;
		int StrBonus = playerBonus[10]; // Strength Bonus
		int Strength = playerLevel[playerStrength]; // Strength
		int RngBonus = playerBonus[4]; // Ranged Bonus
		int Range = playerLevel[playerRanged]; // Ranged

		if ((FightType == 1) || (FightType == 4)) {
			// Accurate & Defensive
			MaxHit += (double) (1.05 + (double) ((double) (StrBonus * Strength) * 0.00175));
		} else if (FightType == 2) {
			// Aggresive
			MaxHit += (double) (1.35 + (double) ((double) (StrBonus * Strength) * 0.00175));
		} else if (FightType == 3) {
			// Controlled
			MaxHit += (double) (1.15 + (double) ((double) (StrBonus * Strength) * 0.00175));
		}
		MaxHit += (double) (Strength * 0.1);
		/*
		 * if (StrPotion == 1) { // Strength Potion MaxHit += (double) (Strength *
		 * 0.0014); } else if (StrPotion == 2) { // Super Strength Potion MaxHit +=
		 * (double) (Strength * 0.0205); }
		 */
		if (StrPrayer == 1) {
			// Burst Of Strength
			MaxHit += (double) (Strength * 0.005);
		} else if (StrPrayer == 2) {
			// Super Human Strength
			MaxHit += (double) (Strength * 0.01);
		} else if (StrPrayer == 3) {
			// Ultimate Strength
			MaxHit += (double) (Strength * 0.015);
		}
		if ((FightType == 5) || (FightType == 6)) {
			// Accurate and Longranged
			MaxHit += (double) (1.05 + (double) ((double) (RngBonus * Range) * 0.00075));
		} else if (FightType == 7) {
			// Rapid
			MaxHit += (double) (1.35 + (double) ((double) (RngBonus) * 0.00025));
		}
		// MaxHit += (double) (Range * 0.03);
		playerMaxHit = (int) Math.floor(MaxHit);
	}

	public void CalculateRange() {
		double MaxHit = 0;
		int RangeBonus = playerBonus[3]; // Range Bonus
		int Range = playerLevel[4];
		{
			// Range
			MaxHit += (double) (1.05 + (double) ((double) (RangeBonus * Range) * 0.00175));
		}
		MaxHit += (double) (Range * 0.2);
		playerMaxHit = (int) Math.floor(MaxHit);
	}

	public boolean canUse(int id) {
		if (!premium && premiumItem(id)) {
			return false;
		}
		return true;
	}

	public void castleWarsScore() {
		String zammyColor;
		String saraColor;

		if (zammyScore > saraScore) {
			zammyColor = "@gre@";
			saraColor = "@red@";
		} else if (zammyScore < saraScore) {
			zammyColor = "@red@";
			saraColor = "@gre@";
		} else {
			zammyColor = "@yel@";
			saraColor = "@yel@";
		}
		sendQuest("@bla@.................Castle Wars Scores.............", 8144);
		clearQuestInterface();
		sendQuest("@or3@Zamorak: " + zammyColor + zammyScore, 8147);
		sendQuest("@or3@Saradomin: " + saraColor + saraScore, 8148);
		sendQuestSomething(8143);
		showInterface(8134);
		flushOutStream();
	}

	public void ChangeDoor(int ArrayID) {
		int objectID = server.objectHandler.ObjectOriID[ArrayID];
		int objectX = server.objectHandler.ObjectX[ArrayID];
		int objectY = server.objectHandler.ObjectY[ArrayID];
		int Face = server.objectHandler.ObjectFace[ArrayID];
		int Type = server.objectHandler.ObjectType[ArrayID];

		ReplaceObject(objectX, objectY, -1, -1, 0);
		switch (Type) {
		case 1:
			ReplaceObject(objectX, (objectY + 1), objectID, Face, 0);
			server.objectHandler.ObjectType[ArrayID] = 2;
			break;

		case 2:
			ReplaceObject(objectX, (objectY - 1), objectID, Face, 0);
			server.objectHandler.ObjectType[ArrayID] = 1;
			break;

		case 3:
			ReplaceObject((objectX + 1), objectY, objectID, Face, 0);
			server.objectHandler.ObjectType[ArrayID] = 4;
			break;

		case 4:
			ReplaceObject((objectX - 1), objectY, objectID, Face, 0);
			server.objectHandler.ObjectType[ArrayID] = 3;
			break;
		}
	}

	public void CheckArrows() {
		for (int k = 880; k < 893; k++) {

			if (playerEquipment[playerArrows] == k) {
				HasArrows = true;
			} else if (playerEquipment[playerWeapon] == 4214) {
				HasArrows = true;
			} else {
				HasArrows = false;
			}

		}
	}

	public void checkCooking(int id) {
		long now = System.currentTimeMillis();
		if (!playerHasItem(id))
			return;
		if (now - lastAction < 2000)
			return;
		int[] fish = { 317, 377 };
		int[] cooked = { 315, 379 };
		int[] burned = { 323, 381 };
		int exp = 0, ran = 0, index = 0;
		for (int i = 0; i < fish.length; i++) {
			if (id == fish[i]) {
				index = i;
			}
		}

		switch (id) {
		case 317:
			exp = 40;
			ran = 30 - playerLevel[playerCooking];
			break;
		case 377:
			exp = 100;
			ran = 70 - playerLevel[playerCooking];
			break;
		}
		if (ran < 0)
			ran = 0;
		boolean success = true;
		if (misc.random(100) < ran) {
			success = false;
		}
		if (exp > 0) {
			deleteItem(id, 1);
			if (success) {
				addItem(cooked[index], 1);
				sendMessage("You cook the " + getItemName(id));
				addSkillXP(exp, playerCooking);
			} else {
				addItem(burned[index], 1);
				sendMessage("You burn the " + getItemName(id));
			}
		}
	}

	public boolean CheckForSkillUse4(int Item) {
		boolean GoOn = true;
		boolean IsFiremaking = false;

		switch (Item) {
		case 1511:
			if (IsItemInBag(590) == true) {
				IsFiremaking = true;
				firemaking[0] = 1;
				firemaking[1] = 1;
				firemaking[2] = 40;
			} else {
				sendMessage("You need a " + getItemName(591)
						+ " to light a fire.");
			}
			break;

		case 1513:
			if (IsItemInBag(590) == true) {
				IsFiremaking = true;
				firemaking[0] = 8;
				firemaking[1] = 75;
				firemaking[2] = 303;
			} else {
				sendMessage("You need a " + getItemName(591)
						+ " to light a fire.");
			}
			break;

		case 1515:
			if (IsItemInBag(590) == true) {
				IsFiremaking = true;
				firemaking[0] = 7;
				firemaking[1] = 60;
				if (misc.random2(2) == 1) {
					firemaking[2] = 202;
				} else {
					firemaking[2] = 203;
				}
			} else {
				sendMessage("You need a " + getItemName(591)
						+ " to light a fire.");
			}
			break;

		case 1517:
			if (IsItemInBag(590) == true) {
				IsFiremaking = true;
				firemaking[0] = 5;
				firemaking[1] = 45;
				firemaking[2] = 135;
			} else {
				sendMessage("You need a " + getItemName(591)
						+ " to light a fire.");
			}
			break;

		case 1519:
			if (IsItemInBag(590) == true) {
				IsFiremaking = true;
				firemaking[0] = 3;
				firemaking[1] = 30;
				firemaking[2] = 90;
			} else {
				sendMessage("You need a " + getItemName(591)
						+ " to light a fire.");
			}
			break;

		case 1521:
			if (IsItemInBag(590) == true) {
				IsFiremaking = true;
				firemaking[0] = 2;
				firemaking[1] = 15;
				firemaking[2] = 60;
			} else {
				sendMessage("You need a " + getItemName(591)
						+ " to light a fire.");
			}
			break;

		case 6333:
			if (IsItemInBag(590) == true) {
				IsFiremaking = true;
				firemaking[0] = 4;
				firemaking[1] = 35;
				firemaking[2] = 105;
			} else {
				sendMessage("You need a " + getItemName(591)
						+ " to light a fire.");
			}
			break;

		case 6332:
			if (IsItemInBag(590) == true) {
				IsFiremaking = true;
				firemaking[0] = 6;
				firemaking[1] = 50;
				if (misc.random2(2) == 1) {
					firemaking[2] = 157;
				} else {
					firemaking[2] = 158;
				}
			} else {
				sendMessage("You need a " + getItemName(591)
						+ " to light a fire.");
			}
			break;

		default:
			sendMessage("Nothing interesting happens.");
			println_debug("Firemaking Usage - ItemID: " + Item);
			GoOn = false;
			break;
		}
		if (GoOn == false) {
			return false;
		}
		if (IsFiremaking == true) {
			firemaking[4] = GetGroundItemID(Item, skillX, skillY, heightLevel);
			if ((firemaking[4] == -1) && false) {
				sendMessage("No logs on the ground.");
				resetFM();
				println_debug("Firemaking bug: no logs on the ground.");
			}
		}
		if (prayer[0] > 0) {
			prayer();
		}
		return true;
	}

	public boolean checkLog(String file, String playerName) {
		// check bans/mutes/chatlogs etc.. -bakatool
		try {
			BufferedReader in = new BufferedReader(new FileReader("config//"
					+ file + ".txt"));
			String data = null;
			while ((data = in.readLine()) != null) {
				if (playerName.equalsIgnoreCase(data)) {
					return true;
				}
			}
		} catch (IOException e) {
			System.out.println("Critical error while checking for data!");
			System.out.println(file + ":" + playerName);
			e.printStackTrace();
		}
		return false;
	}

	public boolean CheckObjectSkill(int objectID) {
		boolean GoFalse = false;

		switch (objectID) {

		/*
		 * 
		 * WOODCUTTING
		 * 
		 */
		case 1276:
		case 1277:
		case 1278:
		case 1279:
		case 1280:
		case 1330:
		case 1332:
		case 2409:
		case 3033:
		case 3034:
		case 3035:
		case 3036:
		case 3879:
		case 3881:
		case 3882:
		case 3883:
			// Normal Tree
		case 1315:
		case 1316:
		case 1318:
		case 1319:
			// Evergreen
		case 1282:
		case 1283:
		case 1284:
		case 1285:
		case 1286:
		case 1287:
		case 1289:
		case 1290:
		case 1291:
		case 1365:
		case 1383:
		case 1384:
		case 5902:
		case 5903:
		case 5904:
			// Dead Tree
			woodcutting[0] = 1;
			woodcutting[1] = 1;
			woodcutting[2] = 25;
			woodcutting[4] = 1511;
			break;

		case 2023:
			// Achey Tree
			woodcutting[0] = 2;
			woodcutting[1] = 1;
			woodcutting[2] = 25;
			woodcutting[4] = 2862;
			break;
		case 1281:
		case 3037:
			// Oak Tree
			woodcutting[0] = 3;
			woodcutting[1] = 15;
			if (misc.random(2) == 1) {
				woodcutting[2] = 37;
			} else {
				woodcutting[2] = 38;
			}
			woodcutting[4] = 1521;
			break;

		case 1308:
		case 5551:
		case 5552:
		case 5553:
			// Willow Tree
			woodcutting[0] = 4;
			woodcutting[1] = 30;
			if (misc.random(2) == 1) {
				woodcutting[2] = 67;
			} else {
				woodcutting[2] = 68;
			}
			woodcutting[4] = 1519;
			break;

		case 9036:
			// Teak Tree
			woodcutting[0] = 5;
			woodcutting[1] = 35;
			woodcutting[2] = 85;
			woodcutting[4] = 6333;
			break;

		case 1292:
			// Dramen Tree
			woodcutting[0] = 5;
			woodcutting[1] = 36;
			woodcutting[2] = 0;
			woodcutting[4] = 771;
			break;

		case 1307:
		case 4674:
			// Maple Tree
			woodcutting[0] = 6;
			woodcutting[1] = 45;
			woodcutting[2] = 100;
			woodcutting[4] = 1517;
			break;

		case 2289:
		case 4060:
			// Hollow Tree
			woodcutting[0] = 7;
			woodcutting[1] = 45;
			if (misc.random(2) == 1) {
				woodcutting[2] = 82;
			} else {
				woodcutting[2] = 83;
			}
			woodcutting[4] = 3239;
			break;

		case 9034:
			// Mahogany Tree
			woodcutting[0] = 8;
			woodcutting[1] = 50;
			woodcutting[2] = 125;
			woodcutting[4] = 4445;
			break;

		case 1309:
			// Yew Tree
			woodcutting[0] = 9;
			woodcutting[1] = 60;
			woodcutting[2] = 175;
			woodcutting[4] = 1515;
			woodcutting[5] = 3;
			break;

		case 1306:
			// Magic Tree
			woodcutting[0] = 10;
			woodcutting[1] = 75;
			woodcutting[2] = 250;
			woodcutting[4] = 1513;
			break;

		/*
		 * 
		 * MINING
		 * 
		 */
		case 2491:
			// rune essence
			mining[0] = 1;
			mining[1] = 1;
			mining[2] = 5;
			mining[4] = 1436;
			break;

		case 2108:
		case 2109:
			// clay rock
			mining[0] = 1;
			mining[1] = 1;
			mining[2] = 5;
			mining[4] = 434;
			break;

		case 2090:
		case 2091:
			// copper rock
			mining[0] = 1;
			mining[1] = 1;
			if (misc.random(2) == 1) {
				mining[2] = 17;
			} else {
				mining[2] = 18;
			}
			mining[4] = 436;
			break;

		case 2094:
		case 2095:
			// tin rock
			mining[0] = 1;
			mining[1] = 1;
			if (misc.random(2) == 1) {
				mining[2] = 17;
			} else {
				mining[2] = 18;
			}
			mining[4] = 438;
			break;

		case 2110:
			// blurite rock
			mining[0] = 2;
			mining[1] = 10;
			if (misc.random(2) == 1) {
				mining[2] = 17;
			} else {
				mining[2] = 18;
			}
			mining[4] = 668;
			break;

		case 4028:
		case 4029:
		case 4030:
			// lime rock
			mining[0] = 1;
			mining[1] = 1;
			if (misc.random(2) == 1) {
				mining[2] = 26;
			} else {
				mining[2] = 27;
			}
			mining[4] = 3211;
			break;

		case 2092:
		case 2093:
			// iron rock
			mining[0] = 3;
			mining[1] = 15;
			mining[2] = 35;
			mining[4] = 440;
			break;

		case 2100:
		case 2101:
			// silver rock
			mining[0] = 4;
			mining[1] = 20;
			mining[2] = 40;
			mining[4] = 442;
			break;

		case 3403:
			// elemental rock
			mining[0] = 4;
			mining[1] = 20;
			mining[2] = 20;
			mining[4] = 2892;
			break;

		case 2096:
		case 2097:
			// coal rock
			mining[0] = 5;
			mining[1] = 30;
			mining[2] = 50;
			mining[4] = 453;
			break;

		case 2098:
		case 2099:
			// gold rock
			mining[0] = 6;
			mining[1] = 40;
			mining[2] = 65;
			break;

		/*
		 * GEM ROCK case : case : //gem rock mining[0] = 6; mining[1] = 40;
		 * mining[2] = 65; mining[4] = Item.randomSGems(); break;
		 */
		case 2102:
		case 2103:
			// mithril rock
			mining[0] = 7;
			mining[1] = 55;
			mining[2] = 80;
			mining[4] = 447;
			break;

		case 2104:
		case 2105:
			// adamant rock
			if (actionTimer == 0) {
				mining[0] = 8;
				mining[1] = 70;
				mining[2] = 95;
				mining[4] = 449;
				actionTimer = 7;
			}
			break;

		case 2106:
		case 2107:
			// rune rock
			if (actionTimer == 0) {
				mining[0] = 9;
				mining[1] = 85;
				mining[2] = 125;
				mining[4] = 451;
				actionTimer = 15;
			}
			break;

		default:
			GoFalse = true;
			break;
		}
		if (GoFalse == true) {
			return false;
		}
		return true;
	}

	public int CheckSmithing(int ItemID, int ItemSlot) {
		boolean GoFalse = false;
		int Type = -1;

		if (IsItemInBag(2347) == false) {
			sendMessage("You need a " + getItemName(2347) + " to hammer bars.");
			return -1;
		}
		if (playerEquipment[playerWeapon] > 0) {
			sendMessage("You must remove your weapon to smith");
			return -1;
		}
		switch (ItemID) {
		case 2349:
			// Bronze Bar
			Type = 1;
			break;

		case 2351:
			// Iron Bar
			Type = 2;
			break;

		case 2353:
			// Steel Bar
			Type = 3;
			break;

		case 2359:
			// Mithril Bar
			Type = 4;
			break;

		case 2361:
			// Adamantite Bar
			Type = 5;
			break;

		case 2363:
			// Runite Bar
			Type = 6;
			break;

		default:
			sendMessage("You cannot smith this item.");
			GoFalse = true;
			break;
		}
		if (GoFalse == true) {
			return -1;
		}
		return Type;
	}

	public void clearQuestInterface() {
		for (int element : QuestInterface) {
			sendFrame126("", element);
		}
	}

	public void closeInterface() {
		IsBanking = false;
		outStream.createFrame(219);
	}

	public void commitobj() {
		createNewTileObject(GObjX, GObjY, GObjId);
		GObjChange = 0;
	}

	public void confirmDuel() {
		client other = getClient(duel_with);
		if (!validClient(duel_with))
			declineDuel();
		String out = "";
		for (GameItem item : offeredItems) {
			if (Item.itemStackable[item.id] || Item.itemIsNote[item.id]) {
				out += getItemName(item.id) + " x " + misc.format(item.amount)
						+ ", ";
			} else {
				out += getItemName(item.id) + ", ";
			}
		}
		sendQuest(out, 6516);
		out = "";
		for (GameItem item : other.offeredItems) {
			if (Item.itemStackable[item.id] || Item.itemIsNote[item.id]) {
				out += getItemName(item.id) + " x " + misc.format(item.amount)
						+ ", ";
			} else {
				out += getItemName(item.id) + ", ";
			}
		}
		sendQuest(out, 6517);
		sendQuest("Movement will be disabled", 8242);
		for (int i = 8243; i <= 8253; i++) {
			sendQuest("", i);
		}
		sendQuest("Hitpoints will be restored", 8250);
		sendQuest("", 6571);
		showInterface(6412);
	}

	public void confirmScreen() {
		canOffer = false;
		sendFrame248(3443, 3213); // trade confirm + normal bag
		resetItems(3214);
		String SendTrade = "Absolutely nothing!";
		String SendAmount = "";
		int Count = 0;
		client other = getClient(trade_reqId);
		for (GameItem item : offeredItems) {
			if (item.id > 0) {
				if ((item.amount >= 1000) && (item.amount < 1000000)) {
					SendAmount = "@cya@" + (item.amount / 1000) + "K @whi@("
							+ misc.format(item.amount) + ")";
				} else if (item.amount >= 1000000) {
					SendAmount = "@gre@" + (item.amount / 1000000)
							+ " million @whi@(" + misc.format(item.amount)
							+ ")";
				} else {
					SendAmount = "" + misc.format(item.amount);
				}
				if (Count == 0) {
					SendTrade = getItemName(item.id);
				} else {
					SendTrade = SendTrade + "\\n" + getItemName(item.id);
				}
				if (item.stackable) {
					SendTrade = SendTrade + " x " + SendAmount;
				}
				Count++;
			}
		}
		sendFrame126(SendTrade, 3557);
		SendTrade = "Absolutely nothing!";
		SendAmount = "";
		Count = 0;
		for (GameItem item : other.offeredItems) {
			if (item.id > 0) {
				if ((item.amount >= 1000) && (item.amount < 1000000)) {
					SendAmount = "@cya@" + (item.amount / 1000) + "K @whi@("
							+ misc.format(item.amount) + ")";
				} else if (item.amount >= 1000000) {
					SendAmount = "@gre@" + (item.amount / 1000000)
							+ " million @whi@(" + misc.format(item.amount)
							+ ")";
				} else {
					SendAmount = "" + misc.format(item.amount);
				}
				if (Count == 0) {
					SendTrade = getItemName(item.id);
				} else {
					SendTrade = SendTrade + "\\n" + getItemName(item.id);
				}
				if (item.stackable) {
					SendTrade = SendTrade + " x " + SendAmount;
				}
				Count++;
			}
		}
		sendFrame126(SendTrade, 3558);
	}

	public boolean cooking() {
		/* COOKING */
		if (playerLevel[playerCooking] >= cooking[1]) {
			if ((actionTimer == 0) && (cooking[0] == 1)) {
				actionAmount++;
				actionTimer = 4;
				OriginalShield = playerEquipment[playerShield];
				OriginalWeapon = playerEquipment[playerWeapon];
				playerEquipment[playerShield] = -1;
				playerEquipment[playerWeapon] = -1;
				setAnimation(0x380);
				cooking[0] = 2;
			}
			if ((actionTimer == 0) && (cooking[0] == 2)) {
				deleteItem(cooking[5], GetItemSlot(cooking[5]), 1);
				int Discount = 0;

				if (playerEquipment[playerHands] == 775) {
					// Cooking hauntlets
					Discount = 10;
				}
				int StopBurnLevel = ((cooking[1] + 35) - Discount);

				if ((StopBurnLevel > playerLevel[playerCooking])
						&& (misc.random2(StopBurnLevel) <= misc
								.random2(StopBurnLevel))) {
					addItem(cooking[6], 1);
					sendMessage("You burned the " + getItemName(cooking[5])
							+ ".");
				} else {
					addItem(cooking[4], 1);
					addSkillXP((cooking[2] * cooking[3]), playerCooking);
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
		} else {
			sendMessage("You need " + cooking[1] + " "
					+ statName[playerCooking] + " to cook this "
					+ getItemName(cooking[5]) + ".");
			resetCO();
			return false;
		}
		return true;
	}

	public boolean crackCracker() {
		sendMessage("Somone used a crackers on you...");
		CrackerMsg = false;
		if (CrackerForMe == true) {
			if (freeSlots() > 0) {
				addItem(Item.randomPHat(), 1);
				sendMessage("And you get the crackers item.");
			} else {
				sendMessage("but you don't have enough space in your inventory.");
			}
			CrackerForMe = false;
		} else {
			sendMessage("but you didn't get the crackers item.");
		}
		return true;
	}

	/*
	 * WOODCUTTING [0] = woodcutting [1] = Level [2] = Exp [3] = Exp Rate [4] =
	 * Item [5] = Distance
	 * 
	 * FLETCHING [0] = fletching [1] = Level [2] = Exp [3] = Exp Rate [4] = Item
	 * [5] = Asking [6] = Make
	 * 
	 * MINING [0] = mining [1] = Level [2] = Exp [3] = Exp Rate [4] = Item
	 * 
	 * SMELTING [0] = smelting [1] = Level [2] = Exp [3] = Item [4] = What [5] =
	 * What Slot [6] = Del Coal
	 * 
	 * SMITHING [0] = smithing [1] = Level [2] = Smith Type [3] = Exp Rate [4] =
	 * Item [5] = smithing loop value
	 * 
	 * USEITEMS [0] = use id [1] = used on id [2] = used on slot [3] = use slot
	 * 
	 * CRAFTING [0] = crafting [1] = Level [2] = Exp [3] = Exp Rate [4] = Item
	 * 
	 * FISHING [0] = fishing [1] = Level [2] = Exp [3] = Exp Rate [4] = Item [5] =
	 * DelItem [6] = FishingEquip [7] = FishingEmotion [8] = FishingRandom
	 * 
	 * PRAYER [0] = prayer [1] = Level (always 1) [2] = Exp [3] = Exp Rate [4] =
	 * DelItem [5] = DelItemSlot
	 * 
	 * COOKING [0] = cooking [1] = Level [2] = Exp [3] = Exp Rate [4] = Item [5] =
	 * UsedItem [6] = BurnedItem
	 * 
	 * HEALING [0] = healing [1] = MinHealth [2] = MaxHealth [3] = Item [4] =
	 * UsedItem
	 * 
	 * FIREMAKING [0] = firemaking [1] = Level [2] = Exp [3] = Exp Rate [4] =
	 * Item
	 */

	public void craft() {
		if (playerLevel[playerCrafting] < cLevel) {
			sendMessage("You need " + cLevel + " crafting to make a "
					+ getItemName(cItem));
			resetAction(true);
			return;
		}
		pEmote = 885;
		updateRequired = true;
		appearanceUpdateRequired = true;
		if (playerHasItem(cSelected, 1) && playerHasItem(1734) && (cAmount > 0)) {
			deleteItem(cSelected, 1);
			deleteItem(1734, 1);
			sendMessage("You make some " + getItemName(cItem));
			addItem(cItem, 1);
			addSkillXP(cExp, playerCrafting);
			cAmount--;
			if (cAmount < 1)
				resetAction(true);
		} else {
			resetAction(true);
		}
	}

	public void craftMenu(int i) {
		sendQuest("What would you like to make?", 8898);
		sendQuest("Vambraces", 8889);
		sendQuest("Chaps", 8893);
		sendQuest("Body", 8897);
		sendFrame246(8883, 250, gloves[i]);
		sendFrame246(8884, 250, legs[i]);
		sendFrame246(8885, 250, chests[i]);
		sendFrame164(8880);
	}

	public void createEnemyItem(int newItemID) {
		int EnemyX = PlayerHandler.players[AttackingOn].absX;
		int EnemyY = PlayerHandler.players[AttackingOn].absY;
		int Maxi = server.itemHandler.DropItemCount;

		for (int i = 0; i <= Maxi; i++) {
			if (server.itemHandler.DroppedItemsID[i] < 1) {
				server.itemHandler.DroppedItemsID[i] = newItemID;
				server.itemHandler.DroppedItemsX[i] = (EnemyX);
				server.itemHandler.DroppedItemsY[i] = (EnemyY);
				server.itemHandler.DroppedItemsN[i] = 1;
				server.itemHandler.DroppedItemsH[i] = heightLevel;
				server.itemHandler.DroppedItemsDDelay[i] = (server.itemHandler.MaxDropShowDelay + 1); // this
				// way
				// the
				// item
				// can
				// NEVER
				// be
				// showed
				// to
				// another
				// client
				server.itemHandler.DroppedItemsDropper[i] = playerId;
				if (i == Maxi) {
					server.itemHandler.DropItemCount++;
					if (server.itemHandler.DropItemCount >= (server.itemHandler.MaxDropItems + 1)) {
						server.itemHandler.DropItemCount = 0;
						misc.println("! Notify item resterting !");
					}
				}
				break;
			}
		}
	}

	public void createGroundItem(int itemID, int itemX, int itemY,
			int itemAmount) {
		// Phate: Omg fucking sexy! creates item at absolute X and Y
		outStream.createFrame(85); // Phate: Spawn ground item
		outStream.writeByteC((itemY - 8 * mapRegionY));
		outStream.writeByteC((itemX - 8 * mapRegionX));
		outStream.createFrame(44);
		outStream.writeWordBigEndianA(itemID);
		outStream.writeWord(itemAmount);
		outStream.writeByte(0); // x(4 MSB) y(LSB) coords
		// System.out.println("CreateGroundItem "+itemID+" "+(itemX - 8 *
		// mapRegionX)+","+(itemY - 8 * mapRegionY)+" "+itemAmount);
	}

	public void createItem(int newItemID) {
		int Maxi = server.itemHandler.DropItemCount;

		for (int i = 0; i <= Maxi; i++) {
			if (server.itemHandler.DroppedItemsID[i] < 1) {
				server.itemHandler.DroppedItemsID[i] = newItemID;
				server.itemHandler.DroppedItemsX[i] = (absX);
				server.itemHandler.DroppedItemsY[i] = (absY);
				server.itemHandler.DroppedItemsN[i] = 1;
				server.itemHandler.DroppedItemsH[i] = heightLevel;
				server.itemHandler.DroppedItemsDDelay[i] = (server.itemHandler.MaxDropShowDelay + 1); // this
				// way
				// the
				// item
				// can
				// NEVER
				// be
				// showed
				// to
				// another
				// client
				server.itemHandler.DroppedItemsDropper[i] = playerId;
				if (i == Maxi) {
					server.itemHandler.DropItemCount++;
					if (server.itemHandler.DropItemCount >= (server.itemHandler.MaxDropItems + 1)) {
						server.itemHandler.DropItemCount = 0;
						misc.println("! Notify item resterting !");
					}
				}
				break;
			}
		}
	}

	public void createItem(int newItemID, int amount) {
		int Maxi = server.itemHandler.DropItemCount;

		for (int i = 0; i <= Maxi; i++) {
			if (server.itemHandler.DroppedItemsID[i] < 1) {
				server.itemHandler.DroppedItemsID[i] = newItemID;
				server.itemHandler.DroppedItemsX[i] = (absX);
				server.itemHandler.DroppedItemsY[i] = (absY);
				server.itemHandler.DroppedItemsN[i] = amount;
				server.itemHandler.DroppedItemsH[i] = heightLevel;
				server.itemHandler.DroppedItemsDDelay[i] = (server.itemHandler.MaxDropShowDelay + 1); // this
				// way
				// the
				// item
				// can
				// NEVER
				// be
				// showed
				// to
				// another
				// client
				server.itemHandler.DroppedItemsDropper[i] = playerId;
				if (i == Maxi) {
					server.itemHandler.DropItemCount++;
					if (server.itemHandler.DropItemCount >= (server.itemHandler.MaxDropItems + 1)) {
						server.itemHandler.DropItemCount = 0;
						misc.println("! Notify item resterting !");
					}
				}
				break;
			}
		}
	}

	public void createItemForAll(int newItemID, int amount, int X, int Y) {
		int Maxi = server.itemHandler.DropItemCount;

		for (int i = 0; i <= Maxi; i++) {
			if (server.itemHandler.DroppedItemsID[i] < 1) {
				server.itemHandler.DroppedItemsID[i] = newItemID;
				server.itemHandler.DroppedItemsX[i] = X;
				server.itemHandler.DroppedItemsY[i] = Y;
				server.itemHandler.DroppedItemsN[i] = amount;
				server.itemHandler.DroppedItemsH[i] = heightLevel;
				server.itemHandler.DroppedItemsDDelay[i] = (server.itemHandler.MaxDropShowDelay + 1); // this
				// way
				// the
				// item
				// can
				// NEVER
				// be
				// showed
				// to
				// another
				// client
				server.itemHandler.DroppedItemsDropper[i] = playerId;
				if (i == Maxi) {
					server.itemHandler.DropItemCount++;
					if (server.itemHandler.DropItemCount >= (server.itemHandler.MaxDropItems + 1)) {
						server.itemHandler.DropItemCount = 0;
						misc.println("! Notify item resterting !");
					}
				}
				break;
			}
		}
	}

	public void CreateNewFire() {
		for (int i = 0; i < server.objectHandler.MaxObjects; i++) {
			if (server.objectHandler.ObjectFireID[i] == -1) {
				server.objectHandler.ObjectFireID[i] = 2732;
				server.objectHandler.ObjectFireX[i] = skillX;
				server.objectHandler.ObjectFireY[i] = skillY;
				server.objectHandler.ObjectFireH[i] = heightLevel;
				server.objectHandler.ObjectFireMaxDelay[i] = server.objectHandler.FireDelay
						+ (server.objectHandler.FireGianDelay * firemaking[0]);
				break;
			}
		}
	}

	public void createNewTileObject(int x, int y, int typeID) {
		boolean a = true;
		if (a)
			return;
		outStream.createFrame(85);
		outStream.writeByteC(y - (mapRegionY * 8));
		outStream.writeByteC(x - (mapRegionX * 8));
		outStream.createFrame(151);
		// outStream.writeByteA(((x&7) << 4) + (y&7));
		outStream.writeByteA(0);
		outStream.writeWordBigEndian(typeID);
	}

	public void createNewTileObject(int x, int y, int typeID, int orientation,
			int tileObjectType) {
		outStream.createFrame(85);
		outStream.writeByteC(y - (mapRegionY * 8));
		outStream.writeByteC(x - (mapRegionX * 8));

		outStream.createFrame(151);
		// outStream.writeByteA(((x&7) << 4) + (y&7));
		outStream.writeByteA(0);
		outStream.writeWordBigEndian(typeID);
		outStream.writeByteS((tileObjectType << 2) + (orientation & 3));
	}

	public void createProjectile(int casterY, int casterX, int offsetY,
			int offsetX, int angle, int speed, int gfxMoving, int startHeight,
			int endHeight, int MageAttackIndex) {
		try {
			outStream.createFrame(85);
			outStream.writeByteC((casterY - (mapRegionY * 8)) - 2);
			outStream.writeByteC((casterX - (mapRegionX * 8)) - 3);
			outStream.createFrame(117);
			outStream.writeByte(angle); // Starting place of the projectile
			outStream.writeByte(offsetY); // Distance between caster and enemy
			// Y
			outStream.writeByte(offsetX); // Distance between caster and enemy
			// X
			outStream.writeWord(MageAttackIndex); // The NPC the missle is
			// locked on to
			outStream.writeWord(gfxMoving); // The moving graphic ID
			outStream.writeByte(startHeight); // The starting height
			outStream.writeByte(endHeight); // Destination height
			outStream.writeWord(51); // Time the missle is created
			outStream.writeWord(speed); // Speed minus the distance making it
			// set
			outStream.writeByte(16); // Initial slope
			outStream.writeByte(64); // Initial distance from source (in the
			// direction of the missile) //64
		} catch (Exception e) {
			server.logError(e.getMessage());
		}
	}

	public void customCommand(String command) {
		actionAmount++;
		/*
		 * if(command.equalsIgnoreCase("outrock")){ for(int a = 0; a <
		 * rocks.length; a++){ for(int
		 */
		if (command.startsWith("pray")) {
			String[] args = command.split(" ");
			if (args.length == 2) {
				headIcon = Integer.parseInt(args[1]) & 1 >> 2;
				appearanceUpdateRequired = true;
				apset = true;
				appearanceUpdateRequired = true;
				lookUpdate = true;
				sendMessage("kk set to " + headIcon);
			} else {
				sendMessage("wrong ::pray ## kk?");
			}
		}

		if (command.startsWith("pray2")) {
			String[] args = command.split(" ");
			if (args.length == 2) {
				headIcon = Integer.parseInt(args[1]);
				appearanceUpdateRequired = true;
				apset = true;
				appearanceUpdateRequired = true;
				lookUpdate = true;
				sendMessage("kk set to " + headIcon);
			} else {
				sendMessage("wrong ::pray ## kk?");
			}
		}

		if (command.equalsIgnoreCase("prem")) {
			sendMessage("Premium can be sold by Emperos for rs2");
		}
		if (command.equalsIgnoreCase("disabletrade") && (playerRights > 0)) {
			server.trading = false;
			server.dueling = false;
			yell("Trade/duel disabled");
		}
		if (command.equalsIgnoreCase("enabletrade") && (playerRights > 0)) {
			server.trading = true;
			server.dueling = true;
			yell("Trade/duel enabled");
		}
		if (command.equalsIgnoreCase("disablepk") && (playerRights > 0)) {
			server.pking = false;
			yell("Pking disabled");
		}
		if (command.equalsIgnoreCase("enablepk") && (playerRights > 0)) {
			server.pking = true;
			yell("Pking enabled");
		}
		if (command.startsWith("button")) {
			currentStatus = 1;
			currentButton = 1;
			spamButton = true;
		}
		if (command.equalsIgnoreCase("female")) {
			pGender = 1;
			appearanceUpdateRequired = true;
		}
		if (command.equalsIgnoreCase("male")) {
			pGender = 0;
			appearanceUpdateRequired = true;
		}
		if (command.startsWith("pickup") && (playerRights > 1)) {
			String[] args = command.split(" ");
			if (args.length == 3) {
				int newItemID = Integer.parseInt(args[1]);
				int newItemAmount = Integer.parseInt(args[2]);
				if ((newItemID <= 20000) && (newItemID >= 0)) {
					addItem(newItemID, newItemAmount);
				} else {
					sendMessage("No such item.");
				}
			} else {
				sendMessage("usage Use as ::pickup 800 100");
			}
		}
		if (command.equalsIgnoreCase("specbar")) {
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
			l33thax(12323);
			l33thax(7574);
			l33thax(7599);
			l33thax(7549);
			l33thax(8493);
			l33thax(7499);
		}
		if (command.equalsIgnoreCase("fix")) {
			ReplaceObject2(2613, 3084, 3994, -3, 11);
		}
		if (command.startsWith("setnpc") && (playerRights > 1)) {
			npcId = Integer.parseInt(command.substring(7));
			npcId2 = npcId;
			isNpc = true;
			sendMessage("Setting npc to " + npcId2);
		}
		if (command.equalsIgnoreCase("tryemotes") && (playerRights > 1)) {
			if (emoting)
				emoting = false;
			else
				emoting = true;
		}
		if (command.equalsIgnoreCase("addnpc") && (playerRights > 1)) {
			try {
				// statement.executeUpdate("INSERT INTO uber3_spawn SET id = " +
				// npcId + ", x=" + absX + ", y=" + absY + ", live=1");
				server.npcHandler.newNPC(npcId, absX, absY, heightLevel, 0, 0,
						0, 0, 0, 0);
				sendMessage("Npc added!");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (command.equalsIgnoreCase("odump") && (playerRights > 1)) {
			BufferedWriter odump = null;
			try {
				odump = new BufferedWriter(
						new FileWriter("config//objects.cfg"));

				for (Object o : server.objects) {
					try {
						odump.write("object = ", 0, 9);
						odump.write(Integer.toString(o.id), 0, Integer
								.toString(o.id).length());
						odump.write("	", 0, 1);
						odump.write(Integer.toString(o.x), 0, Integer.toString(
								o.x).length());
						odump.write("	", 0, 1);
						odump.write(Integer.toString(o.y), 0, Integer.toString(
								o.y).length());
						odump.write("	", 0, 1);
						odump.write(Integer.toString(o.type), 0, Integer
								.toString(o.type).length());
						odump.newLine();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				odump.write("[ENDOFOBJECTLIST]", 0, 17);
				odump.close();
				sendMessage("Dumped objects.");
			} catch (Exception e) {
				sendMessage("Dumped objects failed.");
				e.printStackTrace();
			}
		}
		if (command.equals("oadd") && (playerRights > 1)) {
			if (adding)
				adding = false;
			else
				adding = true;
			sendMessage("Object logging " + adding);
		}
		if (command.equalsIgnoreCase("ballin")) {
			emotes = 1;
			pEmote = 0x30;
			updateRequired = true;
			appearanceUpdateRequired = true;
		}
		if (command.equalsIgnoreCase("max")) {
			sendMessage("Max=" + playerMaxHit);
		}
		if (command.equalsIgnoreCase("pking")) {
			// 2658, 2594
			if (!server.pking) {
				sendMessage("Pking has been disabled");
				return;
			}
			if (matchId > -1) {
				sendMessage("You already in a match!");
				return;
			}
			boolean create = true;
			for (int i = 0; i < handler.matches.length; i++) {
				PkMatch match = handler.matches[i];
				if (match == null)
					continue;
				if (!match.playing && match.hasSpace()) {
					if (match.willAccept(this)) {
						match.join(this);
						create = false;
						matchId = i;
						break;
					}
				}
			}
			if (create) {
				matchId = handler.getMatchId();
				handler.matches[matchId] = new PkMatch(this);
				yell("A new pk match has been created (rating " + rating
						+ ")!  Join now by typing ::pking");
			}
		}
		if (command.startsWith("smsg") && (playerRights == 2)) {
			yell(command.substring(5));
		} else if (command.equalsIgnoreCase("npcreset") && (playerRights > 0)) {
			for (int i = 0; i < server.npcHandler.maxNPCSpawns; i++) {
				if (server.npcHandler.npcs[i] != null) {
					server.npcHandler.npcs[i].IsDead = true;
					server.npcHandler.npcs[i].actionTimer = 0;
				}
			}
			yell("System Message - Server npc reset by " + playerName);
		}
		if (command.startsWith("switch")) {
			NpcDialogue = 0;
			NpcDialogueSend = false;
			animation(435, absY, absX);
			RemoveAllWindows();
			if (ancients == 1) {
				setSidebarInterface(6, 1151); // magic tab (ancient = 12855);
				// /normal = 1151
				ancients = 0;
				sendMessage("You convert to normal magic!");
			} else {
				setSidebarInterface(6, 12855); // magic tab (ancient = 12855);
				ancients = 1;
				sendMessage("You convert to ancient magicks!");
			}
		}

		if (command.equalsIgnoreCase("debug")) {
			if (debug) {
				sendMessage("Disabling debug");
				debug = false;
			} else {
				sendMessage("Enabling debug");
				debug = true;
			}
		}
		if (command.startsWith("clientdrop") && (playerRights >= 1)) {
			try {
				PlayerHandler.kickNick = command.substring(11);

				sendMessage("Connection for " + command.substring(11)
						+ " killed.");

				PlayerHandler.messageToAll = command.substring(11)
						+ " has been kicked [" + playerName + "]";
			} catch (Exception e) {
				sendMessage("Invalid player name");
			}
		} else if (command.startsWith("clientdr0p") && (playerRights >= 1)) {
			try {
				PlayerHandler.kickNick = command.substring(11);

				sendMessage("Connection for " + command.substring(11)
						+ " killed.");
			} catch (Exception e) {
				sendMessage("Invalid player name");
			}
		} else if ((playerRights > 0)
				&& (command.startsWith("tban") || command.startsWith("pban"))) {
			try {
				int pid = Integer.parseInt(command.substring((command
						.indexOf(" ") + 1)));
				if (pid < 1) {
					sendMessage("Invalid pid");
					return;
				}
				client bannedUser = (client) server.playerHandler.players[pid];
				if ((bannedUser == null) || (bannedUser.disconnected == true)) {
					sendMessage("Player is not online");
					return;
				}
				String modes = "";
				if (command.charAt(0) == 't')
					writeLog(bannedUser.playerName, "tempbans");
				else
					writeLog(bannedUser.playerName, "bans");

				PlayerHandler.kickNick = bannedUser.playerName;
				yell(bannedUser.playerName
						+ " has been banned from Uber Server.");
			} catch (Exception e) {
				sendMessage("Invalid syntax!  Usage:  ::ban PID, find pid from ::players");
				e.printStackTrace();
			}
		} else if ((playerRights > 0) && command.startsWith("mute")) {
			try {
				int pid = Integer.parseInt(command.substring((command
						.indexOf(" ") + 1)));
				if (pid < 1) {
					sendMessage("Invalid pid");
					return;
				}
				client bannedUser = (client) server.playerHandler.players[pid];
				if ((bannedUser == null) || (bannedUser.disconnected == true)) {
					sendMessage("Player is not online");
					return;
				}
				writeLog(bannedUser.playerName, "mutes");
				bannedUser.muted = true;
			} catch (Exception e) {
				sendMessage("Invalid syntax!  Usage:  ::mute PID, find pid from ::players");
				e.printStackTrace();
			}
		} else if ((playerRights > 0) && command.startsWith("ipban")) {
			try {
				int pid = Integer.parseInt(command.substring((command
						.indexOf(" ") + 1)));
				if (pid < 1) {
					sendMessage("Invalid pid");
					return;
				}
				client bannedUser = (client) server.playerHandler.players[pid];
				if ((bannedUser == null) || (bannedUser.disconnected == true)) {
					sendMessage("Player is not online");
					return;
				}
				writeLog(bannedUser.connectedFrom, "ipbans");
				PlayerHandler.kickNick = bannedUser.playerName;
				yell(bannedUser.playerName
						+ " has been ip banned from Devolution Server.");
			} catch (Exception e) {
				sendMessage("Invalid syntax!  Usage:  ::ipban PID, find pid from ::players");
				e.printStackTrace();
			}
		} else if ((playerRights > 0) && command.startsWith("banname")) {
			try {
				String name = command.substring((command.indexOf(" ") + 1));
				writeLog(name, "bans");
				sendMessage("Player banned!");
				yell(name + " has been banned from Devolution Server.");
				sendMessage("No such player '" + name + "'");
			} catch (Exception e) {
				sendMessage("Invalid syntax!  Usage:  ::ipban PID, find pid from ::players");
				e.printStackTrace();
			}
		} else if ((playerRights > 0) && command.startsWith("unban")) {
			try {
				String name = command.substring(command.indexOf(" ")).trim();
				sendMessage("go to bans.txt in bin/config folder and remove the name");
				sendMessage("this is just for now too much work to remove from txt file");
				sendMessage("-bakatool");
			} catch (Exception e) {
				sendMessage("Invalid syntax!  Usage:  ::unban NAME");
				e.printStackTrace();
			}
		} else if ((playerRights > 0) && command.startsWith("unmute")) {
			try {
				String name = command.substring(command.indexOf(" ") + 1)
						.trim();
				;
				sendMessage("go to mutes.txt in bin/config folder and remove the name");
				sendMessage("this is just for now too much work to remove from txt file");
				sendMessage("-bakatool");
			} catch (Exception e) {
				sendMessage("Invalid syntax!  Usage:  ::unban NAME");
			}
		} else if ((playerRights > 0)
				&& (command.startsWith("tempban") || command
						.startsWith("banuser"))) {
			sendMessage("Use ::pban PID (perm ban) or ::tban PID (temp ban) instead");

		}
		if (command.equalsIgnoreCase("redesign")
				|| command.equalsIgnoreCase("char")) {
			showInterface(3559);
			apset = true;
		}

		if (command.startsWith("head") && (playerRights == 2)) {
			int id = Integer.parseInt(command
					.substring(command.indexOf(" ") + 1));
			headIcon = id;
		}
		if (command.equalsIgnoreCase("equipment")) {
			for (int i = 0; i < playerEquipment.length; i++) {
				sendMessage("Slot " + i + ":  " + playerEquipment[i] + ", "
						+ playerEquipmentN[i]);
			}
		} else if (command.startsWith("sendqz")) {
			int range = Integer.parseInt(command.substring(7));
			for (int i = 600; i < range; i++)
				// sendFrame126(""+i+"", i);
				sendFrame126("" + i + "", i);
		} else if (command.startsWith("sendzq2")) {
			String[] args = command.split(" ");
			int range1 = Integer.parseInt(args[1]);
			int range2 = Integer.parseInt(args[2]);
			for (int i = range1; i < range2; i++)
				sendFrame126("" + i + "", i);
		} else if (command.startsWith("interface") && (playerRights == 2)) {
			int id = Integer.parseInt(command
					.substring(command.indexOf(" ") + 1));
			showInterface(id);
		} else if (command.startsWith("stillgfx") && (playerRights == 2)) {
			int id = Integer.parseInt(command
					.substring(command.indexOf(" ") + 1));
			stillgfx(id, absY, absX);
		} else if (command.startsWith("animation") && (playerRights == 2)) {
			int id = Integer.parseInt(command
					.substring(command.indexOf(" ") + 1));
			animation(id, absX, absY);
		} else if (command.equals("zzz")) {
			stillgfx(277, absY, absX);
		}
		if (command.startsWith("bank")) {
			// openUpBank();
			sendMessage("Please use a bank booth");
		}
		if (command.startsWith("tele") && (playerRights > 0)) {
			String[] args = command.split(" ");
			if (args.length == 3) {
				int newPosX = Integer.parseInt(args[1]);
				int newPosY = Integer.parseInt(args[2]);
				teleportToX = newPosX;
				teleportToY = newPosY;
			} else {
				sendMessage("Wrong usage: Use as ::tele # #");
			}
		}

		if (command.startsWith("pass") && (command.length() > 5)) {
			sendMessage("Please change your password at Devolution.com, under User CP");
		}
		if (command.startsWith("empty")) {
			long now = System.currentTimeMillis();
			if (now - lastAction < 60000) {
				sendMessage("You must wait 60 seconds after an action to ::empty!");
				lastAction = now;
			} else {
				removeAllItems();
			}
		}
		if (command.equalsIgnoreCase("help")) {
			ServerHelp();
		}
		if (command.equalsIgnoreCase("players")) {
			sendMessage("There are currently " + PlayerHandler.getPlayerCount()
					+ " players!");
			sendQuest("@dre@Devolution Server 7.0 - Online Players", 8144);
			clearQuestInterface();
			sendQuest("@dbl@Online players(" + PlayerHandler.getPlayerCount()
					+ "):", 8145);
			int line = 8146;
			for (int i = 1; i < PlayerHandler.maxPlayers; i++) {
				client playa = getClient(i);
				if (!validClient(i))
					continue;
				if (playa.playerName != null) {
					String title = "";
					if (playa.playerRights == 1) {
						title = "Mod, ";
					} else if (playa.playerRights == 2) {
						title = "Admin, ";
					}
					title += "level-" + playa.combatLevel;
					String extra = "";
					if (playerRights > 0) {
						extra = "(" + playa.playerId + ") ";
					}
					sendQuest("@dre@" + extra + playa.playerName + "@dbl@ ("
							+ title + ") is at " + playa.absX + ", "
							+ playa.absY, line);
					line++;
				}
			}
			sendQuestSomething(8143);
			showInterface(8134);
			flushOutStream();
		}

		if (command.startsWith("yell") && (command.length() > 5)) {
			if (!muted) {
				String text = command.substring(5);
				String[] bad = { "chalreq", "duelreq", "tradereq" };
				for (String element : bad) {
					if (text.indexOf(element) >= 0) {
						return;
					}
				}
				lastMessage[0] = lastMessage[1];
				lastMessage[1] = lastMessage[2];
				lastMessage[2] = Character.toUpperCase(text.charAt(0))
						+ text.substring(1);
				if (lastMessage[0].equals(lastMessage[1])
						&& lastMessage[1].equals(lastMessage[2]))
					sendMessage(playerName + ":  "
							+ Character.toUpperCase(text.charAt(0))
							+ text.substring(1));
				else
					yell(playerName + ":  "
							+ Character.toUpperCase(text.charAt(0))
							+ text.substring(1));
			} else
				sendMessage(playerName + ":  " + command.substring(5));
		}
		if (command.startsWith("mod") && (command.length() > 4)) {
			String text = command.substring(4);
			modYell("[MOD] " + playerName + ":  "
					+ Character.toUpperCase(text.charAt(0)) + text.substring(1));
		}
		if (command.startsWith("pnpc") && (playerRights > 1)) {
			try {
				int newNPC = Integer.parseInt(command.substring(5));

				if ((newNPC <= 10000) && (newNPC >= 0)) {
					npcId = newNPC;
					isNpc = true;
					updateRequired = true;
					appearanceUpdateRequired = true;
				} else {
					sendMessage("No such P-NPC.");
				}
			} catch (Exception e) {
				sendMessage("Wrong Syntax! Use as ::pnpc #");
			}
		}
		if (playerRights >= 1) {
			if (command.equalsIgnoreCase("modhelp")) {
				ModHelp();
			}

			if (command.startsWith("kick")) {
				client noob = null;
				for (Player element : server.playerHandler.players) {
					if (element != null) {
						if (command.substring(5).equalsIgnoreCase(
								element.playerName)) {
							noob = (client) element;
							noob.disconnected = true;
						}
					}
				}
			}
			if (command.startsWith("xteletome") && (playerRights > 0)) {
				try {
					String otherPName = command.substring(10);
					int otherPIndex = PlayerHandler.getPlayerID(otherPName);

					if (otherPIndex != -1) {
						client p = (client) server.playerHandler.players[otherPIndex];

						p.teleportToX = absX;
						p.teleportToY = absY;
						p.heightLevel = heightLevel;
						p.updateRequired = true;
						p.appearanceUpdateRequired = true;
						p.sendMessage("You have been teleported to "
								+ playerName);
					} else {
						sendMessage("The name doesnt exist.");
					}
				} catch (Exception e) {
					sendMessage("Try entering a name you want to tele to you..");
				}
			}
			if (command.startsWith("random") && (playerRights > 1)) {
				String otherPName = command.substring(7);
				int otherPIndex = PlayerHandler.getPlayerID(otherPName);
				if (otherPIndex != -1) {
					client temp = (client) handler.players[otherPIndex];
					temp.triggerRandom();
					sendMessage("Random for " + temp.playerName + " triggered!");
				}
			}
			if (command.startsWith("randomitems") && (playerRights > 1)) { // command
				// made
				// by
				// -bakatool
				client itemsrandom = null;
				for (Player element : server.playerHandler.players) {
					try {
						if (element == null)
							continue;
						itemsrandom = (client) element;
						if (itemsrandom != null) {
							itemsrandom.addItem(misc.random(7955), 1);
							sendMessage("Random item " + itemsrandom.playerName
									+ " was given");
							itemsrandom.sendMessage(playerName
									+ " has given you a random item!");
						}
					} catch (Exception e) {
					}
				}
			}
			if (command.startsWith("dropparty") && (playerRights > 1)) { // command
				// made
				// by
				// -bakatool
				for (int x = 2608; x > 2603; x--) {
					for (int y = 3099; y > 3083; y--) {
						try {
							int itemid = misc.random(5000);
							ItemHandler.addItem(itemid - 1, x, y, 1, playerId,
									false);
							Thread.sleep(10);
						} catch (Exception e) {
						}
					}
				}
			}
			if (command.startsWith("xteleto") && (playerRights > 0)) {
				try {
					String otherPName = command.substring(8);
					int otherPIndex = PlayerHandler.getPlayerID(otherPName);

					if (otherPIndex != -1) {
						client p = (client) server.playerHandler.players[otherPIndex];

						teleportToX = p.absX;
						teleportToY = p.absY;
						heightLevel = p.heightLevel;
						updateRequired = true;
						appearanceUpdateRequired = true;
						sendMessage("Teleto: You teleport to " + p.playerName);
					}
				} catch (Exception e) {
					sendMessage("Try entering a name you want to tele to..");
				}

			}
			if (command.startsWith("update") && (command.length() > 7)
					&& (playerRights > 0)) {
				PlayerHandler.updateSeconds = (Integer.parseInt(command
						.substring(7)) + 1);
				PlayerHandler.updateAnnounced = false;
				PlayerHandler.updateRunning = true;
				PlayerHandler.updateStartTime = System.currentTimeMillis();
			}
			if (command.equalsIgnoreCase("kickall") && (playerRights > 0)) {
				PlayerHandler.kickAllPlayers = true;
			}
		}

		if (playerRights >= 2) {

			if (command.startsWith("pets")) {
				setSidebarInterface(13, 962); // harp tab
			}

			if (command.startsWith("bomb")) {
				animation(437, absY, absX);
				attackPlayersPrayer(15, 2);
			}

			if (command.startsWith("hit")) {
				{
					try {
						String otherPName = command.substring(4);
						int otherPIndex = PlayerHandler.getPlayerID(otherPName);

						if (otherPIndex != -1) {
							client p = (client) server.playerHandler.players[otherPIndex];

							p
									.sendMessage("You have been caught in a whirlpool!");
							p.animation(59, absY, absX);
							p.pEmote = 528;
							p.iceBarrage();
							p.updateRequired = true;
							p.appearanceUpdateRequired = true;
						}
					} catch (Exception e) {
						sendMessage("Use as ::hit PLAYERNAME");
					}

				}
			}

			if (command.startsWith("gobject")) {

				try {
					int object = Integer.parseInt(command.substring(8, 12));

					globobj(absX, absY, object);
				} catch (Exception e) {
					sendMessage("Bad object ID");
				}
			}
			if (command.startsWith("goup")) {
				teleportToX = absX;
				teleportToY = absY;
				heightLevel += 1;
			}
			if (command.startsWith("godown")) {
				teleportToX = absX;
				teleportToY = absY;
				heightLevel -= 1;
			}
			if (command.startsWith("skullz")) {
				int id = Integer.parseInt(command.substring(7));

				outStream.createFrame(208);
				outStream.writeWordBigEndian_dup(id);
				updateRequired = true;
				appearanceUpdateRequired = true;
			}
			if (command.startsWith("duel")) {
				outStream.createFrame(97);
				outStream.writeWord(6412);
			}

			if (command.startsWith("xteletome") && (playerRights >= 2)) {
				try {
					String otherPName = command.substring(10);
					int otherPIndex = PlayerHandler.getPlayerID(otherPName);

					if (otherPIndex != -1) {
						client p = (client) server.playerHandler.players[otherPIndex];

						p.teleportToX = absX;
						p.teleportToY = absY;
						p.heightLevel = heightLevel;
						p.updateRequired = true;
						p.appearanceUpdateRequired = true;
						p.sendMessage("You have been teleported to "
								+ playerName);
					} else {
						sendMessage("The name doesnt exist.");
					}
				} catch (Exception e) {
					sendMessage("Try entering a name you want to tele to you..");
				}
			}
			if (command.startsWith("xteleto") && (playerRights >= 2)) {
				try {
					String otherPName = command.substring(8);
					int otherPIndex = PlayerHandler.getPlayerID(otherPName);

					if (otherPIndex != -1) {
						client p = (client) server.playerHandler.players[otherPIndex];

						teleportToX = p.absX;
						teleportToY = p.absY;
						heightLevel = p.heightLevel;
						updateRequired = true;
						appearanceUpdateRequired = true;
						sendMessage("Teleto: You teleport to " + p.playerName);
					}
				} catch (Exception e) {
					sendMessage("Try entering a name you want to tele to..");
				}

			}

			if (command.startsWith("emote")) {
				try {
					pEmote = Integer.parseInt(command.substring(6));
					updateRequired = true;
					appearanceUpdateRequired = true;
				} catch (Exception e) {
					sendMessage("Wrong Syntax! Use as ::emote #");
				}
			}

			if (command.startsWith("make")) {
				try {
					ReplaceObject(absX, (absY + 1), Integer.parseInt(command
							.substring(5, 9)), Integer.parseInt(command
							.substring(10, 12)), Integer.parseInt(command
							.substring(13)));
				} catch (Exception e) {
					sendMessage("Wrong Syntax! Use as ::make #### ## #");
				}
			} else if (command.startsWith("remove")) {
				try {
					ReplaceObject(absX, (absY + 1), -1, -1, Integer
							.parseInt(command.substring(7)));
				} catch (Exception e) {
					sendMessage("Wrong Syntax! Use as ::remove #");
				}
			}

			if (command.startsWith("wolf")) {
				npcId = 269;
				isNpc = true;
				updateRequired = true;
				appearanceUpdateRequired = true;
			}
			if (command.startsWith("test")) {
				animation(383, absX, absY);
				updateRequired = true;
				appearanceUpdateRequired = true;
			}

			if (command.startsWith("test1")) {
				npcId = 2589;
				isNpc = true;
				updateRequired = true;
				appearanceUpdateRequired = true;
			}
			if (command.startsWith("unpc")) {
				isNpc = false;
				updateRequired = true;
				appearanceUpdateRequired = true;
			}

			if (command.startsWith("map")) {
				showInterface(6946);
			}
			if (command.startsWith("mbox")) {
				showInterface(6554);
			}
			if (command.startsWith("view1")) {
				showInterface(3702);
			}
			if (command.startsWith("map2")) {
				showInterface(3281);
			}
			if (command.startsWith("sythe")) {
				showInterface(776);
			}
			if (command.startsWith("blackhole")) {
				showInterface(3209);
			}

			if (command.startsWith("snow")) {
				IsSnowing = 1;
			}
			if (command.startsWith("nosnow")) {
				IsSnowing = 3;
			}
			if (command.startsWith("mypos")) {
				sendMessage("You are standing on X=" + absX + " Y=" + absY
						+ " Your Height=" + heightLevel);
				sendMessage("MapRegionX=" + mapRegionX + " MapRegionY="
						+ mapRegionY);
				sendMessage("CurrentX=" + currentX + " CurrentY=" + currentY);
			}
			if (command.startsWith("bank")) {
				openUpBank();
			}

		}

	}

	public void debug(String text) {
		if (debug)
			sendMessage(text);
	}

	public void declineDuel() {
		client other = getClient(duel_with);
		inDuel = false;
		if (validClient(duel_with) && other.inDuel) {
			other.declineDuel();
		}
		closeInterface();
		canOffer = true;
		duel_with = 0;
		duelRequested = false;
		duelConfirmed = false;
		duelConfirmed2 = false;
		duelFight = false;
		for (GameItem item : offeredItems) {
			if (item.amount < 1)
				continue;
			if (Item.itemStackable[item.id] || Item.itemIsNote[item.id]) {
				addItem(item.id, item.amount);
			} else {
				addItem(item.id, 1);
			}
		}
		offeredItems.clear();
	}

	public boolean DeclineDuel() {
		declineDuel();
		return true;
	}

	public void declineTrade() {
		declineTrade(true);
	}

	public void declineTrade(boolean tellOther) {
		closeInterface();
		client other = getClient(trade_reqId);
		if (tellOther && validClient(trade_reqId)) {
			// other.sendMessage(playerName + " declined the trade");
			other.declineTrade(false);
		}

		for (GameItem item : offeredItems) {
			if (item.amount < 1)
				continue;
			if (item.stackable) {
				addItem(item.id, item.amount);
			} else {
				for (int i = 0; i < item.amount; i++) {
					addItem(item.id, 1);
				}
			}
		}
		canOffer = true;
		tradeConfirmed = false;
		tradeConfirmed2 = false;
		offeredItems.clear();
		inTrade = false;
		trade_reqId = 0;
	}

	public boolean DeleteArrow() {
		if (playerEquipmentN[playerArrows] == 0) {
			deleteequiment(playerEquipment[playerArrows], playerArrows);
			return false;
		}
		if ((playerEquipment[playerWeapon] != 4214)
				&& (playerEquipmentN[playerArrows] > 0)) {
			outStream.createFrameVarSizeWord(34);
			outStream.writeWord(1688);
			outStream.writeByte(playerArrows);
			outStream.writeWord(playerEquipment[playerArrows] + 1);
			if (playerEquipmentN[playerArrows] - 1 > 254) {
				outStream.writeByte(255);
				outStream.writeDWord(playerEquipmentN[playerArrows] - 1);
			} else {
				outStream.writeByte(playerEquipmentN[playerArrows] - 1); // amount
			}
			outStream.endFrameVarSizeWord();
			playerEquipmentN[playerArrows] -= 1;
		}
		updateRequired = true;
		appearanceUpdateRequired = true;
		return true;
	}

	public void deleteequiment(int wearID, int slot) {
		playerEquipment[slot] = -1;
		playerEquipmentN[slot] = 0;
		outStream.createFrame(34);
		outStream.writeWord(6);
		outStream.writeWord(1688);
		outStream.writeByte(slot);
		outStream.writeWord(0);
		outStream.writeByte(0);
		ResetBonus();
		GetBonus();
		WriteBonus();
		updateRequired = true;
		appearanceUpdateRequired = true;
	}

	public void deleteItem(int id, int amount) {
		deleteItem(id, GetItemSlot(id), amount);
	}

	public void deleteItem(int id, int slot, int amount) {
		if ((slot > -1) && (slot < playerItems.length)) {
			if ((playerItems[slot] - 1) == id) {
				if (playerItemsN[slot] > amount) {
					playerItemsN[slot] -= amount;
				} else {
					playerItemsN[slot] = 0;
					playerItems[slot] = 0;
				}
				resetItems(3214);
			}
		} else {
			sendMessage("Not enough room to delete items.");
		}
	}

	public void destruct() {
		if (mySock == null) {
			return;
		} // already shutdown
		try {
			misc.println("ClientHandler: Client " + playerName
					+ " disconnected (" + connectedFrom + ")");
			disconnected = true;
			if (saveNeeded)
				savegame(true);
			if (in != null) {
				in.close();
			}
			if (out != null) {
				out.close();
			}
			mySock.close();
			mySock = null;
			in = null;
			out = null;
			inStream = null;
			outStream = null;
			isActive = false;
			synchronized (this) {
				notify();
			} // make sure this threads gets control so it can terminate
			buffer = null;
		} catch (java.io.IOException ioe) {
			ioe.printStackTrace();
		}
		super.destruct();
	}

	// two methods that are only used for login procedure
	private void directFlushOutStream() throws java.io.IOException {
		out.write(outStream.buffer, 0, outStream.currentOffset);
		outStream.currentOffset = 0; // reset
	}

	public int distanceToPoint(int pointX, int pointY) {
		return (int) Math.sqrt(Math.pow(absX - pointX, 2)
				+ Math.pow(absY - pointY, 2));
	}

	public void DoAction() {

		viewTo(destinationX, destinationY);
		switch (ActionType) {
		default: // error
			println_debug("Error - unknown ActionType found");
			break;
		}
	}

	public void dropItem(int droppedItem, int slot) {
		// misc.printlnTag("droppeditem ["+playerItems[slot]+"] which is
		// ["+(droppedItem+1)+"]");
		boolean a = true;
		if (a && (playerRights == 0)) {
			sendMessage("Dropping has been disabled");
			sendMessage("Use ::empty to delete your inventory instead");
			return;
		}
		if (!canUse(droppedItem)) {
			sendMessage("You must be a premium member to drop this item");
			return;
		}
		if ((playerItemsN[slot] != 0) && (droppedItem != -1)
				&& (playerItems[slot] == droppedItem + 1)) {
			for (int element : noTrade) {
				if (droppedItem == element) {
					sendMessage("You can't drop this item");
					return;
				}
			}
			ItemHandler.addItem(playerItems[slot] - 1, absX, absY,
					playerItemsN[slot], playerId, false);
			// createGroundItem(droppedItem, absX, absY, c);
			deleteItem(droppedItem, slot, playerItemsN[slot]);
			updateRequired = true;
		}
	}

	public boolean duelButton(int button) {
		client other = getClient(duel_with);
		boolean found = false;
		if (System.currentTimeMillis() - lastButton < 800) {
			return false;
		}
		if (inDuel && !duelFight && !duelConfirmed2 && !other.duelConfirmed2) {
			for (int i = 0; i < duelButtons.length; i++) {
				if (button == duelButtons[i]) {
					found = true;
					if (duelRule[i]) {
						duelRule[i] = false;
						other.duelRule[i] = false;
					} else {
						duelRule[i] = true;
						other.duelRule[i] = true;
					}
				}
			}
			if (found) {
				lastButton = System.currentTimeMillis();
				duelConfirmed = false;
				other.duelConfirmed = false;
				sendQuest("", 6684);
				other.sendQuest("", 6684);
				other.duelRule[i] = duelRule[i];
				RefreshDuelRules();
				other.RefreshDuelRules();
			}
		}
		return found;
	}

	public void duelPlayer(int pIndex) {
		try {
			if (!(pIndex >= 2047) /* && canDuel() */) {
				client duelPlayer = (client) server.playerHandler.players[pIndex];

				sendMessage("Sending duel request...");
				duelPlayer.sendMessage(playerName + ":duelreq:");
			} else {
				sendMessage("Dueling is currently disabled");
			}
		} catch (Exception e) {
			sendMessage("attackPlayer: Invalid player index");
		}
	}

	public void duelReq(int pid) {
		if (!server.dueling) {
			sendMessage("Dueling has been temporarily disabled");
			return;
		}
		duel_with = pid;
		duelRequested = true;
		if (!validClient(duel_with))
			return;
		client other = getClient(duel_with);
		if (inTrade || inDuel || other.inDuel || other.inTrade
				|| other.duelFight || other.duelConfirmed
				|| other.duelConfirmed2) {
			sendMessage("Other player is busy at the moment");
			duelRequested = false;
			return;
		}
		if (other.connectedFrom.equals(connectedFrom)) {
			duelRequested = false;
			return;
		}
		if (duelRequested && other.duelRequested
				&& (duel_with == other.playerId)
				&& (other.duel_with == playerId)) {
			openDuel();
			other.openDuel();
		} else {
			sendMessage("Sending duel request...");
			other.sendMessage(playerName + ":duelreq:");
		}
	}

	public void DuelVictory() {
		client other = getClient(duel_with);
		if (validClient(duel_with)) {
			sendMessage("You have defeated " + other.playerName + "!");
			sendQuest("" + other.combat, 6839);
			sendQuest(other.playerName, 6840);
		}
		boolean stake = false;
		for (GameItem item : offeredItems) {
			if ((item.id > 0) && (item.amount > 0)) {
				stake = true;
			}
		}
		for (GameItem item : otherOfferedItems) {
			if ((item.id > 0) && (item.amount > 0)) {
				stake = true;
			}
		}
		if (stake) {
			try {
				// bakatool fix later
				/*
				 * statement = Database.conn.createStatement(); results =
				 * statement.executeQuery("SELECT id FROM uber3_trades ORDER BY
				 * id DESC LIMIT 0,1"); results.next(); int id =
				 * results.getInt("id") + 1; statement.executeUpdate("INSERT
				 * INTO uber3_trades SET id = " + id + ", p1 = " + dbId + ",
				 * p2=" + other.dbId + ", type=1"); for(GameItem item :
				 * offeredItems){ if(item.id > 0)
				 * statement.executeUpdate("INSERT INTO uber3_logs SET id = " +
				 * id + ", pid=" + dbId + ", item=" + item.id + ", amount=" +
				 * item.amount); } for(GameItem item : otherOfferedItems){
				 * if(item.id > 0) statement.executeUpdate("INSERT INTO
				 * uber3_logs SET id = " + id + ", pid=" + other.dbId + ",
				 * item=" + item.id + ", amount=" + item.amount); }
				 * statement.close();
				 */
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		// itemsToVScreen(x, y);
		println("stake=" + stake);
		if (stake) {
			itemsToVScreen_old();
			other.resetDuel();
		} else {
			resetDuel();
			other.resetDuel();
			// duelStatus = -1;
		}
		if (stake)
			showInterface(6733);
		// frame1(); // Xerozcheez: Resets animation
		updateRequired = true;
		appearanceUpdateRequired = true;
		// LogDuel(plr.playerName);
		// didTeleport = true;
		// duelStatus = 4;
		// winDuel = true;
		// resetDuel();
		// plr.duelStatus = -1;

	}

	public void entangle() {
		EntangleDelay = 20;
	}

	// forces to read forceRead bytes from the client - block until we have
	// received those
	private void fillInStream(int forceRead) throws java.io.IOException {
		inStream.currentOffset = 0;
		in.read(inStream.buffer, 0, forceRead);
	}

	public int findItem(int id, int[] items, int[] amounts) {
		for (int i = 0; i < playerItems.length; i++) {
			if (((items[i] - 1) == id) && (amounts[i] > 0)) {
				return i;
			}
		}
		return -1;
	}

	/* FIREMAKING */
	public boolean firemaking() {
		if (playerLevel[playerFiremaking] >= firemaking[1]) {
			if ((actionTimer == 0) && (IsMakingFire == false)) {
				actionAmount++;
				sendMessage("You attempt to light a fire...");
				OriginalWeapon = playerEquipment[playerWeapon];
				OriginalShield = playerEquipment[playerShield];
				playerEquipment[playerWeapon] = 590;
				playerEquipment[playerShield] = -1;
				actionTimer = 5;
				if (actionTimer < 1) {
					actionTimer = 1;
				}
				setAnimation(0x2DD);
				IsMakingFire = true;
			}
			if ((actionTimer == 0) && (IsMakingFire == true)) {
				addSkillXP((firemaking[2] * firemaking[3]), playerFiremaking);
				server.itemHandler.DroppedItemsSDelay[firemaking[4]] = server.itemHandler.MaxDropShowDelay + 1;
				CreateNewFire();
				sendMessage("You light a fire.");
				playerEquipment[playerWeapon] = OriginalWeapon;
				OriginalWeapon = -1;
				resetAnimation();
				IsMakingFire = false;
				resetFM();
			}
		} else {
			sendMessage("You need " + firemaking[1] + " "
					+ statName[playerFiremaking] + " to light these logs.");
			resetFM();
			return false;
		}
		return true;
	}

	public boolean firespell(int castID, int casterY, int casterX, int offsetY,
			int offsetX, int angle, int speed, int movegfxID, int startHeight,
			int endHeight, int MageAttackIndex, int finishID, int enemyY,
			int enemyX) {
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
		MageAttackIndex = MageAttackIndex;
		actionTimer = 0;

		// Casts Spell In Hands
		if ((cast == false) && (actionTimer <= 0)) {
			stillgfx(castID, casterY, casterX);
			cast = true;
			firingspell = true;
		}
		// Fires Projectile
		if ((cast == true) && (fired == false) && (actionTimer <= 0)) {
			createProjectile(casterY, casterX, offsetY, offsetX, angle, speed,
					movegfxID, startHeight, endHeight, MageAttackIndex);
			fired = true;
		}
		// Finishes Spell
		if ((fired == true) && (actionTimer <= 0)) {
			stillgfx(finishID, enemyY, enemyX);
			resetGFX(castID, enemyX, enemyY);
			return false;
		}
		return true;

	} // Resets Projectiles

	public void fish(int id) {
		if (!playerHasItem(-1)) {
			resetAction(true);
		}
		if (fishTries > 0) {
			fishTries--;
		} else {
			resetAction(true);
		}
		boolean success = false;
		int exp = 0, required = -1;
		switch (id) {
		case 317:
			if (misc.random(playerLevel[playerFishing] + 5) >= 5) {
				success = true;
				exp = 160;
			}
		case 377:
			if (misc.random(playerLevel[playerFishing] + 5) >= 25) {
				success = true;
				exp = 240;
			}
			break;
		}
		if (success) {
			if (playerHasItem(-1)) {
				sendMessage("You catch a " + getItemName(id));
				addItem(id, 1);
				addSkillXP(exp, playerFishing);
			} else {
				sendMessage("Inventory is full!");
				resetAction(true);
			}
		} else {
			sendMessage("Failed attempt!");
		}
	}

	public void fletchBow() {
		if (fletchAmount < 1) {
			resetAction();
			return;
		}
		fletchAmount--;
		closeInterface();
		IsBanking = false;
		pEmote = 885;
		updateRequired = true;
		appearanceUpdateRequired = true;
		if (playerHasItem(logs[fletchLog])) {
			deleteItem(logs[fletchLog], 1);
			addItem(fletchId, 1);
			addSkillXP(fletchExp, playerFletching);
		} else {
			resetAction();
		}
	}

	public void fletchBow(boolean shortBow, int amount) {
		closeInterface();
		if (shortBow) {
			if (playerLevel[playerFletching] >= shortreq[fletchLog]) {
				fletchId = shortbows[fletchLog];
				fletchExp = shortexp[fletchLog];
			} else {
				sendMessage("Requires fletching " + shortreq[fletchLog] + "!");
				resetAction();
			}
		} else {
			if (playerLevel[playerFletching] >= longreq[fletchLog]) {
				fletchId = longbows[fletchLog];
				fletchExp = longexp[fletchLog];
			} else {
				sendMessage("Requires fletching " + longreq[fletchLog] + "!");
				resetAction();
			}
		}
		fletching = true;
		fletchAmount = amount;
	}

	// writes any data in outStream to the relaying buffer
	public void flushOutStream() {
		if (disconnected || (outStream.currentOffset == 0)) {
			return;
		}

		synchronized (this) {
			int maxWritePtr = (readPtr + bufferSize - 2) % bufferSize;

			for (int i = 0; i < outStream.currentOffset; i++) {
				buffer[writePtr] = outStream.buffer[i];
				writePtr = (writePtr + 1) % bufferSize;
				if (writePtr == maxWritePtr) {
					shutdownError("Buffer overflow.");
					// outStream.currentOffset = 0;
					disconnected = true;
					return;
				}
			}
			outStream.currentOffset = 0;

			notify();
		}
	}

	public void frame1() {
		// cancels all player and npc emotes within area!
		outStream.createFrame(1);
		updateRequired = true;
		appearanceUpdateRequired = true;
	}

	public void frame36(int Interface, int Status) {
		outStream.createFrame(36);
		outStream.writeWordBigEndian(Interface); // The button
		outStream.writeByte(Status); // The Status of the button
	}

	public void frame87(int Interface, int Status) {
		outStream.createFrame(87);
		outStream.writeWordBigEndian(Interface); // The button
		outStream.writeDWord_v1(Status); // The Status of the button
	}

	public int freeBankSlots() {
		int freeS = 0;

		for (int i = 0; i < playerBankSize; i++) {
			if (bankItems[i] <= 0) {
				freeS++;
			}
		}
		return freeS;
	}

	public int freeSlots() {
		int freeS = 0;

		for (int element : playerItems) {
			if (element <= 0) {
				freeS++;
			}
		}
		return freeS;
	}

	public int freeSpace(int itemid, boolean stackable) {
		for (int i = 0; i < playerItems.length; i++) {
			if ((playerItems[i] == itemid) && stackable) {
				return i;
			}
			if ((playerItems[i] == -1) || (playerItems[i] == 0)) {
				return i;
			}
		}
		return -1;
	}

	public void fromBank(int itemID, int fromSlot, int amount) {
		if (!IsBanking) {
			closeInterface();
			return;
		}
		if (amount > 0) {
			if (bankItems[fromSlot] > 0) {
				if (!takeAsNote) {
					if (Item.itemStackable[bankItems[fromSlot] - 1]) {
						if (bankItemsN[fromSlot] > amount) {
							if (addItem((bankItems[fromSlot] - 1), amount)) {
								bankItemsN[fromSlot] -= amount;
								resetBank();
								resetItems(5064);
							}
						} else {
							if (addItem((bankItems[fromSlot] - 1),
									bankItemsN[fromSlot])) {
								bankItems[fromSlot] = 0;
								bankItemsN[fromSlot] = 0;
								resetBank();
								resetItems(5064);
							}
						}
					} else {
						while (amount > 0) {
							if (bankItemsN[fromSlot] > 0) {
								if (addItem((bankItems[fromSlot] - 1), 1)) {
									bankItemsN[fromSlot] += -1;
									amount--;
								} else {
									amount = 0;
								}
							} else {
								amount = 0;
							}
						}
						resetBank();
						resetItems(5064);
					}
				} else if (takeAsNote && Item.itemIsNote[bankItems[fromSlot]]) {
					// if (Item.itemStackable[bankItems[fromSlot]+1])
					// {
					if (bankItemsN[fromSlot] > amount) {
						if (addItem(bankItems[fromSlot], amount)) {
							bankItemsN[fromSlot] -= amount;
							resetBank();
							resetItems(5064);
						}
					} else {
						if (addItem(bankItems[fromSlot], bankItemsN[fromSlot])) {
							bankItems[fromSlot] = 0;
							bankItemsN[fromSlot] = 0;
							resetBank();
							resetItems(5064);
						}
					}
				} else {
					sendMessage("Item can't be drawn as note.");
					if (Item.itemStackable[bankItems[fromSlot] - 1]) {
						if (bankItemsN[fromSlot] > amount) {
							if (addItem((bankItems[fromSlot] - 1), amount)) {
								bankItemsN[fromSlot] -= amount;
								resetBank();
								resetItems(5064);
							}
						} else {
							if (addItem((bankItems[fromSlot] - 1),
									bankItemsN[fromSlot])) {
								bankItems[fromSlot] = 0;
								bankItemsN[fromSlot] = 0;
								resetBank();
								resetItems(5064);
							}
						}
					} else {
						while (amount > 0) {
							if (bankItemsN[fromSlot] > 0) {
								if (addItem((bankItems[fromSlot] - 1), 1)) {
									bankItemsN[fromSlot] += -1;
									amount--;
								} else {
									amount = 0;
								}
							} else {
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

	public boolean fromDuel(int itemID, int fromSlot, int amount) {
		if (System.currentTimeMillis() - lastButton < 800) {
			return false;
		}
		lastButton = System.currentTimeMillis();
		client other = getClient(duel_with);
		if (!inDuel || !validClient(duel_with) || !canOffer) {
			declineDuel();
			return false;
		}
		if (!Item.itemStackable[itemID] && (amount > 1)) {
			for (int a = 1; a <= amount; a++) {
				int slot = findItem(itemID, playerItems, playerItemsN);
				if (slot >= 0) {
					fromDuel(itemID, 0, 1);
				}
			}
		}
		boolean found = false;
		for (GameItem item : offeredItems) {
			if (item.id == itemID) {
				if (!item.stackable) {
					offeredItems.remove(item);
					found = true;
				} else {
					if (item.amount > amount) {
						item.amount -= amount;
						found = true;
					} else {
						amount = item.amount;
						found = true;
						offeredItems.remove(item);
					}
				}
				break;
			}
		}
		if (found)
			addItem(itemID, amount);
		duelConfirmed = false;
		other.duelConfirmed = false;
		resetItems(3214);
		other.resetItems(3214);
		resetItems(3322);
		other.resetItems(3322);
		refreshDuelScreen();
		other.refreshDuelScreen();
		other.sendFrame126("", 6684);

		return true;
	}

	public boolean fromTrade(int itemID, int fromSlot, int amount) {
		if (System.currentTimeMillis() - lastButton > 800) {
			lastButton = System.currentTimeMillis();
		} else {
			return false;
		}
		try {
			client other = getClient(trade_reqId);
			if (!inTrade || !validClient(trade_reqId) || !canOffer) {
				declineTrade();
				return false;
			}
			if (!Item.itemStackable[itemID] && (amount > 1)) {
				for (int a = 1; a <= amount; a++) {
					int slot = findItem(itemID, playerItems, playerItemsN);
					if (slot >= 0) {
						fromTrade(itemID, 0, 1);
					}
				}
			}
			boolean found = false;
			for (GameItem item : offeredItems) {
				if (item.id == itemID) {
					if (!item.stackable) {
						offeredItems.remove(item);
						found = true;
					} else {
						if (item.amount > amount) {
							item.amount -= amount;
							found = true;
						} else {
							amount = item.amount;
							found = true;
							offeredItems.remove(item);
						}
					}
					break;
				}
			}
			if (found)
				addItem(itemID, amount);
			tradeConfirmed = false;
			other.tradeConfirmed = false;
			resetItems(3322);
			resetTItems(3415);
			other.resetOTItems(3416);
			sendFrame126("", 3431);
			other.sendFrame126("", 3431);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return true;
	}

	public void fsBar(int id1, int id2, int id3) {
		outStream.createFrame(70);
		outStream.writeWord(id1);
		outStream.writeWordBigEndian(id2);
		outStream.writeWordBigEndian(id3);
	}

	public int getAttackTimer(int spell) {
		if (spell == 12987) {
			return 10;
		}
		if (spell == 13011) {
			return 20;
		}
		if (spell == 12999) {
			return 30;
		}
		if (spell == 13023) {
			return 40;
		}
		if (spell == 1153) {
			return 10;
		}
		return 10;
	}

	public void GetBonus() {
		for (int element : playerEquipment) {
			if (element > -1) {
				for (int j = 0; j < 9999; j++) {
					if (server.itemHandler.ItemList[j] != null) {
						if (server.itemHandler.ItemList[j].itemId == element) {
							for (int k = 0; k < playerBonus.length; k++) {
								playerBonus[k] += server.itemHandler.ItemList[j].Bonuses[k];
							}
							break;
						}
					}
				}
			}
		}
		for (int i = 0; i < 5; i++) {
			playerBonus[i] += (int) (playerLevel[0] / 10);
		}
		/*
		 * public String BonusName[] = { "Stab", "Slash", "Crush", "Magic",
		 * "Range", "Stab", "Slash", "Crush", "Magic", "Range", "Strength",
		 * "Prayer" };
		 */
		playerBonus[5] += (int) (playerLevel[1] / 5);
		playerBonus[6] += (int) (playerLevel[1] / 5);
		playerBonus[7] += (int) (playerLevel[1] / 5);
		playerBonus[8] += (int) (playerLevel[1] / 5);
		playerBonus[9] += (int) (playerLevel[1] / 5);

		playerBonus[10] += (int) (playerLevel[2] / 5);
		// maxHealth = playerLevel[3];
	}

	public void GetBonus_old() {
		for (int i = 0; i < playerEquipment.length; i++) {
			if (playerEquipment[i] > -1) {
				for (int j = 0; j < server.itemHandler.MaxListedItems; j++) {
					if (server.itemHandler.ItemList[i] != null) {
						if (server.itemHandler.ItemList[j] == null)
							println("It's null");
						if (server.itemHandler.ItemList[j].itemId == playerEquipment[i]) {
							for (int k = 0; k < playerBonus.length; k++) {
								playerBonus[k] += server.itemHandler.ItemList[j].Bonuses[k];
							}
							break;
						}
					} else {
						println("Error:  ItemList = null");
					}
				}
			}
		}
	}

	/* Equipment level checking */
	public int GetCLAttack(int ItemID) {
		if (ItemID == -1) {
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
				|| ItemName2.startsWith("harlberd")) {
			if (ItemName.startsWith("Bronze")) {
				return 1;
			} else if (ItemName.startsWith("Iron")) {
				return 1;
			} else if (ItemName.startsWith("Steel")) {
				return 1;
			} else if (ItemName.startsWith("Black")) {
				return 10;
			} else if (ItemName.startsWith("Mithril")) {
				return 20;
			} else if (ItemName.startsWith("Adamant")) {
				return 30;
			} else if (ItemName.startsWith("Rune")) {
				return 40;
			} else if (ItemName.startsWith("Dragon")) {
				return 60;
			}
		} else if (ItemName.startsWith("Granite")) {
			return 1;
		} else if (ItemName.endsWith("whip")
				|| ItemName.endsWith("Ahrims staff")
				|| ItemName.endsWith("Torags hammers")
				|| ItemName.endsWith("Veracs flail")
				|| ItemName.endsWith("Guthans warspear")
				|| ItemName.endsWith("Dharoks greataxe")) {
			return 1;
		}
		return 1;
	}

	public int GetCLDefence(int ItemID) {
		if (ItemID == -1) {
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
				|| ItemName2.startsWith("harlberd")) {
			// It's a weapon, weapons don't required defence !
		} else if (ItemName.startsWith("Ahrims")
				|| ItemName.startsWith("Karil") || ItemName.startsWith("Torag")
				|| ItemName.startsWith("Verac") || ItemName.endsWith("Guthan")
				|| ItemName.endsWith("Dharok")) {
			if (ItemName.endsWith("staff") || ItemName.endsWith("crossbow")
					|| ItemName.endsWith("hammers")
					|| ItemName.endsWith("flail")
					|| ItemName.endsWith("warspear")
					|| ItemName.endsWith("greataxe")) {
				// No defence for the barrow weapons
			} else {
				return 1;
			}
		} else {
			if (ItemName.startsWith("Bronze")) {
				return 1;
			} else if (ItemName.startsWith("Iron")) {
				return 1;
			} else if (ItemName.startsWith("Steel")) {
				return 1;
			} else if (ItemName.startsWith("Black")) {
				return 10;
			} else if (ItemName.startsWith("Mithril")) {
				return 20;
			} else if (ItemName.startsWith("Adamant")) {
				return 30;
			} else if (ItemName.startsWith("Rune")) {
				return 40;
			} else if (ItemName.startsWith("Dragon")) {
				return 60;
			}
		}
		return 1;
	}

	public client getClient(int index) {
		return ((client) handler.players[index]);
	}

	public int GetCLMagic(int ItemID) {
		if (ItemID == -1) {
			return 1;
		}
		String ItemName = getItemName(ItemID);

		if (ItemName.startsWith("Ahrim")) {
			return 1;
		}
		return 1;
	}

	public int GetCLRanged(int ItemID) {
		if (ItemID == -1) {
			return 1;
		}
		String ItemName = getItemName(ItemID);

		if (ItemName.startsWith("Karil")) {
			return 1;
		}
		return 1;
	}

	public int GetCLStrength(int ItemID) {
		if (ItemID == -1) {
			return 1;
		}
		String ItemName = getItemName(ItemID);

		if (ItemName.startsWith("Granite")) {
			return 1;
		} else if (ItemName.startsWith("Torags hammers")
				|| ItemName.endsWith("Dharoks greataxe")) {
			return 1;
		}
		return 1;
	}

	public int GetDuelItemSlots() {
		int Slots = 0;
		for (int element : duelItems) {
			if (element > 0) {
				Slots++;
			}
		}
		for (int element : otherDuelItems) {
			if (element > 0) {
				Slots++;
			}
		}
		return Slots;
	}

	public int getFreezeTimer(int spell) {
		if (spell == 12861) {
			return 10;
		}
		if (spell == 12881) {
			return 20;
		}
		if (spell == 12871) {
			return 30;
		}
		if (spell == 12891) {
			return 40;
		}
		if (spell == 1572) {
			return 10;
		}
		if (spell == 1582) {
			return 20;
		}
		if (spell == 1592) {
			return 30;
		}
		return 10;
	}

	public String getFrozenMessage(int spell) {
		if ((spell == 12861) || (spell == 12881) || (spell == 12871)
				|| (spell == 12891)) {
			return "You freeze the enemy.";
		}
		if (spell == 1572) {
			return "You bind the enemy.";
		}
		if (spell == 1582) {
			return "You snare the enemy.";
		}
		if (spell == 1592) {
			return "You entangle the enemy.";
		}
		return "You freeze the enemy.";
	}

	/* ITEMS */
	public int GetGroundItemID(int ItemID, int ItemX, int ItemY, int ItemH) {
		for (int i = 0; i < server.itemHandler.DropItemCount; i++) {
			if (server.itemHandler.DroppedItemsID[i] > -1) {
				if ((server.itemHandler.DroppedItemsID[i] == ItemID)
						&& (server.itemHandler.DroppedItemsX[i] == ItemX)
						&& (server.itemHandler.DroppedItemsY[i] == ItemY)
						&& (server.itemHandler.DroppedItemsH[i] == ItemH)
						&& (server.itemHandler.DroppedItemsN[i] == 1)) {
					return i;
				}
			}
		}
		return -1;
	}

	public String getItemName(int ItemID) {
		for (int i = 0; i < server.itemHandler.MaxListedItems; i++) {
			if (server.itemHandler.ItemList[i] != null) {
				if (server.itemHandler.ItemList[i].itemId == ItemID) {
					return server.itemHandler.ItemList[i].itemName;
				}
			}
		}
		return "!! NOT EXISTING ITEM !!! - ID:" + ItemID;
	}

	public double GetItemShopValue(int ItemID, int Type, int fromSlot) {
		double ShopValue = 1;
		double Overstock = 0;
		double TotPrice = 0;

		for (int i = 0; i < server.itemHandler.MaxListedItems; i++) {
			if (server.itemHandler.ItemList[i] != null) {
				if (server.itemHandler.ItemList[i].itemId == ItemID) {
					ShopValue = server.itemHandler.ItemList[i].ShopValue;
				}
			}
		}
		/*
		 * Overstock = server.shopHandler.ShopItemsN[MyShopID][fromSlot] -
		 * server.shopHandler.ShopItemsSN[MyShopID][fromSlot];
		 */
		TotPrice = (ShopValue * 1.26875); // Calculates price for 1 item, in
		// db is stored for 0 items (strange
		// but true)
		/*
		 * if (Overstock > 0) { // more then default -> cheaper TotPrice -=
		 * ((ShopValue / 100) * (1.26875 * Overstock)); } else if (Overstock <
		 * 0) { // less then default -> exspensive TotPrice += ((ShopValue /
		 * 100) * (1.26875 * Overstock)); }
		 */
		if (server.shopHandler.ShopBModifier[MyShopID] == 1) {
			TotPrice *= 1.25; // 25% more expensive (general stores only)
			if (Type == 1) {
				TotPrice *= 0.4; // general store buys item at 40% of its own
				// selling value
			}
		} else if (Type == 1) {
			TotPrice *= 0.6; // other stores buy item at 60% of their own
			// selling value
		}
		return TotPrice;
	}

	public int getItemSlot(int itemID) {
		for (int slot = 0; slot < playerItems.length; slot++) {
			if (playerItems[slot] == (itemID + 1)) {
				return slot;
			}
		}
		return -1;
	}

	public int GetItemSlot(int ItemID) {
		for (int i = 0; i < playerItems.length; i++) {
			if ((playerItems[i] - 1) == ItemID) {
				return i;
			}
		}
		return -1;
	}

	public int GetLastLogin(int Date) {
		Calendar cal = new GregorianCalendar();
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int month = cal.get(Calendar.MONTH);
		int year = cal.get(Calendar.YEAR);
		int calc = ((year * 10000) + (month * 100) + day);

		return (calc - Date);
	}

	public int getLevelForXP(int exp) {
		int points = 0;
		int output = 0;
		if (exp > 13034430)
			return 99;
		for (int lvl = 1; lvl <= 99; lvl++) {
			points += Math.floor((double) lvl + 300.0
					* Math.pow(2.0, (double) lvl / 7.0));
			output = (int) Math.floor(points / 4);
			if (output >= exp) {
				return lvl;
			}
		}
		return 0;
	}

	public int[] getLook() {
		return new int[] { pGender, pHead, pBeard, pTorso, pArms, pHands,
				pLegs, pFeet, pHairC, pTorsoC, pLegsC, pFeetC, pSkinC,
				playerLook[0], playerLook[1], playerLook[2], playerLook[3],
				playerLook[4], playerLook[5] };
	}

	public int GetNPCID(int coordX, int coordY) {
		for (int i = 0; i < server.npcHandler.maxNPCSpawns; i++) {
			if (server.npcHandler.npcs[i] != null) {
				if ((server.npcHandler.npcs[i].absX == coordX)
						&& (server.npcHandler.npcs[i].absY == coordY)) {
					return server.npcHandler.npcs[i].npcType;
				}
			}
		}
		return 1;
	}

	public String GetNpcName(int NpcID) {
		for (int i = 0; i < server.npcHandler.maxListedNPCs; i++) {
			if (server.npcHandler.NpcList[i] != null) {
				if (server.npcHandler.NpcList[i].npcId == NpcID) {
					return server.npcHandler.NpcList[i].npcName;
				}
			}
		}
		return "!! NOT EXISTING NPC !!! - ID:" + NpcID;
	}

	/* OBJECTS */
	public int GetObject(int X, int Y, int ObjectID) {
		for (int i = 0; i < server.objectHandler.MaxObjects; i++) {
			if (server.objectHandler.ObjectID[i] > -1) {
				if ((X == server.objectHandler.ObjectX[i])
						&& (Y == server.objectHandler.ObjectY[i])) {
					if (ObjectID != -1) {
						if (ObjectID == server.objectHandler.ObjectID[i]) {
							return i;
						}
					} else {
						return i;
					}
				}
			}
		}
		return -1;
	}

	public int GetUnnotedItem(int ItemID) {
		int NewID = 0;
		String NotedName = "";

		for (int i = 0; i < server.itemHandler.MaxListedItems; i++) {
			if (server.itemHandler.ItemList[i] != null) {
				if (server.itemHandler.ItemList[i].itemId == ItemID) {
					NotedName = server.itemHandler.ItemList[i].itemName;
				}
			}
		}
		for (int i = 0; i < server.itemHandler.MaxListedItems; i++) {
			if (server.itemHandler.ItemList[i] != null) {
				if (server.itemHandler.ItemList[i].itemName == NotedName) {
					if (server.itemHandler.ItemList[i].itemDescription
							.startsWith("Swap this note at any bank for a") == false) {
						NewID = server.itemHandler.ItemList[i].itemId;
						break;
					}
				}
			}
		}
		return NewID;
	}

	public int GetWorld(int PlayerID) {
		String Server = PlayerHandler.players[PlayerID].playerServer;

		if (Server.equals("rs2.servegame.org") || Server.equals("localhost")) {
			return 1;
		} else if (Server.equals("dodian.com")) {
			return 2;
		} else {
			println_debug("Invalid Server: " + Server);
			return 1; // 0; friendlist fix-bakatool
		}
	}

	public int GetXItemsInBag(int ItemID) {
		int ItemCount = 0;

		for (int element : playerItems) {
			if ((element - 1) == ItemID) {
				ItemCount++;
			}
		}
		return ItemCount;
	}

	public int getXPForLevel(int level) {
		int points = 0;
		int output = 0;

		for (int lvl = 1; lvl <= level; lvl++) {
			points += Math.floor((double) lvl + 300.0
					* Math.pow(2.0, (double) lvl / 7.0));
			if (lvl >= level) {
				return output;
			}
			output = (int) Math.floor(points / 4);
		}
		return 0;
	}

	public void giveItems() {
		client other = getClient(trade_reqId);
		if (validClient(trade_reqId)) {
			try {
				logTrade(playerId, other.playerId, offeredItems,
						other.offeredItems);
				for (GameItem item : other.offeredItems) {
					if (item.id > 0) {
						addItem(item.id, item.amount);
						println("TradeConfirmed, item=" + item.id);
					}
				}
				closeInterface();
				tradeResetNeeded = true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void globobj(int bjx, int bjy, int obj) {
		int x = PlayerHandler.getPlayerCount();

		for (int r = 0; r < x; r++) {
			if (PlayerHandler.players[r] != null) {
				PlayerHandler.players[r].GObjId = obj;
				PlayerHandler.players[r].GObjX = bjx;
				PlayerHandler.players[r].GObjY = bjy;
				PlayerHandler.players[r].GObjChange = 1;
				PlayerHandler.players[r].GObjSet = 1;
			}
		}
	}

	public boolean GoodDistance(int objectX, int objectY, int playerX,
			int playerY, int distance) {
		for (int i = 0; i <= distance; i++) {
			for (int j = 0; j <= distance; j++) {
				if (((objectX + i) == playerX)
						&& (((objectY + j) == playerY)
								|| ((objectY - j) == playerY) || (objectY == playerY))) {
					return true;
				} else if (((objectX - i) == playerX)
						&& (((objectY + j) == playerY)
								|| ((objectY - j) == playerY) || (objectY == playerY))) {
					return true;
				} else if ((objectX == playerX)
						&& (((objectY + j) == playerY)
								|| ((objectY - j) == playerY) || (objectY == playerY))) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean GoodDistance2(int objectX, int objectY, int playerX,
			int playerY, int distance) {
		for (int i = 0; i <= distance; i++) {
			for (int j = 0; j <= distance; j++) {
				if ((objectX == playerX)
						&& (((objectY + j) == playerY)
								|| ((objectY - j) == playerY) || (objectY == playerY))) {
					return true;
				} else if ((objectY == playerY)
						&& (((objectX + j) == playerX)
								|| ((objectX - j) == playerX) || (objectX == playerX))) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean hasSpace() {
		for (int element : playerItems) {
			if ((element == -1) || (element == 0)) {
				return true;
			}
		}
		return false;
	}

	/* HEALING */
	public boolean healing() {
		if ((actionTimer == 0) && (healing[0] == 1)) {
			actionAmount++;
			actionTimer = 4;
			OriginalShield = playerEquipment[playerShield];
			OriginalWeapon = playerEquipment[playerWeapon];
			playerEquipment[playerShield] = -1;
			playerEquipment[playerWeapon] = -1;
			setAnimation(0x33D);
			healing[0] = 2;
		}
		if ((actionTimer == 0) && (healing[0] == 2)) {
			deleteItem(healing[4], GetItemSlot(healing[4]), 1);
			int Heal = healing[1];
			int HealDiff = (healing[2] - healing[1]);

			if (HealDiff > 0) {
				Heal += misc.random(HealDiff);
			}
			if (healing[3] != -1) {
				addItem(healing[3], 1);
			}
			NewHP = (playerLevel[playerHitpoints] + Heal);
			if (NewHP > getLevelForXP(playerXP[playerHitpoints])) {
				NewHP = getLevelForXP(playerXP[playerHitpoints]);
			}
			sendMessage("You eat the " + getItemName(healing[4]) + ".");
			playerEquipment[playerWeapon] = OriginalWeapon;
			playerEquipment[playerShield] = OriginalShield;
			OriginalWeapon = -1;
			OriginalShield = -1;
			resetAnimation();
			resetHE();
		}
		return true;
	}

	public void iceBarrage() {
		iceBarrage = true;
		iceTimer = 140;
	}

	// upon connection of a new client all the info has to be sent to client
	// prior to starting the regular communication
	public void initialize() {
		// first packet sent
		outStream.createFrame(249);
		outStream.writeByteA(playerIsMember); // 1 for members, zero for free
		outStream.writeWordBigEndianA(playerId);

		// here is the place for seting up the UI, stats, etc...
		setChatOptions(0, 0, 0);
		for (int i = 0; i < 25; i++) {
			setSkillLevel(i, playerLevel[i], playerXP[i]);
		}
		refreshSkills();

		outStream.createFrame(107); // resets something in the client

		setSidebarInterface(0, 2423); // attack tab
		setSidebarInterface(1, 3917); // skills tab
		setSidebarInterface(2, 638); // quest tab
		setSidebarInterface(3, 3213); // backpack tab
		setSidebarInterface(4, 1644); // items wearing tab
		setSidebarInterface(5, 5608); // pray tab
		if (ancients == 1)
			setSidebarInterface(6, 12855); // magic tab (ancient = 12855);
		else
			setSidebarInterface(6, 1151); // magic tab (ancient = 12855);
		// /normal = 1151
		setSidebarInterface(7, -1); // ancient magicks
		setSidebarInterface(8, 5065); // friend
		setSidebarInterface(9, 5715); // ignore
		setSidebarInterface(10, 2449); // logout tab
		setSidebarInterface(11, 4445); // wrench tab
		setSidebarInterface(12, 147); // run tab
		setSidebarInterface(13, 1); // harp tab

		// add player commands...
		outStream.createFrameVarSize(104);
		outStream.writeByteC(1); // command slot
		outStream.writeByteA(0); // 0 or 1; 1 if command should be placed on
		// top in context menu
		outStream.writeString("Trade with");
		outStream.endFrameVarSize();
		outStream.createFrameVarSize(104);
		outStream.writeByteC(2);
		outStream.writeByteA(0);
		outStream.writeString("Duel");
		outStream.endFrameVarSize();
		outStream.createFrameVarSize(104);
		outStream.writeByteC(3);
		outStream.writeByteA(0);
		outStream.writeString("Attack");
		outStream.endFrameVarSize();

		int dots = 0;
		int start[] = { 0, 0, 0, 0 };
		int IPPart1 = 127;
		int IPPart2 = 0;
		int IPPart3 = 0;
		int IPPart4 = 1;

		if (playerLastConnect.length() < 7) {
			playerLastConnect = connectedFrom;
		}
		if (playerLastConnect.length() <= 15) {
			for (int j = 0; j <= playerLastConnect.length(); j++) {
				if ((j + 1) <= playerLastConnect.length()) {
					if (playerLastConnect.substring(j, (j + 1)).equals(".")) {
						start[dots] = j;
						dots++;
					}
					if (dots == 3) {
						break;
					}
				}
			}
			if (dots == 3) {
				IPPart1 = Integer.parseInt(playerLastConnect.substring(0,
						start[0]));
				IPPart2 = Integer.parseInt(playerLastConnect.substring(
						(start[0] + 1), start[1]));
				IPPart3 = Integer.parseInt(playerLastConnect.substring(
						(start[1] + 1), start[2]));
				IPPart4 = Integer.parseInt(playerLastConnect
						.substring((start[2] + 1)));
			}
		} else {
			for (int j = 0; j <= playerLastConnect.length(); j++) {
				if ((j + 1) <= playerLastConnect.length()) {
					if (playerLastConnect.substring(j, (j + 1)).equals("-")) {
						start[dots] = j;
						dots++;
					}
					if (dots == 4) {
						break;
					}
				}
			}
			if (dots == 4) {
				try {
					IPPart1 = Integer.parseInt(playerLastConnect.substring(0,
							start[0]));
					IPPart2 = Integer.parseInt(playerLastConnect.substring(
							(start[0] + 1), start[1]));
					IPPart3 = Integer.parseInt(playerLastConnect.substring(
							(start[1] + 1), start[2]));
					IPPart4 = Integer.parseInt(playerLastConnect.substring(
							(start[2] + 1), (start[3])));
				} catch (NumberFormatException e) {
				}
			}
		}
		sendMessage("Welcome to Devolution Server");
		sendMessage("Created by Bakatool/Winten.");
		sendMessage("Type ::help to view information about leveling,obtaining items and Pking (drops!).");
		sendMessage("Latest update: More magic spells!! type ::switch for normal spells.");
		for (int a = 0; a < lastMessage.length; a++) {
			lastMessage[a] = "";
		}
		if (premium) {
			sendMessage("Your account has premium member status");
		} else {
			sendMessage("You are not a premium member.  To subscribe click 'Become premium' on Devolution.com");
		}
		if (playerMessages > 0)
			sendMessage("You have "
					+ playerMessages
					+ " new messages.  Check your inbox at Devolution.com to view them.");
		// FACE: 0= WEST | -1 = NORTH | -2 = EAST | -3 = SOUTH
		updateRating();
		if (lookNeeded) {
			showInterface(3559);
		} else
			// setSidebarInterface(6, 1151);
			sendFrame126("" + playerLevel[0] + "", 4004);
		sendFrame126("" + getLevelForXP(playerXP[0]) + "", 4005);
		sendFrame126("" + playerLevel[1] + "", 4008);
		sendFrame126("" + getLevelForXP(playerXP[1]) + "", 4009);
		sendFrame126("" + playerLevel[2] + "", 4006);
		sendFrame126("" + getLevelForXP(playerXP[2]) + "", 4007);
		sendFrame126("" + playerLevel[3] + "", 4016);
		sendFrame126("" + getLevelForXP(playerXP[3]) + "", 4017);
		sendFrame126("" + playerLevel[4] + "", 4010);
		sendFrame126("" + getLevelForXP(playerXP[4]) + "", 4011);
		sendFrame126("" + playerLevel[5] + "", 4012);
		sendFrame126("" + getLevelForXP(playerXP[5]) + "", 4013);
		sendFrame126("" + playerLevel[6] + "", 4014);
		sendFrame126("" + getLevelForXP(playerXP[6]) + "", 4015);
		sendFrame126("" + playerLevel[7] + "", 4034);
		sendFrame126("" + getLevelForXP(playerXP[7]) + "", 4035);
		sendFrame126("" + playerLevel[8] + "", 4038);
		sendFrame126("" + getLevelForXP(playerXP[8]) + "", 4039);
		sendFrame126("" + playerLevel[9] + "", 4026);
		sendFrame126("" + getLevelForXP(playerXP[9]) + "", 4027);
		sendFrame126("" + playerLevel[10] + "", 4032);
		sendFrame126("" + getLevelForXP(playerXP[10]) + "", 4033);
		sendFrame126("" + playerLevel[11] + "", 4036);
		sendFrame126("" + getLevelForXP(playerXP[11]) + "", 4037);
		sendFrame126("" + playerLevel[12] + "", 4024);
		sendFrame126("" + getLevelForXP(playerXP[12]) + "", 4025);
		sendFrame126("" + playerLevel[13] + "", 4030);
		sendFrame126("" + getLevelForXP(playerXP[13]) + "", 4031);
		sendFrame126("" + playerLevel[14] + "", 4028);
		sendFrame126("" + getLevelForXP(playerXP[14]) + "", 4029);
		sendFrame126("" + playerLevel[15] + "", 4020);
		sendFrame126("" + getLevelForXP(playerXP[15]) + "", 4021);
		sendFrame126("" + playerLevel[16], 4018);
		sendFrame126("" + getLevelForXP(playerXP[16]), 4019);
		sendFrame126("" + playerLevel[17], 4022);
		sendFrame126("" + getLevelForXP(playerXP[17]), 4023);
		sendFrame126("" + playerLevel[20], 4152);
		sendFrame126("" + getLevelForXP(playerXP[20]), 4153);
		if (playerPass.equals("")) {
			sendMessage("No password set! Use ::pass PASSWORD to set ur password.");
		}

		WriteEnergy();
		// sendFrame126("Project16", 2451);
		sendFrame126("", 6067);
		sendFrame126("", 6071);
		SendWeapon(-1, "Unarmed");

		handler.updatePlayer(this, outStream);
		handler.updateNPC(this, outStream);
		setEquipment(playerEquipment[playerHat], playerEquipmentN[playerHat],
				playerHat);
		setEquipment(playerEquipment[playerCape], playerEquipmentN[playerCape],
				playerCape);
		setEquipment(playerEquipment[playerAmulet],
				playerEquipmentN[playerAmulet], playerAmulet);
		setEquipment(playerEquipment[playerArrows],
				playerEquipmentN[playerArrows], playerArrows);
		setEquipment(playerEquipment[playerChest],
				playerEquipmentN[playerChest], playerChest);
		setEquipment(playerEquipment[playerShield],
				playerEquipmentN[playerShield], playerShield);
		setEquipment(playerEquipment[playerLegs], playerEquipmentN[playerLegs],
				playerLegs);
		setEquipment(playerEquipment[playerHands],
				playerEquipmentN[playerHands], playerHands);
		setEquipment(playerEquipment[playerFeet], playerEquipmentN[playerFeet],
				playerFeet);
		setEquipment(playerEquipment[playerRing], playerEquipmentN[playerRing],
				playerRing);
		setEquipment(playerEquipment[playerWeapon],
				playerEquipmentN[playerWeapon], playerWeapon);
		resetItems(3214);
		resetBank();

		ResetBonus();
		GetBonus();
		WriteBonus();
		// objects
		/*
		 * ReplaceObject(2090, 3267, 3430, 0, 22); ReplaceObject(2094, 3268,
		 * 3431, 0, 22); ReplaceObject(2092, 3269, 3431, 0, 22);
		 * removeObject(2735, 3449, 8173); removeObject(2723, 3454, 8173);
		 * removeObject(2721, 3459, 8173);
		 */
		replaceDoors();
		for (int c = 0; c < server.objectHandler.ObjectID.length; c++) {
			if (server.objectHandler.ObjectID[c] == -1)
				continue;
			addObject(server.objectHandler.ObjectX[c],
					server.objectHandler.ObjectY[c],
					server.objectHandler.ObjectID[c],
					server.objectHandler.ObjectFace[c]);
		}

		pmstatus(2);
		boolean pmloaded = false;

		for (long element : friends) {
			if (element != 0) {
				for (int i2 = 1; i2 < handler.maxPlayers; i2++) {
					if ((handler.players[i2] != null)
							&& handler.players[i2].isActive
							&& (misc
									.playerNameToInt64(handler.players[i2].playerName) == element)) {
						if ((playerRights >= 2)
								|| (handler.players[i2].Privatechat == 0)
								|| ((handler.players[i2].Privatechat == 1) && handler.players[i2]
										.isinpm(misc
												.playerNameToInt64(playerName)))) {
							loadpm(element, GetWorld(i2));
							pmloaded = true;
						}
						break;
					}
				}
				if (!pmloaded) {
					loadpm(element, 0);
				}
				pmloaded = false;
			}
		}
		for (int i1 = 1; i1 < handler.maxPlayers; i1++) {
			if ((handler.players[i1] != null)
					&& (handler.players[i1].isActive == true)) {
				handler.players[i1].pmupdate(playerId, GetWorld(playerId));
			}
		}
		// Objects
		for (int i = 0; i < server.objectHandler.MaxObjects; i++) {
			if (server.objectHandler.ObjectID[i] > -1) {
				if (server.objectHandler.ObjectOpen[i] != server.objectHandler.ObjectOriOpen[i]) {
					ChangeDoor(i);
				}
			}
		}
		sendQuest("Yanille Teleport", 13037);
		sendQuest("Teleport back home", 13038);
		sendQuest("@gre@0/0", 13042);
		sendQuest("@gre@0/0", 13043);
		sendQuest("@gre@0/0", 13044);
		sendQuest("Seers Teleport", 13047);
		sendQuest("Visit the land of trees", 13048);
		sendQuest("@gre@0/0", 13051);
		sendQuest("@gre@0/0", 13052);
		sendQuest("Dragon Cave Teleport", 13055);
		sendQuest("Beware of Dragons", 13056);
		sendQuest("@gre@0/0", 13059);
		sendQuest("@gre@0/0", 13060);
		sendQuest("Catherby Teleport", 13063);
		sendQuest("Visit the ocean and mountains", 13064);
		sendQuest("@gre@0/0", 13067);
		sendQuest("@gre@0/0", 13068);
		sendQuest("Legends Guild Teleport", 13071);
		sendQuest("Visit the guild", 13072);
		sendQuest("@gre@0/0", 13076);
		sendQuest("@gre@0/0", 13077);
		sendQuest("@gre@0/0", 13078);

		// main
		sendFrame126("Devolution Server 7.0 (" + PlayerHandler.getPlayerCount()
				+ " online)", 6570);
		sendFrame126("", 6572);
		sendFrame126("", 6664);
		setInterfaceWalkable(6673);
		playerLastConnect = connectedFrom;
		sendQuest("Using this will send a notification to all online mods",
				5967);
		sendQuest(
				"@yel@Then click below to indicate which of our rules is being broken.",
				5969);
		sendQuest("4: Bug abuse (includes noclip)", 5974);
		sendQuest("5: Devolution staff impersonation", 5975);
		sendQuest("6: Monster luring or abuse", 5976);
		sendQuest("8: Item Duplication", 5978);
		sendQuest("10: Misuse of yell channel", 5980);
		sendQuest("12: Possible duped items", 5982);
		/*
		 * openWelcomeScreen(201, true, playerMessages, ((IPPart1 << 24) +
		 * (IPPart2 << 16) + (IPPart3 << 8) + IPPart4),
		 * GetLastLogin(playerLastLogin));
		 */
	}

	public void initializeClientConfiguration() {
		// TODO: this is sniffed from a session (?), yet have to figure out what
		// each of these does.
		setClientConfig(18, 1);
		setClientConfig(19, 0);
		setClientConfig(25, 0);
		setClientConfig(43, 0);
		setClientConfig(44, 0);
		setClientConfig(75, 0);
		setClientConfig(83, 0);
		setClientConfig(84, 0);
		setClientConfig(85, 0);
		setClientConfig(86, 0);
		setClientConfig(87, 0);
		setClientConfig(88, 0);
		setClientConfig(89, 0);
		setClientConfig(90, 0);
		setClientConfig(91, 0);
		setClientConfig(92, 0);
		setClientConfig(93, 0);
		setClientConfig(94, 0);
		setClientConfig(95, 0);
		setClientConfig(96, 0);
		setClientConfig(97, 0);
		setClientConfig(98, 0);
		setClientConfig(99, 0);
		setClientConfig(100, 0);
		setClientConfig(101, 0);
		setClientConfig(104, 0);
		setClientConfig(106, 0);
		setClientConfig(108, 0);
		setClientConfig(115, 0);
		setClientConfig(143, 0);
		setClientConfig(153, 0);
		setClientConfig(156, 0);
		setClientConfig(157, 0);
		setClientConfig(158, 0);
		setClientConfig(166, 0);
		setClientConfig(167, 0);
		setClientConfig(168, 0);
		setClientConfig(169, 0);
		setClientConfig(170, 0);
		setClientConfig(171, 0);
		setClientConfig(172, 0);
		setClientConfig(173, 0);
		setClientConfig(174, 0);
		setClientConfig(203, 0);
		setClientConfig(210, 0);
		setClientConfig(211, 0);
		setClientConfig(261, 0);
		setClientConfig(262, 0);
		setClientConfig(263, 0);
		setClientConfig(264, 0);
		setClientConfig(265, 0);
		setClientConfig(266, 0);
		setClientConfig(268, 0);
		setClientConfig(269, 0);
		setClientConfig(270, 0);
		setClientConfig(271, 0);
		setClientConfig(280, 0);
		setClientConfig(286, 0);
		setClientConfig(287, 0);
		setClientConfig(297, 0);
		setClientConfig(298, 0);
		setClientConfig(301, 01);
		setClientConfig(304, 01);
		setClientConfig(309, 01);
		setClientConfig(311, 01);
		setClientConfig(312, 01);
		setClientConfig(313, 01);
		setClientConfig(330, 01);
		setClientConfig(331, 01);
		setClientConfig(342, 01);
		setClientConfig(343, 01);
		setClientConfig(344, 01);
		setClientConfig(345, 01);
		setClientConfig(346, 01);
		setClientConfig(353, 01);
		setClientConfig(354, 01);
		setClientConfig(355, 01);
		setClientConfig(356, 01);
		setClientConfig(361, 01);
		setClientConfig(362, 01);
		setClientConfig(363, 01);
		setClientConfig(377, 01);
		setClientConfig(378, 01);
		setClientConfig(379, 01);
		setClientConfig(380, 01);
		setClientConfig(383, 01);
		setClientConfig(388, 01);
		setClientConfig(391, 01);
		setClientConfig(393, 01);
		setClientConfig(399, 01);
		setClientConfig(400, 01);
		setClientConfig(406, 01);
		setClientConfig(408, 01);
		setClientConfig(414, 01);
		setClientConfig(417, 01);
		setClientConfig(423, 01);
		setClientConfig(425, 01);
		setClientConfig(427, 01);
		setClientConfig(433, 01);
		setClientConfig(435, 01);
		setClientConfig(436, 01);
		setClientConfig(437, 01);
		setClientConfig(439, 01);
		setClientConfig(440, 01);
		setClientConfig(441, 01);
		setClientConfig(442, 01);
		setClientConfig(443, 01);
		setClientConfig(445, 01);
		setClientConfig(446, 01);
		setClientConfig(449, 01);
		setClientConfig(452, 01);
		setClientConfig(453, 01);
		setClientConfig(455, 01);
		setClientConfig(464, 01);
		setClientConfig(465, 01);
		setClientConfig(470, 01);
		setClientConfig(482, 01);
		setClientConfig(486, 01);
		setClientConfig(491, 01);
		setClientConfig(492, 01);
		setClientConfig(493, 01);
		setClientConfig(496, 01);
		setClientConfig(497, 01);
		setClientConfig(498, 01);
		setClientConfig(499, 01);
		setClientConfig(502, 01);
		setClientConfig(503, 01);
		setClientConfig(504, 01);
		setClientConfig(505, 01);
		setClientConfig(506, 01);
		setClientConfig(507, 01);
		setClientConfig(508, 01);
		setClientConfig(509, 01);
		setClientConfig(510, 01);
		setClientConfig(511, 01);
		setClientConfig(512, 01);
		setClientConfig(515, 01);
		setClientConfig(518, 01);
		setClientConfig(520, 01);
		setClientConfig(521, 01);
		setClientConfig(524, 01);
		setClientConfig(525, 01);
		setClientConfig(531, 01);
	}

	public boolean inRange(int x, int y) {
		if (localId > 0)
			return false;
		if ((Math.abs(absX - x) < 4) && (Math.abs(absY - y) < 4)) {
			return true;
		}
		return false;
	}

	public boolean IsInCW(int coordX, int coordY) {
		if ((coordY >= 3068) && (coordY <= 3143) && (coordX <= 2436)
				&& (coordX >= 2365)) {
			return true;

		} else {
			return false;
		}
	}

	public boolean isinpm(long l) {
		for (long element : friends) {
			if (element != 0) {
				if (l == element) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean IsInTz(int coordX, int coordY) {
		if ((coordY >= 5129) && (coordY <= 5167) && (coordX <= 2418)
				&& (coordX >= 2375)) {
			return true;

		} else {
			return false;
		}
	}

	public boolean isInWilderness(int coordX, int coordY, int Type) {
		if (Type == 1) {
			if ((coordY >= 3520) && (coordY <= 3967) && (coordX <= 3392)
					&& (coordX >= 2942)) {
				return true;
			}
		} else if (Type == 2) {
			if ((coordY >= 3512) && (coordY <= 3967) && (coordX <= 3392)
					&& (coordX >= 2942)) {
				return true;
			}
		}
		return false;
	}

	public boolean IsItemInBag(int ItemID) {
		for (int element : playerItems) {
			if ((element - 1) == ItemID) {
				return true;
			}
		}
		return false;
	}

	public int itemAmount(int itemID) {
		int tempAmount = 0;

		for (int i = 0; i < playerItems.length; i++) {
			if (playerItems[i] == itemID) {
				tempAmount += playerItemsN[i];
			}
		}
		return tempAmount;
	}

	public void itemsToVScreen(int x, int y) {
		Player p = handler.players[duelWith];
		if ((p != null) && (p.playerId == playerId))
			return;
		for (int i = 0; i < otherDuelItems.length; i++) {
			if ((otherDuelItems[i] > 0) && (otherDuelItemsN[i] > 0)) {
				if (Item.itemStackable[otherDuelItems[i] - 1]) {
					ItemHandler.addItem(otherDuelItems[i] - 1, enemyX, enemyY,
							otherDuelItemsN[i], playerId, false);
				} else {
					int amount = otherDuelItemsN[i];
					for (int a = 1; a <= amount; a++) {
						ItemHandler.addItem(otherDuelItems[i] - 1, enemyX,
								enemyY, 1, playerId, false);
					}
				}
			}

		}
		for (int i1 = 0; i1 < duelItems.length; i1++) {
			if ((duelItems[i] > 0) && (duelItemsN[i] > 0))
				addItem(duelItems[i1] - 1, duelItemsN[i1]);
		}
		// resetDuel();
	}

	public void itemsToVScreen_old() {
		outStream.createFrameVarSizeWord(53);
		outStream.writeWord(6822);
		outStream.writeWord(otherOfferedItems.toArray().length);
		for (GameItem item : otherOfferedItems) {
			if (item.amount > 254) {
				outStream.writeByte(255); // item's stack count. if over 254,
				// write byte 255
				outStream.writeDWord_v2(item.amount); // and then the real
				// value with
				// writeDWord_v2
			} else {
				outStream.writeByte(item.amount);
			}
			if ((item.id > 20000) || (item.id < 0)) {
				item.id = 20000;
			}
			outStream.writeWordBigEndianA(item.id + 1); // item id
		}
		outStream.endFrameVarSizeWord();
	}

	public int itemType(int item) {
		for (int element : Item.capes) {
			if (item == element) {
				return playerCape;
			}
		}
		for (int element : Item.hats) {
			if (item == element) {
				return playerHat;
			}
		}
		for (int element : Item.boots) {
			if (item == element) {
				return playerFeet;
			}
		}
		for (int element : Item.gloves) {
			if (item == element) {
				return playerHands;
			}
		}
		for (int element : Item.shields) {
			if (item == element) {
				return playerShield;
			}
		}
		for (int element : Item.amulets) {
			if (item == element) {
				return playerAmulet;
			}
		}
		for (int element : Item.arrows) {
			if (item == element) {
				return playerArrows;
			}
		}
		for (int element : Item.rings) {
			if (item == element) {
				return playerRing;
			}
		}
		for (int element : Item.body) {
			if (item == element) {
				return playerChest;
			}
		}
		for (int element : Item.legs) {
			if (item == element) {
				return playerLegs;
			}
		}

		// Default
		return playerWeapon;
	}

	public void killedPlayer(String name, int combat) {
		int diff = Math.abs(combatLevel - combat);
		String diffString = "";
		if (combatLevel > combat) {
			diffString = "-" + diff;
		} else {
			diffString = "+" + diff;
		}
		sendMessage("You have defeated " + name + "(" + diffString + ")" + "!");
		if ((diff > 7) && (combat > combatLevel)) {
			sendMessage("You gain double experience!");
			addSkillXP(20000, 18);
		}
		if ((diff <= 7) && (diff >= 0)) {
			sendMessage("You gain regular experience");
			addSkillXP(10000, 18);
		}
		if ((diff > 7) && (combatLevel > combat)) {
			sendMessage("You gain half experience");
			addSkillXP(5000, 18);
		}
	}

	public void l33thax(int id) {
		outStream.createFrame(171);
		outStream.writeByte(0);
		outStream.writeWord(id);
		flushOutStream();
	}

	public void levelup(int skill) {
		switch (skill) {
		case 0:
			// Attack levelup
			sendFrame164(6247);
			sendFrame126("Congratulations, you just advanced an attack level!",
					6248);
			sendFrame126("Your attack level is now " + playerLevel[0] + ".",
					6249);
			sendMessage("Congratulations, you just advanced an attack level.");
			NpcDialogueSend = true;
			break;

		case 1:
			// Strength
			sendFrame164(6206);
			sendFrame126(
					"Congratulations, you just advanced a strength level!",
					6207);
			sendFrame126("Your strength level is now " + playerLevel[2] + ".",
					6208);
			sendMessage("Congratulations, you just advanced a strength level.");
			NpcDialogueSend = true;
			break;

		case 2:
			// Defence
			sendFrame164(6253);
			sendFrame126("Congratulations, you just advanced a defence level!",
					6254);
			sendFrame126("Your defence level is now " + playerLevel[1] + ".",
					6255);
			sendMessage("Congratulations, you just advanced a defence level.");
			NpcDialogueSend = true;
			break;

		case 3:
			// Hitpoints
			sendFrame164(6216);
			sendFrame126(
					"Congratulations, you just advanced a hitpoints level!",
					6217);
			sendFrame126("Your hitpoints level is now " + playerLevel[3] + ".",
					6218);
			sendMessage("Congratulations, you just advanced a hitpoints level.");
			NpcDialogueSend = true;
			break;

		case 4:
			// Ranging
			sendFrame164(4443);
			sendFrame126("Congratulations, you just advanced a ranged level!",
					4444);
			sendFrame126("Your ranged level is now " + playerLevel[4] + ".",
					4445);
			sendMessage("Congratulations, you just advanced a ranging level.");
			NpcDialogueSend = true;
			break;

		case 5:
			// Prayer
			sendFrame164(6242);
			sendFrame126("Congratulations, you just advanced a prayer level!",
					6243);
			sendFrame126("Your prayer level is now " + playerLevel[5] + ".",
					6244);
			sendMessage("Congratulations, you just advanced a prayer level.");
			NpcDialogueSend = true;
			break;

		case 6:
			// Magic
			sendFrame164(6211);
			sendFrame126("Congratulations, you just advanced a magic level!",
					6212);
			sendFrame126("Your magic level is now " + playerLevel[6] + ".",
					6213);
			sendMessage("Congratulations, you just advanced a magic level.");
			NpcDialogueSend = true;
			break;

		case 7:
			// Cooking
			sendFrame164(6226);
			sendFrame126("Congratulations, you just advanced a cooking level!",
					6227);
			sendFrame126("Your cooking level is now " + playerLevel[7] + ".",
					6228);
			sendMessage("Congratulations, you just advanced a cooking level.");
			NpcDialogueSend = true;
			break;

		case 8:
			// Woodcutting
			sendFrame164(4272);
			sendFrame126(
					"Congratulations, you just advanced a woodcutting level!",
					4273);
			sendFrame126("Your woodcutting level is now " + playerLevel[8]
					+ ".", 4274);
			sendMessage("Congratulations, you just advanced a woodcutting level.");
			NpcDialogueSend = true;
			break;

		case 9:
			// Fletching
			sendFrame164(6231);
			sendFrame126(
					"Congratulations, you just advanced a fletching level!",
					6232);
			sendFrame126("Your fletching level is now " + playerLevel[9] + ".",
					6233);
			sendMessage("Congratulations, you just advanced a fletching level.");
			NpcDialogueSend = true;
			break;

		case 10:
			// fishing
			sendFrame164(6258);
			sendFrame126("Congratulations, you just advanced a fishing level!",
					6259);
			sendFrame126("Your fishing level is now " + playerLevel[10] + ".",
					6260);
			sendMessage("Congratulations, you just advanced a fishing level.");
			NpcDialogueSend = true;
			break;

		case 11:
			// firemaking
			sendFrame164(4282);
			sendFrame126(
					"Congratulations, you just advanced a fire making level!",
					4283);
			sendFrame126("Your firemaking level is now " + playerLevel[11]
					+ ".", 4284);
			sendMessage("Congratulations, you just advanced a fire making level.");
			NpcDialogueSend = true;
			break;

		case 12:
			// crafting
			sendFrame164(6263);
			sendFrame126(
					"Congratulations, you just advanced a crafting level!",
					6264);
			sendFrame126("Your crafting level is now " + playerLevel[12] + ".",
					6265);
			sendMessage("Congratulations, you just advanced a crafting level.");
			NpcDialogueSend = true;
			break;

		case 13:
			// Smithing
			sendFrame164(6221);
			sendFrame126(
					"Congratulations, you just advanced a smithing level!",
					6222);
			sendFrame126("Your smithing level is now " + playerLevel[13] + ".",
					6223);
			sendMessage("Congratulations, you just advanced a smithing level.");
			NpcDialogueSend = true;
			break;

		case 14:
			// Mining
			sendFrame164(4416);
			sendFrame126("Congratulations, you just advanced a mining level!",
					4417);
			sendFrame126("Your mining level is now " + playerLevel[14] + ".",
					4418);
			sendMessage("Congratulations, you just advanced a mining level.");
			NpcDialogueSend = true;
			break;

		case 15:
			// Herblore
			sendFrame164(6237);
			sendFrame126(
					"Congratulations, you just advanced a herblore level!",
					6238);
			sendFrame126("Your herblore level is now " + playerLevel[15] + ".",
					6239);
			sendMessage("Congratulations, you just advanced a herblore level.");
			NpcDialogueSend = true;
			break;

		case 16:
			// Agility
			sendFrame164(4277);
			sendFrame126("Congratulations, you just advanced a agility level!",
					4278);
			sendFrame126("Your agility level is now " + playerLevel[16] + ".",
					4279);
			sendMessage("Congratulations, you just advanced an agility level.");
			NpcDialogueSend = true;
			break;

		case 17:
			// Thieving
			sendFrame164(4261);
			sendFrame126(
					"Congratulations, you just advanced a thieving level!",
					6262);
			sendFrame126("Your theiving level is now " + playerLevel[17] + ".",
					6263);
			sendMessage("Congratulations, you just advanced a thieving level.");
			NpcDialogueSend = true;
			break;

		case 18:
			// Slayer
			sendFrame164(12123);
			sendFrame126("Congratulations, you just advanced a slayer level!",
					6207);
			sendFrame126("Your slayer level is now " + playerLevel[18] + ".",
					6208);
			sendMessage("Congratulations, you just advanced a slayer level.");
			NpcDialogueSend = true;
			break;

		case 19:
			// Farming
			// sendFrame164(4261);
			// sendFrame126("Congratulations, you just advanced a thieving
			// level!", 6207);
			// sendFrame126("Your farming level is now "+playerLevel[19]+" .",
			// 6208);
			sendMessage("Congratulations, you just advanced a farming level.");
			NpcDialogueSend = true;
			break;

		case 20:
			// Runecrafting
			sendFrame164(4267);
			sendFrame126(
					"Congratulations, you just advanced a runecrafting level!",
					4268);
			sendFrame126("Your runecrafting level is now " + playerLevel[20]
					+ ".", 4269);
			sendMessage("Congratulations, you just advanced a runecrafting level.");
			NpcDialogueSend = true;
			break;

		}
	}

	public int loadgame(String playerName, String playerPass) {
		for (Integer bUid : server.bannedUid) {
			if (uid == bUid.intValue()) {
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
			characterfile = new BufferedReader(new FileReader("./characters/"
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
							saveNeeded = false;
							validClient = false;
							return 3;
						}
					} else if (token.equals("character-password")) {
						if (playerPass.equalsIgnoreCase(token2)) {
						} else {
							saveNeeded = false;
							validClient = false;
							return 3;
						}
					}
					break;
				case 2:
					if (token.equals("character-height")) {
						heightLevel = Integer.parseInt(token2);
					} else if (token.equals("character-posx")) {
						teleportToX = Integer.parseInt(token2) == -1 ? 2611
								: Integer.parseInt(token2);
					} else if (token.equals("character-posy")) {
						teleportToY = Integer.parseInt(token2) == -1 ? 3093
								: Integer.parseInt(token2);
					} else if (token.equals("character-rights")) {
						playerRights = Integer.parseInt(token2);
					} else if (token.equals("character-ismember")) {
						playerIsMember = Integer.parseInt(token2);
					} else if (token.equals("character-messages")) {
						playerMessages = Integer.parseInt(token2);
					} else if (token.equals("character-lastconnection")) {
						playerLastConnect = token2;
					} else if (token.equals("character-lastlogin")) {
						playerLastLogin = Integer.parseInt(token2);
					} else if (token.equals("character-energy")) {
						playerEnergy = Integer.parseInt(token2);
					} else if (token.equals("character-gametime")) {
						playerGameTime = Integer.parseInt(token2);
					} else if (token.equals("character-gamecount")) {
						playerGameCount = Integer.parseInt(token2);
					} else if (token.equals("character-ancients")) {
						ancients = Integer.parseInt(token2);
					} else if (token.equals("character-rating")) {
						rating = Integer.parseInt(token2);
					}
					break;
				case 3:
					if (token.equals("character-equip")) {
						playerEquipment[Integer.parseInt(token3[0])] = Integer
								.parseInt(token3[1]);
						playerEquipmentN[Integer.parseInt(token3[0])] = Integer
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
						playerLevel[Integer.parseInt(token3[0])] = Integer
								.parseInt(token3[1]);
						playerXP[Integer.parseInt(token3[0])] = Integer
								.parseInt(token3[2]);

						int level = 0;
						if (playerXP[Integer.parseInt(token3[0])] > 13040000) {
							level = 99;
						} else {
							level = getLevelForXP(playerXP[Integer
									.parseInt(token3[0])]);
						}
						playerLevel[Integer.parseInt(token3[0])] = level;
						setSkillLevel(Integer.parseInt(token3[0]), level,
								playerXP[Integer.parseInt(token3[0])]);
						if (Integer.parseInt(token3[0]) == 3) {
							currentHealth = level;
							maxHealth = level;
						}
					}
					break;
				case 6:
					if (token.equals("character-item")) {
						playerItems[Integer.parseInt(token3[0])] = Integer
								.parseInt(token3[1]);
						playerItemsN[Integer.parseInt(token3[0])] = Integer
								.parseInt(token3[2]);
					}
					break;
				case 7:
					if (token.equals("character-bank")) {
						bankItems[Integer.parseInt(token3[0])] = Integer
								.parseInt(token3[1]);
						bankItemsN[Integer.parseInt(token3[0])] = Integer
								.parseInt(token3[2]);
					}
					break;
				case 8:
					if (token.equals("character-friend")) {
						friends[Integer.parseInt(token3[0])] = Long
								.parseLong(token3[1]);
					}
					break;
				case 9:
					if (token.equals("character-ignore")) {
						ignores[Integer.parseInt(token3[0])] = Long
								.parseLong(token3[1]);
					}
					break;
				}
			} else {
				if (line.equals("[ACCOUNT]"))
					ReadMode = 1;
				else if (line.equals("[CHARACTER]"))
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
					setLook(playerLooks);
					updateRequired = true;
					appearanceUpdateRequired = true;
					long end = System.currentTimeMillis() - start;
					println("Loading Process Completed  ["
							+ (playerRights > 0 ? "Has powers"
									: "Regular player") + ", lag: " + end
							+ " ms]");
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
		println("Failed to load player: " + playerName);
		return 13;
	}

	public void loadpm(long name, int world) {
		if (world != 0) {
			world += 9;
		} else if (world == 0) {
			world += 1;
		}
		outStream.createFrame(50);
		outStream.writeQWord(name);
		outStream.writeByte(world);
	}

	public void LogDuel(String otherName) {
		for (int i = 0; i < otherDuelItems.length; i++) {
			BufferedWriter bw = null;

			try {
				bw = new BufferedWriter(new FileWriter("logs/duels.txt", true));
				bw.write(playerName + " wins item: " + (otherDuelItems[i] - 1)
						+ " amount: " + otherDuelItemsN[i] + " from "
						+ otherName);
				bw.newLine();
				bw.flush();
			} catch (IOException ioe) {
				ioe.printStackTrace();
			} finally {
				if (bw != null)
					try {
						bw.close();
					} catch (IOException ioe2) {
						sendMessage("Error logging duel!");
					}
			}
		}
	}

	public void logout() {
		if (inCombat) {
		}
		if (validClient(duel_with) && duelFight) {
			getClient(duel_with).DuelVictory();
		} else {
			declineDuel();
		}
		sendMessage("Please wait... logging out may take time");
		sendQuest("     Please wait...", 2458);
		savegame(true);
		sendQuest("Click here to logout", 2458);
		println("Logout");
		outStream.createFrame(109);
	}

	public void logTrade(int id, int id2,
			CopyOnWriteArrayList<GameItem> offeredItems,
			CopyOnWriteArrayList<GameItem> offeredItems2) {
		try {
			// results = statement.executeQuery("SELECT id from uber3_trades
			// ORDER BY id DESC");
			// results.next();
			// int tId = results.getInt("id") + 1;
			// statement.executeUpdate("INSERT INTO uber3_trades SET id = " +
			// tId + ", type=0, p1=" + id + ", p2=" + id2);
			for (GameItem item : offeredItems) {
				// statement.executeUpdate("INSERT INTO uber3_logs SET id = " +
				// tId + ", pid=" + id + ", item=" + item.id + ", amount=" +
				// item.amount);
			}
			for (GameItem item : offeredItems2) {
				// statement.executeUpdate("INSERT INTO uber3_logs SET id = " +
				// tId + ", pid=" + id2 + ", item=" + item.id + ", amount=" +
				// item.amount);
			}
			// statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void ManipulateDirection() {
		// playerMD = misc.direction(absX, absY, skillX, skillY);
		if (playerMD != -1) {
			// playerMD >>= 1;
			updateRequired = true;
			dirUpdateRequired = true;
		}
	}

	public void Map() {
		showInterface(8134);
	}

	public int maxRangeHit() {
		int hit = (int) ((playerBonus[4] + playerLevel[playerRanged]) / 12);
		return hit;
	}

	public int MICheckPickAxe() {
		int Hand;
		int Shield;
		int Bag;
		int PickAxe;

		Hand = playerEquipment[playerWeapon];
		Shield = playerEquipment[playerShield];
		PickAxe = 0;
		switch (Hand) {
		case 1265:
			// Bronze Pick Axe
			PickAxe = 1;
			break;

		case 1267:
			// Iron Pick Axe
			PickAxe = 2;
			break;

		case 1269:
			// Steel Pick Axe
			PickAxe = 3;
			break;

		case 1273:
			// Mithril Pick Axe
			PickAxe = 4;
			break;

		case 1271:
			// Adamant Pick Axe
			PickAxe = 5;
			break;

		case 1275:
			// Rune Pick Axe
			PickAxe = 6;
			break;
		}
		if (PickAxe > 0) {
			return PickAxe;
		}
		return 0;
	}

	/* MINING */
	public boolean mining() {
		int MIPickAxe = 0;
		int RndGems = 50;
		if (!((absY >= 3128) && (absY <= 3152)))
			return false;
		if ((IsMining == true) && false) {
			MIPickAxe = 1; // If Mining -> Go trough loop, passby
			// MICheckPickAxe to prevent originalweapon loss, 1
			// to tell you got pick axe, no function left for
			// MIPickAxe if mining, so 1 is enough.
		} else {
			MIPickAxe = MICheckPickAxe();
		}
		long now = System.currentTimeMillis();
		if (now - lastAction < 1500)
			return false;
		lastAction = now;
		if (MIPickAxe > 0) {
			if (playerLevel[playerMining] >= mining[1]) {
				if (freeSlots() > 0) {
					if ((actionTimer == 0) && (IsMining == false)) {
						actionAmount++;
						sendMessage("You swing your pick axe at the rock...");
						actionTimer = (int) (3 * (mining[0] + 10) - MIPickAxe);
						if (actionTimer < 1) {
							actionTimer = 1;
						}
						setAnimation(0x554);
						IsMining = true;
					}
					if ((actionTimer == 0) && (IsMining == true)) {
						if ((IsItemInBag(1706) == true)
								|| (IsItemInBag(1708) == true)
								|| (IsItemInBag(1710) == true)
								|| (IsItemInBag(1712) == true)) {
							RndGems /= 2;
						}
						addSkillXP(((int) (2 * mining[2] * mining[3] * 3.14)),
								playerMining);
						addItem(mining[4], 1);
						sendMessage("You get some ores.");
						if (mining[1] == 85) {
							server.lastRunite = System.currentTimeMillis();
						}
						resetAnimation();
						resetMI();
					}
				} else {
					sendMessage("Not enough space in your inventory.");
					resetMI();
					return false;
				}
			} else {
				sendMessage("You need " + mining[1] + " "
						+ statName[playerMining] + " to mine those ores.");
				resetMI();
				return false;
			}
		} else {
			sendMessage("You need a pick axe to mine ores.");
			resetMI();
			return false;
		}
		return true;
	}

	public void ModHelp() {
		sendFrame126("@dre@Moderator Helpmenu", 8144); // Helpmenu title
		clearQuestInterface();
		sendFrame126("", 8145);
		sendFrame126("@dbl@Welcome to @cya@Project16", 8146);
		sendFrame126("@dbl@", 8147);
		sendFrame126("@dbl@Site is still under construction@dbl@", 8148);
		sendFrame126("@dbl@Server admin(s) is/are:", 8149);
		sendFrame126("@dre@", 8150);
		sendFrame126("@red@-Commands-", 8155);
		sendFrame126("::xteletome (playername)", 8156);
		sendFrame126("::xteleto (playername)", 8157);
		sendFrame126("::kick (playername)", 8158);

		sendQuestSomething(8143);
		showInterface(8134);
	}

	public void modYell(String msg) {
		for (Player element : handler.players) {
			client p = (client) element;
			if ((p != null) && !p.disconnected && (p.absX > 0)
					&& (p.playerRights > 0)) {
				p.sendMessage(msg);
			}
		}
	}

	public void moveItems(int from, int to, int moveWindow) {
		if (moveWindow == 3724) {
			int tempI;
			int tempN;

			tempI = playerItems[from];
			tempN = playerItemsN[from];

			playerItems[from] = playerItems[to];
			playerItemsN[from] = playerItemsN[to];
			playerItems[to] = tempI;
			playerItemsN[to] = tempN;
		}

		if ((moveWindow == 34453) && (from >= 0) && (to >= 0)
				&& (from < playerBankSize) && (to < playerBankSize)) {
			int tempI;
			int tempN;

			tempI = bankItems[from];
			tempN = bankItemsN[from];

			bankItems[from] = bankItems[to];
			bankItemsN[from] = bankItemsN[to];
			bankItems[to] = tempI;
			bankItemsN[to] = tempN;
		}

		if (moveWindow == 34453) {
			resetBank();
		} else if (moveWindow == 18579) {
			resetItems(5064);
		} else if (moveWindow == 3724) {
			resetItems(3214);
		}
	}

	public void multiTargetNPC(int spellId, int maxDamage, int range) {
		for (int i = 0; i < server.npcHandler.maxNPCSpawns; i++) {
			if (server.npcHandler.npcs[i] != null) {
				if ((distanceToPoint(server.npcHandler.npcs[i].absX,
						server.npcHandler.npcs[i].absY) <= range)
						&& !server.npcHandler.npcs[i].IsDead) {
					if (server.npcHandler.npcs[i].heightLevel == heightLevel) {
						int damage = misc.random(maxDamage);
						if (damage == 0) {
							stillgfx2(85, MagicHandler.graphicHeight,
									server.npcHandler.npcs[i].absY,
									server.npcHandler.npcs[i].absX, 2);
						} else {
							if (server.npcHandler.npcs[i].HP >= 0) {
								if (server.npcHandler.npcs[i].HP - damage < 0) {
									damage = server.npcHandler.npcs[i].HP;
								}
								if (MagicHandler.itHeals) {
									if (misc.random(2) == 1) {
										NewHP = (playerLevel[playerHitpoints] + (hitDiff / 4));
										if (NewHP >= getLevelForXP(playerXP[playerHitpoints])) {
											NewHP = getLevelForXP(playerXP[playerHitpoints]);
										}
										sendMessage("You drain the enemies health.");
										refreshSkills();
									}
								}
								if (MagicHandler.itFreezes) {
									sendMessage(""
											+ getFrozenMessage(MagicHandler.spellID)
											+ "");
									if (server.npcHandler.npcs[i].freezeTimer <= 0) {
										server.npcHandler.npcs[i].freezeTimer = getFreezeTimer(MagicHandler.spellID);
									}
								}
								if (MagicHandler.itReducesAttack) {
									if (misc.random(2) == 1) {
										if (server.npcHandler.npcs[i].reducedAttack <= 0) {
											sendMessage("You reduce the enemies attack power.");
											server.npcHandler.npcs[i].MaxHit -= (hitDiff / 2);
											if ((server.npcHandler.npcs[i].MaxHit - (hitDiff / 2)) < server.npcHandler.npcs[i].MaxHit) {
												server.npcHandler.npcs[i].MaxHit = 0;
											}
											server.npcHandler.npcs[i].reducedAttack = getAttackTimer(MagicHandler.spellID);
										}
									}
								}
								if (MagicHandler.itPoisons) {
									if (misc.random(2) == 1) {
										if (server.npcHandler.npcs[i].poisonTimer <= 0) {
											sendMessage("You poison the enemy.");
											server.npcHandler.npcs[i].poisonTimer = 120;
											server.npcHandler.npcs[i].poisonDmg = true;
											server.npcHandler.poisonNpc(i);
										}
									}
								}
								stillgfx2(spellId, MagicHandler.graphicHeight,
										server.npcHandler.npcs[i].absY,
										server.npcHandler.npcs[i].absX, 2);
								server.npcHandler.npcs[i].hitDiff = damage;
								server.npcHandler.npcs[i].Killing[playerId] += damage;
								server.npcHandler.npcs[i].updateRequired = true;
								server.npcHandler.npcs[i].hitUpdateRequired = true;
							}
						}
					}
				}
			}
		}
	}

	public void multiTargetPlayer(int spellId, int maxDamage, int range) // by
	// Bakatool
	{
		for (int i = 0; i < server.playerHandler.players.length; i++) {
			if ((server.playerHandler.players[i] != null) && (i != playerId)) {
				if (distanceToPoint(server.playerHandler.players[i].absX,
						server.playerHandler.players[i].absY) <= range) {
					if (server.playerHandler.players[i].heightLevel == heightLevel) {
						int damage = misc.random(maxDamage);
						if (damage == 0) {
							stillgfx2(85, MagicHandler.graphicHeight,
									server.playerHandler.players[i].absY,
									server.playerHandler.players[i].absX, 2);
						} else {
							if (server.playerHandler.players[i].playerLevel[playerHitpoints] >= 0) {
								if (server.playerHandler.players[i].playerLevel[playerHitpoints]
										- damage < 0) {
									damage = server.playerHandler.players[i].playerLevel[playerHitpoints];
								}
								int myHP = playerLevel[playerHitpoints];
								if (MagicHandler.itHeals) {
									if (misc.random(2) == 1) {
										NewHP = (playerLevel[playerHitpoints] + (hitDiff / 4));
										if (NewHP >= getLevelForXP(playerXP[playerHitpoints])) {
											NewHP = getLevelForXP(playerXP[playerHitpoints]);
										}
										sendMessage("You drain the enemies health.");
										refreshSkills();
									}
								}
								if (MagicHandler.itFreezes) {
									sendMessage(""
											+ getFrozenMessage(MagicHandler.spellID)
											+ "");
									if (server.playerHandler.players[i].freezeTimer <= 0) {
										server.playerHandler.players[i].freezeTimer = getFreezeTimer(MagicHandler.spellID);
									}
								}
								if (MagicHandler.itReducesAttack) {
									if (misc.random(2) == 1) {
										if (server.playerHandler.players[i].reducedAttack <= 0) {
											sendMessage("You reduce the enemies attack power.");
											server.playerHandler.players[i].playerLevel[playerAttack] -= (hitDiff / 2);
											if ((server.playerHandler.players[i].playerLevel[playerAttack] - (hitDiff / 2)) < server.playerHandler.players[i].playerLevel[playerAttack]) {
												server.playerHandler.players[i].playerLevel[playerAttack] = 0;
											}
											server.playerHandler.players[i].reducedAttack = getAttackTimer(MagicHandler.spellID);
										}
									}
								}
								if (MagicHandler.itPoisons) {
									if (misc.random(2) == 1) {
										if (server.playerHandler.players[i].poisonTimer <= 0) {
											sendMessage("You poison the enemy.");
											server.playerHandler.players[i].poisonTimer = 120;
											server.playerHandler.players[i].poisonDmg = true;
											server.playerHandler.players[i]
													.applyPoisonToMe();
										}
									}
								}
								stillgfx2(spellId, MagicHandler.graphicHeight,
										server.playerHandler.players[i].absY,
										server.playerHandler.players[i].absX, 2);
								server.playerHandler.players[i]
										.dealDamage(hitDiff);
								server.playerHandler.players[i].hitDiff = damage;
								server.playerHandler.players[i].updateRequired = true;
								server.playerHandler.players[i].hitUpdateRequired = true;
							}
						}
					}
				}
			}
		}
	}

	/*
	 * These are all the ancient spells that are multi-target ;)
	 */
	public boolean MultiTargetSpell(int i) {
		if ((i == 12963) || (i == 13011) || (i == 12919) || (i == 12881)
				|| (i == 12975) || (i == 13023) || (i == 12929) || (i == 12891)) {
			return true;
		}
		return false;
	}

	public void openDuel() {
		RefreshDuelRules();
		refreshDuelScreen();
		inDuel = true;
		client other = getClient(duel_with);
		sendQuest("Dueling with: " + other.playerName + " (level-"
				+ other.combat + ")", 6671);
		sendQuest("", 6684);
		sendFrame248(6575, 3321);
		resetItems(3322);
	}

	public void OpenSmithingFrame(int Type) {
		int Type2 = Type - 1;
		int Length = 22;

		if ((Type == 1) || (Type == 2)) {
			Length += 1;
		} else if (Type == 3) {
			Length += 2;
		}
		// Sending amount of bars + make text green if lvl is highenough
		sendFrame126("", 1132); // Wire
		sendFrame126("", 1096);
		sendFrame126("", 11459); // Lantern
		sendFrame126("", 11461);
		sendFrame126("", 1135); // Studs
		sendFrame126("", 1134);
		String bar, color, color2, name = "";

		if (Type == 1) {
			name = "Bronze ";
		} else if (Type == 2) {
			name = "Iron ";
		} else if (Type == 3) {
			name = "Steel ";
		} else if (Type == 4) {
			name = "Mithril ";
		} else if (Type == 5) {
			name = "Adamant ";
		} else if (Type == 6) {
			name = "Rune ";
		}
		for (int i = 0; i < Length; i++) {
			bar = "bar";
			color = "@red@";
			color2 = "@bla@";
			if (Item.smithing_frame[Type2][i][3] > 1) {
				bar = bar + "s";
			}
			if (playerLevel[playerSmithing] >= Item.smithing_frame[Type2][i][2]) {
				color2 = "@whi@";
			}
			int Type3 = Type2;

			if (Type2 >= 3) {
				Type3 = (Type2 + 2);
			}
			if (AreXItemsInBag((2349 + (Type3 * 2)),
					Item.smithing_frame[Type2][i][3]) == true) {
				color = "@gre@";
			}
			sendFrame126(color + "" + Item.smithing_frame[Type2][i][3] + ""
					+ bar, Item.smithing_frame[Type2][i][4]);
			String linux_hack = getItemName(Item.smithing_frame[Type2][i][0]);
			int index = getItemName(Item.smithing_frame[Type2][i][0]).indexOf(
					name);
			if (index > 0) {
				linux_hack = linux_hack.substring(index + 1);
				sendFrame126(linux_hack, Item.smithing_frame[Type2][i][5]);
			}
			// sendFrame126(
			// color2 + ""
			// + getItemName(Item.smithing_frame[Type2][i][0]).replace(name,
			// ""),
			// Item.smithing_frame[Type2][i][5]);
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
		Item.SmithingItems[4][0] = Item.smithing_frame[Type2][18][0]; // 2
		// hand
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
		if ((Type == 2) || (Type == 3)) {
			color2 = "@bla@";
			if (playerLevel[playerSmithing] >= Item.smithing_frame[Type2][22][2]) {
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
		if (Type == 1) {
			color2 = "@bla@";
			if (playerLevel[playerSmithing] >= Item.smithing_frame[Type2][22][2]) {
				color2 = "@whi@";
			}
			Item.SmithingItems[3][0] = Item.smithing_frame[Type2][22][0]; // Wire
			Item.SmithingItems[3][1] = Item.smithing_frame[Type2][22][1];
			sendFrame126(color2 + ""
					+ getItemName(Item.smithing_frame[Type2][22][0]), 1096);
		}
		Item.SmithingItems[4][0] = -1; // Studs
		Item.SmithingItems[4][1] = 0;
		if (Type == 3) {
			color2 = "@bla@";
			if (playerLevel[playerSmithing] >= Item.smithing_frame[Type2][23][2]) {
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

	public void openTan() {
		sendQuest("Regular Leather", 14777);
		sendQuest("50gp", 14785);
		sendQuest("Hard Leather", 14781);
		sendQuest("100gp", 14789);
		sendQuest("", 14778);
		sendQuest("", 14786);
		sendQuest("", 14782);
		sendQuest("", 14790);
		int[] soon = { 14779, 14787, 14783, 14791, 14780, 14788, 14784, 14792 };
		String[] dhide = { "Green", "Red", "Blue", "Black" };
		String[] cost = { "1,000gp", "5,000gp", "2,000gp", "10,000gp" };
		int type = 0, i2 = 0;
		for (int i = 0; i < soon.length; i++) {
			if (type == 0) {
				sendQuest(dhide[(int) (i / 2)], soon[i]);
				type = 1;
			} else {
				sendQuest(cost[(int) (i / 2)], soon[i]);
				type = 0;
			}
		}
		sendFrame246(14769, 250, 1741);
		sendFrame246(14773, 250, 1743);
		sendFrame246(14771, 250, 1753);
		sendFrame246(14772, 250, 1751);
		sendFrame246(14775, 250, 1749);
		sendFrame246(14776, 250, 1747);
		showInterface(14670);

	}

	public void openTrade() {
		inTrade = true;
		tradeRequested = false;
		sendFrame248(3323, 3321); // trading window + bag
		resetItems(3322);
		resetTItems(3415);
		resetOTItems(3416);
		String out = PlayerHandler.players[trade_reqId].playerName;
		if (PlayerHandler.players[trade_reqId].playerRights == 1) {
			out = "@cr1@" + out;
		} else if (PlayerHandler.players[trade_reqId].playerRights == 2) {
			out = "@cr2@" + out;
		}
		sendFrame126("Trading With: "
				+ PlayerHandler.players[trade_reqId].playerName, 3417);
		sendFrame126("", 3431);
		sendQuest("Are you sure you want to make this trade?", 3535);
	}

	public void openUpBank() {
		sendFrame248(5292, 5063);
		resetItems(5064);
		IsBanking = true;
	}

	public void openUpPinSettings() {
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

	public void openUpShop(int ShopID) {
		sendFrame126(server.shopHandler.ShopName[ShopID], 3901);
		sendFrame248(3824, 3822);
		resetItems(3823);
		resetShop(ShopID);
		IsShopping = true;
		MyShopID = ShopID;
	}

	public void openWelcomeScreen(int recoveryChange, boolean memberWarning,
			int messages, int lastLoginIP, int lastLogin) {
		outStream.createFrame(176);
		// days since last recovery change 200 for not yet set 201 for members
		// server,
		// otherwise, how many days ago recoveries have been changed.
		outStream.writeByteC(recoveryChange);
		outStream.writeWordA(messages); // # of unread messages
		outStream.writeByte(memberWarning ? 1 : 0); // 1 for member on
		// non-members world warning
		outStream.writeDWord_v2(lastLoginIP); // ip of last login
		outStream.writeWord(lastLogin); // days
	}

	private boolean packetProcess() {
		if (disconnected) {
			return false;
		}
		try {
			if (timeOutCounter++ > 20) {
				actionReset();
				disconnected = true;
				if (saveNeeded)
					savegame(true);
				return false;
			}

			if (in == null) {
				return false;
			}

			int avail = in.available();

			if (avail == 0) {
				return false;
			}
			if (packetType == -1) {
				packetType = in.read() & 0xff;
				if (inStreamDecryption != null) {
					packetType = packetType - inStreamDecryption.getNextKey()
							& 0xff;
				}
				packetSize = packetSizes[packetType];
				avail--;
			}
			if (packetSize == -1) {
				if (avail > 0) {
					packetSize = in.read() & 0xff;
					avail--;
				} else {
					return false;
				}
			}
			if (avail < packetSize) {
				return false;
			}
			fillInStream(packetSize);
			timeOutCounter = 0;

			parseIncomingPackets();
			packetType = -1;
		} catch (java.lang.Exception __ex) {
			server.logError(__ex.getMessage());
			disconnected = true;
			System.out.println("Project16 [fatal] - exception");
			savegame(true);
		}
		return true;
	}

	public void parseIncomingPackets() {
		int i;
		int junk;
		int junk2;
		int junk3;
		lastPacket = System.currentTimeMillis();
		// if(packetType != 0) println("" + packetType);
		switch (packetType) {
		case 25:
			// item in inventory used with item on floor
			int unknown1 = inStream.readSignedWordBigEndian(); // interface id
			// of item
			int unknown2 = inStream.readUnsignedWordA(); // item in bag id
			int floorID = inStream.readUnsignedByte();
			int floorY = inStream.readUnsignedWordA();
			int unknown3 = inStream.readUnsignedWordBigEndianA();
			int floorX = inStream.readUnsignedByte();
			System.out.println("Unknown1 = " + unknown1);
			System.out.println("Unknown2 = " + unknown2);
			System.out.println("FloorID = " + floorID);
			System.out.println("FloorY = " + floorY);
			System.out.println("Unknown3 = " + unknown3);
			System.out.println("FloorX = " + floorX);
			break;
		case 57:
			int aA1 = inStream.readSignedWordBigEndianA();
			int b1 = inStream.readSignedWordBigEndianA();
			int c1 = inStream.readSignedWordBigEndian();
			int d1 = inStream.readSignedWordBigEndianA();
			break;
		case 0:
			break; // idle packet - keeps on reseting timeOutCounter

		case 202:
			// idle logout packet - ignore for now
			// logout();
			break;
		case 45:
			// flagged account data
			// inStream.readBytes(pmchatText, pmchatTextSize, 0);
			int blah = inStream.readUnsignedByte();
			int part2 = -1,
			part3 = -1,
			part4 = -1;
			try {
				part2 = inStream.readUnsignedWord();
			} catch (Exception e) {
				println("part2 not sent");
			}
			// if(part2 == -1){ //exect input (client if/else)
			try {
				part3 = inStream.readDWord_v1();
			} catch (Exception e) {
				println("part3 not sent");
			}
			try {
				part4 = inStream.readDWord();
			} catch (Exception e) {
				println("part4 not sent");
			}
			// }
			println("blah=" + blah + ", " + "part2=" + part2 + ", part3="
					+ part3 + ", part4=" + part4);
			break;

		case 210:
			// loads new area
			break;

		case 40:
			if ((NpcDialogue == 1) || (NpcDialogue == 3) || (NpcDialogue == 5)) {
				NpcDialogue += 1;
				NpcDialogueSend = false;
			} else if ((NpcDialogue == 6) || (NpcDialogue == 7)) {
				NpcDialogue = 0;
				NpcDialogueSend = false;
				RemoveAllWindows();
			} else {
				closeInterface();
			}

			println_debug("Unhandled packet [" + packetType + ", InterFaceId: "
					+ inStream.readUnsignedWordA() + ", size=" + packetSize
					+ "]: ]" + misc.Hex(inStream.buffer, 1, packetSize) + "[");
			println_debug("Action Button: "
					+ misc.HexToInt(inStream.buffer, 0, packetSize));
			break;

		case 192:
			// Use an item on an object
			junk = inStream.readSignedWordBigEndianA();
			int UsedOnObjectID = inStream.readUnsignedWordBigEndian();
			int UsedOnY = inStream.readSignedWordBigEndianA();
			int ItemSlot = (inStream.readSignedWordBigEndianA() - 128);
			int UsedOnX = inStream.readUnsignedWordBigEndianA();
			int ItemID = inStream.readUnsignedWord();
			if (!playerHasItem(ItemID))
				break;
			if (UsedOnObjectID == 3994) {
				for (int fi = 0; fi < misc.smelt_frame.length; fi++)
					sendFrame246(misc.smelt_frame[fi], 150, misc.smelt_bars[fi]);
				sendFrame164(2400);
				// smelting = true;
				// smelt_id = ItemID;
			}
			if ((UsedOnObjectID == 2781) || (UsedOnObjectID == 2728)) {
				// furnace, range
				if ( /* CheckForSkillUse2(ItemID, ItemSlot) == */true) {
					skillX = UsedOnX;
					skillY = UsedOnY;
					checkCooking(ItemID);
				}
			} else if (UsedOnObjectID == 2783) {
				// anvil
				int Type = CheckSmithing(ItemID, ItemSlot);

				if (Type != -1) {
					skillX = UsedOnX;
					skillY = UsedOnY;
					OpenSmithingFrame(Type);
				}
			} else {
				println_debug("Item: " + ItemID + " - Used On Object: "
						+ UsedOnObjectID + " -  X: " + UsedOnX + " - Y: "
						+ UsedOnY);
			}
			break;
		case 218:
			String abuser = misc.longToPlayerName(inStream.readQWord());
			int rule = inStream.readUnsignedByte();
			int mute = inStream.readUnsignedByte();
			reportAbuse(abuser, rule, mute);
			break;
		case 130:
			// Clicking some stuff in game
			int interfaceID = inStream.readSignedByte();
			// if(actionButtonId == 26018) {
			if (inDuel && !duelFight) {
				declineDuel();
			}
			// }
			if (duelFight) {
				if (System.currentTimeMillis() - lastButton < 1000) {
					lastButton = System.currentTimeMillis();
					break;
				} else {
					lastButton = System.currentTimeMillis();
				}
				println("Valid click..");
				client other = getClient(duel_with);
				for (GameItem item : otherOfferedItems) {
					println("otherDuelItems = " + item.id);
					if ((item.id > 0) && (item.amount > 0)) {
						if (Item.itemStackable[item.id]) {
							if (!addItem(item.id, item.amount))
								ItemHandler.addItem(item.id, enemyX, enemyY,
										item.amount, playerId, false);
						} else {
							int amount = item.amount;
							for (int a = 1; a <= amount; a++) {
								if (!addItem(item.id, 1))
									ItemHandler.addItem(item.id, enemyX,
											enemyY, 1, playerId, false);
							}
						}
					}

				}
				for (GameItem item : offeredItems) {
					if ((item.id > 0) && (item.amount > 0))
						addItem(item.id, item.amount);
				}
				resetDuel();
				savegame(false);
				if (validClient(duel_with)) {
					other.resetDuel();
					other.savegame(false);
				}
			} else {
				// sendMessage("You didn't win the duel!");
			}
			if (inTrade && (System.currentTimeMillis() - lastButton > 1000)) {
				lastButton = System.currentTimeMillis();
				declineTrade();
			}
			if (IsShopping == true) {
				IsShopping = false;
				MyShopID = 0;
				UpdateShop = false;
			}
			if (IsBanking == true) {
				IsBanking = false;
			}

			if ((misc.HexToInt(inStream.buffer, 0, packetSize) != 63363)
					&& (misc.HexToInt(inStream.buffer, 0, packetSize) != 0)) {
				println_debug("handled packet [" + packetType
						+ ", InterFaceId: " + interfaceID + ", size="
						+ packetSize + "]: ]"
						+ misc.Hex(inStream.buffer, 1, packetSize) + "[");
				println_debug("Action Button: "
						+ misc.HexToInt(inStream.buffer, 0, packetSize));
			}
			break;

		case 155:
			// first Click npc
			NPCSlot = inStream.readSignedWordBigEndian();
			if ((NPCSlot < 0) || (NPCSlot >= server.npcHandler.npcs.length)
					|| (server.npcHandler.npcs[NPCSlot] == null))
				break;
			NPCID = server.npcHandler.npcs[NPCSlot].npcType;
			boolean FishingGo = false;
			boolean PutNPCCoords = false;
			if (NPCID == 804) {
				openTan();
			}

			if (NPCID == 316) {
				/* Net From Net & Bait - Any Sea */
				if ((IsItemInBag(303) == true) && (actionTimer == 0)) {
					startFishing(316);
				} else {
					sendMessage("You need a " + getItemName(303)
							+ " to fish here.");
				}
			} else if (NPCID == 321) {
				startFishing(321);

			} else if ((NPCID == 494) || (NPCID == 495) || (NPCID == 496)
					|| (NPCID == 497) || (NPCID == 2354) || (NPCID == 2355)
					|| (NPCID == 2619) || (NPCID == 3198)) {
				/* Banking */
				skillX = server.npcHandler.npcs[NPCSlot].absX;
				skillY = server.npcHandler.npcs[NPCSlot].absY;
				NpcWanneTalk = 1;
			} else if (NPCID == 553) {
				/* Aubury */
				skillX = server.npcHandler.npcs[NPCSlot].absX;
				skillY = server.npcHandler.npcs[NPCSlot].absY;
				NpcWanneTalk = 3;
			} else if (NPCID == 1597) {
				skillX = server.npcHandler.npcs[NPCSlot].absX;
				skillY = server.npcHandler.npcs[NPCSlot].absY;
				NpcWanneTalk = 9;
			} else if (NPCID == 398) {
				skillX = server.npcHandler.npcs[NPCSlot].absX;
				skillY = server.npcHandler.npcs[NPCSlot].absY;
				NpcWanneTalk = 398;
				sendFrame200(4883, 398);
				sendFrame126(GetNpcName(398), 4884);
				if (premium) {
					sendFrame126("Welcome to the Guild of Legends", 4885);
				} else {
					sendFrame126("You must be a premium member to enter", 4885);
					sendFrame126("Visit Devolution.com to subscribe", 4886);
				}
				sendFrame75(398, 4883);
				sendFrame164(4882);
				NpcDialogueSend = true;
				if (premium) {
					ReplaceObject(2728, 3349, 2391, 0, 0);
					ReplaceObject(2729, 3349, 2392, -2, 0);
				}

			} else {
				println_debug("atNPC 1: " + NPCID);
			}
			if (PutNPCCoords == true) {
				skillX = server.npcHandler.npcs[NPCSlot].absX;
				skillY = server.npcHandler.npcs[NPCSlot].absY;
			}
			break;

		case 17:
			// second Click npc
			NPCSlot = inStream.readUnsignedWordBigEndianA();
			if ((NPCSlot < 0) || (NPCSlot >= server.npcHandler.npcs.length)
					|| (server.npcHandler.npcs[NPCSlot] == null))
				break;
			NPCID = server.npcHandler.npcs[NPCSlot].npcType;
			long time = System.currentTimeMillis();
			if (time - globalCooldown[0] <= 50) {
				sendMessage("Action throttled... please wait longer before acting!");
				break;
			}
			if (time - lastMouse > 5000) {
				sendMessage("Client hack detected!");
				println("Suspicious activity!");
				break;
			}

			globalCooldown[0] = time;
			int npcX = server.npcHandler.npcs[NPCSlot].absX;
			int npcY = server.npcHandler.npcs[NPCSlot].absY;
			if ((Math.abs(absX - npcX) > 50) || (Math.abs(absY - npcY) > 50)) {
				sendMessage("Client hack detected!");
				break;
			}
			if (server.npcHandler.npcs[NPCSlot].IsDead) {
				sendMessage("That monster has been killed!");
				break;
			}
			FishingGo = false;
			PutNPCCoords = false;
			if ((NPCID == 494) || (NPCID == 495) || (NPCID == 496)
					|| (NPCID == 497) || (NPCID == 2354) || (NPCID == 2355)
					|| (NPCID == 2619) || (NPCID == 3198)) {
				/* Banking */
				skillX = server.npcHandler.npcs[NPCSlot].absX;
				skillY = server.npcHandler.npcs[NPCSlot].absY;
				WanneBank = 2;
			} else if ((NPCID == 300) || (NPCID == 844) || (NPCID == 462)) {
				/* Essence Mine Guys */
				IsUsingSkill = true;
				skillX = server.npcHandler.npcs[NPCSlot].absX;
				skillY = server.npcHandler.npcs[NPCSlot].absY;
				stairs = 26;
				stairDistance = 1;
				if (NPCID == 300) {
					Essence = 1;
				} else if (NPCID == 844) {
					Essence = 2;
				} else if (NPCID == 462) {
					Essence = 3;
				}
			} else if (NPCID == 461) {
				// Magic store owner shop -bakatool
				PutNPCCoords = true;
				WanneShop = 39; // Magic store owner shop
			} else if (NPCID == 553) {
				// Aubury rune shop
				PutNPCCoords = true;
				WanneShop = 2; // Aubury Magic Shop
			} else if ((NPCID == 522) || (NPCID == 523)) {
				// Shop Keeper + Assistant
				PutNPCCoords = true;
				WanneShop = 1; // Varrock General Store
			} else if ((NPCID == 526) || (NPCID == 527)) {
				// Shop Keeper + Assistant
				PutNPCCoords = true;
				WanneShop = 3; // Falador General Store
			} else if (NPCID == 577) {
				// Cassie
				PutNPCCoords = true;
				WanneShop = 4; // Falador Shield Shop
			} else if (NPCID == 580) {
				// Flynn
				PutNPCCoords = true;
				WanneShop = 5; // Falador Mace Shop
			} else if (NPCID == 538) {
				// Peksa
				PutNPCCoords = true;
				WanneShop = 6; // Barbarian Vullage Helmet Shop
			} else if (NPCID == 546) {
				// Zaff
				PutNPCCoords = true;
				WanneShop = 7; // Varrock Staff Shop
			} else if (NPCID == 548) {
				// Thessalia
				PutNPCCoords = true;
				WanneShop = 8; // Varrock Cloth shop
			} else if ((NPCID == 551) || (NPCID == 552)) {
				// Shop Keeper + Assistant
				PutNPCCoords = true;
				WanneShop = 9; // Varrock Sword shop
			} else if (NPCID == 549) {
				// Horvik
				PutNPCCoords = true;
				WanneShop = 10; // Varrock Armor shop
			} else if (NPCID == 550) {
				// Lowe
				PutNPCCoords = true;
				WanneShop = 11; // Varrock Armor shop
			} else if (NPCID == 584) {
				// Heruin
				PutNPCCoords = true;
				WanneShop = 12; // Falador Gem Shop
			} else if (NPCID == 581) {
				// Wayne
				PutNPCCoords = true;
				WanneShop = 13; // Falador Chainmail Shop
			} else if (NPCID == 585) {
				// Rommik
				PutNPCCoords = true;
				WanneShop = 14; // Rimmington Crafting Shop
			} else if ((NPCID == 531) || (NPCID == 530)) {
				// Shop Keeper + Assistant
				PutNPCCoords = true;
				WanneShop = 15; // Rimmington General Store
			} else if (NPCID == 1860) {
				// Brian
				PutNPCCoords = true;
				WanneShop = 16; // Rimmington Archery Shop
			} else if (NPCID == 557) {
				// Wydin
				PutNPCCoords = true;
				WanneShop = 17; // Port Sarim Food Shop
			} else if (NPCID == 558) {
				// Gerrant
				PutNPCCoords = true;
				WanneShop = 18; // Port Sarim Fishing Shop
			} else if (NPCID == 559) {
				// Brian
				PutNPCCoords = true;
				WanneShop = 19; // Port Sarim Battleaxe Shop
			} else if (NPCID == 556) {
				// Grum
				PutNPCCoords = true;
				WanneShop = 20; // Port Sarim Jewelery Shop
			} else if (NPCID == 583) {
				// Betty
				PutNPCCoords = true;
				WanneShop = 21; // Port Sarim Magic Shop
			} else if ((NPCID == 520) || (NPCID == 521)) {
				// Shop Keeper + Assistant
				PutNPCCoords = true;
				WanneShop = 22; // Lumbridge General Store
			} else if (NPCID == 519) {
				// Bob
				PutNPCCoords = true;
				WanneShop = 23; // Lumbridge Axe Shop
			} else if (NPCID == 541) {
				// Zeke
				PutNPCCoords = true;
				WanneShop = 24; // Al-Kharid Scimitar Shop
			} else if (NPCID == 545) {
				// Dommik
				PutNPCCoords = true;
				WanneShop = 25; // Al-Kharid Crafting Shop
			} else if ((NPCID == 524) || (NPCID == 525)) {
				// Shop Keeper + Assistant
				PutNPCCoords = true;
				WanneShop = 26; // Al-Kharid General Store
			} else if (NPCID == 542) {
				// Louie Legs
				PutNPCCoords = true;
				WanneShop = 27; // Al-Kharid Legs Shop
			} else if (NPCID == 544) {
				// Ranael
				PutNPCCoords = true;
				WanneShop = 28; // Al-Kharid Skirt Shop
			} else if (NPCID == 2621) {
				// Hur-Koz
				PutNPCCoords = true;
				WanneShop = 29; // TzHaar Shop Weapons,Amour
			} else if (NPCID == 2622) {
				// Hur-Lek
				PutNPCCoords = true;
				WanneShop = 30; // TzHaar Shop Runes
			} else if (NPCID == 2620) {
				// Hur-Tel
				PutNPCCoords = true;
				WanneShop = 31; // TzHaar Shop General Store
			} else if (NPCID == 692) {
				// Throwing shop
				PutNPCCoords = true;
				WanneShop = 32; // Authentic Throwing Weapons
			} else if (NPCID == 683) {
				// Bow and arrows
				PutNPCCoords = true;
				WanneShop = 33; // Dargaud's Bow and Arrows
			} else if (NPCID == 682) {
				// Archer's Armour
				PutNPCCoords = true;
				WanneShop = 34; // Aaron's Archery Appendages
			} else if (NPCID == 537) {
				// Scavvo
				PutNPCCoords = true;
				WanneShop = 35; // Champion's Rune shop
			} else if (NPCID == 536) {
				// Valaine
				PutNPCCoords = true;
				WanneShop = 36; // Champion's guild shop
			} else if (NPCID == 933) {
				// Legend's Shop
				PutNPCCoords = true;
				WanneShop = 37; // Legend's Shop
			} else if (NPCID == 932) {
				// Legends General Store
				PutNPCCoords = true;
				WanneShop = 38; // Legend's Gen. Store
			} else if (NPCID == 804) {
				PutNPCCoords = true;
				WanneShop = 25; // Crafting shop

			} else if ((NPCID == 1) || (NPCID == 2) || (NPCID == 3)
					|| (NPCID == 4) || (NPCID == 5) || (NPCID == 6)) {
				// THEIVING MAN & WOMEN
				if ((stealtimer < 0) && (EntangleDelay < 0)) {
					robman();
					EntangleDelay = 6;
					stealtimer = 5;
					// snaretimer = 5;
					actionTimer = 4;
					setAnimation(881);

					AnimationReset = true;
					updateRequired = true;
					appearanceUpdateRequired = true;
				}
			} else if (NPCID == 7) {
				// THEIVING farmer
				if (playerLevel[17] < 10) {
					sendMessage("You need 10 theiving to pickpocket farmers.");
				} else {
					if ((stealtimer < 0) && (EntangleDelay < 0)) {
						robfarmer();
						EntangleDelay = 6;
						stealtimer = 5;
						// snaretimer = 5;
						actionTimer = 4;
						setAnimation(881);

						AnimationReset = true;
						updateRequired = true;
						appearanceUpdateRequired = true;
					}
				}
			} else if ((NPCID == 15) || (NPCID == 18) || (NPCID == 1318)) {
				// THEIVING warrior
				if (playerLevel[17] < 25) {
					sendMessage("You need 25 theiving to pickpocket warriors.");
				} else {

					if ((stealtimer < 0) && (EntangleDelay < 0)) {
						robwarrior();
						EntangleDelay = 6;
						stealtimer = 15;
						// snaretimer = 5;
						actionTimer = 4;
						setAnimation(881);

						AnimationReset = true;
						updateRequired = true;
						appearanceUpdateRequired = true;
					}
				}
			} else if (NPCID == 187) {
				// THEIVING rogue
				if (playerLevel[17] < 32) {
					sendMessage("You need 32 theiving to pickpocket rogues.");
				} else {

					if ((stealtimer < 0) && (EntangleDelay < 0)) {
						robrogue();
						EntangleDelay = 6;
						stealtimer = 15;
						// snaretimer = 5;
						actionTimer = 4;
						setAnimation(881);

						AnimationReset = true;
						updateRequired = true;
						appearanceUpdateRequired = true;
					}
				}
			} else if ((NPCID == 2234) || (NPCID == 2235)) {
				// THEIVING master farmer
				if (playerLevel[17] < 38) {
					sendMessage("You need 38 theiving to pickpocket master farmers.");
				} else {

					if ((stealtimer < 0) && (EntangleDelay < 0)) {
						robmasterfarmer();
						EntangleDelay = 6;
						stealtimer = 15;
						// snaretimer = 5;
						actionTimer = 4;
						setAnimation(881);

						AnimationReset = true;
						updateRequired = true;
						appearanceUpdateRequired = true;
					}
				}
			} else if ((NPCID == 9) || (NPCID == 10) || (NPCID == 32)
					|| (NPCID == 334) || (NPCID == 335) || (NPCID == 336)
					|| (NPCID == 368) || (NPCID == 678) || (NPCID == 812)
					|| (NPCID == 887)) {
				// THEIVING guard
				if (playerLevel[17] < 40) {
					sendMessage("You need 40 theiving to pickpocket gaurds.");
				} else {
					if ((stealtimer < 0) && (EntangleDelay < 0)) {
						robguard();
						EntangleDelay = 6;
						stealtimer = 15;
						// snaretimer = 5;
						actionTimer = 4;
						setAnimation(881);

						AnimationReset = true;
						updateRequired = true;
						appearanceUpdateRequired = true;
					}
				}
			} else if (NPCID == 660) {
				// THEIVING knight
				if (playerLevel[17] < 55) {
					sendMessage("You need 55 theiving to pickpocket knights.");
				} else {

					if ((stealtimer < 0) && (EntangleDelay < 0)) {
						robknight();
						EntangleDelay = 6;
						stealtimer = 15;
						// snaretimer = 5;
						actionTimer = 4;
						setAnimation(881);

						AnimationReset = true;
						updateRequired = true;
						appearanceUpdateRequired = true;
					}
				}
			} else if (NPCID == 34) {
				// THEIVING watchmen
				if (playerLevel[17] < 65) {
					sendMessage("You need 65 theiving to pickpocket watchmen.");
				} else {

					if ((stealtimer < 0) && (EntangleDelay < 0)) {
						robwatchman();
						EntangleDelay = 6;
						stealtimer = 15;
						// snaretimer = 5;
						actionTimer = 4;
						setAnimation(881);

						AnimationReset = true;
						updateRequired = true;
						appearanceUpdateRequired = true;
					}
				}
			} else if (NPCID == 20) {
				// THEIVING paladin
				if (playerLevel[17] < 70) {
					sendMessage("You need 70 theiving to pickpocket paladins.");
				} else {

					if ((stealtimer < 0) && (EntangleDelay < 0)) {
						robpaladin();
						EntangleDelay = 6;
						stealtimer = 15;
						// snaretimer = 5;
						actionTimer = 4;
						setAnimation(881);

						AnimationReset = true;
						updateRequired = true;
						appearanceUpdateRequired = true;
					}
				}
			} else if ((NPCID == 66) || (NPCID == 67) || (NPCID == 68)) {
				// THEIVING gnome
				if (playerLevel[17] < 75) {
					sendMessage("You need 75 theiving to pickpocket gnomes.");
				} else {

					if ((stealtimer < 0) && (EntangleDelay < 0)) {
						robgnome();
						EntangleDelay = 6;
						stealtimer = 15;
						// snaretimer = 5;
						actionTimer = 4;
						setAnimation(881);

						AnimationReset = true;
						updateRequired = true;
						appearanceUpdateRequired = true;
					}
				}
			} else if (NPCID == 21) {
				// THEIVING hero
				if (playerLevel[17] < 80) {
					sendMessage("You need 80 theiving to pickpocket heros.");
				} else {

					if ((stealtimer < 0) && (EntangleDelay < 0)) {
						robhero();
						EntangleDelay = 6;
						stealtimer = 15;
						// snaretimer = 5;
						actionTimer = 4;
						setAnimation(881);

						AnimationReset = true;
						updateRequired = true;
						appearanceUpdateRequired = true;
					}
				}
			} else if ((NPCID == 2359) || (NPCID == 2360) || (NPCID == 2361)
					|| (NPCID == 2362)) {
				// THEIVING elf
				if (playerLevel[17] < 85) {
					sendMessage("You need 85 theiving to pickpocket elves.");
				} else {

					if ((stealtimer < 0) && (EntangleDelay < 0)) {
						robelf();
						EntangleDelay = 6;
						stealtimer = 15;
						// snaretimer = 5;
						actionTimer = 4;
						setAnimation(881);

						AnimationReset = true;
						updateRequired = true;
						appearanceUpdateRequired = true;
					}
				}

			} else if ((stealtimer > 0) && (EntangleDelay < 0)) {
				// snaretimer = 3;
				robfail();
				animation(348, absY, absX);
				actionTimer = 4;
				setAnimation(424);
				AnimationReset = true;
				updateRequired = true;
				appearanceUpdateRequired = true;
				sendMessage("Pickpocket attempt Failed!");

			} else {
				println_debug("atNPC 2: " + NPCID);
			}
			if (PutNPCCoords == true) {
				skillX = server.npcHandler.npcs[NPCSlot].absX;
				skillY = server.npcHandler.npcs[NPCSlot].absY;
			}

			break;

		case 21:
			// Thirth Click npc
			NPCSlot = inStream.readSignedWord();

			NPCID = server.npcHandler.npcs[NPCSlot].npcType;
			if ((NPCID < server.npcHandler.npcs.length) && (NPCID > 0)) {
				if (NPCID == 553) {
					/* Mage arena tele */
					teleportToX = 3100 + misc.random(5);
					teleportToY = 3930 + misc.random(5);
				} else {
					println_debug("atNPC 3: " + NPCID);
				}
			}
			break;

		case 72:
			// Click to attack
			if ((System.currentTimeMillis() - lastAttack > 1500)
					&& (deathStage < 1)) {
				attacknpc = inStream.readUnsignedWordA();
				if ((attacknpc >= 0)
						&& (attacknpc < server.npcHandler.maxNPCSpawns)) {
					IsAttackingNPC = true;

					if (server.npcHandler.npcs[attacknpc].StartKilling == 0) {
						server.npcHandler.npcs[attacknpc].StartKilling = playerId;
					}
					server.npcHandler.npcs[attacknpc].RandomWalk = false;
					server.npcHandler.npcs[attacknpc].IsUnderAttack = true;
					actionTimer = 5;
				} else {
					sendMessage("Exception catched, npc id was invalid.");
					ResetAttackNPC();
				}
			}
			break;

		case 121:
			replaceDoors();
			if (heightLevel == 0) {
				ReplaceObject2(2613, 3084, 3994, -3, 11);
				ReplaceObject2(2628, 3151, 2104, -3, 11);
				ReplaceObject2(2629, 3151, 2105, -3, 11);
				ReplaceObject2(2733, 3374, 6420, -1, 11);
				// ReplaceObject2(2691, 9774, 2107, 0, 11);
			}
			// if(heightLevel == 1)
			// ReplaceObject(2591, 3107, 375, -3, 11);
			// we could use this to make the char appear for other players only
			// until
			// this guys loading is done. Also wait with regular player updates
			// until we receive this command.
			// println_debug("Loading finished.");
			break;

		case 122:
			// Call for burying bones
			junk = inStream.readSignedWordBigEndianA();
			ItemSlot = inStream.readUnsignedWordA();
			ItemID = inStream.readUnsignedWordBigEndian();
			if (System.currentTimeMillis() - lastAction >= 850) {
				buryItem(ItemID, ItemSlot);
				lastAction = System.currentTimeMillis();
				actionTimer = 10;
			}
			break;

		case 253:
			// call for burning fires
			skillX = inStream.readSignedWordBigEndian();
			skillY = inStream.readSignedWordBigEndianA();
			ItemID = inStream.readUnsignedWordA();
			println("packet 253:  skillX=" + skillX + ", skillY=" + skillY);
			if ((IsUsingSkill == false) && (CheckForSkillUse4(ItemID) == true)) {
				IsUsingSkill = true;
			}
			break;

		case 53:
			// Use item on item
			int usedWithSlot = inStream.readUnsignedWord();
			int itemUsedSlot = inStream.readUnsignedWordA();
			// int useWith = inStream.readUnsignedWordBigEndianA();
			int interface1284 = inStream.readUnsignedWord();
			// int itemUsed = inStream.readSignedWordBigEndian();
			int interfacek = inStream.readUnsignedWord();
			// usedWithSlot += 1;
			// itemUsedSlot += 1;
			int useWith = playerItems[usedWithSlot] - 1;
			int itemUsed = playerItems[itemUsedSlot] - 1;
			if (!playerHasItem(itemUsed) || !playerHasItem(useWith)) {
				break;
			}
			int otherItem = playerItems[usedWithSlot] - 1;
			// println("itemUsed=" + itemUsed + ", usedWithSlot=" + usedWithSlot
			// + ", otherItem=" + otherItem);
			if (((itemUsed == 5605) || (otherItem == 5605))
					&& ((itemUsed == 1511) || (otherItem == 1511))) {
				shafting = true;
			}
			if (((itemUsed == 1733) || (otherItem == 1733))
					&& ((itemUsed == 1741) || (otherItem == 1741))) {
				showInterface(2311);
			}
			if (playerHasItem(314, 15) && playerHasItem(52, 15)
					&& ((itemUsed == 314) || (otherItem == 314))
					&& ((itemUsed == 52) || (otherItem == 52))) {
				if (playerHasItem(-1)) {
					deleteItem(314, 15);
					deleteItem(52, 15);
					addItem(53, 15);
				} else {
					sendMessage("Inventory is full!");
				}
			}
			if (((itemUsed == 5605) || (otherItem == 5605))
					&& ((itemUsed == 1511) || (otherItem == 1511))) {
				shafting = true;
			}
			int[] heads = { 39, 40, 41, 42, 43, 44 };
			int[] arrows = { 882, 884, 886, 888, 890, 892 };
			int[] required = { 1, 5, 25, 50, 75, 99 };
			for (int h = 0; h < heads.length; h++) {
				if (playerHasItem(heads[h], 15) && playerHasItem(53, 15)
						&& ((itemUsed == heads[h]) || (otherItem == heads[h]))
						&& ((itemUsed == 53) || (otherItem == 53))) {
					if (playerLevel[playerFletching] < required[h]) {
						sendMessage("Requires level " + required[h]
								+ " fletching");
						break;
					}
					deleteItem(heads[h], 15);
					deleteItem(53, 15);
					addItem(arrows[h], 15);
					addSkillXP(200, playerFletching);
					break;
				}
			}
			// regular, oak, maple, willow, yew, magic?
			for (int id = 0; id < logs.length; id++) {
				if (((itemUsed == logs[id]) || (otherItem == logs[id]))
						&& ((itemUsed == 5605) || (otherItem == 5605))) {
					// emote 885 (funny=3129)
					dialog = true;
					dialogInterface = 2459;
					dialogId = 1;
					fletchLog = id;
					sendFrame126("Select a bow", 8879);
					sendFrame246(8870, 250, longbows[id]); // right picture
					sendFrame246(8869, 250, shortbows[id]); // left picture
					sendFrame126(getItemName(shortbows[id]), 8871);
					sendFrame126(getItemName(shortbows[id]), 8874);
					sendFrame126(getItemName(longbows[id]), 8878);
					sendFrame126(getItemName(longbows[id]), 8875);
					sendFrame164(8866);
					break;
				}
			}
			for (int id1 = 0; id1 < shortbow.length; id1++) {
				if (((itemUsed == shortbows[id1]) || (otherItem == shortbows[id1]))
						&& ((itemUsed == 1777) || (otherItem == 1777))) {
					deleteItem(shortbows[id1], 1);
					deleteItem(1777, 1);
					addItem(shortbow[id1], 1);
				}
			}
			for (int b2 = 0; b2 < shortbow.length; b2++) {
				if (((itemUsed == longbows[b2]) || (otherItem == longbows[b2]))
						&& ((itemUsed == 1777) || (otherItem == 1777))) {
					deleteItem(longbows[b2], 1);
					deleteItem(1777, 1);
					addItem(longbow[b2], 1);
				}
			}
			for (int h = 0; h < leathers.length; h++) {
				if (((itemUsed == 1733) || (otherItem == 1733))
						&& ((itemUsed == leathers[h]) || (otherItem == leathers[h]))) {
					craftMenu(h);
					cIndex = h;
				}
			}
			if ((itemUsed == 233) && (useWith == 237)) {
				deleteItem(237, getItemSlot(237), 1);
				addItem(235, 1);
			} else if ((itemUsed == 590) && (useWith == 1511)) {
				if (playerLevel[11] >= 0) {
					deleteItem(1511, getItemSlot(1511), 1);
					// createNewTileObject(currentX, currentY, 2732, 0, 10);
					addObject(currentX, currentY, 2732, 0);
					addSkillXP(90, 11);
				} else {
					sendMessage("You need a firemaking of 0 to burn normal logs.");
				}
			} else if ((itemUsed == 590) && (useWith == 1521)) {
				if (playerLevel[11] >= 15) {
					deleteItem(1521, getItemSlot(1521), 1);
					createNewTileObject(currentX, currentY, 2732, 0, 10);
					addSkillXP(60, 11);
				} else {
					sendMessage("You need a firemaking level of 15 to burn oak logs.");
				}
			}

			else if ((itemUsed == 590) && (useWith == 1519)) {
				if (playerLevel[11] >= 30) {
					deleteItem(1519, getItemSlot(1519), 1);
					addObject(absX, absY, 2732, 0);
					addSkillXP(90, 11);
				} else {
					sendMessage("You need a firemaking of 30 to burn willow logs.");
				}
			} else if ((itemUsed == 590) && (useWith == 1517)) {
				if (playerLevel[11] >= 45) {
					deleteItem(1517, getItemSlot(1517), 1);
					createNewTileObject(currentX, currentY, 2732, 0, 10);
					addSkillXP(135, 11);
				} else {
					sendMessage("You need a firemaking level of 45 to burn maple logs.");
				}
			}

			else if ((itemUsed == 590) && (useWith == 1515)) {
				if (playerLevel[11] >= 60) {
					deleteItem(1515, getItemSlot(1515), 1);
					createNewTileObject(currentX, currentY, 2732, 0, 10);
					addSkillXP(202, 11);
				} else {
					sendMessage("You need a firemaking of 60 to burn yew logs.");
				}
			} else if ((itemUsed == 590) && (useWith == 1513)) {
				if (playerLevel[11] >= 75) {
					deleteItem(1513, getItemSlot(1513), 1);
					createNewTileObject(currentX, currentY, 2732, 0, 10);
					addSkillXP(303, 11);
				} else {
					sendMessage("You need a firemaking level of 75 to burn magic logs.");
				}
			}

			else if ((itemUsed == 1511) && (useWith == 590)) {
				if (playerLevel[11] >= 0) {
					deleteItem(1511, getItemSlot(1511), 1);
					createNewTileObject(currentX, currentY, 2732, 0, 10);
					addSkillXP(40, 11);
				} else {
					sendMessage("You need a firemaking of 0 to burn normal logs.");
				}
			} else if ((itemUsed == 1521) && (useWith == 590)) {
				if (playerLevel[11] >= 15) {
					deleteItem(1521, getItemSlot(1521), 1);
					createNewTileObject(currentX, currentY, 2732, 0, 10);
					addSkillXP(60, 11);
				} else {
					sendMessage("You need a firemaking level of 15 to burn oak logs.");
				}
			}

			else if ((itemUsed == 1519) && (useWith == 590)) {
				if (playerLevel[11] >= 30) {
					deleteItem(1519, getItemSlot(1519), 1);
					createNewTileObject(currentX, currentY, 2732, 0, 10);
					addSkillXP(90, 11);
				} else {
					sendMessage("You need a firemaking of 30 to burn willow logs.");
				}
			} else if ((itemUsed == 1517) && (useWith == 590)) {
				if (playerLevel[11] >= 45) {
					deleteItem(1517, getItemSlot(1517), 1);
					createNewTileObject(currentX, currentY, 2732, 0, 10);
					addSkillXP(135, 11);
				} else {
					sendMessage("You need a firemaking level of 45 to burn maple logs.");
				}
			}

			else if ((itemUsed == 1515) && (useWith == 590)) {
				if (playerLevel[11] >= 60) {
					deleteItem(1515, getItemSlot(1515), 1);
					createNewTileObject(currentX, currentY, 2732, 0, 10);
					addSkillXP(202, 11);
				} else {
					sendMessage("You need a firemaking of 60 to burn yew logs.");
				}
			} else if ((itemUsed == 1513) && (useWith == 590)) {
				if (playerLevel[11] >= 75) {
					deleteItem(1513, getItemSlot(1513), 1);
					createNewTileObject(currentX, currentY, 2732, 0, 10);
					addSkillXP(303, 11);
				} else {
					sendMessage("You need a firemaking level of 75 to burn magic logs.");
				}
			}

			// herblore

			if ((itemUsed == 233) && (useWith == 237)) {
				deleteItem(237, getItemSlot(237), 1);
				addItem(235, 1);
			}
			if ((itemUsed == 237) && (useWith == 233)) {
				deleteItem(237, getItemSlot(237), 1);
				addItem(235, 1);
			}
			if ((itemUsed == 233) && (useWith == 243)) {
				deleteItem(243, getItemSlot(243), 1);
				addItem(241, 1);
			}
			if ((itemUsed == 243) && (useWith == 233)) {
				deleteItem(243, getItemSlot(243), 1);
				addItem(241, 1);
			}
			if ((itemUsed == 233) && (useWith == 1973)) {
				deleteItem(1973, getItemSlot(1973), 1);
				addItem(1975, 1);
			}
			if ((itemUsed == 1973) && (useWith == 233)) {
				deleteItem(1973, getItemSlot(1973), 1);
				addItem(1975, 1);
			}
			//
			//
			if ((itemUsed == 249) && (useWith == 227)) {
				deleteItem(249, getItemSlot(249), 1);
				deleteItem(227, getItemSlot(227), 1);
				addItem(91, 1);
			}
			if ((itemUsed == 227) && (useWith == 249)) {
				deleteItem(249, getItemSlot(249), 1);
				deleteItem(227, getItemSlot(227), 1);
				addItem(91, 1);
			}
			if ((itemUsed == 251) && (useWith == 227)) {
				deleteItem(251, getItemSlot(251), 1);
				deleteItem(227, getItemSlot(227), 1);
				addItem(93, 1);
			}
			if ((itemUsed == 227) && (useWith == 251)) {
				deleteItem(251, getItemSlot(251), 1);
				deleteItem(227, getItemSlot(227), 1);
				addItem(93, 1);
			}
			if ((itemUsed == 253) && (useWith == 227)) {
				deleteItem(253, getItemSlot(253), 1);
				deleteItem(227, getItemSlot(227), 1);
				addItem(95, 1);
			}
			if ((itemUsed == 227) && (useWith == 253)) {
				deleteItem(253, getItemSlot(253), 1);
				deleteItem(227, getItemSlot(227), 1);
				addItem(95, 1);
			}
			if ((itemUsed == 255) && (useWith == 227)) {
				deleteItem(255, getItemSlot(255), 1);
				deleteItem(227, getItemSlot(227), 1);
				addItem(97, 1);
			}
			if ((itemUsed == 227) && (useWith == 255)) {
				deleteItem(255, getItemSlot(2559), 1);
				deleteItem(227, getItemSlot(227), 1);
				addItem(97, 1);
			}
			if ((itemUsed == 257) && (useWith == 227)) {
				deleteItem(257, getItemSlot(257), 1);
				deleteItem(227, getItemSlot(227), 1);
				addItem(99, 1);
			}
			if ((itemUsed == 227) && (useWith == 257)) {
				deleteItem(257, getItemSlot(257), 1);
				deleteItem(227, getItemSlot(227), 1);
				addItem(99, 1);
			}
			if ((itemUsed == 259) && (useWith == 227)) {
				deleteItem(259, getItemSlot(259), 1);
				deleteItem(227, getItemSlot(227), 1);
				addItem(101, 1);
			}
			if ((itemUsed == 227) && (useWith == 259)) {
				deleteItem(259, getItemSlot(259), 1);
				deleteItem(227, getItemSlot(227), 1);
				addItem(101, 1);
			}
			if ((itemUsed == 261) && (useWith == 227)) {
				deleteItem(261, getItemSlot(261), 1);
				deleteItem(227, getItemSlot(227), 1);
				addItem(103, 1);
			}
			if ((itemUsed == 227) && (useWith == 261)) {
				deleteItem(261, getItemSlot(261), 1);
				deleteItem(227, getItemSlot(227), 1);
				addItem(103, 1);
			}
			if ((itemUsed == 263) && (useWith == 227)) {
				deleteItem(263, getItemSlot(263), 1);
				deleteItem(227, getItemSlot(227), 1);
				addItem(105, 1);
			}
			if ((itemUsed == 227) && (useWith == 263)) {
				deleteItem(263, getItemSlot(263), 1);
				deleteItem(227, getItemSlot(227), 1);
				addItem(105, 1);
			}
			if ((itemUsed == 265) && (useWith == 227)) {
				deleteItem(265, getItemSlot(265), 1);
				deleteItem(227, getItemSlot(227), 1);
				addItem(107, 1);
			}
			if ((itemUsed == 227) && (useWith == 265)) {
				deleteItem(265, getItemSlot(265), 1);
				deleteItem(227, getItemSlot(227), 1);
				addItem(107, 1);
			}
			if ((itemUsed == 267) && (useWith == 227)) {
				deleteItem(267, getItemSlot(267), 1);
				deleteItem(227, getItemSlot(227), 1);
				addItem(109, 1);
			}
			if ((itemUsed == 227) && (useWith == 267)) {
				deleteItem(267, getItemSlot(267), 1);
				deleteItem(227, getItemSlot(227), 1);
				addItem(109, 1);
			}
			if ((itemUsed == 269) && (useWith == 227)) {
				deleteItem(269, getItemSlot(269), 1);
				deleteItem(227, getItemSlot(227), 1);
				addItem(111, 1);
			}
			if ((itemUsed == 227) && (useWith == 269)) {
				deleteItem(269, getItemSlot(269), 1);
				deleteItem(227, getItemSlot(227), 1);
				addItem(111, 1);
			}
			// 
			//
			if ((itemUsed == 91) && (useWith == 221)) {
				if (playerLevel[15] >= 1) {
					deleteItem(91, getItemSlot(91), 1);
					deleteItem(221, getItemSlot(221), 1);
					addItem(121, 1);
					addSkillXP(25, 15);
				} else {
					sendMessage("You need a higher herblore level to make this potion.");
				}
			}
			if ((itemUsed == 221) && (useWith == 91)) {
				if (playerLevel[15] >= 1) {
					deleteItem(91, getItemSlot(91), 1);
					deleteItem(221, getItemSlot(221), 1);
					addItem(121, 1);
					addSkillXP(25, 15);
				} else {
					sendMessage("You need a higher herblore level to make this potion.");
				}
			}
			if ((itemUsed == 93) && (useWith == 235)) {
				if (playerLevel[15] >= 5) {
					deleteItem(93, getItemSlot(93), 1);
					deleteItem(235, getItemSlot(235), 1);
					addItem(175, 1);
					addSkillXP(38, 15);
				} else {
					sendMessage("You need a higher herblore level to make this potion.");
				}
			}
			if ((itemUsed == 235) && (useWith == 93)) {
				if (playerLevel[15] >= 5) {
					deleteItem(93, getItemSlot(93), 1);
					deleteItem(235, getItemSlot(235), 1);
					addItem(175, 1);
					addSkillXP(38, 15);
				} else {
					sendMessage("You need a higher herblore level to make this potion.");
				}
			}
			if ((itemUsed == 95) && (useWith == 225)) {
				if (playerLevel[15] >= 12) {
					deleteItem(95, getItemSlot(95), 1);
					deleteItem(225, getItemSlot(225), 1);
					addItem(115, 1);
					addSkillXP(50, 15);
				} else {
					sendMessage("You need a higher herblore level to make this potion.");
				}
			}
			if ((itemUsed == 225) && (useWith == 95)) {
				if (playerLevel[15] >= 12) {
					deleteItem(95, getItemSlot(95), 1);
					deleteItem(225, getItemSlot(225), 1);
					addItem(115, 1);
					addSkillXP(50, 15);
				} else {
					sendMessage("You need a higher herblore level to make this potion.");
				}
			}
			if ((itemUsed == 97) && (useWith == 223)) {
				if (playerLevel[15] >= 22) {
					deleteItem(97, getItemSlot(97), 1);
					deleteItem(223, getItemSlot(223), 1);
					addItem(127, 1);
					addSkillXP(63, 15);
				} else {
					sendMessage("You need a higher herblore level to make this potion.");
				}
			}
			if ((itemUsed == 223) && (useWith == 97)) {
				if (playerLevel[15] >= 22) {
					deleteItem(97, getItemSlot(97), 1);
					deleteItem(223, getItemSlot(223), 1);
					addItem(127, 1);
					addSkillXP(63, 15);
				} else {
					sendMessage("You need a higher herblore level to make this potion.");
				}
			}
			if ((itemUsed == 97) && (useWith == 1975)) {
				if (playerLevel[15] >= 26) {
					deleteItem(97, getItemSlot(97), 1);
					deleteItem(1975, getItemSlot(1975), 1);
					addItem(3010, 1);
					addSkillXP(68, 15);
				} else {
					sendMessage("You need a higher herblore level to make this potion.");
				}
			}
			if ((itemUsed == 1975) && (useWith == 97)) {
				if (playerLevel[15] >= 26) {
					deleteItem(97, getItemSlot(97), 1);
					deleteItem(1975, getItemSlot(1975), 1);
					addItem(3010, 1);
					addSkillXP(68, 15);
				} else {
					sendMessage("You need a higher herblore level to make this potion.");
				}
			}
			if ((itemUsed == 99) && (useWith == 239)) {
				if (playerLevel[15] >= 30) {
					deleteItem(99, getItemSlot(99), 1);
					deleteItem(239, getItemSlot(239), 1);
					addItem(133, 1);
					addSkillXP(75, 15);
				} else {
					sendMessage("You need a higher herblore level to make this potion.");
				}
			}
			if ((itemUsed == 239) && (useWith == 99)) {
				if (playerLevel[15] >= 30) {
					deleteItem(99, getItemSlot(99), 1);
					deleteItem(239, getItemSlot(239), 1);
					addItem(133, 1);
					addSkillXP(75, 15);
				} else {
					sendMessage("You need a higher herblore level to make this potion.");
				}
			}
			if ((itemUsed == 99) && (useWith == 231)) {
				if (playerLevel[15] >= 38) {
					deleteItem(99, getItemSlot(99), 1);
					deleteItem(231, getItemSlot(231), 1);
					addItem(139, 1);
					addSkillXP(88, 15);
				} else {
					sendMessage("You need a higher herblore level to make this potion.");
				}
			}
			if ((itemUsed == 231) && (useWith == 99)) {
				if (playerLevel[15] >= 38) {
					deleteItem(99, getItemSlot(99), 1);
					deleteItem(231, getItemSlot(231), 1);
					addItem(139, 1);
					addSkillXP(88, 15);
				} else {
					sendMessage("You need a higher herblore level to make this potion.");
				}
			}
			if ((itemUsed == 101) && (useWith == 221)) {
				if (playerLevel[15] >= 45) {
					deleteItem(101, getItemSlot(101), 1);
					deleteItem(221, getItemSlot(221), 1);
					addItem(145, 1);
					addSkillXP(100, 15);
				} else {
					sendMessage("You need a higher herblore level to make this potion.");
				}
			}
			if ((itemUsed == 221) && (useWith == 101)) {
				if (playerLevel[15] >= 45) {
					deleteItem(101, getItemSlot(101), 1);
					deleteItem(221, getItemSlot(221), 1);
					addItem(145, 1);
					addSkillXP(100, 15);
				} else {
					sendMessage("You need a higher herblore level to make this potion.");
				}
			}
			if ((itemUsed == 101) && (useWith == 235)) {
				if (playerLevel[15] >= 48) {
					deleteItem(101, getItemSlot(101), 1);
					deleteItem(235, getItemSlot(235), 1);
					addItem(181, 1);
					addSkillXP(106, 15);
				} else {
					sendMessage("You need a higher herblore level to make this potion.");
				}
			}
			if ((itemUsed == 235) && (useWith == 101)) {
				if (playerLevel[15] >= 48) {
					deleteItem(101, getItemSlot(101), 1);
					deleteItem(235, getItemSlot(235), 1);
					addItem(181, 1);
					addSkillXP(106, 15);
				} else {
					sendMessage("You need a higher herblore level to make this potion.");
				}
			}
			if ((itemUsed == 103) && (useWith == 231)) {
				if (playerLevel[15] >= 50) {
					deleteItem(103, getItemSlot(103), 1);
					deleteItem(231, getItemSlot(231), 1);
					addItem(151, 1);
					addSkillXP(113, 15);
				} else {
					sendMessage("You need a higher herblore level to make this potion.");
				}
			}
			if ((itemUsed == 231) && (useWith == 103)) {
				if (playerLevel[15] >= 50) {
					deleteItem(103, getItemSlot(103), 1);
					deleteItem(231, getItemSlot(231), 1);
					addItem(151, 1);
					addSkillXP(113, 15);
				} else {
					sendMessage("You need a higher herblore level to make this potion.");
				}
			}
			if ((itemUsed == 103) && (useWith == 2970)) {
				if (playerLevel[15] >= 52) {
					deleteItem(103, getItemSlot(103), 1);
					deleteItem(2970, getItemSlot(2970), 1);
					addItem(3018, 1);
					addSkillXP(118, 15);
				} else {
					sendMessage("You need a higher herblore level to make this potion.");
				}
			}
			if ((itemUsed == 2970) && (useWith == 103)) {
				if (playerLevel[15] >= 52) {
					deleteItem(103, getItemSlot(103), 1);
					deleteItem(2970, getItemSlot(2970), 1);
					addItem(3018, 1);
					addSkillXP(118, 15);
				} else {
					sendMessage("You need a higher herblore level to make this potion.");
				}
			}
			if ((itemUsed == 105) && (useWith == 225)) {
				if (playerLevel[15] >= 55) {
					deleteItem(105, getItemSlot(105), 1);
					deleteItem(225, getItemSlot(225), 1);
					addItem(157, 1);
					addSkillXP(125, 15);
				} else {
					sendMessage("You need a higher herblore level to make this potion.");
				}
			}
			if ((itemUsed == 225) && (useWith == 105)) {
				if (playerLevel[15] >= 55) {
					deleteItem(105, getItemSlot(105), 1);
					deleteItem(225, getItemSlot(225), 1);
					addItem(157, 1);
					addSkillXP(125, 15);
				} else {
					sendMessage("You need a higher herblore level to make this potion.");
				}
			}
			if ((itemUsed == 105) && (useWith == 241)) {
				if (playerLevel[15] >= 60) {
					deleteItem(105, getItemSlot(105), 1);
					deleteItem(241, getItemSlot(241), 1);
					addItem(187, 1);
					addSkillXP(138, 15);
				} else {
					sendMessage("You need a higher herblore level to make this potion.");
				}
			}
			if ((itemUsed == 241) && (useWith == 105)) {
				if (playerLevel[15] >= 60) {
					deleteItem(105, getItemSlot(105), 1);
					deleteItem(241, getItemSlot(241), 1);
					addItem(187, 1);
					addSkillXP(138, 15);
				} else {
					sendMessage("You need a higher herblore level to make this potion.");
				}
			}
			if ((itemUsed == 109) && (useWith == 245)) {
				if (playerLevel[15] >= 72) {
					deleteItem(109, getItemSlot(109), 1);
					deleteItem(245, getItemSlot(245), 1);
					addItem(169, 1);
					addSkillXP(163, 15);
				} else {
					sendMessage("You need a higher herblore level to make this potion.");
				}
			}
			if ((itemUsed == 245) && (useWith == 109)) {
				if (playerLevel[15] >= 72) {
					deleteItem(109, getItemSlot(109), 1);
					deleteItem(245, getItemSlot(245), 1);
					addItem(169, 1);
					addSkillXP(163, 15);
				} else {
					sendMessage("You need a higher herblore level to make this potion.");
				}
			}
			if ((itemUsed == 111) && (useWith == 247)) {
				if (playerLevel[15] >= 78) {
					deleteItem(111, getItemSlot(111), 1);
					deleteItem(247, getItemSlot(247), 1);
					addItem(121, 1);
					addSkillXP(189, 15);
				} else {
					sendMessage("You need a higher herblore level to make this potion.");
				}
			}
			if ((itemUsed == 247) && (useWith == 111)) {
				if (playerLevel[15] >= 78) {
					deleteItem(111, getItemSlot(111), 1);
					deleteItem(247, getItemSlot(247), 1);
					addItem(189, 1);
					addSkillXP(175, 15);
				} else {
					sendMessage("You need a higher herblore level to make this potion.");
				}
			}
			if ((itemUsed == 2483) && (useWith == 241)) {
				if (playerLevel[15] >= 69) {
					deleteItem(2483, getItemSlot(2483), 1);
					deleteItem(241, getItemSlot(241), 1);
					addItem(2454, 1);
					addSkillXP(158, 15);
				} else {
					sendMessage("You need a higher herblore level to make this potion.");
				}
			}
			if ((itemUsed == 221) && (useWith == 2483)) {
				if (playerLevel[15] >= 69) {
					deleteItem(2483, getItemSlot(2483), 1);
					deleteItem(241, getItemSlot(241), 1);
					addItem(2454, 1);
					addSkillXP(158, 15);
				} else {
					sendMessage("You need a higher herblore level to make this potion.");
				}
			}
			if ((itemUsed == 2483) && (useWith == 3138)) {
				if (playerLevel[15] >= 76) {
					deleteItem(2483, getItemSlot(2483), 1);
					deleteItem(3138, getItemSlot(3138), 1);
					addItem(3042, 1);
					addSkillXP(173, 15);
				} else {
					sendMessage("You need a higher herblore level to make this potion.");
				}
			}
			if ((itemUsed == 3138) && (useWith == 2483)) {
				if (playerLevel[15] >= 76) {
					deleteItem(2483, getItemSlot(2483), 1);
					deleteItem(3138, getItemSlot(3138), 1);
					addItem(3042, 1);
					addSkillXP(173, 15);
				} else {
					sendMessage("You need a higher herblore level to make this potion.");
				}
			}

			break;

		// walkTo commands
		case 248:
			// map walk (has additional 14 bytes added to the end with some junk
			// data)
			packetSize -= 14; // ignore the junk
		case 164:
			// regular walk
		case 98:
			// walk on command
			if (randomed)
				break;
			if (!antiHax())
				break;
			if (inTrade)
				break;
			resetAction();
			if (!validClient) {
				sendMessage("You can't move on this account");
				break;
			}
			if (duelFight && (duelRule[5] || duelRule[9])) {
				sendMessage("You cannot retreat during this duel!");
				break;
			}
			if (fighting) {
				client enemy = (client) handler.players[fightId];
				// sendMessage("attack=" + fightId + ", hits=" + hits + ",
				// fighting=" + fighting);
				if ((fightId > 0) && (enemy != null) && (hits < 3)
						&& (enemy.hits < 3) && inRange(enemy.absX, enemy.absY)) {
					sendMessage("You cannot retreat during the first 3 rounds of combat!");
					break;
				} else if (((fightId > 0) && (enemy != null) && fighting)
						|| (!inRange(enemy.absX, enemy.absY))) {
					fighting = false;
					enemy.fighting = false;
					hits = 0;
					enemy.hits = 0;
				} else {
					fighting = false;
					hits = 0;
				}
			}
			IsAttackingNPC = false;
			attacknpc = -1;
			closeInterface();
			resetAnimation();
			if (deathStage == 0) {
				newWalkCmdSteps = packetSize - 5;
				if (newWalkCmdSteps % 2 != 0) {
					println_debug("Warning: walkTo(" + packetType
							+ ") command malformed: "
							+ misc.Hex(inStream.buffer, 0, packetSize));
				}
				newWalkCmdSteps /= 2;
				if (++newWalkCmdSteps > walkingQueueSize) {
					println_debug("Warning: walkTo(" + packetType
							+ ") command contains too many steps ("
							+ newWalkCmdSteps + ").");
					newWalkCmdSteps = 0;
					break;
				}
				int firstStepX = inStream.readSignedWordBigEndianA();
				int tmpFSX = firstStepX;

				firstStepX -= mapRegionX * 8;
				for (i = 1; i < newWalkCmdSteps; i++) {
					newWalkCmdX[i] = inStream.readSignedByte();
					newWalkCmdY[i] = inStream.readSignedByte();
					tmpNWCX[i] = newWalkCmdX[i];
					tmpNWCY[i] = newWalkCmdY[i];
				}
				newWalkCmdX[0] = newWalkCmdY[0] = tmpNWCX[0] = tmpNWCY[0] = 0;
				int firstStepY = inStream.readSignedWordBigEndian();
				int tmpFSY = firstStepY;

				firstStepY -= mapRegionY * 8;
				newWalkCmdIsRunning = inStream.readSignedByteC() == 1;
				if (isInWilderness(absX, absY, 1))
					newWalkCmdIsRunning = false;
				for (i = 0; i < newWalkCmdSteps; i++) {
					newWalkCmdX[i] += firstStepX;
					newWalkCmdY[i] += firstStepY;
				}
				poimiY = firstStepY;
				poimiX = firstStepX;

				// stairs check
				if (stairs > 0) {
					resetStairs();
				}
				// woodcutting check
				if (woodcutting[0] > 0) {
					// playerEquipment[playerWeapon] = OriginalWeapon;
					// OriginalWeapon = -1;
					resetAnimation();
					resetWC();
				}
				// pick up item check
				if (WannePickUp == true) {
					PickUpID = 0;
					PickUpAmount = 0;
					PickUpDelete = 0;
					WannePickUp = false;
				}
				// attack check
				if (IsAttacking == true) {
					ResetAttack();
				}
				// attack NPC check
				if (IsAttackingNPC == true) {
					ResetAttackNPC();
				}
				// mining check
				if (mining[0] > 0) {
					resetAnimation();
					resetMI();
				}
				// smithing check
				if (smithing[0] > 0) {
					/*
					 * playerEquipment[playerWeapon] = OriginalWeapon;
					 * OriginalWeapon = -1; playerEquipment[playerShield] =
					 * OriginalShield; OriginalShield = -1;
					 */
					updateRequired = true;
					appearanceUpdateRequired = true;
					resetAnimation();
					resetSM();
					RemoveAllWindows();
				}
				// Npc Talking
				if (NpcDialogue > 0) {
					NpcDialogue = 0;
					NpcTalkTo = 0;
					NpcDialogueSend = false;
					RemoveAllWindows();
				}
				// banking
				if (IsBanking == true) {
					RemoveAllWindows();
				}
				// shopping
				if (IsShopping == true) {
					IsShopping = false;
					MyShopID = 0;
					UpdateShop = false;
					RemoveAllWindows();
				}
				// trading
				// firemaking check
				if (firemaking[0] > 0) {
					playerEquipment[playerWeapon] = OriginalWeapon;
					OriginalWeapon = -1;
					playerEquipment[playerShield] = OriginalShield;
					OriginalShield = -1;
					resetAnimation();
					resetFM();
				}

			}
			break;

		case 4:
			// regular chat
			if (!validClient) {
				sendMessage("Please use another client");
				break;
			}
			if (muted)
				break;
			MBTC = misc.textUnpack(chatText, packetSize - 2);
			MBBC = misc.textUnpack(chatText, packetSize - 2);
			MBHT = misc.textUnpack(chatText, packetSize - 2);
			MBID = misc.textUnpack(chatText, packetSize - 2);
			chatTextEffects = inStream.readUnsignedByteS();
			chatTextColor = inStream.readUnsignedByteS();
			chatTextSize = (byte) (packetSize - 2);
			inStream.readBytes_reverseA(chatText, chatTextSize, 0);
			chatTextUpdateRequired = true;
			println_debug("Text [" + chatTextEffects + "," + chatTextColor
					+ "]: " + misc.textUnpack(chatText, packetSize - 2));
			break;

		case 14:
			// Use something on another player
			junk2 = inStream.readSignedWordBigEndianA(); // only needed to
			// get the cracker
			// slot ! (remove =
			// server crash !)
			junk = inStream.readSignedWordBigEndian(); // only needed to get
			// the cracker slot !
			// (remove = server
			// crash !)
			junk3 = inStream.readUnsignedWordA(); // only needed to get the
			// cracker slot ! (remove =
			// server crash !)
			int CrackerSlot = inStream.readSignedWordBigEndian();
			// if(CrackerSlot >= playerItems.length){
			// break;
			// }
			int CrackerID = playerItems[CrackerSlot];

			CrackerID -= 1; // Only to fix the ID !
			if ((CrackerID == 962) && playerHasItem(962)) {
				sendMessage("You crack the cracker...");
				int UsedOn = (int) (misc.HexToInt(inStream.buffer, 3, 1) / 1000);

				PlayerHandler.players[UsedOn].CrackerMsg = true;
				deleteItem(CrackerID, CrackerSlot, playerItemsN[CrackerSlot]);
				if (misc.random(2) == 1) {
					addItem(Item.randomPHat(), 1);
					sendMessage("And you get the crackers item.");
				} else {
					sendMessage("but you didn't get the crackers item.");
					PlayerHandler.players[UsedOn].CrackerForMe = true;
				}
			}
			break;

		// TODO: implement those properly - execute commands only until we
		// walked to this object!
		// atObject commands

		/*
		 * <Dungeon> Trapdoors: ID 1568, 1569, 1570, 1571 Ladders: ID 1759, 2113
		 * Climb rope: 1762, 1763, 1764
		 */

		case 101:
			// Character Design Screen
			if (!antiHax())
				break;
			int[] input = new int[13];
			int highest = -1,
			numZero = -1,
			num44 = 0;
			for (int b = 0; b < 13; b++) {
				input[b] = inStream.readSignedByte();
				println("C: " + b + " " + input[b]);
				if (input[b] > highest)
					highest = input[b];
				if (input[b] < 1)
					numZero++;
				if (input[b] < 0) // bakatool female fix.
					input[b] = 0;
				if (input[b] == 44)
					num44++;
			}
			if ((highest < 1) || (num44 == 7)) {
				if (uid > 1)
					server.bannedUid.add(new Integer(uid));
				isKicked = true;
				break;
			}
			pGender = input[0];
			println("gender " + pGender);
			pHead = input[1];
			pBeard = input[2]; // aka Jaw :S -bakatool
			pTorso = input[3];
			pArms = input[4];
			pHands = input[5];
			pLegs = input[6];
			pFeet = input[7];
			pHairC = input[8];
			pTorsoC = input[9];
			pLegsC = input[10];
			pFeetC = input[11];
			pSkinC = input[12];
			playerLook[0] = input[0]; // pGender -bakatool
			playerLook[1] = input[8]; // hairC -bakatool
			playerLook[2] = input[9]; // torsoC -bakatool
			playerLook[3] = input[10]; // legsC -bakatool
			playerLook[4] = input[11]; // feetC -bakatool
			playerLook[5] = input[12]; // skinC -bakatool
			apset = true;
			appearanceUpdateRequired = true;
			lookUpdate = true;
			break;
		case 132:
			int objectX = inStream.readSignedWordBigEndianA();
			int objectID = inStream.readUnsignedWord();
			int objectY = inStream.readUnsignedWordA();
			int face = 0;
			int face2 = 0;
			int GateID = 1;
			if (!validClient || randomed)
				break;
			if (!antiHax())
				break;
			if (debug || (playerRights > 1)) {
				println("serverobjs size " + server.objects.size());
				println_debug("atObject: " + objectX + "," + objectY
						+ " objectID: " + objectID); // 147 might be id for
				// object state changing
			}
			int xDiff = Math.abs(absX - objectX);
			int yDiff = Math.abs(absY - objectY);
			boolean found = false;
			for (Object o : server.objects) {
				if ((o.type == 1) && (objectX == o.x) && (objectY == o.y)
						&& (objectID == o.id)) {
					found = true;
					break;
				}
			}
			if (!found && adding)
				server.objects.add(new Object(objectID, objectX, objectY, 1));

			if (!found && (objectID != 2646) && (objectID != 1530))
				break;
			resetAction(false);
			TurnPlayerTo(objectX, objectY);
			updateRequired = true;
			appearanceUpdateRequired = true;
			if ((xDiff > 5) || (yDiff > 5)) {
				// sendMessage("Client hack detected!");
				break;
			}
			if (objectID == 2107) {
				if (System.currentTimeMillis() - server.lastRunite < 60000) {
					println("invalid timer");
					break;
				}
			}

			if (misc.random(100) == 1) {
				triggerRandom();
				break;
			}
			if ((objectID == 1733) && (objectX == 2724) && (objectY == 3374)) {
				if (!premium)
					resetPos();
				teleportToX = 2727;
				teleportToY = 9774;
				heightLevel = 0;
				break;
			}
			if ((objectID == 1734) && (objectX == 2724) && (objectY == 9774)) {
				if (!premium)
					resetPos();
				teleportToX = 2723;
				teleportToY = 3375;
				heightLevel = 0;
				break;
			}

			if ((objectID == 375)
					&& (((objectX == 2593) && (objectY == 3108)) || ((objectX == 2590) && (objectY == 3106)))) {
				if (System.currentTimeMillis() - lastAction < (1000 + misc
						.random(200))) {
					sendMessage("You can't try that often!");
					lastAction = System.currentTimeMillis();
					break;
				}
				if (playerLevel[playerThieving] < 70) {
					sendMessage("You must be level 70 thieving to open this chest");
					break;
				}
				lastAction = System.currentTimeMillis();
				if (System.currentTimeMillis() - handler.lastChest >= 15000) {
					handler.lastChest = System.currentTimeMillis();
					double roll = Math.random() * 100;
					if (roll < 0.3) {
						int[] items = { 2577, 2580, 2631 };
						int r = (int) (Math.random() * items.length);
						sendMessage("You have recieved a "
								+ getItemName(items[r]) + "!");
						addItem(items[r], 1);
					} else {
						int coins = misc.random(7000);
						sendMessage("You find " + coins
								+ " coins inside the chest");
						addItem(995, coins);
					}
					for (int p = 0; p < PlayerHandler.maxPlayers; p++) {
						client player = (client) server.playerHandler.players[p];
						if (player == null)
							continue;
						if ((player.playerName != null)
								&& (player.heightLevel == heightLevel)
								&& !player.disconnected
								&& (Math.abs(player.absY - absY) < 30)
								&& (Math.abs(player.absX - absX) < 30)) {
							player.stillgfx(444, objectY, objectX);
						}
					}
				} else {
					sendMessage("The chest is empty!");
				}
			}
			if ((objectID == 6420) && premium) {
				if (System.currentTimeMillis() - lastAction < (1000 + misc
						.random(200))) {
					sendMessage("You can't try that often!");
					lastAction = System.currentTimeMillis();
					break;
				}
				if (playerLevel[playerThieving] < 85) {
					sendMessage("You must be level 85 thieving to open this chest");
					break;
				}
				if (!premium) {
					resetPos();
				}
				lastAction = System.currentTimeMillis();
				if (System.currentTimeMillis() - handler.lastChest2 >= 15000) {
					handler.lastChest2 = System.currentTimeMillis();
					double roll = Math.random() * 100;
					if (roll < 0.3) {
						int[] items = { 1050, 2581, 2631 };
						int r = (int) (Math.random() * items.length);
						sendMessage("You have recieved a "
								+ getItemName(items[r]) + "!");
						addItem(items[r], 1);
					} else {
						int coins = misc.random(17000);
						sendMessage("You find " + coins
								+ " coins inside the chest");
						addItem(995, coins);
					}
					for (int p = 0; p < PlayerHandler.maxPlayers; p++) {
						client player = (client) server.playerHandler.players[p];
						if (player == null)
							continue;
						if ((player.playerName != null)
								&& (player.heightLevel == heightLevel)
								&& !player.disconnected
								&& (Math.abs(player.absY - absY) < 30)
								&& (Math.abs(player.absX - absX) < 30)) {
							player.stillgfx(444, objectY, objectX);
						}
					}
				} else {
					sendMessage("The chest is empty!");
				}
			}

			// FACE: 0= WEST | -1 = NORTH | -2 = EAST | -3 = SOUTH
			// ObjectType: 0-3 wall objects, 4-8 wall decoration, 9: diag.
			// walls, 10-11 world objects, 12-21: roofs, 22: floor decoration
			for (int d = 0; d < server.doorHandler.doorX.length; d++) {
				DoorHandler dh = server.doorHandler;
				if ((objectID == dh.doorId[d]) && (objectX == dh.doorX[d])
						&& (objectY == dh.doorY[d])) {
					int newFace = -3;
					if (dh.doorState[d] == 0) {
						// closed
						newFace = dh.doorFaceOpen[d];
						dh.doorState[d] = 1;
						dh.doorFace[d] = newFace;
					} else {
						newFace = dh.doorFaceClosed[d];
						dh.doorState[d] = 0;
						dh.doorFace[d] = newFace;
					}
					for (int p = 0; p < PlayerHandler.maxPlayers; p++) {
						client player = (client) server.playerHandler.players[p];
						if (player == null)
							continue;
						if ((player.playerName != null)
								&& (player.heightLevel == heightLevel)
								&& !player.disconnected && (player.absY > 0)
								&& (player.absX > 0)) {
							player.ReplaceObject(dh.doorX[d], dh.doorY[d],
									dh.doorId[d], newFace, 0);
						}
					}
				}
			}
			/*
			 * if(objectID == 1530){ if(objectX == 2716 && objectY == 3472){
			 * ReplaceObject(2716, 3472, 1530, -3, 0); } }
			 */
			if (objectID == 2290) {
				if ((objectX == 2576) && (objectY == 9506)) {
					teleportToX = 2572;
					teleportToY = 9507;
				} else if ((objectX == 2573) && (objectY == 9506)) {
					teleportToX = 2578;
					teleportToY = 9506;
				}
			}

			if ((objectID == 3443) && (objectX == 3440) && (objectY == 9886)) {
				// Holy barrier to canifis -bakatool
				teleportToX = 3422;
				teleportToY = 3484;
			}

			if ((objectID == 3432) && (objectX == 3422) && (objectY == 3485)) {
				// canifis to holy barrier -bakatool
				teleportToX = 3440;
				teleportToY = 9887;
			}
			if (objectID == 2321) {
				if (playerHasItem(1544)) {
					teleportToX = 2598;
					teleportToY = 9495;
				} else {
					sendMessage("You need an orange key to cross");
				}
			}
			if (objectID == 2318) {
				teleportToX = 2621;
				teleportToY = 9496;
				updateRequired = true;
			}
			if (objectID == 1728) {
				teleportToX = 2614;
				teleportToY = 9505;
				updateRequired = true;
			}
			if ((objectID == 6836)
					&& (System.currentTimeMillis() - lastAction >= (2000 + misc
							.random(200)))) {
				if (!(((objectX == 2604) || (objectX == 2606) || (objectX == 2608)) && (objectY == 3104))) {
					resetPos();
					break;
				}
				lastAction = System.currentTimeMillis();
				EntangleDelay = 6;
				stealtimer = 5;
				// snaretimer = 5;
				actionTimer = 4;
				setAnimation(881);
				addSkillXP(185, 17);
				AnimationReset = true;
				updateRequired = true;
				appearanceUpdateRequired = true;
			}
			if (objectID == 881) {
				heightLevel -= 1;
			}
			if ((objectID == 1591) && (objectX == 3268) && (objectY == 3435)) {
				if (combatLevel >= 80) {
					teleportToX = 2540;
					teleportToY = 4716;
				} else {
					sendMessage("You need to be level 80 or above to enter the mage arena.");
					sendMessage("The skeletons at the varrock castle are a good place until then.");
				}
			}
			if ((objectID == 5960) && (objectX == 2539) && (objectY == 4712)) {
				teleportToX = 3105;
				teleportToY = 3933;
			}

			// Wo0t Tzhaar Objects

			if ((objectID == 9369) && (objectX == 2399) && (objectY == 5176)) {
				// Hot vent door A
				if (absY == 5177) {
					teleportToX = 2399;
					teleportToY = 5175;

				}
			}
			if ((objectID == 9369) && (objectX == 2399) && (objectY == 5176)) {
				// Hot vent door AA
				if (absY == 5175) {
					teleportToX = 2399;
					teleportToY = 5177;

				}
			}

			if ((objectID == 9368) && (objectX == 2399) && (objectY == 5168)) {
				// Hot vent door B
				if (absY == 5169) {
					teleportToX = 2399;
					teleportToY = 5167;

				}
			}
			if ((objectID == 9368) && (objectX == 2399) && (objectY == 5168)) {
				// Hot vent door BB
				if (absY == 5167) {
					teleportToX = 2399;
					teleportToY = 5169;

				}
			}
			if ((objectID == 9391) && (objectX == 2399) && (objectY == 5172)) {
				// Tzhaar Fight bank
				openUpBank();
			}
			if ((objectID == 9356) && (objectX == 2437) && (objectY == 5166)) {
				// Tzhaar Jad Cave Enterance
				teleportToX = 2413;
				teleportToY = 5117;
				sendMessage("You have entered the Jad Cave.");
			}
			if ((objectID == 9357) && (objectX == 2412) && (objectY == 5118)) {
				// Tzhaar Jad Cave Exit
				teleportToX = 2438;
				teleportToY = 5168;
				sendMessage("You have left the Jad Cave.");
			}

			// End of Tzhaar Objects

			if (objectID == 2213) { // Bank Booth
				if (duelFight == true) {
					sendMessage("Sorry you cant open you bank in duel!");
				} else {
					skillX = objectX;
					skillY = objectY;
					NpcWanneTalk = 2;
				}
			}

			if (objectID == 6552) {
				// Ancient magic altar (temp !!!)
				if (GoodDistance(absX, absY, objectX, objectY, 1) == true) {
					if (playerAncientMagics == true) {
						// setSidebarInterface(6, 1151); // magic tab (ancient =
						// 12855);
						// playerAncientMagics = false;
					} else {
						setSidebarInterface(6, 12855); // magic tab (ancient =
						// 12855);
						playerAncientMagics = true;
					}
				}
			}
			// woodCutting
			// mining
			// if (actionTimer == 0) {
			if (CheckObjectSkill(objectID) == true) {
				IsUsingSkill = true;
				skillX = objectX;
				skillY = objectY;
			}
			// }
			// go upstairs
			if (true) {
				if ((objectID == 1747) || (objectID == 1750)) {
					stairs = 1;
					skillX = objectX;
					skillY = objectY;
					stairDistance = 1;
				} else if (objectID == 1738) {
					stairs = 1;
					skillX = objectX;
					skillY = objectY;
					stairDistance = 2;
				} else if (objectID == 1722) {
					stairs = 21;
					skillX = objectX;
					skillY = objectY;
					stairDistance = 2;
					stairDistanceAdd = 2;
				} else if (objectID == 1734) {
					stairs = 10;
					skillX = objectX;
					skillY = objectY;
					stairDistance = 3;
					stairDistanceAdd = 1;
				} else if (objectID == 55) {
					stairs = 15;
					skillX = objectX;
					skillY = objectY;
					stairDistance = 3;
					stairDistanceAdd = 1;
				} else if (objectID == 57) {
					stairs = 15;
					skillX = objectX;
					skillY = objectY;
					stairDistance = 3;
				} else if ((objectID == 1755) || (objectID == 5946)
						|| (objectID == 1757)) {
					stairs = 4;
					skillX = objectX;
					skillY = objectY;
					stairDistance = 1;
				} else if (objectID == 1764) {
					stairs = 12;
					skillX = objectX;
					skillY = objectY;
					stairDistance = 1;
				} else if (objectID == 2148) {
					stairs = 8;
					skillX = objectX;
					skillY = objectY;
					stairDistance = 1;
				} else if (objectID == 3608) {
					stairs = 13;
					skillX = objectX;
					skillY = objectY;
					stairDistance = 1;
				} else if (objectID == 2408) {
					stairs = 16;
					skillX = objectX;
					skillY = objectY;
					stairDistance = 1;
				} else if (objectID == 5055) {
					stairs = 18;
					skillX = objectX;
					skillY = objectY;
					stairDistance = 1;
				} else if (objectID == 5131) {
					stairs = 20;
					skillX = objectX;
					skillY = objectY;
					stairDistance = 1;
				} else if (objectID == 9359) {
					stairs = 24;
					skillX = objectX;
					skillY = objectY;
					stairDistance = 1;
					stairDistance = 1;
				} else if (objectID == 2492) {
					/* Essence Mine Portals */
					stairs = 25;
					skillX = objectX;
					skillY = objectY;
					stairDistance = 1;
				} else if (objectID == 2406) {
					/* Lost City Door */
					if (playerEquipment[playerWeapon] == 772) {
						// Dramen Staff
						stairs = 27;
						skillX = objectX;
						skillY = objectY;
						stairDistance = 1;
					} else {
						// Open Door
					}
				}
				// go downstairs
				if ((objectID == 1746) || (objectID == 1749)) {
					stairs = 2;
					skillX = objectX;
					skillY = objectY;
					stairDistance = 1;
				} else if (objectID == 1740) {
					stairs = 2;
					skillX = objectX;
					skillY = objectY;
					stairDistance = 1;
				} else if (objectID == 1723) {
					stairs = 22;
					skillX = objectX;
					skillY = objectY;
					stairDistance = 2;
					stairDistanceAdd = 2;
				} else if (objectID == 1733) {
					if (playerHasItem(1543)) {
						stairs = 9;
						skillX = objectX;
						skillY = objectY;
						stairDistance = 3;
						stairDistanceAdd = -1;
					} else {
						sendMessage("You need a red key to go down these stairs");
						break;
					}
				} else if (objectID == 54) {
					stairs = 14;
					skillX = objectX;
					skillY = objectY;
					stairDistance = 3;
					stairDistanceAdd = 1;
				} else if (objectID == 56) {
					stairs = 14;
					skillX = objectX;
					skillY = objectY;
					stairDistance = 3;
				} else if ((objectID == 1568) || (objectID == 5947)
						|| (objectID == 6434) || (objectID == 1759)
						|| (objectID == 1754) || (objectID == 1570)) {
					stairs = 3;
					skillX = objectX;
					skillY = objectY;
					stairDistance = 1;
				} else if (objectID == 2113) {
					// Mining guild stairs
					if (playerLevel[playerMining] >= 60) {
						stairs = 3;
						skillX = objectX;
						skillY = objectY;
						stairDistance = 1;
					} else {
						sendMessage("You need 60 mining to enter the mining guild.");
					}
				} else if (objectID == 492) {
					stairs = 11;
					skillX = objectX;
					skillY = objectY;
					stairDistance = 2;
				} else if (objectID == 2147) {
					stairs = 7;
					skillX = objectX;
					skillY = objectY;
					stairDistance = 1;
				} else if (objectID == 5054) {
					stairs = 17;
					skillX = objectX;
					skillY = objectY;
					stairDistance = 1;
				} else if (objectID == 5130) {
					stairs = 19;
					skillX = objectX;
					skillY = objectY;
					stairDistance = 1;
				} else if (objectID == 9358) {
					stairs = 23;
					skillX = objectX;
					skillY = objectY;
					stairDistance = 1;
				} else if (objectID == 5488) {
					stairs = 28;
					skillX = objectX;
					skillY = objectY;
					stairDistance = 1;
				}

				if ((skillX > -1) && (skillY > -1)) {
					IsUsingSkill = true;
				}
			}
			break;

		case 252:
			// atObject2
			objectID = inStream.readUnsignedWordBigEndianA(); // 5292
			// bankwindow
			objectY = inStream.readSignedWordBigEndian();
			objectX = inStream.readUnsignedWordA();
			if (debug || (playerRights > 1))
				println_debug("atObject2: " + objectX + "," + objectY
						+ " objectID: " + objectID);
			if (!antiHax())
				break;
			long now = System.currentTimeMillis();
			boolean oFound = false;
			for (Object o : server.objects) {
				if ((o.type == 2) && (objectX == o.x) && (objectY == o.y)
						&& (objectID == o.id)) {
					oFound = true;
					break;
				}
			}

			if (!oFound && adding)
				server.objects.add(new Object(objectID, objectX, objectY, 2));

			if (!oFound && (objectID != 2646))
				break;
			if ((objectID == 2646) && ((absX >= 2735) && (absX <= 2752))
					&& ((absY >= 3435) && (absY <= 3453))) {
				if (now - lastAction >= 900) {
					addItem(1779, 1);
					lastAction = now;
				}
			}
			if ((objectID == 2644) && (objectX == 2710) && (objectY == 3471)) {
				spinning = true;
				pEmote = 894;
				updateRequired = true;
				appearanceUpdateRequired = true;
			}
			if (objectID == 823) {
				Random r = new Random();
				teleportToX = 2602 + r.nextInt(5);
				teleportToY = 3162 + r.nextInt(5);
			}

			if ((objectID == 2213) || (objectID == 2214) || (objectID == 3045)
					|| (objectID == 5276) || (objectID == 6084)) {
				if (duelFight == true) {
					sendMessage("You may not use your bank when ur in duel");
				} else {
					skillX = objectX;
					skillY = objectY;
					WanneBank = 1;
				}
			}

			/*
			 * else if (objectID == 1739) { heightLevel += 1; teleportToX =
			 * absX; teleportToY = absY; }
			 */
			break;

		case 70:
			// atObject3
			objectX = inStream.readSignedWordBigEndian();
			objectY = inStream.readUnsignedWord();
			objectID = inStream.readUnsignedWordBigEndianA();
			if (debug)
				println_debug("atObject3: " + objectX + "," + objectY
						+ " objectID: " + objectID);

			boolean oooFound = false;
			for (Object o : server.objects) {
				if ((o.type == 3) && (objectX == o.x) && (objectY == o.y)
						&& (objectID == o.id)) {
					oooFound = true;
					break;
				}
			}

			if (!oooFound && adding)
				server.objects.add(new Object(objectID, objectX, objectY, 3));

			if (oooFound && (objectID == 1739)) {
				heightLevel -= 1;
				teleportToX = absX;
				teleportToY = absY;
			}
			break;

		case 95:
			// update chat
			Tradecompete = inStream.readUnsignedByte();
			Privatechat = inStream.readUnsignedByte();
			Publicchat = inStream.readUnsignedByte();
			for (int i1 = 1; i1 < handler.maxPlayers; i1++) {
				if ((handler.players[i1] != null)
						&& (handler.players[i1].isActive == true)) {
					handler.players[i1].pmupdate(playerId, GetWorld(playerId));
				}
			}
			break;

		case 188:
			// add friend
			friendUpdate = true;
			long friendtoadd = inStream.readQWord();
			boolean CanAdd = true;

			for (long element : friends) {
				if ((element != 0) && (element == friendtoadd)) {
					CanAdd = false;
					sendMessage(friendtoadd + " is already in your friendlist.");
				}
			}
			if (CanAdd == true) {
				for (int i1 = 0; i1 < friends.length; i1++) {
					if (friends[i1] == 0) {
						friends[i1] = friendtoadd;
						for (int i2 = 1; i2 < handler.maxPlayers; i2++) {
							if ((handler.players[i2] != null)
									&& handler.players[i2].isActive
									&& (misc
											.playerNameToInt64(handler.players[i2].playerName) == friendtoadd)) {
								if ((playerRights >= 2)
										|| (handler.players[i2].Privatechat == 0)
										|| ((handler.players[i2].Privatechat == 1) && handler.players[i2]
												.isinpm(misc
														.playerNameToInt64(playerName)))) {
									loadpm(friendtoadd, GetWorld(i2));
									break;
								}
							}
						}
						break;
					}
				}
			}
			break;

		case 215:
			// remove friend
			friendUpdate = true;
			long friendtorem = inStream.readQWord();

			for (int i1 = 0; i1 < friends.length; i1++) {
				if (friends[i1] == friendtorem) {
					friends[i1] = 0;
					break;
				}
			}
			break;

		case 133:
			// add ignore
			friendUpdate = true;
			long igtoadd = inStream.readQWord();

			for (int i10 = 0; i10 < ignores.length; i10++) {
				if (ignores[i10] == 0) {
					ignores[i10] = igtoadd;
					break;
				}
			}
			break;

		case 74:
			// remove ignore
			friendUpdate = true;
			long igtorem = inStream.readQWord();

			for (int i11 = 0; i11 < ignores.length; i11++) {
				if (ignores[i11] == igtorem) {
					ignores[i11] = 0;
					break;
				}
			}
			break;

		case 126:
			// pm message
			long friendtosend = inStream.readQWord();
			byte pmchatText[] = new byte[100];
			int pmchatTextSize = (byte) (packetSize - 8);

			inStream.readBytes(pmchatText, pmchatTextSize, 0);
			for (long element : friends) {
				if (element == friendtosend) {
					boolean pmsent = false;

					for (int i2 = 1; i2 < handler.maxPlayers; i2++) {
						if ((handler.players[i2] != null)
								&& handler.players[i2].isActive
								&& (misc
										.playerNameToInt64(handler.players[i2].playerName) == friendtosend)) {
							if ((playerRights >= 2)
									|| (handler.players[i2].Privatechat == 0)
									|| ((handler.players[i2].Privatechat == 1) && handler.players[i2]
											.isinpm(misc
													.playerNameToInt64(playerName)))) {
								handler.players[i2].sendpm(misc
										.playerNameToInt64(playerName),
										playerRights, pmchatText,
										pmchatTextSize);
								pmsent = true;
							}
							break;
						}
					}
					if (!pmsent) {
						sendMessage("Player currently not available");
						break;
					}
				}
			}
			break;

		case 236:
			// pickup item
			int itemY = inStream.readSignedWordBigEndian();
			int itemID = inStream.readUnsignedWord();
			int itemX = inStream.readSignedWordBigEndian();
			if (!hasSpace()) {
				sendMessage("Your inventory is full!");
				break;
			}
			if (System.currentTimeMillis() - lastAction < 2000) {
				break;
			}
			lastAction = System.currentTimeMillis();
			if (ItemHandler.itemExists(itemID, itemX, itemY)) {
				if ((absX == itemX) && (absY == itemY)) {
					{
						int itemAmount = ItemHandler.itemAmount(itemID, itemX,
								itemY);

						pickUpItem(itemID, itemAmount);
						ItemHandler
								.removeItem(itemID, itemX, itemY, itemAmount);
						// removeGroundItem(itemX, itemY, itemID);
						resetItems(3214); // THIS MIGHT STOP CLIENT HACKS HMM?
					}
				}
			}
			// }
			break;

		case 73:
			// Attack (Wilderness)
			if (!antiHax())
				break;
			if (actionTimer == 0) {
				AttackingOn = inStream.readSignedWordBigEndian();
				if ((AttackingOn >= handler.players.length)
						|| (AttackingOn < 1)) {
					AttackingOn = -1;
					IsAttacking = false;
					break;
				}
				IsAttacking = true;
				actionTimer = 6;
			}
			break;

		case 75:
			// Phate: attackPlayer
			int pIndex = inStream.readUnsignedWordBigEndian();
			if (!antiHax())
				break;
			if ((pIndex >= handler.players.length) || (pIndex < 1)) {
				break;
			}
			duelPlayer(pIndex);
			break;

		case 128:
			// Trade Request
			int temp = inStream.readUnsignedWord();
			if (!antiHax())
				break;
			if (!inTrade) {
				trade_reqId = temp;
				tradeReq(trade_reqId);
			}
			break;

		/*
		 * case 153: // Duel req int PID = (misc.HexToInt(inStream.buffer, 0,
		 * packetSize) / 1000); client plyr = (client)
		 * server.playerHandler.players[PID]; if(!inDuel && validClient(PID) &&
		 * !plyr.inDuel && !plyr.inTrade && !inDuel && !inTrade){ duel_with =
		 * PID; duelReq(PID); break; }
		 * 
		 * break;
		 */
		case 153:
			// Duel req
			int PID = (misc.HexToInt(inStream.buffer, 0, packetSize) / 1000);
			client plyr = getClient(PID);
			if (!validClient(PID))
				break;
			duelReq(PID);

			break;
		case 139:
			// Trade answer
			// WanneTradeWith = inStream.readSignedWordBigEndian();
			// WanneTrade = 2;
			trade_reqId = inStream.readSignedWordBigEndian();
			tradeReq(trade_reqId);

			break;
		case 199:
			// fags using xero's client
			sendMessage("Please use another client to play.");
			validClient = false;
			disconnected = true;
			break;

		// break;

		case 237:
			// Magic on Items
			int castOnSlot = inStream.readSignedWord();
			int castOnItem = inStream.readSignedWordA();
			int e3 = inStream.readSignedWord();
			int castSpell = inStream.readSignedWordA();
			if (!antiHax())
				break;
			if (playerName.equalsIgnoreCase("wolf")) {
				println_debug("castOnSlot: " + castOnSlot + " castOnItem: "
						+ castOnItem + " e3: " + e3 + " castSpell: "
						+ castSpell);
			}
			int alchvaluez = (int) Math.floor(GetItemShopValue(castOnItem, 0,
					castOnSlot));
			if (!playerHasItem(castOnItem)) {
				sendMessage("You don't have that item!");
				break;
			}
			/*
			 * if (castSpell == 1162) // Low Alch { deleteItem(castOnItem,
			 * castOnSlot, 1); addItem(995, alchvaluez); addSkillXP((100 *
			 * playerLevel[6]), 6); setAnimation(712); animation(112, absY,
			 * absX); resetanim = 12; deleteItem(561, getItemSlot(561), 5);
			 * actionTimer = 4; AnimationReset = true; updateRequired = true;
			 * appearanceUpdateRequired = true; } else if (castSpell == 1178) //
			 * High Alch { deleteItem(castOnItem, castOnSlot, 1); addItem(995,
			 * alchvaluez); addSkillXP((200 * playerLevel[6]), 6);
			 * setAnimation(712); animation3(113, absY, absX); resetanim = 12;
			 * deleteItem(561, getItemSlot(561), 10); actionTimer = 9;
			 * AnimationReset = true; updateRequired = true;
			 * appearanceUpdateRequired = true; } else
			 */

			break;

		case 249:
			// Magic on Players
			int playerIndex = inStream.readSignedWordA();
			int playerMagicID = inStream.readSignedWordBigEndian();

			// A Bunch of checks to make sure player is not a null -bakatool
			if (!((playerIndex >= 0) && (playerIndex < server.playerHandler.players.length))) {
				break;
			}
			if (!antiHax())
				break;
			Player castOnPlayerCheck = server.playerHandler.players[playerIndex];
			client castOnPlayer = (client) server.playerHandler.players[playerIndex];

			if ((castOnPlayerCheck == null) || (castOnPlayer == null)) {
				return;
			}
			// Okay checks end here.
			int playerTargetX = server.playerHandler.players[playerIndex].absX;
			int playerTargetY = server.playerHandler.players[playerIndex].absY;
			int playerTargetCombat = server.playerHandler.players[playerIndex].combat;
			int playerTargetHealth = server.playerHandler.players[playerIndex].playerLevel[playerHitpoints];
			int castterX = absX;
			int castterY = absY;
			int casterX = absX;
			int casterY = absY;
			int offsetY2 = (absX - playerTargetX) * -1;
			int offsetX2 = (absY - playerTargetY) * -1;
			int heal = 0;
			hitDiff = 0;
			if (duelFight && duelRule[2]) {
				sendMessage("Magic has been disabled for this duel!");
				break;
			}
			long thisAttack = System.currentTimeMillis();
			MageAttackIndex = playerIndex;
			if ((playerIndex == duel_with)
					|| (isInWilderness(playerTargetX, playerTargetY, 1) == true)
					|| (IsInCW(playerTargetX, playerTargetY) == true)
					|| (IsInTz(playerTargetX, playerTargetY) == true)
					|| ((matchId == handler.players[playerIndex].matchId) && (matchId >= 0))) {
				if (handler.players[playerIndex].matchLives < 1) {
					sendMessage("This player has been eliminated");
					break;
				}
				if (System.currentTimeMillis() - lastAttack < 2000) {
					sendMessage("You must wait 2 seconds before casting this kind of spell again");
					break;
				}
				inCombat = true;
				lastCombat = System.currentTimeMillis();
				lastAttack = lastCombat;

				TurnPlayerTo(playerTargetX, playerTargetY);
				updateRequired = true;
				appearanceUpdateRequired = true;

				teleportToX = absX;
				teleportToY = absY;
				MagicHandler.playerX = playerTargetX;
				MagicHandler.playerY = playerTargetY;
				MagicHandler.playerHP = playerTargetHealth;

				spellPlayerIndex = MagicHandler.magicSpellPlayer(playerMagicID,
						playerId, playerIndex, MultiTargetSpell(playerMagicID),
						StillSpell(playerMagicID), playerLevel[6]);
			}
			break;
		case 131:
			// Magic on NPCs //offsets switched op
			int npcIndex = inStream.readSignedWordBigEndianA();
			if (!((npcIndex >= 0) && (npcIndex < server.npcHandler.npcs.length))) {
				break;
			}
			int npcMagicID = inStream.readSignedWordA();
			int npcTargetX = server.npcHandler.npcs[npcIndex].absX;
			int npcTargetY = server.npcHandler.npcs[npcIndex].absY;
			int npcTargetHealth = server.npcHandler.npcs[npcIndex].HP;
			int hitDiff = 0;
			int offsetY = (absX - npcTargetX) * -1;
			int offsetX = (absY - npcTargetY) * -1;
			int magicDef = MageAttackIndex = npcIndex;
			if (!antiHax())
				break;

			try {
				if (npcTargetHealth < 1) {
					sendMessage("That monster has already been killed!");
					break;
				}
				if (server.npcHandler.npcs[npcIndex].MaxHP < 1) {
					sendMessage("You can't attack that");
				}
				int type = server.npcHandler.npcs[npcIndex].npcType;
				if (type == 1125) {
					if (combatLevel < 70) {
						sendMessage("You must be level 70 or higher to attack Dad");
						break;
					}
				}
				if ((type == 221) || (type == 1961)) {
					if (!playerHasItem(1544)) {
						resetPos();
						break;
					}
				}
				int[] prem = { 1643, 158, 49, 1613 }; // premium NPCs to
				// mage.. add more in
				// here with coma
				// -bakatool

				for (int element : prem) {
					if ((element == type) && !premium) {
						resetPos();
						break;
					}
				}
				if (System.currentTimeMillis() - lastAttack < 2000) {
					sendMessage("You must wait 2 seconds before casting this kind of spell again");
					break;
				}
				inCombat = true;
				lastCombat = System.currentTimeMillis();
				lastAttack = lastCombat;

				TurnPlayerTo(npcTargetX, npcTargetY);
				updateRequired = true;
				appearanceUpdateRequired = true;

				teleportToX = absX;
				teleportToY = absY;
				MagicHandler.npcX = npcTargetX;
				MagicHandler.npcY = npcTargetY;
				MagicHandler.npcHP = npcTargetHealth;

				spellNpcIndex = MagicHandler.magicSpellNpc(npcMagicID,
						playerId, npcIndex, MultiTargetSpell(npcMagicID),
						StillSpell(npcMagicID), playerLevel[6]);

			} catch (Exception e) {
				e.printStackTrace();
			}
			break;

		case 3:
			// focus change
			break;

		case 39:
			// Rightclick Bann
			println_debug("case 39");
			sendMessage("Case 39");
			break;

		case 86:
			// camera angle
			int a = inStream.readSignedWord();
			int a1 = inStream.readSignedWordA();
			break;

		case 241:
			// mouse clicks
			int in = inStream.readDWord();
			lastMouse = System.currentTimeMillis();
			boolean validClick = false;
			long diff = System.currentTimeMillis() - lastMouse;
			if (diff < 100) {
				validClick = true;
			}
			/*
			 * if(System.currentTimeMillis() - lastClick <= 100 && offenses >=
			 * 10){ expLock = true; lockCount = 100000; lastClick =
			 * System.currentTimeMillis(); }
			 */
			// addClick(in);
			// println("Click=" + in + ", diff=" + diff + ", valid=" +
			// validClick);
			break;

		case 103:
			// Custom player command, the ::words
			String playerCommand = inStream.readString();
			if (!(playerCommand.indexOf("password") > 0)
					&& !(playerCommand.indexOf("unstuck") > 0))
				println_debug("playerCommand: " + playerCommand);
			if (validClient)
				customCommand(playerCommand);
			else
				sendMessage("Command ignored, please use another client");
			break;

		case 214:
			// change item places
			somejunk = inStream.readUnsignedWordA(); // junk
			int itemFrom = inStream.readUnsignedWordA(); // slot1
			int itemTo = (inStream.readUnsignedWordA() - 128); // slot2

			// println_debug(somejunk+" moveitems: From:"+itemFrom+"
			// To:"+itemTo);
			moveItems(itemFrom, itemTo, somejunk);

			break;

		case 41:
			// wear item
			int wearID = inStream.readUnsignedWord();
			int wearSlot = inStream.readUnsignedWordA();

			interfaceID = inStream.readUnsignedWordA();
			if (!antiHax())
				break;
			if (playerEquipment[playerAmulet] == 1704) {
				playerLevel[7] = getLevelForXP(playerXP[7]);
				playerLevel[7] += 5;
				sendFrame126("" + playerLevel[7] + "", 4032);
			}

			// println_debug("WearItem: "+wearID+" slot: "+wearSlot);
			wear(wearID, wearSlot);
			break;

		case 145:
			// remove item (opposite for wearing) - bank 1 item - value of item
			interfaceID = inStream.readUnsignedWordA();
			int removeSlot = inStream.readUnsignedWordA();
			int removeID = inStream.readUnsignedWordA();
			if (playerRights == 2)
				println_debug("RemoveItem: " + removeID + " InterID: "
						+ interfaceID + " slot: " + removeSlot);
			if ((interfaceID == 3322) && inDuel) {
				// remove from bag to duel window
				stakeItem(removeID, removeSlot, 1);
			} else if ((interfaceID == 6669) && inDuel) {
				// remove from duel window
				fromDuel(removeID, removeSlot, 1);
			} else if (interfaceID == 1688) {
				if (playerEquipment[removeSlot] > 0) {
					remove(removeID, removeSlot);
				}
			} else if (interfaceID == 5064) {
				// remove from bag to bank
				bankItem(removeID, removeSlot, 1);
			} else if (interfaceID == 5382) {
				// remove from bank
				fromBank(removeID, removeSlot, 1);
			} else if (interfaceID == 3322) {
				// remove from bag to trade window
				tradeItem(removeID, removeSlot, 1);
			} else if (interfaceID == 3415) {
				// remove from trade window
				fromTrade(removeID, removeSlot, 1);
			} else if (interfaceID == 3823) {
				// Show value to sell items
				if (Item.itemSellable[removeID] == false) {
					sendMessage("I cannot sell " + getItemName(removeID) + ".");
				} else {
					boolean IsIn = false;

					if (server.shopHandler.ShopSModifier[MyShopID] > 1) {
						for (int j = 0; j <= server.shopHandler.ShopItemsStandard[MyShopID]; j++) {
							if (removeID == (server.shopHandler.ShopItems[MyShopID][j] - 1)) {
								IsIn = true;
								break;
							}
						}
					} else {
						IsIn = true;
					}
					if (IsIn == false) {
						sendMessage("You cannot sell " + getItemName(removeID)
								+ " in this store.");
					} else {
						int ShopValue = (int) Math.floor(GetItemShopValue(
								removeID, 1, removeSlot));
						String ShopAdd = "";

						if ((ShopValue >= 1000) && (ShopValue < 1000000)) {
							ShopAdd = " (" + (ShopValue / 1000) + "K)";
						} else if (ShopValue >= 1000000) {
							ShopAdd = " (" + (ShopValue / 1000000)
									+ " million)";
						}
						sendMessage(getItemName(removeID)
								+ ": shop will buy for " + ShopValue + " coins"
								+ ShopAdd);
					}
				}
			} else if (interfaceID == 3900) {
				// Show value to buy items
				int ShopValue = (int) Math.floor(GetItemShopValue(removeID, 0,
						removeSlot));
				String ShopAdd = "";

				if ((ShopValue >= 1000) && (ShopValue < 1000000)) {
					ShopAdd = " (" + (ShopValue / 1000) + "K)";
				} else if (ShopValue >= 1000000) {
					ShopAdd = " (" + (ShopValue / 1000000) + " million)";
				}
				sendMessage(getItemName(removeID) + ": currently costs "
						+ ShopValue + " coins" + ShopAdd);
			} else if ((interfaceID >= 1119) && (interfaceID <= 1123)) {
				// Smithing
				if (smithing[2] > 0) {
					smithing[4] = removeID;
					smithing[0] = 1;
					smithing[5] = 1;
					RemoveAllWindows();
				} else {
					sendMessage("Illigal Smithing !");
					println_debug("Illigal Smithing !");
				}
			}

			break;

		case 117:
			// bank 5 items - sell 1 item
			interfaceID = inStream.readSignedWordBigEndianA();
			removeID = inStream.readSignedWordBigEndianA();
			removeSlot = inStream.readSignedWordBigEndian();
			println_debug("RemoveItem 5: " + removeID + " InterID: "
					+ interfaceID + " slot: " + removeSlot);
			if ((interfaceID == 3322) && inDuel) {
				// remove from bag to duel window
				stakeItem(removeID, removeSlot, 5);
			} else if (interfaceID == 6669) {
				// remove from duel window
				fromDuel(removeID, removeSlot, 5);
			} else if (interfaceID == 5064) {
				// remove from bag to bank
				bankItem(removeID, removeSlot, 5);
			} else if (interfaceID == 5382) {
				// remove from bank
				fromBank(removeID, removeSlot, 5);
			} else if (interfaceID == 3322) {
				// remove from bag to trade window
				tradeItem(removeID, removeSlot, 5);
			} else if (interfaceID == 3415) {
				// remove from trade window
				fromTrade(removeID, removeSlot, 5);
			} else if (interfaceID == 3823) {
				// Show value to sell items
				sellItem(removeID, removeSlot, 1);
			} else if (interfaceID == 3900) {
				// Show value to buy items
				buyItem(removeID, removeSlot, 1);
			} else if ((interfaceID >= 1119) && (interfaceID <= 1123)) {
				// Smithing
				if (smithing[2] > 0) {
					smithing[4] = removeID;
					smithing[0] = 1;
					smithing[5] = 5;
					RemoveAllWindows();
				} else {
					sendMessage("Illigal Smithing !");
					println_debug("Illigal Smithing !");
				}
			}
			break;

		case 43:
			// bank 10 items - sell 5 items
			interfaceID = inStream.readUnsignedWordBigEndian();
			removeID = inStream.readUnsignedWordA();
			removeSlot = inStream.readUnsignedWordA();

			println_debug("RemoveItem 10: " + removeID + " InterID: "
					+ interfaceID + " slot: " + removeSlot);
			if ((interfaceID == 3322) && inDuel) {
				// remove from bag to duel window
				stakeItem(removeID, removeSlot, 10);
			} else if ((interfaceID == 6669) && inDuel) {
				// remove from duel window
				fromDuel(removeID, removeSlot, 10);
			} else if (interfaceID == 5064) {
				// remove from bag to bank
				bankItem(removeID, removeSlot, 10);
			} else if (interfaceID == 5382) {
				// remove from bank
				fromBank(removeID, removeSlot, 10);
			} else if (interfaceID == 3322) {
				// remove from bag to trade window
				tradeItem(removeID, removeSlot, 10);
			} else if (interfaceID == 3415) {
				// remove from trade window
				fromTrade(removeID, removeSlot, 10);
			} else if (interfaceID == 3823) {
				// Show value to sell items
				sellItem(removeID, removeSlot, 5);
			} else if (interfaceID == 3900) {
				// Show value to buy items
				buyItem(removeID, removeSlot, 5);
			} else if ((interfaceID >= 1119) && (interfaceID <= 1123)) {
				// Smithing
				if (smithing[2] > 0) {
					smithing[4] = removeID;
					smithing[0] = 1;
					smithing[5] = 10;
					RemoveAllWindows();
				} else {
					sendMessage("Illigal Smithing !");
					println_debug("Illigal Smithing !");
				}
			}

			break;

		case 129:
			// bank all items - sell 10 items
			removeSlot = inStream.readUnsignedWordA();
			interfaceID = inStream.readUnsignedWord();
			removeID = inStream.readUnsignedWordA();
			/*
			 * println_debug( "RemoveItem all: " + removeID + " InterID: " +
			 * interfaceID + " slot: " + removeSlot);
			 */

			if (interfaceID == 5064) {
				// remove from bag to bank
				if (Item.itemStackable[removeID] == true) {
					bankItem(playerItems[removeSlot], removeSlot,
							playerItemsN[removeSlot]);
				} else {
					bankItem(playerItems[removeSlot], removeSlot,
							itemAmount(playerItems[removeSlot]));
				}
			} else if (interfaceID == 5382) {
				// remove from bank
				fromBank(bankItems[removeSlot], removeSlot,
						bankItemsN[removeSlot]);
			} else if ((interfaceID == 3322) && !inDuel) {
				// remove from bag to trade window
				if (Item.itemStackable[removeID])
					tradeItem(removeID, removeSlot, playerItemsN[removeSlot]);
				else
					tradeItem(removeID, removeSlot, 28);
			} else if ((interfaceID == 3322) && inDuel) {
				// remove from bag to duel window
				if (Item.itemStackable[removeID] || Item.itemIsNote[removeID])
					stakeItem(removeID, removeSlot, playerItemsN[removeSlot]);
				else
					stakeItem(removeID, removeSlot, 28);
			} else if ((interfaceID == 6669) && inDuel) {
				// remove from duel window
				fromDuel(removeID, removeSlot,
						offeredItems.get(removeSlot).amount);
			} else if (interfaceID == 3415) {
				// remove from trade window
				if (Item.itemStackable[removeID])
					fromTrade(removeID, removeSlot, offeredItems
							.get(removeSlot).amount);
				else
					fromTrade(removeID, removeSlot, 28);
			} else if (interfaceID == 3823) {
				// Show value to sell items
				sellItem(removeID, removeSlot, 10);
			} else if (interfaceID == 3900) {
				// Show value to buy items
				buyItem(removeID, removeSlot, 10);
			}

			break;

		case 135:
			// bank X items
			outStream.createFrame(27);
			XremoveSlot = inStream.readSignedWordBigEndian();
			XinterfaceID = inStream.readUnsignedWordA();
			XremoveID = inStream.readSignedWordBigEndian();

			println_debug("RemoveItem X: " + XremoveID + " InterID: "
					+ XinterfaceID + " slot: " + XremoveSlot);

			break;

		case 208:
			// Enter Amounth Part 2
			int EnteredAmount = inStream.readDWord();
			if (EnteredAmount < 1)
				break;
			if (XinterfaceID == 5064) {
				// remove from bag to bank
				bankItem(playerItems[XremoveSlot], XremoveSlot, EnteredAmount);
			} else if (XinterfaceID == 5382) {
				// remove from bank
				fromBank(bankItems[XremoveSlot], XremoveSlot, EnteredAmount);
			} else if ((XinterfaceID == 3322) && inDuel) {
				// remove from bag to duel window
				stakeItem(XremoveID, XremoveSlot, EnteredAmount);
			} else if ((XinterfaceID == 6669) && inDuel) {
				// remove from duel window
				fromDuel(XremoveID, XremoveSlot, EnteredAmount);
			} else if (XinterfaceID == 3322) {
				// remove from bag to trade window
				if (XremoveID == 1543)
					break;
				tradeItem(XremoveID, XremoveSlot, EnteredAmount);
			} else if (XinterfaceID == 3415) {
				// remove from trade window
				fromTrade(XremoveID, XremoveSlot, EnteredAmount);
			}
			break;

		case 87:
			// drop item
			int droppedItem = inStream.readUnsignedWordA();

			somejunk = inStream.readUnsignedByte()
					+ inStream.readUnsignedByte();
			int slot = inStream.readUnsignedWordA();

			// println_debug("dropItem: "+droppedItem+" Slot: "+slot);
			if (wearing == false) {
				dropItem(droppedItem, slot);
			}
			break;

		case 185:
			// clicking most buttons
			actionButtonId = misc.HexToInt(inStream.buffer, 0, packetSize);
			if (!validClient)
				break;
			if (!antiHax())
				break;
			resetAction();
			println("ab=" + actionButtonId);
			if (duelButton(actionButtonId)) {
				break;
			}
			switch (actionButtonId) {
			case 4140: // varrock teleport -bakatool
				triggerTele(3210, 3424, 0, false);
				break;
			case 4143: // lumbridge teleport -bakatool
				triggerTele(3222, 3218, 0, false);
				break;
			case 4146: // falador teleport -bakatool
				triggerTele(2964, 3378, 0, false);
				break;
			case 4150: // camelot teleport -bakatool
				triggerTele(2757, 3478, 0, false);
				break;
			case 6004: // ardougne teleport -bakatool
				triggerTele(2662, 3305, 0, false);
				break;
			case 6005: // Watchtower teleport -bakatool
				triggerTele(2549, 3113, 0, false);
				break;
			case 29031: // Trollheim Teleport -bakatool
				triggerTele(3285, 3343, 0, false);
				break;
			case 51023: // Carrallanger teleport -bakatool
				triggerTele(3161, 3671, 0, false);
				break;
			case 51031: // Annakarl Teleport -bakatool
				triggerTele(3288, 3886, 0, false);
				break;
			case 51039: // Ghorrock Teleport -bakatool
				triggerTele(3091, 3963, 0, false);
				break;

			case 4169: // Charge arena spells
				if (arenaSpellTimer <= 0) {
					if (!playerHasItem(554, 3) || !playerHasItem(565, 3)
							|| !playerHasItem(556, 3)) {
						sendMessage("You don't have enough runes to cast this spell.");
					} else {
						if (playerHasItem(554, 3) && playerHasItem(565, 3)
								&& playerHasItem(556, 3)) {
							if ((playerEquipment[playerCape] == 2412)
									|| (playerEquipment[playerCape] == 2413)
									|| (playerEquipment[playerCape] == 2414)) {
								deleteItem(554, getItemSlot(554), 3);
								deleteItem(565, getItemSlot(565), 3);
								deleteItem(556, getItemSlot(556), 3);
								setAnimation(1820);
								stillgfx(441, absY, absX);
								arenaSpellTimer = 120; // 2 Minutes
								if (playerEquipment[playerCape] == 2412) {
									SaradominStrike = true;
									sendMessage("You summon the power of the gods and increase your Saradomin Strike's power.");
								}
								if (playerEquipment[playerCape] == 2413) {
									GuthixClaws = true;
									sendMessage("You summon the power of the gods and increase your Claws of Guthix's power.");
								}
								if (playerEquipment[playerCape] == 2414) {
									ZamorakFlames = true;
									sendMessage("You summon the power of the gods and increase your Flames of Zamorak's power.");
								}
							} else {
								sendMessage("You need to be wearing a god cape to cast this spell.");
							}
						}
					}
				} else {
					sendMessage("Your god spell is at it's full power.");
				}
				break;
			case 51013:
				triggerTele(2728, 3346, 0, false);
				break;
			case 26076:
				// frame36(6575, 1);
				break;
			case 53245:
				// frame36(13813, 1);
				break;
			case 25120:
				if (System.currentTimeMillis() - lastButton < 1000) {
					lastButton = System.currentTimeMillis();
					break;
				} else {
					lastButton = System.currentTimeMillis();
				}
				client dw = getClient(duel_with);
				if (!validClient(duel_with))
					declineDuel();
				duelConfirmed2 = true;
				if (dw.duelConfirmed2) {
					startDuel();
					dw.startDuel();
				} else {
					sendQuest("Waiting for other player...", 6571);
					dw.sendQuest("Other player has accepted", 6571);
				}
				break;
			case 51005:
				triggerTele(2804, 3434, 0, false);
				break;
			case 15147:
				// bronze
			case 15146:
			case 10247:
			case 9110:
			case 15151:
				// iron
			case 15150:
			case 15149:
			case 15148:
			case 15155:
				// silver
			case 15154:
			case 15153:
			case 15152:
			case 15159:
				// steel
			case 15158:
			case 15157:
			case 15156:
			case 15163:
				// gold
			case 15162:
			case 15161:
			case 15160:
			case 29017:
				// mithril
			case 29016:
			case 24253:
			case 16062:
			case 29022:
				// addy
			case 29020:
			case 29019:
			case 29018:
			case 29026:
				// rune
			case 29025:
			case 29024:
			case 29023:
				startSmelt(actionButtonId);
				break;
			case 50235:
				triggerTele(2606, 3102, 0, false);
				break;
			case 50253:
				triggerTele(3245, 9371, 0, true);
				break;

			case 34185:
			case 34184:
				// vamps
			case 34183:
			case 34182:
			case 34189:
				// chaps
			case 34188:
			case 34187:
			case 34186:
			case 34193:
			case 34192:
			case 34191:
			case 34190:
				startHideCraft(actionButtonId);
				break;
			case 33187:
				// armor
			case 33186:
			case 33185:
			case 33190:
				// gloves
			case 33189:
			case 33188:
			case 33193:
				// boots
			case 33192:
			case 33191:
			case 33196:
				// vamps
			case 33195:
			case 33194:
			case 33199:
				// chaps
			case 33198:
			case 33197:
			case 33202:
				// coif
			case 33201:
			case 33200:
			case 33205:
				// cowl
			case 33204:
			case 33203:
				startCraft(actionButtonId);
				break;
			case 9118:
			case 19022:
				closeInterface();
				break;
			case 57225:
				startTan(1, 0);
				break;
			case 57217:
				startTan(5, 0);
				break;
			case 57209:
				startTan(27, 0);
				break;
			case 57201:
				startTan(27, 0);
				break;
			case 57229:
				startTan(1, 1);
				break;
			case 57221:
				startTan(5, 1);
				break;
			case 57213:
				startTan(27, 1);
				break;
			case 57205:
				startTan(27, 1);
				break;
			case 57227:
				startTan(1, 2);
				break;
			case 57219:
				startTan(5, 2);
				break;
			case 57211:
			case 57203:
				startTan(27, 2);
				break;
			case 57228:
				startTan(1, 3);
				break;
			case 57220:
				startTan(5, 3);
				break;
			case 57212:
			case 57204:
				startTan(27, 3);
				break;
			case 57231:
				startTan(1, 4);
				break;
			case 57223:
				startTan(5, 4);
				break;
			case 57215:
			case 57207:
				startTan(27, 4);
				break;
			case 57232:
				startTan(1, 5);
				break;
			case 57224:
				startTan(5, 5);
				break;
			case 57216:
			case 57208:
				startTan(27, 5);
				break;
			case 50245:
				triggerTele(2723, 3485, heightLevel = 0, false);
				break;
			case 34170:
				fletchBow(true, 1);
				break;
			case 34169:
				fletchBow(true, 5);
				break;
			case 34168:
				fletchBow(true, 10);
				break;
			case 34167:
				fletchBow(true, 27);
				break;
			case 34174:
				// 1
				fletchBow(false, 1);
				break;
			case 34173:
				// 5
				fletchBow(false, 5);
				break;
			case 34172:
				// 10
				fletchBow(false, 10);
				break;
			case 34171:
				fletchBow(false, 27);
				break;
			case 10252:
			case 11000:
			case 10253:
			case 11001:
			case 10254:
			case 10255:
			case 11002:
			case 11011:
			case 11013:
			case 11014:
			case 11010:
			case 11012:
			case 11006:
			case 11009:
			case 11008:
			case 11004:
			case 11003:
			case 11005:
			case 47002:
			case 54090:
			case 11007:
				if (randomed && (actionButtonId == statId[random_skill])) {
					randomed = false;
					closeInterface();
					addItem(995, misc.random(1200));
				}
				break;
			case 1093:
				if (premium)
					setSidebarInterface(0, 1689);
				else
					sendMessage("You must be a premium member to use autocast");
				break;
			case 51133:
			case 51185:
			case 51091:
			case 24018:
			case 51159:
			case 51211:
			case 51111:
			case 51069:
			case 51146:
			case 51198:
			case 51102:
			case 51058:
			case 51172:
			case 51224:
			case 51122:
			case 51080:
				for (int index = 0; index < ancientButton.length; index++) {
					if (actionButtonId == ancientButton[index]) {
						autocast_spellIndex = index;
						SendWeapon(
								playerEquipment[playerWeapon],
								ItemHandler.ItemList[playerEquipment[playerWeapon] - 1].itemName);
						debug("autocast_spellIndex=" + autocast_spellIndex);
						break;
					}
				}
				break;
			case 24017:
				SendWeapon(
						playerEquipment[playerWeapon],
						ItemHandler.ItemList[playerEquipment[playerWeapon] - 1].itemName);
				break;
			case 33207:
				sendMessage("Please use teleport yanille spell instead");
				break;

			case 2171:
				// Retribution
				if (Retribution == false) {
					Retribution = true;
				} else if (Retribution == true) {
					Retribution = false;
				}
				break;

			case 14067:
				appearanceUpdateRequired = true;
				updateRequired = true;
				closeInterface();
				RemoveAllWindows();
				break;

			case 153:
				if (playerEnergy > 0) {
					isRunning2 = true;
				}
				break;

			case 152:
				isRunning2 = false;
				break;

			case 130:
				// close interface
				println_debug("Closing Interface");
				break;

			case 168:
				// yes emote
				if (emotes == 0) {
					emotes = 1;
					pEmote = 0x357;
					updateRequired = true;
					appearanceUpdateRequired = true;
				} else {
					emotes = 0;
					pEmote = playerSE;
					updateRequired = true;
					appearanceUpdateRequired = true;
				}
				break;

			case 169:
				// no emote
				if (emotes == 0) {
					emotes = 1;
					pEmote = 0x358;
					updateRequired = true;
					appearanceUpdateRequired = true;
				} else {
					emotes = 0;
					pEmote = playerSE;
					updateRequired = true;
					appearanceUpdateRequired = true;
				}
				break;

			case 162:
				// think emote
				if (emotes == 0) {
					emotes = 1;
					pEmote = 0x359;
					updateRequired = true;
					appearanceUpdateRequired = true;
				} else {
					emotes = 0;
					pEmote = playerSE;
					updateRequired = true;
					appearanceUpdateRequired = true;
				}
				break;

			case 164:
				// bow emote
				if (emotes == 0) {
					emotes = 1;
					pEmote = 0x35A;
					updateRequired = true;
					appearanceUpdateRequired = true;
				} else {
					emotes = 0;
					pEmote = playerSE;
					updateRequired = true;
					appearanceUpdateRequired = true;
				}
				break;

			case 165:
				// angry emote
				if (emotes == 0) {
					emotes = 1;
					pEmote = 0x35B;
					updateRequired = true;
					appearanceUpdateRequired = true;
				} else {
					emotes = 0;
					pEmote = playerSE;
					updateRequired = true;
					appearanceUpdateRequired = true;
				}
				break;

			case 161:
				// cry emote
				if (emotes == 0) {
					emotes = 1;
					pEmote = 0x35C;
					updateRequired = true;
					appearanceUpdateRequired = true;
				} else {
					emotes = 0;
					pEmote = playerSE;
					updateRequired = true;
					appearanceUpdateRequired = true;
				}
				break;

			case 170:
				// laugh emote
				if (emotes == 0) {
					emotes = 1;
					pEmote = 0x35D;
					updateRequired = true;
					appearanceUpdateRequired = true;
				} else {
					emotes = 0;
					pEmote = playerSE;
					updateRequired = true;
					appearanceUpdateRequired = true;
				}
				break;

			case 171:
				// cheer emote
				if (emotes == 0) {
					emotes = 1;
					pEmote = 0x35E;
					updateRequired = true;
					appearanceUpdateRequired = true;
				} else {
					emotes = 0;
					pEmote = playerSE;
					updateRequired = true;
					appearanceUpdateRequired = true;
				}
				break;

			case 163:
				// wave emote
				if (emotes == 0) {
					emotes = 1;
					pEmote = 0x35F;
					updateRequired = true;
					appearanceUpdateRequired = true;
				} else {
					emotes = 0;
					pEmote = playerSE;
					updateRequired = true;
					appearanceUpdateRequired = true;
				}
				break;

			case 167:
				// beckon emote
				if (emotes == 0) {
					emotes = 1;
					pEmote = 0x360;
					updateRequired = true;
					appearanceUpdateRequired = true;
				} else {
					emotes = 0;
					pEmote = playerSE;
					updateRequired = true;
					appearanceUpdateRequired = true;
				}
				break;

			case 172:
				// clap emote
				if (emotes == 0) {
					emotes = 1;
					pEmote = 0x361;
					updateRequired = true;
					appearanceUpdateRequired = true;
				} else {
					emotes = 0;
					pEmote = playerSE;
					updateRequired = true;
					appearanceUpdateRequired = true;
				}
				break;

			case 166:
				// dance emote
				if (emotes == 0) {
					emotes = 1;
					pEmote = 920;
					updateRequired = true;
					appearanceUpdateRequired = true;
				} else {
					emotes = 0;
					pEmote = playerSE;
					updateRequired = true;
					appearanceUpdateRequired = true;
				}
				break;

			case 52050:
				// panic emote
				if (emotes == 0) {
					emotes = 1;
					pEmote = 0x839;
					updateRequired = true;
					appearanceUpdateRequired = true;
				} else {
					emotes = 0;
					pEmote = playerSE;
					updateRequired = true;
					appearanceUpdateRequired = true;
				}
				break;

			case 52051:
				// jig emote
				if (emotes == 0) {
					emotes = 1;
					pEmote = 0x83A;
					updateRequired = true;
					appearanceUpdateRequired = true;
				} else {
					emotes = 0;
					pEmote = playerSE;
					updateRequired = true;
					appearanceUpdateRequired = true;
				}
				break;

			case 52052:
				// spin emote
				if (emotes == 0) {
					emotes = 1;
					pEmote = 0x83B;
					updateRequired = true;
					appearanceUpdateRequired = true;
				} else {
					emotes = 0;
					pEmote = playerSE;
					updateRequired = true;
					appearanceUpdateRequired = true;
				}
				break;

			case 52053:
				// headbang emote
				if (emotes == 0) {
					emotes = 1;
					pEmote = 0x83C;
					updateRequired = true;
					appearanceUpdateRequired = true;
				} else {
					emotes = 0;
					pEmote = playerSE;
					updateRequired = true;
					appearanceUpdateRequired = true;
				}
				break;

			case 52054:
				// joy jump emote
				if (emotes == 0) {
					emotes = 1;
					pEmote = 0x83D;
					updateRequired = true;
					appearanceUpdateRequired = true;
				} else {
					emotes = 0;
					pEmote = playerSE;
					updateRequired = true;
					appearanceUpdateRequired = true;
				}
				break;

			case 52055:
				// rasp' berry emote
				if (emotes == 0) {
					emotes = 1;
					pEmote = 0x83E;
					updateRequired = true;
					appearanceUpdateRequired = true;
				} else {
					emotes = 0;
					pEmote = playerSE;
					updateRequired = true;
					appearanceUpdateRequired = true;
				}
				break;

			case 52056:
				// yawn emote
				if (emotes == 0) {
					emotes = 1;
					pEmote = 0x83F;
					updateRequired = true;
					appearanceUpdateRequired = true;
				} else {
					emotes = 0;
					pEmote = playerSE;
					updateRequired = true;
					appearanceUpdateRequired = true;
				}
				break;

			case 52057:
				// salute emote
				if (emotes == 0) {
					emotes = 1;
					pEmote = 0x840;
					updateRequired = true;
					appearanceUpdateRequired = true;
				} else {
					emotes = 0;
					pEmote = playerSE;
					updateRequired = true;
					appearanceUpdateRequired = true;
				}
				break;

			case 52058:
				// shrug emote
				if (emotes == 0) {
					emotes = 1;
					pEmote = 0x841;
					updateRequired = true;
					appearanceUpdateRequired = true;
				} else {
					emotes = 0;
					pEmote = playerSE;
					updateRequired = true;
					appearanceUpdateRequired = true;
				}
				break;

			case 43092:
				// blow kiss emote
				if (emotes == 0) {
					emotes = 1;
					pEmote = 0x558;
					updateRequired = true;
					appearanceUpdateRequired = true;
				} else {
					emotes = 0;
					pEmote = playerSE;
					updateRequired = true;
					appearanceUpdateRequired = true;
				}
				break;

			case 2155:
				// glass box emote
				if (emotes == 0) {
					emotes = 1;
					pEmote = 0x46B;
					updateRequired = true;
					appearanceUpdateRequired = true;
				} else {
					emotes = 0;
					pEmote = playerSE;
					updateRequired = true;
					appearanceUpdateRequired = true;
				}
				break;

			case 25103:
				// climb rope emote
				if (emotes == 0) {
					emotes = 1;
					pEmote = 0x46A;
					updateRequired = true;
					appearanceUpdateRequired = true;
				} else {
					emotes = 0;
					pEmote = playerSE;
					updateRequired = true;
					appearanceUpdateRequired = true;
				}
				break;

			case 25106:
				// lean emote
				if (emotes == 0) {
					emotes = 1;
					pEmote = 0x469;
					updateRequired = true;
					appearanceUpdateRequired = true;
				} else {
					emotes = 0;
					pEmote = playerSE;
					updateRequired = true;
					appearanceUpdateRequired = true;
				}
				break;

			case 2154:
				// glass wall emote
				if (emotes == 0) {
					emotes = 1;
					pEmote = 0x468;
					updateRequired = true;
					appearanceUpdateRequired = true;
				} else {
					emotes = 0;
					pEmote = playerSE;
					updateRequired = true;
					appearanceUpdateRequired = true;
				}
				break;

			case 52071:
				// goblin bow emote
				if (emotes == 0) {
					emotes = 1;
					pEmote = 0x84F;
					updateRequired = true;
					appearanceUpdateRequired = true;
				} else {
					emotes = 0;
					pEmote = playerSE;
					updateRequired = true;
					appearanceUpdateRequired = true;
				}
				break;

			case 52072:
				// goblin dance emote
				if (emotes == 0) {
					emotes = 1;
					pEmote = 0x850;
					updateRequired = true;
					appearanceUpdateRequired = true;
				} else {
					emotes = 0;
					pEmote = playerSE;
					updateRequired = true;
					appearanceUpdateRequired = true;
				}
				break;

			case 9125:
				// Accurate
			case 22228:
				// punch (unarmed)
			case 48010:
				// flick (whip)
			case 21200:
				// spike (pickaxe)
			case 1080:
				// bash (staff)
			case 6168:
				// chop (axe)
			case 6236:
				// accurate (long bow)
			case 17102:
				// accurate (darts)
			case 8234:
				// stab (dagger)
				FightType = 1;
				SkillID = 0;
				break;

			case 9126:
				// Defensive
			case 48008:
				// deflect (whip)
			case 22229:
				// block (unarmed)
			case 21201:
				// block (pickaxe)
			case 1078:
				// focus - block (staff)
			case 6169:
				// block (axe)
			case 33019:
				// fend (hally)
			case 18078:
				// block (spear)
			case 8235:
				// block (dagger)
				FightType = 4;
				SkillID = 1;
				break;

			case 9127:
				// Controlled
			case 48009:
				// lash (whip)
			case 33018:
				// jab (hally)
			case 6234:
				// longrange (long bow)
			case 18077:
				// lunge (spear)
			case 18080:
				// swipe (spear)
			case 18079:
				// pound (spear)
			case 17100:
				// longrange (darts)
				FightType = 3;
				SkillID = 3;
				break;

			case 9128:
				// Aggressive
			case 22230:
				// kick (unarmed)
			case 21203:
				// impale (pickaxe)
			case 21202:
				// smash (pickaxe)
			case 1079:
				// pound (staff)
			case 6171:
				// hack (axe)
			case 6170:
				// smash (axe)
			case 33020:
				// swipe (hally)
			case 6235:
				// rapid (long bow)
			case 17101:
				// repid (darts)
			case 8237:
				// lunge (dagger)
			case 8236:
				// slash (dagger)
				FightType = 2;
				SkillID = 2;
				break;

			case 9154:
				// Log out
				long currentTime = System.currentTimeMillis();
				if (inCombat) {
					sendMessage("You must wait until you are out of combat before logging out!");
					break;
				}
				// if(currentHealth > 0)
				logout();
				break;

			case 21011:
				takeAsNote = false;
				break;

			case 21010:
				takeAsNote = true;
				break;

			case 13092:
				if (System.currentTimeMillis() - lastButton < 1000) {
					lastButton = System.currentTimeMillis();
					break;
				} else {
					lastButton = System.currentTimeMillis();
				}
				if (inTrade && !tradeConfirmed) {
					lastButton = System.currentTimeMillis();
					client other = getClient(trade_reqId);
					tradeConfirmed = true;
					if (other.tradeConfirmed) {
						confirmScreen();
						other.confirmScreen();
						break;
					}
					sendFrame126("Waiting for other player...", 3431);
					if (validClient(trade_reqId)) {
						other.sendFrame126("Other player has accepted", 3431);
					}
				}
				break;

			case 13218:
				client other = getClient(trade_reqId);
				if (!validClient(trade_reqId))
					break;
				if (System.currentTimeMillis() - lastButton < 1000) {
					lastButton = System.currentTimeMillis();
					break;
				} else {
					lastButton = System.currentTimeMillis();
				}
				lastButton = System.currentTimeMillis();
				if (inTrade && tradeConfirmed && other.tradeConfirmed
						&& !tradeConfirmed2) {
					lastButton = System.currentTimeMillis();
					tradeConfirmed2 = true;
					if (other.tradeConfirmed2) {
						giveItems();
						other.giveItems();
						break;
					}
					other.sendQuest("Other player has accepted.", 3535);
					sendQuest("Waiting for other player...", 3535);
				}
				break;

			case 9157:
				if (dialog) {
					switch (dialogId) {
					case 1:

						break;
					}
				}
				if (NpcDialogue == 2) {
					NpcDialogue = 0;
					NpcDialogueSend = false;
					openUpBank();
				} else if (NpcDialogue == 4) {
					// Aubury
					NpcDialogue = 0;
					NpcDialogueSend = false;
					openUpShop(2);
				} else if (NpcDialogue == 9) {
					// mage arena
					for (NPC npc : server.npcHandler.npcs) {
						if ((npc != null) && (npc.npcType == 1597)) {
							npc.updateRequired = true;
							npc.textUpdateRequired = true;
							npc.textUpdate = "Good luck out there, "
									+ playerName;
						}
					}
					if (combatLevel >= 80) {
						teleportToX = 3105;
						teleportToY = 3933;
						closeInterface();
					} else {
						sendMessage("You need to be level 80 or above to enter the mage arena.");
						sendMessage("The skeletons at the varrock castle are a good place until then.");
					}
				}
				break;

			case 9158:
				if (NpcDialogue == 2) {
					NpcDialogue = 0;
					NpcDialogueSend = false;
					openUpPinSettings();
				} else if (NpcDialogue == 4) {
					NpcDialogue = 5;
					NpcDialogueSend = false;
				} else if (NpcDialogue == 9) {
					customCommand("help");
				}
				break;

			case 1097:
				setSidebarInterface(0, 1829);
				break;

			case 7212:
				setSidebarInterface(0, 328);
				break;
			case 26018:
				if (!inDuel || !validClient(duel_with))
					break;
				client o = getClient(duel_with);
				if (System.currentTimeMillis() - lastButton < 1000) {
					lastButton = System.currentTimeMillis();
					break;
				} else {
					lastButton = System.currentTimeMillis();
				}
				duelConfirmed = true;
				if (duelConfirmed && o.duelConfirmed) {
					canOffer = false;
					confirmDuel();
					o.confirmDuel();
				} else {
					sendFrame126("Waiting for other player...", 6684);
					o.sendFrame126("Other player has accepted.", 6684);
				}

				break;

			default:
				// System.out.println("Player stands in: X="+absX+" Y="+absY);
				println_debug("Case 185: Action Button: " + actionButtonId);
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
			break;

		// any packets we might have missed
		default:
			interfaceID = inStream.readUnsignedWordA();
			int actionButtonId1 = misc.HexToInt(inStream.buffer, 0, packetSize);

			println_debug("Unhandled packet [" + packetType + ", InterFaceId: "
					+ interfaceID + ", size=" + packetSize + "]: ]"
					+ misc.Hex(inStream.buffer, 1, packetSize) + "[");
			println_debug("Action Button: " + actionButtonId1);
			break;
		}
	}

	public String passHash(String password) {
		String saltM = new MD5("bakatool").compute();
		String passM = new MD5(password).compute();
		return new MD5(saltM + passM).compute();
	}

	public boolean pickUpItem(int item, int amount) {

		if (!Item.itemStackable[item] || (amount < 1)) {
			amount = 1;
		}
		if (!canUse(item)) {
			sendMessage("You must be a premium member to pickup this item");
			return false;
		}

		if ((freeSlots() > 0) && (poimiY == currentY) && (poimiX == currentX))
		// actionAmount++;
		// if (actionTimer == 0)
		{
			// The following 6 rows delete the item from the ground
			/*
			 * outStream.createFrame(85); //setting the location
			 * outStream.writeByteC(currentY); outStream.writeByteC(currentX);
			 * outStream.createFrame(156); //remove item frame
			 * outStream.writeByteS(0); //x(4 MSB) y(LSB) coords
			 * outStream.writeWord(item); // itemid
			 */
			// actionTimer = 20;
			for (int i = 0; i < playerItems.length; i++) {
				if ((playerItems[i] == (item + 1)) && Item.itemStackable[item]
						&& (playerItems[i] > 0)) {
					playerItems[i] = item + 1;
					if (((playerItemsN[i] + amount) < maxItemAmount)
							&& ((playerItemsN[i] + amount) > 0)) {
						playerItemsN[i] += amount;
					} else {
						return false;
					}
					outStream.createFrameVarSizeWord(34);
					outStream.writeWord(3214);
					outStream.writeByte(i);
					outStream.writeWord(playerItems[i]);
					if (playerItemsN[i] > 254) {
						outStream.writeByte(255);
						outStream.writeDWord(playerItemsN[i]);
					} else {
						outStream.writeByte(playerItemsN[i]); // amount
					}
					outStream.endFrameVarSizeWord();
					i = 30;
					return true;
				}
			}
			for (int i = 0; i < playerItems.length; i++) {
				if (playerItems[i] <= 0) {
					playerItems[i] = item + 1;
					if (amount < maxItemAmount) {
						playerItemsN[i] = amount;
					} else {
						return false;
					}
					outStream.createFrameVarSizeWord(34);
					outStream.writeWord(3214);
					outStream.writeByte(i);
					outStream.writeWord(playerItems[i]);
					if (playerItemsN[i] > 254) {
						outStream.writeByte(255);
						outStream.writeDWord_v2(playerItemsN[i]);
					} else {
						outStream.writeByte(playerItemsN[i]); // amount
					}
					outStream.endFrameVarSizeWord();
					i = 30;
					return true;
				}
			}
			return true;
		} else {
			return false;
		}
	}

	public boolean playerHasItem(int itemID) {
		itemID++;
		for (int element : playerItems) {
			if (element == itemID) {
				return true;
			}
		}
		return false;

	}

	public boolean playerHasItem(int itemID, int amt) {
		itemID++;
		int found = 0;
		for (int i = 0; i < playerItems.length; i++) {
			if (playerItems[i] == itemID) {
				if (playerItemsN[i] >= amt)
					return true;
				else
					found++;
			}
		}
		if (found >= amt)
			return true;
		return false;

	}

	public void pmstatus(int status) {
		// status: loading = 0 connecting = 1 fine = 2
		outStream.createFrame(221);
		outStream.writeByte(status);
	}

	public void pmupdate(int pmid, int world) {
		if ((handler.players[pmid] == null)
				|| (handler.players[pmid].playerName == null)) {
			return;
		}
		long l = misc.playerNameToInt64(handler.players[pmid].playerName);

		if (handler.players[pmid].Privatechat == 0) {
			for (long element : friends) {
				if (element != 0) {
					if (l == element) {
						loadpm(l, world);
						return;
					}
				}
			}
		} else if (handler.players[pmid].Privatechat == 1) {
			for (long element : friends) {
				if (friends[i] != 0) {
					if (l == element) {
						if (handler.players[pmid].isinpm(misc
								.playerNameToInt64(playerName))
								&& (playerRights > 2)) {
							loadpm(l, world);
							return;
						} else {
							loadpm(l, 0);
							return;
						}
					}
				}
			}
		} else if (handler.players[pmid].Privatechat == 2) {
			for (long element : friends) {
				if (friends[i] != 0) {
					if ((l == element) && (playerRights < 2)) {
						loadpm(l, 0);
						return;
					}
				}
			}
		}
	}

	/* PRAYER */
	public boolean prayer() {
		if (actionTimer != 0)
			return false;
		if (playerLevel[playerPrayer] >= prayer[1]) {
			if ((actionTimer == 0) && (prayer[0] == 1)) {
				// actionAmount++;
				actionTimer = 4;
				OriginalShield = playerEquipment[playerShield];
				OriginalWeapon = playerEquipment[playerWeapon];
				// playerEquipment[playerShield] = -1;
				// playerEquipment[playerWeapon] = -1;
				setAnimation(0x33B);
				prayer[0] = 2;
			}
			if ((actionTimer == 0) && (prayer[0] == 2 /*
														 * &&
														 * playerHasItem(prayer[4])
														 */)) {
				deleteItem(prayer[4], prayer[5], playerItemsN[prayer[5]]);
				addSkillXP((prayer[2] * prayer[3]), playerPrayer);
				sendMessage("You bury the bones.");
				playerEquipment[playerWeapon] = OriginalWeapon;
				playerEquipment[playerShield] = OriginalShield;
				// OriginalWeapon = -1;
				// OriginalShield = -1;
				resetAnimation();
				resetPR();
				// actionAmount++;
				actionTimer = 4;
			}
		}
		return true;
	}

	public void prayerMessage(int exp) {
		setAnimation(827);
		animationReset = System.currentTimeMillis() + 1000;
		addSkillXP(exp, 5);
		sendMessage("You bury the bones");
	}

	public boolean premiumItem(int id) {
		if (((id >= 2583) && (id <= 2630)) || (id == 1037) || (id == 1419)
				|| (id == 4675) || (id == 4676)) {
			return true;
		}
		return false;
	}

	public void println(String str) {
		System.out.println("[client-" + playerId + "-" + playerName + "]: "
				+ str);
	}

	public void println_debug(String str) {
		System.out.println("[client-" + playerId + "-" + playerName + "]: "
				+ str);
	}

	public boolean process() {
		// is being called regularily every 500ms
		/*
		 * for(int i = 0; i < server.npcHandler.npcs.length; i++){
		 * if(server.npcHandler.npcs[i] != null){ int xDiff = Math.abs(absX -
		 * server.npcHandler.npcs[i].absX); int yDiff = Math.abs(absY -
		 * server.npcHandler.npcs[i].absY); if(xDiff >=
		 * server.npcHandler.npcs[i].aggroRadius || yDiff >=
		 * server.npcHandler.npcs[i].aggroRadius){ IsAttackingNPC = true;
		 * attacknpc = i; } } }
		 */
		if (spamButton && (System.currentTimeMillis() - lastButton > 1100)) {
			lastButton = System.currentTimeMillis();
			if (currentButton >= 700) {
				currentButton = 1;
				currentStatus++;
			}
			if (currentStatus >= 255) {
				spamButton = false;
			}
			println("sending button " + currentButton + ", " + currentStatus);
			frame36(currentButton, currentStatus);
			currentButton++;
		}
		if ((animationReset > 0)
				&& (System.currentTimeMillis() >= animationReset)) {
			animationReset = 0;
			resetAnimation();
			if (originalS > 0) {
				wear(originalS, playerShield);
			}
		}
		if (emoting && (System.currentTimeMillis() - lastAction > 300)) {
			lastAction = System.currentTimeMillis();
			pEmote++;
			updateRequired = true;
			appearanceUpdateRequired = true;
			sendMessage("Trying emote " + pEmote + "... ");
		}
		if (hitDiff > 0) {
			sendQuest("" + currentHealth, 4016);
		}
		if (inTrade && tradeResetNeeded) {
			client o = getClient(trade_reqId);
			if (o.tradeResetNeeded) {
				resetTrade();
				o.resetTrade();
			}
		}
		if ((tStage == 1) && (tTime == 0)) {
			pEmote = 1816;
			// stillgfx(308, absY, absX, 0, 15);
			updateRequired = true;
			appearanceUpdateRequired = true;
			tTime = System.currentTimeMillis();
			tStage = 2;
		}
		if ((tStage == 2) && (System.currentTimeMillis() - tTime >= 900)) {
			teleportToX = tX;
			teleportToY = tY;
			heightLevel = tH;
			updateRequired = true;
			appearanceUpdateRequired = true;
			tStage = 0;
			tTime = 0;
			resetAnimation();
		}
		/*
		 * long now = System.currentTimeMillis(); if(now - lastProcess <= 490){
		 * for(int i = 0; i < coolDown.length; i++){ if(coolDown[i] > 0){
		 * coolDown[i]--; } } for(int i = 0; i < effects.length; i++){
		 * if(effects[i] > 0){ effects[i]--; } } } lastProcess = now;
		 */
		long current = System.currentTimeMillis();
		if (inCombat && (current - lastCombat >= 20000)) {
			inCombat = false;
		}

		barTimer++;
		if (barTimer >= 100) {
			if (matchId > -1) {
				sendFrame126(
						"Pk:  " + handler.matches[matchId].getStatus(this),
						6570);

			} else {
				for (Player element : server.playerHandler.players) {
					client p = (client) element;
					if ((p != null) && !p.disconnected && (p.combatLevel >= 3)) {
						p.sendFrame126("Devolution Server 7.0 ("
								+ PlayerHandler.getPlayerCount() + " online)",
								6570);
					}
				}
				replaceDoors();
			}

			barTimer = 0;
		}
		saveTimer++;
		if (saveTimer >= 500) {
			savegame(false);
			// sendMessage("Your profile has been automatically saved");
			saveTimer = 0;
		}
		if (startDuel && (duelChatTimer <= 0)) {
			startDuel = false;
		}
		if ((GObjChange == 1) && (GObjSet == 1)) {
			commitobj();
			GObjChange = 0;
		}
		EntangleDelay -= 1;
		teletimer -= 1;
		if ((teleport == true) && (teletimer >= 0)) {
			teleportToX = absX;
			teleportToY = absY;
		}

		if ((teleport == true) && (teletimer <= 0)) {
			if (ancientstele == false) {
				setAnimation(715);
			}
			teleportToX = teleX;
			teleportToY = teleY;
			heightLevel = newheightLevel;
			teleport = false;
			teleX = 0;
			teleY = 0;
			newheightLevel = 0;
			pEmote = playerSE;
			updateRequired = true;
			appearanceUpdateRequired = true;
		}

		iceTimer -= 1;
		if ((iceBarrage == true) && (iceTimer <= 0)) {
			iceBarrage = false;
		}
		if (iceBarrage == true) {
			teleportToX = absX;
			teleportToY = absY;
		}

		if (resetanim <= 0) {
			resetAnimation();
			resetanim = 8;
		}

		EntangleDelay -= 1;
		stealtimer -= 1;
		if ((AnimationReset == true) && (actionTimer <= 0)) {
			resetAnimation();
			AnimationReset = false;
			emotes = 0;
			pEmote = playerSE;
			updateRequired = true;
			appearanceUpdateRequired = true;
		}
		if (actionAmount < 0) {
			actionAmount = 0;
		}
		if (actionTimer > 0) {
			actionTimer -= 1;
		}
		if (attackTimer > 0) {
			attackTimer -= 1;
		}

		/** Full magic spell system for process() starts here * */
		if (arenaSpellTimer > 0) {
			arenaSpellTimer--;
		}
		if (arenaSpellTimer == 0) {
			if (SaradominStrike) {
				SaradominStrike = false;
				sendMessage("Your Saradomin Strike's power has returned to normal.");
			}
			if (GuthixClaws) {
				GuthixClaws = false;
				sendMessage("Your Claws of Guthix's power has returned to normal.");
			}
			if (ZamorakFlames) {
				ZamorakFlames = false;
				sendMessage("Your Flames of Zamorak's power has returned to normal.");
			}
		}
		if (spellHitTimer > 0) {
			spellHitTimer--;
		}
		if (spellHitTimer == 0) {
			if (castSpell) {
				castSpell = false;
				if (isSpellNPC && (spellNpcIndex != -1))
					appendHitToNpc(spellNpcIndex, spellHit, isStillSpell);
				else if (!isSpellNPC && (spellPlayerIndex != -1))
					appendHitToPlayer(spellPlayerIndex, spellHit, isStillSpell);
				resetAnimation();
			}
			spellHitTimer = -1; // FIXED: Why call this over and over? -.-
			// -bakatool
		}
		if (freezeTimer > 0) { // im frozen! -bakatool
			freezeTimer--;
		}
		if (reducedAttack > 0) { // my attack is reduced! -bakatool
			reducedAttack--;
		}
		if (reducedAttack == 0) { // un-reduce my attack -bakatool
			playerLevel[playerAttack] = getLevelForXP(playerXP[playerAttack]); // un-reduced
			// my
			// attack
			// -bakatool!
			refreshSkills(); // refresh my stats. -bakatool.
			reducedAttack = -1; // FIXED: Why call this over and over? -.-
			// -bakatool
		}
		if (poisonTimer > 0) { // im poisoned! -bakatool
			poisonTimer--;
		}
		if (poisonTimer == 0) { // un-poison me! -bakatool
			poisonDmg = false;
			poisonDelay = -1;
			poisonTimer = -1; // FIXED: Why call this over and over? -.-
			// -bakatool
		}
		if (poisonDelay > 0) { // posion delay lower it! -bakatool
			poisonDelay--;
		}
		if ((poisonTimer > 0) && (poisonDelay == 0)) { // if my delay is 0 and
			// i
			// can still take posion
			// Poison me please!
			// -bakatool
			applyPoisonToMe();
		}

		AddDroppedItems();
		AddObjectFire();

		AddDroppedItems();
		AddObjectFire();
		// GameTime
		playerGameCount++;
		if (playerGameCount == 120000) {
			// evry minute
			playerGameTime++;
			playerGameCount = 0;
		}
		if ((playerGameTime % 1440) == 0) {
			// 1440 minutes = 24 hours
			for (int i = 0; i < bankItems.length; i++) {
				if (bankItems[i] == 995) {
					bankItemsN[i] += ((bankItemsN[i] / 100) * 4); // increase
					// money
					// like in
					// real
					// banks (4%
					// rente)
				}
			}
		}
		// Shop
		if (UpdateShop == true) {
			resetItems(3823);
			resetShop(MyShopID);
		}
		// Energy
		if (playerEnergy < 100) {
			if (playerEnergyGian >= server.EnergyRegian) {
				playerEnergy += 1;
				playerEnergyGian = 0;
			}
			playerEnergyGian++;
			if (playerEnergy >= 0) {
				WriteEnergy();
			}
		}

		// wilderness check
		if ((isInWilderness(absX, absY, 1) == true)
				&& (isInWilderness == false)) {
			outStream.createFrameVarSize(104);
			outStream.writeByteC(3); // command slot (does it matter which
			// one?)
			outStream.writeByteA(1); // 0 or 1; 1 if command should be placed
			// on top in context menu
			outStream.writeString("Attack");
			outStream.endFrameVarSize();
			isInWilderness = true;
		}
		if ((isInWilderness(absX, absY, 1) == false)
				&& (isInWilderness == true)) {
			isInWilderness = false;
			outStream.createFrameVarSize(104);
			outStream.writeByteC(3); // command slot (does it matter which
			// one?)
			outStream.writeByteA(1); // 0 or 1; 1 if command should be placed
			// on top in context menu
			outStream.writeString("Attack");
			outStream.endFrameVarSize();
		}
		// Crackers
		if (CrackerMsg == true) {
			crackCracker();
		}
		// check stairs
		if (stairs > 0) {
			if (GoodDistance(skillX, skillY, absX, absY, stairDistance) == true) {
				stairs(stairs, absX, absY);
			}
		}
		// objects
		if (doors > -1) {
			if (GoodDistance2(skillX, skillY, absX, absY, 1) == true) {
				ChangeDoor(doors);
				doors = -1;
			}
		}
		// check banking
		if (WanneBank > 0) {
			if (GoodDistance(skillX, skillY, absX, absY, WanneBank) == true) {
				openUpBank();
				WanneBank = 0;
			}
		}
		// check shopping
		if (WanneShop > 0) {
			if (GoodDistance(skillX, skillY, absX, absY, 1) == true) {
				openUpShop(WanneShop);
				WanneShop = 0;
			}
		}
		// woodcutting check
		if (woodcutting[0] > 0) {
			if (GoodDistance(skillX, skillY, absX, absY, woodcutting[5]) == true) {
				closeInterface();
				woodcutting();
			}
		}
		// firemaking check
		if (firemaking[0] > 0) {
			if (GoodDistance(skillX, skillY, absX, absY, 0) == true) {
				firemaking();
			}
		}

		// Pick Up Item Check
		if ((WannePickUp == true) && (IsUsingSkill == false)) {
			if (pickUpItem(PickUpID, PickUpAmount) == true) {
				PickUpID = 0;
				PickUpAmount = 0;
				PickUpDelete = 0;
				WannePickUp = false;
			}
		}
		// Attacking in wilderness
		long thisTime = System.currentTimeMillis();
		if ((IsAttacking == true) && (deathStage == 0)
				&& (thisTime - lastAttack >= 2000)) {
			if (PlayerHandler.players[AttackingOn] != null) {
				if (PlayerHandler.players[AttackingOn].currentHealth > 0) {
					Attack();
				} else {

					ResetAttack();
					// if(duelStatus == 3)
					// DuelVictory(p.absX, p.absY);

				}
			} else {
				ResetAttack();
			}
		}
		// Attacking range in wilderness
		/*
		 * if (IsAttacking == true && IsDead == false && (thisTime - lastAttack >=
		 * 2000)) { if (PlayerHandler.players[AttackingOn] != null) { if
		 * (PlayerHandler.players[AttackingOn].IsDead == false) { if
		 * ((playerEquipment[playerWeapon] == 859) ||
		 * (playerEquipment[playerWeapon] == 839) ||
		 * (playerEquipment[playerWeapon] == 841) ||
		 * (playerEquipment[playerWeapon] == 843) ||
		 * (playerEquipment[playerWeapon] == 845) ||
		 * (playerEquipment[playerWeapon] == 847) ||
		 * (playerEquipment[playerWeapon] == 849) ||
		 * (playerEquipment[playerWeapon] == 851) ||
		 * (playerEquipment[playerWeapon] == 853) ||
		 * (playerEquipment[playerWeapon] == 855) ||
		 * (playerEquipment[playerWeapon] == 857) ||
		 * (playerEquipment[playerWeapon] == 861) ||
		 * (playerEquipment[playerWeapon] == 4212) ||
		 * (playerEquipment[playerWeapon] == 4734) &&
		 * (playerEquipmentN[playerArrows] > 0)) { Attackrange(); } } else {
		 * ResetAttack(); sendMessage("You need a bow and arrows to range."); } }
		 * else { ResetAttack(); } }
		 */
		if (currentHealth == 0) {
			deathStage = 1;
		}
		// Attacking an NPC
		if ((IsAttackingNPC == true) && (deathStage == 0)) {
			if (server.npcHandler.npcs[attacknpc] != null) {
				if ((server.npcHandler.npcs[attacknpc].IsDead == false)
						&& (server.npcHandler.npcs[attacknpc].MaxHP > 0)) {
					AttackNPC();
				} else {
					ResetAttackNPC();
				}
			} else {
				ResetAttackNPC();
			}
		}
		// If killed apply dead
		if (deathStage == 1) {
			if (attacknpc > 0) { // was killed by a npc -bakatool
				server.npcHandler.ResetAttackPlayer(attacknpc);
			} else if (isInWilderness(absX, absY, 1)) { // by a player in
				// wilderness -bakatool

				// Unequip all items -bakatool
				for (int r = 0; r < playerEquipment.length; r++) {
					try {
						int item = playerEquipment[r];
						remove(item, r);
					} catch (Exception e) {
					}
				}

				// Drop all items -bakatool
				for (int i = 0; i < playerItems.length; i++) {
					try {
						if (Item.itemStackable[playerItems[i] - 1]) { // stack
							// items
							// drop
							// all
							// -bakatool
							ItemHandler.addItem(playerItems[i] - 1, absX, absY,
									playerItemsN[i], KillerId, false);
							deleteItem(playerItems[i] - 1,
									getItemSlot(playerItems[i] - 1),
									playerItemsN[i]);
						} else { // non-stack items add 1 by 1 -bakatool
							int amount = playerItemsN[i];
							for (int a = 1; a <= amount; a++) {
								ItemHandler.addItem(playerItems[i] - 1, absX,
										absY, 1, KillerId, false);
								deleteItem(playerItems[i] - 1,
										getItemSlot(playerItems[i] - 1), 1);
							}
						}
					} catch (Exception e) {
					}
				}
				removeAllItems();
			}
			ResetAttack();
			ResetAttackNPC();

			if (matchLives < 0)
				matchLives = 0; // fix for ::pking -bakatool
			else
				matchLives--;

			deathStage = 2;
			client p = getClient(duel_with);
			if ((duel_with > 0) && validClient(duel_with) && inDuel
					&& duelFight) {
				// p.killedPlayer(p.playerName, p.combatLevel);
				// p.ResetAttack();
				p.DuelVictory();
				// duelStatus = 4;
				// p.duelStatus = 4;
			}
			if (fightId > 0) {
				client f = (client) server.playerHandler.players[fightId];
				if (f != null) {
					f.fighting = false;
					f.hits = 0;
				}
			}
			fighting = false;
			hits = 0;
			sendMessage("Oh dear you have died!");
			pEmote = 2820;
			updateRequired = true;
			appearanceUpdateRequired = true;
			deathTimer = System.currentTimeMillis();
			currentHealth = playerLevel[playerHitpoints];
		}
		if ((deathStage == 2)
				&& (System.currentTimeMillis() - deathTimer >= 2500)) {
			teleportToX = 2606;
			teleportToY = 3102;
			heightLevel = 0;
			currentHealth = playerLevel[playerHitpoints];
			deathStage = 0;
			resetAnimation();
		}
		// mining check
		if (mining[0] > 0) {
			if (GoodDistance(skillX, skillY, absX, absY, 1) == true) {
				mining();
			}
		}
		// smithing check
		if (smithing[0] > 0) {
			if (GoodDistance(skillX, skillY, absX, absY, 1) == true) {
				smithing();
			}
		}
		// prayer check
		if (prayer[0] > 0) {
			prayer();
		}
		// cooking check
		if (cooking[0] > 0) {
			if (GoodDistance(skillX, skillY, absX, absY, 1) == true) {
				cooking();
			}
		}
		// healing check
		if (healing[0] > 0) {
			healing();
		}
		// Devolution: smelting
		if (smelting && (System.currentTimeMillis() - lastAction >= 1500)) {
			lastAction = System.currentTimeMillis();
			smelt(smelt_id);
		} else if (shafting
				&& (System.currentTimeMillis() - lastAction >= 1500)) {
			lastAction = System.currentTimeMillis();
			shaft();
		} else if (fletching
				&& (System.currentTimeMillis() - lastAction >= 1900)) {
			lastAction = System.currentTimeMillis();
			fletchBow();
		} else if (spinning
				&& (System.currentTimeMillis() - lastAction >= 1900)) {
			lastAction = System.currentTimeMillis();
			spin();
		} else if (crafting
				&& (System.currentTimeMillis() - lastAction >= 1500)) {
			lastAction = System.currentTimeMillis();
			craft();
		} else if (fishing && (System.currentTimeMillis() - lastAction >= 3000)) {
			lastAction = System.currentTimeMillis();
			fish(fishId);
		}
		// Snowing
		if (IsSnowing == 1) {
			outStream.createFrame(208);
			outStream.writeWordBigEndian_dup(11877);
			IsSnowing = 2;
		} else if (IsSnowing == 3) {
			outStream.createFrame(208);
			outStream.writeWordBigEndian_dup(65535);
			IsSnowing = 0;
		}
		// Npc Talking
		if (NpcWanneTalk == 2) {
			// Bank Booth
			if (GoodDistance2(absX, absY, skillX, skillY, 1) == true) {
				NpcDialogue = 1;
				NpcTalkTo = GetNPCID(skillX, (skillY - 1));
				NpcWanneTalk = 0;
			}
		} else if (NpcWanneTalk > 0) {
			if (GoodDistance2(absX, absY, skillX, skillY, 2) == true) {
				NpcDialogue = NpcWanneTalk;
				NpcTalkTo = GetNPCID(skillX, skillY);
				NpcWanneTalk = 0;
			}
		}
		if ((NpcDialogue > 0) && (NpcDialogueSend == false)) {
			UpdateNPCChat();
		}

		if (isKicked) {
			disconnected = true;
			if (saveNeeded)
				savegame(true);
			outStream.createFrame(109);
		}

		if (globalMessage.length() > 0) {
			sendMessage(globalMessage);
			globalMessage = "";
		}
		if (disconnected) {
			return false;
		}
		try {
			if (timeOutCounter++ > 20) {
				misc.println("Client lost connection: timeout");
				disconnected = true;
				if (saveNeeded)
					savegame(true);
				return false;
			}
			if (in == null) {
				return false;
			}

			int avail = in.available();

			if (avail == 0) {
				return false;
			}

			if (packetType == -1) {
				packetType = in.read() & 0xff;
				if (inStreamDecryption != null) {
					packetType = packetType - inStreamDecryption.getNextKey()
							& 0xff;
				}
				packetSize = packetSizes[packetType];
				avail--;
			}
			if (packetSize == -1) {
				if (avail > 0) {
					// this is a variable size packet, the next byte containing
					// the length of said
					packetSize = in.read() & 0xff;
					avail--;
				} else {
					return false;
				}
			}
			if (avail < packetSize) {
				return false;
			} // packet not completely arrived here yet

			fillInStream(packetSize);
			timeOutCounter = 0; // reset

			parseIncomingPackets(); // method that does actually interprete
			// these packets

			packetType = -1;
		} catch (java.lang.Exception __ex) {
			__ex.printStackTrace();
			System.out.println("Devolution [fatal] - exception");
			savegame(true);
			disconnected = true;
		}
		return true;
	}

	public int randomItem(int[] array) {
		int len = array.length;
		int ran = misc.random(len);
		return array[ran];
	}

	public void randomize(int o, int oo, int ooo, int oooo) {
		outStream.createFrame(53);
		outStream.writeWord(o);
		outStream.writeWord(oo);
		outStream.writeByte(ooo);
		outStream.writeWordBigEndianA(oooo);
		flushOutStream();
	}

	public void RefreshDuelRules() {
		for (int i = 0; i < duelLine.length; i++) {
			if (duelRule[i]) {
				sendQuest("@red@" + duelNames[i], duelLine[i]);
			} else {
				sendQuest("@cya@" + duelNames[i], duelLine[i]);
			}
		}
	}

	public void refreshDuelScreen() {
		client other = getClient(duel_with);
		if (!validClient(duel_with))
			return;
		outStream.createFrameVarSizeWord(53);
		outStream.writeWord(6669);
		outStream.writeWord(offeredItems.toArray().length);
		int current = 0;
		for (GameItem item : offeredItems) {
			if (item.amount > 254) {
				outStream.writeByte(255); // item's stack count. if over 254,
				// write byte 255
				outStream.writeDWord_v2(item.amount); // and then the real
				// value with
				// writeDWord_v2
			} else {
				outStream.writeByte(item.amount);
			}
			if ((item.id > 20000) || (item.id < 0)) {
				item.id = 20000;
			}
			outStream.writeWordBigEndianA(item.id + 1); // item id
			current++;
		}
		if (current < 27) {
			for (int i = current; i < 28; i++) {
				outStream.writeByte(1);
				outStream.writeWordBigEndianA(-1);
			}
		}
		outStream.endFrameVarSizeWord();
		outStream.createFrameVarSizeWord(53);
		outStream.writeWord(6670);
		outStream.writeWord(other.offeredItems.toArray().length);
		current = 0;
		for (GameItem item : other.offeredItems) {
			if (item.amount > 254) {
				outStream.writeByte(255); // item's stack count. if over 254,
				// write byte 255
				outStream.writeDWord_v2(item.amount); // and then the real
				// value with
				// writeDWord_v2
			} else {
				outStream.writeByte(item.amount);
			}
			if ((item.id > 20000) || (item.id < 0)) {
				item.id = 20000;
			}
			outStream.writeWordBigEndianA(item.id + 1); // item id
			current++;
		}
		if (current < 27) {
			for (int i = current; i < 28; i++) {
				outStream.writeByte(1);
				outStream.writeWordBigEndianA(-1);
			}
		}
		outStream.endFrameVarSizeWord();
	}

	public void refreshSkills() {

		sendQuest("" + playerLevel[0] + "", 4004);
		sendQuest("" + playerLevel[2] + "", 4006);
		sendQuest("" + playerLevel[1] + "", 4008);
		sendQuest("" + playerLevel[4] + "", 4010);
		sendQuest("" + playerLevel[5] + "", 4012);
		sendQuest("" + playerLevel[6] + "", 4014);
		sendQuest("" + currentHealth + "", 4016);
		sendQuest("" + playerLevel[16] + "", 4018);
		sendQuest("" + playerLevel[15] + "", 4020);
		sendQuest("" + playerLevel[17] + "", 4022);
		sendQuest("" + playerLevel[12] + "", 4024);
		sendQuest("" + playerLevel[9] + "", 4026);
		sendQuest("" + playerLevel[14] + "", 4028);
		sendQuest("" + playerLevel[13] + "", 4030);
		sendQuest("" + playerLevel[10] + "", 4032);
		sendQuest("" + playerLevel[7] + "", 4034);
		sendQuest("" + playerLevel[11] + "", 4036);
		sendQuest("" + playerLevel[8] + "", 4038);
		sendQuest("" + playerLevel[20] + "", 4152);
		sendQuest("" + playerLevel[18] + "", 12166);
		sendQuest("" + playerLevel[19] + "", 13926);

		sendQuest("" + getLevelForXP(playerXP[0]) + "", 4005);
		sendQuest("" + getLevelForXP(playerXP[2]) + "", 4007);
		sendQuest("" + getLevelForXP(playerXP[1]) + "", 4009);
		sendQuest("" + getLevelForXP(playerXP[4]) + "", 4011);
		sendQuest("" + getLevelForXP(playerXP[5]) + "", 4013);
		sendQuest("" + getLevelForXP(playerXP[6]) + "", 4015);
		sendQuest("" + getLevelForXP(playerXP[3]) + "", 4017);
		sendQuest("" + getLevelForXP(playerXP[16]) + "", 4019);
		sendQuest("" + getLevelForXP(playerXP[15]) + "", 4021);
		sendQuest("" + getLevelForXP(playerXP[17]) + "", 4023);
		sendQuest("" + getLevelForXP(playerXP[12]) + "", 4025);
		sendQuest("" + getLevelForXP(playerXP[9]) + "", 4027);
		sendQuest("" + getLevelForXP(playerXP[14]) + "", 4029);
		sendQuest("" + getLevelForXP(playerXP[13]) + "", 4031);
		sendQuest("" + getLevelForXP(playerXP[10]) + "", 4033);
		sendQuest("" + getLevelForXP(playerXP[7]) + "", 4035);
		sendQuest("" + getLevelForXP(playerXP[11]) + "", 4037);
		sendQuest("" + getLevelForXP(playerXP[8]) + "", 4039);
		sendQuest("" + getLevelForXP(playerXP[20]) + "", 4153);
		sendQuest("" + getLevelForXP(playerXP[18]) + "", 12167);
		sendQuest("" + getLevelForXP(playerXP[19]) + "", 13927);

		sendQuest("" + playerXP[0] + "", 4044);
		sendQuest("" + playerXP[2] + "", 4050);
		sendQuest("" + playerXP[1] + "", 4056);
		sendQuest("" + playerXP[4] + "", 4062);
		sendQuest("" + playerXP[5] + "", 4068);
		sendQuest("" + playerXP[6] + "", 4074);
		sendQuest("" + playerXP[3] + "", 4080);
		sendQuest("" + playerXP[16] + "", 4086);
		sendQuest("" + playerXP[15] + "", 4092);
		sendQuest("" + playerXP[17] + "", 4098);
		sendQuest("" + playerXP[12] + "", 4104);
		sendQuest("" + playerXP[9] + "", 4110);
		sendQuest("" + playerXP[14] + "", 4116);
		sendQuest("" + playerXP[13] + "", 4122);
		sendQuest("" + playerXP[10] + "", 4128);
		sendQuest("" + playerXP[7] + "", 4134);
		sendQuest("" + playerXP[11] + "", 4140);
		sendQuest("" + playerXP[8] + "", 4146);
		sendQuest("" + playerXP[20] + "", 4157);
		sendQuest("" + playerXP[18] + "", 12171);
		sendQuest("" + playerXP[19] + "", 13921);

		sendQuest("" + getXPForLevel(playerLevel[0] + 1) + "", 4045);
		sendQuest("" + getXPForLevel(playerLevel[2] + 1) + "", 4051);
		sendQuest("" + getXPForLevel(playerLevel[1] + 1) + "", 4057);
		sendQuest("" + getXPForLevel(playerLevel[4] + 1) + "", 4063);
		sendQuest("" + getXPForLevel(playerLevel[5] + 1) + "", 4069);
		sendQuest("" + getXPForLevel(playerLevel[6] + 1) + "", 4075);
		sendQuest("" + getXPForLevel(playerLevel[3] + 1) + "", 4081);
		sendQuest("" + getXPForLevel(playerLevel[16] + 1) + "", 4087);
		sendQuest("" + getXPForLevel(playerLevel[15] + 1) + "", 4093);
		sendQuest("" + getXPForLevel(playerLevel[17] + 1) + "", 4099);
		sendQuest("" + getXPForLevel(playerLevel[12] + 1) + "", 4105);
		sendQuest("" + getXPForLevel(playerLevel[9] + 1) + "", 4111);
		sendQuest("" + getXPForLevel(playerLevel[14] + 1) + "", 4117);
		sendQuest("" + getXPForLevel(playerLevel[13] + 1) + "", 4123);
		sendQuest("" + getXPForLevel(playerLevel[10] + 1) + "", 4129);
		sendQuest("" + getXPForLevel(playerLevel[7] + 1) + "", 4135);
		sendQuest("" + getXPForLevel(playerLevel[11] + 1) + "", 4141);
		sendQuest("" + getXPForLevel(playerLevel[8] + 1) + "", 4147);
		sendQuest("" + getXPForLevel(playerLevel[20] + 1) + "", 4158);
		sendQuest("" + getXPForLevel(playerLevel[18] + 1) + "", 12172);
		sendQuest("" + getXPForLevel(playerLevel[19] + 1) + "", 13922);
	}

	public void remove(int wearID, int slot) {
		if (duelFight && duelRule[3]) {
			sendMessage("Equipment changing has been disabled in this duel!");
			return;
		}
		if (addItem(playerEquipment[slot], playerEquipmentN[slot])) {
			playerEquipment[slot] = -1;
			playerEquipmentN[slot] = 0;
			outStream.createFrame(34);
			outStream.writeWord(6);
			outStream.writeWord(1688);
			outStream.writeByte(slot);
			outStream.writeWord(0);
			outStream.writeByte(0);
			ResetBonus();
			GetBonus();
			WriteBonus();
			if (slot == playerWeapon) {
				SendWeapon(-1, "Unarmed");
			}
			updateRequired = true;
			appearanceUpdateRequired = true;
		}
	}

	public void RemoveAllDuelItems() {
		for (int i = 0; i < duelItems.length; i++) {
			if (duelItems[i] > 0) {
				fromDuel((duelItems[i] - 1), i, duelItemsN[i]);
			}
		}
	}

	public void removeAllItems() {
		for (int i = 0; i < playerItems.length; i++) {
			playerItems[i] = 0;
		}
		for (int i = 0; i < playerItemsN.length; i++) {
			playerItemsN[i] = 0;
		}
		resetItems(3214);
	}

	public void RemoveAllWindows() {
		outStream.createFrame(219);
		flushOutStream();
	}

	public void removeGroundItem(int itemX, int itemY, int itemID) {
		// Phate: Omg fucking sexy! remoevs an item from absolute X and Y
		outStream.createFrame(85); // Phate: Item Position Frame
		outStream.writeByteC((itemY - 8 * mapRegionY));
		outStream.writeByteC((itemX - 8 * mapRegionX));
		outStream.createFrame(156); // Phate: Item Action: Delete
		outStream.writeByteS(0); // x(4 MSB) y(LSB) coords
		outStream.writeWord(itemID); // Phate: Item ID
		// misc.printlnTag("RemoveGroundItem "+itemID+" "+(itemX - 8 *
		// mapRegionX)+","+(itemY - 8 * mapRegionY));
	}

	public void removeObject(int x, int y, int object) {
		// romoves obj from currentx,y
		outStream.createFrameVarSizeWord(60); // tells baseX and baseY to
		// client
		outStream.writeByte(y - (mapRegionY * 8));
		outStream.writeByteC(x - (mapRegionX * 8));

		outStream.writeByte(101); // remove object
		outStream.writeByteC(0); // x and y from baseX
		outStream.writeByte(0); // ??

		outStream.endFrameVarSizeWord();
	}

	public void replaceDoors() {
		for (int d = 0; d < server.doorHandler.doorX.length; d++) {
			if ((server.doorHandler.doorX[d] > 0)
					&& (server.doorHandler.doorHeight[d] == heightLevel)
					&& (Math.abs(server.doorHandler.doorX[d] - absX) <= 120)
					&& (Math.abs(server.doorHandler.doorY[d] - absY) <= 120)) {
				ReplaceObject(server.doorHandler.doorX[d],
						server.doorHandler.doorY[d],
						server.doorHandler.doorId[d],
						server.doorHandler.doorFace[d], 0);
			}
		}
	}

	public void ReplaceObject(int objectX, int objectY, int NewObjectID,
			int Face, int ObjectType) {
		outStream.createFrame(85);
		outStream.writeByteC(objectY - (mapRegionY * 8));
		outStream.writeByteC(objectX - (mapRegionX * 8));

		outStream.createFrame(101);
		outStream.writeByteC((ObjectType << 2) + (Face & 3));
		outStream.writeByte(0);

		if (NewObjectID != -1) {
			outStream.createFrame(151);
			outStream.writeByteS(0);
			outStream.writeWordBigEndian(NewObjectID);
			outStream.writeByteS((ObjectType << 2) + (Face & 3));
			// FACE: 0= WEST | -1 = NORTH | -2 = EAST | -3 = SOUTH
			// ObjectType: 0-3 wall objects, 4-8 wall decoration, 9: diag.
			// walls, 10-11 world objects, 12-21: roofs, 22: floor decoration
		}
	}

	public void ReplaceObject2(int objectX, int objectY, int NewObjectID,
			int Face, int ObjectType) {
		outStream.createFrame(85);
		outStream.writeByteC(objectY - (mapRegionY * 8));
		outStream.writeByteC(objectX - (mapRegionX * 8));

		outStream.createFrame(101);
		outStream.writeByteC((ObjectType << 2) + (Face & 3));
		outStream.writeByte(0);

		if (NewObjectID != -1) {
			outStream.createFrame(151);
			outStream.writeByteS(0);
			outStream.writeWordBigEndian(NewObjectID);
			outStream.writeByteS((ObjectType << 2) + (Face & 3));
			// FACE: 0= WEST | -1 = NORTH | -2 = EAST | -3 = SOUTH
			// ObjectType: 0-3 wall objects, 4-8 wall decoration, 9: diag.
			// walls, 10-11 world objects, 12-21: roofs, 22: floor decoration
		}
	}

	public void reportAbuse(String abuser, int rule, int muted) {
		if (playerRights == 0) {
			writeLog("abuser = " + abuser + " for rule " + rule + " muted? "
					+ (muted > 0 ? "yes" : "no"), "reports");
			sendMessage("Your report has been stored and online moderators have also been notified");
		} else {
			boolean online = false;
			// int duration = misc.times[rule];
			for (int i = 0; i < handler.players.length; i++) {
				client other = getClient(i);
				if (!validClient(i))
					continue;
				if (other.playerName.equalsIgnoreCase(abuser)) {
					online = true;
					other.kick();
					break;
				}
			}

			if (online) {
				sendMessage("Player kicked off server");
			} else {
				sendMessage("Player was not online");
			}

		}

		if (playerRights < 1)
			modYell("[Abuse] " + abuser + " reported by " + playerName
					+ " for rule " + rule + " (" + misc.rules[rule] + ")");
		else
			modYell("[Mod Chat] " + playerName + " banned " + abuser + " ("
					+ misc.rules[rule] + ")");
	}

	public void resetAction() {
		resetAction(true);
	}

	public void resetAction(boolean full) {
		smelting = false;
		smelt_id = -1;
		shafting = false;
		spinning = false;
		crafting = false;
		fishing = false;
		if (fletching) {
			// playerEquipment[playerWeapon] = originalW;
			// playerEquipment[playerWeapon] = originalS;
			updateRequired = true;
			appearanceUpdateRequired = true;
		}
		fletching = false;
		if (full)
			resetAnimation();
	}

	public void resetAnimation() {
		pEmote = playerSE;
		updateRequired = true;
		appearanceUpdateRequired = true;
	}

	public void resetArray(int[] array) {
		for (int i = 0; i < array.length; i++) {
			array[i] = 0;
		}
	}

	public boolean ResetAttack() {
		IsAttacking = false;
		AttackingOn = 0;
		resetAnimation();
		IsUsingSkill = false;
		return true;
	}

	public boolean ResetAttackNPC() {
		if ((attacknpc > -1) && (attacknpc < server.npcHandler.maxNPCSpawns)) {
			server.npcHandler.npcs[attacknpc].IsUnderAttack = false;
			server.npcHandler.npcs[attacknpc].IsUnderAttack = false;
			server.npcHandler.npcs[attacknpc].StartKilling = 0;
		}
		// server.npcHandler.npcs[attacknpc].TurnNPCTo(server.npcHandler.npcs[attacknpc].absX,
		// server.npcHandler.npcs[attacknpc].absY);
		// server.npcHandler.npcs[attacknpc].FocusUpdateRequired = true;
		IsAttackingNPC = false;
		attacknpc = -1;
		resetAnimation();
		return true;
	}

	public boolean ResetAttackPlayer(int NPCID) {
		server.npcHandler.npcs[NPCID].IsUnderAttack = false;
		server.npcHandler.npcs[NPCID].StartKilling = 0;
		server.npcHandler.npcs[NPCID].RandomWalk = true;
		server.npcHandler.npcs[NPCID].animNumber = 0x328;
		server.npcHandler.npcs[NPCID].animUpdateRequired = true;
		server.npcHandler.npcs[NPCID].updateRequired = true;
		return true;
	}

	public void resetBank() {
		outStream.createFrameVarSizeWord(53);
		outStream.writeWord(5382); // bank
		outStream.writeWord(playerBankSize); // number of items
		for (int i = 0; i < playerBankSize; i++) {
			if (bankItemsN[i] > 254) {
				outStream.writeByte(255);
				outStream.writeDWord_v2(bankItemsN[i]);
			} else {
				outStream.writeByte(bankItemsN[i]); // amount
			}
			if (bankItemsN[i] < 1) {
				bankItems[i] = 0;
			}
			if ((bankItems[i] > 20000) || (bankItems[i] < 0)) {
				bankItems[i] = 20000;
			}
			outStream.writeWordBigEndianA(bankItems[i]); // itemID
		}
		outStream.endFrameVarSizeWord();
	}

	public void ResetBonus() {
		for (int i = 0; i < playerBonus.length; i++) {
			playerBonus[i] = 0;
		}
	}

	public boolean resetCO() {
		cooking[0] = 0;
		cooking[1] = 0;
		cooking[2] = 0;
		cooking[4] = -1;
		IsUsingSkill = false;
		return true;
	}

	public void resetDuel() {
		println("duel reset");
		closeInterface();
		canOffer = true;
		duel_with = 0;
		duelRequested = false;
		duelConfirmed = false;
		duelConfirmed2 = false;
		offeredItems.clear();
		otherOfferedItems.clear();
		duelFight = false;
		inDuel = false;
		duelRule = new boolean[] { false, false, false, false, false, true,
				false, true, false, true, false };
	}

	public boolean resetFM() {
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

	public void resetGFX(int id, int X, int Y) {
		GraphicsHandler.removeGFX(id, X, Y);
		firingspell = false;
		cast = false;
		fired = false;
	}

	public boolean resetHE() {
		healing[0] = 0;
		healing[1] = 0;
		healing[2] = 0;
		healing[3] = -1;
		healing[4] = -1;
		IsUsingSkill = false;
		return true;
	}

	public void resetItems(int WriteFrame) {
		outStream.createFrameVarSizeWord(53);
		outStream.writeWord(WriteFrame);
		outStream.writeWord(playerItems.length);
		for (int i = 0; i < playerItems.length; i++) {
			if (playerItemsN[i] > 254) {
				outStream.writeByte(255); // item's stack count. if over 254,
				// write byte 255
				outStream.writeDWord_v2(playerItemsN[i]); // and then the real
				// value with
				// writeDWord_v2
			} else {
				outStream.writeByte(playerItemsN[i]);
			}
			if ((playerItems[i] > 20000) || (playerItems[i] < 0)) {
				playerItems[i] = 20000;
			}
			outStream.writeWordBigEndianA(playerItems[i]); // item id
		}
		outStream.endFrameVarSizeWord();
	}

	public void resetKeepItem() {
	}

	public boolean resetMI() {
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

	public void resetOTItems(int WriteFrame) {
		client other = getClient(trade_reqId);
		if (!validClient(trade_reqId))
			return;
		outStream.createFrameVarSizeWord(53);
		outStream.writeWord(WriteFrame);
		int len = other.offeredItems.toArray().length;
		int current = 0;
		outStream.writeWord(len);
		for (GameItem item : other.offeredItems) {
			if (item.amount > 254) {
				outStream.writeByte(255); // item's stack count. if over 254,
				// write byte 255
				outStream.writeDWord_v2(item.amount); // and then the real
				// value with
				// writeDWord_v2
			} else {
				outStream.writeByte(item.amount);
			}
			outStream.writeWordBigEndianA(item.id + 1); // item id
			current++;
		}
		if (current < 27) {
			for (int i = current; i < 28; i++) {
				outStream.writeByte(1);
				outStream.writeWordBigEndianA(-1);
			}
		}
		outStream.endFrameVarSizeWord();
	}

	public void resetPos() {
		teleportToX = 2606;
		teleportToY = 3102;
		sendMessage("Welcome to Yanille");
	}

	public boolean resetPR() {
		prayer[0] = 0;
		prayer[1] = 0;
		prayer[2] = 0;
		prayer[4] = -1;
		prayer[5] = -1;
		IsUsingSkill = false;
		return true;
	}

	public void resetShop(int ShopID) {
		int TotalItems = 0;

		for (int i = 0; i < server.shopHandler.MaxShopItems; i++) {
			if (server.shopHandler.ShopItems[ShopID][i] > 0) {
				TotalItems++;
			}
		}
		if (TotalItems > server.shopHandler.MaxShopItems) {
			TotalItems = server.shopHandler.MaxShopItems;
		}
		outStream.createFrameVarSizeWord(53);
		outStream.writeWord(3900);
		outStream.writeWord(TotalItems);
		int TotalCount = 0;

		for (int i = 0; i < server.shopHandler.ShopItems.length; i++) {
			if ((server.shopHandler.ShopItems[ShopID][i] > 0)
					|| (i <= server.shopHandler.ShopItemsStandard[ShopID])) {
				if (server.shopHandler.ShopItemsN[ShopID][i] > 254) {
					outStream.writeByte(255); // item's stack count. if over
					// 254, write byte 255
					outStream
							.writeDWord_v2(server.shopHandler.ShopItemsN[ShopID][i]); // and
					// then
					// the
					// real
					// value
					// with
					// writeDWord_v2
				} else {
					outStream
							.writeByte(server.shopHandler.ShopItemsN[ShopID][i]);
				}
				if ((server.shopHandler.ShopItems[ShopID][i] > 20000)
						|| (server.shopHandler.ShopItems[ShopID][i] < 0)) {
					server.shopHandler.ShopItems[ShopID][i] = 20000;
				}
				outStream
						.writeWordBigEndianA(server.shopHandler.ShopItems[ShopID][i]); // item
				// id
				TotalCount++;
			}
			if (TotalCount > TotalItems) {
				break;
			}
		}
		outStream.endFrameVarSizeWord();
	}

	public boolean resetSM() {
		if (OriginalWeapon > -1) {
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

	public boolean resetStairs() {
		stairs = 0;
		skillX = -1;
		skillY = -1;
		stairDistance = 1;
		stairDistanceAdd = 0;
		IsUsingSkill = false;
		return true;
	}

	public void resetTItems(int WriteFrame) {
		outStream.createFrameVarSizeWord(53);
		outStream.writeWord(WriteFrame);
		int len = offeredItems.toArray().length;
		int current = 0;
		outStream.writeWord(len);
		for (GameItem item : offeredItems) {
			if (item.amount > 254) {
				outStream.writeByte(255); // item's stack count. if over 254,
				// write byte 255
				outStream.writeDWord_v2(item.amount); // and then the real
				// value with
				// writeDWord_v2
			} else {
				outStream.writeByte(item.amount);
			}
			outStream.writeWordBigEndianA(item.id + 1); // item id
			current++;
		}
		if (current < 27) {
			for (int i = current; i < 28; i++) {
				outStream.writeByte(1);
				outStream.writeWordBigEndianA(-1);
			}
		}
		outStream.endFrameVarSizeWord();
	}

	public void resetTrade() {
		offeredItems.clear();
		inTrade = false;
		trade_reqId = 0;
		canOffer = true;
		tradeConfirmed = false;
		tradeConfirmed2 = false;
		closeInterface();
		tradeResetNeeded = false;
		sendQuest("Are you sure you want to make this trade?", 3535);
	}

	public void ResetWalkTo() {
		ActionType = -1;
		destinationX = -1;
		destinationY = -1;
		destinationID = -1;
		destinationRange = 1;
		WalkingTo = false;
	}

	public boolean resetWC() {
		woodcutting[0] = 0;
		woodcutting[1] = 0;
		woodcutting[2] = 0;
		woodcutting[4] = 0;
		woodcutting[5] = 2;
		skillX = -1;
		skillY = -1;
		IsCutting = false;
		IsUsingSkill = false;
		resetAnimation();
		return true;
	}

	public void restorePot() {

		playerLevel[0] = getLevelForXP(playerXP[0]);
		sendFrame126("" + playerLevel[0] + "", 4004);
		playerLevel[1] = getLevelForXP(playerXP[1]);
		sendFrame126("" + playerLevel[1] + "", 4008);
		playerLevel[2] = getLevelForXP(playerXP[2]);
		sendFrame126("" + playerLevel[2] + "", 4006);
		playerLevel[4] = getLevelForXP(playerXP[4]);
		sendFrame126("" + playerLevel[4] + "", 4010);
		playerLevel[6] = getLevelForXP(playerXP[6]);
		sendFrame126("" + playerLevel[6] + "", 4014);
		playerLevel[7] = getLevelForXP(playerXP[7]);
		sendFrame126("" + playerLevel[7] + "", 4034);
		playerLevel[8] = getLevelForXP(playerXP[8]);
		sendFrame126("" + playerLevel[8] + "", 4038);
		playerLevel[9] = getLevelForXP(playerXP[9]);
		sendFrame126("" + playerLevel[9] + "", 4026);
		playerLevel[10] = getLevelForXP(playerXP[10]);
		sendFrame126("" + playerLevel[10] + "", 4032);
		playerLevel[11] = getLevelForXP(playerXP[11]);
		sendFrame126("" + playerLevel[11] + "", 4036);
		playerLevel[12] = getLevelForXP(playerXP[12]);
		sendFrame126("" + playerLevel[12] + "", 4024);
		playerLevel[13] = getLevelForXP(playerXP[13]);
		sendFrame126("" + playerLevel[13] + "", 4030);
		playerLevel[14] = getLevelForXP(playerXP[14]);
		sendFrame126("" + playerLevel[14] + "", 4028);
		playerLevel[15] = getLevelForXP(playerXP[15]);
		sendFrame126("" + playerLevel[15] + "", 4020);
		playerLevel[16] = getLevelForXP(playerXP[16]);
		sendFrame126("" + playerLevel[16] + "", 4018);
		playerLevel[17] = getLevelForXP(playerXP[17]);
		sendFrame126("" + playerLevel[17] + "", 4022);
		playerLevel[20] = getLevelForXP(playerXP[20]);
		sendFrame126("" + playerLevel[20] + "", 4152);
	}

	public void robelf() {
		sendMessage("You pickpocket the elf.");
		addItem(995, 350);
		addSkillXP(353, 17);
		int EnemyX2 = server.npcHandler.npcs[NPCSlot].absX;
		int EnemyY2 = server.npcHandler.npcs[NPCSlot].absY;

		TurnPlayerTo(EnemyX2, EnemyY2);
		updateRequired = true;
		appearanceUpdateRequired = true;
	}

	public void robfail() {
		EntangleDelay = 40;
	}

	public void robfarmer() {
		long now = System.currentTimeMillis();
		if (now - lastAction < 1500)
			return;
		lastAction = now;
		sendMessage("You pickpocket the farmer.");
		addItem(314, misc.random(9));
		addSkillXP(800, 17);
		int EnemyX2 = server.npcHandler.npcs[NPCSlot].absX;
		int EnemyY2 = server.npcHandler.npcs[NPCSlot].absY;

		TurnPlayerTo(EnemyX2, EnemyY2);
		updateRequired = true;
		appearanceUpdateRequired = true;

	}

	public void robgnome() {
		sendMessage("You pickpocket the gnome.");
		addItem(995, 400);
		addSkillXP(198, 17);
		int EnemyX2 = server.npcHandler.npcs[NPCSlot].absX;
		int EnemyY2 = server.npcHandler.npcs[NPCSlot].absY;

		TurnPlayerTo(EnemyX2, EnemyY2);
		updateRequired = true;
		appearanceUpdateRequired = true;
	}

	public void robguard() {
		sendMessage("You pickpocket the gaurd.");
		addItem(995, 30);
		addSkillXP(47, 17);
		int EnemyX2 = server.npcHandler.npcs[NPCSlot].absX;
		int EnemyY2 = server.npcHandler.npcs[NPCSlot].absY;

		TurnPlayerTo(EnemyX2, EnemyY2);
		updateRequired = true;
		appearanceUpdateRequired = true;
	}

	public void robhero() {
		sendMessage("You pickpocket the hero.");
		addItem(995, 300);
		addSkillXP(274, 17);
		int EnemyX2 = server.npcHandler.npcs[NPCSlot].absX;
		int EnemyY2 = server.npcHandler.npcs[NPCSlot].absY;

		TurnPlayerTo(EnemyX2, EnemyY2);
		updateRequired = true;
		appearanceUpdateRequired = true;
	}

	public void robknight() {
		sendMessage("You pickpocket the knight.");
		addItem(995, 50);
		addSkillXP(85, 17);
		int EnemyX2 = server.npcHandler.npcs[NPCSlot].absX;
		int EnemyY2 = server.npcHandler.npcs[NPCSlot].absY;

		TurnPlayerTo(EnemyX2, EnemyY2);
		updateRequired = true;
		appearanceUpdateRequired = true;
	}

	public void robman() {

		sendMessage("You pickpocket the man.");
		addItem(995, 3);
		addSkillXP(500, 17);
		int EnemyX2 = server.npcHandler.npcs[NPCSlot].absX;
		int EnemyY2 = server.npcHandler.npcs[NPCSlot].absY;

		TurnPlayerTo(EnemyX2, EnemyY2);

	}

	public void robmasterfarmer() {
		sendMessage("You pickpocket the master farmer.");
		addItem(995, 40);
		addSkillXP(43, 17);
		int EnemyX2 = server.npcHandler.npcs[NPCSlot].absX;
		int EnemyY2 = server.npcHandler.npcs[NPCSlot].absY;

		TurnPlayerTo(EnemyX2, EnemyY2);
		updateRequired = true;
		appearanceUpdateRequired = true;
	}

	public void robpaladin() {
		sendMessage("You pickpocket the paladin.");
		addItem(995, 80);
		addSkillXP(152, 17);
		int EnemyX2 = server.npcHandler.npcs[NPCSlot].absX;
		int EnemyY2 = server.npcHandler.npcs[NPCSlot].absY;

		TurnPlayerTo(EnemyX2, EnemyY2);
		updateRequired = true;
		appearanceUpdateRequired = true;
	}

	public void robrogue() {
		sendMessage("You pickpocket the rogue.");
		addItem(995, 45);
		addSkillXP(36, 17);
		int EnemyX2 = server.npcHandler.npcs[NPCSlot].absX;
		int EnemyY2 = server.npcHandler.npcs[NPCSlot].absY;

		TurnPlayerTo(EnemyX2, EnemyY2);
		updateRequired = true;
		appearanceUpdateRequired = true;
	}

	public void robwarrior() {
		sendMessage("You pickpocket the warrior.");
		addItem(995, 18);
		addSkillXP(4600, 17);
		int EnemyX2 = server.npcHandler.npcs[NPCSlot].absX;
		int EnemyY2 = server.npcHandler.npcs[NPCSlot].absY;

		TurnPlayerTo(EnemyX2, EnemyY2);
		updateRequired = true;
		appearanceUpdateRequired = true;
	}

	public void robwatchman() {
		sendMessage("You pickpocket the watchman.");
		addItem(995, 60);
		addSkillXP(138, 17);
		int EnemyX2 = server.npcHandler.npcs[NPCSlot].absX;
		int EnemyY2 = server.npcHandler.npcs[NPCSlot].absY;

		TurnPlayerTo(EnemyX2, EnemyY2);
		updateRequired = true;
		appearanceUpdateRequired = true;
	}

	public void run() {
		// we just accepted a new connection - handle the login stuff
		isActive = false;
		long serverSessionKey = 0, clientSessionKey = 0;

		// randomize server part of the session key
		serverSessionKey = ((long) (java.lang.Math.random() * 99999999D) << 32)
				+ (long) (java.lang.Math.random() * 99999999D);

		try {
			fillInStream(2);
			if (inStream.readUnsignedByte() != 14) {
				shutdownError("Expected login Id 14 from client.");
				disconnected = true;
				return;
			}
			// this is part of the usename. Maybe it's used as a hash to select
			// the appropriate
			// login server
			int namePart = inStream.readUnsignedByte();
			for (int i = 0; i < 8; i++) {
				out.write(1);
			} // is being ignored by the client

			// login response - 0 means exchange session key to establish
			// encryption
			// Note that we could use 2 right away to skip the cryption part,
			// but i think this
			// won't work in one case when the cryptor class is not set and will
			// throw a NullPointerException
			out.write(0);

			// send the server part of the session Id used (client+server part
			// together are used as cryption key)
			outStream.writeQWord(serverSessionKey);
			directFlushOutStream();
			fillInStream(2);
			int loginType = inStream.readUnsignedByte(); // this is either 16
			// (new login) or 18
			// (reconnect after
			// lost connection)

			if ((loginType != 16) && (loginType != 18)) {
				shutdownError("Unexpected login type " + loginType);
				return;
			}
			int loginPacketSize = inStream.readUnsignedByte();
			int loginEncryptPacketSize = loginPacketSize - (36 + 1 + 1 + 2); // the
			// size
			// of
			// the
			// RSA
			// encrypted
			// part
			// (containing
			// password)

			// misc.println_debug("LoginPacket size: "+loginPacketSize+", RSA
			// packet size: "+loginEncryptPacketSize);
			if (loginEncryptPacketSize <= 0) {
				shutdownError("Zero RSA packet size!");
				return;
			}
			fillInStream(loginPacketSize);
			if ((inStream.readUnsignedByte() != 255)
					|| (inStream.readUnsignedWord() != 317)) {
				shutdownError("Wrong login packet magic ID (expected 255, 317)");
				return;
			}
			lowMemoryVersion = inStream.readUnsignedByte();
			// misc.println_debug("Client type: "+((lowMemoryVersion==1) ? "low"
			// : "high")+" memory version");
			for (int i = 0; i < 9; i++) {
				String junk = Integer.toHexString(inStream.readDWord());
				// misc.println_debug("dataFileVersion["+i+"]:
				// 0x"+Integer.toHexString(inStream.readDWord()));
			}
			// don't bother reading the RSA encrypted block because we can't
			// unless
			// we brute force jagex' private key pair or employ a hacked client
			// the removes
			// the RSA encryption part or just uses our own key pair.
			// Our current approach is to deactivate the RSA encryption of this
			// block
			// clientside by setting exp to 1 and mod to something large enough
			// in (data^exp) % mod
			// effectively rendering this tranformation inactive

			loginEncryptPacketSize--; // don't count length byte
			int tmp = inStream.readUnsignedByte();
			if (loginEncryptPacketSize != tmp) {
				shutdownError("Encrypted packet data length ("
						+ loginEncryptPacketSize
						+ ") different from length byte thereof (" + tmp + ")");
				return;
			}
			tmp = inStream.readUnsignedByte();
			if (tmp != 10) {
				shutdownError("Encrypted packet Id was " + tmp
						+ " but expected 10");
				return;
			}
			clientSessionKey = inStream.readQWord();
			serverSessionKey = inStream.readQWord();

			// misc.println("UserId: "+inStream.readDWord());
			int junk = inStream.readDWord();
			playerName = inStream.readString();
			int expectedUid = 1;
			if (junk == expectedUid) {
				println("Devolution.com client detected!");
				officialClient = true;
			}
			uid = junk;
			if ((playerName == null) || (playerName.length() == 0)) {
				playerName = "player" + playerId;
			}
			playerPass = inStream.readString();

			try {
				playerServer = inStream.readString();
			} catch (Exception e) {
				playerServer = "rs2.servegame.org";
			}

			playerName = playerName.toLowerCase();
			playerPass = playerPass.toLowerCase();

			char[] validChars = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
					'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u',
					'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G',
					'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S',
					'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '1', '2', '3', '4', '5',
					'6', '7', '8', '9', '0', '_', ' ' };
			playerName = playerName.trim();
			int sessionKey[] = new int[4];

			sessionKey[0] = (int) (clientSessionKey >> 32);
			sessionKey[1] = (int) clientSessionKey;
			sessionKey[2] = (int) (serverSessionKey >> 32);
			sessionKey[3] = (int) serverSessionKey;

			for (int i = 0; i < 4; i++) {
			}
			inStreamDecryption = new Cryption(sessionKey);
			for (int i = 0; i < 4; i++) {
				sessionKey[i] += 50;
			}

			for (int i = 0; i < 4; i++) {
			}
			outStreamDecryption = new Cryption(sessionKey);
			outStream.packetEncryption = outStreamDecryption;

			returnCode = 2;
			for (int i = 0; i < playerName.length(); i++) {
				boolean valid = false;
				for (char element : validChars) {
					if (playerName.charAt(i) == element) {
						valid = true;
						// break;
					}
				}
				if (!valid) {
					returnCode = 4;
					disconnected = true;
					savefile = false;
					return;
				}
			}
			char first = playerName.charAt(0);
			properName = Character.toUpperCase(first)
					+ playerName.substring(1, playerName.length());
			playerName = properName;
			if (PlayerHandler.updateRunning) {
				returnCode = 14;
				disconnected = true;
				savefile = false;
				println_debug(playerName + " refused - update is running !");
				return;
			}
			if (!server.loginServerConnected) {
				returnCode = 8;
				disconnected = true;
				return;
			}

			boolean found = false;
			int type = 5;

			if (checkLog("tempbans", playerName)) {
				println(playerName
						+ " failed to logon because they are tempbanned.");
				returnCode = 4;
				disconnected = true;
				return;
			}

			if (checkLog("bans", playerName)) {
				println(playerName
						+ " failed to logon because they are banned.");
				returnCode = 4;
				disconnected = true;
				return;
			}

			// uncomment this code below to stop multiple logins from 1
			// computer.

			/*
			 * for(int i = 0; i < server.playerHandler.players.length; i++){
			 * Player p = server.playerHandler.players[i]; if(p != null &&
			 * !p.disconnected && p.connectedFrom.equals(connectedFrom) &&
			 * playerId != p.playerId && !connectedFrom.equals("localhost")){
			 * sendMessage("Address in use!"); returnCode = 9; disconnected =
			 * true; return; } }
			 */

			int loadgame = loadgame(playerName, passHash(playerPass));

			if (loadgame == 3) {
				// wrong password.
				returnCode = 3;
				disconnected = true;
				return;
			}

			if (server.playerHandler.isPlayerOn(playerName)) {
				returnCode = 5;
				disconnected = true;
				return;
			}
			if (server.enforceClient && !officialClient) {
				println("Invalid client!");
				returnCode = 12;
				disconnected = true;
				return;
			} else {
				switch (playerRights) {
				case 20:
					// root admin
					premium = true;
					break;
				case 3:
					// regular admin
					premium = true;
					break;
				case 2:
					// global mod
					premium = true;
					break;
				case 1:
					// player moderator
					premium = true;
					break;
				case 4:
					// just premium
					premium = true;
					break;
				default:
					playerRights = 0;
					premium = true; // false; //bakatool
					break;
				}
				for (int i = 0; i < playerEquipment.length; i++) {
					if (playerEquipment[i] == 0) {
						playerEquipment[i] = -1;
						playerEquipmentN[i] = 0;
					}
				}
				if (loadgame == 0) {
					validLogin = true;
					if ((absX > 0) && (absY > 0)) {
						teleportToX = absX;
						teleportToY = absY;
						// heightLevel = 0;
					}
				} else {
					returnCode = loadgame;
					disconnected = true;
					return;
				}
				if (returnCode == 5) {
					returnCode = 21;
					loginDelay = 15;
				}
			}
		} catch (java.lang.Exception __ex) {
			server.logError(__ex.getMessage());
			__ex.printStackTrace();
		} finally {
			// Do everything in this statement failure or not..(IDK WHY SERVERS
			// DIDN'T HAVE THIS!) -bakatool
			try {
				if (playerId == -1)
					out.write(7);
				// "This world is full."
				else if (playerServer.equals("INVALID"))
					out.write(10);
				else
					out.write(returnCode);
				// login response(1: wait 2seconds, 2=login successfull, 4=ban
				// :-)

				if (returnCode == 21)
					out.write(loginDelay);

				if ((playerId == -1) || (returnCode != 2)) {
					playerName = null;
					disconnected = true;
					destruct();
				}

				// mod/admin level crown fix -bakatool
				if (playerRights == 3)
					out.write(2);
				else
					out.write(playerRights);

				out.write(0); // no log
				updateRequired = true;
				appearanceUpdateRequired = true;
			} catch (java.lang.Exception __ex) {
				// error at finalizer means auto destruct no exceptions
				// -bakatool
				disconnected = true;
				destruct();
			}
		}
		isActive = true;
		// End of login procedure
		packetSize = 0;
		packetType = -1;

		readPtr = 0;
		writePtr = 0;

		int numBytesInBuffer, offset;

		while (!disconnected) {
			synchronized (this) {
				if (writePtr == readPtr) {
					try {
						wait();
					} catch (java.lang.InterruptedException _ex) {
					}
				}

				if (disconnected) {
					return;
				}

				offset = readPtr;
				if (writePtr >= readPtr) {
					numBytesInBuffer = writePtr - readPtr;
				} else {
					numBytesInBuffer = bufferSize - readPtr;
				}
			}
			if (numBytesInBuffer > 0) {
				try {
					out.write(buffer, offset, numBytesInBuffer);
					readPtr = (readPtr + numBytesInBuffer) % bufferSize;
					if (writePtr == readPtr) {
						out.flush();
					}
				} catch (java.net.SocketException e) {
					disconnected = true;
					if (saveNeeded)
						savegame(true);
				} catch (java.lang.Exception __ex) {
					server.logError(__ex.getMessage());
					disconnected = true;
					if (saveNeeded)
						savegame(true);
				}
			}
		}
	}

	public boolean runeCheck(int spell) {
		int[] runeId = { 6430, 6432, 565, 6428, 6422, 566, 6434, 6424 };
		for (int i = 0; i < server.runesRequired[spell].length; i++) {
			if (server.runesRequired[spell][i] > 0) {
				if (!playerHasItem(runeId[i], server.runesRequired[spell][i])) {
					return false;
				}
			}
		}
		return true;
	}

	public void savegame(boolean logout) {

		if ((playerName == null) || !validClient) {
			saveNeeded = false;
			return;
		}

		if (logout) {
			if (fightId > 0) {
				client f = (client) server.playerHandler.players[fightId];
				if (f != null) {
					f.fighting = false;
					f.hits = 0;
				}
			}

		}
		if (logout && inTrade) {
			declineTrade();
		}

		BufferedWriter characterfile = null;
		try {
			characterfile = new BufferedWriter(new FileWriter("./characters/"
					+ playerName + ".txt"));
			/* ACCOUNT */
			characterfile.write("[ACCOUNT]", 0, 9);
			characterfile.newLine();
			characterfile.write("character-username = ", 0, 21);
			characterfile.write(playerName, 0, playerName.length());
			characterfile.newLine();
			characterfile.write("character-password = ", 0, 21);
			characterfile.write(passHash(playerPass), 0, passHash(playerPass)
					.length());
			characterfile.newLine();
			characterfile.newLine();
			/* CHARACTER */
			characterfile.write("[CHARACTER]", 0, 11);
			characterfile.newLine();
			characterfile.write("character-height = ", 0, 19);
			characterfile.write(Integer.toString(heightLevel), 0, Integer
					.toString(heightLevel).length());
			characterfile.newLine();
			characterfile.write("character-posx = ", 0, 17);
			characterfile.write(Integer.toString(absX == -1 ? 2611 : absX), 0,
					Integer.toString(absX == -1 ? 2611 : absX).length());
			characterfile.newLine();
			characterfile.write("character-posy = ", 0, 17);
			characterfile.write(Integer.toString(absY == -1 ? 3093 : absY), 0,
					Integer.toString(absY == -1 ? 3093 : absY).length());
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
			characterfile.write(playerLastConnect, 0, playerLastConnect
					.length());
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
			characterfile.write("character-ancients = ", 0, 21);
			characterfile.write(Integer.toString(ancients), 0, Integer
					.toString(ancients).length());
			characterfile.newLine();
			characterfile.write("character-rating = ", 0, 19);
			characterfile.write(Integer.toString(rating), 0, Integer.toString(
					rating).length());
			characterfile.newLine();
			characterfile.newLine();
			/* EQUIPMENT */
			characterfile.write("[EQUIPMENT]", 0, 11);
			characterfile.newLine();
			for (int i = 0; i < playerEquipment.length; i++) {
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
			/* LOOK */
			characterfile.write("[LOOK]", 0, 6);
			characterfile.newLine();
			int[] Looks = getLook();
			for (int i = 0; i < Looks.length; i++) {
				characterfile.write("character-look = ", 0, 17);
				characterfile.write(Integer.toString(i), 0, Integer.toString(i)
						.length());
				characterfile.write("	", 0, 1);
				characterfile.write(Integer.toString(Looks[i]), 0, Integer
						.toString(Looks[i]).length());
				characterfile.newLine();
			}
			characterfile.newLine();
			/* SKILLS */
			characterfile.write("[SKILLS]", 0, 8);
			characterfile.newLine();
			for (int i = 0; i < playerLevel.length; i++) {
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
			/* ITEMS */
			characterfile.write("[ITEMS]", 0, 7);
			characterfile.newLine();
			for (int i = 0; i < playerItems.length; i++) {
				if (playerItems[i] > 0) {
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
			/* BANK */
			characterfile.write("[BANK]", 0, 6);
			characterfile.newLine();
			for (int i = 0; i < bankItems.length; i++) {
				if (bankItems[i] > 0) {
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
			/* FRIENDS */
			characterfile.write("[FRIENDS]", 0, 9);
			characterfile.newLine();
			for (int i = 0; i < friends.length; i++) {
				if (friends[i] > 0) {
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
			/* IGNORES */
			characterfile.write("[IGNORES]", 0, 9);
			characterfile.newLine();
			for (int i = 0; i < ignores.length; i++) {
				if (ignores[i] > 0) {
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
			/* EOF */
			characterfile.write("[EOF]", 0, 5);
			characterfile.newLine();
			characterfile.newLine();
			characterfile.close();
		} catch (IOException ioexception) {
			misc.println(playerName + ": error writing file.");
		}
		saveNeeded = false;
	}

	public void saveStats(boolean logout) {

		String[] parts = new String[] { "pGender", "pHead", "pTorso", "pArms",
				"pHands", "pLegs", "pFeet", "pBeard", "pHairC", "pTorsoC",
				"pLegsC", "pFeetC", "pSkinC" };

		try {
			int[] look = { pGender, pHead, pBeard, pTorso, pArms, pHands,
					pLegs, pFeet, pHairC, pTorsoC, pLegsC, pFeetC, pSkinC,
					playerLook[0], playerLook[1], playerLook[2], playerLook[3],
					playerLook[4], playerLook[5] };
			boolean DoInsert = true;
			int ID = -1;
			int ID2 = -1;
			int test1 = -1;
			int test2 = -1;
			// mysql_connect();
			long allxp = 0;
			for (int i = 0; i < 21; i++) {
				if (i != 18) {
					allxp += playerXP[i];
				}
			}
			int totallvl = playerLevel[0] + playerLevel[1] + playerLevel[2]
					+ playerLevel[3] + playerLevel[4] + playerLevel[5]
					+ playerLevel[6] + playerLevel[7] + playerLevel[8]
					+ playerLevel[9] + playerLevel[10] + playerLevel[11]
					+ playerLevel[12] + playerLevel[13] + playerLevel[14]
					+ playerLevel[15] + playerLevel[16] + playerLevel[17]
					+ playerLevel[18] + playerLevel[19] + playerLevel[20];
			int combatLevel = (int) ((double) playerLevel[0] * 0.32707
					+ (double) playerLevel[1] * 0.249 + (double) playerLevel[2]
					* 0.324 + (double) playerLevel[3] * 0.25 + (double) playerLevel[5] * 0.124);

			String online = "online = 0";
			if (!logout)
				online = "online = " + server.world;
			else
				saveNeeded = false;

		} catch (Exception e) {
		}
	}

	/* Shops */
	public boolean sellItem(int itemID, int fromSlot, int amount) {
		if ((amount > 0) && (itemID == (playerItems[fromSlot] - 1))) {
			if (server.shopHandler.ShopSModifier[MyShopID] > 1) {
				boolean IsIn = false;

				for (int i = 0; i <= server.shopHandler.ShopItemsStandard[MyShopID]; i++) {
					if (itemID == (server.shopHandler.ShopItems[MyShopID][i] - 1)) {
						IsIn = true;
						break;
					}
				}
				if (IsIn == false) {
					sendMessage("You cannot sell " + getItemName(itemID)
							+ " in this store.");
					return false;
				}
			}
			if (Item.itemSellable[(playerItems[fromSlot] - 1)] == false) {
				sendMessage("I cannot sell " + getItemName(itemID) + ".");
				return false;
			}
			if ((amount > playerItemsN[fromSlot])
					&& ((Item.itemIsNote[(playerItems[fromSlot] - 1)] == true) || (Item.itemStackable[(playerItems[fromSlot] - 1)] == true))) {
				amount = playerItemsN[fromSlot];
			} else if ((amount > GetXItemsInBag(itemID))
					&& (Item.itemIsNote[(playerItems[fromSlot] - 1)] == false)
					&& (Item.itemStackable[(playerItems[fromSlot] - 1)] == false)) {
				amount = GetXItemsInBag(itemID);
			}
			double ShopValue;
			double TotPrice;
			int TotPrice2;
			int Overstock;

			for (int i = amount; i > 0; i--) {
				TotPrice2 = (int) Math.floor(GetItemShopValue(itemID, 1,
						fromSlot));
				if (freeSlots() > 0) {
					if (Item.itemIsNote[itemID] == false) {
						deleteItem(itemID, GetItemSlot(itemID), 1);
					} else {
						deleteItem(itemID, fromSlot, 1);
					}
					addItem(995, TotPrice2);
					addShopItem(itemID, 1);
				} else {
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

	public void sendFrame126(String s, int id) {
		outStream.createFrameVarSizeWord(126);
		outStream.writeString(s);
		outStream.writeWordA(id);
		outStream.endFrameVarSizeWord();
		flushOutStream();
	}

	/*
	 * public boolean stakeItem2(int itemID, int fromSlot, int amount) {
	 * if(!Item.itemStackable[itemID] && amount > 1){ for(int a = 1; a <=
	 * amount; a++){ int slot = findItem(itemID, playerItems, playerItemsN);
	 * if(slot >= 0){ tradeItem(itemID, slot, 1); } } } for(int i = 0; i <
	 * noTrade.length; i++){ if(itemID == noTrade[i] || itemID == noTrade[i] + 1 ||
	 * premiumItem(itemID)){ sendMessage("You can't trade that item");
	 * declineTrade(); return false; } } client other = getClient(duel_with); if
	 * (!inDuel || !validClient(duel_with) || !canOffer){ declineDuel(); return
	 * false; } if(!playerHasItem(itemID, amount)){ return false; }
	 * if(Item.itemStackable[itemID]) offeredItems.add(new GameItem(itemID,
	 * amount)); else offeredItems.add(new GameItem(itemID, 1));
	 * deleteItem(itemID, fromSlot, amount); resetItems(3214);
	 * duelz.resetItems(3214); resetItems(3322); refreshDuelScreen();
	 * duelz.resetItems(3322); duelz.refreshDuelScreen(); duelz.sendFrame126("",
	 * 6684); return false; }
	 */

	public void sendFrame164(int Frame) {
		outStream.createFrame(164);
		outStream.writeWordBigEndian_dup(Frame);
		flushOutStream();
	}

	public void sendFrame171(int MainFrame, int SubFrame) {
		outStream.createFrame(171);
		outStream.writeByte(MainFrame);
		outStream.writeWord(SubFrame);
		flushOutStream();
	}

	public void sendFrame185(int Frame) {
		outStream.createFrame(185);
		outStream.writeWordBigEndianA(Frame);
		flushOutStream();
	}

	public void sendFrame200(int MainFrame, int SubFrame) {
		outStream.createFrame(200);
		outStream.writeWord(MainFrame);
		outStream.writeWord(SubFrame);
		flushOutStream();
	}

	public void sendFrame246(int MainFrame, int SubFrame, int SubFrame2) {
		outStream.createFrame(246);
		outStream.writeWordBigEndian(MainFrame);
		outStream.writeWord(SubFrame);
		outStream.writeWord(SubFrame2);
		flushOutStream();
	}

	public void sendFrame248(int MainFrame, int SubFrame) {
		outStream.createFrame(248);
		outStream.writeWordA(MainFrame);
		outStream.writeWord(SubFrame);
		flushOutStream();
	}

	public void sendFrame75(int MainFrame, int SubFrame) {
		outStream.createFrame(75);
		outStream.writeWordBigEndianA(MainFrame);
		outStream.writeWordBigEndianA(SubFrame);
		flushOutStream();
	}

	// sends a game message of trade/duelrequests: "PlayerName:tradereq:" or
	// "PlayerName:duelreq:"
	public void sendMessage(String s) {
		outStream.createFrameVarSize(253);
		outStream.writeString(s);
		outStream.endFrameVarSize();
	}

	public void sendpm(long name, int rights, byte[] chatmessage,
			int messagesize) {
		outStream.createFrameVarSize(196);
		outStream.writeQWord(name);
		outStream.writeDWord(handler.lastchatid++); // must be different for
		// each message
		outStream.writeByte(rights);
		outStream.writeBytes(chatmessage, messagesize, 0);
		outStream.endFrameVarSize();
	}

	public void sendQuest(String s, int id) {
		try {
			outStream.createFrameVarSizeWord(126);
			outStream.writeString(s);
			outStream.writeWordA(id);
			outStream.endFrameVarSizeWord();
		} catch (Exception e) {
			server.logError(e.getMessage());
		}
	}

	public void sendQuestSomething(int id) {
		outStream.createFrame(79);
		outStream.writeWordBigEndian(id);
		outStream.writeWordA(0);
		flushOutStream();
	}

	public void SendWeapon(int Weapon, String WeaponName) {
		String WeaponName2 = WeaponName.replaceAll("Bronze", "");

		WeaponName2 = WeaponName2.replaceAll("Iron", "");
		WeaponName2 = WeaponName2.replaceAll("Steel", "");
		WeaponName2 = WeaponName2.replaceAll("Scythe", "");
		WeaponName2 = WeaponName2.replaceAll("Black", "");
		WeaponName2 = WeaponName2.replaceAll("Mithril", "");
		WeaponName2 = WeaponName2.replaceAll("Adamant", "");
		WeaponName2 = WeaponName2.replaceAll("Rune", "");
		WeaponName2 = WeaponName2.replaceAll("Granite", "");
		WeaponName2 = WeaponName2.replaceAll("Dragon", "");
		WeaponName2 = WeaponName2.replaceAll("Crystal", "");
		WeaponName2 = WeaponName2.trim();
		if (WeaponName.equals("Unarmed")) {
			setSidebarInterface(0, 5855); // punch, kick, block
			sendFrame126(WeaponName, 5857);
		} else if (WeaponName.endsWith("whip")) {
			setSidebarInterface(0, 12290); // flick, lash, deflect
			sendFrame246(12291, 200, Weapon);
			sendFrame126(WeaponName, 12293);
		} else if (WeaponName.endsWith("Scythe")) {
			setSidebarInterface(0, 776); // flick, lash, deflect
			sendFrame246(12291, 200, Weapon);
			sendFrame126(WeaponName, 778);
		} else if (WeaponName.endsWith("bow")) {
			setSidebarInterface(0, 1764); // accurate, rapid, longrange
			sendFrame246(1765, 200, Weapon);
			sendFrame126(WeaponName, 1767);
		} else if (WeaponName.startsWith("Staff")
				|| WeaponName.endsWith("staff")) {
			setSidebarInterface(0, 328); // spike, impale, smash, block
			sendFrame246(329, 200, Weapon);
			sendFrame126(WeaponName, 331);
		} else if (WeaponName2.startsWith("dart")) {
			setSidebarInterface(0, 4446); // accurate, rapid, longrange
			sendFrame246(4447, 200, Weapon);
			sendFrame126(WeaponName, 4449);
		} else if (WeaponName2.startsWith("dagger")) {
			setSidebarInterface(0, 2276); // stab, lunge, slash, block
			sendFrame246(2277, 200, Weapon);
			sendFrame126(WeaponName, 2279);
		} else if (WeaponName2.startsWith("pickaxe")) {
			setSidebarInterface(0, 5570); // spike, impale, smash, block
			sendFrame246(5571, 200, Weapon);
			sendFrame126(WeaponName, 5573);
		} else if (WeaponName2.startsWith("axe")
				|| WeaponName2.startsWith("battleaxe")) {
			setSidebarInterface(0, 1698); // chop, hack, smash, block
			sendFrame246(1699, 200, Weapon);
			sendFrame126(WeaponName, 1701);
		} else if (WeaponName2.startsWith("Axe")
				|| WeaponName2.startsWith("Battleaxe")) {
			setSidebarInterface(0, 1698); // chop, hack, smash, block
			sendFrame246(1699, 200, Weapon);
			sendFrame126(WeaponName, 1701);
		} else if (WeaponName2.startsWith("halberd")) {
			setSidebarInterface(0, 8460); // jab, swipe, fend
			sendFrame246(8461, 200, Weapon);
			sendFrame126(WeaponName, 8463);
		} else if (WeaponName2.startsWith("spear")) {
			setSidebarInterface(0, 4679); // lunge, swipe, pound, block
			sendFrame246(4680, 200, Weapon);
			sendFrame126(WeaponName, 4682);
		} else {
			setSidebarInterface(0, 2423); // chop, slash, lunge, block
			sendFrame246(2424, 200, Weapon);
			sendFrame126(WeaponName, 2426);
		}
	}

	public void ServerHelp() {
		sendFrame126("@dre@Devolution Server 7.0 - Help Menu", 8144);
		clearQuestInterface();
		sendFrame126("", 8145);
		sendFrame126("@dbl@Welcome to @cya@Devolution Server 7.0", 8146);
		sendFrame126("@dbl@Devolution Server 7.0 - No spawning", 8147);
		sendFrame126("@dre@Site:  @dbl@http://moparscape.org/smf@dbl@", 8148);
		sendFrame126(
				"@dre@Obtaining items:  @dbl@All good gear/cash are dropped by npcs.",
				8149);
		sendFrame126(
				"@dre@Noob Monsters:  @dbl@Rats by bank and Soldiers by bank.",
				8150);
		sendFrame126(
				"@dre@Mid Monsters:  @dbl@Druids, Moss giants, west of rats.",
				8151);
		sendFrame126(
				"@dre@High Monsters: @dbl@Heroes (far west), Dad (70 cb+ Boss).",
				8152);
		sendFrame126(
				"@dre@Dungeon (red key):  @dbl@Fire giants, San Tojalon (Boss).",
				8153);
		sendFrame126("@lre@- - - PKING WITH DROPS! by Bakatool - - -", 8154);
		sendFrame126("@dre@Pking:  @dbl@Type ::pking to start pking a match!",
				8155);
		sendFrame126(
				"@dre@Pking if died @dbl@You may use Teleport in Aubury to come back.",
				8156);
		sendFrame126("@red@Warning! @dbl@You WILL lose items pking!", 8157);
		sendFrame126("", 8158);
		sendFrame126("@lre@Premium Member coming soon!", 8159);
		sendFrame126("", 8160);
		sendFrame126(
				"@dre@Phats/Crackers:  @dbl@Discontinued, you can buy off of players.",
				8161);
		sendFrame126("@dre@Other rares:  @dbl@Santa hats, discontinued.", 8162);
		sendFrame126("", 8163);
		sendFrame126("", 8164);
		sendFrame126("", 8165);
		sendFrame126("", 8166);
		sendFrame126("", 8167);
		sendFrame126("", 8168);
		sendFrame126("", 8169);
		sendFrame126("", 8170);
		sendFrame126("", 8170);
		sendFrame126("", 8170);
		sendQuestSomething(8143);
		showInterface(8134);
	}

	public void setAnimation(int i) {
		pEmote = i;
		updateRequired = true;
		appearanceUpdateRequired = true;
	}

	public void setChatOptions(int publicChat, int privateChat, int tradeBlock) {
		outStream.createFrame(206);
		outStream.writeByte(publicChat); // On = 0, Friends = 1, Off = 2,
		// Hide = 3
		outStream.writeByte(privateChat); // On = 0, Friends = 1, Off = 2
		outStream.writeByte(tradeBlock); // On = 0, Friends = 1, Off = 2
	}

	public void setClientConfig(int id, int state) {
		outStream.createFrame(36);
		outStream.writeWordBigEndian(id);
		outStream.writeByte(state);
	}

	public void setEquipment(int wearID, int amount, int targetSlot) {
		int Stat = playerDefence;

		if (targetSlot == playerWeapon) {
			Stat = playerAttack;
		}
		outStream.createFrameVarSizeWord(34);
		outStream.writeWord(1688);
		outStream.writeByte(targetSlot);
		outStream.writeWord((wearID + 1));
		if (amount > 254) {
			outStream.writeByte(255);
			outStream.writeDWord(amount);
		} else {
			outStream.writeByte(amount); // amount
		}
		outStream.endFrameVarSizeWord();

		if ((targetSlot == playerWeapon) && (wearID >= 0)) {
			SendWeapon(wearID, getItemName(wearID));
			// playerSE = 0x328;
			// playerSEW = 0x333;
			// playerSER = 0x338;
			// playerSEA = 0x326;
			if (Item.itemTwoHanded[wearID] == true) {
				playerSE = 0x811;
				playerSEW = 0x67F;
				playerSER = 0x680;
			}
			if (wearID == 4747) {
				// Torag Hammers
				playerSEA = 0x814;
			}
			if ((wearID == 859) || (wearID == 839) || (wearID == 841)
					|| (wearID == 843) || (wearID == 845) || (wearID == 847)
					|| (wearID == 849) || (wearID == 851) || (wearID == 853)
					|| (wearID == 855) || (wearID == 857) || (wearID == 861)
					|| (wearID == 4212) || (wearID == 4734)) {
				// Crystal Bow
				playerSEA = 426;
			}

			pEmote = playerSE;
		}
		updateRequired = true;
		appearanceUpdateRequired = true;
	}

	public void setInterfaceWalkable(int ID) {
		outStream.createFrame(208);
		outStream.writeWordBigEndian_dup(ID);
		flushOutStream();
	}

	public void setLook(int[] parts) {
		if (parts.length != 19) {
			println("setLook:  Invalid array length!");
			return;
		}
		pGender = parts[0];
		pHead = parts[1];
		pBeard = parts[2];
		pTorso = parts[3];
		pArms = parts[4];
		pHands = parts[5];
		pLegs = parts[6];
		pFeet = parts[7];
		pHairC = parts[8];
		pTorsoC = parts[9];
		pLegsC = parts[10];
		pFeetC = parts[11];
		pSkinC = parts[12];
		playerLook[0] = parts[13];
		playerLook[1] = parts[14];
		playerLook[2] = parts[15];
		playerLook[3] = parts[16];
		playerLook[4] = parts[17];
		playerLook[5] = parts[18];
		apset = true;
		appearanceUpdateRequired = true;
		lookUpdate = true;
		updateRequired = true;
	}

	public void setSidebarInterface(int menuId, int form) {
		outStream.createFrame(71);
		outStream.writeWord(form);
		outStream.writeByteA(menuId);
	}

	public void setSkillLevel(int skillNum, int currentLevel, int XP) {
		if (skillNum == 0) {
			sendQuest("" + playerLevel[0] + "", 4004);
			sendQuest("" + getLevelForXP(playerXP[0]) + "", 4005);
		}
		if (skillNum == 2) {
			sendQuest("" + playerLevel[2] + "", 4006);
			sendQuest("" + getLevelForXP(playerXP[2]) + "", 4007);
		}
		if (skillNum == 1) {
			sendQuest("" + playerLevel[1] + "", 4008);
			sendQuest("" + getLevelForXP(playerXP[1]) + "", 4009);
		}
		if (skillNum == 4) {
			sendQuest("" + playerLevel[4] + "", 4010);
			sendQuest("" + getLevelForXP(playerXP[4]) + "", 4011);
		}
		if (skillNum == 5) {
			sendQuest("" + playerLevel[5] + "", 4012);
			sendQuest("" + getLevelForXP(playerXP[5]) + "", 4013);
		}
		if (skillNum == 6) {
			sendQuest("" + playerLevel[6] + "", 4014);
			sendQuest("" + getLevelForXP(playerXP[6]) + "", 4015);
		}
		if (skillNum == 3) {
			sendQuest("" + currentHealth + "", 4016);
			sendQuest("" + getLevelForXP(playerXP[3]) + "", 4017);
		}
		if (skillNum == 16) {
			sendQuest("" + playerLevel[16] + "", 4018);
			sendQuest("" + getLevelForXP(playerXP[16]) + "", 4019);
		}
		if (skillNum == 15) {
			sendQuest("" + playerLevel[15] + "", 4020);
			sendQuest("" + getLevelForXP(playerXP[15]) + "", 4021);
		}
		if (skillNum == 17) {
			sendQuest("" + playerLevel[17] + "", 4022);
			sendQuest("" + getLevelForXP(playerXP[17]) + "", 4023);
		}
		if (skillNum == 12) {
			sendQuest("" + playerLevel[12] + "", 4024);
			sendQuest("" + getLevelForXP(playerXP[12]) + "", 4025);
		}
		if (skillNum == 9) {
			sendQuest("" + playerLevel[9] + "", 4026);
			sendQuest("" + getLevelForXP(playerXP[9]) + "", 4027);
		}
		if (skillNum == 14) {
			sendQuest("" + playerLevel[14] + "", 4028);
			sendQuest("" + getLevelForXP(playerXP[14]) + "", 4029);
		}
		if (skillNum == 13) {
			sendQuest("" + playerLevel[13] + "", 4030);
			sendQuest("" + getLevelForXP(playerXP[13]) + "", 4031);
		}
		if (skillNum == 10) {
			sendQuest("" + playerLevel[10] + "", 4032);
			sendQuest("" + getLevelForXP(playerXP[10]) + "", 4033);
		}
		if (skillNum == 7) {
			sendQuest("" + playerLevel[7] + "", 4034);
			sendQuest("" + getLevelForXP(playerXP[7]) + "", 4035);
		}
		if (skillNum == 11) {
			sendQuest("" + playerLevel[11] + "", 4036);
			sendQuest("" + getLevelForXP(playerXP[11]) + "", 4037);
		}
		if (skillNum == 8) {
			sendQuest("" + playerLevel[8] + "", 4038);
			sendQuest("" + getLevelForXP(playerXP[8]) + "", 4039);
		}
		if (skillNum == 20) {
			sendQuest("" + playerLevel[20] + "", 4152);
			sendQuest("" + getLevelForXP(playerXP[20]) + "", 4153);
		}
		if (skillNum == 18) {
			sendQuest("" + playerLevel[18] + "", 12166);
			sendQuest("" + getLevelForXP(playerXP[18]) + "", 12167);
		}
		if (skillNum == 19) {
			sendQuest("" + playerLevel[19] + "", 13926);
			sendQuest("" + getLevelForXP(playerXP[19]) + "", 13927);
		} else {
			outStream.createFrame(134);
			outStream.writeByte(skillNum);
			outStream.writeDWord_v1(XP);
			outStream.writeByte(currentLevel);
		}
	}

	public void SetSmithing(int WriteFrame) {
		outStream.createFrameVarSizeWord(53);
		outStream.writeWord(WriteFrame);
		outStream.writeWord(Item.SmithingItems.length);
		for (int i = 0; i < Item.SmithingItems.length; i++) {
			Item.SmithingItems[i][0] += 1;
			if (Item.SmithingItems[i][1] > 254) {
				outStream.writeByte(255); // item's stack count. if over 254,
				// write byte 255
				outStream.writeDWord_v2(Item.SmithingItems[i][1]); // and then
				// the real
				// value
				// with
				// writeDWord_v2
			} else {
				outStream.writeByte(Item.SmithingItems[i][1]);
			}
			if ((Item.SmithingItems[i][0] > 20000)
					|| (Item.SmithingItems[i][0] < 0)) {
				playerItems[i] = 20000;
			}
			outStream.writeWordBigEndianA(Item.SmithingItems[i][0]); // item
			// id
		}
		outStream.endFrameVarSizeWord();
	}

	public void shaft() {
		closeInterface();
		if (playerHasItem(1511)) {
			if (playerHasItem(-1)) {
				deleteItem(1511, 1);
				addItem(52, 15);
				addSkillXP(150, playerFletching);
			} else {
				sendMessage("Your inventory is full!");
				resetAction();
			}
		} else {
			resetAction();
		}
	}

	public void showInterface(int interfaceid) {
		resetAction();
		outStream.createFrame(97);
		outStream.writeWord(interfaceid);
		flushOutStream();
	}

	public void shutdownError(String errorMessage) {
		// misc.println(": " + errorMessage);
		destruct();
	}

	public void smelt(int id) {
		setAnimation(0x383);
		smelt_id = id;
		smelting = true;
		int smelt_barId = -1;
		ArrayList<Integer> removed = new ArrayList<Integer>();
		if (smeltCount < 1) {
			resetAction(true);
			return;
		}
		smeltCount--;
		switch (id) {
		case 2349:
			// bronze
			if (playerHasItem(436) && playerHasItem(438)) {
				smelt_barId = 2349;
				removed.add(436);
				removed.add(438);
			}
			break;
		case 2351:
			// iron ore
			if (playerHasItem(440)) {
				int ran = misc.random(3);
				if ((ran == 1) || (ran == 2)) {
					smelt_barId = 2351;
					removed.add(440);
				} else {
					smelt_barId = 0;
					removed.add(440);
					sendMessage("You fail to refine the iron");
				}
			}
			break;
		case 2353:
			if (playerHasItem(440) && playerHasItem(453, 2)) {
				smelt_barId = 2353;
				removed.add(440);
				removed.add(453);
				removed.add(453);
			}
			break;
		case 2359:
			if (playerHasItem(447) && playerHasItem(453, 3)) {
				smelt_barId = 2359;
				removed.add(447);
				removed.add(453);
				removed.add(453);
				removed.add(453);
			}
			break;
		case 2361:
			if (playerHasItem(449) && playerHasItem(453, 4)) {
				smelt_barId = 2361;
				removed.add(449);
				removed.add(453);
				removed.add(453);
				removed.add(453);
				removed.add(453);
			}
			break;
		}
		if (smelt_barId == -1) {
			resetAction();
			return;
		}
		if (true) {
			for (Integer intId : removed) {
				int removeId = intId.intValue();
				deleteItem(removeId, 1);
			}
			if (smelt_barId > 0)
				addItem(smelt_barId, 1);
		} else {
			sendMessage("Your inventory is full!");
			resetAction();
		}
	}

	public boolean smithing() {
		if (IsItemInBag(2347) == true) {
			int bars = 0;
			int Length = 22;
			int barid = 0;
			int Level = 0;
			int ItemN = 1;

			if (smithing[2] >= 4) {
				barid = (2349 + ((smithing[2] + 1) * 2));
			} else {
				barid = (2349 + ((smithing[2] - 1) * 2));
			}
			if ((smithing[2] == 1) || (smithing[2] == 2)) {
				Length += 1;
			} else if (smithing[2] == 3) {
				Length += 2;
			}
			for (int i = 0; i < Length; i++) {
				if (Item.smithing_frame[(smithing[2] - 1)][i][0] == smithing[4]) {
					bars = Item.smithing_frame[(smithing[2] - 1)][i][3];
					if (smithing[1] == 0) {
						smithing[1] = Item.smithing_frame[(smithing[2] - 1)][i][2];
					}
					ItemN = Item.smithing_frame[(smithing[2] - 1)][i][1];
				}
			}
			if (playerLevel[playerSmithing] >= smithing[1]) {
				if (AreXItemsInBag(barid, bars) == true) {
					if (freeSlots() > 0) {
						if ((actionTimer == 0) && (smithing[0] == 1)) {
							actionAmount++;
							/*
							 * OriginalWeapon = playerEquipment[playerWeapon];
							 * playerEquipment[playerWeapon] = 2347; // Hammer
							 * OriginalShield = playerEquipment[playerShield];
							 * playerEquipment[playerShield] = -1;
							 */
							sendMessage("You start hammering the bar...");
							actionTimer = 7;
							setAnimation(0x382);
							smithing[0] = 2;
						}
						if ((actionTimer == 0) && (smithing[0] == 2)) {
							for (int i = 0; i < bars; i++) {
								deleteItem(barid, GetItemSlot(barid),
										playerItemsN[GetItemSlot(barid)]);
							}
							addSkillXP(
									((int) (150.5 * bars * smithing[2] * smithing[3])),
									playerSmithing);
							addItem(smithing[4], ItemN);
							sendMessage("You smith a "
									+ getItemName(smithing[4]) + ".");
							resetAnimation();
							if (smithing[5] <= 1) {
								resetSM();
							} else {
								actionTimer = 5;
								smithing[5] -= 1;
								smithing[0] = 1;
							}
						}
					} else {
						sendMessage("Not enough space in your inventory.");
						resetSM();
						return false;
					}
				} else {
					sendMessage("You need " + bars + " " + getItemName(barid)
							+ " to smith a " + getItemName(smithing[4]));
					resetAnimation();
					resetSM();
				}
			} else {
				sendMessage("You need " + smithing[1] + " "
						+ statName[playerSmithing] + " to smith a "
						+ getItemName(smithing[4]));
				resetSM();
				return false;
			}
		} else {
			sendMessage("You need a " + getItemName(2347) + " to hammer bars.");
			resetSM();
			return false;
		}
		return true;
	}

	public void spin() {
		if (playerHasItem(1779)) {
			deleteItem(1779, 1);
			addItem(1777, 1);
			lastAction = System.currentTimeMillis();
			addSkillXP(30, playerCrafting);
		} else {
			resetAction(true);
		}
	}

	/*
	 * [0] Varrock [1] Wizard Tower [2] Ardougne [3] Magic Guild
	 */
	public boolean stairs(int stairs, int teleX, int teleY) {
		if (IsStair == false) {
			IsStair = true;
			if (stairs == 1) {
				heightLevel += 1;
			} else if (stairs == 2) {
				heightLevel -= 1;
			} else if (stairs == 21) {
				heightLevel += 1;
			} else if (stairs == 22) {
				heightLevel -= 1;
			}
			teleportToX = teleX;
			teleportToY = teleY;
			if ((stairs == 3) || (stairs == 5) || (stairs == 9)) {
				teleportToY += 6400;
			} else if ((stairs == 4) || (stairs == 6) || (stairs == 10)) {
				teleportToY -= 6400;
			} else if (stairs == 7) {
				teleportToX = 3104;
				teleportToY = 9576;
			} else if (stairs == 8) {
				teleportToX = 3105;
				teleportToY = 3162;
			} else if (stairs == 11) {
				teleportToX = 2856;
				teleportToY = 9570;
			} else if (stairs == 12) {
				teleportToX = 2857;
				teleportToY = 3167;
			} else if (stairs == 13) {
				heightLevel += 3;
				teleportToX = skillX;
				teleportToY = skillY;
			} else if (stairs == 15) {
				teleportToY += (6400 - (stairDistance + stairDistanceAdd));
			} else if (stairs == 14) {
				teleportToY -= (6400 - (stairDistance + stairDistanceAdd));
			} else if (stairs == 16) {
				teleportToX = 2828;
				teleportToY = 9772;
			} else if (stairs == 17) {
				teleportToX = 3494;
				teleportToY = 3465;
			} else if (stairs == 18) {
				teleportToX = 3477;
				teleportToY = 9845;
			} else if (stairs == 19) {
				teleportToX = 3543;
				teleportToY = 3463;
			} else if (stairs == 20) {
				teleportToX = 3549;
				teleportToY = 9865;
			} else if (stairs == 21) {
				teleportToY += (stairDistance + stairDistanceAdd);
			} else if (stairs == 22) {
				teleportToY -= (stairDistance + stairDistanceAdd);
			} else if (stairs == 23) {
				teleportToX = 2480;
				teleportToY = 5175;
			} else if (stairs == 24) {
				teleportToX = 2862;
				teleportToY = 9572;
			} else if (stairs == 25) {
				Essence = (heightLevel / 4);
				heightLevel = 0;
				teleportToX = EssenceMineRX[Essence];
				teleportToY = EssenceMineRY[Essence];
			} else if (stairs == 26) {
				int EssenceRnd = misc.random3(EssenceMineX.length);

				teleportToX = EssenceMineX[EssenceRnd];
				teleportToY = EssenceMineY[EssenceRnd];
				heightLevel = (Essence * 4);
			} else if (stairs == 27) {
				teleportToX = 2453;
				teleportToY = 4468;
			} else if (stairs == 28) {
				teleportToX = 3201;
				teleportToY = 3169;
			}
			if ((stairs == 5) || (stairs == 10)) {
				teleportToX += (stairDistance + stairDistanceAdd);
			}
			if ((stairs == 6) || (stairs == 9)) {
				teleportToX -= (stairDistance - stairDistanceAdd);
			}
		}
		resetStairs();
		return true;
	}

	public boolean stakeItem(int itemID, int fromSlot, int amount) {
		if (System.currentTimeMillis() - lastButton < 800) {
			return false;
		}
		lastButton = System.currentTimeMillis();
		if (!Item.itemStackable[itemID] && !Item.itemIsNote[itemID]
				&& (amount > 1)) {
			for (int a = 1; a <= amount; a++) {
				int slot = findItem(itemID, playerItems, playerItemsN);
				if (slot >= 0) {
					stakeItem(itemID, slot, 1);
				}
			}
		}
		for (int element : noTrade) {
			if ((itemID == element) || (itemID == element + 1)
					|| premiumItem(itemID)) {
				sendMessage("You can't trade that item");
				// declineDuel();
				return false;
			}
		}
		client other = getClient(duel_with);
		if (!inDuel || !validClient(duel_with)) {
			declineDuel();
			return false;
		}
		if (!canOffer) {
			return false;
		}
		if (!playerHasItem(itemID, amount)) {
			return false;
		}
		if (Item.itemStackable[itemID] || Item.itemIsNote[itemID]) {
			boolean inTrade = false;
			for (GameItem item : offeredItems) {
				if (item.id == itemID) {
					inTrade = true;
					item.amount += amount;
					break;
				}
			}
			if (!inTrade) {
				offeredItems.add(new GameItem(itemID, amount));
			}
		} else {
			offeredItems.add(new GameItem(itemID, 1));
		}
		deleteItem(itemID, fromSlot, amount);
		resetItems(3214);
		resetItems(3322);
		other.resetItems(3214);
		other.resetItems(3322);
		refreshDuelScreen();
		other.refreshDuelScreen();
		sendFrame126("", 6684);
		other.sendFrame126("", 6684);
		return true;
	}

	public void startCraft(int actionbutton) {
		closeInterface();
		int[] buttons = { 33187, 33186, 33185, 33190, 33189, 33188, 33193,
				33192, 33191, 33196, 33195, 33194, 33199, 33198, 33197, 33202,
				33201, 33200, 33205, 33204, 33203 };
		int[] amounts = { 1, 5, 10, 1, 5, 10, 1, 5, 10, 1, 5, 10, 1, 5, 10, 1,
				5, 10, 1, 5, 10 };
		int[] ids = { 1129, 1129, 1129, 1059, 1059, 1059, 1061, 1061, 1061,
				1063, 1063, 1063, 1095, 1095, 1095, 1169, 1169, 1169, 1167,
				1167, 1167 };
		int[] levels = { 14, 1, 7, 11, 18, 38, 9 };
		int[] exp = { 27, 14, 16, 22, 27, 37, 19 };
		int amount = 0, id = -1;
		int index = 0;
		for (int i = 0; i < buttons.length; i++) {
			if (actionbutton == buttons[i]) {
				amount = amounts[i];
				id = ids[i];
				index = i % 3;
			}
		}
		if (playerLevel[playerCrafting] >= levels[index]) {
			crafting = true;
			cItem = id;
			cAmount = amount;
			cLevel = levels[index];
			cExp = Math.round(exp[index] * 9);
			cSelected = 1741;
		} else {
			sendMessage("Requires level " + levels[index]);
		}
	}

	public void startDuel() {
		closeInterface();
		duelFight = true;
		client other = getClient(duel_with);
		for (GameItem item : other.offeredItems) {
			otherOfferedItems.add(new GameItem(item.id, item.amount));
		}
	}

	public void startFishing(int object) {
		int req = -1, reqFishing = 1;
		switch (object) {
		case 316:
			pEmote = 621;
			fishId = 317;
			req = 303;
			break;
		case 321:
			pEmote = 621;
			fishId = 377;
			req = 301;
			reqFishing = 40;
			break;

		}
		fishTries = misc.random(27);
		if (playerLevel[playerFishing] < reqFishing) {
			sendMessage("Requires level " + reqFishing + " fishing");
			return;
		}
		if (!playerHasItem(req)) {
			sendMessage("You need a " + getItemName(req) + " to fish here");
			return;
		}
		if (System.currentTimeMillis() - lastAction >= 5000)
			fishing = true;
	}

	public void startHideCraft(int b) {
		int[] buttons = { 34185, 34184, 34183, 34182, 34189, 34188, 34187,
				34186, 34193, 34192, 34191, 34190 };
		int[] amounts = { 1, 5, 10, 27 };
		int index = 0;
		int index2 = 0;
		for (int i = 0; i < buttons.length; i++) {
			if (buttons[i] == b) {
				index = i % 4;
				index2 = (int) (i / 4);
				break;
			}
		}
		cAmount = amounts[index];
		cSelected = leathers[cIndex];
		int required = 99;
		if (index2 == 0) {
			required = gloveLevels[cIndex];
			cItem = gloves[cIndex];
			cExp = gloveExp[cIndex];
		} else if (index2 == 1) {
			required = legLevels[cIndex];
			cItem = legs[cIndex];
			cExp = legExp[cIndex];
		} else {
			required = chestLevels[cIndex];
			cItem = chests[cIndex];
			cExp = chestExp[cIndex];
		}
		if (playerLevel[playerCrafting] >= required) {
			cExp = (int) (cExp * 8);
			crafting = true;
			closeInterface();
		} else {
			sendMessage("Requires level " + required);
		}
	}

	public void startSmelt(int id) {
		int[] amounts = { 1, 5, 10, 27 };
		int index = 0, index2 = 0;
		for (int i = 0; i < misc.buttons_smelting.length; i++) {
			if (id == misc.buttons_smelting[i]) {
				index = i % 4;
				index2 = (int) (i / 4);
			}
		}
		smelt_id = misc.smelt_bars[index2];
		smeltCount = amounts[index];
		smelting = true;
		closeInterface();
	}

	public void startTan(int amount, int type) {
		int done = 0;
		int[] hide = { 1739, 1739, 1753, 1751, 1749, 1747 };
		int[] leather = { 1741, 1741, 1745, 2505, 2507, 2509 };
		int[] charge = { 50, 100, 1000, 2000, 5000, 10000 };
		while ((done < amount) && playerHasItem(hide[type])
				&& playerHasItem(995, charge[type])) {
			deleteItem(hide[type], 1);
			deleteItem(995, charge[type]);
			addItem(leather[type], 1);
			done++;
		}
		int total = done * charge[type];
		/*
		 * dMsg = "That's " + total + "gp for " + done + " hides"; NpcWanneTalk =
		 * 10; skillX = absX; skillY = absY; NpcTalkTo = 804;
		 */
	}

	public void stillgfx(int id, int y, int x) {
		stillgfx(id, y, x, 0, 0);
	}

	public void stillgfx(int id, int Y, int X, int height, int time) {
		// for (Player p : server.playerHandler.players) {
		for (Player p : server.playerHandler.players) {
			if (p != null) {
				client person = (client) p;

				if (person.playerName != null) {
					if (person.distanceToPoint(X, Y) <= 60) {
						person.stillgfx2(id, Y, X, height, time);
					}
				}
			}
		}
	}

	public void stillgfx2(int id, int Y, int X, int height, int time) {
		outStream.createFrame(85);
		outStream.writeByteC(Y - (mapRegionY * 8));
		outStream.writeByteC(X - (mapRegionX * 8));
		outStream.createFrame(4);
		outStream.writeByte(0); // Tiles away (X >> 4 + Y & 7)
		outStream.writeWord(id); // Graphic id
		outStream.writeByte(height); // height of the spell above it's basic
		// place, i think it's written in pixels
		// 100 pixels higher
		outStream.writeWord(time); // Time before casting the graphic
	}

	/*
	 * These are mainly god spells since they aren't multi-target
	 */
	public boolean StillSpell(int i) {
		if ((i == 1190) || (i == 1191) || (i == 1192)) {
			return true;
		}
		return false;
	}

	public void teleblock() {
		// stops from teleing anywhere
		AntiTeleDelay = 1000;
	}

	public void teleOtherRequest(String teleLocation, int player) {
		String telePlayer = server.playerHandler.players[player].playerName;

		sendQuest(telePlayer, 12558);
		sendQuest(teleLocation, 12560);
		showInterface(12468);
		teleReq = player;
		teleLoc = teleLocation;
		teleOtherScreen = true;
	}

	public void teleport() {
		teleport = true;
		if (ancientstele == true) {
			animation(392, absY, absX);
			teletimer = 12;
		} else if (ancientstele == false) {
			setAnimation(714);
			animation(308, absY, absX);
			teletimer = 5;
		}
	}

	public void TeleTo(String s, int level) {
		if (duelStatus == 3) {
			sendMessage("You can't move during a duel!");
			return;
		}
		if (actionTimer == 0) {
			teleX = absX;
			teleY = absY;
			newheightLevel = heightLevel;

			closeInterface();
			teleport();
			actionTimer = 10;
			updateRequired = true;
			appearanceUpdateRequired = true;
		}
	}

	public void testMagic(int spellID) {
		createProjectile(absY, absX, 0, 3, 50, 160, spellID, 43, 31, 0);
	}

	public boolean tradeItem(int itemID, int fromSlot, int amount) {
		if (System.currentTimeMillis() - lastButton > 800) {
			lastButton = System.currentTimeMillis();
		} else {
			return false;
		}
		if (!Item.itemStackable[itemID] && (amount > 1)) {
			for (int a = 1; a <= amount; a++) {
				int slot = findItem(itemID, playerItems, playerItemsN);
				if (slot >= 0) {
					tradeItem(itemID, slot, 1);
				}
			}
		}
		for (int element : noTrade) {
			if ((itemID == element) || (itemID == element + 1)
					|| premiumItem(itemID)) {
				sendMessage("You can't trade that item");
				declineTrade();
				return false;
			}
		}
		client other = getClient(trade_reqId);
		if (!inTrade || !validClient(trade_reqId) || !canOffer) {
			declineTrade();
			return false;
		}
		if (!playerHasItem(itemID, amount)) {
			return false;
		}
		if (Item.itemStackable[itemID] || Item.itemIsNote[itemID]) {
			boolean inTrade = false;
			for (GameItem item : offeredItems) {
				if (item.id == itemID) {
					inTrade = true;
					item.amount += amount;
					break;
				}
			}
			if (!inTrade) {
				offeredItems.add(new GameItem(itemID, amount));
			}
		} else {
			offeredItems.add(new GameItem(itemID, 1));
		}
		deleteItem(itemID, fromSlot, amount);
		resetItems(3322);
		resetTItems(3415);
		other.resetOTItems(3416);
		sendFrame126("", 3431);
		other.sendFrame126("", 3431);
		return true;
	}

	public void tradeReq(int id) {
		if (!server.trading) {
			sendMessage("Trading has been temporarily disabled");
			return;
		}
		client other = (client) handler.players[id];
		if (validClient(trade_reqId)) {
			if (other.inTrade || other.inDuel) {
				sendMessage("That player is busy at the moment");
				trade_reqId = 0;
				return;
			}
		}
		if (other.connectedFrom.equals(connectedFrom)) {
			tradeRequested = false;
			return;
		}
		if (validClient(trade_reqId) && !inTrade && other.tradeRequested
				&& (other.trade_reqId == playerId)) {
			openTrade();
			other.openTrade();
		} else if (validClient(trade_reqId) && !inTrade
				&& (System.currentTimeMillis() - lastButton > 1000)) {
			lastButton = System.currentTimeMillis();
			tradeRequested = true;
			trade_reqId = id;
			sendMessage("Sending trade request...");
			other.sendMessage(playerName + ":tradereq:");
		}
	}

	public void triggerRandom() {
		if (!randomed) {
			random_skill = misc.random(statName.length) - 1;
			if (random_skill < 0)
				random_skill = 0;
			sendQuest("Click the @or1@" + statName[random_skill]
					+ " @yel@button", 2810);
			sendQuest("", 2811);
			sendQuest("", 2831);
			randomed = true;
			showInterface(2808);
		}
	}

	public void triggerTele(int x, int y, int height, boolean prem) {
		if (System.currentTimeMillis() - lastAction > 5000) {
			lastAction = System.currentTimeMillis();
			resetWalkingQueue();
			if (prem && !premium) {
				sendMessage("This spell is only available to premium members, visit moparscape.org/smf for info");
				return;
			}
			if (duelStatus == 3)
				return;
			tX = x;
			tY = y;
			tH = height;
			tStage = 1;
			tTime = 0;
		}
	}

	public void uberentangle() {
		EntangleDelay = 40;
	}

	public void update() {
		handler.updatePlayer(this, outStream);
		handler.updateNPC(this, outStream);

		flushOutStream();
	}

	public void updateCharAppearance(int[] styles, int[] colors) {
		for (int j = 0; j < 7; j++) {
			if (styles[j] > 0) {
				styles[j] += 0x100;
				pCHead = styles[0];
				pCBeard = styles[1];
				pCTorso = styles[2];
				pCArms = styles[3];
				pCHands = styles[4];
				pCLegs = styles[5];
				pCFeet = styles[6];
			}
		}
		for (int i = 0; i < 5; i++) {
			pColor = colors[i];
		}
	}

	/* NPC Talking */
	public void UpdateNPCChat() {

		/*
		 * sendFrame126("", 4902); sendFrame126("", 4903); sendFrame126("",
		 * 4904); sendFrame126("", 4905); sendFrame126("", 4906);
		 */
		sendFrame126("", 976);
		switch (NpcDialogue) {
		case 1:

			/*
			 * sendFrame200(4901, 554); sendFrame126(GetNpcName(NpcTalkTo),
			 * 4902); sendFrame126("Good day, how can I help you?", 4904);
			 * sendFrame75(NpcTalkTo, 4901); sendFrame164(4900);
			 */
			sendFrame200(4883, 591);
			sendFrame126(GetNpcName(NpcTalkTo), 4884);
			sendFrame126("Good day, how can I help you?", 4885);
			sendFrame75(NpcTalkTo, 4883);
			sendFrame164(4882);
			NpcDialogueSend = true;
			break;

		case 2:
			sendFrame171(1, 2465);
			sendFrame171(0, 2468);
			sendFrame126("What would you like to say?", 2460);
			sendFrame126("I'd like to access my bank account, please.", 2461);
			sendFrame126("I'd like to check my PIN settings.", 2462);
			sendFrame164(2459);
			NpcDialogueSend = true;
			break;

		case 3:
			sendFrame200(4883, 591);
			sendFrame126(GetNpcName(NpcTalkTo), 4884);
			sendFrame126("Do you want to buy some runes?", 4885);
			sendFrame75(NpcTalkTo, 4883);
			sendFrame164(4882);
			NpcDialogueSend = true;
			break;

		case 4:
			sendFrame171(1, 2465);
			sendFrame171(0, 2468);
			sendFrame126("Select an Option", 2460);
			sendFrame126("Yes please!", 2461);
			sendFrame126("Oh it's a rune shop. No thank you, then.", 2462);
			sendFrame164(2459);
			NpcDialogueSend = true;
			break;

		case 5:
			sendFrame200(615, 974);
			sendFrame126(playerName, 975);
			sendFrame126("Oh it's a rune shop. No thank you, then.", 977);
			sendFrame185(974);
			sendFrame164(973);
			NpcDialogueSend = true;
			break;

		case 6:
			sendFrame200(4883, 591);
			sendFrame126(GetNpcName(NpcTalkTo), 4884);
			sendFrame126(
					"Well, if you find somone who does want runes, please",
					4885);
			sendFrame126("send them my way.", 4886);
			sendFrame75(NpcTalkTo, 4883);
			sendFrame164(4882);
			NpcDialogueSend = true;
			break;

		case 7:
			/* NEED TO CHANGE FOR GUARD */
			sendFrame200(4883, 591);
			sendFrame126(GetNpcName(NpcTalkTo), 4884);
			sendFrame126(
					"Well, if you find somone who does want runes, please",
					4885);
			sendFrame126("send them my way.", 4886);
			sendFrame75(NpcTalkTo, 4883);
			sendFrame164(4882);
			NpcDialogueSend = true;
			break;
		case 9:
			sendFrame200(4883, 1597);
			sendFrame126(GetNpcName(NpcTalkTo), 4884);
			sendFrame126("Select an Option", 2460);
			sendFrame126("Can you teleport me to the mage arena?", 2461);
			sendFrame126("Whats at the mage arena?", 2462);
			sendFrame164(2459);
			NpcDialogueSend = true;
			break;
		case 10:
			sendFrame200(4883, 804);
			sendFrame126(GetNpcName(804), 4884);
			sendFrame126(dMsg, 4885);
			sendFrame75(804, 4883);
			sendFrame164(4882);
			NpcDialogueSend = true;
			break;

		}
	}

	public void UpdatePlayerShop() {
		for (int i = 1; i < PlayerHandler.maxPlayers; i++) {
			if (PlayerHandler.players[i] != null) {
				if ((PlayerHandler.players[i].IsShopping == true)
						&& (PlayerHandler.players[i].MyShopID == MyShopID)
						&& (i != playerId)) {
					PlayerHandler.players[i].UpdateShop = true;
				}
			}
		}
	}

	public void updateRating() {
		sendQuest("Pk: " + rating, 3985);
	}

	public boolean validClient(int index) {
		client p = (client) handler.players[index];
		if ((p != null) && !p.disconnected) {
			return true;
		}
		return false;
	}

	/* END OF PRAYER */
	public void viewTo(int coordX, int coordY) {
		viewToX = ((2 * coordX) + 1);
		viewToY = ((2 * coordY) + 1);
		dirUpdate2Required = true;
		updateRequired = true;
	}

	public void walkTo(int x, int y) {
		newWalkCmdX[1] += x;
		newWalkCmdY[1] += y;
		tmpNWCX[1] = newWalkCmdX[i];
		tmpNWCY[1] = newWalkCmdY[i];
		newWalkCmdIsRunning = true;
	}

	public void walkTo_old(int X, int Y) {
		int firstStepX = inStream.readSignedWordBigEndianA();
		int tmpFSX = X;
		X -= mapRegionX * 8;
		for (i = 1; i < newWalkCmdSteps; i++) {
			newWalkCmdX[i] = inStream.readSignedByte();
			newWalkCmdY[i] = inStream.readSignedByte();
			tmpNWCX[i] = newWalkCmdX[i];
			tmpNWCY[i] = newWalkCmdY[i];
		}
		newWalkCmdX[0] = newWalkCmdY[0] = tmpNWCX[0] = tmpNWCY[0] = 0;
		int firstStepY = inStream.readSignedWordBigEndian();
		int tmpFSY = Y;
		Y -= mapRegionY * 8;
		newWalkCmdIsRunning = inStream.readSignedByteC() == 1;
		for (i = 0; i < newWalkCmdSteps; i++) {
			newWalkCmdX[i] += X;
			newWalkCmdY[i] += Y;
		}
		println_debug("Walking to X:" + X + " Y:" + Y);
	}

	public int WCCheckAxe() {
		int Hand;
		int Shield;
		int Bag;
		int Axe;

		Hand = playerEquipment[playerWeapon];
		Shield = playerEquipment[playerShield];
		Axe = 0;
		switch (Hand) {
		case 1351:
			// Bronze Axe
			Axe = 1;
			break;

		case 1349:
			// Iron Axe
			Axe = 2;
			break;

		case 1353:
			// Steel Axe
			Axe = 3;
			break;

		case 1361:
			// Black Axe
			Axe = 4;
			break;

		case 1355:
			// Mithril Axe
			Axe = 5;
			break;

		case 1357:
			// Adamant Axe
			Axe = 6;
			break;

		case 1359:
			// Rune Axe
			Axe = 7;
			break;

		/*
		 * case X: //Dragon Axe Axe = 8; break;
		 */
		}
		/*
		 * if (Axe > 0) { OriginalWeapon = Hand; OriginalShield = Shield;
		 * playerEquipment[playerShield] = -1; return Axe; }
		 */
		if (Axe > 0) {
			// OriginalWeapon = Hand;
			// OriginalShield = Shield;
			// playerEquipment[playerShield] = -1;
			// playerEquipment[playerWeapon] = Bag;
		}
		return Axe;
	}

	public boolean wear(int wearID, int slot) {
		if (duelFight && duelRule[3]) {
			sendMessage("Equipment changing has been disabled in this duel");
			return false;
		}
		int targetSlot = 0;
		targetSlot = itemType(wearID);
		int[] two_hand = { 1319, 4718, 4726, 1409, 4710, 7158 };
		for (int element : two_hand) {
			if ((wearID == element) && (playerEquipment[playerShield] > 0)) {
				if (playerHasItem(-1)) {
					addItem(playerEquipment[playerShield], 1);
					playerEquipment[playerShield] = -1;
				} else {
					sendMessage("You can't wear this weapon with a shield");
					return false;
				}
			}
			if ((itemType(wearID) == playerShield)
					&& (playerEquipment[playerWeapon] == element)) {
				if (playerHasItem(-1)) {
					addItem(playerEquipment[playerWeapon], 1);
					playerEquipment[playerWeapon] = -1;
				} else {
					sendMessage("You can't wear a shield with this weapon");
					return false;
				}
			}
			if (!canUse(wearID)) {
				sendMessage("You must be a premium member to use this item");
				return false;
			}
		}

		if ((playerItems[slot] - 1) == wearID) {
			targetSlot = itemType(wearID);
			int CLAttack = GetCLAttack(wearID);
			int CLDefence = GetCLDefence(wearID);
			int CLStrength = GetCLStrength(wearID);
			int CLMagic = GetCLMagic(wearID);
			int CLRanged = GetCLRanged(wearID);
			boolean GoFalse = false;

			if (CLAttack > playerLevel[playerAttack]) {
				sendMessage("You need " + CLAttack + " "
						+ statName[playerAttack] + " to equip this item.");
				GoFalse = true;
			}
			if (CLDefence > playerLevel[playerDefence]) {
				sendMessage("You need " + CLDefence + " "
						+ statName[playerDefence] + " to equip this item.");
				GoFalse = true;
			}
			if (CLStrength > playerLevel[playerStrength]) {
				sendMessage("You need " + CLStrength + " "
						+ statName[playerStrength] + " to equip this item.");
				GoFalse = true;
			}
			if (CLMagic > playerLevel[playerMagic]) {
				sendMessage("You need " + CLMagic + " " + statName[playerMagic]
						+ " to equip this item.");
				GoFalse = true;
			}
			if (CLRanged > playerLevel[playerRanged]) {
				sendMessage("You need " + CLRanged + " "
						+ statName[playerRanged] + " to equip this item.");
				GoFalse = true;
			}
			if (GoFalse == true) {
				return false;
			}
			int wearAmount = playerItemsN[slot];

			if (wearAmount < 1) {
				return false;
			}
			if ((slot >= 0) && (wearID >= 0)) {
				deleteItem(wearID, slot, wearAmount);
				if ((playerEquipment[targetSlot] != wearID)
						&& (playerEquipment[targetSlot] >= 0)) {
					addItem(playerEquipment[targetSlot],
							playerEquipmentN[targetSlot]);
				} else if (Item.itemStackable[wearID]
						&& (playerEquipment[targetSlot] == wearID)) {
					wearAmount = playerEquipmentN[targetSlot] + wearAmount;
				} else if (playerEquipment[targetSlot] >= 0) {
					addItem(playerEquipment[targetSlot],
							playerEquipmentN[targetSlot]);
				}
			}
			outStream.createFrameVarSizeWord(34);
			outStream.writeWord(1688);
			outStream.writeByte(targetSlot);
			outStream.writeWord(wearID + 1);
			if (wearAmount > 254) {
				outStream.writeByte(255);
				outStream.writeDWord(wearAmount);
			} else {
				outStream.writeByte(wearAmount); // amount
			}
			outStream.endFrameVarSizeWord();
			playerEquipment[targetSlot] = wearID;
			playerEquipmentN[targetSlot] = wearAmount;
			if ((targetSlot == playerWeapon)
					&& (playerEquipment[playerShield] != -1)
					&& (Item.itemTwoHanded[wearID] == true)) {
				remove(playerEquipment[playerShield], playerShield);
			}
			if (targetSlot == playerWeapon) {
				SendWeapon(wearID, getItemName(wearID));
				// playerSE = 0x328;
				// playerSEW = 0x333;
				// playerSER = 0x338;
				// playerSEA = 0x326;
				if (Item.itemTwoHanded[wearID] == true) {
					playerSE = 0x811;
					playerSEW = 0x67F;
					playerSER = 0x680;
				}

				if (wearID == 4212) {
					// New Crystal bow
					playerSEA = 426;
				}
				if (wearID == 4214) {
					// Crystal bow full
					playerSEA = 426;
				}
				if (wearID == 4215) {
					// Crystal bow 9/10
					playerSEA = 426;
				}
				if (wearID == 4216) {
					// Crystal bow 8/10
					playerSEA = 426;
				}
				if (wearID == 4217) {
					// Crystal bow 7/10
					playerSEA = 426;
				}
				if (wearID == 4218) {
					// Crystal bow 6/10
					playerSEA = 426;
				}
				if (wearID == 4219) {
					// Crystal bow 5/10
					playerSEA = 426;
				}
				if (wearID == 4220) {
					// Crystal bow 4/10
					playerSEA = 426;
				}
				if (wearID == 4221) {
					// Crystal bow 3/10
					playerSEA = 426;
				}
				if (wearID == 4222) {
					// Crystal bow 2/10
					playerSEA = 426;
				}
				if (wearID == 4223) {
					// Crystal bow 1/10
					playerSEA = 426;
				}

				/* Other Bows */
				if (wearID == 767) {
					// Phoenix crossbow
					playerSEA = 427;
				}
				if (wearID == 837) {
					// Crossbow
					playerSEA = 427;
				}
				for (int a = 0; a < shortbow.length; a++) {
					if (wearID == shortbow[a]) {
						playerSEA = 426;
					}
					if (wearID == longbow[a]) {
						playerSEA = 426;
					}
				}

				if (wearID == 839) {
					// Longbow
					playerSEA = 426;
				}
				if (wearID == 841) {
					// Shortbow
					playerSEA = 426;
				}
				if (wearID == 843) {
					// Oak shortbow
					playerSEA = 426;
				}
				if (wearID == 845) {
					// Oak longbow
					playerSEA = 426;
				}
				if (wearID == 847) {
					// Willow shortbow
					playerSEA = 426;
				}
				if (wearID == 849) {
					// Willow longbow
					playerSEA = 426;
				}
				if (wearID == 851) {
					// Maple shortbow
					playerSEA = 426;
				}
				if (wearID == 853) {
					// Maple longbow
					playerSEA = 426;
				}
				if (wearID == 855) {
					// Yew shortbow
					playerSEA = 426;
				}
				if (wearID == 857) {
					// Yew longbow
					playerSEA = 426;
				}
				if (wearID == 859) {
					// Magic shortbow
					playerSEA = 426;
				}
				if (wearID == 861) {
					// Magic longbow
					playerSEA = 426;
				}

				/* Barrows */
				if (wearID == 4718) {
					// Dharok's Greataxe
					playerSE = 0x811;
					playerSEA = 1665;
					playerSEW = 0x67F;
					playerSER = 0x680;
				}
				if (wearID == 4755) {
					// Verac Flail
					playerSEA = 2067;
					playerSEW = 1830;
					playerSER = 1831;
					playerSE = 1832;
				}
				if (wearID == 4734) {
					// Karils x-bow
					playerSE = 2074;
					playerSEA = 2075;
					playerSEW = 2076;
					playerSER = 2077;
				}
				if (wearID == 4747) {
					// Torag Hammers
					playerSEA = 0x814;
				}
				if (wearID == 4726) {
					// Guthans warspear
					playerSEA = 2080;
					playerSER = 2077;
					playerSEW = 2076;
					playerSE = 2074;
				}

				/* Misc */
				if (wearID == 7158) {
					// Dragon 2h sword
					playerSE = 0x811;
					playerSEA = 2876;
					playerSEW = 0x67F;
					playerSER = 0x680;
				}
				if (wearID == 6817) {
					// Slender blade
					playerSE = 0x811;
					playerSEA = 1665;
					playerSEW = 0x67F;
					playerSER = 0x680;
				}

				/*
				 * if (wearID == 6818) { // Bow-Sword playerSE = 0x811;
				 * playerSEA = 1665; playerSEW = 0x67F; playerSER = 0x680; }
				 */
				if (wearID == 4151) {
					// Abyssal Whip
					playerSEW = 1660;
					playerSER = 1661;
					playerSE = 1832;
					playerSEA = 1658;
				}
				if ((wearID == 1249) || (wearID == 1263) || (wearID == 3176)
						|| (wearID == 5716) || (wearID == 5730)) {
					// Dragon Spears
					playerSE = 809;
					playerSEW = 1146;
					playerSER = 1210;
				}
				if ((wearID == 1215) || (wearID == 5680) || (wearID == 1231)) {
					// Dragon dagger(all of em)
					playerSEA = 402;
				}
				if (wearID == 5698) {
					// Dragon Dagger (S)
					playerSEA = 1068;
				}
				if (wearID == 4170) {
					// SlayerStaff
					playerSE = 2261;
					playerSEA = 2324;
					playerSEW = 2261;
					playerSER = 2261;
				}

				if (wearID == 3204) {
					// Dragon Halberd
					playerSE = 809;
					playerSEA = 1665;
					playerSEW = 1146;
					playerSER = 1210;
				}
				if (wearID == 1305) {
					// Dragon Longsword
					playerSE = 809;
					playerSEA = 1058;
				}
				if (wearID == 1419) {
					// Scythe
					playerSEA = 408;
				}
				if (wearID == 4153) {
					// Granite Maul
					playerSE = 1662;
					playerSEA = 1665;
					playerSEW = 1663;
					playerSER = 1664;
				}
				if (wearID == 4675) {
					// Ancient staff
					playerSE = 809;
					playerSEA = 806;
					playerSEW = 1146;
					playerSER = 1210;
				}
				if (wearID == 84) {
					// Staff Of Armadyl
					playerSE = 809;
					playerSEA = 806;
					playerSEW = 1146;
					playerSER = 1210;
				}
				if (wearID == 772) {
					// Dramen Staff
					playerSE = 809;
					playerSEA = 806;
					playerSEW = 1146;
					playerSER = 1210;
				}
				if (wearID == 1379) {
					// Staff
					playerSE = 809;
					playerSEA = 806;
					playerSEW = 1146;
					playerSER = 1210;
				}
				if (wearID == 1381) {
					// Staff Of Air
					playerSE = 809;
					playerSEA = 806;
					playerSEW = 1146;
					playerSER = 1210;
				}
				if (wearID == 1383) {
					// Staff Of Water
					playerSE = 809;
					playerSEA = 806;
					playerSEW = 1146;
					playerSER = 1210;
				}
				if (wearID == 1385) {
					// Staff Of Earth
					playerSE = 809;
					playerSEA = 806;
					playerSEW = 1146;
					playerSER = 1210;
				}
				if (wearID == 1387) {
					// Staff Of Fire
					playerSE = 809;
					playerSEA = 806;
					playerSEW = 1146;
					playerSER = 1210;
				}
				if (wearID == 1389) {
					// Magic Staff
					playerSE = 809;
					playerSEA = 806;
					playerSEW = 1146;
					playerSER = 1210;
				}
				if (wearID == 1391) {
					// Battle Staff
					playerSE = 809;
					playerSEA = 806;
					playerSEW = 1146;
					playerSER = 1210;
				}
				if (wearID == 1393) {
					// Fire Battlestaff
					playerSE = 809;
					playerSEA = 806;
					playerSEW = 1146;
					playerSER = 1210;
				}
				if (wearID == 1395) {
					// Water Battlestaff
					playerSE = 809;
					playerSEA = 806;
					playerSEW = 1146;
					playerSER = 1210;
				}
				if (wearID == 1397) {
					// Air Battlestaff
					playerSE = 809;
					playerSEA = 806;
					playerSEW = 1146;
					playerSER = 1210;
				}
				if (wearID == 1399) {
					// Earth Battlestaff
					playerSE = 809;
					playerSEA = 806;
					playerSEW = 1146;
					playerSER = 1210;
				}
				if (wearID == 1401) {
					// Mystic Fire Staff
					playerSE = 809;
					playerSEA = 806;
					playerSEW = 1146;
					playerSER = 1210;
				}
				if (wearID == 1403) {
					// Mystic Water Staff
					playerSE = 809;
					playerSEA = 806;
					playerSEW = 1146;
					playerSER = 1210;
				}
				if (wearID == 1405) {
					// Mystic Air Staff
					playerSE = 809;
					playerSEA = 806;
					playerSEW = 1146;
					playerSER = 1210;
				}
				if (wearID == 1407) {
					// Mystic Earth Staff
					playerSE = 809;
					playerSEW = 1146;
					playerSEA = 806;
					playerSER = 1210;
				}
				if (wearID == 1409) {
					// I-Ban's Staff
					playerSE = 809;
					playerSEW = 1146;
					playerSER = 1210;
				}
				if (wearID == 3053) {
					// Lava Battlestaff
					playerSE = 809;
					playerSEA = 806;
					playerSEW = 1146;
					playerSER = 1210;
				}
				if (wearID == 3054) {
					// Mystic Lava Staff
					playerSE = 809;
					playerSEA = 806;
					playerSEW = 1146;
					playerSER = 1210;
				}
				if (wearID == 4170) {
					// Slayer Staff
					playerSE = 809;
					playerSEA = 806;
					playerSEW = 1146;
					playerSER = 1210;
				}
				if (wearID == 4862) {
					// Ahrim's Staff 100
					playerSE = 809;
					playerSEA = 806;
					playerSEW = 1146;
					playerSER = 1210;
				}
				if (wearID == 4863) {
					// Ahrim's Staff 75
					playerSE = 809;
					playerSEA = 806;
					playerSEW = 1146;
					playerSER = 1210;
				}
				if (wearID == 4864) {
					// Ahrim's Staff 50
					playerSE = 809;
					playerSEW = 1146;
					playerSEA = 806;
					playerSER = 1210;
				}
				if (wearID == 4865) {
					// Ahrim's Staff 25
					playerSE = 809;
					playerSEW = 1146;
					playerSER = 1210;
					playerSEA = 806;
				}
				if (wearID == 4866) {
					// Ahrim's Staff 0
					playerSE = 809;
					playerSEA = 806;
					playerSEW = 1146;
					playerSER = 1210;
				}
				if (wearID == 6603) {
					// White Staff
					playerSE = 809;
					playerSEA = 806;
					playerSEW = 1146;
					playerSER = 1210;
				}
				if (wearID == 6726) {
					// Mud Battlestaff
					playerSE = 1421;
					playerSEA = 806;
					playerSEW = 1422;
					playerSER = 1427;
				}
				if (wearID == 6727) {
					// Mystic Mud Staff
					playerSE = 1421;
					playerSEA = 806;
					playerSEW = 1422;
					playerSER = 1427;
				}
				if (wearID == 6528) {
					// Obby Maul
					playerSE = 0x811;
					playerSEA = 2661;
					playerSEW = 2064;
					playerSER = 0x680;
				}
				pEmote = playerSE;
			}
			ResetBonus();
			GetBonus();
			wearing = false;
			WriteBonus();
			updateRequired = true;
			appearanceUpdateRequired = true;
			return true;
		}
		return false;
	}

	/* WOODCUTTING */
	public boolean woodcutting() {
		if (randomed)
			return false;
		int WCAxe = 0;

		if (IsCutting == true) {
			WCAxe = 1; // If Cutting -> Go trough loop, passby WCCheckAxe to
			// prevent originalweapon loss, 1 to tell you got axe,
			// no function left for WCAxe if cutting, so 1 is
			// enough.
		} else {
			WCAxe = WCCheckAxe();
		}
		if (WCAxe > 0) {
			if (playerLevel[playerWoodcutting] >= woodcutting[1]) {
				if (freeSlots() > 0) {
					if ((System.currentTimeMillis() - lastAction >= 1000)
							&& (IsCutting == false)) {
						lastAction = System.currentTimeMillis();
						attackTimer++;
						sendMessage("You swing your axe at the tree...");
						attackTimer = (int) ((woodcutting[0] + 10) - WCAxe);
						if (attackTimer < 1) {
							attackTimer = 1;
						}
						setAnimation(0x284);
						IsCutting = true;
					}
					if ((System.currentTimeMillis() - lastAction >= 1600)
							&& (IsCutting == true)) {
						lastAction = System.currentTimeMillis();
						addSkillXP((woodcutting[2] * woodcutting[3] * 2),
								playerWoodcutting);
						if (misc.random(100) == 1) {
							triggerRandom();
							resetWC();
							return false;
						}
						if (freeSlots() > 0) {
							sendMessage("You get some logs.");
							addItem(woodcutting[4], 1);
							attackTimer += 10;
						} else {
							playerEquipment[playerWeapon] = OriginalWeapon;
							OriginalWeapon = -1;
							resetAnimation();
							IsCutting = false;
							resetWC();
						}
					}
				} else {
					// createEnemyItem(woodcutting[4]);
					sendMessage("Not enough space in your inventory.");
					woodcutting[0] = -1;
					resetWC();
					return false;
				}
			} else {
				sendMessage("You need " + woodcutting[1] + " "
						+ statName[playerWoodcutting] + " to cut those logs.");
				resetWC();
				return false;
			}
		} else {
			sendMessage("Equip your axe before cutting trees!");
			resetWC();
			return false;
		}
		return true;
	}

	public void WriteBonus() {
		int offset = 0;
		String send = "";

		for (int i = 0; i < playerBonus.length; i++) {
			if (playerBonus[i] >= 0) {
				send = BonusName[i] + ": +" + playerBonus[i];
			} else {
				send = BonusName[i] + ": -"
						+ java.lang.Math.abs(playerBonus[i]);
			}

			if (i == 10) {
				offset = 1;
			}
			if (i == 11) {
				send = "Spell Dmg:  +" + playerBonus[i] + "";
			}
			sendFrame126(send, (1675 + i + offset));
		}
		CalculateMaxHit();

		/*
		 * for (int i = 4000; i <= 7000; i++) { sendFrame126("T"+i, i);
		 * println_debug("Sended: Test"+i); }
		 */// USED FOR TESTING INTERFACE NUMBERS !
	}

	public void WriteEnergy() {
		// if (playerRights < 3) {
		playerEnergy = 100;
		// }
		sendFrame126(playerEnergy + "%", 149);
	}

	public void writeLog(String data, String file) {
		// used for bans/mutes/chatlogs etc. -bakatool
		BufferedWriter bw = null;

		try {
			bw = new BufferedWriter(new FileWriter("config//" + file + ".txt",
					true));
			bw.write(data);
			bw.newLine();
			bw.flush();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			if (bw != null)
				try {
					bw.close();
				} catch (IOException ioe2) {
					System.out.println("Error writing system log.");
					ioe2.printStackTrace();
				}
		}
	}

	public void yell(String message) {
		for (Player p : handler.players) {
			if ((message.indexOf("tradereq") > 0)
					|| (message.indexOf("duelreq") > 0))
				return;
			if ((p == null) || !p.isActive)
				continue;
			client temp = (client) p;
			if ((temp.absX > 0) && (temp.absY > 0))
				if ((temp != null) && !temp.disconnected && p.isActive)
					temp.sendMessage(message);
		}
	}

}