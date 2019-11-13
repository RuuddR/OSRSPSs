/*
 * SpuneRace Server
 * FrameWriter.java
 *
 * (C) 2006 Troy Osborne (defyboy)
 * All Rights Reserved.
 *
 * Created: 3:34 PM Saturday, August 12, 2006
 *
 * mailto:defyboy@gmail.com
 * http://www.defyboy.net
 *
 * -------------------------------
 *
 * Frame Writer
 *
 * Class for writing several datatypes to a frame.
 *
 */
 
import java.io.*;

public class FrameWriter extends ByteArrayOutputStream {

	public FrameWriter(int t, int s){
		super(s);
		type = s;
		size = t;
	}
	
	public FrameWriter(int s){
		super();
		type = s;
	}
	
    /*
     * WRITE OPERATIONS
     */    
    
    // WRITE BYTE
    public void writeByte(int i) throws IOException{
		write(i);
    }
    
    public void writeByteA(int i) throws IOException{
        write(i + 28);
    }
    
    public void writeByteS(int i) throws IOException{
        write(128 - i);
    }
    
    public void writeByteC(int i) throws IOException{
        write(-i);
    }
    
    // WRITE WORD
    public void writeWord(int i) throws IOException{
        write(i >> 8);
        write(i);
    }
    
    public void writeWordA(int i) throws IOException{
        write(i >> 8);
        write(i + 128);
    }
    
    public void writeWordBigEndian(int i) throws IOException{
        write(i);
        write(i >> 8);
    }
    
    public void writeWordBigEndianA(int i) throws IOException{
        write(i + 128);
        write(i >> 8);
    }
    
    public void writeWordBigEndian_dup(int i) throws IOException{
        write(i);
        write(i >> 8);
    }
    
    // WRITE DWORD
    public void writeDWord(int i) throws IOException{
        write(i >> 24);
        write(i >> 16);
        write(i >> 8);
        write(i);
    }
    
    public void writeDWordBigEndian(int i) throws IOException{
        write(i);
        write(i >> 8);
        write(i >> 16);
        write(i >> 24);
    }
    
    public void writeDWord_v1(int i) throws IOException{
        writeByte(i >> 8);
        writeByte(i);
        writeByte(i >> 24);
        writeByte(i >> 16);
    }
    
    public void writeDWord_v2(int i) throws IOException{
        write(i >> 16);
        write(i >> 24);
        write(i);
        write(i >> 8);
    }
    
    // WRITE BYTES
    public void writeBytes(byte abyte0[], int i, int j) throws IOException{
        for(int k = j; k < j + i; k++)
            write(abyte0[k]);
    }
    
    public void writeBytes_reverse(byte abyte0[], int i, int j) throws IOException{
        for(int k = (j + i) - 1; k >= j; k--)
            write(abyte0[k]);
    }
    
    public void writeBytes_reverseA(byte abyte0[], int i, int j) throws IOException{
        for(int k = (j + i) - 1; k >= j; k--)
            write(abyte0[k] + 128);
    }
    
    // WRITE QWORD
    public void writeQWord(long l) throws IOException{
        write((int)(l >> 56));
        write((int)(l >> 48));
        write((int)(l >> 40));
        write((int)(l >> 32));
        write((int)(l >> 24));
        write((int)(l >> 16));
        write((int)(l >> 8));
        write((int)(l));
    }
    
    // WRITE STRING
    public void writeString(String s) throws IOException{
        byte b[] = s.getBytes();
        writeBytes(b, b.length, 0);
        write(10);
    }
    
    // WRITE 3BYTES
    public void write3Byte(int i) throws IOException{
        write(i >> 16);
        write(i >> 8);
        write(i);
    }

	public int type;
	public int size = -3;

}