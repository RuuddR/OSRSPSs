package net.varek.rs2d;

import java.util.Iterator;
import net.varek.rs2d.model.Player;
import net.varek.rs2d.model.Point;
import net.varek.rs2d.model.World;
import net.varek.rs2d.packetbuilder.PacketBuilder;
import net.varek.rs2d.packetbuilder.PlayerUpdate;
import net.varek.rs2d.util.EntityList;
import net.varek.rs2d.util.Logger;

public final class ClientUpdater {
    private EntityList<Player> players = World.getWorld().getPlayers();
	private PlayerUpdate playerUpdate = new PlayerUpdate();
    
    public ClientUpdater() {
		World.getWorld().setClientUpdater(this);
    }
    
    /**
     * Update player/npc movements
     */
    public void majorUpdate() {
		//Change player positions and remove logged out players
		for(Player p : players) {
			if(!p.isLoggedIn()) {
				World.getWorld().unregisterPlayer(p, true);
				continue;
			}
			if(!p.isMapAreaChanged()) {
				p.getPathHandler().updatePosition();
				if(p.isRunning())
					p.getPathHandler().updatePosition();
			}
			p.updateAppearanceId();
		}
		
		for(Player p : players) {
			p.validatedWatchedPlayers();
			p.updateWatchedPlayers();
		}
		
		//This loop updates player info
		for(Player p : players) {
			if(p.isMapAreaChanged()) {
				p.getActionSender().sendMapArea();
				p.setKnownRegion(p.getLocation().getRegionX(), p.getLocation().getRegionY());
			}
			p.getIoSession().write(playerUpdate.getPacket(p));
		}
		
		// This loop resets player data
		for(Player p : players) {
			p.getAppearance().setChanged(false);
			p.setMapAreaChanged(false);
			p.setSprite(-1); //reset player movement.
			p.setLastChatMessage(null);
			
			p.getWatchedPlayers().update();
		}
    }
    
    /**
     * Update player/npc appearances, game objects, items, wall objects, ping
     */
    public void minorUpdate() {

    }
}
