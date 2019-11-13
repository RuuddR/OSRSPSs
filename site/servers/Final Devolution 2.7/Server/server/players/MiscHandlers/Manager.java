package server.players.MiscHandlers;

import server.*;
import server.util.*;
import server.world.*;
import server.items.*;
import server.players.*;
import server.players.Packets.*;
import server.players.Quests.*;
import server.players.Skills.*;
import server.players.MiscHandlers.*;

public class Manager {

	private client client;
	
	public Manager(client client) {
		this.client = client;
	}

	public void showCase(String text, String text2, String text3, String text4, String title) {
		sendFrame126(title, 6180);
		sendFrame126(text, 6181);
		sendFrame126(text2, 6182);
		sendFrame126(text3, 6183);
		sendFrame126(text4, 6184);	
		sendFrame164(6179);
	}
	public void showitem(int item, String s1, String s2) {
		sendFrame246(6210, 200, item);
		sendFrame126(s1, 6207);
		sendFrame126(s2, 6208);
		sendFrame164(6206);
	}
	public boolean withinDistance(int distance, int X1, int Y1, int X2, int Y2) {
		return (Math.abs(X1 - X2) < ++distance && Math.abs(Y1 - Y2) < distance);
	}
	public void frame177(int x, int y, int height, int speed, int angle) {
		client.outStream.createFrame(177);
		client.outStream.writeByte(x / 64); // X coord within your loaded map area
		client.outStream.writeByte(y / 64); // Y coord within your loaded map area
		client.outStream.writeWord(height); // HeightLevel
		client.outStream.writeByte(speed); //Camera Speed
		client.outStream.writeByte(angle); //Angle
	}
	public void sendSkillInterface(int id[]) {
		client.outStream.createFrameVarSizeWord(53);
		client.outStream.writeWord(8847); // 8847
		client.outStream.writeWord(id.length);
		for (int i = 0; i < id.length; i++) {
			client.outStream.writeByte(1);
			if(id[i] > 0) {
				client.outStream.writeWordBigEndianA(id[i]+1);
			} else {
				client.outStream.writeWordBigEndianA(0);
			}
		}
		client.outStream.endFrameVarSizeWord();   
		client.flushOutStream();
	}
	public void sendFrame126(String s, int id) {
		if (client == null) {
			return;
		}
		client.outStream.createFrameVarSizeWord(126);
		client.outStream.writeString(s);
		client.outStream.writeWordA(id);
		client.outStream.endFrameVarSizeWord();
		client.flushOutStream();
	}
	public void replaceItem(int oldID, int newID) {
		for(int i2 = 0; i2 < client.playerItems.length; i2++)
		{
			if(client.playerItems[i2] == oldID+1)
			{
				int newamount = client.playerItemsN[i2];
				client.deleteItem(oldID, client.getItemSlot(oldID), client.playerItemsN[i2]);
				client.addItem(newID, newamount);
			}
		}
	}
	public void sendFrame34(int id, int slot, int column, int amount)
	{
		client.outStream.createFrameVarSizeWord(34); // init item to smith screen
		client.outStream.writeWord(column); // Column Across Smith Screen
		client.outStream.writeByte(4); // Total Rows?
		client.outStream.writeDWord(slot); // Row Down The Smith Screen
		client.outStream.writeWord(id+1); // item
		client.outStream.writeByte(amount); // how many there are?
		client.outStream.endFrameVarSizeWord();
	}

	public void sendFrame164(int Frame) {
		client.outStream.createFrame(164);
		client.outStream.writeWordBigEndian_dup(Frame);
		client.flushOutStream();
	}
	public void sendFrame171(int MainFrame, int SubFrame) {
		client.outStream.createFrame(171);
		client.outStream.writeByte(MainFrame);
		client.outStream.writeWord(SubFrame);
		client.flushOutStream();
	}
	public void sendFrame185(int Frame) {
		client.outStream.createFrame(185);
		client.outStream.writeWordBigEndianA(Frame);
		client.flushOutStream();
	}
	public void sendFrame200(int MainFrame, int SubFrame) {
		client.outStream.createFrame(200);
		client.outStream.writeWord(MainFrame);
		client.outStream.writeWord(SubFrame);
		client.flushOutStream();
	}
	public void sendFrame246(int MainFrame, int SubFrame, int SubFrame2) {
		client.outStream.createFrame(246);
		client.outStream.writeWordBigEndian(MainFrame);
		client.outStream.writeWord(SubFrame);
		client.outStream.writeWord(SubFrame2);
		client.flushOutStream();
	}
	public void sendFrame248(int MainFrame, int SubFrame) {
		client.outStream.createFrame(248);
		client.outStream.writeWordA(MainFrame);
		client.outStream.writeWord(SubFrame);
		client.flushOutStream();
	}
	public void sendFrame75(int MainFrame, int SubFrame) {
		client.outStream.createFrame(75);
		client.outStream.writeWordBigEndianA(MainFrame);
		client.outStream.writeWordBigEndianA(SubFrame);
		client.flushOutStream();
	}
	public void sendFrame99(int i1) {
		client.outStream.createFrame(99);
		client.outStream.writeByte(i1);
		client.updateRequired = true;
		client.appearanceUpdateRequired = true;
	}

	public void sendQuest(String s, int id) {
		try {
			client.outStream.createFrameVarSizeWord(126);
			client.outStream.writeString(s);
			client.outStream.writeWordA(id);
			client.outStream.endFrameVarSizeWord();
		} catch (Exception e) {
			server.logError(e.getMessage());
		}
	}
	public void sendMessage(String s) {
		client.outStream.createFrameVarSize(253);
		client.outStream.writeString(s);
		//chatLog("+playerName + " + s +" chatLog");
		client.outStream.endFrameVarSize();	
	}
	public void sendFrame79(int id) {
		client.outStream.createFrame(79);
		client.outStream.writeWordBigEndian(id);
		client.outStream.writeWordA(0);
		client.flushOutStream();
	}
	public void showInterface(int interfaceid) {
		
		client.outStream.createFrame(97);
		client.outStream.writeWord(interfaceid);
		client.flushOutStream();
	}


	public int GetWepAnim() {
		int weapon = client.playerEquipment[client.playerWeapon];
		switch(client.playerEquipment[client.playerWeapon]) {
			
		case 863:
		case 864:
		case 865:
		case 866: // knives
		case 867:
		case 868:
		case 869:
			
		case 806:
		case 807:
		case 808:
		case 809: // darts
		case 810:
		case 811:
			
		case 825:
		case 826:
		case 827: // javelin
		case 828:
		case 829:
		case 830:
			
		case 800:
		case 801:
		case 802:
		case 803: // axes
		case 804:
		case 805:
			return 806;
		}
		if(weapon == -1) // unarmed
		if(client.FightType == 2) // kick
		{
			if(weapon == 5698) // dds
			if(client.FightType == 3) // slash
			{
				return 395;
			}
			if(weapon == 4212 ) //bows made by killamess
			{
				return 426;
			}
			if(weapon == 15156 ) //bows made by killamess
			{
				return 426;
			}
			if((weapon == 861) || (weapon == 4212))
			{
				return 426;
			}
			return 423;
		} else {
			return 422;
		}
		if(weapon == 4151) // whip
		{
			return 1658;
		}
		if(weapon == 8447) // cat toy
		{
			return 1658;
		}
		if(weapon == 868) // throwing knives
		{
			return 385;
		}
		if(weapon == 6527) // obby maul plox
		{
			return 2927;
		}
		if(weapon == 6541) // Mouse Toy
		{
			return 1658;
		}
		if(weapon == 15156) // dark bow
		{
			return 426;
		}
		if(weapon == 1305) // d long
		{
			return 390;
		}
		if(weapon == 15333) // godsword
		{
			return 407;
		}
		if(weapon == 15334) // godsword
		{
			return 407;
		}
		if(weapon == 15335) // godsword
		{
			return 407;
		}
		if(weapon == 15336) // godsword
		{
			return 407;
		}
		if(weapon == 14915) // anchor
		{
			return 406;
		}
		if(weapon == 6739) // dragon axe
		{
			return 390;
		}
		if(weapon == 1321 || weapon == 1323 || weapon == 1325 || weapon == 1327 || weapon == 1329 || weapon == 1327 || weapon == 1321 || weapon == 1333) // scimitars
		{
			return 390;
		}
		if(weapon == 4587) // d scim
		{
			return 390;
		}
		if(weapon == 746) // d scim
		{
			return 390;
		}
		if(weapon == 3204) // dragon halberd
		{
			return 440;
		}
		if(weapon == 6818) // bow-sword
		{
			return 440;
		}
		if(weapon == 3202) // rune halberd
		{
			return 440;
		}
		if(weapon == 4212 || weapon == 859 || weapon == 861 || weapon == 6724) //bows
		{
			return 426;
		}
		if(weapon == 4214 || weapon == 4216 || weapon == 4218 || weapon == 4220 || weapon == 4222) //bows
		{
			return 426;
		}
		if(weapon == 15156) //bows made by killamess
		{
			return 426;
		}
		// 839 841 843 845 847 849 851 853 855 857 859 861
		if(weapon == 839 || weapon == 841 || weapon == 843 || weapon == 845 || weapon == 847 || weapon == 849 || weapon == 851 || weapon == 853 || weapon == 855 || weapon == 857 || weapon == 859 ||  weapon == 861) //bows
		{
			return 426;
		}
		if(weapon == 4153) // maul
		{
			return 1665;
		}
		if(weapon == 6528) // obby maul
		{
			return 2661;
		}
		if(weapon == 5018) // bone club
		{
			return 390;
		}
		if(weapon == 3101) // Rune claws
		{
			return 390;
		}
		if(weapon == 7449) // noob smasher
		{
			return 1665;
		}
		if(weapon == 1377) // dragon b axe
		{
			return 1833;
		}
		if(weapon == 1373) // rune b axe
		{
			return 1833;
		}
		if(weapon == 1434) // dragon mace
		{
			return 1833;
		}
		if(weapon == 5018) // dragon mace
		{
			return 1833;
		}
		if(weapon == 5730) // dragon spear
		{
			return 2080;
		}
		if(weapon == 4718) // dharoks axe
		{
			return 2067;
		}
		if(weapon == 4726) // guthans spear
		{
			return 2080;
		}
		if(weapon == 4747) // torags hammers
		{
			return 2068;
		}
		if(weapon == 4755) // veracs flail
		{
			return 2062;
		}
		if(weapon == 4734) // karils x bow
		{
			return 2075;
		}
		if(weapon == 837) // crossbow
		{
			return 427;
		}
		if(weapon == 10431) // rune crossbow
		{
			return 427;
		}
		if(weapon == 1215 || weapon == 1231 || weapon == 5680 || weapon == 5698) // dragon daggers
		{
			return 402;
		}
		if(weapon == 6609 || weapon == 1307 || weapon == 1309 || weapon == 1311 || weapon == 1313 || weapon == 1315 || weapon == 1317 || weapon == 1319)
		// 2 handers
		{
			return 407;
		}
		if(weapon == 7158) // d2h
		{
			return 407;
		}
		if(weapon == 1319) // r2h
		{
			return 407;
		}
		if(weapon == 1419) // scythe
		{
			return 408;
		}
		if(weapon == 4566) // rubber chicken
		{
			return 1833;
		}
		else
		{
			return 390;
		}
	}
	public int GetRunAnim(int id) {
		if(id == 4151) // whip
		{
			return 1661;
		}
		if(id == 8447) // cat toy
		{
			return 1661;
		}
		if(id == 6818) // bow-sword
		{
			return 744;
		}
		if(id == 4734 || id == 837) // karils x bow
		{
			return 2077;
		}
		if(id == 4153) // maul
		{
			return 1664;
		}
		if(id == 1419) // scythe
		{
			return 1664;
		}
		if(id == 7449) // noobsmasher
		{
			return 1664;
		}
		else
		{
			return 0x338;
		}
	}
	public int GetWalkAnim(int id) {
		if(id == 4718) // dharoks axe
		{
			return 2064;
		}
		if(id == 4039 || id == 4037 || id == 1379 || id == 3204 || id == 3202 || id == 1381 || id == 1383 || id == 1385 || id == 1387 || id == 1389 || id == 1391 || id == 1393 || id == 1395 || id == 1397 || id == 1399 || id == 1401 || id == 1403 || id == 145 || id == 1407 || id == 1409 || id == 3053 || id == 3054 || id == 4170 || id == 4675 || id == 4710 || id == 6526 || id == 4726 || id == 6562 || id == 6563 || id == 6914 || id == 5730) // staves + d long and most other weps with str8 up emote
		{
			return 1146;
		}
		if(client.playerEquipment[client.playerFeet] == 4084) // sled
		{
			return 755;
		}
		if(id == 4565) // basket of eggs :)
		{
			return 1836;
		}
		if(id == 4755) // veracs flail
		{
			return 2060;
		}
		if(id == 4734 || id == 837) // karils x bow
		{
			return 2076;
		}
		if(id == 4153 || id == 15334 || id == 15333 || id == 15334 || id == 15335 || id == 15336 || id == 1419) // maul
		{
			return 1663;
		}
		if(id == 7158 || id == 4718 || id == 1319 || id == 6528 || id == 14915) // 2h + gr8 axe
		{
			return 2064;
		}
		if(id == 7449) // noob smasher
		{
			return 1663;
		}
		if(id == 4151) // whip
		{
			return 1661;
		}
		if(id == 8447) // cat toy
		{
			return 1661;
		}
		else
		{
			return 0x333;
		}
	}
	public int GetStandAnim(int id) {
		if(id == 4718) // dharoks axe
		{
			return 2065;
		}
		if(id == 4755) // veracs flail
		{
			return 2061;
		}
		if(id == 4734 || id == 837) // karils x bow
		{
			return 2074;
		}
		if(id == 4153 || id == 15334 || id == 15333 || id == 15334 || id == 15335 || id == 15336 || id == 1419) // maul
		{
			return 1662;
		}
		if(id == 7449) // noob smasher
		{
			return 1662;
		}
		if(id == 4565) // basket of eggs :)
		{
			return 1836;
		}
		if(id == 1305 || id == 1379 || id == 1381 || id == 1383 || id == 1385 || id == 1387 || id == 1389 || id == 1391 || id == 1393 || id == 1395 || id == 1397 || id == 1399 || id == 1401 || id == 1403 || id == 145 || id == 1407 || id == 1409 || id == 3053 || id == 3054 || id == 4170 || id == 4675 || id == 4710 || id == 6526 || id == 4726 || id == 6562 || id == 6563 || id == 5730) // staves
		{
			return 809;
		}
		if(id == 7158 || id == 1319 || id == 6528 || id == 14915) // 2h
		{
			return 2065;
		}
		if(id == 3204 || id == 3202) // halberd
		{
			return 809;
		}
		else
		{
			return 0x328;
		}
	}		
	public int GetBlockAnim(int id)  {
		id = 403;
		return id;
	}
	public int getAttackDelay() {
		switch(client.playerEquipment[client.playerWeapon]) {
		case -1://fist or foot
			client.weaponDelay =  2400;
			break;
		case 15999://dclaws
			if (!client.specOn) {
				client.weaponDelay = 500;
			} else {
				client.weaponDelay = 0;
			}
			break;
		case 1307://brone 2h
		case 1909://iron 2h
		case 1311://steel 2h
		case 1313://black 2h
		case 1315://mith 2h
		case 1317://addy 2h
		case 1319://rune 2h
		case 6609://white 2h [non bronze-dragon type]
			client.weaponDelay =  4200;
			break;
		case 1215://Dragon dagger (n)
		case 1231://Dragon dagger (p)
		case 5680://Dragon dagger (+)
		case 5698://Dragon dagger (s)
			client.weaponDelay = 2400;
			break;
		case 1419://scythe(holiday)
			client.weaponDelay = 2400;
			break;
		case 4151://whip
			client.weaponDelay = 2400;
			break;
		case 746://dark dagger [non bronze-dragon type]
		case 747://glowing dagger [glowing dark dagger] [non bronze-dragon type]
		case 1203://iron dagger
		case 1205://bronze dagger
		case 1207://steel dagger
		case 1209://mith dagger
		case 1211://addy dagger
		case 1213://rune dagger
		case 1217://black dagger
		case 1219://iron dagger (p)
		case 1221://bronze dagger (p)
		case 1223://steel dagger (p)
		case 1225://mith dagger (p)
		case 1227://addy dagger (p)
		case 1229://rune dagger (p)
		case 1233://black dagger (p)
		case 1235://poisoned_dagger(p) [non bronze-dragon type]
		case 2952://wolfbane [preist in paril]/[non bronze-dragon type]
		case 5668://iron dagger (+)
		case 5670://bronze dagger (+)
		case 5672://steel dagger (+)
		case 5674://mith dagger (+)
		case 5676://addy dagger (+)
		case 5678://rune dagger (+)
		case 5682://black dagger (+)
		case 5684://poisoned_dagger (+) [non bronze-dragon type]
		case 5686://iron dagger (s)
		case 5688://bronze dagger (s)
		case 5690://steel dagger (s)
		case 5692://mith dagger (s)
		case 5694://addy dagger (s)
		case 5696://rune dagger (s)
		case 5700://black dagger (s)
		case 5702://poisoned_dagger (s) [non bronze-dragon type]
		case 6595://white dagger (+) [non bronze-dragon type]
		case 6593://white dagger (p) [non bronze-dragon type]
		case 6597://white dagger (s) [non bronze-dragon type]
		case 6591://white dagger [non bronze-dragon type]
			client.weaponDelay = 2400;
			break;
		case 3190://bronze halberd
		case 3192://iron halberd
		case 3194://steel halberd
		case 3196://black halberd
		case 3197://mith halberd
		case 3200://addy halberd
		case 3202://rune halberd
		case 3204://dragon halberd
		case 6599://white halberd [non bronze-dragon type]
			client.weaponDelay = 4200;
			break;
		case 667://blurite sword [non bronze-dragon type]
		case 1277://bronze sword
		case 1279://iron sword
		case 1281://steel sword
		case 1283://black sword
		case 1285://mith sword
		case 1287://addy sword
		case 1289://rune sword
		case 1305://dragon longsword
		case 1291://bronze longsword
		case 1293://iron longsword
		case 1295://steel longsword
		case 1297://black longsword
		case 1299://mith longsword
		case 1301://addy longsword
		case 1303://rune longsword
		case 6607://white longsword [non bronze-dragon type]
		case 6605://white sword [non bronze-dragon type]
			client.weaponDelay = 3000;
			break;
		case 7158:
		case 4153://granite maul
			client.weaponDelay = 4200;
			break;
		case 1377://dragon battleaxe
			client.weaponDelay = 3600;//1833=dragon_baxe
			break;
		case 6082://fixed device [non bronze-dragon type]
			client.weaponDelay = 4000;//2323=fixed_device_kicking
			break;
		case 4755://veraces flail new
		case 4082://veraces flail 100%
		case 4083://veraces flail 75%
		case 4084://veraces flail 50%
		case 4085://veraces flail 25%
		case 4086://veraces flail 0%
			client.weaponDelay = 3000;
			break;
		case 4718://dharoks greataxe new
		case 4887://dharoks greataxe 100%
		case 4888://dharoks greataxe 75%
		case 4889://dharoks greataxe 50%
		case 4890://dharoks greataxe 25%
		case 4891://dharoks greataxe 0%
		case 15156:// dark bow.
			client.weaponDelay = 4200;
			break;
		case 4958://torags hammers new
		case 4959://torags hammers 100%
		case 4960://torags hammers 75%
		case 4961://torags hammers 50%
		case 4962://torags hammers 25%
		case 4963://torags hammers 0%
			client.weaponDelay = 3000;
			break;
		case 4934://karils X-bow new
		case 4935://karils X-bow 100%
		case 4936://karils X-bow 75%
		case 4937://karils X-bow 50%
		case 4938://karils X-bow 25%
		case 4939://karils X-bow 0%
			client.weaponDelay = 2400;
			break;
		case 4862://ahrims staff new
		case 4863://ahrims staff 100%
		case 4864://ahrims staff 75%
		case 4865://ahrims staff 50%
		case 4866://ahrims staff 25%
		case 4867://ahrims staff 0%
			client.weaponDelay = 3600;
			break;
		case 4910://guthans spear new
		case 4911://guthans spear 100%
		case 4912://guthans spear 75%
		case 4913://guthans spear 50%
		case 4914://guthans spear 25%
		case 4915://guthans spear 0%
			client.weaponDelay = 3000;
			break;
		case 841://shortbow
		case 843://oak shortbow
		case 849://willow shortbow
		case 853://maple shortbow
		case 857://yew shortbow
		case 861://magic shortbow
		case 845://oak longbow
		case 847://willow longbow
		case 851://maple longbow
		case 855://yew longbow
		case 859://magic longbow
		case 4734://karils crossbow
			client.weaponDelay = 1800;
			break;
		case 1363://iron battleaxe
		case 1365://steel battleaxe
		case 1367://black battleaxe
		case 1369://mith battleaxe
		case 1371://addy battleaxe
		case 1373://rune battleaxe
		case 1375://bronze battleaxe
		case 6598://white battleaxe [non bronze-dragon type]
			client.weaponDelay = 3600;
			break;
		case 1321://bronze scimitar
		case 1323://iron scimitar
		case 1325://steel scimitar
		case 1327://black scimitar
		case 1329://mith scimitar
		case 1331://addy scimitar
		case 1333://rune scimitar
		case 4587://dragon scimitar
		case 6611://white scimitar [non bronze-dragon type]
			client.weaponDelay = 2400;
			break;
		case 1237://bronze spear
		case 1239://iron spear
		case 1241://steel spear
		case 4850://black spear
		case 1243://mith spear
		case 1245://addy spear
		case 1247://rune spear
		case 1249://dragon spear
		case 1251://bronze spear (p)
		case 1253://iron spear (p)
		case 1255://steel spear (p)
		case 4852://black spear (p)
		case 1257://mith spear (p)
		case 1259://addy spear (p)
		case 1262://rune spear (p)
		case 1263://dragon spear (p)
		case 3170://bronze spear (kp)
		case 3171://iron spear (kp)
		case 3172://steel spear (kp)
		case 4584://black spear (kp)
		case 3173://mith spear (kp)
		case 3174://addy spear (kp)
		case 3175://rune spear (kp)
		case 3176://dragon spear (kp)
		case 5704://bronze spear (+)
		case 5706://iron spear (+)
		case 5708://steel spear (+)
		case 5734://black spear (+)
		case 5710://mith spear (+)
		case 5712://addy spear (+)
		case 5714://rune spear (+)
		case 5716://dragon spear (+)
		case 5718://bronze spear (s)
		case 5720://iron spear (s)
		case 5722://steel spear (s)
		case 5737://black spear (s)
		case 5724://mith spear (s)
		case 5726://addy spear (s)
		case 5728://rune spear (s)
		case 5730://dragon spear (s)
			client.weaponDelay = 3000;
			break;
		case 1337://bronze warhammer
		case 1335://iron warhammer
		case 1339://steel warhammer
		case 1341://black warhammer
		case 1343://mith warhammer
		case 1345://addy warhammer
		case 1347://rune warhammer
		case 1415://warhammer [non bronze-dragon type]
			client.weaponDelay = 3600;
			break;
		case 1351://bronze woodcutting axe
		case 1349://iron woodcutting axe
		case 1353://steel woodcutting axe
		case 1361://black woodcutting axe
		case 1355://mith woodcutting axe
		case 1357://addy woodcutting axe
		case 1359://rune woodcutting axe
		case 6739://dragon woodcutting axe
			client.weaponDelay = 3600;
			break;
		case 1265://bronze pickaxe
		case 1267://iron pickaxe
		case 1269://steel pickaxe
		case 1271://mith pickaxe
		case 1273://addy pickaxe
		case 1275://rune pickaxe
			client.weaponDelay = 3600;
			break;
		case 141422://bronze mace
		case 1420://iron mace
		case 1424://steel mace
		case 1426://black mace
		case 1428://mith mace
		case 1430://addy mace
		case 1432://rune mace
		case 1434://dragon mace
			client.weaponDelay = 3600;
			break;
		case 772://dramen staff
		case 4170://slayers staff
		case 4675://ancient staff [desert treasure]
		case 6526://Toktz-mej-tal [obsidian staff]
		case 1379://staff
		case 1381://air staff
		case 1383://water staff
		case 1385://earth staff
		case 1387://fire staff
		case 1389://magic staff
		case 1391://battle staff
		case 1394://fire battlestaff
		case 1395://water battlestaff
		case 1397://air battlestaff
		case 1399://earth battlestaff
		case 3053://lava battlestaff
		case 6562://mud battlestaff
		case 3054://mystic lava staff
		case 6563://mystic mud staff
		case 1401://mystic fire staff
		case 1403://mystic water staff
		case 1405://mystic air staff
		case 1407://mystic earth staff
		case 1409://Ibans staff
		case 2415://God Staff1
		case 2416://God Staff2
		case 2417://God Staff3
			client.weaponDelay = 3000;
			break;
		case 4212: //cystal bow
			client.weaponDelay = 2400;
			break;
		case 6528://obby maul
			client.weaponDelay = 3700;
			break;
		case 863:
		case 864:
		case 865:
		case 866: // knives
		case 867:
		case 868:
		case 869:
			
		case 806:
		case 807:
		case 808:
		case 809: // darts
		case 810:
		case 811:
			
		case 825:
		case 826:
		case 827: // javelin
		case 828:
		case 829:
		case 830:
			
		case 800:
		case 801:
		case 802:
		case 803: // axes
		case 804:
		case 805:
			client.weaponDelay = 1000;
			break;
			
		default:
			//System.out.println("[WeaponDelays]: unhandled weapon speed. weapon ID: " + playerEquipment[playerWeapon]);
			client.weaponDelay = 2000;
		}
		return client.weaponDelay;	
	}
	public int getNPCMeleeDefence(int NPC) {
		int Defence = 0;	
		switch(NPC) {
		case 1:			//man
		case 2:			//man
		case 3:			//man
		case 4:			//woman
		case 5:			//woman
		case 6:			//woman
			Defence = 0;
			break;
		case 7:			//farmer
		case 425:		//SHADE
			Defence = 10;
			break;
		case 73: 		//zombie
			Defence = 15;
			break;
		case 9:			//gaurd
			Defence = 20;
			break;
		case 1593:		//WILD DOG
			Defence = 45;
			break;
		case 1030:		//warewolf
		case 1033:		//warewolf
			Defence = 50;
			break;
		case 20:		//paladin
		case 21:		//hero
			Defence = 60;
			break;
		case 2025:		//Ahrim_the_Blighted
			Defence = 65;
			break;
		case 2026:		//Dharok_the_Wretched
		case 2027:		//Guthan_the_Infested
		case 2028:		//Karil_the_Tainted
		case 2029:		//Torag_the_Corrupted
		case 2030:
		case 1343:
			Defence = 75;
			break;
		case 1125:	//dad
		case 50:	//kbd
			Defence = 99;
			break;
		case 2882://dag sup
			Defence = 120;
			break;
		default:		//unregistered npcs default defence
			Defence = 5;
		}
		return Defence;
	}
	public int getNPCMagicDefence(int NPC) {
		int Defence = 0;	
		switch(NPC) {
		case 1:			//man
		case 2:			//man
		case 3:			//man
		case 4:			//woman
		case 5:			//woman
		case 6:			//woman
			Defence = 0;
			break;
		case 7:			//farmer
		case 425:		//SHADE
			Defence = 10;
			break;
		case 73: 		//zombie
			Defence = 13;
			break;
		case 9:			//gaurd
			Defence = 15;
			break;
		case 1593:		//WILD DOG
			Defence = 30;
			break;
		case 20:		//paladin
		case 21:		//hero
		case 1030:		//warewolf
		case 1033:		//warewolf
			Defence = 40;
			break;
		case 2026:		//Dharok_the_Wretched
		case 2027:		//Guthan_the_Infeste
		case 2029:		//Torag_the_Corrupted
		case 2030:		//Verac_the_Defiled
			Defence = 59;
			break;
		case 2025:		//Ahrim_the_Blighted
			Defence = 70;
			break;
		case 1125:		//DAD
			Defence = 65;
			break;
		case 2028:		//Karil_the_Tainted
		case 50:		//KBD
			Defence = 89;
			break;
		default:		//unregistered npcs default defence
			Defence = 10;
		}
		return Defence;
	}
	public int getNPCRangedDefence(int NPC) {
		int Defence = 0;	
		switch(NPC) {
		case 1:			//man
		case 2:			//man
		case 3:			//man
		case 4:			//woman
		case 5:			//woman
		case 6:			//woman
			Defence = 0;
			break;
		case 7:			//farmer
		case 425:		//SHADE
			Defence = 10;
			break;
		case 73: 		//zombie
			Defence = 15;
			break;
		case 9:			//gaurd
			Defence = 20;
			break;
		case 1593:		//WILD DOG
			Defence = 45;
			break;
		case 1030:		//warewolf
		case 1033:		//warewolf
			Defence = 50;
			break;
		case 20:		//paladin
		case 21:		//hero
			Defence = 60;
			break;
		case 2025:		//Ahrim_the_Blighted
		case 2028:		//Karil_the_Tainted
			Defence = 65;
			break;
		case 2026:		//Dharok_the_Wretched
		case 2027:		//Guthan_the_Infested
		case 2029:		//Torag_the_Corrupted
		case 2030:		//Verac_the_Defiled
		case 1125:		//DAD
			Defence = 75;
			break;
		case 50:		//KBD
			Defence = 99;
			break;
		default:		//unregistered npcs default defence
			Defence = 5;
		}
		return Defence;
	}
	
}