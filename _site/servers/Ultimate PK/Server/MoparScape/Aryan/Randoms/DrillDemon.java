/**
 * Drill demon random event handler,
 * still not fully functional.
 * c
 *
 * @author Bruncle
 */
public class DrillDemon extends RandomHandler {
	private int counter;
	private String[] mats;

	public void DrillDemon() {
		mats = null;
		counter = 0;
	}

	public long run(long ticks) {
		try {
			if (inArea(new Tile(3157, 4822), new Tile(3168, 4817))) {
				if (mats == null) {
					log("Got Drill Demon random");
					mats = new String[4];
				}

				// still haven't worked out what each of the mats do
				if (getDialogText().length() > 0 && mats[3] == null) {
					continueDialogInterface();
					return 500;
				}

				// worked out what each of the mats do
				if (mats[3] != null) {
					String workout = getDialogText();
					if (workout.length() == 0)
						return 2000;
					for (int i = 0; i < 4; i++) {
						if (workout.contains(mats[i])) {
							log("Sergeant told us to do " + mats[i] + " so off we go");
							int matID = findObject(10076 + i);
							atObject(matID, 1);
							return random(6000, 10000);
						}
					}
					if (workout.contains(" you actually")) { // did all exercises
						continueDialogInterface();
						log("Woot finished the random");
						mats = null;
						counter = 0;
						return 500;
					}
				}

				if (mats[3] == null) {
					if (myPlayer().anim == -1) {
						int matID = findObject(10076 + counter);
						log("Working out what mat # " + (counter + 1) + " is for");
						atObject(matID, 1);
						return random(2000, 4000);
					} else {
						// we've already solved this one, save double logging
						if (mats[counter] != null)
							return 500;
						switch (myPlayer().anim) {
							case 2764:
								mats[counter] = " jog";
								break;
							case 2763:
								mats[counter] = " sit ups";
								break;
							case 2761:
								mats[counter] = " star jumps";
								break;
							case 2762:
								mats[counter] = " push ups";
								break;
							default: // XXX - what if?
								break;
						}
						log("Mat # " + (counter + 1) + " is for " + mats[counter]);
						counter++;
						return 8000;
					}
				}
			}
		} catch (Exception e) {
		}
		return -1;
	}
}
