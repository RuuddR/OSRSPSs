package server.players.Packets;

import server.*;
import server.items.*;
import server.world.*;
import server.npcs.*;
import server.util.*;
import server.players.*;

public class ActionButtons {
	
	public client client;

	public ActionButtons(client c) {
		client = c;
	}
	static final int[] BUTTONS = {168, 169, 162, 164, 165, 161, 170, 171, 163, 167, 172, 166,
					52050, 52051, 52052, 52053, 52054, 52055, 52056, 52057, 52058, 43092,
						2155, 25103, 25106, 2154, 52071, 52072, 59062, 72032, 72033, 72254
	};
	static final int[] ANIMS = {855, 856, 857, 858, 859, 860, 861, 862, 863, 864, 865, 866, 2105,
					2106, 2107, 2108, 2109, 2110, 2111, 2112, 2113, 0x558, 0x46B, 0x46A,
						0x469, 0x468, 0x84F, 0x850, 2836, 3544, 3543, 3866
	};


	public void ActionButtons(client c, int actionButtonId) {
			server.PrayerHandler.clickPrayer(c.playerId);
			c.QH().questsbuttons(c, actionButtonId);
			for(int i = 0; i < BUTTONS.length; i++) {
				if(actionButtonId == BUTTONS[i]) {
					c.startAnimation(ANIMS[i]);
				}
			}
			//if (c.playerRights > 0) {
			//	c.CAM().sendMessage("id : "+ actionButtonId + ". ");
			//}
			switch (actionButtonId) {
			case 73168:
				c.runVeng();
			break;
			case 7038: // wind strike
					c.playerMagicID = 1152;
					c.spellSelected = true;
					c.spellSelectedNpc = true;
					c.autoCastID = 1;
					c.setSidebarInterface(0, 328);
				break;
			case 7039: 
					c.playerMagicID = 1154;
					c.spellSelected = true;
					c.spellSelectedNpc = true;
					c.autoCastID = 2;
					c.setSidebarInterface(0, 328);
				break;
			case 7040: 
					c.playerMagicID = 1156;
					c.spellSelected = true;
					c.spellSelectedNpc = true;
					c.autoCastID = 3;
					c.setSidebarInterface(0, 328);
				break;
			case 7041:
					c.playerMagicID = 1158;
					c.spellSelected = true;
					c.spellSelectedNpc = true;
					c.autoCastID = 4;
					c.setSidebarInterface(0, 328);
				break;
			case 7042:
					c.playerMagicID = 1160;
					c.spellSelected = true;
					c.spellSelectedNpc = true;
					c.autoCastID = 5;
					c.setSidebarInterface(0, 328);
				break;
			case 7043:
					c.playerMagicID = 1163;
					c.spellSelected = true;
					c.spellSelectedNpc = true;
					c.autoCastID = 6;
					c.setSidebarInterface(0, 328);
				break;
			case 7044: 
					c.playerMagicID = 1166;
					c.spellSelected = true;
					c.spellSelectedNpc = true;
					c.autoCastID = 7;
					c.setSidebarInterface(0, 328);
				break;
			case 7045: 
					c.playerMagicID = 1169;
					c.spellSelected = true;
					c.spellSelectedNpc = true;
					c.autoCastID = 8;
					c.setSidebarInterface(0, 328);
				break;
			case 7046: 
					c.playerMagicID = 1172;
					c.spellSelected = true;
					c.spellSelectedNpc = true;
					c.autoCastID = 9;
					c.setSidebarInterface(0, 328);
				break;
			case 7047: 
					c.playerMagicID = 1175;
					c.spellSelected = true;
					c.spellSelectedNpc = true;
					c.autoCastID = 10;
					c.setSidebarInterface(0, 328);
				break;
			case 7048: 
					c.playerMagicID = 1177;
					c.spellSelected = true;
					c.spellSelectedNpc = true;
					c.autoCastID = 11;
					c.setSidebarInterface(0, 328);
				break;
			case 7049:
					c.playerMagicID = 1181;
					c.spellSelected = true;
					c.spellSelectedNpc = true;
					c.autoCastID = 12;
					c.setSidebarInterface(0, 328);
				break;
			case 7050: 
					c.playerMagicID = 1183;
					c.spellSelected = true;
					c.spellSelectedNpc = true;
					c.autoCastID = 13;
					c.setSidebarInterface(0, 328);
				break;
			case 7051: 
					c.playerMagicID = 1185;
					c.spellSelected = true;
					c.spellSelectedNpc = true;
					c.autoCastID = 14;
					c.setSidebarInterface(0, 328);
				break;
			case 7052: 
					c.playerMagicID = 1188;
					c.spellSelected = true;
					c.spellSelectedNpc = true;
					c.autoCastID = 15;
					c.setSidebarInterface(0, 328);
				break;
			case 7053: 
					c.playerMagicID = 1189;
					c.spellSelected = true;
					c.spellSelectedNpc = true;
					c.autoCastID = 16;
					c.setSidebarInterface(0, 328);
				break;
			case 51069: //ice burst
					c.playerMagicID = 12881;
					c.spellSelected = true;
					c.spellSelectedNpc = true;
					c.autoCastID = 17;
					c.setSidebarInterface(0, 328);
				break;
			case 51058: //ice blitz
					c.playerMagicID = 12871;
					c.spellSelected = true;
					c.spellSelectedNpc = true;
					c.autoCastID = 18;
					c.setSidebarInterface(0, 328);
				break;
			case 51172: //smoke barrage
					c.playerMagicID = 12975;
					c.spellSelected = true;
					c.spellSelectedNpc = true;
					c.autoCastID = 19;
					c.setSidebarInterface(0, 328);
				break;
			case 51224: //shadow barrage
					c.playerMagicID = 13023;
					c.spellSelected = true;
					c.spellSelectedNpc = true;
					c.autoCastID = 20;
					c.setSidebarInterface(0, 328);
				break;
			case 51122: //blood barrage
					c.playerMagicID = 12929;
					c.spellSelected = true;
					c.spellSelectedNpc = true;
					c.autoCastID = 21;
					c.setSidebarInterface(0, 328);
				break;
			case 51080: //ice barrage
					c.playerMagicID = 12891;
					c.spellSelected = true;
					c.spellSelectedNpc = true;
					c.autoCastID = 22;
					c.setSidebarInterface(0, 328);
				break;
			case 1093:
				if (!c.autoCast) {
					if (c.spellSelected) {
						c.autoCast = true;
						c.initializeClientConfiguration();
						c.setClientConfig(108, 1);
						break;
					} else {
						c.autoCast = false;
						c.initializeClientConfiguration();
						c.CAM().sendMessage("please select a spell first.");
						break;
					}
				} else {
					c.autoCast = false;
					c.setClientConfig(108, 0);
					break;
				}
			case 1097: 
				if (c.ancients == 1) {
					if (c.hasRegularStaff()) {
						c.CAM().sendMessage("You must have an ancient staff to use autocasting for ancient magic.");
						break;
					} else {
						c.setSidebarInterface(0, 1689);
						break;
					}
				} else {
					if (!c.hasRegularStaff()) {
						c.CAM().sendMessage("You must have an normal staff to use autocasting for normal magic.");
						break;
					} else {
						c.setSidebarInterface(0, 1829);
						break;
					}
				}

			case 7212:
				c.setSidebarInterface(0, 328);
				break;

			case 4140: // varrock teleport 
				if (!c.inTeleport) {
					c.runTeleport(0, Constants.VARROCKX, Constants.VARROCKY, 25, 150, 556, 3, 554, 1, 563, 1, -1, -1);
				} else {
					break;
				}
			case 4143: // lumbridge teleport 
				if (!c.inTeleport) {
					c.runTeleport(0, Constants.LUMBRIGEX, Constants.LUMBRIGEY, 31, 200, 556, 3, 557, 1, 563, 1, -1, -1);
				} else {
					break;
				}
			case 4146: // falador teleport 
				if (!c.inTeleport) {
					c.runTeleport(0, Constants.FALADORX, Constants.FALADORY, 37, 250, 556, 3, 555, 1, 563, 1, -1, -1);
				} else {
					break;
				}
			case 4150: // camelot teleport
				if (!c.inTeleport) {
					c.runTeleport(0, Constants.CAMELOTX, Constants.CAMELOTY, 45, 300, 556, 5, -1, -1, 563, 1, -1, -1);
				} else {
					break;
				}
			case 6004: // ardougne teleport
				if (!c.inTeleport) {
					c.runTeleport(0, Constants.ARDOUGNEX, Constants.ARDOUGNEY, 51, 350, -1, -1, 555, 2, 563, 2, -1, -1);
				} else {
					break;
				}
			case 6005: // Watchtower teleport
				if (!c.inTeleport) {
					c.runTeleport(0, Constants.WATCHTOWERX, Constants.WATCHTOWERY, 0, 0, -1,-1, -1, -1, -1, -1, -1, -1);
				} else {
					break;
				}
			case 29031: // Trollheim Teleport
				if (!c.inTeleport) {
					c.runTeleport(0, Constants.TROLLHEIMX, Constants.TROLLHEIMY, 0, 0, -1,-1, -1, -1, -1, -1, -1, -1);
				} else {
					break;
				}
			case 51023: // Carrallanger teleportl
				if (!c.inTeleport) {
					c.runTeleport(0, Constants.CARRALLANGERX, Constants.CARRALLANGERY, 84, 500, -1, -1, 562, 2, 563, 2, -1, -1);
				} else {
					break;
				}
			case 51031: // Annakarl Teleport
				if (!c.inTeleport) {
					c.runTeleport(0, Constants.ANNAKARLX, Constants.ANNAKARLY, 90, 550, -1, -1, 565, 2, 563, 2, -1, -1);
				} else {
					break;
				}
			case 51039: // Ghorrock Teleport
				if (!c.inTeleport) {
					c.runTeleport(0, Constants.GHORROCKX, Constants.GHORROCKY, 96, 600, -1, -1, 555, 8, 563, 2, -1, -1);
				} else {
					break;
				}
			case 51005:
				if (!c.inTeleport) {
					c.runTeleport(0, Constants.LASSARX, Constants.LASSARY, 1, 0, -1, -1, 563, 2, 555, 4, -1, -1);
				} else {
					break;
				}
			case 50235:
				if (!c.inTeleport) {
					c.runTeleport(0, Constants.PADDEWWAX, Constants.PADDEWWAY, 1, 0, 563, 2, 554, 1, 556, 1, -1, -1);
				} else {
					break;
				}
			case 50253:
				if (!c.inTeleport) {
					c.runTeleport(0, Constants.KHARYRLLX, Constants.KHARYRLLY, 1, 0, -1, -1, 563, 2, 565, 1, -1, -1);
				} else {
					break;
				}
			case 50245:
				if (!c.inTeleport) {
					c.runTeleport(0, Constants.SENNTISTENX, Constants.SENNTISTENY, 1, 0, -1, -1, 563, 2, 566, 1, -1, -1);
				} else {
					break;
				}
			case 51013:
				if (!c.inTeleport) {
					c.runTeleport(0, Constants.DAREEYAKX, Constants.DAREEYAKY, 1, 0, 563, 2, 554, 3, 556, 2, -1, -1);
				} else {
					break;
				}
			case 72038:
				if (!c.inTeleport) {
					c.runTeleport(0, Constants.APE_TOLLX, Constants.APE_TOLLY, 1, 0, 554, 2, 555, 2, 563, 2, 1963, 1);
				} else {
					break;
				}
			case 29113: //bows
			case 33033: //halberds
			case 29163: //swords
				if (c.playerEquipment[c.playerWeapon] == 4153 && c.specialAmount >= 50) {
					if (c.AttackingOn > 0) {
						client AttackingOn2 = (client) server.playerHandler.players[c.AttackingOn];
						if (c.withinDistance(1, AttackingOn2)) {
							if (PlayerHandler.players[c.AttackingOn] != null) {
								c.arrowPullBack(340);
								c.startAnimation(1667);
								c.specialAmount -= 50;
								c.playerMeleeDamage();
							}
						} 
					}	
				}
			case 29138: //dds
			case 48023: //whip
				try {
					if (c.specOn) {
						c.specOn = false;
						c.sendSpecialBarAmount();
					} else if (!c.specOn) {
						c.specOn = true;
						c.sendSpecialBarAmount();
					}
				} catch (Exception popo) {
			}
			break;
		case 34193://1
		case 34192://5
		case 34191://10
		case 34190://X
			c.getCraft().startHideCraft(c, actionButtonId);
			break;

		case 34185://shortbow
			//1
			c.getCraft().startHideCraft(c, actionButtonId);
			break;
		case 34184:
			//5
			c.getCraft().startHideCraft(c, actionButtonId);
			break;

		case 34183:
			//10
			c.getCraft().startHideCraft(c, actionButtonId);
			break;
		case 34182:
			//all
			c.getCraft().startHideCraft(c, actionButtonId);
			break;
		case 34189://longbow
			//1
			c.getCraft().startHideCraft(c, actionButtonId);
			break;
		case 34188:
			//5
			c.getCraft().startHideCraft(c, actionButtonId);
			break;
		case 34187:
			//10
			c.getCraft().startHideCraft(c, actionButtonId);
			break;
		case 34186:
			//all
			c.getCraft().startHideCraft(c, actionButtonId);
			break;

			case 57225:
				c.getCraft().startTan(c, 1, 0);
				break;
			case 57217:
				c.getCraft().startTan(c, 5, 0);
				break;
			case 57209:
				c.getCraft().startTan(c, 27, 0);
				break;
			case 57201:
				c.getCraft().startTan(c, 27, 0);
				break;
			case 57229:
				c.getCraft().startTan(c, 1, 1);
				break;
			case 57221:
				c.getCraft().startTan(c, 5, 1);
				break;
			case 57213:
				c.getCraft().startTan(c, 27, 1);
				break;
			case 57205:
				c.getCraft().startTan(c, 27, 1);
				break;
			case 57227:
				c.getCraft().startTan(c, 1, 2);
				break;
			case 57219:
				c.getCraft().startTan(c, 5, 2);
				break;
			case 57211:
			case 57203:
				c.getCraft().startTan(c, 27, 2);
				break;
			case 57228:
				c.getCraft().startTan(c, 1, 3);
				break;
			case 57220:
				c.getCraft().startTan(c, 5, 3);
				break;
			case 57212:
			case 57204:
				c.getCraft().startTan(c, 27, 3);
				break;
			case 57231:
				c.getCraft().startTan(c, 1, 4);
				break;
			case 57223:
				c.getCraft().startTan(c, 5, 4);
				break;
			case 57215:
			case 57207:
				c.getCraft().startTan(c, 27, 4);
				break;
			case 57232:
				c.getCraft().startTan(c, 1, 5);
				break;
			case 57224:
				c.getCraft().startTan(c, 5, 5);
				break;
			case 57216:
			case 57208:
				c.getCraft().startTan(c, 27, 5);
				break;
			case 33187:
			case 33186:
			case 33185:
			case 33190:
			case 33189:
			case 33188:
			case 33193:
			case 33192:
			case 33191:
			case 33196:
			case 33195:
			case 33194:
			case 33199:
			case 33198:
			case 33197:
			case 33202:
			case 33201:
			case 33200:
			case 33205:
			case 33204:
			case 33203:
				c.getCraft().startCraft(c, actionButtonId);
				break;
			case 14067:
				c.appearanceUpdateRequired = true;
				c.updateRequired = true;
				c.closeInterface();
				c.RemoveAllWindows();
				c.CAM().sendFrame99(0);
				if (c.QH().q1 == 0) {
					c.NpcDialogue = 11;
					c.NpcDialogueSend = false;
				}
				break;

			case 153:
				if (c.playerEnergy > 0) {
					c.isRunning2 = true;
					c.isRunning = true;
				} else {
					c.CAM().sendMessage("You have no energy left!");
					c.CAM().sendMessage("Training your Agility will regain your energy faster.");
				}
				break;
			case 152:
				c.isRunning2 = false;
				c.isRunning = false;
				break;
			case 130:
				c.println_debug("Closing Interface");
				break;
			case 9125:
				// Accurate
			case 22228:
				// punch (unarmed)
			case 48010:
				// flick (whip)
			case 21200:
				// spike (pickaxe)
			case 1080:
				// bash (staff)
			case 6168:
				// chop (axe)
			case 6236:
				// accurate (long bow)
			case 17102:
				// accurate (darts)
			case 8234:
				// stab (dagger)
				c.FightType = 1;
				c.SkillID = 0;
				c.magiccasting = false;
				break;

			case 9126:
				// Defensive
			case 48008:
				// deflect (whip)
			case 22229:
				// block (unarmed)
			case 21201:
				// block (pickaxe)
			case 1078:
				// focus - block (staff)
			case 6169:
				// block (axe)
			case 33019:
				// fend (hally)
			case 18078:
				// block (spear)
			case 8235:
				// block (dagger)
				c.FightType = 4;
				c.SkillID = 1;
				c.magiccasting = false;
				break;

			case 9127:
				// Controlled
			case 48009:
				// lash (whip)
			case 33018:
				// jab (hally)
			case 6234:
				// longrange (long bow)
			case 18077:
				// lunge (spear)
			case 18080:
				// swipe (spear)
			case 18079:
				// pound (spear)
			case 17100:
				// longrange (darts)
				c.FightType = 3;
				c.SkillID = 3;
				c.magiccasting = false;
				break;

			case 9128:
				// Aggressive
			case 22230:
				// kick (unarmed)
			case 21203:
				// impale (pickaxe)
			case 21202:
				// smash (pickaxe)
			case 1079:
				// pound (staff)
			case 6171:
				// hack (axe)
			case 6170:
				// smash (axe)
			case 33020:
				// swipe (hally)
			case 6235:
				// rapid (long bow)
			case 17101:
				// repid (darts)
			case 8237:
				// lunge (dagger)
			case 8236:
				// slash (dagger)
				c.FightType = 2;
				c.SkillID = 2;
				c.magiccasting = false;
				break;

			case 9154:
				// Log out
				c.logout();
				break;

			case 21011:
				c.takeAsNote = false;
				break;

			case 21010:
				c.takeAsNote = true;
				break;

			case 13092:
			client ot = (client) server.playerHandler.players[c.tradeWith];
			if(ot == null) {
				c.getTrade().declineTrade();
				c.CAM().sendMessage("Trade declined as the other player has disconnected.");
				break;
			}
			c.CAM().sendFrame126("Waiting for other player...", 3431);
			ot.CAM().sendFrame126("Other player has accepted", 3431);	
			c.goodTrade= true;
			ot.goodTrade= true;	
			
			for (GameItem item : c.getTrade().offeredItems) {
				if (item.id > 0) {
					if(ot.freeSlots() < c.getTrade().offeredItems.size()) {					
						c.CAM().sendMessage(ot.playerName +" only has "+ot.freeSlots()+" free slots, please remove "+(c.getTrade().offeredItems.size() - ot.freeSlots())+" items.");
						ot.CAM().sendMessage(c.playerName +" has to remove "+(c.getTrade().offeredItems.size() - ot.freeSlots())+" items or you could offer them "+(c.getTrade().offeredItems.size() - ot.freeSlots())+" items.");
						c.goodTrade= false;
						ot.goodTrade= false;
						c.CAM().sendFrame126("Not enough inventory space...", 3431);
						ot.CAM().sendFrame126("Not enough inventory space...", 3431);
							break;
					} else {
						c.CAM().sendFrame126("Waiting for other player...", 3431);				
						ot.CAM().sendFrame126("Other player has accepted", 3431);
						c.goodTrade= true;
						ot.goodTrade= true;
						}
					}	
				}	
				if (c.inTrade && !c.tradeConfirmed && ot.goodTrade && c.goodTrade) {
					c.tradeConfirmed = true;
					if(ot.tradeConfirmed) {
						c.getTrade().confirmScreen();
						ot.getTrade().confirmScreen();
						break;
					}
							  
				}

		
			break;
					
			case 13218:
			c.tradeAccepted = true;
			client ot1 = (client) server.playerHandler.players[c.tradeWith];
				if (ot1 == null) {
					c.getTrade().declineTrade();
					c.CAM().sendMessage("Trade declined as the other player has disconnected.");
					break;
				}
				
				if (c.inTrade && c.tradeConfirmed && ot1.tradeConfirmed && !c.tradeConfirmed2) {
					c.tradeConfirmed2 = true;
					if(ot1.tradeConfirmed2) {	
						c.acceptedTrade = true;
						ot1.acceptedTrade = true;
						c.getTrade().giveItems();
						ot1.getTrade().giveItems();
						break;
					}
				ot1.CAM().sendFrame126("Other player has accepted.", 3535);
				c.CAM().sendFrame126("Waiting for other player...", 3535);
				}
				
			break;	

			case 9157:
				if(c.NpcDialogue == 66) {
					c.openUpBank();
				}
				if (c.NpcDialogue == 29) {
					c.NpcDialogue = 32;
					c.NpcDialogueSend = false;
				}
				if (c.NpcDialogue == 72) {
					c.NpcDialogue = 75;
					c.NpcDialogueSend = false;
				}
				if (c.NpcDialogue == 97) {
					c.NpcDialogue = 99;
					c.NpcDialogueSend = false;
				}
				if(c.NpcDialogue == 2652) {
					if (c.playerHasItem(995, 5000)) {
						c.deleteItem(995, 5000);
						c.NpcDialogue = 2653;
						c.NpcDialogueSend = false;	
					} else {
						c.NpcDialogue = 2655;
						c.NpcDialogueSend = false;
					}
				}
				if (c.NpcDialogue == 68) {
						c.runTeleport(0, c.absX, c.absY-8, 1, 60, -1, -1, -1, -1, -1, -1, -1, -1);
					c.closeInterface();
					c.RemoveAllWindows();
				}
				break;

			case 9158:
				if (c.NpcDialogue == 66) {
					c.CAM().sendMessage("Pins not working atm!");
					c.closeInterface();
					c.RemoveAllWindows();
				}
				if (c.NpcDialogue == 97) {
					c.NpcDialogue = 98;
					c.NpcDialogueSend = false;
				}
				if (c.NpcDialogue == 68) {
					c.closeInterface();
					c.RemoveAllWindows();
				}
				if (c.NpcDialogue == 29) {
					c.NpcDialogue = 30;
					c.NpcDialogueSend = false;
				}
				if (c.NpcDialogue == 72) {
					c.NpcDialogue = 73;
					c.NpcDialogueSend = false;
				}
				if(c.NpcDialogue == 2652) {
					c.NpcDialogue = 2656;
					c.NpcDialogueSend = false;	
				}
				break;
			case 34119:
			case 33212: // defence
				c.SK().defInterface(c);
			break;
			case 34142:
			case 33206: // attack
				c.SK().AttInterface(c);
			break;
			case 34120:
			case 33208:
				c.SK().miningInterface(c);
			break;
			case 33209: // strength
			case 33215: // range
			case 33218: // prayer
			case 33221: // mage
			case 33207: // hp
			break;

			case 26018:
				break;

			default:
				break;
		}
		
	}
public boolean ClickedBook = false;
}