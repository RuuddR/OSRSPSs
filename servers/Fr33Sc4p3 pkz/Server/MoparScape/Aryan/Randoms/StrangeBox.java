import java.util.HashMap;


/**
 * @author RegeX
 */
public class StrangeBox extends RandomHandler implements InterfaceListener {

	private String boxStr;

	private HashMap<String, RSInterface> answers;
	private HashMap<String, String> hm;

	public StrangeBox() {
		hm = new HashMap<String, String>();
		answers = new HashMap<String, RSInterface>();
		EventManager.addInterfaceListener(this);
	}

	private void openBox() {
		if (getInterface() == Constants.INTERFACE_STRANGE_BOX)
			return;

		int index = getInventoryIndex(Constants.ITEM_STRANGE_BOX);
		if (index != -1) {
			log("Opening strange box... (" + index + " inventory index)");
			atSpecialInventoryItem(Constants.ITEM_STRANGE_BOX, index, 1);
		}
	}

	public long run(long ticks) {
		if(getInterface() != -1 && getInterface() != Constants.INTERFACE_STRANGE_BOX && inventoryCount(Constants.ITEM_STRANGE_BOX) > 0) {
			log("Closing interface to open strange box");
			closeInterface();
			return 750 + random(500, 1000);
		}
		else if (getInterface() == Constants.INTERFACE_STRANGE_BOX) {
			if (boxStr != null && hm.get(boxStr) != null) {
				String answerStr = hm.get(boxStr);
				log("Found answer: " + answerStr);
				if (answers.get(answerStr) != null) {
					log("Executing answer!");
					RSInterface rsi = answers.get(answerStr);
					atInterface(rsi.interfaceID - 3, 1);
					return 5000;
				}
			}
		} else if (inventoryCount(Constants.ITEM_STRANGE_BOX) > 0) {
			openBox();
			return 5000;
		}
		return -1;
	}

	public void newInterface(RSInterface rsi) {
		if (rsi.message != null && rsi.message.matches(
				"(Which shape|What colour) is (the [A-Za-z]+|[A-Za-z]+)\\?")) {
			boolean byColor = rsi.message.indexOf("colour") != -1;
			if (byColor) {
				boxStr = rsi.message.substring(rsi.message.indexOf("the") + 4,
						rsi.message.length() - 1).trim().toUpperCase();
			} else
				boxStr = rsi.message.substring(rsi.message.indexOf("is") + 3,
						rsi.message.length() - 1).trim().toUpperCase();
			log((byColor ? "Colour" : "Shape") + " value of " + boxStr +
					" is our target");
		} else if (rsi.message != null) { // Incoming commands
			answers.put(rsi.message.trim().toUpperCase(), rsi);
		}

		// Incoming textures
		if (rsi.textureID >= 3063 && rsi.textureID <= 3091) {
			String name = nameForTexture(rsi.textureID);

			if (name != null) {
				String[] details = name.split(" ");
				details[0] = details[0].trim();
				details[1] = details[1].trim();
				log(details[0] + "->" + details[1]);
				hm.put(details[0].toUpperCase(), details[1].toUpperCase());
				hm.put(details[1].toUpperCase(),
						details[0].toUpperCase()); // Do it twice for color/shape
			}
		}
	}

	private String nameForTexture(int textureID) {
		for (String s : Constants.TEXTURES_STRANGE_BOX) {
			int type = Integer.parseInt(s.split(":")[0]);
			String name = s.split(":")[1];

			if (textureID == type)
				return name.toUpperCase();
		}
		return null;
	}

}
