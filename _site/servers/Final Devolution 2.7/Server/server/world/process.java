package server.world;

import server.*;
import server.npcs.*;
import server.players.*;
import server.items.*;
import server.util.*;

public class process extends Thread {
	public static final int cycleTime = 500;

	@Override
	public void run() {
		int waitFails = 0;
		long lastTicks = System.currentTimeMillis();
		long totalTimeSpentProcessing = 0;
		int cycle = 0;
		while (!server.shutdownServer) {
			try {
				if (server.updateServer)
					server.calcTime();
				
				server.processEvents();								// stuff
				server.playerHandler.process(); // updates all player related
				server.npcHandler.process();
				server.itemHandler.process();
				server.shopHandler.process();
				server.worldObject.process();
				server.uptime++;
				// taking into account the time spend in the processing code for
				// more accurate timing
				long timeSpent = System.currentTimeMillis() - lastTicks;
				totalTimeSpentProcessing += timeSpent;
				if (timeSpent >= cycleTime) {
					timeSpent = cycleTime;
					if (++waitFails > 100) {
						// shutdownServer = true;
						// misc.println("[KERNEL]: machine is too slow to run
						// this server!");
					}
				}
				try {
					Thread.sleep(cycleTime - timeSpent);
				} catch (java.lang.Exception _ex) {
				}
				lastTicks = System.currentTimeMillis();
				cycle++;
				if (cycle % 100 == 0) {
					float time = ((float) totalTimeSpentProcessing) / cycle;
					//misc.println_debug("[CPU-USAGE]: "+(time*100/cycleTime)+"%.");
				}
				if (server.ShutDown == true) {
					if (server.ShutDownCounter >= 100) {
						server.shutdownServer = true;
					}
					server.ShutDownCounter++;
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		// shut down the server
		server.playerHandler.destruct();
		server.clientHandler.killServer();
		server.clientHandler = null;
	}
}
