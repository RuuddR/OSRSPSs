package net.varek.rs2d.io;

import net.varek.rs2d.model.Player;

    public interface PlayerLoader {
	public void save(Player p) throws UnsupportedOperationException;
	public byte load(Player p) throws UnsupportedOperationException;
    }