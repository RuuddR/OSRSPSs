/*
 * PublicChat.java
 * 
 * Created on 24-Dec-2007, 03:35:10
 * 
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package net.varek.rs2d.packethandler;

import net.varek.rs2d.model.ChatMessage;
import net.varek.rs2d.model.Player;
import net.varek.rs2d.net.Packet;
import org.apache.mina.common.IoSession;

/**
 *
 * @author alex
 */
public class PublicChat implements PacketHandler {
    public void handlePacket(Packet p, IoSession session) {
		int chatTextEffects = 128 - p.readByte() & 0xff;
		int chatTextColour = 128 - p.readByte() & 0xff;
		int chatTextSize = p.getLength() - 2;
		byte[] rawChatData = p.getRemainingData();
		byte[] chatData = new byte[chatTextSize];
		for(int i=0; i<chatTextSize; i++)
			chatData[i] = (byte)(rawChatData[chatTextSize - 1 - i] - 128);
		Player player = (Player)session.getAttachment();
		ChatMessage msg = new ChatMessage(player, chatData, chatTextColour, chatTextEffects);
		player.setLastChatMessage(msg);
    }

}
