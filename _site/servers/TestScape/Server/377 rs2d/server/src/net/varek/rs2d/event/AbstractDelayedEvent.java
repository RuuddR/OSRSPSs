package net.varek.rs2d.event;

import net.varek.rs2d.DelayedEventHandler;
import net.varek.rs2d.model.Player;
import net.varek.rs2d.model.World;

public abstract class AbstractDelayedEvent implements DelayedRunnable {
	private boolean running;
	private long runTime, delay;
	private Player owner;
	public final DelayedEventHandler handler = World.getWorld().getDelayedEventHandler();
	
	public AbstractDelayedEvent(Player owner, int delay) {
		this.owner = owner;
		setInterval(delay);
		this.running = true;
	}
	
	public abstract void run();
	public void setRunning(boolean b) { running = b; }
	public boolean isRunning() { return running; }
	public Player getOwner() { return owner; }
	public void setInterval(long delay) {
		if(!running)
			delay = 0;
		this.delay = delay;
		this.runTime = System.currentTimeMillis() + delay; 
	}
	public void reset() { setInterval(delay); }
	public long getDelay() {
		return running ? runTime - System.currentTimeMillis() : 1; 
	}
	public boolean belongsTo(Player p) {
		return owner == null ? false : owner.equals(p);
	}
	public void terminate() { running = false; setInterval(0); }
	public String toString() { return this.getClass().getSimpleName()+",t="+(runTime-System.currentTimeMillis())+",p="+(owner != null ? owner.getUsername():"null"); }
}