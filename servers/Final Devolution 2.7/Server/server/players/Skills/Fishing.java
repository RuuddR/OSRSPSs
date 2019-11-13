package server.players.Skills;

import server.*;
import server.items.*;
import server.util.*;
import server.world.*;
import server.npcs.*;
import server.players.*;

public class Fishing {

	public client client;

	public Fishing(client c) {
		client = c;
	}

	public void fishingMain(client c, String playerName,int playerID, int level, int itemOne, int itemOneAmount, int itemTwo, int itemTwoAmount, int itemThree, int itemThreeAmount, int fishingAnimation, int animationTimeTillRepeat, int getFishID, int getFishExperience, int catchID) {
		c.PLAYER = playerName;
		c.PLAYER_ID = playerID;
		c.getX = c.absX;
		c.getY = c.absY;
		c.CATCH_ID = catchID;
		c.FISHING_LEVEL = level;
		c.FISH_ID = getFishID;
		c.FISH_EXP = getFishEXP(c);
		c.FISHING_DELAY = animationTimeTillRepeat;
		c.FISHING_ANIMATION = fishingAnimation;
		c.ITEM1 = itemOne;
		c.ITEM1AM = itemOneAmount;
		c.ITEM2 = itemTwo;
		c.ITEM2AM = itemTwoAmount;
		c.ITEM3 = itemThree;
		c.ITEM3AM = itemThreeAmount;
		if (playerID < 1) {
			c.stop();
			return;
		}
		if (c.playerLevel[10] < level) {
			c.CAM().sendMessage("You need a fishing level of " + level + " to fish here.");
			c.stop();
			return;
		}
		if (c.freeSlots() < 1) {
			c.CAM().sendMessage("Your inventory is to full.");
			c.stop();
			return;
		}
		if (!c.playerHasItem(itemOne, itemOneAmount)) {
			c.CAM().sendMessage("You need " + itemOneAmount + " " + c.getItemName(itemOne) + " to fish here.");
			c.stop();
			return;
		}
		if (!c.playerHasItem(itemTwo, itemTwoAmount)) {
			c.CAM().sendMessage("You need " + itemTwoAmount + " " + c.getItemName(itemTwo) + " to fish here.");
			c.stop();
			return;
		}
		if (!c.playerHasItem(itemThree, itemThreeAmount)) {
			c.CAM().sendMessage("You need " + itemThreeAmount + " " + c.getItemName(itemThree) + " to fish here.");
			c.stop();
			return;
		}
		c.isBusy = true;
		c.startAnimation(fishingAnimation);
		c.deleteItem(itemTwo, itemTwoAmount);
		if (!hasStoppedFishing(c)) {
			server.registerEvent(new Event(calculateFishingDelay(client)) {
				public void execute() {
					if (client.PLAYER == null) {
						server.deregisterEvent(this);
						return;
					}
					if (client.PLAYER != null) {
						finish(client);
						server.deregisterEvent(this);
					}
                 			}		
			});
		}
		if (!hasStoppedFishing(client)) {
			server.registerEvent(new Event(client.FISHING_DELAY - 500) {
				public void execute() {
					if (client.PLAYER == null) {
						server.deregisterEvent(this);
						return;
					}
					if (client.PLAYER != null) {
						runRepeat(client);
					}
                 			}		
			});
		}
		if (!hasStoppedFishing(client)) {
			server.registerEvent(new Event(c.LOOP_CHECK) {
				public void execute() {
					if (client.PLAYER == null) {
						server.deregisterEvent(this);
						return;
					}
					if (client.PLAYER != null) {
						loopCheck(client);
					}
                 			}		
			});
		}
		c.lastFish = System.currentTimeMillis();
		
	}	
	public void loopCheck(client c) {
		if (hasStoppedFishing(c)) {
			c.stop();
			return;
		}
		if (!c.playerHasItem(c.ITEM1, c.ITEM1AM)) {
			c.CAM().sendMessage("You need " + c.ITEM1AM + " " + c.getItemName(c.ITEM1) + " to fish here.");
			c.stop();
			return;
		}
	}
	public boolean checkABS(client c) {
		if (hasStoppedFishing(c)) {
			c.stop();
			return false;
		}
		if (c.absX != c.getX && c.absY != c.getY) {
			return false;
		}
		return true;
	}
	public boolean canFish(client c) {
		if (hasStoppedFishing(c)) {
			c.stop();
			return false;
		}
		if (c.absX != c.getX && c.absY != c.getY) {
			c.isBusy = false;
			c.stop();
			return false;
		}
		if (c.absX == c.getX && c.absY == c.getY) {
			return true;
		} else {
			c.stop();
			return false;
		}
	}
	public void runRepeat(client c) {
		if (hasStoppedFishing(c)) {
			c.stop();
			return;
		}
		if (!checkABS(c)) {
			c.stop();
			return;		
		}
		c.startAnimation(c.FISHING_ANIMATION);
	}
	public void finish(client c) {
		if (c.absX != c.getX && c.absY != c.getY) {
			c.isBusy = false;
			c.stop();
			return;
		}
		if (hasStoppedFishing(c)) {
			c.stop();
			return;
		}
		c.getX = c.absX;
		c.getY = c.absY;
		c.addSkillXP(getFishEXP(c) * server.SERVER_EXPERIENCE, 10);
		c.addItem(c.FISH_ID, 1);
		c.CAM().sendMessage("You managed to catch a " + c.getItemName(c.FISH_ID) + ".");
		fishingMain(c, c.PLAYER, c.PLAYER_ID, c.FISHING_LEVEL, c.ITEM1, c.ITEM1AM, c.ITEM2, c.ITEM2AM, c.ITEM3, c.ITEM3AM, c.FISHING_ANIMATION, c.FISHING_DELAY, getFish(c), getFishEXP(c), c.CATCH_ID);
	
	}
	public int calculateFishingDelay(client c) {
		if (c.FISHING_LEVEL < 1) {
			c.stop();
			return 0;
		}
		switch (c.FISHING_LEVEL - c.playerLevel[10]) {
			case 0:
				return 5000 + misc.random(20000);
			case 1:
				return 5000 + misc.random(19000);
			case 2:
				return 5000 + misc.random(18000);
			case 3:
				return 5000 + misc.random(17000);
			case 4:
				return 5000 + misc.random(16000);
			case 5:
				return 5000 + misc.random(15000);
			case 6:
				return 5000 + misc.random(14000);
			case 7:
				return 5000 + misc.random(13000);
			case 8:
				return 5000 + misc.random(12000);
			case 9:
				return 5000 + misc.random(11000);
			case 10:
				return 5000 + misc.random(10000);
			case 11:
				return 5000 + misc.random(9000);
			case 12:
				return 5000 + misc.random(8000);
			case 13:
				return 5000 + misc.random(7000);
			case 14:
				return 5000 + misc.random(6000);
			case 15:
				return 5000 + misc.random(5000);
		}
		return 5000 + misc.random(1000);
	}
	public int getFish(client c) {
		if (c.CATCH_ID == 1) {
			if (c.QH().q1 == 3) {
				c.FISH_ID = 317;
				c.CAM().sendMessage("I got the shrimp. Time to cook it");
			} else {
				c.FISH_ID = 317;
			}
		}
		if (c.CATCH_ID == 2) {
			int catch2 = 2;
			if (misc.random(catch2) == 1) {
				c.FISH_ID = 317;
			} else if (misc.random(catch2) == 2) {
				c.FISH_ID = 321;
			}
		}
		if (c.CATCH_ID == 3) {
			c.FISH_ID = 327;
		}
		if (c.CATCH_ID == 4) {
			int catch4 = 2;
			if (misc.random(catch4) == 1) {
				c.FISH_ID = 345;
			} else if (misc.random(catch4) == 2) {
				c.FISH_ID = 327;
			} 
		} 
		if (c.CATCH_ID == 5) {
			c.FISH_ID = 335;
		}
		if (c.CATCH_ID == 6) {
			int catch6 = 2;
			if (misc.random(catch6) == 1) {
				c.FISH_ID = 335;
			} else if (misc.random(catch6) == 2) {
				c.FISH_ID = 331;
			} 
		} 
		if (c.CATCH_ID == 7) {
			c.FISH_ID = 377;
		}
		if (c.CATCH_ID == 8) {
			c.FISH_ID = 359;
		}
		if (c.CATCH_ID == 9) {
			int catch9 = 3;
			if (misc.random(catch9) == 1) {
				c.FISH_ID = 359;
			} else if (misc.random(catch9) == 2) {
				c.FISH_ID = 359;
			} else if (misc.random(catch9) == 3) {
				c.FISH_ID = 371;
			} 
		}
		if (c.CATCH_ID == 10) {
			int catch10 = 2;
			if (misc.random(catch10) == 1) {
				c.FISH_ID = 359;
			} else if (misc.random(catch10) == 2) {
				c.FISH_ID = 371;
			} 
		} 
		if (c.CATCH_ID == 11) {
			c.FISH_ID = 353;
		}
		if (c.CATCH_ID == 12) {
			int catch12 = 2;
			if (misc.random(catch12) == 1) {
				c.FISH_ID = 353;
			} else if (misc.random(catch12) == 2) {
				c.FISH_ID = 341;
			} 
		}
		if (c.CATCH_ID == 13) {
			int catch13 = 3;
			if (misc.random(catch13) == 1) {
				c.FISH_ID = 353;
			} else if (misc.random(catch13) == 2) {
				c.FISH_ID = 341;
			} else if (misc.random(catch13) == 2) {
				c.FISH_ID = 363;
			} 
		}
		if (c.CATCH_ID == 14) {
			c.FISH_ID = 383;
		}
		return c.FISH_ID;
	}
	public int getFishEXP(client c) {
		if (c.FISH_ID < 0) {
			c.stop();
			return 0;
		}
		if (c.FISH_ID == 317) { // shrimps lvl 1
			c.FISH_EXP = 10;
		}
		if (c.FISH_ID == 327) { // sardine lvl 5
			c.FISH_EXP = 20;
		}
		if (c.FISH_ID == 345) { // herring lvl 10
			c.FISH_EXP = 30;
		}
		if (c.FISH_ID == 321) { // anchovie lvl 15
			c.FISH_EXP = 40;
		}
		if (c.FISH_ID == 353) { // Mackerel lvl 16
			c.FISH_EXP = 35;
		}
		if (c.FISH_ID == 335) { // trout lvl 20
			c.FISH_EXP = 50;
		}
		if (c.FISH_ID == 341) { // cod lvl 23
			c.FISH_EXP = 45;
		}
		if (c.FISH_ID == 331) { // salmon lvl 30
			c.FISH_EXP = 70;
		}
		if (c.FISH_ID == 359) { // tuna lvl 35
			c.FISH_EXP = 80;
		}
		if (c.FISH_ID == 377) { // lobster lvl 40
			c.FISH_EXP = 90;
		}
		if (c.FISH_ID == 371) { // swordfish lvl 45
			c.FISH_EXP = 100;
		}
		if (c.FISH_ID == 363) { // bass lvl 46 big net
			c.FISH_EXP = 100;
		}
		if (c.FISH_ID == 383) { // shark lvl 76 harpoon
			c.FISH_EXP = 110;
		}
		return c.FISH_EXP;
	}
	public void stop(client c) {
		c.PLAYER = "null";
		c.PLAYER_ID = 0;
		c.FISH_EXP = 0;
		c.FISH_ID = 0;
		c.isBusy = false;
		c.startAnimation(-1);
		
	}
	public boolean hasStoppedFishing(client c) {
		if (!c.isBusy) {
			return true;
		}
		return false;
	}

}