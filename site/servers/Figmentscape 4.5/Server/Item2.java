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
public static int runerock[] = {451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,1481,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451,451};

    public static int randomRuneRock()
    {
    	return runerock[(int)(Math.random()*runerock.length)];
    }	
public static int Barrows[] = {4708,4710,4712,4714,4716,4718,4720,4722,4724,4726,4728,4730,4732,4734,4736,4738,4743,4745,4747,4749,4751,4753,4755,4757,4759};

    public static int randomBarrows()
    {
    		return Barrows[(int)(Math.random()*Barrows.length)];
    }
public static int jad[] = {995};

    public static int randomjad()
    {
    		return jad[(int)(Math.random()*jad.length)];
    }
public static int fish[] = {385,385,379,379,379,379,379};

    public static int randomFish()
    {
    	return fish[(int)(Math.random()*fish.length)];
    }	
public static int damis[] = {4673};

    public static int randomdamis()
    {
    	return damis[(int)(Math.random()*damis.length)];
    }
public static int fareed[] = {4672};

    public static int randomfareed()
    {
    	return fareed[(int)(Math.random()*fareed.length)];
    }
public static int dustdevil[] = {592,592,592,298,298,592,592,592,592,592,592,592};

    public static int randomdustdevil()
    {
    	return dustdevil[(int)(Math.random()*dustdevil.length)];
    }		
public static int rat[] = {5698,1305,3105,1725,1704,1323,1153,1115,1067,1081,1157,1119,1069,1083};

    public static int randomrat()
    {
    	return rat[(int)(Math.random()*rat.length)];
    }
public static int green[] = {9811,2635,2637,2978,2980,2982,2984,2986,2988,2990,2992,2994,2957,2653,2655,2659,3478,2665,2661,2663,2667,3479,2673,2669,2671,3480,4724,4728,4730,4726,6570,1037,1050,6857,6859,6861,6863,6856,6858,6860,6862,6818,989,1149,3140,4087,1187,4587,2495,995,4151,4101,6585,3391,1725,3486,3481,3483,3488,2615,2619,2617,2621,2627,2623,2625,2627,2657,2653,2655,2659,2665,2661,2663,2667,2673,2669,2671,2675,2613,2607,2609,2611,2605,2599,2601,2603,2567,2583,2585,2589,7370,7366,7374,7382,7396,7392,7388,7394,7390,7386,3058,2631,2651,6137,6139,6141,6147,1127,3801,6965};

    public static int randomgreen()
    {
    	return green[(int)(Math.random()*green.length)];
    }

public static int red[] = {9811,2635,2637,2978,2980,2982,2984,2986,2988,2990,2992,2994,2957,2653,2655,2659,3478,2665,2661,2663,2667,3479,2673,2669,2671,3480,4724,4728,4730,4726,6570,1037,1050,6857,6859,6861,6863,6856,6858,6860,6862,6818,989,1149,3140,4087,1187,4587,2495,995,4151,4101,6585,3391,1725,3486,3481,3483,3488,2615,2619,2617,2621,2627,2623,2625,2627,2657,2653,2655,2659,2665,2661,2663,2667,2673,2669,2671,2675,2613,2607,2609,2611,2605,2599,2601,2603,2567,2583,2585,2589,7370,7366,7374,7382,7396,7392,7388,7394,7390,7386,3058,2631,2651,6137,6139,6141,6147,1127,3801,6965};

    public static int randomred()
    {
    	return red[(int)(Math.random()*red.length)];
    }

public static int black[] = {9811,2635,2637,2978,2980,2982,2984,2986,2988,2990,2992,2994,2957,2653,2655,2659,3478,2665,2661,2663,2667,3479,2673,2669,2671,3480,4724,4728,4730,4726,6570,1037,1050,6857,6859,6861,6863,6856,6858,6860,6862,6818,989,1149,3140,4087,1187,4587,2495,995,4151,4101,6585,3391,1725,3486,3481,3483,3488,2615,2619,2617,2621,2627,2623,2625,2627,2657,2653,2655,2659,2665,2661,2663,2667,2673,2669,2671,2675,2613,2607,2609,2611,2605,2599,2601,2603,2567,2583,2585,2589,7370,7366,7374,7382,7396,7392,7388,7394,7390,7386,3058,2631,2651,6137,6139,6141,6147,1127,3801,6965};

    public static int randomblack()
    {
    	return black[(int)(Math.random()*black.length)];
    }
public static int kamil[] = {4671};

    public static int randomkamil()
    {
    	return kamil[(int)(Math.random()*kamil.length)];
    }

public static int ce[] = {7158,6557,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,1050,592,592,1150,1409};

    public static int randomce()
    {
    	return ce[(int)(Math.random()*ce.length)];
    }
public static int soldier[] = {3101,6897,3202,1333,1319,1113,1127,1147,1093,1079,1373,4131,995};

    public static int randomsoldier()
    {
    	return soldier[(int)(Math.random()*soldier.length)];
    }

public static int blue[] = {6903,6903,6903,244,244,244,244,244,244,244,244,244,244,2506,2506,2506,2506,2506,15187,15186};

    public static int randomblue()
    {
    	return blue[(int)(Math.random()*blue.length)];
    }
public static int zammy[] = {223,225,229,251,253,267,269};

    public static int randomzammy()
    {
    	return zammy[(int)(Math.random()*zammy.length)];
    }
public static int des[] = {4670};

    public static int randomdes()
    {
    	return des[(int)(Math.random()*des.length)];
    }
public static int baby[] = {13800,995,995,995,15187,592,592,592,592,592,592,592,592};

    public static int randombaby()
    {
    	return baby[(int)(Math.random()*baby.length)];
    }
public static int steel[] = {1205,1377,143,3204,4587,5698,5370,6739,7158,3140,15346,15186,15187,2353,2353,2353,2353,2353,2353,2353,2353,2353,2353,2353,2353,2353};

    public static int randomsteel()
    {
    	return steel[(int)(Math.random()*steel.length)];
    }
public static int ogre[] = {837,5018,995};

    public static int randomogre()
    {
    	return ogre[(int)(Math.random()*ogre.length)];
    }
public static int daggy[] = {4830,4830,4830,4830,4830,4830,4830,4830,4830,4830,4830,4830,4830,4830,4830,4830,4830,4830,4830,4830,15341,15330,15340,15343,15329,15338,15332,15342,15339,85,15342,15337};

    public static int randomdaggy()
    {
    	return daggy[(int)(Math.random()*daggy.length)];
    }
public static int daggy2[] = {4830,4830,4830,4830,4830,4830,4830,4830,4830,4830,4830,4830,4830,4830,4830,15341,15330,15340,15343,15329,15338,15332,15342,15339,85,15342,15337};

    public static int randomdaggy2()
    {
    	return daggy2[(int)(Math.random()*daggy2.length)];
    }

public static int daggy3[] = {4830,4830,4830,4830,4830,4830,4830,4830,4830,4830,4830,4830,4830,4830,4830,4830,4830,4830,4830,4830,15341,15330,15340,15343,15329,15338,15332,15342,15339,85,15342,15337};

    public static int randomdaggy3()
    {
    	return daggy3[(int)(Math.random()*daggy3.length)];
    }
public static int ghost[] = {5698,1305,3105,1725,1704,1323,1153,1115,1067,1081,1157,1119,1069,1083};

    public static int randomghost()
    {
    	return ghost[(int)(Math.random()*ghost.length)];
    }
public static int knite[] = {3101,6897,3202,1333,1319,1113,1127,1147,1093,1079,1373,4131,995};

    public static int randomknite()
    {
    	return knite[(int)(Math.random()*knite.length)];
    }
public static int firegiant[] = {3101,6897,3202,1333,1319,1113,1127,1147,1093,1079,1373,4131,995};

    public static int randomfiregiant()
    {
    	return firegiant[(int)(Math.random()*firegiant.length)];
    }
public static int chicken[] = {4834};

    public static int randomchicken()
    {
    	return chicken[(int)(Math.random()*chicken.length)];
    }
public static int skeleton[] = {6137,6139,6141,995,995,995,995,995,995,995,995,995,995,995,995,995,995,995,995,995,995,995,995,995,995,995,995,995,995,995,995,995,995,995,995,995,995};

    public static int randomskeleton()
    {
    	return skeleton[(int)(Math.random()*skeleton.length)];
    }

public static int KBD[] = {2633,9803,9804, 9805, 9806, 9807, 9808, 9809, 9810, 9811,2635,2637,2978,2980,2982,2984,2986,2988,2990,2992,2994,2957,2653,2655,2659,3478,2665,2661,2663,2667,3479,2673,2669,2671,3480,4724,4728,4730,4726,6570,1037,1050,6857,6859,6861,6863,6856,6858,6860,6862,6818,989,1149,3140,4087,1187,4587,2495,995,4151,4101,6585,3391,1725,3486,3481,3483,3488,2615,2619,2617,2621,2627,2623,2625,2627,2657,2653,2655,2659,2665,2661,2663,2667,2673,2669,2671,2675,2613,2607,2609,2611,2605,2599,2601,2603,2567,2583,2585,2589,7370,7366,7374,7382,7396,7392,7388,7394,7390,7386,3058,2631,2651,6137,6139,6141,6147,1127,3801,6965};

    public static int randomKBD()
    {
    	return KBD[(int)(Math.random()*KBD.length)];
    }
public static int crawlinghand[] = {2615,1333,526,526,526,526,526,526};

    public static int randomcrawlinghand()
    {
    	return crawlinghand[(int)(Math.random()*crawlinghand.length)];
    }
public static int cavebug[] = {4119,4121,4123,4125,4127,4129,4131,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526};

    public static int randomcavebug()
    {
    	return cavebug[(int)(Math.random()*cavebug.length)];
    }
public static int jelly[] = {3122,6809,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526};

    public static int randomjelly()
    {
    	return jelly[(int)(Math.random()*jelly.length)];
    }
public static int aberrantspecter[] = {3840,3842,3844,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526};

    public static int randomaberrantspecter()
    {
    	return aberrantspecter[(int)(Math.random()*aberrantspecter.length)];
    }
public static int abyssaldemon[] = {4151,526,526,526,526,526,526,526};

    public static int randomabyssaldemon()
    {
    	return abyssaldemon[(int)(Math.random()*abyssaldemon.length)];
    }
public static int darkbeast[] = {6818,15156,526,526,526,526,526,526,526};

    public static int randomdarkbeast()
    {
    	return darkbeast[(int)(Math.random()*darkbeast.length)];
    }
public static int barbarian[] = {1725,1704,1038,3105,1305,5698,4587,4726,7386,7394,7390,995};

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
 public static int lesser[] = {
        4078, 4078, 4078, 995, 995, 995, 995, 995, 995, 995, 995, 995};

    public static int randomlesser() {
        return lesser[(int) (Math.random() * lesser.length)];
    }
    public static int greater[] = {
        4078, 4078, 4078, 995, 995, 995, 995, 995, 995, 995, 995, 995};

    public static int randomgreater() {
        return greater[(int) (Math.random() * greater.length)];
    }
    public static int koeschi[] = {
        10387};

    public static int randomkoeschi() {
        return koeschi[(int) (Math.random() * koeschi.length)];
    }
    public static int aar[] = {
        5043, 5043, 5043, 995, 995, 995, 995, 995, 995, 995};

    public static int randomaar() {
        return aar[(int) (Math.random() * aar.length)];
    }
    public static int aas[] = {
        5043, 5043, 5043, 995, 995, 995, 995, 995, 995, 995};
    public static int randomaas() {
        return aar[(int) (Math.random() * aas.length)];
    }
    public static int aam[] = {
        5043, 5043, 5043, 995, 995, 995, 995, 995, 995, 995};

    public static int randomaam() {
        return aam[(int) (Math.random() * aam.length)];
    }

public static int KQ[] = {2633,9803,9804, 9805, 9806, 9807, 9808, 9809, 9810, 9811,2635,2637,2978,2980,2982,2984,2986,2988,2990,2992,2994,2957,2653,2655,2659,3478,2665,2661,2663,2667,3479,2673,2669,2671,3480,4724,4728,4730,4726,6570,1037,1050,6857,6859,6861,6863,6856,6858,6860,6862,6818,989,1149,3140,4087,1187,4587,2495,995,4151,4101,6585,3391,1725,3486,3481,3483,3488,2615,2619,2617,2621,2627,2623,2625,2627,2657,2653,2655,2659,2665,2661,2663,2667,2673,2669,2671,2675,2613,2607,2609,2611,2605,2599,2601,2603,2567,2583,2585,2589,7370,7366,7374,7382,7396,7392,7388,7394,7390,7386,3058,2631,2651,6137,6139,6141,6147,1127,3801,6965,2633,9803,9804, 9805, 9806, 9807, 9808, 9809, 9810, 9811,2635,2637,2978,2980,2982,2984,2986,2988,2990,2992,2994,2957,2653,2655,2659,3478,2665,2661,2663,2667,3479,2673,2669,2671,3480,4724,4728,4730,4726,6570,1037,1050,6857,6859,6861,6863,6856,6858,6860,6862,6818,989};

    public static int randomKQ()
    {
    	return KQ[(int)(Math.random()*KQ.length)];
    }

public static int Ahrim[] = {995, 995, 995, 995, 995};
        public static int randomAhrim()
        {
                return Ahrim[(int)(Math.random()*Ahrim.length)];
        }

public static int Guthan[] = {995, 995, 995, 995};
        public static int randomGuthan()
        {
                return Guthan[(int)(Math.random()*Guthan.length)];
        }

public static int Karil[] = {995, 995, 995, 995};
        public static int randomKaril()
        {
                return Karil[(int)(Math.random()*Karil.length)];
        }

public static int Torag[] = {995, 995, 995, 995};
        public static int randomTorag()
        {
                return Torag[(int)(Math.random()*Torag.length)];
        }

public static int Verac[] = {995, 995, 995, 995};
        public static int randomVerac()
        {
                return Verac[(int)(Math.random()*Verac.length)];
        }

public static int Dharok[] = {995, 995, 995, 995};
        public static int randomDharok()
        {
                return Dharok[(int)(Math.random()*Dharok.length)];
        }
public static int Bunny[] = {1037, 1037, 1037, 1037, 1037, 1037, 1037, 1037, 1037, 6102};

    public static int randomBunny()
    {
    	return Bunny[(int)(Math.random()*Bunny.length)];
    }
}