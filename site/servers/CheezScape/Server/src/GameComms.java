/*
 * SpuneRace Server
 * GameComms.java
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
 * Game Communications.
 *
 * Base communications for a connected client
 *
 */

import java.net.*;
import java.io.*;

public class GameComms implements Runnable {

	/*
	 * This is unfinished.
	 *
	 * This I intend, will handle all the underlying frame reading/writing
	 * of a connected client, and provide a means for reading/writing frames
	 * to the client.
	 *
	 * There will be a seperate class for handling packets, I will most likely
	 * initiate some sort of system so I can have multiple classes handle
	 * frames by registering the frames they want to capture and having
	 * the handler pass it onto them.
	 *
	 */
	public void run(){

	}

	public GameComms(Socket socket){
		try{
			this.socket = socket;
			in = new MultiInputStream(socket.getInputStream());
			out = new MultiOutputStream(socket.getOutputStream());

			GameAuth ga = new GameAuth(in, out);
			ga.validate();
			
			inEnc = ga.inEnc;						// Incomming Decryption
			outEnc = ga.outEnc;						// Outgoing Encryption

			/*
			 * This is the first frame the server sends.
			 * I dont know what these values do exactly...
			 */ 
			FrameWriter f = new FrameWriter(81);	// First frame, 81
			f.writeWord(55);						// repl1
			f.writeWord(412);						// repl2
			f.writeDWord(-314247680);				// repl[0][0]
			f.writeDWord(-1975556448);				// repl[0][1]
			f.writeDWord(815744465);				// repl[0][2]
			f.writeDWord(686683170);				// repl[0][3]
			f.writeDWord(0);						// repl[1][0]
			f.writeDWord(0);						// repl[1][1]
			f.writeDWord(0);						// repl[1][2]
			f.writeDWord(0);						// repl[1][3]
			f.writeDWord(1594456309);				// repl[2][0]
			f.writeDWord(552903828);				// repl[2][1]
			f.writeDWord(-805965784);				// repl[2][2]
			f.writeDWord(437830510);				// repl[2][3]
			f.writeDWord(466432663);				// repl[3][0]
			f.writeDWord(-1537108050);				// repl[3][1]
			f.writeDWord(1485921906);				// repl[3][2]
			f.writeDWord(-1024707246);				// repl[3][3]
			f.writeDWord(0);						// repl[4][0]
			f.writeDWord(0);						// repl[4][1]
			f.writeDWord(0);						// repl[4][2]
			f.writeDWord(0);						// repl[4][3]
			f.writeDWord(6371840);					// repl[5][0]
			f.writeDWord(-447042588);				// repl[5][1]
			f.writeDWord(883187353);				// repl[5][2]
			f.writeDWord(-1718486201);				// repl[5][3]
			//f.writeWordBigEndian(392);			// repl12 (Crashes client, changed below)
			f.writeWordBigEndian(1000);				// repl12
			f.writeWord(53);						// repl13
			f.write(1);								// repl14
			writeFrame(f);							// Write frame back to client

			/*
			 * Unfortunatly, I dont know the size of most incomming frames
			 * or what they do, This will break the encryption and cause the
			 * client to crash.
			 *
			 * All the frames Ive worked with are for client #422, As far as I
			 * know, they change with each version. We could handle multiple
			 * client versions by having an array of frames for each version
			 * relative to a specific verion.
			 */

			/*
			 * Known Frames:
			 * 4	= loosefocus
			 * 148	= click
			 * 183	= ping?
			 * 199	= ??
			 * 207	= ??
			 * 45	= Report Abuse
			 * 193	= set chat options
			 */

			while(true){
				while(in.available() > 0){
					int frame = in.read() - inEnc.getNextKey() & 0xff;
					int psize = clientFrameSizes[frame];
					if(psize == -1)
						psize = in.read();
					System.out.print("FRAME: " + frame + "; S=" + psize + "; D=");
					for(int i=0; i<psize; i++){
						System.out.print(" " + TextClass.toHex(in.read()));
					}
					System.out.println();
				}
			}


		} catch (Exception _ex) {
			_ex.printStackTrace();
		}
	}
	
	/*
	 * Send a framewriter object to the client
	 */
	public synchronized void writeFrame(FrameWriter f){
		try{
			int s = serverFrameSizes[f.type];
			byte b[] = f.toByteArray();
			out.write(f.type);
			if(s == -1){
				out.write(b.length);
			}else if(s == -2){
				out.writeWord(b.length);
			}
			out.write(b);
			out.flush();
		} catch (Exception _ex) {
			_ex.printStackTrace();
		}
	}

	/*
	 * Incomming Frame Sizes, for client 422
	 * (-3 = unknown, -2 = varsizeword, -1 = varsize)
	 *
	 * I still dont know most of these.
	 */
	public static final int clientFrameSizes[] = {
	//	 0   1   2   3   4   5   6   7   8   9
		-3, -3, -3, -3,  1, -3, -3, -3, -3, -3,	// 0
		-3, -3, -3, -3, -3, -3, -3, -3, -3, -3,	// 10
		-3, -3, -3, -3, -3, -3, -3, -3, -3, -3,	// 20
		-3, -3, -3, -3, -3, -3, -3, -3, -3, -3,	// 30
		-3, -3, -3, -3, -3,  0, -3, -3, -3, -3,	// 40
		-3, -3, -3, -3, -3, -3, -3, -3, -3, -3,	// 50
		-3, -3, -3, -3, -3, -3, -3, -3, -3, -3,	// 60
		-3, -3, -3, -3, -3, -3, -3, -3, -3, -3,	// 70
		-3, -3, -3, -3, -3, -3, -3, -3, -3, -3,	// 80
		-3, -3, -3, -3, -3, -3, -3, -3, -3, -3,	// 90
		-3, -3, -3, -3, -3, -3, -3, -3, -3, -3,	// 100
		-3, -3, -3, -3, -3, -3, -3, -3, -3, -3,	// 110
		-3, -3, -3, -3, -3, -3, -3, -3, -3, -3,	// 120
		-3, -3, -3, -3, -3, -3, -3, -3, -3, -3,	// 130
		-3, -3, -3, -3, -3, -3, -3, -3,  4, -3,	// 140
		-3, -3, -3, -3, -3, -3, -3, -3, -3, -3,	// 150
		-3, -3, -3, -3, -3, -3, -3, -3, -3, -3,	// 160
		-3, -3, -3, -3, -3, -3, -3, -3, -3, -3,	// 170
		-3, -3, -3,  0, -3, -3, -3, -3, -3, -3,	// 180
		-3, -3, -3,  3, -3, -3, -3, -3, -3,  0,	// 190
		-3, -3, -3, -3, -3, -3, -3, -1, -3, -3,	// 200
		-3, -3, -3, -3, -3, -3, -3, -3, -3, -3,	// 210
		-3, -3, -3, -3, -3, -3, -3, -3, -3, -3,	// 220
		-3, -3, -3, -3, -3, -3, -3, -3, -3, -3,	// 230
		-3, -3, -3, -3, -3, -3, -3, -3, -3, -3,	// 240
		-3, -3, -3, -3, -3, -3   };				// 250

	/*
	 * Outgoing Frame Sizes, From client 422
	 * (-1 = varsize; -2 = varsizeword)
	 */
    public static final int[] serverFrameSizes = {
     //  0   1   2   3   4   5   6   7   8   9
		 0, -2,  0,  0,  0,  0,  0,  0,  0,  0,	// 0
		-2,  2,  0,  8,  0,  0,  0,  0,  0,  0,	// 10
		 0,  0,  0,  0,  0,  0,  0,  3,  0,  0,	// 20
		 0,  0,  0,  0,  0,  0, -1,  0,  0,  0,	// 30
		 0,  0,  0,  0,  0,  5,  0,  3,  0,  0,	// 40
		 0, -2,  0,  1,  0,  0,  0,  0,  5,  0,	// 50
		 4,  0,  0,  0,  0,  5,  0,  0,  0,  0,	// 60
		 0, -1,  4,  0,  0,  0,  0,  2,  6,  0,	// 70
		 0, -2,  2,  0,  0,  0,  0,  0,  5,  0,	// 80
		-2,  2,  0,  0,  2,  0,  0,  0,  0,  0,	// 90
		 0,  0,  0,  0,  0,  0,  0,  2,  0,  0,	// 100
	    -1,  0,  0,  2,  0,  0,  6,  0,  6,  0,	// 110
	     0,  0, 10,  0,  0,  5,  6,  0,  0,  0,	// 120
	     0,  4,  3,  0,  0,  0,  0,  0,  0,  0,	// 130
	     0,  0,  0,  0,  0,  8,  0, 14,  0,  2,	// 140
	    15,  0,  0,  1,  0,  0,  0,  1,  0,  7,	// 150
	     0,  0,  0,  6,  0,  0,  0,  0,  0,  0,	// 160
	     0,  0,  0,  6,  4,  6,  0,  6,  0,  0,	// 170
	     0, -2, -2,  0,  7,  0,  4,  6, -2,  0,	// 180
	     0,  2,  0,  0,  8,  0,  2, 10,  0,  0,	// 190
	     0,  0,  0,  0,  0,  0,  0,  0,  0, -1,	// 200
	     0,  0,  0,  4,  0,  0,  0,  0,  0,  0,	// 210
	     4,  0,  6, -2,  0,  0,  0,  1,  0,  1,	// 220
	     3,  0,  0,  0,  0,  0,  0,  2,  0,  0,	// 230
	     1,  0,  0,  0,  2,  0,  0,  0,  6,  4,	// 240
	     0,  0,  0,  6, -2,  0   };				// 250
	
	public Socket socket;
	public MultiInputStream in;
	public MultiOutputStream out;
	public FrameEncryption inEnc;
	public FrameEncryption outEnc;

}