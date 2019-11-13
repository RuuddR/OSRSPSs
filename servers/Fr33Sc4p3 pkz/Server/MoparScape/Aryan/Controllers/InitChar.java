/**
 * A basic controller that runs a character through
 * Tutorial Island, and then fights some chickens to
 * gain a few combat levels, and hopefully a little
 * cash.
 */
public class InitChar extends Controller {
	private final static int OBJECT_CHICKEN_GATE = 1553;
	private final static Tile TUTORIAL_ISLAND = new Tile(3094, 3107);
	private final static Tile LUMBRIDGE = new Tile(3222, 3219);
	private final static Tile NEAR_CHICKEN_GATE = new Tile(3238, 3295);

	// default password used for creating accounts
	private final static String defPass = "a1r2y3a4n5";

	// this is the level for att/str/def you want to reach
	private final int maxLevel = 10;

	private String curScript = "";
	private String curStat = null;

	public void load() {
		if (getUser() == null) {
			log("Please specify username in your config file.");
			shutdown();
		}

		if (!userExists(getUser())) {
			log("User " + getUser() + " does not exist, attempting to register");
			if (!createAccount(getUser(), defPass)) {
				log("Could not create user, shutting down.");
				shutdown();
			}
			log("User " + getUser() + " created with default password " + defPass);
			setUser(getUser(), defPass);
		}
	}

	public long run(long last) {
		// log in if we've just started.
		if (!scriptManager.running() && !loggedIn()) {
			login();
			return 5000;
		}

		if (finished()) {
			log("Character initialization completed, shutting down");
			shutdown();
		}

		if (curScript.equals("chickens") && needNextStat())
			return nextStat();

		if (curScript.equals("walk") && distanceTo(NEAR_CHICKEN_GATE) < 5) {
			scriptManager.stopScript(curScript);
			curScript = "";
			int gate = findObject(OBJECT_CHICKEN_GATE);
			if (gate != -1) {
				atObject(gate, 1);
				return random(1000, 1500);
			}
		}

		if (scriptManager.isScriptRunning(curScript))
			return 2000;

		if (distanceTo(TUTORIAL_ISLAND) < 5) {
			log("Starting Tutorial Island script.");
			curScript = "tutIsland";
			scriptManager.startScript(curScript + "()");
			return 2000;
		}

		if (distanceTo(LUMBRIDGE) < 10) {
			// equip sword & shield, drop everything else but the pick axe
			if (inventoryCount() > 1)
				return initEquip();

			log("In Lumbridge, walking to chicken pen");
			curScript = "walk";
			scriptManager.startScript(curScript + "(lumbridge, varrock)");
			return 2000;
		}

		// this should only be reached once.
		if (distanceTo(NEAR_CHICKEN_GATE) < 5) {
			log("At chicken pen, fighting.");
			curScript = "chickens";
			curStat = "attack";
			scriptManager.startScript(curScript + "(" + curStat + ")");
			return 2000;
		}

		return 0;
	}

	private boolean finished() {
		return getMaxStat("Attack") >= maxLevel
				&& getMaxStat("Defence") >= maxLevel
				&& getMaxStat("Strength") >= maxLevel;
	}

	private boolean needNextStat() {
		return getMaxStat(curStat) >= maxLevel;
	}

	private long nextStat() {
		scriptManager.stopScript(curScript);

		if (curStat == null)
			curStat = "attack";
		else if (curStat.equals("attack"))
			curStat = "strength";
		else if (curStat.equals("strength"))
			curStat = "defence";
		else if (curStat.equals("defence"))   // should never happen
			curStat = "attack";

		scriptManager.startScript(curScript + "(" + curStat + ")");
		return 2000;
	}

	private long initEquip() {
		if (getTab() != Constants.TAB_INVENTORY) {
			selectTab(Constants.TAB_INVENTORY);
			return random(500, 1000);
		}

		if (getInvCountPrefix("Bronze sword") > 0) {
			equipItem("Bronze sword");
			return random(1500, 2000);
		}

		if (getInvCountPrefix("Wooden shield") > 0) {
			equipItem("Wooden shield");
			return random(1500, 2000);
		}

		int[] inv = getInventory();
		for (int i = 0; i < inv.length; i++) {
			if (inv[i] != -1 && !getItemName(inv[i]).toLowerCase().endsWith(" pickaxe")) {
				dropItem(inv[i], i);
				return random(1500, 2000);
			}
		}

		return 0;
	}

	// i should put this in AccessorMethods some day.
	private void equipItem(String name) {
		atSpecialInventoryItem(getInventory()[locateInvPrefix(name)],
				locateInvPrefix(name), 2);
	}
}
