/*
 * TestWorldLoader.java
 * 
 * Created on 16-Dec-2007, 21:45:53
 * 
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package net.varek.rs2d.test;

import java.util.Arrays;
import net.varek.rs2d.io.PlayerLoadQueue;
import net.varek.rs2d.io.PlayerLoader;
import net.varek.rs2d.io.WorldLoader;
import net.varek.rs2d.model.Player;
import net.varek.rs2d.model.PlayerAppearance;
import net.varek.rs2d.model.Point;
import net.varek.rs2d.model.World;

/**
 *
 * @author alex
 */
public class TestWorldLoader implements WorldLoader {
    public void loadWorld(World w) {
		PlayerLoadQueue pl = new PlayerLoadQueue();
		pl.setLoader(new TestPlayerLoader());
		w.setPlayerLoadQueue(pl);
		new Thread(pl, "PlayerLoadQueue").start();
    }

	static class TestPlayerLoader implements PlayerLoader {

        public void save(Player p) throws UnsupportedOperationException {
            // don't really care much
        }

        public byte load(Player p) throws UnsupportedOperationException {
			p.setAppearance(new PlayerAppearance(3, 19, 29, 35, 39, 44, 7, 8, 9, 5, 0));
			p.setLocation(Point.location(3222, 3218), true);
			int[] stats = new int[25];
			Arrays.fill(stats, 1);
			p.setCurStats(stats);
			p.setMaxStats(stats);
			Arrays.fill(stats, 1); //exp
			p.setExps(stats);
			return (byte)2; 
        }
		
	}
}
