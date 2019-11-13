/**
 * @author Bruncle
 */
public class QuizShow extends RandomHandler {

	private final int OPTION1_INTERFACE = 14197;
	private final int OPTION2_INTERFACE = 14198;
	private final int OPTION3_INTERFACE = 14199;
	private final int BANNER_INTERFACE = 14200;
	private final int REWARD_INTERFACE = 2461;
	private final int REWARD2_INTERFACE = 2462;
	private final Tile QUIZ_TILE = new Tile(1952, 4764);
	
	private boolean activated;

	public QuizShow() {
		activated = false;
	}

	public long run(long ticks) {
		if (getPosition(myPlayer()).equals(QUIZ_TILE)) {
			if (!activated) {
				log("Got QuizShow Random.");
				activated = true;
			}
		
			if (RSInterface.forID(BANNER_INTERFACE).message.startsWith("Pick ")) {
				RSInterface option1 = RSInterface.forID(OPTION1_INTERFACE);
				RSInterface option2 = RSInterface.forID(OPTION2_INTERFACE);
				RSInterface option3 = RSInterface.forID(OPTION3_INTERFACE);
				if (option1.textureID == (option2.textureID + 1)) {
					atInterface(OPTION3_INTERFACE, 1);
					log("answer option 3 (1)");
					return random(3000,3500);
				}
				if (option1.textureID == (option3.textureID + 1)) {
					atInterface(OPTION2_INTERFACE, 1);
					log("answer option 2 (1)");
					return random(3000,3500);
				}
				if (option2.textureID == (option1.textureID + 1)) {
					atInterface(OPTION3_INTERFACE, 1);
					log("answer option 3 (2)");
					return random(3000,3500);
				}
				if (option2.textureID == (option3.textureID + 1)) {
					atInterface(OPTION1_INTERFACE, 1);
					log("answer option 1 (1)");
					return random(3000,3500);
				}
				if (option3.textureID == (option2.textureID + 1)) {
					atInterface(OPTION1_INTERFACE, 1);
					log("answer option 1 (2)");
					return random(3000,3500);
				}
				if (option3.textureID == (option1.textureID + 1)) {
					atInterface(OPTION2_INTERFACE, 1);
					log("answer option 2 (2)");
					return random(3000,3500);
				}
			}

			if (RSInterface.forID(REWARD2_INTERFACE).message.contains("Box")) {
				atInterface(REWARD_INTERFACE, 1);
				log("Solved quiz random");
				activated = false;
				return random(3000,5000);
			}
		}
		return -1;
	}

}
