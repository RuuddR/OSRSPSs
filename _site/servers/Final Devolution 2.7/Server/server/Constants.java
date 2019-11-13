package server;

import server.npcs.*;
import server.players.*;
import server.items.*;
import server.util.*;
import server.world.*;


public interface Constants{


	/* Basic Configuration */
	public static final String SERVER_NAME = "Final Devolution 2.7";//servername
	public static final int SERVER_PORT = 43594;//port - default 43594
	public static final int STARTING_SPAWN_POINT_X = 3090;//starting point x (if story mode is on/off do NOT change this)
	public static final int STARTING_SPAWN_POINT_Y = 3490;//starting point y (if story mode is on/off do NOT change this)
	public static final int STARTING_SPAWN_POINT_X_CITY = 3090;//real starting point x (if story mode is off you can change this to your starting point)
	public static final int STARTING_SPAWN_POINT_Y_CITY = 3490;//real starting point y (if story mode is off you can change this to your starting point)
	public static final int DEATH_SPAWN_POINT_X = 3090;//regular dieing from npc/player x
	public static final int DEATH_SPAWN_POINT_Y = 3490;//regular dieding form npc/player y
	public static final boolean STORY_MODE = false; //true story mode, false no story
	public static final int[] ITEM_TRADEABLE = {7777}; // what items can't be traded or staked

	/* QUEST OPTIONS */
	public static final boolean CLEAR_QUEST_SIDE_INTERFACE = false; //true = no show, false show
	public static final boolean CLEAR_QUEST_BUTTONS = false; //false = use, true = dont use
	
	/* Teleport configurations */
 	public static final boolean CANT_TELEPORT_AFTER_LVL_20_WILDERNESS = true; //true = no teleport passed lvl 20, false = you can teleport passed lvl 20
	public static final boolean TELEPORT_LEVEL_REQ = false; //true = need lvl, false = no lvl
	public static final boolean TELEPORT_REQ_RUNES = false; //true = need runes, false = no runes
	public static final boolean TELPORT_DELETE_RUNES = false; //true = delete runes, false = dont delete runes.

	/* Teleports */ //-1 = no teleport//change the -1's to ur coords
	/* SpellBookNormals Teleports */
	public static final int VARROCKX = 3090;//homeX
	public static final int VARROCKY = 3490;//homeY
	public static final int LUMBRIGEX = -1;
	public static final int LUMBRIGEY = -1;
	public static final int FALADORX = -1;
	public static final int FALADORY = -1;
	public static final int CAMELOTX = -1;
	public static final int CAMELOTY = -1;
	public static final int ARDOUGNEX = -1;
	public static final int ARDOUGNEY = -1;
	public static final int WATCHTOWERX = -1;//slayertower
	public static final int WATCHTOWERY = -1;//slayer tower
	public static final int TROLLHEIMX = -1;//edgeville
	public static final int TROLLHEIMY = -1;
	public static final int APE_TOLLX = -1;
	public static final int APE_TOLLY = -1;

	/* Teleports */ //-1 = no teleport //change -1's to the coords
	/* Ancient Spell Book Teleports */
	public static final int PADDEWWAX = 3090;
	public static final int PADDEWWAY = 3490;
	public static final int SENNTISTENX = -1;
	public static final int SENNTISTENY = -1;
	public static final int KHARYRLLX = -1;
	public static final int KHARYRLLY = -1;
	public static final int LASSARX = -1;
	public static final int LASSARY = -1;
	public static final int DAREEYAKX = -1;
	public static final int DAREEYAKY = -1;
	public static final int CARRALLANGERX = -1;
	public static final int CARRALLANGERY = -1;
	public static final int ANNAKARLX = -1;
	public static final int ANNAKARLY = -1;
	public static final int GHORROCKX = -1;
	public static final int GHORROCKY = -1;


	public static final int AGILITY_LOGS = 60 * server.SERVER_EXPERIENCE;
	public static final int AGILITY_NETS = 60 * server.SERVER_EXPERIENCE;
	public static final int AGILITY_BRANCES = 60 * server.SERVER_EXPERIENCE;
	public static final int AGILITY_PIPES = 60 * server.SERVER_EXPERIENCE;
	/* COOKING EXPS */	

	public static final int COOKING_SHRIMPS = 30 * server.SERVER_EXPERIENCE;
	public static final int COOKING_SARDINE = 40 * server.SERVER_EXPERIENCE;
	public static final int COOKING_HERRING = 50 * server.SERVER_EXPERIENCE;
	public static final int COOKING_ANCHOVIE = 30 * server.SERVER_EXPERIENCE;
	public static final int COOKING_MACKEREL = 50 * server.SERVER_EXPERIENCE;
	public static final int COOKING_TROUT = 70 * server.SERVER_EXPERIENCE;
	public static final int COOKING_COD = 75 * server.SERVER_EXPERIENCE;
	public static final int COOKING_SALMON = 90 * server.SERVER_EXPERIENCE;
	public static final int COOKING_TUNA = 100 * server.SERVER_EXPERIENCE;
	public static final int COOKING_LOBSTER = 120 * server.SERVER_EXPERIENCE;
	public static final int COOKING_SWORDFISH = 140 * server.SERVER_EXPERIENCE;
	public static final int COOKING_BASS = 130 * server.SERVER_EXPERIENCE;
	public static final int COOKING_SHARK = 210 * server.SERVER_EXPERIENCE;

	/* FOOD HEALING EXP */
	public static final int CHICKEN = -20;//Cooked Chicken Healing
	public static final int LOBSTER = 12;//Cooked Lobster Healing
	public static final int TUNA = 10;//Cooked Tuna Healing
	public static final int SARDINE = 6;//Cooked Sardine Healing
	public static final int SHRIMP = 3;//Cooked Shrimp Healing
	public static final int ANCHOVIES = 3;//Cooked Anchovies Healing
	public static final int TURTLE = 23;//Cooked Turtle Healing
	public static final int EEL = 9;//Cooked Eel Healing
	public static final int PIKE = 7;//Cooked Pike Healing
	public static final int BASS = 13;//Cooked Bass Healing
	public static final int SALMON = 7;//Cooked salmon Healing
	public static final int POTATO = 1;//Cooked Potato Healing
	public static final int CABBAGE = 1;//Cooked Cabbage Healing
	public static final int COD = 7;//Cooked Cod Healing
	public static final int SHARK = 20;//Cooked Shark Healing	
	public static final int CRISPY_BREAD = 3;//Cooked CrispyBread Healing
	public static final int SWORD_FISH = 13;//Cooked SwordFish Healing
	public static final int ORANGE = 2;//Orange Healing
	public static final int MANTA_RAY = 25;//Cooked MantaRay Healing
	public static final int CHOCOLATE_CAKE_FULL = 3;
	public static final int CHOCOLATE_CAKE_2_SLICE = 3;
	public static final int CHOCOLATE_CAKE_SLICE = 3;
	public static final int CAKE_FULL = 3;
	public static final int CAKE_2_SLICE = 3;
	public static final int CAKE_SLICE = 3;

	/* BONES EXPS */
	public static final int REG_BONES_1 = 40  * server.SERVER_EXPERIENCE;//bones and wolfbone exp
	public static final int REG_BONES_2= 120  * server.SERVER_EXPERIENCE;//Burnt and bat bone exp
	public static final int BIG_BONES = 55 * server.SERVER_EXPERIENCE;//big bones bone exp
	public static final int B_DRAGON_BONES = 119 * server.SERVER_EXPERIENCE;//Baby Dragon bone exp
	public static final int DRAGON_BONES = 148 * server.SERVER_EXPERIENCE;//Dragon bone exp
	public static final int JOGRE_BONES_1 = 78 * server.SERVER_EXPERIENCE;//Jogre bone exp
	public static final int JOGRE_BONES_2 = 205 * server.SERVER_EXPERIENCE;//Burnt Jogre bone exp
	public static final int JOGRE_BONES_3 = 215 * server.SERVER_EXPERIENCE;//Pasty jorge bone exp
	public static final int MONEY_BONES = 57 * server.SERVER_EXPERIENCE;//Monkey bone exp
	public static final int ZORGRE_BONES = 185 * server.SERVER_EXPERIENCE;//Zogre bone exp
	public static final int REG_BONES_3 = 190 * server.SERVER_EXPERIENCE;//fayrg and shaikahan bone exp
	public static final int DAG_BONES = 199 * server.SERVER_EXPERIENCE;//Dagannoth bone exp


	/* Other Constants */

	public String BonusName[] = { "Stab", "Slash", "Crush", "Magic", "Range", "Stab", "Slash", "Crush", "Magic", "Range", "Strength", "Prayer" };
	public final int[] specialWeapons = {5698, 15999, 1215, 1231, 5680, 4151, 861, 1305, 7158, 1434, 3204, 4153, 6739, 1377, 4587};
	public final int[] regularStaffs = { 1379,1381,1383,1385,1387,1389,1391,1393,1395,1397,1399,1401,1403,1405,1407};
	public final int[] logs = {1511,  1521, 1519, 1517, 1515, 1513};
	public final int[] shortbows = {50, 54, 60, 64, 68, 72};
	public final int[] shortreq = {5, 20, 35, 50, 65, 80};
	public final int[] shortexp = {50, 100, 200, 300, 400, 450};
	public final int[] seeds =  {5291, 5292, 5293, 5294, 5295, 5296, 5297, 5298, 5299, 5300, 5301, 5302, 5303};
	public final int[] NONbowStuff = {15156,863,864,865,866,867,868,869,806,807,808,809,810,811,825,826,827,828,829,830,800,801,802,803,804,805,6522};
	public final int[] shortbow = {15156,841, 843, 849, 853, 857, 861};
	public final int[] longbows = {15156,4734,4212, 48, 56, 58, 62, 66, 70};
	public final int[] longreq = {10, 25, 40, 55, 70, 85};
	public final int[] longexp = {60, 110, 220, 330, 440, 490};
	public final int[] longbow = {15156,4734,4212, 839, 845, 847, 851, 855, 859};
	public final int[] hides = {1753, 1751, 1749, 1747};
	public final int[] leathers = {1745, 2505, 2507, 2509};
	public final int[] gloves = {1065, 2487, 2489, 2491}; 
	public final int[] gloveLevels = {57, 66, 73, 79};
	public final int[] gloveExp = {62, 70, 78, 86};
	public final int[] chests = {1135, 2499, 2501, 2503};
	public final int[] chestLevels = {63, 71, 77, 82};
	public final int[] chestExp = {186, 210, 234, 258};
	public final int[] legs = {1099, 2493, 2495, 2497};
	public final int[] legLevels = {60, 68, 75, 82};
	public final int[] legExp = {124, 140, 156, 172};
}