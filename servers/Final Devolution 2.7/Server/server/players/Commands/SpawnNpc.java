package server.players.Commands;

import server.*;
import server.players.*;

public class SpawnNpc implements Command {

	@Override
	public void execute(client client, String command) {
		if (client.playerRights >= 1) {
			try  {
				int newNPC = Integer.parseInt(command.substring(9));
				if (newNPC >= 0) {
					server.npcHandler.spawnANPC(newNPC, client.absX, client.absY, client.heightLevel, true);
					client.CAM().sendMessage("You spawn an npc");
				} else {
					client.CAM().sendMessage("No such NPC");
				}
				} catch(Exception e) {
					client.CAM().sendMessage("Wrong Syntax! Use as ::spawnnpc 1");
				}
		} else {
			client.CAM().sendMessage("This command is staff only.");
		}
	}
}