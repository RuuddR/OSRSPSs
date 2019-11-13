/**
 * Cheap hack to handle the Lost and Found random.
 * It just keeps clicking the northern appendage until it gets out.
 * This should be handled in a much more rational manner.
 *
 * @author pd
 */
public class LostAndFound extends RandomHandler {
	private final Tile MYPOS = new Tile(2338, 4747);
	private final int OBJECT_APPENDAGE = 8995;

	public long run(long last) {
		if (getDialogText().length() > 0
				&& getDialogText().contains("Abyssal services")) {
			log("Got out of Lost and Found area.");
			continueDialogInterface();
			return random(2000, 3000);
		}

		if (distanceTo(MYPOS) > 2)
			return -1;

		int obj = findObject(OBJECT_APPENDAGE);
		if (obj == -1)
			return 500;
		atObject(obj, 1);
		return random(4000, 5000); // go slowly, like a stupid human.
	}
}
