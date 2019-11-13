package server.players.Combat;




import server.*;
import server.util.*;
import server.world.*;
import server.players.*;
import server.players.Combat.*;

public class MeleeEvents {

	private client c;
	private client other;
	public MeleeEvents(client client) {
		this.c = client;
	}


	public void runMeleeEvent(final client i) {
		if (i == null || i.AttackingOn < 1) 
			return;

		final client other = (client) server.playerHandler.players[i.AttackingOn];
		if (i.mC().usingBow()) {
			i.getCombat().combatExecuter(i.playerId, i.AttackingOn);
			return;
		}
		i.getDistance().meleeFollow(i.AttackingOn);
		if (i.deathStage > 0 || i.currentHealth == 0 || other.currentHealth == 0 || other.deathStage > 0) {		
			i.stopAttack = true;
			return;
		}
		
		
		int EnemyX = server.playerHandler.players[i.AttackingOn].absX;
		int EnemyY = server.playerHandler.players[i.AttackingOn].absY;
		i.mC().facePlayer(i);
		if (i.GoodDistance(EnemyX, EnemyY, i.absX, i.absY, 3) == false)
			return;

		if (i.canMelee && System.currentTimeMillis() - i.lastAction >= i.weaponDelay && i.mC().checkPlayersAreas(i)) {
			if (i.repeatSpell) {
				i.getTheCurrentSpellPVP();
				return;
			}
			other.arrowPullBack(-1);
			other.startAnimation(other.dA().getDefendEmote());
			if (i.specOn && i.sW().checkWeaponForSpecial(i) && i.sW().hasEnoughSpecial(i)) {
				i.startAnimation(i.sW().getWeaponAnimation(i));
				if (i.playerEquipment[i.playerWeapon] == 4151) {
					other.arrowPullBack(i.sW().getWeaponGrahpics(i));
				} else {
					i.arrowPullBack(i.sW().getWeaponGrahpics(i));
				}
				i.specOn = false;
				i.specialAmount = (i.specialAmount - i.sW().getWeaponsDrainAmount(i));
				i.sendSpecialBarAmount();
				if (i.sW().doubleHitter(i)) {
					i.attackDelay2 = 2;
					i.melee2 = 1;
				}
			} else {
				i.startAnimation(i.CAM().GetWepAnim());
			}
			i.mC().prepareNextAttack(i);
			i.attackDelay = 2;
			i.melee1 = 1;
		}
		
	}

}