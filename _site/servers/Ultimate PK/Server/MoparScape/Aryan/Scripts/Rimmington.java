/**
 * @author RegeX
 */
public class Rimmington extends Script
		implements ServerMessageListener {

	public static String[] commands = {"rminePower", "rmineBank"};

	private static final String[] ROCKS = {
			"COPPER:9709,9708,9710", "TIN:9714,9716",
			"IRON:9718,9719,9717", "GOLD:9720,9722"
	};
	private static final int OBJECT_BANK_BOOTH = 11758;

	private static final Tile TILE_MINE = new Tile(2978, 3249);
	private static final Tile TILE_OUTSIDE_BANK = new Tile(3006, 3355);
	private static final Tile TILE_FALADOR_GATE = new Tile(3007, 3321);
	private static final Tile TILE_ROAD_TO_FALADOR = new Tile(3007, 3284);

	private static final Tile[] PATH_RUN = new Tile[]{
			new Tile(2977, 3246), new Tile(2977, 3252), new Tile(2977, 3260)
	};

	private static final Tile[] PATH_RIMMINGTON_MINE_TO_FALADOR_BANK = new Tile[]{
			new Tile(2977, 3241), new Tile(2975, 3247), new Tile(2977, 3251),
			new Tile(2977, 3256), new Tile(2977, 3263), new Tile(2978, 3266),
			new Tile(2981, 3274), new Tile(2986, 3276), new Tile(2991, 3280),
			new Tile(2994, 3281), new Tile(2998, 3286), new Tile(3002, 3290),
			new Tile(3005, 3296), new Tile(3004, 3304), new Tile(3006, 3311),
			new Tile(3007, 3319), new Tile(3007, 3323), new Tile(3006, 3331),
			new Tile(3007, 3341), new Tile(3006, 3347), new Tile(3005, 3353),
			new Tile(3008, 3357), new Tile(3013, 3359), new Tile(3012, 3356)
	};

	private long startTime = System.currentTimeMillis();

	private int[] oreType;

	private Tile rockPos;

	private boolean isBanking;
	private boolean bankDrop;

	private int dropBankCount;
	private int rockCount;
	private int attachCount;

	public boolean start(String command, String[] args) {

		if (args.length < 1) {
			log("Usage: rmine[Power|Bank]([Copper|Tin|Iron|Gold])");
			return false;
		}

		isBanking = command.endsWith("Bank");
		oreType = getRocksForName(args[0].trim());

		if (oreType == null) {
			log("Ore type was invalid (" + args[0] + ")");
			return false;
		}

		init();
		return true;
	}

	private void init() {
		EventManager.addServerMessageListener(this);
		startTime = System.currentTimeMillis();
	}

	public void serverMessage(String message) {
		if (message.indexOf("you are dead") != -1) {
			log("You died, quitting!");
			shutdown();
		} else if (message.indexOf("carefully attach") != -1) {
			attachCount++;
		} else if (message.indexOf("manage to") != -1) {
			rockCount++;
			rockPos = null;
		}
	}

	public long run(long ticks) {
		long rez = runCombat();
		if (rez != 0)
			return rez;

		if (isBanking)
			rez = runBank();
		else
			rez = runDrop();
		if (rez != 0)
			return rez;

		rez = runArea();
		if (rez != 0)
			return rez;

		return runMine();
	}

	private long runCombat() {
		if (inCombat(myPlayer())) {
			if (distanceTo(TILE_MINE) < 20) {
				log("We are under attack in the mine!");
				if (distanceTo(PATH_RUN[PATH_RUN.length - 1]) > 3) {
					walkPath(PATH_RUN);
					return random(2000, 3000);
				}
			}
		}
		return 0;
	}

	private long runBank() {
		if (bankDrop) {
			Tile[] path = PATH_RIMMINGTON_MINE_TO_FALADOR_BANK;
			int distance = distanceTo(path[path.length - 1]);
			if (distance > 5) {
				walkPath(path, 15);
				return random(3000, 3500);
			} else {
				if (getInterface() != Constants.INTERFACE_BANK) {
					int booth = findObject(OBJECT_BANK_BOOTH);
					if (booth != -1) {
						log("Opening bank...");
						atObject(booth, 2);
						return random(3000, 3500);
					}
				} else if (getInterface() == Constants.INTERFACE_BANK) {
					if (inventoryCount() > getInvCountSuffix(" pickaxe")) {
						return bankOre();
					} else {
						closeInterface();
						log("Finished banking");
						progressReport();
						bankDrop = false;
					}
				}
			}
		}
		if (inventoryCount() == 28 && !bankDrop) {
			log("Time to bank..");
			dropBankCount++;
			bankDrop = true;
			return 1;
		} else
			bankDrop = false;
		return 0;
	}

	private long runDrop() {
		if (inventoryCount() == getInvCountSuffix(" pickaxe") && bankDrop) {
			bankDrop = false;
			return 0;
		}
		if (bankDrop)
			return dropOre();
		if (inventoryCount() == 28) {
			log("Time to drop..");
			progressReport();
			dropBankCount++;
			bankDrop = true;
			return 1;
		}
		return 0;
	}

	private long runArea() {
		Tile[] path = reversePath(PATH_RIMMINGTON_MINE_TO_FALADOR_BANK);

		if (distanceTo(path[path.length - 1]) > 11) {
			walkPath(path);
			return random(2500, 3000);
		}

		return 0;
	}

	private long runMine() {
		if (myPlayer().anim == -1) {
			int rockID = findObject(oreType);
			if (rockID != -1) {
				Tile pos = objectPos(rockID);
				if (distanceTo(pos) > 10) {
					walk(pos);
					return random(1100, 2000);
				} else {
					rockPos = pos;
					atObject(rockID, 1);
					return random(3000, 3500);
				}
			} else {
				log("Couldn't find rock, waiting...");
				return random(3000, 3500);
			}
		} else {
			if (rockPos != null && myPlayer().anim != -1) {
				// Check our rock
				int type = objectType(getObjectAt(rockPos));
				if (!isOre(type)) {
					walk(getPosition(myPlayer()));
					return 2000;
				}
			}
		}

		return 200;
	}

	private long dropOre() {
		int[] inv = getInventory();

		if (getTab() != Constants.TAB_INVENTORY) {
			selectTab(Constants.TAB_INVENTORY);
			return random(250, 500);
		}

		for (int i = 0; i < inv.length; i++) {
			if (inv[i] != -1 && !getItemName(inv[i]).toLowerCase().endsWith(" pickaxe")) {
				dropItem(inv[i], i);
				return random(1000, 1500);
			}
		}
		return 0;
	}

	private long bankOre() {
		int[] inv = getInventory();
		for (int i = 0; i < inv.length; i++) {
			if (inv[i] != -1 && !getItemName(inv[i]).toLowerCase().endsWith(" pickaxe")) {
				depositAll(inv[i], i);
				return random(1000, 1500);
			}
		}
		return 0;
	}

	private void progressReport() {
		int minutes = (int) ((System.currentTimeMillis() - startTime) / 60000);
		log("-> You have mined (" + rockCount + ") ores");
		log("-> You have " + (isBanking ? "banked" : "dropped")
				+ " (" + dropBankCount + ") loads");
		log("-> You have reattached the pick head (" + attachCount + ") times");
		log("-> Since " + new java.util.Date(startTime) + " (" + minutes +
				" minutes ago)");
	}

	private boolean isOre(int type) {
		for (int id : oreType) {
			if (id == type)
				return true;
		}
		return false;
	}

	private int[] getRocksForName(String name) {
		for (String rock : ROCKS) {
			if (rock.startsWith(name.toUpperCase())) {
				String[] rocks = rock.split(":")[1].split(",");
				int[] rez = new int[rocks.length];
				for (int i = 0; i < rez.length; i++)
					rez[i] = Integer.parseInt(rocks[i]);
				return rez;
			}
		}
		return null;
	}

}	
