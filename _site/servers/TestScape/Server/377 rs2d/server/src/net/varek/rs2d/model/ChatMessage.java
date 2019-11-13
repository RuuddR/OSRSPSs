/*
* ChatMessage.java
*
* Created on 17-Dec-2007, 19:42:20
*
* To change this template, choose Tools | Template Manager
* and open the template in the editor.
*/

package net.varek.rs2d.model;

public class ChatMessage {
    private int colour, effects;
    private byte[] message;
    private Player sender;

	public ChatMessage(Player p, byte[] b, int col, int eff) {
		this.sender = p;
		this.message = b;
		this.colour = col;
		this.effects = eff;
	}

	public byte[] getMessage() {
		return message;
	}

	public Player getSender() {
		return sender;
	}

	public int getColour() {
		return colour;
	}

	public int getEffects() {
		return effects;
	}
}