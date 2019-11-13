// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

public class Class2
{

    public Class2(byte abyte0[], int i)
    {
        anInt86 = -766;
        aBoolean87 = true;
        method153(0, abyte0);
        if(i < 3 || i > 3)
        {
            for(int j = 1; j > 0; j++);
        }
    }

    public void method153(int i, byte abyte0[])
    {
        ByteStream byteStream = new ByteStream(true, abyte0);
        int j = byteStream.method525();
        int k = byteStream.method525();
        if(k != j)
        {
            byte abyte1[] = new byte[j];
            Class30.method312(abyte1, j, abyte0, k, 6);
            aByteArray88 = abyte1;
            byteStream = new ByteStream(true, aByteArray88);
            aBoolean94 = true;
        } else
        {
            aByteArray88 = abyte0;
            aBoolean94 = false;
        }
        anInt89 = byteStream.method523();
        anIntArray90 = new int[anInt89];
        anIntArray91 = new int[anInt89];
        anIntArray92 = new int[anInt89];
        if(i != 0)
            return;
        anIntArray93 = new int[anInt89];
        int l = byteStream.caret + anInt89 * 10;
        for(int i1 = 0; i1 < anInt89; i1++)
        {
            anIntArray90[i1] = byteStream.method526();
            anIntArray91[i1] = byteStream.method525();
            anIntArray92[i1] = byteStream.method525();
            anIntArray93[i1] = l;
            l += anIntArray92[i1];
        }

    }

    public byte[] method154(String s, byte abyte0[])
    {
        int i = 0;
        s = s.toUpperCase();
        for(int j = 0; j < s.length(); j++)
            i = (i * 61 + s.charAt(j)) - 32;

        for(int k = 0; k < anInt89; k++)
            if(anIntArray90[k] == i)
            {
                if(abyte0 == null)
                    abyte0 = new byte[anIntArray91[k]];
                if(!aBoolean94)
                {
                    Class30.method312(abyte0, anIntArray91[k], aByteArray88, anIntArray92[k], anIntArray93[k]);
                } else
                {
                    for(int l = 0; l < anIntArray91[k]; l++)
                        abyte0[l] = aByteArray88[anIntArray93[k] + l];

                }
                return abyte0;
            }

        return null;
    }

    public int anInt86;
    public boolean aBoolean87;
    public byte aByteArray88[];
    public int anInt89;
    public int anIntArray90[];
    public int anIntArray91[];
    public int anIntArray92[];
    public int anIntArray93[];
    public boolean aBoolean94;
}
