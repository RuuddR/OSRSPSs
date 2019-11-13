package server.items;

import server.*;
import server.npcs.*;
import server.players.*;
import server.util.*;
import server.world.*;

public class ItemList {
	public int[] Bonuses = new int[100];
	public double HighAlch;
	public String itemDescription;
	public int itemId;
	public String itemName;
	public double LowAlch;
	public double ShopValue;

	public ItemList(int _itemId) {
		itemId = _itemId;
	}
}
