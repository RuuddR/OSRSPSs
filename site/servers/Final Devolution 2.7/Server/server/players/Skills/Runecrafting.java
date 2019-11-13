package server.players.Skills;

import server.players.client;
import server.util.misc;
import server.*;
import server.util.*;
import server.players.*;
public class Runecrafting {



	public client client;


	public Runecrafting(client c) {
	client = c;
	}

	public void runecraft(client c, int level, int experience, int rune, int a2,
				int a3, int a4, int a5, int a6, int a7,	int a8,
					int a9, int a10)
		{
			int essence = c.amountOfItem(7936);
			if (c.playerLevel[c.playerRunecrafting] < level)
			{
				c.CAM().sendMessage("You do not have enough runes to craft this.");
				c.CAM().sendMessage("You need at least "+level+" to runecraft this.");
				return;
			}
			if (!c.playerHasItem(7936))
			{
				c.CAM().sendMessage("You do not have any pure essence to craft.");
				return;
			}
			if (c.playerLevel[c.playerRunecrafting] >= level)
			{
				if (c.playerLevel[c.playerRunecrafting] >= a2 && c.playerLevel[c.playerRunecrafting] < a3)
					essence = c.amountOfItem(7936) * 2;
				if (c.playerLevel[c.playerRunecrafting] >= a3 && c.playerLevel[c.playerRunecrafting] < a4)
					essence = c.amountOfItem(7936) * 3;
				if (c.playerLevel[c.playerRunecrafting] >= a4 && c.playerLevel[c.playerRunecrafting] < a5)
					essence = c.amountOfItem(7936) * 4;
				if (c.playerLevel[c.playerRunecrafting] >= a5 && c.playerLevel[c.playerRunecrafting] < a6)
					essence = c.amountOfItem(7936) * 5;
				if (c.playerLevel[c.playerRunecrafting] >= a6 && c.playerLevel[c.playerRunecrafting] < a7)
					essence = c.amountOfItem(7936) * 6;
				if (c.playerLevel[c.playerRunecrafting] >= a7 && c.playerLevel[c.playerRunecrafting] < a8)
					essence = c.amountOfItem(7936) * 7;
				if (c.playerLevel[c.playerRunecrafting] >= a8 && c.playerLevel[c.playerRunecrafting] < a9)
					essence = c.amountOfItem(7936) * 8;
				if (c.playerLevel[c.playerRunecrafting] >= a9 && c.playerLevel[c.playerRunecrafting] < a10)
					essence = c.amountOfItem(7936) * 9;
				if (c.playerLevel[c.playerRunecrafting] >= a10)
					essence = c.amountOfItem(7936) * 10;

			}
			for (int i = 0; i < 30; i++) {
			c.deleteItem2(7936, 30);
			c.addItem(rune, essence);
			c.addSkillXP(experience*essence * server.SERVER_EXPERIENCE, c.playerRunecrafting);
			c.CAM().sendMessage("You bind the temple's power into "+c.getItemName(rune)+".");
			c.startAnimation(791);
			c.proGFX(186);
			return;
			}
		}
	}