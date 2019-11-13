package server.players.Combat;

import server.*;
import server.util.*;
import server.npcs.*;
import server.world.*;
import server.players.*;
import server.players.Combat.*;

public class defenceAnimations {

	private client c;

	public static final int[] weapons = { 4718, 4131 };

	public defenceAnimations(client client) {
		this.c = client;
	}
	public boolean twoHanded() {
		int w = c.playerEquipment[c.playerWeapon];
		for (int i = 0; i < weapons.length; i++) {
			if (w == i || c.mC().usingBow()) {
				return true;
			}
		}
		return false;
	}
	public int getDefendEmote() {
		int s = c.playerEquipment[c.playerShield];
		int w = c.playerEquipment[c.playerWeapon];
		if (s == -1 && !twoHanded()) { 
			return 404;  
		}
		if (s > 1) { 
			return 1156; 
		}
		if (twoHanded()) { 
			return 424;  
		} else {
			return 1156;
		}
	}
}