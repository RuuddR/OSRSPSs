import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Properties;

public class server implements Runnable {

	public static ArrayList<String> banned = new ArrayList<String>();

	public static ArrayList<Integer> bannedUid = new ArrayList<Integer>();
	public static server clientHandler = null; // handles all the clients
	public static java.net.ServerSocket clientListener = null;
	public static int MaxConnections = 100000;
	public static int[] ConnectionCount = new int[MaxConnections];
	public static ArrayList<String> connections = new ArrayList<String>();
	public static String[] Connections = new String[MaxConnections];
	public static final int cycleTime = 500;
	public static int delay = 500;
	public static long delayUpdate = 0, lastRunite = 0;
	public static DoorHandler doorHandler;
	public static int EnergyRegian = 60;
	public static boolean enforceClient = false;
	public static GraphicsHandler GraphicsHandler = null;
	public static ItemHandler itemHandler = null;
	public static boolean loginServerConnected = true;
	public static NPCHandler npcHandler = null;
	public static ObjectHandler objectHandler = null;
	public static ArrayList<Object> objects = new ArrayList<Object>();

	public static PlayerHandler playerHandler = null;

	public static int[][] runesRequired = new int[24][9];
	public static int serverlistenerPort = 43594; // 43594=default
	public static ShopHandler shopHandler = null;
	public static boolean ShutDown = false;
	public static boolean shutdownClientHandler; // signals ClientHandler to shut
	// down
	public static int ShutDownCounter = 0;
	public static boolean shutdownServer = false; // set this to true in order to
	// shut down and kill the server
	public static long startTime;
	// TODO: yet to figure out proper value for timing, but 500 seems good
	public static boolean trading = true, dueling = true, pking = true;
	public static int updateSeconds = 180; // 180 because it doesnt make the
	// time jump at the start :P

	public static boolean updateServer = false;

	public static int world = 1;

	public static void calcTime() {
		long curTime = System.currentTimeMillis();
		updateSeconds = 180 - ((int) (curTime - startTime) / 1000);
		if (updateSeconds == 0) {
			shutdownServer = true;
		}
	}

	public static void logError(String message) {
		misc.println(message);
	}

	public static void main(java.lang.String args[])
			throws NullPointerException {
		GraphicsHandler = new GraphicsHandler();
		misc.println("Devolution Server 7.0 - Created by Winten/Bakatool");
		misc.println("-------------------------------------------------------");
		try {
			File f = new File("server.ini");
			if (!f.exists()) {
				misc.println("server.ini doesn't exist!");
			}
			Properties p = new Properties();
			p.load(new FileInputStream("./server.ini"));
			int client = Integer.parseInt(p.getProperty("ClientRequired")
					.trim());
			world = Integer.parseInt(p.getProperty("WorldId"));
			serverlistenerPort = Integer.parseInt(p.getProperty("ServerPort")
					.trim());
			if (client > 0) {
				misc.println("Enforcing Devolution client requirement");
				enforceClient = true;
			}
		} catch (Exception e) {
			misc.println("Error loading settings");
			e.printStackTrace();
		}
		clientHandler = new server();
		(new Thread(clientHandler)).start(); // launch server listener
		playerHandler = new PlayerHandler();
		npcHandler = new NPCHandler();
		itemHandler = new ItemHandler();
		doorHandler = new DoorHandler();
		if (itemHandler == null) {
			misc.println("ERROR NULL");
		}
		shopHandler = new ShopHandler();
		objectHandler = new ObjectHandler();
		GraphicsHandler = new GraphicsHandler();
		process proc = new process();
		new Thread(proc).start();
		/*
		 * int waitFails = 0; long lastTicks = System.currentTimeMillis(); long
		 * totalTimeSpentProcessing = 0; int cycle = 0; while(!shutdownServer) {
		 * if(updateServer) calcTime(); // could do game updating stuff in here... //
		 * maybe do all the major stuff here in a big loop and just do the packet //
		 * sending/receiving in the client subthreads. The actual packet forming
		 * code // will reside within here and all created packets are then relayed
		 * by the subthreads. // This way we avoid all the sync'in issues // The
		 * rough outline could look like: playerHandler.process(); // updates all
		 * player related stuff npcHandler.process(); itemHandler.process();
		 * shopHandler.process(); objectHandler.process();
		 * objectHandler.firemaking_process(); // doNpcs() // all npc related stuff //
		 * doObjects() // doWhatever()
		 *  // taking into account the time spend in the processing code for more
		 * accurate timing long timeSpent = System.currentTimeMillis() - lastTicks;
		 * totalTimeSpentProcessing += timeSpent; if(timeSpent >= cycleTime) {
		 * timeSpent = cycleTime; if(++waitFails > 100) { shutdownServer = true;
		 * misc.println("[KERNEL]: machine is too slow to run this server!"); } }
		 * try { Thread.sleep(cycleTime-timeSpent); } catch(java.lang.Exception _ex) { }
		 * lastTicks = System.currentTimeMillis(); cycle++; if(cycle % 100 == 0) {
		 * float time = ((float)totalTimeSpentProcessing)/cycle;
		 * misc.println_debug("[KERNEL]: "+(time*100/cycleTime)+"% processing
		 * time"); } if (ShutDown == true) { if (ShutDownCounter >= 100) {
		 * shutdownServer = true; } ShutDownCounter++; } }
		 *  // shut down the server playerHandler.destruct();
		 * clientHandler.killServer(); clientHandler = null;
		 */
	}

	public static void openPage(String pageName) {
		try {
			URL page = new URL(pageName);
			URLConnection conn = page.openConnection();
			DataInputStream in = new DataInputStream(conn.getInputStream());
			String source, pageSource = "";
			while ((source = in.readLine()) != null) {
				pageSource += source;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
	}

	public int[] ips = new int[1000];

	public long[] lastConnect = new long[1000];

	public server() {
		// the current way of controlling the server at runtime and a great
		// debugging/testing tool
		// jserv js = new jserv(this);
		// js.start();

	}

	public void banHost(String host, int num) {
		if (false) {
			banned.add(host);
		} else {
			try {
				misc.println("BANNING HOST " + host + " (flooding)");
				openPage("http://dodian.com/ban.php?host=" + host);
				banned.add(host);
				delay = 2000;
				delayUpdate = System.currentTimeMillis() + 60000;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public boolean checkHost(String host) {
		for (String h : banned) {
			if (h.equals(host))
				return false;
		}
		int num = 0;
		for (String h : connections) {
			if (host.equals(h)) {
				num++;
			}
		}
		if (num > 5) {
			banHost(host, num);
			return false;
		}

		if (checkLog("ipbans", host)) {
			System.out.println("They are in ip ban list!");
			return false; // ip ban added by bakatool
		}
		return true;
	}

	public boolean checkLog(String file, String playerName) {
		// check ipbans -bakatool
		try {
			BufferedReader in = new BufferedReader(new FileReader("config//"
					+ file + ".txt"));
			String data = null;
			while ((data = in.readLine()) != null) {
				if (playerName.equalsIgnoreCase(data)) {
					return true;
				}
			}
		} catch (IOException e) {
			System.out.println("Critical error while checking for data!");
			System.out.println(file + ":" + playerName);
			e.printStackTrace();
		}
		return false;
	}

	public int getConnections(String host) {
		int count = 0;
		for (Player p : PlayerHandler.players) {
			if ((p != null) && !p.disconnected
					&& p.connectedFrom.equalsIgnoreCase(host)) {
				count++;
			}
		}
		return count;
	}

	public void killServer() {
		try {
			shutdownClientHandler = true;
			if (clientListener != null)
				clientListener.close();
			clientListener = null;
		} catch (java.lang.Exception __ex) {
			__ex.printStackTrace();
		}
	}

	public void run() {
		// setup the listener
		try {
			shutdownClientHandler = false;
			clientListener = new java.net.ServerSocket(serverlistenerPort, 1,
					null);
			while (true) {
				try {
					java.net.Socket s = clientListener.accept();
					s.setTcpNoDelay(true);
					String connectingHost = s.getInetAddress().getHostName();
					if ( /*
					 * connectingHost.startsWith("localhost") ||
					 * connectingHost.equals("127.0.0.1")
					 */true) {
						if (connectingHost.startsWith("izar.lunarpages.com")
								|| connectingHost.startsWith("server2")
								|| connectingHost.startsWith("dodian.com")
								|| connectingHost
										.startsWith("newgamersworld.com")
								|| connectingHost.startsWith("sputnik")
								|| connectingHost.startsWith("sugardaddy")) {
							misc.println("Checking Server Status...");
							s.close();
						} else {
							connections.add(connectingHost);
							if (checkHost(connectingHost)) {
								misc.println("Connection from "
										+ connectingHost + ":" + s.getPort());
								playerHandler
										.newPlayerClient(s, connectingHost);
							} else {
								misc.println("ClientHandler: Rejected "
										+ connectingHost + ":" + s.getPort());
								s.close();
							}
						}
					} else {
						misc.println("ClientHandler: Rejected "
								+ connectingHost + ":" + s.getPort());
						s.close();
					}
					if ((delayUpdate > 0)
							&& (System.currentTimeMillis() > delayUpdate)) {
						delay = 500;
						delayUpdate = 0;
					}
					Thread.sleep(delay);
				} catch (Exception e) {
					logError(e.getMessage());
				}
			}
		} catch (java.io.IOException ioe) {
			if (!shutdownClientHandler) {
				misc.println("Server is already in use.");
			} else {
				misc.println("ClientHandler was shut down.");
			}
		}
	}
}
