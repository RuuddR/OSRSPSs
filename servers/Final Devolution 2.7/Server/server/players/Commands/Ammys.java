package server.players.Commands;

import server.*;
import server.players.*;

public class Ammys implements Command {

	public static final int[] ammys = { 1497, 1713, 1726, 1728, 1730, 1732, 6585};

	@Override
	public void execute(client client, String command) {
		for (int i = 0; i < ammys.length; i++) {
			client.addItem(ammys[i], 10);
		}
			
	}
}