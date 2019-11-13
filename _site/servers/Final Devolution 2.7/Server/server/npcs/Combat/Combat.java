package server.npcs.Combat;

import server.*;
import server.players.*;
import server.util.*;
import server.items.*;
import server.world.*;
import server.npcs.*;

public class Combat {

	public NPC n;

	public Combat(NPC NPC) {
		this.n = NPC;
	}
	public void mainMethod() { 
		if (n == null) {
			destructNPC();
			return;
		}
		if (n.needsWalkBack) {
			destructNPC();
			return;	
		}
		switch(n.combatType) {
			case 1:
				runCombatMelee();
				break;
			case 2:
				runCombatRANGED();
				break;
			case 3:
				runCombatMagic();
				break;
			default:
				destructNPC();
			break;
		}
	}
	public boolean needsWalkBack() {
		if (n == null) {
			destructNPC();
			return false;
		}
		if (n.IsDead) {
			destructNPC();
			return false;
		}
		if (n.GoodDistance(n.absX, n.absY, n.makeX, n.makeY, 10) == false) {
			n.needsWalkBack = true;
			return true;
		}
		return false;
	}
	public void runCombatMelee() {
		if (n == null) {
			destructNPC();
			return;
		}
		if (n.IsDead) {
			destructNPC();
			return;
		}
		if (n.playerId < 1 || n.playerName == "") {
			destructNPC();
			return;
		}
		client c = (client) server.playerHandler.players[n.playerId];
		if (c == null) {
			return;
		}
		if (needsWalkBack()) {
			return;
		}
		follow();
		if (n.actionTimer > 0 || n.damageDelay > 0) {
			return;
		}	
		if (n.GoodDistance(n.absX, n.absY, c.absX, c.absY, 1) == true) {
			ATTACK();
		}
		return;

	}
	public void ATTACK() { 			//starts the attack for melee.
		if (n == null) {
			destructNPC();
			return;
		}
		if (n.IsDead) {
			destructNPC();
			return;
		}
		client c = (client) server.playerHandler.players[n.playerId];
		if (c == null) {
			return;
		}
		if (n.IsDead) {
			destructNPC();
			return;
		}
		n.hitPlayerId = n.playerId;
		n.actionTimer = n.attackDelay;
		n.damageDelay = n.damageDelay2;
		n.waiting = true;
		n.animNumber = server.NpcAnimHandler.atk[n.npcType];
		n.animUpdateRequired = true;
	}
	public void runCombatRANGED() {
		if (n == null) {
			destructNPC();
			return;
		}
		if (n.playerId < 1 || n.playerName == "") {
			destructNPC();
			return;
		}
		if (n.IsDead) {
			destructNPC();
			return;
		}
		client c = (client) server.playerHandler.players[n.playerId];
		if (c == null) {
			return;
		}
		if (needsWalkBack()) {
			return;
		}
		n.faceplayer(n.playerId);
		n.updateRequired = true;
		if (n.GoodDistance(n.absX, n.absY, c.absX, c.absY, 5) == false) {
			follow();
		}
		if (n.actionTimer > 0 || n.damageDelay > 0) {
			return;
		}	

		if (n.GoodDistance(n.absX, n.absY, c.absX, c.absY, 8) == true) {
			ATTACKRANGED();
		}
		return;

	}
	public void ATTACKRANGED() {		
		if (n == null) {
			destructNPC();
			return;
		}
		client c = (client) server.playerHandler.players[n.playerId];
		if (c == null) {
			return;
		}
		if (n.IsDead) {
			destructNPC();
			return;
		}
		runProjectile();
		n.hitPlayerId = n.playerId;
		n.actionTimer = n.attackDelay;
		n.damageDelay = n.damageDelay2;
		n.waiting = true;
		n.animNumber = server.NpcAnimHandler.atk[n.npcType];
		n.animUpdateRequired = true;
	}
	public void runCombatMagic() {
		if (n == null) {
			destructNPC();
			return;
		}
		if (n.playerId < 1 || n.playerName == "") {
			destructNPC();
			return;
		}

		if (n.IsDead) {
			destructNPC();		
			return;
		}
		client c = (client) server.playerHandler.players[n.playerId];
		if (c == null) {
			return;
		}
		if (needsWalkBack()) {
			return;
		}
		n.faceplayer(n.playerId);
		n.updateRequired = true;
		if (n.GoodDistance(n.absX, n.absY, c.absX, c.absY, 5) == false) {
			follow();
		}
		if (n.actionTimer > 0 || n.damageDelay > 0) {
			return;
		}
	
		if (n.GoodDistance(n.absX, n.absY, c.absX, c.absY, 8) == true) {
			ATTACKMAGIC();
		}
		return;

	}
	public void ATTACKMAGIC() {		//starts the attack for magic.
		if (n == null) {
			destructNPC();
			return;
		}
		if (n.IsDead) {
			destructNPC();
			return;
		}
		client c = (client) server.playerHandler.players[n.playerId];
		if (c == null) {
			return;
		}
		n.hitPlayerId = c.playerId;
		n.finishMagic = 6 - projectileDelay(n.npcType);
		n.usingMagic = true;
		n.gfx100(n.handGFX);
		n.actionTimer = n.attackDelay;
		n.damageDelay = n.damageDelay2;
		n.waiting = true;
		n.animNumber = server.NpcAnimHandler.atk[n.npcType];
		n.animUpdateRequired = true;
	}
	public int projectileDelay(int type) {//when starting a new spell it will run the animation via the type.
		if (n == null) {
			destructNPC();
			return -1;
		}
		if (n.IsDead) {
			destructNPC();
			return -1;
		}
		switch(type) {
			case 13:
				return 0;
			case 2882:
				return 1;
			default:
				return 0;
		}
	}
	public void npcEndGfx(int type) {	//creates the end gfx before the damage splat.
		if (n == null) {
			destructNPC();
			return;
		}
		if (n.IsDead) {
			destructNPC();
			return;
		}
		int gfx = 0;
		switch(type) {
			case 13:
				gfx = 101;
				break;
			case 2882:
				gfx = 163;
				break;
			default:
				gfx = -1;
		}
		client c = (client) server.playerHandler.players[n.hitPlayerId];
		if (c == null) {
			return;
		}
		if (!c.protMage && n.hitPlayer(n.hitPlayerId)) {
			n.hitMage = true;
			c.arrowPullBack(gfx);

		} else if (!n.hitPlayer(n.hitPlayerId)) {
			n.hitMage = false;
			c.arrowPullBack(85);
		} else {
			n.hitMage = false;
			c.arrowPullBack(85);
		}
	}
	public void runProjectile() {	//creates the projectile for magic
		if (n == null) {
			return;
		}
		if (n.IsDead) {
			destructNPC();
		}
		client c = (client) server.playerHandler.players[n.playerId];
		if (c == null) {
			return;
		}
                int offsetX = ((n.absX) - c.absX) * -1;
               	int offsetY = ((n.absY) - c.absY) * -1;
		c.createPlayersProjectile(n.absY + (4 - n.npcSize), n.absX + (4 - n.npcSize), offsetY, offsetX, 50, 100, n.npcProjectile, 41, 28, - n.playerId - 1);
	}
	public void aggressiveNPC() {	// checks if npc is not already in combat and will find a target to attack.
		if (n == null) {
			destructNPC();
			return;
		}
		if (n.needsWalkBack) {
			destructNPC();
			return;	
		}
		if (n.count > 250) {
			n.count = (n.count -  (100 + misc.random(200)));
		}
		if (n.playerName.length() > 0 && n.playerId > 0 && n.inCombat) {
			return;
		}
		if (n.inCombat) {
			destructNPC();
		}
		for (int i = 1; i < PlayerHandler.maxPlayers; i++) {
			if (PlayerHandler.players[i] != null) {
				if (PlayerHandler.players[i].playerId == n.playerId && n.count < 200) 
					continue;
				if (n.GoodDistance(n.absX, n.absY, PlayerHandler.players[i].absX, PlayerHandler.players[i].absY, n.aggressiveDistance)) {
					n.playerName = "PlayerHandler.players[i].playerName";
					n.playerId = PlayerHandler.players[i].playerId;
					n.inCombat = true;
					n.RandomWalk = false;
					n.count = misc.random(50);
					break;
				}
			}
		}
	}
	public void destructNPC() {	//ends all combat from that npc. (will not reset the player)
		try {
			n.faceplayer(0);
			n.playerName = "";
			n.playerId = 0;
			n.inCombat = false;
			n.RandomWalk = true;
			n.combatTimer = 0;
		} catch (Exception NPC) {
			misc.println(NPC + ": could not be shut down.");
			return;
		}
	}
	public void follow() {
		if (n == null) {
			destructNPC();
			return;
		}
		if (n.playerId < 1 || n.playerName == "") {
			destructNPC();
			return;
		}
		client c = (client) server.playerHandler.players[n.playerId];
		if (c == null) {
			return;
		}
		int follow = c.playerId;
		int playerX = c.absX;
		int playerY = c.absY;
		n.RandomWalk = false;
		n.faceplayer(n.playerId);
		for (int i = 1; i < PlayerHandler.maxPlayers; i++) {
			if (PlayerHandler.players[i] != null) {
				if (PlayerHandler.players[i].playerId == n.playerId && n.playerId != PlayerHandler.players[i].playerId) {
					destructNPC();
					break;
				}
			}
		}
		if (playerY < n.absY) {
			n.moveX = n.GetMove(n.absX, playerX);
			n.moveY = n.GetMove(n.absY, playerY + 1);
		} else if (playerY > n.absY) {
			n.moveX = n.GetMove(n.absX, playerX);
			n.moveY = n.GetMove(n.absY, playerY - 1);
		} else if (playerX < n.absX) {
			n.moveX = n.GetMove(n.absX, playerX + 1);
			n.moveY = n.GetMove(n.absY, playerY);
		} else if (playerX > n.absX) {
			n.moveX = n.GetMove(n.absX, playerX - 1);
			n.moveY = n.GetMove(n.absY, playerY);
		}
		n.getNextNPCMovement();
		n.updateRequired = true;
	}
	public void walkBack() {
		if (n == null) {
			destructNPC();
			return;
		}
		if (n.absX == n.makeX && n.absY == n.makeY) {
			n.needsWalkBack = false;
			n.RandomWalk = true;
			return;
		}
		if (n.needsWalkBack) { //resets npcs killer, facing direction etc..
			n.faceplayer(0);
			n.playerName = "";
			n.playerId = 0;
			n.inCombat = false;
			n.moveX = n.GetMove(n.absX, n.makeX);
			n.moveY = n.GetMove(n.absY, n.makeY);
		}
		n.getNextNPCMovement();
		n.updateRequired = true;
	}
	public void process() {
		if (n.finishMagic > 0) { 				
			n.finishMagic--;
		}
		if (n.usingMagic && n.finishMagic == 4) {
			runProjectile();
		}
		if (n.usingMagic && n.finishMagic == 0) {
			n.finishMagic = -1;
			npcEndGfx(n.npcType);
		}
	}
}