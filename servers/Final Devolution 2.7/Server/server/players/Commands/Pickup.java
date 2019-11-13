package server.players.Commands;

import server.*;
import server.players.*;

public class Pickup implements Command {

	@Override
	public void execute(client client, String command) {
			//	if (client.playerRights >= 1) {
					String[] args = command.split(" ");
				if (args.length == 3) {
					int newItemID = Integer.parseInt(args[1]);
					int newItemAmount = Integer.parseInt(args[2]);
				if ((newItemID <= 99999) && (newItemID >= 0)) {
					client.addItem(newItemID, newItemAmount);
				} else {
					client.CAM().sendMessage("You cannot spawn items over ID-99999.");
				}
			}
		//} else {
		//	client.CAM().sendMessage("Only staff Members can use this command.");
		//}
	}
}