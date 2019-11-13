public class LampHandler extends RandomHandler {

	private int skillID;
	private boolean chosen;

	public LampHandler() {
		Configuration c = new Configuration(rs.configFile);
		String name = c.get("lamp");
		skillID = findIDByName(name);
	}
	
	private void useLamp(int index) {
		if (getInterface() != Constants.INTERFACE_LAMP) {
			log("Using lamp @ " + index);
			atSpecialInventoryItem(Constants.ITEM_LAMP, index, 1);
		} else {
			if (!chosen) {
				atInterface(skillID, 1);
				chosen = true;
			} else {
				atInterface(Constants.INTERFACE_LAMP_CONFIRM, 1);
				chosen = false;
			}
		}
	}

	private void dropLamp(int index) {
		log("Dropping lamp @ " + index);
		dropItem(Constants.ITEM_LAMP, index);
	}

	public long run(long ticks) {
		if (inventoryCount(Constants.ITEM_LAMP) > 0) {
			int index = getInventoryIndex(Constants.ITEM_LAMP);
			if (skillID != -1)
				useLamp(index);
			else
				dropLamp(index);
			return 2000;
		} else
			return -1;
	}

	private int findIDByName(String name) {
		for (String n : Constants.INTERFACES_LAMP_SKILLS)
			if (n.split(":")[0].equalsIgnoreCase(name))
				return Integer.parseInt(n.split(":")[1]);
		return -1;
	}
}
