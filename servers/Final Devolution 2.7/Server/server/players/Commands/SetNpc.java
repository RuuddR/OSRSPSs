package server.players.Commands;

import server.*;
import server.players.*;

public class SetNpc implements Command {

	@Override
	public void execute(client client, String command) {
			if (client.playerRights >= 1) {
				client.npcId = Integer.parseInt(command.substring(7));
				client.npcId2 = client.npcId;
				client.isNpc = true;
				client.CAM().sendMessage("You have Transformed in to NPCID: " + client.npcId2);
		} else {
			client.CAM().sendMessage("Only staff members can use this command");
		}
	}
}