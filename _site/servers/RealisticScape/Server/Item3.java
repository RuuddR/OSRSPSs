import java.io.*;

public class Item3
{

	public static int seed[] = {};

	public static int randomSeed()
	{
		return seed[(int)(Math.random()*seed.length)];
	}

// ---------------------------------------------------------

	public static int BONE[] = {526};

	public static int randomBONE()
	{
		return BONE[(int)(Math.random()*BONE.length)];
	}

// ---------------------------------------------------------

	public static int ASH[] = {592};

	public static int randomASH()
	{
		return ASH[(int)(Math.random()*ASH.length)];
	}

// ---------------------------------------------------------

}