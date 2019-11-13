/*
 * Main.java
 * 
 * Created on 23-Dec-2007, 11:38:13
 * 
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package net.varek.rs2d.test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import net.varek.rs2d.GameEngine;
import net.varek.rs2d.Server;
import net.varek.rs2d.model.World;
import net.varek.rs2d.packethandler.ActionButton;
import net.varek.rs2d.packethandler.PacketHandler;
import net.varek.rs2d.packethandler.PublicChat;
import net.varek.rs2d.packethandler.Walking;
import net.varek.rs2d.util.Logger;

/**
 *
 * @author alex
 */
public class Main {
	private static final Map<Integer,String> packetNames = new HashMap<Integer,String>();

	public static void main(String[] args) throws Exception {
		World.loadWorld(new TestWorldLoader());
		Server s = new Server();
		Logger.log("Loaded world");
		for(int i=0; i<handlers.length && i < handleIds.length; i++) {
			PacketHandler h = handlers[i];
			List<GameEngine.SingleBoundPacketHandler> overwritten = s.getGameEngine().registerPacketHandler(h, handleIds[i]);
			if(overwritten != null) 
				Logger.err("Error! Overwritten packet handlers: "+overwritten.toString());
			Logger.log("\tAdded packet handler: "+h+" (binds="+Arrays.toString(handleIds[i])+")");
		}
		s.start(43594);
		Logger.log("Listening on port 43594");
    }
	
	static {
		int[] knownIds = new int[packetNames.size()];
		Iterator<Integer> keys = packetNames.keySet().iterator();
		for(int i=0; i<knownIds.length; i++) {
			knownIds[i] = keys.next();
		}
		
		handleIds = new int[][] {
			{ 22, 40, 78, 244, 248 },
			//knownIds,
			{ 213, 28, 247 },
			{ 71 }
			//{ 4 }
		};
		handlers = new PacketHandler[] {
			new QuietPacketHandler(),
			//new DebuggingPacketHandler(packetNames),
			new Walking(),
			new ActionButton()
			//new PublicChat()
		};
	}
	
	private static final PacketHandler[] handlers;
	private static final int[][] handleIds;
}
