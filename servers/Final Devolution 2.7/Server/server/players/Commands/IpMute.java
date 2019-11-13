package server.players.Commands;

import server.*;
import server.players.*;

public class IpMute implements Command {

	@Override
	public void execute(client client, String command) {
            if (client.playerRights > 0) {
                try {
                    String otherPName = command.substring(7);
                    int otherPIndex = PlayerHandler.getPlayerID(otherPName);

                    if (otherPIndex != -1) {
                        client p = (client) server.playerHandler.players[otherPIndex];
                        client.writeLog(p.connectedFrom, "mutes2");
			p.muted = true;
                        p.CAM().sendMessage("You have been ip muted by " + client.playerName);
			client.CAM().sendMessage("You have ip muted "+p.playerName+"");
                    } else { 
                       client.CAM().sendMessage("The name doesnt exist."); 
                    }
                } catch (Exception e) { 
                    client.CAM().sendMessage("Try entering a name you want to tele to ip mute..");
                }
            }
	}
}