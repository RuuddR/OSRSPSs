package server.players.Skills;

import server.players.*;
import server.*;
import server.util.*;

public class ThievingStalls implements Skill {

	public client client;
	public int xp = 0;
	public int item = 0;
	public int toadd = 0;
	public int replace = 634;
	public long cantheivdelay, emotionDelay;


	public ThievingStalls(client c) {
		client = c;
	}

	public ThievingStalls(client c, int object, String type, int x, int y) {
		c = c;
		readInput(c.playerLevel[17], type, c, x, y, object);
	}
	public void readInput(int level, String type, client c, int objectX, int objectY, int objectID) {
		synchronized(this) {
		if (c.freeSlots() > 0) {
			if (type.equals("BCSTALL1") && level >= 5) {//bake stall
				if (misc.random(3) == 1) {
					xp = 25;
					item = 1891;
					c.canthiev = true;
				} else if (misc.random(3) == 2) {
					xp = 25;
					item = 1897;
					c.canthiev = true;
				} else {
					xp = 25;
					item = 2309;
					c.canthiev = true;
				}
				
			}
			else if (type.equals("BCSTALL2") && level >= 5) {//bake stall
				if (misc.random(3) == 1) {
					xp = 25;
					item = 1891;
					c.canthiev = true;
				} else if (misc.random(3) == 0) {
					xp = 25;
					item = 1897;
					c.canthiev = true;
				} else {
					xp = 25;
					item = 2309;
					c.canthiev = true;
				}
				
			}
			else if (type.equals("SILVERSTALL") && level >= 50) {
				if (misc.random(1) == 1) {
					xp = 80;
					item = 2355;
					c.canthiev = true;
				} else {
					xp = 30;
					item = 442;
					c.canthiev = true;
				}
			}
			else if (type.equals("SILKSTALL1") && level >= 10) {
				xp = 30;
				item = 950;
				c.canthiev = true;
			}
			else if (type.equals("SILKSTALL2") && level >= 10) {
				xp = 30;
				item = 950;
				c.canthiev = true;
			}
			else if (type.equals("GEMSTALL") && level >= 75) {
				if (misc.random(6) == 1) {
					xp = 116;
					item = 1623;
					c.canthiev = true;
				} else if (misc.random(6) == 2) {
					xp = 116;
					item = 1605;
					c.canthiev = true;
				} else if (misc.random(6) == 3) {
					xp = 116;
					item = 1603;
					c.canthiev = true;
				} else if (misc.random(6) == 1) {
					xp = 116;
					item = 1601;
					c.canthiev = true;
				} else {
					xp = 116;
					item = 1623;
					c.canthiev = true;
				}
			}
			else if (type.equals("FURSTALL") && level >= 15) {
				xp = 45;
				item = 958;
				c.canthiev = true;
			}
			else if (type.equals("SPICESTALL") && level >= 5) {
				xp = 95;
				item = 2007;
				c.canthiev = true;
			} else {
				c.CAM().sendMessage("You need a higher theiving level to steal from this stall!");
				c.canthiev = false;
			} 
			} else {
				c.CAM().sendMessage("Inventory is Full!");
				c.canthiev = false;
			}
			
		
	} try {
		if (c.canthiev == true && c.GoodDistance(c.skillX, c.skillY, c.getX(), c.getY(), 1)) {
		c.TurnPlayerTo(objectX, objectY);
		c.thTimer = 5;
		}
		if (type.equals("BCSTALL1") || emotionDelay - System.currentTimeMillis() > 15000 && !type.equals("SILVERSTALL") && !type.equals("BCSTALL2") && !type.equals("SILKSTALL1") && !type.equals("SILKSTALL2") && !type.equals("GEMSTALL") && !type.equals("FURSTALL") && !type.equals("SPICESTALL")) {
			emotionDelay = System.currentTimeMillis();
			server.worldObject.registerObject(1, objectX, objectY, objectID, replace, 25, 10, 1, objectID);
		}
		else if (!type.equals("BCSTALL1") && type.equals("SILVERSTALL") && !type.equals("BCSTALL2") && !type.equals("SILKSTALL1") && !type.equals("SILKSTALL2") && !type.equals("GEMSTALL") && !type.equals("FURSTALL") && !type.equals("SPICESTALL")) {
			server.worldObject.registerObject(1, objectX, objectY, objectID, replace, 25, 10, 2, objectID);
		}
		else if (!type.equals("BCSTALL1") && !type.equals("SILVERSTALL") && type.equals("BCSTALL2") && !type.equals("SILKSTALL1") && !type.equals("SILKSTALL2") && !type.equals("GEMSTALL") && !type.equals("FURSTALL") && !type.equals("SPICESTALL")) {
			server.worldObject.registerObject(1, objectX, objectY, objectID, replace, 20, 10, 3, objectID);
		}
		else if (!type.equals("BCSTALL1") && !type.equals("SILVERSTALL") && !type.equals("BCSTALL2") && type.equals("SILKSTALL1") && !type.equals("SILKSTALL2") && !type.equals("GEMSTALL") && !type.equals("FURSTALL") && !type.equals("SPICESTALL")) {
			server.worldObject.registerObject(1, objectX, objectY, objectID, replace, 25, 10, 2, objectID);
		}
		else if (!type.equals("BCSTALL1") && !type.equals("SILVERSTALL") && !type.equals("BCSTALL2") && !type.equals("SILKSTALL1") && type.equals("SILKSTALL2") && !type.equals("GEMSTALL") && !type.equals("FURSTALL") && !type.equals("SPICESTALL")) {
			server.worldObject.registerObject(1, objectX, objectY, objectID, replace, 25, 10, 1, objectID);
		}
		else if (!type.equals("BCSTALL1") && !type.equals("SILVERSTALL") && !type.equals("BCSTALL2") && !type.equals("SILKSTALL1") && !type.equals("SILKSTALL2") && type.equals("GEMSTALL") && !type.equals("FURSTALL") && !type.equals("SPICESTALL")) {
			server.worldObject.registerObject(1, objectX, objectY, objectID, replace, 25, 10, 3, objectID);
		}
		else if (!type.equals("BCSTALL1") && !type.equals("SILVERSTALL") && !type.equals("BCSTALL2") && !type.equals("SILKSTALL1") && !type.equals("SILKSTALL2") && !type.equals("GEMSTALL") && type.equals("FURSTALL") && !type.equals("SPICESTALL")) {
			server.worldObject.registerObject(1, objectX, objectY, objectID, replace, 25, 10, 0, objectID);
		}
		else if (!type.equals("BCSTALL1") && !type.equals("SILVERSTALL") && !type.equals("BCSTALL2") && !type.equals("SILKSTALL1") && !type.equals("SILKSTALL2") && !type.equals("GEMSTALL") && !type.equals("FURSTALL") && type.equals("SPICESTALL")) {
			server.worldObject.registerObject(1, objectX, objectY, objectID, replace, 25, 10, 0, objectID);
		}
		c.THSTALLS().doaction(c, item, -1, -1, objectX, objectY, objectID, xp);
		c.canthiev = true;
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	public void out(String s, client c) {}

	public boolean doaction(client c, int toadd, int toremove, int toremove2, int x, int y, int objectid, int xp) {
				if (c.canthiev == true && cantheivdelay - System.currentTimeMillis() < -5000) {
					server.worldObject.reduceHealth(x, y);
					c.CAM().sendMessage("You got some "+c.getItemName(toadd)+".");
					c.addItem(toadd, 1);
					c.addSkillXP(xp * server.SERVER_EXPERIENCE, 17);
					c.canthiev = false;
					cantheivdelay = System.currentTimeMillis();
				}
		return false;
	}
}