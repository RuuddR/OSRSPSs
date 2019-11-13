/*
 * SpuneRace Server
 * ServerConfig.java
 *
 * (C) 2006 Troy Osborne (defyboy)
 * All Rights Reserved.
 *
 * Created: 3:34 PM Saturday, August 12, 2006
 *
 * mailto:defyboy@gmail.com
 * http://www.defyboy.net
 *
 * -------------------------------
 *
 * Server Configuration
 *
 * Several parameters for the server
 *
 */
public class ServerConfig {

	/*
	 * The port the server listens for incomming game connections
	 * Default = 43594
	 */
	public static final int localPort = 43594;
	
	/*
	 * The port the server listens for incomming telnet connections
	 * Default = 23
	 * Disabled = -1
	 */
	public static final int telnetPort = -1;
	
	/*
	 * Telnet Authentication username and password
	 */
	public static final String telnetUser = "root";
	public static final String telnetPass = "root";
	
	/*
	 * Server name, Displayed on several interfaces.
	 */
	public static final String serverName = "SpuneRace Default Server";
	
	/*
	 * Maximum connections to updateserver
	 */
	public static final int maxUpdates = 500;

	/*
	 * Maximum connections to gameserver
	 */
	public static final int maxPlayers = 2000;
	
	/*
	 * Server version, Dont change this!
	 */
	public static final String serverVersion = "2.0.422";

}