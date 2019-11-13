package server.players.Skills;

import server.*;
import server.players.*;
import server.util.*;

public class FireMaking {

	public client client;

	public FireMaking(client c) {
		client = c;
	}

	public int giveExp() {
		return FMEXP;
	}
	public int FMEXP = 0;
	public int fireTotal = 0;
	public void firemakingMain(client c, int level, int woodID, int experience) {
		if (System.currentTimeMillis() - c.lastFire > 1000 && c.hasFinished) {
			if (c.playerLevel[11] >= level) {
				int time = ((c.playerLevel[11] - level) *1000);
				fireTotal = (13000 - time);
				if (fireTotal < 3001) {
					fireTotal = 2000;
				}
				c.hasMoved = false;
				c.hasFinished = false;
				FMEXP = experience;
				c.dropItem(woodID, c.getItemSlot(woodID));
				c.startAnimation(733);
				c.CAM().sendMessage("You attempt to light the logs.");
				server.registerEvent(new Event(6100) {
					public void execute() {
						client c = client;
						if (!c.hasMoved) {
							c.addSkillXP(giveExp() * server.SERVER_EXPERIENCE, 11);
							c.removeGroundItem(c.absX, c.absY, c.woodIDs);
							c.CAM().sendMessage("The log manages to catch on fire!");
							c.ReplaceServerObject(c.absX, c.absY, 2732, -1, 10);
							c.hasFinished = true;
							c.fmwalkto(+1,0);
						}
						server.deregisterEvent(this);
					}
				});
			} else {
				c.CAM().sendMessage("You need an Firemaking level of "+level+" to make this burn.");
			}
			c.lastFire = System.currentTimeMillis();
		}
		return;	
	}	
	
}