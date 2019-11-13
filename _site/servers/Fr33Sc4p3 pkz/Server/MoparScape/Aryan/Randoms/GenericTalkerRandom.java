/**
 * @author Sean
 */
public class GenericTalkerRandom extends RandomHandler
		implements EntityMessageListener, InterfaceListener {

	private NPC talkToNpc;
	private RSInterface cont;
	private boolean continueInterface = false;
	private final String[] ignore = {"Sandwich lady", "Freaky Forester"};

	public GenericTalkerRandom() {
		EventManager.addEntityMessageListener(this);
	}

	public long run(long ticks) {
		if (talkToNpc != null) {
			if (getNPCs()[talkToNpc.index] == talkToNpc) {
				EntityDef def = talkToNpc.npcDescription;
				if (def == null || def.actions == null) {
					talkToNpc = null;
					return -1;
				}
				for (int i = 0; i < def.actions.length; i++) {
					if (def.actions[i] != null && def.actions[i].indexOf("Talk") != -1) {
						boolean canTalk = true;
						for (String ignoreName : ignore)
							if (ignoreName.equals(talkToNpc.npcDescription.npcName))
									canTalk = false;
						
						if (canTalk) {
							atNPC(talkToNpc, i + 1);
							log("Generic talker random handler talking to " +
									talkToNpc.npcDescription.npcName);
							talkToNpc = null;
							return random(4000, 6000);
						}
					}
				}
			} else {
				talkToNpc = null;
			}
		}
		if (continueInterface) {
			continueInterface = false;
			log("Continuing interface");
			continueInterface(cont);
			return random(1000, 3000);
		}
		return -1;
	}

	public void entityMessage(EntityEvent evt) {
		Entity ent = evt.entity;
		String message = evt.message;

		if (myPlayer() == null || myPlayer().playerName == null)
			return;

		if (ent instanceof NPC) {
			NPC npc = (NPC) ent;
			if (message.indexOf(myPlayer().playerName) != -1
					&& message.toLowerCase().indexOf("flee") == -1)
				talkToNpc = npc;
		}

	}

	public void newInterface(RSInterface rsi) {
		if (getDialogText().toLowerCase().indexOf("to continue") > 0) {
			cont = rsi;
			continueInterface = true;
		}
	}
}
