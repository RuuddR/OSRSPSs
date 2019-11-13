package net.varek.rs2d.packethandler;

import org.apache.mina.common.IoSession;
import net.varek.rs2d.net.Packet;

public interface PacketHandler {
	public void handlePacket(Packet p, IoSession session);
}
