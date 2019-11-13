/*
* Equipment.java
*
* Created on 16-Dec-2007, 23:56:41
*
* To change this template, choose Tools | Template Manager
* and open the template in the editor.
*/

package net.varek.rs2d.model;

public class Equipment {
    private Player player;
    private EquippedItem[] items = new EquippedItem[14];

	public Equipment(Player p) {
		this.player = p;
	}

	public int getId(int type) {
		if(!isEquipped(type))
			return -1;
			return items[type].getItemId();
	}

	public int getAmount(int type) {
		if(!isEquipped(type))
			return -1;
			return items[type].getAmount();
	}

	public boolean isEquipped(int type) {
		return items[type] != null;
	}

static class EquippedItem {
    private int itemId, amount;

	public EquippedItem(int id, int a) {
		this.itemId = id;
		this.amount = a;
	}

	public int getItemId() {
		return itemId;
	}

	public void setItemId(int itemId) {
		this.itemId = itemId;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
}

public static final int HAT=0, CAPE=1, AMULET=2, WEAPON=3,
			TORSO=4, SHIELD=5, LEGS=7, HANDS=9, FEET=10,
			RING=12, ARROWS=13;
}