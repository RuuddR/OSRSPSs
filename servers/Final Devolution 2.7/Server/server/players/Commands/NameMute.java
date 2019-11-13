package server.players.Commands;

import server.*;
import server.players.*;

public class NameMute implements Command {

	@Override
	public void execute(client client, String command) {
            if (client.playerRights > 0) {
                try {
                    String otherPName = command.substring(5);
                    int otherPIndex = PlayerHandler.getPlayerID(otherPName);

                    if (otherPIndex != -1) {
                        client p = (client) server.playerHandler.players[otherPIndex];

                        p.muted = true;
			client.writeLog(p.playerName, "mutes");
			client.CAM().sendMessage("You have muted "+p.playerName+"!");
                    } else { 
                      	client.CAM().sendMessage("The name doesnt exist."); 
                    }
                } catch (Exception e) { 
                    client.CAM().sendMessage("Try entering a name you want to mute..");
                }
            }
	}
}