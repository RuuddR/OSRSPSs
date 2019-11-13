package server.players.Commands;

import server.*;
import server.players.*;

public class ExpRate implements Command {

	@Override
	public void execute(client client, String command) {
			if (client.playerRights > 2) {
			try {
				server.SERVER_EXPERIENCE = Integer.parseInt(command.substring(8));
				if (server.SERVER_EXPERIENCE > 10) {	
					server.SERVER_EXPERIENCE = 1;
					client.CAM().sendMessage("You can only raise this to x10");
					return;
				}
				client.yell("Server experience rate is now "+ server.SERVER_EXPERIENCE +".");
			} catch (Exception e) {
				client.CAM().sendMessage("Incorrect usage,  ::exprate amount");
			}
		} else {
			client.CAM().sendMessage("Only staff members can use this command.");
		}
	}
}