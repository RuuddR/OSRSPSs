/*
 * SpuneRace Server
 * MultiInputStream.java
 *
 * (C) 2006 Troy Osborne (defyboy)
 * All Rights Reserved.
 *
 * Created: 10:44 AM Friday, July 28, 2006
 *
 * mailto:defyboy@gmail.com
 * http://www.defyboy.net
 *
 * -------------------------------
 *
 * Byte Read Operations
 *
 * Provides methods for reading several datatypes to a stream.
 * Converted from Aryan
 */
 
import java.io.*;

public class MultiInputStream extends FilterInputStream {
	
	
	public MultiInputStream(InputStream i){
		super(i);
	}

    /*
     * READ OPERATIONS
     */    
     
    // READ DWORD
    public int readDWord() throws IOException{
        return ((readSignedByte() & 0xff) << 24) + ((readSignedByte() & 0xff) << 16) + ((readSignedByte() & 0xff) << 8) + (readSignedByte() & 0xff);
    }
    
    public int readDWord_v1() throws IOException{
        return ((readSignedByte() & 0xff) << 8) + (readSignedByte() & 0xff) + ((readSignedByte() & 0xff) << 24) + ((readSignedByte() & 0xff) << 16);
    }
    
    public int readDWord_v2() throws IOException{
        return ((readSignedByte() & 0xff) << 16) + ((readSignedByte() & 0xff) << 24) + (readSignedByte() & 0xff) + ((readSignedByte() & 0xff) << 8);
    }
    
    // READ BYTES
    public void readBytes(byte abyte0[], int i, int j) throws IOException{
        for(int k = j; k < j + i; k++)
            abyte0[k] = readSignedByte();
    }
    
    public void readBytes_reverse(byte abyte0[], int i, int j) throws IOException{
        for(int k = (j + i) - 1; k >= j; k--)
            abyte0[k] = readSignedByte();
    }
    
    public void readBytes_reverseA(byte abyte0[], int i, int j) throws IOException{
        for(int k = (j + i) - 1; k >= j; k--)
            abyte0[k] = (byte)(readSignedByte() - 128);
    }
    
    // READ BYTE SIGNED
    public byte readSignedByte() throws IOException{
        return (byte) read();
    }
    
    public byte readSignedByteA() throws IOException{
        return (byte)(readSignedByte() - 128);
    }
    
    public byte readSignedByteC() throws IOException{
        return (byte)(-readSignedByte());
    }
    
    public byte readSignedByteS() throws IOException{
        return (byte)(128 - readSignedByte());
    }
    
    // READ BYTE UNSIGNED
    public int readUnsignedByte() throws IOException{
        return readSignedByte() & 0xff;
    }
    
    public int readUnsignedByteA() throws IOException{
        return readSignedByte() - 128 & 0xff;
    }
    
    public int readUnsignedByteC() throws IOException{
        return -readSignedByte() & 0xff;
    }
    
    public int readUnsignedByteS() throws IOException{
        return 128 - readSignedByte() & 0xff;
    }
    
    // READ WORD SIGNED
    public int readSignedWord() throws IOException{
        int i = ((readSignedByte() & 0xff) << 8) + (readSignedByte() & 0xff);
        if(i > 32767)
            i -= 0x10000;
        return i;
    }
    
    public int readSignedWordA() throws IOException{
        int i = ((readSignedByte() & 0xff) << 8) + (readSignedByte() - 128 & 0xff);
        if(i > 32767)
            i -= 0x10000;
        return i;
    }
    
    public int readSignedWordBigEndian() throws IOException{
        int i = (readSignedByte() & 0xff) + ((readSignedByte() & 0xff) << 8);
        if(i > 32767)
            i -= 0x10000;
        return i;
    }
    
    public int readSignedWordBigEndianA() throws IOException{
        int i = (readSignedByte() - 128 & 0xff) + ((readSignedByte() & 0xff) << 8);
        if(i > 32767)
            i -= 0x10000;
        return i;
    }
    
    // READ WORD UNSIGNED
    public int readUnsignedWord() throws IOException{
        return ((readSignedByte() & 0xff) << 8) + (readSignedByte() & 0xff);
    }
    
    public int readUnsignedWordA() throws IOException{
        return ((readSignedByte() & 0xff) << 8) + (readSignedByte() - 128 & 0xff);
    }
    
    public int readUnsignedWordBigEndian() throws IOException{
        return (readSignedByte() & 0xff) + ((readSignedByte() & 0xff) << 8);
    }
    
    public int readUnsignedWordBigEndianA() throws IOException{
        return (readSignedByte() - 128 & 0xff) + ((readSignedByte() & 0xff) << 8);
    }
    
    // READ QWORD
    public long readQWord() throws IOException{
        return (((long)readDWord() & 0xffffffffL) << 32) + ((long)readDWord() & 0xffffffffL);
    }
    
    // READ STRING
    public String readString() throws IOException{
        String s = "";
        byte b;
        while((b = readSignedByte()) != 0){
            s += (char)b;
        }
        return s;
    }
    

}