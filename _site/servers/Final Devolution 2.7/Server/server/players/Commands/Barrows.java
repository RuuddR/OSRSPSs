package server.players.Commands;

import server.*;
import server.players.*;

public class Barrows implements Command {

	public static final int[] barrows = { 4709, 4711, 4713, 4715, 4717, 4719, 4721, 4723, 4725, 4727, 4729,
	4731, 4733, 4735, 4737, 4739};

	@Override
	public void execute(client client, String command) {
		for (int i = 0; i < barrows.length; i++) {
			client.addItem(barrows[i], 10);
		}
		client.addItem(4740, 100000);
			
	}
}