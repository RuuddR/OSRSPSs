/**
 * Issues:
 * - Assumes you won't have both a pickaxe and an axe.
 * If you do, pickaxes are preferred and axes will
 * probably fail.
 * - It's possible for you to pick up someone else's head,
 * if for some reason you have a headless handle in your
 * inventory. So, erm, don't.
 *
 * @author pd
 */
public class AxeHandler extends RandomHandler
		implements ItemSpawnListener, ServerMessageListener {

	private Item droppedItem, lostHead;
	private boolean grabHead, dropping, dropped, cancelAction;
	private String droppedName;
	private int droppedCount;

	public AxeHandler() {
		EventManager.addItemSpawnListener(this);
		grabHead = false;
		dropped = false;
		dropping = false;
	}

	public long run(long ticks) {
		if (cancelAction) {
			cancelAction = false;
			cancelAction();
			return random(1000, 2000);
		}
		if (getInvCountSuffix(" handle") > 0) {
			if (getTab() != 3) {
				selectTab(3);
				return random(500, 1000);
			}
			if (getInvCountSuffix(" head") > 0) {
				if (isItemSelected() && getSelectedItemName().endsWith(" handle")) {
					log("Attaching handle to head");
					useWithItem(getInventory()[locateInvSuffix(" head")],
							locateInvSuffix(" head"));
				} else {
					log("Selecting handle");
					selectItem(getInventory()[locateInvSuffix(" handle")],
							locateInvSuffix(" handle"));
				}
				return random(1000, 2000);
			}
			if (grabHead) {
				if (isMoving()) // make sure we're not already on the way to grab it
					return 1000;
				if (inventoryCount() >= 28) { // drop an item if we have too many
					if (dropped) // already sent drop, waiting for it to go
						return 500;
					for (int i = 0; i < getInventory().length; i++) {
						if (getInventory()[i] < 0)
							continue;
						String itemName = getItemName(getInventory()[i]).toLowerCase();
						if (!itemName.endsWith(" handle") && !itemName.endsWith(" head")) {
							dropping = true;
							droppedName = itemName;
							droppedCount = getInvCountSuffix(droppedName);
							log("Dropping item: " + droppedName + ", to make room for axe head");
							dropItem(getInventory()[i], i);
							return random(1000, 2000);
						}
					}
				}
				log("Picking up head: " + getItemName(lostHead.itemID));
				takeItem(lostHead.itemID, lostHead.x, lostHead.y);
				return random(3000, 6000);
			}
		}

		if (dropped) {
			if (isMoving())
				return 500;
			if (getInvCountSuffix(droppedName) == droppedCount) { // assume we've picked it up
				log(" # Picked up " + droppedName);
				dropped = false;
				return -1;
			}
			log("Picking up item: " + droppedName);
			takeItem(droppedItem.itemID, droppedItem.x, droppedItem.y);
			return random(3000, 6000);
		}

		return -1;
	}

	public void itemSpawned(ItemEvent evt) {
		if (dropping && droppedName.equalsIgnoreCase(getItemName(evt.item.itemID))) {
			log("Dropped: " + getItemName(evt.item.itemID));
			droppedItem = evt.item;
			droppedItem.x = evt.x;
			droppedItem.y = evt.y;
			dropped = true;
		}
		if (getItemName(evt.item.itemID).toLowerCase().endsWith(" head") && getInvCountSuffix(" handle") > 0) {
			log("Lost: " + getItemName(evt.item.itemID));
			lostHead = evt.item;
			lostHead.x = evt.x;
			lostHead.y = evt.y;
			grabHead = true;
		}
	}

	public void serverMessage(String message) {
		if (message.indexOf("carefully attach") != -1)
			cancelAction = true;
	}

}
