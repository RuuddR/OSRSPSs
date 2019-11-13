//  This file is free software; you can redistribute it and/or modify it under
//  the terms of the GNU General Public License version 2, 1991 as published by
//  the Free Software Foundation.

//  This program is distributed in the hope that it will be useful, but WITHOUT
//  ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
//  FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more
//  details.

//  A copy of the GNU General Public License can be found at:
//    http://www.gnu.org/licenses/gpl.html

// a collection of item methods
import java.io.*;
public class Item2 
{

public static int crystal[] = {1057,1055,1053,1057,1055,1053,1057,1055,1053,1057,1055,1053,1057,1055,1053,1038,1040,1042,1044,1046,1048,1050,1038,1040,1042,1044,1046,1048,1050,1038,1040,1042,1044,1046,1048,1050,1038,1040,1042,1044,1046,1048,1050,1038,1040,1042,1044,1046,1048,1050,11965,11965,11268,11268,4151,4151,1057,1055,1053,1057,1055,1053,1057,1055,1053,1057,1055,1053,1057,1055,1053,1038,1040,1042,1044,1046,1048,1050,1038,1040,1042,1044,1046,1048,1050,1038,1040,1042,1044,1046,1048,1050,1038,1040,1042,1044,1046,1048,1050,1038,1040,1042,1044,1046,1048,1050,11965,11965,11268,11268,4151,4151,1057,1055,1053,1057,1055,1053,1057,1055,1053,1057,1055,1053,1057,1055,1053,1038,1040,1042,1044,1046,1048,1050,1038,1040,1042,1044,1046,1048,1050,1038,1040,1042,1044,1046,1048,1050,1038,1040,1042,1044,1046,1048,1050,1038,1040,1042,1044,1046,1048,1050,11965,11965,11268,11268,4151,4151,11785};
    
    public static int randomCrystal()
    {
	return crystal[(int)(Math.random()*crystal.length)];
    }
public static int runerock[] = {451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,1481,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451};

    public static int randomRuneRock()
    {
    	return runerock[(int)(Math.random()*runerock.length)];
    }	

public static int fish[] = {385,385,379,379,379,379,379};

    public static int randomFish()
    {
    	return fish[(int)(Math.random()*fish.length)];
    }	
public static int rat[] = {5698,1305,3105,1725,1704,1323,1153,1115,1067,1081,1157,1119,1069,1083};

    public static int randomrat()
    {
    	return rat[(int)(Math.random()*rat.length)];
    }
public static int soldier[] = {3101,6897,3202,1333,1319,1113,1127,1147,1093,1079,1373,4131,995};

    public static int randomsoldier()
    {
    	return soldier[(int)(Math.random()*soldier.length)];
    }
public static int ogre[] = {837,5018,995};

    public static int randomogre()
    {
    	return ogre[(int)(Math.random()*ogre.length)];
    }
public static int chicken[] = {4834};

    public static int randomchicken()
    {
    	return chicken[(int)(Math.random()*chicken.length)];
    }
public static int skeleton[] = {13601,14638,15334,995,995,995,995,995,995,995,995,995,995,995,995,995,995,995,995,995,995,995,995,995,995,995,995,995,995,995,995,995,995,995,995,995,995};

    public static int randomskeleton()
    {
    	return skeleton[(int)(Math.random()*skeleton.length)];
    }
public static int crawlinghand[] = {2615,1333,526,526,526,526,526,526,526,526,526,526,526,526,526,526};

    public static int randomcrawlinghand()
    {
    	return crawlinghand[(int)(Math.random()*crawlinghand.length)];
    }
public static int cavebug[] = {4119,4121,4123,4125,4127,4129,4131,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526};

    public static int randomcavebug()
    {
    	return cavebug[(int)(Math.random()*cavebug.length)];
    }
public static int jelly[] = {15352,3122,6809,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526};

    public static int randomjelly()
    {
    	return jelly[(int)(Math.random()*jelly.length)];
    }
public static int aberrantspecter[] = {3840,3842,3844,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526};

    public static int randomaberrantspecter()
    {
    	return aberrantspecter[(int)(Math.random()*aberrantspecter.length)];
    }
public static int abyssaldemon[] = {989,989,98915352,4151,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526};

    public static int randomabyssaldemon()
    {
    	return abyssaldemon[(int)(Math.random()*abyssaldemon.length)];
    }
public static int darkbeast[] = {15352,13640,15156,6818,11192,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526};

    public static int randomdarkbeast()
    {
    	return darkbeast[(int)(Math.random()*darkbeast.length)];
    }
public static int barbarian[] = {7611,7611,7611,3105,7603,5698,4587,4726,7386,7394,7390,995};

    public static int randombarbarian()
    {
    	return barbarian[(int)(Math.random()*barbarian.length)];
    }
public static int unicorn[] = {6966,995,995,995,995,995,995,995,995,995,995,995,995,995,995,995,995,995,995};

    public static int randomunicorn()
    {
    	return unicorn[(int)(Math.random()*unicorn.length)];
    }
public static int battlemagesara[] = {14507,14508,14513,5698,995,995,995,995,995,995,995,995,995,995,995,995,995,995,995,995,995,995,995,995,995,995,995,995,995,995,995};

    public static int randombattlemagesara()
    {
    	return battlemagesara[(int)(Math.random()*battlemagesara.length)];
    }
public static int battlemagezammy[] = {14507,14508,14512,5698,995,995,995,995,995,995,995,995,995,995,995,995,995,995,995,995,995,995,995,995,995,995,995,995,995,995,995};

    public static int randombattlemagezammy()
    {
    	return battlemagezammy[(int)(Math.random()*battlemagezammy.length)];
    }
public static int battlemageguthix[] = {14507,14508,14511,5698,995,995,995,995,995,995,995,995,995,995,995,995,995,995,995,995,995,995,995,995,995,995,995,995,995,995,995};

    public static int randombattlemageguthix()
    {
    	return battlemageguthix[(int)(Math.random()*battlemageguthix.length)];
    }
public static int troll[] = {3741,995,995,995,995,995,995,995,995,995,995,995,995,995,995,995,995,995,995,995,995,995,995};

    public static int randomtroll()
    {
    	return troll[(int)(Math.random()*troll.length)];
    }

public static int KQ[] = {15352,2633,2635,2637,2978,2980,2982,2984,2986,2988,2990,2992,2994,2957,2653,2655,2659,3478,2665,2661,2663,2667,3479,2673,2669,2671,3480,4724,4728,4730,4726,6570,1037,1050,6857,6859,6861,6863,6856,6858,6860,6862,6818,989};

    public static int randomKQ()
    {
    	return KQ[(int)(Math.random()*KQ.length)];
    }
public static int Ahrim[] = {4708, 4710, 4712, 4714};
        public static int randomAhrim()
        {
                return Ahrim[(int)(Math.random()*Ahrim.length)];
        }

public static int Guthan[] = {4724, 4726, 4728, 4730};
        public static int randomGuthan()
        {
                return Guthan[(int)(Math.random()*Guthan.length)];
        }

public static int Karil[] = {4732, 4734, 4736, 4738};
        public static int randomKaril()
        {
                return Karil[(int)(Math.random()*Karil.length)];
        }

public static int Torag[] = {4745, 4747, 4749, 4751};
        public static int randomTorag()
        {
                return Torag[(int)(Math.random()*Torag.length)];
        }

public static int Verac[] = {4753, 4755, 4757, 4759};
        public static int randomVerac()
        {
                return Verac[(int)(Math.random()*Verac.length)];
        }

public static int Dharok[] = {4716, 4718, 4720, 4722};
        public static int randomDharok()
        {
                return Dharok[(int)(Math.random()*Dharok.length)];
        }
public static int barrows[] = {1215,1215,1215,1215,6585,4708,4710,4712,4714,4716,4718,4720,4722,4724,4726,4728,4730,4732,4734,4736,4738,4745,4747,4749,4751,4753,4755,4757,4759};

    public static int randomBarrows()
    {
       return barrows[(int)(Math.random()*barrows.length)];
    }

public static int barrows1[] = {1215,1215,1215,1215,6585,4708,4710,4712,4714,4716,4718,4720,4722,4724,4726,4728,4730,4732,4734,4736,4738,4745,4747,4749,4751,4753,4755,4757,4759};

    public static int random1Barrows()
    {
       return barrows[(int)(Math.random()*barrows.length)];
    }

public static int barrows2[] = {1215,1215,1215,1215,6585,4708,4710,4712,4714,4716,4718,4720,4722,4724,4726,4728,4730,4732,4734,4736,4738,4745,4747,4749,4751,4753,4755,4757,4759};

    public static int random2Barrows()
    {
       return barrows[(int)(Math.random()*barrows.length)];
    }

public static int barrows3[] = {1215,1215,1215,1215,6585,4708,4710,4712,4714,4716,4718,4720,4722,4724,4726,4728,4730,4732,4734,4736,4738,4745,4747,4749,4751,4753,4755,4757,4759};

    public static int random3Barrows()
    {
       return barrows[(int)(Math.random()*barrows.length)];
    }

public static int barrows4[] = {1215,1215,1215,1215,6585,4708,4710,4712,4714,4716,4718,4720,4722,4724,4726,4728,4730,4732,4734,4736,4738,4745,4747,4749,4751,4753,4755,4757,4759};

    public static int random4Barrows()
    {
       return barrows[(int)(Math.random()*barrows.length)];
    }	
public static int herby[] = {221,221,221,221,221,221,221,221,221,221,221,221,221,221,223,223,223,223,223,225,225,225,225,227,227,227,227,227,227,227,227,227,227,227,227,227,227,227,227,227,227,227,227,227,227,227,227,227,227,229,231,229,229,231,229,229,233,233,233,235,233,235,235,235,237,237,237,235,237,239,239,239,239,241,241,241,243,243,243,243,243,245,245,245,245,245,245,245,247,247,247,247,247,247,249,249,249,249,249,249,249,249,249,249,249,249,249,249,249,249,249,249,249,251,251,251,251,251,251,253,253,253,255,257,257,259,259,261,261,263,263,263,265,265,267,269,269};

    public static int randomHerby()
    {
    	return herby[(int)(Math.random()*herby.length)];
    }	
public static int bones[] = {526,526,526,526,526,526,526,526,526,526,526,526,526,526};

    public static int bones()
    {
       return bones[(int)(Math.random()*bones.length)];
    }

public static int bigbones[] = {532,532,532,532,532,532,532,532,532,532,532,532,532};

    public static int bigbones()
    {
       return bigbones[(int)(Math.random()*bigbones.length)];
    }

public static int babydbones[] = {534,534,534,534,534,534,534,534,534,534,534,534,534,534};

    public static int babydbones()
    {
       return babydbones[(int)(Math.random()*babydbones.length)];
    }

public static int dragonbones[] = {536,536,536,536,536,536,536,536,536,536,536,536,536,536};

    public static int dragonbones()
    {
       return dragonbones[(int)(Math.random()*dragonbones.length)];
    }

public static int zogrebones[] = {4812,4812,4812,4812,4812,4812,4812,4812,4812,4812,4812};

    public static int zogrebones()
    {
       return zogrebones[(int)(Math.random()*zogrebones.length)];
    }

public static int fayrgbones[] = {4830,4830,4830,4830,4830,4830,4830,4830,4830,4830,4830};

    public static int fayrgbones()
    {
       return fayrgbones[(int)(Math.random()*fayrgbones.length)];
    }

public static int raurgbones[] = {4832,4832,4832,4832,4832,4832,48324832,4832,4832,4832};

    public static int raurgbones()
    {
       return raurgbones[(int)(Math.random()*raurgbones.length)];
    }

public static int ourgbones[] = {4834,4834,4834,4834,4834,4834,4834,4834,4834,4834,4834};

    public static int ourgbones()
    {
       return ourgbones[(int)(Math.random()*ourgbones.length)];
    }

public static int babyreddrag[] = {3134,6524,6382,3121,6568,6143,6145,6147,6562,6562,6149,5698,6524,5016,5018,6568,3204,6106,6107,6108,6109,6110,6111};

    public static int babyreddrag()
    {
       return babyreddrag[(int)(Math.random()*babyreddrag.length)];
    }

public static int redhide[] = {1749,1749,1749,1749,1749,1749,1749,1749,1749,1749,1749,1749};

    public static int redhide()
    {
       return redhide[(int)(Math.random()*redhide.length)];
    }

public static int reddragon[] = {6128,6322,6324,6524,6362,6328,6562,6330,6129,6130,3134,6524,5730,3121,5698,5574,4675,5016,5018};

    public static int reddragon()
    {
       return reddragon[(int)(Math.random()*reddragon.length)];
    }

public static int mossgiant[] = {6585,5016,6260,5698,6131,6528,6237,6149,6133,6135,6528,5018,3121,4675,5576};

    public static int mossgiant()
    {
       return mossgiant[(int)(Math.random()*mossgiant.length)];
    }

public static int mossgiant2[] = {6585,6528,6382,5016,6216,6335,6337,6339,5018,6528,5730,3121,5698,5575,4675};

    public static int mossgiant2()
    {
       return mossgiant2[(int)(Math.random()*mossgiant2.length)];
    }

public static int firegiant[] = {6585,3121,6524,7158,4151,6153,6568,6237,5018,6137,6139,6524,6141,5016,7158,5730,6568,5698,4675,5574};

    public static int firegiant()
    {
       return firegiant[(int)(Math.random()*firegiant.length)];
    }

public static int bronzedragon[] = {6585,3204,6568,4089,6562,5576,6216,6237,4091,7158,4093,6568,6524,4503,6562,6382,4095,7158,4097,4675,4151,995,5730,5575,6524,4131,6562,4212,4224,995,4504,4505,4506,4507,4585};

    public static int bronzedragon()
    {
       return bronzedragon[(int)(Math.random()*bronzedragon.length)];
    }

public static int kalphitequeen[] = {3140,7158,13630,15352,15195,15156,1149};

    public static int kalphitequeen()
    {
       return kalphitequeen[(int)(Math.random()*kalphitequeen.length)];
    }

public static int irondragon[] = {6585,3204,995,4099,5574,4585,6524,6562,5698,4101,4103,4105,4107,995,5575,5730,4131,6524,4212,6562,4224,4508,4509,4510,4511,4512};

    public static int irondragon()
    {
       return irondragon[(int)(Math.random()*irondragon.length)];
    }

public static int steeldragon[] = {6585,6585,1149,1187,1149,1187,3204,995,6562,4109,6151,6260,6216,6524,5576,5730,4111,4113,6562,4115,4151,6524,4117,5575,6562,4131,4212,4224,4585,995};

    public static int steeldragon()
    {
       return steeldragon[(int)(Math.random()*steeldragon.length)];
    }

public static int dagannoths[] = {15333,15334,15335,15336,15345,15346,15347,15348,15349,14350};

    public static int dagannoths()
    {
       return dagannoths[(int)(Math.random()*dagannoths.length)];
    }
public static int slayeritems85[] = {};

    public static int randomSlayeritem85()
    {
    	return slayeritems85[(int)(Math.random()*slayeritems85.length)];
    }

public static int chaos[] = {592,592,2446,962,179,179,179,179,2446,2446,2446,2446,7158,6735,6735,6737,6737,592,592,592,592,6733,6733,592,592,592,592,6731,6731,6585,6585,6575,6575,6575,4087,4087,4087,1319,1319,1319,1319,1319,1319,1712,1712,1712,592,592,592,592,592,1712};

    public static int randomchaos()
    {
    	return chaos[(int)(Math.random()*chaos.length)];
    }

public static int guard[] = {995,995,995,995,962,995,995,995,995,995,995,995,995,995,995,4278,4278,4278,2681,995,995,995,995,995,995,995,995,995,995,995,995,995,995,995,995,995,995};

    public static int randomguard()
    {
    	return guard[(int)(Math.random()*guard.length)];
    }
public static int bandit[] = {6587,6589,6591,6593,6535,6597,6599,6601,6603,6605,6607,6609,6611,6613,6615,6617,6619,6621,6623,6625,6627,6629,6631,6633,6587,6589,6591,6593,6535,6597,6599,6601,6603,6605,6607,6609,6611,6613,6615,6617,6619,6621,6623,6625,6627,6629,6631,6633};

    public static int randomBandit()
    {
    	return guard[(int)(Math.random()*bandit.length)];
    }
public static int bandit2[] = {5611,5612,5613,5611,5612,5613,5611,5612,5613,5611,5612,5613,5611,5612,5613,5611,5612,5613};

    public static int randomBandit2()
    {
    	return guard[(int)(Math.random()*bandit2.length)];
    }

public static int leather[] = {1059,1061,1063,1095,1129,1131,1167,1169,1097,1133,1167};

    public static int randomleather()
    {
    	return leather[(int)(Math.random()*leather.length)];
    }

public static int greendhide[] = {1065,1065,1099,1099,1135,1135,2557,2581};

    public static int randomgreendhide()
    {
    	return greendhide[(int)(Math.random()*greendhide.length)];
    }

public static int bluedhide[] = {2487,2487,2493,2493,2499,2499,2557,2581};

    public static int randombluedhide()
    {
    	return bluedhide[(int)(Math.random()*bluedhide.length)];
    }

public static int reddhide[] = {2489,2489,2495,2495,2501,2501,2557,2581};

    public static int reddhide()
    {
    	return reddhide[(int)(Math.random()*reddhide.length)];
    }

public static int blackdhide[] = {2491,2491,2497,2497,2503,2503,2557,2581};

    public static int randomblackdhide()
    {
    	return blackdhide[(int)(Math.random()*blackdhide.length)];
    }
public static int LesserDemon[] = {1195/*Black_Kiteshield*/,554/*Fire_Rune*/,1525/*Herb*/,1109/*Mithril_Chainbody*/,1157/*Steel_Full_Helm*/,1617/*Uncut_Diamond*/,1454/*Cosmic_Talisman*/,444/*Gold_Ore*/,1191/*Iron_Kiteshield*/,1295/*Steel_Longsword*/,1621/*Uncut_Emerald*/,560/*Death_Rune*/,1993/*Jug_of_Wine*/,1141/*Steel_MedHelm*/,7639,1619/*Uncut_Ruby*/,562/*Chaos_Rune*/,1181/*Mithril_Sq_Shield*/,1353/*Steel_Axe*/,1325/*Steel_Scimitar*/,1623/*Uncut_sapphire*/,1195/*Black_Kiteshield*/,554/*Fire_Rune*/,1525/*Herb*/,1109/*Mithril_Chainbody*/,1157/*Steel_Full_Helm*/,1617/*Uncut_Diamond*/,1454/*Cosmic_Talisman*/,444/*Gold_Ore*/,7639,1191/*Iron_Kiteshield*/,1295/*Steel_Longsword*/,1621/*Uncut_Emerald*/,560/*Death_Rune*/,1993/*Jug_of_Wine*/,1141/*Steel_MedHelm*/,1619/*Uncut_Ruby*/,562/*Chaos_Rune*/,1181/*Mithril_Sq_Shield*/,1353/*Steel_Axe*/,1325/*Steel_Scimitar*/,1623/*Uncut_sapphire*/,2359/*Mithril_Bar*/,1462/*Nature_Tally*/,1195/*Black_Kiteshield*/,554/*Fire_Rune*/,1525/*Herb*/,1109/*Mithril_Chainbody*/,1157/*Steel_Full_Helm*/,1617/*Uncut_Diamond*/,1454/*Cosmic_Talisman*/,444/*Gold_Ore*/,1191/*Iron_Kiteshield*/,1295/*Steel_Longsword*/,1621/*Uncut_Emerald*/,560/*Death_Rune*/,1993/*Jug_of_Wine*/,1141/*Steel_MedHelm*/,1619/*Uncut_Ruby*/,7639,562/*Chaos_Rune*/,1181/*Mithril_Sq_Shield*/,1353/*Steel_Axe*/,1325/*Steel_Scimitar*/,7639,1623/*Uncut_sapphire*/,1147/*Rune_MedHelm*/,1247/*Rune_Spear*/,830/*Rune_Javelin*/,1195/*Black_Kiteshield*/,554/*Fire_Rune*/,1525/*Herb*/,1109/*Mithril_Chainbody*/,1157/*Steel_Full_Helm*/,1617/*Uncut_Diamond*/,1454/*Cosmic_Talisman*/,444/*Gold_Ore*/,1191/*Iron_Kiteshield*/,1295/*Steel_Longsword*/,1621/*Uncut_Emerald*/,560/*Death_Rune*/,1993/*Jug_of_Wine*/,1141/*Steel_MedHelm*/,1619/*Uncut_Ruby*/,562/*Chaos_Rune*/,1181/*Mithril_Sq_Shield*/,1353/*Steel_Axe*/,1325/*Steel_Scimitar*/,1623/*Uncut_sapphire*/,985/*Half_of_a_Key*/,986/*Half_of_a_Key*/,1195/*Black_Kiteshield*/,554/*Fire_Rune*/,1525/*Herb*/,1109/*Mithril_Chainbody*/,1157/*Steel_Full_Helm*/,7639,1617/*Uncut_Diamond*/,1454/*Cosmic_Talisman*/,444/*Gold_Ore*/,1191/*Iron_Kiteshield*/,1295/*Steel_Longsword*/,1621/*Uncut_Emerald*/,560/*Death_Rune*/,1993/*Jug_of_Wine*/,7639,1141/*Steel_MedHelm*/,1619/*Uncut_Ruby*/,562/*Chaos_Rune*/,1181/*Mithril_Sq_Shield*/,1353/*Steel_Axe*/,7639,1325/*Steel_Scimitar*/,1623/*Uncut_sapphire*/,2366/*Shield_Left_Half*/,1249/*Dragon_Spear*/};

    public static int randomLesserDemon()
    {
    	return LesserDemon[(int)(Math.random()*LesserDemon.length)];
    }
public static int BlessedSpider[] = {526,526,526};

    public static int randomBlessedSpider()
    {
    	return BlessedSpider[(int)(Math.random()*BlessedSpider.length)];
    }
public static int BlackDemon[] = {1283,1201,1215,1147,1373};

    public static int randomBlackDemon()
    {
    	return BlackDemon[(int)(Math.random()*BlackDemon.length)];
    }
public static int Chronozon[] = {779};

    public static int randomChronozon()
    {
    	return Chronozon[(int)(Math.random()*Chronozon.length)];
    }
public static int DungeonRat[] = {2134,2134,2134};

    public static int randomDungeonRat()
    {
    	return DungeonRat[(int)(Math.random()*DungeonRat.length)];
    }
public static int WerePeople[] = {1147,958,6814,1325,1621};

    public static int randomWerePeople()
    {
    	return WerePeople[(int)(Math.random()*WerePeople.length)];
    }
public static int Zombie[] = {556,1139,199,882,1349,1351,561,1291,313};

    public static int randomZombie()
    {
    	return Zombie[(int)(Math.random()*Zombie.length)];
    }
public static int GreaterDemon[] = {1247,1201,1073,1365};

    public static int randomGreaterDemon()
    {
    	return GreaterDemon[(int)(Math.random()*GreaterDemon.length)];
    }
public static int Guard[] = {1291,1101,1203,440,1935};

    public static int randomGuard()
    {
    	return Guard[(int)(Math.random()*Guard.length)];
    }
public static int WhiteKnight[] = {1375,1207,1141,1203,1619};

    public static int randomWhiteKnight()
    {
    	return WhiteKnight[(int)(Math.random()*WhiteKnight.length)];
    }
public static int ManWoman[] = {1438,1965,463,313,882,562,557,199,1440,1203,1139,995,554,563};

    public static int randomManWoman()
    {
    	return ManWoman[(int)(Math.random()*ManWoman.length)];
    }
public static int KalphiteQueen[] = {1245,995,1305,554,884,379,413,1319,2366,1623,556,560,1434,987,888,892,1617,245,1731,1149,985,3053,561,1359,1201,1621,565,1249,1525,563,1373,1347,1619,3140,1245,995,1305,554,884,379,413,1319,2366,1623,556,560,1434,987,888,892,1617,245,1731,1149,985,3053,3140,561,1359,1201,1621,565,1249,1525,563,1373,1347,1619,1245,995,1305,554,884,379,413,1319,2366,1623,556,560,1434,987,888,892,1617,245,1731,1149,985,3053,561,1359,1201,1621,565,1249,1525,563,1373,1347,1619,1245,995,1305,554,884,379,413,1319,2366,1623,556,560,1434,987,888,892,1617,245,1731,1149,985,3053,561,1359,1201,1621,565,1249,1525,563,1373,1347,1619,1245,995,1305,554,884,379,413,1319,2366,1623,556,560,1434,987,888,892,1617,245,1731,1149,985,3053,561,1359,1201,1621,565,1249,1525,563,1373,1347,1619,1245,995,1305,554,884,379,413,1319,2366,1623,556,560,1434,987,888,892,3140,1617,245,1731,1149,985,3053,561,1359,1201,1621,565,1249,1525,563,1373,1347,1619,1245,995,1305,554,884,379,413,7158,1319,2366,1623,556,560,1434,987,888,892,1617,245,1731,1149,985,3053,561,1359,1201,1621,565,1249,1525,563,1373,1347,3140,1619,1245,995,1305,554,884,379,413,1319,2366,1623,556,560,1434,987,888,892,1617,245,1731,1149,985,3053,561,1359,1201,1621,565,1249,1525,563,1373,1347,1619,1245,995,1305,554,884,379,413,1319,2366,1623,556,560,1434,987,888,892,1617,245,1731,1149,985,3053,561,1359,1201,1621,565,1249,1525,563,1373,1347,1619,1245,995,1305,554,884,379,413,1319,2366,1623,556,560,1434,987,888,892,1617,245,1731,1149,985,3053,561,1359,1201,1621,7158,565,1249,1525,563,1373,1347,1619,1245,995,1305,554,884,379,413,1319,2366,1623,556,560,1434,987,888,892,7158,1617,245,1731,1149,985,3053,561,1359,1201,1621,565,1249,1525,3140,563,1373,1347,1619,1245,995,1305,554,884,379,413,1319,2366,1623,556,560,3140,1434,987,888,892,1617,245,1731,1149,985,3053,561,1359,1201,1621,565,1249,1525,563,1373,1347,1619,1245,995,7158,1305,554,884,379,413,1319,2366,1623,556,560,1434,987,888,892,1617,245,1731,1149,985,3053,561,1359,1201,1621,565,1249,1525,563,1373,1347,1619,1245,995,1305,554,884,379,413,1319,2366,1623,556,560,1434,987,888,892,1617,245,1731,1149,985,3053,561,1359,1201,1621,565,1249,1525,563,1373,1347,1619,1245,995,1305,554,884,379,413,1319,2366,1623,556,560,1434,987,888,892,1617,245,1731,1149,985,3053,561,1359,1201,1621,565,1249,1525,563,1373,1347,1619,1245,995,1305,554,884,379,413,1319,2366,1623,556,560,1434,987,888,892,1617,245,1731,1149,985,3053,561,1359,1201,1621,565,1249,1525,563,1373,1347,1619,1245,995,1305,554,884,379,413,1319,2366,1623,556,560,1434,987,888,892,1617,245,1731,1149,985,3053,561,1359,7158,1201,1621,565,1249,1525,563,1373,1347,1619,1245,995,1305,554,884,379,413,1319,2366,1623,556,560,1434,987,888,892,1617,245,1731,1149,985,3053,561,1359,1201,1621,565,1249,1525,563,1373,1347,1619,1245,995,1305,554,884,379,413,1319,2366,1623,556,560,1434,987,888,892,1617,245,1731,1149,985,3053,561,1359,1201,1621,565,1249,1525,563,1373,1347,1619,1245,995,1305,554,884,379,413,1319,2366,1623,556,560,1434,987,888,892,1617,245,1731,1149,985,3053,561,1359,1201,1621,565,1249,1525,563,1373,1347,1619,1245,995,1305,554,884,379,413,1319,2366,1623,556,560,1434,987,888,892,1617,245,1731,1149,985,3053,561,1359,1201,1621,565,1249,1525,563,1373,1347,3140,1619,1245,995,1305,554,884,379,413,1319,2366,1623,556,560,1434,987,888,892,1617,245,1731,1149,985,3053,561,1359,1201,1621,565,1249,1525,563,1373,1347,1619,1245,995,1305,554,884,379,413,1319,2366,1623,556,560,1434,987,888,892,1617,245,1731,1149,985,3053,561,1359,1201,1621,565,1249,1525,563,1373,1347,1619,1245,995,1305,554,884,379,413,1319,2366,1623,556,560,1434,987,7158,888,892,1617,245,7158,1731,1149,985,3053,561,1359,1201,1621,565,7158,1249,1525,563,1373,1347,1619,1245,995,1305,554,884,379,413,1319,2366,1623,556,560,1434,987,888,892,1617,245,1731,1149,985,3053,561,1359,1201,1621,565,1249,1525,563,1373,1347,1619,7158,7158,3140,3140,3140};

    public static int randomKalphiteQueen()
    {
    	return KalphiteQueen[(int)(Math.random()*KalphiteQueen.length)];
    }
public static int KalphiteGuardian[] = {1211,1147,1201,1319,1365,563,379};

    public static int randomKalphiteGuardian()
    {
    	return KalphiteGuardian[(int)(Math.random()*KalphiteGuardian.length)];
    }
public static int KalphiteSoldier[] = {1145,1109,1181,1281,1617,554,1619};

    public static int randomKalphiteSoldier()
    {
    	return KalphiteSoldier[(int)(Math.random()*KalphiteSoldier.length)];
    }
public static int CrawlingHand[] = {1059,2902,2912,2922,2932,2942,3799,995,1617,1619,1621,1623,1635,1637,1639,1641,1643,985,987};

    public static int randomCrawlingHand()
    {
    	return CrawlingHand[(int)(Math.random()*CrawlingHand.length)];
    }
public static int Banshee[] = {199,562,564,556,554,221,995,1436,1191};

    public static int randomBanshee()
    {
    	return Banshee[(int)(Math.random()*Banshee.length)];
    }
public static int InfernalMage[] = {554,555,556,557,558,560,565,1393,1387,3053};

    public static int randomInfernalMage()
    {
    	return InfernalMage[(int)(Math.random()*InfernalMage.length)];
    }
public static int BloodVeld[] = {565,554,995,985,987,2293,1147,1181,1109,4125};

    public static int randomBloodVeld()
    {
    	return BloodVeld[(int)(Math.random()*BloodVeld.length)];
    }
public static int AberrantSpecter[] = {207,5281,3053,985,987,5106,5280};

    public static int randomAberrantSpecter()
    {
    	return AberrantSpecter[(int)(Math.random()*AberrantSpecter.length)];
    }
public static int Nechrayael[] = {4131,1365,1319,1303,1197,1073,1163,560,562,563,561,442,1734,995};

    public static int randomNechrayael()
    {
    	return Nechrayael[(int)(Math.random()*Nechrayael.length)];
    }
public static int AbyssalDemon[] = {556,562,560,565,1319,211,995,556,562,560,565,1319,211,4151,995,556,562,560,565,1319,211,995,556,562,560,565,1319,211,995,556,562,560,565,1319,211,995,556,562,560,565,1319,211,995,556,562,560,565,1319,211,995,556,562,560,565,1319,211,995,556,562,560,565,1319,211,995,556,562,560,565,4151,1319,211,995,556,562,560,565,1319,211,995,556,562,560,565,1319,211,995,556,562,560,565,1319,7321,211,995,556,4151,562,560,565,1319,4151,211,995,556,562,560,565,1319,211,995,556,562,560,565,1319,211,995,556,562,560,565,1319,7332,211,995,556,562,560,565,1319,211,995,556,562,4151,560,565,1319,211,995,556,562,560,565,1319,211,995,556,562,560,7321,565,1319,211,995,556,562,560,565,4151,1319,211,995,556,562,560,565,1319,211,995,556,562,560,565,1319,211,995,556,562,560,565,4151,1319,211,995,556,562,560,565,1319,211,995,556,562,560,4151,565,1319,211,995,556,562,560,565,1319,211,995,556,562,560,565,1319,211,995,556,562,560,565,1319,211,995,556,562,560,565,1319,4151,211,995,556,562,560,565,1319,211,995,556,562,560,565,1319,211,995,4151,556,562,560,565,1319,211,995};

    public static int randomAbyssalDemon()
    {
    	return AbyssalDemon[(int)(Math.random()*AbyssalDemon.length)];
    }
public static int AgrithNaar[] = {556,562,560,565,1319,211,995,556,562,560,565,1319,211,6541,995,556,562,560,565,1319,211,995,556,562,560,565,1319,211,995,556,562,560,565,1319,211,995,556,562,560,565,1319,211,995,556,562,560,565,1319,211,995,556,562,560,565,1319,211,995,556,562,560,565,1319,211,995,556,562,560,565,6541,1319,211,995,556,562,560,565,1319,211,995,556,562,560,565,1319,211,995,556,562,560,565,1319,7321,211,995,556,6541,562,560,565,1319,6541,211,995,556,562,560,565,1319,211,995,556,562,560,565,1319,211,995,556,562,560,565,1319,7332,211,995,556,562,560,565,1319,211,995,556,562,6541,560,565,1319,211,995,556,562,560,565,1319,211,995,556,562,560,7321,565,1319,211,995,556,562,560,565,6541,1319,211,995,556,562,560,565,1319,211,995,556,562,560,565,1319,211,995,556,562,560,565,6541,1319,211,995,556,562,560,565,1319,211,995,556,562,560,6541,565,1319,211,995,556,562,560,565,1319,211,995,556,562,560,565,1319,211,995,556,562,560,565,1319,211,995,556,562,560,565,1319,6541,211,995,556,562,560,565,1319,211,995,556,562,560,565,1319,211,995,6541,556,562,560,565,1319,211,995};

    public static int randomAgrithNaar()
    {
    	return AgrithNaar[(int)(Math.random()*AgrithNaar.length)];
    }
public static int AbyssalGuardian[] = {1438,1442,1462,1444,1454,1446,1452,1440,1448,5509,5510,5512,1436};

    public static int randomAbyssalGuardian()
    {
    	return AbyssalGuardian[(int)(Math.random()*AbyssalGuardian.length)];
    }
public static int AbyssalLeech[] = {1438,1442,1462,1444,1454,1446,1452,1440,1448,5509,5510,5512,1436};

    public static int randomAbyssalLeech()
    {
    	return AbyssalLeech[(int)(Math.random()*AbyssalLeech.length)];
    }
public static int AbyssalWalker[] = {1438,1442,1462,1444,1454,1446,1452,1440,1448,5509,5510,5512,1436,1163};

    public static int randomAbyssalWalker()
    {
    	return AbyssalWalker[(int)(Math.random()*AbyssalWalker.length)];
    }
public static int GiantLobster[] = {301,377,379,381,7536,7538,7540,7539,7541,301,377,379,381,7536,7538,7540,7541,301,377,379,381,7539,7536,7538,7540,7541,301,377,379,381,7536,7538,7540,7541};

    public static int randomGiantLobster()
    {
    	return GiantLobster[(int)(Math.random()*GiantLobster.length)];
    }
public static int Mummy[] = {4684,6992,6997,7003,6998,6999,7000,6971,4684,6992,6997,6998,6999,7000,6971,4684,6992,6997,6998,6999,7000,7003,6971,4684,6992,6997,6998,6999,7000,6971};

    public static int randomMummy()
    {
    	return Mummy[(int)(Math.random()*Mummy.length)];
    }
public static int Shade[] = {546,548,546,548,546,548,546,7398,548,546,548,546,548,546,548,546,548,546,7399,548,546,548,546,548,546,548,546,548,546,548,7400,546,548,546,548,546,548,546,548,546,548,};

    public static int randomShade()
    {
    	return Shade[(int)(Math.random()*Shade.length)];
    }
public static int Wyvern[] = {995,217,273,411,436,7394,474,560,995,217,273,411,436,474,560,995,217,273,411,436,474,560,7390,995,217,273,411,436,474,560,995,217,273,411,436,474,7386,560,995,217,273,411,436,474,560};

    public static int randomWyvern()
    {
    	return Wyvern[(int)(Math.random()*Wyvern.length)];
    }
public static int KBD[] = {995,217,273,411,436,7394,474,560,995,217,273,411,3140,436,474,560,4087,995,217,273,411,436,474,560,7390,7360,995,1187,217,273,411,436,474,560,995,217,273,7461,411,436,474,7386,560,995,217,273,411,436,474,560};

    public static int randomKBD()
    {
    	return KBD[(int)(Math.random()*KBD.length)];
    }
public static int Chaos[] = {995,217,273,411,436,7394,474,560,995,217,273,411,3140,436,474,560,4087,995,217,273,411,436,474,560,7390,7360,995,1187,217,273,411,436,474,560,995,217,273,7461,411,436,474,7386,560,995,217,273,411,436,474,560,995,217,273,411,436,7394,474,560,995,217,273,411,3140,436,474,560,4087,995,217,273,411,436,474,560,7390,7360,995,1187,217,273,411,436,474,560,995,217,273,7461,411,436,474,7386,560,995,217,273,411,436,474,560,995,217,273,411,436,7394,474,560,995,217,273,411,3140,436,474,560,4087,995,217,273,411,436,474,560,7390,7360,995,1187,217,273,411,436,474,560,995,217,273,7461,411,436,474,7386,560,995,217,273,411,436,474,560,995,217,273,411,436,7394,474,560,995,217,273,411,3140,436,474,560,4087,995,217,273,411,436,474,560,7390,7360,995,1187,217,273,411,436,474,560,995,217,273,7461,411,436,474,7386,560,995,217,273,411,436,474,560,995,217,273,411,436,7394,474,560,995,217,273,411,3140,436,474,560,4087,995,217,273,411,436,474,560,7390,7360,995,1187,217,273,411,436,474,560,995,217,273,7461,411,436,474,7386,560,995,217,273,411,436,474,560,995,217,273,411,436,7394,474,560,995,217,273,411,3140,436,474,560,4087,995,217,273,411,436,474,560,7390,7360,995,1187,217,273,411,436,474,560,995,217,273,7461,411,436,474,7386,560,995,217,273,411,436,474,560,995,217,273,411,436,7394,474,560,995,217,273,411,3140,436,474,560,4087,995,217,273,411,436,474,560,7158,7390,7360,995,1187,217,273,411,436,474,560,995,217,273,7461,411,436,474,7386,560,995,217,273,411,436,474,560,995,217,273,411,436,7394,474,560,995,217,273,411,3140,436,474,560,4087,995,217,273,411,436,474,560,7390,7360,995,1187,217,273,411,436,474,560,995,217,273,7461,411,436,474,7386,560,995,217,273,411,436,474,560};

    public static int randomChaos()
    {
    	return Chaos[(int)(Math.random()*Chaos.length)];
    }
public static int BattleMage[] = {2412,2412,2412,2412,2412,2413,2413,2413,2413,2413,2414,2414,2414,2414,2414,2415,2415,2415,2415,2415,2416,2416,2416,2416,2416,2417,2417,2417,2417,2417,3840,3840,3840,3840,3840,3842,3842,3842,3842,3842,3844,3844,3844,3844,3844,1033,1033,1033,1033,1033,1033,1033,1033,1033,1033,1035,1035,1035,1035,1035,1035,1035,1035,1035,1035,538,538,538,538,538,538,538,538,538,538,540,540,540,540,540,540,540,540,540,540};
    
	public static int randomBattleMage()
    {
	return BattleMage[(int)(Math.random()*BattleMage.length)];
    }
public static int DruidWarrior[] = {808,1069,1083,1105,1119,1141,1157,1177,1193,1207,1269,1281,1295,1325,1339,1353,1365,808,1069,1083,1105,1119,1141,1157,1177,1193,1207,1269,1281,1295,1325,1339,1353,1365,808,1069,1083,1105,1119,1141,1157,1177,1193,1207,1269,1281,1295,1325,1339,1353,1365,808,1069,1083,1105,1119,1141,1157,1177,1193,1207,1269,1281,1295,1325,1339,1353,1365,808,1069,1083,1105,1119,1141,1157,1177,1193,1207,1269,1281,1295,1325,1339,1353,1365,808,1069,1083,1105,1119,1141,1157,1177,1193,1207,1269,1281,1295,1325,1339,1353,1365,808,1069,1083,1105,1119,1141,1157,1177,1193,1207,1269,1281,1295,1325,1339,1353};
    
	public static int randomDruidWarrior()
    {
	return DruidWarrior[(int)(Math.random()*DruidWarrior.length)];
    }
public static int Dragon[] = {995,217,273,411,436,7394,474,560,995,217,273,411,436,474,560,995,217,273,411,436,474,560,7390,995,217,273,411,436,474,560,995,217,273,411,436,474,7386,560,995,217,273,411,436,474,560};

    public static int randomDragon()
    {
	return Dragon[(int)(Math.random()*Dragon.length)];
    }
public static int LifeBattle[] = {6128,6129,6130,6145,6151};

    public static int randomLifeBattle()
    {
	return LifeBattle[(int)(Math.random()*LifeBattle.length)];
    }
public static int GiantRockCrabs[] = {45,46,47,91,215,249,45,46,47,91,215,6129,249,45,46,47,91,215,249,45,46,47,91,215,249};

    public static int randomGiantRockCrabs()
    {
	return GiantRockCrabs[(int)(Math.random()*GiantRockCrabs.length)];
    }
public static int RockCrabs[] = {45,46,47,91,215,249,45,46,47,91,215,6151,249,45,46,47,91,215,249,45,46,47,91,215,249};

    public static int randomRockCrabs()
    {
	return RockCrabs[(int)(Math.random()*RockCrabs.length)];
    }
}