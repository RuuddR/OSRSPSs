package server.players.Commands;

import server.*;
import server.players.*;

public class XTeleToMe implements Command {

	@Override
	public void execute(client client, String command) {
		if (client.playerRights >= 1) {
				try {
					String otherPName = command.substring(10);
					int otherPIndex = PlayerHandler.getPlayerID(otherPName);

					if (otherPIndex != -1) {
						client p = (client) server.playerHandler.players[otherPIndex];
						
						p.teleportToX = client.absX;
						p.teleportToY = client.absY;
						p.heightLevel = client.heightLevel;
						p.updateRequired = true;
						p.appearanceUpdateRequired = true;
						p.CAM().sendMessage("You have been teleported via command by " + client.playerName);
					} else {
						client.CAM().sendMessage("The name you have entered doesn't exist.");
					}
				} catch (Exception e) {
					client.CAM().sendMessage("Try and enter a name for the person you want to go to you.");
				}
			} else {
			client.CAM().sendMessage("Only staff members can use this command.");
		}
	}
}