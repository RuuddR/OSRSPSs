// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

import sign.signlink;

public class Class50_Sub1_Sub4_Sub5 extends Class50_Sub1_Sub4
{

    public Class47 method603(int i)
    {
        int j = -1;
        if(i != 0)
            anInt1728 = 109;
        if(anInt1725 != -1)
        {
            Class49 class49 = Class49.aClass49Array824[anInt1725];
            int k = class49.anInt826;
            int l = class49.anInt827;
            int i1 = class49.anInt828;
            int j1 = client.anIntArray1214[i1 - l];
            j = aClient1723.anIntArray1039[k] >> l & j1;
        } else
        if(anInt1726 != -1)
            j = aClient1723.anIntArray1039[anInt1726];
        if(j < 0 || j >= anIntArray1727.length || anIntArray1727[j] == -1)
            return null;
        else
            return Class47.method423(anIntArray1727[j]);
    }

    public Class50_Sub1_Sub4_Sub5(int i, int j, int k, int l, int i1, byte byte0, int j1, 
            boolean flag, int k1, int l1)
    {
        aBoolean1714 = false;
        anInt1720 = j1;
        anInt1721 = i1;
        anInt1722 = l1;
        anInt1715 = k1;
        anInt1716 = l;
        anInt1717 = j;
        anInt1718 = k;
        if(i != -1)
        {
            aClass14_1724 = Class14.aClass14Array293[i];
            anInt1730 = 0;
            anInt1729 = client.anInt1325 - 1;
            if(flag && aClass14_1724.anInt298 != -1)
            {
                anInt1730 = (int)(Math.random() * (double)aClass14_1724.anInt294);
                anInt1729 -= (int)(Math.random() * (double)aClass14_1724.method205(0, anInt1730));
            }
        }
        Class47 class47 = Class47.method423(anInt1720);
        anInt1725 = class47.anInt778;
        anInt1726 = class47.anInt781;
        anIntArray1727 = class47.anIntArray805;
        if(byte0 != 3)
            anInt1719 = -126;
    }

    public Class50_Sub1_Sub4_Sub4 method561(byte byte0)
    {
        if(byte0 != 3)
            aBoolean1714 = !aBoolean1714;
        int i = -1;
        if(aClass14_1724 != null)
        {
            int j = client.anInt1325 - anInt1729;
            if(j > 100 && aClass14_1724.anInt298 > 0)
                j = 100;
            while(j > aClass14_1724.method205(0, anInt1730)) 
            {
                j -= aClass14_1724.method205(0, anInt1730);
                anInt1730++;
                if(anInt1730 < aClass14_1724.anInt294)
                    continue;
                anInt1730 -= aClass14_1724.anInt298;
                if(anInt1730 >= 0 && anInt1730 < aClass14_1724.anInt294)
                    continue;
                aClass14_1724 = null;
                break;
            }
            anInt1729 = client.anInt1325 - j;
            if(aClass14_1724 != null)
                i = aClass14_1724.anIntArray295[anInt1730];
        }
        Class47 class47;
        if(anIntArray1727 != null)
            class47 = method603(0);
        else
            class47 = Class47.method423(anInt1720);
        if(class47 == null)
        {
            return null;
        } else
        {
            Class50_Sub1_Sub4_Sub4 class50_sub1_sub4_sub4 = class47.method431(anInt1721, anInt1722, anInt1715, anInt1716, anInt1717, anInt1718, i);
            return class50_sub1_sub4_sub4;
        }
    }

    public boolean aBoolean1714;
    public int anInt1715;
    public int anInt1716;
    public int anInt1717;
    public int anInt1718;
    public int anInt1719;
    public int anInt1720;
    public int anInt1721;
    public int anInt1722;
    public static client aClient1723;
    public Class14 aClass14_1724;
    public int anInt1725;
    public int anInt1726;
    public int anIntArray1727[];
    public int anInt1728;
    public int anInt1729;
    public int anInt1730;
}
