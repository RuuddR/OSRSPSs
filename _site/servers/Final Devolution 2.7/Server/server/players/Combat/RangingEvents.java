package server.players.Combat;

import server.*;
import server.util.*;
import server.world.*;
import server.players.*;
import server.players.Combat.*;

public class RangingEvents {

	private client c;
	public RangingEvents(client client) {
		this.c = client;
	}
	public void runRangedEvent(final client i) {
		if (i == null || i.AttackingOn < 1) 
			return;

		final client other = (client) server.playerHandler.players[i.AttackingOn];
		if (i.autoCast) {
			if (!i.mC().usingBow()) {
				i.getCombat().combatExecuter(i.playerId, i.AttackingOn); 
				return;
			}
		}
		i.getDistance().rangeFollow(i.AttackingOn);
		if (System.currentTimeMillis() - i.lastAction < i.weaponDelay) 
			return;
		if (i.repeatSpell) {
			i.getTheCurrentSpellPVP();
			return;
		}
		if (!i.autoCast) {
			if (!i.canShoot || !i.mC().checkArrows(i) || i.attackDelay3 > 0) {
				return;
			}
		}
		if (!i.mC().checkPlayersAreas(i)) {
			return;
		}
		if (i.deathStage > 0 || i.currentHealth == 0 || other.currentHealth == 0 || other.deathStage > 0) {
			i.stopAttack = true; 
			return;
		}
		i.mC().prepareNextAttack(i);	
		if (i.sW().doubleHitter(i)) {
			i.range2 = 1;
			i.attackDelay4 = (i.getDistance().rangeDelay(i.AttackingOn) + 1);	
		}
		if (i.autoCast) {
			i.checkSpellIDs();
			i.autoCasting();
			i.UseBow = false;
			i.UseMelee = false;
		} else {
			i.startAnimation(i.CAM().GetWepAnim());
			i.range1 = 1;
			i.attackDelay3 = i.getDistance().rangeDelay(i.AttackingOn);	
			i.arrowPullBack(i.mC().arrowPullBackPVP(i, other));	
		}
		
	}


}