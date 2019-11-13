// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

import sign.signlink;

public class Class19
{

    public Class19(byte byte0, int i)
    {
        aBoolean398 = true;
        aBoolean399 = true;
        anInt400 = i;
        aClass50Array401 = new Class50[i];
        for(int j = 0; j < i; j++)
        {
            Class50 class50 = aClass50Array401[j] = new Class50();
            class50.aClass50_834 = class50;
            class50.aClass50_835 = class50;
        }

        if(byte0 != 0)
        {
            for(int k = 1; k > 0; k++);
        }
    }

    public Class50 method233(long l)
    {
        Class50 class50 = aClass50Array401[(int)(l & (long)(anInt400 - 1))];
        for(Class50 class50_1 = class50.aClass50_834; class50_1 != class50; class50_1 = class50_1.aClass50_834)
            if(class50_1.aLong833 == l)
                return class50_1;

        return null;
    }

    public void method234(int i, Class50 class50, long l)
    {
        try
        {
            if(class50.aClass50_835 != null)
                class50.method442();
            Class50 class50_1 = aClass50Array401[(int)(l & (long)(anInt400 - 1))];
            if(i != 6)
            {
                return;
            } else
            {
                class50.aClass50_835 = class50_1.aClass50_835;
                class50.aClass50_834 = class50_1;
                class50.aClass50_835.aClass50_834 = class50;
                class50.aClass50_834.aClass50_835 = class50;
                class50.aLong833 = l;
                return;
            }
        }
        catch(RuntimeException runtimeexception)
        {
            signlink.reporterror("24638, " + i + ", " + class50 + ", " + l + ", " + runtimeexception.toString());
        }
        throw new RuntimeException();
    }

    public boolean aBoolean398;
    public boolean aBoolean399;
    public int anInt400;
    public Class50 aClass50Array401[];
}
