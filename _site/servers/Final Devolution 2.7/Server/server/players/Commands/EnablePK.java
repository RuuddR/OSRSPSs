package server.players.Commands;

import server.*;
import server.players.*;

public class EnablePK implements Command {

	@Override
	public void execute(client client, String command) {
		if (client.playerRights >= 1) {
				server.pking = true;
				client.yell("PVP has been turned on");
		} else {
			client.CAM().sendMessage("Only a Admin or Owner can enable Pking");
		}
	}
}