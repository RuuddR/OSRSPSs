package server.players.Commands;

import server.*;
import server.players.*;

public class GFX implements Command {

	@Override
	public void execute(client client, String command) {
			if (client.playerRights >= 1) {
				int gfx = Integer.parseInt(command.substring(4));
						client.gfx0(gfx);
			} else {
			client.CAM().sendMessage("Only staff Members can use this command.");
		}
	}
}