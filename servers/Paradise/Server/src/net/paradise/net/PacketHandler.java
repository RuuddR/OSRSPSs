package net.paradise.net;

import net.paradise.game.player.Client;

/**
 * 
 *
 * @author Nouish <nouish.no@gmail.com>
 */
public interface PacketHandler
{

	public void handle(Client client, Packet packet);

}