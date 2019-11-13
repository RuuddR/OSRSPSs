package server.players.Skills;

import server.players.client;
import server.util.misc;

import server.*;
import server.util.*;
import server.players.*;


public class Mining implements Skill
{

	public static int skillX = 0;
	public static int skillY = 0;
	public int toadd = -1;
	public int replace = 452;
	public final static int VALIDAXE[] = { 1265, 1267, 1269, 1271, 1273, 1275 };
	public final static int UNCUTS[] = { 1617, 1619, 1621, 1623, 1625, 1627, 1629, 1631, 6571 };
	public int xp = 0;
	public static int item = -1;
	private client c;

	public Mining(client c, int object, String type, int x, int y)
	{
		this.c = c;
		readInput(this.c.playerLevel[14], type, this.c, x, y, object);
	}
	
	public Mining(client c) 
	{
	this.c = c;
	}
	

	public void readInput(int level, String type, client c, int objectx, int objecty, int objectid)
	{
		if (c.getStatus().isBusy()) {
			return;
		}
		synchronized(this)
		{
		if (type.equals("TIN") && level >= 1)
		{
			xp = 18;
			item = 438;
			this.c.canmine = true;
		}
		
		else if (type.equals("COPPER") && level >= 1)
		{
			xp = 18;
			item = 436;
			this.c.canmine = true;
		}
		
		else if (type.equals("BLUR") && level >= 10)
		{
			xp = 18;
			item = 668;
			this.c.canmine = true;
		}
		
		else if (type.equals("ELEMENTAL") && level >= 20)
		{
			xp = 0;
			item = 2892;
			this.c.canmine = true;
		}
		
		else if (type.equals("GRANITE") && level >= 45)
		{
			int i = misc.random(3);
			if (i == 1)
			{
				xp = 50;
				item = 6979;
				this.c.canmine = true;
			}
			
			else if (i == 2)
			{
				xp = 60;
				item = 6981;
				this.c.canmine = true;
			}
			
			else if (i == 3)
			{
				xp = 75;
				item = 6983;
				this.c.canmine = true;
			}
		}
		
		else if (type.equals("CLAY") && level >= 1)
		{
			xp = 5;
			item = 434;
			this.c.canmine = true;
		}
		
		else if (type.equals("ESS") && level >= 1)
		{
			if (level >= 30)
			{
			xp = 5;
			item = 1436; 
			}
			else
			{
			xp = 5;
			item = 7936;
			}
		}
		
		else if (type.equals("IRON") && level >= 15)
		{
			xp = 35;
			item = 440;
			this.c.canmine = true;
		}
		
		else if (type.equals("SILVER") && level >= 20)
		{
			xp = 40;
			item = 442;
			this.c.canmine = true;
		}
		
		else if (type.equals("COAL") && level >= 30)
		{
			xp = 50;
			item = 453;
			this.c.canmine = true;
		}
		
		else if (type.equals("GOLD") && level >= 40)
		{
			xp = 65;
			item = 444;
			this.c.canmine = true;
		}
		
		else if (type.equals("MITH") && level >= 55)
		{
			xp = 80;
			item = 447;
			this.c.canmine = true;
		}
		
		else if (type.equals("ADDY") && level >= 70)
		{
			xp = 95;
			item = 449;
			this.c.canmine = true;
		}
		
		else if (type.equals("RUNE") && level >= 85)
		{
			xp = 125;
			item = 451;
			this.c.canmine = true;
		}
		
		else if (type.equals("LIME") && level >= 10)
		{
			xp = 27;
			item = 3211;
			this.c.canmine = true;
		}
		
		else
		{
			this.c.CAM().sendMessage("You need a higher mining level to extract ore from this rock!");
			xp = 0;
			item = -1;
			this.c.canmine = false;
		}
	}
			for (int id : VALIDAXE)
			{
				//System.out.println(id);
				//System.out.println(c.playerEquipment[c.playerWeapon]);
			if(id == c.playerEquipment[c.playerWeapon] && c.canmine && canminewithpick(c.playerEquipment[c.playerWeapon], c) || c.playerHasItem(id, 1) && c.canmine && canminewithpick(id, c))
				{
				//System.out.println("Called");
				switch (id)
				{
				case 1265:
					c.mineanim = 625;
					c.intervaldeduction = 0;
				break;
				case 1267:
					c.mineanim = 626;
					c.intervaldeduction = 1;
					break;
				case 1269:
					c.mineanim = 627;
					c.intervaldeduction = 2;
					break;
				case 1271:
					c.mineanim = 628;
					c.intervaldeduction = 3;
					break;
				case 1273:
					c.mineanim = 629;
					c.intervaldeduction = 4;
					break;
				case 1275:
					c.mineanim = 624;
					c.intervaldeduction = 5;
					break;
				}
				try
				{
				this.c.CAM().sendMessage("You swing your pick at the rock.");
				c.getStatus().setBusy();
				c.TurnPlayerTo(objectx, objecty);
				this.c.minetime = misc.random(20 - c.intervaldeduction);
				if (!type.equals("ESS"))
				{
				server.worldObject.registerObject(1, objectx, objecty, objectid, replace, 30, 10, 0, objectid);
				}
				this.c.mn.doaction(c, item, -1, -1, objectx, objecty, objectid, xp);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				break;
				}
			else if (id == c.playerEquipment[c.playerWeapon] && c.canmine && !canminewithpick(c.playerEquipment[c.playerWeapon], c) || c.playerHasItem(id, 1) && c.canmine && !canminewithpick(id, c))
			{
				c.CAM().sendMessage("You need a higher mining level to use this pickaxe!");
				c.canmine = false;
				c.ismining = false;
				c.getStatus().setNotBusy();
			}
		}
	}

	private boolean canminewithpick(int i, client c) {
		switch (i)
		{
		case 1265:
		case 1267:
			if (c.playerLevel[14] >= 1)
				return true;
			break;
		case 1269:
			if (c.playerLevel[14] >= 6)
				return true;
			break;
		case 1273:
			if (c.playerLevel[14] >= 21)
				return true;
			break;
		case 1271:
			if (c.playerLevel[14] >= 31)
				return true;
			break;
		case 1275:
			if (c.playerLevel[14] >= 41)
				return true;
		break;
		default:
			return false;
			
		}
		return false;
	}
	public void startEssMine(int object) {
			for (int id : VALIDAXE) {
				switch (id) {
				case 1265:
					c.mineanim = 625;
					c.intervaldeduction = 0;
				break;
				case 1267:
					c.mineanim = 626;
					c.intervaldeduction = 1;
					break;
				case 1269:
					c.mineanim = 627;
					c.intervaldeduction = 2;
					break;
				case 1271:
					c.mineanim = 628;
					c.intervaldeduction = 3;
					break;
				case 1273:
					c.mineanim = 629;
					c.intervaldeduction = 4;
					break;
				case 1275:
					c.mineanim = 624;
					c.intervaldeduction = 5;
					break;
			} try {
			this.c.minetime = misc.random(20 - c.intervaldeduction);
		if(System.currentTimeMillis() - c.lastAction >= 1 && c.playerHasItem(id, 1) || id == c.playerEquipment[c.playerWeapon])
			c.essMine = true;
			this.c.CAM().sendMessage("You swing your pick at the rock.");
			if (c.freeSlots() < 0) {
			c.CAM().sendMessage("Not enough space.");
			c.essMine = true;
			c.getStatus().setNotBusy();
			return;
			}
				
				} catch(Exception e) {
					e.printStackTrace();
				}
			if (!c.playerHasItem(id, 1) || id == c.playerEquipment[c.playerWeapon] ) {
			this.c.CAM().sendMessage("You need a pick axe!");
			c.getStatus().setNotBusy();
			c.essMine = false;
		}
				break;
				}
	}

	public void out(String s, client c)
	{
	
	}


	public boolean doaction(client c, int toadd, int toremove, int toremove2, int x, int y, int objectid, int xp) {
	if (true) {
		if (c.freeSlots() > 0) {
		this.toadd = toadd;
		this.c.ismining = true;
		this.xp = xp;
		if (c.minetime <= 0) {
			if (misc.random(80) == 1 || misc.random(30) == 1 && toadd == 453)
			{
				this.c.addItem(UNCUTS[misc.random(8)], 1);
				this.c.CAM().sendMessage("You find a gem in the rock!");
			}
		server.worldObject.reduceHealth(x, y);
		this.c.CAM().sendMessage("You get some ore");
		this.c.minetime = misc.random(20 - c.intervaldeduction);
		this.c.addItem(toadd, 1);
		this.c.addSkillXP(xp * server.SERVER_EXPERIENCE, 14);
		}
	} else {
		this.c.CAM().sendMessage("You do not have enough room in your inventory.");
		this.c.canmine = false;
		this.c.ismining = false;
		this.c.minetime = 0;
		c.getStatus().setNotBusy();
		}
	}
	return true;
	}

	
	
}