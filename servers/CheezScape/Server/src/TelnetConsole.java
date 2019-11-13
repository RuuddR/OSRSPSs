/*
 * SpuneRace Server
 * TelnetConsole.java
 *
 * (C) 2006 Troy Osborne (defyboy)
 * All Rights Reserved.
 *
 * Created: 10:44 AM Friday, July 28, 2006
 *
 * mailto:defyboy@gmail.com
 * http://www.defyboy.net
 *
 * -------------------------------
 *
 * Telnet/Console Commands.
 *
 * Provides interface for accepting commands from both telnet
 * remote and local console.
 *
 */

import java.io.*;
import java.net.*;

public class TelnetConsole implements Runnable{

	public TelnetConsole(InputStream i, OutputStream o){
		in = new BufferedReader(new InputStreamReader(i));
		out = new PrintWriter(o);
	}
	
	public void run(){
		printVersion();
		printWelcome();
		try{
			while(true){
				processCommand(in.readLine());
			}
		}catch(Exception ex){}
	}
	
	public void processCommand(String cmd){
	
		int cs = -1;
		for(int i=0; i<commands.length; i++){
			if(cmd.equalsIgnoreCase(commands[i])){
				cs = i;
				break;
			}
		}
		
		switch(cs){
			case -1:
				out.println("Invalid Command. \"" + cmd + "\"");
				out.flush();
				break;
			case 0:
			case 1:
				out.println("Shutting down NOW!");
				out.flush();
				System.exit(0);
				break;
			case 2:
			case 3:
				printWelcome();
				break;
			case 4:
				printVersion();
				break;
			case 5:
				out.println();
				out.println("-[ SpuneRace Extra Commands ]-");
				out.println("none.");
				out.println();
				out.flush();
				break;
			default:
				out.println("Unhandled Command. \"" + cmd + "\"");
				out.flush();
				break;
		}
	
	}
	
	public void printVersion(){
		out.println();
		out.println("SpuneRace Server v" + ServerConfig.serverVersion);
		out.println("(C) 2006 Troy Osborne -/- defyboy@gmail.com");
		out.println();
		out.println(ServerConfig.serverName);
		out.println();
		out.flush();
	}
	
	public void printWelcome(){
		out.println();
		out.println("-[ SpuneRace Commands ]-");
		out.println("quit q     = Quit Server");
		out.println("stop       = Stop Server");
		out.println("start      = Start Server");
		out.println("cmds       = More Commands");
		out.println("vers       = Version");
		out.println("help ?     = Help");
		out.println();
		out.flush();
	}
	
	private String[] commands = {"quit", "q", "help", "?", "vers", "cmds", "start", "stop"};

	private BufferedReader in;
	private PrintWriter out;

}