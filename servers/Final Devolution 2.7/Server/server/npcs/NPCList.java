package server.npcs;

import server.*;
import server.players.*;
import server.util.*;
import server.items.*;
import server.world.*;

public class NPCList {
	public int npcCombat;
	public int npcHealth;
	public int npcId;
	public String npcName;
	public int npcRespawn = 60;

	public NPCList(int _npcId) {
		npcId = _npcId;
	}
}
