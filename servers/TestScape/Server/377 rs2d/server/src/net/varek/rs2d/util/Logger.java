package net.varek.rs2d.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * A class to handle logging events
 */

public class Logger {
	private static final DecimalFormat df2 = new DecimalFormat("00");
	private static PrintStream out, err;
	private static Map<String,PrintStream> outStreams = new HashMap<String, PrintStream>();
	
	static {
		try {
			out = newStream("out.log");
			err = newStream("err.log");
		} catch(IOException ieo) {
			ieo.printStackTrace();
			System.exit(1);
		}
	}
	
	private static PrintStream newStream(String file) throws IOException {
		PrintStream p = new PrintStream(new BufferedOutputStream(new FileOutputStream(new File("logs/"+file)), 1024));
		outStreams.put(file, p);
		return p;
	}
	
	private static String time() {
		return timeSince(START_TIME);
	}
	
	private static String prefix() {
		return "[" + time() + "] ["+Thread.currentThread().getName()+"] ";
	}
	
	public static void close() {
		Iterator<Map.Entry<String,PrintStream>> it = outStreams.entrySet().iterator();
		while(it.hasNext()) {
			Map.Entry<String,PrintStream> entry = it.next();
			PrintStream stream = entry.getValue();
			stream.println("Closing stream..");
			stream.flush();
			stream.close();
		}
	}
	
	public static void flush(String f) {
		PrintStream tmpStream = outStreams.get(f);
		if (tmpStream == null) {
			Logger.err("Failed to flush "+f+" (No such stream)");
		} else tmpStream.flush();
	}
	
	public static synchronized void log(Object o) {
		String s = prefix() + o.toString();
		out.println(s);
		System.out.println(s);
	}
	
	public static synchronized void log(String f, Object o) {
		String s = prefix() + o.toString();
		try {
			PrintStream tmpStream = outStreams.get(f);
			if (tmpStream == null) {
				tmpStream = newStream(f);
			}
			tmpStream.println(s);
		} catch (IOException ex) {
			Logger.err("Failed to log to "+f+", msg='"+s+"'");
		}
	}
	
	public static synchronized void err(Throwable e) {
		String s = prefix() + e.toString();
		err.println(s);
		System.err.println(s);
		for(StackTraceElement ste : e.getStackTrace()) {
			String s1 = prefix() + "\t" +ste.toString();
			err.println(s1);
			System.err.println(s1);
		}
	}
	
	public static synchronized void err(String s1) {
		String s = prefix() + s1;
		err.println(s);
		err.flush();
		System.err.println(s);
	}
	
    public static final String timeSince(long time) {
    	int seconds = (int)((System.currentTimeMillis() - time) / 1000);
    	int minutes = (int)(seconds / 60);
    	int hours = (int)(minutes / 60);
    	int days = (int)(hours / 24);
		String dayStr = "";
		if(days > 0)
			dayStr = days+" days, ";
		String s = null;
		synchronized(df2) {
			s = dayStr + df2.format(hours % 24) + ":" + df2.format(minutes % 60) + ":"+df2.format(seconds % 60);
		}
		return s;
    }
	
	private static long START_TIME;
	static {
		START_TIME = System.currentTimeMillis();
	}
}
