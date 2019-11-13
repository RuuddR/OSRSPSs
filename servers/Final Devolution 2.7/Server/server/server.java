package server;

import server.npcs.*;
import server.players.*;
import server.items.*;
import server.util.*;
import server.world.*;
import server.players.Packets.*;
import server.players.MiscHandlers.*;

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
import java.util.*;

public class server implements Runnable {
	/* THIS IS THE EXPERIENCE MULTIPLER */
	public static textHandler textHandler = new textHandler();
	public static int uptime;
	public static int SERVER_EXPERIENCE = 20;
	public static npcsdrops npcsdrops = new npcsdrops();
	public static final boolean lockyell = false;
	public static PrayerHandler PrayerHandler = null;
	public static WorldObject worldObject = new WorldObject();
	public static lvlHandler lvlHandler = new lvlHandler();
	public static NpcAnimHandler NpcAnimHandler = new NpcAnimHandler();
	public static ArrayList<String> banned = new ArrayList<String>();
	public static WeaponHandler WeaponHandler = null;
	public static npcController npcController = null;
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
	public static int EnergyRegian = 60;
	public static boolean enforceClient = false;
	public static GraphicsHandler GraphicsHandler = null;
	public static ItemHandler itemHandler = null;
	public static boolean loginServerConnected = true;
	public static NPCHandler npcHandler = null;
	public static PlayerHandler playerHandler = null;
	public static int[][] runesRequired = new int[24][9];
	public static int serverlistenerPort = Constants.SERVER_PORT; // 43594=default
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
	public static List<Event> events = new ArrayList<Event>();
	public static List<Event> eventsToAdd = new ArrayList<Event>();
	public static List<Event> eventsToRemove = new ArrayList<Event>();

	public static void registerEvent(Event event) {
		eventsToAdd.add(event);
	}

	public static void deregisterEvent(Event event) {
		eventsToRemove.add(event);
	}

	public static void startEvents()
	{
	}

	public static void processEvents() {
		for(Event e : eventsToAdd) {
			events.add(e);
		}
		eventsToAdd.clear();
		for(Event e : events) {
			if(e.isStopped()) {
				eventsToRemove.add(e);
			} else if(e.isReady()) {
				e.run();
			}
		}
		for(Event e : eventsToRemove) {
			events.remove(e);
		}
		eventsToRemove.clear();
	}
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
		clientHandler = new server();
		(new Thread(clientHandler)).start();
		startEvents();
		processEvents();
		playerHandler = new PlayerHandler();
		npcHandler = new NPCHandler();
		itemHandler = new ItemHandler();
		shopHandler = new ShopHandler();
		PrayerHandler = new PrayerHandler();
		GraphicsHandler = new GraphicsHandler();
		process proc = new process();
		new Thread(proc).start();
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

		if (checkLog("ipbans", host)) {
			return false; // ip ban added by bakatool
		}
		return true;
	}

	public boolean checkLog(String file, String playerName) {
		// check ipbans -bakatool
		try {
			BufferedReader in = new BufferedReader(new FileReader("./bin/config/"
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
		try {
			shutdownClientHandler = false;
			clientListener = new java.net.ServerSocket();
			clientListener.bind(new java.net.InetSocketAddress(java.net.InetAddress.getByName("0.0.0.0"), serverlistenerPort), 250);
			while(true) {
				java.net.Socket s = clientListener.accept();
				String connectingHost = s.getInetAddress().getHostName();
				if(true) {
					int Found = -1;
					for (int i = 0; i < MaxConnections; i++) {
						if (Connections[i] == connectingHost) {
							Found = ConnectionCount[i];
							break;
						}
					}
					if (checkHost(connectingHost)) {
						playerHandler.newPlayerClient(s, connectingHost);
					} else {
						s.close();
					}
				} else {
					misc.println("ClientHandler: Rejected "+connectingHost+":"+s.getPort());
					s.close();
				}
			}
		} catch(java.io.IOException ioe) {
			if(!shutdownClientHandler) {
				misc.println("Error: Unable to startup listener on "+serverlistenerPort+" - port already in use?");
			} else {
				misc.println("ClientHandler was shut down.");
			}
		}
	}
}