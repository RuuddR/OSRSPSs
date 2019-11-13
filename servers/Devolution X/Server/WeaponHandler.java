import java.io.*;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class WeaponHandler {
/*Weapon Timers are based off of rs's knowledge bases speed bars. There are 10 sections, subtract 2 from 20 per bar,
and then an extra 2 at the end.
Example: [+|+|+|+|+|+| | | | ] 6 full bars out of 10, subtract 12 from 20, another 2, and u get 6 for the speed :)*/

public int WeaponSpeed = 10;

public int SendWeapon(String WeaponName, int FightType) {

WeaponName = WeaponName.replaceAll("_", " ");
WeaponName = WeaponName.trim();

	if (WeaponName.contains("Unarmed")) {
		if (FightType == 2) {
		return 423;
		}
		else {
		return 422;
		}
	}

	else if (WeaponName.contains("Dragon dagger")) {
		if (FightType == 2) {
		return 401;
		}
		else {
		return 401;
		}
	}

	else if (WeaponName.contains("dagger") || WeaponName.contains("pickaxe")) {
		if (FightType == 2) {
		return 451;
		}
		else {
		return 412;
		}
	}

	else if (WeaponName.contains("sword") && !WeaponName.contains("god") && !WeaponName.contains("2h") || WeaponName.contains("mace") || WeaponName.contains("longsword") && !WeaponName.contains("2h") || WeaponName.contains("scimitar")) {
		if (FightType == 3) {
		return 412;
		}
		else {
		return 451;
		}
	}

	else if (WeaponName.contains("axe") && !WeaponName.contains("greataxe") || WeaponName.contains("battleaxe")) {
		return 1833;
	}

	else if (WeaponName.contains("halberd") || WeaponName.contains("spear") && !WeaponName.contains("Guthans")) {
		if (FightType == 2) {
		return 440;
		}
		else {
		return 412;
		}
	}

	else if (WeaponName.contains("anchor")) {
		return 2661;
	}

	else if (WeaponName.contains("2h")) {
		if (FightType == 3) {
		return 406;
		}
		else {
		return 407;
		}
	}

	if(WeaponName.contains("godsword")) // godswords
	{
		return 2890;
	}

	else if (WeaponName.contains("Tzhaar-ket-om")) {
		return 2661;
	}

	else if (WeaponName.contains("Granite maul")) {
		return 1665;
	}

	else if (WeaponName.contains("greataxe")) {
		if (FightType == 3) {
		return 2066;
		}
		else {
		return 2067;
		}
	}

	else if (WeaponName.contains("flail")) {
		return 2062;
	}

	else if (WeaponName.contains("whip")) {
		return 1658;
	}

	else if (WeaponName.contains("Mouse")) {
		return 1658;
	}

	else if (WeaponName.contains("spear") && WeaponName.contains("Guthans")) {
		if (FightType == 3) {
		return 2080;
		}
		else {
		return 2081;
		}
	}

	else if (WeaponName.contains("toktz-xil-ul")) {
		return 1060;
	}

	else if (WeaponName.contains("hammers")) {
		return 2068;
	}

	else {
	return 451;
	}

}

public int WeaponSpeed(String WeaponName) {

WeaponName = WeaponName.replaceAll("_", " ");
WeaponName = WeaponName.trim();

	if (WeaponName.contains("Unarmed")) {
		return 5;
	}

	else if (WeaponName.contains("Dragon dagger")) {
		return 6;
	}
else if (WeaponName.contains("Magic shortbow")) {
		return 4;
	}
else if (WeaponName.contains("Magic longbow")) {
		return 8;
	}
	else if (WeaponName.contains("dagger") || WeaponName.contains("pickaxe")) {
		return 5;
	}

	else if (WeaponName.contains("sword") && !WeaponName.contains("2h") && !WeaponName.contains("god")) {
		return 5;
	}

	else if (WeaponName.contains("mace")) {
		return 6;
	}

	else if (WeaponName.contains("longsword") && !WeaponName.contains("2h") && !WeaponName.contains("god")) {
		return 6;
	}

	else if (WeaponName.contains("scimitar")) {
		return 4;
	}

	else if (WeaponName.contains("axe") && !WeaponName.contains("greataxe")) {
		return 10;
	}

	else if (WeaponName.contains("battleaxe")) {
		return 10;
	}


	if(WeaponName.contains("godsword")) // godswords
	{
		return 7;
	}

	else if (WeaponName.contains("halberd")) {
		return 10;
	}

	else if (WeaponName.contains("spear") && !WeaponName.contains("Guthans")) {
		return 7;
	}

	else if (WeaponName.contains("2h")) {
		return 10;
	}

	else if (WeaponName.contains("Tzhaar-ket-om")) {
		return 10;
	}

	else if (WeaponName.contains("Granite maul")) {
		return 10;
	}

	else if (WeaponName.contains("greataxe")) {
		return 11;
	}

	else if (WeaponName.contains("flail")) {
		return 7;
	}

	else if (WeaponName.contains("whip")) {
		return 5;
	}

	else if (WeaponName.contains("warhammer")) {
		return 8;
	}

	else if (WeaponName.contains("Mouse")) {
		return 5;
	}
	if(WeaponName.contains("god")) // godswords
	{
		return 8;
	}
	else if (WeaponName.contains("spear") && WeaponName.contains("Guthans")) {
		return 7;
	}

	else if (WeaponName.contains("hammers")) {
		return 8;
	}

	else if (WeaponName.contains("staff")) {
		return 6;
	}

	else if (WeaponName.contains("ancient")) {
		return 6;
	}

	else if (WeaponName.contains("shortbow")) {
		return 5;
	}

	else if (WeaponName.contains("Seercull")) {
		return 5;
	}

	else if (WeaponName.contains("Karils crossbow")) {
		return 6;
	}

	else if (WeaponName.contains("dart")) {
		return 4;
	}

	else if (WeaponName.contains("knife")) {
		return 4;
	}

	else if (WeaponName.contains("thrownaxe")) {
		return 6;
	}

	else if (WeaponName.contains("javelin")) {
		return 8;
	}

	else if (WeaponName.contains("crystal")) {
		return 6;
	}

	else {
	return 10;
	}

}

}