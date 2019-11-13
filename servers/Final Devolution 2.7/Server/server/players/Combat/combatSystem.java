package server.players.Combat;

import server.*;
import server.util.*;
import server.npcs.*;
import server.world.*;
import server.players.*;
import server.players.Combat.*;

public class combatSystem {

	private client c;
	public combatSystem(client client) {
		this.c = client;
	}

	public void combatExecuter(int playerId, int attackingOn) {
		if (playerId < 0){
			return;
		}
		
		final client i = (client) server.playerHandler.players[playerId];
		final client i_ = (client) server.playerHandler.players[i.AttackingOn];
		i_.KillerId = playerId;
		if (attackingOn != i.AttackingOn) {
			return;
		}
		if (i.mC().usingBow()) 
			i.rE().runRangedEvent(i);
		else 
			i.mE().runMeleeEvent(i);
		i.updateRequired = true;
             	i.appearanceUpdateRequired = true;
	}
}