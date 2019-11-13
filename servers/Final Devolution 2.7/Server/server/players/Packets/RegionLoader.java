package server.players.Packets;

import server.players.*;
import server.*;
import server.npcs.*;
import server.util.*;

public class RegionLoader {

	public client client;
	
	public RegionLoader(client c) {
		client = c;
	}

	public void RegionLoader(client c) {
			c.replaceDoors();
			c.SG().savegame(c, true);
	}

}