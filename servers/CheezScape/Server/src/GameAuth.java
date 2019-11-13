/*
 * SpuneRace Server
 * GameAuth.java
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
 * Game Authentication.
 *
 * Login/Authentication methods for a connecting client
 *
 */

import java.net.*;
import java.math.BigInteger;
import java.io.*;

public class GameAuth {

	/*
	 * RSA Keypair for New Engine Private Servers.
	 * Read the /docs/client/clientguide.txt document for more information.
	 */
	BigInteger rsaMod = new BigInteger("6259303740527262883015923895570727397218781226085692550100785536440535768270843613514350956402286696436891699210001655233714746992165907709666947465760831");
	BigInteger rsaPri = new BigInteger("5142925437767666753050124169398120032164522913964070086787392854834938904906831929600159308346826186029959259049029252165821318148827121340901605979762213");
	BigInteger rsaPub = new BigInteger("58778699976184461502525193738213253649000149147835990136706041084440742975821");

	public GameAuth(MultiInputStream in, MultiOutputStream out){
		this.in = in;
		this.out = out;
	}
	
	public boolean validate(){
		/*
		 * The login process.
		 *
		 * In past the servers modified the client so it didnt download
		 * any updates from the server, they also blocked the RSA packet
		 * So passwords were sent in plaintext.
		 *
		 * Thanks to the new client, which updates directly from the server,
		 * I have been able to write an updateserver to update the client
		 * directly withought a webserver. I have also generated a RSA keypair
		 * for a more secure login packet, and less editing of the client.
		 *
		 * to enable your client to run on this server, you only need change
		 * the modulo exponent, Read the /docs/client/clientguide.txt file for
		 * more information.
		 *		 
		 * I hope future servers will follow in my trend by using the same keypair,
		 * and not transmitting plaintext passwords.
		 *
		 * Login Process Explained:
		 *  Here I have attempted to document the login process.
		 *  This will make it easier for future updates
		 *
		 * key:
		 *  W		= Whos transmitting, c=Client; s=Server
		 *  Type	= Data type, Prefix s=Signed, u=Unsigned
		 *  Field	= Field name
		 *	Desc	= Field Description
		 *
		 * 	W 	Type	Field		Desc
		 * ----------------------------------------
		 * *** Stage 1 ***
		 *	c	uBYTE	request		Request type, 15=login, 14=update
		 *	c	uBYTE	namepart	First letter in name, for choosing loginserver
		 *	s	uBYTE	response1	First response code, 0=ok, see below for others
		 *	s	QWORD	session		Server session key
		 * *** Stage 2 ***
		 *	c	uBYTE	logintype	Logintype, 16=new login; 18=reconnect
		 *	c	uBYTE	decsize		Size of decrypted RSA packet
		 *	c	DWORD	clientver	Client Version
		 *	c	uBYTE	lowmem		Lowmemory (0=true; 1=false)
		 *	c	QWORD	crc[0]		CRC of Cache index 0
		 *	c	QWORD	crc[1]		CRC of Cache index 1
		 *	c	QWORD	crc[2]		CRC of Cache index 2
		 *	c	QWORD	crc[3]		CRC of Cache index 3
		 *	c	QWORD	crc[4]		CRC of Cache index 4
		 *	c	QWORD	crc[5]		CRC of Cache index 5
		 *	c	QWORD	crc[6]		CRC of Cache index 6
		 *	c	QWORD	crc[7]		CRC of Cache index 7
		 *	c	QWORD	crc[8]		CRC of Cache index 8
		 *	c	QWORD	crc[9]		CRC of Cache index 9
		 *	c	QWORD	crc[10]		CRC of Cache index 10
		 *	c	QWORD	crc[11]		CRC of Cache index 11
		 *	c	uBYTE	encsize		Size of encrypted RSA packet
		 * *** Stage 3 (RSA ENCRYPTED) ***
		 *	c	uBYTE	rsahead		RSA Header, should always be 10
		 *	c	QWORD	ckey		Client session key
		 *	c	QWORD	skey		Server session key, skey == session
		 *	c	DWORD	userid		Player userID
		 *	c	DWORD	username	Player username
		 *	c	string	password	Player password
		 *	c	uBYTE	strterm		String terminator, Always 0!, (follows password string)
		 * *** Stage 4 ***
		 *	s	uBYTE	response2	Second response code, 2=ok, see below for others
		 *	s	uBYTE	rights		Player rights, 0=player; 1=gamemod; 2=admin
		 *	s	uByte	ismember	Members, 0=false; 1=true
		 *	s	WORD	randomnum	Seems random....
		 *	s	uBYTE	lgnterm		Login terminator, Always 0!
		 * ---------------------------------------
		 *
		 * Response Codes:
		 * 0  = **OK (Stage 1)**
		 * 1  = **RETRY**
		 * 2  = **OK (Stage 4)**
		 * 3  = Username or password invalid.
		 * 4  = Your account has been disabled.
		 * 5  = Your account is allready logged in.
		 * 6  = Runescape has been Updated, Please reload this page.
		 * 7  = World Full.
		 * 8  = Login Server Offine.
		 * 9  = Too many connections from your address.
		 * 10 = Bad session ID.
		 * 11 = Please try Again.
		 * 12 = You need a members account to login to this world.
		 * 13 = Could not complete login.
		 * 14 = The server is being updated.
		 * 15 = **CRASHES CLIENT** (MoparScape v2.0)
		 * 16 = Login attempts Exceeded
		 * 17 = You are standing in a members-only area.
		 * 18 = Account locked as we suspect it has been stolen
		 * 20 = Invalid loginserver requested
		 * 21 = You have only just left another world
		 * 22 = Malformed login packet.
		 * 23 = No reply from loginserver.
		 * 24 = Error loading your profile.
		 * 25 = Unexpected loginserver response.
		 * 26 = This computers address has been blocked
		 */
		try{
			// Remember the first byte has been read by the listner to determine server type

			// Generate a key for this session.
			long session = ((long)(java.lang.Math.random() * 99999999D) << 32) + (long)(java.lang.Math.random() * 99999999D);

			/*
			 * -- Login block start --
			 */
			 
			// Stage 1
			int namepart	= in.readUnsignedByte();		// Name part, Used for selecting loginserver
			out.writeByte(0); 								// First Response code. (0=ok, otherwise response code)
			out.writeQWord(session);						// Send the session key
			out.flush();
			
			// Stage 2
			int logintype	= in.readUnsignedByte();		// Login type, 16=new; 18=retry
			int decsize		= in.readUnsignedByte();		// Size of decrypted RSA packet
			int clientver	= in.readDWord();				// Client version
			int lowmem		= in.readUnsignedByte();		// Memory, (0=low; 1=high)
			
			int crc[]		= new int[12];
			for(int i=0; i<crc.length; i++){
				crc[i]		= in.readDWord();				// CRC of Cache Index[i]
			}
			int encsize		= in.readUnsignedByte();		// Size of encrypted RSA packet

			System.out.println(encsize);

			/*
			 * -- RSA block start --
			 */
		
			// Stage 3
			byte eb[] = new byte[encsize];
			in.read(eb);
			
			// Decode the RSA block
			ByteArrayInputStream bes = new ByteArrayInputStream(new BigInteger(eb).modPow(rsaPri, rsaMod).toByteArray());
			MultiInputStream ses = new MultiInputStream(bes);
			int rsahead		= ses.readUnsignedByte();		// RSA header, should always be 10
			long cky 		= ses.readQWord(); 				// Client Key
			long sky 		= ses.readQWord();				// Server Key
			
			System.out.println(rsahead);
			
			System.out.println("OSKY: " + session);

			System.out.println("CKY: " + cky);
			System.out.println("SKY: " + sky);
			
			System.out.println("VER: " + clientver);
			
			int userid		= ses.readDWord();				// UserID
			String username	= TextClass.nameForLong(ses.readQWord());	// UserName
			String password	= ses.readString();				// Password
			
			// Calculate the key and set the packet encryption
			
			System.out.println(username + ":" + password);
			
			int skey[] = {(int)(cky >> 32), (int)cky, (int)(sky >> 32), (int)sky};
			inEnc = new FrameEncryption(skey);
			for(int i = 0; i < 4; i++){skey[i] += 50;}
			outEnc = new FrameEncryption(skey);
			
			/*
			 * -- RSA block end --
			 */

			// Stage 4
			out.write(2);			// Second Response Code (0-17; 2=Ok)
			out.write(2);			// Return Player Mod Level
			out.write(1);			// members??
			out.writeWord(1255);	// seems random??
			out.write(0);			// login packet trail.
			
			System.out.println("Connection: Auth OK, Welcome " + username);

			/*
			 * -- Login block end --
			 */
			 
		} catch (Exception _ex) {
			_ex.printStackTrace();
		}
		return true;
	}
	
	public FrameEncryption inEnc;
	public FrameEncryption outEnc;
	
	public MultiInputStream in;
	public MultiOutputStream out;

}