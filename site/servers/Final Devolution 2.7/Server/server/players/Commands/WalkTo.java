package server.players.Commands;

import server.*;
import server.players.*;

public class WalkTo implements Command {

	@Override
	public void execute(client client, String command) {
		if (client.playerRights >= 3) {
			String[] args = command.split(" ");
				if (args.length == 3) {
					int X = Integer.parseInt(args[1]);
					int Y = Integer.parseInt(args[2]);
					client.WalkTo(X, Y);
				} else {
					client.CAM().sendMessage("Wrong usage: Use as ::walkto # #");
				}
		} else {
			client.CAM().sendMessage("This command is only for staff members.");
		}
	}
}