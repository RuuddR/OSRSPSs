package server.world;

import server.*;
import server.npcs.*;
import server.players.*;
import server.items.*;
import server.util.*;

/* stream - Decompiled by JODE
 * Visit http://jode.sourceforge.net/
 */

public class stream
{
    private static final int frameStackSize = 10;
    private int frameStackPtr = -1;
    private int[] frameStack;
    public byte[] buffer;
    public int currentOffset;
    public int bitPosition;
    public static int[] bitMaskOut = new int[32];
    public Cryption packetEncryption;
    
    public stream() {
	frameStack = new int[10];
	buffer = null;
	currentOffset = 0;
	bitPosition = 0;
	packetEncryption = null;
    }
    
    public stream(byte[] is) {
	frameStack = new int[10];
	buffer = null;
	currentOffset = 0;
	bitPosition = 0;
	packetEncryption = null;
	buffer = is;
	currentOffset = 0;
    }
    
    public byte readSignedByteA() {
	return (byte) (buffer[currentOffset++] - 128);
    }
    
    public byte readSignedByteC() {
	return (byte) -buffer[currentOffset++];
    }
    
    public byte readSignedByteS() {
	return (byte) (128 - buffer[currentOffset++]);
    }
    
    public int readUnsignedByteA() {
	return buffer[currentOffset++] - 128 & 0xff;
    }
    
    public int readUnsignedByteC() {
	return -buffer[currentOffset++] & 0xff;
    }
    
    public int readUnsignedByteS() {
	return 128 - buffer[currentOffset++] & 0xff;
    }
    
    public void writeByteA(int i) {
	buffer[currentOffset++] = (byte) (i + 128);
    }
    
    public void writeByteS(int i) {
	buffer[currentOffset++] = (byte) (128 - i);
    }
    
    public void writeByteC(int i) {
	buffer[currentOffset++] = (byte) -i;
    }
    public void writeByteD(int i) {
	buffer[currentOffset++] = (byte) +i;
    }
    
    public int readSignedWordBigEndian() {
	currentOffset += 2;
	int i = (((buffer[currentOffset - 1] & 0xff) << 8)
		 + (buffer[currentOffset - 2] & 0xff));
	if (i > 32767)
	    i -= 65536;
	return i;
    }
    
    public int readSignedWordA() {
	currentOffset += 2;
	int i = (((buffer[currentOffset - 2] & 0xff) << 8)
		 + (buffer[currentOffset - 1] - 128 & 0xff));
	if (i > 32767)
	    i -= 65536;
	return i;
    }
    
    public int readSignedWordBigEndianA() {
	currentOffset += 2;
	int i = (((buffer[currentOffset - 1] & 0xff) << 8)
		 + (buffer[currentOffset - 2] - 128 & 0xff));
	if (i > 32767)
	    i -= 65536;
	return i;
    }
    
    public int readUnsignedWordBigEndian() {
	currentOffset += 2;
	return (((buffer[currentOffset - 1] & 0xff) << 8)
		+ (buffer[currentOffset - 2] & 0xff));
    }
    
    public int readUnsignedWordA() {
	currentOffset += 2;
	return (((buffer[currentOffset - 2] & 0xff) << 8)
		+ (buffer[currentOffset - 1] - 128 & 0xff));
    }
    
    public int readUnsignedWordBigEndianA() {
	currentOffset += 2;
	return (((buffer[currentOffset - 1] & 0xff) << 8)
		+ (buffer[currentOffset - 2] - 128 & 0xff));
    }
    
    public void writeWordBigEndianA(int i) {
	buffer[currentOffset++] = (byte) (i + 128);
	buffer[currentOffset++] = (byte) (i >> 8);
    }
    
    public void writeWordA(int i) {
	buffer[currentOffset++] = (byte) (i >> 8);
	buffer[currentOffset++] = (byte) (i + 128);
    }
    
    public void writeWordBigEndian_dup(int i) {
	buffer[currentOffset++] = (byte) i;
	buffer[currentOffset++] = (byte) (i >> 8);
    }
    
    public int readDWord_v1() {
	currentOffset += 4;
	return (((buffer[currentOffset - 2] & 0xff) << 24)
		+ ((buffer[currentOffset - 1] & 0xff) << 16)
		+ ((buffer[currentOffset - 4] & 0xff) << 8)
		+ (buffer[currentOffset - 3] & 0xff));
    }
    
    public int readDWord_v2() {
	currentOffset += 4;
	return (((buffer[currentOffset - 3] & 0xff) << 24)
		+ ((buffer[currentOffset - 4] & 0xff) << 16)
		+ ((buffer[currentOffset - 1] & 0xff) << 8)
		+ (buffer[currentOffset - 2] & 0xff));
    }
    
    public void writeDWord_v1(int i) {
	buffer[currentOffset++] = (byte) (i >> 8);
	buffer[currentOffset++] = (byte) i;
	buffer[currentOffset++] = (byte) (i >> 24);
	buffer[currentOffset++] = (byte) (i >> 16);
    }
    
    public void writeDWord_v2(int i) {
	buffer[currentOffset++] = (byte) (i >> 16);
	buffer[currentOffset++] = (byte) (i >> 24);
	buffer[currentOffset++] = (byte) i;
	buffer[currentOffset++] = (byte) (i >> 8);
    }
    
    public void readBytes_reverse(byte[] is, int i, int i_0_) {
	for (int i_1_ = i_0_ + i - 1; i_1_ >= i_0_; i_1_--)
	    is[i_1_] = buffer[currentOffset++];
    }
    
    public void writeBytes_reverse(byte[] is, int i, int i_2_) {
	for (int i_3_ = i_2_ + i - 1; i_3_ >= i_2_; i_3_--)
	    buffer[currentOffset++] = is[i_3_];
    }
    
    public void readBytes_reverseA(byte[] is, int i, int i_4_) {
	for (int i_5_ = i_4_ + i - 1; i_5_ >= i_4_; i_5_--)
	    is[i_5_] = (byte) (buffer[currentOffset++] - 128);
    }
    
    public void writeBytes_reverseA(byte[] is, int i, int i_6_) {
	for (int i_7_ = i_6_ + i - 1; i_7_ >= i_6_; i_7_--)
	    buffer[currentOffset++] = (byte) (is[i_7_] + 128);
    }
    
    public void createFrame(int i) {
	try {
		buffer[currentOffset++] = (byte) (i + packetEncryption.getNextKey());
	} catch (Exception exception) {
	    /* empty */
	}
    }
    
    public void createFrameVarSize(int i) {
	try {
		buffer[currentOffset++] = (byte) (i + packetEncryption.getNextKey());
		buffer[currentOffset++] = (byte) 0;
		if (frameStackPtr >= 9) {
	  		throw new RuntimeException("Stack overflow");
		}
		frameStack[++frameStackPtr] = currentOffset;
	} catch (Exception exception) {
	    /* empty */
	}
    }
    
    public void createFrameVarSizeWord(int i) {
	try {
	    buffer[currentOffset++]
		= (byte) (i + packetEncryption.getNextKey());
	    writeWord(0);
	    if (frameStackPtr >= 9)
		throw new RuntimeException("Stack overflow");
	    frameStack[++frameStackPtr] = currentOffset;
	} catch (Exception exception) {
	    /* empty */
	}
    }
    
    public void endFrameVarSize() {
	try {
	    if (frameStackPtr < 0)
		throw new RuntimeException("Stack empty");
	    writeFrameSize(currentOffset - frameStack[frameStackPtr--]);
	} catch (Exception exception) {
	    /* empty */
	}
    }
    
    public void endFrameVarSizeWord() {
	try {
	    if (frameStackPtr < 0)
		throw new RuntimeException("Stack empty");
	    writeFrameSizeWord(currentOffset - frameStack[frameStackPtr--]);
	} catch (Exception exception) {
	    /* empty */
	}
    }
    
    public void writeByte(int i) {
	buffer[currentOffset++] = (byte) i;
    }
    
    public void writeWord(int i) {
	buffer[currentOffset++] = (byte) (i >> 8);
	buffer[currentOffset++] = (byte) i;
    }
    
    public void writeWordBigEndian(int i) {
	buffer[currentOffset++] = (byte) i;
	buffer[currentOffset++] = (byte) (i >> 8);
    }
    
    public void write3Byte(int i) {
	buffer[currentOffset++] = (byte) (i >> 16);
	buffer[currentOffset++] = (byte) (i >> 8);
	buffer[currentOffset++] = (byte) i;
    }
    
    public void writeDWord(int i) {
	buffer[currentOffset++] = (byte) (i >> 24);
	buffer[currentOffset++] = (byte) (i >> 16);
	buffer[currentOffset++] = (byte) (i >> 8);
	buffer[currentOffset++] = (byte) i;
    }
    
    public void writeDWordBigEndian(int i) {
	buffer[currentOffset++] = (byte) i;
	buffer[currentOffset++] = (byte) (i >> 8);
	buffer[currentOffset++] = (byte) (i >> 16);
	buffer[currentOffset++] = (byte) (i >> 24);
    }
    
    public void writeQWord(long l) {
	buffer[currentOffset++] = (byte) (int) (l >> 56);
	buffer[currentOffset++] = (byte) (int) (l >> 48);
	buffer[currentOffset++] = (byte) (int) (l >> 40);
	buffer[currentOffset++] = (byte) (int) (l >> 32);
	buffer[currentOffset++] = (byte) (int) (l >> 24);
	buffer[currentOffset++] = (byte) (int) (l >> 16);
	buffer[currentOffset++] = (byte) (int) (l >> 8);
	buffer[currentOffset++] = (byte) (int) l;
    }
    
    public void writeString(String string) {
	System.arraycopy(string.getBytes(), 0, buffer, currentOffset,
			 string.length());
	currentOffset += string.length();
	buffer[currentOffset++] = (byte) 10;
    }
    
    public void writeBytes(byte[] is, int i, int i_8_) {
	for (int i_9_ = i_8_; i_9_ < i_8_ + i; i_9_++)
	    buffer[currentOffset++] = is[i_9_];
    }
    
    public void writeFrameSize(int i) {
	buffer[currentOffset - i - 1] = (byte) i;
    }
    
    public void writeFrameSizeWord(int i) {
	buffer[currentOffset - i - 2] = (byte) (i >> 8);
	buffer[currentOffset - i - 1] = (byte) i;
    }
    
    public int readUnsignedByte() {
	return buffer[currentOffset++] & 0xff;
    }
    
    public byte readSignedByte() {
	return buffer[currentOffset++];
    }
    
    public int readUnsignedWord() {
	currentOffset += 2;
	return (((buffer[currentOffset - 2] & 0xff) << 8)
		+ (buffer[currentOffset - 1] & 0xff));
    }
    
    public int readSignedWord() {
	currentOffset += 2;
	int i = (((buffer[currentOffset - 2] & 0xff) << 8)
		 + (buffer[currentOffset - 1] & 0xff));
	if (i > 32767)
	    i -= 65536;
	return i;
    }
    
    public int readDWord() {
	currentOffset += 4;
	return (((buffer[currentOffset - 4] & 0xff) << 24)
		+ ((buffer[currentOffset - 3] & 0xff) << 16)
		+ ((buffer[currentOffset - 2] & 0xff) << 8)
		+ (buffer[currentOffset - 1] & 0xff));
    }
    
    public long readQWord() {
	long l = (long) readDWord() & 0xffffffffL;
	long l_10_ = (long) readDWord() & 0xffffffffL;
	return (l << 32) + l_10_;
    }
    
    public long readQWord2() {
	currentOffset += 8;
	return (long) (((buffer[currentOffset - 8] & 0xff) << 56)
		       + ((buffer[currentOffset - 7] & 0xff) << 48)
		       + ((buffer[currentOffset - 6] & 0xff) << 40)
		       + ((buffer[currentOffset - 5] & 0xff) << 32)
		       + ((buffer[currentOffset - 4] & 0xff) << 24)
		       + ((buffer[currentOffset - 3] & 0xff) << 16)
		       + ((buffer[currentOffset - 2] & 0xff) << 8)
		       + (buffer[currentOffset - 1] & 0xff));
    }
    
    public String readString() {
	int i = currentOffset;
	while (buffer[currentOffset++] != 10) {
	    /* empty */
	}
	return new String(buffer, i, currentOffset - i - 1);
    }
    
    public void readBytes(byte[] is, int i, int i_11_) {
	for (int i_12_ = i_11_; i_12_ < i_11_ + i; i_12_++)
	    is[i_12_] = buffer[currentOffset++];
    }
    
    public void initBitAccess() {
	bitPosition = currentOffset * 8;
    }
    
    public void writeBits(int i, int i_13_) {
	int i_14_ = bitPosition >> 3;
	int i_15_ = 8 - (bitPosition & 0x7);
	bitPosition += i;
	for (/**/; i > i_15_; i_15_ = 8) {
	    buffer[i_14_] &= bitMaskOut[i_15_] ^ 0xffffffff;
	    buffer[i_14_++] |= i_13_ >> i - i_15_ & bitMaskOut[i_15_];
	    i -= i_15_;
	}
	if (i == i_15_) {
	    buffer[i_14_] &= bitMaskOut[i_15_] ^ 0xffffffff;
	    buffer[i_14_] |= i_13_ & bitMaskOut[i_15_];
	} else {
	    buffer[i_14_] &= bitMaskOut[i] << i_15_ - i ^ 0xffffffff;
	    buffer[i_14_] |= (i_13_ & bitMaskOut[i]) << i_15_ - i;
	}
    }
    
    public void finishBitAccess() {
	currentOffset = (bitPosition + 7) / 8;
    }
    
    static {
	for (int i = 0; i < 32; i++)
	    bitMaskOut[i] = (1 << i) - 1;
    }
}
