package server.players.Combat;


import server.*;
import server.util.*;
import server.world.*;
import server.players.*;
import server.players.Combat.*;

public class Distances {

	private client c;

	public Distances(client client) {
		this.c = client;
	}
	public int calculateDistance(int i) {
		if (c == null || i < 1) {
			return -1;	
		}
		int X = 0;
		int Y = 0;
		client other = (client) server.playerHandler.players[i];
			
		if (c.absX > other.absX) {
			X = c.absX - other.absX;
		} else if (c.absX < other.absX) {
			X = other.absX - c.absX;
		} else if (c.absX == other.absX) {
			X = 0;
		}
		if (c.absY > other.absY) {
			Y = c.absY - other.absY;
		} else if (c.absY < other.absY) {
			Y = other.absY - c.absY;
		} else if (c.absY == other.absY) {
			Y = 0;
		}
		if (X > Y) {
			return X;
		} else {	
			return Y;
		}
	}
	public void meleeFollow(int i) {
		if (c == null || i < 1) {
			return;
		}
		if (c.isFrozen) {
			runFrozenDistance(c.AttackingOn);
			return;
		}
		switch(calculateDistance(i)) {
			case 0:	
			case 1:
				toClose();
				break;
			case 2:
			case 3:
				keepAttacking();
				break;
			case 4:
			case 5:
			case 6:
			case 7:
			case 8:
			case 9:
			case 10:
			case 11:
			case 12:
				getCloser();
				break;
			default:	
				endAction();
				break;
		}
	}
	public void rangeFollow(int i) {
		if (c == null || i < 1) {
			return;
		}
		if (c.isFrozen) {
			runFrozenDistanceRange(c.AttackingOn);
			return;
		}
		switch(calculateDistance(i)) {
			case 0:	
			case 1:
			case 2:
			case 3:
			case 4:
			case 5:
			case 6:
			case 7:
				stopMovements();
				break;
			case 8:
			case 9:
			case 10:
				keepAttackingRange();
				break;
			case 11:
			case 12:
				getCloser();
				break;
			default:	
				endAction();
				break;
		}
	}
	public int rangeDelay(int i) {
		if (c == null || i < 1) {
			return -1;
		}
		switch(calculateDistance(i)) {
			case 0:
			case 1:
			case 2:
			case 3:
			case 4:
			case 5:
				return 3;
			case 6:
			case 7:
			case 8:
			case 9:
				return 4;
		}
		return -1;
	}
	public void runFrozenDistance(int i) {
		switch(calculateDistance(i)) {
			case 0:
				frozen0();	
				break;
			case 1:
				frozen1();
				break;
			case 2:
			case 3:
			case 4:
			case 5:
			case 6:
			case 7:
			case 8:
			case 9:
			case 10:
			case 11:
			case 12:
				frozen();
				break;
		}
	}
	public void runFrozenDistanceRange(int i) {
		switch(calculateDistance(i)) {
			case 0:
				frozen0();	
				break;
			case 1:
			case 2:
			case 3:
			case 4:
			case 5:
			case 6:
			case 7:
			case 8:
			case 9:
				frozen1();
				break;
			case 10:
			case 11:
			case 12:
				frozen();
				break;
		}
	}
	public void toClose() {
		c.canShoot = true;
		c.canMelee = true;
		c.pauseFollow = false;
	}
	public void keepAttacking() {
		c.pauseFollow = false;
		c.canMelee = true;
	}
	public void getCloser() {
		c.pauseFollow = false;
		c.canMelee = false;
		c.canShoot = false;
	}
	public void stopMovements() {
		c.pauseFollow = true;
		c.canMelee = false;
		c.canShoot = true;
	}
	public void keepAttackingRange() {
		c.pauseFollow = false;
		c.canShoot = true;
	}
	public void frozen0() {
		c.canMelee = false;
		c.canShoot = false;
		c.pauseFollow = true;
	}
	public void frozen1() {
		c.canMelee = true;
		c.canShoot = true;
		c.pauseFollow = true;
	}
	public void frozen() {
		c.canMelee = false;
		c.pauseFollow = true;
	}
	public void endAction() {
		c.pauseFollow = false;
		c.stopAttack = true;
	}

}