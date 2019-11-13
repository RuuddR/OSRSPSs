// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

import java.math.BigInteger;
import sign.signlink;

public class ByteStream extends Class50_Sub1
{

    public static ByteStream method509(byte byte0, int i)
    {
        synchronized(aClass6_1463)
        {
            ByteStream byteStream_2 = null;
            if(i == 0 && anInt1459 > 0)
            {
                anInt1459--;
                byteStream_2 = (ByteStream)aClass6_1462.method157();
            } else
            if(i == 1 && anInt1460 > 0)
            {
                anInt1460--;
                byteStream_2 = (ByteStream)aClass6_1463.method157();
            } else
            if(i == 2 && anInt1461 > 0)
            {
                anInt1461--;
                byteStream_2 = (ByteStream)aClass6_1464.method157();
            }
            if(byteStream_2 != null)
            {
                byteStream_2.caret = 0;
                ByteStream byteStream = byteStream_2;
                return byteStream;
            }
        }
        ByteStream byteStream_1 = new ByteStream(-211);
        if(byte0 != 0)
            aBoolean1451 = !aBoolean1451;
        byteStream_1.caret = 0;
        if(i == 0)
            byteStream_1.aByteArray1453 = new byte[100];
        else
        if(i == 1)
            byteStream_1.aByteArray1453 = new byte[5000];
        else
            byteStream_1.aByteArray1453 = new byte[30000];
        return byteStream_1;
    }

    public ByteStream(int i)
    {
        aBoolean1435 = false;
        anInt1436 = 8;
        aBoolean1437 = false;
        aBoolean1438 = true;
        aByte1439 = 5;
        anInt1440 = -29290;
        aBoolean1441 = false;
        anInt1442 = 217;
        anInt1443 = 236;
        aBoolean1444 = false;
        aByte1447 = 17;
        aByte1448 = 89;
        aByte1449 = -16;
        aBoolean1450 = false;
        for(anInt1452 = 1; i >= 0;)
            throw new NullPointerException();

    }

    public ByteStream(boolean flag, byte abyte0[])
    {
        aBoolean1435 = false;
        anInt1436 = 8;
        aBoolean1437 = false;
        aBoolean1438 = true;
        aByte1439 = 5;
        anInt1440 = -29290;
        aBoolean1441 = false;
        anInt1442 = 217;
        anInt1443 = 236;
        aBoolean1444 = false;
        aByte1447 = 17;
        aByte1448 = 89;
        aByte1449 = -16;
        aBoolean1450 = false;
        anInt1452 = 1;
        aByteArray1453 = abyte0;
        caret = 0;
        if(!flag)
        {
            for(int i = 1; i > 0; i++);
        }
    }

    public void method510(byte byte0, int i)
    {
        aByteArray1453[caret++] = (byte)(i + aISAAC_1458.method296());
        if(byte0 != 4)
        {
            for(int j = 1; j > 0; j++);
        }
    }

    public void writeByte(int i)
    {
        aByteArray1453[caret++] = (byte)i;
    }

    public void writeWord(int i)
    {
        aByteArray1453[caret++] = (byte)(i >> 8);
        aByteArray1453[caret++] = (byte)i;
    }

    public void method513(int i, boolean flag)
    {
        aByteArray1453[caret++] = (byte)i;
        if(flag)
        {
            return;
        } else
        {
            aByteArray1453[caret++] = (byte)(i >> 8);
            return;
        }
    }

    public void method514(int i)
    {
        aByteArray1453[caret++] = (byte)(i >> 16);
        aByteArray1453[caret++] = (byte)(i >> 8);
        aByteArray1453[caret++] = (byte)i;
    }

    public void method515(int i)
    {
        aByteArray1453[caret++] = (byte)(i >> 24);
        aByteArray1453[caret++] = (byte)(i >> 16);
        aByteArray1453[caret++] = (byte)(i >> 8);
        aByteArray1453[caret++] = (byte)i;
    }

    public void method516(int i, boolean flag)
    {
        aByteArray1453[caret++] = (byte)i;
        aByteArray1453[caret++] = (byte)(i >> 8);
        if(flag)
            anInt1436 = 306;
        aByteArray1453[caret++] = (byte)(i >> 16);
        aByteArray1453[caret++] = (byte)(i >> 24);
    }

    public void method517(long l, boolean flag)
    {
        try
        {
            aByteArray1453[caret++] = (byte)(int)(l >> 56);
            aByteArray1453[caret++] = (byte)(int)(l >> 48);
            aByteArray1453[caret++] = (byte)(int)(l >> 40);
            aByteArray1453[caret++] = (byte)(int)(l >> 32);
            aByteArray1453[caret++] = (byte)(int)(l >> 24);
            aByteArray1453[caret++] = (byte)(int)(l >> 16);
            aByteArray1453[caret++] = (byte)(int)(l >> 8);
            aByteArray1453[caret++] = (byte)(int)l;
            if(!flag)
                return;
        }
        catch(RuntimeException runtimeexception)
        {
            signlink.reporterror("88423, " + l + ", " + flag + ", " + runtimeexception.toString());
            throw new RuntimeException();
        }
    }

    public void writeString(String s)
    {
        s.getBytes(0, s.length(), aByteArray1453, caret);
        caret += s.length();
        aByteArray1453[caret++] = 10;
    }

    public void method519(byte abyte0[], int i, int j, int k)
    {
        if(i != 0)
            aBoolean1451 = !aBoolean1451;
        for(int l = k; l < k + j; l++)
            aByteArray1453[caret++] = abyte0[l];

    }

    public void method520(int i, int j)
    {
        aByteArray1453[caret - i - 1] = (byte)i;
        if(j == 0);
    }

    public int method521()
    {
        return aByteArray1453[caret++] & 0xff;
    }

    public byte method522()
    {
        return aByteArray1453[caret++];
    }

    public int method523()
    {
        caret += 2;
        return ((aByteArray1453[caret - 2] & 0xff) << 8) + (aByteArray1453[caret - 1] & 0xff);
    }

    public int method524()
    {
        caret += 2;
        int i = ((aByteArray1453[caret - 2] & 0xff) << 8) + (aByteArray1453[caret - 1] & 0xff);
        if(i > 32767)
            i -= 0x10000;
        return i;
    }

    public int method525()
    {
        caret += 3;
        return ((aByteArray1453[caret - 3] & 0xff) << 16) + ((aByteArray1453[caret - 2] & 0xff) << 8) + (aByteArray1453[caret - 1] & 0xff);
    }

    public int method526()
    {
        caret += 4;
        return ((aByteArray1453[caret - 4] & 0xff) << 24) + ((aByteArray1453[caret - 3] & 0xff) << 16) + ((aByteArray1453[caret - 2] & 0xff) << 8) + (aByteArray1453[caret - 1] & 0xff);
    }

    public long method527(int i)
    {
        long l = (long)method526() & 0xffffffffL;
        if(i >= 0)
        {
            throw new NullPointerException();
        } else
        {
            long l1 = (long)method526() & 0xffffffffL;
            return (l << 32) + l1;
        }
    }

    public String readString()
    {
        int i = caret;
        while(aByteArray1453[caret++] != 10) ;
        return new String(aByteArray1453, i, caret - i - 1);
    }

    public byte[] method529(int i)
    {
        int j = caret;
        if(i <= 0)
            throw new NullPointerException();
        while(aByteArray1453[caret++] != 10) ;
        byte abyte0[] = new byte[caret - j - 1];
        for(int k = j; k < caret - 1; k++)
            abyte0[k - j] = aByteArray1453[k];

        return abyte0;
    }

    public void method530(int i, int j, int k, byte abyte0[])
    {
        if(k >= 0)
            aBoolean1438 = !aBoolean1438;
        for(int l = j; l < j + i; l++)
            abyte0[l] = aByteArray1453[caret++];

    }

    public void method531(byte byte0)
    {
        anInt1455 = caret * 8;
        if(byte0 == 6)
            byte0 = 0;
    }

    public int method532(int i, int j)
    {
        int k = anInt1455 >> 3;
        int l = 8 - (anInt1455 & 7);
        int i1 = 0;
        anInt1455 += j;
        if(i <= 0)
            return anInt1452;
        for(; j > l; l = 8)
        {
            i1 += (aByteArray1453[k++] & anIntArray1457[l]) << j - l;
            j -= l;
        }

        if(j == l)
            i1 += aByteArray1453[k] & anIntArray1457[l];
        else
            i1 += aByteArray1453[k] >> l - j & anIntArray1457[j];
        return i1;
    }

    public void method533(int i)
    {
        if(i != anInt1440)
            anInt1440 = -448;
        caret = (anInt1455 + 7) / 8;
    }

    public int method534()
    {
        int i = aByteArray1453[caret] & 0xff;
        if(i < 128)
            return method521() - 64;
        else
            return method523() - 49152;
    }

    public int method535()
    {
        int i = aByteArray1453[caret] & 0xff;
        if(i < 128)
            return method521();
        else
            return method523() - 32768;
    }

    public void method536(int i, BigInteger biginteger, BigInteger biginteger1)
    {
        int j = caret;
        caret = 0;
        byte abyte0[] = new byte[j];
        method530(j, 0, -21, abyte0);
        BigInteger biginteger2 = new BigInteger(abyte0);
        BigInteger biginteger3 = biginteger2;//.modPow(biginteger1, biginteger);
        byte abyte1[] = biginteger3.toByteArray();
        if(i < 0 || i > 0)
        {
            return;
        } else
        {
            caret = 0;
            writeByte(abyte1.length);
            method519(abyte1, 0, abyte1.length, 0);
            return;
        }
    }

    public void method537(boolean flag, int i)
    {
        aByteArray1453[caret++] = (byte)(i + 128);
        if(flag)
        {
            for(int j = 1; j > 0; j++);
        }
    }

    public void method538(byte byte0, int i)
    {
        if(byte0 == 0)
        {
            byte0 = 0;
        } else
        {
            for(int j = 1; j > 0; j++);
        }
        aByteArray1453[caret++] = (byte)(-i);
    }

    public void writeByteS(int i, int j)
    {
        if(j != 1)
        {
            return;
        } else
        {
            aByteArray1453[caret++] = (byte)(128 - i);
            return;
        }
    }

    public int method540(int i)
    {
        if(i != anInt1445)
            return anInt1442;
        else
            return aByteArray1453[caret++] - 128 & 0xff;
    }

    public int method541(int i)
    {
        if(i != -34545)
            return anInt1436;
        else
            return -aByteArray1453[caret++] & 0xff;
    }

    public int method542(int i)
    {
        i = 77 / i;
        return 128 - aByteArray1453[caret++] & 0xff;
    }

    public byte method543(int i)
    {
        if(i != 0)
        {
            for(int j = 1; j > 0; j++);
        }
        return (byte)(aByteArray1453[caret++] - 128);
    }

    public byte method544(int i)
    {
        if(i != 0)
            anInt1452 = 54;
        return (byte)(-aByteArray1453[caret++]);
    }

    public byte method545(int i)
    {
        if(i != 43428)
        {
            for(int j = 1; j > 0; j++);
        }
        return (byte)(128 - aByteArray1453[caret++]);
    }

    public void method546(int i, int j)
    {
        aByteArray1453[caret++] = (byte)j;
        aByteArray1453[caret++] = (byte)(j >> 8);
        if(i != 0)
            anInt1446 = 403;
    }

    public void method547(int i, int j)
    {
        aByteArray1453[caret++] = (byte)(i >> 8);
        aByteArray1453[caret++] = (byte)(i + 128);
        if(j == 0);
    }

    public void method548(int i, int j)
    {
        if(i < 3 || i > 3)
            aBoolean1435 = !aBoolean1435;
        aByteArray1453[caret++] = (byte)(j + 128);
        aByteArray1453[caret++] = (byte)(j >> 8);
    }

    public int method549(int i)
    {
        caret += 2;
        if(i >= 0)
            return 3;
        else
            return ((aByteArray1453[caret - 1] & 0xff) << 8) + (aByteArray1453[caret - 2] & 0xff);
    }

    public int method550(byte byte0)
    {
        caret += 2;
        if(byte0 != 9)
            return anInt1446;
        else
            return ((aByteArray1453[caret - 2] & 0xff) << 8) + (aByteArray1453[caret - 1] - 128 & 0xff);
    }

    public int method551(int i)
    {
        caret += 2;
        if(i >= 0)
            anInt1446 = 68;
        return ((aByteArray1453[caret - 1] & 0xff) << 8) + (aByteArray1453[caret - 2] - 128 & 0xff);
    }

    public int method552(int i)
    {
        caret += 2;
        int j = ((aByteArray1453[caret - 1] & 0xff) << 8) + (aByteArray1453[caret - 2] & 0xff);
        while(i >= 0) 
        {
            for(int k = 1; k > 0; k++);
        }
        if(j > 32767)
            j -= 0x10000;
        return j;
    }

    public int method553(byte byte0)
    {
        caret += 2;
        if(byte0 != aByte1447)
            aBoolean1441 = !aBoolean1441;
        int i = ((aByteArray1453[caret - 2] & 0xff) << 8) + (aByteArray1453[caret - 1] - 128 & 0xff);
        if(i > 32767)
            i -= 0x10000;
        return i;
    }

    public int method554(int i)
    {
        caret += 3;
        if(i >= 0)
            return 1;
        else
            return ((aByteArray1453[caret - 2] & 0xff) << 16) + ((aByteArray1453[caret - 3] & 0xff) << 8) + (aByteArray1453[caret - 1] & 0xff);
    }

    public int method555(int i)
    {
        if(i <= 0)
            anInt1446 = -453;
        caret += 4;
        return ((aByteArray1453[caret - 1] & 0xff) << 24) + ((aByteArray1453[caret - 2] & 0xff) << 16) + ((aByteArray1453[caret - 3] & 0xff) << 8) + (aByteArray1453[caret - 4] & 0xff);
    }

    public int method556(int i)
    {
        if(i < 3 || i > 3)
            anInt1446 = -258;
        caret += 4;
        return ((aByteArray1453[caret - 2] & 0xff) << 24) + ((aByteArray1453[caret - 1] & 0xff) << 16) + ((aByteArray1453[caret - 4] & 0xff) << 8) + (aByteArray1453[caret - 3] & 0xff);
    }

    public int method557(boolean flag)
    {
        caret += 4;
        if(!flag)
        {
            for(int i = 1; i > 0; i++);
        }
        return ((aByteArray1453[caret - 3] & 0xff) << 24) + ((aByteArray1453[caret - 4] & 0xff) << 16) + ((aByteArray1453[caret - 1] & 0xff) << 8) + (aByteArray1453[caret - 2] & 0xff);
    }

    public void method558(byte byte0, byte abyte0[], int i, int j)
    {
        if(byte0 != -73)
            return;
        for(int k = (j + i) - 1; k >= j; k--)
            abyte0[k] = aByteArray1453[caret++];

    }

    public void method559(byte abyte0[], int i, int j, int k)
    {
        if(j != 0)
            aBoolean1438 = !aBoolean1438;
        for(int l = k; l < k + i; l++)
            abyte0[l] = (byte)(aByteArray1453[caret++] - 128);

    }

    public boolean aBoolean1435;
    public int anInt1436;
    public boolean aBoolean1437;
    public boolean aBoolean1438;
    public byte aByte1439;
    public int anInt1440;
    public boolean aBoolean1441;
    public int anInt1442;
    public int anInt1443;
    public boolean aBoolean1444;
    public int anInt1445;
    public int anInt1446;
    public byte aByte1447;
    public byte aByte1448;
    public byte aByte1449;
    public boolean aBoolean1450;
    public static boolean aBoolean1451 = true;
    public int anInt1452;
    public byte aByteArray1453[];
    public int caret;
    public int anInt1455;
    public static int anIntArray1456[];
    public static final int anIntArray1457[] = {
        0, 1, 3, 7, 15, 31, 63, 127, 255, 511, 
        1023, 2047, 4095, 8191, 16383, 32767, 65535, 0x1ffff, 0x3ffff, 0x7ffff, 
        0xfffff, 0x1fffff, 0x3fffff, 0x7fffff, 0xffffff, 0x1ffffff, 0x3ffffff, 0x7ffffff, 0xfffffff, 0x1fffffff, 
        0x3fffffff, 0x7fffffff, -1
    };
    public ISAAC aISAAC_1458;
    public static int anInt1459;
    public static int anInt1460;
    public static int anInt1461;
    public static Class6 aClass6_1462 = new Class6(true);
    public static Class6 aClass6_1463 = new Class6(true);
    public static Class6 aClass6_1464 = new Class6(true);
    public static char aCharArray1465[] = {
        'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 
        'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 
        'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 
        'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 
        'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 
        'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', 
        '8', '9', '+', '/'
    };
    public static boolean aBoolean1466;

    static 
    {
        anIntArray1456 = new int[256];
        for(int j = 0; j < 256; j++)
        {
            int i = j;
            for(int k = 0; k < 8; k++)
                if((i & 1) == 1)
                    i = i >>> 1 ^ 0xedb88320;
                else
                    i >>>= 1;

            anIntArray1456[j] = i;
        }

    }
}
