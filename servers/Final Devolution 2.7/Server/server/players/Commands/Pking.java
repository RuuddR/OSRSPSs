package server.players.Commands;

import server.*;
import server.players.*;

public class Pking implements Command {

	public static final int[] item = { 5699, 4152, 6570, 4588, 2551, 3752, 6890, 4132, 4154, 6737, 6568,
	1128, 1080, 1094, 1164, 1202, 4212, 861};
	
	
	@Override
	public void execute(client client, String command) {
		for (int i = 0; i < item.length; i++) {
			client.addItem(item[i], 10);
		}
		client.addItem(892, 1000);
			
	}
}