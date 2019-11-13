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

public static int KBD[] = {4734,1575,3140,1575,1575,4119,4119,4119,4119,4119,2503,2503,2503,2503,2503,2503,2503,2503,2503,1249,1846,1846,1846,4158,4158,4158,4158,4158,2476,2476,2476,2476,2476,2476,2476,2476,2476,2476,2476,2476,2476,2476,2476,2476,2476,2476,2476,1263,1635,1635,1635,1635,1635,1635,1635,1635,1635,1635,1635,1635,1635,1635,1635,1635,1635,1635,1635,1635,1635,1635,1635,1635,1635,1635,1635,1635,1635,1580,5013,5013,5013,1611,1611,1611,1611,1611,1611,1611,1611,1611,1611,1611,1611,2599,2599,2599,2599,2599,2599,4170,4170,4170,1641,1641,1641,1641,1641,1641,1641,1641,1641,1641,1641,1641,1641,1641,1641,1641,1641,1641,1641,1641,1641,1641,1641,1641,1641,1641,1641,1641,1641,1641,1641,1641,1635,1635,1635,1635,1635,1635,1635,1635,1635,1635,1635,1635,1635,1635,1635,1635,1635,1635,2683,2683};

    public static int randomKBD()
    {
    	return KBD[(int)(Math.random()*KBD.length)];
    }	

public static int fish[] = {385,385,379,379,379,379,379};

    public static int randomFish()
    {
    	return fish[(int)(Math.random()*fish.length)];
    }	

public static int abyssal[] = {2683,2683,2683,2683,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,1377,1377,592,4111,4111,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,1434,592,592,592,592,1434,1434,1434,592,592,1434,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,405,405,405,405,405,405,405,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,405,405,405,405,405,405,405,405,405,405,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,4113,4113,4113,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,2503,2503,2503,2503,592,592,592,592,592,592,592,592,2503,2503,2503,2503,2503,2503,2503,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,2503,2503,2503,2503,2503,2503,2503,2503,2503,2503,2503,2503,2503,2503,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,2489,2489,2489,2489,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,2489,2489,2489,2489,2489,2489,592,592,592,592,2489,2489,2489,592,592,592,592,592,592,592,592,592,592,592,592,2493,2493,2493,2493,2493,2493,2493,2493,2493,592,592,592,592,2493,2493,2493,2493,2493,2493,2493,2493,2493,2493,2493,2493,592,592,592,592,592,2493,2493,2493,2493,2493,592,592,592,592,2493,2493,2493,2493,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,861,861,861,861,861,861,861,861,592,592,592,592,592,592,861,861,861,861,592,592,592,592,861,861,861,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,859,859,859,859,859,859,859,859,859,859,859,859,859,859,859,859,859,859,859,859,592,592,592,592,592,592,592,859,859,859,859,859,859,859,859,859,859,592,592,592,592,592,592,592,592,859,859,859,859,859,859,859,859,859,859,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,589,589,589,589,589,589,589,589,589,589,589,589,589,589,589,589,589,589,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,589,589,589,589,589,589,589,589,589,589,589,3753,3753,3753,3753,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,3753,3755,3755,3755,592,592,592,592,592,592,592,592,592,592,3751,3751,3751,3751,3751,3751,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,3749,3749,3749,3749,3749,3749,3749,3749,3749,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,579,579,579,579,579,579,579,579,579,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,579,579,579,579,579,579,579,579,579,579,579,579,579,579,579,579,579,579,579,579,579,579,579,579,579,579,579,579,579,579,579,579,579,579,579,579,579,579,579,579,579,579,579,579,579,579,579,579,579,579,579,579,579,579,579,579,579,579,579,579,579,579,579,579,579,579,579,579,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,577,577,577,577,577,577,577,577,577,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,577,577,577,577,577,577,577,577,577,577,577,577,577,577,577,577,577,577,577,577,577,577,577,577,577,577,577,577,577,577,577,577,577,577,577,577,577,577,577,577,577,577,577,577,577,577,577,577,577,577,577,577,577,577,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,1409,1409,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,776,776,776,776,592,592,592,592,592,592,592,592,592,592,592,592,776,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,772,772,772,772,772,772,772,772,772,772,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,772,772,772,772,772,772,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,772,772,772,592,592,592,592,592,592,592,592,542,542,542,542,542,542,542,542,542,542,542,542,542,542,542,542,542,542,542,542,542,542,542,542,542,542,542,542,542,542,542,542,542,542,542,542,542,542,542,542,542,542,542,542,542,542,542,542,542,542,542,542,542,542,542,542,542,542,542,542,542,542,542,542,542,542,542,542,542,542,542,542,542,542,542,542,542,542,542,542,542,542,542,542,542,542,542,542,542,542,542,542,542,542,542,542,542,542,542,542,542,542,542,542,542,542,542,542,542,542,542,542,542,542,542,542,542,542,542,542,542,542,542,542,542,542,542,542,542,542,542,542,542,542,542,542,542,542,542,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,2683,2683,2683,2683,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,544,544,544,544,544,544,544,544,544,544,544,544,544,544,544,544,544,544,544,544,544,544,544,544,544,544,544,544,544,544,544,544,544,544,544,544,544,544,544,544,544,544,544,544,544,544,544,544,544,544,544,544,544,544,544,544,544,544,544,544,544,544,544,544,544,544,544,544,544,544,544,544,544,544,544,544,544,544,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,772,772,772,772,772,772,772,772,772,772,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,772,772,772,772,772,772,772,772,772,772,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,772,772,772,772,772,772,772,772,772,772,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,2683,2683,2683,2683,2683,2683,2683,2683,2683,2683,2683,2683,2683,2683,2683,2683,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,592,2683,2683,2683,2683,2683,2683,2683,2683,2683,2683,2683,2683,2683,2683,2683,2683,2683,2683,2683,2683,2683,2683,2683,2683,2683,2683,2683,2683,2683,2683,2683,2683,2683,2683,2683,2683,2683,2683,2683,2683,2683,2683,2683,2683,2683,2683,2683,2683,2683,2683,2683,2683,2683,2683,2683,2683,2683,2683,2683,2683,2683,2683,2683,2683,2683,2683,2683,2683,2683,2683,2683,2683,2683,2683,2683,2683,2683,2683,2683,2683,2683,2683,2683,2683,2683,2683,2683,2683,2683,2683,2683,2683,2683,2683,2683,2683,2683,2683,2683,2683};

    public static int randomAbyssal()
    {
    	return abyssal[(int)(Math.random()*abyssal.length)];
    }	

public static int herby[] = {221,221,221,221,221,221,221,221,221,221,221,221,221,221,223,223,223,223,223,225,225,225,225,227,227,227,227,227,227,227,227,227,227,227,227,227,227,227,227,227,227,227,227,227,227,227,227,227,227,229,231,229,229,231,229,229,233,233,233,235,233,235,235,235,237,237,237,235,237,239,239,239,239,241,241,241,243,243,243,243,243,245,245,245,245,245,245,245,247,247,247,247,247,247,249,249,249,249,249,249,249,249,249,249,249,249,249,249,249,249,249,249,249,251,251,251,251,251,251,253,253,253,255,257,257,259,259,261,261,263,263,263,265,265,267,269,269};

    public static int randomHerby()
    {
    	return herby[(int)(Math.random()*herby.length)];
    }	

}