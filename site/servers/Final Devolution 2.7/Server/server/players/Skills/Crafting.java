package server.players.Skills;

import server.players.client;
import server.util.misc;
import server.*;
import server.util.*;
import server.players.*;

public class Crafting {


	public client client;
	public int cLevel = 1, cItem = -1, cAmount = 0, cSelected = -1, cExp = 0, cIndex = -1;
	public final int[] chestExp = { 186, 210, 234, 258 };
	public final int[] chestLevels = { 63, 71, 77, 82 };
	public final int[] chests = { 1135, 2499, 2501, 2503 };
	public final int[] gloveExp = { 62, 70, 78, 86 };
	public final int[] gloveLevels = { 57, 66, 73, 79 };
	public final int[] gloves = { 1065, 2487, 2489, 2491 };
	public final int[] hides = { 1753, 1751, 1749, 1747 };
	public final int[] leathers = { 1745, 2505, 2507, 2509 };
	public final int[] legExp = { 124, 140, 156, 172 };
	public final int[] legLevels = { 60, 68, 75, 82 };
	public final int[] legs = { 1099, 2493, 2495, 2497 };
	public boolean crafting = false;
	public boolean spinning = false;

	public Crafting(client c)
	{
		client = c;
	}

	public void craft(client c) {
		if (c.playerLevel[c.playerCrafting] < cLevel) {
			c.CAM().sendMessage("You need " + cLevel + " crafting to make a " + c.getItemName(cItem));
			crafting = false;
			return;
		}
		c.startAnimation(885);
		c.updateRequired = true;
		c.appearanceUpdateRequired = true;
		if (c.playerHasItem(cSelected, 1) && c.playerHasItem(1734) && (cAmount > 0)) {
			c.deleteItem(cSelected, 1);
			c.deleteItem(1734, 1);
			c.CAM().sendMessage("You make some " + c.getItemName(cItem));
			c.addItem(cItem, 1);
			c.addSkillXP(cExp * server.SERVER_EXPERIENCE, c.playerCrafting);
			cAmount--;
			if (cAmount < 1)
				crafting = false;
		} else {
			crafting = false;
		}
	}

	public void startTan(client c, int amount, int type) {
		int done = 0;
		int[] hide = { 1739, 1739, 1753, 1751, 1749, 1747 };
		int[] leather = { 1741, 1741, 1745, 2505, 2507, 2509 };
		int[] charge = { 50, 100, 1000, 2000, 5000, 10000 };
		while ((done < amount) && c.playerHasItem(hide[type])
				&& c.playerHasItem(995, charge[type])) {
			c.deleteItem2(hide[type], 1);
			c.deleteItem2(995, charge[type]);
			c.addItem(leather[type], 1);
			done++;
		}
		int total = done * charge[type];
	}

	public void craftMenu(client c, int i) {
		c.CAM().sendFrame126("What would you like to make?", 8898);
		c.CAM().sendFrame126("Vambraces", 8889);
		c.CAM().sendFrame126("Chaps", 8893);
		c.CAM().sendFrame126("Body", 8897);
		c.CAM().sendFrame246(8883, 250, gloves[i]);
		c.CAM().sendFrame246(8884, 250, legs[i]);
		c.CAM().sendFrame246(8885, 250, chests[i]);
		c.CAM().sendFrame164(8880);
	}

	public void startCraft(client c, int actionbutton) {
		c.closeInterface();
		int[] buttons = { 33187, 33186, 33185, 33190, 33189, 33188, 33193,
				33192, 33191, 33196, 33195, 33194, 33199, 33198, 33197, 33202,
				33201, 33200, 33205, 33204, 33203 };
		int[] amounts = { 1, 5, 10, 1, 5, 10, 1, 5, 10, 1, 5, 10, 1, 5, 10, 1,
				5, 10, 1, 5, 10 };
		int[] ids = { 1129, 1129, 1129, 1059, 1059, 1059, 1061, 1061, 1061,
				1063, 1063, 1063, 1095, 1095, 1095, 1169, 1169, 1169, 1167,
				1167, 1167 };
		int[] levels = { 14, 1, 7, 11, 18, 38, 9 };
		int[] exp = { 27, 14, 16, 22, 27, 37, 19 };
		int amount = 0, id = -1;
		int index = 0;
		for (int i = 0; i < buttons.length; i++) {
			if (actionbutton == buttons[i]) {
				amount = amounts[i];
				id = ids[i];
				index = i % 3;
			}
		}
		if (c.playerLevel[c.playerCrafting] >= levels[index]) {
			crafting = true;
			cItem = id;
			cAmount = amount;
			cLevel = levels[index];
			cExp = Math.round(exp[index] * 9);
			cSelected = 1741;
		} else {
			c.CAM().sendMessage("Requires level " + levels[index]);
		}
	}
	public void openTan(client c) {
		c.CAM().sendFrame126("Regular Leather", 14777);
		c.CAM().sendFrame126("50gp", 14785);
		c.CAM().sendFrame126("Hard Leather", 14781);
		c.CAM().sendFrame126("100gp", 14789);
		c.CAM().sendFrame126("", 14778);
		c.CAM().sendFrame126("", 14786);
		c.CAM().sendFrame126("", 14782);
		c.CAM().sendFrame126("", 14790);
		int[] soon = { 14779, 14787, 14783, 14791, 14780, 14788, 14784, 14792 };
		String[] dhide = { "Green", "Red", "Blue", "Black" };
		String[] cost = { "1,000gp", "5,000gp", "2,000gp", "10,000gp" };
		int type = 0, i2 = 0;
		for (int i = 0; i < soon.length; i++) {
			if (type == 0) {
				c.CAM().sendFrame126(dhide[(int) (i / 2)], soon[i]);
				type = 1;
			} else {
				c.CAM().sendFrame126(cost[(int) (i / 2)], soon[i]);
				type = 0;
			}
		}
		c.CAM().sendFrame246(14769, 250, 1741);
		c.CAM().sendFrame246(14773, 250, 1743);
		c.CAM().sendFrame246(14771, 250, 1753);
		c.CAM().sendFrame246(14772, 250, 1751);
		c.CAM().sendFrame246(14775, 250, 1749);
		c.CAM().sendFrame246(14776, 250, 1747);
		c.CAM().showInterface(14670);

	}
	public void startHideCraft(client c, int b) {
		int[] buttons = { 34185, 34184, 34183, 34182, 34189, 34188, 34187,
				34186, 34193, 34192, 34191, 34190 };
		int[] amounts = { 1, 5, 10, 27 };
		int index = 0;
		int index2 = 0;
		for (int i = 0; i < buttons.length; i++) {
			if (buttons[i] == b) {
				index = i % 4;
				index2 = (int) (i / 4);
				break;
			}
		}
		cAmount = amounts[index];
		cSelected = leathers[cIndex];
		int required = 50;
		if (index2 == 0) {
			required = gloveLevels[cIndex];
			cItem = gloves[cIndex];
			cExp = gloveExp[cIndex];
		} else if (index2 == 1) {
			required = legLevels[cIndex];
			cItem = legs[cIndex];
			cExp = legExp[cIndex];
		} else {
			required = chestLevels[cIndex];
			cItem = chests[cIndex];
			cExp = chestExp[cIndex];
		}
		if (c.playerLevel[c.playerCrafting] >= required) {
			cExp = (int) (cExp * 8);
			c.closeInterface();
			crafting = true;
		} else {
			c.CAM().sendMessage("This Item Requires level " + required);
		}
	}

	public void spin(client c) {
		if (c.playerHasItem(1779)) {
			c.deleteItem(1779, 1);
			c.addItem(1777, 1);
			c.lastAction = System.currentTimeMillis();
			c.addSkillXP(30 * server.SERVER_EXPERIENCE, c.playerCrafting);
		} else {
			crafting = false;
		}
	}

}