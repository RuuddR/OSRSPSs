package server.players.Commands;

import server.*;
import server.players.*;
import java.io.*;

public class Yell implements Command {

	@Override
	public void execute(client client, String command) {
		if (command.length() > 5) {
			BufferedWriter yellBW = null;
			if(client.muted == true)
				client.CAM().sendMessage("You are muted and cannot yell!");
			else if(server.lockyell == false) {
				String text = command.substring(5);
				String[] bad = {"chalreq", "duelreq", "tradereq", ". com", "com", 
						"org", "net", "biz", ". net", ". org", ". biz", 
						". no-ip", "- ip", ".no-ip.biz", "no-ip.org", "servegame",
						".com", ".net", ".org", "no-ip", "****", "is gay", "****",
						"crap", "rubbish", ". com", ". serve", ". no-ip", ". net", ". biz"};
			for(int i = 0; i < bad.length; i++){
				if(text.indexOf(bad[i]) >= 0){
					client.CAM().sendMessage("No Advertising");
					return;
				}
			}
			if(client.playerRights == 0) {
				client.yell(client.playerName + ": " +command.substring(5));
				client.yellTimer = 30;
			}
			if(client.playerRights == 1) {
				client.yell("[MOD]"+ client.playerName + ": " +command.substring(5));
				client.yellTimer = 30;
			}
			if(client.playerRights == 2) {
				client.yell("[Admin]"+ client.playerName + ": " +command.substring(5));
				client.yellTimer = 30;
			}
			if (client.playerRights == 3) {
				client.yell("[Owner]"+ client.playerName + ": " +command.substring(5));
				client.yellTimer = 30;
			}
			} else {
				client.CAM().sendMessage("The yell command is currently disabled");
			}
			try {
				yellBW = new BufferedWriter(new FileWriter("bin/config/yells.txt", true));
				yellBW.write(client.playerName+" yelled "+command.substring(5));
				yellBW.newLine();
				yellBW.write("-------------------------------------");
				yellBW.newLine();
				yellBW.flush();
				} catch (IOException ioe) {
					ioe.printStackTrace();
				} finally {
			if (yellBW != null) try {
				yellBW.close();
			} catch (IOException ioe2) {
				client.CAM().sendMessage(" ");
			}
			}
			client.disconnected = false;
		}
	}
}