public class WelcomeScreen extends RandomHandler {
	public long run(long last) {
		if (rs.altInterfaceID == Constants.INTERFACE_WELCOME_SCREEN) {
			log("Closing welcome screen");
			atInterface(Constants.INTERFACE_WELCOME_BUTTON, 4);
			return random(2000, 3000);
		} else
			return -1;
	}
}
