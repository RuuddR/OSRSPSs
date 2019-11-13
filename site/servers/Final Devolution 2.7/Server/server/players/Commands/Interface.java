package server.players.Commands;

import server.*;
import server.players.*;

public class Interface implements Command {

	@Override
	public void execute(client client, String command) {
		if (client.playerRights >= 1) {
			int id = Integer.parseInt(command.substring(10));
			client.println_debug("Interface: "+id);
			client.CAM().showInterface(id);
		} else {
			client.CAM().sendMessage("Only staff memebers can use this command.");
		}
	}
}