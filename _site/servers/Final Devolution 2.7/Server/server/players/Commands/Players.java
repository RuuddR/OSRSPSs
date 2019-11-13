package server.players.Commands;

import server.*;
import server.players.*;

public class Players implements Command {

	@Override
	public void execute(client client, String command) {
				client.CAM().sendMessage("There are currently " + PlayerHandler.getPlayerCount() + " player(s)!");
				client.CAM().sendQuest("@dre@"+Constants.SERVER_NAME+" - Online Players", 8144);
				client.clearQuestInterface();
				client.CAM().sendQuest("@dbl@Online player(s) (" + PlayerHandler.getPlayerCount() + "):", 8145);
				int line = 8147;
				for (int i = 1; i < PlayerHandler.maxPlayers; i++) {
					client playa = client.getClient(i);
					if (!client.validClient(i))
						continue;
					if (playa.playerName != null) {
						String title = "";
						if (playa.playerRights == 1) {
							title = "Mod, ";
						} else if (playa.playerRights == 2) {
							title = "Admin, ";
						}
						title += "level-" + playa.combatLevel;
						String extra = "";
						if (client.playerRights > 0) {
							extra = "(" + playa.playerId + ") ";
						}
						client.CAM().sendQuest("@dre@"+ playa.playerName + "@dbl@ (" + title + ") is at " + playa.absX + ", " + playa.absY, line);
						line++;
					}
				}
				client.CAM().sendFrame79(8143);
				client.CAM().showInterface(8134);
				client.flushOutStream();
	}
}