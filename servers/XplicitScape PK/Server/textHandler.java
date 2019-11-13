public class textHandler {
	public void startText(int ID) {
client c = (client) server.playerHandler.players[ID];
c.sendQuest("XplicitScape Highscores", 6399);
c.sendQuest("XplicitScape", 640);
c.sendQuest("Quests", 663);
if(c.q1 == 0){
c.sendQuest("Lost City", 7332);
}
if(c.q1 > 0){
c.sendQuest("@yel@Lost City", 7332);
}
if(c.q1 == 15){
c.sendQuest("@gre@Lost City", 7332);
}
if(c.q2 == 0){
c.sendQuest("Rune Mysteries", 7333);
}
if(c.q2 > 0){
c.sendQuest("@yel@Rune Mysteries", 7333);
}
if(c.q2 == 15){
c.sendQuest("@gre@Rune Mysteries", 7333);
}
if(c.q3 == 0){
c.sendQuest("Desert Treasure", 7334);
}
if(c.q3 > 0){
c.sendQuest("@yel@Desert Treasure", 7334);
}
if(c.q3 == 15){
c.sendQuest("@gre@Desert Treasure", 7334);
}
if(c.q4 == 0){
c.sendQuest("Vampire Slayer", 7336);
}
if(c.q4 > 0){
c.sendQuest("@yel@Vampire Slayer", 7336);
}
if(c.q4 == 15){
c.sendQuest("@gre@Vampire Slayer", 7336);
}
if(c.q5 == 0){
c.sendQuest("Monkey Madness", 7383);
}
if(c.q5 > 0){
c.sendQuest("@yel@Monkey Madness", 7383);
}
if(c.q5 == 15){
c.sendQuest("@gre@Monkey Madness", 7383);
}
if(c.q6 == 0){
c.sendQuest("Sheep Shearer", 7339);
}
if(c.q6 > 0){
c.sendQuest("@yel@Sheep Shearer", 7339);
}
if(c.q6 == 15){
c.sendQuest("@gre@Sheep Shearer", 7339);
}
if(c.q7 == 0){
c.sendQuest("Legends Quest", 7338);
}
if(c.q7 > 0){
c.sendQuest("@yel@Legends Quest", 7338);
}
if(c.q7 == 15){
c.sendQuest("@gre@Legends Quest", 7338);
}
if(c.q8 == 0){
c.sendQuest("Lunar Diplomacy", 7340);
}
if(c.q8 > 0){
c.sendQuest("@yel@Lunar Diplomacy", 7340);
}
if(c.q8 == 15){
c.sendQuest("@gre@Lunar Diplomacy", 7340);
}
if(c.q9 == 0){
c.sendQuest("Knight's Legend", 7346);
}
if(c.q9 > 0){
c.sendQuest("@yel@Knight's Legend", 7346);
}
if(c.q9 == 15){
c.sendQuest("@gre@Knight's Legend", 7346);
}
if(c.q10 == 0){
c.sendQuest("Lunar Spirit", 7341);
}
if(c.q10 > 0){
c.sendQuest("@yel@Lunar Spirit", 7341);
}
if(c.q10 == 15){
c.sendQuest("@gre@Lunar Spirit", 7341);
}
if(c.q11 == 0){
c.sendQuest("The Wolf's Cure", 7342);
}
if(c.q11 > 0){
c.sendQuest("@yel@The Wolf's Cure", 7342);
}
if(c.q11 == 15){
c.sendQuest("@gre@The Wolf's Cure", 7342);
}
c.sendQuest("", 7337);
c.sendQuest("", 7343);
c.sendQuest("", 7335);
c.sendQuest("", 7344);
c.sendQuest("", 7345);
c.sendQuest("", 7347);
c.sendQuest("", 7348);
c.sendQuest("Please enter your PIN using the buttons below.", 14920);
c.sendQuest("First click the FIRST digit", 15313);
c.sendQuest("1", 14883);
c.sendQuest("2", 14884);
c.sendQuest("3", 14885);
c.sendQuest("4", 14886);
c.sendQuest("5", 14887);
c.sendQuest("6", 14888);
c.sendQuest("7", 14889);
c.sendQuest("8", 14890);
c.sendQuest("9", 14891);
c.sendQuest("0", 14892);
c.sendQuest("I don't know it.", 14921);
c.sendQuest("Exit", 14922);
c.sendQuest("", 15075);
c.sendQuest("", 15076);
c.sendQuest("", 15176);
c.sendQuest("", 15171);
c.sendQuest("", 15079);
c.sendQuest("", 15080);
if(c.bankPin != -1){
    c.sendQuest("@gre@Set", 15105);
}
if(c.bankPin == -1){
    c.sendQuest("@red@NONE", 15105);
}
c.sendFrame126("The bank of XplicitScape - Deposit Box", 7421);
c.sendQuest("The bank of XplicitScape - Bank Pin", 14923);
c.sendQuest("", 15107);
c.sendQuest("Set Pin", 15078);
c.sendQuest("Delete Pin", 15082);
c.sendQuest("", 15107);
c.sendQuest("Set Pin", 15078);
c.sendQuest("The Bank of XplicitScape",5383);
c.sendQuest("",4439);
c.sendQuest("Pest control", 10361);
c.sendQuest("Current points:", 10378);
c.sendQuest("3rd age mage", 10374);
c.sendQuest("Void Knight", 10375);
c.sendQuest("100 points", 10376);
c.sendQuest("100 points", 10377);
c.sendQuest("3rd age melee", 6557);
c.sendQuest("100 points", 6559);
c.sendQuest("3rd age range", 16159);
c.sendQuest("100 points", 16160);
c.sendQuest("Close Window", 15949);//Close Text
c.sendQuest("      XplicitScape", 15950);//Title
c.sendQuest("", 15960);//Under Title
c.sendQuest("", 15959);//Beside ^
c.sendQuest("", 15961);//Beside ^
c.sendQuest("Client [V17]: Forums, Server chat section", 15951);//Bottom banner- top line
c.sendQuest("", 15955);//top line
c.sendQuest("", 15953);//top line
c.sendQuest("", 15958);//top line
c.sendQuest("gamerzlifeforum.tk is the forums!", 15952);//Bottom banner- bottom line
c.sendQuest("         Latest update: Full magic", 15956);//bottom line
c.sendQuest("", 15954);//bottom line
c.sendQuest("", 15957);//bottom line
c.sendQuest("Ape Atoll", 7457);
c.sendQuest("Visit the land of Monkeys", 7458);
    c.sendQuest("Ape Atoll", 13089);
    c.sendQuest("Visit the land of Monkeys", 13090);
    c.sendQuest("@gre@0/0", 13093);
    c.sendQuest("@gre@0/0", 13094);

    c.sendQuest("Runecrafting", 16251);
    c.sendQuest("Orbs to points", 1259);
    c.sendQuest("Changes held orbs into points", 1260);
    c.sendQuest("Orbs to more points", 15879);
    c.sendQuest("Changes held orbs into more points", 15880);
    c.sendQuest("Home", 1300);
    c.sendQuest("Teleport home", 1301);
    c.sendQuest("Home", 13037);
    c.sendQuest("Teleport home", 13038);
    c.sendQuest("@gre@0/0", 13042);
    c.sendQuest("@gre@0/0", 13043);
    c.sendQuest("@gre@0/0", 13044);
    c.sendQuest("Pking", 1325);
    c.sendQuest("Pk here", 1326);
    c.sendQuest("Pking", 13047);
    c.sendQuest("Pk here", 13048);
    c.sendQuest("@gre@0/0", 13051);
    c.sendQuest("@gre@0/0", 13052);
    c.sendQuest("Beginning training", 1350);
    c.sendQuest("Begin your adventure here", 1351);
    c.sendQuest("Beginning training", 13055);
    c.sendQuest("Begin your adventure here", 13056);
    c.sendQuest("@gre@0/0", 13059);
    c.sendQuest("@gre@0/0", 13060);
    c.sendQuest("Catherby", 1382);
    c.sendQuest("Train non-combat skills here", 1383);
    c.sendQuest("Catherby", 13063);
    c.sendQuest("Train non-combat skills here", 13064);
    c.sendQuest("@gre@0/0", 13067);
    c.sendQuest("@gre@0/0", 13068);
    c.sendQuest("Monster tele", 1415);
    c.sendQuest("Battle different monsters", 1416);
    c.sendQuest("Monster tele", 13071);
    c.sendQuest("Battle different monsters", 13072);
    c.sendQuest("@gre@0/0", 13076);
    c.sendQuest("@gre@0/0", 13077);
    c.sendQuest("@gre@0/0", 13078);
    c.sendQuest("Minigame tele", 1454);
    c.sendQuest("Try out minigames", 1455);
    c.sendQuest("Minigame tele", 13081);
    c.sendQuest("Try out minigames", 13082);
    c.sendQuest("@gre@0/0", 13085);
    c.sendQuest("@gre@0/0", 13086);
    c.sendQuest("", 4297);
    c.sendQuest("", 4298);
    c.sendQuest("", 4299);
    c.sendQuest("", 4300);
    c.sendQuest("", 4301);
    c.sendQuest("", 4302);
    c.sendQuest("", 4303);
    c.sendQuest("", 4304);
    c.sendQuest("", 14872);
    c.sendQuest("", 664);
    c.sendQuest("", 8971);
    c.sendFrame126("", 6570);
    c.sendFrame126("", 6572);
    c.sendFrame126("", 6664);
		c.sendQuest("Using this will send a notification to all online mods",
				5967);
		c.sendQuest(
				"@yel@Then click below to indicate which of our rules is being broken.",
				5969);
		c.sendQuest("4: Bug abuse (includes noclip)", 5974);
		c.sendQuest("5: staff impersonation", 5975);
		c.sendQuest("6: Monster luring or abuse", 5976);
		c.sendQuest("8: Item Duplication", 5978);
		c.sendQuest("10: X logging", 5980);
		c.sendQuest("12: Possible duped items", 5982);
	}
} // ends textHandler