// Decompiled by Jad v1.5.8f. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 

import java.awt.*;
import java.awt.image.*;
import java.io.PrintStream;
import sign.signlink;

public class Class18
    implements ImageProducer, ImageObserver
{

    public Class18(int i, byte byte0, Component component, int j)
    {
        aBoolean391 = true;
        anInt393 = j;
        anInt394 = i;
        anIntArray392 = new int[j * i];
        aColorModel395 = new DirectColorModel(32, 0xff0000, 65280, 255);
        anImage397 = component.createImage(this);
        method232();
        component.prepareImage(anImage397, this);
        method232();
        component.prepareImage(anImage397, this);
        if(byte0 != -12)
        {
            throw new NullPointerException();
        } else
        {
            method232();
            component.prepareImage(anImage397, this);
            method230(false);
            return;
        }
    }

    public void method230(boolean flag)
    {
        if(flag)
        {
            for(int i = 1; i > 0; i++);
        }
        Class50_Sub1_Sub1.method444(aBoolean391, anInt393, anInt394, anIntArray392);
    }

    public void method231(int i, int j, Graphics g, boolean flag)
    {
        if(flag)
        {
            return;
        } else
        {
            method232();
            g.drawImage(anImage397, j, i, this);
            return;
        }
    }

    public synchronized void addConsumer(ImageConsumer imageconsumer)
    {
        anImageConsumer396 = imageconsumer;
        imageconsumer.setDimensions(anInt393, anInt394);
        imageconsumer.setProperties(null);
        imageconsumer.setColorModel(aColorModel395);
        imageconsumer.setHints(14);
    }

    public synchronized boolean isConsumer(ImageConsumer imageconsumer)
    {
        return anImageConsumer396 == imageconsumer;
    }

    public synchronized void removeConsumer(ImageConsumer imageconsumer)
    {
        if(anImageConsumer396 == imageconsumer)
            anImageConsumer396 = null;
    }

    public void startProduction(ImageConsumer imageconsumer)
    {
        addConsumer(imageconsumer);
    }

    public void requestTopDownLeftRightResend(ImageConsumer imageconsumer)
    {
        System.out.println("TDLR");
    }

    public synchronized void method232()
    {
        if(anImageConsumer396 == null)
        {
            return;
        } else
        {
            anImageConsumer396.setPixels(0, 0, anInt393, anInt394, aColorModel395, anIntArray392, 0, anInt393);
            anImageConsumer396.imageComplete(2);
            return;
        }
    }

    public boolean imageUpdate(Image image, int i, int j, int k, int l, int i1)
    {
        return true;
    }

    public boolean aBoolean391;
    public int anIntArray392[];
    public int anInt393;
    public int anInt394;
    public ColorModel aColorModel395;
    public ImageConsumer anImageConsumer396;
    public Image anImage397;
}
