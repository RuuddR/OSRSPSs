package server.players.Skills;

import server.*;
import server.players.Skills.*;
import server.players.*;

public class SkillInterfaces {

	public client client;
	public SkillInterfaces(client c) {
		c = c;
	}

		private int item[] = new int[40];

	public void defInterface(client c) {
		c.CAM().sendFrame126("@dre@Defence Interface", 8716);
		for(int i = 0;i<31;i++) {
			item[i] = 0;
		}
		item[0] = 1117;
		item[1] = 1115;
		item[2] = 1119;
		item[3] = 1121;
		item[4] = 1123;
		item[5] = 1127;
		item[6] = 3140;
		item[7] = 2684;
		for(int i = 8720;i<8799;i++) {
			c.CAM().sendFrame126("",i);
		}
		c.CAM().sendFrame126("1",8720);
		c.CAM().sendFrame126("Bronze Armor",8760);
		c.CAM().sendFrame126("1",8721);
		c.CAM().sendFrame126("Iron Armor", 8761);
		c.CAM().sendFrame126("5",8722);
		c.CAM().sendFrame126("Steel Armor",8762);
		c.CAM().sendFrame126("20",8723);
		c.CAM().sendFrame126("Mithril Armor", 8763);
		c.CAM().sendFrame126("30",8724);
		c.CAM().sendFrame126("Adamant Armor", 8764);
		c.CAM().sendFrame126("40",8725);
		c.CAM().sendFrame126("Rune Armor",8765);
		c.CAM().sendFrame126("60",8726);
		c.CAM().sendFrame126("Dragon Armor", 8766);
		c.CAM().sendFrame126("99",8727);
		c.CAM().sendFrame126("Defence Skillscape", 8767);
		c.CAM().sendFrame126("",8849);// members only skill
		c.CAM().sendFrame126("",8827);//steel
		c.CAM().sendFrame126("",8837);//mithril
		c.CAM().sendFrame126("",8840);
		c.CAM().sendFrame126("",8843);
		c.CAM().sendFrame126("Attack",8846); // first option
		c.CAM().sendFrame126("Defence",8823);
		c.CAM().sendFrame126("Mining",8824);
		c.CAM().sendFrame126("",8859);
		c.CAM().sendFrame126("",8862);
		c.CAM().sendFrame126("",8865);
		c.CAM().sendFrame126("",15303);
		c.CAM().sendFrame126("",15306);
		c.CAM().sendFrame126("",15309);
		
		c.CAM().sendSkillInterface(item);
		c.CAM().showInterface(8714);
	}
	public void AttInterface(client c) {
		c.CAM().sendFrame126("@dre@Attack Interface", 8716);
		for(int i = 0;i<31;i++) {
			item[i] = 0;
		}
		item[0] = 1321;
		item[1] = 1323;
		item[2] = 1325;
		item[3] = 1329;
		item[4] = 1331;
		item[5] = 1333;
		item[6] = 4587;
		item[7] = 4151;
		item[8] = 2678;
		for(int i = 8720;i<8799;i++) {
			c.CAM().sendFrame126("",i);
		}
		c.CAM().sendFrame126("1",8720);
		c.CAM().sendFrame126("Bronze Weapons",8760);
		c.CAM().sendFrame126("1",8721);
		c.CAM().sendFrame126("Iron Weapons", 8761);
		c.CAM().sendFrame126("5",8722);
		c.CAM().sendFrame126("Steel Weapons",8762);
		c.CAM().sendFrame126("20",8723);
		c.CAM().sendFrame126("Mithril Weapons", 8763);
		c.CAM().sendFrame126("30",8724);
		c.CAM().sendFrame126("Adamant Weapons", 8764);
		c.CAM().sendFrame126("40",8725);
		c.CAM().sendFrame126("Rune Weapons",8765);
		c.CAM().sendFrame126("60",8726);
		c.CAM().sendFrame126("Dragon Weapons", 8766);
		c.CAM().sendFrame126("70",8727);
		c.CAM().sendFrame126("Whip", 8767);
		c.CAM().sendFrame126("99", 8728);
		c.CAM().sendFrame126("Attack SkillCape", 8768);
		c.CAM().sendFrame126("",8849);// members only skill
		c.CAM().sendFrame126("",8827);//steel
		c.CAM().sendFrame126("",8837);//mithril
		c.CAM().sendFrame126("",8840);
		c.CAM().sendFrame126("",8843);
		c.CAM().sendFrame126("Attack",8846); // first option
		c.CAM().sendFrame126("Defence",8823);
		c.CAM().sendFrame126("Mining",8824);
		c.CAM().sendFrame126("",8859);
		c.CAM().sendFrame126("",8862);
		c.CAM().sendFrame126("",8865);
		c.CAM().sendFrame126("",15303);
		c.CAM().sendFrame126("",15306);
		c.CAM().sendFrame126("",15309);
		
		c.CAM().sendSkillInterface(item);
		c.CAM().showInterface(8714);
	}
	public void miningInterface(client c) {
		c.CAM().sendFrame126("@dre@Mining Interface", 8716);
		for(int i = 0;i<31;i++) {
			item[i] = 0;
		}
		item[0] = 436;
		item[1] = 438;
		item[2] = 440;
		item[3] = 453;
		item[4] = 447;
		item[5] = 449;
		item[6] = 451;
		item[7] = 1265;
		item[8] = 1267;
		item[9] = 1269;
		item[10] = 1273;
		item[11] = 1271;
		item[12] = 1275;
		item[13] = 2723;
		for(int i = 8720;i<8799;i++) {
			c.CAM().sendFrame126("",i);
		}
		c.CAM().sendFrame126("1",8720);
		c.CAM().sendFrame126("Copper Ores",8760);
		c.CAM().sendFrame126("1",8721);
		c.CAM().sendFrame126("Tin Ores", 8761);
		c.CAM().sendFrame126("15",8722);
		c.CAM().sendFrame126("Iron Ores",8762);
		c.CAM().sendFrame126("30",8723);
		c.CAM().sendFrame126("Coal Ores", 8763);
		c.CAM().sendFrame126("50",8724);
		c.CAM().sendFrame126("Mitril Ores", 8764);
		c.CAM().sendFrame126("70",8725);
		c.CAM().sendFrame126("Adamant Ores",8765);
		c.CAM().sendFrame126("70",8726);
		c.CAM().sendFrame126("Rune Ores", 8766);
		c.CAM().sendFrame126("1",8727);
		c.CAM().sendFrame126("Bronze Picks", 8767);
		c.CAM().sendFrame126("1", 8728);
		c.CAM().sendFrame126("Iron Pick", 8768);
		c.CAM().sendFrame126("5", 8729);
		c.CAM().sendFrame126("steel Pick", 8769);
		c.CAM().sendFrame126("30", 8730);
		c.CAM().sendFrame126("Mithril Pick", 8770);
		c.CAM().sendFrame126("30", 8731);
		c.CAM().sendFrame126("Adamant Pick", 8771);
		c.CAM().sendFrame126("40", 8732);
		c.CAM().sendFrame126("Rune Pick", 8772);
		c.CAM().sendFrame126("99", 8733);
		c.CAM().sendFrame126("Minning SkillCape", 8773);		
		c.CAM().sendFrame126("",8849);// members only skill
		c.CAM().sendFrame126("",8827);//steel
		c.CAM().sendFrame126("",8837);//mithril
		c.CAM().sendFrame126("",8840);
		c.CAM().sendFrame126("",8843);
		c.CAM().sendFrame126("Attack",8846); // first option
		c.CAM().sendFrame126("Defence",8823);
		c.CAM().sendFrame126("Mining",8824);
		c.CAM().sendFrame126("",8859);
		c.CAM().sendFrame126("",8862);
		c.CAM().sendFrame126("",8865);
		c.CAM().sendFrame126("",15303);
		c.CAM().sendFrame126("",15306);
		c.CAM().sendFrame126("",15309);
		
		c.CAM().sendSkillInterface(item);
		c.CAM().showInterface(8714);
	}
}