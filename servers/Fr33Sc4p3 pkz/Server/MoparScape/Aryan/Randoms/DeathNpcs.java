/**
 * Kills the bot when we run into an npc random event unable to be handled.
 *
 * @author Sean
 */
public class DeathNpcs extends RandomHandler implements EntityMessageListener {
	public boolean needToQuit = false;
	public static final String[] DEATH_NPCS = new String[]{
			"Dr Ford"
	};

	public DeathNpcs() {
		EventManager.addEntityMessageListener(this);
	}

	public void entityMessage(EntityEvent evt) {
		Entity ent = evt.entity;
		String message = evt.message;

		if (myPlayer() == null || myPlayer().playerName == null)
			return;

		if (ent instanceof NPC) {
			NPC npc = (NPC) ent;
			if (message.indexOf(myPlayer().playerName) != -1) {
				EntityDef def = npc.npcDescription;
				if (def == null || def.npcName == null)
					return;
				for (String name : DEATH_NPCS) {
					if (name.equalsIgnoreCase(def.npcName)) {
						log("Unhandleable random: " + def.npcName + " (" + message + ")");
						needToQuit = true;
					}
				}
			}
		}
	}

	public long run(long ticks) {
		if (needToQuit) {
			log("Quitting...");
			shutdown();
		}
		return -1;
	}

}
