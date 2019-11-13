import java.io.*;


public class TextHandler { 

    /*
     *  (C) ??
     */
	
    public TextHandler() {// Nothing needs to be put in here
    }

    public void process(int a) {

        client p = (client) server.playerHandler.players[a];

        // ---Start of prayer list---
        p.sendQuest("@whi@ Prayer", 687);
        p.sendQuest("@whi@", 2437);
        p.sendQuest("@gre@", 2438);
        p.sendQuest("@whi@", 2439);
        p.sendQuest("@whi@Attack Style :", 2427);
        p.sendQuest("@gre@W3p0n3: ", 2425);
        p.sendQuest("@gre@", 2440);
        p.sendQuest("@whi@OWN", 2441);
        p.sendQuest("@whi@ATK", 2445);
        p.sendQuest("@whi@DEF EWW", 2442);
        p.sendQuest("@gre@PWNAGE STR", 2443);
        p.sendQuest("", 2444);
        p.sendQuest("", 2446);
        p.sendQuest("", 2447);
        p.sendQuest("", 2448);
        p.sendQuest("@whi@?!?", 1084);
        p.sendQuest("@gre@Smith", 1117);

        p.sendQuest("@whi@Yes", 12466);
        p.sendQuest("@whi@No", 12467);

        p.sendQuest("@gre@Mouse Buttons", 918);
        p.sendQuest("@whi@Dark", 919);
        p.sendQuest("@whi@Normal", 920);
        p.sendQuest("@whi@HURT UR EYES", 921);
        p.sendQuest("@whi@HURTS!", 922);

        p.sendQuest("@gre@Mouse Buttons", 923);
        p.sendQuest("@whi@One", 925);
        p.sendQuest("@whi@Two", 924);
        p.sendQuest("@cya@Chat Effects", 926);
        p.sendQuest("@whi@On", 928);
        p.sendQuest("@whi@Off", 927);

        p.sendQuest("@whi@No", 960);
        p.sendQuest("@whi@Yes", 959);
        p.sendQuest("@cya@Split Chat", 956);
        p.sendQuest("@whi@", 940);
        p.sendQuest("@whi@", 946);
        p.sendQuest("@whi@", 947);
        p.sendQuest("@whi@", 948);
        p.sendQuest("@whi@", 949);
        p.sendQuest("@whi@", 950);
        p.sendQuest(
                "@whi@ Players Online: "+PlayerHandler.getPlayerCount(), 174);

        p.sendQuest(" Change your character looks!", 3649);
        p.sendQuest("Rearrange mode:", 5390);
        p.sendQuest("Withdraw as -", 5388);
        p.sendQuest("Swap", 8133);
        p.sendQuest("Insert", 8132);
        p.sendQuest("Withdraw", 5389);
        p.sendQuest("Note", 5391);

        // Range
        p.sendQuest("", 4450);
        p.sendQuest("slow!", 1776);
        p.sendQuest("fasttieee!", 1777);
        p.sendQuest("grose.", 1778);
        p.sendQuest("Attacking Style:", 1768);

        // staff
        p.sendQuest("@cya@K03D", 340);
        p.sendQuest("@red@owned", 341);
        p.sendQuest("@gre@eat me!", 342);
        p.sendQuest("@whi@attack with", 351);
        p.sendQuest("iN0n3!", 352);
        p.sendQuest("Ch00s3", 354);
        p.sendQuest("Sp3ll", 355);
        p.sendQuest("", 343);
        p.sendQuest("", 344);
        p.sendQuest("", 345);
        p.sendQuest("", 346);
        p.sendQuest("", 347);
        p.sendQuest("", 348);

        // IDK
        p.sendQuest("@cya@Attacking Style:", 2427);
        p.sendQuest("@red@Stab that ting!!", 2439);
        p.sendQuest("@whi@Smash IT UP BABYY!!", 2438);
        p.sendQuest("@gre@Chop dat!", 2437);
        p.sendQuest("@whi@Blockit up!", 2440);
        p.sendQuest("", 2441);
        p.sendQuest("", 2442);
        p.sendQuest("", 2443);
        p.sendQuest("", 2444);
        p.sendQuest("", 2445);
        p.sendQuest("", 2446);
        p.sendQuest("", 2447);
        p.sendQuest("", 2448);

        // ----Friends & Ignores----
        p.sendQuest("@cya@Bro List", 5067);
        p.sendQuest("@bla@Haters List", 5717);
        p.sendQuest("@whi@Add FRIENDD", 5070);
        p.sendQuest("@gre@Del Trader", 5071);
        p.sendQuest("@whi@Add ma bro", 5720);
        p.sendQuest("@gre@Del TRADER", 5721);

        // ----Shop----
        p.sendQuest(
                "@whi@NOOB JUST FRICKIN BUY SOMETHING AND GET OVER IT!",
                3903);

        // ----Bonuses----
        p.sendQuest("@gre@Attack bonus", 1673);
        p.sendQuest("@gre@Defence bonus", 1674); 
        p.sendQuest("@gre@Other bonuses", 1685);

        // Hands
        p.sendQuest("@gre@Choose Attack Style", 5858);
        p.sendQuest("@whi@PUNCH face!", 5866); 
        p.sendQuest("@gre@kick balls", 5867);
        p.sendQuest("@whi@block it", 5868);
        p.sendQuest("", 5869);
        p.sendQuest("", 5870);
        p.sendQuest("", 5871);
        p.sendQuest("", 5872);
        p.sendQuest("", 5873);
        p.sendQuest("", 5874);
        p.sendQuest("Weapon:", 5856);
        p.sendQuest("Spec", 7761);

        // barrows?
        p.sendQuest("@gre@Choose Attack Style", 5858);
        p.sendQuest("@whi@Punch face", 5866); 
        p.sendQuest("@gre@Kick balls", 5867);
        p.sendQuest("@whi@Block", 5868);
        p.sendQuest("", 5869);
        p.sendQuest("", 5870);
        p.sendQuest("", 5871);
        p.sendQuest("", 5872);
        p.sendQuest("", 5873);
        p.sendQuest("", 5874);
        p.sendQuest("Weapon:", 5856);
        p.sendQuest("OWNAGE K0", 7761);

        // whip ****
        p.sendQuest("@gre@Choose Attack Style", 12294);
        p.sendQuest("@whi@ATTACK", 12302); 
        p.sendQuest("@gre@STR", 12303);
        p.sendQuest("@whi@DEF", 12304);
        p.sendQuest("", 12305);
        p.sendQuest("", 12306);
        p.sendQuest("", 12307);
        p.sendQuest("", 12308);
        p.sendQuest("", 12309);
        p.sendQuest("", 12310);
        p.sendQuest("Weapon:", 12292);

        p.sendFrame126("", 180);
        p.sendFrame126("@whi@Train", 181);
        p.sendFrame126("@blu@VENG", 72);
        p.sendFrame126("@cya@Shops", 175);
        p.sendFrame126("@pur@UdrwtrPK", 177);
        p.sendFrame126("@whi@PkBox", 185);
        p.sendFrame126("@gre@Barrowz", 186);
        p.sendFrame126("@cya@Slayer", 173);
        p.sendFrame126("@red@   Jad", 179);
        p.sendFrame126("@bla@home2", 187);
        p.sendFrame126("@blu@SKILLZZ", 176);
        p.sendFrame126("@cya@::kbd", 189);
        p.sendFrame126("@gre@rpk", 13372);
        p.sendFrame126("@bla@epk", 13373);
        p.sendFrame126("@whi@icepk", 13374);
        p.sendFrame126("@red@mb", 13376);
        p.sendFrame126("", 13378);
        p.sendFrame126("", 13380);
        p.sendFrame126("", 13381);
        p.sendFrame126("", 13382);
        p.sendFrame126("", 11102);
        p.sendFrame126("", 13379);
        p.sendFrame126("", 13377);
        p.sendFrame126("", 13375);
        p.sendFrame126("", 11103);
        p.sendFrame126("@whi@SLOW", 160);
        p.sendFrame126("@cya@FAST", 159);
        p.sendFrame126("@whi@Energy Left:", 148);
        p.sendFrame126("@whi@Move Speed", 158);
        p.sendFrame126("@whi@Dragon teleports", 155);
        p.sendFrame126("@bla@newpk", 182);
        p.sendFrame126("@whi@Closewindow", 3902);
        p.sendFrame126("@gre@ 1", 157);
        p.sendFrame126("@cya@2 @red@PK", 156);

        // --Ancients--
        p.sendQuest("@red@Level 50 : Smoke Rush", 12941);
        p.sendQuest("@whi@A single target smoke attack", 12942);
        p.sendQuest("@red@Level 52 : Shadow Rush", 12989);
        p.sendQuest("@cya@A single target shadow attack", 12990);
        p.sendQuest("@red@Fight Pits", 13037);
        p.sendQuest("@cya@teleport to a 1v1 Fun Fight", 13038);
        p.sendQuest("@red@Level 56 : Blood Rush", 12903);
        p.sendQuest("@cya@A single target blood attack", 12904);
        p.sendQuest("@red@Level 58 : Ice Rush", 12863);
        p.sendQuest("@cya@A single target ice attack", 12864);
        p.sendQuest("@red@PK Bank", 13047);
        p.sendQuest("@cya@teleport to MB", 13048);
        p.sendQuest("@red@Level 62 : Smoke Burst", 12965);
        p.sendQuest("@cya@A multi-target smoke attack", 12966);
        p.sendQuest("@red@Level 64 : Shadow Burst", 13013);
        p.sendQuest("@cya@A multi-target shadow attack", 13014);
        p.sendQuest("@red@PK Shack", 13055);
        p.sendQuest("@cya@teleport to the shack at PK", 13056);
        p.sendQuest("@red@Level 68 : Blood Burst", 12921);
        p.sendQuest("@cya@A multi-target blood attack", 12922);
        p.sendQuest("@red@Level 70 : Ice Burst", 12883);
        p.sendQuest("@cya@A multi-target ice attack", 12884);
        p.sendQuest("@red@Castle Wars", 13063);
        p.sendQuest("@cya@teleport to Castle Wars Lobby", 13064);
        p.sendQuest("@red@Level 74 : Smoke Blitz", 12953);
        p.sendQuest("@cya@A single target strong smoke attack", 12954);
        p.sendQuest("@red@Level 76 : Shadow Blitz", 13001);
        p.sendQuest("@cya@A single target strong shadow attack", 13002);
        p.sendQuest("@red@Guilds", 13071);
        p.sendQuest("@cya@teleport to the Guilds", 13072);
        p.sendQuest("@red@Level 80 : Blood Blitz", 12913);
        p.sendQuest("@cya@A single target strong blood attack", 12914);
        p.sendQuest("@red@Level 82 : Ice Blitz", 12873);
        p.sendQuest("@cya@A single target strong ice attack", 12874);
        p.sendQuest("@red@Third Age", 13081);
        p.sendQuest("@cya@Teleport to 3rdage", 13082);
        p.sendQuest("@red@Level 86 : Smoke Barrage", 12977);
        p.sendQuest("@cya@A multi-target strong smoke attack", 12978);
        p.sendQuest("@red@Level 88 : Shadow Barrage", 13025);
        p.sendQuest("@cya@A multi-target strong shadow attack", 13026);
        p.sendQuest("@red@God Sword Mini-Game", 13089);
        p.sendQuest("@cya@Teleport to GS minigame", 13090);
        p.sendQuest("@red@Level 92 : Blood Barrage", 12931);
        p.sendQuest("@cya@A multi-target strong blood attack", 12932);
        p.sendQuest("@cya@Level 94 : Ice Barrage", 12893);
        p.sendQuest("@red@A multi-target strong ice attack", 12894);
        p.sendQuest("Church", 13097);
        p.sendQuest("@cya@Teleport to the Varrok Church", 13098);

        // --Modern--
        p.sendQuest("@red@Level 1 : Wind Strike", 1200);
        p.sendQuest("@cya@A basic Air missile", 1201);
        p.sendQuest("@red@Level 3 : Confuse", 1207);
        p.sendQuest("@cya@Reduces your target's attack by 5%", 1208);
        p.sendQuest("@red@Level 5 : Water Strike", 1216);
        p.sendQuest("@cya@A basic Water missile", 1217);
        p.sendQuest("@red@Level 7 : Lvl-1 Enchant", 1225);
        p.sendQuest("@whi@For use on sapphire jewellery", 1226);
        p.sendQuest("@red@Level 9 : Earth Strike", 1232);
        p.sendQuest("@whi@A basic Earth missile", 1233);
        p.sendQuest("@red@Level 11 : Weaken", 1241);
        p.sendQuest("@whi@Reduces your target's str by 5%", 1242);
        p.sendQuest("@red@Level 13 : Fire Strike", 1250);
        p.sendQuest("@whi@A basic Fire missile", 1251);
        p.sendQuest("@red@Level 15 : Bones to Bananas", 1259);
        p.sendQuest("@whi@Changes held bones to bananas", 1260);
        p.sendQuest("@red@Level 17 : Wind Bolt", 1268);
        p.sendQuest("@whi@A low level Air missile", 1269);
        p.sendQuest("@red@Level 19 : Curse", 1275);
        p.sendQuest("@whi@Reduces your target's def by 5%", 1276);
        p.sendQuest("@red@Level 20 : Bind", 1574);
        p.sendQuest("@whi@Holds you target for 5 seconds", 1575);
        p.sendQuest("@red@Level 21 : Low Level Alchemy", 1284);
        p.sendQuest("@whi@Converts an item into gold", 1285);
        p.sendQuest("@red@Level 23 : Water Bolt", 1291);
        p.sendQuest("@whi@A low level Water missile", 1292);
        p.sendQuest("@red@Teleporting Spell", 1300);
        p.sendQuest("@whi@", 1301);
        p.sendQuest("@red@Level 27 : Lvl-2 Enchant", 1309);
        p.sendQuest("@whi@For use on emerald jewellery", 1310);
        p.sendQuest("@red@Level 29 : Earth Bolt", 1316);
        p.sendQuest("@whi@A low level Earth missile", 1317);
        p.sendQuest("@red@Teleporting Spell", 1325);
        p.sendQuest("@whi@", 1326);
        p.sendQuest("@red@Level 33 : Telekinetic Grab", 1334);
        p.sendQuest("@whi@Take an item you can't reach", 1336);
        p.sendQuest("@red@Level 35 : Fire Bolt", 1341);
        p.sendQuest("@whi@A low level Fire missile", 1342);
        p.sendQuest("@red@Teleporting Spell", 1350);
        p.sendQuest("@whi@", 1351);
        p.sendQuest("@red@Level 39 : Crumble Undead", 1359);
        p.sendQuest("@whi@Hits un-dead monsters hard", 1360);
        p.sendQuest("@red@Level 41 : Wind Blast", 1368);
        p.sendQuest("@whi@A medium level Wind missile", 1369);
        p.sendQuest("@red@Level 43 : Superheat Item", 1375);
        p.sendQuest("@whi@Smelt ore without a furnace", 1376);
        p.sendQuest("@red@Teleporting Spell", 1382);
        p.sendQuest("@whi@", 1383);
        p.sendQuest("@red@Level 47 : Water Blast", 1389);
        p.sendQuest("@whi@A medium level Water missile", 1390);
        p.sendQuest("@red@Level 49 : Lvl-3 Enchant", 1398);
        p.sendQuest("@whi@For use on ruby jewellery", 1399);
        p.sendQuest("@red@Level 50 : Iban Blast", 1405);
        p.sendQuest("@whi@Summons the wrath of Iban", 1406); // Iban blast
        p.sendQuest("@red@Level 50 : Snare", 1584);
        p.sendQuest("@whi@Holds your target for 10 seconds", 1585);
        p.sendQuest("@red@Level 50 : Magic Dart", 12039); // Magic dart
        p.sendQuest("@whi@A magic dart of slaying", 12040);
        p.sendQuest("@red@Teleporting Spell", 1415);
        p.sendQuest("@whi@", 1416);
        p.sendQuest("@red@Level 53 : Earth Blast", 1422);
        p.sendQuest("@whi@A medium level Earth missile", 1423);
        p.sendQuest("@red@Level 55 : High Level Alchemy", 1431);
        p.sendQuest("@whi@Converts an item into more gold", 1432);
        p.sendQuest("@red@Level 56 : Charge Water Orb", 1438);
        p.sendQuest("@whi@Cast on a Water obelisk", 1439);
        p.sendQuest("@red@Level 57 : Lvl-4 Enchant", 1447);
        p.sendQuest("@whi@For use on diamond jewellery", 1448);
        p.sendQuest("@red@Teleporting Spell", 1454);
        p.sendQuest("@whi@", 1455);
        p.sendQuest("@red@Level 59 : Fire Blast", 1461);
        p.sendQuest("@whi@A medium level Fire missile", 1462);
        p.sendQuest("@red@Level 60 : Charge Earth Orb", 1470);
        p.sendQuest("@whi@Cast on a Earth obelisk", 1471);
        p.sendQuest("@red@Level 60 : Ores to Bars", 15879);
        p.sendQuest("@whi@Turns Ores Into Bars", 15880);
        p.sendQuest("@red@Level 99 : Saradomin Strike", 1603); // Saradomin Strike
        p.sendQuest("@whi@The power of Saradomin", 1604);
        p.sendQuest("@red@Level 99 : Claws of Guthix", 1614); // Claws of Guthix
        p.sendQuest("@whi@The power of Guthix", 1615);
        p.sendQuest("@red@Level 99 : Flames of Zamorak", 1625); // Flames of Zamorak
        p.sendQuest("@whi@The power of Zamorak", 1626);
        p.sendQuest("@red@Teleporting Spell", 7457);
        p.sendQuest("@whi@", 7458);
        p.sendQuest("@red@Level 62 : Wind Wave", 1479);
        p.sendQuest("@whi@A high level Air missile", 1480);
        p.sendQuest("@red@Level 63 : Charge Fire Orb", 1486);
        p.sendQuest("@whi@Cast on a Fire obelisk", 1487);
        p.sendQuest("@red@Teleporting Spell", 18472);
        p.sendQuest("@whi@", 18473);
        p.sendQuest("@red@Level 65 : Water Wave", 1495);
        p.sendQuest("@whi@A high level Water missile", 1496);
        p.sendQuest("@whi@Level 66 : Charge Air Orb", 1504);
        p.sendQuest("@whi@Cast on a Air obelisk", 1505);
        p.sendQuest("@red@Level 66 : Vulnerability", 1513);
        p.sendQuest("@whi@Reduces your target's def by 10%", 1514);
        p.sendQuest("@red@Level 68 : Lvl-5 Enchant", 1522);
        p.sendQuest("@whi@For use on dragonstone jewellery", 1523);
        p.sendQuest("@red@Level 70 : Earth Wave", 1531);
        p.sendQuest("@whi@A high level Earth missile", 1532);
        p.sendQuest("@red@Level 73 : Enfeeble", 1545);
        p.sendQuest("@whi@Reduces your target's str by 10%", 1546);
        p.sendQuest("@red@Teleporting Spell", 12427); // Teleother Lumbridge
        p.sendQuest("@whi@", 12428);
        p.sendQuest("@red@Level 75 : Fire Wave", 1554);
        p.sendQuest("@whi@A high level Fire missile", 1555);
        p.sendQuest("@red@Level 79 : Entangle", 1594);
        p.sendQuest("@whi@Holds your target for 15 seconds", 1595);
        p.sendQuest("@red@Level 80 : Stun", 1564);
        p.sendQuest("@whi@Reduces your target's att by 10%", 1565);
        p.sendQuest("@red@Level 80 : Charge", 1636);
        p.sendQuest("@whi@Charges God spells", 1637);
        p.sendQuest("@red@Teleporting Spell", 12437); // Teleother Falador
        p.sendQuest("@whi@", 12438);
        p.sendQuest("@red@Teleporting Spell", 12447);
        p.sendQuest("@whi@", 12448);
        p.sendQuest("@red@Level 87 : Lvl-6 Enchant", 6005);
        p.sendQuest("@whi@For use on onyx jewellery", 6006);
        p.sendQuest("@red@Teleporting Spell", 12457); // Teleother Camelot
        p.sendQuest("@whi@", 12458);
        p.sendQuest("@red@Attack XP:", 4042);
        p.sendQuest("@red@Next Lvl At:", 4043);
        p.sendQuest("@red@Strength XP:", 4048);
        p.sendQuest("@red@Next Lvl At:", 4049);
        p.sendQuest("@red@Defence XP:", 4054);
        p.sendQuest("@red@Next Lvl At:", 4055);
        p.sendQuest("@red@Ranged XP:", 4060);
        p.sendQuest("@red@Next Lvl At:", 4061);
        p.sendQuest("@red@Prayer XP:", 4066);
        p.sendQuest("@red@Next Lvl At:", 4067);
        p.sendQuest("@red@Magic XP:", 4072);
        p.sendQuest("@red@Next Lvl At:", 4073);
        p.sendQuest("@red@Runecraft XP:", 4155);
        p.sendQuest("@red@Next Lvl At:", 4156);
        p.sendQuest("@red@Hitpoints XP:", 4078);
        p.sendQuest("@red@Next Lvl At:", 4079);
        p.sendQuest("@red@Agility XP:", 4084);
        p.sendQuest("@red@Next Lvl At:", 4085);
        p.sendQuest("@red@Herblore XP:", 4090);
        p.sendQuest("@red@Next Lvl At:", 4091);
        p.sendQuest("@red@Theiving XP:", 4096);
        p.sendQuest("@red@Next Lvl At:", 4097);
        p.sendQuest("@red@Crafting XP:", 4102);
        p.sendQuest("@red@Next Lvl At:", 4103);
        p.sendQuest("@red@Fletching XP:", 4108);
        p.sendQuest("@gre@Next Lvl At:", 4109);
        p.sendQuest("@gre@Slayer XP:", 12169);
        p.sendQuest("@gre@Next Lvl At:", 12170);
        p.sendQuest("@gre@Mining XP:", 4114);
        p.sendQuest("@gre@Next Lvl At:", 4115);
        p.sendQuest("@gre@Smihing XP:", 4120);
        p.sendQuest("@gre@Next Lvl At:", 4121);
        p.sendQuest("@gre@Fishing XP:", 4126);
        p.sendQuest("@gre@Next Lvl At:", 4127);
        p.sendQuest("@gre@Cooking XP:", 4132);
        p.sendQuest("@gre@Next Lvl At:", 4133);
        p.sendQuest("@gre@Firemaking XP:", 4138);
        p.sendQuest("@gre@Next Lvl At:", 4139);
        p.sendQuest("@gre@Woodcutting XP:", 4144);
        p.sendQuest("@gre@Next Lvl At:", 4145);
        p.sendQuest("@gre@Farming XP:", 13919);
        p.sendQuest("@gre@Next Lvl At:", 13920);

    }
}
