package net.varek.rs2d.model;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import net.varek.rs2d.ClientUpdater;
import net.varek.rs2d.DelayedEventHandler;
import net.varek.rs2d.GameEngine;
import net.varek.rs2d.io.PlayerLoadQueue;
import net.varek.rs2d.io.WorldLoader;
import net.varek.rs2d.util.EntityList;
import net.varek.rs2d.util.Logger;

public final class World {	
	private static World worldInstance;
	public static final int MAX_WIDTH = 4000, MAX_HEIGHT = 4000;
	public Tile[][] tiles = new Tile[MAX_WIDTH][MAX_HEIGHT];
	private EntityList<Player> players = new EntityList<Player>();
	private LinkedList<Player> playersAwaitingLogin = new LinkedList<Player>();
	private ClientUpdater clientUpdater;
	private DelayedEventHandler delayedEventHandler;
	private GameEngine gameEngine;
	private PlayerLoadQueue persister;

	public static synchronized World getWorld() {
		return worldInstance;
	}
	
	public static void loadWorld(WorldLoader wl) {
		worldInstance = new World();
		wl.loadWorld(worldInstance);
	}

	private World() {}
	
	public void setClientUpdater(ClientUpdater clientUpdater) {
		this.clientUpdater = clientUpdater;
	}
	
	public void setDelayedEventHandler(DelayedEventHandler delayedEventHandler) {
		this.delayedEventHandler = delayedEventHandler;
	}
	
	public void setGameEngine(GameEngine gameEngine) {
		this.gameEngine = gameEngine;
	}
	
	public GameEngine getGameEngine() {
		return gameEngine;
	}
	
	public ClientUpdater getClientUpdater() {
		return clientUpdater;
	}
	
	public DelayedEventHandler getDelayedEventHandler() {
		return delayedEventHandler;
	}
	
	public PlayerLoadQueue getPlayerLoadQueue() { 
		return persister; 
	}
	
	public void setPlayerLoadQueue(PlayerLoadQueue pl) { 
		persister = pl; 
	}
	
	public void setLocation(Entity entity, Point oldPoint, Point newPoint) {
		Tile t;
		if (oldPoint != null) {
			t = getTile(oldPoint);
			t.remove(entity);
		}
		if (newPoint != null) {
			t = getTile(newPoint);
			t.add(entity);
		}
	}
	
	public Tile getTile(int x, int y) {
		Tile t = tiles[x][y];
		if (t == null) {
			t = new Tile(x, y);
			tiles[x][y] = t;
		}
		return t;
	}

	public Tile getTile(Point p) {
		return getTile(p.getX(), p.getY());
	}

	public void registerPlayer(Player p) {
		p.setLoggedIn(true);
		players.add(p);
		Logger.log("Registered "+p+" [online: "+players.size()+"].");
	}
	
	public List<Player> getPlayersAwaitingLogin() {
		return playersAwaitingLogin;
	}
	
	public void addPlayerAwaitingLogin(Player p) {
		synchronized(playersAwaitingLogin) {
			playersAwaitingLogin.add(p);
		}
	}

	public void unregisterPlayer(Player p, boolean notify) {
		p.setLoggedIn(false);
		players.remove(p);
		Logger.log("Unregistered "+p+".");
	}
	
	public EntityList<Player> getPlayers() {
		return players;
	}
	
	public int countPlayers() {
		return players.size();
	}

	public boolean hasPlayer(Player p) {
		return players.contains(p);
	}
}
