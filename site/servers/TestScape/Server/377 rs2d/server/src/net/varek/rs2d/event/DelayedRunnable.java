package net.varek.rs2d.event;

import net.varek.rs2d.model.Player;

public interface DelayedRunnable {
    public long getDelay();
    public void run();
    public void reset(); // only needs implementation if this runnable is running more than once
    public boolean isRunning();
    public Player getOwner();
    public void terminate();
}