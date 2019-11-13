
package server.players.Commands;

import server.*;
import server.players.*;

public class Master implements Command {

	@Override
	public void execute(client client, String command) {
		if (client.playerRights < 0)
			return;
		for(int i = 0; i < 24; i++) {
			client.addSkillXP(14000000, i);
		}
		client.CAM().sendMessage("Click the pking teleport on magic book!");
	}
}