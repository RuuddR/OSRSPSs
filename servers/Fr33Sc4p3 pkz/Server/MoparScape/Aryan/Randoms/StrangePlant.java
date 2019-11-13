/**
 * Another inelegant anti-random.
 * Doesn't care if it's for us or not; just keeps picking the
 * thing until it disappears.
 *
 * I didn't want to rely on state booleans.
 *
 * @author pd
 */
public class StrangePlant extends RandomHandler
		implements ServerMessageListener {

	public StrangePlant() {
		EventManager.addServerMessageListener(this);
	}

	public long run(long last) {
		NPC plant = findNPCByName("Strange plant");
		if (plant == null)
			return -1;

		// ignore it if it's too far off
		if (distanceTo(getPosition(plant)) > 5)
			return -1;

		// be patient.
		// i suppose it's possible it could turn hostile within
		// this time, but i doubt it.
		atNPC(plant, "Pick");
		return random(5000, 7000);
	}

	public void serverMessage(String msg) {
		if (msg.indexOf("You pick the fruit") != -1)
			log("Handled Strange Plant random.");
	}
}
