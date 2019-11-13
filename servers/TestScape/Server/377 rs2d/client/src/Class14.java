// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

public class Class14
{

    public static void method204(Class2 class2, int i)
    {
        ByteStream byteStream = new ByteStream(true, class2.method154("seq.dat", null));
        anInt292 = byteStream.method523();
        if(aClass14Array293 == null)
            aClass14Array293 = new Class14[anInt292];
        for(int j = 0; j < anInt292; j++)
        {
            if(aClass14Array293[j] == null)
                aClass14Array293[j] = new Class14();
            aClass14Array293[j].method206(aByte290, byteStream);
        }

        if(i == 36135);
    }

    public int method205(int i, int j)
    {
        int k = anIntArray297[j];
        if(i != 0)
            return 1;
        if(k == 0)
        {
            Class21 class21 = Class21.method238(anIntArray295[j]);
            if(class21 != null)
                k = anIntArray297[j] = class21.anInt431;
        }
        if(k == 0)
            k = 1;
        return k;
    }

    public void method206(byte byte0, ByteStream byteStream)
    {
        if(byte0 == 6)
            byte0 = 0;
        else
            aBoolean291 = !aBoolean291;
        do
        {
            int i = byteStream.method521();
            if(i == 0)
                break;
            if(i == 1)
            {
                anInt294 = byteStream.method521();
                anIntArray295 = new int[anInt294];
                anIntArray296 = new int[anInt294];
                anIntArray297 = new int[anInt294];
                for(int j = 0; j < anInt294; j++)
                {
                    anIntArray295[j] = byteStream.method523();
                    anIntArray296[j] = byteStream.method523();
                    if(anIntArray296[j] == 65535)
                        anIntArray296[j] = -1;
                    anIntArray297[j] = byteStream.method523();
                }

            } else
            if(i == 2)
                anInt298 = byteStream.method523();
            else
            if(i == 3)
            {
                int k = byteStream.method521();
                anIntArray299 = new int[k + 1];
                for(int l = 0; l < k; l++)
                    anIntArray299[l] = byteStream.method521();

                anIntArray299[k] = 0x98967f;
            } else
            if(i == 4)
                aBoolean300 = true;
            else
            if(i == 5)
                anInt301 = byteStream.method521();
            else
            if(i == 6)
                anInt302 = byteStream.method523();
            else
            if(i == 7)
                anInt303 = byteStream.method523();
            else
            if(i == 8)
                anInt304 = byteStream.method521();
            else
            if(i == 9)
                anInt305 = byteStream.method521();
            else
            if(i == 10)
                anInt306 = byteStream.method521();
            else
            if(i == 11)
                anInt307 = byteStream.method521();
            else
            if(i == 12)
                anInt308 = byteStream.method526();
            else
                System.out.println("Error unrecognised seq config code: " + i);
        } while(true);
        if(anInt294 == 0)
        {
            anInt294 = 1;
            anIntArray295 = new int[1];
            anIntArray295[0] = -1;
            anIntArray296 = new int[1];
            anIntArray296[0] = -1;
            anIntArray297 = new int[1];
            anIntArray297[0] = -1;
        }
        if(anInt305 == -1)
            if(anIntArray299 != null)
                anInt305 = 2;
            else
                anInt305 = 0;
        if(anInt306 == -1)
        {
            if(anIntArray299 != null)
            {
                anInt306 = 2;
                return;
            }
            anInt306 = 0;
        }
    }

    public Class14()
    {
        aBoolean291 = false;
        anInt298 = -1;
        aBoolean300 = false;
        anInt301 = 5;
        anInt302 = -1;
        anInt303 = -1;
        anInt304 = 99;
        anInt305 = -1;
        anInt306 = -1;
        anInt307 = 2;
    }

    public static byte aByte290 = 6;
    public boolean aBoolean291;
    public static int anInt292;
    public static Class14 aClass14Array293[];
    public int anInt294;
    public int anIntArray295[];
    public int anIntArray296[];
    public int anIntArray297[];
    public int anInt298;
    public int anIntArray299[];
    public boolean aBoolean300;
    public int anInt301;
    public int anInt302;
    public int anInt303;
    public int anInt304;
    public int anInt305;
    public int anInt306;
    public int anInt307;
    public int anInt308;
    public static int anInt309;

}
