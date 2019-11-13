package server.players.Skills;

import server.*;
import server.players.*;

public class AgilityNet {

	public client client;

	public AgilityNet(client c) {
		c = c;
	}

	public void ClimbNets(client c, int level, int X, int Y, int X1, int Y1, int X2, int Y2,
				 int X3, int Y3, int X4, int Y4, int X5, int Y5) {
		if (c.getStatus().isBusy()) {
			return;
		}
		if (c.playerLevel[16] >= level) {
			if (System.currentTimeMillis() - c.lastAgilityNets > 1000 && !c.hasFinishedAgilityNets) {
				if (c.absX != X && c.absY != Y || c.absX != X1 && c.absY != Y1 || c.absX != X2 && c.absY != Y2 ||
					c.absX != X3 && c.absY != Y3 || c.absX != X4 && c.absY != Y4 || c.absX != X5 && c.absY != Y5 ) {
					return;
				} else {
					c.getStatus().setBusy();
					c.cumb(c);
					c.isRunning2 = false;
					c.isRunning = false;
					c.hasFinishedAgilityNets = true;
					RunMiddleNets(c);
					c.startAnimation(3063);
				}

			c.lastAgilityNets = System.currentTimeMillis();
			}
		} else {
			c.CAM().sendMessage("You need a highier Agility level to cross this log!");
		}
	}
	public void RunMiddleNets(client c) {
		if (c.upNetTimer == 0) {
			c.upNetTimer = 4;
		} 
		if (c.upNetTimer == 1 && c.hasFinishedAgilityNets) {
			RunEndingNetsClimbing(c);
		}
	}

	public void RunEndingNetsClimbing(client c) {
		if (c.hasFinishedAgilityNets) {
			if (c.agilityhalf) {
				c.teleportToX = c.absX;
				c.teleportToY = c.absY-2;
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
				c.frame1();
				if (c.heightLevel == 0) {
					c.heightLevel = 1;
				}
			}
			if (!c.agilityhalf) {
				c.teleportToX = c.absX;
				c.teleportToY = c.absY+2;
				c.TurnPlayerTo(c.objectXz, c.objectYz);
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
			}
			c.addSkillXP(60 * server.SERVER_EXPERIENCE, 16);
			c.hasFinishedAgilityNets = false;
			c.cumb(c);
			c.resetCumb(c);
			c.getStatus().setNotBusy();
		}
	}
}