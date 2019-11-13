package net.varek.rs2d;

import java.util.ArrayList;
import java.util.List;

import org.apache.mina.common.IoSession;
import net.varek.rs2d.model.Player;
import net.varek.rs2d.model.Tile;
import net.varek.rs2d.model.World;
import net.varek.rs2d.net.ActionSender;
import net.varek.rs2d.net.Packet;
import net.varek.rs2d.net.PacketQueue;
import net.varek.rs2d.packethandler.PacketHandler;
import net.varek.rs2d.util.Logger;
import org.apache.mina.common.IdleStatus;

/*
 *
 * The central motor of the game. This class is responsible for the
 * primary operation of the entire game.
 */
public final class GameEngine implements Runnable {
	/*
	 *
	 * The packet queue to be processed
	 */
	private PacketQueue packetQueue = new PacketQueue();
	/*
	 *
	 * Whether the engine's thread is running
	 */
	private boolean running = true;
	/*
	 *
	 * The mapping of packet IDs to their handler
	 */
	private PacketHandler[] packetHandlers = new PacketHandler[256];
	/*
	 *
	 * Responsible for updating all connected clients
	 */
	private ClientUpdater clientUpdater = new ClientUpdater();
	/*
	 *
	 * Handles delayed events rather than events to be ran every iteration
	 */
	private DelayedEventHandler eventHandler = new DelayedEventHandler();

	/*
	 *
	 * The thread execution process.
	 */
	public void run() {
		long lastSentEventUpdate = 0, lastSentMajorUpdate = 0, lastSentMinorUpdate = 0, 
			lastTileCleanup = System.currentTimeMillis(), lastPerfInfo = 0;
		while (running) {
			try {
				Thread.sleep(30);
				processIncomingPackets();
				processPlayersAwaitingLogin();
				long curTime = System.currentTimeMillis();
				if(curTime - lastSentEventUpdate >= 100) {
					lastSentEventUpdate = curTime;
					eventHandler.doEvents();
				}
				if(curTime - lastSentMajorUpdate >= 600) {
					lastSentMajorUpdate = curTime;
					clientUpdater.majorUpdate();
				}
				if(curTime - lastSentMinorUpdate >= 200) {
					lastSentMinorUpdate = curTime;
					clientUpdater.minorUpdate();
				}
				if(curTime - lastTileCleanup >= 60 * 8 * 1000) {
					lastTileCleanup = curTime;
					int cleaned = cleanUpTiles(); //ideally this should be done asynchronously.
				}
			} catch(Exception e) {
				lastSentEventUpdate = lastSentMinorUpdate = lastSentMajorUpdate = 0L;
				Logger.err("Error processing updates,");
				Logger.err(e);
			}
		}
	}
	
	public int cleanUpTiles() {
		Tile[][] tiles = World.getWorld().tiles;
		int cleaned = 0;
		for(int x=0; x<tiles.length; x++) {
			for(int y=0; y<tiles[0].length; y++) {
				Tile t = tiles[x][y];
				if(t == null) continue;
				if(t.isEmpty()) {
					t = null;
					cleaned++;
				}
			}
		}
		return cleaned;
	}

	/*
	 *
	 * Returns the current packet queue.
	 *
	 * @return A <code>PacketQueue</code>
	 */
	public PacketQueue getPacketQueue() {
		return packetQueue;
	}
	
	/*
	 *
	 * Register a new packet handler.
	 * @param p the handler to add
	 * @param bindingIds the packet IDs the new handle will bind to
	 * @return a list of the packet handlers overwritten with their respective packet ids.
	 */
	public List<SingleBoundPacketHandler> registerPacketHandler(PacketHandler p, int... bindingIds) {
		List<SingleBoundPacketHandler> overwrittenHandlers = null;
		for(int id : bindingIds) {
			if(id < 0 || id >= packetHandlers.length)
				throw new ArrayIndexOutOfBoundsException("Invalid packet id: "+id+" (required: 0<=id<"+packetHandlers.length+")");
			if(packetHandlers[id] != null) {
				if(overwrittenHandlers == null)
					overwrittenHandlers = new ArrayList<SingleBoundPacketHandler>();
				overwrittenHandlers.add(new SingleBoundPacketHandler(packetHandlers[id], id));
			}
			packetHandlers[id] = p;
		}
		return overwrittenHandlers;
	}

	/*
	 *
	 * Processes incoming packets.
	 */
	public void processIncomingPackets() {
		for(Packet p : packetQueue.getPackets()) {
			IoSession session = p.getSession();
			Player player = null;
			try {
				player = (Player)(session.getAttachment());
				
				PacketHandler ph = packetHandlers[p.getId()];
				if(ph != null) {
					ph.handlePacket(p, session);
				} else {
					Logger.err("Unhandled (src="+player+"): "+p);
				}
			} catch(Exception e) {
				Logger.err("Error processing packet from "+player+": "+p);
				Logger.err(e);
			}
		}
	}
	
	/*
	 *
	 * Process all players waiting to be logged in
	 */
	public void processPlayersAwaitingLogin() {
		if(World.getWorld().getPlayersAwaitingLogin().isEmpty())
			return;
		Player[] players = null;
		List<Player> worldList = World.getWorld().getPlayersAwaitingLogin();
		synchronized(worldList) {
			players = worldList.toArray(new Player[worldList.size()]);
			worldList.clear();
		}
		for(Player player : players) {
			byte loginCode = player.getResponseCode();
			final IoSession session = player.getIoSession();
			try {
				int loginResponse = player.getResponseCode();
				//Logger.log("resp: "+loginResponse);
				switch(loginResponse) {
				case 2:
					session.setIdleTime(IdleStatus.READER_IDLE, 30);
					session.setIdleTime(IdleStatus.WRITER_IDLE, 30);
					
					ActionSender sender = player.getActionSender();
					sender.sendLoginResponse(2, 0, 0);
					sender.sendLoginPacket();
					sender.sendPrivacySettings();
					for(int i=0; i<25; i++)
						sender.sendStat(i);
					sender.resetCameraView();
					
					int[][] sideBarInterfaces = {
						{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 0},
						{3917, 638, 3213, 1644, 5608, 1151, 1, 5065, 5715, 2449, 4445, 147, 6299, 2423}
					};
					for(int i=0; i<sideBarInterfaces[0].length; i++)
						sender.sendSideBarInterface(sideBarInterfaces[0][i], sideBarInterfaces[1][i]);
					
					sender.sendPlayerCommand(3, true, "Trade with");
					//sender.sendLoginWindow(201, false, 3, (127 << 24)+1, (int)(Math.random() * 10));
					
					World.getWorld().registerPlayer(player);
				}
			} catch(Exception e) {
				Logger.err("Error loading "+player);
				Logger.err(e);
			}
		}
	}
	
	/*
	 *
	 * Represents a PacketHandler bound to a single packet ID.
	 */
	public static class SingleBoundPacketHandler {
		private PacketHandler ph;
		private int id;
		private SingleBoundPacketHandler(PacketHandler ph, int id) {
			this.ph = ph;
			this.id = id;
		}
		public PacketHandler getHandler() { return ph; }
		public int getId() { return id; }
		public String toString() { return "["+id+":"+ph.getClass().getSimpleName()+"]"; }
	}
}
