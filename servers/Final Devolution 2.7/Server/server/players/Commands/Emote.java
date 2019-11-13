package server.players.Commands;

import server.*;
import server.players.*;

public class Emote implements Command {

	@Override
	public void execute(client client, String command) {
			if (client.playerRights >= 1) {
			try {
				client.pEmote = Integer.parseInt(command.substring(6));
				client.updateRequired = true;
				client.appearanceUpdateRequired = true;
			} catch (Exception e) {
				client.CAM().sendMessage("Incorrect usage,  ::emote EMOTEID");
			}
		} else {
			client.CAM().sendMessage("Only staff members can use this command.");
		}
	}
}