package server.players.Commands;

import server.*;
import server.players.*;

public class XTeleTo implements Command {

	@Override
	public void execute(client client, String command) {
		if (client.playerRights >= 1) {
				try {
					String otherPName = command.substring(8);
					int otherPIndex = PlayerHandler.getPlayerID(otherPName);

					if (otherPIndex != -1) {
						client p = (client) server.playerHandler.players[otherPIndex];
						client.teleportToX = p.absX;
						client.teleportToY = p.absY;
						client.heightLevel = p.heightLevel;
						client.updateRequired = true;
						client.appearanceUpdateRequired = true;
						client.CAM().sendMessage("You teleport to via command to " + p.playerName);
					}
				} catch (Exception e) {
					client.CAM().sendMessage("Try and enter someones name to teleport to them.");
				}
			} else {
				client.CAM().sendMessage("Only staff members can use this command.");
		}
	}
}