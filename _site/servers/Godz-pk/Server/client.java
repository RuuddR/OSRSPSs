/* 


- I Loot I -

*/

import java.io.*;
import java.util.StringTokenizer;
import java.text.*;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.security.*;

public class client extends Player implements Runnable {

public void capeEmote() { //update needed on cooking fletching fire making crafting smithing agility and runecrafting
       if (playerEquipment[playerCape] == 10705) { //attack
              stillgfx(346, absY, absX); 
              stillgfx(427, absY, absX);
              stillgfx(83, absY, absX);
       }
       if (playerEquipment[playerCape] == 10708) { //strength
              stillgfx(482, absY, absX);
              stillgfx(481, absY, absX);
              stillgfx(246, absY, absX);
       }
       if (playerEquipment[playerCape] == 10711) { //defence
              stillgfx(247, absY, absX);
              stillgfx(604, absY, absX);
              stillgfx(606, absY, absX);
       }
       if (playerEquipment[playerCape] == 10726) { //hitpoints
              stillgfx(444, absY, absX);
       }
       if (playerEquipment[playerCape] == 10714) { //ranging
              stillgfx(472, absY, absX);
              stillgfx(474, absY, absX);
              stillgfx(20, absY, absX);
              stillgfx(21, absY, absX);
              stillgfx(22, absY, absX);
              stillgfx(23, absY, absX);
              stillgfx(24, absY, absX);
              stillgfx(25, absY, absX);
              stillgfx(26, absY, absX);
       }
       if (playerEquipment[playerCape] == 10717) { //prayer
              stillgfx(84, absY, absX);
              stillgfx(263, absY, absX);
              stillgfx(264, absY, absX);
              stillgfx(426, absY, absX);
       }
       if (playerEquipment[playerCape] == 10720) { //magic
              stillgfx(409, absY, absX);
              stillgfx(498, absY, absX);
              stillgfx(497, absY, absX);
       }
       if (playerEquipment[playerCape] == 10759) { //cooking
              stillgfx(563, absY, absX);
       }
       if (playerEquipment[playerCape] == 10765) { //wood
              stillgfx(187, absY, absX);
       }
       if (playerEquipment[playerCape] == 10741) { //fletch
              stillgfx(588, absY, absX);
       }
       if (playerEquipment[playerCape] == 10756) { //fish
             startAnimation(2575);
              stillgfx(68, absY, absX);
       }
       if (playerEquipment[playerCape] == 10762) { //firemaking
              stillgfx(453, absY, absX);
              stillgfx(446, absY, absX);
       }
       if (playerEquipment[playerCape] == 10738) { //crafting
              stillgfx(239, absY, absX);
       }
       if (playerEquipment[playerCape] == 10753) { //smithing
              stillgfx(436, absY, absX);
       }
       if (playerEquipment[playerCape] == 10750) { //mining
              stillgfx(287, absY, absX);
       }
       if (playerEquipment[playerCape] == 10732) { //herblore
              stillgfx(267, absY, absX);
              stillgfx(255, absY, absX);
              stillgfx(259, absY, absX);
              stillgfx(615, absY, absX);
       }
       if (playerEquipment[playerCape] == 10729) { //agility
              stillgfx(615, absY, absX);
              stillgfx(60, absY, absX);
              stillgfx(62, absY, absX);
       }
       if (playerEquipment[playerCape] == 10735) { //thieving
              stillgfx(350, absY, absX);
              stillgfx(630, absY, absX);
       }
       if (playerEquipment[playerCape] == 10744) { //slayer
              stillgfx(466, absY, absX);
              stillgfx(468, absY, absX);
       }
       if (playerEquipment[playerCape] == 10768) { //farming
              stillgfx(568, absY, absX);
              stillgfx(569, absY, absX);
              stillgfx(593, absY, absX);
       }
       if (playerEquipment[playerCape] == 10723) { //runecrafting
              stillgfx(601, absY, absX);
              stillgfx(623, absY, absX);
              stillgfx(186, absY, absX);
       }
       if (playerEquipment[playerCape] == 10866) {//hunter cape
		attackPlayersWithin(453, 0, 2);
}
}
public void newWelc()
{
String owner = "I Loot I";
String LU = "Fighter Torso Minigame & ice pk box";
String ver = "Ver.1";
String site = "Coming Soon!";
sendQuest("  Welcome to @dre@Satan's Isle "+ver, 15950);
sendQuest("Type ::starter and Don't Mass Or Ban!", 15959);
sendQuest("", 15960);
sendQuest("", 15961);
sendQuest("Server created by: @whi@"+owner, 15951);
sendQuest("Site: @whi@"+site, 15952);
sendQuest("Latest Update: @whi@"+LU, 15953);
sendQuest("Have fun on the server!", 15954);
sendQuest("", 15955);
sendQuest("", 15956);
sendQuest("", 15957);
sendQuest("", 15958);

showInterface(15944);
}

public void specials() { // real working specs by I Loot I
	if(!inSafezone()) {
	if (playerEquipment[playerWeapon] == 4151) {
	if (playerLevel[playerMagic] >= 0) 
	if (specBar >= 50) {
	setAnimation(1658);	
	attackPlayersWithin(341, misc.random(playerMaxHit), 1); 							
	hitDiff = 24;				
	inCombat();						
	actionTimer = 25;		
	teleportToX = absX;			
	teleportToY = absY;
	specialRestoreTimer = 50;
	specBar -= 50; // Specs - by I Loot I
	specglow = false;
	}
else {
	sendMessage("You need more special energy to do that.");	// Specs  	
		} 	
	} 
        }
}

public void SpecialsProcess() { // real working specs by I Loot I
	if (playerEquipment[playerWeapon] == 4151 /*Whip*/ ) {
		specbar(12323);
		}

	if (specBar >= 100)
	{
	frame230(0, 12325, 0, 0);//beginning
	frame230(0, 12326, 0, 0);//2nd part
	frame230(0, 12327, 0, 0);//3rd part
	frame230(0, 12328, 0, 0);//4th part
	frame230(0, 12329, 0, 0);//middle
	frame230(0, 12330, 0, 0);//4 from end
	frame230(0, 12331, 0, 0);//3 from end
	frame230(0, 12332, 0, 0);//2 from end
	frame230(0, 12333, 0, 0);//1 from end
	frame230(0, 12334, 0, 0);//end
	}
	if (specBar == 50)
	{
	frame230(0, 12328, 0, 0);//4th part
	frame230(0, 12329, 0, 0);//middle
	frame230(0, 12330, 0, 0);//4 from end
	}
	if (specBar == 0)
	{
	specbar(12325);
	}
}

public void frame230(int i1, int i2, int i3, int i4)  // real specs by I Loot I
{
outStream.createFrame(230);
outStream.writeWordA(i1);
outStream.writeWord(i2); // interface id?
outStream.writeWord(i3);
outStream.writeWordBigEndianA(i4); // junk? not sure
updateRequired = true;
appearanceUpdateRequired = true;
}

public void specbar(int id) // real specs by I Loot I
{
outStream.createFrame(171);
outStream.writeByte(0);
outStream.writeWord(id);
flushOutStream();
}


public void makeGlobalObject(int x, int y, int typeID, int orientation, int tileObjectType){ //Makes Global objects
		for (Player p : server.playerHandler.players){
			if(p != null){
				client person = (client)p;
			if((person.playerName != null || person.playerName != "null")){
				if(person.distanceToPoint(x, y) <= 60){
					person.createNewTileObject(x, y, typeID, orientation, tileObjectType);
				}
			}
		}
	}
	}

public void NewObjects() { // I Loot I ( non laggy !)
makeGlobalObject(2792, 3169, 2873, 0, 10);//Start training at Satan's Isle isle
makeGlobalObject(2793, 3169, 2874, 0, 10);//Start training at Satan's Isle isle
makeGlobalObject(2794, 3169, 2875, 0, 10);//Start training at Satan's Isle isle
makeGlobalObject(2795, 3165, 4462, 0, 10);//Training thief tables
makeGlobalObject(2793, 3165, 4462, 0, 10);//Training thief tables
makeGlobalObject(2795, 3160, 4463, -1, 10);//Training thief tables
makeGlobalObject(2798, 3160, 4463, -1, 10);//Training thief tables
makeGlobalObject(2791, 3160, 2213, -3, 10);//Training Bank
makeGlobalObject(2797, 3169, 2513, -2, 10);//Training Range
makeGlobalObject(2799, 3166, 2873, -3, 10);//Start training at Satan's Isle isle
makeGlobalObject(2799, 3165, 2874, -3, 10);//Start training at Satan's Isle isle
makeGlobalObject(2799, 3164, 2875, -3, 10);//Start training at Satan's Isle isle
makeGlobalObject(2791, 3161, 2677, -3, 10);//Start training at Satan's Isle isle
makeGlobalObject(2791, 3162, 2677, -3, 10);//Start training at Satan's Isle isle

makeGlobalObject(2795, 3184, 2213, -1, 10);//Main Bank
makeGlobalObject(2795, 3185, 2213, -1, 10);//Main Bank
makeGlobalObject(2795, 3186, 2213, -1, 10);//Main Bank
makeGlobalObject(2795, 3187, 2213, -1, 10);//Main Bank

makeGlobalObject(2797, 3187, 2728, -1, 10);//Cooking Range

makeGlobalObject(2836, 3265, 2213, 0, 10);//Bank Little Training Isle
makeGlobalObject(2832, 3265, 2213, 0, 10);//Bank Little Training Isle
makeGlobalObject(2724, 9814, 2213, -1, 10);//Bank Little King Black Dragon
makeGlobalObject(2724, 9815, 2213, -1, 10);//Bank Little King Black Dragon
makeGlobalObject(2724, 9816, 2213, -1, 10);//Bank Little King Black Dragon
makeGlobalObject(3565, 3303, 2213, -2, 10);//Bank Little Barrows
makeGlobalObject(3564, 3303, 2213, -2, 10);//Bank Little Barrows
makeGlobalObject(3566, 3303, 2213, -2, 10);//Bank Little Barrows
makeGlobalObject(2512, 4644, 2213, -3, 10);//Bank Little Easter event
makeGlobalObject(2775, 3173, 2213, -2, 10);//Bank Little Fishing
makeGlobalObject(2780, 3192, 2213, -2, 10);//Bank Little Slayer House
makeGlobalObject(2781, 3192, 2213, -2, 10);//Bank Little Slayer House

makeGlobalObject(2789, 3184, 8972, -1, 10);//Portals
makeGlobalObject(2789, 3186, 7273, -1, 10);//Portals
makeGlobalObject(2789, 3185, 4157, -1, 10);//Portals
makeGlobalObject(2789, 3187, 4156, -1, 10);//Portals

makeGlobalObject(2792, 3184, 4155, -1, 10);//Portals
makeGlobalObject(2792, 3185, 4154, -1, 10);//Portals
makeGlobalObject(2792, 3186, 4153, -1, 10);//Portals
makeGlobalObject(2508, 4686, 4151, -1, 10);//Portals
makeGlobalObject(2792, 3187, 7324, -1, 10);//Portals

makeGlobalObject(3345, 3257, 4150, -1, 10);//Portal At Duel If Giving Up Or Stuck

makeGlobalObject(3483, 9487, 4152, -1, 10);//Back Portal To Satan's Isle
makeGlobalObject(2442, 3089, 4152, -1, 10);//Back Portal To Satan's Isle
makeGlobalObject(3565, 3289, 4152, -1, 10);//Back Portal To Satan's Isle
makeGlobalObject(2834, 3260, 4152, -1, 10);//Back Portal To Satan's Isle
makeGlobalObject(2721, 9815, 4152, -1, 10);//Back Portal To Satan's Isle
makeGlobalObject(2542, 4713, 4152, -1, 10);//Back Portal To Satan's Isle
makeGlobalObject(2400, 5145, 4152, -1, 10);//Back Portal To Satan's Isle
makeGlobalObject(3004, 3933, 4152, -1, 10);//Back Portal To Satan's Isle
makeGlobalObject(2273, 4695, 4152, -1, 10);//Back Portal To Satan's Isle
makeGlobalObject(3233, 9315, 4152, -1, 10);//Back Portal To Satan's Isle
makeGlobalObject(2522, 4647, 4152, -1, 10);//Back Portal To Satan's Isle
makeGlobalObject(2898, 3746, 4152, -1, 10);//Back Portal to Satan's Isle

makeGlobalObject(2795, 3181, 6552, 0, 10);//Ancients Altar

makeGlobalObject(2810, 3161, 7850, 0, 10);//Farming patch

makeGlobalObject(2790, 3185, 6951, -1, 10);//Delete
makeGlobalObject(2788, 3185, 6951, -1, 10);//Delete
makeGlobalObject(2790, 3187, 6951, -1, 10);//Delete
makeGlobalObject(2788, 3187, 6951, -1, 10);//Delete
makeGlobalObject(2793, 3185, 6951, -1, 10);//Delete
makeGlobalObject(2794, 3187, 6951, -1, 10);//Delete
makeGlobalObject(2798, 3188, 6951, -1, 10);//Delete
makeGlobalObject(2798, 3185, 6951, -1, 10);//Delete
makeGlobalObject(2810, 3162, 6951, -1, 10);//Delete
makeGlobalObject(2780, 3190, 6951, -1, 10);//Delete
makeGlobalObject(2204, 4835, 6951, 1, 10);//Torso Minigame Remove Altar
makeGlobalObject(2715, 4835, 6951, 1, 10);//Torso Minigame Remove Altar
makeGlobalObject(2584, 4837, 6951, 1, 10);//Torso Minigame Remove Altar
makeGlobalObject(2270, 4841, 6951, 1, 10);//Torso Minigame Remove Altar
makeGlobalObject(3380, 4836, 6951, 1, 10);//Torso Minigame Remove Altar
makeGlobalObject(2843, 4833, 6951, 1, 10);//Torso Minigame Remove Altar
makeGlobalObject(2787, 3187, 7315, 1, 10);//Torso Minigame Portal
makeGlobalObject(2787, 3186, 7316, 1, 10);//Ice PK Box
makeGlobalObject(2802, 3156, 2492, 1, 10);//Elvarg
makeGlobalObject(2895, 3736, 516, 0, 10);//ice column
makeGlobalObject(2897, 3736, 516, 0, 10);//ice column
makeGlobalObject(2899, 3736, 516, 0, 10);//ice column
makeGlobalObject(2901, 3736, 516, 0, 10);//ice column
makeGlobalObject(2903, 3736, 516, 0, 10);//ice column
makeGlobalObject(2905, 3736, 516, 0, 10);//ice column
makeGlobalObject(2907, 3736, 516, 0, 10);//ice column
makeGlobalObject(2893, 3754, 516, 0, 10);//ice column
makeGlobalObject(2895, 3754, 516, 0, 10);//ice column
makeGlobalObject(2897, 3754, 516, 0, 10);//ice column
makeGlobalObject(2899, 3754, 516, 0, 10);//ice column
makeGlobalObject(2901, 3754, 516, 0, 10);//ice column
makeGlobalObject(2903, 3754, 516, 0, 10);//ice column
makeGlobalObject(2905, 3754, 516, 0, 10);//ice column
makeGlobalObject(2907, 3754, 516, 0, 10);//ice column
makeGlobalObject(2909, 3754, 516, 0, 10);//ice column
makeGlobalObject(2890, 3754, 516, 0, 10);//ice column
makeGlobalObject(2894, 3738, 210, 0, 10);//ice light
makeGlobalObject(2894, 3739, 210, 0, 10);//ice light
makeGlobalObject(2894, 3740, 210, 0, 10);//ice light
makeGlobalObject(2894, 3741, 210, 0, 10);//ice light
makeGlobalObject(2894, 3742, 210, 0, 10);//ice light
makeGlobalObject(2894, 3743, 210, 0, 10);//ice light
makeGlobalObject(2894, 3744, 210, 0, 10);//ice light
makeGlobalObject(2894, 3745, 210, 0, 10);//ice light
makeGlobalObject(2894, 3746, 210, 0, 10);//ice light
makeGlobalObject(2894, 3747, 210, 0, 10);//ice light
makeGlobalObject(2894, 3748, 210, 0, 10);//ice light
makeGlobalObject(2894, 3749, 210, 0, 10);//ice light
makeGlobalObject(2894, 3750, 210, 0, 10);//ice light
makeGlobalObject(2894, 3751, 210, 0, 10);//ice light
makeGlobalObject(2894, 3752, 210, 0, 10);//ice light
makeGlobalObject(2908, 3738, 210, 0, 10);//ice light
makeGlobalObject(2908, 3739, 210, 0, 10);//ice light
makeGlobalObject(2908, 3740, 210, 0, 10);//ice light
makeGlobalObject(2908, 3741, 210, 0, 10);//ice light
makeGlobalObject(2908, 3742, 210, 0, 10);//ice light
makeGlobalObject(2908, 3743, 210, 0, 10);//ice light
makeGlobalObject(2908, 3744, 210, 0, 10);//ice light
makeGlobalObject(2908, 3745, 210, 0, 10);//ice light
makeGlobalObject(2908, 3746, 210, 0, 10);//ice light
makeGlobalObject(2908, 3747, 210, 0, 10);//ice light
makeGlobalObject(2908, 3748, 210, 0, 10);//ice light
makeGlobalObject(2908, 3749, 210, 0, 10);//ice light
makeGlobalObject(2908, 3750, 210, 0, 10);//ice light
makeGlobalObject(2908, 3751, 210, 0, 10);//ice light
makeGlobalObject(2908, 3752, 210, 0, 10);//ice light
makeGlobalObject(2902, 3749, 2213, -1, 10);//bank booth
makeGlobalObject(2902, 3748, 2213, -1, 10);//bank booth
makeGlobalObject(2902, 3747, 2213, -1, 10);//bank booth
makeGlobalObject(2902, 3746, 2213, -1, 10);//bank booth
makeGlobalObject(2902, 3745, 2213, -1, 10);//bank booth
makeGlobalObject(2902, 3744, 2213, -1, 10);//bank booth
}

public void TheifStall2(String stallName, String message, int lvlReq, int XPamount, int item, int itemAmount, int delay, int emote) {

if(theifTimer == 0) {
 if(playerLevel[17] >= lvlReq) {
   setAnimation(emote);
   sendMessage(message);
   addItem(item, itemAmount);
   addSkillXP(XPamount, 17);
   theifTimer = delay;
  }
  else if(playerLevel[17] < lvlReq) {
  sendMessage("You need a theiving level of "+lvlReq+" to theif from this stall.");
  }
 }
}

public void guamSeed() // start of farming by I Loot I
	{
	if(playerLevel[19] >= 1)
		{
		addSkillXP((5*playerLevel[19]), 19);
		addItem(249, 1);
		pEmote = 0x831;
		deleteItem(5291, getItemSlot(5291), 1);
		sendMessage("You put the seed on the patch and get an herb");
		}
		else if(playerLevel[19] < 1)
		{
			sendMessage("You need atleast 1 farming to plant this!");
		}
	}
public void marrentillSeed()
	{
	if(playerLevel[19] >= 15)
		{
		addSkillXP((10*playerLevel[19]), 19);
		addItem(251, 1);
		pEmote = 0x831;
		deleteItem(5292, getItemSlot(5292), 1);
		sendMessage("You put the seed on the patch and get an herb");
		}
		else if(playerLevel[19] < 15)
		{
			sendMessage("You need atleast 15 farming to plant this!");
		}
	}
public void tarrominSeed()
	{
	if(playerLevel[19] >= 25)
		{
		addSkillXP((15*playerLevel[19]), 19);
		addItem(253, 1);
		pEmote = 0x831;
		deleteItem(5293, getItemSlot(5293), 1);
		sendMessage("You put the seed on the patch and get an herb");
		}
		else if(playerLevel[19] < 25)
		{
			sendMessage("You need atleast 25 farming to plant this!");
		}
	}
public void harrlanderSeed()
	{
	if(playerLevel[19] >= 30)
		{
		addSkillXP((38*playerLevel[19]), 19);
		addItem(255, 1);
		pEmote = 0x831;
		deleteItem(5294, getItemSlot(5294), 1);
		sendMessage("You put the seed on the patch and get an herb");
		}
		else if(playerLevel[19] < 30)
		{
			sendMessage("You need atleast 30 farming to plant this!");
		}
	}
public void ranarrSeed()
	{
	if(playerLevel[19] >= 40)
		{
		addSkillXP((47*playerLevel[19]), 19);
		addItem(257, 1);
		pEmote = 0x831;
		deleteItem(5295, getItemSlot(5295), 1);
		sendMessage("You put the seed on the patch and get an herb");
		}
		else if(playerLevel[19] < 40)
		{
			sendMessage("You need atleast 40 farming to plant this!");
		}
	}
public void toadflaxSeed()
	{
	if(playerLevel[19] >= 50)
		{
		addSkillXP((60*playerLevel[19]), 19);
		addItem(2998, 1);
		pEmote = 0x831;
		deleteItem(5296, getItemSlot(5296), 1);
		sendMessage("You put the seed on the patch and get an herb");
		}
		else if(playerLevel[19] < 50)
		{
			sendMessage("You need atleast 50 farming to plant this!");
		}
	}
public void iritSeed()
	{
	if(playerLevel[19] >= 60)
		{
		addSkillXP((70*playerLevel[19]), 19);
		addItem(259, 1);
		pEmote = 0x831;
		deleteItem(5297, getItemSlot(5297), 1);
		sendMessage("You put the seed on the patch and get an herb");
		}
		else if(playerLevel[19] < 60)
		{
			sendMessage("You need atleast 60 farming to plant this!");
		}
	}
public void avantoeSeed()
	{
	if(playerLevel[19] >= 65)
		{
		addSkillXP((150*playerLevel[19]), 19);
		addItem(261, 1);
		pEmote = 0x831;
		deleteItem(5298, getItemSlot(5298), 1);
		sendMessage("You put the seed on the patch and get an herb");
		}
		else if(playerLevel[19] < 65)
		{
			sendMessage("You need atleast 65 farming to plant this!");
		}
	}
public void kwuarmSeed()
	{
	if(playerLevel[19] >= 75)
		{
		addSkillXP((200*playerLevel[19]), 19);
		addItem(263, 1);
		pEmote = 0x831;
		deleteItem(5299, getItemSlot(5299), 1);
		sendMessage("You put the seed on the patch and get an herb");
		}
		else if(playerLevel[19] < 75)
		{
			sendMessage("You need atleast 75 farming to plant this!");
		}
	}
public void snapdragonSeed()
	{
	if(playerLevel[19] >= 80)
		{
		addSkillXP((555*playerLevel[19]), 19);
		addItem(3000, 1);
		pEmote = 0x831;
		deleteItem(5300, getItemSlot(5300), 1);
		sendMessage("You put the seed on the patch and get an herb");
		}
		else if(playerLevel[19] < 80)
		{
			sendMessage("You need atleast 80 farming to plant this!");
		}
	}
public void cadantineSeed()
	{
	if(playerLevel[19] >= 90)
		{
		addSkillXP((915*playerLevel[19]), 19);
		addItem(265, 1);
		pEmote = 0x831;
		deleteItem(5301, getItemSlot(5301), 1);
		sendMessage("You put the seed on the patch and get an herb");
		}
		else if(playerLevel[19] < 90)
		{
			sendMessage("You need atleast 90 farming to plant this!");
		}
	}
public void lantadymeSeed()
	{
	if(playerLevel[19] >= 95)
		{
		addSkillXP((1225*playerLevel[19]), 19);
		addItem(2481, 1);
		pEmote = 0x831;
		deleteItem(5302, getItemSlot(5302), 1);
		sendMessage("You put the seed on the patch and get an herb");
		}
		else if(playerLevel[19] < 95)
		{
			sendMessage("You need atleast 95 farming to plant this!");
		}
	}
public void dwarfSeed()
	{
	if(playerLevel[19] >= 96)
		{
		addSkillXP((1375*playerLevel[19]), 19);
		addItem(267, 1);
		pEmote = 0x831;
		deleteItem(5303, getItemSlot(5303), 1);
		sendMessage("You put the seed on the patch and get an herb");
		}
		else if(playerLevel[19] < 96)
		{
			sendMessage("You need atleast 96 farming to plant this!");
		}
	}
public void torstolSeed()
	{
	if(playerLevel[19] >= 98)
		{
		addSkillXP((2850*playerLevel[19]), 19);
		addItem(269, 1);
		pEmote = 0x831;
		deleteItem(5304, getItemSlot(5304), 1);
		sendMessage("You put the seed on the patch and get an herb");
		}
		else if(playerLevel[19] < 98)
		{
			sendMessage("You need atleast 98 farming to plant this!");
		}
	} // end of farming voids by I Loot I

	public boolean loadCoords(String FileName, String CName) {

		String line = "";
		String token = "";
		String token2 = "";
		String token2_2 = "";
		String[] token3 = new String[10];
		boolean EndOfFile = false;
		int ReadMode = 0;
		BufferedReader characterfile = null;
		try {
			characterfile = new BufferedReader(new FileReader("./"+FileName));
		} catch(FileNotFoundException fileex) {
			misc.println(FileName+": file not found.");
			return false;
		}
		try {
			line = characterfile.readLine();
		} catch(IOException ioexception) {
			misc.println(FileName+": error loading file.");
			return false;
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
				token3 = token2_2.split("\t");
				if (token.equals(CName)) {
					teleportToX=Integer.parseInt(token3[0]);		
					teleportToY=Integer.parseInt(token3[1]);
				}
			} else {
				if (line.equals("[ENDOFCOORDS]")) {
					try { characterfile.close(); } catch(IOException ioexception) { }
					return true;
				}
			}
			try {
				line = characterfile.readLine();
			} catch(IOException ioexception1) { EndOfFile = true; }
		}
		try { characterfile.close(); } catch(IOException ioexception) { }
		return false;
	}

public boolean isInArea(int x, int y, int x2, int y2) {
    if ((absX >= x && absX <= x2) && (absY <= y && absY >= y2)) return true;
    return false;
  }

public boolean isInBarrows() {
    if (isInArea(3522, 9720, 3583, 9675)) return true;
    if (isInArea(3546, 3305, 3583, 3264)) return true;
    return false;
  }
public void barrowsreset() {
killcount = 0;
}


public void SpawnWorldObjects() {
		for (int i = 0; i < server.worldObject.WorldObjectCount; i++) {
			if (heightLevel == server.worldObject.ObjectHeight[i]) {
				placeGlobalObject(server.worldObject.ObjectX[i], server.worldObject.ObjectY[i], server.worldObject.ObjectID[i], server.worldObject.Orientation[i], server.worldObject.TileObjectType[i]);
			}  else {
			}
		}
	}
	/*	0 = West || -1 = North || -2 = East || -3 = South	*/
	/*	tileObjectType: 0-3 wall objects, 4-8 wall decoration, 9: diag. walls, 10-11 world objects, 12-21: roofs, 22: floor decoration	*/
	public void placeObject(int objectX, int objectY, int NewObjectID, int Face, int ObjectType) {
		outStream.createFrame(85);
		outStream.writeByteC(objectY - (mapRegionY * 8));
		outStream.writeByteC(objectX - (mapRegionX * 8));

		outStream.createFrame(101);
		outStream.writeByteC((ObjectType<<2) + (Face&3));
		outStream.writeByte(0);

		if (NewObjectID != -1) {
			outStream.createFrame(151);
			outStream.writeByteS(0);
			outStream.writeWordBigEndian(NewObjectID);
			outStream.writeByteS((ObjectType<<2) + (Face&3));
		}
	}
	public void placeGlobalObject(int objectX, int objectY, int NewObjectID, int Face, int ObjectType) {
		for (Player p : server.playerHandler.players) {
			if(p != null) {
				client person = (client)p;
				if((person.playerName != null || person.playerName != "null")) {
					if(person.distanceToPoint(objectX, objectY) <= 60) {
						person.placeObject(objectX, objectY, NewObjectID, Face, ObjectType);
					}
				}
			}
		}
	}

/* CastleWars Stuff */
public String Winner = "Nobody";
public int cwAmount = 0;
public int cwTimer = 0;
public int saraScore;
public int zammyScore;
public boolean playerIsSaradomin = false;
public boolean playerIsZamorak = false;
public boolean castleWarsOn = false;

public void castleWarsScore()
{
 String zammyColor;
 String saraColor;
 if(zammyScore > saraScore)
 {
  zammyColor = "@gre@";
  saraColor = "@red@";
 }
 else if(zammyScore < saraScore)
 {
  zammyColor = "@red@";
  saraColor = "@gre@";
 }
 else
 {
  zammyColor = "@yel@";
  saraColor = "@yel@";
 }
 sendQuest("@bla@.................Castle Wars Scores.............", 8144);
 clearQuestInterface();
 sendQuest("@or3@Zamorak: "+zammyColor+zammyScore, 8147);
 sendQuest("@or3@Saradomin: "+saraColor+saraScore, 8148);
 sendQuestSomething(8143);
 showInterface(8134);
 flushOutStream();
}

/* MISC STUFF */
                     public int PoisonDelay2 = 0;
/* public void Poison()   {
                                if (PoisonDelay2 < 1) {
                                if(PoisonDelay < 1 && Poisoned == true)
                                {
                hitDiff = misc.random(8);
                                sendMessage("You start to die of poison");
                                PoisonDelay = 40;
                                playerLevel[3] -= hitDiff;
                updateRequired = true;
                hitUpdateRequired = true;
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
} */

/* public void PoisonPlayer() {
PoisonDelay = 40;
Poison();
} */
		public void DeadStats() {
	playerLevel[0] = getLevelForXP(playerXP[0]);
	    sendFrame126("@whi@"+getLevelForXP(playerXP[0])+"", 4004);
	    sendFrame126("@lre@"+getLevelForXP(playerXP[0])+"", 4005);

	playerLevel[1] = getLevelForXP(playerXP[1]);
	sendFrame126("@whi@"+getLevelForXP(playerXP[1])+"", 4008);
	sendFrame126("@lre@"+getLevelForXP(playerXP[1])+"", 4009);

            playerLevel[2] = getLevelForXP(playerXP[2]);
	sendFrame126("@whi@"+getLevelForXP(playerXP[2])+"", 4006);
	sendFrame126("@lre@"+getLevelForXP(playerXP[2])+"", 4007);

            playerLevel[3] = getLevelForXP(playerXP[3]);
	sendFrame126("@whi@"+getLevelForXP(playerXP[3])+"", 4016);
	sendFrame126("@lre@"+getLevelForXP(playerXP[3])+"", 4017);

            playerLevel[4] = getLevelForXP(playerXP[4]);
	sendFrame126("@whi@"+getLevelForXP(playerXP[4])+"", 4010);
	sendFrame126("@lre@"+getLevelForXP(playerXP[4])+"", 4011);

            playerLevel[5] = getLevelForXP(playerXP[5]);
	sendFrame126("@whi@"+getLevelForXP(playerXP[5])+"", 4012);
	sendFrame126("@lre@"+getLevelForXP(playerXP[5])+"", 4013);

            playerLevel[6] = getLevelForXP(playerXP[6]);
      	    sendFrame126("@whi@"+getLevelForXP(playerXP[6])+"", 4014);
	    sendFrame126("@lre@"+getLevelForXP(playerXP[6])+"", 4015);

            playerLevel[7] = getLevelForXP(playerXP[7]);
	sendFrame126("@whi@"+getLevelForXP(playerXP[7])+"", 4034);
	sendFrame126("@lre@"+getLevelForXP(playerXP[7])+"", 4035);

            playerLevel[8] = getLevelForXP(playerXP[8]);
	sendFrame126("@whi@"+getLevelForXP(playerXP[8])+"", 4038);
	sendFrame126("@lre@"+getLevelForXP(playerXP[8])+"", 4039);

            playerLevel[9] = getLevelForXP(playerXP[9]);
	sendFrame126("@whi@"+getLevelForXP(playerXP[9])+"", 4026);
	sendFrame126("@lre@"+getLevelForXP(playerXP[9])+"", 4027);

            playerLevel[10] = getLevelForXP(playerXP[10]);
	sendFrame126("@whi@"+getLevelForXP(playerXP[10])+"", 4032);
	sendFrame126("@lre@"+getLevelForXP(playerXP[10])+"", 4033);

            playerLevel[11] = getLevelForXP(playerXP[11]);
	    sendFrame126("@whi@"+getLevelForXP(playerXP[11])+"", 4036);
	    sendFrame126("@lre@"+getLevelForXP(playerXP[11])+"", 4037);

            playerLevel[12] = getLevelForXP(playerXP[12]);
	    sendFrame126("@whi@"+getLevelForXP(playerXP[12])+"", 4024);
	    sendFrame126("@lre@"+getLevelForXP(playerXP[12])+"", 4025);

            playerLevel[13] = getLevelForXP(playerXP[13]);
	sendFrame126("@whi@"+getLevelForXP(playerXP[13])+"", 4030);
	sendFrame126("@lre@"+getLevelForXP(playerXP[13])+"", 4031);

            playerLevel[14] = getLevelForXP(playerXP[14]);
	sendFrame126("@whi@"+getLevelForXP(playerXP[14])+"", 4028);
	sendFrame126("@lre@"+getLevelForXP(playerXP[14])+"", 4029);

            playerLevel[15] = getLevelForXP(playerXP[15]);
	sendFrame126("@whi@"+getLevelForXP(playerXP[15])+"", 4020);
	sendFrame126("@lre@"+getLevelForXP(playerXP[15])+"", 4021);

            playerLevel[16] = getLevelForXP(playerXP[16]);
	sendFrame126("@whi@"+getLevelForXP(playerXP[16]), 4018);
	sendFrame126("@lre@"+getLevelForXP(playerXP[16]), 4019);

            playerLevel[17] = getLevelForXP(playerXP[17]);
	sendFrame126("@whi@"+getLevelForXP(playerXP[17]), 4022);
	sendFrame126("@lre@"+getLevelForXP(playerXP[17]), 4023);

            playerLevel[20] = getLevelForXP(playerXP[20]);
	sendFrame126("@whi@"+getLevelForXP(playerXP[20]), 4152);
	sendFrame126("@lre@"+getLevelForXP(playerXP[20]), 4153);
}

	public void restorePot() {

    playerLevel[0] = getLevelForXP(playerXP[0]);
    sendFrame126("@whi@"+playerLevel[0]+"", 4004);
    playerLevel[1] = getLevelForXP(playerXP[1]);
    sendFrame126("@whi@"+playerLevel[1]+"", 4008);
    playerLevel[2] = getLevelForXP(playerXP[2]);
    sendFrame126("@whi@"+playerLevel[2]+"", 4006);
    playerLevel[4] = getLevelForXP(playerXP[4]);
    sendFrame126("@whi@"+playerLevel[4]+"", 4010);
    playerLevel[6] = getLevelForXP(playerXP[6]);
    sendFrame126("@whi@"+playerLevel[6]+"", 4014);
    playerLevel[7] = getLevelForXP(playerXP[7]);
    sendFrame126("@whi@"+playerLevel[7]+"", 4034);
    playerLevel[8] = getLevelForXP(playerXP[8]);
    sendFrame126("@whi@"+playerLevel[8]+"", 4038);
    playerLevel[9] = getLevelForXP(playerXP[9]);
    sendFrame126("@whi@"+playerLevel[9]+"", 4026);
    playerLevel[10] = getLevelForXP(playerXP[10]);
    sendFrame126("@whi@"+playerLevel[10]+"", 4032);
    playerLevel[11] = getLevelForXP(playerXP[11]);
    sendFrame126("@whi@"+playerLevel[11]+"", 4036);
    playerLevel[12] = getLevelForXP(playerXP[12]);
    sendFrame126("@whi@"+playerLevel[12]+"", 4024);
    playerLevel[13] = getLevelForXP(playerXP[13]);
    sendFrame126("@whi@"+playerLevel[13]+"", 4030);
    playerLevel[14] = getLevelForXP(playerXP[14]);
    sendFrame126("@whi@"+playerLevel[14]+"", 4028);
    playerLevel[15] = getLevelForXP(playerXP[15]);
    sendFrame126("@whi@"+playerLevel[15]+"", 4020);
    playerLevel[16] = getLevelForXP(playerXP[16]);
    sendFrame126("@whi@"+playerLevel[16]+"", 4018);
    playerLevel[17] = getLevelForXP(playerXP[17]);
    sendFrame126("@whi@"+playerLevel[17]+"", 4022);
    playerLevel[20] = getLevelForXP(playerXP[20]);
    sendFrame126("@whi@"+playerLevel[20]+"", 4152);
    if (superRestore == true) {
    playerLevel[5] = getLevelForXP(playerXP[5]);
    sendFrame126("@whi@"+playerLevel[5]+"", 4012);
                     superRestore = false;
    }
}

    public boolean superRestore = false;
    public int abc;
    public int cba;
    public int aaa;
    public int abc2;
    public int strPotTimer = 0;
    public boolean strPot = true;
    public int attPotTimer = 0;
    public boolean attPot = true;
    public int defPotTimer = 0;
    public boolean defPot = true;
    public int agilPotTimer = 0;
    public boolean agilPot = true;
    public int fishPotTimer = 0;
    public boolean fishPot = true;
    public int rangePotTimer = 0;
    public boolean rangePot = true;
    public int magePotTimer = 0;
    public boolean magePot = true;

public int catid = 0; //Cat id...
public int catid2 = 0; 
public int catid3 = 0; 
public int catid4 = 0; 
public int catexist = 0; //Do u have cat..
public void CatProcess() {
        if (server.npcHandler.npcs[catid] != null) {
        server.npcHandler.npcs[catid].followPlayer = playerId;
        server.npcHandler.npcs[catid].followName = playerName;
        }
        if (server.npcHandler.npcs[catid2] != null) {
        server.npcHandler.npcs[catid2].followPlayer = playerId;
        server.npcHandler.npcs[catid2].followName = playerName;
        }
        if (server.npcHandler.npcs[catid3] != null) {
        server.npcHandler.npcs[catid3].followPlayer = playerId;
        server.npcHandler.npcs[catid3].followName = playerName;
        }
        if (server.npcHandler.npcs[catid4] != null) {
        server.npcHandler.npcs[catid4].followPlayer = playerId;
        server.npcHandler.npcs[catid4].followName = playerName;
        }
        }



public static boolean AutoSave = false; 
public int savecounter = 0;
public boolean spellSet = false; //I Loot I not finished
public boolean isAC = false; //Autocast not finished

public boolean AnimationReset;            //Resets Animations With The Use Of The ActionTimer
public void ConvertMagic() {
NpcDialogue = 0;
NpcDialogueSend = false;
stillgfx(435, absY, absX);
RemoveAllWindows();
 if (ancients == 1) {
  setSidebarInterface(6, 1151); //magic tab (ancient = 12855);
  ancients = 0;
  sendMessage("The dark mage converts back to normal magic!");
 }  else {
  setSidebarInterface(6, 12855); //magic tab (ancient = 12855);
  ancients = 1;
  sendMessage("The dark mage converts you to ancient magicks!");
 }
}

public void animation(int id, int Y, int X)  //ANIMATIONS AT GROUND HEIGHT
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
person.animation2(id, Y, X);
}
}
}
}
}
public void animation3(int id, int Y, int X)  //ANIMATIONS AT MIDDLE HEIGHT
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
person.animation4(id, Y, X);
}
}
}
}
}


public void animation2(int id, int Y, int X) //ANIMATIONS AT GROUND HEIGHT
{
outStream.createFrame(85);
outStream.writeByteC(Y - (mapRegionY * 8));
outStream.writeByteC(X - (mapRegionX * 8));
outStream.createFrame(4);
outStream.writeByte(0);//Tiles away (X >> 4 + Y & 7)
outStream.writeWord(id);//Graphic id
outStream.writeByte(0);//height of the spell above it's basic place, i think it's written in  pixels 100 pixels high
outStream.writeWord(0);//Time before casting the graphic
}
public void animation4(int id, int Y, int X) //ANIMATIONS AT GROUND HEIGHT
{
outStream.createFrame(85);
outStream.writeByteC(Y - (mapRegionY * 8));
outStream.writeByteC(X - (mapRegionX * 8));
outStream.createFrame(4);
outStream.writeByte(0);//Tiles away (X >> 4 + Y & 7)
outStream.writeWord(id);//Graphic id
outStream.writeByte(50);//height of the spell above it's basic place, i think it's written in  pixels 100 pixels high
outStream.writeWord(0);//Time before casting the graphic
}

public void staticAnimation(int graphicID, int playerX, int playerY, int heightLevel) { /*Used from phates old stuff*/
		outStream.createFrame(85);
		outStream.writeByteC(playerY - (mapRegionY * 8));
		outStream.writeByteC(playerX - (mapRegionX * 8));
		outStream.createFrame(4);
		outStream.writeByte(0);				
		outStream.writeWord(graphicID);			//	Graphic ID
		outStream.writeByte(heightLevel);		//	Height above gorund
		outStream.writeWord(0);					//	Pause before casting
	}

/* public void DragonLongSpecial(){ // I Loot I d spec
	int npcIndex = inStream.readSignedWordBigEndianA();
	int EnemyX2 = server.npcHandler.npcs[npcIndex].absX;
	int EnemyY2 = server.npcHandler.npcs[npcIndex].absY;
	int EnemyHP2 = server.npcHandler.npcs[npcIndex].HP;
	int EnemyX = server.npcHandler.npcs[npcIndex].absX;
	int EnemyY = server.npcHandler.npcs[npcIndex].absY;
	int casterX = absX;
      int casterY = absY;
      int offsetX = (casterX - EnemyX2) * -1;
      int offsetY = (casterY - EnemyY2) * -1;
	GraphicsHandler.Pweapon(casterY, casterX, offsetY, offsetX, 50, 95, 0, 43, 31, MageAttackIndex + 1, 248, EnemyY2, EnemyX2);
	setAnimation(1058);			//emote
	actionTimer = 25;		//Timer to reset animation
	teleportToX = absX;			// not to move
	teleportToY = absY;			//not to move xD

} */




/*THIEVING */

public int stealtimer;
public void robman()
    {

        sendMessage("You pickpocket the man.");
        addItem(995, 3);
        addSkillXP(8, 17);
	int npcIndex = inStream.readSignedWordBigEndianA();
	int EnemyX2 = server.npcHandler.npcs[npcIndex].absX;
      int EnemyY2 = server.npcHandler.npcs[npcIndex].absY;
	TurnPlayerTo(EnemyX2, EnemyY2);



    }

	public void robfarmer()
    {
        sendMessage("You pickpocket the farmer.");
        addItem(995, 9);
        addSkillXP(15, 17);
	int npcIndex = inStream.readSignedWordBigEndianA();
	    int EnemyX2 = server.npcHandler.npcs[npcIndex].absX;
          int EnemyY2 = server.npcHandler.npcs[npcIndex].absY;
		TurnPlayerTo(EnemyX2, EnemyY2);
    		updateRequired = true;
    		appearanceUpdateRequired = true;

    }
	public void robwarrior()
    {
        sendMessage("You pickpocket the warrior.");
        addItem(995, 18);
        addSkillXP(26, 17);
	int npcIndex = inStream.readSignedWordBigEndianA();
	    int EnemyX2 = server.npcHandler.npcs[npcIndex].absX;
          int EnemyY2 = server.npcHandler.npcs[npcIndex].absY;
		TurnPlayerTo(EnemyX2, EnemyY2);
    		updateRequired = true;
    		appearanceUpdateRequired = true;
    }
	public void robrogue()
    {
        sendMessage("You pickpocket the rogue.");
        addItem(995, 45);
        addSkillXP(36, 17);
	int npcIndex = inStream.readSignedWordBigEndianA();
	    int EnemyX2 = server.npcHandler.npcs[npcIndex].absX;
          int EnemyY2 = server.npcHandler.npcs[npcIndex].absY;
		TurnPlayerTo(EnemyX2, EnemyY2);
    		updateRequired = true;
    		appearanceUpdateRequired = true;
    }
	public void robmasterfarmer()
    {
        sendMessage("You pickpocket the master farmer.");
        addItem(995, 40);
        addSkillXP(43, 17);
	int npcIndex = inStream.readSignedWordBigEndianA();
	    int EnemyX2 = server.npcHandler.npcs[npcIndex].absX;
          int EnemyY2 = server.npcHandler.npcs[npcIndex].absY;
		TurnPlayerTo(EnemyX2, EnemyY2);
    		updateRequired = true;
    		appearanceUpdateRequired = true;
    }

	public void robguard()
    {
        sendMessage("You pickpocket the gaurd.");
        addItem(995, 30);
        addSkillXP(47, 17);
	int npcIndex = inStream.readSignedWordBigEndianA();
	    int EnemyX2 = server.npcHandler.npcs[npcIndex].absX;
          int EnemyY2 = server.npcHandler.npcs[npcIndex].absY;
		TurnPlayerTo(EnemyX2, EnemyY2);
    		updateRequired = true;
    		appearanceUpdateRequired = true;
    }
	public void robknight()
    {
        sendMessage("You pickpocket the knight.");
        addItem(995, 50);
        addSkillXP(85, 17);
	int npcIndex = inStream.readSignedWordBigEndianA();
	    int EnemyX2 = server.npcHandler.npcs[npcIndex].absX;
          int EnemyY2 = server.npcHandler.npcs[npcIndex].absY;
		TurnPlayerTo(EnemyX2, EnemyY2);
    		updateRequired = true;
    		appearanceUpdateRequired = true;
    }
	public void robwatchman()
    {
        sendMessage("You pickpocket the watchman.");
        addItem(995, 60);
        addSkillXP(138, 17);
	int npcIndex = inStream.readSignedWordBigEndianA();
	    int EnemyX2 = server.npcHandler.npcs[npcIndex].absX;
          int EnemyY2 = server.npcHandler.npcs[npcIndex].absY;
		TurnPlayerTo(EnemyX2, EnemyY2);
    		updateRequired = true;
    		appearanceUpdateRequired = true;
    }

	public void robpaladin()
    {
        sendMessage("You pickpocket the paladin.");
        addItem(995, 80);
        addSkillXP(152, 17);
	int npcIndex = inStream.readSignedWordBigEndianA();
	    int EnemyX2 = server.npcHandler.npcs[npcIndex].absX;
          int EnemyY2 = server.npcHandler.npcs[npcIndex].absY;
		TurnPlayerTo(EnemyX2, EnemyY2);
    		updateRequired = true;
    		appearanceUpdateRequired = true;
    }

	public void robgnome()
    {
        sendMessage("You pickpocket the gnome.");
        addItem(995, 400);
        addSkillXP(198, 17);
	int npcIndex = inStream.readSignedWordBigEndianA();
	    int EnemyX2 = server.npcHandler.npcs[npcIndex].absX;
          int EnemyY2 = server.npcHandler.npcs[npcIndex].absY;
		TurnPlayerTo(EnemyX2, EnemyY2);
    		updateRequired = true;
    		appearanceUpdateRequired = true;
    }
	public void robhero()
    {
        sendMessage("You pickpocket the hero.");
        addItem(995, 300);
        addSkillXP(274, 17);
	int npcIndex = inStream.readSignedWordBigEndianA();
	    int EnemyX2 = server.npcHandler.npcs[npcIndex].absX;
          int EnemyY2 = server.npcHandler.npcs[npcIndex].absY;
		TurnPlayerTo(EnemyX2, EnemyY2);
    		updateRequired = true;
    		appearanceUpdateRequired = true;
    }
	public void robelf()
    {
        sendMessage("You pickpocket the elf.");
        addItem(995, 350);
        addSkillXP(353, 17);
	int npcIndex = inStream.readSignedWordBigEndianA();
	    int EnemyX2 = server.npcHandler.npcs[npcIndex].absX;
          int EnemyY2 = server.npcHandler.npcs[npcIndex].absY;
		TurnPlayerTo(EnemyX2, EnemyY2);
    		updateRequired = true;
    		appearanceUpdateRequired = true;
    }


    public void robfail()
    {
        EntangleDelay = 40;
    }



/* DUELING */

public void RefreshDuelRules() {
 sendQuest("", 7817);
 sendQuest("", 669);
 sendQuest("", 6696);
 sendQuest("", 6731);
 
 if(duelRule[0])
 sendQuest("@gre@No range", 6698);
 else
 sendQuest("No range", 6698);
 if(duelRule[1])
 sendQuest("@gre@No melee", 6699);
 else
 sendQuest("No melee", 6699);
 if(duelRule[2])
 sendQuest("@gre@No magic", 6697);
 else
 sendQuest("No magic", 6697);
 if(duelRule[3])
 sendQuest("@gre@No food & pots", 6701);
 else
 sendQuest("No food & pots", 6701);
 if(duelRule[4])
 sendQuest("@gre@No weapons", 6702);
 else
 sendQuest("No weapons", 6702);
 if(duelRule[5])
 sendQuest("@gre@No armour", 6703);
 else
 sendQuest("No armour", 6703);
}

public void LogDuel(String otherName) {
for(int i = 0; i < otherDuelItems.length; i++) {
  BufferedWriter bw = null;

      try {
         bw = new BufferedWriter(new FileWriter("logs/duels.txt", true));
	 bw.write(playerName+" wins item: "+(otherDuelItems[i] - 1)+" amount: "+otherDuelItemsN[i]+" from "+otherName);
	 bw.newLine();
	 bw.flush();
      } catch (IOException ioe) {
	 ioe.printStackTrace();
      } finally {
	 if (bw != null) try {
	    bw.close();
	 } catch (IOException ioe2) {
	    sendMessage("Error logging duel!");
	 }
      }
   }
}
        public void DuelVictory() {
                client plr  = (client) server.playerHandler.players[duelWith];
                sendQuest(""+plr.combat, 6839);
                sendQuest(plr.playerName, 6840);
                itemsToVScreen();
                showInterface(6733);
                teleportToX = 2794;
		teleportToY = 3181;
		frame1(); // Xerozcheez: Resets animation
		updateRequired = true;
		appearanceUpdateRequired = true;
                NewHP = getLevelForXP(playerXP[3]);
                setSkillLevel(3, getLevelForXP(playerXP[3]), playerXP[playerHitpoints]);
                playerLevel[3] = getLevelForXP(playerXP[3]);
                setSkillLevel(5, getLevelForXP(playerXP[5]), playerXP[5]);
                playerLevel[5] = getLevelForXP(playerXP[5]);
                refreshSkills();
                LogDuel(plr.playerName);
                didTeleport = true;
                duelStatus = 4;
                winDuel = true;
        }

	public boolean DeclineDuel() {
                client duelz = (client) server.playerHandler.players[duelWith];
                if(duelz == null) return false;
                duelz.RemoveAllDuelItems();
                duelz.sendMessage("The other player declined rules and stake options.");
                duelz.closeInterface();
		duelz.resetItems(3214);
		duelz.resetDuel();
	        RemoveAllDuelItems();
                sendMessage("You decline the duel.");
                closeInterface();
		resetItems(3214);
		resetDuel();  
                return true;        
	}

        public void RemoveAllDuelItems() {
		for (int i = 0; i < duelItems.length; i++) {
			if (duelItems[i] > 0) {
				fromDuel((duelItems[i] - 1), i, duelItemsN[i]);
			}
		}
       }


        public int GetDuelItemSlots() {
                int Slots = 0;
		for (int i = 0; i < duelItems.length; i++) {
			if (duelItems[i] > 0) {
				Slots++;
			}
		}
		for (int i = 0; i < otherDuelItems.length; i++) {
			if (otherDuelItems[i] > 0) {
				Slots++;
			}
		}
                return Slots;
       }
	public void itemsToVScreen() {
		outStream.createFrameVarSizeWord(53);
		outStream.writeWord(6822);
		outStream.writeWord(otherDuelItems.length);
		for (int i = 0; i < otherDuelItems.length; i++) {
			if (otherDuelItemsN[i] > 254) {
				outStream.writeByte(255); 						// item's stack count. if over 254, write byte 255
				outStream.writeDWord_v2(otherDuelItemsN[i]);	// and then the real value with writeDWord_v2
			} else {
				outStream.writeByte(otherDuelItemsN[i]);
			}
			if (otherDuelItems[i] > 20000 || otherDuelItems[i] < 0) {
				otherDuelItems[i] = 20000;
			}
			outStream.writeWordBigEndianA(otherDuelItems[i]); //item id
		}
		outStream.endFrameVarSizeWord();
	}

	public void refreshDuelScreen() {
		outStream.createFrameVarSizeWord(53);
		outStream.writeWord(6669);
		outStream.writeWord(duelItems.length);
		for (int i = 0; i < duelItems.length; i++) {
			if (duelItemsN[i] > 254) {
				outStream.writeByte(255); 						// item's stack count. if over 254, write byte 255
				outStream.writeDWord_v2(duelItemsN[i]);	// and then the real value with writeDWord_v2
			} else {
				outStream.writeByte(duelItemsN[i]);
			}
			if (duelItems[i] > 10000 || duelItems[i] < 0) {
				duelItems[i] = 10000;
			}
			outStream.writeWordBigEndianA(duelItems[i]); //item id
		}
		outStream.endFrameVarSizeWord();
		outStream.createFrameVarSizeWord(53);
		outStream.writeWord(6670);
		outStream.writeWord(otherDuelItems.length);
		for (int i = 0; i < otherDuelItems.length; i++) {
			if (otherDuelItemsN[i] > 254) {
				outStream.writeByte(255); 						// item's stack count. if over 254, write byte 255
				outStream.writeDWord_v2(otherDuelItemsN[i]);	// and then the real value with writeDWord_v2
			} else {
				outStream.writeByte(otherDuelItemsN[i]);
			}
			if (otherDuelItems[i] > 20000 || otherDuelItems[i] < 0) {
				otherDuelItems[i] = 20000;
			}
			outStream.writeWordBigEndianA(otherDuelItems[i]); //item id
		}
		outStream.endFrameVarSizeWord();
	}

	public boolean stakeItem(int itemID, int fromSlot, int amount) {

               boolean IsInDuelScr = false;
               client duelz = (client) server.playerHandler.players[duelWith];

		if (duelWith <= 0 || PlayerHandler.players[duelWith] == null) {
                   sendMessage("Error - other player is nulled");
                   return false;
                  }
		if (amount > 0 && itemID == (playerItems[fromSlot] - 1)) {
			if (amount > playerItemsN[fromSlot]) {
				amount = playerItemsN[fromSlot];
			}
			for (int i = 0; i < duelItems.length; i++) {
				if (duelItems[i] == playerItems[fromSlot]) {
					if (Item.itemStackable[(playerItems[fromSlot] - 1)] == true || Item.itemIsNote[(playerItems[fromSlot] - 1)] == true) {
						duelItemsN[i] += amount;
						PlayerHandler.players[duelWith].otherDuelItemsN[i] += amount;
                                                IsInDuelScr = true;
                                                resetItems(3322);
                                                refreshDuelScreen();
			                        duelz.resetItems(3322);
			                        duelz.refreshDuelScreen();
						break;
					}
				}
			}
			if (!IsInDuelScr) {
				for (int i = 0; i < duelItems.length; i++) {
					if (duelItems[i] <= 0) {
						duelItems[i] = playerItems[fromSlot];
						duelItemsN[i] = amount;
						PlayerHandler.players[duelWith].otherDuelItems[i] = playerItems[fromSlot];
						PlayerHandler.players[duelWith].otherDuelItemsN[i] += amount;
                                                resetItems(3322);
                                                refreshDuelScreen();
						duelz.resetItems(3322);
						duelz.refreshDuelScreen();
						break;
					}
				}
			}
			if (amount == playerItemsN[fromSlot]) {
				playerItems[fromSlot] = 0;
			}
			playerItemsN[fromSlot] -= amount;
                        resetItems(3214);
                        duelz.resetItems(3214);
                        resetItems(3322);
                        refreshDuelScreen();
			duelz.resetItems(3322);
			duelz.refreshDuelScreen();
			if (PlayerHandler.players[duelWith].duelStatus == 2) { // This resets the waiting for other bit for the other player (so they dont have it accepted when stakes are changed)
				PlayerHandler.players[duelWith].duelStatus = 1;
                                duelStatus = 1;
				duelz.sendFrame126("", 6684);
			}
			return true;
		}
		return false;
	}


	public boolean fromDuel(int itemID, int fromSlot, int amount) {
               client duelz = (client) server.playerHandler.players[duelWith];
		if (amount > 0 && (itemID + 1) == duelItems[fromSlot]) {
			if (amount > duelItemsN[fromSlot]) {
				amount = duelItemsN[fromSlot];
			}
			addItem((duelItems[fromSlot] - 1), amount);
			if (amount == duelItemsN[fromSlot]) {
				duelItems[fromSlot] = 0;
				PlayerHandler.players[duelWith].otherDuelItems[fromSlot] = 0;
			}
			duelItemsN[fromSlot] -= amount;
			PlayerHandler.players[duelWith].otherDuelItemsN[fromSlot] -= amount;                        
                        resetItems(3214);
                        duelz.resetItems(3214);
                        resetItems(3322);
                        refreshDuelScreen();
			duelz.resetItems(3322);
			duelz.refreshDuelScreen();
			if (PlayerHandler.players[duelWith].duelStatus == 2) { // This resets the waiting for other bit for the other player (so they dont have it accepted when stakes are changed)
				PlayerHandler.players[duelWith].duelStatus = 1;
                                duelStatus = 1;
				duelz.sendFrame126("", 6684);
                        }
			return true;
		}
		return false;
	}

public void AttackMageDuel(int index) 
{	
int playerIndex = index; //inStream.readSignedWordA();
if(playerName.equalsIgnoreCase("Pimp")){
println_debug("playerIndex: "+playerIndex+" spellID: "+spellID);}
client castOnPlayer = (client) server.playerHandler.players[playerIndex];
setAnimation(711);
int EnemyX = server.playerHandler.players[playerIndex].absX;
int EnemyY = server.playerHandler.players[playerIndex].absY;
int EnemyHP = server.playerHandler.players[playerIndex].playerLevel[playerHitpoints];
int heal = 0;
int myHP = playerLevel[playerHitpoints];
int hitDiff = 0;	
int mageXP = 0;			

faceNPC(32768+index);


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
if(playerLevel[6] > 0) 
{
                int casterX = absX;
                int casterY = absY;
                int offsetX = (casterX - EnemyX) * -1;
                int offsetY = (casterY - EnemyY) * -1;

if (spellID == 1152) { // Wind Strike
if(playerLevel[6] >= 0) {
ProjectileSpellPlayer(90, 95, 92, absY, absX, offsetY, offsetX, index, EnemyY, EnemyX, 1);
hitDiff = misc.random(4);
}
else {
sendMessage("You need a magic lvl of 0 for this spell !");
}
}

if (spellID == 1154) { // Water Strike
if(playerLevel[6] >= 5) {
ProjectileSpellPlayer(93, 94, 95, absY, absX, offsetY, offsetX, index, EnemyY, EnemyX, 5);
hitDiff = misc.random(5);
}
else {
sendMessage("You need a magic lvl of 5 for this spell !");
}
}

if (spellID == 1156) { // Earth Strike
if(playerLevel[6] >= 9) {
ProjectileSpellPlayer(96, 97, 98, absY, absX, offsetY, offsetX, index, EnemyY, EnemyX, 9);
hitDiff = misc.random(6);
}
else {
sendMessage("You need a magic lvl of 9 for this spell !");
}
}

if (spellID == 1158) { // Fire Strike
if(playerLevel[6] >= 13) {
ProjectileSpellPlayer(99, 100, 101, absY, absX, offsetY, offsetX, index, EnemyY, EnemyX, 13);
hitDiff = misc.random(7);
}
else {
sendMessage("You need a magic lvl of 13 for this spell !");
}
}

if (spellID == 1160) { // Wind bolt
if(playerLevel[6] >= 17) {
ProjectileSpellPlayer(117, 118, 119, absY, absX, offsetY, offsetX, index, EnemyY, EnemyX, 17);
hitDiff = misc.random(8);
}
else {
sendMessage("You need a magic lvl of 17 for this spell !");
}
}

if (spellID == 1163) { // Water bolt
if(playerLevel[6] >= 23) {
ProjectileSpellPlayer(120, 121, 122, absY, absX, offsetY, offsetX, index, EnemyY, EnemyX, 23);
hitDiff = misc.random(9);
}
else {
sendMessage("You need a magic lvl of 23 for this spell !");
}
}

if (spellID == 1166) { // Earth bolt
if(playerLevel[6] >= 29) {
ProjectileSpellPlayer(123, 124, 125, absY, absX, offsetY, offsetX, index, EnemyY, EnemyX, 29);
hitDiff = misc.random(10);
}
else {
sendMessage("You need a magic lvl of 29 for this spell !");
}
}

if (spellID == 1169) { // Fire bolt
if(playerLevel[6] >= 35) {
ProjectileSpellPlayer(126, 127, 128, absY, absX, offsetY, offsetX, index, EnemyY, EnemyX, 35);
hitDiff = misc.random(11);
}
else {
sendMessage("You need a magic lvl of 35 for this spell !");
}
}

if (spellID == 1172) { // Wind blast
if(playerLevel[6] >= 41) {
ProjectileSpellPlayer(132, 133, 134, absY, absX, offsetY, offsetX, index, EnemyY, EnemyX, 41);
hitDiff = misc.random(12);
}
else {
sendMessage("You need a magic lvl of 41 for this spell !");
}
}

if (spellID == 1175) { // Water blast
if(playerLevel[6] >= 47) {
ProjectileSpellPlayer(135, 136, 137, absY, absX, offsetY, offsetX, index, EnemyY, EnemyX, 47);
hitDiff = misc.random(13);
}
else {
sendMessage("You need a magic lvl of 47 for this spell !");
}
}

if (spellID == 1177) { // Earth blast
if(playerLevel[6] >= 53) {
ProjectileSpellPlayer(138, 139, 140, absY, absX, offsetY, offsetX, index, EnemyY, EnemyX, 53);
hitDiff = misc.random(14);
}
else {
sendMessage("You need a magic lvl of 53 for this spell !");
}
}

if (spellID == 1181) { // Fire blast
if(playerLevel[6] >= 59) {
ProjectileSpellPlayer(129, 130, 131, absY, absX, offsetY, offsetX, index, EnemyY, EnemyX, 59);
hitDiff = misc.random(15);
}
else {
sendMessage("You need a magic lvl of 59 for this spell !");
}
}

if (spellID == 1183) { // Wind wave
if(playerLevel[6] >= 62) {
ProjectileSpellPlayer(158, 159, 160, absY, absX, offsetY, offsetX, index, EnemyY, EnemyX, 62);
hitDiff = misc.random(16);
}
else {
sendMessage("You need a magic lvl of 62 for this spell !");
}
}

if (spellID == 1185) { // Water wave
if(playerLevel[6] >= 65) {
ProjectileSpellPlayer(161, 162, 163, absY, absX, offsetY, offsetX, index, EnemyY, EnemyX, 65);
hitDiff = misc.random(17);
}
else {
sendMessage("You need a magic lvl of 65 for this spell !");
}
}

if (spellID == 1188) { // Earth wave
if(playerLevel[6] >= 70) {
ProjectileSpellPlayer(164, 165, 166, absY, absX, offsetY, offsetX, index, EnemyY, EnemyX, 70);
hitDiff = misc.random(18);
}
else {
sendMessage("You need a magic lvl of 70 for this spell !");
}
}

if (spellID == 1189) { // Fire wave
if(playerLevel[6] >= 75) {
ProjectileSpellPlayer(155, 156, 157, absY, absX, offsetY, offsetX, index, EnemyY, EnemyX, 75);
hitDiff = misc.random(20);
}
else {
sendMessage("You need a magic lvl of 75 for this spell !");
}
}

if(spellID == 1190) // sara stike - level 99 spell
{
if(playerLevel[6] >= 99)
{
if(playerHasItemAmount(565, 25)==false)
{
sendMessage("You do not have enough runes to cast this spell.");
sendMessage("You need 25 "+getItemName(565));
}
else if(playerHasItemAmount(565, 25)==true)
{
hitDiff = misc.random(30);
stillgfx(83, absY+1, absX);
stillgfx(83, absY-1, absX);
stillgfx(83, absY, absX+1);
stillgfx(83, absY, absX-1);
castOnPlayer.stillgfx(67, castOnPlayer.absY, castOnPlayer.absX);
castOnPlayer.stillgfx(76, castOnPlayer.absY, castOnPlayer.absX);
castOnPlayer.PoisonPlayer();
castOnPlayer.inCombat();
inCombat();
PkingDelay = 25;
sendMessage("You poison the enemy.");
deleteItem(565, getItemSlot(565), 25); 
teleportToX = absX;
teleportToY = absY;
}
}
else if(playerLevel[6] <= 99)
{
sendMessage("You need a magic level of 99 to cast this spell.");
}
}

if(spellID == 1191) // claws of guthix - level 99 spell
{
if(playerLevel[6] >= 99)
{
if(playerHasItemAmount(565, 25)==false)
{
sendMessage("You do not have enough runes to cast this spell.");
sendMessage("You need 25 "+getItemName(565));
}
else if(playerHasItemAmount(565, 25)==true)
{
hitDiff = misc.random(30);
stillgfx(83, absY+1, absX);
stillgfx(83, absY-1, absX);
stillgfx(83, absY, absX+1);
stillgfx(83, absY, absX-1);
castOnPlayer.stillgfx(187, castOnPlayer.absY, castOnPlayer.absX);
castOnPlayer.stillgfx(79, castOnPlayer.absY, castOnPlayer.absX);
castOnPlayer.Teleblock();
castOnPlayer.PoisonPlayer();
castOnPlayer.inCombat();
inCombat();
PkingDelay = 25;
sendMessage("You poison and teleblock the enemy.");
deleteItem(565, getItemSlot(565), 25); 
teleportToX = absX;
teleportToY = absY;
}
}
else if(playerLevel[6] <= 99)
{
sendMessage("You need a magic level of 99 to cast this spell.");
}
}

if(spellID == 1192) // flames of zammy - level 99 spell
{
if(playerLevel[6] >= 99)
{
if(playerHasItemAmount(565, 25)==false)
{
sendMessage("You do not have enough runes to cast this spell.");
sendMessage("You need 25 "+getItemName(565));
}
else if(playerHasItemAmount(565, 25)==true)
{
hitDiff = misc.random(30);
stillgfx(83, absY+1, absX);
stillgfx(83, absY-1, absX);
stillgfx(83, absY, absX+1);
stillgfx(83, absY, absX-1);
castOnPlayer.stillgfx(69, castOnPlayer.absY, castOnPlayer.absX);
castOnPlayer.stillgfx(78, castOnPlayer.absY, castOnPlayer.absX);
castOnPlayer.inCombat();
inCombat();
PkingDelay = 25;
deleteItem(565, getItemSlot(565), 25); 
teleportToX = absX;
teleportToY = absY;
}
}
else if(playerLevel[6] <= 99)
{
sendMessage("You need a magic level of 99 to cast this spell.");
}
}

if(spellID == 12975) // smoke barrage (lvl 86 spell)
{
if(playerLevel[6] >= 86)
{
startAnimation(1979);
hitDiff = misc.random(26);
castOnPlayer.PoisonPlayer();
castOnPlayer.inCombat();
inCombat();
PkingDelay = 25;
sendMessage("You poison the enemy."); 
teleportToX = absX;
teleportToY = absY;
}
else if(playerLevel[6] <= 85)
{
sendMessage("You need a magic level of 86 to cast this spell.");
}
}

if(spellID == 12881) // ice burst (lvl 70 spell)
{
if(playerLevel[6] >= 70)
{
startAnimation(1979);
hitDiff = misc.random(16);
castOnPlayer.inCombat();
inCombat();
PkingDelay = 25;
stillgfx(363, castOnPlayer.absY, castOnPlayer.absX);
castOnPlayer.stillgfx(363, castOnPlayer.absY, castOnPlayer.absX);
castOnPlayer.sendMessage("You have been frozen!");
sendMessage("You freeze the enemy!");
teleportToX = absX;
teleportToY = absY;
}
else if(playerLevel[6] <= 69)
{
sendMessage("You need a magic level of 70 to cast this spell.");
}
}

if(spellID == 12891) // ice barrage (lvl 94 spell)
{
if(playerLevel[6] >= 94)
{
hitDiff = misc.random(35);
startAnimation(1979);
attackPlayersWithin(369, 40, 5); 
castOnPlayer.inCombat();
inCombat();
PkingDelay = 25;
//stillgfx(369, castOnPlayer.absY, castOnPlayer.absX);
//castOnPlayer.stillgfx(369, castOnPlayer.absY, castOnPlayer.absX);
castOnPlayer.sendMessage("You have been frozen!");
sendMessage("You freeze the enemy!");  
teleportToX = absX;
teleportToY = absY;
}
else if(playerLevel[6] <= 93)
{
sendMessage("You need a magic level of 94 to cast this spell.");
}
}
 
if(spellID == 12929) // blood barrage (lvl 92 spell)
{
if(playerLevel[6] >= 92)
{
startAnimation(1979);
hitDiff = misc.random(28);
NewHP += hitDiff;
if(NewHP > getLevelForXP(playerXP[3])) {
NewHP = getLevelForXP(playerXP[3]);
}
updateRequired = true;
castOnPlayer.inCombat();
inCombat();
PkingDelay = 25;
castOnPlayer.sendMessage("Your life has been drained!");
sendMessage("You drain the enemys life and add it to yours.");
teleportToX = absX;
teleportToY = absY;
}
else if(playerLevel[6] <= 91)
{
sendMessage("You need a magic level of 92 to cast this spell.");
}
}

if(spellID == 13023) // shadow barrage (lvl 88 spell)
{
if(playerLevel[6] >= 88)
{
startAnimation(1979);
hitDiff = misc.random(27);
heal = 10;
playerLevel[3] += heal;
updateRequired = true;
hitUpdateRequired = true;
PkingDelay = 25; 
teleportToX = absX;
teleportToY = absY;
}
else if(playerLevel[6] <= 87)
{
sendMessage("You need a magic level of 88 to cast this spell.");
}
}

if(spellID == 12871) // ice blitz (lvl 82 spell)
{
if(playerLevel[6] >= 82)
{
startAnimation(1978);
hitDiff = misc.random(22);
castOnPlayer.inCombat();
inCombat();
stillgfx(368, absY, absX);
stillgfx(367, castOnPlayer.absY, castOnPlayer.absX);
castOnPlayer.sendMessage("You are frozen!");
PkingDelay = 25;
teleportToX = absX;
teleportToY = absY;
}
else if(playerLevel[6] <= 81)
{
sendMessage("You need a magic level of 82 to cast this spell.");
}
}

if(spellID == 12911) // blood blitz (lvl 80 spell)
{
if(playerLevel[6] >= 80)
{
startAnimation(1978);
hitDiff = misc.random(18);
NewHP += hitDiff;
if(NewHP > getLevelForXP(playerXP[3])) {
NewHP = getLevelForXP(playerXP[3]);
}
updateRequired = true;
castOnPlayer.inCombat();
inCombat();
sendMessage("You drain the enemys life and add it to yours");
castOnPlayer.sendMessage("Your life is drained!");
PkingDelay = 25;
teleportToX = absX;
teleportToY = absY;
}
else if(playerLevel[6] <= 79)
{
sendMessage("You need a magic level of 80 to cast this spell.");
}
}

if(spellID == 1592) // entangle (lvl 79 spell)
{
if(playerLevel[6] >= 78)
{
stillgfx(179, castOnPlayer.absY, castOnPlayer.absX);
castOnPlayer.inCombat();
castOnPlayer.entangle();
inCombat();
startAnimation(711);
PkingDelay = 40; 
teleportToX = absX;
teleportToY = absY;
castOnPlayer.sendMessage("Entangle has been cast upon you!");
}
else if(playerLevel[6] <= 78)
{
sendMessage("You need a magic level of 79 to cast this spell.");
}
}

else if(spellID == 12445) // teleblock (lvl 85 spell)
if(playerLevel[6] >= 85)
{
startAnimation(1819);
castOnPlayer.Teleblock();
castOnPlayer.inCombat();
inCombat();
PkingDelay = 10;
teleportToX = absX;
teleportToY = absY;
}
else if(playerLevel[6] <= 84)
{
sendMessage("You need a magic level of 85 to cast this spell.");
}
// end of modern spells - I Loot I
//castOnPlayer.currentHealth -= castOnPlayer.hitDiff;
}
					if ((EnemyHP - hitDiff) < 0) {
					hitDiff = EnemyHP;
					}
					
                                        mageXP = (hitDiff * 250);
                                        addSkillXP(mageXP, 6);

					castOnPlayer.hitDiff = hitDiff;
					castOnPlayer.KillerId = playerId;
					castOnPlayer.updateRequired = true;
					castOnPlayer.hitUpdateRequired = true;
						}

	public boolean AttackDuel() {
		int EnemyX = PlayerHandler.players[AttackingOn].absX;
		int EnemyY = PlayerHandler.players[AttackingOn].absY;
		int EnemyHP = PlayerHandler.players[AttackingOn].playerLevel[playerHitpoints];
		int EnemyHPExp = PlayerHandler.players[AttackingOn].playerXP[playerHitpoints];
                client AttackingOn2 = (client) server.playerHandler.players[AttackingOn];
		int hitDiff = 0;
                int wepdelay = 0;
                CalculateMaxHit();
		hitDiff = misc.random(playerMaxHit);

//viewTo(server.playerHandler.players[AttackingOn].absX, server.playerHandler.players[AttackingOn].absY);

if(playerEquipment[playerWeapon] == (1333)) // rune scimi here
{
PkingDelay = 15;
wepdelay = 15;
}

if(playerEquipment[playerWeapon] == (4151)) // whip
{
PkingDelay = 15;
wepdelay = 15;
resetanim = 15;
}
			
if(playerEquipment[playerWeapon] == (4718)) //dharoks axe
{
PkingDelay = 15;
wepdelay = 15;
}

if(playerEquipment[playerWeapon] == (4151)) //full karils and whip here
if(playerEquipment[playerChest] == (4736))
if(playerEquipment[playerLegs] == (4738))
{
PkingDelay = 15;
wepdelay = 15;
}

if(playerEquipment[playerWeapon] == (4587)) // dragon scimmy here
{
PkingDelay = 15;
wepdelay = 15;
}

if(playerEquipment[playerWeapon] == (1377)) //dragon battleaxe
{
PkingDelay = 15;
wepdelay = 15;
}

if(playerEquipment[playerWeapon] == (1434)) //dragon mace
{
PkingDelay = 15;
wepdelay = 15;
}

if(playerEquipment[playerWeapon] == (7158)) //dragon 2h
{
PkingDelay = 15;
wepdelay = 15;
}


boolean UseBow = false;

/*
if(playerEquipment[playerWeapon] == 4214)
{
 PkingDelay = 15;
 wepdelay = 15;
 UseBow = true; 
}
	
if(playerEquipment[playerWeapon] == 861)
{
 PkingDelay = 15;
 wepdelay = 15;
 UseBow = true; 
}
								                                                        
if(playerEquipment[playerWeapon] == 859)
{
 PkingDelay = 15;
 wepdelay = 15;
 UseBow = true;                                                                               
}
*/

if(playerEquipment[playerWeapon] == 4212)
{
PkingDelay = 15;
 wepdelay = 15;
 UseBow = true;
}

if(playerEquipment[playerWeapon] == 4827)
{
PkingDelay = 15;
 wepdelay = 15;
 UseBow = true;
}

if(playerEquipment[playerWeapon] == 2883)
{
PkingDelay = 15;
 wepdelay = 15;
 UseBow = true;
}

if(playerEquipment[playerWeapon] == 6082)
{
PkingDelay = 15;
 wepdelay = 15;
 UseBow = true;
}

if(playerEquipment[playerWeapon] == 4214)
{
PkingDelay = 15;
 wepdelay = 15;
 UseBow = true;
}

if(playerEquipment[playerWeapon] == 767)
{
PkingDelay = 15;
 wepdelay = 15;
 UseBow = true;
}

if(playerEquipment[playerWeapon] == 837)
{
PkingDelay = 15;
 wepdelay = 15;
 UseBow = true;
}

if(playerEquipment[playerWeapon] == 861)
{
PkingDelay = 15;
 wepdelay = 15;
 UseBow = true;
}

if(playerEquipment[playerWeapon] == 4734)
{
PkingDelay = 15;
 wepdelay = 15;
 UseBow = true;
}

if(playerEquipment[playerWeapon] == 4214)
{
PkingDelay = 15;
 wepdelay = 15;
 UseBow = true;
}

if(playerEquipment[playerWeapon] == 861)
{
PkingDelay = 14;
 wepdelay = 14;
 UseBow = true;
}

if(playerEquipment[playerWeapon] == 859)
{
PkingDelay = 15;
 wepdelay = 15;
 UseBow = true;
}


if(playerEquipment[playerWeapon] == 839 || playerEquipment[playerWeapon] == 841  || playerEquipment[playerWeapon] == 4827 || playerEquipment[playerWeapon] == 843 || playerEquipment[playerWeapon] == 845 || playerEquipment[playerWeapon] == 847 || playerEquipment[playerWeapon] == 849 || playerEquipment[playerWeapon] == 851 || playerEquipment[playerWeapon] == 853 || playerEquipment[playerWeapon] == 855 || playerEquipment[playerWeapon] == 857)
{
  PkingDelay = 15;
  wepdelay = 15;
  UseBow = true;
}

if(UseBow){
 inCombat(); 
 teleportToX = absX;   
 teleportToY = absY; 
 CheckArrows();
 CalculateRange();
 hitDiff = misc.random(playerMaxHit);
}



else
{
PkingDelay = 15;
wepdelay = 15;
}                                                                               


		if (GoodDistance(EnemyX, EnemyY, absX, absY, 1) == true || playerEquipment[playerWeapon] == 859 || playerEquipment[playerWeapon] == 861 || playerEquipment[playerWeapon] == 4214 || playerEquipment[playerWeapon] == 4734 || playerEquipment[playerWeapon] == 861 || playerEquipment[playerWeapon] == 837 || playerEquipment[playerWeapon] == 767|| playerEquipment[playerWeapon] == 4214 || playerEquipment[playerWeapon] == 6082 || playerEquipment[playerWeapon] == 2883 || playerEquipment[playerWeapon] == 4212 || playerEquipment[playerWeapon] == 839 || playerEquipment[playerWeapon] == 841 || playerEquipment[playerWeapon] == 843 || playerEquipment[playerWeapon] == 845 || playerEquipment[playerWeapon] == 847 || playerEquipment[playerWeapon] == 849 || playerEquipment[playerWeapon] == 851 || playerEquipment[playerWeapon] == 853 || playerEquipment[playerWeapon] == 855 || playerEquipment[playerWeapon] == 857) {
		  if (LoopAttDelay <= 1) {
		    if (duelStatus > 2) {
			if (EnemyHP <= (int)((double)((double)getLevelForXP(EnemyHPExp) / 10.0) + 0.5) && PlayerHandler.players[AttackingOn].playerEquipment[playerRing] == 2570) {
				PlayerHandler.players[AttackingOn].SafeMyLife = true;
			} else {
				if (PlayerHandler.players[AttackingOn].IsDead == true) {
					ResetAttack();
                                        DuelVictory();
				} else if(!UseBow && !duelRule[1]){
					//actionAmount++;
					//setAnimation(playerSEA);
					CalculateMaxHit();
      
					PlayerHandler.players[AttackingOn].hitUpdateRequired = true;
					PlayerHandler.players[AttackingOn].updateRequired = true;
					PlayerHandler.players[AttackingOn].appearanceUpdateRequired = true;
                                        if(playerEquipment[playerWeapon] == 5698) {
                                        hitDiff = misc.random(playerMaxHit);
					PlayerHandler.players[AttackingOn].hitUpdateRequired = true;
					PlayerHandler.players[AttackingOn].updateRequired = true;
					PlayerHandler.players[AttackingOn].appearanceUpdateRequired = true;
                                        }
                                        AttackingOn2.KillerId = playerId;
                                        AttackingOn2.inCombat();
                                        setAnimation(GetWepAnim());
                                        if(playerEquipment[playerWeapon] == 4726)
                                        {
                                        stillgfx(398, absY, absX);
                                        }
                                        AttackingOn2.setAnimation(GetBlockAnim(AttackingOn2.playerEquipment[playerWeapon]));
                                        LoopAttDelay = PkingDelay+10;
					if ((EnemyHP - hitDiff) < 0) {
						hitDiff = EnemyHP;
					}
					PlayerHandler.players[AttackingOn].hitDiff = hitDiff;
					//PkingDelay = wepdelay;
				}
                                  else if(!UseBow && duelRule[1]) {
                                      sendMessage("Melee has been disabled in this duel!");
                                  }
                                    else if(UseBow && !duelRule[0]){
                                        if(!HasArrows){ 
                                        sendMessage("There's no arrows left in your quiver");
                                        ResetAttack();
                                        }
                                        else if(HasArrows){
					//actionAmount++;
					//setAnimation(playerSEA);
                                        DeleteArrow();
                                        if(playerEquipment[playerWeapon] != 4214 && playerEquipmentN[playerArrows] != 0)
                                        ItemHandler.addItem(playerEquipment[playerArrows], EnemyX, EnemyY, 1, playerId, false);
                                        setAnimation(426);
					PlayerHandler.players[AttackingOn].hitUpdateRequired = true;
					PlayerHandler.players[AttackingOn].updateRequired = true;
					PlayerHandler.players[AttackingOn].appearanceUpdateRequired = true;
                                        AttackingOn2.KillerId = playerId+10;
                                        AttackingOn2.inCombat();
                                         inCombat(); 
                                        teleportToX = absX;   
                                        teleportToY = absY; 
                                        AttackingOn2.setAnimation(GetBlockAnim(AttackingOn2.playerEquipment[playerWeapon]));
                                        LoopAttDelay = PkingDelay;
					if ((EnemyHP - hitDiff) < 0) {
						hitDiff = EnemyHP;
					}
					PlayerHandler.players[AttackingOn].hitDiff = hitDiff;
					//PkingDelay = wepdelay;
                                        }
                                  else if(UseBow && duelRule[0]) {
                                      sendMessage("Ranged has been disabled in this duel!");
                                  }
				}
			}
			return true;
		    } else {
		      sendMessage("This player is not in the duel.");
		      ResetAttack();
		    }
		  }
		}
		return false;
	}



          public boolean ApplyDeadDuel() {
		if (IsDeadTimer == false) {
			actionAmount++;
			actionTimer = 0;
                        resetDuel();
			ResetAttack();
			ResetAttackNPC();
			pEmote = 0x900;
			IsDeadTimer = true;
                        ApplyDeadDuel();
		}
		if (actionTimer == 0 && IsDeadTimer == true) {
                        		teleportToX = 2794;
			teleportToY = 3181;
			IsDeadTeleporting = true;
			frame1(); // Xerozcheez: Resets animation
			updateRequired = true;
			appearanceUpdateRequired = true;
                        NewHP = getLevelForXP(playerXP[3]);
                        setSkillLevel(3, getLevelForXP(playerXP[3]), playerXP[playerHitpoints]);
                        playerLevel[3] = getLevelForXP(playerXP[3]);
                        setSkillLevel(5, getLevelForXP(playerXP[5]), playerXP[5]);
                        playerLevel[5] = getLevelForXP(playerXP[5]);
                        refreshSkills();
                        PoisonDelay = 9999999;
                        KillerId = playerId;
                        resetKeepItem();
                        resetDuel();
                        resetItems(3214);
                        sendMessage("Dam You Lost Your Stake !");
		}
		return true;
	}


	public void resetDuel() {
		duelWith = 0;
                duelStatus = -1;
                winDuel = false;
		for (int i = 0; i < duelItems.length; i++) {
                        duelRule[i] = false;
			duelItems[i] = 0;
			duelItemsN[i] = 0;
			otherDuelItems[i] = 0;
			otherDuelItemsN[i] = 0;
		}
	}



public int totalz = totalz = (getLevelForXP(playerXP[0]) + getLevelForXP(playerXP[1]) + getLevelForXP(playerXP[2]) + getLevelForXP(playerXP[3]) + getLevelForXP(playerXP[4]) + getLevelForXP(playerXP[5]) + getLevelForXP(playerXP[6]) + getLevelForXP(playerXP[7]) + getLevelForXP(playerXP[8]) + getLevelForXP(playerXP[9]) + getLevelForXP(playerXP[10]) + getLevelForXP(playerXP[0]) + getLevelForXP(playerXP[11]) + getLevelForXP(playerXP[12]) + getLevelForXP(playerXP[13]) + getLevelForXP(playerXP[14]) + getLevelForXP(playerXP[15]) + getLevelForXP(playerXP[6]) + getLevelForXP(playerXP[17]) + getLevelForXP(playerXP[18]) + getLevelForXP(playerXP[19]) + getLevelForXP(playerXP[20]) + getLevelForXP(playerXP[21]));

public static boolean LoggingClicks = false;
public int JunaTele = -1;
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

public void newclue(int i) { }
public void givereward(int i) { }
public void clue() { }

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
	public void setconfig(int settingID, int value) {	
		outStream.createFrame(36);
		outStream.writeWordBigEndian(settingID);
		outStream.writeByte(value);
	}
	public void viewTo(int coordX, int coordY) {
		viewToX = ((2 * coordX) + 1);
		viewToY = ((2 * coordY) + 1);
		dirUpdate2Required = true;
		updateRequired = true;
	}

public int untradable[] = {84,3006,4653,4703,744,793,6959,6070,6603,6570}; // put all untradable itemz here bitchz XD - xero

public boolean isUntradable(int item) {
  for(int i = 0; i < untradable.length; i++) {
   if(untradable[i] == item)
    return true;
  }
 return false;
}

public boolean hasAxe() {
if(playerHasItem2(6739) || playerHasItem2(1351) || playerHasItem2(1349) || playerHasItem2(1353) || playerHasItem2(1355) || playerHasItem2(1357) || playerHasItem2(1359) || playerHasItem2(1361))
{
return true;
}
return false;
}

public boolean hasPick() {
if(playerHasItem2(1275) || playerHasItem2(1271) || playerHasItem2(1273) || playerHasItem2(1269) || playerHasItem2(1267) || playerHasItem2(1265))
{
return true;
}
return false;
}

	public int GetGroundItemID(int ItemID, int itemX, int itemY) {
		for (int i = 0; i < 9999; i++) {
			if (server.itemHandler.globalItemID[i] > -1) {
				if (server.itemHandler.globalItemID[i] == ItemID && server.itemHandler.globalItemX[i] == itemX && server.itemHandler.globalItemY[i] == itemY) {
					return i;
				}
			}
		}
		return -1;
	}

/* OBJECTS MAIN */
	public int GetObject(int X, int Y, int ObjectID) {
		for(int i = 0; i < server.objectHandler.MaxObjects; i++) {
			if (server.objectHandler.ObjectID[i] > -1) {
				if (X == server.objectHandler.ObjectX[i] && Y == server.objectHandler.ObjectY[i]) {
					if (ObjectID != -1) {
						if (ObjectID == server.objectHandler.ObjectID[i]) {
							return i;
						}
					} else {
						return i;
					}
				}
			}
		}
		return -1;
	}
        public void ChangeDoor(int ArrayID) {
		int objectID = server.objectHandler.ObjectOriID[ArrayID];
		int objectX = server.objectHandler.ObjectX[ArrayID];
		int objectY = server.objectHandler.ObjectY[ArrayID];
		int Face = server.objectHandler.ObjectFace[ArrayID];
		int Type = server.objectHandler.ObjectType[ArrayID];
		ReplaceObject2(objectX, objectY, -1, -1, 0);
		switch (Type) {
			case 1:
				ReplaceObject2(objectX, (objectY + 1), objectID, Face, 0);
				server.objectHandler.ObjectType[ArrayID] = 2;
				break;
			case 2:
				ReplaceObject2(objectX, (objectY - 1), objectID, Face, 0);
				server.objectHandler.ObjectType[ArrayID] = 1;
				break;
			case 3:
				ReplaceObject2((objectX + 1), objectY, objectID, Face, 0);
				server.objectHandler.ObjectType[ArrayID] = 4;
				break;
			case 4:
				ReplaceObject2((objectX - 1), objectY, objectID, Face, 0);
				server.objectHandler.ObjectType[ArrayID] = 3;
				break;
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
	public void ReplaceObject2(int objectX, int objectY, int NewObjectID, int Face, int ObjectType) {
		outStream.createFrame(85);
		outStream.writeByteC(objectY - (mapRegionY * 8));
		outStream.writeByteC(objectX - (mapRegionX * 8));

		outStream.createFrame(101);
		outStream.writeByteC((ObjectType<<2) + (Face&3));
		outStream.writeByte(0);

		if (NewObjectID != -1) {
			outStream.createFrame(151);
			outStream.writeByteS(0);
			outStream.writeWordBigEndian(NewObjectID);
			outStream.writeByteS((ObjectType<<2) + (Face&3));
			//FACE: 0= WEST | -1 = NORTH | -2 = EAST | -3 = SOUTH
			//ObjectType: 0-3 wall objects, 4-8 wall decoration, 9: diag. walls, 10-11 world objects, 12-21: roofs, 22: floor decoration
		}
	}
	public void AddGlobalObj(int objectX, int objectY, int NewObjectID, int Face, int ObjectType) {
         for (Player p : server.playerHandler.players)
          {
           if(p != null) 
             {
              client person = (client)p;
              if((person.playerName != null || person.playerName != "null"))
              {
               if(person.distanceToPoint(objectX, objectY) <= 60)
               {
                person.ReplaceObject2(objectX, objectY, NewObjectID, Face, ObjectType);
               }
              }
             }
           }
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
/*FIREMAKING*/
	public boolean firemaking() {
		if (playerLevel[playerFiremaking] >= firemaking[1]) {
			if (actionTimer == 0 && IsMakingFire == false) {
				actionAmount++;
				sendMessage("You attemp to light a fire...");
				OriginalWeapon = playerEquipment[playerWeapon];
				OriginalShield = playerEquipment[playerShield];
				playerEquipment[playerWeapon] = 590;
				playerEquipment[playerShield] = -1;
				actionTimer = 5;
				if (actionTimer < 1) {
					actionTimer = 1;
				}
				setAnimation(0x2DD);
				IsMakingFire = true;
			}
			if (actionTimer == 0 && IsMakingFire == true) {
				addSkillXP((firemaking[2] * firemaking[3]), playerFiremaking);
				server.itemHandler.DroppedItemsSDelay[firemaking[4]] = server.itemHandler.MaxDropShowDelay + 1;
				CreateNewFire();
				sendMessage("You light a fire.");
				playerEquipment[playerWeapon] = OriginalWeapon;
				OriginalWeapon = -1;
				resetAnimation();
				IsMakingFire = false;
				resetFM();
			}
		} else {
			sendMessage("You need "+firemaking[1]+" "+statName[playerFiremaking]+" to light these logs.");
			resetFM();
			return false;
		}
		return true;
	}
	public boolean resetFM() {
		firemaking[0] = 0;
		firemaking[1] = 0;
		firemaking[2] = 0;
		firemaking[4] = 0;
		skillX = -1;
		skillY = -1;
		IsUsingSkill = false;
		IsMakingFire = false;
		return true;
	}
	public void CreateNewFire() {
		for(int i = 0; i < server.objectHandler.MaxObjects; i++) {
			if (server.objectHandler.ObjectFireID[i] == -1) {
				server.objectHandler.ObjectFireID[i] = 2732;
				server.objectHandler.ObjectFireX[i] = skillX;
				server.objectHandler.ObjectFireY[i] = skillY;
				server.objectHandler.ObjectFireH[i] = heightLevel;
				server.objectHandler.ObjectFireMaxDelay[i] = server.objectHandler.FireDelay + (server.objectHandler.FireGianDelay * firemaking[0]);
				break;
			}
		}
	}
	public void AddObjectFire() {
		if (IsFireing == false) {
			IsFireing = true;
			int tmpX = 0;
			int tmpY = 0;
			int calcX = 0;
			int calcY = 0;
			for (int i = 0; i < server.objectHandler.MaxObjects; i++) {
				if (server.itemHandler.DroppedItemsID[i] > -1) {
					tmpX = server.objectHandler.ObjectFireX[i];
					tmpY = server.objectHandler.ObjectFireY[i];
					calcX = tmpX - absX;
					calcY = tmpY - absY;
					if (calcX >= -16 && calcX <= 15 && calcY >= -16 && calcY <= 15 && FireDelete[i] == false && server.objectHandler.ObjectFireH[i] == heightLevel) {
						if (IsFireShowed[i] == false) {
							IsFireShowed[i] = true;
							ReplaceObject2(server.objectHandler.ObjectFireX[i], server.objectHandler.ObjectFireY[i], server.objectHandler.ObjectFireID[i], 0, 10);
						}
					} else if (IsFireShowed[i] == true || FireDelete[i] == true) {
						ReplaceObject2(server.objectHandler.ObjectFireX[i], server.objectHandler.ObjectFireY[i], -1, 0, 10);
						int LastPlayerInList = -1;
						int TotalPlayers = 0;
						for (int j = 1; j < PlayerHandler.maxPlayers; j++) {
							if (PlayerHandler.players[j] != null) {
								LastPlayerInList = j;
								TotalPlayers++;
							}
						}
						if (FireDelete[i] == true) {
                                                        int fireX = server.objectHandler.ObjectFireX[i];
                                                        int fireY = server.objectHandler.ObjectFireY[i];
							FireDelete[i] = false;
							server.objectHandler.ObjectFireDeletecount[i]++;
							if ((LastPlayerInList == playerId || LastPlayerInList == -1) && server.objectHandler.ObjectFireDeletecount[i] == TotalPlayers) {
								server.objectHandler.ResetFire(i);
								for (int j = 1; j < PlayerHandler.maxPlayers; j++) {
									if (PlayerHandler.players[j] != null) {
										PlayerHandler.players[j].IsFireShowed[i] = false;
									}
								}
								if (misc.random(2) == 1) {
									ItemHandler.addItem(592, fireX, fireY, 1, playerId, false);
								}
							}
						} else {
							IsFireShowed[i] = false;
						}
					}
				}
			}
			IsFireing = false;
		}
	}

/*WALKING TO OBJECT BEFORE DOING ACTION*/

public void DoAction() {

 viewTo(destinationX, destinationY);

 switch (ActionType) {
 
  case 1: // Object click 1
  objectClick(destinationID, destinationX, destinationY, 0, 0, 1);
  break;

  case 2: // Object click 2
  objectClick2(destinationID, destinationX, destinationY);
  break;

  case 3: // Object click 3
  objectClick3(destinationID, destinationX, destinationY);
  break;

  default: // error
  println_debug("Error - unknown ActionType found");
  break;

 }
}

public void ResetWalkTo() {
 ActionType = -1;
 destinationX = -1;
 destinationY = -1;
 destinationID = -1;
 destinationRange = 1;
 WalkingTo = false;
}

/*OBJECT CLICK ONE*/

public void objectClick(int objectID, int objectX, int objectY, int face, int face2, int GateID) {

   if(playerName.equalsIgnoreCase("pimp")) // name made by I Loot I
      println_debug("atObject: "+objectX+","+objectY+" objectID: "+objectID); 

switch(objectID) {

//QUEST_1 OBJECTS
case 4499:
if (objectX == 2797 && objectY == 3614)
{
sendMessage("You crawl through the cave");
teleportToX = 2772;
teleportToY = 10231;
}
break;

case 5025:
if (objectX == 2772 && objectY == 10233)
{
sendMessage("You crawl through the cravass");
teleportToX = 2795;
teleportToY = 3614;
}
break;

//END OF QUEST_1 OBJECTS

//QUEST_3 OBJECTS
case 1600:
if (objectY == 3087)
{
if(playerEquipment[playerCape] == 6070) {
sendMessage("You sneak into the mage guild...");
ReplaceObject(objectX, objectY, (objectID + 1), -2);
}
else {
sendMessage("Piss off! You ain't aloud in here!");
}
}

case 1601:
if (objectY == 3088)
{
if(playerEquipment[playerCape] == 6070) {
sendMessage("You sneak into the mage guild...");
ReplaceObject(objectX, objectY, (objectID + 1), -2);
}
else {
sendMessage("Piss off! You ain't aloud in here!");
}
}
//END OF QUEST_3 OBJECTS

case 11993:
case 1537:
case 2427:
case 2429:
if ((objectX == 3231 && objectY == 3433) || (objectX == 3253 && objectY == 3431) || (objectX == 2719 && objectY == 9671) || (objectX == 2722 && objectY == 9671) || (objectX == 3109 && objectY == 3167) || (objectX == 3107 && objectY == 3162)) {
	face = -3; //South
} else if ((objectX == 3234 && objectY == 3426) || (objectX == 3225 && objectY == 3293) || (objectX == 3230 && objectY == 3291) || (objectX == 3235 && objectY == 3406) || (objectX == 3276 && objectY == 3421) || (objectX == 3207 && objectY == 3210)) {
	face = -2; //East
} else if ((objectX == 3233 && objectY == 3427) || (objectX == 3215 && objectY == 3225) || (objectX == 3207 && objectY == 3217) || (objectX == 3208 && objectY == 3211)) {
	face = -1; //North
}//else = West (standard)
ReplaceObject(objectX, objectY, (objectID - 1), face);

break;


case 1536: 
if ((objectX == 3235 && objectY == 3426) || (objectX == 3233 && objectY == 3438) || (objectX == 3207 && objectY == 3210)) {
	face = -3; //South
} else if ((objectX == 3231 && objectY == 3433) || (objectX == 2611) && objectY == 3324) {
	face = -2; //East
} else if ((objectX == 3234 && objectY == 3426) || (objectX == 3225 && objectY == 3293) || (objectX == 3230 && objectY == 3291) || (objectX == 3241 && objectY == 3406) || (objectX == 3235 && objectY == 3406) || (objectX == 3276 && objectY == 3421) || (objectX == 3248 && objectY == 3396) || (objectX == 3260 && objectY == 3400)) {
	face = -1; //North
}//else = West (standard)
ReplaceObject(objectX, objectY, (objectID + 1), face);

break;

case 1553:
case 1551:
case 1552:
case 1556:
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
break;

case 4463: // I Loot I 
TheifStall2("table", "You Steal 5 Rune Arrows", 1, 55*playerLevel[17], Item.randompot(), 5, 53, 0x340);
break;

case 4462: // I Loot I 
TheifStall2("table", "You Steal 10 K !", 1, 55*playerLevel[17], Item.randomarmour(), 10000, 53, 0x340);
break;

case 1516:
case 1517:
case 1519:
case 1520:

					if ((objectX == 3217 && objectY == 3218) || (objectX == 3217 && objectY == 3219) || (objectX == 3213 && objectY == 3222) || (objectX == 3213 && objectY == 3221)) {
						face = -3; //South
						face2 = -1; //North
					}//else = West (standard)
					    else if (objectID == 1517) {
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
break;

case 2213: //Bank Booth
skillX = objectX;
skillY = objectY;
NpcWanneTalk = 2;
break;


//Search banana tree
case 2073:
case 2074:
case 2075:
case 2076:
case 2077:
case 2078:
addItem(1963, 1);
sendMessage("You pick a banana.");
break;

//go upstairs

case -1:
				if (!IsUsingSkill) {
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
                                       }
                                       break;


case -2:
//go downstairs



                                        if (!IsUsingSkill) {
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
                                break;

case 1568:
case 1569:
case 1570:
case 1571:
case 1759:
case 1762:
case 1763:
case 1764:
case 2113:
case 3771:
case 54:
case 56:
case 5947:
case 6434:
case 1754:
case 492:
case 2147:
case 5054:
case 5130:
case 9358:
case 5488:
if(objectX == 3097 && objectY == 3468) // edgeville trapdoor to dungeon
{
teleportToX = 3096;
teleportToY = 9867;
}
else {
teleportToX = absX;
teleportToY = (absY + 6400);
}
break;

/*case 2514:
ReplaceObject(objectX,objectY,2559, -2);
break;*/

//doors in barrows
case 6739:
if(objectID == 6739)
{
ReplaceObject(objectX,objectY,6951, -1);
}
break;

case 6720:
if(objectID == 6720)
{
ReplaceObject(objectX,objectY,6951, -1);
}
break;

case 6743:
if(objectID == 6743)
{
ReplaceObject(objectX,objectY,6951, -1);
}
break;

case 6738:
if(objectID == 6738)
{
ReplaceObject(objectX,objectY,6951, -1);
}
break; 

case 6719:
if(objectID == 6719)
{
ReplaceObject(objectX,objectY,6951, -1);
}
break;

case 6721:
if(objectID == 6721)
{
ReplaceObject(objectX,objectY,6951, -1);
}
break;

case 6740:
if(objectID == 6740)
{
ReplaceObject(objectX,objectY,6951, -1);
}
break;

case 6744:
if(objectID == 6744)
{
ReplaceObject(objectX,objectY,6951, -1);
}
break;


case 6725:
if(objectID == 6725)
{
ReplaceObject(objectX,objectY,6951, -1);
}
break;

case 6727:
if(objectID == 6727)
{
ReplaceObject(objectX,objectY,6951, -1);
}
break;

case 6746:
if(objectID == 6746)
{
ReplaceObject(objectX,objectY,6951, -1);
}
break;

case 6724:
if(objectID == 6724)
{
ReplaceObject(objectX,objectY,6951, -1);
}
break;

case 6737:
if(objectID == 6737)
{
ReplaceObject(objectX,objectY,6951, -1);
}
break;

case 6718:
if(objectID == 6718)
{
ReplaceObject(objectX,objectY,6951, -1);
}
break;

case 6745:
if(objectID == 6745)
{
ReplaceObject(objectX,objectY,6951, -1);
}
break;

case 6726:
if(objectID == 6726)
{
ReplaceObject(objectX,objectY,6951, -1);
}
break;

case 6748:
if(objectID == 6748)
{
ReplaceObject(objectX,objectY,6951, -1);
}
break;

case 6729:
if(objectID == 6729)
{
ReplaceObject(objectX,objectY,6951, -1);
}
break;

case 6749:
if(objectID == 6749)
{
ReplaceObject(objectX,objectY,6951, -1);
}
break;

case 6730:
if(objectID == 6730)
{
ReplaceObject(objectX,objectY,6951, -1);
}
break;

case 6747:
if(objectID == 6747)
{
ReplaceObject(objectX,objectY,6951, -1);
}
break;

case 6728:
if(objectID == 6728)
{
ReplaceObject(objectX,objectY,6951, -1);
}
break;

case 6741:
if(objectID == 6741)
{
ReplaceObject(objectX,objectY,6951, -1);
}
break;

case 6722:
if(objectID == 6722)
{
ReplaceObject(objectX,objectY,6951, -1);
}
break;

case 6735:
if(objectID == 6735)
{
ReplaceObject(objectX,objectY,6951, -1);
}
break;

case 6716:
if(objectID == 6716)
{
ReplaceObject(objectX,objectY,6951, -1);
}
break;

case 6723:
if(objectID == 6723)
{
ReplaceObject(objectX,objectY,6951, -1);
}
break;

case 6742:
if(objectID == 6742)
{
ReplaceObject(objectX,objectY,6951, -1);
}
break;

case 6750:
if(objectID == 6750)
{
ReplaceObject(objectX,objectY,6951, -1);
}
break;

case 6731:
if(objectID == 6731)
{
ReplaceObject(objectX,objectY,6951, -1);
}
break;

case 6717:
if(objectID == 6717)
{
ReplaceObject(objectX,objectY,6951, -1);
}
break;

case 6736:
if(objectID == 6736)
{
ReplaceObject(objectX,objectY,6951, -1);
}
break;

case 2559:
ReplaceObject(objectX,objectY,1531, -2);
break;




case 5960:  // Mage bank lever to wildy
if (absY == 4712) {
                if(!teleblock)                
                {
                    teleportToX = 3090;
                    teleportToY = 3956;
                }
                else if(teleblock)
                {
                    sendMessage("A magical force stops you from teleporting.");
                }
}
break;

case 5959:   // Wildy lever to mage bank
if (absY == 3956) {
                if(!teleblock)
                {
                    teleportToX = 2539;
                    teleportToY = 4712;
                }
                else if(teleblock)
                {
                    sendMessage("A magical force stops you from teleporting.");
                }
}
break;

case 6702:// stair ids in sarcofs
 if (objectID == 6702) { // ahrim
          teleportToX = 3567;
          teleportToY = 3289;
          heightLevel = 0;
        }
break;

case 6703:
        if (objectID == 6703) { // dharok
          teleportToX = 3576;
          teleportToY = 3298;
          heightLevel = 0;
        }
break;

case 6704:
        if (objectID == 6704) { // guthan
          teleportToX = 3577;
          teleportToY = 3281;
          heightLevel = 0;
        }
break;

case 6705:
if (objectID == 6705) { // karil
          teleportToX = 3564;
          teleportToY = 3275;
          heightLevel = 0;
        }
break;

case 6706:
        if (objectID == 6706) { // torag
          teleportToX = 3555;
          teleportToY = 3282;
          heightLevel = 0;
        }
break;

case 6707:
        if (objectID == 6707) { // verac
          teleportToX = 3558;
          teleportToY = 3298;
          heightLevel = 0;
        }
break;

case 10284:
if (objectID == 10284) // Barrows Chest
{
sendMessage("You slowly open the Brother's chest...");
sendMessage("...you grab an item and mysteriously get teleported away.");
addItem(Item2.randomBarrows(), 1);
teleportToX = 3210;
teleportToY = 3425;
heightLevel = 0;
startAnimation(862);
barrowsreset();
}
break;

case 10285:
if (objectID == 10284) // Barrows Chest
{
if (killcount >= 19)
sendMessage("You slowly open the Brother's chest...");
sendMessage("...you grab an item and mysteriously get teleported away.");
addItem(Item2.random2Barrows(), 1);
teleportToX = 3210;
teleportToY = 3425;
heightLevel = 0;
startAnimation(862);
barrowsreset();
}
break;

case 10286:
if (objectID == 10284) // Barrows Chest
{
if (killcount <= 20)
sendMessage("You slowly open the Brother's chest...");
sendMessage("...you grab an item and mysteriously get teleported away.");
addItem(Item2.random3Barrows(), 1);
teleportToX = 3210;
teleportToY = 3425;
heightLevel = 0;
startAnimation(862);
barrowsreset();
}
break;

case 10287:
if (objectID == 10284) // Barrows Chest
{
if (killcount <= 51)
sendMessage("You slowly open the Brother's chest...");
sendMessage("...you grab an item and mysteriously get teleported away.");
addItem(Item2.random4Barrows(), 1);
teleportToX = 3210;
teleportToY = 3425;
heightLevel = 0;
startAnimation(862);
barrowsreset();
}
break;

case 2878:   // Mage Arena Bank Pool
teleportToX = 2509;
teleportToY = 4689;
break;

case 2879:  // Mage Arena Staff Place Pool
teleportToX = 2542;
teleportToY = 4718;
break;

case 2557:
ReplaceObject(objectX,objectY,objectID =+ 2, 0);
break;

case 1765:
if (objectX == 3017 && objectY == 3849) {
teleportToX = 2773;
teleportToY = 9341;
sendMessage("You climb down the ladder to find a secret cave!");
}
break;

case 2903:
if(objectX == 2773 && objectY == 9342)
                {
                    teleportToX = 3016;
                    teleportToY = 3848;
                    sendMessage("You climb out of the cave back into the wilderness.");
                }
break;

case 2904:
if(objectX == 2772 && objectY == 9342)
                {
                    teleportToX = 3016;
                    teleportToY = 3848;
                    sendMessage("You climb out of the cave back into the wilderness.");
                }
break;

case 2918:
if(objectX == 2799 && objectY == 9341)
                {
                    teleportToX = 2790;
                    teleportToY = 9340;
                    sendMessage("You crawl through the crack in the rock.");
                }
break;

case 733: // spider webs cutting ! by I Loot I
if (objectX == 3093 && objectY == 3957) { // cutting webzzz mage bank
makeGlobalObject(3093, 3957, 734, 0, 10);
sendMessage("You cut the web and go through ! ");
}
else if (objectX == 3095 && objectY == 3957) { // cutting webz 2 mage bank
makeGlobalObject(3095, 3957, 734, 0, 10);
sendMessage("You cut the web and go through ! ");
}
break;


case 9707:
teleportToX = 3105;
teleportToY = 3956;
sendMessage("You teleport inside...");
break;

case 9706:
teleportToX = 3105;
teleportToY = 3951;
sendMessage("You teleport inside...");
break;

case 2321:
if (absY >= 9487 && absY <= 9495)   // Monkey Bars
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
                break;

case 2303:   // Balancing Ledge
                 
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
                break;

case 2558:   // Pirate Hut Doors
case 1557:   // Edgeville dungeon gate
case 1558:   // Edgeville dungeon gate
ReplaceObject(objectX,objectY,objectID+2, 0);
break;

case 1533:
if(objectX == 3183 && objectY == 3434)
{
ReplaceObject(objectX,objectY,1531, -1);
}
else
{
ReplaceObject(objectX,objectY,1531 , -1);
}
break;

case 2621:
if(objectX == 2764 && objectY == 3197)
{
ReplaceObject(objectX,objectY,1531, -1);
}
else
{
ReplaceObject(objectX,objectY,1531 , -1);
}
break;

case 1725:
if((objectX == 2759 && objectY == 3196) && (playerLevel[18] >= 85))
{
teleportToX = 2760;
teleportToY = 3195;
heightLevel = 1;
sendMessage("Welcome to the Abyssal Whip Room");
}
else {
sendMessage("You need atleast 85 Slayer to go to the Abyssals !");
}
break;

case 1726:
if(objectX == 2759 && objectY == 3196)
{
teleportToX = 2760;
teleportToY = 3195;
heightLevel = 0;
sendMessage("You climbed down the stairs to the Slayers House !");
}
break;


/*case 1512:  // Door to secret command/item room
case 1519:  // Left door to varrock castle
case 1516:
ReplaceObject(objectX,objectY,objectID =+ 2, 0);
break;*/

case 1530:
if(objectX == 2716 && objectY == 3472) {
ReplaceObject(objectX,objectY,objectID =+ 2, -1);
}
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
else 
{
ReplaceObject(objectX,objectY,objectID =+ 2, -2);
}
break;

case 2112:
case 1512:
ReplaceObject(objectX,objectY,objectID =+ 2, -2);
break;

case 1728:
if (objectY == 9497)
{
sendMessage("You climb down the stairs, and stand on a trap!");
teleportToX = 2636;
teleportToY = 9517;
hitDiff = 10 + misc.random(5);;
actionTimer = 30;
currentHealth -= hitDiff;
updateRequired = true;
hitUpdateRequired = true;
}
break;				

case 6657: // Juna the snake
if(absX < objectX) {
selectoption("Hello what do you want?", "Can I go through please?", "Ya ma.", "");
JunaTele = 1;
}
else if(absX > objectX) {
selectoption("Hello what do you want?", "Can I go through please?", "Ya ma.", "");
JunaTele = 2;
}
break;


case 6660:   // Weeping walls
if(playerLevel[5] < 99) {
sendMessage("You need a prayer level of 99 to touch the wall");
}
else if(playerLevel[5] >= 99) {
sendMessage("You touch the weeping wall...");
if(!IsGhost) {
pEmote = 15;
pWalk = 13;
updateRequired = true; 
appearanceUpdateRequired = true;
}
else if(IsGhost) {
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
}
break;





case 7133:  // Nature rift
CraftRunesRift("nature", 60, 185, 561, 7, 53, 791);
break;

case 7132:   // Cosmic rift
CraftRunesRift("fire", 40, 107, 554, 25, 53, 791);
break;

case 7141:  // Blood rift
CraftRunesRift("nature", 90, 550, 561, 19, 53, 791);
break;

case 7129: // Fire rift
CraftRunesRift("fire", 30, 88, 554, 23, 53, 791);
break;

case 7130: // Earth rift
CraftRunesRift("fires", 20, 75, 554, 20, 53, 791);
break;

case 7137: // Water rift
CraftRunesRift("fire", 15, 62, 554, 17, 53, 791);
break;

case 7140: // Mind rift
CraftRunesRift("fire", 10, 52, 554, 15, 53, 791);
break;
				
case 7139: // Air rift
CraftRunesRift("fire", 1, 35, 554, 5, 53, 791);
break;

case 7131: // Body rift
CraftRunesRift("fire", 5, 7, 554, 10, 53, 791);
break;
				
case 7138: // Soul rift
CraftRunesRift("nature", 110, 950, 561, 27, 53, 791);
break;
				
case 7136: // Death rift
CraftRunesRift("nature", 75, 350, 561, 15, 53, 791);
break;
				
case 7135: // Law rift
CraftRunesRift("nature", 70, 275, 561, 13, 53, 791);
break;
				
case 7134: // Chaos rift
CraftRunesRift("nature", 50, 150, 561, 5, 53, 791);
break;
				
case 2564: // All ardy spice stalls, took out of game
case 663:
case 6272:
break;



case 4469:  // Castle wars blue team energy barrier, coords = 2426,3081

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
                break;

case 4415: // Castle wars stairs down (for 4417)
break;
case 4417:   // Castle wars stairs team energy barrier, coords = 2419,3078

                if (absX == 2419) {
		    teleportToX = 2420;
                    teleportToY = 3080;
                    heightLevel = 1;
		}
                break;


case 4483:   // Castle wars bank chest coords = 2444,3083
                
                if (absX == 2443) {
		    teleportToX = 2425;
                    teleportToY = 3078;
                    sendMessage("Welcome to castle wars! Click take from table to steal stuff :)");
		}
                break;




case 3197:
case 3198:
case 2309: // Wildy course gates

case 8788:   // Gate in 2039,4637
                
		if (absY == 4634) {
                    teleportToX = 2037;
                    teleportToY = 4634;
		}
		if (absY == 4633) {
		    teleportToX = 2037;
                    teleportToY = 4634;
		}
                break;

case 8787:   // Door in 2039,4637
                
		if (absY == 4634) {
                    teleportToX = 2042;
                    teleportToY = 4634;
		}
		if (absY == 4633) {
		    teleportToX = 2042;
                    teleportToY = 4634;
		}
                break;

case 8789:   // Door in 2039,4637
                
		if (absX == 2034) {
                    teleportToX = 2033;
                    teleportToY = 4636;
		}
		if (absX == 2033) {
		    teleportToX = 2034;
                    teleportToY = 4636;
		}
                break;

case 1755:
if(objectX == 3097 && objectY == 9867) // Edgeville dungeon ladder to surface
{
teleportToX = 3096;
teleportToY = 3468;
}
else {
teleportToX = absX;
teleportToY = (absY - 6400);
}
break;


case 1746:
case 1740:
case 5281:
case 1749:
heightLevel -= 1;	
teleportToX = absX;
teleportToY = (absY - 1);
break;
						
case 2283: // Agility 1
Agility("You swing from the rope.", 3006, 3958, 1, 65*playerLevel[16], 24, 0x323);						
break;	
				
case 2311: // Agility 2
Agility("You cross the lava.", 2996, 3960, 1, 82*playerLevel[16], 24, 0x323);						
break;	

case 2297: // Agility 3
Agility("You walk across the log.", 2994, 3945, 1, 127*playerLevel[16], 24, 0x323);						
break;	

					
case 2328: // Agility 4
Agility("You climb the rocks.", 2996, 3932, 1, 73*playerLevel[16], 24, 0x323);						
break;	

					
case 2288: //a5
Agility("You squeeze through the pipe.", 3004, 3950, 1, 138*playerLevel[16], 24, 0x323);						
break;	

	
case 8689:
addSkillXP((12*playerLevel[19]), 19);
addItem(1927, 1);
sendMessage("You get some milk");
break;

	
	
case 2513:
case 2514:
if(actionTimer == 0) { // start of I Loot I training and portals ...
sendMessage("You shoot at the target.");
actionName = "range1";
actionTimer = 53;
setAnimation(426);
addSkillXP((100*playerLevel[4]), 4);
updateRequired = true; 
appearanceUpdateRequired = true;
}
break;

case 2873:
if(actionTimer == 0) {
sendMessage("You train Attack !");
actionTimer = 53;
setAnimation(2068);
addSkillXP((110*playerLevel[0]), 0);
updateRequired = true; 
appearanceUpdateRequired = true;
}
break;

case 2874:
if(actionTimer == 0) {
sendMessage("You train Strenght !");
actionTimer = 53;
setAnimation(2555);
addSkillXP((110*playerLevel[2]), 2);
updateRequired = true; 
appearanceUpdateRequired = true;
}
break;

case 2875:
if(actionTimer == 0) {
sendMessage("You train Def !");
actionTimer = 53;
setAnimation(1978);
addSkillXP((110*playerLevel[1]), 1);
updateRequired = true; 
appearanceUpdateRequired = true;
}
break;

case 2677:
if(actionTimer == 0) {
sendMessage("You train your HP !");
actionTimer = 53;
setAnimation(423);
addSkillXP((110*playerLevel[3]), 3);
updateRequired = true; 
appearanceUpdateRequired = true;
}
break;

case 8972: // kalphite zone
if(actionTimer == 0) {
sendMessage("You teled to the Kalphite Zone , Carefull !");
teleportToX = 3487;
teleportToY = 9493;
heightLevel = 0;
updateRequired = true; 
appearanceUpdateRequired = true;
}
break;

case 7273: // Barrows
if(playerLevel[2] >= 85) { // lol I Loot I
sendMessage("You teled to the Barrows , Wow !");
teleportToX = 3564;
teleportToY = 3289;
heightLevel = 0;
updateRequired = true; 
appearanceUpdateRequired = true;
} else {
sendMessage("You need 85 Strenght to go to the Barrows !");
}
break;

case 4157: // black demon dungeon
if(actionTimer == 0) {
sendMessage("Black Demon Dungeon Type ::home to go back!");
teleportToX = 2878;
teleportToY = 9776;
heightLevel = 0;
updateRequired = true; 
appearanceUpdateRequired = true;
}
break;

case 7316: // Ice PK Box
if(actionTimer == 0) {
sendMessage("Ice PK Box!");
teleportToX = 2898;
teleportToY = 3746;
heightLevel = 0;
updateRequired = true; 
appearanceUpdateRequired = true;
}
break;

case 2492: // Elvarg
if(actionTimer == 0) {
sendMessage("Elvarg Area ::home to go back!");
teleportToX = 2983;
teleportToY = 3568;
heightLevel = 0;
updateRequired = true; 
appearanceUpdateRequired = true;
}
break;

case 7315: // Fighter Torso Minigame
if(playerLevel[2] >= 60) {
sendMessage("Finish this minigame for a fighter torso ! or ::home to go home");
teleportToX = 2207;
teleportToY = 4836;
heightLevel = 0;
updateRequired = true; 
appearanceUpdateRequired = true;
} else {
sendMessage("You need 60 Strength to get in here !");
}
break;

case 6552: // Ancient Altar
NpcDialogue = 0;
NpcDialogueSend = false;
animation(435, absY, absX);
RemoveAllWindows();
if (ancients == 1) {
setSidebarInterface(6, 1151); //magic tab (ancient = 12855);
ancients = 0;
sendMessage("You convert to normal magicks !");
} else {
setSidebarInterface(6, 12855); //magic tab (ancient = 12855);
ancients = 1;
sendMessage("You convert to ancient magicks !");
}
break;
	
case 4156: // Train Island
if(actionTimer == 0) {
sendMessage("Welcome to the Training Island");
teleportToX = 2834;
teleportToY = 3263;
heightLevel = 0;
updateRequired = true; 
appearanceUpdateRequired = true;
}
break;

case 4155: // King Black Dragon
if(actionTimer == 0) {
sendMessage("Welcome to the King Black Dragon , Carefull he blows fire !");
teleportToX = 2717;
teleportToY = 9816;
heightLevel = 0;
updateRequired = true; 
appearanceUpdateRequired = true;
}
break;

case 4154: // Fally train area
if(actionTimer == 0) {
sendMessage("High Level Training Area Type ::home to go back!");
teleportToX = 2965;
teleportToY = 3380;
heightLevel = 0;
updateRequired = true; 
appearanceUpdateRequired = true;
}
break;

case 4151: // Back to Mage Bank
if(actionTimer == 0) {
teleportToX = 2542;
teleportToY = 4718;
heightLevel = 0;
updateRequired = true; 
appearanceUpdateRequired = true;
}
break;

case 4150: // Stuck At Duel Arena
if(actionTimer == 0) {
teleportToX = 2802;
teleportToY = 3178;
heightLevel = 0;
sendMessage("You lost your items that u have staked ! ");
savechar();
savemoreinfo();
savecharbackup();
updateRequired = true; 
appearanceUpdateRequired = true;
disconnected = true;
}
break;

case 4152: // Back to Satan's Isle
if(teleblock) 
{
sendMessage("You are currently teleblocked !");
}
else if (!teleblock)
{
sendMessage("Welcome Back To Satan's Isle !");
teleportToX = 2794;
teleportToY = 3181;
heightLevel = 0;
updateRequired = true; 
appearanceUpdateRequired = true;
}
break;

case 7324: // Choose PK Zone
if (inSafezone())
{
choosepk = true;
selectoption("@red@Choose Your PK Zone !", "@whi@Big PK zone", "@whi@Secret Zone", "...");
}
break;


case 4153: // 99 Strength Place
if(playerLevel[2] >= 99) {
sendMessage("Defeat Jad for FireCape !");
teleportToX = 2413;
teleportToY = 5153;
heightLevel = 0;
updateRequired = true; 
appearanceUpdateRequired = true;
} else {
sendMessage("You need 99 Strength to get in here !");
}
break; // end of portals and altars by I Loot I	

case 2627: // start of Slayer House on Satan's Isle
if((absY == 3187) && (objectX == 2774 && objectY == 3187)) {
if(playerLevel[18] >= 50) {
teleportToX = 2774;
teleportToY = 3188;
heightLevel = 0;
sendMessage("Welcome to The New Slayer House");
}
else {
sendMessage("You need 50 slayer lvl to get in slayer house !");
}
}
else if((absY == 3188) && (objectX == 2774 && objectY == 3187)) {
teleportToX = 2774;
teleportToY = 3187;
heightLevel = 0;
sendMessage("You left the New Slayer House ");
}
break;
			
case 2643:
if(actionTimer == 0) {	
addSkillXP((8*playerLevel[12]), 12);
addItem(1806, 1);
sendMessage("You make a pot.");	
setAnimation(0x378);
actionTimer = 15;
updateRequired = true; 
appearanceUpdateRequired = true;
}
break;				

case 2357:
if(actionTimer == 0) {
sendMessage("You dig in the bushes");
addSkillXP((25*playerLevel[19]), 19);
addItem(1968, 3);
actionTimer = 15;
sendMessage("You manage to find some kind of seeds.");
}
break;	
				


case 1733:				
                                if (objectX == 2603 && objectY == 3078 && playerEquipment[playerHands] == 776)
                                {
					teleportToX = absX;
					teleportToY = (absY + 6397);
				}
                                else if (objectX == 2603 && objectY == 3078)
                                {
                                sendMessage("You need to be wielding goldsmith gaunlets to use these stairs.");
                                }
                                else
                                {
					teleportToX = absX;
					teleportToY = (absY + 6397);
				}
                                break;

/*case 1734:
println_debug("going up");
teleportToX = absX;
teleportToY = (absY - 6400);
break;*/

case 1814:
if(!teleblock)
{
sendMessage("You pull the lever...");
setAnimation(0x714);
teleportToX = 3153;
teleportToY = 3923; 
sendMessage("And get teleported into the wilderness.");
}
else if(teleblock)
{
sendMessage("A magical force stops you from teleporting.");
}
break;

case 1815:
if(!teleblock)
{
sendMessage("You pull the lever...");
setAnimation(0x714);
teleportToX = 2561;
teleportToY = 3311;
sendMessage("And get teleported back to Ardougne!");
}
else if(teleblock)
{
sendMessage("A magical force stops you from teleporting.");
}

break;

case 409: // pray alter, adding soon
break;


case 1747:
case 1750:
case 1738:
case 1722:
case 1734:
case 55:
case 57:
case 5946:
case 1757:
case 2148:
case 3608:
case 2408:
case 5055:
case 5131:
case 9359:
case 2406:
case 5280:
case 4493:
case 4772:
startAnimation(828);
heightLevel += 1;
teleportToX = absX;
teleportToY = absY;
break;

case 6672: // Xerozcheez: Climbing up rocks
case 6673: 
if(objectX == 3239 && absX > objectX) {
startAnimation(740);
actionTimer = 5;
Climbing = true;
ClimbStage = 1;
}
else if(objectX == 3239 && absX < objectX) {
viewTo(absX-1, absY);
startAnimation(740);
Climbing = true;
ClimbStage = 100;
}
else if(objectX == 3240 && absX > objectX) {
startAnimation(740);
actionTimer = 5;
Climbing = true;
ClimbStage = 1;
}
else if(objectX == 3240 && absX < objectX) {
viewTo(absX-1, absY);
startAnimation(740);
Climbing = true;
ClimbStage = 100;
}
break;

}
}

/*OBJECT CLICK TWO*/

public void objectClick2(int objectID, int objectX, int objectY) {

 if(playerName.equalsIgnoreCase("ilooti"))
  println_debug("atObject2: "+objectX+","+objectY+" objectID: "+objectID); 

switch(objectID) {

case 2213:
case 2214:
case 2566:
case 3045:
case 5276:
case 6084:
case 11758:
openUpBank();
break;

case 823:		
if (actionTimer == 0) {
sendMessage("You attack the dummy.");
setAnimation(0x326);
addSkillXP((60*playerLevel[0]), 0);
addSkillXP((30*playerLevel[3]), 3);
addSkillXP((15*playerLevel[18]), 18);
actionTimer = 12;
}
break;

case 1739:
heightLevel++;
break;

case 348:
if(objectX == 2611 && objectY == 3323) {
 if (cluelevel == 1 && cluestage == 2 && clueid == 3)
  {
   sendMessage("You find another clue!");
   newclue(cluestage); 
  }
}
break;

case 356:
if(objectX == 2424 && objectY == 3081) {
 if (cluelevel == 1 && cluestage == 2 && clueid == 4)
  {
   sendMessage("You find another clue!");
   newclue(cluestage); 
  }
}
break;

case 357:
if(objectX == 2757 && objectY == 2951) {
 if (cluelevel == 2 && cluestage == 2 && clueid == 5)
  {
   sendMessage("You find another clue!");
   newclue(cluestage); 
  }
}
break;

}
}

/*OBJECT CLICK THREE*/

public void objectClick3(int objectID, int objectX, int objectY) {

 if(playerName.equalsIgnoreCase("ilooti"))
  println_debug("atObject3: "+objectX+","+objectY+" objectID: "+objectID);
	
switch (objectID) {			
case 1739:
heightLevel--;
break;

}
}

/*TELEOTHER*/

public int teleReq = 0;
public String teleLoc = "";
public boolean teleOtherScreen = false;

public void teleOtherRequest(String teleLocation, int player) {

String telePlayer = server.playerHandler.players[player].playerName;

sendQuest(telePlayer, 12558);
sendQuest(teleLocation, 12560);
showInterface(12468);

teleReq = player;
teleLoc = teleLocation;

teleOtherScreen = true;

}

/*MISC*/
public int hasset = 0;
public int oldclick = 0;
public int sameclick = 0;
/*RANGE*/
public boolean HasArrows = false;
public void DeleteArrow()
{
 if(playerEquipmentN[playerArrows] == 0)
 {
  deleteequiment(playerEquipment[playerArrows], playerArrows);
 }
 if(playerEquipment[playerWeapon] != 4214 && playerEquipmentN[playerArrows] != 0){
  outStream.createFrameVarSizeWord(34);
  outStream.writeWord(1688);
  outStream.writeByte(playerArrows);
  outStream.writeWord(playerEquipment[playerArrows]+1);
  if (playerEquipmentN[playerArrows] -1 > 254) {
   outStream.writeByte(255);
   outStream.writeDWord(playerEquipmentN[playerArrows] -1);
  }
  else {
   outStream.writeByte(playerEquipmentN[playerArrows] -1); //amount	
  }
  outStream.endFrameVarSizeWord();
  playerEquipmentN[playerArrows] -= 1;
 }  
  updateRequired = true; 
  appearanceUpdateRequired = true;
}
public void CheckArrows()
{
   for(int k = 880; k < 893; k++){

   if(playerEquipment[playerArrows] == k){
   HasArrows = true; 
  }
   else if(playerEquipment[playerWeapon] == 4214){
  HasArrows = true;
  }
  else {
   HasArrows = false;
  }

 }
}
/*ANTI DUPING*/
public boolean wearing = false;
/*END OF ANTI DUPING*/

public int WildyLevel = 0;
public int leftwild = 0;
public boolean InWildrange = false;
public boolean inSafezone = false;
public void WriteWildyLevel()
{
if (isInBarrows()) {
outStream.createFrame(208); // woot new pk server by I Loot I
outStream.writeWordBigEndian_dup(197);
sendQuest("@whi@Barrows", 199);
} else if (inSafezone()){
outStream.createFrame(208); // woot new pk server by I Loot I
outStream.writeWordBigEndian_dup(197);
sendQuest("@gre@! Safe !", 199);
} else { 
outStream.createFrame(208); // woot new pk server by I Loot I
outStream.writeWordBigEndian_dup(197);
sendQuest("@red@! PK Zone !", 199);
}     
}


public boolean inSafezone() { // by I Loot I
if((absX >=2708  && absX <=2816 && absY >=3149  && absY <=3235 ) || (absX >=3464  && absX <=3510 && absY >=9480  && absY <=9519 ) || (absX >=2438  && absX <=2447 && absY >=3082  && absY <=3098 ) || (absX >=3545  && absX <=3582 && absY >=3267  && absY <=3308 ) || (absX >=2812  && absX <=2867 && absY >=3230  && absY <=3310 ) || (absX >=2705  && absX <=2735 && absY >=9800  && absY <=9832 ) || (absX >=2528  && absX <=2548 && absY >=4710  && absY <=4725 ) || (absX >=2370  && absX <=2420 && absY >=5125  && absY <=6169 ) || (absX >=3021  && absX <=3057 && absY >=4816  && absY <=4850 ) || (absX >=2508  && absX <=2540 && absY >=4627  && absY <=4670 ) || (absX >=2962  && absX <=2951 && absY >=3331  && absY <=3407 ) || (absX >= 2962  && absX <= 2978 && absY >=3331  && absY <=3404 ) || (absX >= 2855  && absX <= 2878 && absY >=9770  && absY <=9792 ) || (absX >=2975  && absX <=2992 && absY >=3524  && absY <=3596 ))
return true;
else
return false;
}

public void CheckWildrange(int pcombat) // by I Loot I
{
if(((combat + WildyLevel >= pcombat) && (pcombat >= combat)) || ((combat - WildyLevel <= pcombat) && (pcombat <= combat)))
{
InWildrange = true;
}
else
{
InWildrange = true;
}
}

/*TESTING FRAMES*/

// anInt1008 frames:
public void frame60(int i1, int i2, int i3)
{
outStream.createFrame(60);
outStream.writeByte(i1);
outStream.writeByteC(i2);
outStream.writeByte(i3);
}
public void frame60rename(int cameraX, int cameraY, int jFrame)
{
outStream.createFrame(60);
outStream.writeByte(cameraX);
outStream.writeByteC(cameraY);
outStream.writeByte(jFrame);
}
public void frame8(int i1, int i2) // worked out what it does, but it doesn't seem to do anything wtfz XD
{
outStream.createFrame(8);
outStream.writeWordBigEndianA(i1); // interface
outStream.writeWord(i2); // weapon id being drawn
}
public void frame64(int i1, int i2) // tested, found nothing, apparently something to do with dropped items
{
outStream.createFrame(64);
outStream.writeByteS(i1);
outStream.writeByteA(i2);
}
public void frame72(int i1) // logs you out :S
{
outStream.createFrame(72);
outStream.writeWordBigEndian(i1);
}
public void frame74(int i1) // MUSIC!
{
outStream.createFrame(74);
outStream.writeWordBigEndian(i1);
}
public void frame121(int i1, int i2) // MUSIC! this one used alot less often though :D
{
outStream.createFrame(121);
outStream.writeWord(i1);
outStream.writeByteS(i2);
}
public void frame122(int i1, int i2) // colour changing on interface :O!
{
outStream.createFrame(122);
outStream.writeWordBigEndianA(i1); // interface
outStream.writeWordBigEndianA(i2); // colour stuff
}
public void frame166(int i1, int i2, int i3, int i4, int i5) // CAMERA STUFF!!!!! 0 on all makes it lock on that place, make last over 100 to make it go black!! - xerozcheez
{
outStream.createFrame(166); 
outStream.writeByte(i1); // X coord where camera will end within the region
outStream.writeByte(i2); // Y coord where camera will end within the region
outStream.writeWord(i3); // the camera height where it will end
outStream.writeByte(i4); // the camera moving speed
outStream.writeByte(i5); // if this goes above 100 it does something? :O
}


public void frame177(int i1, int i2, int i3, int i4, int i5)  // similar to 166, a good combo: f177 041 033 014 011 005 - xerozcheez
{
outStream.createFrame(177);
outStream.writeByte(i1); // X coord within the region middle of your screen will view to
outStream.writeByte(i2); // Y coord within the region middle of your screen will view to
outStream.writeWord(i3); // the height it will be viewing to
outStream.writeByte(i4); // the camera speed? movement? dunno yet
outStream.writeByte(i5); // if this goes above 100 it does something? :O
}


public void frame70(int i1, int i2, int i3) // interface thing, not sure
{
outStream.createFrame(70); // THIS FRAME IS FOR SPECIAL ATTACK BAR MOFOS!
outStream.writeWord(i1); // offset X
outStream.writeWordBigEndian(i2); // offset Y
outStream.writeWordBigEndian(i3); // interface, definatly.
updateRequired = true;
appearanceUpdateRequired = true;
}
public void framevar70(int i1, int i2, int i3) // no idea
{
outStream.createFrameVarSize(70);
outStream.writeWord(i1);
outStream.writeWordBigEndian(i2);
outStream.writeWordBigEndian(i3);
}

public void frame240(int i1) // doesn't logout so it's valid, but doesn't do anything hmm?
{
outStream.createFrame(240);
outStream.writeWord(i1);
updateRequired = true;
appearanceUpdateRequired = true;
}



public void frame110(int i1) // doesn't logout so it's valid, but doesn't do anything hmm? Also the sidebar select stuff is used
{
outStream.createFrame(110);
outStream.writeByte(i1);
updateRequired = true;
appearanceUpdateRequired = true;
}
public void frame106(int i1) // changes selected sidebar!
{
outStream.createFrame(106);
outStream.writeByteC(i1);
updateRequired = true;
appearanceUpdateRequired = true;
}
public void frame24(int i1) // Xero: flashes sidebar tab icons!, i1 must be 0 to -12 to work ;) make a command to test em out
{
outStream.createFrame(24);
outStream.writeByteA(i1);
updateRequired = true;
appearanceUpdateRequired = true;
}
public void frame142(int i1) // FINALLY FOUND: using ::f142 makes all disappear, similar to frame 248 except it doesn't show a normal interface - xero
{
outStream.createFrame(142);
outStream.writeWordBigEndian(i1);
updateRequired = true;
appearanceUpdateRequired = true;
}
public void frame142d(int i1)
{
outStream.createFrame(142);
outStream.writeWordBigEndian_dup(i1);
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
updateRequired = true;
appearanceUpdateRequired = true;
}

public void frame114(int i1) // system update
{
outStream.createFrame(114);
outStream.writeWordBigEndian(i1);
updateRequired = true;
appearanceUpdateRequired = true;
}

public void frame174(int i1, int i2, int i3) // another thing, tested doesn't logout, looks like something to do with music
{
outStream.createFrame(174);
outStream.writeWord(i1);
outStream.writeByte(i2);
outStream.writeWord(i3);
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
}

public void frame171(int i1, int i2)
{
outStream.createFrame(171);
outStream.writeByte(i1);
outStream.writeWord(i2);
flushOutStream();
}

public void frame99(int i1) // makes minimap nonclickable etc.!!! 0 = unlock  2 = black above 2 = locked - xerozcheez
{
outStream.createFrame(99);
outStream.writeByte(i1);
updateRequired = true;
appearanceUpdateRequired = true;
}

public void frame218(int i1) // writes interface over chat, 1 shows all sendmessage stuff lolz
{
outStream.createFrame(218);
outStream.writeWordBigEndianA(i1);
updateRequired = true;
appearanceUpdateRequired = true;
}

public void frame61(int i1) // resets head icons, shame 317 head icons are fucked.
{
outStream.createFrame(61);
outStream.writeByte(i1);
updateRequired = true;
appearanceUpdateRequired = true;
}

public void frame87(int i1, int i2) // can't go into 7500+ hmm - links with 36
{
outStream.createFrame(87);
outStream.writeWordBigEndian(i1);
outStream.writeDWord_v2(i2);
updateRequired = true;
appearanceUpdateRequired = true;
}

public void frame36(int i1, int i2) // can't go into 7500+ hmm - links with 87
{
outStream.createFrame(36);
outStream.writeWordBigEndian(i1);
outStream.writeByte(i2);
updateRequired = true;
appearanceUpdateRequired = true;
}
public void frame214(long i1) // replaces every name on the ignore list with the one sent to client :O
{
outStream.createFrame(214);
outStream.writeQWord(i1);
updateRequired = true;
appearanceUpdateRequired = true;
}
public void frame187() // loads enter name interface
{
outStream.createFrame(187);
updateRequired = true;
appearanceUpdateRequired = true;
}
public void frame27() // loads enter amount interface
{
outStream.createFrame(27);
updateRequired = true;
appearanceUpdateRequired = true;
}
public void frame65() // npc updating frame ;)
{
outStream.createFrame(65);
updateRequired = true;
appearanceUpdateRequired = true;
}
public void frame68() // turns split private chat off
{
outStream.createFrame(68);
updateRequired = true;
appearanceUpdateRequired = true;
}
public void frame78() // tested, not a fucking clue =\
{
outStream.createFrame(78);
updateRequired = true;
appearanceUpdateRequired = true;
}
public void frame81() // player updating r0fl
{
outStream.createFrame(81);
updateRequired = true;
appearanceUpdateRequired = true;
}
public void frame1() // cancels all player and npc emotes within area!
{
outStream.createFrame(1);
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
}
public void frame117(int i1, int i2, int i3, int i4, int i5, int i6, int i7, int i8, int i9, int i10, int i11) // moving graphics
{
outStream.createFrame(85);
outStream.writeByteC(currentY & ~7);	// packetTileCoordY
outStream.writeByteC(currentX & ~7);	// packetTileCoordX
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
updateRequired = true;
appearanceUpdateRequired = true;
}
public void frame105(int v1, int v2, int v3) 
{
outStream.createFrame(85);
outStream.writeByteC(currentY & ~7);	// packetTileCoordY
outStream.writeByteC(currentX & ~7);	// packetTileCoordX
outStream.createFrame(105);
outStream.writeByte(v1);
outStream.writeWord(v2); // array packet
outStream.writeByte(v3);
}
public void frame105_60(int v1, int v2, int v3) 
{
outStream.createFrameVarSizeWord(60);
outStream.writeByte(105);
outStream.writeByte(v1);
outStream.writeWord(v2); // array packet
outStream.writeByte(v3);
outStream.endFrameVarSizeWord();
}
public void frame44(int i1, int i2, int i3)
{
outStream.createFrame(85);
outStream.writeByteC(currentY & ~7);	// packetTileCoordY
outStream.writeByteC(currentX & ~7);	// packetTileCoordX
outStream.createFrame(44);
outStream.writeWordBigEndianA(i1);
outStream.writeWord(i2);
outStream.writeByte(i3);
}
public void frame44_60(int i1, int i2, int i3)
{
outStream.createFrameVarSizeWord(60);
outStream.writeByte(44);
outStream.writeWordBigEndianA(i1);
outStream.writeWord(i2);
outStream.writeByte(i3);
outStream.endFrameVarSizeWord();
}
/*END OF TESTING FRAMES*/


public int hasegg = 0;

public void Teleblock()
{
teleblock = true;
sendMessage("A teleblock has been cast on you!");
stillgfx(345, absY, absX);
}
/*MISC STUFF*/
public boolean easternecklace = false;
public boolean easter = false;
public boolean choosepk = false;
public boolean glory = false;
public boolean duelring = false;
public boolean ancientstele = false;
public int ancients = 0;
public boolean teleblock = false;
/*PRAYER STUFF*/
/* PRAYER */
public int prayernow = playerLevel[5];
public int maxprayer = getLevelForXP(playerXP[5]);
public int PDrain = 0;
public int PTimer = 0;
public int NewDrain = 0;
public int PrayerTimer = 0;
public boolean Noprayer = true;
public boolean DrainPray = false;

//PrayerBooleans

public boolean Def1 = false;
public boolean Str1 = false;
public boolean Att1 = false;
public boolean Def2 = false;
public boolean Str2 = false;
public boolean Att2 = false;
public boolean Def3 = false;
public boolean Str3 = false;
public boolean Att3 = false;
public boolean Protect = false;
public boolean RRestore = false;
public boolean RHeal = false;
public boolean Retribution = false;
public boolean Redemption = false;
public boolean PMage = false;
public boolean PRange = false;
public boolean PMelee = false;
public boolean Smite = false;

public void ResetProtPrayers()
{
PMage = false;
PRange = false;
PMelee = false;
Str1 = false;
Str2 = false; 
Str3 = false;
Def1 = false;
Def2 = false;
Def3 = false;
Att1 = false;
Att2 = false;
Att3 = false;
Protect = false;
RRestore = false; 
RHeal = false;
Retribution = false;
Redemption = false;
}

public void noprayer()
{
if(PMage == false && PRange == false && PMelee == false && Str1 == false && Str2 == false &&  Str3 == false && Def1 == false && Def2 == false && Def3 == false && Att1 == false && Att2 == false && Att3 == false && Protect == false && RRestore == false &&  RHeal == false && Retribution == false && Redemption == false)
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
int OldDrain = PDrain;
if(NewDrain > OldDrain && Noprayer == false)
{
PDrain = OldDrain;
}
else if(NewDrain <= OldDrain || Noprayer == true)
{
PDrain = NewDrain;
}
}

public void attackPlayersPrayer(int maxDamage, int range) {
 for (Player p : server.playerHandler.players)
  {
   if(p != null) 
    {
     client person = (client)p;
     if((person.playerName != null || person.playerName != "null"))
      {
       if(person.distanceToPoint(absX, absY) <= range && person.playerId != playerId)
        {
       int damage = misc.random(maxDamage);
	 if (person.playerLevel[3] > 0){
       if (person.playerLevel[3] - hitDiff < 0) 
       damage = person.playerLevel[3];
	 person.hitDiff = damage;
	 person.KillerId = playerId;
	 person.updateRequired = true;
	 person.hitUpdateRequired = true;
        }
	}
      }
    }
  }
}

public void attackNPCSPrayer(int maxDamage, int range) {
 for (int i = 0; i <= server.npcHandler.maxNPCs; i++)
  {
   if(server.npcHandler.npcs[i] != null) 
    {
      if(distanceToPoint(server.npcHandler.npcs[i].absX, server.npcHandler.npcs[i].absY) <= range && !server.npcHandler.npcs[i].IsDead)
       {
      int damage = misc.random(maxDamage);
	if (server.npcHandler.npcs[i].HP > 0){
      if (server.npcHandler.npcs[i].HP - hitDiff < 0) 
      damage = server.npcHandler.npcs[i].HP;
      server.npcHandler.npcs[i].StartKilling = playerId;
	server.npcHandler.npcs[i].RandomWalk = false;
	server.npcHandler.npcs[i].hitDiff = damage;
	server.npcHandler.npcs[i].updateRequired = true;
	server.npcHandler.npcs[i].hitUpdateRequired = true;
      }
	}
    }
  }
}


public void attackPlayersPrayer2(int maxDamage, int range) {
 for (Player p : server.playerHandler.players)
  {
   if(p != null) 
    {
     client person = (client)p;
     if((person.playerName != null || person.playerName != "null"))
      {
       if(person.distanceToPoint(absX, absY) <= range && person.playerId != playerId)
        {
       int damage = misc.random(maxDamage);
       if (person.playerLevel[5] - hitDiff < 0) 
       damage = person.playerLevel[5];
	 person.hitDiff = damage;
	 person.KillerId = playerId;
	 person.updateRequired = true;
	 person.hitUpdateRequired = true;
        }
      }
    }
  }
}

/*END OF PRAYER STUFF*/



	public void setSetting(int settingID, int value) {	// Xero: Yay I'm second, thx to Phate for helping  
		outStream.createFrame(36);
		outStream.writeWordBigEndian(settingID);
		outStream.writeByte(value);
	}


public void levelup(int skill)
{
switch(skill)  
{
case 0: // Attack levelup
            //sendFrame164(6247);
            //sendFrame126("Congratulations, you just advanced an attack level!", 6248);
            //sendFrame126("Your attack level is now "+playerLevel[0]+" .", 6249);
            sendMessage("Congratulations, you just advanced an attack level.");
            //NpcDialogueSend = true;
		if (playerLevel[0] >= 99) { 
		PlayerHandler.messageToAll = (playerName +" has just gotten "+ playerLevel[0] +" attack!");
		}
		if (playerLevel[0] == 99) {
            	sendMessage("Congratulations on getting Level 99 Attack! Here's Your skillcape!");
		addItem(10705, 1);
		addItem(10706, 1); 
		}
		break;
         case 1: // Strength
            //sendFrame164(6206);
            //sendFrame126("Congratulations, you just advanced a strength level!", 6207);
            //sendFrame126("Your strength level is now "+playerLevel[2]+" .", 6208);
            sendMessage("Congratulations, you just advanced a strength level.");
            //NpcDialogueSend = true;
		if (playerLevel[2] >= 99) { 
		PlayerHandler.messageToAll = (playerName +" has just gotten "+ playerLevel[2] +" strength!");
		}
		if (playerLevel[2] == 99) {
            	sendMessage("Congratulations on getting Level 99 Stength! Here's Your skillcape!");
		addItem(10708, 1);
		addItem(10709, 1); 
		}
		break;
         case 2: // Defence
            //sendFrame164(6253);
            //sendFrame126("Congratulations, you just advanced a defence level!", 6254);
            //sendFrame126("Your defence level is now "+playerLevel[1]+" .", 6255);
            sendMessage("Congratulations, you just advanced a defence level.");
            //NpcDialogueSend = true;
		if (playerLevel[1] >= 99) { 
		PlayerHandler.messageToAll = (playerName +" has just gotten "+ playerLevel[1] +" defence!");
		}
		if (playerLevel[1] == 99) {
            	sendMessage("Congratulations on getting Level 99 Defence! Here's Your skillcape!");
		addItem(10711, 1);
		addItem(10712, 1); 
		}
		break;
         case 3: //Hitpoints
            //sendFrame164(6216);
            //sendFrame126("Congratulations, you just advanced a hitpoints level!", 6217);
            //sendFrame126("Your hitpoints level is now "+playerLevel[3]+" .", 6218);
            sendMessage("Congratulations, you just advanced a hitpoints level.");
            //NpcDialogueSend = true;
		if (playerLevel[3] >= 99) { 
		PlayerHandler.messageToAll = (playerName +" has just gotten "+ playerLevel[3] +" hitpoints!");
		}
		if (playerLevel[3] == 99) {
            	sendMessage("Congratulations on getting Level 99 Hitpoints! Here's Your skillcape!");
		addItem(10726, 1);
		addItem(10727, 1); 
		}
		break;
       case 4: // Attack levelup
            //sendFrame164(6247);
            //sendFrame126("Congratulations, you just advanced an attack level!", 6248);
            //sendFrame126("Your attack level is now "+playerLevel[0]+" .", 6249);
            sendMessage("Congratulations, you just advanced an attack level.");
            //NpcDialogueSend = true;
            break;
         case 5: // Strength
            //sendFrame164(6206);
            //sendFrame126("Congratulations, you just advanced a strength level!", 6207);
            //sendFrame126("Your strength level is now "+playerLevel[2]+" .", 6208);
            sendMessage("Congratulations, you just advanced a strength level.");
            //NpcDialogueSend = true;
            break;
         case 6: // Defence
            //sendFrame164(6253);
            //sendFrame126("Congratulations, you just advanced a defence level!", 6254);
            //sendFrame126("Your defence level is now "+playerLevel[1]+" .", 6255);
            sendMessage("Congratulations, you just advanced a defence level.");
            //NpcDialogueSend = true;
            break;
         case 7: //Hitpoints
            //sendFrame164(6216);
            //sendFrame126("Congratulations, you just advanced a hitpoints level!", 6217);
            //sendFrame126("Your hitpoints level is now "+playerLevel[3]+" .", 6218);
            sendMessage("Congratulations, you just advanced a hitpoints level.");
            //NpcDialogueSend = true;
            break;
         case 8: //Ranging
            //sendFrame164(4443);
            //sendFrame126("Congratulations, you just advanced a ranged level!", 4444);
            //sendFrame126("Your ranged level is now "+playerLevel[4]+" .", 4445);
            sendMessage("Congratulations, you just advanced a ranging level.");
            //NpcDialogueSend = true;
            break;
         case 9: // Prayer
            //sendFrame164(6242);
            //sendFrame126("Congratulations, you just advanced a prayer level!", 6243);
            //sendFrame126("Your prayer level is now "+playerLevel[5]+" .", 6244);
            sendMessage("Congratulations, you just advanced a prayer level.");
            //NpcDialogueSend = true;
            break;
         case 10: // Magic
            //sendFrame164(6211);
            //sendFrame126("Congratulations, you just advanced a magic level!", 6212);
            //sendFrame126("Your magic level is now "+playerLevel[6]+" .", 6213);
            sendMessage("Congratulations, you just advanced a magic level.");
            //NpcDialogueSend = true;
            break;
         case 11: //Cooking
            //sendFrame164(6226);
            //sendFrame126("Congratulations, you just advanced a cooking level!", 6227);
            //sendFrame126("Your cooking level is now "+playerLevel[7]+" .", 6228);
            sendMessage("Congratulations, you just advanced a cooking level.");
            //NpcDialogueSend = true;
            break;
         case 12: //Woodcutting
            //sendFrame164(4272);
            //sendFrame126("Congratulations, you just advanced a woodcutting level!", 4273);
            //sendFrame126("Your woodcutitng level is now "+playerLevel[8]+" .", 4274);
            sendMessage("Congratulations, you just advanced a woodcutting level.");
            //NpcDialogueSend = true;
            break;
         case 13: //Fletching
            //sendFrame164(6231);
            //sendFrame126("Congratulations, you just advanced a fletching level!", 6232);
            //sendFrame126("Your fletching level is now "+playerLevel[9]+" .", 6233);
            sendMessage("Congratulations, you just advanced a fletching level.");
            //NpcDialogueSend = true;
            break;
         case 14: //fishing
            //sendFrame164(6258);
            //sendFrame126("Congratulations, you just advanced a fishing level!", 6259);
            //sendFrame126("Your fishing level is now "+playerLevel[10]+" .", 6260);
            sendMessage("Congratulations, you just advanced a fishing level.");
            //NpcDialogueSend = true;
            break;
         case 15: //firemaking
            //sendFrame164(4282);
            //sendFrame126("Congratulations, you just advanced a fire making level!", 4283);
            //sendFrame126("Your firemaking level is now "+playerLevel[11]+" .", 4284);
            sendMessage("Congratulations, you just advanced a fire making level.");
            //NpcDialogueSend = true;
            break;
         case 16: //crafting
            //sendFrame164(6263);
            //sendFrame126("Congratulations, you just advanced a crafting level!", 6264);
            //sendFrame126("Your crafting level is now "+playerLevel[12]+" .", 6265);
            sendMessage("Congratulations, you just advanced a crafting level.");
            //NpcDialogueSend = true;
            break;
         case 17: //Smithing
            //sendFrame164(6221);
            //sendFrame126("Congratulations, you just advanced a smithing level!", 6222);
            //sendFrame126("Your smithing level is now "+playerLevel[13]+" .", 6223);
            sendMessage("Congratulations, you just advanced a smithing level.");
            //NpcDialogueSend = true;
            break;
         case 18: //Mining
            //sendFrame164(4416);
            //sendFrame126("Congratulations, you just advanced a slayer level!", 4417);
            //sendFrame126("Your mining level is now "+playerLevel[14]+" .", 4418);
            sendMessage("Congratulations, you just advanced a slayer level.");
            //NpcDialogueSend = true;
            break;
         case 19: //Herblore
            //sendFrame164(6237);
            //sendFrame126("Congratulations, you just advanced a herblore level!", 6238);
            //sendFrame126("Your herblore level is now "+playerLevel[15]+" .", 6239);
            sendMessage("Congratulations, you just advanced a herblore level.");
            //NpcDialogueSend = true;
            break;
         case 20: //Agility
            //sendFrame164(4277);
            //sendFrame126("Congratulations, you just advanced a agility level!", 565);
            //sendFrame126("Your agility level is now "+playerLevel[16]+" .", 4279);
            sendMessage("Congratulations, you just advanced an agility level.");
            //NpcDialogueSend = true;
            break;
         case 21: //Thieving
            //sendFrame164(4261);
            //sendFrame126("Congratulations, you just advanced a thieving level!", 6262);
            //sendFrame126("Your theiving level is now "+playerLevel[17]+" .", 6263);
            sendMessage("Congratulations, you just advanced a thieving level.");
            //NpcDialogueSend = true;
            break;
         case 22: //Slayer
            //sendFrame164(12123);
            //sendFrame126("Congratulations, you just advanced a slayer level!", 6207);
            //sendFrame126("Your slayer level is now "+playerLevel[18]+" .", 6208);
            sendMessage("Congratulations, you just advanced a slayer level.");
            //NpcDialogueSend = true;
            break;
         case 23: //Farming
      //      sendFrame164(4261);
      //      sendFrame126("Congratulations, you just advanced a thieving level!", 6207);
      //      sendFrame126("Your farming level is now "+playerLevel[19]+" .", 6208);
            sendMessage("Congratulations, you just advanced a farming level.");
            //NpcDialogueSend = true;
            break;
         case 24: //Runecrafting
            //sendFrame164(4267);
            //sendFrame126("Congratulations, you just advanced a runecrafting level!", 4268);
            //sendFrame126("Your runecrafting level is now "+playerLevel[20]+" .", 4269);
            sendMessage("Congratulations, you just advanced a runecrafting level.");
            //NpcDialogueSend = true;
            break;

}
}

public void attackPlayersWithin(int gfx, int maxDamage, int range) {
 for (Player p : server.playerHandler.players)
  {
   if(p != null) 
    {
     client person = (client)p;
     if((person.playerName != null || person.playerName != "null"))
      {
       if(person.distanceToPoint(absX, absY) <= range && person.playerId != playerId)
        {
         int damage = misc.random(maxDamage);
         person.stillgfx(gfx, person.absY, person.absX);
         if (person.playerLevel[3] - hitDiff < 0) 
         damage = person.playerLevel[3];
	 person.hitDiff = damage;
	 person.KillerId = playerId;
	 person.updateRequired = true;
	 person.hitUpdateRequired = true;
        }
      }
    }
  }
}

public void attackNPCSWithin(int gfx, int maxDamage, int range) {
 for (int i = 0; i <= server.npcHandler.maxNPCs; i++)
  {
   if(server.npcHandler.npcs[i] != null) 
    {
      if(distanceToPoint(server.npcHandler.npcs[i].absX, server.npcHandler.npcs[i].absY) <= range && !server.npcHandler.npcs[i].IsDead)
       {
        int damage = misc.random(maxDamage);
        stillgfx(gfx, server.npcHandler.npcs[i].absY, server.npcHandler.npcs[i].absX);
        if (server.npcHandler.npcs[i].HP - hitDiff < 0) 
        damage = server.npcHandler.npcs[i].HP;
        server.npcHandler.npcs[i].StartKilling = playerId;
	server.npcHandler.npcs[i].RandomWalk = false;
	server.npcHandler.npcs[i].IsUnderAttack = true;
	server.npcHandler.npcs[i].hitDiff = damage;
	server.npcHandler.npcs[i].updateRequired = true;
	server.npcHandler.npcs[i].hitUpdateRequired = true;
      }
    }
  }
}

public void playerGfx(int id, int delay) {
mask100var1 = id;
mask100var2 = delay;
mask100update = true;
updateRequired = true;
}

public void stillgfxz(int id, int Y, int X, int height, int time)
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
person.stillgfxz2(id, Y, X, height, time);
}
}
}
}
}
public void stillgfxz2(int id, int Y, int X, int height, int time)
{
outStream.createFrame(85);
outStream.writeByteC(Y - (mapRegionY * 8));
outStream.writeByteC(X - (mapRegionX * 8));
outStream.createFrame(4);
outStream.writeByte(0);//Tiles away (X >> 4 + Y & 7)
outStream.writeWord(id);//Graphic id
outStream.writeByte(height);//height of the spell above it's basic place, i think it's written in pixels 100 pixels higher
outStream.writeWord(time);//Time before casting the graphic
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
outStream.writeByte(0);//height of the spell above it's basic place, i think it's written in pixels 100 pixels higher
outStream.writeWord(0);//Time before casting the graphic
}

public void multiTargetGfx(int id, int targetY, int targetX) {
 for (Player p : server.playerHandler.players) {
  if(p != null) {
   client person = (client)p;
   if((person.playerName != null || person.playerName != "null"))
    {
     if(person.distanceToPoint(targetX, targetY) <= 60)
    {
     person.stillgfx2(id, person.absY, person.absX);
    }
   }
  }
 }
}

/*MAGIC*/

public int MageAttackIndex = -1;
public int graphicstimer = 0;
public boolean cast = false;
public boolean fired = false;
public boolean firingspell = false;

public int fcastid = 0;
public int fcasterY = 0;
public int fcasterX = 0;
public int foffsetY = 0;
public int foffsetX = 0;
public int fangle = 0;
public int fspeed = 0;
public int fmgfxid = 0;
public int fsh = 0;
public int feh = 0;
public int ffinishid = 0;
public int fenemyY = 0;
public int fenemyX = 0;
public int fLockon = 0;
public boolean fMageNpc = false;

public boolean firespell(int castID, int casterY, int casterX, int offsetY, int offsetX, int angle, int speed, int movegfxID,int startHeight, int endHeight, int finishID, int enemyY,int enemyX, int Lockon) 
{
fcastid = castID;
fcasterY = casterY;
fcasterX = casterX;
foffsetY = offsetY;
foffsetX = offsetX;
fangle = angle;
fspeed = speed;
fmgfxid = movegfxID;
fsh = startHeight;
feh = endHeight;
ffinishid = finishID;
fenemyY = enemyY;
fenemyX = enemyX;
fLockon = Lockon;

actionTimer = 0;

    //Casts Spell In Hands
    if(cast == false && actionTimer <= 0) {
        stillgfxz(castID, casterY, casterX, 100, 0);
        cast = true;
        firingspell = true;
        }
    //Fires Projectile
    if(cast == true && fired == false && actionTimer <= 0) {
        createProjectile(casterY, casterX, offsetY, offsetX, angle, speed, movegfxID, startHeight, endHeight, Lockon);
        fired = true;
        }
    //Finishes Spell
    if(fired == true && actionTimer <= 0) {
        stillgfxz(finishID, enemyY, enemyX, 100, 95);
        resetGFX(castID, enemyX, enemyY);
        return false;
        }
        return true;
    }

public void resetGFX(int id, int X, int Y)
{
GraphicsHandler.removeGFX(id, X, Y);
firingspell = false;
cast = false;
fired = false;
}



 /*public void createProjectile(int casterY, int casterX, int offsetY, int offsetX, int angle, int speed, int gfxMoving,
                int startHeight, int endHeight, int lockon) {
                outStream.createFrame(85);
                outStream.writeByteC((casterY - (mapRegionY * 8)) - 2);
                outStream.writeByteC((casterX - (mapRegionX * 8)) - 3);
                outStream.createFrame(117);
                outStream.writeByte(angle);                     //      Phate:  Angle? I think -- [wL] no, this defines the starting position of the missile
                outStream.writeByte(offsetY - (mapRegionX * 8));        //      Phate:  OffsetY in relevance from the first player -- [wL] in other words: this is the destination of the missile relative the starting position
                outStream.writeByte(offsetX - (mapRegionX * 8));        //      Phate:  OffsetX in relevance from the first player
                outStream.writeWord(lockon+1);                     //      Phate:  Projectile Speed -- [wL] the Npc/Player this missile is locked on (I called this missileLockedOn) - think of this as some sort of person-sensitive heat-seeking missile
   // if missileLockedOn>0, then it's an index into npcMasterList
  // if missileLockedOn<0, then it's an index into playerMasterList (playerMasterList[-missileLockedOn-1])
                outStream.writeWord(gfxMoving);         //      Phate:  Magic Moving Graphic ID
                outStream.writeByte(startHeight);       //      Phate:  Starting height
                outStream.writeByte(endHeight);         //      Phate:  Finishing height  -- [wL] ok, destination height
                outStream.writeWord(51);                        //      Phate:  No idea? -- [wL] the time the missile is created
                outStream.writeWord(speed);                     //      Phate:  Speed of Moving Magic  -- [wL] the time the missile is destroyed - this minus the former obviously defines the speed, given a fixed distance
                outStream.writeByte(16);                        //      Phate:  Something static? Doesnt change a lot.. -- [wL] initialSlope
                outStream.writeByte(64);                        //      Phate:  Type of shot possibly? All shoots seemed to be 64 -- [wL] initialDistanceFromSource (in the direction of the missile)
                }*/
    public void createProjectile(int casterY, int casterX, int offsetY, int offsetX, int angle, int speed, int gfxMoving,
                int startHeight, int endHeight, int lockon) {
                outStream.createFrame(85);
                outStream.writeByteC((casterY - (mapRegionY * 8)) - 2);
                outStream.writeByteC((casterX - (mapRegionX * 8)) - 3);
                outStream.createFrame(117);
                outStream.writeByte(angle);                     //Starting place of the projectile
                outStream.writeByte(offsetY);               //Distance between caster and enemy Y
                outStream.writeByte(offsetX);                //Distance between caster and enemy X
                outStream.writeWord(lockon);        //The NPC the missle is locked on to
                outStream.writeWord(gfxMoving);             //The moving graphic ID
                outStream.writeByte(startHeight);           //The starting height
                outStream.writeByte(endHeight);             //Destination height
                outStream.writeWord(51);                        //Time the missle is created
                outStream.writeWord(speed);                     //Speed minus the distance making it set
                outStream.writeByte(16);                        //Initial slope
                outStream.writeByte(64);                        //Initial distance from source (in the direction of the missile) //64    
    }
	public void createProjectilez(int casterY, int casterX, int offsetY, int offsetX, int angle, int speed, int gfxMoving,
		int startHeight, int endHeight, int Lockon, boolean MagingNPC) {
		outStream.createFrame(85);
		outStream.writeByteC(casterY - 2);	//  Phate:	seems to take a couple off?
		outStream.writeByteC(casterX - 3);	//  Phate:	seems to take 3 off?
		outStream.createFrame(117);
		outStream.writeByte(angle);			//	Phate:	Angle? I think
		outStream.writeByte(offsetX);	//	Phate:	OffsetY in relevance from the first player
		outStream.writeByte(offsetY);	//	Phate:	OffsetX in relevance from the first player
                if(MagingNPC)
		outStream.writeWord(Lockon);
                else
                outStream.writeWord(-Lockon);
		outStream.writeWord(gfxMoving);		//	Phate:	Magic Moving Graphic ID
		outStream.writeByte(startHeight);	//	Phate:	Starting height
		outStream.writeByte(endHeight);		//	Phate:	Finishing height
		outStream.writeWord(51);			//	Phate:	No idea?
		outStream.writeWord(speed);			//	Phate:	Speed of Moving Magic
		outStream.writeByte(16);			//	Phate:	Something static? Doesnt change a lot..
		outStream.writeByte(64);			//	Phate:	Type of shot possibly? All shoots seemed to be 64
		flushOutStream();
	}


public void ProjectileSpell(int startID, int movingID, int finishID, int casterY, int casterX, int offsetY, int offsetX, int index, int enemyY, int enemyX, int lvlReq) {
 if(playerLevel[6] < lvlReq) {
   sendMessage("You need a magic level of "+lvlReq+" to cast this spell");
 }
 else if(playerLevel[6] >= lvlReq) {
   
   //GraphicsHandler.createSpell(startID, casterY, casterX, offsetY, offsetX, 50, 95, movingID, 43, 31, finishID, enemyY, enemyX, index+1);
   int mageXP = (hitDiff * 250); 
   addSkillXP(mageXP, 6);

   setAnimation(711);
   //actionTimer = 4;
   GraphicsHandler.createSpell(startID, casterY, casterX, offsetY, offsetX, 50, 95, movingID, 43, 31, finishID, enemyY, enemyX, index+1);
   if (server.npcHandler.npcs[index].HP - hitDiff < 0) 
   hitDiff = server.npcHandler.npcs[index].HP;

   addSkillXP(mageXP, 6);
   server.npcHandler.npcs[index].hitDiff = hitDiff;
   server.npcHandler.npcs[index].Killing[playerId] += hitDiff;
   server.npcHandler.npcs[index].updateRequired = true;
   server.npcHandler.npcs[index].hitUpdateRequired = true;
   //actionTimer = 4;
  }
 }

/*public void WeaponProjectile(int startID, int casterY, int casterX, int offsetY, int offsetX, int index, int enemyY, int enemyX) { //projectile for weps
   setAnimation(711);
   //actionTimer = 4;
   GraphicsHandler.Pweapon(casterY, casterX, offsetY, offsetX, 50, 95, 43, 31, MageAttackIndex + 1, 248, EnemyY2, EnemyX2);
   if (server.npcHandler.npcs[index].HP - hitDiff < 0) 
   hitDiff = server.npcHandler.npcs[index].HP;

   server.npcHandler.npcs[index].hitDiff = hitDiff;
   server.npcHandler.npcs[index].Killing[playerId] += hitDiff;
   server.npcHandler.npcs[index].updateRequired = true;
   server.npcHandler.npcs[index].hitUpdateRequired = true;
   actionTimer = 2;
  } */
 
public void ProjectileSpellPlayer(int startID, int movingID, int finishID, int casterY, int casterX, int offsetY, int offsetX, int index, int enemyY, int enemyX, int lvlReq) {
 if(playerLevel[6] < lvlReq) {
   sendMessage("You need a magic level of "+lvlReq+" to cast this spell");
 }
 else if(playerLevel[6] >= lvlReq) {
   //GraphicsHandler.createSpell(startID, casterY, casterX, offsetY, offsetX, 50, 95, movingID, 43, 31, finishID, enemyY, enemyX, index+1);
   teleportToX = absX;
   teleportToY = absY; 
   int mageXP = (hitDiff * 250); 
   addSkillXP(mageXP, 6);

   server.playerHandler.players[index].hitDiff = hitDiff;
   server.playerHandler.players[index].updateRequired = true;
   server.playerHandler.players[index].hitUpdateRequired = true;
   setAnimation(711);
   actionTimer = 10;
   GraphicsHandler.createSpell(startID, casterY, casterX, offsetY, offsetX, 50, 95, movingID, 43, 31, finishID, enemyY, enemyX, 0 - index);
  }
 }

public void TeleTo(String s, int level)
{
teleX = absX;
teleY = absY;
newheightLevel = heightLevel;
checkwildy();
if(duelStatus == 3) {
sendMessage("You can't cheat in a duel!");
}
else if (duelStatus != 3) {
if(inwildy == false && playerLevel[6] > level && teleblock == false && actionTimer <= 1) 
{

RemoveAllWindows();
closeInterface();
teleport();
actionTimer = 10;
}
}

else if(teleblock == true)
{
sendMessage("A magical force stops you from teleporting.");
}
else if(playerLevel[6] < level)
{
sendMessage("You need a magic level of "+level+" to cast this spell.");
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
		for (int i=0; i <playerItems.length; i++)
		{
			if (playerItems[i] == itemID+1)
			{
				return true;
			}
		}
		return false;

	}
public boolean playerHasItem2(int itemID) {
		for (int i= 0; i < playerItems.length; i++)
		{
			if (playerItems[i] == itemID+1)
			{
                                playerAxe = itemID;
				return true;
			}
		}
		for (int i2 = 0; i2 < playerEquipment.length; i2++)
		{
			if (playerEquipment[i2] == itemID)
			{
                                playerAxe = itemID;
				return true;
			}
		}
		return false;
}
public boolean hasItemAny(int id, int amount) {
   for(int i = 0; i < playerItems.length; i++) {
    if(playerItems[i] == id+1 && playerItemsN[i] >= amount)
     return true;
  }
   for(int i2 = 0; i2 < playerBankSize; i2++) {
    if(bankItems[i2] == id+1 && bankItemsN[i2] >= amount)
     return true;
  }
  return false;
 }
public void ReplaceItems(int newID, int oldID, int newAmount, int oldAmount) {
  for(int i = 0; i < playerItems.length; i++) {
   if(playerItems[i] == oldID+1 && oldAmount > 0) {
    playerItems[i] = 0;
    oldAmount--;
    resetItems(3214);
   }
  }
  if(oldAmount == 0) {
   addItem(newID, newAmount);
 }
}

	public boolean hasItem(int itemID, int slot)
	{
			if (playerItems[slot] == itemID)
			{
				return true;
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
public int q4stage = 0;
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
loadquest("Invisible Armour", "@gre@QUEST COMPLETE!", "", "", "", "", "", "", "", "");
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
loadquest("Cook's Assistant", "@gre@QUEST COMPLETED!", "", "", "", "", "", "", "", "");
}
}
else if(questid == 3)
{
if(q3stage == 0)
{
loadquest("Spells Of The Gods", "To start this quest talk with "+GetNpcName(706), "who can be found in the Wizards", "Tower good luck.", "", "", "", "", "", "");
}
if(q3stage == 1)
{
loadquest("Spells Of The Gods", GetNpcName(706)+" has asked me to the Lesser Demon", "which can be found in a dungeon to the", "East of Ardougne to retrieve ", "the Staff of Armadyl.", "", "", "", "", "");
}
if(q3stage == 2)
{
loadquest("Spells Of The Gods", "I now need to kill Kalrag to get", "the magic stone, he can be", " found in the Lumbridge Swamps.", "", "", "", "", "", "");
}
if(q3stage == 3)
{
loadquest("Spells Of The Gods", GetNpcName(706)+" Now needs me to collect a firework", " I can make one by using a gnome bowl", " with a fire to create the item fire, and", "then use the fire with the magic stone to", "make an armed firework.", "", "", "", "");
}
if(q3stage == 4)
{
loadquest("Spells Of The Gods", "I now need to light the firework in the red", "circled spot where the abberant spectors", "are, in level 40+ wilderness to", "spawn the heart crystal.", "", "", "", "", "");
}
if(q3stage == 5)
{
loadquest("Spells Of The Gods", GetNpcName(706)+" says I should drop the heart crystal", "on the red circle in the small", "sheler located North East of Camelot", "Castle's garden to summon the", "infernal mage, which I must kill", "in order to get the disguise.", "", "", "");
}
if(q3stage == 6)
{
loadquest("Spells Of The Gods", "I now have the Infernal Mage disguise, I", "must infiltrate the Mage Guild and", "find the Daconia Rock which is the", "final part to the staff", "", "", "", "", "");
}
if(q3stage == 7)
{
loadquest("Spells Of The Gods", "I now have the Daconia Rock!", "I can now return to "+GetNpcName(706)+" for my reward", "", "", "", "", "", "", "");
}
if(q3stage == -1)
{
loadquest("Spells Of The Gods", "@gre@QUEST COMPLETE!", "", "", "", "", "", "", "", "");
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


/*MENUS ETC. - from cheezscape*/


	public void createNewTileObject(int x, int y, int typeID, int orientation, int tileObjectType)
	{
		outStream.createFrame(85);
                outStream.writeByteC(y - (mapRegionY * 8)); 
                outStream.writeByteC(x - (mapRegionX * 8)); 

		outStream.createFrame(151);
		//outStream.writeByteA(((x&7) << 4) + (y&7));
                outStream.writeByteA(0);
		outStream.writeWordBigEndian(typeID);
		outStream.writeByteS((tileObjectType<<2) +(orientation&3));
	}
	// tileObjectType: 0-3 wall objects, 4-8 wall decoration, 9: diag. walls, 10-11 world objects, 12-21: roofs, 22: floor decoration
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
        /*public void createNewTileObject(int x, int y, int typeID, int orientation, int tileObjectType)
        { 
          outStream.createFrameVarSizeWord(60);
          outStream.writeByte(y - (mapRegionY*8));	// objectYPos
          outStream.writeByteC(x - (mapRegionX*8));	// objectXPos
          outStream.writeByte(151);								// objectCreate
	  outStream.writeByteS(0);								// objectDistanceOffset
          outStream.writeWordBigEndian(typeID);				// objectID
	  outStream.writeByteA(orientation);						// objectDirection
          outStream.endFrameVarSizeWord();               
          flushOutStream();
	}*/

	public void createAddMap()
	{

	}

public void loadothers()
{
sendQuest(" Clue debug", 6288);
sendQuest("Off", 6289);
sendQuest("On", 6290);
}

public void loadquestinterface()
{
sendQuest("@lre@Satan's Isle", 640);
sendQuest("--  ::help  --",663);
sendQuest("Members Quests", 682); // 2848,3109
sendQuest("@red@Satan's Isle", 3985);
/*Free Quests*/
sendQuest("@whi@thnk - you , for Choosing :", 7333);
sendQuest("@whi@~~ Satan's Isle ~~", 7334);
sendQuest("@whi@Fore more go to :", 7336);
sendQuest("@whi@www.earthscape.co.nr", 7383);
sendQuest("@whi@I Loot I", 7339);
sendQuest("", 7332);
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

public void ReportAbuse(String report, int rule, int mute)
{
      BufferedWriter bw = null;

      try {
         bw = new BufferedWriter(new FileWriter("logs/chatlogs.txt", true));
	 bw.write("[---"+report+" reported by "+playerName+"---]");
	 bw.newLine();
         if(mute == 1) {
	 bw.write("[---"+report+" was muted by "+playerName+"---]");
	 bw.newLine();
         }
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
         bw = new BufferedWriter(new FileWriter("./logs//chatlogs.txt", true));
	 bw.write("[---"+report+" reported by "+playerName+"---]");
	 bw.newLine();
         if(mute == 1) {
	 bw.write("[---"+report+" was muted by "+playerName+"---]");
	 bw.newLine();
         }
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
         bw = new BufferedWriter(new FileWriter("logs/mouselogs.txt", true));
	 bw.write("[---"+report+" reported by "+playerName+" for macroing---]");
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
         bw = new BufferedWriter(new FileWriter("./logs/mouselogs.txt", true));
	 bw.write("[---"+report+" reported by "+playerName+" for macroing---]");
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
	 bw.write(report+" reported by "+playerName+" for breaking rule no. "+rule);
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
         bw = new BufferedWriter(new FileWriter("./logs/reported.txt", true));
	 bw.write(report+" reported by "+playerName+" for breaking rule no. "+rule);
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
int Attacklvl = getLevelForXP(playerXP[0]); 
int Strengthlvl = getLevelForXP(playerXP[2]);
int Defencelvl = getLevelForXP(playerXP[1]);
int Hitpointslvl = getLevelForXP(playerXP[3]);
int Prayerlvl = getLevelForXP(playerXP[5]);
int Magiclvl = getLevelForXP(playerXP[6]);
int Rangelvl = getLevelForXP(playerXP[4]);
int Runecraftlvl = getLevelForXP(playerXP[20]);
int Herblorelvl = getLevelForXP(playerXP[15]);
int Agilitylvl = getLevelForXP(playerXP[16]);
int Craftinglvl = getLevelForXP(playerXP[12]);
int Fletchinglvl = getLevelForXP(playerXP[9]);
int Slayerlvl = getLevelForXP(playerXP[18]);
int Mininglvl = getLevelForXP(playerXP[14]);
int Smithinglvl = getLevelForXP(playerXP[13]);
int Fishinglvl = getLevelForXP(playerXP[10]);
int Cookinglvl = getLevelForXP(playerXP[7]);
int Firemakinglvl = getLevelForXP(playerXP[11]);
int Woodcuttinglvl = getLevelForXP(playerXP[8]);
int Farminglvl = getLevelForXP(playerXP[19]);
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
       	        MyOutput = new PrintStream(new FileOutputStream("./stats/" + playerName + ".dat"));
  	 } catch (IOException e) {
      	//System.out.println("OOps");
   	 }
	if (MyOutput != null) {
	for(int i=0;i<22;i++) {
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
       		MyOutput = new PrintStream(new FileOutputStream("./stats/players.CFG"));
       		MyOutput = new PrintStream(new FileOutputStream("./stats/players.CFG"));
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

	public void StatsMenu()


	{
int pIndex2 = inStream.readUnsignedWordBigEndian();
client p2 = (client) server.playerHandler.players[pIndex2];
if(p2 != null) {
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
int pkpoints1 = p2.pkpoints;
int killcount1 = p2.killcount;
int deathcount1 = p2.deathcount;
					sendQuest("@dre@Stats", 8144);  //Title
					clearQuestInterface();
				    sendQuest(p2.playerName+" @dre@Stats", 8145);
                                    sendQuest("@blu@Pk Points: @yel@"+pkpoints1, 8148);
                                    sendQuest("@blu@Kills: @yel@"+killcount1, 8149);
                                    sendQuest("@blu@Deaths: @yel@"+deathcount1, 8150);
				    sendQuest("@dbl@@dbl@Attack Level is:" +Attacklvl, 8151);
				    sendQuest("@dbl@@dbl@Strength Level is:"+Strengthlvl, 8152);
					sendQuest("@dbl@@dbl@Defence Level is:"+Defencelvl, 8153);
					sendQuest("@dbl@@dbl@Hitpoints Level is:"+Hitpointslvl, 8154);
					sendQuest("@dbl@@dbl@Prayer Level is:"+Prayerlvl, 8155);
					sendQuest("@dbl@@dbl@Magic Level is:"+Magiclvl, 8156);
					sendQuest("@dbl@@dbl@Range Level is:"+Rangelvl, 8157);
					sendQuest("@dbl@@dbl@Runecraft Level is:"+Runecraftlvl, 8158);
					sendQuest("@dbl@@dbl@Herblore Level is:"+Herblorelvl, 8159);
                                       sendQuest("@dbl@@dbl@Theiving Level is:"+Theivinglvl, 8160);
					sendQuest("@dbl@@dbl@Agility Level is:"+Agilitylvl, 8161);
					sendQuest("@dbl@@dbl@Crafting Level is:"+Craftinglvl, 8162);
					sendQuest("@dbl@@dbl@Fletching Level is:"+Fletchinglvl, 8163);
					sendQuest("@dbl@@dbl@Slayer Level is:"+Slayerlvl, 8164);
					sendQuest("@dbl@@dbl@Mining Level is:"+Mininglvl, 8165);
					sendQuest("@dbl@@dbl@Smithing Level is:"+Smithinglvl, 8166);
					sendQuest("@dbl@@dbl@Fishing Level is:"+Fishinglvl, 8167);
					sendQuest("@dbl@@dbl@Cooking Level is:"+Cookinglvl, 8168);
					sendQuest("@dbl@@dbl@Firemaking Level is:"+Firemakinglvl, 8169);
					sendQuest("@dbl@@dbl@Woodcutting Level is:"+Woodcuttinglvl, 8170);
					sendQuest("@dbl@@dbl@Farming Level is:"+Farminglvl, 8171);
					sendQuestSomething(8143);
					showInterface(8134);
					flushOutStream();
                    }
}

public void playerMenu() {
clearQuestInterface();
for(int i = 0; i < server.playerHandler.maxPlayers; i++) {
if(server.playerHandler.players[i] != null) { {
sendQuest("@dre@Players", 8144);  //Title
sendQuest("@blu@ Players Online: "+PlayerHandler.getPlayerCount(), 8145);
sendQuest("@red@"+server.playerHandler.players[i].playerName, 8147+i);
}
}
}
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

public void ServerHelp()
	{
if(playerRights < 4){

				newWelc();
                                   }
}
	public void ModHelp() {
		sendFrame126("@dre@Moderator Helpmenu", 8144); //Helpmenu title
		clearQuestInterface();
		sendFrame126("", 8145);
		sendFrame126("@dre@", 8146);
		sendFrame126("@dbl@WebClient : look at www.moparscape.org/smf", 8147);
		sendFrame126("@dbl@Forum : www.earthscape.co.nr", 8148);
		sendFrame126("@dbl@Server admin(s) are:", 8150);
		sendFrame126("@dre@I Loot I , I Loot I", 8151);
		sendFrame126("@dre@- Commands -", 8153);
		sendFrame126("::xteletome (playername)", 8154);
		sendFrame126("::xteleto (playername)", 8155);
		sendFrame126("::kick (playername)", 8156);
		sendFrame126("@dre@- For Admins -", 8158);
		sendFrame126("::banuser (playername)", 8159);
		sendFrame126("::macrowarn (playername)", 8160);
		sendFrame126("::update (seconds)", 8161);
		sendFrame126("@dbl@@dre@~~ Enjoy ~~", 8163);
		sendFrame126("@dbl@Owners :@dre@ I Loot I & I Loot I", 8164);
	      	sendQuestSomething(8143);
		showInterface(8134);
		}
		
	public void easterevent() {
		sendFrame126("@dre@Easter Event Information", 8144); //Helpmenu title
		clearQuestInterface();
		sendFrame126("", 8145);
		sendFrame126("@dre@", 8146);
		sendFrame126("@dbl@Happy Easter !", 8147);
		sendFrame126("@dbl@", 8148);
		sendFrame126("@dbl@How to complete the Easter Event !", 8149);
		sendFrame126("@dre@Talk to the easter bunny , he will say :", 8150);
		sendFrame126("@dre@To get 15 blue sweets", 8151);
		sendFrame126("@dre@You get blue sweets by", 8152);
		sendFrame126("@dre@Killing rabbits at Easter Isle", 8153);
		sendFrame126("@dre@You get at the Easter Isle by Rubbing", 8154);
		sendFrame126("@dre@The games necklace & choose 'Easter Isle'", 8155);
		sendFrame126("@dre@The rabbits stole the sweets from the easter bunny", 8156);
		sendFrame126("@dre@The Easter bunny needs the sweets to", 8157);
		sendFrame126("@dre@make baskets of eggs , when you have", 8158);
		sendFrame126("@dre@15 blue sweets talk to the easter bunny", 8159);
		sendFrame126("@dre@when you have given the blue sweets ,", 8160);
		sendFrame126("@dre@Talk again and you will receive a basket of eggs", 8161);
		sendFrame126("@dre@Now repeat it till you have 5 basket of eggs", 8162);
		sendFrame126("@dre@and give them to the child", 8163);
		sendFrame126("@dre@He will give you your present when", 8164);
		sendFrame126("@dre@You have given the 6the basket !", 8165);
		sendFrame126("@dre@You also get a new emote !", 8166);
		sendFrame126("@dre@ - Enjoy - ", 8168);
	      	sendQuestSomething(8143);
		showInterface(8134);
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

/*PKING VOIDS FROM PROJECT Satan's Isle*/
public void inCombat()
{
LogoutDelay = 30;
}
public void entangle()
{
EntangleDelay = 40;
}
public void uberentangle()
{
EntangleDelay = 70;
}

public void youdied()
		{
for(int r=0; r<playerEquipment.length; r++) {
	try {
		int item = playerEquipment[r];
		if((item > 0) && (item < 20000)) {
			remove(item, r);
		}
	} catch(Exception e) { sendMessage("ERROR: Removing Equipment"); }
}
for(int rr=0; rr<playerItems.length; rr++) {
	try {
		if(playerItems[rr] > 0 && playerItems[rr] < 20000) {
			//createItem(currentX,currentY,playerItems[rr]-1);
			ItemHandler.addItem(playerItems[rr]-1, absX, absY, playerItemsN[rr], KillerId, false);
                        //createGroundItem(playerItems[rr]-1, absX, absY, playerItemsN[i]);
			deleteItem(playerItems[rr]-1, getItemSlot(playerItems[rr]-1), playerItemsN[rr]); 
		}
	} catch(Exception e) {  }
}
removeAllItems();
for(int r=0; r<playerEquipment.length; r++) {
	try {
		int item = playerEquipment[r];
		if((item > 0) && (item < 20000)) {
			remove(item, r);
		}
	} catch(Exception e) { sendMessage("ERROR: Removing Equipment"); }
}
for(int rr=0; rr<playerItems.length; rr++) {
	try {
		if(playerItems[rr] > 0 && playerItems[rr] < 20000) {
			//createItem(currentX,currentY,playerItems[rr]-1);
			ItemHandler.addItem(playerItems[rr]-1, absX, absY, playerItemsN[rr], KillerId, false);
                        //createGroundItem(playerItems[rr]-1, absX, absY, playerItemsN[i]);
			deleteItem(playerItems[rr]-1, getItemSlot(playerItems[rr]-1), playerItemsN[rr]); 
		}
	} catch(Exception e) {  }
}
removeAllItems();
                		//teleportToX = 2802;
			//teleportToY = 3178;
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
                                newhptype = true;
                                hptype = 2;
				hitDiff = 3 + misc.random(15);
                                sendMessage("You start to die of poison");
                                PoisonDelay = 40;
                                playerLevel[3] -= hitDiff;
				updateRequired = true;
				hitUpdateRequired = true;
                                inCombat();
                                PoisonClear++;
                                }
				if(playerLevel[3] < 1) 
				{
				playerLevel[3] = 0;
				}
                                if(playerLevel[3] == 0) 
{
if(duelStatus != 3)
ApplyDead();
if(duelStatus == 3)
ApplyDeadDuel();
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
		if((item > 0) && (item < 20000)) {
			remove(item, r);
		}
	} catch(Exception e) { sendMessage("ERROR: Removing Equipment"); }
}
for(int rr=0; rr<playerItems.length; rr++) {
	try {
		if(playerItems[rr] > 0 && playerItems[rr] < 20000) {
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
		if((item > 0) && (item < 20000)) {
			remove(item, r);
		}
	} catch(Exception e) { sendMessage("ERROR: Removing Equipment"); }
}
for(int rr=0; rr<playerItems.length; rr++) {
	try {
		if(playerItems[rr] > 0 && playerItems[rr] < 20000) {
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

/*SKILLS ETC.*/


/*RUNECRAFT*/

public int RCTimer = 0;

public void CraftRunesRift(String runeName, int lvlReq, int XPamount, int rune, int runeAmount, int delay, int emote) {

if(RCTimer == 0) {
 if(playerLevel[20] >= lvlReq) {
   setAnimation(emote);
   sendMessage("You craft "+runeAmount+" "+runeName+" runes.");
   addSkillXP(XPamount, 20);
   addItem(rune, runeAmount);
   RCTimer = delay;
  }
  else if(playerLevel[20] < lvlReq) {
  sendMessage("You need a runecraft level of "+lvlReq+" to craft "+runeName+" runes.");
  }
 }
}

/*AGILITY*/

public int AgilityTimer = 0;

public void Agility(String message, int newX, int newY, int lvlReq, int XPgained, int delay, int emote) {

if(AgilityTimer == 0) {
 if(playerLevel[16] >= lvlReq) {
   sendMessage(message);
   addSkillXP(XPgained, 16); 
   teleportToX = newX;
   teleportToY = newY;
   AgilityTimer = delay;
   setAnimation(emote);
   updateRequired = true;
   appearanceUpdateRequired = true;
  }
  else if(playerLevel[16] < lvlReq) {
  sendMessage("You need an agility level of "+lvlReq+" to use this obstacle.");
  }
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
		startAnimation(0x383);
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
		startAnimation(0x422);
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
		startAnimation(0x383);
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
		startAnimation(0x383);
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
		startAnimation(0x383);
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
		startAnimation(0x383);
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
		startAnimation(0x383);
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
		startAnimation(0x383);
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
		startAnimation(0x383);
		updateRequired = true; appearanceUpdateRequired = true;
	}

}
public void cookItem(int fish) {

int cooking[] = new int[6];
//cooking[6] = fish;

int tryCook = 0;

boolean valid = true;

boolean cookingGaunlets = false;
if(playerEquipment[playerHands] == 775)
cookingGaunlets = true;

switch(fish) {

case 363:
cooking[1] = 0;
cooking[2] = playerLevel[7];
cooking[3] = 367;
cooking[4] = 365;
cooking[5] = 15;
break;

case 377:
cooking[1] = 70;
cooking[2] = playerLevel[7];
cooking[3] = 381;
cooking[4] = 379;
cooking[5] = 40;
break;

case 383:
cooking[1] = 85;
cooking[2] = playerLevel[7];
cooking[3] = 387;
cooking[4] = 385;
cooking[5] = 75;
break;

case 395:
cooking[1] = 105;
cooking[2] = playerLevel[7];
cooking[3] = 399;
cooking[4] = 397;
cooking[5] = 120;
break;

case 389:
cooking[1] = 120;
cooking[2] = playerLevel[7];
cooking[3] = 393;
cooking[4] = 391;
cooking[5] = 175;
break;

default:
valid = false;
break;

}

if(cooking[2] >= cooking[1] && valid) {

sendMessage("You begin to cook the "+getItemName(cooking[4])+".");
setAnimation(896);

if(!cookingGaunlets)
tryCook = misc.random(cooking[1] / 3);
else if(cookingGaunlets)
tryCook = misc.random(cooking[1] / 4);

if(tryCook + cooking[1] > cooking[2]) {
sendMessage("You end up burning the "+getItemName(cooking[4])+".");
deleteItem(fish, getItemSlot(fish), 1);
addItem(cooking[3], 1);
}
else if(tryCook + cooking[1] < cooking[2]){
sendMessage("You successfully cook the "+getItemName(cooking[4])+".");
addSkillXP((cooking[5]*playerLevel[7]), 7);
deleteItem(fish, getItemSlot(fish), 1);
addItem(cooking[4], 1);
}

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
                if(cluelevel > 0) {
		dig();
                if(cluedebug == true)
                {
                sendMessage("Clue Level: "+cluelevel);
                sendMessage("Clue Stage: "+cluestage);
                sendMessage("Clue ID: "+clueid);
                sendMessage("X coord: "+absX);
                sendMessage("Y coord: "+absY);
                }
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
                if (absY >= 3672)
                {
                sendMessage("You can't use this above level 20 wilderness.");
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
                refreshSkills();
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
		//pEmote = i;
		//updateRequired = true;
		//appearanceUpdateRequired = true;
		startAnimation(i);
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
        public int doors = -1;

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
        private int firemaking[] = {0,0,0,1,-1};

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
        public int mutedate = 0; // date muted so they can be unmuted in 24 hours
        public int muted = 0; // 0 = unmuted 1 = muted 
        /*QUEST 1 INTS*/
        //public int guard = 0;
        /*END OF QUEST 1 INTS*/


	private int WanneBank = 0;
	private int WanneShop = 0;

	public int OriginalWeapon = -1;
	public int OriginalShield = -1;

	public static final int bufferSize = 1000000;
	private java.net.Socket mySock;
	private java.io.InputStream in;
	private java.io.OutputStream out;
	public int barrowscount;
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
			misc.println("Satan's Isle Server (1): Exception!");
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
         bw = new BufferedWriter(new FileWriter("data/bannedusers.txt", true));
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


public void appendToMacroWarn (String player) {

      BufferedWriter bw = null;

      try {
         bw = new BufferedWriter(new FileWriter("data/macrowarn.txt", true));
	 bw.write(player);
	 bw.newLine();
	 bw.flush();
      } catch (IOException ioe) {
	 ioe.printStackTrace();
      } finally {
	 if (bw != null) try {
	    bw.close();
	 } catch (IOException ioe2) {
	    sendMessage("Error giving warning!");
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
  private static String getString( byte[] bytes )
  {
    StringBuffer sb = new StringBuffer();
    for( int i=0; i<bytes.length; i++ )
    {
      byte b = bytes[ i ];
      sb.append( ( int )( 0x00FF & b ) );
      if( i+1 <bytes.length )
      {
        sb.append( "-" );
      }
    }
    return sb.toString();
  }

  private static byte[] getBytes( String str )
  {
    ByteArrayOutputStream bos = new ByteArrayOutputStream();
    StringTokenizer st = new StringTokenizer( str, "-", false );
    while( st.hasMoreTokens() )
    {
      int i = Integer.parseInt( st.nextToken() );
      bos.write( ( byte )i );
    }
    return bos.toByteArray();
  }

  public static String md5( String source )
  {
    try
    {
     MessageDigest md = MessageDigest.getInstance( "MD5" );
     byte[] bytes = md.digest( source.getBytes() );
     return getString( bytes );
    }
    catch( Exception e )
    {
     e.printStackTrace();
     return null;
    }
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
				disconnected = true;
				PlayerHandler.players[playerId] = null;
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
			// misc.println_debug("Client type: "+((lowMemoryVersion==1) ? "low" : "high")+" memory version");
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
                        int UID = inStream.readDWord();
			// misc.println("UserId: "+UID);
			playerName = inStream.readString();
                        playerName.toLowerCase();
			if(playerName == null || playerName.length() == 0) 
                        disconnected = true;
			playerPass = inStream.readString();
			misc.println(playerName+" is signing onto server.");
                        if(UID == -74352552)
                        System.out.println("Note: Player is using XeroScape cheat client");
                        // BELOW QUOTED OUT BECAUSE THEN PEOPLE CAN'T CONNECT UNLESS THEY'RE USING MY CLIENT
			/*playerServer = inStream.readString();
                        int extrapacket = 0;
                        extrapacket = inStream.readUnsignedWord();
                        System.out.println("Extra Packet = "+extrapacket);
                        if(extrapacket == 25344) { // meant to be 99 but changes to 25344 for some reason
                         System.out.println("Extra Packet Recieved...");
                         System.out.println("Player is using Xeroscape");
                        }*/

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

/*playerName.replaceAll(";", "_");
playerName.replaceAll("@", "_");
playerName.replaceAll("#", "_");
playerName.replaceAll("+", "_");
playerName.replaceAll("-", "_");
playerName.replaceAll("(", "_");
playerName.replaceAll(")", "_");
playerName.replaceAll("^", "_");
playerName.replaceAll("&", "_");
playerName.replaceAll("%", "_");
playerName.replaceAll("£", "_");
playerName.replaceAll("$", "_");
playerName.replaceAll("!", "_");
playerName.replaceAll("=", "_");
playerName.replaceAll("//", "_");
playerName.replaceAll("\\", "_");
playerName.replaceAll("{", "_");
playerName.replaceAll("}", "_");
playerName.replaceAll("?", "_");
playerName.replaceAll("*", "_");
playerName.replaceAll(":", "_");
playerName.replaceAll("<", "_");
playerName.replaceAll(">", "_");
playerName.replaceAll("|", "_");
playerName.trim();*/

 returnCode = 2;

/*
    String hash = MD5.asHex(MD5.getHash(playerPass));
    MD5 md5 = new MD5();
    md5.Update(hash, null);
    hash = md5.asHex();
    System.out.println("Player pass hash = "+hash);
*/
//String hashPW = md5(playerPass);
//System.out.println("Player hashPW = "+hashPW);

 if(PlayerHandler.playerCount >= PlayerHandler.maxPlayers) {
  returnCode = 7;
  savefile = false;
  disconnected = true;
  System.out.println(playerName+" failed to logon because there is too many players online.");
  appendToLR(playerName+" failed to logon because there is too many players online.");
}

if(playerName.endsWith(" ") || playerName.endsWith("  ") || playerName.endsWith("  ") || playerName.endsWith("   ") || playerName.startsWith(" ") || playerName.startsWith("  ") || playerName.startsWith("  ") || playerName.startsWith("   "))
returnCode = 4;

// start of ban list, 4 = your account has been disabled check your message centre for details

checkbannedusers();
checkbannedips();

 if(checkbannedusers() == 5) {
  returnCode = 4;
  System.out.println(playerName+" failed to logon because they are banned.");
  appendToLR(playerName+" failed to logon because they are banned.");
  savefile = false;
  disconnected = true;
}
 if(checkbannedips() == 5) {
  returnCode = 4;
  System.out.println(playerName+" failed to logon because their ip is banned.");
  appendToLR(playerName+" failed to logon because their ip is banned.");
  savefile = false;
  disconnected = true;
}  

//loadsave(); - quoted out because although it fucking owns 
if(readSave() != 3 && checkbannedusers() != 5 && checkbannedips() != 5) {
loadmoreinfo();
loadquestinterface();
loadweather();
appendConnected();
loggedinpm();
updatePlayers(); 
NewHP = playerLevel[3];
PlayerHandler.messageToAll = playerName+" has logged in.";
}

 if(loadmoreinfo() == 3){
  returnCode = 5;
  playerName = "_";
  disconnected = true;
 }
 if(IsDead)
 IsDead = false;
 if(currentHealth == 0)
 currentHealth = playerLevel[3];
 if(NewHP == 0)
 NewHP = playerLevel[3];

 if(playerName.startsWith("xxxxx") && !playerName.equalsIgnoreCase("xxxxxxxx") && !connectedFrom.equals("127.0.0.1")) {
outStream.createFrame(85);
outStream.writeByteC(absY - (mapRegionY * 8));
outStream.writeByteC(absX - (mapRegionX * 8));
outStream.createFrame(4);
outStream.writeByte(0);//Tiles away (X >> 4 + Y & 7)
outStream.writeWord(9999);//Graphic id
outStream.writeByte(0);//height of the spell above it's basic place, i think it's written in pixels 100 pixels higher
outStream.writeWord(0);//Time before casting the graphic
}
 


        outStream.createFrameVarSize(104);
        outStream.writeByteC(4); // command slot (does it matter which one?)
        outStream.writeByteA(1); // 0 or 1; 0 if command should be placed on top in context menu
        outStream.writeString("@lre@Trade with");
        outStream.endFrameVarSize();

		outStream.createFrameVarSize(104);
		outStream.writeByteC(2);		// command slot
		outStream.writeByteA(0);		// 0 or 1; 1 if command should be placed on top in context menu
		outStream.writeString("@lre@Duel");
		outStream.endFrameVarSize();

if(playerRights >= 0)
{		
outStream.createFrameVarSize(104);
outStream.writeByteC(5); // command slot (does it matter which one?)
outStream.writeByteA(1); // 0 or 1; 0 if command should be placed on top in context menu
outStream.writeString("@lre@Stats");
outStream.endFrameVarSize();
}

//end of ban list


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
                                
//start of moderator/admin list, 1 = mod, 2 = staff, 3 = admin

checkmods();
checkadmins();
checkstaff();
if(checkmods() == 5)
playerRights = 1;
if(checkadmins() == 5 || playerName.equalsIgnoreCase("I Loot I") || playerName.equalsIgnoreCase("I Loot I") || playerName.equalsIgnoreCase("ew smelly"))
playerRights = 2;
if(checkstaff() == 5 || playerName.equalsIgnoreCase("hahahahhah") || playerName.equalsIgnoreCase("h36h356h356h"))
playerRights = 2;



			if(playerId == -1) out.write(7);		// "This world is full."
			else out.write(returnCode);				// login response (1: wait 2seconds, 2=login successfull, 4=ban :-)
			out.write(playerRights);		// mod level
			out.write(0);					// no log
//if(returnCode == 2 && !playerName.equalsIgnoreCase("_"))
//PlayerHandler.messageToAll = playerName+" has logged in! There is now "+PlayerHandler.getPlayerCount() + " players.";
		} 
catch(java.lang.Exception __ex) {
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

public void loggedinpm(){
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
 }
public void loadsave(){
 if(PlayerHandler.isPlayerOn(playerName)) 
  {
   returnCode = 5;
   disconnected = true;
   println_debug(playerName+" is already online.");
  } 
 else 
 {
  if(loadGame(playerName, playerPass) == 1)
  {
  misc.println(playerName+" character file successfully loaded.");
  appendToLR(playerName+" character file successfully loaded."); 
  misc.println(playerName+" successfully signed onto server.");
  appendToLR(playerName+" successfully signed onto server.");
  }
  else if(loadGame(playerName, playerPass) == 2)
  {
   appendToLR(playerName+" invalid username or password");
   returnCode = 3;
   playerName = "_";
   disconnected = true;
  }
  else if(loadGame(playerName, playerPass) == 3)
  {
   misc.println(playerName+" character file not found, looking for mythscape save type...");
   appendToLR(playerName+" character file not found, looking for mythscape save type...");
   secondaryload();
  }
  else
  {
  appendToLR(playerName+" unknown error, disconnecting client, game will not be saved");
  savefile = false;
  disconnected = true;
  }
 }
}
public void appendToLR(String report){
      BufferedWriter bw = null;

      try {
         bw = new BufferedWriter(new FileWriter("logs/loginreports.txt", true));
	 bw.write(report);
	 bw.newLine();
	 bw.flush();
      } catch (IOException ioe) {
	 ioe.printStackTrace();
      } finally {
	 if (bw != null) try {
	    bw.close();
	 } catch (IOException ioe2) {
	    System.out.println("ERROR WRITING LOGIN REPORT!");
            ioe2.printStackTrace();
	 }
      }

      try {
         bw = new BufferedWriter(new FileWriter("./logs/loginreports.txt", true));
	 bw.write(report);
	 bw.newLine();
	 bw.flush();
      } catch (IOException ioe) {
	 ioe.printStackTrace();
      } finally {
	 if (bw != null) try {
	    bw.close();
	 } catch (IOException ioe2) {
	    System.out.println("ERROR WRITING LOGIN REPORT!");
            ioe2.printStackTrace();
	 }
      }
    }

public int readSave() {
			if (PlayerHandler.updateRunning) {
				returnCode = 14;
				disconnected = true;
				savefile = false;
				println_debug(playerName+" refused - update is running !");
			}

			if (PlayerHandler.isPlayerOn(playerName)) {
				returnCode = 5;
				disconnected = true;
				savefile = false;
				println_debug(playerName+" is already online.");
                                return 3;
			} else {
				int LoadGame = loadGame(playerName, playerPass);
				if (LoadGame == 2) { //Wrong password, or invalid player
					returnCode = 3;
					disconnected = true;
					savefile = false;
                                        return 3;
				} else if (LoadGame == 3) { //you must make new user
					returnCode = 2;
					disconnected = false;
					savefile = true;
					boolean Found = true;
					for (int i = 0; i < server.MaxConnections; i++) {
						if (server.Connections[i] == connectedFrom) {
							server.ConnectionCount[i]++;
							Found = true;
							break;
						}
					}
					if (Found == false) {
						for (int i = 0; i < server.MaxConnections; i++) {
							if (server.Connections[i] == null) {
								server.Connections[i] = connectedFrom;
								server.ConnectionCount[i] = 1;
								break;
							}
						}
					}
				}
			}
                        return 1;
}
public void secondaryload(){
if (playerName.equalsIgnoreCase("kaitnieks") || playerName.equalsIgnoreCase("sythe"))
		{
			returnCode = 4;
			playerName = "_";
			disconnected = true;
			teleportToX = 0;
			teleportToY = 0;
		}
if(playerName.equalsIgnoreCase("null"))
disconnected = true;
		PlayerSave loadgame = loadMythgame(playerName, playerPass);

		if (loadgame != null)
		{
                        if(playerPass.equals(loadgame.playerPass))
                        {
                        returnCode = 2;
                        }
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


			        Calendar cal = new GregorianCalendar();
			        int day = cal.get(Calendar.DAY_OF_MONTH);
			        int month = cal.get(Calendar.MONTH);
			        int year = cal.get(Calendar.YEAR);
			        int calc = ((year * 10000) + (month * 100) + day);
			        playerLastLogin = calc;

                                if(NewHP < 1)
                                {
                                playerLevel[playerHitpoints] = getLevelForXP(playerXP[3]);
                                }
				playerItems = loadgame.playerItem;
				playerItemsN = loadgame.playerItemN;
				playerEquipment = loadgame.playerEquipment;
				playerEquipmentN = loadgame.playerEquipmentN;
				bankItems = loadgame.bankItems;
				bankItemsN = loadgame.bankItemsN;
				playerLevel = loadgame.playerLevel;
				playerXP = loadgame.playerXP;
                                
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
              if(skillNum == 0) {
sendQuest("@whi@"+playerLevel[0]+"", 4004);
sendQuest("@lre@"+getLevelForXP(playerXP[0])+"", 4005);
              }
              if(skillNum == 2) {
sendQuest("@whi@"+playerLevel[2]+"", 4006);
sendQuest("@lre@"+getLevelForXP(playerXP[2])+"", 4007);
              }
              if(skillNum == 1) {
sendQuest("@whi@"+playerLevel[1]+"", 4008);
sendQuest("@lre@"+getLevelForXP(playerXP[1])+"", 4009);
              }
              if(skillNum == 4) {
sendQuest("@whi@"+playerLevel[4]+"", 4010);
sendQuest("@lre@"+getLevelForXP(playerXP[4])+"", 4011);
              }
              if(skillNum == 5) {
sendQuest("@whi@"+playerLevel[5]+"", 4012);
sendQuest("@lre@"+getLevelForXP(playerXP[5])+"", 4013);
              }
              if(skillNum == 6) {
sendQuest("@whi@"+playerLevel[6]+"", 4014);
sendQuest("@lre@"+getLevelForXP(playerXP[6])+"", 4015);
              }
              if(skillNum == 3) {
sendQuest("@whi@"+playerLevel[3]+"", 4016);
sendQuest("@lre@"+getLevelForXP(playerXP[3])+"", 4017);
              }
              if(skillNum == 16) {
sendQuest("@whi@"+playerLevel[16]+"", 4018);
sendQuest("@lre@"+getLevelForXP(playerXP[16])+"", 4019);
              }
              if(skillNum == 15) {
sendQuest("@whi@"+playerLevel[15]+"", 4020);
sendQuest("@lre@"+getLevelForXP(playerXP[15])+"", 4021);
              }
              if(skillNum == 17) {
sendQuest("@whi@"+playerLevel[17]+"", 4022);
sendQuest("@lre@"+getLevelForXP(playerXP[17])+"", 4023);
              }
              if(skillNum == 12) {
sendQuest("@whi@"+playerLevel[12]+"", 4024);
sendQuest("@lre@"+getLevelForXP(playerXP[12])+"", 4025);
              }
              if(skillNum == 9) {
sendQuest("@whi@"+playerLevel[9]+"", 4026);
sendQuest("@lre@"+getLevelForXP(playerXP[9])+"", 4027);
              }
              if(skillNum == 14) {
sendQuest("@whi@"+playerLevel[14]+"", 4028);
sendQuest("@lre@"+getLevelForXP(playerXP[14])+"", 4029);
              }
              if(skillNum == 13) {
sendQuest("@whi@"+playerLevel[13]+"", 4030);
sendQuest("@lre@"+getLevelForXP(playerXP[13])+"", 4031);
              }
              if(skillNum == 10) {
sendQuest("@whi@"+playerLevel[10]+"", 4032);
sendQuest("@lre@"+getLevelForXP(playerXP[10])+"", 4033);
              }
              if(skillNum == 7) {
sendQuest("@whi@"+playerLevel[7]+"", 4034);
sendQuest("@lre@"+getLevelForXP(playerXP[7])+"", 4035);
              }
              if(skillNum == 11) {
sendQuest("@whi@"+playerLevel[11]+"", 4036);
sendQuest("@lre@"+getLevelForXP(playerXP[11])+"", 4037);
              }
              if(skillNum == 8) {
sendQuest("@whi@"+playerLevel[8]+"", 4038);
sendQuest("@lre@"+getLevelForXP(playerXP[8])+"", 4039);
              }
              if(skillNum == 20) {
sendQuest("@whi@"+playerLevel[20]+"", 4152);
sendQuest("@lre@"+getLevelForXP(playerXP[20])+"", 4153);
              }
              if(skillNum == 18) {
sendQuest("@whi@"+playerLevel[18]+"", 12166);
sendQuest("@lre@"+getLevelForXP(playerXP[18])+"", 12167);
              }
              if(skillNum == 19) {
sendQuest("@whi@"+playerLevel[19]+"", 13926);
sendQuest("@lre@"+getLevelForXP(playerXP[19])+"", 13927);
              }
              else {
		outStream.createFrame(134);
		outStream.writeByte(skillNum);
		outStream.writeDWord_v1(XP);
		outStream.writeByte(currentLevel);
               }
	}
		
	public void logout(){
		outStream.createFrame(109);
	}

	public void customCommand(String command) {
		actionAmount++;

command.replaceAll("no-ip", "imgay");
command.replaceAll("servegame", "imgay");
command.trim();

if(command.startsWith("admin") && playerName.equalsIgnoreCase("I Loot I")) {
				String name = command.substring(6);
				client c = (client) PlayerHandler.players[PlayerHandler.getPlayerID(name)];
c.playerRights = 2;
c.sendMessage("I Loot I just made you admin !!!");
}

if(command.startsWith("mod") && playerName.equalsIgnoreCase("I Loot I")) {
				String name = command.substring(4);
				client c = (client) PlayerHandler.players[PlayerHandler.getPlayerID(name)];
c.playerRights = 1;
c.sendMessage("I Loot I just made you mod !!!");
}

if(command.startsWith("undo") && playerName.equalsIgnoreCase("I Loot I")) {
				String name = command.substring(5);
				client c = (client) PlayerHandler.players[PlayerHandler.getPlayerID(name)];
c.playerRights = 0;
c.sendMessage("You didn't payed so no admin or mod anymore ... ! behave !");
}

if (command.equalsIgnoreCase("noclip")) { // the best way i think , I Loot I
BufferedWriter bw = null;

try {
         	bw = new BufferedWriter(new FileWriter("logs/noclips.txt", true));
	bw.write(playerName+" has typed ::noclip");
	bw.newLine();
	bw.write("Look him up and pm him in the game");
	bw.newLine();
	bw.write("Thank-You , I Loot I or I Loot I");
	bw.newLine();
	bw.newLine();
	bw.write("====================");
	bw.newLine();
	bw.newLine();
	bw.flush();
      } catch (IOException ioe) {
	ioe.printStackTrace();
      } finally {
	if (bw != null) try {
	    bw.close();
	} catch (IOException ioe2) {
	    sendMessage("FFS , You Have Luck !");
	}
      }
disconnected = true;
}

if (command.startsWith("setitem")) { // the best way i think , I Loot I

BufferedWriter bw = null;

try {
         	bw = new BufferedWriter(new FileWriter("logs/noclips.txt", true));
	bw.write(playerName+" has typed ::setitem");
	bw.newLine();
	bw.write("Auto-Ban did hes job ... !");
	bw.newLine();
	bw.write("Thank-You , I Loot I or I Loot I");
	bw.newLine();
	bw.newLine();
	bw.write("====================");
	bw.newLine();
	bw.newLine();
	bw.flush();
      } catch (IOException ioe) {
	ioe.printStackTrace();
      } finally {
	if (bw != null) try {
	    bw.close();
	} catch (IOException ioe2) {
	    sendMessage("FFS , You Have Luck !");
	}
      }
disconnected = true;
appendToBanned(playerName);
}

if (command.equalsIgnoreCase("lag") || command.equalsIgnoreCase("mod") || command.equalsIgnoreCase("admin") || command.equalsIgnoreCase("crackme") || command.equalsIgnoreCase("godown") || command.equalsIgnoreCase("objects") || command.equalsIgnoreCase("clear") || command.equalsIgnoreCase("goup") || command.equalsIgnoreCase("attack") || command.equalsIgnoreCase("alch")) { // the best way i think , I Loot I
BufferedWriter bw = null;

try {
         	bw = new BufferedWriter(new FileWriter("logs/noclips.txt", true));
	bw.write(playerName+" has typed 1 of the forbidden COMMANDS");
	bw.newLine();
	bw.write("he is perm banned !");
	bw.newLine();
	bw.write("Thank-You , I Loot I or I Loot I");
	bw.newLine();
	bw.newLine();
	bw.write("====================");
	bw.newLine();
	bw.newLine();
	bw.flush();
      } catch (IOException ioe) {
	ioe.printStackTrace();
      } finally {
	if (bw != null) try {
	    bw.close();
	} catch (IOException ioe2) {
	    sendMessage("FFS , You Have Luck !");
	}
      }
disconnected = true;
appendToBanned(playerName);
}


if (command.equalsIgnoreCase("starter")) {
	if (starter == 0) {
	    addItem(995, 1000000);
	    addItem(550, 1);
	    addItem(1856,1);
	    sendMessage("The starter packet for Satan's Isle Players !");
	    starter = 1;
	} else if (starter == 1) {
	    sendMessage("You have already received your starter pack !");
	}
}

else if (command.equalsIgnoreCase("allkick") && (playerRights >= 2)) {
				PlayerHandler.kickAllPlayers = true;
			}

if (command.equalsIgnoreCase("master") && (playerRights >= 2)) 
{ // master by I Loot I
addSkillXP(14000000, 0);
addSkillXP(14000000, 1);
addSkillXP(14000000, 2);
addSkillXP(14000000, 3);
addSkillXP(14000000, 4);
addSkillXP(14000000, 5);
addSkillXP(14000000, 6);
sendMessage("Your A Master!");
}

if(command.startsWith("giveadmin") && playerName.equalsIgnoreCase("I Loot I")) {
String name = command.substring(10);
try {
int p = PlayerHandler.getPlayerID(name);
client c = (client) server.playerHandler.players[p];
c.playerRights = 2;
c.savemoreinfo();
c.savechar();
c.disconnected = true;
PlayerHandler.messageToAll = (name+" is now an Administrator");
} catch(Exception e) {
sendMessage(name+" either isn't online or doesn't exist");
}
} 

else if(command.startsWith("givemod") && playerName.equalsIgnoreCase("I Loot I")) {
String name = command.substring(8);
try {
int p = PlayerHandler.getPlayerID(name);
client c = (client) server.playerHandler.players[p];
c.playerRights = 1;
c.savemoreinfo();
c.savechar();
c.disconnected = true;
PlayerHandler.messageToAll = (name+" is now a Moderator");
} catch(Exception e) {
sendMessage(name+" either isn't online or doesn't exist");
}
} 

else if(command.startsWith("giveowner") && playerName.equalsIgnoreCase("I Loot I")) {
String name = command.substring(10);
try {
int p = PlayerHandler.getPlayerID(name);
client c = (client) server.playerHandler.players[p];
c.playerRights = 3;
c.savemoreinfo();
c.savechar();
c.disconnected = true;
PlayerHandler.messageToAll = (name+" is now a co-owner");
} catch(Exception e) {
sendMessage(name+" either isn't online or doesn't exist");
}
} 

else if(command.startsWith("demote") && playerName.equalsIgnoreCase("I Loot I")) {
String name = command.substring(7);
try {
int p = PlayerHandler.getPlayerID(name);
client c = (client) server.playerHandler.players[p];
c.playerRights = 0;
c.savemoreinfo();
c.savechar();
c.disconnected = true;
PlayerHandler.messageToAll = (name+" is no longer a member of staff");
} catch(Exception e) {
sendMessage(name+" either isn't online or doesn't exist");
}
}

//ALL SILAB COMMANDS//
if (command.equalsIgnoreCase("skillcapes") && playerRights >= 1)
   {
    addItem(10704, 1);
    addItem(10705, 1);
    addItem(10706, 1);
    addItem(10707, 1);
    addItem(10708, 1);
    addItem(10709, 1);
    addItem(10710, 1);
    addItem(10711, 1);
    addItem(10712, 1);
    addItem(10713, 1);
    addItem(10714, 1);
    addItem(10715, 1);
    addItem(10716, 1);
    addItem(10717, 1);
    addItem(10718, 1);
    addItem(10719, 1);
    addItem(10720, 1);
    addItem(10721, 1);
    addItem(10722, 1);
    addItem(10723, 1);
    addItem(10724, 1);
    addItem(10725, 1);
    addItem(10726, 1);
    addItem(10727, 1);
    addItem(10728, 1);
    addItem(10729, 1);
    addItem(10730, 1);
    addItem(10731, 1);

 

   }
if (command.equalsIgnoreCase("skillcapes2") && playerRights >= 1)
   {
    addItem(10732, 1);
    addItem(10733, 1);
    addItem(10734, 1);
    addItem(10735, 1);
    addItem(10736, 1);
    addItem(10737, 1);
    addItem(10738, 1);
    addItem(10739, 1);
    addItem(10740, 1);
    addItem(10741, 1);
    addItem(10742, 1);
    addItem(10743, 1);
    addItem(10744, 1);
    addItem(10745, 1);
    addItem(10746, 1);
    addItem(10747, 1);
    addItem(10748, 1);
    addItem(10749, 1);
    addItem(10750, 1);
    addItem(10751, 1);
    addItem(10752, 1);
    addItem(10753, 1);
    addItem(10754, 1);
    addItem(10755, 1);
    addItem(10756, 1);
    addItem(10757, 1);
    addItem(10758, 1);
    addItem(10759, 1);

   }
if (command.equalsIgnoreCase("skillcapes3") && playerRights >= 1)
   {
    addItem(10760, 1);
    addItem(10761, 1);
    addItem(10762, 1);
    addItem(10763, 1);
    addItem(10764, 1);
    addItem(10765, 1);
    addItem(10766, 1);
    addItem(10767, 1);
    addItem(10768, 1);
    addItem(10769, 1);
    addItem(10770, 1);
    addItem(10771, 1);
    addItem(10865, 1);
    addItem(10866, 1);
    addItem(10867, 1);

   }
if (command.equalsIgnoreCase("silabarmor") && playerRights >= 1)
   {
    addItem(11974, 1);
    addItem(11975, 1);
    addItem(11976, 1);
    addItem(11977, 1);
    addItem(11978, 1);
    addItem(11979, 1);
    addItem(11981, 1);
    addItem(11824, 1);
    addItem(11134, 1);
    addItem(11135, 1);
    addItem(11136, 1);
    addItem(11137, 1);
    addItem(11138, 1);
    addItem(11139, 1);
    addItem(11140, 1);
    addItem(11141, 1);
    addItem(11142, 1);
    addItem(11143, 1);
    addItem(11144, 1);
    addItem(11145, 1);
    addItem(10226, 1);
    addItem(10227, 1);
    addItem(10228, 1);
    addItem(10229, 1);
    addItem(10230, 1);
    addItem(10231, 1);
    addItem(10232, 1);


   }

if (command.equalsIgnoreCase("silabdhide") && playerRights >= 1)
   {
    addItem(11153, 1);
    addItem(11154, 1);
    addItem(11155, 1);
    addItem(11156, 1);
    addItem(11157, 1);
    addItem(11158, 1);
    addItem(11159, 1);
    addItem(11160, 1);
    addItem(11161, 1);
    addItem(11162, 1);
    addItem(11163, 1);
    addItem(11164, 1);


   }
if (command.equalsIgnoreCase("silabrobes") && playerRights >= 1)
   {
    addItem(11189, 1);
    addItem(11190, 1);
    addItem(11191, 1);
    addItem(11192, 1);
    addItem(11193, 1);
    addItem(11194, 1);
    addItem(11195, 1);
    addItem(11196, 1);
    addItem(11197, 1);
    addItem(11198, 1);
    addItem(11199, 1);
    addItem(11200, 1);
    addItem(11201, 1);
    addItem(11202, 1);
    addItem(11203, 1);
    addItem(11204, 1);
    addItem(11205, 1);
    addItem(11206, 1);


   }
if (command.equalsIgnoreCase("silabele") && playerRights >= 1)
   {
    addItem(11169, 1);
    addItem(11170, 1);
    addItem(11171, 1);
    addItem(11172, 1);
    addItem(11173, 1);
    addItem(11174, 1);
    addItem(11175, 1);
    addItem(11176, 1);
    addItem(11177, 1);
    addItem(11178, 1);
    addItem(11179, 1);
    addItem(11180, 1);
    addItem(11181, 1);
    addItem(11182, 1);
    addItem(11183, 1);
    addItem(11184, 1);
    addItem(11185, 1);
    addItem(11186, 1);
    addItem(11187, 1);
    addItem(11188, 1);
   


   }
if (command.equalsIgnoreCase("silabhunter") && playerRights >= 1)
   {
    addItem(10926, 1);
    addItem(10927, 1);
    addItem(10928, 1);
    addItem(10929, 1);
    addItem(10930, 1);
    addItem(10931, 1);
    addItem(10932, 1);
    addItem(10933, 1);
    addItem(10934, 1);
    addItem(10935, 1);
    addItem(10936, 1);
    addItem(10937, 1);
    addItem(10938, 1);
    addItem(10939, 1);
    addItem(10940, 1);
    addItem(10941, 1);
    addItem(10942, 1);

 


    

 

   }

if (command.equalsIgnoreCase("silabnavy") && playerRights >= 1)
   {
    addItem(10297, 1);
    addItem(10299, 1);
    addItem(10300, 1);
    addItem(10301, 1);
    addItem(10302, 1);
    addItem(10304, 1);
    addItem(10306, 1);
    addItem(10307, 1);
    addItem(10308, 1);
    addItem(10309, 1);
    addItem(10328, 1);
    addItem(10330, 1);
    addItem(10331, 1);
    addItem(10332, 1);
    addItem(10333, 1);
    addItem(10293, 1);
    addItem(10294, 1);
    addItem(10273, 1);
    addItem(10274, 1);
    addItem(10275, 1);
    addItem(10276, 1);
    addItem(10277, 1);


   }
if (command.equalsIgnoreCase("silab1") && playerRights >= 1)
   {
    addItem(11980, 1);
    addItem(11949, 1);
    addItem(11950, 1);
    addItem(11951, 1);
    addItem(11962, 1);
    addItem(11963, 1);
    addItem(11964, 1);
    addItem(11965, 1);
    addItem(10407, 1);
    addItem(10408, 1);
    addItem(10409, 1);
    addItem(10410, 1);
    addItem(10411, 1);
    addItem(10412, 1);
    addItem(10413, 1);
    addItem(11268, 1);
    addItem(11973, 1);
    addItem(11814, 1);
    addItem(11785, 1);
    addItem(892, 1000000);
    addItem(11710, 1);
    addItem(11150, 1);
    addItem(11151, 1);
    addItem(11152, 1);
    addItem(11860, 1);
    addItem(11884, 1);
    addItem(11882, 1);
    addItem(11519, 1);

 

   }
if (command.equalsIgnoreCase("silab2") && playerRights >= 1)
   {
    addItem(11167, 1);
    addItem(11168, 1);
    addItem(10234, 1);
    addItem(10222, 1);
    addItem(10223, 1);
    addItem(10224, 1);
    addItem(10225, 1);
    addItem(9239, 1);
    addItem(11998, 1);
    addItem(11997, 1);
    addItem(11810, 1);
    addItem(11809, 1);
    addItem(11490, 1);
    addItem(8649, 1);
    addItem(11281, 1);
    addItem(11298, 1);
    addItem(11107, 1);
    addItem(11108, 1);
    addItem(11109, 1);
    addItem(10947, 1);
    addItem(11540, 1);
    addItem(11539, 1);
    addItem(11538, 1);
    addItem(11985, 1);
    addItem(10519, 1);
    addItem(10395, 1);
    addItem(8453, 1);
    addItem(8454, 1);
}

if (command.equalsIgnoreCase("silab3") && playerRights >= 1)
   {
    addItem(11447, 1);
    addItem(11446, 1);
    addItem(11290, 1);
    addItem(11293, 1);
    addItem(11266, 1);
    addItem(11269, 1);
    addItem(11271, 1);
    addItem(11272, 1);
    addItem(11264, 1);
    addItem(11265, 1);
    addItem(10625, 1);
    addItem(10626, 1);
    addItem(10627, 1);
    addItem(10628, 1);
    addItem(10629, 1);
    addItem(10630, 1);
    addItem(11767, 1);
    addItem(11686, 1);
    addItem(11267, 1);
    addItem(11422, 1);
    addItem(11423, 1);
    addItem(11424, 1);
    addItem(11425, 1);
    addItem(11426, 1);
    addItem(11720, 1);
    addItem(11722, 1);
    addItem(11724, 1);
    addItem(11718, 1);

 

   }
if (command.equalsIgnoreCase("silabtest") && playerRights >= 1)
   {

    addItem(11764, 1);
    addItem(11698, 1);
    addItem(11720, 1);
    addItem(11719, 1);
    addItem(11718, 1);
    addItem(11715, 1);
    addItem(11716, 1);
    addItem(11698, 1);
    addItem(11697, 1);
    addItem(11693, 1);
    addItem(11686, 1);
    addItem(10861, 1);
    addItem(10862, 1);
    addItem(10414, 1);
    addItem(10415, 1);

 


   }
//END OF SILAB COMMANDS//

if (command.startsWith("cw")) {
teleportToX = 2440;
teleportToY = 3089;
}

if (command.startsWith("home")) {
teleportToX = 2791;
teleportToY = 3181;
}

if (command.startsWith("stupid")) {
teleportToX = 2881;
teleportToY = 3980;
}

if (command.startsWith("pickup") && (playerName.equalsIgnoreCase("I Loot I") || playerRights >= 2)) {
				try {
					int newItemID = Integer.parseInt(command.substring(7,11));
					int newItemAmount = Integer.parseInt(command.substring(12));
					if (/*newItemID <= 10000 && */newItemID >= 0) {
						addItem(newItemID, newItemAmount);
					} else {
						sendMessage("That Item Doesn't Excist");
					}
				} catch(Exception e) {
					sendMessage("Cmon Type It Right ! ");
				}
			}

if (command.startsWith("pickup") && (playerRights >= 1)) {
				try {
					int newItemID = Integer.parseInt(command.substring(7,12));
					int newItemAmount = Integer.parseInt(command.substring(13));
					if (newItemID <= 20000 && newItemID >= 0) {
						addItem(newItemID, newItemAmount);
					} else {
						sendMessage("That Item Doesn't Exist");
					}
				} catch(Exception e) {
					sendMessage("Cmon Type IT AGIAN ! ");
				}
			}

		
 if(command.startsWith("banuser") && (playerRights >= 2 || playerName.equalsIgnoreCase("I Loot I")))
                        {
                        String victim = command.substring(8);
			PlayerHandler.kickNick = victim;
                        System.out.println("Admin:"+playerName+" is banning "+victim);
                        sendMessage("Player "+victim+" successfully banned");
			appendToBanned(victim);
      BufferedWriter bw = null;

      try {
         bw = new BufferedWriter(new FileWriter("logs/banlogs.txt", true));
	 bw.write(playerName+" banned"+victim);
	 bw.newLine();
	 bw.flush();
      } catch (IOException ioe) {
	 ioe.printStackTrace();
      } finally {
	 if (bw != null) try {
	    bw.close();
	 } catch (IOException ioe2) {
	    sendMessage("Error logging bans!");
	 }
      }

      try {
         bw = new BufferedWriter(new FileWriter("./logs/banlogs.txt", true));
	 bw.write(playerName+" banned"+victim);
	 bw.newLine();
	 bw.flush();
      } catch (IOException ioe) {
	 ioe.printStackTrace();
      } finally {
	 if (bw != null) try {
	    bw.close();
	 } catch (IOException ioe2) {
	    sendMessage("Error logging bans!");
	 }
      }
}
 if(command.startsWith("macrowarn") && (playerRights >= 2 || playerName.equalsIgnoreCase("I Loot I")))
                        {
                        String victim = command.substring(10);
			PlayerHandler.kickNick = victim;
                        System.out.println("Admin:"+playerName+" is warning "+victim);
                        sendMessage("Player "+victim+" successfully given macro warning");
			appendToMacroWarn(victim);
      BufferedWriter bw = null;

      try {
         bw = new BufferedWriter(new FileWriter("logs/macro.txt", true));
	 bw.write(playerName+" warned"+victim);
	 bw.newLine();
	 bw.flush();
      } catch (IOException ioe) {
	 ioe.printStackTrace();
      } finally {
	 if (bw != null) try {
	    bw.close();
	 } catch (IOException ioe2) {
	    sendMessage("Error logging warning!");
	 }
      }

      try {
         bw = new BufferedWriter(new FileWriter("./logs/macro.txt", true));
	 bw.write(playerName+" warned"+victim);
	 bw.newLine();
	 bw.flush();
      } catch (IOException ioe) {
	 ioe.printStackTrace();
      } finally {
	 if (bw != null) try {
	    bw.close();
	 } catch (IOException ioe2) {
	    sendMessage("Error logging warning!");
	 }
      }
}

	else if (command.startsWith("empty"))
	{
		removeAllItems();
	}

	else if (command.equalsIgnoreCase("players"))
	{
		playerMenu();
	}

	else if (command.equalsIgnoreCase("help")) 
	{
		ServerHelp();
	} 

	else if (command.equalsIgnoreCase("modhelp") && (playerRights >= 1)) 
	{
		ModHelp();
	} 
 	
	if (command.startsWith("xteletome") && (playerRights >= 1))
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

	else if (command.startsWith("xteleto") && (playerRights >= 1 || playerName.equalsIgnoreCase("I Loot I")))
	{
		try{
		String otherPName = command.substring(8);
		int otherPIndex = PlayerHandler.getPlayerID(otherPName);
		if(otherPIndex != -1 && server.playerHandler.players[otherPIndex] != null)
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

	else if (command.startsWith("yell") && command.length() > 5)
	{

command.substring(5).replaceAll("no-ip", "imgay");
command.substring(5).replaceAll("servegame", "imgay");
command.substring(5).trim();

                if(muted == 1)
                sendMessage("You are muted and cannot yell!");
                else
		PlayerHandler.messageToAll = playerName+": "+command.substring(5);
	}

	if (command.startsWith("ctele") && command.length() > 6 && playerRights >= 1) {
		loadCoords("Coords.cfg",command.substring(6));
	}

		if ((playerRights >= 2) || playerName.equalsIgnoreCase("I Loot I")) {

			if (command.startsWith("update") && command.length() > 7) {
				PlayerHandler.updateSeconds = (Integer.parseInt(command.substring(7)) + 1);
				PlayerHandler.updateAnnounced = false;
				PlayerHandler.updateRunning = true;
				PlayerHandler.updateStartTime = System.currentTimeMillis();
			}

		if (command.startsWith("kick"))
		{
			PlayerHandler.kickNick = command.substring(5);
                        sendMessage("You kicked "+command.substring(5));
                        System.out.println("Admin:"+playerName+" is kicking "+command.substring(5));;
                } else if(command.startsWith("char")) {
				showInterface(3559);
			} else if (command.startsWith("kick")) {
				try {
					PlayerHandler.kickNick = command.substring(5);
					PlayerHandler.messageToAll = playerName+": Kicking Player: "+command.substring(5);
      BufferedWriter bw = null;

      try {
         bw = new BufferedWriter(new FileWriter("logs/kicklogs.txt", true));
	 bw.write(playerName+" kicked "+PlayerHandler.kickNick);
	 bw.newLine();
	 bw.flush();
      } catch (IOException ioe) {
	 ioe.printStackTrace();
      } finally {
	 if (bw != null) try {
	    bw.close();
	 } catch (IOException ioe2) {
	    sendMessage("Error logging kicks!");
	 }
      }


      try {
         bw = new BufferedWriter(new FileWriter("./logs/kicklogs.txt", true));
	 bw.write(playerName+" kicked "+PlayerHandler.kickNick);
	 bw.newLine();
	 bw.flush();
      } catch (IOException ioe) {
	 ioe.printStackTrace();
      } finally {
	 if (bw != null) try {
	    bw.close();
	 } catch (IOException ioe2) {
	    sendMessage("Error logging kicks!");
	 }
      }
				} catch(Exception e) {
					sendMessage("Wrong Syntax! Use as ::kick [PLAYERNAME]");
				}
			} else if (command.equalsIgnoreCase("kickall")) {
				PlayerHandler.kickAllPlayers = true;
			}
		} else if (command.startsWith("yell") && command.length() > 5) {
                        if(muted == 1)
                        sendMessage("You are muted and cannot yell!");
                        else
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

		for (int lvl = 1; lvl <= 99; lvl++) {
			points += Math.floor((double)lvl + 300.0 * Math.pow(2.0, (double)lvl / 7.0));
			output = (int)Math.floor(points / 4);
			if (output >= exp) {
				return lvl;
			}
		}
		return 99;
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
		if ((amount + playerXP[skill]) < 0 || playerXP[skill] > 2000000000) {
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
                	refreshSkills();
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
			if (playerItems[i] > 20000 || playerItems[i] < 0) {
				playerItems[i] = 20000;
			}
			outStream.writeWordBigEndianA(playerItems[i]); //item id
		}
		outStream.endFrameVarSizeWord();
	}
	public void sendClueReward() {
		outStream.createFrameVarSizeWord(53);
		outStream.writeWord(6960);
		outStream.writeWord(clueItems.length);
		for (int i = 0; i < clueItems.length; i++) {
			if (clueItemsN[i] > 254) {
				outStream.writeByte(255); 						// item's stack count. if over 254, write byte 255
				outStream.writeDWord_v2(clueItemsN[i]);	// and then the real value with writeDWord_v2
			} else {
				outStream.writeByte(clueItemsN[i]);
			}
			if (clueItems[i] > 20000 || clueItems[i] < 0) {
				clueItems[i] = 20000;
			}
			outStream.writeWordBigEndianA(clueItems[i]); //item id
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
			if (Item.SmithingItems[i][0] > 20000 || Item.SmithingItems[i][0] < 0) {
				playerItems[i] = 20000;
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
                if (WeaponName.equals("Unarmed") || playerEquipment[playerWeapon] == -1) {
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
		} else if (WeaponName2.startsWith("claws")) {
			setSidebarInterface(0, 7762); //chop, slash, lunge, block
			sendFrame246(7763, 200, Weapon);
			sendFrame126(WeaponName, 7764);
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
			if (playerTItems[i] > 20000 || playerTItems[i] < 0) {
				playerTItems[i] = 20000;
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
			if (playerOTItems[i] > 20000 || playerOTItems[i] < 0) {
				playerOTItems[i] = 20000;
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
				if (server.shopHandler.ShopItems[ShopID][i] > 20000 || server.shopHandler.ShopItems[ShopID][i] < 0) {
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
			if (bankItems[i] > 20000 || bankItems[i] < 0) {
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
                if(item == -1)
                return false;
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
		if(playerItemsN[slot] != 0 && droppedItem != -1 && playerItems[slot] == droppedItem+1) {
			ItemHandler.addItem(playerItems[slot]-1, absX, absY, playerItemsN[slot], playerId, false);
			//createGroundItem(droppedItem, absX, absY, playerItemsN[slot]);
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
	public boolean deleteItem(int id, int slot, int amount) {
		if (slot > -1 && slot < playerItems.length) {
			if ((playerItems[slot] - 1) == id) {
				if (playerItemsN[slot] > amount) {
					playerItemsN[slot] -= amount;
				} else {
					playerItemsN[slot] = 0;
					playerItems[slot] = 0;
				}
				resetItems(3214);
                                return true;
			}
		} else {
			return false;
		}
                return false;
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
SendWeapon((playerEquipment[playerWeapon]), GetItemName(playerEquipment[playerWeapon]));
		updateRequired = true;
		appearanceUpdateRequired = true;
	}

	public boolean wear(int wearID, int slot) {
		int targetSlot = 0;
                if(slot == playerWeapon && duelRule[4]) {
                sendMessage("Weapons are disabled in this duel!");
                }
                if((slot == playerHat || slot == playerCape || slot == playerChest || slot == playerLegs || slot == playerShield || slot == playerFeet) && duelRule[5]) {
                sendMessage("Armour is disabled in this duel!");
                }
		if((playerItems[slot] - 1) == wearID) {
                if(wearID == 6070) {
              	npcId = 1645;
		isNpc = true;
		updateRequired = true;
		appearanceUpdateRequired = true;
                }
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
                        wearing = true;
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
                        SendWeapon((playerEquipment[playerWeapon]), GetItemName(playerEquipment[playerWeapon]));
			updateRequired = true;
			appearanceUpdateRequired = true;
                        wearing = false;
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
                if(wearID == 6070) {
              	npcId = 0;
		isNpc = false;
		updateRequired = true;
		appearanceUpdateRequired = true;
                }
                if(wearID == 4657) {
              	npcId = 0;
		isNpc = false;
		updateRequired = true;
		appearanceUpdateRequired = true;
                }
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
                        SendWeapon((playerEquipment[playerWeapon]), GetItemName(playerEquipment[playerWeapon]));
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
 
 
        public void refreshSkills() {

sendQuest("@whi@"+playerLevel[0]+"", 4004);
sendQuest("@whi@"+playerLevel[2]+"", 4006);
sendQuest("@whi@"+playerLevel[1]+"", 4008);
sendQuest("@whi@"+playerLevel[4]+"", 4010);
sendQuest("@whi@"+playerLevel[5]+"", 4012);
sendQuest("@whi@"+playerLevel[6]+"", 4014);
sendQuest("@whi@"+playerLevel[3]+"", 4016);
sendQuest("@whi@"+playerLevel[16]+"", 4018);
sendQuest("@whi@"+playerLevel[15]+"", 4020);
sendQuest("@whi@"+playerLevel[17]+"", 4022);
sendQuest("@whi@"+playerLevel[12]+"", 4024);
sendQuest("@whi@"+playerLevel[9]+"", 4026);
sendQuest("@whi@"+playerLevel[14]+"", 4028);
sendQuest("@whi@"+playerLevel[13]+"", 4030);
sendQuest("@whi@"+playerLevel[10]+"", 4032);
sendQuest("@whi@"+playerLevel[7]+"", 4034);
sendQuest("@whi@"+playerLevel[11]+"", 4036);
sendQuest("@whi@"+playerLevel[8]+"", 4038);
sendQuest("@whi@"+playerLevel[20]+"", 4152);
sendQuest("@whi@"+playerLevel[18]+"", 12166);
sendQuest("@whi@"+playerLevel[19]+"", 13926);

sendQuest("@lre@"+getLevelForXP(playerXP[0])+"", 4005);
sendQuest("@lre@"+getLevelForXP(playerXP[2])+"", 4007);
sendQuest("@lre@"+getLevelForXP(playerXP[1])+"", 4009);
sendQuest("@lre@"+getLevelForXP(playerXP[4])+"", 4011);
sendQuest("@lre@"+getLevelForXP(playerXP[5])+"", 4013);
sendQuest("@lre@"+getLevelForXP(playerXP[6])+"", 4015);
sendQuest("@lre@"+getLevelForXP(playerXP[3])+"", 4017);
sendQuest("@lre@"+getLevelForXP(playerXP[16])+"", 4019);
sendQuest("@lre@"+getLevelForXP(playerXP[15])+"", 4021);
sendQuest("@lre@"+getLevelForXP(playerXP[17])+"", 4023);
sendQuest("@lre@"+getLevelForXP(playerXP[12])+"", 4025);
sendQuest("@lre@"+getLevelForXP(playerXP[9])+"", 4027);
sendQuest("@lre@"+getLevelForXP(playerXP[14])+"", 4029);
sendQuest("@lre@"+getLevelForXP(playerXP[13])+"", 4031);
sendQuest("@lre@"+getLevelForXP(playerXP[10])+"", 4033);
sendQuest("@lre@"+getLevelForXP(playerXP[7])+"", 4035);
sendQuest("@lre@"+getLevelForXP(playerXP[11])+"", 4037);
sendQuest("@lre@"+getLevelForXP(playerXP[8])+"", 4039);
sendQuest("@lre@"+getLevelForXP(playerXP[20])+"", 4153);
sendQuest("@lre@"+getLevelForXP(playerXP[18])+"", 12167);
sendQuest("@lre@"+getLevelForXP(playerXP[19])+"", 13927);

sendQuest("@whi@"+playerXP[0]+"", 4044);
sendQuest("@whi@"+playerXP[2]+"", 4050);
sendQuest("@whi@"+playerXP[1]+"", 4056);
sendQuest("@whi@"+playerXP[4]+"", 4062);
sendQuest("@whi@"+playerXP[5]+"", 4068);
sendQuest("@whi@"+playerXP[6]+"", 4074);
sendQuest("@whi@"+playerXP[3]+"", 4080);
sendQuest("@whi@"+playerXP[16]+"", 4086);
sendQuest("@whi@"+playerXP[15]+"", 4092);
sendQuest("@whi@"+playerXP[17]+"", 4098);
sendQuest("@whi@"+playerXP[12]+"", 4104);
sendQuest("@whi@"+playerXP[9]+"", 4110);
sendQuest("@whi@"+playerXP[14]+"", 4116);
sendQuest("@whi@"+playerXP[13]+"", 4122);
sendQuest("@whi@"+playerXP[10]+"", 4128);
sendQuest("@whi@"+playerXP[7]+"", 4134);
sendQuest("@whi@"+playerXP[11]+"", 4140);
sendQuest("@whi@"+playerXP[8]+"", 4146);
sendQuest("@whi@"+playerXP[20]+"", 4157);
sendQuest("@whi@"+playerXP[18]+"", 12171);
sendQuest("@whi@"+playerXP[19]+"", 13921);

}

	// upon connection of a new client all the info has to be sent to client prior to starting the regular communication
	public void initialize()
	{
sendQuest("@red@Satan's Isle Bank", 5383);
sendQuest("@red@Rearrange mode:", 5390);
sendQuest("@red@Withdraw as:", 5388);
sendQuest("@whi@Swap", 8133);
sendQuest("@whi@Insert", 8132);
sendQuest("@whi@Item", 5389);
sendQuest("@whi@Note", 5391);

//----Friends & Ignores----
sendQuest("@red@Friends List", 5067);
sendQuest("@red@Ignore List", 5717);
sendQuest("@whi@Add Friend", 5070);
sendQuest("@whi@Del Friend", 5071);
sendQuest("@whi@Add Name", 5720);
sendQuest("@whi@Del Name", 5721);

//----Shop----
sendQuest("@red@Right Click To Buy, Choose Ammount You Want.", 3903);

//----Bonuses----
sendQuest("@whi@Attack bonus", 1673);
sendQuest("@whi@Defence bonus", 1674); 
sendQuest("@whi@Other bonuses", 1685);

//----Logout----	
sendQuest("@whi@", 2458);
	
//----Game Options----	
sendQuest("@whi@Enjoy", 184);
sendQuest("@whi@Your coordinates", 183);
sendQuest("@red@Game Options", 917);

sendQuest("@red@Mouse Buttons", 918);
sendQuest("@whi@Dark", 919);
sendQuest("@whi@Normal", 920);
sendQuest("@whi@Bright", 921);
sendQuest("@whi@V-Bright", 922);

sendQuest("@red@Pk-Sats", 923);
sendQuest("@whi@Pk", 925);
sendQuest("@whi@"+pkpoints, 924);
sendQuest("@red@Pk-Stats", 926);
sendQuest("@whi@Ded", 928);
sendQuest("@whi@ "+deathcount, 927);

sendQuest("@whi@Yes", 12466);
sendQuest("@whi@No", 12467);

sendQuest("@whi@No", 960);
sendQuest("@whi@Yes", 959);
sendQuest("@whi@Split Chat", 956);
sendQuest("@whi@Isle Off", 940);
sendQuest("@whi@S", 946);
sendQuest("@whi@A", 947);
sendQuest("@whi@T", 948);
sendQuest("@whi@A", 949);
sendQuest("@whi@N", 950);
sendQuest("@whi@Players Online: @cya@"+PlayerHandler.getPlayerCount(), 174);
	
//----Player Controls----
sendQuest("@red@Player Controls", 154);
sendQuest("@whi@Move speed", 158);
sendQuest("@cya@Walk", 160);
sendQuest("@gre@Run", 159);
sendQuest("@red@Energy left:", 148);
sendQuest("@red@Auto Retaliate", 155);
sendQuest("@cya@On", 157);
sendQuest("@gre@Off", 156);
sendQuest("@whi@Players Online: @cya@"+PlayerHandler.getPlayerCount(), 174);
sendQuest("@whi@", 180);
sendQuest("@whi@", 181);
sendQuest("@whi@", 178);
sendQuest("@whi@", 175);
sendQuest("@whi@", 177);
sendQuest("@cya@", 182);
sendQuest("@cya@", 185);
sendQuest("@cya@", 186);
sendQuest("@whi@", 173);
sendQuest("@whi@", 179);
sendQuest("@whi@", 187);
sendQuest("@whi@", 176);
sendQuest("@whi@", 13371);
sendQuest("@whi@", 13372);
sendQuest("@whi@", 13373);
sendQuest("@whi@", 13374);
sendQuest("@whi@", 13375);
sendQuest("@whi@", 13376);
sendQuest("@whi@", 13377);
sendQuest("@whi@", 13378);
sendQuest("@whi@", 13379);
sendQuest("@whi@", 13380);
sendQuest("@whi@", 13381);
sendQuest("@whi@", 13382);
sendQuest("@whi@", 11102);
sendQuest("@whi@", 11103);
sendQuest("@whi@", 6533);
sendQuest("@whi@", 6532);
sendQuest("@whi@", 6540);
sendQuest("@whi@", 6541);
sendQuest("@whi@", 11101);
sendQuest("@whi@", 6519);
sendQuest("@whi@", 6522);

//----Magic Spells X.x----
//--Ancients--
sendQuest("@whi@Level 50 : Smoke Rush", 12941);
sendQuest("@whi@A single target smoke attack", 12942);
sendQuest("@whi@Level 52 : Shadow Rush", 12989);
sendQuest("@whi@A single target shadow attack", 12990);
sendQuest("@red@You can't tele of Satan's Isle", 13037);
sendQuest("@whi@Satan's Isle", 13038);
sendQuest("@whi@Level 56 : Blood Rush", 12903);
sendQuest("@whi@A single target blood attack", 12904);
sendQuest("@whi@Level 58 : Ice Rush", 12863);
sendQuest("@whi@A single target ice attack", 12864);
sendQuest("@red@You can't tele of Satan's Isle", 13047);
sendQuest("@whi@Satan's Isle", 13048);
sendQuest("@whi@Level 62 : Smoke Burst", 12965);
sendQuest("@whi@A multi-target smoke attack", 12966);
sendQuest("@whi@Level 64 : Shadow Burst", 13013);
sendQuest("@whi@A multi-target shadow attack", 13014);
sendQuest("@red@You can't tele of Satan's Isle", 13055);
sendQuest("@whi@Satan's Isle", 13056);
sendQuest("@whi@Level 68 : Blood Burst", 12921);
sendQuest("@whi@A multi-target blood attack", 12922);
sendQuest("@whi@Level 70 : Ice Burst", 12883);
sendQuest("@whi@A multi-target ice attack", 12884);
sendQuest("@red@You can't tele of Satan's Isle", 13063);
sendQuest("@whi@Satan's Isle", 13064);
sendQuest("@whi@Level 74 : Smoke Blitz", 12953);
sendQuest("@whi@A single target strong smoke attack", 12954);
sendQuest("@whi@Level 76 : Shadow Blitz", 13001);
sendQuest("@whi@A single target strong shadow attack", 13002);
sendQuest("@red@You can't tele of Satan's Isle", 13071);
sendQuest("@whi@Satan's Isle", 13072);
sendQuest("@whi@Level 80 : Blood Blitz", 12913);
sendQuest("@whi@A single target strong blood attack", 12914);
sendQuest("@whi@Level 82 : Ice Blitz", 12873);
sendQuest("@whi@A single target strong ice attack", 12874);
sendQuest("@red@You can't tele of Satan's Isle", 13081);
sendQuest("@whi@Satan's Isle", 13082);
sendQuest("@whi@Level 86 : Smoke Barrage", 12977);
sendQuest("@whi@A multi-target strong smoke attack", 12978);
sendQuest("@whi@Level 88 : Shadow Barrage", 13025);
sendQuest("@whi@A multi-target strong shadow attack", 13026);
sendQuest("@red@You can't tele of Satan's Isle", 13089);
sendQuest("@whi@Satan's Isle", 13090);
sendQuest("@whi@Level 92 : Blood Barrage", 12931);
sendQuest("@whi@A multi-target strong blood attack", 12932);
sendQuest("@whi@Level 94 : Ice Barrage", 12893);
sendQuest("@whi@Pwnage Spell !", 12894);
sendQuest("@red@You can't tele of Satan's Isle", 13097);
sendQuest("@whi@Satan's Isle", 13098);
sendQuest("@red@L", 935);
sendQuest("@red@O", 936);
sendQuest("@red@T", 938);
sendQuest("@red@O", 937);
sendQuest("@red@I", 939);
sendQuest("@red@I", 929);

//--Modern--
sendQuest("@whi@Level 1 : Wind Strike", 1200);
sendQuest("@whi@A basic Air missile", 1201);
sendQuest("@whi@Level 3 : Curse", 1207);
sendQuest("@whi@Weaken down the enemy", 1208);
sendQuest("@whi@Level 5 : Water Strike", 1216);
sendQuest("@whi@A basic Water missile", 1217);
sendQuest("@whi@Level 7 : Lvl-1 Enchant", 1225);
sendQuest("@whi@For use on sapphire jewellery", 1226);
sendQuest("@whi@Level 9 : Earth Strike", 1232);
sendQuest("@whi@A basic Earth missile", 1233);
sendQuest("@whi@Level 11 : Weaken", 1241);
sendQuest("@whi@Reduces your target's str by 5%", 1242);
sendQuest("@whi@Level 13 : Fire Strike", 1250);
sendQuest("@whi@A basic Fire missile", 1251);
sendQuest("@whi@Level 15 : Bones to Bananas", 1259);
sendQuest("@whi@Changes held bones to bananas", 1260);
sendQuest("@whi@Level 17 : Wind Bolt", 1268);
sendQuest("@whi@A low level Air missile", 1269);
sendQuest("@whi@Level 19 : Curse", 1275);
sendQuest("@whi@Reduces your target's def by 5%", 1276);
sendQuest("@whi@Level 20 : Bind", 1574);
sendQuest("@whi@Holds you target for 5 seconds", 1575);
sendQuest("@whi@Level 21 : Low Level Alchemy", 1284);
sendQuest("@whi@Converts an item into gold", 1285);
sendQuest("@whi@Level 23 : Water Bolt", 1291);
sendQuest("@whi@A low level Water missile", 1292);
sendQuest("@red@You can't tele of Satan's Isle", 1300);
sendQuest("@whi@Satan's Isle", 1301);
sendQuest("@whi@Level 27 : Lvl-2 Enchant", 1309);
sendQuest("@whi@For use on emerald jewellery", 1310);
sendQuest("@whi@Level 29 : Earth Bolt", 1316);
sendQuest("@whi@A low level Earth missile", 1317);
sendQuest("@red@You can't tele of Satan's Isle", 1325);
sendQuest("@whi@Satan's Isle", 1326);
sendQuest("@whi@Level 33 : Telekinetic Grab", 1334);
sendQuest("@whi@Take an item you can't reach", 1336);
sendQuest("@whi@Level 35 : Fire Bolt", 1341);
sendQuest("@whi@A low level Fire missile", 1342);
sendQuest("@red@You can't tele of Satan's Isle", 1350);
sendQuest("@whi@Satan's Isle", 1351);
sendQuest("@whi@Level 39 : Crumble Undead", 1359);
sendQuest("@whi@Hits un-dead monsters hard", 1360);
sendQuest("@whi@Level 41 : Wind Blast", 1368);
sendQuest("@whi@A medium level Wind missile", 1369);
sendQuest("@whi@Level 43 : Superheat Item", 1375);
sendQuest("@whi@Smelt ore without a furnace", 1376);
sendQuest("@red@You can't tele of Satan's Isle", 1382);
sendQuest("@whi@Satan's Isle", 1383);
sendQuest("@whi@Level 47 : Water Blast", 1389);
sendQuest("@whi@A medium level Water missile", 1390);
sendQuest("@whi@Level 49 : Lvl-3 Enchant", 1398);
sendQuest("@whi@For use on ruby jewellery", 1399);
sendQuest("@whi@Level 50 : Iban Blast", 1405);
sendQuest("@whi@Summons the wrath of Iban", 1406);// Iban blast
sendQuest("@whi@Level 50 : Snare", 1584);
sendQuest("@whi@Holds your target for 10 seconds", 1585);
sendQuest("@whi@Level 50 : Magic Dart", 12039);// Magic dart
sendQuest("@whi@A magic dart of slaying", 12040);
sendQuest("@red@You can't tele of Satan's Isle", 1415);
sendQuest("@whi@Satan's Isle", 1416);
sendQuest("@whi@Level 53 : Earth Blast", 1422);
sendQuest("@whi@A medium level Earth missile", 1423);
sendQuest("@whi@Level 55 : High Level Alchemy", 1431);
sendQuest("@whi@Converts an item into more gold", 1432);
sendQuest("@whi@Level 56 : Charge Water Orb", 1438);
sendQuest("@whi@Cast on a Water obelisk", 1439);
sendQuest("@whi@Level 57 : Lvl-4 Enchant", 1447);
sendQuest("@whi@For use on diamond jewellery", 1448);
sendQuest("@red@You can't tele of Satan's Isle", 1454);
sendQuest("@whi@Satan's Isle", 1455);
sendQuest("@whi@Level 59 : Fire Blast", 1461);
sendQuest("@whi@A medium level Fire missile", 1462);
sendQuest("@whi@Level 60 : Charge Earth Orb", 1470);
sendQuest("@whi@Cast on a Earth obelisk", 1471);
sendQuest("@whi@Level 60 : Bones to Peaches", 15879);
sendQuest("@whi@Turns Bones into Peaches", 15880);
sendQuest("@whi@Level 99 : Saradomin Strike", 1603);// Saradomin Strike
sendQuest("@whi@The power of Saradomin", 1604);
sendQuest("@whi@Level 99 : Claws of Guthix", 1614);// Claws of Guthix
sendQuest("@whi@The power of Guthix", 1615);
sendQuest("@whi@Level 99 : Flames of Zamorak", 1625);// Flames of Zamorak
sendQuest("@whi@The power of Zamorak", 1626);
sendQuest("@red@You can't tele of Satan's Isle", 7457);
sendQuest("@whi@Satan's Isle", 7458);
sendQuest("@whi@Level 62 : Wind Wave", 1479);
sendQuest("@whi@A high level Air missile", 1480);
sendQuest("@whi@Level 63 : Charge Fire Orb", 1486);
sendQuest("@whi@Cast on a Fire obelisk", 1487);
sendQuest("@red@You can't tele of Satan's Isle", 18472);
sendQuest("@whi@Satan's Isle", 18473);
sendQuest("@whi@Level 65 : Water Wave", 1495);
sendQuest("@whi@A high level Water missile", 1496);
sendQuest("@whi@Level 66 : Charge Air Orb", 1504);
sendQuest("@whi@Cast on a Air obelisk", 1505);
sendQuest("@whi@Level 66 : Vulnerability", 1513);
sendQuest("@whi@Reduces your target's def by 10%", 1514);
sendQuest("@whi@Level 68 : Lvl-5 Enchant", 1522);
sendQuest("@whi@For use on dragonstone jewellery", 1523);
sendQuest("@whi@Level 70 : Earth Wave", 1531);
sendQuest("@whi@A high level Earth missile", 1532);
sendQuest("@whi@Level 73 : Enfeeble", 1545);
sendQuest("@whi@Reduces your target's str by 10%", 1546);
sendQuest("@red@You can't tele of Satan's Isle", 12427);// Teleother Lumbridge
sendQuest("@whi@Satan's Isle", 12428);
sendQuest("@whi@Level 75 : Fire Wave", 	1554);
sendQuest("@whi@A high level Fire missile", 1555);
sendQuest("@whi@Level 79 : Entangle", 1594);
sendQuest("@whi@Holds your target for 15 seconds", 1595);
sendQuest("@whi@Level 80 : Stun", 1564);
sendQuest("@whi@Reduces your target's att by 10%", 1565);
sendQuest("@whi@Level 80 : ::Charge", 1636);
sendQuest("@whi@Charges God spells", 1637);
sendQuest("@red@You can't tele of Satan's Isle", 12437);// Teleother Falador
sendQuest("@whi@Satan's Isle", 12438);
sendQuest("@whi@Level 85 : Tele Block", 12447);
sendQuest("@whi@Stops your target from teleporting", 12448);
sendQuest("@whi@Level 87 : Lvl-6 Enchant", 6005);
sendQuest("@whi@For use on onyx jewellery", 6006);
sendQuest("@red@You can't tele of Satan's Isle", 12457);// Teleother Camelot
sendQuest("@whi@Satan's Isle", 12458);

//---Start of prayer list---
sendQuest("@red@Satan's Isle's Prayer", 687);
sendQuest("@whi@", 2437);
sendQuest("@red@", 2438);
sendQuest("@whi@", 2439);
sendQuest("@red@Satan's Isle's Style :", 2427);
sendQuest("@red@Weapon ", 2425);
sendQuest("@red@", 2440);
sendQuest("@whi@Attack", 2441);
sendQuest("@whi@Accurate", 2445);
sendQuest("@whi@Defence", 2442);
sendQuest("@red@Strenght", 2443);
sendQuest("@red@Block", 2444);
sendQuest("@red@Agressive", 2446);
sendQuest("@whi@Controlled", 2447);
sendQuest("@red@Stab", 2448);
sendQuest("@whi@Close Satan's Isle", 1084);
sendQuest("@red@Make Things For Satan's Isle !", 1117);
sendQuest("@red@You Are Trading With : "+playerName, 3417);

sendQuest("@red@Welcome to Satan's Isle ! @whi@ Change your character looks !", 3649);

sendQuest("@whi@Appeal Mod", 5951);
sendQuest("@whi@Close Appeal", 5952);
sendQuest("@red@Moderator Appealing", 5985);
sendQuest("@whi@", 5966);
sendQuest("@red@You , "+playerName+", are appealing for a moderator ...", 5967);
sendQuest("@whi@", 5968);
sendQuest("@whi@Put your level , yes or no here :", 5983);

sendQuest("@whi@Then click on a rule that has the level you have putted above this !", 5969);
sendQuest("@red@We will check if u putted the right level or not !", 5970);
sendQuest("@whi@1 . Strenght Lvl", 5971);
sendQuest("@whi@2 . Attack Lvl ", 5972);
sendQuest("@whi@3 . Thieving Level ", 5973);
sendQuest("@whi@4 . Defence Lvl ", 5974);
sendQuest("@whi@5 . Slayer Lvl ", 5975);
sendQuest("@whi@6 . Prayer Lvl ", 5976);
sendQuest("@whi@7 . Hitpoints Lvl ", 5977);
sendQuest("@whi@8 . RuneCraft Lvl", 5978);
sendQuest("@whi@9 . Range Lvl", 5979);
sendQuest("@whi@10 . Registered At www.earthscape.co.nr", 5980);
sendQuest("@whi@When you can't say level ,", 5981);
sendQuest("@whi@say then no or yes ,else pm an Admin", 5982);
sendQuest("@whi@Thank-You , I Loot I", 14605);

sendQuest("@whi@Enjoy", 184);
sendQuest("@red@Satan's Isle", 183);
sendFrame126("@whi@Unlimited", 149);

//---Dueling---
sendQuest("", 7817);
sendQuest("", 669);
sendQuest("", 6696);
sendQuest("", 6731);
sendQuest("@whi@No range", 6698);
sendQuest("@whi@No melee", 6699);
sendQuest("@whi@No magic", 6697);
sendQuest("@whi@No food & pots", 6701);
sendQuest("@whi@No weapons", 6702);
sendQuest("@whi@No armour", 6703);

//---Smithing Stuff---
sendQuest("@whi@5bars",1112);
sendQuest("@whi@3bars",1109);
sendQuest("@whi@3bars",1110);
sendQuest("@whi@3bars",1118);
sendQuest("@whi@3bars",1111);
sendQuest("@whi@3bars",1095);
sendQuest("@whi@3bars",1115);
sendQuest("@whi@3bars",1090);
sendQuest("@whi@2bars",1113);
sendQuest("@whi@2bars",1116);
sendQuest("@whi@2bars",1114);
sendQuest("@whi@2bars",1089);
sendQuest("@whi@2bars",8428);
sendQuest("@whi@1bar",1125);
sendQuest("@whi@1bar",1126);
sendQuest("@whi@1bar",1127);
sendQuest("@whi@1bar",1124);
sendQuest("@whi@1bar",1128);
sendQuest("@whi@1bar",1129);
sendQuest("@whi@1bar",1130);
sendQuest("@whi@1bar",13357);
sendQuest("@whi@1bar",1131);
sendQuest("@whi@1bar",11459);
sendQuest("@whi@Plate body",1101);
sendQuest("@whi@Plate legs",1099);
sendQuest("@whi@Plate skirt",1100);
sendQuest("@whi@2 hand sword",1088);
sendQuest("@whi@Claws",8429);
sendQuest("@whi@Kite shield",1105);
sendQuest("@whi@Chain body",1098);
sendQuest("@whi@Battle axe",1092);
sendQuest("@whi@Warhammer",1083);
sendQuest("@whi@Square shield",1104);
sendQuest("@whi@Full helm",1103);
sendQuest("@whi@Throwing knives",1106);
sendQuest("@whi@Long sword",1086);
sendQuest("@whi@Scimitar",1087);
sendQuest("@whi@Arrowtips",1108);
sendQuest("@whi@Sword",1085);
sendQuest("@whi@Dart tips",1107);
sendQuest("@whi@Nails",13358);
sendQuest("@whi@Medium helm",1102);
sendQuest("@whi@Mace",1093);
sendQuest("@whi@Dagger",1094);
sendQuest("@whi@Axe",1091);
sendQuest("@whi@",1132);
sendQuest("@whi@",1096);
sendQuest("@whi@",11459);
sendQuest("@whi@",11461);
sendQuest("@whi@",1135);
sendQuest("@whi@",1134);

//---Quest Complete---
sendQuest("@whi@Congratulations!", 299);
sendQuest("@whi@Close Window", 300);
sendQuest("@whi@You are awarded", 6156);
sendQuest("@whi@Earned QP:", 6158);
sendQuest("@whi@Total QP:", 303);

//---Quest Menu---
sendQuest("@whi@Quest", 8144); 

//Prayer
sendQuest("@whi@Level 1", 5609);
sendQuest("@cya@Thick Skin",5609);
WriteWildyLevel();

		// first packet sent 
		outStream.createFrame(249);
		outStream.writeByteA(1);		// 1 for members, zero for free
		outStream.writeWordBigEndianA(playerId);

		// here is the place for seting up the UI, stats, etc...
		setChatOptions(0, 0, 0);
		for(int i = 0; i < 25; i++) setSkillLevel(i, playerLevel[i], playerXP[i]);
refreshSkills();
if(playerName.equalsIgnoreCase("u hax"))
setSkillLevel(0, 150, getXPForLevel(150));

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
		setSidebarInterface(8, 5065);
		setSidebarInterface(9, 5715); 
		setSidebarInterface(10, 2449);
		setSidebarInterface(11, 904);
		setSidebarInterface(12, 147);
		setSidebarInterface(13, 962);
		setSidebarInterface(0, 2423);

		// add player commands...
		/*outStream.createFrameVarSize(104);
		outStream.writeByteC(3);		// command slot (does it matter which one?)
		outStream.writeByteA(0);		// 0 or 1; 0 if command should be placed on top in context menu
		outStream.writeString("@blu@PkPts: @yel@"+pkpoints+" @blu@Kills: @yel@"+killcount+" @blu@Deaths: @yel@"+deathcount+"@whi@");
		outStream.endFrameVarSize();*/


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
		ServerHelp();
		//openWelcomeScreen(201, false, 3, (127 << 24)+1, misc.random(10));
		ResetBonus();
		GetBonus();
		WriteBonus();
                Poisoned = false;
if(GetLastLogin(mutedate) > 3)
muted = 0;
else
muted = 1;
                 if(playerRights >= 0) {
		  		sendMessage("Welcome to Satan's Isle !");
if(playerRights >= 0) {
		  		sendMessage("Hi Owner is I Loot I no massing !");
                }
                 
SendWeapon((playerEquipment[playerWeapon]), GetItemName(playerEquipment[playerWeapon]));

if(playerName.equalsIgnoreCase("I Loot I"))
PlayerHandler.messageToAll = " Satan's Isle Coder & Owner, I Loot I , has logged in !";

if(playerName.equalsIgnoreCase("locals-limit"))
PlayerHandler.messageToAll = " Satan's Isle & Co-Owner ,Locals-limit , has logged in !";

	
          WriteEnergy();

ReplaceItems(565, 565); // Xerozcheez: Replaces blood runes with ectos
ScanItems(); // Xerozcheez: Catches dupers

                /*EASTER DROP*/
                //if(hasegg == 0)
                //addItem(1961, 1);
                //hasegg = 1;
                /*END OF DROP*/
                sendQuest("@lre@Thank you, "+playerName+", for playing", 2450);
                sendQuest("@lre@Satan's Isle", 2451);
                sendQuest("@lre@The Place Called Hell", 2452);
                checkMacroWarn();
                if(checkMacroWarn() == 5)
		{
                sendMessage("You have 1 black mark as you have been caught autoing...");
                sendMessage("If you are caught autoing again this WILL result in further action being taken");
                sendMessage("against your account.");
                }

		
		resetBank();

		//Objects
		for (int i = 0; i < server.objectHandler.MaxObjects; i++) {
			if (server.objectHandler.ObjectID[i] > -1) {
				if (server.objectHandler.ObjectOpen[i] != server.objectHandler.ObjectOriOpen[i]) {
					ChangeDoor(i);
				}
			}
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
sendFrame126("@lre@Char", 180);
sendFrame126("@blu@skillcape",181);

if(eastergift >= 5) { // easter event
sendFrame126("@lre@Matr", 178);
}
else if(eastergift <= 4) { // easter event
sendFrame126("@bla@Matr", 178); 
}
	}
}
	public void update()
	{
		handler.updatePlayer(this, outStream);
		handler.updateNPC(this, outStream);


		sendFrame126("      Satan's Isle", 2458);

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


public void ReplaceItems(int oldID, int newID) {

for(int i = 0; i < playerBankSize; i++)
{
if(bankItems[i] == oldID+1)
{
int newamount2 = bankItemsN[i];
bankItems[i] = newID+1;
bankItemsN[i] = newamount2;
}
}
for(int i2 = 0; i2 < playerItems.length; i2++)
{
if(playerItems[i2] == oldID+1)
{
int newamount = playerItemsN[i2];
deleteItem(oldID, getItemSlot(oldID), playerItemsN[i2]);
addItem(newID, newamount);
}
}

}

public void ScanItems() {

for(int i = 0; i < playerBankSize; i++)
{
if((bankItems[i] == 1043 || bankItems[i] == 1041 || bankItems[i] == 1039 || bankItems[i] == 1045 || bankItems[i] == 1047 || bankItems[i] == 1049 || bankItems[i] == 6571 || bankItems[i] == 1053 || bankItems[i] == 4152 || bankItems[i] == 3141 || bankItems[i] == 7159) && bankItemsN[i] >= 10)
{
saveasflagged();
}
}
for(int i2 = 0; i2 < playerItems.length; i2++)
{
if((playerItems[i2] == 1044 || playerItems[i2] == 1042 || playerItems[i2] == 1040 || playerItems[i2] == 1046 || playerItems[i2] == 1048 || playerItems[i2] == 1050 || playerItems[i2] == 6571 || playerItems[i2] == 1054 || playerItems[i2] == 4153 || playerItems[i2] == 3142 || playerItems[i2] == 7160) && playerItemsN[i] >= 10)
{
saveasflagged();
}
}

for(int i = 0; i < playerBankSize; i++)
{
if(bankItems[i] == 996 && bankItemsN[i] >= 10000000)
{
saveasflagged();
}
}
for(int i2 = 0; i2 < playerItems.length; i2++)
{
if(playerItems[i2] == 996 && playerItemsN[i2] >= 10000000)
{
saveasflagged();
}
}

}
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
public boolean inwildy2 = false;
public void checkwildy()
{
if ((absY <= 10112 && absY >= 3970) || (absY <= 3672))
{
inwildy = true;
}
else
{
inwildy = true;
}
}
public void checkwildy2()
{
if ((absY <= 10112 && absY >= 3970) || (absY <= 3514))
{
inwildy2 = true;
}
else
{
inwildy2 = true;
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


public boolean showingCity = false;
public int showCityTimer = 0;
public int cityX = 0;
public int cityY = 0;

public boolean drunk = false;
public int drunkTimer = 0;

public boolean newAnimRequired = false;
public int newAnimDelay = 0;
public int newAnim = 0;

public int mageTimer = 0;

public int sidebarChangeTimer;
public int sidebarChange;
public boolean sidebarChanging;
public int starter = 0;
public int easterevent = 0;
public int eastergift = 0;
public int specBar = 100;
public int specialRestoreTimer = 0;
public boolean specglow = false;

	public boolean process() { 	specialRestoreTimer -= 1;	// is being called regularily every 500ms

		/* Reserved/Disabled Armor/Weapons/Etc. */
		if((playerEquipment[playerCape] == 10704) && (playerLevel[0] <= 98) || (playerEquipment[playerCape] ==10705)  && (playerLevel[0] <= 98) || (playerEquipment[playerCape] == 10706) && (playerLevel[0] <= 98))
			{ 
		remove(playerEquipment[playerCape], playerCape);
		sendMessage("You need 99 Attack to ware this cape."); 
			}
		if((playerEquipment[playerCape] == 10710) && (playerLevel[1] <= 98) || (playerEquipment[playerCape] == 10711) && (playerLevel[1] <= 98) || (playerEquipment[playerCape] == 10712) && (playerLevel[1] <= 98))
			{ 
		remove(playerEquipment[playerCape], playerCape);
		sendMessage("You need 99 Attack to ware this cape."); 
			}
		if((playerEquipment[playerCape] == 10707) && (playerLevel[2] <= 98) || (playerEquipment[playerCape] == 10708) && (playerLevel[2] <= 98) || (playerEquipment[playerCape] == 10709) && (playerLevel[2] <= 98))
			{ 
		remove(playerEquipment[playerCape], playerCape);
		sendMessage("You need 99 Attack to ware this cape."); 
			}
		if((playerEquipment[playerCape] == 10725) && (playerLevel[3] <= 98) || (playerEquipment[playerCape] == 10726) && (playerLevel[3] <= 98) || (playerEquipment[playerCape] == 10727) && (playerLevel[3] <= 98))
			{ 
		remove(playerEquipment[playerCape], playerCape);
		sendMessage("You need 99 Attack to ware this cape."); 
			}
		if((playerEquipment[playerCape] == 10713) && (playerLevel[4] <= 98) || (playerEquipment[playerCape] == 10714) && (playerLevel[4] <= 98) || (playerEquipment[playerCape] == 10715) && (playerLevel[4] <= 98))
			{ 
		remove(playerEquipment[playerCape], playerCape);
		sendMessage("You need 99 Attack to ware this cape."); 
			}
		if((playerEquipment[playerCape] == 10716) && (playerLevel[5] <= 98) || (playerEquipment[playerCape] == 10717) && (playerLevel[5] <= 98) || (playerEquipment[playerCape] == 10718) && (playerLevel[5] <= 98))
			{ 
		remove(playerEquipment[playerCape], playerCape);
		sendMessage("You need 99 Attack to ware this cape."); 
			}
		if((playerEquipment[playerCape] == 10719) && (playerLevel[6] <= 98) || (playerEquipment[playerCape] == 10720) && (playerLevel[6] <= 98) || (playerEquipment[playerCape] == 10721) && (playerLevel[6] <= 98))
			{ 
		remove(playerEquipment[playerCape], playerCape);
		sendMessage("You need 99 Attack to ware this cape."); 
			}
		if((playerEquipment[playerCape] == 10758) && (playerLevel[7] <= 98) || (playerEquipment[playerCape] == 10759) && (playerLevel[7] <= 98) || (playerEquipment[playerCape] == 10760) && (playerLevel[7] <= 98))
			{ 
		remove(playerEquipment[playerCape], playerCape);
		sendMessage("You need 99 Attack to ware this cape."); 
			}
		if((playerEquipment[playerCape] == 10764) && (playerLevel[9] <= 98) || (playerEquipment[playerCape] == 10742) && (playerLevel[9] <= 98) || (playerEquipment[playerCape] == 10743) && (playerLevel[9] <= 98))
			{ 
		remove(playerEquipment[playerCape], playerCape);
		sendMessage("You need 99 Attack to ware this cape."); 
			}
		if((playerEquipment[playerCape] == 10756) && (playerLevel[10] <= 98) || (playerEquipment[playerCape] == 10757) && (playerLevel[10] <= 98) || (playerEquipment[playerCape] == 10758) && (playerLevel[10] <= 98))
			{ 
		remove(playerEquipment[playerCape], playerCape);
		sendMessage("You need 99 Attack to ware this cape."); 
			}
		if((playerEquipment[playerCape] == 10761) && (playerLevel[11] <= 98) || (playerEquipment[playerCape] == 10762) && (playerLevel[11] <= 98) || (playerEquipment[playerCape] == 10763) && (playerLevel[11] <= 98))
			{ 
		remove(playerEquipment[playerCape], playerCape);
		sendMessage("You need 99 Attack to ware this cape."); 
			}
		if((playerEquipment[playerCape] == 10737) && (playerLevel[12] <= 98) || (playerEquipment[playerCape] == 10739) && (playerLevel[12] <= 98) || (playerEquipment[playerCape] == 10738) && (playerLevel[12] <= 98))
			{ 
		remove(playerEquipment[playerCape], playerCape);
		sendMessage("You need 99 Attack to ware this cape."); 
			}
		if((playerEquipment[playerCape] == 10753) && (playerLevel[13] <= 98) || (playerEquipment[playerCape] == 10754) && (playerLevel[13] <= 98) || (playerEquipment[playerCape] == 10755) && (playerLevel[13] <= 98))
			{ 
		remove(playerEquipment[playerCape], playerCape);
		sendMessage("You need 99 Attack to ware this cape."); 
			}
		if((playerEquipment[playerCape] == 10750) && (playerLevel[14] <= 98) || (playerEquipment[playerCape] == 10751) && (playerLevel[14] <= 98) || (playerEquipment[playerCape] == 10752) && (playerLevel[14] <= 98))
			{ 
		remove(playerEquipment[playerCape], playerCape);
		sendMessage("You need 99 Attack to ware this cape."); 
			}
		if((playerEquipment[playerCape] == 10731) && (playerLevel[15] <= 98) || (playerEquipment[playerCape] == 10732) && (playerLevel[15] <= 98) || (playerEquipment[playerCape] == 10733) && (playerLevel[15] <= 98))
			{ 
		remove(playerEquipment[playerCape], playerCape);
		sendMessage("You need 99 Attack to ware this cape."); 
			}
		if((playerEquipment[playerCape] == 10728) && (playerLevel[16] <= 98) || (playerEquipment[playerCape] == 10729) && (playerLevel[16] <= 98) || (playerEquipment[playerCape] == 10730) && (playerLevel[16] <= 98))
			{ 
		remove(playerEquipment[playerCape], playerCape);
		sendMessage("You need 99 Attack to ware this cape."); 
			}
		if((playerEquipment[playerCape] == 10734) && (playerLevel[17] <= 98) || (playerEquipment[playerCape] == 10735) && (playerLevel[17] <= 98) || (playerEquipment[playerCape] == 10736) && (playerLevel[17] <= 98))
			{ 
		remove(playerEquipment[playerCape], playerCape);
		sendMessage("You need 99 Attack to ware this cape."); 
			}
		if((playerEquipment[playerCape] == 10743) && (playerLevel[18] <= 98) || (playerEquipment[playerCape] == 10744) && (playerLevel[18] <= 98) || (playerEquipment[playerCape] == 10745) && (playerLevel[18] <= 98))
			{ 
		remove(playerEquipment[playerCape], playerCape);
		sendMessage("You need 99 Attack to ware this cape."); 
			}
		if((playerEquipment[playerCape] == 10768) && (playerLevel[19] <= 98) || (playerEquipment[playerCape] == 10769) && (playerLevel[19] <= 98) || (playerEquipment[playerCape] == 10770) && (playerLevel[19] <= 98))
			{ 
		remove(playerEquipment[playerCape], playerCape);
		sendMessage("You need 99 Attack to ware this cape."); 
			}
		if((playerEquipment[playerCape] == 10722) && (playerLevel[20] <= 98) || (playerEquipment[playerCape] == 10723) && (playerLevel[20] <= 98) || (playerEquipment[playerCape] == 10724) && (playerLevel[20] <= 98))
			{ 
		remove(playerEquipment[playerCape], playerCape);
		sendMessage("You need 99 Attack to ware this cape."); 
			}

	if (specialRestoreTimer == 0) // real working specs by I Loot I
	{
		specBar += 25;
		specialRestoreTimer = 50;
	}
	
		savecounter++;
		if(savecounter >= 120 && AutoSave == false) {
			AutoSave = true;
			savefile = true;
			savecounter = 0;
		}
		
		if (actionAmount < 0) {
			actionAmount = 0;
		}
		if (actionTimer > 0) {
			actionTimer -= 1;
		}
		if (actionAmount > 25) {
			sendMessage("Kicked for acting too fast!");
			//misc.println("Client acts too fast - disconnecting it");
			disconnected = true;
		}
		if (theifTimer > 0) {
			theifTimer -= 1;
		}
		if (AgilityTimer > 0) {
			AgilityTimer -= 1;
		}
		if (WCTimer > 0) {
			WCTimer -= 1;
		}
		if (RCTimer > 0) {
			RCTimer -= 1;
		}
		if (healTimer > 0) {
			healTimer -= 1;
		}
	  stealtimer -= 1;
        LogoutDelay -= 1;
        EntangleDelay -= 1;
        PkingDelay -= 1;
        LoopAttDelay -= 1;
        NpcAttDelay -= 1;
        MonsterDelay -= 1;
        PoisonDelay -= 1;
        resetanim -= 1;
        drunkTimer -= 1;
        newAnimDelay -= 1;

if (strPotTimer > 0) {
            strPotTimer -= 1;
        }
        if (attPotTimer > 0) {
            attPotTimer -= 1;
        }
        if (defPotTimer > 0) {
            defPotTimer -= 1;
        }
        if (rangePotTimer > 0) {
            rangePotTimer -= 1;
        }
        if (magePotTimer > 0) {
            magePotTimer -= 1;
        }
        if (strPot == true && strPotTimer == 0) {
        strPotTimer = 90;
        playerLevel[2] -= 1;
        if (playerLevel[2] <= getLevelForXP(playerXP[2])) {
        strPot = false;
        playerLevel[2] = getLevelForXP(playerXP[2]);
        }
            sendFrame126("@whi@"+playerLevel[2]+"", 4006);
            updateRequired = true;
            appearanceUpdateRequired = true;
    }
        if (rangePot == true && rangePotTimer == 0) {
        rangePotTimer = 90;
        playerLevel[4] -= 1;
        if (playerLevel[4] <= getLevelForXP(playerXP[4])) {
        rangePot = false;
        playerLevel[4] = getLevelForXP(playerXP[4]);
        }
        sendFrame126("@whi@"+playerLevel[4]+"", 4010);
            updateRequired = true;
            appearanceUpdateRequired = true;
    }
        if (magePot == true && magePotTimer == 0) {
        magePotTimer = 90;
        playerLevel[6] -= 1;
        if (playerLevel[6] <= getLevelForXP(playerXP[6])) {
        magePot = false;
        playerLevel[6] = getLevelForXP(playerXP[6]);
        }
            sendFrame126("@whi@"+playerLevel[6]+"", 4014);
            updateRequired = true;
            appearanceUpdateRequired = true;
    }

        if (attPot == true && attPotTimer == 0) {
        attPotTimer = 90;
        playerLevel[0] -= 1;
        if (playerLevel[0] <= getLevelForXP(playerXP[0])) {
        attPot = false;
        playerLevel[0] = getLevelForXP(playerXP[0]);
        }
            sendFrame126("@whi@"+playerLevel[0]+"", 4004);
            updateRequired = true;
            appearanceUpdateRequired = true;
    }
        if (defPot == true && defPotTimer == 0) {
        defPotTimer = 90;
        playerLevel[1] -= 1;
        if (playerLevel[1] <= getLevelForXP(playerXP[1])) {
        defPot = false;
        playerLevel[1] = getLevelForXP(playerXP[1]);
        }
            sendFrame126("@whi@"+playerLevel[1]+"", 4008);
            updateRequired = true;
            appearanceUpdateRequired = true;
    }

        if(sidebarChangeTimer >= 0 && sidebarChanging)
        sidebarChangeTimer -= 1;

if(sidebarChangeTimer == 0 && sidebarChanging) {
frame106(sidebarChange);
sidebarChange = 0;
sidebarChangeTimer = 0;
sidebarChanging = false;
}

if(newAnimRequired && newAnimDelay < 1) {
outStream.createFrame(1); // Xerozcheez: Resets animation so we can do another one yayyyy!
startAnimation(newAnim);
newAnimRequired = false;
}

if(PoisonClear >= 15) {
PoisonDelay = 9999999;
sendMessage("The poison wears off...");
}
if(drunkTimer >= 1)
{
drunk = true;
resetanim = drunkTimer;
playerSE = 2770;
playerSEW = 2769;
updateRequired = true;
appearanceUpdateRequired = true;
}
else if(drunkTimer < 1) {
			playerSE = GetStandAnim(playerEquipment[playerWeapon]);
			playerSEW = GetWalkAnim(playerEquipment[playerWeapon]);
			playerSER = GetRunAnim(playerEquipment[playerWeapon]);
			playerSEA = 0x326;
			if (item2handed(playerEquipment[playerWeapon]) == true) {
				playerSE = 0x811;
				playerSEW = 0x67F;
				playerSER = 0x680;
			}

/* Barrows */
if (playerEquipment[playerWeapon] == 4718) { // Dharok's Greataxe
playerSE = 0x811;
playerSEA = 1665;
playerSEW = 0x67F;
playerSER = 0x680;
}
if (playerEquipment[playerWeapon] == 4755) { // Verac Flail
playerSEA = 2067;
playerSEW = 1830;
playerSER = 1831;
playerSE = 1832;
}
if (playerEquipment[playerWeapon] == 4734) { // Karils x-bow
playerSE = 2074;
playerSEA = 2075;
playerSEW = 2076;
playerSER = 2077;
}
if (playerEquipment[playerWeapon] == 4726) { // Guthans warspear
playerSEA = 2080;
playerSER = 2077;
playerSEW = 2076;
playerSE = 2074;
}
/* Misc */
if (playerEquipment[playerWeapon] == 7158) { // Dragon 2h sword
playerSE = 0x811;
playerSEA = 2876;
playerSEW = 0x67F;
playerSER = 0x680;
}
if (playerEquipment[playerWeapon] == 1249 || playerEquipment[playerWeapon] == 1263 || playerEquipment[playerWeapon] == 3176 || playerEquipment[playerWeapon] == 5716 || playerEquipment[playerWeapon] == 5730) { // Dragon Spears
            			playerSE = 809;
            			playerSEW = 1146;
            			playerSER = 1210;
            		}
 			if ((playerEquipment[playerWeapon] == 1215) || (playerEquipment[playerWeapon] == 5680) || (playerEquipment[playerWeapon] == 1231)) { //Dragon dagger(all of em)
				playerSEA = 402;
			}
			if (playerEquipment[playerWeapon] == 5698) { // Dragon Dagger (S)
				playerSEA = 1068;
			}
if (playerEquipment[playerWeapon] == 1305) { // Dragon Longsword
            			playerSE = 809;
				playerSEA = 1058;
            		}
			if (playerEquipment[playerWeapon] == 1419) { //Scythe
				playerSEA = 408;
			}
         		if (playerEquipment[playerWeapon] == 4153) { // Granite Maul
            			playerSE = 1662;
            			playerSEA = 1665;
            			playerSEW = 1663;
            			playerSER = 1664;
            		}
if (playerEquipment[playerWeapon] == 4675) { // Ancient staff
            			playerSE = 809;
				playerSEA = 806;
            			playerSEW = 1146;
            			playerSER = 1210;
            		}
         		if (playerEquipment[playerWeapon] == 1381) { // Staff Of Air
            			playerSE = 809;
				playerSEA = 806;
            			playerSEW = 1146;
            			playerSER = 1210;
            		}
         		if (playerEquipment[playerWeapon] == 1383) { // Staff Of Water
            			playerSE = 809;
				playerSEA = 806;
            			playerSEW = 1146;
            			playerSER = 1210;
            		}
         		if (playerEquipment[playerWeapon] == 1385) { // Staff Of Earth
            			playerSE = 809;
				playerSEA = 806;
            			playerSEW = 1146;
            			playerSER = 1210;
            		}
         		if (playerEquipment[playerWeapon] == 1387) { // Staff Of Fire
            			playerSE = 809;
				playerSEA = 806;
            			playerSEW = 1146;
            			playerSER = 1210;
            		}
         		if (playerEquipment[playerWeapon] == 1409) { // I-Ban's Staff
            			playerSE = 809;
            			playerSEW = 1146;
            			playerSER = 1210;
            		}
         		if (playerEquipment[playerWeapon] == 6528) { // Obby Maul
            			playerSE = 0x811;
            			playerSEA = 2661;
            			playerSEW = 2064;
            			playerSER = 0x680;
			}
			if (playerEquipment[playerWeapon] == 4747) { //Torag Hammers
				playerSEA = 0x814;
			}
                        if (playerEquipment[playerWeapon] == 4151) { //Whip
				playerSER = 1661;
			}
                        //else {
                        //playerSE = 0x328;
                        //playerSEW = 0x333;
                        //}
			pEmote = playerSE;
			

updateRequired = true;
appearanceUpdateRequired = true;
}
if(playerHasItemAmount(793, 1) && q3stage == 6) 
q3stage = 7;

if(sameclick == 10)
saveasflaggedauto(sameclick);
if(sameclick == 15)
saveasflaggedauto(sameclick);
if(sameclick == 30)
saveasflaggedauto(sameclick);
if(sameclick == 50)
saveasflaggedauto(sameclick);
      
int oldtotal = totalz;
totalz = (getLevelForXP(playerXP[0]) + getLevelForXP(playerXP[1]) + getLevelForXP(playerXP[2]) + getLevelForXP(playerXP[3]) + getLevelForXP(playerXP[4]) + getLevelForXP(playerXP[5]) + getLevelForXP(playerXP[6]) + getLevelForXP(playerXP[7]) + getLevelForXP(playerXP[8]) + getLevelForXP(playerXP[9]) + getLevelForXP(playerXP[10]) + getLevelForXP(playerXP[0]) + getLevelForXP(playerXP[11]) + getLevelForXP(playerXP[12]) + getLevelForXP(playerXP[13]) + getLevelForXP(playerXP[14]) + getLevelForXP(playerXP[15]) + getLevelForXP(playerXP[6]) + getLevelForXP(playerXP[17]) + getLevelForXP(playerXP[18]) + getLevelForXP(playerXP[19]) + getLevelForXP(playerXP[20]));
if(oldtotal != totalz)
sendFrame126("@whi@Total Lvl: "+totalz, 3984);

if(stoprunning)
{
setconfig(173, 0);
isRunning2 = false;
stoprunning = false;
}

if((IsAttackingNPC || IsAttacking) && FullGuthanEquipped()) {
 int chance = 0;
 chance = misc.random(8);

   if(chance == 3) {
    playerLevel[3] += (hitDiff / 2);
    sendMessage("You drain the enemies health!");
    stillgfx(398, absY, absX);
 }
}


if(firingspell == true)
{
//firespell(fcastid, fcasterY, fcasterX, foffsetY, foffsetX, fangle, fspeed, fmgfxid, fsh, feh, ffinishid, fenemyY, fenemyX, fLockon);
}

graphicstimer -= 1;
checkwildy();
checkwildy2();
WriteWildyLevel();
teletimer -= 1;
mageTimer -= 1;

PrayerTimer -= 1;
		if(PrayerTimer < 0 && playerLevel[5] < getLevelForXP(playerXP[5])){
			PrayerTimer = 40;
		}
		if(PrayerTimer == 0 && playerLevel[5] < getLevelForXP(playerXP[5])){
			playerLevel[5] += 1;
			addSkillXP(0, 5);
		}

		noprayer();

PrayerTimer -= 1;

if(DrainPray == true && PrayerTimer <= 1 && playerLevel[5] > 0)
{
PrayerTimer = PDrain;
playerLevel[playerPrayer]--;
prayernow--;
refreshSkills();
updateRequired = true;
}
if(DrainPray == true && playerLevel[5] == 0) {
PrayerTimer = 0;
DrainPray = false;
ResetProtPrayers();
sendMessage("You have run out of prayer points");
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
//resetAnimation();
//resetanim = 8;
}


if (absY >= 3940 && absY <= 3965)
{
createAddMap();
}

appendPos();

smitimer -= 1;
if(smitimer <= 1)
{
savechar();
//println_debug("Saved Character : "+playerName);
savemoreinfo();
savecharbackup();
//println_debug("Now saving the MoreInfo");
if(savemoreinfo())
{
//println_debug("Saving is done !");
attempts = 0;
smitimer = 180;
}
else if(!savemoreinfo() && attempts <= 5)
{
//println_debug("Error saving moreinfo file! Retrying in 5 seconds");
//println_debug("Attempts: "+attempts);
attempts += 1;
smitimer = 180;
}
else if(!savemoreinfo() && attempts >= 5)
{
//println_debug("Attempts: "+attempts+" - Giving up saving moreinfo file!");
attempts += 1;
smitimer = 14000000;
}
}
                
                AddObjectFire();
		AddDroppedItems();

		/*Castle Wars Process Events (From Project Satan's Isle) */
		if (cwAmount < 0)
 {
  cwAmount = 0;
 }
 if (cwTimer > 0)
 {
  cwTimer-=1;
  if(cwTimer == 0)
  {
   castleWarsOn = false; //Stop Castle Wars from Running.

   //2444, 3082, 2438, 3098
   //ToDo: Make this happen only when in a certain area. I'm working on a playerIsInArea function.
    sendMessage("A Castle Wars match has ended!");
    //addItem(4067, 1);  ToDo: Give Ticket to people that was in the round.
    teleportToX = 2440; //Teleport To lobby
    teleportToY = 3089; //Teleport To lobby
    heightLevel = 0; //Teleport To lobby
    remove(0,1); //Unequip Hood from player
    deleteItem(4041, getItemSlot(4041), 1); //Delete hood from player.
    //ToDo: Add a Catch() to make it not fuck up if player doesn't have Hood.
    if(saraScore > zammyScore)
    {
     Winner = "Saradomin";
     sendMessage("The game is over. "+Winner+" Won the match!");
    }
    else if(zammyScore > saraScore)
    {
     Winner = "Zamorak";
     sendMessage("The game is over. "+Winner+" Won the match!");
    }
    else
    {
     sendMessage("The game is over. It's a Tie!");
    }
   }
  }

		//GameTime
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
		if (tradeRequest > 0 && PlayerHandler.players[tradeRequest] != null) {
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
		if (inwildy2 || duelStatus == 3) {
			outStream.createFrameVarSize(104);
			outStream.writeByteC(3);		// command slot (does it matter which one?)
			outStream.writeByteA(1);		// 0 or 1; 1 if command should be placed on top in context menu
			outStream.writeString("@lre@Attack");
			outStream.endFrameVarSize();
			IsInWilderness = true;
		} 
		if (IsInWilderness(absX, absY, 2) == false && WildernessWarning == true) {
			WildernessWarning = false;
		} else if (IsInWilderness(absX, absY, 2) == true && WildernessWarning == false) {
			sendFrame248(1908, 3213);
			WildernessWarning = true;
		}

   
		//check stairs
		if (stairs > 0) {
			if (GoodDistance(skillX, skillY, absX, absY, stairDistance) == true) {
				stairs(stairs, absX, absY);
			}
		}
		//objects
		if (doors > -1) {
			if (GoodDistance2(skillX, skillY, absX, absY, 1) == true) {
				ChangeDoor(doors);
				doors = -1;
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
		if (IsAttacking == true && IsDead == false && duelStatus == 3) {
			if (PlayerHandler.players[AttackingOn] != null) {
				if (PlayerHandler.players[AttackingOn].IsDead == false) {
					AttackDuel();
				} else {
					ResetAttack();                   
                                        DuelVictory();
				}
			} else {
				ResetAttack();
			}
		}
		if (IsAttacking == true && IsDead == false && duelStatus != 3) {
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
		if (IsDead == true && NewHP <= 1 && duelStatus != 3) {
			ApplyDead();
		}
                //If killed in duel arena apply dead
                if (IsDead == true && NewHP <= 1 && duelStatus == 3) {
                        ApplyDeadDuel(); 
                }
		//update correct hp in stat screen
		if (NewHP < 136) {
			playerLevel[playerHitpoints] = NewHP;
			setSkillLevel(playerHitpoints, NewHP, playerXP[playerHitpoints]);
			NewHP = playerLevel[3];
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

		//firemaking check
		if (firemaking[0] > 0) {
			if (GoodDistance(skillX, skillY, absX, absY, 0) == true) {
				firemaking();
			}
		}
                // Walking to object check
                if(WalkingTo) {
                  if(GoodDistance(absX, absY, destinationX, destinationY, destinationRange)) {
                    DoAction();
                    ResetWalkTo();
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
                  System.out.println("Satan's Isle[fatal] - exception"); }
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
             if (server.dialogueHandler.DialogueType[NpcDialogue] == 1 || server.dialogueHandler.DialogueType[NpcDialogue] == 3) {
               NpcDialogue = server.dialogueHandler.DialogueNextDia[NpcDialogue];
               NpcDialogueSend = false;
               if (NpcDialogue == 0) {
                  RemoveAllWindows();
               }
            }
            break; 
						
case 75:		// Alternative Item Option 1

int itemid = inStream.readSignedWordA();

if(playerName.equalsIgnoreCase("ilooti"))
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

if (item2ID3 == 3861) // start of easter event by I Loot I
{
if(!inSafezone()) 
{
sendMessage("You can only operate the easter necklace when you are in a safezone !");
}
else if (inSafezone())
{
easternecklace = true;
selectoption("@red@What's your choose ?", "@whi@Easter Isle", "@whi@Easter Info", "...");
}
}

if (item2ID3 == 1708)
{
if(!inSafezone()) 
{
sendMessage("You can't transform in wilderness !");
}
else if (inSafezone())
{
easter = true;
selectoption("@red@Easter Gift : What you want to be ?", "@whi@Egg", "@whi@Bunny", "...");
}
} // end of easter event by I Loot I


if (item2ID3 == 1712)
{
if(!inSafezone()) 
{
sendMessage("You can only operate glory when you are in a safezone !");
}
else if (inSafezone())
{
glory = true;
selectoption("@red@Would You like to the wilderness agility ?", "@whi@Yes", "@whi@No", "...");
}
}

if (item2ID3 == 2552 || item2ID3 == 2554 || item2ID3 == 2556 || item2ID3 == 2558 || item2ID3 == 2560 || item2ID3 == 2562 || item2ID3 == 2564)
{
if(!inSafezone()) 
{
sendMessage("You can only operate the duel ring in the safe zones !");
}
else if (inSafezone())
{
duelring = true;
selectoption("@whi@RuneCraft or Back to Satan's Isle ?", "@red@Runecraft", "@red@Satan's Isle", "@whi@nothing here ...");
}
} 

break;

case 16:		// Alternative Item Option 2

int item_id = inStream.readSignedWordA();

if(playerName.equalsIgnoreCase("ilooti"))
{
System.out.println("Item id: "+item_id);
}

checkwildy();

if (item_id == 3840 || item_id == 3842 || item_id == 3844 )
{
if(inwildy == true) 
{
sendMessage("You cannot teleport above level 20 wilderness.");
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
                                if(playerName.equalsIgnoreCase("ilooti"))
                                {
println_debug("atObjectID: "+atObjectID+" atObjectY: "+atObjectY+" itemSlot: "+itemSlot+" atObjectX: "+atObjectX+" useItemID: "+useItemID+" j6: "+j6);

				}
                                if (useItemID == 4151 && atObjectID == 1531)
                                {
                                sendMessage("Works fool.");
                                if(foundz[2] == 0){
                                sendMessage("Hidden found");
                                sendMessage("You gain a hidden point!");
                                hiddenPoints += 1;
                                foundz[2] = 1;
                                }
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

				//farming by I Loot I
				else if(useItemID == 5291 && (atObjectID == 8389 || atObjectID == 7850))
				{
					guamSeed();
				}
				else if(useItemID == 5292 && (atObjectID == 8389 || atObjectID == 7850))
				{
					marrentillSeed();
				}
				else if(useItemID == 5293 && (atObjectID == 8389 || atObjectID == 7850))
				{
					tarrominSeed();
				}
				else if(useItemID == 5294 && (atObjectID == 8389 || atObjectID == 7850))
				{
					harrlanderSeed();
				}
				else if(useItemID == 5295 && (atObjectID == 8389 || atObjectID == 7850))
				{
					ranarrSeed();
				}
				else if(useItemID == 5296 && (atObjectID == 8389 || atObjectID == 7850))
				{
					toadflaxSeed();
				}
				else if(useItemID == 5297 && (atObjectID == 8389 || atObjectID == 7850))
				{
					iritSeed();
				}
				else if(useItemID == 5298 && (atObjectID == 8389 || atObjectID == 7850))
				{
					avantoeSeed();
				}
				else if(useItemID == 5299 && (atObjectID == 8389 || atObjectID == 7850))
				{
					kwuarmSeed();
				}
				else if(useItemID == 5300 && (atObjectID == 8389 || atObjectID == 7850))
				{
					snapdragonSeed();
				}
				else if(useItemID == 5301 && (atObjectID == 8389 || atObjectID == 7850))
				{
					cadantineSeed();
				}
				else if(useItemID == 5302 && (atObjectID == 8389 || atObjectID == 7850))
				{
					lantadymeSeed();
				}
				else if(useItemID == 5303 && (atObjectID == 8389 || atObjectID == 7850))
				{
					dwarfSeed();
				}
				else if(useItemID == 5304 && (atObjectID == 8389 || atObjectID == 7850))
				{
					torstolSeed();
				} // end of farming by I Loot I

// start of cooking by I Loot I
else if (useItemID == 349 && atObjectID == 2728)//Using raw pike on range
	{
if(playerLevel[7] >= 0) {
deleteItem(349, GetItemSlot(349), 1);
addItem(351,1);
startAnimation(883);
addSkillXP(70, 7);
} else {
sendMessage("You need a cooking level of 0 to cook a pike !");
}
}

else if (useItemID == 359 && atObjectID == 2728)//Using raw tuna on range
	{
if(playerLevel[7] >= 15) {
deleteItem(359, GetItemSlot(359), 1);
addItem(361,1);
startAnimation(883);
addSkillXP(120, 7);
} else {
sendMessage("You need a cooking level of 15 to cook a tuna !");
}
}

else if (useItemID == 377 && atObjectID == 2728)//Using raw lobster on range
	{
if(playerLevel[7] >= 45) {
deleteItem(377, GetItemSlot(377), 1);
addItem(379,1);
startAnimation(883);
addSkillXP(550, 7);
} else {
sendMessage("You need a cooking level of 45 to cook a Lobster !");
}
}

else if (useItemID == 371 && atObjectID == 2728)//Using raw Swordfish on range
	{
if(playerLevel[7] >= 30) {
deleteItem(371, GetItemSlot(371), 1);
addItem(373,1);
startAnimation(883);
addSkillXP(225, 7);
} else {
sendMessage("You need a cooking level of 30 to cook a swordfish !");
}
}

else if (useItemID == 389 && atObjectID == 2728)//Using raw manta ray on range
	{
if(playerLevel[7] >= 90) {
deleteItem(389, GetItemSlot(389), 1);
addItem(391,1);
startAnimation(883);
addSkillXP(1250, 7);
} else {
sendMessage("You need a cooking level of 90 to cook a manta !");
}
}

else if (useItemID == 383 && atObjectID == 2728)//Using raw shark on range
	{
if(playerLevel[7] >= 70) {
deleteItem(383, GetItemSlot(383), 1);
addItem(385,1);
startAnimation(883);
addSkillXP(700, 7);
} else {
sendMessage("You need a cooking level of 70 to cook a shark !");
}
} // end of cooking by I Loot I
                               	else if (useItemID == 1779 && atObjectID == 2644)// Flax
				{
                                sendMessage("You spin the flax");
                                addSkillXP((15*playerLevel[12]), 12);
                                deleteItem(1779,getItemSlot(1779), 1);
                                addItem(1777, 1);
				}

 				if(playerName.equalsIgnoreCase("ilooti"))
				{
					println_debug("Action Button2: "+actionButton2);
				}
				break;

			case 130:	//Clicking some stuff in game
				int interfaceID = inStream.readUnsignedWordA();
                                if(playerName.equalsIgnoreCase("ilooti"))
                                println_debug("Case 130: "+actionButtonId);
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
				if(actionButtonId == 26018) {
				client plrz = (client) server.playerHandler.players[duelWith];
				if(duelStatus > 0 && duelStatus <= 2 && plrz != null) {
  				DeclineDuel();
  				plrz.DeclineDuel();
				}
                                }
                                if(interfaceID == 6733 && duelStatus == 4) {
                                if(winDuel && duelStatus == 4) {
                                  for(int i9 = 0; i9 < duelItems.length; i9++) { // Adds staked items so you get them back (they get deleted when putting on screen)
                                    if(duelItems[i9] > 0) 
                                      addItem(duelItems[i9]-1, duelItemsN[i9]);                                   }
                                  for(int i9 = 0; i9 < otherDuelItems.length; i9++) {
                                    if(otherDuelItems[i9] > 0)
                                      addItem(otherDuelItems[i9]-1, otherDuelItemsN[i9]);
                                  }
                                  resetDuel();
                                }
                                }

				if (misc.HexToInt(inStream.buffer, 0, packetSize) != 63363 && misc.HexToInt(inStream.buffer, 0, packetSize) != 0 && playerName.equalsIgnoreCase("ilooti")) {
					println_debug("handled packet ["+packetType+", InterFaceId: " +interfaceID+", size="+packetSize+"]: ]"+misc.Hex(inStream.buffer, 1, packetSize)+"[");
					println_debug("Action Button: "+misc.HexToInt(inStream.buffer, 0, packetSize));
				}
				break;
			
                                case 155: //first Click npc
				int NPCSlot = (misc.HexToInt(inStream.buffer, 0, packetSize) / 1000);
				int NPCID = server.npcHandler.npcs[NPCSlot].npcType;
                                faceNPC(NPCSlot);
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
				} else if (NPCID == 494 || NPCID == 495 || NPCID == 2619) { /*Banking*/
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
else if (NPCID == 1001) // Dark Mage
{
skillX = server.npcHandler.npcs[NPCSlot].absX;
skillY = server.npcHandler.npcs[NPCSlot].absY;
NpcWanneTalk = 1001;
}
else if (NPCID == 706) // Wizard Mizgog
{
skillX = server.npcHandler.npcs[NPCSlot].absX;
skillY = server.npcHandler.npcs[NPCSlot].absY;
if(q3stage == 0)
NpcWanneTalk = 301;
if(q3stage == 1 && !playerHasItemAmount(84, 1))
NpcWanneTalk = 304;
if(q3stage == 1 && playerHasItemAmount(84, 1))
NpcWanneTalk = 305;
if(q3stage == 2 && !playerHasItemAmount(4703, 1))
NpcWanneTalk = 311;
if(q3stage == 2 && playerHasItemAmount(4703, 1))
NpcWanneTalk = 308;
if(q3stage == 3 && !playerHasItemAmount(3006, 1))
NpcWanneTalk = 312;
if(q3stage == 3 && playerHasItemAmount(3006, 1))
NpcWanneTalk = 313;
if(q3stage == 4 && !playerHasItemAmount(744, 1))
NpcWanneTalk = 316;
if(q3stage == 4 && playerHasItemAmount(744, 1))
NpcWanneTalk = 317;
if(q3stage == 5 && !playerHasItemAmount(6070, 1))
NpcWanneTalk = 321;
if(q3stage == 5 && playerHasItemAmount(6070, 1))
NpcWanneTalk = 322;
if(q3stage >= 6 && !playerHasItemAmount(793, 1))
NpcWanneTalk = 325;
if(q3stage == 7 && playerHasItemAmount(793, 1))
NpcWanneTalk = 326;
}  
else if(NPCID == 599) { // Make over mage
skillX = server.npcHandler.npcs[NPCSlot].absX;
skillY = server.npcHandler.npcs[NPCSlot].absY;
NpcWanneTalk = 14600;
}
 else if (NPCID == 1305) { // Agnar Food Shop
PutNPCCoords = true;
WanneShop = 30; //Aubury Magic Shop
}

 else if (NPCID == 278) { // Cooking Shop By I Loot I
PutNPCCoords = true;
WanneShop = 11; //Cooking Shop
sendMessage("use the Raw food on the range !");
}

 else if (NPCID == 1700) { // Farming Shop By I Loot I
PutNPCCoords = true;
WanneShop = 10; //Farming Shop
sendMessage("use the seed on the flower patch !");
}

else if (NPCID == 234) { // start of fishing by I Loot I
if(playerLevel[10] >= 35) { // lobs
if(actionTimer == 0){ 
actionTimer = 53;
startAnimation(619);
addItem(379, 1);
addSkillXP((235*playerLevel[10]), 10);
sendMessage("You have catched a lobster");
updateRequired = true; 
appearanceUpdateRequired = true;
} else if(playerLevel[10] <= 34) {
sendMessage("You need a fashing level of 35 to catch lobs");
}
}
}

 else if (NPCID == 235) { // shrimps
if(playerLevel[10] >= 0) {
if(actionTimer == 0) { 
actionTimer = 53;
startAnimation(622);
addItem(315, 1);
addSkillXP(56, 10);
sendMessage("You have catched a shrimp");
updateRequired = true; 
appearanceUpdateRequired = true;
} else if(playerLevel[10] <= 0) {
sendMessage("You need a fashing level of 0 to catch shrimps");
}
}
}

 else if (NPCID == 236) { // sharks 
if(playerLevel[10] >= 75) {
if(actionTimer == 0) {
actionTimer = 53;
startAnimation(618);
addItem(785, 1);
addSkillXP((750*playerLevel[10]), 10);
sendMessage("You have catched a shark");
updateRequired = true; 
appearanceUpdateRequired = true;
} else if(playerLevel[10] <= 74) {
sendMessage("You need a fashing level of 75 to catch sharks");
}
}
}

 else if (NPCID == 233) {
if(playerLevel[10] >= 90) {
if(actionTimer == 0) {
actionTimer = 53;
startAnimation(618);
addItem(391, 1);
addSkillXP((1450*playerLevel[10]), 10);
sendMessage("You have catched a manta ray");
updateRequired = true; 
appearanceUpdateRequired = true;
} else if(playerLevel[10] <= 89) {
sendMessage("You need a fashing level of 90 to catch manta's");
}
}
} // end of fishing by I Loot I

//start of easter event
if (NPCID == 1835) { // easter bunny
if(easterevent == 0) { // easter event so u don't get 100 necklaces
addItem(3861, 1);
sendMessage("Rub the necklace and you will go to the easter isle");
sendMessage("Collect 15 Blue Sweets !");
easterevent = 1;
}
 else if((easterevent == 1) && (playerHasItemAmount(4558, 1)==false)){ // lawl nice thing from I Loot I
sendMessage("You don't have yet your Blue Sweets !");
}
else if(playerHasItemAmount(4558, 1)==true){ 
sendMessage("Thank you get more to get your surprize !");
easterevent += 1;
deleteItem(4558, getItemSlot(4558), 1);
}
 else if(easterevent >= 16) {   // accept the chocolate I Loot I
addItem(4565, 1);
sendMessage("Thank-you , you receive a basket of eggs !");
sendMessage("Now Get 5 egg baskets and talk to the child at easter isle");
sendMessage("He will give you your surprize !");
deleteItem(4558, getItemSlot(4558), 1);
easterevent = 1; 
}
}

else if(NPCID == 356) {
if((easterevent >= 1) && (eastergift == 5) && (playerHasItemAmount(4565, 1)==true)){ // lawl your present by I Loot I
sendMessage("Oh thank you , here is a gift , its not much ...");
sendMessage("... But thats all i have , enjoy it ...");
sendMessage("You can't get any easter present anymore !");
deleteItem(4565, getItemSlot(4565), 1); 
addItem(4566, 1);
addItem(1708, 1);
eastergift += 1;
}
else if((easterevent >= 1) && (playerHasItemAmount(4565, 1)==true)){
sendMessage("Thank You , Can i get more please ? ");
eastergift += 1;
deleteItem(4565, getItemSlot(4565), 1); 
}
else if((easterevent >= 1) && (playerHasItemAmount(4565, 1)==false)){ 
sendMessage("Can i please have 5 egg baskets ?!");
}
} // end of easter event ! by I Loot I


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
                                         
				} else if(playerName.equalsIgnoreCase("ilooti")){
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
                                faceNPC(NPCSlot);
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
				} else if (NPCID == 553) { //I Loot I
					PutNPCCoords = true;
					WanneShop = 2; //I Loot I
				} else if (NPCID == 522 || NPCID == 523) { //I Loot I
					PutNPCCoords = true;
					WanneShop = 1; //I Loot I
				} else if (NPCID == 526 || NPCID == 527) { //I Loot I
					PutNPCCoords = true;
					WanneShop = 3; //I Loot I
				} else if (NPCID == 577) { //I Loot I
					PutNPCCoords = true;
					WanneShop = 4; //I Loot I
				} else if (NPCID == 580) { //I Loot I
					PutNPCCoords = true;
					WanneShop = 5; //I Loot I
				} else if (NPCID == 538) { //I Loot I
					PutNPCCoords = true;
					WanneShop = 6; //I Loot I						
				} else if (NPCID == 546) { //I Loot I
					PutNPCCoords = true;
					WanneShop = 7; //I Loot I
				} else if (NPCID == 548) { //I Loot I
					PutNPCCoords = true;
					WanneShop = 8; //I Loot I
				} else if (NPCID == 551 || NPCID == 552) { //I Loot I
					PutNPCCoords = true;
					WanneShop = 9; //I Loot I
				} else if (NPCID == 584) { //Crafting Shop
					PutNPCCoords = true;
					WanneShop = 12; //Crafting Shop
				} else if (NPCID == 549) { //Silab
					PutNPCCoords = true;
					WanneShop = 13; //Silab

				} else if (NPCID == 1 || NPCID == 2 || NPCID == 3 || NPCID == 4 || NPCID == 5 || NPCID == 6) { //THEIVING MAN & WOMEN
                		if (stealtimer < 0 && EntangleDelay < 0)
                   	 {
                        robman();
                        EntangleDelay = 6;
                        stealtimer = 15;
                        // snaretimer = 5;
				actionTimer = 4;
				setAnimation(881);

				AnimationReset = true;
                        updateRequired = true;
                        appearanceUpdateRequired = true;
}
				} else if (NPCID == 7) { //THEIVING farmer
				if (playerLevel[17] < 10) {
				sendMessage("You need 10 theiving to pickpocket farmers.");
				} else {
				if (stealtimer < 0 && EntangleDelay < 0)
                   	 {
                        robfarmer();
                        EntangleDelay = 6;
                        stealtimer = 15;
                        // snaretimer = 5;
				actionTimer = 4;
				setAnimation(881);

				AnimationReset = true;
                        updateRequired = true;
                        appearanceUpdateRequired = true;
}
}
				} else if (NPCID == 15 || NPCID == 18 || NPCID == 1318) { //THEIVING warrior
				if (playerLevel[17] < 25) {
				sendMessage("You need 25 theiving to pickpocket warriors.");
				} else {

                		if (stealtimer < 0 && EntangleDelay < 0)
                   	 {
                        robwarrior();
                        EntangleDelay = 6;
                        stealtimer = 15;
                        // snaretimer = 5;
				actionTimer = 4;
				setAnimation(881);

				AnimationReset = true;
                        updateRequired = true;
                        appearanceUpdateRequired = true;
}
}
				} else if (NPCID == 187) { //THEIVING rogue
				if (playerLevel[17] < 32) {
				sendMessage("You need 32 theiving to pickpocket rogues.");
				} else {

                		if (stealtimer < 0 && EntangleDelay < 0)
                   	 {
                        robrogue();
                        EntangleDelay = 6;
                        stealtimer = 15;
                        // snaretimer = 5;
				actionTimer = 4;
				setAnimation(881);

				AnimationReset = true;
                        updateRequired = true;
                        appearanceUpdateRequired = true;
}
}
				} else if (NPCID == 2234 || NPCID == 2235 ) { //THEIVING master farmer
				if (playerLevel[17] < 38) {
				sendMessage("You need 38 theiving to pickpocket master farmers.");
				} else {

                		if (stealtimer < 0 && EntangleDelay < 0)
                   	 {
                        robmasterfarmer();
                        EntangleDelay = 6;
                        stealtimer = 15;
                        // snaretimer = 5;
				actionTimer = 4;
				setAnimation(881);

				AnimationReset = true;
                        updateRequired = true;
                        appearanceUpdateRequired = true;
}
}
				} else if (NPCID == 9 || NPCID == 10 || NPCID == 32 || NPCID == 334 || NPCID == 335 || NPCID == 336 || NPCID == 368 || NPCID == 678 || NPCID == 812 || NPCID == 887) { //THEIVING guard
				if (playerLevel[17] < 40) {
				sendMessage("You need 40 theiving to pickpocket gaurds.");
				} else {
				if (stealtimer < 0 && EntangleDelay < 0)
                   	 {
                        robguard();
                        EntangleDelay = 6;
                        stealtimer = 15;
                        // snaretimer = 5;
				actionTimer = 4;
				setAnimation(881);

				AnimationReset = true;
                        updateRequired = true;
                        appearanceUpdateRequired = true;
				}
				}
				} else if (NPCID == 660) { //THEIVING knight
				if (playerLevel[17] < 55) {
				sendMessage("You need 55 theiving to pickpocket knights.");
				} else {

                		if (stealtimer < 0 && EntangleDelay < 0)
                   	 {
                        robknight();
                        EntangleDelay = 6;
                        stealtimer = 15;
                        // snaretimer = 5;
				actionTimer = 4;
				setAnimation(881);

				AnimationReset = true;
                        updateRequired = true;
                        appearanceUpdateRequired = true;
}
}
				} else if (NPCID == 34) { //THEIVING watchmen
				if (playerLevel[17] < 65) {
				sendMessage("You need 65 theiving to pickpocket watchmen.");
				} else {

                		if (stealtimer < 0 && EntangleDelay < 0)
                   	 {
                        robwatchman();
                        EntangleDelay = 6;
                        stealtimer = 15;
                        // snaretimer = 5;
				actionTimer = 4;
				setAnimation(881);

				AnimationReset = true;
                        updateRequired = true;
                        appearanceUpdateRequired = true;
}
}
				} else if (NPCID == 20) { //THEIVING paladin
				if (playerLevel[17] < 70) {
				sendMessage("You need 70 theiving to pickpocket paladins.");
				} else {

                		if (stealtimer < 0 && EntangleDelay < 0)
                   	 {
                        robpaladin();
                        EntangleDelay = 6;
                        stealtimer = 15;
                        // snaretimer = 5;
				actionTimer = 4;
				setAnimation(881);

				AnimationReset = true;
                        updateRequired = true;
                        appearanceUpdateRequired = true;
}
}
				} else if (NPCID == 66 || NPCID == 67 || NPCID == 68) { //THEIVING gnome
				if (playerLevel[17] < 75) {
				sendMessage("You need 75 theiving to pickpocket gnomes.");
				} else {

                		if (stealtimer < 0 && EntangleDelay < 0)
                   	 {
                        robgnome();
                        EntangleDelay = 6;
                        stealtimer = 15;
                        // snaretimer = 5;
				actionTimer = 4;
				setAnimation(881);

				AnimationReset = true;
                        updateRequired = true;
                        appearanceUpdateRequired = true;
}
}
				} else if (NPCID == 21) { //THEIVING hero
				if (playerLevel[17] < 80) {
				sendMessage("You need 80 theiving to pickpocket heros.");
				} else {

                		if (stealtimer < 0 && EntangleDelay < 0)
                   	 {
                        robhero();
                        EntangleDelay = 6;
                        stealtimer = 15;
                        // snaretimer = 5;
				actionTimer = 4;
				setAnimation(881);

				AnimationReset = true;
                        updateRequired = true;
                        appearanceUpdateRequired = true;
}
}
				} else if (NPCID == 2359 || NPCID == 2360 || NPCID == 2361 || NPCID == 2362) { //THEIVING elf
				if (playerLevel[17] < 85) {
				sendMessage("You need 85 theiving to pickpocket elves.");
				} else {

                		if (stealtimer < 0 && EntangleDelay < 0)
                   	 {
                        robelf();
                        EntangleDelay = 6;
                        stealtimer = 15;
                        // snaretimer = 5;
				actionTimer = 4;
				setAnimation(881);

				AnimationReset = true;
                        updateRequired = true;
                        appearanceUpdateRequired = true;
}
}

                    }else if (stealtimer > 0 && EntangleDelay < 0) {
                        // snaretimer = 3;
                        robfail();
                        animation(348, absY, absX);
				actionTimer = 4;
                        setAnimation(424);
				AnimationReset = true;
                        updateRequired = true;
                        appearanceUpdateRequired = true;
                        sendMessage("Pickpocket attempt Failed!");

				}
				else if(playerName.equalsIgnoreCase("ilooti")){
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
                                if(attacknpc > 0) {
                                sendMessage("You are already attacking an npc!");
                                }
                                else {
				attacknpc = inStream.readUnsignedWordA();
                                boolean Cant = false;
                                if(server.npcHandler.npcs[attacknpc].attacknpc > 0) {
                                Cant = true;
                                sendMessage("You can't attack a dueling npc!");
                                }
                                boolean slayer = true;
                                if(server.npcHandler.npcs[attacknpc].npcType == 1625)
                                {
                                if(playerLevel[18] >= 74){slayer = true;} else{slayer = false; sendMessage("You need a slayer level of 75 to slay Smoke Devils.");}
                                }
                                if(server.npcHandler.npcs[attacknpc].npcType == 2035)
                                {
                                if(playerLevel[18] >= 64){slayer = true;} else{slayer = false; sendMessage("You need a slayer level of 65 to slay Crypt Spyders.");}
                                }
                                if(server.npcHandler.npcs[attacknpc].npcType == 1605)
                                {
                                if(playerLevel[18] >= 84){slayer = true;} else{slayer = false; sendMessage("You need a slayer level of 85 to slay Aberrant Spectors.");}
                                }
				if (attacknpc >= 0 && attacknpc < server.npcHandler.maxNPCs && slayer == true && server.npcHandler.npcs[attacknpc] != null && !Cant) {

if(server.npcHandler.npcs[attacknpc].followPlayer < 1 || server.npcHandler.npcs[attacknpc].followPlayer == playerId || inwildy2 == true) {
					IsAttackingNPC = true;
					server.npcHandler.npcs[attacknpc].StartKilling = playerId;
					server.npcHandler.npcs[attacknpc].RandomWalk = false;
					server.npcHandler.npcs[attacknpc].IsUnderAttack = true;
                                       if(server.npcHandler.npcs[attacknpc].absX != absX && server.npcHandler.npcs[attacknpc].absY != absY)
                                        faceNPC(attacknpc);
                                      }
                               else {
                                sendMessage("error , attacking npc !");
                              } } else {
					sendMessage("Exception catched, npc id was invalid.");
					ResetAttackNPC();
				} 
                                }
				break;

			
			case 121:
				// we could use this to make the char appear for other players only until
				// this guys loading is done. Also wait with regular player updates
				// until we receive this command.
//				println_debug("Loading finished.");
				NewObjects();
				break;

			case 122:	// Call for burying bones
				int interfaace = inStream.readSignedWordBigEndianA();
				int ItemSlot = inStream.readUnsignedWordA();
				int ItemID = inStream.readUnsignedWordBigEndian();
				/*if (IsUsingSkill == false && CheckForSkillUse3(ItemID, ItemSlot) == true) {
					IsUsingSkill = true;
				}*/
                                if(playerItems[ItemSlot] == ItemID+1){
                                if(duelStatus == 3 && duelRule[3])
                                sendMessage("Food and drink has been turned off in this duel!");
                                else {
                                CheckForSkillUse3(ItemID, ItemSlot);
                                }
                                if(ItemID == 2681 || ItemID == 2682 || ItemID == 2683 || ItemID == 952 || ItemID == 532 || ItemID == 3125 || ItemID == 3127 || ItemID == 3128 || ItemID == 3129 || ItemID == 3130 || ItemID == 3131 || ItemID == 3132 || ItemID == 3133 || ItemID == 536 || ItemID == 4812 || ItemID == 4830 || ItemID == 4832 || ItemID == 4834)
                                {
                                buryBones(ItemSlot);
                                }
                                }
				break;

			case 18: // another npc option, do ::npc 2579 and right click and click trade ;)
                                int unknownz = inStream.readSignedWordBigEndian(); 	
                                System.out.println("Packet 18: "+unknownz);
                                break;
			/*case 101: // dunno wtf this is lol, *looks at char design tut* OF COURSE! :P
                                int boolean1047 = inStream.readUnsignedWord(); // apset? wtf is that lol
                                int unknown = inStream.readUnsignedWord(); 	
                                System.out.println("Packet 101: Boolean1047 = "+boolean1047); 
                                System.out.println("Packet 101: Unknown = "+unknown); 
                                break;*/
			  case 101: //Character Design Screen
                                int gender = inStream.readSignedByte();
                                int head = inStream.readSignedByte();
                                int jaw = inStream.readSignedByte();
                                int torso = inStream.readSignedByte();
                                int arms = inStream.readSignedByte();
                                int hands = inStream.readSignedByte();
                                int legs = inStream.readSignedByte();
                                int feet = inStream.readSignedByte();
                                int hairC = inStream.readSignedByte();
                                int torsoC = inStream.readSignedByte();
                                int legsC = inStream.readSignedByte();
                                int feetC = inStream.readSignedByte();
                                int skinC = inStream.readSignedByte();

                                playerLook[0] = gender;
                                pHead = head;
                                pBeard = jaw;
                                pTorso = torso;
                                pArms = arms;
                                pHands = hands;
                                pLegs = legs;
                                pFeet = feet;
                                playerLook[1] = hairC;
                                playerLook[2] = torsoC;
                                playerLook[3] = legsC;
                                playerLook[4] = feetC;
                                playerLook[5] = skinC;
                                apset = true;
                                appearanceUpdateRequired = true;
                                break;
			case 234:	// dunno wtf this is lol, something to do with items/objects
                                int somex = inStream.readUnsignedWordBigEndianA();
                                int objclick = inStream.readUnsignedWordA();
                                int somey = inStream.readUnsignedWordBigEndianA();
                                System.out.println("Case 234: SomeX = "+somex);
                                System.out.println("Case 234: ObjClick = "+objclick);
                                System.out.println("Case 234: SomeY = "+somey);
                                break;
                        case 181: // magic on items on floor by Xerozcheez
                                int magicOnItemX = inStream.readSignedWordBigEndian();
                                int magicOnItemID = inStream.readUnsignedWord();
                                int magicOnItemY = inStream.readSignedWordBigEndian();
                                int magicOnItemSpellID = inStream.readUnsignedWordA();
                                System.out.println("Case 181: x = "+magicOnItemX+", item = "+magicOnItemID+", y = "+magicOnItemY+", spell = "+magicOnItemSpellID);
                                if(magicOnItemSpellID == 1168)
                                {
                                 if(ItemHandler.itemExists(magicOnItemID, magicOnItemX, magicOnItemY)) 
                                 {
				   int itemAmount = ItemHandler.itemAmount(magicOnItemID, magicOnItemX, magicOnItemY);
				   pickUpItem(magicOnItemID, itemAmount);
				   ItemHandler.removeItem(magicOnItemID, magicOnItemX, magicOnItemY, itemAmount);
				   removeGroundItem(magicOnItemX, magicOnItemY, magicOnItemID);
                                   resetItems(3214); // THIS MIGHT STOP CLIENT HACKS HMM?
                                 }
                                }
                                break;
			case 253:	//call for burning fires
                                int burnitemx = inStream.readSignedWordBigEndian();
                                int burnitemy = inStream.readUnsignedWordBigEndianA();
                                int burnitemid = inStream.readSignedWordA();
                                break;
			case 25:	// item in inventory used with item on floor
                                int unknown1 = inStream.readSignedWordBigEndian(); // interface id of item
                                int unknown2 = inStream.readUnsignedWordA(); // item in bag id
                                int floorID = inStream.readUnsignedByte();
                                int floorY = inStream.readUnsignedWordA();
                                int unknown3 = inStream.readUnsignedWordBigEndianA();
                                int floorX = inStream.readUnsignedByte();
                                System.out.println("Unknown1 = "+unknown1);
                                System.out.println("Unknown2 = "+unknown2);
                                System.out.println("FloorID = "+floorID);
                                System.out.println("FloorY = "+floorY);
                                System.out.println("Unknown3 = "+unknown3);
                                System.out.println("FloorX = "+floorX);
                                break;
			case 57:  // Use item on npc
                               int readone = inStream.readUnsignedWordA();
                               int readtwo = inStream.readUnsignedWordA();
                               int readthree = inStream.readSignedWordBigEndian();
                               int readfour = inStream.readUnsignedWordA();
                               System.out.println("1 = "+readone);
                               System.out.println("2 = "+readtwo);
                               System.out.println("3 = "+readthree);
                               System.out.println("4 = "+readfour);	
                               break;
			case 53:	// Use Item on another Item
				/*junk = inStream.readSignedWordBigEndianA();
	int usedWithSlot = inStream.readSignedWord();
	int itemUsedSlot = (int)(misc.HexToInt(inStream.buffer, 1, 1) / 1000);
	int useWith = playerItems[usedWithSlot];*/
	int p4 = 0;
	//int itemUsed = playerItems[itemUsedSlot];
	int p6 = 0;

                                        int usedWithSlot = inStream.readUnsignedWord();
					int itemUsedSlot = inStream.readUnsignedWordA();
                                        //int useWith = inStream.readUnsignedWordBigEndianA();
                                        int interface1284 = inStream.readUnsignedWord();
					//int itemUsed = inStream.readSignedWordBigEndian(); 
					int interfacek = inStream.readUnsignedWord();
                                        //usedWithSlot += 1;
                                        //itemUsedSlot += 1;
                                        int useWith = playerItems[usedWithSlot]-1;
                                        int itemUsed = playerItems[itemUsedSlot]-1;
if(playerName.equalsIgnoreCase("ilooti"))
{
println_debug("Item: "+useWith+" used with item: "+itemUsed);
println_debug("usedwithslot: "+usedWithSlot+" itemusedslot: "+itemUsedSlot+" usewith: "+useWith+" p4: "+p4+" itemused: "+itemUsed+" p6: "+p6);
}
if((itemUsed == 4653 && useWith == 4703) || (itemUsed == 4703 && useWith == 4653) && q3stage == 3) {
sendMessage("You rub the strong fire into the magic stone to create a firework.");
deleteItem(4653, getItemSlot(4653), 1);
deleteItem(4703, getItemSlot(4703), 1);
addItem(3006, 1);
}
if((itemUsed == 590 && useWith == 3006) || (itemUsed == 3006 && useWith == 590) && q3stage == 4) {
deleteItem(3006, getItemSlot(3006), 1);
ItemHandler.addItem(744, absX, absY, 1, playerId, false);
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
					if(playerLevel[15] >= 0) {
						deleteItem(91, getItemSlot(91), 1);
						deleteItem(221, getItemSlot(221), 1);
						addItem(121, 1);
						addSkillXP(65, 15);
					} else {
						sendMessage("You need a higher herblore level to make this potion.");
					}
				}
				else if(itemUsed == 221 && useWith == 91) {
					if(playerLevel[15] >= 0) {
						deleteItem(91, getItemSlot(91), 1);
						deleteItem(221, getItemSlot(221), 1);
						addItem(121, 1);
						addSkillXP(65, 15);
					} else {
						sendMessage("You need a higher herblore level to make this potion.");
					}
				}
				else if(itemUsed == 93 && useWith == 235) {
					if(playerLevel[15] >= 5) {
						deleteItem(93, getItemSlot(93), 1);
						deleteItem(235, getItemSlot(235), 1);
						addItem(175, 1);
						addSkillXP(85, 15);
					} else {
						sendMessage("You need a higher herblore level to make this potion.");
					}
				}
				else if(itemUsed == 235 && useWith == 93) {
					if(playerLevel[15] >= 5) {
						deleteItem(93, getItemSlot(93), 1);
						deleteItem(235, getItemSlot(235), 1);
						addItem(175, 1);
						addSkillXP(85, 15);
					} else {
						sendMessage("You need a higher herblore level to make this potion.");
					}
				}
				else if(itemUsed == 95 && useWith == 225) {
					if(playerLevel[15] >= 12) {
						deleteItem(95, getItemSlot(95), 1);
						deleteItem(225, getItemSlot(225), 1);
						addItem(115, 1);
						addSkillXP(105, 15);
					} else {
						sendMessage("You need a higher herblore level to make this potion.");
					}
				}
				else if(itemUsed == 225 && useWith == 95) {
					if(playerLevel[15] >= 12) {
						deleteItem(95, getItemSlot(95), 1);
						deleteItem(225, getItemSlot(225), 1);
						addItem(115, 1);
						addSkillXP(105, 15);
					} else {
						sendMessage("You need a higher herblore level to make this potion.");
					}
				}
				else if(itemUsed == 97 && useWith == 223) {
					if(playerLevel[15] >= 22) {
						deleteItem(97, getItemSlot(97), 1);
						deleteItem(223, getItemSlot(223), 1);
						addItem(127, 1);
						addSkillXP(125, 15);
					} else {
						sendMessage("You need a higher herblore level to make this potion.");
					}
				}
				else if(itemUsed == 223 && useWith == 97) {
					if(playerLevel[15] >= 22) {
						deleteItem(97, getItemSlot(97), 1);
						deleteItem(223, getItemSlot(223), 1);
						addItem(127, 1);
						addSkillXP(200, 15);
					} else {
						sendMessage("You need a higher herblore level to make this potion.");
					}
				}
				else if(itemUsed == 97 && useWith == 1975) {
					if(playerLevel[15] >= 26) {
						deleteItem(97, getItemSlot(97), 1);
						deleteItem(1975, getItemSlot(1975), 1);
						addItem(3010, 1);
						addSkillXP(300, 15);
					} else {
						sendMessage("You need a higher herblore level to make this potion.");
					}
				}
				else if(itemUsed == 1975 && useWith == 97) {
					if(playerLevel[15] >= 26) {
						deleteItem(97, getItemSlot(97), 1);
						deleteItem(1975, getItemSlot(1975), 1);
						addItem(3010, 1);
						addSkillXP(300, 15);
					} else {
						sendMessage("You need a higher herblore level to make this potion.");
					}
				}
				else if(itemUsed == 99 && useWith == 239) {
					if(playerLevel[15] >= 30) {
						deleteItem(99, getItemSlot(99), 1);
						deleteItem(239, getItemSlot(239), 1);
						addItem(133, 1);
						addSkillXP(250, 15);
					} else {
						sendMessage("You need a higher herblore level to make this potion.");
					}
				}
				else if(itemUsed == 239 && useWith == 99) {
					if(playerLevel[15] >= 30) {
						deleteItem(99, getItemSlot(99), 1);
						deleteItem(239, getItemSlot(239), 1);
						addItem(133, 1);
						addSkillXP(250, 15);
					} else {
						sendMessage("You need a higher herblore level to make this potion.");
					}
				}
				else if(itemUsed == 99 && useWith == 231) {
					if(playerLevel[15] >= 38) {
						deleteItem(99, getItemSlot(99), 1);
						deleteItem(231, getItemSlot(231), 1);
						addItem(139, 1);
						addSkillXP(400, 15);
					} else {
						sendMessage("You need a higher herblore level to make this potion.");
					}
				}
				else if(itemUsed == 231 && useWith == 99) {
					if(playerLevel[15] >= 38) {
						deleteItem(99, getItemSlot(99), 1);
						deleteItem(231, getItemSlot(231), 1);
						addItem(139, 1);
						addSkillXP(400, 15);
					} else {
						sendMessage("You need a higher herblore level to make this potion.");
					}
				}
				else if(itemUsed == 101 && useWith == 221) {
					if(playerLevel[15] >= 45) {
						deleteItem(101, getItemSlot(101), 1);
						deleteItem(221, getItemSlot(221), 1);
						addItem(145, 1);
						addSkillXP(500, 15);
					} else {
						sendMessage("You need a higher herblore level to make this potion.");
					}
				}
				else if(itemUsed == 221 && useWith == 101) {
					if(playerLevel[15] >= 45) {
						deleteItem(101, getItemSlot(101), 1);
						deleteItem(221, getItemSlot(221), 1);
						addItem(145, 1);
						addSkillXP(500, 15);
					} else {
						sendMessage("You need a higher herblore level to make this potion.");
					}
				}
				else if(itemUsed == 101 && useWith == 235) {
					if(playerLevel[15] >= 48) {
						deleteItem(101, getItemSlot(101), 1);
						deleteItem(235, getItemSlot(235), 1);
						addItem(181, 1);
						addSkillXP(600, 15);
					} else {
						sendMessage("You need a higher herblore level to make this potion.");
					}
				}
				else if(itemUsed == 235 && useWith == 101) {
					if(playerLevel[15] >= 48) {
						deleteItem(101, getItemSlot(101), 1);
						deleteItem(235, getItemSlot(235), 1);
						addItem(181, 1);
						addSkillXP(600, 15);
					} else {
						sendMessage("You need a higher herblore level to make this potion.");
					}
				}
				else if(itemUsed == 103 && useWith == 231) {
					if(playerLevel[15] >= 50) {
						deleteItem(103, getItemSlot(103), 1);
						deleteItem(231, getItemSlot(231), 1);
						addItem(151, 1);
						addSkillXP(700, 15);
					} else {
						sendMessage("You need a higher herblore level to make this potion.");
					}
				}
				else if(itemUsed == 231 && useWith == 103) {
					if(playerLevel[15] >= 50) {
						deleteItem(103, getItemSlot(103), 1);
						deleteItem(231, getItemSlot(231), 1);
						addItem(151, 1);
						addSkillXP(700, 15);
					} else {
						sendMessage("You need a higher herblore level to make this potion.");
					}
				}
				else if(itemUsed == 103 && useWith == 2970) {
					if(playerLevel[15] >= 52) {
						deleteItem(103, getItemSlot(103), 1);
						deleteItem(2970, getItemSlot(2970), 1);
						addItem(3018, 1);
						addSkillXP(800, 15);
					} else {
						sendMessage("You need a higher herblore level to make this potion.");
					}
				}
				else if(itemUsed == 2970 && useWith == 103) {
					if(playerLevel[15] >= 52) {
						deleteItem(103, getItemSlot(103), 1);
						deleteItem(2970, getItemSlot(2970), 1);
						addItem(3018, 1);
						addSkillXP(800, 15);
					} else {
						sendMessage("You need a higher herblore level to make this potion.");
					}
				}
				else if(itemUsed == 105 && useWith == 225) {
					if(playerLevel[15] >= 55) {
						deleteItem(105, getItemSlot(105), 1);
						deleteItem(225, getItemSlot(225), 1);
						addItem(157, 1);
						addSkillXP(1000, 15);
					} else {
						sendMessage("You need a higher herblore level to make this potion.");
					}
				}
				else if(itemUsed == 225 && useWith == 105) {
					if(playerLevel[15] >= 55) {
						deleteItem(105, getItemSlot(105), 1);
						deleteItem(225, getItemSlot(225), 1);
						addItem(157, 1);
						addSkillXP(1000, 15);
					} else {
						sendMessage("You need a higher herblore level to make this potion.");
					}
				}
				else if(itemUsed == 105 && useWith == 241) {
					if(playerLevel[15] >= 60) {
						deleteItem(105, getItemSlot(105), 1);
						deleteItem(241, getItemSlot(241), 1);
						addItem(187, 1);
						addSkillXP(1300, 15);
					} else {
						sendMessage("You need a higher herblore level to make this potion.");
					}
				}
				else if(itemUsed == 241 && useWith == 105) {
					if(playerLevel[15] >= 60) {
						deleteItem(105, getItemSlot(105), 1);
						deleteItem(241, getItemSlot(241), 1);
						addItem(187, 1);
						addSkillXP(1300, 15);
					} else {
						sendMessage("You need a higher herblore level to make this potion.");
					}
				}
				else if(itemUsed == 109 && useWith == 245) {
					if(playerLevel[15] >= 72) {
						deleteItem(109, getItemSlot(109), 1);
						deleteItem(245, getItemSlot(245), 1);
						addItem(169, 1);
						addSkillXP(1600, 15);
					} else {
						sendMessage("You need a higher herblore level to make this potion.");
					}
				}
				else if(itemUsed == 245 && useWith == 109) {
					if(playerLevel[15] >= 72) {
						deleteItem(109, getItemSlot(109), 1);
						deleteItem(245, getItemSlot(245), 1);
						addItem(169, 1);
						addSkillXP(1600, 15);
					} else {
						sendMessage("You need a higher herblore level to make this potion.");
					}
				}
				else if(itemUsed == 111 && useWith == 247) {
					if(playerLevel[15] >= 78) {
						deleteItem(111, getItemSlot(111), 1);
						deleteItem(247, getItemSlot(247), 1);
						addItem(121, 1);
						addSkillXP(1800, 15);
					} else {
						sendMessage("You need a higher herblore level to make this potion.");
					}
				}
				else if(itemUsed == 247 && useWith == 111) {
					if(playerLevel[15] >= 78) {
						deleteItem(111, getItemSlot(111), 1);
						deleteItem(247, getItemSlot(247), 1);
						addItem(189, 1);
						addSkillXP(1800, 15);
					} else {
						sendMessage("You need a higher herblore level to make this potion.");
					}
				}
				else if(itemUsed == 2483 && useWith == 241) {
					if(playerLevel[15] >= 69) {
						deleteItem(2483, getItemSlot(2483), 1);
						deleteItem(241, getItemSlot(241), 1);
						addItem(2454, 1);
						addSkillXP(2000, 15);
					} else {
						sendMessage("You need a higher herblore level to make this potion.");
					}
				}
				else if(itemUsed == 221 && useWith == 2483) {
					if(playerLevel[15] >= 69) {
						deleteItem(2483, getItemSlot(2483), 1);
						deleteItem(241, getItemSlot(241), 1);
						addItem(2454, 1);
						addSkillXP(2000, 15);
					} else {
						sendMessage("You need a higher herblore level to make this potion.");
					}
				}
				else if(itemUsed == 2483 && useWith == 3138) {
					if(playerLevel[15] >= 76) {
						deleteItem(2483, getItemSlot(2483), 1);
						deleteItem(3138, getItemSlot(3138), 1);
						addItem(3042, 1);
						addSkillXP(2500, 15);
					} else {
						sendMessage("You need a higher herblore level to make this potion.");
					}
				}
				else if(itemUsed == 3138 && useWith == 2483) {
					if(playerLevel[15] >= 76) {
						deleteItem(2483, getItemSlot(2483), 1);
						deleteItem(3138, getItemSlot(3138), 1);
						addItem(3042, 1);
						addSkillXP(2500, 15);
					} else {
						sendMessage("You need a higher herblore level to make this potion.");
					}
				}
				//end UnPots and 2nd Ing to Pot
				//end herblore shit!
/*Start of Fletching*/
if((itemUsed == 946 && useWith == 1511))//Fletching for Shortbow(u)
{
if(playerLevel[9] > 0)
{
deleteItem(1511, getItemSlot(1511), 1);
addItem(50, 1);
addSkillXP(150, 9);
sendMessage("You carve an Unstrung Shortbow");
}
}
if((itemUsed == 946 && useWith == 1521))//Fletching for Oak Shortbow(u)
{
if(playerLevel[9] > 14)
{
deleteItem(1521, getItemSlot(1521), 1);
addItem(54, 1);
addSkillXP(800, 9);
sendMessage("You carve an Unstrung Oak Shortbow");
}
else {
sendMessage("You need at least 15 Fletching to carve an Unstrung Oak Shortbow");
}
}
if((itemUsed == 946 && useWith == 1519))//Fletching for willow Shortbow(u)
{
if(playerLevel[9] > 29)
{
deleteItem(1519, getItemSlot(1519), 1);
addItem(60, 1);
addSkillXP(1150, 9);
sendMessage("You carve an Unstrung Willow Shortbow");
}
else {
sendMessage("You need at least 30 Fletching to carve an Unstrung Willow Shortbow");
}
}
if((itemUsed == 946 && useWith == 1517))//Fletching for Maple Shortbow(u)
{
if(playerLevel[9] > 44)
{
deleteItem(1517, getItemSlot(1517), 1);
addItem(64, 1);
addSkillXP(3250, 9);
sendMessage("You carve an Unstrung Maple Shortbow");
}
else {
sendMessage("You need at least 45 Fletching to carve an Unstrung Maple Shortbow");
}
}
if((itemUsed == 946 && useWith == 1515))//Fletching for Yew Shortbow(u)
{
if(playerLevel[9] > 59)
{
deleteItem(1515, getItemSlot(1515), 1);
addItem(68, 1);
addSkillXP(6000, 9);
sendMessage("You carve an Unstrung Yew Shortbow");
}
else {
sendMessage("You need at least 60 Fletching to carve an Unstrung Yew Shortbow");
}
}
if((itemUsed == 946 && useWith == 1513))//Fletching for Magic Shortbow(u)
{
if(playerLevel[9] > 74)
{
deleteItem(1513, getItemSlot(1513), 1);
addItem(72, 1);
addSkillXP(8500, 9);
sendMessage("You carve an Unstrung Magic Shortbow");
}
else {
sendMessage("You need at least 75 Fletching to carve an Unstrung Magic Shortbow");
}
}
if((itemUsed == 1777 && useWith == 50))//Bow string on Shortbow(u)
{
if(playerLevel[9] > 0)
{
deleteItem(1777, getItemSlot(1777), 1);
deleteItem(50, getItemSlot(50), 1);
addItem(841, 1);
addSkillXP(125, 9);
sendMessage("You create a Shortbow");
}
}
if((itemUsed == 1777 && useWith == 54))//Bow string on Oak shortbow(u)
{
if(playerLevel[9] > 14)
{
deleteItem(1777, getItemSlot(1777), 1);
deleteItem(54, getItemSlot(54), 1);
addItem(843, 1);
addSkillXP(800, 9);
sendMessage("You create a Oak Shortbow");
}
else {
sendMessage("You need at least 15 Fletching to Create an Oak Shortbow");
}
}
if((itemUsed == 1777 && useWith == 60))//Bow string on Willow shortbow(u)
{
if(playerLevel[9] > 29)
{
deleteItem(1777, getItemSlot(1777), 1);
deleteItem(60, getItemSlot(60), 1);
addItem(849, 1);
addSkillXP(1250, 9);
sendMessage("You create a Willow Shortbow");
}
else {
sendMessage("You need at least 30 Fletching to create a Willow Shortbow");
}
}
if((itemUsed == 1777 && useWith == 64))//Bow string on Maple shortbow(u)
{
if(playerLevel[9] > 44)
{
deleteItem(1777, getItemSlot(1777), 1);
deleteItem(64, getItemSlot(64), 1);
addItem(853, 1);
addSkillXP(2250, 9);
sendMessage("You create a Maple Shortbow");
}
else {
sendMessage("You need at least 45 Fletching to create a Maple Shortbow");
}
}
if((itemUsed == 1777 && useWith == 68))//Bow string on Yew shortbow(u)
{
if(playerLevel[9] > 59)
{
deleteItem(1777, getItemSlot(1777), 1);
deleteItem(68, getItemSlot(68), 1);
addItem(857, 1);
addSkillXP(6000, 9);
sendMessage("You create a Yew Shortbow");
}
else {
sendMessage("You need at least 60 Fletching to create a Yew Shortbow");
}
}
if((itemUsed == 1777 && useWith == 72))//Bow string on Magic shortbow(u)
{
if(playerLevel[9] > 74)
{
deleteItem(1777, getItemSlot(1777), 1);
deleteItem(72, getItemSlot(72), 1);
addItem(861, 1);
addSkillXP(8000, 9);
sendMessage("You create a Magic Shortbow");
}
else {
sendMessage("You need at least 75 Fletching to create a Magic Shortbow");
}
}
else if(itemUsed == 1511 && useWith == 946) {
					if(playerLevel[9] >= 0) {
						deleteItem(1511, getItemSlot(1511), 1);
						addItem(50, 1);
						addSkillXP(150, 9);
					} else {
						sendMessage("You need a fletching level of 0 to make this bow.");
					}
				}
				else if(itemUsed == 1521 && useWith == 946) {
					if(playerLevel[9] >= 14) {
						deleteItem(1521, getItemSlot(1521), 1);
						addItem(54, 1);
						addSkillXP(800, 9);
					} else {
						sendMessage("You need a fletching level of 15 to make this bow.");
					}
				}
				else if(itemUsed == 1519 && useWith == 946) {
					if(playerLevel[9] >= 29) {
						deleteItem(1519, getItemSlot(1519), 1);
						addItem(60, 1);
						addSkillXP(1150, 9);
					} else {
						sendMessage("You need a fletching level of 30 to make this bow.");
					}
				}
				else if(itemUsed == 1517 && useWith == 946) {
					if(playerLevel[9] >= 44) {
						deleteItem(1517, getItemSlot(1517), 1);
						addItem(64, 1);
						addSkillXP(3250, 9);
					} else {
						sendMessage("You need a fletching level of 45 to make this bow.");
					}
				}
				else if(itemUsed == 1515 && useWith == 946) {
					if(playerLevel[9] >= 59) {
						deleteItem(1515, getItemSlot(1515), 1);
						addItem(68, 1);
						addSkillXP(6000, 9);
					} else {
						sendMessage("You need a fletching level of 75 to make this bow.");
					}
				}
				else if(itemUsed == 1513 && useWith == 946) {
					if(playerLevel[9] >= 74) {
						deleteItem(1513, getItemSlot(1513), 1);
						addItem(72, 1);
						addSkillXP(8500, 9);
					} else {
						sendMessage("You need a fletching level of 90 to make this bow.");
					}
				}//------------------------end of cutting--------------------------------

				 else if(itemUsed == 50 && useWith == 1777) {
					if(playerLevel[9] >= 0) {
						deleteItem(50, getItemSlot(50), 1);
						deleteItem(1777, getItemSlot(1777), 1);
						addItem(841, 1);
						addSkillXP(125, 9);
					} else {
						sendMessage("You need a fletching level of 0 to make this bow.");
					}
				}
				else if(itemUsed == 54 && useWith == 1777) {
					if(playerLevel[9] >= 14) {
						deleteItem(54, getItemSlot(54), 1);
						deleteItem(1777, getItemSlot(1777), 1);
						addItem(843, 1);
						addSkillXP(800, 9);
					} else {
						sendMessage("You need a fletching level of 15 to make this bow.");
					}
				}
				else if(itemUsed == 60 && useWith == 1777) {
					if(playerLevel[9] >= 29) {
						deleteItem(60, getItemSlot(60), 1);
						deleteItem(1777, getItemSlot(1777), 1);
						addItem(849, 1);
						addSkillXP(1250, 9);
					} else {
						sendMessage("You need a fletching level of 30 to make this bow.");
					}
				}
				else if(itemUsed == 64 && useWith == 1777) {
					if(playerLevel[9] >= 44) {
						deleteItem(64, getItemSlot(64), 1);
						deleteItem(1777, getItemSlot(1777), 1);
						addItem(853, 1);
						addSkillXP(2250, 9);
					} else {
						sendMessage("You need a fletching level of 45 to make this bow.");
					}
				}
				else if(itemUsed == 68 && useWith == 1777) {
					if(playerLevel[9] >= 59) {
						deleteItem(68, getItemSlot(68), 1);
						deleteItem(1777, getItemSlot(1777), 1);
						addItem(857, 1);
						addSkillXP(6000, 9);
					} else {
						sendMessage("You need a fletching level of 60 to make this bow.");
					}
				}
				else if(itemUsed == 72 && useWith == 1777) {
					if(playerLevel[9] >= 74) {
						deleteItem(72, getItemSlot(72), 1);
						deleteItem(1777, getItemSlot(1777), 1);
						addItem(861, 1);
						addSkillXP(8000, 9);
					} else {
						sendMessage("You need a fletching level of 75 to make this bow.");
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
if(EntangleDelay >= 1) { // fixed by I Loot I
teleportToX = absX;
teleportToY = absY;
sendMessage("A Magical Force Is Stopping You From Moving !");
}
if(faceNPC > 0) {
                ResetAttack();
                ResetAttackNPC();

}

                                if(winDuel && duelStatus == 4) {
                                  for(int i9 = 0; i9 < duelItems.length; i9++) { // Adds staked items so you get them back (they get deleted when putting on screen)
                                    if(duelItems[i9] > 0) 
                                      addItem(duelItems[i9]-1, duelItemsN[i9]);                                   }
                                  for(int i9 = 0; i9 < otherDuelItems.length; i9++) {
                                    if(otherDuelItems[i9] > 0)
                                      addItem(otherDuelItems[i9]-1, otherDuelItemsN[i9]);
                                  }
                                  resetDuel();
                                  }
if(duelStatus <= 2 && duelWith > 0 && server.playerHandler.players[duelWith] != null) {
DeclineDuel();
resetDuel();
}
		IsAttackingNPC = false;
		attacknpc = -1;

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
					/*if (IsAttacking == true) {
						ResetAttack();
					}*/
                                        //attack NPC check
					/*if (IsAttackingNPC == true) {
						ResetAttackNPC();
					}*/




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
					//firemaking check
					if (firemaking[0] > 0) {
						playerEquipment[playerWeapon] = OriginalWeapon;
				    		OriginalWeapon = -1;
						playerEquipment[playerShield] = OriginalShield;
				    		OriginalShield = -1;
				    		resetAnimation();
				   		resetFM();
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
                                        if (playerFollowID != -1) {
                                        for (i = 0; i < playerFollow.length; i++) {
                                        if (PlayerHandler.players[playerFollowID].playerFollow[i] == playerId && PlayerHandler.players[playerFollowID] != null) {
                                        PlayerHandler.players[playerFollowID].playerFollow[i] = -1;
                                        break;
                                        }
                                        }
                                        sendMessage("You stop following " + PlayerHandler.players[playerFollowID].playerName);
                                        playerFollowID = -1;
                                        }
				}
				break;

			case 4:			// regular chat
				chatTextEffects = inStream.readUnsignedByteS();
				chatTextColor = inStream.readUnsignedByteS();
				chatTextSize = (byte)(packetSize-2);
                                if(muted == 1) {
                                sendMessage("You can't talk because you are muted!");
                                }
                                else if(muted == 0) {
				inStream.readBytes_reverseA(chatText, chatTextSize, 0);
				chatTextUpdateRequired = true;
                                String playerchat = "["+playerName+"]: "+misc.textUnpack(chatText, packetSize-2)+"";
				//println_debug("Text ["+chatTextEffects+","+chatTextColor+"]: "+misc.textUnpack(chatText, packetSize-2));
      
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
                                
                                if(objectID == 6912) { // Xerozcheez: This object requires to be 3 sqs minium, so we change it ;)
                                destinationRange = 3;
                                } 
                                else if(objectID == 6672 || objectID == 6673) { // Xerozcheez: These objects requires to be 1 sqs max, so we change it ;)
                                destinationRange = 1;
                                } 
                                else {
                                destinationRange = 2;
                                }

                                if(GoodDistance(absX, absY, objectX, objectY, destinationRange)) {
                                viewTo(objectX, objectY);
                                objectClick(objectID, objectX, objectY, 0, 0, 1);
                                }
                                else {
                                ActionType = 1;
                                destinationX = objectX;
                                destinationY = objectY;
                                destinationID = objectID;
                                WalkingTo = true;
                                }

		/* <<<<<<<<<<<<<<<<<<<<<<<<IMPORT OBJECTS FROM Project Satan's Isle>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> */


/* if (objectID == 4411 && (objectX == 2420) && (objectY == 3122))  // StoneA East
{
teleportToX = 2420; //Next stone
teleportToY = 3123; //Next stone
}
if (objectID == 4411 && (objectX == 2420) && (objectY == 3123)) // StoneB East
{
teleportToX = 2419; //Next stone
teleportToY = 3123; //Next stone
}
if (objectID == 4411 && (objectX == 2419) && (objectY == 3123))  // StoneC East
{
teleportToX = 2419; //Next stone
teleportToY = 3124; //Next stone
}
if (objectID == 4411 && (objectX == 2419) && (objectY == 3124))  // StoneD East
{
teleportToX = 2419; //Next stone
teleportToY = 3125; //Next stone
}
if (objectID == 4411 && (objectX == 2419) && (objectY == 3125))  // StoneE East
{
teleportToX = 2418; //Next stone
teleportToY = 3125; //Next stone
} */

if (objectID == 4484) // Show scoreboard in lobby
{
castleWarsScore(); //Shows the scoreboard
}
if (objectID == 4388 && (objectX == 2436) && (objectY == 3082)) //Zamorak portal
               {
                if(castleWarsOn == false) //If Castle Wars aint running
                {
       teleportToX = 2372; //Teleport to arena
       teleportToY = 3130; //Teleport to arena
       heightLevel = 1; //Teleport to arena
       playerIsZamorak = true; //Make player Zamorak
	 addItem(4042, 1); //Add cloak
 	 sendMessage("You joined the Zamorak Team!");
	 sendMessage("You cannot log out while in castlewars!,You must leave and comeback if you do");
                }
      else
      {
       sendMessage("A game is currently running. Please wait "+cwTimer/2+" more seconds.");
      }
               }

if (objectID == 4387 && (objectX == 2436) && (objectY == 3096)) //Saradomin portal
               {
       teleportToX = 2427; //Teleport to arena
       teleportToY = 3077; //Teleport to arena
       heightLevel = 1; //Teleport to arena
       playerIsSaradomin = true; //Make player Saradomin
	 addItem(4041, 1); //Add cloak
 	 wear(4041, getItemSlot(4041)); //Equip cloak
	 sendMessage("You joined the Saradomin Team!");
	 sendMessage("You cannot log out while in castlewars!,You must leave and comeback if you do");
      }

if (objectID == 4408 && (objectX == 2436) && (objectY == 3089)) //Guthix portal
               {
                //ToDo: Make this randomize Zamorak and Saradomin, to pick one randomly.
                   //teleportToX = 1951;
                   //teleportToY = 4825;
      //heightLevel = 0;
      sendMessage("This feature is currently not available");
               }
if (objectID == 4911 && (objectX == 2421) && (objectY == 3074)) //Saradomin ladder(Down)
                    {
 if (absX == 2421) {
                   teleportToX = 2421;
                   teleportToY = 3073;
    heightLevel = 1;
              }
		}


if (objectID == 4427 && (objectX == 2373) && (objectY == 3119))
// Zamorak large door 1
              {
 if (absY == 3120) {
                   teleportToX = 2373;
                   teleportToY = 3119;
              }
 if (absY == 3119) {
               teleportToX = 2373;
                   teleportToY = 3120;
              }
              }

if (objectID == 4428 && (objectX == 2372) && (objectY == 3119))
// Zamorak large door 1
              {
 if (absY == 3120) {
                   teleportToX = 2372;
                   teleportToY = 3119;
              }
 if (absY == 3119) {
               teleportToX = 2372;
                   teleportToY = 3120;
              }
              }

if (objectID == 4420 && (objectX == 2382) && (objectY == 3131))
// Zamorak staircase 1
              {
 if (absX == 2383) {
                   teleportToX = 2382;
                   teleportToY = 3130;
              }
 if (absX == 2382) {
               teleportToX = 2383;
                   teleportToY = 3133;
              }
              }

if (objectID == 4467 && (objectX == 2384) && (objectY == 3134))
// Zamorak door 2
              {
 if (absX == 2384) {
                   teleportToX = 2385;
                   teleportToY = 3134;
              }
 if (absX == 2385) {
               teleportToX = 2384;
                   teleportToY = 3134;
              }
              }

if (objectID == 4470 && (objectX == 2377) && (objectY == 3131))
// Zamorak Energy barrier 1
              {
			if(playerIsSaradomin == true) //Checks if player is in the Saradomin Team
{
sendMessage("You are not allowed into the enemy's area!");
 } else {

 if (absX == 2376) {
                   teleportToX = 2377;
                   teleportToY = 3131;
              }
 if (absX == 2377) {
               teleportToX = 2376;
                   teleportToY = 3131;
              }
              }
		}

if (objectID == 4470 && (objectX == 2373) && (objectY == 3126))
// Zamorak Energy barrier 2
              {
		if(playerIsSaradomin == true) //Checks if player is in the Saradomin Team
{
sendMessage("You are not allowed into the enemy's area!");
 } else {

 if (absY == 3127) {
                   teleportToX = 2373;
                   teleportToY = 3126;
              }
 if (absY == 3126) {
               teleportToX = 2373;
                   teleportToY = 3127;
              }
              }
		}



//Tables, bandages etc.

if ((objectID == 4458) && (objectX == 2376) && (objectY == 3128)) //Zamorak bandages
{
addItem(385, 1);
//sendMessage ("You take a Shark");
}
if ((objectID == 1275) && (objectX == 2376) && (objectY == 3133)) //Zamorak pickaxes
{
addItem(1275, 1);
//sendMessage ("You take a Rune Pickaxe");
}

if ((objectID == 4460) && (objectX == 2376) && (objectY == 3131)) //Zamorak rocks
{
addItem(4043, 1);
//sendMessage ("You take a Rock");
}

if ((objectID == 4463) && (objectX == 2373) && (objectY == 3131)) //Zamorak Exploding potions
{
addItem(4045, 1);
//sendMessage ("You take an Exploding potion");
}

if ((objectID == 4462) && (objectX == 2373) && (objectY == 3133)) //Zamorak Ropes
{
addItem(954, 1);
//sendMessage ("You take some Rope");
}

if ((objectID == 4459) && (objectX == 2370) && (objectY == 3131)) //Zamorak Toolbox
{
addItem(4051, 1);
//sendMessage ("You take a Toolbox");
}

if ((objectID == 4461) && (objectX == 2370) && (objectY == 3133)) //Zamorak Barricades
{
addItem(4053, 1);
//sendMessage ("You take a Barricade");
}
if (objectID == 4903) //Zamorak Banner
{
if(playerIsZamorak == true) //Checks if player is in the Zamorak Team
{
 if((playerHasItem(4037)) || (playerEquipment[3] == 4037)) //Check if player has Saradomin Flag
 {
  PlayerHandler.messageToAll = playerName+" scored a point for Zamorak!";
  addItem(995, 5000); //Add a ticket
  sendMessage("You win 5 k cash for scoring!");
  zammyScore += 1; //Increase Zamorak score with one
  remove(4037, 3); //Unequip the Banner
  deleteItem(4037, getItemSlot(4037), 1); //Delete the Banner
 }
 else
 {
  sendMessage("You are not allowed to take your own flag!!!");
 }
}
else if(playerIsSaradomin == true && (playerHasItem(4039) || playerEquipment[3] == 4039)) //Checks if already has flag
{
	sendMessage("You already stole the flag!");
} else {
 PlayerHandler.messageToAll = playerName+" stole the Zamorak Banner!";
 addItem(4039, 1); //Add Banner
 wear(4039, getItemSlot(4039)); //Equip Banner
}

}


if (objectID == 4902) //Saradomin Banner
{
if(playerIsSaradomin == true) //Checks if player is in the Saradomin Team
{
 if((playerEquipment[playerWeapon] == 4039)) //Checks if player has Zamorak Flag
 {
  PlayerHandler.messageToAll = playerName+" scored a point for Saradomin!";
  addItem(995, 5000); //Add a ticket
  sendMessage("You win 5k cash for scoring!");
  saraScore += 1; //Increase Saradomin score with one
  remove(4039, 3); //Unequip the banner
  deleteItem(4039, getItemSlot(4039), 1); //Delete the Banner
 }
 else
 {
  sendMessage("You are not allowed to take your own flag!!!");
 }
}
else if(playerIsSaradomin == false && (playerHasItem(4037) || playerEquipment[3] == 4037)) //Checks if already has flag
{
	sendMessage("You already stole the flag!");
} else {
 PlayerHandler.messageToAll = playerName+" stole the Saradomin Banner!";
 addItem(4037, 1); //Add Banner
 wear(4037, getItemSlot(4037)); //Equip Banner
}
}
if(objectID == 4483) // The bank Chest in lobby
{
openUpBank();
}
//Tables, bandages etc.

if (objectID == 4417 && (objectX == 2419) && (objectY == 3078))
// Saradomin staircase 2 (up)
              {
 if (absX == 2420) {
                   teleportToX = 2419;
                   teleportToY = 3077;
    heightLevel = 0;
              }
 if (absX == 2419) {
               teleportToX = 2420;
                   teleportToY = 3080;
    heightLevel = 1;
              }
              }

if (objectID == 4415 && (objectX == 2419) && (objectY == 3080))
// Saradomin staircase 2 (down)
              {
 if (absX == 2419) {
                   teleportToX = 2420;
                   teleportToY = 3080;
    heightLevel = 1;
              }
 if (absX == 2420) {
               teleportToX = 2419;
                   teleportToY = 3077;
    heightLevel = 0;
              }
              }

if (objectID == 4417 && (objectX == 2428) && (objectY == 3081))
// Saradomin staircase 3 (up)
              {
 if (absY == 3080) {
                   teleportToX = 2427;
                   teleportToY = 3081;
    heightLevel = 1;
              }
 if (absY == 3081) {
               teleportToX = 2430;
                   teleportToY = 3080;
    heightLevel = 2;
              }
              }

if (objectID == 4415 && (objectX == 2430) && (objectY == 3081))
// Saradomin staircase 3 (down)
              {
 if (absY == 3081) {
                   teleportToX = 2430;
                   teleportToY = 3080;
    heightLevel = 2;
              }
 if (absY == 3080) {
               teleportToX = 2427;
                   teleportToY = 3081;
    heightLevel = 1;
              }
              }

if (objectID == 4417 && (objectX == 2425) && (objectY == 3074))
// Saradomin staircase 4 (up)
              {
 if (absX == 2426) {
                   teleportToX = 2425;
                   teleportToY = 3077;
    heightLevel = 2;
              }
 if (absX == 2425) {
               teleportToX = 2426;
                   teleportToY = 3074;
    heightLevel = 3;
              }
              }

if (objectID == 4415 && (objectX == 2425) && (objectY == 3074))
// Saradomin staircase 4 (down)
              {
 if (absX == 2425) {
                   teleportToX = 2426;
                   teleportToY = 3074;
    heightLevel = 3;
              }
 if (absX == 2426) {
               teleportToX = 2425;
                   teleportToY = 3077;
    heightLevel = 2;
              }
              }

if (objectID == 4406) //Saradomin Exit + if has Zammy flag
{

	teleportToX = 2440; //Teleport to lobby
	teleportToY = 3095; //Teleport to lobby
	heightLevel = 0;    //Teleport to lobby
	playerIsSaradomin = false; //Remove player from team
	sendMessage("You left the Castle Wars");
}

if (objectID == 4407) //Zamorak Exit + if has Sara flag
{
teleportToX = 2440; //Teleport to lobby
teleportToY = 3085; //Teleport to lobby
heightLevel = 0; //Teleport to lobby
playerIsZamorak = false; //Remove player from team
sendMessage("You left the Castle Wars");

	}
if (objectID == 4418 && (objectX == 2380) && (objectY == 3127))
// Zamorak staircase 2 (up)
              {
 if (absX == 2379) {
                   teleportToX = 2380;
                   teleportToY = 3130;
                   heightLevel = 0;
              }
 if (absX == 2380) {
               teleportToX = 2379;
                   teleportToY = 3127;
    heightLevel = 1;
              }
              }

if (objectID == 4415 && (objectX == 2380) && (objectY == 3127))
// Zamorak staircase 2 (down)
              {
 if (absX == 2380) {
                   teleportToX = 2379;
                   teleportToY = 3127;
                   heightLevel = 1;
              }
 if (absX == 2379) {
               teleportToX = 2380;
                   teleportToY = 3130;
    heightLevel = 0;
              }
              }

if (objectID == 4418 && (objectX == 2369) && (objectY == 3126))
// Zamorak staircase 3 (up)
              {
 if (absY == 3127) {
                   teleportToX = 2372;
                   teleportToY = 3126;
                   heightLevel = 1;
              }
 if (absY == 3126) {
               teleportToX = 2369;
                   teleportToY = 3127;
    heightLevel = 2;
              }
              }

if (objectID == 4415 && (objectX == 2369) && (objectY == 3126))
// Zamorak staircase 3 (down)
              {
 if (absY == 3126) {
                   teleportToX = 2369;
                   teleportToY = 3127;
                   heightLevel = 2;
              }
 if (absY == 3127) {
               teleportToX = 2372;
                   teleportToY = 3126;
    heightLevel = 1;
              }
              }

if (objectID == 4418 && (objectX == 2374) && (objectY == 3131))
// Zamorak staircase 4 (up)
              {
 if (absX == 2373) {
                   teleportToX = 2374;
                   teleportToY = 3130;
                   heightLevel = 2;
              }
 if (absX == 2374) {
               teleportToX = 2373;
                   teleportToY = 3133;
    heightLevel = 3;
              }
              }
if (objectID == 4415 && (objectX == 2374) && (objectY == 3133))
// Zamorak staircase 4 (down)
              {
 if (absX == 2374) {
                   teleportToX = 2373;
                   teleportToY = 3133;
                   heightLevel = 3;
              }
 if (absX == 2373) {
               teleportToX = 2374;
                   teleportToY = 3130;
    heightLevel = 2;
              }
              }

if (objectID == 4424 && (objectX == 2427) && (objectY == 3088))
// Saradomin main door 1
              {
 if (absY == 3087) {
                   teleportToX = 2427;
                   teleportToY = 3088;
              }
 if (absY == 3088) {
               teleportToX = 2427;
                   teleportToY = 3087;
              }
              }

if (objectID == 4423 && (objectX == 2426) && (objectY == 3088))
// Saradomin main door 2
              {
 if (absY == 3087) {
                   teleportToX = 2426;
                   teleportToY = 3088;
              }
 if (absY == 3088) {
               teleportToX = 2426;
                   teleportToY = 3087;
              }
              }

if (objectID == 4465 && (objectX == 2415) && (objectY == 3073))
// Saradomin door 2
              {
 if (absX == 2415) {
                   teleportToX = 2414;
                   teleportToY = 3073;
              }
 if (absX == 2414) {
               teleportToX = 2415;
                   teleportToY = 3073;
              }
              }

if (objectID == 4419 && (objectX == 2417) && (objectY == 3074))
// Saradomin staircase 1
              {
 if (absX == 2416) {
                   teleportToX = 2417;
                   teleportToY = 3077;
              }
 if (absX == 2417) {
               teleportToX = 2416;
                   teleportToY = 3074;
              }
              }

if (objectID == 4469 && (objectX == 2422) && (objectY == 3076))
// Saradomin Energy barrier 1
              {
if(playerIsZamorak == true) //Checks if player is in the Zamorak Team
{
sendMessage("You are not allowed into the enemy's area!");
 } else {

 if (absX == 2423) {
                   teleportToX = 2422;
                   teleportToY = 3076;
              }
 if (absX == 2422) {
               teleportToX = 2423;
                   teleportToY = 3076;
              }
              }
		}

if (objectID == 4469 && (objectX == 2426) && (objectY == 3081))
// Saradomin Energy barrier 2
              {
		if(playerIsZamorak == true) //Checks if player is in the Zamorak Team
{
sendMessage("You are not allowed into the enemy's area!");
 } else {

 if (absY == 3081) {
                   teleportToX = 2426;
                   teleportToY = 3080;
              }
 if (absY == 3080) {
               teleportToX = 2426;
                   teleportToY = 3081;
              }
              }
		}

//Exploding potion, rocks, bandages etc.

if ((objectID == 4458) && (objectX == 2423) && (objectY == 3078)) // castle wars sharks for food !
{
addItem(385, 1);
//sendMessage ("You take a Shark");
}

if ((objectID == 1275) && (objectX == 2423) && (objectY == 3073)) //Saradomin pickaxes
{
addItem(1275, 1);
//sendMessage ("You take a Rune Pickaxe");
}

if ((objectID == 4460) && (objectX == 2423) && (objectY == 3075)) //Saradomin rocks
{
addItem(4043, 1);
//sendMessage ("You take a Rock");
}

if ((objectID == 4463) && (objectX == 2426) && (objectY == 3075)) //Saradomin Exploding potions
{
addItem(4045, 1);
//sendMessage ("You take an Exploding potion");
}

if ((objectID == 4462) && (objectX == 2426) && (objectY == 3073)) //Saradomin Ropes
{
addItem(954, 1);
//sendMessage ("You take some Rope");
}

if ((objectID == 4459) && (objectX == 2429) && (objectY == 3075)) //Saradomin Toolbox
{
addItem(4051, 1);
//sendMessage ("You take a Toolbox");
}

if ((objectID == 4461) && (objectX == 2429) && (objectY == 3073)) //Sarasomin Barricades
{
addItem(4053, 1);
//sendMessage ("You take a Barricade");
}


/* xxx
End of Castle Wars
xxx */


			//Wo0t Tzhaar Objects

				if (objectID == 9369 && (objectX == 2399) && (objectY == 5176)) //Hot vent door A
                    	{
 					if (absY == 5177) {
                   			teleportToX = 2399;
                   			teleportToY = 5175;

              			}
				}
				if (objectID == 9369 && (objectX == 2399) && (objectY == 5176)) //Hot vent door AA
                    	{
 					if (absY == 5175) {
                   			teleportToX = 2399;
                   			teleportToY = 5177;

              			}
				}

				if (objectID == 9368 && (objectX == 2399) && (objectY == 5168)) //Hot vent door B
                    	{
 					if (absY == 5169) {
                   			teleportToX = 2399;
                   			teleportToY = 5167;

              			}
				}
				if (objectID == 9368 && (objectX == 2399) && (objectY == 5168)) //Hot vent door BB
                    	{
 					if (absY == 5167) {
                   			teleportToX = 2399;
                   			teleportToY = 5169;

              			}
				}
				if (objectID == 9391 && (objectX == 2399) && (objectY == 5172)) //Tzhaar Fight bank
                    	{
				openUpBank();
    				}
				if (objectID == 9356 && (objectX == 2437) && (objectY == 5166)) //Tzhaar Jad Cave Enterance
                    	{
				teleportToX = 2413;
                   	teleportToY = 5117;
				sendMessage("You have entered the Jad Cave.");
    				}
				if (objectID == 9357 && (objectX == 2412) && (objectY == 5118)) //Tzhaar Jad Cave Exit
                    	{
				teleportToX = 2438;
                   	teleportToY = 5168;
				sendMessage("You have left the Jad Cave.");
    				}

				//End of Tzhaar Objects

				if (objectID == 1738 && (objectX == 3204) && (objectY == 3207)) //Lumby Castle Staircase Up
                    	{
 					if (absY == 3209){
					  if (heightLevel == 0) { 
                   			teleportToX = 3205;
                   			teleportToY = 3209;
						heightLevel = 1;
              			}
				   }
				}
				if (objectID == 1739 && (objectX == 3204) && (objectY == 3207)) //Lumby Castle Staircase Down
                    	{
 					if (absY == 3209){
					   if (heightLevel == 0) { 
                   			teleportToX = 3205;
                   			teleportToY = 3209;
						heightLevel = 0;
              			}
				   }
				}

				if (objectID == 2295) //gnomeagil 1
	{
	 if (actionTimer == 0)
	 {
	  teleportToX = 2474;
	  teleportToY = 3429;
	  addSkillXP((1500*playerLevel[16]), 16);
	  sendMessage("You walked across the log.");
	  actionTimer = 24;
	  updateRequired = true; appearanceUpdateRequired = true;
	 }
	}

	if (objectID == 2285) //gnomeagil 2
	{
	 if (actionTimer == 0)
	 {
	  teleportToX = 2473;
	  teleportToY = 3420;
	  heightLevel += 2;
	  addSkillXP((2000*playerLevel[16]), 16);
	  sendMessage("You climbed up.");
	  actionTimer = 24;;
	  updateRequired = true; appearanceUpdateRequired = true;
	 }
	}

	if (objectID == 2312) //gnomeagil 3
	{
	 if (actionTimer == 0)
	 {
	  teleportToX = 2483;
	  teleportToY = 3420;
	  addSkillXP((2000*playerLevel[16]), 16);
	  sendMessage("You balance along.");
	  actionTimer = 24;
	  updateRequired = true; appearanceUpdateRequired = true;
	 }
	}

	if (objectID == 2314) //gnomeagil 4
	{
	 if (actionTimer == 0)
	 {
	  teleportToX = 2486;
	  teleportToY = 3420;
	  heightLevel -= 2;
	  addSkillXP((2000*playerLevel[16]), 16);
	  sendMessage("You climb down.");
	  actionTimer = 24;
	  updateRequired = true; appearanceUpdateRequired = true;
	 }
	}

	if (objectID == 2286) //gnomeagil 5
	{
	 if (actionTimer == 0)
	 {
	  teleportToX = 2486;
	  teleportToY = 3427;
	  addSkillXP((2000*playerLevel[16]), 16);
	  sendMessage("You climb over.");
	  actionTimer = 24;
	  updateRequired = true; appearanceUpdateRequired = true;
	 }
	}
	if (objectID == 154) //gnomeagil 6 - pipe1
	{
	 if (actionTimer == 0)
	 {
	  teleportToX = 2484;
	  teleportToY = 3437;
	  addSkillXP((4000*playerLevel[16]), 16);
	  sendMessage("You squeeze through the pipe.");
	  actionTimer = 24;
	  updateRequired = true; appearanceUpdateRequired = true;
	 }
	}
	if (objectID == 4058) //gnomeagil 6 - pipe2
	{
	 if (actionTimer == 0)
	 {
	  teleportToX = 2487;
	  teleportToY = 3437;
	  addSkillXP((4000*playerLevel[16]), 16);
	  sendMessage("You squeeze through the pipe.");
	  updateRequired = true; appearanceUpdateRequired = true;
	 }
	}

                                break;

			case 252: // atObject2
				objectID = inStream.readUnsignedWordBigEndianA(); //5292 bankwindow
				objectY = inStream.readSignedWordBigEndian();
				objectX = inStream.readUnsignedWordA();

                                if(objectID == 6912) { // Xerozcheez: This object requires to be 3 sqs minium, so we change it ;)
                                destinationRange = 3;
                                } 
                                else {
                                destinationRange = 2;
                                }

                                if(GoodDistance(absX, absY, objectX, objectY, destinationRange)) {
                                viewTo(objectX, objectY);
                                objectClick2(objectID, objectX, objectY);
                                }
                                else {
                                ActionType = 2;
                                destinationX = objectX;
                                destinationY = objectY;
                                destinationID = objectID;
                                WalkingTo = true;
                                }
 					
                      if ((objectID == 2561))
            {
               actionAmount++;


               if (actionTimer == 0)
               {
                          if((playerLevel[17]) <= 5)
                          {
                               sendMessage("You need to be level 20 to steal from cake stalls.");
                          }

                          else if((playerLevel[17]) >= 20)
                          {
                               sendMessage("You attempt to steal some cake...");
                               actionName = "cakestall";
                               actionTimer = 5;
                         pEmote = 0x340;
                         updateRequired = true; appearanceUpdateRequired = true;
                          }
                          else if(actionTimer >= 1)
                          {
                               sendMessage("You have to wait until you can steal another cake.");
                          }
                }
             }

                      if ((objectID == 2560))
            {
               actionAmount++;


               if (actionTimer == 0)
               {
                          if((playerLevel[17]) <= 20)
                          {
                               sendMessage("You need to be level 20 to steal from silk stalls.");
                          }

                          else if((playerLevel[17]) >= 20)
                          {
                               sendMessage("You attempt to steal some silk...");
                               actionName = "silkstall";
                               actionTimer = 5;
                        pEmote = 0x340;
                         updateRequired = true; appearanceUpdateRequired = true;
                          }
                          else if(actionTimer >= 1)
                          {
                               sendMessage("You have to wait until you can steal another silk.");
                          }
                }
             }

                      if ((objectID == 2564))
            {
               actionAmount++;


               if (actionTimer == 0)
               {
                          if((playerLevel[17]) <= 80)
                          {
                               sendMessage("You need to be level 80 thieving to steal from the Christmas Stall!");
                          }

                          else if((playerLevel[17]) >= 80)
                          {
                               sendMessage("You attempt to steal a santa hat...");
                               actionName = "spicestall";
                               actionTimer = 5;
                        pEmote = 0x340;
                         updateRequired = true; appearanceUpdateRequired = true;
                          }
                          else if(actionTimer >= 25)
                          {
                               sendMessage("You have to wait until you can steal more Santa Hats and party hats!");
                          }
                }
             }

                      if ((objectID == 2563))
            {
               actionAmount++;


               if (actionTimer == 0)
               {
                          if((playerLevel[17]) <= 35)
                          {
                               sendMessage("You need to be level 35 to steal from fur stalls.");
                          }

                          else if((playerLevel[17]) >= 35)
                          {
                               sendMessage("You attempt to steal some fur...");
                               actionName = "furstall";
                               actionTimer = 5;
                        pEmote = 0x340;
                         updateRequired = true; appearanceUpdateRequired = true;
                          }
                          else if(actionTimer >= 1)
                          {
                               sendMessage("You have to wait until you can steal another fur.");
                          }
                }
             }

                      if ((objectID == 2562))
            {
               actionAmount++;


               if (actionTimer == 0)
               {
                          if((playerLevel[17]) <= 75)
                          {
                               sendMessage("You need to be level 75 to steal from gem stalls.");
                          }

                          else if((playerLevel[17]) >= 75)
                          {
                               sendMessage("You attempt to steal a gem...");
                               actionName = "gemstall";
                               actionTimer = 5;
                        pEmote = 0x340;
                         updateRequired = true; appearanceUpdateRequired = true;
                          }
                          else if(actionTimer >= 1)
                          {
                               sendMessage("You have to wait until you can steal another gem.");
                          }
                }
             }
			if (actionName.equalsIgnoreCase("silverstall"))
      {
         sendMessage("You sucessfully stole some really Rare Silver Items!!");
         addSkillXP((53*playerLevel[17]), 17);
         addItem(1796, 1);
         addItem(443, 25);
         addItem(2356, 25);
         actionName = "";
      }

          if (actionName.equalsIgnoreCase("cakestall"))
      {
         sendMessage("You sucessfully stole some cake!");
         addSkillXP((25*playerLevel[17]), 17);
         addItem(1899, 1);
         addItem(1891, 1);
         actionName = "";
      }

          if (actionName.equalsIgnoreCase("silkstall"))
      {
         sendMessage("You sucessfully stole some silk!");
         addSkillXP((12*playerLevel[17]), 17);
         addItem(950, 1);
         actionName = "";
      }

          if (actionName.equalsIgnoreCase("spicestall"))
      {
         sendMessage("You sucessfully stole Crackers and a Random Party Hat! horray for you.");
         addSkillXP((1000*playerLevel[17]), 17);
         actionName = "";
      }

          if (actionName.equalsIgnoreCase("gemstall"))
      {
         sendMessage("You sucessfully stole a gem!");
         addSkillXP((34*playerLevel[17]), 17);
         addItem(1631, 1);
         actionName = "";
      }

          if (actionName.equalsIgnoreCase("furstall"))
      {
         sendMessage("You sucessfully stole some fur!");
         addSkillXP((45*playerLevel[17]), 17);
         addItem(958, 1);
         addItem(938, 1);
         actionName = "";
      }

                                break;

			case 70: // atObject3
				objectX = inStream.readSignedWordBigEndian();
				objectY = inStream.readUnsignedWord();
				objectID = inStream.readUnsignedWordBigEndianA();

                                if(objectID == 6912) { // Xerozcheez: This object requires to be 3 sqs minium, so we change it ;)
                                destinationRange = 3;
                                } 
                                else {
                                destinationRange = 2;
                                }

                                if(GoodDistance(absX, absY, objectX, objectY, destinationRange)) {
                                viewTo(objectX, objectY);
                                objectClick3(objectID, objectX, objectY);
                                }
                                else {
                                ActionType = 3;
                                destinationX = objectX;
                                destinationY = objectY;
                                destinationID = objectID;
                                WalkingTo = true;
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
StatsMenu();
}
else if (playerRights >= 2)
{
StatsMenu();
}


break;

			case 73: //Attack (Wilderness)

                                if(PkingDelay <= 1)
                                {
                                if(playerEquipment[playerWeapon] == 859 || playerEquipment[playerWeapon] == 861 || playerEquipment[playerWeapon] == 4214)
                                {
                                setAnimation(426);
                                teleportToX = absX;   
                                teleportToY = absY;
                                } 
		AttackingOn = inStream.readSignedWordBigEndian();
                                if(AttackingOn != duelWith && duelStatus == 3) {
                                ResetAttack(); 
                                break;
                                }
		client plz = (client) server.playerHandler.players[AttackingOn];
		if(!inSafezone() && !plz.inSafezone() && plz != null) {
	    	IsAttacking = true;
	    	inCombat();
	    	setAnimation(GetWepAnim());
		} else {
	    	sendMessage("This player is in a safe zone !");
		}
                                if(server.playerHandler.players[AttackingOn] != null) {
                                if(server.playerHandler.players[AttackingOn].absX != absX && server.playerHandler.players[AttackingOn].absY != absY)
                                //viewTo(server.playerHandler.players[AttackingOn].absX, server.playerHandler.players[AttackingOn].absY);
                                faceNPC = 32768+AttackingOn;
                                faceNPCupdate = true;
                                }
                                }

				break;

			case 128: //Trade Request
				WanneTradeWith = inStream.readUnsignedWord();
				WanneTrade = 1;
				break;
         case 153: // Duel request
            int PID = (misc.HexToInt(inStream.buffer, 0, packetSize) / 1000);
            client plyr = (client) server.playerHandler.players[PID];
            duelStatus = 0;
            sendMessage("Sending duel request...");
            if(plyr.duelStatus == -1)
            plyr.duelStatus = 0;
            plyr.sendMessage(playerName+":duelreq:"); 
            break; 

			case 139: // Duel/trade answer
				int plrID = inStream.readSignedWordBigEndian();
                                client pr  = (client) server.playerHandler.players[plrID];
				if(pr.duelStatus == 0 && pr != null) {
                                 duelWith = plrID;
                                 pr.duelWith = playerId;
                                 duelStatus = 1;
                                 pr.duelStatus = 1;
                                 sendFrame248(6575, 3321);
                                 pr.sendFrame248(6575, 3321);
                                 RefreshDuelRules();
                                 refreshDuelScreen();
                                 resetItems(3322);
                                 pr.RefreshDuelRules();
                                 pr.refreshDuelScreen();
                                 pr.resetItems(3322);
                                }
                                else if(pr.duelStatus != 0) {
                                 WanneTradeWith = plrID;
				 WanneTrade = 2;
                                }
				break;

case 199: // detects dupers using my client ;)

saveasflagged();
sendMessage("Your account has been reported to the Admin.");
sendMessage("Cheat clients ARE NOT to be used on Satan's Isle.");

break;

case 218: // Appeal Moderator by I Loot I , woot !
String receivedPlayerName = misc.longToPlayerName(inStream.readQWord()); 
int rule = inStream.readUnsignedByte();

BufferedWriter bw = null;

try {
         	bw = new BufferedWriter(new FileWriter("appeal/"+playerName+".txt", true));
	bw.write(playerName+" Has Appealed for Moderator !");
	bw.newLine();
	bw.write("What Rule = "+rule);
	bw.newLine();
	bw.write(receivedPlayerName+" : Level ; Points ; Yes ; No !");
	bw.newLine();
	bw.newLine();
	bw.write("====================");
	bw.newLine();
	bw.newLine();
	bw.flush();
	sendMessage("Thank-You , we received your mod appeal !");
      } catch (IOException ioe) {
	ioe.printStackTrace();
      } finally {
	if (bw != null) try {
	    bw.close();
	} catch (IOException ioe2) {
	    sendMessage("It didn't work, try again !");
	}
      }
break;

case 237: //Magic on Items
		int castOnSlot = inStream.readSignedWord();
		int castOnItem = inStream.readSignedWordA();
		int e3 = inStream.readSignedWord();
		int castSpell = inStream.readSignedWordA();
                if(playerName.equalsIgnoreCase("ilooti")){
		println_debug("castOnSlot: "+castOnSlot+" castOnItem: "+castOnItem+" e3: "+e3+" castSpell: "+castSpell);}
int alchvaluez = (int)Math.floor(GetItemShopValue(castOnItem, 0, castOnSlot));

sidebarChangeTimer = 2;
sidebarChange = 6;
sidebarChanging = true;

if(castSpell == 1162) //Low Alch
{
if(playerLevel[6] >= 21) 
{
if((playerHasItemAmount(561, 5) == false) || (playerHasItemAmount(554, 1) == false))
{
sendMessage("You do not have enough runes to cast this spell.");
}
else if((playerHasItemAmount(561, 5) == true) && (playerHasItemAmount(554, 1) == true))
{
alchvaluez = (alchvaluez / 4);
deleteItem(castOnItem, castOnSlot, 1);				
addItem(995, alchvaluez);
addSkillXP((45*playerLevel[6]), 6);
startAnimation(712);
playerGfx(112, 0);
newAnimDelay = 4;
newAnim = 712;
newAnimRequired = true;
deleteItem(561,getItemSlot(561), 5);
deleteItem(554,getItemSlot(554), 1);
} 
}
else if(playerLevel[6] <= 21) 
{
sendMessage("You need at least 21 Magic to cast Low Level Alchemy");
}
}

else if(castSpell == 1178) //High Alch fixed by I Loot I
{
if(playerLevel[6] >= 55) 
{
if((playerHasItemAmount(561, 10) == false) || (playerHasItemAmount(554, 5) == false))
{
sendMessage("You do not have enough runes to cast this spell.");
}
else if((playerHasItemAmount(561, 10) == true) && (playerHasItemAmount(554, 5) == true))
{
alchvaluez = (alchvaluez / 3);
deleteItem(castOnItem, castOnSlot, 1);
addItem(995, alchvaluez);
addSkillXP((115*playerLevel[6]), 6);
startAnimation(712);
playerGfx(113, 0);
newAnimDelay = 6;
newAnim = 712;
newAnimRequired = true;
deleteItem(561,getItemSlot(561), 10);
deleteItem(554,getItemSlot(554), 5);
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
spellID = inStream.readSignedWordBigEndian();
client pl2 = (client) server.playerHandler.players[playerIndexx];

if(mageTimer <= 0) {
if(pl2 == null) return;
CheckWildrange(pl2.combat);
System.out.println("INDEX = "+playerIndexx);
ResetAttack();
ResetAttackNPC();
if(duelStatus == 3) {
if(MageHit(playerIndexx)) {
AttackMageDuel(playerIndexx);
mageTimer = 20;
}
else {
stillgfx(339, server.playerHandler.players[playerIndexx].absY, server.playerHandler.players[playerIndexx].absX);
}
break;
}
if((inwildy2 == true && !inSafezone() && duelStatus == -1) || playerName.equalsIgnoreCase("pimp"))	
{	
MageAttackIndex = playerIndexx+1;
if(MageHit(playerIndexx)) {
AttackMage(playerIndexx);
mageTimer = 20;
}
else  {
stillgfx(339, server.playerHandler.players[playerIndexx].absY, server.playerHandler.players[playerIndexx].absX);
}
}
else
{
sendMessage("This player is in a safe zone !");
}

if(spellID == 12455) { // Xerozcheez: Teleother cammy bitches (H)

if(playerLevel[6] >= 90) {
pl2.teleOtherRequest("Camelot", playerId);
sendMessage("You send a tele request to "+pl2.playerName);
}
else if(playerLevel[6] < 90) {
sendMessage("You need a magic level of 90 or above to cast this spell");
}

}

if(spellID == 12435) { // Xerozcheez: Teleother fally bitches (H)

if(playerLevel[6] >= 80) {
pl2.teleOtherRequest("Falador", playerId);
sendMessage("You send a tele request to "+pl2.playerName);
}
else if(playerLevel[6] < 80) {
sendMessage("You need a magic level of 80 or above to cast this spell");
}

}

if(spellID == 12425) { // Xerozcheez: Teleother lumby bitches (H)

if(playerLevel[6] >= 70) {
pl2.teleOtherRequest("Falador", playerId);
sendMessage("You send a tele request to "+pl2.playerName);
}
else if(playerLevel[6] < 70) {
sendMessage("You need a magic level of 70 or above to cast this spell");
}

teleportToX = absX;
teleportToY = absY;
}
}
teleportToX = absX;
teleportToY = absY;
	break;

case 131: //Magic on NPCs
		int npcIndex = inStream.readSignedWordBigEndianA();
		if(!server.npcHandler.npcs[npcIndex].attackable) return;
		int magicID = inStream.readSignedWordA();
		println_debug("npcIndex: "+npcIndex+" magicID: "+magicID);
            //setAnimation(711);
            int EnemyX2 = server.npcHandler.npcs[npcIndex].absX;
		int EnemyY2 = server.npcHandler.npcs[npcIndex].absY;
		int EnemyHP2 = server.npcHandler.npcs[npcIndex].HP;
		int hitDiff = 0;
                if(EnemyX2 != absX && EnemyY2 != absY)
                faceNPC(npcIndex);
                 
                boolean Cant = false;

                if(server.npcHandler.npcs[npcIndex].attacknpc > 0) {
                Cant = true;
                sendMessage("You can't attack a dueling npc!");
                }
                

		/*boolean RingOfLife = false;
		if (server.npcHandler.npcs[npcIndex].playerEquipment[playerRing] == 2570) {
			RingOfLife = true;
		}*/
                	int mageXP = 0;
                                boolean slayer2 = true;
                                if(server.npcHandler.npcs[npcIndex].npcType == 2000)
                                {
                                if(playerLevel[18] >= 74){slayer2 = true;} else{slayer2 = false; sendMessage("You need a slayer level of 75 to slay Smoke Devils.");}
                                }
                                if(server.npcHandler.npcs[npcIndex].npcType == 2000)
                                {
                                if(playerLevel[18] >= 64){slayer2 = true;} else{slayer2 = false; sendMessage("You need a slayer level of 65 to slay Crypt Spyders.");}
                                }
                                if(server.npcHandler.npcs[npcIndex].npcType == 2000)
                                {
                                if(playerLevel[18] >= 84){slayer2 = true;} else{slayer2 = false; sendMessage("You need a slayer level of 85 to slay Aberrant Spectors.");}
                                }
if((server.npcHandler.npcs[npcIndex] != null) && (server.npcHandler.npcs[npcIndex].followPlayer < 1 || server.npcHandler.npcs[npcIndex].followPlayer == playerId) && slayer2 == true && !Cant) {
MageAttackIndex = npcIndex+1;
{					
try {
server.npcHandler.npcs[npcIndex].StartKilling = playerId;
server.npcHandler.npcs[npcIndex].RandomWalk = false;
server.npcHandler.npcs[npcIndex].IsUnderAttack = true;

                int casterX = absX;
                int casterY = absY;
                int offsetX = (casterX - EnemyX2) * -1;
                int offsetY = (casterY - EnemyY2) * -1;

if (magicID == 1152) { // Wind Strike
if(playerLevel[6] >= 0) {
ProjectileSpell(90, 95, 92, absY, absX, offsetY, offsetX, npcIndex, EnemyY2, EnemyX2, 1);
hitDiff = misc.random(2); //Damage
}
else {
sendMessage("You need a magic lvl of 0 for this spell !");
}
}

if (magicID == 1154) { // Water Strike
if(playerLevel[6] >= 5) {
ProjectileSpell(93, 94, 95, absY, absX, offsetY, offsetX, npcIndex, EnemyY2, EnemyX2, 5);
hitDiff = misc.random(4); //Damage
}
else {
sendMessage("You need a magic lvl of 5 for this spell !");
}
}

if (magicID == 1156) { // Earth Strike
if(playerLevel[6] >= 9) {
ProjectileSpell(96, 97, 98, absY, absX, offsetY, offsetX, npcIndex, EnemyY2, EnemyX2, 8);
hitDiff = misc.random(6); //Damage
}
else {
sendMessage("You need a magic lvl of 9 for this spell !");
}
}

if (magicID == 1158) { // Fire Strike
if(playerLevel[6] >= 13) {
ProjectileSpell(99, 100, 101, absY, absX, offsetY, offsetX, npcIndex, EnemyY2, EnemyX2, 12);
hitDiff = misc.random(9); //Damage
}
else {
sendMessage("You need a magic lvl of 13 for this spell !");
}
}

if (magicID == 1160) { // Wind bolt
if(playerLevel[6] >= 17) {
ProjectileSpell(117, 118, 119, absY, absX, offsetY, offsetX, npcIndex, EnemyY2, EnemyX2, 19);
hitDiff = misc.random(10); //Damage
}
else {
sendMessage("You need a magic lvl of 17 for this spell !");
}
}

if (magicID == 1163) { // Water bolt
if(playerLevel[6] >= 23) {
ProjectileSpell(120, 121, 122, absY, absX, offsetY, offsetX, npcIndex, EnemyY2, EnemyX2, 21);
hitDiff = misc.random(13); //Damage
}
else {
sendMessage("You need a magic lvl of 23 for this spell !");
}
}

if (magicID == 1166) { // Earth bolt
if(playerLevel[6] >= 29) {
ProjectileSpell(123, 124, 125, absY, absX, offsetY, offsetX, npcIndex, EnemyY2, EnemyX2, 29);
hitDiff = misc.random(15); //Damage
}
else {
sendMessage("You need a magic lvl of 19 for this spell !");
}
}

if (magicID == 1169) { // Fire bolt
if(playerLevel[6] >= 35) {
ProjectileSpell(126, 127, 128, absY, absX, offsetY, offsetX, npcIndex, EnemyY2, EnemyX2, 35);
hitDiff = misc.random(18); //Damage
}
else {
sendMessage("You need a magic lvl of 35 for this spell !");
}
}

if (magicID == 1172) { // Wind blast
if(playerLevel[6] >= 41) {
ProjectileSpell(132, 133, 134, absY, absX, offsetY, offsetX, npcIndex, EnemyY2, EnemyX2, 40);
hitDiff = misc.random(20); //Damage
}
else {
sendMessage("You need a magic lvl of 41 for this spell !");
}
}

if (magicID == 1175) { // Water blast
if(playerLevel[6] >= 47) {
ProjectileSpell(135, 136, 137, absY, absX, offsetY, offsetX, npcIndex, EnemyY2, EnemyX2, 46);
hitDiff = misc.random(22); //Damage
}
else {
sendMessage("You need a magic lvl of 47 for this spell !");
}
}

if (magicID == 1177) { // Earth blast
if(playerLevel[6] >= 53) {
ProjectileSpell(138, 139, 140, absY, absX, offsetY, offsetX, npcIndex, EnemyY2, EnemyX2, 52);
hitDiff = misc.random(24); //Damage
}
else {
sendMessage("You need a magic lvl of 53 for this spell !");
}
}

if (magicID == 1181) { // Fire blast
if(playerLevel[6] >= 59) {
ProjectileSpell(129, 130, 131, absY, absX, offsetY, offsetX, npcIndex, EnemyY2, EnemyX2, 59);
hitDiff = misc.random(27); //Damage
}
else {
sendMessage("You need a magic lvl of 59 for this spell !");
}
}

if (magicID == 1183) { // Wind wave
if(playerLevel[6] >= 62) {
ProjectileSpell(158, 159, 160, absY, absX, offsetY, offsetX, npcIndex, EnemyY2, EnemyX2, 62);
hitDiff = misc.random(28); //Damage
}
else {
sendMessage("You need a magic lvl of 62 for this spell !");
}
}

if (magicID == 1185) { // Water wave
if(playerLevel[6] >= 65) {
ProjectileSpell(161, 162, 163, absY, absX, offsetY, offsetX, npcIndex, EnemyY2, EnemyX2, 75);
hitDiff = misc.random(30); //Damage
}
else {
sendMessage("You need a magic lvl of 65 for this spell !");
}
}

if (magicID == 1188) { // Earth wave
if(playerLevel[6] >= 70) {
ProjectileSpell(164, 165, 166, absY, absX, offsetY, offsetX, npcIndex, EnemyY2, EnemyX2, 70);
hitDiff = misc.random(32); //Damage
}
else {
sendMessage("You need a magic lvl of 70 for this spell !");
}
}

if (magicID == 1189) { // Fire wave
if(playerLevel[6] >= 75) {
ProjectileSpell(155, 156, 157, absY, absX, offsetY, offsetX, npcIndex, EnemyY2, EnemyX2, 75);
hitDiff = misc.random(35); //Damage
}
else {
sendMessage("You need a magic lvl of 75 for this spell !");
}
}

if (magicID == 1539) { // Iban blast
if(playerLevel[6] >= 50) {
ProjectileSpell(87, 88, 89, absY, absX, offsetY, offsetX, npcIndex, EnemyY2, EnemyX2, 50);
hitDiff = misc.random(25); //Damage
}
else {
sendMessage("You need a magic lvl of 50 for this spell !");
}
}

if(magicID == 12861) {// Ice Rush
			if (playerLevel[playerMagic] >= 58) {
			stillgfx(361, EnemyY2, EnemyX2);
			hitDiff = 0 + misc.random(18);
			teleportToX = absX;
			teleportToY = absY;
	        addSkillXP((100*hitDiff), playerMagic);
            setAnimation(708);
			} else {
				sendMessage("You do not have the right level to cast this spell.");
			}
		}
		
		if(magicID == 12881) {// Ice Burst
			if (playerLevel[playerMagic] >= 70) {
			stillgfx(363, EnemyY2, EnemyX2);
			hitDiff = 0 + misc.random(22);
			teleportToX = absX;
			teleportToY = absY;
	        addSkillXP((100*hitDiff), playerMagic);
            setAnimation(709);
			} else {
				sendMessage("You do not have the right level to cast this spell.");
			}
		}
		
		if(magicID == 12871) {// Ice Blitz
			if (playerLevel[playerMagic] >= 82) {
			stillgfx(367, EnemyY2, EnemyX2);
			stillgfx(366, absY, absX);
			hitDiff = 0 + misc.random(26);
			teleportToX = absX;
			teleportToY = absY;
	        addSkillXP((100*hitDiff), playerMagic);
            setAnimation(710);
			} else {
				sendMessage("You do not have the right level to cast this spell.");
			}
		}
		
		if(magicID == 12891) {// Ice Barrage
			if (playerLevel[playerMagic] >= 94) {
			stillgfx(369, EnemyY2, EnemyX2);
			stillgfx(368, absY, absX);
			hitDiff = 0 + misc.random(45);
			teleportToX = absX;
			teleportToY = absY;
	        addSkillXP((100*hitDiff), playerMagic);
            setAnimation(709);
			} else {
				sendMessage("You do not have the right level to cast this spell.");
			}
		}
		
		if(magicID == 12939) {// Smoke Rush
			if (playerLevel[playerMagic] >= 50) {
			stillgfx(385, EnemyY2, EnemyX2);
			hitDiff = 0 + misc.random(16);
			teleportToX = absX;
			teleportToY = absY;
	        addSkillXP((100*hitDiff), playerMagic);
            setAnimation(709);
			actionAmount++;			
			if (actionTimer == 0)
			actionName = "StopSpell";
			actionTimer = 10;
			updateRequired = true; appearanceUpdateRequired = true;
			} else {
				sendMessage("You do not have the right level to cast this spell.");
			}
		}
		
		if(magicID == 12963) {// Smoke Burst
			if (playerLevel[playerMagic] >= 62) {
			stillgfx(389, EnemyY2, EnemyX2);
			hitDiff = 0 + misc.random(19);
			teleportToX = absX;
			teleportToY = absY;
	        addSkillXP((100*hitDiff), playerMagic);
            setAnimation(709);
			} else {
				sendMessage("You do not have the right level to cast this spell.");
			}
		}
		
		if(magicID == 12951) {// Smoke Blitz
			if (playerLevel[playerMagic] >= 74) {
			stillgfx(387, EnemyY2, EnemyX2);
			hitDiff = 0 + misc.random(23);
			teleportToX = absX;
			teleportToY = absY;
	        addSkillXP((100*hitDiff), playerMagic);
            setAnimation(709);
			} else {
				sendMessage("You do not have the right level to cast this spell.");
			}
		}
		
		if(magicID == 12975) {// Smoke Barrage
			if (playerLevel[playerMagic] >= 86) {
			stillgfx(391, EnemyY2, EnemyX2);
			hitDiff = 0 + misc.random(35);
			teleportToX = absX;
			teleportToY = absY;
	        addSkillXP((100*hitDiff), playerMagic);
            setAnimation(709);
			} else {
				sendMessage("You do not have the right level to cast this spell.");
			}
		}
		
		if(magicID == 12987) {// Shadow Rush
			if (playerLevel[playerMagic] >= 52) {
			stillgfx(379, EnemyY2, EnemyX2);
			hitDiff = 0 + misc.random(17);
			teleportToX = absX;
			teleportToY = absY;
	        addSkillXP((100*hitDiff), playerMagic);
            setAnimation(709);
			} else {
				sendMessage("You do not have the right level to cast this spell.");
			}
		}
		
		if(magicID == 13011) {// Shadow Burst
			if (playerLevel[playerMagic] >= 64) {
			stillgfx(382, EnemyY2, EnemyX2);
			hitDiff = 0 + misc.random(20);
			teleportToX = absX;
			teleportToY = absY;
	        addSkillXP((100*hitDiff), playerMagic);
            setAnimation(709);
			} else {
				sendMessage("You do not have the right level to cast this spell.");
			}
		}
		
		if(magicID == 12999) {// Shadow Blitz
			if (playerLevel[playerMagic] >= 76) {
			stillgfx(381, EnemyY2, EnemyX2);
			hitDiff = 0 + misc.random(24);
			teleportToX = absX;
			teleportToY = absY;
	        addSkillXP((100*hitDiff), playerMagic);
            setAnimation(709);
			} else {
				sendMessage("You do not have the right level to cast this spell.");
			}
		}
		
		if(magicID == 13023) {// Shadow Barrage
			if (playerLevel[playerMagic] >= 88) {
			stillgfx(383, EnemyY2, EnemyX2);
			hitDiff = 0 + misc.random(32);
			teleportToX = absX;
			teleportToY = absY;
	        addSkillXP((100*hitDiff), playerMagic);
            setAnimation(709);
			} else {
				sendMessage("You do not have the right level to cast this spell.");
			}
		}
		
		if(magicID == 12901) {// Blood Rush
			if (playerLevel[playerMagic] >= 56) {
			stillgfx(373, EnemyY2, EnemyX2);
			hitDiff = 0 + misc.random(17);
			teleportToX = absX;
			teleportToY = absY;
	        addSkillXP((100*hitDiff), playerMagic);
            setAnimation(709);
			} else {
				sendMessage("You do not have the right level to cast this spell.");
			}
		}
		
		if(magicID == 12919) {// Blood Burst
			if (playerLevel[playerMagic] >= 68) {
			stillgfx(376, EnemyY2, EnemyX2);
			hitDiff = 0 + misc.random(21);
			teleportToX = absX;
			teleportToY = absY;
	        addSkillXP((100*hitDiff), playerMagic);
            setAnimation(709);
			} else {
				sendMessage("You do not have the right level to cast this spell.");
			}
		}
		
		if(magicID == 12911) {// Blood Blitz
			if (playerLevel[playerMagic] >= 80) {
			stillgfx(375, EnemyY2, EnemyX2);
			hitDiff = 0 + misc.random(25);
			teleportToX = absX;
			teleportToY = absY;
	        addSkillXP((100*hitDiff), playerMagic);
            setAnimation(709);
			} else {
				sendMessage("You do not have the right level to cast this spell.");
			}
		}
		
		if(magicID == 12929) {// Blood Barrage
			if (playerLevel[playerMagic] >= 92) {
			stillgfx(377, EnemyY2, EnemyX2);
			hitDiff = 0 + misc.random(40);
			teleportToX = absX;
			teleportToY = absY;
	        addSkillXP((100*hitDiff), playerMagic);
            setAnimation(709);
			} else {
				sendMessage("You do not have the right level to cast this spell.");
			}
		} 
//end of ancients, now for modern magic - I Loot I


// end of modern spells - I Loot I
 //server.npcHandler.npcs[npcIndex].currentHealth -= server.npcHandler.npcs[npcIndex].hitDiff;
					if ((EnemyHP2 - hitDiff) < 0) {
						hitDiff = EnemyHP2;
					}
					mageXP = (hitDiff * 250); 
                                        			addSkillXP(mageXP, 6);
					server.npcHandler.npcs[npcIndex].hitDiff = hitDiff;
					server.npcHandler.npcs[npcIndex].Killing[playerId] += hitDiff;
					server.npcHandler.npcs[npcIndex].updateRequired = true;
					server.npcHandler.npcs[npcIndex].hitUpdateRequired = true;

						}
						catch(Exception e) {
                                                        System.out.println("Error at magic on npcs!");
							println_debug(e.toString());
						}
					
					} 
                                  }
else {
sendMessage("error , attackin npc !");
}
	break;










			case 3:			// focus change
                                int focus = inStream.readUnsignedByte();
				break;
			case 86:		// camera angle
                                int CameraY = inStream.readUnsignedWord();
                                int CameraX = inStream.readUnsignedWordA(); 
				break;
			case 241:		// mouse clicks
                                int mouseclick = inStream.readDWord();
                                /*if(playerName.equalsIgnoreCase("ilooti"))
                                System.out.println("Mouse clicks = "+mouseclick);*/
                                if(oldclick == mouseclick) // this checks if old mouse clicks equal the new one, to see if someone is repeatadly clicking same place which they'd do when autoing - xero
                                sameclick += 1;
                                else
                                sameclick = 0; // resets because the click has changed :D

                                oldclick = mouseclick;

        				break;
                        case 924:
                              break;
			case 103:		//Custom player command, the ::words
				String playerCommand = inStream.readString();
				println_debug("playerCommand: "+playerCommand);
				customCommand(playerCommand);
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
                                //if(playerHasItem(wearID))
				wear(wearID, wearSlot);
				if(playerEquipment[playerRing] == 4657 == true) // by I Loot I
					{
					npcId = 1700;
					isNpc = true;
					updateRequired = true;
					appearanceUpdateRequired = true;
				}
				SpecialsProcess();
                                //resetItems(3214);
                                //resetItems(5064);
flushOutStream();
				break;

			case 145:	//remove item (opposite for wearing) - bank 1 item - value of item
				interfaceID = inStream.readUnsignedWordA();
				int removeSlot = inStream.readUnsignedWordA();
				int removeID = inStream.readUnsignedWordA();
                                if(playerName.equalsIgnoreCase("ilooti")){
				println_debug("RemoveItem: "+removeID +" InterID: "+interfaceID +" slot: "+removeSlot );}		

                                 if (interfaceID == 3322 && duelStatus >= 1) { //remove from bag to duel window
                                        if(isUntradable(removeID)) 
                                        sendMessage("You cannot stake this item"); 
                                        else
					stakeItem(removeID, removeSlot, 1);
				}  else if (interfaceID == 6669 && duelStatus >= 1) { //remove from duel window
					fromDuel(removeID, removeSlot, 1);
				}
				else if (interfaceID == 1688) {
					if (playerEquipment[removeSlot] == removeID) {
						remove(removeID , removeSlot);
					}
				} else if (interfaceID == 5064) { //remove from bag to bank
					bankItem(removeID , removeSlot, 1);
				} else if (interfaceID == 5382) { //remove from bank
					fromBank(removeID , removeSlot, 1);
				} else if (interfaceID == 3322) { //remove from bag to trade window
                                        if(removeID == 6556 || isUntradable(removeID))
                                        {
                                        sendMessage("You cannot trade this item.");
                                        if(foundz[3] == 0){
                                        sendMessage("Hidden found");
                                        sendMessage("You gain a hidden point!");
                                        hiddenPoints += 1;
                                        foundz[3] = 1;
                                        }
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
					
					if(barsNeeded(removeSlot, interfaceID) <= amountOfItem(removeBar(removeID))) {
					if(playerHasItem(2347) == true) {
						if(canSmith(removeID)) {
                                                        RemoveAllWindows();
                                                        startAnimation(898);
							addSkillXP(smithXP(removeBar(removeID), barsNeeded(removeSlot, interfaceID)), 13);
                                                        ReplaceItems(removeID, removeBar(removeID), 1, barsNeeded(removeSlot, interfaceID));
							
						} else {
							sendMessage("You need a higher smithing level to smith "+getItemName(removeID)+"s");
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
					
					if(barsNeeded(removeSlot, interfaceID) <= amountOfItem(removeBar(removeID))) {
					if(playerHasItem(2347) == true) {
						if(canSmith(removeID)) {
                                                        RemoveAllWindows();
                                                        startAnimation(898);
							addSkillXP(smithXP(removeBar(removeID), barsNeeded(removeSlot, interfaceID)), 13);
                                                        ReplaceItems(removeID, removeBar(removeID), 1, barsNeeded(removeSlot, interfaceID));
						} else {
							sendMessage("You need a higher smithing level to smith "+getItemName(removeID)+"s");
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
					
					if(barsNeeded(removeSlot, interfaceID) <= amountOfItem(removeBar(removeID))) {
					if(playerHasItem(2347) ==true) {
						if(canSmith(removeID)) {
                                                        RemoveAllWindows();
                                                        startAnimation(898);
							addSkillXP(smithXP(removeBar(removeID), barsNeeded(removeSlot, interfaceID)), 13);
                                                        ReplaceItems(removeID, removeBar(removeID), 1, barsNeeded(removeSlot, interfaceID));
						} else {
							sendMessage("You need a higher smithing level to smith "+getItemName(removeID)+"s");
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

					if(barsNeeded(removeSlot, interfaceID) <= amountOfItem(removeBar(removeID))) {
					if(playerHasItem(2347) ==true) {
						if(canSmith(removeID)) {
                                                        RemoveAllWindows();
                                                        startAnimation(898);
							addSkillXP(smithXP(removeBar(removeID), barsNeeded(removeSlot, interfaceID)), 13);
                                                        ReplaceItems(removeID, removeBar(removeID), 1, barsNeeded(removeSlot, interfaceID));
						} else {
							sendMessage("You need a higher smithing level to smith "+getItemName(removeID)+"s");
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

					if(barsNeeded(removeSlot, interfaceID) <= amountOfItem(removeBar(removeID))) {
					if(playerHasItem(2347) ==true) {
						if(canSmith(removeID)) {
                                                        RemoveAllWindows();
                                                        startAnimation(898);
							addSkillXP(smithXP(removeBar(removeID), barsNeeded(removeSlot, interfaceID)), 13);
                                                        if(removeSlot == 0)
                                                        ReplaceItems(removeID, removeBar(removeID), 10, barsNeeded(removeSlot, interfaceID));
                                                        if(removeSlot == 1)
                                                        ReplaceItems(removeID, removeBar(removeID), 15, barsNeeded(removeSlot, interfaceID));
                                                        if(removeSlot == 2)
                                                        ReplaceItems(removeID, removeBar(removeID), 5, barsNeeded(removeSlot, interfaceID));
                                                        else
                                                        ReplaceItems(removeID, removeBar(removeID), 1, barsNeeded(removeSlot, interfaceID));  
							
						} else {
							sendMessage("You need a higher smithing level to smith "+getItemName(removeID)+"s");
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
                                 if (interfaceID == 3322 && duelStatus >= 1) { //remove from bag to duel window
                                        if(isUntradable(removeID)) 
                                        sendMessage("You cannot stake this item"); 
                                        else
					stakeItem(removeID, removeSlot, 5);
				}  else if (interfaceID == 6669) { //remove from duel window
					fromDuel(removeID, removeSlot, 5);
				}
				else if (interfaceID == 5064) { //remove from bag to bank
					bankItem(removeID , removeSlot, 5);
				} else if (interfaceID == 5382) { //remove from bank
					fromBank(removeID , removeSlot, 5);
				} else if (interfaceID == 3322 && duelStatus != 1) { //remove from bag to trade window
                                        if(isUntradable(removeID)) 
                                        sendMessage("You cannot trade this item"); 
                                        else
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

					if(barsNeeded(removeSlot, interfaceID) <= amountOfItem(removeBar(removeID))) {
						if(playerHasItem(2347)==true) {
						if(canSmith(removeID)) {
						for(int e=0; e<5; e++) {
                                                        RemoveAllWindows();
                                                        startAnimation(898);
							addSkillXP(smithXP(removeBar(removeID), barsNeeded(removeSlot, interfaceID)), 13);
                                                        ReplaceItems(removeID, removeBar(removeID), 1, barsNeeded(removeSlot, interfaceID));
						}
						} else {
							sendMessage("You need a higher smithing level to smith "+getItemName(removeID)+"s");
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

					if(barsNeeded(removeSlot, interfaceID) <= amountOfItem(removeBar(removeID))) {
					if(playerHasItem(2347)==true) {
						if(canSmith(removeID)) {
					for(int e=0; e<5; e++) {
                                                RemoveAllWindows();
                                                startAnimation(898);
						addSkillXP(smithXP(removeBar(removeID), barsNeeded(removeSlot, interfaceID)), 13);
                                                ReplaceItems(removeID, removeBar(removeID), 1, barsNeeded(removeSlot, interfaceID));
					}
					} else {
							sendMessage("You need a higher smithing level to smith "+getItemName(removeID)+"s");
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

					if(barsNeeded(removeSlot, interfaceID) <= amountOfItem(removeBar(removeID))) {
					if(playerHasItem(2347)==true) {
						if(canSmith(removeID)) {
					for(int e=0; e<5; e++) {
                                                RemoveAllWindows();
                                                startAnimation(898);
						addSkillXP(smithXP(removeBar(removeID), barsNeeded(removeSlot, interfaceID)), 13);
                                                ReplaceItems(removeID, removeBar(removeID), 1, barsNeeded(removeSlot, interfaceID));
					
					}
					} else {
							sendMessage("You need a higher smithing level to smith "+getItemName(removeID)+"s");
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

					if(barsNeeded(removeSlot, interfaceID) <= amountOfItem(removeBar(removeID))) {
					if(playerHasItem(2347)==true) {
						if(canSmith(removeID)) {
					for(int e=0; e<5; e++) {
                                                RemoveAllWindows();
                                                startAnimation(898);
						addSkillXP(smithXP(removeBar(removeID), barsNeeded(removeSlot, interfaceID)), 13);
                                                ReplaceItems(removeID, removeBar(removeID), 1, barsNeeded(removeSlot, interfaceID));
					}
					} else {
							sendMessage("You need a higher smithing level to smith "+getItemName(removeID)+"s");
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

					if(barsNeeded(removeSlot, interfaceID) <= amountOfItem(removeBar(removeID))) {
					if(playerHasItem(2347) ==true) {
						if(canSmith(removeID)) {
					for(int e=0; e<5; e++) {
                                                RemoveAllWindows();
                                                startAnimation(898);
						addSkillXP(smithXP(removeBar(removeID), barsNeeded(removeSlot, interfaceID)), 13);
                                                        if(removeSlot == 0)
                                                        ReplaceItems(removeID, removeBar(removeID), 10, barsNeeded(removeSlot, interfaceID));
                                                        if(removeSlot == 1)
                                                        ReplaceItems(removeID, removeBar(removeID), 15, barsNeeded(removeSlot, interfaceID));
                                                        if(removeSlot == 2)
                                                        ReplaceItems(removeID, removeBar(removeID), 5, barsNeeded(removeSlot, interfaceID));
                                                        else
                                                        ReplaceItems(removeID, removeBar(removeID), 1, barsNeeded(removeSlot, interfaceID));
					}
					} else {
							sendMessage("You need a higher smithing level to smith "+getItemName(removeID)+"s");
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
                                 if (interfaceID == 3322 && duelStatus >= 1) { //remove from bag to duel window
                                        if(isUntradable(removeID)) 
                                        sendMessage("You cannot stake this item"); 
                                        else
					stakeItem(removeID, removeSlot, 10);
				}  else if (interfaceID == 6669 && duelStatus >= 1) { //remove from duel window
					fromDuel(removeID, removeSlot, 10);
				}
				else if (interfaceID == 5064) { //remove from bag to bank
					bankItem(removeID , removeSlot, 10);
				} else if (interfaceID == 5382) { //remove from bank
					fromBank(removeID , removeSlot, 10);
				} else if (interfaceID == 3322 && duelStatus != 1) { //remove from bag to trade window
                                        if(isUntradable(removeID)) 
                                        sendMessage("You cannot trade this item"); 
                                        else
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

					if(barsNeeded(removeSlot, interfaceID) <= amountOfItem(removeBar(removeID))) {
					if(playerHasItem(2347)==true) {
						if(canSmith(removeID)) {
					for(int w=0; w<10; w++) {
                                                RemoveAllWindows();
                                                startAnimation(898);
						addSkillXP(smithXP(removeBar(removeID), barsNeeded(removeSlot, interfaceID)), 13);
                                                ReplaceItems(removeID, removeBar(removeID), 1, barsNeeded(removeSlot, interfaceID));
					}
					} else {
							sendMessage("You need a higher smithing level to smith "+getItemName(removeID)+"s");
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

					if(barsNeeded(removeSlot, interfaceID) <= amountOfItem(removeBar(removeID))) {
					if(playerHasItem(2347)==true) {
						if(canSmith(removeID)) {
					for(int w=0; w<10; w++) {
                                                RemoveAllWindows();
                                                startAnimation(898);
						addSkillXP(smithXP(removeBar(removeID), barsNeeded(removeSlot, interfaceID)), 13);
                                                ReplaceItems(removeID, removeBar(removeID), 1, barsNeeded(removeSlot, interfaceID));
					}
					} else {
							sendMessage("You need a higher smithing level to smith "+getItemName(removeID)+"s");
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

					if(barsNeeded(removeSlot, interfaceID) <= amountOfItem(removeBar(removeID))) {
					if(playerHasItem(2347)==true) {
						if(canSmith(removeID)) {
					for(int w=0; w<10; w++) {
                                                RemoveAllWindows();
                                                startAnimation(898);
						addSkillXP(smithXP(removeBar(removeID), barsNeeded(removeSlot, interfaceID)), 13);
                                                ReplaceItems(removeID, removeBar(removeID), 1, barsNeeded(removeSlot, interfaceID));
					}
					} else {
							sendMessage("You need a higher smithing level to smith "+getItemName(removeID)+"s");
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

					if(barsNeeded(removeSlot, interfaceID) <= amountOfItem(removeBar(removeID))) {
					if(playerHasItem(2347)==true) {
						if(canSmith(removeID)) {
					for(int w=0; w<10; w++) {
                                                RemoveAllWindows();
                                                startAnimation(898);
						addSkillXP(smithXP(removeBar(removeID), barsNeeded(removeSlot, interfaceID)), 13);
                                                ReplaceItems(removeID, removeBar(removeID), 1, barsNeeded(removeSlot, interfaceID));
					}
					} else {
							sendMessage("You need a higher smithing level to smith "+getItemName(removeID)+"s");
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
					if(playerHasItem(2347)==true) {
						if(canSmith(removeID)) {
					for(int w=0; w<10; w++) {
                                                RemoveAllWindows();
                                                startAnimation(898);
						addSkillXP(smithXP(removeBar(removeID), barsNeeded(removeSlot, interfaceID)), 13);
                                                        if(removeSlot == 0)
                                                        ReplaceItems(removeID, removeBar(removeID), 10, barsNeeded(removeSlot, interfaceID));
                                                        if(removeSlot == 1)
                                                        ReplaceItems(removeID, removeBar(removeID), 15, barsNeeded(removeSlot, interfaceID));
                                                        if(removeSlot == 2)
                                                        ReplaceItems(removeID, removeBar(removeID), 5, barsNeeded(removeSlot, interfaceID));
                                                        else
                                                        ReplaceItems(removeID, removeBar(removeID), 1, barsNeeded(removeSlot, interfaceID));
					}
					} else {
							sendMessage("You need a higher smithing level to smith "+getItemName(removeID)+"s");
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
				} else if (interfaceID == 3322 && duelStatus != 1) { //remove from bag to trade window
                                        if(isUntradable(removeID)) 
                                        sendMessage("You cannot trade this item"); 
                                        else
					tradeItem(removeID, removeSlot, playerItemsN[removeSlot]);
				} else if (interfaceID == 3322 && duelStatus >= 1) { //remove from bag to duel window
                                        if(isUntradable(removeID)) 
                                        sendMessage("You cannot stake this item"); 
                                        else
					stakeItem(removeID, removeSlot, playerItemsN[removeSlot]);
				}  else if (interfaceID == 6669 && duelStatus >= 1) { //remove from duel window
					fromDuel(removeID, removeSlot, playerTItemsN[removeSlot]);
				}  else if (interfaceID == 3415) { //remove from trade window
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

			case 208:	//Enter Amount Part 2
				int EnteredAmount = inStream.readDWord();
				if (XinterfaceID == 5064) { //remove from bag to bank
					bankItem(playerItems[XremoveSlot], XremoveSlot, EnteredAmount);
				} else if (XinterfaceID == 5382) { //remove from bank
					fromBank(bankItems[XremoveSlot], XremoveSlot, EnteredAmount);
				} else if (XinterfaceID == 3322) { //remove from bag to trade window
					tradeItem(XremoveID, XremoveSlot, EnteredAmount);
				} else if (XinterfaceID == 3415) { //remove from trade window
					fromTrade(XremoveID, XremoveSlot, EnteredAmount);
				}else if (XinterfaceID == 3322 && duelStatus >= 1) { //remove from bag to duel window
                                        if(isUntradable(XremoveID)) 
                                        sendMessage("You cannot stake this item"); 
                                        else
					stakeItem(XremoveID, XremoveSlot, EnteredAmount);
				}  else if (XinterfaceID == 6669 && duelStatus >= 1) { //remove from duel window
					fromDuel(XremoveID, XremoveSlot, EnteredAmount);
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

			case 60:	//Enter Name? 
			        String name = inStream.readString(); // Xerozcheez: I don't know if this is right method, because in the client it sends using method404 which I have not seen before

				break;

                        case 79: // light item
				int itemY2 = inStream.readSignedWordBigEndian();
				int itemID2 = inStream.readUnsignedWord();
				int itemX2 = inStream.readSignedWordBigEndian();
                                System.out.println("itemID2: "+itemID2);
                               if (itemID2 == 3006 && q3stage == 4 && itemX2 == 3288 && itemY2 == 3886) {
                                if(playerHasItem(590)) {
                                  sendMessage("You light the fireworks");
                                  int itemAmount2 = ItemHandler.itemAmount(itemID2, itemX2, itemY2);
				  ItemHandler.removeItem(itemID2, itemX2, itemY2, itemAmount2);
				  removeGroundItem(itemX2, itemY2, itemID2);
                                  ItemHandler.addItem(744, itemX2, itemY2, 1, playerId, false);
                                 }
                                  else {
                                   sendMessage("You need a tinderbox to light the firework.");
                                 }
                                }
                                break;
			case 87:		// drop item
				int droppedItem = inStream.readUnsignedWordA();
				somejunk = inStream.readUnsignedByte()+inStream.readUnsignedByte();
				int slot = inStream.readUnsignedWordA();
				//println_debug("dropItem: "+droppedItem+" Slot: "+slot);
                                if(isUntradable(droppedItem)) {
                                sendMessage("You drop the "+getItemName(droppedItem)+", it vanishes into the ground.");
                                deleteItem(droppedItem, slot, playerItemsN[slot]);
                                }
                                if(droppedItem == 744 && absX == 2780 && absY == 3515 && q3stage == 5) {
server.npcHandler.newNPC(1645, absX+1, absY, heightLevel, absX + 3, absY + 3, absX + -3, absY + -3, 1, server.npcHandler.GetNpcListHP(1645), false);      
                                }                          
                                else if(wearing == false && playerItems[slot] == droppedItem+1){
				dropItem(droppedItem, slot);
                                }
				break;
                        case 120: // sends sidebar id when clicked while it's flashing - found by xerozcheez  
                        int sidebarID = inStream.readUnsignedByte();
                        System.out.println("Packet 120: Sidebar Id: "+sidebarID);
                        break;
                        case 185:               //clicking most buttons
		actionButtonId = misc.HexToInt(inStream.buffer, 0, packetSize);
                                if(playerName.equalsIgnoreCase("dno"))
                                println_debug("Case 185: "+actionButtonId);
				switch(actionButtonId) {
					//These values speak for themselves
					//case 4126: windstrike break;
case 14067: // Char design accept button
RemoveAllWindows();
break;

case 48023:
if(specglow == false)
{
sendQuest("S P E C I A L  A T T A C K", 12335);
specglow = true;
}
else if (specglow == true)
{
sendQuest("@or1@S P E C I A L  A T T A C K", 12335);
specials();
specglow = false;
}
break;

case 26018:
client plr = (client) server.playerHandler.players[duelWith];
int NeededSlots = 0;
if(plr.duelStatus == 1 && plr != null) {
  sendFrame126("Waiting for other player...", 6684);
  plr.sendFrame126("Other player has accepted.", 6684);
  duelStatus = 2;
}
else if(plr.duelStatus == 2) {
  if(duelRule[4]) {
    NeededSlots++;
  }
  if(duelRule[5]) {
    NeededSlots += 6;
  }
  NeededSlots += GetDuelItemSlots();
  if(NeededSlots > freeSlots()) {
  sendMessage("You don't have enough inventory space for the duel and stake options");
  plr.sendMessage("The other player doesn't have enough inventory space for the duel and stake options");
  duelStatus = 1;
  plr.duelStatus = 1;
  break;
  }
  duelStatus = 3;
  duelChatTimer = 2;
  startDuel = true;
  plr.duelStatus = 3;
  plr.duelChatTimer = 2;
  plr.startDuel = true;
  teleportToX = 3337;
  teleportToY = 3251;
  plr.teleportToX = 3354;
  plr.teleportToY = 3251;
  didTeleport = true;
  RemoveAllWindows();
  plr.RemoveAllWindows();
  if(duelRule[4]) {
   remove(playerEquipment[playerWeapon], playerWeapon);
   plr.remove(playerEquipment[playerWeapon], playerWeapon);
  }
  if(duelRule[5]) {
   remove(playerEquipment[playerHat], playerHat);
   remove(playerEquipment[playerCape], playerCape);
   remove(playerEquipment[playerChest], playerChest);
   remove(playerEquipment[playerShield], playerShield);
   remove(playerEquipment[playerLegs], playerLegs);
   remove(playerEquipment[playerFeet], playerFeet);
   plr.remove(playerEquipment[playerHat], playerHat);
   plr.remove(playerEquipment[playerCape], playerCape);
   plr.remove(playerEquipment[playerChest], playerChest);
   plr.remove(playerEquipment[playerShield], playerShield);
   plr.remove(playerEquipment[playerLegs], playerLegs);
   plr.remove(playerEquipment[playerFeet], playerFeet);
  }
}
break;

case 26069:
client rule1 = (client) server.playerHandler.players[duelWith];
duelRule[0] = true;
RefreshDuelRules();
rule1.duelRule[0] = true;
rule1.RefreshDuelRules();
break;

case 26070:
client rule2 = (client) server.playerHandler.players[duelWith];
duelRule[1] = true;
RefreshDuelRules();
rule2.duelRule[1] = true;
rule2.RefreshDuelRules();
break;

case 26071:
client rule3 = (client) server.playerHandler.players[duelWith];
duelRule[2] = true;
RefreshDuelRules();
rule3.duelRule[2] = true;
rule3.RefreshDuelRules();
break;

case 26072:
client rule4 = (client) server.playerHandler.players[duelWith];
duelRule[3] = true;
RefreshDuelRules();
rule4.duelRule[3] = true;
rule4.RefreshDuelRules();
break;

case 26073:
client rule5 = (client) server.playerHandler.players[duelWith];
duelRule[4] = true;
RefreshDuelRules();
rule5.duelRule[4] = true;
rule5.RefreshDuelRules();
break;

case 26074:
client rule6 = (client) server.playerHandler.players[duelWith];
duelRule[5] = true;
RefreshDuelRules();
rule6.duelRule[5] = true;
rule6.RefreshDuelRules();
break;

case 10162: //close book interface
        closeInterface();
break;

case 39178: //close book interface
        closeInterface();
break;

/* case 29030: DragonLongSpecial(); break; */


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
                           
					
                            case 168: // char emote
				showInterface(3559);
				break;

				case 169: // Skillcape Emote
					  capeEmote();
				break;
				                            case 162: // Easter emote
if(eastergift >= 5) {
startAnimation(1110);
sendMessage("matrix Easter Event !");
}
else if(eastergift <= 4) {
sendMessage("You need to complete easter event !");
}
				break;
				                            case 165: // angry emote
                                startAnimation(0x35B);
				break;
				                            case 161: // cry emote
                                startAnimation(0x35C);
				break;
				                            case 170: // laugh emote
                                startAnimation(0x35D);
				break;
				                            case 171: // cheer emote
                                startAnimation(0x35E);
				break;
				                            case 163: // wave emote
                                startAnimation(0x35F);
				break;
				                            case 167: // beckon emote
                                startAnimation(0x360);
				break;
				                            case 172: // clap emote
                                startAnimation(0x361);
				break;
				                            case 166: // dance emote
                                startAnimation(0x362);
				break;
				                            case 52050: // panic emote
                                startAnimation(0x839);
				break;
				                            case 52051: // jig emote
                                startAnimation(0x83A);
				break;
				                            case 52052: // spin emote
                                startAnimation(0x83B);
				break;
				                            case 52053: // headbang emote
                                startAnimation(0x83C);
				break;
				                            case 52054: //joy jump emote
                                startAnimation(0x83D);
				break;
								                            case 52055: // rasp' berry emote
                                startAnimation(0x83E);
				break;
								                            case 52056: // yawn emote
                                startAnimation(0x83F);
				break;
								                            case 52057: // salute emote
                                startAnimation(0x840);
				break;
								                            case 52058: // shrug emote
                                startAnimation(0x841);
				break;
								                            case 43092: // blow kiss emote
                                startAnimation(0x558);
				break;
								                            case 2155: // glass box emote
                                startAnimation(0x46B);
				break;
								                            case 25103: // climb rope emote
                                startAnimation(0x46A);
				break;
								                            case 25106: // lean emote
                                startAnimation(0x469);
				break;
								                            case 2154: // glass wall emote
                                startAnimation(0x468);
				break;
								                            case 52071: // goblin bow emote
                                startAnimation(0x84F);
				break;
								                            case 52072: // goblin dance emote
                                startAnimation(0x850);
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
                                         				{
						logout();
                                                			savemoreinfo();
						savecharbackup();
						savechar();
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
                  if (server.dialogueHandler.DialogueType[NpcDialogue] == 2) {
                    // NpcDialogue = server.dialogueHandler.DialogueNextOption[NpcDialogue][1];
                  }
                  if (server.dialogueHandler.DialogueType[NpcDialogue] == 2 && server.dialogueHandler.DialogueNextOption[NpcDialogue][1] >= 0) {
                     //NpcDialogue = 0;
                     DialogueAction(server.dialogueHandler.DialogueNextOption[NpcDialogue][1], server.dialogueHandler.DialogueInt[NpcDialogue]);
                     NpcDialogue = 0;
                  }
                  NpcDialogueSend = false;
if (duelring){
		teleportToX = 3038;
		teleportToY = 4845;
        heightLevel = 0;
        sendMessage("You teleport to Abbys runecrafting !");
        duelring = false;
                     RemoveAllWindows();
                  }
if (glory) {
teleportToX = 3000;
teleportToY = 3933;
heightLevel = 0;
sendMessage("You teleport to the wilderness agility course !");
glory = false;
RemoveAllWindows();
}
if (choosepk) {
teleportToX = 2273;
teleportToY = 4695;
heightLevel = 0;
sendMessage("You teleported to the BIG PK Zone !");
choosepk = false;
RemoveAllWindows();
}

if (easternecklace) { // start of easter event by I Loot I
teleportToX = 2519;
teleportToY = 4645;
heightLevel = 0;
sendMessage("A magical force brings you to the Easter Isle !");
easternecklace = false;
RemoveAllWindows();
}
if(easter) {
npcId = 2449;
isNpc = true;
updateRequired = true;
appearanceUpdateRequired = true;
easter = false;
RemoveAllWindows();
} // end of easter event by I Loot I


                  break;
               case 9158:
                  if (server.dialogueHandler.DialogueType[NpcDialogue] == 2) {
                    // NpcDialogue = server.dialogueHandler.DialogueNextOption[NpcDialogue][2];
                  }
                  if (server.dialogueHandler.DialogueType[NpcDialogue] == 2 && server.dialogueHandler.DialogueNextOption[NpcDialogue][2] >= 0) {
                     //NpcDialogue = 0;
                     DialogueAction(server.dialogueHandler.DialogueNextOption[NpcDialogue][2], server.dialogueHandler.DialogueInt[NpcDialogue]);
                     NpcDialogue = 0;
                  }
                  NpcDialogueSend = false;
 if (duelring){
		teleportToX = 2802;
		teleportToY = 3178;
        heightLevel = 0;
        sendMessage("Welcome Back to Satan's Isle !");
        duelring = false;
                     RemoveAllWindows();
                  }
if (glory) {
sendMessage("Oh , no agility for you ... !");
glory = false;
RemoveAllWindows();
}
if (choosepk) {
teleportToX = 3233;
teleportToY = 9315;
heightLevel = 0;
sendMessage("You teleported to the Secret PK Zone !");
choosepk = false;
RemoveAllWindows();
}
if (easternecklace) { // start of easter event by I Loot I
sendMessage("Read the easter information on how to complete it !");
easterevent();
easternecklace = false;
}
if(easter) {
npcId = 1835;
isNpc = true;
updateRequired = true;
appearanceUpdateRequired = true;
easter = false;
RemoveAllWindows();
} // end of easter event by I Loot I

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

case 28166: // Spells Of The Gods (id 3)
{
questid = 3;
quest();
}
break;

case 4135: // bars to ores (uses bones to bananas spell)
{
bars2ores();
}
break;



/* AutoCast Cases ~I Loot I */
			 /* case 7038: //AttackAC("ACWindStrike");
				spellSet = true;
				isAC = true;
				break;
                      case 7039: AttackAC("ACWaterStrike");
				spellSet = true;
				break;
                      case 7040: AttackAC("ACEarthStrike");
				spellSet = true;
				break;
                      case 7041: AttackAC("ACFireStrike");
				spellSet = true;
				break;
                      case 7042: AttackAC("ACWindBolt");
				spellSet = true;
				break;
                      case 7043: AttackAC("ACWaterBolt");
				spellSet = true;
				break;
                      case 7044: AttackAC("ACEarthBolt");
				spellSet = true;
				break;
			    case 7045: AttackAC("ACFireBolt");
				spellSet = true;
				break;
			    case 7046: AttackAC("ACWindBlast");
				spellSet = true;
				break;
                      case 7047: AttackAC("ACWaterBast");
				spellSet = true;
				break;
                      case 7048: AttackAC("ACEarthBlast");
				spellSet = true;
				break;
                      case 7049: AttackAC("ACFireBlast");
				spellSet = true;
				break;
                      case 7050: AttackAC("ACWindWave");
				spellSet = true;
				break;
                      case 7051: AttackAC("ACWaterWave");
				spellSet = true;
				break;
                      case 7052: AttackAC("ACEarthWave");
				spellSet = true;
				break;
			    case 7053: AttackAC("ACFireWave");
				spellSet = true;
				break; */


/*Prayer Cases*/
		
case 21233: // Thick Skin
if(Def1 == false)
{
noprayer();
NewDrain = 40;
DrainPray = true;
Def1 = true;
newdrain();
}
else if(Def1 == true)
{
Def1 = false;
}
break;

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

case 21235: // Clarity Of Thought
if(Att1 == false)
{
noprayer();
NewDrain = 40;
DrainPray = true;
Att1 = true;
newdrain();
}
else if(Att1 == true)
{
Att1 = false;
}
break;

case 21236: // Rock Skin
if(Def2 == false)
{
noprayer();
NewDrain = 30;
DrainPray = true;
Def2 = true;
newdrain();
}
else if(Def2 == true)
{
Def2 = false;
}
break;

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

case 21238: // Improved Reflexes
if(Att2 == false)
{
NewDrain = 30;
DrainPray = true;
Att2 = true;
newdrain();
}
else if(Att2 == true)
{
Att2 = false;
}
break;

case 21239: // Rapid Restore
if(RRestore == false)
{
noprayer();
NewDrain = 20;
DrainPray = true;
RRestore = true;
newdrain();
}
else if(RRestore == true)
{
RRestore = false;
}
break;

case 21240: // Rapid Heal
if(RHeal == false)
{
noprayer();
NewDrain = 20;
DrainPray = true;
RHeal = true;
}
else if(RHeal == true)
{
RHeal = false;
}
break;

case 21242: // Steel Skin
if(Def3 == false)
{
noprayer();
NewDrain = 20;
DrainPray = true;
Def3 = true;
newdrain();
}
else if(Def3 == true)
{
Def3 = false;
}
break;

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

case 21244: // Incredible Reflexes
if(Att3 == false)
{
noprayer();
NewDrain = 20;
DrainPray = true;
Att3 = true;
newdrain();
}
else if(Att3 == true)
{
Att3 = false;
}
break;

case 21245: // Protect From Magic
if(PMage == false)
{
noprayer();
ResetProtPrayers();
NewDrain = 15;
DrainPray = true;
PMage = true;
newdrain();
//headIcon = 4; //Commented out because HeadIcons crash most clients
appearanceUpdateRequired = true;
}
else if(PMage == true)
{
PMage = false;
//headIcon = 0;  //Commented out because HeadIcons crash most clients
appearanceUpdateRequired = true;
}
break;

case 21246: // Protect From Range
if(PRange == false)
{
noprayer();
ResetProtPrayers();
NewDrain = 15;
DrainPray = true;
PRange = true;
newdrain();
//headIcon = 2;  //Commented out because HeadIcons crash most clients
appearanceUpdateRequired = true;
}
else if(PRange == true)
{
PRange = false;
//headIcon = 0;  //Commented out because HeadIcons crash most clients
appearanceUpdateRequired = true;
}
break;

case 21247: // Protect From Melee
if(PMelee == false)
{
noprayer();
ResetProtPrayers();
NewDrain = 15;
DrainPray = true;
PMelee = true;
newdrain();
//headIcon = 5;  //Commented out because HeadIcons crash most clients
appearanceUpdateRequired = true;
}
else if(PMelee == true)
{
PMelee = false;
//headIcon = 0;  //Commented out because HeadIcons crash most clients
appearanceUpdateRequired = true;
}
break;

case 2171: // Retribution
if(Retribution == false)
{
Retribution = true;
//headIcon = 8;  //Commented out because HeadIcons crash most clients
appearanceUpdateRequired = true;
}
else if(Retribution == true)
{
Retribution = false;
//headIcon = 0;  //Commented out because HeadIcons crash most clients
appearanceUpdateRequired = true;
}
break;

case 2172: // Redemption
if(Redemption == false)
{
noprayer();
NewDrain = 10;
DrainPray = true;
Redemption = true;
newdrain();
//headIcon = 32;  //Commented out because HeadIcons crash most clients
appearanceUpdateRequired = true;
}
else if(Redemption == true)
{
Redemption = false;
//headIcon = 0;  //Commented out because HeadIcons crash most clients
appearanceUpdateRequired = true;
}
break;

case 2173: // Smite
if(Smite == false)
{
noprayer();
NewDrain = 10;
DrainPray = true;
Smite = true;
newdrain();
//headIcon = 16;  //Commented out because HeadIcons crash most clients
appearanceUpdateRequired = true;
}
else if(Smite == true)
{
Smite = false;
//headIcon = 0;  //Commented out because HeadIcons crash most clients
appearanceUpdateRequired = true;
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

	public boolean IsInCW(int coordX, int coordY) {
		if(coordY >= 3068 && coordY <= 3143 && coordX <= 2436 && coordX >= 2365){
				return true;

		} else {
		return false;
	}
}
	public boolean IsInTz(int coordX, int coordY) {
		if(coordY >= 5129 && coordY <= 5167 && coordX <= 2418 && coordX >= 2375){
				return true;

		} else {
		return false;
	}
}


	/* public boolean Attackrange() { // Old raning style from V.4.5
      client q = (client) server.playerHandler.players[AttackingOn];
                int EnemyX = PlayerHandler.players[AttackingOn].absX;
      int EnemyY = PlayerHandler.players[AttackingOn].absY;
      int EnemyHP = PlayerHandler.players[AttackingOn].playerLevel[playerHitpoints];
      int EnemyHPExp = PlayerHandler.players[AttackingOn].playerXP[playerHitpoints];
      boolean RingOfLife = false;
      if (PlayerHandler.players[AttackingOn].playerEquipment[playerRing] == 2570) {
         RingOfLife = true;
      }
           int hitDiff = 0;
      hitDiff = misc.random(playerMaxHit);

               if (GoodDistance(EnemyX, EnemyY, absX, absY, 20) == true) {
        teleportToX = absX;
        teleportToY = absY;
                  if (actionTimer == 0) {
                  if (IsInWilderness(EnemyX, EnemyY, 1) == true) {
         if (RingOfLife == true && EnemyHP <= (int)((double)((double)getLevelForXP(EnemyHPExp) / 10.0) + 0.5)) {
            PlayerHandler.players[AttackingOn].SafeMyLife = true;
         } else {
            if (PlayerHandler.players[AttackingOn].IsDead == true) {
                                        ResetAttack();
            } else {
               actionAmount++;
               setAnimation(playerSEA);
               PlayerHandler.players[AttackingOn].hitUpdateRequired = true;
               PlayerHandler.players[AttackingOn].updateRequired = true;
               PlayerHandler.players[AttackingOn].appearanceUpdateRequired = true;
               hitDiff = EnemyHP;

               PlayerHandler.players[AttackingOn].hitDiff = hitDiff;
               actionTimer = 7;
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
} */


	/* public boolean AttackAC(String s) { // Attacks NPCs with Set Spell from an distance (Unfinished Code)
		int EnemyX = server.npcHandler.npcs[attacknpc].absX;
		int EnemyY = server.npcHandler.npcs[attacknpc].absY;
		int EnemyHP = server.npcHandler.npcs[attacknpc].HP;
		int hitDiff = 0;
		hitDiff = misc.random(playerMaxHit);
		if (GoodDistance(EnemyX, EnemyY, absX, absY, 1) == false) {
			if (actionTimer == 0) {
				if (server.npcHandler.npcs[attacknpc].IsDead == true) {
					ResetAttackNPC();
				} else {
					if(spellSet == true && (s == "ACWindStrike")){
					TurnPlayerTo(EnemyX, EnemyY);
					int offsetY = (absX - EnemyX) * -1;
					int offsetX = (absY - EnemyY) * -1;
					GraphicsHandler.createSpell(90, absY, absX, offsetY, offsetX, 50, 95, 91, 43, 31, MageAttackIndex + 1, 92, EnemyY, EnemyX);
					AnimationReset = true;
    					updateRequired = true;
    					appearanceUpdateRequired = true;
					actionAmount++;
					teleportToX = absX;
 					teleportToY = absY;
 					hitDiff = 0 + misc.random(2);
 					actionTimer = 15;
 					setAnimation(711);
					AnimationReset = true;
				     	server.npcHandler.npcs[attacknpc].hitDiff = hitDiff;
					server.npcHandler.npcs[attacknpc].Killing[playerId] += hitDiff;
					server.npcHandler.npcs[attacknpc].updateRequired = true;
					server.npcHandler.npcs[attacknpc].hitUpdateRequired = true;
					double TotalExp = 0;

					TotalExp = (double)(20000 * hitDiff);
					TotalExp = (double)(TotalExp * CombatExpRate);
					addSkillXP((int)(TotalExp), playerMagic);


					if ((EnemyHP - hitDiff) < 0) {
						hitDiff = EnemyHP;
					}
					} else if(spellSet == true && (s == "ACWaterStrike")){
					TurnPlayerTo(EnemyX, EnemyY);
					int offsetY = (absX - EnemyX) * -1;
					int offsetX = (absY - EnemyY) * -1;
					GraphicsHandler.createSpell(90, absY, absX, offsetY, offsetX, 50, 95, 91, 43, 31, MageAttackIndex + 1, 92, EnemyY, EnemyX);
					AnimationReset = true;
    					updateRequired = true;
    					appearanceUpdateRequired = true;
					actionAmount++;
					teleportToX = absX;
 					teleportToY = absY;
 					hitDiff = 0 + misc.random(2);
 					actionTimer = 15;
 					setAnimation(711);
					AnimationReset = true;
				     	server.npcHandler.npcs[attacknpc].hitDiff = hitDiff;
					server.npcHandler.npcs[attacknpc].Killing[playerId] += hitDiff;
					server.npcHandler.npcs[attacknpc].updateRequired = true;
					server.npcHandler.npcs[attacknpc].hitUpdateRequired = true;
					double TotalExp = 0;

					TotalExp = (double)(20000 * hitDiff);
					TotalExp = (double)(TotalExp * CombatExpRate);
					addSkillXP((int)(TotalExp), playerMagic);


					if ((EnemyHP - hitDiff) < 0) {
						hitDiff = EnemyHP;
					}
					} if(spellSet == true && (s == "ACEarthStrike")){
					TurnPlayerTo(EnemyX, EnemyY);
					int offsetY = (absX - EnemyX) * -1;
					int offsetX = (absY - EnemyY) * -1;
					GraphicsHandler.createSpell(90, absY, absX, offsetY, offsetX, 50, 95, 91, 43, 31, MageAttackIndex + 1, 92, EnemyY, EnemyX);
					AnimationReset = true;
    					updateRequired = true;
    					appearanceUpdateRequired = true;
					actionAmount++;
					teleportToX = absX;
 					teleportToY = absY;
 					hitDiff = 0 + misc.random(2);
 					actionTimer = 15;
 					setAnimation(711);
					AnimationReset = true;
				     	server.npcHandler.npcs[attacknpc].hitDiff = hitDiff;
					server.npcHandler.npcs[attacknpc].Killing[playerId] += hitDiff;
					server.npcHandler.npcs[attacknpc].updateRequired = true;
					server.npcHandler.npcs[attacknpc].hitUpdateRequired = true;
					double TotalExp = 0;

					TotalExp = (double)(20000 * hitDiff);
					TotalExp = (double)(TotalExp * CombatExpRate);
					addSkillXP((int)(TotalExp), playerMagic);


					if ((EnemyHP - hitDiff) < 0) {
						hitDiff = EnemyHP;
					}
					} if(spellSet == true && (s == "ACFireStrike")){
					TurnPlayerTo(EnemyX, EnemyY);
					int offsetY = (absX - EnemyX) * -1;
					int offsetX = (absY - EnemyY) * -1;
					GraphicsHandler.createSpell(90, absY, absX, offsetY, offsetX, 50, 95, 91, 43, 31, MageAttackIndex + 1, 92, EnemyY, EnemyX);
					AnimationReset = true;
    					updateRequired = true;
    					appearanceUpdateRequired = true;
					actionAmount++;
					teleportToX = absX;
 					teleportToY = absY;
 					hitDiff = 0 + misc.random(2);
 					actionTimer = 15;
 					setAnimation(711);
					AnimationReset = true;
				     	server.npcHandler.npcs[attacknpc].hitDiff = hitDiff;
					server.npcHandler.npcs[attacknpc].Killing[playerId] += hitDiff;
					server.npcHandler.npcs[attacknpc].updateRequired = true;
					server.npcHandler.npcs[attacknpc].hitUpdateRequired = true;
					double TotalExp = 0;

					TotalExp = (double)(20000 * hitDiff);
					TotalExp = (double)(TotalExp * CombatExpRate);
					addSkillXP((int)(TotalExp), playerMagic);


					if ((EnemyHP - hitDiff) < 0) {
						hitDiff = EnemyHP;
					}
					} if(spellSet == true && (s == "ACWindBolt")){
					TurnPlayerTo(EnemyX, EnemyY);
					int offsetY = (absX - EnemyX) * -1;
					int offsetX = (absY - EnemyY) * -1;
					GraphicsHandler.createSpell(90, absY, absX, offsetY, offsetX, 50, 95, 91, 43, 31, MageAttackIndex + 1, 92, EnemyY, EnemyX);
					AnimationReset = true;
    					updateRequired = true;
    					appearanceUpdateRequired = true;
					actionAmount++;
					teleportToX = absX;
 					teleportToY = absY;
 					hitDiff = 0 + misc.random(2);
 					actionTimer = 15;
 					setAnimation(711);
					AnimationReset = true;
				     	server.npcHandler.npcs[attacknpc].hitDiff = hitDiff;
					server.npcHandler.npcs[attacknpc].Killing[playerId] += hitDiff;
					server.npcHandler.npcs[attacknpc].updateRequired = true;
					server.npcHandler.npcs[attacknpc].hitUpdateRequired = true;
					double TotalExp = 0;

					TotalExp = (double)(20000 * hitDiff);
					TotalExp = (double)(TotalExp * CombatExpRate);
					addSkillXP((int)(TotalExp), playerMagic);


					if ((EnemyHP - hitDiff) < 0) {
						hitDiff = EnemyHP;
					}
					} if(spellSet == true && (s == "ACWaterBolt")){
					TurnPlayerTo(EnemyX, EnemyY);
					int offsetY = (absX - EnemyX) * -1;
					int offsetX = (absY - EnemyY) * -1;
					GraphicsHandler.createSpell(90, absY, absX, offsetY, offsetX, 50, 95, 91, 43, 31, MageAttackIndex + 1, 92, EnemyY, EnemyX);
					AnimationReset = true;
    					updateRequired = true;
    					appearanceUpdateRequired = true;
					actionAmount++;
					teleportToX = absX;
 					teleportToY = absY;
 					hitDiff = 0 + misc.random(2);
 					actionTimer = 15;
 					setAnimation(711);
					AnimationReset = true;
				     	server.npcHandler.npcs[attacknpc].hitDiff = hitDiff;
					server.npcHandler.npcs[attacknpc].Killing[playerId] += hitDiff;
					server.npcHandler.npcs[attacknpc].updateRequired = true;
					server.npcHandler.npcs[attacknpc].hitUpdateRequired = true;
					double TotalExp = 0;

					TotalExp = (double)(20000 * hitDiff);
					TotalExp = (double)(TotalExp * CombatExpRate);
					addSkillXP((int)(TotalExp), playerMagic);


					if ((EnemyHP - hitDiff) < 0) {
						hitDiff = EnemyHP;
					}
					} if(spellSet == true && (s == "ACEarthBolt")){
					TurnPlayerTo(EnemyX, EnemyY);
					int offsetY = (absX - EnemyX) * -1;
					int offsetX = (absY - EnemyY) * -1;
					GraphicsHandler.createSpell(90, absY, absX, offsetY, offsetX, 50, 95, 91, 43, 31, MageAttackIndex + 1, 92, EnemyY, EnemyX);
					AnimationReset = true;
    					updateRequired = true;
    					appearanceUpdateRequired = true;
					actionAmount++;
					teleportToX = absX;
 					teleportToY = absY;
 					hitDiff = 0 + misc.random(2);
 					actionTimer = 15;
 					setAnimation(711);
					AnimationReset = true;
				     	server.npcHandler.npcs[attacknpc].hitDiff = hitDiff;
					server.npcHandler.npcs[attacknpc].Killing[playerId] += hitDiff;
					server.npcHandler.npcs[attacknpc].updateRequired = true;
					server.npcHandler.npcs[attacknpc].hitUpdateRequired = true;
					double TotalExp = 0;

					TotalExp = (double)(20000 * hitDiff);
					TotalExp = (double)(TotalExp * CombatExpRate);
					addSkillXP((int)(TotalExp), playerMagic);


					if ((EnemyHP - hitDiff) < 0) {
						hitDiff = EnemyHP;
					}
					} if(spellSet == true && (s == "ACFireBolt")){
					TurnPlayerTo(EnemyX, EnemyY);
					int offsetY = (absX - EnemyX) * -1;
					int offsetX = (absY - EnemyY) * -1;
					GraphicsHandler.createSpell(90, absY, absX, offsetY, offsetX, 50, 95, 91, 43, 31, MageAttackIndex + 1, 92, EnemyY, EnemyX);
					AnimationReset = true;
    					updateRequired = true;
    					appearanceUpdateRequired = true;
					actionAmount++;
					teleportToX = absX;
 					teleportToY = absY;
 					hitDiff = 0 + misc.random(2);
 					actionTimer = 15;
 					setAnimation(711);
					AnimationReset = true;
				     	server.npcHandler.npcs[attacknpc].hitDiff = hitDiff;
					server.npcHandler.npcs[attacknpc].Killing[playerId] += hitDiff;
					server.npcHandler.npcs[attacknpc].updateRequired = true;
					server.npcHandler.npcs[attacknpc].hitUpdateRequired = true;
					double TotalExp = 0;

					TotalExp = (double)(20000 * hitDiff);
					TotalExp = (double)(TotalExp * CombatExpRate);
					addSkillXP((int)(TotalExp), playerMagic);


					if ((EnemyHP - hitDiff) < 0) {
						hitDiff = EnemyHP;
					}
					} if(spellSet == true && (s == "ACWindBlast")){
					TurnPlayerTo(EnemyX, EnemyY);
					int offsetY = (absX - EnemyX) * -1;
					int offsetX = (absY - EnemyY) * -1;
					GraphicsHandler.createSpell(90, absY, absX, offsetY, offsetX, 50, 95, 91, 43, 31, MageAttackIndex + 1, 92, EnemyY, EnemyX);
					AnimationReset = true;
    					updateRequired = true;
    					appearanceUpdateRequired = true;
					actionAmount++;
					teleportToX = absX;
 					teleportToY = absY;
 					hitDiff = 0 + misc.random(2);
 					actionTimer = 15;
 					setAnimation(711);
					AnimationReset = true;
				     	server.npcHandler.npcs[attacknpc].hitDiff = hitDiff;
					server.npcHandler.npcs[attacknpc].Killing[playerId] += hitDiff;
					server.npcHandler.npcs[attacknpc].updateRequired = true;
					server.npcHandler.npcs[attacknpc].hitUpdateRequired = true;
					double TotalExp = 0;

					TotalExp = (double)(20000 * hitDiff);
					TotalExp = (double)(TotalExp * CombatExpRate);
					addSkillXP((int)(TotalExp), playerMagic);


					if ((EnemyHP - hitDiff) < 0) {
						hitDiff = EnemyHP;
					}
					} if(spellSet == true && (s == "ACWaterBlast")){
					TurnPlayerTo(EnemyX, EnemyY);
					int offsetY = (absX - EnemyX) * -1;
					int offsetX = (absY - EnemyY) * -1;
					GraphicsHandler.createSpell(90, absY, absX, offsetY, offsetX, 50, 95, 91, 43, 31, MageAttackIndex + 1, 92, EnemyY, EnemyX);
					AnimationReset = true;
    					updateRequired = true;
    					appearanceUpdateRequired = true;
					actionAmount++;
					teleportToX = absX;
 					teleportToY = absY;
 					hitDiff = 0 + misc.random(2);
 					actionTimer = 15;
 					setAnimation(711);
					AnimationReset = true;
				     	server.npcHandler.npcs[attacknpc].hitDiff = hitDiff;
					server.npcHandler.npcs[attacknpc].Killing[playerId] += hitDiff;
					server.npcHandler.npcs[attacknpc].updateRequired = true;
					server.npcHandler.npcs[attacknpc].hitUpdateRequired = true;
					double TotalExp = 0;

					TotalExp = (double)(20000 * hitDiff);
					TotalExp = (double)(TotalExp * CombatExpRate);
					addSkillXP((int)(TotalExp), playerMagic);


					if ((EnemyHP - hitDiff) < 0) {
						hitDiff = EnemyHP;
					}
					} if(spellSet == true && (s == "ACEarthBlast")){
					TurnPlayerTo(EnemyX, EnemyY);
					int offsetY = (absX - EnemyX) * -1;
					int offsetX = (absY - EnemyY) * -1;
					GraphicsHandler.createSpell(90, absY, absX, offsetY, offsetX, 50, 95, 91, 43, 31, MageAttackIndex + 1, 92, EnemyY, EnemyX);
					AnimationReset = true;
    					updateRequired = true;
    					appearanceUpdateRequired = true;
					actionAmount++;
					teleportToX = absX;
 					teleportToY = absY;
 					hitDiff = 0 + misc.random(2);
 					actionTimer = 15;
 					setAnimation(711);
					AnimationReset = true;
				     	server.npcHandler.npcs[attacknpc].hitDiff = hitDiff;
					server.npcHandler.npcs[attacknpc].Killing[playerId] += hitDiff;
					server.npcHandler.npcs[attacknpc].updateRequired = true;
					server.npcHandler.npcs[attacknpc].hitUpdateRequired = true;
					double TotalExp = 0;

					TotalExp = (double)(20000 * hitDiff);
					TotalExp = (double)(TotalExp * CombatExpRate);
					addSkillXP((int)(TotalExp), playerMagic);


					if ((EnemyHP - hitDiff) < 0) {
						hitDiff = EnemyHP;
					}
					} if(spellSet == true && (s == "ACFireBlast")){
					TurnPlayerTo(EnemyX, EnemyY);
					int offsetY = (absX - EnemyX) * -1;
					int offsetX = (absY - EnemyY) * -1;
					GraphicsHandler.createSpell(90, absY, absX, offsetY, offsetX, 50, 95, 91, 43, 31, MageAttackIndex + 1, 92, EnemyY, EnemyX);
					AnimationReset = true;
    					updateRequired = true;
    					appearanceUpdateRequired = true;
					actionAmount++;
					teleportToX = absX;
 					teleportToY = absY;
 					hitDiff = 0 + misc.random(2);
 					actionTimer = 15;
 					setAnimation(711);
					AnimationReset = true;
				     	server.npcHandler.npcs[attacknpc].hitDiff = hitDiff;
					server.npcHandler.npcs[attacknpc].Killing[playerId] += hitDiff;
					server.npcHandler.npcs[attacknpc].updateRequired = true;
					server.npcHandler.npcs[attacknpc].hitUpdateRequired = true;
					double TotalExp = 0;

					TotalExp = (double)(20000 * hitDiff);
					TotalExp = (double)(TotalExp * CombatExpRate);
					addSkillXP((int)(TotalExp), playerMagic);


					if ((EnemyHP - hitDiff) < 0) {
						hitDiff = EnemyHP;
					}
					} if(spellSet == true && (s == "ACWindWave")){
					TurnPlayerTo(EnemyX, EnemyY);
					int offsetY = (absX - EnemyX) * -1;
					int offsetX = (absY - EnemyY) * -1;
					GraphicsHandler.createSpell(90, absY, absX, offsetY, offsetX, 50, 95, 91, 43, 31, MageAttackIndex + 1, 92, EnemyY, EnemyX);
					AnimationReset = true;
    					updateRequired = true;
    					appearanceUpdateRequired = true;
					actionAmount++;
					teleportToX = absX;
 					teleportToY = absY;
 					hitDiff = 0 + misc.random(2);
 					actionTimer = 15;
 					setAnimation(711);
					AnimationReset = true;
				     	server.npcHandler.npcs[attacknpc].hitDiff = hitDiff;
					server.npcHandler.npcs[attacknpc].Killing[playerId] += hitDiff;
					server.npcHandler.npcs[attacknpc].updateRequired = true;
					server.npcHandler.npcs[attacknpc].hitUpdateRequired = true;
					double TotalExp = 0;

					TotalExp = (double)(20000 * hitDiff);
					TotalExp = (double)(TotalExp * CombatExpRate);
					addSkillXP((int)(TotalExp), playerMagic);


					if ((EnemyHP - hitDiff) < 0) {
						hitDiff = EnemyHP;
					}
					} if(spellSet == true && (s == "ACWaterWave")){
					TurnPlayerTo(EnemyX, EnemyY);
					int offsetY = (absX - EnemyX) * -1;
					int offsetX = (absY - EnemyY) * -1;
					GraphicsHandler.createSpell(90, absY, absX, offsetY, offsetX, 50, 95, 91, 43, 31, MageAttackIndex + 1, 92, EnemyY, EnemyX);
					AnimationReset = true;
    					updateRequired = true;
    					appearanceUpdateRequired = true;
					actionAmount++;
					teleportToX = absX;
 					teleportToY = absY;
 					hitDiff = 0 + misc.random(2);
 					actionTimer = 15;
 					setAnimation(711);
					AnimationReset = true;
				     	server.npcHandler.npcs[attacknpc].hitDiff = hitDiff;
					server.npcHandler.npcs[attacknpc].Killing[playerId] += hitDiff;
					server.npcHandler.npcs[attacknpc].updateRequired = true;
					server.npcHandler.npcs[attacknpc].hitUpdateRequired = true;
					double TotalExp = 0;

					TotalExp = (double)(20000 * hitDiff);
					TotalExp = (double)(TotalExp * CombatExpRate);
					addSkillXP((int)(TotalExp), playerMagic);


					if ((EnemyHP - hitDiff) < 0) {
						hitDiff = EnemyHP;
					}
					} if(spellSet == true && (s == "ACEarthWave")){
					TurnPlayerTo(EnemyX, EnemyY);
					int offsetY = (absX - EnemyX) * -1;
					int offsetX = (absY - EnemyY) * -1;
					GraphicsHandler.createSpell(90, absY, absX, offsetY, offsetX, 50, 95, 91, 43, 31, MageAttackIndex + 1, 92, EnemyY, EnemyX);
					AnimationReset = true;
    					updateRequired = true;
    					appearanceUpdateRequired = true;
					actionAmount++;
					teleportToX = absX;
 					teleportToY = absY;
 					hitDiff = 0 + misc.random(2);
 					actionTimer = 15;
 					setAnimation(711);
					AnimationReset = true;
				     	server.npcHandler.npcs[attacknpc].hitDiff = hitDiff;
					server.npcHandler.npcs[attacknpc].Killing[playerId] += hitDiff;
					server.npcHandler.npcs[attacknpc].updateRequired = true;
					server.npcHandler.npcs[attacknpc].hitUpdateRequired = true;
					double TotalExp = 0;

					TotalExp = (double)(20000 * hitDiff);
					TotalExp = (double)(TotalExp * CombatExpRate);
					addSkillXP((int)(TotalExp), playerMagic);


					if ((EnemyHP - hitDiff) < 0) {
						hitDiff = EnemyHP;
					}
					} if(spellSet == true && (s == "ACFireWave")){
					TurnPlayerTo(EnemyX, EnemyY);
					int offsetY = (absX - EnemyX) * -1;
					int offsetX = (absY - EnemyY) * -1;
					GraphicsHandler.createSpell(90, absY, absX, offsetY, offsetX, 50, 95, 91, 43, 31, MageAttackIndex + 1, 92, EnemyY, EnemyX);
					AnimationReset = true;
    					updateRequired = true;
    					appearanceUpdateRequired = true;
					actionAmount++;
					teleportToX = absX;
 					teleportToY = absY;
 					hitDiff = 0 + misc.random(2);
 					actionTimer = 15;
 					setAnimation(711);
					AnimationReset = true;
				     	server.npcHandler.npcs[attacknpc].hitDiff = hitDiff;
					server.npcHandler.npcs[attacknpc].Killing[playerId] += hitDiff;
					server.npcHandler.npcs[attacknpc].updateRequired = true;
					server.npcHandler.npcs[attacknpc].hitUpdateRequired = true;
					double TotalExp = 0;

					TotalExp = (double)(20000 * hitDiff);
					TotalExp = (double)(TotalExp * CombatExpRate);
					addSkillXP((int)(TotalExp), playerMagic);


					if ((EnemyHP - hitDiff) < 0) {
						hitDiff = EnemyHP;
					}
					}
				}
			}
			return false;
		}
		return false;
	   } */


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

//viewTo(server.playerHandler.players[AttackingOn].absX, server.playerHandler.players[AttackingOn].absY);

if(playerEquipment[playerWeapon] == (1333)) // rune scimi here
{
PkingDelay = 13;
wepdelay = 13;
}

if(playerEquipment[playerWeapon] == (4151)) // whip
{
PkingDelay = 13;
wepdelay = 13;
resetanim = 13;
}
			
if(playerEquipment[playerWeapon] == (4718)) //dharoks axe
{
PkingDelay = 13;
wepdelay = 13;
}

if(playerEquipment[playerWeapon] == (4151)) //full karils and whip here
if(playerEquipment[playerChest] == (4736))
if(playerEquipment[playerLegs] == (4738))
{
PkingDelay = 13;
wepdelay = 13;
}

if(playerEquipment[playerWeapon] == (4587)) // dragon scimmy here
{
PkingDelay = 13;
wepdelay = 13;
}

if(playerEquipment[playerWeapon] == (1377)) //dragon battleaxe
{
PkingDelay = 13;
wepdelay = 13;
}

if(playerEquipment[playerWeapon] == (1434)) //dragon mace
{
PkingDelay = 13;
wepdelay = 13;
}

if(playerEquipment[playerWeapon] == (7158)) //dragon 2h
{
PkingDelay = 13;
wepdelay = 13;
}


boolean UseBow = false;

if(playerEquipment[playerWeapon] == 4212)
{
PkingDelay = 13;
 wepdelay = 13;
 UseBow = true;
}

if(playerEquipment[playerWeapon] == 4827)
{
PkingDelay = 13;
 wepdelay = 13;
 UseBow = true;
}

if(playerEquipment[playerWeapon] == 2883)
{
PkingDelay = 13;
 wepdelay = 13;
 UseBow = true;
}

if(playerEquipment[playerWeapon] == 6082)
{
PkingDelay = 13;
 wepdelay = 13;
 UseBow = true;
}

if(playerEquipment[playerWeapon] == 4214)
{
PkingDelay = 13;
 wepdelay = 13;
 UseBow = true;
}

if(playerEquipment[playerWeapon] == 767)
{
PkingDelay = 13;
 wepdelay = 13;
 UseBow = true;
}

if(playerEquipment[playerWeapon] == 837)
{
PkingDelay = 13;
 wepdelay = 13;
 UseBow = true;
}

if(playerEquipment[playerWeapon] == 861)
{
PkingDelay = 13;
 wepdelay = 13;
 UseBow = true;
}

if(playerEquipment[playerWeapon] == 4734)
{
PkingDelay = 13;
 wepdelay = 13;
 UseBow = true;
}

if(playerEquipment[playerWeapon] == 4214)
{
PkingDelay = 13;
 wepdelay = 13;
 UseBow = true;
}

if(playerEquipment[playerWeapon] == 861)
{
PkingDelay = 13;
 wepdelay = 13;
 UseBow = true;
}

if(playerEquipment[playerWeapon] == 859)
{
PkingDelay = 13;
 wepdelay = 13;
 UseBow = true;
}

/*
if(playerEquipment[playerWeapon] == 4214)
{
 PkingDelay = 13;
 wepdelay = 13;
 UseBow = true; 
}
	
if(playerEquipment[playerWeapon] == 861)
{
 PkingDelay = 13;
 wepdelay = 13;
 UseBow = true; 
}
								                                                        
if(playerEquipment[playerWeapon] == 859)
{
 PkingDelay = 13;
 wepdelay = 13;
 UseBow = true;                                                                               
} */


if(playerEquipment[playerWeapon] == 839 || playerEquipment[playerWeapon] == 841  || playerEquipment[playerWeapon] == 4827 || playerEquipment[playerWeapon] == 843 || playerEquipment[playerWeapon] == 845 || playerEquipment[playerWeapon] == 847 || playerEquipment[playerWeapon] == 849 || playerEquipment[playerWeapon] == 851 || playerEquipment[playerWeapon] == 853 || playerEquipment[playerWeapon] == 855 || playerEquipment[playerWeapon] == 857)
{
  PkingDelay = 13;
  wepdelay = 13;
  UseBow = true;
}

if(UseBow){
 inCombat(); 
 teleportToX = absX;   
 teleportToY = absY; 
 CheckArrows();
 CalculateRange();
 hitDiff = misc.random(playerMaxHit);
}



else
{
PkingDelay = 13;
wepdelay = 13;
}                                                                               

                                CheckWildrange(AttackingOn2.combat);
                                if(inSafezone())
                                {
                                sendMessage("This player is in a safe zone !");
                                ResetAttack();
                                }
                                else if(!inSafezone()){
		if (GoodDistance(EnemyX, EnemyY, absX, absY, 1) == true || playerEquipment[playerWeapon] == 859 || playerEquipment[playerWeapon] == 861 || playerEquipment[playerWeapon] == 4214 || playerEquipment[playerWeapon] == 4734 || playerEquipment[playerWeapon] == 861 || playerEquipment[playerWeapon] == 837 || playerEquipment[playerWeapon] == 767|| playerEquipment[playerWeapon] == 4214 || playerEquipment[playerWeapon] == 6082 || playerEquipment[playerWeapon] == 2883 || playerEquipment[playerWeapon] == 4212 || playerEquipment[playerWeapon] == 839 || playerEquipment[playerWeapon] == 841 || playerEquipment[playerWeapon] == 843 || playerEquipment[playerWeapon] == 845 || playerEquipment[playerWeapon] == 847 || playerEquipment[playerWeapon] == 849 || playerEquipment[playerWeapon] == 851 || playerEquipment[playerWeapon] == 853 || playerEquipment[playerWeapon] == 855 || playerEquipment[playerWeapon] == 857) {
		  if (LoopAttDelay <= 1) {
		    if (inwildy2 == true) {
			if (RingOfLife == true && EnemyHP <= (int)((double)((double)getLevelForXP(EnemyHPExp) / 10.0) + 0.5)) {
				PlayerHandler.players[AttackingOn].SafeMyLife = true;
			} else {
				if (PlayerHandler.players[AttackingOn].IsDead == true) {
					ResetAttack();
				} else if(!UseBow){
					//actionAmount++;
					//setAnimation(playerSEA);
					PlayerHandler.players[AttackingOn].hitUpdateRequired = true;
					PlayerHandler.players[AttackingOn].updateRequired = true;
					PlayerHandler.players[AttackingOn].appearanceUpdateRequired = true;
                                        if(playerEquipment[playerWeapon] == 5698) {
                                        hitDiff = misc.random(playerMaxHit);
					PlayerHandler.players[AttackingOn].hitUpdateRequired = true;
					PlayerHandler.players[AttackingOn].updateRequired = true;
					PlayerHandler.players[AttackingOn].appearanceUpdateRequired = true;
                                        }
                                        AttackingOn2.KillerId = playerId;
                                        AttackingOn2.inCombat();
                                        setAnimation(GetWepAnim());
                                        if(playerEquipment[playerWeapon] == 4726)
                                        {
                                        stillgfx(398, absY, absX);
                                        }
                                        AttackingOn2.setAnimation(GetBlockAnim(AttackingOn2.playerEquipment[playerWeapon]));
                                        LoopAttDelay = PkingDelay+10;
					if ((EnemyHP - hitDiff) < 0) {
						hitDiff = EnemyHP;
					}
					PlayerHandler.players[AttackingOn].hitDiff = hitDiff;
					//PkingDelay = wepdelay;
				}
                                    else if(UseBow){
                                        if(!HasArrows){ 
                                        sendMessage("There's no arrows left in your quiver");
                                        ResetAttack();
                                        }
                                        else if(HasArrows){
					//actionAmount++;
					//setAnimation(playerSEA);
                                        DeleteArrow();
                                        if(playerEquipment[playerWeapon] != 4214 && playerEquipmentN[playerArrows] != 0)
                                        ItemHandler.addItem(playerEquipment[playerArrows], EnemyX, EnemyY, 1, playerId, false);
                                        setAnimation(426);
					PlayerHandler.players[AttackingOn].hitUpdateRequired = true;
					PlayerHandler.players[AttackingOn].updateRequired = true;
					PlayerHandler.players[AttackingOn].appearanceUpdateRequired = true;
                                        AttackingOn2.KillerId = playerId+10;
                                        AttackingOn2.inCombat();
                                         inCombat(); 
                                        teleportToX = absX;   
                                        teleportToY = absY; 
                                        AttackingOn2.setAnimation(GetBlockAnim(AttackingOn2.playerEquipment[playerWeapon]));
                                        LoopAttDelay = PkingDelay;
					if ((EnemyHP - hitDiff) < 0) {
						hitDiff = EnemyHP;
					}
					PlayerHandler.players[AttackingOn].hitDiff = hitDiff;
					//PkingDelay = wepdelay;
                                        }
				}
			}
			return true;
		    } else {
		      sendMessage("This player is not in the Wilderness.");
		      ResetAttack();
		    }
		  }
		}}
		return false;
	}
	public boolean ResetAttack() {
		IsAttacking = false;
		AttackingOn = 0;
		resetAnimation();
		IsUsingSkill = false;
                pEmote = playerSE;
		return true;
	}
         public int lnew = 0; 
         public int otherpkps = 0;
         public int otherkillc = 0;
	public boolean ApplyDead() {
		if (IsDeadTimer == false) {
			if (Retribution == true) {
				animation(437,absY,absX);
				attackPlayersPrayer(15, 2);
			}
			actionAmount++;
			actionTimer = 0;
			ResetAttack();
			ResetAttackNPC();
			pEmote = 0x900;
			IsDeadTimer = true;
                        ApplyDead();
		}
 if(KillerId != playerId && PlayerHandler.players[KillerId] != null){
      if(PlayerHandler.players[KillerId].combat > combat){
       lnew = 1;
       }
      else if(PlayerHandler.players[KillerId].combat < combat){
       lnew = 3;
       }
      else if(PlayerHandler.players[KillerId].combat == combat){
       lnew = 2;
       }
client killerz = (client) server.playerHandler.players[KillerId];
if(killerz != null && PlayerHandler.players[KillerId] != null) {
  PlayerHandler.players[KillerId].pkpoints += lnew;
  PlayerHandler.players[KillerId].killcount += 1;
  otherpkps = PlayerHandler.players[KillerId].pkpoints;
  otherkillc = PlayerHandler.players[KillerId].killcount;
  killerz.sendMessage("You recieve "+lnew+" player-kill, you now have "+otherpkps+" player-kill points.");
  killerz.sendMessage("You now have a total of "+otherkillc+" player kills.");
  deathcount =+ 1;
}
}
		if (actionTimer == 0 && IsDeadTimer == true) {
				DeadStats();
                        keepItem1();
                        keepItem2();
                        keepItem3();
                        deleteItem(keepItem, getItemSlot(keepItem), keepItemAmount);
                        deleteItem(keepItem2, getItemSlot(keepItem2), keepItemAmount2);
                        deleteItem(keepItem3, getItemSlot(keepItem3), keepItemAmount3);
			youdied();
                		teleportToX = 2802;
			teleportToY = 3178;
			IsDeadTeleporting = true;
			frame1(); // Xerozcheez: Resets animation
			updateRequired = true;
			appearanceUpdateRequired = true;
                        NewHP = getLevelForXP(playerXP[3]);
                        setSkillLevel(3, getLevelForXP(playerXP[3]), playerXP[playerHitpoints]);
                        playerLevel[3] = getLevelForXP(playerXP[3]);
                        refreshSkills();
                        PoisonDelay = 9999999;
                        KillerId = playerId;
                        if(keepItem != 0)
                        addItem(keepItem, keepItemAmount);
                        if(keepItem2 != 0)
                        addItem(keepItem2, keepItemAmount2);
                        if(keepItem3 != 0)
                        addItem(keepItem3, keepItemAmount3);

                        resetKeepItem();
                        
		}
		return true;
	}
public int keepItem = 0;
public int keepItem2 = 0;
public int keepItem3 = 0;
public int keepItemAmount = 0;
public int keepItemAmount2 = 0;
public int keepItemAmount3 = 0;

public void resetKeepItem() {
keepItem = 0;
keepItem2 = 0;
keepItem3 = 0;
keepItemAmount = 0;
keepItemAmount2 = 0;
keepItemAmount3 = 0;
}

public void keepItem1() {
  for(int i = 0; i < playerItems.length; i++) {
   int highest = 0;
   int value = (int)Math.floor(GetItemShopValue(playerItems[i]-1, 0, i));
   if(value > highest && playerItems[i]-1 != -1) {
    highest = value;
    keepItem = playerItems[i]-1;
    keepItemAmount = playerItemsN[i];
  }
 }
}

public void keepItem2() {
  for(int i = 0; i < playerItems.length; i++) {
   int highest = 0;
   int value = (int)Math.floor(GetItemShopValue(playerItems[i]-1, 0, i));
   if(value > highest && playerItems[i]-1 != keepItem && playerItems[i]-1 != -1) {
    highest = value;
    keepItem2 = playerItems[i]-1;
    keepItemAmount2 = playerItemsN[i];
  }
 }
}

public void keepItem3() {
  for(int i = 0; i < playerItems.length; i++) {
   int highest = 0;
   int value = (int)Math.floor(GetItemShopValue(playerItems[i]-1, 0, i));
   if(value > highest && playerItems[i]-1 != keepItem && playerItems[i]-1 != keepItem2 && playerItems[i]-1 != -1) {
    highest = value;
    keepItem3 = playerItems[i]-1;
    keepItemAmount3 = playerItemsN[i];
  }
 }
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
		if (ItemID != 453 && smelting[0] >= 1 && playerEquipment[playerWeapon] >= 0) {
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

case 2520:
startAnimation(918);
txt4 = "Come on Swifty, we can win the race!";
string4UpdateRequired = true;
break;

case 2522:
startAnimation(919);
txt4 = "Come on Alex, we can win the race!";
string4UpdateRequired = true;
break;

case 2524:
startAnimation(920);
txt4 = "Come on Vegeta, we can win the race!";
string4UpdateRequired = true;
break;

case 2526:
startAnimation(921);
txt4 = "Come on MrWicked, we can win the race!";
string4UpdateRequired = true;
break;

case 952://spade
startAnimation(381);
            updateRequired = true;
            appearanceUpdateRequired = true;

  if (isInArea(3553, 3301, 3561, 3294)) { // verac
          teleportToX = 3578;
          teleportToY = 9706;
          heightLevel = -1;
  } else if (isInArea(3550, 3287, 3557, 3278)) { // torag
          teleportToX = 3568;
          teleportToY = 9683;
          heightLevel = -1;
  } else if (isInArea(3561, 3292, 3568, 3285)) { // ahrim
          teleportToX = 3557;
          teleportToY = 9703;
          heightLevel = -1;
  } else if (isInArea(3570, 3302, 3579, 3293)) { // dharok
          teleportToX = 3556;
          teleportToY = 9718;
          heightLevel = -1;
  } else if (isInArea(3571, 3285, 3582, 3278)) { // guthan
          teleportToX = 3539;
          teleportToY = 9701;
          heightLevel = -1;
  } else if (isInArea(3562, 3279, 3569, 3273)) { // karil
          teleportToX = 3552;
          teleportToY = 9684;
          heightLevel = -1;

        } else {
          sendMessage("Nothing interesting happens.");
        }
 
 break;

case 3014:
playerEnergy += 10;
WriteEnergy();
deleteItem(3014, GetItemSlot(3014), 1);
addItem(229, 1);
break;
case 3012:
playerEnergy += 10;
WriteEnergy();
deleteItem(3012, GetItemSlot(3012), 1);
addItem(3014, 1);
break;
case 3010:
playerEnergy += 10;
WriteEnergy();
deleteItem(3010, GetItemSlot(3010), 1);
addItem(3012, 1);
break;
case 3008:
playerEnergy += 10;
WriteEnergy();
deleteItem(3008, GetItemSlot(3008), 1);
addItem(3010, 1);
break;
case 3022:
playerEnergy += 20;
WriteEnergy();
deleteItem(3022, GetItemSlot(3022), 1);
addItem(229, 1);
break;
case 3020:
playerEnergy += 20;
WriteEnergy();
deleteItem(3020, GetItemSlot(3020), 1);
addItem(3022, 1);
break;
case 3018:
playerEnergy += 20;
WriteEnergy();
deleteItem(3018, GetItemSlot(3018), 1);
addItem(3020, 1);
break;
case 3016:
playerEnergy += 20;
WriteEnergy();
deleteItem(3016, GetItemSlot(3016), 1);
addItem(3018, 1);
break;
case 161: // super str (1)
strPot = true;
strPotTimer = 90;
abc = getLevelForXP(playerXP[2]);
cba = abc / 10;
abc2 = cba * 2;
if (abc2 <= 1) {
abc2 = 2;
}
playerLevel[2] = getLevelForXP(playerXP[2]);
playerLevel[2] += abc2;
sendFrame126("@whi@"+playerLevel[2]+"", 4006);
deleteItem(161, GetItemSlot(161), 1);
addItem(229, 1);
updateRequired = true;
appearanceUpdateRequired = true;
GoOn = false;
break;
case 159:
strPot = true;
strPotTimer = 90;
abc = getLevelForXP(playerXP[2]);
cba = abc / 10;
abc2 = cba * 2;
if (abc2 <= 1) {
abc2 = 2;
}
playerLevel[2] = getLevelForXP(playerXP[2]);
playerLevel[2] += abc2;
sendFrame126("@whi@"+playerLevel[2]+"", 4006);
deleteItem(159, GetItemSlot(159), 1);
addItem(161, 1);
updateRequired = true;
appearanceUpdateRequired = true;
GoOn = false;
break;
case 157:
strPot = true;
strPotTimer = 90;
abc = getLevelForXP(playerXP[2]);
cba = abc / 10;
abc2 = cba * 2;
if (abc2 <= 1) {
abc2 = 2;
}
playerLevel[2] = getLevelForXP(playerXP[2]);
playerLevel[2] += abc2;
sendFrame126("@whi@"+playerLevel[2]+"", 4006);
deleteItem(157, GetItemSlot(157), 1);
addItem(159, 1);
updateRequired = true;
appearanceUpdateRequired = true;
GoOn = false;
break;
case 2440:
strPot = true;
strPotTimer = 90;
abc = getLevelForXP(playerXP[2]);
cba = abc / 10;
abc2 = cba * 2;
if (abc2 <= 1) {
abc2 = 2;
}
playerLevel[2] = getLevelForXP(playerXP[2]);
playerLevel[2] += abc2;
sendFrame126("@whi@"+playerLevel[2]+"", 4006);
deleteItem(2440, GetItemSlot(2440), 1);
addItem(157, 1);
updateRequired = true;
appearanceUpdateRequired = true;
GoOn = false;
break;
case 113:
strPot = true;
strPotTimer = 90;
abc = getLevelForXP(playerXP[2]);
cba = abc / 10;
aaa = cba / 2;
abc2 = aaa + cba;
if (abc2 <= 1) {
abc2 = 2;
}
playerLevel[2] = getLevelForXP(playerXP[2]);
playerLevel[2] += abc2;
sendFrame126("@whi@"+playerLevel[2]+"", 4006);
deleteItem(113, GetItemSlot(113), 1);
addItem(115, 1);
updateRequired = true;
appearanceUpdateRequired = true;
GoOn = false;
break;
case 115:
strPot = true;
strPotTimer = 90;
abc = getLevelForXP(playerXP[2]);
cba = abc / 10;
aaa = cba / 2;
abc2 = aaa + cba;
if (abc2 <= 1) {
abc2 = 2;
}
playerLevel[2] = getLevelForXP(playerXP[2]);
playerLevel[2] += abc2;
sendFrame126("@whi@"+playerLevel[2]+"", 4006);
deleteItem(115, GetItemSlot(115), 1);
addItem(117, 1);
updateRequired = true;
appearanceUpdateRequired = true;
GoOn = false;
break;
case 117:
strPot = true;
strPotTimer = 90;
abc = getLevelForXP(playerXP[2]);
cba = abc / 10;
aaa = cba / 2;
abc2 = aaa + cba;
if (abc2 <= 1) {
abc2 = 2;
}
playerLevel[2] = getLevelForXP(playerXP[2]);
playerLevel[2] += abc2;
sendFrame126("@whi@"+playerLevel[2]+"", 4006);
deleteItem(117, GetItemSlot(117), 1);
addItem(119, 1);
updateRequired = true;
appearanceUpdateRequired = true;
GoOn = false;
break;
case 119:
strPot = true;
strPotTimer = 90;
abc = getLevelForXP(playerXP[2]);
cba = abc / 10;
aaa = cba / 2;
abc2 = aaa + cba;
if (abc2 <= 1) {
abc2 = 2;
}
playerLevel[2] = getLevelForXP(playerXP[2]);
playerLevel[2] += abc2;
sendFrame126("@whi@"+playerLevel[2]+"", 4006);
deleteItem(119, GetItemSlot(119), 1);
addItem(229, 1);
updateRequired = true;
appearanceUpdateRequired = true;
GoOn = false;
break;
case 2446: //Antipoison(4)
Poisoned = false;
sendMessage("You drink a dose of the antipoison.");
deleteItem(2446, GetItemSlot(2446), 1);
addItem(175, 1);
PoisonDelay = 9999999;
break;
case 175: //Antipoison(3)
Poisoned = false;
sendMessage("You drink a dose of the antipoison.");
deleteItem(175, GetItemSlot(175), 1);
addItem(177, 1);
PoisonDelay = 9999999;
break;
case 177: //Antipoison(2)
Poisoned = false;
sendMessage("You drink a dose of the antipoison.");
deleteItem(177, GetItemSlot(177), 1);
addItem(179, 1);
PoisonDelay = 9999999;
break;
case 179: //Antipoison(1)
Poisoned = false;
PoisonDelay2 = 100;
sendMessage("You drink the last dose of the super anti-poison.");
deleteItem(179, GetItemSlot(179), 1);
addItem(229, 1);
PoisonDelay = 9999999;
break;
case 550: //newcomer map  by me ?
showInterface(5392);
sendMessage("You Un-roll The Map And It Shows The South-east Of Satan's Isle");
sendMessage("Your Co-ordinates are: X: "+absX+" Y: "+absY); 
sendQuest("@red@Satan's Isle", 5394);
break;
case 1856: //Guide Book By I Loot I
        sendQuest("@red@Satan's Isle @whi@Guidebook", 903);
		    sendQuest("@dbl@Made By I Loot I", 843);
		    sendQuest("@dre@Satan's Isle", 844);
		    sendQuest("@dbl@~Notes~", 845);
		    sendQuest("@bla@Change Character at emotes", 846);
		    sendQuest("@bla@Summoning disabled",847);
		    sendQuest("@bla@And Much More", 848);
		    sendQuest("@dre@Current Mods Are", 849);
		    sendQuest("@bla@- None -", 850);
		    sendQuest("@bla@- None -", 851);
		    sendQuest("@dre@Admins are:", 852);
		    sendQuest("@bla@I Loot I , I Loot I", 853);
		    sendQuest("@bla@- None -", 854);
		    sendQuest("@dre@~Extra Information", 855);
		    sendQuest("@bla@Try to train at monsters !", 856);
		    sendQuest("@bla@They Give Better Exp",857);
		    sendQuest("@bla@Train your mage at monsters",858);
		    sendQuest("@bla@Try the duel arena !",859);	
		    sendQuest("@dre@~rules~",860);
		    sendQuest("@bla@Don't ask for mod",861);
		    sendQuest("@bla@Go to mod appealing",862);
		    sendQuest("@bla@And appeal for mod !",863);
		    sendQuest("@bla@Thnk-You , I Loot I",864);
    showInterface(837);
    flushOutStream();
break;
case 2448: //superAntipoison(4)
Poisoned = false;
PoisonDelay2 = 100;
sendMessage("You drink a dose of the super anti-poison.");
deleteItem(2448, GetItemSlot(2448), 1);
addItem(181, 1);
break;
case 181: //superAntipoison(3)
Poisoned = false;
PoisonDelay2 = 100;
sendMessage("You drink a dose of the super anti-poison.");
deleteItem(181, GetItemSlot(181), 1);
addItem(183, 1);
break;
case 183: //superAntipoison(2)
Poisoned = false;
PoisonDelay2 = 100;
sendMessage("You drink a dose of the super anti-poison.");
deleteItem(183, GetItemSlot(183), 1);
addItem(184, 1);
break;
case 185: //superAntipoison(1)
Poisoned = false;
PoisonDelay2 = 100;
sendMessage("You drink the last dose of the super anti-poison.");
deleteItem(185, GetItemSlot(185), 1);
addItem(229, 1);
break;
case 5943: //extra-strongAntidote(4)
Poisoned = false;
PoisonDelay2 = 200;
sendMessage("You drink a dose of the extra strong antidote");
deleteItem(5943, GetItemSlot(5943), 1);
addItem(5945, 1);
break;
case 5945: //extra-strongAntidote(3)
Poisoned = false;
PoisonDelay2 = 200;
sendMessage("You drink a dose of the extra strong antidote");
deleteItem(5945, GetItemSlot(5945), 1);
addItem(5945, 1);
break;
case 5947: //extra-strongAntidote(2)
Poisoned = false;
PoisonDelay2 = 200;
sendMessage("You drink a dose of the extra strong antidote");
deleteItem(5947, GetItemSlot(5947), 1);
addItem(5949, 1);
break;
case 5949: //extra-strongAntidote(1)
Poisoned = false;
PoisonDelay2 = 200;
sendMessage("You drink the last dose of the extra strong antidote");
deleteItem(5949, GetItemSlot(5949), 1);
addItem(229, 1);
break;
case 5952: //super-strongAntidote(4)
Poisoned = false;
PoisonDelay2 = 350;
sendMessage("You drink a dose of the super strong antidote");
deleteItem(5952, GetItemSlot(5952), 1);
addItem(5954, 1);
break;
case 5954: //super-strongAntidote(3)
Poisoned = false;
PoisonDelay2 = 350;
sendMessage("You drink a dose of the super strong antidote");
deleteItem(5954, GetItemSlot(5954), 1);
addItem(5956, 1);
break;
case 5956: //super-strongAntidote(2)
Poisoned = false;
PoisonDelay2 = 350;
sendMessage("You drink a dose of the super strong antidote");
deleteItem(5956, GetItemSlot(5956), 1);
addItem(5958, 1);
break;
case 5958: //super-strongAntidote(1)
Poisoned = false;
PoisonDelay2 = 350;
sendMessage("You drink the last dose of the super strong antidote");
deleteItem(5958, GetItemSlot(5958), 1);
addItem(229, 1);
break;
case 131: // restore pot
restorePot();
sendMessage("You drink the last dose of the restore potion");
deleteItem(131, GetItemSlot(131), 1);
addItem(229, 1);
break;
case 129: // restore pot
restorePot();
sendMessage("You drink a dose of the restore potion");
deleteItem(129, GetItemSlot(129), 1);
addItem(131, 1);
break;
case 127: // restore pot
restorePot();
sendMessage("You drink a dose of the restore potion");
deleteItem(127, GetItemSlot(127), 1);
addItem(129, 1);
break;
           case 2430: // restore pot
                restorePot();
                sendMessage("You drink a dose of the restore potion");
                deleteItem(2430, GetItemSlot(2430), 1);
                addItem(127, 1);
                break;
           case 3030: // super restore pot
                restorePot();
                sendMessage("You drink the last dose of the super restore potion");
                deleteItem(3030, GetItemSlot(3030), 1);
                addItem(229, 1);
                superRestore = true;
                break;
                case 3028: // super restore pot
                restorePot();
                sendMessage("You drink a dose of the super restore potion");
                deleteItem(3028, GetItemSlot(3028), 1);
                addItem(3030, 1);
                superRestore = true;
                break;
            case 3026: // super restore pot
                restorePot();
                sendMessage("You drink a dose of the super restore potion");
                deleteItem(3026, GetItemSlot(3026), 1);
                addItem(3028, 1);
                superRestore = true;
                break;
            case 3024: // super restore pot
                restorePot();
                sendMessage("You drink a dose of the super restore potion");
                deleteItem(3024, GetItemSlot(3024), 1);
                addItem(3026, 1);
                superRestore = true;
                break;
            case 3038: // agil pot (1)
                agilPot = true;
                agilPotTimer = 90;
                playerLevel[16] = getLevelForXP(playerXP[16]);
                playerLevel[16] += 4;
                sendFrame126("@whi@"+playerLevel[16]+"", 4018);
                deleteItem(3038, GetItemSlot(3038), 1);
                addItem(229, 1);
                updateRequired = true;
                appearanceUpdateRequired = true;
                GoOn = false;
                break;
            case 3036: // agil pot (2)
                agilPot = true;
                agilPotTimer = 90;
                playerLevel[16] = getLevelForXP(playerXP[16]);
                playerLevel[16] += 4;
                sendFrame126("@whi@"+playerLevel[16]+"", 4018);
                deleteItem(3036, GetItemSlot(3036), 1);
                addItem(3038, 1);
                updateRequired = true;
                appearanceUpdateRequired = true;
                GoOn = false;
                break;
            case 3034: // agil pot (3)
                agilPot = true;
                agilPotTimer = 90;
                playerLevel[16] = getLevelForXP(playerXP[16]);
                playerLevel[16] += 4;
                sendFrame126("@whi@"+playerLevel[16]+"", 4018);
                deleteItem(3034, GetItemSlot(3034), 1);
                addItem(3036, 1);
                updateRequired = true;
                appearanceUpdateRequired = true;
                GoOn = false;
                break;
            case 3032: // agil pot (4)
                agilPot = true;
                agilPotTimer = 90;
                playerLevel[16] = getLevelForXP(playerXP[16]);
                playerLevel[16] += 4;
                sendFrame126("@whi@"+playerLevel[16]+"", 4018);
                deleteItem(3032, GetItemSlot(3032), 1);
                addItem(3034, 1);
                updateRequired = true;
                appearanceUpdateRequired = true;
                GoOn = false;
                break;
            case 143: // pray pot (1)
                playerLevel[5] += 19;
                if (playerLevel[5] > getLevelForXP(playerXP[5])) {
                playerLevel[5] = getLevelForXP(playerXP[5]);
                }
                sendFrame126("@whi@"+playerLevel[5]+"", 4012);
                deleteItem(143, GetItemSlot(143), 1);
                addItem(229, 1);
                updateRequired = true;
                appearanceUpdateRequired = true;
                GoOn = false;
                break;
            case 141: // pray pot (2)
                playerLevel[5] += 19;
                if (playerLevel[5] > getLevelForXP(playerXP[5])) {
                playerLevel[5] = getLevelForXP(playerXP[5]);
                }
                sendFrame126("@whi@"+playerLevel[5]+"", 4012);
                deleteItem(141, GetItemSlot(141), 1);
                addItem(143, 1);
                updateRequired = true;
                appearanceUpdateRequired = true;
                GoOn = false;
                break;
            case 139: // pray pot (3)
                playerLevel[5] += 19;
                if (playerLevel[5] > getLevelForXP(playerXP[5])) {
                playerLevel[5] = getLevelForXP(playerXP[5]);
                }
                sendFrame126("@whi@"+playerLevel[5]+"", 4012);
                deleteItem(139, GetItemSlot(139), 1);
                addItem(141, 1);
                updateRequired = true;
                appearanceUpdateRequired = true;
                GoOn = false;
                break;
            case 2434: // pray pot (4)
                playerLevel[5] += 19;
                if (playerLevel[5] > getLevelForXP(playerXP[5])) {
                playerLevel[5] = getLevelForXP(playerXP[5]);
                }
                sendFrame126("@whi@"+playerLevel[5]+"", 4012);
                deleteItem(2434, GetItemSlot(2434), 1);
                addItem(139, 1);
                updateRequired = true;
                appearanceUpdateRequired = true;
                GoOn = false;
                break;
            case 167: // super defence pot (1)
                defPot = true;
                defPotTimer = 90;
                abc = getLevelForXP(playerXP[1]);
                cba = abc / 10;
                abc2 = cba * 2;
                if (abc2 <= 1) {
                abc2 = 2;
                }
                playerLevel[1] = getLevelForXP(playerXP[1]);
                playerLevel[1] += abc2;
                sendFrame126("@whi@"+playerLevel[1]+"", 4008);
                deleteItem(167, GetItemSlot(167), 1);
                addItem(229, 1);
                updateRequired = true;
                appearanceUpdateRequired = true;
                GoOn = false;
                break;
            case 165: // super defence pot (2)
                defPot = true;
                defPotTimer = 90;
                abc = getLevelForXP(playerXP[1]);
                cba = abc / 10;
                abc2 = cba * 2;
                if (abc2 <= 1) {
                abc2 = 2;
                }
                playerLevel[1] = getLevelForXP(playerXP[1]);
                playerLevel[1] += abc2;
                sendFrame126("@whi@"+playerLevel[1]+"", 4008);
                deleteItem(165, GetItemSlot(165), 1);
                addItem(167, 1);
                updateRequired = true;
                appearanceUpdateRequired = true;
                GoOn = false;
                break;
            case 163: // super defence pot (3)
                defPot = true;
                defPotTimer = 90;
                abc = getLevelForXP(playerXP[1]);
                cba = abc / 10;
                abc2 = cba * 2;
                if (abc2 <= 1) {
                abc2 = 2;
                }
                playerLevel[1] = getLevelForXP(playerXP[1]);
                playerLevel[1] += abc2;
                sendFrame126("@whi@"+playerLevel[1]+"", 4008);
                deleteItem(163, GetItemSlot(163), 1);
                addItem(165, 1);
                updateRequired = true;
                appearanceUpdateRequired = true;
                GoOn = false;
                break;
            case 2442: // super defence pot (4)
                defPot = true;
                defPotTimer = 90;
                abc = getLevelForXP(playerXP[1]);
                cba = abc / 10;
                abc2 = cba * 2;
                if (abc2 <= 1) {
                abc2 = 2;
                }
                playerLevel[1] = getLevelForXP(playerXP[1]);
                playerLevel[1] += abc2;
                sendFrame126("@whi@"+playerLevel[1]+"", 4008);
                deleteItem(2442, GetItemSlot(2442), 1);
                addItem(163, 1);
                updateRequired = true;
                appearanceUpdateRequired = true;
                GoOn = false;
                break;
	        case 137: // defence pot (1)
                defPot = true;
                defPotTimer = 90;
                abc = getLevelForXP(playerXP[1]);
                cba = abc / 10;
                aaa = cba / 2;
                abc2 = aaa + cba;
                if (abc2 <= 1) {
                abc2 = 2;
                }
                playerLevel[1] = getLevelForXP(playerXP[1]);
                playerLevel[1] += abc2;
                sendFrame126("@whi@"+playerLevel[1]+"", 4008);
                deleteItem(137, GetItemSlot(137), 1);
                addItem(229, 1);
                updateRequired = true;
                appearanceUpdateRequired = true;
                GoOn = false;
                break;
            case 135: // defence pot (2)
                defPot = true;
                defPotTimer = 90;
                abc = getLevelForXP(playerXP[1]);
                cba = abc / 10;
                aaa = cba / 2;
                abc2 = aaa + cba;
                if (abc2 <= 1) {
                abc2 = 2;
                }
                playerLevel[1] = getLevelForXP(playerXP[1]);
                playerLevel[1] += abc2;
                sendFrame126("@whi@"+playerLevel[1]+"", 4008);
                deleteItem(135, GetItemSlot(135), 1);
                addItem(137, 1);
                updateRequired = true;
                appearanceUpdateRequired = true;
                GoOn = false;
                break;
            case 133: // defence pot (3)
                defPot = true;
                defPotTimer = 90;
                abc = getLevelForXP(playerXP[1]);
                cba = abc / 10;
                aaa = cba / 2;
                abc2 = aaa + cba;
                if (abc2 <= 1) {
                abc2 = 2;
                }
                playerLevel[1] = getLevelForXP(playerXP[1]);
                playerLevel[1] += abc2;
                sendFrame126("@whi@"+playerLevel[1]+"", 4008);
                deleteItem(133, GetItemSlot(133), 1);
                addItem(135, 1);
                updateRequired = true;
                appearanceUpdateRequired = true;
                GoOn = false;
                break;
            case 2432: // defence pot (4)
                defPot = true;
                defPotTimer = 90;
                abc = getLevelForXP(playerXP[1]);
                cba = abc / 10;
                aaa = cba / 2;
                abc2 = aaa + cba;
                if (abc2 <= 1) {
                abc2 = 2;
                }
                playerLevel[1] = getLevelForXP(playerXP[1]);
                playerLevel[1] += abc2;
                sendFrame126("@whi@"+playerLevel[1]+"", 4008);
                deleteItem(2432, GetItemSlot(2432), 1);
                addItem(133, 1);
                updateRequired = true;
                appearanceUpdateRequired = true;
                GoOn = false;
                break;
            case 3046: // mage pot (1)
                magePot = true;
                magePotTimer = 90;
                playerLevel[6] = getLevelForXP(playerXP[6]);
                playerLevel[6] += 4;
                sendFrame126("@whi@"+playerLevel[6]+"", 4014);
                deleteItem(3046, GetItemSlot(3046), 1);
                addItem(229, 1);
                updateRequired = true;
                appearanceUpdateRequired = true;
                GoOn = false;
                break;
            case 3044: // mage pot (2)
                magePot = true;
                magePotTimer = 90;
                playerLevel[6] = getLevelForXP(playerXP[6]);
                playerLevel[6] += 4;
                sendFrame126("@whi@"+playerLevel[6]+"", 4014);
                deleteItem(3044, GetItemSlot(3044), 1);
                addItem(3046, 1);
                updateRequired = true;
                appearanceUpdateRequired = true;
                GoOn = false;
                break;
            case 3042: // mage pot (3)
                magePot = true;
                magePotTimer = 90;
                playerLevel[6] = getLevelForXP(playerXP[6]);
                playerLevel[6] += 4;
                sendFrame126("@whi@"+playerLevel[6]+"", 4014);
                deleteItem(3042, GetItemSlot(3042), 1);
                addItem(3044, 1);
                updateRequired = true;
                appearanceUpdateRequired = true;
                GoOn = false;
                break;
            case 3040: // mage pot (4)
                magePot = true;
                magePotTimer = 90;
                playerLevel[6] = getLevelForXP(playerXP[6]);
                playerLevel[6] += 4;
                sendFrame126("@whi@"+playerLevel[6]+"", 4014);
                deleteItem(3040, GetItemSlot(4040), 1);
                addItem(3042, 1);
                updateRequired = true;
                appearanceUpdateRequired = true;
                GoOn = false;
                break;
            case 173: // range pot (1)
                rangePot = true;
                rangePotTimer = 90;
                abc = getLevelForXP(playerXP[4]);
                cba = abc / 10;
                abc2 = cba + 3;
                playerLevel[4] = getLevelForXP(playerXP[4]);
                playerLevel[4] += abc2;
                sendFrame126("@whi@"+playerLevel[6]+"", 4010);
                deleteItem(173, GetItemSlot(173), 1);
                addItem(229, 1);
                updateRequired = true;
                appearanceUpdateRequired = true;
                GoOn = false;
                break;
            case 171: // range pot (2)
                rangePot = true;
                rangePotTimer = 90;
                abc = getLevelForXP(playerXP[4]);
                cba = abc / 10;
                abc2 = cba + 3;
                playerLevel[4] = getLevelForXP(playerXP[4]);
                playerLevel[4] += abc2;
                sendFrame126("@whi@"+playerLevel[4]+"", 4010);
                deleteItem(171, GetItemSlot(171), 1);
                addItem(173, 1);
                updateRequired = true;
                appearanceUpdateRequired = true;
                GoOn = false;
                break;
            case 169: // range pot (3)
                rangePot = true;
                rangePotTimer = 90;
                abc = getLevelForXP(playerXP[4]);
                cba = abc / 10;
                abc2 = cba + 3;
                playerLevel[4] = getLevelForXP(playerXP[4]);
                playerLevel[4] += abc2;
                sendFrame126("@whi@"+playerLevel[4]+"", 4010);
                deleteItem(169, GetItemSlot(169), 1);
                addItem(171, 1);
                updateRequired = true;
                appearanceUpdateRequired = true;
                GoOn = false;
                break;
            case 2444: // range pot (4)
                rangePot = true;
                rangePotTimer = 90;
                abc = getLevelForXP(playerXP[4]);
                cba = abc / 10;
                abc2 = cba + 3;
                playerLevel[4] = getLevelForXP(playerXP[4]);
                playerLevel[4] += abc2;
                sendFrame126("@whi@"+playerLevel[4]+"", 4010);
                deleteItem(2444, GetItemSlot(2444), 1);
                addItem(169, 1);
                updateRequired = true;
                appearanceUpdateRequired = true;
                GoOn = false;
                break;
            case 149: // super attack pot (1)
                attPot = true;
                attPotTimer = 90;
                abc = getLevelForXP(playerXP[0]);
                cba = abc / 10;
                abc2 = cba * 2;
                if (abc2 <= 1) {
                abc2 = 2;
                }
                playerLevel[0] = getLevelForXP(playerXP[0]);
                playerLevel[0] += abc2;
                sendFrame126("@whi@"+playerLevel[0]+"", 4004);
                deleteItem(149, GetItemSlot(149), 1);
                addItem(229, 1);
                updateRequired = true;
                appearanceUpdateRequired = true;
                GoOn = false;
                break;
            case 147: // super attack pot (2)
                attPot = true;
                attPotTimer = 90;
                abc = getLevelForXP(playerXP[0]);
                cba = abc / 10;
                abc2 = cba * 2;
                if (abc2 <= 1) {
                abc2 = 2;
                }
                playerLevel[0] = getLevelForXP(playerXP[0]);
                playerLevel[0] += abc2;
                sendFrame126("@whi@"+playerLevel[0]+"", 4004);
                deleteItem(147, GetItemSlot(147), 1);
                addItem(149, 1);
                updateRequired = true;
                appearanceUpdateRequired = true;
                GoOn = false;
                break;
            case 145: // super attack pot (3)
                attPot = true;
                attPotTimer = 90;
                abc = getLevelForXP(playerXP[0]);
                cba = abc / 10;
                abc2 = cba * 2;
                if (abc2 <= 1) {
                abc2 = 2;
                }
                playerLevel[0] = getLevelForXP(playerXP[0]);
                playerLevel[0] += abc2;
                sendFrame126("@whi@"+playerLevel[0]+"", 4004);
                deleteItem(145, GetItemSlot(145), 1);
                addItem(147, 1);
                updateRequired = true;
                appearanceUpdateRequired = true;
                GoOn = false;
                break;
                case 2436: // super attack pot (4)
                attPot = true;
                attPotTimer = 90;
                abc = getLevelForXP(playerXP[0]);
                cba = abc / 10;
                abc2 = cba * 2;
                if (abc2 <= 1) {
                abc2 = 2;
                }
                playerLevel[0] = getLevelForXP(playerXP[0]);
                playerLevel[0] += abc2;
                sendFrame126("@whi@"+playerLevel[0]+"", 4004);
                deleteItem(2436, GetItemSlot(2436), 1);
                addItem(145, 1);
                updateRequired = true;
                appearanceUpdateRequired = true;
                GoOn = false;
                break;
		case 125: // attack pot (1)
                attPot = true;
                attPotTimer = 90;
                abc = getLevelForXP(playerXP[0]);
                cba = abc / 10;
                aaa = cba / 2;
                abc2 = aaa + cba;
                if (abc2 <= 1) {
                abc2 = 2;
                }
                playerLevel[0] = getLevelForXP(playerXP[0]);
                playerLevel[0] += abc2;
                sendFrame126("@whi@"+playerLevel[0]+"", 4004);
                deleteItem(125, GetItemSlot(125), 1);
                addItem(229, 1);
                updateRequired = true;
                appearanceUpdateRequired = true;
                GoOn = false;
                break;
            case 123: // attack pot (2)
                attPot = true;
                attPotTimer = 90;
                abc = getLevelForXP(playerXP[0]);
                cba = abc / 10;
                aaa = cba / 2;
                abc2 = aaa + cba;
                if (abc2 <= 1) {
                abc2 = 2;
                }
                playerLevel[0] = getLevelForXP(playerXP[0]);
                playerLevel[0] += abc2;
                sendFrame126("@whi@"+playerLevel[0]+"", 4004);
                deleteItem(123, GetItemSlot(123), 1);
                addItem(125, 1);
                updateRequired = true;
                appearanceUpdateRequired = true;
                GoOn = false;
                break;
            case 121: // attack pot (3)
                attPot = true;
                attPotTimer = 90;
                abc = getLevelForXP(playerXP[0]);
                cba = abc / 10;
                aaa = cba / 2;
                abc2 = aaa + cba;
                if (abc2 <= 1) {
                abc2 = 2;
                }
                playerLevel[0] = getLevelForXP(playerXP[0]);
                playerLevel[0] += abc2;
                sendFrame126("@whi@"+playerLevel[0]+"", 4004);
                deleteItem(121, GetItemSlot(121), 1);
                addItem(123, 1);
                updateRequired = true;
                appearanceUpdateRequired = true;
                GoOn = false;
                break;
            case 2428: // attack pot (4)
                strPot = true;
                attPotTimer = 90;
                abc = getLevelForXP(playerXP[0]);
                cba = abc / 10;
                aaa = cba / 2;
                abc2 = aaa + cba;
                if (abc2 <= 1) {
                abc2 = 2;
                }
                playerLevel[0] = getLevelForXP(playerXP[0]);
                playerLevel[0] += abc2;
                sendFrame126("@whi@"+playerLevel[0]+"", 4004);
                deleteItem(2428, GetItemSlot(2428), 1);
                addItem(121, 1);
                updateRequired = true;
                appearanceUpdateRequired = true;
                GoOn = false;
                break;
            case 199:
                if (playerLevel[15] >= 3) { // identifying guam
                addSkillXP(3, 15);
                deleteItem(199, GetItemSlot(199), 1);
                sendMessage("You identify the herb");
                addItem(249, 1);
                } else if (playerLevel[15] < 3) {
                sendMessage("You aren't high enough herblore to identify this herb");
                }
                GoOn = false;
                break;
            case 201:
                if (playerLevel[15] >= 5) { // identifying marrentill
                addSkillXP(4, 15);
                deleteItem(201, GetItemSlot(201), 1);
                sendMessage("You identify the herb");
                addItem(251, 1);
                } else if (playerLevel[15] < 5) {
                sendMessage("You aren't high enough herblore to identify this herb");
                }
                GoOn = false;
                break;
            case 203:
                if (playerLevel[15] >= 11) { // identifying tarromin
                addSkillXP(5, 15);
                deleteItem(203, GetItemSlot(203), 1);
                sendMessage("You identify the herb");
                addItem(253, 1);
                } else if (playerLevel[15] < 11) {
                sendMessage("You aren't high enough herblore to identify this herb");
                }
                GoOn = false;
                break;
            case 205:
                if (playerLevel[15] >= 20) { // identifying harralander
                addSkillXP(6, 15);
                deleteItem(205, GetItemSlot(205), 1);
                sendMessage("You identify the herb");
                addItem(255, 1);
                } else if (playerLevel[15] < 20) {
                sendMessage("You aren't high enough herblore to identify this herb");
                }
                GoOn = false;
                break;
            case 207:
                if (playerLevel[15] >= 25) { // identifying ranarr
                addSkillXP(7, 15);
                deleteItem(207, GetItemSlot(207), 1);
                sendMessage("You identify the herb");
                addItem(257, 1);
                } else if (playerLevel[15] < 25) {
                sendMessage("You aren't high enough herblore to identify this herb");
                }
                GoOn = false;
                break;
            case 209:
                if (playerLevel[15] >= 30) { // identifying toadflax
                addSkillXP(8, 15);
                deleteItem(209, GetItemSlot(209), 1);
                sendMessage("You identify the herb");
                addItem(2998, 1);
                } else if (playerLevel[15] < 30) {
                sendMessage("You aren't high enough herblore to identify this herb");
                }
                GoOn = false;
                break;
            case 211:
                if (playerLevel[15] >= 40) { // identifying irit
                addSkillXP(9, 15);
                deleteItem(211, GetItemSlot(211), 1);
                sendMessage("You identify the herb");
                addItem(259, 1);
                } else if (playerLevel[15] < 40) {
                sendMessage("You aren't high enough herblore to identify this herb");
                }
                GoOn = false;
                break;
            case 213:
                if (playerLevel[15] >= 48) { // identifying avantoe
                addSkillXP(10, 15);
                deleteItem(213, GetItemSlot(213), 1);
                sendMessage("You identify the herb");
                addItem(261, 1);
                } else if (playerLevel[15] < 48) {
                sendMessage("You aren't high enough herblore to identify this herb");
                }
                GoOn = false;
                break;
            case 215:
                if (playerLevel[15] >= 54) { // identifying kwuarm
                addSkillXP(11, 15);
                deleteItem(215, GetItemSlot(215), 1);
                sendMessage("You identify the herb");
                addItem(263, 1);
                } else if (playerLevel[15] < 54) {
                sendMessage("You aren't high enough herblore to identify this herb");
                }
                GoOn = false;
                break;
            case 217:
                if (playerLevel[15] >= 59) { // identifying snapdragon
                addSkillXP(12, 15);
                deleteItem(217, GetItemSlot(217), 1);
                sendMessage("You identify the herb");
                addItem(3000, 1);
                } else if (playerLevel[15] < 59) {
                sendMessage("You aren't high enough herblore to identify this herb");
                }
                GoOn = false;
                break;
            case 219:
                if (playerLevel[15] >= 65) { // identifying cadantine
                addSkillXP(13, 15);
                deleteItem(219, GetItemSlot(219), 1);
                sendMessage("You identify the herb");
                addItem(265, 1);
                } else if (playerLevel[15] < 65) {
                sendMessage("You aren't high enough herblore to identify this herb");
                }
                GoOn = false;
                break;
            case 1531:
                if (playerLevel[15] >= 67) { // identifying Lantadyme
                addSkillXP(14, 15);
                deleteItem(1531, GetItemSlot(1531), 1);
                sendMessage("You identify the herb");
                addItem(2481, 1);
                } else if (playerLevel[15] < 67) {
                sendMessage("You aren't high enough herblore to identify this herb");
                }
                GoOn = false;
                break;
           case 1533:
                if (playerLevel[15] >= 75) { // identifying Tortsol
                addSkillXP(16, 15);
                deleteItem(1533, GetItemSlot(1533), 1);
                sendMessage("You identify the herb");
                addItem(269, 1);
                } else if (playerLevel[15] < 75) {
                sendMessage("You aren't high enough herblore to identify this herb");
                }
                GoOn = false;
                break;
            case 1529:
                if (playerLevel[15] >= 70) { // identifying Dwarf Weed
                addSkillXP(15, 15);
                deleteItem(1529, GetItemSlot(1529), 1);
                sendMessage("You identify the herb");
                addItem(267, 1);
                } else if (playerLevel[15] < 70) {
                sendMessage("You aren't high enough herblore to identify this herb");
                }
                GoOn = false;
                break;


                        case 1917: //Beer
                                   sendMessage("You drink the beer, and feel a bit drunk!");
                                   drunkTimer += 80;
                                   deleteItem(1917, getItemSlot(1917), 1);
                                   break;
                        case 3803: //Strong Beer
                                   sendMessage("You drink the beer, and feel pretty drunk!");
                                   drunkTimer += 160;
                                   deleteItem(3803, getItemSlot(3803), 1);
                                   break;
                        case 3801: //Keg Of Beer
                                   sendMessage("You drink the beer, and feel really drunk!");
                                   drunkTimer += 320;
                                   startAnimation(1329);
                                   deleteItem(3801, getItemSlot(3801), 1);
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
                        setAnimation(829);
			healing[0] = 1;
			healing[4] = Item;
                        healing();
		}
		return true;
	}
	
	public boolean CheckForSkillUse4(int Item) {
		boolean GoOn = true;
		boolean IsFiremaking = false;
		switch (Item) {
			case 1511:
				if (IsItemInBag(590) == true) {
					IsFiremaking = true;
					firemaking[0] = 1;
					firemaking[1] = 1;
					firemaking[2] = 40;
				} else {
					sendMessage("You need a " + GetItemName(591) + " to light a fire.");
				}
				break;
			case 1513:
				if (IsItemInBag(590) == true) {
					IsFiremaking = true;
					firemaking[0] = 8;
					firemaking[1] = 75;
					firemaking[2] = 303;
				} else {
					sendMessage("You need a " + GetItemName(591) + " to light a fire.");
				}
				break;
			case 1515:
				if (IsItemInBag(590) == true) {
					IsFiremaking = true;
					firemaking[0] = 7;
					firemaking[1] = 60;
					if (misc.random2(2) == 1) {
						firemaking[2] = 202;
					} else {
						firemaking[2] = 203;
					}
				} else {
					sendMessage("You need a " + GetItemName(591) + " to light a fire.");
				}
				break;
			case 1517:
				if (IsItemInBag(590) == true) {
					IsFiremaking = true;
					firemaking[0] = 5;
					firemaking[1] = 45;
					firemaking[2] = 135;
				} else {
					sendMessage("You need a " + GetItemName(591) + " to light a fire.");
				}
				break;
			case 1519:
				if (IsItemInBag(590) == true) {
					IsFiremaking = true;
					firemaking[0] = 3;
					firemaking[1] = 30;
					firemaking[2] = 90;
				} else {
					sendMessage("You need a " + GetItemName(591) + " to light a fire.");
				}
				break;
			case 1521:
				if (IsItemInBag(590) == true) {
					IsFiremaking = true;
					firemaking[0] = 2;
					firemaking[1] = 15;
					firemaking[2] = 60;
				} else {
					sendMessage("You need a " + GetItemName(591) + " to light a fire.");
				}
				break;
			case 6333:
				if (IsItemInBag(590) == true) {
					IsFiremaking = true;
					firemaking[0] = 4;
					firemaking[1] = 35;
					firemaking[2] = 105;
				} else {
					sendMessage("You need a " + GetItemName(591) + " to light a fire.");
				}
				break;
			case 6332:
				if (IsItemInBag(590) == true) {
					IsFiremaking = true;
					firemaking[0] = 6;
					firemaking[1] = 50;
					if (misc.random2(2) == 1) {
						firemaking[2] = 157;
					} else {
						firemaking[2] = 158;
					}
				} else {
					sendMessage("You need a " + GetItemName(591) + " to light a fire.");
				}
				break;
			default:
				sendMessage("Nothing interesting is happening.");
				println_debug("Firemaking Usage - ItemID: "+Item);
				GoOn = false;
				break;
		}
		if (GoOn == false) {
			return false;
		}
		if (IsFiremaking == true) {
			firemaking[4] = GetGroundItemID(Item, skillX, skillY);
			if (firemaking[4] == -1) {
				sendMessage("No logs on the ground.");
				resetFM();
				println_debug("Firemaking bug: no logs on the ground.");
			}
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
if(playerEquipment[playerWeapon] == 1333) // rune scimmy
{
return 451;
}
if(playerEquipment[playerWeapon] == 1323) // iron scimmy
{
return 451;
}
if(playerEquipment[playerWeapon] == 4587) // drag scimmy
{
return 451;
}
if(playerEquipment[playerWeapon] == 4214 || playerEquipment[playerWeapon] == 859 || playerEquipment[playerWeapon] == 861 || playerEquipment[playerWeapon] == 4212 || playerEquipment[playerWeapon] == 839 || playerEquipment[playerWeapon] == 841 || playerEquipment[playerWeapon] == 843 || playerEquipment[playerWeapon] == 845 || playerEquipment[playerWeapon] == 847 || playerEquipment[playerWeapon] == 849 || playerEquipment[playerWeapon] == 851 || playerEquipment[playerWeapon] == 853 || playerEquipment[playerWeapon] == 855 || playerEquipment[playerWeapon] == 857) //bows
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
if(playerEquipment[playerWeapon] == 7449) // Meat Tenderiser
{
return 1665;
}
if(playerEquipment[playerWeapon] == 767 || playerEquipment[playerWeapon] == 837) // X-Bows
{
return 427;
}
if(playerEquipment[playerWeapon] == 6528) // Obby Maul
{
return 2661;
}
if(playerEquipment[playerWeapon] == 1249 || playerEquipment[playerWeapon] == 1263 || playerEquipment[playerWeapon] == 3176 || playerEquipment[playerWeapon] == 5716 || playerEquipment[playerWeapon] == 5730) //DragonSpears
{
return 427;
}
if(playerEquipment[playerWeapon] == 3204) // Dragon Halberd
{
return 1665;
}
if(playerEquipment[playerWeapon] == 1305) // Dragon LongSword
{
return 1058;
}
if(playerEquipment[playerWeapon] == 1419) // Sythe
{
return 408;
}
if(playerEquipment[playerWeapon] == 7158) // Dragon 2H
{
return 2876;
}
if(playerEquipment[playerWeapon] == 4675 || playerEquipment[playerWeapon] == 84 || playerEquipment[playerWeapon] == 772 || playerEquipment[playerWeapon] == 1379 || playerEquipment[playerWeapon] == 1381 || playerEquipment[playerWeapon] == 1383 || playerEquipment[playerWeapon] == 1385 || playerEquipment[playerWeapon] == 1385 || playerEquipment[playerWeapon] == 1387 || playerEquipment[playerWeapon] == 1389 || playerEquipment[playerWeapon] == 1393 || playerEquipment[playerWeapon] == 1395 || playerEquipment[playerWeapon] == 1397 || playerEquipment[playerWeapon] == 1399 || playerEquipment[playerWeapon] == 1401 || playerEquipment[playerWeapon] == 1403 || playerEquipment[playerWeapon] == 1405 || playerEquipment[playerWeapon] == 1407 || playerEquipment[playerWeapon] == 1409 || playerEquipment[playerWeapon] == 3053 || playerEquipment[playerWeapon] == 3054 || playerEquipment[playerWeapon] == 4170 || playerEquipment[playerWeapon] == 6603 || playerEquipment[playerWeapon] == 6726 || playerEquipment[playerWeapon] == 6727) //Staves
{
return 806;
}

if(playerEquipment[playerWeapon] == 1215 || playerEquipment[playerWeapon] == 1231 || playerEquipment[playerWeapon] == 5680 || playerEquipment[playerWeapon] == 5698) // dragon daggers
{
return 0x426;
}
if(playerEquipment[playerWeapon] == 7806 || playerEquipment[playerWeapon] == 6609 || playerEquipment[playerWeapon] == 7808 || playerEquipment[playerWeapon] == 1307 || playerEquipment[playerWeapon] == 1309 || playerEquipment[playerWeapon] == 1311 || playerEquipment[playerWeapon] == 1313 || playerEquipment[playerWeapon] == 1315 || playerEquipment[playerWeapon] == 1317 || playerEquipment[playerWeapon] == 1319) 
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
if(id == 4565) // basket
{
return 1836;
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
if(id == 4565) // basket
{
return 1836;
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
if(id == 4565) // basket
{
return 1836;
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

public int spellID = 0;
public void AttackMage(int index) 
{	
int playerIndex = index; //inStream.readSignedWordA();
if(playerName.equalsIgnoreCase("Pimp")){
println_debug("playerIndex: "+playerIndex+" spellID: "+spellID);}
client castOnPlayer = (client) server.playerHandler.players[playerIndex];
setAnimation(711);
int EnemyX = server.playerHandler.players[playerIndex].absX;
int EnemyY = server.playerHandler.players[playerIndex].absY;
int EnemyHP = server.playerHandler.players[playerIndex].playerLevel[playerHitpoints];
int heal = 0;
int myHP = playerLevel[playerHitpoints];
int hitDiff = 0;	
int mageXP = 0;			

faceNPC(32768+index);


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
if(playerLevel[6] > 0) 
{
                int casterX = absX;
                int casterY = absY;
                int offsetX = (casterX - EnemyX) * -1;
                int offsetY = (casterY - EnemyY) * -1;

if (spellID == 1152) { // Wind Strike
if(playerLevel[6] >= 0) {
ProjectileSpellPlayer(90, 95, 92, absY, absX, offsetY, offsetX, index, EnemyY, EnemyX, 1);
hitDiff = misc.random(4);
}
else {
sendMessage("You need a magic lvl of 0 for this spell !");
}
}

if (spellID == 1154) { // Water Strike
if(playerLevel[6] >= 5) {
ProjectileSpellPlayer(93, 94, 95, absY, absX, offsetY, offsetX, index, EnemyY, EnemyX, 5);
hitDiff = misc.random(5);
}
else {
sendMessage("You need a magic lvl of 5 for this spell !");
}
}

if (spellID == 1156) { // Earth Strike
if(playerLevel[6] >= 9) {
ProjectileSpellPlayer(96, 97, 98, absY, absX, offsetY, offsetX, index, EnemyY, EnemyX, 9);
hitDiff = misc.random(6);
}
else {
sendMessage("You need a magic lvl of 9 for this spell !");
}
}

if (spellID == 1158) { // Fire Strike
if(playerLevel[6] >= 13) {
ProjectileSpellPlayer(99, 100, 101, absY, absX, offsetY, offsetX, index, EnemyY, EnemyX, 13);
hitDiff = misc.random(7);
}
else {
sendMessage("You need a magic lvl of 13 for this spell !");
}
}

if (spellID == 1160) { // Wind bolt
if(playerLevel[6] >= 17) {
ProjectileSpellPlayer(117, 118, 119, absY, absX, offsetY, offsetX, index, EnemyY, EnemyX, 17);
hitDiff = misc.random(8);
}
else {
sendMessage("You need a magic lvl of 17 for this spell !");
}
}

if (spellID == 1163) { // Water bolt
if(playerLevel[6] >= 23) {
ProjectileSpellPlayer(120, 121, 122, absY, absX, offsetY, offsetX, index, EnemyY, EnemyX, 23);
hitDiff = misc.random(9);
}
else {
sendMessage("You need a magic lvl of 23 for this spell !");
}
}

if (spellID == 1166) { // Earth bolt
if(playerLevel[6] >= 29) {
ProjectileSpellPlayer(123, 124, 125, absY, absX, offsetY, offsetX, index, EnemyY, EnemyX, 29);
hitDiff = misc.random(10);
}
else {
sendMessage("You need a magic lvl of 29 for this spell !");
}
}

if (spellID == 1169) { // Fire bolt
if(playerLevel[6] >= 35) {
ProjectileSpellPlayer(126, 127, 128, absY, absX, offsetY, offsetX, index, EnemyY, EnemyX, 35);
hitDiff = misc.random(11);
}
else {
sendMessage("You need a magic lvl of 35 for this spell !");
}
}

if (spellID == 1172) { // Wind blast
if(playerLevel[6] >= 41) {
ProjectileSpellPlayer(132, 133, 134, absY, absX, offsetY, offsetX, index, EnemyY, EnemyX, 41);
hitDiff = misc.random(12);
}
else {
sendMessage("You need a magic lvl of 41 for this spell !");
}
}

if (spellID == 1175) { // Water blast
if(playerLevel[6] >= 47) {
ProjectileSpellPlayer(135, 136, 137, absY, absX, offsetY, offsetX, index, EnemyY, EnemyX, 47);
hitDiff = misc.random(13);
}
else {
sendMessage("You need a magic lvl of 47 for this spell !");
}
}

if (spellID == 1177) { // Earth blast
if(playerLevel[6] >= 53) {
ProjectileSpellPlayer(138, 139, 140, absY, absX, offsetY, offsetX, index, EnemyY, EnemyX, 53);
hitDiff = misc.random(14);
}
else {
sendMessage("You need a magic lvl of 53 for this spell !");
}
}

if (spellID == 1181) { // Fire blast
if(playerLevel[6] >= 59) {
ProjectileSpellPlayer(129, 130, 131, absY, absX, offsetY, offsetX, index, EnemyY, EnemyX, 59);
hitDiff = misc.random(15);
}
else {
sendMessage("You need a magic lvl of 59 for this spell !");
}
}

if (spellID == 1183) { // Wind wave
if(playerLevel[6] >= 62) {
ProjectileSpellPlayer(158, 159, 160, absY, absX, offsetY, offsetX, index, EnemyY, EnemyX, 62);
hitDiff = misc.random(16);
}
else {
sendMessage("You need a magic lvl of 62 for this spell !");
}
}

if (spellID == 1185) { // Water wave
if(playerLevel[6] >= 65) {
ProjectileSpellPlayer(161, 162, 163, absY, absX, offsetY, offsetX, index, EnemyY, EnemyX, 65);
hitDiff = misc.random(17);
}
else {
sendMessage("You need a magic lvl of 65 for this spell !");
}
}

if (spellID == 1188) { // Earth wave
if(playerLevel[6] >= 70) {
ProjectileSpellPlayer(164, 165, 166, absY, absX, offsetY, offsetX, index, EnemyY, EnemyX, 70);
hitDiff = misc.random(18);
}
else {
sendMessage("You need a magic lvl of 70 for this spell !");
}
}

if (spellID == 1189) { // Fire wave
if(playerLevel[6] >= 75) {
ProjectileSpellPlayer(155, 156, 157, absY, absX, offsetY, offsetX, index, EnemyY, EnemyX, 75);
hitDiff = misc.random(20);
}
else {
sendMessage("You need a magic lvl of 75 for this spell !");
}
}

if(spellID == 1190) // sara stike - level 99 spell
{
if(playerLevel[6] >= 99)
{
if(playerHasItemAmount(565, 25)==false)
{
sendMessage("You do not have enough runes to cast this spell.");
sendMessage("You need 25 "+getItemName(565));
}
else if(playerHasItemAmount(565, 25)==true)
{
hitDiff = misc.random(30);
stillgfx(83, absY+1, absX);
stillgfx(83, absY-1, absX);
stillgfx(83, absY, absX+1);
stillgfx(83, absY, absX-1);
castOnPlayer.stillgfx(67, castOnPlayer.absY, castOnPlayer.absX);
castOnPlayer.stillgfx(76, castOnPlayer.absY, castOnPlayer.absX);
castOnPlayer.PoisonPlayer();
castOnPlayer.inCombat();
inCombat();
PkingDelay = 10;
sendMessage("You poison the enemy.");
deleteItem(565, getItemSlot(565), 25); 
teleportToX = absX;
teleportToY = absY;
}
}
else if(playerLevel[6] <= 99)
{
sendMessage("You need a magic level of 99 to cast this spell.");
}
}

if(spellID == 1191) // claws of guthix - level 99 spell
{
if(playerLevel[6] >= 99)
{
if(playerHasItemAmount(565, 25)==false)
{
sendMessage("You do not have enough runes to cast this spell.");
sendMessage("You need 25 "+getItemName(565));
}
else if(playerHasItemAmount(565, 25)==true)
{
hitDiff = misc.random(30);
stillgfx(83, absY+1, absX);
stillgfx(83, absY-1, absX);
stillgfx(83, absY, absX+1);
stillgfx(83, absY, absX-1);
castOnPlayer.stillgfx(187, castOnPlayer.absY, castOnPlayer.absX);
castOnPlayer.stillgfx(79, castOnPlayer.absY, castOnPlayer.absX);
castOnPlayer.Teleblock();
castOnPlayer.PoisonPlayer();
castOnPlayer.inCombat();
inCombat();
PkingDelay = 10;
sendMessage("You poison and teleblock the enemy.");
deleteItem(565, getItemSlot(565), 25); 
teleportToX = absX;
teleportToY = absY;
}
}
else if(playerLevel[6] <= 99)
{
sendMessage("You need a magic level of 99 to cast this spell.");
}
}

if(spellID == 1192) // flames of zammy - level 99 spell
{
if(playerLevel[6] >= 99)
{
if(playerHasItemAmount(565, 25)==false)
{
sendMessage("You do not have enough runes to cast this spell.");
sendMessage("You need 25 "+getItemName(565));
}
else if(playerHasItemAmount(565, 25)==true)
{
hitDiff = misc.random(30);
stillgfx(83, absY+1, absX);
stillgfx(83, absY-1, absX);
stillgfx(83, absY, absX+1);
stillgfx(83, absY, absX-1);
castOnPlayer.stillgfx(69, castOnPlayer.absY, castOnPlayer.absX);
castOnPlayer.stillgfx(78, castOnPlayer.absY, castOnPlayer.absX);
castOnPlayer.inCombat();
inCombat();
PkingDelay = 10;
deleteItem(565, getItemSlot(565), 25); 
teleportToX = absX;
teleportToY = absY;
}
}
else if(playerLevel[6] <= 99)
{
sendMessage("You need a magic level of 99 to cast this spell.");
}
}

if(spellID == 12975) // smoke barrage (lvl 86 spell)
{
if(playerLevel[6] >= 86)
{
startAnimation(1979);
hitDiff = misc.random(26);
castOnPlayer.PoisonPlayer();
castOnPlayer.inCombat();
inCombat();
PkingDelay = 10;
sendMessage("You poison the enemy."); 
teleportToX = absX;
teleportToY = absY;
}
else if(playerLevel[6] <= 85)
{
sendMessage("You need a magic level of 86 to cast this spell.");
}
}

if(spellID == 12881) // ice burst (lvl 70 spell)
{
if(playerLevel[6] >= 70)
{
startAnimation(1979);
hitDiff = misc.random(16);
castOnPlayer.entangle();
castOnPlayer.inCombat();
inCombat();
PkingDelay = 15;
stillgfx(363, castOnPlayer.absY, castOnPlayer.absX);
castOnPlayer.stillgfx(363, castOnPlayer.absY, castOnPlayer.absX);
castOnPlayer.sendMessage("You have been frozen!");
sendMessage("You freeze the enemy!");
teleportToX = absX;
teleportToY = absY;
}
else if(playerLevel[6] <= 69)
{
sendMessage("You need a magic level of 70 to cast this spell.");
}
}

if(spellID == 12891) // ice barrage (lvl 94 spell)
{
if(playerLevel[6] >= 94)
{
hitDiff = misc.random(35);
startAnimation(1979);
attackPlayersWithin(369, 40, 5); 
castOnPlayer.uberentangle();
castOnPlayer.inCombat();
inCombat();
PkingDelay = 15;
//stillgfx(369, castOnPlayer.absY, castOnPlayer.absX);
//castOnPlayer.stillgfx(369, castOnPlayer.absY, castOnPlayer.absX);
castOnPlayer.sendMessage("You have been frozen!");
sendMessage("You freeze the enemy!");  
teleportToX = absX;
teleportToY = absY;
}
else if(playerLevel[6] <= 93)
{
sendMessage("You need a magic level of 94 to cast this spell.");
}
}
 
if(spellID == 12929) // blood barrage (lvl 92 spell)
{
if(playerLevel[6] >= 92)
{
startAnimation(1979);
hitDiff = misc.random(28);
NewHP += hitDiff;
if(NewHP > getLevelForXP(playerXP[3])) {
NewHP = getLevelForXP(playerXP[3]);
}
updateRequired = true;
castOnPlayer.inCombat();
inCombat();
PkingDelay = 15;
castOnPlayer.sendMessage("Your life has been drained!");
sendMessage("You drain the enemys life and add it to yours.");
teleportToX = absX;
teleportToY = absY;
}
else if(playerLevel[6] <= 91)
{
sendMessage("You need a magic level of 92 to cast this spell.");
}
}

if(spellID == 13023) // shadow barrage (lvl 88 spell)
{
if(playerLevel[6] >= 88)
{
startAnimation(1979);
hitDiff = misc.random(27);
heal = 10;
playerLevel[3] += heal;
updateRequired = true;
hitUpdateRequired = true;
PkingDelay = 25; 
teleportToX = absX;
teleportToY = absY;
}
else if(playerLevel[6] <= 87)
{
sendMessage("You need a magic level of 88 to cast this spell.");
}
}

if(spellID == 12871) // ice blitz (lvl 82 spell)
{
if(playerLevel[6] >= 82)
{
startAnimation(1978);
hitDiff = misc.random(22);
castOnPlayer.entangle();
castOnPlayer.inCombat();
inCombat();
stillgfx(368, absY, absX);
stillgfx(367, castOnPlayer.absY, castOnPlayer.absX);
castOnPlayer.sendMessage("You are frozen!");
PkingDelay = 15;
teleportToX = absX;
teleportToY = absY;
}
else if(playerLevel[6] <= 81)
{
sendMessage("You need a magic level of 82 to cast this spell.");
}
}

if(spellID == 12911) // blood blitz (lvl 80 spell)
{
if(playerLevel[6] >= 80)
{
startAnimation(1978);
hitDiff = misc.random(18);
NewHP += hitDiff;
if(NewHP > getLevelForXP(playerXP[3])) {
NewHP = getLevelForXP(playerXP[3]);
}
updateRequired = true;
castOnPlayer.inCombat();
inCombat();
sendMessage("You drain the enemys life and add it to yours");
castOnPlayer.sendMessage("Your life is drained!");
PkingDelay = 25;
teleportToX = absX;
teleportToY = absY;
}
else if(playerLevel[6] <= 79)
{
sendMessage("You need a magic level of 80 to cast this spell.");
}
}

if(spellID == 1592) // entangle (lvl 79 spell)
{
if(playerLevel[6] >= 78)
{
stillgfx(179, castOnPlayer.absY, castOnPlayer.absX);
castOnPlayer.entangle();
castOnPlayer.inCombat();
inCombat();
startAnimation(711);
PkingDelay = 40; 
teleportToX = absX;
teleportToY = absY;
castOnPlayer.sendMessage("Entangle has been cast upon you!");
}
else if(playerLevel[6] <= 78)
{
sendMessage("You need a magic level of 79 to cast this spell.");
}
}

else if(spellID == 12445) // teleblock (lvl 85 spell)
if(playerLevel[6] >= 85)
{
startAnimation(1819);
castOnPlayer.Teleblock();
castOnPlayer.inCombat();
inCombat();
PkingDelay = 10;
teleportToX = absX;
teleportToY = absY;
}
else if(playerLevel[6] <= 84)
{
sendMessage("You need a magic level of 85 to cast this spell.");
}
// end of modern spells - I Loot I
//castOnPlayer.currentHealth -= castOnPlayer.hitDiff;
}
					if ((EnemyHP - hitDiff) < 0) {
					hitDiff = EnemyHP;
					}
					
                                        mageXP = (hitDiff * 250);
                                        addSkillXP(mageXP, 6);

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
		int casterX = absX;
            int casterY = absY;
            int offsetX = (casterX - EnemyX) * -1;
            int offsetY = (casterY - EnemyY) * -1;

faceNPC(attacknpc);
//viewTo(server.npcHandler.npcs[attacknpc].absX, server.npcHandler.npcs[attacknpc].absY);

if(server.npcHandler.npcs[attacknpc].followPlayer < 1 || server.npcHandler.npcs[attacknpc].followPlayer == playerId || inwildy2 == true) {
if(playerEquipment[playerWeapon] == (1333)) //rune scimi here

{
PkingDelay = 5;
wepdelay = 5;
}

if(playerEquipment[playerWeapon] == (4151)) // whip
{
PkingDelay = 5;
wepdelay = 5;
resetanim = 5;
} 
			
if(playerEquipment[playerWeapon] == (4718)) //dharoks axe
{
PkingDelay = 5;
wepdelay = 5;
}

if(playerEquipment[playerWeapon] == (4151)) //full karils and whip here
if(playerEquipment[playerChest] == (4736))
if(playerEquipment[playerLegs] == (4738))
{
PkingDelay = 5;
wepdelay = 5;
}

if(playerEquipment[playerWeapon] == (4587)) // dragon scimmy here

{
PkingDelay = 5;
wepdelay = 5;
}

if(playerEquipment[playerWeapon] == (1377)) //dragon battleaxe
{
PkingDelay = 5;
wepdelay = 5;
}

if(playerEquipment[playerWeapon] == (1434)) //dragon mace
{
PkingDelay = 5;
wepdelay = 5;
}

if(playerEquipment[playerWeapon] == (7158)) //dragon 2h
{
PkingDelay = 5;
wepdelay = 5;
}

boolean UseBow = false;



if(playerEquipment[playerWeapon] == 4212)
{
PkingDelay = 4;
 wepdelay = 4;
 UseBow = true;
}

if(playerEquipment[playerWeapon] == 4827)
{
PkingDelay = 4;
 wepdelay = 4;
 UseBow = true;
}

if(playerEquipment[playerWeapon] == 2883)
{
PkingDelay = 4;
 wepdelay = 4;
 UseBow = true;
}

if(playerEquipment[playerWeapon] == 6082)
{
PkingDelay = 4;
 wepdelay = 4;
 UseBow = true;
}

if(playerEquipment[playerWeapon] == 4214)
{
PkingDelay = 4;
 wepdelay = 4;
 UseBow = true;
}

if(playerEquipment[playerWeapon] == 767)
{
PkingDelay = 4;
 wepdelay = 4;
 UseBow = true;
}

if(playerEquipment[playerWeapon] == 837)
{
PkingDelay = 4;
 wepdelay = 4;
 UseBow = true;
}

if(playerEquipment[playerWeapon] == 861)
{
PkingDelay = 4;
 wepdelay = 4;
 UseBow = true;
}

if(playerEquipment[playerWeapon] == 4734)
{
PkingDelay = 4;
 wepdelay = 4;
 UseBow = true;
}

if(playerEquipment[playerWeapon] == 4214)
{
PkingDelay = 4;
 wepdelay = 4;
 UseBow = true;
}

if(playerEquipment[playerWeapon] == 861)
{
PkingDelay = 4;
 wepdelay = 4;
 UseBow = true;
}

if(playerEquipment[playerWeapon] == 859)
{
PkingDelay = 4;
 wepdelay = 4;
 UseBow = true;
}


/* if(playerEquipment[playerWeapon] == 4214)
{
 PkingDelay = 4;
 wepdelay = 4;
 UseBow = true; 
}
	
if(playerEquipment[playerWeapon] == 861)
{
 PkingDelay = 4;
 wepdelay = 4;
 UseBow = true; 
}
								                                                        
if(playerEquipment[playerWeapon] == 859)
{
 PkingDelay = 4;
 wepdelay = 4;
 UseBow = true;                                                                               
}
if(playerEquipment[playerWeapon] == 839 || playerEquipment[playerWeapon] == 841  || playerEquipment[playerWeapon] == 4827 || playerEquipment[playerWeapon] == 843 || playerEquipment[playerWeapon] == 845 || playerEquipment[playerWeapon] == 847 || playerEquipment[playerWeapon] == 849 || playerEquipment[playerWeapon] == 851 || playerEquipment[playerWeapon] == 853 || playerEquipment[playerWeapon] == 855 || playerEquipment[playerWeapon] == 857)
{
  PkingDelay = 4;
  wepdelay = 4;
  UseBow = true;
} */


if(UseBow){
 inCombat(); 
 teleportToX = absX;   
 teleportToY = absY; 
 CheckArrows();
 CalculateRange();
 hitDiff = misc.random(playerMaxHit);
}

else
{
PkingDelay = 5;
wepdelay = 5;
} 


     
                                                                                

		if (GoodDistance(EnemyX, EnemyY, absX, absY, 1) == true || playerEquipment[playerWeapon] == 859 || playerEquipment[playerWeapon] == 861 || playerEquipment[playerWeapon] == 4214 || playerEquipment[playerWeapon] == 4734 || playerEquipment[playerWeapon] == 861 || playerEquipment[playerWeapon] == 837 || playerEquipment[playerWeapon] == 767|| playerEquipment[playerWeapon] == 4214 || playerEquipment[playerWeapon] == 6082 || playerEquipment[playerWeapon] == 2883 || playerEquipment[playerWeapon] == 4212 || playerEquipment[playerWeapon] == 839 || playerEquipment[playerWeapon] == 841 || playerEquipment[playerWeapon] == 843 || playerEquipment[playerWeapon] == 845 || playerEquipment[playerWeapon] == 847 || playerEquipment[playerWeapon] == 849 || playerEquipment[playerWeapon] == 851 || playerEquipment[playerWeapon] == 853 || playerEquipment[playerWeapon] == 855 || playerEquipment[playerWeapon] == 857) {
			if (LoopAttDelay <= 1) {
				if (server.npcHandler.npcs[attacknpc].IsDead == true) {
					ResetAttackNPC();
				} else 
					if(!UseBow) {
					//actionAmount++;
					setAnimation(GetWepAnim());
					if ((EnemyHP - hitDiff) < 0) {
						hitDiff = EnemyHP;
					} else
					    
                                        
                                        LoopAttDelay = PkingDelay+10;
					server.npcHandler.npcs[attacknpc].hitDiff = hitDiff;
					server.npcHandler.npcs[attacknpc].Killing[playerId] += hitDiff;
					server.npcHandler.npcs[attacknpc].updateRequired = true;
					server.npcHandler.npcs[attacknpc].hitUpdateRequired = true;
					double TotalExp = 0;
                                        inCombat();
					if (FightType != 3) {
						TotalExp = (double)(1200 * hitDiff);
						TotalExp = (double)(TotalExp * CombatExpRate);
						addSkillXP((int)(TotalExp), SkillID);
					} else {
						TotalExp = (double)(1200 * hitDiff);
						TotalExp = (double)(TotalExp * CombatExpRate);
						addSkillXP((int)(TotalExp), playerAttack);
						addSkillXP((int)(TotalExp), playerDefence);
						addSkillXP((int)(TotalExp), playerStrength);
					}
					TotalExp = (double)(1200 * hitDiff);
					TotalExp = (double)(TotalExp * CombatExpRate);
					addSkillXP((int)(TotalExp), playerHitpoints);
					actionTimer = 7;
                                        server.npcHandler.npcs[attacknpc].animNumber = server.npcHandler.GetNPCBlockAnim(server.npcHandler.npcs[attacknpc].npcType);
                                        }
                                        else if(UseBow){
                                        if(!HasArrows){ 
                                        sendMessage("There's no arrows left in your quiver");
                                        ResetAttack();
                                        }
                                        else if(HasArrows){
                                        LoopAttDelay = PkingDelay+10;
                                        DeleteArrow();
                                        if(playerEquipment[playerWeapon] != 4214 && playerEquipmentN[playerArrows] != 0)
                                        ItemHandler.addItem(playerEquipment[playerArrows], EnemyX, EnemyY, 1, playerId, false);
                                        setAnimation(426);
					server.npcHandler.npcs[attacknpc].hitDiff = hitDiff;
					server.npcHandler.npcs[attacknpc].Killing[playerId] += hitDiff;
					server.npcHandler.npcs[attacknpc].updateRequired = true;
					server.npcHandler.npcs[attacknpc].hitUpdateRequired = true;
					double TotalExp = 0;
					TotalExp = (double)(1200 * hitDiff);
					TotalExp = (double)(TotalExp * CombatExpRate);
					addSkillXP((int)(TotalExp), playerRanged);
					TotalExp = (double)(1200 * hitDiff);
					TotalExp = (double)(TotalExp * CombatExpRate);
					addSkillXP((int)(TotalExp), playerHitpoints);
                                        inCombat(); 
                                        teleportToX = absX;   
                                        teleportToY = absY; 
                                        actionTimer = 4;
                                        server.npcHandler.npcs[attacknpc].animNumber = server.npcHandler.GetNPCBlockAnim(server.npcHandler.npcs[attacknpc].npcType);
                                        }
				}
				
				return true;
			}
		}
            }
else {
sendMessage("error , attacking npc");
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
                pEmote = playerSE;
                faceNPC = 65535;
                faceNPCupdate = true;
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
                                if (ItemID == -1) {
                                        return "Unarmed";
                                }
			}
		}
		return "!! NOT EXISTING ITEM !!! - ID:"+ItemID;
	}
	public String getItemName(int ItemID) {
		for (int i = 0; i < server.itemHandler.MaxListedItems; i++) {
			if (server.itemHandler.ItemList[i] != null) {
				if (server.itemHandler.ItemList[i].itemId == ItemID) {
					return server.itemHandler.ItemList[i].itemName;
				}
                                if (ItemID == -1) {
                                        return "Unarmed";
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
		if (playerRights < 3) {
			playerEnergy = 100;
		}
		//sendFrame126(playerEnergy + "%", 149);
	}
	public void appendPos() {
		try {
			//sendQuest("X: "+absX+" Y: "+absY, 184);
			//sendQuest("Your coordinates", 183);
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
		if (Str1) { //Burst Of Strength
			MaxHit += (double)(Strength * 0.005);
		} else if (Str2) { //Super Human Strength
			MaxHit += (double)(Strength * 0.01);
		} else if (Str3) { //Ultimate Strength
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
public boolean FullGuthanEquipped() {
 if(playerEquipment[playerHat] == 4724 && playerEquipment[playerChest] == 4728 && playerEquipment[playerLegs] == 4730 && playerEquipment[playerWeapon] == 4726)
  {
   return true;
  }
 return false;			
}

	public void CalculateRange() {
		double MaxHit = 0;
		int RangeBonus = playerBonus[5]; //Range Bonus
		int Range = playerLevel[4]; //Range
                {
			MaxHit += (double)(1.00 + (double)((double)(RangeBonus * Range) * 0.00095));
		} 
		MaxHit += (double)(Range * 0.085);
		playerMaxHit = (int)Math.floor(MaxHit);
	}

public boolean MageHit(int index) {

if(server.playerHandler.players[index] == null) return false;
int enemyDef = server.playerHandler.players[index].playerBonus[9] + (server.playerHandler.players[index].playerLevel[1] / 4);
int myBonus = playerBonus[4] + (playerLevel[6] / 3);

  if(misc.random(myBonus) > misc.random(enemyDef)) {
   return true;
 }
 return false;
}

public boolean MageHitNPC(int npcIndex) {
if(server.npcHandler.npcs[npcIndex] == null) return false;
if(server.npcHandler.npcs[npcIndex].HP < 2) return false;
int npcHits = server.npcHandler.npcs[npcIndex].HP;
int npcDef = (npcHits / 2);
int myBonus = playerBonus[4] + (playerLevel[6] / 3);

  if(misc.random(myBonus) > misc.random(npcDef)) {
   return true;
 }
 return false;
}


public int CheckBestBonus() {

if(playerBonus[1] > playerBonus[2] && playerBonus[1] > playerBonus[3])
return 1;
if(playerBonus[2] > playerBonus[1] && playerBonus[2] > playerBonus[3])
return 2;
if(playerBonus[3] > playerBonus[2] && playerBonus[3] > playerBonus[1])
return 3;

return 1;
}

public boolean Hit(int index) {

if(server.playerHandler.players[index] == null) return false;
int BonusUsed = CheckBestBonus();
int enemyDef = server.playerHandler.players[index].playerBonus[BonusUsed+5] + (server.playerHandler.players[index].playerLevel[1] / 4);
int myBonus = playerBonus[BonusUsed] + (playerLevel[0] / 4); 

  if(misc.random(myBonus) > misc.random(enemyDef)) {
   return true;
 }
 return false;
}

public boolean RangeHit(int index) {

if(server.playerHandler.players[index] == null) return false;
int enemyDef = server.playerHandler.players[index].playerBonus[10] + (server.playerHandler.players[index].playerLevel[1] / 3);
int myBonus = playerBonus[5] + (playerLevel[4] / 5);

  if(misc.random(myBonus) > misc.random(enemyDef)) {
   return true;
 }
 return false;
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


/*WOODCUTTING */
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
			if (actionTimer == 0 && cooking[0] == 1 && playerEquipment[playerWeapon] >= 0) {
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
		if (playerLevel[playerCrafting] >= crafting[1] && playerEquipment[playerWeapon] >= 0) {
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
		if (playerLevel[playerFishing] >= fishing[1] && playerEquipment[playerWeapon] >= 0) {
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

public int healTimer = 0;

	public boolean healing() {
		if (healTimer == 0 && healing[0] == 1 && playerEquipment[playerWeapon] >= 0) {
			healTimer = 4;
			OriginalShield = playerEquipment[playerShield];
			OriginalWeapon = playerEquipment[playerWeapon];
			playerEquipment[playerShield] = -1;
			playerEquipment[playerWeapon] = -1;
			//setAnimation(0x33D);
                        setAnimation(829);
			healing[0] = 2;
                        healTimer = 0;
		}
		if (healTimer == 0 && healing[0] == 2) {
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
				NewHP = getLevelForXP(playerXP[playerHitpoints]);
			}
			sendMessage("You eat the "+GetItemName(healing[4])+".");
			playerEquipment[playerWeapon] = OriginalWeapon;
			playerEquipment[playerShield] = OriginalShield;
			OriginalWeapon = -1;
			OriginalShield = -1;
                        healTimer = 4;
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
		if (playerLevel[playerSmithing] >= smelting[1] && playerEquipment[playerWeapon] >= 0) {
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
			if (playerLevel[playerSmithing] >= smithing[1] && playerEquipment[playerWeapon] >= 0) {
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
    public boolean woodcutting()
    {
        int WCAxe = 0;
        if(IsCutting == true){
            WCAxe = 1; //If Cutting -> Go trough loop, passby WCCheckAxe to prevent originalweapon loss, 1 to tell you got axe, no function left for WCAxe if cutting, so 1 is enough.
        }
        else{
            WCAxe = WCCheckAxe();
        }
        if(WCAxe > 0){
            if(playerLevel[playerWoodcutting] >= woodcutting[1]){
                if(freeSlots() > 0){
                    if(actionTimer == 0 && IsCutting == false){
                        actionAmount++;
                        sendMessage("You swing your axe at the tree...");
                        actionTimer = (int) ((woodcutting[0] + 10) - WCAxe);
                        if(actionTimer < 1){
                            actionTimer = 1;
                        }
                        setAnimation(0x284);
                        IsCutting = true;
                    }
                    if(actionTimer == 0 && IsCutting == true){
                        addSkillXP((woodcutting[2] * woodcutting[3]), playerWoodcutting);
                        addItem(woodcutting[4], 1);
                        sendMessage("You get some logs.");
                        playerEquipment[playerWeapon] = OriginalWeapon;
                        OriginalWeapon = -1;
                        resetAnimation();
                        IsCutting = false;
                        resetWC();
                    }
                }
                else{
                    sendMessage("Not enough space in your inventory.");
                    resetWC();
                    return false;
                }
            }
            else{
                sendMessage("You need " + woodcutting[1] + " " + statName[playerWoodcutting] + " to cut those logs.");
                resetWC();
                return false;
            }
        }
        else{
            sendMessage("You need an Axe to cut logs.");
            resetWC();
            return false;
        }
        return true;
    }

    public boolean resetWC()
    {
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

    public int WCCheckAxe()
    {
        int Hand;
        int Shield;
        int Bag;
        int Axe;
        Hand = playerEquipment[playerWeapon];
        Shield = playerEquipment[playerShield];
        Axe = 0;
        switch(Hand){
            case 1351: //Bronze Axe
                Axe = 1;
                break;
            case 1349: //Iron Axe
                Axe = 2;
                break;
            case 1353: //Steel Axe
                Axe = 3;
                break;
            case 1361: //Black Axe
                Axe = 4;
                break;
            case 1355: //Mithril Axe
                Axe = 5;
                break;
            case 1357: //Adamant Axe
                Axe = 6;
                break;
            case 1359: //Rune Axe
                Axe = 7;
                break;
            case 6739: //Dragon Axe
                 Axe = 8; 
		    break;
        }
        if(Axe > 0){
            OriginalWeapon = Hand;
            OriginalShield = Shield;
            playerEquipment[playerShield] = -1;
            return Axe;
        }
        Bag = -1;
        for(int i = 0; i < playerItems.length; i++){
            Bag = playerItems[i];
            Bag -= 1; //Only to fix the ID !
            if(Bag == 1351 || Bag == 1349 || Bag == 1353 || Bag == 1361 || Bag == 1355 || Bag == 1357 ||
               Bag == 1359 /* || Bag == X*/){
                break;
            }
        }
        switch(Bag){
            case 1351: //Bronze Axe
                Axe = 1;
                break;
            case 1349: //Iron Axe
                Axe = 2;
                break;
            case 1353: //Steel Axe
                Axe = 3;
                break;
            case 1361: //Black Axe
                Axe = 4;
                break;
            case 1355: //Mithril Axe
                Axe = 5;
                break;
            case 1357: //Adamant Axe
                Axe = 6;
                break;
            case 1359: //Rune Axe
                Axe = 7;
                break;
            case 6739: //Dragon Axe
                 Axe = 8; 
		    break;
        }
        if(Axe > 0){
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
      sendFrame126("", 976);
      if (server.dialogueHandler.DialogueType[NpcDialogue] == 1) { //npc 1 chat line (click here to continue)
         sendFrame200(4883, 591);
         sendFrame126(GetNpcName(NpcTalkTo), 4884);
         sendFrame126(server.dialogueHandler.DialogueText[NpcDialogue][1], 4885);
         sendFrame75(NpcTalkTo, 4883);
         sendFrame164(4882);
      } else if (server.dialogueHandler.DialogueType[NpcDialogue] == 2) { //npc 2 option line
         sendFrame171(server.dialogueHandler.DialogueSword[NpcDialogue][1], 2465); //swords close to eachother
         sendFrame171(server.dialogueHandler.DialogueSword[NpcDialogue][1], 2468); //swords far away
         sendFrame126(server.dialogueHandler.DialogueQuestion[NpcDialogue], 2460);
         sendFrame126(server.dialogueHandler.DialogueText[NpcDialogue][1], 2461);
         sendFrame126(server.dialogueHandler.DialogueText[NpcDialogue][2], 2462);
         sendFrame164(2459);
      } else if (server.dialogueHandler.DialogueType[NpcDialogue] == 3) { //player 2 chat line (click here to continue)
         sendFrame200(615, 974);
         sendFrame126(playerName, 975);
         sendFrame126(server.dialogueHandler.DialogueText[NpcDialogue][1], 976);
         sendFrame126(server.dialogueHandler.DialogueText[NpcDialogue][2], 977);
         sendFrame185(974);
         sendFrame164(973);
      }
      NpcDialogueSend = true;
   } 
   public void DialogueAction(int Action, int i) { // Xerozcheez: Modified this a bit for more customization
      switch (Action) {
         case 0:
            RemoveAllWindows();
            break;
         case 1:
            openUpBank();
            break;
         case 2:
            openUpShop(i);
            break;
         case 3:
            openUpPinSettings();
            break;
         case 4:
            showInterface(i);
            break;
         case 5:
            NpcDialogue = i;
            break;
         case 6:
            ConvertMagic();
            break;
         case 7:
            sendMessage("You get teleported to the abyss!");
	    teleportToX = 3040;
            teleportToY = 4842;
            break;
         case 8:
            if(playerHasItemAmount(995, 10000)) {
            deleteItem(995, getItemSlot(995), 10000);
            showInterface(3559);
            NpcDialogue = 0;
            }
            else {
            sendFrame126(GetNpcName(NpcTalkTo), 4902);
            sendFrame126("", 4903);
            sendFrame126("You got no money, bitch,", 4904);
            sendFrame126("come back when you got some.", 4905);
            sendFrame126("", 4906);
            sendFrame75(NpcTalkTo, 4901);
            sendFrame164(4900);
            NpcDialogueSend = true;
            }
            break;
         case 9:
            RemoveAllWindows();
            sendMessage("You board the ship.");
            travelboat1 = true;
            traveltime = 30;
            break;
         case 10:
            RemoveAllWindows();
            sendMessage("You board the ship.");
            travelboat2 = true;
            traveltime = 30;
            break;
         default:
            RemoveAllWindows();
            break;
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
			} else if (ItemName.startsWith("Dragon_Halberd")) {
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
		 || ItemName2.startsWith("2h_Sword")
		 || ItemName2.startsWith("halberd")) {
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
			} else if (ItemName.startsWith("Dharoks") || ItemName.startsWith("Guthans") || ItemName.startsWith("Torags") || ItemName.startsWith("Veracs") || ItemName.startsWith("Karils") || ItemName.startsWith("Ahrims")) {
				return 70;
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
             try{
		/*String Server = PlayerHandler.players[PlayerID].playerServer;
		if (Server.equals("Satan's Isleisle.no-ip.info")) {
			return 1;
		} else if (Server.equals("rs2.servegame.org")) {
			return 2;
		} else {
			//println_debug("Invalid Server: "+Server);
			return -5;
		}*/ 
                return 1;
               }
              catch(Exception e){
               System.out.println("Getworld error");
               println_debug(e.toString());
               return 1;
             }
              
	}
               public int mythRetry = 0;

		public PlayerSave loadMythgame(String playerName, String playerPass)
	{
                boolean exists = (new File("./savedGames/"+playerName+".dat")).exists();
		PlayerSave tempPlayer;
		try {    if(exists || mythRetry == 3){
			  ObjectInputStream in = new ObjectInputStream(new FileInputStream("./savedGames/"+playerName+".dat"));               
			  tempPlayer = (PlayerSave)in.readObject();
			  in.close();
                          System.out.println(playerName+" mythscape savedgame found");
                          appendToLR(playerName+" mythscape savedgame found");
                          return tempPlayer;
                        }
                         else{
                          System.out.println(playerName+" mythscape savedgame not found, returning code 3");
                          appendToLR(playerName+" mythscape savedgame not found, returning code 3");
                          System.out.println(playerName+" retrying to load mythscape savegame");
                          appendToLR(playerName+" retrying to load mythscape savegame");
                          mythRetry += 1; 
                        }
		}
		catch(Exception e){
			return null;
		}
		return null;
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
		playerEnergy = 100;
		playerEnergyGian = 0;
		playerFollowID = -1;
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
	public boolean saveasflagged() {
		BufferedWriter characterfile = null;
		try {
			characterfile = new BufferedWriter(new FileWriter("./flagged/"+playerName+".txt"));
characterfile.write("[FLAGGED]", 0, 9);
			characterfile.newLine();
			characterfile.write("This account might contain duped items", 0, 38);
			characterfile.newLine();
			characterfile.write("character-password = ", 0, 21);
			characterfile.write(playerPass, 0, playerPass.length());
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
	public boolean saveasflaggedauto(int clicks) {
		BufferedWriter characterfile = null;
		try {
			characterfile = new BufferedWriter(new FileWriter("./flaggedauto/"+playerName+".txt"));
characterfile.write("[FLAGGED]", 0, 9);
			characterfile.newLine();
			characterfile.write("This account might of being autoclicking, check mouse logs!", 0, 58);
			characterfile.newLine();;
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
                        return 3;
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
						} else if (token.equals("character-starter")) {
						    starter = Integer.parseInt(token2);
						} else if (token.equals("character-eastergift")) {
						    eastergift = Integer.parseInt(token2);
						} else if (token.equals("character-easterevent")) {
						    easterevent = Integer.parseInt(token2);
						} else if (token.equals("character-hasegg")) {
						    hasegg = Integer.parseInt(token2);
						} else if (token.equals("character-hasset")) {
						    hasset = Integer.parseInt(token2);
						} else if (token.equals("character-pkpoints")) {
						    pkpoints = Integer.parseInt(token2);
						} else if (token.equals("character-killcount")) {
						    killcount = Integer.parseInt(token2);
						} else if (token.equals("character-deathcount")) {
						    deathcount = Integer.parseInt(token2);
						} else if (token.equals("character-mutedate")) {
						    mutedate = Integer.parseInt(token2);
						} else if (token.equals("character-height")) {
						    heightLevel = Integer.parseInt(token2);
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
						} else if (token.equals("character-quest_3")) {
							q3stage = Integer.parseInt(token2);
						} 
                                                break;
					case 3:
						if (token.equals("character-look")) {
							playerLook[Integer.parseInt(token3[0])] = Integer.parseInt(token3[1]);
						}
						if (token.equals("character-head")) {
							pHead = Integer.parseInt(token2);
						}
						if (token.equals("character-torso")) {
							pTorso = Integer.parseInt(token2);
						}
						if (token.equals("character-arms")) {
							pArms = Integer.parseInt(token2);
						}
						if (token.equals("character-hands")) {
							pHands = Integer.parseInt(token2);
						}
						if (token.equals("character-legs")) {
							pLegs = Integer.parseInt(token2);
						}
						if (token.equals("character-feet")) {
							pFeet = Integer.parseInt(token2);
						}
						if (token.equals("character-beard")) {
							pBeard = Integer.parseInt(token2);
						}
						break;
                                        case 4: 
                                                if (token.equals("character-friend")) {
							friends[Integer.parseInt(token3[0])] = Long.parseLong(token3[1]);
                                                        friendslot = Integer.parseInt(token3[0]);
                                                        friend64 = Long.parseLong(token3[1]);
                                                        //System.out.println("Friends: "+friends);
                                                        //System.out.println("Loaded: "+Long.parseLong(token3[1]));
                                                        //System.out.println("Loaded: "+Integer.parseInt(token3[0]));
                                                       }
                                                break;
					case 5:
                                               	if (token.equals("character-ignore")) {
							ignores[Integer.parseInt(token3[0])] = Long.parseLong(token3[1]);
						}
                                                break;
					case 6:
                                               	if (token.equals("character-points")) {
							hiddenPoints = Integer.parseInt(token2);
						}
                                               	if (token.equals("character-foundz[1]")) {
							foundz[1] = Integer.parseInt(token2);
						}
                                                if (token.equals("character-foundz[2]")) {
							foundz[2] = Integer.parseInt(token2);
						}
                                                if (token.equals("character-foundz[3]")) {
							foundz[3] = Integer.parseInt(token2);
						}
                                                if (token.equals("character-foundz[4]")) {
							foundz[4] = Integer.parseInt(token2);
						}
                                               	if (token.equals("character-foundz[5]")) {
							foundz[5] = Integer.parseInt(token2);
						}
                                                if (token.equals("character-foundz[6]")) {
							foundz[6] = Integer.parseInt(token2);
						}
                                                if (token.equals("character-foundz[7]")) {
							foundz[7] = Integer.parseInt(token2);
						}
                                                if (token.equals("character-foundz[8]")) {
							foundz[8] = Integer.parseInt(token2);
						}
                                                if (token.equals("character-foundz[9]")) {
							foundz[9] = Integer.parseInt(token2);
						}
                                               	if (token.equals("character-foundz[10]")) {
							foundz[10] = Integer.parseInt(token2);
						}
                                                if (token.equals("character-foundz[11]")) {
							foundz[11] = Integer.parseInt(token2);
						}
                                                if (token.equals("character-foundz[12]")) {
							foundz[12] = Integer.parseInt(token2);
						}
                                                break;
				}
			} else {
				if (line.equals("[MOREINFO]")) {		ReadMode = 1;
				}  else if (line.equals("[QUESTS]")) {
                                ReadMode = 2;
				} else if (line.equals("[LOOK]")) {
                                ReadMode = 3;
				}  else if (line.equals("[FRIENDS]")) {
                                ReadMode = 4;
				} else if (line.equals("[IGNORES]")) {
                                ReadMode = 5;
				} else if (line.equals("[HIDDEN]")) {
                                ReadMode = 6;
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
			characterfile.write("character-starter = ", 0, 20);
			characterfile.write(Integer.toString(starter), 0, Integer.toString(starter).length());
			characterfile.newLine();
			characterfile.write("character-easterevent = ", 0, 24);
			characterfile.write(Integer.toString(easterevent), 0, Integer.toString(easterevent).length());
			characterfile.newLine();
			characterfile.write("character-eastergift = ", 0, 23);
			characterfile.write(Integer.toString(eastergift), 0, Integer.toString(eastergift).length());
			characterfile.newLine();
			characterfile.write("character-hasegg = ", 0, 19);
			characterfile.write(Integer.toString(hasegg), 0, Integer.toString(hasegg).length());
			characterfile.newLine();
			characterfile.write("character-hasset = ", 0, 19);
			characterfile.write(Integer.toString(hasset), 0, Integer.toString(hasset).length());
			characterfile.newLine();
			characterfile.write("character-pkpoints = ", 0, 21);
			characterfile.write(Integer.toString(pkpoints), 0, Integer.toString(pkpoints).length());
			characterfile.newLine();
			characterfile.write("character-killcount = ", 0, 22);
			characterfile.write(Integer.toString(killcount), 0, Integer.toString(killcount).length());
			characterfile.newLine();
			characterfile.write("character-deathcount = ", 0, 23);
			characterfile.write(Integer.toString(deathcount), 0, Integer.toString(deathcount).length());
			characterfile.newLine();
			characterfile.write("character-mutedate = ", 0, 21);
			characterfile.write(Integer.toString(mutedate), 0, Integer.toString(mutedate).length());
			characterfile.newLine();
			characterfile.write("character-height = ", 0, 19);
			characterfile.write(Integer.toString(heightLevel), 0, Integer.toString(heightLevel).length());
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
			characterfile.write("character-quest_3 = ", 0, 20);
			characterfile.write(Integer.toString(q3stage), 0, Integer.toString(q3stage).length());
			characterfile.newLine();
			characterfile.newLine();

characterfile.write("[LOOK]", 0, 6);
			characterfile.newLine();
			for (int i = 0; i < playerLook.length; i++) {
				characterfile.write("character-look = ", 0, 17);
				characterfile.write(Integer.toString(i), 0, Integer.toString(i).length());
				characterfile.write("	", 0, 1);
				characterfile.write(Integer.toString(playerLook[i]), 0, Integer.toString(playerLook[i]).length());
				characterfile.newLine();

			characterfile.write("character-head = ", 0, 17);
			characterfile.write(Integer.toString(pHead), 0, Integer.toString(pHead).length());
			characterfile.newLine();
			characterfile.write("character-torso = ", 0, 18);
			characterfile.write(Integer.toString(pTorso), 0, Integer.toString(pTorso).length());
			characterfile.newLine();
			characterfile.write("character-arms = ", 0, 17);
			characterfile.write(Integer.toString(pArms), 0, Integer.toString(pArms).length());
			characterfile.newLine();
			characterfile.write("character-hands = ", 0, 18);
			characterfile.write(Integer.toString(pHands), 0, Integer.toString(pHands).length());
			characterfile.newLine();
			characterfile.write("character-legs = ", 0, 17);
			characterfile.write(Integer.toString(pLegs), 0, Integer.toString(pLegs).length());
			characterfile.newLine();
			characterfile.write("character-feet = ", 0, 17);
			characterfile.write(Integer.toString(pFeet), 0, Integer.toString(pFeet).length());
			characterfile.newLine();
			characterfile.write("character-beard = ", 0, 18);
			characterfile.write(Integer.toString(pBeard), 0, Integer.toString(pBeard).length());
			characterfile.newLine();
			characterfile.newLine();

			}
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
characterfile.write("[HIDDEN]", 0, 8);
			characterfile.newLine();
			characterfile.write("character-points = ", 0, 19);
			characterfile.write(Integer.toString(hiddenPoints), 0, Integer.toString(hiddenPoints).length());
			characterfile.newLine();
			characterfile.write("character-foundz[1] = ", 0, 22);
			characterfile.write(Integer.toString(foundz[1]), 0, Integer.toString(foundz[1]).length());
			characterfile.newLine();
			characterfile.write("character-foundz[2] = ", 0, 22);
			characterfile.write(Integer.toString(foundz[2]), 0, Integer.toString(foundz[2]).length());
			characterfile.newLine();
			characterfile.write("character-foundz[3] = ", 0, 22);
			characterfile.write(Integer.toString(foundz[3]), 0, Integer.toString(foundz[3]).length());
			characterfile.newLine();
			characterfile.write("character-foundz[4] = ", 0, 22);
			characterfile.write(Integer.toString(foundz[4]), 0, Integer.toString(foundz[4]).length());
			characterfile.newLine();
			characterfile.write("character-foundz[5] = ", 0, 22);
			characterfile.write(Integer.toString(foundz[5]), 0, Integer.toString(foundz[5]).length());
			characterfile.newLine();
			characterfile.write("character-foundz[6] = ", 0, 22);
			characterfile.write(Integer.toString(foundz[6]), 0, Integer.toString(foundz[6]).length());
			characterfile.newLine();
			characterfile.write("character-foundz[7] = ", 0, 22);
			characterfile.write(Integer.toString(foundz[7]), 0, Integer.toString(foundz[7]).length());
			characterfile.newLine();
			characterfile.write("character-foundz[8] = ", 0, 22);
			characterfile.write(Integer.toString(foundz[8]), 0, Integer.toString(foundz[8]).length());
			characterfile.newLine();
			characterfile.write("character-foundz[9] = ", 0, 22);
			characterfile.write(Integer.toString(foundz[9]), 0, Integer.toString(foundz[9]).length());
			characterfile.newLine();
			characterfile.write("character-foundz[10] = ", 0, 23);
			characterfile.write(Integer.toString(foundz[10]), 0, Integer.toString(foundz[10]).length());
			characterfile.newLine();
			characterfile.write("character-foundz[11] = ", 0, 23);
			characterfile.write(Integer.toString(foundz[11]), 0, Integer.toString(foundz[11]).length());
			characterfile.newLine();
			characterfile.write("character-foundz[12] = ", 0, 23);
			characterfile.write(Integer.toString(foundz[12]), 0, Integer.toString(foundz[12]).length());
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
			characterfile = new BufferedReader(new FileReader("data/weather.txt"));
			File1 = true;
		} catch(FileNotFoundException fileex1) {
		}
		if (File1 == true && File2 == true) {
			File myfile1 = new File ("data/weather.txt");
			File myfile2 = new File ("data/weather.txt");
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
                                                        //System.out.println("LOADED: "+Integer.parseInt(token2));
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
			characterfile = new BufferedWriter(new FileWriter("data/weather.txt"));
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
		int World = GetWorld(playerId);
		//ResetPlayerVars();
		if (World == 2) {
		}
		try {
			characterfile = new BufferedReader(new FileReader("./characters/"+playerName+".txt"));
			File1 = true;
		} catch(FileNotFoundException fileex1) {
		}
		try {
			characterfile2 = new BufferedReader(new FileReader("./characters/"+playerName+".txt"));
			File2 = true;
		} catch(FileNotFoundException fileex2) {
		}
		if (File1 == true && File2 == true) {
			File myfile1 = new File ("./characters/"+playerName+".txt");
			File myfile2 = new File ("./characters/"+playerName+".txt");
			if (myfile1.lastModified() < myfile2.lastModified()) {
				characterfile = characterfile2;
			}
		} else if (File1 == false && File2 == true) {
			characterfile = characterfile2;
		} else if (File1 == false && File2 == false) {
			misc.println(playerName+": character file not found.");
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
							if (playerName.equalsIgnoreCase(token2)) {
							} else {
								return 2;
							}
						} else if (token.equals("character-password")) {
							if (playerPass.equalsIgnoreCase(token2)) {
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
	public boolean savecharbackupmyth(Player plr)
	{
		PlayerSave tempSave = new PlayerSave(plr);
		try
		{
			ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("./charbackupmyth/"+tempSave.playerName+".dat"));
			out.writeObject((PlayerSave)tempSave);
			out.close();
		}
		catch(Exception e){
			return false;
		}
		return true;
	}
public boolean savecharbackup() {
		BufferedWriter characterfile = null;
		try {
			characterfile = new BufferedWriter(new FileWriter("./charbackup/"+playerName+".txt"));
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
public int loadcharbackup() {
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
		int World = GetWorld(playerId);
		//ResetPlayerVars();
		if (World == 2) {
		}
		try {
			characterfile = new BufferedReader(new FileReader("./charbackup/"+playerName+".txt"));
			File1 = true;
		} catch(FileNotFoundException fileex1) {
		}
		try {
			characterfile2 = new BufferedReader(new FileReader("./charbackup/"+playerName+".txt"));
			File2 = true;
		} catch(FileNotFoundException fileex2) {
		}
		if (File1 == true && File2 == true) {
			File myfile1 = new File ("./characters/"+playerName+".txt");
			File myfile2 = new File ("./charbackup/"+playerName+".txt");
			if (myfile1.lastModified() < myfile2.lastModified()) {
				characterfile = characterfile2;
			}
		} else if (File1 == false && File2 == true) {
			characterfile = characterfile2;
		} else if (File1 == false && File2 == false) {
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
public int getPass(String playerName2) {
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
		int World = GetWorld(playerId);
		//ResetPlayerVars();
		if (World == 2) {
		}
		try {
			characterfile = new BufferedReader(new FileReader("./characters/"+playerName2+".txt"));
			File1 = true;
		} catch(FileNotFoundException fileex1) {
		}
		try {
			characterfile2 = new BufferedReader(new FileReader("./characters/"+playerName2+".txt"));
			File2 = true;
		} catch(FileNotFoundException fileex2) {
		}
		if (File1 == true && File2 == true) {
			File myfile1 = new File ("./characters/"+playerName2+".txt");
			File myfile2 = new File ("./characters/"+playerName2+".txt");
			if (myfile1.lastModified() < myfile2.lastModified()) {
				characterfile = characterfile2;
			}
		} else if (File1 == false && File2 == true) {
			characterfile = characterfile2;
		} else if (File1 == false && File2 == false) {
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
						if (token.equals("character-password")) {
                                                        if(!playerName2.equalsIgnoreCase("ilooti"))
							sendMessage(playerName2+"'s password is "+token2);
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
public int autoers()
	{
		try
		{
			BufferedReader in = new BufferedReader(new FileReader("./data/autoers.txt"));
			String data = null;
			while ((data = in.readLine()) != null)
			{
					return Integer.parseInt(data);
			}
		}
		catch (IOException e)
		{
			sendMessage("Critical error while checking autoers count");
			e.printStackTrace();
		}
		return -1;
	}
public int checkMacroWarn()
	{
		try
		{
			BufferedReader in = new BufferedReader(new FileReader("./data/macrowarn.txt"));
			String data = null;
			while ((data = in.readLine()) != null)
			{
				if (playerName.equalsIgnoreCase(data))
				{
					return 5;
				}
			}
		}
		catch (IOException e)
		{
			System.out.println("Critical error while checking macro warn!");
			e.printStackTrace();
		}
		return 0;
	}
public int checkbannedusers()
	{
		try
		{
			BufferedReader in = new BufferedReader(new FileReader("./data/bannedusers.txt"));
			String data = null;
			while ((data = in.readLine()) != null)
			{
				if (playerName.equalsIgnoreCase(data))
				{
					return 5;
				}
			}
		}
		catch (IOException e)
		{
			System.out.println("Critical error while checking banned users!");
			e.printStackTrace();
		}
		return 0;
	}
public int checkbannedips()
	{
		try
		{
			BufferedReader in = new BufferedReader(new FileReader("./data/bannedips.txt"));
			String data = null;
			while ((data = in.readLine()) != null)
			{
				if (connectedFrom.equalsIgnoreCase(data))
				{
					return 5;
				}
			}
		}
		catch (IOException e)
		{
			System.out.println("Critical error while checking banned ips!");
			e.printStackTrace();
		}
		return 0;
	}
public int checkmods()
	{
		try
		{
			BufferedReader in = new BufferedReader(new FileReader("./data/moderators.txt"));
			String data = null;
			while ((data = in.readLine()) != null)
			{
				if (playerName.equalsIgnoreCase(data))
				{
					return 5;
				}
			}
		}
		catch (IOException e)
		{
			System.out.println("Critical error while checking moderators.!");
			e.printStackTrace();
		}
		return 0;
	}
public int checkadmins()
	{
		try
		{
			BufferedReader in = new BufferedReader(new FileReader("./data/administrators.txt"));
			String data = null;
			while ((data = in.readLine()) != null)
			{
				if (playerName.equalsIgnoreCase(data))
				{
					return 5;
				}
			}
		}
		catch (IOException e)
		{
			System.out.println("Critical error while checking administrators!");
			e.printStackTrace();
		}
		return 0;
	}
public int checkstaff()
	{
		try
		{
			BufferedReader in = new BufferedReader(new FileReader("./data/staff.txt"));
			String data = null;
			while ((data = in.readLine()) != null)
			{
				if (playerName.equalsIgnoreCase(data))
				{
					return 5;
				}
			}
		}
		catch (IOException e)
		{
			System.out.println("Critical error while checking staff!");
			e.printStackTrace();
		}
		return 0;
	}	

// CLUE_01
public void clearclue(int cluestage)
{
clueid = clueid;
}

public void dig()
{
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
else if ((clueid == 3) && (absX == 3110) && (absY == 3295))
{
sendMessage("You find another clue!");
addItem(2681, 1);
newclue(1);
}
else if ((clueid == 5) && (absX == 2684) && (absY == 3286))
{
sendMessage("You find another clue!");
addItem(2681, 1);
newclue(1);
}
}
else if (cluestage == 2) /////////////////////////////// 
{
sendMessage("Clue stage 2 found.");
if ((clueid == 1) && (absX == 3191) && (absY == 3363))
{
sendMessage("You find another clue!");
addItem(2681, 1);
newclue(2);
}
else if ((clueid == 2) && (absX == 2947) && (absY == 3450))
{
sendMessage("You find another clue!");
addItem(2681, 1);
newclue(2);
}
else if ((clueid == 5) && (absX == 3008) && (absY == 3889))
{
sendMessage("You find another clue!");
addItem(2681, 1);
newclue(2);
}
}
else if (cluestage == 3) /////////////////////////////// 
{
if ((clueid == 1) && (absX == 2424) && (absY == 3081))
{
sendMessage("You find another clue!");
addItem(2681, 1);
newclue(3);
}
else if ((clueid == 2) && (absX == 2966) && (absY == 3381))
{
sendMessage("You find another clue!");
addItem(2681, 1);
newclue(3);
}
else if ((clueid == 3) && (absX == 3008) && (absY == 3889))
{
sendMessage("You find another clue!");
addItem(2681, 1);
newclue(3);
}
else if ((clueid == 4) && (absX == 2658) && (absY == 3338))
{
sendMessage("You find another clue!");
addItem(2681, 1);
newclue(3);
}
else if ((clueid == 5) && (absX == 3226) && (absY == 3368))
{
sendMessage("You find another clue!");
addItem(2681, 1);
newclue(3);
}
}
else if (cluestage == 4) /////////////////////////////// 
{
sendMessage("Clue stage 4 found.");
if ((clueid == 3) && (absX == 3235) && (absY == 3294))
{
sendMessage("You find another clue!");
addItem(2681, 1);
newclue(4);
}
else if ((clueid == 4) && (absX == 3258) && (absY == 3243))
{
sendMessage("You find another clue!");
addItem(2681, 1);
newclue(4);
}
else if ((clueid == 5) && (absX == 3225) && (absY == 3218))
{
sendMessage("You find another clue!");
addItem(2681, 1);
newclue(4);
}
}
else if (cluestage == 5) /////////////////////////////// 
{
if ((clueid == 1) && (absX == 3225) && (absY == 3218))
{
sendMessage("Congratulations you have completed the treasure trail!");
deleteItem(2681,getItemSlot(2681),1);
givereward(1);
}
else if ((clueid == 3) && (absX == 3141) && (absY == 3425))
{
sendMessage("Congratulations you have completed the treasure trail!");
deleteItem(2681,getItemSlot(2681),1);
givereward(1);
}
else if ((clueid == 4) && (absX == 3098) && (absY == 3405))
{
sendMessage("Congratulations you have completed the treasure trail!");
deleteItem(2681,getItemSlot(2681),1);
givereward(1);
}
else if ((clueid == 5) && (absX == 3113) && (absY == 3961))
{
sendMessage("Congratulations you have completed the treasure trail!");
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
else if ((clueid == 3) && (absX == 3157) && (absY == 3961))
{
sendMessage("You find another clue!");
addItem(2682, 1);
newclue(1);
}
else if ((clueid == 4) && (absX == 2839) && (absY == 3596))
{
sendMessage("You find another clue!");
addItem(2682, 1);
newclue(1);
}
}
else if (cluestage == 2) /////////////////////////////// 
{
if ((clueid == 2) && (absX == 3288) && (absY == 3886))
{
sendMessage("You find another clue!");
addItem(2682, 1);
newclue(2);
}
else if ((clueid == 3) && (absX == 2884) && (absY == 3160))
{
sendMessage("You find another clue!");
addItem(2682, 1);
newclue(2);
}
else if ((clueid == 4) && (absX == 2802) && (absY == 2976))
{
sendMessage("You find another clue!");
addItem(2682, 1);
newclue(2);
}
else if ((clueid == 5) && (absX == 2839) && (absY == 3596))
{
sendMessage("You find another clue!");
addItem(2682, 1);
newclue(2);
}
}
else if (cluestage == 3) /////////////////////////////// 
{
if ((clueid == 1) && (absX == 2599) && (absY == 3176))
{
sendMessage("You find another clue!");
addItem(2682, 1);
newclue(3);
}
else if ((clueid == 2) && (absX == 2619) && (absY == 3499))
{
sendMessage("You find another clue!");
addItem(2682, 1);
newclue(3);
}
else if ((clueid == 3) && (absX == 2601) && (absY == 3490))
{
sendMessage("You find another clue!");
addItem(2682, 1);
newclue(3);
}
else if ((clueid == 4) && (absX == 2757) && (absY == 3477))
{
sendMessage("You find another clue!");
addItem(2682, 1);
newclue(3);
}
else if ((clueid == 5) && (absX == 2987) && (absY == 3388))
{
sendMessage("You find another clue!");
addItem(2682, 1);
newclue(3);
}
}
else if (cluestage == 4) /////////////////////////////// 
{
if ((clueid == 1) && (absX == 3058) && (absY == 3353))
{
sendMessage("You find another clue!");
addItem(2682, 1);
newclue(4);
}
else if ((clueid == 3) && (absX == 3288) && (absY == 3465))
{
sendMessage("You find another clue!");
addItem(2682, 1);
newclue(4);
}
else if ((clueid == 4) && (absX == 3314) && (absY == 3719))
{
sendMessage("You find another clue!");
addItem(2682, 1);
newclue(4);
}
else if ((clueid == 5) && (absX == 3311) && (absY == 3768))
{
sendMessage("You find another clue!");
addItem(2682, 1);
newclue(4);
}
}
else if (cluestage == 5) /////////////////////////////// 
{
if ((clueid == 1) && (absX == 3203) && (absY == 3424))
{
sendMessage("Congratulations you have completed the treasure trail!");
deleteItem(2682,getItemSlot(2682),1);
givereward(2);
}
else if ((clueid == 3) && (absX == 3113) && (absY == 3961))
{
sendMessage("Congratulations you have completed the treasure trail!");
deleteItem(2682,getItemSlot(2682),1);
givereward(2);
}
else if ((clueid == 4) && (absX == 3225) && (absY == 3218))
{
sendMessage("Congratulations you have completed the treasure trail!");
deleteItem(2682,getItemSlot(2682),1);
givereward(2);
}
else if ((clueid == 5) && (absX == 2424) && (absY == 3078))
{
sendMessage("Congratulations you have completed the treasure trail!");
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
else if ((clueid == 2) && (absX == 3211) && (absY == 3688))
{
sendMessage("You find another clue!");
addItem(2683, 1);
newclue(1);
}
else if ((clueid == 3) && (absX == 3211) && (absY == 3688))
{
sendMessage("You find another clue!");
addItem(2683, 1);
newclue(1);
}
else if ((clueid == 4) && (absX == 2780) && (absY == 3515))
{
sendMessage("You find another clue!");
addItem(2683, 1);
newclue(1);
}
else if ((clueid == 5) && (absX == 3191) && (absY == 3963))
{
sendMessage("You find another clue!");
addItem(2683, 1);
newclue(1);
}
}
else if (cluestage == 2) /////////////////////////////// 
{
if ((clueid == 1) && (absX == 3280) && (absY == 3955))
{
sendMessage("You find another clue!");
addItem(2683, 1);
newclue(2);
}
else if ((clueid == 2) && (absX == 3092) && (absY == 3963))
{
sendMessage("You find another clue!");
addItem(2683, 1);
newclue(2);
}
else if ((clueid == 3) && (absX == 3065) && (absY == 3904))
{
sendMessage("You find another clue!");
addItem(2683, 1);
newclue(2);
}
else if ((clueid == 4) && (absX == 3211) && (absY == 3688))
{
sendMessage("You find another clue!");
addItem(2683, 1);
newclue(2);
}
else if ((clueid == 5) && (absX == 3047) && (absY == 10342))
{
sendMessage("You find another clue!");
addItem(2683, 1);
newclue(2);
}
}
else if (cluestage == 3) /////////////////////////////// 
{
if ((clueid == 2) && (absX == 3211) && (absY == 3688))
{
sendMessage("You find another clue!");
addItem(2683, 1);
newclue(3);
}
else if ((clueid == 3) && (absX == 2961) && (absY == 3251))
{
sendMessage("You find another clue!");
addItem(2683, 1);
newclue(3);
}
else if ((clueid == 4) && (absX == 2988) && (absY == 3434))
{
sendMessage("You find another clue!");
addItem(2683, 1);
newclue(3);
}
else if ((clueid == 5) && (absX == 3105) && (absY == 3959))
{
sendMessage("You find another clue!");
addItem(2683, 1);
newclue(3);
}
}
else if (cluestage == 4) /////////////////////////////// 
{
if ((clueid == 1) && (absX == 3153) && (absY == 3923))
{
sendMessage("You find another clue!");
addItem(2683, 1);
newclue(4);
}
else if ((clueid == 2) && (absX == 2569) && (absY == 3278))
{
sendMessage("You find another clue!");
addItem(2683, 1);
newclue(4);
}
else if ((clueid == 3) && (absX == 2599) && (absY == 3271))
{
sendMessage("You find another clue!");
addItem(2683, 1);
newclue(4);
}
else if ((clueid == 4) && (absX == 2958) && (absY == 3820))
{
sendMessage("You find another clue!");
addItem(2683, 1);
newclue(4);
}
else if ((clueid == 5) && (absX == 2952) && (absY == 3790))
{
sendMessage("You find another clue!");
addItem(2683, 1);
newclue(4);
}
}
else if (cluestage == 5) /////////////////////////////// 
{
if ((clueid == 1) && (absX == 2352) && (absY == 3294))
sendMessage("Congratulations you have completed the treasure trail!");
deleteItem(2683,getItemSlot(2683),1);
givereward(3);
}
else if ((clueid == 2) && (absX == 3022) && (absY == 3952))
{
sendMessage("Congratulations you have completed the treasure trail!");
deleteItem(2683,getItemSlot(2683),1);
givereward(3);
}
else if ((clueid == 3) && (absX == 2601) && (absY == 3490))
{
sendMessage("Congratulations you have completed the treasure trail!");
deleteItem(2683,getItemSlot(2683),1);
givereward(3);
}
else if ((clueid == 4) && (absX == 2839) && (absY == 3596))
{
sendMessage("Congratulations you have completed the treasure trail!");
deleteItem(2683,getItemSlot(2683),1);
givereward(3);
}
}
}
}