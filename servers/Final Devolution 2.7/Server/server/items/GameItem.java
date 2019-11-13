package server.items;

import server.*;
import server.npcs.*;
import server.players.*;
import server.util.*;
import server.world.*;

public class GameItem {
	public int id, amount;
	public boolean stackable = false;

	public GameItem(int id, int amount) {
		if (Item.itemStackable[id]) {
			stackable = true;
		}
		this.id = id;
		this.amount = amount;
	}
}