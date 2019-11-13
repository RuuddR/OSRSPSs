// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

public class Class27
{

    public static void method305(Class2 class2, int i)
    {
        ByteStream byteStream = new ByteStream(true, class2.method154("spotanim.dat", null));
        anInt553 = byteStream.method523();
        if(i != 36135)
            aBoolean551 = !aBoolean551;
        if(aClass27Array554 == null)
            aClass27Array554 = new Class27[anInt553];
        for(int j = 0; j < anInt553; j++)
        {
            if(aClass27Array554[j] == null)
                aClass27Array554[j] = new Class27();
            aClass27Array554[j].anInt555 = j;
            aClass27Array554[j].method306(aByte550, byteStream);
        }

    }

    public void method306(byte byte0, ByteStream byteStream)
    {
        if(byte0 == 6)
            byte0 = 0;
        else
            anInt552 = 458;
        do
        {
            int i = byteStream.method521();
            if(i == 0)
                return;
            if(i == 1)
                anInt556 = byteStream.method523();
            else
            if(i == 2)
            {
                anInt557 = byteStream.method523();
                if(Class14.aClass14Array293 != null)
                    aClass14_558 = Class14.aClass14Array293[anInt557];
            } else
            if(i == 4)
                anInt561 = byteStream.method523();
            else
            if(i == 5)
                anInt562 = byteStream.method523();
            else
            if(i == 6)
                anInt563 = byteStream.method523();
            else
            if(i == 7)
                anInt564 = byteStream.method521();
            else
            if(i == 8)
                anInt565 = byteStream.method521();
            else
            if(i >= 40 && i < 50)
                anIntArray559[i - 40] = byteStream.method523();
            else
            if(i >= 50 && i < 60)
                anIntArray560[i - 50] = byteStream.method523();
            else
                System.out.println("Error unrecognised spotanim config code: " + i);
        } while(true);
    }

    public Class50_Sub1_Sub4_Sub4 method307()
    {
        Class50_Sub1_Sub4_Sub4 class50_sub1_sub4_sub4 = (Class50_Sub1_Sub4_Sub4)aClass33_566.method345(anInt555);
        if(class50_sub1_sub4_sub4 != null)
            return class50_sub1_sub4_sub4;
        class50_sub1_sub4_sub4 = Class50_Sub1_Sub4_Sub4.method577(anInt556);
        if(class50_sub1_sub4_sub4 == null)
            return null;
        for(int i = 0; i < 6; i++)
            if(anIntArray559[0] != 0)
                class50_sub1_sub4_sub4.method591(anIntArray559[i], anIntArray560[i]);

        aClass33_566.method346(class50_sub1_sub4_sub4, anInt555, 5);
        return class50_sub1_sub4_sub4;
    }

    public Class27()
    {
        anInt552 = -214;
        anInt557 = -1;
        anIntArray559 = new int[6];
        anIntArray560 = new int[6];
        anInt561 = 128;
        anInt562 = 128;
    }

    public static byte aByte550 = 6;
    public static boolean aBoolean551 = true;
    public int anInt552;
    public static int anInt553;
    public static Class27 aClass27Array554[];
    public int anInt555;
    public int anInt556;
    public int anInt557;
    public Class14 aClass14_558;
    public int anIntArray559[];
    public int anIntArray560[];
    public int anInt561;
    public int anInt562;
    public int anInt563;
    public int anInt564;
    public int anInt565;
    public static Class33 aClass33_566 = new Class33(30, -572);

}
