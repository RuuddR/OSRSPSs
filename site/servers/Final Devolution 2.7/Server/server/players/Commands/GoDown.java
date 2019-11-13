package server.players.Commands;

import server.*;
import server.players.*;

public class GoDown implements Command {

	@Override
	public void execute(client client, String command) {
		if (client.playerRights >= 1) {
			client.teleportToX = client.absX;
			client.teleportToY = client.absY;
			client.heightLevel -= 1;
		}
	}
}