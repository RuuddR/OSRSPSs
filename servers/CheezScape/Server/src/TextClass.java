/*
 * SpuneRace Server
 * TextClass.java
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
 * String operations
 *
 * Provides several String Interfaces
 *
 */
 
public class TextClass {

	/*
	 * For getting the hex val of a byte (used for debug)
	 */
	public static String toHex(int i)
	{
		String t = "";
		if(i < 16)
			t += "0";
		t += Integer.toHexString(i);
		return t.toUpperCase();
	}

	/*
	 * From Aryan, converts a string to a long.
	 */
    public static long longForName(String s)
    {
        long l = 0L;
        for(int i = 0; i < s.length() && i < 12; i++)
        {
            char c = s.charAt(i);
            l *= 37L;
            if(c >= 'A' && c <= 'Z')
                l += (1 + c) - 65;
            else
            if(c >= 'a' && c <= 'z')
                l += (1 + c) - 97;
            else
            if(c >= '0' && c <= '9')
                l += (27 + c) - 48;
        }

        for(; l % 37L == 0L && l != 0L; l /= 37L);
        return l;
    }

	/*
	 * From Aryan, long to a string.
	 */
    public static String nameForLong(long l)
    {
        try
        {
            if(l <= 0L || l >= 0x5b5b57f8a98a5dd1L)
                return "invalid_name";
            if(l % 37L == 0L)
                return "invalid_name";
            int j = 0;
            char ac[] = new char[12];
            while(l != 0L) 
            {
                long l1 = l;
                l /= 37L;
                ac[11 - j++] = chars[(int)(l1 - l * 37L)];
            }
            return new String(ac, 12 - j, j);
        }
        catch(RuntimeException _ex)
        {
			_ex.printStackTrace();
        }
        throw new RuntimeException();
    }
    
    public static final char chars[] = {
        '_', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 
        'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 
        't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', 
        '3', '4', '5', '6', '7', '8', '9'
    };
    
}