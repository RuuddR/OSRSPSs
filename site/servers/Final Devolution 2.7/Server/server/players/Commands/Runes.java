
package server.players.Commands;

import server.*;
import server.players.*;

public class Runes implements Command {

	@Override
	public void execute(client client, String command) {
		if (client.playerRights >= 1) {
			client.addItem(554, 100);
			client.addItem(555, 100);
			client.addItem(556, 100);
			client.addItem(557, 100);
			client.addItem(558, 100);
			client.addItem(559, 100);
			client.addItem(560, 100);
			client.addItem(561, 100);
			client.addItem(562, 100);
			client.addItem(563, 100);
			client.addItem(564, 100);
			client.addItem(565, 100);
			client.addItem(566, 100);
		} else {
			client.CAM().sendMessage("Only a Staff Member can use this command.");
		}
	}
}