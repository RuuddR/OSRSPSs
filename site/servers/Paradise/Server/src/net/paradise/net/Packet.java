package net.paradise.net;

/**
 * 
 * 
 * @author Nouish <nouish.no@gmail.com>
 */
public class Packet
{

	/**
	 * 
	 */
	private StreamBuffer stream;

	/**
	 * 
	 */
	private int opcode;

	/**
	 * 
	 */
	private int length;

	/**
	 * 
	 * @param stream
	 * @param opcode
	 * @param length
	 */
	protected Packet(StreamBuffer stream, int opcode, int length)
	{
		this.stream = stream;
		this.opcode = opcode;
		this.length = length;
	}

	/**
	 * 
	 * @return
	 */
	public int getOpcode()
	{
		return opcode;
	}

	/**
	 * 
	 * @return
	 */
	public int getLength()
	{
		return length;
	}

	/**
	 * 
	 * @return
	 */
	public StreamBuffer getStream()
	{
		return stream;
	}

}