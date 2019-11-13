/**
 * @author RegeX, pd
 */
public class PowerChopper extends Script
		implements ServerMessageListener {

	public static String[] commands = {"powerChopper"};

	// incomplete
	private static final String[] TREES = {
			"TREE:1276,1278",
			"OAK:1281",
			"WILLOW:1308,5551,5552,5553",
			"YEW:1309"
	};

	private long startTime = System.currentTimeMillis();

	private String treeName;
	private int[] treeType;

	private Tile treePos;
	private Tile returnTile;

	private int treeCount;
	private int attachCount;

	private boolean dropping;

	public boolean start(String cmd, String[] args) {
		if (args.length < 1) {
			log("Usage: powerChopper([Tree Type])");
			return false;
		}

		treeType = getTreesForName(args[0]);

		if (treeType == null) {
			log("Tree type was invalid (" + args[0] + ")");
			return false;
		}

		EventManager.addServerMessageListener(this);
		return true;
	}

	public void serverMessage(String message) {
		if (message.indexOf("you are dead") != -1) {
			log("You died, quitting!");
			shutdown();
		} else if (message.indexOf("carefully attach") != -1) {
			attachCount++;
		} else if (message.indexOf("some " + treeName.toLowerCase() + " logs") != -1) {
			treeCount++;
		}
	}

	public long run(long lastExecuted) {
		runProgress();

		long ticks = runCombat();
		if (ticks != 0)
			return ticks;

		ticks = runDrop();
		if (ticks != 0)
			return ticks;

		return runChop();
	}

	private void runProgress() {
		if (treeCount % 100 == 0 && treeCount != 0) {
			log("You have cut " + treeCount + " trees!");
			log("You have reattached axe head " + attachCount + " times!");
			log("Since " + ((System.currentTimeMillis() - startTime) / 60000) +
					" minutes ago");
			treeCount++;
		}
	}

	private long runCombat() {
		if (!inCombat(myPlayer())) {
			if (returnTile != null) {
				log("Returning to pre-combat tile...");
				walk(returnTile);
				returnTile = null;
				return random(5000, 6000);
			}
			return 0;
		} else {
			log("Running away from combat... ");
			returnTile = getPosition(myPlayer());
			Tile pos = returnTile;

			do {
				pos.x += (random(10, 20) % 2 == 0 ? -1 : 1) * random(5, 10);
				pos.y += (random(10, 20) % 2 == 0 ? -1 : 1) * random(5, 10);
			} while (!canReach(pos));

			walk(pos);
			return random(5000, 6000);
		}
	}

	private long runDrop() {
		if (inventoryCount() == getInvCountSuffix(" axe") && dropping) {
			dropping = false;
			return 0;
		}

		if (dropping)
			return dropLogs();

		if (inventoryCount() == 28) {
			log("Time to drop..");
			dropping = true;
			return 1;
		}
		return 0;
	}

	private long runChop() {
		if (myPlayer().anim == -1) {
			int treeID = findObject(treeType);
			if (treeID != -1) {
				Tile pos = objectPos(treeID);
				if (distanceTo(pos) > 5) {
					walk(pos);
					return random(4500, 6500);
				} else {
					treePos = pos;
					atObject(treeID, 1);
					return random(3000, 3500);
				}
			} else {
				log("Could not find tree, waiting");
				return random(3000, 3500);
			}
		} else if (treePos != null && myPlayer().anim != -1) {
			NPC ent = findNearestNPCByName(treeName);
			if (ent == null)
				return 200;
			if (getPosition(ent).distanceTo(treePos) < 3) {
				log("Ent appeared near us, stop chopping.");
				walk(getPosition(myPlayer()));
				treePos = null;
			}
			return random(3000, 3500);
		}

		return 200;
	}

	private long dropLogs() {
		if (getTab() != Constants.TAB_INVENTORY) {
			selectTab(Constants.TAB_INVENTORY);
			return random(500, 1000);
		}

		int[] inv = getInventory();
		for (int i = 0; i < inv.length; i++) {
			if (inv[i] != -1 && !getItemName(inv[i]).endsWith(" axe")) {
				dropItem(inv[i], i);
				return random(1000, 1500);
			}
		}
		return 0;
	}

	private int[] getTreesForName(String name) {
		for (String log : TREES) {
			if (log.startsWith(name.toUpperCase())) {
				treeName = log.split(":")[0].substring(0, 1).toUpperCase() +
						log.split(":")[0].substring(1).toLowerCase();
				String[] trees = log.split(":")[1].split(",");
				int[] rez = new int[trees.length];
				for (int i = 0; i < rez.length; i++)
					rez[i] = Integer.parseInt(trees[i]);
				return rez;
			}
		}
		return null;
	}
}
