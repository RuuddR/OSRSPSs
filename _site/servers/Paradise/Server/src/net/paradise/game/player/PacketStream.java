package net.paradise.game.player;

/*
 * I'll be throwing in all the outgoing packets here for convenience.
 * These will be relocated once more when I get around to work on
 * the networking.
 */
public class PacketStream
{

	public static void sendMessage(Client client, String message)
	{
		client.outStream.createFrameVarSize(253);
		client.outStream.putString(message);
		client.outStream.endFrameVarSize();
	}

	public static void createGroundItem(Client client, int id, int x, int y,
			int amount)
	{
		client.outStream.createFrame(85);
		client.outStream.putByteC((y - 8 * client.mapRegionY));
		client.outStream.putByteC((x - 8 * client.mapRegionX));
		client.outStream.createFrame(44);
		client.outStream.putWordBigEndianA(id);
		client.outStream.putShort(amount);
		client.outStream.put(0);
	}

	public static void removeGroundItem(Client client, int x, int y, int id)
	{
		if (client == null)
		{
			System.err.println("FATAL-EXCEPTION: Client 'client' is null!");
			System.err.println("PacketStream#removeGroundItem(client, " + x
					+ ", " + y + ", " + id + ")");
		}
		else if (client.outStream == null)
		{
			System.err.println("FATAL-EXCEPTION: Outstream is null!");
			System.err.println("PacketStream#removeGroundItem("
					+ client.username + ", " + x + ", " + y + ", " + id + ")");
		}

		client.outStream.createFrame(85);
		client.outStream.putByteC((y - 8 * client.mapRegionY));
		client.outStream.putByteC((x - 8 * client.mapRegionX));
		client.outStream.createFrame(156);
		client.outStream.putByteS(0);
		client.outStream.putShort(id);
	}

}