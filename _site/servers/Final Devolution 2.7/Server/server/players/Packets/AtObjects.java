package server.players.Packets;

import server.*;
import server.players.*;
import server.items.*;
import server.npcs.*;
import server.util.*;
import server.world.*;
import server.players.Quests.*;
import server.players.Skills.*;
import server.players.MiscHandlers.*;
public class AtObjects {

	public client client;
	public int AtObject3 = 70, AtObject2 = 252, AtObject1 = 132;

	public AtObjects(client c) {
		c = c;
	}

	public void AtObjects1(client c, int objectIDz, int objectXz, int objectYz) {
			if (c.getStatus().isBusy()) {
				return;
			}	
			//c.CAM().sendMessage("ID = "+objectIDz+".");
			char this1 = '\0';
                		char this2 = '\0';
			if (misc.random(2) == 1) {
                    			this1 = '\u0DE0';
                    			this2 = '\u25E2';
                		} else if (misc.random(2) == 1) {
                    			this1 = '\u0DDC';
                    			this2 = '\u25DF';
                		} else if (misc.random(2) == 1) {
                    			this1 = '\u0DE3';
                    			this2 = '\u25DE';
                		} else {
                    			this1 = '\u0DE0';
                    			this2 = '\u25DB';
                		}
			c.TurnPlayerTo(objectXz, objectYz);
			if (objectIDz > 0 && objectIDz < 99999) {
				c.skillX = objectXz;
				c.skillY = objectYz;
			}
		switch(objectIDz) {
			case 1568: //trapdoor to dags
				if (c.GoodDistance(c.skillX, c.skillY, c.getX(), c.getY(), 1)) {
					c.heightLevel = 0;
					c.teleportToX = 2515;
					c.teleportToY = 4631;
				}
			break;
			case 4413: //gates at dags
				if (c.GoodDistance(c.skillX, c.skillY, c.getX(), c.getY(), 1)) {
					c.teleportToX = 3096;
					c.teleportToY = 3468;
					c.heightLevel = 0;
					
				}
			break;
			case 2517:
			if (objectXz == 2784 && objectYz == 3286) {
				if (c.absX == 2784 && c.absY == 3287 || c.absX == 2785 && c.absY == 3287 || c.absX == 2785 && c.absY == 3286 || c.absX == 2785 && c.absY == 3285
					|| c.absX == 2738 && c.absY == 3285 || c.absX == 2783 && c.absY == 3287 || c.absX == 2783 && c.absY == 3286) {
					if (c.QH().q1 == 4) {
						c.heightLevel = 1;
						c.teleportToX = c.absX;
						c.teleportToY = c.absY;
					} else {
						c.CAM().sendMessage("You can not go up here yet.");
					}
				}
			}
			break;
			
				
			case 9398://deposit box
				c.CAM().sendFrame126("The Bank of "+Constants.SERVER_NAME+" - Deposit Box", 7421);
				c.CAM().sendFrame248(4465, 197);
				c.resetItems(7423);
			break;
			case 1746:
			if(objectXz == 2784 && objectYz == 3286) {
				if (c.absX == 2784 && c.absY == 3287 || c.absX == 2785 && c.absY == 3287 || c.absX == 2785 && c.absY == 3286 || c.absX == 2785 && c.absY == 3285
					|| c.absX == 2738 && c.absY == 3285 || c.absX == 2783 && c.absY == 3287 || c.absX == 2783 && c.absY == 3286) {
						c.heightLevel = 0;
						c.teleportToX = c.absX;
						c.teleportToY = c.absY;
				}
			}
			break;
			case 354:
				if (c.QH().q1 == 7) {
					if (c.absX == 2764 && c.absY == 3275 || c.absX == 2765 && c.absY == 3276) {
						c.addItem(1273, 1);
						c.CAM().sendMessage("You have found Drogo's pick-axe.");
						c.NpcDialogue = 592;
						c.QH().q1 = 8;
					}	
				}
			if (c.QH().q1 == 1) {
				if (c.absX == 2762 && c.absY == 3286 || c.absX == 2763 && c.absY == 3286 || c.absX == 2763 && c.absY == 3287 
					|| c.absX == 2762 && c.absY == 3288 || c.absX == 2763 && c.absY == 3288) {
					c.addItem(954, 1);
					c.QH().q1 = 2;
					c.CAM().sendMessage("You searched through the crate and found some rope.");
				}
			}
			break;
			case 355://create
			break;

			case 6822:
				if (!c.karilSpawned) {
					c.karilSpawned = true;
					//server.npcHandler.newNPC(2028, absX -1, absY, heightLevel, 0, 0, 0, 0, 0, 200);
					
				} else {
					c.CAM().sendMessage("You have already Killed Karil; you may reset it using ::resetKaril");
				}
			break;
				case 1277:
					c.skillX = objectXz;
					c.skillY = objectYz;
					new Woodcutting(c, objectIDz, "NM", objectXz, objectYz);
				break;
				case 1278:
					c.skillX = objectXz;
					c.skillY = objectYz;
					new Woodcutting(c, objectIDz, "NM", objectXz, objectYz);
				break;
				case 1279:
					c.skillX = objectXz;
					c.skillY = objectYz;
					new Woodcutting(c, objectIDz, "NM", objectXz, objectYz);
				break;
				case 1280:
					c.skillX = objectXz;
					c.skillY = objectYz;
					new Woodcutting(c, objectIDz, "NM", objectXz, objectYz);
				break;
				case 1303:
					c.skillX = objectXz;
					c.skillY = objectYz;
					new Woodcutting(c, objectIDz, "NM", objectXz, objectYz);
				break;
				case 1304:
					c.skillX = objectXz;
					c.skillY = objectYz;
					new Woodcutting(c, objectIDz, "NM", objectXz, objectYz);
				break;
				case 1305:
					c.skillX = objectXz;
					c.skillY = objectYz;
					new Woodcutting(c, objectIDz, "NM", objectXz, objectYz);
				break;
				case 1310:
					c.skillX = objectXz;
					c.skillY = objectYz;
					new Woodcutting(c, objectIDz, "NM", objectXz, objectYz);
				break;
				case 1315:
					c.skillX = objectXz;
					c.skillY = objectYz;
					new Woodcutting(c, objectIDz, "NM", objectXz, objectYz);
				break;
				case 1316:
					c.skillX = objectXz;
					c.skillY = objectYz;
					new Woodcutting(c, objectIDz, "NM", objectXz, objectYz);
				break;
				case 1330:
					c.skillX = objectXz;
					c.skillY = objectYz;
					new Woodcutting(c, objectIDz, "NM", objectXz, objectYz);
				break;
				case 1331:
					c.skillX = objectXz;
					c.skillY = objectYz;
					new Woodcutting(c, objectIDz, "NM", objectXz, objectYz);
				break;
				case 1332:
					c.skillX = objectXz;
					c.skillY = objectYz;
					new Woodcutting(c, objectIDz, "NM", objectXz, objectYz);
				break;
				case 1281:
					c.skillX = objectXz;
					c.skillY = objectYz;
					new Woodcutting(c, objectIDz, "OAK", objectXz, objectYz);
				break;
				case 3037:
					c.skillX = objectXz;
					c.skillY = objectYz;
					new Woodcutting(c, objectIDz, "OAK", objectXz, objectYz);
				break;
				case 308:
					c.skillX = objectXz;
					c.skillY = objectYz;
					new Woodcutting(c, objectIDz, "WILL", objectXz, objectYz);
				break;
				case 5551:
					c.skillX = objectXz;
					c.skillY = objectYz;
					new Woodcutting(c, objectIDz, "WILL", objectXz, objectYz);
				break;
				case 5552:
					c.skillX = objectXz;
					c.skillY = objectYz;
					new Woodcutting(c, objectIDz, "WILL", objectXz, objectYz);
				break;
				case 5553:
					c.skillX = objectXz;
					c.skillY = objectYz;
					new Woodcutting(c, objectIDz, "WILL", objectXz, objectYz);
				break;
				case 1307:
					c.skillX = objectXz;
					c.skillY = objectYz;
					new Woodcutting(c, objectIDz, "MAP", objectXz, objectYz);
				break;
				case 4574:
					c.skillX = objectXz;
					c.skillY = objectYz;
					new Woodcutting(c, objectIDz, "MAP", objectXz, objectYz);
				break;
				case 1309:
					c.skillX = objectXz;
					c.skillY = objectYz;
					new Woodcutting(c, objectIDz, "YEW", objectXz, objectYz);
				break;
				case 1306:
					c.skillX = objectXz;
					c.skillY = objectYz;
					new Woodcutting(c, objectIDz, "MAGE", objectXz, objectYz);
				break;
/* AGILITY */
				case 2295:
					c.AL().WalkingOnLogs(c, 1, 2474, 3436, 0, -7, 1);
					c.ag1 = 1;
				break;
				case 2285:
					c.AN().ClimbNets(c, 1, 2471, 3426, 2472, 3426, 2473, 3426, 2474, 3426, 2475, 3426, 2476, 3426);
					c.agilityhalf = true;
					c.ag2 = 1;
				break;
				case 2313:
					c.ABS().RunBranches(c, 1, 2473, 3423, 2474, 3422, 2472, 3422, -1, -1);
					c.ag3 = 1;
				break;
				case 2312:
					c.AL().WalkingOnLogs(c, 1, 2477, 3420, 6, 0, 1);
					c.ag4 = 1;
				break;
				case 2314:
					c.ABS().RunBranches(c, 1, 2485, 3419, 2486, 3420, 2486, 3418, 2485, 3420); 
					c.ag5 = 1;
				break;
				case 2286:
					c.AN().ClimbNets(c, 1, 2483, 3425, 2484, 3425, 2485, 3425, 2486, 3425, 2487, 3425, 2488, 3425);
					c.ag6 = 1;
					c.agilityhalf = false;
					//c.getAgil().bonus = true;
				break;
				case  154:
					c.AP().RunPipes(c, 1, 0, 7);
				break;
				case 4058:
					c.AP().RunPipes(c, 1, 0, 7);
				break;
/* END OF AGILITY */
				case 7139:
				case 2478://Air
					if (c.playerHasItem(1438, 1) && c.playerHasItem(7936, 1)) {
						c.getRuneCraft().runecraft(c, 1, 30, 556, 11, 22, 33, 44, 55, 66, 77, 88, 99);
					} else if (c.playerEquipment[c.playerHat] == 5527 && c.playerHasItem(7936, 1)) {
						c.getRuneCraft().runecraft(c, 1, 30, 556, 11, 22, 33, 44, 55, 66, 77, 88, 99);
					} else {
						c.CAM().sendMessage("You either have no talisman, or a tiara, or ess, to cast runecrafting.");
					}
				break;
				case 7140:
				case 2479://Mind
					if (c.playerHasItem(1448, 1) && c.playerHasItem(7936, 1)) {
						c.getRuneCraft().runecraft(c, 2, 35, 558, 14, 28, 42, 56, 70, 84, 98, 150, 150);
					} else if (c.playerEquipment[c.playerHat] == 5529 && c.playerHasItem(7936, 1)) {
						c.getRuneCraft().runecraft(c, 2, 35, 558, 14, 28, 42, 56, 70, 84, 98, 150, 150);
					} else {
						c.CAM().sendMessage("You either have no talisman, or a tiara, or ess, to cast runecrafting.");
					}
				break;
				case 7137:
				case 2480://Water
					if (c.playerHasItem(1444, 1) && c.playerHasItem(7936, 1)) {
						c.getRuneCraft().runecraft(c, 5, 40, 555, 19, 38, 57, 76, 95, 150, 150, 150, 150);
					} else if (c.playerEquipment[c.playerHat] == 5531 && c.playerHasItem(7936, 1)) {
						c.getRuneCraft().runecraft(c, 5, 40, 555, 19, 38, 57, 76, 95, 150, 150, 150, 150);
					} else {
						c.CAM().sendMessage("You either have no talisman, or a tiara, or ess, to cast runecrafting.");
					}
				break;
				case 7130:
				case 2481://Earth
					if (c.playerHasItem(1440, 1) && c.playerHasItem(7936, 1)) {
						c.getRuneCraft().runecraft(c, 9, 45, 557, 26, 52, 78, 150, 150, 150, 150, 150, 150);
					} else if (c.playerEquipment[c.playerHat] == 5535 && c.playerHasItem(7936, 1)) {
						c.getRuneCraft().runecraft(c, 9, 45, 557, 26, 52, 78, 150, 150, 150, 150, 150, 150);
					} else {
						c.CAM().sendMessage("You either have no talisman, or a tiara, or ess, to cast runecrafting.");
					}
				break;
				case 7129:
				case 4091://Fire
					if (c.playerHasItem(1442, 1) && c.playerHasItem(7936, 1)) {
						c.getRuneCraft().runecraft(c, 14, 50, 554, 35, 70, 150, 150, 150, 150, 150, 150, 150);
					} else if (c.playerEquipment[c.playerHat] == 5537 && c.playerHasItem(7936, 1)) {
						c.getRuneCraft().runecraft(c, 14, 50, 554, 35, 70, 150, 150, 150, 150, 150, 150, 150);
					} else {
						c.CAM().sendMessage("You either have no talisman, or a tiara, or ess, to cast runecrafting.");
					}
				break;
				case 7131:
				case 2483://Body
					if (c.playerHasItem(1446, 1) && c.playerHasItem(7936, 1)) {
						c.getRuneCraft().runecraft(c, 20, 45, 559, 46, 92, 150, 150, 150, 150, 150, 150, 150);
					} else if (c.playerEquipment[c.playerHat] == 5533 && c.playerHasItem(7936, 1)) {
						c.getRuneCraft().runecraft(c, 20, 45, 559, 46, 92, 150, 150, 150, 150, 150, 150, 150);
					} else {
						c.CAM().sendMessage("You either have no talisman, or a tiara, or ess, to cast runecrafting.");
					}
				break;
				case 7132:
				case 2484://Cosmic
					if (c.playerHasItem(1454, 1) && c.playerHasItem(7936, 1)) {
						c.getRuneCraft().runecraft(c, 27, 65, 564, 59, 150, 150, 150, 150, 150, 150, 150, 150);
					} else if (c.playerEquipment[c.playerHat] == 5539 && c.playerHasItem(7936, 1)) {
						c.getRuneCraft().runecraft(c, 27, 65, 564, 59, 150, 150, 150, 150, 150, 150, 150, 150);
					} else {
						c.CAM().sendMessage("You either have no talisman, or a tiara, or ess, to cast runecrafting.");
					}
				break;
				case 7134:
				case 2487://Chaos
					if (c.playerHasItem(1452, 1) && c.playerHasItem(7936, 1)) {
						c.getRuneCraft().runecraft(c, 35, 78, 562, 74, 150, 150, 150, 150, 150, 150, 150, 150);
					} else if (c.playerEquipment[c.playerHat] == 5543 && c.playerHasItem(7936, 1)) {
						c.getRuneCraft().runecraft(c, 35, 78, 562, 74, 150, 150, 150, 150, 150, 150, 150, 150);
					} else {
						c.CAM().sendMessage("You either have no talisman, or a tiara, or ess, to cast runecrafting.");
					}
				break;
				case 7133:
				case 2486://Nature
					if (c.playerHasItem(1462, 1) && c.playerHasItem(7936, 1)) {
						c.getRuneCraft().runecraft(c, 44, 120, 561, 91, 150, 150, 150, 150, 150, 150, 150, 150);
					} else if (c.playerEquipment[c.playerHat] == 5541 && c.playerHasItem(7936, 1)) {
						c.getRuneCraft().runecraft(c, 44, 120, 561, 91, 150, 150, 150, 150, 150, 150, 150, 150);
					} else {
						c.CAM().sendMessage("You either have no talisman, or a tiara, or ess, to cast runecrafting.");
					}
				break;
				case 7135:
				case 2485://Law
					if (c.playerHasItem(1458, 1) && c.playerHasItem(7936, 1)) {
						c.getRuneCraft().runecraft(c, 54, 140, 563, 150, 150, 150, 150, 150, 150, 150, 150, 150);
					} else if (c.playerEquipment[c.playerHat] == 5545 && c.playerHasItem(7936, 1)) {
						c.getRuneCraft().runecraft(c, 54, 140, 563, 150, 150, 150, 150, 150, 150, 150, 150, 150);
					} else {
						c.CAM().sendMessage("You either have no talisman, or a tiara, or ess, to cast runecrafting.");
					}
				break;
				case 7136:
				case 2488://Death
					if (c.playerHasItem(1456, 1) && c.playerHasItem(7936, 1)) {
						c.getRuneCraft().runecraft(c, 65, 150, 560, 150, 150, 150, 150, 150, 150, 150, 150, 150);
					} else if (c.playerEquipment[c.playerHat] == 5547 && c.playerHasItem(7936, 1)) {
						c.getRuneCraft().runecraft(c, 65, 150, 560, 150, 150, 150, 150, 150, 150, 150, 150, 150);
					} else {
						c.CAM().sendMessage("You either have no talisman, or a tiara, or ess, to cast runecrafting.");
					}
				break;
				case 7141://Blood
					if (c.playerHasItem(1450, 1) && c.playerHasItem(7936, 1)) {
						c.getRuneCraft().runecraft(c, 75, 250, 565, 565, 150, 150, 150, 150, 150, 150, 150, 150);
					} else if (c.playerEquipment[c.playerHat] == 5549 && c.playerHasItem(7936, 1)) {
						c.getRuneCraft().runecraft(c, 75, 250, 565, 565, 150, 150, 150, 150, 150, 150, 150, 150);
					} else {
						c.CAM().sendMessage("You either have no talisman, or a tiara, or ess, to cast runecrafting.");
					}
				break;
				case 7138://Soul
					if (c.playerHasItem(1460, 1) && c.playerHasItem(7936, 1)) {
						c.getRuneCraft().runecraft(c, 82, 350, 566, 566, 150, 150, 150, 150, 150, 150, 150, 150);
					} else if (c.playerEquipment[c.playerHat] == 5551 && c.playerHasItem(7936, 1)) {
						c.getRuneCraft().runecraft(c, 82, 350, 566, 566, 150, 150, 150, 150, 150, 150, 150, 150);
					} else {
						c.CAM().sendMessage("You either have no talisman, or a tiara, or ess, to cast runecrafting.");
					}
				break;
				case 2094:
					c.skillX = objectXz;
					c.skillY = objectYz;
					new Mining(c, objectIDz, "TIN", objectXz, objectYz);
					break;
				case 2090:
					c.skillX = objectXz;
					c.skillY = objectYz;
					new Mining(c, objectIDz, "COPPER", objectXz, objectYz);
					break;
				case 2091:
					c.skillX = objectXz;
					c.skillY = objectYz;
					new Mining(c, objectIDz, "COPPER", objectXz, objectYz);
					break;
				case 2092:
					c.skillX = objectXz;
					c.skillY = objectYz;
					new Mining(c, objectIDz, "IRON", objectXz, objectYz);
					break;
				case 2093:
					c.skillX = objectXz;
					c.skillY = objectYz;
					new Mining(c, objectIDz, "IRON", objectXz, objectYz);
					break;
				case 2096:
					c.skillX = objectXz;
					c.skillY = objectYz;
					new Mining(c, objectIDz, "COAL", objectXz, objectYz);
					break;
				case 2102:
					c.skillX = objectXz;
					c.skillY = objectYz;
					new Mining(c, objectIDz, "MITH", objectXz, objectYz);
					break;
				case 2103:
					c.skillX = objectXz;
					c.skillY = objectYz;
					new Mining(c, objectIDz, "MITH", objectXz, objectYz);
					break;
				case 2104:
					c.skillX = objectXz;
					c.skillY = objectYz;
					new Mining(c, objectIDz, "ADDY", objectXz, objectYz);
					break;
				case 2105:
					c.skillX = objectXz;
					c.skillY = objectYz;
					new Mining(c, objectIDz, "ADDY", objectXz, objectYz);
					break;
				case 2106:
					c.skillX = objectXz;
					c.skillY = objectYz;
					new Mining(c, objectIDz, "RUNE", objectXz, objectYz);
					break;
				case 2107:
					c.skillX = objectXz;
					c.skillY = objectYz;
					new Mining(c, objectIDz, "RUNE", objectXz, objectYz);
					break;
				case 2095:
					c.mn.startEssMine(objectIDz);
					break;
				case 2491:
					c.mn.startEssMine(objectIDz);
					break;

				case 1276:
					if (c.playerHasItem(1351)) {
						c.skillX = objectXz;
						c.skillY = objectYz;
						new Woodcutting(c, objectIDz, "NM", objectXz, objectYz);
					}
					else if (!c.playerHasItem(1351)) {
						c.CAM().sendMessage("You need a axe!");
					}
				break;

				case 6552:
					if (c.ancients == 1) {
						c.setSidebarInterface(6, 1151);
						c.ancients = 0;
						c.CAM().sendMessage("You convert to normal magic!");
					} else if (c.ancients == 0 || c.ancients == 2) {
						c.setSidebarInterface(6, 12855); // magic tab (ancient = 12855);
						c.ancients = 1;
						c.CAM().sendMessage("You convert to ancient magicks!");
					}
				break;

				case 410:
					if (c.ancients != 0){
						c.setSidebarInterface(6, 1151);
						c.ancients = 0;
						c.CAM().sendMessage("You convert to normal magic!");
					} else if (c.ancients == 0 || c.ancients == 1) {
						//c.setSidebarInterface(6, 18787);
						//c.ancients = 2;
						c.CAM().sendMessage("Sorry this is unavaible at this moment!");
					}
					break;

				case 409:
        				if(c.playerLevel[5] == c.getLevelForXP(c.playerXP[5])) {
            					c.CAM().sendMessage("You already have full Prayer points.");
        				} else {
	    					c.setAnimation(645);
            					c.playerLevel[5] = c.getLevelForXP(c.playerXP[5]);
            					c.CAM().sendFrame126("Prayer: "+c.playerLevel[5]+"/"+c.getLevelForXP(c.playerXP[5])+"", 687);
            					c.CAM().sendFrame126("" + c.getLevelForXP(c.playerXP[5]) + "", 4013);
            					c.CAM().sendFrame126("" + c.playerLevel[5] + "", 4012);
            					c.CAM().sendMessage("You recharge your Prayer points.");
        				}  
					break;

			case 2213:
			case 2214:
			case 3045:
			case 5276:
			case 6084:
			case 12759:
				if (c.GoodDistance(c.skillX, c.skillY, c.getX(), c.getY(), 1)) {
					c.skillX = objectXz;
					c.skillY = objectYz;
					c.WanneBank = 1;
				}
			break;

			/* STAIRS */
				case 1747:
				//case 1746:
				case 1738:
				case 1740:
				case 1748:
				if (c.GoodDistance(c.skillX, c.skillY, c.getX(), c.getY(), 1)) {
					c.heightLevel = 1;
					c.teleportToX = c.absX;
					c.teleportToY = c.absY;
				}
				break;
				case 1739:
				//case 1748:
				if (c.GoodDistance(c.skillX, c.skillY, c.getX(), c.getY(), 1)) {
					c.heightLevel = 2;
					c.teleportToX = c.absX;
					c.teleportToY = c.absY;
				}
				break;
				//case 1746:
				//if (c.GoodDistance(c.skillX, c.skillY, c.getX(), c.getY(), 1)) {
				//	c.heightLevel = 0;
				//	c.teleportToX = c.absX;
				//	c.teleportToY = c.absY;
				//}
				//break;
				case 9299:
				if (c.GoodDistance(c.skillX, c.skillY, c.getX(), c.getY(), 1)) {
					if (c.playerLevel[16] > 4) {
						if (c.absX == 3240 && c.absY == 3191) {
							c.teleportToX = c.absX;
							c.teleportToY = c.absY-1;
							c.CAM().sendMessage("You squeezed through the fence.");
						} else {
							c.teleportToX = c.absX;
							c.teleportToY = c.absY+1;
							c.CAM().sendMessage("You squeezed through the fence.");	
						}
					} else {
						c.CAM().sendMessage("You need a agility level of 5, to pass through here.");
					}
				}
				break;
				case 2883:
				case 2882:
					if(c.playerHasItem(995, 30)) {
						if (c.absX == 3267 && c.absY == 3227 || c.absX == 3267 && c.absY == 3228) {
							c.deleteItem(995, 1000);
							c.fmwalkto(1, 0);
						}
						if (c.absX == 3268 && c.absY == 3227 || c.absX == 3268 && c.absY == 3228) {
							c.deleteItem(995, 1000);
							c.fmwalkto(-1, 0);
						}
					} else {
						c.DA().npctalking(c, 925, "", "You need at least 1,000gp to enter", "through this gate.", "");
					}
				break;
			}
			for (int d = 0; d < c.doorHandler.doorX.length; d++) {
				DoorHandler dh = c.doorHandler;

				if ((objectIDz == dh.doorId[d]) && (objectXz == dh.doorX[d])
						&& (objectYz == dh.doorY[d])) {
					int newFace = -3;
					if (dh.doorState[d] == 0) {
						// closed
						newFace = dh.doorFaceOpen[d];
						dh.doorState[d] = 1;
						dh.doorFace[d] = newFace;
					} else {
						newFace = dh.doorFaceClosed[d];
						dh.doorState[d] = 0;
						dh.doorFace[d] = newFace;
					}
					for (int p = 0; p < PlayerHandler.maxPlayers; p++) {
						client player = (client) server.playerHandler.players[p];
						if (player == null)
							continue;
						if ((player.playerName != null)
								&& (player.heightLevel == c.heightLevel)
								&& !player.disconnected && (player.absY > 0)
								&& (player.absX > 0)) {
							player.ReplaceObject(dh.doorX[d], dh.doorY[d],
									dh.doorId[d], newFace, 0);
						}
					}
				}
			}
		}
	public void AtObjects2(client c, int objectIDz, int objectXz, int objectYz) {
		if (c.getStatus().isBusy()) {
			return;
		}
			c.TurnPlayerTo(objectXz, objectYz);
		switch(objectIDz) {
			case 2561:
			if (c.stallstun == false) {
				if(objectXz == 2655 && objectYz == 3311 || c.GoodDistance(c.skillX, c.skillY, c.getX(), c.getY(), 1)) {
					c.skillX = objectXz;
					c.skillY = objectYz;
					new ThievingStalls(c, objectIDz, "BCSTALL1", objectXz, objectYz);
				}
				if (objectXz == 2667 && objectYz == 3310 || c.GoodDistance(c.skillX, c.skillY, c.getX(), c.getY(), 1)) {
					c.skillX = objectXz;
					c.skillY = objectYz;
					new ThievingStalls(c, objectIDz, "BCSTALL2", objectXz, objectYz);
				}
			} else {
				c.DA().npctalking(c, 571, "Hey! That is mine!", "Get your paws off of my baked good!", "", "");
				c.caught = 1;
			}
			break;
			case 2565:
				if(objectXz == 2657 && objectYz == 3314 || c.GoodDistance(c.skillX, c.skillY, c.getX(), c.getY(), 1)) {
					c.skillX = objectXz;
					c.skillY = objectYz;
					new ThievingStalls(c, objectIDz, "SILVERSTALL", objectXz, objectYz);
				}
			break;
			case 2560:
				if(objectXz == 2662 && objectYz == 3314 || c.GoodDistance(c.skillX, c.skillY, c.getX(), c.getY(), 1)) {
					c.skillX = objectXz;
					c.skillY = objectYz;
					new ThievingStalls(c, objectIDz, "SILKSTALL1", objectXz, objectYz);
				}
				if (objectXz == 2656 && objectYz == 3302 || c.GoodDistance(c.skillX, c.skillY, c.getX(), c.getY(), 1)) {
					c.skillX = objectXz;
					c.skillY = objectYz;
					new ThievingStalls(c, objectIDz, "SILKSTALL2", objectXz, objectYz);
				}
			break;

			case 2562:
				if(objectXz == 2667 && objectYz == 3303 || c.GoodDistance(c.skillX, c.skillY, c.getX(), c.getY(), 1)) {
					c.skillX = objectXz;
					c.skillY = objectYz;
					new ThievingStalls(c, objectIDz, "GEMSTALL", objectXz, objectYz);
				}
			break;

			case 2563:
				if(objectXz == 2663 && objectYz == 3296 || c.GoodDistance(c.skillX, c.skillY, c.getX(), c.getY(), 1)) {
					c.skillX = objectXz;
					c.skillY = objectYz;
					new ThievingStalls(c, objectIDz, "FURSTALL", objectXz, objectYz);
				}
			break;
			case 2564:
				if(objectXz == 2658 && objectYz == 3297 || c.GoodDistance(c.skillX, c.skillY, c.getX(), c.getY(), 1)) {
					c.skillX = objectXz;
					c.skillY = objectYz;
					new ThievingStalls(c, objectIDz, "SPICESTALL", objectXz, objectYz);
				}
			break;
				case 2213:
				case 2214:
				case 3045:
				case 5276:
				case 6084:
				if (c.GoodDistance(c.skillX, c.skillY, c.getX(), c.getY(), 1)) {
					c.skillX = objectXz;
					c.skillY = objectYz;
					c.WanneBank = 1;
				}
				break;

			/* STAIRS */
				case 1739:
				case 1748:
					if (c.GoodDistance(c.skillX, c.skillY, c.getX(), c.getY(), 1)) {
						c.heightLevel = 2;
						c.teleportToX = c.absX;
						c.teleportToY = c.absY;
					}
				break;
			}
		}

	public void AtObjects3(client c, int objectIDz, int objectXz, int objectYz) {
		if (c.getStatus().isBusy()) {
			return;
		}
		c.TurnPlayerTo(objectXz, objectYz);
		switch(objectIDz) {
			/* STAIRS */
				case 1739:
				case 1748:
				if (c.GoodDistance(c.skillX, c.skillY, c.getX(), c.getY(), 1)) {
					c.heightLevel = 0;
					c.teleportToX = c.absX;
					c.teleportToY = c.absY;
				}
				break;
		}
		if (c.debug)
		return;
	}
}