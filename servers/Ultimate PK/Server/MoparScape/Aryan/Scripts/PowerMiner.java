/*
  powerminer with banking and walkback, should be mostly working
  make a text file in Configs/ called paths.txt
  in there, an entry 0=x1,y1,x2,y2,xn,yn
  the path needs to start at mine and end at bank
  specify multiple paths with the path= argument

  walkback is untested

  usage: /powerMiner([optional bank,][optional walkback,][optional path=int,]id1,id2,idn)

  by eXemplar
 */

import java.util.ArrayList;
import java.util.List;

public class PowerMiner extends Script
		implements ServerMessageListener {

	public static String[] commands = {"powerMiner"};

	private int OBJECT_BANK_BOOTH = 2213;

	private int retryCount, currentRocks[];
	private Tile startTile, combatTile, lastRockPos, bankPath[], walkBackPath[];
	private boolean dropOres, humanReaction, banking;
	private boolean walkBank, walkMine, doBank, walkBack, walkingBack, runCombat;

	private long startTime, oresMined;

	public boolean start(String cmd, String[] args) {
		llog("Powerminer + banker by eXemplar");
		if (args == null || args.length <= 0) {
			printUsage();
			return false;
		}

		String pathNum = "0";

		// grab the id's of the rocks we want to mine,
		// and see if we're banking or using a custom path
		List<Integer> rocks = new ArrayList<Integer>();
		for (String s : args) {
			if (s.equalsIgnoreCase(""))
				continue;
			if (s.toLowerCase().startsWith("bank"))
				banking = true;
			else if (s.equalsIgnoreCase("walkback"))
				walkingBack = true;
			else if (s.toLowerCase().startsWith("path")) {
				if (s.contains("="))
					pathNum = s.split("=")[1].trim();
			} else
				rocks.add(Integer.valueOf(s));
		}
		if (rocks.size() <= 0) {
			llog("Please specify some ore ids to start with,");
			printUsage();
			return false;
		}

		Object[] array = rocks.toArray();
		currentRocks = new int[array.length];
		for (int i = 0; i < currentRocks.length; i++)
			currentRocks[i] = Integer.valueOf(array[i].toString());

		Configuration c = new Configuration("Configs/paths.txt");
		if (banking)
			try {
				String[] points = c.get(String.valueOf(pathNum)).split(",");
				if (points == null || points.length % 2 > 0)
					throw new Exception();
				List<Tile> path = new ArrayList<Tile>();
				for (int i = 0; i < points.length - 1; i = i + 2)
					path.add(new Tile(Integer.valueOf(points[i]),
							Integer.valueOf(points[i + 1])));
				if (path.size() <= 0)
					throw new Exception();
				Object[] array2 = path.toArray();
				bankPath = new Tile[array2.length];
				for (int i = 0; i < bankPath.length; i++)
					bankPath[i] = (Tile) array2[i];
			} catch (Exception e) {
				llog("Error loading paths, not banking");
				banking = false;
			}
		if (walkingBack)
			try {
				String[] points = c.get("walkback").split(",");
				if (points == null || points.length % 2 > 0)
					throw new Exception();
				List<Tile> path = new ArrayList<Tile>();
				for (int i = 0; i < points.length - 1; i = i + 2)
					path.add(new Tile(Integer.valueOf(points[i]),
							Integer.valueOf(points[i + 1])));
				if (path.size() <= 0)
					throw new Exception();
				Object[] array2 = path.toArray();
				walkBackPath = new Tile[array2.length];
				for (int i = 0; i < walkBackPath.length; i++)
					walkBackPath[i] = (Tile) array2[i];
			} catch (Exception e) {
				llog("Error loading walkback path, not walking back");
				walkingBack = false;
			}

		llog("Running powerMiner in " + (banking ? "mine + bank" : "power miner") + " mode");
		if (banking)
			llog("Using path " + pathNum);
		if (walkingBack)
			llog("Using walkback path if dead");

		EventManager.addServerMessageListener(this);

		// default vars
		startTile = getPosition(myPlayer());
		lastRockPos = startTile;
		startTime = System.currentTimeMillis();
		oresMined = 0;

		return true;
	}

	private void printUsage() {
		llog("PowerMiner usage:");
		llog("\t /powerMiner([optional bank,][optional walkback,][optional path=int,]id1,id2,idn)");
		llog("To use banking, make a text file in Configs/ called paths.txt containing enties like,");
		llog("rimm=x1,y1,x2,y2 etc, by default the script reads entry 0");
		llog("- eXemplar");
	}

	public long run(long lastExecuted) {
		// make sure we don't do anything when we can't
		if (!loggedIn())
			return 2000;

		// make sure something is not fighting us
		if (inCombat(myPlayer())) {
			llog("Running away from combat");
			runCombat = true;
			combatTile = getPosition(myPlayer());
			walk(new Tile(combatTile.x + random(0, 30) - 15,
					combatTile.y + random(0, 30) - 15));
			return random(15000, 25000);
		}
		if (runCombat) {
			if (isMoving())
				return 2000;
			llog("Returning from combat");
			if (distanceTo(combatTile) < 5)
				runCombat = false;
			walk(combatTile);
			return random(2000, 5000);
		}

		// something has taken us too far away from the ore/bank, die
		if ((distanceTo(startTile) > 25 || distanceTo(lastRockPos) > 25) && !banking) {
			llog("Possible teleport, quitting!");
			llog("My position: " + getPosition(myPlayer()).toString() +
					", starting tile position: " + startTile.toString() +
					", last rock position: " + lastRockPos.toString());
		}

		// make sure we don't do anything when we shouldn't
		if (isMoving())
			return 2000;

		if (walkBack) {
			Tile[] path = walkBackPath;
			int distance = distanceTo(path[path.length - 1]);
			if (distance > 5) {
				walkPath(path, 15);
				return random(3000, 4000);
			}
			walkBack = false;
			return 0;
		}

		if (banking) {
			// check and do walking to/from bank
			if (walkBank) {
				llog("Walking to bank");
				Tile[] path = bankPath;
				if (distanceTo(path[path.length - 1]) > 5) {
					walkPath(path, random(11,15));
					return random(3000, 4000);
				}
				walkBank = false;
				return 0;
			}

			if (walkMine) {
				llog("Walking to mine");
				Tile[] path = reversePath(bankPath);
				if (distanceTo(path[path.length - 1]) > 11) {
					walkPath(path, random(11,15));
					return random(3000, 4000);
				}
				walkMine = false;
				return 0;
			}
			// if we're supposed to bank
			if (doBank) {
				if (getInterface() != Constants.INTERFACE_BANK) {
					llog("Opening bank");
					// no need to check if booth exists, assuming
					// we're already at it due to the above if
					atObject(findObject(OBJECT_BANK_BOOTH), 2);
					return 2500 + random(500, 1000);
				}

				// if there's items other than our axe in inv
				if (getInvCountSuffix(" pickaxe") < inventoryCount()) {
					for (int i = 0; i < getInventory().length; i++) {
						if (getInventory()[i] >= 0 &&
								!getItemName(getInventory()[i]).toLowerCase().endsWith(
										" pickaxe")) {
							llog("Depositing " + getItemName(getInventory()[i]));
							depositAll(getInventory()[i], i);
							return random(1000, 1500);
						}
					}
				}
				llog("Closing bank");
				closeInterface();
				doBank = false;
				walkMine = true;
				return random(1000, 2000);
			}
		} else {
			if (getTab() != Constants.TAB_INVENTORY) {
				selectTab(Constants.TAB_INVENTORY);
				return random(500, 1000);
			}
			// if we're supposed to drop ores
			if (dropOres) {
				for (int i = 0; i < getInventory().length; i++) { // go through the inventory and drop everything bar a pickaxe
					if (getInventory()[i] < 0)
						continue;
					String itemName = getItemName(getInventory()[i]);
					if (!itemName.toLowerCase().endsWith(" pickaxe") || itemName.toLowerCase().startsWith("Broken ")) {
						llog("Dropping " + itemName);
						dropItem(getInventory()[locateInvSuffix(itemName)],
								locateInvSuffix(itemName));
						return random(700, 1000);
					}
				}
				printStats();
				dropOres = false; // if we get here there's nothing else to drop
			}
		}

		// check if inv full
		if (inventoryCount() >= 28) {
			if (banking) {
				Tile[] path = bankPath;
				int distance = distanceTo(path[path.length - 1]);
				if (distance <= 5)
					doBank = true;
				else
					walkBank = true;
			} else
				dropOres = true;
			return 0;
		} else if (findObject(currentRocks) < 0 && (distanceTo(startTile) > 11)) {
			// must be somewhere in between
			walkMine = true;
			return 0;
		}

		// if we're out of mining instruments
		if (getInvCountSuffix(" pickaxe") <= 0 || getInvCountPrefix("Broken pickaxe") == getInvCountSuffix(" pickaxe")) {
			if (retryCount > 10) {
				llog("Out of pickaxes, quitting");
				shutdown();
				return -1;
			}
			retryCount++;
			return 1000;
		} else
			retryCount = 0;

		// if we're mining and there's no ore at the location (or gas), gtfo
		if (lastRockPos != null && myPlayer().anim >= 0)
			if (isOreAt(lastRockPos))
				return random(200, 300);
			else {
				llog("Gas found or someone mined out our ore.");
				walk(getPosition(myPlayer()));
				return random(100, 150);
			}

		// random pauses
		if (random(1, 40) == 5 && myPlayer().anim >= 0) {
			llog("Pausing for a bit");
			return random(3000, 6000);
		}

		// attempt mine at the closes rock site if possible
		int rock = findObject(currentRocks);
		if (rock < 0) {
			llog("Cannot find a rock");
			return 0; // possibly too low a return value
		}
		lastRockPos = objectPos(rock);
		// attempt to walk to rock if too far away
		if (distanceTo(lastRockPos) > 10) {
			// make a few attempts to walk to a pos around the rock
			for (int i = 0; i < 5; i++) {
				Tile around = new Tile(lastRockPos.x + random(0, 4) - 2,
						lastRockPos.y + random(0, 4) - 2);
				if (getObjectAt(around) < 0) {
					llog("Walking to around rock");
					walk(around);
					return random(3000, 5000);
				}
			}
			llog("Walking to rock");
			walk(lastRockPos); // failing that walk straight to it
			return random(3000, 5000);
		}
		if (!humanReaction) {
			humanReaction = true;
			return random(400, 600);
		} else
			humanReaction = false;
		llog("Mining rock, x=" + lastRockPos.x + ",y=" + lastRockPos.y);
		atObject(rock, 1);
		return random(2000, 2500);
		// hopefully long enough for the mining animation to kick in
	}

	public void serverMessage(String message) {
		if (message.toLowerCase().indexOf("you are dead") >= 0) {
			if (walkingBack) {
				llog("You died, starting walk back");
				walkBack = true;
			} else {
				llog("You died, quitting!");
				shutdown();
			}
		} else if (message.toLowerCase().indexOf("manage to mine") >= 0)
			oresMined++;
	}

	// simple test whether there is an ore at tile
	private boolean isOreAt(Tile pos) {
		for (int id : currentRocks)
			if (objectType(getObjectAt(pos, 5)) == id)
				return true;
		return false;
	}

	// simple test whether type is an ore
	private boolean isOre(int type) {
		for (int id : currentRocks)
			if (id == type)
				return true;
		return false;
	}

	private void printStats() {
		float hours = ((System.currentTimeMillis() - startTime) / 1000F / 60F / 60F);
		llog("== Power Miner Stats ==");
		llog("Mined for " + hours + " hours");
		llog("Mined " + oresMined + " ores");
		llog("Averaging " + oresMined / hours + " ores per hour");
		llog("=======================");
	}

	private String lastMsg = "";

	private void llog(String s) {
		if (lastMsg.equalsIgnoreCase(s))
			return;
		lastMsg = s;
		log(s);
	}

}
