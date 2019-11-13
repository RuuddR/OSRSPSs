import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.io.*;
import java.net.*;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import javax.swing.table.AbstractTableModel;

/**
 * <p>Title: ServerGUI</p>
 * <p/>
 * <p>Description: GUI for the Server</p>
 * <p/>
 * <p>Copyright: Copyright (c) 2006 MissSilabsoft</p>
 * <p/>
 * <p>Company: Project HybridScape</p>
 *
 * @author MissSilabsoft
 * @version 1.0
 *
 */
public class ServerGUI extends JFrame implements ActionListener, ListSelectionListener {

	private final static String serverTag = "[S] ";
	private final static String serverName = "Miss. Silabsoft Server Console \u00a9 Miss. Silabsoft @ http://Moparscape.org/smf";
	private final static String newLine = "\n";
	protected static JTextField serverCommand;
	protected static JButton serverCommandExecute;
	protected static JPanel serverCommandPanel;
	protected static JPanel userListPanel;
	protected static JPanel userListPanelControls;
	protected static JTextArea serverConsole;
	protected static JScrollPane serverConsoleScroll;
	protected static JList serverUsers;
	protected static JScrollPane serverUsersScroll;
	protected static JMenuBar jmb;
	protected static JTextField hostField;
	protected static JButton ban;
	protected static JButton ip_ban;
	protected static JLabel Abs;
	protected static JTable UserInfo;
	protected static String[] columnNames = {"Variable Name","Variable Value"};
	protected static Object[][] data = new Object[5][2];
	private static Object lastSelection;
	public static String DownloadUrl = "http://dnd.no-ip.org/ServerConsol/ServerConsol.zip";
	public static String SaveFileName = "ServerConsol.zip";
	public static boolean CFU;
	public static boolean GenStats;

	public ServerGUI() {
        	super("Miss. Silabsoft Server Console \u00a9 Miss. Silabsoft @ http://Moparscape.org/smf");
        	setPreferredSize(new Dimension(550, 440));
        	initComponents();
	}


	public static void addConsole(String consoleText, boolean consoleTag) {
        	if (consoleTag && consoleText.length() > 2)
			serverConsole.append(serverTag + consoleText + newLine);
    	}

    	public static void updateUserList() {
        	String[] players = PlayerHandler.playersCurrentlyOn;
        	int len = 0;
        	for (int i = 0; i < players.length; i++) {
            		if (players[i] != null)
                		len++;
        	}
        	String[] users = new String[len];
        	int pos = 0;
        	for (int i = 0; i < players.length; i++) {
            		if (players[i] != null){
                		users[pos++] = players[i];
				Player[] players2 = PlayerHandler.players;
			}
        	}
        	serverUsers.setListData(users);
	}

	public void addConsole(String consoleText) {
		serverConsole.append(consoleText + newLine);
	}

	private void initComponents() {
        	serverConsoleScroll = new JScrollPane();
        	serverConsole = new JTextArea();
        	serverUsersScroll = new JScrollPane();
        	serverUsers = new JList();
        	userListPanel = new JPanel(new BorderLayout());
       		userListPanelControls = new JPanel(new BorderLayout());
        	serverCommandPanel = new JPanel(new BorderLayout());
        	serverCommand = new JTextField("serverCommand");
        	serverCommandExecute = new JButton("execute");
        	jmb = new JMenuBar();
        	hostField = new JTextField("Hostname goes here");
        	ban = new JButton("Ban");
        	ip_ban = new JButton("IP Ban");
		UserInfo = new JTable(data, columnNames);
		UserInfo.setPreferredSize(new Dimension(190, 120));
        	setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        	setFont(new Font("Arial", 1, 10));
        	serverConsole.setEditable(false);
        	serverConsole.setFont(new Font("Tahoma", 0, 12));
        	serverConsole.setRows(9);
        	serverConsole.setBorder(new EtchedBorder(null, new Color(102, 102, 102)));
        	serverConsoleScroll.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        	serverConsoleScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        	serverConsoleScroll.setAutoscrolls(true);
        	serverConsoleScroll.setViewportView(serverConsole);
        	serverUsers.setBorder(new EtchedBorder(null, new Color(102, 102, 102)));
        	serverUsers.setPreferredSize(new Dimension(120, 100));
        	serverUsers.setListData(new String[]{"12-letters:)"});
        	serverUsers.addListSelectionListener(this);
        	serverUsersScroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        	serverUsersScroll.setViewportView(serverUsers);
		UserInfo.setPreferredSize(new Dimension(150, 120));
        	userListPanelControls.add(ban, BorderLayout.WEST);
		userListPanelControls.add(ip_ban, BorderLayout.CENTER);
		userListPanelControls.add(hostField, BorderLayout.NORTH);
		userListPanelControls.add(UserInfo, BorderLayout.SOUTH);
        	userListPanel.add(serverUsersScroll);
        	userListPanel.add(userListPanelControls, BorderLayout.SOUTH);
        	serverCommand.setColumns(34);
        	serverCommand.setBorder(new EtchedBorder(null, new Color(102, 102, 102)));
        	serverCommand.addActionListener(this);
        	serverCommandPanel.setBackground(new Color(227, 227, 225));
		serverCommandPanel.add(serverCommand);
        	ban.addActionListener(this);
        	ip_ban.addActionListener(this);
        	serverCommandExecute.addActionListener(this);
        	serverCommandPanel.add(serverCommandExecute, BorderLayout.EAST);
        	getContentPane().add(serverConsoleScroll);
        	getContentPane().add(userListPanel, BorderLayout.EAST);
        	getContentPane().add(serverCommandPanel, BorderLayout.SOUTH);
        	setJMenuBar(jmb);
        	pack();
	}

	public void valueChanged(ListSelectionEvent evt) {
        	if (serverUsers.getSelectedValue() != null && !serverUsers.getSelectedValue().equals("")) {
            		lastSelection = serverUsers.getSelectedValue();
            		Player[] players = PlayerHandler.players;
            		for (int i = 0; i < players.length; i++)
                		if (players[i] != null && players[i].playerName.equals(lastSelection)) {
                    			hostField.setText(players[i].connectedFrom);
                	}
        	}
    	}


	public void actionPerformed(ActionEvent evt) {
        	String gsc = evt.getActionCommand();
        		if (gsc.equalsIgnoreCase("execute"))
            	gsc = serverCommand.getText();
        	gsc = gsc.toLowerCase();
        	boolean used = true;
		if (gsc.startsWith("tele")) {
			try
        		{
				for (Player p : server.playerHandler.players){
					if(p != null){
						client person = (client)p;
						if((person.playerName != null || person.playerName != "null") || person.playerName.equals(lastSelection)){
							person.teleportToX = Integer.parseInt(gsc.substring(5,10));
							person.teleportToY = Integer.parseInt(gsc.substring(11,16));
							printlnTag("Teleported "+lastSelection+" to "+person.teleportToX+"X,"+person.teleportToY+"Y");
							return;
						}
					}
        			}
			}
			catch(Exception e) 
        		{
        			printlnTag("Syntax Error! Use as tele #####,#####"); 
				return;
        		}
		}
		if (gsc.startsWith("nick")) {
			for (Player p : server.playerHandler.players){
				if(p != null){
					client person = (client)p;
					if((person.playerName != null || person.playerName != "null") || person.playerName.equals(lastSelection)){
						person.playerName = gsc.substring(5);
						printlnTag(lastSelection+" name is now "+gsc.substring(5));
						return;
					}
				}
			}
				
		}
        	if (gsc.startsWith("msg ")) {
            		printlnTag("[Server] " + gsc.substring(4));
            		PlayerHandler.messageToAll = "[Server] " + gsc.substring(4);
        	}else if (gsc.startsWith("bootall")) {
            		PlayerHandler.kickAllPlayers = true;
        	} else if (gsc.startsWith("kick ")) {
            		PlayerHandler.kickNick = gsc.substring(5);
           		PlayerHandler.messageToAll = "[Server] is Kicking Player: " + gsc.substring(5);
           		printlnTag("player kicked");
        	} else if (gsc.startsWith("update ")) {
            		PlayerHandler.updateSeconds = (Integer.parseInt(gsc.substring(7)) + 1);
            		PlayerHandler.updateAnnounced = false;
            		PlayerHandler.updateRunning = true;
            		PlayerHandler.updateStartTime = System.currentTimeMillis();
        	} else if (gsc.startsWith("ban ")) {
            		String name = gsc.substring(4);
            		if (BanHandler.isBanned(name)) {
                		printlnTag("player is already banned");
                		serverCommand.setText("");
                		return;
            		}
            		if (PlayerHandler.isPlayerOn(name)) {
                		PlayerHandler.kickNick = name;
                		PlayerHandler.messageToAll = "[Server] is KickBanning Player: " + name;
                		printlnTag("player kicked");
            		}
            		BanHandler.ban(name);
            		printlnTag("player banned");
        	} else if (gsc.startsWith("ipban ")) {
            		String name = gsc.substring(6);
            		if (BanHandler.isBanned(name)) {
                		printlnTag("player is already banned");
               			serverCommand.setText("");
                		return;
            		}
            		if (PlayerHandler.isPlayerOn(name)) {
                		PlayerHandler.kickNick = name;
                		PlayerHandler.messageToAll = "[Server] is IP-Banning Player: " + name;
                		printlnTag("player kicked");
            		}
            		Player[] players = PlayerHandler.players;
            		for (int i = 0; i < players.length; i++)
                		if (players[i] != null && players[i].playerName.equalsIgnoreCase(name)) {
                    		try {
                        		BanHandler.unBan(InetAddress.getByName(players[i].connectedFrom));
                        		printlnTag("unbanned player");
                    		}
                    		catch (Exception e) {
                        		e.printStackTrace();
                    		}
                	}
        	} else if (gsc.startsWith("unban ")) {
            		if (!BanHandler.isBanned(gsc.substring(6))) {
                		printlnTag("player isn't banned");
                		serverCommand.setText("");
                		return;
            	}
            		BanHandler.unBan(gsc.substring(6));
            		printlnTag("player unbanned");

        	} else if (gsc.equals("ban")) {
            		if (lastSelection != null) {
                	BanHandler.ban((String) lastSelection);
               		printlnTag(lastSelection + " banned");
            		} else
                		printlnTag("No Player is selected");
            			return;
        	} else if (gsc.equals("ip ban")) {
            		if (lastSelection != null) {
                		Player[] players = PlayerHandler.players;
                		for (int i = 0; i < players.length; i++)
                    			if (players[i] != null && players[i].playerName.equals(lastSelection)) {
                        			try {
                            				BanHandler.ban(InetAddress.getByName(players[i].connectedFrom));
                        			}
                        			catch (Exception e) {
                            				printlnTag("Error banning " + players[i].connectedFrom);
                        			}
                        			printlnTag(players[i].connectedFrom + " Banned");
                    			}
            			} else
                			printlnTag("No Player is selected");
            				return;
        		} else
            			used = false;
        		if (used)
            			serverCommand.setText("");
        	else
            	printlnTag("Unknown Command: " + gsc.split(" ")[0]);

    }


	public static void main() 
	{
        	new ServerGUI().setVisible(true);
	}

    	public static void process()
	{
		updateUserList();
		if(!CFU){
			Update();
			CFU = true;
		}
			pi();
	}

	public static void pi()
	{
		for (Player p : server.playerHandler.players){
			if(p != null){client person = (client)p;
				if((person.playerName != null || person.playerName != "null") || person.playerName.equals(lastSelection)){
           				UserInfo.setValueAt("Player Name:", 0, 0);
					UserInfo.setValueAt(person.playerName, 0, 1);
					UserInfo.setValueAt("absX:", 1, 0);
					UserInfo.setValueAt(person.absX, 1, 1);
					UserInfo.setValueAt("absY:", 2, 0);
					UserInfo.setValueAt(person.absY, 2, 1);
					UserInfo.setValueAt("Height:", 3, 0);
					UserInfo.setValueAt(person.heightLevel, 3, 1);
					UserInfo.setValueAt("Rights:", 4, 0);
					UserInfo.setValueAt(person.playerRights, 4, 1);

				}
			}
        	}
	}



	public static void printlnTag(String str)
	{
        	ServerGUI.addConsole(str, true);
        	javax.swing.JScrollBar jsb = ServerGUI.serverConsoleScroll.getVerticalScrollBar();
        	jsb.setValue(jsb.getMaximum());
	}

	public static void Update()
	{
		if(downloadFile())
		{
			unZipFile();
			deleteFile();
        	} 
		else
        	{
            		deleteFile();
        	}
    	}		


	public static boolean downloadFile()
	{
		try{
        		URLConnection jarUC = (new URL(DownloadUrl)).openConnection();
        		long length = jarUC.getContentLength();
        		FileOutputStream fos = new FileOutputStream(SaveFileName);
        		BufferedOutputStream bos = new BufferedOutputStream(fos);
        		writeStream(jarUC.getInputStream(), bos);
        		fos.close();
        		bos.close();
        		File file = new File(SaveFileName);
        		if(length != file.length())
            			return false;
        		return true;
        	}
        	catch(Exception e)
        	{
            		System.out.println("Error Download File");
        	}
        	return false;
    	}

    	public static void writeStream(InputStream In, OutputStream Out)throws IOException
	{
        	byte Buffer[] = new byte[1024];
        	int Len;
        	while((Len = In.read(Buffer)) >= 0) 
           		Out.write(Buffer, 0, Len);
        	In.close();
        	Out.close();
	}


	public static void unZipFile()
	{
        	try
        	{
			ZipFile ZipFile = new ZipFile(SaveFileName);
            		for(Enumeration Entries = ZipFile.entries(); Entries.hasMoreElements();)
            		{
                		ZipEntry Entry = (ZipEntry)Entries.nextElement();
                		if(Entry.isDirectory())
                		{
                    			new File(Entry.getName()).mkdir();
                		} 
				else
                		{
                    			writeStream(ZipFile.getInputStream(Entry), new BufferedOutputStream(new FileOutputStream(Entry.getName())));
                		}
            		}
            		ZipFile.close();
        	}
        	catch(Exception e)
        	{
            		e.printStackTrace();
        	}
	}
	
	public static void deleteFile()
	{
        	try
        	{
            		File file = new File(SaveFileName);
            		file.delete();
        	}
        	catch(Exception e)
        	{
            		e.printStackTrace();
        	}
    	}

}
