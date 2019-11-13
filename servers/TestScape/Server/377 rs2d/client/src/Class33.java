// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

import sign.signlink;

public class Class33
{

    public Class33(int i, int j)
    {
        anInt592 = 256;
        aClass50_Sub1_595 = new Class50_Sub1();
        aClass9_599 = new Class9(true);
        anInt596 = i;
        if(j >= 0)
            anInt592 = 433;
        anInt597 = i;
        aClass19_598 = new Class19((byte)0, 1024);
    }

    public Class50_Sub1 method345(long l)
    {
        Class50_Sub1 class50_sub1 = (Class50_Sub1)aClass19_598.method233(l);
        if(class50_sub1 != null)
        {
            aClass9_599.method185(class50_sub1);
            anInt594++;
        } else
        {
            anInt593++;
        }
        return class50_sub1;
    }

    public void method346(Class50_Sub1 class50_sub1, long l, int i)
    {
        try
        {
            if(i != 5)
                anInt592 = 150;
            if(anInt597 == 0)
            {
                Class50_Sub1 class50_sub1_1 = aClass9_599.method186();
                class50_sub1_1.method442();
                class50_sub1_1.method443();
                if(class50_sub1_1 == aClass50_Sub1_595)
                {
                    Class50_Sub1 class50_sub1_2 = aClass9_599.method186();
                    class50_sub1_2.method442();
                    class50_sub1_2.method443();
                }
            } else
            {
                anInt597--;
            }
            aClass19_598.method234(6, class50_sub1, l);
            aClass9_599.method185(class50_sub1);
            return;
        }
        catch(RuntimeException runtimeexception)
        {
            signlink.reporterror("76413, " + class50_sub1 + ", " + l + ", " + i + ", " + runtimeexception.toString());
        }
        throw new RuntimeException();
    }

    public void method347()
    {
        do
        {
            Class50_Sub1 class50_sub1 = aClass9_599.method186();
            if(class50_sub1 != null)
            {
                class50_sub1.method442();
                class50_sub1.method443();
            } else
            {
                anInt597 = anInt596;
                return;
            }
        } while(true);
    }

    public int anInt592;
    public int anInt593;
    public int anInt594;
    public Class50_Sub1 aClass50_Sub1_595;
    public int anInt596;
    public int anInt597;
    public Class19 aClass19_598;
    public Class9 aClass9_599;
}
