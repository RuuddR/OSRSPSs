// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

import sign.signlink;

public class Class7
    implements Runnable
{

    public Class7(client client1, byte byte0)
    {
        aBoolean131 = true;
        anIntArray132 = new int[500];
        anObject133 = new Object();
        anInt135 = 8;
        anIntArray137 = new int[500];
        if(byte0 != -116)
            anInt135 = 294;
        aClient134 = client1;
    }

    public void run()
    {
        while(aBoolean131) 
        {
            synchronized(anObject133)
            {
                if(anInt136 < 500)
                {
                    anIntArray137[anInt136] = ((Applet_Sub1) (aClient134)).anInt22;
                    anIntArray132[anInt136] = ((Applet_Sub1) (aClient134)).anInt23;
                    anInt136++;
                }
            }
            try
            {
                Thread.sleep(50L);
            }
            catch(Exception _ex) { }
        }
    }

    public boolean aBoolean131;
    public int anIntArray132[];
    public Object anObject133;
    public client aClient134;
    public int anInt135;
    public int anInt136;
    public int anIntArray137[];
}
