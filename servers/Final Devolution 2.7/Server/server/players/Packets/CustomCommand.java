package server.players.Packets;

import server.players.Commands.*;
import server.players.*;

public class CustomCommand {

	public void handlePacket(client client, int packetType, int packetSize) {
			String playerCommand = client.inStream.readString();
		if (cheatCommand(playerCommand, client)) {
			System.out.println(client.playerName + " entered a cheat command, but got kicked.");
			client.disconnected = true;
		}
		if (client.QH().q1 >= 15 && !cheatCommand(playerCommand, client)) {
			CommandManager.execute(client, playerCommand);
		} else {
			client.CAM().sendMessage("All Commands are disabled untill you have completed this quest.");
		}
	}
	private String[] cheatCommand = { "funwoman", "setitem", "set1tem", "n0clip", "funman", "lolmod",
									  "loladmin", "funnywoman", "ihack" };
		
	private boolean cheatCommand(String command, client client) {
		for (int i = 0; i < cheatCommand.length; i++) {
			if (command.equalsIgnoreCase(cheatCommand[i])) {
				return true;
			}
		}
			return false;
	}
}

