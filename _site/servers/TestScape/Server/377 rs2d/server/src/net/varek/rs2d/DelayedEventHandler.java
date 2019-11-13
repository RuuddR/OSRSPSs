package net.varek.rs2d;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.varek.rs2d.event.DelayedRunnable;
import net.varek.rs2d.model.Player;
import net.varek.rs2d.model.World;

public final class DelayedEventHandler {
	private List<DelayedRunnable> toAdd = new ArrayList<DelayedRunnable>();
	private List<DelayedRunnable> eventList = new ArrayList<DelayedRunnable>();
	
	public DelayedEventHandler() {
		World.getWorld().setDelayedEventHandler(this);
	}
	
	public boolean contains(DelayedRunnable event) {
		return eventList.contains(event);
	}
	
	public List<DelayedRunnable> geteventList() {
		return eventList;
	}
	
	public void add(DelayedRunnable event) {
		if(!eventList.contains(event)) {
			if(toAdd.contains(event)) {
				toAdd.remove(event);
			}
			toAdd.add(event);
			//Logger.log("Added event: "+event);
		}
	}
	
	public void remove(DelayedRunnable event) {
		eventList.remove(event);
	}
	
	public void removePlayersEvents(Player player) {
		for(Map.Entry<String, DelayedRunnable> entry : player.getEvents().entrySet()) {
			DelayedRunnable dr = entry.getValue();
			dr.terminate();
		}
	}
	
	public void doEvents() {
		if(!toAdd.isEmpty()) {
			eventList.addAll(toAdd);
			toAdd.clear();
		}
		//Logger.log(eventList.toString());
		Iterator<DelayedRunnable> events = eventList.iterator();
		while(events.hasNext()) {
			DelayedRunnable event = events.next();
			if(event != null && event.isRunning() && event.getDelay() <= 0) {
				//Logger.log("Ran event: "+event.getClass().getName());
				event.run();
				if(!event.isRunning())
					events.remove();
				event.reset();
			} else if(event == null || !event.isRunning())
				events.remove();
		}
	}
	
	public int count() { return eventList.size(); }
}