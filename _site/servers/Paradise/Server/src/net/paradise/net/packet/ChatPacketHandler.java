package net.paradise.net.packet;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import net.paradise.game.player.Client;
import net.paradise.net.Packet;
import net.paradise.net.PacketHandler;
import net.paradise.util.MethodCollection;

/**
 * @author Nouish <nouish.no@gmail.com>
 */
public class ChatPacketHandler implements PacketHandler
{

	@Override
	public void handle(Client client, Packet packet)
	{
		client.chatTextEffects = packet.getStream().getUnsignedByteS();
		client.chatTextColor = packet.getStream().getUnsignedByteS();
		client.chatTextSize = (byte) (packet.getLength() - 2);
		packet.getStream().readBytes_reverseA(client.chatText,
				client.chatTextSize, 0);
		client.chatTextUpdateRequired = true;

		logChat(client);
	}

	private void logChat(Client client)
	{
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd | HH:mm:ss | ");
		BufferedWriter bw = null;
		try
		{
			DateFormat f = new SimpleDateFormat("yyyyMMdd");
			bw = new BufferedWriter(new FileWriter("./data/chatlogs/"
					+ f.format(new Date()) + ".txt", true));
			bw.write(df.format(new Date())
					+ client.username
					+ ": "
					+ MethodCollection.textUnpack(client.chatText,
							client.packetSize - 2));
			bw.newLine();
			bw.newLine();
			bw.flush();
		}
		catch (IOException ioe)
		{
			ioe.printStackTrace();
		}
		finally
		{
			if (bw != null)
			{
				try
				{
					bw.close();
				}
				catch (IOException ioe2)
				{
				}
			}
		}
	}

}