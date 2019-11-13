// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

public class Class48
{

    public static void method434(Class2 class2, int i)
    {
        ByteStream byteStream = new ByteStream(true, class2.method154("idk.dat", null));
        anInt814 = byteStream.method523();
        if(aClass48Array815 == null)
            aClass48Array815 = new Class48[anInt814];
        for(int j = 0; j < anInt814; j++)
        {
            if(aClass48Array815[j] == null)
                aClass48Array815[j] = new Class48();
            aClass48Array815[j].method435(aByte811, byteStream);
        }

        if(i == 36135);
    }

    public void method435(byte byte0, ByteStream byteStream)
    {
        if(byte0 == 6)
            byte0 = 0;
        else
            throw new NullPointerException();
        do
        {
            int i = byteStream.method521();
            if(i == 0)
                return;
            if(i == 1)
                anInt816 = byteStream.method521();
            else
            if(i == 2)
            {
                int j = byteStream.method521();
                anIntArray817 = new int[j];
                for(int k = 0; k < j; k++)
                    anIntArray817[k] = byteStream.method523();

            } else
            if(i == 3)
                aBoolean821 = true;
            else
            if(i >= 40 && i < 50)
                anIntArray818[i - 40] = byteStream.method523();
            else
            if(i >= 50 && i < 60)
                anIntArray819[i - 50] = byteStream.method523();
            else
            if(i >= 60 && i < 70)
                anIntArray820[i - 60] = byteStream.method523();
            else
                System.out.println("Error unrecognised config code: " + i);
        } while(true);
    }

    public boolean method436(int i)
    {
        if(anIntArray817 == null)
            return true;
        boolean flag = true;
        i = 89 / i;
        for(int j = 0; j < anIntArray817.length; j++)
            if(!Class50_Sub1_Sub4_Sub4.method578(anIntArray817[j]))
                flag = false;

        return flag;
    }

    public Class50_Sub1_Sub4_Sub4 method437(byte byte0)
    {
        if(anIntArray817 == null)
            return null;
        Class50_Sub1_Sub4_Sub4 aclass50_sub1_sub4_sub4[] = new Class50_Sub1_Sub4_Sub4[anIntArray817.length];
        for(int i = 0; i < anIntArray817.length; i++)
            aclass50_sub1_sub4_sub4[i] = Class50_Sub1_Sub4_Sub4.method577(anIntArray817[i]);

        Class50_Sub1_Sub4_Sub4 class50_sub1_sub4_sub4;
        if(aclass50_sub1_sub4_sub4.length == 1)
            class50_sub1_sub4_sub4 = aclass50_sub1_sub4_sub4[0];
        else
            class50_sub1_sub4_sub4 = new Class50_Sub1_Sub4_Sub4(aclass50_sub1_sub4_sub4.length, aclass50_sub1_sub4_sub4, (byte)-89);
        for(int j = 0; j < 6; j++)
        {
            if(anIntArray818[j] == 0)
                break;
            class50_sub1_sub4_sub4.method591(anIntArray818[j], anIntArray819[j]);
        }

        if(byte0 != 2)
            throw new NullPointerException();
        else
            return class50_sub1_sub4_sub4;
    }

    public boolean method438(int i)
    {
        if(i != -10584)
            throw new NullPointerException();
        boolean flag = true;
        for(int j = 0; j < 5; j++)
            if(anIntArray820[j] != -1 && !Class50_Sub1_Sub4_Sub4.method578(anIntArray820[j]))
                flag = false;

        return flag;
    }

    public Class50_Sub1_Sub4_Sub4 method439(boolean flag)
    {
        Class50_Sub1_Sub4_Sub4 aclass50_sub1_sub4_sub4[] = new Class50_Sub1_Sub4_Sub4[5];
        if(flag)
            anInt812 = -298;
        int i = 0;
        for(int j = 0; j < 5; j++)
            if(anIntArray820[j] != -1)
                aclass50_sub1_sub4_sub4[i++] = Class50_Sub1_Sub4_Sub4.method577(anIntArray820[j]);

        Class50_Sub1_Sub4_Sub4 class50_sub1_sub4_sub4 = new Class50_Sub1_Sub4_Sub4(i, aclass50_sub1_sub4_sub4, (byte)-89);
        for(int k = 0; k < 6; k++)
        {
            if(anIntArray818[k] == 0)
                break;
            class50_sub1_sub4_sub4.method591(anIntArray818[k], anIntArray819[k]);
        }

        return class50_sub1_sub4_sub4;
    }

    public Class48()
    {
        anInt812 = -766;
        anInt813 = 256;
        anInt816 = -1;
        anIntArray818 = new int[6];
        anIntArray819 = new int[6];
        aBoolean821 = false;
    }

    public static byte aByte811 = 6;
    public int anInt812;
    public int anInt813;
    public static int anInt814;
    public static Class48 aClass48Array815[];
    public int anInt816;
    public int anIntArray817[];
    public int anIntArray818[];
    public int anIntArray819[];
    public int anIntArray820[] = {
        -1, -1, -1, -1, -1
    };
    public boolean aBoolean821;

}
