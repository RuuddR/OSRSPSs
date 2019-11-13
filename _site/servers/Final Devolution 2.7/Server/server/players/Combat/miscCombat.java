package server.players.Combat;


import server.*;
import server.util.*;
import server.world.*;
import server.players.*;
import server.players.Combat.*;

public class miscCombat {

	private client c;
	public miscCombat(client client) {
		this.c = client;
	}
	public boolean isNull(client p) {
		if (p == null) {
			return true;
		}
		return false;
	}
	public boolean isNull(int p) {
		if (p < 1) {
			return true;
		}
		return false;
	}
	public boolean isNull(client p, client i_) {
		if (p == null || i_ == null) {
			return true;
		}
		return false;
	}
	public boolean isNull(int p, client i_) {
		if (p < 1 || i_ == null) {
			return true;
		}
		return false;
	}
	public boolean isNull(client p, int i_) {
		if (p == null || i_ < 1) {
			return true;
		}
		return false;
	}
	public boolean stopAttack(client p) {
		if (p == null) {
			return true;
		}
		if (p.stopAttack) {	
			return true;
		}
		return false;
	}
	public boolean facePlayer(client p) {
		if (isNull(p)) {
			return true;//leave this as true to stop repeats
		}
		if (p.AttackingOn > 0) {
			if (p.faceNPC != 32768 + p.AttackingOn) {
				p.faceNPC = 32768 + p.AttackingOn;
				return false;
			} else {
				p.faceNPC = 32768 + p.AttackingOn;
				return true;
			}
		} else {
			return false;
		}
	}
	public void resetFacingDirection(client p) {
		if (isNull(p)) {
			return;
		}
		p.faceNPC = 0;
	}
	public void resetFeatures() { //will use later
		if (isNull(c)) {
			return;
		}
	}
	public int arrowPullBackPVP(client p, client other) {
		if (p == null || other == null) {
			return -1;
		}
		startProjectile(p,other);
		int A = p.playerEquipment[p.playerArrows];	
		int w = p.playerEquipment[p.playerWeapon];
		if (A == 892) {
			return 24;
		}
		if (A == 890) {
          		return 22;
        	} 
		if (A == 888) {
			return 21;
		} 
		if (A == 886) {
			return 20;
		} 
		if (A == 884) {
			return 18;
		} 
		if (w == 4212) {
			return 250;
		} 
		if (A == 882) {
			return 19;
		} 
		return -1;
	}
	public void startProjectile(client p, client other) {
		if (p == null || other == null) {
			return;	
		}
		
		int EnemyX = PlayerHandler.players[p.AttackingOn].absX;
		int EnemyY = PlayerHandler.players[p.AttackingOn].absY;
		int w = p.playerEquipment[p.playerWeapon];
		int offsetX = (p.absX - EnemyX) * -1;
		int offsetY = (p.absY - EnemyY) * -1;
		if (w == 4212) {
			p.Projectile2(p.absY, p.absX, offsetX, offsetY, 50, p.calcDistanceSpeedPlayerRange() + 5, p.getarrowgfxnow(), 45, 31, -p.AttackingOn-1);
		} else if (w == 15156) {
			p.DeleteArrow();
			p.DeleteArrow();
			p.Projectile2(p.absY, p.absX, offsetX, offsetY, 50, p.calcDistanceSpeedPlayerRange() + 25, p.getarrowgfxnow(), 48, 35, -p.AttackingOn-1);
			p.Projectile2(p.absY, p.absX, offsetX, offsetY, 50, p.calcDistanceSpeedPlayerRange(), p.getarrowgfxnow(), 45, 31, -p.AttackingOn-1);
		} else {
			p.DeleteArrow();		
			p.Projectile2(p.absY, p.absX, offsetX, offsetY, 50, p.calcDistanceSpeedPlayerRange() - 3, p.getarrowgfxnow(), 45, 31, -p.followThisPlayer-1);
		}
	}
	public boolean usingBow() {
		if (c.autoCast) {
			return true;	
		}
		for (int i = 0; i < c.shortbow.length; i++) 
		{
			if ((c.playerEquipment[c.playerWeapon] == c.shortbow[i]) || (c.playerEquipment[c.playerWeapon] == c.longbow[i]))
			
			{
				return true;
			}
		}
		return false;
	}
	public void runCancel(client p, String m) {
		if (isNull(p)) {
			return;
		}
		p.mC().resetFacingDirection(p);
		p.followPlayer = null;
		p.WalkTo(0,0);
		p.ResetAttack();	
		p.CAM().sendMessage(m);
		p.stopAttack = true;
	}
	public boolean checkPlayersAreas(client p) {
		if (isNull(p)) {
			return false;
		}
		if (p.AttackingOn < 1) {
			return false;
		}
		client AttackingOn2 = (client) server.playerHandler.players[p.AttackingOn];
		int EnemyX = PlayerHandler.players[p.AttackingOn].absX;
		int EnemyY = PlayerHandler.players[p.AttackingOn].absY;
		int absX = p.absX;
		int absY = p.absY;
		if (p.isInWilderness(EnemyX, EnemyY, 1) == true && p.isInWilderness(absX, absY, 1) == false) {
			runCancel(p, "You cannot attack players outside of the wilderness.");
			return false;
		}
		if (p.isInWilderness(EnemyX, EnemyY, 1) == false && p.isInWilderness(absX, absY, 1) == true) {
			runCancel(p, "You cannot attack players outside of the wilderness.");
			return false;
		}
		if (p.isInWilderness(EnemyX, EnemyY, 1) == false && p.isInWilderness(absX, absY, 1) == false) {
			runCancel(p, "You cannot attack players outside of the wilderness");
			return false;
		}
		if (p.isInWilderness(EnemyX, EnemyY, 1) == false && p.isInWilderness(absX, absY, 1) == true) {
			runCancel(p, "You cannot attack players outside of the wilderness");
			return false;
		}
		if (AttackingOn2.combatLevel + AttackingOn2.wildyLevel < p.combatLevel || p.combatLevel + AttackingOn2.wildyLevel < AttackingOn2.combatLevel) {
			runCancel(p, "You and your opponent have to greater level difference for this area.");
		}	
		return true;
	}
	public boolean checkPlayersAreas() {
		if (c.followThisPlayer < 1) {
			return false;
		}
		client playerIndex2 = (client) server.playerHandler.players[c.followThisPlayer];
		int EnemyX = PlayerHandler.players[c.followThisPlayer].absX;
		int EnemyY = PlayerHandler.players[c.followThisPlayer].absY;
		int absX = c.absX;
		int absY = c.absY;
		if (c.isInWilderness(EnemyX, EnemyY, 1) == true && c.isInWilderness(absX, absY, 1) == false) {
			runCancel(c, "You cannot attack players outside of the wilderness.");
			return false;
		}
		if (c.isInWilderness(EnemyX, EnemyY, 1) == false && c.isInWilderness(absX, absY, 1) == true) {
			runCancel(c, "You cannot attack players outside of the wilderness.");
			return false;
		}
		if (c.isInWilderness(EnemyX, EnemyY, 1) == false && c.isInWilderness(absX, absY, 1) == false) {
			runCancel(c, "You cannot attack players outside of the wilderness");
			return false;
		}
		if (c.isInWilderness(EnemyX, EnemyY, 1) == false && c.isInWilderness(absX, absY, 1) == true) {
			runCancel(c, "You cannot attack players outside of the wilderness");
			return false;
		}
		if (playerIndex2.combatLevel + playerIndex2.wildyLevel < c.combatLevel || c.combatLevel + playerIndex2.wildyLevel < playerIndex2.combatLevel) {
			runCancel(c, "You and your opponent have to greater level difference for this area.");
		}	
		return true;
	}
	public void prepareNextAttack(client p) {
		if (isNull(p)) {
			return;
		}
		if (p.AttackingOn < 1) {
			return;
		}
		p.lastAction = System.currentTimeMillis();
		p.CAM().getAttackDelay();

	}
	public boolean checkArrows(client p) {
		if (isNull(p)) {
			return false;
		}
		int a = p.playerEquipment[p.playerArrows];
		int w = p.playerEquipment[p.playerWeapon];

		if (a < 1) {
			if (w == 4734) {
				runCancel(p, "You have no bolts.");
				return false;
			} else if (w == 15156) {
				runCancel(p, "You have no arrows.");
				return false;
			} else if (w != 4212 && w != 4214 && w != 4216 && w != 4218 && w != 4220 && w != 4222) {
				runCancel(p, "You have no arrows.");
				return false;
			}
		} else if (a > 0) {
			if (w == 4212 || w == 4214 || w == 4216 || w == 4218 || w == 4220 || w == 4222) {
				if (p.freeSlots() > 0) {
					p.remove(p.playerArrows, p.playerArrows);
				} else {
					runCancel(p, "You cannot use arrows with this bow.");
					return false;
				}
			} else if (w == 4734) {
				if (a != 4740) {
					runCancel(p, "You cannot use arrows with this bow.");
					return false;	
				}
			} else if (w != 4734 && a == 4740) {
				runCancel(p, "You cannot use bolts with this bow.");
				return false;	
			}
		}
		return true;
	}	


	
}