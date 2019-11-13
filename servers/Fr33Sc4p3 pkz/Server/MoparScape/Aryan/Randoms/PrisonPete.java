/**
 * Gets out of that bastard cat's ScapeRune prison.
 *
 * @author Kamu
 */
public class PrisonPete extends RandomHandler
		implements InterfaceListener {

	private final Tile PRISON_NW = new Tile(2086, 4440);
	private final Tile PRISON_SE = new Tile(2109, 4418);
	private final Tile TILE_CENTER = new Tile(2095,4428);
	
	private final int LEVER = 10817;
	private final int LEVER_INTERFACE = 16129;
	
	private final String[] ANIMALS = { "DOG", "CAT", "BUNNY", "GOAT" };
	private final int[] ANIMALS_TYPE = { 3119, 3120, 3121, 3122 };
	private final int[] ANIMALS_INTERFACE = { 10749, 10750, 10751, 10752 };
	private final String PRISON_PETE = "Prison Pete";
	
	private int targetAnimal;
	private boolean activated;
	
	public PrisonPete() {
		EventManager.addInterfaceListener(this);
		targetAnimal = -1;
		activated = false;
	}

	public long run(long ticks) {
		if (!inArea(PRISON_NW, PRISON_SE)) {
			activated = false;
			return -1;
		}
		
		if (!activated) {
			log("Got Prison Pete random.");
			activated = true;
		}

		if (getDialogText().length() > 0) {
			if (getDialogText().contains("right one")) {
				continueDialogInterface();
				return random(5000,6000);
			}
			continueDialogInterface();
			return random(500,750);
		}

		if (getInterface() == LEVER_INTERFACE) {
			closeInterface();
			return random(500,750);
		}
		
		if (getInvCountPrefix("Prison key") > 0) {
			NPC pp = findNPCByName(PRISON_PETE);
			if(pp != null) {
				atNPC(pp, 1);	
				targetAnimal = -1;
				return random(3000,6000);						
			} else {
				walk(TILE_CENTER);
				return random(3000, 6000);
			}
		}
		
		if (targetAnimal == -1) {
			int lever = findObject(LEVER);
			if(lever != -1) {
				atObject(lever, 1);
				return random(6000,8000);			
			}
		} else {
			NPC animal = findNearestNPCByType(ANIMALS_TYPE[targetAnimal]);
			if(animal != null) {
				atNPC(animal, 1);
				return random(6000,10000);				
			}
		}
		return random(1000, 2000);
	}

	public void newInterface(RSInterface rsi) {
		if (rsi.textureID >= 10749 && rsi.textureID <= 10752 && targetAnimal == -1) {
			for (int i = 0; i < ANIMALS.length; i++)
				if(ANIMALS_INTERFACE[i] == rsi.textureID)
					targetAnimal = i;
			log("Target balloon animal is " + ANIMALS[targetAnimal]);
		}
	}
}
