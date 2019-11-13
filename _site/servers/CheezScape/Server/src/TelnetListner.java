/*
 * SpuneRace Server
 * TelnetListner.java
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
 * Telnet Listner
 *
 * Listens for incomming Telnet connections.
 *
 */

import java.net.*;
import java.io.*;

public class TelnetListner implements Runnable {

	public void run() {
		if(ServerConfig.telnetPort > 0){
			try {
				ServerSocket serversocket = new ServerSocket(ServerConfig.telnetPort);
				System.out.println("Telnet Server started on port: " + ServerConfig.telnetPort + ".");
				while(true) {
					Socket socket = serversocket.accept();
					TelnetComms tn = new TelnetComms(socket);
					new Thread(tn).start();
				}
			} catch(Exception _ex) { 
				System.out.println("Could not start Telnet server on port: " + ServerConfig.telnetPort);
				System.exit(2);
			}
		}
	}

}