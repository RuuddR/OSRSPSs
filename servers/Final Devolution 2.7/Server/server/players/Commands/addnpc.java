package server.players.Commands;

import server.*;
import server.players.*;

public class addnpc implements Command {
	int npc;
	int d;
	int hp;
	@Override
	public void execute(client client, String command) {
		if (client.playerRights > 2) {
			try {
				String[] args = command.split(" ");
				if (args.length == 3) {
					npc = Integer.parseInt(args[1]);
					d = Integer.parseInt(args[2]);
					int x = client.absX;
					int y = client.absY;
					int h = client.heightLevel;
					boolean respawn = true;
					if (npc > 2000) {
						client.CAM().sendMessage("NPCID cannot exceed the limit of 2000.");
						return;
					}
					if (d > 10) {
						client.CAM().sendMessage("distance cannot exceed the limit of 10.");
						return;
					}
					hp = server.npcHandler.npcs[npc].HP;
					server.npcHandler.newNPC(npc, x, y, h, x+d, y+d, x-d, y-d, 1, server.npcHandler.GetNpcListHP(npc), respawn);
					client.appendToAutoSpawn2(npc, x, y, x+d, y+d, x-d, y-d);	
				} 
			} catch (Exception e) {
				client.CAM().sendMessage("Incorrect usage,  ::addnpc npcid distance");
			}
		} else {
			client.CAM().sendMessage("Only staff members can use this command.");
		}
	}
}		