package server.util;

import server.*;
import server.npcs.*;
import server.players.*;
import server.items.*;
import server.world.*;

// a collection of misc methods
import java.text.NumberFormat;

public class misc {
public static int getCurrentHP(int i, int i1, int i2) {
	double x = (double)i / (double)i1;
	return (int)Math.round(x*i2);
}
	public static int[] buttons_smelting = { 15147, 15146, 10247, 9110, 15151,
			15150, 15149, 15148, 15155, 15154, 15153, 15152, 15159, 15158,
			15157, 15156, 15163, 15162, 15161, 15160, 29017, 29016, 24253,
			16062, 29022, 29020, 29019, 29018, 29026, 29025, 29024, 29023 };
	private static char decodeBuf[] = new char[4096];
	public static byte directionDeltaX[] = new byte[] { 0, 1, 1, 1, 0, -1, -1,
			-1 };
	public static byte directionDeltaY[] = new byte[] { 1, 1, 0, -1, -1, -1, 0,
			1 };
	public static final char playerNameXlateTable[] = { '_', 'a', 'b', 'c',
			'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
			'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2',
			'3', '4', '5', '6', '7', '8', '9' };
	public static String[] rules = { "Offensive language", "Item scamming",
			"Password scamming", "Bug abuse (includes noclip)",
			"Devolution staff impersonation", "Monster luring or abuse",
			"Macroing", "Item Duplication",
			"Encouraging others to break rules", "Misuse of yell channel",
			"Advertising / website", "Possible duped items" };

	public static int[] smelt_bars = { 2349, 2351, 2355, 2353, 2357, 2359,
			2361, 2363 };

	public static int[] smelt_frame = { 2405, 2406, 2407, 2409, 2410, 2411,
			2412, 2413 };
	public static int[] times = { 1, 3, 3, 1, 5, 1, 0, 0, 0, 1, 1, 0, 0 };
	// translates our direction convention to the one used in the protocol
	public static byte xlateDirectionToClient[] = new byte[] { 1, 2, 4, 7, 6,
			5, 3, 0 };
	public static char xlateTable[] = { ' ', 'e', 't', 'a', 'o', 'i', 'h', 'n',
			's', 'r', 'd', 'l', 'u', 'm', 'w', 'c', 'y', 'f', 'g', 'p', 'b',
			'v', 'k', 'x', 'j', 'q', 'z', '0', '1', '2', '3', '4', '5', '6',
			'7', '8', '9', ' ', '!', '?', '.', ',', ':', ';', '(', ')', '-',
			'&', '*', '\\', '\'', '@', '#', '+', '=', '\243', '$', '%', '"',
			'[', ']' };

	// gets the direction between the two given points
	// valid directions are N:0, NE:2, E:4, SE:6, S:8, SW:10, W:12, NW:14
	// the invalid (inbetween) direction are 1,3,5,7,9,11,13,15 i.e. odd
	// integers
	// returns -1, if src and dest are the same
	public static int direction(int srcX, int srcY, int x, int y) {
		double dx = (double)x-srcX,
		dy = (double)y-srcY;
		double angle = Math.atan(dy/dx);
		angle = Math.toDegrees(angle);

		if (Double.isNaN(angle)) return -1;
		if (Math.signum(dx) < 0) angle += 180.0;

		return (int)((((90-angle)/22.5)+16)%16);
	}

	public static String format(int num) {
		return NumberFormat.getInstance().format(num);
	}

	public static String Hex(byte data[]) {
		return Hex(data, 0, data.length);
	}

	public static String Hex(byte data[], int offset, int len) {
		String temp = "";
		for (int cntr = 0; cntr < len; cntr++) {
			int num = data[offset + cntr] & 0xFF;
			String myStr;
			if (num < 16)
				myStr = "0";
			else
				myStr = "";
			temp += myStr + Integer.toHexString(num) + " ";
		}
		return temp.toUpperCase().trim();
	}

	public static int HexToInt(byte data[], int offset, int len) {
		int temp = 0;
		int i = 1000;
		for (int cntr = 0; cntr < len; cntr++) {
			int num = (data[offset + cntr] & 0xFF) * i;
			temp += num;
			if (i > 1)
				i = i / 1000;
		}
		return temp;
	}

	public static String longToPlayerName(long l) {
		int i = 0;
		char ac[] = new char[12];
		while (l != 0L) {
			long l1 = l;
			l /= 37L;
			ac[11 - i++] = playerNameXlateTable[(int) (l1 - l * 37L)];
		}
		return new String(ac, 12 - i, i);
	}

	public static String optimizeText(String text) {
		char buf[] = text.toCharArray();
		boolean endMarker = true; // marks the end of a sentence to make the
		// next char capital
		for (int i = 0; i < buf.length; i++) {
			char c = buf[i];
			if (endMarker && (c >= 'a') && (c <= 'z')) {
				buf[i] -= 0x20; // transform lower case into upper case
				endMarker = false;
			}
			if ((c == '.') || (c == '!') || (c == '?'))
				endMarker = true;
		}
		return new String(buf, 0, buf.length);
	}

	public static long playerNameToInt64(String s) {
		long l = 0L;
		for (int i = 0; (i < s.length()) && (i < 12); i++) {
			char c = s.charAt(i);
			l *= 37L;
			if ((c >= 'A') && (c <= 'Z'))
				l += (1 + c) - 65;
			else if ((c >= 'a') && (c <= 'z'))
				l += (1 + c) - 97;
			else if ((c >= '0') && (c <= '9'))
				l += (27 + c) - 48;
		}
		while ((l % 37L == 0L) && (l != 0L))
			l /= 37L;
		return l;
	}

	public static long playerNameToLong(String s) {
		long l = 0L;
		for (int i = 0; (i < s.length()) && (i < 12); i++) {
			char c = s.charAt(i);
			l *= 37L;
			if ((c >= 'A') && (c <= 'Z')) {
				l += (1 + c) - 65;
			} else if ((c >= 'a') && (c <= 'z')) {
				l += (1 + c) - 97;
			} else if ((c >= '0') && (c <= '9')) {
				l += (27 + c) - 48;
			}
		}
		for (; (l % 37L == 0L) && (l != 0L); l /= 37L)
			;
		return l;
	}

	public static void print(String str) {
		System.out.print(str);
	}

	public static void print_debug(String str) {
		System.out.print(str); // comment this line out if you want
		// to get rid of debug messages
	}

	public static void println(String str) {
		System.out.println(str);
	}

	public static void println_debug(String str) {
		System.out.println(str);
	}

	public static int random(int range) { // 0 till range (range INCLUDED)
		return (int) (java.lang.Math.random() * (range + 1));
	}

	public static int random2(int range) { // 1 till range
		return (int) ((java.lang.Math.random() * range) + 1);
	}

	public static int random3(int range) { // 0 till range
		return (int) (java.lang.Math.random() * range);
	}

	public static int random4(int range) { // 0 till range (range INCLUDED)
		return (int) (java.lang.Math.random() * (range + 1));
	}

	public static void textPack(byte packedData[], java.lang.String text) {
		if (text.length() > 80)
			text = text.substring(0, 80);
		text = text.toLowerCase();

		int carryOverNibble = -1;
		int ofs = 0;
		for (int idx = 0; idx < text.length(); idx++) {
			char c = text.charAt(idx);
			int tableIdx = 0;
			for (int i = 0; i < xlateTable.length; i++) {
				if (c == xlateTable[i]) {
					tableIdx = i;
					break;
				}
			}
			if (tableIdx > 12)
				tableIdx += 195;
			if (carryOverNibble == -1) {
				if (tableIdx < 13)
					carryOverNibble = tableIdx;
				else
					packedData[ofs++] = (byte) (tableIdx);
			} else if (tableIdx < 13) {
				packedData[ofs++] = (byte) ((carryOverNibble << 4) + tableIdx);
				carryOverNibble = -1;
			} else {
				packedData[ofs++] = (byte) ((carryOverNibble << 4) + (tableIdx >> 4));
				carryOverNibble = tableIdx & 0xf;
			}
		}

		if (carryOverNibble != -1)
			packedData[ofs++] = (byte) (carryOverNibble << 4);
	}

	public static String textUnpack(byte packedData[], int size) {
		int idx = 0, highNibble = -1;
		for (int i = 0; i < size * 2; i++) {
			int val = packedData[i / 2] >> (4 - 4 * (i % 2)) & 0xf;
			if (highNibble == -1) {
				if (val < 13)
					decodeBuf[idx++] = xlateTable[val];
				else
					highNibble = val;
			} else {
				decodeBuf[idx++] = xlateTable[((highNibble << 4) + val) - 195];
				highNibble = -1;
			}
		}

		return new String(decodeBuf, 0, idx);
	}
}
