package net.varek.rs2d.net;

import net.varek.rs2d.model.Player;
import net.varek.rs2d.packetbuilder.StaticPacketBuilder;

/**
 * These classes simplify network tasks, so you shouldn't have to mess about 
 * with StaticPacketBuilders yourself.
 * Those packets whose purpose I don't understand are left as 'sendUnknownId'.
 * 
 * NOTE: These packets DO NOT modify player state. They simply send data.
 */
public class ActionSender {

	private Player player;
	
	public ActionSender(Player player) {
		this.player = player;
	}
	
	public void sendMapArea() {
		player.getIoSession().write(new StaticPacketBuilder().setId(222)
				.addShort(player.getLocation().getRegionY() + 6)
				.addShortBigEndianA(player.getLocation().getRegionX() + 6).toPacket());
	}
	
	public void sendLoginResponse(int loginResponse, int playerGroup, int flag) {
		player.getIoSession().write(new StaticPacketBuilder().setBare(true)
				.addByte((byte)loginResponse)
				.addByte((byte)playerGroup)
				.addByte((byte)flag).toPacket());
	}
	
	public void sendLoginPacket() {
		player.getIoSession().write(new StaticPacketBuilder().setId(126) //first packet
					.addByte((byte)1) //1 = members
					.addShortBigEndian(player.getIndex()) //player id
					.toPacket());
	}
	
	public void sendMessage(String s) {
		player.getIoSession().write(new StaticPacketBuilder().setId(63).setSize(Packet.Size.VariableByte)
                .addString(s).toPacket());
	}
	
	public void sendPrivacySettings() {
		player.getIoSession().write(new StaticPacketBuilder().setId(201)
				.addByte((byte)player.getPrivacySetting(0))
				.addByte((byte)player.getPrivacySetting(1))
				.addByte((byte)player.getPrivacySetting(2)).toPacket());
	}
	
	public void sendStat(int stat) {
		player.getIoSession().write(new StaticPacketBuilder().setId(49)
				.addByteC((byte)stat)
				.addByte((byte)player.getCurStat(stat))
				.addInt(player.getExps()[stat]).toPacket());
    }
	
	public void sendSideBarInterface(int menuId, int form) {
		player.getIoSession().write(new StaticPacketBuilder().setId(10)
                .addByteS((byte)menuId)
                .addShortA(form).toPacket());
	}
	
	public void sendLogout() {
		player.getIoSession().write(new StaticPacketBuilder().setId(5).toPacket());
	}
	
	public void resetCameraView() {
		player.getIoSession().write(new StaticPacketBuilder().setId(148).toPacket());
	}
	
	public void sendPlayerCommand(int slot, boolean placeOnTop, String command) {
		player.getIoSession().write(new StaticPacketBuilder().setId(157).setSize(Packet.Size.VariableByte)
				.addByteC((byte)(-slot))
                .addString(command)
                .addByte(placeOnTop ? (byte)1 : (byte)0).toPacket());
	}
	
	public void sendLoginWindow(int recoveryChange, boolean memberWarning, int messages, int lastLoginIp, int lastLogin) {
        player.getIoSession().write(new StaticPacketBuilder().setId(76)
                .addShortBigEndian(0)
                .addShortBigEndianA(0)
                .addShort(0)
                .addShort(0)
                .addShortBigEndian(lastLogin)
                .addShortA(messages)
                .addShortA(0)
                .addShort(0)
                .addIntBigEndian(lastLoginIp)
                .addShortBigEndianA(0)
                .addByteS(0)
                .toPacket());
        sendFullScreenInterface(15244, 5993);
    }

    private void sendFullScreenInterface(int interfaceID, int bgInterfaceID) {
        player.getIoSession().write(new StaticPacketBuilder().setId(253)
                .addShortBigEndian(bgInterfaceID)
                .addShortA(interfaceID)
                .toPacket());
    }


    /*public void sendEquipment(int type) {
		int itemId = 0, amount = 0;
		if(player.getEquipment().isEquipped(type)) {
			itemId = player.getEquipment().getId(type);
			amount = player.getEquipment().getAmount(type);
		}
		StaticPacketBuilder equip = new StaticPacketBuilder().setId(34)
				.addShort(1688)
				.addByte((byte)type)
				.addShort(itemId);
		if(amount > 254)
			equip.addByte((byte)255).addInt(amount);
		else equip.addByte((byte)amount);
		player.getIoSession().write(equip.toPacket());
	}*/
}
