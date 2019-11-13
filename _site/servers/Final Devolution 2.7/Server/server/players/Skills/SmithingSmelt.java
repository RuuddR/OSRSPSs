package server.players.Skills;

import server.players.client;
import server.util.misc;
import server.*;
import server.util.*;
import server.players.*;
public class SmithingSmelt implements Skill {
	
	public client client;
	public int xp = 0;
	public static int item = -1;
	public int remove = -1;
	public int remove2 = -1;
	public int removeam = -1;
	public int removeam2 = -1;

	public SmithingSmelt(client c, int NOTUSED2, int timestosmelt, int NOTUSED, int itemid) 
	{
		client = c;
		readInput(c.playerLevel[13], itemidforstring(itemid, c), c, timestosmelt, -1, -1);
	}
	
	private String itemidforstring(int itemid, client c) {
		if (itemid == 438 || itemid == 436)
			return "BRONZE";
		else if (itemid == 442)
			return "SILVER";
		else if (itemid == 440 && !c.playerHasItem(453, 2))
			return "IRON";
		else if (itemid == 444)
			return "GOLD";
		else if (itemid == 447)
			return "MITH";
		else if (itemid == 449)
			return "ADDY";
		else if (itemid == 451)
			return "RUNE";
		else if (itemid == 440 && c.playerHasItem(453, 2))
			return "STEEL";
		else if (itemid == 668)
			return "BLUR";
		else if (itemid == 2892)
			return "ELEMENTAL";
		else if (itemid == 453)
			return "COAL";
		return "UNKOWN";
	}

	public SmithingSmelt(client c)
	{
		client = c;
	}

	@Override
	public boolean doaction(client c, int toadd, int toremove, int toremove2,
			int timestosmelt, int removeam, int removeam2, int xp) {
		int times = timestosmelt;
		boolean hasfailed = false;
	if (c.playerHasItem(toremove, removeam) && c.playerHasItem(toremove2, removeam2))
	{
		while (times > 0 && c.playerHasItem(toremove, removeam) && c.playerHasItem(toremove2, removeam2))
		{
		if (toremove == 447)
		{
			c.deleteItem(447, c.getItemSlot(447), 1);
			c.deleteItem(453, 4);
		}
		else if (toremove == 440 && misc.random(2) == 1)
		{
				c.deleteItem(440, c.getItemSlot(440), 1);
				c.CAM().sendMessage("You fail to smelt the iron ore into a bar.");
				c.startAnimation(899);
				hasfailed = true;
		}
		else if (toremove == 449)
		{
			c.deleteItem(449, c.getItemSlot(449), 1);
			c.deleteItem(453, 6);
		}
		else if (toremove == 451)
		{
			c.deleteItem(451, c.getItemSlot(451), 1);
			c.deleteItem(453, 8);
		}
		else
		{
			c.deleteItem(toremove, c.getItemSlot(toremove), 1);
			if (toremove2 != -1)
			{
				c.deleteItem(toremove2, c.getItemSlot(toremove2), 1);
			}
		}
		if (!hasfailed)
		{
		c.startAnimation(899);
		c.CAM().sendMessage("You smelt the ore into a bar.");
		c.addItem(toadd, 1);
		c.addSkillXP(xp * server.SERVER_EXPERIENCE, 13);
		}
		hasfailed = false;
		times--;
		}
	}
	else
	{
		c.CAM().sendMessage("You don't have enough ore to smelt this bar!");
		return false;
	}
		return true;
	}

	@Override
	public void out(String s, client c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void readInput(int level, String type, client c, int objectx,
			int objecty, int objectid) 
	{
		synchronized (this)
		{
			c.TurnPlayerTo(objectx + 1, objecty);
			if (type.equals("BRONZE") && level >= 1)
			{
				if (c.playerHasItem(438, 1) && c.playerHasItem(436, 1))
				{
				xp = 6;
				item = 2349;
				remove = 438;
				remove2 = 436;
				removeam = 1;
				removeam2 = 1;
				}
				else if (!c.playerHasItem(438, 1))
				{
					c.closeInterface();
					c.CAM().sendMessage("You don't have enough tin to smelt this bar!");
					return;
				}
				
				else
				{
					c.closeInterface();
					c.CAM().sendMessage("You don't have enough copper to smelt this bar!");
					return;
				}
			}
			
			else if (type.equals("STEEL") && level >= 30)
			{
				xp = 18;
				item = 2353;
				remove = 440;
				removeam = 1;
				remove2 = 453;
				removeam2 = 2;
			}
			
			else if (type.equals("IRON") && level >= 15)
			{
				xp = 13;
				item = 2351;
				remove = 440;
				removeam = 1;
			}
			
			else if (type.equals("BLUR") && level >= 8)
			{
				xp = 8;
				item = -1;
				remove = 668;
				removeam = 1;
			}
			
			else if (type.equals("SILVER") && level >= 20)
			{
				xp = 14;
				item = 2355;
				remove = 442;
				removeam = 1;
			}
			
			else if (type.equals("GOLD") && level >= 40)
			{
				xp = 23;
				item = 2357;
				remove = 444;
				removeam = 1;
			}
			else if (type.equals("MITH") && level >= 50)
			{
				if (c.playerHasItem(453, 4))
				{
				xp = 30;
				item = 2359;
				remove = 447;
				removeam = 1;
				remove2 = 453;
				removeam2 = 4;
				}
				else
				{
					c.closeInterface();
					c.CAM().sendMessage("You don't have enough coal to smelt this bar!");
					return;
				}
			}
			else if (type.equals("COAL") && level >= 30)
			{
				c.closeInterface();
				c.CAM().sendMessage("You can't smith coal by itself! Try using the other ore on this object instead.");
				return;
			}
			else if (type.equals("ADDY") && level >= 70)
			{
				if (c.playerHasItem(453, 6))
				{
				xp = 38;
				item = 2361;
				remove = 449;
				remove2 = 453;
				removeam = 1;
				removeam2 = 6;
				}
				else
				{
					c.closeInterface();
					c.CAM().sendMessage("You don't have enough coal to smelt this bar!");
					return;
				}
			}
			
			else if (type.equals("RUNE") && level >= 85)
			{
				if (c.playerHasItem(453, 8))
				{
				xp = 50;
				item = 2363;
				remove = 451;
				removeam = 1;
				remove2 = 453;
				removeam2 = 8;
				}
				else
				{
					c.closeInterface();
					c.CAM().sendMessage("You don't have enough coal to smelt this bar!");
					return;
				}
			}
			
			else if (type.equals("UNKOWN"))
			{
				c.CAM().sendMessage("Unknown id specified.");
				return;
			}
			
			else
			{
				c.CAM().sendMessage("You don't have a high enough smithing level to smelt this bar!");
				c.closeInterface();
				return;
			}
			c.closeInterface();
			c.smith.doaction(c, item, remove, remove2, objectx, removeam, removeam2, xp);
			
		}
		
		
	}

}
