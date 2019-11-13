package server;

import server.players.*;
//import server.players.Quests.*;

public class textHandler {
public textHandler() {}

	public void startText(client c) {
c.QH().questcolorloginchanger(c);

	/*Teleports Names*/
	c.CAM().sendQuest("@yel@ Level 25 : Vanquish", 1300);
	c.CAM().sendQuest("Teleports you to Home", 1301);
	c.CAM().sendQuest("@gre@0/0", 1305);
	c.CAM().sendQuest("@gre@0/0", 1306);
	c.CAM().sendQuest("@gre@0/0", 1307);
	c.CAM().sendQuest("@yel@ Level 31 : La'more", 1325);
	c.CAM().sendQuest("Teleports you to Market Place", 1326);
	c.CAM().sendQuest("@gre@0/0", 1330);
	c.CAM().sendQuest("@gre@0/0", 1331);
	c.CAM().sendQuest("@gre@0/0", 1332);
	c.CAM().sendQuest("@yel@ Level 37 : FlatLands", 1350);
	c.CAM().sendQuest("Teleports you to Training Island", 1351);
	c.CAM().sendQuest("@gre@0/0", 1355);
	c.CAM().sendQuest("@gre@0/0", 1356);
	c.CAM().sendQuest("@gre@0/0", 1357);
	c.CAM().sendQuest("@yel@ Level 45 : Cordoba", 1382);
	c.CAM().sendQuest("Teleports you to Skiller Villages", 1383);
	c.CAM().sendQuest("@gre@0/0", 1386);
	c.CAM().sendQuest("@gre@0/0", 1387);
	c.CAM().sendQuest("@yel@ Level 51 : Avignon", 1415);
	c.CAM().sendQuest("Teleports you to Thieving City", 1416);
	c.CAM().sendQuest("@gre@0/0", 1419);
	c.CAM().sendQuest("@gre@0/0", 1420);
	c.CAM().sendQuest("@yel@Slayer tower", 1454);
	c.CAM().sendQuest("Teleports you to the slayer tower", 1455);
	c.CAM().sendQuest("@gre@0/0", 1458);
	c.CAM().sendQuest("@gre@0/0", 1459);
	c.CAM().sendQuest("Pking", 7457);
	c.CAM().sendQuest("Teleports you to Edgeville", 7458);
	c.CAM().sendQuest("Level 64 : No Teleport Uses", 18472);
	c.CAM().sendQuest("Teleports you No Where", 18473);
	c.CAM().sendQuest("@gre@0/0", 18476);
	c.CAM().sendQuest("@gre@0/0", 18477);
	c.CAM().sendQuest("@gre@0/0", 18479);
	c.CAM().sendQuest("@gre@0/0", 18481);



	c.CAM().sendQuest("@yel@ level 51 : Burn0ut Blast", 1405);
	c.CAM().sendQuest("Summons the wrath of burn0ut", 1406);

	/*BASICS*/
		/*if (Constants.TELEPORT_LEVEL_REQ == false && Constants.TELEPORT_REQ_RUNES == false) {
		c.CAM().sendQuest("@gre@0/0", 13042);
		c.CAM().sendQuest("@gre@0/0", 13043);
		c.CAM().sendQuest("@gre@0/0", 13044);
		c.CAM().sendQuest("@gre@0/0", 13051);
		c.CAM().sendQuest("@gre@0/0", 13052);
		c.CAM().sendQuest("@gre@0/0", 13059);
		c.CAM().sendQuest("@gre@0/0", 13060);
		c.CAM().sendQuest("@gre@0/0", 13067);
 		c.CAM().sendQuest("@gre@0/0", 13068);
		c.CAM().sendQuest("@gre@0/0", 13076);
		c.CAM().sendQuest("@gre@0/0", 13077);
		c.CAM().sendQuest("@gre@0/0", 13078);
		c.CAM().sendQuest("@gre@0/0", 13085);
		c.CAM().sendQuest("@gre@0/0", 13086);
		}*/

		/* OTHER SENDQUESTS */
		c.CAM().sendQuest("The Bank of "+Constants.SERVER_NAME,5383);
		c.CAM().sendFrame126("The Bank of "+Constants.SERVER_NAME+" - Deposit Box", 7421);
		c.CAM().sendQuest("The Bank of "+Constants.SERVER_NAME+" - Bank Pin", 14923);
		c.CAM().sendQuest("Using this will send a notification to all online mods", 5967);
		c.CAM().sendQuest( "@yel@Then click below to indicate which of our rules is being broken.", 5969);
		c.CAM().sendQuest("4: Bug abuse (includes noclip)", 5974);
		c.CAM().sendQuest("5: "+Constants.SERVER_NAME+"staff impersonation", 5975);
		c.CAM().sendQuest("6: Monster luring or abuse", 5976);
		c.CAM().sendQuest("8: Item Duplication", 5978);
		c.CAM().sendQuest("10: Misuse of yell channel", 5980);
		c.CAM().sendQuest("12: Possible duped items", 5982);
		c.CAM().sendFrame126("", 6570);
		c.CAM().sendFrame126("", 6572);
		c.CAM().sendFrame126("", 6664);
		c.CAM().sendFrame126("@yel@Glass",6533); 
		c.CAM().sendFrame126("@yel@Box",6532); 
		c.CAM().sendFrame126("@yel@Climb",6540); 
		c.CAM().sendFrame126("@yel@Rope",6541); 
		c.CAM().sendFrame126("@yel@Lean",11101);
		c.CAM().sendFrame126("@yel@Glass",6519); 
		c.CAM().sendFrame126("@yel@Wall",6522); 
		c.CAM().sendFrame126("@yel@Goblin",13386); 
		c.CAM().sendFrame126("@yel@Bow",13388); 
		c.CAM().sendFrame126("@yel@Goblin",13387); 
		c.CAM().sendFrame126("@yel@Dance",15170); 
		c.CAM().sendFrame126("@yel@Scared",15175); 
		c.CAM().sendFrame126("@yel@Zombie",18466); 
		c.CAM().sendFrame126("@yel@Walk",18467); 
		c.CAM().sendFrame126("@yel@Zombie",18468); 
		c.CAM().sendFrame126("@yel@Dance",18469); 
		c.CAM().sendFrame126("@yel@Zombie",18467); 
		c.CAM().sendFrame126("@yel@Walk",18468); 
		c.CAM().sendFrame126("@yel@Zombie",18469); 
		c.CAM().sendFrame126("@yel@Dance",18687); 
		c.CAM().sendFrame126("@yel@Rabbit",18688); 
		c.CAM().sendFrame126("@yel@Hop",18689); 
		c.CAM().sendQuest("", 963);//top music header
		c.CAM().sendQuest("", 8934);//under above
		c.CAM().sendQuest("", 6272);//box 1 near above
		c.CAM().sendQuest("", 6271);//box 2 near above
		c.CAM().sendQuest("", 9926);//box 3 near above
		c.CAM().sendQuest("", 4439);//header
		c.CAM().sendQuest("@yel@Head Admins (Level 3)", 3206);
		c.CAM().sendQuest("@gre@- Killamess", 5449);
		c.CAM().sendQuest("@red@- ", 11941);
		c.CAM().sendQuest("@red@- ", 4287);
		c.CAM().sendQuest("@red@- ", 4288);
		c.CAM().sendQuest("-----------------------", 4289); 
		c.CAM().sendQuest("@or1@Admins (Level 2)", 4290); 
		c.CAM().sendQuest("@red@- ", 11134); 
		c.CAM().sendQuest("-----------------------", 4291); 
		c.CAM().sendQuest("@whi@Moderators (Level 1)", 4292); 
		c.CAM().sendQuest("@red@- ", 4293); 
		c.CAM().sendQuest("", 4294); 
		c.CAM().sendQuest("", 4295); 
		c.CAM().sendQuest("", 4296);
		c.CAM().sendQuest("", 8935); 
		c.CAM().sendQuest("", 4297); 
		c.CAM().sendQuest("", 4298); 
		c.CAM().sendQuest("", 4299); 
		c.CAM().sendQuest("", 4300); 
		c.CAM().sendQuest("", 4301); 
		c.CAM().sendQuest("", 4302); 
		c.CAM().sendQuest("", 4303); 
		c.CAM().sendQuest("", 4304);
		c.CAM().sendQuest("", 14872); 
		c.CAM().sendQuest("", 664);
		c.CAM().sendQuest("", 8971); 
		c.CAM().sendQuest("", 4306); 
		c.CAM().sendQuest("", 7454);
		c.CAM().sendQuest("", 4864);
		c.CAM().sendQuest("", 12128);
		c.CAM().sendQuest("", 4307);
		c.CAM().sendQuest("", 4308);
		c.CAM().sendQuest("", 4309);
		c.CAM().sendQuest("", 14871);
		c.CAM().sendQuest("", 12127);
		c.CAM().sendQuest("", 8968);
		c.CAM().sendQuest("", 12844);
		c.CAM().sendQuest("", 4310);
		c.CAM().sendQuest("", 10111);
		c.CAM().sendQuest("", 8565);
		c.CAM().sendQuest("", 14242);
		c.CAM().sendQuest("", 15494);
		c.CAM().sendQuest("", 17508);
		c.CAM().sendQuest("", 4311);
		c.CAM().sendQuest("", 11476);

	}
}