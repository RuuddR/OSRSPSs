package server.players.Skills;

import server.players.client;
import server.util.misc;
import server.*;
import server.util.*;
import server.players.*;

public class Herblore {

	public client client;

	public Herblore(client c) {
		client = c;
	}

	public void PotMaker(client c, String type, String message, int item, int item2, int item3, int xp, int level) {

	if (c.playerLevel[c.playerHerblore] < level) {
		c.CAM().sendMessage("You need a Herblore level of "+ level +" to make this potion.");
		return;
	}
			if (c.playerLevel[c.playerHerblore] >= level) {
				c.lastAction = System.currentTimeMillis();
				c.startAnimation(1652);
				c.CAM().sendMessage(message +" "+ type);
				c.addItem(item2, 1);
				c.deleteItem2(item, 1);
				c.deleteItem2(item3, 1);
				c.addSkillXP(xp * server.SERVER_EXPERIENCE, c.playerHerblore);
			}
		}

	public void HerbIndentify(client c, String type, int level, int herb, int identity, int xp) {

	if (c.playerLevel[c.playerFarming] < level) {
		c.CAM().sendMessage("You need at least "+level+" Herblore to Identify this "+ type +".");
		return;
	}
	for (int i = 0; i < 29; i++) {
		if (type.equals("Herb")) {
			c.addSkillXP(xp * server.SERVER_EXPERIENCE, c.playerHerblore);
			c.deleteItem(herb, 1);
			c.addItem(identity, 1);
		break;
		}
	}
}

	public long lastDrink;

	public void DrinkPot(client c, int skill, int item, int remove) {
		if (System.currentTimeMillis() - lastDrink > 1500) {	
			lastDrink = System.currentTimeMillis();
			c.startAnimation(1327);
			c.playerLevel[skill] += 5;
			c.addItem(item, 1);
			c.deleteItem(remove, 1);
		}
	}
}