package server.players.Commands;

import server.*;
import server.players.*;

public class BanUser implements Command {
	@Override
	public void execute(client client, String command) {
	if (client.playerRights >= 2) {
                try {
                    String otherPName = command.substring(8);
                    int otherPIndex = PlayerHandler.getPlayerID(otherPName);

                    if (otherPIndex != -1) {
                        client p = (client) server.playerHandler.players[otherPIndex];

			client.writeLog(p.playerName, "bans");
			p.disconnected = true;
			client.CAM().sendMessage("You have banned "+p.playerName+"!");
                    } else {
                       client.CAM().sendMessage("The name doesnt exist."); 
                    }
                } catch (Exception e) { 
                    client.CAM().sendMessage("Try entering a name you want to ban..");
                }
            }
	}
}