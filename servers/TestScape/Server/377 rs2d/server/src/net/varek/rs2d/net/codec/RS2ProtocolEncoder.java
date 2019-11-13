package net.varek.rs2d.net.codec;

import org.apache.mina.common.ByteBuffer;
import org.apache.mina.common.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;
import net.varek.rs2d.net.Packet;
import net.varek.rs2d.util.Logger;


/**
 * Encodes the high level <code>Packet</code> class into the proper
 * protocol data required for transmission.
 */
public class RS2ProtocolEncoder implements ProtocolEncoder {
	private int[] packetLengths;
	
	public RS2ProtocolEncoder(int[] plen) {
		this.packetLengths = plen;
	}
	
	/**
	 * Converts a <code>Packet</code> object into the raw data needed
	 * for transmission.
	 *
	 * @param session The IO session associated with the packet
	 * @param message A <code>Packet</code> to encode
	 * @param out	 The output stream to which to write the data
	 */
	public void encode(IoSession session, Object message, ProtocolEncoderOutput out) {
		Packet p = (Packet) message;
		try {
			byte[] data = p.getData();
			int dataLength = p.getLength();
			ByteBuffer buffer;
			if (!p.isBare()) {
				buffer = ByteBuffer.allocate(dataLength + 3);
				int id = p.getId();
				Cryption c = null;
				if((c = (Cryption)session.getAttribute("CRYPTION_OUT")) != null) {
					int originalId = id;
					int nextKey = c.getNextKey();
					byte encryptedId = (byte)(id + nextKey);
					//Logger.log("decrypted "+originalId+"=>"+(encryptedId&0xff));
					buffer.put(encryptedId);
				} else {
					buffer.put((byte)id);
				}
				if(p.getSize() != Packet.Size.Fixed) { //variable length
					//Logger.log("variable length: id="+id+",dataLength="+dataLength);
					if(p.getSize() == Packet.Size.VariableByte) {
						if(dataLength > 255) //trying to send more data then we can represent with 8 bits!
							throw new IllegalArgumentException("Tried to send packet length "+dataLength+" in 8 bits [pid="+p.getId()+"]");
						buffer.put((byte)dataLength);
					} else if(p.getSize() == Packet.Size.VariableShort) {
						buffer.put((byte)(dataLength >> 8));
						buffer.put((byte)dataLength);
					}
				}
			} else {
				buffer = ByteBuffer.allocate(dataLength);
			}
			buffer.put(data, 0, dataLength);
			buffer.flip();
			out.write(buffer);
		} catch(Exception e) {
			Logger.err("Error handling message: "+p);
			Logger.err(e);
		}
	}

	/**
	 * Releases all resources used by this encoder.
	 *
	 * @param session The IO session
	 */
	public void dispose(IoSession session) {
	}
}
