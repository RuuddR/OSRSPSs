package net.varek.rs2d.model;

import java.util.ArrayList;
import java.util.List;

public class Tile {

	private List<Player> players = null;
	private Point loc;

	public Tile(int x, int y) {
		this(Point.location(x, y));
	}
	
	public Tile(Point p) {
		this.loc = p;
	}

	public void add(Entity entity) {
		if (entity instanceof Player) {
			if(players == null)
				players = new ArrayList<Player>();
			players.add((Player)entity);
		}
	}

	public void remove(Entity entity) {
		if (entity instanceof Player) {
			getPlayers().remove(entity);
		}
	}
	
	public boolean hasPlayers() {
		return players != null && players.size() > 0;
	}

	public List<Player> getPlayers() {
		if(players == null)
			players = new ArrayList<Player>();
		return players;
	}

	public int getX() {
		return loc.getX();
	}

	public int getY() {
		return loc.getY();
	}
	
	/**
	 * @returns true if the tile can be cleaned up.
	 */
	public boolean isEmpty() {
		return false;
	}
	
	public Point getLocation() { return loc; }
	
	public String toString() { 
		return "[loc="+loc.toString()+",players="+hasPlayers()+"]"; 
	}
}
