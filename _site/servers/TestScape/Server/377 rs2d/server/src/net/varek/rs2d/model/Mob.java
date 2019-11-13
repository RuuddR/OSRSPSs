package net.varek.rs2d.model;

/**
 * A mob represents a mobile entity.
 */
public abstract class Mob extends Entity {
	public static final int[][] MOB_SPRITES = new int[][]{
			{1,  2,  3},
			{0, -1,  4},
			{7,  6,  5}
	};
	public static final int[] CLIENT_SPRITES = new int[] { 1, 2, 4, 7, 6, 5, 3, 0 }; //stolen from some rs2 server
	private transient int lastSprite = -1, mobSprite = -1, combatLevel = 3;
	protected transient PathHandler pathHandler;
	protected transient ViewArea viewArea;
	
	public Mob() {
		 pathHandler = new PathHandler(this);
		 viewArea = new ViewArea(this);
	}
	
	public PathHandler getPathHandler() { 
		return pathHandler; 
	}
	
	public void updatePosition() { 
		pathHandler.updatePosition(); 
	}
	
	public void resetPath() { 
		pathHandler.resetPath();
	}
	
	public ViewArea getViewArea() { 
		return viewArea; 
	}
	
	public int getCombatLevel() { 
		return combatLevel; 
	}
	
	public void setCombatLevel(int level) {
		combatLevel = level;
	}
	
	public void setLocation(Point p, boolean teleported) {
		if (!teleported && location != null) {
			updateSprite(p);
		}
		setLocation(p);
	}

	public void updateSprite(Point newLocation) {
		int dX, dY;
		int newSprite;
		dX = getLocation().getX() - newLocation.getX();
		dY = getLocation().getY() - newLocation.getY();
		if(dX < -1 || dX > 1 || dY < -1 || dY > 1)
			throw new ArrayIndexOutOfBoundsException("Sprite index values out of range (-1<=i<=1): "+dX+","+dY);
		newSprite = MOB_SPRITES[dX + 1][dY + 1];
		setSprite(newSprite);
	}
	
	public void setSprite(int x) {
		lastSprite = mobSprite;
		mobSprite = x; 
	}
	
	public int getSprite() {
		return mobSprite;
	}
	
	public int getLastSprite() {
		return lastSprite;
	}
	
	public boolean hasMoved() { 
		return mobSprite != -1; 
	}
	
	public void resetMoved() { 
		mobSprite = -1;
	}
}
