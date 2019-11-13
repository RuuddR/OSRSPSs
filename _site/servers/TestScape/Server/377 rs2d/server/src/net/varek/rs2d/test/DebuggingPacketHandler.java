/*
 * DebuggingPacketHandler.java
 * 
 * Created on 23-Dec-2007, 15:38:13
 * 
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package net.varek.rs2d.test;

import java.util.Map;
import net.varek.rs2d.model.Player;
import net.varek.rs2d.net.Packet;
import net.varek.rs2d.packethandler.PacketHandler;
import net.varek.rs2d.util.Logger;
import org.apache.mina.common.IoSession;

/**
 *
 * @author alex
 */
public class DebuggingPacketHandler implements PacketHandler {
	private Map<Integer,String> packetNames;

    /**
     * Creates a new DebuggingPacketHandler.
     * @param packetNames a map containing packet IDs and names of packets.
     */
    public DebuggingPacketHandler(Map<Integer,String> packetNames) {
		this.packetNames = packetNames;
    }

    public void handlePacket(Packet p, IoSession session) {
		String username = ((Player)session.getAttachment()).getUsername();
		Logger.log("["+username+"] recv: "+p+" ("+packetNames.get(p.getId())+")");
    }

}
