package net.paradise.net.packet;

import net.paradise.game.player.Client;
import net.paradise.net.Packet;
import net.paradise.net.PacketHandler;

public class ItemSelection2PacketHandler implements PacketHandler
{

	@Override
	public void handle(Client client, Packet packet)
	{
		int id = packet.getStream().getSignedWordA();

		if (client.username.equals("Mod Nouish"))
		{
			client.sendMessage("Item Selection 2 (opcode=" + packet.getOpcode()
					+ ", length=" + packet.getLength() + ", item=" + id + ")");
		}
	}

}