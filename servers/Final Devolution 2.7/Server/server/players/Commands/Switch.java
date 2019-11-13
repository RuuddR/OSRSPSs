package server.players.Commands;

import server.*;
import server.players.*;

public class Switch implements Command {
	int npc;
	int d;
	int hp;
	@Override
	public void execute(client client, String command) {
		client.NpcDialogue = 0;
		client.NpcDialogueSend = false;
		client.RemoveAllWindows();
		if (client.ancients == 1) {
			client.setSidebarInterface(6, 1151); 
			client.ancients = 0;
			client.CAM().sendMessage("You convert to normal magic!");
		} else {
			client.setSidebarInterface(6, 12855);
			client.ancients = 1;
			client.CAM().sendMessage("You convert to ancient magicks!");
		}
	}
}		