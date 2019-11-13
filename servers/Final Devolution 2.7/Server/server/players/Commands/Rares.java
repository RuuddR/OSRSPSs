package server.players.Commands;

import server.*;
import server.players.*;

public class Rares implements Command {

	public static final int[] rares = { 1037, 1050};

	@Override
	public void execute(client client, String command) {
		for (int i = 0; i < rares.length; i++) {
			client.addItem(rares[i], 10);
		}
		client.addItem(1419, 1);
			
	}
}