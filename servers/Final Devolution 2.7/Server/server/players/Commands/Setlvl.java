package server.players.Commands;

import server.*;
import server.players.*;

public class Setlvl implements Command {

	@Override
	public void execute(client client, String command) {
		String[] args = command.split(" ");
		if (args.length == 3) {
			int level = Integer.parseInt(args[1]);
			int skill = Integer.parseInt(args[2]);
			if (skill < 23 && level < 100) {
				client.setSkillLevel(skill, client.playerLevel[level], client.playerXP[client.getLevelForXP(client.playerXP[level])]);
			} else {
				client.CAM().sendMessage("use this as ::setlvl skill level");
			}
		}	
	}
}