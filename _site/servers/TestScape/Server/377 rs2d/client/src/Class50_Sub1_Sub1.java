// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

import sign.signlink;

public class Class50_Sub1_Sub1 extends Class50_Sub1
{

    public static void method444(boolean flag, int i, int j, int ai[])
    {
        if(!flag)
            anInt1423 = -394;
        anIntArray1424 = ai;
        anInt1425 = i;
        anInt1426 = j;
        method446(0, 0, j, i, true);
    }

    public static void method445(byte byte0)
    {
        anInt1429 = 0;
        anInt1427 = 0;
        anInt1430 = anInt1425;
        anInt1428 = anInt1426;
        anInt1431 = anInt1430 - 1;
        if(byte0 != 82)
            anInt1423 = -258;
        anInt1432 = anInt1430 / 2;
    }

    public static void method446(int i, int j, int k, int l, boolean flag)
    {
        if(j < 0)
            j = 0;
        if(i < 0)
            i = 0;
        if(l > anInt1425)
            l = anInt1425;
        if(k > anInt1426)
            k = anInt1426;
        anInt1429 = j;
        anInt1427 = i;
        anInt1430 = l;
        anInt1428 = k;
        if(!flag)
        {
            return;
        } else
        {
            anInt1431 = anInt1430 - 1;
            anInt1432 = anInt1430 / 2;
            anInt1433 = anInt1428 / 2;
            return;
        }
    }

    public static void method447(int i)
    {
        int j = anInt1425 * anInt1426;
        if(i != 4)
            aBoolean1421 = !aBoolean1421;
        for(int k = 0; k < j; k++)
            anIntArray1424[k] = 0;

    }

    public static void method448(boolean flag, int i, int j, int k, int l, int i1, int j1)
    {
        if(j1 < anInt1429)
        {
            k -= anInt1429 - j1;
            j1 = anInt1429;
        }
        if(j < anInt1427)
        {
            l -= anInt1427 - j;
            j = anInt1427;
        }
        if(j1 + k > anInt1430)
            k = anInt1430 - j1;
        if(j + l > anInt1428)
            l = anInt1428 - j;
        int k1 = 256 - i1;
        int l1 = (i >> 16 & 0xff) * i1;
        int i2 = (i >> 8 & 0xff) * i1;
        int j2 = (i & 0xff) * i1;
        if(flag)
            aBoolean1421 = !aBoolean1421;
        int j3 = anInt1425 - k;
        int k3 = j1 + j * anInt1425;
        for(int l3 = 0; l3 < l; l3++)
        {
            for(int i4 = -k; i4 < 0; i4++)
            {
                int k2 = (anIntArray1424[k3] >> 16 & 0xff) * k1;
                int l2 = (anIntArray1424[k3] >> 8 & 0xff) * k1;
                int i3 = (anIntArray1424[k3] & 0xff) * k1;
                int j4 = ((l1 + k2 >> 8) << 16) + ((i2 + l2 >> 8) << 8) + (j2 + i3 >> 8);
                anIntArray1424[k3++] = j4;
            }

            k3 += j3;
        }

    }

    public static void method449(int i, int j, int k, byte byte0, int l, int i1)
    {
        if(i1 < anInt1429)
        {
            l -= anInt1429 - i1;
            i1 = anInt1429;
        }
        if(j < anInt1427)
        {
            i -= anInt1427 - j;
            j = anInt1427;
        }
        if(i1 + l > anInt1430)
            l = anInt1430 - i1;
        if(j + i > anInt1428)
            i = anInt1428 - j;
        int j1 = anInt1425 - l;
        int k1 = i1 + j * anInt1425;
        for(int l1 = -i; l1 < 0; l1++)
        {
            for(int i2 = -l; i2 < 0; i2++)
                anIntArray1424[k1++] = k;

            k1 += j1;
        }

        if(byte0 == -24);
    }

    public static void method450(int i, int j, int k, int l, int i1, int j1)
    {
        method452(i1, l, j, j1, true);
        method452(i1, l, (j + k) - 1, j1, true);
        if(i != 0)
            anInt1420 = -278;
        method454(i1, l, k, false, j);
        method454((i1 + j1) - 1, l, k, false, j);
    }

    public static void method451(int i, int j, int k, int l, int i1, int j1, byte byte0)
    {
        if(byte0 != -113)
            return;
        method453(i1, i, j, 1388, j1, k);
        method453((i1 + l) - 1, i, j, 1388, j1, k);
        if(l >= 3)
        {
            method455(0, i1 + 1, i, k, l - 2, j1);
            method455(0, i1 + 1, (i + j) - 1, k, l - 2, j1);
        }
    }

    public static void method452(int i, int j, int k, int l, boolean flag)
    {
        if(k < anInt1427 || k >= anInt1428)
            return;
        if(i < anInt1429)
        {
            l -= anInt1429 - i;
            i = anInt1429;
        }
        if(i + l > anInt1430)
            l = anInt1430 - i;
        int i1 = i + k * anInt1425;
        if(!flag)
        {
            for(int j1 = 1; j1 > 0; j1++);
        }
        for(int k1 = 0; k1 < l; k1++)
            anIntArray1424[i1 + k1] = j;

    }

    public static void method453(int i, int j, int k, int l, int i1, int j1)
    {
        if(i < anInt1427 || i >= anInt1428)
            return;
        if(j < anInt1429)
        {
            k -= anInt1429 - j;
            j = anInt1429;
        }
        if(j + k > anInt1430)
            k = anInt1430 - j;
        int k1 = 256 - i1;
        int l1 = (j1 >> 16 & 0xff) * i1;
        int i2 = (j1 >> 8 & 0xff) * i1;
        int j2 = (j1 & 0xff) * i1;
        int j3 = j + i * anInt1425;
        for(int k3 = 0; k3 < k; k3++)
        {
            int k2 = (anIntArray1424[j3] >> 16 & 0xff) * k1;
            int l2 = (anIntArray1424[j3] >> 8 & 0xff) * k1;
            int i3 = (anIntArray1424[j3] & 0xff) * k1;
            int l3 = ((l1 + k2 >> 8) << 16) + ((i2 + l2 >> 8) << 8) + (j2 + i3 >> 8);
            anIntArray1424[j3++] = l3;
        }

        if(l != 1388)
            anInt1420 = -36;
    }

    public static void method454(int i, int j, int k, boolean flag, int l)
    {
        if(flag)
            return;
        if(i < anInt1429 || i >= anInt1430)
            return;
        if(l < anInt1427)
        {
            k -= anInt1427 - l;
            l = anInt1427;
        }
        if(l + k > anInt1428)
            k = anInt1428 - l;
        int i1 = i + l * anInt1425;
        for(int j1 = 0; j1 < k; j1++)
            anIntArray1424[i1 + j1 * anInt1425] = j;

    }

    public static void method455(int i, int j, int k, int l, int i1, int j1)
    {
        if(k < anInt1429 || k >= anInt1430)
            return;
        if(j < anInt1427)
        {
            i1 -= anInt1427 - j;
            j = anInt1427;
        }
        if(j + i1 > anInt1428)
            i1 = anInt1428 - j;
        int k1 = 256 - j1;
        int l1 = (l >> 16 & 0xff) * j1;
        int i2 = (l >> 8 & 0xff) * j1;
        int j2 = (l & 0xff) * j1;
        if(i != 0)
        {
            for(int j3 = 1; j3 > 0; j3++);
        }
        int k3 = k + j * anInt1425;
        for(int l3 = 0; l3 < i1; l3++)
        {
            int k2 = (anIntArray1424[k3] >> 16 & 0xff) * k1;
            int l2 = (anIntArray1424[k3] >> 8 & 0xff) * k1;
            int i3 = (anIntArray1424[k3] & 0xff) * k1;
            int i4 = ((l1 + k2 >> 8) << 16) + ((i2 + l2 >> 8) << 8) + (j2 + i3 >> 8);
            anIntArray1424[k3] = i4;
            k3 += anInt1425;
        }

    }

    public Class50_Sub1_Sub1()
    {
    }

    public static int anInt1420;
    public static boolean aBoolean1421;
    public static boolean aBoolean1422 = true;
    public static int anInt1423 = -291;
    public static int anIntArray1424[];
    public static int anInt1425;
    public static int anInt1426;
    public static int anInt1427;
    public static int anInt1428;
    public static int anInt1429;
    public static int anInt1430;
    public static int anInt1431;
    public static int anInt1432;
    public static int anInt1433;
    public static boolean aBoolean1434;

}
