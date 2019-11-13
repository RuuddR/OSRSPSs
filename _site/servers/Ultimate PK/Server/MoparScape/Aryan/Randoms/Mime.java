/**
 * @author Morgoth
 */
public class Mime extends RandomHandler {

	private static final int animMimeToMe = 858;

	private static final int animThink = 857;
	private static final int animLaugh = 861;
	private static final int animClimbRope = 1130;
	private static final int animGlassBox = 1131;
	private static final int animCry = 860;
	private static final int animDance = 866;
	private static final int animLeanOnAir = 1129;
	private static final int animGlassWall = 1128;

	private static final int interfaceThink = 6547;
	private static final int interfaceLaugh = 6548;
	private static final int interfaceClimbRope = 6550;
	private static final int interfaceGlassBox = 6553;
	private static final int interfaceCry = 6546;
	private static final int interfaceDance = 6549;
	private static final int interfaceLeanOnAir = 6551;
	private static final int interfaceGlassWall = 6552;

	private boolean watching = true;
	private int mimesAnim = -1;

	public long run(long lastExecuted) {
		NPC Mime = findNPCByName("Mime");
		if (Mime != null) {
			if (watching) {
				if (Mime.anim != -1 && Mime.anim != animMimeToMe) {
					log("Mime found: His anim = " + Mime.anim + ", my anim = " + myPlayer().anim);
					watching = false;
					mimesAnim = Mime.anim;
					return random(6000, 8000);
				}
				return 250;
			} else {
				watching = true;
				switch (mimesAnim) {
					case animThink:
						log("Performing Think");
						atInterface(interfaceThink, 1);
						break;
					case animLaugh:
						log("Performing Laugh");
						atInterface(interfaceLaugh, 1);
						break;
					case animClimbRope:
						log("Performing Climb Rope");
						atInterface(interfaceClimbRope, 1);
						break;
					case animGlassBox:
						log("Performing Glass Box");
						atInterface(interfaceGlassBox, 1);
						break;
					case animCry:
						log("Performing Cry");
						atInterface(interfaceCry, 1);
						break;
					case animDance:
						log("Performing Dance");
						atInterface(interfaceDance, 1);
						break;
					case animLeanOnAir:
						log("Performing Lean On Air");
						atInterface(interfaceLeanOnAir, 1);
						break;
					case animGlassWall:
						log("Performing Glass Wall");
						atInterface(interfaceGlassWall, 1);
						break;
					default:
						log("Mime performed unknown animation " + mimesAnim + ", shutting down");
						shutdown();
						return -1;		// unreached
				}
				return 2000;
			}
		}

		return -1;
	}

}
