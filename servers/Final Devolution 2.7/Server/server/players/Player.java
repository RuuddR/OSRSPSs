package server.players;

import server.*;
import server.npcs.*;
import server.util.*;
import server.items.*;
import server.world.*;
import server.players.*;
import server.players.Skills.*;
import server.players.Packets.*;
import server.players.Quests.*;
import server.players.Combat.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

public abstract class Player implements Constants {
	public int getABSX() {
		return absX;
	}
	public int getABSY() {
		return absY;
	}
	public void runFreezeEvent() {
		server.registerEvent(new Event(freezeTiming) {
			public void execute() {
				if (isFrozen) {
					isFrozen = false;
					server.deregisterEvent(this);
				} else if (!isFrozen) {
					server.deregisterEvent(this);
				}
				server.deregisterEvent(this);
			}
		});
	}
	public void runFreezeEventNPC() {
		isFrozen = true;
		server.registerEvent(new Event(10000) {
			public void execute() {
				if (isFrozen) {
					isFrozen = false;
					server.deregisterEvent(this);
				} else if (!isFrozen) {
					server.deregisterEvent(this);
				}
				server.deregisterEvent(this);
			}
		});
	}
	public String
		txt4 = "",
		MBBC = "",
		MBHT = "",
		MBID = "",
		MBTC = "",
		properName = "",
		converse = "",
		dsendMessage = "",
		PLAYER = "",
		actionName = "",
		connectedFrom = "",
		globalMessage = "",
		optionType = "null",
		Winner = "Nobody",
		plrText = "",
		teleLoc = "",
		playerLastConnect,
		playerName = null,
		playerPass = null,
		playerServer;

	public int
		vengDamage = 0,
		vengDelay = 0,
		skulls = 0,
		hint = 0,
		DamageThisPlayer = 0,
		stunDelay = 0,
		thisPlayer = 0,
		melee1 = 0,
		melee2 = 0,
		melee3 = 0,
		range1 = 0,
		range2 = 0,
		range3 = 0,
		getPoisonDamage = 0,	
		poisonThisPlayer = 0,	
		delayMovement,	
		freezeTiming,	
		thisX, thisY,	
		recoilDamage,	
		recoilPercent,	
		stuntimer = 0,	
		caught = 0,	
		fightingWith,
		castRune1,
		castRune1Amount,
		castRune2,
		castRune2Amount,
		castRune3,
		castRune3Amount,
		castRune4,
		castRune4Amount,
		autoCastLevel,	
		tradeStatus,
		tradeWith,
		LogoutDelay,	
		upNetTimer,
		branchTimer,
		pipesTimer,
		falltimer,
		falltimer2,
		packetSize = 0, 
		packetType = -1,
		pCArms,
		pCBeard,
		pCFeet,
		pCHands,
		pCHead,
		pCLegs,
		pColor,
		pCTorso,
		playerstatus,
		Publicchat = 0, 
		cAmount = 0,
		loginDelay = 1,
		fcastid = 0,
		feh = 0,
		fenemyX = 0,
		fenemyY = 0,
		foffsetX = 0,
		ffinishid = 0,
		foffsetY = 0,
		fmgfxid = 0,
		fsh = 0,
		fspeed = 0,
		cba,
		cExp = 0,		
		cItem = -1,
		cLevel = 1,		
		clickIndex = 0,
		cSelected = -1,
		cIndex = -1,
		CombatExpRate = 1,
		OriginalShield = -1,
		OriginalWeapon = -1,
		NPCID,
		NPCSlot,
		newheightLevel,		
		lowMemoryVersion = 1,
		MageAttackIndex = -1,		
		currentButton = 0,
		currentStatus = 0,		
		dialogInterface = 2459,
		dialogId = 1,
		doors = -1,
		emotes = 0,
		enemyId = -1,
		enemyX = -1,		
		enemyY = -1,
		attackTimer = 0,
		EntangleDelay = 0,
		fangle = 0,
		fcasterX = 0,
		fcasterY = 0,		
		TX = 0,
		TY = 0,
		woodID = 0,
		teleportThis,
		teleportThis2,
		kniveDelay,
		woodIDs = 0,
		allignment = 0,
		ancients = 0,
		angle = 250,
		attackedNpcId = -1,
		autoCastID,
		autoCastIDNpc,
		i__,	
		mageThisNPC = 0,
		spam = 0,
		height = 0,
		fLockon = 0,
		x = 0,
		y = 0,
		getMagicalDamage = 0,
		magicH = 0,
		endGFX = 0,
		getMagicalEndGFX = 0,
		aaa,
		abc,
		abc2,
		actionButtonId = 0,
		barTimer = 0,
		saveTimer = 0,
		offTimer,
		hitID,
		yellTimer,
		itemKept1,
		itemKept2,
		itemKept3,
		itemKept4,
		itemKept1Slot,
		itemKept2Slot,
		itemKept3Slot,
		itemKept4Slot,
		CATCH_ID,
		FISH_EXP,
		FISH_ID,
		FISHING_LEVEL,
		FISHING_DELAY,
		FISHING_ANIMATION,
		LOOP_CHECK,
		getX,
		getY,	
		ITEM1,
		ITEM1AM,
		ITEM2,
		ITEM2AM,
		ITEM3,
		ITEM3AM,
		PLAYER_ID,
		COOKING_SPEED,
		COOKING_LEVEL,
		COOKING_EXP,
		COOKING_ANIMATION,
		cookID,
		ag1,
		ag2,
		ag3,
		ag4,
		ag5,
		ag6,
		intervaldeduction = -1,
		minewait,
		mineanim,
		minetime,
		fishtime,
		fishanim,
		actioninterval,
		wctime,
		wcanim,
		wcwait,
		pItemX,
		pItemY,
		pItemId,
		ClickCount,
		foundchild,
		tStage = 0,
		thisNPC = 0,
		followId,
		barrowBroSlot,
		mageThisPlayer = 0,
		followId2,
		hitDiff2 = 0,
		specialAmount = 100,
      		PoisonDelay2 = 0,
		PoisonDelay = 1,		
      		PoisonClear = 5,
		Poison = 4,		
      		strPotTimer = 0,
		attPotTimer = 0,		
      		defPotTimer = 0,
		agilPotTimer = 0,		
      		fishPotTimer = 0,
		rangePotTimer = 0,		
      		magePotTimer = 0,
		prayerDrain,
		prayerTimer,
		newDrain,
		PrayerTimer,
		oldDrain,
		objectIDz = 0,
		objectXz = 0,
		objectYz = 0,
		playerIndex = 0,
		playerMagicID = 0,
		outCome,
		randomDraw = 0,
		currentX, currentY,
		deathStage = 0,
		absX, absY,
		actionAmount = 0,
		attackDelay = 0,
		attackDelay2 = 0,
		attackDelay3 = 0,
		attackDelay4 = 0,
		attackDelay5 = 0,
		actionTimer = 0,
		ActionType = -1,
		AttackingOn = 0,
		attacknpc = -1,
		npcIndex = -1,
		ClimbStage = -1,
		combat = 0,
		combatLevel = 0,
		destinationID = -1,
		destinationRange = 1,
		destinationX = -1,
		playerAncientMagics2,
		destinationY = -1,
		npcMagicID = -1,
		dir1 = -1, dir2 = -1,
		DirectionCount = 0,
		duelChatStage = -1,
		duelChatTimer = -1,
		duelStatus = -1, // 0 = Requesting duel, 1 = in duel screen, 2 =
		duelWith = 0,
		Essence,
		FightType = 1,
		FocusPointX = -1,
		FocusPointY = -1,
		GObjChange = 1,
		headIcon = 1 & 1 >> 2,
		heightLevel,
		hitDiff = 0,
		hitDiff3 = 0,
		hits = 0,
		fightId = 0,
		npcId2 = 0,
		hptype = 0,
		i = 0,
		ip = 0,
		IsSnowing = 0,
		m4001 = 0,
		m4002 = 0,
		m4003 = 0,
		m4004 = 0,
		m4005 = 0,
		m4006 = 0,
		m4007 = 0,
		mapRegionX, mapRegionY,
		mask100var1 = 0,
		mask100var2 = 0,
		mask1var = 0,
		maxItemAmount = /* 2000000000 */999999999,
		playerEmotionReq = -1,
		playerEmotionDelay = 0,
		MyShopID = 0,
		NpcDialogue = 0,
		npcId,
		npcListSize = 0,
		NpcTalkTo = 0,
		NpcWanneTalk = 0,
		OptionObject = -1,
		pArms = 31,
		pBeard = 16,
		pFeet = 42,
		pFeetC = 3,
		pGender = 0,
		pHairC = 3,
		pHands = 33,
		pHead = 1,
		pLegs = 39,
		pLegsC = 2,
		pSkinC = 0,
		pTorso = 20,
		pTorsoC = 1,
		playerSE = 0x328, // SE = Standard Emotion
		playerSEA = 0x326, // SEA = Standard Emotion Attack
		playerSER = 0x338, // SER = Standard Emotion Run
		playerSEW = 0x333, // SEW = Standard Emotion Walking
		pEmote = 0x328, // this being the original standing state
		pWalk = 0x333, // original walking animation
		playerFly = 0x2261, // Flying Emotion
		playerallignment,
		playerBankSize = 350,
		playercwstatus,
		playerHat = 0,
		playerCape = 1,
		playerAmulet = 2,
		playerWeapon = 3,
		playerChest = 4,
		playerShield = 5,
		playerLegs = 7,
		playerHands = 9,
		playerFeet = 10,
		playerRing = 12,
		playerArrows = 13,
		playerAttack = 0,
		playerDefence = 1,
		playerStrength = 2,
		playerHitpoints = 3,
		playerRanged = 4,
		playerPrayer = 5,
		playerMagic = 6,
		playerCooking = 7,
		playerWoodcutting = 8,
		playerFletching = 9,
		playerFishing = 10,
		playerFiremaking = 11,
		playerCrafting = 12,
		playerSmithing = 13,
		playerMining = 14,
		playerHerblore = 15,
		playerAgility = 16,
		playerThieving = 17,
		playerSlayer = 18,
		playerFarming = 19,
		playerRunecrafting = 20,
		animationRequest = -1,
		animationWaitCycles = 0,
		playerEnergy,
		playerEnergyGian,
		playerFollowID = -1,
		playerGameCount,
		playerGameTime,
		playerId = -1,
		playerIsMember,
		playerRights,
		target = 0,
		playerListSize = 0,
		playerMaxHit = 0,
		playerMD = -1,
		playerMessages,
		damageDelay3 = 0,
		damageDelay4 = 0,
		weaponDelayy =0,
		weaponDelay = 0,
		playerLastLogin,
		damageDelay = 0,
		damageDelay2 = 0,
		extraDamage = 0,
		damageThisNPC = 0,
		TlastSlot = 0, 
		RlastSlot = 0,
		ItemSlot = 0,
		ItemID = 0,
		waitingID,
		waitingSlot,
		maulCount,
		EnemyIndexP = -1,
		originalS = 0,
		advertising = 0,
		wildyLevel = 0,
		timeIS = 0,
		countStack = 0,
		thTimer = 0,
		random_skill = -1,
		readPtr, writePtr,
		resetanim = 8,
		restart = 0,
		returnCode = 2, // Tells the client if the login was successfull
		rune1,
		rune1Am,
		rune2,
		rune2Am,
		rune3,
		rune3Am,
		rune4,
		rune4Am,
		spellXP,
		sameclick = 0,
		saraScore,
		savecounter = 0,
		SkillID = 0,
		skillX = -1,
		skillY = -1,
		somejunk,
		spellHit,
		spellHitTimer,
		spellNpcIndex = -1,
		spellPlayerIndex = -1, // same added so it won't be static ROFL
		stairDistance = 1,
		stairDistanceAdd = 0,
		stairs = 0,
		stealtimer,
		stillSpellGFX,
		summonLevel = 250,
		summonXP = 9999999,
		teletimer = 0,
		teleX = 0,
		teleY = 0,
		timeOutCounter = 0,
		tX = 0, tY = 0, tH = 1,
		WanneBank = 0,
		WanneShop = 0,
		XinterfaceID = 0,
		XremoveID = 0,
		XremoveSlot = 0,
		Xamount,
		zammyScore,
		poimiX = 0, poimiY = 0,
		Privatechat = 0,
		rating = 1500,
		loginReturn = 11,
		deathNum = 0,
		uid = -1,
		playerTicks = 100,
		reducedAttack = -1,
		StrPotion = 0,
		StrPrayer = 0,
		summonedNPCS = 0,
		teleportToX = -1,
		teleportToY = -1, // contain absolute x/y
		teleReq = 0,
		emTimer,
		violations = 0,
		viewToX = -1,
		viewToY = -1,
		wQueueReadPtr = 0,
		wQueueWritePtr = 0,
		_i = 0,
		followThisPlayer = 0,
		otherDuelItems[] = new int[28],
		otherDuelItemsN[] = new int[28],
		playerLook[] = new int[6],
		playerBonus[] = new int[12],
		duelItems[] = new int[28],
		duelItemsN[] = new int[28],
		delayA = 0,
		playerFollow[] = new int[PlayerHandler.maxPlayers];

	public boolean
		delayAA = false,
		pauseFollow = false,
		vengOn = false,
		keepPlayerStill,
		usingSpecialWeapon,
		outOfDistance,
		canShoot,
		canMelee,
		eventRunning,
		isFollow,
		runningTeleport,
		runningTeleport2,
		knive,
		tradeAccepted,
		goodTrade,
		inTrade,
		wasRunning,
		wasRunning2,
		tradeRequested,
		tradeResetNeeded,
		tradeConfirmed,
		tradeConfirmed2,
		canOffer,
		acceptTrade,
		flying = false,
		acceptedTrade,
		canthiev = false,
		stallstun = false,
		isInCombat,
		hasFinishedAgility = false,
		hasFinishedAgilityNets = false,
		agilityhalf = false,
		hasFinishedAgilityBranches = false,
		hasFinishedAgilityPipes = false,
		readyToHit = false,
		countDown,
		imFrozen,
		isPoisoned = false,
		isFrozen,
		cast = false,
		castleWarsOn = false,		
		castSpell,
		isStillSpell,
		officialClient = false,
		inTeleport = false,
		autoCast,
		autoCastNpc,		
		spellSelected,
		spellSelectedNpc,
		repeatSpell = false,
		repeatSpellNpc = false,
		hasMoved = false,
		hasFinished = true,
		isBusy,
		lookNeeded = false,
		friendUpdate = false,
		lookUpdate = false,
		HasArrows = false,
		fired = false,
		firingspell = false,
		getCloser = false,
		adding = false,
		emoting = false,
		alreadyChose = false,
		AnimationReset,
		ancientstele = false,
		isStunned = false,
		itemSlot1,
		itemSlot2,
		itemSlot3,
		itemSlot4,
		ismining,
		canmine,
		essMine = false,
		isplayerincombat = false,
		isFishing,
		canfish,
		cancut,
		iscutting,
		walkingToItem = false,
		specOn = false,
		Skulled,
	      	Poisoned = false,
      		superRestore = false,		
      		strPot = true,
      		attPot = true,
     		defPot = true,
      		agilPot = true,		
      		fishPot = true,		
      		rangePot = true, 
      		magePot = true,
		noPrayer = false,
		drainPray = false,
		defLow = false,
		strLow = false,
		atkLow = false,
		defMid = false,
		strMid = false,
		atkMid = false,
		defHigh = false,
		strHigh = false,
		atkHigh = false,
		rapidRest = false,
		rapidHeal = false, 
		protItem = false, 
		protMage = false, 
		protRange = false, 
		protMelee = false, 
		ret = false,
		redempt = false,
		smite = false,	
		appearanceUpdateRequired = true,	 // set to true if the
		apset,	 // apperance set.
		bankNotes = false,	
		Climbing = false,	
		CrackerForMe = false,	
		CrackerMsg = false,	
		debug = false,	
		following = false,	
		fighting = false,	
		dirUpdateRequired = false,	
		dropsitem = false,	
		didTeleport = false,	
		healUpdateRequired = false,	
		IsDead = false,	
		hitUpdateRequired = false,
		hitUpdateRequired2 = false,
		hitUpdateRequired3 = false,	
		inCombat = false,	
		initialized = false,
		disconnected = false,
		savefile = true,	
		IsUsingSkill = false,	
		isActive = false,	
		IsAttacking = false,	
		IsAttackingNPC = false,	
		IsBanking = false,	
		IsCutting = false,	
		IsDropping = false,	
		IsFireing = false,	
		IsGhost = false,	
		IsInCW = false,	
		IsInTz = false,	
		isInWilderness = false,	
		isKicked = false,	
		IsMakingFire = false,	
		IsMining = false,	
		isNpc,	
		IsPMLoaded = false,	
		isRunning = false,	
		isRunning2 = false,	
		IsShopping = false,	
		newhptype = false,	
		NpcDialogueSend = false,	
		playerAncientMagics = true,	
		mapRegionDidChange = false,	
		stopMaging,	
		doubleHit = false,	
		xtraDamage = false,	
		lastAttackz = false,	
		isFollowing = false,	
		wasInCombat = false,	
		isattackingaplayer = false,	
		rehitRangePlayer = false,	
		karilStairs,	
		karilSpawned,	
		throwable = false,	
		UseBow = false,	
		rehitRange = false,	
		isCloseEnough = false,	
		UseMelee = false,	
		magiccasting = false,	
		UseBow2 = false,	
		rehit3 = false, rehit4 = false,	
		stopAttack = false,	
		reHit = false,	
		reHit2 = false,	
		hasWildySign = false,	
		showWarning = true,	
		canDrop = false,	
		lastUpdateSpec = false,	
		canthiev1 = false,	
		isAlive = true,	
		isDead = false,	
		leftDanger = false,	
		logoutAttempt = false,	
		warning = true,	
		displayWarning = false,	
		Retribution = false,	
		SaradominStrike,
		GuthixClaws,
		ZamorakFlames,	
		saveNeeded = true,	
		shafting = false,	
		spamButton = false,	
		spellSet = false,	
		teleport = false,	
		validClient = true,
		muted = false,
		attackedNpc = false,	
		validLogin = false,	
		wearing = false,	
		WildernessWarning = false,	
		plrTextUpdateRequired = false,	
		premium = false, randomed = false,	
		RebuildNPCList = false,	
		startDuel = false,	
		takeAsNote = false,	
		teleOtherScreen = false,	
		updateRequired = true,	
		UpdateShop = false,	
		WalkingTo = false,	
		WannePickUp = false,	
		winDuel = false,	
		takingDamage = false,	
		takingDamage2 = false,	
		followPlayerIdle,
		duelRule[] = new boolean[28];

	public long
		lastAttackClick,
		lastDamageTaken = 0,
		lastThieve = 0,
		lastCount,
		lastclickz = 0,
		lastFire = 0,
		lastMageAttack = 0,
		tTime2 = 0,
		lasttalk = 0,
		ateLast = 0,
		lastAttack = 0,
		lastMouse = 0,
		lastProcess = 0,
		lastTeleblock = 0,
		extraDelay = 0,
		extraDelayTime = 0,
		animationReset = 0,
		lastButton = 0,
		stopMovementTime,
		stopMovement,
		lastFreeze,
		lastFish,
		updateEnergy,
		lastEnergyUpdate,
		lastAgility,
		lastAgilityNets,
		lastAgilityBranches,
		lastAgilityPipes,
		resetCombatParter,
		deathTimer = 0,
		freezeTimer = -1,
		lastCombat = 0,	
		lastDeath = 0,
		lastAction = 0,
		lastClick = 0,
		lastPacket = 0,
		deathTime = 0,
		lastBuild = 0,
		duhhh = 0,
		lastMage = 0,
		rehit = 0,
		lastAction2 = 0,
		rehit2 = 0,
		lastAdvertise = 0,
		timeOfDeath = 0,
		lastSave = 0,	
		lastSpecialUpdate = 0,
		delayDeath = 0,
		hpDelay = 0,
		lastPoison = 0,
		drop = 0,
		lastdeduct = 1000,
		tTime = 0,
		friends[] = new long[200],
		ignores[] = new long[100];

	protected boolean
		chatTextUpdateRequired = false,
		dirUpdate2Required = false,
		mask100update = false,
		mask1update = false,
		animationUpdateRequired = false,
		mask400update = false,
		IsStair = false;

	protected int 
		chatTextEffects = 0,
		chatTextColor = 0;


	public int[] 
		bankItems = new int[800],
		bankItemsN = new int[800],
		killers = new int[PlayerHandler.maxPlayers],
		playerEquipment = new int[14],
		playerEquipmentN = new int[14],
		playerItems = new int[28],
		playerItemsN = new int[28],
		playerLevel = new int[25],
		clicks = new int[50],
		effects = new int[10],
		playerXP = new int[25];

	public boolean[]
		IsDropped = new boolean[ItemHandler.MaxDropItems],
		MustDelete = new boolean[ItemHandler.MaxDropItems];

	public static int 
		GObjId = 0,
		GObjOrient = 0,
		GObjSet = 0,
		GObjType = 0,
		GObjX = 0,
		GObjY = 0,
		localId = -1;

	public static boolean 
		comeback = false,
		AutoSave = false,
		threats;

	public byte 
		playerInListBitmap[] = new byte[(PlayerHandler.maxPlayers + 7) >> 3],
		cachedPropertiesBitmap[] = new byte[(PlayerHandler.maxPlayers + 7) >> 3],
		npcInListBitmap[] = new byte[(NPCHandler.maxNPCSpawns + 7) >> 3];



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
			"range", "prayer", "magic", "cooking", "woodcutting", "fletching",
			"fishing", "firemaking", "crafting", "smithing", "mining",
			"herblore", "agility", "thieving", "slayer", "farming",
			"runecrafting" };


	protected static stream playerProps;
	public PlayerHandler handler = null;
	protected byte chatText[] = new byte[4096], chatTextSize = 0;
	public NPC npcList[] = new NPC[maxNPCListSize];
	public Player playerList[] = new Player[maxPlayerListSize];
	public static client client = null;
	public NPC followNPC = null;
	public Player followPlayer = null;
	public stream update;
	public int currentHealth = playerLevel[playerHitpoints];
	public int maxHealth = playerLevel[playerHitpoints];
	public long[]globalCooldown = new long[10];
	public int walkingQueueX[] = new int[walkingQueueSize], walkingQueueY[] = new int[walkingQueueSize];
	public abstract boolean isinpm(long l);
	public abstract void loadpm(long l, int world);
	public abstract void pmupdate(int pmid, int world);
	public static final int bufferSize = 25000;
	String[] lastMessage = new String[3];
	public stream inStream = null, outStream = null;
	public final static int maxNPCListSize = NPCHandler.maxNPCSpawns;
	public final static int maxPlayerListSize = PlayerHandler.maxPlayers;
	public static final int walkingQueueSize = 50;
	protected static boolean newWalkCmdIsRunning = false;
	protected static int newWalkCmdSteps = 0; 
	protected static int numTravelBackSteps = 0;
	protected static int newWalkCmdX[] = new int[walkingQueueSize];
	protected static int newWalkCmdY[] = new int[walkingQueueSize];
	protected static int tmpNWCX[] = new int[walkingQueueSize];
	protected static int tmpNWCY[] = new int[walkingQueueSize];
	protected static int travelBackX[] = new int[walkingQueueSize];
	protected static int travelBackY[] = new int[walkingQueueSize];


	static {
		playerProps = new stream(new byte[100]);
	}
	public void doublePullBack(int id, int delay) {
		mask100var1 = id;
		mask100var2 = delay;
		mask100update = true;
		updateRequired = true;
	}
	public void gfx0(int gfx){
		mask100var1 = gfx;
        	mask100var2 = 65536;
        	mask100update = true;
		updateRequired = true;
	}

	public void proGFX(int gfx) { 
		mask100var1 = gfx;
		mask100var2 = 6553600;
		mask100update = true;
		updateRequired = true;
	}
	public double GetItemValue(int ItemID) {
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
		TotPrice = (ShopValue * 1);
		return TotPrice;
	}
	public void frame1() {
		// cancels all player and npc emotes within area!
		outStream.createFrame(1);
		updateRequired = true;
		appearanceUpdateRequired = true;
	}
	public void follow(int i1, int i2) {
		Player FP = server.playerHandler.players[i1];
		client C = (client) server.playerHandler.players[i2];

		if (C.withinDistance(FP) && C.EntangleDelay <= 0) {
  			C.faceNPC = 32768 + i1;
			C.faceNPCupdate = true;
			C.followPlayer = FP;
		}
		if (isFrozen) {
			C.followPlayer = null;
		}
		if (!C.withinDistance(FP)) {
			C.followPlayer = null;
		}
	}
	public void followNPC(int i1, int i2) {
			
		NPC FNPC = server.npcHandler.npcs[i1];
		client C = (client) server.playerHandler.players[i2];
	}

	public Player(int _playerId) {
		playerId = _playerId;
		// playerName = "player"+playerId;
		playerRights = 0; // player rights
		lastPacket = System.currentTimeMillis();
		for (int i = 0; i < playerItems.length; i++) {
			// Setting player items
			playerItems[i] = 0;
		}
		for (int i = 0; i < playerItemsN.length; i++) {
			// Setting Item amounts
			playerItemsN[i] = 0;
		}

		for (int i = 0; i < playerLevel.length; i++) {
			// Setting Levels
			if (i == 3) {
				playerLevel[i] = 10;
				playerXP[i] = 1155;
				currentHealth = 10;
				maxHealth = 10;
			} else {
				playerLevel[i] = 1;
				playerXP[i] = 0;
			}
		}

		for (int i = 0; i < playerBankSize; i++) {
			// Setting bank items
			bankItems[i] = 0;
		}

		for (int i = 0; i < playerBankSize; i++) {
			// Setting bank item amounts
			bankItemsN[i] = 0;
		}
		Calendar cal = new GregorianCalendar();
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int month = cal.get(Calendar.MONTH);
		int year = cal.get(Calendar.YEAR);
		int calc = ((year * 10000) + (month * 100) + day);
		playerLastLogin = calc;
		playerLastConnect = "";
		playerIsMember = 1;
		playerMessages = 0;
		playerLook[0] = 2;
		playerLook[1] = 6;
		playerLook[2] = 7;
		playerLook[3] = 10;
		playerLook[4] = 5;
		playerLook[5] = 0;
		heightLevel = 0;
			if (Constants.STORY_MODE == true) {
				teleportToX = Constants.STARTING_SPAWN_POINT_X;
				teleportToY = Constants.STARTING_SPAWN_POINT_Y;
			} else if (Constants.STORY_MODE == false) {
				teleportToX = Constants.STARTING_SPAWN_POINT_X_CITY;
				teleportToY = Constants.STARTING_SPAWN_POINT_Y_CITY;
			}
		absX = absY = -1;
		mapRegionX = mapRegionY = -1;
		currentX = currentY = 0;
		resetWalkingQueue();
	}
	public void addNewNPC(NPC npc, stream str, stream updateBlock) {
		int id = npc.npcId;
		npcInListBitmap[id >> 3] |= 1 << (id & 7); // set the flag
		npcList[npcListSize++] = npc;

		str.writeBits(14, id); // client doesn't seem to like id=0

		int z = npc.absY - absY;
		if (z < 0)
			z += 32;
		str.writeBits(5, z); // y coordinate relative to thisPlayer
		z = npc.absX - absX;
		if (z < 0)
			z += 32;
		str.writeBits(5, z); // x coordinate relative to thisPlayer

		str.writeBits(1, 0); // something??
		str.writeBits(12, npc.npcType);

		boolean savedUpdateRequired = npc.updateRequired;
		npc.updateRequired = true;
		npc.appendNPCUpdateBlock(updateBlock);
		npc.updateRequired = savedUpdateRequired;
		str.writeBits(1, 1); // update required
	}

	public void addNewPlayer(Player plr, stream str, stream updateBlock) {
		int id = plr.playerId;
		playerInListBitmap[id >> 3] |= 1 << (id & 7); // set the flag
		playerList[playerListSize++] = plr;
		str.writeBits(11, id); // client doesn't seem to like id=0
		str.writeBits(1, 1); // set to true, if player definitions follow below
		boolean savedFlag = plr.appearanceUpdateRequired;
		boolean savedUpdateRequired = plr.updateRequired;
		plr.appearanceUpdateRequired = true;
		plr.updateRequired = true;
		plr.appendPlayerUpdateBlock(updateBlock);
		plr.appearanceUpdateRequired = savedFlag;
		plr.updateRequired = savedUpdateRequired;
		str.writeBits(1, 1); // set to true, if we want to discard the
		int z = plr.absY - absY;
		if (z < 0)
			z += 32;
		str.writeBits(5, z); // y coordinate relative to thisPlayer
		z = plr.absX - absX;
		if (z < 0)
			z += 32;
		str.writeBits(5, z); // x coordinate relative to thisPlayer
	}
	public void arrowPullBack(int gfx) { 
		mask100var1 = gfx;
		mask100var2 = 6553600;
		mask100update = true;
		updateRequired = true;
	} 
	public void db(int gfx) { 
		mask100var1 = gfx;
		mask100var2 = 6553550;
		mask100update = true;
		updateRequired = true;
	} 
	public void gfx100(int gfx) {
		animationRequest = gfx;
		mask100var2 = 6553600;
		mask100update = true;
		updateRequired = true;
	}
	public void startAnimation(int animIdx)
	{
		animationRequest = animIdx;
		animationWaitCycles = 0;
	}
	public void appendAnimationRequest(stream str)
	{
		str.writeWordBigEndian((animationRequest==-1) ? 65535 : animationRequest);
		str.writeByteC(animationWaitCycles);
	}
       	public void appendEmotionUpdate(stream str) {
		str.writeWordBigEndian((playerEmotionReq==-1) ? 65535 : playerEmotionReq);
		str.writeByteC(playerEmotionDelay);
	}

	public void addToWalkingQueue(int x, int y) {
		int next = (wQueueWritePtr + 1) % walkingQueueSize;
		if (next == wQueueWritePtr)
			return;
		walkingQueueX[wQueueWritePtr] = x;
		walkingQueueY[wQueueWritePtr] = y;
		wQueueWritePtr = next;
	}

	public void appendDirUpdate(stream str) {
		if (playerMD != -1) {
			/*
			 * str.writeBits(2, 1); // updateType str.writeBits(3,
			 * misc.xlateDirectionToClient[playerMD]); if (updateRequired) {
			 * str.writeBits(1, 1); // tell client there's an update block appended
			 * at the end } else { str.writeBits(1, 0); }
			 */
			str.writeWord(playerMD);
			playerMD = -1;
		}
	}

	protected void appendHitUpdate(stream str) {
		try {
			
			str.writeByte(hitDiff); 
			 if ((hitDiff > 0) && (newhptype == false)) {
				str.writeByteA(1);
			} else if ((hitDiff > 0) && (newhptype == true)) {
				str.writeByteA(hptype);
			} else {
				str.writeByteA(0);
			}
			if (!healUpdateRequired) {
				
			} else {
				currentHealth = (currentHealth + hitDiff);
				if (currentHealth > maxHealth) {
					currentHealth = maxHealth;
					followThisPlayer = -1;
				}
			}
			if (currentHealth <= 0) {
				currentHealth = 0;
				IsDead = true;
				deathStage = 1;
			}
			healUpdateRequired = false;
			str.writeByteC(currentHealth);
			str.writeByte(getLevelForXP(playerXP[playerHitpoints]));
			inCombat = true;
			lastCombat = System.currentTimeMillis();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	protected void appendHitUpdate2(stream str)
	{
		
		if(str != null)
		{
			
			str.writeByte(hitDiff2); 
			 if (hitDiff2 > 0 && !readyToHit) {
				str.writeByteS(1); // 0: red hitting - 1: blue hitting
			} else if (hitDiff2 > 0 && readyToHit) {
				str.writeByteS(2); // 0: red hitting - 1: blue hitting
				readyToHit = false;
			} else if (hitDiff2 <= 0) {
				str.writeByteS(0); // 0: red hitting - 1: blue hitting
			} else {
				str.writeByteS(1);
			}
			if (currentHealth <= 0) {
				currentHealth = 0;
				IsDead = true;
				deathStage = 1;
			}
            			str.writeByte(currentHealth);
            			str.writeByteC(getLevelForXP(playerXP[3]));
            			if(currentHealth <= 0) {
				isPoisoned = false;
				readyToHit = false;
				currentHealth = 0;
				followThisPlayer = -1;
			}
		}
	}

	public void appendPlayerUpdateBlock(stream str) {
		if (!updateRequired && !chatTextUpdateRequired)
			return;
		int updateMask = 0;
		if (mask400update)
			updateMask |= 0x400;
		if (mask100update)
			updateMask |= 0x100; 
		if(animationRequest != -1) 
			updateMask |= 8;
		if (chatTextUpdateRequired)
			updateMask |= 0x80;
		 if(faceNPCupdate)
			updateMask |= 1;
		if (appearanceUpdateRequired)
			updateMask |= 0x10;
		if (FocusPointX != -1)
			updateMask |= 2;
		if (hitUpdateRequired)
			updateMask |= 0x20;
		if (hitUpdateRequired2)
			updateMask |= 0x200;
		if (dirUpdateRequired)
			updateMask |= 0x40;
		if (plrTextUpdateRequired)
			updateMask |= 4;
		if (updateMask >= 0x100) {
			updateMask |= 0x40;
			str.writeByte(updateMask & 0xFF);
			str.writeByte(updateMask >> 8);
		} else
			str.writeByte(updateMask);
		if (mask400update)
			appendMask400Update(str);
		if (mask100update)
			appendMask100Update(str);
		if (plrTextUpdateRequired)
			appendString4(str);
		if(animationRequest != -1) 
			appendAnimationRequest(str);
		if (chatTextUpdateRequired)
			appendPlayerChatText(str);
		if(faceNPCupdate)   
			appendFaceNPCUpdate(str);
		if (appearanceUpdateRequired)
			appendPlayerAppearance(str);
		if (FocusPointX != -1)
			appendSetFocusDestination(str);
		if (hitUpdateRequired)
			appendHitUpdate(str);
		if (hitUpdateRequired2)
			appendHitUpdate2(str);
		if (dirUpdateRequired)
			appendDirUpdate(str);

	}

	public void appendMask100Update(stream str) {
		str.writeWordBigEndian(mask100var1);
		str.writeDWord(mask100var2);
	}

	public void appendMask1Update(stream str) {
		str.writeWordBigEndian(mask1var);
	}

	public void appendMask400Update(stream str) {
		// Xerozcheez: Something to do with direction
		str.writeByteA(m4001);
		str.writeByteA(m4002);
		str.writeByteA(m4003);
		str.writeByteA(m4004);
		str.writeWordA(m4005);
		str.writeWordBigEndianA(m4006);
		str.writeByteA(m4007); // direction
	}

	protected void appendPlayerAppearance(stream str) {
		playerProps.currentOffset = 0;

		// TODO: yet some things to figure out on this block + properly implement
		// this
		playerProps.writeByte(pGender);
		// player sex. 0=Male and 1=Female
		playerProps.writeByte(headIcon);
		// playerStatusMask - skull, prayers etc alkup 0

		// defining the character shape - 12 slots following - 0 denotes a null
		// entry and just a byte is used
		// slot 0,8,11,1 is head part - missing additional things are beard and
		// eyepatch like things either 11 or 1
		// cape, apron, amulet... the remaining things...

		if (isNpc == false) {
			if (playerEquipment[playerHat] > 1) {
				playerProps.writeWord(0x200 + playerEquipment[playerHat]);
			} else {
				playerProps.writeByte(0);
			}
			if (playerEquipment[playerCape] > 1) {
				playerProps.writeWord(0x200 + playerEquipment[playerCape]);
			} else {
				playerProps.writeByte(0);
			}
			if (playerEquipment[playerAmulet] > 1) {
				playerProps.writeWord(0x200 + playerEquipment[playerAmulet]);
			} else {
				playerProps.writeByte(0);
			}
			if (playerEquipment[playerWeapon] > 1) {
				playerProps.writeWord(0x200 + playerEquipment[playerWeapon]);
			} else {
				playerProps.writeByte(0);
			}
			if (playerEquipment[playerChest] > 1) {
				playerProps.writeWord(0x200 + playerEquipment[playerChest]);
			} else {
				playerProps.writeWord(0x100 + pTorso);
			}
			if (playerEquipment[playerShield] > 1) {
				playerProps.writeWord(0x200 + playerEquipment[playerShield]);
			} else {
				playerProps.writeByte(0);
			}
			if (!Item.isPlate(playerEquipment[playerChest])) {
				playerProps.writeWord(0x100 + pArms);
			} else {
				playerProps.writeByte(0);
			}
			if (playerEquipment[playerLegs] > 1) {
				playerProps.writeWord(0x200 + playerEquipment[playerLegs]);
			} else {
				playerProps.writeWord(0x100 + pLegs);
			}
			if (!Item.isFullHelm(playerEquipment[playerHat])
					&& !Item.isFullMask(playerEquipment[playerHat])) {
				playerProps.writeWord(0x100 + pHead); // head
			} else {
				playerProps.writeByte(0);
			}
			if (playerEquipment[playerHands] > 1) {
				playerProps.writeWord(0x200 + playerEquipment[playerHands]);
			} else {
				playerProps.writeWord(0x100 + pHands);
			}
			if (playerEquipment[playerFeet] > 1) {
				playerProps.writeWord(0x200 + playerEquipment[playerFeet]);
			} else {
				playerProps.writeWord(0x100 + pFeet);
			}
			if (!Item.isFullHelm(playerEquipment[playerHat])
					&& !Item.isFullMask(playerEquipment[playerHat])
					&& (pGender != 1)) {
				playerProps.writeWord(0x100 + pBeard);
			} else {
				playerProps.writeByte(0); // 0 = nothing on and girl don't have beard
				// so send 0. -bakatool
			}
		} else {
			playerProps.writeWord(-1);
			playerProps.writeWord(npcId2);

		}
		// array of 5 bytes defining the colors
		playerProps.writeByte(pHairC); // hair color
		playerProps.writeByte(pTorsoC); // torso color.
		playerProps.writeByte(pLegsC); // leg color
		playerProps.writeByte(pFeetC); // feet color
		playerProps.writeByte(pSkinC); // skin color (0-6)

		playerProps.writeWord(pEmote); // standAnimIndex
		playerProps.writeWord(0x337); // standTurnAnimIndex
		playerProps.writeWord(playerSEW); // walkAnimIndex
		playerProps.writeWord(0x334); // turn180AnimIndex
		playerProps.writeWord(0x335); // turn90CWAnimIndex
		playerProps.writeWord(0x336); // turn90CCWAnimIndex
		playerProps.writeWord(playerSER); // runAnimIndex

		playerProps.writeQWord(misc.playerNameToInt64(playerName));

		// Stat fix, combat decreases when your hp or any of these skills get
		// lowerd, this fixes that problem.
		/*
		 * int att = (int)((double)(getLevelForXP(playerXP[0])) * 0.325); int def =
		 * (int)((double)(getLevelForXP(playerXP[1])) * 0.25); int str =
		 * (int)((double)(getLevelForXP(playerXP[2])) * 0.325); int hit =
		 * (int)((double)(getLevelForXP(playerXP[3])) * 0.25); int mag =
		 * (int)((double)(getLevelForXP(playerXP[4])) * 0.4875); int pra =
		 * (int)((double)(getLevelForXP(playerXP[5])) * 0.125); int ran =
		 * (int)((double)(getLevelForXP(playerXP[6])) * 0.4875);
		 */

		/*
		 * int mag = (int)((double)(getLevelForXP(playerXP[4])) * 1.5); int ran =
		 * (int)((double)(getLevelForXP(playerXP[6])) * 1.5); int attstr =
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
		 * ((double)(getLevelForXP(playerXP[4])) * 0.4875)); } else { combatLevel =
		 * (int)(((double)(getLevelForXP(playerXP[1])) * 0.25) +
		 * ((double)(getLevelForXP(playerXP[3])) * 0.25) +
		 * ((double)(getLevelForXP(playerXP[5])) * 0.125) +
		 * ((double)(getLevelForXP(playerXP[0])) * 0.325) +
		 * ((double)(getLevelForXP(playerXP[2])) * 0.325)); }
		 * playerProps.writeByte(combatLevel); // combat level
		 * playerProps.writeWord(0); // incase != 0, writes skill-%d
		 * 
		 * str.writeByteC(playerProps.currentOffset); // size of player appearance
		 * block str.writeBytes(playerProps.buffer, playerProps.currentOffset, 0); }
		 */

		int mag = (int) ((getLevelForXP(playerXP[4])) * 1.5);
		int ran = (int) ((getLevelForXP(playerXP[6])) * 1.5);
		int attstr = (int) ((double) (getLevelForXP(playerXP[0])) + (double) (getLevelForXP(playerXP[2])));

		combatLevel = 0;
		if (ran > attstr) {
			combatLevel = (int) (((getLevelForXP(playerXP[1])) * 0.25)
					+ ((getLevelForXP(playerXP[3])) * 0.25)
					+ ((getLevelForXP(playerXP[5])) * 0.125) + ((getLevelForXP(playerXP[6])) * 0.4875));
		} else if (mag > attstr) {
			combatLevel = (int) (((getLevelForXP(playerXP[1])) * 0.25)
					+ ((getLevelForXP(playerXP[3])) * 0.25)
					+ ((getLevelForXP(playerXP[5])) * 0.125) + ((getLevelForXP(playerXP[4])) * 0.4875));
		} else {
			combatLevel = (int) (((getLevelForXP(playerXP[1])) * 0.25)
					+ ((getLevelForXP(playerXP[3])) * 0.25)
					+ ((getLevelForXP(playerXP[5])) * 0.125)
					+ ((getLevelForXP(playerXP[0])) * 0.325) + ((getLevelForXP(playerXP[2])) * 0.325));
		}
		combat = combatLevel;
		playerProps.writeByte(combatLevel);
		// combat level
		playerProps.writeWord(0);
		// incase != 0, writes skill-%d
		str.writeByteC(playerProps.currentOffset);
		// size of player appearance block
		str.writeBytes(playerProps.buffer, playerProps.currentOffset, 0);
	}


	protected void appendPlayerChatText(stream str) {
		str.writeWordBigEndian(((chatTextColor & 0xFF) << 8)
				+ (chatTextEffects & 0xFF));
		str.writeByte(playerRights);
		str.writeByteC(chatTextSize);
		// no more than 256 bytes!!!
		str.writeBytes_reverse(chatText, chatTextSize, 0);
	}


	private void appendSetFocusDestination(stream str) {
		str.writeWordBigEndianA(FocusPointX);
		str.writeWordBigEndian(FocusPointY);
	}

	public void appendString4(stream str) {
		str.writeString(plrText);
	}

	public void applyPoisonToMe() // by bakatool.
	{
	}

	public void clearUpdateFlags() {
		FocusPointX = FocusPointY = -1;
		updateRequired = false;
		animationRequest = -1;
		plrTextUpdateRequired = false;
		faceNPCupdate = false;
		faceNPC = 65535;
		chatTextUpdateRequired = false;
		appearanceUpdateRequired = false;
		hitUpdateRequired = false;
		takingDamage = false;
		hitUpdateRequired2 = false;
		takingDamage2 = false;
		hitUpdateRequired3 = false;
		mask100update = false;
		IsStair = false;
		
		
	}
	public void faceNPC(int index) {
                	faceNPC = index;
                	faceNPCupdate = true;
                	updateRequired = true;
      	}
       	 public boolean faceNPCupdate = false;
       		 public int faceNPC = -1;
       		 public void appendFaceNPCUpdate(stream str) {
              	  str.writeWordBigEndian(faceNPC);
      	  }
	public void dealDamage(int amt) {
		currentHealth -= amt;
		
	}

	void destruct() {
		playerListSize = 0;
		for (int i = 0; i < maxPlayerListSize; i++)
			playerList[i] = null;
		npcListSize = 0;
		for (int i = 0; i < maxNPCListSize; i++)
			npcList[i] = null;

		absX = absY = -1;
		mapRegionX = mapRegionY = -1;
		currentX = currentY = 0;
		resetWalkingQueue();
	}

	public int getLevelForXP(int exp) {
		int points = 0;
		int output = 0;

		for (int lvl = 1; lvl <= 135; lvl++) {
			points += Math.floor(lvl + 300.0 * Math.pow(2.0, lvl / 7.0));
			output = (int) Math.floor(points / 4);
			if (output >= exp)
				return lvl;
		}
		return 0;
	}

	public void getNextPlayerMovement() {
		mapRegionDidChange = false;
		didTeleport = false;
		dir1 = dir2 = -1;

		if(teleportToX != -1 && teleportToY != -1) {
			followPlayer = null;
			keepPlayerStill = true;
			mapRegionDidChange = true;
			if(mapRegionX != -1 && mapRegionY != -1) {
				int relX = teleportToX-mapRegionX*8, relY = teleportToY-mapRegionY*8;
				if(relX >= 2*8 && relX < 11*8 && relY >= 2*8 && relY < 11*8)
					mapRegionDidChange = false;
			}

			if(mapRegionDidChange) {
				
				mapRegionX = (teleportToX>>3)-6;
				mapRegionY = (teleportToY>>3)-6;

				//playerListSize = 0;
			}

			currentX = teleportToX - 8*mapRegionX;
			currentY = teleportToY - 8*mapRegionY;
			absX = teleportToX;
			absY = teleportToY;

			resetWalkingQueue();

			teleportToX = teleportToY = -1;
			didTeleport = true;
		} else {
			if(followPlayer != null) {
				if(followPlayerIdle) {
					followPlayerIdle = false;
					return;
				}
				dir1 = getNextFollowingDirection(followPlayer);
				if(dir1 == -1) followPlayerIdle = true;
			} else
				dir1 = getNextWalkingDirection();
			if(dir1 == -1) return;

			if(isRunning && followPlayer != null)
				dir2 = getNextFollowingDirection(followPlayer);
			else if(isRunning2)
				dir2 = getNextWalkingDirection();

			int deltaX = 0, deltaY = 0;
			if(currentX < 2*8) {
				deltaX = 4*8;
				mapRegionX -= 4;
				mapRegionDidChange = true;
			} else if(currentX >= 11*8) {
				deltaX = -4*8;
				mapRegionX += 4;
				mapRegionDidChange = true;
			}

			if(currentY < 2*8) {
				deltaY = 4*8;
				mapRegionY -= 4;
				mapRegionDidChange = true;
			} else if(currentY >= 11*8) {
				deltaY = -4*8;
				mapRegionY += 4;
				mapRegionDidChange = true;
			}

			if(mapRegionDidChange) {
				currentX += deltaX;
				currentY += deltaY;
				for(int i = 0; i < walkingQueueSize; i++) {
					walkingQueueX[i] += deltaX;
					walkingQueueY[i] += deltaY;
				}
			}

		}
	}
	public int getNextFollowingDirection(Player player) {
		resetWalkingQueue();
		int dir = -1;
		boolean goNorth = false, goSouth = false, goEast = false, goWestWhereTheCowboysRoam = false;
		
		if(absX < player.absX)
			goEast = true;
		else if(absX > player.absX)
			goWestWhereTheCowboysRoam = true;
		if(absY < player.absY)
			goNorth = true;
		else if(absY > player.absY)
			goSouth = true;

		if(!goSouth && !goNorth && !goEast && !goWestWhereTheCowboysRoam)
			return -1;

		if (isFrozen) {
			return -1;
		}
		if (pauseFollow) {
			return -1;
		}
		if(withinDistance(1, player))
			return -1;

		if(!withinDistance(14, player) || player.playerLevel[3] <= 0) {
			followPlayer = null;
		}
	
		if(goNorth && goEast) {
			dir = 2;
		}else if(goNorth && goWestWhereTheCowboysRoam) {
			dir = 14;
		}else if(goSouth && goEast) {
			dir = 6;
		}else if(goSouth && goWestWhereTheCowboysRoam) {
			dir = 10;
		}else if(goNorth) {
			dir = 0;
		}else if(goEast) {
			dir = 4;
		}else if(goWestWhereTheCowboysRoam) {
			dir = 12;
		}else if(goSouth) {
			dir = 8;
		}
		dir >>= 1;
		currentX += misc.directionDeltaX[dir];
		currentY += misc.directionDeltaY[dir];
		absX += misc.directionDeltaX[dir];
		absY += misc.directionDeltaY[dir];

		return dir;
	}
	public void WalkTo(int x, int y) 
	{
            		newWalkCmdSteps = (Math.abs((x+y)));
		if (newWalkCmdSteps % 1 != 0) newWalkCmdSteps /= 1;
		int firstStepX = absX;
		int tmpFSX = firstStepX;
		firstStepX -= mapRegionX*8;
		for (i = 1; i < newWalkCmdSteps; i++) 
		{
			newWalkCmdX[i] = x;
			newWalkCmdY[i] = y;
			tmpNWCX[i] = newWalkCmdX[i];
			tmpNWCY[i] = newWalkCmdY[i];
		}
		newWalkCmdX[0] = newWalkCmdY[0] = tmpNWCX[0] = tmpNWCY[0] = 0;
		int firstStepY = absY;
		int tmpFSY = firstStepY;
		firstStepY -= mapRegionY*8;
		//newWalkCmdIsRunning = ((inStream.readSignedByteC() == 1) && playerEnergy > 0);
		for (i = 0; i < newWalkCmdSteps; i++) 
		{
			newWalkCmdX[i] += firstStepX;
			newWalkCmdY[i] += firstStepY;
		}
	}
	public int getNextWalkingDirection() {
		if (wQueueReadPtr == wQueueWritePtr)
			return -1;
		// walking queue empty
		int dir;
		do {
			dir = misc.direction(currentX, currentY,
					walkingQueueX[wQueueReadPtr], walkingQueueY[wQueueReadPtr]);
			if (dir == -1)
				wQueueReadPtr = (wQueueReadPtr + 1) % walkingQueueSize;
			else if ((dir & 1) != 0) {
				resetWalkingQueue();
				return -1;
			}
		} while ((dir == -1) && (wQueueReadPtr != wQueueWritePtr));
		if (dir == -1)
			return -1;
		dir >>= 1;
		currentX += misc.directionDeltaX[dir];
		currentY += misc.directionDeltaY[dir];
		absX += misc.directionDeltaX[dir];
		absY += misc.directionDeltaY[dir];
		return dir;
	}

	public abstract void initialize();

	public void kick() {
		isKicked = true;
	}
	
	public void postProcessing()
	{
		if(newWalkCmdSteps > 0) {
			int OldcurrentX = currentX;
			int OldcurrentY = currentY;
			for(i = 0; i < playerFollow.length; i++) {
				if (playerFollow[i] != -1 && following == true) {
					PlayerHandler.players[playerFollow[i]].newWalkCmdSteps = (newWalkCmdSteps + 1);
					for(int j = 0; j < newWalkCmdSteps; j++) {
						PlayerHandler.players[playerFollow[i]].newWalkCmdX[(j + 1)] = newWalkCmdX[j];
						PlayerHandler.players[playerFollow[i]].newWalkCmdY[(j + 1)] = newWalkCmdY[j];
					}
					PlayerHandler.players[playerFollow[i]].newWalkCmdX[0] = OldcurrentX;
					PlayerHandler.players[playerFollow[i]].newWalkCmdY[0] = OldcurrentY;
					PlayerHandler.players[playerFollow[i]].poimiX = OldcurrentX;
					PlayerHandler.players[playerFollow[i]].poimiY = OldcurrentY;
				}
				int firstX = newWalkCmdX[0], firstY = newWalkCmdY[0];

				int lastDir = 0;
				boolean found = false;
				numTravelBackSteps = 0;
				int ptr = wQueueReadPtr;
				int dir = misc.direction(currentX, currentY, firstX, firstY);
				if(dir != -1 && (dir&1) != 0) {
					do {
						lastDir = dir;
						if(--ptr < 0) ptr = walkingQueueSize-1;

						travelBackX[numTravelBackSteps] = walkingQueueX[ptr];
						travelBackY[numTravelBackSteps++] = walkingQueueY[ptr];
						dir = misc.direction(walkingQueueX[ptr], walkingQueueY[ptr], firstX, firstY);
						if(lastDir != dir) {
							found = true;
							break;
						}

					} while(ptr != wQueueWritePtr);
				}
				else found = true;

			if(!found) {
				disconnected = true;
			} else {
				wQueueWritePtr = wQueueReadPtr;

				addToWalkingQueue(currentX, currentY);

				if(dir != -1 && (dir&1) != 0) {

					for(int i = 0; i < numTravelBackSteps-1; i++) {
						addToWalkingQueue(travelBackX[i], travelBackY[i]);
					}
					int wayPointX2 = travelBackX[numTravelBackSteps-1], wayPointY2 = travelBackY[numTravelBackSteps-1];
					int wayPointX1, wayPointY1;
					if(numTravelBackSteps == 1) {
						wayPointX1 = currentX;
						wayPointY1 = currentY;
					}
					else {
						wayPointX1 = travelBackX[numTravelBackSteps-2];
						wayPointY1 = travelBackY[numTravelBackSteps-2];
					}

					dir = misc.direction(wayPointX1, wayPointY1, wayPointX2, wayPointY2);
					if(dir == -1 || (dir&1) != 0) {
						System.out.println("Error - The walking queue is corrupt!");
					}
					else {
						dir >>= 1;
						found = false;
						int x = wayPointX1, y = wayPointY1;
						while(x != wayPointX2 || y != wayPointY2) {
							x += misc.directionDeltaX[dir];
							y += misc.directionDeltaY[dir];
							if((misc.direction(x, y, firstX, firstY)&1) == 0) {
								found = true;
								break;
							}
						}
						if(!found) {
							System.out.println("Error - Internal error: Unable to determine connection vertex!");
						}
						else addToWalkingQueue(wayPointX1, wayPointY1);
					}
				}
				else {
					for(int i = 0; i < numTravelBackSteps; i++) {
						addToWalkingQueue(travelBackX[i], travelBackY[i]);
					}
				}

				for(int i = 0; i < newWalkCmdSteps; i++) {
					addToWalkingQueue(newWalkCmdX[i], newWalkCmdY[i]);
				}

			}
			isRunning = newWalkCmdIsRunning || isRunning2;

			for(i = 0; i < playerFollow.length; i++) {
				if (playerFollow[i] != -1 && PlayerHandler.players[playerFollow[i]] != null) {
					PlayerHandler.players[playerFollow[i]].postProcessing();
					}
				}
			}
		}
	}
	
	public boolean withinDistanceNPC(int distance, NPC NPC) {
		if(NPC == null) return false;
			if(heightLevel != NPC.heightLevel) 
				return false;
				int deltaX = NPC.absX-absX, deltaY = NPC.absY-absY;
				return deltaX <= distance && deltaX >= ((distance + 0) * -1) && deltaY <= distance && deltaY >= ((distance + 0) * -1);
	}
	public boolean withinDistance(int distance, Player NPC) {
		if(NPC == null) return false;
			if(heightLevel != NPC.heightLevel) 
				return false;
				int deltaX = NPC.absX-absX, deltaY = NPC.absY-absY;
				return deltaX <= distance && deltaX >= ((distance + 0) * -1) && deltaY <= distance && deltaY >= ((distance + 0) * -1);
	}

	public void preProcessing() {
		newWalkCmdSteps = 0;
	}

	public void println(String str) {
		System.out.println("[" + playerId + "]: " + str);
	}

	public void println_debug(String str) {
		System.out.println("[" + playerId + "]: " + str);
	}

	public abstract boolean process();
	//public abstract boolean packetProcess();
	public abstract boolean playerProcess();
	public abstract boolean timers();

	public void removeequipped() {
		dropsitem = true;
	}

	public void resetWalkingQueue() {
		try {
			wQueueReadPtr = wQueueWritePtr = 0;
			for (int i = 0; i < walkingQueueSize; i++) {
				walkingQueueX[i] = currentX;
				walkingQueueY[i] = currentY;
			}
			keepPlayerStill = false;
		} catch (Exception e) {
			disconnected = true;
			e.printStackTrace();
		}
	}
	public abstract void sendpm(long name, int rights, byte[] chatmessage,
			int messagesize);

	public void TurnPlayerTo(int pointX, int pointY) {
		FocusPointX = 2 * pointX + 1;
		FocusPointY = 2 * pointY + 1;
	}

	public abstract void update();

	public void updatePlayerMovement(stream str) {
		if (dir1 == -1) {
			// don't have to update the character position, because the char is just
			// standing
			if (updateRequired || chatTextUpdateRequired) {
				// tell client there's an update block appended at the end
				str.writeBits(1, 1);
				str.writeBits(2, 0);
			} else
				str.writeBits(1, 0);
		} else if (dir2 == -1) {
			// send "walking packet"
			str.writeBits(1, 1);
			str.writeBits(2, 1);
			str.writeBits(3, misc.xlateDirectionToClient[dir1]);
			str
					.writeBits(1,
							(updateRequired || chatTextUpdateRequired) ? 1 : 0);
		} else {
			// send "running packet"
			str.writeBits(1, 1);
			str.writeBits(2, 2);
			str.writeBits(3, misc.xlateDirectionToClient[dir1]);
			str.writeBits(3, misc.xlateDirectionToClient[dir2]);
			str
					.writeBits(1,
							(updateRequired || chatTextUpdateRequired) ? 1 : 0);
		}
	}

	public void updateThisPlayerMovement(stream str) {
		if (mapRegionDidChange) {
			str.createFrame(73);
			str.writeWordA(mapRegionX + 6); // for some reason the client substracts 6
			// from those values
			str.writeWord(mapRegionY + 6);
		}

		if (didTeleport == true) {
			str.createFrameVarSizeWord(81);
			str.initBitAccess();
			str.writeBits(1, 1);
			str.writeBits(2, 3);
			// updateType
			str.writeBits(2, heightLevel);
			str.writeBits(1, 1);
			// set to true, if discarding (clientside) walking queue
			str.writeBits(1, (updateRequired) ? 1 : 0);
			str.writeBits(7, currentY);
			str.writeBits(7, currentX);
			return;
		}

		if (dir1 == -1) {
			// don't have to update the character position, because we're just
			// standing
			str.createFrameVarSizeWord(81);
			str.initBitAccess();
			if (updateRequired) {
				// tell client there's an update block appended at the end
				str.writeBits(1, 1);
				str.writeBits(2, 0);
			} else {
				str.writeBits(1, 0);
			}
			if (DirectionCount < 50) {
				DirectionCount++;
			}
		} else {
			DirectionCount = 0;
			str.createFrameVarSizeWord(81);
			str.initBitAccess();
			str.writeBits(1, 1);

			if (dir2 == -1) {
				// send "walking packet"
				str.writeBits(2, 1);
				// updateType
				str.writeBits(3, misc.xlateDirectionToClient[dir1]);
				if (updateRequired)
					str.writeBits(1, 1);
				// tell client there's an update block appended at the end
				else
					str.writeBits(1, 0);
			} else {
				// send "running packet"
				str.writeBits(2, 2);
				// updateType
				str.writeBits(3, misc.xlateDirectionToClient[dir1]);
				str.writeBits(3, misc.xlateDirectionToClient[dir2]);
				if (updateRequired)
					str.writeBits(1, 1);
				// tell client there's an update block appended at the end
				else
					str.writeBits(1, 0);

				if (playerEnergy > 0) {
					playerEnergy = (playerEnergy - 1);
				} else {
					isRunning2 = false;
				}
					
			}
		}

	}
	public boolean withinDistance(NPC npc) {
		if (heightLevel != npc.heightLevel)
			return false;
		if (npc.NeedRespawn == true)
			return false;
		int deltaX = npc.absX - absX, deltaY = npc.absY - absY;
		return (deltaX <= 15) && (deltaX >= -16) && (deltaY <= 15)
				&& (deltaY >= -16);
	}

	public boolean withinDistance(Player otherPlr) {
		if (heightLevel != otherPlr.heightLevel)
			return false;
		int deltaX = otherPlr.absX - absX, deltaY = otherPlr.absY - absY;
		return (deltaX <= 15) && (deltaX >= -16) && (deltaY <= 15)
				&& (deltaY >= -16);
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
		0, 0, 6, 6, 0, 0, 0 // 250
	};
	public void inCombat() {
		LogoutDelay = 20;
	}
	public boolean isSkulled;
	public int skullTimer = 0;
	public String skulledBy;
	public boolean hasMultiSign;
	public void checkHead() {
		if (!isSkulled) {
			headIcon = 0;
		}
		if (isSkulled) {
			headIcon = 64;
		}
	}
	public void clearSkull() {
		headIcon = 0;
	}
	public void getHead() {
		if (protMage && !isSkulled)
			headIcon = 4;
		if (protRange && !isSkulled)
			headIcon = 2;
		if(protMelee && !isSkulled)
			headIcon = 1;
		if(ret && !isSkulled)
			headIcon = 8;
		if(redempt && !isSkulled)
			headIcon = 32;
		if(smite && !isSkulled)
			headIcon = 16;
		if(protMage && isSkulled)
			headIcon = 68;
		if(protRange && isSkulled)
			headIcon = 66;
		if(protMelee && isSkulled)
			headIcon = 65;
		if(ret && isSkulled)
			headIcon = 72;
		if(redempt && isSkulled)
			headIcon = 96;
		if(smite && isSkulled)
			headIcon = 80;
		else if(!protMage && !protRange && !protMelee && !ret && !redempt && !smite && isSkulled)
			headIcon = 64;
		else if(!protMage && !protRange && !protMelee && !ret && !redempt && !smite && !isSkulled)
			headIcon = 0;
	}
	public void turnOffHead(){
		if(protMage)
			headIcon = 4;
		if(protRange)
			headIcon = 2;
		if(protMelee)
			headIcon = 1;
		if(ret)
			headIcon = 8;
		if(redempt)
			headIcon = 32;
		if(smite)
			headIcon = 16;
		else if(!isSkulled && !protMage && !protRange && !protMelee && !ret && !redempt && !smite)
			headIcon = 0;
	}
	public int[] specialWeapons = {5698, 1215, 1231, 5680, 4151, 861, 1305, 7158, 1434, 3204, 4153, 6739, 1377, 4587};
	public byte buffer[] = null;
	public Cryption inStreamDecryption = null, outStreamDecryption = null;
	public int KillerId = playerId;
	
}
