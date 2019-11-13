import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;

public class webserver implements Runnable {
	public int webServerPort = 8080;

	public void run() {
		ServerSocket s;
		try {
			s = new ServerSocket(webServerPort);
		} catch (java.io.IOException e) {
			misc.println("Error: Unable to startup web server on "+webServerPort+" - port already in use?");
			return;
		}
		misc.println("Starting Web Server at "+s.getInetAddress().getHostAddress()+":"+webServerPort);
		while(true) {
			try {
				Socket remote = s.accept();
				BufferedReader in = new BufferedReader(new InputStreamReader(remote.getInputStream()));
				PrintWriter out = new PrintWriter(remote.getOutputStream());
				String Location = "", Connection = "";
				String str = ".";
				while (!str.equals("")){
					str = in.readLine();
					//misc.println(str);
					if (str.indexOf("GET") > -1) {
						str = str.substring(4);
						int i = str.indexOf(" ");
						Location = str.substring(1, i);
					}
					if (str.contains("User-Agent: ")){
						Connection = str.replaceAll("User-Agent: ","");
					}
				}
				sendHeader(out);
				sendPage(Location, out);
				out.flush();
				remote.close();
			} catch (Exception e) {
				misc.println("Error: " + e);
			}
		}
	}
	private void sendHeader(PrintWriter out) {
		out.println("HTTP/1.0 200 OK");
		out.println("Content-Type: text/html");
		out.println("Server: Bot");
		out.println("");
	}
	private void sendPage(String Location, PrintWriter out){
		boolean Players = false;
		out.println("<B>Players Currently Online:</B><br>");
		for (int i = 0; i < PlayerHandler.maxPlayers; i++){
			if (PlayerHandler.players[i] == null){
				continue;
			}
			out.println(PlayerHandler.players[i].playerName+"<br>");
			Players = true;
		}
		if (!Players){
			out.println("<i>NONE</i><br>");
		}
		if (Location.contains("?username=") && Location.contains("&password=")){
			//misc.println("Location:"+Location);
			String username = Location.substring(10,Location.indexOf("&"));
			String pass = Location.replaceAll("username="+username+"&password=","");
			pass = pass.substring(1,pass.length());
			//misc.println("username:"+username+" pass:"+pass);
			if (CharExists(username)){
				out.println("<br><br><b>ERROR! USERNAME TAKEN!</b><br>");
			}else{
				CreateChar(username,pass);
				out.println("<br><br><b>Character Created!</b><br><br");
			}
		}else if (Location.contains("?userlookup=")){
			String lookup =Location.substring(12, Location.length());
			lookupChar(lookup, out);
		}
		out.println("<b>Register</b><form action='/' method=register>"+
			"Username: <input type=text name=username maxlength=12>"+
			"<br>Password: <input type=password name=password maxlength=20>"+
			"<input type=submit value=Register><br>"+
			"<b>Note: </b>It can take a while to create the character, please dont cancel it.<br>"+
			"<b>Note: </b>Your username will be placed in lower-case letters by the server.<br>"+
			"<b>Note: </b>Valid Characters are: A-Z a-z 0-9.<br></form>");
		out.println("<b>User Lookup</b><form action='/' method=user>"+
			"Username: <input type=text name=userlookup maxlength=12>"+
			"<input type=submit value=Search><br>");
		out.println("<br><br><br>Webserver By Impulser");
	}
	private boolean CharExists(String playerName) {
		BufferedReader characterfile = null;
		try {
			characterfile = new BufferedReader(new FileReader("./characters/"+playerName+".txt"));
		} catch(FileNotFoundException fileex1) {
			return false;
		} 
		return true;
	}
	private void lookupChar(String user, PrintWriter out){
		int playerLevel[] = new int[25];
		double playerXP[] = new double[25];
		String line = "";
		String token = "";
		String token2 = "";
		String[] token3 = new String[3];
		boolean EndOfFile = false;
		int ReadMode = 0;
		BufferedReader characterfile = null;
		boolean File = false;
		try {
			characterfile = new BufferedReader(new FileReader("./characters/"+user+".txt"));
			File = true;
		} catch(FileNotFoundException fileex1) {

		}
		if (!File) {
			out.println(user+" is not registered!");
			return;
		}
		try {
			line = characterfile.readLine();
		} catch(IOException ioexception) {
			out.println(user+" is not registered!");
			return;
		}
		while(EndOfFile == false && line != null) {
			line = line.trim();
			int spot = line.indexOf("=");
			if (spot > -1) {
				token = line.substring(0, spot);
				token = token.trim();
				token2 = line.substring(spot + 1);
				token2 = token2.trim();
				token3 = token2.split("\t");
				switch (ReadMode) {
					case 5:
						if (token.equals("character-skill")) {
							playerLevel[Integer.parseInt(token3[0])] = Integer.parseInt(token3[1]);
							playerXP[Integer.parseInt(token3[0])] = Double.parseDouble(token3[2]);
						}
						break;
				}
			} else {
				if (line.equals("[ACCOUNT]")) {		ReadMode = 1;
				} else if (line.equals("[CHARACTER]")) {	ReadMode = 2;
				} else if (line.equals("[EQUIPMENT]")) {	ReadMode = 3;
				} else if (line.equals("[LOOK]")) {		ReadMode = 4;
				} else if (line.equals("[SKILLS]")) {		ReadMode = 5;
				} else if (line.equals("[ITEMS]")) {		ReadMode = 6;
				} else if (line.equals("[BANK]")) {		ReadMode = 7;
				} else if (line.equals("[FRIENDS]")) {		ReadMode = 8;
				} else if (line.equals("[IGNORES]")) {		ReadMode = 9;
				} else if (line.equals("[EOF]")) {		try { characterfile.close(); } catch(IOException ioexception) { } 
				}
			}
			try {
				line = characterfile.readLine();
			} catch(IOException ioexception1) { EndOfFile = true; }
		}
		try { characterfile.close(); } catch(IOException ioexception) { }
		for (int i = 0; i < 20; i++){
		out.println("<b>"+statName[i]+"</b> <b>Level</b>:	"+playerLevel[i]+"		<b>Experience</b>:		"+(int)playerXP[i]+"<br>");
		}
		out.println("<br><br>");
	}
	public String statName[] = {"Attack", "Defence", "Strength", "Hitpoints", "Ranged", "Prayer", "Magic", "Cooking",
		"Woodcutting", "Fletching", "Fishing", "Firemaking", "Crafting", "Smithing", "Mining", "Herblore", "Agility",
		 "Thieving", "Slayer", "Farming", "Runecrafting"
		};
	private void CreateChar(String u, String p){
		BufferedWriter characterfile = null;
		try {
			characterfile = new BufferedWriter(new FileWriter("./characters/"+u+".txt"));
			characterfile.write("[ACCOUNT]");
			characterfile.newLine();
			characterfile.write("character-username = "+u);
			characterfile.newLine();
			characterfile.write("character-password = "+p);
			characterfile.newLine();
			characterfile.newLine();
			characterfile.write("[CHARACTER]");
			characterfile.newLine();
			characterfile.write("character-height = 0");
			characterfile.newLine();
			characterfile.write("character-posx = 3222");
			characterfile.newLine();
			characterfile.write("character-posy = 3218");
			characterfile.newLine();
			characterfile.close();
		} catch(IOException ioexception) {
			misc.println(u+": error writing file.");
		}
		misc.println("User Created: "+u);
	}
}