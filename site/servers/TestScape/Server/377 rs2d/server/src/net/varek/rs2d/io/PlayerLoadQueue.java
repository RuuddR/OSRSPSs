package net.varek.rs2d.io;

import java.util.ArrayList;
import java.util.List;
import net.varek.rs2d.model.Player;
import net.varek.rs2d.model.World;
import net.varek.rs2d.util.Logger;

public class PlayerLoadQueue implements Runnable {
    private ArrayList<PlayerEvent> eventList = new ArrayList<PlayerEvent>();
    private PlayerLoader loader;
    private enum EventType { Load, Save };
    private int toBeProcessed = -1;

	public void setLoader(PlayerLoader pel) {
		loader = pel;
	}

	public void run() {
		do {
			while(eventList.isEmpty())
		try {
			Thread.sleep(50);
		} catch(InterruptedException ie) { }
			List<PlayerEvent> tmp = null;
		synchronized(eventList) {
			tmp = (List<PlayerEvent>)eventList.clone();
			toBeProcessed = eventList.size();
			eventList.clear();
		}
		for(PlayerEvent pa : tmp) {
			try {
				if(pa.getType() == EventType.Load) {
					Player p = pa.getPlayer();
					p.setResponseCode(loader.load(p));
					World.getWorld().addPlayerAwaitingLogin(p);
				} else if(pa.getType() == EventType.Save) {
					loader.save(pa.getPlayer());
				}
			} catch(UnsupportedOperationException uoe) { //ignore it
			} catch(Exception e) {
				Logger.err(e);
			}
			toBeProcessed--;
			}
			toBeProcessed = -1;
			} while(true);
		}

	public int eventCount() {
		return toBeProcessed == -1 ? eventList.size() : toBeProcessed;
	}

	public void addLoad(Player p) {
		add(new PlayerEvent(p, EventType.Load));
	}

	public void addSave(Player p) {
		add(new PlayerEvent(p, EventType.Save));
	}

	private void add(PlayerEvent pe) {
		synchronized(eventList) {
			eventList.add(pe);
		}
	}

	public class PlayerEvent {
		private Player p;
		private EventType type;
		private Object[] attachments;

		public PlayerEvent(Player p, EventType pt, Object... attachments) {
			this.p = p;
			this.type = pt;
			this.attachments = attachments;
		}

		public Object getAttachment(int i) {
			return attachments[i];
		}

		public Player getPlayer() {
			return p;
		}

		public EventType getType() {
			return type;
		}
	}
}