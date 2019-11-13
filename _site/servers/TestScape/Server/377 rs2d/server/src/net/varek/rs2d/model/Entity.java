package net.varek.rs2d.model;

/*
 * base class, used for iterating etc
 *
 * TODO: Needs redone, thanks exemplar. Some things have an index but not an id, some things have an id and not an index, and some have both, some could potentially have neither. 
*/

public class Entity {
    public Point location = null;
    public int id;
    public transient int index;
	
	public int getId() {
		return id;
	}

	public void setId(int newid) {
		id = newid;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int newIndex) {
		index = newIndex;
	}

	public void setLocation(Point p) {
		World.getWorld().setLocation(this, location, p);
		location = p;
	}

	public Point getLocation() {
		return location;
	}

	public int getX() {
		return location.getX();
	}

	public int getY() {
		return location.getY();
	}
	
}
