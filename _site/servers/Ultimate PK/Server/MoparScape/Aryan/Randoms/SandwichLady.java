/**
 * Handles Sandwich lady.
 *
 * @author Kamu
 */
public class SandwichLady extends RandomHandler
		implements InterfaceListener, EntityMessageListener {

	private final String name = "Sandwich lady";
	private final int SANDWICH_INTERFACE = 16135;
	private final int SANDWICH_TEXT_INTERFACE = 16145;
	private final String[] SANDWICHES = {
		"BAGUETTE", "BREAD ROLL", "CHOCOLATE BAR", "KEBAB",
		"PIE", "SQUARE SANDWICH", "TRIANGLE SANDWICH"
	};
	private final int[] SANDWICHES_TEXTURES = {
		10726, 10727, 10728, 10729, 10730, 10731, 10732
	};

	private int[] tray;
	private int sandwich;
	private NPC lady;
	private boolean solved;
	
	public SandwichLady() {
		EventManager.addInterfaceListener(this);
		EventManager.addEntityMessageListener(this);
		tray = new int[7];
		sandwich = -1;
		solved = false;
	}

	public long run(long ticks) {
		if (getInterface() != SANDWICH_INTERFACE
				&& !solved && getDialogText().length() == 0) {
			if (lady != null && lady.index != -1 && getNPCs()[lady.index] == lady) {
				log("Talking to Sandwich Lady");
				atNPC(lady, "Talk-to");
				return random(5000,6000);
			}
		} else if (lady != null && getInterface() != SANDWICH_INTERFACE
				&& !solved && getDialogText().length() > 0) {
			continueDialogInterface();
			return random(1000,3000);
		} else if (getInterface() == SANDWICH_INTERFACE) {
			if (sandwich != -1 && tray[0] != -1) {
				log("Grabbing " + SANDWICHES[sandwich]);
				int sandwichToGrab = -1;
				for (int i = 0; i < tray.length; i++) {
					if(sandwich == tray[i])
						sandwichToGrab = i;
				}
				
				if (sandwichToGrab != -1) {
					atInterface(16137 + sandwichToGrab, 1);
					solved = true;
					return random(3000,5000);
				} else {
					log("Something screwed up!");
					return 500;
				}
			}
		} else if (getInterface() != SANDWICH_INTERFACE && solved) {
			log("Solved Sandwich lady, silly bitch.");
			solved = false;
			tray = new int[7];
			sandwich = -1;
			lady = null;
		}
		return -1;
	}

	public void entityMessage(EntityEvent evt) {
		Entity ent = evt.entity;
		String message = evt.message;

		if (myPlayer() == null || myPlayer().playerName == null)
			return;

		if (ent instanceof NPC) {
			NPC npc = (NPC) ent;
			if (npc.npcDescription == null || npc.npcDescription.npcName == null)
				return;
			if (message.indexOf(myPlayer().playerName) != -1) {
				if (name.equalsIgnoreCase(npc.npcDescription.npcName)) {
					log("Got the sandwich lady");
					lady = npc;
				}
			}
		}
	}

	public void newInterface(RSInterface rsi) {
		if (rsi.textureID >= 10726 && rsi.textureID <= 10732) {
			for (int i = 0; i < SANDWICHES.length; i++)
				if (SANDWICHES_TEXTURES[i] == rsi.textureID)
					tray[rsi.interfaceID - 16137] = i;
		}

		if (rsi.interfaceID == SANDWICH_TEXT_INTERFACE)
			if (rsi.message.contains("for free!")) {
				for (int i = 0; i < SANDWICHES.length; i++)
					if (SANDWICHES[i].equalsIgnoreCase(rsi.message.substring(7, rsi.message.length() - 10))) {
							sandwich = i;
							log("Yummy, we can have a " + SANDWICHES[i] + ".");
					}
			}
	}
}
