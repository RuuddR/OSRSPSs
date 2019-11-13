package server.players.Skills;

import server.*;
import server.util.misc;
import server.players.*;


public class Woodcutting implements Skill
{
	public static int skillX = 0;
	public static int skillY = 0;
	public static int toadd = -1;
	public int replace = 1342;
	public final static int VALIDAXE[] = { 1351, 1349, 1353, 1361, 1355, 1357, 1359, 6739 };
	public static int xp = 0;
	public static int item = -1;
	private client c;

	public Woodcutting(client c, int object, String type, int x, int y)
	{
		this.c = c;
		readInput(this.c.playerLevel[8], type, this.c, x, y, object);
	}
	
	public Woodcutting(client c) 
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
		if (type.equals("NM") && level >= 1)
		{
			xp = 25;
			item = 1511;
			this.c.cancut = true;
		}
		
		else if (type.equals("OAK") && level >= 15)
		{
			xp = 37;
			item = 1521;
			this.c.cancut = true;
		}
		
		else if (type.equals("WILL") && level >= 30)
		{
			xp = 67;
			item = 1519;
			this.c.cancut = true;
		}
		
		else if (type.equals("MAP") && level >= 45)
		{
			xp = 100;
			item = 1517;
			this.c.cancut = true;
		}
		
		else if (type.equals("YEW") && level >= 60)
		{
			xp = 175;
			item = 1515;
			this.c.cancut = true;
		}
		
		else if (type.equals("MAGE") && level >= 75)
		{
			xp = 250;
			item = 1513;
			this.c.cancut = true;
		}
		
		else
		{
			c.getStatus().setNotBusy();
			this.c.CAM().sendMessage("You need a higher woodcutting level to cut this tree!");
			xp = 0;
			item = -1;
			this.c.cancut = false;
		}
		}
			for (int id : VALIDAXE) {
			if(id == c.playerEquipment[c.playerWeapon] && c.cancut && canwithaxe(c.playerEquipment[c.playerWeapon], c) || c.playerHasItem(id, 1) && c.cancut && canwithaxe(id, c)) {
				//System.out.println("Called");
				switch (id) {
		case 1351:
					c.wcanim = 879;
					c.intervaldeduction = 0;
					break;
		case 1349:
					c.wcanim = 877;
					c.intervaldeduction = 1;
					break;
		case 1353:
					c.wcanim = 875;
					c.intervaldeduction = 2;
					break;
		case 1361:
					c.wcanim = 873;
					c.intervaldeduction = 3;
					break;
		case 1355:
					c.wcanim = 871;
					c.intervaldeduction = 4;
					break;
		case 1357:
					c.wcanim = 869;
					c.intervaldeduction = 5;
					break;
		case 1359:
					c.wcanim = 867;
					c.intervaldeduction = 6;
					break;
		case 6739:
					c.wcanim = 2846;
					c.intervaldeduction = 7;
					break;
				} try {
				c.getStatus().setBusy();
				this.c.CAM().sendMessage("You swing your axe at the tree");
				c.TurnPlayerTo(objectx, objecty);
				this.c.wctime = misc.random(20 - c.intervaldeduction);
if (type.equals("NM") && !type.equals("OAK") && !type.equals("WILL") && !type.equals("MAP") && !type.equals("YEW") && !type.equals("MAGE")) {
				server.worldObject.registerObject(1, objectx, objecty, objectid, replace, 5, 10, 0, objectid);
}
if (type.equals("OAK") && !type.equals("NM") && !type.equals("WILL") && !type.equals("MAP") && !type.equals("YEW") && !type.equals("MAGE")) {
				server.worldObject.registerObject(1+misc.random(10), objectx, objecty, objectid, replace, 10, 10, 0, objectid);
}
if (type.equals("WILL") && !type.equals("OAK") && !type.equals("NM") && !type.equals("MAP") && !type.equals("YEW") && !type.equals("MAGE")) {
				server.worldObject.registerObject(1+misc.random(10), objectx, objecty, objectid, replace, 20, 10, 0, objectid);
}
if (type.equals("MAP") && !type.equals("OAK") && !type.equals("NM") && !type.equals("WILL") && !type.equals("YEW") && !type.equals("MAGE")) {
				server.worldObject.registerObject(1+misc.random(10), objectx, objecty, objectid, replace, 30, 10, 0, objectid);
}
if (type.equals("YEW") && !type.equals("OAK") && !type.equals("NM") && !type.equals("WILL") && !type.equals("MAP") && !type.equals("MAGE")) {
				server.worldObject.registerObject(1+misc.random(10), objectx, objecty, objectid, replace, 70, 10, 0, objectid);
}
if (type.equals("MAGE") && !type.equals("OAK") && !type.equals("NM") && !type.equals("WILL") && !type.equals("MAP") && !type.equals("YEW")) {
				server.worldObject.registerObject(1+misc.random(10), objectx, objecty, objectid, replace, 120, 10, 0, objectid);
}
				this.c.wc.doaction(c, item, -1, -1, objectx, objecty, objectid, xp);
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				break;
				}
			else if (id == c.playerEquipment[c.playerWeapon] && c.cancut && !canwithaxe(c.playerEquipment[c.playerWeapon], c) || c.playerHasItem(id, 1) && c.cancut && !canwithaxe(id, c))
			{
				c.getStatus().setNotBusy();
				c.CAM().sendMessage("You need a higher woodcutting level to use this woodcutting axe!");
				c.cancut = false;
				c.iscutting = false;
			}
			}
			}

	private boolean canwithaxe(int i, client c) {
		switch (i) {
		case 1351:
		case 1349:
			if (c.playerLevel[8] >= 1)
				return true;
			break;
		case 1353:
			if (c.playerLevel[8] >= 6)
				return true;
			break;
		case 1361:
			if (c.playerLevel[8] >= 10)
				return true;
			break;
		case 1355:
			if (c.playerLevel[8] >= 21)
				return true;
			break;
		case 1357:
			if (c.playerLevel[8] >= 31)
				return true;
			break;
		case 1359:
			if (c.playerLevel[8] >= 41)
				return true;
		case 6739:
			if (c.playerLevel[8] >= 61)
				return true;
		break;
		default:
			return false;
			
		}
		return false;
	}

	public void out(String s, client c) {	}

	public boolean doaction(client c, int toadd, int toremove, int toremove2, int x, int y, int objectid, int xp) {
	if (true) {
		if (c.freeSlots() > 0) {
		this.toadd = toadd;
		this.c.iscutting = true;
		this.xp = xp;
	if (c.wctime <= 0) {
		server.worldObject.reduceHealth(x, y);
		this.c.CAM().sendMessage("You get some logs");
		this.c.wctime = misc.random(20 - c.intervaldeduction);
		this.c.addItem(toadd, 1);
		this.c.addSkillXP(xp * server.SERVER_EXPERIENCE, 8);
		}
	} else {
		this.c.getStatus().setNotBusy();
		this.c.CAM().sendMessage("You've do not have enough room in your inventory.");
		this.c.cancut = false;
		this.c.iscutting = false;
		this.c.wctime = 0;
		}
	}
	return true;
	}
	
	

}