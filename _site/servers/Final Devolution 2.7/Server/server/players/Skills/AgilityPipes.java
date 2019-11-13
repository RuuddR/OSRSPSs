package server.players.Skills;

import server.*;
import server.players.*;
import server.util.*;

public class AgilityPipes {

	public client client;
	
	public AgilityPipes(client c) {
		c = c;
	}

	public void RunPipes(client c, int level, int X1, int Y1) {
		if (c.getStatus().isBusy()) {
			return;
		}
		if (c.playerLevel[16] >= level) {
			if (System.currentTimeMillis() - c.lastAgilityPipes > 1000 && !c.hasFinishedAgilityPipes) {
				if (c.absX == 2487 && c.absY == 3430 || c.absX == 2484 && c.absY == 3430) {
					c.getStatus().setBusy();
					c.cumb(c);
					c.hasFinishedAgilityPipes = true;
					c.playerSE = 0x328;//walk
					c.playerSEW = 746;//walk
					c.isRunning2 = false;
					c.isRunning = false;
					c.WalkTo(X1,Y1);
					RunMiddlePipes(c);
				} else {
					return;
				}
			c.lastAgilityNets = System.currentTimeMillis();
			}
		} else {
			c.CAM().sendMessage("You need a highier Agility level to cross this object!");
		}	
	}

	public void RunMiddlePipes(client c) {
		if (c.absX == 2487 && c.absY == 3437 || c.absX == 2484 && c.absY == 3437) {
			c.playerSE = 0x328;//walk
			c.playerSEW = 0x333;//walk
			c.WalkTo(0, 1);
			RunPipesEnding(c);
		}
	}

	public void RunPipesEnding(client c) {
		if (c.ag1 == 1 && c.ag2 == 1 && c.ag3 == 1 && c.ag4 == 1 && c.ag5 == 1 && c.ag6 == 1) {
			c.addSkillXP(60 * server.SERVER_EXPERIENCE, 16);
			c.addItem(2996, 10);
			c.CAM().sendMessage("You receive 10 tickets for completing the course correctly.");
			c.ag1 = 0;
			c.ag2 = 0;
			c.ag3 = 0;
			c.ag4 = 0;
			c.ag5 = 0;
			c.ag6 = 0;
		} else {
			c.CAM().sendMessage("You failed to complete the course correctly, you receive 0 tickets.");	
			c.ag1 = 0;
			c.ag2 = 0;
			c.ag3 = 0;
			c.ag4 = 0;
			c.ag5 = 0;
			c.ag6 = 0;
		}
			c.ag1 = 0;
			c.ag2 = 0;
			c.ag3 = 0;
			c.ag4 = 0;
			c.ag5 = 0;
			c.ag6 = 0;
		
			c.addSkillXP(60 * server.SERVER_EXPERIENCE, 16);
			c.hasFinishedAgilityPipes = false;
			c.updateRequired = true;
			c.appearanceUpdateRequired = true;
			c.cumb(c);
			c.resetCumb(c);
			c.getStatus().setNotBusy();
	}
}