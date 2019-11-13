package net.varek.rs2d.net.codec;

import org.apache.mina.filter.codec.ProtocolCodecFactory;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolEncoder;

/**
 * Provides access to the protocol encoders and decoders
 * for the RSC protocol.
 */
public class RS2CodecFactory implements ProtocolCodecFactory {
	
	/**
	 * The protocol encoder in use
	 */
	private static ProtocolEncoder encoder;
	/**
	 * The protocol decoder in use
	 */
	private static ProtocolDecoder decoder;

	/**
	 * Provides the encoder to use to parse incoming data.
	 *
	 * @return A protocol encoder
	 */
	public ProtocolEncoder getEncoder() {
		return encoder;
	}

	/**
	 * Provides the decoder to use to format outgoing data.
	 *
	 * @return A protocol decoder
	 */
	public ProtocolDecoder getDecoder() {
		return decoder;
	}
	
	static {
		final int[] packetLengthsIn = new int[256];
		packetLengthsIn[1] = 12;
		packetLengthsIn[3] = 6;
		packetLengthsIn[4] = 6;
		packetLengthsIn[6] = 0;
		packetLengthsIn[8] = 2;
		packetLengthsIn[13] = 2;
		packetLengthsIn[19] = 4;
		packetLengthsIn[22] = 2;
		packetLengthsIn[24] = 6;
		packetLengthsIn[28] = -1;		// Walking
		packetLengthsIn[31] = 4;
		packetLengthsIn[36] = 8;
		packetLengthsIn[40] = 0;
		packetLengthsIn[42] = 2;
		packetLengthsIn[45] = 2;
		packetLengthsIn[49] = -1;		// Possible chat?
		packetLengthsIn[50] = 6;
		packetLengthsIn[54] = 6;
		packetLengthsIn[55] = 6;
		packetLengthsIn[56] = -1;		// Admin command
		packetLengthsIn[57] = 8;
		packetLengthsIn[67] = 2;
		packetLengthsIn[71] = 6;
		packetLengthsIn[75] = 4;
		packetLengthsIn[77] = 6;
		packetLengthsIn[78] = 4;
		packetLengthsIn[79] = 2;
		packetLengthsIn[80] = 2;		// Reports signlink errors?
		packetLengthsIn[83] = 8;
		packetLengthsIn[91] = 6;
		packetLengthsIn[95] = 4;
		packetLengthsIn[100] = 6;
		packetLengthsIn[104] = 4;
		packetLengthsIn[110] = 0;
		packetLengthsIn[112] = 2;
		packetLengthsIn[116] = 2;
		packetLengthsIn[119] = 1;
		packetLengthsIn[120] = 8;
		packetLengthsIn[123] = 7;
		packetLengthsIn[126] = 1;
		packetLengthsIn[136] = 6;
		packetLengthsIn[140] = 4;
		packetLengthsIn[141] = 8;
		packetLengthsIn[143] = 8;
		packetLengthsIn[152] = 12;
		packetLengthsIn[157] = 4;
		packetLengthsIn[158] = 6;
		packetLengthsIn[160] = 8;
		packetLengthsIn[161] = 6;
		packetLengthsIn[163] = 13;
		packetLengthsIn[165] = 1;
		packetLengthsIn[168] = 0;
		packetLengthsIn[171] = -1;		// Not sure, but goes into for loop...
		packetLengthsIn[173] = 3;
		packetLengthsIn[176] = 3;
		packetLengthsIn[177] = 6;
		packetLengthsIn[181] = 6;
		packetLengthsIn[184] = 10;
		packetLengthsIn[187] = 1;
		packetLengthsIn[194] = 2;
		packetLengthsIn[197] = 4;
		packetLengthsIn[202] = 0;
		packetLengthsIn[203] = 6;
		packetLengthsIn[206] = 8;
		packetLengthsIn[210] = 8;
		packetLengthsIn[211] = 12;
		packetLengthsIn[213] = -1;		// Walking
		packetLengthsIn[217] = 8;
		packetLengthsIn[222] = 3;
		packetLengthsIn[226] = 2;
		packetLengthsIn[227] = 9;
		packetLengthsIn[228] = 6;
		packetLengthsIn[230] = 6;
		packetLengthsIn[231] = 6;
		packetLengthsIn[233] = 2;
		packetLengthsIn[241] = 6;
		packetLengthsIn[244] = -1;
		packetLengthsIn[245] = 2;
		packetLengthsIn[247] = -1;		// Walking
		packetLengthsIn[248] = 0;
        packetLengthsIn[249] = -1;
        packetLengthsIn[250] = -1;
        packetLengthsIn[251] = -1;
        packetLengthsIn[252] = -1;
        packetLengthsIn[253] = -1;
        packetLengthsIn[254] = -1;
        packetLengthsIn[255] = -1;

        encoder = new RS2ProtocolEncoder(packetLengthsIn);
		decoder = new RS2ProtocolDecoder(packetLengthsIn);
	}
}
