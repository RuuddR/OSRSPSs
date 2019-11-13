package server.players.Commands;

import server.*;
import server.players.*;

public class KickAll implements Command {

	@Override
	public void execute(client client, String command) {
		PlayerHandler.kickAllPlayers = true;
		client.SG().savegame(client, true);
	}
}