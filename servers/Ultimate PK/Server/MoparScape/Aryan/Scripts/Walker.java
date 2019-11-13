import java.lang.reflect.Field;


/**
 * A generic walker.
 * This is pretty nasty code now.
 * Reflection was very much not meant for this...
 *
 * @author pd
 */
public class Walker extends Script {

	public static String[] commands = {"walk"};

	public static final String[] LUMBRIDGE_TO_DRAYNOR = new String[]{
			"EXIT_LUMBRIDGE_CASTLE",
			"LUMBRIDGE_FOREST",
			"ROAD_TO_DRAYNOR_BANK"
	};

	public static final String[] LUMBRIDGE_TO_WIZARDTOWER = new String[]{
			"EXIT_LUMBRIDGE_CASTLE",
			"LUMBRIDGE_FOREST",
			"WIZARDTOWER_BRIDGE"
	};

	public static final String[] LUMBRIDGE_TO_VARROCK = new String[]{
			"EXIT_LUMBRIDGE_CASTLE",
			"CROSS_LUMBRIDGE_BRIDGE",
			"LUMBRIDGE_EAST_TO_VARROCK_WALL",
			"VARROCK_WALL_TO_VARROCK_SOUTH_GATE",
			"VARROCK_SOUTH_GATE_TO_CENTER"
	};

	// !NAME = reversePath(NAME)
	public static final String[] DRAYNOR_TO_WIZARDTOWER = new String[]{
			"!ROAD_TO_DRAYNOR_BANK",
			"WIZARDTOWER_BRIDGE"
	};

	public static final String[] DRAYNOR_TO_FALADOR = new String[]{
			"DRAYNOR_BANK_TO_FALADOR_WALL",
			"FALADOR_WALL_TO_CROSSROADS",
			"FALADOR_CROSSROADS_TO_BANK"
	};

	public static final String[] DRAYNOR_TO_RIMMINGTON = new String[]{
			"DRAYNOR_BANK_TO_FALADOR_WALL",
			"FALADOR_WALL_TO_CROSSROADS",
			"FALADOR_CROSSROADS_TO_RIMMINGTON"
	};

	public static final String[] FALADOR_TO_RIMMINGTON = new String[]{
			"!FALADOR_CROSSROADS_TO_BANK",
			"FALADOR_CROSSROADS_TO_RIMMINGTON"
	};

	private Tile[] path;

	public boolean start(String cmd, String[] args) {
		if (args.length != 2) {
			log("Usage: /walk(from, to)");
			dumpPaths();
			return false;
		}

		path = findPath(args[0].toUpperCase(), args[1].toUpperCase());
		if (path == null) {
			log("Unsupported path (" + args[0] + " to " + args[1] + ")");
			dumpPaths();
			return false;
		}

		return true;
	}

	public long run(long ticks) {
		if (distanceTo(path[path.length - 1]) <= 2) {
			log("Done!");
			return -1;
		}

		boolean walked = walkPath(path);
		if (!walked) {
			log("Argh, walk path fucked up, trying again");
			return 2000;
		} else
			return random(1000, 2500);
	}

	/**
	 * Accepts the old-style of Tile[] paths, too;
	 * but does so quietly, discouraging use of them.
	 */
	private Tile[] findPath(String from, String to) {
		Field f;
		Tile[] p = null;

		// first, look for the exact name
		try {
			f = this.getClass().getField(from + "_TO_" + to);
			if (f != null) {
				if (f.getType().getName().equals("[Ljava.lang.String;"))
					p = createPath((String[]) f.get(null));
				else if (f.getType().getName().equals("[LTile;"))
					p = (Tile[]) f.get(null);

				return p;
			}
		} catch (Exception e) {
		}

		// try the names in reverse, if we need to
		try {
			f = this.getClass().getField(to + "_TO_" + from);
			if (f != null) {
				if (f.getType().getName().equals("[Ljava.lang.String;"))
					p = createPath((String[]) f.get(null));
				else if (f.getType().getName().equals("[LTile;"))
					p = reversePath((Tile[]) f.get(null));

				return reversePath(p);
			}
		} catch (Exception e) {
		}

		return null;
	}

	private Tile[] createPath(String[] parts) {
		Tile[] finalPath = null;

		// impossible?
		if (parts.length == 0)
			return null;

		for (int i = 0; i < parts.length; i++) {
			Tile[] nextPart = getPath(parts[i]);
			if (nextPart == null)
				return null;

			finalPath = chainPaths(finalPath, nextPart);
		}

		return finalPath;
	}

	/**
	 * There should be some magic to determine whether it's a Tile[]
	 * or a String[] we're looking for (preferably not with a prefix
	 * again, that's ugly), and then a recursive call to findPath()
	 * would obtain a nested String[] path.
	 */
	private Tile[] getPath(String name) {
		Field f;
		Tile[] p = null;
		boolean reverse = false;

		if (name.startsWith("!")) {
			reverse = true;
			name = name.substring(1);
		}

		try {
			f = this.getClass().getField(name);
			if (f != null)
				p = (Tile[]) f.get(null);
		} catch (Exception e) {
			log(e.toString());
			e.printStackTrace();
		}

		if (p != null)
			return (reverse ? reversePath(p) : p);

		return null;
	}

	private void dumpPaths() {
		Class c = this.getClass();
		Field[] fields = c.getFields();

		log("Supported paths (also available reversed):");
		for (int i = 0; i < fields.length; i++) {
			if (fields[i].getType().getName().equals("[Ljava.lang.String;")
					&& fields[i].getName().matches("\\w+_TO_\\w+")) {
				String[] places = fields[i].getName().split("_TO_");
				log("  * " + wordCase(places[0]) + " to " + wordCase(places[1]));
			}
		}
	}

	// quick hack to combine arrays
	private Tile[] chainPaths(Tile[] one, Tile[] two) {
		if (one == null)
			return two;

		Tile[] newPath = new Tile[one.length + two.length];
		System.arraycopy(one, 0, newPath, 0, one.length);
		System.arraycopy(two, 0, newPath, one.length, two.length);
		return newPath;
	}

	private String wordCase(String str) {
		return str.substring(0, 1).toUpperCase() +
				str.substring(1).toLowerCase();
	}


	/**
	 * PATHS:
	 * when in doubt, use a lot of tiles.
	 * <p/>
	 * it'd be nice to break these out into a file, as exemplar has
	 * suggested in the past. (read: he can do it)
	 * <p/>
	 * these are the small sections which will later be combined into
	 * full paths.
	 * <p/>
	 * i hate this language.
	 */

	// exit the lumbridge castle area, get to where our other
	// paths start
	public static final Tile[] EXIT_LUMBRIDGE_CASTLE = new Tile[]{
			new Tile(3223, 3218), new Tile(3229, 3218), new Tile(3234, 3225)
	};

	// navigate the lumbridge forest; this goes from lumbridge
	// outside the castle entrance to the road east of draynor
	public static final Tile[] LUMBRIDGE_FOREST = new Tile[]{
			new Tile(3234, 3226), new Tile(3231, 3230), new Tile(3227, 3234),
			new Tile(3219, 3237), new Tile(3210, 3239), new Tile(3201, 3239),
			new Tile(3192, 3244), new Tile(3185, 3244), new Tile(3177, 3242),
			new Tile(3169, 3238), new Tile(3160, 3238), new Tile(3154, 3233),
			new Tile(3145, 3233), new Tile(3140, 3226), new Tile(3132, 3223),
			new Tile(3125, 3217), new Tile(3121, 3212),
	};

	// walk from the southern tip of the road east of draynor
	// to inside the draynor bank
	public static final Tile[] ROAD_TO_DRAYNOR_BANK = new Tile[]{
			new Tile(3122, 3210), new Tile(3118, 3216), new Tile(3115, 3220),
			new Tile(3110, 3224), new Tile(3103, 3227), new Tile(3100, 3233),
			new Tile(3098, 3238), new Tile(3099, 3245), new Tile(3093, 3243)
	};

	// walk along the wizard tower bridge to the tower entrance
	public static final Tile[] WIZARDTOWER_BRIDGE = new Tile[]{
			new Tile(3122, 3204), new Tile(3122, 3197), new Tile(3117, 3193),
			new Tile(3113, 3189), new Tile(3114, 3181), new Tile(3114, 3173),
			new Tile(3109, 3169)
	};

	// exit draynor bank and walk to the opening in the wall
	// that separates draynor from the falador/rimmington area
	public static final Tile[] DRAYNOR_BANK_TO_FALADOR_WALL = new Tile[]{
			new Tile(3093, 3243), new Tile(3088, 3248), new Tile(3083, 3253),
			new Tile(3081, 3260), new Tile(3077, 3266), new Tile(3074, 3271),
			new Tile(3073, 3276), new Tile(3070, 3277)
	};

	// from falador wall opening to the falador crossroads
	public static final Tile[] FALADOR_WALL_TO_CROSSROADS = new Tile[]{
			new Tile(3070, 3277), new Tile(3060, 3277), new Tile(3053, 3277),
			new Tile(3045, 3275), new Tile(3036, 3277), new Tile(3028, 3277),
			new Tile(3020, 3275), new Tile(3012, 3277), new Tile(3008, 3278)
	};

	// from falador crossroads to next to falador east bank
	public static final Tile[] FALADOR_CROSSROADS_TO_BANK = new Tile[]{
			new Tile(3008, 3278), new Tile(3006, 3286), new Tile(3007, 3294),
			new Tile(3004, 3301), new Tile(3006, 3308), new Tile(3007, 3316),
			new Tile(3007, 3324), new Tile(3007, 3332), new Tile(3006, 3341),
			new Tile(3006, 3348), new Tile(3006, 3356), new Tile(3006, 3363)
	};

	// from falador crossroads to center of rimmington mine
	public static final Tile[] FALADOR_CROSSROADS_TO_RIMMINGTON = new Tile[]{
			new Tile(3008, 3278), new Tile(3000, 3277), new Tile(2994, 3272),
			new Tile(2987, 3267), new Tile(2982, 3263), new Tile(2978, 3257),
			new Tile(2977, 3250), new Tile(2975, 3244), new Tile(2976, 3240)
	};

	// from west side of southern lumbridge bridge to the east side
	// of the river 
	public static final Tile[] CROSS_LUMBRIDGE_BRIDGE = new Tile[]{
			new Tile(3234, 3225), new Tile(3241, 3225), new Tile(3249, 3225),
			new Tile(3260, 3228)
	};

	// north from east side of lumbridge river to wall south of varrock
	public static final Tile[] LUMBRIDGE_EAST_TO_VARROCK_WALL = new Tile[]{
			new Tile(3260, 3228), new Tile(3260, 3235), new Tile(3259, 3243),
			new Tile(3255, 3249), new Tile(3251, 3254), new Tile(3250, 3263),
			new Tile(3248, 3269), new Tile(3245, 3275), new Tile(3240, 3280),
			new Tile(3238, 3287), new Tile(3239, 3296), new Tile(3239, 3303),
			new Tile(3244, 3309), new Tile(3248, 3314), new Tile(3253, 3320),
			new Tile(3260, 3324), new Tile(3266, 3325), new Tile(3268, 3330)
	};

	// from wall south of varrock to varrock's southern entrance
	public static final Tile[] VARROCK_WALL_TO_VARROCK_SOUTH_GATE = new Tile[]{
			new Tile(3268, 3330), new Tile(3261, 3333), new Tile(3254, 3334),
			new Tile(3247, 3336), new Tile(3240, 3336), new Tile(3232, 3337),
			new Tile(3227, 3341), new Tile(3226, 3349), new Tile(3224, 3355),
			new Tile(3218, 3360), new Tile(3217, 3366), new Tile(3213, 3370),
			new Tile(3211, 3375), new Tile(3211, 3380)
	};

	// from varrock's southern entrance to the fountain in the center
	public static final Tile[] VARROCK_SOUTH_GATE_TO_CENTER = new Tile[]{
			new Tile(3211, 3380), new Tile(3211, 3389), new Tile(3211, 3399),
			new Tile(3211, 3407), new Tile(3211, 3416), new Tile(3213, 3425)
	};
}
