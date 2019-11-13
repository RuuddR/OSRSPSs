package net.varek.rs2d.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Immutable class representing a Point on the game.
 */
public final class Point {
	private static final PointCache pointCache;
	private int[] absolute, mapRegion, local;
	private int height = 0;

	/**
	 * @param x the absolute x coordinate
     * @param y the absolute y coordinate
     * @return a new Point object for the specified coordinates.
     */
	public static Point location(int x, int y) {
		if (x < 0 || y < 0) {
			throw new IllegalArgumentException("Point may not contain non negative values x:" + x + " y:" + y);
		}
		Point point = pointCache.getPoint(hashCode(x, y));
		if(point == null)
			point = new Point(x, y);
		return point;
	}

	private Point(int x, int y) {
		this.absolute = new int[] { x, y };
		this.mapRegion = new int[] { (absolute[0] >> 3) - 6, (absolute[1] >> 3) - 6 };
		this.local = new int[] { absolute[0] - 8 * mapRegion[0], absolute[1] - 8 * mapRegion[1] };
	}

	/**
	 * @return the absolute x coordinate of this point.
	 */
	public final int getX() {
		return absolute[0];
	}
	
	/**
	 * @return the absolute y coordinate of this point.
	 */
	public final int getY() {
		return absolute[1];
	}
	
	/**
	 * @return the x region coordinate of this point
	 */
	public final int getRegionX() {
		return mapRegion[0];
	}
	
	/**
	 * @return the y region coordinate of this point
	 */
	public final int getRegionY() {
		return mapRegion[1];
	}
	
	/**
	 * @return the local y coordinate of this point
	 */
	public final int getLocalX() {
		return local[0];
	}
	
	/**
	 * @return the local x coordinate of this point
	 */
	public final int getLocalY() {
		return local[1];
	}

    public int getHeight() {
        return height;
    }

	public final boolean equals(Object o) {
		if (o instanceof Point) {
			return java.util.Arrays.equals(((Point)o).absolute, this.absolute);
		}
		return false;
	}

	public int hashCode() {
		return hashCode(absolute[0], absolute[1]);
	}
	
	public String toString() { 
		return "["+absolute[0]+","+absolute[1]+"]"; 
	}
	
	/**
	 * Returns a unique hashcode for a Point (assumes a point can only be represented
	 * by 16 bits).
	 */
	public static int hashCode(int x, int y) {
		return x << 16 | y;
	}
	
	static {
		pointCache = new PointCache();
	}
	
	/**
	 * Useful little number saves on having to recreate new points for everything.
	 * Saves a bit of memory for the most part, and object creation times.
	 * Not sure if it's really necessary. Would be worth bencharking it.
	 */
	static class PointCache {
		/**
		 * Stores each point we have created, with the key as it's hashcode.
		 */
		private Map<Integer,Point> points = new HashMap<Integer,Point>();
		public Point getPoint(int code) {
			return points.get(code);
		}
	}
}