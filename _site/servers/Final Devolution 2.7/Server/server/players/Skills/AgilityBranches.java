package server.players.Skills;

import server.*;
import server.players.*;
import server.util.*;

public class AgilityBranches {

	public client client;

	public AgilityBranches(client c) {
		c = c;
	}

	public void RunBranches(client c, int level, int X, int Y, int PosX, int PosY, int Xz, int Yz, int Xc, int Yc) {
		if (c.getStatus().isBusy()) {
			return;
		}
		if (c.playerLevel[16] >= level) {
			if (System.currentTimeMillis() - c.lastAgilityBranches > 1000 && !c.hasFinishedAgilityBranches) {
				c.getStatus().setBusy();
				if (c.absX == X && c.absY == Y || c.absX == PosX && c.absY == PosY || c.absX == Xz && c.absY == Yz || c.absX == Xc && c.absY == Yc) {
					c.cumb(c);
					c.isRunning2 = false;
					c.isRunning = false;
					c.hasFinishedAgilityBranches = true;
					RunMiddleBranches(c);
					c.startAnimation(828);
					c.lastAgilityBranches = System.currentTimeMillis();
				} else {
					return;
				}
				
			}
		} else {
			c.CAM().sendMessage("You need a highier Agility level to cross this Object!");
		}
	}
	
	public void RunMiddleBranches(client c) {
		if (c.branchTimer == 0) {//gnome
			c.branchTimer = 3;
		}
		if (c.branchTimer == 1 && c.hasFinishedAgilityBranches) {
			RunBranchEnding(c);
		}
	}
	public void RunBranchEnding(client c) {
		if (c.absX == 2473 && c.absY == 3423 || c.absX == 2474 && c.absY == 3422 || c.absX == 2472 && c.absY == 3422) {//gnome
			c.teleportToX = 2473;
			c.teleportToY = 3420;
		} else {
			c.teleportToX = c.absX;
			c.teleportToY = c.absY-1;
		}
		if (c.heightLevel == 2) {
			c.heightLevel = 0;
		} else if (c.heightLevel == 1) {
			c.heightLevel = 2;
		} else if (c.heightLevel == 0) {
			c.heightLevel = 1;
		}

		c.addSkillXP(60 * server.SERVER_EXPERIENCE, 16);
		c.hasFinishedAgilityBranches = false;
		c.TurnPlayerTo(c.objectXz, c.objectYz);
		c.updateRequired = true;
		c.appearanceUpdateRequired = true;
		c.cumb(c);
		c.resetCumb(c);
		c.getStatus().setNotBusy();
	}

}