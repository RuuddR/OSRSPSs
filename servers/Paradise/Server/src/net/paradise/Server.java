package net.paradise;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import net.paradise.game.GameService;
import net.paradise.game.player.PlayerHandler;
import net.paradise.util.OutLogger;

public class Server implements Runnable
{

	public static int EnergyRegian = 0;
	public static int MaxConnections = 2;
	public static String[] Connections = new String[MaxConnections];
	public static int[] ConnectionCount = new int[MaxConnections];
	public static boolean ShutDown = false;
	public static int ShutDownCounter = 0;
	public static List<String> connections = new ArrayList<String>();
	public static boolean updateServer = false;
	public static int updateSeconds = 180;
	public static int restartTime;
	public static boolean shutdownServer = false;
	public static boolean shutdownClientHandler;
	public static int port = 43594;
	private static Server singleton = null;
	private static ServerSocket serverSocket = null;

	private static List<String> bannedHostnames;

	private final ScheduledExecutorService scheduler = Executors
			.newSingleThreadScheduledExecutor();

	public static void main(String[] args)
	{
		System.setOut(new OutLogger(System.out));
		System.setErr(new OutLogger(System.err));

		System.out.println("- Paradise 317 -");
		System.out.println("Java version: "
				+ System.getProperty("java.version"));

		refreshHostList();

		singleton = new Server();
		new GameService().start();

		try
		{
			singleton.start();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public static void suspendHostname(String hostname)
	{
		bannedHostnames.add(hostname);

		try
		{
			BufferedWriter bw = new BufferedWriter(new FileWriter(
					"data/bannedips.txt", true));

			bw.write(hostname);
			bw.newLine();
			bw.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public static void refreshHostList()
	{
		bannedHostnames = new ArrayList<String>();

		try
		{
			BufferedReader br = new BufferedReader(new FileReader(
					"data/bannedips.txt"));

			String hostname;
			while ((hostname = br.readLine()) != null)
			{
				bannedHostnames.add(hostname);
			}

			br.close();
		}
		catch (Exception e)
		{
			System.out
					.println("Error reading banned hosts: /data/bannedips.txt");
			e.printStackTrace();
		}
	}

	private void start() throws IOException
	{
		shutdownClientHandler = false;

		try
		{
			serverSocket = new ServerSocket(port, 1, null);
		}
		catch (IOException e)
		{
			if (!shutdownClientHandler)
			{
				System.err.println("Error: Unable to startup listener on "
						+ port + " - port already in use?");
			}
			else
			{
				e.printStackTrace();
			}
		}

		scheduler.scheduleAtFixedRate(singleton, 600, 600,
				TimeUnit.MILLISECONDS);

		if ((serverSocket != null) && (serverSocket.isBound()))
		{
			System.out.println("Paradise is now live! Connect at *:" + port
					+ ".");
		}
	}

	public void run()
	{
		try
		{
			accept();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	private void accept() throws IOException
	{
		for (int i = 0; i < 10; i++)
		{
			Socket socket = acceptSocketSafe();
			socket.setTcpNoDelay(true);
			String hostname = socket.getInetAddress().getHostName();
			boolean banned = (bannedHostnames.contains(hostname) || hostname
					.endsWith(".htc.net"));

			try
			{
				DateFormat dfn = new SimpleDateFormat("yyyyMMdd");
				DateFormat dft = new SimpleDateFormat(
						"yyyy-MM-dd | HH:mm:ss | ");
				Date date = new Date();
				BufferedWriter bw = new BufferedWriter(new FileWriter(
						"./data/conlogs/" + dfn.format(date) + ".txt", true));

				bw.write(dft.format(date) + "Socket connection established:");
				bw.newLine();
				bw.write(dft.format(date) + "Hostname: "
						+ socket.getInetAddress().getHostName());
				bw.newLine();
				bw.write(dft.format(date) + "Address: "
						+ socket.getInetAddress().getHostAddress());
				bw.newLine();
				bw.write(dft.format(date) + "Auto-close: " + banned);
				bw.newLine();
				bw.newLine();
				bw.close();
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}

			if (banned)
			{
				System.out.println("Connection rejected from: " + hostname
						+ " (host banned).");
				socket.close();
				continue;
			}

			// if (!connections.contains(hostname))
			// {
			System.out.println("Connection accepted from " + hostname + ".");
			connections.add(hostname);
			PlayerHandler.getSingleton().registerSocket(socket, hostname);
			// }
			// else
			// {
			// System.out.println("Connection rejected from: "
			// + socket.getInetAddress().getHostName()
			// + " (too many connections).");
			// socket.close();
			// }
		}
	}

	private Socket acceptSocketSafe()
	{ // Anti-nuller by Slysoft
		boolean socketFound = false;
		Socket socket = null;

		do
		{
			try
			{
				socket = serverSocket.accept();
				int opcode = socket.getInputStream().read() & 0xff;

				if ((opcode == 14))
				{
					socketFound = true;
				}
			}
			catch (Exception e)
			{
			}
		} while (!socketFound);

		return socket;
	}

	public void killServer()
	{
		try
		{
			shutdownClientHandler = true;
			if (serverSocket != null)
			{
				serverSocket.close();
			}
			serverSocket = null;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}