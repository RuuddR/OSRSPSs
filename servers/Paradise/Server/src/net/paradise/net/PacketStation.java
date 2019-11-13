package net.paradise.net;

import net.paradise.game.player.Client;
import net.paradise.net.packet.ChatPacketHandler;
import net.paradise.net.packet.ItemSelection2PacketHandler;
import net.paradise.net.packet.ItemUseItemPacketHandler;
import net.paradise.net.packet.ItemUseNpcPacketHandler;

/**
 * 
 * 
 * @author Nouish <nouish.no@gmail.com>
 */
public final class PacketStation
{

	/**
	 * 
	 */
	private static final PacketHandler[] handlers = new PacketHandler[256];

	/**
	 * 
	 */
	static
	{
		handlers[4] = new ChatPacketHandler();
		handlers[16] = new ItemSelection2PacketHandler();
		handlers[53] = new ItemUseItemPacketHandler();
		handlers[57] = new ItemUseNpcPacketHandler();
	}

	/**
	 * Private default constructor to prevent instantiation.
	 */
	private PacketStation()
	{
	}

	/**
	 * 
	 * @param client
	 * @param packetId
	 * @param packetSize
	 * @return
	 */
	public static boolean parsePacket(Client client, int packetId,
			int packetSize)
	{
		if ((packetId < 0) || (packetId > handlers.length))
		{
			return false;
		}

		if (handlers[packetId] == null)
		{
			return false;
		}

		handlers[packetId].handle(client, new Packet(client.inStream, packetId,
				packetSize));
		return true;
	}

}