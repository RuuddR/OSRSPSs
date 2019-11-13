// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

import sign.signlink;

public class Class6
{

    public Class6(boolean flag)
    {
        anInt127 = -48545;
        aClass50_129 = new Class50();
        if(!flag)
        {
            for(int i = 1; i > 0; i++);
        }
        aClass50_129.aClass50_834 = aClass50_129;
        aClass50_129.aClass50_835 = aClass50_129;
    }

    public void method155(Class50 class50)
    {
        if(class50.aClass50_835 != null)
            class50.method442();
        class50.aClass50_835 = aClass50_129.aClass50_835;
        class50.aClass50_834 = aClass50_129;
        class50.aClass50_835.aClass50_834 = class50;
        class50.aClass50_834.aClass50_835 = class50;
    }

    public void method156(byte byte0, Class50 class50)
    {
        if(byte0 != -57)
            anInt128 = -23;
        if(class50.aClass50_835 != null)
            class50.method442();
        class50.aClass50_835 = aClass50_129;
        class50.aClass50_834 = aClass50_129.aClass50_834;
        class50.aClass50_835.aClass50_834 = class50;
        class50.aClass50_834.aClass50_835 = class50;
    }

    public Class50 method157()
    {
        Class50 class50 = aClass50_129.aClass50_834;
        if(class50 == aClass50_129)
        {
            return null;
        } else
        {
            class50.method442();
            return class50;
        }
    }

    public Class50 method158()
    {
        Class50 class50 = aClass50_129.aClass50_834;
        if(class50 == aClass50_129)
        {
            aClass50_130 = null;
            return null;
        } else
        {
            aClass50_130 = class50.aClass50_834;
            return class50;
        }
    }

    public Class50 method159(boolean flag)
    {
        Class50 class50 = aClass50_129.aClass50_835;
        if(flag)
        {
            for(int i = 1; i > 0; i++);
        }
        if(class50 == aClass50_129)
        {
            aClass50_130 = null;
            return null;
        } else
        {
            aClass50_130 = class50.aClass50_835;
            return class50;
        }
    }

    public Class50 method160(int i)
    {
        Class50 class50 = aClass50_130;
        if(class50 == aClass50_129)
        {
            aClass50_130 = null;
            return null;
        }
        aClass50_130 = class50.aClass50_834;
        if(i != 1)
            throw new NullPointerException();
        else
            return class50;
    }

    public Class50 method161(int i)
    {
        Class50 class50 = aClass50_130;
        if(i <= 0)
            throw new NullPointerException();
        if(class50 == aClass50_129)
        {
            aClass50_130 = null;
            return null;
        } else
        {
            aClass50_130 = class50.aClass50_835;
            return class50;
        }
    }

    public void method162()
    {
        if(aClass50_129.aClass50_834 == aClass50_129)
            return;
        do
        {
            Class50 class50 = aClass50_129.aClass50_834;
            if(class50 == aClass50_129)
                return;
            class50.method442();
        } while(true);
    }

    public int anInt127;
    public int anInt128;
    public Class50 aClass50_129;
    public Class50 aClass50_130;
}
