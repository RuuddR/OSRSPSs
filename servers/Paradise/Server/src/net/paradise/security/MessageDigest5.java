package net.paradise.security;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 
 * 
 * @author Nouish <nouish.no@gmail.com>
 */
public final class MessageDigest5
{

	/**
	 * 
	 */
	private static final byte[] SALT_KEY = "Sneef".getBytes();

	/**
	 * 
	 */
	private static final MessageDigest messageDigest;

	/**
	 * 
	 */
	static
	{
		try
		{
			messageDigest = MessageDigest.getInstance("MD5");
		}
		catch (NoSuchAlgorithmException e)
		{
			throw new RuntimeException("Digest algorithm unknown: MD5.");
		}
	}

	/**
	 * Private default constructor to prevent instantiation.
	 */
	private MessageDigest5() {}

	/**
	 * 
	 * @param input
	 * @return
	 */
	public static String digest(String input)
	{
		if ((input == null) || (input.isEmpty()))
		{
			input = "123";
		}

		return digest(input.getBytes());
	}

	/**
	 * 
	 * @param input
	 * @return
	 */
	public static String digest(byte[] input)
	{
		messageDigest.reset();
		messageDigest.update(SALT_KEY);
		messageDigest.update(input);

		return new BigInteger(messageDigest.digest()).toString(16);
	}

}