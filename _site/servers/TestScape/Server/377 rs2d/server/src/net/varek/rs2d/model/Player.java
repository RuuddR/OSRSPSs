package net.varek.rs2d.model;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.mina.common.IoSession;
import net.varek.rs2d.event.DelayedRunnable;
import net.varek.rs2d.net.ActionSender;
import net.varek.rs2d.util.Data;
import net.varek.rs2d.util.Logger;
import net.varek.rs2d.util.StatefulEntityCollection;

/**
 * A single player.
 */
public final class Player extends Mob {
	private String username, password,
			lastIP = "0.0.0.0", currentIP = "0.0.0.0";
	private PlayerAppearance appearance;
	private int[] curStats = new int[25], maxStats = new int[25], exps = new int[25], 
			privacySettings = new int[3];
	private Equipment equipment = new Equipment(this);
	private transient boolean reconnecting = false, mapAreaChanged = true, running = false, loggedIn = false;
	private transient byte responseCode;
	private transient int knownRegionX, knownRegionY;
	private transient long usernameHash;
	private transient IoSession ioSession;
	private transient ActionSender actionSender = new ActionSender(this);
	private transient Map<String,DelayedRunnable> events = new HashMap<String,DelayedRunnable>();
	private transient ChatMessage lastChatMessage = null;
	private transient StatefulEntityCollection<Player> watchedPlayers = new StatefulEntityCollection<Player>();
	private transient Map<Player,Integer> knownAppearanceIds = new HashMap<Player,Integer>();
			
	public Player(IoSession ios) {
		ioSession = ios;
	}

    public Map<String, DelayedRunnable> getEvents() {
        return events;
    }

    public ActionSender getActionSender() {
        return actionSender;
    }

    public ChatMessage getLastChatMessage() {
		return lastChatMessage;
	}
	
	public void setLastChatMessage(ChatMessage m) {
		lastChatMessage = m;
	}

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }
	
	public int getPrivacySetting(int idx) {
		return privacySettings[idx];
	}
	
	public void setPrivacySetting(int idx, int i) {
		privacySettings[idx] = i;
	}

    public int[] getCurStats() {
        return curStats;
    }
	
	public int getCurStat(int idx) { 
		return curStats[idx];
	}

    public void setCurStats(int[] curStats) {
        this.curStats = curStats;
    }
	
	public void setCurStat(int idx, int s) {
		curStats[idx] = s;
	}

    public int[] getMaxStats() {
        return maxStats;
    }
	
	public int getMaxStat(int idx) { 
		return maxStats[idx];
	}

    public void setMaxStats(int[] maxStats) {
        this.maxStats = maxStats;
    }
	
	public void setMaxStat(int idx, int s) {
		maxStats[idx] = s;
	}

    public int[] getExps() {
        return exps;
    }

    public void setExps(int[] exps) {
        this.exps = exps;
    }

    public String getUsername() {
        return username;
    }
	
	public long getUsernameHash() {
		return usernameHash;
	}

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }
	
    public PlayerAppearance getAppearance() {
        return appearance;
    }

    public void setAppearance(PlayerAppearance appearance) {
        this.appearance = appearance;
    }

    public void setCredentials(String u, String p, boolean r) {
		username = u;
		usernameHash = Data.playerNameToLong(u);
		password = p;
		reconnecting = r;
	}

    public String getLastIP() {
        return lastIP;
    }

    public IoSession getIoSession() {
        return ioSession;
    }

    public void setLastIP(String lastIP) {
        this.lastIP = lastIP;
    }

    public String getCurrentIP() {
        return currentIP;
    }

    public void setCurrentIP(String currentIP) {
        this.currentIP = currentIP;
    }

    public byte getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(byte responseCode) {
        this.responseCode = responseCode;
    }

    public boolean isMapAreaChanged() {
        return mapAreaChanged;
    }

    public void setMapAreaChanged(boolean mapAreaChanged) {
        this.mapAreaChanged = mapAreaChanged;
    }

    public boolean isRunning() {
        return running;
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public StatefulEntityCollection<Player> getWatchedPlayers() {
        return watchedPlayers;
    }
	
	public void setKnownRegion(int x, int y) {
		knownRegionX = x;
		knownRegionY = y;
	}
	
	public int[] getKnownRegion() {
		return new int[] { knownRegionX, knownRegionY };
	}
	
	public void validatedWatchedPlayers() {
		for(Player p : watchedPlayers.getKnownEntities()) {
			if(!withinRange(p) || !p.isLoggedIn())
				removeWatchedPlayer(p);
		}
	}
	
	public void updateWatchedPlayers() {
		List<Player> playersInArea = getViewArea().getPlayersInView();
		for(Player p : playersInArea) {
			if(!p.equals(this)) {
				informOfPlayer(p);
				p.informOfPlayer(this);
			}
		}
	}
	
	private void informOfPlayer(Player p) {
		if ((!watchedPlayers.contains(p) || watchedPlayers.isRemoving(p)) && !watchedPlayers.isAdding(p) && withinRange(p) && p.isLoggedIn()) {
			watchedPlayers.add(p);
			knownAppearanceIds.put(p, -1);
		}
	}
	
	public void updateAppearanceId() {
		if(getAppearance().hasChanged()) {
			for(Player p : watchedPlayers.getKnownEntities()) {
				p.knownAppearanceIds.put(this, getAppearance().getId());
			}
			knownAppearanceIds.put(this, getAppearance().getId());
			getAppearance().incrementId();
		}
	}
	
	public int getKnownAppearanceId(Player p) {
		return knownAppearanceIds.get(p);
	}
	
	public int setKnownAppearanceId(Player p, int i) {
		return knownAppearanceIds.put(p, i);
	}
	
	@Override
	public void setCombatLevel(int c) {
		super.setCombatLevel(c);
		getAppearance().setChanged(true);
	}
	
	@Override
	public void setLocation(Point p) {
		if(location != null) { // location could be null if we've just logged in
			int[] knownMapArea = getKnownRegion();
			int[] newMapArea = { p.getRegionX(), p.getRegionY() };
			int[] differences = { Math.abs(newMapArea[0] - knownMapArea[0]), Math.abs(newMapArea[1] - knownMapArea[1]) };
			if(differences[0] >= 3 || differences[1] >= 3) {
				//Logger.log("mapAreaChanged, known="+Arrays.toString(knownMapArea)+", new="+Arrays.toString(newMapArea)+", d="+Arrays.toString(differences));
				setMapAreaChanged(true);
			}
		} else {
			//if it is null we definately need to send the map are packet
			setMapAreaChanged(true);
		}
		super.setLocation(p);
	}
	
	public String toString() { 
		return "['"+username+"',idx="+index+"]";
	}
	
	public boolean equals(Object o) {
		if(o instanceof Player)
			return ((Player)o).getUsernameHash() == this.getUsernameHash();
		return false;
	}
	
	public boolean withinRange(Point p, int rad) {
		if(p == null) return false;
		int dX = Math.abs(location.getX() - p.getX());
		int dY = Math.abs(location.getY() - p.getY());
		return dX <= rad && dY <= rad;
	}
	
	public boolean withinRange(Entity e, int rad) {
		if(e == null) return false;
		return withinRange(e.getLocation(), rad);
	}
	
	public boolean withinRange(Entity e) {
		return withinRange(e, 16);
	}
	
	private void removeWatchedPlayer(Player p) {
		watchedPlayers.remove(p);
		knownAppearanceIds.remove(p);
	}
}
