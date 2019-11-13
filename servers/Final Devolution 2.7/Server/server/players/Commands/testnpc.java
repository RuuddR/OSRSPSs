package server.players.Commands;
import server.npcs.*;
import server.*;
import server.players.*;

public class testnpc implements Command {
	@Override
	public void execute(client client, String command) {
		if (client.playerRights >= 1) {
			try  {
				int newNPC = Integer.parseInt(command.substring(8));
				NPC n = server.npcHandler.npcs[newNPC];
				if (newNPC >= 0) {
					client.CAM().sendMessage("npc details: "+n.npcType+" "+n.aggressive+" "+n.attackLevel+".");
				} else {
					client.CAM().sendMessage("No such NPC");
				}
				} catch(Exception e) {
					client.CAM().sendMessage("Wrong Syntax! Use as ::testnpc id");
				}
		} else {
			client.CAM().sendMessage("This command is staff only.");
		}
	}
}		