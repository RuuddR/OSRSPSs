package net.paradise.util;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OutLogger extends PrintStream
{

	private static final DateFormat formatter = new SimpleDateFormat(
			"yyyy-MM-dd | HH:mm:ss | ");

	public OutLogger(OutputStream out)
	{
		super(out);
	}

	@Override
	public void println(String str)
	{
		super.println(str);

		try
		{
			BufferedWriter bw = new BufferedWriter(new FileWriter("out.txt",
					true));

			bw.append(formatter.format(new Date()) + str);
			bw.newLine();
			bw.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

}