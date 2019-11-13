package server.players.Commands;

import server.*;
import server.players.*;

public class EnableDuel implements Command {

	@Override
	public void execute(client client, String command) {
		if (client.playerRights >= 2) {
				server.dueling = true;
				client.yell("Dueling has been turned on");
		} else {
			client.CAM().sendMessage("Only a Admin Or Owner can Enable Dueling.");
		}
	}
}