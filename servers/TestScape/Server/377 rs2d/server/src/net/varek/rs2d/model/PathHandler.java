package net.varek.rs2d.model;

import java.util.Iterator;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class PathHandler {

	private Queue<Point> waypoints = new LinkedBlockingQueue<Point>();
	private Mob mob;

	public PathHandler(Mob m) {
		mob = m;
		resetPath();
	}
	
	public void addPoint(Point p) {
		if(waypoints.size() > 80)
			return;
		waypoints.offer(p);
	}

	public void updatePosition() {
		if (!waypoints.isEmpty()) {
			setNextPosition();
		}
	}
	
	public void resetPath() {
		waypoints.clear();
	}
	
	public String toString() {
		Iterator<Point> it = waypoints.iterator();
		StringBuilder out = new StringBuilder();
		while(it.hasNext()) {
			Point p = it.next();
			out.append("["+p.getX()+","+p.getY()+"]");
			if(it.hasNext())
				out.append("=>");
		}
		return out.toString();
	}

	public void setNextPosition() {
		Point newPoint = null;
		int newX=-1,newY=-1;
		if(!waypoints.isEmpty()) {
			newPoint = waypoints.peek();
			if(newPoint == null) 
				return;
			if(newPoint.equals(mob.getLocation())) {
				waypoints.remove();
				newPoint = waypoints.peek();
				if(newPoint == null) 
					return;
			}
			newX = getNextCoord(mob.getX(), newPoint.getX());
			newY = getNextCoord(mob.getY(), newPoint.getY());
		}
		
		if (newX != -1 && newY != -1) {
			Point nextPoint = Point.location(newX, newY);
			mob.setLocation(nextPoint, false);
		}
	}
	
	/**
	 * Dunno if RS2 directions are the same as RSCs..
	 */
	public Tile getTile(int direction) {
		int x = mob.getX(), y = mob.getY();
		switch(direction) {
			case NONE:		return World.getWorld().getTile(x, y);
			case NORTH:		return World.getWorld().getTile(x, y+1);
			case NORTHEAST:	return World.getWorld().getTile(x+1, y+1);
			case EAST:		return World.getWorld().getTile(x+1, y);
			case SOUTHEAST:	return World.getWorld().getTile(x+1, y-1);
			case SOUTH:		return World.getWorld().getTile(x, y-1);
			case SOUTHWEST:	return World.getWorld().getTile(x-1, y-1);
			case WEST:		return World.getWorld().getTile(x-1, y);
			case NORTHWEST:	return World.getWorld().getTile(x-1, y+1);
		}
		throw new IllegalArgumentException("Bad direction: "+direction);
	}
	
	public int getNextCoord(int startCoord, int destCoord) {
		if (startCoord == destCoord) {
			return startCoord;
		} else if (startCoord > destCoord) {
			return --startCoord;
		} else if (startCoord < destCoord) {
			return ++startCoord;
		}
		return 0; //never going to happen.
	}
	
	// Directions
	public static final int NONE=-1, NORTH=0, NORTHEAST=2, EAST=4, 
		SOUTHEAST=6, SOUTH=8, SOUTHWEST=10, WEST=12, NORTHWEST=14;
}
