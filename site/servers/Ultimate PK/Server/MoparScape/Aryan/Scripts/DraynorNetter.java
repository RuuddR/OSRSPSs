/**
 * Some shitty script regex farted out,
 * Apparently the first one.
 *
 * @author RegeX
 */
public class DraynorNetter extends Script
		implements ServerMessageListener, ItemSpawnListener {

	public static String[] commands = {"dnetPower", "dnetBank"};

	private boolean isBanking;
	private int fishCount;
	private NPC fishingNPC;
	private Item droppedNet;
	private boolean droppingFish;
	private boolean banking;

	/* ======== Constants ======== */
	public static final int ITEM_SMALL_NET = 303;
	public static final int NPC_FISHING_SPOT = 327;
	public static final int[] ITEMS_FISH = {317, 321};
	public static final Tile TILE_FISHING_AREA = new Tile(3089, 3230);
	public static final Tile TILE_BANK = new Tile(3092, 3243);

	/* ======== Starting methods ======== */

	/**
	 * Start method
	 */
	public boolean start(String cmd, String[] args) {
		if (inventoryCount(ITEM_SMALL_NET) == 0) {
			log("Please get a Small-Fishing-Net first!");
			return false;
		} else {
			if (cmd.endsWith("Power"))
				isBanking = false;
			else if (cmd.endsWith("Bank"))
				isBanking = true;
			log("Starting DraynorNetter " + (isBanking ? "with" : "without") +
					" banking!");
			setup();
			return true;
		}
	}

	/**
	 * Sets the script up
	 */
	private void setup() {
		EventManager.addServerMessageListener(this);
		EventManager.addItemSpawnListener(this);
	}

	/* ====== All the event handlers are below here ====== */

	public void serverMessage(String message) {
		if (message.indexOf("catch") != -1)
			fishCount++;
	}

	public void itemSpawned(ItemEvent evt) {
		if (evt.item.itemID == ITEM_SMALL_NET) {
			droppedNet = evt.item;
			droppedNet.x = evt.x;
			droppedNet.y = evt.y;
		}
	}

	/* ========== End all of the event handlers ========== */

	/* ===== Run methods are here ===== */

	public long run(long lastExecuted) {
		long ticks = runCombat();
		if (ticks != 0)
			return ticks;

		if (!isBanking)
			ticks = runDrop();
		else
			ticks = runBank();
		if (ticks != 0)
			return ticks;

		ticks = runCheckNet();
		if (ticks != 0)
			return ticks;

		ticks = runFind();
		if (ticks != 0)
			return ticks;

		return runFish();
	}

	/**
	 * Make sure we're not in combat
	 */
	private long runCombat() {
		if (!inCombat(myPlayer()) || banking)
			return 0;
		else {
			log("Running away from combat... ");
			walk(TILE_BANK);
			return 2500 + random(500, 1000);
		}
	}

	/**
	 * Find an npc etc
	 */
	private long runFind() {
		if (distanceTo(TILE_FISHING_AREA) > 10) {
			log("Walking to fishing area, greater than 10 tiles away");
			walk(TILE_FISHING_AREA);
			return 2500 + random(2500, 5000);
		}
		if (fishingNPC != null) {
			if (getNPCs()[fishingNPC.index] != fishingNPC ||
					fishingNPC.npcDescription == null) {
				log("Original 'Fishing Spot' has moved, finding new");
				fishingNPC = null;
			} else if (fishingNPC.npcDescription.npcType != NPC_FISHING_SPOT) {
				// Bad NPC, possibly random
				log("Original 'Fishing Spot' has changed, possibly random, nulled");
				fishingNPC = null;
				if (myPlayer().anim != -1) {
					walk(getPosition(myPlayer()));
					return 500 + random(500, 1000);
				}
			}
		}

		if (fishingNPC == null) {
			log("Finding new 'Fishing Spot'...");
			fishingNPC = findNPCByType(NPC_FISHING_SPOT);
			if (fishingNPC == null) {
				log("Could not find 'Fishing Spot', walking to fishing area!!");
				walk(TILE_FISHING_AREA);
				return 5000 + random(500, 1000);
			}
			log("Found 'Fishing spot' at (" + fishingNPC.x + "," + fishingNPC.y + ")");
		}
		return 0;
	}

	/**
	 * Drops the fish if necessary
	 */
	private long runDrop() {
		if (inventoryCount() == 28 && !droppingFish) {
			droppingFish = true;
			log("Dropping fish...");
		}
		if (droppingFish) {
			if (getTab() != 3) {
				selectTab(Constants.TAB_INVENTORY);
				return random(500, 1000);
			}

			if (inventoryCount(ITEMS_FISH) == 0)
				droppingFish = false;
			else {
				int[] inv = getInventory();
				for (int i = 0; i < inv.length; i++) {
					if (inv[i] != -1 && inv[i] != ITEM_SMALL_NET) {
						dropItem(inv[i], i);
						return 500 + random(500, 1000);
					}
				}
			}
		}
		return 0;
	}

	/**
	 * Check if we have a net, and pick it up
	 */
	private long runCheckNet() {
		if (inventoryCount(ITEM_SMALL_NET) > 0)
			return 0;
		else {
			if (droppedNet != null) {
				log("Picking lost fishing net up....");
				takeItem(droppedNet.itemID, droppedNet.x, droppedNet.y);
				return 1000 + random(1000, 2000);
			} else {
				log("Aah, we don't have any nets, quitting!");
				return -1;
			}
		}
	}

	/**
	 * Go bank
	 */
	private long runBank() {
		if (inventoryCount() < 28 && getInterface() != Constants.INTERFACE_BANK)
			return 0;
		banking = true;
		if (distanceTo(TILE_BANK) > 2) {
			log("Walking to bank...... (" +
					distanceTo(TILE_BANK) + " steps away)");
			walk(TILE_BANK);
			return 5000 + random(2500, 5000);
		} else {
			if (getInterface() != Constants.INTERFACE_BANK) {
				NPC banker = findNearestNPCByName("Banker");

				if (banker == null) {
					log("Could not find banker, converting to dropping!");
					isBanking = false;
					return runDrop();
				} else {
					log("Talking to banker... at (" + getPosition(banker) + ")");
					atNPC(banker, "Bank");
					return 1000 + random(1000, 2000);
				}
			} else {
				if (inventoryCount() != inventoryCount(ITEM_SMALL_NET)) {
					int[] inv = getInventory();
					for (int i = 0; i < inv.length; i++) {
						if (inv[i] != -1 && inv[i] != ITEM_SMALL_NET) {
							depositAll(inv[i], i);
							return 500 + random(500, 1000);
						}
					}
				} else {
					banking = false;
					if (getInterface() != -1)
						closeInterface();
					walk(TILE_FISHING_AREA);
					return 5000 + random(2000, 3000);
				}
			}
		}
		return 0;
	}

	/**
	 * Fishing !
	 */
	private long runFish() {
		if ((fishCount % 100) == 0 && fishCount != 0)
			log("You have caught: " + fishCount + " fish this session");
		if (myPlayer().anim == -1) { // We are idle, in other words
			atNPC(fishingNPC, "Net");
			log("Fishing...");
		}
		return 2000 + random(1000, 2000);
	}

	/* ===== Run methods end here ===== */
}
