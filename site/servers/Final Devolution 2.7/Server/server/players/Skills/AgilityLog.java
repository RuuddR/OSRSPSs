package server.players.Skills;

import server.*;
import server.players.*;
import server.util.*;


public class AgilityLog {
	public client client;
	public int animation = 762;
	
	public AgilityLog(client c) {
		c = c;
	}
	public int agilitydelay = 5000;
	public int agilitydelay() {
		return agilitydelay;
	}

	public void WalkingOnLogs(client c, int level, int X, int Y, int X1, int Y1, int exp) {
		if (c.getStatus().isBusy()) {
			return;
		}
		if (c.playerLevel[16] >= level) {
			if (System.currentTimeMillis() - c.lastAgility > 1000 && !c.hasFinishedAgility) {
				if (c.absX == X && c.absY == Y) {
					if (c.hasFinishedAgility == false) {
						c.getStatus().setBusy();
						c.cumb(c);
						c.isRunning2 = false;
						c.isRunning = false;
						c.WalkTo(X1, Y1);
						c.hasFinishedAgility = true;
						c.playerSE = 0x328;//walk
						c.playerSEW = 762;//walk
						c.delayA = 10;
						c.delayAA = true;
					}
				} else {
					return;
				}

			c.lastAgility = System.currentTimeMillis();
			}
		} else {
			c.CAM().sendMessage("You need a highier Agility level to cross this object!");
		}
	}
	public void RunAgilityEnding(client c) {
		if (c.hasFinishedAgility == true) {
			c.addSkillXP(60 * server.SERVER_EXPERIENCE, 16);
			c.hasFinishedAgility = false;
			c.playerSE = 0x328; // SE = Standard Emotion
			c.playerSEW = 0x333; // SEW = Standard Emotion Walking
			c.updateRequired = true;
			c.appearanceUpdateRequired = true;
			c.resetWalkingQueue();
			c.cumb(c);
			c.resetCumb(c);
			c.getStatus().setNotBusy();
			c.delayAA = false;
		}
	}
}