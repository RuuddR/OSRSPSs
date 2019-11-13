/**
 * Generic chicken fighter. Should work anywhere.
 * Picks up feathers, buries bones.
 */
public class Chickens extends Script
		implements ItemSpawnListener, ServerMessageListener {

	public static String[] commands = new String[] { "chickens" };

	private Item bones;
	private Item feathers;
	private boolean burying;
	private boolean setFightMode = true;
	private int interfaceID = -1;

	private String[] INTERFACE_IDS = new String[] {
		"ATTACK:2282", "STRENGTH:2285", "DEFENCE:2283"
	};

	public boolean start(String cmd, String[] args) {
		interfaceID = getInterfaceByName(args[0]);
		if (interfaceID == -1) {
			log("Usage: /chickens([attack|strength|defence])");
			return false;
		}

		log("Killing Chickens, Fight mode " + args[0] + " @ interface " + interfaceID);
		EventManager.addItemSpawnListener(this);
		EventManager.addServerMessageListener(this);
		return true;
	}

	public long run(long last) {
		long rez = changeFightMode();
		if (rez != 0)
			return rez;

		if (inCombat(myPlayer()) || isMoving()) {
			NPC npc = getInteractingNPC(myPlayer());
			if (npc != null && npc.npcDescription != null) {
				String name = npc.npcDescription.npcName;
				if (!name.equals("Chicken"))
					return runFromRandom(name);
			}

			return random(500, 1500);
		}

		rez = buryBones();
		if (rez != 0)
			return rez;

		rez = getFeathers();
		if (rez != 0)
			return rez;

		rez = getBones();
		if (rez != 0)
			return rez;

		return fightChicken();
	}

	private long changeFightMode() {
		if (setFightMode) {
			if (getTab() != Constants.TAB_ATTACK) {
				selectTab(Constants.TAB_ATTACK);
				return random(500, 1000);
			}
			atInterface(interfaceID, 5);
			setFightMode = false;
			return random(500, 1000);
		}

		return 0;
	}

	private long runFromRandom(String name) {
		Tile pos = getPosition(myPlayer());

		// ignore not having the proper tab open, just set run quickly
		if (!getRun()) {
			setRun(true);
			return random(500, 1000);
		}

		log("Attempting to run from NPC " + name);
		do {
			pos.x += (random(10, 20) % 2 == 0 ? -1 : 1) * random(5, 10);
			pos.y += (random(10, 20) % 2 == 0 ? -1 : 1) * random(5, 10);
		} while (!canReach(pos));

		log("Running from " + getPosition(myPlayer()) + " to " + pos);
		walk(pos);
		return random(2000, 3000);
	}

	private long buryBones() {
		if (getInvCountPrefix("Bones") == 0) {
			burying = false;
			return 0;
		}

		if (inventoryCount() == 28)
			burying = true;

		if (burying) {
			if (getTab() != Constants.TAB_INVENTORY) {
				selectTab(Constants.TAB_INVENTORY);
				return random(500, 1000);
			}

			atSpecialInventoryItem(getInventory()[locateInvPrefix("Bones")],
					locateInvPrefix("Bones"), 1);
			return random(1500, 2500);
		}

		return 0;
	}

	private long getFeathers() {
		if (feathers == null)
			return 0;

		takeItem(feathers.itemID, feathers.x, feathers.y);
		feathers = null;
		return random(3000, 5000);
	}

	private long getBones() {
		if (bones == null)
			return 0;

		takeItem(bones.itemID, bones.x, bones.y);
		bones = null;
		return random(3000, 5000);
	}

	private long fightChicken() {
		NPC chicken = findNearestChicken();

		if (chicken != null && canReach(getPosition(chicken))) {
			atNPC(chicken, 2);
			return random(4000, 5000);
		} else
			return random(2500, 3500);
	}

	// stolen from eP by way of ChrisID
	private NPC findNearestChicken() {
		NPC near = null;

		for (NPC npc : getNPCs()) {
			if (npc == null || inCombat(npc))
				continue;
			if (npc.npcDescription != null && npc.npcDescription.npcName.equals("Chicken")) {
				if (near == null)
					near = npc;
				else if (distanceTo(getPosition(npc)) < distanceTo(getPosition(near)))
					near = npc;
			}
		}

		return near;
	}

	// only store the most recent spawn
	public void itemSpawned(ItemEvent evt) {
		if (!canReach(evt.x, evt.y) || distanceTo(evt.x, evt.y) > 10)
			return;

		String itemName = getItemName(evt.item.itemID);
		if (itemName.equalsIgnoreCase("bones")) {
			bones = evt.item;
			bones.x = evt.x;
			bones.y = evt.y;
		} else if (itemName.equalsIgnoreCase("feather")) {
			feathers = evt.item;
			feathers.x = evt.x;
			feathers.y = evt.y;
		}
	}

	public void serverMessage(String msg) {
		if (msg.indexOf("Welcome to RuneScape") != -1)
			setFightMode = true;
		if (msg.indexOf("you are dead") != -1) {
			log("You died. This script sucks. Shutting down.");
			shutdown();
		}
	}

	private int getInterfaceByName(String name) {
		for (String s : INTERFACE_IDS)
			if (s.split(":")[0].equalsIgnoreCase(name))
				return Integer.parseInt(s.split(":")[1]);
		return -1;
	}

}
