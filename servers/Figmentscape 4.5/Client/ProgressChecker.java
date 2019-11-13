
import java.io.*;

public final class ProgressChecker extends client
{

    public int percentage;
    public String filetocheck;
    public long filelength;

    public ProgressChecker(String s, long l)
    {
        percentage = 0;
        filetocheck = s;
        filelength = l;
        start();
    }

    public void ProgressChecker()
    {
        long l = 0L;
        long l1 = 0L;
        File file = new File(filetocheck);
        if(l < filelength);
        if(l < l1)
        {
            return;
        } else
        {
            long l2 = l;
            l = file.length();
            int i = (int)(((double)l / (double)filelength) * 100D);
            method13(20, (byte)4, (new StringBuilder()).append("Downloading file - ").append(i).append("& done").toString());
            return;
        }
    }
}
