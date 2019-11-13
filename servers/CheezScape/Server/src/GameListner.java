/*
 * SpuneRace Server
 * GameListner.java
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
 * Game Listner
 *
 * Listens for incomming Game connections.
 *
 */

import java.net.*;
import java.io.*;

public class GameListner implements Runnable {

	public void run(){
		try{
			serversocket = new ServerSocket(ServerConfig.localPort, 1, null);
			System.out.println("Game Server started on port: " + ServerConfig.localPort + ".");
			while(true){
				Socket socket = serversocket.accept();
				socket.setTcpNoDelay(true);
				System.out.print("Connection: " + socket.getInetAddress().getHostName() + ":" + socket.getPort());
				InputStream is = socket.getInputStream();
				switch(is.read()){
					case 15:
						System.out.println(" > UpdateServer");
						GameUpdate gu = new GameUpdate(socket);
						new Thread(gu).start();
						break;
					case 14:
						System.out.println(" > GameServer");
						GameComms gc = new GameComms(socket);
						new Thread(gc).start();
						break;
					default:
						System.out.println(" > Unknown, Dropping.");
						is.close();
						socket.close();
						break;
				}			
			}
		} catch (Exception _ex){
			System.out.println("Could not start Game server on port: " + ServerConfig.localPort);
		}
	}
	
	public ServerSocket serversocket;

}