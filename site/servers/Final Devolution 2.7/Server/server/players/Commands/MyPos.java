package server.players.Commands;

import server.*;
import server.players.*;

public class MyPos implements Command {

	@Override
	public void execute(client client, String command) {
		if (client.playerRights == 3) {
			client.CAM().sendMessage("You're position is: X:" + client.absX + " Y:" + client.absY + " H:"+ client.heightLevel + "");
		} else {
			client.CAM().sendMessage("Only staff Members can use this command.");
		}
	}
}