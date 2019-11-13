// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

public class Class38
{

    public Class38(int i)
    {
        anInt665 = -573;
        anInt666 = -252;
        for(aClass11Array672 = new Class11[10]; i >= 0;)
            throw new NullPointerException();

    }

    public static void method365(ByteStream byteStream, int i)
    {
        if(i != 36135)
            return;
        aByteArray670 = new byte[0x6baa8];
        aByteStream_671 = new ByteStream(true, aByteArray670);
        Class11.method190();
        do
        {
            int j = byteStream.method523();
            if(j == 65535)
                return;
            aClass38Array668[j] = new Class38(-524);
            aClass38Array668[j].method367(aByte664, byteStream);
            anIntArray669[j] = aClass38Array668[j].method368(0);
        } while(true);
    }

    public static ByteStream method366(int i, byte byte0, int j)
    {
        if(byte0 != 6)
            aBoolean667 = !aBoolean667;
        if(aClass38Array668[j] != null)
        {
            Class38 class38 = aClass38Array668[j];
            return class38.method369(-573, i);
        } else
        {
            return null;
        }
    }

    public void method367(byte byte0, ByteStream byteStream)
    {
        for(int i = 0; i < 10; i++)
        {
            int j = byteStream.method521();
            if(j != 0)
            {
                byteStream.caret--;
                aClass11Array672[i] = new Class11();
                aClass11Array672[i].method193(aByte664, byteStream);
            }
        }

        anInt673 = byteStream.method523();
        anInt674 = byteStream.method523();
        if(byte0 == 6)
        {
            byte0 = 0;
            return;
        } else
        {
            anInt666 = 64;
            return;
        }
    }

    public int method368(int i)
    {
        int j = 0x98967f;
        for(int k = 0; k < 10; k++)
            if(aClass11Array672[k] != null && aClass11Array672[k].anInt199 / 20 < j)
                j = aClass11Array672[k].anInt199 / 20;

        if(anInt673 < anInt674 && anInt673 / 20 < j)
            j = anInt673 / 20;
        if(j == 0x98967f || j == 0)
            return 0;
        for(int l = 0; l < 10; l++)
            if(aClass11Array672[l] != null)
                aClass11Array672[l].anInt199 -= j * 20;

        if(i != 0)
            aBoolean667 = !aBoolean667;
        if(anInt673 < anInt674)
        {
            anInt673 -= j * 20;
            anInt674 -= j * 20;
        }
        return j;
    }

    public ByteStream method369(int i, int j)
    {
        int k = method370(j);
        aByteStream_671.caret = 0;
        aByteStream_671.method515(0x52494646);
        aByteStream_671.method516(36 + k, false);
        aByteStream_671.method515(0x57415645);
        aByteStream_671.method515(0x666d7420);
        if(i >= 0)
        {
            throw new NullPointerException();
        } else
        {
            aByteStream_671.method516(16, false);
            aByteStream_671.method513(1, false);
            aByteStream_671.method513(1, false);
            aByteStream_671.method516(22050, false);
            aByteStream_671.method516(22050, false);
            aByteStream_671.method513(1, false);
            aByteStream_671.method513(8, false);
            aByteStream_671.method515(0x64617461);
            aByteStream_671.method516(k, false);
            aByteStream_671.caret += k;
            return aByteStream_671;
        }
    }

    public int method370(int i)
    {
        int j = 0;
        for(int k = 0; k < 10; k++)
            if(aClass11Array672[k] != null && aClass11Array672[k].anInt198 + aClass11Array672[k].anInt199 > j)
                j = aClass11Array672[k].anInt198 + aClass11Array672[k].anInt199;

        if(j == 0)
            return 0;
        int l = (22050 * j) / 1000;
        int i1 = (22050 * anInt673) / 1000;
        int j1 = (22050 * anInt674) / 1000;
        if(i1 < 0 || i1 > l || j1 < 0 || j1 > l || i1 >= j1)
            i = 0;
        int k1 = l + (j1 - i1) * (i - 1);
        for(int l1 = 44; l1 < k1 + 44; l1++)
            aByteArray670[l1] = -128;

        for(int i2 = 0; i2 < 10; i2++)
            if(aClass11Array672[i2] != null)
            {
                int j2 = (aClass11Array672[i2].anInt198 * 22050) / 1000;
                int i3 = (aClass11Array672[i2].anInt199 * 22050) / 1000;
                int ai[] = aClass11Array672[i2].method191(j2, aClass11Array672[i2].anInt198);
                for(int l3 = 0; l3 < j2; l3++)
                {
                    int j4 = (aByteArray670[l3 + i3 + 44] & 0xff) + (ai[l3] >> 8);
                    if((j4 & 0xffffff00) != 0)
                        j4 = ~(j4 >> 31);
                    aByteArray670[l3 + i3 + 44] = (byte)j4;
                }

            }

        if(i > 1)
        {
            i1 += 44;
            j1 += 44;
            l += 44;
            int k2 = (k1 += 44) - l;
            for(int j3 = l - 1; j3 >= j1; j3--)
                aByteArray670[j3 + k2] = aByteArray670[j3];

            for(int k3 = 1; k3 < i; k3++)
            {
                int l2 = (j1 - i1) * k3;
                for(int i4 = i1; i4 < j1; i4++)
                    aByteArray670[i4 + l2] = aByteArray670[i4];

            }

            k1 -= 44;
        }
        return k1;
    }

    public static byte aByte664 = 6;
    public int anInt665;
    public int anInt666;
    public static boolean aBoolean667 = true;
    public static Class38 aClass38Array668[] = new Class38[5000];
    public static int anIntArray669[] = new int[5000];
    public static byte aByteArray670[];
    public static ByteStream aByteStream_671;
    public Class11 aClass11Array672[];
    public int anInt673;
    public int anInt674;

}
