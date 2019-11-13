/*
 * SpuneRace Server
 * Server.java
 *
 * (C) 2006 Troy Osborne (defyboy)
 * All Rights Reserved.
 *
 * Created: 12:56 PM Wednesday, July 26, 2006
 *
 * mailto:defyboy@gmail.com
 * http://www.defyboy.net
 *
 */
 
import java.net.*;
import java.io.*;

public class Server {

	public static void main(String args[]) {
		new Thread(new GameListner()).start();
		new Thread(new TelnetListner()).start();
		new Thread(new TelnetConsole(System.in, System.out)).start();
	}

}