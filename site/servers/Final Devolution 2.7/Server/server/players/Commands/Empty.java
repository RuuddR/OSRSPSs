package server.players.Commands;

import server.*;
import server.players.*;

public class Empty implements Command {

	@Override
	public void execute(client client, String command) {
		long now = System.currentTimeMillis();
		if (now - client.lastAction < 60000) {
			client.CAM().sendMessage("You must wait 60 seconds after an action to ::empty!");
			client.lastAction = now;
		} else {
			client.removeAllItems();
		}
	}
}