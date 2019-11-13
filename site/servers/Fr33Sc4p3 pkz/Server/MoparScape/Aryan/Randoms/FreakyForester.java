import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * @author pd
 */
public class FreakyForester extends RandomHandler
		implements ItemSpawnListener {

	private static final Tile FIELD_NW = new Tile(2596, 4780);
	private static final Tile FIELD_SE = new Tile(2611, 4771);

	private static final String[] PHEASANT_NAMES = {"one", "two", "three", "four"};
	private static final int[] PHEASANT_IDS = {2459, 2460, 2461, 2462};

	private static final int ITEM_PHEASANT = 6178;
	private static final int OBJECT_PORTAL = 8972;

	private int target = -1;
	private Item rawPheasant = null;
	private NPC ff = null;

	private String regex = "(\\w+) tailed";
	private Pattern pattern = Pattern.compile(regex);

	private boolean mayLeave;

	public FreakyForester() {
		EventManager.addItemSpawnListener(this);
	}

	public long run(long ticks) {
		// RegeX: i dont think youre going to get much overhead at all
		if (!inArea(FIELD_NW, FIELD_SE)) {
			ff = null;
			rawPheasant = null;
			target = -1;
			return -1;
		}

		if (ff == null) {
			log("Got Freaky Forester random");
			ff = findNPCByName("Freaky Forester");
		}

		if (getDialogText().length() > 0) {
			if (target == -1) {
				Matcher matcher = pattern.matcher(getDialogText());
				matcher.find();
				if (matcher.group(1) != null) {
					log("Our target is the " + matcher.group(1) + " tailed pheasant");
					target = getTargetByName(matcher.group(1));
				}
			} else if (getDialogText().contains("you may leave"))
					mayLeave = true;

			continueDialogInterface();
			return random(500, 1000);
		} else {
			// i think you talk to him automagically,
			// but just in case.
			if (ff != null && target == -1) {
				atNPC(ff, "Talk-to");
				return random(2000, 3000);
			}
		}

		if (mayLeave) {
			int portal = findObject(OBJECT_PORTAL);
			if (portal != -1) {
				log("All done, leaving through the portal");
				atObject(portal, 1);
				return random(4000, 5000);
			}
		}

		if (rawPheasant != null) {
			if (inventoryCount(ITEM_PHEASANT) == 0) {
				takeItem(ITEM_PHEASANT, rawPheasant.x, rawPheasant.y);
				return random(2000, 3000);
			} else {
				if (!isItemSelected()) {
					selectItem(getInventory()[locateInvSuffix(" pheasant")],
							locateInvSuffix(" pheasant"));
					return random(500, 1000);
				} else {
					useWithNPC(ff);
					return random(2000, 3000);
				}
			}
		}

		if (target != -1) {
			NPC npc = findNPCByType(target);
			log("Attacking the pheasant");
			atNPC(npc, "Attack");
			return random(2000, 3000);
		}

		return -1;
	}

	public void itemSpawned(ItemEvent evt) {
		if (evt.item.itemID == ITEM_PHEASANT) {
			log("The proper raw pheasant spawned on the ground");
			rawPheasant = evt.item;
			rawPheasant.x = evt.x;
			rawPheasant.y = evt.y;
		}
	}

	private int getTargetByName(String name) {
		for (int i = 0; i < PHEASANT_NAMES.length; i++) {
			if (PHEASANT_NAMES[i].equalsIgnoreCase(name))
				return PHEASANT_IDS[i];
		}
		return -1;
	}
}
