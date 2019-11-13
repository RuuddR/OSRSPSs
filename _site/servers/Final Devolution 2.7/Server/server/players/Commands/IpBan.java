package server.players.Commands;

import server.*;
import server.players.*;

public class IpBan implements Command {

	@Override
	public void execute(client client, String command) {
		if (client.playerRights > 1) {
			try {
				String otherPName = command.substring(6);
				int otherPIndex = PlayerHandler.getPlayerID(otherPName);
			if (otherPIndex != -1) {
				client p = (client) server.playerHandler.players[otherPIndex];
				p.writeLog(p.playerLastConnect, "ipbans");
				p.disconnected = true;
				client.CAM().sendMessage("You have ipbanned "+p.playerName+"!");
			} else { 
				client.CAM().sendMessage("The name doesnt exist."); 
			}
			} catch (Exception e) { 
				client.CAM().sendMessage("Try entering a name you want to ip ban..");
			}
			} else {
				client.CAM().sendMessage("Only Staff members can use this command.");
		}
	}
}