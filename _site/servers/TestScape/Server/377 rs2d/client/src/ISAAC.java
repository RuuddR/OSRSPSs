// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

import sign.signlink;

public class ISAAC
{

    public ISAAC(int i, int ai[])
    {
        anIntArray524 = new int[256];
        anIntArray523 = new int[256];
        for(int j = 0; j < ai.length; j++)
            anIntArray523[j] = ai[j];

        if(i >= 0)
        {
            throw new NullPointerException();
        } else
        {
            method298();
            return;
        }
    }

    public int method296()
    {
        if(anInt522-- == 0)
        {
            method297();
            anInt522 = 255;
        }
        return anIntArray523[anInt522];
    }

    public void method297()
    {
        anInt526 += ++anInt527;
        for(int i = 0; i < 256; i++)
        {
            int j = anIntArray524[i];
            if((i & 3) == 0)
                anInt525 ^= anInt525 << 13;
            else
            if((i & 3) == 1)
                anInt525 ^= anInt525 >>> 6;
            else
            if((i & 3) == 2)
                anInt525 ^= anInt525 << 2;
            else
            if((i & 3) == 3)
                anInt525 ^= anInt525 >>> 16;
            anInt525 += anIntArray524[i + 128 & 0xff];
            int k;
            anIntArray524[i] = k = anIntArray524[(j & 0x3fc) >> 2] + anInt525 + anInt526;
            anIntArray523[i] = anInt526 = anIntArray524[(k >> 8 & 0x3fc) >> 2] + j;
        }

    }

    public void method298()
    {
        int i1;
        int j1;
        int k1;
        int l1;
        int i2;
        int j2;
        int k2;
        int l = i1 = j1 = k1 = l1 = i2 = j2 = k2 = 0x9e3779b9;
        for(int i = 0; i < 4; i++)
        {
            l ^= i1 << 11;
            k1 += l;
            i1 += j1;
            i1 ^= j1 >>> 2;
            l1 += i1;
            j1 += k1;
            j1 ^= k1 << 8;
            i2 += j1;
            k1 += l1;
            k1 ^= l1 >>> 16;
            j2 += k1;
            l1 += i2;
            l1 ^= i2 << 10;
            k2 += l1;
            i2 += j2;
            i2 ^= j2 >>> 4;
            l += i2;
            j2 += k2;
            j2 ^= k2 << 8;
            i1 += j2;
            k2 += l;
            k2 ^= l >>> 9;
            j1 += k2;
            l += i1;
        }

        for(int j = 0; j < 256; j += 8)
        {
            l += anIntArray523[j];
            i1 += anIntArray523[j + 1];
            j1 += anIntArray523[j + 2];
            k1 += anIntArray523[j + 3];
            l1 += anIntArray523[j + 4];
            i2 += anIntArray523[j + 5];
            j2 += anIntArray523[j + 6];
            k2 += anIntArray523[j + 7];
            l ^= i1 << 11;
            k1 += l;
            i1 += j1;
            i1 ^= j1 >>> 2;
            l1 += i1;
            j1 += k1;
            j1 ^= k1 << 8;
            i2 += j1;
            k1 += l1;
            k1 ^= l1 >>> 16;
            j2 += k1;
            l1 += i2;
            l1 ^= i2 << 10;
            k2 += l1;
            i2 += j2;
            i2 ^= j2 >>> 4;
            l += i2;
            j2 += k2;
            j2 ^= k2 << 8;
            i1 += j2;
            k2 += l;
            k2 ^= l >>> 9;
            j1 += k2;
            l += i1;
            anIntArray524[j] = l;
            anIntArray524[j + 1] = i1;
            anIntArray524[j + 2] = j1;
            anIntArray524[j + 3] = k1;
            anIntArray524[j + 4] = l1;
            anIntArray524[j + 5] = i2;
            anIntArray524[j + 6] = j2;
            anIntArray524[j + 7] = k2;
        }

        for(int k = 0; k < 256; k += 8)
        {
            l += anIntArray524[k];
            i1 += anIntArray524[k + 1];
            j1 += anIntArray524[k + 2];
            k1 += anIntArray524[k + 3];
            l1 += anIntArray524[k + 4];
            i2 += anIntArray524[k + 5];
            j2 += anIntArray524[k + 6];
            k2 += anIntArray524[k + 7];
            l ^= i1 << 11;
            k1 += l;
            i1 += j1;
            i1 ^= j1 >>> 2;
            l1 += i1;
            j1 += k1;
            j1 ^= k1 << 8;
            i2 += j1;
            k1 += l1;
            k1 ^= l1 >>> 16;
            j2 += k1;
            l1 += i2;
            l1 ^= i2 << 10;
            k2 += l1;
            i2 += j2;
            i2 ^= j2 >>> 4;
            l += i2;
            j2 += k2;
            j2 ^= k2 << 8;
            i1 += j2;
            k2 += l;
            k2 ^= l >>> 9;
            j1 += k2;
            l += i1;
            anIntArray524[k] = l;
            anIntArray524[k + 1] = i1;
            anIntArray524[k + 2] = j1;
            anIntArray524[k + 3] = k1;
            anIntArray524[k + 4] = l1;
            anIntArray524[k + 5] = i2;
            anIntArray524[k + 6] = j2;
            anIntArray524[k + 7] = k2;
        }

        method297();
        anInt522 = 256;
    }

    public int anInt522;
    public int anIntArray523[];
    public int anIntArray524[];
    public int anInt525;
    public int anInt526;
    public int anInt527;
}
