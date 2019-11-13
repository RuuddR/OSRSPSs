/*
 * SpuneRace Server
 * TelnetComms.java
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
 * Telnet Communications.
 *
 * Base communications for Telnet server.
 *
 */
 
import java.net.*;
import java.io.*;
import java.util.*;

public class TelnetComms implements Runnable {

	private Socket socket;

	public TelnetComms(Socket skt) {
		socket = skt;
	}

	public void run() {
		try {
			while(true) {
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				PrintWriter out = new PrintWriter(socket.getOutputStream());
				out.println("SpuneRace Remote Console");
				out.println("-----------");
				out.println("Console Login: ");
				out.flush();
				boolean auth = false;
				do {
					do {
						out.println();
						out.print("Username: ");
						out.flush();
					} while(!in.readLine().equals(ServerConfig.telnetUser));
					out.println();
					out.print("Password: ");
					out.flush();
					if(in.readLine().equals(ServerConfig.telnetPass)) {
						auth=true;
					}
				} while(auth == false);
				out.println();
				out.println("Auth OK");
				out.println("You now have remote console access");
				out.println();
				out.flush();
				TelnetConsole tc = new TelnetConsole(socket.getInputStream(), socket.getOutputStream());
				tc.run();
			}
		} catch (Exception e) {}
	}

}