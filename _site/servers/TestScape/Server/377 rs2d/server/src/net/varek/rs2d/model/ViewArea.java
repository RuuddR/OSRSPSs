package net.varek.rs2d.model;

import java.util.ArrayList;
import java.util.List;

public class ViewArea {
	private Mob mob;

	public ViewArea(Mob m) {
		mob = m;
	}
	
	public List<Player> getPlayersInAttackRange() {
		return getPlayersInView(2);
	}
	
	public List<Player> getPlayersInView() {
		return getPlayersInView(15);
	}

	public List<Player> getPlayersInView(int range) {
		List<Player> players = new ArrayList<Player>();
		TileIterator ti = new TileIterator(range, range, range+1, range+1);
		for(int i=0; i < ti.size(); i++) {
			Tile t = ti.next();
			if(t == null) 
				continue;
			List<Player> temp = t.getPlayers();
			if (temp != null && !temp.isEmpty()) {
				players.addAll(temp);
			}
		}
		return players;
	}
	
	class TileIterator {
		private Tile[][] tiles = World.getWorld().tiles;
		private int xoff, yoff, startX, startY, endX, endY, size;
		public TileIterator(int x1, int y1, int x2, int y2) {
			int mobX = mob.getX();
			int mobY = mob.getY();
			startX = mobX - x1;
			if (startX < 0) {
				startX = 0;
			}
			startY = mobY - y1;
			if (startY < 0) {
				startY = 0;
			}
			endX = mobX + x2;
			if (endX >= World.MAX_WIDTH) {
				endX = World.MAX_WIDTH - 1;
			}
			endY = mobY + y2;
			if (endY >= World.MAX_HEIGHT) {
				endY = World.MAX_HEIGHT - 1;
			}
      		int xWidth;
      		int yWidth;
      		if (startX > endX) {
      			xWidth = startX - endX;
      		} else {
      			xWidth = endX - startX;
      		}
      		if (startY > endY) {
      			yWidth = startY - endY;
      		} else {
      			yWidth = endY - startY;
      		}
			size = xWidth * yWidth;
			this.xoff=0;
			this.yoff=0;
			//com.rscd.util.Logger.log("size="+size+",start="+startX+","+startY+",end="+endX+","+endY+",off="+xoff+","+yoff);
		}
		public Tile next() {
			if((xoff+startX)==endX) {
				xoff = 0;
				if((yoff+startY)==endY)
					throw new java.util.NoSuchElementException("No more elements! (offset="+xoff+","+yoff+"; " +
						"start="+startX+","+startY+"; end="+endX+","+endY+"; size="+size+")");
				yoff++;
			}
			Tile t = tiles[(xoff++)+startX][yoff+startY];
			return t;
		}
		public int size() {
			return size;
		}
	}
}
