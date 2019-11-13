package server.players.Packets;

import server.*;
import server.npcs.*;
import server.util.*;
import server.items.*;
import server.world.*;
import server.players.*;

public class PrayerHandler {
	public void resetPrayer(int p) {
		client c = (client) server.playerHandler.players[p];
		c.protMage = false;
		c.protRange = false;
		c.protMelee = false;
		c.strLow = false;
		c.strMid = false; 
		c.strHigh = false;
		c.defLow = false;
		c.defMid = false;
		c.defHigh = false;
		c.atkLow = false;
		c.atkMid = false;
		c.atkHigh = false;
		c.rapidRest = false; 
		c.rapidHeal = false;
		c.ret = false;
		c.redempt = false;
		c.smite = false;
		c.headIcon = 0;
		c.frame36(83,0);
		c.frame36(84,0);
		c.frame36(85,0);
		c.frame36(86,0);
		c.frame36(87,0);
		c.frame36(88,0);
		c.frame36(89,0);
		c.frame36(90,0);
		c.frame36(91,0);
		c.frame36(92,0);
		c.frame36(93,0);
		c.frame36(94,0);
		c.frame36(95,0);
		c.frame36(96,0);
		c.frame36(97,0);
		c.frame36(98,0);
		c.frame36(99,0);
		c.frame36(100,0);
	}
	public void noPrayer(int p) {
		client c = (client) server.playerHandler.players[p];
		if(!c.protMage && !c.protRange && !c.protMelee && !c.strLow && !c.strMid &&  !c.strHigh && !c.defLow && !c.defMid && !c.defHigh && !c.atkLow && !c.atkMid && !c.atkHigh && !c.protItem && !c.rapidRest && !c.rapidHeal && !c.ret && !c.redempt && !c.smite) {
			c.noPrayer = true;
			c.drainPray = false;
		} else {
			c.noPrayer = false;
		}
	}
	public void newDrain(int p) {
		client c = (client) server.playerHandler.players[p];
		c.oldDrain = c.prayerDrain;
		if(c.newDrain > c.oldDrain && !c.noPrayer) {
			c.prayerDrain = c.oldDrain;
		} else if(c.newDrain <= c.oldDrain || c.noPrayer) {
			c.prayerDrain = c.newDrain;
		}
	}
	public void drainPrayer(int p) {
		client c = (client) server.playerHandler.players[p];
		c.prayerTimer -= 1;
		if(c.prayerTimer < 0 && c.playerLevel[5] < c.getLevelForXP(c.playerXP[5])){
			c.PrayerTimer = 40;
		}
		if(c.prayerTimer == 0 && c.playerLevel[5] < c.getLevelForXP(c.playerXP[5])){
			c.playerLevel[5]++;
		}
		noPrayer(p);
		if(c.drainPray && c.prayerTimer <= 1 && c.playerLevel[5] > 0) {
			c.prayerTimer = c.prayerDrain;
			c.playerLevel[5]--;
			c.updateRequired = true;
		}
		if(c.drainPray && c.playerLevel[5] <= 0) {
			c.prayerTimer = 0;
			c.drainPray = false;
			c.protItem = false;
			resetPrayer(p);
			c.headIcon = 0;
			c.updateRequired = true;
			c.appearanceUpdateRequired = true;
			c.CAM().sendMessage("You have run out of Prayer points; you must recharge at an altar.");
		}
		if(!c.drainPray && c.playerLevel[5] <= 0) {
			c.playerLevel[5] = 0;
			c.prayerTimer = 0;
			c.drainPray = false;
			c.protItem = false;
			resetPrayer(p);
			c.headIcon = 0;
        		c.CAM().sendQuest(" Prayer: "+c.playerLevel[5]+"/"+c.getLevelForXP(c.playerXP[5])+"", 687);
        		c.CAM().sendFrame126(""+c.playerLevel[5]+"", 4012);
			c.updateRequired = true;
			c.appearanceUpdateRequired = true;
		}
	}
	public void redemption(int p) {
		client c = (client) server.playerHandler.players[p];
		if(c.playerLevel[3] <= c.getLevelForXP(c.playerXP[3])/10 && c.playerLevel[3] > 0 && c.redempt) {
			c.playerLevel[3] += c.getLevelForXP(c.playerXP[5])/4;
			c.playerLevel[5] = 0;
			c.headIcon = 0;
			resetPrayer(p);
			c.gfx100(436);
		}
	}
	public void prayerPot(int p) {
		client c = (client) server.playerHandler.players[p];
		if(c.getLevelForXP(c.playerXP[5]) >= 1 && c.getLevelForXP(c.playerXP[5]) <= 3) {
			c.playerLevel[5] += 7;
		} else if(c.getLevelForXP(c.playerXP[5]) >= 4 && c.getLevelForXP(c.playerXP[5]) <= 7) {
			c.playerLevel[5] += 8;
		} else if(c.getLevelForXP(c.playerXP[5]) >= 8 && c.getLevelForXP(c.playerXP[5]) <= 11) {
			c.playerLevel[5] += 9;
		} else if(c.getLevelForXP(c.playerXP[5]) >= 12 && c.getLevelForXP(c.playerXP[5]) <= 15) {
			c.playerLevel[5] += 10;
		} else if(c.getLevelForXP(c.playerXP[5]) >= 16 && c.getLevelForXP(c.playerXP[5]) <= 19) {
			c.playerLevel[5] += 11;
		} else if(c.getLevelForXP(c.playerXP[5]) >= 20 && c.getLevelForXP(c.playerXP[5]) <= 23) {
			c.playerLevel[5] += 12;
		} else if(c.getLevelForXP(c.playerXP[5]) >= 24 && c.getLevelForXP(c.playerXP[5]) <= 27) {
			c.playerLevel[5] += 13;
		} else if(c.getLevelForXP(c.playerXP[5]) >= 28 && c.getLevelForXP(c.playerXP[5]) <= 31) {
			c.playerLevel[5] += 14;
		} else if(c.getLevelForXP(c.playerXP[5]) >= 32 && c.getLevelForXP(c.playerXP[5]) <= 35) {
			c.playerLevel[5] += 15;
		} else if(c.getLevelForXP(c.playerXP[5]) >= 36 && c.getLevelForXP(c.playerXP[5]) <= 39) {
			c.playerLevel[5] += 16;
		} else if(c.getLevelForXP(c.playerXP[5]) >= 40 && c.getLevelForXP(c.playerXP[5]) <= 43) {
			c.playerLevel[5] += 17;
		} else if(c.getLevelForXP(c.playerXP[5]) >= 44 && c.getLevelForXP(c.playerXP[5]) <= 47) {
			c.playerLevel[5] += 18;
		} else if(c.getLevelForXP(c.playerXP[5]) >= 48 && c.getLevelForXP(c.playerXP[5]) <= 51) {
			c.playerLevel[5] += 19;
		} else if(c.getLevelForXP(c.playerXP[5]) >= 52 && c.getLevelForXP(c.playerXP[5]) <= 55) {
			c.playerLevel[5] += 20;
		} else if(c.getLevelForXP(c.playerXP[5]) >= 56 && c.getLevelForXP(c.playerXP[5]) <= 59) {
			c.playerLevel[5] += 21;
		} else if(c.getLevelForXP(c.playerXP[5]) >= 60 && c.getLevelForXP(c.playerXP[5]) <= 63) {
			c.playerLevel[5] += 22;
		} else if(c.getLevelForXP(c.playerXP[5]) >= 64 && c.getLevelForXP(c.playerXP[5]) <= 67) {
			c.playerLevel[5] += 23;
		} else if(c.getLevelForXP(c.playerXP[5]) >= 68 && c.getLevelForXP(c.playerXP[5]) <= 71) {
			c.playerLevel[5] += 24;
		} else if(c.getLevelForXP(c.playerXP[5]) >= 72 && c.getLevelForXP(c.playerXP[5]) <= 75) {
			c.playerLevel[5] += 25;
		} else if(c.getLevelForXP(c.playerXP[5]) >= 76 && c.getLevelForXP(c.playerXP[5]) <= 79) {
			c.playerLevel[5] += 26;
		} else if(c.getLevelForXP(c.playerXP[5]) >= 80 && c.getLevelForXP(c.playerXP[5]) <= 83) {
			c.playerLevel[5] += 27;
		} else if(c.getLevelForXP(c.playerXP[5]) >= 84 && c.getLevelForXP(c.playerXP[5]) <= 87) {
			c.playerLevel[5] += 28;
		} else if(c.getLevelForXP(c.playerXP[5]) >= 88 && c.getLevelForXP(c.playerXP[5]) <= 91) {
			c.playerLevel[5] += 29;
		} else if(c.getLevelForXP(c.playerXP[5]) >= 92 && c.getLevelForXP(c.playerXP[5]) <= 95) {
			c.playerLevel[5] += 30;
		} else if(c.getLevelForXP(c.playerXP[5]) >= 96 && c.getLevelForXP(c.playerXP[5]) <= 99) {
			c.playerLevel[5] += 31;
		}
		if(c.playerLevel[5] > c.getLevelForXP(c.playerXP[5])) {
			c.playerLevel[5] = c.getLevelForXP(c.playerXP[5]);
		}
	}
	public void clickPrayer(int p) {
		client c = (client) server.playerHandler.players[p];
		switch(c.actionButtonId) {
			case 21233://Thick Skin
				if(c.getLevelForXP(c.playerXP[5]) >= 1) {
					if(c.playerLevel[5] > 0) {
						if(!c.defLow) {
							if(c.defMid || c.defHigh) {
								c.defMid = false;
								c.defHigh = false;
								c.frame36(86, 0);
								c.frame36(92, 0);
							}
							noPrayer(p);
							c.newDrain = 40;
							c.drainPray = true;
							c.defLow = true;
							newDrain(p);
							c.frame36(83, 1);
						} else if(c.defLow) {
							c.defLow = false;
							c.frame36(83,0);
						}
					} else {
						c.CAM().sendMessage("You need to recharge your Prayer at an altar.");
						c.frame36(83,0);
					}
				} else {
					c.CAM().sendFrame126("You need a @blu@Prayer level of 1 to use Thick Skin.", 357);
					c.CAM().sendFrame164(356);
					c.frame36(83,0);
				}
			break;
			case 21234://Burst Of Strength
				if(c.getLevelForXP(c.playerXP[5]) >= 4) {
					if(c.playerLevel[5] > 0) {
						if(!c.strLow) {
							if(c.strMid || c.strHigh) {
								c.strMid = false;
								c.strHigh = false;
								c.frame36(87, 0);
								c.frame36(93, 0);
							}
							noPrayer(p);
							c.newDrain = 38;
							c.drainPray = true;
							c.strLow = true;
							newDrain(p);
							c.frame36(84, 1);
						} else if(c.strLow) {
							c.strLow = false;
							c.frame36(84, 0);
						}
					} else {
						c.CAM().sendMessage("You need to recharge your Prayer at an altar.");
						c.frame36(84,0);
					}
				} else {
					c.CAM().sendFrame126("You need a @blu@Prayer level of 4 to use Burst of Strength.", 357);
					c.CAM().sendFrame164(356);
					c.frame36(84, 0);
				}
			break;
			case 21235://Clarity Of Thought
				if(c.getLevelForXP(c.playerXP[5]) >= 7) {
					if(c.playerLevel[5] > 0) {
						if(!c.atkLow) {
							if(c.atkMid || c.atkHigh) {
								c.atkMid = false;
								c.atkHigh = false;
								c.frame36(88, 0);
								c.frame36(94, 0);
							}
							noPrayer(p);
							c.newDrain = 36;
							c.drainPray = true;
							c.atkLow = true;
							newDrain(p);
							c.frame36(85, 1);
						} else if(c.atkLow) {
							c.atkLow = false;
							c.frame36(85, 0);
						}
					} else {
						c.CAM().sendMessage("You need to recharge your Prayer at an altar.");
						c.frame36(85,0);
					}
				} else {
					c.CAM().sendFrame126("You need a @blu@Prayer level of 7 to use Clarity of Thought.", 357);
					c.CAM().sendFrame164(356);
					c.frame36(85, 0);
				}
			break;
			case 21236://Rock Skin
				if(c.getLevelForXP(c.playerXP[5]) >= 10) {
					if(c.playerLevel[5] > 0) {
						if(!c.defMid) {
							if(c.defLow || c.defHigh) {
								c.defLow = false;
								c.defHigh = false;
								c.frame36(83, 0);
								c.frame36(92, 0);
							}
							noPrayer(p);
							c.newDrain = 34;
							c.drainPray = true;
							c.defMid = true;
							newDrain(p);
							c.frame36(86, 1);
						} else if(c.defMid) {
							c.defMid = false;
							c.frame36(86, 0);
						}
					} else {
						c.CAM().sendMessage("You need to recharge your Prayer points at an altar.");
						c.frame36(86,0);
					}
				} else {
					c.CAM().sendFrame126("You need a @blu@Prayer level of 10 to use Rock Skin.", 357);
					c.CAM().sendFrame164(356);
					c.frame36(86, 0);
				}
			break;
			case 21237://Superhuman Strength
				if(c.getLevelForXP(c.playerXP[5]) >= 13) {
					if(c.playerLevel[5] > 0) {
						if(!c.strMid) {
							if(c.strLow || c.strHigh) {
								c.strLow = false;
								c.strHigh = false;
								c.frame36(84, 0);
								c.frame36(93, 0);
							}
							noPrayer(p);
							c.newDrain = 32;
							c.drainPray = true;
							c.strMid = true;
							newDrain(p);
							c.frame36(87, 1);
						} else if(c.strMid) {
							c.strMid = false;
							c.frame36(87, 0);
						}
					} else {
						c.CAM().sendMessage("You need to recharge your Prayer points at an altar.");
						c.frame36(87,0);
					}
				} else {
					c.CAM().sendFrame126("You need a @blu@Prayer level of 13 to use Superhuman Strength.", 357);
					c.CAM().sendFrame164(356);
					c.frame36(87, 0);
				}
			break;
			case 21238://Improved Reflexes
				if(c.getLevelForXP(c.playerXP[5]) >= 16) {
					if(c.playerLevel[5] > 0) {
						if(!c.atkMid) {
							if(c.atkLow || c.atkHigh) {
								c.atkLow = false;
								c.atkHigh = false;
								c.frame36(85, 0);
								c.frame36(94, 0);
							}
							noPrayer(p);
							c.newDrain = 30;
							c.drainPray = true;
							c.atkMid = true;
							newDrain(p);
							c.frame36(88, 1);
						} else if(c.atkMid) {
							c.atkMid = false;
							c.frame36(88, 0);
						}
					} else {
						c.CAM().sendMessage("You need to recharge your Prayer points at an altar.");
						c.frame36(88,0);
					}
				} else {
					c.CAM().sendFrame126("You need a @blu@Prayer level of 16 to use Improved Reflexes.", 357);
					c.CAM().sendFrame164(356);
					c.frame36(88, 0);
				}
			break;
			case 21239://Rapid Restore
				if(c.getLevelForXP(c.playerXP[5]) >= 19) {
					if(c.playerLevel[5] > 0) {
						if(!c.rapidRest) {
							noPrayer(p);
							c.newDrain = 28;
							c.drainPray = true;
							c.rapidRest = true;
							newDrain(p);
							c.frame36(89, 1);
						} else if(c.rapidRest) {
							c.rapidRest = false;
							c.frame36(89, 0);
						}
					} else {
						c.CAM().sendMessage("You need to recharge your Prayer points at an altar.");
						c.frame36(89,0);
					}
				} else {
					c.CAM().sendFrame126("You need a @blu@Prayer level of 19 to use Rapid Restore.", 357);
					c.CAM().sendFrame164(356);
					c.frame36(89, 0);
				}
			break;
			case 21240://Rapid Heal
				if(c.getLevelForXP(c.playerXP[5]) >= 22) {
					if(c.playerLevel[5] > 0) {
						if(!c.rapidHeal) {
							noPrayer(p);
							c.newDrain = 26;
							c.drainPray = true;
							c.rapidHeal = true;
							newDrain(p);
							c.frame36(90, 1);
						} else if(c.rapidHeal) {
							c.rapidHeal = false;
							c.frame36(90, 0);
						}
					} else {
						c.CAM().sendMessage("You need to recharge your Prayer points at an altar.");
						c.frame36(90,0);
					}
				} else {
					c.CAM().sendFrame126("You need a @blu@Prayer level of 22 to use Rapid Heal.", 357);
					c.CAM().sendFrame164(356);
					c.frame36(90, 0);
				}
			break;
			case 21241://Protect Item
				if(c.getLevelForXP(c.playerXP[5]) >= 25) {
					if(c.playerLevel[5] > 0) {
						if(!c.protItem) {
							noPrayer(p);
							c.newDrain = 24;
							c.drainPray = true;
							c.protItem = true;
							newDrain(p);
							c.frame36(91, 1);
						} else if(c.protItem) {
							c.protItem = false;
							c.frame36(91, 0);
						}
					} else {
						c.CAM().sendMessage("You need to recharge your Prayer points at an altar.");
						c.frame36(91,0);
					}
				} else {
					c.CAM().sendFrame126("You need a @blu@Prayer level of 25 to use Protect Item.", 357);
					c.CAM().sendFrame164(356);
					c.frame36(91, 0);
				}
			break;
			case 21242://Steel Skin
				if(c.getLevelForXP(c.playerXP[5]) >= 28) {
					if(c.playerLevel[5] > 0) {
						if(!c.defHigh) {
							if(c.defLow || c.defMid) {
								c.defLow = false;
								c.defMid = false;
								c.frame36(83, 0);
								c.frame36(86, 0);
							}
							noPrayer(p);
							c.newDrain = 22;
							c.drainPray = true;
							c.defHigh = true;
							newDrain(p);
							c.frame36(92, 1);
						} else if(c.defHigh) {
							c.defHigh = false;
							c.frame36(92, 0);
						}
					} else {
						c.CAM().sendMessage("You need to recharge your Prayer points at an altar.");
						c.frame36(92,0);
					}
				} else {
					c.CAM().sendFrame126("You need a @blu@Prayer level of 28 to use Steel Skin.", 357);
					c.CAM().sendFrame164(356);
					c.frame36(92, 0);
				}
			break;
			case 21243://Ultimate Strength
				if(c.getLevelForXP(c.playerXP[5]) >= 31) {
					if(c.playerLevel[5] > 0) {
						if(!c.strHigh) {
							if(c.strLow || c.strMid) {
								c.strLow = false;
								c.strMid = false;
								c.frame36(84, 0);
								c.frame36(87, 0);
							}
							noPrayer(p);
							c.newDrain = 20;
							c.drainPray = true;
							c.strHigh = true;
							newDrain(p);
							c.frame36(93, 1);
						} else if(c.strHigh) {
							c.strHigh = false;
							c.frame36(93, 0);
						}
					} else {
						c.CAM().sendMessage("You need to recharge your Prayer points at an altar.");
						c.frame36(93,0);
					}
				} else {
					c.CAM().sendFrame126("You need a @blu@Prayer level of 31 to use Ultimate Strength.", 357);
					c.CAM().sendFrame164(356);
					c.frame36(93, 0);
				}
			break;
			case 21244://Incredible Reflexes
				if(c.getLevelForXP(c.playerXP[5]) >= 34) {
					if(c.playerLevel[5] > 0) {
						if(!c.atkHigh) {
							if(c.atkLow || c.atkMid) {
								c.atkLow = false;
								c.atkMid = false;
								c.frame36(85, 0);
								c.frame36(88, 0);
							}
							noPrayer(p);
							c.newDrain = 18;
							c.drainPray = true;
							c.atkHigh = true;
							newDrain(p);
							c.frame36(94, 1);
						} else if(c.atkHigh) {
							c.atkHigh = false;
							c.frame36(94, 0);
						}
					} else {
						c.CAM().sendMessage("You need to recharge your Prayer points at an altar.");
						c.frame36(94,0);
					}
				} else {
					c.CAM().sendFrame126("You need a @blu@Prayer level of 34 to use Incredible Reflexes.", 357);
					c.CAM().sendFrame164(356);
					c.frame36(94, 0);
				}
			break;
case 21245://Protect From Magic
		c.getHead();
				if(c.getLevelForXP(c.playerXP[5]) >= 37) {
					if(c.playerLevel[5] > 0) {
						if(!c.protMage) {
							if(c.protRange || c.protMelee || c.ret || c.redempt || c.smite) {
								c.protMelee = false;
								c.protRange = false;
								c.ret = false;
								c.redempt = false;
								c.smite = false;
								c.frame36(96, 0);
								c.frame36(97, 0);
								c.frame36(100, 0);
								c.frame36(98, 0);
								c.frame36(99, 0);
							}
							noPrayer(p);
							c.newDrain = 16;
							c.drainPray = true;
							c.protMage = true;
							newDrain(p);
							c.frame36(95, 1);
							c.getHead();
						} else if(c.protMage) {
							c.protMage = false;
							c.frame36(95, 0);
							c.getHead();
						}
					} else {
						c.CAM().sendMessage("You need to recharge your Prayer points at an altar.");
						c.frame36(95,0);
					}
				} else {
					c.CAM().sendFrame126("You need a @blu@Prayer level of 37 to use Protect from Magic.", 357);
					c.CAM().sendFrame164(356);
					c.frame36(95, 0);
				}
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
			break;
			case 21246://Protect From Range
		c.getHead();
				if(c.getLevelForXP(c.playerXP[5]) >= 40) {
					if(c.playerLevel[5] > 0) {
						if(!c.protRange) {
							if(c.protMage || c.protMelee || c.ret || c.redempt || c.smite) {
								c.protMelee = false;
								c.protMage = false;
								c.ret = false;
								c.redempt = false;
								c.smite = false;
								c.frame36(95, 0);
								c.frame36(97, 0);
								c.frame36(100, 0);
								c.frame36(98, 0);
								c.frame36(99, 0);
							}
							noPrayer(p);
							c.newDrain = 14;
							c.drainPray = true;
							c.protRange = true;
							newDrain(p);
							c.frame36(96, 1);
							c.getHead();
						} else if(c.protRange) {
							c.protRange = false;
							c.frame36(96, 0);
							c.getHead();
						}
					} else {
						c.CAM().sendMessage("You need to recharge your Prayer points at an altar.");
						c.frame36(96,0);
					}
				} else {
					c.CAM().sendFrame126("You need a @blu@Prayer level of 40 to use Protect from Missiles.", 357);
					c.CAM().sendFrame164(356);
					c.frame36(96, 0);
				}
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
			break;
			case 21247://Protect From Melee
		c.getHead();
				if(c.getLevelForXP(c.playerXP[5]) >= 43) {
					if(c.playerLevel[5] > 0) {
						if(!c.protMelee) {
							if(c.protMage || c.protRange || c.ret || c.redempt || c.smite) {
								c.protRange = false;
								c.protMage = false;
								c.ret = false;
								c.redempt = false;
								c.smite = false;
								c.frame36(95, 0);
								c.frame36(96, 0);
								c.frame36(100, 0);
								c.frame36(98, 0);
								c.frame36(99, 0);
							}
							noPrayer(p);
							c.newDrain = 12;
							c.drainPray = true;
							c.protMelee = true;
							newDrain(p);
							c.frame36(97, 1);
							c.getHead();
						} else if(c.protMelee) {
							c.protMelee = false;
							c.frame36(97, 0);
							c.getHead();
						}
					} else {
						c.CAM().sendMessage("You need to recharge your Prayer points at an altar.");
						c.frame36(97,0);
					}
				} else {
					c.CAM().sendFrame126("You need a @blu@Prayer level of 43 to use Protect from Melee.", 357);
					c.CAM().sendFrame164(356);
					c.frame36(97, 0);
				}
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
			break;
			case 2171://Retribution
		c.getHead();
				if(c.getLevelForXP(c.playerXP[5]) >= 46) {
					if(c.playerLevel[5] > 0) {
						if(!c.ret) {
							if(c.protMage || c.protRange || c.protMelee || c.redempt || c.smite) {
								c.protRange = false;
								c.protMage = false;
								c.protMelee = false;
								c.redempt = false;
								c.smite = false;
								c.frame36(95, 0);
								c.frame36(96, 0);
								c.frame36(97, 0);
								c.frame36(100, 0);
								c.frame36(99, 0);
							}
							noPrayer(p);
							c.newDrain = 10;
							c.drainPray = true;
							c.ret = true;
							newDrain(p);
							c.frame36(98, 1);
							c.getHead();
						} else if(c.ret) {
							c.ret = false;
							c.frame36(98, 0);
							c.getHead();
						}
					} else {
						c.CAM().sendMessage("You need to recharge your Prayer points at an altar.");
						c.frame36(98,0);
					}
				} else {
					c.CAM().sendFrame126("You need a @blu@Prayer level of 46 to use Retribution.", 357);
					c.CAM().sendFrame164(356);
					c.frame36(98, 0);
				}
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
			break;
			case 2172://Redemption
		c.getHead();
				if(c.getLevelForXP(c.playerXP[5]) >= 49) {
					if(c.playerLevel[5] > 0) {
						if(!c.redempt) {
							if(c.protMage || c.protRange || c.ret || c.protMelee || c.smite) {
								c.protRange = false;
								c.protMage = false;
								c.ret = false;
								c.protMelee = false;
								c.smite = false;
								c.frame36(95, 0);
								c.frame36(96, 0);
								c.frame36(97, 0);
								c.frame36(98, 0);
								c.frame36(100, 0);
							}
							noPrayer(p);
							c.newDrain = 8;
							c.drainPray = true;
							c.redempt = true;
							newDrain(p);
							c.frame36(99, 1);
							c.getHead();
						} else if(c.redempt) {
							c.redempt = false;
							c.frame36(99, 0);
							c.getHead();
						}
					} else {
						c.CAM().sendMessage("You need to recharge your Prayer points at an altar.");
						c.frame36(99,0);
					}
				} else {
					c.CAM().sendFrame126("You need a @blu@Prayer level of 49 to use Redemption.", 357);
					c.CAM().sendFrame164(356);
					c.frame36(99, 0);
				}
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
			break;
			case 2173://Smite
				if(c.getLevelForXP(c.playerXP[5]) >= 52) {
					if(c.playerLevel[5] > 0) {
						if(!c.smite) {
							if(c.protMage || c.protRange || c.ret || c.redempt || c.protMelee) {
								c.protRange = false;
								c.protMage = false;
								c.ret = false;
								c.redempt = false;
								c.protMelee = false;
								c.frame36(95, 0);
								c.frame36(96, 0);
								c.frame36(97, 0);
								c.frame36(98, 0);
								c.frame36(99, 0);
							}
							noPrayer(p);
							c.newDrain = 6;
							c.drainPray = true;
							c.smite = true;
							newDrain(p);
							c.frame36(100, 1);
							c.getHead();
						} else if(c.smite) {
							c.smite = false;
							c.frame36(100, 0);
							c.getHead();
						}
					} else {
						c.CAM().sendMessage("You need to recharge your Prayer points at an altar.");
						c.frame36(100, 0);
					}
				} else {
					c.CAM().sendFrame126("You need a @blu@Prayer level of 52 to use Smite.", 357);
					c.CAM().sendFrame164(356);
					c.frame36(100, 0);
				}
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
			break;
		}
	}
}