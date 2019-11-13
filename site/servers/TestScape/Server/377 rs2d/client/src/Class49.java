// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

public class Class49
{

    public static void method440(Class2 class2, int i)
    {
        ByteStream byteStream = new ByteStream(true, class2.method154("varbit.dat", null));
        if(i != 36135)
            return;
        anInt823 = byteStream.method523();
        if(aClass49Array824 == null)
            aClass49Array824 = new Class49[anInt823];
        for(int j = 0; j < anInt823; j++)
        {
            if(aClass49Array824[j] == null)
                aClass49Array824[j] = new Class49();
            aClass49Array824[j].method441(-954, j, byteStream);
            if(aClass49Array824[j].aBoolean829)
                Class43.aClass43Array704[aClass49Array824[j].anInt826].aBoolean716 = true;
        }

        if(byteStream.caret != byteStream.aByteArray1453.length)
            System.out.println("varbit load mismatch");
    }

    public void method441(int i, int j, ByteStream byteStream)
    {
        if(i >= 0)
            anInt822 = -151;
        do
        {
            int k = byteStream.method521();
            if(k == 0)
                return;
            if(k == 1)
            {
                anInt826 = byteStream.method523();
                anInt827 = byteStream.method521();
                anInt828 = byteStream.method521();
            } else
            if(k == 10)
                aString825 = byteStream.readString();
            else
            if(k == 2)
                aBoolean829 = true;
            else
            if(k == 3)
                anInt830 = byteStream.method526();
            else
            if(k == 4)
                anInt831 = byteStream.method526();
            else
            if(k == 5)
                aBoolean832 = false;
            else
                System.out.println("Error unrecognised config code: " + k);
        } while(true);
    }

    public Class49()
    {
        aBoolean829 = false;
        anInt830 = -1;
        aBoolean832 = true;
    }

    public int anInt822;
    public static int anInt823;
    public static Class49 aClass49Array824[];
    public String aString825;
    public int anInt826;
    public int anInt827;
    public int anInt828;
    public boolean aBoolean829;
    public int anInt830;
    public int anInt831;
    public boolean aBoolean832;
}
