package server.players.Commands;

import server.*;
import server.players.*;

public class EnableTrade implements Command {

	@Override
	public void execute(client client, String command) {
		if (client.playerRights >= 2) {
			server.trading = true;
			client.yell("Trading has been turned on");
		} else {
			client.CAM().sendMessage("Only a Admin or Owner can enable Trading.");
		}
	}
}