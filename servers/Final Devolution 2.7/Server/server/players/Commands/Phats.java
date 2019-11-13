package server.players.Commands;

import server.*;
import server.players.*;

public class Phats implements Command {

	public static final int[] phats = { 1039, 1041, 1043, 1045, 1047, 1049};

	@Override
	public void execute(client client, String command) {
		for (int i = 0; i < phats.length; i++) {
			client.addItem(phats[i], 10);
		}
			
	}
}