import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import javax.swing.*;
import sign.signlink;
import java.net.*;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

public class Updater extends Thread
{

    public String name;
    public String dir;

    public void get(String url, String name1, String dir1, String type)
    {
        dir = dir1;
        name = name1;
        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("Hello.");
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());
        frame.setPreferredSize(new Dimension(500, 80));
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        client client = new client();
        ClassLoader cl = getClass().getClassLoader();
        try
        {
            URLConnection connection = (new URL(url)).openConnection();
            String f[] = url.split("/");
            File file = new File(f[f.length - 1]);
            int length = connection.getContentLength();
            InputStream instream = connection.getInputStream();
            try{new File(signlink.findcachedir()+dir).mkdir();}catch(Exception e){}
            FileOutputStream outstream = new FileOutputStream(signlink.findcachedir()+dir+file);
            int size = 0;
            int copy = 0;
            JProgressBar bar = new JProgressBar();
            bar.setStringPainted(true);
            bar.setMaximum(length);
            frame.add(bar, "Center");
            frame.pack();
            frame.setVisible(true);
            while((copy = instream.read()) != -1)
            {
                outstream.write(copy);
                size++;
                int percentage = (int)(((double)size / (double)length) * 100D);
                bar.setValue(size);
                bar.setString("Downloading "+type+" "+percentage+"%");
            }
            if(length != size)
            {
                instream.close();
                outstream.close();
            } else
            {
                bar.setString("Unpacking files...");
                instream.close();
                outstream.close();
                unZipFile();
                deleteFile();
                frame.setVisible(false);
            }
        }
        catch(Exception e)
        {
            System.err.println("Error connecting to update server.");
            e.printStackTrace();
        }
    }

    private void writeStream(InputStream In, OutputStream Out) throws IOException
    {
        byte Buffer[] = new byte[1024];
        int Len;
        while((Len = In.read(Buffer)) >= 0)
        {
            Out.write(Buffer, 0, Len);
        }
        In.close();
        Out.close();
    }

    private void unZipFile()
    {
        try
        {
            ZipFile ZipFile = new ZipFile(signlink.findcachedir()+dir+name);
            for(Enumeration Entries = ZipFile.entries(); Entries.hasMoreElements();)
            {
                ZipEntry Entry = (ZipEntry)Entries.nextElement();
                if(Entry.isDirectory())
                {
                    (new File(signlink.findcachedir()+dir+Entry.getName())).mkdir();
                } else
                {
                    writeStream(ZipFile.getInputStream(Entry), new BufferedOutputStream(new FileOutputStream(signlink.findcachedir()+dir+Entry.getName())));
                }
            }
            ZipFile.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    private void deleteFile()
    {
        try
        {
            File file = new File(signlink.findcachedir()+dir+name);
            file.delete();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }


}
