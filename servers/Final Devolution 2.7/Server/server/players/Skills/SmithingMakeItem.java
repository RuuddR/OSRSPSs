package server.players.Skills;

import server.players.client;
import server.*;
import server.util.*;
import server.players.*;

public class SmithingMakeItem implements Skill {
	
	public int item;
	public int xp;
	public int remove;
	public int removeamount;
	public int maketimes;

	@Override
	public boolean doaction(client c, int toadd, int toremove, int toremove2, 
			int timestomake, int NOTUSED, int NOTUSED2, int xp) {
		int maketimes = timestomake;
		c.closeInterface();
		if (c.playerHasItem(toremove, toremove2))
		{
			c.startAnimation(898);
		if (maketimes > 1 && c.playerHasItem(toremove, toremove2 * 2))
		{
		c.CAM().sendMessage("You make some " + c.getItemName(toadd) +"s");
		}
		else
		{
			c.CAM().sendMessage("You make a " + c.getItemName(toadd));
		}
		while (maketimes > 0)
		{
			if (c.playerHasItem(toremove, toremove2))
			{
		c.deleteItem2(toremove, toremove2);
		if (c.getItemName(toadd).contains("dart"))
		{
			c.addItem(toadd, 10);
		}
		else if (c.getItemName(toadd).contains("nail"))
		{
			c.addItem(toadd, 15);
		}
		else if (c.getItemName(toadd).contains("arrow"))
		{
			c.addItem(toadd, 15);
		}
		else if (c.getItemName(toadd).contains("knife"))
		{
			c.addItem(toadd, 5);
		}
		else if (c.getItemName(toadd).contains("cannon"))
				{
			c.addItem(toadd, 4);
				}
		else
		{
		c.addItem(toadd, 1);
		}
		c.addSkillXP(xp * server.SERVER_EXPERIENCE, 13);
		maketimes--;
		//try {
			//Thread.sleep(500);
		//} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		//}
			}
			else
			{
				break;
			}
		}
		}
		else
		{
			c.CAM().sendMessage("You don't have enough bars to make this item!");
			return false;
		}
		return true;
	}
	
	public void createItemTimes(client c, int toadd, int toremove, int toremove2, int xp)
	{
	}
	
	public SmithingMakeItem(client c, int id, int amount)
	{
		readInput(c.playerLevel[13], Integer.toString(id), c, amount, -1, -1);
	}

	@Override
	public void out(String s, client c) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void readInput(int level, String type, client c, int amounttomake,
			int NOTUSED, int NOTUSED2) {
		//System.out.println(type);
		
		if (c.getItemName(Integer.parseInt(type)).contains("Bronze"))
		{
			CheckBronze(c, level, amounttomake, type);
		}
		else if (c.getItemName(Integer.parseInt(type)).contains("Iron"))
		{
			CheckIron(c, level, amounttomake, type);
		}
		else if (c.getItemName(Integer.parseInt(type)).contains("Steel"))
		{
			CheckSteel(c, level, amounttomake, type);
		}
		else if (c.getItemName(Integer.parseInt(type)).contains("Mith"))
		{
			CheckMith(c, level, amounttomake, type);
		}
		else if (c.getItemName(Integer.parseInt(type)).contains("Adam") || c.getItemName(Integer.parseInt(type)).contains("Addy"))
		{
			CheckAddy(c, level, amounttomake, type);
		}
		else if (c.getItemName(Integer.parseInt(type)).contains("Rune"))
		{
			CheckRune(c, level, amounttomake, type);
		}
		else
		{
			c.CAM().sendMessage("INVALID ARMOUR");
			return;
		}
		
	}
	
	private void CheckIron(client c, int level, int amounttomake, String type) {
		remove = 2351;
		
		if (type.equalsIgnoreCase("1349") && level >= 16) //Axe
		{
			xp = 25;
			item = 1349;
			removeamount = 1;
			maketimes = amounttomake;
		}
		
		else if (type.equalsIgnoreCase("1203") && level >= 15) //Dagger
		{
			xp = 25;
			item = 1203;
			removeamount = 1;
			maketimes = amounttomake;
		}
		
		else if (type.equals("1420") && level >= 17) //Mace
		{
			xp = 25;
			item = 1420;
			removeamount = 1;
			maketimes = amounttomake;
		}
		else if (type.equals("1137") && level >= 18) //Med helm
		{
			xp = 25;
			item = 1137;
			removeamount = 1;
			maketimes = amounttomake;
		}
		else if (type.equals("820") && level >= 19) //Dart tips
		{
			xp = 25;
			item = 820;
			removeamount = 1;
			maketimes = amounttomake;
		}
		
		else if (type.equals("1279") && level >= 19) //Sword (s)
		{
			xp = 25;
			item = 1277;
			removeamount = 1;
			maketimes = amounttomake;
		}
		
		else if (type.equals("4820") && level >= 19) //Nails
		{
			xp = 25;
			item = 4820;
			removeamount = 1;
			maketimes = amounttomake;
		}
		
		else if (type.equals("40") && level >= 20) //Arrow tips
		{
			xp = 25;
			item = 40;
			removeamount = 1;
			maketimes = amounttomake;
		}
		
		else if (type.equals("1323") && level >= 20)//Scim
		{
			xp = 50;
			item = 1323;
			removeamount = 2;
			maketimes = amounttomake;
		}
		
		else if (type.equals("1293") && level >= 21) //Longsword
		{
			xp = 50;
			item = 1293;
			removeamount = 2;
			maketimes = amounttomake;
		}
		
		else if (type.equals("863") && level >= 22) //Knives
		{
			xp = 25;
			item = 865;
			removeamount = 1;
			maketimes = amounttomake;
		}
		
		else if (type.equals("1153") && level >= 22) //Full Helm
		{
			xp = 50;
			item = 1153;
			removeamount = 2;
			maketimes = amounttomake;
		}
		
		else if (type.equals("1175") && level >= 23) //Square shield
		{
			xp = 50;
			item = 1175;
			removeamount = 2;
			maketimes = amounttomake;
		}
		
		else if (type.equals("1335") && level >= 24) //Warhammer
		{
			xp = 38;
			item = 1335;
			removeamount = 3;
			maketimes = amounttomake;
		}
		
		else if (type.equals("1363") && level >= 25) //Battle axe
		{
			xp = 75;
			item = 1363;
			removeamount = 3;
			maketimes = amounttomake;
		}
		else if (type.equals("1101") && level >= 26) //Chain
		{
			xp = 75;
			item = 1101;
			removeamount = 3;
			maketimes = amounttomake;
		}
		
		else if (type.equals("1191") && level >= 27) //Kite
		{
			xp = 75;
			item = 1191;
			removeamount = 3;
			maketimes = amounttomake;
		}
		else if (type.equals("3096") && level >= 28) //Claws
		{
			xp = 50;
			item = 3096;
			removeamount = 2;
			maketimes = amounttomake;
		}
		else if (type.equals("1309") && level >= 29) //2h Sword
		{
			xp = 75;
			item = 1309;
			removeamount = 3;
			maketimes = amounttomake;
		}
		else if (type.equals("1067") && level >= 31) //Platelegs
		{
			xp = 75;
			item = 1067;
			removeamount = 3;
			maketimes = amounttomake;
		}
		else if (type.equals("1081") && level >= 31) //PlateSkirt
		{
			xp = 75;
			item = 1081;
			removeamount = 3;
			maketimes = amounttomake;
		}
		else if (type.equals("1115") && level >= 33) //Platebody
		{
			xp = 100;
			item = 1115;
			removeamount = 5;
			maketimes = amounttomake;
		}
		else
		{
			c.CAM().sendMessage("You don't have a high enough level to make this Item!");
			return;
		}
		
		doaction(c, item, remove, removeamount, maketimes, -1, -1, xp);
		
	}

	private void CheckSteel(client c, int level, int amounttomake, String type) {
		remove = 2353;
		
		if (type.equalsIgnoreCase("1353") && level >= 31) //Axe
		{
			xp = 38;
			item = 1353;
			removeamount = 1;
			maketimes = amounttomake;
		}
		
		else if (type.equalsIgnoreCase("1207") && level >= 30) //Dagger
		{
			xp = 50;
			item = 1207;
			removeamount = 1;
			maketimes = amounttomake;
		}
		
		else if (type.equals("1424") && level >= 32) //Mace
		{
			xp = 50;
			item = 1424;
			removeamount = 1;
			maketimes = amounttomake;
		}
		else if (type.equals("1141") && level >= 33) //Med helm
		{
			xp = 50;
			item = 1141;
			removeamount = 1;
			maketimes = amounttomake;
		}
		else if (type.equals("821") && level >= 34) //Dart tips
		{
			xp = 50;
			item = 821;
			removeamount = 1;
			maketimes = amounttomake;
		}
		
		else if (type.equals("1281") && level >= 34) //Sword (s)
		{
			xp = 50;
			item = 1281;
			removeamount = 1;
			maketimes = amounttomake;
		}
		
		else if (type.equals("1539") && level >= 34) //Nails
		{
			xp = 50;
			item = 1539;
			removeamount = 1;
			maketimes = amounttomake;
		}
		
		else if (type.equals("41") && level >= 35) //Arrow tips
		{
			xp = 50;
			item = 41;
			removeamount = 1;
			maketimes = amounttomake;
		}
		
		else if (type.equals("1325") && level >= 35)//Scim
		{
			xp = 75;
			item = 1325;
			removeamount = 2;
			maketimes = amounttomake;
		}
		
		else if (type.equals("1295") && level >= 36) //Longsword
		{
			xp = 75;
			item = 1295;
			removeamount = 2;
			maketimes = amounttomake;
		}
		
		else if (type.equals("865") && level >= 37) //Knives
		{
			xp = 50;
			item = 865;
			removeamount = 1;
			maketimes = amounttomake;
		}
		
		else if (type.equals("1157") && level >= 37) //Full Helm
		{
			xp = 75;
			item = 1157;
			removeamount = 2;
			maketimes = amounttomake;
		}
		
		else if (type.equals("1177") && level >= 38) //Square shield
		{
			xp = 75;
			item = 1177;
			removeamount = 2;
			maketimes = amounttomake;
		}
		
		else if (type.equals("1339") && level >= 39) //Warhammer
		{
			xp = 113;
			item = 1339;
			removeamount = 3;
			maketimes = amounttomake;
		}
		
		else if (type.equals("1365") && level >= 40) //Battle axe
		{
			xp = 113;
			item = 1365;
			removeamount = 3;
			maketimes = amounttomake;
		}
		else if (type.equals("1105") && level >= 41) //Chain
		{
			xp = 113;
			item = 1105;
			removeamount = 3;
			maketimes = amounttomake;
		}
		
		else if (type.equals("1193") && level >= 42) //Kite
		{
			xp = 113;
			item = 1193;
			removeamount = 3;
			maketimes = amounttomake;
		}
		else if (type.equals("3097") && level >= 43) //Claws
		{
			xp = 75;
			item = 3097;
			removeamount = 2;
			maketimes = amounttomake;
		}
		else if (type.equals("1311") && level >= 44) //2h Sword
		{
			xp = 113;
			item = 1311;
			removeamount = 3;
			maketimes = amounttomake;
		}
		else if (type.equals("1069") && level >= 46) //Platelegs
		{
			xp = 113;
			item = 1069;
			removeamount = 3;
			maketimes = amounttomake;
		}
		else if (type.equals("1083") && level >= 46) //PlateSkirt
		{
			xp = 113;
			item = 1083;
			removeamount = 3;
			maketimes = amounttomake;
		}
		else if (type.equals("1119") && level >= 48) //Platebody
		{
			xp = 188;
			item = 1119;
			removeamount = 5;
			maketimes = amounttomake;
		}
		else
		{
			c.CAM().sendMessage("You don't have a high enough level to make this Item!");
			return;
		}
		
		doaction(c, item, remove, removeamount, maketimes, -1, -1, xp);
		
	}

	private void CheckMith(client c, int level, int amounttomake, String type) {
		remove = 2359;
		
		if (type.equalsIgnoreCase("1355") && level >= 51) //Axe
		{
			xp = 50;
			item = 1355;
			removeamount = 1;
			maketimes = amounttomake;
		}
		
		else if (type.equalsIgnoreCase("1209") && level >= 50) //Dagger
		{
			xp = 50;
			item = 1209;
			removeamount = 1;
			maketimes = amounttomake;
		}
		
		else if (type.equals("1428") && level >= 52) //Mace
		{
			xp = 50;
			item = 1428;
			removeamount = 1;
			maketimes = amounttomake;
		}
		else if (type.equals("1143") && level >= 53) //Med helm
		{
			xp = 50;
			item = 1143;
			removeamount = 1;
			maketimes = amounttomake;
		}
		else if (type.equals("822") && level >= 54) //Dart tips
		{
			xp = 50;
			item = 822;
			removeamount = 1;
			maketimes = amounttomake;
		}
		
		else if (type.equals("1285") && level >= 54) //Sword (s)
		{
			xp = 50;
			item = 1285;
			removeamount = 1;
			maketimes = amounttomake;
		}
		
		else if (type.equals("4822") && level >= 54) //Nails
		{
			xp = 50;
			item = 4822;
			removeamount = 1;
			maketimes = amounttomake;
		}
		
		else if (type.equals("42") && level >= 55) //Arrow tips
		{
			xp = 50;
			item = 42;
			removeamount = 1;
			maketimes = amounttomake;
		}
		
		else if (type.equals("1329") && level >= 55)//Scim
		{
			xp = 100;
			item = 1329;
			removeamount = 2;
			maketimes = amounttomake;
		}
		
		else if (type.equals("1299") && level >= 56) //Longsword
		{
			xp = 100;
			item = 1299;
			removeamount = 2;
			maketimes = amounttomake;
		}
		
		else if (type.equals("866") && level >= 57) //Knives
		{
			xp = 50;
			item = 866;
			removeamount = 1;
			maketimes = amounttomake;
		}
		
		else if (type.equals("1159") && level >= 57) //Full Helm
		{
			xp = 100;
			item = 1159;
			removeamount = 2;
			maketimes = amounttomake;
		}
		
		else if (type.equals("1181") && level >= 58) //Square shield
		{
			xp = 100;
			item = 1181;
			removeamount = 2;
			maketimes = amounttomake;
		}
		
		else if (type.equals("1343") && level >= 59) //Warhammer
		{
			xp = 150;
			item = 1343;
			removeamount = 3;
			maketimes = amounttomake;
		}
		
		else if (type.equals("1369") && level >= 60) //Battle axe
		{
			xp = 150;
			item = 1369;
			removeamount = 3;
			maketimes = amounttomake;
		}
		else if (type.equals("1109") && level >= 61) //Chain
		{
			xp = 150;
			item = 1109;
			removeamount = 3;
			maketimes = amounttomake;
		}
		
		else if (type.equals("1197") && level >= 62) //Kite
		{
			xp = 150;
			item = 1197;
			removeamount = 3;
			maketimes = amounttomake;
		}
		else if (type.equals("3099") && level >= 63) //Claws
		{
			xp = 100;
			item = 3099;
			removeamount = 2;
			maketimes = amounttomake;
		}
		else if (type.equals("1315") && level >= 64) //2h Sword
		{
			xp = 150;
			item = 1315;
			removeamount = 3;
			maketimes = amounttomake;
		}
		else if (type.equals("1071") && level >= 66) //Platelegs
		{
			xp = 150;
			item = 1071;
			removeamount = 3;
			maketimes = amounttomake;
		}
		else if (type.equals("1085") && level >= 66) //PlateSkirt
		{
			xp = 150;
			item = 1085;
			removeamount = 3;
			maketimes = amounttomake;
		}
		else if (type.equals("1121") && level >= 68) //Platebody
		{
			xp = 250;
			item = 1121;
			removeamount = 5;
			maketimes = amounttomake;
		}
		else
		{
			c.CAM().sendMessage("You don't have a high enough level to make this Item!");
			return;
		}
		
		doaction(c, item, remove, removeamount, maketimes, -1, -1, xp);
		
		
	}

	private void CheckRune(client c, int level, int amounttomake, String type) {
remove = 2363;
		
		if (type.equalsIgnoreCase("1359") && level >= 86) //Axe
		{
			xp = 75;
			item = 1359;
			removeamount = 1;
			maketimes = amounttomake;
		}
		
		else if (type.equalsIgnoreCase("1213") && level >= 85) //Dagger
		{
			xp = 75;
			item = 1213;
			removeamount = 1;
			maketimes = amounttomake;
		}
		
		else if (type.equals("1432") && level >= 87) //Mace
		{
			xp = 75;
			item = 1432;
			removeamount = 1;
			maketimes = amounttomake;
		}
		else if (type.equals("1147") && level >= 88) //Med helm
		{
			xp = 75;
			item = 1147;
			removeamount = 1;
			maketimes = amounttomake;
		}
		else if (type.equals("824") && level >= 89) //Dart tips
		{
			xp = 75;
			item = 824;
			removeamount = 1;
			maketimes = amounttomake;
		}
		
		else if (type.equals("1289") && level >= 89) //Sword (s)
		{
			xp = 75;
			item = 1289;
			removeamount = 1;
			maketimes = amounttomake;
		}
		
		else if (type.equals("4824") && level >= 89) //Nails
		{
			xp = 75;
			item = 4824;
			removeamount = 1;
			maketimes = amounttomake;
		}
		
		else if (type.equals("44") && level >= 90) //Arrow tips
		{
			xp = 75;
			item = 44;
			removeamount = 1;
			maketimes = amounttomake;
		}
		
		else if (type.equals("1333") && level >= 90)//Scim
		{
			xp = 150;
			item = 1333;
			removeamount = 2;
			maketimes = amounttomake;
		}
		
		else if (type.equals("1303") && level >= 91) //Longsword
		{
			xp = 150;
			item = 1303;
			removeamount = 2;
			maketimes = amounttomake;
		}
		
		else if (type.equals("868") && level >= 92) //Knives
		{
			xp = 75;
			item = 868;
			removeamount = 1;
			maketimes = amounttomake;
		}
		
		else if (type.equals("1163") && level >= 92) //Full Helm
		{
			xp = 150;
			item = 1163;
			removeamount = 2;
			maketimes = amounttomake;
		}
		
		else if (type.equals("1185") && level >= 93) //Square shield
		{
			xp = 150;
			item = 1185;
			removeamount = 2;
			maketimes = amounttomake;
		}
		
		else if (type.equals("1347") && level >= 94) //Warhammer
		{
			xp = 225;
			item = 1347;
			removeamount = 3;
			maketimes = amounttomake;
		}
		
		else if (type.equals("1373") && level >= 95) //Battle axe
		{
			xp = 225;
			item = 1373;
			removeamount = 3;
			maketimes = amounttomake;
		}
		else if (type.equals("1113") && level >= 96) //Chain
		{
			xp = 225;
			item = 1113;
			removeamount = 3;
			maketimes = amounttomake;
		}
		
		else if (type.equals("1201") && level >= 97) //Kite
		{
			xp = 225;
			item = 1201;
			removeamount = 3;
			maketimes = amounttomake;
		}
		else if (type.equals("3101") && level >= 98) //Claws
		{
			xp = 150;
			item = 3101;
			removeamount = 2;
			maketimes = amounttomake;
		}
		else if (type.equals("1319") && level >= 99) //2h Sword
		{
			xp = 225;
			item = 1319;
			removeamount = 3;
			maketimes = amounttomake;
		}
		else if (type.equals("1079") && level >= 99) //Platelegs
		{
			xp = 225;
			item = 1079;
			removeamount = 3;
			maketimes = amounttomake;
		}
		else if (type.equals("1093") && level >= 99) //PlateSkirt
		{
			xp = 225;
			item = 1093;
			removeamount = 3;
			maketimes = amounttomake;
		}
		else if (type.equals("1127") && level >= 99) //Platebody
		{
			xp = 313;
			item = 1127;
			removeamount = 5;
			maketimes = amounttomake;
		}
		else
		{
			c.CAM().sendMessage("You don't have a high enough level to make this Item!");
			return;
		}
		
		doaction(c, item, remove, removeamount, maketimes, -1, -1, xp);
		
	}

	private void CheckAddy(client c, int level, int amounttomake, String type) {
remove = 2361;
		
		if (type.equalsIgnoreCase("1357") && level >= 71) //Axe
		{
			xp = 63;
			item = 1357;
			removeamount = 1;
			maketimes = amounttomake;
		}
		
		else if (type.equalsIgnoreCase("1211") && level >= 70) //Dagger
		{
			xp = 63;
			item = 1211;
			removeamount = 1;
			maketimes = amounttomake;
		}
		
		else if (type.equals("1430") && level >= 72) //Mace
		{
			xp = 63;
			item = 1430;
			removeamount = 1;
			maketimes = amounttomake;
		}
		else if (type.equals("1145") && level >= 73) //Med helm
		{
			xp = 63;
			item = 1145;
			removeamount = 1;
			maketimes = amounttomake;
		}
		else if (type.equals("823") && level >= 74) //Dart tips
		{
			xp = 63;
			item = 823;
			removeamount = 1;
			maketimes = amounttomake;
		}
		
		else if (type.equals("1287") && level >= 74) //Sword (s)
		{
			xp = 63;
			item = 1287;
			removeamount = 1;
			maketimes = amounttomake;
		}
		
		else if (type.equals("4823") && level >= 74) //Nails
		{
			xp = 63;
			item = 4823;
			removeamount = 1;
			maketimes = amounttomake;
		}
		
		else if (type.equals("43") && level >= 75) //Arrow tips
		{
			xp = 63;
			item = 43;
			removeamount = 1;
			maketimes = amounttomake;
		}
		
		else if (type.equals("1331") && level >= 75)//Scim
		{
			xp = 125;
			item = 1331;
			removeamount = 2;
			maketimes = amounttomake;
		}
		
		else if (type.equals("1301") && level >= 76) //Longsword
		{
			xp = 125;
			item = 1301;
			removeamount = 2;
			maketimes = amounttomake;
		}
		
		else if (type.equals("867") && level >= 77) //Knives
		{
			xp = 63;
			item = 867;
			removeamount = 1;
			maketimes = amounttomake;
		}
		
		else if (type.equals("1161") && level >= 77) //Full Helm
		{
			xp = 125;
			item = 1161;
			removeamount = 2;
			maketimes = amounttomake;
		}
		
		else if (type.equals("1183") && level >= 78) //Square shield
		{
			xp = 125;
			item = 1183;
			removeamount = 2;
			maketimes = amounttomake;
		}
		
		else if (type.equals("1345") && level >= 79) //Warhammer
		{
			xp = 188;
			item = 1345;
			removeamount = 3;
			maketimes = amounttomake;
		}
		
		else if (type.equals("1371") && level >= 80) //Battle axe
		{
			xp = 188;
			item = 1371;
			removeamount = 3;
			maketimes = amounttomake;
		}
		else if (type.equals("1111") && level >= 81) //Chain
		{
			xp = 188;
			item = 1111;
			removeamount = 3;
			maketimes = amounttomake;
		}
		
		else if (type.equals("1199") && level >= 82) //Kite
		{
			xp = 188;
			item = 1199;
			removeamount = 3;
			maketimes = amounttomake;
		}
		else if (type.equals("3100") && level >= 83) //Claws
		{
			xp = 125;
			item = 3100;
			removeamount = 2;
			maketimes = amounttomake;
		}
		else if (type.equals("1317") && level >= 84) //2h Sword
		{
			xp = 188;
			item = 1317;
			removeamount = 3;
			maketimes = amounttomake;
		}
		else if (type.equals("1073") && level >= 86) //Platelegs
		{
			xp = 188;
			item = 1073;
			removeamount = 3;
			maketimes = amounttomake;
		}
		else if (type.equals("1091") && level >= 86) //PlateSkirt
		{
			xp = 188;
			item = 1091;
			removeamount = 3;
			maketimes = amounttomake;
		}
		else if (type.equals("1123") && level >= 88) //Platebody
		{
			xp = 313;
			item = 1123;
			removeamount = 5;
			maketimes = amounttomake;
		}
		else
		{
			c.CAM().sendMessage("You don't have a high enough level to make this Item!");
			return;
		}
		
		doaction(c, item, remove, removeamount, maketimes, -1, -1, xp);
		
	}

	private void CheckBronze(client c, int level, int amounttomake,String type)
	{
	System.out.println(type);
		if (type.equalsIgnoreCase("1351") && level >= 1)
		{
			xp = 13;
			item = 1351;
			remove = 2349;
			removeamount = 1;
			maketimes = amounttomake;
		}
		
		else if (type.equalsIgnoreCase("1205") && level >= 1)
		{
			xp = 13;
			item = 1205;
			remove = 2349;
			removeamount = 1;
			maketimes = amounttomake;
		}
		
		else if (type.equals("1422") && level >= 2)
		{
			xp = 13;
			item = 1422;
			remove = 2349;
			removeamount = 1;
			maketimes = amounttomake;
		}
		else if (type.equals("1139") && level >= 3)
		{
			xp = 13;
			item = 1139;
			remove = 2349;
			removeamount = 1;
			maketimes = amounttomake;
		}
		else if (type.equals("819") && level >= 4)
		{
			xp = 13;
			item = 819;
			remove = 2349;
			removeamount = 1;
			maketimes = amounttomake;
		}
		
		else if (type.equals("1277") && level >= 4)
		{
			xp = 13;
			item = 1277;
			remove = 2349;
			removeamount = 1;
			maketimes = amounttomake;
		}
		
		else if (type.equals("4819") && level >= 4)
		{
			xp = 13;
			item = 4819;
			remove = 2349;
			removeamount = 1;
			maketimes = amounttomake;
		}
		
		else if (type.equals("39") && level >= 5)
		{
			xp = 13;
			item = 39;
			remove = 2349;
			removeamount = 1;
			maketimes = amounttomake;
		}
		
		else if (type.equals("1321") && level >= 5)
		{
			xp = 25;
			item = 1321;
			remove = 2349;
			removeamount = 2;
			maketimes = amounttomake;
		}
		
		else if (type.equals("1291") && level >= 6)
		{
			xp = 25;
			item = 1291;
			remove = 2349;
			removeamount = 2;
			maketimes = amounttomake;
		}
		
		else if (type.equals("864") && level >= 7)
		{
			xp = 25;
			item = 864;
			remove = 2349;
			removeamount = 1;
			maketimes = amounttomake;
		}
		
		else if (type.equals("1155") && level >= 7)
		{
			xp = 25;
			item = 1155;
			remove = 2349;
			removeamount = 2;
			maketimes = amounttomake;
		}
		
		else if (type.equals("1173") && level >= 8)
		{
			xp = 25;
			item = 1173;
			remove = 2349;
			removeamount = 2;
			maketimes = amounttomake;
		}
		
		else if (type.equals("1337") && level >= 9)
		{
			xp = 38;
			item = 1337;
			remove = 2349;
			removeamount = 3;
			maketimes = amounttomake;
		}
		
		else if (type.equals("1375") && level >= 10)
		{
			xp = 38;
			item = 1375;
			remove = 2349;
			removeamount = 3;
			maketimes = amounttomake;
		}
		else if (type.equals("1103") && level >= 11)
		{
			xp = 38;
			item = 1103;
			remove = 2349;
			//remove2 = 2349;
			//remove3 = 2349;
			//remove4 = -1;
			//remove5 = -1;			
			removeamount = 3;
			maketimes = amounttomake;
		}
		
		else if (type.equals("1189") && level >= 12)
		{
			xp = 38;
			item = 1189;
			remove = 2349;
			removeamount = 3;
			maketimes = amounttomake;
		}
		else if (type.equals("3095") && level >= 13)
		{
			xp = 25;
			item = 3095;
			remove = 2349;
			removeamount = 2;
			maketimes = amounttomake;
		}
		else if (type.equals("1307") && level >= 14)
		{
			xp = 38;
			item = 1307;
			remove = 2349;
			removeamount = 3;
			maketimes = amounttomake;
		}
		else if (type.equals("1075") && level >= 16)
		{
			xp = 38;
			item = 1075;
			remove = 2349;
			removeamount = 3;
			maketimes = amounttomake;
		}
		else if (type.equals("1087") && level >= 16)
		{
			xp = 38;
			item = 1087;
			remove = 2349;
			removeamount = 3;
			maketimes = amounttomake;
		}
		else if (type.equals("1117") && level >= 18)
		{
			xp = 63;
			item = 1117;
			remove = 2349;
			removeamount = 5;
			maketimes = amounttomake;
		}
		else
		{
			c.CAM().sendMessage("You don't have a high enough level to make this Item!");
			return;
		}
		
		doaction(c, item, remove, removeamount, maketimes, -1, -1, xp);
		
	}

}
