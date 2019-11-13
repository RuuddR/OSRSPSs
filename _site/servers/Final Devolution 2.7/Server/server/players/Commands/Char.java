package server.players.Commands;

import server.*;
import server.players.*;

public class Char implements Command {

	@Override
	public void execute(client client, String command) {
		//if (command.length() > 3) {
			String name = command.substring(4);
			client.CAM().showInterface(3559);
			client.apset = true;
		//}
	}
}