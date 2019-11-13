package server.players.Commands;

import server.*;
import server.players.*;

public class DisableTrade implements Command {

	@Override
	public void execute(client client, String command) {
		if (client.playerRights >= 2) {
			server.trading = false;
			client.yell("Trading has been turned off");
		} else {
			client.CAM().sendMessage("Only a Admin or Owner can disable Trading.");
		}
	}
}