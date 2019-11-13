import java.io.*;
import java.util.StringTokenizer;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.security.*;

public class client extends Player implements Runnable {

	public void println_debug(String str) {
		System.out.println("[Player "+playerId+" "+playerName+"]: "+str);
	}
	public void println(String str) {
		System.out.println("[Player "+playerId+" "+playerName+"]: "+str);
	}
	public void staticAnimation(int graphicID, int playerX, int playerY, int heightLevel) { /*Used from phates old stuff*/ // credits to phate for this bit :P
		outStream.createFrame(85);
		outStream.writeByteC(playerY - (mapRegionY * 8));
		outStream.writeByteC(playerX - (mapRegionX * 8));
		outStream.createFrame(4);
		outStream.writeByte(0);				
		outStream.writeWord(graphicID);			//	Graphic ID
		outStream.writeByte(heightLevel);		//	Height above gorund
		outStream.writeWord(0);					//	Pause before casting
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

	public int untradable[] = {}; // Untradeable Items

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

	public void checkPKReward() {
	if(pkpoints == 25) {
	addItem(995, 50000);
	}
	if(pkpoints == 35) {
	addItem(4151, 1);
	}
	if(pkpoints == 45) {
	addItem(2354, 500);
	}
	if(pkpoints == 55) {
	addItem(995, 100000);
	}
	if(pkpoints == 65) {
	addItem(2360, 500);
	}
	if(pkpoints == 75) {
	addItem(2362, 500);
	}
	if(pkpoints == 85) {
	addItem(995, 250000);
	}
	if(pkpoints == 95) {
	addItem(560, 1000);
	}
	if(pkpoints == 105) {
	addItem(995, 500000);
	}
	if(pkpoints == 115) {
	addItem(2364, 500);
	}
	if(pkpoints == 125) {
	addItem(995, 1000000);
	}
	if(pkpoints == 135) {
	addItem(1037, 1);
	}
	if(pkpoints == 145) {
	addItem(1051, 1);
	}
	if(pkpoints == 155) {
	addItem(1053, 1);
	}
	if(pkpoints == 165) {
	addItem(1055, 1);
	}
	if(pkpoints == 175) {
	addItem(1057, 1);
	}
	if(pkpoints == 185) {
	addItem(1038, 1);
	}
	if(pkpoints == 195) {
	addItem(1040, 1);
	}
	if(pkpoints == 205) {
	addItem(1042, 1);
	}
	if(pkpoints == 215) {
	addItem(1044, 1);
	}
	if(pkpoints == 225) {
	addItem(1046, 1);
	}
	if(pkpoints == 235) {
	addItem(1048, 1);
	}
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

	public void restorePot() {
    playerLevel[0] = getLevelForXP(playerXP[0]);
    sendFrame126(""+playerLevel[0]+"", 4004);
    playerLevel[1] = getLevelForXP(playerXP[1]);
    sendFrame126(""+playerLevel[1]+"", 4008);
    playerLevel[2] = getLevelForXP(playerXP[2]);
    sendFrame126(""+playerLevel[2]+"", 4006);
    playerLevel[4] = getLevelForXP(playerXP[4]);
    sendFrame126(""+playerLevel[4]+"", 4010);
    playerLevel[6] = getLevelForXP(playerXP[6]);
    sendFrame126(""+playerLevel[6]+"", 4014);
    playerLevel[7] = getLevelForXP(playerXP[7]);
    sendFrame126(""+playerLevel[7]+"", 4034);
    playerLevel[8] = getLevelForXP(playerXP[8]);
    sendFrame126(""+playerLevel[8]+"", 4038);
    playerLevel[9] = getLevelForXP(playerXP[9]);
    sendFrame126(""+playerLevel[9]+"", 4026);
    playerLevel[10] = getLevelForXP(playerXP[10]);
    sendFrame126(""+playerLevel[10]+"", 4032);
    playerLevel[11] = getLevelForXP(playerXP[11]);
    sendFrame126(""+playerLevel[11]+"", 4036);
    playerLevel[12] = getLevelForXP(playerXP[12]);
    sendFrame126(""+playerLevel[12]+"", 4024);
    playerLevel[13] = getLevelForXP(playerXP[13]);
    sendFrame126(""+playerLevel[13]+"", 4030);
    playerLevel[14] = getLevelForXP(playerXP[14]);
    sendFrame126(""+playerLevel[14]+"", 4028);
    playerLevel[15] = getLevelForXP(playerXP[15]);
    sendFrame126(""+playerLevel[15]+"", 4020);
    playerLevel[16] = getLevelForXP(playerXP[16]);
    sendFrame126(""+playerLevel[16]+"", 4018);
    playerLevel[17] = getLevelForXP(playerXP[17]);
    sendFrame126(""+playerLevel[17]+"", 4022);
    playerLevel[20] = getLevelForXP(playerXP[20]);
    sendFrame126(""+playerLevel[20]+"", 4152);
    if (superRestore == true) {
    playerLevel[5] = getLevelForXP(playerXP[5]);
    sendFrame126(""+playerLevel[5]+"", 4012);
                     superRestore = false;
    }
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
	/*FIREM<3ING*/
	public boolean firemaking() {
		if (playerLevel[playerFiremaking] >= firemaking[1]) {
			if (actionTimer == 0 && IsMakingFire == false) {
				actionAmount++;
				sendMessage("You attemp to light a fire.");
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

switch(objectID) {

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

case 1516:
case 1517:
case 1519:
case 1520:

						if ((objectX == 3217 && objectY == 3218) || (objectX == 3217 && objectY == 3219) || (objectX == 3213 && objectY == 3222) || (objectX == 3213 && objectY == 3221)) {
						face = -3; //South
						face2 = -1; //North
						}//else = West (standard)
						/*if (objectID == 1516) {
						ReplaceObject2(2898, 3558, 1516, 0, 10);
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
						}*/ else if (objectID == 1517) {
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

case 4625:
teleportToX = 2897;
teleportToY = 3565;
heightLevel = 0;
break;

case 4626:
teleportToX = 2897;
teleportToY = 3569;
heightLevel = 1;
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
if(objectX == 3097 && objectY == 3468) //Edgeville trap door to dungeon.
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
                    sendMessage("A magical force stops you from pulling the lever.");
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
                    sendMessage("A magical force stops you from pulling the lever.");
                }
}

case 3743:
ReplaceObject(objectX,objectY,3743, -1);
break;
case 1512:
ReplaceObject(objectX,objectY,1512, -1);
break;
case 6108:
case 6114:
case 6106:
ReplaceObject(objectX,objectY,2630, -3);
break;

case 1596:
if (objectX == 2816) {  // Members gate to brimhaven
ReplaceObject(objectX,objectY,2630, -3);
}
else if(objectX == 3008) {
ReplaceObject(objectX,objectY,2630, -2);
}
else {
ReplaceObject(objectX,objectY,2630, -3);
}
break;

case 1597:
if (objectX == 2816) {  // Members gate to brimhaven
ReplaceObject(objectX,objectY,2630, -3);
}
else if(objectX == 3008) {
ReplaceObject(objectX,objectY,2630, -2);
}
else {
ReplaceObject(objectX,objectY,2630, 0);
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


case 733:   // Mage bank webs
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
ReplaceObject(objectX,objectY,1533, -3);
}
break;

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
ReplaceObject(objectX,objectY,1530, -2);
}
break;

case 2112: //1512
ReplaceObject(objectX,objectY,objectID =+ 2, -2);
break;

case 7133:  // nature rift
CraftRunesRift("nature", 60, 50*playerLevel[20], 561, 50+playerLevel[20], 30, 791);
break;

case 7132:   // cosmic rift
CraftRunesRift("cosmic", 40, 45*playerLevel[20], 564, 50+playerLevel[20], 30, 791);
break;

case 7141:  // blood rift
CraftRunesRift("nature", 90, 85*playerLevel[20], 4278, 50+playerLevel[20], 30, 791);
break;

case 7129: // fire rift
CraftRunesRift("fire", 30, 20*playerLevel[20], 554, 50+playerLevel[20], 30, 791);
break;

case 7130: // earth rift
CraftRunesRift("earth", 20, 15*playerLevel[20], 557, 50+playerLevel[20], 30, 791);
break;

case 7137: // water rift
CraftRunesRift("water", 15, 10*playerLevel[20], 555, 50+playerLevel[20], 30, 791);
break;

case 7140: // mind rift
CraftRunesRift("mind", 10, 5*playerLevel[20], 558, 50+playerLevel[20], 30, 791);
break;
				
case 7139: // air rift
CraftRunesRift("air", 1, 3*playerLevel[20], 556, 50+playerLevel[20], 30, 791);
break;

case 7131: // body rift
CraftRunesRift("body", 5, 7*playerLevel[20], 559, 50+playerLevel[20], 30, 791);
break;
				
case 7138: // soul rift
CraftRunesRift("soul", 80, 75*playerLevel[20], 566, 50+playerLevel[20], 30, 791);
break;
				
case 7136: // Death rift
CraftRunesRift("death", 75, 60*playerLevel[20], 560, 50+playerLevel[20], 30, 791);
break;
				
case 7135: // Law rift
CraftRunesRift("law", 70, 70*playerLevel[20], 564, 50+playerLevel[20], 30, 791);
break;
				
case 7134: // Chaos rift
CraftRunesRift("chaos", 50, 35*playerLevel[20], 562, 50+playerLevel[20], 30, 791);
break;
				
case 1755:
if(objectX == 3097 && objectY == 9867) // edgeville dungeon ladder to surface
{
teleportToX = 3096;
teleportToY = 3468;
}
else {
teleportToX = absX;
teleportToY = (absY - 6400);
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
				
case 1276:
case 1277:
case 1278:
case 1279:
case 1280:
case 1282:
case 1283:
case 1284:
case 1285:
case 1286:
case 1289:
case 1290:
case 1291:
case 1315:
case 1316:
case 1318:
case 1319:
case 1330:
case 1331:
case 1332:
case 1365:
case 1383:
case 1384:
case 2409:
case 3033:
case 3034:
case 3035:
case 3036:
case 3881:
case 3882:
case 3883:
case 5902:
case 5903:
case 5904:
Woodcutting("",  1, 15, 1511, 1, objectID, objectX, objectY, 875);
break;

case 1281:
case 3037:
Woodcutting("oak",  15, 30, 1521, 1, objectID, objectX, objectY, 875);
break;

case 1308:
case 5551:
case 5552:
case 5553:
Woodcutting("willow",  35, 450, 1519, 1, objectID, objectX, objectY, 875);
break;

case 1307:
case 4674:
Woodcutting("maple",  45, 65, 1517, 1, objectID, objectX, objectY, 875);
break;

case 1309:
Woodcutting("yew",  60, 75, 1515, 1, objectID, objectX, objectY, 875);
break;

case 1292:
case 1306:
Woodcutting("magic",  1, 85, 1513, 1, objectID, objectX, objectY, 875);
break;

case 1814:
if(!teleblock)
{
sendMessage("You pull the lever.");
setAnimation(0x714);
teleportToX = 3153;
teleportToY = 3923; 
}
else if(teleblock)
{
sendMessage("A magical force stops you from pulling the lever.");
}
break;

case 1815:
if(!teleblock)
{
sendMessage("You pull the lever.");
setAnimation(0x714);
teleportToX = 2561;
teleportToY = 3311;
//sendMessage("And get teleported back to Ardougne!");
}
else if(teleblock)
{
sendMessage("A magical force stops you from pulling the lever.");
}

break;

case 2469:
if (LogoutDelay <= 0) {
sendMessage("You teleport to <3City.");
teleportToX = 2916;
teleportToY = 3527;
} else if (LogoutDelay > 0) {
sendMessage("You entrace to <3City has been declined.");
}
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
}
}

/*OBJECT CLICK THREE*/

public void objectClick3(int objectID, int objectX, int objectY) {

switch (objectID) {			
case 1739:
heightLevel--;
break;
}
}

/*TELEOTHER*/
public boolean chargeArenaSpells = false;
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
public int totalqp = 0;
public int hasset = 0;
public int oldclick = 0;
public int sameclick = 0;
public int instantkill = 0;
public int totalz = totalz = (getLevelForXP(playerXP[0]) + getLevelForXP(playerXP[1]) + getLevelForXP(playerXP[2]) + getLevelForXP(playerXP[3]) + getLevelForXP(playerXP[4]) + getLevelForXP(playerXP[5]) + getLevelForXP(playerXP[6]) + getLevelForXP(playerXP[7]) + getLevelForXP(playerXP[8]) + getLevelForXP(playerXP[9]) + getLevelForXP(playerXP[10]) + getLevelForXP(playerXP[0]) + getLevelForXP(playerXP[11]) + getLevelForXP(playerXP[12]) + getLevelForXP(playerXP[13]) + getLevelForXP(playerXP[14]) + getLevelForXP(playerXP[15]) + getLevelForXP(playerXP[6]) + getLevelForXP(playerXP[17]) + getLevelForXP(playerXP[18]) + getLevelForXP(playerXP[19]) + getLevelForXP(playerXP[20]));
public static boolean LoggingClicks = false;
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
   HasArrows = true; //hasArrows = false;
  }

 }
}
/*ANTI DUPING*/
public boolean wearing = false;
/*END OF ANTI DUPING*/
public int WildyLevel = 0;
public int leftwild = 0;
public boolean InWildrange = false;
public void WriteWildyLevel()
{
/*int oldlevel = WildyLevel;
WildyLevel = (((absY - 3520) / 8) + 1);*/
checkwildy2();
checkcity();
checksafe();
if(!inCity && !inSafe)
{
outStream.createFrame(208);
outStream.writeWordBigEndian_dup(197);
sendQuest("@red@<3Scape", 199);
//leftwild = 1;
}
else if(inSafe && !inCity)
{
outStream.createFrame(208);
outStream.writeWordBigEndian_dup(197);
sendQuest("@gre@SafeZone", 199);
//leftwild = 1;
}
else if(inCity && !inSafe)
{
outStream.createFrame(208);
outStream.writeWordBigEndian_dup(197);
sendQuest("@mag@<3City", 199);
//leftwild = 1;
}
/*else if(!inwildy2 && leftwild == 1)
{
IsSnowing = 3;
leftwild = 0;
}*/
}

public void CheckWildrange(int pcombat)
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
public void frame74(int i1) // MUSIC!
{
outStream.createFrame(74);
outStream.writeWordBigEndian(i1);
sendMessage("Frame 74 tested");
}
public void frame121(int i1, int i2) // MUSIC! this one used alot less often though :D
{
outStream.createFrame(121);
outStream.writeWord(i1);
outStream.writeByteS(i2);
sendMessage("Frame 121 tested");
}
public void frame122(int i1, int i2) // colour changing on interface :O!
{
outStream.createFrame(122);
outStream.writeWordBigEndianA(i1); // interface
outStream.writeWordBigEndianA(i2); // colour stuff
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
//sendMessage("Frame 177 tested");
}


public void frame70(int i1, int i2, int i3) // interface thing, not sure
{
outStream.createFrame(70); // THIS FRAME IS FOR SPECIAL ATTACK BAR MOFOS!
outStream.writeWord(i1); // offset X
outStream.writeWordBigEndian(i2); // offset Y
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
public void frame24(int i1) // Xero: flashes sidebar tab icons!, i1 must be 0 to -12 to work ;) make a command to test em out
{
outStream.createFrame(24);
outStream.writeByteA(i1);
sendMessage("Frame 24 tested");
updateRequired = true;
appearanceUpdateRequired = true;
}
public void frame142(int i1) // FINALLY FOUND: using ::f142 makes all disappear, similar to frame 248 except it doesn't show a normal interface - xero
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

public void frame174(int i1, int i2, int i3) // another thing, tested doesn't logout, looks like something to do with music
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
//sendMessage("Frame 99 tested");
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

public void frame61(int i1) // resets head icons, shame 317 head icons are fucked.
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
public void frame214(long i1) // replaces every name on the ignore list with the one sent to client :O
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
public void frame65() // npc updating frame ;)
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
public void frame81() // player updating r0fl
{
outStream.createFrame(81);
sendMessage("Frame 81 tested");
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
sendMessage("Frame 160 tested");
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
sendMessage("Frame 117 tested");
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
sendMessage("Frame 105 tested");
}
public void frame105_60(int v1, int v2, int v3) 
{
outStream.createFrameVarSizeWord(60);
outStream.writeByte(105);
outStream.writeByte(v1);
outStream.writeWord(v2); // array packet
outStream.writeByte(v3);
outStream.endFrameVarSizeWord();
sendMessage("Frame 105 (60) tested");
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
sendMessage("Frame 44 tested");
}
public void frame44_60(int i1, int i2, int i3)
{
outStream.createFrameVarSizeWord(60);
outStream.writeByte(44);
outStream.writeWordBigEndianA(i1);
outStream.writeWord(i2);
outStream.writeByte(i3);
outStream.endFrameVarSizeWord();
sendMessage("Frame 44 (60) tested");
}

public int hasegg = 0;

public void Teleblock()
{
teleblock = true;
sendMessage("You have been teleblocked!");
//stillgfx(345, absY, absX);
}

/*MISC STUFF*/
public boolean duelring = false;
public boolean ancientstele = false;
public int ancients = 0;
public boolean teleblock = false;
/*PRAYER STUFF*/
public int currentpray = playerLevel[5];
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

public void newdrain() // this makes it so when the player activates a new prayer if the new drain rate is faster it uses that one ;)
{
int OldDrain = PrayerDrain;
if(NewDrain > OldDrain && Noprayer == false)
{
PrayerDrain = OldDrain;
}
else if(NewDrain <= OldDrain || Noprayer == true)
{
PrayerDrain = NewDrain;
}
}

/*END OF PRAYER STUFF*/

	public void spawnobjects() { //0= WEST | -1 = NORTH | -2 = EAST | -3 = SOUTH
	ReplaceObject2(2895, 3564, 2213, -1, 10);
	ReplaceObject2(2895, 3563, 2213, -1, 10);
	ReplaceObject2(2895, 3562, 2213, -1, 10);
	ReplaceObject2(2894, 3564, 2213, -1, 10);
	ReplaceObject2(2894, 3563, 2213, -1, 10);
	ReplaceObject2(2894, 3562, 2213, -1, 10);
	ReplaceObject(2899, 3558, 1519, -2);
	makeGlobalObject(3210, 3424, 2469, 0, 10);
	makeGlobalObject(2927, 3558, 2563, 0, 10);
	makeGlobalObject(2891, 3538, 299, 0, 10);
	makeGlobalObject(2889, 3536, 299, 0, 10);
	makeGlobalObject(2891, 3534, 299, 0, 10);
	makeGlobalObject(2896, 3538, 2513, 0, 10);
	makeGlobalObject(2896, 3537, 2513, -2, 10);
	makeGlobalObject(2868, 10205, 354, -1, 10);
	makeGlobalObject(2897, 3532, 354, -1, 10);
	makeGlobalObject(2897, 3533, 354, -1, 10);
	makeGlobalObject(2896, 3533, 354, -1, 10);
	}

	public void setSetting(int settingID, int value) {	// Xero: Yay I'm second, thx to Phate for helping  
		outStream.createFrame(36);
		outStream.writeWordBigEndian(settingID);
		outStream.writeByte(value);
	}

public void levelup(int skill)
{
switch(skill)  
{
      
       case 0: // Attack
            sendFrame164(6247);
            sendFrame126("Congratulations, you just advanced an Attack level!", 6248);
            sendFrame126("Your Attack level is now "+playerLevel[0]+" .", 6249);
            sendMessage("Congratulations, you just advanced an Attack level.");
            stillgfx(199, absY, absX);
	    NpcDialogueSend = true;
            break;
         case 1: // Strength
            sendFrame164(6206);
            sendFrame126("Congratulations, you just advanced a Strength level!", 6207);
            sendFrame126("Your Strength level is now "+playerLevel[2]+" .", 6208);
            sendMessage("Congratulations, you just advanced a Strength level.");
		stillgfx(199, absY, absX);
            NpcDialogueSend = true;
            break;
         case 2: // Defence
            sendFrame164(6253);
            sendFrame126("Congratulations, you just advanced a Defense level!", 6254);
            sendFrame126("Your Defense level is now "+playerLevel[1]+" .", 6255);
            sendMessage("Congratulations, you just advanced a Defense level.");
		stillgfx(199, absY, absX);
            NpcDialogueSend = true;
            break;
         case 3: //Hitpoints
            sendFrame164(6216);
            sendFrame126("Congratulations, you just advanced a Hitpoints level!", 6217);
            sendFrame126("Your Hitpoints level is now "+playerLevel[3]+" .", 6218);
            sendMessage("Congratulations, you just advanced a Hitpoints level.");
		stillgfx(199, absY, absX);
            NpcDialogueSend = true;
            break;
         case 4: //Ranged
            sendFrame164(4443);
            sendFrame126("Congratulations, you just advanced a Ranged level!", 4444);
            sendFrame126("Your Ranged level is now "+playerLevel[4]+" .", 4445);
            sendMessage("Congratulations, you just advanced a Ranged level.");
		stillgfx(199, absY, absX);
            NpcDialogueSend = true;
            break;
         case 5: // Prayer
            sendFrame164(6242);
            sendFrame126("Congratulations, you just advanced a Prayer level!", 6243);
            sendFrame126("Your Prayer level is now "+playerLevel[5]+" .", 6244);
            sendMessage("Congratulations, you just advanced a Prayer level.");
		stillgfx(199, absY, absX);
            NpcDialogueSend = true;
            break;
         case 6: // Magic
            sendFrame164(6211);
            sendFrame126("Congratulations, you just advanced a Magic level!", 6212);
            sendFrame126("Your Magic level is now "+playerLevel[6]+" .", 6213);
            sendMessage("Congratulations, you just advanced a Magic level.");
		stillgfx(199, absY, absX);
            NpcDialogueSend = true;
            break;
         case 7: //Cooking
            sendFrame164(6226);
            sendFrame126("Congratulations, you just advanced a Cooking level!", 6227);
            sendFrame126("Your Cooking level is now "+playerLevel[7]+" .", 6228);
            sendMessage("Congratulations, you just advanced a Cooking level.");
	stillgfx(199, absY, absX);
            NpcDialogueSend = true;
            break;
         case 8: //Woodcutting
            sendFrame164(4272);
            sendFrame126("Congratulations, you just advanced a Woodcutting level!", 4273);
            sendFrame126("Your Woodcutitng level is now "+playerLevel[8]+" .", 4274);
            sendMessage("Congratulations, you just advanced a Woodcutting level.");
		stillgfx(199, absY, absX);
            NpcDialogueSend = true;
            break;
         case 9: //Fletching
            sendFrame164(6231);
            sendFrame126("Congratulations, you just advanced a Fletching level!", 6232);
            sendFrame126("Your Fletching level is now "+playerLevel[9]+" .", 6233);
            sendMessage("Congratulations, you just advanced a Fletching level.");
		stillgfx(199, absY, absX);
            NpcDialogueSend = true;
            break;
         case 10: //Fishing
            sendFrame164(6258);
            sendFrame126("Congratulations, you just advanced a Fishing level!", 6259);
            sendFrame126("Your Fishing level is now "+playerLevel[10]+" .", 6260);
            sendMessage("Congratulations, you just advanced a Fishing level.");
		stillgfx(199, absY, absX);
            NpcDialogueSend = true;
            break;
         case 11: //Firemaking
            sendFrame164(4282);
            sendFrame126("Congratulations, you just advanced a Firemaking level!", 4283);
            sendFrame126("Your Firemaking level is now "+playerLevel[11]+" .", 4284);
            sendMessage("Congratulations, you just advanced a Firemaking level.");
		stillgfx(199, absY, absX);
            NpcDialogueSend = true;
            break;
         case 12: //Crafting
            sendFrame164(6263);
            sendFrame126("Congratulations, you just advanced a Crafting level!", 6264);
            sendFrame126("Your Crafting level is now "+playerLevel[12]+" .", 6265);
            sendMessage("Congratulations, you just advanced a Crafting level.");
		stillgfx(199, absY, absX);
            NpcDialogueSend = true;
            break;
         case 13: //Smithing
            sendFrame164(6221);
            sendFrame126("Congratulations, you just advanced a Smithing level!", 6222);
            sendFrame126("Your Smithing level is now "+playerLevel[13]+" .", 6223);
            sendMessage("Congratulations, you just advanced a Smithing level.");
		stillgfx(199, absY, absX);
            NpcDialogueSend = true;
            break;
         case 14: //Mining
            sendFrame164(4416);
            sendFrame126("Congratulations, you just advanced a Mining level!", 4417);
            sendFrame126("Your Mining level is now "+playerLevel[14]+" .", 4418);
            sendMessage("Congratulations, you just advanced a Mining level.");
		stillgfx(199, absY, absX);
            NpcDialogueSend = true;
            break;
         case 15: //Herblore
            sendFrame164(6237);
            sendFrame126("Congratulations, you just advanced a Herblore level!", 6238);
            sendFrame126("Your Herblore level is now "+playerLevel[15]+" .", 6239);
            sendMessage("Congratulations, you just advanced a Herblore level.");
		stillgfx(199, absY, absX);
            NpcDialogueSend = true;
            break;
         case 16: //Agility
            sendFrame164(4277);
            sendFrame126("Congratulations, you just advanced a Agility level!", 4278);
            sendFrame126("Your Agility level is now "+playerLevel[16]+" .", 4279);
            sendMessage("Congratulations, you just advanced an Agility level.");
		stillgfx(199, absY, absX);
            NpcDialogueSend = true;
            break;
         case 17: //Thieving
            sendFrame164(4261);
            sendFrame126("Congratulations, you just advanced a Thieving level!", 4262);
            sendFrame126("Your Thieving level is now "+playerLevel[17]+" .", 4263);
            sendMessage("Congratulations, you just advanced a Thieving level.");
		stillgfx(199, absY, absX);
            NpcDialogueSend = true;
            break;
         case 18: //Slayer
     //       sendFrame164(12123);
    //        sendFrame126("Congratulations, you just advanced a Slayer level!", 6207);
    //        sendFrame126("Your Slayer level is now "+playerLevel[18]+" .", 6208);
            sendMessage("Congratulations, you just advanced a Slayer level.");
		stillgfx(199, absY, absX);
            NpcDialogueSend = true;
            break;
         case 19: //Farming
      //      sendFrame164(4261);
      //      sendFrame126("Congratulations, you just advanced a Farming level!", 6207);
      //      sendFrame126("Your Farming level is now "+playerLevel[19]+" .", 6208);
            sendMessage("Congratulations, you just advanced a Farming level.");
		stillgfx(199, absY, absX);
            NpcDialogueSend = true;
            break;
         case 20: //Runecrafting
            sendFrame164(4267);
            sendFrame126("Congratulations, you just advanced a Runecrafting level!", 4268);
            sendFrame126("Your Runecrafting level is now "+playerLevel[20]+" .", 4269);
            sendMessage("Congratulations, you just advanced a Runecrafting level.");
		stillgfx(199, absY, absX);
            NpcDialogueSend = true;
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
       if(person.distanceToPoint(absX, absY) <= range && person.playerId != playerId && !person.inSafe)
	if(person.heightLevel == heightLevel) {
        {
       int damage = misc.random(maxDamage);
	if (person.playerLevel[3] > 0){
       person.animation(gfx, person.absY, person.absX);
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
} }

public void attackNPCSWithin(int gfx, int maxDamage, int range) {
 for (int i = 0; i <= server.npcHandler.maxNPCs; i++)
  {
   if(server.npcHandler.npcs[i] != null) 
    {
      if(distanceToPoint(server.npcHandler.npcs[i].absX, server.npcHandler.npcs[i].absY) <= range && !server.npcHandler.npcs[i].IsDead && server.npcHandler.npcs[i].HP != 1000 && npcId != 2475 && npcId != 2259)
       {
        int damage = misc.random(maxDamage);
        stillgfx(gfx, server.npcHandler.npcs[i].absY, server.npcHandler.npcs[i].absX);
        if (server.npcHandler.npcs[i].HP - hitDiff < 0) 
        damage = server.npcHandler.npcs[i].HP;
        hitDiff = damage;
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


public void stillgfx3(int id, int heightS, int Y, int X, int timeBCS) {
		outStream.createFrame(85);
		outStream.writeByteC(Y - (mapRegionY * 8));
		outStream.writeByteC(X - (mapRegionX * 8));
		outStream.createFrame(4);
		outStream.writeByte(0);//Tiles away (X >> 4 + Y & 7) 	//Tiles Away From absX and absY 
		outStream.writeWord(id);//Graphic id
		outStream.writeByte(heightS);//height of the spell above it's basic place, i think it's written in pixels 100 pixels higher //100
		outStream.writeWord(timeBCS);//Time before casting the graphic
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

/*MAGIC*/

public int MageAttackIndex = 0;
public int graphicstimer = 0;
public boolean cast = false;
public boolean fired = false;
public boolean firingspell = false;
public int TeleTimer = 0;
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

public boolean firespell(int castID, int casterY, int casterX, int offsetY, int offsetX, int angle, int speed, int movegfxID,int startHeight, int endHeight, int MageAttackIndex, int finishID, int enemyY,int enemyX) 
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
MageAttackIndex = MageAttackIndex;
actionTimer = 0;


	/*Casts spell in hand - xerozcheez*/
	if(cast == false && graphicstimer <= 0)
	{
	stillgfx3(castID, 100, casterY, casterX, 0);
	cast = true;
	graphicstimer = 4;
	firingspell = true;
	}
	//Fires Projectile
	if(cast == true && fired == false && actionTimer <= 0) {
		createProjectile(casterY, casterX, offsetY, offsetX, angle, speed, movegfxID, startHeight, endHeight, MageAttackIndex);
		fired = true;
		}
	//Finishes Spell
	if(fired == true && actionTimer <= 0) {
		stillgfx3(finishID, 100, enemyY, enemyX, 95);
		resetGFX(castID, enemyX, enemyY);
		return false;
		}
		return true;



	} //Resets Projectiles
	public void resetGFX(int id, int X, int Y) {
		GraphicsHandler.removeGFX(id, X, Y);
		firingspell = false;
		cast = false;
		fired = false;
		}

 	public void createProjectile(int casterY, int casterX, int offsetY, int offsetX, int angle, int speed, int gfxMoving,
                int startHeight, int endHeight, int MageAttackIndex) {
                outStream.createFrame(85);
                outStream.writeByteC((casterY - (mapRegionY * 8)) - 2);
                outStream.writeByteC((casterX - (mapRegionX * 8)) - 3);
                outStream.createFrame(117);
                outStream.writeByte(angle);                     //Starting place of the projectile
                outStream.writeByte(offsetY);               //Distance between caster and enemy Y
                outStream.writeByte(offsetX);                //Distance between caster and enemy X
                outStream.writeWord(MageAttackIndex);        //The NPC the missle is locked on to
                outStream.writeWord(gfxMoving);             //The moving graphic ID
                outStream.writeByte(startHeight);           //The starting height
                outStream.writeByte(endHeight);             //Destination height
                outStream.writeWord(51);                        //Time the missle is created
                outStream.writeWord(speed);                     //Speed minus the distance making it set
                outStream.writeByte(16);                        //Initial slope
                outStream.writeByte(64);                        //Initial distance from source (in the direction of the missile) //64    
    }

public void ReportAbuse(String report, int rule, int mute)
{
      BufferedWriter bw = null;

      try {
         bw = new BufferedWriter(new FileWriter("Logs/Chat.txt", true));
	 bw.write("[---"+report+" reported by "+playerName+"---]");
	 bw.newLine();
         if(mute == 1) {
	 bw.write("[--- "+report+" was muted by "+playerName+". ---]");
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
         bw = new BufferedWriter(new FileWriter("Logs/Chat.txt", true));
	 bw.write("[--- "+report+" reported by "+playerName+". ---]");
	 bw.newLine();
         if(mute == 1) {
	 bw.write("[--- "+report+" was muted by "+playerName+". ---]");
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
         bw = new BufferedWriter(new FileWriter("Logs/MouseLogs.txt", true));
	 bw.write("[--- "+report+" reported by "+playerName+" for macro. ---]");
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
         bw = new BufferedWriter(new FileWriter("Logs/Chat.txt", true));
	 bw.write("[--- "+report+" reported by "+playerName+" for macro. ---]");
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
         bw = new BufferedWriter(new FileWriter("Logs/Reported.txt", true));
	 bw.write(report+" reported by "+playerName+" for "+rule+".");
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
         bw = new BufferedWriter(new FileWriter("Logs/Reported.txt", true));
	 bw.write(report+" reported by "+playerName+" for "+rule+".");
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
    	
public void TeleTo(String s, int level)
{
teleX = absX;
teleY = absY;
newheightLevel = heightLevel;
if(inCity == false && playerLevel[6] > level && teleblock == false && TeleTimer <= 0 && LogoutDelay <= 0) 
{
     
     if (s == "Varrock")
     {
	actionAmount++;
        teleX = 3210;
        teleY = 3424;
        addSkillXP((50*playerLevel[6]), 6);
        newheightLevel = 0;
	
     }

     if (s == "Falador")
     {
	actionAmount++;
        teleX = 2964;
        teleY = 3378;
        addSkillXP((100*playerLevel[6]), 6);
        newheightLevel = 0;
	
             
     }

     if (s == "Lumby")
     {
	actionAmount++;
        teleX = 3222;
        teleY = 3218;
        addSkillXP((300*playerLevel[6]), 6);
        newheightLevel = 0;
     }

     if (s == "Camelot")
     {
	actionAmount++;
        teleX = 2757;
        teleY = 3477;
       addSkillXP((450*playerLevel[6]), 6);
        newheightLevel = 0;
     }

     if (s == "Ardougne")
     {
	actionAmount++;
        teleX = 2662;
        teleY = 3305;
        addSkillXP((550*playerLevel[6]), 6);
        newheightLevel = 0;
        
     }

     if (s == "Watchtower")
     {
	actionAmount++;
        teleX = 2549;
        teleY = 3113;
        addSkillXP((600*playerLevel[6]), 6);
        newheightLevel = 2;
        
     }

     if (s == "Trollheim")
     {
	actionAmount++;
        teleX = 2890;
        teleY = 3677;
        addSkillXP((650*playerLevel[6]), 6);
        newheightLevel = 0;
     }

     if (s == "Ape")
     {
	actionAmount++;
        teleX = 2762;
        teleY = 2784;
        addSkillXP((700*playerLevel[6]), 6);
        newheightLevel = 0;
     }
    if (s == "Paddewwa"){
	actionAmount++;
        teleX = 3131; 
        teleY = 9912;
        addSkillXP((220*playerLevel[playerMagic]), playerMagic);
        newheightLevel = 0;
     }
 if (s == "Senntisten"){
	actionAmount++;
        teleX = 3312; 
        teleY = 3375;
        addSkillXP((350*playerLevel[playerMagic]), playerMagic);
        newheightLevel = 0;
     }
  
  if (s == "Kharyrll"){
    	actionAmount++;
        teleX = 3494; 
        teleY = 3483;
        addSkillXP((470*playerLevel[playerMagic]), playerMagic);
        newheightLevel = 0;
     }
    if (s == "Lassar"){
	actionAmount++;
        teleX = 3007; 
        teleY = 3477;
        addSkillXP((540*playerLevel[playerMagic]), playerMagic);
        newheightLevel = 0;
     }
    if (s == "Dareeyak"){
	actionAmount++;
        teleX = 2990; 
        teleY = 3695;
        addSkillXP((610*playerLevel[playerMagic]), playerMagic);
        newheightLevel = 0;
     }
    
    if (s == "Carrallangar"){
	actionAmount++;
        teleX = 3161; 
        teleY = 3671;
        addSkillXP((770*playerLevel[playerMagic]), playerMagic);
        newheightLevel = 0;

     }
  if (s == "Annakarl"){
	actionAmount++;
        teleX = 3288; 
        teleY = 3886;
        addSkillXP((850*playerLevel[playerMagic]), playerMagic);
        newheightLevel = 0;
     }
  if (s == "Ghorrock"){
    	actionAmount++;
        teleX = 3091;
        teleY = 3963;
        addSkillXP((950*playerLevel[playerMagic]), playerMagic);
        newheightLevel = 0;
     }
closeInterface();
teleport();
TeleTimer = 25;
}
else if (inCity == true) { sendMessage("You cannot teleport within <3City."); }
else if(LogoutDelay > 0) { sendMessage("Your teleport has failed!"); }
else if(teleblock == true)
{
sendMessage("A magical force stops you from teleporting.");
}
else if(playerLevel[6] < level)
{
sendMessage("You need a Magic level of "+level+" to cast this spell.");
}
updateRequired = true; 
appearanceUpdateRequired = true;
  }

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
	public void AddGlobalObject(int x, int y, int typeID, int orientation, int tileObjectType)
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

	public void magicOnNpc() {
		int npcIndex = inStream.readSignedWordBigEndianA();
		int magicID = inStream.readSignedWordA();
		//println_debug("npcIndex: "+npcIndex+" magicID: "+magicID);
                setAnimation(711);
		
		inCombat();
      
                int EnemyX2 = server.npcHandler.npcs[npcIndex].absX;
		int EnemyY2 = server.npcHandler.npcs[npcIndex].absY;
		int EnemyHP2 = server.npcHandler.npcs[npcIndex].HP;
		int offsetY = (absY - EnemyY2) * -1;
		int offsetX = (absX - EnemyX2) * -1;
		int hitDiff = 0;
		MageAttackIndex = npcIndex;
				
		
		if(EnemyHP2 == 0)
		return;
		
		teleportToX = absX; //Stop While Cast
		teleportToY = absY; //Stop While Cast
		{		
					
		try {

				if ((magicID == 1191) && (playerLevel[6] >= 60) && (playerHasItemAmount(4278, 2)==true) && (playerHasItemAmount(554, 1)==true) && (playerHasItemAmount(556, 4)==true)) { //Claws of Guthix 
					
					 
					 

					hitDiff = 0 + misc.random(20); //Damage
if (chargeArenaSpells == true){
						hitDiff = 30;
					}
animation(187, EnemyY2, EnemyX2);
deleteItem(556, getItemSlot(556), 4); 
deleteItem(4278, getItemSlot(4278), 2); 
deleteItem(554, getItemSlot(554), 1);
addSkillXP((35*playerLevel[6]), 6); 
					actionTimer = 4;
					
                                        }
				if ((magicID == 1190)  && (playerLevel[6] >= 60) && (playerHasItemAmount(4278, 2)==true) && (playerHasItemAmount(554, 4)==true) && (playerHasItemAmount(556, 2)==true)) { //Saradomin Strike 
					
					 
					 

					hitDiff = 0 + misc.random(20); //Damage
if (chargeArenaSpells == true){
						hitDiff = 30;
					}
animation(76, EnemyY2, EnemyX2);
deleteItem(556, getItemSlot(556), 2); 
deleteItem(4278, getItemSlot(4278), 2); 
deleteItem(554, getItemSlot(554), 4); 
addSkillXP((35*playerLevel[6]), 6);
					actionTimer = 4;
					
                                        }
				
				if ((magicID == 1192) && (playerLevel[6] >= 60) && (playerHasItemAmount(4278, 2)==true) && (playerHasItemAmount(554, 4)==true) && (playerHasItemAmount(556, 1)==true)) { //Flames of Zamorak
					
					 
					 

					hitDiff = 0 + misc.random(20); //Damage
if (chargeArenaSpells == true){
						hitDiff = 30;
					}
animation(78, EnemyY2, EnemyX2);
deleteItem(556, getItemSlot(556), 1); 
deleteItem(4278, getItemSlot(4278), 2); 
deleteItem(554, getItemSlot(554), 4); 
addSkillXP((35*playerLevel[6]), 6);
					actionTimer = 4;
					
                                        }
								
if ((magicID == 12871) && (playerLevel[6] >= 81) && (playerHasItemAmount(560, 2)==true) && (playerHasItemAmount(4278, 2)==true) && (playerHasItemAmount(555, 3)==true))  {	//	Ice Blitz
				setAnimation(1978);
				

 				animation(367, EnemyY2, EnemyX2);
						hitDiff = 0 + misc.random(26);
						 
					 
				deleteItem(555, getItemSlot(555), 3); 
				deleteItem(4278, getItemSlot(4278), 2);
				deleteItem(560, getItemSlot(560), 2);
				addSkillXP((46*playerLevel[6]), 6);
						actionTimer = 4;
						
						
						   
					}

	if ((magicID == 12929) && (playerLevel[6] >= 91) && (playerHasItemAmount(560, 4)==true) && (playerHasItemAmount(4278, 4)==true) && (playerHasItemAmount(566, 1)==true))  {	//	Blood Barrage
				setAnimation(1979);

				animation(377, EnemyY2, EnemyX2);
				//animation2(373, absY, absX);
 				
						hitDiff = 0 + misc.random(29);
						 
					 
				deleteItem(560, getItemSlot(560), 4); 
				deleteItem(4278, getItemSlot(4278), 4);
				deleteItem(566, getItemSlot(566), 1);
				addSkillXP((51*playerLevel[6]), 6);
						actionTimer = 4;
						
						
						   
					}

if ((magicID == 12881) && (playerLevel[6] >= 58) && (playerHasItemAmount(562, 2)==true) && (playerHasItemAmount(560, 2)==true) && (playerHasItemAmount(555, 2)==true))  {	//	Ice Burst
				setAnimation(1979);
				animation(363, EnemyY2, EnemyX2);

 				
						hitDiff = 0 + misc.random(22);
						 
					 
				deleteItem(555, getItemSlot(555), 2); 
				deleteItem(560, getItemSlot(560), 2);
				deleteItem(562, getItemSlot(562), 2);
				addSkillXP((34*playerLevel[6]), 6);
						actionTimer = 4;
						
						
						   
					}
				if ((magicID == 13023) && (playerLevel[6] >= 87) && (playerHasItemAmount(560, 4)==true) && (playerHasItemAmount(4278, 2)==true) && (playerHasItemAmount(556, 4)==true) && (playerHasItemAmount(566, 3)==true))  {	//	Shadow Barrage
				setAnimation(1979);
				animation(383, EnemyY2, EnemyX2);

 				
						hitDiff = 0 + misc.random(28);
						 
					 
				deleteItem(4278, getItemSlot(4278), 2); 
				deleteItem(566, getItemSlot(566), 3);
				deleteItem(556, getItemSlot(556), 4);
				deleteItem(560, getItemSlot(560), 4);
				addSkillXP((49*playerLevel[6]), 6);
						actionTimer = 4;
						
						
						   
					}
				if ((magicID == 12975) && (playerLevel[6] >= 86) && (playerHasItemAmount(560, 4)==true) && (playerHasItemAmount(4278, 2)==true) && (playerHasItemAmount(556, 4)==true) && (playerHasItemAmount(554, 4)==true))  {	//	Smoke Barrage
				setAnimation(1979);
				animation(390, EnemyY2, EnemyX2);
				//animation3(391, absY, absX);
 				 
						hitDiff = 0 + misc.random(27);
						 
					 
				deleteItem(560, getItemSlot(560), 4); 
				deleteItem(4278, getItemSlot(4278), 2);
				deleteItem(556, getItemSlot(556), 4);
				deleteItem(554, getItemSlot(566), 4);
				addSkillXP((49*playerLevel[6]), 6);
						actionTimer = 4;
					}
				
if ((magicID == 12911) && (playerLevel[6] >= 79) && (playerHasItemAmount(4278, 4)==true) && (playerHasItemAmount(560, 2)==true))  {	//	Blood Blitz
				setAnimation(1978);
				

 				animation(375, EnemyY2, EnemyX2);
						hitDiff = 0 + misc.random(25);
						 
					 
				deleteItem(4278, getItemSlot(4278), 4); 
				deleteItem(560, getItemSlot(560), 2);
				addSkillXP((45*playerLevel[6]), 6);
						actionTimer = 4;
						
						
						   
					}


if ((magicID == 12939) && (playerLevel[6] >= 50) && (playerHasItemAmount(562, 2)==true) && (playerHasItemAmount(560, 2)==true) && (playerHasItemAmount(554, 1)==true) && (playerHasItemAmount(556, 1)==true)) { //Smoke Rush
setAnimation(711);
animation(385, EnemyY2, EnemyX2);
actionTimer = 4;
hitDiff = 0 + misc.random(15);
deleteItem(560, getItemSlot(560), 2); 
				deleteItem(562, getItemSlot(562), 2);
				deleteItem(554, getItemSlot(554), 1);
deleteItem(556, getItemSlot(556), 1);
				addSkillXP((30*playerLevel[6]), 6);
}

if ((magicID == 12963) && (playerLevel[6] >= 62) && (playerHasItemAmount(562, 4)==true) && (playerHasItemAmount(560, 2)==true) && (playerHasItemAmount(554, 2)==true) && (playerHasItemAmount(556, 2)==true)) { //Smoke Burst
setAnimation(1979);
 
actionTimer = 4;
animation(388, EnemyY2, EnemyX2); 
hitDiff = 0 + misc.random(19);
deleteItem(562, getItemSlot(562), 4); 
				deleteItem(560, getItemSlot(560), 2);
				deleteItem(554, getItemSlot(554), 2);
deleteItem(556, getItemSlot(556), 2);
				addSkillXP((36*playerLevel[6]), 6);
}

if ((magicID == 12951) && (playerLevel[6] >= 74) && (playerHasItemAmount(4278, 2)==true) && (playerHasItemAmount(560, 2)==true) && (playerHasItemAmount(554, 2)==true) && (playerHasItemAmount(556, 2)==true)) { //Smoke Blitz
setAnimation(1978);
animation(387, EnemyY2, EnemyX2);
actionTimer = 4;
deleteItem(556, getItemSlot(556), 2); 
				deleteItem(4278, getItemSlot(4278), 2);
				deleteItem(554, getItemSlot(554), 2);
deleteItem(556, getItemSlot(556), 2);
				addSkillXP((43*playerLevel[6]), 6);
hitDiff = 0 + misc.random(23);
}

if ((magicID == 12901) && (playerLevel[6] >= 56) && (playerHasItemAmount(562, 2)==true) && (playerHasItemAmount(560, 2)==true) && (playerHasItemAmount(4278, 1)==true)) { //Blood Rush
setAnimation(711);

animation(373, EnemyY2, EnemyX2);
actionTimer = 4;
deleteItem(560, getItemSlot(560), 2); 
				deleteItem(562, getItemSlot(562), 2);
				deleteItem(4278, getItemSlot(4278), 1);
				addSkillXP((33*playerLevel[6]), 6);
hitDiff = 0 + misc.random(17);
}

if ((magicID == 12919)  && (playerLevel[6] >= 68) && (playerHasItemAmount(562, 4)==true) && (playerHasItemAmount(560, 2)==true) && (playerHasItemAmount(4278, 2)==true)) { //Blood Burst
setAnimation(1979);

actionTimer = 4;
animation(376, EnemyY2, EnemyX2);
deleteItem(4278, getItemSlot(4278), 2); 
				deleteItem(560, getItemSlot(560), 2);
				deleteItem(562, getItemSlot(562), 4);
				addSkillXP((39*playerLevel[6]), 6);
hitDiff = 0 + misc.random(21);
}

if ((magicID == 12987) && (playerLevel[6] >= 52) && (playerHasItemAmount(562, 2)==true) && (playerHasItemAmount(560, 2)==true) && (playerHasItemAmount(566, 1)==true) && (playerHasItemAmount(556, 1)==true)) { //Shadow Rush
animation(379, EnemyY2, EnemyX2);
setAnimation(711);
actionTimer = 4;
deleteItem(560, getItemSlot(560), 2); 
				deleteItem(562, getItemSlot(562), 2);
				deleteItem(566, getItemSlot(566), 1);
deleteItem(556, getItemSlot(556), 1);
				addSkillXP((31*playerLevel[6]), 6);
hitDiff = 0 + misc.random(16);
}

if ((magicID == 13011) && (playerLevel[6] >= 64) && (playerHasItemAmount(562, 4)==true) && (playerHasItemAmount(560, 2)==true) && (playerHasItemAmount(566, 2)==true) && (playerHasItemAmount(556, 2)==true)) { //Shadow Burst
setAnimation(1979);

actionTimer = 4;
animation(382, EnemyY2, EnemyX2);
deleteItem(562, getItemSlot(562), 4); 
				deleteItem(560, getItemSlot(560), 2);
				deleteItem(566, getItemSlot(566), 2);
deleteItem(556, getItemSlot(556), 2);
				addSkillXP((37*playerLevel[6]), 6);
hitDiff = 0 + misc.random(20);
}

if ((magicID == 12999) && (playerLevel[6] >= 76) && (playerHasItemAmount(4278, 2)==true) && (playerHasItemAmount(560, 2)==true) && (playerHasItemAmount(566, 2)==true) && (playerHasItemAmount(556, 2)==true)) { //Shadow Blitz
setAnimation(1978);
animation(381, EnemyY2, EnemyX2);
actionTimer = 4;
deleteItem(4278, getItemSlot(4278), 2); 
				deleteItem(560, getItemSlot(560), 2);
				deleteItem(556, getItemSlot(556), 2);
deleteItem(566, getItemSlot(566), 2);
				addSkillXP((43*playerLevel[6]), 6);
hitDiff = 0 + misc.random(24);
}

if ((magicID == 12861) && (playerLevel[6] >= 58) && (playerHasItemAmount(562, 2)==true) && (playerHasItemAmount(560, 2)==true) && (playerHasItemAmount(555, 1)==true)) { //Ice Rush
setAnimation(711);
animation(361, EnemyY2, EnemyX2);
actionTimer = 4;
deleteItem(555, getItemSlot(555), 2); 
				deleteItem(560, getItemSlot(560), 2);
				deleteItem(562, getItemSlot(562), 2);
				addSkillXP((34*playerLevel[6]), 6);
hitDiff = 0 + misc.random(18);
}

 
				
				if (((magicID == 12891) && (playerLevel[6] >= 94) && (playerHasItemAmount(560, 4)==true) && (playerHasItemAmount(4278, 2)==true) && (playerHasItemAmount(555, 6)==true)) || ((magicID == 12891) && (playerLevel[6] >= 94) && (playerHasItemAmount(560, 4)==true) && (playerHasItemAmount(4278, 2)==true) && (playerEquipment[playerWeapon] == 1383)))   {	//	Ice Barrage
				setAnimation(1979);
				//GraphicsHandler.createSpell(362, absY, absX, offsetX, offsetY, 50, 95, 360, 43, 31, MageAttackIndex + 1, 0, EnemyY2, EnemyX2); 
				
				animation(369, EnemyY2, EnemyX2);
 				
						hitDiff = 0 + misc.random(30);
						 
					 
				deleteItem(560, getItemSlot(560), 4); 
				actionTimer = 4;
				deleteItem(4278, getItemSlot(4278), 2);
				addSkillXP((52*playerLevel[6]), 6);
				if((playerEquipment[playerWeapon] != 1383) && (magicID == 12891)) { 
				deleteItem(555, getItemSlot(555), 6); 
				}
				
				
						
						
						
						   
					}
					
					
 //server.npcHandler.npcs[npcIndex].currentHealth -= server.npcHandler.npcs[npcIndex].hitDiff;
					TurnPlayerTo(EnemyX2, EnemyY2);
					if ((EnemyHP2 - hitDiff) < 0) {
						hitDiff = EnemyHP2;
					}	
				 	faceNPC(npcIndex);				
					server.npcHandler.npcs[npcIndex].hitDiff = hitDiff;
					server.npcHandler.npcs[npcIndex].Killing[playerId] += hitDiff;
					server.npcHandler.npcs[npcIndex].updateRequired = true;
					server.npcHandler.npcs[npcIndex].hitUpdateRequired = true;
						}
						catch(Exception e) {
							println_debug(e.toString());
						}
					
					}

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
         characterfile = new BufferedReader(new FileReader("CFG/Teleport.cfg"));
      } catch(FileNotFoundException fileex) {
         misc.println("Teleport.CFG was not found.");
         
      }
      try {
         line = characterfile.readLine();
      } catch(IOException ioexception) {
         misc.println("Error loading Teleport.CFG.");
         
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
sendMessage("You don't have the required runes.");}
            }
            if(RuneTypes == 3){
            if(Has3Items(Rune1, R1Amt, Rune2, R2Amt, Rune3, R3Amt)){
               
               deleteItem(Rune1, GetItemSlot(Rune1), R1Amt);
               deleteItem(Rune2, GetItemSlot(Rune2), R2Amt);
               deleteItem(Rune3, GetItemSlot(Rune3), R3Amt);
               teleport(TeleX, TeleY, Height, Xp, playerMagic);
   
            }else{
sendMessage("You don't have the required runes.");}
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
sendMessage("You need a "+GetItemName(Item)+".");}
            }else{
sendMessage("You don't have the required runes.");}
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
//Dragon smithing
      if(removeID == 1215 || removeID == 6739 || removeID == 3140
      || removeID == 1149 || removeID == 1249 || removeID == 1434
      || removeID == 4087 || removeID == 4587 || removeID == 3204
      || removeID == 4585 || removeID == 1187 || removeID == 1305
      || removeID == 1377 || removeID == 4151 || removeID == 7461
      || removeID == 4224 || removeID == 4214 || removeID == 4153 
      || removeID == 3122 || removeID == 6809 || removeID == 1704) {    
      return 2893;
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
 if (barType == 2349) return barAmount*35;
 if (barType == 2351) return barAmount*45;
 if (barType == 2353) return barAmount*55;
 if (barType == 2359) return barAmount*60;
 if (barType == 2361) return barAmount*65;
 if (barType == 2363) return barAmount*72;
 if (barType == 2893) return barAmount*71;
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
if(item == 42 && playerLevel[13] >= 55) {
 return true;
}
if(item == 1329 && playerLevel[13] >= 55) {
 return true;
}
if(item == 1299 && playerLevel[13] >= 56) {
 return true;
}
if(item == 866 && playerLevel[13] >= 57) {
 return true;
}
if(item == 1159 && playerLevel[13] >= 57) {
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
if(item == 1085 && playerLevel[13] >= 66) {
 return true;
}
if(item == 1071 && playerLevel[13] >= 66) {
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
if(item == 1287 && playerLevel[13] >= 74) {
 return true;
}
if(item == 4823 && playerLevel[13] >= 74) {
 return true;
}
if(item == 823 && playerLevel[13] >= 74) {
 return true;
}
if(item == 1331 && playerLevel[13] >= 75) {
 return true;
}
if(item == 43 && playerLevel[13] >= 75) {
 return true;
}
if(item == 1301 && playerLevel[13] >= 76) {
 return true;
}
if(item == 1161 && playerLevel[13] >= 77) {
 return true;
}
if(item == 867 && playerLevel[13] >= 77) {
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
if(item == 1073 && playerLevel[13] >= 86) {
 return true;
}
if(item == 1359 && playerLevel[13] >= 86) {
 return true;
}
if(item == 1091 && playerLevel[13] >= 86) {
 return true;
}
if(item == 1432 && playerLevel[13] >= 87) {
 return true;
}
if(item == 1123 && playerLevel[13] >= 88) {
 return true;
}
if(item == 1147 && playerLevel[13] >= 88) {
 return true;
}
if(item == 1289 && playerLevel[13] >= 89) {
 return true;
}
if(item == 4824 && playerLevel[13] >= 89) {
 return true;
}
if(item == 824 && playerLevel[13] >= 89) {
 return true;
}
if(item == 44 && playerLevel[13] >= 90) {
 return true;
}
if(item == 1333 && playerLevel[13] >= 90) {
 return true;
}
if(item == 1303 && playerLevel[13] >= 91) {
 return true;
}
if(item == 1163 && playerLevel[13] >= 92) {
 return true;
}
if(item == 868 && playerLevel[13] >= 92) {
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
if(item == 1093 && playerLevel[13] >= 99) {
 return true;
}
if(item == 1127 && playerLevel[13] >= 99) {
 return true;
}
if(item == 1215 && playerLevel[13] >= 100) {//Dagger
return true;
}
if(item == 1434 && playerLevel[13] >= 100) {//Mace
return true;
}
if(item == 1149 && playerLevel[13] >= 100) {//Med
return true;
}
if(item == 1249 && playerLevel[13] >= 100) {//Spear
return true;
}
if(item == 4587 && playerLevel[13] >= 105) {//Scimitar
return true;
}
if(item == 1305 && playerLevel[13] >= 110) {//LongSword
return true;
}
if(item == 3204 && playerLevel[13] >= 115) {//Halberd
return true;
}
if(item == 1377 && playerLevel[13] >= 115) {//BattleAxe
return true;
}
if(item == 7158 && playerLevel[13] >= 135) {//2H
return true;
}
if(item == 3140 && playerLevel[13] >= 135) {//Chain
return true;
}
if(item == 4087 && playerLevel[13] >= 135) {//PlateLegs
return true;
}
if(item == 4585 && playerLevel[13] >= 135) {//PlateSkirt
return true;
}
if(item == 1187 && playerLevel[13] >= 120) {//Sq shield
return true;
}
if(item == 6739 && playerLevel[13] >= 105) {//Wc Axe
return true;
}
if(item == 4151 && playerLevel[13] >= 120) {//Abyssal whip
return true;
}
if(item == 7461 && playerLevel[13] >= 100) {//Dragon gloves
return true;
}
if(item == 4224 && playerLevel[13] >= 135) {//Crystal shield
return true;
}
if(item == 4214 && playerLevel[13] >= 135) {//Crystal bow
return true;
}
if(item == 4153 && playerLevel[13] >= 100) {//Granite maul
return true;
}
if(item == 3122 && playerLevel[13] >= 105) {//Grantie shield
return true;
}
if(item == 1704 && playerLevel[13] >= 100) {//Amulet
return true;
}

if(item == 6809 && playerLevel[13] >= 100) {//Granite legs
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
  sendQuest("@red@5 bars@red@",1112);
 } else {
  sendQuest("@gre@5 bars@gre@",1112);
 }
 if(amountOfItem(barType) < 3) {
  sendQuest("@red@3 bars@red@",1109);
  sendQuest("@red@3 bars@red@",1110);
  sendQuest("@red@3 bars@red@",1118);
  sendQuest("@red@3 bars@red@",1111);
  sendQuest("@red@3 bars@red@",1095);
  sendQuest("@red@3 bars@red@",1115);
  sendQuest("@red@3 bars@red@",1090);
 } else {
  sendQuest("@gre@3 bars@gre@",1109);
  sendQuest("@gre@3 bars@gre@",1110);
  sendQuest("@gre@3 bars@gre@",1118);
  sendQuest("@gre@3 bars@gre@",1111);
  sendQuest("@gre@3 bars@gre@",1095);
  sendQuest("@gre@3 bars@gre@",1115);
  sendQuest("@gre@3 bars@gre@",1090);
 }
 if(amountOfItem(barType) < 2) {
  sendQuest("@red@2 bars@red@",1113);
  sendQuest("@red@2 bars@red@",1116);
  sendQuest("@red@2 bars@red@",1114);
  sendQuest("@red@2 bars@red@",1089);
  sendQuest("@red@2 bars@red@",8428);
 } else {
  sendQuest("@gre@2 bars@gre@",1113);
  sendQuest("@gre@2 bars@gre@",1116);
  sendQuest("@gre@2 bars@gre@",1114);
  sendQuest("@gre@2 bars@gre@",1089);
  sendQuest("@gre@2 bars@gre@",8428);
 }
 if(amountOfItem(barType) < 1) {
  sendQuest("@red@1 bar@red@",1125);
  sendQuest("@red@1 bar@red@",1126);
  sendQuest("@red@1 bar@red@",1127);
  sendQuest("@red@1 bar@red@",1124);
  sendQuest("@red@1 bar@red@",1128);
  sendQuest("@red@1 bar@red@",1129);
  sendQuest("@red@1 bar@red@",1130);
  sendQuest("@red@1 bar@red@",13357);
  sendQuest("@red@1 bar@red@",1131);
  sendQuest("@red@1 bar@red@",11459);
 } else {
  sendQuest("@gre@1 bar@gre@",1125);
  sendQuest("@gre@1 bar@gre@",1126);
  sendQuest("@gre@1 bar@gre@",1127);
  sendQuest("@gre@1 bar@gre@",1124);
  sendQuest("@gre@1 bar@gre@",1128);
  sendQuest("@gre@1 bar@gre@",1129);
  sendQuest("@gre@1 bar@gre@",1130);
  sendQuest("@gre@1 bar@gre@",13357);
  sendQuest("@gre@1 bar@gre@",1131);
  sendQuest("@gre@1 bar@gre@",11459);
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
 addItemToSmith(1428,1,1120,1);
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
//addItemToSmith(1307,4,1123);
 addItemToSmith(4824,4,1122,1);
 //addItemToSmith(1307,4,1123);
 sendQuest("",1135);
 sendQuest("",1134);
 sendQuest("",11461);
 sendQuest("",11459);
}
if(barType == 2893) { //Elemental(Dragon)
 if(playerLevel[13] < 100) {
  sendQuest("@bla@Crystal bow@bla@",1101);
  sendQuest("@bla@Plate legs@bla@",1099);
  sendQuest("@bla@Plate skirt@bla@",1100);
  sendQuest("@bla@2 hand sword@bla@",1088);
 } else {
  sendQuest("@whi@Crystal bow@whi@",1101);
  sendQuest("@whi@Plate legs@whi@",1099);
  sendQuest("@whi@Plate skirt@whi@",1100);
  sendQuest("@whi@2 hand sword@whi@",1088);
 }
 if(playerLevel[13] < 100) {
  sendQuest("@bla@Abyssal whip@bla@",8429);
 } else {
  sendQuest("@whi@Abyssal whip@whi@",8429);
 }
 if(playerLevel[13] < 100) {
  sendQuest("@bla@Crystal shield@bla@",1105);
 } else {
  sendQuest("@whi@Crystal shield@whi@",1105);
 }
 if(playerLevel[13] < 100) {
  sendQuest("@bla@Chain body@bla@",1098);
 } else {
  sendQuest("@whi@Chain body@whi@",1098);
 }
 if(playerLevel[13] < 100) {
  sendQuest("@bla@Battle axe@bla@",1092);
 } else {
  sendQuest("@whi@Battle axe@whi@",1092);
 }
 if(playerLevel[13] < 100) {
  sendQuest("@bla@Halberd@bla@",1083);
 } else {
  sendQuest("@whi@Halberd@whi@",1083);
 }
 if(playerLevel[13] < 100) {
  sendQuest("@bla@Square shield@bla@",1104);
 } else {
  sendQuest("@whi@Square shield@whi@",1104);
 }
 if(playerLevel[13] < 100) {
  sendQuest("@bla@Dragon gloves@bla@",1103);
  sendQuest("@bla@Granite legs@bla@",1106);
 } else {
  sendQuest("@whi@Dragon gloves@whi@",1103);
  sendQuest("@whi@Granite legs@whi@",1106);
 }
 if(playerLevel[13] < 100) {
  sendQuest("@bla@Long sword@bla@",1086);
 } else {
  sendQuest("@whi@Long sword@whi@",1086);
 }
 if(playerLevel[13] < 100) {
  sendQuest("@bla@Scimitar@bla@",1087);
  sendQuest("@bla@Granite shield@bla@",1108);
 } else {
  sendQuest("@whi@Scimitar@whi@",1087);
  sendQuest("@whi@Granite shield@whi@",1108);
 }
 if(playerLevel[13] < 98) {
  sendQuest("@bla@Spear@bla@",1085);
  sendQuest("@bla@Granite maul@bla@",1107);
  sendQuest("@bla@Amulet of Glory@",13358);
 } else {
  sendQuest("@whi@Spear@whi@",1085);
  sendQuest("@whi@Granite maul@whi@",1107);
  sendQuest("@whi@Amulet of Glory@whi@",13358);
 }
 if(playerLevel[13] < 100) {
  sendQuest("@bla@Medium helm@bla@",1102);
 } else {
  sendQuest("@whi@Medium helm@whi@",1102);
 }
 if(playerLevel[13] < 100) {
  sendQuest("@bla@Mace@bla@",1093);
 } else {
  sendQuest("@whi@Mace@whi@",1093);
 }
 if(playerLevel[13] < 100) {
  sendQuest("@bla@Axe@bla@",1091);
 } else {
  sendQuest("@whi@Axe@whi@",1091);
 }
 if(playerLevel[13] < 100) {
  sendQuest("@bla@Dagger@bla@",1094);
 } else {
  sendQuest("@whi@Dagger@whi@",1094);
 }
 addItemToSmith(1215,0,1119,1);
 addItemToSmith(6739,0,1120,1);
 addItemToSmith(3140,0,1121,1);
 addItemToSmith(1149,0,1122,1);
 addItemToSmith(4153,0,1123,1);
 addItemToSmith(1249,1,1119,1);
 addItemToSmith(1434,1,1120,1);
 addItemToSmith(4087,1,1121,1);
 addItemToSmith(7461,1,1122,1);
 addItemToSmith(3122,1,1123,1);
 addItemToSmith(4587,2,1119,1);
 addItemToSmith(3204,2,1120,1);
 addItemToSmith(4585,2,1121,1);
 addItemToSmith(1187,2,1122,1);
 addItemToSmith(6809,2,1123,1);
 addItemToSmith(1305,3,1119,1);
 addItemToSmith(1377,3,1120,1);
 addItemToSmith(4214,3,1121,1);
 addItemToSmith(4224,3,1122,1);
 //addItemToSmith(0,3,1123);
 sendQuest("",1132);
 sendQuest("",1096);
 addItemToSmith(7158,4,1119,1);
 addItemToSmith(4151,4,1120,1);
 //addItemToSmith(4540,4,1121,1);
 addItemToSmith(1704,4,1122,1);
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
sendMessage("You turn the bars into ores.");

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
sendMessage("but you have no bars to convert.");
}
}
else
{
sendMessage("You need a Magic level of 65 to cast that spell.");
}
}

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
sendQuest("", 640);
sendQuest("",663);
sendQuest("", 682);
sendQuest("", 3985);

/*FTP Quests*/
sendQuest("", 7332);
sendQuest("", 7333);
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
sendQuest("", 9927);
sendQuest("", 7349);
sendQuest("", 7350);
sendQuest("", 7351);
sendQuest("", 13356);
sendQuest("", 17510); //Quests
sendQuest("", 7353);
sendQuest("", 15841);
sendQuest("", 16149);
sendQuest("", 6987);
sendQuest("", 16128);
sendQuest("", 15487);
sendQuest("", 6027);
sendQuest("", 15499);
sendQuest("", 18306);
sendQuest("", 668);
sendQuest("", 14912);
sendQuest("", 15352);
sendQuest("", 15592);
sendQuest("", 15235);
sendQuest("", 191);
sendQuest("", 6024);
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
	MyOutput = new PrintStream(new FileOutputStream("Saved/" + playerName + ".dat"));
	
  	 } catch (IOException e) {
   	 }
	if (MyOutput != null) {
	for(int i=0;i<22;i++) {
             	MyOutput.print(statName[i] + " - " + playerLevel[i]  + " - " + playerXP[i] + "\n");
             	 }
      		 MyOutput.close();
     	  } else {
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

					sendQuest("@dre@Stats", 8144);  //Title
					clearQuestInterface();
				    	sendQuest(p2.playerName+" @yel@Stats:", 8145);
				    	sendQuest("Attack level: " +Attacklvl, 8148);
				    	sendQuest("Strength level: "+Strengthlvl, 8149);
					sendQuest("Defense level: "+Defencelvl, 8150);
					sendQuest("Hitpoints level: "+Hitpointslvl, 8151);
					sendQuest("Prayer level: "+Prayerlvl, 8152);
					sendQuest("Magic level: "+Magiclvl, 8153);
					sendQuest("Range level: "+Rangelvl, 8154);
					sendQuest("Runecraft level: "+Runecraftlvl, 8155);
					sendQuest("Herblore level: "+Herblorelvl, 8156);
                                        sendQuest("Theiving level: "+Theivinglvl, 8157);
					sendQuest("Agility level: "+Agilitylvl, 8158);
					sendQuest("Crafting level: "+Craftinglvl, 8159);
					sendQuest("Fletching level: "+Fletchinglvl, 8160);
					sendQuest("Slayer level: "+Slayerlvl, 8161);
					sendQuest("Mining level is: "+Mininglvl, 8162);
					sendQuest("Smithing level is: "+Smithinglvl, 8163);
					sendQuest("Fishing level is: "+Fishinglvl, 8164);
					sendQuest("Cooking level is: "+Cookinglvl, 8165);
					sendQuest("Firemaking level is: "+Firemakinglvl, 8166);
					sendQuest("Woodcutting level is: "+Woodcuttinglvl, 8167);
					sendQuest("Farming level is: "+Farminglvl, 8168);
					sendQuest("", 8169);
					sendQuest("Kills: "+killcount1+"", 8170);
					sendQuest("Player Kill Points: "+pkpoints1, 8171);
					sendQuestSomething(8143);
					showInterface(8134);
					flushOutStream();	
				

}
}
		public int dots = 0;
		public int start[] = {0,0,0,0};
		public int IPPart1 = 127;
		public int IPPart2 = 0;
		public int IPPart3 = 0;
		public int IPPart4 = 1;


public int playerItemAmountCount = 0;

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


public void inCombat()
{
LogoutDelay = 80;
}
public void entangle()
{
EntangleDelay = 30;
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
	} catch(Exception e) { sendMessage("Error: Removing equipment."); }
}
for(int rr=0; rr<playerItems.length; rr++) {
	try {
		if(playerItems[rr] > 0 && playerItems[rr] < 10000) {
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
		if((item > 0) && (item < 10000)) {
			remove(item, r);
		}
	} catch(Exception e) { sendMessage("Error: Removing equipment."); }
}
for(int rr=0; rr<playerItems.length; rr++) {
	try {
		if(playerItems[rr] > 0 && playerItems[rr] < 10000) {
			//createItem(currentX,currentY,playerItems[rr]-1);
			ItemHandler.addItem(playerItems[rr]-1, absX, absY, playerItemsN[rr], KillerId, false);
                        //createGroundItem(playerItems[rr]-1, absX, absY, playerItemsN[i]);
			deleteItem(playerItems[rr]-1, getItemSlot(playerItems[rr]-1), playerItemsN[rr]); 
		}
	} catch(Exception e) {  }
}
removeAllItems();
			sendMessage("Oh dear, you are dead!");
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
				hitDiff = 3 + misc.random(5);
                                sendMessage("You start to die from poison.");
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
                                if(playerLevel[3] == 0) // && (inclanwar = false)
				{
				ApplyDead();
				teleportToX = 2870;
				teleportToY = 10199;
				heightLevel = 0;
				updateRequired = true;
				appearanceUpdateRequired = true;
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




	public void closeInterface() {
		outStream.createFrame(219);
	}

/*MISC*/

public int TakeMiscTimer = 0;

public void TakeMisc(String message1, String message2, String message3, int reqSkillNum, int lvlReq, int XPskillnum, int XPamount, int item, int itemAmount, int delay, int emote) {

if(TakeMiscTimer == 0) {
 if(playerLevel[reqSkillNum] >= lvlReq) {
   setAnimation(emote);
   sendMessage(message1);
   sendMessage(message2);
   addItem(item, itemAmount);
   addSkillXP(XPamount, XPskillnum);
   TakeMiscTimer = delay;
  }
  else if(playerLevel[reqSkillNum] < lvlReq) {
  sendMessage("You need a "+statName[reqSkillNum]+" level of "+lvlReq+" to "+message3+".");
  }
 }
}

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
  sendMessage("You need a Runecraft level of "+lvlReq+" to craft "+runeName+" runes.");
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
  sendMessage("You need an Agility level of "+lvlReq+" to use this obstacle.");
  }
 }
}

/*WOODCUTTING*/

public boolean WC() {

if(WCTimer <= 0) {
if(TreeHP == 0) {
AddGlobalObj(TreeX, TreeY, 1341, 0, 10);
sendMessage("This tree has been choped.");
ResetWC();
return false;
}
else {
if(!hasAxe()) {
sendMessage("You need an axe to chop down this tree.");
ResetWC();
}
else if(hasAxe()) {
addSkillXP(WCxp, 8);
if(!addItem(logID, logAmount)) {
ResetWC();
return false;
}
else {
sendMessage("You get some logs.");
WCTimer = TreeTimer;
TreeHP--;
return true;
}
}
}
}
return false;
}

public void ResetWC() {

IsWcing = false;
WCTimer = 0;
logID = 0;
logAmount = 0;
TreeHP = 20;
TreeX = 0;
TreeY = 0;
TreeTimer = 0;
resetAnimation();
resetanim = 4;

}

public void Woodcutting(String logName,  int lvlReq, int XPamount, int log, int amount, int obj, int X, int Y, int emote) {

if(WCTimer <= 0) {
 if(playerLevel[8] >= lvlReq) {
   pEmote = emote;
   resetanim = 999;
   sendMessage("You begin to cut the "+logName+" tree.");
   logID = log;
   logAmount = amount;
   TreeX = X;
   TreeY = Y;
   TreeTimer = GetWCBonus(obj);
   WCxp = XPamount;
   WCTimer = TreeTimer;
   IsWcing = true;
  }
  else if(playerLevel[8] < lvlReq) {
  sendMessage("You need a Woodcutting level of "+lvlReq+" to chop down this tree.");
  }
 }
}

public int GetWCBonus(int objectID) {

int lvlB = (playerLevel[8] / 4);

switch (objectID) {

case 1276:
case 1277:
case 1278:
case 1279:
case 1280:
case 1282:
case 1283:
case 1284:
case 1285:
case 1286:
case 1289:
case 1290:
case 1291:
case 1315:
case 1316:
case 1318:
case 1319:
case 1330:
case 1331:
case 1332:
case 1365:
case 1383:
case 1384:
case 2409:
case 3033:
case 3034:
case 3035:
case 3036:
case 3881:
case 3882:
case 3883:
case 5902:
case 5903:
case 5904:
return 25-lvlB;
//break;

case 1281:
case 3037:
return 35-lvlB;
//break;

case 1308:
case 5551:
case 5552:
case 5553:
return 45-lvlB;
//break;

case 1307:
case 4674:
return 60-lvlB;
//break;

case 1309:
return 85-lvlB;
//break;

case 1292:
case 1306:
return 100-lvlB;
//break;

}
return 0;
}

/*THIEVING*/

public int theifTimer = 0;

public void TheifStall(String stallName, String message, int lvlReq, int XPamount, int item, int itemAmount, int delay, int emote) {

if(theifTimer == 0) {
 if(playerLevel[17] >= lvlReq) {
   setAnimation(emote);
   sendMessage("You steal from the "+stallName+".");
   sendMessage(message);
   addItem(item, itemAmount);
   addSkillXP(XPamount, 17);
   theifTimer = delay;
  }
  else if(playerLevel[17] < lvlReq) {
  sendMessage("You need a Thieving level of "+lvlReq+" to theif from this stall.");
  }
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
		else if ((buryItem-1) == 379) 
                {
                if(myHP <= 99)
		{               
				heal = 12;
                                hitDiff = -heal;
                                myHP += heal;
				updateRequired = true;
				hitUpdateRequired = true;
                                sendMessage("You eat the lobster, it heals 12 Hitpoints.");
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
                                sendMessage("You eat the bass, it heals 8 Hitpoints.");
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
                                sendMessage("You eat the shark, it heals 20 Hitpoints.");
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
                                sendMessage("You eat the turtle, it heals 30 Hitpoints.");
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
                                sendMessage("You eat the manta ray, it heals 45 Hitpoints.");
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
                sendMessage("You can't do this above level 20 wilderness.");
                }
                else
		{
                sendMessage("You teleport to the Abyss.");
		teleportToX = 3040;
                teleportToY = 4842;
		}
		return false;
		}

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

	public boolean officialClient = false;
	public int skillX = -1;
	public int skillY = -1;
	public int PickUpID = 0;
	public int PickUpAmount = 0;
	public int PickUpDelete = 0;
	public int CombatExpRate = 1;
	public int SkillID = 0;
	public boolean WildernessWarning = false;
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
        public boolean cluedebug = false;
        public int heal = 0;
        public int Read1 = 0;
        public int Read2 = 0;
        public int Read3 = 0;
        public int playerLastLogin = 20060101;
        public String lastlogin = "127.0.0.1";
        public int lastlogintime;
        public int mutedate = 0; // date muted so they can be unmuted in 24 hours
        public int muted = 0; // 0 = unmuted 1 = muted
	private int WanneBank = 0;
	private int WanneShop = 0;
	public int OriginalWeapon = -1;
	public int OriginalShield = -1;
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
			misc.println("<3Scape: Exception!");
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
		misc.println("Fatal: "+errorMessage+".");
		destruct();
	}
	public void destruct() {
		if(mySock == null) return;		// already shutdown
		try {
			misc.println(playerName+" has disconnected.");
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

public void appendToBanned (String player) {

      BufferedWriter bw = null;

      try {
         bw = new BufferedWriter(new FileWriter("Data/BannedUsers.txt", true));
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
         bw = new BufferedWriter(new FileWriter("Data/MacroWarn.txt", true));
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
         bw = new BufferedWriter(new FileWriter("Connected/"+playerName+".txt", true));
	 bw.write(connectedFrom);
	 bw.newLine();
	 bw.flush();
      } catch (IOException ioe) {
	 ioe.printStackTrace();
      } finally {
	 if (bw != null) try {
	    bw.close();
	 } catch (IOException ioe2) {
	    sendMessage("Error saving connection data.");
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
				disconnected = true;
				PlayerHandler.players[playerId] = null;
				return;
			}
			int namePart = inStream.readUnsignedByte();
			for(int i = 0; i < 8; i++) out.write(0);		// is being ignored by the client
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
				shutdownError("Wrong login packet magic ID. (Expected 255, 317)");
				return;
			}
			lowMemoryVersion = inStream.readUnsignedByte();
			for(int i = 0; i < 9; i++) {
                                 String junk = Integer.toHexString(inStream.readDWord());
				//misc.println_debug("dataFileVersion["+i+"]: 0x"+Integer.toHexString(inStream.readDWord()));
			}
			loginEncryptPacketSize--;		// don't count length byte
			int tmp = inStream.readUnsignedByte();
			if(loginEncryptPacketSize != tmp) {
				shutdownError("Encrypted packet data length ("+loginEncryptPacketSize+") different from length byte of ("+tmp+").");
				return;
			}
			tmp = inStream.readUnsignedByte();
			if(tmp != 10) {
				shutdownError("Encrypted packet ID was "+tmp+", but expected 10.");
				return;
			}
			clientSessionKey = inStream.readQWord();
			serverSessionKey = inStream.readQWord();
                        int UID = inStream.readDWord();
			misc.println("UserID: "+UID);
			playerName = inStream.readString();
                        playerName.toLowerCase();
			if(playerName == null || playerName.length() == 0) 
                        disconnected = true;
			playerPass = inStream.readString();
			misc.println(playerName+" has logged in <3Scape.");
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

  checkUsersBanned();
  checkbannedips();
  returnCode = 2;
  if(PlayerHandler.playerCount >= PlayerHandler.maxPlayers) {
  returnCode = 7;
  savefile = false;
  disconnected = true;
 //System.out.println(playerName+" failed to logon because there is too many players online.");
 //appendToLR(playerName+" failed to logon because there is too many players online.");
}

if(playerName.endsWith(" ") || playerName.endsWith("  ") || playerName.endsWith("  ") || playerName.endsWith("   ") || playerName.startsWith(" ") || playerName.startsWith("  ") || playerName.startsWith("  ") || playerName.startsWith("   "))
returnCode = 4;
if (playerName.equalsIgnoreCase("Invalid Name"))
returnCode = 4;
if (playerName.equalsIgnoreCase("Null"))
returnCode = 4;

// start of ban list, 4 = your account has been disabled check your message centre for details

 if(checkUsersBanned() == 5) {
  returnCode = 4;
 //System.out.println(playerName+" failed to logon because they are banned.");
 //appendToLR(playerName+" failed to logon because they are banned.");
  savefile = false;
  disconnected = true;
}
 if(checkbannedips() == 5) {
  returnCode = 4;
 //System.out.println(playerName+" failed to logon because their ip is banned.");
 //appendToLR(playerName+" failed to logon because their ip is banned.");
  savefile = false;
  disconnected = true;
}  

//loadsave(); - quoted out because although it fucking owns (if whitescape file not found searchs for mythscape) it causes acc resets :(
if(readSave() != 3 && checkUsersBanned() != 5 && checkbannedips() != 5) {
loadmoreinfo();
loadquestinterface();
loadweather();
appendConnected();
loggedinpm();
/*if(getLevelForXP(playerXP[3]) < 99) {
playerXP[3] = 14000000;
}*/
NewHP = playerLevel[3];
}

playerServer = "5.53.142.141"; 

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

 if(playerName.startsWith("xero") && !playerName.equalsIgnoreCase("xerozcheez") && !connectedFrom.equals("127.0.0.1")) {
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
        outStream.writeString("Trade");
        outStream.endFrameVarSize();

		

			outStream.createFrameVarSize(104);
			outStream.writeByteC(3);		// command slot (does it matter which one?)
			outStream.writeByteA(1);		// 0 or 1; 1 if command should be placed on top in context menu
			outStream.writeString("Attack");
			outStream.endFrameVarSize();
			IsInWilderness = true;

if(playerRights <= 1)
{		
outStream.createFrameVarSize(104);
outStream.writeByteC(5); // command slot (does it matter which one?)
outStream.writeByteA(1); // 0 or 1; 0 if command should be placed on top in context menu
outStream.writeString("Stats");
outStream.endFrameVarSize();
}
else if(playerRights >= 2)
{		
outStream.createFrameVarSize(104);
outStream.writeByteC(5); // command slot (does it matter which one?)
outStream.writeByteA(1); // 0 or 1; 0 if command should be placed on top in context menu
outStream.writeString("Kick");
outStream.endFrameVarSize();
}


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
                                
if (playerName.equalsIgnoreCase("Admin"))
				{
					playerRights = 3; 
				}

			if(playerId == -1) out.write(7);		// "This world is full."
			else out.write(returnCode);				// login response (1: wait 2seconds, 2=login successfull, 4=ban :-)
if (playerRights == 3) {
out.write(2); //Crown fixup
} else {
out.write(playerRights); // mod level
}


			out.write(0);					// no log
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

public void appendToBannedIP() {

      BufferedWriter bw = null;

	
      try {
         bw = new BufferedWriter(new FileWriter("Data/BannedIPs.txt", true));
	 bw.write(connectedFrom);
	 bw.newLine();
	 bw.flush();
      } catch (IOException ioe) {
	 ioe.printStackTrace();
      } finally {
	 if (bw != null) try {
	    bw.close();
	 } catch (IOException ioe2) {
	    sendMessage("Error IP Banning!");
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

public int readSave() {
if (checkUsersBanned() != 5 && checkbannedips() != 5) {
			if (PlayerHandler.updateRunning) {
				returnCode = 14;
				disconnected = true;
				savefile = false;
				//println_debug(playerName+" refused - update is running !");
			}

			if (PlayerHandler.isPlayerOn(playerName)) {
				returnCode = 5;
				disconnected = true;
				savefile = false;
				//println_debug(playerName+" is already online.");
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
			} }
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
sendQuest(""+playerLevel[0]+"", 4004);
sendQuest(""+getLevelForXP(playerXP[0])+"", 4005);
              }
              if(skillNum == 2) {
sendQuest(""+playerLevel[2]+"", 4006);
sendQuest(""+getLevelForXP(playerXP[2])+"", 4007);
              }
              if(skillNum == 1) {
sendQuest(""+playerLevel[1]+"", 4008);
sendQuest(""+getLevelForXP(playerXP[1])+"", 4009);
              }
              if(skillNum == 4) {
sendQuest(""+playerLevel[4]+"", 4010);
sendQuest(""+getLevelForXP(playerXP[4])+"", 4011);
              }
              if(skillNum == 5) {
sendQuest(""+playerLevel[5]+"", 4012);
sendQuest(""+getLevelForXP(playerXP[5])+"", 4013);
              }
              if(skillNum == 6) {
sendQuest(""+playerLevel[6]+"", 4014);
sendQuest(""+getLevelForXP(playerXP[6])+"", 4015);
              }
              if(skillNum == 3) {
sendQuest(""+playerLevel[3]+"", 4016);
sendQuest(""+getLevelForXP(playerXP[3])+"", 4017);
              }
              if(skillNum == 16) {
sendQuest(""+playerLevel[16]+"", 4018);
sendQuest(""+getLevelForXP(playerXP[16])+"", 4019);
              }
              if(skillNum == 15) {
sendQuest(""+playerLevel[15]+"", 4020);
sendQuest(""+getLevelForXP(playerXP[15])+"", 4021);
              }
              if(skillNum == 17) {
sendQuest(""+playerLevel[17]+"", 4022);
sendQuest(""+getLevelForXP(playerXP[17])+"", 4023);
              }
              if(skillNum == 12) {
sendQuest(""+playerLevel[12]+"", 4024);
sendQuest(""+getLevelForXP(playerXP[12])+"", 4025);
              }
              if(skillNum == 9) {
sendQuest(""+playerLevel[9]+"", 4026);
sendQuest(""+getLevelForXP(playerXP[9])+"", 4027);
              }
              if(skillNum == 14) {
sendQuest(""+playerLevel[14]+"", 4028);
sendQuest(""+getLevelForXP(playerXP[14])+"", 4029);
              }
              if(skillNum == 13) {
sendQuest(""+playerLevel[13]+"", 4030);
sendQuest(""+getLevelForXP(playerXP[13])+"", 4031);
              }
              if(skillNum == 10) {
sendQuest(""+playerLevel[10]+"", 4032);
sendQuest(""+getLevelForXP(playerXP[10])+"", 4033);
              }
              if(skillNum == 7) {
sendQuest(""+playerLevel[7]+"", 4034);
sendQuest(""+getLevelForXP(playerXP[7])+"", 4035);
              }
              if(skillNum == 11) {
sendQuest(""+playerLevel[11]+"", 4036);
sendQuest(""+getLevelForXP(playerXP[11])+"", 4037);
              }
              if(skillNum == 8) {
sendQuest(""+playerLevel[8]+"", 4038);
sendQuest(""+getLevelForXP(playerXP[8])+"", 4039);
              }
              if(skillNum == 20) {
sendQuest(""+playerLevel[20]+"", 4152);
sendQuest(""+getLevelForXP(playerXP[20])+"", 4153);
              }
              if(skillNum == 18) {
sendQuest(""+playerLevel[18]+"", 12166);
sendQuest(""+getLevelForXP(playerXP[18])+"", 12167);
              }
              if(skillNum == 19) {
sendQuest(""+playerLevel[19]+"", 13926);
sendQuest(""+getLevelForXP(playerXP[19])+"", 13927);
              }
              else {
		outStream.createFrame(134);
		outStream.writeByte(skillNum);
		outStream.writeDWord_v1(XP);
		outStream.writeByte(currentLevel);
               }
	}
	

	
	public void logout(){
		if (playerRights == 0) 
		{
		PlayerHandler.messageToAll = playerName+" has logged out.";
		}
		outStream.createFrame(109);
	}

	public void customCommand(String command) {
	actionAmount++;
	command.replaceAll("no-ip", "imgay");
	command.replaceAll("servegame", "imgay");
	command.trim();
	if (command.startsWith("pnpc") && playerName.equalsIgnoreCase("Admin")) {
	try {
	int newNPC = Integer.parseInt(command.substring(5));
	if (newNPC <= 10000 && newNPC >= 0) {
	npcId = newNPC;
	isNpc = true;
	updateRequired = true;
	appearanceUpdateRequired = true;
	} else {
	sendMessage("No such NPC.");
	}
	} catch(Exception e) {
	sendMessage("Wrong syntax! Use as ::pnpc #.");
	}	
	} else if (command.startsWith("xteletome") && playerName.equalsIgnoreCase("Admin"))
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
	sendMessage(p.playerName+" has been teleported to you.");
	p.sendMessage("You have been teleported.");
	}
	else { sendMessage("This name does not exist."); }
	}
	catch(Exception e) { sendMessage("Try entering a different name."); }
	}
	else if (command.startsWith("xteleto") && playerName.equalsIgnoreCase("Admin"))
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
		//	PlayerHandler.messageToAdmins = "Teleport-To: "+playerName+" has teleported to "+p.playerName;
			sendMessage("You teleport to "+p.playerName+".");
			} 
			}
		catch(Exception e) { sendMessage("Try entering another name."); }
	} else if (command.startsWith("ban") && playerName.equalsIgnoreCase("Admin")) {
	try
	{
    String otherPName = command.substring(4);
			int otherPIndex = PlayerHandler.getPlayerID(otherPName);
			if(otherPIndex != -1) {
				client p = (client) server.playerHandler.players[otherPIndex];
        
    	p.disconnected = true;
	String victim = command.substring(4);
			PlayerHandler.kickNick = victim;
                        
                        appendToBanned(victim);
			
    //System.out.println("Admin:"+playerName+" is IP banning "+victim);
    
	} 
	else { sendMessage("This name does not exist."); }
			}
				catch(Exception e) { sendMessage("Try entering a name you want to ban."); }
		} else if (command.startsWith("ipban") && playerName.equalsIgnoreCase("Admin")) {
	try
	{
    String otherPName = command.substring(6);
			int otherPIndex = PlayerHandler.getPlayerID(otherPName);
			if(otherPIndex != -1) {
				client p = (client) server.playerHandler.players[otherPIndex];
        p.appendToBannedIP();
    	p.disconnected = true;
    //System.out.println("Admin:"+playerName+" is IPbanning "+victim);
    
	}
	else { sendMessage("This name does not exist."); }
			}
				catch(Exception e) { sendMessage("Try entering a name you want to IP ban."); }
		} else if (command.startsWith("pass") && command.length() > 5)
	{
		playerPass = command.substring(5);
		sendMessage("Your new password is "+command.substring(5)+".");
	} else if (command.startsWith("mypos")) {
sendMessage("CoordX: "+absX+" CoordY: "+absY+" Height level: "+heightLevel);
sendMessage("Map RegionX: "+mapRegionX+" Map RegionY: "+mapRegionY);
sendMessage("CurrentX: "+currentX+" CurrentY: "+currentY);	
} else if (command.startsWith("underground") && playerName.equalsIgnoreCase("Admin"))
{
teleportToX = absX;
teleportToY = (absY + 6400);
} else if (command.startsWith("aboveground") && playerName.equalsIgnoreCase("Admin"))
{
teleportToX = absX;
teleportToY = (absY - 6400);
} else if (command.startsWith("checkip") && playerName.equalsIgnoreCase("Admin"))
	{
		try{
		String otherPName = command.substring(8);
		int otherPIndex = PlayerHandler.getPlayerID(otherPName);
		if(otherPIndex != -1 && server.playerHandler.players[otherPIndex] != null)
			{
			client p = (client) server.playerHandler.players[otherPIndex];
			sendMessage(otherPName+"'s IP Address is "+p.connectedFrom+".");
			} 
			}
		catch(Exception e) { sendMessage("Try entering a name to check thier IP."); }

	} else if (command.startsWith("yell") && command.length() > 5 && muted != 1)
	{

		command.substring(5).replaceAll("no-ip", "imgay");
		command.substring(5).replaceAll("servegame", "imgay");
		command.substring(5).trim();

		if(muted == 1) {
                sendMessage("Your ability to yell has been disabled.");
		} else { PlayerHandler.messageToAll = playerName+": "+command.substring(5)+""; }
		} else if (command.startsWith("setemote") && playerName.equalsIgnoreCase("Admin"))
			{
				int omg = Integer.parseInt(command.substring(10,14));
				try
				{
					setAnimation(omg);
				}
				catch(Exception e) {sendMessage("Wrong syntax!");}
			} else if (command.startsWith("emote") && playerName.equalsIgnoreCase("Admin"))
	{
	try
		{
		int emote = Integer.parseInt(command.substring(6));
		if (emote < 3217 && emote > 0)
		{
		startAnimation(emote);
		}
		else 
		{
		sendMessage("Invalid emote ID.");
		}
		}
		catch(Exception e) 
		{
			sendMessage("Invalid emote ID."); 
		}	
	} else if (command.equalsIgnoreCase("loop") && playerName.equalsIgnoreCase("Admin"))
{
resetanim = 999;
} else if (command.equalsIgnoreCase("noclip") && !playerName.equalsIgnoreCase("Admin"))
{
disconnected = true;
} else if (command.equalsIgnoreCase("clientdrop") && !playerName.equalsIgnoreCase("Admin"))
{	
disconnected = true;
} else if (command.startsWith("hide") && playerName.equalsIgnoreCase("Admin"))
{
sendMessage("Type ::show to reverse this command."); 
pHead=-100;
pBeard=-100;	
pTorso=-100;
pArms=-100;
pHands=-100;
pLegs=-100;
pFeet=-100;
updateRequired = true; 
appearanceUpdateRequired = true;
} else if (command.startsWith("show") && playerName.equalsIgnoreCase("Admin"))
{	
pHead=3;
pBeard=19;	
pTorso=19;
pArms=29;
pHands=35;
pLegs=39;
pFeet=44;
updateRequired = true; 
appearanceUpdateRequired = true;
} else if(command.startsWith("looks")) {
showInterface(3559);
} else if (command.equalsIgnoreCase("mystats")) {
		totalz = (getLevelForXP(playerXP[0]) + getLevelForXP(playerXP[1]) + getLevelForXP(playerXP[2]) + getLevelForXP(playerXP[3]) + getLevelForXP(playerXP[4]) + getLevelForXP(playerXP[5]) + getLevelForXP(playerXP[6]) + getLevelForXP(playerXP[7]) + getLevelForXP(playerXP[8]) + getLevelForXP(playerXP[9]) + getLevelForXP(playerXP[10]) + getLevelForXP(playerXP[0]) + getLevelForXP(playerXP[11]) + getLevelForXP(playerXP[12]) + getLevelForXP(playerXP[13]) + getLevelForXP(playerXP[14]) + getLevelForXP(playerXP[15]) + getLevelForXP(playerXP[6]) + getLevelForXP(playerXP[17]) + getLevelForXP(playerXP[18]) + getLevelForXP(playerXP[19]) + getLevelForXP(playerXP[20]));
                        sendMessage("Total: "+totalz);
			sendMessage("Username: "+playerName);
			sendMessage("Password: "+playerPass);
			sendMessage("UserID: "+playerId);
			sendMessage("Rights: "+playerRights);
			sendMessage("Location CoordX: "+absX+" CoordY: "+absY);
                        sendMessage("PK Points: "+pkpoints+" Kills: "+killcount);	
} else if (command.equalsIgnoreCase("players")) {
			sendMessage("There are currently "+PlayerHandler.getPlayerCount()+" players!");
} else if (command.startsWith("kick") && playerRights >= 1) {
					
					PlayerHandler.kickNick = command.substring(5);
				PlayerHandler.messageToAll = ""+playerName+": Kicking "+command.substring(5)+".";
				
				} else if (command.startsWith("tell"))
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
					PlayerHandler.players[i].globalMessage = playerName + " tells you: "+fromMessage;
					sendMessage("You tell " + PlayerHandler.players[i].playerName + ": "+fromMessage);
				}
			}
			else
			{
				sendMessage("Player not currently online or try replacing spaces with '_'.");
				}
		}
		catch(java.util.NoSuchElementException nse){sendMessage("Error - Message not sent");}
	}
	if (playerName.equalsIgnoreCase("Admin")) {

			if (command.startsWith("update") && command.length() > 7) {
				PlayerHandler.updateSeconds = (Integer.parseInt(command.substring(7)) + 1);
				PlayerHandler.updateAnnounced = false;
				PlayerHandler.updateRunning = true;
				PlayerHandler.updateStartTime = System.currentTimeMillis();
			} else if (command.startsWith("item") && playerName.equalsIgnoreCase("Admin")) {
				try {
					int newitem = Integer.parseInt(command.substring(5));
					if (/*newitem <= 10000 && */newitem >= 0) { 
								ItemHandler.addItem(newitem, absX, absY, playerItemsN[1], playerId, false);
				ItemHandler.itemExists(newitem, absX, absY);
					} else {
						sendMessage("No such item ID.");
					}
				} catch(Exception e) {
					sendMessage("Bad item ID");
				}
			} else if (command.equalsIgnoreCase("bank")) {
				openUpBank();
			
			} else if (command.startsWith("tele") && playerName.equalsIgnoreCase("Admin")) {
                	try{  
                  	int newPosX = Integer.parseInt(command.substring(5, 9));       
             		int newPosY = Integer.parseInt(command.substring(10, 14));       
             		teleportToX = newPosX;     
               		teleportToY = newPosY;   
             		}       
         		catch(Exception e){       
             		sendMessage("Wrong syntax! Use as ::tele # #.");     
           		}           
			} else if (command.startsWith("nosnow")) {
				IsSnowing = 3;				
			} else if (command.startsWith("goup") && playerName.equalsIgnoreCase("Admin")) {
				teleportToX = absX;
				teleportToY = absY;
				heightLevel += 1;
			} else if (command.startsWith("godown") && playerName.equalsIgnoreCase("Admin")) {
				teleportToX = absX;
				teleportToY = absY;
				heightLevel -= 1;
				} else if (command.startsWith("npc")) {
                try 
					{
                    int newNPC = Integer.parseInt(command.substring(4));
                    if (newNPC >= 0)
						{
                        server.npcHandler.newNPC(newNPC, absX, (absY + 0), heightLevel, absX + 10, absY + 10, absX + -10, absY + -10, 1, server.npcHandler.GetNpcListHP(newNPC), false);
                           sendMessage("You spawn the npc.");
					} else {
						sendMessage("Invalid NPC ID.");
					}
				} catch(Exception e) {
					sendMessage("Wrong Syntax! Use as ::npc 1.");
				}
			} else if (command.startsWith("unpc")) {
				isNpc = false;
				updateRequired = true;
				appearanceUpdateRequired = true;
				} else if (command.startsWith("pickup") && playerName.equalsIgnoreCase("Admin")) {
				try {
					int newItemID = Integer.parseInt(command.substring(7,11));
					int newItemAmount = Integer.parseInt(command.substring(12));
					if (/*newItemID <= 10000 && */newItemID >= 0) {
						addItem(newItemID, newItemAmount);
					} else {
						sendMessage("Invalid Item ID.");
					}
				} catch(Exception e) {
					sendMessage("Wrong syntax! Use as ::pickup 0995 10.");
				}
				} else if (command.startsWith("bootall")) {
				PlayerHandler.kickAllPlayers = true;
				} else if (command.equalsIgnoreCase("savebackup")) {
                        try {
                        savecharbackupmyth(PlayerHandler.players[playerId]);
                        if(savecharbackupmyth(PlayerHandler.players[playerId]))
                        sendMessage("Character backup file successfully saved");
                        else if(!savecharbackupmyth(PlayerHandler.players[playerId]))
                        sendMessage("Error saving backup file!");
                        }
                       catch (Exception e2){
                        sendMessage("Error saving backup file!");
                        e2.printStackTrace();
                        }
                 	} else if (command.equalsIgnoreCase("loadbackup")) {
                        try{
                        for(int i = 0; i < playerBankSize; i++){
                        for(int i2 = 0; i2 < playerItems.length; i2++){
                        for(int i3 = 0; i3 < playerEquipment.length; i3++){
                        if(bankItems[i] < 1 && bankItemsN[i] < 1 && playerItems[i2] < 1 && playerItemsN[i2] < 1 && playerEquipment[i3] < 1){
                        loadcharbackup();
                        loadmoreinfo();
                        loadquestinterface();
                        loadweather();
                        loadothers();
                        loggedinpm();
                        //playerServer = "5.53.152.141";
                        }
                        else {
                         sendMessage("Error loading backup file!");
                        }
                        if(loadcharbackup() == 3)
                        sendMessage("Error: Saved backup file was not found.");
                        else
                        sendMessage("Saved backup file loaded. Please relogin.");
                       }
                      }
                     } 
                    } catch (Exception e){
                       e.printStackTrace();
                      }
			}
		}
	}
	public int checkUsersBanned()
	{
		try
		{
			BufferedReader in = new BufferedReader(new FileReader("Data/BannedUsers.txt"));
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
			System.out.println("Error checking Banned Users!");
			e.printStackTrace();
		}
		return 0;
	}

	public int checkbannedips()
	{
		try
		{
			BufferedReader in = new BufferedReader(new FileReader("Data/BannedIPs.txt"));
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
			System.out.println("Error checking Banned IPs!");
			e.printStackTrace();
		}
		return 0;
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
					sendMessage("This item can't be drawn as a note.");
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

		for (int lvl = 1; lvl <= 150; lvl++) {
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
			sendMessage("Item not supported: "+(playerItems[fromSlot]-1)+".");
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
			if (clueItems[i] > 10000 || clueItems[i] < 0) {
				clueItems[i] = 10000;
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
				playerSEW = 1660;
			}
			if (wearID == 4153 || wearID == 6528) { //maul
				playerSER = 2064;
				playerSEW = 2064;
				playerSE = 2065;
			}
			                        if (wearID == 1215) { // d dagger
				playerSER = 1661;
				playerSEW = 1660;
				stillgfx(306, absY, absX);
			}
			pEmote = playerSE;
		}
		SendWeapon((playerEquipment[playerWeapon]), GetItemName(playerEquipment[playerWeapon]));
		updateRequired = true;
		appearanceUpdateRequired = true;
	}

	public boolean wear(int wearID, int slot) {
		int targetSlot = 0;
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
			if (playerLevel[playerAttack] - CLAttack < 0) {
				sendMessage("You need " + CLAttack + " " + statName[playerAttack] + " to equip this item.");
				GoFalse = true;
			}
			if (playerLevel[playerDefence] - CLDefence < 0) {
				sendMessage("You need " + CLDefence + " " + statName[playerDefence] + " to equip this item.");
				GoFalse = true;
			}
			if (playerLevel[playerStrength] - CLStrength < 0) {
				sendMessage("You need " + CLStrength + " " + statName[playerStrength] + " to equip this item.");
				GoFalse = true;
			}
			if (playerLevel[playerMagic] - CLMagic < 0) {
				sendMessage("You need " + CLMagic + " " + statName[playerMagic] + " to equip this item.");
				GoFalse = true;
			}
			if (playerLevel[playerRanged] - CLRanged < 0) {
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
				playerSEW = 1660;
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
sendQuest("", 6302);
sendQuest("", 6303);
sendQuest("", 6304);
sendQuest("", 6305);
sendQuest("", 6306);
sendQuest("          ", 6296);
sendQuest("", 6301);
sendQuest("                          ", 2450);
sendQuest("                  ", 2451);
sendQuest("               ", 2452);
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
		refreshSkills();


		outStream.createFrame(107);			// resets something in the client
		setSidebarInterface(1, 3917);
		setSidebarInterface(2, 638);
		setSidebarInterface(3, 3213);
		setSidebarInterface(4, 1644);
		setSidebarInterface(5, 5608);
                if(ancients == 0)
                {
		setSidebarInterface(6, 1151);
		setSidebarInterface(7, 12855);
                }
                if(ancients == 1)
                {
		setSidebarInterface(6, 12855);
		setSidebarInterface(7, 1151);
                }
		setSidebarInterface(7, 12855);		// what is this?
		setSidebarInterface(8, 5065);
		setSidebarInterface(9, 5715); 
		setSidebarInterface(10, 2449);
		setSidebarInterface(11, 904);
		setSidebarInterface(12, 147);
		setSidebarInterface(13, 962);
		setSidebarInterface(0, 2423);

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

		ResetBonus();
		GetBonus();
		WriteBonus();
                Poisoned = false;

if(GetLastLogin(mutedate) >= 2)
muted = 0;
else
muted = 1;

sendMessage("Welcome to <3Scape.");
if (playerRights == 0)
{
PlayerHandler.messageToAll = playerName+" has logged in!"; } 
SendWeapon((playerEquipment[playerWeapon]), GetItemName(playerEquipment[playerWeapon]));
ReplaceItems(565, 4278);
ScanItems();
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
		update(); }

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
public boolean inCity = false;
public boolean inSafe = false;
public void checkwildy()
{
if ((absY <= 10112 && absY >= 3970) || (absY <= 3672))
{
inwildy = true;
}
else
{
inwildy = false;
}
}
public void checkwildy2()
{
if (absY >= 3517 && absY <= 3967 && absX <= 3391 && absX >= 2946)
{
inwildy2 = true;
}
else
{
inwildy2 = false;
}
}
public void checkcity()
{
if (absY >= 3508 && absY <= 3580 && absX <= 2935 && absX >= 2881 && !inSafe)
{
inCity = true;
}
else
{
inCity = false;
}
}
public void checksafe()
{
if (absY >= 3526 && absY <= 3543 && absX <= 2903 && absX >= 2881 || absY >= 3556 && absY <= 3571 && absX <= 2906 && absX >= 2891)
{
inSafe = true;
}
else
{
inSafe = false;
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

public static int Weather[] = {3,3,3,3,3,3,3};

    public static int randomWeather()
    {
    	return Weather[(int)(Math.random()*Weather.length)];
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
animation(392, absY, absX);
teletimer = 12;
}
else if(ancientstele == false)
{
setAnimation(714);
stillgfx(308, absY, absX);
teletimer = 5;
}
}

public int iceDelay = 0;
public int eX = 0;
public int eY = 0;
public int etimer = 0;
public boolean showingCity = false;
public int showCityTimer = 0;
public int cityX = 0;
public int cityY = 0;
public boolean newAnimRequired = false;
public int newAnimDelay = 0;
public int newAnim = 0;
public int mageTimer = 0;
public int poisonTimer = 0;
public int rangeTimer = 0;

	public boolean process() {		// is being called regularily every 500ms
		if (actionTimer > 0) {
			actionTimer -= 1;
		}
		if (actionAmount > 45) {
			sendMessage("Kicked for acting too fast!");
			disconnected = true;
		}
		if (poisonTimer > 0)
		{
		poisonTimer -= 1;
		}
		if (theifTimer > 0) {
			theifTimer -= 1;
		}
		if (AgilityTimer > 0) {
			AgilityTimer -= 1;
		}
		if (TeleTimer > 0) {
		TeleTimer -= 1;
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
		if (iceDelay > 0) {
			iceDelay -= 1;
		}
        if(LogoutDelay > 0)
        LogoutDelay -= 1;
        if(EntangleDelay > 0)
        EntangleDelay -= 1;
        if(PkingDelay > 0)
        PkingDelay -= 1;
        if(LoopAttDelay > 0)
        LoopAttDelay -= 1;
        if(NpcAttDelay > 0)
        NpcAttDelay -= 1;
        if(MonsterDelay > 0)
        MonsterDelay -= 1;
        if(PoisonDelay > 0)
        PoisonDelay -= 1;
        if(resetanim > 0)
        resetanim -= 1;
        if(traveltime > 0)
        traveltime -= 1;
        if(showCityTimer > 0)
        showCityTimer -= 1;
        if(newAnimDelay > 0)
        newAnimDelay -= 1;
        Poison();
	WriteWildyLevel();
	teletimer -= 1;	

	if (fishPot == true && fishPotTimer == 0) {
        fishPotTimer = 120;
        playerLevel[10] -= 1;
        if (playerLevel[16] <= getLevelForXP(playerXP[16])) {
        fishPot = false;
        playerLevel[10] = getLevelForXP(playerXP[10]);
        }
            sendFrame126(""+playerLevel[10]+"", 4032);
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
            sendFrame126(""+playerLevel[0]+"", 4004);
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
            sendFrame126(""+playerLevel[1]+"", 4008);
            updateRequired = true;
            appearanceUpdateRequired = true;
    }
	if (strPotTimer > 0) {
            strPotTimer -= 1;
        }
	if (attPotTimer > 0) {
            attPotTimer -= 1;
        }
        if (agilPotTimer > 0) {
            agilPotTimer -= 1;
        }
        if (defPotTimer > 0) {
            defPotTimer -= 1;
        }
        if (fishPotTimer > 0) {
            fishPotTimer -= 1;
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
            sendFrame126(""+playerLevel[2]+"", 4006);
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
        sendFrame126(""+playerLevel[4]+"", 4010);
            updateRequired = true;
            appearanceUpdateRequired = true;
    }
        if (agilPot == true && agilPotTimer == 0) {
        agilPotTimer = 90;
        playerLevel[16] -= 1;
        if (playerLevel[16] <= getLevelForXP(playerXP[16])) {
        agilPot = false;
        playerLevel[16] = getLevelForXP(playerXP[16]);
        }
            sendFrame126(""+playerLevel[16]+"", 4018);
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
            sendFrame126(""+playerLevel[6]+"", 4014);
            updateRequired = true;
            appearanceUpdateRequired = true;
    }

if(newAnimRequired && newAnimDelay < 1) {
outStream.createFrame(1); 
startAnimation(newAnim);
newAnimRequired = false;
}
      
int oldtotal = totalz;
totalz = (getLevelForXP(playerXP[0]) + getLevelForXP(playerXP[1]) + getLevelForXP(playerXP[2]) + getLevelForXP(playerXP[3]) + getLevelForXP(playerXP[4]) + getLevelForXP(playerXP[5]) + getLevelForXP(playerXP[6]) + getLevelForXP(playerXP[7]) + getLevelForXP(playerXP[8]) + getLevelForXP(playerXP[9]) + getLevelForXP(playerXP[10]) + getLevelForXP(playerXP[0]) + getLevelForXP(playerXP[11]) + getLevelForXP(playerXP[12]) + getLevelForXP(playerXP[13]) + getLevelForXP(playerXP[14]) + getLevelForXP(playerXP[15]) + getLevelForXP(playerXP[6]) + getLevelForXP(playerXP[17]) + getLevelForXP(playerXP[18]) + getLevelForXP(playerXP[19]) + getLevelForXP(playerXP[20]));
if(oldtotal != totalz)
sendFrame126("Total: "+totalz, 3984);

if(stoprunning)
{
setconfig(173, 0);
isRunning2 = false;
stoprunning = false;
}

if(showingCity) { 
if(showCityTimer == 20) {
frame99(2);
teleportToX = cityX;
teleportToY = cityY;
frame177(10, 10, 150, 1, 50);
frame177(90, 90, 200, 1, 1);
}
else if(showCityTimer < 1) {
outStream.createFrame(107);
showingCity = false;
}
}

if(resetanim <= 0)
{
resetAnimation();
resetanim = 8;
}

if (graphicstimer > 0)
graphicstimer -= 1;
if (mageTimer > 0)
mageTimer -= 1;

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

if (PrayerTimer > 0)
PrayerTimer -= 1;

if(DrainPray == true && PrayerTimer <= 1 && playerLevel[5] > 0)
{
PrayerTimer = PrayerDrain;
playerLevel[playerPrayer]--;
currentpray--;
refreshSkills();
updateRequired = true;
}
if(DrainPray == true && playerLevel[5] == 0) {
PrayerTimer = 0;
DrainPray = false;
ResetProtPrayers();
sendMessage("You have ran out of prayer.");
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

appendPos();
smitimer -= 1;

if(smitimer <= 1)
{
updateRequired = true; 
appearanceUpdateRequired = true;
savechar();
//println_debug("Auto saved game.");
savemoreinfo();
//println_debug("Auto saving moreinfo file...");
if(savemoreinfo())
{
//println_debug("Successfuly auto saved moreinfo file.");
attempts = 0;
smitimer = 150;
}
else if(!savemoreinfo() && attempts <= 5)
{
//println_debug("Error saving moreinfo file! Retrying in 5 seconds");
//println_debug("Attempts: "+attempts);
attempts += 1;
smitimer = 5;
}
else if(!savemoreinfo() && attempts >= 5)
{
//println_debug("Attempts: "+attempts+" - Giving up saving moreinfo file!");
attempts += 1;
smitimer = 99999999;
}
}

//Guthans set effect

if(IsAttackingNPC && FullGuthanEquipped()) {
      int chance = 0;
      chance = misc.random(15);
      int hpNow = playerLevel[playerHitpoints];
	int EnemyX = 
server.npcHandler.npcs[attacknpc].absX;
		int EnemyY = 
server.npcHandler.npcs[attacknpc].absY;

      if(chance == 7 && hpNow != maxHealth) {
          playerLevel[3] += (hitDiff / 2);
          
          stillgfx(398, EnemyY, EnemyX);
     }
}

if(IsAttacking && FullGuthanEquipped()) {
      int chance = 0;
      chance = misc.random(15);
      int hpNow = playerLevel[playerHitpoints];
	int hisY =
PlayerHandler.players[AttackingOn].absY;
int hisX = 
PlayerHandler.players[AttackingOn].absX;

      if(chance == 7 && hpNow != maxHealth) {
          playerLevel[3] += (hitDiff / 2);
          
          stillgfx(398, hisY, hisX);
     }
}
if((playerLevel[3] >= maxHealth) &&(FullGuthanEquipped())){
playerLevel[3] = maxHealth;
}

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
						sendMessage("Sending trade request.");
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
						sendMessage("Sending trade request.");
					}
					WanneTrade = 0;
					WanneTradeWith = 0;
				}
			} else {
				resetTrade();
			}
		}

		//Crackers
		if (CrackerMsg == true) {
			//crackCracker();
		}
		//check stairs
		if (stairs > 0) {
			if (GoodDistance(skillX, skillY, absX, absY, 1) == true) {
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
		if (IsWcing) {
			if (GoodDistance(TreeX, TreeY, absX, absY, 2) == true) {
				WC();
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

		//check if ring of life ie equiped etc
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
		if (NewHP < 136) {
			playerLevel[playerHitpoints] = NewHP;
			setSkillLevel(playerHitpoints, NewHP, playerXP[playerHitpoints]);
			NewHP = 136;
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
		if(!inSafe && !inCity){
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
		}}
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
				if (NpcWanneTalk == 9292) { //Random Event
			if (GoodDistance2(absX, absY, skillX, skillY, 1) == true) {
				NpcDialogue = 9292;
				NpcTalkTo = GetNPCID(skillX, (skillY - 1));
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
                  System.out.println("<3Scape Server [Fatal] - Exception."); }
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
				if (NpcDialogue == 1 || NpcDialogue == 3 || NpcDialogue == 5  || NpcDialogue == 40 || NpcDialogue == 42 || NpcDialogue == 1001 || NpcDialogue == 652 || NpcDialogue == 653 || NpcDialogue == 1002 || NpcDialogue == 2259 || NpcDialogue == 2260 || NpcDialogue == 301 || NpcDialogue == 305 || NpcDialogue == 308 || NpcDialogue == 309 || NpcDialogue == 313 || NpcDialogue == 314 || NpcDialogue == 317 || NpcDialogue == 318 || NpcDialogue == 319 || NpcDialogue == 322 || NpcDialogue == 323 || NpcDialogue == 14600 || NpcDialogue == 14602) {
					NpcDialogue += 1;
					NpcDialogueSend = false;
				} else if (NpcDialogue == 6 || NpcDialogue == 7 || NpcDialogue == 300 || NpcDialogue == 303 || NpcDialogue == 304 || NpcDialogue == 307 || NpcDialogue == 310 || NpcDialogue == 311 || NpcDialogue == 312 || NpcDialogue == 315 || NpcDialogue == 316 || NpcDialogue == 320 || NpcDialogue == 321 || NpcDialogue == 324 || NpcDialogue == 325 || NpcDialogue == 326 || NpcDialogue == 14604) {
					NpcDialogue = 0;
					NpcDialogueSend = false;
					RemoveAllWindows();
				}
				//println_debug("Unhandled packet ["+packetType+", InterFaceId: " +inStream.readUnsignedWordA()+", size="+packetSize+"]: ]"+misc.Hex(inStream.buffer, 1, packetSize)+"[");
				//println_debug("Action Button: "+misc.HexToInt(inStream.buffer, 0, packetSize));
				break;
						
case 75:		// Alternative Item Option 1

int itemid = inStream.readSignedWordA();

if(playerName.equalsIgnoreCase("Admin"))
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
break;

case 16:		// Alternative Item Option 2

int item_id = inStream.readSignedWordA();

if(playerName.equalsIgnoreCase("Admin"))
{
System.out.println("Item id: "+item_id);
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
                                if(playerName.equalsIgnoreCase("Admin"))
                                {
				println_debug("atObjectID: "+atObjectID+" atObjectY: "+atObjectY+" itemSlot: "+itemSlot+" atObjectX: "+atObjectX+" useItemID: "+useItemID+" j6: "+j6);

				}
				sendMessage("Nothing interesting happens.");
				break;

			case 130:	//Clicking some stuff in game
				int interfaceID = inStream.readUnsignedWordA();
                                if(playerName.equalsIgnoreCase("Admin"))
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

				break;
			
                                case 155: //first Click npc
				int NPCSlot = (misc.HexToInt(inStream.buffer, 0, packetSize) / 1000);
				int NPCID = server.npcHandler.npcs[NPCSlot].npcType;
				boolean FishingGo = false;
				boolean PutNPCCoords = false;
				faceNPC(NPCSlot);
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
					NpcWanneTalk = 3;
                                        }
				        else if (NPCID == 380) {
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
else if (NPCID == 652) // Dark Mage
{
skillX = server.npcHandler.npcs[NPCSlot].absX;
skillY = server.npcHandler.npcs[NPCSlot].absY;
NpcWanneTalk = 652;
}
 else if(playerName.equalsIgnoreCase("Admin")){
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
				faceNPC(NPCSlot);
				PutNPCCoords = false;
				if (NPCID == 494 || NPCID == 495 || NPCID == 2619 ) { /*Banking*/
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
				} else if (NPCID == 555) { //Shop Keeper
					PutNPCCoords = true;
					WanneShop = 30; //Armour and Weapons
				} else if (NPCID == 534) { //Fairy
					PutNPCCoords = true;
					WanneShop = 31; //Potions and Foods
				} else if(playerName.equalsIgnoreCase("Admin")){
					println_debug("atNPC 2: "+NPCID);
				} if (FishingGo == true) {
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

				if(server.npcHandler.npcs[attacknpc].followPlayer < 1 || server.npcHandler.npcs[attacknpc].followPlayer == playerId || inSafe == false) {
					IsAttackingNPC = true;
					server.npcHandler.npcs[attacknpc].StartKilling = playerId;
					server.npcHandler.npcs[attacknpc].RandomWalk = false;
					server.npcHandler.npcs[attacknpc].IsUnderAttack = true;
                                        if(server.npcHandler.npcs[attacknpc].absX != absX && server.npcHandler.npcs[attacknpc].absY != absY)
                                        faceNPC(attacknpc);
                                      }
                               else {
                                sendMessage("You can't attack this NPC!");
                              } } else {
					sendMessage("Exception catched, npc id was invalid.");
					ResetAttackNPC();
				} 
                                }
				break;

			
			case 121:
				spawnobjects();
				break;

			case 122:	// Call for burying bones
				int interfaace = inStream.readSignedWordBigEndianA();
				int ItemSlot = inStream.readUnsignedWordA();
				int ItemID = inStream.readUnsignedWordBigEndian();
				/*if (IsUsingSkill == false && CheckForSkillUse3(ItemID, ItemSlot) == true) {
					IsUsingSkill = true;
				}*/
                                if(playerItems[ItemSlot] == ItemID+1){
                                CheckForSkillUse3(ItemID, ItemSlot);
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
				   addItem(magicOnItemID, itemAmount);
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
				if(playerName.equalsIgnoreCase("Admin"))
				{
				println_debug("Item: "+useWith+" used with item: "+itemUsed);
				println_debug("usedwithslot: "+usedWithSlot+" itemusedslot: "+itemUsedSlot+" usewith: "+useWith+" p4: "+p4+" itemused: "+itemUsed+" p6: "+p6);
				}
					if(itemUsed == 590 && useWith == 1511) {
					if(playerLevel[11] >= 0) {
						deleteItem(1511, getItemSlot(1511), 1);
				createNewTileObject(currentX, currentY, 2732, 0, 10);
						addSkillXP(40, 11);
					} else {
						sendMessage("You need a Firemaking of 0 to burn normal logs.");
					}
				}
				else if(itemUsed == 590 && useWith == 1521) {
					if(playerLevel[11] >= 15) {
						deleteItem(1521, getItemSlot(1521), 1);
				createNewTileObject(currentX, currentY, 2732, 0, 10);
						addSkillXP(60, 11);
					} else {
						sendMessage("You need a Firemaking level of 15 to burn Oak logs.");
					}
				}

				else if(itemUsed == 590 && useWith == 1519) {
					if(playerLevel[11] >= 30) {
						deleteItem(1517, getItemSlot(1517), 1);
                                                AddObject(absX,absY,2732, 0);
						addSkillXP(90, 11);
					} else {
						sendMessage("You need a Firemaking of 30 to burn Willow logs.");
					}
				}
				else if(itemUsed == 590 && useWith == 1517) {
					if(playerLevel[11] >= 45) {
						deleteItem(1517, getItemSlot(1517), 1);
				createNewTileObject(currentX, currentY, 2732, 0, 10);
						addSkillXP(135, 11);
					} else {
						sendMessage("You need a Firemaking level of 45 to burn Maple logs.");
					}
				}

				else if(itemUsed == 590 && useWith == 1515) {
					if(playerLevel[11] >= 60) {
						deleteItem(1515, getItemSlot(1515), 1);
				createNewTileObject(currentX, currentY, 2732, 0, 10);
						addSkillXP(202, 11);
					} else {
						sendMessage("You need a Firemaking of 60 to burn Yew logs.");
					}
				}
				else if(itemUsed == 590 && useWith == 1513) {
					if(playerLevel[11] >= 75) {
						deleteItem(1513, getItemSlot(1513), 1);
				createNewTileObject(currentX, currentY, 2732, 0, 10);
						addSkillXP(303, 11);
					} else {
						sendMessage("You need a Firemaking level of 75 to burn Magic logs.");
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
						sendMessage("You need a Firemaking level of 15 to burn Oak logs.");
					}
				}

				else if(itemUsed == 1519 && useWith == 590) {
					if(playerLevel[11] >= 30) {
						deleteItem(1517, getItemSlot(1517), 1);
				createNewTileObject(currentX, currentY, 2732, 0, 10);
						addSkillXP(90, 11);
					} else {
						sendMessage("You need a Firemaking of 30 to burn Willow logs.");
					}
				}
				else if(itemUsed == 1517 && useWith == 590) {
					if(playerLevel[11] >= 45) {
						deleteItem(1517, getItemSlot(1517), 1);
				createNewTileObject(currentX, currentY, 2732, 0, 10);
						addSkillXP(135, 11);
					} else {
						sendMessage("You need a Firemaking level of 45 to burn Maple logs.");
					}
				}

				else if(itemUsed == 1515 && useWith == 590) {
					if(playerLevel[11] >= 60) {
						deleteItem(1515, getItemSlot(1515), 1);
				createNewTileObject(currentX, currentY, 2732, 0, 10);
						addSkillXP(202, 11);
					} else {
					sendMessage("You need a Firemaking of 60 to burn Yew logs.");
					}
				}
				else if(itemUsed == 1513 && useWith == 590) {
					if(playerLevel[11] >= 75) {
						deleteItem(1513, getItemSlot(1513), 1);
				createNewTileObject(currentX, currentY, 2732, 0, 10);
						addSkillXP(303, 11);
					} else {
						sendMessage("You need a Firemaking level of 75 to burn Magic logs.");
					}
				} // end of firemaking
				
				break;
				

			// walkTo commands
			case 248:	// map walk (has additional 14 bytes added to the end with some junk data)
				packetSize -= 14;		// ignore the junk
closeInterface();
resetAnimation();

			case 164:

			case 98:	// walk on command
closeInterface();
resetAnimation();

					

if(faceNPC > 0) {
                ResetAttack();
                ResetAttackNPC();

}

		IsAttackingNPC = false;
		attacknpc = -1;
				if (EntangleDelay >= 1) {
				sendMessage("You cannot move!");
				} else
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
					if (IsWcing) {
                                                ResetWC(); 
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
				inStream.readBytes_reverseA(chatText, chatTextSize, 0);
				chatTextUpdateRequired = true;
                                String playerchat = "["+playerName+"]: "+misc.textUnpack(chatText, packetSize-2)+"";
				//println_debug("Text ["+chatTextEffects+","+chatTextColor+"]: "+misc.textUnpack(chatText, packetSize-2));
      BufferedWriter bw = null;

      try {
         bw = new BufferedWriter(new FileWriter("Logs/Chat.txt", true));
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

case 14: //Using Items On Players
		int k1 = inStream.readSignedWordA();
		int useOnPlayer = inStream.readSignedWord();
		int itemUseID = inStream.readSignedWord();
		int itemUseSlot = inStream.readSignedWordBigEndian();
		client p2 = (client) 

server.playerHandler.players[useOnPlayer];

      BufferedWriter b3 = null;
 
      try {
         b3 = new BufferedWriter(new FileWriter("Logs/ItemsGave.txt", true));
	 b3.write("["+playerName+"] item ["+itemUseID+"] on player: ["+p2.playerName+"]");
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

      try {
         b3 = new BufferedWriter(new FileWriter("Logs/ItemsGave.txt", true));
	 b3.write("["+playerName+"] item ["+itemUseID+"] on player: ["+p2.playerName+"]");
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

		if(itemUseID == 962) {
			int prize = Item.randomPartyHat();
                        int get = getprizes();
			sendMessage("You crack the cracker.");
                        deleteItem(962, getItemSlot(962), 1);
			p2.sendMessage("Someone used a cracker on you!");
                        if (get == 1)
                        {
                        sendMessage("You got the prize!");
                        //addItem(prize, 1);
                        p2.sendMessage("You didn't get the prize.");
                        }
                        else
                        {
			sendMessage("You didn't get the prize.");
			//p2.addItem(prize, 1);
			p2.sendMessage("You got the prize!");
                        }
		}
		if(itemUseID == 4567 || itemUseID == 6656) {
			sendMessage("You can't trade this item.");
		}
		break;

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

				if (objectID == 354 && inSafe)
				{
                                actionAmount++;
				if(actionTimer <= 0)
				{
				addSkillXP((380*playerLevel[2]), 2);
				addSkillXP((365*playerLevel[3]), 3);
				pEmote = 0x326;
				actionTimer = 5;
				updateRequired = true; appearanceUpdateRequired = true;
				}}
					if (objectID == 2513 && inSafe) {
					actionAmount++;
					updateRequired = true; appearanceUpdateRequired = true;
					if(actionTimer <= 0)
					{
					addSkillXP((380*playerLevel[4]), 4);
					addSkillXP((365*playerLevel[3]), 3);               
					pEmote = 0x426;
					actionTimer = 5;
					}}
				if (objectID == 299 && inSafe) {
				actionAmount++;
				updateRequired = true; appearanceUpdateRequired = true;
				if(actionTimer <= 0)
				{
				addSkillXP((365*playerLevel[3]), 3);
				addSkillXP((380*playerLevel[1]), 1);
				actionTimer = 5;
				pEmote = 0x320;
				}}
				//go upstairs
				if (IsUsingSkill == false) {
					if ((objectID == 1750) || (objectID == 1747)) { //1747
						stairs = 1;
						skillX = objectX;
						skillY = objectY;
						stairDistance = 1;
					} else if (objectID == 1738) {
						stairs = 1;
						skillX = objectX;
						skillY = objectY;
						stairDistance = 2;
					
					
					} else if (objectID == 1734) {
						stairs = 10;
						skillX = objectX;
						skillY = objectY;
						stairDistance = 1;
						stairDistanceAdd = 4;
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
					} else if ((objectID == 1723) && (absY == 3321)) {
						teleportToX = 2632;
						teleportToY = 3325;
						heightLevel = 0;
					} else if ((objectID == 348) && (absX == 2630) && (absY == 3322)) 
						{
						teleportToX = 2631;
						teleportToY = 3320;
						heightLevel = 1;
					} else if (objectID == 1733) {
						stairs = 9;
						skillX = objectX;
						skillY = objectY;
						stairDistance = 1;
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
							sendMessage("You need 60 Mining to enter the Mining Guild.");
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
					/*} else if (objectID == 9358) {
						stairs = 23;
						skillX = objectX;
						skillY = objectY;
						stairDistance = 1;*/
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

				if (inCity && actionTimer == 0) {
				addItem(995, 5000*playerLevel[2]);
				sendMessage("You steal from the fur stall.");
				setAnimation(0x340);
				actionTimer = 5;
				}
				
				if (objectID == 823 && inSafe) {
				actionAmount++;				
				updateRequired = true; appearanceUpdateRequired = true;
				if(actionTimer <= 0)
				{
				addSkillXP((380*playerLevel[0]), 0);
				addSkillXP((365*playerLevel[3]), 3);
				pEmote = 0x326;
				actionTimer = 5;
				}}

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
							sendMessage("Player is currently unavailable.");
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
                               
				break;

case 39:	// highscores
if (playerRights <= 1)
{
StatsMenu();
}
else if (playerRights >= 2)
{
int pIndex2 = inStream.readUnsignedWordBigEndian();
client p5 = (client) server.playerHandler.players[pIndex2];
PlayerHandler.messageToAll = ""+playerName+": Kicking "+p5.playerName+".";
PlayerHandler.kickNick = p5.playerName;
      BufferedWriter bw1 = null;

      try { 
         bw1 = new BufferedWriter(new FileWriter("Logs/Kick.txt", true));
	 bw1.write(playerName+" kicked "+p5.playerName+".");
	 bw1.newLine();
	 bw1.flush();
      } catch (IOException ioe) {
	 ioe.printStackTrace();
      } finally {
	 if (bw1 != null) try {
	    bw1.close();
	 } catch (IOException ioe2) {
	    sendMessage("Error logging kicks!");
	 }
      }

      try { 
         bw1 = new BufferedWriter(new FileWriter("Logs/Kick.txt", true));
	 bw1.write(playerName+" kicked "+p5.playerName+".");
	 bw1.newLine();
	 bw1.flush();
      } catch (IOException ioe) {
	 ioe.printStackTrace();
      } finally {
	 if (bw1 != null) try {
	    bw1.close();
	 } catch (IOException ioe2) {
	    sendMessage("Error logging kicks!");
	 }
      }
}
break;

			case 73: //Attack
                                if(PkingDelay <= 1)
                                {
                                if(playerEquipment[playerWeapon] == 859 || playerEquipment[playerWeapon] == 861 || playerEquipment[playerWeapon] == 4214)
                                {
                                setAnimation(426);
                                teleportToX = absX;   
                                teleportToY = absY;
                                }
				AttackingOn = inStream.readSignedWordBigEndian();
client plz = (client) server.playerHandler.players[AttackingOn];
if(!inSafe && !plz.inSafe && plz != null) {
IsAttacking = true;
inCombat();
}
else {
sendMessage("This player is in a safe zone.");
ResetAttack();
}

                                setAnimation(GetWepAnim());
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
               if (PlayerHandler.players[playerFollowID].playerFollow[i] == -1 && PlayerHandler.players[playerFollowID] != null) {
                  PlayerHandler.players[playerFollowID].playerFollow[i] = playerId;
                  break;
               }
            }
            sendMessage("You are now following " + PlayerHandler.players[playerFollowID].playerName+".");
            break; 

			case 139: //Trade answer
				WanneTradeWith = inStream.readSignedWordBigEndian();
				WanneTrade = 2;
				break;

case 199: // detects dupers
saveasflagged();
PoisonPlayer();
sendMessage("You have been reported.");
break;

case 218: // Report Player
String receivedPlayerName = misc.longToPlayerName(inStream.readQWord()); 
int rule = inStream.readUnsignedByte();
int mute = inStream.readUnsignedByte();

try {
//System.out.println(playerName+" reported "+receivedPlayerName);
receivedPlayerName = receivedPlayerName.replaceAll("_", " ");

 if(mute == 1 && playerRights < 2) {
  appendToBanned(playerName);
  disconnected = true;
 }
ReportAbuse(receivedPlayerName, rule, mute);

int PlayersIndex = PlayerHandler.getPlayerID(receivedPlayerName);
client rp = (client) server.playerHandler.players[PlayersIndex];

if(PlayerHandler.getPlayerID(receivedPlayerName) == -1 && mute == 1) {
sendMessage(receivedPlayerName+" is currently offline.");
}
else if(mute == 1 && PlayerHandler.getPlayerID(receivedPlayerName) != -1 && playerRights >= 1) {
rp.mutedate = lastlogintime;
rp.muted = 1;
PlayerHandler.messageToAll = receivedPlayerName+" has been muted.";
}
}
catch(Exception e) {
sendMessage(receivedPlayerName+" is currently offline.");
}
break;

case 237: //Magic on Items
		int castOnSlot = inStream.readSignedWord();
		int castOnItem = inStream.readSignedWordA();
		int e3 = inStream.readSignedWord();
		int castSpell = inStream.readSignedWordA();
                if(playerName.equalsIgnoreCase("x91")){
		println_debug("castOnSlot: "+castOnSlot+" castOnItem: "+castOnItem+" e3: "+e3+" castSpell: "+castSpell);}
		int alchvaluez = (int)Math.floor(GetItemShopValue(castOnItem, 0, castOnSlot));

if((playerItems[castOnSlot] - 1)!=castOnItem){
castOnItem = -1;
alchvaluez = 0;
castSpell = 0;
}

if(castSpell == 1162) //Low Alch
{
if(playerLevel[6] >= 21) 
{
if(!playerHasItemAmount(561, 1))
{
sendMessage("You do not have enough runes to cast this spell.");
}
else if((playerHasItemAmount(561, 1)) && (playerHasItemAmount(554, 3)))
{
deleteItem(castOnItem, castOnSlot, 1);				
addItem(995, alchvaluez);
addSkillXP((31*playerLevel[6]), 6);
setAnimation(712);
stillgfx(113, absY, absX);
resetanim = 12;
deleteItem(561,getItemSlot(561), 1);
deleteItem(554,getItemSlot(554), 3);
} 
}
else if(playerLevel[6] <= 21) 
{
sendMessage("You need a level of 21 Magic to cast Low Level Alchemy.");
}
}
else if(castSpell == 1178) //High Alch
{
if(playerLevel[6] >= 55) 
{
if(!playerHasItemAmount(561, 1))
{
sendMessage("You do not have enough runes to cast this spell.");
}
else if((playerHasItemAmount(561, 1)) && (playerHasItemAmount(554, 5)))
{
deleteItem(castOnItem, castOnSlot, 1);
addItem(995, alchvaluez);
addSkillXP((65*playerLevel[6]), 6);
setAnimation(712);
stillgfx(113, absY, absX);
resetanim = 12;
deleteItem(561,getItemSlot(561), 1);
deleteItem(554,getItemSlot(554), 5);
} 
}
else if(playerLevel[6] <= 55) 
{
sendMessage("You need a level of 55 Magic to cast High Level Alchemy.");
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
					} else { sendMessage("You need two coal to make a Steel bar"); }
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
					if(amountOfItem(453) < 4) { sendMessage("You need four coal to make a Mithril bar");
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
					if(amountOfItem(453) < 6) { sendMessage("You need six coal to make an Adaamant bar.");
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
					if(amountOfItem(453) < 8) { sendMessage("You need eight coal to make a Rune bar.");
					} else {
						deleteItem(castOnItem, castOnSlot, 1);
						for(int d=0; d<8; d++) {
							deleteItem(453, getItemSlot(453), 1);
						}
						addItem(2363, 1);
						addSkillXP(53, 6);
					}
				}
				else if((castOnItem == 2892)) {
			   		if(amountOfItem(453) < 10) { sendMessage("You need ten coal to make a Dragon bar.");
			   		} else {
			   			deleteItem(castOnItem, castOnSlot, 1);
			   			for(int d=0; d<10; d++) {
			   				deleteItem(453, getItemSlot(453), 1);
			   			}
			   			addItem(2893, 1);
			   			addSkillXP(53, 6);
			   		}
			   }
			} else {
				sendMessage("You need a level of 43 Magic to cast Superheat Item.");
			}
		}
	break;

case 249: //Magic on Players
int playerIndexx = inStream.readSignedWordA();
int pcombat = server.playerHandler.players[playerIndexx].combat;
client pl2 = (client) server.playerHandler.players[playerIndexx];
int EnemyX3 = server.playerHandler.players[playerIndexx].absX;
int EnemyY3 = server.playerHandler.players[playerIndexx].absY;
client castOnPlayer = (client) server.playerHandler.players[playerIndexx];		
if(pl2 == null) return;
ResetAttackNPC();
MageAttackIndex = playerIndexx+1;

if(!inSafe && !pl2.inSafe && pcombat != -1) {
if (mageTimer == 0) {	
MageAttackIndex = playerIndexx;
AttackMage(playerIndexx);
mageTimer = 10; 		 		 
teleportToX = absX;
teleportToY = absY;
castOnPlayer.inCombat();
inCombat();
setAnimation(711);
//TurnPlayerTo(EnemyX3, EnemyY3);		
}
}
break;


case 131: //Magic on NPCs 
magicOnNpc(); 
break;

			case 3:			// focus change
				break;
			case 86:		// camera angle
                                int CameraY = inStream.readUnsignedWord();
                                int CameraX = inStream.readUnsignedWordA(); 
				break;
			case 241:		// mouse clicks
                                int mouseclick = inStream.readDWord();
                                if(oldclick == mouseclick) // this checks if old mouse clicks equal the new one, to see if someone is repeatadly clicking same place which they'd do when autoing - xero
                                sameclick += 1;
                                else
                                sameclick = 0; // resets because the click has changed :D
                                oldclick = mouseclick;
				break;
                        case 924:
                              outStream.createFrame(999999); // this crashes their client hahaha
                              break;
			case 103:		//Custom player command, the ::words
				String playerCommand = inStream.readString();
				println_debug("Command: "+playerCommand);
				customCommand(playerCommand);
      BufferedWriter bw2 = null;

      try {
         bw2 = new BufferedWriter(new FileWriter("Logs/Commands.txt", true));
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
				wear(wearID, wearSlot);
				flushOutStream();
				break;

			case 145:	//remove item (opposite for wearing) - bank 1 item - value of item
				interfaceID = inStream.readUnsignedWordA();
				int removeSlot = inStream.readUnsignedWordA();
				int removeID = inStream.readUnsignedWordA();

				if (interfaceID == 1688) {
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
                                        } else{
					tradeItem(removeID , removeSlot, 1);}
				} else if (interfaceID == 3415) { //remove from trade window
					fromTrade(removeID, removeSlot, 1);
				} else if (interfaceID == 3823) { //Show value to sell items
					if (Item.itemSellable[removeID] == false) {
						sendMessage("You cannot sell "+GetItemName(removeID)+".");
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
							sendMessage("You need a higher Smithing level to smith this item.");
						}
					} else {
						sendMessage("You need a hammer to smith this item.");
					}
					} else {
						sendMessage("You don't have enough bars to make this item.");
					}
					} catch(Exception e) { 
					//sendMessage("You don't have enough bars to make this item.");
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
							sendMessage("You need a higher Smithing level to smith this item.");
						}
					} else {
						sendMessage("You need a hammer to smith this item.");
					}
					} else {
						sendMessage("You don't have enough bars to make this item.");
					}
					} catch(Exception e) { 
					//sendMessage("You don't have enough bars to make this item");
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
							sendMessage("You need a higher Smithing level to smith this item.");
						}
					} else {
						sendMessage("You need a hammer to smith this item.");
					}
					} else {
						sendMessage("You don't have enough bars to make this item.");
					}
					} catch(Exception e) { 
					//sendMessage("You don't have enough bars to make this item."); 
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
							sendMessage("You need a higher Smithing level to smith this item.");
						}
					} else {
						sendMessage("You need a hammer to smith this item.");
					}
					} else {
						sendMessage("You don't have enough bars to make tthis item.");
					}
					} catch(Exception e) {
					// sendMessage("You don't have enough bars to make this item."); 
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
							sendMessage("You need a higher Smithing level to smith this item.");
						}
					} else {
						sendMessage("You need a hammer to smith this item.");
					}
					} else {
						sendMessage("You don't have enough bars to make this item.");
					}
					} catch(Exception e) { 
					//sendMessage("You don't have enough bars to make this item.");
					 }
				}
				
				break;

			case 117:	//bank 5 items - sell 1 item
				interfaceID = inStream.readSignedWordBigEndianA();
				removeID = inStream.readSignedWordBigEndianA();
				removeSlot = inStream.readSignedWordBigEndian();

				//println_debug("RemoveItem 5: "+removeID +" InterID: "+interfaceID +" slot: "+removeSlot );

				if (interfaceID == 5064) { //remove from bag to bank
					bankItem(removeID , removeSlot, 5);
				} else if (interfaceID == 5382) { //remove from bank
					fromBank(removeID , removeSlot, 5);
				} else if (interfaceID == 3322) { //remove from bag to trade window
                                        if(isUntradable(removeID)) 
                                        sendMessage("You cannot trade this item."); 
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
							sendMessage("You need a higher Smithing level to smith this item.");
						}
					} else {
						sendMessage("You need a hammer to smith this item.");
					}
					} else {
						sendMessage("You don't have enough bars to make this item.");
					}
					} catch(Exception e) { 
					//sendMessage("You don't have enough bars to make this item.");
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
							sendMessage("You need a higher Smithing level to smith this item.");
						}
					} else {
						sendMessage("You need a hammer to smith this item.");
					}
					} else {
						sendMessage("You don't have enough bars to make this item.");
					}
					} catch(Exception e) { 
					//sendMessage("You don't have enough bars to make this item.");
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
							sendMessage("You need a higher Smithing level to smith this item.");
						}
					} else {
						sendMessage("You need a hammer to smith this item.");
					}
					} else {
						sendMessage("You don't have enough bars to make this item.");
					}
					} catch(Exception e) { 
					//sendMessage("You don't have enough bars to make this item.");
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
							sendMessage("You need a higher Smithing level to smith this item.");
						}
					} else {
						sendMessage("You need a hammer to smith this item.");
					}
					} else {
						sendMessage("You don't have enough bars to make this item.");
					}
					} catch(Exception e) { 
					//sendMessage("You don't have enough bars to make this item.");
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
							sendMessage("You need a higher Smithing level to smith this item.");
						}
					} else {
						sendMessage("You need a hammer to smith this item.");
					}
					} else {
						sendMessage("You don't have enough bars to make this item.");
					}
					} catch(Exception e) { 
					//sendMessage("You don't have enough bars to make this item."); 
					}
				}
                                
				
				break;

			case 43:	//bank 10 items - sell 5 items
				interfaceID = inStream.readUnsignedWordBigEndian();
				removeID = inStream.readUnsignedWordA();
				removeSlot = inStream.readUnsignedWordA();

				//println_debug("RemoveItem 10: "+removeID +" InterID: "+interfaceID +" slot: "+removeSlot );

				if (interfaceID == 5064) { //remove from bag to bank
					bankItem(removeID , removeSlot, 10);
				} else if (interfaceID == 5382) { //remove from bank
					fromBank(removeID , removeSlot, 10);
				} else if (interfaceID == 3322) { //remove from bag to trade window
                                        if(isUntradable(removeID)) 
                                        sendMessage("You cannot trade this item."); 
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
							sendMessage("You need a higher Smithing level to smith this item.");
						}
					} else {
						sendMessage("You need a hammer to smith this item.");
					}
					} else {
						sendMessage("You don't have enough bars to make this item.");
					}
					} catch(Exception e) { 
					//sendMessage("You don't have enough bars to make this item.");
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
							sendMessage("You need a higher Smithing level to smith this item.");
						}
					} else {
						sendMessage("You need a hammer to smith this item.");
					}
					} else {
						sendMessage("You don't have enough bars to make this item.");
					}
					} catch(Exception e) {
					// sendMessage("You don't have enough bars to make this item."); 
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
							sendMessage("You need a higher Smithing level to smith this item.");
						}
					} else {
						sendMessage("You need a hammer to smith this item.");
					}
					} else {
						sendMessage("You don't have enough bars to make this item.");
					}
					} catch(Exception e) { 
					//sendMessage("You don't have enough bars to make this item.");
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
							sendMessage("You need a higher Smithing level to smith this item.");
						}
					} else {
						sendMessage("You need a hammer to smith this item.");
					}
					} else {
						sendMessage("You don't have enough bars to make this item.");
					}
					} catch(Exception e) { 
					//sendMessage("You don't have enough bars to make this item.");
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
							sendMessage("You need a higher Smithing level to smith this item.");
						}
					} else {
						sendMessage("You need a hammer to smith this item.");
					}
					} else {
						sendMessage("You don't have enough bars to make this item.");
					}
					} catch(Exception e) { 
				//	sendMessage("You don't have enough bars to make this item.");
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
                                        if(isUntradable(removeID)) 
                                        sendMessage("You cannot trade this item."); 
                                        else
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

				//println_debug("RemoveItem X: "+XremoveID +" InterID: "+XinterfaceID +" slot: "+XremoveSlot);

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
				}
				if (fletching[5] == 1) {
					if (EnteredAmount >= 1 && EnteredAmount <= 3) {
						fletching[5] = 2;
						fletching[6] = EnteredAmount;
						CheckForSkillUse();
					} else {
						resetFL();
						sendMessage("1 = Arrow Shafts | 2 = Short Bow | 3 = Long Bow");
						sendMessage("Achey Logs: 1 = Ogre Arrow Shafts | 2 = Ogre Composite Bow");
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
                               if (itemID2 == 3006 && itemX2 == 3288 && itemY2 == 3886) {
                                if(playerHasItem(590)) {
                                  sendMessage("You light the fireworks");
                                  int itemAmount2 = ItemHandler.itemAmount(itemID2, itemX2, itemY2);
				  ItemHandler.removeItem(itemID2, itemX2, itemY2, itemAmount2);
				  removeGroundItem(itemX2, itemY2, itemID2);
                                  ItemHandler.addItem(744, itemX2, itemY2, 1, playerId, false);
                                 }
                                  else {
                                   sendMessage("You need a tinderbox.");
                                 }
                                }
                                break;
			case 87:		// drop item
				int droppedItem = inStream.readUnsignedWordA();
				somejunk = inStream.readUnsignedByte()+inStream.readUnsignedByte();
				int slot = inStream.readUnsignedWordA();
				//println_debug("dropItem: "+droppedItem+" Slot: "+slot);
                                if(isUntradable(droppedItem)) {
                                sendMessage("You drop the "+getItemName(droppedItem)+".");
                                deleteItem(droppedItem, slot, playerItemsN[slot]);
                                }
                                if(droppedItem == 744 && absX == 2780 && absY == 3515) {
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
				switch(actionButtonId) {
				//These values speak for themselves
				
				case 14067: // Char design accept button
				RemoveAllWindows();
				break;
                      		case 4140: TeleTo("Varrock", 0); ancientstele = false; break;
                      case 4143: TeleTo("Lumby", 31); ancientstele = false; break;
                      case 4146: TeleTo("Falador", 37); ancientstele = false; break;
                      case 4150: TeleTo("Camelot", 45); ancientstele = false; break;
                      case 6004: TeleTo("Ardougne", 51);ancientstele = false; break;
                      case 6005: TeleTo("Watchtower", 58);ancientstele = false; break;
                      case 29031: TeleTo("Trollheim", 61);ancientstele = false; break;
                      case 72038: TeleTo("Ape", 64); ancientstele = false; break;

                    case 50235: TeleTo("Paddewwa", 54); ancientstele = true; break;
                    case 50245: TeleTo("Senntisten", 60); ancientstele = true; break;
                    case 50253: TeleTo("Kharyrll", 66); ancientstele = true; break;
                    case 51005: TeleTo("Lassar", 72); ancientstele = true; break;
                    case 51013: TeleTo("Dareeyak", 78); ancientstele = true; break;
                    case 51023: TeleTo("Carrallangar", 84); ancientstele = true; break;
                    case 51031: TeleTo("Annakarl", 90); ancientstele = true; break;
                    case 51039: TeleTo("Ghorrock", 96); ancientstele = true; break;
		case 49022: // Xerozcheez: Player accepts teleother request

case 49024: // Xerozcheez: Player declines teleother
if(teleOtherScreen) {
client tele2 = (client) server.playerHandler.players[teleReq];
teleOtherScreen = false;
sendMessage("You decline the request.");
tele2.sendMessage("The other player declined the request.");
}
closeInterface();
break;

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
                                        if (LogoutDelay > 0)
                                        {
                                        sendMessage("You have to wait 30 seconds before logging out.");
                                        }
					if(!inCity) { sendMessage("You cannot logout outside <3City."); }
                                        else
					{
						logout();
                                                savemoreinfo();
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
								sendFrame126("Waiting for other player.", 3431);
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
								sendFrame126("Waiting for other player.", 3535);
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
                  }else if (NpcDialogue == 306){
                     NpcDialogue = 307;
                     NpcDialogueSend = false;
                  }else if (NpcDialogue == 14601){
                     NpcDialogue = 14602;
                     NpcDialogueSend = false;
                  }else if (NpcDialogue == 14603){
                     NpcDialogue = 14604;
                     NpcDialogueSend = false;
                  } else if (NpcDialogue == 2260 && LogoutDelay <= 0) { // Mage Of Zamorak
		NpcDialogue = 0;
		NpcDialogueSend = false;

		sendMessage("You teleport to the Abyss.");
		teleportToX = 3040;
                teleportToY = 4842;
		}else if (NpcDialogue == 1002 && LogoutDelay <= 0) { // Dark Mage
		NpcDialogue = 0;
		NpcDialogueSend = false;
		RemoveAllWindows();
		sendMessage("You teleport to the wilderness.");
        	teleportToX = 3091;
        	teleportToY = 3963;
		} else if (NpcDialogue == 653  && LogoutDelay <= 0) { // Wizard
		NpcDialogue = 0;
		NpcDialogueSend = false;
		RemoveAllWindows();
		sendMessage("You teleport to <3City.");
		teleportToX = 2916;
		teleportToY = 3527;
		}
		break;
					case 9158:
						if (NpcDialogue == 2) {
							NpcDialogue = 0;
							NpcDialogueSend = false;
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
                  }else if (NpcDialogue == 1002 && LogoutDelay <= 0){
                     NpcDialogue = 0;
                     NpcDialogueSend = true;
                     RemoveAllWindows();
		     teleportToX = 2835;
		     teleportToY = 3509;
        	     heightLevel = 0;
                  }else if (NpcDialogue == 653 && LogoutDelay <= 0){
                     NpcDialogue = 0;
                     NpcDialogueSend = true;
                     RemoveAllWindows();
		     teleportToX = 2835;
		     teleportToY = 3509;
        	     heightLevel = 0;
                  }else if (NpcDialogue == 302){
                     NpcDialogue = 300;
                     NpcDialogueSend = false;
                  }else if (NpcDialogue == 305){
                     NpcDialogue = 0;
                     NpcDialogueSend = true;
                     RemoveAllWindows();
                  }else if (NpcDialogue == 14601){
                     NpcDialogue = 0;
                     NpcDialogueSend = true;
                     RemoveAllWindows();
                  }else if (NpcDialogue == 14603){
                     NpcDialogue = 0;
                     NpcDialogueSend = true;
                     RemoveAllWindows();
                  } 

						break;
					case 1097:
						setSidebarInterface(0, 1829);
						break;
					case 7212:
						setSidebarInterface(0, 328);
						break;

case 3162:
InWildrange = true;
break;

case 3163:
InWildrange = false;
break;

case 24135: // Clue debug set to on
{
sendMessage("Clue Debugging: True.");
cluedebug = true;
break;
}
case 24134: // Clue debug set to off
{
sendMessage("Clue Debugging: False.");
cluedebug = false;
break;
}
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
						//println_debug("Case 185: Action Button: "+actionButtonId);
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
				break;
			// any packets we might have missed
			default:
				interfaceID = inStream.readUnsignedWordA();
				int actionButtonId1 = misc.HexToInt(inStream.buffer, 0, packetSize);
				//println_debug("Unhandled packet ["+packetType+", InterFaceId: " +interfaceID+", size="+packetSize+"]: ]"+misc.Hex(inStream.buffer, 1, packetSize)+"[");
				//println_debug("Action Button: "+actionButtonId1);
			break;
		}
	}
	private int somejunk;
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
		sendMessage("Somone used a crackers on you!");
		CrackerMsg = false;
		if (CrackerForMe == true) {
			if (freeSlots() > 0) {
				addItem(Item.randomPHat(), 1);
				sendMessage("You got the prize!");
			} else {
				sendMessage("You don't have enough space in your inventory.");
			}
			CrackerForMe = false;
		} else {
			sendMessage("You didn't get the prize.");
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
		int offsetY = (absX - EnemyX) * -1;
		int offsetX = (absY - EnemyY) * -1;
		if (PlayerHandler.players[AttackingOn].playerEquipment[playerRing] == 2570) {
			RingOfLife = true;
		}
		int hitDiff = 0;
                int wepdelay = 0;
                CalculateMaxHit();
		hitDiff = misc.random(playerMaxHit);
		faceNPC = 32768+AttackingOn;
//viewTo(server.playerHandler.players[AttackingOn].absX, server.playerHandler.players[AttackingOn].absY);

if(playerEquipment[playerWeapon] == (1333)) // rune scimi here
{
PkingDelay = 8;
wepdelay = 8;
}

if(playerEquipment[playerWeapon] == (4151)) // whip
{
PkingDelay = 3;
wepdelay = 3;
resetanim = 3;
}

if(playerEquipment[playerWeapon] == (6611)) // white scimmy
{
PkingDelay = 3;
wepdelay = 3;
resetanim = 3;
}
			
if(playerEquipment[playerWeapon] == (4718)) //dharoks axe
{
PkingDelay = 8;
wepdelay = 8;
}

if(playerEquipment[playerWeapon] == (4151)) //full karils and whip here
if(playerEquipment[playerChest] == (4736))
if(playerEquipment[playerLegs] == (4738))
{
PkingDelay = 2;
wepdelay = 2;
}

if(playerEquipment[playerWeapon] == (4151)) //full torags and whip here
if(playerEquipment[playerChest] == (4749))
if(playerEquipment[playerLegs] == (4751))
if(playerEquipment[playerHat] == (4745))
{
PkingDelay = 3;
wepdelay = 3;
}

if(playerEquipment[playerWeapon] == (4587)) // dragon scimmy here
{
PkingDelay = 5;
wepdelay = 5;
}

if(playerEquipment[playerWeapon] == (1377)) //dragon battleaxe
{
PkingDelay = 13;
wepdelay = 13;
}

if(playerEquipment[playerWeapon] == (1434)) //dragon mace
{
PkingDelay = 10;
wepdelay = 10;
}

if(playerEquipment[playerWeapon] == (7158)) //dragon 2h
{
PkingDelay = 14;
wepdelay = 14;
}


boolean UseBow = false;
if(playerEquipment[playerWeapon] == 4214)
{
 PkingDelay = 10;
 wepdelay = 2;
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
 PkingDelay = 5;
 wepdelay = 4;
 UseBow = true;                                                                               
}
if(playerEquipment[playerWeapon] == 839 || playerEquipment[playerWeapon] == 841 || playerEquipment[playerWeapon] == 843 || playerEquipment[playerWeapon] == 845 || playerEquipment[playerWeapon] == 847 || playerEquipment[playerWeapon] == 849 || playerEquipment[playerWeapon] == 851 || playerEquipment[playerWeapon] == 853 || playerEquipment[playerWeapon] == 855 || playerEquipment[playerWeapon] == 857)
{
  PkingDelay = 8;
  wepdelay = 8;
  UseBow = true;
}

if(UseBow && rangeTimer == 0){
 inCombat(); 
 teleportToX = absX;   
 teleportToY = absY; 
 CheckArrows();
 CalculateRange();
 hitDiff = misc.random(playerMaxHit);
}


else
{
PkingDelay = 10;
wepdelay = 10;
}                                                                               

                                if(inSafe || AttackingOn2.inSafe) {
                                 sendMessage("This player is in a safe zone.");
					ResetAttack();
                                 }
                                else if(!inSafe && !AttackingOn2.inSafe){
		if (GoodDistance(EnemyX, EnemyY, absX, absY, 1) == true || playerEquipment[playerWeapon] == 859 || playerEquipment[playerWeapon] == 861 || playerEquipment[playerWeapon] == 4214 || playerEquipment[playerWeapon] == 839 || playerEquipment[playerWeapon] == 841 || playerEquipment[playerWeapon] == 843 || playerEquipment[playerWeapon] == 845 || playerEquipment[playerWeapon] == 847 || playerEquipment[playerWeapon] == 849 || playerEquipment[playerWeapon] == 851 || playerEquipment[playerWeapon] == 853 || playerEquipment[playerWeapon] == 855 || playerEquipment[playerWeapon] == 857) {
		  if (LoopAttDelay <= 1) {
		    if (!inSafe && !AttackingOn2.inSafe) {
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
                                        addSkillXP(hitDiff, 3);
					PlayerHandler.players[AttackingOn].hitDiff = hitDiff;
					//PkingDelay = wepdelay;
					}
                                    	else if(UseBow){
                                        if(!HasArrows){ 
                                        sendMessage("There are no arrows in your quiver.");
                                        ResetAttack();
                                        }
                                        else if(HasArrows){
					//GraphicsHandler.createSpell(473, absY, absX, offsetY, offsetX, 50, 95, 472, 43, 31, MageAttackIndex + 1, 473, EnemyY, EnemyX);
					//actionAmount++;
					//setAnimation(playerSEA);
                                        //DeleteArrow();
                                        //if(playerEquipment[playerWeapon] != 4214 && playerEquipmentN[playerArrows] != 0)
                                        //ItemHandler.addItem(playerEquipment[playerArrows], EnemyX, EnemyY, 1, playerId, false);
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
                                        addSkillXP(hitDiff, 3);
					PlayerHandler.players[AttackingOn].hitDiff = hitDiff;
					//PkingDelay = wepdelay;
                                        }
				}
			}
			return true;
		    } else {
		      sendMessage("This player is in a safe zone.");
		      ResetAttack();
		    }
		  }
		} }
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
	
        public void PKs() {
	if(PlayerHandler.players[KillerId] != null) {
 if(KillerId != playerId){
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
  if(killerz != null) {
  boolean givePoints = true;
    if(killerz.lastKill.equalsIgnoreCase(playerName)) {
  killerz.sendMessage("No Kills or Player Kill Points.");
  givePoints = false;
  }
  if(givePoints) {
  PlayerHandler.players[KillerId].pkpoints += lnew;
  PlayerHandler.players[KillerId].killcount += 1;
  otherpkps = PlayerHandler.players[KillerId].pkpoints;
  otherkillc = PlayerHandler.players[KillerId].killcount;
  killerz.sendMessage("You have defeated "+playerName+".");
  PlayerHandler.messageToAll = killerz.playerName+": Kills: "+otherkillc+" Player Kill Points: "+otherpkps+"";
  int gp = (combat * 250); // gives cash depending on combat level
  killerz.addItem(995, gp);
  killerz.checkPKReward();
  killerz.lastKill = playerName;
 }
 }
} //deathcount =+ 1;
}
}

public void CheckDrop() {
                        keepItem1();
                        keepItem2();
                        keepItem3();
                        deleteItem(keepItem, getItemSlot(keepItem), keepItemAmount);
                        deleteItem(keepItem2, getItemSlot(keepItem2), keepItemAmount2);
                        deleteItem(keepItem3, getItemSlot(keepItem3), keepItemAmount3);
}

	public boolean ApplyDead() {
		if (IsDeadTimer == false) {
			actionAmount++;
			actionTimer = 0;
			ResetAttack();
			ResetAttackNPC();
			pEmote = 2820;
			IsDeadTimer = true;
                        ApplyDead();
		}
		if (actionTimer == 0 && IsDeadTimer == true) {
			PKs();
			CheckDrop();
                        keepItem1();
                        keepItem2();
                        keepItem3();
                        deleteItem(keepItem, getItemSlot(keepItem), keepItemAmount);
                        deleteItem(keepItem2, getItemSlot(keepItem2), keepItemAmount2);
                        deleteItem(keepItem3, getItemSlot(keepItem3), keepItemAmount3);
			youdied();
			didTeleport = true;
                	teleportToX = 2899;
			teleportToY = 3563;
			heightLevel = 0;
			IsDeadTeleporting = true;
			frame1();
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
	/*public boolean ApplyDeadz() {

		if (IsDeadTimer == false) {
			actionTimer = 0;
			ResetAttack();
			ResetAttackNPC();
			setAnimation(0x900);
                        IsDeadTeleporting = true;
			IsDeadTimer = true;
                        ApplyDead();
		}
		if (actionTimer == 0 && IsDeadTimer == true) {
                        PKs();                      
                        CheckDrop();
                        ApplyRingz();
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
	}*/
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

	public boolean ApplyRingOfLife() {
		if (IsDeadTimer == false) {
			actionAmount++;
			actionTimer = 4;
			ResetAttack();
			setAnimation(0x718);
			sendMessage("Ring of Life saved you!");
			deleteequiment(2570, playerRing); //2570 = ring of life
			IsDeadTimer = true;
		}
		if (actionTimer == 0 && IsDeadTimer == true) {
			teleportToX = 2870;
			teleportToY = 10199;
			heightLevel = 0;
			resetAnimation();
			IsDeadTeleporting = true;
			updateRequired = true;
			appearanceUpdateRequired = true;
		}
		return true;
	}
	public boolean ApplyRingz() {
		if (IsDeadTimer == false) {
			actionAmount++;
			actionTimer = 4;
			ResetAttack();
			//setAnimation(0x718);
			IsDeadTimer = true;
		}
		if (actionTimer == 0 && IsDeadTimer == true) {
			teleportToX = 2870;
			teleportToY = 10199;
			heightLevel = 0;
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
sendFrame126(""+playerLevel[2]+"", 4006);
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
sendFrame126(""+playerLevel[2]+"", 4006);
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
sendFrame126(""+playerLevel[2]+"", 4006);
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
sendFrame126(""+playerLevel[2]+"", 4006);
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
sendFrame126(""+playerLevel[2]+"", 4006);
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
sendFrame126(""+playerLevel[2]+"", 4006);
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
sendFrame126(""+playerLevel[2]+"", 4006);
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
sendFrame126(""+playerLevel[2]+"", 4006);
deleteItem(119, GetItemSlot(119), 1);
addItem(229, 1);
updateRequired = true;
appearanceUpdateRequired = true;
GoOn = false;
break;

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
       			case 2448: //superAntipoison(4)
//Poisoned = false;
PoisonDelay = 999999;
sendMessage("You drink a dose of the superantipoison.");
deleteItem(2448, GetItemSlot(2448), 1);
addItem(181, 1);
break;
case 181: //superAntipoison(3)
//Poisoned = false;
PoisonDelay = 999999;
sendMessage("You drink a dose of the superantipoison.");
deleteItem(181, GetItemSlot(181), 1);
addItem(183, 1);
break;
case 183: //superAntipoison(2)
//Poisoned = false;
PoisonDelay = 999999;
sendMessage("You drink a dose of the superantipoison.");
deleteItem(183, GetItemSlot(183), 1);
addItem(185, 1);
break;
case 185: //superAntipoison(1)
//Poisoned = false;
PoisonDelay = 999999;
sendMessage("You drink the last dose of the superantipoison.");
deleteItem(185, GetItemSlot(185), 1);
addItem(229, 1);
break;
case 5943: //extra-strongAntidote(4)
//Poisoned = false;
PoisonDelay = 999999;
sendMessage("You drink a dose of the antidote+.");
deleteItem(5943, GetItemSlot(5943), 1);
addItem(5945, 1);
break;
case 5945: //extra-strongAntidote(3)
//Poisoned = false;
PoisonDelay = 999999;
sendMessage("You drink a dose of the antidote+.");
deleteItem(5954, GetItemSlot(5954), 1);
addItem(5947, 1);
break;
case 5947: //extra-strongAntidote(2)
//Poisoned = false;
PoisonDelay = 999999;
sendMessage("You drink a dose of the antidote+.");
deleteItem(5947, GetItemSlot(5947), 1);
addItem(5949, 1);
break;
case 5949: //extra-strongAntidote(1)
//Poisoned = false;
PoisonDelay = 999999;
sendMessage("You drink the last dose of the antidote+.");
deleteItem(5949, GetItemSlot(5949), 1);
addItem(229, 1);
break;
case 5952: //super-strongAntidote(4)
//Poisoned = false;
PoisonDelay = 999999;
sendMessage("You drink a dose of the antidote++.");
deleteItem(5952, GetItemSlot(5952), 1);
addItem(5954, 1);
break;
case 5954: //super-strongAntidote(3)
//Poisoned = false;
PoisonDelay = 999999;
sendMessage("You drink a dose of the antidote++.");
deleteItem(5954, GetItemSlot(5954), 1);
addItem(5956, 1);
break;
case 5956: //super-strongAntidote(2)
//Poisoned = false;
PoisonDelay = 999999;
sendMessage("You drink a dose of the antidote++.");
deleteItem(5956, GetItemSlot(5956), 1);
addItem(5958, 1);
break;
case 5958: //super-strongAntidote(1)
//Poisoned = false;
PoisonDelay = 999999;
sendMessage("You drink the last dose of the antidote++.");
deleteItem(5958, GetItemSlot(5958), 1);
addItem(229, 1);
break;
case 131: // restore pot
restorePot();
sendMessage("You drink the last dose of the restore potion.");
deleteItem(131, GetItemSlot(131), 1);
addItem(229, 1);
break;
case 129: // restore pot
restorePot();
sendMessage("You drink a dose of the restore potion.");
deleteItem(129, GetItemSlot(129), 1);
addItem(131, 1);
break;
case 127: // restore pot
restorePot();
sendMessage("You drink a dose of the restore potion.");
deleteItem(127, GetItemSlot(127), 1);
addItem(129, 1);
break;
           case 2430: // restore pot
                restorePot();
                sendMessage("You drink a dose of the restore potion.");
                deleteItem(2430, GetItemSlot(2430), 1);
                addItem(127, 1);
                break;
           case 3030: // super restore pot
                restorePot();
                sendMessage("You drink the last dose of the super restore potion.");
                deleteItem(3030, GetItemSlot(3030), 1);
                addItem(229, 1);
                superRestore = true;
                break;
                case 3028: // super restore pot
                restorePot();
                sendMessage("You drink a dose of the super restore potion.");
                deleteItem(3028, GetItemSlot(3028), 1);
                addItem(3030, 1);
                superRestore = true;
                break;
            case 3026: // super restore pot
                restorePot();
                sendMessage("You drink a dose of the super restore potion.");
                deleteItem(3026, GetItemSlot(3026), 1);
                addItem(3028, 1);
                superRestore = true;
                break;
            case 3024: // super restore pot
                restorePot();
                sendMessage("You drink a dose of the super restore potion.");
                deleteItem(3024, GetItemSlot(3024), 1);
                addItem(3026, 1);
                superRestore = true;
                break;
            case 155: // fish pot (1)
                fishPot = true;
                fishPotTimer = 120;
                playerLevel[10] = getLevelForXP(playerXP[10]);
                playerLevel[10] += 4;
                sendFrame126(""+playerLevel[10]+"", 4032);
		sendMessage("You drink the last dose of the fishing potion.");
                deleteItem(155, GetItemSlot(155), 1);
                addItem(229, 1);
                updateRequired = true;
                appearanceUpdateRequired = true;
                GoOn = false;
                break;
            case 153: // fish pot (2)
                fishPot = true;
                fishPotTimer = 120;
                playerLevel[10] = getLevelForXP(playerXP[10]);
                playerLevel[10] += 4;
                sendFrame126(""+playerLevel[10]+"", 4032);
		sendMessage("You drink a dose of the fishing potion.");
                deleteItem(153, GetItemSlot(153), 1);
                addItem(155, 1);
                updateRequired = true;
                appearanceUpdateRequired = true;
                GoOn = false;
                break;
            case 151: // fish pot (3)
                fishPot = true;
                fishPotTimer = 120;
                playerLevel[10] = getLevelForXP(playerXP[16]);
                playerLevel[10] += 4;
                sendFrame126(""+playerLevel[10]+"", 4032);
		sendMessage("You drink a dose of the fishing potion.");
                deleteItem(151, GetItemSlot(151), 1);
                addItem(153, 1);
                updateRequired = true;
                appearanceUpdateRequired = true;
                GoOn = false;
                break;
            case 2438: // fish pot (4)
                fishPot = true;
                fishPotTimer = 120;
                playerLevel[10] = getLevelForXP(playerXP[10]);
                playerLevel[10] += 4;
                sendFrame126(""+playerLevel[10]+"", 4032);
		sendMessage("You drink a dose of the fishing potion.");
                deleteItem(2438, GetItemSlot(2438), 1);
                addItem(151, 1);
                updateRequired = true;
                appearanceUpdateRequired = true;
                GoOn = false;
                break;
            case 3038: // agil pot (1)
                agilPot = true;
                agilPotTimer = 90;
                playerLevel[16] = getLevelForXP(playerXP[16]);
                playerLevel[16] += 4;
                sendFrame126(""+playerLevel[16]+"", 4018);
		sendMessage("You drink the last dose of the agility potion.");
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
                sendFrame126(""+playerLevel[16]+"", 4018);
		sendMessage("You drink a dose of the agility potion.");
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
                sendFrame126(""+playerLevel[16]+"", 4018);
		sendMessage("You drink a dose of the agility potion.");
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
                sendFrame126(""+playerLevel[16]+"", 4018);
		sendMessage("You drink a dose of the agility potion.");
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
                sendFrame126(""+playerLevel[5]+"", 4012);
		sendMessage("You drink the last dose of the prayer potion.");
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
                sendFrame126(""+playerLevel[5]+"", 4012);
		sendMessage("You drink a dose of the prayer potion.");
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
                sendFrame126(""+playerLevel[5]+"", 4012);
		sendMessage("You drink a dose of the prayer potion.");
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
                sendFrame126(""+playerLevel[5]+"", 4012);
		sendMessage("You drink a dose of the prayer potion.");
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
                sendFrame126(""+playerLevel[1]+"", 4008);
		sendMessage("You drink the last dose of the super defense potion.");
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
                sendFrame126(""+playerLevel[1]+"", 4008);
		sendMessage("You drink a dose of the super defense potion.");
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
                sendFrame126(""+playerLevel[1]+"", 4008);
		sendMessage("You drink a dose of the super defense potion.");
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
                sendFrame126(""+playerLevel[1]+"", 4008);
		sendMessage("You drink a dose of the super defense potion.");
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
                sendFrame126(""+playerLevel[1]+"", 4008);
		sendMessage("You drink the last dose of the defense potion.");
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
                sendFrame126(""+playerLevel[1]+"", 4008);
		sendMessage("You drink a dose of the defense potion.");
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
                sendFrame126(""+playerLevel[1]+"", 4008);
		sendMessage("You drink a dose of the defense potion.");
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
                sendFrame126(""+playerLevel[1]+"", 4008);
		sendMessage("You drink a dose of the defense potion.");
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
                sendFrame126(""+playerLevel[6]+"", 4014);
		sendMessage("You drink the last dose of the magic potion.");
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
                sendFrame126(""+playerLevel[6]+"", 4014);
		sendMessage("You drink a dose of the magic potion.");
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
                sendFrame126(""+playerLevel[6]+"", 4014);
		sendMessage("You drink a dose of the magic potion.");
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
                sendFrame126(""+playerLevel[6]+"", 4014);
		sendMessage("You drink a dose of the magic potion.");
                deleteItem(3040, GetItemSlot(3040), 1);
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
                sendFrame126(""+playerLevel[6]+"", 4010);
		sendMessage("You drink the last dose of the ranging potion.");
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
                sendFrame126(""+playerLevel[4]+"", 4010);
		sendMessage("You drink a dose of the ranging potion.");
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
                sendFrame126(""+playerLevel[4]+"", 4010);
		sendMessage("You drink a dose of the ranging potion.");
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
                sendFrame126(""+playerLevel[4]+"", 4010);
		sendMessage("You drink a dose of the ranging potion.");
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
                sendFrame126(""+playerLevel[0]+"", 4004);
		sendMessage("You drink the last dose of the super attack potion.");
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
                sendFrame126(""+playerLevel[0]+"", 4004);
		sendMessage("You drink a dose of the super attack potion.");
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
                sendFrame126(""+playerLevel[0]+"", 4004);
		sendMessage("You drink a dose of the super attack potion.");
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
                sendFrame126(""+playerLevel[0]+"", 4004);
		sendMessage("You drink a dose of the super attack potion.");
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
                sendFrame126(""+playerLevel[0]+"", 4004);
		sendMessage("You drink the last dose of the attack potion.");
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
                sendFrame126(""+playerLevel[0]+"", 4004);
		sendMessage("You drink a dose of the attack potion.");
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
                sendFrame126(""+playerLevel[0]+"", 4004);
		sendMessage("You drink a dose of the attack potion.");
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
                sendFrame126(""+playerLevel[0]+"", 4004);
                deleteItem(2428, GetItemSlot(2428), 1);
                addItem(121, 1);
                updateRequired = true;
                appearanceUpdateRequired = true;
                GoOn = false;
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
				sendMessage("Nothing interesting happens.");
				//println_debug("Prayer Usage - ItemID: "+Item);
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
				sendMessage("Nothing interesting happens.");
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
				sendMessage("There are no logs on the ground.");
				resetFM();
				//println_debug("Firemaking Glitch: There are no logs on the ground.");
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
if(playerEquipment[playerWeapon] == 4214 || playerEquipment[playerWeapon] == 859 || playerEquipment[playerWeapon] == 861) //bows
{
return 426;
}
if(playerEquipment[playerWeapon] == 4153) // maul
{
return 2062;
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
if(playerEquipment[playerWeapon] == 6609 || playerEquipment[playerWeapon] == 1307 || playerEquipment[playerWeapon] == 1309 || playerEquipment[playerWeapon] == 1311 || playerEquipment[playerWeapon] == 1313 || playerEquipment[playerWeapon] == 1315 || playerEquipment[playerWeapon] == 1317 || playerEquipment[playerWeapon] == 1319) 
// 2 handers
{
return 406;
}
if(playerEquipment[playerWeapon] == 7158) // d2h
{
return 2876;
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

public void AttackMage(int index) 
{	
int playerIndex = index; //inStream.readSignedWordA();
int spellID = inStream.readSignedWordBigEndian();	
client castOnPlayer = (client) server.playerHandler.players[playerIndex];
int EnemyX3 = server.playerHandler.players[playerIndex].absX;
int EnemyY3 = server.playerHandler.players[playerIndex].absY;
int EnemyHP = server.playerHandler.players[playerIndex].playerLevel[playerHitpoints];
int offsetY = (absX - EnemyX3) * -1;
int offsetX = (absY - EnemyY3) * -1;
int heal = 0;
setAnimation(711);				
castOnPlayer.hitDiff = hitDiff;
castOnPlayer.KillerId = playerId;
castOnPlayer.updateRequired = true;
castOnPlayer.hitUpdateRequired = true;
teleportToX = absX;
teleportToY = absY;
castOnPlayer.inCombat();
inCombat();
faceNPC(32768+index);                
                
                
if ((spellID == 12939) && (playerLevel[6] >= 50) && (playerHasItemAmount(562, 2)==true) && (playerHasItemAmount(560, 2)==true) && (playerHasItemAmount(554, 1)==true) && (playerHasItemAmount(556, 1)==true)) { //Smoke Rush
		animation(385, EnemyY3, EnemyX3);
		 
		 
		EnemyHP -= castOnPlayer.hitDiff;
		actionTimer = 4;
		setAnimation(1979);
		castOnPlayer.hitDiff = 0 + misc.random(15);
		deleteItem(560, getItemSlot(560), 2); 
				deleteItem(562, getItemSlot(562), 2);
				deleteItem(554, getItemSlot(554), 1);
deleteItem(556, getItemSlot(556), 1);
				addSkillXP((30*playerLevel[6]), 6);
		//TurnPlayerTo(EnemyX3, EnemyY3);
		castOnPlayer.updateRequired = true;
		castOnPlayer.hitUpdateRequired = true;
		}

if ((spellID == 12963)  && (playerLevel[6] >= 62) && (playerHasItemAmount(562, 4)==true) && (playerHasItemAmount(560, 2)==true) && (playerHasItemAmount(554, 2)==true) && (playerHasItemAmount(556, 2)==true)) { //Smoke Burst
setAnimation(1979);
attackPlayersWithin(388, 19, 5); 
actionTimer = 4;
EnemyHP -= castOnPlayer.hitDiff;
 
castOnPlayer.hitDiff = 0 + misc.random(19);
deleteItem(562, getItemSlot(562), 4); 
				deleteItem(560, getItemSlot(560), 2);
				deleteItem(554, getItemSlot(554), 2);
deleteItem(556, getItemSlot(556), 2);
				addSkillXP((36*playerLevel[6]), 6);
EnemyHP -= castOnPlayer.hitDiff;
//TurnPlayerTo(EnemyX3, EnemyY3);
		castOnPlayer.updateRequired = true;
		castOnPlayer.hitUpdateRequired = true;
}

if ((spellID == 12951) && (playerLevel[6] >= 74) && (playerHasItemAmount(4278, 2)==true) && (playerHasItemAmount(560, 2)==true) && (playerHasItemAmount(554, 2)==true) && (playerHasItemAmount(556, 2)==true)) { //Smoke Blitz
setAnimation(1978);
animation(387, EnemyY3, EnemyX3);
actionTimer = 4;
deleteItem(556, getItemSlot(556), 2); 
				deleteItem(4278, getItemSlot(4278), 2);
				deleteItem(554, getItemSlot(554), 2);
deleteItem(556, getItemSlot(556), 2);
				addSkillXP((43*playerLevel[6]), 6);
castOnPlayer.hitDiff = 0 + misc.random(23);
EnemyHP -= castOnPlayer.hitDiff;
//TurnPlayerTo(EnemyX3, EnemyY3);
		castOnPlayer.updateRequired = true;
		castOnPlayer.hitUpdateRequired = true;
}

if ((spellID == 12901) && (playerLevel[6] >= 56) && (playerHasItemAmount(562, 2)==true) && (playerHasItemAmount(560, 2)==true) && (playerHasItemAmount(4278, 1)==true)) { //Blood Rush
setAnimation(711);


EnemyHP -= castOnPlayer.hitDiff;
//TurnPlayerTo(EnemyX3, EnemyY3);
		castOnPlayer.updateRequired = true;
		castOnPlayer.hitUpdateRequired = true;
animation(373, EnemyY3, EnemyX3);
actionTimer = 4;
deleteItem(560, getItemSlot(560), 2); 
				deleteItem(562, getItemSlot(562), 2);
				deleteItem(4278, getItemSlot(4278), 1);
				addSkillXP((33*playerLevel[6]), 6);

castOnPlayer.hitDiff = 0 + misc.random(17);
}

if ((spellID == 12919)  && (playerLevel[6] >= 68) && (playerHasItemAmount(562, 4)==true) && (playerHasItemAmount(560, 2)==true) && (playerHasItemAmount(4278, 2)==true)) { //Blood Burst
setAnimation(1979);

EnemyHP -= castOnPlayer.hitDiff;
//TurnPlayerTo(EnemyX3, EnemyY3);
attackPlayersWithin(376, 21, 5);
		castOnPlayer.updateRequired = true;
		castOnPlayer.hitUpdateRequired = true;
//animation(376, EnemyY3, EnemyX3);

actionTimer = 4;
deleteItem(4278, getItemSlot(4278), 2); 
				deleteItem(560, getItemSlot(560), 2);
				deleteItem(562, getItemSlot(562), 4);
				addSkillXP((39*playerLevel[6]), 6);
castOnPlayer.hitDiff = 0 + misc.random(21);
}

if ((spellID == 12987) && (playerLevel[6] >= 52) && (playerHasItemAmount(562, 2)==true) && (playerHasItemAmount(560, 2)==true) && (playerHasItemAmount(566, 1)==true) && (playerHasItemAmount(556, 1)==true)) { //Shadow Rush
animation(379, EnemyY3, EnemyX3);
EnemyHP -= castOnPlayer.hitDiff;
//TurnPlayerTo(EnemyX3, EnemyY3);
		castOnPlayer.updateRequired = true;
		castOnPlayer.hitUpdateRequired = true;
setAnimation(711);
actionTimer = 4;
deleteItem(560, getItemSlot(560), 2); 
				deleteItem(562, getItemSlot(562), 2);
				deleteItem(566, getItemSlot(566), 1);
deleteItem(556, getItemSlot(556), 1);
				addSkillXP((31*playerLevel[6]), 6);
castOnPlayer.hitDiff = 0 + misc.random(16);
}

if ((spellID == 13011) && (playerLevel[6] >= 64) && (playerHasItemAmount(562, 4)==true) && (playerHasItemAmount(560, 2)==true) && (playerHasItemAmount(566, 2)==true) && (playerHasItemAmount(556, 2)==true)) { //Shadow Burst
setAnimation(1979);
EnemyHP -= castOnPlayer.hitDiff;
//TurnPlayerTo(EnemyX3, EnemyY3);
		castOnPlayer.updateRequired = true;
		castOnPlayer.hitUpdateRequired = true;
//animation(382, EnemyY3, EnemyX3); 
attackPlayersWithin(382, 20, 5);
actionTimer = 4;
deleteItem(562, getItemSlot(562), 4); 
				deleteItem(560, getItemSlot(560), 2);
				deleteItem(566, getItemSlot(566), 2);
deleteItem(556, getItemSlot(556), 2);
				addSkillXP((37*playerLevel[6]), 6);
castOnPlayer.hitDiff = 0 + misc.random(20);
}

if ((spellID == 12999) && (playerLevel[6] >= 76) && (playerHasItemAmount(4278, 2)==true) && (playerHasItemAmount(560, 2)==true) && (playerHasItemAmount(566, 2)==true) && (playerHasItemAmount(556, 2)==true)) { //Shadow Blitz
setAnimation(1978);
EnemyHP -= castOnPlayer.hitDiff;
//TurnPlayerTo(EnemyX3, EnemyY3);
		castOnPlayer.updateRequired = true;
		castOnPlayer.hitUpdateRequired = true;
animation(381, EnemyY3, EnemyX3);
actionTimer = 4;
deleteItem(4278, getItemSlot(4278), 2); 
				deleteItem(560, getItemSlot(560), 2);
				deleteItem(556, getItemSlot(556), 2);
deleteItem(566, getItemSlot(566), 2);
				addSkillXP((43*playerLevel[6]), 6);
castOnPlayer.hitDiff = 0 + misc.random(24);
}

if ((spellID == 12861) && (playerLevel[6] >= 58) && (playerHasItemAmount(562, 2)==true) && (playerHasItemAmount(560, 2)==true) && (playerHasItemAmount(555, 1)==true)) { //Ice Rush
setAnimation(711);
if (castOnPlayer.EntangleDelay == 0);{
castOnPlayer.entangle(); } 
EnemyHP -= castOnPlayer.hitDiff;
//TurnPlayerTo(EnemyX3, EnemyY3);
		castOnPlayer.updateRequired = true;
		castOnPlayer.hitUpdateRequired = true;
animation(361, EnemyY3, EnemyX3);
actionTimer = 4;
deleteItem(555, getItemSlot(555), 2); 
				deleteItem(560, getItemSlot(560), 2);
				deleteItem(562, getItemSlot(562), 2);
				addSkillXP((34*playerLevel[6]), 6);
castOnPlayer.hitDiff = 0 + misc.random(18);
}

				
				
				
				if ((spellID == 1191) && (playerLevel[6] >= 60) && (playerHasItemAmount(4278, 2)==true) && (playerHasItemAmount(554, 1)==true) && (playerHasItemAmount(556, 4)==true)) { //Claws of Guthix 
					EnemyHP -= castOnPlayer.hitDiff;
actionTimer = 4;
castOnPlayer.hitDiff = 0 + misc.random(20);
//TurnPlayerTo(EnemyX3, EnemyY3);
castOnPlayer.updateRequired = true;
castOnPlayer.hitUpdateRequired = true;
					  //Stop While Cast
					  //Stop While Cast

					
if (chargeArenaSpells == true){
						hitDiff = 30;
					}
animation(187, EnemyY3, EnemyX3);
deleteItem(556, getItemSlot(556), 4); 
deleteItem(4278, getItemSlot(4278), 2); 
deleteItem(554, getItemSlot(554), 1);
addSkillXP((35*playerLevel[6]), 6); 
					
					
                                        }
				if ((spellID == 1190)  && (playerLevel[6] >= 60) && (playerHasItemAmount(4278, 2)==true) && (playerHasItemAmount(554, 4)==true) && (playerHasItemAmount(556, 2)==true)) { //Saradomin Strike 
					EnemyHP -= castOnPlayer.hitDiff;
actionTimer = 4;
castOnPlayer.hitDiff = 0 + misc.random(20);
//TurnPlayerTo(EnemyX3, EnemyY3);
castOnPlayer.updateRequired = true;
castOnPlayer.hitUpdateRequired = true;
					  //Stop While Cast
					  //Stop While Cast

					
if (chargeArenaSpells == true){
						hitDiff = 30;
					}
 animation(76, EnemyY3, EnemyX3);
deleteItem(556, getItemSlot(556), 2); 
deleteItem(4278, getItemSlot(4278), 2); 
deleteItem(554, getItemSlot(554), 4); 
addSkillXP((35*playerLevel[6]), 6);
					
					
                                        }
				
				if ((spellID == 1192) && (playerLevel[6] >= 60) && (playerHasItemAmount(4278, 2)==true) && (playerHasItemAmount(554, 4)==true) && (playerHasItemAmount(556, 1)==true)) { //Flames of Zamorak
					EnemyHP -= castOnPlayer.hitDiff;
actionTimer = 4;
castOnPlayer.hitDiff = 0 + misc.random(20);
//TurnPlayerTo(EnemyX3, EnemyY3);
castOnPlayer.updateRequired = true;
castOnPlayer.hitUpdateRequired = true;
					  //Stop While Cast
					  //Stop While Cast

					 
if (chargeArenaSpells == true){
						hitDiff = 30;
					}
animation(78, EnemyY3, EnemyX3);
deleteItem(556, getItemSlot(556), 1); 
deleteItem(4278, getItemSlot(4278), 2); 
deleteItem(554, getItemSlot(554), 4); 
addSkillXP((35*playerLevel[6]), 6);
					
					
                                        }
				


				if ((spellID == 12871) && (playerLevel[6] >= 81) && (playerHasItemAmount(560, 2)==true) && (playerHasItemAmount(4278, 2)==true) && (playerHasItemAmount(555, 3)==true))  {	//	Ice Blitz
setAnimation(1978);
EnemyHP -= castOnPlayer.hitDiff;
if (castOnPlayer.EntangleDelay == 0);{
castOnPlayer.entangle(); } 
actionTimer = 4;

castOnPlayer.hitDiff = 0 + misc.random(26);
//TurnPlayerTo(EnemyX3, EnemyY3);
castOnPlayer.updateRequired = true;
castOnPlayer.hitUpdateRequired = true;

animation(367, EnemyY3, EnemyX3);				
  //Stop While Cast
  //Stop While Cast
deleteItem(555, getItemSlot(555), 3); 
				deleteItem(4278, getItemSlot(4278), 2);
				deleteItem(560, getItemSlot(560), 2);
				addSkillXP((46*playerLevel[6]), 6);
}
			if ((spellID == 12929) && (iceDelay <= 0) && (playerLevel[6] >= 91) && (playerHasItemAmount(560, 4)==true) && (playerHasItemAmount(4278, 4)==true) && (playerHasItemAmount(566, 1)==true))  {	//	Blood Barrage
setAnimation(1979);

EnemyHP -= castOnPlayer.hitDiff;
actionTimer = 4;
iceDelay = 30;
attackPlayersWithin(377, 29, 5);
castOnPlayer.hitDiff = 0 + misc.random(29);
//TurnPlayerTo(EnemyX3, EnemyY3);
castOnPlayer.updateRequired = true;
castOnPlayer.hitUpdateRequired = true;
//animation(377, EnemyY3, EnemyX3);
				
  //Stop While Cast
  //Stop While Cast
deleteItem(560, getItemSlot(560), 4); 
				deleteItem(4278, getItemSlot(4278), 4);
				deleteItem(566, getItemSlot(566), 1);
				addSkillXP((51*playerLevel[6]), 6);
}
if ((spellID == 12881) && (playerLevel[6] >= 58) && (playerHasItemAmount(562, 2)==true) && (playerHasItemAmount(560, 2)==true) && (playerHasItemAmount(555, 2)==true))  {	//	Ice Burst
setAnimation(1979);
EnemyHP -= castOnPlayer.hitDiff;
actionTimer = 4;
attackPlayersWithin(363, 22, 5);
if (castOnPlayer.EntangleDelay == 0);{
castOnPlayer.entangle(); } 

castOnPlayer.hitDiff = 0 + misc.random(22);
//TurnPlayerTo(EnemyX3, EnemyY3);
castOnPlayer.updateRequired = true;
castOnPlayer.hitUpdateRequired = true;

				
  //Stop While Cast
  //Stop While Cast
deleteItem(555, getItemSlot(555), 2); 
				deleteItem(560, getItemSlot(560), 2);
				deleteItem(562, getItemSlot(562), 2);
				addSkillXP((34*playerLevel[6]), 6);
}
if ((spellID == 13023) && (iceDelay <= 0) && (playerLevel[6] >= 87) && (playerHasItemAmount(560, 4)==true) && (playerHasItemAmount(4278, 2)==true) && (playerHasItemAmount(556, 4)==true) && (playerHasItemAmount(566, 3)==true))  {	//	Shadow Barrage
setAnimation(1979);
EnemyHP -= castOnPlayer.hitDiff;
actionTimer = 4;
attackPlayersWithin(383, 28, 5);
castOnPlayer.hitDiff = 0 + misc.random(28);
//TurnPlayerTo(EnemyX3, EnemyY3);
castOnPlayer.updateRequired = true;
castOnPlayer.hitUpdateRequired = true;

//animation(383, EnemyY3, EnemyX3);
				
  //Stop While Cast
  //Stop While Cast
iceDelay = 30;
deleteItem(4278, getItemSlot(4278), 2); 
				deleteItem(566, getItemSlot(566), 3);
				deleteItem(556, getItemSlot(556), 4);
				deleteItem(560, getItemSlot(560), 4);
				addSkillXP((49*playerLevel[6]), 6);
}
if ((spellID == 12975) && (playerLevel[6] >= 86) && (playerHasItemAmount(560, 4)==true) && (playerHasItemAmount(4278, 2)==true) && (playerHasItemAmount(556, 4)==true) && (playerHasItemAmount(554, 4)==true))  {	//	Smoke Barrage
setAnimation(1979);
EnemyHP -= castOnPlayer.hitDiff;
actionTimer = 4;
attackPlayersWithin(390, 27, 5);
castOnPlayer.hitDiff = 0 + misc.random(27);
//TurnPlayerTo(EnemyX3, EnemyY3);
castOnPlayer.updateRequired = true;
castOnPlayer.hitUpdateRequired = true;
//animation(390, EnemyY3, EnemyX3);

				

deleteItem(560, getItemSlot(560), 4); 
				deleteItem(4278, getItemSlot(4278), 2);
				deleteItem(556, getItemSlot(556), 4);
				deleteItem(554, getItemSlot(566), 4);
				addSkillXP((49*playerLevel[6]), 6);
}

if ((spellID == 12911) && (playerLevel[6] >= 79) && (playerHasItemAmount(4278, 4)==true) && (playerHasItemAmount(560, 2)==true))  {	//	Blood Blitz
setAnimation(1978);

EnemyHP -= castOnPlayer.hitDiff;
actionTimer = 4;
castOnPlayer.hitDiff = 0 + misc.random(25);
//TurnPlayerTo(EnemyX3, EnemyY3);
castOnPlayer.updateRequired = true;
castOnPlayer.hitUpdateRequired = true;

animation(375, EnemyY3, EnemyX3);				
  //Stop While Cast
  //Stop While Cast
deleteItem(4278, getItemSlot(4278), 4); 
				deleteItem(560, getItemSlot(560), 2);
				addSkillXP((45*playerLevel[6]), 6);
}

				
				if (((spellID == 12891) && (iceDelay <= 0) && (playerLevel[6] >= 94) && (playerHasItemAmount(560, 4)==true) && (playerHasItemAmount(4278, 2)==true) && (playerHasItemAmount(555, 6)==true)) || ((spellID == 12891) && (iceDelay <= 0) && (playerLevel[6] >= 94) && (playerHasItemAmount(560, 4)==true) && (playerHasItemAmount(4278, 2)==true) && (playerEquipment[playerWeapon] == 1383))) {	//	Ice Barrage
				setAnimation(1979);
				EnemyHP -= castOnPlayer.hitDiff;

iceDelay = 30;
castOnPlayer.hitDiff = 0 + misc.random(30);
if (castOnPlayer.EntangleDelay == 0);{
castOnPlayer.uberentangle(); } 
//TurnPlayerTo(EnemyX3, EnemyY3);
castOnPlayer.updateRequired = true;
castOnPlayer.hitUpdateRequired = true;
attackPlayersWithin(369, 30, 5);
	//animation(369, EnemyY3, EnemyX3);
			
 				//GraphicsHandler.ancientSpell(362, absY, absX, offsetY, offsetX, 50, 95, 360, 43, 31, MageAttackIndex + 1, 0, EnemyY3, EnemyX3);
						
						 
				deleteItem(560, getItemSlot(560), 4); 
				
				deleteItem(4278, getItemSlot(4278), 2);
				addSkillXP((52*playerLevel[6]), 6);
				if((playerEquipment[playerWeapon] != 1383) && (spellID == 12891)) 
				{
				
				deleteItem(555, getItemSlot(555), 6); 
				}
				
				
}



				
				
	}
	
public boolean AttackNPC() {
		int EnemyX = server.npcHandler.npcs[attacknpc].absX;
		int EnemyY = server.npcHandler.npcs[attacknpc].absY;
		int EnemyHP = server.npcHandler.npcs[attacknpc].HP;
		int offsetY = (absY - EnemyY) * -1;
		int offsetX = (absX - EnemyX) * -1;
		int hitDiff = 0;
                int Npchitdiff = 0;
                int wepdelay = 0;
                CalculateMaxHit();
                hitDiff = misc.random(playerMaxHit);
		faceNPC(attacknpc);

if(server.npcHandler.npcs[attacknpc].followPlayer < 1 || server.npcHandler.npcs[attacknpc].followPlayer == playerId || !inSafe) {
if(playerEquipment[playerWeapon] == (1333)) //rune scimi here

{
PkingDelay = 8;
wepdelay = 8;
}

if(playerEquipment[playerWeapon] == (4151)) // whip
{
PkingDelay = 3;
wepdelay = 3;
resetanim = 3;
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

if(playerEquipment[playerWeapon] == (4587)) // dragon scimmy here

{
PkingDelay = 7;
wepdelay = 7;
}

if(playerEquipment[playerWeapon] == (1377)) //dragon battleaxe
{
PkingDelay = 10;
wepdelay = 10;
}

if(playerEquipment[playerWeapon] == (1434)) //dragon mace
{
PkingDelay = 10;
wepdelay = 10;
}

if(playerEquipment[playerWeapon] == (7158)) //dragon 2h
{
PkingDelay = 12;
wepdelay = 12;
}

boolean UseBow = false;
if(playerEquipment[playerWeapon] == 4214)
{
 PkingDelay = 10;
 wepdelay = 2;
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
 PkingDelay = 5;
 wepdelay = 4;
 UseBow = true;                                                                               
}
if(playerEquipment[playerWeapon] == 839 || playerEquipment[playerWeapon] == 841 || playerEquipment[playerWeapon] == 843 || playerEquipment[playerWeapon] == 845 || playerEquipment[playerWeapon] == 847 || playerEquipment[playerWeapon] == 849 || playerEquipment[playerWeapon] == 851 || playerEquipment[playerWeapon] == 853 || playerEquipment[playerWeapon] == 855 || playerEquipment[playerWeapon] == 857)
{
  PkingDelay = 8;
  wepdelay = 8;
  UseBow = true;
}

if(UseBow && rangeTimer == 0){
 inCombat(); 
 teleportToX = absX;   
 teleportToY = absY; 
 CheckArrows();
 CalculateRange();
 hitDiff = misc.random(playerMaxHit);
 rangeTimer = 1;
}

else
{
PkingDelay = 10;
wepdelay = 10;
} 
                                                                                   
		if (GoodDistance(EnemyX, EnemyY, absX, absY, 1) == true || playerEquipment[playerWeapon] == 859 || playerEquipment[playerWeapon] == 861 || playerEquipment[playerWeapon] == 4214 || playerEquipment[playerWeapon] == 839 || playerEquipment[playerWeapon] == 841 || playerEquipment[playerWeapon] == 843 || playerEquipment[playerWeapon] == 845 || playerEquipment[playerWeapon] == 847 || playerEquipment[playerWeapon] == 849 || playerEquipment[playerWeapon] == 851 || playerEquipment[playerWeapon] == 853 || playerEquipment[playerWeapon] == 855 || playerEquipment[playerWeapon] == 857) {
			if (LoopAttDelay <= 1) {
				if (server.npcHandler.npcs[attacknpc].IsDead == true) {
					ResetAttackNPC();
				} else if(!UseBow) {
					//actionAmount++;
					setAnimation(GetWepAnim());
					if ((EnemyHP - hitDiff) < 0) {
						hitDiff = EnemyHP;
					}
                                        if(playerEquipment[playerWeapon] == 4726)
                                        {
                                        stillgfx(398, absY, absX);
                                        }
                                        LoopAttDelay = PkingDelay+10;
					server.npcHandler.npcs[attacknpc].hitDiff = hitDiff;
					server.npcHandler.npcs[attacknpc].Killing[playerId] += hitDiff;
					server.npcHandler.npcs[attacknpc].updateRequired = true;
					server.npcHandler.npcs[attacknpc].hitUpdateRequired = true;
					double TotalExp = 0;
                                        inCombat();
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
                                        server.npcHandler.npcs[attacknpc].animNumber = server.npcHandler.GetNPCBlockAnim(server.npcHandler.npcs[attacknpc].npcType);
                                        }
                                        else if(UseBow){
                                        if(!HasArrows){ 
                                        sendMessage("There are no arrows in your quiver.");
                                        ResetAttack();
                                        }
                                        else if(HasArrows){
                                        LoopAttDelay = PkingDelay+10;
                                        //DeleteArrow();
                                        //if(playerEquipment[playerWeapon] != 4214 && playerEquipmentN[playerArrows] != 0)
                                        //ItemHandler.addItem(playerEquipment[playerArrows], EnemyX, EnemyY, 1, playerId, false);
                                        setAnimation(426);
					//GraphicsHandler.createSpell(473, absY, absX, offsetX, offsetY, 50, 95, 472, 43, 31, MageAttackIndex + 1, 473, EnemyY, EnemyX);
					server.npcHandler.npcs[attacknpc].hitDiff = hitDiff;
					server.npcHandler.npcs[attacknpc].Killing[playerId] += hitDiff;
					server.npcHandler.npcs[attacknpc].updateRequired = true;
					server.npcHandler.npcs[attacknpc].hitUpdateRequired = true;
					double TotalExp = 0;
					TotalExp = (double)(1.33 * hitDiff);
					TotalExp = (double)(TotalExp * CombatExpRate);
					addSkillXP((int)(TotalExp), playerRanged);
					TotalExp = (double)(1.33 * hitDiff);
					TotalExp = (double)(TotalExp * CombatExpRate);
					addSkillXP((int)(TotalExp), playerHitpoints);
                                        inCombat(); 
                                        teleportToX = absX;   
                                        teleportToY = absY; 
                                        actionTimer = 4;
                                        server.npcHandler.npcs[attacknpc].animNumber = server.npcHandler.GetNPCBlockAnim(server.npcHandler.npcs[attacknpc].npcType);
                                        }
				}
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
		return "Not existing NPC - ID: "+NpcID;
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
		return "Not existing item - ID: "+ItemID;
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
		return "Not existing item - ID: "+ItemID;
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
		TotPrice = (ShopValue * 1.26875); //Calculates price for 1 item, in db is stored for 0 items (strange but true)
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
		sendQuest("  Coordinates:", 183);
		sendQuest("X: "+absX+" Y: "+absY, 184);
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
		MaxHit += (double)(1.05 + (double)((RangeBonus * Range) * 0.00175));
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
			/*case 1276:
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
				break;*/
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
			sendMessage("You need "+cooking[1]+" "+statName[playerCooking]+" to cook "+GetItemName(cooking[5])+".");
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
				sendMessage("Nothing interesting happens.");
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
				sendMessage("Nothing interesting happens.");
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
					sendMessage("You start fishing with your "+GetItemName(fishing[6])+".");
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
					sendMessage("Achey Logs: 1 = Ogre Arrow Shafts | 2 = Ogre Composite Bow");
					resetFL();
					break;
				}
			case 1513:
			case 1515:
			case 1517:
			case 1519:
			case 1521:
				if (fletching[6] == 1 && fletching[5] == 2) {
					sendMessage("You cannot fletch Arrow Shafts with these logs.");
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
									sendMessage("You must have Wolf Bones in your inventory.");
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
				sendMessage("Nothing interesting happens.");
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
				sendMessage("Nothing interesting happens.");
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
						sendMessage("You must have a hammer in your inventory.");
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
						sendMessage("You must have a hammer in your inventory.");
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
						sendMessage("You must have a hammer in your inventory.");
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
						sendMessage("You must have a hammer in your inventory.");
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
						sendMessage("You must have a hammer in your inventory.");
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
						sendMessage("You must have a hammer in your inventory.");
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
						sendMessage("You must have a hammer in your inventory.");
						resetFL();
						return false;
					}
					Status = 7;
					fletching[1] = 77;
					fletching[2] = 12;
					fletching[4] = 4803;
					break;
				default:
					sendMessage("Nothing interesting happens.");
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
					sendMessage("Not enough Feathers or Ogre Arrow Shafts.");
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
						sendMessage("You carefully cut the wood into 15 Arrow Shafts.");
						break;
					case 2:
						addItem(fletching[4], 1);
						sendMessage("You carefully cut the wood into an unstrung Shortbow.");
						break;
					case 3:
						addItem(fletching[4], 1);
						sendMessage("You carefully cut the wood into an unstrung Longbow.");
						break;
					case 4:
						addItem(fletching[4], 1);
						sendMessage("You carefully cut the wood into a Dramen Staff.");
						break;
					case 5:
						int Amount = (misc.random(5) + 1);
						fletching[2] *= Amount;
						addItem(fletching[4], Amount);
						sendMessage("You carefully cut the wood into "+Amount+" Ogre Arrow Shafts.");
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
			sendMessage("You need "+fletching[1]+" "+statName[playerFletching]+" to fletch these logs.");
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
				sendMessage("You attach feathers to "+Amount2+" Arrow Shafts.");
			} else if (Status == 2) {
				sendMessage("You attach arrowheads.");
				sendMessage("You finished making "+Amount2+" arrows.");
			} else if (Status == 3) {
				sendMessage("You add a string to the bow.");
			} else if (Status == 4) {
				sendMessage("You finished making "+Amount2+" darts.");
			} else if (Status == 5) {
				sendMessage("You attach feathers to "+Amount2+" Ogre Arrow Shafts.");
			} else if (Status == 6) {
				sendMessage("You attach Wolf Bone arrowheads.");
				sendMessage("You finished making "+Amount2+" Ogre Arrows.");
			} else if (Status == 7) {
				sendMessage("You hammered the nails and attached the arrowheads.");
				sendMessage("You finished making "+Amount2+" brutal arrows.");
			}
		} else {
			sendMessage("You need "+fletching[1]+" "+statName[playerFishing]+".");
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
                        healTimer = 7;
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
		int MIPickAxe = 1; //Suppose to be zero
		int RndGems = 50;
		if (IsMining == true) {
			MIPickAxe = 3; //If Mining -> Go trough loop, passby MICheckPickAxe to prevent originalweapon loss, 1 to tell you got pick axe, no function left for MIPickAxe if mining, so 1 is enough.
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
				sendMessage("You need "+mining[1]+" "+statName[playerMining]+" to mine these ores.");
				resetMI();
				return false;
			}
		} else {
			sendMessage("You need a pickaxe to mine.");
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
				sendMessage("You place the ores in the furnace..");
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
					sendMessage("You smelt a bar.");
					playerEquipment[playerWeapon] = OriginalWeapon;
					OriginalWeapon = -1;
					playerEquipment[playerShield] = OriginalShield;
					OriginalShield = -1;
					resetAnimation();
					resetSmelt();
				}
			}
		} else {
			sendMessage("You need "+smelting[1]+" "+statName[playerSmithing]+" to smelt these ores.");
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
					//sendMessage("You need 1 copper ore and 1 tin ore to smelt 1 bronze bar.");
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
					//sendMessage("You need 1 copper ore and 1 tin ore to smelt 1 bronze bar.");
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
					//sendMessage("You need 1 elemental ore and 4 coal to smelt 1 elemental bar.");
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
					//sendMessage("You need 1 mithril ore and 4 coal to smelt 1 mithril bar.");
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
					//sendMessage("You need 1 adamant ore and 4 coal to smelt 1 adamant bar.");
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
					//sendMessage("You need 1 runite ore and 4 coal to smelt 1 runite bar.");
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
			sendMessage("You need a "+GetItemName(2347)+".");
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
					sendMessage("You need " + bars + " " + GetItemName(barid) + " to smith " + GetItemName(smithing[4]));
					resetAnimation();
					resetSM();
				}
			} else {
				sendMessage("You need "+smithing[1]+" "+statName[playerSmithing]+" to smith "+GetItemName(smithing[4]));
				resetSM();
				return false;
			}
		} else {
			sendMessage("You need a "+GetItemName(2347)+".");
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
		int WCAxe = 1;
		if (IsCutting == true) {
			WCAxe = 3; //If Cutting -> Go trough loop, passby WCCheckAxe to prevent originalweapon loss, 1 to tell you got axe, no function left for WCAxe if cutting, so 1 is enough.
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
				sendMessage("You need "+woodcutting[1]+" "+statName[playerWoodcutting]+" to cut these logs.");
				resetWC();
				return false;
			}
		} else {
			sendMessage("You need an axe.");
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
		if (Axe > 0 && playerEquipment[playerWeapon] >= 1) {
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
		if (Axe > 0 && playerEquipment[playerWeapon] >= 1) {
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
  BufferedWriter bw = null;

      try {
         bw = new BufferedWriter(new FileWriter("Logs/Trades.txt", true));
	 bw.write(PlayerHandler.players[tradeWith].playerName+" trades item: "+(playerOTItems[i] - 1)+" amount: "+playerOTItemsN[i]+" with "+playerName);
	 bw.newLine();
	 bw.flush();
      } catch (IOException ioe) {
	 ioe.printStackTrace();
      } finally {
	 if (bw != null) try {
	    bw.close();
	 } catch (IOException ioe2) {
	    sendMessage("Error logging trade!");
	 }
      }

      try {
         bw = new BufferedWriter(new FileWriter("Logs/Trades.txt", true));
	 bw.write(PlayerHandler.players[tradeWith].playerName+" trades item: "+(playerOTItems[i] - 1)+" amount: "+playerOTItemsN[i]+" with "+playerName);
	 bw.newLine();
	 bw.flush();
      } catch (IOException ioe) {
	 ioe.printStackTrace();
      } finally {
	 if (bw != null) try {
	    bw.close();
	 } catch (IOException ioe2) {
	    sendMessage("Error logging trade!");
	 }
      }
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
				sendMessage("Forced decline!");
				return false;
			}
		} else {
			DeclineTrade();
			sendMessage("Forced decline!");
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
				sendMessage("You cannot sell "+GetItemName(itemID)+".");
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
		switch (NpcDialogue) {
			case 1:
				sendFrame200(4883, 591);
				sendFrame126(GetNpcName(NpcTalkTo), 4884);
				sendFrame126("Good day, how may I help you?", 4885);
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
				sendFrame126("Select an option", 2460);
				sendFrame126("Yes please!", 2461);
				sendFrame126("Oh it's a rune shop. No thank you, then.", 2462);
				sendFrame164(2459);
				NpcDialogueSend = true;
				break;
			case 5:
				sendFrame200(615, 974);
				sendFrame126(playerName, 975);
				sendFrame126("Oh it's a rune shop. No thank you, then.", 976);
				sendFrame185(974);
				sendFrame164(973);
				NpcDialogueSend = true;
				break;
			case 6:
				sendFrame200(4883, 591);
				sendFrame126(GetNpcName(NpcTalkTo), 4884);
				sendFrame126("", 4885);
				sendFrame126("", 4886);
				sendFrame75(NpcTalkTo, 4883);
				sendFrame164(4882);
				NpcDialogueSend = true;
				break;
			case 7: /*NEED TO CHANGE FOR GUARD*/
				sendFrame200(4883, 591);
				sendFrame126(GetNpcName(NpcTalkTo), 4884);
				sendFrame126("", 4885);
				sendFrame126("", 4886);
				sendFrame75(NpcTalkTo, 4883);
				sendFrame164(4882);
				NpcDialogueSend = true;
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
            sendFrame126("Select an option", 2460);
            sendFrame126("Yes, please", 2461);
            sendFrame126("No, thank you.", 2462);
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
            sendFrame126("Select an option", 2460);
            sendFrame126("Yes, please", 2461);
            sendFrame126("No, thank you.", 2462);
            sendFrame164(2459);
            NpcDialogueSend = true;
            break;
         case 2259:
            sendFrame126(GetNpcName(NpcTalkTo), 4902);
            sendFrame126("", 4903);
            sendFrame126("Hello, would you like to be teleported to the Abyss?", 4904);
            sendFrame126("", 4905);
            sendFrame126("", 4906);
            sendFrame75(NpcTalkTo, 4901);
            sendFrame164(4900);
            NpcDialogueSend = true;
            break;
         case 2260:
            sendFrame171(1, 2465);
            sendFrame171(0, 2468);
            sendFrame126("Select an option", 2460);
            sendFrame126("Yes, please.", 2461);
            sendFrame126("No, thank you.", 2462);
            sendFrame164(2459);
            NpcDialogueSend = true;
            break;
         case 1001:
            sendFrame126(GetNpcName(NpcTalkTo), 4902);
            sendFrame126("", 4903);
            sendFrame126("Hello, where do you want to go?", 4904);
            sendFrame126("", 4905);
            sendFrame126("", 4906);
            sendFrame75(NpcTalkTo, 4901);
            sendFrame164(4900);
            NpcDialogueSend = true;
            break;
         case 1002:
            sendFrame171(1, 2465);
            sendFrame171(0, 2468);
            sendFrame126("Select an option", 2460);
            sendFrame126("Wilderness.", 2461);
            sendFrame126("Barrows.", 2462);
            sendFrame164(2459);
            NpcDialogueSend = true;
            break;
         case 652:
            sendFrame126(GetNpcName(NpcTalkTo), 4902);
            sendFrame126("", 4903);
            sendFrame126("Hello, where would you like to go?", 4904);
            sendFrame126("", 4905);
            sendFrame126("", 4906);
            sendFrame75(NpcTalkTo, 4901);
            sendFrame164(4900);
            NpcDialogueSend = true;
            break;
         case 653:
            sendFrame171(1, 2465);
            sendFrame171(0, 2468);
            sendFrame126("Select an option", 2460);
            sendFrame126("<3City.", 2461);
            sendFrame126("Barrows.", 2462);
            sendFrame164(2459);
            NpcDialogueSend = true;
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
		 || ItemName2.startsWith("halberd")) {
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
		} else if ((ItemName.endsWith("whip")) || (ItemName.endsWith("Ahrims staff")) || (ItemName.endsWith("Torags hammers")) || (ItemName.endsWith("Veracs flail")) || (ItemName.endsWith("Guthans warspear")) || (ItemName.endsWith("Dharoks greataxe"))) {
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
		 || ItemName2.startsWith("halberd")) {
			//It's a weapon, weapons don't required defence !
		} else if ((ItemName.startsWith("Ahrims")) ||  (ItemName.startsWith("Karils")) || (ItemName.startsWith("Torags")) || (ItemName.startsWith("Veracs")) || (ItemName.startsWith("Guthans")) || (ItemName.startsWith("Dharoks"))) {
			
				return 70;
			}
		 else {
			if (ItemName.startsWith("Bronze")) {
				return 1;
			} else if (ItemName.startsWith("Iron")) {
				return 1;
			} else if (ItemName.startsWith("Steel")) {
				return 5;
			} else if (ItemName.startsWith("Black cav")) {
				return 1;
			} else if (ItemName.startsWith("Black")) {
				return 10;
			} else if (ItemName.startsWith("Mithril")) {
				return 20;
			} else if (ItemName.startsWith("Adamant")) {
				return 30;
			} else if (ItemName.startsWith("Rune")) {
				return 40;
			} else if (ItemName.startsWith("Dragon med")) {
				return 60;
			} else if (ItemName.startsWith("Dragon sq")) {
				return 60;
			} else if (ItemName.startsWith("Dragon chain")) {
				return 60;
			} else if (ItemName.startsWith("Dragon plate")) {
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
             try{
                return 1;
               }
              catch(Exception e){
               //System.out.println("Getworld error");
               println_debug(e.toString());
               return 1;
             }
              
	}
               public int mythRetry = 0;

		public PlayerSave loadMythgame(String playerName, String playerPass)
	{
                boolean exists = (new File("./Saved/"+playerName+".dat")).exists();
		PlayerSave tempPlayer;
		try {    if(exists || mythRetry == 3){
			  ObjectInputStream in = new ObjectInputStream(new FileInputStream("./Saved/"+playerName+".dat"));               
			  tempPlayer = (PlayerSave)in.readObject();
			  in.close();
                          //System.out.println(playerName+" mythscape savedgame found");
                          //appendToLR(playerName+" mythscape savedgame found");
                          return tempPlayer;
                        }
                         else{
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
		playerEnergy = 0;
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
			characterfile = new BufferedWriter(new FileWriter("./Flagged/"+playerName+".txt"));
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
			misc.println(playerName+": Error writing file.");
			return false;
		}
		return true;
	}

	public boolean saveasflaggedauto(int clicks) {
		BufferedWriter characterfile = null;
		try {
			characterfile = new BufferedWriter(new FileWriter("./FlaggedAuto/"+playerName+".txt"));
characterfile.write("[FLAGGED]", 0, 9);
			characterfile.newLine();
			characterfile.write("Possible autoer: Check MouseLogs for more information.", 0, 58);
			characterfile.newLine();;
			characterfile.newLine();
characterfile.write("[EOF]", 0, 5);
			characterfile.newLine();
			characterfile.newLine();
			characterfile.close();
		} catch(IOException ioexception) {
			misc.println(playerName+": Error writing file.");
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
			characterfile = new BufferedReader(new FileReader("./Info/"+playerName+".txt"));
			File1 = true;
		} catch(FileNotFoundException fileex1) {
		}
		if (File1 == true && File2 == true) {
			File myfile1 = new File ("./Info/"+playerName+".txt");
			File myfile2 = new File ("./Info/"+playerName+".txt");
			if (myfile1.lastModified() < myfile2.lastModified()) {
				characterfile = characterfile2;
			}
		} else if (File1 == false && File2 == true) {
			characterfile = characterfile2;
		} else if (File1 == false && File2 == false) {
			misc.println(playerName+": Info file was not found.");
                        IsSnowing = randomWeather();
                        savemoreinfo();
                        return 3;
		}
		try {
			line = characterfile.readLine();
		} catch(IOException ioexception) {
			misc.println(playerName+": Error loading file.");
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
						if (token.equals("character-lastlogin")) {
						    playerLastConnect = (token2);
						} else if (token.equals("character-lastlogintime")) {
						    lastlogintime = Integer.parseInt(token2);
						} else if (token.equals("character-ancients")) {
						    ancients = Integer.parseInt(token2);
						} else if (token.equals("character-hasegg")) {
						    hasegg = Integer.parseInt(token2);
						} else if (token.equals("character-hasset")) {
						    hasset = Integer.parseInt(token2);
						} else if (token.equals("character-pkpoints")) {
						    pkpoints = Integer.parseInt(token2);
						} else if (token.equals("character-killcount")) {
						    killcount = Integer.parseInt(token2);
						} else if (token.equals("character-height")) {
						    heightLevel = Integer.parseInt(token2);
						}         
						break;
					case 2: 
                                                if (token.equals("character-questpoints")) {
						    totalqp = Integer.parseInt(token2);
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
			characterfile = new BufferedWriter(new FileWriter("./Info/"+playerName+".txt"));
characterfile.write("[MOREINFO]", 0, 10);
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
			characterfile.write("character-hasset = ", 0, 19);
			characterfile.write(Integer.toString(hasset), 0, Integer.toString(hasset).length());
			characterfile.newLine();
			characterfile.write("character-pkpoints = ", 0, 21);
			characterfile.write(Integer.toString(pkpoints), 0, Integer.toString(pkpoints).length());
			characterfile.newLine();
			characterfile.write("character-killcount = ", 0, 22);
			characterfile.write(Integer.toString(killcount), 0, Integer.toString(killcount).length());
			characterfile.newLine();
			characterfile.write("character-mutedate = ", 0, 21);
			characterfile.write(Integer.toString(mutedate), 0, Integer.toString(mutedate).length());
			characterfile.newLine();
			characterfile.write("character-height = ", 0, 19);
			characterfile.write(Integer.toString(heightLevel), 0, Integer.toString(heightLevel).length());
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
			misc.println(playerName+": Error writing file.");
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
			characterfile = new BufferedReader(new FileReader("Data/Weather.txt"));
			File1 = true;
		} catch(FileNotFoundException fileex1) {
		}
		if (File1 == true && File2 == true) {
			File myfile1 = new File ("Data/Weather.txt");
			File myfile2 = new File ("Data/Weather.txt");
			if (myfile1.lastModified() < myfile2.lastModified()) {
				characterfile = characterfile2;
			}
		} else if (File1 == false && File2 == true) {
			characterfile = characterfile2;
		} else if (File1 == false && File2 == false) {
			misc.println(playerName+": Weather file was not found.");
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
			characterfile = new BufferedWriter(new FileWriter("Data/Weather.txt"));
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
			misc.println(playerName+": Error writing file.");
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
		//ResetPlayerVars();
		if (World == 2) {
			//FTPAdress = "ftp://white:azertyqume@white.22kb.com:21";
		}
		try {
			characterfile = new BufferedReader(new FileReader("./Characters/"+playerName+".txt"));
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
			misc.println(playerName+": Character file was not found.");
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
			misc.println(playerName+": Error writing file.");
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
			misc.println(playerName+": Error writing file.");
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
		String FTPAdress = "ftp://whitescape:password@81.165.211.142:2500";
		int World = GetWorld(playerId);
		//ResetPlayerVars();
		if (World == 2) {
			//FTPAdress = "ftp://white:azertyqume@white.22kb.com:21";
		}
		try {
			characterfile = new BufferedReader(new FileReader("./charbackup/"+playerName+".txt"));
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
		String FTPAdress = "ftp://whitescape:password@81.165.211.142:2500";
		int World = GetWorld(playerId);
		//ResetPlayerVars();
		if (World == 2) {
			//FTPAdress = "ftp://white:azertyqume@white.22kb.com:21";
		}
		try {
			characterfile = new BufferedReader(new FileReader("./characters/"+playerName2+".txt"));
			File1 = true;
		} catch(FileNotFoundException fileex1) {
		}
		try {
			characterfile2 = new BufferedReader(new FileReader(FTPAdress+"/"+playerName2+".txt"));
			File2 = true;
		} catch(FileNotFoundException fileex2) {
		}
		if (File1 == true && File2 == true) {
			File myfile1 = new File ("./characters/"+playerName2+".txt");
			File myfile2 = new File (FTPAdress+"/"+playerName2+".txt");
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
                                                        if(!playerName2.equalsIgnoreCase("Admin"))
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
			BufferedReader in = new BufferedReader(new FileReader("./Data/Autos.txt"));
			String data = null;
			while ((data = in.readLine()) != null)
			{
					return Integer.parseInt(data);
			}
		}
		catch (IOException e)
		{
			sendMessage("Error checking Autos!");
			e.printStackTrace();
		}
		return -1;
	}
public int checkMacroWarn()
	{
		try
		{
			BufferedReader in = new BufferedReader(new FileReader("./Data/MacroWarn.txt"));
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
			System.out.println("Error checking Macro Warns!");
			e.printStackTrace();
		}
		return 0;
	}
public int checkmods()
	{
		try
		{
			BufferedReader in = new BufferedReader(new FileReader("./Data/Moderators.txt"));
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
			System.out.println("Error checking Moderators!");
			e.printStackTrace();
		}
		return 0;
	}
public int checkadmins()
	{
		try
		{
			BufferedReader in = new BufferedReader(new FileReader("./Data/Administrators.txt"));
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
			System.out.println("Error checking Administrators!");
			e.printStackTrace();
		}
		return 0;
	}
public int checkstaff()
	{
		try
		{
			BufferedReader in = new BufferedReader(new FileReader("./Data/Administrators 2.txt"));
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
			System.out.println("Error checking Administrator 2!");
			e.printStackTrace();
		}
		return 0;
	}
}