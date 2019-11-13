package server.players.Skills;

import server.*;
import server.util.*;
import server.npcs.*;
import server.items.*;
import server.world.*;
import server.players.*;

public class ThievingNPC {
	
	public client client;
	
	public ThievingNPC(client c) {
		client = c;
	}

	public void pickPocket(client c, int lev, int loot, int amount, int exp, String name, int hit) {
		int chance = 0;
		if (c.playerLevel[17] >= lev) {
			chance = 3 +(c.playerLevel[17] - lev);
		 	if (System.currentTimeMillis() - c.lastThieve > 2500 && !c.isStunned) {
				if (misc.random(chance) >= 3) {
					c.addSkillXP(exp * server.SERVER_EXPERIENCE, 17);
					c.CAM().sendMessage("You pickpocket the " + name);
					c.addItem(loot, amount);
					c.startAnimation(881);
					c.updateRequired = true;
					c.appearanceUpdateRequired = true;
				} else {
					c.isStunned = true;
					server.npcHandler.npcs[c.NPCSlot].faceplayer(c.playerId);
					server.npcHandler.npcs[c.NPCSlot].animNumber = 390;
					server.npcHandler.npcs[c.NPCSlot].updateRequired = true;
					server.npcHandler.npcs[c.NPCSlot].animUpdateRequired = true;
					c.stunDelay = 15;
					c.hitDiff2 = 1 + misc.random(hit);
					c.dealDamage(c.hitDiff2);
					c.startAnimation(422);
					c.arrowPullBack(80);
					c.refreshhps();
					c.updateRequired = true;
					c.hitUpdateRequired2 = true;
					c.appearanceUpdateRequired = true;
					
				}
				c.lastThieve = System.currentTimeMillis();
            			}
        		} else if (c.playerLevel[17] < lev) {
            			c.CAM().sendMessage("You need a thieving level of " + lev + " to pickpocket " + name);
        		}
    		}
	}