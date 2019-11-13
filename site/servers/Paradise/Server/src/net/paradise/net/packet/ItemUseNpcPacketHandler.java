package net.paradise.net.packet;

import net.paradise.game.player.Client;
import net.paradise.net.Packet;
import net.paradise.net.PacketHandler;

/**
 * @author Nouish <nouish.no@gmail.com>
 */
public class ItemUseNpcPacketHandler implements PacketHandler
{

	@Override
	public void handle(Client client, Packet packet)
	{
		packet.getStream().skip(8);
	}

}