package net.varek.rs2d.packethandler;

import org.apache.mina.common.IoSession;
import net.varek.rs2d.model.Player;
import net.varek.rs2d.model.World;
import net.varek.rs2d.net.Packet;

/**
 * This basic handler must navigate the login process and then add the player to the
 * playerloadqueue.
 */
public class PlayerLogin {
    
    public void handlePacket(Packet p, final IoSession session) {
        Player player = (Player)session.getAttachment();
		
        World.getWorld().getPlayerLoadQueue().addLoad(player);
    }
}
