package server.players.Commands;

import server.*;
import server.players.*;

public class Bonus implements Command {

	@Override
	public void execute(client client, String command) {
		client.CAM().sendMessage("Best bonus: "+client.CheckBestBonuz()+".");
	}
}