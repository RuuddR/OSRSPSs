/*
 * BlankPacketHandler.java
 * 
 * Created on 23-Dec-2007, 15:32:19
 * 
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package net.varek.rs2d.test;

import net.varek.rs2d.packethandler.*;
import net.varek.rs2d.net.Packet;
import org.apache.mina.common.IoSession;

/**
 *
 * @author alex
 */
public class QuietPacketHandler implements PacketHandler {
    public void handlePacket(Packet p, IoSession session) {
        //ignore
    }
}
