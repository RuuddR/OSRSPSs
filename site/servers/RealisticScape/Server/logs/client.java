/*
CHEEZSCAPE 

###############################################################
###############################################################

Last Update (61): 10.4.2006

[===================================================================]

Update 62 info: Instead of blood runes there is ecto tokens, this stops server from crashing
Update 61 info: Added teleblock!
Update 60 info: Added GLOBAL GRAPHICS!!!!
Update 59 info: Added Chaos Elemental which can remove your item when your attacks (random though)
Update 58 info: Added weather, changes from morning to afternoon, from afternoon to evening etc. depending on the time, also randomly changes weather every 20 mins from either snow, sandstorm or normal.
Update 57 info: Friends/Ignore list fully working, saves as well.
Update 56 info: Cook's Assistant Quest fully working!
Update 55 info: Bones To Bananas Spell
Update 54 info: Invisible Armour Quest fully working!
Update 53 info: Added working clues
Update 52 info: Added Dr Jad
Update 51 info: Added 362 items and map support
Update 50 info: Added spells requiring runes
Update 49 info: Converted from mythscape base to whitescape base
Update 48 info: Added ancient magicks with working spells on players
Update 47 info: Added server status
Update 46 info: Added new dungeon with kbd
Update 45 info: Added global items
Update 44 info: Added timed attacking and new weapons
Update 43 info: Added logout timers
Update 42 info: Added herblore stall
Update 41 info: Added fletching skill
Update 40 info: Added smithing and herblore
Update 39 info: Added ranged pking
Update 38 info: Added abyssal demons with drops
Update 37 info: Added orb of light
Update 36 info: Added openable doors
Update 35 info: Added enchanting and high alching
Update 34 info: Added proper trading and energy
Update 33 info: Added more doors, bank chests and disabled wildy teleporting
Update 32 info: Added prayer training area
Update 31 info: Added weapon timers
Update 30 info: Added proper pking so items is dropped and dupe bug is now fixed
Update 29 info: Added equipment requirements
Update 28 info: Added god capes (changed area) and working mage bank lever
Update 27 info: Fixed hp bug
Update 26 info: Added strength bonuses
Update 25 info: Added losing items when dying (don't drop on floor though)
Update 24 info: Added abyss
Update 23 info: Fixed crashing client bug on ancient gate, and took out scythes
Update 22 info: Added very basic trading (use item on player to give item)
Update 21 info: Added god capes
Update 20 info: Added new death place and updatestats feature
Update 19 info: Made training faster
Update 18 info: Added 3 new slayer objects
Update 17 info: Added restore command to fix broken stats
Update 16 info: Changed max skill level from 99 to 135
Update 15 info: Added more items
Update 14 info: Added legends cape
Update 13 info: Added runecraft skill
Update 12 info: Fixed dupe bug
Update 11 info: Party hat spawning bug fixed and removed from game
Update 10 info: Added working clues
Update 9 info: Added stat viewer
Update 8 info: Added 3 more menus
Update 7 info: Fixed level requirement bug
Update 6 info: Fixed keep on dying bug
Update 5 info: Hp back to full after dying
Update 4 info: Added god staves
Update 3 info: Added new theiving area
Update 2 info: Added first ever quest
Update 1 info: Fixed minor message bug and added silver stalls

[===================================================================]

###############################################################
###############################################################

Index (use find to find these for editing things):
CLUE_00 - clue menus
CLUE_01 - clues
CLUE_02 - check clues
MAGE_00 - magic on players

###############################################################
###############################################################
*/

import java.io.*;
import java.util.StringTokenizer;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class client extends Player implements Runnable {

	public void println_debug(String str) {
		System.out.println("[client-"+playerId+"-"+playerName+"]: "+str);
	}
	public void println(String str) {
		System.out.println("[client-"+playerId+"-"+playerName+"]: "+str);
	}
	
	
        public void updateCharAppearance(int[] styles, int[] colors) {
		for(int j = 0; j < 7; j++) {
			if(styles[j] > 0) {
				styles[j] += 0x100;
				pCHead  = styles[0];
				pCBeard = styles[1];
				pCTorso = styles[2];
				pCArms  = styles[3];
				pCHands = styles[4];
				pCLegs  = styles[5];
				pCFeet  = styles[6];
			}
		}
		for(int i = 0; i < 5; i++) {
			pColor = colors[i];
		}
	}

	public int distanceTo(Player other) {
        return (int) Math.sqrt(Math.pow(absX - other.absX, 2) + Math.pow(absY - other.absY, 2));
    }
	public int distanceToPoint(int pointX,int pointY) {
        return (int) Math.sqrt(Math.pow(absX - pointX, 2) + Math.pow(absY - pointY, 2));
    }

	public int getItemSlot(int itemID)
	{
		for (int slot=0; slot < playerItems.length; slot++)
		{
			if (playerItems[slot] == (itemID +1))
			{
				return slot;
			}
		}
		return -1;
	}
public int WildyLevel = 0;
public boolean InWildrange = false;
public void WriteWildyLevel()
{
checkwildy();
int oldlevel = WildyLevel;
WildyLevel = (((absY - 3520) / 8) + 1);
if(inwildy && WildyLevel != oldlevel)
{
outStream.createFrame(208);
outStream.writeWordBigEndian_dup(197);
sendQuest("Level: "+WildyLevel, 199);
}
}
public void CheckWildrange(int pcombat)
{
if((combat + WildyLevel >= pcombat) || (combat - WildyLevel <= pcombat))
{
InWildrange = true;
}
else
{
InWildrange = false;
}
}
/*TESTING FRAMES*/

// anInt1008 frames:
public void frame8(int i1, int i2) // tested
{
outStream.createFrame(8);
outStream.writeWordBigEndianA(i1); // probs interface
outStream.writeWord(i2);
sendMessage("Frame 8 tested");
}
public void frame64(int i1, int i2) // tested, found nothing, apparently something to do with dropped items
{
outStream.createFrame(64);
outStream.writeByteS(i1);
outStream.writeByteA(i2);
sendMessage("Frame 64 tested");
}
public void frame72(int i1) // logs you out :S
{
outStream.createFrame(72);
outStream.writeWordBigEndian(i1);
sendMessage("Frame 72 tested");
}
public void frame74(int i1) 
{
outStream.createFrame(74);
outStream.writeWordBigEndian(i1);
sendMessage("Frame 74 tested");
}
public void frame121(int i1, int i2) 
{
outStream.createFrame(121);
outStream.writeWord(i1);
outStream.writeByteS(i2);
sendMessage("Frame 121 tested");
}
public void frame122(int i1, int i2) // tested
{
outStream.createFrame(122);
outStream.writeWordBigEndianA(i1);// probs interface
outStream.writeWordBigEndianA(i2);
sendMessage("Frame 122 tested");
}
public void frame166(int i1, int i2, int i3, int i4, int i5) // CAMERA STUFF!!!!! 0 on all makes it lock on that place, make last over 100 to make it go black!! - xerozcheez
{
outStream.createFrame(166); 
outStream.writeByte(i1); // X coord where camera will end within the region
outStream.writeByte(i2); // Y coord where camera will end within the region
outStream.writeWord(i3); // the camera height where it will end
outStream.writeByte(i4); // the camera moving speed
outStream.writeByte(i5); // if this goes above 100 it does something? :O
sendMessage("Testing Camera Angle");
}


public void frame177(int i1, int i2, int i3, int i4, int i5)  // similar to 166, a good combo: f177 041 033 014 011 005 - xerozcheez
{
outStream.createFrame(177);
outStream.writeByte(i1); // X coord within the region middle of your screen will view to
outStream.writeByte(i2); // Y coord within the region middle of your screen will view to
outStream.writeWord(i3); // the height it will be viewing to
outStream.writeByte(i4); // the camera speed? movement? dunno yet
outStream.writeByte(i5); // if this goes above 100 it does something? :O
sendMessage("Frame 177 tested");
}


public void frame70(int i1, int i2, int i3) // interface thing, not sure
{
outStream.createFrame(70);
outStream.writeWord(i1);
outStream.writeWordBigEndian(i2);
outStream.writeWordBigEndian(i3); // interface, definatly.
sendMessage("Frame 70 tested");
updateRequired = true;
appearanceUpdateRequired = true;
}
public void framevar70(int i1, int i2, int i3) // no idea
{
outStream.createFrameVarSize(70);
outStream.writeWord(i1);
outStream.writeWordBigEndian(i2);
outStream.writeWordBigEndian(i3);
sendMessage("Framevar 70 tested");
}

public void frame240(int i1) // doesn't logout so it's valid, but doesn't do anything hmm?
{
outStream.createFrame(240);
outStream.writeWord(i1);
sendMessage("Frame 240 tested");
updateRequired = true;
appearanceUpdateRequired = true;
}



public void frame110(int i1) // doesn't logout so it's valid, but doesn't do anything hmm? Also the sidebar select stuff is used
{
outStream.createFrame(110);
outStream.writeByte(i1);
sendMessage("Frame 110 tested");
updateRequired = true;
appearanceUpdateRequired = true;
}
public void frame106(int i1) // changes selected sidebar!
{
outStream.createFrame(106);
outStream.writeByteC(i1);
sendMessage("Frame 106 tested");
updateRequired = true;
appearanceUpdateRequired = true;
}
public void frame24(int i1) // flashes icons!!, try ::f24 0, also negative nos work
{
outStream.createFrame(24);
outStream.writeByteA(i1);
sendMessage("Frame 24 tested");
updateRequired = true;
appearanceUpdateRequired = true;
}
public void frame142(int i1)
{
outStream.createFrame(142);
outStream.writeWordBigEndian(i1);
sendMessage("Frame 142 tested");
updateRequired = true;
appearanceUpdateRequired = true;
}
public void frame142d(int i1)
{
outStream.createFrame(142);
outStream.writeWordBigEndian_dup(i1);
sendMessage("Frame 142d tested");
updateRequired = true;
appearanceUpdateRequired = true;
}
public void frame254(int i1, int i2, int i3, int i4, int i5)
{
outStream.createFrame(254); 
outStream.writeByte(i1);
if(i1 == 1)
{
outStream.writeWord(i2); 
}
if(i1 >= 2 && i1 <= 6)
{
outStream.writeWord(i3); 
outStream.writeWord(i4); 
outStream.writeByte(i5); 
}
if(i1 == 10)
{
outStream.writeWord(i2);
}
sendMessage("Frame 254 tested");
updateRequired = true;
appearanceUpdateRequired = true;
}

public void frame254skull(int i1, int i2)
{
outStream.createFrame(254);
outStream.writeByte(i1);
outStream.writeWord(i2);
}

public void frame35(int i1, int i2, int i3, int i4) // earthquake
{
outStream.createFrame(35);
outStream.writeByte(i1);
outStream.writeByte(i2);
outStream.writeByte(i3);
outStream.writeByte(i4);
sendMessage("Frame 35 tested");
updateRequired = true;
appearanceUpdateRequired = true;
}

public void frame230(int i1, int i2, int i3, int i4)  // i2 being negative logs you out, otherwise it doesn't log you out :O
{
outStream.createFrame(230);
outStream.writeWordA(i1);
outStream.writeWord(i2); // interface id?
outStream.writeWord(i3);
outStream.writeWordBigEndianA(i4); // junk? not sure
sendMessage("Frame 230 tested");
updateRequired = true;
appearanceUpdateRequired = true;
}


public void frame114(int i1) // system update
{
outStream.createFrame(114);
outStream.writeWordBigEndian(i1);
sendMessage("Frame 114 tested");
updateRequired = true;
appearanceUpdateRequired = true;
}

public void frame174(int i1, int i2, int i3) // doesn't kick you, so it's right, but doesn't do anything?
{
outStream.createFrame(174);
outStream.writeWord(i1);
outStream.writeByte(i2);
outStream.writeWord(i3);
sendMessage("Frame 174 tested");
updateRequired = true;
appearanceUpdateRequired = true;
}
public void frame246(int i1, int i2, int i3) // doesn't kick you, so it's right, but doesn't do anything?
{
outStream.createFrame(246);
outStream.writeWordBigEndian(i1);
outStream.writeWord(i2);
outStream.writeWord(i3);
flushOutStream();
sendMessage("Frame 246 tested");
}

public void frame171(int i1, int i2)
{
outStream.createFrame(171);
outStream.writeByte(i1);
outStream.writeWord(i2);
flushOutStream();
sendMessage("Frame 171 tested");
}

public void frame99(int i1) // makes minimap nonclickable etc.!!! 0 = unlock  2 = black above 2 = locked - xerozcheez
{
outStream.createFrame(99);
outStream.writeByte(i1);
sendMessage("Frame 99 tested");
updateRequired = true;
appearanceUpdateRequired = true;
}

public void frame218(int i1) // writes interface over chat, 1 shows all sendmessage stuff lolz
{
outStream.createFrame(218);
outStream.writeWordBigEndianA(i1);
sendMessage("Frame 218 tested");
updateRequired = true;
appearanceUpdateRequired = true;
}

public void frame61(int i1) // unknown, can write high amounts without crashing/logging out
{
outStream.createFrame(61);
outStream.writeByte(i1);
sendMessage("Frame 61 tested");
updateRequired = true;
appearanceUpdateRequired = true;
}

public void frame87(int i1, int i2) // can't go into 7500+ hmm - links with 36
{
outStream.createFrame(87);
outStream.writeWordBigEndian(i1);
outStream.writeDWord_v2(i2);
sendMessage("Frame 87 tested");
updateRequired = true;
appearanceUpdateRequired = true;
}

public void frame36(int i1, int i2) // can't go into 7500+ hmm - links with 87
{
outStream.createFrame(36);
outStream.writeWordBigEndian(i1);
outStream.writeByte(i2);
sendMessage("Frame 36 tested");
updateRequired = true;
appearanceUpdateRequired = true;
}
public void frame214(long i1) // logs out
{
outStream.createFrame(214);
outStream.writeQWord(i1);
sendMessage("Frame 214 tested");
updateRequired = true;
appearanceUpdateRequired = true;
}
public void frame187() // loads enter name interface
{
outStream.createFrame(187);
sendMessage("Frame 187 tested");
updateRequired = true;
appearanceUpdateRequired = true;
}
public void frame27() // loads enter amount interface
{
outStream.createFrame(27);
sendMessage("Frame 27 tested");
updateRequired = true;
appearanceUpdateRequired = true;
}
public void frame65() // crashes, then logs out
{
outStream.createFrame(65);
sendMessage("Frame 65 tested");
updateRequired = true;
appearanceUpdateRequired = true;
}
public void frame68() // turns split private chat off
{
outStream.createFrame(68);
sendMessage("Frame 68 tested");
updateRequired = true;
appearanceUpdateRequired = true;
}
public void frame78() // tested, not a fucking clue =\
{
outStream.createFrame(78);
sendMessage("Frame 78 tested");
updateRequired = true;
appearanceUpdateRequired = true;
}
public void frame81() // crashes, then logs out
{
outStream.createFrame(81);
sendMessage("Frame 81 tested");
updateRequired = true;
appearanceUpdateRequired = true;
}
public void frame1() // tested, not a fucking clue =\
{
outStream.createFrame(1);
sendMessage("Frame 1 tested");
updateRequired = true;
appearanceUpdateRequired = true;
}
// j frames:

public void frame160(int i1, int i2, int i3) // objects according to whitefang, dunno what though hmm
{
outStream.createFrame(85);
outStream.writeByteC(currentY & ~7);	// packetTileCoordY
outStream.writeByteC(currentX & ~7);	// packetTileCoordX
outStream.createFrame(160);
outStream.writeByteA(i1);
outStream.writeByteA(i2);
outStream.writeWordA(i3);
updateRequired = true;
appearanceUpdateRequired = true;
sendMessage("Frame 160 tested");
}
public void frame117(int i1, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11) // moving graphics
{
outStream.createFrame(117); 
outStream.writeByte(i1);
outStream.writeByte(i2);
outStream.writeByte(i3);
outStream.writeWord(i4);
outStream.writeWord(i5);
outStream.writeByte(i6);
outStream.writeByte(i7);
outStream.writeWord(i8);
outStream.writeWord(i9);
outStream.writeByte(i10);
outStream.writeByte(i11);
sendMessage("Frame 117 tested");
updateRequired = true;
appearanceUpdateRequired = true;
}
/*END OF TESTING FRAMES*/


public int hasegg = 0;

public void Teleblock()
{
teleblock = true;
sendMessage("A teleblock has been cast on you!");
}
/*MISC STUFF*/
public boolean duelring = false;
public boolean ancientstele = false;
public int ancients = 0;
public boolean teleblock = false;
/*PRAYER STUFF*/
public int currentpray = playerLevel[playerPrayer];
public int maxpray = getLevelForXP(playerXP[5]);
public int PrayerDrain = 0;
public int PrayerTimer = 0;
public int NewDrain = 0;
public boolean Str1 = false;
public boolean Str2 = false; 
public boolean Str3 = false;
public boolean Skin = false;
public boolean Rock = false;
public boolean Steel = false;
public boolean Clarity = false;
public boolean Reflex2 = false;
public boolean Reflex3 = false;
public boolean ProtItem = false;
public boolean RapidRestore = false; 
public boolean RapidHeal = false;
public boolean Retribution = false;
public boolean Redemption = false;
public boolean DrainPray = false;
public boolean ProtMage = false;
public boolean ProtRange = false;
public boolean ProtMelee = false;
public boolean Smite = false;
public boolean Noprayer = true;

public void ResetProtPrayers()
{
ProtMage = false;
ProtRange = false;
ProtMelee = false;
Str1 = false;
Str2 = false; 
Str3 = false;
Skin = false;
Rock = false;
Steel = false;
Clarity = false;
Reflex2 = false;
Reflex3 = false;
ProtItem = false;
RapidRestore = false; 
RapidHeal = false;
Retribution = false;
Redemption = false;
}

public void noprayer()
{
if(
ProtMage == false &&
ProtRange == false &&
ProtMelee == false &&
Str1 == false &&
Str2 == false && 
Str3 == false &&
Skin == false &&
Rock == false &&
Steel == false &&
Clarity == false &&
Reflex2 == false &&
Reflex3 == false &&
ProtItem == false &&
RapidRestore == false && 
RapidHeal == false &&
Retribution == false &&
Redemption == false)
{
Noprayer = true;
DrainPray = false;
}
else
{
Noprayer = false;
}
}

public void newdrain()
{
int OldDrain = PrayerDrain;
if(NewDrain >= OldDrain && Noprayer == false)
{
PrayerDrain = OldDrain;
}
else if(NewDrain <= OldDrain || Noprayer == true)
{
PrayerDrain = NewDrain;
}
}

/*END OF PRAYER STUFF*/






public void levelup(int skill)
{
switch(skill)  
{
       case 0: // Attack levelup
            sendFrame164(6247);
            sendFrame126("Congratulations, you just advanced an attack level!", 6248);
            sendFrame126("Your attack level is now "+playerLevel[0]+" .", 6249);
            sendMessage("Congratulations, you just advanced an attack level.");
            NpcDialogueSend = true;
            break;
         case 1: // Strength
            sendFrame164(6206);
            sendFrame126("Congratulations, you just advanced a strength level!", 6207);
            sendFrame126("Your strength level is now "+playerLevel[2]+" .", 6208);
            sendMessage("Congratulations, you just advanced a strength level.");
            NpcDialogueSend = true;
            break;
         case 2: // Defence
            sendFrame164(6253);
            sendFrame126("Congratulations, you just advanced a defence level!", 6254);
            sendFrame126("Your defence level is now "+playerLevel[1]+" .", 6255);
            sendMessage("Congratulations, you just advanced a defence level.");
            NpcDialogueSend = true;
            break;
         case 3: //Hitpoints
            sendFrame164(6216);
            sendFrame126("Congratulations, you just advanced a hitpoints level!", 6217);
            sendFrame126("Your hitpoints level is now "+playerLevel[3]+" .", 6218);
            sendMessage("Congratulations, you just advanced a hitpoints level.");
            NpcDialogueSend = true;
            break;
         case 4: //Ranging
            sendFrame164(4443);
            sendFrame126("Congratulations, you just advanced a ranged level!", 4444);
            sendFrame126("Your ranged level is now "+playerLevel[4]+" .", 4445);
            sendMessage("Congratulations, you just advanced a ranging level.");
            NpcDialogueSend = true;
            break;
         case 5: // Prayer
            sendFrame164(6242);
            sendFrame126("Congratulations, you just advanced a prayer level!", 6243);
            sendFrame126("Your prayer level is now "+playerLevel[5]+" .", 6244);
            sendMessage("Congratulations, you just advanced a prayer level.");
            NpcDialogueSend = true;
            break;
         case 6: // Magic
            sendFrame164(6211);
            sendFrame126("Congratulations, you just advanced a magic level!", 6212);
            sendFrame126("Your magic level is now "+playerLevel[6]+" .", 6213);
            sendMessage("Congratulations, you just advanced a magic level.");
            NpcDialogueSend = true;
            break;
         case 7: //Cooking
            sendFrame164(6226);
            sendFrame126("Congratulations, you just advanced a cooking level!", 6227);
            sendFrame126("Your cooking level is now "+playerLevel[7]+" .", 6228);
            sendMessage("Congratulations, you just advanced a cooking level.");
            NpcDialogueSend = true;
            break;
         case 8: //Woodcutting
            sendFrame164(4272);
            sendFrame126("Congratulations, you just advanced a woodcutting level!", 4273);
            sendFrame126("Your woodcutitng level is now "+playerLevel[8]+" .", 4274);
            sendMessage("Congratulations, you just advanced a woodcutting level.");
            NpcDialogueSend = true;
            break;
         case 9: //Fletching
            sendFrame164(6231);
            sendFrame126("Congratulations, you just advanced a fletching level!", 6232);
            sendFrame126("Your fletching level is now "+playerLevel[9]+" .", 6233);
            sendMessage("Congratulations, you just advanced a fletching level.");
            NpcDialogueSend = true;
            break;
         case 10: //fishing
            sendFrame164(6258);
            sendFrame126("Congratulations, you just advanced a fishing level!", 6259);
            sendFrame126("Your fishing level is now "+playerLevel[10]+" .", 6260);
            sendMessage("Congratulations, you just advanced a fishing level.");
            NpcDialogueSend = true;
            break;
         case 11: //firemaking
            sendFrame164(4282);
            sendFrame126("Congratulations, you just advanced a fire making level!", 4283);
            sendFrame126("Your firemaking level is now "+playerLevel[11]+" .", 4284);
            sendMessage("Congratulations, you just advanced a fire making level.");
            NpcDialogueSend = true;
            break;
         case 12: //crafting
            sendFrame164(6263);
            sendFrame126("Congratulations, you just advanced a crafting level!", 6264);
            sendFrame126("Your crafting level is now "+playerLevel[12]+" .", 6265);
            sendMessage("Congratulations, you just advanced a crafting level.");
            NpcDialogueSend = true;
            break;
         case 13: //Smithing
            sendFrame164(6221);
            sendFrame126("Congratulations, you just advanced a smithing level!", 6222);
            sendFrame126("Your smithing level is now "+playerLevel[13]+" .", 6223);
            sendMessage("Congratulations, you just advanced a smithing level.");
            NpcDialogueSend = true;
            break;
         case 14: //Mining
            sendFrame164(4416);
            sendFrame126("Congratulations, you just advanced a mining level!", 4417);
            sendFrame126("Your mining level is now "+playerLevel[14]+" .", 4418);
            sendMessage("Congratulations, you just advanced a mining level.");
            NpcDialogueSend = true;
            break;
         case 15: //Herblore
            sendFrame164(6237);
            sendFrame126("Congratulations, you just advanced a herblore level!", 6238);
            sendFrame126("Your herblore level is now "+playerLevel[15]+" .", 6239);
            sendMessage("Congratulations, you just advanced a herblore level.");
            NpcDialogueSend = true;
            break;
         case 16: //Agility
            sendFrame164(4277);
            sendFrame126("Congratulations, you just advanced a agility level!", 4278);
            sendFrame126("Your agility level is now "+playerLevel[16]+" .", 4279);
            sendMessage("Congratulations, you just advanced an agility level.");
            NpcDialogueSend = true;
            break;
         case 17: //Thieving
            sendFrame164(4261);
            sendFrame126("Congratulations, you just advanced a thieving level!", 6262);
            sendFrame126("Your theiving level is now "+playerLevel[17]+" .", 6263);
            sendMessage("Congratulations, you just advanced a thieving level.");
            NpcDialogueSend = true;
            break;
         case 18: //Slayer
            sendFrame164(12123);
            sendFrame126("Congratulations, you just advanced a slayer level!", 6207);
            sendFrame126("Your slayer level is now "+playerLevel[18]+" .", 6208);
            sendMessage("Congratulations, you just advanced a slayer level.");
            NpcDialogueSend = true;
            break;
         case 19: //Farming
      //      sendFrame164(4261);
      //      sendFrame126("Congratulations, you just advanced a thieving level!", 6207);
      //      sendFrame126("Your farming level is now "+playerLevel[19]+" .", 6208);
            sendMessage("Congratulations, you just advanced a farming level.");
            NpcDialogueSend = true;
            break;
         case 20: //Runecrafting
            sendFrame164(4267);
            sendFrame126("Congratulations, you just advanced a runecrafting level!", 4268);
            sendFrame126("Your runecrafting level is now "+playerLevel[20]+" .", 4269);
            sendMessage("Congratulations, you just advanced a runecrafting level.");
            NpcDialogueSend = true;
            break;

}
}

public void stillgfx(int id, int Y, int X)
{
for (Player p : server.playerHandler.players)
{
if(p != null) 
{
client person = (client)p;
if((person.playerName != null || person.playerName != "null"))
{
if(person.distanceToPoint(X, Y) <= 60)
{
person.stillgfx2(id, Y, X);
}
}
}
}
}
public void stillgfx2(int id, int Y, int X)
{
outStream.createFrame(85);
outStream.writeByteC(Y - (mapRegionY * 8));
outStream.writeByteC(X - (mapRegionX * 8));
outStream.createFrame(4);
outStream.writeByte(0);//Tiles away (X >> 4 + Y & 7)
outStream.writeWord(id);//Graphic id
outStream.writeByte(100);//height of the spell above it's basic place, i think it's written in pixels 100 pixels higher
outStream.writeWord(0);//Time before casting the graphic
}
public void Graphics(int starth, int finishh)
{
outStream.createFrame(117); 
outStream.writeByte(50);
outStream.writeByte(50);
outStream.writeByte(50);
outStream.writeWord(50);
outStream.writeWord(50);
outStream.writeByte(starth);
outStream.writeByte(finishh);
outStream.writeWord(50);
outStream.writeWord(50);
outStream.writeByte(50);
outStream.writeByte(50);
}

public void Graphics2(int a, int b, int c, int d, int e, int f, int g, int h, int i)
{
outStream.createFrame(117); /*WhiteFang says this is right!!!!!! Btw we write 50 as a test - xerozcheez*/
outStream.writeByte(a);
outStream.writeByte(b);
outStream.writeByte(c);
outStream.writeWord(d);
outStream.writeWord(e);
outStream.writeByte(60);
outStream.writeByte(10);
outStream.writeWord(f);
outStream.writeWord(g);
outStream.writeByte(h);
outStream.writeByte(i);
}

public void Graphics3()
{
outStream.createFrame(117); /*WhiteFang says this is right!!!!!! Btw we write 50 as a test - xerozcheez*/
outStream.writeByte(absX); // not coord
outStream.writeByte(absY); // not coord
outStream.writeByte(50); // not speed
outStream.writeWord(150); // not id
outStream.writeWord(150); // not id
outStream.writeByte(50); // starting height
outStream.writeByte(50); // finishing height
outStream.writeWord(150); // not id
outStream.writeWord(150); // not id
outStream.writeByte(absX); // not coord
outStream.writeByte(absY); // not coord
}

public void Graphics4()
{
outStream.createFrame(117); /*WhiteFang says this is right!!!!!! Btw we write 50 as a test - xerozcheez*/
outStream.writeByte(0); // not coord
outStream.writeByte(0); // not coord
outStream.writeByte(0); // not speed
outStream.writeWord(0); // not id
outStream.writeWord(0); // not id
outStream.writeByte(50); // starting height
outStream.writeByte(50); // finishing height
outStream.writeWord(0); // not id
outStream.writeWord(0); // not id
outStream.writeByte(0); // not coord
outStream.writeByte(0); // not coord
}

public void Graphics5()
{
outStream.createFrame(85);
outStream.writeByteC((absY - 1) - (mapRegionY * 8));
outStream.writeByteC((absX - 1) - (mapRegionX * 8));
outStream.createFrame(117); // The moving gfx frame
outStream.writeByte(3); // Tiles away : (i3 >> 4 & 7); - i3 being the byte written - Tiles away (X >> 4 + Y & 7)
outStream.writeByte(currentX); // Coord X of where tiles are away from (use absX when casting spell with player)
outStream.writeByte(currentY); // Coord Y of where tiles are away from (use absY when casting spell with player)
outStream.writeWord(1618); // speed 
outStream.writeWord(9); // angle
outStream.writeByte(50); // starting height
outStream.writeByte(50); // finishing height
outStream.writeWord(51); // releates to below (adds anInt1161 in client) (think it's starting id)
outStream.writeWord(86); // relates to above (adds anInt1161 in client) (think it's finishing id)
outStream.writeByte(16); // finishing coord (possibly)
outStream.writeByte(64); // not coord
}

public void Graphics6()
{
outStream.createFrame(85);
outStream.writeByteC((absY - 1) - (mapRegionY * 8));
outStream.writeByteC((absX - 1) - (mapRegionX * 8));
outStream.createFrame(117); // The moving gfx frame
outStream.writeByte(37); // Tiles away : (i3 >> 4 & 7); - i3 being the byte written - Tiles away (X >> 4 + Y & 7)
outStream.writeByte(33); // Coord X of where tiles are away from (use absX when casting spell with player)
outStream.writeByte(49); // Coord Y of where tiles are away from (use absY when casting spell with player)
outStream.writeWord(1601); // speed 
outStream.writeWord(91); // angle
outStream.writeByte(172); // starting height
outStream.writeByte(124); // finishing height
outStream.writeWord(51); // relates to below (adds anInt1161 in client) (think it's starting id)
outStream.writeWord(86); // relates to above (adds anInt1161 in client) (think it's finishing id)
outStream.writeByte(16); // finishing coord (possibly)
outStream.writeByte(64); // not coord
}
/*public void TeleTo(String s, int level)
{
checkwildy();
if(inwildy == false && playerLevel[6] > level) 
{
     if (s == "Varrock")
     {
        teleportToX = 3210;
        teleportToY = 3424;
        addSkillXP((2*playerLevel[6]), 6);
        heightLevel = 0;
        actionTimer = 30;
     }

     if (s == "Falador")
     {
        teleportToX = 2964;
        teleportToY = 3378;
        addSkillXP((3*playerLevel[6]), 6);
        heightLevel = 0;
             
     }

     if (s == "Lumby")
     {
        teleportToX = 3222;
        teleportToY = 3218;
        addSkillXP((4*playerLevel[6]), 6);
        heightLevel = 0;
     }

     if (s == "Camelot")
     {
        teleportToX = 2757;
        teleportToY = 3477;
       addSkillXP((5*playerLevel[6]), 6);
        heightLevel = 0;
     }

     if (s == "Ardougne")
     {
        teleportToX = 2662;
        teleportToY = 3305;
        addSkillXP((12*playerLevel[6]), 6);
        heightLevel = 0;
        
     }

     if (s == "Watchtower")
     {
        teleportToX = 2549;
        teleportToY = 3113;
        addSkillXP((15*playerLevel[6]), 6);
        heightLevel = 0;
        
     }

     if (s == "Trollheim")
     {
        teleportToX = 3091;
        teleportToY = 3963;
        addSkillXP((40*playerLevel[6]), 6);
        heightLevel = 0;
     }

     if (s == "Ape")
     {
        teleportToX = 2795;
        teleportToY = 2799;
        addSkillXP((40*playerLevel[6]), 6);
        heightLevel = 1;
     }
}

else if(playerLevel[6] < level)
{
sendMessage("You need a magic level of "+level+" to cast this spell.");
}
else if(inwildy == true) 
{
sendMessage("You cannot teleport above Y 3712 wilderness.");
}
	
updateRequired = true; 
appearanceUpdateRequired = true;
  }*/

public void TeleTo(String s, int level)
{
teleX = absX;
teleY = absY;
newheightLevel = heightLevel;
checkwildy();
if(inwildy == false && playerLevel[6] > level && teleblock == false && actionTimer <= 1) 
{
     if (s == "Varrock")
     {
        teleX = 3210;
        teleY = 3424;
        addSkillXP((2*playerLevel[6]), 6);
        newheightLevel = 0;
     }

     if (s == "Falador")
     {
        teleX = 2964;
        teleY = 3378;
        addSkillXP((3*playerLevel[6]), 6);
        newheightLevel = 0;
             
     }

     if (s == "Lumby")
     {
        teleX = 2425;
        teleY = 3078;
        addSkillXP((4*playerLevel[6]), 6);
        newheightLevel = 0;
     }

     if (s == "Camelot")
     {
        teleX = 2757;
        teleY = 3477;
       addSkillXP((5*playerLevel[6]), 6);
        newheightLevel = 0;
     }

     if (s == "Ardougne")
     {
        teleX = 2662;
        teleY = 3305;
        addSkillXP((12*playerLevel[6]), 6);
        newheightLevel = 0;
        
     }

     if (s == "Watchtower")
     {
        teleX = 2549;
        teleY = 3113;
        addSkillXP((15*playerLevel[6]), 6);
        newheightLevel = 0;
        
     }

     if (s == "Trollheim")
     {
        teleX = 3285;
        teleY = 3343;
        addSkillXP((40*playerLevel[6]), 6);
        newheightLevel = 0;
     }

     if (s == "Ape")
     {
        teleX = 2795;
        teleY = 2799;
        addSkillXP((40*playerLevel[6]), 6);
        newheightLevel = 1;
     }
    if (s == "Paddewwa"){

        teleX = 3131; 
        teleY = 9912;
        addSkillXP((15*playerLevel[playerMagic]), playerMagic);
        newheightLevel = 0;
     }
 if (s == "Senntisten"){

        teleX = 3312; 
        teleY = 3376;
        addSkillXP((20*playerLevel[playerMagic]), playerMagic);
        newheightLevel = 0;
     }
  if (s == "Kharyrll"){
    
        teleportToX = 3493; 
        teleportToY = 3485;
        addSkillXP((25*playerLevel[playerMagic]), playerMagic);
        newheightLevel = 0;
     }
  if (s == "Kharyrll"){
    
        teleportToX = 3493; 
        teleportToY = 3485;
        addSkillXP((25*playerLevel[playerMagic]), playerMagic);
        newheightLevel = 0;
     }
    if (s == "Lasaar"){

        teleX = 3007; 
        teleY = 3477;
        addSkillXP((35*playerLevel[playerMagic]), playerMagic);
        newheightLevel = 0;
     }
    if (s == "Carrallangar"){

        teleX = 3161; 
        teleY = 3671;
        addSkillXP((40*playerLevel[playerMagic]), playerMagic);
        newheightLevel = 0;
     }
    /*if (s == "Carrallangar"){

        teleX = 2990; 
        teleY = 3695;
        addSkillXP((50*playerLevel[playerMagic]), playerMagic);
        newheightLevel = 0;
     }*/
 if (s == "Annakarl"){

        teleX = 3288; 
        teleY = 3886;
        addSkillXP((55*playerLevel[playerMagic]), playerMagic);
        newheightLevel = 0;
     }
  if (s == "Ghorrock"){
    
        teleX = 3091;
        teleY = 3963;
        addSkillXP((65*playerLevel[playerMagic]), playerMagic);
        newheightLevel = 0;
     }
closeInterface();
teleport();
actionTimer = 10;
}

else if(teleblock == true)
{
sendMessage("A magical force stops you from teleporting.");
}
else if(playerLevel[6] < level)
{
sendMessage("You need a magic level of "+level+" to cast this spell.");
}
else if(inwildy == true) 
{
sendMessage("You cannot teleport above Y 3712 wilderness.");
}
	
updateRequired = true; 
appearanceUpdateRequired = true;
  }
public boolean HasItemAmount(int itemID, int itemAmount) {
      int playerItemAmountCount = 0;
      for (int i=0; i<playerItems.length; i++)
{
         if (playerItems[i] == itemID+1)
{
            playerItemAmountCount = playerItemsN[i];
}
                 if(playerItemAmountCount >= itemAmount){
                    return true;}
            }
         return false;
      }
                 
 

    public boolean Has2Items(int itemID, int amount, int itemID2, int amount2){
   if(HasItemAmount(itemID, amount)) {
      if(HasItemAmount(itemID2, amount2)){
         return true;
      } else{ return false; }
   } else{ return false; }
    }

    public boolean Has3Items(int itemID, int amount, int itemID2, int amount2, int itemID3, int amount3){
   if(HasItemAmount(itemID, amount)){
      if(HasItemAmount(itemID2, amount2)){
         if(HasItemAmount(itemID3, amount3)){
      return true;
         } else{ return false; }
      } else{ return false; }
   } else{ return false; }
    }

   


    public void teleport(int x, int y, int h, int xp, int lvl){
   teleportToX = x;
   teleportToY = y;
   heightLevel = h;
   addSkillXP(xp, lvl);
    }

public void TeleToAdvanced(String cityName, int lvl, String type) {
      if(playerLevel[playerMagic] >= lvl){
      String line = "";
      String token = "";
      String token2 = "";
      String token2_2 = "";
      String[] token3 = new String[25];
      boolean EndOfFile = false;
      int ReadMode = 0;
      BufferedReader characterfile = null;
      try {
         characterfile = new BufferedReader(new FileReader("Teleport.cfg"));
      } catch(FileNotFoundException fileex) {
         misc.println("Teleport.cfg: not found.");
         
      }
      try {
         line = characterfile.readLine();
      } catch(IOException ioexception) {
         misc.println("Teleport.cfg: error loading file.");
         
      }
      while(EndOfFile == false && line != null) {
         line = line.trim();
         int spot = line.indexOf("=");
         if (spot > -1) {
            token = line.substring(0, spot);
            token = token.trim();
            token2 = line.substring(spot + 1);
            token2 = token2.trim();
            token2_2 = token2.replaceAll("\t\t", "\t");
            token2_2 = token2_2.replaceAll("\t\t", "\t");
            token2_2 = token2_2.replaceAll("\t\t", "\t");
            token2_2 = token2_2.replaceAll("\t\t", "\t");
            token2_2 = token2_2.replaceAll("\t\t", "\t");
            token3 = token2_2.split("\t");
            int RandomNum = 0;
         
   if (token.equals("Tele")) {
            String city = token3[0];
if(type.equalsIgnoreCase("cmd")){RandomNum = 1;}
if(type.equalsIgnoreCase("mage")){RandomNum = misc.random(25);}
            int RTID = Integer.parseInt(token3[1]);
            int MLvl = Integer.parseInt(token3[2]);
            int RuneTypes = Integer.parseInt(token3[3]);
            int Rune1 = Integer.parseInt(token3[4]);
            int Rune2 = Integer.parseInt(token3[5]);
            int Rune3 = Integer.parseInt(token3[6]);
            int Item = Integer.parseInt(token3[7]);
            int TeleX = Integer.parseInt(token3[8]);
            int TeleY = Integer.parseInt(token3[9]);
            int Height = Integer.parseInt(token3[10]);
            int Xp = Integer.parseInt(token3[11]);
            int R1Amt = Integer.parseInt(token3[12]);
            int R2Amt = Integer.parseInt(token3[13]);
            int R3Amt = Integer.parseInt(token3[14]);
            int ItmAmt = Integer.parseInt(token3[15]);
      
if (cityName.equalsIgnoreCase(city)){ 
   if(RandomNum == RTID){

if(type.equalsIgnoreCase("cmd")){
teleport(TeleX, TeleY, Height, Xp, playerMagic);
}
      if(Item == -1){
         if(RuneTypes == 2){
            if(Has2Items(Rune1, R1Amt, Rune2, R2Amt)){
               deleteItem(Rune1, GetItemSlot(Rune1), R1Amt);
               deleteItem(Rune2, GetItemSlot(Rune2), R2Amt);
               teleport(TeleX, TeleY, Height, Xp, playerMagic);
            }else{
sendMessage("You don't have the required runes to do that.");}
            }
            if(RuneTypes == 3){
            if(Has3Items(Rune1, R1Amt, Rune2, R2Amt, Rune3, R3Amt)){
               
               deleteItem(Rune1, GetItemSlot(Rune1), R1Amt);
               deleteItem(Rune2, GetItemSlot(Rune2), R2Amt);
               deleteItem(Rune3, GetItemSlot(Rune3), R3Amt);
               teleport(TeleX, TeleY, Height, Xp, playerMagic);
   
            }else{
sendMessage("You don't have the required runes to do that.");}
            }
         }else{
            if(RuneTypes == 2){
            if(Has2Items(Rune1, R1Amt, Rune2, R2Amt)){             if(HasItemAmount(Item, ItmAmt)){
               
               deleteItem(Item, GetItemSlot(Item), ItmAmt);
               deleteItem(Rune1, GetItemSlot(Rune1), R1Amt);
               deleteItem(Rune2, GetItemSlot(Rune2), R1Amt);
               teleport(TeleX, TeleY, Height, Xp, playerMagic);
            
            }else{
sendMessage("You need a "+GetItemName(Item)+" to do that.");}
            }else{
sendMessage("You don't have the required runes to do that.");}
            }
            if(RuneTypes == 3){
            if(Has3Items(Rune1, R1Amt, Rune2, R2Amt, Rune3, R3Amt)){             if(HasItemAmount(Item, ItmAmt)){
               
               deleteItem(Item, GetItemSlot(Item), ItmAmt);
               deleteItem(Rune1, GetItemSlot(Rune1), R1Amt);
               deleteItem(Rune2, GetItemSlot(Rune2), R1Amt);
               deleteItem(Rune3, GetItemSlot(Rune3), R3Amt);
               teleport(TeleX, TeleY, Height, Xp, playerMagic);

            }else{
sendMessage("You need a "+GetItemName(Item)+" to do that.");}
            }else{
sendMessage("You don't have the required runes to do that.");}
            }
      }   
   }
}         
   }

                  
         } else {
            if (line.equals("[ENDOFTELELIST]")) {
               try { characterfile.close(); } catch(IOException ioexception) { }
               
            }
         }

         try {
            line = characterfile.readLine();
         } catch(IOException ioexception1) { EndOfFile = true; }
      }
      try { characterfile.close(); } catch(IOException ioexception) { }
      }else{ sendMessage("You need level "+lvl+" to use this spell.");}
   } 

	public boolean playerHasItem(int itemID)
	{
		for (int i=0; i<playerItems.length; i++)
		{
			if (playerItems[i] == itemID)
			{
				return true;
			}
		}
		return false;

	}
	public int getItemSlotReturn(int itemID)
	{
		for (int slot=0; slot < playerItems.length; slot++)
		{
			if (playerItems[slot] == (itemID +1))
			{
				return slot;
			}
		}
		return -1;
	}

public boolean Inair = false;	
public Calendar cal = new GregorianCalendar();
   public int hour12 = cal.get(Calendar.HOUR);            // 0..11
   public int hour24 = cal.get(Calendar.HOUR_OF_DAY);     // 0..23
   public int min = cal.get(Calendar.MINUTE);             // 0..59
   public int sec = cal.get(Calendar.SECOND);             // 0..59
   public int ms = cal.get(Calendar.MILLISECOND);         // 0..999
   public int ampm = cal.get(Calendar.AM_PM);             // 0=AM, 1=PM
             public static boolean snowFilter;
	public static boolean dustFilter;
	public static boolean dizzyFilter;
	public static boolean afternoonFilter;
	public static boolean eveningFilter;
	public static boolean nightFilter;
	public static boolean resetFilters;

    public static boolean morningTime;
        public static boolean afternoonTime;
	public static boolean eveningTime;
	public static boolean nightTime;


public void getTime()
{
sendMessage("hour: "+hour24+" mins: "+min+" secs: "+sec);
}



public int actionButtonId = 0;
public boolean actionset = false;

public void setInterfaceWalkable(int ID){

			outStream.createFrame(208);
			outStream.writeWordBigEndian_dup(ID);
			flushOutStream();}

public void setTime(){
if(morningTime){setInterfaceWalkable(65535);}

if(afternoonTime){setInterfaceWalkable(12416);}

if(eveningTime){setInterfaceWalkable(12418);}

if(nightTime){setInterfaceWalkable(12414);}}

public int friendslot = 0;
public long friend64 = 0;
/* ADD MORE TWO HANDED ITEMS HERE */

public static int more2handed[] = {7158};

public static int more2handed()
{
return more2handed[more2handed.length];
}


public boolean item2handed(int ID)
{
if (ID == 7158 || ID == 1307 || ID == 1309 || ID == 1311 || ID == 1315 || ID == 1317 || ID == 1319 || ID == 6609)
{
return true;
}
else
{
return false;
}
}

/* END OF 2 HANDED */


/*SMITHING*/
public int removeBar(int removeID) {
 if(removeID == 1205 || removeID == 1351 || removeID == 1103
     || removeID == 1139 || removeID == 819 || removeID == 1277
     || removeID == 1422 || removeID == 1075 || removeID == 1155
     || removeID == 39 || removeID == 1321 || removeID == 1337
     || removeID == 1087 || removeID == 1173 || removeID == 864
     || removeID == 1291 || removeID == 1375 || removeID == 1117
     || removeID == 1189 || removeID == 1307 || removeID == 3095
     || removeID == 4819) {
      return 2349;
     }
     if(removeID == 1203 || removeID == 1349 || removeID == 1420
     || removeID == 1137 || removeID == 1279 || removeID == 820
     || removeID == 4820 || removeID == 1323 || removeID == 40
     || removeID == 1293 || removeID == 1153 || removeID == 863
     || removeID == 1175 || removeID == 1335 || removeID == 1363
     || removeID == 1101 || removeID == 4540 || removeID == 1191
     || removeID == 3096 || removeID == 1309 || removeID == 1067
     || removeID == 1081 ||
removeID == 1115) {
      return 2351;
     }
     if(removeID == 1207 || removeID == 3097 || removeID == 1353
     || removeID == 1424 || removeID == 1141 || removeID == 1281
     || removeID == 1325 || removeID == 1295 || removeID == 1157
     || removeID == 1177 || removeID == 1339 || removeID == 1365
     || removeID == 1105 || removeID == 1193 || removeID == 1069
     || removeID == 1083 || removeID == 1311 || removeID == 1119
     || removeID == 1539 ||
removeID == 821 || removeID == 41
     || removeID == 2 ||
removeID == 2370 || removeID == 865
     || removeID == 4544) {
      return 2353;
     }
//Mith
     if(removeID == 1209 || removeID == 3099 || removeID == 1355
     || removeID == 1428 || removeID == 1143 || removeID == 1285
     || removeID == 1329 || removeID == 1299 || removeID == 1159
     || removeID == 1181 || removeID == 1343 || removeID == 1369
     || removeID == 1109 || removeID == 1197 || removeID == 1071
     || removeID == 1085 || removeID == 1315 || removeID == 1121
     || removeID == 822 || removeID == 4822 || removeID == 42
     || removeID == 42 || removeID == 866) {
      return 2359;
     }
//Addy
     if(removeID == 1211 || removeID == 3100 || removeID == 1430
     || removeID == 1145 || removeID == 1287 || removeID == 1331
     || removeID == 1301 || removeID == 1161 || removeID == 1183
     || removeID == 1371 || removeID == 1111 || removeID == 1073
     || removeID == 1091 || removeID == 1317 || removeID == 1123
     || removeID == 823 ||
removeID == 4823 || removeID == 43
     || removeID == 867 ||
removeID == 1199) {
      return 2361;
     }
//Rune
     if(removeID == 1213 || removeID == 3101 || removeID == 1432
     || removeID == 1147 || removeID == 1289 || removeID == 1333
     || removeID == 1303 || removeID == 1163 || removeID == 1185
     || removeID == 1347 || removeID == 1373 || removeID == 1113
     || removeID == 1201 || removeID == 1079 || removeID == 1093
     || removeID == 1319 || removeID == 1127 || removeID == 824
     || removeID == 4824 || removeID == 44 || removeID == 868) {
      return 2363;
     }
     return 0;
}

public int barsNeeded(int slot, int column) {
 if (column == 1119) {
  if (slot == 0 || slot == 1) return 1;
  if (slot == 2 || slot == 3) return 2;
  if (slot == 4) return 3;
 }
 if (column == 1120) {
  if (slot == 0 || slot == 1) return 1;
  if (slot == 2 || slot == 3) return 3;
  if (slot == 4) return 2;
 }
 if (column == 1121) {
  if (slot == 0 || slot == 1 || slot == 2) return 3;
  if (slot == 3) return 5;
  if (slot == 4) return 1;
 }
 if (column == 1122) {
  if (slot == 0 || slot == 4) return 1;
  if (slot == 1 || slot == 2) return 2;
  if (slot == 3) return 3;
 }
 if (column == 1123) {
  if (slot == 0 || slot == 1 || slot == 2 || slot == 3 || slot == 4) return 1;
 }
 return 0;
}

public int barsNeeded2(int slot, int column) {
 if (column == 1119) {
  if (slot == 0 || slot == 1) return 5;
  if (slot == 2 || slot == 3) return 10;
  if (slot == 4) return 15;
 }
 if (column == 1120) {
  if (slot == 0 || slot == 1) return 5;
  if (slot == 2 || slot == 3) return 15;
  if (slot == 4) return 10;
 }
 if (column == 1121) {
  if (slot == 0 || slot == 1 || slot == 2) return 15;
  if (slot == 3) return 25;
  if (slot == 4) return 5;
 }
 if (column == 1122) {
  if (slot == 0 || slot == 4) return 5;
  if (slot == 1 || slot == 2) return 10;
  if (slot == 3) return 15;
 }
 if (column == 1123) {
  if (slot == 0 || slot == 1 || slot == 2 || slot == 3 || slot == 4) return 5;
 }
 return 0;
}

public int barsNeeded3(int slot, int column) {
 if (column == 1119) {
  if (slot == 0 || slot == 1) return 10;
  if (slot == 2 || slot == 3) return 20;
  if (slot == 4) return 30;
 }
 if (column == 1120) {
  if (slot == 0 || slot == 1) return 10;
  if (slot == 2 || slot == 3) return 30;
  if (slot == 4) return 20;
 }
 if (column == 1121) {
  if (slot == 0 || slot == 1 || slot == 2) return 30;
  if (slot == 3) return 50;
  if (slot == 4) return 10;
 }
 if (column == 1122) {
  if (slot == 0 || slot == 4) return 10;
  if (slot == 1 || slot == 2) return 20;
  if (slot == 3) return 30;
 }
 if (column == 1123) {
  if (slot == 0 || slot == 1 || slot == 2 || slot == 3 || slot == 4) return 10;
 }
 return 0;
}
public int smithXP(int barType, int barAmount) {
 if (barType == 2349) return barAmount*29;
 if (barType == 2351) return barAmount*25;
 if (barType == 2353) return barAmount*38;
 if (barType == 2359) return barAmount*50;
 if (barType == 2361) return barAmount*75;
 if (barType == 2363) return barAmount*75;
 return 0;
}

public boolean canSmith(int item) {
if(item == 1205 || item == 1351 && playerLevel[13] >= 1) {
   return true;
}
if(item == 1422 && playerLevel[13] >= 2) {
 return true;
}
if(item == 1139 && playerLevel[13] >= 3) {
 return true;
}
if(item == 1277 || item == 819 && playerLevel[13] >= 4) {
 return true;
}
if(item == 1321 || item == 39 &&playerLevel[13] >= 5) {
 return true;
}
if(item == 1291 && playerLevel[13] >= 6) {
 return true;
}
if(item == 1155 || item == 864 &&playerLevel[13] >= 7) {
 return true;
}
if(item == 1173 && playerLevel[13] >= 8) {
 return true;
}
if(item == 1337 && playerLevel[13] >= 9) {
 return true;
}
if(item == 1375 && playerLevel[13] >= 10) {
 return true;
}
if(item == 1103 && playerLevel[13] >= 11) {
 return true;
}
if(item == 1189 && playerLevel[13] >= 12) {
 return true;
}
if(item == 3095 && playerLevel[13] >= 13) {
 return true;
}
if(item == 1307 && playerLevel[13] >= 14) {
 return true;
}
if(item == 1203 && playerLevel[13] >= 15) {
 return true;
}
if(item == 1087  || item == 1075 || item == 1349 && playerLevel[13] >= 16) {
 return true;
}
if(item == 1420 && playerLevel[13] >= 17) {
 return true;
}
if(item == 1117 || item == 1137 && playerLevel[13] >= 18) {
 return true;
}
if(item == 1279 || item == 820 || item == 4820 && playerLevel[13] >= 19) {
 return true;
}
if(item == 1323 || item == 40 && playerLevel[13] >= 20) {
 return true;
}
if(item == 1293 && playerLevel[13] >= 21) {
 return true;
}
if(item == 1153 || item == 863 && playerLevel[13] >= 22) {
 return true;
}
if(item == 1175 && playerLevel[13] >= 23) {
 return true;
}
if(item == 1335 && playerLevel[13] >= 24) {
 return true;
}
if(item == 1363 && playerLevel[13] >= 25) {
 return true;
}
if(item == 1101 || item == 4540 && playerLevel[13] >= 26) {
 return true;
}
if(item == 1191 && playerLevel[13] >= 27) {
 return true;
}
if(item == 3096 && playerLevel[13] >= 28) {
 return true;
}
if(item == 1309 && playerLevel[13] >= 29) {
 return true;
}
if(item == 1207 && playerLevel[13] >= 30) {
 return true;
}
if(item == 1067 || item == 1081 || item == 1353 && playerLevel[13] >= 31) {
 return true;
}
if(item == 1424 && playerLevel[13] >= 32) {
 return true;
}
if(item == 1115 || item == 1141 && playerLevel[13] >= 33) {
 return true;
}
if(item == 1281 || item == 1539 || item == 821 && playerLevel[13] >= 34) {
 return true;
}
if(item == 1325 || item == 41 && playerLevel[13] >= 35) {
 return true;
}
if(item == 1295 || item == 2370 && playerLevel[13] >= 36) {
 return true;
}
if(item == 1157 || item == 865 && playerLevel[13] >= 37) {
 return true;
}
if(item == 1177 && playerLevel[13] >= 38) {
 return true;
}
if(item == 1339 && playerLevel[13] >= 39) {
 return true;
}
if(item == 1365 && playerLevel[13] >= 40) {
 return true;
}
if(item == 1105 && playerLevel[13] >= 41) {
 return true;
}
if(item == 1193 && playerLevel[13] >= 42) {
 return true;
}
if(item == 3097 && playerLevel[13] >= 43) {
 return true;
}
if(item == 1311 && playerLevel[13] >= 44) {
 return true;
}
if(item == 1069 || item == 1083 && playerLevel[13] >= 46) {
 return true;
}
if(item == 1119 && playerLevel[13] >= 48) {
 return true;
}
if(item == 4544 && playerLevel[13] >= 49) {
 return true;
}
if(item == 1209 && playerLevel[13] >= 50) {
 return true;
}
if(item == 1355 && playerLevel[13] >= 51) {
 return true;
}
if(item == 1428 && playerLevel[13] >= 52) {
 return true;
}
if(item == 1143 && playerLevel[13] >= 53) {
 return true;
}
if(item == 1285 || item == 822 || item == 4822 && playerLevel[13] >= 54) {
 return true;
}
if(item == 1329 || item == 42 && playerLevel[13] >= 55) {
 return true;
}
if(item == 1299 && playerLevel[13] >= 56) {
 return true;
}
if(item == 1159 || item == 866 && playerLevel[13] >= 57) {
 return true;
}
if(item == 1181 && playerLevel[13] >= 58) {
 return true;
}
if(item == 1343 && playerLevel[13] >= 59) {
 return true;
}
if(item == 1369 && playerLevel[13] >= 60) {
 return true;
}
if(item == 1109 && playerLevel[13] >= 61) {
 return true;
}
if(item == 1197 && playerLevel[13] >= 62) {
 return true;
}
if(item == 3099 && playerLevel[13] >= 63) {
 return true;
}
if(item == 1315 && playerLevel[13] >= 64) {
 return true;
}
if(item == 1071 || item == 1085 && playerLevel[13] >= 66) {
 return true;
}
if(item == 1121 && playerLevel[13] >= 68) {
 return true;
}
if(item == 1211 && playerLevel[13] >= 70) {
 return true;
}
if(item == 1430 && playerLevel[13] >= 72) {
 return true;
}
if(item == 1145 && playerLevel[13] >= 73) {
 return true;
}
if(item == 1287 || item == 823 || item == 4823 && playerLevel[13] >= 74) {
 return true;
}
if(item == 1331 || item == 43 && playerLevel[13] >= 75) {
 return true;
}
if(item == 1301 && playerLevel[13] >= 76) {
 return true;
}
if(item == 1161 || item == 867 && playerLevel[13] >= 77) {
 return true;
}
if(item == 1183 && playerLevel[13] >= 78) {
 return true;
}
if(item == 1371 && playerLevel[13] >= 79) {
 return true;
}
if(item == 1111 && playerLevel[13] >= 81) {
 return true;
}
if(item == 1199 && playerLevel[13] >= 82) {
 return true;
}
if(item == 3100 && playerLevel[13] >= 83) {
 return true;
}
if(item == 1317 && playerLevel[13] >= 84) {
 return true;
}
if(item == 1213 && playerLevel[13] >= 85) {
 return true;
}
if(item == 1073 || item == 1091 || item == 1359 && playerLevel[13] >= 86) {
 return true;
}
if(item == 1432 && playerLevel[13] >= 87) {
 return true;
}
if(item == 1123 || item == 1147 && playerLevel[13] >= 88) {
 return true;
}
if(item == 1289 || item == 824 || item == 4824 && playerLevel[13] >= 89) {
 return true;
}
if(item == 1333 || item == 44 && playerLevel[13] >= 90) {
 return true;
}
if(item == 1303 && playerLevel[13] >= 91) {
 return true;
}
if(item == 1163 || item == 868 && playerLevel[13] >= 92) {
 return true;
}
if(item == 1185 && playerLevel[13] >= 93) {
 return true;
}
if(item == 1347 && playerLevel[13] >= 94) {
 return true;
}
if(item == 1373 && playerLevel[13] >= 95) {
 return true;
}
if(item == 1113 && playerLevel[13] >= 96) {
 return true;
}
if(item == 1201 && playerLevel[13] >= 97) {
 return true;
}
if(item == 3101 && playerLevel[13] >= 98) {
 return true;
}
if(item == 1319 && playerLevel[13] >= 99) {
 return true;
}
if(item == 1079 && playerLevel[13] >= 99) {
 return true;
}
if(item == 1079 || item == 1093 || item == 1319 || item == 1127 && playerLevel[13] >= 99) {
 return true;
} else {
 return false;
}
//return false;
}

public void addItemToSmith(int id, int slot, int column, int amount)
{
   outStream.createFrameVarSizeWord(34); // init item to smith screen
   outStream.writeWord(column); // Column Across Smith Screen
   outStream.writeByte(4); // Total Rows?
   outStream.writeDWord(slot); // Row Down The Smith Screen
   outStream.writeWord(id+1); // item
   outStream.writeByte(amount); // how many there are?
   outStream.endFrameVarSizeWord();

}


public void initSmithing(int barType)
   {
outStream.createFrame(97);
outStream.writeWord(994);
 if(amountOfItem(barType) < 5) {
  sendQuest("@red@5bars@red@",1112);
 } else {
  sendQuest("@gre@5bars@gre@",1112);
 }
 if(amountOfItem(barType) < 3) {
  sendQuest("@red@3bars@red@",1109);
  sendQuest("@red@3bars@red@",1110);
  sendQuest("@red@3bars@red@",1118);
  sendQuest("@red@3bars@red@",1111);
  sendQuest("@red@3bars@red@",1095);
  sendQuest("@red@3bars@red@",1115);
  sendQuest("@red@3bars@red@",1090);
 } else {
  sendQuest("@gre@3bars@gre@",1109);
  sendQuest("@gre@3bars@gre@",1110);
  sendQuest("@gre@3bars@gre@",1118);
  sendQuest("@gre@3bars@gre@",1111);
  sendQuest("@gre@3bars@gre@",1095);
  sendQuest("@gre@3bars@gre@",1115);
  sendQuest("@gre@3bars@gre@",1090);
 }
 if(amountOfItem(barType) < 2) {
  sendQuest("@red@2bars@red@",1113);
  sendQuest("@red@2bars@red@",1116);
  sendQuest("@red@2bars@red@",1114);
  sendQuest("@red@2bars@red@",1089);
  sendQuest("@red@2bars@red@",8428);
 } else {
  sendQuest("@gre@2bars@gre@",1113);
  sendQuest("@gre@2bars@gre@",1116);
  sendQuest("@gre@2bars@gre@",1114);
  sendQuest("@gre@2bars@gre@",1089);
  sendQuest("@gre@2bars@gre@",8428);
 }
 if(amountOfItem(barType) < 1) {
  sendQuest("@red@1bar@red@",1125);
  sendQuest("@red@1bar@red@",1126);
  sendQuest("@red@1bar@red@",1127);
  sendQuest("@red@1bar@red@",1124);
  sendQuest("@red@1bar@red@",1128);
  sendQuest("@red@1bar@red@",1129);
  sendQuest("@red@1bar@red@",1130);
  sendQuest("@red@1bar@red@",13357);
  sendQuest("@red@1bar@red@",1131);
  sendQuest("@red@1bar@red@",11459);
 } else {
  sendQuest("@gre@1bar@gre@",1125);
  sendQuest("@gre@1bar@gre@",1126);
  sendQuest("@gre@1bar@gre@",1127);
  sendQuest("@gre@1bar@gre@",1124);
  sendQuest("@gre@1bar@gre@",1128);
  sendQuest("@gre@1bar@gre@",1129);
  sendQuest("@gre@1bar@gre@",1130);
  sendQuest("@gre@1bar@gre@",13357);
  sendQuest("@gre@1bar@gre@",1131);
  sendQuest("@gre@1bar@gre@",11459);
 }
if(barType == 2349) { //Bronze
 if(playerLevel[13] < 18) {
  sendQuest("@bla@Plate body@bla@",1101);
 } else {
  sendQuest("@whi@Plate body@whi@",1101);
 }
 if(playerLevel[13] < 16) {
  sendQuest("@bla@Plate legs@bla@",1099);
  sendQuest("@bla@Plate skirt@bla@",1100);
 } else {
  sendQuest("@whi@Plate legs@whi@",1099);
  sendQuest("@whi@Plate skirt@whi@",1100);
 }
 if(playerLevel[13] < 14) {
  sendQuest("@bla@2 hand sword@bla@",1088);
 } else {
  sendQuest("@whi@2 hand sword@whi@",1088);
 }
 if(playerLevel[13] < 13) {
  sendQuest("@bla@Claws@bla@",8429);
 } else {
  sendQuest("@whi@Claws@whi@",8429);
 }
 if(playerLevel[13] < 12) {
  sendQuest("@bla@Kite shield@bla@",1105);
 } else {
  sendQuest("@whi@Kite shield@whi@",1105);
 }
 if(playerLevel[13] < 11) {
  sendQuest("@bla@Chain body@bla@",1098);
 } else {
  sendQuest("@whi@Chain body@whi@",1098);
 }
 if(playerLevel[13] < 10) {
  sendQuest("@bla@Battle axe@bla@",1092);
 } else {
  sendQuest("@whi@Battle axe@whi@",1092);
 }
 if(playerLevel[13] < 9) {
  sendQuest("@bla@Warhammer@bla@",1083);
 } else {
  sendQuest("@whi@Warhammer@whi@",1083);
 }
 if(playerLevel[13] < 8) {
  sendQuest("@bla@Square shield@bla@",1104);
 } else {
  sendQuest("@whi@Square shield@whi@",1104);
 }
 if(playerLevel[13] < 7) {
  sendQuest("@bla@Full helm@bla@",1103);
  sendQuest("@bla@Throwing knives@bla@",1106);
 } else {
  sendQuest("@whi@Full helm@whi@",1103);
  sendQuest("@whi@Throwing knives@whi@",1106);
 }
 if(playerLevel[13] < 6) {
  sendQuest("@bla@Long sword@bla@",1086);
 } else {
  sendQuest("@whi@Long sword@whi@",1086);
 }
 if(playerLevel[13] < 5) {
  sendQuest("@bla@Scimitar@bla@",1087);
  sendQuest("@bla@Arrowtips@bla@",1108);
 } else {
  sendQuest("@whi@Scimitar@whi@",1087);
  sendQuest("@whi@Arrowtips@whi@",1108);
 }
 if(playerLevel[13] < 4) {
  sendQuest("@bla@Sword@bla@",1085);
  sendQuest("@bla@Dart tips@bla@",1107);
  sendQuest("@bla@Nails@bla@",13358);
 } else {
  sendQuest("@whi@Sword@whi@",1085);
  sendQuest("@whi@Dart tips@whi@",1107);
  sendQuest("@whi@Nails@whi@",13358);
 }
 if(playerLevel[13] < 3) {
  sendQuest("@bla@Medium helm@bla@",1102);
 } else {
  sendQuest("@whi@Medium helm@whi@",1102);
 }
 if(playerLevel[13] < 2) {
  sendQuest("@bla@Mace@bla@",1093);
 } else {
  sendQuest("@whi@Mace@whi@",1093);
 }
 if(playerLevel[13] < 1) {
  sendQuest("@bla@Dagger@bla@",1094);
  sendQuest("@bla@Axe@bla@",1091);
 } else {
  sendQuest("@whi@Dagger@whi@",1094);
  sendQuest("@whi@Axe@whi@",1091);
 }
 addItemToSmith(1205,0,1119,1);
 addItemToSmith(1351,0,1120,1);
 addItemToSmith(1103,0,1121,1);
 addItemToSmith(1139,0,1122,1);
 addItemToSmith(819,0,1123,10);
 addItemToSmith(1277,1,1119,1);
 addItemToSmith(1422,1,1120,1);
 addItemToSmith(1075,1,1121,1);
 addItemToSmith(1155,1,1122,1);
 addItemToSmith(39,1,1123,15);
 addItemToSmith(1321,2,1119,1);
 addItemToSmith(1337,2,1120,1);
 addItemToSmith(1087,2,1121,1);
 addItemToSmith(1173,2,1122,1);
 addItemToSmith(864,2,1123,5);
 addItemToSmith(1291,3,1119,1);
 addItemToSmith(1375,3,1120,1);
 addItemToSmith(1117,3,1121,1);
 addItemToSmith(1189,3,1122,1);
 //addItemToSmith(0,3,1123);
 sendQuest("",1132);
 sendQuest("",1096);
 addItemToSmith(1307,4,1119,1);
 addItemToSmith(3095,4,1120,1);
 //addItemToSmith(1307,4,1121);
 sendQuest("",11459);
 sendQuest("",11461);
 addItemToSmith(4819,4,1122,1);
 //addItemToSmith(1307,4,1123);
 sendQuest("",1135);
 sendQuest("",1134);
}
 if(barType == 2351) { //Iron
 if(playerLevel[13] < 33) {
  sendQuest("@bla@Plate body@bla@",1101);
 } else {
  sendQuest("@whi@Plate body@whi@",1101);
 }
 if(playerLevel[13] < 31) {
  sendQuest("@bla@Plate legs@bla@",1099);
  sendQuest("@bla@Plate skirt@bla@",1100);
 } else {
  sendQuest("@whi@Plate legs@whi@",1099);
  sendQuest("@whi@Plate skirt@whi@",1100);
 }
 if(playerLevel[13] < 29) {
  sendQuest("@bla@2 hand sword@bla@",1088);
 } else {
  sendQuest("@whi@2 hand sword@whi@",1088);
 }
 if(playerLevel[13] < 28) {
  sendQuest("@bla@Claws@bla@",8429);
 } else {
  sendQuest("@whi@Claws@whi@",8429);
 }
 if(playerLevel[13] < 27) {
  sendQuest("@bla@Kite shield@bla@",1105);
 } else {
  sendQuest("@whi@Kite shield@whi@",1105);
 }
 if(playerLevel[13] < 26) {
  sendQuest("@bla@Chain body@bla@",1098);
  sendQuest("@bla@Oil lantern frame@bla@",11461);
 } else {
  sendQuest("@whi@Chain body@whi@",1098);
  sendQuest("@whi@Oil lantern frame@whi@",11461);
 }
 if(playerLevel[13] < 25) {
  sendQuest("@bla@Battle axe@bla@",1092);
 } else {
  sendQuest("@whi@Battle axe@whi@",1092);
 }
 if(playerLevel[13] < 24) {
  sendQuest("@bla@Warhammer@bla@",1083);
 } else {
  sendQuest("@whi@Warhammer@whi@",1083);
 }
 if(playerLevel[13] < 23) {
  sendQuest("@bla@Square shield@bla@",1104);
 } else {
  sendQuest("@whi@Square shield@whi@",1104);
 }
 if(playerLevel[13] < 22) {
  sendQuest("@bla@Full helm@bla@",1103);
  sendQuest("@bla@Throwing knives@bla@",1106);
 } else {
  sendQuest("@whi@Full helm@whi@",1103);
  sendQuest("@whi@Throwing knives@whi@",1106);
 }
 if(playerLevel[13] < 21) {
  sendQuest("@bla@Long sword@bla@",1086);
 } else {
  sendQuest("@whi@Long sword@whi@",1086);
 }
 if(playerLevel[13] < 20) {
  sendQuest("@bla@Scimitar@bla@",1087);
  sendQuest("@bla@Arrowtips@bla@",1108);
 } else {
  sendQuest("@whi@Scimitar@whi@",1087);
  sendQuest("@whi@Arrowtips@whi@",1108);
 }
 if(playerLevel[13] < 19) {
  sendQuest("@bla@Sword@bla@",1085);
  sendQuest("@bla@Dart tips@bla@",1107);
  sendQuest("@bla@Nails@bla@",13358);
 } else {
  sendQuest("@whi@Sword@whi@",1085);
  sendQuest("@whi@Dart tips@whi@",1107);
  sendQuest("@whi@Nails@whi@",13358);
 }
 if(playerLevel[13] < 18) {
  sendQuest("@bla@Medium helm@bla@",1102);
 } else {
  sendQuest("@whi@Medium helm@whi@",1102);
 }
 if(playerLevel[13] < 17) {
  sendQuest("@bla@Mace@bla@",1093);
 } else {
  sendQuest("@whi@Mace@whi@",1093);
 }
 if(playerLevel[13] < 16) {
  sendQuest("@bla@Axe@bla@",1091);
 } else {
  sendQuest("@whi@Axe@whi@",1091);
 }
 if(playerLevel[13] < 15) {
  sendQuest("@bla@Dagger@bla@",1094);
 } else {
  sendQuest("@whi@Dagger@whi@",1094);
 }
 addItemToSmith(1203,0,1119,1);
 addItemToSmith(1349,0,1120,1);
 addItemToSmith(1101,0,1121,1);
 addItemToSmith(1137,0,1122,1);
 addItemToSmith(820,0,1123,10);
 addItemToSmith(1279,1,1119,1);
 addItemToSmith(1420,1,1120,1);
 addItemToSmith(1067,1,1121,1);
 addItemToSmith(1153,1,1122,1);
 addItemToSmith(40,1,1123,15);
 addItemToSmith(1323,2,1119,1);
 addItemToSmith(1335,2,1120,1);
 addItemToSmith(1081,2,1121,1);
 addItemToSmith(1175,2,1122,1);
 addItemToSmith(863,2,1123,5);
 addItemToSmith(1293,3,1119,1);
 addItemToSmith(1363,3,1120,1);
 addItemToSmith(1115,3,1121,1);
 addItemToSmith(1191,3,1122,1);
 //addItemToSmith(0,3,1123);
 sendQuest("",1132);
 sendQuest("",1096);
 addItemToSmith(1309,4,1119,1);
 addItemToSmith(3096,4,1120,1);
 addItemToSmith(4540,4,1121,1);
 addItemToSmith(4820,4,1122,1);
 //addItemToSmith(1307,4,1123);
 sendQuest("",1135);
 sendQuest("",1134);
}
 if(barType == 2353) { //Steel
 if(playerLevel[13] < 49) {
  sendQuest("@bla@Bullseye lantern@bla@",11461);
 } else {
  sendQuest("@whi@Bullseye lantern@whi@",11461);
 }
 if(playerLevel[13] < 48) {
  sendQuest("@bla@Plate body@bla@",1101);
 } else {
  sendQuest("@whi@Plate body@whi@",1101);
 }
 if(playerLevel[13] < 46) {
  sendQuest("@bla@Plate legs@bla@",1099);
  sendQuest("@bla@Plate skirt@bla@",1100);
 } else {
  sendQuest("@whi@Plate legs@whi@",1099);
  sendQuest("@whi@Plate skirt@whi@",1100);
 }
 if(playerLevel[13] < 44) {
  sendQuest("@bla@2 hand sword@bla@",1088);
 } else {
  sendQuest("@whi@2 hand sword@whi@",1088);
 }
 if(playerLevel[13] < 43) {
  sendQuest("@bla@Claws@bla@",8429);
 } else {
  sendQuest("@whi@Claws@whi@",8429);
 }
 if(playerLevel[13] < 42) {
  sendQuest("@bla@Kite shield@bla@",1105);
 } else {
  sendQuest("@whi@Kite shield@whi@",1105);
 }
 if(playerLevel[13] < 41) {
  sendQuest("@bla@Chain body@bla@",1098);
 } else {
  sendQuest("@whi@Chain body@whi@",1098);
 }
 if(playerLevel[13] < 40) {
  sendQuest("@bla@Battle axe@bla@",1092);
 } else {
  sendQuest("@whi@Battle axe@whi@",1092);
 }
 if(playerLevel[13] < 39) {
  sendQuest("@bla@Warhammer@bla@",1083);
 } else {
  sendQuest("@whi@Warhammer@whi@",1083);
 }
 if(playerLevel[13] < 38) {
  sendQuest("@bla@Square shield@bla@",1104);
 } else {
  sendQuest("@whi@Square shield@whi@",1104);
 }
 if(playerLevel[13] < 37) {
  sendQuest("@bla@Full helm@bla@",1103);
  sendQuest("@bla@Throwing knives@bla@",1106);
 } else {
  sendQuest("@whi@Full helm@whi@",1103);
  sendQuest("@whi@Throwing knives@whi@",1106);
 }
 if(playerLevel[13] < 36) {
  sendQuest("@bla@Long sword@bla@",1086);
  sendQuest("@bla@Studs@bla@",1134);
 } else {
  sendQuest("@whi@Long sword@whi@",1086);
  sendQuest("@whi@Studs@whi@",1134);
 }
 if(playerLevel[13] < 35) {
  sendQuest("@bla@Scimitar@bla@",1087);
  sendQuest("@bla@Arrowtips@bla@",1108);
 } else {
  sendQuest("@whi@Scimitar@whi@",1087);
  sendQuest("@whi@Arrowtips@whi@",1108);
 }
 if(playerLevel[13] < 34) {
  sendQuest("@bla@Sword@bla@",1085);
  sendQuest("@bla@Dart tips@bla@",1107);
  sendQuest("@bla@Nails@bla@",13358);
 } else {
  sendQuest("@whi@Sword@whi@",1085);
  sendQuest("@whi@Dart tips@whi@",1107);
  sendQuest("@whi@Nails@whi@",13358);
 }
 if(playerLevel[13] < 33) {
  sendQuest("@bla@Medium helm@bla@",1102);
 } else {
  sendQuest("@whi@Medium helm@whi@",1102);
 }
 if(playerLevel[13] < 32) {
  sendQuest("@bla@Mace@bla@",1093);
 } else {
  sendQuest("@whi@Mace@whi@",1093);
 }
 if(playerLevel[13] < 31) {
  sendQuest("@bla@Axe@bla@",1091);
 } else {
  sendQuest("@whi@Axe@whi@",1091);
 }
 if(playerLevel[13] < 30) {
  sendQuest("@bla@Dagger@bla@",1094);
 } else {
  sendQuest("@whi@Dagger@whi@",1094);
 }
 addItemToSmith(1207,0,1119,1);
 addItemToSmith(1353,0,1120,1);
 addItemToSmith(1105,0,1121,1);
 addItemToSmith(1141,0,1122,1);
 addItemToSmith(821,0,1123,10);
 addItemToSmith(1281,1,1119,1);
 addItemToSmith(1424,1,1120,1);
 addItemToSmith(1069,1,1121,1);
 addItemToSmith(1157,1,1122,1);
 addItemToSmith(41,1,1123,15);
 addItemToSmith(1325,2,1119,1);
 addItemToSmith(1339,2,1120,1);
 addItemToSmith(1083,2,1121,1);
 addItemToSmith(1177,2,1122,1);
 addItemToSmith(865,2,1123,5);
 addItemToSmith(1295,3,1119,1);
 addItemToSmith(1365,3,1120,1);
 addItemToSmith(1119,3,1121,1);
 addItemToSmith(1193,3,1122,1);
 sendQuest("",1132);
 sendQuest("",1096);
 addItemToSmith(1311,4,1119,1);
 addItemToSmith(3097,4,1120,1);
 addItemToSmith(4544,4,1121,1);
 addItemToSmith(1539,4,1122,1);
 addItemToSmith(2370,4,1123,1);
 if(amountOfItem(barType) < 1) {
  sendQuest("@red@1bar@red@",1135);
 } else {
  sendQuest("@gre@1bar@gre@",1135);
 }
}
if(barType == 2359) { //Mith
 if(playerLevel[13] < 68) {
  sendQuest("@bla@Plate body@bla@",1101);
 } else {
  sendQuest("@whi@Plate body@whi@",1101);
 }
 if(playerLevel[13] < 66) {
  sendQuest("@bla@Plate legs@bla@",1099);
  sendQuest("@bla@Plate skirt@bla@",1100);
 } else {
  sendQuest("@whi@Plate legs@whi@",1099);
  sendQuest("@whi@Plate skirt@whi@",1100);
 }
 if(playerLevel[13] < 64) {
  sendQuest("@bla@2 hand sword@bla@",1088);
 } else {
  sendQuest("@whi@2 hand sword@whi@",1088);
 }
 if(playerLevel[13] < 63) {
  sendQuest("@bla@Claws@bla@",8429);
 } else {
  sendQuest("@whi@Claws@whi@",8429);
 }
 if(playerLevel[13] < 62) {
  sendQuest("@bla@Kite shield@bla@",1105);
 } else {
  sendQuest("@whi@Kite shield@whi@",1105);
 }
 if(playerLevel[13] < 61) {
  sendQuest("@bla@Chain body@bla@",1098);
 } else {
  sendQuest("@whi@Chain body@whi@",1098);
 }
 if(playerLevel[13] < 60) {
  sendQuest("@bla@Battle axe@bla@",1092);
 } else {
  sendQuest("@whi@Battle axe@whi@",1092);
 }
 if(playerLevel[13] < 59) {
  sendQuest("@bla@Warhammer@bla@",1083);
 } else {
  sendQuest("@whi@Warhammer@whi@",1083);
 }
 if(playerLevel[13] < 58) {
  sendQuest("@bla@Square shield@bla@",1104);
 } else {
  sendQuest("@whi@Square shield@whi@",1104);
 }
 if(playerLevel[13] < 57) {
  sendQuest("@bla@Full helm@bla@",1103);
  sendQuest("@bla@Throwing knives@bla@",1106);
 } else {
  sendQuest("@whi@Full helm@whi@",1103);
  sendQuest("@whi@Throwing knives@whi@",1106);
 }
 if(playerLevel[13] < 56) {
  sendQuest("@bla@Long sword@bla@",1086);
 } else {
  sendQuest("@whi@Long sword@whi@",1086);
 }
 if(playerLevel[13] < 55) {
  sendQuest("@bla@Scimitar@bla@",1087);
  sendQuest("@bla@Arrowtips@bla@",1108);
 } else {
  sendQuest("@whi@Scimitar@whi@",1087);
  sendQuest("@whi@Arrowtips@whi@",1108);
 }
 if(playerLevel[13] < 54) {
  sendQuest("@bla@Sword@bla@",1085);
  sendQuest("@bla@Dart tips@bla@",1107);
  sendQuest("@bla@Nails@bla@",13358);
 } else {
  sendQuest("@whi@Sword@whi@",1085);
  sendQuest("@whi@Dart tips@whi@",1107);
  sendQuest("@whi@Nails@whi@",13358);
 }
 if(playerLevel[13] < 53) {
  sendQuest("@bla@Medium helm@bla@",1102);
 } else {
  sendQuest("@whi@Medium helm@whi@",1102);
 }
 if(playerLevel[13] < 52) {
  sendQuest("@bla@Mace@bla@",1093);
 } else {
  sendQuest("@whi@Mace@whi@",1093);
 }
 if(playerLevel[13] < 51) {
  sendQuest("@bla@Axe@bla@",1091);
 } else {
  sendQuest("@whi@Axe@whi@",1091);
 }
 if(playerLevel[13] < 50) {
  sendQuest("@bla@Dagger@bla@",1094);
 } else {
  sendQuest("@whi@Dagger@whi@",1094);
 }
 addItemToSmith(1209,0,1119,1);
 addItemToSmith(1355,0,1120,1);
 addItemToSmith(1109,0,1121,1);
 addItemToSmith(1143,0,1122,1);
 addItemToSmith(822,0,1123,10);
 addItemToSmith(1285,1,1119,1);
 addItemToSmith(1355,1,1120,1);
 addItemToSmith(1071,1,1121,1);
 addItemToSmith(1159,1,1122,1);
 addItemToSmith(42,1,1123,15);
 addItemToSmith(1329,2,1119,1);
 addItemToSmith(1343,2,1120,1);
 addItemToSmith(1085,2,1121,1);
 addItemToSmith(1181,2,1122,1);
 addItemToSmith(866,2,1123,5);
 addItemToSmith(1299,3,1119,1);
 addItemToSmith(1369,3,1120,1);
 addItemToSmith(1121,3,1121,1);
 addItemToSmith(1197,3,1122,1);
 //addItemToSmith(0,3,1123);
 sendQuest("",1132);
 sendQuest("",1096);
 addItemToSmith(1315,4,1119,1);
 addItemToSmith(3099,4,1120,1);
 //addItemToSmith(4540,4,1121,1);
 addItemToSmith(4822,4,1122,1);
 //addItemToSmith(1307,4,1123);
 sendQuest("",1135);
 sendQuest("",1134);
 sendQuest("",11461);
 sendQuest("",11459);
}
if(barType == 2361) { //Addy
 if(playerLevel[13] < 88) {
  sendQuest("@bla@Plate body@bla@",1101);
 } else {
  sendQuest("@whi@Plate body@whi@",1101);
 }
 if(playerLevel[13] < 86) {
  sendQuest("@bla@Plate legs@bla@",1099);
  sendQuest("@bla@Plate skirt@bla@",1100);
 } else {
  sendQuest("@whi@Plate legs@whi@",1099);
  sendQuest("@whi@Plate skirt@whi@",1100);
 }
 if(playerLevel[13] < 84) {
  sendQuest("@bla@2 hand sword@bla@",1088);
 } else {
  sendQuest("@whi@2 hand sword@whi@",1088);
 }
 if(playerLevel[13] < 83) {
  sendQuest("@bla@Claws@bla@",8429);
 } else {
  sendQuest("@whi@Claws@whi@",8429);
 }
 if(playerLevel[13] < 82) {
  sendQuest("@bla@Kite shield@bla@",1105);
 } else {
  sendQuest("@whi@Kite shield@whi@",1105);
 }
 if(playerLevel[13] < 81) {
  sendQuest("@bla@Chain body@bla@",1098);
 } else {
  sendQuest("@whi@Chain body@whi@",1098);
 }
 if(playerLevel[13] < 80) {
  sendQuest("@bla@Battle axe@bla@",1092);
 } else {
  sendQuest("@whi@Battle axe@whi@",1092);
 }
 if(playerLevel[13] < 79) {
  sendQuest("@bla@Warhammer@bla@",1083);
 } else {
  sendQuest("@whi@Warhammer@whi@",1083);
 }
 if(playerLevel[13] < 78) {
  sendQuest("@bla@Square shield@bla@",1104);
 } else {
  sendQuest("@whi@Square shield@whi@",1104);
 }
 if(playerLevel[13] < 77) {
  sendQuest("@bla@Full helm@bla@",1103);
  sendQuest("@bla@Throwing knives@bla@",1106);
 } else {
  sendQuest("@whi@Full helm@whi@",1103);
  sendQuest("@whi@Throwing knives@whi@",1106);
 }
 if(playerLevel[13] < 76) {
  sendQuest("@bla@Long sword@bla@",1086);
 } else {
  sendQuest("@whi@Long sword@whi@",1086);
 }
 if(playerLevel[13] < 75) {
  sendQuest("@bla@Scimitar@bla@",1087);
  sendQuest("@bla@Arrowtips@bla@",1108);
 } else {
  sendQuest("@whi@Scimitar@whi@",1087);
  sendQuest("@whi@Arrowtips@whi@",1108);
 }
 if(playerLevel[13] < 74) {
  sendQuest("@bla@Sword@bla@",1085);
  sendQuest("@bla@Dart tips@bla@",1107);
  sendQuest("@bla@Nails@bla@",13358);
 } else {
  sendQuest("@whi@Sword@whi@",1085);
  sendQuest("@whi@Dart tips@whi@",1107);
  sendQuest("@whi@Nails@whi@",13358);
 }
 if(playerLevel[13] < 73) {
  sendQuest("@bla@Medium helm@bla@",1102);
 } else {
  sendQuest("@whi@Medium helm@whi@",1102);
 }
 if(playerLevel[13] < 72) {
  sendQuest("@bla@Mace@bla@",1093);
 } else {
  sendQuest("@whi@Mace@whi@",1093);
 }
 if(playerLevel[13] < 71) {
  sendQuest("@bla@Axe@bla@",1091);
 } else {
  sendQuest("@whi@Axe@whi@",1091);
 }
 if(playerLevel[13] < 70) {
  sendQuest("@bla@Dagger@bla@",1094);
 } else {
  sendQuest("@whi@Dagger@whi@",1094);
 }
 addItemToSmith(1211,0,1119,1);
 addItemToSmith(1357,0,1120,1);
 addItemToSmith(1111,0,1121,1);
 addItemToSmith(1145,0,1122,1);
 addItemToSmith(823,0,1123,10);
 addItemToSmith(1287,1,1119,1);
 addItemToSmith(1430,1,1120,1);
 addItemToSmith(1073,1,1121,1);
 addItemToSmith(1161,1,1122,1);
 addItemToSmith(43,1,1123,15);
 addItemToSmith(1331,2,1119,1);
 addItemToSmith(1345,2,1120,1);
 addItemToSmith(1091,2,1121,1);
 addItemToSmith(1183,2,1122,1);
 addItemToSmith(867,2,1123,5);
 addItemToSmith(1301,3,1119,1);
 addItemToSmith(1371,3,1120,1);
 addItemToSmith(1123,3,1121,1);
 addItemToSmith(1199,3,1122,1);
 //addItemToSmith(0,3,1123);
 sendQuest("",1132);
 sendQuest("",1096);
 addItemToSmith(1317,4,1119,1);
 addItemToSmith(3100,4,1120,1);
 //addItemToSmith(4540,4,1121,1);
 addItemToSmith(4823,4,1122,1);
 //addItemToSmith(1307,4,1123);
 sendQuest("",1135);
 sendQuest("",1134);
 sendQuest("",11461);
 sendQuest("",11459);
}
if(barType == 2363) { //Rune
 if(playerLevel[13] < 99) {
  sendQuest("@bla@Plate body@bla@",1101);
  sendQuest("@bla@Plate legs@bla@",1099);
  sendQuest("@bla@Plate skirt@bla@",1100);
  sendQuest("@bla@2 hand sword@bla@",1088);
 } else {
  sendQuest("@whi@Plate body@whi@",1101);
  sendQuest("@whi@Plate legs@whi@",1099);
  sendQuest("@whi@Plate skirt@whi@",1100);
  sendQuest("@whi@2 hand sword@whi@",1088);
 }
 if(playerLevel[13] < 98) {
  sendQuest("@bla@Claws@bla@",8429);
 } else {
  sendQuest("@whi@Claws@whi@",8429);
 }
 if(playerLevel[13] < 97) {
  sendQuest("@bla@Kite shield@bla@",1105);
 } else {
  sendQuest("@whi@Kite shield@whi@",1105);
 }
 if(playerLevel[13] < 96) {
  sendQuest("@bla@Chain body@bla@",1098);
 } else {
  sendQuest("@whi@Chain body@whi@",1098);
 }
 if(playerLevel[13] < 95) {
  sendQuest("@bla@Battle axe@bla@",1092);
 } else {
  sendQuest("@whi@Battle axe@whi@",1092);
 }
 if(playerLevel[13] < 94) {
  sendQuest("@bla@Warhammer@bla@",1083);
 } else {
  sendQuest("@whi@Warhammer@whi@",1083);
 }
 if(playerLevel[13] < 93) {
  sendQuest("@bla@Square shield@bla@",1104);
 } else {
  sendQuest("@whi@Square shield@whi@",1104);
 }
 if(playerLevel[13] < 92) {
  sendQuest("@bla@Full helm@bla@",1103);
  sendQuest("@bla@Throwing knives@bla@",1106);
 } else {
  sendQuest("@whi@Full helm@whi@",1103);
  sendQuest("@whi@Throwing knives@whi@",1106);
 }
 if(playerLevel[13] < 91) {
  sendQuest("@bla@Long sword@bla@",1086);
 } else {
  sendQuest("@whi@Long sword@whi@",1086);
 }
 if(playerLevel[13] < 90) {
  sendQuest("@bla@Scimitar@bla@",1087);
  sendQuest("@bla@Arrowtips@bla@",1108);
 } else {
  sendQuest("@whi@Scimitar@whi@",1087);
  sendQuest("@whi@Arrowtips@whi@",1108);
 }
 if(playerLevel[13] < 89) {
  sendQuest("@bla@Sword@bla@",1085);
  sendQuest("@bla@Dart tips@bla@",1107);
  sendQuest("@bla@Nails@bla@",13358);
 } else {
  sendQuest("@whi@Sword@whi@",1085);
  sendQuest("@whi@Dart tips@whi@",1107);
  sendQuest("@whi@Nails@whi@",13358);
 }
 if(playerLevel[13] < 88) {
  sendQuest("@bla@Medium helm@bla@",1102);
 } else {
  sendQuest("@whi@Medium helm@whi@",1102);
 }
 if(playerLevel[13] < 87) {
  sendQuest("@bla@Mace@bla@",1093);
 } else {
  sendQuest("@whi@Mace@whi@",1093);
 }
 if(playerLevel[13] < 86) {
  sendQuest("@bla@Axe@bla@",1091);
 } else {
  sendQuest("@whi@Axe@whi@",1091);
 }
 if(playerLevel[13] < 85) {
  sendQuest("@bla@Dagger@bla@",1094);
 } else {
  sendQuest("@whi@Dagger@whi@",1094);
 }
 addItemToSmith(1213,0,1119,1);
 addItemToSmith(1359,0,1120,1);
 addItemToSmith(1113,0,1121,1);
 addItemToSmith(1147,0,1122,1);
 addItemToSmith(824,0,1123,10);
 addItemToSmith(1289,1,1119,1);
 addItemToSmith(1432,1,1120,1);
 addItemToSmith(1079,1,1121,1);
 addItemToSmith(1163,1,1122,1);
 addItemToSmith(44,1,1123,15);
 addItemToSmith(1333,2,1119,1);
 addItemToSmith(1347,2,1120,1);
 addItemToSmith(1093,2,1121,1);
 addItemToSmith(1185,2,1122,1);
 addItemToSmith(868,2,1123,5);
 addItemToSmith(1303,3,1119,1);
 addItemToSmith(1373,3,1120,1);
 addItemToSmith(1127,3,1121,1);
 addItemToSmith(1201,3,1122,1);
 //addItemToSmith(0,3,1123);
 sendQuest("",1132);
 sendQuest("",1096);
 addItemToSmith(1319,4,1119,1);
 addItemToSmith(3101,4,1120,1);
 //addItemToSmith(4540,4,1121,1);
 addItemToSmith(4824,4,1122,1);
 //addItemToSmith(1307,4,1123);
 sendQuest("",1135);
 sendQuest("",1134);
 sendQuest("",11461);
 sendQuest("",11459);
}
}

/*END OF SMITHING*/

public void bars2ores()
{
if(playerLevel[6] >= 65)
{
sendMessage("You turn the bars into ores...");

if (playerHasItemAmount(2349, 1)==true) // bronze
{
deleteItem(2349, GetItemSlot(2349), 1);
addItem(436, 1);
}
if (playerHasItemAmount(2351, 1)==true) // iron
{
deleteItem(2351, GetItemSlot(2351), 1);
addItem(440, 1);
}
if (playerHasItemAmount(2353, 1)==true) // steel
{
deleteItem(2353, GetItemSlot(2353), 1);
addItem(453, 1);
}
if (playerHasItemAmount(2355, 1)==true) // silver
{
deleteItem(2355, GetItemSlot(2355), 1);
addItem(442, 1);
}
if (playerHasItemAmount(2357, 1)==true) // gold
{
deleteItem(2357, GetItemSlot(2357), 1);
addItem(444, 1);
}
if (playerHasItemAmount(2359, 1)==true) // mithril
{
deleteItem(2359, GetItemSlot(2359), 1);
addItem(447, 1);
}
if (playerHasItemAmount(2361, 1)==true) // adamant
{
deleteItem(2361, GetItemSlot(2361), 1);
addItem(449, 1);
}
if (playerHasItemAmount(2363, 1)==true) // rune
{
deleteItem(2363, GetItemSlot(2363), 1);
addItem(451, 1);
}
else if ((playerHasItemAmount(2363, 1)==false) && (playerHasItemAmount(2361, 1)==false) && (playerHasItemAmount(2359, 1)==false) && (playerHasItemAmount(2357, 1)==false) && (playerHasItemAmount(2355, 1)==false) && (playerHasItemAmount(2353, 1)==false) && (playerHasItemAmount(2351, 1)==false) && (playerHasItemAmount(2349, 1)==false))
{
sendMessage("...but you have no bars to convert");
}
}
else
{
sendMessage("You need a magic level of 65 to cast that spell.");
}
}

/*QUEST VOIDS AND INTS*/
public int questid = 0;
public int q1stage = 0;
public int q2stage = 0;
public int q3stage = 0;
public int totalqp = 0;

public void quest()
{
/* QUEST 1 */
if(questid == 1)
{
if(q1stage == 0)
{
loadquest("Invisible Armour", "To start this quest talk with "+GetNpcName(549), "who can be found in Varrock Armour", "shop, good luck.", "", "", "", "", "", "");
}
if(q1stage == 1)
{
loadquest("Invisible Armour", GetNpcName(549)+" has asked me to collect the resources", "needed to make the armour", "These include:", GetItemName(451), GetItemName(2339), GetItemName(1777), "", "", "");
}
if(q1stage == 2)
{
loadquest("Invisible Armour", "I now need to collect the "+GetItemName(6889), "so that "+GetNpcName(549)+" can make the armour.", "", "", "", "", "", "", "");
}
if(q1stage == 3)
{
loadquest("Invisible Armour", "All "+GetNpcName(549)+" needs now is", "a "+GetItemName(4206)+" to add the power to the armour.", "The crystal can be found in the caves", "of Relleka, North of Camelot.", "But be careful, as there is many dangerous", "creatures there...", "", "", "");
}
if(q1stage == 4)
{
loadquest("Invisible Armour", "You have the "+GetItemName(4206)+".", "Return to Horvik for your reward!", "", "", "", "", "", "", "");
}
if(q1stage == -1)
{
loadquest("Invisible Armour", "QUEST COMPLETE!", "", "", "", "", "", "", "", "");
}
}
else if(questid == 2)
{
if(q2stage == 0)
{
loadquest("Cook's Assistant", "To start this quest talk with the "+GetNpcName(278), "who can be found in Lumbridge castle", "good luck.", "", "", "", "", "", "");
}
if(q2stage == 1)
{
loadquest("Cook's Assistant", GetNpcName(278)+" has asked me to collect the following", "ingredients for his cake:", GetItemName(1933), GetItemName(1944), GetItemName(1927), "", "", "", "");
}
if(q2stage == -1)
{
loadquest("Cook's Assistant", "QUEST COMPLETED!", "", "", "", "", "", "", "", "");
}
}
}

public void showQuestCompleted(String questName, int rewardqp) {
                totalqp += rewardqp;
		showInterface(297);
		sendQuest("Congratulations!", 299);
		sendQuest("Close Window", 300);
		sendQuest("You are awarded", 6156);
		sendQuest("Earned QP:", 6158);
		sendQuest("Total QP:", 303);
		sendQuest("You have completed "+questName, 301);
		sendQuest(""+rewardqp, 4444);
		sendQuest(""+totalqp, 304);
	}

public void loadquest(String questname, String questinfo1, String questinfo2, String questinfo3, String questinfo4, String questinfo5, String questinfo6, String questinfo7, String questinfo8, String questinfo9)
{
sendQuest("@dre@Quest", 8144); 
clearQuestInterface();
sendQuest("@dbl@"+questname, 8145);
sendQuest("@dbl@@dre@"+questinfo1+"@dbl@", 8147);
sendQuest("@dbl@@dre@"+questinfo2+"@dbl@", 8148);
sendQuest("@dbl@@dre@"+questinfo3+"@dbl@", 8149);
sendQuest("@dbl@@dre@"+questinfo4+"@dbl@", 8150);
sendQuest("@dbl@@dre@"+questinfo5+"@dbl@", 8151);
sendQuest("@dbl@@dre@"+questinfo6+"@dbl@", 8152);
sendQuest("@dbl@@dre@"+questinfo7+"@dbl@", 8153);
sendQuest("@dbl@@dre@"+questinfo8+"@dbl@", 8154);
sendQuest("@dbl@@dre@"+questinfo9+"@dbl@", 8155);
sendQuestSomething(8143);
showInterface(8134);
flushOutStream();		
}
/*END OF QUEST 1*/
/*CLUE VOIDS AND OTHER STUFF*/
// CLUE_00
public static int clue[] = {1,2,3,4,5};

public static int randomClue()
{
return clue[(int)(Math.random()*clue.length)];
}

/*public void additemtoclue(int itemID, int slot, int amount)
{
sendQuest("@dre@Clue Scroll", 8144); 
clearQuestInterface();
sendQuest("@dbl@Congratz, you have completed the treasure trail!", 8145);
outStream.createFrameVarSizeWord(34);
outStream.writeWord(8134); // shops
outStream.writeByte(slot); // slot
outStream.writeWord(itemID); // item
outStream.endFrameVarSizeWord();
}*/

//public boolean isdoingclue(int cluelevel, int cluestage, int clueid) = false;

public void cluereward(int item1, int item2, int item3, int item4, int item5, int amount1, int amount2, int amount3, int amount4, int amount5)
{
sendQuest("@dre@Clue Scroll", 8144); 
clearQuestInterface();
sendQuest("@dbl@Congratz, you have completed the treasure trail!", 8145);
sendQuest("@dbl@Reward:", 8146);
sendQuest("@dbl@"+GetItemName(item1)+"@dre@ ("+amount1+")@dbl@", 8147);
sendQuest("@dbl@"+GetItemName(item2)+"@dre@ ("+amount2+")@dbl@", 8148);
sendQuest("@dbl@"+GetItemName(item3)+"@dre@ ("+amount3+")@dbl@", 8149);
sendQuest("@dbl@"+GetItemName(item4)+"@dre@ ("+amount4+")@dbl@", 8150);
sendQuest("@dbl@"+GetItemName(item5)+"@dre@ ("+amount5+")@dbl@", 8151);
addItem(item1, amount1);
addItem(item2, amount2);
addItem(item3, amount3);
addItem(item4, amount4);
addItem(item5, amount5);
sendQuestSomething(8143);
showInterface(8134);
flushOutStream();		
}

public void sendclue1(int clueX, int clueY)
{
sendQuest("@dre@Clue Scroll", 8144); 
clearQuestInterface();
sendQuest("@dbl@Coordinates of next clue", 8145);
sendQuest("@dbl@X Coordinate:@dre@ "+clueX+"@dbl@", 8147);
sendQuest("@dbl@Y Coordinate:@dre@ "+clueY+"@dbl@", 8148);
sendQuest("@dbl@@dre@ GOOD LUCK! @dbl@", 8149);
sendQuestSomething(8143);
showInterface(8134);
flushOutStream();		
}

public void sendclue2(int npc)
{
sendQuest("@dre@Clue Scroll", 8144); 
clearQuestInterface();
sendQuest("@dbl@You next clue lies ahead...", 8145);
sendQuest("@dbl@Talk to@dre@ "+GetNpcName(npc)+"@dbl@ for more information", 8147);
sendQuest("@dbl@@dre@ GOOD LUCK! @dbl@", 8148);
sendQuestSomething(8143);
showInterface(8134);
flushOutStream();		
}

public void sendclue3(String clueinfo1, String clueinfo2)
{
sendQuest("@dre@Clue Scroll", 8144); 
clearQuestInterface();
sendQuest("@dbl@Coordinates of next clue", 8145);
sendQuest("@dbl@@dre@"+clueinfo1+"@dbl@", 8147);
sendQuest("@dbl@@dre@"+clueinfo2+"@dbl@", 8148);
sendQuest("@dbl@@dre@ GOOD LUCK! @dbl@", 8149);
sendQuestSomething(8143);
showInterface(8134);
flushOutStream();		
}

public void newclue(int currentcluestage)
{
clueid = randomClue(); 
cluestage += 1;
deleteItem(2681, GetItemSlot(2681), 1);
deleteItem(2682, GetItemSlot(2682), 1);
deleteItem(2683, GetItemSlot(2683), 1);
savemoreinfo();
}

public void givereward(int cluelevel)
{
if (cluelevel == 1)
{
cluereward(Clues.randomClue1(), Clues.randomClue1(), Clues.randomNonClue1(), Clues.randomNonClue1(), Clues.randomRunes1(), 1, 1, 1, 1, 500);
deleteItem(2681, GetItemSlot(2681), 1);
cluelevel = 0;
cluestage = 0;
clueid = 0;
savemoreinfo();
}
if (cluelevel == 2)
{
cluereward(Clues.randomClue2(), Clues.randomClue2(), Clues.randomNonClue2(), Clues.randomNonClue2(), Clues.randomRunes2(), 1, 1, 1, 1, 500);
deleteItem(2682, GetItemSlot(2682), 1);
cluelevel = 0;
cluestage = 0;
clueid = 0;
savemoreinfo();
}
if (cluelevel == 3)
{
cluereward(Clues.randomClue3(), Clues.randomClue3(), Clues.randomNonClue3(), Clues.randomNonClue3(), Clues.randomRunes3(), 1, 1, 1, 1, 500);
deleteItem(2683, GetItemSlot(2683), 1);
cluelevel = 0;
cluestage = 0;
clueid = 0;
savemoreinfo();
}
}

// CLUE_01
public void clearclue(int cluestage)
{
clueid = clueid;
}

public void dig()
{
sendMessage("Dig working - cheezy");
if (cluelevel == 1) // LEVEL 1 CLUES
{
sendMessage("Clue level 1 found.");
if (cluestage == 1) /////////////////////////////// 
{
sendMessage("Clue stage 1 found.");
if ((clueid == 2) && (absX == 3211) && (absY == 3688))
{
sendMessage("You find another clue!");
addItem(2681, 1);
newclue(1);
}
if ((clueid == 3) && (absX == 3110) && (absY == 3295))
{
sendMessage("You find another clue!");
addItem(2681, 1);
newclue(1);
}
if ((clueid == 5) && (absX == 2684) && (absY == 3286))
{
sendMessage("You find another clue!");
addItem(2681, 1);
newclue(1);
}
}
if (cluestage == 2) /////////////////////////////// 
{
sendMessage("Clue stage 2 found.");
if ((clueid == 1) && (absX == 3191) && (absY == 3363))
{
sendMessage("You find another clue!");
addItem(2681, 1);
newclue(2);
}
if ((clueid == 2) && (absX == 2947) && (absY == 3450))
{
sendMessage("You find another clue!");
addItem(2681, 1);
newclue(2);
}
if ((clueid == 5) && (absX == 3008) && (absY == 3889))
{
sendMessage("You find another clue!");
addItem(2681, 1);
newclue(2);
}
}
if (cluestage == 3) /////////////////////////////// 
{
if ((clueid == 1) && (absX == 2424) && (absY == 3081))
{
sendMessage("You find another clue!");
addItem(2681, 1);
newclue(3);
}
if ((clueid == 2) && (absX == 2955) && (absY == 3381))
{
sendMessage("You find another clue!");
addItem(2681, 1);
newclue(3);
}
if ((clueid == 3) && (absX == 3008) && (absY == 3889))
{
sendMessage("You find another clue!");
addItem(2681, 1);
newclue(3);
if ((clueid == 4) && (absX == 2658) && (absY == 3338))
{
sendMessage("You find another clue!");
addItem(2681, 1);
newclue(3);
}
if ((clueid == 5) && (absX == 3226) && (absY == 3368))
{
sendMessage("You find another clue!");
addItem(2681, 1);
newclue(3);
}
}
if (cluestage == 4) /////////////////////////////// 
{
sendMessage("Clue stage 4 found.");
if ((clueid == 3) && (absX == 3235) && (absY == 3294))
{
sendMessage("You find another clue!");
addItem(2681, 1);
newclue(4);
}
if ((clueid == 4) && (absX == 3258) && (absY == 3243))
{
sendMessage("You find another clue!");
addItem(2681, 1);
newclue(4);
}
if ((clueid == 5) && (absX == 3225) && (absY == 3218))
{
sendMessage("You find another clue!");
addItem(2681, 1);
newclue(4);
}
}
if (cluestage == 5) /////////////////////////////// 
{
if ((clueid == 1) && (absX == 3225) && (absY == 3218))
{
sendMessage("You have completed the treasure trail!");
deleteItem(2681,getItemSlot(2681),1);
givereward(1);
}
if ((clueid == 3) && (absX == 3141) && (absY == 3425))
{
sendMessage("You have completed the treasure trail!");
deleteItem(2681,getItemSlot(2681),1);
givereward(1);
}
if ((clueid == 4) && (absX == 3098) && (absY == 3405))
{
sendMessage("You have completed the treasure trail!");
deleteItem(2681,getItemSlot(2681),1);
givereward(1);
}
if ((clueid == 5) && (absX == 3113) && (absY == 3961))
{
sendMessage("You have completed the treasure trail!");
deleteItem(2681,getItemSlot(2681),1);
givereward(1);
}
}
}
else if (cluelevel == 2) // LEVEL 2 CLUES
{
sendMessage("Clue level 2 found.");
if (cluestage == 1) /////////////////////////////// 
{
if ((clueid == 2) && (absX == 3211) && (absY == 3688))
{
sendMessage("You find another clue!");
addItem(2682, 1);
newclue(1);
}
if ((clueid == 3) && (absX == 3157) && (absY == 3961))
{
sendMessage("You find another clue!");
addItem(2682, 1);
newclue(1);
}
if ((clueid == 4) && (absX == 2839) && (absY == 3596))
{
sendMessage("You find another clue!");
addItem(2682, 1);
newclue(1);
}
}
if (cluestage == 2) /////////////////////////////// 
{
if ((clueid == 2) && (absX == 3288) && (absY == 3886))
{
sendMessage("You find another clue!");
addItem(2682, 1);
newclue(2);
}
if ((clueid == 3) && (absX == 2884) && (absY == 3160))
{
sendMessage("You find another clue!");
addItem(2682, 1);
newclue(2);
}
if ((clueid == 4) && (absX == 2802) && (absY == 2976))
{
sendMessage("You find another clue!");
addItem(2682, 1);
newclue(2);
}
if ((clueid == 5) && (absX == 2839) && (absY == 3596))
{
sendMessage("You find another clue!");
addItem(2682, 1);
newclue(2);
}
}
if (cluestage == 3) /////////////////////////////// 
{
if ((clueid == 1) && (absX == 2599) && (absY == 3176))
{
sendMessage("You find another clue!");
addItem(2682, 1);
newclue(3);
}
if ((clueid == 2) && (absX == 2619) && (absY == 3499))
{
sendMessage("You find another clue!");
addItem(2682, 1);
newclue(3);
}
if ((clueid == 3) && (absX == 2601) && (absY == 3490))
{
sendMessage("You find another clue!");
addItem(2682, 1);
newclue(3);
if ((clueid == 4) && (absX == 2757) && (absY == 3477))
{
sendMessage("You find another clue!");
addItem(2682, 1);
newclue(3);
}
if ((clueid == 5) && (absX == 2987) && (absY == 3388))
{
sendMessage("You find another clue!");
addItem(2682, 1);
newclue(3);
}
}
if (cluestage == 4) /////////////////////////////// 
{
if ((clueid == 1) && (absX == 3058) && (absY == 3353))
{
sendMessage("You find another clue!");
addItem(2682, 1);
newclue(4);
}
if ((clueid == 3) && (absX == 3288) && (absY == 3465))
{
sendMessage("You find another clue!");
addItem(2682, 1);
newclue(4);
}
if ((clueid == 4) && (absX == 3314) && (absY == 3719))
{
sendMessage("You find another clue!");
addItem(2682, 1);
newclue(4);
}
if ((clueid == 5) && (absX == 3311) && (absY == 3768))
{
sendMessage("You find another clue!");
addItem(2682, 1);
newclue(4);
}
}
if (cluestage == 5) /////////////////////////////// 
{
if ((clueid == 1) && (absX == 3203) && (absY == 3424))
{
sendMessage("You find another clue!");
deleteItem(2682,getItemSlot(2682),1);
givereward(2);
}
if ((clueid == 3) && (absX == 3113) && (absY == 3961))
{
sendMessage("You find another clue!");
deleteItem(2682,getItemSlot(2682),1);
givereward(2);
}
if ((clueid == 4) && (absX == 3225) && (absY == 3218))
{
sendMessage("You find another clue!");
deleteItem(2682,getItemSlot(2682),1);
givereward(2);
}
if ((clueid == 5) && (absX == 2424) && (absY == 3078))
{
sendMessage("You find another clue!");
deleteItem(2682,getItemSlot(2682),1);
givereward(2);
}
}
}
else if (cluelevel == 3) // LEVEL 3 CLUES
{
sendMessage("Clue level 3 found.");
if (cluestage == 1) /////////////////////////////// 
{
if ((clueid == 1) && (absX == 3022) && (absY == 3952))
{
sendMessage("You find another clue!");
addItem(2683, 1);
newclue(1);
}
if ((clueid == 2) && (absX == 3211) && (absY == 3688))
{
sendMessage("You find another clue!");
addItem(2683, 1);
newclue(1);
}
if ((clueid == 3) && (absX == 3211) && (absY == 3688))
{
sendMessage("You find another clue!");
addItem(2683, 1);
newclue(1);
}
if ((clueid == 4) && (absX == 2780) && (absY == 3515))
{
sendMessage("You find another clue!");
addItem(2683, 1);
newclue(1);
}
if ((clueid == 5) && (absX == 3191) && (absY == 3963))
{
sendMessage("You find another clue!");
addItem(2683, 1);
newclue(1);
}
}
if (cluestage == 2) /////////////////////////////// 
{
if ((clueid == 1) && (absX == 3280) && (absY == 3955))
{
sendMessage("You find another clue!");
addItem(2683, 1);
newclue(2);
}
if ((clueid == 2) && (absX == 3092) && (absY == 3963))
{
sendMessage("You find another clue!");
addItem(2683, 1);
newclue(2);
}
if ((clueid == 3) && (absX == 3065) && (absY == 3904))
{
sendMessage("You find another clue!");
addItem(2683, 1);
newclue(2);
}
if ((clueid == 4) && (absX == 3211) && (absY == 3688))
{
sendMessage("You find another clue!");
addItem(2683, 1);
newclue(2);
}
if ((clueid == 5) && (absX == 3047) && (absY == 10342))
{
sendMessage("You find another clue!");
addItem(2683, 1);
newclue(2);
}
}
if (cluestage == 3) /////////////////////////////// 
{
if ((clueid == 2) && (absX == 3211) && (absY == 3688))
{
sendMessage("You find another clue!");
addItem(2683, 1);
newclue(3);
}
if ((clueid == 3) && (absX == 2961) && (absY == 3251))
{
sendMessage("You find another clue!");
addItem(2683, 1);
newclue(3);
if ((clueid == 4) && (absX == 2988) && (absY == 3434))
{
sendMessage("You find another clue!");
addItem(2683, 1);
newclue(3);
}
if ((clueid == 5) && (absX == 3105) && (absY == 3959))
{
sendMessage("You find another clue!");
addItem(2683, 1);
newclue(3);
}
}
if (cluestage == 4) /////////////////////////////// 
{
if ((clueid == 1) && (absX == 3153) && (absY == 3923))
{
sendMessage("You find another clue!");
addItem(2683, 1);
newclue(4);
}
if ((clueid == 2) && (absX == 2569) && (absY == 3278))
{
sendMessage("You find another clue!");
addItem(2683, 1);
newclue(4);
}
if ((clueid == 3) && (absX == 2599) && (absY == 3271))
{
sendMessage("You find another clue!");
addItem(2683, 1);
newclue(4);
}
if ((clueid == 4) && (absX == 2958) && (absY == 3820))
{
sendMessage("You find another clue!");
addItem(2683, 1);
newclue(4);
}
if ((clueid == 5) && (absX == 2952) && (absY == 3790))
{
sendMessage("You find another clue!");
addItem(2683, 1);
newclue(4);
}
}
if (cluestage == 5) /////////////////////////////// 
{
if ((clueid == 1) && (absX == 2352) && (absY == 3294))
{
sendMessage("You find another clue!");
deleteItem(2683,getItemSlot(2683),1);
givereward(3);
}
if ((clueid == 2) && (absX == 3022) && (absY == 3952))
{
sendMessage("You find another clue!");
deleteItem(2683,getItemSlot(2683),1);
givereward(3);
}
if ((clueid == 3) && (absX == 2601) && (absY == 3490))
{
sendMessage("You find another clue!");
deleteItem(2683,getItemSlot(2683),1);
givereward(3);
}
if ((clueid == 4) && (absX == 2839) && (absY == 3596))
{
sendMessage("You find another clue!");
deleteItem(2683,getItemSlot(2683),1);
givereward(3);
}
}
}
}
}
}
}

// CLUE_02
public void clue()
{
if (cluelevel == 0) // new clue
{
newclue(0);
}
if (cluelevel == 1) // level 1 clue scrolls
{
if (cluestage == 1)///////////////////////////////////////////////////
{
if (clueid == 1)
{
sendclue2(549); // horvik
}
if (clueid == 2)
{
sendclue1(3211, 3688);
}
if (clueid == 3)
{
sendclue3("Head to the crossroad located", "south of the haunted house"); // coords: 3110,3295
}
if (clueid == 4)
{
sendclue2(527); // shop assistant in fally general store
}
if (clueid == 5)
{
sendclue1(2684, 3286);
}
}
if (cluestage == 2)////////////////////////////////////////////////
{
if (clueid == 1)
{
sendclue1(3191, 3363);
}
if (clueid == 2)
{
sendclue3("The old anvil would be a very", "good place to check..."); // coords: 2947,3450
}
if (clueid == 3)
{
sendclue3("Search the drawers of a house containing", "a sink in East Ardougne"); // coords: 2611,3323
}
if (clueid == 4)
{
sendclue3("Search the boxes in castle wars", "for your next clue."); // coords: 2424,3081
}
if (clueid == 5)
{
sendclue1(3008, 3889);
}
}
if (cluestage == 3) ////////////////////////////////////////////////
{
if (clueid == 1)
{
sendclue3("Your next clue lies where the ghost of", "Camelot lives..."); // coords: 2424,3081
}
if (clueid == 2)
{
sendclue1(2966, 3381);
}
if (clueid == 3)
{
sendclue1(3008, 3889);
}
if (clueid == 4)
{
sendclue1(2658, 3338);
}
if (clueid == 5)
{
sendclue3("A dolmen is always a good place to check", "when doing treasure trails."); // coords: 2424,3081
}
}
if (cluestage == 4) ////////////////////////////////////////////////
{
if (clueid == 1)
{
sendclue2(220); // fisherking
}
if (clueid == 2)
{
sendclue2(542); // Al-Kharid Legs Shopkeeper
}
if (clueid == 3)
{
sendclue1(3235, 3294);
}
if (clueid == 4)
{
sendclue1(3258, 3243);
}
if (clueid == 5)
{
sendclue3("Try and check outside of the", "castle in Lumbridge."); // coords: 3225,3218
}
}
if (cluestage == 5) ////////////////////////////////////////////////
{
if (clueid == 1)
{
sendclue3("Try and check outside of the", "castle in Lumbridge."); // coords: 3225,3218
}
if (clueid == 2)
{
sendclue2(2253); // Rich Old Guy
}
if (clueid == 3)
{
sendclue1(3141, 3425);
}
if (clueid == 4)
{
sendclue1(3098, 3405);
}
if (clueid == 5)
{
sendclue1(3113, 3961);
}
}
}
if (cluelevel == 2) // level 2 clue scrolls
{
if (cluestage == 1) ////////////////////////////////////////////////
{
if (clueid == 1)
{
sendclue2(903); // lundail
}
if (clueid == 2)
{
sendclue1(3211, 3688);
}
if (clueid == 3)
{
sendclue1(3157, 3961);
}
if (clueid == 4)
{
sendclue3("Read the sign up in the dangerous", "mountains..."); // coords: 2839,3596
}
if (clueid == 5)
{
sendclue2(542); // Al-Kharid Legs Shopkeeper
}
}
if (cluestage == 2) ////////////////////////////////////////////////
{
if (clueid == 1)
{
sendclue2(553); // Aubury
}
if (clueid == 2)
{
sendclue1(3288, 3886);
}
if (clueid == 3)
{
sendclue1(2884, 3160);
}
if (clueid == 4)
{
sendclue1(2802, 2976);
}
if (clueid == 5)
{
sendclue3("Search the crates at the south west", "of Karamja island, above the thick jungle."); // coords: 2839,3596
}
}
if (cluestage == 3) ////////////////////////////////////////////////
{
if (clueid == 1)
{
sendclue1(2599, 3176);
}
if (clueid == 2)
{
sendclue1(2619, 3499);
}
if (clueid == 3)
{
sendclue3("At the end of the track.", "......."); // coords: 2601,3490
}
if (clueid == 4)
{
sendclue1(2757, 3477);
}
if (clueid == 5)
{
sendclue3("The left flower patch", "contains 4 flowers and your next clue."); // coords: 2987,3388
}
}
if (cluestage == 4) ////////////////////////////////////////////////
{
if (clueid == 1)
{
sendclue1(3058, 3353);
}
if (clueid == 2)
{
sendclue2(548); // Thessalia
}
if (clueid == 3)
{
sendclue1(3288, 3465);
}
if (clueid == 4)
{
sendclue1(3314, 3719);
}
if (clueid == 5)
{
sendclue3("The abandoned outpost near the poisonous", "spiders holds your final clue."); // coords: 3311,3768
}
}
if (cluestage == 5) ////////////////////////////////////////////////
{
if (clueid == 1)
{
sendclue3("The gypsy tent is the location of your", "final reward."); // coords: 3203,3424
}
if (clueid == 2)
{
sendclue2(542); // Al-Kharid Legs Shopkeeper
}
if (clueid == 3)
{
sendclue1(3113, 3961);
}
if (clueid == 4)
{
sendclue3("A dolmen is always a good place to check", "when doing treasure trails."); // coords: 2424,3081
}
if (clueid == 5)
{
sendclue3("The table full of bandages contains the", "final reward."); // coords: 2424,3078
}
}
}
if (cluelevel == 3) // level 3 clue scrolls
{
if (cluestage == 1) ////////////////////////////////////////////////
{
if (clueid == 1)
{
sendclue3("Go to the skeletons at the dead ship.", "!!!..........!!!"); // coords: 3022,3952
}
if (clueid == 2)
{
sendclue1(3211, 3688);
}
if (clueid == 3)
{
sendclue1(3211, 3688);
}
if (clueid == 4)
{
sendclue3("Your next clue lies where the ghost of", "Camelot lives..."); // coords: 2780,3515
}
if (clueid == 5)
{
sendclue3("The hut that lies in the wilderness", "is your next destination"); // coords: 3191,3963
}
}
if (cluestage == 2) ////////////////////////////////////////////////
{
if (clueid == 1)
{
sendclue3("The pillars of stone in the north eastern coast", "is a good place to go."); // coords: 3280,3955
}
if (clueid == 2)
{
sendclue1(3092, 3963);
}
if (clueid == 3)
{
sendclue3("Just south of the lava snake lies", "your next clue..."); // coords: 3065,3904
}
if (clueid == 4)
{
sendclue1(3211, 3688);
}
if (clueid == 5)
{
sendclue1(3047, 10342);
}
} 
if (cluestage == 3) ////////////////////////////////////////////////
{
if (clueid == 1)
{
sendclue2(546); // Zaff
}
if (clueid == 2)
{
sendclue1(3211, 3688);
}
if (clueid == 3)
{
sendclue1(2961, 3251);
}
if (clueid == 4)
{
sendclue1(2988, 3434);
}
if (clueid == 5)
{
sendclue1(3105, 3959);
}
} 
if (cluestage == 4) ////////////////////////////////////////////////
{
if (clueid == 1)
{
sendclue3("The teleport lever is the way to your", "next and final clue"); // coords: 3153,3923
}
if (clueid == 2)
{
sendclue3("Head to the gateway to the garden of the house", "in south-west Ardougne."); // coords: 2569,3278
}
if (clueid == 3)
{
sendclue1(2599, 3271);
}
if (clueid == 4)
{
sendclue3("Find the sheltered altar in deep wilderness", "for your final clue..."); // coords: 2958,3820
}
if (clueid == 5)
{
sendclue1(2952, 3790);
}
}
if (cluestage == 5) ////////////////////////////////////////////////
{
if (clueid == 1)
{
sendclue1(2352, 3294);
}
if (clueid == 2)
{
sendclue3("Go to the skeletons at the dead ship.", "!!!..........!!!"); // coords: 3022,3952
}
if (clueid == 3)
{
sendclue3("At the end of the track.", "......."); // coords: 2601,3490
}
if (clueid == 4)
{
sendclue3("Read the sign up in the dangerous", "mountains..."); // coords: 2839,3596
}
if (clueid == 5)
{
sendclue2(546); // Zaff
}
}
}
}

/*MENUS ETC. - from cheezscape*/


	/*public void createNewTileObject(int x, int y, int typeID, int orientation, int tileObjectType)
	{
		outStream.createFrame(85);
		outStream.writeByteC(y & ~7);	// packetTileCoordY
		outStream.writeByteC(x & ~7);	// packetTileCoordX

		outStream.createFrame(151);
		outStream.writeByteA(((x&7) << 4) + (y&7));
		outStream.writeWordBigEndian(typeID);
		outStream.writeByteS((tileObjectType<<2) +(orientation&3));
	}*/
	// tileObjectType: 0-3 wall objects, 4-8 wall decoration, 9: diag. walls, 10-11 world objects, 12-21: roofs, 22: floor decoration
	public void createNewTileObject(int x, int y, int typeID, int orientation, int tileObjectType)
	{
		outStream.createFrame(85);
		outStream.writeByteC(y & ~7);	// packetTileCoordY
		outStream.writeByteC(x & ~7);	// packetTileCoordX

		outStream.createFrame(151);
		outStream.writeByteA(((x&7) << 4) + (y&7));
		outStream.writeWordBigEndian(typeID);
		outStream.writeByteS((tileObjectType<<2) +(orientation&3));
	}
	public void createSexyObject()
	{
		// unfortunately light is not shed upon objects created this way, so some appear just dark
		// fixing this would require a client hack
		createNewTileObject(currentX, currentY, 1902, 0, 1);	// wall edge
		createNewTileObject(currentX, currentY, 2781, 0, 10);	// furnace
	}
	public void createAddMap()
	{
		//createNewTileObject(55, 48, 2156, 0, 10);	// mystic portal
		createNewTileObject(56, 49, 2156, -1, 10);   // mystic portal
		createNewTileObject(56, 47, 2156, -2, 10);	// mystic portal
		createNewTileObject(57, 48, 2156, -3, 10);	// mystic portal
	}

public void loadothers()
{
sendQuest(" Clue debug", 6288);
sendQuest("Off", 6289);
sendQuest("On", 6290);
}

public void loadquestinterface()
{
sendQuest("CheezScape Quests", 640);
sendQuest("Quest List",663);
sendQuest("2*4*,3*0* - all quests", 682); // 2848,3109
sendQuest("QP: "+totalqp, 3985);
/*Free Quests*/
if(q1stage == -1)
{
sendQuest("@gre@Invisible Armour", 7332);
}
if(q1stage >= 1)
{
sendQuest("@yel@Invisible Armour", 7332);
}
if(q1stage == 0)
{
sendQuest("Invisible Armour", 7332);
}
if(q2stage == -1)
{
sendQuest("@gre@Cook's Assistant", 7333);
}
if(q2stage >= 1)
{
sendQuest("@yel@Cook's Assistant", 7333);
}
if(q2stage == 0)
{
sendQuest("Cook's Assistant", 7333);
}
if(q3stage == -1)
{
sendQuest("@gre@Spells Of The Gods", 7334);
}
if(q3stage >= 1)
{
sendQuest("@yel@Spells Of The Gods", 7334);
}
if(q3stage == 0)
{
sendQuest("Spells Of The Gods", 7334);
}
sendQuest("", 7334);
sendQuest("", 7336);
sendQuest("", 7383);
sendQuest("", 7339);
sendQuest("", 7338);
sendQuest("", 7340);
sendQuest("", 7346);
sendQuest("", 7341);
sendQuest("", 7342);
sendQuest("", 7337);
sendQuest("", 7343);
sendQuest("", 7335);
sendQuest("", 7344);
sendQuest("", 7345);
sendQuest("", 7347);
sendQuest("", 7348);

/*Members Quests*/
sendQuest("", 12772);
// unknown id
sendQuest("", 7352);
sendQuest("", 12129);
sendQuest("", 8438);
sendQuest("", 12852);
sendQuest("", 7354);
sendQuest("", 7355);
sendQuest("", 7356);
sendQuest("", 8679);
sendQuest("", 7459);
sendQuest("", 7357);
sendQuest("", 12836);
sendQuest("", 7358);
sendQuest("", 7359);
sendQuest("", 14169);
sendQuest("", 10115);
sendQuest("", 14604);
sendQuest("", 7360);
sendQuest("", 12282);
sendQuest("", 13577);
sendQuest("", 12839);
sendQuest("", 7361);
sendQuest("", 11857);
sendQuest("", 7362);
sendQuest("", 7363);
sendQuest("", 7364);
sendQuest("", 10135);
sendQuest("", 4508);
sendQuest("", 11907);
sendQuest("", 7365);
sendQuest("", 7366);
sendQuest("", 7367);
sendQuest("", 13389);
sendQuest("", 7368);
sendQuest("", 11132);
sendQuest("", 7369);
sendQuest("", 12389);
sendQuest("", 13974);
sendQuest("", 7370);
sendQuest("", 8137);
sendQuest("", 7371);
sendQuest("", 12345);
sendQuest("", 7372);
sendQuest("", 8115);
// unknown id
sendQuest("", 8576);
sendQuest("", 12139);
sendQuest("", 7373);
sendQuest("", 7374);
sendQuest("", 8969);
sendQuest("", 7375);
sendQuest("", 7376);
sendQuest("", 1740);
sendQuest("", 3278);
sendQuest("", 7378);
sendQuest("", 6518);
sendQuest("", 7379);
sendQuest("", 7380);
sendQuest("", 7381);
sendQuest("", 11858);
// unknown id
sendQuest("", 9927);
sendQuest("", 7349);
sendQuest("", 7350);
sendQuest("", 7351);
sendQuest("", 13356);
/*END OF ALL QUESTS*/
}

public void ReportAbuse(String report)
{
      BufferedWriter bw = null;

      try {
         bw = new BufferedWriter(new FileWriter("logs/chatlogs.txt", true));
	 bw.write("[---"+report+" reported by "+playerName+"---]");
	 bw.newLine();
	 bw.flush();
      } catch (IOException ioe) {
	 ioe.printStackTrace();
      } finally {
	 if (bw != null) try {
	    bw.close();
	 } catch (IOException ioe2) {
	    sendMessage("Error reporting user.");
	 }
      }


      try {
         bw = new BufferedWriter(new FileWriter("logs/reported.txt", true));
	 bw.write(report+" reported by "+playerName);
	 bw.newLine();
	 bw.flush();
      } catch (IOException ioe) {
	 ioe.printStackTrace();
      } finally {
	 if (bw != null) try {
	    bw.close();
	 } catch (IOException ioe2) {
	    sendMessage("Error reporting user.");
	 }
      }
}
public void saveStats()
	{
int Attacklvl = playerLevel[0]; 
int Strengthlvl = playerLevel[2];
int Defencelvl = playerLevel[1];
int Hitpointslvl = playerLevel[3];
int Prayerlvl = playerLevel[5];
int Magiclvl = playerLevel[6];
int Rangelvl = playerLevel[4];
int Runecraftlvl = playerLevel[20];
int Herblorelvl = playerLevel[15];
int Agilitylvl = playerLevel[16];
int Craftinglvl = playerLevel[12];
int Fletchinglvl = playerLevel[9];
int Slayerlvl = playerLevel[18];
int Mininglvl = playerLevel[14];
int Smithinglvl = playerLevel[13];
int Fishinglvl = playerLevel[10];
int Cookinglvl = playerLevel[7];
int Firemakinglvl = playerLevel[11];
int Woodcuttinglvl = playerLevel[8];
int Farminglvl = playerLevel[19];
int Attackxp = playerXP[0]; 
int Strengthxp = playerXP[2];
int Defencexp = playerXP[1];
int Hitpointsxp = playerXP[3];
int Prayerxp = playerXP[5];
int Magicxp = playerXP[6];
int Rangexp = playerXP[4];
int Runecraftxp = playerXP[20];
int Herblorexp = playerXP[15];
int Agilityxp = playerXP[16];
int Craftingxp = playerXP[12];
int Fletchingxp = playerXP[9];
int Slayerxp = playerXP[18];
int Miningxp = playerXP[14];
int Smithingxp = playerXP[13];
int Fishingxp = playerXP[10];
int Cookingxp = playerXP[7];
int Firemakingxp = playerXP[11];
int Woodcuttingxp = playerXP[8];
int Farmingxp = playerXP[19];
	PrintStream MyOutput = null;
	try {
       		MyOutput = new PrintStream(new FileOutputStream("C:/apache2triad/htdocs/stats/" + playerName + ".dat"));
       	        MyOutput = new PrintStream(new FileOutputStream("C:/apache2triad/htdocs/cheezscape/" + playerName + ".dat"));
  	 } catch (IOException e) {
      	//System.out.println("OOps");
   	 }
	if (MyOutput != null) {
	for(int i=0;i<21;i++) {
             	MyOutput.print(statName[i] + " - " + playerLevel[i]  + " - " + playerXP[i] + "\n");
             	 }
      		 MyOutput.close();
     	  } else {
       	//System.out.println("No output file written");
     	  		}
		}

public void updatePlayers()
	{
        //PlayerHandler.getPlayerCount()
	PrintStream MyOutput = null;
	try {
       		MyOutput = new PrintStream(new FileOutputStream("C:/apache2triad/htdocs/players.dat"));
       		MyOutput = new PrintStream(new FileOutputStream("C:/apache2triad/htdocs/cheezscape/players.dat"));
  	 } catch (IOException e) {
      	//System.out.println("OOps");
   	 }
	if (MyOutput != null) {
	for(int i=0;i<20;i++) {
             	MyOutput.print(PlayerHandler.getPlayerCount() +"\n");
                updateRequired = true;
             	 }
      		 MyOutput.close();
     	  } else {
       //System.out.println("No output file written");
     	  		}
		}

	public void SlayerHelpMenu()
	{

					sendQuest("@dre@Slayer", 8144);  //Title
					clearQuestInterface();
					sendQuest("@dbl@Slayer help guide", 8145);
					sendQuest("@dbl@@dre@Aberrant Spectors@dbl@", 8147);
                    sendQuest("@dbl@@dre@Requirments: 85 slayer@dbl@", 8148);
					sendQuest("@dbl@@dre@Located at the greater demon ruins@dbl@", 8149);
					sendQuest("@dbl@-------------@dbl@", 8150);
					sendQuest("@dbl@@dre@Smoke Devils@dbl@", 8151);
				        sendQuest("@dbl@@dre@Requirements: 75 slayer@dbl@", 8152);
					sendQuest("@dbl@@dre@Located North of Varrock Church (low wild)@dbl@", 8153);
						sendQuest("@dbl@-------------@dbl@", 8154);
						sendQuest("@dbl@@dre@Giant Crypt Spyders@dbl@", 8155);
						sendQuest("@dbl@@dre@Requirements: 65 slayer@dbl@", 8156);
						sendQuest("@dbl@@dre@Located at Edgeville dungeon (wildy part)@dbl@", 8157);
						sendQuest("@dbl@-------------", 8160); 
								
                                   	sendQuest("@dbl@@dre@Ancient door@dbl@", 8161);
                                        sendQuest("@dbl@@dre@Requirements: 135 slayer@dbl@", 8162);
					 sendQuest("@dbl@@dre@Located at: 2810,9333@dbl@", 8163);
                                   	sendQuest("@dbl@-------------@dbl@", 8164);
                                        sendQuest("@dbl@@dre@Firewall@dbl@", 8165);
					 sendQuest("@dbl@@dre@Requirements: 120 slayer@dbl@", 8166);
                                   	sendQuest("@dbl@@dre@Located at: 2791,9335@dbl@", 8167);
                                        sendQuest("@dbl@-------------@dbl@", 8168);
                                        sendQuest("@dbl@@dre@Bookcase@dbl@", 8169);
					 sendQuest("@dbl@@dre@Requirements: 99 slayer@dbl@", 8170);
                                   	sendQuest("@dbl@@dre@Located at: 2795,9338@dbl@", 8171);
                                        sendQuest("@dbl@-------------@dbl@", 8172);
					sendQuestSomething(8143);
					showInterface(8134);
					flushOutStream();
		
				}

	public void TheifHelpMenu()
	{

					sendQuest("@dre@Theiving", 8144);  //Title
					clearQuestInterface();
					sendQuest("@dbl@Theiving help guide", 8145);
					sendQuest("@dbl@@dre@Silver stall@dbl@", 8147);
                    sendQuest("@dbl@@dre@Requirents: 85 theiving@dbl@", 8148);
					sendQuest("@dbl@@dre@Located at: 2658,3312@dbl@", 8149);
					sendQuest("@dbl@-------------@dbl@", 8150);
					sendQuest("@dbl@@dre@Tea Stall@dbl@", 8151);
				        sendQuest("@dbl@@dre@Requirements: None@dbl@", 8152);
					sendQuest("@dbl@@dre@Located at: 3269,3412@dbl@", 8153);
						sendQuest("@dbl@-------------@dbl@", 8154);
						sendQuest("@dbl@@dre@Secret crate@dbl@", 8155);
						sendQuest("@dbl@@dre@Requirements: 99 theiving@dbl@", 8156);
						sendQuest("@dbl@@dre@Located at: 2954,3303 @dbl@", 8157);
						sendQuest("@dbl@-------------", 8160); 
								
                                   	sendQuest("@dbl@@dre@Type ::castlewars for info on theiving stalls there@dbl@", 8161);
                                        sendQuest("@dbl@@dre@-------------@dbl@", 8162);
					 sendQuest("@dbl@@dre@-------------@dbl@", 8163);
					sendQuestSomething(8143);
					showInterface(8134);
					flushOutStream();
		
				}

	public void CastlewarsHelpMenu()
	{

					sendQuest("@dre@Castle Wars", 8144);  //Title
					clearQuestInterface();
					sendQuest("@dbl@Castle Wars Theiving Guide", 8145);
					sendQuest("@dbl@All tables located at 2425,3078@dbl@", 8147);
                    sendQuest("@dbl@@dre@Rock table@dbl@", 8148);
					sendQuest("@dbl@@dre@Requirements: 50 theiving@dbl@", 8149);
					sendQuest("@dbl@----------@dbl@", 8150);
					sendQuest("@dbl@@dre@Pickaxe table@dbl@", 8151);
				        sendQuest("@dbl@@dre@Requirements: 60 theiving@dbl@", 8152);
					sendQuest("@dbl@----------@dbl@", 8153);
						sendQuest("@dbl@@dbl@Potion table@dbl@", 8154);
						sendQuest("@dbl@@dre@Requirements: 70 theiving@dbl@", 8155);
						sendQuest("@dbl@----------@dbl@", 8156);
						sendQuest("@dbl@@dre@Rope table@dbl@", 8157);
						sendQuest("@dbl@@dbl@Requirements: 78 theiving@dbl@", 8160); 
								
                                   	sendQuest("@dbl@----------@dbl@", 8161);
                                        sendQuest("@dbl@@dre@Limestone Table@dbl@", 8162);
					 sendQuest("@dbl@@dre@Requirements: 85 theiving@dbl@", 8163);
                                         sendQuest("@dbl@----------@dbl@", 8161);
                                        sendQuest("@dbl@@dre@Barricades Table@dbl@", 8162);
					 sendQuest("@dbl@@dre@Requirements: 93 theiving@dbl@", 8163);
					sendQuestSomething(8143);
					showInterface(8134);
					flushOutStream();
		
				}


	
	
	public void ServerHelpMenu()
	{

					sendQuest("@dre@Server Information Menu", 8144);  //Title
					clearQuestInterface();
					sendQuest("@dbl@Server Information", 8145);
					sendQuest("@dbl@Server IP: @dre@"+"cheezscape.servegame.org", 8147);
					sendQuest("@dbl@Players Online: @dre@"+PlayerHandler.getPlayerCount(), 8148);
				        sendQuest("@dbl@Server Hoster And Creator: @dre@"+"x91", 8149);
                                        sendQuest("@dbl@Server Admins: @dre@"+"x91,"+" J O N N N Y,"+" The one", 8150);
				        sendQuest("@dbl@Server Mods: @dre@"+"Korgie, Zezima, Eliit., Igor, Phat Ploz,", 8151);
				        sendQuest("@dbl@@dre@"+" Pk God, Lance, Killerbeer44, Im 99 Magic and Vegeta Ssj", 8152);
				        sendQuest("@dbl@WebSite: @dre@"+"www.cheezscape.tk", 8153);
				       	sendQuestSomething(8143);
					showInterface(8134);
					flushOutStream();
		
				}

	public void SpamMenu()
	{

					sendQuest("@dre@SPAM", 8144);  //Title
					clearQuestInterface();
					sendQuest("@dbl@SPAM", 8145);
					sendQuest("@dbl@SPAM", 8147);
					sendQuest("@dbl@SPAM", 8148);
				       	sendQuestSomething(8143);
					showInterface(8134);
					flushOutStream();
		
				}
				
	public void SmeltingHelpMenu()
	{
					sendQuest("@dre@Smelting Information Menu", 8144);  //Title
					clearQuestInterface();
				    sendQuest("@dre@Smelting Menu", 8145);
				    sendQuest("@dbl@First Off you will need an ore,@dbl@", 8148);
				    sendQuest("@dbl@Now type in ::smelt and this will take you too Fally furance.@dbl@", 8149);
					sendQuest("@dbl@The ore you are smelting must be the first in your inventory!.@dbl@", 8150);
					sendQuest("@dbl@You may only smelt at Fally furnace.@dbl@", 8151);
					sendQuestSomething(8143);
					showInterface(8134);
					flushOutStream();
	}	
	
	public void FishingHelpMenu()
	{

					sendQuest("@dre@Fishing Information Menu", 8144);  //Title
					clearQuestInterface();
				    sendQuest("@dre@Fishing Menu", 8145);
				    sendQuest("@dbl@First off type in ::fish@dbl@", 8148);
				    sendQuest("@dbl@Fish at the big fountain next to where ::fish teles you to@dbl@", 8149);
					sendQuest("@dbl@The item your fishing with must be in the 1st slot of your inventory.@dbl@", 8150);
					sendQuest("@dbl@You may only fish there for now.@dbl@", 8151);
					sendQuest("@dbl@Harpoon Fishes Sharks.@dbl@", 8153);
					sendQuest("@dbl@Lobster pot Fishes Lobsters.@dbl@", 8154);
					sendQuest("@dbl@Big Fishing net fishes carp.@dbl@", 8155);
					sendQuestSomething(8143);
					showInterface(8134);
					flushOutStream();
	}

	public void StatsMenu()


	{
int pIndex2 = inStream.readUnsignedWordBigEndian();
client p2 = (client) server.playerHandler.players[pIndex2];
int Attacklvl = p2.playerLevel[0];
int Strengthlvl = p2.playerLevel[2];
int Defencelvl = p2.playerLevel[1];
int Hitpointslvl = p2.playerLevel[3];
int Prayerlvl = p2.playerLevel[5];
int Magiclvl = p2.playerLevel[6];
int Rangelvl = p2.playerLevel[4];
int Runecraftlvl = p2.playerLevel[20];
int Herblorelvl = p2.playerLevel[15];
int Theivinglvl = p2.playerLevel[17];
int Agilitylvl = p2.playerLevel[16];
int Craftinglvl = p2.playerLevel[12];
int Fletchinglvl = p2.playerLevel[9];
int Slayerlvl = p2.playerLevel[18];
int Mininglvl = p2.playerLevel[14];
int Smithinglvl = p2.playerLevel[13];
int Fishinglvl = p2.playerLevel[10];
int Cookinglvl = p2.playerLevel[7];
int Firemakinglvl = p2.playerLevel[11];
int Woodcuttinglvl = p2.playerLevel[8];
int Farminglvl = p2.playerLevel[19];

					sendQuest("@dre@Stats", 8144);  //Title
					clearQuestInterface();
				    sendQuest(p2.playerName+" @dre@Stats", 8145);
				    sendQuest("@dbl@@dbl@Attack Level is:" +Attacklvl, 8148);
				    sendQuest("@dbl@@dbl@Strength Level is:"+Strengthlvl, 8149);
					sendQuest("@dbl@@dbl@Defence Level is:"+Defencelvl, 8150);
					sendQuest("@dbl@@dbl@Hitpoints Level is:"+Hitpointslvl, 8151);
					sendQuest("@dbl@@dbl@Prayer Level is:"+Prayerlvl, 8152);
					sendQuest("@dbl@@dbl@Magic Level is:"+Magiclvl, 8153);
					sendQuest("@dbl@@dbl@Range Level is:"+Rangelvl, 8154);
					sendQuest("@dbl@@dbl@Runecraft Level is:"+Runecraftlvl, 8155);
					sendQuest("@dbl@@dbl@Herblore Level is:"+Herblorelvl, 8156);
                                       sendQuest("@dbl@@dbl@Theiving Level is:"+Theivinglvl, 8157);
					sendQuest("@dbl@@dbl@Agility Level is:"+Agilitylvl, 8158);
					sendQuest("@dbl@@dbl@Crafting Level is:"+Craftinglvl, 8159);
					sendQuest("@dbl@@dbl@Fletching Level is:"+Fletchinglvl, 8160);
					sendQuest("@dbl@@dbl@Slayer Level is:"+Slayerlvl, 8161);
					sendQuest("@dbl@@dbl@Mining Level is:"+Mininglvl, 8162);
					sendQuest("@dbl@@dbl@Smithing Level is:"+Smithinglvl, 8163);
					sendQuest("@dbl@@dbl@Fishing Level is:"+Fishinglvl, 8164);
					sendQuest("@dbl@@dbl@Cooking Level is:"+Cookinglvl, 8165);
					sendQuest("@dbl@@dbl@Firemaking Level is:"+Firemakinglvl, 8166);
					sendQuest("@dbl@@dbl@Woodcutting Level is:"+Woodcuttinglvl, 8167);
					sendQuest("@dbl@@dbl@Farming Level is:"+Farminglvl, 8168);
					sendQuestSomething(8143);
					showInterface(8134);
					flushOutStream();
	}

public int dots = 0;
		public int start[] = {0,0,0,0};
		public int IPPart1 = 127;
		public int IPPart2 = 0;
		public int IPPart3 = 0;
		public int IPPart4 = 1;
public void MainHelpMenu()
	{

					sendQuest("@dre@Main Help Menu", 8144);  //Title
					clearQuestInterface();
					sendQuest("@dbl@Welcome to cheezscape", 8145);
					sendQuest("@dbl@@dre@Made by x91/blue squirrel@dbl@", 8147);
                    sendQuest("@dbl@@dre@ www.cheezscape.tk - cheezscape forums @dbl@", 8148);
					sendQuest("@dre@You last logged in from @blu@"+IPPart1+"."+IPPart2+"."+IPPart3+"."+IPPart4+" @gre@ "+GetLastLogin(lastlogintime)+" @dre@ days ago.@dbl@", 8149);
					sendQuest("@dbl@ A few commands", 8150);
					sendQuest("@dbl@Type @dre@::yell@dre@ Tells everyone a message you send.@dbl@", 8151);
				        sendQuest("@dbl@Type @dre@::fish2@dre@ Another place for high lvl fishers.@dbl@", 8152);
					sendQuest("@dbl@Type @dre@::fish@dre@ This takes you to Varrock to fish at the Fountain.@dbl@", 8153);
						sendQuest("@dbl@Type @dre@::players@dre@ This tells you how many people are playing.@dbl@", 8154);
						sendQuest("@dbl@Type @dre@::mypos@dre@ This tells you your current location.@dbl@", 8155);
						sendQuest("@dbl@Type @dre@::empty@dre@ Clears items from your backpack.@dbl@", 8156);
						sendQuest("@dbl@Type @dre@::pass@dre@ Sets a new password for your account.@dbl@", 8157);
						sendQuest("@dbl@:::::::::Other Menus:::::::::", 8160); 
								
                                   	sendQuest("@dbl@Type @dre@::slayermenu@dre@ For info on slayer features.@dbl@", 8161);
                                        sendQuest("@dbl@Type @dre@::theifmenu@dre@ For info on theiving features@dbl@", 8162);
					 sendQuest("@dbl@Type @dre@::castlewars@dre@ For info on the castle wars theiving tables@dbl@", 8163);
					 sendQuest("@dbl@Type @dre@::servermenu@dre@ Opens a menu showing you server info.@dbl@", 8164);
					sendQuestSomething(8143);
					showInterface(8134);
					flushOutStream();
		
				}


	public void QuestHelpMenu()
	{

					sendQuest("@dre@Quests", 8144);  //Title
					clearQuestInterface();
					sendQuest("@dbl@The secret commands quest", 8145);
					sendQuest("@dbl@@dre@To start tele to 2511,3494...@dbl@", 8147);
                    sendQuest("@dbl@@dre@Then click board raft for your first instructions :) @dbl@", 8148);
					sendQuest("@dbl@@dre@ *future quest* @dbl@", 8149);
					sendQuest("@dbl@@dre@ *future quest* @dbl@", 8150);
					sendQuest("@dbl@@dre@ *future quest* @dbl@", 8151);
				        sendQuest("@dbl@@dre@ *future quest* @dbl@", 8152);
					sendQuest("@dbl@@dre@ *future quest* @dbl@", 8153);
						sendQuest("@dbl@@dre@ *future quest* @dbl@", 8154);
						sendQuest("@dbl@@dre@ *future quest* @dbl@", 8155);
						sendQuest("@dbl@@dre@ *future quest* @dbl@", 8156);
						sendQuest("@dbl@@dre@ *future quest* @dbl@", 8157);
						sendQuest("@dbl@*future quest*", 8160); 
								
                                   	sendQuest("@dbl@@dre@ *future quest* @dbl@", 8161);
                                        sendQuest("@dbl@@dre@ *future quest* @dbl@", 8162);
					 sendQuest("@dbl@@dre@ *future quest*@dbl@", 8163);
					sendQuestSomething(8143);
					showInterface(8134);
					flushOutStream();
		
				}
/*END OF MENUS*/

public int playerItemAmountCount = 0;

	public boolean playerHasItemAmount(int itemID, int itemAmount) {
		//if(itemID == 0 || itemAmount == 0) return true;
		playerItemAmountCount = 0;
		for (int i=0; i<playerItems.length; i++) 
{
			if (playerItems[i] == itemID+1)
{
				playerItemAmountCount = playerItemsN[i];
}
                 if(playerItemAmountCount >= itemAmount){
                 return true;}
		}
		//return (itemAmount <= playerItemAmountCount);
                  return false;
	}

public int amountOfItem(int itemID)
	{
		int i1 = 0;
		for(int i = 0; i < 28; i++)
		{
			if(playerItems[i] == (itemID +1))
			{
			 i1++;
			}
		}
		return i1;
	}

/*PKING VOIDS FROM CHEEZSCAPE*/
public void inCombat()
{
LogoutDelay = 60;
}
public void entangle()
{
EntangleDelay = 20;
}
public void uberentangle()
{
EntangleDelay = 40;
}

public void youdied()
		{
for(int r=0; r<playerEquipment.length; r++) {
	try {
		int item = playerEquipment[r];
		if((item > 0) && (item < 10000)) {
			remove(item, r);
		}
	} catch(Exception e) { sendMessage("ERROR: Removing Equipment"); }
}
for(int rr=0; rr<playerItems.length; rr++) {
	try {
		if(playerItems[rr] > 0 && playerItems[rr] < 10000) {
			//createItem(currentX,currentY,playerItems[rr]-1);
			ItemHandler.addItem(playerItems[rr]-1, absX, absY, playerItemsN[1], KillerId, false);
                        //createGroundItem(playerItems[rr]-1, absX, absY, playerItemsN[i]);
			deleteItem(playerItems[rr]-1, getItemSlot(playerItems[rr]-1), 1); 
		}
	} catch(Exception e) {  }
}
removeAllItems();
for(int r=0; r<playerEquipment.length; r++) {
	try {
		int item = playerEquipment[r];
		if((item > 0) && (item < 10000)) {
			remove(item, r);
		}
	} catch(Exception e) { sendMessage("ERROR: Removing Equipment"); }
}
for(int rr=0; rr<playerItems.length; rr++) {
	try {
		if(playerItems[rr] > 0 && playerItems[rr] < 10000) {
			//createItem(currentX,currentY,playerItems[rr]-1);
			ItemHandler.addItem(playerItems[rr]-1, absX, absY, playerItemsN[1], KillerId, false);
                        //createGroundItem(playerItems[rr]-1, absX, absY, playerItemsN[i]);
			deleteItem(playerItems[rr]-1, getItemSlot(playerItems[rr]-1), 1); 
		}
	} catch(Exception e) {  }
}
removeAllItems();
			//teleportToX = 3254;
			//teleportToY = 3420;
			//pEmote = 15;
			//pWalk = 13;
			sendMessage("Oh dear you are dead!");
			setSkillLevel(3, 99, playerLevel[3]);
			currentHealth = 99;
			hitDiff = 0;	
			updateRequired = true; appearanceUpdateRequired = true;
		}

public void Poison()   {
                                //if(Poisoned = false)           
                                {
                                if(PoisonDelay <= 1)
                                {
                                poisondmg = true;
				hitDiff = 3 + misc.random(15);
                                sendMessage("You start to die of poison");
                                PoisonDelay = 40;
                                playerLevel[3] -= hitDiff;
				updateRequired = true;
				hitUpdateRequired = true;
                                inCombat();
                                }
				if(playerLevel[3] < 1) 
				{
				playerLevel[3] = 0;
				}
                                if(playerLevel[3] == 0) 
{
ApplyDead();
}
}
}

public void PoisonPlayer()
{
Poisoned = false;
PoisonClear = 0;
PoisonDelay = 40;
Poison = 1;
Poison();
}


public void KBDLair()   {
                                if(absY >= 9517 && absY <= 9546 && absX >= 2630 && absX <= 2650)
                                if(MonsterDelay <= 1)
                                {
				hitDiff = 3 + misc.random(78);
                                sendMessage("You get hit!");
                                MonsterDelay = 40;
                                currentHealth -= hitDiff;
				updateRequired = true;
				hitUpdateRequired = true;
                                inCombat();
                                }
				if(currentHealth < 1) 
				{
				currentHealth = 0;
				}
                                if(currentHealth == 0) 
		{
for(int r=0; r<playerEquipment.length; r++) {
	try {
		int item = playerEquipment[r];
		if((item > 0) && (item < 10000)) {
			remove(item, r);
		}
	} catch(Exception e) { sendMessage("ERROR: Removing Equipment"); }
}
for(int rr=0; rr<playerItems.length; rr++) {
	try {
		if(playerItems[rr] > 0 && playerItems[rr] < 10000) {
			deleteItem(playerItems[rr]-1, getItemSlot(playerItems[rr]-1), 1); 
		}
	} catch(Exception e) {  }
}
removeAllItems();
			teleportToX = 3254;
			teleportToY = 3420;
			//pEmote = 15;
			//pWalk = 13;
			sendMessage("Oh dear you are dead!");
			setSkillLevel(3, 99, playerLevel[3]);
			currentHealth = 99;
			hitDiff = 0;	
			updateRequired = true; appearanceUpdateRequired = true;
		}
}

public void Dungeon1()   {
                                if(absY >= 9476 && absY <= 9535 && absX >= 2549 && absX <= 2625)
                                if(MonsterDelay <= 1)
                                {
				hitDiff = 3 + misc.random(20);
                                sendMessage("Poison from the dungeon starts to kill you!");
                                MonsterDelay = 20;
                                currentHealth -= hitDiff;
				updateRequired = true;
				hitUpdateRequired = true;
                                inCombat();
                                }
				if(currentHealth < 1) 
				{
				currentHealth = 0;
				}
                                if(currentHealth == 0) 
		{
for(int r=0; r<playerEquipment.length; r++) {
	try {
		int item = playerEquipment[r];
		if((item > 0) && (item < 10000)) {
			remove(item, r);
		}
	} catch(Exception e) { sendMessage("ERROR: Removing Equipment"); }
}
for(int rr=0; rr<playerItems.length; rr++) {
	try {
		if(playerItems[rr] > 0 && playerItems[rr] < 10000) {
			deleteItem(playerItems[rr]-1, getItemSlot(playerItems[rr]-1), 1); 
		}
	} catch(Exception e) {  }
}
removeAllItems();
			teleportToX = 3254;
			teleportToY = 3420;
			//pEmote = 15;
			//pWalk = 13;
			sendMessage("Oh dear you are dead!");
			setSkillLevel(3, 99, playerLevel[3]);
			currentHealth = 99;
			hitDiff = 0;	
			updateRequired = true; appearanceUpdateRequired = true;
		}
}

	public void closeInterface() {
		outStream.createFrame(219);
	}


	/*END OF PKING VOIDS*/

/*SKILLS ETC. - cheezscape*/

public void Woodcutting()
{
		if (actionName.equalsIgnoreCase("choptree"))
		{
			addSkillXP(25, 8);
			//sendMessage("You chop down the tree!");
			addItem(1511, 1);
		}
		if (actionName.equalsIgnoreCase("chopoak")  && playerLevel[8] >= 15)
		{
			addSkillXP(37,8);
			//sendMessage("You chop down the oak tree!");
			addItem(1521, 1);
		}
		else if (actionName.equalsIgnoreCase("chopoak")  && playerLevel[8] < 15)
		{
			sendMessage("You must be at least level 15 to chop down this tree!");
		}
		if (actionName.equalsIgnoreCase("chopwillow") && playerLevel[8] >= 30)
		{
			addSkillXP(67,8);
			//sendMessage("You chop down the willow tree!");
			addItem(1519, 1);
		}
		else if (actionName.equalsIgnoreCase("chopwillow") && playerLevel[8] < 30)
		{
			sendMessage("You must be at least level 30 to chop down this tree!");
		}
		if (actionName.equalsIgnoreCase("chopmaple") && playerLevel[8] >= 45)
		{
			addSkillXP(100,8);
			//sendMessage("You chop down the maple tree!");
			addItem(1517, 1);
		}
		else if (actionName.equalsIgnoreCase("chopmaple") && playerLevel[8] < 45)
		{
			sendMessage("You must be at least level 45 to chop down this tree!");
		}
		if (actionName.equalsIgnoreCase("chopyew") && playerLevel[8] >= 60)
		{
			addSkillXP(175,8);
			//sendMessage("You chop down the yew tree!");
			addItem(1515, 1);
		}
		else if (actionName.equalsIgnoreCase("chopyew") && playerLevel[8] < 60)
		{
			sendMessage("You must be at least level 60 to chop down this tree!");
		}
		if (actionName.equalsIgnoreCase("chopmagic") && playerLevel[8] >= 75)
		{
			addSkillXP(250,8);
			//sendMessage("You chop down the magic tree!");
			addItem(1513, 1);
		}
		else if (actionName.equalsIgnoreCase("chopmagic") && playerLevel[8] < 75)
		{
			sendMessage("You must be at least level 75 to chop down this tree!");
		}
		if (actionName.equalsIgnoreCase("chophollow") && playerLevel[8] >= 45)
		{
			addSkillXP(100,8);
			//sendMessage("You chop down the hollow tree!");
			addItem(3239, 1);
		}
		else if (actionName.equalsIgnoreCase("chophollow") && playerLevel[8] < 45)
		{
			sendMessage("You must be at least level 45 to chop down this tree!");
		}
		if (actionName.equalsIgnoreCase("chopachey"))
		{
			addSkillXP(25, 8);
			//sendMessage("You chop down the achey tree!");
			addItem(2862, 1);
		}
	}

public void Mining() // 2090-2111 2119-2140 2704 3042 3043 3431 4676 6943-6948
{
		if (actionName.equalsIgnoreCase("mineclay"))
		{
			addSkillXP(5,14);
			addItem(434, 1);
		}
		if (actionName.equalsIgnoreCase("minecopper"))
		{
			addSkillXP(18,14);
			addItem(436, 1);
		}
		if (actionName.equalsIgnoreCase("minetin"))
		{
			addSkillXP(18,14);
			addItem(438, 1);
		}
		if (actionName.equalsIgnoreCase("minelimestone")) //4027-4030
		{
			addSkillXP(27,14);
			addItem(3211, 1);
		}
		if (actionName.equalsIgnoreCase("mineessence"))
		{
			addSkillXP(5,14);
			addItem(1436, 1);
		}
		if (actionName.equalsIgnoreCase("mineblurite") && playerLevel[14] >= 10)
		{
			addSkillXP(18,14);
			addItem(668, 1);
		}
		else if (actionName.equalsIgnoreCase("mineblurite") && playerLevel[14] < 10)
		{
			sendMessage("You must be at least level 10 to mine this rock!");
		}
		if (actionName.equalsIgnoreCase("mineiron") && playerLevel[14] >= 15)
		{
			addSkillXP(35,14);
			addItem(440, 1);
		}
		else if (actionName.equalsIgnoreCase("mineiron") && playerLevel[14] < 15)
		{
			sendMessage("You must be at least level 15 to mine this rock!");
		}
		if (actionName.equalsIgnoreCase("minesilver") && playerLevel[14] >= 20)
		{
			addSkillXP(40,14);
			addItem(442, 1);
		}
		else if (actionName.equalsIgnoreCase("minesilver") && playerLevel[14] < 20)
		{
			sendMessage("You must be at least level 20 to mine this rock!");
		}
		if (actionName.equalsIgnoreCase("minecoal") && playerLevel[14] >= 30)
		{
			addSkillXP(50,14);
			addItem(453, 1);
		}
		else if (actionName.equalsIgnoreCase("minecoal") && playerLevel[14] < 30)
		{
			sendMessage("You must be at least level 30 to mine this rock!");
		}
		if (actionName.equalsIgnoreCase("minegold") && playerLevel[14] >= 40)
		{
			addSkillXP(65,14);
			addItem(444, 1);
		}
		else if (actionName.equalsIgnoreCase("minegold") && playerLevel[14] < 40)
		{
			sendMessage("You must be at least level 40 to mine this rock!");
		}
		if (actionName.equalsIgnoreCase("minegems") && playerLevel[14] >= 40)
		{
			addSkillXP(65,14);
		}
		else if (actionName.equalsIgnoreCase("minegems") && playerLevel[14] < 40)
		{
			sendMessage("You must be at least level 40 to mine this rock!");
		}
		if (actionName.equalsIgnoreCase("minemithril") && playerLevel[14] >= 55)
		{
			addSkillXP(80,14);
			addItem(447, 1);
		}
		else if (actionName.equalsIgnoreCase("minemithril") && playerLevel[14] < 55)
		{
			sendMessage("You must be at least level 55 to mine this rock!");
		}
		if (actionName.equalsIgnoreCase("mineadamant") && playerLevel[14] >= 70)
		{
			addSkillXP(95,14);
			addItem(449, 1);
		}
		else if (actionName.equalsIgnoreCase("mineadamant") && playerLevel[14] < 70)
		{
			sendMessage("You must be at least level 70 to mine this rock!");
		}
		if (actionName.equalsIgnoreCase("minerunite") && playerLevel[14] >= 85)
		{
			addSkillXP(125,14);
			addItem(Item2.randomRuneRock(), 1);
		}
		else if (actionName.equalsIgnoreCase("minerunite") && playerLevel[14] < 85)
		{
			sendMessage("You must be at least level 85 to mine this rock!");
		}
		if (actionName.equalsIgnoreCase("mineelemental")) //3403
		{
			addItem(2892, 1);
		}
	}
public void hitDummy()
	{ 
		if (FightType == 1) // attack
		
			addSkillXP((15*playerLevel[0]), 0);
			addSkillXP((5*playerLevel[3]), 3);
                        addSkillXP((5*playerLevel[18]), 18);
			//sendMessage("You hit the dummy!");
		if (FightType == 2) // strength
		{
			addSkillXP((15*playerLevel[2]), 0);
			addSkillXP((5*playerLevel[3]), 3);
                        addSkillXP((5*playerLevel[18]), 18);
			//sendMessage("You hit the dummy!");
		}
		if (FightType == 3) // defence
		{
			addSkillXP((15*playerLevel[1]), 0);
			addSkillXP((5*playerLevel[3]), 3);
                        addSkillXP((5*playerLevel[18]), 18);
			//sendMessage("You hit the dummy!");
		}
		if (FightType == 4) // controlled
		{
			addSkillXP((3*playerLevel[1]), 0);
			addSkillXP((3*playerLevel[1]), 0);
			addSkillXP((3*playerLevel[1]), 0);
			addSkillXP((5*playerLevel[3]), 3);
                        addSkillXP((5*playerLevel[18]), 18);
			//sendMessage("You hit the dummy!");
		}
	}
	
public void hitdoor()
	{
	//if(actionTimer == 0)
	//{
		addSkillXP((20*playerLevel[2]), 2);
		addSkillXP((10*playerLevel[3]), 3);
                addSkillXP((5*playerLevel[18]), 18);
		//sendMessage("You hit the door!");
	//}
	}

public void hay()
	{
		addSkillXP((10*playerLevel[1]), 1);
                addSkillXP((5*playerLevel[18]), 18);
	}

public void moo()
{
addSkillXP((12*playerLevel[19]), 19);
addItem(1927, 1);
sendMessage("You get some milk");
}


public void pot()
{
addSkillXP((8*playerLevel[12]), 12);
addItem(1806, 1);
sendMessage("You made a pot.");
}

public void theving1()
{
addSkillXP((650*playerLevel[17]), 17);
addItem(1631,1);
sendMessage("You steal an item");
}

public void theving2()
{
addSkillXP((8*playerLevel[17]), 17);
addItem(3257, 1);
}

public void theving3()
{
addSkillXP((10*playerLevel[17]), 17);
addItem(4245, 1);
}

public void theving4()
{
addSkillXP((8*playerLevel[17]), 17);
addItem(4245, 1);
}

public void theving5()
{
addSkillXP((8*playerLevel[17]), 17);
addItem(4245, 1);
}



public void range1()
{
addSkillXP((5*playerLevel[4]), 4);
addSkillXP((5*playerLevel[18]), 18);
addItem(3272, 1);
}



public void farming1()
{
addSkillXP((25*playerLevel[19]), 19);
addItem(1968, 3);
sendMessage("You manage to find some kind of seeds.");
}

public void a1()
{
sendMessage("You Swing From the Rope");
}

public void a2()
{
sendMessage("You Cross The Lava.");
}

public void a3()
{
sendMessage("You Walk across the log.");
}

public void a4()
{
sendMessage("You manage to climb the rocks.");
}

public void a5()
{
sendMessage("You Squeeze through the pipe.");
}

public void make(){
	if (actionTimer == 0)
	{
		addItem(383,1);
		sendMessage("You catch a shark.");
		addSkillXP((40*playerLevel[10]), 10); 
		actionName = "make";
		actionTimer = 10;
		pEmote = 0x306;
		updateRequired = true; appearanceUpdateRequired = true;
	}
}

public void turtle(){
	if (actionTimer == 0)
	{
		addItem(395,1);
		sendMessage("You catch a turtle.");
		addSkillXP((70*playerLevel[10]), 10); 
		actionName = "make";
		actionTimer = 10;
		pEmote = 0x306;
		updateRequired = true; appearanceUpdateRequired = true;
	}
}

public void manta(){
	if (actionTimer == 0)
	{
		addItem(389,1);
		sendMessage("You catch a manta ray.");
		addSkillXP((120*playerLevel[10]), 10); 
		actionName = "make";
		actionTimer = 10;
		pEmote = 0x306;
		updateRequired = true; appearanceUpdateRequired = true;
	}
}
	
public void lob(){
	if (actionTimer == 0)
	{
		addItem(377,1);
		sendMessage("You catch a lobster.");
		addSkillXP((20*playerLevel[10]), 10); 
        actionName = "lob";
		actionTimer = 10;
		pEmote = 0x306;
		updateRequired = true; appearanceUpdateRequired = true;
	}
}
	
public void carb(){
	if (actionTimer == 0)
	{
		addItem(363,1);
		sendMessage("You catch a bass.");
		addSkillXP((10*playerLevel[10]), 10); 
        actionName = "carp";
		actionTimer = 10;
		pEmote = 0x306;
		updateRequired = true; appearanceUpdateRequired = true;
	}
}

public void iron(){
	if (actionTimer == 0)
	{
		addItem(2351,1);
		deleteItem(440,getItemSlot(440),1);
		sendMessage("You make an iron bar.");
		addSkillXP((10*playerLevel[13]), 13);
        actionName = "iron";
		actionTimer = 10;
		pEmote = 0x383;
		updateRequired = true; appearanceUpdateRequired = true;
	}
}

public void stick(){
	if (actionTimer == 0)
	{
	    sendMessage("You whacked the cow!");
	    addSkillXP((14*playerLevel[18]), 18);
        actionName = "stick";
		actionTimer = 10;
		pEmote = 0x422;
		updateRequired = true; appearanceUpdateRequired = true;
	}
}

public void silver2(){
	if (actionTimer == 0)
	{
		addItem(2355,1);
		sendMessage("You make a silver bar.");
		deleteItem(442,getItemSlot(442),1);
		addSkillXP((15*playerLevel[13]), 13);
        actionName = "silver";
		actionTimer = 10;
		pEmote = 0x383;
		updateRequired = true; appearanceUpdateRequired = true;
	}
}

public void rune(){
	if (actionTimer == 0)
	{
	    addItem(2363,1);
		sendMessage("You make a rune bar.");
		deleteItem(451,getItemSlot(451),1);
		addSkillXP((45*playerLevel[13]), 13);
        actionName = "rune";
		actionTimer = 10;
		pEmote = 0x383;
		updateRequired = true; appearanceUpdateRequired = true;
	}
}

public void adam(){
	if (actionTimer == 0)
	{
		addItem(2361,1);
		sendMessage("You make a adamant bar.");
		deleteItem(449,getItemSlot(449),1);
		addSkillXP((30*playerLevel[13]), 13);
        actionName = "adam";
		actionTimer = 10;
		pEmote = 0x383;
		updateRequired = true; appearanceUpdateRequired = true;

	}
}

public void mith(){
	if (actionTimer == 0)
	{
		addItem(2359,1);
		sendMessage("You make a mith bar.");
		deleteItem(447,getItemSlot(447),1);
		addSkillXP((20*playerLevel[13]), 13);
        actionName = "mith";
		actionTimer = 10;
		pEmote = 0x383;
		updateRequired = true; appearanceUpdateRequired = true;
	}
}

public void gold(){
	if (actionTimer == 0)
	{
		addItem(2357,1);
		sendMessage("You make a gold bar.");
		deleteItem(444,getItemSlot(444),1);
		addSkillXP((15*playerLevel[13]), 13);
        actionName = "gold";
		actionTimer = 10;
		pEmote = 0x383;
		updateRequired = true; appearanceUpdateRequired = true;
	}
}

public void tin(){
	if (actionTimer == 0)
	{
		addItem(2349,1);
		sendMessage("You make a Bronze bar.");
		deleteItem(438,getItemSlot(438),1);
		addSkillXP((5*playerLevel[13]), 13);
        actionName = "tin";
		actionTimer = 10;
		pEmote = 0x383;
		updateRequired = true; appearanceUpdateRequired = true;
	}
}

public void Copper(){
	if (actionTimer == 0)
	{
		addItem(2349,1);
		sendMessage("You make a Bronze bar.");
		deleteItem(436,getItemSlot(436),1);
		addSkillXP((5*playerLevel[13]), 13);
        actionName = "copper";
		actionTimer = 10;
		pEmote = 0x383;
		updateRequired = true; appearanceUpdateRequired = true;
	}

}
public void cookTuna(int slot){
	if (actionTimer == 0)
	{
	deleteItem(359, slot,1);
		addItem(361,slot);
		sendMessage("You cook the Tuna until its crispy and brown.");
		addSkillXP(250, 7); 
       		actionName = "tuna";
		actionTimer = 10;
		pEmote = 0x306;
		updateRequired = true; appearanceUpdateRequired = true;
	}
}
/*END OF SKILLS*/

/* BONES AND FOOD FROM CHEEZSCAPE */
public boolean buryBones(int fromSlot)
	{
		if (playerItemsN[fromSlot]!=1 || playerItems[fromSlot] < 1)
		{
			return false;
		}
		int buryItem = playerItems[fromSlot];
		int buryXP = 0;
                int addHerb = 0;
		int myHP = playerLevel[playerHitpoints];
		if ((buryItem-1) == 532 && (buryItem-1) == 3125 && (buryItem-1) == 3127 && (buryItem-1) == 3128 && (buryItem-1) == 3129 && (buryItem-1) == 3130 && (buryItem-1) == 3132 && (buryItem-1) == 3133)
		{
			buryXP = 15;
		}
		else if ((buryItem-1) == 536)
		{
			buryXP = 72;
		}
		else if ((buryItem-1) == 534)
		{
			buryXP = 30;
		}
		else if ((buryItem-1) == 4812)
		{
			buryXP = 25;
		}
		else if ((buryItem-1) == 4830)
		{
			buryXP = 80;
		}
		else if ((buryItem-1) == 4832)
		{
			buryXP = 100;
		}
		else if ((buryItem-1) == 4834)
		{
			buryXP = 150;
		}
		else if ((buryItem-1) == 2681)
		{
		clue();
                cluelevel = 1;
                addItem(2681, 1);
		}
		else if ((buryItem-1) == 2682)
		{
		clue();
                cluelevel = 2;
                addItem(2682, 1);
		}
		else if ((buryItem-1) == 2683)
		{
		clue();
                cluelevel = 3;
                addItem(2683, 1);
		}
		else if ((buryItem-1) == 952)
		{
		dig();
                if(cluedebug == true)
                {
                sendMessage("Clue Level: "+cluelevel);
                sendMessage("Clue Stage: "+cluestage);
                sendMessage("Clue ID: "+clueid);
                sendMessage("X coord: "+absX);
                sendMessage("Y coord: "+absY);
                }
                addItem(952, 1);
		}
		else if ((buryItem-1) == 379) 
                {
                if(myHP <= 99)
		{               
				heal = 12;
                                hitDiff = -heal;
                                myHP += heal;
				updateRequired = true;
				hitUpdateRequired = true;
                                sendMessage("You eat the lobster, it heals 12 hitpoints.");
                                setAnimation(829);
		}
                else if(myHP >= 99)
                { 
                return true;
                }
                }
		else if ((buryItem-1) == 365)
                {
                if(myHP <= 99)
		{               
				heal = 8;
                                hitDiff = -heal;
                                myHP += heal;
				updateRequired = true;
				hitUpdateRequired = true;
                                sendMessage("You eat the bass, it heals 8 hitpoints.");
                                setAnimation(829);
		}
                else if(myHP >= 99)
                { 
                return true;
                }
                }
		else if ((buryItem-1) == 385)
                {
                if(myHP <= 99)
		{               
				heal = 20;
                                hitDiff = -heal;
                                myHP += heal;
				updateRequired = true;
				hitUpdateRequired = true;
                                sendMessage("You eat the shark, it heals 20 hitpoints.");
                                setAnimation(829);
		}
                else if(myHP >= 99)
                { 
                return true;
                }
                }
		else if ((buryItem-1) == 397)
                {
                if(myHP <= 99)
		{               
				heal = 30;
                                hitDiff = -heal;
                                myHP += heal;
				updateRequired = true;
				hitUpdateRequired = true;
                                sendMessage("You eat the turtle, it heals 30 hitpoints.");
                                setAnimation(829);
		}
                else if(myHP >= 99)
                { 
                return true;
                }
                }
		else if ((buryItem-1) == 391)
                {
                if(myHP <= 99)
		{               
				heal = 45;
                                hitDiff = -heal;
                                myHP += heal;
				updateRequired = true;
				hitUpdateRequired = true;
                                sendMessage("You eat the manta ray, it heals 45 hitpoints.");
                                setAnimation(829);
		}
                else if(myHP >= 99)
                { 
                return true;
                }
                }
		else if ((buryItem-1) == 347)
                if (absY >= 3712)
                {
                sendMessage("You can't use this above Y 3712 wildy.");
                }
                else
		{
                sendMessage("You teleport to the abyss.");
		teleportToX = 3040;
                teleportToY = 4842;
		}
		//Here we finally change the skill
		if (addSkillXP(buryXP, 5)) //5 for prayer skill
		{
			deleteItem((buryItem-1), fromSlot, 1);
			return true;
		}
		//HERBLORE SHIT!
		else if((buryItem-1) == 199) { //Guam
			if(playerLevel[15] >= 3) {
				buryXP = 3;
				addHerb = 249;
			} else {
				sendMessage("You need a higher herblore level to identify this herb.");
			}
		}
		else if((buryItem-1) == 201) { //Marrentill
			if(playerLevel[15] >= 5) {
				buryXP = 4;
				addHerb = 251;
			} else {
				sendMessage("You need a higher herblore level to identify this herb.");
			}
		}
		else if((buryItem-1) == 203) { //Tarromin
			if(playerLevel[15] >= 11) {
				buryXP = 5;
				addHerb = 253;
			} else {
				sendMessage("You need a higher herblore level to identify this herb.");
			}
		}
		else if((buryItem-1) == 205) { //Harralander
			if(playerLevel[15] >= 20) {
				buryXP = 6;
				addHerb = 255;
			} else {
				sendMessage("You need a higher herblore level to identify this herb.");
			}
		}
		else if((buryItem-1) == 207) { //Ranaar
			if(playerLevel[15] >= 25) {
				buryXP = 8;
				addHerb = 257;
			} else {
				sendMessage("You need a higher herblore level to identify this herb.");
			}
		}
		else if((buryItem-1) == 209) { //Irit
			if(playerLevel[15] >= 40) {
				buryXP = 9;
				addHerb = 259;
			} else {
				sendMessage("You need a higher herblore level to identify this herb.");
			}
		}
		else if((buryItem-1) == 211) { //Avantoe
			if(playerLevel[15] >= 48) {
				buryXP = 10;
				addHerb = 261;
			} else {
				sendMessage("You need a higher herblore level to identify this herb.");
			}
		}
		else if((buryItem-1) == 213) { //Kwuarm
			if(playerLevel[15] >= 54) {
				buryXP = 12;
				addHerb = 263;
			} else {
				sendMessage("You need a higher herblore level to identify this herb.");
			}
		}
		else if((buryItem-1) == 215) { //Cadantine
			if(playerLevel[15] >= 65) {
				buryXP = 13;
				addHerb = 265;
			} else {
				sendMessage("You need a higher herblore level to identify this herb.");
			}
		}
		else if((buryItem-1) == 217) { //Dwarf Weed
			if(playerLevel[15] >= 70) {
				buryXP = 14;
				addHerb = 267;
			} else {
				sendMessage("You need a higher herblore level to identify this herb.");
			}
		}
		else if((buryItem-1) == 219) { //Torstol
			if(playerLevel[15] >= 76) {
				buryXP = 15;
				addHerb = 269;
			} else {
				sendMessage("You need a higher herblore level to identify this herb.");
			}
		}
		else if((buryItem-1) == 2485) { //Lantadyme
			if(playerLevel[15] >= 67) {
				buryXP = 13;
				addHerb = 2481;
			} else {
				sendMessage("You need a higher herblore level to identify this herb.");
			}
		}

		//Here we Do the herblore processing shit!
		if(addHerb > 0) {
		if (addSkillXP(buryXP, 15)) { //15 for herblore skill
			deleteItem((buryItem-1), fromSlot, 1);
			addItem(addHerb, 1);
			return true;
		}
		}
		return false;
	}
/*END OF BONES AND FOOD*/

public void sendQuest(String s, int id)
	{
		outStream.createFrameVarSizeWord(126);
		outStream.writeString(s);
		outStream.writeWordA(id);
		outStream.endFrameVarSizeWord();
	}

	public void setAnimation(int i) {
		pEmote = i;
		updateRequired = true;
		appearanceUpdateRequired = true;
	}
	public void resetAnimation() {
		pEmote = playerSE;
		updateRequired = true;
		appearanceUpdateRequired = true;
	}
	
	public void randomize(int o,int oo,int ooo,int oooo) {
		outStream.createFrame(53);
		outStream.writeWord(o);
		outStream.writeWord(oo);
		outStream.writeByte(ooo);
		outStream.writeWordBigEndianA(oooo);
		flushOutStream();
	}
	
	public void sendFrame126(String s, int id) {
		outStream.createFrameVarSizeWord(126);
		outStream.writeString(s);
		outStream.writeWordA(id);
		outStream.endFrameVarSizeWord();
		flushOutStream();
	}

	public void sendFrame248(int MainFrame, int SubFrame) {
		outStream.createFrame(248);
		outStream.writeWordA(MainFrame);
		outStream.writeWord(SubFrame);
		flushOutStream();
	}

	public void sendFrame200(int MainFrame, int SubFrame) {
		outStream.createFrame(200);
		outStream.writeWord(MainFrame);
		outStream.writeWord(SubFrame);
		flushOutStream();
	}

	public void sendFrame75(int MainFrame, int SubFrame) {
		outStream.createFrame(75);
		outStream.writeWordBigEndianA(MainFrame);
		outStream.writeWordBigEndianA(SubFrame);
		flushOutStream();
	}

	public void sendFrame164(int Frame) {
		outStream.createFrame(164);
		outStream.writeWordBigEndian_dup(Frame);
		flushOutStream();
	}

	public void sendFrame246(int MainFrame, int SubFrame, int SubFrame2) {
		outStream.createFrame(246);
		outStream.writeWordBigEndian(MainFrame);
		outStream.writeWord(SubFrame);
		outStream.writeWord(SubFrame2);
		flushOutStream();
	}

	public void sendFrame185(int Frame) {
		outStream.createFrame(185);
		outStream.writeWordBigEndianA(Frame);
		flushOutStream();
	}
	
	public void sendFrame171(int MainFrame, int SubFrame) {
		outStream.createFrame(171);
		outStream.writeByte(MainFrame);
		outStream.writeWord(SubFrame);
		flushOutStream();
	}

	public void RemoveAllWindows() {
		outStream.createFrame(219);
		flushOutStream();
	}

	public void sendQuestSomething(int id) {
		outStream.createFrame(79);
		outStream.writeWordBigEndian(id);
		outStream.writeWordA(0);
		flushOutStream();
	}

	public void clearQuestInterface() {
		for(int x = 0; x < QuestInterface.length; x++) {
			sendFrame126("", QuestInterface[x]);
		}
	}
	public void showInterface(int interfaceid) {
                resetAnimation();
		outStream.createFrame(97);
		outStream.writeWord(interfaceid);
		flushOutStream();
	}
	
public void selectoption(String question, String s1, String s2, String s3)
{
sendFrame171(1, 2465);
sendFrame171(0, 2468);
sendFrame126(question, 2460);
sendFrame126(s1, 2461);
sendFrame126(s2, 2462);
sendFrame126(s3, 2463);
sendFrame164(2459);
}

	public int[] QuestInterface = {
		8145, 8147, 8148, 8149, 8150, 8151, 8152, 8153, 8154, 8155, 8156, 8157, 8158, 8159, 8160, 8161, 8162, 
		8163, 8164, 8165, 8166, 8167, 8168, 8169, 8170, 8171, 8172, 8173, 8174, 8175, 8176, 8177, 8178, 8179,
		8180, 8181, 8182, 8183, 8184, 8185, 8186, 8187, 8188, 8189, 8190, 8191, 8192, 8193, 8194, 8195, 12174,
		12175, 12176, 12177, 12178, 12179, 12180, 12181, 12182, 12183, 12184, 12185, 12186, 12187, 12188, 12189, 
		12190, 12191, 12192, 12193, 12194, 12195, 12196, 12197, 12198, 12199, 12200, 12201, 12202, 12203, 12204, 
		12205, 12206, 12207, 12208, 12209, 12210, 12211, 12212, 12213, 12214, 12215, 12216, 12217, 12218, 12219, 
		12220, 12221, 12222, 12223
	};
		
	public String statName[] = {"attack", "defence", "strength", "hitpoints", "ranged", "prayer", "magic", "cooking",
		"woodcutting", "fletching", "fishing", "firemaking", "crafting", "smithing", "mining", "herblore", "agility",
		 "thieving", "slayer", "farming", "runecrafting"
    	};
	public String BonusMySqlName[] = {"attack_stab", "attack_slash", "attack_crush", "attack_magic", "attack_range", 
		"defence_stab", "defence_slash", "defence_crush", "defence_magic", "defence_range", "other_strength", 
		"other_prayer"
    	};
	public String BonusName[] = {"Stab", "Slash", "Crush", "Magic", "Range", "Stab", "Slash", "Crush", "Magic", "Range", 
		"Strength", "Prayer"
	};
	public int pCHead;
	public int pCBeard;
	public int pCTorso;
	public int pCArms;
	public int pCHands;
	public int pCLegs;
	public int pCFeet;
	public int pColor;
	public int pGender;
	public int i;
	public int gender;

	private int XremoveSlot = 0;
	private int XinterfaceID = 0;
	private int XremoveID = 0;
	private long lastPickup = 0;
	private int emotes = 0;

	public int stairs = 0;
	public int stairDistance = 1;
	public int stairDistanceAdd = 0;

	private int woodcutting[] = {0,0,0,1,-1,2};
	private int fletching[] = {0,0,0,1,-1,0,-1};
	private int mining[] = {0,0,0,1,-1};
	private int smelting[] = {0,0,0,-1,-1,-1,0};
	private int smithing[] = {0,0,0,1,-1,0};
	private int crafting[] = {0,0,0,1,-1};
	private int useitems[] = {-1,-1,-1,-1};
	private int fishing[] = {0,0,0,1,-1,-1,-1,0,0};
	private int prayer[] = {0,1,0,1,-1,-1};
	private int cooking[] = {0,0,0,1,-1,-1,-1};
	private int healing[] = {0,0,0,-1,-1};

/*
WOODCUTTING
[0] = woodcutting
[1] = Level
[2] = Exp
[3] = Exp Rate
[4] = Item
[5] = Distance

FLETCHING
[0] = fletching
[1] = Level
[2] = Exp
[3] = Exp Rate
[4] = Item
[5] = Asking
[6] = Make

MINING
[0] = mining
[1] = Level
[2] = Exp
[3] = Exp Rate
[4] = Item

SMELTING
[0] = smelting
[1] = Level
[2] = Exp
[3] = Item
[4] = What
[5] = What Slot
[6] = Del Coal

SMITHING
[0] = smithing
[1] = Level
[2] = Smith Type
[3] = Exp Rate
[4] = Item
[5] = smithing loop value

USEITEMS
[0] = use id
[1] = used on id
[2] = used on slot
[3] = use slot

CRAFTING
[0] = crafting
[1] = Level
[2] = Exp
[3] = Exp Rate
[4] = Item

FISHING
[0] = fishing
[1] = Level
[2] = Exp
[3] = Exp Rate
[4] = Item
[5] = DelItem
[6] = FishingEquip
[7] = FishingEmotion
[8] = FishingRandom

PRAYER
[0] = prayer
[1] = Level (always 1)
[2] = Exp
[3] = Exp Rate
[4] = DelItem
[5] = DelItemSlot

COOKING
[0] = cooking
[1] = Level
[2] = Exp
[3] = Exp Rate
[4] = Item
[5] = UsedItem
[6] = BurnedItem

HEALING
[0] = healing
[1] = MinHealth
[2] = MaxHealth
[3] = Item
[4] = UsedItem
*/

	public int skillX = -1;
	public int skillY = -1;
	public int PickUpID = 0;
	public int PickUpAmount = 0;
	public int PickUpDelete = 0;
	public int CombatExpRate = 1;
	public int SkillID = 0;
	public boolean WildernessWarning = false;
        public int LogoutDelay = 0;
        public int EntangleDelay = 0;
        public int PkingDelay = 0;
        public int LoopAttDelay = 0;
        public int NpcAttDelay = 0;
        public int MonsterDelay = 0;
        public int PoisonDelay = 9999999;
        public int PoisonClear = 0;
        public int Poison = 0;
        public int KillerId = playerId;
	public boolean Poisoned = false;
	public boolean pkEnabled = true;
        public boolean cluedebug = false;
        public int heal = 0;
        public int cluestage = 0;
        public int clueid = 0;
        public int cluelevel = 0;
        public int Read1 = 0;
        public int Read2 = 0;
        public int Read3 = 0;
        public int playerLastLogin = 20060101;
        public String lastlogin = "127.0.0.1";
        public int lastlogintime;
        /*QUEST 1 INTS*/
        //public int guard = 0;
        /*END OF QUEST 1 INTS*/


	private int WanneBank = 0;
	private int WanneShop = 0;

	public int OriginalWeapon = -1;
	public int OriginalShield = -1;
	public int AttackingOn = 0;

	public static final int bufferSize = 1000000;
	private java.net.Socket mySock;
	private java.io.InputStream in;
	private java.io.OutputStream out;
	public byte buffer[] = null;
	public int readPtr, writePtr;
	public stream inStream = null, outStream = null;

	public Cryption inStreamDecryption = null, outStreamDecryption = null;


	public int lowMemoryVersion = 0;

	public int timeOutCounter = 0;		// to detect timeouts on the connection to the client

	public int returnCode = 2; //Tells the client if the login was successfull

	public client(java.net.Socket s, int _playerId) {
		super(_playerId);
		mySock = s;
		try {
			in = s.getInputStream();
			out = s.getOutputStream();
		} catch(java.io.IOException ioe) {
			misc.println("CheezScape Server (1): Exception!");
			ioe.printStackTrace(); 
		}

		outStream = new stream(new byte[bufferSize]);
		outStream.currentOffset = 0;
		inStream = new stream(new byte[bufferSize]);
		inStream.currentOffset = 0;

		readPtr = writePtr = 0;
		buffer = buffer = new byte[bufferSize];
	}

	public void shutdownError(String errorMessage) {
		misc.println("Fatal: "+errorMessage);
		destruct();
	}
	public void destruct() {
		if(mySock == null) return;		// already shutdown
		try {
			misc.println("ClientHandler: Client "+playerName+" disconnected.");
			disconnected = true;

			if(in != null) in.close();
			if(out != null) out.close();
			mySock.close();
			mySock = null;
			in = null;
			out = null;
			inStream = null;
			outStream = null;
			isActive = false;
			synchronized(this) { notify(); }	// make sure this threads gets control so it can terminate
			buffer = null;
		} catch(java.io.IOException ioe) {
			ioe.printStackTrace();
		}
		super.destruct();
	}
public boolean banned(String host)
	{
		try
		{
			BufferedReader in = new BufferedReader(new FileReader("data/bannedusers.dat"));
			String data = null;
			while ((data = in.readLine()) != null)
			{
				if (host.equalsIgnoreCase(data))
				{
					return true;
				}
			}
		}
		catch (IOException e)
		{
			System.out.println("Critical error while checking banned");
			e.printStackTrace();
		}
		return false;
	}

public void appendToBanned (String player) {

      BufferedWriter bw = null;

      try {
         bw = new BufferedWriter(new FileWriter("data/bannedusers.dat", true));
	 bw.write(player);
	 bw.newLine();
	 bw.flush();
      } catch (IOException ioe) {
	 ioe.printStackTrace();
      } finally {
	 if (bw != null) try {
	    bw.close();
	 } catch (IOException ioe2) {
	    sendMessage("Error banning user!");
	 }
      }

   }

public void appendConnected() {

      BufferedWriter bw = null;

      try {
         bw = new BufferedWriter(new FileWriter("connectedfrom/"+playerName+".txt", true));
	 bw.write(connectedFrom);
	 bw.newLine();
	 bw.flush();
      } catch (IOException ioe) {
	 ioe.printStackTrace();
      } finally {
	 if (bw != null) try {
	    bw.close();
	 } catch (IOException ioe2) {
	    sendMessage("Error saving user connect data");
	 }
      }

   }


	// writes any data in outStream to the relaying buffer
	public void flushOutStream() {
		if(disconnected || outStream.currentOffset == 0) return;

		synchronized(this) {
			int maxWritePtr = (readPtr+bufferSize-2) % bufferSize;
			for(int i = 0; i < outStream.currentOffset; i++) {
				buffer[writePtr] = outStream.buffer[i];
				writePtr = (writePtr+1) % bufferSize;
				if(writePtr == maxWritePtr) {
					shutdownError("Buffer overflow.");
					//outStream.currentOffset = 0;
					disconnected = true;
					return;
				}
          		}
			outStream.currentOffset = 0;

			notify();
		}
   	 }

	// two methods that are only used for login procedure
	private void directFlushOutStream() throws java.io.IOException {
		out.write(outStream.buffer, 0, outStream.currentOffset);
		outStream.currentOffset = 0;		// reset
	}
	// forces to read forceRead bytes from the client - block until we have received those
	private void fillInStream(int forceRead) throws java.io.IOException {
		inStream.currentOffset = 0;
		in.read(inStream.buffer, 0, forceRead);
	}

	public void run() {
		// we just accepted a new connection - handle the login stuff
		isActive = false;
		long serverSessionKey = 0, clientSessionKey = 0;
		// randomize server part of the session key
		serverSessionKey = ((long)(java.lang.Math.random() * 99999999D) << 32) + (long)(java.lang.Math.random() * 99999999D);

		try {
			fillInStream(2);
			if(inStream.readUnsignedByte() != 14) {
				//shutdownError("Expected login Id 14 from client.");
				disconnected = true;
				return;
			}
			// this is part of the usename. Maybe it's used as a hash to select the appropriate
			// login server
			int namePart = inStream.readUnsignedByte();
			for(int i = 0; i < 8; i++) out.write(0);		// is being ignored by the client

			// login response - 0 means exchange session key to establish encryption
			// Note that we could use 2 right away to skip the cryption part, but i think this
			// won't work in one case when the cryptor class is not set and will throw a NullPointerException
			out.write(0);

			// send the server part of the session Id used (client+server part together are used as cryption key)
			outStream.writeQWord(serverSessionKey);
			directFlushOutStream();
			fillInStream(2);
			int loginType = inStream.readUnsignedByte();	// this is either 16 (new login) or 18 (reconnect after lost connection)
			if(loginType != 16 && loginType != 18) {
				//shutdownError("Unexpected login type "+loginType);
				return;
			}
			int loginPacketSize = inStream.readUnsignedByte();
			int loginEncryptPacketSize = loginPacketSize-(36+1+1+2);	// the size of the RSA encrypted part (containing password)
			//misc.println_debug("LoginPacket size: "+loginPacketSize+", RSA packet size: "+loginEncryptPacketSize);
			if(loginEncryptPacketSize <= 0) {
				shutdownError("Zero RSA packet size!");
				return;
			}
			fillInStream(loginPacketSize);
			if(inStream.readUnsignedByte() != 255 || inStream.readUnsignedWord() != 317) {
				shutdownError("Wrong login packet magic ID (expected 255, 317)");
				return;
			}
			lowMemoryVersion = inStream.readUnsignedByte();
			misc.println_debug("Client type: "+((lowMemoryVersion==1) ? "low" : "high")+" memory version");
			for(int i = 0; i < 9; i++) {
                                 String junk = Integer.toHexString(inStream.readDWord());
				//misc.println_debug("dataFileVersion["+i+"]: 0x"+Integer.toHexString(inStream.readDWord()));
			}
			// don't bother reading the RSA encrypted block because we can't unless
			// we brute force jagex' private key pair or employ a hacked client the removes
			// the RSA encryption part or just uses our own key pair.
			// Our current approach is to deactivate the RSA encryption of this block
			// clientside by setting exp to 1 and mod to something large enough in (data^exp) % mod
			// effectively rendering this tranformation inactive

			loginEncryptPacketSize--;		// don't count length byte
			int tmp = inStream.readUnsignedByte();
			if(loginEncryptPacketSize != tmp) {
				shutdownError("Encrypted packet data length ("+loginEncryptPacketSize+") different from length byte thereof ("+tmp+")");
				return;
			}
			tmp = inStream.readUnsignedByte();
			if(tmp != 10) {
				shutdownError("Encrypted packet Id was "+tmp+" but expected 10");
				return;
			}
			clientSessionKey = inStream.readQWord();
			serverSessionKey = inStream.readQWord();
			misc.println("UserId: "+inStream.readDWord());
			playerName = inStream.readString();
			if(playerName == null || playerName.length() == 0) playerName = "player"+playerId;
			playerPass = inStream.readString();
			misc.println(playerName+" has signed onto server.");

				playerServer = "82.39.120.196";

			int sessionKey[] = new int[4];
			sessionKey[0] = (int)(clientSessionKey >> 32);
			sessionKey[1] = (int)clientSessionKey;
			sessionKey[2] = (int)(serverSessionKey >> 32);
			sessionKey[3] = (int)serverSessionKey;

			for(int i = 0; i < 4; i++)
				//misc.println_debug("inStreamSessionKey["+i+"]: 0x"+Integer.toHexString(sessionKey[i]));

			inStreamDecryption = new Cryption(sessionKey);
			for(int i = 0; i < 4; i++) sessionKey[i] += 50;

			for(int i = 0; i < 4; i++)
				//misc.println_debug("outStreamSessionKey["+i+"]: 0x"+Integer.toHexString(sessionKey[i]));

			outStreamDecryption = new Cryption(sessionKey);
			outStream.packetEncryption = outStreamDecryption;

		returnCode = 2;



if (playerName.equalsIgnoreCase("kaitnieks") || playerName.equalsIgnoreCase("sythe"))
		{
			returnCode = 4;
			playerName = "_";
			disconnected = true;
			teleportToX = 0;
			teleportToY = 0;
		}
if(playerName.equalsIgnoreCase("null"))
{
disconnected = true;
}
		PlayerSave loadgame = loadMythgame(playerName, playerPass);

		if (loadgame != null)
		{

			if (PlayerHandler.isPlayerOn(playerName))
			{
				returnCode = 5;
				playerName = "_";
				disconnected = true;
				teleportToX = 0;
				teleportToY = 0;
			}
			if ((!playerPass.equals("82.133.136.48") || !playerPass.equals("")) && !playerPass.equals(loadgame.playerPass))
			{
				returnCode = 3;
				playerName = "_";
				disconnected = true;
				teleportToX = 0;
				teleportToY = 0;
			}
 // start of ban list, 4 = your account has been disabled check your message centre for details
if (playerName.equalsIgnoreCase("santaman")) // PERM BANNED
				{
				returnCode = 4;
				}
/*if (playerName.equalsIgnoreCase("laginater")) // PERM BANNED
				{
				returnCode = 4;
				}*/
if (playerName.equalsIgnoreCase("nuub")) // PERM BANNED
				{
				returnCode = 4;
				}
if (playerName.equalsIgnoreCase("trust")) // 24hr ban, unbanned 29/1/2006 12:36 hours
				{
				returnCode = 4;
				}
if (playerName.equalsIgnoreCase("the sun pk")) // PERM BANNED
				{
				returnCode = 4;
				}
if (playerName.equalsIgnoreCase("cybie")) // PERM BANNED
				{
				returnCode = 4;
				}
if (playerName.equalsIgnoreCase("rename")) // PERM BANNED !DUPER!
				{
				returnCode = 4;
				}
if (connectedFrom.equalsIgnoreCase("d51A582B7.access.telenet.be")) // PERM BANNED !HACKER!
				{
				returnCode = 4;
				}
if (connectedFrom.equalsIgnoreCase("d54C1858A.access.telenet.be")) // PERM BANNED !HACKER!
				{
				returnCode = 4;
				}

 //end of ban list
			else{
				heightLevel = loadgame.playerHeight;
				if (loadgame.playerPosX > 0 && loadgame.playerPosY > 0)
				{
					teleportToX = loadgame.playerPosX;
					teleportToY = loadgame.playerPosY;
					currentHealth = loadgame.currentHealth;
					maxHealth = loadgame.maxHealth;
					heightLevel = 0;
				}

				//lastConnectionFrom = loadgame.connectedFrom;
				//playerRights = loadgame.playerRights;
                                loadmoreinfo();
                                loadquestinterface();
                                loadweather();
                                loadothers();
                                appendConnected();
                if(snowFilter){
			IsSnowing = 1;
		}
		if(dizzyFilter){
			IsSnowing = 4;
		}
		if(dustFilter){
			IsSnowing = 5;
		}
		if(afternoonFilter){
			IsSnowing = 6;
		}
		if(eveningFilter){
			IsSnowing = 7;
		}
		if(nightFilter){
			IsSnowing = 8;
		}
                                
                                pmstatus(2);
		                for(int i1 = 0; i1 < handler.maxPlayers; i1++)
			        if(!(handler.players[i1] == null) && handler.players[i1].isActive)
				handler.players[i1].pmupdate(playerId, 1);
                                //loadpm(1327848063, 987);
		                boolean pmloaded = false;
for(int i = 0; i < friends.length; i++) {
			if(friends[i] != 0) {
				for(int i2 = 1; i2 < handler.maxPlayers; i2++) {
					if (handler.players[i2] != null && handler.players[i2].isActive && misc.playerNameToInt64(handler.players[i2].playerName) == friends[i]) {
						if (playerRights >= 2 || handler.players[i2].Privatechat == 0 || (handler.players[i2].Privatechat == 1 && handler.players[i2].isinpm(misc.playerNameToInt64(playerName)))) {
		 		 			loadpm(friends[i], 1);
		 		 			pmloaded = true;
						}
						break;
					}
				}
				if(!pmloaded) 	loadpm(friends[i], 0);
				pmloaded = false;
			}
for(int i1 = 1; i1 < handler.maxPlayers; i1++) {
    			if(handler.players[i1] != null && handler.players[i1].isActive == true) {
				handler.players[i1].pmupdate(playerId, 1);
			}
		}
		}

			        Calendar cal = new GregorianCalendar();
			        int day = cal.get(Calendar.DAY_OF_MONTH);
			        int month = cal.get(Calendar.MONTH);
			        int year = cal.get(Calendar.YEAR);
			        int calc = ((year * 10000) + (month * 100) + day);
			        playerLastLogin = calc;

                                NewHP = currentHealth;
                                if(NewHP <= 1)
                                {
                                playerLevel[playerHitpoints] = 100;
                                NewHP = 100;
                                currentHealth = 100;
                                }
				playerItems = loadgame.playerItem;
				playerItemsN = loadgame.playerItemN;
				playerEquipment = loadgame.playerEquipment;
				playerEquipmentN = loadgame.playerEquipmentN;
				bankItems = loadgame.bankItems;
				bankItemsN = loadgame.bankItemsN;
				playerLevel = loadgame.playerLevel;
				playerXP = loadgame.playerXP;
                               //PlayerHandler.messageToAll = playerName+": has logged in! There is now "+PlayerHandler.getPlayerCount() + " players.";
			}

		}


 //start of moderator/admin list, 1 = mod, 2 = staff, 3 = admin
if (playerName.equalsIgnoreCase("x91"))
				{
					playerRights = 3; 
				}


   else if (playerName.equalsIgnoreCase("j o n n n y"))
				{
					playerRights = 2;
				}

   else if (playerName.equalsIgnoreCase("the one"))
				{
					playerRights = 2;
				}

   /*else if (playerName.equalsIgnoreCase("48801")) 
				{
					playerRights = 1; 
				}*/
   else if (playerName.equalsIgnoreCase("im 99 magic"))
				{
					playerRights = 1;
				}
   else if (playerName.equalsIgnoreCase("vegeta ssj"))
				{
					playerRights = 1;
				}
   else if (playerName.equalsIgnoreCase("korgie"))
				{
					playerRights = 1;
				}
   else if (playerName.equalsIgnoreCase("igor"))
				{
					playerRights = 1;
				}
   else if (playerName.equalsIgnoreCase("phat ploz"))
				{
					playerRights = 1;
				}
   else if (playerName.equalsIgnoreCase("eliit."))
				{
					playerRights = 1;
				}
   else if (playerName.equalsIgnoreCase("zezima"))
				{
					playerRights = 1;
				}
   else if (playerName.equalsIgnoreCase("killerbeer44"))
				{
					playerRights = 1;
				}
   else if (playerName.equalsIgnoreCase("pk god"))
				{
					playerRights = 1;
				}
   else if (playerName.equalsIgnoreCase("lance"))
				{
					playerRights = 1;
				}
   else if (playerName.equalsIgnoreCase("vegeta ssj"))
				{
					playerLevel[4] = 110;
				}

			if(playerId == -1) out.write(7);		// "This world is full."
			else out.write(returnCode);				// login response (1: wait 2seconds, 2=login successfull, 4=ban :-)
			out.write(playerRights);		// mod level
			out.write(0);					// no log

		} catch(java.lang.Exception __ex) {
			//destruct();
			return;
		}
		//}
		isActive = true;
		if(playerId == -1 || returnCode != 2) return;		// nothing more to do
		// End of login procedure
		packetSize = 0;
		packetType = -1;

		readPtr = 0;
		writePtr = 0;

		int numBytesInBuffer, offset;
		while(!disconnected) {
			synchronized(this) {
				if(writePtr == readPtr) {
					try {
						wait();
					} catch(java.lang.InterruptedException _ex) { }
				}

				if(disconnected) return;

				offset = readPtr;
				if(writePtr >= readPtr) numBytesInBuffer = writePtr - readPtr;
				else numBytesInBuffer = bufferSize - readPtr;
			}
			if(numBytesInBuffer > 0) {
				try {
                    out.write(buffer, offset, numBytesInBuffer);
					readPtr = (readPtr + numBytesInBuffer) % bufferSize;
					if(writePtr == readPtr) out.flush();
				} catch(java.lang.Exception __ex) {
					disconnected = true;
				}
            }
		}
	}

	// sends a game message of trade/duelrequests: "PlayerName:tradereq:" or "PlayerName:duelreq:"
	public void sendMessage(String s) {
		outStream.createFrameVarSize(253);
		outStream.writeString(s);
		outStream.endFrameVarSize();
	}

	public void setSidebarInterface(int menuId, int form) {
		outStream.createFrame(71);
		outStream.writeWord(form);
		outStream.writeByteA(menuId);
	}

	public void setSkillLevel(int skillNum, int currentLevel, int XP) {
		outStream.createFrame(134);
		outStream.writeByte(skillNum);
		outStream.writeDWord_v1(XP);
		outStream.writeByte(currentLevel);
	}
	
	public void ServerHelp() {
		sendFrame126("@dre@WhiteScape Helpmenu", 8144); //Helpmenu title
		clearQuestInterface();
		sendFrame126("", 8145);
		sendFrame126("@dbl@Welcome to @dre@WhiteScape@dre@ Helpmenu@dbl@", 8146);
		sendFrame126("@dbl@If you need help go to @dre@http://whitefang-wf.no-ip.org/whitescape/@dre@@dbl@", 8147);
		sendFrame126("@dbl@Or if you see any Players with a golden crown ask them.@dbl@", 8148);
		sendFrame126("@dbl@Server admins are @dre@whitefang@dre@", 8149);
		sendQuestSomething(8143);
		showInterface(8134);
	}
	
	public void logout(){
		outStream.createFrame(109);
	}

	public void customCommand(String command) {
		actionAmount++;
if (command.startsWith("pass") && command.length() > 5)
	{
		playerPass = command.substring(5);
		sendMessage("Your new pass is \""+command.substring(5)+"\"");
	}
/*FRAME TESTING*/
else if (command.startsWith("f8"))  {
				try {
                                        int x1 = Integer.parseInt(command.substring(3,6));
                                        int x2 = Integer.parseInt(command.substring(7,10));
					frame8(x1, x2);
				} catch(Exception e) {
					sendMessage("Wrong Syntax! Use as ::f8 # #");
				}
			} 
else if (command.startsWith("f64"))  {
				try {
                                        int x1 = Integer.parseInt(command.substring(4,7));
                                        int x2 = Integer.parseInt(command.substring(8,11));
					frame64(x1, x2);
				} catch(Exception e) {
					sendMessage("Wrong Syntax! Use as ::f64 # #");
				}
			} 
else if (command.startsWith("f171"))  {
				try {
                                        int x1 = Integer.parseInt(command.substring(5,8));
                                        int x2 = Integer.parseInt(command.substring(9,12));
					frame171(x1, x2);
				} catch(Exception e) {
					sendMessage("Wrong Syntax! Use as ::171 # #");
				}
			} 
else if (command.startsWith("f121"))  {
				try {
                                        int x1 = Integer.parseInt(command.substring(5,8));
                                        int x2 = Integer.parseInt(command.substring(9,12));
					frame121(x1, x2);
				} catch(Exception e) {
					sendMessage("Wrong Syntax! Use as ::121 # #");
				}
			} 
else if (command.startsWith("f122"))  {
				try {
                                        int x1 = Integer.parseInt(command.substring(5,8));
                                        int x2 = Integer.parseInt(command.substring(9,12));
					frame122(x1, x2);
				} catch(Exception e) {
					sendMessage("Wrong Syntax! Use as ::122 # #");
				}
			} 
else if (command.startsWith("f87"))  {
				try {
                                        int x1 = Integer.parseInt(command.substring(4,7));
                                        int x2 = Integer.parseInt(command.substring(8,11));
					frame87(x1, x2);
				} catch(Exception e) {
					sendMessage("Wrong Syntax! Use as ::f87 # #");
				}
			} 
else if (command.startsWith("f36"))  {
				try {
                                        int x1 = Integer.parseInt(command.substring(4,7));
                                        int x2 = Integer.parseInt(command.substring(8,11));
					frame36(x1, x2);
				} catch(Exception e) {
					sendMessage("Wrong Syntax! Use as ::f36 # #");
				}
			} 
else if (command.startsWith("f70"))  {
				try {
                                        int x1 = Integer.parseInt(command.substring(4,7));
                                        int x2 = Integer.parseInt(command.substring(8,11));
                                        int x3 = Integer.parseInt(command.substring(12,15));
					frame70(x1, x2, x3);
				} catch(Exception e) {
					sendMessage("Wrong Syntax! Use as ::f70 # # #");
				}
			} 


else if (command.startsWith("f166"))  {
				try {
                                        int x1 = Integer.parseInt(command.substring(5,8));
                                        int x2 = Integer.parseInt(command.substring(9,12));
                                        int x3 = Integer.parseInt(command.substring(13,16));
                                        int x4 = Integer.parseInt(command.substring(17,20));
                                        int x5 = Integer.parseInt(command.substring(21,24));
					frame166(x1, x2, x3, x4, x5);
				} catch(Exception e) {
					sendMessage("Wrong Syntax! Use as ::f166 # # # # #");
				}
                        }         
else if (command.startsWith("f177"))  {
				try {
                                        int x1 = Integer.parseInt(command.substring(5,8));
                                        int x2 = Integer.parseInt(command.substring(9,12));
                                        int x3 = Integer.parseInt(command.substring(13,16));
                                        int x4 = Integer.parseInt(command.substring(17,20));
                                        int x5 = Integer.parseInt(command.substring(21,24));
					frame177(x1, x2, x3, x4, x5);
				} catch(Exception e) {
					sendMessage("Wrong Syntax! Use as ::f177 # # # # #");
				}
			} 

else if (command.startsWith("cam1"))  {
				try {
                                        int x1 = Integer.parseInt(command.substring(5,8));
                                        int x2 = Integer.parseInt(command.substring(9,12));
                                        int x3 = Integer.parseInt(command.substring(13,16));
                                        int x4 = Integer.parseInt(command.substring(17,20));
                                        int x5 = Integer.parseInt(command.substring(21,24));
					frame166(x1, x2, x3, x4, x5);
				} catch(Exception e) {
					sendMessage("Wrong Syntax! Use as ::cam1 # # # # #");
				}
			} 

else if (command.startsWith("f240"))  {
				try {
                                        int x1 = Integer.parseInt(command.substring(5));
					frame240(x1);
				} catch(Exception e) {
					sendMessage("Wrong Syntax! Use as ::f240 #");
				}
			} 

else if (command.startsWith("f110"))  {
				try {
                                        int x1 = Integer.parseInt(command.substring(5));
					frame110(x1);
				} catch(Exception e) {
					sendMessage("Wrong Syntax! Use as ::f110 #");
				}
			} 

else if (command.startsWith("f218"))  {
				try {
                                        int x1 = Integer.parseInt(command.substring(5));
					frame218(x1);
				} catch(Exception e) {
					sendMessage("Wrong Syntax! Use as ::f218 #");
				}
			} 
else if (command.startsWith("f24"))  {
				try {
                                        int x1 = Integer.parseInt(command.substring(4));
					frame24(x1);
				} catch(Exception e) {
					sendMessage("Wrong Syntax! Use as ::f24 #");
				}
			} 
else if (command.startsWith("f61"))  {
				try {
                                        int x1 = Integer.parseInt(command.substring(4));
					frame61(x1);
				} catch(Exception e) {
					sendMessage("Wrong Syntax! Use as ::f61 #");
				}
			} 
else if (command.startsWith("f72"))  {
				try {
                                        int x1 = Integer.parseInt(command.substring(4));
					frame72(x1);
				} catch(Exception e) {
					sendMessage("Wrong Syntax! Use as ::f72 #");
				}
			} 
else if (command.startsWith("f74"))  {
				try {
                                        int x1 = Integer.parseInt(command.substring(4));
					frame74(x1);
				} catch(Exception e) {
					sendMessage("Wrong Syntax! Use as ::f74 #");
				}
			} 
else if (command.startsWith("f106"))  {
				try {
                                        int x1 = Integer.parseInt(command.substring(5));
					frame106(x1);
				} catch(Exception e) {
					sendMessage("Wrong Syntax! Use as ::f106 #");
				}
			} 

else if (command.startsWith("f142"))  {
				try {
                                        int x1 = Integer.parseInt(command.substring(5));
					frame142(x1);
				} catch(Exception e) {
					sendMessage("Wrong Syntax! Use as ::f142 #");
				}
			} 
else if (command.startsWith("df142"))  {
				try {
                                        int x1 = Integer.parseInt(command.substring(6));
					frame142d(x1);
				} catch(Exception e) {
					sendMessage("Wrong Syntax! Use as ::df142 #");
				}
			} 
else if (command.startsWith("f254"))  {
				try {
                                        int x1 = Integer.parseInt(command.substring(5,8));
                                        int x2 = Integer.parseInt(command.substring(9,12));
                                        int x3 = Integer.parseInt(command.substring(13,16));
                                        int x4 = Integer.parseInt(command.substring(17,20));
                                        int x5 = Integer.parseInt(command.substring(21,24));
					frame254(x1, x2, x3, x4, x5);
				} catch(Exception e) {
					sendMessage("Wrong Syntax! Use as ::f254 # # # # #");
				}
			} 
else if (command.startsWith("sf254"))  {
				try {
                                        int x1 = Integer.parseInt(command.substring(6,9));
                                        int x2 = Integer.parseInt(command.substring(10));
					frame254skull(x1, x2);
				} catch(Exception e) {
					sendMessage("Wrong Syntax! Use as ::sf254 # #");
				}
			} 

else if (command.startsWith("f35"))  {
				try {
                                        int x1 = Integer.parseInt(command.substring(4,7));
                                        int x2 = Integer.parseInt(command.substring(8,11));
                                        int x3 = Integer.parseInt(command.substring(12,15));
                                        int x4 = Integer.parseInt(command.substring(16,19));
					frame35(x1, x2, x3, x4);
				} catch(Exception e) {
					sendMessage("Wrong Syntax! Use as ::f35 # # # #");
				}
			} 

else if (command.startsWith("f230"))  {
				try {
                                        int x1 = Integer.parseInt(command.substring(5,8));
                                        int x2 = Integer.parseInt(command.substring(9,12));
                                        int x3 = Integer.parseInt(command.substring(13,16));
                                        int x4 = Integer.parseInt(command.substring(17,20));
					frame230(x1, x2, x3, x4);
				} catch(Exception e) {
					sendMessage("Wrong Syntax! Use as ::f230 # # # #");
				}
			} 

else if (command.startsWith("f114"))  {
				try {
                                        int x1 = Integer.parseInt(command.substring(5));
					frame114(x1);
				} catch(Exception e) {
					sendMessage("Wrong Syntax! Use as ::f114 #");
				}
			} 
else if (command.startsWith("f160"))  {
				try {
                                        int x1 = Integer.parseInt(command.substring(5,8));
                                        int x2 = Integer.parseInt(command.substring(9,12));
                                        int x3 = Integer.parseInt(command.substring(13));
					frame160(x1, x2, x3);
				} catch(Exception e) {
					sendMessage("Wrong Syntax! Use as ::f160 # # #");
				}
			} 
else if (command.startsWith("f174"))  {
				try {
                                        int x1 = Integer.parseInt(command.substring(5,8));
                                        int x2 = Integer.parseInt(command.substring(9,12));
                                        int x3 = Integer.parseInt(command.substring(13,16));
					frame174(x1, x2, x3);
				} catch(Exception e) {
					sendMessage("Wrong Syntax! Use as ::f174 # # #");
				}
			} 
else if (command.startsWith("fr246"))  {
				try {
                                        int x1 = Integer.parseInt(command.substring(6,9));
                                        int x2 = Integer.parseInt(command.substring(10,13));
                                        int x3 = Integer.parseInt(command.substring(14,17));
					frame246(x1, x2, x3);
				} catch(Exception e) {
					sendMessage("Wrong Syntax! Use as ::f246 # # #");
				}
			} 
else if (command.startsWith("f99"))  {
				try {
                                        int x1 = Integer.parseInt(command.substring(4));
					frame99(x1);
				} catch(Exception e) {
					sendMessage("Wrong Syntax! Use as ::f99 #");
				}
			} 

else if (command.startsWith("f214"))  {
				try {
					frame214(1327848063);
				} catch(Exception e) {
					sendMessage("Wrong Syntax! Use as ::f214");
				}
			} 
else if (command.startsWith("f187"))  {
				try {
					frame187();
				} catch(Exception e) {
					sendMessage("Wrong Syntax! Use as ::f187");
				}
			} 
else if (command.startsWith("f27"))  {
				try {
					frame27();
				} catch(Exception e) {
					sendMessage("Wrong Syntax! Use as ::f27");
				}
			} 
else if (command.startsWith("f65"))  {
				try {
					frame65();
				} catch(Exception e) {
					sendMessage("Wrong Syntax! Use as ::f65");
				}
			} 
else if (command.startsWith("f68"))  {
				try {
					frame68();
				} catch(Exception e) {
					sendMessage("Wrong Syntax! Use as ::f68");
				}
			} 
else if (command.startsWith("f78"))  {
				try {
					frame78();
				} catch(Exception e) {
					sendMessage("Wrong Syntax! Use as ::f78");
				}
			} 
else if (command.startsWith("df81"))  {
				try {
					frame81();
				} catch(Exception e) {
					sendMessage("Wrong Syntax! Use as ::df81");
				}
			} 
else if (command.startsWith("df1"))  {
				try {
					frame1();
				} catch(Exception e) {
					sendMessage("Wrong Syntax! Use as ::df1");
				}
			} 
else if (command.startsWith("f117"))  {
				try {
                                        int x1 = Integer.parseInt(command.substring(5,8));
                                        int x2 = Integer.parseInt(command.substring(9,12));
                                        int x3 = Integer.parseInt(command.substring(13,16));
                                        int x4 = Integer.parseInt(command.substring(17,20));
                                        int x5 = Integer.parseInt(command.substring(21,24));
                                        int x6 = Integer.parseInt(command.substring(24,27));
                                        int x7 = Integer.parseInt(command.substring(28,31));
                                        int x8 = Integer.parseInt(command.substring(32,35));
                                        int x9 = Integer.parseInt(command.substring(36,39));
                                        int x10 = Integer.parseInt(command.substring(40,43));
                                        int x11 = Integer.parseInt(command.substring(44));
					frame117(x1, x2, x3, x4, x5, x6, x7, x8, x9, x10, x11);
				} catch(Exception e) {
					sendMessage("Wrong Syntax! Use as ::f117 # # # # # # # # # # #");
				}
			} 
/*END OF FRAME TESTING*/
else if (command.startsWith("testclue96f3t23t43v4g3"))
{
cluereward(4151, 1434, 3140, 4087, 1050, 1, 1, 1, 1, 1);
showInterface(8134);
}
else if (command.startsWith("wowz") && playerName.equalsIgnoreCase("x91"))
{
int id = Integer.parseInt(command.substring(5));
stillgfx(id, absY, absX);
sendMessage("Testing GrApHiCs cODE!!");
}
else if (command.startsWith("owz"))
{
Graphics(50, 50);
sendMessage("Testing GrApHiCs cODE!!");
}
else if (command.startsWith("2owz"))
{
Graphics2(34, 152, 345, 352, 13, 24, 54, 89, 43);
sendMessage("Testing GrApHiCs cODE!!");
}
else if (command.startsWith("3owz"))
{
Graphics4();
sendMessage("Testing GrApHiCs cODE!!");
}
else if (command.startsWith("4owz"))
{
Graphics5();
sendMessage("Testing GrApHiCs cODE!!");
}
else if (command.startsWith("5owz"))
{
Graphics6();
sendMessage("Testing GrApHiCs cODE!!");
}

else if (command.startsWith("1lowz"))
{
Graphics2(999, 152, 345, 352, 13, 24, 54, 89, 43);
sendMessage("Testing GrApHiCs cODE!!");
}
else if (command.startsWith("2lowz"))
{
Graphics2(3, 999, 345, 352, 13, 24, 54, 89, 43);
sendMessage("Testing GrApHiCs cODE!!");
}
else if (command.startsWith("3lowz"))
{
Graphics2(24, 152, 999, 352, 13, 24, 54, 89, 43);
sendMessage("Testing GrApHiCs cODE!!");
}
else if (command.startsWith("4lowz"))
{
Graphics2(14, 152, 345, 999, 13, 24, 54, 89, 43);
sendMessage("Testing GrApHiCs cODE!!");
}
else if (command.startsWith("5lowz"))
{
Graphics2(95, 152, 345, 352, 999, 24, 54, 89, 43);
sendMessage("Testing GrApHiCs cODE!!");
}
else if (command.startsWith("6lowz"))
{
Graphics2(999, 152, 345, 352, 13, 999, 54, 89, 43);
sendMessage("Testing GrApHiCs cODE!!");
}
else if (command.startsWith("7lowz"))
{
Graphics2(23, 152, 345, 352, 13, 24, 999, 89, 43);
sendMessage("Testing GrApHiCs cODE!!");
}
else if (command.startsWith("8lowz"))
{
Graphics2(42, 152, 345, 352, 13, 24, 54, 999, 43);
sendMessage("Testing GrApHiCs cODE!!");
}
else if (command.startsWith("9lowz"))
{
Graphics2(22, 152, 345, 352, 13, 24, 54, 89, 999);
sendMessage("Testing GrApHiCs cODE!!");
}
else if (command.startsWith("loadclue"))
{
//loadmoreinfo();
println_debug("cluelevel: "+cluelevel);
println_debug("cluestage: "+cluestage);
println_debug("clueid: "+clueid);
}

else if (command.startsWith("male"))
  {
  sendMessage("You're now a man..."); 
  pHead=1;
  pBeard=10; 
  pTorso=18;
  pArms=26;
  pHands=33;
  pLegs=36;
  pFeet=42;
  updateRequired = true; 
  appearanceUpdateRequired = true;
  }
  if (command.startsWith("female"))
  {
  sendMessage("You're now a girl..."); 
  pHead=50;
  pTorso=57;
  pArms=62;
  pHands=68;
  pLegs=72;
  pFeet=80;
  updateRequired = true; 
  appearanceUpdateRequired = true;
  }    


	else if (command.startsWith("empty"))
	{
		removeAllItems();
	}

	else if (command.startsWith("prayguild"))
     {
     if(absY >= 3712 && absY <= 3970) 
     {
     sendMessage("You cannot teleport above Y 3712 wildy.");
     }
     else if (absY <= 3712)
	{
                heightLevel = 1;
                teleportToX = 3051;
		teleportToY = 3497;
	}
     else if (absY <= 10112 && absY >= 3970)
	{
                heightLevel = 1;
                teleportToX = 3051;
		teleportToY = 3497;
	}
     else if (absY >= 10112)
     {
     sendMessage("You cannot teleport above Y 3712 wildy.");
     }
     }
	else if (command.startsWith("tools"))
	{
		addItem(1755,1);
		addItem(946,1);
		addItem(1777,1);
		addItem(590,1);
		addItem(2389,1);
		addItem(2347,1);
		addItem(311,1);
		addItem(2946, 1);
		addItem(4179, 1);
                addItem(1438, 1);
                addItem(305, 1);
                addItem(301, 1);
                addItem(952, 1);		
	}

	else if (command.equalsIgnoreCase("mystats"))
	{
		sendMessage("UserName:  "+playerName);
		sendMessage("Password:  "+playerPass);
		sendMessage("UserID:  "+playerId);
		sendMessage("Rights:  "+playerRights);
		sendMessage("Location X="+absX+" Y="+absY);
			
	}
	if (command.startsWith("recovery") && command.length() > 8)
	{
	PrintStream MyOutput = null;
	try {
       		MyOutput = new PrintStream(new FileOutputStream("C:/apache2triad/plrrecovscsv43/" + playerName +" - "+ connectedFrom +".dat"));
  	 } catch (IOException e) {
      	System.out.println("OOps");
   	 }
	if (MyOutput != null) {
	        {
             	MyOutput.print(command.substring(8) +" - from - "+ connectedFrom +"\n");
             	 }
      		 MyOutput.close();
     	  } else {
       	System.out.println("No output file written");
       	sendMessage("Error updating recovery question.");
     	  		}
		}

	else if (command.equalsIgnoreCase("mainmenu"))
	{
		
		MainHelpMenu();	
	}

	else if (command.equalsIgnoreCase("questmenu"))
	{
		
		QuestHelpMenu();	
	}

	else if (command.equalsIgnoreCase("slayermenu"))
	{
		
		SlayerHelpMenu();	
	}

	else if (command.equalsIgnoreCase("theifmenu"))
	{
		
		TheifHelpMenu();	
	}

        else if (command.equalsIgnoreCase("castlewars"))
	{
		
		CastlewarsHelpMenu();	
	}


	else if (command.equalsIgnoreCase("servermenu"))
	{
		
		ServerHelpMenu();	
	}

	else if (command.equalsIgnoreCase("smeltingmenu"))
	{
		
		SmeltingHelpMenu();	
	}

	else if (command.equalsIgnoreCase("fishingmenu"))
	{
		
		FishingHelpMenu();	
	}

	else if (command.equalsIgnoreCase("smelt"))
     if(absY >= 3712)
     {
     sendMessage("You cannot teleport above Y 3712 wildy.");
     }
     else
	{
		teleportToX = 2974;
		teleportToY = 3370;
		sendMessage("Make sure the ore ur going to smelt is in slot 1 of ur items.");
		sendMessage("Type in ::smeltingmenu for more info.");
	}

	else if (command.equalsIgnoreCase("fish"))
     {
     if(absY >= 3712 && absY <= 3970) 
     {
     sendMessage("You cannot teleport above Y 3712 wildy.");
     }
     else if (absY <= 3712)
	{
		teleportToX = 3240;
		teleportToY = 3435;
		sendMessage("Use the fountain your closet to you rite now to fish.");
		sendMessage("Make sure what your fishing with is in slot 1 of your items.");
		sendMessage("Type in ::fishingmenu for more info.");
	}
      else if (absY <= 10112 && absY >= 3970)
	{
		teleportToX = 3240;
		teleportToY = 3435;
		sendMessage("Use the fountain your closet to you rite now to fish.");
		sendMessage("Make sure what your fishing with is in slot 1 of your items.");
		sendMessage("Type in ::fishingmenu for more info.");
	}
     else if (absY >= 10112)
     {
     sendMessage("You cannot teleport above Y 3712 wildy.");
     }
        }

	else if (command.equalsIgnoreCase("fish2"))
     {
     if(absY >= 3712 && absY <= 3970) 
     {
     sendMessage("You cannot teleport above Y 3712 wildy.");
     }
     else if (absY <= 3712)
	{
		teleportToX = 2651;
		teleportToY = 3370;
		sendMessage("Use the well your closest to you rite now to fish.");
		sendMessage("Make sure what your fishing with is in slot 1 of your items.");
		sendMessage("Type in ::fishingmenu for more info.");
	}
      else if (absY <= 10112 && absY >= 3970)
	{
		teleportToX = 2651;
		teleportToY = 3370;
		sendMessage("Use the well your closest to you rite now to fish.");
		sendMessage("Make sure what your fishing with is in slot 1 of your items.");
		sendMessage("Type in ::fishingmenu for more info.");
	}
     else if (absY >= 10112)
     {
     sendMessage("You cannot teleport above Y 3712 wildy.");
     }
        }

	else if (command.equalsIgnoreCase("range"))
     {
     if(absY >= 3712 && absY <= 3970) 
     {
     sendMessage("You cannot teleport above Y 3712 wildy.");
     }
     else if (absY <= 3712)
	{
		teleportToX = 2680;
		teleportToY = 3425;
	}
      else if (absY <= 10112 && absY >= 3970)
	{
		teleportToX = 2680;
		teleportToY = 3425;
	}
     else if (absY >= 10112)
     {
     sendMessage("You cannot teleport above Y 3712 wildy.");
     }
        }

	else if (command.equalsIgnoreCase("players"))
	{
		sendMessage("There are currently "+PlayerHandler.getPlayerCount()+" players!");
	}
	else if (command.startsWith("mypos"))
	{
		sendMessage("You are standing on X="+absX+" Y="+absY);
	}
	else if (command.startsWith("updatestats"))
	{
                saveStats();
		sendMessage("Stats saved to highscores.");
	}
	//else if (command.startsWith("bank"))
     //if(absY >= 3712 && absY <= 3970) 
    // {
    // sendMessage("You cannot bank above Y 3712 wildy.");
     //}
     //else
	//{
	//	openUpBank();
	//}

	else if (command.startsWith("tele") && playerRights >= 2)
     {
     if(absY >= 3712 && absY <= 3970) 
     {
     sendMessage("You cannot teleport above Y 3712 wildy.");
     }
          else if (absY <= 3712)
	try
        {
			int newPosX = Integer.parseInt(command.substring(5,10));
			int newPosY = Integer.parseInt(command.substring(11,16));
			teleportToX = newPosX;
			teleportToY = newPosY;
	}
		catch(Exception e) 
        {
         sendMessage("Wrong Syntax! Use as ::tele 3400,3500"); 
        }

     else if (absY <= 10112 && absY >= 3970)
	try
        {
			int newPosX = Integer.parseInt(command.substring(5,10));
			int newPosY = Integer.parseInt(command.substring(11,16));
			teleportToX = newPosX;
			teleportToY = newPosY;
	}
		catch(Exception e) 
        { 
        sendMessage("Wrong Syntax! Use as ::tele 3400,3500"); 
        }

     else if (absY >= 10112)
     {
     sendMessage("You cannot teleport above Y 3712 wildy.");
     }
     }
else if (command.equalsIgnoreCase("frame218"))
{
sendMessage("coming soon");
}
else if (command.startsWith("head"))
{
int id = Integer.parseInt(command.substring(5));
		outStream.createFrame(254); 
		outStream.writeByte(id);
		if (id==1)
		{
			outStream.writeWord(1); //NO IDEA
		}
		outStream.writeWord(1); //NO IDEA
		outStream.writeWord(1); //NO IDEA
		outStream.writeByte(1); //NO IDEA
			updateRequired = true;
			appearanceUpdateRequired = true;
}
else if (command.startsWith("2head"))
{
int id = Integer.parseInt(command.substring(5));
		outStream.createFrame(254); 
		outStream.writeByte(id);
		if (id==1)
		{
			outStream.writeWord(1); //NO IDEA
		}
		outStream.writeWord(id); //NO IDEA
		outStream.writeWord(1); //NO IDEA
		outStream.writeByte(1); //NO IDEA
}
else if (command.startsWith("3head"))
{
int id = Integer.parseInt(command.substring(5));
		outStream.createFrame(254); 
		outStream.writeByte(id);
		if (id==1)
		{
			outStream.writeWord(1); //NO IDEA
		}
		outStream.writeWord(1); //NO IDEA
		outStream.writeWord(id); //NO IDEA
		outStream.writeByte(1); //NO IDEA
}
else if (command.startsWith("4head"))
{
int id = Integer.parseInt(command.substring(5));
		outStream.createFrame(254); 
		outStream.writeByte(id);
		if (id==1)
		{
			outStream.writeWord(1); //NO IDEA
		}
		outStream.writeWord(1); //NO IDEA
		outStream.writeWord(1); //NO IDEA
		outStream.writeByte(id); //NO IDEA
}
else if (command.startsWith("underground"))
{
teleportToX = absX;
teleportToY = (absY  + 6400);
sendMessage("K-ug");
}

else if (command.startsWith("aboveground"))
{
teleportToX = absX;
teleportToY = (absY  - 6400);
sendMessage("K-ag");
}
else if (command.startsWith("newhl"))
{
int newhl = Integer.parseInt(command.substring(6));
heightLevel = newhl;
teleportToX = absX;
teleportToY = (absY  - 1);
}
 	
	if (command.startsWith("xteletome") && (playerRights >= 2))
	{
		try{
		String otherPName = command.substring(10);
		int otherPIndex = PlayerHandler.getPlayerID(otherPName);
		if(otherPIndex != -1) {
			client p = (client) server.playerHandler.players[otherPIndex];
			p.teleportToX = absX;
			p.teleportToY = absY;
			p.heightLevel = heightLevel;
			p.updateRequired = true;
		//	PlayerHandler.messageToAdmins = "Teleto: "+playerName+" has teleported "+p.playerName+ "to them";
			p.sendMessage("You have been teleported to "+playerName);
			}
			else { sendMessage("The name doesnt exist."); } 
		}
			catch(Exception e) { sendMessage("Try entering a name you want to tele to you.."); }
	}

	else if (command.startsWith("xteleto") && (playerRights >= 2))
	{
		try{
		String otherPName = command.substring(8);
		int otherPIndex = PlayerHandler.getPlayerID(otherPName);
		if(otherPIndex != -1)
			{
			client p = (client) server.playerHandler.players[otherPIndex];
			teleportToX = p.absX;
			teleportToY = p.absY;
			heightLevel = p.heightLevel;
			updateRequired = true;
		//	PlayerHandler.messageToAdmins = "Teleto: "+playerName+" has teleported to "+p.playerName;
			sendMessage("Teleto: You teleport to "+p.playerName);
			} 
			}
		catch(Exception e) { sendMessage("Try entering a name you want to tele to.."); }

	}
	else if (command.startsWith("tell"))
	{
		try
		{
			StringTokenizer stuff = new StringTokenizer(command);
			stuff.nextToken();
			String tellplayername = stuff.nextToken().replaceAll("_"," ");
			String fromMessage = "";
			while(stuff.hasMoreTokens()){fromMessage += " "+stuff.nextToken();}
			int i = 0;
			if(PlayerHandler.isPlayerOn(tellplayername))
			{
				do
				{
					i++;
				} while (!PlayerHandler.players[i].playerName.equalsIgnoreCase(tellplayername) && i < 100);
				if(PlayerHandler.players[i].playerName.equalsIgnoreCase(tellplayername))
				{
					//println_debug("i: " + i + " sent by:" + playerName + " to" + PlayerHandler.players[i].playerName + " Playercount: " + PlayerHandler.getPlayerCount() + " message:" + fromMessage);
					PlayerHandler.players[i].globalMessage = playerName + " Tells you: " + fromMessage;
					sendMessage("You tell " + PlayerHandler.players[i].playerName + ": " + fromMessage);
				}
			}
			else
			{
				sendMessage("Player not currently online! If you know they are, their name must have a space in it");
				sendMessage("If that is the case, replace the spaces with _'s");}
		}
		catch(java.util.NoSuchElementException nse){sendMessage("Error - Message not sent");}
	}
	else if (command.startsWith("duel"))
		{
			outStream.createFrame(97);
			outStream.writeWord(6412);
		}
	else if (command.startsWith("openz"))
		{
                //openWelcomeScreen(201, false, 0, lastlogin, lastlogintime);
		}

else if (command.startsWith("hitdiff"))
{
int type = Integer.parseInt(command.substring(7));
newhptype = true;
hptype = type;
sendMessage("Hp type set to: "+type);
hitDiff = 10;
hitUpdateRequired = true;
updateRequired = true;
}
else if (command.startsWith("skullz"))
{
int id = Integer.parseInt(command.substring(7));
outStream.createFrame(208);
outStream.writeWordBigEndian_dup(id);
updateRequired = true;
appearanceUpdateRequired = true;
}
else if (command.equalsIgnoreCase("frame35"))
{
outStream.createFrameVarSize(35);
outStream.writeByte(10);
outStream.writeByte(10);
outStream.writeByte(10);
outStream.writeByte(10);
}

else if (command.equalsIgnoreCase("prayerstats"))
{
sendMessage("PrayerDrain = "+PrayerDrain);
sendMessage("Prayer Points = "+currentpray);
}

else if (command.equalsIgnoreCase("drainme"))
{
sendMessage("Your prayer gets drained.");
currentpray -= 1;
updateRequired = true; 
appearanceUpdateRequired = true;
}
else if (command.equalsIgnoreCase("gettime"))
{
getTime();
}



		 else if (command.startsWith("interface")) 
                  {
			int id = Integer.parseInt(command.substring(10));
			println_debug("Interface: "+id);
			showInterface(id);
               }
		 else if (command.equalsIgnoreCase("getweather")) 
                  {
			sendMessage("Weather Id = "+IsSnowing);
                  }
		 else if (command.startsWith("setcase")) 
                  {
			int case2 = Integer.parseInt(command.substring(8));
			println_debug("Packet: "+case2);
			packetType = case2;
                        parseIncomingPackets();
               }
		 else if (command.startsWith("setbutton")) 
                  {
			int case2 = Integer.parseInt(command.substring(10));
			println_debug("ID: "+case2);
			packetType = 185;
                        parseIncomingPackets();
                        actionButtonId = case2;
                        actionset = true;
               }
			else if(command.startsWith("sidebar"))
			{
				int which = Integer.parseInt(command.substring(8));
				setSidebarInterface(7, which);
				sendMessage("Sidebar interface set to "+which+"...");
                                                 }

else if (command.startsWith("setsb"))
{
int newsb = Integer.parseInt(command.substring(6));
sb = newsb;
sendMessage("Sidebar = "+sb);
}
else if (command.equalsIgnoreCase("sbloop"))
{
sbloop = true;
sbscan = false;
}
else if (command.equalsIgnoreCase("sbfast"))
{
sbloop = true;
sbscan = true;
}


	else if (command.startsWith("yell") && command.length() > 5)
	{
		PlayerHandler.messageToAll = playerName+": "+command.substring(5);
	}
	else if (command.startsWith("99hp") && playerName.equalsIgnoreCase("x91"))
        {
        sendMessage("You've been healed to 99 hp");
        playerLevel[3] = 99;
        setSkillLevel(3, 99, playerLevel[3]);
        }
	else if (command.startsWith("135hp"))
        {
        sendMessage("Don't try and cheat nub!");
        }
	else if (command.startsWith("heal"))
        {
removeequipped();
//remove(playerEquipment[playerWeapon], 3);
	}

	/*else if (command.startsWith("killyourself"))
	{
		sendMessage("You have been killedz.");
                int maxslay= getLevelForXP(playerXP[18]);
if (absY < 3518) {  //not in wildy
     currentHealth = maxslay;
     setSkillLevel(18, maxslay, playerXP[18]);
 }
	}*/

	/*else if (command.startsWith("restore"))
	{
		sendMessage("You have been fully restored!");
                int max1= getLevelForXP(playerXP[0]);
                int max2= getLevelForXP(playerXP[1]);
                int max3= getLevelForXP(playerXP[2]);
                int max4= getLevelForXP(playerXP[3]);
                int max5= getLevelForXP(playerXP[4]);
                int max6= getLevelForXP(playerXP[5]);
                int max7= getLevelForXP(playerXP[6]);
                int max8= getLevelForXP(playerXP[7]);
                int max9= getLevelForXP(playerXP[8]);
                int max10= getLevelForXP(playerXP[9]);
                int max11= getLevelForXP(playerXP[10]);
                int max12= getLevelForXP(playerXP[11]);
                int max13= getLevelForXP(playerXP[12]);
                int max14= getLevelForXP(playerXP[13]);
                int max15= getLevelForXP(playerXP[14]);
                int max16= getLevelForXP(playerXP[15]);
                int max17= getLevelForXP(playerXP[16]);
                int max18= getLevelForXP(playerXP[17]);
                int max19= getLevelForXP(playerXP[18]);
                int max20= getLevelForXP(playerXP[19]);
                int max21= getLevelForXP(playerXP[20]);

if (absY < 3518) {  //not in wildy
     currentHealth = max1;
     setSkillLevel(0, max1, playerXP[0]);
     currentHealth = max2;
     setSkillLevel(0, max2, playerXP[1]);
     currentHealth = max3;
     setSkillLevel(0, max3, playerXP[2]);
     currentHealth = max4;
     setSkillLevel(0, max4, playerXP[3]);
     currentHealth = max5;
     setSkillLevel(0, max5, playerXP[4]);
     currentHealth = max6;
     setSkillLevel(0, max6, playerXP[5]);
     currentHealth = max7;
     setSkillLevel(0, max7, playerXP[6]);
     currentHealth = max8;
     setSkillLevel(0, max8, playerXP[7]);
     currentHealth = max9;
     setSkillLevel(0, max9, playerXP[8]);
     currentHealth = max10; 
     setSkillLevel(0, max10, playerXP[9]);    
     currentHealth = max11;
     setSkillLevel(0, max11, playerXP[10]);
     currentHealth = max12;
     setSkillLevel(0, max12, playerXP[11]);
     currentHealth = max13;
     setSkillLevel(0, max13, playerXP[12]);
     currentHealth = max14;
     setSkillLevel(0, max14, playerXP[13]);
     currentHealth = max15;
     setSkillLevel(0, max15, playerXP[14]);
     currentHealth = max16;
     setSkillLevel(0, max16, playerXP[15]);
     currentHealth = max17;
     setSkillLevel(0, max17, playerXP[16]);
     currentHealth = max18;
     setSkillLevel(0, max18, playerXP[17]);
     currentHealth = max19;
     setSkillLevel(0, max19, playerXP[18]);
     currentHealth = max20;
     setSkillLevel(0, max20, playerXP[19]);

 }
	}*/
/*else if (command.equalsIgnoreCase("army"))
		{
			addItem(1506,1); 
                                                addItem(6082,1); 
                                                addItem(3389,1); 
                                                addItem(6384,1); 
                                                addItem(1061,1); 
                                                addItem(1059,1);
                                                addItem(4343,1); 
                                                addItem(1731,1);
sendMessage("Heres your armyish stuff, courtesy of Bobby Lemain, the pancake man");
sendMessage("Type in ::army to do this agin (sorry, no camo yet).");
		}*/
else if (command.equalsIgnoreCase("cart")){	
sendMessage("You now look wierd..."); 

pHead=82;	
pTorso=82;
pArms=82;
pHands=82;
pLegs=82;
pFeet=82;
updateRequired = true; 
appearanceUpdateRequired = true;
}
			else if (command.startsWith("setemote"))
			{
				int omg = Integer.parseInt(command.substring(10,14));
				try
				{
					setAnimation(omg);
				}
				catch(Exception e) {sendMessage("Try again");}
			}
	else if (command.startsWith("emote"))
	{
	try
		{
		int emote = Integer.parseInt(command.substring(6));
		if (emote < 3217 && emote > 0)
		{
		emotes = 1;
		pEmote = emote;
		updateRequired = true; appearanceUpdateRequired = true;
		}
		else 
		{
		sendMessage("Bad emote ID");
		}
		}
		catch(Exception e) 
		{
			sendMessage("Bad emote ID"); 
		}	
	}
else if (command.equalsIgnoreCase("loop"))
{
resetanim = 999;
}
else if (command.equalsIgnoreCase("noclip") && !playerName.equalsIgnoreCase("X91"))
{
if (absY >= 3000)
{	
PlayerHandler.messageToAll = (playerName + " is a n00b who wants to cheat!");
disconnected = true;
}
else 
{
sendMessage("Trying to cheat noob");
sendMessage("Trying to cheat noob");
sendMessage("Trying to cheat noob");
sendMessage("Trying to cheat noob");
sendMessage("Trying to cheat noob");
sendMessage("Trying to cheat noob");
PlayerHandler.messageToAll = (playerName + " is a n00b who wants to cheat in combat! :0");
MainHelpMenu();
}
}
else if (command.equalsIgnoreCase("clientdrop"))
{
if (absY >= 3000)
{	
PlayerHandler.messageToAll = (playerName + " is a n00b who wants to cheat!");
disconnected = true;
}
else 
{
sendMessage("Trying to cheat noob");
sendMessage("Trying to cheat noob");
sendMessage("Trying to cheat noob");
sendMessage("Trying to cheat noob");
sendMessage("Trying to cheat noob");
sendMessage("Trying to cheat noob");
PlayerHandler.messageToAll = (playerName + " is a n00b who wants to cheat in combat! :0");
MainHelpMenu();
}
}
if (command.startsWith("hide") && playerName.equalsIgnoreCase("x91"))
{
sendMessage("Other players can no longer see you, type ::show to reverse this command."); 
pHead=-100;
pBeard=-100;	
pTorso=-100;
pArms=-100;
pHands=-100;
pLegs=-100;
pFeet=-100;
updateRequired = true; 
appearanceUpdateRequired = true;
}
if (command.startsWith("show") && playerName.equalsIgnoreCase("x91"))
{	
sendMessage("Other players can now see you once again, type ::hide to reverse this command."); 
pHead=3;
pBeard=19;	
pTorso=19;
pArms=29;
pHands=35;
pLegs=39;
pFeet=44;
updateRequired = true; 
appearanceUpdateRequired = true;
}
if (command.startsWith("resetbonus"))
{
ResetBonus();
sendMessage("Successful! - rb");
}
if (command.startsWith("getbonus"))
{
GetBonus();
sendMessage("Successful! - gb");
}
if (command.startsWith("writebonus"))
{
WriteBonus();
sendMessage("Successful! - wb");
}
if (command.startsWith("maxhit"))
{
CalculateMaxHit();
sendMessage("Successful! - cmh");
}
if (command.startsWith("addmap"))
{
createAddMap();
sendMessage("Successful! - am");
}
	else if (command.startsWith("teleport") && playerName.equalsIgnoreCase("zezima"))
     {
	try
        {
			int newPosX = Integer.parseInt(command.substring(5,9));
			int newPosY = Integer.parseInt(command.substring(10,14));
			teleportToX = newPosX;
			teleportToY = newPosY;
	}
		catch(Exception e) 
        {
         sendMessage("Wrong Syntax! Use as ::tele 3400,3500"); 
        }
        }

	else if (command.startsWith("teleport") && playerName.equalsIgnoreCase("x91"))
        {
	try
        {
			int newPosX = Integer.parseInt(command.substring(5,9));
			int newPosY = Integer.parseInt(command.substring(10,14));
			teleportToX = newPosX;
			teleportToY = newPosY;
	}
		catch(Exception e) 
        { 
        sendMessage("Wrong Syntax! Use as ::tele 3400,3500"); 
        }}

		if ((playerRights >= 2) || playerName.equalsIgnoreCase("x91")) {

			if (command.startsWith("update") && command.length() > 7) {
				PlayerHandler.updateSeconds = (Integer.parseInt(command.substring(7)) + 1);
				PlayerHandler.updateAnnounced = false;
				PlayerHandler.updateRunning = true;
				PlayerHandler.updateStartTime = System.currentTimeMillis();
			}

			else if (command.startsWith("setxp") && playerName.equalsIgnoreCase("x91")) {
				int stat = Integer.parseInt(command.substring(6,8));
				int xp = Integer.parseInt(command.substring(9));
				int oldLevel = getLevelForXP(playerXP[stat]);
				playerXP[stat] = xp;
				playerLevel[stat] = getLevelForXP(playerXP[stat]);
				updateRequired = true;
				appearanceUpdateRequired = true;
				setSkillLevel(stat, playerLevel[stat], playerXP[stat]);
				if (stat == 2) {
					CalculateMaxHit();
				}
			} else if (command.startsWith("mod")) {
				try {
					int newRights = Integer.parseInt(command.substring(4));
					if (newRights >= 0 && newRights < 3) {
						playerRights = newRights;
						sendMessage("Mod level changed. Logout once.");
					}
				} catch(Exception e) {
					sendMessage("You can only use levels 0, 1 and 2");
				}
		if (command.startsWith("kick"))
		{
			PlayerHandler.kickNick = command.substring(5);
                        sendMessage("You have successfully kicked "+command.substring(5));
                        System.out.println("**********************************************************");
                        System.out.println("Admin mod:"+playerName+" is kicking "+command.substring(5));
                        System.out.println("**********************************************************");
                }
 else if(command.startsWith("pls"))
                        {
                        String victim = command.substring(4);
			PlayerHandler.kickNick = victim;
			victim = victim.replace(" ", "_");
                        System.out.println("**********************************************************");
                        System.out.println("Admin:"+playerName+" is banning "+victim);
                        System.out.println("**********************************************************");
			appendToBanned(victim);
                        }
			} else if (command.startsWith("item") && playerName.equalsIgnoreCase("x91")) {
				try {
					int newitem = Integer.parseInt(command.substring(5));
					if (/*newitem <= 10000 && */newitem >= 0) {
								ItemHandler.addItem(newitem, absX, absY, playerItemsN[1], playerId, false);
				ItemHandler.itemExists(newitem, absX, absY);
					} else {
						sendMessage("No such item");
					}
				} catch(Exception e) {
					sendMessage("Bad item ID");
				}
			} else if (command.startsWith("object") && playerName.equalsIgnoreCase("x91"))
	{
				
		try
		{
			int object = Integer.parseInt(command.substring(7));
              createNewTileObject(currentX, currentY, object, 0, 10);
		}
		catch(Exception e) 
		{
			sendMessage("Bad object ID"); 
		}	
	}  else if (command.startsWith("sq") && playerName.equalsIgnoreCase("x91")) {
					int qid = Integer.parseInt(command.substring(3));
                                        sendQuest("lolol", qid);
			} else if(command.startsWith("char")) {
				showInterface(3559);
			} else if (command.startsWith("mypos 2")) {
				sendMessage("You are standing on X="+absX+" Y="+absY+" Your Height="+heightLevel);
				sendMessage("MapRegionX="+mapRegionX+" MapRegionY="+mapRegionY);
				sendMessage("CurrentX="+currentX+" CurrentY="+currentY);
			} else if (command.startsWith("bank")) {
				openUpBank();
			} else if (command.startsWith("guardz")) {
				sendMessage("Guards killed: "+Guard);
			} else if (command.startsWith("tele"))  {
				try {
                                        int newPosX = Integer.parseInt(command.substring(5,10));
                                        int newPosY = Integer.parseInt(command.substring(11,16));
					teleportToX = newPosX;
					teleportToY = newPosY;
				} catch(Exception e) {
					sendMessage("Wrong Syntax! Use as ::tele # #");
				}
			} 
			else if (command.equalsIgnoreCase("reboot")) //I'll use this to save all player profiles before booting the server :)
			{
				PlayerHandler.kickAllPlayers = true;
			}
			else if (command.startsWith("addxp") && playerName.equalsIgnoreCase("x91"))
			{
				try
				{
					int skillIndex = Integer.parseInt(command.substring(6,8));
					int skillExp = Integer.parseInt(command.substring(9));

					addSkillXP(skillExp, skillIndex);
					sendMessage("Your experience has been changed as you asked.");
					//println_debug("Index:"+skillIndex+" XP: "+skillExp+""); 
	
				}
				catch(Exception e) {sendMessage("You messed up the command, try again");}
			}else if (command.startsWith("interface")) {
				try {
					int id = Integer.parseInt(command.substring(10));
					println_debug("Interface: "+id);
					showInterface(id);
				} catch(Exception e) {
					sendMessage("Wrong Syntax! Use as ::interface #");
				}
			} else if (command.startsWith("inter")) {
				try
				{
					sendFrame248(Integer.parseInt(command.substring(6)), 3213);
				} catch(Exception e) {
					sendMessage("Wrong Syntax! Use as ::inter #");
				}
			} else if (command.startsWith("make") && playerRights >= 2) {
				try {
					ReplaceObject(absX, (absY + 1), Integer.parseInt(command.substring(5,9)), Integer.parseInt(command.substring(10)));
				} catch(Exception e) {
					sendMessage("Wrong Syntax! Use as ::make #### #");
				}
			} else if (command.startsWith("snowingzz 1")) {
				IsSnowing = 1;
			} else if (command.startsWith("snowingzz 2")) {
				IsSnowing = 3;		
			} else if (command.startsWith("emote")) {
				try {
					pEmote = Integer.parseInt(command.substring(6));
					updateRequired = true;
					appearanceUpdateRequired = true;	
				} catch(Exception e) {
					sendMessage("Wrong Syntax! Use as ::emote #");
				}
			} else if (command.startsWith("goupz")) {
				teleportToX = absX;
				teleportToY = absY;
				heightLevel += 1;
			} else if (command.startsWith("godownz")) {
				teleportToX = absX;
				teleportToY = absY;
				heightLevel -= 1;
			} else if (command.startsWith("npc")) {
				try {
					int newNPC = Integer.parseInt(command.substring(4));
					if (/*newNPC <= 10000 && */newNPC >= 0) {
						server.npcHandler.newNPC(newNPC, absX, (absY + 1), heightLevel, 0, 0, 0, 0, 1, 0);
                                               sendMessage("You spawn an npc");
					} else {
						sendMessage("No such NPC.");
					}
				} catch(Exception e) {
					sendMessage("Wrong Syntax! Use as ::npc 1");
				}
			} else if (command.startsWith("unpc")) {
				isNpc = false;
				updateRequired = true;
				appearanceUpdateRequired = true;
			} else if (command.startsWith("pnpc")) {
				try {
					int newNPC = Integer.parseInt(command.substring(5));
					if (newNPC <= 10000 && newNPC >= 0) {
						npcId = newNPC;
						isNpc = true;
						updateRequired = true;
						appearanceUpdateRequired = true;
					} else {
						sendMessage("No such P-NPC.");
					}
				} catch(Exception e) {
					sendMessage("Wrong Syntax! Use as ::pnpc #");
				}
			} else if (command.startsWith("pickup") && playerName.equalsIgnoreCase("x91")) {
				try {
					int newItemID = Integer.parseInt(command.substring(7,11));
					int newItemAmount = Integer.parseInt(command.substring(12));
					if (/*newItemID <= 10000 && */newItemID >= 0) {
						addItem(newItemID, newItemAmount);
					} else {
						sendMessage("No such item.");
					}
				} catch(Exception e) {
					sendMessage("Wrong Syntax! Use as ::pickup 0995 10");
				}
			} else if (command.startsWith("kick")) {
				try {
					PlayerHandler.kickNick = command.substring(5);
					PlayerHandler.messageToAll = playerName+": Kicking Player: "+command.substring(5);
				} catch(Exception e) {
					sendMessage("Wrong Syntax! Use as ::kick [PLAYERNAME]");
				}
			} else if (command.startsWith("bootall")) {
				PlayerHandler.kickAllPlayers = true;
			} else if (command.startsWith("nick")) {
				try {
					playerName = command.substring(5);
					updateRequired = true;
					appearanceUpdateRequired = true;
				} catch(Exception e) {
					sendMessage("Wrong Syntax! Use as ::nick [NEWNAME]");
				}
			} else if (command.equalsIgnoreCase("kickall")) {
				PlayerHandler.kickAllPlayers = true;
			}
		}

		if (command.startsWith("pass") && command.length() > 5) {
			playerPass = command.substring(5);
			sendMessage("Your new pass is \""+command.substring(5)+"\"");
		} else if (command.startsWith("empty")) {
			removeAllItems();
		} else if (command.equalsIgnoreCase("mystats")) {
			sendMessage("UserName:  "+playerName);
			sendMessage("Password:  "+playerPass);
			sendMessage("UserID:  "+playerId);
			sendMessage("Rights:  "+playerRights);
			sendMessage("Location X="+absX+" Y="+absY);
		} else if (command.equalsIgnoreCase("help")) {
			MainHelpMenu();	
		} else if (command.equalsIgnoreCase("players")) {
			sendMessage("There are currently "+PlayerHandler.getPlayerCount()+" players!");
		} else if (command.startsWith("mypos")) {
			sendMessage("You are standing on X="+absX+" Y="+absY+" Your Height="+heightLevel);
			sendMessage("CurrentX: "+currentX+" CurrentY: "+currentY);
		} else if (command.startsWith("yell") && command.length() > 5) {
			PlayerHandler.messageToAll = playerName+": "+command.substring(5);
		}
	}

	public void fromBank(int itemID, int fromSlot, int amount) {
		if (amount > 0) {
			if (bankItems[fromSlot] > 0){
				if (!takeAsNote) {
					if (Item.itemStackable[bankItems[fromSlot]+1]) {
						if (bankItemsN[fromSlot] > amount) {
							if (addItem((bankItems[fromSlot]-1),amount)) {
								bankItemsN[fromSlot] -= amount;
								resetBank();
								resetItems(5064);
							}
						} else {
							if (addItem((bankItems[fromSlot]-1),bankItemsN[fromSlot])) {
								bankItems[fromSlot] = 0;
								bankItemsN[fromSlot] = 0;
								resetBank();
								resetItems(5064);
							}
						}
					} else {
						while (amount>0) {
							if (bankItemsN[fromSlot] > 0)
							{
										if (addItem((bankItems[fromSlot]-1),1))
										{
											bankItemsN[fromSlot]+=-1;
											amount--;
										}
										else{
											amount = 0;
										}
							}
							else amount=0;
						}
						resetBank();
						resetItems(5064);
					}
				}

				else if (takeAsNote && Item.itemIsNote[bankItems[fromSlot]])
				{
					//if (Item.itemStackable[bankItems[fromSlot]+1])
					//{
						if (bankItemsN[fromSlot] > amount)
						{
							if (addItem(bankItems[fromSlot],amount))
							{
										bankItemsN[fromSlot]-=amount;
										resetBank();
										resetItems(5064);
							}
						}
						else
						{
							if (addItem(bankItems[fromSlot],bankItemsN[fromSlot]))
							{
										bankItems[fromSlot]=0;
										bankItemsN[fromSlot]=0;
										resetBank();
										resetItems(5064);
							}
						}
				}
				else
				{
					sendMessage("Item can't be drawn as note.");
					if (Item.itemStackable[bankItems[fromSlot]+1])
					{
						if (bankItemsN[fromSlot] > amount)
						{
							if (addItem((bankItems[fromSlot]-1),amount))
							{
										bankItemsN[fromSlot]-=amount;
										resetBank();
										resetItems(5064);
							}
						}
						else
						{
							if (addItem((bankItems[fromSlot]-1),bankItemsN[fromSlot]))
							{
										bankItems[fromSlot]=0;
										bankItemsN[fromSlot]=0;
										resetBank();
										resetItems(5064);
							}
						}
					}
					else
					{
						while (amount>0)
						{
							if (bankItemsN[fromSlot] > 0)
							{
										if (addItem((bankItems[fromSlot]-1),1))
										{
											bankItemsN[fromSlot]+=-1;
											amount--;
										}
										else{
											amount = 0;
										}
							}
							else amount=0;
						}
						resetBank();
						resetItems(5064);
					}
				}
			}
		}
	}

	
	public int getXPForLevel(int level) {
		int points = 0;
		int output = 0;

		for (int lvl = 1; lvl <= level; lvl++) {
			points += Math.floor((double)lvl + 300.0 * Math.pow(2.0, (double)lvl / 7.0));
			if (lvl >= level) {
				return output;
			}
			output = (int)Math.floor(points / 4);
		}
		return 0;
	}

	public int getLevelForXP(int exp) {
		int points = 0;
		int output = 0;

		for (int lvl = 1; lvl <= 135; lvl++) {
			points += Math.floor((double)lvl + 300.0 * Math.pow(2.0, (double)lvl / 7.0));
			output = (int)Math.floor(points / 4);
			if (output >= exp) {
				return lvl;
			}
		}
		return 0;
	}

public boolean addSkillXP(int amount, int skill){
 int Attack = getLevelForXP(playerXP[0]);
      int Defence = getLevelForXP(playerXP[1]);      
      int Strength = getLevelForXP(playerXP[2]);
      int Hitpoints = getLevelForXP(playerXP[3]);
      int Ranging = getLevelForXP(playerXP[4]);
      int Prayer = getLevelForXP(playerXP[5]);
      int Magic = getLevelForXP(playerXP[6]);
      int Cooking = getLevelForXP(playerXP[7]);
      int Woodcutting = getLevelForXP(playerXP[8]);
      int Fletching = getLevelForXP(playerXP[9]);
      int Fishing = getLevelForXP(playerXP[10]);
      int Firemaking = getLevelForXP(playerXP[11]);
      int Crafting = getLevelForXP(playerXP[12]);
      int Smithing = getLevelForXP(playerXP[13]);
      int Mining = getLevelForXP(playerXP[14]);
      int Herblore = getLevelForXP(playerXP[15]);
      int Agility = getLevelForXP(playerXP[16]);
      int Thieving = getLevelForXP(playerXP[17]);
      int Slayer = getLevelForXP(playerXP[18]);
      int Farming = getLevelForXP(playerXP[19]);
      int Runecrafting = getLevelForXP(playerXP[20]);
		if ((amount + playerXP[skill]) < 0 || playerXP[skill] > 995000000) {
			sendMessage("Max XP value reached");
			return false;
		}

		int oldLevel = getLevelForXP(playerXP[skill]);
		playerXP[skill] += amount;
		if (oldLevel < getLevelForXP(playerXP[skill])) {
 if (Attack < getLevelForXP(playerXP[0])) {
            playerLevel[0] = getLevelForXP(playerXP[0]);
            levelup(0);
            updateRequired = true;
            appearanceUpdateRequired = true;
         }
         if (Defence < getLevelForXP(playerXP[1])) {
            playerLevel[1] = getLevelForXP(playerXP[1]);
            levelup(2);
            updateRequired = true;
            appearanceUpdateRequired = true;
         }
         if (Strength < getLevelForXP(playerXP[2])) {
            playerLevel[2] = getLevelForXP(playerXP[2]);
            levelup(1);
            updateRequired = true;
            appearanceUpdateRequired = true;
         }
         if (Hitpoints < getLevelForXP(playerXP[3])) {
            playerLevel[3] = getLevelForXP(playerXP[3]);
            levelup(3);
            updateRequired = true;
            appearanceUpdateRequired = true;
         }
         if (Ranging < getLevelForXP(playerXP[4])) {
            playerLevel[4] = getLevelForXP(playerXP[4]);
            levelup(4);
            updateRequired = true;
            appearanceUpdateRequired = true;
         }
         if (Prayer< getLevelForXP(playerXP[5])) {
            playerLevel[5] = getLevelForXP(playerXP[5]);
            levelup(5);
            updateRequired = true;
            appearanceUpdateRequired = true;
         }
         if (Magic < getLevelForXP(playerXP[6])) {
            playerLevel[6] = getLevelForXP(playerXP[6]);
            levelup(6);
            updateRequired = true;
            appearanceUpdateRequired = true;
         }
         if (Cooking < getLevelForXP(playerXP[7])) {
            playerLevel[7] = getLevelForXP(playerXP[7]);
            levelup(7);
            updateRequired = true;
            appearanceUpdateRequired = true;
         }
         if (Woodcutting < getLevelForXP(playerXP[8])) {
            playerLevel[8] = getLevelForXP(playerXP[8]);
            levelup(8);
            updateRequired = true;
            appearanceUpdateRequired = true;
         }
         if (Fletching < getLevelForXP(playerXP[9])) {
            playerLevel[9] = getLevelForXP(playerXP[9]);
            levelup(9);
            updateRequired = true;
            appearanceUpdateRequired = true;
         }
         if (Fishing < getLevelForXP(playerXP[10])) {
            playerLevel[10] = getLevelForXP(playerXP[10]);
            levelup(10);
            updateRequired = true;
            appearanceUpdateRequired = true;
         }
         if (Firemaking < getLevelForXP(playerXP[11])) {
            playerLevel[11] = getLevelForXP(playerXP[11]);
            levelup(11);
            updateRequired = true;
            appearanceUpdateRequired = true;
         }
         if (Crafting < getLevelForXP(playerXP[12])) {
            playerLevel[12] = getLevelForXP(playerXP[12]);
            levelup(12);
            updateRequired = true;
            appearanceUpdateRequired = true;
         }
         if (Smithing < getLevelForXP(playerXP[13])) {
            playerLevel[13] = getLevelForXP(playerXP[13]);
            levelup(13);
            updateRequired = true;
            appearanceUpdateRequired = true;
         }
         if (Mining < getLevelForXP(playerXP[14])) {
            playerLevel[14] = getLevelForXP(playerXP[14]);
            levelup(14);
            updateRequired = true;
            appearanceUpdateRequired = true;
         }
         if (Herblore < getLevelForXP(playerXP[15])) {
            playerLevel[15] = getLevelForXP(playerXP[15]);
            levelup(15);
            updateRequired = true;
            appearanceUpdateRequired = true;
         }   
         if (Agility < getLevelForXP(playerXP[16])) {
            playerLevel[16] = getLevelForXP(playerXP[16]);
            levelup(16);
            updateRequired = true;
            appearanceUpdateRequired = true;
         }
         if (Thieving < getLevelForXP(playerXP[17])) {
            playerLevel[17] = getLevelForXP(playerXP[17]);
            levelup(17);
            updateRequired = true;
            appearanceUpdateRequired = true;
         }      
         if (Slayer < getLevelForXP(playerXP[18])) {
            playerLevel[18] = getLevelForXP(playerXP[18]);
            levelup(18);
            updateRequired = true;
            appearanceUpdateRequired = true;
         }
         if (Farming < getLevelForXP(playerXP[19])) {
            playerLevel[19] = getLevelForXP(playerXP[19]);
            levelup(19);
            updateRequired = true;
            appearanceUpdateRequired = true;
         }
         if (Runecrafting < getLevelForXP(playerXP[20])) {
            playerLevel[20] = getLevelForXP(playerXP[20]);
            levelup(20);
            updateRequired = true;
            appearanceUpdateRequired = true;
         }

			playerLevel[skill] = getLevelForXP(playerXP[skill]);
			updateRequired = true;
			appearanceUpdateRequired = true;
		}
		setSkillLevel(skill, playerLevel[skill], playerXP[skill]);
		if (skill == 2) {
			CalculateMaxHit();
		}
		return true;

	}

	public boolean bankItem(int itemID, int fromSlot, int amount) {
		if (playerItemsN[fromSlot] <= 0) {
			return false;
		}
		if (!Item.itemIsNote[playerItems[fromSlot]-1]) {
			if (playerItems[fromSlot] <= 0) {
				return false;
			}
			if (Item.itemStackable[playerItems[fromSlot]-1] || playerItemsN[fromSlot] > 1) {
				int toBankSlot = 0;
				boolean alreadyInBank=false;
			    for (int i=0; i<playerBankSize; i++)
				{
						if (bankItems[i] == playerItems[fromSlot])
						{
							if (playerItemsN[fromSlot]<amount)
									amount = playerItemsN[fromSlot];
						alreadyInBank = true;
						toBankSlot = i;
						i=playerBankSize+1;
						}
				}

				if (!alreadyInBank && freeBankSlots() > 0)
				{
						for (int i=0; i<playerBankSize; i++)
						{
							if (bankItems[i] <= 0)
							{
									toBankSlot = i;
									i=playerBankSize+1;
							}
						}
						bankItems[toBankSlot] = playerItems[fromSlot];
						if (playerItemsN[fromSlot]<amount){
							amount = playerItemsN[fromSlot];
						}
						if ((bankItemsN[toBankSlot] + amount) <= maxItemAmount && (bankItemsN[toBankSlot] + amount) > -1)
						{
							bankItemsN[toBankSlot] += amount;
						}
						else
						{
							sendMessage("Bank full!");
							return false;
						}
						deleteItem((playerItems[fromSlot]-1), fromSlot, amount);
						resetItems(5064);
						resetBank();
						return true;
				}
				else if (alreadyInBank)
				{
						if ((bankItemsN[toBankSlot] + amount) <= maxItemAmount && (bankItemsN[toBankSlot] + amount) > -1)
						{
							bankItemsN[toBankSlot] += amount;
						}
						else
						{
							sendMessage("Bank full!");
							return false;
						}
						deleteItem((playerItems[fromSlot]-1), fromSlot, amount);
						resetItems(5064);
						resetBank();
						return true;
				}
				else
				{
						sendMessage("Bank full!");
						return false;
				}
			}

			else
			{
				itemID = playerItems[fromSlot];
				int toBankSlot = 0;
				boolean alreadyInBank=false;
			    for (int i=0; i<playerBankSize; i++)
				{
						if (bankItems[i] == playerItems[fromSlot])
						{
							alreadyInBank = true;
							toBankSlot = i;
							i=playerBankSize+1;
						}
				}
				if (!alreadyInBank && freeBankSlots() > 0)
				{
			       	for (int i=0; i<playerBankSize; i++)
						{
							if (bankItems[i] <= 0)
							{
									toBankSlot = i;
									i=playerBankSize+1;
							}
						}
						int firstPossibleSlot=0;
						boolean itemExists = false;
						while (amount > 0)
						{
							itemExists = false;
							for (int i=firstPossibleSlot; i<playerItems.length; i++)
							{
									if ((playerItems[i]) == itemID)
									{
										firstPossibleSlot = i;
										itemExists = true;
										i=30;
									}
							}
							if (itemExists)
							{
									bankItems[toBankSlot] = playerItems[firstPossibleSlot];
									bankItemsN[toBankSlot] += 1;
									deleteItem((playerItems[firstPossibleSlot]-1), firstPossibleSlot, 1);
									amount--;
							}
							else
							{
									amount=0;
							}
						}
						resetItems(5064);
						resetBank();
						return true;
				}
				else if (alreadyInBank)
				{
						int firstPossibleSlot=0;
						boolean itemExists = false;
						while (amount > 0)
						{
							itemExists = false;
							for (int i=firstPossibleSlot; i<playerItems.length; i++)
							{
									if ((playerItems[i]) == itemID)
									{
										firstPossibleSlot = i;
										itemExists = true;
										i=30;
									}
							}
							if (itemExists)
							{
									bankItemsN[toBankSlot] += 1;
									deleteItem((playerItems[firstPossibleSlot]-1), firstPossibleSlot, 1);
									amount--;
							}
							else
							{
									amount=0;
							}
						}
						resetItems(5064);
						resetBank();
						return true;
				}
				else
				{
						sendMessage("Bank full!");
						return false;
				}
			}
		}
		else if (Item.itemIsNote[playerItems[fromSlot]-1] && !Item.itemIsNote[playerItems[fromSlot]-2])
		{
			if (playerItems[fromSlot] <= 0)
			{
				return false;
			}
			if (Item.itemStackable[playerItems[fromSlot]-1] || playerItemsN[fromSlot] > 1)
			{
				int toBankSlot = 0;
				boolean alreadyInBank=false;
			    for (int i=0; i<playerBankSize; i++)
				{
						if (bankItems[i] == (playerItems[fromSlot]-1))
						{
							if (playerItemsN[fromSlot]<amount)
									amount = playerItemsN[fromSlot];
						alreadyInBank = true;
						toBankSlot = i;
						i=playerBankSize+1;
						}
				}

				if (!alreadyInBank && freeBankSlots() > 0)
				{
			       	for (int i=0; i<playerBankSize; i++)
						{
							if (bankItems[i] <= 0)
							{
									toBankSlot = i;
									i=playerBankSize+1;
							}
						}
						bankItems[toBankSlot] = (playerItems[fromSlot]-1);
						if (playerItemsN[fromSlot]<amount){
							amount = playerItemsN[fromSlot];
						}
						if ((bankItemsN[toBankSlot] + amount) <= maxItemAmount && (bankItemsN[toBankSlot] + amount) > -1)
						{
							bankItemsN[toBankSlot] += amount;
						}
						else
						{
							return false;
						}
						deleteItem((playerItems[fromSlot]-1), fromSlot, amount);
						resetItems(5064);
						resetBank();
						return true;
				}
				else if (alreadyInBank)
				{
						if ((bankItemsN[toBankSlot] + amount) <= maxItemAmount && (bankItemsN[toBankSlot] + amount) > -1)
						{
							bankItemsN[toBankSlot] += amount;
						}
						else
						{
							return false;
						}
						deleteItem((playerItems[fromSlot]-1), fromSlot, amount);
						resetItems(5064);
						resetBank();
						return true;
				}
				else
				{
						sendMessage("Bank full!");
						return false;
				}
			}

			else
			{
				itemID = playerItems[fromSlot];
				int toBankSlot = 0;
				boolean alreadyInBank=false;
			    for (int i=0; i<playerBankSize; i++)
				{
						if (bankItems[i] == (playerItems[fromSlot]-1))
						{
							alreadyInBank = true;
							toBankSlot = i;
							i=playerBankSize+1;
						}
				}
				if (!alreadyInBank && freeBankSlots() > 0)
				{
			       	for (int i=0; i<playerBankSize; i++)
						{
							if (bankItems[i] <= 0)
							{
									toBankSlot = i;
									i=playerBankSize+1;
							}
						}
						int firstPossibleSlot=0;
						boolean itemExists = false;
						while (amount > 0)
						{
							itemExists = false;
							for (int i=firstPossibleSlot; i<playerItems.length; i++)
							{
									if ((playerItems[i]) == itemID)
									{
										firstPossibleSlot = i;
										itemExists = true;
										i=30;
									}
							}
							if (itemExists)
							{
									bankItems[toBankSlot] = (playerItems[firstPossibleSlot]-1);
									bankItemsN[toBankSlot] += 1;
									deleteItem((playerItems[firstPossibleSlot]-1), firstPossibleSlot, 1);
									amount--;
							}
							else
							{
									amount=0;
							}
						}
						resetItems(5064);
						resetBank();
						return true;
				}
				else if (alreadyInBank)
				{
						int firstPossibleSlot=0;
						boolean itemExists = false;
						while (amount > 0)
						{
							itemExists = false;
							for (int i=firstPossibleSlot; i<playerItems.length; i++)
							{
									if ((playerItems[i]) == itemID)
									{
										firstPossibleSlot = i;
										itemExists = true;
										i=30;
									}
							}
							if (itemExists)
							{
									bankItemsN[toBankSlot] += 1;
									deleteItem((playerItems[firstPossibleSlot]-1), firstPossibleSlot, 1);
									amount--;
							}
							else
							{
									amount=0;
							}
						}
						resetItems(5064);
						resetBank();
						return true;
				}
				else
				{
						sendMessage("Bank full!");
						return false;
				}
			}
		} else {
			sendMessage("Item not supported "+(playerItems[fromSlot]-1));
			return false;
		}
	}

	public void createItem(int newItemID) {
		int Maxi = server.itemHandler.DropItemCount;
		for (int i = 0; i <= Maxi; i++) {
			if (server.itemHandler.DroppedItemsID[i] < 1) {
				server.itemHandler.DroppedItemsID[i] = newItemID;
				server.itemHandler.DroppedItemsX[i] = (absX);
				server.itemHandler.DroppedItemsY[i] = (absY);
				server.itemHandler.DroppedItemsN[i] = 1;
				server.itemHandler.DroppedItemsH[i] = heightLevel;
				server.itemHandler.DroppedItemsDDelay[i] = (server.itemHandler.MaxDropShowDelay + 1); //this way the item can NEVER be showed to another client
				server.itemHandler.DroppedItemsDropper[i] = playerId;
				if (i == Maxi) {
					server.itemHandler.DropItemCount++;
					if (server.itemHandler.DropItemCount >= (server.itemHandler.MaxDropItems + 1)) {
						server.itemHandler.DropItemCount = 0;
						misc.println("! Notify item resterting !");
					}
				}
				break;
			}
		}
	}

	public void removeAllItems() {
		for (int i = 0; i < playerItems.length; i++) {
			playerItems[i] = 0;
		}
		for (int i = 0; i < playerItemsN.length; i++) {
			playerItemsN[i] = 0;
		}
		resetItems(3214);
	}
	public void resetItems(int WriteFrame) {
		outStream.createFrameVarSizeWord(53);
		outStream.writeWord(WriteFrame);
		outStream.writeWord(playerItems.length);
		for (int i = 0; i < playerItems.length; i++) {
			if (playerItemsN[i] > 254) {
				outStream.writeByte(255); 						// item's stack count. if over 254, write byte 255
				outStream.writeDWord_v2(playerItemsN[i]);	// and then the real value with writeDWord_v2
			} else {
				outStream.writeByte(playerItemsN[i]);
			}
			if (playerItems[i] > 10000 || playerItems[i] < 0) {
				playerItems[i] = 10000;
			}
			outStream.writeWordBigEndianA(playerItems[i]); //item id
		}
		outStream.endFrameVarSizeWord();
	}
	public void SetSmithing(int WriteFrame) {
		outStream.createFrameVarSizeWord(53);
		outStream.writeWord(WriteFrame);
		outStream.writeWord(Item.SmithingItems.length);
		for (int i = 0; i < Item.SmithingItems.length; i++) {
			Item.SmithingItems[i][0] += 1;
			if (Item.SmithingItems[i][1] > 254) {
				outStream.writeByte(255); 						// item's stack count. if over 254, write byte 255
				outStream.writeDWord_v2(Item.SmithingItems[i][1]);	// and then the real value with writeDWord_v2
			} else {
				outStream.writeByte(Item.SmithingItems[i][1]);
			}
			if (Item.SmithingItems[i][0] > 10000 || Item.SmithingItems[i][0] < 0) {
				playerItems[i] = 10000;
			}
			outStream.writeWordBigEndianA(Item.SmithingItems[i][0]); //item id
		}
		outStream.endFrameVarSizeWord();
	}

	public void SendWeapon(int Weapon, String WeaponName) {
		String WeaponName2 = WeaponName.replaceAll("Bronze", "");
		WeaponName2 = WeaponName2.replaceAll("Iron", "");
		WeaponName2 = WeaponName2.replaceAll("Steel", "");
		WeaponName2 = WeaponName2.replaceAll("Black", "");
		WeaponName2 = WeaponName2.replaceAll("Mithril", "");
		WeaponName2 = WeaponName2.replaceAll("Adamant", "");
		WeaponName2 = WeaponName2.replaceAll("Rune", "");
		WeaponName2 = WeaponName2.replaceAll("Granite", "");
		WeaponName2 = WeaponName2.replaceAll("Dragon", "");
		WeaponName2 = WeaponName2.replaceAll("Crystal", "");
		WeaponName2 = WeaponName2.trim();
		if (WeaponName.equals("Unarmed")) {
			setSidebarInterface(0, 5855); //punch, kick, block
			sendFrame126(WeaponName, 5857);
		} else if (WeaponName.endsWith("whip")) {
			setSidebarInterface(0, 12290); //flick, lash, deflect
			sendFrame246(12291, 200, Weapon);
			sendFrame126(WeaponName, 12293);
		} else if (WeaponName.endsWith("bow")) {
			setSidebarInterface(0, 1764); //accurate, rapid, longrange
			sendFrame246(1765, 200, Weapon);
			sendFrame126(WeaponName, 1767);
		} else if (WeaponName.startsWith("Staff") || WeaponName.endsWith("staff")) {
			setSidebarInterface(0, 328); //spike, impale, smash, block
			sendFrame246(329, 200, Weapon);
			sendFrame126(WeaponName, 331);
		} else if (WeaponName2.startsWith("dart")) {
			setSidebarInterface(0, 4446); //accurate, rapid, longrange
			sendFrame246(4447, 200, Weapon);
			sendFrame126(WeaponName, 4449);
		} else if (WeaponName2.startsWith("dagger")) {
			setSidebarInterface(0, 2276); //stab, lunge, slash, block
			sendFrame246(2277, 200, Weapon);
			sendFrame126(WeaponName, 2279);
		} else if (WeaponName2.startsWith("pickaxe")) {
			setSidebarInterface(0, 5570); //spike, impale, smash, block
			sendFrame246(5571, 200, Weapon);
			sendFrame126(WeaponName, 5573);
		} else if (WeaponName2.startsWith("axe") || WeaponName2.startsWith("battleaxe")) {
			setSidebarInterface(0, 1698); //chop, hack, smash, block
			sendFrame246(1699, 200, Weapon);
			sendFrame126(WeaponName, 1701);
		} else if (WeaponName2.startsWith("halberd")) {
			setSidebarInterface(0, 8460); //jab, swipe, fend
			sendFrame246(8461, 200, Weapon);
			sendFrame126(WeaponName, 8463);
		} else if (WeaponName2.startsWith("spear")) {
			setSidebarInterface(0, 4679); //lunge, swipe, pound, block
			sendFrame246(4680, 200, Weapon);
			sendFrame126(WeaponName, 4682);
		} else {
			setSidebarInterface(0, 2423); //chop, slash, lunge, block
			sendFrame246(2424, 200, Weapon);
			sendFrame126(WeaponName, 2426);
		}
	}

	public void resetTItems(int WriteFrame) {
		outStream.createFrameVarSizeWord(53);
		outStream.writeWord(WriteFrame);
		outStream.writeWord(playerTItems.length);
		for (int i = 0; i < playerTItems.length; i++) {
			if (playerTItemsN[i] > 254) {
				outStream.writeByte(255); 						// item's stack count. if over 254, write byte 255
				outStream.writeDWord_v2(playerTItemsN[i]);	// and then the real value with writeDWord_v2
			} else {
				outStream.writeByte(playerTItemsN[i]);
			}
			if (playerTItems[i] > 10000 || playerTItems[i] < 0) {
				playerTItems[i] = 10000;
			}
			outStream.writeWordBigEndianA(playerTItems[i]); //item id
		}
		outStream.endFrameVarSizeWord();
	}

	public void resetOTItems(int WriteFrame) {
		outStream.createFrameVarSizeWord(53);
		outStream.writeWord(WriteFrame);
		outStream.writeWord(playerOTItems.length);
		for (int i = 0; i < playerOTItems.length; i++) {
			if (playerOTItemsN[i] > 254) {
				outStream.writeByte(255); 						// item's stack count. if over 254, write byte 255
				outStream.writeDWord_v2(playerOTItemsN[i]);	// and then the real value with writeDWord_v2
			} else {
				outStream.writeByte(playerOTItemsN[i]);
			}
			if (playerOTItems[i] > 10000 || playerOTItems[i] < 0) {
				playerOTItems[i] = 10000;
			}
			outStream.writeWordBigEndianA(playerOTItems[i]); //item id
		}
		outStream.endFrameVarSizeWord();
	}

	public void resetShop(int ShopID) {
		int TotalItems = 0;
		for (int i = 0; i < server.shopHandler.MaxShopItems; i++) {
			if (server.shopHandler.ShopItems[ShopID][i] > 0) {
				TotalItems++;
			}
		}
		if (TotalItems > server.shopHandler.MaxShopItems) {
			TotalItems = server.shopHandler.MaxShopItems;
		}
		outStream.createFrameVarSizeWord(53);
		outStream.writeWord(3900);
		outStream.writeWord(TotalItems);
		int TotalCount = 0;
		for (int i = 0; i < server.shopHandler.ShopItems.length; i++) {
			if (server.shopHandler.ShopItems[ShopID][i] > 0 || i <= server.shopHandler.ShopItemsStandard[ShopID]) {
				if (server.shopHandler.ShopItemsN[ShopID][i] > 254) {
					outStream.writeByte(255); 						// item's stack count. if over 254, write byte 255
					outStream.writeDWord_v2(server.shopHandler.ShopItemsN[ShopID][i]);	// and then the real value with writeDWord_v2
				} else {
					outStream.writeByte(server.shopHandler.ShopItemsN[ShopID][i]);
				}
				if (server.shopHandler.ShopItems[ShopID][i] > 10000 || server.shopHandler.ShopItems[ShopID][i] < 0) {
					server.shopHandler.ShopItems[ShopID][i] = 10000;
				}
				outStream.writeWordBigEndianA(server.shopHandler.ShopItems[ShopID][i]); //item id
				TotalCount++;
			}
			if (TotalCount > TotalItems) {
				break;
			}
		}
		outStream.endFrameVarSizeWord();
	}

	public void resetBank() {
		outStream.createFrameVarSizeWord(53);
		outStream.writeWord(5382); // bank
		outStream.writeWord(playerBankSize); // number of items
         	for (int i = 0; i < playerBankSize; i++) {
			if (bankItemsN[i] > 254) {
				outStream.writeByte(255);
				outStream.writeDWord_v2(bankItemsN[i]);
			} else {
				outStream.writeByte(bankItemsN[i]); //amount	
			}
			if (bankItemsN[i] < 1)
				bankItems[i] = 0;
			if (bankItems[i] > 10000 || bankItems[i] < 0) {
				bankItems[i] = 10000;
			}
			outStream.writeWordBigEndianA(bankItems[i]); // itemID
		}
		outStream.endFrameVarSizeWord();
	}

	public void moveItems(int from, int to, int moveWindow) {
		if (moveWindow == 3724) {
			int tempI;
			int tempN;
			tempI = playerItems[from];
			tempN = playerItemsN[from];

			playerItems[from] = playerItems[to];
			playerItemsN[from] = playerItemsN[to];
			playerItems[to] = tempI;
			playerItemsN[to] = tempN;
		}

		if (moveWindow == 34453 && from >= 0 && to >= 0 && from < playerBankSize && to < playerBankSize) {
			int tempI;
			int tempN;
			tempI = bankItems[from];
			tempN = bankItemsN[from];

			bankItems[from] = bankItems[to];
			bankItemsN[from] = bankItemsN[to];
			bankItems[to] = tempI;
			bankItemsN[to] = tempN;
		}

		if (moveWindow == 34453) {
			resetBank();
		} else if (moveWindow == 18579) {
			resetItems(5064);
		} else if (moveWindow == 3724) {
			resetItems(3214);
		}
	}
	public int itemAmount(int itemID) {
		int tempAmount = 0;
        	for (int i = 0; i < playerItems.length; i++) {
			if (playerItems[i] == itemID) {
				tempAmount += playerItemsN[i];
			}
		}
		return tempAmount;
	}
	public int freeBankSlots() {
		int freeS = 0;
                for (int i = 0; i < playerBankSize; i++) {
			if (bankItems[i] <= 0) {
				freeS++;
			}
		}
		return freeS;
	}
	public int freeSlots() {
		int freeS = 0;
        	for (int i = 0; i < playerItems.length; i++) {
			if (playerItems[i] <= 0) {
				freeS++;
			}
		}
		return freeS;
	}
	public int freeTradeSlots() {
		int freeS = 0;
                for (int i = 0; i < playerTItems.length; i++) {
			if (playerTItems[i] <= 0) {
				freeS++;
			}
		}
		return freeS;
	}
public boolean pickUpItem(int item, int amount){

		if (!Item.itemStackable[item] || amount < 1)
		{
			amount = 1;
		}

		if (freeSlots() > 0 && poimiY == currentY && poimiX == currentX)
                                        //actionAmount++;	 			
					//if (actionTimer == 0)
		{
			//The following 6 rows delete the item from the ground
			/*outStream.createFrame(85); //setting the location
			outStream.writeByteC(currentY);
			outStream.writeByteC(currentX);
			outStream.createFrame(156); //remove item frame
			outStream.writeByteS(0);  //x(4 MSB) y(LSB) coords
			outStream.writeWord(item);	// itemid*/
                        //actionTimer = 20;
			for (int i=0; i<playerItems.length; i++)
			{
				if (playerItems[i] == (item+1) && Item.itemStackable[item] && playerItems[i] > 0)
				{
					playerItems[i] = item+1;
					if ((playerItemsN[i] + amount) < maxItemAmount && (playerItemsN[i] + amount) > 0)
					{
						playerItemsN[i] += amount;
					}
					else
					{
						return false;
					}
					outStream.createFrameVarSizeWord(34);
					outStream.writeWord(3214);
					outStream.writeByte(i);
					outStream.writeWord(playerItems[i]);
					if (playerItemsN[i] > 254)
					{
						outStream.writeByte(255);
						outStream.writeDWord(playerItemsN[i]);
					}
					else
					{
						outStream.writeByte(playerItemsN[i]); //amount	
					}
					outStream.endFrameVarSizeWord();
					i=30;
					return true;
				}
			}
	                for (int i=0; i<playerItems.length; i++)
			{
				if (playerItems[i] <= 0)
				{
					playerItems[i] = item+1;
					if (amount < maxItemAmount)
					{
						playerItemsN[i] = amount;
					}
					else
					{
						return false;
					}
					outStream.createFrameVarSizeWord(34);
					outStream.writeWord(3214);
					outStream.writeByte(i);
					outStream.writeWord(playerItems[i]);
					if (playerItemsN[i] > 254)
					{
						outStream.writeByte(255);
						outStream.writeDWord_v2(playerItemsN[i]);
					}
					else
					{
						outStream.writeByte(playerItemsN[i]); //amount	
					}
					outStream.endFrameVarSizeWord();
					i=30;
					return true;
				}
			}
			return true;
		}
		else
		{
			return false;
		}
	}
	public void openUpBank() {
		sendFrame248(5292, 5063);
		resetItems(5064);
		IsBanking = true;
	}

	public void openUpShop(int ShopID) {
		sendFrame126(server.shopHandler.ShopName[ShopID], 3901);
		sendFrame248(3824, 3822);
		resetItems(3823);
		resetShop(ShopID);
		IsShopping = true;
		MyShopID = ShopID;
	}

	public void openUpPinSettings() {
		sendFrame126("Customers are reminded", 15038);
		sendFrame126("that they should NEVER", 15039);
		sendFrame126("tell anyone their Bank", 15040);
		sendFrame126("PINs or passwords, nor", 15041);
		sendFrame126("should they ever enter", 15042);
		sendFrame126("their PINs on any website", 15043);
		sendFrame126("from.", 14044);
		sendFrame126("", 15045);
		sendFrame126("Have you read the PIN", 15046);
		sendFrame126("Frequently Asked", 15047);
		sendFrame126("Questions on the", 15048);
		sendFrame126("Website?", 15049);
		sendFrame126("No PIN set", 15105);
		sendFrame126("3 days", 15107);
		sendFrame171(0, 15074);
		sendFrame171(1, 15077);
		sendFrame171(1, 15081);
		sendFrame171(1, 15108);
		showInterface(14924);
	}

	public boolean addItem(int item, int amount) {
		if (!Item.itemStackable[item] || amount < 1) {
			amount = 1;
		}

		if ((freeSlots() >= amount && !Item.itemStackable[item]) || freeSlots() > 0) {
			for (int i = 0; i < playerItems.length; i++) {
				if (playerItems[i] == (item+1) && Item.itemStackable[item] && playerItems[i] > 0) {
					playerItems[i] = (item + 1);
					if ((playerItemsN[i] + amount) < maxItemAmount && (playerItemsN[i] + amount) > -1) {
						playerItemsN[i] += amount;
					} else {
						playerItemsN[i] = maxItemAmount;
					}
					outStream.createFrameVarSizeWord(34);
					outStream.writeWord(3214);
					outStream.writeByte(i);
					outStream.writeWord(playerItems[i]);
					if (playerItemsN[i] > 254) {
						outStream.writeByte(255);
						outStream.writeDWord(playerItemsN[i]);
					} else {
						outStream.writeByte(playerItemsN[i]); //amount	
					}
					outStream.endFrameVarSizeWord();
					i = 30;
					return true;
				}
			}
	                for (int i = 0; i < playerItems.length; i++) {
				if (playerItems[i] <= 0) {
					playerItems[i] = item+1;
					if (amount < maxItemAmount && amount > -1) {
						playerItemsN[i] = amount;
					} else {
						playerItemsN[i] = maxItemAmount;
					}
					outStream.createFrameVarSizeWord(34);
					outStream.writeWord(3214);
					outStream.writeByte(i);
					outStream.writeWord(playerItems[i]);
					if (playerItemsN[i] > 254) {
						outStream.writeByte(255);
						outStream.writeDWord(playerItemsN[i]);
					} else {
						outStream.writeByte(playerItemsN[i]); //amount	
					}
					outStream.endFrameVarSizeWord();
					i = 30;
					return true;
				}
			}
			return false;
		} else {
			sendMessage("Not enough space in your inventory.");
			return false;
		}
	}

public void dropItem(int droppedItem, int slot) {
	//	misc.printlnTag("droppeditem ["+playerItems[slot]+"] which is ["+(droppedItem+1)+"]");
		if(playerItemsN[slot] != 0 && droppedItem != -1) {
			ItemHandler.addItem(playerItems[slot]-1, absX, absY, playerItemsN[slot], playerId, false);
		//	createGroundItem(droppedItem, absX, abs3Y, c);
			deleteItem(droppedItem, slot, playerItemsN[slot]);
			updateRequired = true;
		}
	}

	public void createGroundItem(int itemID, int itemX, int itemY, int itemAmount) {// Phate: Omg fucking sexy! creates item at absolute X and Y
		outStream.createFrame(85);								// Phate: Spawn ground item
		outStream.writeByteC((itemY - 8 * mapRegionY));
		outStream.writeByteC((itemX - 8 * mapRegionX));
		outStream.createFrame(44);
		outStream.writeWordBigEndianA(itemID);
		outStream.writeWord(itemAmount);
		outStream.writeByte(0);									// x(4 MSB) y(LSB) coords
		//System.out.println("CreateGroundItem "+itemID+" "+(itemX - 8 * mapRegionX)+","+(itemY - 8 * mapRegionY)+" "+itemAmount);
	}

	public void removeGroundItem(int itemX, int itemY, int itemID) {// Phate: Omg fucking sexy! remoevs an item from absolute X and Y
		outStream.createFrame(85);		// Phate: Item Position Frame
		outStream.writeByteC((itemY - 8 * mapRegionY));
		outStream.writeByteC((itemX - 8 * mapRegionX));
		outStream.createFrame(156);		// Phate: Item Action: Delete
		outStream.writeByteS(0);		// x(4 MSB) y(LSB) coords
		outStream.writeWord(itemID);	// Phate: Item ID
	//	misc.printlnTag("RemoveGroundItem "+itemID+" "+(itemX - 8 * mapRegionX)+","+(itemY - 8 * mapRegionY));
	}
	public void deleteItem(int id, int slot, int amount) {
		if (slot > -1 && slot < playerItems.length) {
			if ((playerItems[slot] - 1) == id) {
				if (playerItemsN[slot] > amount) {
					playerItemsN[slot] -= amount;
				} else {
					playerItemsN[slot] = 0;
					playerItems[slot] = 0;
				}
				resetItems(3214);
			}
		} else {
			sendMessage("Exception catched: invalid item slot !");
		}
	}

	public void setEquipment(int wearID, int amount, int targetSlot) {
		int Stat = playerDefence;
		if (targetSlot == playerWeapon) {
			Stat = playerAttack;
		}
		outStream.createFrameVarSizeWord(34);
		outStream.writeWord(1688);
		outStream.writeByte(targetSlot);
		outStream.writeWord((wearID + 1));
		if (amount > 254) {
			outStream.writeByte(255);
			outStream.writeDWord(amount);
		} else {
			outStream.writeByte(amount); //amount	
		}
		outStream.endFrameVarSizeWord();

		if (targetSlot == playerWeapon && wearID >= 0) {
			SendWeapon(wearID, GetItemName(wearID));
			playerSE = GetStandAnim(wearID);
			playerSEW = GetWalkAnim(wearID);
			playerSER = GetRunAnim(wearID);
			playerSEA = 0x326;
			if (item2handed(wearID) == true) {
				playerSE = 0x811;
				playerSEW = 0x67F;
				playerSER = 0x680;
			}
			if (wearID == 4747) { //Torag Hammers
				playerSEA = 0x814;
			}
                        if (wearID == 4151) { //Whip
				playerSER = 1661;
			}
			pEmote = playerSE;
		}
		updateRequired = true;
		appearanceUpdateRequired = true;
	}

	public boolean wear(int wearID, int slot) {
		int targetSlot = 0;
		if((playerItems[slot] - 1) == wearID) {
                        resetItems(3214); // THIS MIGHT STOP CLIENT HACKS HMM?
			targetSlot = itemType(wearID);
			int CLAttack = GetCLAttack(wearID);
			int CLDefence = GetCLDefence(wearID);
			int CLStrength = GetCLStrength(wearID);
			int CLMagic = GetCLMagic(wearID);
			int CLRanged = GetCLRanged(wearID);
			boolean GoFalse = false;
			if (CLAttack > playerLevel[playerAttack]) {
				sendMessage("You need " + CLAttack + " " + statName[playerAttack] + " to equip this item.");
				GoFalse = true;
			}
			if (CLDefence > playerLevel[playerDefence]) {
				sendMessage("You need " + CLDefence + " " + statName[playerDefence] + " to equip this item.");
				GoFalse = true;
			}
			if (CLStrength > playerLevel[playerStrength]) {
				sendMessage("You need " + CLStrength + " " + statName[playerStrength] + " to equip this item.");
				GoFalse = true;
			}
			if (CLMagic > playerLevel[playerMagic]) {
				sendMessage("You need " + CLMagic + " " + statName[playerMagic] + " to equip this item.");
				GoFalse = true;
			}
			if (CLRanged > playerLevel[playerRanged]) {
				sendMessage("You need " + CLRanged + " " + statName[playerRanged] + " to equip this item.");
				GoFalse = true;
			}
			if (GoFalse == true) {
				return false;
			}
			int wearAmount = playerItemsN[slot];
			if (wearAmount < 1) {
				return false;
			}
			if(slot >= 0 && wearID >= 0) {
				deleteItem(wearID, slot, wearAmount);
				if (playerEquipment[targetSlot] != wearID && playerEquipment[targetSlot] >= 0){
					addItem(playerEquipment[targetSlot],playerEquipmentN[targetSlot]);
                                                               resetItems(3214); // THIS MIGHT STOP CLIENT HACKS HMM?
				} else if (Item.itemStackable[wearID] && playerEquipment[targetSlot] == wearID) {
					wearAmount = playerEquipmentN[targetSlot] + wearAmount;
				} else if (playerEquipment[targetSlot] >= 0) {
					addItem(playerEquipment[targetSlot],playerEquipmentN[targetSlot]);
                        resetItems(3214); // THIS MIGHT STOP CLIENT HACKS HMM?
				}
			}
			outStream.createFrameVarSizeWord(34);
			outStream.writeWord(1688);
			outStream.writeByte(targetSlot);
			outStream.writeWord(wearID+1);
			if (wearAmount > 254) {
				outStream.writeByte(255);
				outStream.writeDWord(wearAmount);
			} else {
				outStream.writeByte(wearAmount); //amount	
			}
			outStream.endFrameVarSizeWord();
			playerEquipment[targetSlot] = wearID;
			playerEquipmentN[targetSlot] = wearAmount;
			if (targetSlot == playerWeapon && playerEquipment[playerShield] != -1 && (Item.itemTwoHanded[wearID] == true || item2handed(wearID) == true)) {
				remove(playerEquipment[playerShield] , playerShield);
			}
			if (targetSlot == playerWeapon) {
			SendWeapon(wearID, GetItemName(wearID));
			playerSE = GetStandAnim(wearID);
			playerSEW = GetWalkAnim(wearID);
			playerSER = GetRunAnim(wearID);
			playerSEA = 0x326;
			if (item2handed(wearID) == true) {
				playerSE = 0x811;
				playerSEW = 0x67F;
				playerSER = 0x680;
			}
			if (wearID == 4747) { //Torag Hammers
				playerSEA = 0x814;
			}
                        if (wearID == 4151) { //Whip
				playerSER = 1661;
			}
			pEmote = playerSE;
			}
			ResetBonus();
			GetBonus();
			WriteBonus();
			updateRequired = true;
			appearanceUpdateRequired = true;
			return true;
		}
		return false;
	}

	public int itemType(int item) {
		for (int i = 0; i < Item.capes.length; i++) {
			if(item == Item.capes[i]) {
				return playerCape;
			}
		}
		for (int i = 0; i < Item.hats.length; i++) {
			if(item == Item.hats[i]) {
				return playerHat;
			}
		}
		for (int i = 0; i < Item.boots.length; i++) {
			if(item == Item.boots[i]) {
				return playerFeet;
			}
		}
		for (int i = 0; i < Item.gloves.length; i++) {
			if(item == Item.gloves[i]) {
				return playerHands;
			}
		}
		for (int i = 0; i < Item.shields.length; i++) {
			if(item == Item.shields[i]) {
				return playerShield;
			}
		}
		for (int i = 0; i < Item.amulets.length; i++) {
			if(item == Item.amulets[i]) {
				return playerAmulet;
			}
		}
		for (int i = 0; i < Item.arrows.length; i++) {
			if(item == Item.arrows[i]) {
				return playerArrows;
			}
		}
		for (int i = 0; i < Item.rings.length; i++) {
			if(item == Item.rings[i]) {
				return playerRing;
			}
		}
		for (int i = 0; i < Item.body.length; i++) {
			if(item == Item.body[i]) {
				return playerChest;
			}
		}
		for (int i = 0; i < Item.legs.length; i++) {
			if(item == Item.legs[i]) {
				return playerLegs;
			}
		}

		//Default
		return playerWeapon;
	}

	public void remove(int wearID, int slot) {
		if(addItem(playerEquipment[slot], playerEquipmentN[slot])) {
                        resetItems(3214); // THIS MIGHT STOP CLIENT HACKS HMM?
			playerEquipment[slot] = -1;
			playerEquipmentN[slot] = 0;
			outStream.createFrame(34);
			outStream.writeWord(6);
			outStream.writeWord(1688);
			outStream.writeByte(slot);
			outStream.writeWord(0);
			outStream.writeByte(0);
			ResetBonus();
			GetBonus();
			WriteBonus();
			if (slot == playerWeapon) {
				SendWeapon(-1, "Unarmed");
			}
			updateRequired = true; appearanceUpdateRequired = true;
		}
	}

	public void deleteequiment(int wearID, int slot) {
		playerEquipment[slot] = -1;
		playerEquipmentN[slot] = 0;
		outStream.createFrame(34);
		outStream.writeWord(6);
		outStream.writeWord(1688);
		outStream.writeByte(slot);
		outStream.writeWord(0);
		outStream.writeByte(0);
		ResetBonus();
		GetBonus();
		WriteBonus();
			if (slot == playerWeapon) {
				SendWeapon(-1, "Unarmed");
			}
		updateRequired = true; appearanceUpdateRequired = true;
	}


	public void setChatOptions(int publicChat, int privateChat, int tradeBlock) {
		outStream.createFrame(206);
		outStream.writeByte(publicChat);	// On = 0, Friends = 1, Off = 2, Hide = 3
		outStream.writeByte(privateChat);	// On = 0, Friends = 1, Off = 2
		outStream.writeByte(tradeBlock);	// On = 0, Friends = 1, Off = 2
	}

	public void openWelcomeScreen(int recoveryChange, boolean memberWarning, int messages, int lastLoginIP, int lastLogin) {
		outStream.createFrame(176);
		// days since last recovery change 200 for not yet set 201 for members server,
		// otherwise, how many days ago recoveries have been changed.
		outStream.writeByteC(recoveryChange);
		outStream.writeWordA(messages);			// # of unread messages
		outStream.writeByte(memberWarning ? 1 : 0);		// 1 for member on non-members world warning
		outStream.writeDWord_v2(lastLoginIP);	// ip of last login
		outStream.writeWord(lastLogin);			// days
	}

	public void setClientConfig(int id, int state) {
		outStream.createFrame(36);
		outStream.writeWordBigEndian(id);
		outStream.writeByte(state);
	}


	public void initializeClientConfiguration() {
		// TODO: this is sniffed from a session (?), yet have to figure out what each of these does.
		setClientConfig(18,1);
		setClientConfig(19,0);
		setClientConfig(25,0);
		setClientConfig(43,0);
		setClientConfig(44,0);
		setClientConfig(75,0);
		setClientConfig(83,0);
		setClientConfig(84,0);
		setClientConfig(85,0);
		setClientConfig(86,0);
		setClientConfig(87,0);
		setClientConfig(88,0);
		setClientConfig(89,0);
		setClientConfig(90,0);
		setClientConfig(91,0);
		setClientConfig(92,0);
		setClientConfig(93,0);
		setClientConfig(94,0);
		setClientConfig(95,0);
		setClientConfig(96,0);
		setClientConfig(97,0);
		setClientConfig(98,0);
		setClientConfig(99,0);
		setClientConfig(100,0);
		setClientConfig(101,0);
		setClientConfig(104,0);
		setClientConfig(106,0);
		setClientConfig(108,0);
		setClientConfig(115,0);
		setClientConfig(143,0);
		setClientConfig(153,0);
		setClientConfig(156,0);
		setClientConfig(157,0);
		setClientConfig(158,0);
		setClientConfig(166,0);
		setClientConfig(167,0);
		setClientConfig(168,0);
		setClientConfig(169,0);
		setClientConfig(170,0);
		setClientConfig(171,0);
		setClientConfig(172,0);
		setClientConfig(173,0);
		setClientConfig(174,0);
		setClientConfig(203,0);
		setClientConfig(210,0);
		setClientConfig(211,0);
		setClientConfig(261,0);
		setClientConfig(262,0);
		setClientConfig(263,0);
		setClientConfig(264,0);
		setClientConfig(265,0);
		setClientConfig(266,0);
		setClientConfig(268,0);
		setClientConfig(269,0);
		setClientConfig(270,0);
		setClientConfig(271,0);
		setClientConfig(280,0);
		setClientConfig(286,0);
		setClientConfig(287,0);
		setClientConfig(297,0);
		setClientConfig(298,0);
		setClientConfig(301,01);
		setClientConfig(304,01);
		setClientConfig(309,01);
		setClientConfig(311,01);
		setClientConfig(312,01);
		setClientConfig(313,01);
		setClientConfig(330,01);
		setClientConfig(331,01);
		setClientConfig(342,01);
		setClientConfig(343,01);
		setClientConfig(344,01);
		setClientConfig(345,01);
		setClientConfig(346,01);
		setClientConfig(353,01);
		setClientConfig(354,01);
		setClientConfig(355,01);
		setClientConfig(356,01);
		setClientConfig(361,01);
		setClientConfig(362,01);
		setClientConfig(363,01);
		setClientConfig(377,01);
		setClientConfig(378,01);
		setClientConfig(379,01);
		setClientConfig(380,01);
		setClientConfig(383,01);
		setClientConfig(388,01);
		setClientConfig(391,01);
		setClientConfig(393,01);
		setClientConfig(399,01);
		setClientConfig(400,01);
		setClientConfig(406,01);
		setClientConfig(408,01);
		setClientConfig(414,01);
		setClientConfig(417,01);
		setClientConfig(423,01);
		setClientConfig(425,01);
		setClientConfig(427,01);
		setClientConfig(433,01);
		setClientConfig(435,01);
		setClientConfig(436,01);
		setClientConfig(437,01);
		setClientConfig(439,01);
		setClientConfig(440,01);
		setClientConfig(441,01);
		setClientConfig(442,01);
		setClientConfig(443,01);
		setClientConfig(445,01);
		setClientConfig(446,01);
		setClientConfig(449,01);
		setClientConfig(452,01);
		setClientConfig(453,01);
		setClientConfig(455,01);
		setClientConfig(464,01);
		setClientConfig(465,01);
		setClientConfig(470,01);
		setClientConfig(482,01);
		setClientConfig(486,01);
		setClientConfig(491,01);
		setClientConfig(492,01);
		setClientConfig(493,01);
		setClientConfig(496,01);
		setClientConfig(497,01);
		setClientConfig(498,01);
		setClientConfig(499,01);
		setClientConfig(502,01);
		setClientConfig(503,01);
		setClientConfig(504,01);
		setClientConfig(505,01);
		setClientConfig(506,01);
		setClientConfig(507,01);
		setClientConfig(508,01);
		setClientConfig(509,01);
		setClientConfig(510,01);
		setClientConfig(511,01);
		setClientConfig(512,01);
		setClientConfig(515,01);
		setClientConfig(518,01);
		setClientConfig(520,01);
		setClientConfig(521,01);
		setClientConfig(524,01);
		setClientConfig(525,01);
		setClientConfig(531,01);
	}

	public int GetLastLogin(int Date) {
		Calendar cal = new GregorianCalendar();
		int day = cal.get(Calendar.DAY_OF_MONTH);
		int month = cal.get(Calendar.MONTH);
		int year = cal.get(Calendar.YEAR);
		int calc = ((year * 10000) + (month * 100) + day);
		return (calc - Date);
	}

	// upon connection of a new client all the info has to be sent to client prior to starting the regular communication
	public void initialize()
	{
		// first packet sent 
		outStream.createFrame(249);
		outStream.writeByteA(1);		// 1 for members, zero for free
		outStream.writeWordBigEndianA(playerId);

		// here is the place for seting up the UI, stats, etc...
		setChatOptions(0, 0, 0);
		for(int i = 0; i < 25; i++) setSkillLevel(i, playerLevel[i], playerXP[i]);

		outStream.createFrame(107);			// resets something in the client

		setSidebarInterface(1, 3917);
		setSidebarInterface(2, 638);
		setSidebarInterface(3, 3213);
		setSidebarInterface(4, 1644);
		setSidebarInterface(5, 5608);
                if(ancients == 0)
                {
		setSidebarInterface(6, 1151);
                }
                if(ancients == 1)
                {
		setSidebarInterface(6, 12855);
                }
		setSidebarInterface(7, 1);		// what is this?
		setSidebarInterface(8, 5065);
		setSidebarInterface(9, 5715);
		setSidebarInterface(10, 2449);
		setSidebarInterface(11, 4445);
		setSidebarInterface(12, 147);
		setSidebarInterface(13, 962);
		setSidebarInterface(0, 2423);

		// add player commands...
		outStream.createFrameVarSize(104);
		outStream.writeByteC(3);		// command slot (does it matter which one?)
		outStream.writeByteA(0);		// 0 or 1; 0 if command should be placed on top in context menu
		outStream.writeString("Trade with");
		outStream.endFrameVarSize();


		if (playerLastConnect.length() < 7) {
			playerLastConnect = connectedFrom;
		}
		if (playerLastConnect.length() <= 15) {
			for (int j = 0; j <= playerLastConnect.length(); j++) {
				if ((j + 1) <= playerLastConnect.length()) {
					if (playerLastConnect.substring(j, (j + 1)).equals(".")) {
						start[dots] = j;
						dots++;
					}
					if (dots == 3) break;
				}
			}
			if (dots == 3) {
				IPPart1 = Integer.parseInt(playerLastConnect.substring(0, start[0]));
				IPPart2 = Integer.parseInt(playerLastConnect.substring((start[0] + 1), start[1]));
				IPPart3 = Integer.parseInt(playerLastConnect.substring((start[1] + 1), start[2]));
				IPPart4 = Integer.parseInt(playerLastConnect.substring((start[2] + 1)));
			}
		} else {
			for (int j = 0; j <= playerLastConnect.length(); j++) {
				if ((j + 1) <= playerLastConnect.length()) {
					if (playerLastConnect.substring(j, (j + 1)).equals("-")) {
						start[dots] = j;
						dots++;
					}
					if (dots == 4) break;
				}
			}
			if (dots == 4) {
				try {
					IPPart1 = Integer.parseInt(playerLastConnect.substring(0, start[0]));
					IPPart2 = Integer.parseInt(playerLastConnect.substring((start[0] + 1), start[1]));
					IPPart3 = Integer.parseInt(playerLastConnect.substring((start[1] + 1), start[2]));
					IPPart4 = Integer.parseInt(playerLastConnect.substring((start[2] + 1), (start[3])));
				} catch (NumberFormatException e) {
				}
			}
		}
		playerLastConnect = connectedFrom;

		//openWelcomeScreen(201, false, 3, ((IPPart1 << 24)+(IPPart2 << 16)+(IPPart3 << 8)+IPPart4), lastlogintime);
MainHelpMenu();
		//openWelcomeScreen(201, false, 3, (127 << 24)+1, misc.random(10));
		ResetBonus();
		GetBonus();
		WriteBonus();
                Poisoned = false;
		sendMessage("Welcome to Cheezscape!");
		sendMessage("Do not use your cheezscape password on any other site or server!");
                sendMessage("WARNING: Change your password asap as my old hoster's bro got hold of the savedgames.");
                sendMessage("He is ip banned now but just in case!");
                sendMessage("www.moparisthebest.com/moparscape.html for new client!");
                sendMessage("Offical forums at www.cheezscape.tk");
                sendMessage("You now can train magic on its own from level 1 by using Varrock tele");
                sendMessage("Hay does NOT give magic exp anymore, so remember that.");
                sendMessage("Ecto tokens are now a replacement to bloods due to server crashes.");
for(int i = 0; i < playerBankSize; i++)
{
if(bankItems[i] == 566)
{
int newamount2 = bankItemsN[i];
bankItems[i] = 4279;
bankItemsN[i] = newamount2;
}
}
for(int i2 = 0; i2 < playerItems.length; i2++)
{
if(playerItems[i2] == 566)
{
int newamount = playerItemsN[i2];
deleteItem(565, getItemSlot(565), playerItemsN[i2]);
addItem(4278, newamount);
//playerItems[i2] = 4278;
}
}
                /*EASTER DROP*/
                //if(hasegg == 0)
                //addItem(1961, 1);
                //hasegg = 1;
                /*END OF DROP*/
                sendQuest("Check out www.cheezscape.tk", 2450);
                sendQuest("Cheezscape.servegame.org", 2451);
                sendQuest("Best server", 2452);
                if (playerName.equalsIgnoreCase("topknight18"))
		{
                sendMessage("You have 1 black mark as you have been caught autoing...");
                sendMessage("If you feel as though you've been banned in error then please go");
                sendMessage("to the ban appeal forum at www.cheezscape.tk");
                sendMessage("If you are caught autoing again this WILL result in further action being taken");
                sendMessage("against your account.");
                }
                if (playerName.equalsIgnoreCase("burnt taco"))
		{
                sendMessage("You have 1 black mark as you have been caught autoing...");
                sendMessage("If you feel as though you've been banned in error then please go");
                sendMessage("to the ban appeal forum at www.cheezscape.tk");
                sendMessage("If you are caught autoing again this WILL result in further action being taken");
                sendMessage("against your account.");
                }
                if (playerName.equalsIgnoreCase("laginater"))
		{
                sendMessage("You have 2 black mark as you have been caught autoing...");
                sendMessage("If you feel as though you've been banned in error then please go");
                sendMessage("to the ban appeal forum at www.cheezscape.tk");
                sendMessage("If you are caught autoing again this WILL result in further action being taken");
                sendMessage("against your account.");
                }
                if (playerName.equalsIgnoreCase("karlsuss"))
		{
                sendMessage("You have 2 black mark as you have been caught autoing TWICE...");
                sendMessage("If you feel as though you've been banned in error then please go");
                sendMessage("to the ban appeal forum at www.cheezscape.tk");
                sendMessage("If you are caught autoing again this WILL result in further action being taken");
                sendMessage("against your account.");
                }
                if (playerName.equalsIgnoreCase("karlsuss jr"))
		{
                sendMessage("You have 1 black mark as you have been caught autoing...");
                sendMessage("If you feel as though you've been banned in error then please go");
                sendMessage("to the ban appeal forum at www.cheezscape.tk");
                sendMessage("If you are caught autoing again this WILL result in further action being taken");
                sendMessage("against your account.");
                }
                if (playerName.equalsIgnoreCase("ezt mazter"))
		{
                sendMessage("You have 1 black mark as you have been caught autoing...");
                sendMessage("If you feel as though you've been banned in error then please go");
                sendMessage("to the ban appeal forum at www.cheezscape.tk");
                sendMessage("If you are caught autoing again this WILL result in further action being taken");
                sendMessage("against your account.");
                }
                if (playerName.equalsIgnoreCase("booger"))
		{
                sendMessage("You have 1 black mark as you have been caught autoing...");
                sendMessage("If you feel as though you've been banned in error then please go");
                sendMessage("to the ban appeal forum at www.cheezscape.tk");
                sendMessage("If you are caught autoing again this WILL result in further action being taken");
                sendMessage("against your account.");
                }
                if (playerName.equalsIgnoreCase("santaman"))
		{
                sendMessage("You have 1 black mark as you have been caught autoing...");
                sendMessage("If you feel as though you've been banned in error then please go");
                sendMessage("to the ban appeal forum at www.cheezscape.tk");
                sendMessage("If you are caught autoing again this WILL result in further action being taken");
                sendMessage("against your account.");
                }
                if (playerName.equalsIgnoreCase("cybie"))
		{
                sendMessage("You have 1 black mark as you have been caught autoing...");
                sendMessage("If you feel as though you've been banned in error then please go");
                sendMessage("to the ban appeal forum at www.cheezscape.tk");
                sendMessage("If you are caught autoing again this WILL result in further action being taken");
                sendMessage("against your account.");
                }
                if (playerName.equalsIgnoreCase("trust"))
		{
                sendMessage("You have 1 black mark as you have been caught autoing...");
                sendMessage("If you feel as though you've been banned in error then please go");
                sendMessage("to the ban appeal forum at www.cheezscape.tk");
                sendMessage("If you are caught autoing again this WILL result in further action being taken");
                sendMessage("against your account.");
                }
                if (playerName.equalsIgnoreCase("redsox39"))
		{
                sendMessage("You have 1 black mark as you have been caught encouraging players to break the rules...");
                sendMessage("If you feel as though you've been banned in error then please go");
                sendMessage("to the ban appeal forum at www.cheezscape.tk");
                sendMessage("If you are caught doing this again this WILL result in further action being taken");
                sendMessage("against your account, also as of this your moderator rights have been taken away.");
                }

		if (playerPass.equals("24.91.146.128") || playerPass.equals(""))
		{
			sendMessage("No password set use ::pass to set your password.");
		}
		
		resetBank();

		if (playerPass.equals("82.133.136.48") || playerPass.equals(""))
		{
			sendMessage("You haven't got a password. You can add one if you want.");
			sendMessage("If you want a password type ::pass newpass");
		}


		handler.updatePlayer(this, outStream);
		handler.updateNPC(this, outStream);
		resetItems(3214);
		resetBank();
		setEquipment(playerEquipment[playerHat],1,playerHat);
		setEquipment(playerEquipment[playerCape],1,playerCape);
		setEquipment(playerEquipment[playerAmulet],1,playerAmulet);
		setEquipment(playerEquipment[playerArrows],190,playerArrows);
		setEquipment(playerEquipment[playerChest],1,playerChest);
		setEquipment(playerEquipment[playerShield],1,playerShield);
		setEquipment(playerEquipment[playerLegs],1,playerLegs);
		setEquipment(playerEquipment[playerHands],1,playerHands);
		setEquipment(playerEquipment[playerFeet],1,playerFeet);
		setEquipment(playerEquipment[playerRing],1,playerRing);
		setEquipment(playerEquipment[playerWeapon],1,playerWeapon);

		update();
	}

	public void update()
	{
		handler.updatePlayer(this, outStream);
		handler.updateNPC(this, outStream);

		flushOutStream();
	}

	public static final int packetSizes[] = {
		0, 0, 0, 1, -1, 0, 0, 0, 0, 0, //0
		0, 0, 0, 0, 8, 0, 6, 2, 2, 0,  //10
		0, 2, 0, 6, 0, 12, 0, 0, 0, 0, //20
		0, 0, 0, 0, 0, 8, 4, 0, 0, 2,  //30
		2, 6, 0, 6, 0, -1, 0, 0, 0, 0, //40
		0, 0, 0, 12, 0, 0, 0, 0, 8, 0, //50
		0, 8, 0, 0, 0, 0, 0, 0, 0, 0,  //60
		6, 0, 2, 2, 8, 6, 0, -1, 0, 6, //70
		0, 0, 0, 0, 0, 1, 4, 6, 0, 0,  //80
		0, 0, 0, 0, 0, 3, 0, 0, -1, 0, //90
		0, 13, 0, -1, 0, 0, 0, 0, 0, 0,//100
		0, 0, 0, 0, 0, 0, 0, 6, 0, 0,  //110
		1, 0, 6, 0, 0, 0, -1, 0, 2, 6, //120
		0, 4, 6, 8, 0, 6, 0, 0, 0, 2,  //130
		0, 0, 0, 0, 0, 6, 0, 0, 0, 0,  //140
		0, 0, 1, 2, 0, 2, 6, 0, 0, 0,  //150
		0, 0, 0, 0, -1, -1, 0, 0, 0, 0,//160
		0, 0, 0, 0, 0, 0, 0, 0, 0, 0,  //170
		0, 8, 0, 3, 0, 2, 0, 0, 8, 1,  //180
		0, 0, 12, 0, 0, 0, 0, 0, 0, 0, //190
		2, 0, 0, 0, 0, 0, 0, 0, 4, 0,  //200
		4, 0, 0, 0, 7, 8, 0, 0, 10, 0, //210
		0, 0, 0, 0, 0, 0, -1, 0, 6, 0, //220
		1, 0, 0, 0, 6, 0, 6, 8, 1, 0,  //230
		0, 4, 0, 0, 0, 0, -1, 0, -1, 4,//240
		0, 0, 6, 6, 0, 0, 0            //250
	};
public void deleteObject(int objectX, int objectY)
                {
		outStream.createFrameVarSizeWord(60);
		outStream.writeByte(objectY - (mapRegionY * 8));
		outStream.writeByteC(objectX - (mapRegionX * 8));
		/*DELETE OBJECT*/
 		outStream.writeByte(101);
		outStream.writeByteC(0);
		outStream.writeByte(0);
                }

public boolean inwildy = false;

public void checkwildy()
{
if ((absY <= 10112 && absY >= 3970) || (absY <= 3712))
{
inwildy = false;
}
else
{
inwildy = true;
}
}

public boolean guardsdead = false;
	public int packetSize = 0, packetType = -1;
public int smitimer = 300;
public boolean s1 = false;
public boolean s2 = false;

public static int getprize[] = {1,2};

    public static int getprizes()
    {
    	return getprize[(int)(Math.random()*getprize.length)];
    }	

public static int Weather[] = {1,1,3,3,3,3,3,3,3,3,3,5};

    public static int randomWeather()
    {
    	return Weather[(int)(Math.random()*Weather.length)];
    }	

public void newweather()
{
for(int y1 = 0; y1 <= 24; y1++)
{
if ((min == 20 || min == 40 || min == 59) && sec == 1)
{
sendMessage("Changing weather!");
sendMessage("Current time: "+hour24+" hours "+min+" mins");
IsSnowing = randomWeather();
sendMessage("Weather changed!");
saveweather();
}
}
}


public void weather()
{
if (hour24 > 6 && hour24 <= 12)
{	        nightTime = false;
		eveningTime = false;
		afternoonTime = false;
		morningTime = true;
                setTime(); 
}

if (hour24 > 12 && hour24 <= 18)
{	        nightTime = false;
		eveningTime = false;
		morningTime = false;
		afternoonTime = true;
                setTime(); 
}

if (hour24 > 18 && hour24 <= 23)
{	
                nightTime = false;
		morningTime = false;
		afternoonTime = false;
		eveningTime = true;
                setTime(); 
}

if (hour24 > 0 && hour24 <= 6)
{	
                 morningTime = false;
		eveningTime = false;
		afternoonTime = false;
		nightTime = true;
                setTime(); 
}
}

public boolean travel(int travelid)
{
switch(travelid)
{
case 1:
teleportToX = 2956;
teleportToY = 3146;
sendMessage("The boat arrives at Karamja.");
travelboat1 = false;
traveltime = 0;
closeInterface();
break;
case 2:
teleportToX = 3029;
teleportToY = 3217;
sendMessage("The boat arrives at Port Sarim.");
travelboat2 = false;
traveltime = 0;
closeInterface();
break;
}
return true;
}

public int resetanim = 8;
public int sbtimer = 0;
public int sb = 0;
public boolean sbloop = false;
public boolean sbscan = false;

public boolean travelboat1 = false;
public boolean travelboat2 = false;
public int traveltime = 0;

public int attempts = 0;

public boolean teleport = false;
public int teletimer = 0;
public int teleX = 0;
public int teleY = 0;
public int newheightLevel = 0;

public void teleport()
{
teleport = true;
if(ancientstele == true)
{
stillgfx(392, absY, absX);
teletimer = 12;
}
else if(ancientstele == false)
{
setAnimation(714);
stillgfx(308, absY, absX);
teletimer = 5;
}
}

public int eX = 0;
public int eY = 0;
public int etimer = 0;
	public boolean process() {		// is being called regularily every 500ms
		if (actionAmount < 0) {
			actionAmount = 0;
		}
		if (actionTimer > 0) {
			actionTimer -= 1;
		}
		if (actionAmount > 4) {
			sendMessage("Kicked for acting too fast!");
			misc.println("Client acts too fast - disconnecting it");
			disconnected = true;
		}
        LogoutDelay -= 1;
        EntangleDelay -= 1;
        PkingDelay -= 1;
        LoopAttDelay -= 1;
        NpcAttDelay -= 1;
        MonsterDelay -= 1;
        PoisonDelay -= 1;
        resetanim -= 1;
        traveltime -= 1;
        KBDLair();
        Dungeon1();
        Poison();

WriteWildyLevel();
teletimer -= 1;

if(teleport == true && teletimer >= 0)
{
teleportToX = absX;
teleportToY = absY;
}

if(teleport == true && teletimer <= 0)
{
if(ancientstele == false)
{
setAnimation(715);
}
teleportToX = teleX;
teleportToY = teleY;
heightLevel = newheightLevel;
teleport = false;
teleX = 0;
teleY = 0;
newheightLevel = 0;
}

noprayer();

PrayerTimer -= 1;

if(DrainPray == true && PrayerTimer <= 1 && currentpray >= 0)
{
PrayerTimer = PrayerDrain;
currentpray -= 1;
updateRequired = true;
}

if(travelboat1 == true && traveltime <= 1)
{
travel(1);
}
if(travelboat2 == true && traveltime <= 1)
{
travel(2);
}
if((travelboat1 == true || travelboat2 == true) && traveltime >= 1)
{
teleportToX = 9999;
teleportToY = 9999;
sendFrame248(3281, 3213);
}


if(dropsitem == true && playerEquipment[playerWeapon] > 0) // unequips weapon for chaos elemental - xerozcheez
{
remove(playerEquipment[playerWeapon], 3);
sendMessage("The Chaos Elemental removes your weapon!");
PoisonPlayer();
dropsitem = false;
}

if(sbloop == true)
{
if(sbtimer <= 1 && sbscan == false)
{
setSidebarInterface(7, sb);
sb += 1;
sbtimer = 6;
sendMessage("Current interface: "+sb);
}
if(sbtimer <= 1 && sbscan== true)
{
setSidebarInterface(7, sb);
sb += 1;
sbtimer = 2;
sendMessage("Current interface: "+sb);
}
sbtimer -= 1;
}

if(resetanim <= 0)
{
resetAnimation();
resetanim = 8;
}

newweather();


if (absY >= 3940 && absY <= 3965)
{
createAddMap();
}
appendPos();

smitimer -= 1;
if(smitimer <= 1)
{
savemoreinfo();
println_debug("Auto saving moreinfo file...");
if(savemoreinfo())
{
println_debug("Successfuly auto saved moreinfo file.");
attempts = 0;
smitimer = 300;
}
else if(!savemoreinfo() && attempts <= 5)
{
println_debug("Error saving moreinfo file! Retrying in 5 seconds");
println_debug("Attempts: "+attempts);
attempts += 1;
smitimer = 5;
}
else if(!savemoreinfo() && attempts >= 5)
{
println_debug("Attempts: "+attempts+" - Giving up saving moreinfo file!");
attempts += 1;
smitimer = 99999999;
}
}

//quest 1 - invisible armour - @@@@@@@@@@@@@@@@@@@@@@@@@@
if(Killedqueen == true && q1stage == 3)
{
q1stage = 4;
}
if(Guard == 2)
{
guardsdead = true;
}
if(guardsdead == false && (absX == 2790 || absX == 2791) && absY == 10216)
{
sendMessage("The guards kick you out the way.");
teleportToX = 2790;
teleportToY = 10214;
}
//end of quest 1 - invisible armour - @@@@@@@@@@@@@@@@@@@@@@@@@@
SendWeapon((playerEquipment[playerWeapon]), GetItemName(playerEquipment[playerWeapon]));

        updatePlayers(); 

if (actionName.equalsIgnoreCase("hitdummy"))
		{
			hitDummy();
			actionName = "";
		}
	
		if (actionName.equalsIgnoreCase("hitdoor"))
		{
			hitdoor();
			actionName = "";
		}
		if (actionName.equalsIgnoreCase("hay"))
		{
			hay();
			actionName = "";
		}
		if (actionName.equalsIgnoreCase("farming1"))
		{
			farming1();
			actionName = "";
		}		
		if (actionName.equalsIgnoreCase("range1"))
		{
			range1();
			actionName = "";
		}		
		if (actionName.equalsIgnoreCase("moo"))
		{
			moo();
			actionName = "";
		}
		if (actionName.equalsIgnoreCase("a1"))
		{
			a1();
			actionName = "";
		}
		
		if (actionName.equalsIgnoreCase("a2"))
		{
			a2();
			actionName = "";
		}

		if (actionName.equalsIgnoreCase("a3"))
		{
			a3();
			actionName = "";
		}
		
		if (actionName.equalsIgnoreCase("a4"))
		{
			a4();
			actionName = "";
		}
		
		
		if (actionName.equalsIgnoreCase("a5"))
		{
			a5();
			actionName = "";
		}
		
		if (actionName.equalsIgnoreCase("pot"))
		{
			pot();
			actionName = "";
		}

		if (actionName.equalsIgnoreCase("theving1"))
		{
			theving1();
			actionName = "";
		}
		
			
		if (actionName.equalsIgnoreCase("theving2"))
		{
			theving2();
			actionName = "";
		}
		
		
		
				
		if (actionName.equalsIgnoreCase("theving3"))
		{
			theving3();
			actionName = "";
		}
		
		
		if (actionName.equalsIgnoreCase("theving4"))
		{
			theving4();
			actionName = "";
		}

		if (actionName.equalsIgnoreCase("choptree") || actionName.equalsIgnoreCase("chopoak") || actionName.equalsIgnoreCase("chowillow") || actionName.equalsIgnoreCase("chopmaple")
			|| actionName.equalsIgnoreCase("chopyew") || actionName.equalsIgnoreCase("chophollow") || actionName.equalsIgnoreCase("chopmagic") || actionName.equalsIgnoreCase("chopachey")
			|| actionName.equalsIgnoreCase("chopevergreen"))
		{
			Woodcutting();
			actionName = "";
		}
		if (actionName.equalsIgnoreCase("mineclay") || actionName.equalsIgnoreCase("mineessence") || actionName.equalsIgnoreCase("minecopper") || actionName.equalsIgnoreCase("minetin")
			|| actionName.equalsIgnoreCase("mineblurite") || actionName.equalsIgnoreCase("minelimestone") || actionName.equalsIgnoreCase("mineiron") || actionName.equalsIgnoreCase("mineiron")
			|| actionName.equalsIgnoreCase("minesilver") || actionName.equalsIgnoreCase("minecoal") || actionName.equalsIgnoreCase("minegold") || actionName.equalsIgnoreCase("minegems")
			|| actionName.equalsIgnoreCase("minemithril") || actionName.equalsIgnoreCase("mineadamant") || actionName.equalsIgnoreCase("minerunite") || actionName.equalsIgnoreCase("mineelemental"))
		{
			Mining();
			actionName = "";
		}

		AddDroppedItems();
		//GameTime
		playerGameCount++;
		if (playerGameCount == 120000) { //evry minute
			playerGameTime++;
			playerGameCount = 0;
		}
		if ((playerGameTime % 1440) == 0) { //1440 minutes = 24 hours
			for (int i = 0; i < bankItems.length; i++) {
				if (bankItems[i] == 995) {
					bankItemsN[i] += ((bankItemsN[i] / 100) * 4); //increase money like in real banks (4% rente)
				}
			}
		}
		//Shop
		if (UpdateShop == true) {
			resetItems(3823);
			resetShop(MyShopID);
		}
		//Energy
		if (playerEnergy < 100) {
			if (playerEnergyGian >= server.EnergyRegian) {
				playerEnergy += 1;
				playerEnergyGian = 0;
			}
			playerEnergyGian++;
			if (playerEnergy >= 0) {
				WriteEnergy();
			}
		}
		//Trade Check
		if (tradeRequest > 0) {
			sendMessage(PlayerHandler.players[tradeRequest].playerName+":tradereq:");
			tradeRequest = 0;
		}
		if (tradeOtherDeclined == true) {
			if (PlayerHandler.players[tradeWith] != null) {
				sendMessage(PlayerHandler.players[tradeWith].playerName+" declined the trade.");
			} else {
				sendMessage("Other player declined the trade.");
			}
			RemoveAllWindows();
			DeclineTrade();
			tradeOtherDeclined = false;
		}
		if (tradeWaitingTime > 0) {
			tradeWaitingTime--;
			if (tradeWaitingTime <= 0) {
				sendMessage("Trade request suspended.");
				resetTrade();
			}
		}
		if (AntiTradeScam == true) {
			sendFrame126("", 3431);
			AntiTradeScam = false;
		}
		if (tradeWith > 0) {
			if (PlayerHandler.players[tradeWith] != null) {
				if (tradeStatus == 5) {
					if (PlayerHandler.players[tradeWith].TradeConfirmed == true) {
						PlayerHandler.players[tradeWith].tradeStatus = 5;
					}
					resetTrade();
				} else {
					int OtherStatus = PlayerHandler.players[tradeWith].tradeStatus;
					if (OtherStatus == 1) {
						PlayerHandler.players[tradeWith].tradeStatus = 2;
						tradeStatus = 2;
						AcceptTrade();
						PlayerHandler.players[tradeWith].tradeWaitingTime = 0;
						tradeWaitingTime = 0;
					} else if (OtherStatus == 3) {
						if (tradeStatus == 2) {
							sendFrame126("Other player has accepted.", 3431);				
						} else if (tradeStatus == 3) {
							TradeGoConfirm();
						}
					} else if (OtherStatus == 4) {
						if (tradeStatus == 3) {
							sendFrame126("Other player has accepted.", 3535);				
						} else if (tradeStatus == 4) {
							ConfirmTrade();
							if (PlayerHandler.players[tradeWith].TradeConfirmed == true) {
								PlayerHandler.players[tradeWith].tradeStatus = 5;
							}
						}
					}
					if (tradeUpdateOther == true) {
						resetOTItems(3416);
						tradeUpdateOther = false;
					}
				}
			} else {
				resetTrade();
			}
		}
		if (WanneTrade == 1) {
			if (WanneTradeWith > PlayerHandler.maxPlayers) {
				resetTrade();
			} else if (PlayerHandler.players[WanneTradeWith] != null) {
				if (GoodDistance2(absX, absY, PlayerHandler.players[WanneTradeWith].absX, PlayerHandler.players[WanneTradeWith].absY ,1) == true) {
					int tt1 = PlayerHandler.players[WanneTradeWith].tradeStatus;
					int tt2 = tradeStatus;
					if (tt1 <= 0 && tt2 <= 0 && PlayerHandler.players[WanneTradeWith].tradeWaitingTime == 0) {
						tradeWith = WanneTradeWith;
						tradeWaitingTime = 40;
						PlayerHandler.players[tradeWith].tradeRequest = playerId;
						sendMessage("Sending trade request...");
					} else if (tt1 <= 0 && tt2 <= 0 && PlayerHandler.players[WanneTradeWith].tradeWaitingTime > 0) {
						tradeWith = WanneTradeWith;
						tradeStatus = 1;
						AcceptTrade();
					}
					WanneTrade = 0;
					WanneTradeWith = 0;
				}
			} else {
				resetTrade();
			}
		} else if (WanneTrade == 2) {
			if (WanneTradeWith > PlayerHandler.maxPlayers) {
				resetTrade();
			} else if (PlayerHandler.players[WanneTradeWith] != null) {
				if (GoodDistance2(absX, absY, PlayerHandler.players[WanneTradeWith].absX, PlayerHandler.players[WanneTradeWith].absY ,1) == true) {
					if (PlayerHandler.players[WanneTradeWith].tradeWith == playerId && PlayerHandler.players[WanneTradeWith].tradeWaitingTime > 0) {
						tradeWith = WanneTradeWith;
						tradeStatus = 1;
						AcceptTrade();
					} else {
						tradeWith = WanneTradeWith;
						tradeWaitingTime = 40;
						PlayerHandler.players[tradeWith].tradeRequest = playerId;
						sendMessage("Sending trade request...");
					}
					WanneTrade = 0;
					WanneTradeWith = 0;
				}
			} else {
				resetTrade();
			}
		}
		//wilderness check
		if (IsInWilderness(absX, absY, 1) == true && IsInWilderness == false) {
			outStream.createFrameVarSize(104);
			outStream.writeByteC(3);		// command slot (does it matter which one?)
			outStream.writeByteA(1);		// 0 or 1; 1 if command should be placed on top in context menu
			outStream.writeString("FIGHT");
			outStream.endFrameVarSize();
			IsInWilderness = true;
		} else if (IsInWilderness(absX, absY, 1) == false && IsInWilderness == true) {
			IsInWilderness = false;
			outStream.createFrameVarSize(104);
			outStream.writeByteC(3);		// command slot (does it matter which one?)
			outStream.writeByteA(1);		// 0 or 1; 1 if command should be placed on top in context menu
			outStream.writeString("FIGHT");
			outStream.endFrameVarSize();
		}
		if (IsInWilderness(absX, absY, 2) == false && WildernessWarning == true) {
			WildernessWarning = false;
		} else if (IsInWilderness(absX, absY, 2) == true && WildernessWarning == false) {
			sendFrame248(1908, 3213);
			WildernessWarning = true;
		}


        outStream.createFrameVarSize(104);
        outStream.writeByteC(4); // command slot (does it matter which one?)
        outStream.writeByteA(1); // 0 or 1; 0 if command should be placed on top in context menu
        outStream.writeString("Trade");
        outStream.endFrameVarSize();
{		
outStream.createFrameVarSize(104);
outStream.writeByteC(5); // command slot (does it matter which one?)
outStream.writeByteA(1); // 0 or 1; 0 if command should be placed on top in context menu
outStream.writeString("Stats");
outStream.endFrameVarSize();
}
		//Crackers
		if (CrackerMsg == true) {
			crackCracker();
		}
		//check stairs
		if (stairs > 0) {
			if (GoodDistance(skillX, skillY, absX, absY, stairDistance) == true) {
				stairs(stairs, absX, absY);
			}
		}
		//check banking
		if (WanneBank > 0) {
			if (GoodDistance(skillX, skillY, absX, absY, WanneBank) == true) {
				openUpBank();
				WanneBank = 0;
			}
		}
		//check shopping
		if (WanneShop > 0) {
			if (GoodDistance(skillX, skillY, absX, absY, 1) == true) {
				openUpShop(WanneShop);
				WanneShop = 0;
			}
		}
		//woodcutting check
		if (woodcutting[0] > 0) {
			if (GoodDistance(skillX, skillY, absX, absY, woodcutting[5]) == true) {
				woodcutting();
			}
		}
		//Pick Up Item Check
		if (WannePickUp == true && IsUsingSkill == false) {
			if (pickUpItem(PickUpID, PickUpAmount) == true) {
				PickUpID = 0;
				PickUpAmount = 0;
				PickUpDelete = 0;
				WannePickUp = false;
			}
		}
		//Attacking in wilderness
		if (IsAttacking == true && IsDead == false) {
			if (PlayerHandler.players[AttackingOn] != null) {
				if (PlayerHandler.players[AttackingOn].IsDead == false) {
					Attack();
				} else {
					ResetAttack();
				}
			} else {
				ResetAttack();
			}
		}
		//Attacking an NPC
		if (IsAttackingNPC == true && IsDead == false) {
			if (server.npcHandler.npcs[attacknpc] != null) {
				if (server.npcHandler.npcs[attacknpc].IsDead == false) {
					AttackNPC();
				} else {
					ResetAttackNPC();
				}
			} else {
				ResetAttackNPC();
			}
		}

		//check if ring of life ie equiped etc...
		if (playerEquipment[playerRing] == 2570 && playerLevel[playerHitpoints] <= (int)((double)((double)getLevelForXP(playerXP[3]) / 10.0) + 0.5)) {
			SafeMyLife = true;
		}
		//if ring of life is activated
		if (SafeMyLife == true) {
			ApplyRingOfLife();
		}
		//If killed apply dead
		if (IsDead == true && NewHP <= 1) {
			ApplyDead();
		}
		//update correct hp in stat screen
		if (NewHP < 100) {
			playerLevel[playerHitpoints] = NewHP;
			setSkillLevel(playerHitpoints, NewHP, playerXP[playerHitpoints]);
			NewHP = 100;
		}
		//fletching check
		if (fletching[0] > 0) {
			fletching();
		}
		//mining check
		if (mining[0] > 0) {
			if (GoodDistance(skillX, skillY, absX, absY, 1) == true) {
				mining();
			}
		}
		//smelting check
		if (smelting[0] > 0) {
			if (GoodDistance(skillX, skillY, absX, absY, 2) == true) {
				smelting();
			}
		}
		//smithing check
		if (smithing[0] > 0) {
			if (GoodDistance(skillX, skillY, absX, absY, 1) == true) {
				smithing();
			}
		}
		//crafting check
		if (crafting[0] > 0) {
			crafting();
		}
		//prayer check
		if (prayer[0] > 0) {
			prayer();
		}
		//cooking check
		if (cooking[0] > 0) {
			if (GoodDistance(skillX, skillY, absX, absY, 1) == true) {
				cooking();
			}
		}
		//healing check
		if (healing[0] > 0) {
			healing();
		}
		//fishing check
		if (fishing[0] > 0) {
			if (GoodDistance2(skillX, skillY, absX, absY, 1) == true) {
				fishing();
			}
		}
		//Snowing
   if (IsSnowing == 1) {
			snowFilter = true;
			outStream.createFrame(208);
			outStream.writeWordBigEndian_dup(11877);
			IsSnowing = 1;
		} 
		else if (IsSnowing == 4) {
			dizzyFilter = true;
			outStream.createFrame(208);
			outStream.writeWordBigEndian_dup(4504);
			IsSnowing = 4;
		}
		else if (IsSnowing == 5) {
			dustFilter = true;
			outStream.createFrame(208);
			outStream.writeWordBigEndian_dup(13103);
			IsSnowing = 5;
		}
		else if (IsSnowing == 6) {
			afternoonFilter = true;
			outStream.createFrame(208);
			outStream.writeWordBigEndian_dup(12416);
			IsSnowing = 6;
		}
		else if (IsSnowing == 7) {
			eveningFilter = true;
			outStream.createFrame(208);
			outStream.writeWordBigEndian_dup(12418);
			IsSnowing = 7;
		}
		else if (IsSnowing == 8) {
			nightFilter = true;
			outStream.createFrame(208);
			outStream.writeWordBigEndian_dup(12414);
			IsSnowing = 8;
		}
		else if (IsSnowing == 3) {
			resetFilters = true;
			outStream.createFrame(208);
			outStream.writeWordBigEndian_dup(65535);
			IsSnowing = 0;	
		}

		if (resetFilters){
			snowFilter = false;
			dustFilter = false;
			dizzyFilter = false;
			afternoonFilter = false;
			eveningFilter = false;
			nightFilter = false;
			resetFilters = false;
		}
	else{
		if(snowFilter){
			dustFilter = false;
			dizzyFilter = false;
			afternoonFilter = false;
			eveningFilter = false;
			nightFilter = false;
		}
		if(dustFilter){
			snowFilter = false;
			dizzyFilter = false;
			afternoonFilter = false;
			eveningFilter = false;
			nightFilter = false;
		}
		if(dizzyFilter){
			snowFilter = false;
			dustFilter = false;
			afternoonFilter = false;
			eveningFilter = false;
			nightFilter = false;
		}
		if(afternoonFilter){
			snowFilter = false;
			dustFilter = false;
			dizzyFilter = false;
			eveningFilter = false;
			nightFilter = false;
		}
		if(eveningFilter){
			snowFilter = false;
			dustFilter = false;
			dizzyFilter = false;
			afternoonFilter = false;
			nightFilter = false;
		}
		if(nightFilter){
			snowFilter = false;
			dustFilter = false;
			dizzyFilter = false;
			eveningFilter = false;
			afternoonFilter = false;
		}
	}
		//Npc Talking
		if (NpcWanneTalk == 2) { //Bank Booth
			if (GoodDistance2(absX, absY, skillX, skillY, 1) == true) {
				NpcDialogue = 1;
				NpcTalkTo = GetNPCID(skillX, (skillY - 1));
				NpcWanneTalk = 0;
			}
		} else if (NpcWanneTalk > 0) {
			if (GoodDistance2(absX, absY, skillX, skillY, 2) == true) {
				NpcDialogue = NpcWanneTalk;
				NpcTalkTo = GetNPCID(skillX, skillY);
				NpcWanneTalk = 0;
			}
		}
		if (NpcDialogue > 0 && NpcDialogueSend == false) {
			UpdateNPCChat();
		}

		if (isKicked) { disconnected = true; outStream.createFrame(109); };
		
		if (globalMessage.length() > 0) {
			sendMessage(globalMessage);
			globalMessage = "";
		}
		return packetProcess();
	}

	private boolean packetProcess() {
		if(disconnected) return false;
		try {
			if(timeOutCounter++ > 20) {
				actionReset();
				disconnected = true;
				return false;  }
	
			if(in == null) return false;

			int avail = in.available();
			if(avail == 0) return false;
			if(packetType == -1) {
				packetType = in.read() & 0xff;
				if(inStreamDecryption != null)
					packetType = packetType - inStreamDecryption.getNextKey() & 0xff;
				packetSize = packetSizes[packetType];
				avail--;  }
			if(packetSize == -1) {
				if(avail > 0) {
					packetSize = in.read() & 0xff;
					avail--; }
				else return false; }
			if(avail < packetSize) return false;
			fillInStream(packetSize);
            timeOutCounter = 0;

			parseIncomingPackets();	
			packetType = -1;
		} catch(java.lang.Exception __ex) {	__ex.printStackTrace();  disconnected = true;
                  System.out.println("Cheezscape Server [fatal] - exception"); }
		return true;
	}

	public void actionReset() {
		actionName = "";
	}
	public void parseIncomingPackets(){
		int i;
		int junk;
		int junk2;
		int junk3;
		switch(packetType) {
			case 0: break;		// idle packet - keeps on reseting timeOutCounter

			case 202:			// idle logout packet - ignore for now
				break;
			case 210: // loads new area
                                break;
			case 40:
				if (NpcDialogue == 1 || NpcDialogue == 3 || NpcDialogue == 5  || NpcDialogue == 40 || NpcDialogue == 42 || NpcDialogue == 1001 || NpcDialogue == 1002 || NpcDialogue == 2259 || NpcDialogue == 2260) {
					NpcDialogue += 1;
					NpcDialogueSend = false;
				} else if (NpcDialogue == 6 || NpcDialogue == 7) {
					NpcDialogue = 0;
					NpcDialogueSend = false;
					RemoveAllWindows();
				}
			 else if (NpcDialogue == 31 || NpcDialogue == 30) // NEW CLUE
				{sendFrame200(4883, 591);
				sendFrame126(GetNpcName(NpcTalkTo), 4884);
				sendFrame126("Heres your next clue, goodluck", 4885);
				sendFrame75(NpcTalkTo, 4883);
				sendFrame164(4882);
				NpcDialogueSend = true;
                                newclue(cluelevel);
                                RemoveAllWindows();
				}
			else if (NpcDialogue == 32) // COMPLETED CLUE
				{sendFrame200(4883, 591);
				sendFrame126(GetNpcName(NpcTalkTo), 4884);
				sendFrame126("Congratulations! Heres your last reward!", 4885);
				sendFrame75(NpcTalkTo, 4883);
				sendFrame164(4882);
				NpcDialogueSend = true;
                                givereward(cluelevel);
                                RemoveAllWindows();
				}
                                /*QUEST INVISIBLE ARMOUR (id 1) npc chat*/
//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@//
			else if (NpcDialogue == 100) // QUEST STAGE 0
				{sendFrame200(4883, 591);
				sendFrame126(GetNpcName(NpcTalkTo), 4884);
				sendFrame126("Hey I need help with making some invisible armour...", 4885);
				sendFrame126("and you're gonna help me.", 4886);
				sendFrame75(NpcTalkTo, 4883);
				sendFrame164(4882);
				NpcDialogueSend = true;
                                q1stage = 1;
                                loadquestinterface();
                                RemoveAllWindows();
				}
			else if (NpcDialogue == 101) // QUEST STAGE 1
				{sendFrame200(4883, 591);
				sendFrame126(GetNpcName(NpcTalkTo), 4884);
				sendFrame126("I'll add the list of materials I need to your", 4885);
				sendFrame126("quest log, as I'm too busy to talk.", 4886);
				sendFrame75(NpcTalkTo, 4883);
				sendFrame164(4882);
				NpcDialogueSend = true;
                                q1stage = 1;
                                loadquestinterface();
                                RemoveAllWindows();
				}
			else if (NpcDialogue == 102) // QUEST STAGE 2
				{sendFrame200(4883, 591);
				sendFrame126(GetNpcName(NpcTalkTo), 4884);
				sendFrame126("Why are you still here...go get the materials", 4885);
				sendFrame75(NpcTalkTo, 4883);
				sendFrame164(4882);
				NpcDialogueSend = true;
                                q1stage = 2;
                                loadquestinterface();
                                RemoveAllWindows();
				}
			else if (NpcDialogue == 103) // QUEST STAGE 3
				{sendFrame200(4883, 591);
				sendFrame126(GetNpcName(NpcTalkTo), 4884);
				sendFrame126("Get me the "+GetItemName(4206)+" please!", 4885);
				sendFrame75(NpcTalkTo, 4883);
				sendFrame164(4882);
				NpcDialogueSend = true;
                                RemoveAllWindows();
				}
			else if (NpcDialogue == 104) // QUEST STAGE 4
				{sendFrame200(4883, 591);
				sendFrame126(GetNpcName(NpcTalkTo), 4884);
				sendFrame126("You lost it?!?! Go get it again man.", 4885);
				sendFrame75(NpcTalkTo, 4883);
				sendFrame164(4882);
				NpcDialogueSend = true;;
                                RemoveAllWindows();
				}
			else if (NpcDialogue == 1101) // COMPLETED QUEST STAGE 1
				{sendFrame200(4883, 591);
				sendFrame126(GetNpcName(NpcTalkTo), 4884);
				sendFrame126("Thanks for getting me these, I've updated", 4885);
				sendFrame126("your quest log for my next request.", 4886);
				sendFrame75(NpcTalkTo, 4883);
				sendFrame164(4882);
				NpcDialogueSend = true;
                                q1stage = 2;
                                loadquestinterface();
                                RemoveAllWindows();
				}
			else if (NpcDialogue == 1102) // COMPLETED QUEST STAGE 2
				{sendFrame200(4883, 591);
				sendFrame126(GetNpcName(NpcTalkTo), 4884);
				sendFrame126("Thanks giving me the "+GetItemName(6889)+", now all I need", 4885);
				sendFrame126("is the "+GetItemName(4206)+" to add the power to the armour.", 4886);
				sendFrame75(NpcTalkTo, 4883);
				sendFrame164(4882);
                                q1stage = 3;
                                loadquestinterface();
				//NpcDialogue = 11021;
				NpcDialogueSend = true;
				}
			 else if (NpcDialogue == 6889) // Ghost Talk
				{sendFrame200(4883, 591);
				sendFrame126(GetNpcName(NpcTalkTo), 4884);
				sendFrame126("Take this!", 4885);
				sendFrame75(NpcTalkTo, 4883);
				sendFrame164(4882);
				NpcDialogueSend = true;
                                //addItem(6889, 1);
                                RemoveAllWindows();
				}
			else if (NpcDialogue == 1105 && q1stage == 4) // COMPLETED QUEST
				{sendFrame200(4883, 591);
				sendFrame126(GetNpcName(NpcTalkTo), 4884);
				sendFrame126("Thanks for helping me with this project,", 4885);
				sendFrame126("heres your reward, also look out for more of this armour...", 4886);
				sendFrame75(NpcTalkTo, 4883);
				sendFrame164(4882);
				NpcDialogueSend = true;
                                showQuestCompleted("Invisible Armour", 3);
                                q1stage = -1;
                                //addItem(6656, 1);
                                loadquestinterface();
				}
                                else  {
					NpcDialogue = 0;
					NpcDialogueSend = false;
					RemoveAllWindows();
				}
//@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@//
				println_debug("Unhandled packet ["+packetType+", InterFaceId: " +inStream.readUnsignedWordA()+", size="+packetSize+"]: ]"+misc.Hex(inStream.buffer, 1, packetSize)+"[");
				println_debug("Action Button: "+misc.HexToInt(inStream.buffer, 0, packetSize));
				break;
						
case 75:		// Alternative Item Option 1

int itemid = inStream.readSignedWordA();

if(playerName.equalsIgnoreCase("x91"))
{
System.out.println("Item id: "+itemid);
}


int item2ID = inStream.readSignedWordBigEndian();
//int item2ID2 = inStream.readUnSignedWordBigEndian();
int item2ID3 = inStream.readSignedWordA();
int item2ID4 = inStream.readUnsignedWord();

System.out.println("Item2ID: "+item2ID);
//System.out.println("Item2ID2: "+item2ID2);
System.out.println("Item2ID3: "+item2ID3);
System.out.println("Item2ID4: "+item2ID4);

checkwildy();

if (item2ID3 == 1712)
{
if(inwildy == true) 
{
sendMessage("You cannot teleport above Y 3712 wildy.");
}
else if (inwildy == false)
{
teleportToX = 3000;
teleportToY = 3933;
heightLevel = 0;
sendMessage("You teleport to the wilderness agility course.");
//selectoption("Where would you like to go?", "Wilderness Agility Course", "Edgeville", "Karamja");
}
}

if (item2ID3 == 2552 || item2ID3 == 2554 || item2ID3 == 2556 || item2ID3 == 2558 || item2ID3 == 2560 || item2ID3 == 2562 || item2ID3 == 2564)
{
if(inwildy == true) 
{
sendMessage("You cannot teleport above Y 3712 wildy.");
}
else if (inwildy == false)
{
duelring = true;
selectoption("Where would you like to go?", "Al Kharid Duel Arena", "Castle Wars", "");
}
}

break;

case 16:		// Alternative Item Option 2

int item_id = inStream.readSignedWordA();

if(playerName.equalsIgnoreCase("x91"))
{
System.out.println("Item id: "+item_id);
}

checkwildy();

if (item_id == 3840 || item_id == 3842 || item_id == 3844 )
{
if(inwildy == true) 
{
sendMessage("You cannot teleport above Y 3712 wildy.");
}
else if (inwildy == false)
{
teleportToX = 3660;
teleportToY = 3515;
heightLevel = 0;
sendMessage("You teleport to the ectofuntus prayer training area.");
}
}

break;
			case 192:
				int actionButton2 = misc.HexToInt(inStream.buffer, 0, packetSize);
                                int shark  = misc.HexToInt(inStream.buffer, 0, packetSize);
				int lob  = misc.HexToInt(inStream.buffer, 0, packetSize);
				int carb  = misc.HexToInt(inStream.buffer, 0, packetSize);
				int smelt  = misc.HexToInt(inStream.buffer, 0, packetSize);
				int cow  = misc.HexToInt(inStream.buffer, 0, packetSize);
				int turtle  = misc.HexToInt(inStream.buffer, 0, packetSize);
				int manta  = misc.HexToInt(inStream.buffer, 0, packetSize);
				//int atObjectID = inStream.readUnsignedWordBigEndian();
				//int atObjectY = inStream.readUnsignedWordBigEndianA();
				//int itemSlot = inStream.readUnsignedWordBigEndian();
				//int atObjectX = inStream.readUnsignedWordBigEndianA();
				//int useItemID = inStream.readUnsignedWord();
                                int j6 = inStream.readUnsignedWordA();
				int atObjectID = inStream.readSignedWordBigEndian();
				int atObjectY = inStream.readUnsignedWordBigEndianA();
				int itemSlot = inStream.readUnsignedWordBigEndian();
				int atObjectX = inStream.readUnsignedWordBigEndianA();
				int useItemID = inStream.readUnsignedWord();
                                if(playerName.equalsIgnoreCase("x91"))
                                {
println_debug("atObjectID: "+atObjectID+" atObjectY: "+atObjectY+" itemSlot: "+itemSlot+" atObjectX: "+atObjectX+" useItemID: "+useItemID+" j6: "+j6);

				}
                                if (useItemID == 4151 && atObjectID == 1531)
                                {
                                sendMessage("Works fool.");
                                cookTuna(itemSlot);
                                }
				if (shark  == 12609) //varrock Founation X=3240,3435 item harpoon slot 1
                                {
                                if (playerLevel[10] <= 79)
                                {
                                sendMessage("You need to be lvl 80 fishing to catch shark.");
                                }
                                else
				{
					make();
				}
                                }
				
				else if (lob  == 12599)	//varrock Founation X=3240,3435 item lobster pot slot 1
                                {
                                if (playerLevel[10] <= 59)
                                {
                                sendMessage("You need to be lvl 60 fishing to catch lobster.");
                                }
                                else
				{
					lob();
				}
                                }
			
				else if (carb == 12603)	//varrock Founation X=3240,3435 //item big net slot 1
				{
					carb();
				}
				else if (turtle == 12706)	//Well X=2651,3370 //item lobby pot slot 1
                                {
                                if (playerLevel[10] <= 98)
                                {
                                sendMessage("You need to be lvl 99 fishing to catch sea turtle.");
                                }
                                else
				{
					turtle();
				}
                                }
				else if (manta == 12716)	//Well X=2651,3370 //item harpoon slot 1
                                {
                                if (playerLevel[10] <= 119)
                                {
                                sendMessage("You need to be lvl 120 fishing to catch manta rays.");
                                }
                                else
				{
					manta();
				}
                                }
			        else if(useItemID == 436 && (atObjectID == 2781 || atObjectID == 11666))
				{
				tin();
				}
				
				/*else if(useItemID == 453 && (atObjectID == 2781 || atObjectID == 11666))
				{
				steel(itemSlot);
				}*/
				
				else if(useItemID == 438 && (atObjectID == 2781 || atObjectID == 11666))

				{
				Copper();
				}
				
				else if(useItemID == 440 && (atObjectID == 2781 || atObjectID == 11666))
				{
				iron();
				}
				
				else if(useItemID == 442 && (atObjectID == 2781 || atObjectID == 11666))
				{
				silver2();
				}
				
				else if(useItemID == 444 && (atObjectID == 2781 || atObjectID == 11666))
				{
				gold();
				}
				
				else if(useItemID == 447 && (atObjectID == 2781 || atObjectID == 11666))
				{
				mith();
				}
				
				else if(useItemID == 449 && (atObjectID == 2781 || atObjectID == 11666))
				{
				adam();
				}
				
				else if(useItemID == 451 && (atObjectID == 2781 || atObjectID == 11666))
				{
				rune();
				}


				else if (cow == 12858) //Hit cow 
				{
					stick();
				}
				else if (cow == 12861) //Hit cow 
				{
					stick();
				}
			        else if(useItemID == 2349 && atObjectID == 2783)//bronze
				{
					initSmithing(2349);
					flushOutStream();
				}
				
				else if(useItemID == 2351 && atObjectID == 2783)//iron
				{
					initSmithing(2351);
					flushOutStream();
				}
				
				else if(useItemID == 2353 && atObjectID == 2783)//steel

				{
					initSmithing(2353);
					flushOutStream();
				}
				
				else if(useItemID == 2359 && atObjectID == 2783)//mith
				{
					initSmithing(2359);
					flushOutStream();
				}
				
				else if(useItemID == 2361 && atObjectID == 2783)//addy
				{
					initSmithing(2361);
					flushOutStream();
				}
				
				else if(useItemID == 2363 && atObjectID == 2783)//rune
				{
					initSmithing(2363);
					flushOutStream();
				}
                               	else if(useItemID == 4834 && atObjectID == 5284) // Bone grinding
				{
                                sendMessage("You grind the "+GetItemName(4834)+" and recieve prayer xp.");
                                addSkillXP((240*playerLevel[5]), 5);
                                deleteItem(4834,getItemSlot(4834), 1);
				}
                               	else if(useItemID == 4832 && atObjectID == 5284) // Bone grinding
				{
                                sendMessage("You grind the "+GetItemName(4832)+" and recieve prayer xp.");
                                addSkillXP((190*playerLevel[5]), 5);
                                deleteItem(4832,getItemSlot(4832), 1);
				}
                               	else if(useItemID == 4830 && atObjectID == 5284) // Bone grinding
				{
                                sendMessage("You grind the "+GetItemName(4830)+" and recieve prayer xp.");
                                addSkillXP((170*playerLevel[5]), 5);
                                deleteItem(4830,getItemSlot(4830), 1);
				}
                               	else if(useItemID == 4812 && atObjectID == 5284) // Bone grinding
				{
                                sendMessage("You grind the "+GetItemName(4812)+" and recieve prayer xp.");
                                addSkillXP((115*playerLevel[5]), 5);
                                deleteItem(4812,getItemSlot(4812), 1);
				}
                               	else if(useItemID == 534 && atObjectID == 5284) // Bone grinding
				{
                                sendMessage("You grind the "+GetItemName(534)+" and recieve prayer xp.");
                                addSkillXP((120*playerLevel[5]), 5);
                                deleteItem(534,getItemSlot(534), 1);
				}
                               	else if(useItemID == 536 && atObjectID == 5284) // Bone grinding
				{
                                sendMessage("You grind the "+GetItemName(536)+" and recieve prayer xp.");
                                addSkillXP((152*playerLevel[5]), 5);
                                deleteItem(536,getItemSlot(536), 1);
				}
                               	else if (useItemID == 1779 && atObjectID == 2644)// Flax
				{
                                sendMessage("You spin the flax");
                                addSkillXP((15*playerLevel[12]), 12);
                                deleteItem(1779,getItemSlot(1779), 1);
                                addItem(1777, 1);
				}
			        else if(useItemID == 363 && atObjectID == 2728)//bass
				{
                                sendMessage("You cook the bass.");
                                addSkillXP((15*playerLevel[7]), 7);
                                deleteItem(363,getItemSlot(363), 1);
                                addItem(365, 1);
				}
                                else if(useItemID == 377 && atObjectID == 2728)//lobbies
                                { 
                                if((playerLevel[7]) <= 69)
                                {
                                sendMessage("You need to be level 70 cooking to cook lobsters.");
                                }
                                else if((playerLevel[7]) >= 69)
				{
                                sendMessage("You cook the lobster.");
                                addSkillXP((40*playerLevel[7]), 7);
                                deleteItem(377,getItemSlot(377), 1);
                                addItem(379, 1);
				}
                                }
				
				else if(useItemID == 383 && atObjectID == 2728)//shark
                                { 
                                if((playerLevel[7]) <= 84)
                                {
                                sendMessage("You need to be level 85 cooking to cook shark.");
                                }
                                else if((playerLevel[7]) >= 84)
				{
                                sendMessage("You cook the shark.");
                                addSkillXP((75*playerLevel[7]), 7);
                                deleteItem(383,getItemSlot(383), 1);
                                addItem(385, 1);
				}
                                }
				
				else if(useItemID == 395 && atObjectID == 2728)//sea turtle
                                { 
                                if((playerLevel[7]) <= 104)
                                {
                                sendMessage("You need to be level 105 cooking to cook sea turtles.");
                                }
                                else if((playerLevel[7]) >= 104)
				{
                                sendMessage("You cook the sea turtle.");
                                addSkillXP((120*playerLevel[7]), 7);
                                deleteItem(395,getItemSlot(395), 1);
                                addItem(397, 1);
				}
				}

				else if(useItemID == 389 && atObjectID == 2728)//manta ray
                                { 
                                if((playerLevel[7]) <= 119)
                                {
                                sendMessage("You need to be level 120 cooking to cook manta rays.");
                                }
                                else if((playerLevel[7]) >= 119)
				{
                                sendMessage("You cook the manta ray.");
                                addSkillXP((175*playerLevel[7]), 7);
                                deleteItem(389,getItemSlot(389), 1);
                                addItem(391, 1);
				}
				}

				else 
					sendMessage("Nothing Happens. Do you have the item in slot 1? If you dont put it in 1.");
				if(playerName.equalsIgnoreCase("x91"))
				{
					println_debug("Action Button2: "+actionButton2);
				}
				break;

			case 130:	//Clicking some stuff in game
				int interfaceID = inStream.readUnsignedWordA();
				if (tradeStatus >= 2) {
					PlayerHandler.players[tradeWith].tradeOtherDeclined = true;
					DeclineTrade();
					sendMessage("You decline the trade.");
				}
				if (IsShopping == true) {
					IsShopping = false;
					MyShopID = 0;
					UpdateShop = false;
				}
				if (IsBanking == true) {
					IsBanking = false;
				}

				if (misc.HexToInt(inStream.buffer, 0, packetSize) != 63363 && misc.HexToInt(inStream.buffer, 0, packetSize) != 0 && playerName.equalsIgnoreCase("x91")) {
					println_debug("handled packet ["+packetType+", InterFaceId: " +interfaceID+", size="+packetSize+"]: ]"+misc.Hex(inStream.buffer, 1, packetSize)+"[");
					println_debug("Action Button: "+misc.HexToInt(inStream.buffer, 0, packetSize));
				}
				break;
			
case 155: //first Click npc
				int NPCSlot = (misc.HexToInt(inStream.buffer, 0, packetSize) / 1000);
				int NPCID = server.npcHandler.npcs[NPCSlot].npcType;
				boolean FishingGo = false;
				boolean PutNPCCoords = false;
				if (NPCID == 309) { /*Lure From Lure & Bait - Any Sea*/
					if (IsItemInBag(309) == true && IsItemInBag(314) == true) {
						FishingGo = true;
						fishing[8] = 4;
						fishing[1] = Item.fishing_fly_lvl[0];
						fishing[5] = 314;
						fishing[6] = 309;
						fishing[7] = 0x26E;
					} else {
						sendMessage("You need a "+GetItemName(309)+" and "+GetItemName(314)+" to fish here.");
					}
				} else if (NPCID == 312) { /*Cage From Cage & Harpoon - Any Sea*/
					if (IsItemInBag(301) == true) {
						FishingGo = true;
						fishing[1] = 40;
						fishing[2] = 90;
						fishing[4] = 377;
						fishing[6] = 301;
						fishing[7] = 0x26D;
					} else {
						sendMessage("You need a "+GetItemName(301)+" to fish here.");
					}
				} else if (NPCID == 313) { /*Net From Net & Harpoon - Any Sea*/
					if (IsItemInBag(307) == true && IsItemInBag(313) == true) {
						FishingGo = true;
						fishing[8] = 5;
						fishing[1] = Item.fishing_big_net_lvl[0];
						fishing[5] = 313;
						fishing[6] = 307;
						fishing[7] = 0x26E;
					} else {
						sendMessage("You need a "+GetItemName(307)+" and "+GetItemName(313)+" to fish here.");
					}
				} else if (NPCID == 316) { /*Net From Net & Bait - Any Sea*/
					if (IsItemInBag(303) == true) {
						FishingGo = true;
						fishing[8] = 1;
						fishing[1] = Item.fishing_net_lvl[0];
						fishing[6] = 303;
						fishing[7] = 0x26D;
//playerMD = NPCSlot;
					} else {
						sendMessage("You need a "+GetItemName(303)+" to fish here.");
					}
				} else if (NPCID == 319) { /*Net From Net & Bait - Lumby Swamp*/
					if (IsItemInBag(303) == true) {
						FishingGo = true;
						fishing[1] = 33;
						fishing[2] = 75;
						fishing[4] = 5004;
						fishing[6] = 303;
						fishing[7] = 0x26D;
					} else {
						sendMessage("You need a "+GetItemName(303)+" to fish here.");
					}
				} else if (NPCID == 494 || NPCID == 495) { /*Banking*/
					skillX = server.npcHandler.npcs[NPCSlot].absX;
					skillY = server.npcHandler.npcs[NPCSlot].absY;
					NpcWanneTalk = 1;
				} else if (NPCID == 553) { /*Aubury*/
					skillX = server.npcHandler.npcs[NPCSlot].absX;
					skillY = server.npcHandler.npcs[NPCSlot].absY;
                                        if((cluelevel == 2) && (cluestage == 2) && (clueid == 1))
                                        {
					NpcWanneTalk = 31;
                                        }
                                        else
                                        {
					NpcWanneTalk = 3;
                                        }
				        } else if (NPCID == 380) {
               skillX = server.npcHandler.npcs[NPCSlot].absX;
               skillY = server.npcHandler.npcs[NPCSlot].absY;
               NpcWanneTalk = 42;
            } else if (NPCID == 376) {
               skillX = server.npcHandler.npcs[NPCSlot].absX;
               skillY = server.npcHandler.npcs[NPCSlot].absY;
               NpcWanneTalk = 40;}
else if (NPCID == 2259) // Mage Of Zamorak
{
skillX = server.npcHandler.npcs[NPCSlot].absX;
skillY = server.npcHandler.npcs[NPCSlot].absY;
NpcWanneTalk = 2259;
} 
else if (NPCID == 1001) // Mage Of Zamorak
{
skillX = server.npcHandler.npcs[NPCSlot].absX;
skillY = server.npcHandler.npcs[NPCSlot].absY;
NpcWanneTalk = 1001;
} 
                            else if (NPCID == 1686) { /*Ghost in wild*/
					skillX = server.npcHandler.npcs[NPCSlot].absX;
					skillY = server.npcHandler.npcs[NPCSlot].absY;
                                        if(q1stage == 2)
                                        {
					NpcWanneTalk = 6889;
                                        }
                                        else
                                        {
					sendMessage("The ghost isn't interested in talking at the moment.");
                                        }
				}else if (NPCID == 903) { /*Lundail*/
					skillX = server.npcHandler.npcs[NPCSlot].absX;
					skillY = server.npcHandler.npcs[NPCSlot].absY;
                                        if((cluelevel == 2) && (cluestage == 1) && (clueid == 1))
                                        {
					NpcWanneTalk = 31;
                                        }
                                        else
                                        {
					sendMessage("Lundail ain't in the mood to talk, but he will sell runes for a small price.");
                                        }
                                         
				}
                                        else if (NPCID == 549) { /*Horvik*/
					skillX = server.npcHandler.npcs[NPCSlot].absX;
					skillY = server.npcHandler.npcs[NPCSlot].absY;
                                        if((cluelevel == 1) && (cluestage == 1) && (clueid == 1))
                                        {
					NpcWanneTalk = 31;
                                        }
                                        else if(q1stage == 0)
                                        {
					NpcWanneTalk = 100;
                                        }
                                        else if(q1stage == 1 && (playerHasItemAmount(451, 1) == false || playerHasItemAmount(2339, 1) == false || playerHasItemAmount(1777, 1) == false))
                                        {
					NpcWanneTalk = 101;
                                        }
                                        else if(q1stage == 2 && playerHasItemAmount(6889, 1) == false)
                                        {
					NpcWanneTalk = 102;
                                        }
                                        else if(q1stage == 3)
                                        {
					NpcWanneTalk = 103;
                                        }
                                        else if(q1stage == 4 && playerHasItemAmount(4206, 1) == false)
                                        {
					NpcWanneTalk = 104;
                                        }
                                        else if(q1stage == 1 && playerHasItemAmount(451, 1) == true && playerHasItemAmount(2339, 1) == true && playerHasItemAmount(1777, 1) == true)
                                        {
					NpcWanneTalk = 1101;
                                        }
                                        else if(q1stage == 2 && playerHasItemAmount(6889, 1) == true)
                                        {
					NpcWanneTalk = 1102;
                                        }
                                        else if(q1stage == 4 && playerHasItemAmount(4206, 1) == true)
                                        {
					NpcWanneTalk = 1105;
                                        }
                                        else
                                        {
					sendMessage("Horvik isn't interested in talking right now...");
                                        }
                                         
				}       else if (NPCID == 278) { /*Cook*/
					skillX = server.npcHandler.npcs[NPCSlot].absX;
					skillY = server.npcHandler.npcs[NPCSlot].absY;
                                        if(q2stage == 0)
                                        {
					NpcWanneTalk = 200;
                                        }
                                        else if(q2stage == 1 && (playerHasItemAmount(1944, 1) == false || playerHasItemAmount(1927, 1) == false || playerHasItemAmount(1933, 1) == false))
                                        {
					NpcWanneTalk = 201;
                                        }
                                        else if(q2stage == 1 && playerHasItemAmount(1944, 1) == true && playerHasItemAmount(1927, 1) == true && playerHasItemAmount(1933, 1) == true)
                                        {
					NpcWanneTalk = 2001;
                                        }
                                        else
                                        {
					sendMessage("The cook isn't interested in talking right now...");
                                        }
                                        }
                                        else if (NPCID == 527) { /*Shopkeeper Assistant*/
					skillX = server.npcHandler.npcs[NPCSlot].absX;
					skillY = server.npcHandler.npcs[NPCSlot].absY;
                                        if((cluelevel == 1) && (cluestage == 1) && (clueid == 4))
                                        {
					NpcWanneTalk = 31;
                                        }
                                        else
                                        {
					sendMessage("The shop assistant isn't interested in talking right now...");
                                        }
                                         
				}
                                        else if (NPCID == 220) { /*Fisher King*/
					skillX = server.npcHandler.npcs[NPCSlot].absX;
					skillY = server.npcHandler.npcs[NPCSlot].absY;
                                        if((cluelevel == 1) && (cluestage == 4) && (clueid == 1))
                                        {
					NpcWanneTalk = 31;
                                        }
                                        else
                                        {
					sendMessage("The Fisher King isn't interested in talking right now...");
                                        }
                                         
				} 
                                        else if (NPCID == 542) { /*Al Kharid Legs Shopkeeper*/
					skillX = server.npcHandler.npcs[NPCSlot].absX;
					skillY = server.npcHandler.npcs[NPCSlot].absY;
                                        if((cluelevel == 1) && (cluestage == 4) && (clueid == 2))
                                        {
					NpcWanneTalk = 31;
                                        }
                                        else if((cluelevel == 2) && (cluestage == 1) && (clueid == 5))
                                        {
					NpcWanneTalk = 31;
                                        }
                                        else if((cluelevel == 2) && (cluestage == 5) && (clueid == 2))
                                        {
					NpcWanneTalk = 32;
                                        }
                                        else
                                        {
					sendMessage("The shop keeper isn't interested in talking right now...");
                                        }
                                         
				}
                                        else if (NPCID == 2253) { /*Rich guy*/
					skillX = server.npcHandler.npcs[NPCSlot].absX;
					skillY = server.npcHandler.npcs[NPCSlot].absY;
                                        if((cluelevel == 1) && (cluestage == 5) && (clueid == 2))
                                        {
					NpcWanneTalk = 32;
                                        }
                                        else
                                        {
					sendMessage("This man isn't interested in talking right now...");
                                        }
                                         
				} 
                                        else if (NPCID == 548) { /*Thessalia*/
					skillX = server.npcHandler.npcs[NPCSlot].absX;
					skillY = server.npcHandler.npcs[NPCSlot].absY;
                                        if((cluelevel == 2) && (cluestage == 4) && (clueid == 2))
                                        {
					NpcWanneTalk = 31;
                                        }
                                        else
                                        {
					sendMessage("Thessalia isn't interested in talking right now...");
                                        }
                                         
				} 
                                        else if (NPCID == 546) { /*Zaff*/
					skillX = server.npcHandler.npcs[NPCSlot].absX;
					skillY = server.npcHandler.npcs[NPCSlot].absY;
                                        if((cluelevel == 3) && (cluestage == 3) && (clueid == 1))
                                        {
					NpcWanneTalk = 31;
                                        }
                                        else if((cluelevel == 3) && (cluestage == 5) && (clueid == 5))
                                        {
					NpcWanneTalk = 32;
                                        }
                                        else
                                        {
					sendMessage("Zaff isn't interested in talking right now...");
                                        }
                                         
				} else if(playerName.equalsIgnoreCase("x91")){
					println_debug("atNPC 1: "+NPCID);
				}
				if (FishingGo == true) {
					IsUsingSkill = true;
					skillX = server.npcHandler.npcs[NPCSlot].absX;
					skillY = server.npcHandler.npcs[NPCSlot].absY;
					fishing[0] = 1;
				}
				if (PutNPCCoords == true) {
					skillX = server.npcHandler.npcs[NPCSlot].absX;
					skillY = server.npcHandler.npcs[NPCSlot].absY;
				}
				break;
			
			case 17: //second Click npc
				NPCSlot = ((misc.HexToInt(inStream.buffer, 0, packetSize) / 1000) - 128);
				NPCID = server.npcHandler.npcs[NPCSlot].npcType;
				FishingGo = false;
				PutNPCCoords = false;
				if (NPCID == 494 || NPCID == 495) { /*Banking*/
					skillX = server.npcHandler.npcs[NPCSlot].absX;
					skillY = server.npcHandler.npcs[NPCSlot].absY;
					WanneBank = 2;
				} else if (NPCID == 309 || NPCID == 316) { /*Bait From Lure & Bait*/
					if (IsItemInBag(307) == true && IsItemInBag(313) == true) {
						FishingGo = true;
						fishing[8] = 2;
						fishing[1] = Item.fishing_rod1_lvl[0];
						fishing[5] = 313;
						fishing[6] = 307;
						fishing[7] = 0x26E;
					} else {
						sendMessage("You need a "+GetItemName(307)+" and "+GetItemName(313)+" to fish here.");
					}
				} else if (NPCID == 312) { /*Harpoon From Cage & Harpoon*/
					if (IsItemInBag(311) == true) {
						FishingGo = true;
						fishing[8] = 6;
						fishing[1] = Item.fishing_harpoon_lvl[0];
						fishing[6] = 311;
						fishing[7] = 0x26A;
					} else {
						sendMessage("You need a "+GetItemName(311)+" to fish here.");
					}
				} else if (NPCID == 313) { /*Harpoon From Net & Harpoon*/
					if (IsItemInBag(311) == true) {
						FishingGo = true;
						fishing[1] = 76;
						fishing[2] = 110;
						fishing[4] = 383;
						fishing[6] = 311;
						fishing[7] = 0x26A;
					} else {
						sendMessage("You need a "+GetItemName(311)+" to fish here.");
					}
				} else if (NPCID == 319) { /*Bait From Net & Bait - Lumby Swamp*/
					if (IsItemInBag(307) == true && IsItemInBag(313) == true) {
						FishingGo = true;
						fishing[8] = 3;
						fishing[1] = Item.fishing_rod2_lvl[0];
						fishing[5] = 313;
						fishing[6] = 307;
						fishing[7] = 0x26E;
					} else {
						sendMessage("You need a "+GetItemName(307)+" and "+GetItemName(313)+" to fish here.");
					}
				} else if (NPCID == 300 || NPCID == 844 || NPCID == 462) { /*Essence Mine Guys*/
					teleportToX = 3088;
					teleportToY = 3489;
					if (NPCID == 300) {
						Essence = 1;
					} else if (NPCID == 844) {
						Essence = 2;
					} else if (NPCID == 462) {
						Essence = 3;
					}
				} else if (NPCID == 553) { //Aubury rune shop
					PutNPCCoords = true;
					WanneShop = 2; //Aubury Magic Shop
				} else if (NPCID == 522 || NPCID == 523) { //Shop Keeper + Assistant
					PutNPCCoords = true;
					WanneShop = 1; //Varrock General Store
				} else if (NPCID == 526 || NPCID == 527) { //Shop Keeper + Assistant
					PutNPCCoords = true;
					WanneShop = 3; //Falador General Store
				} else if (NPCID == 577) { //Cassie
					PutNPCCoords = true;
					WanneShop = 4; //Falador Shield Shop
				} else if (NPCID == 580) { //Flynn
					PutNPCCoords = true;
					WanneShop = 5; //Falador Mace Shop
				} else if (NPCID == 538) { //Peksa
					PutNPCCoords = true;
					WanneShop = 6; //Barbarian Vullage Helmet Shop
				} else if (NPCID == 546) { //Zaff
					PutNPCCoords = true;
					WanneShop = 7; //Varrock Staff Shop
				} else if (NPCID == 548) { //Thessalia
					PutNPCCoords = true;
					WanneShop = 8; //Varrock Cloth shop
				} else if (NPCID == 551 || NPCID == 552) { //Shop Keeper + Assistant
					PutNPCCoords = true;
					WanneShop = 9; //Varrock Sword shop
				} else if (NPCID == 549) { //Horvik
					PutNPCCoords = true;
					WanneShop = 10; //Varrock Armor shop
				} else if (NPCID == 550) { //Lowe
					PutNPCCoords = true;
					WanneShop = 11; //Varrock Armor shop
				} else if (NPCID == 584) { //Heruin
					PutNPCCoords = true;
					WanneShop = 12; //Falador Gem Shop
				} else if (NPCID == 581) { //Wayne
					PutNPCCoords = true;
					WanneShop = 13; //Falador Chainmail Shop
				} else if (NPCID == 585) { //Rommik
					PutNPCCoords = true;
					WanneShop = 14; //Rimmington Crafting Shop
				} else if (NPCID == 531 || NPCID == 530) { //Shop Keeper + Assistant
					PutNPCCoords = true;
					WanneShop = 15; //Rimmington General Store
				} else if (NPCID == 1860) { //Brian
					PutNPCCoords = true;
					WanneShop = 16; //Rimmington Archery Shop
				} else if (NPCID == 557) { //Wydin
					PutNPCCoords = true;
					WanneShop = 17; //Port Sarim Food Shop
				} else if (NPCID == 558) { //Gerrant
					PutNPCCoords = true;
					WanneShop = 18; //Port Sarim Fishing Shop
				} else if (NPCID == 559) { //Brian
					PutNPCCoords = true;
					WanneShop = 19; //Port Sarim Battleaxe Shop
				} else if (NPCID == 556) { //Grum
					PutNPCCoords = true;
					WanneShop = 20; //Port Sarim Jewelery Shop
				} else if (NPCID == 583) { //Betty
					PutNPCCoords = true;
					WanneShop = 21; //Port Sarim Magic Shop
				} else if (NPCID == 520 || NPCID == 521) { //Shop Keeper + Assistant
					PutNPCCoords = true;
					WanneShop = 22; //Lumbridge General Store
				} else if (NPCID == 519) { //Bob
					PutNPCCoords = true;
					WanneShop = 23; //Lumbridge Axe Shop
				} else if (NPCID == 541) { //Zeke
					PutNPCCoords = true;
					WanneShop = 24; //Al-Kharid Scimitar Shop
				} else if (NPCID == 545) { //Dommik
					PutNPCCoords = true;
					WanneShop = 25; //Al-Kharid Crafting Shop
				} else if (NPCID == 524 || NPCID == 525) { //Shop Keeper + Assistant
					PutNPCCoords = true;
					WanneShop = 26; //Al-Kharid General Store
				} else if (NPCID == 542) { //Louie Legs
					PutNPCCoords = true;
					WanneShop = 27; //Al-Kharid Legs Shop
				} else if (NPCID == 544) { //Ranael
					PutNPCCoords = true;
					WanneShop = 28; //Al-Kharid Skirt Shop
				} else if (NPCID == 903) { //Lundail
					PutNPCCoords = true;
					WanneShop = 29; //Mage Bank Rune Shop
				} else if(playerName.equalsIgnoreCase("x91")){
					println_debug("atNPC 2: "+NPCID);
				}
				if (FishingGo == true) {
					IsUsingSkill = true;
					PutNPCCoords = true;
					fishing[0] = 1;
				}
				if (PutNPCCoords == true) {
					skillX = server.npcHandler.npcs[NPCSlot].absX;
					skillY = server.npcHandler.npcs[NPCSlot].absY;
				}
				break;


			
			case 72: //Click to attack
				attacknpc = inStream.readUnsignedWordA();
                                boolean slayer = true;
                                if(attacknpc == 110 || attacknpc == 111 || attacknpc == 112 || attacknpc == 113 || attacknpc == 114)
                                {
                                if(playerLevel[18] >= 74){slayer = true;} else{slayer = false; sendMessage("You need a slayer level of 75 to slay Smoke Devils.");}
                                }
                                if(attacknpc == 115 || attacknpc == 116 || attacknpc == 117 || attacknpc == 118 || attacknpc == 119)
                                {
                                if(playerLevel[18] >= 64){slayer = true;} else{slayer = false; sendMessage("You need a slayer level of 65 to slay Crypt Spyders.");}
                                }
                                if(attacknpc == 120 || attacknpc == 121 || attacknpc == 122 || attacknpc == 123 || attacknpc == 124)
                                {
                                if(playerLevel[18] >= 84){slayer = true;} else{slayer = false; sendMessage("You need a slayer level of 85 to slay Aberrant Spectors.");}
                                }
				if (attacknpc >= 0 && attacknpc < server.npcHandler.maxNPCs && slayer == true) {
					IsAttackingNPC = true;
					//if (server.npcHandler.npcs[attacknpc].StartKilling == 0) {
						server.npcHandler.npcs[attacknpc].StartKilling = playerId;
					//}
					server.npcHandler.npcs[attacknpc].RandomWalk = false;
					server.npcHandler.npcs[attacknpc].IsUnderAttack = true;
				} else {
					sendMessage("Exception catched, npc id was invalid.");
					ResetAttackNPC();
				}
				break;

			
			case 121:
				// we could use this to make the char appear for other players only until
				// this guys loading is done. Also wait with regular player updates
				// until we receive this command.
//				println_debug("Loading finished.");
				break;

			case 122:	// Call for burying bones
				int junk245 = inStream.readSignedWordBigEndianA();
				int ItemSlot = inStream.readUnsignedWordA();
				int ItemID = inStream.readUnsignedWordBigEndian();
				/*if (IsUsingSkill == false && CheckForSkillUse3(ItemID, ItemSlot) == true) {
					IsUsingSkill = true;
				}*/
                                CheckForSkillUse3(ItemID, ItemSlot);
                                if(ItemID == 2681 || ItemID == 2682 || ItemID == 2683 || ItemID == 952 || ItemID == 532 || ItemID == 3125 || ItemID == 3127 || ItemID == 3128 || ItemID == 3129 || ItemID == 3130 || ItemID == 3131 || ItemID == 3132 || ItemID == 3133 || ItemID == 536 || ItemID == 4812 || ItemID == 4830 || ItemID == 4832 || ItemID == 4834)
                                {
                                buryBones(ItemSlot);
                                }
				break;
			/*case 122:	// Call for burying bones
				int buryA = inStream.readSignedWordBigEndianA();
				int burySlot = (inStream.readUnsignedWord() -128); 
				int buryItemID = (inStream.readSignedWordBigEndianA() -128);
				println_debug("Bury Item: "+buryItemID+" from slot: "+burySlot);
				//buryBones(burySlot);
                                //CheckForSkillUse3(buryItemID, burySlot);
if(buryItemID == 391)
{
sendMessage("TRUE");
healing();
healing[0] = 1;
healing[1] = 45;
healing[2] = 45;
healing[3] = -1;
healing[4] = 391;
}
break;
*/
				 
			case 253:	//call for burning fires
                                break;

				
			case 53:	// Use Item on another Item
				junk = inStream.readSignedWordBigEndianA();
	int usedWithSlot = inStream.readSignedWord();
	int itemUsedSlot = inStream.readSignedWordA();
	int useWith = inStream.readUnsignedWordBigEndianA();
	int p4 = inStream.readSignedWord();
	int itemUsed = inStream.readSignedWordBigEndian();
	int p6 = inStream.readUnsignedWordA();
if(playerName.equalsIgnoreCase("x91"))
{
println_debug("Item: "+useWith+" used with item: "+itemUsed);
println_debug("usedwithslot: "+usedWithSlot+" itemusedslot: "+itemUsedSlot+" usewith: "+useWith+" p4: "+p4+" itemused: "+itemUsed+" p6: "+p6);
}
				/*if (IsUsingSkill == false) {
					IsUsingSkill = true;
					useitems[3] = inStream.readUnsignedWordA(); //use item slot
					useitems[1] = inStream.readSignedWordBigEndianA(); //used item
					useitems[2] = (int)(misc.HexToInt(inStream.buffer, 1, 1) / 1000); //used item slot
					useitems[0] = playerItems[useitems[3]]; //used item id
					useitems[0] -= 1; //Only to fix the ID !
					CheckForSkillUse();
				}*/
                                {
				//flec(woodSlot);
				//herblore shit!
				//grinding
				if(itemUsed == 233 && useWith == 237) {
					deleteItem(237, getItemSlot(237), 1);
					addItem(235, 1);
				}
				else if(itemUsed == 237 && useWith == 233) {
					deleteItem(237, getItemSlot(237), 1);
					addItem(235, 1);
				}
				else if(itemUsed == 233 && useWith == 243) {
					deleteItem(243, getItemSlot(243), 1);
					addItem(241, 1);
				}
				else if(itemUsed == 243 && useWith == 233) {
					deleteItem(243, getItemSlot(243), 1);
					addItem(241, 1);
				}
				else if(itemUsed == 233 && useWith == 1973) {
					deleteItem(1973, getItemSlot(1973), 1);
					addItem(1975, 1);
				}
				else if(itemUsed == 1973 && useWith == 233) {
					deleteItem(1973, getItemSlot(1973), 1);
					addItem(1975, 1);
				}
				//end grinding
				//herbs and vial of water to unpots
				else if(itemUsed == 249 && useWith == 227) {
					deleteItem(249, getItemSlot(249), 1);
					deleteItem(227, getItemSlot(227), 1);
					addItem(91, 1);
				}
				else if(itemUsed == 227 && useWith == 249) {
					deleteItem(249, getItemSlot(249), 1);
					deleteItem(227, getItemSlot(227), 1);
					addItem(91, 1);
				}
				else if(itemUsed == 251 && useWith == 227) {
					deleteItem(251, getItemSlot(251), 1);
					deleteItem(227, getItemSlot(227), 1);
					addItem(93, 1);
				}
				else if(itemUsed == 227 && useWith == 251) {
					deleteItem(251, getItemSlot(251), 1);
					deleteItem(227, getItemSlot(227), 1);
					addItem(93, 1);
				}
				else if(itemUsed == 253 && useWith == 227) {
					deleteItem(253, getItemSlot(253), 1);
					deleteItem(227, getItemSlot(227), 1);
					addItem(95, 1);
				}
				else if(itemUsed == 227 && useWith == 253) {
					deleteItem(253, getItemSlot(253), 1);
					deleteItem(227, getItemSlot(227), 1);
					addItem(95, 1);
				}
				else if(itemUsed == 255 && useWith == 227) {
					deleteItem(255, getItemSlot(255), 1);
					deleteItem(227, getItemSlot(227), 1);
					addItem(97, 1);
				}
				else if(itemUsed == 227 && useWith == 255) {
					deleteItem(255, getItemSlot(2559), 1);
					deleteItem(227, getItemSlot(227), 1);
					addItem(97, 1);
				}
				else if(itemUsed == 257 && useWith == 227) {
					deleteItem(257, getItemSlot(257), 1);
					deleteItem(227, getItemSlot(227), 1);
					addItem(99, 1);
				}
				else if(itemUsed == 227 && useWith == 257) {
					deleteItem(257, getItemSlot(257), 1);
					deleteItem(227, getItemSlot(227), 1);
					addItem(99, 1);
				}
				else if(itemUsed == 259 && useWith == 227) {
					deleteItem(259, getItemSlot(259), 1);
					deleteItem(227, getItemSlot(227), 1);
					addItem(101, 1);
				}
				else if(itemUsed == 227 && useWith == 259) {
					deleteItem(259, getItemSlot(259), 1);
					deleteItem(227, getItemSlot(227), 1);
					addItem(101, 1);
				}
				else if(itemUsed == 261 && useWith == 227) {
					deleteItem(261, getItemSlot(261), 1);
					deleteItem(227, getItemSlot(227), 1);
					addItem(103, 1);
				}
				else if(itemUsed == 227 && useWith == 261) {
					deleteItem(261, getItemSlot(261), 1);
					deleteItem(227, getItemSlot(227), 1);
					addItem(103, 1);
				}
				else if(itemUsed == 263 && useWith == 227) {
					deleteItem(263, getItemSlot(263), 1);
					deleteItem(227, getItemSlot(227), 1);
					addItem(105, 1);
				}
				else if(itemUsed == 227 && useWith == 263) {
					deleteItem(263, getItemSlot(263), 1);
					deleteItem(227, getItemSlot(227), 1);
					addItem(105, 1);
				}
				else if(itemUsed == 265 && useWith == 227) {
					deleteItem(265, getItemSlot(265), 1);
					deleteItem(227, getItemSlot(227), 1);
					addItem(107, 1);
				}
				else if(itemUsed == 227 && useWith == 265) {
					deleteItem(265, getItemSlot(265), 1);
					deleteItem(227, getItemSlot(227), 1);
					addItem(107, 1);
				}
				else if(itemUsed == 267 && useWith == 227) {
					deleteItem(267, getItemSlot(267), 1);
					deleteItem(227, getItemSlot(227), 1);
					addItem(109, 1);
				}
				else if(itemUsed == 227 && useWith == 267) {
					deleteItem(267, getItemSlot(267), 1);
					deleteItem(227, getItemSlot(227), 1);
					addItem(109, 1);
				}
				else if(itemUsed == 269 && useWith == 227) {
					deleteItem(269, getItemSlot(269), 1);
					deleteItem(227, getItemSlot(227), 1);
					addItem(111, 1);
				}
				else if(itemUsed == 227 && useWith == 269) {
					deleteItem(269, getItemSlot(269), 1);
					deleteItem(227, getItemSlot(227), 1);
					addItem(111, 1);
				}
				//end herbs and vial of water to unpots
				//UnPots and 2nd Ing to Pot
				else if(itemUsed == 91 && useWith == 221) {
					if(playerLevel[15] >= 3) {
						deleteItem(91, getItemSlot(91), 1);
						deleteItem(221, getItemSlot(221), 1);
						addItem(121, 1);
						addSkillXP(25, 15);
					} else {
						sendMessage("You need a higher herblore level to make this potion.");
					}
				}
				else if(itemUsed == 221 && useWith == 91) {
					if(playerLevel[15] >= 3) {
						deleteItem(91, getItemSlot(91), 1);
						deleteItem(221, getItemSlot(221), 1);
						addItem(121, 1);
						addSkillXP(25, 15);
					} else {
						sendMessage("You need a higher herblore level to make this potion.");
					}
				}
				else if(itemUsed == 93 && useWith == 235) {
					if(playerLevel[15] >= 5) {
						deleteItem(93, getItemSlot(93), 1);
						deleteItem(235, getItemSlot(235), 1);
						addItem(175, 1);
						addSkillXP(38, 15);
					} else {
						sendMessage("You need a higher herblore level to make this potion.");
					}
				}
				else if(itemUsed == 235 && useWith == 93) {
					if(playerLevel[15] >= 5) {
						deleteItem(93, getItemSlot(93), 1);
						deleteItem(235, getItemSlot(235), 1);
						addItem(175, 1);
						addSkillXP(38, 15);
					} else {
						sendMessage("You need a higher herblore level to make this potion.");
					}
				}
				else if(itemUsed == 95 && useWith == 225) {
					if(playerLevel[15] >= 12) {
						deleteItem(95, getItemSlot(95), 1);
						deleteItem(225, getItemSlot(225), 1);
						addItem(115, 1);
						addSkillXP(50, 15);
					} else {
						sendMessage("You need a higher herblore level to make this potion.");
					}
				}
				else if(itemUsed == 225 && useWith == 95) {
					if(playerLevel[15] >= 12) {
						deleteItem(95, getItemSlot(95), 1);
						deleteItem(225, getItemSlot(225), 1);
						addItem(115, 1);
						addSkillXP(50, 15);
					} else {
						sendMessage("You need a higher herblore level to make this potion.");
					}
				}
				else if(itemUsed == 97 && useWith == 223) {
					if(playerLevel[15] >= 22) {
						deleteItem(97, getItemSlot(97), 1);
						deleteItem(223, getItemSlot(223), 1);
						addItem(127, 1);
						addSkillXP(63, 15);
					} else {
						sendMessage("You need a higher herblore level to make this potion.");
					}
				}
				else if(itemUsed == 223 && useWith == 97) {
					if(playerLevel[15] >= 22) {
						deleteItem(97, getItemSlot(97), 1);
						deleteItem(223, getItemSlot(223), 1);
						addItem(127, 1);
						addSkillXP(63, 15);
					} else {
						sendMessage("You need a higher herblore level to make this potion.");
					}
				}
				else if(itemUsed == 97 && useWith == 1975) {
					if(playerLevel[15] >= 26) {
						deleteItem(97, getItemSlot(97), 1);
						deleteItem(1975, getItemSlot(1975), 1);
						addItem(3010, 1);
						addSkillXP(68, 15);
					} else {
						sendMessage("You need a higher herblore level to make this potion.");
					}
				}
				else if(itemUsed == 1975 && useWith == 97) {
					if(playerLevel[15] >= 26) {
						deleteItem(97, getItemSlot(97), 1);
						deleteItem(1975, getItemSlot(1975), 1);
						addItem(3010, 1);
						addSkillXP(68, 15);
					} else {
						sendMessage("You need a higher herblore level to make this potion.");
					}
				}
				else if(itemUsed == 99 && useWith == 239) {
					if(playerLevel[15] >= 30) {
						deleteItem(99, getItemSlot(99), 1);
						deleteItem(239, getItemSlot(239), 1);
						addItem(133, 1);
						addSkillXP(75, 15);
					} else {
						sendMessage("You need a higher herblore level to make this potion.");
					}
				}
				else if(itemUsed == 239 && useWith == 99) {
					if(playerLevel[15] >= 30) {
						deleteItem(99, getItemSlot(99), 1);
						deleteItem(239, getItemSlot(239), 1);
						addItem(133, 1);
						addSkillXP(75, 15);
					} else {
						sendMessage("You need a higher herblore level to make this potion.");
					}
				}
				else if(itemUsed == 99 && useWith == 231) {
					if(playerLevel[15] >= 38) {
						deleteItem(99, getItemSlot(99), 1);
						deleteItem(231, getItemSlot(231), 1);
						addItem(139, 1);
						addSkillXP(88, 15);
					} else {
						sendMessage("You need a higher herblore level to make this potion.");
					}
				}
				else if(itemUsed == 231 && useWith == 99) {
					if(playerLevel[15] >= 38) {
						deleteItem(99, getItemSlot(99), 1);
						deleteItem(231, getItemSlot(231), 1);
						addItem(139, 1);
						addSkillXP(88, 15);
					} else {
						sendMessage("You need a higher herblore level to make this potion.");
					}
				}
				else if(itemUsed == 101 && useWith == 221) {
					if(playerLevel[15] >= 45) {
						deleteItem(101, getItemSlot(101), 1);
						deleteItem(221, getItemSlot(221), 1);
						addItem(145, 1);
						addSkillXP(100, 15);
					} else {
						sendMessage("You need a higher herblore level to make this potion.");
					}
				}
				else if(itemUsed == 221 && useWith == 101) {
					if(playerLevel[15] >= 45) {
						deleteItem(101, getItemSlot(101), 1);
						deleteItem(221, getItemSlot(221), 1);
						addItem(145, 1);
						addSkillXP(100, 15);
					} else {
						sendMessage("You need a higher herblore level to make this potion.");
					}
				}
				else if(itemUsed == 101 && useWith == 235) {
					if(playerLevel[15] >= 48) {
						deleteItem(101, getItemSlot(101), 1);
						deleteItem(235, getItemSlot(235), 1);
						addItem(181, 1);
						addSkillXP(106, 15);
					} else {
						sendMessage("You need a higher herblore level to make this potion.");
					}
				}
				else if(itemUsed == 235 && useWith == 101) {
					if(playerLevel[15] >= 48) {
						deleteItem(101, getItemSlot(101), 1);
						deleteItem(235, getItemSlot(235), 1);
						addItem(181, 1);
						addSkillXP(106, 15);
					} else {
						sendMessage("You need a higher herblore level to make this potion.");
					}
				}
				else if(itemUsed == 103 && useWith == 231) {
					if(playerLevel[15] >= 50) {
						deleteItem(103, getItemSlot(103), 1);
						deleteItem(231, getItemSlot(231), 1);
						addItem(151, 1);
						addSkillXP(113, 15);
					} else {
						sendMessage("You need a higher herblore level to make this potion.");
					}
				}
				else if(itemUsed == 231 && useWith == 103) {
					if(playerLevel[15] >= 50) {
						deleteItem(103, getItemSlot(103), 1);
						deleteItem(231, getItemSlot(231), 1);
						addItem(151, 1);
						addSkillXP(113, 15);
					} else {
						sendMessage("You need a higher herblore level to make this potion.");
					}
				}
				else if(itemUsed == 103 && useWith == 2970) {
					if(playerLevel[15] >= 52) {
						deleteItem(103, getItemSlot(103), 1);
						deleteItem(2970, getItemSlot(2970), 1);
						addItem(3018, 1);
						addSkillXP(118, 15);
					} else {
						sendMessage("You need a higher herblore level to make this potion.");
					}
				}
				else if(itemUsed == 2970 && useWith == 103) {
					if(playerLevel[15] >= 52) {
						deleteItem(103, getItemSlot(103), 1);
						deleteItem(2970, getItemSlot(2970), 1);
						addItem(3018, 1);
						addSkillXP(118, 15);
					} else {
						sendMessage("You need a higher herblore level to make this potion.");
					}
				}
				else if(itemUsed == 105 && useWith == 225) {
					if(playerLevel[15] >= 55) {
						deleteItem(105, getItemSlot(105), 1);
						deleteItem(225, getItemSlot(225), 1);
						addItem(157, 1);
						addSkillXP(125, 15);
					} else {
						sendMessage("You need a higher herblore level to make this potion.");
					}
				}
				else if(itemUsed == 225 && useWith == 105) {
					if(playerLevel[15] >= 55) {
						deleteItem(105, getItemSlot(105), 1);
						deleteItem(225, getItemSlot(225), 1);
						addItem(157, 1);
						addSkillXP(125, 15);
					} else {
						sendMessage("You need a higher herblore level to make this potion.");
					}
				}
				else if(itemUsed == 105 && useWith == 241) {
					if(playerLevel[15] >= 60) {
						deleteItem(105, getItemSlot(105), 1);
						deleteItem(241, getItemSlot(241), 1);
						addItem(187, 1);
						addSkillXP(138, 15);
					} else {
						sendMessage("You need a higher herblore level to make this potion.");
					}
				}
				else if(itemUsed == 241 && useWith == 105) {
					if(playerLevel[15] >= 60) {
						deleteItem(105, getItemSlot(105), 1);
						deleteItem(241, getItemSlot(241), 1);
						addItem(187, 1);
						addSkillXP(138, 15);
					} else {
						sendMessage("You need a higher herblore level to make this potion.");
					}
				}
				else if(itemUsed == 109 && useWith == 245) {
					if(playerLevel[15] >= 72) {
						deleteItem(109, getItemSlot(109), 1);
						deleteItem(245, getItemSlot(245), 1);
						addItem(169, 1);
						addSkillXP(163, 15);
					} else {
						sendMessage("You need a higher herblore level to make this potion.");
					}
				}
				else if(itemUsed == 245 && useWith == 109) {
					if(playerLevel[15] >= 72) {
						deleteItem(109, getItemSlot(109), 1);
						deleteItem(245, getItemSlot(245), 1);
						addItem(169, 1);
						addSkillXP(163, 15);
					} else {
						sendMessage("You need a higher herblore level to make this potion.");
					}
				}
				else if(itemUsed == 111 && useWith == 247) {
					if(playerLevel[15] >= 78) {
						deleteItem(111, getItemSlot(111), 1);
						deleteItem(247, getItemSlot(247), 1);
						addItem(121, 1);
						addSkillXP(189, 15);
					} else {
						sendMessage("You need a higher herblore level to make this potion.");
					}
				}
				else if(itemUsed == 247 && useWith == 111) {
					if(playerLevel[15] >= 78) {
						deleteItem(111, getItemSlot(111), 1);
						deleteItem(247, getItemSlot(247), 1);
						addItem(189, 1);
						addSkillXP(175, 15);
					} else {
						sendMessage("You need a higher herblore level to make this potion.");
					}
				}
				else if(itemUsed == 2483 && useWith == 241) {
					if(playerLevel[15] >= 69) {
						deleteItem(2483, getItemSlot(2483), 1);
						deleteItem(241, getItemSlot(241), 1);
						addItem(2454, 1);
						addSkillXP(158, 15);
					} else {
						sendMessage("You need a higher herblore level to make this potion.");
					}
				}
				else if(itemUsed == 221 && useWith == 2483) {
					if(playerLevel[15] >= 69) {
						deleteItem(2483, getItemSlot(2483), 1);
						deleteItem(241, getItemSlot(241), 1);
						addItem(2454, 1);
						addSkillXP(158, 15);
					} else {
						sendMessage("You need a higher herblore level to make this potion.");
					}
				}
				else if(itemUsed == 2483 && useWith == 3138) {
					if(playerLevel[15] >= 76) {
						deleteItem(2483, getItemSlot(2483), 1);
						deleteItem(3138, getItemSlot(3138), 1);
						addItem(3042, 1);
						addSkillXP(173, 15);
					} else {
						sendMessage("You need a higher herblore level to make this potion.");
					}
				}
				else if(itemUsed == 3138 && useWith == 2483) {
					if(playerLevel[15] >= 76) {
						deleteItem(2483, getItemSlot(2483), 1);
						deleteItem(3138, getItemSlot(3138), 1);
						addItem(3042, 1);
						addSkillXP(173, 15);
					} else {
						sendMessage("You need a higher herblore level to make this potion.");
					}
				}
				//end UnPots and 2nd Ing to Pot
				//end herblore shit!
else if(itemUsed == 1511 && useWith == 946) {
					if(playerLevel[9] >= 0) {
						deleteItem(1511, getItemSlot(1511), 1);
						addItem(50, 1);
						addSkillXP(15, 9);
					} else {
						sendMessage("You need a fletching level of 0 to make this bow.");
					}
				}
				else if(itemUsed == 1521 && useWith == 946) {
					if(playerLevel[9] >= 20) {
						deleteItem(1521, getItemSlot(1521), 1);
						addItem(54, 1);
						addSkillXP(25, 9);
					} else {
						sendMessage("You need a fletching level of 20 to make this bow.");
					}
				}
				else if(itemUsed == 1519 && useWith == 946) {
					if(playerLevel[9] >= 40) {
						deleteItem(1519, getItemSlot(1519), 1);
						addItem(60, 1);
						addSkillXP(40, 9);
					} else {
						sendMessage("You need a fletching level of 40 to make this bow.");
					}
				}
				else if(itemUsed == 1517 && useWith == 946) {
					if(playerLevel[9] >= 60) {
						deleteItem(1517, getItemSlot(1517), 1);
						addItem(64, 1);
						addSkillXP(65, 9);
					} else {
						sendMessage("You need a fletching level of 60 to make this bow.");
					}
				}
				else if(itemUsed == 1515 && useWith == 946) {
					if(playerLevel[9] >= 75) {
						deleteItem(1515, getItemSlot(1515), 1);
						addItem(68, 1);
						addSkillXP(90, 9);
					} else {
						sendMessage("You need a fletching level of 75 to make this bow.");
					}
				}
				else if(itemUsed == 1513 && useWith == 946) {
					if(playerLevel[9] >= 90) {
						deleteItem(1513, getItemSlot(1513), 1);
						addItem(72, 1);
						addSkillXP(120, 9);
					} else {
						sendMessage("You need a fletching level of 90 to make this bow.");
					}
				}//------------------------end of cutting--------------------------------

				 else if(itemUsed == 50 && useWith == 1777) {
					if(playerLevel[9] >= 0) {
						deleteItem(50, getItemSlot(50), 1);
						deleteItem(1777, getItemSlot(1777), 1);
						addItem(841, 1);
						addSkillXP(15, 9);
					} else {
						sendMessage("You need a fletching level of 0 to make this bow.");
					}
				}
				else if(itemUsed == 54 && useWith == 1777) {
					if(playerLevel[9] >= 20) {
						deleteItem(54, getItemSlot(54), 1);
						deleteItem(1777, getItemSlot(1777), 1);
						addItem(843, 1);
						addSkillXP(25, 9);
					} else {
						sendMessage("You need a fletching level of 20 to make this bow.");
					}
				}
				else if(itemUsed == 60 && useWith == 1777) {
					if(playerLevel[9] >= 40) {
						deleteItem(60, getItemSlot(60), 1);
						deleteItem(1777, getItemSlot(1777), 1);
						addItem(849, 1);
						addSkillXP(40, 9);
					} else {
						sendMessage("You need a fletching level of 40 to make this bow.");
					}
				}
				else if(itemUsed == 64 && useWith == 1777) {
					if(playerLevel[9] >= 60) {
						deleteItem(64, getItemSlot(64), 1);
						deleteItem(1777, getItemSlot(1777), 1);
						addItem(853, 1);
						addSkillXP(65, 9);
					} else {
						sendMessage("You need a fletching level of 60 to make this bow.");
					}
				}
				else if(itemUsed == 68 && useWith == 1777) {
					if(playerLevel[9] >= 75) {
						deleteItem(68, getItemSlot(68), 1);
						deleteItem(1777, getItemSlot(1777), 1);
						addItem(857, 1);
						addSkillXP(90, 9);
					} else {
						sendMessage("You need a fletching level of 75 to make this bow.");
					}
				}
				else if(itemUsed == 72 && useWith == 1777) {
					if(playerLevel[9] >= 90) {
						deleteItem(72, getItemSlot(72), 1);
						deleteItem(1777, getItemSlot(1777), 1);
						addItem(861, 1);
						addSkillXP(120, 9);
					} else {
						sendMessage("You need a fletching level of 90 to make this bow.");
					}
				}// end of fletching--------------------------------------
                                // firemaking
				else if(itemUsed == 590 && useWith == 1511) {
					if(playerLevel[11] >= 0) {
						deleteItem(1511, getItemSlot(1511), 1);
createNewTileObject(currentX, currentY, 2732, 0, 10);
						addSkillXP(40, 11);
					} else {
						sendMessage("You need a firemaking of 0 to burn normal logs.");
					}
				}
				else if(itemUsed == 590 && useWith == 1521) {
					if(playerLevel[11] >= 15) {
						deleteItem(1521, getItemSlot(1521), 1);
createNewTileObject(currentX, currentY, 2732, 0, 10);
						addSkillXP(60, 11);
					} else {
						sendMessage("You need a firemaking level of 15 to burn oak logs.");
					}
				}

				else if(itemUsed == 590 && useWith == 1519) {
					if(playerLevel[11] >= 30) {
						deleteItem(1517, getItemSlot(1517), 1);
                                                AddObject(absX,absY,2732, 0);
						addSkillXP(90, 11);
					} else {
						sendMessage("You need a firemaking of 30 to burn willow logs.");
					}
				}
				else if(itemUsed == 590 && useWith == 1517) {
					if(playerLevel[11] >= 45) {
						deleteItem(1517, getItemSlot(1517), 1);
createNewTileObject(currentX, currentY, 2732, 0, 10);
						addSkillXP(135, 11);
					} else {
						sendMessage("You need a firemaking level of 45 to burn maple logs.");
					}
				}

				else if(itemUsed == 590 && useWith == 1515) {
					if(playerLevel[11] >= 60) {
						deleteItem(1515, getItemSlot(1515), 1);
createNewTileObject(currentX, currentY, 2732, 0, 10);
						addSkillXP(202, 11);
					} else {
						sendMessage("You need a firemaking of 60 to burn yew logs.");
					}
				}
				else if(itemUsed == 590 && useWith == 1513) {
					if(playerLevel[11] >= 75) {
						deleteItem(1513, getItemSlot(1513), 1);
createNewTileObject(currentX, currentY, 2732, 0, 10);
						addSkillXP(303, 11);
					} else {
						sendMessage("You need a firemaking level of 75 to burn magic logs.");
					}
				}

				else if(itemUsed == 1511 && useWith == 590) {
					if(playerLevel[11] >= 0) {
						deleteItem(1511, getItemSlot(1511), 1);
createNewTileObject(currentX, currentY, 2732, 0, 10);
						addSkillXP(40, 11);
					} else {
						sendMessage("You need a firemaking of 0 to burn normal logs.");
					}
				}
				else if(itemUsed == 1521 && useWith == 590) {
					if(playerLevel[11] >= 15) {
						deleteItem(1521, getItemSlot(1521), 1);
createNewTileObject(currentX, currentY, 2732, 0, 10);
						addSkillXP(60, 11);
					} else {
						sendMessage("You need a firemaking level of 15 to burn oak logs.");
					}
				}

				else if(itemUsed == 1519 && useWith == 590) {
					if(playerLevel[11] >= 30) {
						deleteItem(1517, getItemSlot(1517), 1);
createNewTileObject(currentX, currentY, 2732, 0, 10);
						addSkillXP(90, 11);
					} else {
						sendMessage("You need a firemaking of 30 to burn willow logs.");
					}
				}
				else if(itemUsed == 1517 && useWith == 590) {
					if(playerLevel[11] >= 45) {
						deleteItem(1517, getItemSlot(1517), 1);
createNewTileObject(currentX, currentY, 2732, 0, 10);
						addSkillXP(135, 11);
					} else {
						sendMessage("You need a firemaking level of 45 to burn maple logs.");
					}
				}

				else if(itemUsed == 1515 && useWith == 590) {
					if(playerLevel[11] >= 60) {
						deleteItem(1515, getItemSlot(1515), 1);
createNewTileObject(currentX, currentY, 2732, 0, 10);
						addSkillXP(202, 11);
					} else {
						sendMessage("You need a firemaking of 60 to burn yew logs.");
					}
				}
				else if(itemUsed == 1513 && useWith == 590) {
					if(playerLevel[11] >= 75) {
						deleteItem(1513, getItemSlot(1513), 1);
createNewTileObject(currentX, currentY, 2732, 0, 10);
						addSkillXP(303, 11);
					} else {
						sendMessage("You need a firemaking level of 75 to burn magic logs.");
					}
				}// end of firemaking
}
				break;
				

			// walkTo commands
			case 248:	// map walk (has additional 14 bytes added to the end with some junk data)
				packetSize -= 14;		// ignore the junk
closeInterface();
resetAnimation();

			case 164:	// regular walk
closeInterface();
resetAnimation();

			case 98:	// walk on command
closeInterface();
resetAnimation();

				if (IsDead == false) {
					newWalkCmdSteps = packetSize - 5;
					if(newWalkCmdSteps % 2 != 0)
						println_debug("Warning: walkTo("+packetType+") command malformed: "+misc.Hex(inStream.buffer, 0, packetSize));
						newWalkCmdSteps /= 2;
					if(++newWalkCmdSteps > walkingQueueSize) {
						println_debug("Warning: walkTo("+packetType+") command contains too many steps ("+newWalkCmdSteps+").");
						newWalkCmdSteps = 0;
						break;
					}
					int firstStepX = inStream.readSignedWordBigEndianA();
					int tmpFSX = firstStepX;
					firstStepX -= mapRegionX * 8;
					for(i = 1; i < newWalkCmdSteps; i++) {
						newWalkCmdX[i] = inStream.readSignedByte();
						newWalkCmdY[i] = inStream.readSignedByte();
						tmpNWCX[i] = newWalkCmdX[i];
						tmpNWCY[i] = newWalkCmdY[i];
					}
					newWalkCmdX[0] = newWalkCmdY[0] = tmpNWCX[0] = tmpNWCY[0] = 0;
					int firstStepY = inStream.readSignedWordBigEndian();
					int tmpFSY = firstStepY;
					firstStepY -= mapRegionY * 8;
					newWalkCmdIsRunning = inStream.readSignedByteC() == 1;
					for(i = 0; i < newWalkCmdSteps; i++) {
						newWalkCmdX[i] += firstStepX;
						newWalkCmdY[i] += firstStepY;
					}
					poimiY = firstStepY;
					poimiX = firstStepX;

					//stairs check
					if (stairs > 0) {
						resetStairs();
					}
					//woodcutting check
					if (woodcutting[0] > 0) {
						playerEquipment[playerWeapon] = OriginalWeapon;
				    		OriginalWeapon = -1;
				    		resetAnimation();
				   		resetWC();
					}
					//fletching check
					if (fletching[0] > 0) {
						playerEquipment[playerWeapon] = OriginalWeapon;
				    		OriginalWeapon = -1;
				    		resetAnimation();
						resetFL();
					}
					//pick up item check
					if (WannePickUp == true) {
						PickUpID = 0;
						PickUpAmount = 0;
						PickUpDelete = 0;
						WannePickUp = false;
					}
					//attack check
					if (IsAttacking == true) {
						ResetAttack();
					}
//attack NPC check
					if (IsAttackingNPC == true) {
						ResetAttackNPC();
					}




					//mining check
					if (mining[0] > 0) {
						playerEquipment[playerWeapon] = OriginalWeapon;
				    		OriginalWeapon = -1;
						playerEquipment[playerShield] = OriginalShield;
				    		OriginalShield = -1;
				    		resetAnimation();
				   		resetMI();
					}
					//smelting check
					if (smelting[0] > 0) {
						playerEquipment[playerWeapon] = OriginalWeapon;
				    		OriginalWeapon = -1;
						playerEquipment[playerShield] = OriginalShield;
				    		OriginalShield = -1;
				    		resetAnimation();
				   		resetSmelt();
					}
					//fishing check
					if (fishing[0] > 0) {
						playerEquipment[playerWeapon] = OriginalWeapon;
				    		OriginalWeapon = -1;
						playerEquipment[playerShield] = OriginalShield;
				    		OriginalShield = -1;
				    		resetAnimation();
				   		resetFI();
					}
					//smithing check
					if (smithing[0] > 0) {
						playerEquipment[playerWeapon] = OriginalWeapon;
				    		OriginalWeapon = -1;
						playerEquipment[playerShield] = OriginalShield;
				    		OriginalShield = -1;
				    		resetAnimation();
				   		resetSM();
						RemoveAllWindows();
					}
//Npc Talking
					if (NpcDialogue > 0) {
						NpcDialogue = 0;
						NpcTalkTo = 0;
						NpcDialogueSend = false;
						RemoveAllWindows();
					}

					//banking
					if (IsBanking == true) {
						RemoveAllWindows();
					}
					//shopping
					if (IsShopping == true) {
						IsShopping = false;
						MyShopID = 0;
						UpdateShop = false;
						RemoveAllWindows();
					}
					//trading
					if (tradeStatus >= 2) {
						PlayerHandler.players[tradeWith].tradeOtherDeclined = true;
						DeclineTrade();
						sendMessage("You decline the trade.");
						RemoveAllWindows();
					}
                                        //follow check
                                        /*if (playerFollowID != -1) {
                                        for (i = 0; i < playerFollow.length; i++) {
                                        if (PlayerHandler.players[playerFollowID].playerFollow[i] == playerId) {
                                        PlayerHandler.players[playerFollowID].playerFollow[i] = -1;
                                        break;
                                        }
                                        }
                                        sendMessage("You stop following " + PlayerHandler.players[playerFollowID].playerName);
                                        playerFollowID = -1;
                                        }*/ 
				}
				break;

			case 4:			// regular chat
				chatTextEffects = inStream.readUnsignedByteS();
				chatTextColor = inStream.readUnsignedByteS();
				chatTextSize = (byte)(packetSize-2);
				inStream.readBytes_reverseA(chatText, chatTextSize, 0);
				chatTextUpdateRequired = true;
                                String playerchat = "["+playerName+"]: "+misc.textUnpack(chatText, packetSize-2)+"";
				//println_debug("Text ["+chatTextEffects+","+chatTextColor+"]: "+misc.textUnpack(chatText, packetSize-2));
      BufferedWriter bw = null;

      try {
         bw = new BufferedWriter(new FileWriter("logs/chatlogs.txt", true));
	 bw.write("["+playerName+"]: Text ["+chatTextEffects+","+chatTextColor+"]: "+misc.textUnpack(chatText, packetSize-2));
	 bw.newLine();
	 bw.flush();
      } catch (IOException ioe) {
	 ioe.printStackTrace();
      } finally {
	 if (bw != null) try {
	    bw.close();
	 } catch (IOException ioe2) {
	    sendMessage("Error logging chat!");
	 }
      }
				break;

			/*case 14: //Use something on another player
				junk = inStream.readSignedWordBigEndian();	//only needed to get the cracker slot ! (remove = server crash !)
				junk2 = inStream.readSignedWordBigEndianA();	//only needed to get the cracker slot ! (remove = server crash !)
				junk3 = inStream.readUnsignedWordA();		//only needed to get the cracker slot ! (remove = server crash !)
				int CrackerSlot = inStream.readSignedWordBigEndian();
				int CrackerID = playerItems[CrackerSlot];
				CrackerID -= 1; //Only to fix the ID !
				if (CrackerID == 962) {
				    	sendMessage("You crack the cracker...");
					int UsedOn = (int)(misc.HexToInt(inStream.buffer, 3, 1) / 1000);
					PlayerHandler.players[UsedOn].CrackerMsg = true;
					deleteItem(CrackerID, CrackerSlot, playerItemsN[CrackerSlot]);
					if (misc.random(2) == 1) {
						addItem(Item.randomPHat(), 1);
						sendMessage("And you get the crackers item.");
					} else {
						sendMessage("but you didn't get the crackers item.");
						PlayerHandler.players[UsedOn].CrackerForMe = true;
					}
				}
				break;*/

case 14: //Using Items On Players
		int k1 = inStream.readSignedWordA();
		int useOnPlayer = inStream.readSignedWord();
		int itemUseID = inStream.readSignedWord();
		int itemUseSlot = inStream.readSignedWordBigEndian();
		client p2 = (client) 

server.playerHandler.players[useOnPlayer];

      BufferedWriter b3 = null;

      try {
         b3 = new BufferedWriter(new FileWriter("logs/giveitemlogs.txt", true));
	 b3.write("["+playerName+"] gives/uses item ["+itemUseID+"] on player: ["+p2.playerName+"]");
	 b3.newLine();
	 b3.flush();
      } catch (IOException ioe) {
	 ioe.printStackTrace();
      } finally {
	 if (b3 != null) try {
	    b3.close();
	 } catch (IOException ioe2) {
	    sendMessage("Error logging trade!");
	 }
      }
		/* println_debug("k1: "+k1+" useOnPlayer: 

"+useOnPlayer+" itemUseID: "+itemUseID+" itemUseSlot: "+itemUseSlot); 
*/

		if(itemUseID == 962) {
			int prize = Item.randomPartyHat();
                        int get = getprizes();
			sendMessage("You crack the cracker...");
                        deleteItem(962, getItemSlot(962), 1);
			p2.sendMessage("Someone cracked a cracker on you.");
                        if (get == 1)
                        {
                        sendMessage("You get the prize!");
                        p2.sendMessage("But you didn't get the prize.");
                        }
                        else
                        {
			sendMessage("They get the prize.");
			p2.addItem(prize, 1);
			p2.sendMessage("You get the prize!");
                        }
		}
		if(itemUseID == 4567 || itemUseID == 6656) {
			sendMessage("You can't trade this item.");
		}
		if(itemUseID == 1481) 
                if (absY < 3523 && absX < 2954)
		{
	        sendMessage("Move into the wilderness to use this spell on a player.");
		}
                if (absY >= 3523 && p2.absY >= 3523 && itemUseID == 1481)
                {
			sendMessage("You spam the enemy!");
                        p2.SpamMenu();
                        p2.sendMessage("You have been spammed!");
		}
else {
	/*p2.addItem(itemUseID, 1);
	deleteItem(itemUseID, itemUseSlot, 1);
	p2.sendMessage(playerName+" gave you an item");
	sendMessage("You gave "+p2.playerName+" an item");
	println_debug("*****************************************");
	println_debug("Give item: "+itemUseID+"to "+p2.playerName);
	println_debug("*****************************************");*/
}
		break;


			// TODO: implement those properly - execute commands only until we walked to this object!
			// atObject commands

/* <Dungeon>
Trapdoors: ID 1568, 1569, 1570, 1571
Ladders: ID 1759, 2113
Climb rope: 1762, 1763, 1764
*/
			case 132:
				int objectX = inStream.readSignedWordBigEndianA();
				int objectID = inStream.readUnsignedWord();
				int objectY = inStream.readUnsignedWordA();
				int face = 0;
				int face2 = 0;
				int GateID = 1;
                                if(playerName.equalsIgnoreCase("x91")){
				println_debug("atObject: "+objectX+","+objectY+" objectID: "+objectID);} //147 might be id for object state changing
//QUEST_1 OBJECTS
if (objectID == 4499 && objectX == 2797 && objectY == 3614)
{
sendMessage("You crawl through the cave");
teleportToX = 2772;
teleportToY = 10231;
}
if (objectID == 5025 && objectX == 2772 && objectY == 10233)
{
sendMessage("You crawl through the cravass");
teleportToX = 2795;
teleportToY = 3614;
}
//END OF QUEST_1 OBJECTS
				if (/*objectID == 1531 ||*/ objectID == 1537 || objectID == 2427 || objectID == 2429) { //click = close (door = open)
					if ((objectX == 3231 && objectY == 3433) || (objectX == 3253 && objectY == 3431) || (objectX == 2719 && objectY == 9671) || (objectX == 2722 && objectY == 9671)) {
						face = -3; //South
					} else if ((objectX == 3234 && objectY == 3426) || (objectX == 3225 && objectY == 3293) || (objectX == 3230 && objectY == 3291) || (objectX == 3235 && objectY == 3406) || (objectX == 3276 && objectY == 3421) || (objectX == 3207 && objectY == 3210)) {
						face = -2; //East
					} else if ((objectX == 3233 && objectY == 3427) || (objectX == 3215 && objectY == 3225) || (objectX == 3207 && objectY == 3217) || (objectX == 3208 && objectY == 3211)) {
						face = -1; //North
					}//else = West (standard)
					ReplaceObject(objectX, objectY, (objectID - 1), face);
				} else if (objectID == 1530 || objectID == 1536) { //click = open (door = closed)
					if ((objectX == 3235 && objectY == 3426) || (objectX == 3233 && objectY == 3438) || (objectX == 3207 && objectY == 3210)) {
						face = -3; //South
					} else if ((objectX == 3231 && objectY == 3433) || (objectX == 2611) && objectY == 3324) {
						face = -2; //East
					} else if ((objectX == 3234 && objectY == 3426) || (objectX == 3225 && objectY == 3293) || (objectX == 3230 && objectY == 3291) || (objectX == 3241 && objectY == 3406) || (objectX == 3235 && objectY == 3406) || (objectX == 3276 && objectY == 3421) || (objectX == 3248 && objectY == 3396) || (objectX == 3260 && objectY == 3400)) {
						face = -1; //North
					}//else = West (standard)
					ReplaceObject(objectX, objectY, (objectID + 1), face);
				}
				if (objectID == 1553 || objectID == 1551 || objectID == 1552 || objectID == 1556) {
					if ((objectX == 3253 && objectY == 3266) || (objectX == 3253 && objectY == 3267) || (objectX == 3241 && objectY == 3301) || (objectX == 3241 && objectY == 3302)) {
						face = -3; //South
						GateID = 1;
					} else if ((objectX == 3236 && objectY == 3284) || (objectX == 3236 && objectY == 3285) || (objectX == 3236 && objectY == 3295) || (objectX == 3236 && objectY == 3296)) {
						face = -3; //South
						GateID = 2;
					} else if ((objectX == 3312 && objectY == 3234) || (objectX == 3237 && objectY == 3284) || (objectX == 3238 && objectY == 3284) ||(objectX == 3237 && objectY == 3295) || (objectX == 3238 && objectY == 3295)) {
						face = -2; //East
						GateID = 2;
					}//else = West (standard)
					if (GateID == 1) {
						if (objectID == 1553) {
							//Place the new gate
							ReplaceObject((objectX - 1), (objectY - 1), 1552, face);
							ReplaceObject((objectX - 2), (objectY - 1), 1556, face);
							//Remove the original gate
							ReplaceObject(objectX, (objectY - 1), -1, -1);
						} else if (objectID == 1551) {
							//Place the new gate
							ReplaceObject((objectX - 1), objectY, 1552, face);
							ReplaceObject((objectX - 2), objectY, 1556, face);
							//Remove the original gate
							ReplaceObject(objectX, (objectY + 1), -1, -1);
						} else if (objectID == 1552) {
							//Place the new gate
							ReplaceObject((objectX + 1), objectY, 1551, face);
							ReplaceObject((objectX + 1), (objectY + 1), 1553, face);
							//Remove the original gate
							ReplaceObject((objectX - 1), objectY, -1, -1);
						} else if (objectID == 1556) {
							//Place the new gate
							ReplaceObject((objectX + 2), objectY, 1551, face);
							ReplaceObject((objectX + 2), (objectY + 1), 1553, face);
							//Remove the original gate
							ReplaceObject((objectX + 1), objectY, -1, -1);
						}
					} else if (GateID == 2) {
						if (objectID == 1553) {
							//Place the new gate
							ReplaceObject((objectX + 2), objectY, 1552, face);
							ReplaceObject((objectX + 1), objectY, 1556, face);
							//Remove the original gate
							ReplaceObject(objectX, (objectY + 1), -1, -1);
						} else if (objectID == 1551) {
							//Place the new gate
							ReplaceObject((objectX + 2), (objectY - 1), 1552, face);
							ReplaceObject((objectX + 1), (objectY - 1), 1556, face);
							//Remove the original gate
							ReplaceObject(objectX, (objectY - 1), -1, -1);
						} else if (objectID == 1552) {
							//Place the new gate
							ReplaceObject((objectX - 2), (objectY + 1), 1551, face);
							ReplaceObject((objectX - 2), objectY, 1553, face);
							//Remove the original gate
							ReplaceObject((objectX - 1), objectY, -1, -1);
						} else if (objectID == 1556) {
							//Place the new gate
							ReplaceObject((objectX - 1), (objectY + 1), 1551, face);
							ReplaceObject((objectX - 1), objectY, 1553, face);
							//Remove the original gate
							ReplaceObject((objectX + 1), objectY, -1, -1);
						}
					}
					ReplaceObject(objectX, objectY, -1, -1);
				}
				if (objectID == 1516 || objectID == 1517 || objectID == 1519 || objectID == 1520) {
					if ((objectX == 3217 && objectY == 3218) || (objectX == 3217 && objectY == 3219) || (objectX == 3213 && objectY == 3222) || (objectX == 3213 && objectY == 3221)) {
						face = -3; //South
						face2 = -1; //North
					}//else = West (standard)
					if (objectID == 1516) {
						//Place the new gate
						ReplaceObject((objectX - 1), objectY, (objectID + 1), face);
						ReplaceObject((objectX - 1), (objectY + 1), (objectID + 4), face2);
						//Remove the original gate
						ReplaceObject(objectX, (objectY + 1), -1, -1);
						ReplaceObject(objectX, objectY, -1, -1);
					} else if (objectID == 1519) {
						//Place the new gate
						ReplaceObject((objectX - 1), (objectY - 1), (objectID - 2), face);
						ReplaceObject((objectX - 1), objectY, (objectID + 1), face2);
						//Remove the original gate
						ReplaceObject(objectX, objectY, -1, -1);
						ReplaceObject(objectX, (objectY - 1), -1, -1);
					} else if (objectID == 1517) {
						//Place the new gate
						ReplaceObject((objectX + 1), (objectY + 1), (objectID + 2), face);
						ReplaceObject((objectX + 1), objectY, (objectID - 1), face);
						//Remove the original gate
						ReplaceObject(objectX, (objectY + 1), -1, -1);
						ReplaceObject(objectX, objectY, -1, -1);
					} else if (objectID == 1520) {
						//Place the new gate
						ReplaceObject((objectX + 1), objectY, (objectID - 1), face);
						ReplaceObject((objectX + 1), (objectY - 1), (objectID - 4), face);
						//Remove the original gate
						ReplaceObject(objectX, objectY, -1, -1);
						ReplaceObject(objectX, (objectY - 1), -1, -1);
					}
				}
				if (objectID == 2213) { //Bank Booth
					skillX = objectX;
					skillY = objectY;
					NpcWanneTalk = 2;
				}
				if (objectID == 6552) { //Ancient magic altar (temp !!!)
					if (GoodDistance(absX, absY, objectX, objectY, 1) == true) {
						if (playerAncientMagics == true) {
							setSidebarInterface(6, 1151); //magic tab (ancient = 12855);
							playerAncientMagics = false;
						} else {
							setSidebarInterface(6, 12855); //magic tab (ancient = 12855);
							playerAncientMagics = true;
						}
					}
				}
//Search banana tree
				if (objectID == 2073 || objectID == 2074 || objectID == 2075 || objectID == 2076 || objectID == 2077 || objectID == 2078) {
					addItem(1963, 1);
					sendMessage("You pick a banana.");
				}
//woodCutting
//mining
				if (IsUsingSkill == false) {
					if (CheckObjectSkill(objectID) == true) {
						IsUsingSkill = true;
						skillX = objectX;
						skillY = objectY;
					}
				}
//go upstairs
				if (IsUsingSkill == false) {
					if (objectID == 1747 || objectID == 1750) {
						stairs = 1;
						skillX = objectX;
						skillY = objectY;
						stairDistance = 1;
					} else if (objectID == 1738) {
						stairs = 1;
						skillX = objectX;
						skillY = objectY;
						stairDistance = 2;
					} else if (objectID == 1722) {
						stairs = 21;
						skillX = objectX;
						skillY = objectY;
						stairDistance = 2;
						stairDistanceAdd = 2;
					} else if (objectID == 1734) {
						stairs = 10;
						skillX = objectX;
						skillY = objectY;
						stairDistance = 3;
						stairDistanceAdd = 1;
					} else if (objectID == 55) {
						stairs = 15;
						skillX = objectX;
						skillY = objectY;
						stairDistance = 3;
						stairDistanceAdd = 1;
					} else if (objectID == 57) {
						stairs = 15;
						skillX = objectX;
						skillY = objectY;
						stairDistance = 3;
					} else if (objectID == 1755 || objectID == 5946 || objectID == 1757) {
						stairs = 4;
						skillX = objectX;
						skillY = objectY;
						stairDistance = 1;
					} else if (objectID == 1764) {
						stairs = 12;
						skillX = objectX;
						skillY = objectY;
						stairDistance = 1;
					} else if (objectID == 2148) {
						stairs = 8;
						skillX = objectX;
						skillY = objectY;
						stairDistance = 1;
					} else if (objectID == 3608) {
						stairs = 13;
						skillX = objectX;
						skillY = objectY;
						stairDistance = 1;
					} else if (objectID == 2408) {
						stairs = 16;
						skillX = objectX;
						skillY = objectY;
						stairDistance = 1;
					} else if (objectID == 5055) {
						stairs = 18;
						skillX = objectX;
						skillY = objectY;
						stairDistance = 1;
					} else if (objectID == 5131) {
						stairs = 20;
						skillX = objectX;
						skillY = objectY;
						stairDistance = 1;
					} else if (objectID == 9359) {
						stairs = 24;
						skillX = objectX;
						skillY = objectY;
						stairDistance = 1;
						stairDistance = 1;
					} else if (objectID == 2492) { /*Essence Mine Portals*/
						stairs = 25;
						skillX = objectX;
						skillY = objectY;
						stairDistance = 1;
					} else if (objectID == 2406) { /*Lost City Door*/
						if (playerEquipment[playerWeapon] == 772) { //Dramen Staff
							stairs = 27;
							skillX = objectX;
							skillY = objectY;
							stairDistance = 1;
						} else {
							//Open Door
						}
					}
//go downstairs
					if (objectID == 1746 || objectID == 1749) {
						stairs = 2;
						skillX = objectX;
						skillY = objectY;
						stairDistance = 1;
					} else if (objectID == 1740) {
						stairs = 2;
						skillX = objectX;
						skillY = objectY;
						stairDistance = 1;
					} else if (objectID == 1723) {
						stairs = 22;
						skillX = objectX;
						skillY = objectY;
						stairDistance = 2;
						stairDistanceAdd = 2;
					} else if (objectID == 1733) {
						stairs = 9;
						skillX = objectX;
						skillY = objectY;
						stairDistance = 3;
						stairDistanceAdd = -1;
					} else if (objectID == 54) {
						stairs = 14;
						skillX = objectX;
						skillY = objectY;
						stairDistance = 3;
						stairDistanceAdd = 1;
					} else if (objectID == 56) {
						stairs = 14;
						skillX = objectX;
						skillY = objectY;
						stairDistance = 3;
					} else if (objectID == 1568 || objectID == 5947 || objectID == 6434 || objectID == 1759 || objectID == 1754 || objectID == 1570) {
						stairs = 3;
						skillX = objectX;
						skillY = objectY;
						stairDistance = 1;
					} else if (objectID == 2113) { //Mining guild stairs
						if (playerLevel[playerMining] >= 60) {
							stairs = 3;
							skillX = objectX;
							skillY = objectY;
							stairDistance = 1;
						} else {
							sendMessage("You need 60 mining to enter the mining guild.");
						}
					} else if (objectID == 492) {
						stairs = 11;
						skillX = objectX;
						skillY = objectY;
						stairDistance = 2;
					} else if (objectID == 2147) {
						stairs = 7;
						skillX = objectX;
						skillY = objectY;
						stairDistance = 1;
					} else if (objectID == 5054) {
						stairs = 17;
						skillX = objectX;
						skillY = objectY;
						stairDistance = 1;
					} else if (objectID == 5130) {
						stairs = 19;
						skillX = objectX;
						skillY = objectY;
						stairDistance = 1;
					} else if (objectID == 9358) {
						stairs = 23;
						skillX = objectX;
						skillY = objectY;
						stairDistance = 1;
					} else if (objectID == 5488) {
						stairs = 28;
						skillX = objectX;
						skillY = objectY;
						stairDistance = 1;
					}

					if (skillX > -1 && skillY > -1) {
						IsUsingSkill = true;
					}
				}
if ((objectID == 1568) || (objectID == 1569) || (objectID == 1570) || (objectID == 1571) ||
					(objectID == 1759) || (objectID == 1762) || (objectID == 1763) || (objectID == 1764) || (objectID == 2113) ||
					(objectID == 3771))
				{
					teleportToX = absX;
					teleportToY = (absY + 6400);
				}
				if (objectID == 2514)
                {
                ReplaceObject(objectX,objectY,2559, -2);
                }
				if (objectID == 2559)
                {
                ReplaceObject(objectX,objectY,1531, -2);
                }


                          /* xxx
                          Start Portal Codes
                          xxx */

                          if (objectID == 2465) // Air Temple Portal
                          {
                          teleportToX = 2985;
                          teleportToY = 3290;
                          }
                          if (objectID == 2492) // Ess
                          {
                          teleportToX = 3254;
                          teleportToY = 3402;
                          }
                          if (objectID == 2472)   // Law Temple Portal
                {
                    teleportToX = 2858;
                    teleportToY = 3379;
                }

if (objectID == 5960)   // Mage bank lever to wildy
                    if (absY == 4712)
                {
                if(teleblock == false)                
                {
                    teleportToX = 3090;
                    teleportToY = 3956;
                }
else if(teleblock == true)
{
sendMessage("A magical force stops you from teleporting.");
}
}
if (objectID == 5959)   // Wildy lever to mage bank
                    if (absY == 3956)
                {
                if(teleblock == false)
                {
                    teleportToX = 2539;
                    teleportToY = 4712;
                }
else if(teleblock == true)
{
sendMessage("A magical force stops you from teleporting.");
}
}
if (objectID == 2878)   // Mage Arena Bank Pool
                {
                    teleportToX = 2509;
                    teleportToY = 4689;
                }
if (objectID == 2879)   // Mage Arena Staff Place Pool
                {
                    teleportToX = 2542;
                    teleportToY = 4718;
                }
if (objectID == 1596)   // Members gate
                {
                ReplaceObject(objectX,objectY,2630, 0);
                }
if ((objectID == 1596) && (objectX == 3008))
                {
                ReplaceObject(objectX,objectY,2630, -2);
                }
if (objectID == 1597)   // Members gate
                {
                ReplaceObject(objectX,objectY,2630, 0);
                }
if ((objectID == 1597) && (objectX == 3008))
                {
                ReplaceObject(objectX,objectY,2630, -2);
                }
if ((objectID == 2514))
                {
                ReplaceObject(objectX,objectY,objectID =+ 2, -2);
                }

if (objectID == 2557)   // Axe hut door
                {
                ReplaceObject(objectX,objectY,objectID =+ 2, 0);
                }
if ((objectID == 1765) && (objectX == 3017) && (objectY == 3849))
                {
                    teleportToX = 2773;
                    teleportToY = 9341;
                    sendMessage("You climb down the ladder to find a secret cave!");
                }
if ((objectID == 2903) && (objectX == 2773) && (objectY == 9342))
                {
                    teleportToX = 3016;
                    teleportToY = 3848;
                    sendMessage("You climb out of the cave back into the wilderness.");
                }
if ((objectID == 2904) && (objectX == 2772) && (objectY == 9342))
                {
                    teleportToX = 3016;
                    teleportToY = 3848;
                    sendMessage("You climb out of the cave back into the wilderness.");
                }
if ((objectID == 393) && (objectX == 2790) && (objectY == 9341))
                {
                    teleportToX = 2800;
                    teleportToY = 9341;
                    sendMessage("You search the bookcase a crawl through a hidden crack in the rock.");
                }
if ((objectID == 2918) && (objectX == 2799) && (objectY == 9341))
                {
                    teleportToX = 2790;
                    teleportToY = 9340;
                    sendMessage("You crawl through the crack in the rock.");
                }
if (objectID == 733)   // Mage bank webs
                {
                if (absX == 3092) {
                    teleportToX = 3093;
                    teleportToY = 3957;
                }
                if (absX == 3093) {
                    teleportToX = 3092;
                    teleportToY = 3957;
                }
                if (absX == 3094) {
                    teleportToX = 3095;
                    teleportToY = 3957;
                }
                if (absX == 3095) {
                    teleportToX = 3094;
                    teleportToY = 3957;
                }
                
		if (absY == 3950) {
                    teleportToX = 3158;
                    teleportToY = 3952;
		}
		if (absY == 3952) {
		    teleportToX = 3158;
                    teleportToY = 3950;
		}
		if (absY == 3119) {
                    teleportToX = absX;
                    teleportToY = 3118;
		}
		if (absY == 3118) {
		    teleportToX = absX;
                    teleportToY = 3119;
		}
		if (absY == 3957) {
                    teleportToX = absX;
                    teleportToY = 3959;
		}
		if (absY == 3959) {
		    teleportToX = objectX;
                    teleportToY = 3957;
		}
                }


if (objectID == 9707)
{
teleportToX = 3105;
teleportToY = 3956;
sendMessage("You teleport inside...");
}

if (objectID == 9706)
{
teleportToX = 3105;
teleportToY = 3951;
sendMessage("You teleport inside...");
}

if (objectID == 2321 && absY >= 9487 && absY <= 9495)   // Monkey Bars
                {   
		if (absY == 9494 && playerLevel[16] >= 80) {
                    teleportToX = absX;
                    teleportToY = 9488;
                    sendMessage("You swing across the monkey bars.");
                    addSkillXP((40*playerLevel[16]), 16);
		}
                else
                {
                sendMessage("You need an agility level of 80 to climb the monkey bars."); 
                sendMessage("You need to be wearing goldsmith gaunlets to climb the monkey bars."); 
                }   
		if (absY == 9488 && playerLevel[16] >= 80 && playerEquipment[playerHands] == 776) {
                    teleportToX = absX;
                    teleportToY = 9494;
                    sendMessage("You swing accross the monkey bars.");
		}
                else 
                {
                sendMessage("You need an agility level of 80 to climb the monkey bars."); 
                sendMessage("You need to be wearing goldsmith gaunlets to climb the monkey bars."); 
                }  
                }

if (objectID == 2303)   // Balancing Ledge
                {   
		if (absY == 9520 && playerLevel[16] >= 80) {
                    teleportToX = absX;
                    teleportToY = 9512;
                    sendMessage("You climb accross the ledge.");
                    addSkillXP((40*playerLevel[16]), 16);
		}
                else if (absY == 9520 && playerLevel[16] <= 80)
                {
                sendMessage("You need an agility level of 80 to climb this ledge."); 
                }   
		if (absY == 9512 && playerLevel[16] >= 80) {
                    teleportToX = absX;
                    teleportToY = 9520;
                    sendMessage("You climb accross the ledge.");
		}
                else if (absY == 9512 && playerLevel[16] <= 80)
                {
                sendMessage("You need an agility level of 80 to climb this ledge."); 
                }  
                }

if (objectID == 2558)   // Pirate Hut Doors
                {
                ReplaceObject(objectX,objectY,objectID =+ 2, 0);
                }


if (objectID == 1558)   // Edgeville dungeon gate
                {
                ReplaceObject(objectX,objectY,objectID =+ 2, 0);
                }
if (objectID == 1557)   // Edgeville dungeon gate
                {
                ReplaceObject(objectX,objectY,objectID =+ 2, 0);
                }


//------------------ bronze doors --------------------
                if (objectID == 4106)
                {
                ReplaceObject(objectX,objectY,objectID =+ 2, 0);
                }
//-------------------- steel doors --------------------
           	if (objectID == 4107)
                {
                ReplaceObject(objectX,objectY,objectID =+ 2, 0);
                }
//--------------------- silver doors ---------------------
              	if (objectID == 4109)
                {
                ReplaceObject(objectX,objectY,objectID =+ 2, 0);
                }
//--------------------- black doors ---------------------
                if (objectID == 4108)
                {
                ReplaceObject(objectX,objectY,objectID =+ 2, 0);
                }

if(objectID == 1533)
{
if(objectX == 3183 && objectY == 3434)
{
ReplaceObject(objectX,objectY,1531, -1);
}
else
{
ReplaceObject(objectX,objectY,objectID =+ 2, 0);
}
}

if (objectID == 1512)   // Door to secret command/item room
                {
                ReplaceObject(objectX,objectY,objectID =+ 2, 0);
                }

if (objectID == 1519)   // Left door to varrock castle
                {
                ReplaceObject(objectX,objectY,objectID =+ 2, 0);
                }
if (objectID == 1516)   // Left door to varrock castle
                {
                ReplaceObject(objectX,objectY,objectID =+ 2, 0);
                }
if ((objectID == 1530)  || (objectID == 2112)  || (objectID == 1512)    || /*(objectID == 1533) ||*/ (objectID == 1519))
                {
                ReplaceObject(objectX,objectY,objectID =+ 2, -2);
                }
if ((objectID == 1530) && (objectX == 2716) && (objectY == 3472))
                {
                ReplaceObject(objectX,objectY,objectID =+ 2, -1);
                }
if (objectID == 1728 && objectY == 9497)
{
sendMessage("You climb down the stairs, and stand on a trap!");
			teleportToX = 2636;
			teleportToY = 9517;
				hitDiff = 10 + misc.random(5);;
                                actionTimer = 30;
                                currentHealth -= hitDiff;
				updateRequired = true;
				hitUpdateRequired = true;
                                inCombat();
                                }
				if(currentHealth < 1) 
				{
				currentHealth = 0;
				}
                                if(currentHealth == 0) 
		{
for(int r=0; r<playerEquipment.length; r++) {
	try {
		int item = playerEquipment[r];
		if((item > 0) && (item < 10000)) {
			remove(item, r);
		}
	} catch(Exception e) { sendMessage("ERROR: Removing Equipment"); }
}
for(int rr=0; rr<playerItems.length; rr++) {
	try {
		if(playerItems[rr] > 0 && playerItems[rr] < 10000) {
			deleteItem(playerItems[rr]-1, getItemSlot(playerItems[rr]-1), 1); 
		}
	} catch(Exception e) {  }
}
removeAllItems();
			teleportToX = 3254;
			teleportToY = 3420;
			//pEmote = 15;
			//pWalk = 13;
			sendMessage("Oh dear you are dead!");
			setSkillLevel(3, 99, playerLevel[3]);
			currentHealth = 99;
			hitDiff = 0;	
			updateRequired = true; appearanceUpdateRequired = true;
		}

if (objectID == 6657)   // Juna the snake
                {
sendMessage("Juna: Touch the weeping walls to gain the power of ghosts...");
                }

if (objectID == 2873)   // Saradomin statue
				{
					actionAmount++;
				

					if (actionTimer == 0)
					{
                          if((playerLevel[16]) <= 98)
                          {
                               sendMessage("You need to be level 99 agility to pray to Saradomin.");
                          }

                          else if((playerLevel[16]) >= 98)
                          {
                               sendMessage("You bow down to Saradomin");
                               actionName = "cape1";
                               actionTimer = 30;
                          }       
                          else if(actionTimer >= 1) 
                          {
                               sendMessage("You have to wait before you can pray again.");
                          }                                          
				    }
			    }
	      if (actionName.equalsIgnoreCase("cape1"))
		{
			sendMessage("You recieve the cape of Saradomin.");
			addSkillXP((5000*playerLevel[16]), 16);
			addItem(2412, 1);
			actionName = "";
		}

if (objectID == 2875)   // Guthix statue
				{
					actionAmount++;
				

					if (actionTimer == 0)
					{
                          if((playerLevel[15]) <= 98)
                          {
                               sendMessage("You need to be level 99 herblore to pray to Guthix.");
                          }

                          else if((playerLevel[15]) >= 98)
                          {
                               sendMessage("You bow down to Guthix...");
                               actionName = "cape2";
                               actionTimer = 30;
                          }       
                          else if(actionTimer >= 1) 
                          {
                               sendMessage("You have to wait before you can pray again.");
                          }                                          
				    }
			    }
	      if (actionName.equalsIgnoreCase("cape2"))
		{
			sendMessage("You recieve the cape of Guthix.");
			addSkillXP((5000*playerLevel[15]), 15);
			addItem(2413, 1);
			actionName = "";
		}

if (objectID == 2874)   // Zamorak statue
				{
					actionAmount++;
				

					if (actionTimer == 0)
					{
                          if((playerLevel[20]) <= 98)
                          {
                               sendMessage("You need to be level 99 runecrafting to pray to Zamorak.");
                          }

                          else if((playerLevel[20]) >= 98)
                          {
                               sendMessage("You bow down to Zamorak...");
                               actionName = "cape3";
                               actionTimer = 30;
                          }       
                          else if(actionTimer >= 1) 
                          {
                               sendMessage("You have to wait before you can pray again.");
                          }                                          
				    }
			    }
	      if (actionName.equalsIgnoreCase("cape3"))
		{
			sendMessage("You recieve the cape of Zamorak.");
			addSkillXP((5000*playerLevel[20]), 20);
			addItem(2414, 1);
			actionName = "";
		}

if (objectID == 6660)   // Weeping walls
				{
					actionAmount++;
				

					if (actionTimer == 0)
					{
                          if((playerLevel[5]) <= 98)
                          {
                               sendMessage("You need to be level 99 prayer to touch the wall.");
                          }

                          else if((playerLevel[5]) >= 98)
                          {
                               sendMessage("You touch the weeping wall...");
                               actionName = "ghost";
                               actionTimer = 30;
                          }       
                          else if(actionTimer >= 1) 
                          {
                               sendMessage("You have to wait before you can touch the wall again.");
                          }                                          
				    }
			    }
	          if (actionName.equalsIgnoreCase("ghost"))
			{
				pEmote = 15;
				pWalk = 13;
				updateRequired = true; appearanceUpdateRequired = true;
                                sendMessage("You're now a ghost, type ::normal to change back");
                        }
if (objectID == 7133)   // Nature rift
				{
					actionAmount++;
				

					if (actionTimer == 0)
					{
                          if((playerLevel[20]) <= 59)
                          {
                               sendMessage("You need to be level 60 runecrafting to craft nature runes.");
                          }

                          else if((playerLevel[20]) >= 59)
                          {
                               actionName = "nats";
                               actionTimer = 30;
                          }       
                          else if(actionTimer >= 1) 
                          {
                               sendMessage("You have to wait before you can make more runes.");
                          }                                          
				    }
			    }
	      if (actionName.equalsIgnoreCase("nats"))
		{
			sendMessage("You craft some runes.");
			addSkillXP((100*playerLevel[20]), 20);
			addItem(561, 5);
			actionName = "";
		}

if (objectID == 7132)   // Cosmic rift
				{
					actionAmount++;
				

					if (actionTimer == 0)
					{
                          if((playerLevel[20]) <= 39)
                          {
                               sendMessage("You need to be level 40 runecrafting to craft cosmic runes.");
                          }

                          else if((playerLevel[20]) >= 39)
                          {
                               actionName = "cosmics";
                               actionTimer = 30;
                          }       
                          else if(actionTimer >= 1) 
                          {
                               sendMessage("You have to wait before you can make more runes.");
                          }                                          
				    }
			    }
	      if (actionName.equalsIgnoreCase("cosmics"))
		{
			sendMessage("You craft some runes.");
			addSkillXP((60*playerLevel[20]), 20);
			addItem(564, 5);
			actionName = "";
		}

if (objectID == 7141)   // Blood rift
				{
					actionAmount++;
				

					if (actionTimer == 0)
					{
                          if((playerLevel[20]) <= 89)
                          {
                               sendMessage("You need to be level 90 runecrafting to craft blood runes.");
                          }

                          else if((playerLevel[20]) >= 89)
                          {
                               actionName = "bloodz";
                               actionTimer = 30;
                          }       
                          else if(actionTimer >= 1) 
                          {
                               sendMessage("You have to wait before you can make more runes.");
                          }                                          
				    }
			    }
	      if (actionName.equalsIgnoreCase("bloodz"))
		{
			sendMessage("You craft some runes.");
			addSkillXP((200*playerLevel[20]), 20);
			addItem(4278, 5);
			actionName = "";
		}

if (objectID == 7129)   // Fire rift
				{
					actionAmount++;
				

					if (actionTimer == 0)
					{
                          if((playerLevel[20]) <= 29)
                          {
                               sendMessage("You need to be level 30 runecrafting to craft fire runes.");
                          }

                          else if((playerLevel[20]) >= 29)
                          {
                               actionName = "fires";
                               actionTimer = 30;
                          }       
                          else if(actionTimer >= 1) 
                          {
                               sendMessage("You have to wait before you can make more runes.");
                          }                                          
				    }
			    }
	      if (actionName.equalsIgnoreCase("fires"))
		{
			sendMessage("You craft some runes.");
			addSkillXP((20*playerLevel[20]), 20);
			addItem(554, 5);
			actionName = "";
		}

if (objectID == 7130)   // Earth rift
				{
					actionAmount++;
				

					if (actionTimer == 0)
					{
                          if((playerLevel[20]) <= 19)
                          {
                               sendMessage("You need to be level 90 runecrafting to craft earth runes.");
                          }

                          else if((playerLevel[20]) >= 19)
                          {
                               actionName = "earths";
                               actionTimer = 30;
                          }       
                          else if(actionTimer >= 1) 
                          {
                               sendMessage("You have to wait before you can make more runes.");
                          }                                          
				    }
			    }
	      if (actionName.equalsIgnoreCase("earths"))
		{
			sendMessage("You craft some runes.");
			addSkillXP((15*playerLevel[20]), 20);
			addItem(557, 5);
			actionName = "";
		}

if (objectID == 7137)   // Water rift
				{
					actionAmount++;
				

					if (actionTimer == 0)
					{
                          if((playerLevel[20]) <= 14)
                          {
                               sendMessage("You need to be level 15 runecrafting to craft water runes.");
                          }

                          else if((playerLevel[20]) >= 14)
                          {
                               actionName = "waters";
                               actionTimer = 30;
                          }       
                          else if(actionTimer >= 1) 
                          {
                               sendMessage("You have to wait before you can make more runes.");
                          }                                          
				    }
			    }
	      if (actionName.equalsIgnoreCase("waters"))
		{
			sendMessage("You craft some runes.");
			addSkillXP((15*playerLevel[20]), 20);
			addItem(555, 5);
			actionName = "";
		}

if (objectID == 7140)   // Mind rift
				{
					actionAmount++;
				

					if (actionTimer == 0)
					{
                          if((playerLevel[20]) <= 9)
                          {
                               sendMessage("You need to be level 10 runecrafting to craft mind runes.");
                          }

                          else if((playerLevel[20]) >= 9)
                          {
                               actionName = "earths";
                               actionTimer = 30;
                          }       
                          else if(actionTimer >= 1) 
                          {
                               sendMessage("You have to wait before you can make more runes.");
                          }                                          
				    }
			    }
	      if (actionName.equalsIgnoreCase("earths"))
		{
			sendMessage("You craft some runes.");
			addSkillXP((10*playerLevel[20]), 20);
			addItem(558, 5);
			actionName = "";
		}

if (objectID == 7139)   // Air rift
				{
					actionAmount++;
				

					if (actionTimer == 0)
					{
                          if((playerLevel[20]) <= 0)
                          {
                               sendMessage("You need to be level 1 runecrafting to craft air runes.");
                          }

                          else if((playerLevel[20]) >= 0)
                          {
                               actionName = "airs";
                               actionTimer = 30;
                          }       
                          else if(actionTimer >= 1) 
                          {
                               sendMessage("You have to wait before you can make more runes.");
                          }                                          
				    }
			    }
	      if (actionName.equalsIgnoreCase("airs"))
		{
			sendMessage("You craft some runes.");
			addSkillXP((5*playerLevel[20]), 20);
			addItem(556, 5);
			actionName = "";
		}

if (objectID == 7131)   // Body rift
				{
					actionAmount++;
				

					if (actionTimer == 0)
					{
                          if((playerLevel[20]) <= 4)
                          {
                               sendMessage("You need to be level 5 runecrafting to craft body runes.");
                          }

                          else if((playerLevel[20]) >= 4)
                          {
                               actionName = "bodys";
                               actionTimer = 30;
                          }       
                          else if(actionTimer >= 1) 
                          {
                               sendMessage("You have to wait before you can make more runes.");
                          }                                          
				    }
			    }
	      if (actionName.equalsIgnoreCase("bodys"))
		{
			sendMessage("You craft some runes.");
			addSkillXP((7*playerLevel[20]), 20);
			addItem(559, 5);
			actionName = "";
		}

if (objectID == 7138)   // Soul rift
				{
					actionAmount++;
				

					if (actionTimer == 0)
					{
                          if((playerLevel[20]) <= 109)
                          {
                               sendMessage("You need to be level 110 runecrafting to craft soul runes.");
                          }

                          else if((playerLevel[20]) >= 109)
                          {
                               actionName = "souls";
                               actionTimer = 30;
                          }       
                          else if(actionTimer >= 1) 
                          {
                               sendMessage("You have to wait before you can make more runes.");
                          }                                          
				    }
			    }
	      if (actionName.equalsIgnoreCase("souls"))
		{
			sendMessage("You craft some runes.");
			addSkillXP((350*playerLevel[20]), 20);
			addItem(566, 5);
			actionName = "";
		}

if (objectID == 7136)   // Death rift
				{
					actionAmount++;
				

					if (actionTimer == 0)
					{
                          if((playerLevel[20]) <= 74)
                          {
                               sendMessage("You need to be level 75 runecrafting to craft death runes.");
                          }

                          else if((playerLevel[20]) >= 74)
                          {
                               actionName = "deaths";
                               actionTimer = 30;
                          }       
                          else if(actionTimer >= 1) 
                          {
                               sendMessage("You have to wait before you can make more runes.");
                          }                                          
				    }
			    }
	      if (actionName.equalsIgnoreCase("deaths"))
		{
			sendMessage("You craft some runes.");
			addSkillXP((150*playerLevel[20]), 20);
			addItem(560, 5);
			actionName = "";
		}

if (objectID == 7135)   // Law rift
				{
					actionAmount++;
				

					if (actionTimer == 0)
					{
                          if((playerLevel[20]) <= 69)
                          {
                               sendMessage("You need to be level 70 runecrafting to craft law runes.");
                          }

                          else if((playerLevel[20]) >= 69)
                          {
                               actionName = "laws";
                               actionTimer = 30;
                          }       
                          else if(actionTimer >= 1) 
                          {
                               sendMessage("You have to wait before you can make more runes.");
                          }                                          
				    }
			    }
	      if (actionName.equalsIgnoreCase("laws"))
		{
			sendMessage("You craft some runes.");
			addSkillXP((175*playerLevel[20]), 20);
			addItem(563, 5);
			actionName = "";
		}

if (objectID == 7134)   // Chaos rift
				{
					actionAmount++;
				

					if (actionTimer == 0)
					{
                          if((playerLevel[20]) <= 49)
                          {
                               sendMessage("You need to be level 50 runecrafting to craft chaos runes.");
                          }

                          else if((playerLevel[20]) >= 49)
                          {
                               actionName = "chaos";
                               actionTimer = 30;
                          }       
                          else if(actionTimer >= 1) 
                          {
                               sendMessage("You have to wait before you can make more runes.");
                          }                                          
				    }
			    }
	      if (actionName.equalsIgnoreCase("chaos"))
		{
			sendMessage("You craft some runes.");
			addSkillXP((80*playerLevel[20]), 20);
			addItem(562, 5);
			actionName = "";
		}
if (objectID == 2156)   // Mage Arena Portal
{
sendMessage("This slayer object has been changed...");
sendMessage("Type ::slayermenu for details on new location.");
}
if (objectID == 5259)   // Ectofuntus Energy Barrier
				{
					actionAmount++;
				

					if (actionTimer == 0)
					{
                          if((playerLevel[5]) <= 84)
                          {
                               sendMessage("You need to be level 85 prayer to pray at the energy barrier.");
                          }

                          else if((playerLevel[5]) >= 84)
                          {
                               sendMessage("You begin to pray to the energy barrier...");
                               actionName = "ecto1";
                               actionTimer = 30;
                          }       
                          else if(actionTimer >= 1) 
                          {
                               sendMessage("You have to wait before you can pray to the energy barrier again.");
                          }                                          
				    }
			    }
		    if (actionName.equalsIgnoreCase("ecto1"))
		{
			sendMessage("You recieve some ourg bones");
			addSkillXP((20*playerLevel[5]), 5);
			addItem(4834, 1);
			actionName = "";
		}

if (objectID == 5282)   // Ectofuntus Energy Barrier
				{
					actionAmount++;
				

					if (actionTimer == 0)
					{
                          if((playerLevel[5]) <= 84)
                          {
                               sendMessage("You need to be level 85 prayer to pray at the ectofuntus.");
                          }

                          else if((playerLevel[5]) >= 84)
                          {
                               sendMessage("You begin to pray to the ectofuntus...");
                               actionName = "ecto2";
                               actionTimer = 30;
                          }       
                          else if(actionTimer >= 1) 
                          {
                               sendMessage("You have to wait before you can pray to the ectofuntus again.");
                          }                                          
				    }
			    }
		    if (actionName.equalsIgnoreCase("ecto2"))
		{
			sendMessage("You recieve some prayer exp.");
			addSkillXP((80*playerLevel[5]), 5);
			actionName = "";
		}

if (objectID == 2909)   // Legends quest firewall, 2799,9329
				{
					actionAmount++;
				

					if (actionTimer == 0)
					{
                          if((playerLevel[18]) <= 119)
                          {
                               sendMessage("You need to be level 120 slayer to attack the firewall.");
                          }

                          else if((playerLevel[18]) >= 119)
                          {
                               sendMessage("You attack the firewall...");
                               actionName = "119slayer";
                               actionTimer = 30;
                          }       
                          else if(actionTimer >= 1) 
                          {
                               sendMessage("You have to wait before you can attack the firewall again.");
                          }                                          
				    }
			    }
		    if (actionName.equalsIgnoreCase("119slayer"))
		{
			sendMessage("You slay the firewall portal.");
			addSkillXP((5000*playerLevel[18]), 18);
			addItem(Item.randomSlayer120item(), 1);
			actionName = "";
		}

if (objectID == 2908)   // Legends quest firewall, 2799,9329
				{
					actionAmount++;
				

					if (actionTimer == 0)
					{
                          if((playerLevel[18]) <= 119)
                          {
                               sendMessage("You need to be level 120 slayer to attack the firewall.");
                          }

                          else if((playerLevel[18]) >= 119)
                          {
                               sendMessage("You attack the firewall...");
                               actionName = "119slayer";
                               actionTimer = 30;
                          }       
                          else if(actionTimer >= 1) 
                          {
                               sendMessage("You have to wait before you can attack the firewall again.");
                          }                                          
				    }
			    }
		    if (actionName.equalsIgnoreCase("119slayer"))
		{
			sendMessage("You slay the firewall portal.");
			addSkillXP((5000*playerLevel[18]), 18);
			addItem(Item.randomSlayer120item(), 1);
			actionName = "";
		}


if (objectID == 2911)   // Legends quest bookcase, 2796,9338
				{
					actionAmount++;
				

					if (actionTimer == 0)
					{
                          if((playerLevel[18]) <= 98)
                          {
                               sendMessage("You need to be level 99 slayer to attack the bookcase.");
                          }

                          else if((playerLevel[18]) >= 98)
                          {
                               sendMessage("You attack the bookcase...");
                               actionName = "99slayer";
                               actionTimer = 30;
                          }       
                          else if(actionTimer >= 1) 
                          {
                               sendMessage("You have to wait before you can attack the bookcase again.");
                          }                                          
				    }
			    }
		    if (actionName.equalsIgnoreCase("99slayer"))
		{
			sendMessage("You slay the bookcase!");
			addSkillXP((500*playerLevel[18]), 18);
			addItem(Item.randomSlayer99item(), 1);
			actionName = "";
		}

if (objectID == 2912)   // Legends quest ancient door, 2809,9332
				{
					actionAmount++;
				

					if (actionTimer == 0)
					{
                          if((playerLevel[18]) <= 134)
                          {
                               sendMessage("You need to be level 135 slayer to attack the ancient door.");
                          }

                          else if((playerLevel[18]) >= 134)
                          {
                               sendMessage("You attack the ancient door...");
                               actionName = "135slayer";
                               actionTimer = 30;
                          }       
                          else if(actionTimer >= 1) 
                          {
                               sendMessage("You have to wait before you can attack the ancient door again.");
                          }                                          
				    }
			    }
		    if (actionName.equalsIgnoreCase("135slayer"))
		{
			sendMessage("You slay ancient door!");
			addSkillXP((10000*playerLevel[18]), 18);
			addItem(Item.randomSlayer135item(), 1);
			actionName = "";
		}

if (objectID == 2913)   // Legends quest ancient door, 2809,9332
				{
					actionAmount++;
				

					if (actionTimer == 0)
					{
                          if((playerLevel[18]) <= 134)
                          {
                               sendMessage("You need to be level 135 slayer to attack the ancient door.");
                          }

                          else if((playerLevel[18]) >= 134)
                          {
                               sendMessage("You attack the ancient door...");
                               actionName = "135slayer";
                               actionTimer = 30;
                          }       
                          else if(actionTimer >= 1) 
                          {
                               sendMessage("You have to wait before you can attack the ancient door again.");
                          }                                          
				    }
			    }
		    if (actionName.equalsIgnoreCase("135slayer"))
		{
			sendMessage("You slay the ancient door!");
			addSkillXP((10000*playerLevel[18]), 18);
			addItem(Item.randomSlayer135item(), 1);
			actionName = "";
		}

if (objectID == 2640)   // Pray guild altar
				{
					actionAmount++;
				

					if (actionTimer == 0)
					{
                          if((playerLevel[00]) <= 119)
                          {
                               sendMessage("You need to be level 120 attack to pray at this altar.");
                          }

                          else if((playerLevel[00]) >= 119)
                          {
                               sendMessage("You pray at the altar...");
                               actionName = "pray";
                               actionTimer = 30;
                          }       
                          else if(actionTimer >= 1) 
                          {
                               sendMessage("You have to wait before you can pray at the altar again.");
                          }                                          
				    }
			    }
		    if (actionName.equalsIgnoreCase("pray"))
		{
			sendMessage("You recieve the cape of legends!");
			addSkillXP((2000*playerLevel[5]), 5);
			addItem(1052, 1);
			actionName = "";
		}

if (objectID == 2564)  // Ardy spice stall
				{
					actionAmount++;
				

					if (actionTimer == 0)
					{
                          if((playerLevel[17]) <= 79)
                          {
                               sendMessage("You need to be level 80 theiving to steal from the spice stall.");
                          }

                          else if((playerLevel[18]) >= 79)
                          {
                               sendMessage("You attempt to steal from the spice stall");
                               actionName = "spice";
                               actionTimer = 30;
                          }       
                          else if(actionTimer >= 1) 
                          {
                               sendMessage("You have to wait before you can steal from the spice stall again.");
                          }                                          
				    }
			    }
		    if (actionName.equalsIgnoreCase("spice"))
		{
			sendMessage("You steal a cape.");
			addSkillXP((200*playerLevel[17]), 17);
			addItem(1050, 1);
			actionName = "";
		}

if (objectID == 663)  // Ardy spice stall
				{
					actionAmount++;
				

					if (actionTimer == 0)
					{
                          if((playerLevel[17]) <= 79)
                          {
                               sendMessage("You need to be level 80 theiving to steal from the spice stall.");
                          }

                          else if((playerLevel[18]) >= 79)
                          {
                               sendMessage("You attempt to steal from the spice stall");
                               actionName = "spice";
                               actionTimer = 30;
                          }       
                          else if(actionTimer >= 1) 
                          {
                               sendMessage("You have to wait before you can steal from the spice stall again.");
                          }                                          
				    }
			    }
		    if (actionName.equalsIgnoreCase("spice"))
		{
			sendMessage("This stall has been taken out of the game...");
			addSkillXP((200*playerLevel[17]), 17);
			addItem(0000, 1);
			actionName = "";
		}

if (objectID == 6572)  // Ardy spice stall
				{
					actionAmount++;
				

					if (actionTimer == 0)
					{
                          if((playerLevel[17]) <= 79)
                          {
                               sendMessage("You need to be level 80 theiving to steal from the spice stall.");
                          }

                          else if((playerLevel[18]) >= 79)
                          {
                               sendMessage("You attempt to steal from the spice stall");
                               actionName = "spice";
                               actionTimer = 30;
                          }       
                          else if(actionTimer >= 1) 
                          {
                               sendMessage("You have to wait before you can steal from the spice stall again.");
                          }                                          
				    }
			    }
		    if (actionName.equalsIgnoreCase("spice"))
		{
			sendMessage("This stall has been taken out of the game...");
			addSkillXP((200*playerLevel[17]), 17);
			addItem(0000, 1);
			actionName = "";
		}

if (objectID == 4388)   // Castle wars zammy portal
				{
					actionAmount++;
				

					if (actionTimer == 0)
					{
                          if((playerLevel[6]) <= 98)
                          {
                               sendMessage("You need to be level 99 magic to reach inside.");
                          }

                          else if((playerLevel[6]) >= 98)
                          {
                               sendMessage("You put your hand into the portal...");
                               actionName = "zammyportal";
                               actionTimer = 3000;
                          }       
                          else if(actionTimer >= 1) 
                          {
                               sendMessage("You have to wait before you can check the portal again.");
                          }                                          
				    }
			    }
		    if (actionName.equalsIgnoreCase("zammyportal"))
		{
			sendMessage("You find a staff of zamorak!");
			addSkillXP((1000*playerLevel[6]), 6);
			addItem(2417, 1);
			actionName = "";
		}

if (objectID == 4408)   // Castle wars guthix portal
				{
					actionAmount++;
				

					if (actionTimer == 0)
					{
                          if((playerLevel[6]) <= 98)
                          {
                               sendMessage("You need to be level 99 magic to reach inside.");
                          }

                          else if((playerLevel[6]) >= 98)
                          {
                               sendMessage("You put your hand into the portal...");
                               actionName = "guthixportal";
                               actionTimer = 3000;
                          }       
                          else if(actionTimer >= 1) 
                          {
                               sendMessage("You have to wait before you can check the portal again.");
                          }                                          
				    }
			    }
		    if (actionName.equalsIgnoreCase("guthixportal"))
		{
			sendMessage("You find a staff of guthix!");
			addSkillXP((1000*playerLevel[6]), 6);
			addItem(2416, 1);
			actionName = "";
		}

if (objectID == 4387)   // Castle wars sara portal
				{
					actionAmount++;
				

					if (actionTimer == 0)
					{
                          if((playerLevel[6]) <= 98)
                          {
                               sendMessage("You need to be level 99 magic to reach inside.");
                          }

                          else if((playerLevel[6]) >= 98)
                          {
                               sendMessage("You put your hand into the portal...");
                               actionName = "saraportal";
                               actionTimer = 3000;
                          }       
                          else if(actionTimer >= 1) 
                          {
                               sendMessage("You have to wait before you can check the portal again.");
                          }                                          
				    }
			    }
		    if (actionName.equalsIgnoreCase("saraportal"))
		{
			sendMessage("You find a staff of saradomin!");
			addSkillXP((1000*playerLevel[6]), 6);
			addItem(2415, 1);
			actionName = "";
		}

if (objectID == 381)   // Bookcase, got id from varrock library, where the secret command/item room is 
                {

		}

if (objectID == 1987)   // Raft at waterfall, coords = 2509,3493
                {

		}

if (objectID == 411)   // Chaos altar in wildy, coords = 3239,3610
                {

		}

if (objectID == 1282)   // Largest dead tree, coords = 3203,3605
                {

		}

if (objectID == 2145)   // Restless ghost coffin, coords = 3249,3192
                {

		}

if (objectID == 4469)   // Castle wars blue team energy barrier, coords = 2426,3081
                {
                if (absX == 2422) {
		    teleportToX = 2423;
                    teleportToY = 3076;
		}
                if (absX == 2423) {
		    teleportToX = 2422;
                    teleportToY = 3076;
		}
                if (absY == 3081) {
                  sendMessage("Future update...");
		}
                 if (absY == 3080) {
                  sendMessage("Future update...");
		}
                }

if (objectID == 4417)   // Castle wars stairs team energy barrier, coords = 2419,3078
                {
                if (absX == 2419) {
		    teleportToX = 2420;
                    teleportToY = 3080;
                    heightLevel = 1;
		}
                }


if (objectID == 4483)   // Castle wars bank chest coords = 2444,3083
                {
                if (absX == 2443) {
		    teleportToX = 2425;
                    teleportToY = 3078;
                    sendMessage("Welcome to castle wars! Click take from table to steal stuff :)");
		}
                }

if (objectID == 4460)   // Castle wars main place, rock table, coords = 2423,3075
				{
					actionAmount++;
				

					if (actionTimer == 0)
					{
                          if((playerLevel[17]) <= 49)
                          {
                               sendMessage("You need to be level 50 theiving to steal from this table.");
                          }

                          else if((playerLevel[18]) >= 49)
                          {
                               sendMessage("You attempt to steal from the table...");
                               actionName = "50theiftable";
                               actionTimer = 30;
                          }       
                          else if(actionTimer >= 1) 
                          {
                               sendMessage("You have to wait before you can steal from the table again.");
                          }                                          
				    }
			    }
		    if (actionName.equalsIgnoreCase("50theiftable"))
		{
			sendMessage("You steal from the table!");
			addSkillXP((50*playerLevel[17]), 17);
			addItem(Item.randomTheif50tableitem(), 1);
			actionName = "";
		}

if (objectID == 4464)   // Castle wars main place, axe table, coords = 2424,3074
				{
					actionAmount++;
				

					if (actionTimer == 0)
					{
                          if((playerLevel[17]) <= 59)
                          {
                               sendMessage("You need to be level 60 theiving to steal from this table.");
                          }

                          else if((playerLevel[17]) >= 59)
                          {
                               sendMessage("You attempt to steal from the table...");
                               actionName = "60theiftable";
                               actionTimer = 30;
                          }       
                          else if(actionTimer >= 1) 
                          {
                               sendMessage("You have to wait before you can steal from the table again.");
                          }                                          
				    }
			    }
		    if (actionName.equalsIgnoreCase("60theiftable"))
		{
			sendMessage("You steal from the table!");
			addSkillXP((65*playerLevel[17]), 17);
			addItem(Item.randomTheif60tableitem(), 1);
			actionName = "";
		}

if (objectID == 4463)   // Castle wars main place, potion table, coords = 2425,3075
				{
					actionAmount++;
				

					if (actionTimer == 0)
					{
                          if((playerLevel[17]) <= 69)
                          {
                               sendMessage("You need to be level 70 theiving to steal from this table.");
                          }

                          else if((playerLevel[17]) >= 69)
                          {
                               sendMessage("You attempt to steal from the table...");
                               actionName = "70theiftable";
                               actionTimer = 30;
                          }       
                          else if(actionTimer >= 1) 
                          {
                               sendMessage("You have to wait before you can steal from the table again.");
                          }                                          
				    }
			    }
		    if (actionName.equalsIgnoreCase("70theiftable"))
		{
			sendMessage("You steal from the table!");
			addSkillXP((95*playerLevel[17]), 17);
			addItem(Item.randomTheif70tableitem(), 1);
			actionName = "";
		}

if (objectID == 4462)   // Castle wars main place, rope table, coords = 2425,3074
				{
					actionAmount++;
				

					if (actionTimer == 0)
					{
                          if((playerLevel[17]) <= 77)
                          {
                               sendMessage("You need to be level 78 theiving to steal from this table.");
                          }

                          else if((playerLevel[17]) >= 77)
                          {
                               sendMessage("You attempt to steal from the table...");
                               actionName = "78theiftable";
                               actionTimer = 30;
                          }       
                          else if(actionTimer >= 1) 
                          {
                               sendMessage("You have to wait before you steal from the table again.");
                          }                                          
				    }
			    }
		    if (actionName.equalsIgnoreCase("78theiftable"))
		{
			sendMessage("You steal from the table!");
			addSkillXP((105*playerLevel[17]), 17);
			addItem(Item.randomTheif70tableitem(), 1);
			actionName = "";
		}

if (objectID == 4461)   // Castle wars main place, barricades table, coords = 2428,3074
				{
					actionAmount++;
				

					if (actionTimer == 0)
					{
                          if((playerLevel[17]) <= 92)
                          {
                               sendMessage("You need to be level 93 theiving to steal from this table.");
                          }

                          else if((playerLevel[17]) >= 92)
                          {
                               sendMessage("You attempt to steal from the table...");
                               actionName = "93theiftable";
                               actionTimer = 30;
                          }       
                          else if(actionTimer >= 1) 
                          {
                               sendMessage("You have to wait before you steal from the table again.");
                          }                                          
				    }
			    }
		    if (actionName.equalsIgnoreCase("93theiftable"))
		{
			sendMessage("You steal from the table!");
			addSkillXP((300*playerLevel[17]), 17);
			addItem(Item.randomTheif93tableitem(), 1);
			actionName = "";
		}

if (objectID == 4459)   // Castle wars main place, limestone table, coords = 2428,3076
				{
					actionAmount++;
				

					if (actionTimer == 0)
					{
                          if((playerLevel[17]) <= 84)
                          {
                               sendMessage("You need to be level 85 theiving to steal from this table.");
                          }

                          else if((playerLevel[17]) >= 84)
                          {
                               sendMessage("You attempt to steal from the table...");
                               actionName = "85theiftable";
                               actionTimer = 30;
                          }       
                          else if(actionTimer >= 1) 
                          {
                               sendMessage("You have to wait before you steal from the table again.");
                          }                                          
				    }
			    }
		    if (actionName.equalsIgnoreCase("85theiftable"))
		{
			sendMessage("You steal from the table!");
			addSkillXP((200*playerLevel[17]), 17);
			addItem(Item.randomTheif85tableitem(), 1);
			actionName = "";
		}

if (objectID == 358)   // Crate near mage bank, before the 2 webz
{
sendMessage("This slayer object has been changed...");
sendMessage("Type ::slayermenu for details on new location.");
}
/*if (objectID == 2309)   // Wildy course gate 
{
if(objectX == 2998 && objectY == 3917 && absY <= objectY)
{
teleportToX = 2998;
teleportToY = 3918;
}
else if(objectX == 2998 && objectY == 3917 && absY >= objectY)
{
teleportToX = 2998;
teleportToY = 3916;
}
}*/
if(objectID == 2309 || objectID == 2307 || objectID == 2308) // Wildy course gates
{
ReplaceObject(objectX,objectY,objectID =+ 2, -1);
}
if (objectID == 1530)   // Door Between Lever & Ardougne
                {
		if (absX == 2564) {
                    teleportToX = 2563;
                    teleportToY = 3310;
		}
		if (absX == 2563) {
		    teleportToX = 2564;
                    teleportToY = 3310;
		}
                if (absX == 3246) {
		    teleportToX = 3247;
                    teleportToY = 3193;
		}
                if (absX == 3247) {
		    teleportToX = 3246;
                    teleportToY = 3193;
		}
                if (absX == 2575) {
		    teleportToX = 2576;
                    teleportToY = (absY);
		}
                if (absX == 2581) {
		    teleportToX = 2582;
                    teleportToY = (absY);
		}
		if (absX == 2576) {
                    teleportToX = 2575;
                    teleportToY = (absY);
		}
		if (absX == 2582) {
		    teleportToX = 2581;
                    teleportToY = (absY);
		}
                }
if (objectID == 8788)   // Gate in 2039,4637
                {
		if (absY == 4634) {
                    teleportToX = 2037;
                    teleportToY = 4634;
		}
		if (absY == 4633) {
		    teleportToX = 2037;
                    teleportToY = 4634;
		}
                }
if (objectID == 8787)   // Door in 2039,4637
                {
		if (absY == 4634) {
                    teleportToX = 2042;
                    teleportToY = 4634;
		}
		if (absY == 4633) {
		    teleportToX = 2042;
                    teleportToY = 4634;
		}
                }
if (objectID == 8789)   // Door in 2039,4637
                {
		if (absX == 2034) {
                    teleportToX = 2033;
                    teleportToY = 4636;
		}
		if (absX == 2033) {
		    teleportToX = 2034;
                    teleportToY = 4636;
		}
                }
                          /* xxx
                          End Of Portal Codes
                          xxx */
                                if (objectID == 1755)
				{
					teleportToX = absX;
					teleportToY = (absY - 6400);
				}
				if ((objectID == 1747) || (objectID == 1738) || (objectID ==4772) || (objectID == 5280) || (objectID == 6707) || (objectID == 6706) || (objectID == 6705) || (objectID == 6704) || (objectID == 6703) || (objectID == 6702) || (objectID == 1750) || (objectID == 4493))
				{
					heightLevel += 1;
					teleportToX = absX;
					teleportToY = (absY - 1);
                                println_debug("**********************************************************");
				println_debug(playerName+"- Might be trying to use ladder dupe bug!");
				println_debug(playerName+"- from coords: "+absX+","+absY);
                                println_debug("**********************************************************");
				}
					
				if ((objectID == 1746) || (objectID == 1740) || (objectID == 5281) || (objectID == 1749))
				{
					heightLevel -= 1;	
					teleportToX = absX;
					teleportToY = (absY - 1);
				}
						
				if (objectID == 2283) //a1
				{       actionAmount++;
					if (actionTimer == 0)
					{
						teleportToX = 3006;
						teleportToY = 3958;
						addSkillXP((8*playerLevel[16]), 16);
						actionName = "a1";
						actionTimer = 24;
						pEmote = 0x323;
						updateRequired = true; appearanceUpdateRequired = true;
					}							
				}	
				
				if (objectID == 2311) //a2
				{       actionAmount++;
					if (actionTimer == 0)
					{
						teleportToX = 2996;
						teleportToY = 3960;
						addSkillXP((6*playerLevel[16]), 16);
						actionName = "a2";
						actionTimer = 24;
						pEmote = 0x323;
						updateRequired = true; appearanceUpdateRequired = true;
					}						
				}	
						
				if (objectID == 2297) //a3
				{       actionAmount++;
					if (actionTimer == 0)
					{
						teleportToX = 2994;
						teleportToY = 3945;
						addSkillXP((14*playerLevel[16]), 16);
						actionName = "a3";
						actionTimer = 24;
						pEmote = 0x323;
						updateRequired = true; appearanceUpdateRequired = true;
					}	
				}
					
				if (objectID == 2328) //a4
				{       actionAmount++;
					if (actionTimer == 0)
					{
						teleportToX = 2996;
						teleportToY = 3932;
						addSkillXP((10*playerLevel[16]), 16);
						actionName = "a4";
						actionTimer = 24;
						pEmote = 0x323;
						updateRequired = true; appearanceUpdateRequired = true;
					}	
				}
					
				if (objectID == 2288) //a5
				{       actionAmount++;
					if (actionTimer == 0)
					{
						teleportToX = 3004;
						teleportToY = 3950;
						addSkillXP((8*playerLevel[16]), 16);
						actionName = "a5";
						actionTimer = 24;
						pEmote = 0x323;
						updateRequired = true; appearanceUpdateRequired = true;

					}		
				}
	
				if ((objectID == 8689))
				{       actionAmount++;
					if (actionTimer == 0)
					{
						sendMessage("You begin milking the cow");
						actionName = "moo";
						actionTimer = 20;
				
					}
			
				}	

				if ((objectID == 354))
				{
					if (actionTimer == 0)
                          if((playerLevel[17]) <= 98)
                          {
                               sendMessage("You need to be level 99 to steal from the secret crate.");
                          }

                          else if((playerLevel[17]) >= 98)
					{
						sendMessage("You steal an item.");
						actionName = "theving1";
						actionTimer = 40;
						pEmote = 0x340;
						updateRequired = true; appearanceUpdateRequired = true;
					}
			
				}	
				
				if ((objectID == 355))
				{       actionTimer++;
					if (actionTimer == 0)
					{
						sendMessage("You steal a gnome.");
						actionName = "theving2";
						actionTimer = 28;
						pEmote = 0x340;
						updateRequired = true; appearanceUpdateRequired = true;
					}
				}	
				
				if ((objectID == 361))
				{       actionTimer++;
					if (actionTimer == 0)
					{
						sendMessage("You steal a cup of tea.");
						actionName = "theving3";
						actionTimer = 28;
						pEmote = 0x340;
						updateRequired = true; appearanceUpdateRequired = true;	
					}
			
				}	
				
				
				if ((objectID == 361))
				{       actionTimer++;
					if (actionTimer == 0)
					{
						sendMessage("You steal a basket of eggs.");
						actionName = "theving4";
						actionTimer = 28;
						pEmote = 0x340;
						updateRequired = true; appearanceUpdateRequired = true;
					}			
				}	

				if ((objectID == 359))
				{       actionTimer++;
					if (actionTimer == 0)
					{
						sendMessage("You Steal a Basket of eggs");
						actionName = "theving5";
						actionTimer = 28;
						pEmote = 0x340;
						updateRequired = true; appearanceUpdateRequired = true;
					}
				}	

				if ((objectID == 2513))
				{       actionAmount++;
					if (actionTimer == 0)
					{
						sendMessage("You shoot at the target.");
						actionName = "range1";
						actionTimer = 30;
						pEmote = 0x426;
						updateRequired = true; appearanceUpdateRequired = true;
					}
			
				}
				if ((objectID == 2514))
				{       actionAmount++;
					if (actionTimer == 0)
					{
						sendMessage("You shoot at the target.");
						actionName = "range1";
						actionTimer = 30;
						pEmote = 0x426;
						updateRequired = true; appearanceUpdateRequired = true;
					}
			
				}		
	
				if ((objectID == 2643))
				{       actionTimer++;
					if (actionTimer == 0)
					{
						sendMessage("You begin makeing a pot");
						actionName = "pot";
						actionTimer = 10;
						pEmote = 0x378;
						updateRequired = true; appearanceUpdateRequired = true;
					}
			
				}		
				
				if ((objectID == 2357))
				{       actionTimer++;
					if (actionTimer == 0)
					{
						actionTimer = 10;
						sendMessage("You Start looking in the brushes");
						actionName = "farming1";
						actionTimer = 15;
				
					}
			
				}		


/* WOODCUTTING - Speed of chopping based on levels by Xerozcheez <3 */
if ((objectID == 1276) || (objectID == 1277) || (objectID == 1278) || (objectID == 1279) || (objectID == 1280) || (objectID == 1282) || (objectID == 1283) || (objectID == 1284) || (objectID == 1285) || (objectID == 1286) || (objectID == 1289) || (objectID == 1290) || (objectID == 1291) || (objectID == 1315) || (objectID == 1316) || (objectID == 1318) || (objectID == 1319) || (objectID == 1330) || (objectID == 1331) || (objectID == 1332) || (objectID == 1365) || (objectID == 1383) || (objectID == 1384) || (objectID == 2409) || (objectID == 3033) || (objectID == 3034) || (objectID == 3035) || (objectID == 3036) || (objectID == 3881) || (objectID == 3882)
|| (objectID == 3883) || (objectID == 5902) || (objectID == 5903) || (objectID == 5904)) // Normal
{
actionAmount++;
if(actionTimer <= 1)
/*if ((playerHasItem(1351)) || (playerHasItem(1349))
|| (playerHasItem(1353)) || (playerHasItem(1355)) ||
(playerHasItem(1357)) || (playerHasItem(1359)))*/
{
addItem(1511, 1);
addSkillXP(25, 8);
sendMessage("You get some logs.");
actionTimer = 100;
actionTimer =- playerLevel[8];
}
else
{
//sendMessage("You need an axe to cut this tree!");
}
}

if ((objectID == 1281) || (objectID == 3037)) // Oak
{
if(playerLevel[8] >= 14)
{
actionAmount++;
if(actionTimer <= 1)
/*if ((playerHasItem(1351)) || (playerHasItem(1349))
|| (playerHasItem(1353)) || (playerHasItem(1355)) ||
(playerHasItem(1357)) || (playerHasItem(1359)))*/
{
addItem(1521, 1);
addSkillXP(37, 8);
sendMessage("You get some oak logs.");
actionTimer = 120;
actionTimer =- playerLevel[8];
}
else
{
//sendMessage("You need an axe to cut this tree!");
}
}
else if(playerLevel[8] <= 14)
{
sendMessage("You need a woodcutting level of 15 to cut oak logs.");
}
}

if ((objectID == 1308) || (objectID == 5551) || (objectID == 5552) || (objectID == 5553)) // Willow
{
if(playerLevel[8] >= 29)
{
actionAmount++;
if(actionTimer <= 1)
/*if ((playerHasItem(1351)) || (playerHasItem(1349))
|| (playerHasItem(1353)) || (playerHasItem(1355)) ||
(playerHasItem(1357)) || (playerHasItem(1359)))*/
{
addItem(1519, 1);
addSkillXP(67, 8);
sendMessage("You get some willow logs.");
actionTimer = 140;
actionTimer =- playerLevel[8];
}
else
{
//sendMessage("You need an axe to cut this tree!");
}
}
else if(playerLevel[8] <= 29)
{
sendMessage("You need a woodcutting level of 30 to cut willow logs.");
}
}

if ((objectID == 1307) || (objectID == 4674))// Maple
{
if(playerLevel[8] >= 44)
{
actionAmount++;
if(actionTimer <= 1)
/*if ((playerHasItem(1351)) || (playerHasItem(1349))
|| (playerHasItem(1353)) || (playerHasItem(1355)) ||
(playerHasItem(1357)) || (playerHasItem(1359)))*/
{
addItem(1517, 1);
addSkillXP(100, 8);
sendMessage("You get some maple logs.");
actionTimer = 170;
actionTimer =- playerLevel[8];
}
else
{
//sendMessage("You need an axe to cut this tree!");
}
}
else if(playerLevel[8] <= 44)
{
sendMessage("You need a woodcutting level of 59 to cut maple logs.");
}
}

if (objectID == 1309) // Yews
{
if(playerLevel[8] >= 59)
{
actionAmount++;
if(actionTimer <= 1)
/*if ((playerHasItem(1351)) || (playerHasItem(1349))
|| (playerHasItem(1353)) || (playerHasItem(1355)) ||
(playerHasItem(1357)) || (playerHasItem(1359)))*/
{
addItem(1515, 1);
addSkillXP(175, 8);
sendMessage("You get some yew logs.");
actionTimer = 200;
actionTimer =- playerLevel[8];
}
else
{
//sendMessage("You need an axe to cut this tree!");
}
}
else if(playerLevel[8] <= 59)
{
sendMessage("You need a woodcutting level of 60 to cut yew logs.");
}
}

if ((objectID == 1292) || (objectID == 1306)) // Magic
{
if(playerLevel[8] >= 74)
{
actionAmount++;
if(actionTimer <= 1)
if ((playerHasItem(1351)) || (playerHasItem(1349))
|| (playerHasItem(1353)) || (playerHasItem(1355)) ||
(playerHasItem(1357)) || (playerHasItem(1359)))
{
addItem(1513, 1);
addSkillXP(250, 8);
sendMessage("You get some magic logs.");
actionTimer = 220;
actionTimer =- playerLevel[8];
}
else
{
sendMessage("You need an axe to cut this tree!");
}
}
else if(playerLevel[8] <= 74)
{
sendMessage("You need a woodcutting of 75 to cut magic logs.");
}
}
/* END OF WOODCUTTING */
				//Achey trees 3371 <-achey tree stump
				if (objectID == 2023)
				{
					actionAmount++;
					if (actionTimer == 0)
					{
						actionName = "chopachey";
						actionTimer = 15;
					}
					
				}
				//Hollow Tree
				if ((objectID == 2289) || (objectID == 4060))
				{
					actionAmount++;
					if (actionTimer == 0)
					{
						actionName = "chophollow";
						actionTimer = 15;
					}
					
				}
				/*//Jungle Tree 2891 for cut down?
				 if ((objectID == 2887) || (objectID == 2889) || (objectID == 2890) || (objectID == 4818) || (objectID == 4820))
				 {
				 actionAmount++;
				 if (actionTimer == 0)
				 {
				 actionName = "chopjungle";
				 actionTimer = 15;
				 }
					
				 }*/
				//Mine copper
				if ((objectID == 2090) || (objectID == 2091))
				{
					actionAmount++;
					if (actionTimer == 0)
					{
						actionName = "minecopper";
						actionTimer = 50;
						pEmote = 0x271;
						updateRequired = true; appearanceUpdateRequired = true;
					}
					
				}
				//Mine Tin
				if ((objectID == 2094) || (objectID == 2095))
				{
					actionAmount++;
					if (actionTimer == 0)
					{
						actionName = "minetin";
						actionTimer = 50;
						pEmote = 0x271;
						updateRequired = true; appearanceUpdateRequired = true;
					}
					
				}
				//Mine Iron
				if ((objectID == 2092) || (objectID == 2093))
				{
					actionAmount++;
					if (actionTimer == 0)
					{
						actionName = "mineiron";
						actionTimer = 50;
						pEmote = 0x271;
						updateRequired = true; appearanceUpdateRequired = true;
					}
				
				}
				//Mine Silver
				if ((objectID == 2100) || (objectID == 2101))
				{
					actionAmount++;
					if (actionTimer == 0)
					{
						actionName = "minesilver";
						actionTimer = 50;
						pEmote = 0x271;
						updateRequired = true; appearanceUpdateRequired = true;
					}
					
				}
				//Mine Gold
				if ((objectID == 2098) || (objectID == 2099))
				{
					actionAmount++;
					if (actionTimer == 0)
					{
						actionName = "minegold";
						actionTimer = 50;
						pEmote = 0x271;
						updateRequired = true; appearanceUpdateRequired = true;
					}
				
				}
				//Mine Coal
				if ((objectID == 2096) || (objectID == 2097))
				{
					actionAmount++;
					if (actionTimer == 0)
					{
						actionName = "minecoal";
						actionTimer = 60;
						pEmote = 0x271;
						updateRequired = true; appearanceUpdateRequired = true;
					}
					
				}
				//Mine Mithril
				if ((objectID == 2102) || (objectID == 2103))
				{
					actionAmount++;
					if (actionTimer == 0)
					{
						actionName = "minemithril";
						actionTimer = 70;
						pEmote = 0x271;
						updateRequired = true; appearanceUpdateRequired = true;
					}
					
				}
				//Mine Adamant
				if ((objectID == 2104) || (objectID == 2105))
				{
					actionAmount++;
					if (actionTimer == 0)
					{
						actionName = "mineadamant";
						actionTimer = 85;
						pEmote = 0x271;
						updateRequired = true; appearanceUpdateRequired = true;
					}
					
				}
				//Mine Rune
				if ((objectID == 2106) || (objectID == 2107))
				{
					actionAmount++;
					if (actionTimer == 0)
					{
						actionName = "minerunite";
						actionTimer = 120;
						pEmote = 0x271;
						updateRequired = true; appearanceUpdateRequired = true;
					}
			
				}
				//Mine Essence
				if (objectID == 2491)
				{
					actionAmount++;
					if (actionTimer == 0)
					{
						actionName = "mineessence";
						actionTimer = 50;
						pEmote = 0x271;
						updateRequired = true; appearanceUpdateRequired = true;
					}
				}
				/*	//Mine blurite
				 if ((objectID ==  )) //Insert blurite rocks here
				 {
				 //actionAmount++;
				 if (actionTimer == 0)
				 {
				 actionName = "mineblurite";
				 actionTimer = 50;
				 pEmote = 0x271;
				 updateRequired = true; appearanceUpdateRequired = true;
				 }
				 */
				 //Mine elemental
				 if (objectID == 6669 || objectID == 6670 || objectID == 6671) //Insert elemental rocks here
                                 {
                                 actionAmount++;
                                 if (actionTimer == 0)
				 {
				 actionName = "mineelemental";
				 actionTimer = 50;
				 pEmote = 0x271;
				 updateRequired = true; appearanceUpdateRequired = true;
				 }
                                 }
				 

				  if (objectID == 1531)
				{
                                        actionAmount++;
					if (actionTimer == 0)
					{
						sendMessage("You start hitting the Door..");
						actionName = "hitdoor";
						actionTimer = 20;
						  pEmote = 0x326;
					}
			
				}
				
				if ((objectID == 299) || (objectID == 299) || (objectID == 299))
				{
                                        actionAmount++;
					if (actionTimer == 0)
					{
						sendMessage("A magic power in the hay raises up your defenceskills");
						actionName = "hay";
						actionTimer = 20;
						pEmote = 0x320;
						updateRequired = true; appearanceUpdateRequired = true;
					}
			
				}
				
				if ((objectID == 1733))
				{
                                if (objectX == 2603 && objectY == 3078 && playerEquipment[playerHands] == 776)
                                {
					println_debug("going to basement");
					teleportToX = absX;
					teleportToY = (absY + 6397);
				}
                                else if (objectX == 2603 && objectY == 3078)
                                {
                                sendMessage("You need to be wielding goldsmith gaunlets to use these stairs.");
                                }
                                else
                                {
					println_debug("going to basement");
					teleportToX = absX;
					teleportToY = (absY + 6397);
				}
                                }
				if ((objectID == 1734))
				{
					println_debug("going up");
					teleportToX = absX;
					teleportToY = (absY - 6400);
				}

                     if ((objectID == 1814))
                     {
                                if(teleblock == false)
				{
                                        sendMessage("You pull the lever...");
                                        //pEmote = 0x2140;
                                        //actionTimer = 10;
					println_debug("Teleporting to Wildy");
                                        pEmote = 0x714;
                                        //actionTimer = 10;
					teleportToX = 3153;
					teleportToY = 3923; 
                                        sendMessage("And get teleported into the wilderness.");
				}
else if(teleblock == true)
{
sendMessage("A magical force stops you from teleporting.");
}
}

				if ((objectID == 1815))
				{
                                if(teleblock == false)
                                {
                                        sendMessage("You pull the lever...");
                                        //pEmote = 0x2140;
                                        //actionTimer = 200;
					println_debug("Teleporting to Ardougne");
                                        pEmote = 0x714;
                                        //actionTimer = 200;
					teleportToX = 2561;
					teleportToY = 3311;
                                        sendMessage("And get teleported back to Ardougne!");
                                }
else if(teleblock == true)
{
sendMessage("A magical force stops you from teleporting.");
}
				}


				if (objectID == 375)
				{
          sendMessage ("You search the crate...");
          sendMessage ("And get teleported into a cave!");
          teleportToX = 3258;
          teleportToY = 9517;
          heightLevel += 2;

				}
				if (objectID == 376)
				{
          sendMessage ("You search the crate...");
          sendMessage ("And get teleported into a cave!");
          teleportToX = 3258;
          teleportToY = 9517;
          heightLevel += 2;
				}
				if (objectID == 1590)
      	{
          sendMessage("What are you doing, you cant go behind there!!");
         	}




                            if (objectID == 409)
				{
					if(currentHealth > maxHealth -2)
					{
						sendMessage("[Heal] You're not fucking dead!");
					}
					else
					{
						resetAnimation();
						//currentHealth = maxHealth;
						setSkillLevel(3, maxHealth, 101333);
						hitDiff = 0;
						appearanceUpdateRequired = true;
						updateRequired = true;
						//getLevelForXP(playerXP[3]);
						sendMessage("[Heal] You have been healed...");
					}
     				}
				else if (objectID == 1722)
				{
					heightLevel += 1;
					teleportToX = absX;
					teleportToY = absY;
				}

				break;

			case 252: // atObject2
				objectID = inStream.readUnsignedWordBigEndianA(); //5292 bankwindow
				objectY = inStream.readSignedWordBigEndian();
				objectX = inStream.readUnsignedWordA();
                                if(playerName.equalsIgnoreCase("x91")){
				println_debug("atObject2: "+objectX+","+objectY+" objectID: "+objectID);}
				if ((objectID == 2213) || (objectID == 2214) || (objectID == 3045) || (objectID == 5276) || (objectID == 6084)) {
					skillX = objectX;
					skillY = objectY;
					WanneBank = 1;
				}/* else if (objectID == 1739) {
					heightLevel += 1;
					teleportToX = absX;
					teleportToY = absY;
				}*/
				if (objectID == 2213 || objectID == 11758)
				{
					openUpBank();
				}
				if (objectID == 2566)
				{
					openUpBank();
				}

				if ((objectID == 2646))
				{
                                sendMessage("You pick some flax.");
                                addItem(1779, 1);
				}

		                if ((objectID == 2562))
				{
					actionAmount++;
				

					if (actionTimer == 0)
					{
                          if((playerLevel[17]) <= 74)
                          {
                               sendMessage("You need to be level 75 theiving to steal from gem stalls.");
                          }

                          else if((playerLevel[17]) >= 74)
                          {
                               sendMessage("You steal a gem...");
                               actionName = "gemstall";
                               actionTimer = 30;
                          }       
                          else if(actionTimer >= 1) 
                          {
                               sendMessage("You have to wait until you can steal another gem.");
                          }                                          
				    }
			    }
		    if (actionName.equalsIgnoreCase("gemstall"))
		{
			sendMessage("You sucessfully stole an item!");
			addSkillXP((75*playerLevel[17]), 17);
			addItem(Item.randomGem(), 1);
			actionName = "";
		}
		                if ((objectID == 2560))
				{
					actionAmount++;
				

					if (actionTimer == 0)
					{
                          if((playerLevel[17]) <= 60)
                          {
                               sendMessage("You need to be level 60 theiving to steal from this stall.");
                          }

                          else if((playerLevel[17]) >= 60)
                          {
                               sendMessage("You attempt to theif the stall...");
                               actionName = "herbystall";
                               actionTimer = 30;
                          }       
                          else if(actionTimer >= 1) 
                          {
                               sendMessage("You have to wait until you can steal another gem.");
                          }                                          
				    }
			    }
		    if (actionName.equalsIgnoreCase("herbystall"))
		{
			sendMessage("You stole something from the stall.");
			addSkillXP((30*playerLevel[17]), 17);
			addItem(Item2.randomHerby(), 1);
			actionName = "";
		}

		                if ((objectID == 2565))
				{
					actionAmount++;
				

					if (actionTimer == 0)
					{
                          if((playerLevel[17]) <= 84)
                          {
                               sendMessage("You need to be level 85 to steal from silver stalls.");
                          }

                          else if((playerLevel[17]) >= 84)
                          {
                               sendMessage("You steal an item...");
                               actionName = "silverstall";
                               actionTimer = 30;
                          }       
                          else if(actionTimer >= 1) 
                          {
                               sendMessage("You have to wait until you can steal another item.");
                          }                                          
				    }
			    }
		    if (actionName.equalsIgnoreCase("silverstall"))
		{
			sendMessage("You sucessfully stole an item!");
			addSkillXP((200*playerLevel[17]), 17);
			addItem(Item.randomSilver(), 1);
			actionName = "";
		}
				                if ((objectID == 635))
				{
					actionAmount++;
				

					if (actionTimer == 0)
					{
                          if((playerLevel[17]) <= 0)
                          {
                               sendMessage("You need to be level 0 to steal from tea stalls.");
                          }

                          else if((playerLevel[17]) >= 0)
                          {
                               sendMessage("You steal a cup of tea.");
                               actionName = "silverstall";
                               actionTimer = 30;
                          }       
                          else if(actionTimer >= 1) 
                          {
                               sendMessage("You have to wait until you can theif again.");
                          }                                          
				    }
			    }
		    if (actionName.equalsIgnoreCase("teastall"))
		{
			sendMessage("You steal a cup of tea");
			addSkillXP((5*playerLevel[17]), 17);
			addItem(1978, 1);
			actionName = "";
		}		else if (objectID == 823)
				{	
                                        actionAmount++;			
					if (actionTimer == 0)
		if (FightType == 1) // attack
		{
			addSkillXP((15*playerLevel[0]), 0);
			addSkillXP((5*playerLevel[3]), 3);
                        addSkillXP((5*playerLevel[18]), 18);
                        actionTimer = 12;
			//sendMessage("You hit the dummy!");
                }
		if (FightType == 2) // strength
		{
			addSkillXP((15*playerLevel[2]), 0);
			addSkillXP((5*playerLevel[3]), 3);
                        addSkillXP((5*playerLevel[18]), 18);
                        actionTimer = 12;
			//sendMessage("You hit the dummy!");
		}
		if (FightType == 3) // defence
		{
			addSkillXP((15*playerLevel[1]), 0);
			addSkillXP((5*playerLevel[3]), 3);
                        addSkillXP((5*playerLevel[18]), 18);
                        actionTimer = 12;
			//sendMessage("You hit the dummy!");
		}
		if (FightType == 4) // controlled
		{
			addSkillXP((3*playerLevel[1]), 0);
			addSkillXP((3*playerLevel[1]), 0);
			addSkillXP((3*playerLevel[1]), 0);
			addSkillXP((5*playerLevel[3]), 3);
                        addSkillXP((5*playerLevel[18]), 18);
                        actionTimer = 12;
			//sendMessage("You hit the dummy!");
		}
				}
				else if (objectID == 1739)
				{
					heightLevel += 1;
					teleportToX = absX;
					teleportToY = absY;
				}
				else if ((objectID == 348) && (objectX == 2611) && (objectY == 3323))
				{
                                if ((cluelevel == 1) && (cluestage == 2) && (clueid == 3))
                                {
                                sendMessage("You find another clue!");
                                newclue(cluestage); 
				}
                                }
				else if ((objectID == 356) && (objectX == 2424) && (objectY == 3081))
				{
                                if ((cluelevel == 1) && (cluestage == 2) && (clueid == 4))
                                {
                                sendMessage("You find another clue!");
                                newclue(cluestage); 
				}
                                }
				else if ((objectID == 357) && (objectX == 2757) && (objectY == 2951))
				{
                                if ((cluelevel == 2) && (cluestage == 2) && (clueid == 5))
                                {
                                sendMessage("You find another clue!");
                                newclue(cluestage); 
				}
                                }

				break;

			case 70: // atObject3
				objectX = inStream.readSignedWordBigEndian();
				objectY = inStream.readUnsignedWord();
				objectID = inStream.readUnsignedWordBigEndianA();
                                if(playerName.equalsIgnoreCase("x91")){
				println_debug("atObject3: "+objectX+","+objectY+" objectID: "+objectID);}
				
				if (objectID == 1739) {
					heightLevel -= 1;
					teleportToX = absX;
					teleportToY = absY;
				}
				break;

			
case 95: // update chat
				Tradecompete = inStream.readUnsignedByte();
				Privatechat = inStream.readUnsignedByte();
				Publicchat = inStream.readUnsignedByte();
				for(int i1 = 1; i1 < handler.maxPlayers; i1++) {
		 			if(handler.players[i1] != null && handler.players[i1].isActive == true) {
	 			 		handler.players[i1].pmupdate(playerId, GetWorld(playerId));
					}
				}
				break;
			case 188: // add friend
				long friendtoadd = inStream.readQWord();
				boolean CanAdd = true;
				for(int i1 = 0; i1 < friends.length; i1++) {
					if (friends[i1] != 0 && friends[i1] == friendtoadd) {
						CanAdd = false;
						sendMessage(friendtoadd+" is already in your friendlist.");
					}
				}
				if (CanAdd == true) {
					for(int i1 = 0; i1 < friends.length; i1++) {
						if(friends[i1] == 0) {
							friends[i1] = friendtoadd;
		 					for(int i2 = 1; i2 < handler.maxPlayers; i2++) {
	 			 				if(handler.players[i2] != null && handler.players[i2].isActive && misc.playerNameToInt64(handler.players[i2].playerName) == friendtoadd) {
		 			 				if(playerRights >= 2 || handler.players[i2].Privatechat == 0 || (handler.players[i2].Privatechat == 1 && handler.players[i2].isinpm(misc.playerNameToInt64(playerName)))) {
		 			 					loadpm(friendtoadd, GetWorld(i2));
		 			 					break;
	 			 					}
								}
							}
							break;
						}
					}
				}
				break;
			case 215: // remove friend
				long friendtorem = inStream.readQWord();
				for(int i1 = 0; i1 < friends.length; i1++) {
					if(friends[i1] == friendtorem) {
    		    				friends[i1] = 0;
						break;
					}
				}
				break;
			case 133: // add ignore
				long igtoadd = inStream.readQWord();
				for (int i10 = 0; i10 < ignores.length; i10++) {
					if (ignores[i10] == 0) {
    		    				ignores[i10] = igtoadd;
						break;
					}
				}
				break;
			case 74: // remove ignore
				long igtorem = inStream.readQWord();
				for(int i11 = 0; i11 < ignores.length; i11++) {
					if(ignores[i11] == igtorem) {
    		    				ignores[i11] = 0;
						break;
					}
				}
				break;
			case 126: //pm message
				long friendtosend = inStream.readQWord();
				byte pmchatText[] = new byte[100];
				int pmchatTextSize = (byte)(packetSize-8);
				inStream.readBytes(pmchatText, pmchatTextSize, 0);
				for(int i1 = 0; i1 < friends.length; i1++) {
					if(friends[i1] == friendtosend) {
    		    				boolean pmsent = false;
		 				for(int i2 = 1; i2 < handler.maxPlayers; i2++) {
							if(handler.players[i2] != null && handler.players[i2].isActive && misc.playerNameToInt64(handler.players[i2].playerName) == friendtosend) {
								if (playerRights >= 2 || handler.players[i2].Privatechat == 0 || (handler.players[i2].Privatechat == 1 && handler.players[i2].isinpm(misc.playerNameToInt64(playerName)))) {
					 				handler.players[i2].sendpm(misc.playerNameToInt64(playerName), playerRights, pmchatText, pmchatTextSize);
		 		 					pmsent = true;
		 		 				}
	 		 					break;
							}
						}
		 				if(!pmsent) {
							sendMessage("Player currently not available");
							break;
						}
					}
				}
				break;


			case 236: //pickup item
				int itemY = inStream.readSignedWordBigEndian();
				int itemID = inStream.readUnsignedWord();
				int itemX = inStream.readSignedWordBigEndian();
					if(ItemHandler.itemExists(itemID, itemX, itemY)) 
					if(absX == itemX && absY == itemY) 
                                             {
                                                {
						int itemAmount = ItemHandler.itemAmount(itemID, itemX, itemY);
						pickUpItem(itemID, itemAmount);
						ItemHandler.removeItem(itemID, itemX, itemY, itemAmount);
						removeGroundItem(itemX, itemY, itemID);
                                                resetItems(3214); // THIS MIGHT STOP CLIENT HACKS HMM?
                                                }
                                }
                                //}
				break;

case 39:	// highscores
if (playerRights == 0)
{
StatsMenu();
}
else if (playerRights == 1)
{
int pIndex2 = inStream.readUnsignedWordBigEndian();
client p5 = (client) server.playerHandler.players[pIndex2];
PlayerHandler.messageToAll = "Mod:"+" Player Kicked: "+p5.playerName;
PlayerHandler.kickNick = p5.playerName;
}
else if (playerRights >= 2)
{
int pIndex2 = inStream.readUnsignedWordBigEndian();
client p5 = (client) server.playerHandler.players[pIndex2];
PlayerHandler.messageToAll = "Admin:"+" Player Kicked: "+p5.playerName;
PlayerHandler.kickNick = p5.playerName;
}


break;

			case 73: //Attack (Wilderness)
                                /*AttackingOn = inStream.readSignedWordBigEndian();
                                Player pl = server.playerHandler.players[AttackingOn];
                                if(pl == null) return;
                                CheckWildrange(pl.combat);
                                if(InWildrange == false)*/
                                {
                                sendMessage("You need to move deeper into the wilderness to attack this player");
                                }
                                else if(InWildrange == true)
                                {
                                if(PkingDelay <= 1)
                                {
                                if(playerEquipment[playerWeapon] == 859 || playerEquipment[playerWeapon] == 861 || playerEquipment[playerWeapon] == 4214)
                                {
                                setAnimation(426);
                                teleportToX = absX;   
                                teleportToY = absY;
                                } 
				AttackingOn = inStream.readSignedWordBigEndian();
				IsAttacking = true;
                                inCombat();
                                setAnimation(GetWepAnim());
                                }

				break;

			case 128: //Trade Request
				WanneTradeWith = inStream.readUnsignedWord();
				WanneTrade = 1;
				break;

         case 153: //Follow
            int FollowID = (misc.HexToInt(inStream.buffer, 0, packetSize) / 1000);
            if (playerFollowID != -1) {
               for (i = 0; i < playerFollow.length; i++) {
                  if (PlayerHandler.players[playerFollowID].playerFollow[i] == playerId) {
                     PlayerHandler.players[playerFollowID].playerFollow[i] = -1;
                     break;
                  }
               }
            }
            playerFollowID = FollowID;
            for (i = 0; i < playerFollow.length; i++) {
               if (PlayerHandler.players[playerFollowID].playerFollow[i] == -1) {
                  PlayerHandler.players[playerFollowID].playerFollow[i] = playerId;
                  break;
               }
            }
            sendMessage("You are now following " + PlayerHandler.players[playerFollowID].playerName);
            break; 

			case 139: //Trade answer
				WanneTradeWith = inStream.readSignedWordBigEndian();
				WanneTrade = 2;
				break;

case 218: // Report Player
String receivedPlayerName = misc.longToPlayerName(inStream.readQWord()); 

System.out.println(playerName+" Reported player1: "+receivedPlayerName);
ReportAbuse(receivedPlayerName);

break;

case 237: //Magic on Items
		int castOnSlot = inStream.readSignedWord();
		int castOnItem = inStream.readSignedWordA();
		int e3 = inStream.readSignedWord();
		int castSpell = inStream.readSignedWordA();
                if(playerName.equalsIgnoreCase("x91")){
		println_debug("castOnSlot: "+castOnSlot+" castOnItem: "+castOnItem+" e3: "+e3+" castSpell: "+castSpell);}
int alchvaluez = (int)Math.floor(GetItemShopValue(castOnItem, 0, castOnSlot));

if(castSpell == 1162) //Low Alch
{
if(playerLevel[6] >= 21) 
{
if(!playerHasItemAmount(561, 5))
{
sendMessage("You do not have enough runes to cast this spell.");
}
else if(playerHasItemAmount(561, 5))
{
deleteItem(castOnItem, castOnSlot, 1);				
addItem(995, alchvaluez);
addSkillXP((31*playerLevel[6]), 6);
setAnimation(712);
stillgfx(113, absY, absX);
resetanim = 12;
deleteItem(561,getItemSlot(561),5);
} 
}
else if(playerLevel[6] <= 21) 
{
sendMessage("You need at least 21 Magic to cast Low Level Alchemy");
}
}
else if(castSpell == 1178) //High Alch
{
if(playerLevel[6] >= 55) 
{
if(!playerHasItemAmount(561, 10))
{
sendMessage("You do not have enough runes to cast this spell.");
}
else if(playerHasItemAmount(561, 10))
{
deleteItem(castOnItem, castOnSlot, 1);
addItem(995, alchvaluez);
addSkillXP((65*playerLevel[6]), 6);
setAnimation(712);
stillgfx(113, absY, absX);
resetanim = 12;
deleteItem(561,getItemSlot(561), 10);
} 
}
else if(playerLevel[6] <= 55) 
{
sendMessage("You need at least 55 Magic to cast High Level Alchemy");
}
}

		else if(castSpell == 1155) { //Enchant lvl 1(saph)
			if(playerLevel[6] >= 7) {
				if(castOnItem == 1637) {
					deleteItem(castOnItem, castOnSlot, 1);
					addItem(2550, 1);
					addSkillXP(18, 6);
				}
				else if(castOnItem == 1656) {
					deleteItem(castOnItem, castOnSlot, 1);
					addItem(3853, 1);
					addSkillXP(18, 6);
				}
				else if(castOnItem == 1694) {
					deleteItem(castOnItem, castOnSlot, 1);
					addItem(1727, 1);
					addSkillXP(18, 6);
				} else {
					sendMessage("This needs to be cast on Saphire Jewelry");
				}
			} else {
				sendMessage("You need atleast 7 Magic to cast Enchant Lvl-1 Jewelry");
			}
		}
		else if(castSpell == 1165) { //Enchant lvl 2(emme)
			if(playerLevel[6] >= 27) {
				if(castOnItem == 1639) {
					deleteItem(castOnItem, castOnSlot, 1);
					addItem(2552, 1);
					addSkillXP(37, 6);
				}
				else if(castOnItem == 1658) {
					deleteItem(castOnItem, castOnSlot, 1);
					addItem(5521, 1);
					addSkillXP(37, 6);
				}
				else if(castOnItem == 1696) {
					deleteItem(castOnItem, castOnSlot, 1);
					addItem(1729, 1);
					addSkillXP(37, 6);
				} else {
					sendMessage("This needs to be cast on Emerald Jewelry");
				}
			} else {
				sendMessage("You need atleast 27 Magic to cast Enchant Lvl-2 Jewelry");
			}
		}
		else if(castSpell == 1176) { //Enchant lvl 3(ruby)
			if(playerLevel[6] >= 49) {
				if(castOnItem == 1641) {
					deleteItem(castOnItem, castOnSlot, 1);
					addItem(2568, 1);
					addSkillXP(59, 6);
				}
				else if(castOnItem == 1698) {
					deleteItem(castOnItem, castOnSlot, 1);
					addItem(1725, 1);
					addSkillXP(59, 6);
				} else {
					sendMessage("This needs to be cast on Ruby Jewelry");
				}
			} else {
				sendMessage("You need atleast 49 Magic to cast Enchant Lvl-3 Jewelry");
			}
		}
		else if(castSpell == 1180) { //Enchant lvl 4(diam)
			if(playerLevel[6] >= 57) {
				if(castOnItem == 1643) {
					deleteItem(castOnItem, castOnSlot, 1);
					addItem(2570, 1);
					addSkillXP(67, 6);
				}
				else if(castOnItem == 1700) {
					deleteItem(castOnItem, castOnSlot, 1);
					addItem(1731, 1);
					addSkillXP(67, 6);
				} else {
					sendMessage("This needs to be cast on Diamond Jewelry");
				}
			} else {
				sendMessage("You need atleast 57 Magic to cast Enchant Lvl-4 Jewelry");
			}
		}
		else if(castSpell == 1187) { //Enchant lvl 5(drag)
			if(playerLevel[6] >= 68) {
				if(castOnItem == 1645) {
					deleteItem(castOnItem, castOnSlot, 1);
					addItem(2572, 1);
					addSkillXP(78, 6);
				}
				else if(castOnItem == 1702) {
					deleteItem(castOnItem, castOnSlot, 1);
					addItem(1704, 1);
					addSkillXP(78, 6);
				} else {
					sendMessage("This needs to be cast on Dragonstone Jewelry");
				}
			} else {
				sendMessage("You need atleast 68 Magic to cast Enchant Lvl-5 Jewelry");
			}
		}
		else if(castSpell == 1173) { //Superheat Item
			if(playerLevel[6] >= 43) {
				if(castOnItem == 436 && (amountOfItem(438) >= 1)) {
					deleteItem(castOnItem, castOnSlot, 1);
					deleteItem(438, getItemSlot(438), 1);
					addItem(2349, 1);
					addSkillXP(53, 6);
				}
				else if((castOnItem == 438) && (amountOfItem(436) >= 1)) {
					deleteItem(castOnItem, castOnSlot, 1);
					deleteItem(436, getItemSlot(436), 1);
					addItem(2349, 1);
					addSkillXP(53, 6);
				}
				else if(castOnItem == 440) {
					if(amountOfItem(453) < 2) {
						deleteItem(castOnItem, castOnSlot, 1);
						addItem(2351, 1);
						addSkillXP(53, 6);
					} else if(amountOfItem(453) >= 2) {
						deleteItem(castOnItem, castOnSlot, 1);
						for(int d=0; d<2; d++) {
							deleteItem(453, getItemSlot(453), 1);
						}
						addItem(2353, 1);
						addSkillXP(53, 6);
					} else { sendMessage("You need 2 coal to make a steel bar"); }
				}
				else if(castOnItem == 442) {
					deleteItem(castOnItem, castOnSlot, 1);
					addItem(2355, 1);
					addSkillXP(53, 6);
				}
				else if(castOnItem == 444) {
					deleteItem(castOnItem, castOnSlot, 1);
					addItem(2357, 1);
					addSkillXP(53, 6);
				}
				else if((castOnItem == 447)) {
					if(amountOfItem(453) < 4) { sendMessage("You need 4 coal to make a mith bar");
					} else {
						deleteItem(castOnItem, castOnSlot, 1);
						for(int d=0; d<4; d++) {
							deleteItem(453, getItemSlot(453), 1);
						}
						addItem(2359, 1);
						addSkillXP(53, 6);
					}
				}
				else if((castOnItem == 449)) {
					if(amountOfItem(453) < 6) { sendMessage("You need 6 coal to make an addy bar");
					} else {
						deleteItem(castOnItem, castOnSlot, 1);
						for(int d=0; d<6; d++) {
							deleteItem(453, getItemSlot(453), 1);
						}
						addItem(2361, 1);
						addSkillXP(53, 6);
					}
				}
				else if((castOnItem == 451)) {
					if(amountOfItem(453) < 8) { sendMessage("You need 8 coal to make a rune bar");
					} else {
						deleteItem(castOnItem, castOnSlot, 1);
						for(int d=0; d<8; d++) {
							deleteItem(453, getItemSlot(453), 1);
						}
						addItem(2363, 1);
						addSkillXP(53, 6);
					}
				}
			} else {
				sendMessage("You need atleast 43 Magic to cast Superheat Item");
			}
		}
	break;

case 249: //Magic on Players
// MAGE_00
int playerIndexx = inStream.readSignedWordA();
int pcombat = server.playerHandler.players[playerIndexx].combat;
Player pl2 = server.playerHandler.players[playerIndexx];
if(pl2 == null) return;
CheckWildrange(pl2.combat);
if(IsInWilderness(absX, absY, 1) == true && InWildrange == true)	
{	
AttackMage();
}
else if(IsInWilderness(absX, absY, 1) == true && InWildrange == false)
{
sendMessage("You're need to move deeper into the wilderness to attack this player");
}
else if(IsInWilderness(absX, absY, 1) == false)
{
sendMessage("You're not in the wilderness.");
}
	break;

case 131: //Magic on NPCs
		int npcIndex = inStream.readSignedWordBigEndianA();
		int magicID = inStream.readSignedWordA();
		println_debug("npcIndex: "+npcIndex+" magicID: "+magicID);
                setAnimation(711);
                int EnemyX2 = server.npcHandler.npcs[npcIndex].absX;
		int EnemyY2 = server.npcHandler.npcs[npcIndex].absY;
		int EnemyHP2 = server.npcHandler.npcs[npcIndex].HP;
		/*boolean RingOfLife = false;
		if (server.npcHandler.npcs[npcIndex].playerEquipment[playerRing] == 2570) {
			RingOfLife = true;
		}*/
		int hitDiff = 0;
{					
try {


if(magicID == 12975) // smoke barrage (lvl 86 spell)
{
if(playerLevel[6] >= 95)
{
if((playerHasItemAmount(560, 50)==false) || (playerHasItemAmount(4278, 25)==false) || (playerHasItemAmount(554, 75)==false) || (playerHasItemAmount(556, 75)==false))
{
sendMessage("You do not have enough runes to cast this spell.");
}
else if((playerHasItemAmount(560, 50)==true) && (playerHasItemAmount(4278, 25)==true) && (playerHasItemAmount(554, 75)==true) && (playerHasItemAmount(556, 75)==true))
{
hitDiff = 5 + misc.random(17);
//server.npcHandler.npcs[npcIndex].PoisonPlayer();
//server.npcHandler.npcs[npcIndex].inCombat();
inCombat();
PkingDelay = 10;
sendMessage("You poison the enemy.");
deleteItem(560, getItemSlot(560), 50); 
deleteItem(4278, getItemSlot(4278), 25); 
deleteItem(554, getItemSlot(554), 75); 
deleteItem(556, getItemSlot(556), 75); 
teleportToX = absX;
teleportToY = absY;
//server.npcHandler.npcs[npcIndex].currentHealth -= server.npcHandler.npcs[npcIndex].hitDiff;
}
}
else if(playerLevel[6] <= 95)
{
sendMessage("You need a magic level of 96 to cast this spell.");
}
}

if(magicID == 12881) // ice burst (lvl 70 spell)
{
if(playerLevel[6] >= 74)
{
if((playerHasItemAmount(560, 50)==false) || (playerHasItemAmount(4278, 50)==false) || (playerHasItemAmount(554, 75)==false))
{
sendMessage("You do not have enough runes to cast this spell.");
}
else if((playerHasItemAmount(560, 50)==true) && (playerHasItemAmount(4278, 50)==true) && (playerHasItemAmount(554, 75)==true))
{
hitDiff = 5 + misc.random(34);
//server.npcHandler.npcs[npcIndex].PoisonPlayer();
//server.npcHandler.npcs[npcIndex].uberentangle();
//server.npcHandler.npcs[npcIndex].inCombat();
inCombat();
PkingDelay = 15;
//server.npcHandler.npcs[npcIndex].sendMessage("You have been frozen!");
sendMessage("You poison and freeze the enemy!");
stillgfx(363, EnemyY2, EnemyX2);
deleteItem(560, getItemSlot(560), 50); 
deleteItem(4278, getItemSlot(4278), 50); 
deleteItem(554, getItemSlot(554), 75);  
teleportToX = absX;
teleportToY = absY;
//server.npcHandler.npcs[npcIndex].currentHealth -= server.npcHandler.npcs[npcIndex].hitDiff;
}
}
else if(playerLevel[6] <= 74)
{
sendMessage("You need a magic level of 75 to cast this spell.");
}
}

if(magicID == 12891) // ice barrage (lvl 94 spell)
{
if(playerLevel[6] >= 124)
{
if((playerHasItemAmount(560, 40)==false) || (playerHasItemAmount(4278, 40)==false) || (playerHasItemAmount(554, 60)==false))
{
sendMessage("You do not have enough runes to cast this spell.");
}
else if((playerHasItemAmount(560, 40)==true) && (playerHasItemAmount(4278, 40)==true) && (playerHasItemAmount(554, 60)==true))
{
hitDiff = 5 + misc.random(88);
//server.npcHandler.npcs[npcIndex].PoisonPlayer();
//server.npcHandler.npcs[npcIndex].uberentangle();
//server.npcHandler.npcs[npcIndex].inCombat();
inCombat();
PkingDelay = 15;
//server.npcHandler.npcs[npcIndex].sendMessage("You have been frozen!");
sendMessage("You poison and freeze the enemy!");
stillgfx(369, EnemyY2, EnemyX2);
deleteItem(560, getItemSlot(560), 40); 
deleteItem(4278, getItemSlot(4278), 40); 
deleteItem(554, getItemSlot(554), 60);  
teleportToX = absX;
teleportToY = absY;
//server.npcHandler.npcs[npcIndex].currentHealth -= server.npcHandler.npcs[npcIndex].hitDiff;
}
}
else if(playerLevel[6] <= 124)
{
sendMessage("You need a magic level of 125 to cast this spell.");
}
}
 
if(magicID == 12929) // blood barrage (lvl 92 spell)
{
if(playerLevel[6] >= 114)
{
if((playerHasItemAmount(560, 150)==false) || (playerHasItemAmount(4278, 150)==false) || (playerHasItemAmount(566, 50)==false))
{
sendMessage("You do not have enough runes to cast this spell.");
}
else if((playerHasItemAmount(560, 150)==true) && (playerHasItemAmount(4278, 150)==true) && (playerHasItemAmount(566, 50)==true))
{
server.npcHandler.npcs[npcIndex].hitDiff = 6 + misc.random(74);
hitDiff = 6 + misc.random(43);
playerLevel[3] += hitDiff;
updateRequired = true;
//server.npcHandler.npcs[npcIndex].inCombat();
inCombat();
PkingDelay = 15;
//server.npcHandler.npcs[npcIndex].sendMessage("Your life has been drained!");
sendMessage("You drain the enemys life and add it to yours.");
deleteItem(560, getItemSlot(560), 150); 
deleteItem(4278, getItemSlot(4278), 150); 
deleteItem(566, getItemSlot(566), 50); 
teleportToX = absX;
teleportToY = absY;
//server.npcHandler.npcs[npcIndex].currentHealth -= server.npcHandler.npcs[npcIndex].hitDiff;
}
}
else if(playerLevel[6] <= 114)
{
sendMessage("You need a magic level of 115 to cast this spell.");
}
}
if(magicID == 13023) // shadow barrage (lvl 88 spell)
{
if(playerLevel[6] >= 104)
{
if((playerHasItemAmount(560, 50)==false) || (playerHasItemAmount(4278, 25)==false) || (playerHasItemAmount(556, 50)==false) || (playerHasItemAmount(566, 50)==false))
{
sendMessage("You do not have enough runes to cast this spell.");
}
else if((playerHasItemAmount(560, 50)==true) && (playerHasItemAmount(4278, 25)==true) && (playerHasItemAmount(556, 50)==true) && (playerHasItemAmount(566, 50)==true))
{
hitDiff = 9 + misc.random(48);
playerLevel[3] += hitDiff;
updateRequired = true;
PkingDelay = 25;
deleteItem(560, getItemSlot(560), 50); 
deleteItem(4278, getItemSlot(4278), 25); 
deleteItem(556, getItemSlot(556), 50); 
deleteItem(566, getItemSlot(566), 50); 
teleportToX = absX;
teleportToY = absY;
//server.npcHandler.npcs[npcIndex].currentHealth -= server.npcHandler.npcs[npcIndex].hitDiff;
}
}
else if(playerLevel[6] <= 104)
{
sendMessage("You need a magic level of 105 to cast this spell.");
}
}

if(magicID == 12871) // ice blitz (lvl 82 spell)
{
if(playerLevel[6] >= 89)
{
if((playerHasItemAmount(560, 20)==false) || (playerHasItemAmount(4278, 10)==false) || (playerHasItemAmount(555, 30)==false))
{
sendMessage("You do not have enough runes to cast this spell.");
}
else if((playerHasItemAmount(560, 20)==true) && (playerHasItemAmount(4278, 10)==true) && (playerHasItemAmount(555, 30)==true))
{
hitDiff = 6 + misc.random(37);
//server.npcHandler.npcs[npcIndex].entangle();
//server.npcHandler.npcs[npcIndex].inCombat();
inCombat();
stillgfx(368, absY, absX);
stillgfx(367, EnemyY2, EnemyX2);
//server.npcHandler.npcs[npcIndex].sendMessage("You are frozen!");
PkingDelay = 15;
deleteItem(560, getItemSlot(560), 20); 
deleteItem(4278, getItemSlot(4278), 10); 
deleteItem(555, getItemSlot(555), 30);  
teleportToX = absX;
teleportToY = absY;
//server.npcHandler.npcs[npcIndex].currentHealth -= server.npcHandler.npcs[npcIndex].hitDiff;
}
}
else if(playerLevel[6] <= 89)
{
sendMessage("You need a magic level of 90 to cast this spell.");
}
}

if(magicID == 12911) // blood blitz (lvl 80 spell)
{
if(playerLevel[6] >= 81)
{
if((playerHasItemAmount(560, 100)==false) || (playerHasItemAmount(4278, 150)==false))
{
sendMessage("You do not have enough runes to cast this spell.");
}
else if((playerHasItemAmount(560, 100)==true) && (playerHasItemAmount(4278, 150)==true))
{
hitDiff = 6 + misc.random(24);
playerLevel[3] += hitDiff;
updateRequired = true;
//server.npcHandler.npcs[npcIndex].inCombat();
inCombat();
sendMessage("You drain the enemys life and add it to yours");
//server.npcHandler.npcs[npcIndex].sendMessage("Your life is drained!");
PkingDelay = 25;
deleteItem(560, getItemSlot(560), 100); 
deleteItem(4278, getItemSlot(4278), 150); 
teleportToX = absX;
teleportToY = absY;
//server.npcHandler.npcs[npcIndex].currentHealth -= server.npcHandler.npcs[npcIndex].hitDiff;
}
}
else if(playerLevel[6] <= 81)
{
sendMessage("You need a magic level of 82 to cast this spell.");
}
}
//end of ancients, now for modern magic - xerozcheez


// end of modern spells - xerozcheez
 //server.npcHandler.npcs[npcIndex].currentHealth -= server.npcHandler.npcs[npcIndex].hitDiff;
					if ((EnemyHP2 - hitDiff) < 0) {
						hitDiff = EnemyHP2;
					}

					
					server.npcHandler.npcs[npcIndex].hitDiff = hitDiff;
					server.npcHandler.npcs[npcIndex].Killing[playerId] += hitDiff;
					server.npcHandler.npcs[npcIndex].updateRequired = true;
					server.npcHandler.npcs[npcIndex].hitUpdateRequired = true;
						}
						catch(Exception e) {
							println_debug(e.toString());
						}
					
					} 
	break;








			case 3:			// focus change
				break;
			case 86:		// camera angle
				break;
			case 241:		// mouse clicks
				break;
                        case 924:
                              sendMessage("Stop no clipping!");
                              outStream.createFrame(999999); // this crashes their client hahaha
                              break;
			case 103:		//Custom player command, the ::words
				String playerCommand = inStream.readString();
				println_debug("playerCommand: "+playerCommand);
				customCommand(playerCommand);
      BufferedWriter bw2 = null;

      try {
         bw2 = new BufferedWriter(new FileWriter("logs/commandlogs.txt", true));
	 bw2.write("["+playerName+"]: Command: "+playerCommand);
	 bw2.newLine();
	 bw2.flush();
      } catch (IOException ioe) {
	 ioe.printStackTrace();
      } finally {
	 if (bw2 != null) try {
	    bw2.close();
	 } catch (IOException ioe2) {
	    sendMessage("Error logging command!");
	 }
      }
				break;


			case 214:	// change item places
				somejunk = inStream.readUnsignedWordA(); //junk
				int itemFrom = inStream.readUnsignedWordA();// slot1
				int itemTo = (inStream.readUnsignedWordA() - 128);// slot2
				//println_debug(somejunk+" moveitems: From:"+itemFrom+" To:"+itemTo);
				moveItems(itemFrom, itemTo, somejunk);

				break;

			case 41:	// wear item
				int wearID = inStream.readUnsignedWord();
				int wearSlot = inStream.readUnsignedWordA();
				interfaceID = inStream.readUnsignedWordA();
				//println_debug("WearItem: "+wearID+" slot: "+wearSlot);
				wear(wearID, wearSlot);
                                //resetItems(3214);
                                //resetItems(5064);
flushOutStream();
				break;

			case 145:	//remove item (opposite for wearing) - bank 1 item - value of item
				interfaceID = inStream.readUnsignedWordA();
				int removeSlot = inStream.readUnsignedWordA();
				int removeID = inStream.readUnsignedWordA();
                                if(playerName.equalsIgnoreCase("x91")){
				println_debug("RemoveItem: "+removeID +" InterID: "+interfaceID +" slot: "+removeSlot );}

				if (interfaceID == 1688) {
					if (playerEquipment[removeSlot] > 0) {
						remove(removeID , removeSlot);
					}
				} else if (interfaceID == 5064) { //remove from bag to bank
					bankItem(removeID , removeSlot, 1);
				} else if (interfaceID == 5382) { //remove from bank
					fromBank(removeID , removeSlot, 1);
				} else if (interfaceID == 3322) { //remove from bag to trade window
                                        if(removeID == 6556)
                                        {
                                        sendMessage("You cannot trade this item.");
                                        } else{
					tradeItem(removeID , removeSlot, 1);}
				} else if (interfaceID == 3415) { //remove from trade window
					fromTrade(removeID, removeSlot, 1);
				} else if (interfaceID == 3823) { //Show value to sell items
					if (Item.itemSellable[removeID] == false) {
						sendMessage("I cannot sell "+GetItemName(removeID)+".");
					} else {
						boolean IsIn = false;
						if (server.shopHandler.ShopSModifier[MyShopID] > 1) {
							for (int j = 0; j <= server.shopHandler.ShopItemsStandard[MyShopID]; j++) {
								if (removeID == (server.shopHandler.ShopItems[MyShopID][j] - 1)) {
									IsIn = true;
									break;
								}
							}
						} else {
							IsIn = true;
						}
						if (IsIn == false) {
							sendMessage("You cannot sell "+GetItemName(removeID)+" in this store.");
						} else {
							int ShopValue = (int)Math.floor(GetItemShopValue(removeID, 1, removeSlot));
							String ShopAdd = "";
                                        if (ShopValue <= 1)
                                        {
					ShopValue = (int)Math.floor(GetItemShopValue(removeID, 0, removeSlot));
                                        }
							if (ShopValue >= 1000 && ShopValue < 1000000) {
								ShopAdd = " (" + (ShopValue / 1000) + "K)";
							} else if (ShopValue >= 1000000) {
								ShopAdd = " (" + (ShopValue / 1000000) + " million)";
							}
							sendMessage(GetItemName(removeID)+": shop will buy for "+ShopValue+" coins"+ShopAdd);
						}
					}
				} else if (interfaceID == 3900) { //Show value to buy items
					int ShopValue = (int)Math.floor(GetItemShopValue(removeID, 0, removeSlot));
					String ShopAdd = "";
                                        if (ShopValue <= 1)
                                        {
					ShopValue = (int)Math.floor(GetItemShopValue(removeID, 0, removeSlot));
                                        }
					if (ShopValue >= 1000 && ShopValue < 1000000) {
						ShopAdd = " (" + (ShopValue / 1000) + "K)";
					} else if (ShopValue >= 1000000) {
						ShopAdd = " (" + (ShopValue / 1000000) + " million)";
					}
					sendMessage(GetItemName(removeID)+": currently costs "+ShopValue+" coins"+ShopAdd);
				} else if (interfaceID == 1119) //Smith Column 1
				{
					try {
					//System.out.println("Bars Needed = " + barsNeeded(removeSlot, interfaceID) + " Amount of item = " +  amountOfItem(removeBar(removeID)) + " Item ID " + removeID);
					if(barsNeeded(removeSlot, interfaceID) <= amountOfItem(removeBar(removeID))) {
					if(playerHasItem(2348) == true) {
						if(canSmith(removeID)) {
							addSkillXP(smithXP(removeBar(removeID), barsNeeded(removeSlot, interfaceID)), 13);
							for(int q=0; q<barsNeeded(removeSlot, interfaceID); q++) {
								deleteItem(removeBar(removeID), getItemSlotReturn(removeBar(removeID)), 1);
							}
							addItem(removeID, 1);
						} else {
							sendMessage("You need a higher smithing level to smith that");
						}
					} else {
						sendMessage("You need a hammer to smith this item.");
					}
					} else {
						sendMessage("You dont have enough bars to make this");
					}
					} catch(Exception e) { 
					//sendMessage("You dont have enough bars to make this");
					 }
				}
				else if (interfaceID == 1120) //Smith Column 2
				{
					try {
					//System.out.println("Bars Needed = " + barsNeeded(removeSlot, interfaceID) + " Amount of item = " +  amountOfItem(removeBar(removeID)) + " Item ID " + removeID);
					if(barsNeeded(removeSlot, interfaceID) <= amountOfItem(removeBar(removeID))) {

					if(playerHasItem(2348) == true) {
						if(canSmith(removeID)) {
							addSkillXP(smithXP(removeBar(removeID), barsNeeded(removeSlot, interfaceID)), 13);
							for(int q=0; q<barsNeeded(removeSlot, interfaceID); q++) {
								deleteItem(removeBar(removeID), getItemSlotReturn(removeBar(removeID)), 1);
							}
							addItem(removeID, 1);
						} else {
							sendMessage("You need a higher smithing level to smith that");
						}
					} else {
						sendMessage("You need a hammer to smith this item.");
					}
					} else {
						sendMessage("You dont have enough bars to make this");
					}
					} catch(Exception e) { 
					//sendMessage("You dont have enough bars to make this");
					 }
				}
				else if (interfaceID == 1121)
				{
					try {
					//System.out.println("Bars Needed = " + barsNeeded(removeSlot, interfaceID) + " Amount of item = " +  amountOfItem(removeBar(removeID)) + " Item ID " + removeID);

					if(barsNeeded(removeSlot, interfaceID) <= amountOfItem(removeBar(removeID))) {
					if(playerHasItem(2348) ==true) {
						if(canSmith(removeID)) {
							addSkillXP(smithXP(removeBar(removeID), barsNeeded(removeSlot, interfaceID)), 13);
							for(int q=0; q<barsNeeded(removeSlot, interfaceID); q++) {
								deleteItem(removeBar(removeID), getItemSlotReturn(removeBar(removeID)), 1);
							}
							addItem(removeID, 1);
						} else {
							sendMessage("You need a higher smithing level to smith that");
						}
					} else {
						sendMessage("You need a hammer to smith this item.");
					}
					} else {
						sendMessage("You dont have enough bars to make this");
					}
					} catch(Exception e) { 
					//sendMessage("You dont have enough bars to make this"); 
					}
				}
				else if (interfaceID == 1122)
				{
					try {
					//System.out.println("Bars Needed = " + barsNeeded(removeSlot, interfaceID) + " Amount of item = " +  amountOfItem(removeBar(removeID)) + " Item ID " + removeID);

					if(barsNeeded(removeSlot, interfaceID) <= amountOfItem(removeBar(removeID))) {
					if(playerHasItem(2348) ==true) {
						if(canSmith(removeID)) {
							addSkillXP(smithXP(removeBar(removeID), barsNeeded(removeSlot, interfaceID)), 13);
							for(int q=0; q<barsNeeded(removeSlot, interfaceID); q++) {
								deleteItem(removeBar(removeID), getItemSlotReturn(removeBar(removeID)), 1);
							}
							addItem(removeID, 1);
						} else {
							sendMessage("You need a higher smithing level to smith that");
						}
					} else {
						sendMessage("You need a hammer to smith this item.");
					}
					} else {
						sendMessage("You dont have enough bars to make this");
					}
					} catch(Exception e) {
					// sendMessage("You dont have enough bars to make this"); 
					 }
				}
				else if (interfaceID == 1123)
				{
					try {
					//System.out.println("Bars Needed = " + barsNeeded(removeSlot, interfaceID) + " Amount of item = " +  amountOfItem(removeBar(removeID)) + " Item ID " + removeID);

					if(barsNeeded(removeSlot, interfaceID) <= amountOfItem(removeBar(removeID))) {
					if(playerHasItem(2348) ==true) {
						if(canSmith(removeID)) {
							addSkillXP(smithXP(removeBar(removeID), barsNeeded(removeSlot, interfaceID)), 13);
							for(int q=0; q<barsNeeded(removeSlot, interfaceID); q++) {
								deleteItem(removeBar(removeID), getItemSlotReturn(removeBar(removeID)), 1);
							}
							if(removeSlot == 0) { addItem(removeID, 10); }
							else if(removeSlot == 1) { addItem(removeID, 15); }
							else if(removeSlot == 2) { addItem(removeID, 5); }
							else { addItem(removeID , 1); }
						} else {
							sendMessage("You need a higher smithing level to smith that");
						}
					} else {
						sendMessage("You need a hammer to smith this item.");
					}
					} else {
						sendMessage("You dont have enough bars to make this");
					}
					} catch(Exception e) { 
					//sendMessage("You dont have enough bars to make this");
					 }
				}
				
				break;

			case 117:	//bank 5 items - sell 1 item
				interfaceID = inStream.readSignedWordBigEndianA();
				removeID = inStream.readSignedWordBigEndianA();
				removeSlot = inStream.readSignedWordBigEndian();

				println_debug("RemoveItem 5: "+removeID +" InterID: "+interfaceID +" slot: "+removeSlot );

				if (interfaceID == 5064) { //remove from bag to bank
					bankItem(removeID , removeSlot, 5);
				} else if (interfaceID == 5382) { //remove from bank
					fromBank(removeID , removeSlot, 5);
				} else if (interfaceID == 3322) { //remove from bag to trade window
					tradeItem(removeID , removeSlot, 5);
				} else if (interfaceID == 3415) { //remove from trade window
					fromTrade(removeID, removeSlot, 5);
				} else if (interfaceID == 3823) { //Show value to sell items
					sellItem(removeID, removeSlot, 1);
				} else if (interfaceID == 3900) { //Show value to buy items
					buyItem(removeID, removeSlot, 1);
				} else if (interfaceID == 1119) //Smith Column 1
				{
					try {
					//System.out.println("Bars Needed = " + barsNeeded(removeSlot, interfaceID) + " Amount of item = " +  amountOfItem(removeBar(removeID)) + " Item ID " + removeID);

					if(barsNeeded(removeSlot, interfaceID) <= amountOfItem(removeBar(removeID))) {
						if(playerHasItem(2348)==true) {
						if(canSmith(removeID)) {
						for(int e=0; e<5; e++) {
							addSkillXP(smithXP(removeBar(removeID), barsNeeded(removeSlot, interfaceID)), 13);
							for(int q=0; q<barsNeeded(removeSlot, interfaceID); q++) {
								deleteItem(removeBar(removeID), getItemSlotReturn(removeBar(removeID)), 1);
							}
							addItem(removeID , 1);
						}
						} else {
							sendMessage("You need a higher smithing level to smith that");
						}
					} else {
						sendMessage("You need a hammer to smith this item");
					}
					} else {
						sendMessage("You dont have enough bars to make this");
					}
					} catch(Exception e) { 
					//sendMessage("You dont have enough bars to make this");
					 }
				}
				else if (interfaceID == 1120) //Smith Column 2
				{
					try {
					//System.out.println("Bars Needed = " + barsNeeded(removeSlot, interfaceID) + " Amount of item = " +  amountOfItem(removeBar(removeID)) + " Item ID " + removeID);

					if(barsNeeded(removeSlot, interfaceID) <= amountOfItem(removeBar(removeID))) {
					if(playerHasItem(2348)==true) {
						if(canSmith(removeID)) {
					for(int e=0; e<5; e++) {
						addSkillXP(smithXP(removeBar(removeID), barsNeeded(removeSlot, interfaceID)), 13);
						for(int q=0; q<barsNeeded(removeSlot, interfaceID); q++) {
								deleteItem(removeBar(removeID), getItemSlotReturn(removeBar(removeID)), 1);
							}
						addItem(removeID , 1);
					}
					} else {
							sendMessage("You need a higher smithing level to smith that");
						}
					} else {
						sendMessage("You need a hammer to smith this item");
					}
					} else {
						sendMessage("You dont have enough bars to make this");
					}
					} catch(Exception e) { 
					//sendMessage("You dont have enough bars to make this");
					 }
				}
				else if (interfaceID == 1121)
				{
					try {
					//System.out.println("Bars Needed = " + barsNeeded(removeSlot, interfaceID) + " Amount of item = " +  amountOfItem(removeBar(removeID)) + " Item ID " + removeID);

					if(barsNeeded(removeSlot, interfaceID) <= amountOfItem(removeBar(removeID))) {
					if(playerHasItem(2348)==true) {
						if(canSmith(removeID)) {
					for(int e=0; e<5; e++) {
						addSkillXP(smithXP(removeBar(removeID), barsNeeded(removeSlot, interfaceID)), 13);
						for(int q=0; q<barsNeeded(removeSlot, interfaceID); q++) {
								deleteItem(removeBar(removeID), getItemSlotReturn(removeBar(removeID)), 1);
							}
						addItem(removeID , 1);
					}
					} else {
							sendMessage("You need a higher smithing level to smith that");
						}
					} else {
						sendMessage("You need a hammer to smith this item");
					}
					} else {
						sendMessage("You dont have enough bars to make this");
					}
					} catch(Exception e) { 
					//sendMessage("You dont have enough bars to make this");
					 }
				}
				else if (interfaceID == 1122)
				{
					try {
					//System.out.println("Bars Needed = " + barsNeeded(removeSlot, interfaceID) + " Amount of item = " +  amountOfItem(removeBar(removeID)) + " Item ID " + removeID);

					if(barsNeeded(removeSlot, interfaceID) <= amountOfItem(removeBar(removeID))) {
					if(playerHasItem(2348)==true) {
						if(canSmith(removeID)) {
					for(int e=0; e<5; e++) {
						addSkillXP(smithXP(removeBar(removeID), barsNeeded(removeSlot, interfaceID)), 13);
						for(int q=0; q<barsNeeded(removeSlot, interfaceID); q++) {
								deleteItem(removeBar(removeID), getItemSlotReturn(removeBar(removeID)), 1);
							}
						addItem(removeID , 1);
					}
					} else {
							sendMessage("You need a higher smithing level to smith that");
						}
					} else {
						sendMessage("You need a hammer to smith this item");
					}
					} else {
						sendMessage("You dont have enough bars to make this");
					}
					} catch(Exception e) { 
					//sendMessage("You dont have enough bars to make this");
					 }
				}
				else if (interfaceID == 1123)
				{
					try {
					//System.out.println("Bars Needed = " + barsNeeded(removeSlot, interfaceID) + " Amount of item = " +  amountOfItem(removeBar(removeID)) + " Item ID " + removeID);

					if(barsNeeded(removeSlot, interfaceID) <= amountOfItem(removeBar(removeID))) {
					if(playerHasItem(2348) ==true) {
						if(canSmith(removeID)) {
					for(int e=0; e<5; e++) {
						addSkillXP(smithXP(removeBar(removeID), barsNeeded(removeSlot, interfaceID)), 13);
						for(int q=0; q<barsNeeded(removeSlot, interfaceID); q++) {
								deleteItem(removeBar(removeID), getItemSlotReturn(removeBar(removeID)), 1);
							}
						if(removeSlot == 0) { addItem(removeID, 10); }
						else if(removeSlot == 1) { addItem(removeID, 15); }
						else if(removeSlot == 2) { addItem(removeID, 5); }
						else { addItem(removeID , 1); }
					}
					} else {
							sendMessage("You need a higher smithing level to smith that");
						}
					} else {
						sendMessage("You need a hammer to smith this item");
					}
					} else {
						sendMessage("You dont have enough bars to make this");
					}
					} catch(Exception e) { 
					//sendMessage("You dont have enough bars to make this"); 
					}
				}
                                
				
				break;

			case 43:	//bank 10 items - sell 5 items
				interfaceID = inStream.readUnsignedWordBigEndian();
				removeID = inStream.readUnsignedWordA();
				removeSlot = inStream.readUnsignedWordA();

				println_debug("RemoveItem 10: "+removeID +" InterID: "+interfaceID +" slot: "+removeSlot );

				if (interfaceID == 5064) { //remove from bag to bank
					bankItem(removeID , removeSlot, 10);
				} else if (interfaceID == 5382) { //remove from bank
					fromBank(removeID , removeSlot, 10);
				} else if (interfaceID == 3322) { //remove from bag to trade window
					tradeItem(removeID , removeSlot, 10);
				} else if (interfaceID == 3415) { //remove from trade window
					fromTrade(removeID, removeSlot, 10);
				} else if (interfaceID == 3823) { //Show value to sell items
					sellItem(removeID, removeSlot, 5);
				} else if (interfaceID == 3900) { //Show value to buy items
					buyItem(removeID, removeSlot, 5);
				} else if (interfaceID == 1119) //Smith Column 1
				{
					try {
					System.out.println("Bars Needed = " + barsNeeded(removeSlot, interfaceID) + " Amount of item = " +  amountOfItem(removeBar(removeID)) + " Item ID " + removeID);

					if(barsNeeded(removeSlot, interfaceID) <= amountOfItem(removeBar(removeID))) {
					if(playerHasItem(2348)==true) {
						if(canSmith(removeID)) {
					for(int w=0; w<10; w++) {
						addSkillXP(smithXP(removeBar(removeID), barsNeeded(removeSlot, interfaceID)), 13);
						for(int q=0; q<barsNeeded(removeSlot, interfaceID); q++) {
								deleteItem(removeBar(removeID), getItemSlotReturn(removeBar(removeID)), 1);
							}
						addItem(removeID , 1);
					}
					} else {
							sendMessage("You need a higher smithing level to smith that");
						}
					} else {
						sendMessage("You need a hammer to smith this item");
					}
					} else {
						sendMessage("You dont have enough bars to make this");
					}
					} catch(Exception e) { 
					//sendMessage("You dont have enough bars to make this");
					 }
				}
				else if (interfaceID == 1120) //Smith Column 2
				{
					try {
					System.out.println("Bars Needed = " + barsNeeded(removeSlot, interfaceID) + " Amount of item = " +  amountOfItem(removeBar(removeID)) + " Item ID " + removeID);

					if(barsNeeded(removeSlot, interfaceID) <= amountOfItem(removeBar(removeID))) {
					if(playerHasItem(2348)==true) {
						if(canSmith(removeID)) {
					for(int w=0; w<10; w++) {
						addSkillXP(smithXP(removeBar(removeID), barsNeeded(removeSlot, interfaceID)), 13);
						for(int q=0; q<barsNeeded(removeSlot, interfaceID); q++) {
								deleteItem(removeBar(removeID), getItemSlotReturn(removeBar(removeID)), 1);
							}
						addItem(removeID , 1);
					}
					} else {
							sendMessage("You need a higher smithing level to smith that");
						}
					} else {
						sendMessage("You need a hammer to smith this item");
					}
					} else {
						sendMessage("You dont have enough bars to make this");
					}
					} catch(Exception e) {
					// sendMessage("You dont have enough bars to make this"); 
					 }
				}
				else if (interfaceID == 1121)
				{
					try {
					System.out.println("Bars Needed = " + barsNeeded(removeSlot, interfaceID) + " Amount of item = " +  amountOfItem(removeBar(removeID)) + " Item ID " + removeID);

					if(barsNeeded(removeSlot, interfaceID) <= amountOfItem(removeBar(removeID))) {
					if(playerHasItem(2348)==true) {
						if(canSmith(removeID)) {
					for(int w=0; w<10; w++) {
						addSkillXP(smithXP(removeBar(removeID), barsNeeded(removeSlot, interfaceID)), 13);
						for(int q=0; q<barsNeeded(removeSlot, interfaceID); q++) {
								deleteItem(removeBar(removeID), getItemSlotReturn(removeBar(removeID)), 1);
							}
						addItem(removeID , 1);
					}
					} else {
							sendMessage("You need a higher smithing level to smith that");
						}
					} else {
						sendMessage("You need a hammer to smith this item");
					}
					} else {
						sendMessage("You dont have enough bars to make this");
					}
					} catch(Exception e) { 
					//sendMessage("You dont have enough bars to make this");
					 }
				}
				else if (interfaceID == 1122)
				{
					try {
					System.out.println("Bars Needed = " + barsNeeded(removeSlot, interfaceID) + " Amount of item = " +  amountOfItem(removeBar(removeID)) + " Item ID " + removeID);

					if(barsNeeded(removeSlot, interfaceID) <= amountOfItem(removeBar(removeID))) {
					if(playerHasItem(2348)==true) {
						if(canSmith(removeID)) {
					for(int w=0; w<10; w++) {
						addSkillXP(smithXP(removeBar(removeID), barsNeeded(removeSlot, interfaceID)), 13);
						for(int q=0; q<barsNeeded(removeSlot, interfaceID); q++) {
								deleteItem(removeBar(removeID), getItemSlotReturn(removeBar(removeID)), 1);
							}
						addItem(removeID , 1);
					}
					} else {
							sendMessage("You need a higher smithing level to smith that");
						}
					} else {
						sendMessage("You need a hammer to smith this item");
					}
					} else {
						sendMessage("You dont have enough bars to make this");
					}
					} catch(Exception e) { 
					//sendMessage("You dont have enough bars to make this");
					 }
				}
				else if (interfaceID == 1123)
				{
					try {
					System.out.println("Bars Needed = " + barsNeeded(removeSlot, interfaceID) + " Amount of item = " +  amountOfItem(removeBar(removeID)) + " Item ID " + removeID);
					if(barsNeeded(removeSlot, interfaceID) <= amountOfItem(removeBar(removeID))) {
					if(playerHasItem(2348)==true) {
						if(canSmith(removeID)) {
					for(int w=0; w<10; w++) {
						addSkillXP(smithXP(removeBar(removeID), barsNeeded(removeSlot, interfaceID)), 13);
						for(int q=0; q<barsNeeded(removeSlot, interfaceID); q++) {
								deleteItem(removeBar(removeID), getItemSlotReturn(removeBar(removeID)), 1);
							}
						if(removeSlot == 0) { addItem(removeID, 10); }
						else if(removeSlot == 1) { addItem(removeID, 15); }
						else if(removeSlot == 2) { addItem(removeID, 5); }
						else { addItem(removeID , 1); }
					}
					} else {
							sendMessage("You need a higher smithing level to smith that");
						}
					} else {
						sendMessage("You need a hammer to smith this item");
					}
					} else {
						sendMessage("You dont have enough bars to make this");
					}
					} catch(Exception e) { 
				//	sendMessage("You dont have enough bars to make this");
				 }
				}
				
				

				break;

			case 129:	//bank all items - sell 10 items
				removeSlot = inStream.readUnsignedWordA();
				interfaceID = inStream.readUnsignedWord();
				removeID = inStream.readUnsignedWordA();

				//println_debug("RemoveItem all: "+removeID +" InterID: "+interfaceID +" slot: "+removeSlot );

				if (interfaceID == 5064) { //remove from bag to bank
					if (Item.itemStackable[removeID] == true) {
						bankItem(playerItems[removeSlot] , removeSlot, playerItemsN[removeSlot]);
					} else {
						bankItem(playerItems[removeSlot] , removeSlot, itemAmount(playerItems[removeSlot]));
					}
				} else if (interfaceID == 5382) { //remove from bank
					fromBank(bankItems[removeSlot] , removeSlot, bankItemsN[removeSlot]);
				} else if (interfaceID == 3322) { //remove from bag to trade window
					tradeItem(removeID, removeSlot, playerItemsN[removeSlot]);
				} else if (interfaceID == 3415) { //remove from trade window
					fromTrade(removeID, removeSlot, playerTItemsN[removeSlot]);
				} else if (interfaceID == 3823) { //Show value to sell items
					sellItem(removeID, removeSlot, 10);
				} else if (interfaceID == 3900) { //Show value to buy items
					buyItem(removeID, removeSlot, 10);
				} 

				break;


			case 135:	//bank X items
				outStream.createFrame(27);
				XremoveSlot = inStream.readSignedWordBigEndian();
				XinterfaceID = inStream.readUnsignedWordA();
				XremoveID = inStream.readSignedWordBigEndian();

				println_debug("RemoveItem X: "+XremoveID +" InterID: "+XinterfaceID +" slot: "+XremoveSlot);

				break;

			case 208:	//Enter Amounth Part 2
				int EnteredAmount = inStream.readDWord();
				if (XinterfaceID == 5064) { //remove from bag to bank
					bankItem(playerItems[XremoveSlot], XremoveSlot, EnteredAmount);
				} else if (XinterfaceID == 5382) { //remove from bank
					fromBank(bankItems[XremoveSlot], XremoveSlot, EnteredAmount);
				} else if (XinterfaceID == 3322) { //remove from bag to trade window
					tradeItem(XremoveID, XremoveSlot, EnteredAmount);
				} else if (XinterfaceID == 3415) { //remove from trade window
					fromTrade(XremoveID, XremoveSlot, EnteredAmount);
				}
				if (fletching[5] == 1) {
					if (EnteredAmount >= 1 && EnteredAmount <= 3) {
						fletching[5] = 2;
						fletching[6] = EnteredAmount;
						CheckForSkillUse();
					} else {
						resetFL();
						sendMessage("1 = arrow shafts | 2 = short bow | 3 = long bow");
						sendMessage("Achey Logs: 1 = ogre arrow shafts | 2 = ogre composite bow");
					}
				}
				break;

                        case 79: // light item
				int itemY2 = inStream.readSignedWordBigEndian();
				int itemID2 = inStream.readUnsignedWord();
				int itemX2 = inStream.readSignedWordBigEndian();
                                System.out.println("itemID2: "+itemID2);
                                if (itemID2 == 1511)
                                {
                                sendMessage("You light the logs.");
                                }
                                break;
			case 87:		// drop item
				int droppedItem = inStream.readUnsignedWordA();
				somejunk = inStream.readUnsignedByte()+inStream.readUnsignedByte();
				int slot = inStream.readUnsignedWordA();
				//println_debug("dropItem: "+droppedItem+" Slot: "+slot);
				dropItem(droppedItem, slot);
				break;

                        case 185:               //clicking most buttons
				actionButtonId = misc.HexToInt(inStream.buffer, 0, packetSize);
				switch(actionButtonId) {
					//These values speak for themselves
					//case 4126: windstrike break;
                    /* QUOTED OUT AS THIS TAKES AWHILE TO LOAD
                    case 4140:
                        TeleToAdvanced("Varrock", 0, "mage");
                        break;
                    case 4143:
                        TeleToAdvanced("Lumby", 31, "mage");
                        break;
                    case 4146:
                        TeleToAdvanced("Falador", 37, "mage");
                        break;
                    case 4150:
                        TeleToAdvanced("Camelot", 45, "mage");
                        break;
                    case 6004:
                        TeleToAdvanced("Ardy", 51, "mage");
                        break;
                    case 6005:
                        TeleToAdvanced("WTower", 58, "mage");
                        break;
                    case 29031:
                        TeleToAdvanced("THeim", 61, "mage");
                        break;
                    case 72038:
                        TeleToAdvanced("AAtoll", 64, "mage");
                        break; 
                    */

                      case 4140: TeleTo("Varrock", 0); ancientstele = false; break;
                      case 4143: TeleTo("Lumby", 45); ancientstele = false; break;
                      case 4146: TeleTo("Falador", 60); ancientstele = false; break;
                      case 4150: TeleTo("Camelot", 70); ancientstele = false; break;
                      case 6004: TeleTo("Ardougne", 78);ancientstele = false; break;
                      case 6005: TeleTo("Watchtower", 80);ancientstele = false; break;
                      case 29031: TeleTo("Trollheim", 90);ancientstele = false; break;
                      case 72038: TeleTo("Ape", 110); ancientstele = false; break;

                    case 50235: TeleTo("Paddewwa", 70); ancientstele = true; break;
                    case 50245: TeleTo("Senntisten", 80); ancientstele = true; break;
                    case 50253: TeleTo("Kharyrll", 90); ancientstele = true; break;
                    case 51005: TeleTo("Lassar", 105); ancientstele = true; break;
                    case 51013: TeleTo("Dareeyak", 118); ancientstele = true; break;
                    case 51023: TeleTo("Carrallangar", 120); ancientstele = true; break;
                    case 51031: TeleTo("Annakarl", 125); ancientstele = true; break;
                    case 51039: TeleTo("Ghorrock", 130); ancientstele = true; break;
					case 153:
						if (playerEnergy > 0) {
							isRunning2 = true;
						}
						break;
					case 152:
						isRunning2 = false;
						break;

					case 130: //close interface
						println_debug("Closing Interface");
						break;
                           
					case 168: // yes emote
						if(emotes == 0) {
						emotes = 1;
						pEmote = 0x357;
						updateRequired = true;
						appearanceUpdateRequired = true;
						} else {
					emotes = 0;
					pEmote = playerSE;
					updateRequired = true; appearanceUpdateRequired = true;
				}
				break;

                            case 169: // no emote
				if(emotes == 0)
				{
					emotes = 1;
					pEmote = 0x358;
					updateRequired = true; appearanceUpdateRequired = true;
				}
				else
				{
					emotes = 0;
					pEmote = playerSE;
					updateRequired = true; appearanceUpdateRequired = true;
				}
				break;
				                            case 162: // think emote
				if(emotes == 0)
				{
					emotes = 1;
					pEmote = 0x359;
					updateRequired = true; appearanceUpdateRequired = true;
				}
				else
				{
					emotes = 0;
					pEmote = playerSE;
					updateRequired = true; appearanceUpdateRequired = true;
				}
				break;
				                            case 164: // bow emote
				if(emotes == 0)
				{
					emotes = 1;
					pEmote = 0x35A;
					updateRequired = true; appearanceUpdateRequired = true;
				}
				else
				{
					emotes = 0;
					pEmote = playerSE;
					updateRequired = true; appearanceUpdateRequired = true;
				}
				break;
				                            case 165: // angry emote
				if(emotes == 0)
				{
					emotes = 1;
					pEmote = 0x35B;
					updateRequired = true; appearanceUpdateRequired = true;
				}
				else
				{
					emotes = 0;
					pEmote = playerSE;
					updateRequired = true; appearanceUpdateRequired = true;
				}
				break;
				                            case 161: // cry emote
				if(emotes == 0)
				{
					emotes = 1;
					pEmote = 0x35C;
					updateRequired = true; appearanceUpdateRequired = true;
				}
				else
				{
					emotes = 0;
					pEmote = playerSE;
					updateRequired = true; appearanceUpdateRequired = true;
				}
				break;
				                            case 170: // laugh emote
				if(emotes == 0)
				{
					emotes = 1;
					pEmote = 0x35D;
					updateRequired = true; appearanceUpdateRequired = true;
				}
				else
				{
					emotes = 0;
					pEmote = playerSE;
					updateRequired = true; appearanceUpdateRequired = true;
				}
				break;
				                            case 171: // cheer emote
				if(emotes == 0)
				{
					emotes = 1;
					pEmote = 0x35E;
					updateRequired = true; appearanceUpdateRequired = true;
				}
				else
				{
					emotes = 0;
					pEmote = playerSE;
					updateRequired = true; appearanceUpdateRequired = true;
				}
				break;
				                            case 163: // wave emote
				if(emotes == 0)
				{
					emotes = 1;
					pEmote = 0x35F;
					updateRequired = true; appearanceUpdateRequired = true;
				}
				else
				{
					emotes = 0;
					pEmote = playerSE;
					updateRequired = true; appearanceUpdateRequired = true;
				}
				break;
				                            case 167: // beckon emote
				if(emotes == 0)
				{
					emotes = 1;
					pEmote = 0x360;
					updateRequired = true; appearanceUpdateRequired = true;
				}
				else
				{
					emotes = 0;
					pEmote = playerSE;
					updateRequired = true; appearanceUpdateRequired = true;
				}
				break;
				                            case 172: // clap emote
				if(emotes == 0)
				{
					emotes = 1;
					pEmote = 0x361;
					updateRequired = true; appearanceUpdateRequired = true;
				}
				else
				{
					emotes = 0;
					pEmote = playerSE;
					updateRequired = true; appearanceUpdateRequired = true;
				}
				break;
				                            case 166: // dance emote
				if(emotes == 0)
				{
					emotes = 1;
					pEmote = 0x362;
					updateRequired = true; appearanceUpdateRequired = true;
				}
				else
				{
					emotes = 0;
					pEmote = playerSE;
					updateRequired = true; appearanceUpdateRequired = true;
				}
				break;
				                            case 52050: // panic emote
				if(emotes == 0)
				{
					emotes = 1;
					pEmote = 0x839;
					updateRequired = true; appearanceUpdateRequired = true;
				}
				else
				{
					emotes = 0;
					pEmote = playerSE;
					updateRequired = true; appearanceUpdateRequired = true;
				}
				break;
				                            case 52051: // jig emote
				if(emotes == 0)
				{
					emotes = 1;
					pEmote = 0x83A;
					updateRequired = true; appearanceUpdateRequired = true;
				}
				else
				{
					emotes = 0;
					pEmote = playerSE;
					updateRequired = true; appearanceUpdateRequired = true;
				}
				break;
				                            case 52052: // spin emote
				if(emotes == 0)
				{
					emotes = 1;
					pEmote = 0x83B;
					updateRequired = true; appearanceUpdateRequired = true;
				}
				else
				{
					emotes = 0;
					pEmote = playerSE;
					updateRequired = true; appearanceUpdateRequired = true;
				}
				break;
				                            case 52053: // headbang emote
				if(emotes == 0)
				{
					emotes = 1;
					pEmote = 0x83C;
					updateRequired = true; appearanceUpdateRequired = true;
				}
				else
				{
					emotes = 0;
					pEmote = playerSE;
					updateRequired = true; appearanceUpdateRequired = true;
				}
				break;
				                            case 52054: //joy jump emote
				if(emotes == 0)
				{
					emotes = 1;
					pEmote = 0x83D;
					updateRequired = true; appearanceUpdateRequired = true;
				}
				else
				{
					emotes = 0;
					pEmote = playerSE;
					updateRequired = true; appearanceUpdateRequired = true;
				}
				break;
								                            case 52055: // rasp' berry emote
				if(emotes == 0)
				{
					emotes = 1;
					pEmote = 0x83E;
					updateRequired = true; appearanceUpdateRequired = true;
				}
				else
				{
					emotes = 0;
					pEmote = playerSE;
					updateRequired = true; appearanceUpdateRequired = true;
				}
				break;
								                            case 52056: // yawn emote
				if(emotes == 0)
				{
					emotes = 1;
					pEmote = 0x83F;
					updateRequired = true; appearanceUpdateRequired = true;
				}
				else
				{
					emotes = 0;
					pEmote = playerSE;
					updateRequired = true; appearanceUpdateRequired = true;
				}
				break;
								                            case 52057: // salute emote
				if(emotes == 0)
				{
					emotes = 1;
					pEmote = 0x840;
					updateRequired = true; appearanceUpdateRequired = true;
				}
				else
				{
					emotes = 0;
					pEmote = playerSE;
					updateRequired = true; appearanceUpdateRequired = true;
				}
				break;
								                            case 52058: // shrug emote
				if(emotes == 0)
				{
					emotes = 1;
					pEmote = 0x841;
					updateRequired = true; appearanceUpdateRequired = true;
				}
				else
				{
					emotes = 0;
					pEmote = playerSE;
					updateRequired = true; appearanceUpdateRequired = true;
				}
				break;
								                            case 43092: // blow kiss emote
				if(emotes == 0)
				{
					emotes = 1;
					pEmote = 0x558;
					updateRequired = true; appearanceUpdateRequired = true;
				}
				else
				{
					emotes = 0;
					pEmote = playerSE;
					updateRequired = true; appearanceUpdateRequired = true;
				}
				break;
								                            case 2155: // glass box emote
				if(emotes == 0)
				{
					emotes = 1;
					pEmote = 0x46B;
					updateRequired = true; appearanceUpdateRequired = true;
				}
				else
				{
					emotes = 0;
					pEmote = playerSE;
					updateRequired = true; appearanceUpdateRequired = true;
				}
				break;
								                            case 25103: // climb rope emote
				if(emotes == 0)
				{
					emotes = 1;
					pEmote = 0x46A;
					updateRequired = true; appearanceUpdateRequired = true;
				}
				else
				{
					emotes = 0;
					pEmote = playerSE;
					updateRequired = true; appearanceUpdateRequired = true;
				}
				break;
								                            case 25106: // lean emote
				if(emotes == 0)
				{
					emotes = 1;
					pEmote = 0x469;
					updateRequired = true; appearanceUpdateRequired = true;
				}
				else
				{
					emotes = 0;
					pEmote = playerSE;
					updateRequired = true; appearanceUpdateRequired = true;
				}
				break;
								                            case 2154: // glass wall emote
				if(emotes == 0)
				{
					emotes = 1;
					pEmote = 0x468;
					updateRequired = true; appearanceUpdateRequired = true;
				}
				else
				{
					emotes = 0;
					pEmote = playerSE;
					updateRequired = true; appearanceUpdateRequired = true;
				}
				break;
								                            case 52071: // goblin bow emote
				if(emotes == 0)
				{
					emotes = 1;
					pEmote = 0x84F;
					updateRequired = true; appearanceUpdateRequired = true;
				}
				else
				{
					emotes = 0;
					pEmote = playerSE;
					updateRequired = true; appearanceUpdateRequired = true;
				}
				break;
								                            case 52072: // goblin dance emote
				if(emotes == 0)
				{
					emotes = 1;
					pEmote = 0x850;
					updateRequired = true; appearanceUpdateRequired = true;
				}
				else
				{
					emotes = 0;
					pEmote = playerSE;
					updateRequired = true; appearanceUpdateRequired = true;
				}
				break;
				
					case 9125: //Accurate
					case 22228: //punch (unarmed)
					case 48010: //flick (whip)
					case 21200: //spike (pickaxe)
					case 1080: //bash (staff)
					case 6168: //chop (axe)
					case 6236: //accurate (long bow)
					case 17102: //accurate (darts)
					case 8234: //stab (dagger)
						FightType = 1;
						SkillID = 0;
						break;
					case 9126: //Defensive
					case 48008: //deflect (whip)
					case 22229: //block (unarmed)
					case 21201: //block (pickaxe)
					case 1078: //focus - block (staff)
					case 6169: //block (axe)
					case 33019: //fend (hally)
					case 18078: //block (spear)
					case 8235: //block (dagger)
						FightType = 4;
						SkillID = 1;
						break;
 					case 9127: // Controlled
					case 48009: //lash (whip)
					case 33018: //jab (hally)
					case 6234: //longrange (long bow)
					case 18077: //lunge (spear)
					case 18080: //swipe (spear)
					case 18079: //pound (spear)
					case 17100: //longrange (darts)
						FightType = 3;
						SkillID = 3;
						break;
					case 9128: //Aggressive
					case 22230: //kick (unarmed)
					case 21203: //impale (pickaxe)
					case 21202: //smash (pickaxe)
					case 1079: //pound (staff)
					case 6171: //hack (axe)
					case 6170: //smash (axe)
					case 33020: //swipe (hally)
					case 6235: //rapid (long bow)
					case 17101: //repid (darts)
					case 8237: //lunge (dagger)
					case 8236: //slash (dagger)
						FightType = 2;
						SkillID = 2;
						break;
					case 9154: //Log out
                                        if (LogoutDelay >= 0)
                                        {
                                        sendMessage("You have to wait 30 seconds out of being in combat to logout.");
                                        }
                                        else
                                        {
						logout();
                                                savemoreinfo();
                                                saveStats();
                                        }
						break;
					case 21011:
						takeAsNote = false;
						break;
					case 21010:
						takeAsNote = true;
						break;
					case 13092:
						if (tradeWith > 0) {
							if (PlayerHandler.players[tradeWith].tradeStatus == 2) {
								tradeStatus = 3;
								sendFrame126("Waiting for other player...", 3431);
							} else if (PlayerHandler.players[tradeWith].tradeStatus == 3) {
								tradeStatus = 3;
								//TradeGoConfirm();
							}
						}
						break;
					case 13218:
						if (tradeWith > 0) {
							if (PlayerHandler.players[tradeWith].tradeStatus == 3) {
								tradeStatus = 4;
								sendFrame126("Waiting for other player...", 3535);
							} else if (PlayerHandler.players[tradeWith].tradeStatus == 4) {
								tradeStatus = 4;
								//ConfirmTrade();
							}
						}
						break;

					case 9157:
						if (NpcDialogue == 2) {
							NpcDialogue = 0;
							NpcDialogueSend = false;
							openUpBank();
						} else if (NpcDialogue == 4) { //Aubury
							NpcDialogue = 0;
							NpcDialogueSend = false;
							openUpShop(2);
						}else if (NpcDialogue == 41){
                     NpcDialogue = 0;
                     NpcDialogueSend = false;
                     RemoveAllWindows();
                     sendMessage("You board the ship.");
                     travelboat1 = true;
                     traveltime = 30;
                  }else if (NpcDialogue == 43){
                     NpcDialogue = 0;
                     NpcDialogueSend = false;
                     RemoveAllWindows();
                     sendMessage("You board the ship.");
                     travelboat2 = true;
                     traveltime = 30;
                  }else if (NpcDialogue == 2260) { // Mage Of Zamorak
NpcDialogue = 0;
NpcDialogueSend = false;
                sendMessage("You teleport to the abyss.");
		teleportToX = 3040;
                teleportToY = 4842;
		}else if (NpcDialogue == 1002) { // Dark Mage
NpcDialogue = 0;
NpcDialogueSend = false;
stillgfx(435, absY, absX);
RemoveAllWindows();
if (ancients == 1) {
setSidebarInterface(6, 1151); //magic tab (ancient = 12855);
ancients = 0;
sendMessage("The dark mage converts back to normal magic!");
} else {
setSidebarInterface(6, 12855); //magic tab (ancient = 12855);
ancients = 1;
sendMessage("The dark mage converts you to ancient magicks!");
}
}else if (duelring == true){
        teleportToX = 3311;
        teleportToY = 3234;
        heightLevel = 0;
        sendMessage("You teleport to the duel arena!");
        duelring = false;
                     RemoveAllWindows();
                  }
						break;
					case 9158:
						if (NpcDialogue == 2) {
							NpcDialogue = 0;
							NpcDialogueSend = false;
							openUpPinSettings();
						} else if (NpcDialogue == 4) {
							NpcDialogue = 5;
							NpcDialogueSend = false;
						                  }else if (NpcDialogue == 41){
                     NpcDialogue = 0;
                     NpcDialogueSend = false;
                     RemoveAllWindows();
                  }else if (NpcDialogue == 43){
                     NpcDialogue = 0;
                     NpcDialogueSend = false;
                     RemoveAllWindows();
                  }else if (NpcDialogue == 2260){
                     NpcDialogue = 0;
                     NpcDialogueSend = false;
                     RemoveAllWindows();
                  }else if (NpcDialogue == 1002){
                     NpcDialogue = 0;
                     NpcDialogueSend = false;
                     RemoveAllWindows();
                  }else if (duelring == true){
        teleportToX = 2425;
        teleportToY = 3078;
        heightLevel = 0;
        sendMessage("You teleport to castle wars!");
        duelring = false;
                     RemoveAllWindows();
                  }

						break;
					case 1097:
						setSidebarInterface(0, 1829);
						break;
					case 7212:
						setSidebarInterface(0, 328);
						break;

case 24135: // Clue debug set to on
{
sendMessage("Clue debugging set to true.");
cluedebug = true;
break;
}
case 24134: // Clue debug set to off
{
sendMessage("Clue debugging set to false.");
cluedebug = false;
break;
}
case 28164: // Invisible Armour (id 1)
{
questid = 1;
quest();
}
break;

case 28165: // Cook's Assistant (id 2)
{
questid = 2;
quest();
}
break;

case 4135: // bars to ores (uses bones to bananas spell)
{
bars2ores();
}
break;

/*PRAYERS*/
// Lower the prayer drain the faster it goes
case 21233: // Thick Skin
if(Skin == false)
{
noprayer();
NewDrain = 40;
DrainPray = true;
Skin = true;
newdrain();
}
else if(Skin == true)
{
Skin = false;
}
break;
//---------------------------------------------------------//
case 21234: // Burst Of Strength
if(Str1 == false)
{
noprayer();
NewDrain = 40;
DrainPray = true;
Str1 = true;
newdrain();
}
else if(Str1 == true)
{
Str1 = false;
}
break;
//---------------------------------------------------------//
case 21235: // Clarity Of Thought
if(Clarity == false)
{
noprayer();
NewDrain = 40;
DrainPray = true;
Clarity = true;
newdrain();
}
else if(Clarity == true)
{
Clarity = false;
}
break;
//---------------------------------------------------------//
case 21236: // Rock Skin
if(Rock == false)
{
noprayer();
NewDrain = 30;
DrainPray = true;
Rock = true;
newdrain();
}
else if(Rock == true)
{
Rock = false;
}
break;
//---------------------------------------------------------//
case 21237: // Superhuman Strength
if(Str2 == false)
{
noprayer();
NewDrain = 30;
DrainPray = true;
Str2 = true;
newdrain();
}
else if(Str2 == true)
{
Str2 = false;
}
break;
//---------------------------------------------------------//
case 21238: // Improved Reflexes
if(Reflex2 == false)
{
NewDrain = 30;
DrainPray = true;
Reflex2 = true;
newdrain();
}
else if(DrainPray == true)
{
Reflex2 = false;
}
break;
//---------------------------------------------------------//
case 21239: // Rapid Restore
if(RapidRestore == false)
{
noprayer();
NewDrain = 20;
DrainPray = true;
RapidRestore = true;
newdrain();
}
else if(RapidRestore == true)
{
RapidRestore = false;
}
break;
//---------------------------------------------------------//
case 21240: // Rapid Heal
if(RapidHeal == false)
{
noprayer();
NewDrain = 20;
DrainPray = true;
RapidHeal = true;
}
else if(RapidHeal == true)
{
RapidHeal = false;
}
break;
//---------------------------------------------------------//
case 21241: // Protect Item
if(DrainPray == false)
{
noprayer();
NewDrain = 20;
DrainPray = true;
ProtItem = true;
newdrain();
}
else if(ProtItem == true)
{
ProtItem = false;
}
break;
//---------------------------------------------------------//
case 21242: // Steel Skin
if(Steel == false)
{
noprayer();
NewDrain = 20;
DrainPray = true;
Steel = true;
newdrain();
}
else if(Steel == true)
{
Steel = false;
}
break;
//---------------------------------------------------------//
case 21243: // Ultimate Strength
if(Str3 == false)
{
noprayer();
NewDrain = 20;
DrainPray = true;
Str3 = true;
newdrain();
}
else if(Str3 == true)
{
Str3 = false;
}
break;
//---------------------------------------------------------//
case 21244: // Incredible Reflexes
if(Reflex3 == false)
{
noprayer();
NewDrain = 20;
DrainPray = true;
Reflex3 = true;
newdrain();
}
else if(Reflex3 == true)
{
Reflex3 = false;
}
break;
//---------------------------------------------------------//
case 21245: // Protect From Magic
if(ProtMage == false)
{
noprayer();
ResetProtPrayers();
NewDrain = 15;
DrainPray = true;
ProtMage = true;
newdrain();
}
else if(ProtMage == true)
{
ProtMage = false;
}
break;
//---------------------------------------------------------//
case 21246: // Protect From Range
if(ProtRange == false)
{
noprayer();
ResetProtPrayers();
NewDrain = 15;
DrainPray = true;
ProtRange = true;
newdrain();
}
else if(ProtRange == true)
{
ProtRange = false;
}
break;
//---------------------------------------------------------//
case 21247: // Protect From Melee
if(ProtMelee == false)
{
noprayer();
ResetProtPrayers();
NewDrain = 15;
DrainPray = true;
ProtMelee = true;
newdrain();
}
else if(ProtMelee == true)
{
ProtMelee = false;
}
break;
//---------------------------------------------------------//
case 2171: // Retribution
if(Retribution == false)
{
noprayer();
NewDrain = 10;
DrainPray = true;
Retribution = true;
newdrain();
}
else if(Retribution == true)
{
Retribution = false;
}
break;
//---------------------------------------------------------//
case 2172: // Redemption
if(Redemption == false)
{
noprayer();
NewDrain = 10;
DrainPray = true;
Redemption = true;
newdrain();
}
else if(Redemption == true)
{
Redemption = false;
}
break;
//---------------------------------------------------------//
case 2173: // Smite
if(Smite == false)
{
noprayer();
NewDrain = 10;
DrainPray = true;
Smite = true;
newdrain();
}
else if(Smite == true)
{
Smite = false;
}
break;
//---------------------------------------------------------//

					default:
						//System.out.println("Player stands in: X="+absX+" Y="+absY);
						println_debug("Case 185: Action Button: "+actionButtonId);
						break;
				}
			break;
			// the following Ids are the reason why AR-type cheats are hopeless to make...
			// basically they're just there to make reversing harder
			case 226:
			case 78:
			case 148:
			case 183:
			case 230:
			case 136:
			case 189:
			case 152:
			case 200:
			case 85:
			case 165:
			case 238:
			case 150:
			case 36:
			case 246:
			case 77:
/*QUESTS
case 28164: // Invisible Armour (id 1)
{
questid = 1;
quest();
}*/
				break;
			// any packets we might have missed
			default:
				interfaceID = inStream.readUnsignedWordA();
				int actionButtonId1 = misc.HexToInt(inStream.buffer, 0, packetSize);
				println_debug("Unhandled packet ["+packetType+", InterFaceId: " +interfaceID+", size="+packetSize+"]: ]"+misc.Hex(inStream.buffer, 1, packetSize)+"[");
				println_debug("Action Button: "+actionButtonId1);
			break;
		}
	}
	private int somejunk;

/*case 2:
						if (token.equals("character-height")) {
							heightLevel = Integer.parseInt(token2);
						} else if (token.equals("character-posx")) {
							teleportToX = Integer.parseInt(token2);
						} else if (token.equals("character-posy")) {
							teleportToY = Integer.parseInt(token2);
						} else if (token.equals("character-rights")) {
							playerRights = Integer.parseInt(token2);
						} else if (token.equals("character-ismember")) {
							playerIsMember = Integer.parseInt(token2);
						} else if (token.equals("character-messages")) {
							playerMessages = Integer.parseInt(token2);
						} else if (token.equals("character-lastconnection")) {
							playerLastConnect = token2;
						} else if (token.equals("character-lastlogin")) {
							playerLastLogin = Integer.parseInt(token2);
						} else if (token.equals("character-energy")) {
							playerEnergy = Integer.parseInt(token2);
						} else if (token.equals("character-gametime")) {
							playerGameTime = Integer.parseInt(token2);
						} else if (token.equals("character-gamecount")) {
							playerGameCount = Integer.parseInt(token2);
						}
						break;
					case 3:
						if (token.equals("character-equip")) {
							playerEquipment[Integer.parseInt(token3[0])] = Integer.parseInt(token3[1]);
							playerEquipmentN[Integer.parseInt(token3[0])] = Integer.parseInt(token3[2]);
						}
						break;
					case 4:
						if (token.equals("character-look")) {
							playerLook[Integer.parseInt(token3[0])] = Integer.parseInt(token3[1]);
						}
						break;
					case 5:
						if (token.equals("character-skill")) {
							playerLevel[Integer.parseInt(token3[0])] = Integer.parseInt(token3[1]);
							playerXP[Integer.parseInt(token3[0])] = Integer.parseInt(token3[2]);
						}
						break;
					case 6:
						if (token.equals("character-item")) {
							playerItems[Integer.parseInt(token3[0])] = Integer.parseInt(token3[1]);
							playerItemsN[Integer.parseInt(token3[0])] = Integer.parseInt(token3[2]);
						}
						break;
					case 7:
						if (token.equals("character-bank")) {
							bankItems[Integer.parseInt(token3[0])] = Integer.parseInt(token3[1]);
							bankItemsN[Integer.parseInt(token3[0])] = Integer.parseInt(token3[2]);
						}
						break;
					case 8:
						if (token.equals("character-friend")) {
							friends[Integer.parseInt(token3[0])] = Long.parseLong(token3[1]);
						}
						break;
					case 9:
						if (token.equals("character-ignore")) {
							ignores[Integer.parseInt(token3[0])] = Long.parseLong(token3[1]);
						}
						break;
				}
			} else {
				if (line.equals("[ACCOUNT]")) {		ReadMode = 1;
				} else if (line.equals("[CHARACTER]")) {	ReadMode = 2;
				} else if (line.equals("[EQUIPMENT]")) {	ReadMode = 3;
				} else if (line.equals("[LOOK]")) {		ReadMode = 4;
				} else if (line.equals("[SKILLS]")) {		ReadMode = 5;
				} else if (line.equals("[ITEMS]")) {		ReadMode = 6;
				} else if (line.equals("[BANK]")) {		ReadMode = 7;
				} else if (line.equals("[FRIENDS]")) {		ReadMode = 8;
				} else if (line.equals("[IGNORES]")) {		ReadMode = 9;
				} else if (line.equals("[EOF]")) {		try { characterfile.close(); } catch(IOException ioexception) { } return 1;
				}
			}
			try {
				line = characterfile.readLine();
			} catch(IOException ioexception1) { EndOfFile = true; }
		}
		try { characterfile.close(); } catch(IOException ioexception) { }
		return 3;
	}*/

	public int EssenceMineX[] = {2893,2921,2911,2926,2899};
	public int EssenceMineY[] = {4846,4846,4832,4817,4817};
/*
[0] North West
[1] North East
[2] Center
[3] South East
[4] South West
*/
	public int EssenceMineRX[] = {3253,3105,2681,2591};
	public int EssenceMineRY[] = {3401,9571,3325,3086};
/*
[0] Varrock
[1] Wizard Tower
[2] Ardougne
[3] Magic Guild
*/
	public boolean stairs(int stairs, int teleX, int teleY) {
		if (IsStair == false) {
			IsStair = true;
			if (stairs == 1) {
				heightLevel += 1;
			} else if (stairs == 2) {
				heightLevel -= 1;
			} else if (stairs == 21) {
				heightLevel += 1;
			} else if (stairs == 22) {
				heightLevel -= 1;
			}
			teleportToX = teleX;
			teleportToY = teleY;
			if (stairs == 3 || stairs == 5 || stairs == 9) {
				teleportToY += 6400;
			} else if (stairs == 4 || stairs == 6 || stairs == 10) {
				teleportToY -= 6400;
			} else if (stairs == 7) {
				teleportToX = 3104;
				teleportToY = 9576;
			} else if (stairs == 8) {
				teleportToX = 3105;
				teleportToY = 3162;
			} else if (stairs == 11) {
				teleportToX = 2856;
				teleportToY = 9570;
			} else if (stairs == 12) {
				teleportToX = 2857;
				teleportToY = 3167;
			} else if (stairs == 13) {
				heightLevel += 3;
				teleportToX = skillX;
				teleportToY = skillY;
			} else if (stairs == 15) {
				teleportToY += (6400 - (stairDistance + stairDistanceAdd));
			} else if (stairs == 14) {
				teleportToY -= (6400 - (stairDistance + stairDistanceAdd));
			} else if (stairs == 16) {
				teleportToX = 2828;
				teleportToY = 9772;
			} else if (stairs == 17) {
				teleportToX = 3494;
				teleportToY = 3465;
			} else if (stairs == 18) {
				teleportToX = 3477;
				teleportToY = 9845;
			} else if (stairs == 19) {
				teleportToX = 3543;
				teleportToY = 3463;
			} else if (stairs == 20) {
				teleportToX = 3549;
				teleportToY = 9865;
			} else if (stairs == 21) {
				teleportToY += (stairDistance + stairDistanceAdd);
			} else if (stairs == 22) {
				teleportToY -= (stairDistance + stairDistanceAdd);
			} else if (stairs == 23) {
				teleportToX = 2480;
				teleportToY = 5175;
			} else if (stairs == 24) {
				teleportToX = 2862;
				teleportToY = 9572;
			} else if (stairs == 25) {
				Essence = (heightLevel / 4);
				heightLevel = 0;
				teleportToX = EssenceMineRX[Essence];
				teleportToY = EssenceMineRY[Essence];
			} else if (stairs == 26) {
				int EssenceRnd = misc.random3(EssenceMineX.length);
				teleportToX = EssenceMineX[EssenceRnd];
				teleportToY = EssenceMineY[EssenceRnd];
				heightLevel = (Essence * 4);
			} else if (stairs == 27) {
				teleportToX = 2453;
				teleportToY = 4468;
			} else if (stairs == 28) {
				teleportToX = 3201;
				teleportToY = 3169;
			}
			if (stairs == 5 || stairs == 10) {
				teleportToX += (stairDistance + stairDistanceAdd);
			}
			if (stairs == 6 || stairs == 9) {
				teleportToX -= (stairDistance - stairDistanceAdd);
			}
		}
		resetStairs();
		return true;
	}
	public boolean resetStairs() {
		stairs = 0;
		skillX = -1;
		skillY = -1;
		stairDistance = 1;
		stairDistanceAdd = 0;
		IsUsingSkill = false;
		return true;
	}
	public boolean crackCracker() {
		sendMessage("Somone used a crackers on you...");
		CrackerMsg = false;
		if (CrackerForMe == true) {
			if (freeSlots() > 0) {
				addItem(Item.randomPHat(), 1);
				sendMessage("And you get the crackers item.");
			} else {
				sendMessage("but you don't have enough space in your inventory.");
			}
			CrackerForMe = false;
		} else {
			sendMessage("but you didn't get the crackers item.");
		}
		return true;
	}
	public boolean IsInWilderness(int coordX, int coordY, int Type) {
		if (Type == 1) {
			if (coordY >= 3520 && coordY <= 3967 && coordX <= 3392 && coordX >= 2942) {
				return true;
			}
		} else if (Type == 2) {
			if (coordY >= 3512 && coordY <= 3967 && coordX <= 3392 && coordX >= 2942) {
				return true;
			}
		}
		return false;
	}
	public boolean Attack() {
		int EnemyX = PlayerHandler.players[AttackingOn].absX;
		int EnemyY = PlayerHandler.players[AttackingOn].absY;
		int EnemyHP = PlayerHandler.players[AttackingOn].playerLevel[playerHitpoints];
		int EnemyHPExp = PlayerHandler.players[AttackingOn].playerXP[playerHitpoints];
                client AttackingOn2 = (client) server.playerHandler.players[AttackingOn];
		boolean RingOfLife = false;
		if (PlayerHandler.players[AttackingOn].playerEquipment[playerRing] == 2570) {
			RingOfLife = true;
		}
		int hitDiff = 0;
                int wepdelay = 0;
                CalculateMaxHit();
		hitDiff = misc.random(playerMaxHit);

if(playerEquipment[playerWeapon] == (1333)) //full rune and scimi here
if(playerEquipment[playerChest] == (1127))
if(playerEquipment[playerLegs] == (1079))
if(playerEquipment[playerShield] == (1201)) 
{
PkingDelay = 8;
wepdelay = 8;
}

if(playerEquipment[playerWeapon] == (4151)) // whip
{
PkingDelay = 6;
wepdelay = 6;
setAnimation(1658);
resetanim = 4;
}
			
if(playerEquipment[playerWeapon] == (4718)) //dharoks axe
{
PkingDelay = 16;
wepdelay = 16;
}

if(playerEquipment[playerWeapon] == (4151)) //full karils and whip here
if(playerEquipment[playerChest] == (4736))
if(playerEquipment[playerLegs] == (4738))
{
PkingDelay = 2;
wepdelay = 2;
}

if(playerEquipment[playerWeapon] == (4587)) //full dragon and scimmy here
if(playerEquipment[playerChest] == (3140))
if(playerEquipment[playerLegs] == (4087))
if(playerEquipment[playerShield] == (1187))
{
PkingDelay = 7;
wepdelay = 7;
}

if(playerEquipment[playerWeapon] == (1377)) //dragon battleaxe
{
PkingDelay = 16;
wepdelay = 16;
}

if(playerEquipment[playerWeapon] == (1434)) //dragon mace
{
PkingDelay = 10;
wepdelay = 10;
}

if(playerEquipment[playerWeapon] == (7158)) //dragon 2h
{
PkingDelay = 16;
wepdelay = 16;
}


                                                                        else if(playerLevel[4] <= 99) 
                                                                        if(playerEquipment[playerWeapon] == 4214)
									{
										hitDiff = 5 + misc.random(30);
                                                                                PkingDelay = 2;
                                                                                wepdelay = 2;
                                                                                inCombat();
                                 setAnimation(426);
                                 teleportToX = absX;   
                                 teleportToY = absY; 
									}
									else 									                                                        if(playerLevel[4] >= 99) 
                                                                        if(playerEquipment[playerWeapon] == 4214)
                                                                                {
										hitDiff = 5 + misc.random(37);
                                                                                PkingDelay = 2;
                                                                                wepdelay = 2;
                                                                                inCombat();
                                 setAnimation(426);
                                 teleportToX = absX;   
                                 teleportToY = absY; 
                                                                                }
									else if(playerLevel[4] <= 99) 
                                                                        if(playerEquipment[playerWeapon] == 861)
									{
										hitDiff = 3 + misc.random(21);
                                                                                PkingDelay = 4;
                                                                                wepdelay = 4;
                                                                                inCombat();
                                 //setAnimation(426);
                                 teleportToX = absX;   
                                 teleportToY = absY; 
									}
									else if (playerLevel[4] >= 99)
                                                                        if(playerEquipment[playerWeapon] == 861)
                                                                                {
										hitDiff = 5 + misc.random(31);
                                                                                PkingDelay = 4;
                                                                                wepdelay = 4;
                                                                                inCombat();
                                 setAnimation(426);
                                 teleportToX = absX;   
                                 teleportToY = absY; 
                                                                                }
									else if(playerLevel[4] <= 99) 
                                                                        if(playerEquipment[playerWeapon] == 859)
									{
										hitDiff = 3 + misc.random(23);
                                                                                PkingDelay = 5;
                                                                                wepdelay = 5;
                                                                                inCombat();
                                 setAnimation(426);
                                 teleportToX = absX;   
                                 teleportToY = absY; 
									}
									else 									                                                        if(playerLevel[4] >= 99) 
                                                                        if(playerEquipment[playerWeapon] == 859)
                                                                                {
										hitDiff = 5 + misc.random(33);
                                                                                PkingDelay = 5;
                                                                                inCombat();
                                 setAnimation(426);
                                 teleportToX = absX;   
                                 teleportToY = absY; 
                                                                                }
else
{
PkingDelay = 10;
wepdelay = 10;
}
		if (GoodDistance(EnemyX, EnemyY, absX, absY, 1) == true || playerEquipment[playerWeapon] == 859 || playerEquipment[playerWeapon] == 861 || playerEquipment[playerWeapon] == 4214) {
		  if (LoopAttDelay <= 1) {
		    if (IsInWilderness(EnemyX, EnemyY, 1) == true) {
			if (RingOfLife == true && EnemyHP <= (int)((double)((double)getLevelForXP(EnemyHPExp) / 10.0) + 0.5)) {
				PlayerHandler.players[AttackingOn].SafeMyLife = true;
			} else {
				if (PlayerHandler.players[AttackingOn].IsDead == true) {
					ResetAttack();
				} else {
					//actionAmount++;
					//setAnimation(playerSEA);
					PlayerHandler.players[AttackingOn].hitUpdateRequired = true;
					PlayerHandler.players[AttackingOn].updateRequired = true;
					PlayerHandler.players[AttackingOn].appearanceUpdateRequired = true;
                                        AttackingOn2.KillerId = playerId;
                                        AttackingOn2.inCombat();
                                        setAnimation(GetWepAnim());
                                        if(playerEquipment[playerWeapon] == 4726)
                                        {
                                        stillgfx(398, absY, absX);
                                        }
                                        AttackingOn2.setAnimation(GetBlockAnim(AttackingOn2.playerEquipment[playerWeapon]));
                                        LoopAttDelay = PkingDelay;
					if ((EnemyHP - hitDiff) < 0) {
						hitDiff = EnemyHP;
					}
					PlayerHandler.players[AttackingOn].hitDiff = hitDiff;
					//PkingDelay = wepdelay;
				}
			}
			return true;
		    } else {
		      sendMessage("This player is not in the Wilderness.");
		      ResetAttack();
		    }
		  }
		}
		return false;
	}
	public boolean ResetAttack() {
		IsAttacking = false;
		AttackingOn = 0;
		resetAnimation();
		IsUsingSkill = false;
		return true;
	}
	public boolean ApplyDead() {
		if (IsDeadTimer == false) {
			actionAmount++;
			actionTimer = 0;
			ResetAttack();
			ResetAttackNPC();
			setAnimation(0x900);
			IsDeadTimer = true;
                        ApplyDead();
		}
		if (actionTimer == 0 && IsDeadTimer == true) {
			youdied();
                        teleportToX = 3254;
			teleportToY = 3420;
			IsDeadTeleporting = true;
			resetAnimation();
			updateRequired = true;
			appearanceUpdateRequired = true;
                        NewHP = 100;
                        setSkillLevel(3, getLevelForXP(playerXP[3]), playerXP[playerHitpoints]);
                        playerLevel[3] = getLevelForXP(playerXP[3]);
                        PoisonDelay = 9999999;
                        KillerId = playerId;
		}
		return true;
	}

/*public boolean Owntimer = false;
public boolean Own()
{
if(Owntimer == false)
{
actionAmount++;
teleportToX = absX;
teleportToY = absY;
playerSER = 1501;
playerSEW = 1501;
setAnimation(1500);
sendMessage("You rise to the air...");
actionTimer = 0;
Owntimer = true;
Own();
}
if(actionTimer <= 0 && Owntimer == true)
{
teleportToX = absX;
teleportToY = absY;
setAnimation(1502);
sendMessage("and electricute "+castOnPlayer.playerName+"!");
castOnPlayer.sendMessage("You get electricuted!");
castOnPlayer.hitDiff = 1 + misc.random(6);
teleportToX = absX;
teleportToY = absY;
castOnPlayer.setAnimation(3170);
castOnPlayer.entangle();
Owntimer = false;
}
return true;
}*/
	public boolean ApplyRingOfLife() {
		if (IsDeadTimer == false) {
			actionAmount++;
			actionTimer = 4;
			ResetAttack();
			setAnimation(0x718);
			sendMessage("Ring of Life saved your life !");
			deleteequiment(2570, playerRing); //2570 = ring of life
			IsDeadTimer = true;
		}
		if (actionTimer == 0 && IsDeadTimer == true) {
			teleportToX = 3254;
			teleportToY = 3420;
			resetAnimation();
			IsDeadTeleporting = true;
			updateRequired = true;
			appearanceUpdateRequired = true;
		}
		return true;
	}
	public boolean CheckForSkillUse() { //Use Item On Item
		boolean GoOn = true;
		if (useitems[0] == 946) { //knife
			CheckKnifeUsage();
		} else if (useitems[0] == 1777) { //string
			CheckStringUsage();
		} else if (useitems[0] == 314) { //feathers
			CheckFeatherUsage();
		} else if (useitems[1] == 1019 || useitems[1] == 1021 || useitems[1] == 1023 || useitems[1] == 1027 || useitems[1] == 1029 || useitems[1] == 1031 || useitems[1] == 1007) {
			CheckDyeCape();
		} else if (useitems[0] == 1755) { //chisel
			CheckCuttingGem();
		} else if (useitems[1] == 946 || useitems[1] == 1777 || useitems[1] == 314 || useitems[1] == 1755 || useitems[0] == 1019 || useitems[0] == 1021 || useitems[0] == 1023 || useitems[0] == 1027 || useitems[0] == 1029 || useitems[0] == 1031 || useitems[0] == 1007) {
			int temp;
			temp = useitems[0];
			useitems[0] = useitems[1];
			useitems[1] = temp;
			temp = useitems[3];
			useitems[3] = useitems[2];
			useitems[2] = temp;
			CheckForSkillUse();
		} else {
			sendMessage("Nothing interesting is happening.");
		}
		return true;
	}
	public boolean CheckForSkillUse2(int ItemID, int ItemSlot) { //Use Item On Object
		boolean GoFalse = false;
		switch (ItemID) {
			case 317: //raw shrimps
				cooking[1] = 1;
				cooking[2] = 30;
				cooking[4] = 315;
				cooking[6] = 323;
				break;
			case 321: //raw anchovies
				cooking[1] = 1;
				cooking[2] = 30;
				cooking[4] = 319;
				cooking[6] = 323;
				break;
			case 327: //raw sardine
				cooking[1] = 1;
				cooking[2] = 40;
				cooking[4] = 325;
				cooking[6] = 369;
				break;
			case 331: //raw salmon
				cooking[1] = 25;
				cooking[2] = 90;
				cooking[4] = 329;
				cooking[6] = 343;
				break;
			case 335: //raw trout
				cooking[1] = 15;
				cooking[2] = 70;
				cooking[4] = 333;
				cooking[6] = 343;
				break;
			case 341: //raw cod
				cooking[1] = 18;
				cooking[2] = 75;
				cooking[4] = 339;
				cooking[6] = 343;
				break;
			case 345: //raw harring
				cooking[1] = 5;
				cooking[2] = 50;
				cooking[4] = 347;
				cooking[6] = 357;
				break;
			case 349: //raw pike
				cooking[1] = 20;
				cooking[2] = 80;
				cooking[4] = 351;
				cooking[6] = 357;
				break;
			case 353: //raw mackerel
				cooking[1] = 10;
				cooking[2] = 60;
				cooking[4] = 355;
				cooking[6] = 357;
				break;
			case 359: //raw tuna
				cooking[1] = 30;
				cooking[2] = 100;
				cooking[4] = 361;
				cooking[6] = 367;
				break;
			case 363: //raw bass
				cooking[1] = 43;
				cooking[2] = 130;
				cooking[4] = 365;
				cooking[6] = 367;
				break;
			case 371: //raw swordfish
				cooking[1] = 45;
				cooking[2] = 140;
				cooking[4] = 373;
				cooking[6] = 375;
				break;
			case 377: //raw lobster
				cooking[1] = 40;
				cooking[2] = 120;
				cooking[4] = 379;
				cooking[6] = 381;
				break;
			case 383: //raw shark
				cooking[1] = 80;
				cooking[2] = 210;
				cooking[4] = 385;
				cooking[6] = 387;
				break;
			case 389: //raw mante ray
				cooking[1] = 91;
				cooking[2] = 216;
				cooking[4] = 391;
				cooking[6] = 393;
				break;
			case 395: //raw sea turtle
				cooking[1] = 82;
				cooking[2] = 212;
				cooking[4] = 397;
				cooking[6] = 399;
				break;
			case 436: //copper ore
				if (IsItemInBag(438) == true) {
					smelting[0] = 1;
					smelting[1] = 1;
					if (misc.random(2) == 1) {
						smelting[2] = 6;
					} else {
						smelting[2] = 7;
					}
					smelting[3] = 2349;
				} else {
					sendMessage("You need 1 copper ore and 1 tin ore to smelt 1 bronze bar.");
					return false;
				}
				break;
			case 438: //tin
				if (IsItemInBag(436) == true) {
					smelting[0] = 1;
					smelting[1] = 1;
					if (misc.random(2) == 1) {
						smelting[2] = 6;
					} else {
						smelting[2] = 7;
					}
					smelting[3] = 2349;
				} else {
					sendMessage("You need 1 copper ore and 1 tin ore to smelt 1 bronze bar.");
					return false;
				}
				break;
			case 440: //iron
				smelting[0] = 1;
				if (AreXItemsInBag(453, 2) == true) {
					smelting[1] = 30;
					if (misc.random(2) == 1) {
						smelting[2] = 17;
					} else {
						smelting[2] = 18;
					}
					smelting[3] = 2353;
					smelting[6] = 2;
				} else {
					smelting[1] = 15;
					if (misc.random(2) == 1) {
						smelting[2] = 12;
					} else {
						smelting[2] = 13;
					}
					smelting[3] = 2349;
				}
				break;
			case 2892: //elemental
				if (AreXItemsInBag(453, 4) == true) {
					smelting[0] = 1;
					smelting[1] = 20;
					smelting[2] = 18;
					smelting[3] = 2893;
					smelting[6] = 4;
				} else {
					sendMessage("You need 1 elemental ore and 4 coal to smelt 1 elemental bar.");
					return false;
				}
				break;
			case 442: //silver
				smelting[0] = 1;
				smelting[1] = 20;
				if (misc.random(2) == 1) {
					smelting[2] = 13;
				} else {
					smelting[2] = 14;
				}
				smelting[3] = 2355;
				break;
			case 444: //gold
				smelting[0] = 1;
				smelting[1] = 40;
				if (playerEquipment[playerHands] == 776) {
					if (misc.random(2) == 1) {
						smelting[2] = 56;
					} else {
						smelting[2] = 57;
					}
				} else {
					if (misc.random(2) == 1) {
						smelting[2] = 22;
					} else {
						smelting[2] = 23;
					}
				}
				smelting[3] = 2357;
				break;
			case 447: //mithril
				if (AreXItemsInBag(453, 4) == true) {
					smelting[0] = 1;
					smelting[1] = 50;
					smelting[2] = 30;
					smelting[3] = 2359;
					smelting[6] = 4;
				} else {
					sendMessage("You need 1 mithril ore and 4 coal to smelt 1 mithril bar.");
					return false;
				}
				break;
			case 449: //adamantite
				if (AreXItemsInBag(453, 6) == true) {
					smelting[0] = 1;
					smelting[1] = 70;
					if (misc.random(2) == 1) {
						smelting[2] = 37;
					} else {
						smelting[2] = 38;
					}
					smelting[3] = 2361;
					smelting[6] = 6;
				} else {
					sendMessage("You need 1 adamant ore and 4 coal to smelt 1 adamant bar.");
					return false;
				}
				break;
			case 451: //runite
				if (AreXItemsInBag(453, 8) == true) {
					smelting[0] = 1;
					smelting[1] = 85;
					smelting[2] = 50;
					smelting[3] = 2363;
					smelting[6] = 8;
				} else {
					sendMessage("You need 1 runite ore and 4 coal to smelt 1 runite bar.");
					return false;
				}
				break;
			case 453: //coal
				if (IsItemInBag(451) == true) {			//runite
					CheckSmelting(451, GetItemSlot(451));
				} else if (IsItemInBag(449) == true) {		//adamant
					CheckSmelting(449, GetItemSlot(449));
				} else if (IsItemInBag(447) == true) {		//mithril
					CheckSmelting(447, GetItemSlot(447));
				} else if (IsItemInBag(2892) == true) {		//elemental
					CheckSmelting(2892, GetItemSlot(2892));
				} else if (IsItemInBag(440) == true) {		//iron (to make steel)
					CheckSmelting(440, GetItemSlot(440));
				}
				break;
			case 1889: //uncooked cake
				cooking[1] = 40;
				cooking[2] = 180;
				cooking[4] = 1891;
				cooking[6] = 1903;
				break;
			case 2001: //Uncooked stew
				cooking[1] = 25;
				cooking[2] = 117;
				cooking[4] = 2003;
				cooking[6] = 2005;
				break;
			case 2009: //Uncooked curry
				cooking[1] = 60;
				cooking[2] = 280;
				cooking[4] = 2011;
				cooking[6] = 2013;
				break;
			case 2132: //raw beef
			case 2134: //raw rat meat
			case 2136: //raw bear meat
				cooking[1] = 1;
				cooking[2] = 30;
				cooking[4] = 2142;
				cooking[6] = 2146;
				break;
			case 2138: //raw chicken
				cooking[1] = 1;
				cooking[2] = 30;
				cooking[4] = 2140;
				cooking[6] = 2144;
				break;
			case 2287: //uncooked pizza
				cooking[1] = 35;
				cooking[2] = 143;
				cooking[4] = 2289;
				cooking[6] = 2305;
				break;
			case 2307: //bread dough
				cooking[1] = 1;
				cooking[2] = 40;
				cooking[4] = 2309;
				cooking[6] = 2311;
				break;
			case 2317: //uncooked apple pie
				cooking[1] = 30;
				cooking[2] = 104;
				cooking[4] = 2323;
				cooking[6] = 2329;
				break;
			case 2319: //uncooked meat pie
				cooking[1] = 20;
				cooking[2] = 104;
				cooking[4] = 2327;
				cooking[6] = 2329;
				break;
			case 2321: //uncooked berry pie
				cooking[1] = 10;
				cooking[2] = 78;
				cooking[4] = 2325;
				cooking[6] = 2329;
				break;
			case 2876: //Raw chompy
				cooking[1] = 20;
				cooking[2] = 140;
				cooking[4] = 2878;
				cooking[6] = 2880;
				break;
			case 3142: //Raw karambwan
				cooking[1] = 1;
				cooking[2] = 80;
				cooking[4] = 3144;
				cooking[6] = 3148;
				break;
			case 3363: //Thin snail
				cooking[1] = 12;
				cooking[2] = 70;
				cooking[4] = 3369;
				cooking[6] = 3375;
				break;
			case 3365: //Lean snail
				cooking[1] = 17;
				cooking[2] = 80;
				cooking[4] = 3371;
				cooking[6] = 3375;
				break;
			case 3367: //Fat snail
				cooking[1] = 22;
				cooking[2] = 95;
				cooking[4] = 3373;
				cooking[6] = 3375;
				break;
			case 3379: //Slimy eel
				cooking[1] = 28;
				cooking[2] = 95;
				cooking[4] = 3381;
				cooking[6] = 3383;
				break;
			case 5001: //Raw cave eel
				cooking[1] = 28;
				cooking[2] = 115;
				cooking[4] = 5003;
				cooking[6] = 5002;
				break;
			case 5986: //Sweetcorn
				cooking[1] = 21;
				cooking[2] = 104;
				cooking[4] = 5988;
				cooking[6] = 5990;
				break;
			case 6293: //Spider on stick
				cooking[1] = 16;
				cooking[2] = 80;
				cooking[4] = 6297;
				cooking[6] = 6301;
				break;
			case 6295: //Spider on shaft
				cooking[1] = 16;
				cooking[2] = 80;
				cooking[4] = 6299;
				cooking[6] = 6303;
				break;
			default:
				sendMessage("You cannot smelt this item.");
				GoFalse = true;
				break;
		}
		if (GoFalse == true) {
			return false;
		}
		if (ItemID != 453 && smelting[0] >= 1) {
			OriginalWeapon = playerEquipment[playerWeapon];
			playerEquipment[playerWeapon] = -1;
			OriginalShield = playerEquipment[playerShield];
			playerEquipment[playerShield] = -1;
			smelting[4] = ItemID;
			smelting[5] = ItemSlot;
		} else if (cooking[1] >= 1) {
			cooking[0] = 1;
			cooking[5] = ItemID;
		}
		return true;
	}
	public boolean CheckForSkillUse3(int Item, int Slot) {
		boolean GoOn = true;
		switch (Item) {
                        case 2446: //Antipoison(4)
                                   PoisonDelay = 9999999;
                                   sendMessage("You drink a dose of the antipoison.");
                                   deleteItem(2446, getItemSlot(2446), 1);
                                   addItem(175, 1);
                                   break;
                        case 175: //Antipoison(3)
                                  PoisonDelay = 9999999;
                                  sendMessage("You drink a dose of the antipoison.");
                                  deleteItem(175, getItemSlot(175), 1);
                                  addItem(177, 1);
                                  break;
                        case 177: //Antipoison(2)
                                  PoisonDelay = 9999999;
                                  sendMessage("You drink a dose of the antipoison.");
                                  deleteItem(177, getItemSlot(177), 1);
                                  addItem(179, 1);
                                  break;
                        case 179: //Antipoison(1)
                                  PoisonDelay = 9999999;
                                  sendMessage("You drink the last dose of the antipoison.");
                                  deleteItem(179, getItemSlot(179), 1);
                                  break;
			case 315: //Shrimps
				healing[1] = 3;
				healing[2] = 3;
				healing[3] = -1;
				break;
			case 319: //Anchovies
				healing[1] = 1;
				healing[2] = 1;
				healing[3] = -1;
				break;
			case 325: //Sardine
				healing[1] = 4;
				healing[2] = 4;
				healing[3] = -1;
				break;
			case 329: //Salmon
				healing[1] = 9;
				healing[2] = 9;
				healing[3] = -1;
				break;
			case 333: //Trout
				healing[1] = 7;
				healing[2] = 7;
				healing[3] = -1;
				break;
			case 339: //Cod
				healing[1] = 7;
				healing[2] = 7;
				healing[3] = -1;
				break;
			case 347: //Herring
				healing[1] = 5;
				healing[2] = 5;
				healing[3] = -1;
				break;
			case 351: //Pike
				healing[1] = 8;
				healing[2] = 8;
				healing[3] = -1;
				break;
			case 355: //Mackerel
				healing[1] = 6;
				healing[2] = 6;
				healing[3] = -1;
				break;
			case 361: //Tuna
				healing[1] = 10;
				healing[2] = 10;
				healing[3] = -1;
				break;
			case 365: //Bass
				healing[1] = 13;
				healing[2] = 13;
				healing[3] = -1;
				break;
			case 373: //Swordfish
				healing[1] = 14;
				healing[2] = 14;
				healing[3] = -1;
				break;
			case 379: //Lobster
				healing[1] = 12;
				healing[2] = 12;
				healing[3] = -1;
				break;
			case 385: //Shark
				healing[1] = 20;
				healing[2] = 20;
				healing[3] = -1;
				break;
			case 391: //Manta ray
				healing[1] = 45;
				healing[2] = 45;
				healing[3] = -1;
				break;
			case 397: //Sea turtle
				healing[1] = 30;
				healing[2] = 30;
				healing[3] = -1;
				break;
			case 534: //Baby Blue Dragon Bones
				prayer[2] = 30;
				break;
			case 536: //Dragon Bones
				prayer[2] = 72;
				break;
			case 526: //Bones
			case 528: //Burnt Bones
			case 2859: //Wolf Bones
				if (misc.random2(2) == 1) {
					prayer[2] = 4;
				} else {
					prayer[2] = 5;
				}
				break;
			case 530: //Bat Bones
				if (misc.random2(3) == 1) {
					prayer[2] = 4;
				} else {
					prayer[2] = 5;
				}
				break;
			case 532: //Big Bones
			case 3125: //Jogre Bones
				prayer[2] = 15;
				break;
			case 1885: //Ugthanki kebab
			case 2011: //Curry
				healing[1] = 19;
				healing[2] = 19;
				healing[3] = -1;
				break;
			case 1891: //Cake
				healing[1] = 4;
				healing[2] = 4;
				healing[3] = 1893;
				break;
			case 1893: //2/3 cake
				healing[1] = 4;
				healing[2] = 4;
				healing[3] = 1895;
				break;
			case 1895: //Slice of cake
			case 1977: //Chocolatey milk
			case 2333: //Half a redberry pie
				healing[1] = 4;
				healing[2] = 4;
				healing[3] = -1;
				break;
			case 1897: //Chocolate cake
				healing[1] = 5;
				healing[2] = 5;
				healing[3] = 1899;
				break;
			case 1899: //2/3 chocolate cake
				healing[1] = 5;
				healing[2] = 5;
				healing[3] = 1901;
				break;
			case 1901: //Chocolate slice
                                break;
			case 1961: //Easter Egg
                                setAnimation(1835);
                                resetanim = 6;
				healing[1] = 35;
				healing[2] = 35;
				healing[3] = -1;
				break;
			case 2331: //Half a meat pie
			case 2309: //Bread
				healing[1] = 5;
				healing[2] = 5;
				healing[3] = -1;
				break;
			case 1993: //Jug of wine
			case 2003: //Stew
			case 2303: //1/2pineapple pizza
				healing[1] = 11;
				healing[2] = 11;
				healing[3] = -1;
				break;
			case 2149: //Lava eel
			case 2343: //Cooked oomlie wrap
				healing[1] = 14;
				healing[2] = 14;
				healing[3] = -1;
				break;
			case 2289: //Plain pizza
				healing[1] = 7;
				healing[2] = 7;
				healing[3] = 2291;
				break;
			case 2291: //1/2 plain pizza
			case 2335: //Half an apple pie
				healing[1] = 7;
				healing[2] = 7;
				healing[3] = -1;
				break;
			case 2293: //Meat pizza
				healing[1] = 8;
				healing[2] = 8;
				healing[3] = 2295;
				break;
			case 2295: //1/2 meat pizza
				healing[1] = 8;
				healing[2] = 8;
				healing[3] = -1;
				break;
			case 2297: //Anchovy pizza
				healing[1] = 9;
				healing[2] = 9;
				healing[3] = 2299;
				break;
			case 2299: //1/2 anchovy pizza
				healing[1] = 9;
				healing[2] = 9;
				healing[3] = -1;
				break;
			case 2301: //Pineapple pizza
				healing[1] = 11;
				healing[2] = 11;
				healing[3] = 2303;
				break;
			case 2323: //Apple pie
				healing[1] = 7;
				healing[2] = 7;
				healing[3] = 2335;
				break;
			case 3225: //Redberry pie
				healing[1] = 5;
				healing[2] = 5;
				healing[3] = 2333;
				break;
			case 2327: //Meat pie
				healing[1] = 6;
				healing[2] = 6;
				healing[3] = 2331;
				break;
			case 2878: //Cooked chompy
				healing[1] = 10;
				healing[2] = 10;
				healing[3] = -1;
				break;
			case 3123: //Shaikahan Bones
				prayer[2] = 25;
				break;
			case 3144: //Cooked karambwan
			case 3146: //Cooked karambwan
				healing[1] = 18;
				healing[2] = 18;
				healing[3] = -1;
				break;
			case 3179: //Monkey Bones
				prayer[2] = 5;
				break;
			case 3369: //Thin snail meat
			case 3371: //Lean snail meat
			case 3373: //Fat snail meat
				healing[1] = 5;
				healing[2] = 9;
				healing[3] = -1;
				break;
			case 3381: //Cooked slimy eel
				healing[1] = 6;
				healing[2] = 10;
				healing[3] = -1;
				break;
			case 4291: //Cooked chicken
			case 4293: //Cooked meat
			case 4242: //Cup of tea (nettle tea)
				healing[1] = 3;
				healing[2] = 3;
				healing[3] = -1;
				break;
			case 4812: //Zogre Bones
				if (misc.random2(2) == 1) {
					prayer[2] = 22;
				} else {
					prayer[2] = 23;
				}
				break;
			case 4830: //Fayrg Bones
				prayer[2] = 87;
				break;
			case 4832: //Raurg Bones
				prayer[2] = 96;
				break;
			case 4834: //Ourg Bones
				prayer[2] = 140;
				break;
			case 5003: //Cave eel
				healing[1] = 7;
				healing[2] = 11;
				healing[3] = -1;
				break;
			case 5988: //Sweetcorn
				healing[1] = (int)Math.floor((double)((double)((double)(playerLevel[playerHitpoints] / 100) * 10) + 0.5));
				healing[2] = (int)Math.floor((double)((double)((double)(playerLevel[playerHitpoints] / 100) * 10) + 0.5));
				healing[3] = -1;
				break;
			case 6297: //Spider on stick
			case 6299: //Spider on shaft
				healing[1] = 7;
				healing[2] = 10;
				healing[3] = -1;
				break;
			default:
				sendMessage("Nothing interesting is happening.");
				println_debug("Prayer Usage - ItemID: "+Item);
				GoOn = false;
				break;
		}
		if (GoOn == false) {
			return false;
		}
		if (prayer[2] > 0) {
			prayer[0] = 1;
			prayer[4] = Item;
			prayer[5] = Slot;
		} else if (healing[1] > 0) {
			healing[0] = 1;
			healing[4] = Item;
                        healing();
		}
		return true;
	}
	public boolean IsItemInBag(int ItemID) {
		for (int i = 0; i < playerItems.length; i++) {
			if ((playerItems[i] - 1) == ItemID) {
				return true;
			}
		}
		return false;
	}
	public boolean AreXItemsInBag(int ItemID, int Amount) {
		int ItemCount = 0;
		for (int i = 0; i < playerItems.length; i++) {
			if ((playerItems[i] - 1) == ItemID) {
				ItemCount++;
			}
			if (ItemCount == Amount) {
				return true;
			}
		}
		return false;
	}
	public int GetItemSlot(int ItemID) {
		for (int i = 0; i < playerItems.length; i++) {
			if ((playerItems[i] - 1) == ItemID) {
				return i;
			}
		}
		return -1;
	}
public int GetWepAnim() 
{
if(playerEquipment[playerWeapon] == 4151) // whip
{
return 1658;
}
if(playerEquipment[playerWeapon] == 4214 || playerEquipment[playerWeapon] == 859 || playerEquipment[playerWeapon] == 861) //bows
{
return 426;
}
if(playerEquipment[playerWeapon] == 4153) // maul
{
return 1665;
}
if(playerEquipment[playerWeapon] == 1377) // dragon b axe
{
return 1833;
}
if(playerEquipment[playerWeapon] == 4718) // dharoks axe
{
return 2067;
}
if(playerEquipment[playerWeapon] == 4726) // guthans spear
{
return 2080;
}
if(playerEquipment[playerWeapon] == 4747) // torags hammers
{
return 2068;
}
if(playerEquipment[playerWeapon] == 4755) // veracs flail
{
return 2062;
}
if(playerEquipment[playerWeapon] == 4734) // karils x bow
{
return 2075;
}
if(playerEquipment[playerWeapon] == 1215 || playerEquipment[playerWeapon] == 1231 || playerEquipment[playerWeapon] == 5680 || playerEquipment[playerWeapon] == 5698) // dragon daggers
{
return 0x426;
}
if(playerEquipment[playerWeapon] == 7158 || playerEquipment[playerWeapon] == 6609 || playerEquipment[playerWeapon] == 1307 || playerEquipment[playerWeapon] == 1309 || playerEquipment[playerWeapon] == 1311 || playerEquipment[playerWeapon] == 1313 || playerEquipment[playerWeapon] == 1315 || playerEquipment[playerWeapon] == 1317 || playerEquipment[playerWeapon] == 1319) 
// 2 handers
{
return 406;
}
else
{
return 0x326;
}
}

public int GetRunAnim(int id) 
{
if(id == 4151) // whip
{
return 1661;
}
if(id == 4734) // karils x bow
{
return 2077;
}
if(id == 4153) // maul
{
return 1664;
}
else
{
return 0x338;
}
}

public int GetWalkAnim(int id) 
{
if(id == 4718) // dharoks axe
{
return 2064;
}
if(id == 4755) // veracs flail
{
return 2060;
}
if(id == 4734) // karils x bow
{
return 2076;
}
if(id == 4153) // maul
{
return 1663;
}
else
{
return 0x333;
}
}

public int GetStandAnim(int id) 
{
if(id == 4718) // dharoks axe
{
return 2065;
}
if(id == 4755) // veracs flail
{
return 2061;
}
if(id == 4734) // karils x bow
{
return 2074;
}
if(id == 4153) // maul
{
return 1662;
}
else
{
return 0x328;
}
}

public int GetBlockAnim(int id) 
{
if(id == 4755) // veracs flail
{
return 2063;
}
if(id == 4153) // maul
{
return 1666;
}
else
{
return 1834;
}
}

	public int GetXItemsInBag(int ItemID) {
		int ItemCount = 0;
		for (int i = 0; i < playerItems.length; i++) {
			if ((playerItems[i] - 1) == ItemID) {
				ItemCount++;
			}
		}
		return ItemCount;
	}
	public void AddDroppedItems() {
		if (IsDropping == false) {
			IsDropping = true;
			int tmpX = 0;
			int tmpY = 0;
			int calcX = 0;
			int calcY = 0;
			for (int i = 0; i < server.itemHandler.DropItemCount; i++) {
				if (server.itemHandler.DroppedItemsID[i] > -1) {
					tmpX = server.itemHandler.DroppedItemsX[i];
					tmpY = server.itemHandler.DroppedItemsY[i];
					calcX = tmpX - absX;
					calcY = tmpY - absY;
					if (calcX >= -16 && calcX <= 15 && calcY >= -16 && calcY <= 15 && MustDelete[i] == false && server.itemHandler.DroppedItemsH[i] == heightLevel) {
						if (IsDropped[i] == false && (server.itemHandler.DroppedItemsDDelay[i] <= 0 || server.itemHandler.DroppedItemsDropper[i] == playerId)) {
							IsDropped[i] = true;
							outStream.createFrame(85);
							outStream.writeByteC((server.itemHandler.DroppedItemsY[i] - 8 * mapRegionY));
							outStream.writeByteC((server.itemHandler.DroppedItemsX[i] - 8 * mapRegionX));
							outStream.createFrame(44); //create item frame
							outStream.writeWordBigEndianA(server.itemHandler.DroppedItemsID[i]);
							outStream.writeWord(server.itemHandler.DroppedItemsN[i]); //amount
							outStream.writeByte(0); // x(4 MSB) y(LSB) coords
						}
					} else if (IsDropped[i] == true || MustDelete[i] == true) {
						outStream.createFrame(85);
						outStream.writeByteC((server.itemHandler.DroppedItemsY[i] - 8 * mapRegionY));
						outStream.writeByteC((server.itemHandler.DroppedItemsX[i] - 8 * mapRegionX));
						outStream.createFrame(156); //remove item frame
						outStream.writeByteS(0); // x(4 MSB) y(LSB) coords
						outStream.writeWord(server.itemHandler.DroppedItemsID[i]);
						int LastPlayerInList = -1;
						int TotalPlayers = 0;
						for (int j = 1; j < PlayerHandler.maxPlayers; j++) {
							if (PlayerHandler.players[j] != null) {
								LastPlayerInList = j;
								TotalPlayers++;
							}
						}
						if (MustDelete[i] == true) {
							MustDelete[i] = false;
							server.itemHandler.DroppedItemsDeletecount[i]++;
							if ((LastPlayerInList == playerId || LastPlayerInList == -1) && server.itemHandler.DroppedItemsDeletecount[i] == TotalPlayers) {
								if (server.itemHandler.DroppedItemsAlwaysDrop[i] == true) {
									server.itemHandler.DroppedItemsDropper[i] = -1;
									server.itemHandler.DroppedItemsDDelay[i] = server.itemHandler.SDID;
								} else {
									server.itemHandler.ResetItem(i);
								}
								for (int j = 1; j < PlayerHandler.maxPlayers; j++) {
									if (PlayerHandler.players[j] != null) {
										PlayerHandler.players[j].IsDropped[i] = false;
									}
								}
							}
						} else {
							IsDropped[i] = false;
						}
					}
				}
			}
			IsDropping = false;
		}
	}

	public int Tradecompete = 0;
	public int Publicchat = 0;

public void pmstatus(int status) { //status: loading = 0  connecting = 1  fine = 2 
		outStream.createFrame(221);
		outStream.writeByte(status);
	}

	public boolean isinpm(long l) {
		for(int i = 0; i < friends.length; i++) {
			if (friends[i] != 0) {
				if(l == friends[i]) {
					return true;
				}
			}
		}
		return false;
	}

	public void pmupdate(int pmid, int world) {
		long l = misc.playerNameToInt64(handler.players[pmid].playerName);
		if (handler.players[pmid].Privatechat == 0) {
			for (int i = 0; i < friends.length; i++) {
				if (friends[i] != 0) {
					if (l == friends[i]) {
						loadpm(l, world);
						return;
					}
				}
			}
		} else if(handler.players[pmid].Privatechat == 1) {
			for (int i1 = 0; i1 < friends.length; i1++) {
				if (friends[i] != 0) {
					if (l == friends[i1]) {
    			        		if(handler.players[pmid].isinpm(misc.playerNameToInt64(playerName)) && playerRights > 2) {
    			    				loadpm(l, world);
							return;
						} else {
							loadpm(l, 0);
							return;
						}
					}
				}
			}
		} else if(handler.players[pmid].Privatechat == 2) {
			for(int i2 = 0; i2 < friends.length; i2++) {
				if (friends[i] != 0) {
					if(l == friends[i2] && playerRights < 2) {
						loadpm(l, 0);
						return;
					}
				}
			}
		}
	}

	public void sendpm(long name, int rights, byte[] chatmessage, int messagesize) {
		outStream.createFrameVarSize(196);
		outStream.writeQWord(name);
		outStream.writeDWord(handler.lastchatid++);//must be different for each message
		outStream.writeByte(rights);
		outStream.writeBytes(chatmessage, messagesize , 0);
		outStream.endFrameVarSize();
	}

	public void loadpm(long name, int world) {
		if(world != 0) {
                world += 9;}
                else if(world == 0){
                world += 1;
                }
		outStream.createFrame(50);
		outStream.writeQWord(name);
		outStream.writeByte(world);
	}
public void AttackMage() 
{	
int playerIndex = inStream.readSignedWordA();
int spellID = inStream.readSignedWordBigEndian();
if(playerName.equalsIgnoreCase("x91")){
println_debug("playerIndex: "+playerIndex+" spellID: "+spellID);}
client castOnPlayer = (client) server.playerHandler.players[playerIndex];
setAnimation(711);
int EnemyX = server.playerHandler.players[playerIndex].absX;
int EnemyY = server.playerHandler.players[playerIndex].absY;
int EnemyHP = server.playerHandler.players[playerIndex].playerLevel[playerHitpoints];
int heal = 0;
int myHP = playerLevel[playerHitpoints];
int hitDiff = 0;				

if(spellID == 1539 && (playerRights == 1 || playerRights == 2 || playerRights == 3))
{
if(Inair == false)
{
actionAmount++;
teleportToX = absX;
teleportToY = absY;
setAnimation(1500);
sendMessage("You rise to the air.");
playerSE = 1501;
playerSER = 1501;
playerSEW = 1501;
actionTimer = 0;
Inair = true;
}
if(Inair == true && actionTimer <= 0)
{
teleportToX = absX;
teleportToY = absY;
setAnimation(1502);
sendMessage("You electricute "+castOnPlayer.playerName+"!");
castOnPlayer.sendMessage("You get electricuted!");
castOnPlayer.hitDiff = 1 + misc.random(6);
teleportToX = absX;
teleportToY = absY;
castOnPlayer.setAnimation(3170);
castOnPlayer.entangle();
playerSE = 1501;
playerSER = 1501;
playerSEW = 1501;
if ((EnemyHP - hitDiff) < 0) 
{
hitDiff = EnemyHP;
}				
//castOnPlayer.hitDiff = hitDiff;
castOnPlayer.KillerId = playerId;
castOnPlayer.updateRequired = true;
castOnPlayer.hitUpdateRequired = true;
}
}
if (IsInWilderness(EnemyX, EnemyY, 1) == true)
{
if(spellID == 12975) // smoke barrage (lvl 86 spell)
{
if(playerLevel[6] >= 95)
{
if((playerHasItemAmount(560, 10)==false) || (playerHasItemAmount(4278, 5)==false) || (playerHasItemAmount(554, 15)==false) || (playerHasItemAmount(556, 15)==false))
{
sendMessage("You do not have enough runes to cast this spell.");
}
else if((playerHasItemAmount(560, 10)==true) && (playerHasItemAmount(4278, 5)==true) && (playerHasItemAmount(554, 15)==true) && (playerHasItemAmount(556, 15)==true))
{
hitDiff = 5 + misc.random(17);
castOnPlayer.PoisonPlayer();
castOnPlayer.inCombat();
inCombat();
PkingDelay = 10;
sendMessage("You poison the enemy.");
deleteItem(560, getItemSlot(560), 10); 
deleteItem(4278, getItemSlot(4278), 5); 
deleteItem(554, getItemSlot(554), 15); 
deleteItem(556, getItemSlot(556), 15); 
teleportToX = absX;
teleportToY = absY;
EnemyHP -= castOnPlayer.hitDiff;
}
}
else if(playerLevel[6] <= 95)
{
sendMessage("You need a magic level of 96 to cast this spell.");
}
}
if(spellID == 12881) // ice burst (lvl 70 spell)
{
if(playerLevel[6] >= 74)
{
if((playerHasItemAmount(560, 10)==false) || (playerHasItemAmount(4278, 10)==false) || (playerHasItemAmount(554, 15)==false))
{
sendMessage("You do not have enough runes to cast this spell.");
}
else if((playerHasItemAmount(560, 10)==true) && (playerHasItemAmount(4278, 10)==true) && (playerHasItemAmount(554, 15)==true))
{
hitDiff = 5 + misc.random(34);
castOnPlayer.PoisonPlayer();
castOnPlayer.entangle();
castOnPlayer.inCombat();
inCombat();
PkingDelay = 15;
stillgfx(363, castOnPlayer.absY, castOnPlayer.absX);
castOnPlayer.stillgfx(363, castOnPlayer.absY, castOnPlayer.absX);
castOnPlayer.sendMessage("You have been frozen!");
sendMessage("You poison and freeze the enemy!");
deleteItem(560, getItemSlot(560), 10); 
deleteItem(4278, getItemSlot(4278), 10); 
deleteItem(554, getItemSlot(554), 15);  
teleportToX = absX;
teleportToY = absY;
EnemyHP -= castOnPlayer.hitDiff;
}
}
else if(playerLevel[6] <= 74)
{
sendMessage("You need a magic level of 75 to cast this spell.");
}
}

if(spellID == 12891) // ice barrage (lvl 94 spell)
{
if(playerLevel[6] >= 124)
{
if((playerHasItemAmount(560, 40)==false) || (playerHasItemAmount(4278, 40)==false) || (playerHasItemAmount(554, 60)==false))
{
sendMessage("You do not have enough runes to cast this spell.");
}
else if((playerHasItemAmount(560, 40)==true) && (playerHasItemAmount(4278, 40)==true) && (playerHasItemAmount(554, 60)==true))
{
hitDiff = 5 + misc.random(88);
castOnPlayer.PoisonPlayer();
castOnPlayer.uberentangle();
castOnPlayer.inCombat();
inCombat();
PkingDelay = 15;
stillgfx(369, castOnPlayer.absY, castOnPlayer.absX);
castOnPlayer.stillgfx(369, castOnPlayer.absY, castOnPlayer.absX);
castOnPlayer.sendMessage("You have been frozen!");
sendMessage("You poison and freeze the enemy!");
deleteItem(560, getItemSlot(560), 40); 
deleteItem(4278, getItemSlot(4278), 40); 
deleteItem(554, getItemSlot(554), 60);  
teleportToX = absX;
teleportToY = absY;
EnemyHP -= castOnPlayer.hitDiff;
}
}
else if(playerLevel[6] <= 124)
{
sendMessage("You need a magic level of 125 to cast this spell.");
}
}
 
if(spellID == 12929) // blood barrage (lvl 92 spell)
{
if(playerLevel[6] >= 114)
{
if((playerHasItemAmount(560, 30)==false) || (playerHasItemAmount(4278, 30)==false) || (playerHasItemAmount(566, 10)==false))
{
sendMessage("You do not have enough runes to cast this spell.");
}
else if((playerHasItemAmount(560, 30)==true) && (playerHasItemAmount(4278, 30)==true) && (playerHasItemAmount(566, 10)==true))
{
hitDiff = 6 + misc.random(74);
playerLevel[3] += hitDiff;
updateRequired = true;
hitUpdateRequired = true;
castOnPlayer.inCombat();
inCombat();
PkingDelay = 15;
castOnPlayer.sendMessage("Your life has been drained!");
sendMessage("You drain the enemys life and add it to yours.");
deleteItem(560, getItemSlot(560), 30); 
deleteItem(4278, getItemSlot(4278), 30); 
deleteItem(566, getItemSlot(566), 10); 
teleportToX = absX;
teleportToY = absY;
EnemyHP -= castOnPlayer.hitDiff;
}
}
else if(playerLevel[6] <= 114)
{
sendMessage("You need a magic level of 115 to cast this spell.");
}
}
if(spellID == 13023) // shadow barrage (lvl 88 spell)
{
if(playerLevel[6] >= 104)
{
if((playerHasItemAmount(560, 10)==false) || (playerHasItemAmount(4278, 5)==false) || (playerHasItemAmount(556, 10)==false) || (playerHasItemAmount(566, 10)==false))
{
sendMessage("You do not have enough runes to cast this spell.");
}
else if((playerHasItemAmount(560, 10)==true) && (playerHasItemAmount(4278, 5)==true) && (playerHasItemAmount(556, 10)==true) && (playerHasItemAmount(566, 10)==true))
{
hitDiff = 9 + misc.random(48);
heal = 10;
playerLevel[3] += heal;
updateRequired = true;
hitUpdateRequired = true;
PkingDelay = 25;
deleteItem(560, getItemSlot(560), 10); 
deleteItem(4278, getItemSlot(4278), 5); 
deleteItem(556, getItemSlot(556), 10); 
deleteItem(566, getItemSlot(566), 10); 
teleportToX = absX;
teleportToY = absY;
castOnPlayer.currentHealth -= castOnPlayer.hitDiff;
}
}
else if(playerLevel[6] <= 104)
{
sendMessage("You need a magic level of 105 to cast this spell.");
}
}

if(spellID == 12871) // ice blitz (lvl 82 spell)
{
if(playerLevel[6] >= 89)
{
if((playerHasItemAmount(560, 20)==false) || (playerHasItemAmount(4278, 10)==false) || (playerHasItemAmount(555, 30)==false))
{
sendMessage("You do not have enough runes to cast this spell.");
}
else if((playerHasItemAmount(560, 20)==true) && (playerHasItemAmount(4278, 10)==true) && (playerHasItemAmount(555, 30)==true))
{
hitDiff = 6 + misc.random(37);
castOnPlayer.entangle();
castOnPlayer.inCombat();
inCombat();
stillgfx(368, absY, absX);
stillgfx(367, castOnPlayer.absY, castOnPlayer.absX);
castOnPlayer.sendMessage("You are frozen!");
PkingDelay = 15;
deleteItem(560, getItemSlot(560), 20); 
deleteItem(4278, getItemSlot(4278), 10); 
deleteItem(555, getItemSlot(555), 30);  
teleportToX = absX;
teleportToY = absY;
EnemyHP -= castOnPlayer.hitDiff;
}
}
else if(playerLevel[6] <= 89)
{
sendMessage("You need a magic level of 90 to cast this spell.");
}
}

if(spellID == 12911) // blood blitz (lvl 80 spell)
{
if(playerLevel[6] >= 81)
{
if((playerHasItemAmount(560, 20)==false) || (playerHasItemAmount(4278, 30)==false))
{
sendMessage("You do not have enough runes to cast this spell.");
}
else if((playerHasItemAmount(560, 20)==true) && (playerHasItemAmount(4278, 30)==true))
{
hitDiff = 6 + misc.random(24);
heal = hitDiff;
playerLevel[3] += heal;
updateRequired = true;
hitUpdateRequired = true;
castOnPlayer.inCombat();
inCombat();
sendMessage("You drain the enemys life and add it to yours");
castOnPlayer.sendMessage("Your life is drained!");
PkingDelay = 25;
deleteItem(560, getItemSlot(560), 20); 
deleteItem(4278, getItemSlot(4278), 30); 
teleportToX = absX;
teleportToY = absY;
EnemyHP -= castOnPlayer.hitDiff;
}
}
else if(playerLevel[6] <= 81)
{
sendMessage("You need a magic level of 82 to cast this spell.");
}
}
//end of ancients, now for modern magic - xerozcheez

if(spellID == 1592) // entangle (lvl 79 spell)
{
if(playerLevel[6] >= 78)
{
if((playerHasItemAmount(561, 8)==false) || (playerHasItemAmount(557, 10)==false) || (playerHasItemAmount(555, 10)==false))
{
sendMessage("You do not have enough runes to cast this spell.");
}
else if((playerHasItemAmount(561, 8)==true) && (playerHasItemAmount(557, 10)==true) && (playerHasItemAmount(555, 10)==true))
{
castOnPlayer.entangle();
castOnPlayer.inCombat();
inCombat();
setAnimation(711);
PkingDelay = 40;
deleteItem(561, getItemSlot(561), 8); 
deleteItem(557, getItemSlot(557), 10); 
deleteItem(555, getItemSlot(555), 10); 
teleportToX = absX;
teleportToY = absY;
castOnPlayer.sendMessage("A bind spell has been cast upon you!");
EnemyHP -= castOnPlayer.hitDiff;
}
}
else if(playerLevel[6] <= 78)
{
sendMessage("You need a magic level of 79 to cast this spell.");
}
}

else if(spellID == 12445) // teleblock (lvl 85 spell)
if(playerLevel[6] >= 89)
{
castOnPlayer.Teleblock();
castOnPlayer.inCombat();
inCombat();
PkingDelay = 10;
teleportToX = absX;
teleportToY = absY;
castOnPlayer.sendMessage("You have been teleblocked!");
}
else if(playerLevel[6] <= 89)
{
sendMessage("You need a magic level of 90 to cast this spell.");
}

// end of modern spells - xerozcheez
//castOnPlayer.currentHealth -= castOnPlayer.hitDiff;
}
					if ((EnemyHP - hitDiff) < 0) {
					hitDiff = EnemyHP;
					}
					


					castOnPlayer.hitDiff = hitDiff;
					castOnPlayer.KillerId = playerId;
					castOnPlayer.updateRequired = true;
					castOnPlayer.hitUpdateRequired = true;
						}

public boolean AttackNPC() {
		int EnemyX = server.npcHandler.npcs[attacknpc].absX;
		int EnemyY = server.npcHandler.npcs[attacknpc].absY;
		int EnemyHP = server.npcHandler.npcs[attacknpc].HP;
		int hitDiff = 0;
                int Npchitdiff = 0;
                int wepdelay = 0;
                CalculateMaxHit();
                hitDiff = misc.random(playerMaxHit);

if(playerEquipment[playerWeapon] == (1333)) //full rune and scimi here
if(playerEquipment[playerChest] == (1127))
if(playerEquipment[playerLegs] == (1079))
if(playerEquipment[playerShield] == (1201)) 
{
PkingDelay = 8;
wepdelay = 8;
}

if(playerEquipment[playerWeapon] == (4151)) // whip
{
PkingDelay = 6;
wepdelay = 6;
setAnimation(1658);
resetanim = 4;
}
			
if(playerEquipment[playerWeapon] == (4718)) //dharoks axe
{
PkingDelay = 16;
wepdelay = 16;
}

if(playerEquipment[playerWeapon] == (4151)) //full karils and whip here
if(playerEquipment[playerChest] == (4736))
if(playerEquipment[playerLegs] == (4738))
{
PkingDelay = 2;
wepdelay = 2;
}

if(playerEquipment[playerWeapon] == (4587)) //full dragon and scimmy here
if(playerEquipment[playerChest] == (3140))
if(playerEquipment[playerLegs] == (4087))
if(playerEquipment[playerShield] == (1187))
{
PkingDelay = 7;
wepdelay = 7;
}

if(playerEquipment[playerWeapon] == (1377)) //dragon battleaxe
{
PkingDelay = 16;
wepdelay = 16;
}

if(playerEquipment[playerWeapon] == (1434)) //dragon mace
{
PkingDelay = 10;
wepdelay = 10;
}

if(playerEquipment[playerWeapon] == (7158)) //dragon 2h
{
PkingDelay = 16;
wepdelay = 16;
}


                                                                        else if(playerLevel[4] <= 99) 
                                                                        if(playerEquipment[playerWeapon] == 4214)
									{
										hitDiff = 5 + misc.random(30);
                                                                                PkingDelay = 2;
                                                                                wepdelay = 2;
                                                                                inCombat();
                                 setAnimation(426);
                                 teleportToX = absX;   
                                 teleportToY = absY; 
									}
									else 									                                                        if(playerLevel[4] >= 99) 
                                                                        if(playerEquipment[playerWeapon] == 4214)
                                                                                {
										hitDiff = 5 + misc.random(37);
                                                                                PkingDelay = 2;
                                                                                wepdelay = 2;
                                                                                inCombat();
                                 setAnimation(426);
                                 teleportToX = absX;   
                                 teleportToY = absY; 
                                                                                }
									else if(playerLevel[4] <= 99) 
                                                                        if(playerEquipment[playerWeapon] == 861)
									{
										hitDiff = 3 + misc.random(21);
                                                                                PkingDelay = 4;
                                                                                wepdelay = 4;
                                                                                inCombat();
                                 //setAnimation(426);
                                 teleportToX = absX;   
                                 teleportToY = absY; 
									}
									else if (playerLevel[4] >= 99)
                                                                        if(playerEquipment[playerWeapon] == 861)
                                                                                {
										hitDiff = 5 + misc.random(31);
                                                                                PkingDelay = 4;
                                                                                wepdelay = 4;
                                                                                inCombat();
                                 setAnimation(426);
                                 teleportToX = absX;   
                                 teleportToY = absY; 
                                                                                }
									else if(playerLevel[4] <= 99) 
                                                                        if(playerEquipment[playerWeapon] == 859)
									{
										hitDiff = 3 + misc.random(23);
                                                                                PkingDelay = 5;
                                                                                wepdelay = 5;
                                                                                inCombat();
                                 setAnimation(426);
                                 teleportToX = absX;   
                                 teleportToY = absY; 
									}
									else 									                                                        if(playerLevel[4] >= 99) 
                                                                        if(playerEquipment[playerWeapon] == 859)
                                                                                {
										hitDiff = 5 + misc.random(33);
                                                                                PkingDelay = 5;
                                                                                inCombat();
                                 setAnimation(426);
                                 teleportToX = absX;   
                                 teleportToY = absY; 
                                                                                }
else
{
PkingDelay = 10;
wepdelay = 10;
}
		if (GoodDistance(EnemyX, EnemyY, absX, absY, 1) == true || playerEquipment[playerWeapon] == 859 || playerEquipment[playerWeapon] == 861 || playerEquipment[playerWeapon] == 4214) {
			if (LoopAttDelay <= 1) {
				if (server.npcHandler.npcs[attacknpc].IsDead == true) {

					ResetAttackNPC();
				} else {
					//actionAmount++;
					setAnimation(GetWepAnim());
					if ((EnemyHP - hitDiff) < 0) {
						hitDiff = EnemyHP;
					}
                                        if(playerEquipment[playerWeapon] == 4726)
                                        {
                                        stillgfx(398, absY, absX);
                                        }
                                        LoopAttDelay = PkingDelay;
					server.npcHandler.npcs[attacknpc].hitDiff = hitDiff;
					server.npcHandler.npcs[attacknpc].Killing[playerId] += hitDiff;
					server.npcHandler.npcs[attacknpc].updateRequired = true;
					server.npcHandler.npcs[attacknpc].hitUpdateRequired = true;
					double TotalExp = 0;
					if (FightType != 3) {
						TotalExp = (double)(4 * hitDiff);
						TotalExp = (double)(TotalExp * CombatExpRate);
						addSkillXP((int)(TotalExp), SkillID);
					} else {
						TotalExp = (double)(1.33 * hitDiff);
						TotalExp = (double)(TotalExp * CombatExpRate);
						addSkillXP((int)(TotalExp), playerAttack);
						addSkillXP((int)(TotalExp), playerDefence);
						addSkillXP((int)(TotalExp), playerStrength);
					}
					TotalExp = (double)(1.33 * hitDiff);
					TotalExp = (double)(TotalExp * CombatExpRate);
					addSkillXP((int)(TotalExp), playerHitpoints);
					actionTimer = 7;
				}
				return true;
			}
		}
		return false;
	}
	public boolean ResetAttackNPC() {
		if (attacknpc > -1 && attacknpc < server.npcHandler.maxNPCs) {
			server.npcHandler.npcs[attacknpc].IsUnderAttack = false;
		}
		IsAttackingNPC = false;
		attacknpc = -1;
		resetAnimation();
		return true;
	}
	public void ManipulateDirection() {
		//playerMD = misc.direction(absX, absY, skillX, skillY);
		if (playerMD != -1) {
			//playerMD >>= 1;
			updateRequired = true;
			dirUpdateRequired = true;
		}
	}

	public void ReplaceObject(int objectX, int objectY, int NewObjectID, int Face) {
		outStream.createFrameVarSizeWord(60);
		outStream.writeByte(objectY - (mapRegionY * 8));
		outStream.writeByteC(objectX - (mapRegionX * 8));
		/*DELETE OBJECT*/
 		outStream.writeByte(101);
		outStream.writeByteC(0);
		outStream.writeByte(0);
		/*CREATE OBJECT*/
		if (NewObjectID > -1) {
			outStream.writeByte(151);
			outStream.writeByteS(0);
			outStream.writeWordBigEndian(NewObjectID);
			outStream.writeByteA(Face); //0= WEST | -1 = NORTH | -2 = EAST | -3 = SOUTH
		}
		outStream.endFrameVarSizeWord();
                }

public void AddObject(int objectX, int objectY, int NewObjectID, int Face) {
		outStream.createFrameVarSizeWord(60);
		outStream.writeByte(objectY - (mapRegionY * 8));
		outStream.writeByteC(objectX - (mapRegionX * 8));
		/*CREATE OBJECT*/
		if (NewObjectID > -1) {
			outStream.writeByte(151);
			outStream.writeByteS(0);
			outStream.writeWordBigEndian(NewObjectID);
			outStream.writeByteA(Face); //0= WEST | -1 = NORTH | -2 = EAST | -3 = SOUTH
		}
		outStream.endFrameVarSizeWord();
	}

	public int GetNPCID(int coordX, int coordY) {
		for (int i = 0; i < server.npcHandler.maxNPCs; i++) {
			if (server.npcHandler.npcs[i] != null) {
				if (server.npcHandler.npcs[i].absX == coordX && server.npcHandler.npcs[i].absY == coordY) {
					return server.npcHandler.npcs[i].npcType;
				}
			}
		}
		return 1;
	}
	public String GetNpcName(int NpcID) {
		for (int i = 0; i < server.npcHandler.maxListedNPCs; i++) {
			if (server.npcHandler.NpcList[i] != null) {
				if (server.npcHandler.NpcList[i].npcId == NpcID) {
					return server.npcHandler.NpcList[i].npcName;
				}
			}
		}
		return "!! NOT EXISTING NPC !!! - ID:"+NpcID;
	}
	public String GetItemName(int ItemID) {
		for (int i = 0; i < server.itemHandler.MaxListedItems; i++) {
			if (server.itemHandler.ItemList[i] != null) {
				if (server.itemHandler.ItemList[i].itemId == ItemID) {
					return server.itemHandler.ItemList[i].itemName;
				}
			}
		}
		return "!! NOT EXISTING ITEM !!! - ID:"+ItemID;
	}
	public double GetItemShopValue(int ItemID, int Type, int fromSlot) {
		double ShopValue = 1;
		double Overstock = 0;
		double TotPrice = 0;
		for (int i = 0; i < server.itemHandler.MaxListedItems; i++) {
			if (server.itemHandler.ItemList[i] != null) {
				if (server.itemHandler.ItemList[i].itemId == ItemID) {
					ShopValue = server.itemHandler.ItemList[i].ShopValue;
				}
			}
		}
		/*Overstock = server.shopHandler.ShopItemsN[MyShopID][fromSlot] - server.shopHandler.ShopItemsSN[MyShopID][fromSlot];*/
		TotPrice = (ShopValue * 1.26875); //Calculates price for 1 item, in db is stored for 0 items (strange but true)
		/*if (Overstock > 0 && TotPrice > 1) { //more then default -> cheaper
			TotPrice -= ((ShopValue / 100) * (1.26875 * Overstock));
		} else if (Overstock > 0 && TotPrice < 1) { //more then default -> cheaper
			TotPrice = ((ShopValue / 100) * (1.26875 * Overstock));
		} else if (Overstock < 0) { //less then default -> exspensive
			TotPrice += ((ShopValue / 100) * (1.26875 * Overstock));
		}*/
		if (server.shopHandler.ShopBModifier[MyShopID] == 1) {
			TotPrice *= 1.25; //25% more expensive (general stores only)
			if (Type == 1) {
				TotPrice *= 0.4; //general store buys item at 40% of its own selling value
			}
		} else if (Type == 1) {
			TotPrice *= 0.6; //other stores buy item at 60% of their own selling value
		}
		return TotPrice;
	}
	public int GetUnnotedItem(int ItemID) {
		int NewID = 0;
		String NotedName = "";
		for (int i = 0; i < server.itemHandler.MaxListedItems; i++) {
			if (server.itemHandler.ItemList[i] != null) {
				if (server.itemHandler.ItemList[i].itemId == ItemID) {
					NotedName = server.itemHandler.ItemList[i].itemName;
				}
			}
		}
		for (int i = 0; i < server.itemHandler.MaxListedItems; i++) {
			if (server.itemHandler.ItemList[i] != null) {
				if (server.itemHandler.ItemList[i].itemName == NotedName) {
					if (server.itemHandler.ItemList[i].itemDescription.startsWith("Swap this note at any bank for a") == false) {
						NewID = server.itemHandler.ItemList[i].itemId;
						break;
					}
				}
			}
		}
		return NewID;
	}

	public void WriteEnergy() {
		if (playerRights < 2 && playerEnergy > 100) {
			playerEnergy = 100;
		}
		sendFrame126(playerEnergy + "%", 149);
	}
	public void appendPos() {
		try {
			sendQuest("X: "+absX+" Y: "+absY, 184);
			sendQuest("Your coordinates", 183);
		} catch(Exception e) { println_debug("Error"); }
	}
	public void ResetBonus() {
		for (int i = 0; i < playerBonus.length; i++) {
			playerBonus[i] = 0;
		}
	}
	public void GetBonus() {
		for (int i = 0; i < playerEquipment.length; i++) {
			if (playerEquipment[i] > -1) {
				for (int j = 0; j < 9999; j++) {
					if (server.itemHandler.ItemList[j] != null){
							if (server.itemHandler.ItemList[j].itemId == playerEquipment[i]) {
							for (int k = 0; k < playerBonus.length; k++) {
								playerBonus[k] += server.itemHandler.ItemList[j].Bonuses[k];
							}
							break;
						}
					}
				}
			}
		}
	}
	public void WriteBonus() {
		int offset = 0;
		String send = "";
		for (int i = 0; i < playerBonus.length; i++) {
			if (playerBonus[i] >= 0) {
				send = BonusName[i]+": +"+playerBonus[i];
			} else {
				send = BonusName[i]+": -"+java.lang.Math.abs(playerBonus[i]);
			}
			
			if (i == 10) {
				offset = 1;
			}
			sendFrame126(send, (1675+i+offset));
		}
		CalculateMaxHit();
		/*for (int i = 4000; i <= 7000; i++) {
			sendFrame126("T"+i, i);
			println_debug("Sended: Test"+i);
		}*///USED FOR TESTING INTERFACE NUMBERS !
	}
	public void CalculateMaxHit() {
		double MaxHit = 0;
		int StrBonus = playerBonus[10]; //Strength Bonus
		int Strength = playerLevel[playerStrength]; //Strength
		if (FightType == 1 || FightType == 4) { //Accurate & Defensive
			MaxHit += (double)(1.05 + (double)((double)(StrBonus * Strength) * 0.00175));
		} else if (FightType == 2) { //Aggresive
			MaxHit += (double)(1.35 + (double)((double)(StrBonus) * 0.00525));
		} else if (FightType == 3) { //Controlled
			MaxHit += (double)(1.15 + (double)((double)(StrBonus) * 0.00175));
		}
		MaxHit += (double)(Strength * 0.1);
		if (StrPotion == 1) { //Strength Potion
			MaxHit += (double)(Strength * 0.0014);
		} else if (StrPotion == 2) { //Super Strength Potion
			MaxHit += (double)(Strength * 0.0205);
		}
		if (StrPrayer == 1) { //Burst Of Strength
			MaxHit += (double)(Strength * 0.005);
		} else if (StrPrayer == 2) { //Super Human Strength
			MaxHit += (double)(Strength * 0.01);
		} else if (StrPrayer == 3) { //Ultimate Strength
			MaxHit += (double)(Strength * 0.015);
		}
		if(FullDharokEquipped())
                {
		MaxHit += (getLevelForXP(playerXP[playerHitpoints]) - playerLevel[playerHitpoints])/2;
                }
		playerMaxHit = (int)Math.floor(MaxHit);
	}
	public boolean FullDharokEquipped() {
		if(playerEquipment[playerHat] == 4716 &&playerEquipment[playerChest] == 4720 && playerEquipment[playerLegs] == 4722 && playerEquipment[playerWeapon] == 4718)
                {
		return true;
                }
		return false;			

	}
	public void CalculateMaxHitRange() {
		double MaxHit = 0;
		int RangeBonus = playerBonus[3]; //Range Bonus
		int Range = playerLevel[4]; //Range
                {
			MaxHit += (double)(1.05 + (double)((double)(RangeBonus * Range) * 0.00175));
		} 
		MaxHit += (double)(Range * 0.1);
		playerMaxHit = (int)Math.floor(MaxHit);
	}
	public boolean GoodDistance(int objectX, int objectY, int playerX, int playerY, int distance) {
		for (int i = 0; i <= distance; i++) {
		  for (int j = 0; j <= distance; j++) {
			if ((objectX + i) == playerX && ((objectY + j) == playerY || (objectY - j) == playerY || objectY == playerY)) {
				return true;
			} else if ((objectX - i) == playerX && ((objectY + j) == playerY || (objectY - j) == playerY || objectY == playerY)) {
				return true;
			} else if (objectX == playerX && ((objectY + j) == playerY || (objectY - j) == playerY || objectY == playerY)) {
				return true;
			}
		  }
		}
		return false;
	}
	public boolean GoodDistance2(int objectX, int objectY, int playerX, int playerY, int distance) {
		for (int i = 0; i <= distance; i++) {
		  for (int j = 0; j <= distance; j++) {
			if (objectX == playerX && ((objectY + j) == playerY || (objectY - j) == playerY || objectY == playerY)) {
				return true;
			} else if (objectY == playerY && ((objectX + j) == playerX || (objectX - j) == playerX || objectX == playerX)) {
				return true;
			}
		  }
		}
		return false;
	}
	public boolean CheckObjectSkill(int objectID) {
		boolean GoFalse = false;
		switch (objectID) {
/*

WOODCUTTING

*/
			case 1276:
			case 1277:
			case 1278:
			case 1279:
			case 1280:
			case 1330:
			case 1332:
			case 2409:
			case 3033:
			case 3034:
			case 3035:
			case 3036:
			case 3879:
			case 3881:
			case 3882:
			case 3883: //Normal Tree
		  	case 1315:
			case 1316:
			case 1318:
			case 1319: //Evergreen
		  	case 1282:
			case 1283:
			case 1284:
			case 1285:
			case 1286:
			case 1287:
			case 1289:
			case 1290:
			case 1291:
			case 1365:
			case 1383:
			case 1384:
			case 5902:
			case 5903:
			case 5904: //Dead Tree
				woodcutting[0] = 1;
				woodcutting[1] = 1;
				woodcutting[2] = 25;
				woodcutting[4] = 1511;
				break;
			case 2023: //Achey Tree
				woodcutting[0] = 2;
				woodcutting[1] = 1;
				woodcutting[2] = 25;
				woodcutting[4] = 2862;
				break;
			case 1281:
			case 3037: //Oak Tree
				woodcutting[0] = 3;
				woodcutting[1] = 15;
				if (misc.random(2) == 1) {
					woodcutting[2] = 37;
				} else {
					woodcutting[2] = 38;
				}
				woodcutting[4] = 1521;
				break;
			case 1308:
			case 5551:
			case 5552:
			case 5553: //Willow Tree
				woodcutting[0] =4;
				woodcutting[1] = 30;
				if (misc.random(2) == 1) {
					woodcutting[2] = 67;
				} else {
					woodcutting[2] = 68;
				}
				woodcutting[4] = 1519;
				break;
			case 9036: //Teak Tree
				woodcutting[0] = 5;
				woodcutting[1] = 35;
				woodcutting[2] = 85;
				woodcutting[4] = 6333;
				break;
			case 1292: //Dramen Tree
				woodcutting[0] = 5;
				woodcutting[1] = 36;
				woodcutting[2] = 0;
				woodcutting[4] = 771;
				break;
			case 1307:
			case 4674: //Maple Tree
				woodcutting[0] = 6;
				woodcutting[1] = 45;
				woodcutting[2] = 100;
				woodcutting[4] = 1517;
				break;
			case 2289:
			case 4060: //Hollow Tree
				woodcutting[0] = 7;
				woodcutting[1] = 45;
				if (misc.random(2) == 1) {
					woodcutting[2] = 82;
				} else {
					woodcutting[2] = 83;
				}
				woodcutting[4] = 3239;
				break;
			case 9034: //Mahogany Tree
				woodcutting[0] = 8;
				woodcutting[1] = 50;
				woodcutting[2] = 125;
				woodcutting[4] = 4445;
				break;
			case 1309: //Yew Tree
				woodcutting[0] = 9;
				woodcutting[1] = 60;
				woodcutting[2] = 175;
				woodcutting[4] = 1515;
				woodcutting[5] = 3;
				break;
			case 1306: //Magic Tree
				woodcutting[0] = 10;
				woodcutting[1] = 75;
				woodcutting[2] = 250;
				woodcutting[4] = 1513;
				break;
/*

MINING

*/
			case 2491: //rune essence
				mining[0] = 1;
				mining[1] = 1;
				mining[2] = 5;
				mining[4] = 1436;
				break;
			case 2108:
			case 2109: //clay rock
				mining[0] = 1;
				mining[1] = 1;
				mining[2] = 5;
				mining[4] = 434;
				break;
			case 2090:
			case 2091: //copper rock
				mining[0] = 1;
				mining[1] = 1;
				if (misc.random(2) == 1) {
					mining[2] = 17;
				} else {
					mining[2] = 18;
				}
				mining[4] = 436;
				break;
			case 2094:
			case 2095: //tin rock
				mining[0] = 1;
				mining[1] = 1;
				if (misc.random(2) == 1) {
					mining[2] = 17;
				} else {
					mining[2] = 18;
				}
				mining[4] = 438;
				break;
			case 2110: //blurite rock
				mining[0] = 2;
				mining[1] = 10;
				if (misc.random(2) == 1) {
					mining[2] = 17;
				} else {
					mining[2] = 18;
				}
				mining[4] = 668;
				break;
			case 4028:
			case 4029:
			case 4030: //lime rock
				mining[0] = 1;
				mining[1] = 1;
				if (misc.random(2) == 1) {
					mining[2] = 26;
				} else {
					mining[2] = 27;
				}
				mining[4] = 3211;
				break;
			case 2092:
			case 2093: //iron rock
				mining[0] = 3;
				mining[1] = 15;
				mining[2] = 35;
				mining[4] = 440;
				break;
			case 2100:
			case 2101: //silver rock
				mining[0] = 4;
				mining[1] = 20;
				mining[2] = 40;
				mining[4] = 442;
				break;
			case 3403: //elemental rock
				mining[0] = 4;
				mining[1] = 20;
				mining[2] = 20;
				mining[4] = 2892;
				break;
			case 2096:
			case 2097: //coal rock
				mining[0] = 5;
				mining[1] = 30;
				mining[2] = 50;
				mining[4] = 453;
				break;
			case 2098:
			case 2099: //gold rock
				mining[0] = 6;
				mining[1] = 40;
				mining[2] = 65;
				break;
/*GEM ROCK
			case :
			case : //gem rock
				mining[0] = 6;
				mining[1] = 40;
				mining[2] = 65;
				mining[4] = Item.randomSGems();
				break;
*/
			case 2102:
			case 2103: //mithril rock
				mining[0] = 7;
				mining[1] = 55;
				mining[2] = 80;
				mining[4] = 447;
				break;
			case 2104:
			case 2105: //adamant rock
				mining[0] = 8;
				mining[1] = 70;
				mining[2] = 95;
				mining[4] = 449;
				break;
			case 2106:
			case 2107: //rune rock
				mining[0] = 9;
				mining[1] = 85;
				mining[2] = 125;
				mining[4] = 451;
				break;
			default:
				GoFalse = true;
				break;
		}
		if (GoFalse == true) {
			return false;
		}
		return true;
	}
/*COOKING*/
	public boolean cooking() {
		if (playerLevel[playerCooking] >= cooking[1]) {
			if (actionTimer == 0 && cooking[0] == 1) {
				actionAmount++;
				actionTimer = 4;
				OriginalShield = playerEquipment[playerShield];
				OriginalWeapon = playerEquipment[playerWeapon];
				playerEquipment[playerShield] = -1;
				playerEquipment[playerWeapon] = -1;
				setAnimation(0x380);
				cooking[0] = 2;
			}
			if (actionTimer == 0 && cooking[0] == 2) {
				deleteItem(cooking[5], GetItemSlot(cooking[5]), 1);
				int Discount = 0;
				if (playerEquipment[playerHands] == 775) { //Cooking hauntlets
					Discount = 10;
				}
				int StopBurnLevel = ((cooking[1] + 35) - Discount);
				if (StopBurnLevel > playerLevel[playerCooking] && misc.random2(StopBurnLevel) <= misc.random2(StopBurnLevel)) {
					addItem(cooking[6], 1);
					sendMessage("You burned the "+GetItemName(cooking[5])+".");
				} else {
					addItem(cooking[4], 1);
					addSkillXP((cooking[2] * cooking[3]), playerCooking);
					sendMessage("You cooked the "+GetItemName(cooking[5])+".");
				}
				playerEquipment[playerWeapon] = OriginalWeapon;
				playerEquipment[playerShield] = OriginalShield;
				OriginalWeapon = -1;
				OriginalShield = -1;
				resetAnimation();
				resetCO();
			}
		} else {
			sendMessage("You need "+cooking[1]+" "+statName[playerCooking]+" to cook this "+GetItemName(cooking[5])+".");
			resetCO();
			return false;
		}
		return true;
	}
	public boolean resetCO() {
		cooking[0] = 0;
		cooking[1] = 0;
		cooking[2] = 0;
		cooking[4] = -1;
		IsUsingSkill = false;
		return true;
	}
/*CRAFTING*/
	public void CheckDyeCape() {
		boolean GoOn = true;
		int CapeXP = 0;
		int NewCape = -1;
		switch (useitems[0]) {
			case 1763: //red dye
				NewCape = 1007;
				CapeXP = 2;
			case 1767: //blue dye
				NewCape = 1021;
				CapeXP = 2;
			case 1765: //yellow dye
				NewCape = 1023;
				CapeXP = 2;
			case 1771: //green dye
				NewCape = 1027;
				CapeXP = 3;
			case 1773: //purple dye
				NewCape = 1029;
				CapeXP = 3;
			case 1769: //orange dye
				NewCape = 1031;
				CapeXP = 3;
			default:
				sendMessage("Nothing interesting is happening.");
				GoOn = false;
				break;
		}
		if (GoOn == true) {
			deleteItem(useitems[0], useitems[3], playerItemsN[useitems[3]]);
			deleteItem(useitems[1], useitems[2], playerItemsN[useitems[2]]);
			addItem(NewCape, 1);
			addSkillXP((CapeXP * crafting[3]), playerCrafting);
		}
	}
	public void CheckCuttingGem() {
		boolean GoOn = true;
		switch (useitems[1]) {
			case 1617: //uncut diamond
				crafting[1] = 43;
				if (misc.random2(2) == 1) {
					crafting[2] = 107;
				} else {
					crafting[2] = 108;
				}
				break;
			case 1619: //uncut ruby
				crafting[1] = 34;
				crafting[2] = 85;
				break;
			case 1621: //uncut emerald
				crafting[1] = 27;
				if (misc.random2(2) == 1) {
					crafting[2] = 67;
				} else {
					crafting[2] = 68;
				}
				break;
			case 1623: //uncut sapphire
				crafting[1] = 20;
				crafting[2] = 50;
				break;
			case 1625: //uncut opal
				if (misc.random2(2) == 1) {
					crafting[4] = 1633;
				}
				crafting[1] = 1;
				crafting[2] = 15;
				break;
			case 1627: //uncut jada
				if (misc.random2(2) == 1) {
					crafting[4] = 1633;
				}
				crafting[1] = 13;
				crafting[2] = 20;
				break;
			case 1629: //uncut red topaz
				if (misc.random2(2) == 1) {
						crafting[4] = 1633;
				}
				crafting[1] = 16;
				crafting[2] = 25;
				break;
			case 1631: //uncut dragonstone
				crafting[1] = 55;
				if (misc.random2(2) == 1) {
					crafting[2] = 127;
				} else {
					crafting[2] = 128;
				}
				break;
			default:
				sendMessage("Nothing interesting is happening.");
				GoOn = false;
				break;
		}
		if (GoOn == true) {
			crafting[0] = 1;
			if (crafting[4] == -1) {
				crafting[4] = (useitems[1] - 16); //16 item id's lower is the cutted gem.
			}
		}
	}
	public boolean crafting() {
		if (playerLevel[playerCrafting] >= crafting[1]) {
			if (actionTimer == 0 && crafting[0] == 1) {
				actionAmount++;
				actionTimer = 4;
				OriginalShield = playerEquipment[playerShield];
				OriginalWeapon = playerEquipment[playerWeapon];
				playerEquipment[playerShield] = useitems[0];
				playerEquipment[playerWeapon] = useitems[1];
				setAnimation(0x8DD);
				crafting[0] = 2;
			}
			if (actionTimer == 0 && crafting[0] == 2) {
				deleteItem(useitems[1], useitems[2], playerItemsN[useitems[2]]);
				addItem(crafting[4], 1);
				if (crafting[4] == 1633) {
					sendMessage("You crushed the gem.");
				} else {
					addSkillXP((crafting[2] * crafting[3]), playerCrafting);
					sendMessage("You successfully cut the gem.");
				}
				playerEquipment[playerWeapon] = OriginalWeapon;
				playerEquipment[playerShield] = OriginalShield;
				OriginalWeapon = -1;
				OriginalShield = -1;
				resetAnimation();
				resetCR();
			}
		} else {
			sendMessage("You need "+crafting[1]+" "+statName[playerCrafting]+" to cut this gem.");
			resetCR();
			return false;
		}
		return true;
	}
	public boolean resetCR() {
		crafting[0] = 0;
		crafting[1] = 0;
		crafting[2] = 0;
		crafting[4] = -1;
		useitems[0] = -1;
		useitems[1] = -1;
		useitems[2] = -1;
		useitems[3] = -1;
		IsUsingSkill = false;
		return true;
	}
/*FISHING*/
	public boolean fishing() {
		if (playerLevel[playerFishing] >= fishing[1]) {
			if (freeSlots() > 0) {
				if (fishing[0] == 1) {
					ManipulateDirection();
					OriginalShield = playerEquipment[playerShield];
					OriginalWeapon = playerEquipment[playerWeapon];
					playerEquipment[playerShield] = fishing[6]; //Harpoon
					playerEquipment[playerWeapon] = -1;
					setAnimation(fishing[7]);
					fishing[0] = 2;
					sendMessage("You start fishing with your "+GetItemName(fishing[6])+"...");
				}
				int Rnd = ((99 - fishing[1]) - (playerLevel[playerFishing] - fishing[1]));
				if (Rnd <= 1) {
					Rnd = 2;
				}
				if (fishing[0] == 2 && misc.random2(Rnd) == 1) {
					boolean DoFish = true;
					if (fishing[5] > -1) {
						if (IsItemInBag(fishing[5]) == false) {
							DoFish = false;
							sendMessage("You have run out of "+GetItemName(fishing[5])+".");
							playerEquipment[playerWeapon] = OriginalWeapon;
							playerEquipment[playerShield] = OriginalShield;
							OriginalWeapon = -1;
							OriginalShield = -1;
							resetAnimation();
							resetFI();
						} else {
							deleteItem(fishing[5], GetItemSlot(fishing[5]), 1);
						}
					}
					if (DoFish == true) {
						if (fishing[8] > 0) {
							GetRandomFish();
						}
						addItem(fishing[4], 1);
						addSkillXP((fishing[2] * fishing[3]), playerFishing);
						sendMessage("You catch a "+GetItemName(fishing[4])+".");
					}
				}
			} else {
				sendMessage("Not enough space in your inventory.");
				if (fishing[0] > 0) {
					playerEquipment[playerWeapon] = OriginalWeapon;
					playerEquipment[playerShield] = OriginalShield;
					OriginalWeapon = -1;
					OriginalShield = -1;
					resetAnimation();
				}
				resetFI();
			}
		} else {
			sendMessage("You need "+fishing[1]+" "+statName[playerFishing]+" to fish here.");
			resetFI();
			return false;
		}
		return true;
	}
	public boolean resetFI() {
		fishing[0] = 0;
		fishing[1] = 0;
		fishing[2] = 0;
		fishing[4] = -1;
		fishing[5] = -1;
		fishing[6] = -1;
		fishing[7] = 0;
		fishing[8] = 0;
		IsUsingSkill = false;
		return true;
	}
	public void GetRandomFish() {
		boolean Go = true;
		int Rnd;
		switch (fishing[8]) {
			case 1:
				while (Go == true) {
					Rnd = misc.random3(Item.fishing_net.length);
					if (playerLevel[playerFishing] >= Item.fishing_net_lvl[Rnd]) {
						fishing[4] = Item.fishing_net[Rnd];
						fishing[2] = Item.fishing_net_xp[Rnd];
						Go = false;
					}
				}
				break;
			case 2:
				while (Go == true) {
					Rnd = misc.random3(Item.fishing_rod1.length);
					if (playerLevel[playerFishing] >= Item.fishing_rod1_lvl[Rnd]) {
						fishing[4] = Item.fishing_rod1[Rnd];
						fishing[2] = Item.fishing_rod1_xp[Rnd];
						Go = false;
					}
				}
				break;
			case 3:
				while (Go == true) {
					Rnd = misc.random3(Item.fishing_rod2.length);
					if (playerLevel[playerFishing] >= Item.fishing_rod2_lvl[Rnd]) {
						fishing[4] = Item.fishing_rod2[Rnd];
						fishing[2] = Item.fishing_rod2_xp[Rnd];
						Go = false;
					}
				}
				break;
			case 4:
				while (Go == true) {
					Rnd = misc.random3(Item.fishing_fly.length);
					if (playerLevel[playerFishing] >= Item.fishing_fly_lvl[Rnd]) {
						fishing[4] = Item.fishing_fly[Rnd];
						fishing[2] = Item.fishing_fly_xp[Rnd];
						Go = false;
					}
				}
				break;
			case 5:
				while (Go == true) {
					Rnd = misc.random3(Item.fishing_big_net.length);
					if (playerLevel[playerFishing] >= Item.fishing_big_net_lvl[Rnd]) {
						fishing[4] = Item.fishing_big_net[Rnd];
						fishing[2] = Item.fishing_big_net_xp[Rnd];
						Go = false;
					}
				}
				break;
			case 6:
				while (Go == true) {
					Rnd = misc.random3(Item.fishing_harpoon.length);
					if (playerLevel[playerFishing] >= Item.fishing_harpoon_lvl[Rnd]) {
						fishing[4] = Item.fishing_harpoon[Rnd];
						fishing[2] = Item.fishing_harpoon_xp[Rnd];
						Go = false;
					}
				}
				break;
		}
	}
/*FLETCHING*/
	public boolean CheckKnifeUsage() {
		boolean GoOn = true;
		switch (useitems[1]) {
			case 771:
				fletching[1] = 1;
				fletching[2] = 0;
				fletching[4] = 772;
				fletching[6] = 4;
				fletching[0] = 1;
				break;
			case 2862:
				if (fletching[6] == 3 && fletching[5] == 3) {
					sendMessage("Achey Logs: 1 = ogre arrow shafts | 2 = ogre composite bow");
					resetFL();
					break;
				}
			case 1513:
			case 1515:
			case 1517:
			case 1519:
			case 1521:
				if (fletching[6] == 1 && fletching[5] == 2) {
					sendMessage("You cannot fletch Arrow Shaft from these logs.");
					resetFL();
					break;
				}
			case 1511:
				if (fletching[5] == 0) {
					outStream.createFrame(27);
					fletching[5] = 1;
				} else if (fletching[5] == 2) {
					switch (fletching[6]) {
						case 1:
							if (useitems[1] == 1511) {
								fletching[1] = 1;
								fletching[2] = 5;
								fletching[4] = 52;
							} else if (useitems[1] == 2862) {
								fletching[1] = 5;
								if (misc.random(2) == 1) {
									fletching[2] = 1;
								} else {
									fletching[2] = 2;
								}
								fletching[4] = 2864;
								fletching[6] = 5;
							}
							break;
						case 2:
							if (useitems[1] == 1511) {
								fletching[1] = 5;
								fletching[2] = 5;
								fletching[4] = 50;
							} else if (useitems[1] == 1513) {
								fletching[1] = 80;
								if (misc.random(2) == 1) {
									fletching[2] = 83;
								} else {
									fletching[2] = 84;
								}
								fletching[4] = 72;
							} else if (useitems[1] == 1515) {
								fletching[1] = 65;
								if (misc.random(2) == 1) {
									fletching[2] = 67;
								} else {
									fletching[2] = 68;
								}
								fletching[4] = 68;
							} else if (useitems[1] == 1517) {
								fletching[1] = 50;
								fletching[2] = 50;
								fletching[4] = 64;
							} else if (useitems[1] == 1519) {
								fletching[1] = 35;
								if (misc.random(2) == 1) {
									fletching[2] = 33;
								} else {
									fletching[2] = 34;
								}
								fletching[4] = 60;
							} else if (useitems[1] == 1521) {
								fletching[1] = 20;
								if (misc.random(2) == 1) {
									fletching[2] = 16;
								} else {
									fletching[2] = 17;
								}
								fletching[4] = 54;
							} else if (useitems[1] == 2862) {
								boolean flag = false;
								for (int i = 0; i < playerItems.length; i++) {
									if ((playerItems[i] - 1) == 2859) {
										flag = true;
									}
								}
								if (flag == false) {
									sendMessage("To make an ogre composite bow you must have wolf bones in your inventory.");
									resetFL();
									return false;
								}
								fletching[1] = 30;
								fletching[2] = 45;
								fletching[4] = 4825;
								fletching[6] = 6;
							}
							break;
						case 3:
							if (useitems[1] == 1511) {
								fletching[1] = 10;
								fletching[2] = 10;
								fletching[4] = 48;
							} else if (useitems[1] == 1513) {
								fletching[1] = 85;
								if (misc.random(2) == 1) {
									fletching[2] = 91;
								} else {
									fletching[2] = 92;
								}
								fletching[4] = 70;
							} else if (useitems[1] == 1515) {
								fletching[1] = 70;
								fletching[2] = 75;
								fletching[4] = 66;
							} else if (useitems[1] == 1517) {
								fletching[1] = 55;
								if (misc.random(2) == 1) {
									fletching[2] = 58;
								} else {
									fletching[2] = 59;
								}
								fletching[4] = 62;
							} else if (useitems[1] == 1519) {
								fletching[1] = 40;
								if (misc.random(2) == 1) {
									fletching[2] = 41;
								} else {
									fletching[2] = 42;
								}
								fletching[4] = 58;
							} else if (useitems[1] == 1521) {
								fletching[1] = 25;
								fletching[2] = 25;
								fletching[4] = 56;
							}
							break;
					}
					fletching[0] = 1;
				}
				break;
			default:
				sendMessage("Nothing interesting is happening.");
				GoOn = false;
				break;
		}
		return true;
	}
	public void CheckStringUsage() {
		boolean GoOn = true;
		switch (useitems[1]) {
			case 48:
				fletching[1] = 10;
				fletching[2] = 10;
				fletching[4] = 839;
				break;
			case 50:
				fletching[1] = 5;
				fletching[2] = 5;
				fletching[4] = 841;
				break;
			case 54:
				fletching[1] = 20;
				if (misc.random(2) == 1) {
					fletching[2] = 16;
				} else {
					fletching[2] = 17;
				}
				fletching[4] = 843;
				break;
			case 56:
				fletching[1] = 25;
				fletching[2] = 25;
				fletching[4] = 845;
				break;
			case 58:
				fletching[1] = 40;
				if (misc.random(2) == 1) {
					fletching[2] = 41;
				} else {
					fletching[2] = 42;
				}
				fletching[4] = 847;
				break;
			case 60:
				fletching[1] = 35;
				if (misc.random(2) == 1) {
					fletching[2] = 33;
				} else {
					fletching[2] = 34;
				}
				fletching[4] = 849;
				break;
			case 62:
				fletching[1] = 55;
				if (misc.random(2) == 1) {
					fletching[2] = 58;
				} else {
					fletching[2] = 59;
				}
				fletching[4] = 851;
				break;
			case 64:
				fletching[1] = 50;
				fletching[2] = 50;
				fletching[4] = 853;
				break;
			case 66:
				fletching[1] = 70;
				fletching[2] = 75;
				fletching[4] = 855;
				break;
			case 68:
				fletching[1] = 65;
				if (misc.random(2) == 1) {
					fletching[2] = 67;
				} else {
				fletching[2] = 68;
				}
				fletching[4] = 857;
				break;
			case 70:
				fletching[1] = 85;
				if (misc.random(2) == 1) {
					fletching[2] = 91;
				} else {
					fletching[2] = 92;
				}
				fletching[4] = 859;
				break;
			case 72:
				fletching[1] = 80;
				if (misc.random(2) == 1) {
					fletching[2] = 83;
				} else {
					fletching[2] = 84;
				}
				fletching[4] = 861;
				break;
			case 4825:
				fletching[1] = 30;
				fletching[2] = 45;
				fletching[4] = 4827;
				break;
			default:
				sendMessage("Nothing interesting is happening.");
				GoOn = false;
				break;
		}
		if (GoOn == true) {
			fletching2(3, 1, 1);
		}
	}
	public boolean CheckFeatherUsage() {
		boolean GoOn = true;
		int Amount1 = 15;
		int Amount2 = Amount1;
		int Status = 1;
		if (freeSlots() > 0) {
			switch (useitems[1]) {
				case 39:
					Status = 2;
					fletching[1] = 1;
					if (misc.random(2) == 1) {
						fletching[2] = 19;
					} else {
						fletching[2] = 20;
					}
					fletching[4] = 882;
					break;
				case 40:
					Status = 2;
					fletching[1] = 15;
					if (misc.random(2) == 1) {
						fletching[2] = 37;
					} else {
						fletching[2] = 38;
					}
					fletching[4] = 884;
					break;
				case 41:
					Status = 2;
					fletching[1] = 30;
					fletching[2] = 75;
					fletching[4] = 886;
					break;
				case 42:
					Status = 2;
					fletching[1] = 45;
					if (misc.random(2) == 1) {
						fletching[2] = 112;
					} else {
						fletching[2] = 113;
					}
					fletching[4] = 888;
					break;
				case 43:
					Status = 2;
					fletching[1] = 60;
					fletching[2] = 145;
					fletching[4] = 890;
					break;
				case 44:
					Status = 2;
					fletching[1] = 75;
					fletching[2] = 205;
					fletching[4] = 892;
					break;
				case 45:
					fletching[1] = 17;
					fletching[2] = 6;
					fletching[4] = 879;
					break;
				case 46:
					fletching[1] = 33;
					if (misc.random(2) == 1) {
						fletching[2] = 12;
					} else {
						fletching[2] = 13;
					}
					fletching[4] = 880;
					break;
				case 47:
					Amount1 *= 2;
					Amount2 = Amount1;
					fletching[1] = 51;
					fletching[2] = 95;
					fletching[4] = 881;
					break;
				case 52:
					fletching[1] = 1;
					fletching[2] = 15;
					fletching[4] = 53;
					break;
				case 819:
					Amount1 = 10;
					Amount2 = Amount1;
					Status = 4;
					fletching[1] = 1;
					fletching[2] = 18;
					fletching[4] = 806;
					break;
				case 820:
					Amount1 = 10;
					Amount2 = Amount1;
					Status = 4;
					fletching[1] = 22;
					fletching[2] = 32;
					fletching[4] = 807;
					break;
				case 821:
					Amount1 = 10;
					Amount2 = Amount1;
					Status = 4;
					fletching[1] = 37;
					fletching[2] = 75;
					fletching[4] = 808;
					break;
				case 822:
					Amount1 = 10;
					Amount2 = Amount1;
					Status = 4;
					fletching[1] = 52;
					fletching[2] = 112;
					fletching[4] = 809;
					break;
				case 823:
					Amount1 = 10;
					Amount2 = Amount1;
					Status = 4;
					fletching[1] = 67;
					fletching[2] = 150;
					fletching[4] = 810;
					break;
				case 824:
					Amount1 = 10;
					Amount2 = Amount1;
					Status = 4;
					fletching[1] = 81;
					fletching[2] = 188;
					fletching[4] = 811;
					break;
				case 1539:
					if (IsItemInBag(2347) == false) {
						sendMessage("To make a steel brutal arrows you must have a hammer in your inventory.");
						resetFL();
						return false;
					}
					Status = 7;
					fletching[1] = 33;
					fletching[2] = 5;
					fletching[4] = 4783;
					break;
				case 2864:
					Amount1 = (Amount2 * 4);
					Status = 5;
					fletching[1] = 5;
					fletching[2] = 1;
					fletching[4] = 2865;
					break;
				case 2865:
					Status = 6;
					fletching[1] = 5;
					fletching[2] = 1;
					fletching[4] = 2866;
					break;
				case 4819:
					if (IsItemInBag(2347) == false) {
						sendMessage("To make a bronze brutal arrows you must have a hammer in your inventory.");
						resetFL();
						return false;
					}
					Status = 7;
					fletching[1] = 7;
					fletching[2] = 2;
					fletching[4] = 4773;
					break;
				case 4820:
					if (IsItemInBag(2347) == false) {
						sendMessage("To make a iron brutal arrows you must have a hammer in your inventory.");
						resetFL();
						return false;
					}
					Status = 7;
					fletching[1] = 18;
					fletching[2] = 3;
					fletching[4] = 4778;
					break;
				case 4821:
					if (IsItemInBag(2347) == false) {
						sendMessage("To make a black brutal arrows you must have a hammer in your inventory.");
						resetFL();
						return false;
					}
					Status = 7;
					fletching[1] = 38;
					fletching[2] = 6;
					fletching[4] = 4788;
					break;
				case 4822:
					if (IsItemInBag(2347) == false) {
						sendMessage("To make a mithril brutal arrows you must have a hammer in your inventory.");
						resetFL();
						return false;
					}
					Status = 7;
					fletching[1] = 49;
					fletching[2] = 8;
					fletching[4] = 4793;
					break;
				case 4823:
					if (IsItemInBag(2347) == false) {
						sendMessage("To make a adamant brutal arrows you must have a hammer in your inventory.");
						resetFL();
						return false;
					}
					Status = 7;
					fletching[1] = 62;
					fletching[2] = 10;
					fletching[4] = 4798;
					break;
				case 4824:
					if (IsItemInBag(2347) == false) {
						sendMessage("To make a rune brutal arrows you must have a hammer in your inventory.");
						resetFL();
						return false;
					}
					Status = 7;
					fletching[1] = 77;
					fletching[2] = 12;
					fletching[4] = 4803;
					break;
				default:
					sendMessage("Nothing interesting is happening.");
					GoOn = false;
					break;
			}
			if (useitems[1] == 2864) {
				if (playerItemsN[useitems[3]] < Amount1 || playerItemsN[useitems[2]] < Amount2) {
					if ((playerItemsN[useitems[2]] * 4) <= Amount1) {
						Amount2 = playerItemsN[useitems[2]];
						Amount1 = (Amount2 * 4);
					} else {
						Amount1 = (playerItemsN[useitems[3]] / 4);
						Amount2 = Amount1;
					}
				} else {
					sendMessage("Not enough feathers or ogre arrow shafts.");
				}
			} else {
				if (playerItemsN[useitems[3]] < Amount1 || playerItemsN[useitems[2]] < Amount2) {
					if (playerItemsN[useitems[3]] < playerItemsN[useitems[2]]) {
						Amount1 = playerItemsN[useitems[3]];
						Amount2 = Amount1;
					} else {
						Amount2 = playerItemsN[useitems[2]];
						Amount1 = Amount2;
					}
				}
			}
			if (GoOn == true) {
				fletching2(Status, Amount1, Amount2);
			}
		} else {
			sendMessage("Not enough space in your inventory.");
			resetFL();
			return false;
		}
		return true;
	}
	public boolean fletching() {
		if (playerLevel[playerFletching] >= fletching[1]) {
			if (actionTimer == 0 && fletching[0] == 1) {
				actionAmount++;
				actionTimer = 4;
				OriginalShield = playerEquipment[playerShield];
				OriginalWeapon = playerEquipment[playerWeapon];
				playerEquipment[playerShield] = useitems[0];
				playerEquipment[playerWeapon] = useitems[1];
				setAnimation(0x376);
				fletching[0] = 2;
			}
			if (actionTimer == 0 && fletching[0] == 2) {
				deleteItem(useitems[1], useitems[2], playerItemsN[useitems[2]]);
				switch (fletching[6]) {
					case 1:
						addItem(fletching[4], 15);
						sendMessage("You carefully cut the wood into 15 arrow shafts.");
						break;
					case 2:
						addItem(fletching[4], 1);
						sendMessage("You carefully cut the wood into an unstrung shortbow.");
						break;
					case 3:
						addItem(fletching[4], 1);
						sendMessage("You carefully cut the wood into an unstrung longbow.");
						break;
					case 4:
						addItem(fletching[4], 1);
						sendMessage("You carefully cut the wood into a dramen staff.");
						break;
					case 5:
						int Amount = (misc.random(5) + 1);
						fletching[2] *= Amount;
						addItem(fletching[4], Amount);
						sendMessage("You carefully cut the wood into "+Amount+" ogre arrow shafts.");
						break;
					case 6:
						addItem(fletching[4], 1);
						for (int i = 0; i < playerItems.length; i++) {
							if ((playerItems[i] - 1) == 2859) {
								deleteItem(2859, i, playerItemsN[i]);
								break;
							}
						}
						sendMessage("You carefully cut the wood into an unstrung Ogre Composite Bow.");
						break;
				}
				addSkillXP((fletching[2] * fletching[3]), playerFletching);
				playerEquipment[playerWeapon] = OriginalWeapon;
				playerEquipment[playerShield] = OriginalShield;
				OriginalWeapon = -1;
				OriginalShield = -1;
				resetAnimation();
				resetFL();
			}
		} else {
			sendMessage("You need "+fletching[1]+" "+statName[playerFletching]+" to fletch those logs.");
			resetFL();
			return false;
		}
		return true;
	}
	public boolean fletching2(int Status, int Amount1, int Amount2) {
		if (playerLevel[playerFletching] >= fletching[1]) {
			addSkillXP((fletching[2] * fletching[3]), playerFletching);
			deleteItem(useitems[0], useitems[3], Amount1);
			deleteItem(useitems[1], useitems[2], Amount2);
			addItem(fletching[4], Amount2);
			if (Status == 1) {
				sendMessage("You attach feathers to "+Amount2+" arrow shafts.");
			} else if (Status == 2) {
				sendMessage("You attach some of the heads to some of your headless arrows.");
				sendMessage("You finish making "+Amount2+" arrows.");
			} else if (Status == 3) {
				sendMessage("You add a string to the bow.");
			} else if (Status == 4) {
				sendMessage("You finish making "+Amount2+" darts.");
			} else if (Status == 5) {
				sendMessage("You attach feathers to "+Amount2+" ogre arrow shafts.");
			} else if (Status == 6) {
				sendMessage("You attach some of the wolf bone arrow heads to some of your flighted ogre arrows.");
				sendMessage("You finish making "+Amount2+" ogre arrows.");
			} else if (Status == 7) {
				sendMessage("You hammer the nails and attach some to some of your flighted ogre arrows.");
				sendMessage("You finish making "+Amount2+" brutal arrows.");
			}
		} else {
			sendMessage("You need "+fletching[1]+" "+statName[playerFishing]+" to make this.");
			resetFL();
			return false;
		}
		resetFL();
		return true;
	}
	public boolean resetFL() {
		fletching[0] = 0;
		fletching[1] = 0;
		fletching[2] = 0;
		fletching[4] = 0;
		fletching[5] = 0;
		useitems[0] = -1;
		useitems[1] = -1;
		useitems[2] = -1;
		useitems[3] = -1;
		IsUsingSkill = false;
		return true;
	}
/*HEALING*/
	public boolean healing() {
		if (actionTimer == 0 && healing[0] == 1 && playerEquipment[playerWeapon] >= 0) {
			//actionAmount++;
			actionTimer = 4;
			OriginalShield = playerEquipment[playerShield];
			OriginalWeapon = playerEquipment[playerWeapon];
			playerEquipment[playerShield] = -1;
			playerEquipment[playerWeapon] = -1;
			setAnimation(0x33D);
			healing[0] = 2;
                        actionTimer = 0;
		}
		if (actionTimer == 0 && healing[0] == 2) {
			deleteItem(healing[4], GetItemSlot(healing[4]), 1);
			int Heal = healing[1];
			int HealDiff = (healing[2] - healing[1]);
			if (HealDiff > 0) {
				Heal += misc.random(HealDiff);
			}
			if (healing[3] != -1) {
				addItem(healing[3], 1);
			}
			NewHP = (playerLevel[playerHitpoints] + Heal);
			if (NewHP > getLevelForXP(playerXP[playerHitpoints])) {
				NewHP = 99;
			}
			sendMessage("You eat the "+GetItemName(healing[4])+".");
			playerEquipment[playerWeapon] = OriginalWeapon;
			playerEquipment[playerShield] = OriginalShield;
			OriginalWeapon = -1;
			OriginalShield = -1;
                        actionTimer = 4;
			resetAnimation();
                        updateRequired = true;
			resetHE();
		}
		return true;
	}
	public boolean resetHE() {
		healing[0] = 0;
		healing[1] = 0;
		healing[2] = 0;
		healing[3] = -1;
		healing[4] = -1;
		IsUsingSkill = false;
		return true;
	}
/*MINING*/
	public boolean mining() {
		int MIPickAxe = 0;
		int RndGems = 50;
		if (IsMining == true) {
			MIPickAxe = 1; //If Mining -> Go trough loop, passby MICheckPickAxe to prevent originalweapon loss, 1 to tell you got pick axe, no function left for MIPickAxe if mining, so 1 is enough.
		} else {
			MIPickAxe = MICheckPickAxe();
		}
		if (MIPickAxe > 0) {
			if (playerLevel[playerMining] >= mining[1]) {
				if (freeSlots() > 0) {
					if (actionTimer == 0 && IsMining == false) {
						actionAmount++;
						sendMessage("You swing your pick axe at the rock...");
						actionTimer = (int)((mining[0] + 10) - MIPickAxe);
						if (actionTimer < 1) {
							actionTimer = 1;
						}
						setAnimation(0x554);
						IsMining = true;
					}
					if (actionTimer == 0 && IsMining == true) {
						if (IsItemInBag(1706) == true || IsItemInBag(1708) == true || IsItemInBag(1710) == true || IsItemInBag(1712) == true) {
							RndGems /= 2;
						}
						if (misc.random(RndGems) == (int)(RndGems / 4)) {
							addSkillXP((65 * mining[3]), playerMining);
							addItem(Item.randomNGems(), 1);
							sendMessage("You get a gem!");
						} else {
							addSkillXP((mining[2] * mining[3]), playerMining);
							addItem(mining[4], 1);
							sendMessage("You get some ores.");
						}
						playerEquipment[playerWeapon] = OriginalWeapon;
						OriginalWeapon = -1;
						playerEquipment[playerShield] = OriginalShield;
						OriginalShield = -1;
						resetAnimation();
						resetMI();
					}
				} else {
					sendMessage("Not enough space in your inventory.");
					resetMI();
					return false;
				}
			} else {
				sendMessage("You need "+mining[1]+" "+statName[playerMining]+" to mine those ores.");
				resetMI();
				return false;
			}
		} else {
			sendMessage("You need a pick axe to mine ores.");
			resetMI();
			return false;
		}
		return true;
	}
	public boolean resetMI() {
		mining[0] = 0;
		mining[1] = 0;
		mining[2] = 0;
		mining[4] = 0;
		skillX = -1;
		skillY = -1;
		IsMining = false;
		IsUsingSkill = false;
		return true;
	}
	public int MICheckPickAxe() {
		int Hand;
		int Shield;
		int Bag;
		int PickAxe;
		Hand = playerEquipment[playerWeapon];
		Shield = playerEquipment[playerShield];
		PickAxe = 0;
		switch (Hand) {
			case 1265: //Bronze Pick Axe
				PickAxe = 1; break;
			case 1267: //Iron Pick Axe
				PickAxe = 2; break;
			case 1269: //Steel Pick Axe
				PickAxe = 3; break;
			case 1273: //Mithril Pick Axe
				PickAxe = 4; break;
			case 1271: //Adamant Pick Axe
				PickAxe = 5; break;
			case 1275: //Rune Pick Axe
				PickAxe = 6; break;
		}
		if (PickAxe > 0) {
			OriginalWeapon = Hand;
			OriginalShield = Shield;
			playerEquipment[playerShield] = -1;
			return PickAxe;
		}
		Bag = -1;
		for (int i = 0; i < playerItems.length; i++) {
			Bag = playerItems[i];
			Bag -=1; //Only to fix the ID !
			if (Bag == 1265 || Bag == 1267 || Bag == 1269 || Bag == 1271 || Bag == 1273 || Bag == 1275) {
				break;
			}
		}
		switch (Bag) {
			case 1265: //Bronze Pick Axe
				PickAxe = 1; break;
			case 1267: //Iron Pick Axe
				PickAxe = 2; break;
			case 1269: //Steel Pick Axe
				PickAxe = 3; break;
			case 1273: //Mithril Pick Axe
				PickAxe = 4; break;
			case 1271: //Adamant Pick Axe
				PickAxe = 5; break;
			case 1275: //Rune Pick Axe
				PickAxe = 6; break;
		}
		if (PickAxe > 0) {
			OriginalWeapon = Hand;
			OriginalShield = Shield;
			playerEquipment[playerShield] = -1;
			playerEquipment[playerWeapon] = Bag;
		}
		return PickAxe;
	}
/*PRAYER*/
	public boolean prayer() {
		if (playerLevel[playerPrayer] >= prayer[1]) {
			if (actionTimer == 0 && prayer[0] == 1 && playerEquipment[playerWeapon] >= 1) {
				//actionAmount++;
				actionTimer = 2;
				OriginalShield = playerEquipment[playerShield];
				OriginalWeapon = playerEquipment[playerWeapon];
				playerEquipment[playerShield] = -1;
				playerEquipment[playerWeapon] = -1;
				setAnimation(0x33B);
				prayer[0] = 2;
			}
			if (actionTimer == 0 && prayer[0] == 2) {
				deleteItem(prayer[4], prayer[5], playerItemsN[prayer[5]]);
				addSkillXP((prayer[2] * prayer[3]), playerPrayer);
				sendMessage("You bury the bones.");
				playerEquipment[playerWeapon] = OriginalWeapon;
				playerEquipment[playerShield] = OriginalShield;
				OriginalWeapon = -1;
				OriginalShield = -1;
				resetAnimation();
				resetPR();
			}
		} else {
			sendMessage("You need "+prayer[1]+" "+statName[playerPrayer]+" to bury these bones.");
			resetPR();
			return false;
		}
		return true;
	}
	public boolean resetPR() {
		prayer[0] = 0;
		prayer[1] = 0;
		prayer[2] = 0;
		prayer[4] = -1;
		prayer[5] = -1;
		IsUsingSkill = false;
		return true;
	}
/*SMITHING*/
	public boolean smelting() {
		boolean DoSmelt = true;
		if (playerLevel[playerSmithing] >= smelting[1]) {
			if (actionTimer == 0 && smelting[0] == 1) {
				actionAmount++;
				sendMessage("You place the ores in the furnace...");
				actionTimer = 8;
				if (actionTimer < 1) {
					actionTimer = 1;
				}
				setAnimation(0x383);
				smelting[0] = 2;
			}
			if (actionTimer == 0 && smelting[0] == 2) {
				deleteItem(smelting[4], smelting[5], playerItemsN[smelting[5]]);
				if (smelting[6] > 0) {
					for (int i = 1; i <= smelting[6]; i++) {
						deleteItem(453, GetItemSlot(453), playerItemsN[GetItemSlot(453)]);
					}
				}
				if (smelting[4] == 436) {
					deleteItem(438, GetItemSlot(438), playerItemsN[GetItemSlot(438)]);
				} else if (smelting[4] == 438) {
					deleteItem(436, GetItemSlot(436), playerItemsN[GetItemSlot(436)]);
				}
				if (smelting[3] == 2351 && playerEquipment[playerRing] != 2568) {
					if (misc.random2(2) == 1) {
						DoSmelt = false;
					}
				}
				if (DoSmelt == true) {
					addSkillXP((smelting[2] * smithing[3]), playerSmithing);
					addItem(smelting[3], 1);
					sendMessage("You smelted a bar.");
					playerEquipment[playerWeapon] = OriginalWeapon;
					OriginalWeapon = -1;
					playerEquipment[playerShield] = OriginalShield;
					OriginalShield = -1;
					resetAnimation();
					resetSmelt();
				}
			}
		} else {
			sendMessage("You need "+smelting[1]+" "+statName[playerSmithing]+" to smelt those ores.");
			resetSmelt();
			return false;
		}
		return true;
	}
	public boolean resetSmelt() {
		smelting[0] = 0;
		smelting[1] = 0;
		smelting[2] = 0;
		smelting[3] = 0;
		smelting[4] = -1;
		smelting[5] = -1;
		smelting[6] = 0;
		skillX = -1;
		skillY = -1;
		IsUsingSkill = false;
		return true;
	}
	public boolean CheckSmelting(int ItemID, int ItemSlot) {
		boolean GoFalse = false;
		switch (ItemID) {
			case 436: //copper ore
				if (IsItemInBag(438) == true) {
					smelting[0] = 1;
					smelting[1] = 1;
					if (misc.random(2) == 1) {
						smelting[2] = 6;
					} else {
						smelting[2] = 7;
					}
					smelting[3] = 2349;
				} else {
					sendMessage("You need 1 copper ore and 1 tin ore to smelt 1 bronze bar.");
					return false;
				}
				break;
			case 438: //tin
				if (IsItemInBag(436) == true) {
					smelting[0] = 1;
					smelting[1] = 1;
					if (misc.random(2) == 1) {
						smelting[2] = 6;
					} else {
						smelting[2] = 7;
					}
					smelting[3] = 2349;
				} else {
					sendMessage("You need 1 copper ore and 1 tin ore to smelt 1 bronze bar.");
					return false;
				}
				break;
			case 440: //iron
				smelting[0] = 1;
				if (AreXItemsInBag(453, 2) == true) {
					smelting[1] = 30;
					if (misc.random(2) == 1) {
						smelting[2] = 17;
					} else {
						smelting[2] = 18;
					}
					smelting[3] = 2353;
					smelting[6] = 2;
				} else {
					smelting[1] = 15;
					if (misc.random(2) == 1) {
						smelting[2] = 12;
					} else {
						smelting[2] = 13;
					}
					smelting[3] = 2349;
				}
				break;
			case 2892: //elemental
				if (AreXItemsInBag(453, 4) == true) {
					smelting[0] = 1;
					smelting[1] = 20;
					smelting[2] = 18;
					smelting[3] = 2893;
					smelting[6] = 4;
				} else {
					sendMessage("You need 1 elemental ore and 4 coal to smelt 1 elemental bar.");
					return false;
				}
				break;
			case 442: //silver
				smelting[0] = 1;
				smelting[1] = 20;
				if (misc.random(2) == 1) {
					smelting[2] = 13;
				} else {
					smelting[2] = 14;
				}
				smelting[3] = 2355;
				break;
			case 444: //gold
				smelting[0] = 1;
				smelting[1] = 40;
				if (playerEquipment[playerHands] == 776) {
					if (misc.random(2) == 1) {
						smelting[2] = 56;
					} else {
						smelting[2] = 57;
					}
				} else {
					if (misc.random(2) == 1) {
						smelting[2] = 22;
					} else {
						smelting[2] = 23;
					}
				}
				smelting[3] = 2357;
				break;
			case 447: //mithril
				if (AreXItemsInBag(453, 4) == true) {
					smelting[0] = 1;
					smelting[1] = 50;
					smelting[2] = 30;
					smelting[3] = 2359;
					smelting[6] = 4;
				} else {
					sendMessage("You need 1 mithril ore and 4 coal to smelt 1 mithril bar.");
					return false;
				}
				break;
			case 449: //adamantite
				if (AreXItemsInBag(453, 6) == true) {
					smelting[0] = 1;
					smelting[1] = 70;
					if (misc.random(2) == 1) {
						smelting[2] = 37;
					} else {
						smelting[2] = 38;
					}
					smelting[3] = 2361;
					smelting[6] = 6;
				} else {
					sendMessage("You need 1 adamant ore and 4 coal to smelt 1 adamant bar.");
					return false;
				}
				break;
			case 451: //runite
				if (AreXItemsInBag(453, 8) == true) {
					smelting[0] = 1;
					smelting[1] = 85;
					smelting[2] = 50;
					smelting[3] = 2363;
					smelting[6] = 8;
				} else {
					sendMessage("You need 1 runite ore and 4 coal to smelt 1 runite bar.");
					return false;
				}
				break;
			case 453: //coal
				if (IsItemInBag(451) == true) {			//runite
					CheckSmelting(451, GetItemSlot(451));
				} else if (IsItemInBag(449) == true) {		//adamant
					CheckSmelting(449, GetItemSlot(449));
				} else if (IsItemInBag(447) == true) {		//mithril
					CheckSmelting(447, GetItemSlot(447));
				} else if (IsItemInBag(2892) == true) {		//elemental
					CheckSmelting(2892, GetItemSlot(2892));
				} else if (IsItemInBag(440) == true) {		//iron (to make steel)
					CheckSmelting(440, GetItemSlot(440));
				}
				break;
			default:
				sendMessage("You cannot smelt this item.");
				GoFalse = true;
				break;
		}
		if (GoFalse == true) {
			return false;
		}
		if (ItemID != 453) {
			if (smelting[0] >= 1) {
				OriginalWeapon = playerEquipment[playerWeapon];
				playerEquipment[playerWeapon] = -1;
				OriginalShield = playerEquipment[playerShield];
				playerEquipment[playerShield] = -1;
				smelting[4] = ItemID;
				smelting[5] = ItemSlot;
			}
		}
		return true;
	}
	public int CheckSmithing(int ItemID, int ItemSlot) {
		boolean GoFalse = false;
		int Type = -1;
		if (IsItemInBag(2347) == false) {
			sendMessage("You need a "+GetItemName(2347)+" to hammer bars.");
			return -1;
		}
		switch (ItemID) {
			case 2349: //Bronze Bar
				Type = 1;
				break;
			case 2351: //Iron Bar
				Type = 2;
				break;
			case 2353: //Steel Bar
				Type = 3;
				break;
			case 2359: //Mithril Bar
				Type = 4;
				break;
			case 2361: //Adamantite Bar
				Type = 5;
				break;
			case 2363: //Runite Bar
				Type = 6;
				break;
			default:
				sendMessage("You cannot smith this item.");
				GoFalse = true;
				break;
		}
		if (GoFalse == true) {
			return -1;
		}
		return Type;
	}
	public void OpenSmithingFrame(int Type) {
		int Type2 = Type - 1;
		int Length = 22;
		if (Type == 1 || Type == 2) {
			Length += 1;
		} else if (Type == 3) {
			Length += 2;
		}
		//Sending amount of bars + make text green if lvl is highenough
		sendFrame126("", 1132); //Wire
		sendFrame126("", 1096);
		sendFrame126("", 11459); //Lantern
		sendFrame126("", 11461);
		sendFrame126("", 1135); //Studs
		sendFrame126("", 1134);
		String bar, color, color2, name = "";
		if (Type == 1) {
			name = "Bronze ";
		} else if (Type == 2) {
			name = "Iron ";
		} else if (Type == 3) {
			name = "Steel ";
		} else if (Type == 4) {
			name = "Mithril ";
		} else if (Type == 5) {
			name = "Adamant ";
		} else if (Type == 6) {
			name = "Rune ";
		}
		for (int i = 0; i < Length; i++) {
			bar = "bar";
			color = "@red@";
			color2 = "@bla@";
			if (Item.smithing_frame[Type2][i][3] > 1) {
				bar = bar + "s";
			}
			if (playerLevel[playerSmithing] >= Item.smithing_frame[Type2][i][2]) {
				color2 = "@whi@";
			}
			int Type3 = Type2;
			if (Type2 >= 3) {
				Type3 = (Type2 + 2);
			}
			if (AreXItemsInBag((2349 + (Type3 * 2)), Item.smithing_frame[Type2][i][3]) == true) {
				color = "@gre@";
			}
			sendFrame126(color + "" + Item.smithing_frame[Type2][i][3] + "" + bar, Item.smithing_frame[Type2][i][4]);
			sendFrame126(color2 + "" + GetItemName(Item.smithing_frame[Type2][i][0]).replace(name, ""), Item.smithing_frame[Type2][i][5]);
		}
		Item.SmithingItems[0][0] = Item.smithing_frame[Type2][0][0]; //Dagger
		Item.SmithingItems[0][1] = Item.smithing_frame[Type2][0][1];
		Item.SmithingItems[1][0] = Item.smithing_frame[Type2][4][0]; //Sword
		Item.SmithingItems[1][1] = Item.smithing_frame[Type2][4][1];
		Item.SmithingItems[2][0] = Item.smithing_frame[Type2][8][0]; //Scimitar
		Item.SmithingItems[2][1] = Item.smithing_frame[Type2][8][1];
		Item.SmithingItems[3][0] = Item.smithing_frame[Type2][9][0]; //Long Sword
		Item.SmithingItems[3][1] = Item.smithing_frame[Type2][9][1];
		Item.SmithingItems[4][0] = Item.smithing_frame[Type2][18][0]; //2 hand sword
		Item.SmithingItems[4][1] = Item.smithing_frame[Type2][18][1];
		SetSmithing(1119);
		Item.SmithingItems[0][0] = Item.smithing_frame[Type2][1][0]; //Axe
		Item.SmithingItems[0][1] = Item.smithing_frame[Type2][1][1];
		Item.SmithingItems[1][0] = Item.smithing_frame[Type2][2][0]; //Mace
		Item.SmithingItems[1][1] = Item.smithing_frame[Type2][2][1];
		Item.SmithingItems[2][0] = Item.smithing_frame[Type2][13][0]; //Warhammer
		Item.SmithingItems[2][1] = Item.smithing_frame[Type2][13][1];
		Item.SmithingItems[3][0] = Item.smithing_frame[Type2][14][0]; //Battle axe
		Item.SmithingItems[3][1] = Item.smithing_frame[Type2][14][1];
		Item.SmithingItems[4][0] = Item.smithing_frame[Type2][17][0]; //Claws
		Item.SmithingItems[4][1] = Item.smithing_frame[Type2][17][1];
		SetSmithing(1120);
		Item.SmithingItems[0][0] = Item.smithing_frame[Type2][15][0]; //Chain body
		Item.SmithingItems[0][1] = Item.smithing_frame[Type2][15][1];
		Item.SmithingItems[1][0] = Item.smithing_frame[Type2][20][0]; //Plate legs
		Item.SmithingItems[1][1] = Item.smithing_frame[Type2][20][1];
		Item.SmithingItems[2][0] = Item.smithing_frame[Type2][19][0]; //Plate skirt
		Item.SmithingItems[2][1] = Item.smithing_frame[Type2][19][1];
		Item.SmithingItems[3][0] = Item.smithing_frame[Type2][21][0]; //Plate body
		Item.SmithingItems[3][1] = Item.smithing_frame[Type2][21][1];
		Item.SmithingItems[4][0] = -1; //Lantern
		Item.SmithingItems[4][1] = 0;
		if (Type == 2 || Type == 3) {
			color2 = "@bla@";
			if (playerLevel[playerSmithing] >= Item.smithing_frame[Type2][22][2]) {
				color2 = "@whi@";
			}
			Item.SmithingItems[4][0] = Item.smithing_frame[Type2][22][0]; //Lantern
			Item.SmithingItems[4][1] = Item.smithing_frame[Type2][22][1];
			sendFrame126(color2 + "" + GetItemName(Item.smithing_frame[Type2][22][0]), 11461);
		}
		SetSmithing(1121);
		Item.SmithingItems[0][0] = Item.smithing_frame[Type2][3][0]; //Medium
		Item.SmithingItems[0][1] = Item.smithing_frame[Type2][3][1];
		Item.SmithingItems[1][0] = Item.smithing_frame[Type2][10][0]; //Full Helm
		Item.SmithingItems[1][1] = Item.smithing_frame[Type2][10][1];
		Item.SmithingItems[2][0] = Item.smithing_frame[Type2][12][0]; //Square
		Item.SmithingItems[2][1] = Item.smithing_frame[Type2][12][1];
		Item.SmithingItems[3][0] = Item.smithing_frame[Type2][16][0]; //Kite
		Item.SmithingItems[3][1] = Item.smithing_frame[Type2][16][1];
		Item.SmithingItems[4][0] = Item.smithing_frame[Type2][6][0]; //Nails
		Item.SmithingItems[4][1] = Item.smithing_frame[Type2][6][1];
		SetSmithing(1122);
		Item.SmithingItems[0][0] = Item.smithing_frame[Type2][5][0]; //Dart Tips
		Item.SmithingItems[0][1] = Item.smithing_frame[Type2][5][1];
		Item.SmithingItems[1][0] = Item.smithing_frame[Type2][7][0]; //Arrow Heads
		Item.SmithingItems[1][1] = Item.smithing_frame[Type2][7][1];
		Item.SmithingItems[2][0] = Item.smithing_frame[Type2][11][0]; //Knives
		Item.SmithingItems[2][1] = Item.smithing_frame[Type2][11][1];
		Item.SmithingItems[3][0] = -1; //Wire
		Item.SmithingItems[3][1] = 0;
		if (Type == 1) {
			color2 = "@bla@";
			if (playerLevel[playerSmithing] >= Item.smithing_frame[Type2][22][2]) {
				color2 = "@whi@";
			}
			Item.SmithingItems[3][0] = Item.smithing_frame[Type2][22][0]; //Wire
			Item.SmithingItems[3][1] = Item.smithing_frame[Type2][22][1];
			sendFrame126(color2 + "" + GetItemName(Item.smithing_frame[Type2][22][0]), 1096);
		}
		Item.SmithingItems[4][0] = -1; //Studs
		Item.SmithingItems[4][1] = 0;
		if (Type == 3) {
			color2 = "@bla@";
			if (playerLevel[playerSmithing] >= Item.smithing_frame[Type2][23][2]) {
				color2 = "@whi@";
			}
			Item.SmithingItems[4][0] = Item.smithing_frame[Type2][23][0]; //Studs
			Item.SmithingItems[4][1] = Item.smithing_frame[Type2][23][1];
			sendFrame126(color2 + "" + GetItemName(Item.smithing_frame[Type2][23][0]), 1134);
		}
		SetSmithing(1123);
		showInterface(994);
		smithing[2] = Type;
	}
	public boolean smithing() {
		if (IsItemInBag(2347) == true) {
			int bars = 0;
			int Length = 22;
			int barid = 0;
			int Level = 0;
			int ItemN = 1;
			if (smithing[2] >= 4) {
				barid = (2349 + ((smithing[2] + 1) * 2));
			} else {
				barid = (2349 + ((smithing[2] - 1) * 2));
			}
			if (smithing[2] == 1 || smithing[2] == 2) {
				Length += 1;
			} else if (smithing[2] == 3) {
				Length += 2;
			}
			for (int i = 0; i < Length; i++) {
				if (Item.smithing_frame[(smithing[2] - 1)][i][0] == smithing[4]) {
					bars = Item.smithing_frame[(smithing[2] - 1)][i][3];
					if (smithing[1] == 0) {
						smithing[1] = Item.smithing_frame[(smithing[2] - 1)][i][2];
					}
					ItemN = Item.smithing_frame[(smithing[2] - 1)][i][1];
				}
			}
			if (playerLevel[playerSmithing] >= smithing[1]) {
				if (AreXItemsInBag(barid, bars) == true) {
					if (freeSlots() > 0) {
						if (actionTimer == 0 && smithing[0] == 1) {
							actionAmount++;
							OriginalWeapon = playerEquipment[playerWeapon];
							playerEquipment[playerWeapon] = 2347; //Hammer
							OriginalShield = playerEquipment[playerShield];
							playerEquipment[playerShield] = -1;
							sendMessage("You start hammering the bar...");
							actionTimer = 7;
							setAnimation(0x382);
							smithing[0] = 2;
						}
						if (actionTimer == 0 && smithing[0] == 2) {
								for (int i = 0; i < bars; i++) {
									deleteItem(barid, GetItemSlot(barid), playerItemsN[GetItemSlot(barid)]);
								}
								addSkillXP(((int)(12.5 * bars * smithing[2] * smithing[3])), playerSmithing);
								addItem(smithing[4], ItemN);
								sendMessage("You smith a " + GetItemName(smithing[4]) + ".");
								resetAnimation();
								if (smithing[5] <= 1) {
									resetSM();
								} else {
									actionTimer = 5;
									smithing[5] -= 1;
									smithing[0] = 1;
								}
						}
					} else {
						sendMessage("Not enough space in your inventory.");
						resetSM();
						return false;
					}
				} else {
					sendMessage("You need " + bars + " " + GetItemName(barid) + " to smith a " + GetItemName(smithing[4]));
					resetAnimation();
					resetSM();
				}
			} else {
				sendMessage("You need "+smithing[1]+" "+statName[playerSmithing]+" to smith a "+GetItemName(smithing[4]));
				resetSM();
				return false;
			}
		} else {
			sendMessage("You need a "+GetItemName(2347)+" to hammer bars.");
			resetSM();
			return false;
		}
		return true;
	}
	public boolean resetSM() {
		if (OriginalWeapon > -1) {
			playerEquipment[playerWeapon] = OriginalWeapon;
			OriginalWeapon = -1;
			playerEquipment[playerShield] = OriginalShield;
			OriginalShield = -1;
		}
		smithing[0] = 0;
		smithing[1] = 0;
		smithing[2] = 0;
		smithing[4] = -1;
		smithing[5] = 0;
		skillX = -1;
		skillY = -1;
		IsUsingSkill = false;
		return true;
	}
/*WOODCUTTING*/
	public boolean woodcutting() {
		int WCAxe = 0;
		if (IsCutting == true) {
			WCAxe = 1; //If Cutting -> Go trough loop, passby WCCheckAxe to prevent originalweapon loss, 1 to tell you got axe, no function left for WCAxe if cutting, so 1 is enough.
		} else {
			WCAxe = WCCheckAxe();
		}
		if (WCAxe > 0) {
			if (playerLevel[playerWoodcutting] >= woodcutting[1]) {
				if (freeSlots() > 0) {
					if (actionTimer == 0 && IsCutting == false) {
						actionAmount++;
						sendMessage("You swing your axe at the tree...");
						actionTimer = (int)((woodcutting[0] + 10) - WCAxe);
						if (actionTimer < 1) {
							actionTimer = 1;
						}
						setAnimation(0x284);
						IsCutting = true;
					}
					if (actionTimer == 0 && IsCutting == true) {
						addSkillXP((woodcutting[2] * woodcutting[3]), playerWoodcutting);
						addItem(woodcutting[4], 1);
						sendMessage("You get some logs.");
						playerEquipment[playerWeapon] = OriginalWeapon;
						OriginalWeapon = -1;
						resetAnimation();
						IsCutting = false;
						resetWC();
					}
				} else {
					sendMessage("Not enough space in your inventory.");
					resetWC();
					return false;
				}
			} else {
				sendMessage("You need "+woodcutting[1]+" "+statName[playerWoodcutting]+" to cut those logs.");
				resetWC();
				return false;
			}
		} else {
			sendMessage("You need an Axe to cut logs.");
			resetWC();
			return false;
		}
		return true;
	}
	public boolean resetWC() {
		woodcutting[0] = 0;
		woodcutting[1] = 0;
		woodcutting[2] = 0;
		woodcutting[4] = 0;
		woodcutting[5] = 2;
		skillX = -1;
		skillY = -1;
		IsCutting = false;
		IsUsingSkill = false;
		return true;
	}
	public int WCCheckAxe() {
		int Hand;
		int Shield;
		int Bag;
		int Axe;
		Hand = playerEquipment[playerWeapon];
		Shield = playerEquipment[playerShield];
		Axe = 0;
		switch (Hand) {
			case 1351: //Bronze Axe
				Axe = 1; break;
			case 1349: //Iron Axe
				Axe = 2; break;
			case 1353: //Steel Axe
				Axe = 3; break;
			case 1361: //Black Axe
				Axe = 4; break;
			case 1355: //Mithril Axe
				Axe = 5; break;
			case 1357: //Adamant Axe
				Axe = 6; break;
			case 1359: //Rune Axe
				Axe = 7; break;
			case 6739: //Dragon Axe
				Axe = 8; break;
		}
		if (Axe > 0) {
			OriginalWeapon = Hand;
			OriginalShield = Shield;
			playerEquipment[playerShield] = -1;
			return Axe;
		}
		Bag = -1;
		for (int i = 0; i < playerItems.length; i++) {
			Bag = playerItems[i];
			Bag -=1; //Only to fix the ID !
			if (Bag == 1351 || Bag == 1349 || Bag == 1353 || Bag == 1361 || Bag == 1355 || Bag == 1357 || Bag == 1359/* || Bag == X*/) {
				break;
			}
		}
		switch (Bag) {
			case 1351: //Bronze Axe
				Axe = 1; break;
			case 1349: //Iron Axe
				Axe = 2; break;
			case 1353: //Steel Axe
				Axe = 3; break;
			case 1361: //Black Axe
				Axe = 4; break;
			case 1355: //Mithril Axe
				Axe = 5; break;
			case 1357: //Adamant Axe
				Axe = 6; break;
			case 1359: //Rune Axe
				Axe = 7; break;
			case 6739: //Dragon Axe
				Axe = 8; break;
		}
		if (Axe > 0) {
			OriginalWeapon = Hand;
			OriginalShield = Shield;
			playerEquipment[playerShield] = -1;
			playerEquipment[playerWeapon] = Bag;
		}
		return Axe;
	}
/*TRADING*/
	public void AcceptTrade() {
		sendFrame248(3323, 3321); //trading window + bag
		resetItems(3322);
		resetTItems(3415);
		resetOTItems(3416);
		sendFrame126("Trading With: "+PlayerHandler.players[tradeWith].playerName, 3417);
		sendFrame126("", 3431);
	}
	public void DeclineTrade() {
		for (int i = 0; i < playerTItems.length; i++) {
			if (playerTItems[i] > 0) {
				fromTrade((playerTItems[i] - 1), i, playerTItemsN[i]);
			}
		}
		resetItems(3214);
		resetTrade();
	}
	public void resetTrade() {
		tradeWith = 0;
		tradeWaitingTime = 0;
		tradeStatus = 0;
		tradeUpdateOther = false;
		tradeOtherDeclined = false;
		WanneTrade = 0;
		WanneTradeWith = 0;
		TradeConfirmed = false;
		for (int i = 0; i < playerTItems.length; i++) {
			playerTItems[i] = 0;
			playerTItemsN[i] = 0;
			playerOTItems[i] = 0;
			playerOTItemsN[i] = 0;
		}
	}
	public void ConfirmTrade() {
		if (TradeConfirmed == false) {
			RemoveAllWindows();
			for (int i = 0; i < playerOTItems.length; i++) {
				if (playerOTItems[i] > 0) {
					addItem((playerOTItems[i] - 1), playerOTItemsN[i]);
				}
			}
			resetItems(3214);
			TradeConfirmed = true;
		}
	}
	public void TradeGoConfirm() {
		sendFrame248(3443, 3213); //trade confirm + normal bag
		resetItems(3214);
		String SendTrade = "Absolutely nothing!";
		String SendAmount = "";
		int Count = 0;
		for (int i = 0; i < playerTItems.length; i++) {
			if (playerTItems[i] > 0) {
				if (playerTItemsN[i] >= 1000 && playerTItemsN[i] < 1000000) {
					SendAmount = "@cya@" + (playerTItemsN[i] / 1000) + "K @whi@(" + playerTItemsN[i] + ")";
				} else if (playerTItemsN[i] >= 1000000) {
					SendAmount = "@gre@" + (playerTItemsN[i] / 1000000) + " million @whi@(" + playerTItemsN[i] + ")";
				} else {
					SendAmount = "" + playerTItemsN[i];
				}
				if (Count == 0) {
					SendTrade = GetItemName((playerTItems[i] - 1));
				} else {
					SendTrade = SendTrade + "\\n" + GetItemName((playerTItems[i] - 1));
				}
				if (Item.itemIsNote[(playerTItems[i] - 1)] == true || Item.itemStackable[(playerTItems[i] - 1)] == true) {
					SendTrade = SendTrade + " x " + SendAmount;
				}
				Count++;
			}
		}
		sendFrame126(SendTrade, 3557);
		SendTrade = "Absolutely nothing!";
		SendAmount = "";
		Count = 0;
		for (int i = 0; i < playerOTItems.length; i++) {
			if (playerOTItems[i] > 0) {
				if (playerOTItemsN[i] >= 1000 && playerOTItemsN[i] < 1000000) {
					SendAmount = "@cya@" + (playerOTItemsN[i] / 1000) + "K @whi@(" + playerOTItemsN[i] + ")";
				} else if (playerOTItemsN[i] >= 1000000) {
					SendAmount = "@gre@" + (playerOTItemsN[i] / 1000000) + " million @whi@(" + playerOTItemsN[i] + ")";
				} else {
					SendAmount = "" + playerOTItemsN[i];
				}
				if (Count == 0) {
					SendTrade = GetItemName((playerOTItems[i] - 1));
				} else {
					SendTrade = SendTrade + "\\n" + GetItemName((playerOTItems[i] - 1));
				}
				if (Item.itemIsNote[(playerOTItems[i] - 1)] == true || Item.itemStackable[(playerOTItems[i] - 1)] == true) {
					SendTrade = SendTrade + " x " + SendAmount;
				}
				Count++;
			}
		}
		sendFrame126(SendTrade, 3558);
	}
	public boolean fromTrade(int itemID, int fromSlot, int amount) {
		if (amount > 0 && (itemID + 1) == playerTItems[fromSlot]) {
			if (amount > playerTItemsN[fromSlot]) {
				amount = playerTItemsN[fromSlot];
			}
			addItem((playerTItems[fromSlot] - 1), amount);
			if (amount == playerTItemsN[fromSlot]) {
				playerTItems[fromSlot] = 0;
				PlayerHandler.players[tradeWith].playerOTItems[fromSlot] = 0;
			}
			playerTItemsN[fromSlot] -= amount;
			PlayerHandler.players[tradeWith].playerOTItemsN[fromSlot] -= amount;
			resetItems(3322);
			resetTItems(3415);
			PlayerHandler.players[tradeWith].tradeUpdateOther = true;
			if (PlayerHandler.players[tradeWith].tradeStatus == 3) {
				PlayerHandler.players[tradeWith].tradeStatus = 2;
				PlayerHandler.players[tradeWith].AntiTradeScam = true;
				sendFrame126("", 3431);
			}
			return true;
		}
		return false;
	}
	public boolean tradeItem(int itemID, int fromSlot, int amount) {
		if (tradeWith > 0) {
			if (PlayerHandler.players[tradeWith] == null) {
				DeclineTrade();
				sendMessage("FORCED DECLINE BY SERVER !");
				return false;
			}
		} else {
			DeclineTrade();
			sendMessage("FORCED DECLINE BY SERVER !");
			return false;
		}
		if (amount > 0 && itemID == (playerItems[fromSlot] - 1)) {
			if (amount > playerItemsN[fromSlot]) {
				amount = playerItemsN[fromSlot];
			}
			boolean IsInTrade = false;
			for (int i = 0; i < playerTItems.length; i++) {
				if (playerTItems[i] == playerItems[fromSlot]) {
					if (Item.itemStackable[(playerItems[fromSlot] - 1)] == true || Item.itemIsNote[(playerItems[fromSlot] - 1)] == true) {
						playerTItemsN[i] += amount;
						PlayerHandler.players[tradeWith].playerOTItemsN[i] += amount;
						IsInTrade = true;
						break;
					}
				}
			}
			if (IsInTrade == false) {
				for (int i = 0; i < playerTItems.length; i++) {
					if (playerTItems[i] <= 0) {
						playerTItems[i] = playerItems[fromSlot];
						playerTItemsN[i] = amount;
						PlayerHandler.players[tradeWith].playerOTItems[i] = playerItems[fromSlot];
						PlayerHandler.players[tradeWith].playerOTItemsN[i] = amount;
						break;
					}
				}
			}
			if (amount == playerItemsN[fromSlot]) {
				playerItems[fromSlot] = 0;
			}
			playerItemsN[fromSlot] -= amount;
			resetItems(3322);
			resetTItems(3415);
			PlayerHandler.players[tradeWith].tradeUpdateOther = true;
			if (PlayerHandler.players[tradeWith].tradeStatus == 3) {
				PlayerHandler.players[tradeWith].tradeStatus = 2;
				PlayerHandler.players[tradeWith].AntiTradeScam = true;
				sendFrame126("", 3431);
			}
			return true;
		}
		return false;
	}
/*Shops*/
	public boolean sellItem(int itemID, int fromSlot, int amount) {
		if (amount > 0 && itemID == (playerItems[fromSlot] - 1)) {
			if (server.shopHandler.ShopSModifier[MyShopID] > 1) {
				boolean IsIn = false;
				for (int i = 0; i <= server.shopHandler.ShopItemsStandard[MyShopID]; i++) {
					if (itemID == (server.shopHandler.ShopItems[MyShopID][i] - 1)) {
						IsIn = true;
						break;
					}
				}
				if (IsIn == false) {
					sendMessage("You cannot sell "+GetItemName(itemID)+" in this store.");
					return false;
				}
			}
			if (Item.itemSellable[(playerItems[fromSlot] - 1)] == false) {
				sendMessage("I cannot sell "+GetItemName(itemID)+".");
				return false;
			}
			if (amount > playerItemsN[fromSlot] && (Item.itemIsNote[(playerItems[fromSlot] - 1)] == true || Item.itemStackable[(playerItems[fromSlot] - 1)] == true)) {
				amount = playerItemsN[fromSlot];
			} else if (amount > GetXItemsInBag(itemID) && Item.itemIsNote[(playerItems[fromSlot] - 1)] == false && Item.itemStackable[(playerItems[fromSlot] - 1)] == false) {
				amount = GetXItemsInBag(itemID);
			}
			double ShopValue;
			double TotPrice;
			int TotPrice2;
			int Overstock;
			for (int i = amount; i > 0; i--) {
				TotPrice2 = (int)Math.floor(GetItemShopValue(itemID, 1, fromSlot));
				if (freeSlots() > 0) {
					if (Item.itemIsNote[itemID] == false) {
						deleteItem(itemID, GetItemSlot(itemID), 1);
					} else {
						deleteItem(itemID, fromSlot, 1);
					}
					addItem(995, TotPrice2);
					addShopItem(itemID, 1);
				} else {
					sendMessage("Not enough space in your inventory.");
					break;
				}
			}
			resetItems(3823);
			resetShop(MyShopID);
			UpdatePlayerShop();
			return true;
		}
		return true;
	}
	public boolean buyItem(int itemID, int fromSlot, int amount) {
		if (amount > 0 && itemID == (server.shopHandler.ShopItems[MyShopID][fromSlot] - 1)) {
			if (amount > server.shopHandler.ShopItemsN[MyShopID][fromSlot]) {
				amount = server.shopHandler.ShopItemsN[MyShopID][fromSlot];
			}
			double ShopValue;
			double TotPrice;
			int TotPrice2;
			int Overstock;
			int Slot = 0;
			for (int i = amount; i > 0; i--) {
				TotPrice2 = (int)Math.floor(GetItemShopValue(itemID, 0, fromSlot));
				Slot = GetItemSlot(995);
				if (Slot == -1) {
					sendMessage("You don't have enough coins.");
					break;
				}
                        if(TotPrice2 <= 1)
                        {
                        TotPrice2 = (int)Math.floor(GetItemShopValue(itemID, 0, fromSlot));
                        }
				if (playerItemsN[Slot] >= TotPrice2) {
					if (freeSlots() > 0) {
						deleteItem(995, GetItemSlot(995), TotPrice2);
						addItem(itemID, 1);
						server.shopHandler.ShopItemsN[MyShopID][fromSlot] -= 1;
						server.shopHandler.ShopItemsDelay[MyShopID][fromSlot] = 0;
						if ((fromSlot + 1) > server.shopHandler.ShopItemsStandard[MyShopID]) {
							server.shopHandler.ShopItems[MyShopID][fromSlot] = 0;
						}
					} else {
						sendMessage("Not enough space in your inventory.");
						break;
					}
				} else {
					sendMessage("You don't have enough coins.");
					break;
				}
			}
			resetItems(3823);
			resetShop(MyShopID);
			UpdatePlayerShop();
			return true;
		}
		return false;
	}
	public void UpdatePlayerShop() {
		for (int i = 1; i < PlayerHandler.maxPlayers; i++) {
			if (PlayerHandler.players[i] != null) {
				if (PlayerHandler.players[i].IsShopping == true && PlayerHandler.players[i].MyShopID == MyShopID && i != playerId) {
					PlayerHandler.players[i].UpdateShop = true;
				}
			}
		}
	}
	public boolean addShopItem(int itemID, int amount) {
		boolean Added = false;
		if (amount <= 0) {
			return false;
		}
		if (Item.itemIsNote[itemID] == true) {
			itemID = GetUnnotedItem(itemID);
		}
		for (int i = 0; i < server.shopHandler.ShopItems.length; i++) {
			if ((server.shopHandler.ShopItems[MyShopID][i] - 1) == itemID) {
				server.shopHandler.ShopItemsN[MyShopID][i] += amount;
				Added = true;
			}
		}
		if (Added == false) {
			for (int i = 0; i < server.shopHandler.ShopItems.length; i++) {
				if (server.shopHandler.ShopItems[MyShopID][i] == 0) {
					server.shopHandler.ShopItems[MyShopID][i] = (itemID + 1);
					server.shopHandler.ShopItemsN[MyShopID][i] = amount;
					server.shopHandler.ShopItemsDelay[MyShopID][i] = 0;
					break;
				}
			}
		}
		return true;
	}
/*NPC Talking*/
	public void UpdateNPCChat() {
		/*sendFrame126("", 4902);
		sendFrame126("", 4903);
		sendFrame126("", 4904);
		sendFrame126("", 4905);
		sendFrame126("", 4906);*/
		sendFrame126("", 976);
		switch (NpcDialogue) {
			case 1:
				/*sendFrame200(4901, 554);
				sendFrame126(GetNpcName(NpcTalkTo), 4902);
				sendFrame126("Good day, how can I help you?", 4904);
				sendFrame75(NpcTalkTo, 4901);
				sendFrame164(4900);
				*/
				sendFrame200(4883, 591);
				sendFrame126(GetNpcName(NpcTalkTo), 4884);
				sendFrame126("Good day, how can I help you?", 4885);
				sendFrame75(NpcTalkTo, 4883);
				sendFrame164(4882);
				NpcDialogueSend = true;
				break;
			case 2:
				sendFrame171(1, 2465);
				sendFrame171(0, 2468);
				sendFrame126("What would you like to say?", 2460);
				sendFrame126("I'd like to access my bank account, please.", 2461);
				sendFrame126("I'd like to check my PIN settings.", 2462);
				sendFrame164(2459);
				NpcDialogueSend = true;
				break;
			case 3:
				sendFrame200(4883, 591);
				sendFrame126(GetNpcName(NpcTalkTo), 4884);
				sendFrame126("Do you want to buy some runes?", 4885);
				sendFrame75(NpcTalkTo, 4883);
				sendFrame164(4882);
				NpcDialogueSend = true;
				break;
			case 4:
				sendFrame171(1, 2465);
				sendFrame171(0, 2468);
				sendFrame126("Select an Option", 2460);
				sendFrame126("Yes please!", 2461);
				sendFrame126("Oh it's a rune shop. No thank you, then.", 2462);
				sendFrame164(2459);
				NpcDialogueSend = true;
				break;
			case 5:
				sendFrame200(615, 974);
				sendFrame126(playerName, 975);
				sendFrame126("Oh it's a rune shop. No thank you, then.", 977);
				sendFrame185(974);
				sendFrame164(973);
				NpcDialogueSend = true;
				break;
			case 6:
				sendFrame200(4883, 591);
				sendFrame126(GetNpcName(NpcTalkTo), 4884);
				sendFrame126("Well, if you find somone who does want runes, please", 4885);
				sendFrame126("send them my way.", 4886);
				sendFrame75(NpcTalkTo, 4883);
				sendFrame164(4882);
				NpcDialogueSend = true;
				break;
			case 7: /*NEED TO CHANGE FOR GUARD*/
				sendFrame200(4883, 591);
				sendFrame126(GetNpcName(NpcTalkTo), 4884);
				sendFrame126("Well, if you find somone who does want runes, please", 4885);
				sendFrame126("send them my way.", 4886);
				sendFrame75(NpcTalkTo, 4883);
				sendFrame164(4882);
				NpcDialogueSend = true;
				break;
			case 31: // NEW CLUE
				sendFrame200(4883, 591);
				sendFrame126(GetNpcName(NpcTalkTo), 4884);
				sendFrame126("Heres your next clue, goodluck", 4885);
				sendFrame75(NpcTalkTo, 4883);
				sendFrame164(4882);
				NpcDialogueSend = true;
				break;
			case 32: // COMPLETED CLUE
				sendFrame200(4883, 591);
				sendFrame126(GetNpcName(NpcTalkTo), 4884);
				sendFrame126("Congratulations! Heres your last reward!", 4885);
				sendFrame75(NpcTalkTo, 4883);
				sendFrame164(4882);
				NpcDialogueSend = true;
                                givereward(cluelevel);
				break;
case 40:
            sendFrame126(GetNpcName(NpcTalkTo), 4902);
            sendFrame126("", 4903);
            sendFrame126("Do you want to go on a trip to Karjama?", 4904);
            sendFrame126("It's free.", 4905);
            sendFrame126("", 4906);
            sendFrame75(NpcTalkTo, 4901);
            sendFrame164(4900);
            NpcDialogueSend = true;
            break;
         case 41:
            sendFrame171(1, 2465);
            sendFrame171(0, 2468);
            sendFrame126("Select an Option", 2460);
            sendFrame126("Yes, please", 2461);
            sendFrame126("No, Thank you.", 2462);
            sendFrame164(2459);
            NpcDialogueSend = true;
            break;
         case 42:
            sendFrame126(GetNpcName(NpcTalkTo), 4902);
            sendFrame126("", 4903);
            sendFrame126("Do you want to go on a trip to Port Sarim?", 4904);
            sendFrame126("It's free.", 4905);
            sendFrame126("", 4906);
            sendFrame75(NpcTalkTo, 4901);
            sendFrame164(4900);
            NpcDialogueSend = true;
            break;
         case 43:
            sendFrame171(1, 2465);
            sendFrame171(0, 2468);
            sendFrame126("Select an Option", 2460);
            sendFrame126("Yes, please", 2461);
            sendFrame126("No, Thank you.", 2462);
            sendFrame164(2459);
            NpcDialogueSend = true;
            break;
			case 11021: // COMPLETED QUEST STAGE 2
				sendFrame200(615, 974);
				sendFrame126(playerName, 975);
				sendFrame126("Ok, where can I find the "+GetItemName(4206)+"?", 977);
				sendFrame185(974);
				sendFrame164(973);
				NpcDialogueSend = true;
				break;	
			case 11022: // COMPLETED QUEST STAGE 2
				sendFrame200(4883, 591);
				sendFrame126(GetNpcName(NpcTalkTo), 4884);
				sendFrame126("It can be found in the caves of Relleka, north ", 4885);
				sendFrame126("of Camelot, but be warned, evil monsters lie there...", 4886);
				sendFrame75(NpcTalkTo, 4883);
				sendFrame164(4882);
				NpcDialogueSend = true;
				break;
                        case 100: // QUEST STAGE 0
				{sendFrame200(4883, 591);
				sendFrame126(GetNpcName(NpcTalkTo), 4884);
				sendFrame126("Hey I need help with making some invisible armour...", 4885);
				sendFrame126("and you're gonna help me.", 4886);
				sendFrame75(NpcTalkTo, 4883);
				sendFrame164(4882);
				NpcDialogueSend = true;
				NpcDialogueSend = true;
                                q1stage = 1;
                                //RemoveAllWindows();
				break;
				}
			case 101: // QUEST STAGE 1
				{sendFrame200(4883, 591);
				sendFrame126(GetNpcName(NpcTalkTo), 4884);
				sendFrame126("I'll add the list of materials I need to your", 4885);
				sendFrame126("quest log, as I'm too busy to talk.", 4886);
				sendFrame75(NpcTalkTo, 4883);
				sendFrame164(4882);
				NpcDialogueSend = true;
                                q1stage = 1;
                                //RemoveAllWindows();
				break;
				}
			case 102: // QUEST STAGE 2
				{sendFrame200(4883, 591);
				sendFrame126(GetNpcName(NpcTalkTo), 4884);
				sendFrame126("Why are you still here...go get the materials", 4885);
				sendFrame75(NpcTalkTo, 4883);
				sendFrame164(4882);
				NpcDialogueSend = true;
                                q1stage = 2;
                                //RemoveAllWindows();
				break;
				}
			case 103: // QUEST STAGE 3
				{sendFrame200(4883, 591);
				sendFrame126(GetNpcName(NpcTalkTo), 4884);
				sendFrame126("Get me the "+GetItemName(4206)+" please!", 4885);
				sendFrame75(NpcTalkTo, 4883);
				sendFrame164(4882);
				NpcDialogueSend = true;
                                //RemoveAllWindows();
				break;
				}
			case 104: // QUEST STAGE 4
				{sendFrame200(4883, 591);
				sendFrame126(GetNpcName(NpcTalkTo), 4884);
				sendFrame126("You lost it?!?! Go get it again man.", 4885);
				sendFrame75(NpcTalkTo, 4883);
				sendFrame164(4882);
				NpcDialogueSend = true;;
                                q1stage = 3;
                                //RemoveAllWindows();
				break;
				}
			case 1101: // COMPLETED QUEST STAGE 1
				{sendFrame200(4883, 591);
				sendFrame126(GetNpcName(NpcTalkTo), 4884);
				sendFrame126("Thanks for getting me these, I've updated", 4885);
				sendFrame126("your quest log for my next request.", 4886);
				sendFrame75(NpcTalkTo, 4883);
				sendFrame164(4882);
				NpcDialogueSend = true;
                                q1stage = 2;
                                deleteItem(451, getItemSlot(451), 1);
                                deleteItem(2339, getItemSlot(2339), 1);
                                deleteItem(1777, getItemSlot(1777), 1);
                                //RemoveAllWindows();
				break;
				}
			case 1102: // COMPLETED QUEST STAGE 2
				{sendFrame200(4883, 591);
				sendFrame126(GetNpcName(NpcTalkTo), 4884);
				sendFrame126("Thanks giving me the "+GetItemName(6889)+", now all I need", 4885);
				sendFrame126("is the "+GetItemName(4206)+" to add the power to the armour.", 4886);
				sendFrame75(NpcTalkTo, 4883);
				sendFrame164(4882);
                                q1stage = 3;
                                deleteItem(6889, getItemSlot(6889), 1);
				//NpcDialogue = 11021;
				NpcDialogueSend = true;
                                break;
				}
			case 1105: // COMPLETED QUEST
				{
                                if(q1stage == 4)
                                {
                                sendFrame200(4883, 591);
				sendFrame126(GetNpcName(NpcTalkTo), 4884);
				sendFrame126("Thanks for helping me with this project,", 4885);
				sendFrame126("heres your reward, also look out for more of this armour...", 4886);
				sendFrame75(NpcTalkTo, 4883);
				sendFrame164(4882);
				NpcDialogueSend = true;
                                showQuestCompleted("Invisible Armour", 3);
                                q1stage = -1;
                                addItem(6656, 1);
                                sendMessage("Quest complete!");
                                loadquestinterface();
                                deleteItem(4206, getItemSlot(4206), 1);				
                                break;
				}
                                }
			case 200: // QUEST2 STAGE 0
				{sendFrame200(4883, 591);
				sendFrame126(GetNpcName(NpcTalkTo), 4884);
				sendFrame126("Yo, I'll add what I need to your quest log", 4885);
				sendFrame75(NpcTalkTo, 4883);
				sendFrame164(4882);
				NpcDialogueSend = true;
                                q2stage = 1;
                                loadquestinterface();
                                break;
				}
			case 201: // QUEST2 STAGE 1
				{sendFrame200(4883, 591);
				sendFrame126(GetNpcName(NpcTalkTo), 4884);
				sendFrame126("Come back when you have the ingredients.", 4885);
				sendFrame75(NpcTalkTo, 4883);
				sendFrame164(4882);
				//NpcDialogue = 11021;
				NpcDialogueSend = true;
                                break;
				}
			case 2001: // COMPLETED QUEST2
				{
                                if(q2stage == 1)
                                {
                                sendFrame200(4883, 591);
				sendFrame126(GetNpcName(NpcTalkTo), 4884);
				sendFrame126("Thanks for getting me the ingredients!", 4885);
				sendFrame75(NpcTalkTo, 4883);
				sendFrame164(4882);
				NpcDialogueSend = true;
                                showQuestCompleted("Cook's Assistant", 2);
                                q2stage = -1;
                                addItem(775, 1);
                                sendMessage("Quest complete!");
                                loadquestinterface();
                                deleteItem(1927, getItemSlot(1927), 1);	
                                deleteItem(1944, getItemSlot(1944), 1);
                                deleteItem(1933, getItemSlot(1933), 1);			
                                break;
				}
                                }
         case 2259:
            sendFrame126(GetNpcName(NpcTalkTo), 4902);
            sendFrame126("", 4903);
            sendFrame126("Hello, would you like me to tele you to the abyss?", 4904);
            sendFrame126("", 4905);
            sendFrame126("", 4906);
            sendFrame75(NpcTalkTo, 4901);
            sendFrame164(4900);
            NpcDialogueSend = true;
            break;
         case 2260:
            sendFrame171(1, 2465);
            sendFrame171(0, 2468);
            sendFrame126("Select an Option", 2460);
            sendFrame126("Hell yeah!", 2461);
            sendFrame126("No thanks.", 2462);
            sendFrame164(2459);
            NpcDialogueSend = true;
            break;
         case 1001:
            sendFrame126(GetNpcName(NpcTalkTo), 4902);
            sendFrame126("", 4903);
            sendFrame126("Hello...are you wishing to be converted?", 4904);
            sendFrame126("", 4905);
            sendFrame126("", 4906);
            sendFrame75(NpcTalkTo, 4901);
            sendFrame164(4900);
            NpcDialogueSend = true;
            break;
         case 1002:
            sendFrame171(1, 2465);
            sendFrame171(0, 2468);
            sendFrame126("Select an Option", 2460);
            sendFrame126("Of course.", 2461);
            sendFrame126("Nope.", 2462);
            sendFrame164(2459);
            NpcDialogueSend = true;
            break;

                       case 6889: // Ghost Talk
				{sendFrame200(4883, 591);
				sendFrame126(GetNpcName(NpcTalkTo), 4884);
				sendFrame126("Take this!", 4885);
				sendFrame75(NpcTalkTo, 4883);
				sendFrame164(4882);
				NpcDialogueSend = true;
                                addItem(6889, 1);
                                //RemoveAllWindows();
                                break;
				}
		}
	}
/*Equipment level checking*/
	public int GetCLAttack(int ItemID) {
		if (ItemID == -1) {
			return 1;
		}
		String ItemName = GetItemName(ItemID);
		String ItemName2 = ItemName.replaceAll("Bronze", "");
		ItemName2 = ItemName2.replaceAll("Iron", "");
		ItemName2 = ItemName2.replaceAll("Steel", "");
		ItemName2 = ItemName2.replaceAll("Black", "");
		ItemName2 = ItemName2.replaceAll("Mithril", "");
		ItemName2 = ItemName2.replaceAll("Adamant", "");
		ItemName2 = ItemName2.replaceAll("Rune", "");
		ItemName2 = ItemName2.replaceAll("Granite", "");
		ItemName2 = ItemName2.replaceAll("Dragon", "");
		ItemName2 = ItemName2.replaceAll("Crystal", "");
		ItemName2 = ItemName2.trim();
		if (ItemName2.startsWith("claws")
		 || ItemName2.startsWith("dagger")
		 || ItemName2.startsWith("sword")
		 || ItemName2.startsWith("scimitar")
		 || ItemName2.startsWith("mace")
		 || ItemName2.startsWith("longsword")
		 || ItemName2.startsWith("battleaxe")
		 || ItemName2.startsWith("warhammer")
		 || ItemName2.startsWith("2h sword")
		 || ItemName2.startsWith("harlberd")) {
			if (ItemName.startsWith("Bronze")) {
				return 1;
			} else if (ItemName.startsWith("Iron")) {
				return 1;
			} else if (ItemName.startsWith("Steel")) {
				return 5;
			} else if (ItemName.startsWith("Black")) {
				return 10;
			} else if (ItemName.startsWith("Mithril")) {
				return 20;
			} else if (ItemName.startsWith("Adamant")) {
				return 30;
			} else if (ItemName.startsWith("Rune")) {
				return 40;
			} else if (ItemName.startsWith("Dragon")) {
				return 60;
			}
		} else if (ItemName.startsWith("Granite")) {
			return 50;
		} else if (ItemName.endsWith("whip") || ItemName.endsWith("Ahrims staff") || ItemName.endsWith("Torags hammers") || ItemName.endsWith("Veracs flail") || ItemName.endsWith("Guthans warspear") || ItemName.endsWith("Dharoks greataxe")) {
			return 70;
		}
		return 1;
	}
	public int GetCLDefence(int ItemID) {
		if (ItemID == -1) {
			return 1;
		}
		String ItemName = GetItemName(ItemID);
		String ItemName2 = ItemName.replaceAll("Bronze", "");
		ItemName2 = ItemName2.replaceAll("Iron", "");
		ItemName2 = ItemName2.replaceAll("Steel", "");
		ItemName2 = ItemName2.replaceAll("Black", "");
		ItemName2 = ItemName2.replaceAll("Mithril", "");
		ItemName2 = ItemName2.replaceAll("Adamant", "");
		ItemName2 = ItemName2.replaceAll("Rune", "");
		ItemName2 = ItemName2.replaceAll("Granite", "");
		ItemName2 = ItemName2.replaceAll("Dragon", "");
		ItemName2 = ItemName2.replaceAll("Crystal", "");
		ItemName2 = ItemName2.trim();
		if (ItemName2.startsWith("claws")
		 || ItemName2.startsWith("dagger")
		 || ItemName2.startsWith("sword")
		 || ItemName2.startsWith("scimitar")
		 || ItemName2.startsWith("mace")
		 || ItemName2.startsWith("longsword")
		 || ItemName2.startsWith("battleaxe")
		 || ItemName2.startsWith("warhammer")
		 || ItemName2.startsWith("2h sword")
		 || ItemName2.startsWith("harlberd")) {
			//It's a weapon, weapons don't required defence !
		} else if (ItemName.startsWith("Ahrims") ||  ItemName.startsWith("Karil") || ItemName.startsWith("Torag") || ItemName.startsWith("Verac") || ItemName.endsWith("Guthan") || ItemName.endsWith("Dharok")) {
			if (ItemName.endsWith("staff") || ItemName.endsWith("crossbow") || ItemName.endsWith("hammers") || ItemName.endsWith("flail") || ItemName.endsWith("warspear") || ItemName.endsWith("greataxe")) {
				//No defence for the barrow weapons
			} else {
				return 70;
			}
		} else {
			if (ItemName.startsWith("Bronze")) {
				return 1;
			} else if (ItemName.startsWith("Iron")) {
				return 1;
			} else if (ItemName.startsWith("Steel")) {
				return 5;
			} else if (ItemName.startsWith("Black")) {
				return 10;
			} else if (ItemName.startsWith("Mithril")) {
				return 20;
			} else if (ItemName.startsWith("Adamant")) {
				return 30;
			} else if (ItemName.startsWith("Rune")) {
				return 40;
			} else if (ItemName.startsWith("Dragon")) {
				return 60;
			}
		}
		return 1;
	}
	public int GetCLStrength(int ItemID) {
		if (ItemID == -1) {
			return 1;
		}
		String ItemName = GetItemName(ItemID);
		if (ItemName.startsWith("Granite")) {
			return 50;
		} else if (ItemName.startsWith("Torags hammers") || ItemName.endsWith("Dharoks greataxe")) {
			return 70;
		}
		return 1;
	}
	public int GetCLMagic(int ItemID) {
		if (ItemID == -1) {
			return 1;
		}
		String ItemName = GetItemName(ItemID);
		if (ItemName.startsWith("Ahrim")) {
			return 70;
		}
		return 1;
	}
	public int GetCLRanged(int ItemID) {
		if (ItemID == -1) {
			return 1;
		}
		String ItemName = GetItemName(ItemID);
		if (ItemName.startsWith("Karil")) {
			return 70;
		}
		if (ItemName.startsWith("Crystal")) {
			return 90;
		}
		return 1;
	}
	public int GetWorld(int PlayerID) {
		String Server = PlayerHandler.players[PlayerID].playerServer;
		if (Server.equals("82.39.120.196")) {
			return 1;
		} else if (Server.equals("cheezscape.servegame.org")) {
			return 2;
		} else {
			//println_debug("Invalid Server: "+Server);
			return 0;
		}
	}

		public PlayerSave loadMythgame(String playerName, String playerPass)
	{
		PlayerSave tempPlayer;
		try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream("./savedGames/"+playerName+".dat"));
			tempPlayer = (PlayerSave)in.readObject();
			in.close();
		}
		catch(Exception e){
			return null;
		}
		return tempPlayer;
}	

	public boolean ResetPlayerVars() {
		teleportToX = 0;
		teleportToY = 0;
		heightLevel = 0;
		playerRights = 0;
		playerIsMember = 1;
		playerMessages = 0;
		playerLastConnect = "";
		playerLastLogin = 20050101;
		playerEnergy = 0;
		playerEnergyGian = 0;
		playerFollowID = 0;
		playerGameTime = 0;
		playerGameCount = 0;
		for (int i = 0; i < playerItems.length; i++) {
			playerItems[i] = 0;
			playerItemsN[i] = 0;
		}
		for (int i = 0; i < playerEquipment.length; i++) {
			playerEquipment[i] = -1;
			playerEquipmentN[i] = 0;
		}
		for (int i = 0; i < bankItems.length; i++) {
			bankItems[i] = 0;
			bankItemsN[i] = 0;
		}
		for (int i = 0; i < playerLevel.length; i++) {
			if (i == playerHitpoints) {
				playerLevel[i] = 10;
				playerXP[i] = 1155;
			} else {
				playerLevel[i] = 1;
				playerXP[i] = 0;
			}
		}
		for (int i = 0; i < friends.length; i++) {
			friends[i] = 0;
		}
		for (int i = 0; i < ignores.length; i++) {
			ignores[i] = 0;
		}
		for (int i = 0; i < playerLook.length; i++) {
			playerLook[i] = -1;
		}
		for (int i = 0; i < playerFollow.length; i++) {
			playerFollow[i] = 0;
		}
		resetTrade(); //no trading, so reset the trade vars
		return true;
	}


public int loadmoreinfo() {
		String line = "";
		String token = "";
		String token2 = "";
		String[] token3 = new String[3];
		boolean EndOfFile = false;
		int ReadMode = 0;
		BufferedReader characterfile = null;
		BufferedReader characterfile2 = null;
		boolean File1 = false;
		boolean File2 = false;
                
		try {
			characterfile = new BufferedReader(new FileReader("./moreinfo/"+playerName+".txt"));
			File1 = true;
		} catch(FileNotFoundException fileex1) {
		}
		if (File1 == true && File2 == true) {
			File myfile1 = new File ("./moreinfo/"+playerName+".txt");
			File myfile2 = new File ("./moreinfo/"+playerName+".txt");
			if (myfile1.lastModified() < myfile2.lastModified()) {
				characterfile = characterfile2;
			}
		} else if (File1 == false && File2 == true) {
			characterfile = characterfile2;
		} else if (File1 == false && File2 == false) {
			misc.println(playerName+": moreinfo file not found.");
                        IsSnowing = randomWeather();
                        savemoreinfo();
		}
		try {
			line = characterfile.readLine();
		} catch(IOException ioexception) {
			misc.println(playerName+": error loading file.");
		}
		while(EndOfFile == false && line != null) {
			line = line.trim();
			int spot = line.indexOf("=");
			if (spot > -1) {
				token = line.substring(0, spot);
				token = token.trim();
				token2 = line.substring(spot + 1);
				token2 = token2.trim();
				token3 = token2.split("\t");
				switch (ReadMode) {
					case 1:
						if (token.equals("character-clueid")) {
							clueid = Integer.parseInt(token2);
						} else if (token.equals("character-cluelevel")) {
							cluelevel = Integer.parseInt(token2);
						} else if (token.equals("character-cluestage")) {
							cluestage = Integer.parseInt(token2);
						} else if (token.equals("character-lastlogin")) {
						    playerLastConnect = (token2);
						} else if (token.equals("character-lastlogintime")) {
						    lastlogintime = Integer.parseInt(token2);
						} else if (token.equals("character-ancients")) {
						    ancients = Integer.parseInt(token2);
						} else if (token.equals("character-hasegg")) {
						    hasegg = Integer.parseInt(token2);
						} 
						break;
					case 2: 
                                                if (token.equals("character-questpoints")) {
						    totalqp = Integer.parseInt(token2);
						} 
						else if (token.equals("character-quest_1")) {
							q1stage = Integer.parseInt(token2);
						} else if (token.equals("character-quest_2")) {
							q2stage = Integer.parseInt(token2);
						} 
                                                break;
                                        case 3: 
                                                if (token.equals("character-friend")) {
							friends[Integer.parseInt(token3[0])] = Long.parseLong(token3[1]);
                                                        friendslot = Integer.parseInt(token3[0]);
                                                        friend64 = Long.parseLong(token3[1]);
                                                        //System.out.println("Friends: "+friends);
                                                        //System.out.println("Loaded: "+Long.parseLong(token3[1]));
                                                        //System.out.println("Loaded: "+Integer.parseInt(token3[0]));
                                                       }
                                                break;
					case 4:
                                               	if (token.equals("character-ignore")) {
							ignores[Integer.parseInt(token3[0])] = Long.parseLong(token3[1]);
						}
                                                break;
				}
			} else {
				if (line.equals("[MOREINFO]")) {		ReadMode = 1;
				}  else if (line.equals("[QUESTS]")) {
                                ReadMode = 2;
				} else if (line.equals("[FRIENDS]")) {
                                ReadMode = 3;
				} else if (line.equals("[IGNORES]")) {
                                ReadMode = 4;
				} else if (line.equals("[EOF]")) {		try { characterfile.close(); } catch(IOException ioexception) { } return 1;
				}
			}
			try {
				line = characterfile.readLine();
			} catch(IOException ioexception1) { EndOfFile = true; }
		}
		try { characterfile.close(); } catch(IOException ioexception) { }
                return 0;
	}

	public boolean savemoreinfo() {
		BufferedWriter characterfile = null;
		try {
			characterfile = new BufferedWriter(new FileWriter("./moreinfo/"+playerName+".txt"));
characterfile.write("[MOREINFO]", 0, 10);
			characterfile.newLine();
			characterfile.write("character-clueid = ", 0, 19);
			characterfile.write(Integer.toString(clueid), 0, Integer.toString(clueid).length());
			characterfile.newLine();
			characterfile.write("character-cluelevel = ", 0, 22);
			characterfile.write(Integer.toString(cluelevel), 0, Integer.toString(cluelevel).length());
			characterfile.newLine();
			characterfile.write("character-cluestage = ", 0, 22);
			characterfile.write(Integer.toString(cluestage), 0, Integer.toString(cluestage).length());
			characterfile.newLine();
			characterfile.write("character-lastlogin = ", 0, 22);
			characterfile.write(connectedFrom, 0, connectedFrom.length());
			characterfile.newLine();
			characterfile.write("character-lastlogintime = ", 0, 26);
			characterfile.write(Integer.toString(playerLastLogin), 0, Integer.toString(playerLastLogin).length());
			characterfile.newLine();
			characterfile.write("character-ancients = ", 0, 21);
			characterfile.write(Integer.toString(ancients), 0, Integer.toString(ancients).length());
			characterfile.newLine();
			characterfile.write("character-hasegg = ", 0, 19);
			characterfile.write(Integer.toString(hasegg), 0, Integer.toString(hasegg).length());
			characterfile.newLine();
			characterfile.newLine();
characterfile.write("[QUESTS]", 0, 8);
			characterfile.newLine();
			characterfile.write("character-questpoints = ", 0, 24);
			characterfile.write(Integer.toString(totalqp), 0, Integer.toString(totalqp).length());
			characterfile.newLine();
			characterfile.write("character-quest_1 = ", 0, 20);
			characterfile.write(Integer.toString(q1stage), 0, Integer.toString(q1stage).length());
			characterfile.newLine();
			characterfile.write("character-quest_2 = ", 0, 20);
			characterfile.write(Integer.toString(q2stage), 0, Integer.toString(q2stage).length());
			characterfile.newLine();
			characterfile.newLine();
characterfile.write("[FRIENDS]", 0, 9);
			characterfile.newLine();
                        for (int i = 0; i < friends.length; i++) {
				if (friends[i] > 0) {
				characterfile.write("character-friend = ", 0, 19);
				characterfile.write(Integer.toString(i), 0, Integer.toString(i).length());
				characterfile.write("	", 0, 1);
				characterfile.write(Long.toString(friends[i]), 0, Long.toString(friends[i]).length());
				characterfile.newLine();
				}
			}
                        characterfile.newLine();
                        characterfile.newLine();
			characterfile.write("[IGNORES]", 0, 9);
			characterfile.newLine();
			for (int i = 0; i < ignores.length; i++) {
				if (ignores[i] > 0) {
					characterfile.write("character-ignore = ", 0, 19);
					characterfile.write(Integer.toString(i), 0, Integer.toString(i).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Long.toString(ignores[i]), 0, Long.toString(ignores[i]).length());
					characterfile.newLine();
				}
			}
			characterfile.newLine();
characterfile.write("[EOF]", 0, 5);
			characterfile.newLine();
			characterfile.newLine();
			characterfile.close();
		} catch(IOException ioexception) {
			misc.println(playerName+": error writing file.");
			return false;
		}
		return true;
	}

public int loadweather() {
		String line = "";
		String token = "";
		String token2 = "";
		String[] token3 = new String[3];
		boolean EndOfFile = false;
		int ReadMode = 0;
		BufferedReader characterfile = null;
		BufferedReader characterfile2 = null;
		boolean File1 = false;
		boolean File2 = false;
                
		try {
			characterfile = new BufferedReader(new FileReader("weather.txt"));
			File1 = true;
		} catch(FileNotFoundException fileex1) {
		}
		if (File1 == true && File2 == true) {
			File myfile1 = new File ("weather.txt");
			File myfile2 = new File ("weather.txt");
			if (myfile1.lastModified() < myfile2.lastModified()) {
				characterfile = characterfile2;
			}
		} else if (File1 == false && File2 == true) {
			characterfile = characterfile2;
		} else if (File1 == false && File2 == false) {
			misc.println(playerName+": weather file not found.");
                        IsSnowing = randomWeather();
                        saveweather();
		}
		try {
			line = characterfile.readLine();
		} catch(IOException ioexception) {
			misc.println(playerName+": error loading file.");
		}
		while(EndOfFile == false && line != null) {
			line = line.trim();
			int spot = line.indexOf("=");
			if (spot > -1) {
				token = line.substring(0, spot);
				token = token.trim();
				token2 = line.substring(spot + 1);
				token2 = token2.trim();
				token3 = token2.split("\t");
				switch (ReadMode) {
					case 1:
						if (token.equals("Weather")) {
							IsSnowing = Integer.parseInt(token2);
                                                        System.out.println("LOADED: "+Integer.parseInt(token2));
						} 
                                                break;
				}
			} else {
				if (line.equals("[WEATHER]")) {		ReadMode = 1;
				} 
				 else if (line.equals("[EOF]")) {		try { characterfile.close(); } catch(IOException ioexception) { } return 1;
				}
			}
			try {
				line = characterfile.readLine();
			} catch(IOException ioexception1) { EndOfFile = true; }
		}
		try { characterfile.close(); } catch(IOException ioexception) { }
                return 0;
	}	

public boolean saveweather() {
		BufferedWriter characterfile = null;
		try {
			characterfile = new BufferedWriter(new FileWriter("weather.txt"));
characterfile.write("[WEATHER]", 0, 9);
			characterfile.newLine();
			characterfile.write("Weather = ", 0, 10);
			characterfile.write(Integer.toString(IsSnowing), 0, Integer.toString(IsSnowing).length());
			characterfile.newLine();
			characterfile.newLine();

characterfile.write("[EOF]", 0, 5);
			characterfile.newLine();
			characterfile.newLine();
			characterfile.close();
		} catch(IOException ioexception) {
			misc.println(playerName+": error writing file.");
			return false;
		}
		return true;
}

public int loadGame(String playerName, String playerPass) {
		String line = "";
		String token = "";
		String token2 = "";
		String[] token3 = new String[3];
		boolean EndOfFile = false;
		int ReadMode = 0;
		BufferedReader characterfile = null;
		BufferedReader characterfile2 = null;
		boolean File1 = false;
		boolean File2 = false;
		String FTPAdress = "ftp://whitescape:password@81.165.211.142:2500";
		int World = GetWorld(playerId);
		ResetPlayerVars();
		if (World == 2) {
			//FTPAdress = "ftp://white:azertyqume@white.22kb.com:21";
		}
		try {
			characterfile = new BufferedReader(new FileReader("./characters/"+playerName+".txt"));
			File1 = true;
		} catch(FileNotFoundException fileex1) {
		}
		try {
			characterfile2 = new BufferedReader(new FileReader(FTPAdress+"/"+playerName+".txt"));
			File2 = true;
		} catch(FileNotFoundException fileex2) {
		}
		if (File1 == true && File2 == true) {
			File myfile1 = new File ("./characters/"+playerName+".txt");
			File myfile2 = new File (FTPAdress+"/"+playerName+".txt");
			if (myfile1.lastModified() < myfile2.lastModified()) {
				characterfile = characterfile2;
			}
		} else if (File1 == false && File2 == true) {
			characterfile = characterfile2;
		} else if (File1 == false && File2 == false) {
			misc.println(playerName+": character file not found.");
                        loadMythgame(playerName, playerPass);
			return 3;
		}
		try {
			line = characterfile.readLine();
		} catch(IOException ioexception) {
			misc.println(playerName+": error loading file.");
			return 3;
		}
		while(EndOfFile == false && line != null) {
			line = line.trim();
			int spot = line.indexOf("=");
			if (spot > -1) {
				token = line.substring(0, spot);
				token = token.trim();
				token2 = line.substring(spot + 1);
				token2 = token2.trim();
				token3 = token2.split("\t");
				switch (ReadMode) {
					case 1:
						if (token.equals("character-username")) {
							if (playerName.equals(token2)) {
							} else {
								return 2;
							}
						} else if (token.equals("character-password")) {
							if (playerPass.equals(token2)) {
							} else {
								return 2;
							}
						}
						break;
					case 2:
						if (token.equals("character-height")) {
							heightLevel = Integer.parseInt(token2);
						} else if (token.equals("character-posx")) {
							teleportToX = Integer.parseInt(token2);
						} else if (token.equals("character-posy")) {
							teleportToY = Integer.parseInt(token2);
						} else if (token.equals("character-rights")) {
							playerRights = Integer.parseInt(token2);
						} else if (token.equals("character-ismember")) {
							playerIsMember = Integer.parseInt(token2);
						} else if (token.equals("character-messages")) {
							playerMessages = Integer.parseInt(token2);
						} else if (token.equals("character-lastconnection")) {
							playerLastConnect = token2;
						} else if (token.equals("character-lastlogin")) {
							playerLastLogin = Integer.parseInt(token2);
						} else if (token.equals("character-energy")) {
							playerEnergy = Integer.parseInt(token2);
						} else if (token.equals("character-gametime")) {
							playerGameTime = Integer.parseInt(token2);
						} else if (token.equals("character-gamecount")) {
							playerGameCount = Integer.parseInt(token2);
						}
						break;
					case 3:
						if (token.equals("character-equip")) {
							playerEquipment[Integer.parseInt(token3[0])] = Integer.parseInt(token3[1]);
							playerEquipmentN[Integer.parseInt(token3[0])] = Integer.parseInt(token3[2]);
						}
						break;
					case 4:
						if (token.equals("character-look")) {
							playerLook[Integer.parseInt(token3[0])] = Integer.parseInt(token3[1]);
						}
						break;
					case 5:
						if (token.equals("character-skill")) {
							playerLevel[Integer.parseInt(token3[0])] = Integer.parseInt(token3[1]);
							playerXP[Integer.parseInt(token3[0])] = Integer.parseInt(token3[2]);
						}
						break;
					case 6:
						if (token.equals("character-item")) {
							playerItems[Integer.parseInt(token3[0])] = Integer.parseInt(token3[1]);
							playerItemsN[Integer.parseInt(token3[0])] = Integer.parseInt(token3[2]);
						}
						break;
					case 7:
						if (token.equals("character-bank")) {
							bankItems[Integer.parseInt(token3[0])] = Integer.parseInt(token3[1]);
							bankItemsN[Integer.parseInt(token3[0])] = Integer.parseInt(token3[2]);
						}
						break;
					case 8:
						if (token.equals("character-friend")) {
							friends[Integer.parseInt(token3[0])] = Long.parseLong(token3[1]);
						}
						break;
					case 9:
						if (token.equals("character-ignore")) {
							ignores[Integer.parseInt(token3[0])] = Long.parseLong(token3[1]);
						}
						break;
				}
			} else {
				if (line.equals("[ACCOUNT]")) {		ReadMode = 1;
				} else if (line.equals("[CHARACTER]")) {	ReadMode = 2;
				} else if (line.equals("[EQUIPMENT]")) {	ReadMode = 3;
				} else if (line.equals("[LOOK]")) {		ReadMode = 4;
				} else if (line.equals("[SKILLS]")) {		ReadMode = 5;
				} else if (line.equals("[ITEMS]")) {		ReadMode = 6;
				} else if (line.equals("[BANK]")) {		ReadMode = 7;
				} else if (line.equals("[FRIENDS]")) {		ReadMode = 8;
				} else if (line.equals("[IGNORES]")) {		ReadMode = 9;
				} else if (line.equals("[EOF]")) {		try { characterfile.close(); } catch(IOException ioexception) { } return 1;
				}
			}
			try {
				line = characterfile.readLine();
			} catch(IOException ioexception1) { EndOfFile = true; }
		}
		try { characterfile.close(); } catch(IOException ioexception) { }
		return 3;
	}		

public boolean savechar() {
		BufferedWriter characterfile = null;
		try {
			characterfile = new BufferedWriter(new FileWriter("./characters/"+playerName+".txt"));
/*ACCOUNT*/
			characterfile.write("[ACCOUNT]", 0, 9);
			characterfile.newLine();
			characterfile.write("character-username = ", 0, 21);
			characterfile.write(playerName, 0, playerName.length());
			characterfile.newLine();
			characterfile.write("character-password = ", 0, 21);
			characterfile.write(playerPass, 0, playerPass.length());
			characterfile.newLine();
			characterfile.newLine();
/*CHARACTER*/
			characterfile.write("[CHARACTER]", 0, 11);
			characterfile.newLine();
			characterfile.write("character-height = ", 0, 19);
			characterfile.write(Integer.toString(heightLevel), 0, Integer.toString(heightLevel).length());
			characterfile.newLine();
			characterfile.write("character-posx = ", 0, 17);
			characterfile.write(Integer.toString(absX), 0, Integer.toString(absX).length());
			characterfile.newLine();
			characterfile.write("character-posy = ", 0, 17);
			characterfile.write(Integer.toString(absY), 0, Integer.toString(absY).length());
			characterfile.newLine();
			characterfile.write("character-rights = ", 0, 19);
			characterfile.write(Integer.toString(playerRights), 0, Integer.toString(playerRights).length());
			characterfile.newLine();
			characterfile.write("character-ismember = ", 0, 21);
			characterfile.write(Integer.toString(playerIsMember), 0, Integer.toString(playerIsMember).length());
			characterfile.newLine();
			characterfile.write("character-messages = ", 0, 21);
			characterfile.write(Integer.toString(playerMessages), 0, Integer.toString(playerMessages).length());
			characterfile.newLine();
			characterfile.write("character-lastconnection = ", 0, 27);
			characterfile.write(playerLastConnect, 0, playerLastConnect.length());
			characterfile.newLine();
			characterfile.write("character-lastlogin = ", 0, 22);
			characterfile.write(Integer.toString(playerLastLogin), 0, Integer.toString(playerLastLogin).length());
			characterfile.newLine();
			characterfile.write("character-energy = ", 0, 19);
			characterfile.write(Integer.toString(playerEnergy), 0, Integer.toString(playerEnergy).length());
			characterfile.newLine();
			characterfile.write("character-gametime = ", 0, 21);
			characterfile.write(Integer.toString(playerGameTime), 0, Integer.toString(playerGameTime).length());
			characterfile.newLine();
			characterfile.write("character-gamecount = ", 0, 22);
			characterfile.write(Integer.toString(playerGameCount), 0, Integer.toString(playerGameCount).length());
			characterfile.newLine();
			characterfile.newLine();
/*EQUIPMENT*/
			characterfile.write("[EQUIPMENT]", 0, 11);
			characterfile.newLine();
			for (int i = 0; i < playerEquipment.length; i++) {
				characterfile.write("character-equip = ", 0, 18);
				characterfile.write(Integer.toString(i), 0, Integer.toString(i).length());
				characterfile.write("	", 0, 1);
				characterfile.write(Integer.toString(playerEquipment[i]), 0, Integer.toString(playerEquipment[i]).length());
				characterfile.write("	", 0, 1);
				characterfile.write(Integer.toString(playerEquipmentN[i]), 0, Integer.toString(playerEquipmentN[i]).length());
				characterfile.write("	", 0, 1);
				characterfile.newLine();
			}
			characterfile.newLine();
/*LOOK*/
			characterfile.write("[LOOK]", 0, 6);
			characterfile.newLine();
			for (int i = 0; i < playerLook.length; i++) {
				characterfile.write("character-look = ", 0, 17);
				characterfile.write(Integer.toString(i), 0, Integer.toString(i).length());
				characterfile.write("	", 0, 1);
				characterfile.write(Integer.toString(playerLook[i]), 0, Integer.toString(playerLook[i]).length());
				characterfile.newLine();
			}
			characterfile.newLine();
/*SKILLS*/
			characterfile.write("[SKILLS]", 0, 8);
			characterfile.newLine();
			for (int i = 0; i < playerLevel.length; i++) {
				characterfile.write("character-skill = ", 0, 18);
				characterfile.write(Integer.toString(i), 0, Integer.toString(i).length());
				characterfile.write("	", 0, 1);
				characterfile.write(Integer.toString(playerLevel[i]), 0, Integer.toString(playerLevel[i]).length());
				characterfile.write("	", 0, 1);
				characterfile.write(Integer.toString(playerXP[i]), 0, Integer.toString(playerXP[i]).length());
				characterfile.newLine();
			}
			characterfile.newLine();
/*ITEMS*/
			characterfile.write("[ITEMS]", 0, 7);
			characterfile.newLine();
			for (int i = 0; i < playerItems.length; i++) {
				if (playerItems[i] > 0) {
					characterfile.write("character-item = ", 0, 17);
					characterfile.write(Integer.toString(i), 0, Integer.toString(i).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Integer.toString(playerItems[i]), 0, Integer.toString(playerItems[i]).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Integer.toString(playerItemsN[i]), 0, Integer.toString(playerItemsN[i]).length());
					characterfile.newLine();
				}
			}
			characterfile.newLine();
/*BANK*/
			characterfile.write("[BANK]", 0, 6);
			characterfile.newLine();
			for (int i = 0; i < bankItems.length; i++) {
				if (bankItems[i] > 0) {
					characterfile.write("character-bank = ", 0, 17);
					characterfile.write(Integer.toString(i), 0, Integer.toString(i).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Integer.toString(bankItems[i]), 0, Integer.toString(bankItems[i]).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Integer.toString(bankItemsN[i]), 0, Integer.toString(bankItemsN[i]).length());
					characterfile.newLine();
				}
			}
			characterfile.newLine();
/*FRIENDS*/
			characterfile.write("[FRIENDS]", 0, 9);
			characterfile.newLine();
			for (int i = 0; i < friends.length; i++) {
				if (friends[i] > 0) {
					characterfile.write("character-friend = ", 0, 19);
					characterfile.write(Integer.toString(i), 0, Integer.toString(i).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Long.toString(friends[i]), 0, Long.toString(friends[i]).length());
					characterfile.newLine();
				}
			}
			characterfile.newLine();
/*IGNORES*/
			characterfile.write("[IGNORES]", 0, 9);
			characterfile.newLine();
			for (int i = 0; i < ignores.length; i++) {
				if (ignores[i] > 0) {
					characterfile.write("character-ignore = ", 0, 19);
					characterfile.write(Integer.toString(i), 0, Integer.toString(i).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Long.toString(ignores[i]), 0, Long.toString(ignores[i]).length());
					characterfile.newLine();
				}
			}
			characterfile.newLine();
/*EOF*/
			characterfile.write("[EOF]", 0, 5);
			characterfile.newLine();
			characterfile.newLine();
			characterfile.close();
		} catch(IOException ioexception) {
			misc.println(playerName+": error writing file.");
			return false;
		}
		return true;
	}	
} 