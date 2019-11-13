package net.varek.rs2d.net.codec;

import org.apache.mina.common.ByteBuffer;
import org.apache.mina.common.IoSession;
import org.apache.mina.filter.codec.CumulativeProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import net.varek.rs2d.net.Packet;
import net.varek.rs2d.util.Logger;

/**
 * A decoder for the RSC protocol. Parses the incoming data from an
 * IoSession and outputs it as a <code>Packet</code> object.
 */
public class RS2ProtocolDecoder extends CumulativeProtocolDecoder {
	private int[] packetLengths;
	
	public RS2ProtocolDecoder(int[] plen) {
		this.packetLengths = plen;
	}
	
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
			if (in.remaining() >= 1) {
				int id = in.getUnsigned();
				Cryption c = null;
				if((c = (Cryption)session.getAttribute("CRYPTION_IN")) != null) {
					int originalId = id;
					id = (id & 0xff) - c.getNextKey() & 0xff;
					//Logger.log("decrypted "+originalId+"=>"+id);
				} else {
					//Logger.log("not decrypted: "+id);
				}
				int len = packetLengths[id];
				if(len == -1) { //variable length sucka.
					if(in.remaining() >= 1) {
						len = in.get() & 0xff;
						//Logger.log("variable length sucka': "+len);
					} else {
						in.rewind();
						return false;
					}
				}
				if(len < 0) {
					Logger.err("Negative length. uh-oh. stream is gonna fail.");
					return true;
				}
				if (in.remaining() >= len) {
					byte[] payload = new byte[len];
					in.get(payload);
					Packet p = new Packet(session, id, payload);
					out.write(p);
					//Logger.log("recv packet: "+p);
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

