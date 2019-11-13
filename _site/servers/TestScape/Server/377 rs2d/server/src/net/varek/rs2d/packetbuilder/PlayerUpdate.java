/*
 * PlayerUpdate.java
 *
 * Created on 17-Dec-2007, 09:22:58
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package net.varek.rs2d.packetbuilder;

import net.varek.rs2d.model.ChatMessage;
import net.varek.rs2d.model.Equipment;
import net.varek.rs2d.model.Mob;
import net.varek.rs2d.model.Player;
import net.varek.rs2d.net.Packet;
import net.varek.rs2d.util.StatefulEntityCollection;

/**
 *
 * @author alex
 */
public class PlayerUpdate implements PacketBuilder {
	Player playerToUpdate = null;
	
	public synchronized Packet getPacket(Player player) {
		playerToUpdate = player;
		
		/**
		 * This packet will be the one we send to the client.
		 */
		StaticPacketBuilder mainPacket = new StaticPacketBuilder()
				.setId(90).setSize(Packet.Size.VariableShort);
		
		/**
		 * This packet is just something we're going to use to construct the
		 * update block, since that comes after the movement updates.
		 * When it's finished, we copy it to mainPacket.
		 */
		StaticPacketBuilder updatePacket = new StaticPacketBuilder();
		
		int mask = getUpdateMask(player, player);
		
		//Logger.log("Getting update for "+player+", mask="+mask);
		
		appendMovementUpdate(mainPacket, player, mask);
		appendUpdateBlock(updatePacket, player, mask);
		
		StatefulEntityCollection<Player> players = player.getWatchedPlayers();
		mainPacket.addBits(players.size(), 8); //player count
		//Logger.log("\tUpdating "+players.size()+" players");
		// Update/remove players.
		for(Player p : players.getKnownEntities()) {
			if(!players.isRemoving(p)) {
				int thisPlayersMask = getUpdateMask(player, p);
				boolean thisPlayerMapAreaChanged = p.isMapAreaChanged();
				p.setMapAreaChanged(false);
				appendMovementUpdate(mainPacket, p, thisPlayersMask);
				p.setMapAreaChanged(thisPlayerMapAreaChanged);
				appendUpdateBlock(updatePacket, p, thisPlayersMask);
			} else {
				mainPacket.addBits(1, 1);
				mainPacket.addBits(3, 2);
				//Logger.log("\tRemoved: "+p);
			}
		}
		
		// Here's where we should add new players.
		for(Player p : players.getNewEntities()) {
			addPlayer(player, p, mainPacket, updatePacket);
			//Logger.log("\tAdded: "+p);
		}
		
		if(updatePacket.getLength() > 0) {
			mainPacket.addBits(2047, 11); // magic byte saying we has no players to add
			mainPacket.addBytes(updatePacket.toPacket().getData());
		}
		
		return mainPacket.toPacket();
	}
	
	private void appendMovementUpdate(StaticPacketBuilder spb, Player player, int updateMask) {
		if(updateMask > 0 || player.isMapAreaChanged() || player.hasMoved()) {
			spb.addBits(1, 1);

			/**
			 * self Update type is sent as 2 bits.
			 * Only one of these is sent at once.
			 * 0: No data to send
			 * 1: 3 bits for direction then 1 bit for update required (?)
			 * 2: 3 bits for 1st dir, 3 bits for 2nd dir, then 1 bit for update required (?)
			 * 3: 2 bits for height, 1 bit for whether to discard the walk queue,
			 *		then 1 bit for update required (?), then 7 bits for y position (local probably)
			 *		and 7 bits for x position (local probably).
			 */
			if(player.isMapAreaChanged()) {
				spb.addBits(3, 2); // update type 3
				spb.addBits(1, 1); // if true, discard the client walking queue
				spb.addBits(player.getLocation().getHeight(), 2); // point height
				spb.addBits(player.getLocation().getLocalY(), 7);
				spb.addBits(player.getLocation().getLocalX(), 7);
                spb.addBits(updateMask > 0 ? 1 : 0, 1); // is the update required?
			} else if(player.hasMoved()) {
				if(player.isRunning() && player.getLastSprite() != -1) {
					spb.addBits(2, 2); // update type 2
					spb.addBits(Mob.CLIENT_SPRITES[player.getLastSprite()], 3); // direction 1
					spb.addBits(Mob.CLIENT_SPRITES[player.getSprite()], 3); // direction 2
					spb.addBits(updateMask > 0 ? 1 : 0, 1); // is the update required?
				} else {
					spb.addBits(1, 2); // update type 1
					spb.addBits(Mob.CLIENT_SPRITES[player.getSprite()], 3); // direction
					spb.addBits(updateMask > 0 ? 1 : 0, 1); // is the update required?
				}
			} else {
				spb.addBits(0, 2); //don't need to update anything and an update is required
			}
		} else {
			spb.addBits(0, 1);
		}
	}
	
	private void appendUpdateBlock(StaticPacketBuilder spb, Player player, int updateMask) {
		if(updateMask == 0)
			return;
		if(updateMask >= 256) {
			updateMask |= 20;
			spb.addByte((byte)(updateMask & 0xff))
					.addByte((byte)(updateMask >> 8));
		} else {
			spb.addByte((byte)updateMask);
			//Logger.log("[updating "+playerToUpdate+"] sent update mask "+updateMask+" for "+player);
		}
		
		if((updateMask & 40) != 0) { // Player chat
			ChatMessage cm = player.getLastChatMessage();
			spb.addShort(((cm.getColour()&0xff) << 8) + (cm.getEffects()&0xFF));
			spb.addByteC((byte)0);
			spb.addByteA((byte)-cm.getMessage().length);
			byte[] msg = cm.getMessage();
            spb.addBytesA(msg);
		}
		
		if((updateMask & 4) != 0) { // Player appearance
			StaticPacketBuilder appearance = new StaticPacketBuilder();
			appearance.addByte((byte)0) //sex
					.addByte((byte)-1) //mask
                    .addByte((byte)-1); // prayer

            Equipment e = player.getEquipment();
			if(e.isEquipped(Equipment.HAT))
				appearance.addShort(512 + e.getId(Equipment.HAT));
			else appearance.addByte((byte)0);

			if(e.isEquipped(Equipment.CAPE))
				appearance.addShort(512 + e.getId(Equipment.CAPE));
			else appearance.addByte((byte)0);

			if(e.isEquipped(Equipment.AMULET))
				appearance.addShort(512 + e.getId(Equipment.AMULET));
			else appearance.addByte((byte)0);

			if(e.isEquipped(Equipment.WEAPON))
				appearance.addShort(512 + e.getId(Equipment.WEAPON));
			else appearance.addByte((byte)0);

			if(e.isEquipped(Equipment.TORSO))
				appearance.addShort(512 + e.getId(Equipment.TORSO));
			else appearance.addShort(256 + player.getAppearance().getTorso());

			if(e.isEquipped(Equipment.SHIELD))
				appearance.addShort(512 + e.getId(Equipment.SHIELD));
			else appearance.addByte((byte)0);

					/*if (!Item.isPlate(playerEquipment[playerChest]))
					playerProps.writeWord(0x100+pArms);
					else
					playerProps.writeByte(0);*/
			appearance.addShort(256+player.getAppearance().getArms());

			if(e.isEquipped(Equipment.LEGS))
				appearance.addShort(512 + e.getId(Equipment.LEGS));
			else appearance.addShort(256 + player.getAppearance().getLegs());

					/*if (!Item.isFullHelm(playerEquipment[playerHat]) && !Item.isFullMask(playerEquipment[playerHat]))
					playerProps.writeWord(0x100 + pHead);		// head
					else
					playerProps.writeByte(0);*/
			appearance.addShort(256+player.getAppearance().getHead());

			if(e.isEquipped(Equipment.HANDS))
				appearance.addShort(512 + e.getId(Equipment.HANDS));
			else appearance.addShort(256 + player.getAppearance().getHands());

			if(e.isEquipped(Equipment.FEET))
				appearance.addShort(512 + e.getId(Equipment.FEET));
			else appearance.addShort(256 + player.getAppearance().getFeet());

			appearance.addByte((byte)0); //end byte?

			appearance.addByte((byte)player.getAppearance().getHairColour())
					.addByte((byte)player.getAppearance().getTorsoColour())
					.addByte((byte)player.getAppearance().getLegColour())
					.addByte((byte)player.getAppearance().getFeetColour())
					.addByte((byte)player.getAppearance().getSkinColour());

			for(int i : ANIMATION_INDICES)
				appearance.addShort(i);

            appearance.addLong(player.getUsernameHash());
			
			appearance.addByte((byte)player.getCombatLevel());
			appearance.addShort((byte)0);
			Packet appearancePacket = appearance.toPacket();
			spb.addByte((byte) appearancePacket.getLength());
			spb.addBytes_reverse(appearancePacket.getData());//, 0, appearancePacket.getLength());
		}
	}
	
	private void addPlayer(Player thisPlayer, Player player, StaticPacketBuilder main, StaticPacketBuilder update) {
		int		dX = player.getLocation().getX() - thisPlayer.getLocation().getX(),
				dY = player.getLocation().getY() - thisPlayer.getLocation().getY();
		if(dX < 0) dX += 32;
		if(dY < 0) dY += 32;
		main.addBits(player.getIndex(), 11)
                .addBits(dX, 5)
                .addBits(1, 1) //do we want to add the update block
                .addBits(1, 1) //discard clients walking queue
                .addBits(dY, 5);
        appendUpdateBlock(update, player, getUpdateMask(thisPlayer, player));
    }
	
	private int getUpdateMask(Player player, Player target) {
		int updateMask = 0;
		if(target.getLastChatMessage() != null && !player.equals(target))
			updateMask |= 40;
		if(player.getKnownAppearanceId(target) < target.getAppearance().getId()) {
			updateMask |= 4;
			player.setKnownAppearanceId(target, target.getAppearance().getId());
		}
		return updateMask;
	}
	
	private static final int[] ANIMATION_INDICES = {
		0x328, 0x337, 0x333, 0x334, 0x335, 0x336, 0x338
	};
}
