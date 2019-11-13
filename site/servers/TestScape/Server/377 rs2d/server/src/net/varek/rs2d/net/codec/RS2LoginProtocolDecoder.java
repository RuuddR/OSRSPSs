package net.varek.rs2d.net.codec;

import org.apache.mina.common.ByteBuffer;
import org.apache.mina.common.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import net.varek.rs2d.model.Player;
import net.varek.rs2d.model.World;
import net.varek.rs2d.net.Packet;
import net.varek.rs2d.packetbuilder.StaticPacketBuilder;
import net.varek.rs2d.util.Logger;

/**
 * A decoder for the RSC protocol. Parses the incoming data from an
 * IoSession and outputs it as a <code>Packet</code> object.
 */
public class RS2LoginProtocolDecoder extends CumulativeProtocolDecoder {
	
	private static final byte[] STAGE1RESPONSE = new byte[] {
		0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00
	};
	
	/**
	 * Parses the data in the provided byte buffer and writes it to
	 * <code>out</code> as a <code>Packet</code>.
	 *
	 * @param session The IoSession the data was read from
	 * @param in	  The buffer
	 * @param out	 The decoder output stream to which to write the <code>Packet</code>
	 * @return Whether enough data was available to create a packet
	 */
	public boolean doDecode(IoSession session, ByteBuffer in, ProtocolDecoderOutput out) {
		try {
			Object loginStageObj = session.getAttribute("LOGIN_STAGE");
			int loginStage = 0;
			if(loginStageObj != null) {
				loginStage = (Integer)loginStageObj;
			}
			//Logger.log("recv login packet, stage: "+loginStage);
			switch(loginStage) {
			case 0: //first login packets
				if(2 <= in.remaining()) {
					int protocolId = in.get() & 0xff;
					int namePart = in.get() & 0xff;
					long serverSessionKey = ((long) (java.lang.Math.random() * 99999999D) << 32) + (long) (java.lang.Math.random() * 99999999D);
					StaticPacketBuilder s1Response = new StaticPacketBuilder();
					s1Response.setBare(true).addBytes(STAGE1RESPONSE).addLong(serverSessionKey);
					session.setAttribute("SERVER_SESSION_KEY", serverSessionKey);
					session.write(s1Response.toPacket());
					session.setAttribute("LOGIN_STAGE", 1);
					//Logger.log("protocolId="+protocolId+"; namePart="+namePart);
					return true;
				} else {
					in.rewind();
					return false;
				}
			case 1: //here's where we get the username and password
				int loginType = -1, loginPacketSize = -1;
				if(2 <= in.remaining()) {
					loginType = in.get() & 0xff; //should be 16 or 18
					loginPacketSize = in.get() & 0xff;
					//Logger.log("loginType="+loginType);
				} else {
					in.rewind();
					return false;
				}
				if(loginPacketSize <= in.remaining()) {
					byte[] payload = new byte[loginPacketSize];
					in.get(payload);
					Packet p = new Packet(session, -1, payload);
					int loginEncryptPacketSize = loginPacketSize - (36 + 1 + 1 + 2); // can't be negative
                    int loginid = p.readUnsignedByte();
                    int clientVer = p.readShort();
                    if(loginid != 255 || clientVer != 377) {
						// this is bad apparently
					}
                    int lowMemoryVersion = p.readUnsignedByte();
					for(int n=0; n<9; n++) {
						int dataFileSum = p.readInt(); //i don't care personally
					}
					int tmpEncryptPacketSize = p.readUnsignedByte(); //hopefully same as (--loginEncryptPacketSize)
					int encryptPacketId = p.readUnsignedByte(); //hopefully 10
					long clientSessionKey = p.readLong();
					long serverSessionKey = p.readLong();
					int uid = p.readInt(); //unique identifier for this session i think ?
					String	user = p.readRS2String(), //given username
							pass = p.readRS2String(); //given password
					int sessionKey[] = new int[4];
					sessionKey[0] = (int)(clientSessionKey >> 32);
					sessionKey[1] = (int)clientSessionKey;
					sessionKey[2] = (int)(serverSessionKey >> 32);
					sessionKey[3] = (int)serverSessionKey;
					
					session.setAttribute("CRYPTION_IN", new Cryption(sessionKey));
					for(int i = 0; i < 4; i++) sessionKey[i] += 50;
					session.setAttribute("CRYPTION_OUT", new Cryption(sessionKey));
					
					session.removeAttribute("LOGIN_STAGE");
					
					/** 
					 * Here's where we add the user to the login queue, and if the login is 
					 * accepted, we change their session filter to a standard RS2ProtocolCodec.
					 */
					
					session.getFilterChain().remove("protocolFilter");
					session.getFilterChain().addLast("protocolFilter", new ProtocolCodecFilter(new RS2CodecFactory()));
					
					Player player = (Player)session.getAttachment();
					player.setCredentials(user, pass, loginType == 16);
					World.getWorld().getPlayerLoadQueue().addLoad(player);
					return true;
				} else {
					in.rewind();
					return false;
				}
			}
		} catch(Exception e) {
			Logger.err(e);
		}
		return false;
	}
	
	
	
	/**
	 * Releases the buffer used by the given session.
	 *
	 * @param session The session for which to release the buffer
	 * @throws Exception if failed to dispose all resources
	 */
	public void dispose(IoSession session) throws Exception {
		super.dispose(session);
	}
}