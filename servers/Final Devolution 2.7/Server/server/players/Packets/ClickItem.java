package server.players.Packets;

import server.players.*;
import server.players.Quests.*;
import server.players.Skills.*;
import server.world.*;
import server.items.*;
import server.util.*;
import server.npcs.*;
import server.*;

public class ClickItem {

	public long lastBury = 0, eatDelay = 0;
	public client client;
	public boolean GoOn = true;
	
	public ClickItem(client c) {
		c = c;
	}

	public void buryItem(client c, int item, int slot) {
		if (System.currentTimeMillis() - eatDelay < 1750)
		{
			return;
		}
		if (c.playerHasItem(item)) {
			switch (item) {
			case 4597:
				if (c.QH().q2 == 1 || c.QH().q2 == 2) {
					c.QH().q2 = 2;
					c.QH().loadMenus("q2notes", c);
				} else {
					c.CAM().sendMessage("I already read these!");
				}
			break;

			case 6865://blue
				c.startAnimation(3003);
				c.gfx0(511);
			break;
			case 6866://green
				c.startAnimation(3003);
				c.gfx0(515);
			break;
			case 6867://red
				c.startAnimation(3003);
				c.gfx0(507);
			break;
			case 526:
			case 2859:
				if (c.QH().q1 == 4) {
					c.QH().q1 = 5;
				}
				prayerMessage(c, Constants.REG_BONES_1);//40
				c.deleteItem(item, slot, 1);
				break;
			case 528:
			case 530:
				prayerMessage(c, Constants.REG_BONES_2);//120
				c.deleteItem(item, slot, 1);
				break;
			case 532:
				prayerMessage(c, Constants.BIG_BONES);//55
				c.deleteItem(item, slot, 1);
				break;
			case 534:
				prayerMessage(c, Constants.B_DRAGON_BONES);//119
				c.deleteItem(item, slot, 1);
				break;
			case 536:
				prayerMessage(c, Constants.DRAGON_BONES);//148
				c.deleteItem(item, slot, 1);
				break;
			case 3125:
				prayerMessage(c, Constants.JOGRE_BONES_1);//78
				c.deleteItem(item, slot, 1);
				break;
			case 3127:
				prayerMessage(c, Constants.JOGRE_BONES_2);//205
				c.deleteItem(item, slot, 1);
				break;
			case 3129:
				prayerMessage(c, Constants.JOGRE_BONES_3);//215
				c.deleteItem(item, slot, 1);
				break;
			case 3179:
				prayerMessage(c, Constants.MONEY_BONES);//57
				c.deleteItem(item, slot, 1);
				break;
			case 4812:
				prayerMessage(c, Constants.ZORGRE_BONES);//185
				c.deleteItem(item, slot, 1);
				break;
			case 4830:
			case 3123:
				prayerMessage(c, Constants.REG_BONES_3);//190
				c.deleteItem(item, slot, 1);
				break;
			case 6729:
				prayerMessage(c, Constants.DAG_BONES);//199
				c.deleteItem(item, slot, 1);
				break;

			case 2140:
				eatFood(c, 0x33D, 950, Constants.CHICKEN);
				c.deleteItem(item, slot, 1);
				c.CAM().sendMessage("You eat the cooked chicken.");
				break;
			case 315:
				eatFood(c, 0x33D, 750, Constants.SHRIMP);
				c.deleteItem(item, slot, 1);
				c.CAM().sendMessage("You eat the shrimp.");
				break;
			case 361:
				eatFood(c, 0x33D, 750, Constants.TUNA);
				c.deleteItem(item, slot, 1);
				c.CAM().sendMessage("You eat the Tuna.");
				break;
			case 319:
				eatFood(c, 0x33D, 750, Constants.ANCHOVIES);
				c.deleteItem(item, slot, 1);
				c.CAM().sendMessage("You eat the anchovies.");
				break;
			case 325:
				eatFood(c, 0x33D, 750, Constants.SARDINE);
				c.deleteItem(item, slot, 1);
				c.CAM().sendMessage("You eat the sardine.");
				break;
			case 397:
				eatFood(c, 0x33D, 750, Constants.TURTLE);
				c.deleteItem(item, slot, 1);
				c.CAM().sendMessage("You eat the Sea Turtle.");
				break;
			case 2149:
				eatFood(c, 0x33D, 750, Constants.EEL);
				c.deleteItem(item, slot, 1);
				c.CAM().sendMessage("You eat the Lava Eel.");
				break;
			case 351:
				eatFood(c, 0x33D, 750, Constants.PIKE);
				c.deleteItem(item, slot, 1);
				c.CAM().sendMessage("You eat the Pike.");
				break;
			case 365:
				eatFood(c, 0x33D, 750, Constants.BASS);
				c.deleteItem(item, slot, 1);
				c.CAM().sendMessage("You eat the Bass.");
				break;
			case 329:
				eatFood(c, 0x33D, 750, Constants.SALMON);
				c.deleteItem(item, slot, 1);
				c.CAM().sendMessage("You eat the salmon.");
				break;
			case 379:
				eatFood(c, 0x33D, 750, Constants.LOBSTER);
				c.deleteItem(item, slot, 1);
				c.CAM().sendMessage("You eat the lobster.");
				break;
			case 1942:
				eatFood(c, 0x33D, 750, Constants.POTATO);
				c.deleteItem(item, slot, 1);
				c.CAM().sendMessage("You eat the potato.");
				break;
			case 1965:
				eatFood(c, 0x33D, 750, Constants.CABBAGE);
				c.deleteItem(item, slot, 1);
				c.CAM().sendMessage("You eat the cabbage.");
				break;
			case 339:
				eatFood(c, 0x33D, 750, Constants.COD);
				c.deleteItem(item, slot, 1);
				c.CAM().sendMessage("You eat the cod!");
				break;
			case 385:
				eatFood(c, 0x33D, 750, Constants.SHARK);
				c.deleteItem(item, slot, 1);
				c.CAM().sendMessage("You eat the shark!");
				break;
			case 2309:
				eatFood(c, 0x33D, 750, Constants.CRISPY_BREAD);
				c.deleteItem(item, slot, 1);
				c.CAM().sendMessage("You eat the crispy bread.");
				break;
			case 373:
				eatFood(c, 0x33D, 750, Constants.SWORD_FISH);
				c.deleteItem(item, slot, 1);
				c.CAM().sendMessage("You eat the sword fish.");
				break;
			case 2108:
				eatFood(c, 0x33D, 750, Constants.ORANGE);
				c.deleteItem(item, slot, 1);
				c.CAM().sendMessage("You eat the orange.");
				break;
			case 391:
				eatFood(c, 0x33D, 750, Constants.MANTA_RAY);
				c.deleteItem(item, slot, 1);
				c.CAM().sendMessage("You eat the manta ray.");
				break;
			case 1897:
				eatFood(c, 0x33D, 750, Constants.CHOCOLATE_CAKE_FULL);
				c.deleteItem(item, slot, 1);
				c.addItem(1899, 1);
				c.CAM().sendMessage("You eat 1/3 of the chocolate cake");
				break;
			case 1899:
				eatFood(c, 0x33D, 750, Constants.CHOCOLATE_CAKE_2_SLICE);
				c.deleteItem(item, slot, 1);
				c.addItem(1901, 1);
				c.CAM().sendMessage("You eat 1/3 of the chocolate cake");
				break;
			case 1901:
				eatFood(c, 0x33D, 750, Constants.CHOCOLATE_CAKE_SLICE);
				c.deleteItem(item, slot, 1);
				c.CAM().sendMessage("You eat a slice of the chocolate cake");
				break;
			case 1891:
				eatFood(c, 0x33D, 750, Constants.CAKE_FULL);
				c.deleteItem(item, slot, 1);
				c.addItem(1893, 1);
				c.CAM().sendMessage("You eat 1/3 of the cake");
				break;
			case 1893:
				eatFood(c, 0x33D, 750, Constants.CAKE_2_SLICE);
				c.deleteItem(item, slot, 1);
				c.addItem(1895, 1);
				c.CAM().sendMessage("You eat 1/3 of the cake");
				break;
			case 1895:
				eatFood(c, 0x33D, 750, Constants.CAKE_SLICE);
				c.deleteItem(item, slot, 1);
				c.CAM().sendMessage("You eat a slice of the cake");
				break;
			case 952: //spade
				//used for barrows
				if(c.absX >= 3497 && c.absX <= 3498 && c.absY >= 3183 && c.absY <= 3184 && c.QH().q2 == 2) {
					c.CAM().sendMessage("Wow, I found a chest here!");
					c.CAM().sendMessage("No monster. Davey is a lier, im going to open the chest.");
					c.QH().q2 = 3;
					c.addItem(405, 1);	
				}
			break;
			case 405:
				if (c.QH().q2 == 3) {
					server.npcHandler.spawnANPC(1610, c.absX+1, c.absY, 0, false);
				}
			break;
			
			case 199:
				c.getHerb().HerbIndentify(c, "Herb", 1, 199, 249, 30);
			break;
			case 203:
				c.getHerb().HerbIndentify(c, "Herb", 15, 203, 251, 40);
			break;
			case 205:
				c.getHerb().HerbIndentify(c, "Herb", 20, 205, 253, 50);
			break;
			case 207:
				c.getHerb().HerbIndentify(c, "Herb", 30, 207, 255, 80);
			break;
			case 209:
				c.getHerb().HerbIndentify(c, "Herb", 60, 209, 257, 105);
			break;
			case 211:
				c.getHerb().HerbIndentify(c, "Herb", 70, 211, 2998, 180);
			break;
			case 213:
				c.getHerb().HerbIndentify(c, "Herb", 90, 213, 3000, 250);
			break;

			case 2442://def4
				c.getHerb().DrinkPot(c, c.playerDefence, 163, 2442);
			break;
			case 163://def3
				c.getHerb().DrinkPot(c, c.playerDefence, 165, 163);
			break;
			case 165://def2
				c.getHerb().DrinkPot(c, c.playerDefence, 167, 165);
			break;
			case 167://def1
				c.getHerb().DrinkPot(c, c.playerDefence, 229, 167);
			break;

			case 2434://Prayer4
				c.getHerb().DrinkPot(c, c.playerPrayer, 139, 2434);
			break;
			case 139://Prayer3
				c.getHerb().DrinkPot(c, c.playerPrayer, 141, 139);
			break;
			case 141://Prayer2
				c.getHerb().DrinkPot(c, c.playerPrayer, 143, 141);
			break;
			case 143://Prayer1
				c.getHerb().DrinkPot(c, c.playerPrayer, 229, 143);
			break;
			
			default:
				break;
			}
		}			
	}
	public void prayerMessage(client c, int exp) {
		if (System.currentTimeMillis() - lastBury > 1500) {
			lastBury = System.currentTimeMillis();
			c.setAnimation(827);
			c.addSkillXP(exp * server.SERVER_EXPERIENCE, 5);
			c.CAM().sendMessage("You bury the bones.");
		}
		return;
	}
	public void eatFood(client c, int anim, int rt, int nh) { //Created by Rog3r
		if (c.currentHealth <= 0) {
			return;
		}
		c.castEmote(anim, rt);
		c.currentHealth += nh;
		if (c.currentHealth > c.playerLevel[c.playerHitpoints])
		c.currentHealth = c.playerLevel[c.playerHitpoints];
		c.refreshhps();
		eatDelay = System.currentTimeMillis();
		
	}
}
