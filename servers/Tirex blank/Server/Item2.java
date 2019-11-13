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
public static int skeleton[] = {6137,6139,6141,995,995,995,995,995,995,995,995,995,995,995,995,995,995,995,995,995,995,995,995,995,995,995,995,995,995,995,995,995,995,995,995,995,995};

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
public static int abyssaldemon[] = {4151,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526};

    public static int randomabyssaldemon()
    {
    	return abyssaldemon[(int)(Math.random()*abyssaldemon.length)];
    }
public static int darkbeast[] = {6818,11192,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526,526};

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

public static int KQ[] = {2633,2635,2637,2978,2980,2982,2984,2986,2988,2990,2992,2994,2957,2653,2655,2659,3478,2665,2661,2663,2667,3479,2673,2669,2671,3480,4724,4728,4730,4726,6570,1037,1050,6857,6859,6861,6863,6856,6858,6860,6862,6818,989};

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
}