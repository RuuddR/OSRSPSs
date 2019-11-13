/*
 * SpuneRace Server
 * ServerUpdate.java
 *
 * (C) 2006 Troy Osborne (defyboy)
 * All Rights Reserved.
 *
 * Created: 12:56 PM Wednesday, July 26, 2006
 *
 * mailto:defyboy@gmail.com
 * http://www.defyboy.net
 *
 * -------------------------------
 *
 * SpuneRace Update Server.
 *
 * Provides a method for updating the newer clients with cache
 * kept on the server.
 *
 */

import java.net.*;
import java.io.*;
import java.util.LinkedList;

public class GameUpdate implements Runnable {

	public String dirCache = "./data/cache/";

	public void run(){
		// Remember that the first byte has allready been read to determine server type.
		try{
		
			/*
			 * Response Codes
			 * 0 = OK
			 * 6 = Client Out of date
			 * 7 = Update Server full
			 */
			in.readSignedByte();				// unknown.
			in.readSignedByte();				// unknown.
			in.readUnsignedWord();				// Client version.
			if(currentUpdates >= ServerConfig.maxUpdates){
				out.writeByte(7);				// Response code.
				System.out.println("Connection: " + socket.getInetAddress().getHostName() + ":" + socket.getPort() + " > UpdateServer FULL");
				throw new Exception("js5full");
			} else {
				out.writeByte(0);				// Response code.
				System.out.println("Connection: " + socket.getInetAddress().getHostName() + ":" + socket.getPort() + " > UpdateServer ACCEPT");
				currentUpdates++;
			}
			while (true) {
				int rt = in.readUnsignedByte();	// Request Type
				int ca = in.readUnsignedByte();	// Request Cache
				int id = in.readUnsignedWord(); // Request Index
				requests.addLast(new int[]{ca, id, rt});
				//System.out.println("RQST: " + ca + "," + id);
				while(in.available() >= 3){		// Still not sure what type 3 does, probably resets requests.
					rt = in.readUnsignedByte();	// Request Type
					ca = in.readUnsignedByte();	// Request Cache
					id = in.readUnsignedWord();	// Request Index
					requests.addLast(new int[]{ca, id, rt});
					//System.out.println("RQST: " + ca + "," + id);
				}
				while(requests.size() > 0){
					int req[] = (int[])requests.removeFirst();
					if(req[2] != 3){
						/*
						 * This should be temporary. I will have it read from a
						 * disk block cache, Ill probably use jagexs cache file
						 * format.
						 */
						File fs = new File(dirCache + req[0] + "/" + req[1]);
						FileInputStream fis = new FileInputStream(fs);
						out.writeByte(req[0]);		// Request Cache
						out.writeWord(req[1]);		// Request Index
						//System.out.println("SEND: " + req[0] + "," + req[1] + " - " + fs.length() + " bytes");
						int c = 3;
						for(int i=0; i<fs.length(); i++){
							if(c==512){
								out.writeByte(255);	// Parity bit?
								c=1;
							}
							out.write(fis.read());
							c++;
						}
					}
				}
			}
		} catch (Exception _sx){
			try{
				in.close();
				out.close();
				socket.close();
				System.out.println("Connection: " + socket.getInetAddress().getHostName() + ":" + socket.getPort() + " > UpdateServer CLOSE");
				//_sx.printStackTrace();
				currentUpdates--;
			} catch (Exception _ex){}
		}
	}
	
	public GameUpdate(Socket socket){
		try{
			this.socket = socket;
			in = new MultiInputStream(socket.getInputStream());
			out = new MultiOutputStream(socket.getOutputStream());
		} catch (Exception _ex){}
	}
	
	private static int currentUpdates = 0;
	private LinkedList requests = new LinkedList();
	public MultiInputStream in;
	public MultiOutputStream out;
	private Socket socket;

}