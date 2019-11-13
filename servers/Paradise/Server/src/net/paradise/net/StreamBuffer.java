package net.paradise.net;

import net.paradise.util.IsaacRandom;

public class StreamBuffer
{

	public StreamBuffer()
	{
	}

	public StreamBuffer(byte[] buffer)
	{
		this.buffer = buffer;
		offset = 0;
	}

	public void skip(int len)
	{
		offset += len;
	}

	public byte getSignedByteA()
	{
		return (byte) (buffer[offset++] - 128);
	}

	public byte getSignedByteC()
	{
		return (byte) (-buffer[offset++]);
	}

	public byte getSignedByteS()
	{
		return (byte) (128 - buffer[offset++]);
	}

	public int getUnsignedByteA()
	{
		return buffer[offset++] - 128 & 0xff;
	}

	public int getUnsignedByteC()
	{
		return -buffer[offset++] & 0xff;
	}

	public int getUnsignedByteS()
	{
		return 128 - buffer[offset++] & 0xff;
	}

	public void putByteA(int i)
	{
		buffer[offset++] = (byte) (i + 128);
	}

	public void putByteS(int i)
	{
		buffer[offset++] = (byte) (128 - i);
	}

	public void putByteC(int i)
	{
		buffer[offset++] = (byte) (-i);
	}

	public int getSignedWordBigEndian()
	{
		offset += 2;
		int i = ((buffer[offset - 1] & 0xff) << 8)
				+ (buffer[offset - 2] & 0xff);

		if (i > 32767)
		{
			i -= 0x10000;
		}
		return i;
	}

	public int getSignedWordA()
	{
		offset += 2;
		int i = ((buffer[offset - 2] & 0xff) << 8)
				+ (buffer[offset - 1] - 128 & 0xff);

		if (i > 32767)
		{
			i -= 0x10000;
		}
		return i;
	}

	public int getSignedWordBigEndianA()
	{
		offset += 2;
		int i = ((buffer[offset - 1] & 0xff) << 8)
				+ (buffer[offset - 2] - 128 & 0xff);

		if (i > 32767)
		{
			i -= 0x10000;
		}
		return i;
	}

	public int getUnsignedWordBigEndian()
	{
		offset += 2;
		return ((buffer[offset - 1] & 0xff) << 8) + (buffer[offset - 2] & 0xff);
	}

	public int getUnsignedWordA()
	{
		offset += 2;
		return ((buffer[offset - 2] & 0xff) << 8)
				+ (buffer[offset - 1] - 128 & 0xff);
	}

	public int getUnsignedWordBigEndianA()
	{
		offset += 2;
		return ((buffer[offset - 1] & 0xff) << 8)
				+ (buffer[offset - 2] - 128 & 0xff);
	}

	public void putWordBigEndianA(int i)
	{
		buffer[offset++] = (byte) (i + 128);
		buffer[offset++] = (byte) (i >> 8);
	}

	public void putWordA(int i)
	{
		buffer[offset++] = (byte) (i >> 8);
		buffer[offset++] = (byte) (i + 128);
	}

	public void putWordBigEndian_dup(int i)
	{
		buffer[offset++] = (byte) i;
		buffer[offset++] = (byte) (i >> 8);
	}

	public int getDWord_v1()
	{
		offset += 4;
		return ((buffer[offset - 2] & 0xff) << 24)
				+ ((buffer[offset - 1] & 0xff) << 16)
				+ ((buffer[offset - 4] & 0xff) << 8)
				+ (buffer[offset - 3] & 0xff);
	}

	public int getDWord_v2()
	{
		offset += 4;
		return ((buffer[offset - 3] & 0xff) << 24)
				+ ((buffer[offset - 4] & 0xff) << 16)
				+ ((buffer[offset - 1] & 0xff) << 8)
				+ (buffer[offset - 2] & 0xff);
	}

	public void putDWord_v1(int i)
	{
		buffer[offset++] = (byte) (i >> 8);
		buffer[offset++] = (byte) i;
		buffer[offset++] = (byte) (i >> 24);
		buffer[offset++] = (byte) (i >> 16);
	}

	public void putDWord_v2(int i)
	{
		buffer[offset++] = (byte) (i >> 16);
		buffer[offset++] = (byte) (i >> 24);
		buffer[offset++] = (byte) i;
		buffer[offset++] = (byte) (i >> 8);
	}

	public void readBytes_reverse(byte abyte0[], int i, int j)
	{
		for (int k = (j + i) - 1; k >= j; k--)
		{
			abyte0[k] = buffer[offset++];

		}
	}

	public void writeBytes_reverse(byte abyte0[], int i, int j)
	{
		for (int k = (j + i) - 1; k >= j; k--)
		{
			buffer[offset++] = abyte0[k];
		}

	}

	public void readBytes_reverseA(byte abyte0[], int i, int j)
	{
		for (int k = (j + i) - 1; k >= j; k--)
		{
			abyte0[k] = (byte) (buffer[offset++] - 128);
		}

	}

	public void writeBytes_reverseA(byte abyte0[], int i, int j)
	{
		for (int k = (j + i) - 1; k >= j; k--)
		{
			buffer[offset++] = (byte) (abyte0[k] + 128);
		}

	}

	public void createFrame(int id)
	{
		buffer[offset++] = (byte) (id + packetEncryption.nextInt());
	}

	private static final int frameStackSize = 10;
	private int frameStackPtr = -1;
	private int frameStack[] = new int[frameStackSize];

	public void createFrameVarSize(int id)
	{ // creates a variable sized frame
		buffer[offset++] = (byte) (id + packetEncryption.nextInt());
		buffer[offset++] = 0; // placeholder for size byte
		if (frameStackPtr >= frameStackSize - 1)
		{
			throw new RuntimeException("Stack overflow");
		}
		else
		{
			frameStack[++frameStackPtr] = offset;
		}
	}

	public void createFrameVarSizeWord(int id)
	{ // creates a variable sized frame
		buffer[offset++] = (byte) (id + packetEncryption.nextInt());
		putShort(0); // placeholder for size word
		if (frameStackPtr >= frameStackSize - 1)
		{
			throw new RuntimeException("Stack overflow");
		}
		else
		{
			frameStack[++frameStackPtr] = offset;
		}
	}

	public void endFrameVarSize()
	{ // ends a variable sized frame
		if (frameStackPtr < 0)
		{
			throw new RuntimeException("Stack empty");
		}
		else
		{
			writeFrameSize(offset - frameStack[frameStackPtr--]);
		}
	}

	public void endFrameVarSizeWord()
	{
		if (frameStackPtr < 0)
		{
			throw new RuntimeException("Stack empty");
		}
		else
		{
			writeFrameSizeWord(offset - frameStack[frameStackPtr--]);
		}
	}

	public void put(int value)
	{
		buffer[offset++] = (byte) value;
	}

	public void putShort(int value)
	{
		buffer[offset++] = (byte) (value >> 8);
		buffer[offset++] = (byte) value;
	}

	public void putShortBE(int value)
	{
		buffer[offset++] = (byte) value;
		buffer[offset++] = (byte) (value >> 8);
	}

	public void write3Byte(int value)
	{
		buffer[offset++] = (byte) (value >> 16);
		buffer[offset++] = (byte) (value >> 8);
		buffer[offset++] = (byte) value;
	}

	public void putInt(int value)
	{
		buffer[offset++] = (byte) (value >> 24);
		buffer[offset++] = (byte) (value >> 16);
		buffer[offset++] = (byte) (value >> 8);
		buffer[offset++] = (byte) value;
	}

	public void putIntBE(int value)
	{
		buffer[offset++] = (byte) value;
		buffer[offset++] = (byte) (value >> 8);
		buffer[offset++] = (byte) (value >> 16);
		buffer[offset++] = (byte) (value >> 24);
	}

	public void putLong(long value)
	{
		buffer[offset++] = (byte) (int) (value >> 56);
		buffer[offset++] = (byte) (int) (value >> 48);
		buffer[offset++] = (byte) (int) (value >> 40);
		buffer[offset++] = (byte) (int) (value >> 32);
		buffer[offset++] = (byte) (int) (value >> 24);
		buffer[offset++] = (byte) (int) (value >> 16);
		buffer[offset++] = (byte) (int) (value >> 8);
		buffer[offset++] = (byte) (int) value;
	}

	public void putString(String str)
	{
		System.arraycopy(str.getBytes(), 0, buffer, offset, str.length());
		offset += str.length();
		buffer[offset++] = 10;
	}

	public void put(byte[] b, int len, int off)
	{
		for (int i = off; i < off + len; i++)
		{
			buffer[offset++] = b[i];
		}

	}

	public void writeFrameSize(int i)
	{
		buffer[offset - i - 1] = (byte) i;
	}

	public void writeFrameSizeWord(int i)
	{
		buffer[offset - i - 2] = (byte) (i >> 8);
		buffer[offset - i - 1] = (byte) i;
	}

	public int getUnsigned()
	{
		return buffer[offset++] & 0xff;
	}

	public byte get()
	{
		return buffer[offset++];
	}

	public int getUnsignedShort()
	{
		offset += 2;
		return ((buffer[offset - 2] & 0xff) << 8) + (buffer[offset - 1] & 0xff);
	}

	public int getSignedShort()
	{
		offset += 2;
		int i = ((buffer[offset - 2] & 0xff) << 8)
				+ (buffer[offset - 1] & 0xff);

		if (i > 32767)
		{
			i -= 0x10000;
		}
		return i;
	}

	public int getInt()
	{
		offset += 4;
		return ((buffer[offset - 4] & 0xff) << 24)
				+ ((buffer[offset - 3] & 0xff) << 16)
				+ ((buffer[offset - 2] & 0xff) << 8)
				+ (buffer[offset - 1] & 0xff);
	}

	public long readQWord()
	{
		long l = (long) getInt() & 0xffffffffL;
		long l1 = (long) getInt() & 0xffffffffL;

		return (l << 32) + l1;
	}

	public long getLong()
	{
		offset += 8;
		return (((buffer[offset - 8] & 0xff) << 56)
				+ ((buffer[offset - 7] & 0xff) << 48)
				+ ((buffer[offset - 6] & 0xff) << 40)
				+ ((buffer[offset - 5] & 0xff) << 32)
				+ ((buffer[offset - 4] & 0xff) << 24)
				+ ((buffer[offset - 3] & 0xff) << 16)
				+ ((buffer[offset - 2] & 0xff) << 8) + (buffer[offset - 1] & 0xff));
	}

	public String getString()
	{
		int i = offset;
		while (buffer[offset++] != 10)
			;
		return new String(buffer, i, offset - i - 1);
	}

	public void get(byte[] src, int len, int destPos)
	{
		for (int i = destPos; i < destPos + len; i++)
		{
			src[i] = buffer[offset++];
		}
	}

	public void initBitAccess()
	{
		bitPosition = offset * 8;
	}

	public void writeBits(int numBits, int value)
	{
		int bytePos = bitPosition >> 3;
		int bitOffset = 8 - (bitPosition & 7);

		bitPosition += numBits;

		for (; numBits > bitOffset; bitOffset = 8)
		{
			buffer[bytePos] &= ~bitMaskOut[bitOffset]; // mask out the desired
														// area
			buffer[bytePos++] |= (value >> (numBits - bitOffset))
					& bitMaskOut[bitOffset];

			numBits -= bitOffset;
		}
		if (numBits == bitOffset)
		{
			buffer[bytePos] &= ~bitMaskOut[bitOffset];
			buffer[bytePos] |= value & bitMaskOut[bitOffset];
		}
		else
		{
			buffer[bytePos] &= ~(bitMaskOut[numBits] << (bitOffset - numBits));
			buffer[bytePos] |= (value & bitMaskOut[numBits]) << (bitOffset - numBits);
		}
	}

	public void finishBitAccess()
	{
		offset = (bitPosition + 7) / 8;
	}

	public byte buffer[] = null;
	public int offset = 0;
	public int bitPosition = 0;

	public static int bitMaskOut[] = new int[32];
	static
	{
		for (int i = 0; i < 32; i++)
		{
			bitMaskOut[i] = (1 << i) - 1;
		}
	}

	public IsaacRandom packetEncryption = null;

}
