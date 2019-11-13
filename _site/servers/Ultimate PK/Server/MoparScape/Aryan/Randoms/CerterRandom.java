/**
 * Handles *Niles random events.
 *
 * @author RegeX
 */
public class CerterRandom extends RandomHandler
		implements InterfaceListener, EntityMessageListener {

	private String[] names = {"Miles", "Niles", "Giles"};

	private NPC iles;
	private String modelName;
	private boolean solved;

	private long timeOut;

	public CerterRandom() {
		EventManager.addInterfaceListener(this);
		EventManager.addEntityMessageListener(this);
	}

	public long run(long ticks) {
		if (getInterface() != Constants.INTERFACE_CERTER_PUZZLE && !solved) {
			if (iles != null && iles.index != -1 && getNPCs()[iles.index] == iles) {
				log("Talking to Certer NPC");
				atNPC(iles, "Talk-to");
				return 5000;
			}
		} else if (getInterface() == Constants.INTERFACE_CERTER_PUZZLE && !solved) {
			if (modelName != null) {
				int button = getAnswerIndex();
				if (button == -1) {
					log("Cant solve *iles random handler");
					shutdown();
				}
				log("Answering *iles: " + modelName);
				atInterface(14227 + button, 1);
				log("Done!");
				solved = true;
				return 5000;
			}
		} else if ((System.currentTimeMillis() - timeOut) >= 10000 && !solved) {
			log("Nothing has appeared, re-talking");
			log("Talking to Certer NPC");
			atNPC(iles, "Talk-to");
			return 5000;
		} else if (getInterface() == Constants.INTERFACE_CERTER_PUZZLE && solved) {
			log("Solved, but window hasn't dissapeared...");
			solved = false;
			return 1000;
		} else if (getInterface() != Constants.INTERFACE_CERTER_PUZZLE && solved) {
			log("Solved!");
			solved = false;
			iles = null;
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

			if (npc.npcDescription == null || npc.npcDescription.npcName == null)
				return;

			if (message.indexOf(myPlayer().playerName) != -1) {
				for (String name : names) {
					if (name.equals(npc.npcDescription.npcName)) {
						log("Got a *iles random: " + npc.npcDescription.npcName);
						iles = npc;
					}
				}
			}
		}
	}

	public void newInterface(RSInterface rsi) {
		if (rsi.textureID >= 8828 && rsi.textureID <= (8828 + 9) && iles != null) {
			modelName = textureForID(rsi.textureID);
			log("Model -> " + modelName);
		}
	}

	private String textureForID(int id) {
		id -= 8828;
		if (id >= 0 && id < Constants.TEXTURES_ILES.length)
			return Constants.TEXTURES_ILES[id];
		return null;
	}

	private int getAnswerIndex() {
		String[] answers = new String[3];
		for (int i = 14219; i <= 14221; i++) {
			answers[i - 14219] = RSInterface.forID(i).message;
			log("" + (i - 14219) + " -> " + RSInterface.forID(i).message);
		}
		for (int i = 0; i < answers.length; i++) {
			if (answers[i] != null && answers[i].equals(modelName)) {
				return i;
			}
		}
		return -1;
	}

}
