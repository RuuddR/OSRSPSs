import java.util.ArrayList;
import java.util.Iterator;


/**
 * This only solves from inside the cave.
 *
 * @author RegeX
 *         Modified by Odie5533 on 11/23/05
 */
public class FrogHandler extends RandomHandler {

	private final Tile CAVE_NW = new Tile(2453, 4790);
	private final Tile CAVE_SE = new Tile(2477, 4768);

	public NPC getFrogPrince() {
		ArrayList<NPC> npcs = findNPCsByName("Frog");
		Iterator<NPC> it = npcs.iterator();
		int[] frogs = new int[]{0, 0};
		NPC frog1 = null;
		NPC frog2 = null;
		while (it.hasNext()) {
			NPC npc = it.next();
			if (frog1 == null)
				frog1 = npc;
			if (frog2 == null && frog1.npcDescription.npcType != npc.npcDescription.npcType)
				frog2 = npc;
			// frog1 never null from above asignment
			// if (frog1 != null && frog1.npcDescription.npcType == npc.npcDescription.npcType)
			if (frog1.npcDescription.npcType == npc.npcDescription.npcType)
				frogs[0]++;
			if (frog2 != null && frog2.npcDescription.npcType == npc.npcDescription.npcType)
				frogs[1]++;
		}
		if (frogs[0] == 0 || frogs[1] == 0)
			return null;
		if (frogs[0] > frogs[1])
			return frog2;
		else
			return frog1;
	}

	public long run(long ticks) {
		if (inArea(CAVE_NW, CAVE_SE)) {
			NPC talkToNpc = getFrogPrince();

			if (getDialogText().length() > 0) {
				continueDialogInterface();
				return random(500, 1000);
			}

			if (talkToNpc != null) {
				if (distanceTo(getPosition(talkToNpc)) > 3) {
					if (isMoving())
						return random(100, 200);
					walk(getPosition(talkToNpc));
					return random(500, 1000);
				} else {
					log("Talking to frog!");
					atNPC(talkToNpc, "Talk-to");
					return random(500, 1000);
				}
			}
		}
		return -1;
	}

}
