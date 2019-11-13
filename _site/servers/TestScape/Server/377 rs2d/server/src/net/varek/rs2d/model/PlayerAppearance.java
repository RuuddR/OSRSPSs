/*
* PlayerAppearance.java
*
* Created on 16-Dec-2007, 21:38:43
*
* To change this template, choose Tools | Template Manager
* and open the template in the editor.
*/

package net.varek.rs2d.model;

public class PlayerAppearance {
    private int head, torso, arms, hands, legs, feet, //styles
		hairColour, torsoColour, legColour, feetColour, skinColour,
		id = 0;
    private transient boolean hasChanged = true;

	public PlayerAppearance(int... i) {
	    int n = 0;
		try {
			setHead(i[n++]);
			setTorso(i[n++]);
			setArms(i[n++]);
			setHands(i[n++]);
			setLegs(i[n++]);
			setFeet(i[n++]);
			setHairColour(i[n++]);
			setTorsoColour(i[n++]);
			setLegColour(i[n++]);
			setFeetColour(i[n++]);
			setSkinColour(i[n++]);
		} catch(ArrayIndexOutOfBoundsException e) {
			throw new ArrayIndexOutOfBoundsException("PlayerAppearance.<init> argument underflow (in: "+i.length+")");
		}
	}

	public int getHead() {
		return head;
	}

	public void setHead(int head) {
		this.head = head;
	}

	public int getTorso() {
		return torso;
	}

	public void setTorso(int torso) {
		this.torso = torso;
	}

	public int getArms() {
		return arms;
	}

	public void setArms(int arms) {
		this.arms = arms;
	}

	public int getHands() {
		return hands;
	}

	public void setHands(int hands) {
		this.hands = hands;
	}

	public int getLegs() {
		return legs;
	}

	public void setLegs(int legs) {
		this.legs = legs;
	}

	public int getFeet() {
		return feet;
	}

	public void setFeet(int feet) {
		this.feet = feet;
	}

	public int getHairColour() {
		return hairColour;
	}

	public void setHairColour(int hairColour) {
		this.hairColour = hairColour;
	}

	public int getTorsoColour() {
		return torsoColour;
	}

	public void setTorsoColour(int torsoColour) {
		this.torsoColour = torsoColour;
	}

	public int getLegColour() {
		return legColour;
	}

	public void setLegColour(int legColour) {
		this.legColour = legColour;
	}

	public int getFeetColour() {
		return feetColour;
	}

	public void setFeetColour(int feetColour) {
		this.feetColour = feetColour;
	}

	public int getSkinColour() {
		return skinColour;
	}

	public void setSkinColour(int skinColour) {
		this.skinColour = skinColour;
	}

	public boolean hasChanged() {
		return hasChanged;
	}

	public void setChanged(boolean b) {
		hasChanged = b;
	}

	protected void incrementId() {
		id++;
	}

	public int getId() {
		return id;
	}
}