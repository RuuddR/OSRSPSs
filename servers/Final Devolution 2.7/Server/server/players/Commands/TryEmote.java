package server.players.Commands;

import server.*;
import server.players.*;

public class TryEmote implements Command {

	@Override
	public void execute(client client, String command) {
		if (client.playerRights >= 1) {
				if (client.emoting) {
					client.emoting = false;
				} else {
					client.emoting = true;
				}
			} else {
				client.CAM().sendMessage("This command is only for staff members.");
		}
	}
}