package server.players.Commands;

import server.*;
import server.players.*;

public class Tele implements Command {

	@Override
	public void execute(client client, String command) {
		if (client.playerRights >= 1) {
				String[] args = command.split(" ");
				if (args.length == 3) {
					int newPosX = Integer.parseInt(args[1]);
					int newPosY = Integer.parseInt(args[2]);
					client.teleportToX = newPosX;
					client.teleportToY = newPosY;
				} else {
					client.CAM().sendMessage("Wrong usage: Use as ::tele # #");
				}
			} else {
				client.CAM().sendMessage("This command is only for staff members.");
		}
	}
}