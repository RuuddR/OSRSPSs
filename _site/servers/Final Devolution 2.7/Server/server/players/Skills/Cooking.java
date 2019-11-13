package server.players.Skills;

import server.*;
import server.items.*;
import server.util.*;
import server.world.*;
import server.npcs.*;
import server.players.*;

public class Cooking {

	public client client;
	
	public Cooking(client c) {
		client = c;
	}

	public void cookingMain(client c, String playerName,int playerID, int level, int itemOne, int itemOneAmount, int cookingAnimation, int FoodID) {
		c.PLAYER = playerName;
		c.PLAYER_ID = playerID;
		c.cookID = FoodID;
		c.COOKING_LEVEL = level;
		c.COOKING_ANIMATION = cookingAnimation;
		c.ITEM1 = itemOne;
		c.ITEM1AM = itemOneAmount;
		if (playerID < 1) {
			c.stop();
			return;
		}
		if (c.playerLevel[7] < level) {
			c.CAM().sendMessage("You need a cooking level of " + level + " to cook this.");
			c.stop();
			return;
		}
		if (!c.playerHasItem(itemOne, itemOneAmount)) {
			c.CAM().sendMessage("You have ran out of supplies.");
			c.stop();
			return;
		}
		c.getStatus().setBusy();
		c.startAnimation(cookingAnimation);
		if (!hasStoppedCooking(c)) {
			server.registerEvent(new Event(c.COOKING_SPEED - 1500) {
				public void execute() {
					if (client.PLAYER == null) {
						server.deregisterEvent(this);
						return;
					}
					if (!client.playerHasItem(client.cookID)) {
						server.deregisterEvent(this);
						return;
					}
					if (client.PLAYER != null) {
						getCookedItem(client);
					}
					server.deregisterEvent(this);
                 			}		
			});
		}
		
	}	
	public void getBurntItem(client c) { //removed for now, noone likes burning food i guess
		if (!c.playerHasItem(c.cookID)) {
			c.stop();
			return;
		}
		int burntItem = 0;
		if (c.cookID == 317) { // shrimps
			burntItem = 323;
		}
		if (c.cookID == 327) { // sardine lvl 5
			burntItem = 369;
		}
		if (c.cookID == 345) { // herring lvl 10
			burntItem = 357;
		}
		if (c.cookID == 321) { // anchovie lvl 15
			burntItem = 323;
		}
		if (c.cookID == 353) { // Mackerel lvl 16
			burntItem = 357;
		}
		if (c.cookID == 335) { // trout lvl 20
			burntItem = 343;
		}
		if (c.cookID == 341) { // cod lvl 23
			burntItem = 343;
		}
		if (c.cookID == 331) { // salmon lvl 30
			burntItem = 343;
		}
		if (c.cookID == 359) { // tuna lvl 35
			burntItem = 367;
		}
		if (c.cookID == 377) { // lobster lvl 40
			burntItem = 381;
		}
		if (c.cookID == 363) { // bass lvl 46 big net
			burntItem = 367;
		}
		if (c.cookID == 383) { // shark lvl 76 harpoon
			burntItem = 387;
		}
		c.deleteItem(c.ITEM1, c.ITEM1AM);
		c.addItem(burntItem, 7);
		c.CAM().sendMessage("You over cooked you're " + c.getItemName(getCookedItem(c)) + ".");
		cookingMain(c, c.PLAYER, c.PLAYER_ID, c.COOKING_LEVEL, c.ITEM1, c.ITEM1AM, c.COOKING_ANIMATION, c.cookID);
	}
	public int getCookedItem(client c) {
		if (!c.playerHasItem(c.cookID)) {
			c.stop();
			return -1;
		}
		int cookedItem = 0;
		if (c.cookID == 317) { // shrimps
			cookedItem = 315;
		}
		if (c.cookID == 327) { // sardine lvl 5
			cookedItem = 325;
		}
		if (c.cookID == 345) { // herring lvl 10
			cookedItem = 347;
		}
		if (c.cookID == 321) { // anchovie lvl 15
			cookedItem = 319;
		}
		if (c.cookID == 353) { // Mackerel lvl 16
			cookedItem = 355;
		}
		if (c.cookID == 335) { // trout lvl 20
			cookedItem = 333;
		}
		if (c.cookID == 341) { // cod lvl 23
			cookedItem = 339;
		}
		if (c.cookID == 331) { // salmon lvl 30
			cookedItem = 329;
		}
		if (c.cookID == 359) { // tuna lvl 35
			cookedItem = 361;
		}
		if (c.cookID == 377) { // lobster lvl 40
			cookedItem = 379;
		}
		if (c.cookID == 371) { // swordfish lvl 45
			cookedItem = 373;
		}
		if (c.cookID == 363) { // bass lvl 46 big net
			cookedItem = 365;
		}
		if (c.cookID == 383) { // shark lvl 76 harpoon
			cookedItem = 385;
		}
		c.addSkillXP(getCookingEXP(c) * server.SERVER_EXPERIENCE, 7);
		c.deleteItem(c.ITEM1, c.ITEM1AM);
		c.addItem(cookedItem, 1);
		c.CAM().sendMessage("You managed to cook the " + c.getItemName(cookedItem) + ".");
		cookingMain(c, c.PLAYER, c.PLAYER_ID, c.COOKING_LEVEL, c.ITEM1, c.ITEM1AM, c.COOKING_ANIMATION, c.cookID);
		return cookedItem;
	}
	public int calculateCooking(client c) {
		if (!c.playerHasItem(c.cookID)) {
			c.CAM().sendMessage("You have ran out of supplies.");
			c.stop();
			return -1;
		}
		if (c.playerLevel[7] < c.COOKING_LEVEL) {
			c.CAM().sendMessage("You need a cooking level of " +c.COOKING_LEVEL+ " to cook this.");
			c.stop();
			return -1;
		}
		if (!c.playerHasItem(c.ITEM1, c.ITEM1AM)) {
			c.CAM().sendMessage("You need " + c.ITEM1AM + " " + c.getItemName(c.ITEM1) + " to cook here.");
			c.stop();
			return -1;
		}
		c.startAnimation(c.COOKING_ANIMATION);
		switch (c.COOKING_LEVEL - c.playerLevel[7]) {
			case 0:
				if (misc.random(3) == 1) {
					getCookedItem(c); 
				} else if (misc.random(3) == 2) {
					getBurntItem(c);
				} else if (misc.random(3) == 3) {
					getBurntItem(c);
				}
			case 1:
				if (misc.random(3) == 1) {
					getCookedItem(c); 
				} else if (misc.random(3) == 2) {
					getBurntItem(c);
				} else if (misc.random(3) == 3) {
					getBurntItem(c);
				}
			case 2:
				if (misc.random(2) == 1) {
					getCookedItem(c);
				} else if (misc.random(2) == 2) {
					getBurntItem(c);
				}
			case 3:
				if (misc.random(2) == 1) {
					getCookedItem(c);
				} else if (misc.random(2) == 2) {
					getBurntItem(c);
				}
			case 4:
				if (misc.random(2) == 1) {
					getCookedItem(c);
				} else if (misc.random(2) == 2) {
					getBurntItem(c);
				}
			case 5:
				if (misc.random(2) == 1) {
					getCookedItem(c);
				} else if (misc.random(2) == 2) {
					getBurntItem(c);
				}
			case 6:
				if (misc.random(2) == 1) {
					getCookedItem(c);
				} else if (misc.random(2) == 2) {
					getBurntItem(c);
				}
			case 7:
				if (misc.random(2) == 1) {
					getCookedItem(c);
				} else if (misc.random(2) == 2) {
					getBurntItem(c);
				}
			case 8:
				if (misc.random(2) == 1) {
					getCookedItem(c);
				} else if (misc.random(2) == 2) {
					getBurntItem(c);
				}
			case 9:
				if (misc.random(2) == 1) {
					getCookedItem(c);
				} else if (misc.random(2) == 2) {
					getBurntItem(c);
				}
			case 10:
				if (misc.random(3) == 1) {
					getCookedItem(c);
				} else if (misc.random(3) == 2) {
					getCookedItem(c);
				} else if (misc.random(3) == 3) {
					getBurntItem(c);
				}
			case 11:
				if (misc.random(3) == 1) {
					getCookedItem(c);
				} else if (misc.random(3) == 2) {
					getCookedItem(c);
				} else if (misc.random(3) == 3) {
					getBurntItem(c);
				}
			case 12:
				if (misc.random(3) == 1) {
					getCookedItem(c);
				} else if (misc.random(3) == 2) {
					getCookedItem(c);
				} else if (misc.random(3) == 3) {
					getBurntItem(c);
				}
			case 13:
				if (misc.random(3) == 1) {
					getCookedItem(c);
				} else if (misc.random(3) == 2) {
					getCookedItem(c);
				} else if (misc.random(3) == 3) {
					getBurntItem(c);
				}
			case 14:
				if (misc.random(3) == 1) {
					getCookedItem(c);
				} else if (misc.random(3) == 2) {
					getCookedItem(c);
				} else if (misc.random(3) == 3) {
					getBurntItem(c);
				}
			case 15:
				getCookedItem(c);
				
		}
		return getCookedItem(c);
	}
	public int getCookingEXP(client c) { // 30x real rs exp
		if (c.cookID == 317) {
			c.COOKING_EXP = Constants.COOKING_SHRIMPS;
		}
		if (c.cookID == 327) {
			c.COOKING_EXP = Constants.COOKING_SARDINE;
		}
		if (c.cookID == 345) {
			c.COOKING_EXP = Constants.COOKING_HERRING;
		}
		if (c.cookID == 321) {
			c.COOKING_EXP = Constants.COOKING_ANCHOVIE;
		}
		if (c.cookID == 353) {
			c.COOKING_EXP = Constants.COOKING_MACKEREL;
		}
		if (c.cookID == 335) {
			c.COOKING_EXP = Constants.COOKING_TROUT;
		}
		if (c.cookID == 341) {
			c.COOKING_EXP = Constants.COOKING_COD;
		}
		if (c.cookID == 331) {
			c.COOKING_EXP = Constants.COOKING_SALMON;
		}
		if (c.cookID == 359) {
			c.COOKING_EXP = Constants.COOKING_TUNA;
		}
		if (c.cookID == 377) {
			c.COOKING_EXP = Constants.COOKING_LOBSTER;
		}
		if (c.cookID == 371) {
			c.COOKING_EXP = Constants.COOKING_SWORDFISH;
		}
		if (c.cookID == 363) {
			c.COOKING_EXP = Constants.COOKING_BASS;
		}
		if (c.cookID == 383) {
			c.COOKING_EXP = Constants.COOKING_SHARK;
		}
		return c.COOKING_EXP;
	}
	public boolean hasStoppedCooking(client c) {
		if (!c.isBusy) {
			return true;
		}
		return false;
	}

}