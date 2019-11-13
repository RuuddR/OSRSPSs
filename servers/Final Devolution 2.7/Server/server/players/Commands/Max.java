package server.players.Commands;

import server.*;
import server.players.*;

public class Max implements Command {

	@Override
	public void execute(client client, String command) {
				client.getDharokBonus();
				client.CAM().sendMessage("Max: "+client.getMaxHit()+".");
				client.extraDamage = 0;
	}
}