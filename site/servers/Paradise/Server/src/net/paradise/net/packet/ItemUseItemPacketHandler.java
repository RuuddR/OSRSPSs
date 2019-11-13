package net.paradise.net.packet;

import net.paradise.game.player.Client;
import net.paradise.game.player.Player;
import net.paradise.net.Packet;
import net.paradise.net.PacketHandler;

/**
 * @author Nouish <nouish.no@gmail.com>
 */
public class ItemUseItemPacketHandler implements PacketHandler
{

	@Override
	public void handle(Client client, Packet packet)
	{
		int firstSlot = packet.getStream().getUnsignedShort();
		int secondSlot = packet.getStream().getUnsignedWordA();

		packet.getStream().skip(4);
		// int interfaceId = packet.getStream().getUnsignedShort();
		// int interfaceChild = packet.getStream().getUnsignedShort();

		if ((firstSlot < 0) || (firstSlot > client.playerItems.length))
		{
			System.out.println(client.username + ": erraneous first slot "
					+ firstSlot + ".");
			return;
		}

		if ((secondSlot < 0) || (secondSlot > client.playerItems.length))
		{
			System.out.println(client.username + ": erraneous second slot "
					+ secondSlot + ".");
			return;
		}

		int firstId = client.playerItems[firstSlot] - 1;
		int secondId = client.playerItems[secondSlot] - 1;

		// if (client.username.equals("Mod Nouish"))
		// {
		// client.sendMessage(getClass().getSimpleName() + ":");
		// client.sendMessage("firstSlot=" + firstSlot + ", secondSlot="
		// + secondSlot + ", interfaceId=" + interfaceId
		// + ", interfaceChild=" + interfaceChild);
		// }

		if (validate(firstId, secondId, 946, 1511))
		{
			if (client.playerLevel[Player.FLETCHING] < 1)
			{
				client.sendMessage("You need a Fletching level of 1 to do this!");
				return;
			}

			if (firstId == 1511)
			{
				client.deleteItem(1511, firstSlot, 1);
			}
			else
			{
				client.deleteItem(1511, secondSlot, 1);
			}

			client.addItem(48, 1);
			client.addSkillXP(100 * client.playerLevel[Player.FLETCHING],
					Player.FLETCHING);
			client.sendMessage("You fletch a longbow.");
		}
		else if (validate(firstId, secondId, 946, 1521))
		{
			if (client.playerLevel[Player.FLETCHING] < 20)
			{
				client.sendMessage("You need a Fletching level of 20 to do this!");
				return;
			}

			if (firstId == 1521)
			{
				client.deleteItem(1521, firstSlot, 1);
			}
			else
			{
				client.deleteItem(1521, secondSlot, 1);
			}

			client.addItem(56, 1);
			client.addSkillXP(200 * client.playerLevel[Player.FLETCHING],
					Player.FLETCHING);
			client.sendMessage("You fletch an oak longbow.");
		}
		else if (validate(firstId, secondId, 946, 1519))
		{
			if (client.playerLevel[Player.FLETCHING] < 40)
			{
				client.sendMessage("You need a Fletching level of 40 to do this!");
				return;
			}

			if (firstId == 1519)
			{
				client.deleteItem(1519, firstSlot, 1);
			}
			else
			{
				client.deleteItem(1519, secondSlot, 1);
			}

			client.addItem(58, 1);
			client.addSkillXP(400 * client.playerLevel[Player.FLETCHING],
					Player.FLETCHING);
			client.sendMessage("You fletch a willow longbow.");
		}
		else if (validate(firstId, secondId, 946, 1517))
		{
			if (client.playerLevel[Player.FLETCHING] < 50)
			{
				client.sendMessage("You need a Fletching level of 50 to do this!");
				return;
			}

			if (firstId == 1517)
			{
				client.deleteItem(1517, firstSlot, 1);
			}
			else
			{
				client.deleteItem(1517, secondSlot, 1);
			}

			client.addItem(62, 1);
			client.addSkillXP(400 * client.playerLevel[Player.FLETCHING],
					Player.FLETCHING);
			client.sendMessage("You fletch a maple longbow.");
		}
		else if (validate(firstId, secondId, 946, 1515))
		{
			if (client.playerLevel[Player.FLETCHING] < 60)
			{
				client.sendMessage("You need a Fletching level of 60 to do this!");
				return;
			}

			if (firstId == 1515)
			{
				client.deleteItem(1515, firstSlot, 1);
			}
			else
			{
				client.deleteItem(1515, secondSlot, 1);
			}

			client.addItem(66, 1);
			client.addSkillXP(500 * client.playerLevel[Player.FLETCHING],
					Player.FLETCHING);
			client.sendMessage("You fletch a yew longbow.");
		}
		else if (validate(firstId, secondId, 946, 1513))
		{
			if (client.playerLevel[Player.FLETCHING] < 80)
			{
				client.sendMessage("You need a Fletching level of 80 to do this!");
				return;
			}

			if (firstId == 1513)
			{
				client.deleteItem(1513, firstSlot, 1);
			}
			else
			{
				client.deleteItem(1513, secondSlot, 1);
			}

			client.addItem(70, 1);
			client.addSkillXP(500 * client.playerLevel[Player.FLETCHING],
					Player.FLETCHING);
			client.sendMessage("You fletch a magic longbow.");
		}
		else
		{
			client.sendMessage("Nothing interesting happens.");
		}
	}

	private boolean validate(int firstId, int secondId, int foo, int bar)
	{
		return ((firstId == foo) && (secondId == bar))
				|| ((firstId == bar) && (secondId == foo));
	}

}