
package server.players.Commands;

import server.*;
import server.players.*;

public class Pure implements Command {
	@Override
	public void execute(client client, String command) {
		if (client.playerRights < 0)
			return;

		client.addSkillXP(14000000, 6);
		client.addSkillXP(14000000, 4);
		client.addSkillXP(14000000, 0);
		client.addSkillXP(14000000, 3);
		client.addSkillXP(14000000, 2);
	}
}