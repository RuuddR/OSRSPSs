package server.players;

import server.*;
import server.npcs.*;
import server.util.*;
import server.items.*;
import server.world.*;
import server.players.*;
import server.players.Packets.*;
import server.players.Skills.*;
import server.players.Quests.*;
import server.players.Combat.*;
import server.players.MiscHandlers.*;
import server.players.Saving.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class client extends Player implements Runnable {
	private java.net.Socket mySock;
	private java.io.OutputStream out;
	private java.io.InputStream in;

	public CopyOnWriteArrayList<GameItem> offeredItems = new CopyOnWriteArrayList<GameItem>();
	public CopyOnWriteArrayList<GameItem> otherOfferedItems = new CopyOnWriteArrayList<GameItem>();
	public int getBlock() {
		int s = playerEquipment[playerShield];
		int w = playerEquipment[playerWeapon];
		if (mC().usingBow()) {
			return 424;
		} else if (w == 4718) {
			return 424;
		} else if (s == -1) {
			return 404;
		} else {
			return 1156;
		}
	}
	public int getEnergyRestoreRate() {
		return (5950 - (playerLevel[16] * 50));
	}
	public void runVeng() {
		if (vengOn) {
			CAM().sendMessage("You already have a active vengence spell!");
			return;
		}			
		if (vengDelay > 0) {
			CAM().sendMessage("You can only cast this spell every 30 seconds!");
			return;
		}
		if (playerLevel[6] < 94) {
			CAM().sendMessage("You need a magic level of 94 to use this spell!");
			return;
		}
		int item = 553; //astra
		int item2 = 557; // earths
		int item3 = 560; // deaths

		int itemAM = 4;
		int item2AM = 10;
		int item3AM = 2;
		if (!playerHasItem(item2, item2AM)) {
			if (!staffType(item2)) {
				CAM().sendMessage("You do not have the required runes to cast this spell.");
				return;	
			}
		}
		if (!playerHasItem(item, itemAM)) {
			CAM().sendMessage("You do not have the required runes to cast this spell.");
			return;
		}
		if (!playerHasItem(item3, item3AM)) {
			CAM().sendMessage("You do not have the required runes to cast this spell.");
			return;
		}
		startAnimation(1914);
		arrowPullBack(642);
		vengDelay = 60;
		vengOn = true;
		CAM().sendMessage("You cast Vengence!");
		deleteItem(item, itemAM);
		deleteItem(item3, item3AM);
		if (!staffType(item2)) {	
			deleteItem(item2, item2AM);
		}
	}
   	public int bestmeleeDefence() {
        	if (playerBonus[5] > playerBonus[6] && playerBonus[5] > playerBonus[7]) {
            		return 5;
        	}
        	if (playerBonus[6] > playerBonus[5] && playerBonus[6] > playerBonus[7]) {
            		return 6;
        	}
        	return playerBonus[7] <= playerBonus[5] || playerBonus[7] <= playerBonus[6] ? 5 : 7;
    	}

    	public int meleeDefence() {
        	int i = playerBonus[bestmeleeDefence()];
        	int j = playerLevel[playerDefence];
        	int k = getLevelForXP(playerXP[playerDefence]);
		int total = i / 2 + j;
        	if (defLow) {
            		total += 5;

        	} else if (defMid) {
            		total += 15;

        	} else if (defHigh) {
            		total += 30;
		}
		return total;
    	}
	public void facePlayer() {
		if (AttackingOn > 0)
			faceNPC = 32768 + AttackingOn;
		else
			CAM().sendMessage("Could not locate attacker.");
		return;
	}
	public void NewObjects(){
		/* Use as : addObject(x, y, id, face, type)
		   Example: addObject(3333, 3333, objectId, 0, 10) */
		CreateObj(3109, 3490, 1987, 0, 10);
		//CreateObj(3007, 3383, 8553, 0, 10);
		//CreateObj(3089, 3494, 6552, -1, 10);
		//CreateObj(3209, 3438, 6552, -2, 10);
		//CreateObj(3013, 3385, 1306, 0, 10);
		//CreateObj(3012, 3373, 1309, 0, 10);
		//CreateObj(3011, 3389, 1307, 0, 10);
		//CreateObj(3014, 3389, 1308, 0, 10);
		//CreateObj(3018, 3387, 1281, 0, 10);
		//CreateObj(3021, 3387, 1276, 0, 10);
		//CreateObj(3089, 3485, 410, -1, 10);
		//CreateObj(3207, 3435, 410, -1, 10);
	}
	public void cumb(client c) {
		if (c.wasRunning2)
			c.isRunning2 = true;
		if (c.wasRunning)
			c.isRunning = true;
		if (c.isRunning2)
			c.wasRunning2 = true;
		if (isRunning)
			c.wasRunning = true;
	}
	public void resetCumb2() {
		wasRunning = false;
		wasRunning2 = false;
	}
	public void cumb2() {
		if (wasRunning2)
			isRunning2 = true;
		if (wasRunning)
			isRunning = true;
		if (isRunning2)
			wasRunning2 = true;
		if (isRunning)
			wasRunning = true;	
	}
	public void resetCumb(client c) {
		c.wasRunning = false;
		c.wasRunning2 = false;
	}
	public void stop() {
		PLAYER = "null";
		PLAYER_ID = 0;
		isBusy = false;
		startAnimation(-1);
		
	}
	public int getDestination() {
		teleportToX = TX;
		teleportToY = TY;	
		return 0;
	}
	public int getTeleportTime() { 
		if (ancients == 1)
		return 2300;
		else
		return 2100;
	}
	public int getGraphicDelay() { 
		if (ancients == 1)
		return 0;
		else
		return 0;
	}
	public void runTeleport(int height, int X, int Y, int level, int exp, int rune1, int rune1AM, int rune2, int rune2AM, int rune3, int rune3AM, int rune4, int rune4AM) {
		TX = X;
		TY = Y;
		closeInterface();
		if ( System.currentTimeMillis() - lastAction > 3500 && !inTeleport) {
			lastAction = System.currentTimeMillis();
			if (TX == -1 && TY == -1) {
				CAM().sendMessage("Not available.");
				return;
			}
			if (heightLevel > 0) {
				heightLevel = 0;
			}
			if (QH().q1 <= 14) {
				CAM().sendMessage("A magical force stops you from teleporting.");
				return;
			}
			if (wildyLevel >= 20 && CANT_TELEPORT_AFTER_LVL_20_WILDERNESS == true) {
				CAM().sendMessage("A magical Force Stops you from teleporting out of the wilderness!");
				WalkTo(0,0);
				return;
			}
			if (level > playerLevel[6] && Constants.TELEPORT_LEVEL_REQ == true) {
				CAM().sendMessage("You need a Magic level of "+level+" to use this Teleport.");
				WalkTo(0,0);
				return;
			}
			if (!playerHasItem(rune1, rune1AM) && Constants.TELEPORT_REQ_RUNES == true) {
				if (!staffType(rune1)) {
					CAM().sendMessage("You do not have the required runes to cast this spell.");
					WalkTo(0,0);
					return;	
				}
			}
			if (!playerHasItem(rune2, rune2AM) && Constants.TELEPORT_REQ_RUNES == true) {
				if (!staffType(rune2)) {
					CAM().sendMessage("You do not have the required runes to cast this spell.");
					WalkTo(0,0);
					return;	
				}
			}
			if (!playerHasItem(rune3, rune3AM) && Constants.TELEPORT_REQ_RUNES == true) {
				if (!staffType(rune3)) {
					CAM().sendMessage("You do not have the required runes to cast this spell.");
					WalkTo(0,0);
					return;	
				}
			}
			if (!staffType(rune1)) {
				deleteItem(rune1, rune1AM);
			}
			if (!staffType(rune2)) {	
				deleteItem(rune2, rune2AM);
			}
			if (!staffType(rune3)) {
				deleteItem(rune3, rune3AM);	
			}
			WalkTo(0,0);
			inTeleport = true;
			if (ancients == 0) {
				setAnimation(714);
			}
			if (ancients == 1) {
				startAnimation(1979);	
			}
			teleportThis = 1;
			teleportThis2 = 6;
			runningTeleport = true;
			runningTeleport2 = true;
		}
	}
	public boolean staffType(int i) {
		if (i == 555) {
			if (playerEquipment[playerWeapon] == 1383 || playerEquipment[playerWeapon] == 1395 || playerEquipment[playerWeapon] == 1403) {
				return true;
			}
		}
		if (i == 554) {
			if (playerEquipment[playerWeapon] == 1387 || playerEquipment[playerWeapon] == 1393 || playerEquipment[playerWeapon] == 1401) {
				return true;
			}
		}
		if (i == 556) {
			if (playerEquipment[playerWeapon] == 1381 || playerEquipment[playerWeapon] == 1397 || playerEquipment[playerWeapon] == 1405) {
				return true;
			}
		}
		if (i == 557) {
			if (playerEquipment[playerWeapon] == 1385 || playerEquipment[playerWeapon] == 1399 || playerEquipment[playerWeapon] == 1407) {
				return true;
			}
		}
		return false;
	}
	public void gatherMagicInformationPVP(int playerIndex,int stopMovementTime, int level,int ANI,int handGFX, int projectile, int endGFX, int damage, int rune1, int rune1AM, int rune2, int rune2AM, int rune3, int rune3AM,int rune4, int rune4AM, int magicHeight, int extraDelay) { //created by killamess
		repeatSpell = false;
		AttackingOn = followThisPlayer;
		client i_ = (client) server.playerHandler.players[followThisPlayer];
		int EnemyX = PlayerHandler.players[followThisPlayer].absX;
		int EnemyY = PlayerHandler.players[followThisPlayer].absY;
		int EnemyHP = PlayerHandler.players[followThisPlayer].currentHealth;
		int offsetX = (absY - EnemyY) * -1;
		int offsetY = (absX - EnemyX) * -1;
		i_.KillerId = playerId;
		followThisPlayer = followThisPlayer;
		faceNPC(32768 + followThisPlayer);
		if (i_.AttackingOn != playerId && i_.AttackingOn != 0 && !i_.multiZone()) {
			CAM().sendMessage("This player is already in combat.");
			ResetAttack();
			return;
		}
		getDistance().rangeFollow(followThisPlayer);
		if (!autoCast) {
			if (System.currentTimeMillis() - lastAction < weaponDelay) {
				repeatSpell = true;
				return;
			}
		}
		if (autoCast) {
			if (System.currentTimeMillis() - lastAction > weaponDelay) {
				return;
			}
		}
		if (!mC().checkPlayersAreas()) {
			WalkTo(0,0);
			return;
		}
		if(i_.skulledBy != playerName) {
			skullTimer = 1200;
			isSkulled = true;
			skulledBy = i_.playerName;
			getHead();
		}
		extraDelayTime = extraDelay;
		delayMovement = stopMovementTime;
		i_.delayMovement = delayMovement;	
		getMagicalDamage = damage;	
		magicH = magicHeight;
		damage = misc.random(damage);
		weaponDelay = 3500;
		lastAttackz = false;
		lastAction = System.currentTimeMillis();
		getMagicalEndGFX = endGFX;
		if (!i_.isInCombat) {
			fightingWith = i_.playerId;
			i_.isInCombat = true;
			isInCombat = true;
		}
		if (level > playerLevel[6]) {
			CAM().sendMessage("You need a Magic level of "+level+" to cast this spell.");
			WalkTo(0,0);
			return;
		} else {
			if (!playerHasItem(rune1, rune1AM)) {
				if (!staffType(rune1)) {
					CAM().sendMessage("You do not have the required runes to cast this spell.");
					WalkTo(0,0);
					setClientConfig(108, 0);
					autoCast = false;
					return;	
				}
			}
			if (!playerHasItem(rune2, rune2AM)) {
				if (!staffType(rune2)) {
					CAM().sendMessage("You do not have the required runes to cast this spell.");
					WalkTo(0,0);
					setClientConfig(108, 0);
					autoCast = false;
					return;	
				}
			}
			if (!playerHasItem(rune3, rune3AM)) {
				if (!staffType(rune3)) {
					CAM().sendMessage("You do not have the required runes to cast this spell.");
					WalkTo(0,0);
					setClientConfig(108, 0);
					autoCast = false;
					return;	
				}
			}
			if (!playerHasItem(rune4, rune4AM)) {
				if (!staffType(rune4)) {
					CAM().sendMessage("You do not have the required runes to cast this spell.");
					WalkTo(0,0);
					setClientConfig(108, 0);
					autoCast = false;
					return;	
				}
			}
			if (!staffType(rune1)) {
				deleteItem(rune1, rune1AM);
			}
			if (!staffType(rune2)) {	
				deleteItem(rune2, rune2AM);
			}
			if (!staffType(rune3)) {
				deleteItem(rune3, rune3AM);	
			}
			if (!staffType(rune4)) {
				deleteItem(rune4, rune4AM);	
			}	
			startAnimation(ANI);
			arrowPullBack(handGFX);
			Projectile2(absY, absX, offsetX, offsetY, 50, calcDistanceSpeedPlayer(), projectile, 42, 25, - followThisPlayer  -1);
			WalkTo(0,0);
			
			server.registerEvent(new Event(calcDistanceDamagePlayer() - 100) {
				public void execute() {
					if (followThisPlayer > 0) {
						getMagicDamageStatus();
					} else {
						server.deregisterEvent(this);
					}
					server.deregisterEvent(this);
				}
			});
			
		}
	}
	public void getMagicDamageStatus() {
		if (followThisPlayer < 1 ) {
			return;
		}
		client i_ = (client) server.playerHandler.players[followThisPlayer];
		i__ = 0;
		int aBonus = 0;
		int EnemyHP = PlayerHandler.players[followThisPlayer].currentHealth;
		int dBonus = 0;
		int rand_att = misc.random(playerLevel[6])*3;
		int rand_def = (int) (misc.random(i_.playerLevel[8]));
		int random_u = misc.random(playerBonus[3] * 2);
		int random_def = misc.random(i_.playerBonus[8]);
		if ((random_u >= random_def) && (rand_att > rand_def)) {
			i__ = misc.random(getMagicalDamage);
			if (magicH > 0) {
				i_.arrowPullBack(getMagicalEndGFX);
				if (playerMagicID == 12929) {
					currentHealth = (currentHealth + i__ / 2);
					CAM().sendMessage("You drain your enemys health");
					if (currentHealth > playerLevel[playerHitpoints])
					currentHealth = playerLevel[playerHitpoints];
					refreshhps();
					i_.gfx0(getMagicalEndGFX);
				}
			} else {
				i_.gfx0(getMagicalEndGFX);
			}
		} else {
			i_.arrowPullBack(85);
			return;
		}
		if (i__ > EnemyHP) {
			i__ = EnemyHP;
			
		}
		if (EnemyHP < 1 ) {
			i__ = 0;
			stopAttack = true;
			return;
		}
		i_.inCombat();

		if (i_.protMage) {
			if (i__ > 1) {
				i__ = (i__ / 2);
			} 

		}
		if (i__ > 0) {

			i_.startAnimation(424);
		}
		if (!i_.isFrozen && delayMovement > 0 && i__ > 0) {
			i_.CAM().sendMessage("You have been frozen!");
			i_.isFrozen = true;
		}
		server.registerEvent(new Event(0) { //damage splat
			public void execute() {
				if (followThisPlayer > 0) {
					magicDamage(i__);
				} else {
					server.deregisterEvent(this);
				}
				server.deregisterEvent(this);
			}
		});
	}
	public int magicDamage(int i) {
		if (followThisPlayer < 1 ) {
			return -1;
		}
		client i_ = (client) server.playerHandler.players[followThisPlayer];
		if (i_.inTeleport) {
			return -1;
		}
		if (i_.vengOn) {
			if (i_.vengOn) {
				vengDamage = (i / 2 + i / 4);
			}
			i_.plrText = "Taste Vengence!";
                        i_.plrTextUpdateRequired = true;
			i_.vengOn = false;
			if (!hitUpdateRequired == true) {
				dealDamage(vengDamage);
				hitDiff = vengDamage;
				hitUpdateRequired = true;
			} else if (!hitUpdateRequired2 == true) {
				dealDamage(vengDamage);
				hitDiff2 = vengDamage;
				hitUpdateRequired2 = true;	
			} else {
				dealDamage(vengDamage);
				hitDiff = vengDamage;
				hitUpdateRequired = true;		
			}
			killers[localId] += vengDamage;
			hits++;
			fighting = true;
			fightId = localId;
			updateRequired = true;
			appearanceUpdateRequired = true;
		}
		if (!i_.hitUpdateRequired == true) {
			i_.dealDamage(i);
			i_.hitDiff = i;
			i_.hitUpdateRequired = true;
		} else if (!i_.hitUpdateRequired2 == true) {
			i_.dealDamage(i);
			i_.hitDiff2 = i;
			i_.hitUpdateRequired2 = true;	
		} else {
			i_.dealDamage(i);
			i_.hitDiff = i;
			i_.hitUpdateRequired = true;
		}
		i_.killers[localId] += i;
		i_.hits++;
		i_.fighting = true;
		i_.fightId = localId;
		i_.updateRequired = true;
		i_.appearanceUpdateRequired = true;
		addSkillXP(i * 70 * server.SERVER_EXPERIENCE, 6);
		addSkillXPmelee(i * 25 * server.SERVER_EXPERIENCE, playerHitpoints);
		i = -1;
		return -1;
	}
	public int recoilMagic(int i) {
		int k = 0;
		client i_ = (client) server.playerHandler.players[followThisPlayer];
		if (i_.inTeleport) {
			return -1;
		}
		if (i_.playerEquipment[playerRing] == 2550) {
			if (i == 0) {
				k = 0;
			} else if (i >= 1 && i <= 7) {
				k = 1;
			} else if (i >= 8 && i <= 14) {
				k = 2;
			} else if (i >= 15 && i <= 21) {
				k = 3;
			} else if (i >= 22 && i <= 28) {
				k = 4;
			} else if (i >= 29 && i <= 35) {
				k = 5;
			} else if (i > 35) {
				k = 6;
			}
			if (!i_.hitUpdateRequired == true) {
				i_.dealDamage(k);
				i_.hitDiff = k;
				i_.hitUpdateRequired = true;
			} else if (!i_.hitUpdateRequired2 == true) {
				i_.dealDamage(k);
				i_.hitDiff2 = k;
				i_.hitUpdateRequired2 = true;	
			} else {
				i_.dealDamage(k);
				i_.hitDiff = k;
				i_.hitUpdateRequired = true;
			}
			i_.killers[localId] += k;
			i_.hits++;
			i_.fighting = true;
			i_.fightId = localId;
			i_.updateRequired = true;
			i_.appearanceUpdateRequired = true;	
			if (k > 0) {
				if (i_.recoilPercent >= 10) {
					if (misc.random(5) == 5) {
						i_.removeRing(2550, playerRing);
						i_.CAM().sendMessage("You ring crumbles to dust.");
						i_.recoilPercent = 0;
					}
				}
				if (i_.recoilPercent < 10) {
					i_.recoilPercent = (i_.recoilPercent + 1);
				}
			}
			k = 0;
		} else {
			return 1;
		}
		return -1;
		
	}
	public int getDelayForMage() { 
		return delayMovement;
	}
	public boolean hasRegularStaff() {
		for (int i = 0; i < regularStaffs.length; i++) {
			if (playerEquipment[playerWeapon] == regularStaffs[i]) {
				return true;
			}
		}
		return false;
	}
	public void removePlayersItems() {
		for (int i = 0; i < playerItems.length; i++) {
			playerItems[i] = 0;
		}
		for (int i = 0; i < playerItemsN.length; i++) {
			playerItemsN[i] = 0;
		}
	}
	public int stopMovementTime2;
	public int npcMAGICHEIGHT;
	public void gatherMagicInformation(int magicIndex,int height, int stopMovementTime, int level, int ANI, int handGFX, int projectile, int endGFX, int damage, int rune1, int rune1AM, int rune2, int rune2AM, int rune3, int rune3AM, int rune4, int rune4AM) {
		if (server.npcHandler.npcs[npcIndex].playerId != playerId) {
			return;		
		}
		repeatSpellNpc = false;
		int EnemyX = server.npcHandler.npcs[npcIndex].absX;
		int EnemyY = server.npcHandler.npcs[npcIndex].absY;
		int EnemyHP = server.npcHandler.npcs[npcIndex].HP;
		faceNPC(npcIndex);
		int offsetX = (absY - EnemyY) * -1;
		int offsetY = (absX - EnemyX) * -1;
		int type = server.npcHandler.npcs[npcIndex].npcType;
		if (type == 1616) {
			if (playerLevel[18] < 20) {
				CAM().sendMessage("You need a slayer level of 20 to attack this npc.");
				ResetAttackNPC();
				return;
			}
		} else if (type == 1637) {
			if (playerLevel[18] < 40) {
				CAM().sendMessage("You need a slayer level of 40 to attack this npc.");
				ResetAttackNPC();
				return;
			}
		} else if (type == 1624) {
			if (playerLevel[18] < 60) {
				CAM().sendMessage("You need a slayer level of 60 to attack this npc.");
				ResetAttackNPC();
				return;
			}
		} else if (type == 1615) {
			if (playerLevel[18] < 85) {
				CAM().sendMessage("You need a slayer level of 85 to attack this npc.");
				ResetAttackNPC();
				return;
			}
		}
		if (isStunned)
		return;
		if (GoodDistance(EnemyX, EnemyY, absX, absY, 8) != true) {
			CAM().sendMessage("You are to far away to attack this npc.");
			WalkTo(0,0);
			return;
		}
		if (EnemyHP == 0) {
			WalkTo(0,0);
			return;
		}
		if (!autoCastNpc) {
			if (System.currentTimeMillis() - lastAction < weaponDelay) {
				repeatSpellNpc = true;
				WalkTo(0,0);
				return;
			}
		}
		if (autoCastNpc) {
			if (System.currentTimeMillis() - lastAction > weaponDelay) {
				return;
			}
		}
		if (System.currentTimeMillis() - lastAction > weaponDelay && isCloseEnough && !isStunned) {
			stopMovementTime2 = stopMovementTime;
			mageThisNPC = npcIndex;
			npcMAGICHEIGHT = height;
			getMagicalDamage = damage;	
			getMagicalEndGFX = endGFX;
			weaponDelay = 3000;
			damage = misc.random(damage);
			lastAction = System.currentTimeMillis();
			if (level > playerLevel[6]) {
				CAM().sendMessage("You need a Magic level of "+level+" to cast this spell.");
				WalkTo(0,0);
				return;
			} else {
				if (!playerHasItem(rune1, rune1AM)) {
					if (!staffType(rune1)) {
						CAM().sendMessage("You do not have the required runes to cast this spell.");
						WalkTo(0,0);
						setClientConfig(108, 0);
						autoCastNpc = false;
						return;	
					}
				}
				if (!playerHasItem(rune2, rune2AM)) {
					if (!staffType(rune2)) {
						CAM().sendMessage("You do not have the required runes to cast this spell.");
						WalkTo(0,0);
						setClientConfig(108, 0);
						autoCastNpc = false;
						return;	
					}
				}
				if (!playerHasItem(rune3, rune3AM)) {
					if (!staffType(rune3)) {
						CAM().sendMessage("You do not have the required runes to cast this spell.");
						WalkTo(0,0);
						setClientConfig(108, 0);
						autoCastNpc = false;
						return;	
					}
				}
				if (!playerHasItem(rune4, rune4AM)) {
					if (!staffType(rune4)) {
						CAM().sendMessage("You do not have the required runes to cast this spell.");
						WalkTo(0,0);
						setClientConfig(108, 0);
						autoCastNpc = false;
						return;	
					}
				}
				if (!staffType(rune1)) {
					deleteItem(rune1, rune1AM);
				}
				if (!staffType(rune2)) {	
					deleteItem(rune2, rune2AM);
				}
				if (!staffType(rune3)) {
					deleteItem(rune3, rune3AM);	
				}
				if (!staffType(rune4)) {
					deleteItem(rune4, rune4AM);	
				}
				startAnimation(ANI);
				arrowPullBack(handGFX);
				Projectile2(absY, absX, offsetX, offsetY, 50, calcDistanceSpeed(), projectile, 43, 31, npcIndex+1);
				WalkTo(0,0);
				server.registerEvent(new Event(calcDistanceDamage() - 100) {
					public void execute() {
						if (mageThisNPC > 0) {
							npcMageDamage();
						}
						server.deregisterEvent(this);
					}
				});
			}	
		}
	}

	public boolean npcMageDamage() {
		if (server.npcHandler.npcs[mageThisNPC] == null) {
			return false;
		}
		NPC i_ = server.npcHandler.npcs[mageThisNPC];
		int i = misc.random(getMagicalDamage);	
		int T = i_.npcType;	
		int X = i_.absX;
		int Y = i_.absY;
		int HP = i_.HP;
		int D = misc.random(server.npcHandler.npcs[mageThisNPC].npcDefence);
		int A = misc.random((playerBonus[3] + (playerLevel[6]) / 9));
		int E = playerBonus[3];


		i_.Killing[playerId] += 1;
		
		if (HP == 0) {
			return false;
		}
		if (i >= HP) {
			i = HP;
		}
		if (X == absX && Y == absY) {	
			WalkTo(0,-1);
		}
		if (D > A || E < 0) {
			i_.gfx100(85);	
			i_.Killing[playerId] += 1;	
			i_.playerName = "playerName";
			i_.playerId = playerId;
			i_.inCombat = true;
			i_.combatTimer = 30;		
			i_.animNumber = server.NpcAnimHandler.block[T];
			npcMageDamageNOW(-1);
			/*server.registerEvent(new Event(0) {
				public void execute() {
					if (mageThisNPC > 0) {
						npcMageDamageNOW(-1);
					}
				server.deregisterEvent(this);
				}
			});*/
			addSkillXP(25 * server.SERVER_EXPERIENCE, 6); //this is just cast experience
			return true;
		}
		final int damage = i;
		if (server.npcHandler.npcs[mageThisNPC].freezeTimer < 1) {
			server.npcHandler.npcs[mageThisNPC].freezeTimer = stopMovementTime2;
		}
		server.registerEvent(new Event(0) {
			public void execute() {
				if (mageThisNPC > 0) {
					npcMageDamageNOW(damage);
				}
			server.deregisterEvent(this);
			}
		});
		if (npcMAGICHEIGHT > 0) {
			i_.gfx100(getMagicalEndGFX);
		} else {
			i_.Lowgfx(getMagicalEndGFX);
		}
		i_.Killing[playerId] += 1;
		i_.IsUnderAttack = true;
		i_.RandomWalk = false;
		i_.playerName = "playerName";
		i_.playerId = playerId;
		i_.inCombat = true;
		i_.combatTimer = 30;
		i_.animNumber = server.NpcAnimHandler.block[T];
		i_.animUpdateRequired = true;
		return true;
	}
	public int npcMageDamageNOW(int i) {
		if (server.npcHandler.npcs[mageThisNPC] == null) {
			return -1;
		}	
		if (i == -1) {
			return -1;
		}
		NPC i_ = server.npcHandler.npcs[mageThisNPC];
		if (!i_.hitUpdateRequired) {
			i_.hit = true;
			i_.hitDiff = i;
			i_.hitUpdateRequired = true;	
		} else {
			i_.hit2 = true;
			i_.hitDiff2 = i;
			i_.hitUpdateRequired2 = true;
		}
		i_.Killing[playerId] += i;
		i_.IsUnderAttack = true;
		i_.RandomWalk = false;
		i_.playerName = "playerName";
		i_.playerId = playerId;
		i_.inCombat = true;
		i_.combatTimer = 30;
		i_.updateRequired = true;
		addSkillXP(i * 70 * server.SERVER_EXPERIENCE, 6);	
		addSkillXPmelee(i * 25 * server.SERVER_EXPERIENCE, playerHitpoints);
		return -1;
	}
	public int getTheCurrentSpell() {
		switch(npcMagicID) {
		case 1152://wind strike
			gatherMagicInformation(1152, 100, 0, 1, 711, 90, 91, 92, 2, 558, 1, 556, 1, -1, -1, -1, -1);
			break;
		case 1153://confuse
			gatherMagicInformation(1153, 100, 0, 3, 716, 102, 103, 104, 0, 555, 3, 557, 2, 559, 1, -1, -1);
			break;
		case 1154://water strike
			gatherMagicInformation(1154, 100, 0, 5, 711, 93, 94, 95, 4, 555, 1, 556, 1, 558, 1, -1, -1);
			break;
		case 1156://earth strike
			gatherMagicInformation(1156, 100, 0, 9, 711, 96, 97, 98, 6, 558, 1, 556, 1, 557, 2, -1, -1);
			break;
		case 1157://weaken
			gatherMagicInformation(1157, 100, 0, 11, 729, 105, 106, 107, 0, 555, 3, 557, 2, 559, 1, -1, -1);
			break;
		case 1158://fire strike
			gatherMagicInformation(1158, 100, 0, 13, 711, 99, 100, 101, 8, 554, 3, 556, 2, 558, 1, -1, -1);
			break;
		case 1160://wind bolt
			gatherMagicInformation(1160, 100, 0, 17, 711, 117, 118, 119, 9, 556, 2, 562, 1, -1, -1, -1, -1);
			break;
		case 1161://curse
			gatherMagicInformation(1161, 100, 0, 19, 729, 108, 109, 110, 0, 555, 2, 557, 3, 559, 1, -1, -1);
			break;
		case 1572://bind
			gatherMagicInformation(1172, 100, 11, 20, 724, 177, 178, 181, 1, 557, 3, 555, 3, 561, 2, -1, -1);
			break;
		case 1163://water bolt
			gatherMagicInformation(1163, 100, 0, 23, 711, 120, 121, 122, 10, 555, 3, 556, 2, 562, 1, -1, -1);
			break;
		case 1166://earth bolt
			gatherMagicInformation(1166, 100, 0, 29, 711, 123, 124, 125, 11, 557, 3, 556, 2, 562, 1, -1, -1);
			break;
		case 1169://fire bolt
			gatherMagicInformation(1169, 100, 0, 35, 711, 126, 127, 128, 12, 554, 4, 556, 3, 562, 1, -1, -1);
			break;
		case 1172://windblast
			gatherMagicInformation(1172, 100, 0, 41, 711, 132, 133, 134, 13, 556, 3, 560, 1, -1, -1, -1, -1);
			break;
		case 1175://waterblast
			gatherMagicInformation(1175, 100, 0, 47, 711, 135, 136, 137, 14, 555, 3, 556, 3, 560, 1, -1, -1);
			break;
		case 1582://snare
			gatherMagicInformation(1182, 100, 21, 50, 724, 177, 178, 180, 3, 557, 4, 555, 4, 561, 3, -1, -1);
			break;
		case 1177://earthblast
			gatherMagicInformation(1177, 100, 0, 53, 711, 138, 139, 140, 15, 557, 4, 556, 3, 560, 1, -1, -1);
			break;
		case 1181://fireblast
			gatherMagicInformation(1181, 100, 0, 59, 711, 129, 130, 131, 16, 554, 5, 556, 4, 560, 1, -1, -1);
			break;
		case 1183://windwave
			gatherMagicInformation(1183, 100, 0, 62, 718, 158, 159, 160, 17, 556, 5, 565, 1, -1, -1, -1, -1);
			break;
		case 1185://waterwave
			gatherMagicInformation(1185, 100, 0, 65, 718, 161, 162, 163, 18, 555, 7, 556, 5, 565, 1, -1, -1);
			break;
		case 1188://earthwave
			gatherMagicInformation(1188, 100, 0, 70, 718, 164, 165, 166, 19, 557, 7, 556, 5, 565, 1, -1, -1);
			break;
		case 1189://firewave
			gatherMagicInformation(1189, 100, 0, 75, 718, 155, 156, 157, 20, 554, 7, 556, 5, 565, 1, -1, -1);
			break;
		case 1592://entangle
			gatherMagicInformation(1192, 100, 31, 79, 710, 177, 178, 179, 5, 557, 5, 555, 5, 561, 4, -1, -1);
			break;
		case 12881: //ice burst
			gatherMagicInformation(12881, 0, 20, 70, 1979, -1, -1, 363, 22, 560, 2, 562, 4, 555, 4, -1, -1);
			break;
		case 12871: //ice blitz
			gatherMagicInformation(12871, 0, 30, 82, 1978, 366, -1, 367, 26, 560, 2, 565, 2, 555, 3, -1, -1);		
			break;
		case 12975: //smoke barrage
			gatherMagicInformation(12975, 0, 0, 86, 1979, -1, -1, 391, 27, 560, 4, 565, 2, 556, 4, 554, 4);		
			break;
		case 13023: //shadow barrage
			gatherMagicInformation(13023, 0, 0, 88, 1979, -1, -1, 383, 28, 560, 4, 565, 2, 556, 4, 566, 3);		
			break;
		case 12929: //blood barrage
			gatherMagicInformation(12929, 0, 0, 92, 1979, -1, -1, 377, 29, 566, 1, 565, 4, 560, 4, -1, -1);		
			break;
		case 12891: //ice barrage
			gatherMagicInformation(12891, 0, 40, 94, 1979, -1, -1, 369, 30, 555, 6, 565, 2, 560, 4, -1, -1);		
			break;
		}
		return -1;
	}
	public void refreshhps(){
		CAM().sendQuest("" + currentHealth + "", 4016);
		CAM().sendQuest("" + playerXP[3] + "", 4080);
		CAM().sendQuest("" + getLevelForXP(playerXP[3]) + "", 4017);
		CAM().sendQuest("" + getXPForLevel(playerLevel[3] + 1) + "", 4081);
	}
	public void specAttack() {
		try {
		sendSpecialBarAmount();
		} catch(Exception e) {  }
	}
	public void removeSpec(int id) {
		try {
		outStream.createFrame(171);
		outStream.writeByte(1);
		outStream.writeWord(id);
		flushOutStream();
		} catch(Exception e) {  }
	}
	public void sendAllSpecialBars() {
		try {
		boolean visible = false;
		for (int i = 0; i < specialWeapons.length; i++) {
			if (playerEquipment[playerWeapon] == specialWeapons[i]) {
				visible = true;
			}
		}
		int[] ids = {12323, 7574, 7599, 7549, 8493, 7499};
		for (int i = 0; i < 6; i++) {
			outStream.createFrame(171);
			outStream.writeByte(visible ? 0 : 1);
			outStream.writeWord(ids[i]);
			flushOutStream();
		}
		} catch(Exception e) {  }
	}
	public void sendSpecialBarAmount() {
		try {
		int startId = 0;
		int textId = 0;
		switch (playerEquipment[playerWeapon]) {
		case 5698: //DDS
		case 1215: //DD
		case 1231: //DDP
		case 5680: //DD+
		case 4586: //D scim
			startId = 7576;
			textId = 7586;
			refreshBar();
			break;
		case 4151: //Whip
			startId = 12325;
			textId = 12335;
			refreshBar();
			break;
		case 861: //Mage Shortbow
			startId = 7551;
			textId = 7561;
			refreshBar();
			break;
		case 4153: // G-Maul
		case 1305: //Dragon longsword
		case 7158: //Dragon 2h sword
		case 1434: //Dragon mace
			startId = 7601;
			textId = 7611;
			refreshBar();
			break;
		case 3204: //Dragon halberd
			startId = 8495;
			textId = 8505;
			refreshBar();
			break;
		case 6739: //Dragon axe
		case 1377: //Dragon battleaxe
			startId = 7501;
			textId = 7511;
			refreshBar();
			break;
		}
		int amountToBeFilled = (int) (specialAmount / 10);
		if (specialAmount < 10 || specialAmount > 100) {
			amountToBeFilled = -1;
		}
		for (int i = 0; i < amountToBeFilled; i++) {
			sendSpecialBarAmount(500, startId + i, playerId);
		}
		for (int i = amountToBeFilled + 1; i < 10; i++) {
			sendSpecialBarAmount(0, startId + i, playerId);
		}
		if (specOn) {
			if (specialAmount < getWeaponsDrainAmount()) {
				CAM().sendFrame126("@blk@S P E C I A L  A T T A C K", textId);
				CAM().sendMessage("You do not have enough special attack to use this.");
				sendAllSpecialBars();
				specOn = false;
			} else if (specialAmount >= getWeaponsDrainAmount()) {
				CAM().sendFrame126("@yel@S P E C I A L  A T T A C K", textId);
				sendAllSpecialBars();	
			}
		}
		if (!specOn) {
			CAM().sendFrame126("@blk@S P E C I A L  A T T A C K", textId);
			sendAllSpecialBars();	
		}
		} catch(Exception e) {  }
	}
	public void refreshBar() {
		int startId = 0;
		int textId = 0;
		switch (playerEquipment[playerWeapon]) {
		case 5698: //DDS
		case 1215: //DD
		case 1231: //DDP
		case 5680: //DD+
		case 4586: //D scim
			startId = 7576;
			textId = 7586;
			break;
		case 4151: //Whip
			startId = 12325;
			textId = 12335;
			break;
		case 861: //Mage Shortbow
			startId = 7551;
			textId = 7561;
			break;
		case 4153: // G-Maul
		case 1305: //Dragon longsword
		case 7158: //Dragon 2h sword
		case 1434: //Dragon mace
			startId = 7601;
			textId = 7611;
			break;
		case 3204: //Dragon halberd
			startId = 8495;
			textId = 8505;
			break;
		case 6739: //Dragon axe
		case 1377: //Dragon battleaxe
			startId = 7501;
			textId = 7511;
			break;
		}
		int amountToBeFilled = (int) (specialAmount / 10);
		if (specialAmount < 10 || specialAmount > 100) {
			amountToBeFilled = -1;
		}
		for (int i = 0; i < amountToBeFilled; i++) {
			sendSpecialBarAmount(500, startId + i, playerId);
		}
		for (int i = amountToBeFilled + 1; i < 10; i++) {
			sendSpecialBarAmount(0, startId + i, playerId);
		}
	}
	private void sendSpecialBarAmount(int offset, int interfaceId, int c) {
		if (c == -1) {
			return;
		}
		outStream.createFrame(70);
		outStream.writeWord(offset); // offset X
		outStream.writeWordBigEndian(0); // offset Y
		outStream.writeWordBigEndian(interfaceId); // interface, definatly.
	}
	public int getWeaponsDrainAmount() {
		int drain = 0;
		switch (playerEquipment[playerWeapon]) {
		case 5698: //DDS
		case 1215: //DD
		case 1231: //DDP
		case 5680: //DD+
		case 1434: //Dragon mace
		case 1305: //Dragon longsword
			drain = 25;
			break;
		case 3204: //Dragon halberd
		case 861: //Mage Shortbow
		case 4151: //Whip
		case 4586: //D scim
		case 6739: //Dragon axe
		case 4153: //G-Maul
			drain = 50;
			break;
		case 7158: //Dragon 2h sword
		case 1377: //Dragon battleaxe
			drain = 100;
			break;
		}
		return drain;
	}
	public void rightClickCheck() {
		if (isInWilderness(absX,absY, 1) == false) {
			outStream.createFrameVarSize(104);
			outStream.writeByteC(3);		// command slot
			outStream.writeByteA(0);		// 0 or 1; 1 if command should be placed on top in context menu
			outStream.writeString("null");
			outStream.endFrameVarSize();
		}
		if (isInWilderness(absX,absY, 1) == true) {
			outStream.createFrameVarSize(104);
			outStream.writeByteC(3);		// command slot
			outStream.writeByteA(1);		// 0 or 1; 1 if command should be placed on top in context menu
			outStream.writeString("Attack");
			outStream.endFrameVarSize();
		}	
	}
	public void youdied() {
		if (!Skulled)
		{
			keepItemHandle();
		}
		for(int rr=0; rr<playerItems.length; rr++)
		{
			try
			{
				if(playerItems[rr] > 0 && playerItems[rr] < 19999)
				{	
					//replaceBarrows();
					ItemHandler.addItem(playerItems[rr]-1, absX, absY, playerItemsN[rr], KillerId, false);
					deleteItem(playerItems[rr]-1, getItemSlot(playerItems[rr]-1), playerItemsN[rr]);
				}
			} catch(Exception e) {  }
		}
		for(int r=0; r<playerEquipment.length; r++)
		{
			try
			{
				int item = playerEquipment[r];
				if((item > 0) && (item < 19999))
				{
					remove(item, r);
				}
			} catch(Exception e) { CAM().sendMessage("ERROR: Removing Equipment"); }
		}
		if(playerHasItem(5509))
		{
			deleteItem(5509, 1);
		}
		if(playerHasItem(5510))
		{
			deleteItem(5510, 1);
		}
		if(playerHasItem(5512))
		{
			deleteItem(5512, 1);
		}
		if(playerHasItem(5514))
		{
			deleteItem(5514, 1);
		}
		if(playerHasItem(6570))
		{
			deleteItem(6570, 1);
		}
		for(int rr=0; rr<playerItems.length; rr++)
		{
			try
			{
				if(playerItems[rr] > 0 && playerItems[rr] < 11999)
				{
					//replaceBarrows();
					ItemHandler.addItem(playerItems[rr]-1, absX, absY, playerItemsN[rr], KillerId, false);
					deleteItem(playerItems[rr]-1, getItemSlot(playerItems[rr]-1), playerItemsN[rr]);
				}
			} catch(Exception e) {  }
		}
		try
		{
		} catch(Exception e) {}
		ItemHandler.addItem(526, absX, absY, 1, KillerId, false);
		try
		{
		} catch(Exception e) {}
		if (itemKept1 > 0)
		addItem(itemKept1, 1);
		if (itemKept2 > 0)
		addItem(itemKept2, 1);
		if (itemKept3 > 0)
		addItem(itemKept3, 1);
		if (itemKept4 > 0)
		addItem(itemKept4, 1);
		resetKeepItem();
		hitDiff = 0;	
		updateRequired = true; appearanceUpdateRequired = true;
	}
	public void keepItem1() {
		int highest = 0;
		for (int i = 0; i < playerItems.length; i++)
		{
			int value = (int)Math.floor(GetItemValue(playerItems[i]-1));
			if(value > highest && playerItems[i]-1 != -1)
			{
				highest = value;
				itemKept1 = playerItems[i]-1;
				itemKept1Slot = i;
				itemSlot1 = true;
			}
		}
		for (int i = 0; i < playerEquipment.length; i++)
		{
			int value = (int)Math.floor(GetItemValue(playerEquipment[i]));
			if(value > highest && playerEquipment[i] != -1)
			{
				highest = value;
				itemKept1 = playerEquipment[i];
				itemKept1Slot = i;
				itemSlot1 = false;
			}
		}
	}
	public void keepItem2() {
		int highest = 0;
		for (int i = 0; i < playerItems.length; i++)
		{
			if (itemKept1Slot != i)
			{
				int value = (int)Math.floor(GetItemValue(playerItems[i]-1));
				if(value > highest && playerItems[i]-1 != -1)
				{
					highest = value;
					itemKept2 = playerItems[i]-1;
					itemKept2Slot = i;
					itemSlot2 = true;
				}
			}
		}
		for (int i = 0; i < playerEquipment.length; i++)
		{
			if (itemKept1Slot != i)
			{
				int value = (int)Math.floor(GetItemValue(playerEquipment[i]));
				if(value > highest && playerEquipment[i] != -1)
				{
					highest = value;
					itemKept2 = playerEquipment[i];
					itemKept2Slot = i;
					itemSlot2 = false;
				}
			}
		}
	}
	public void keepItem3() {
		int highest = 0;
		for (int i = 0; i < playerItems.length; i++)
		{
			if (itemKept1Slot != i && itemKept2Slot != i)
			{
				int value = (int)Math.floor(GetItemValue(playerItems[i]-1));
				if(value > highest && playerItems[i]-1 != -1)
				{
					highest = value;
					itemKept3 = playerItems[i]-1;
					itemKept3Slot = i;
					itemSlot3 = true;
				}
			}
		}
		for (int i = 0; i < playerEquipment.length; i++)
		{
			if (itemKept1Slot != i && itemKept2Slot != i)
			{
				int value = (int)Math.floor(GetItemValue(playerEquipment[i]));
				if(value > highest && playerEquipment[i] != -1)
				{
					highest = value;
					itemKept3 = playerEquipment[i];
					itemKept3Slot = i;
					itemSlot3 = false;
				}
			}
		}
	}
	public void resetKeepItem() {
		itemKept1 = itemKept2 = itemKept3 = itemKept4 = -1;
		itemKept1Slot = itemKept2Slot = itemKept3Slot = itemKept4Slot = -1;
	}
	public void replaceBarrows() {
		replaceitem(4708, 4860);
		replaceitem(4710, 4866);
		replaceitem(4712, 4872);
		replaceitem(4714, 4878);
		replaceitem(4716, 4884);
		replaceitem(4718, 4890);
		replaceitem(4720, 4896);
		replaceitem(4722, 4902);
		replaceitem(4724, 4908);
		replaceitem(4726, 4914);
		replaceitem(4728, 4920);
		replaceitem(4730, 4926);
		replaceitem(4732, 4932);
		replaceitem(4734, 4938);
		replaceitem(4736, 4944);
		replaceitem(4738, 4950);
		replaceitem(4745, 4956);
		replaceitem(4747, 4962);
		replaceitem(4749, 4968);
		replaceitem(4751, 4974);
		replaceitem(4753, 4980);
		replaceitem(4755, 4986);
		replaceitem(4757, 4992);
		replaceitem(4759, 4998);
	}
	public void replaceitem(int oldID, int newID) {
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
	public void replaceitem2(int oldID, int newID) {
		for(int i2 = 0; i2 < playerItems.length; i2++)
		{
			if(playerItems[i2] == oldID+1)
			{
				int newamount = playerItemsN[i2];
				deleteItem(oldID, getItemSlot(oldID), playerItemsN[i2]);
				ItemHandler.addItem(newID, absX, absY, newamount, playerId, false);
			}
		}
	}
	public void keepItemHandle() {
		keepItem1();
		keepItem2();
		keepItem3();
		if (itemKept1 > 0)
		{
			if (itemSlot1)
			deleteItem(itemKept1, itemKept1Slot, 1);
			else  if (!itemSlot1)
			deleteequiment(itemKept1, itemKept1Slot);
		}
		if (itemKept2 > 0)
		{
			if (itemSlot2)
			deleteItem(itemKept2, itemKept2Slot, 1);
			else if (!itemSlot2)
			deleteequiment(itemKept2, itemKept2Slot);
		}
		if (itemKept3 > 0)
		{
			if (itemSlot3)
			deleteItem(itemKept3, itemKept3Slot, 1);
			else if (!itemSlot3)
			deleteequiment(itemKept3, itemKept3Slot);
		}
	}
	public int calcDistanceDamage() {
		int EnemyX = server.npcHandler.npcs[mageThisNPC].absX;
		int EnemyY = server.npcHandler.npcs[mageThisNPC].absY;
		if (GoodDistance(EnemyX, EnemyY, absX, absY, 1) == true)
		return 1600;
		if (GoodDistance(EnemyX, EnemyY, absX, absY, 2) == true)
		return 1700;
		if (GoodDistance(EnemyX, EnemyY, absX, absY, 3) == true)
		return 1800;
		if (GoodDistance(EnemyX, EnemyY, absX, absY, 4) == true)
		return 1900;
		if (GoodDistance(EnemyX, EnemyY, absX, absY, 5) == true)
		return 2050;
		if (GoodDistance(EnemyX, EnemyY, absX, absY, 6) == true)
		return 2150;
		if (GoodDistance(EnemyX, EnemyY, absX, absY, 7) == true)
		return 2250;
		return (!(GoodDistance(EnemyX, EnemyY, absX, absY, 1) == true) ? 2250 : 1600);
	}
	public int calcDistanceSpeed2() {
		int EnemyX = server.npcHandler.npcs[attacknpc].absX;
		int EnemyY = server.npcHandler.npcs[attacknpc].absY;
		if (GoodDistance(EnemyX, EnemyY, absX, absY, 1) == true)
		return 65;
		if (GoodDistance(EnemyX, EnemyY, absX, absY, 2) == true)
		return 67;
		if (GoodDistance(EnemyX, EnemyY, absX, absY, 3) == true)
		return 69;
		if (GoodDistance(EnemyX, EnemyY, absX, absY, 4) == true)
		return 71;
		if (GoodDistance(EnemyX, EnemyY, absX, absY, 5) == true)
		return 73;
		if (GoodDistance(EnemyX, EnemyY, absX, absY, 6) == true)
		return 75;
		if (GoodDistance(EnemyX, EnemyY, absX, absY, 7) == true)
		return 77;
		return (!(GoodDistance(EnemyX, EnemyY, absX, absY, 1) == true) ? 77 : 65);
	}
	public int calcDistanceSpeed() {
		int EnemyX = server.npcHandler.npcs[mageThisNPC].absX;
		int EnemyY = server.npcHandler.npcs[mageThisNPC].absY;
		if (GoodDistance(EnemyX, EnemyY, absX, absY, 1) == true)
		return 95;
		if (GoodDistance(EnemyX, EnemyY, absX, absY, 2) == true)
		return 95;
		if (GoodDistance(EnemyX, EnemyY, absX, absY, 3) == true)
		return 100;
		if (GoodDistance(EnemyX, EnemyY, absX, absY, 4) == true)
		return 100;
		if (GoodDistance(EnemyX, EnemyY, absX, absY, 5) == true)
		return 105;
		if (GoodDistance(EnemyX, EnemyY, absX, absY, 6) == true)
		return 110;
		if (GoodDistance(EnemyX, EnemyY, absX, absY, 7) == true)
		return 115;
		return (!(GoodDistance(EnemyX, EnemyY, absX, absY, 1) == true) ? 115 : 95);
	}
	public int calcDistanceSpeedPlayer() {
		if (followThisPlayer > 0 ) {
			int EnemyX = PlayerHandler.players[AttackingOn].absX;
			int EnemyY = PlayerHandler.players[AttackingOn].absY;
			if (GoodDistance(EnemyX, EnemyY, absX, absY, 1) == true)
			return 86;
			if (GoodDistance(EnemyX, EnemyY, absX, absY, 2) == true)
			return 89;
			if (GoodDistance(EnemyX, EnemyY, absX, absY, 3) == true)
			return 92;
			if (GoodDistance(EnemyX, EnemyY, absX, absY, 4) == true)
			return 95;
			if (GoodDistance(EnemyX, EnemyY, absX, absY, 5) == true)
			return 98;
			if (GoodDistance(EnemyX, EnemyY, absX, absY, 6) == true)
			return 101;
			if (GoodDistance(EnemyX, EnemyY, absX, absY, 7) == true)
			return 104;
			return (!(GoodDistance(EnemyX, EnemyY, absX, absY, 1) == true) ? 104 : 86);
		} else {
			return -1;
		}
	}
	public int calcDistanceSpeedPlayerRange() {
		if (followThisPlayer > 0 ) {
			int EnemyX = PlayerHandler.players[AttackingOn].absX;
			int EnemyY = PlayerHandler.players[AttackingOn].absY;
			if (GoodDistance(EnemyX, EnemyY, absX, absY, 1) == true)
			return 65;
			if (GoodDistance(EnemyX, EnemyY, absX, absY, 2) == true)
			return 67;
			if (GoodDistance(EnemyX, EnemyY, absX, absY, 3) == true)
			return 69;
			if (GoodDistance(EnemyX, EnemyY, absX, absY, 4) == true)
			return 71;
			if (GoodDistance(EnemyX, EnemyY, absX, absY, 5) == true)
			return 73;
			if (GoodDistance(EnemyX, EnemyY, absX, absY, 6) == true)
			return 75;
			if (GoodDistance(EnemyX, EnemyY, absX, absY, 7) == true)
			return 77;
			return (!(GoodDistance(EnemyX, EnemyY, absX, absY, 1) == true) ? 77 : 65);
		} else {
			return -1;
		}
	}
	public int calcDistanceDamagePlayer() {
		if (followThisPlayer > 0 ) {
			int EnemyX = server.npcHandler.npcs[AttackingOn].absX;
			int EnemyY = server.npcHandler.npcs[AttackingOn].absY;
			if (GoodDistance(EnemyX, EnemyY, absX, absY, 1) == true)
			return 780;
			if (GoodDistance(EnemyX, EnemyY, absX, absY, 2) == true)
			return 961;
			if (GoodDistance(EnemyX, EnemyY, absX, absY, 3) == true)
			return 1142;
			if (GoodDistance(EnemyX, EnemyY, absX, absY, 4) == true)
			return 1323;
			if (GoodDistance(EnemyX, EnemyY, absX, absY, 5) == true)
			return 1504;
			if (GoodDistance(EnemyX, EnemyY, absX, absY, 6) == true)
			return 1685;
			if (GoodDistance(EnemyX, EnemyY, absX, absY, 7) == true)
			return 1866;
			return (!(GoodDistance(EnemyX, EnemyY, absX, absY, 1) == true) ? 1866 : 780);
		} else {
			return -1;
		}
	}
	public int calcDistanceDamagePlayerRange() {
		if (followThisPlayer > 0 ) {
			int EnemyX = server.npcHandler.npcs[AttackingOn].absX;
			int EnemyY = server.npcHandler.npcs[AttackingOn].absY;
			if (GoodDistance(EnemyX, EnemyY, absX, absY, 1) == true)
			return 1400;
			if (GoodDistance(EnemyX, EnemyY, absX, absY, 2) == true)
			return 1250;
			if (GoodDistance(EnemyX, EnemyY, absX, absY, 3) == true)
			return 1300;
			if (GoodDistance(EnemyX, EnemyY, absX, absY, 4) == true)
			return 1320;
			if (GoodDistance(EnemyX, EnemyY, absX, absY, 5) == true)
			return 1350;
			if (GoodDistance(EnemyX, EnemyY, absX, absY, 6) == true)
			return 1380;
			if (GoodDistance(EnemyX, EnemyY, absX, absY, 7) == true)
			return 1400;
			return (!(GoodDistance(EnemyX, EnemyY, absX, absY, 1) == true) ? 1400 : 1400);
		} else {
			return -1;
		}
	}
	public client(java.net.Socket s, int _playerId) {
		super(_playerId);
		mySock = s;
		try {
			in = s.getInputStream();
			out = s.getOutputStream();
		} catch (java.io.IOException ioe) {
			misc.println("Project-Dx (1): Exception!");
			server.logError(ioe.getMessage());
		}
		outStream = new stream(new byte[bufferSize]);
		outStream.currentOffset = 0;
		inStream = new stream(new byte[bufferSize]);
		inStream.currentOffset = 0;
		readPtr = writePtr = 0;
		buffer = buffer = new byte[bufferSize];
	}
	public void actionReset() {
		actionName = "";
	}
	public String getItemName(int ItemID) {
		for (int i = 0; i < server.itemHandler.MaxListedItems; i++) {
			if (server.itemHandler.ItemList[i] != null) {
				if (server.itemHandler.ItemList[i].itemId == ItemID) {
					return server.itemHandler.ItemList[i].itemName;
				}
			}
		}
		return "!!! ITEM NOT FULLY ADDED !!! - ID:" + ItemID;
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
		return "!!! ITEM NOT FULLY ADDED !!! - ID:"+ItemID;
	}
	public void addClick(int ms) {
		//println("adding click " + ms + " (clickindex = " + clickIndex + " )");
		if (clickIndex <= clicks.length - 1) {
			clicks[clickIndex] = ms;
			clickIndex++;
		} else {
			int[] precision = new int[50];
			int average = average(clicks);
			for (int i = 0; i < precision.length; i++) {
				precision[i] = Math.abs(clicks[i] - average);
				//println("precision[" + i + "]=" + precision[i]);
			}
			resetArray(clicks);
			clickIndex = 0;
		}
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
					if ((calcX >= -16)
							&& (calcX <= 15)
							&& (calcY >= -16)
							&& (calcY <= 15)
							&& (MustDelete[i] == false)
							&& (server.itemHandler.DroppedItemsH[i] == heightLevel)) {
						if ((IsDropped[i] == false)
								&& ((server.itemHandler.DroppedItemsDDelay[i] <= 0) || (server.itemHandler.DroppedItemsDropper[i] == playerId))) {
							IsDropped[i] = true;
							outStream.createFrame(85);
							outStream
							.writeByteC((server.itemHandler.DroppedItemsY[i] - 8 * mapRegionY));
							outStream
							.writeByteC((server.itemHandler.DroppedItemsX[i] - 8 * mapRegionX));
							outStream.createFrame(44); // create item frame
							outStream
							.writeWordBigEndianA(server.itemHandler.DroppedItemsID[i]);
							outStream
							.writeWord(server.itemHandler.DroppedItemsN[i]); // amount
							outStream.writeByte(0); // x(4 MSB) y(LSB) coords
						}
					} else if ((IsDropped[i] == true)
							|| (MustDelete[i] == true)) {
						outStream.createFrame(85);
						outStream
						.writeByteC((server.itemHandler.DroppedItemsY[i] - 8 * mapRegionY));
						outStream
						.writeByteC((server.itemHandler.DroppedItemsX[i] - 8 * mapRegionX));
						outStream.createFrame(156); // remove item frame
						outStream.writeByteS(0); // x(4 MSB) y(LSB) coords
						outStream
						.writeWord(server.itemHandler.DroppedItemsID[i]);
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
							if (((LastPlayerInList == playerId) || (LastPlayerInList == -1))
									&& (server.itemHandler.DroppedItemsDeletecount[i] == TotalPlayers)) {
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
	public void appendToAutoSpawn(int npcid, int absx, int absy) {
		BufferedWriter bw = null;
		try
		{
			bw = new BufferedWriter(new FileWriter("./bin/config/autospawn.cfg", true));
			bw.write("spawn = "+npcid+"	"+absx+"	"+absy+"	0	0	0	0	0	1");
			bw.newLine();
			bw.flush();
		} catch (IOException ioe) {
			
		} finally {
			if (bw != null) {
				try
				{
					bw.close();
				} catch (IOException ioe2) {
					CAM().sendMessage("Error autospawning!");
				}
			}
		}
	}
	public void appendToAutoSpawn2(int npcid, int absx, int absy, int absx2, int absy2, int absx3, int absy3) {
		BufferedWriter bw = null;
		try
		{
			bw = new BufferedWriter(new FileWriter("./bin/config/autospawn.cfg", true));
			bw.write("spawn = "+npcid+"	"+absx+"	"+absy+"	0	"+absx2+"	"+absy2+"	"+absx3+"	"+absy3+"	1");
			bw.newLine();
			bw.flush();
		} catch (IOException ioe) {
			
		} finally {
			if (bw != null) {
				try {
					bw.close();
				} catch (IOException ioe2) {
					CAM().sendMessage("Error autospawning!");
				}
			}
		}
	}
	public boolean addItem(int item, int amount) {
		if (item >= Item.itemStackable.length) {
			return false;
		}
		if ((item < 0) || (amount < 1)) {
			return false;
		}
		if (!Item.itemStackable[item] || (amount < 1)) {
			amount = 1;
		}
		if (((freeSlots() >= amount) && !Item.itemStackable[item])
				|| (freeSlots() > 0)) {
			for (int i = 0; i < playerItems.length; i++) {
				if ((playerItems[i] == (item + 1)) && Item.itemStackable[item]
						&& (playerItems[i] > 0)) {
					playerItems[i] = (item + 1);
					if (((playerItemsN[i] + amount) < maxItemAmount)
							&& ((playerItemsN[i] + amount) > -1)) {
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
						outStream.writeByte(playerItemsN[i]); // amount
					}
					outStream.endFrameVarSizeWord();
					i = 30;
					return true;
				}
			}
			for (int i = 0; i < playerItems.length; i++) {
				if (playerItems[i] <= 0) {
					playerItems[i] = item + 1;
					if ((amount < maxItemAmount) && (amount > -1)) {
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
						outStream.writeByte(playerItemsN[i]); // amount
					}
					outStream.endFrameVarSizeWord();
					i = 30;
					return true;
				}
			}
			return false;
		} else {
			CAM().sendMessage("Not enough space in your inventory.");
			return false;
		}
	}
	public void ReplaceServerObject(int x, int y, int obj, int face, int t) {
		for (int i = 0; i < PlayerHandler.maxPlayers; i++)
		{
			client c = (client) PlayerHandler.players[i];
			if (c == null || c.disconnected)
			continue;
			c.ReplaceObject2(x, y, obj, face, t);
		}
	}
	public void ReplaceObject2(int objectX, int objectY, int NewObjectID, int Face, int ObjectType) {
		outStream.createFrame(85);
		outStream.writeByteC(objectY - (mapRegionY * 8));
		outStream.writeByteC(objectX - (mapRegionX * 8));
		outStream.createFrame(101);
		outStream.writeByteC((ObjectType << 2) + (Face & 3));
		outStream.writeByte(0);
		if (NewObjectID != -1) {
			outStream.createFrame(151);
			outStream.writeByteS(0);
			outStream.writeWordBigEndian(NewObjectID);
			outStream.writeByteS((ObjectType << 2) + (Face & 3));
			// FACE: 0= WEST | -1 = NORTH | -2 = EAST | -3 = SOUTH
			// ObjectType: 0-3 wall objects, 4-8 wall decoration, 9: diag.
			// walls, 10-11 world objects, 12-21: roofs, 22: floor decoration
		}
	}
	public void addObject(int objectX, int objectY, int NewObjectID, int Face) {
		outStream.createFrameVarSizeWord(60);
		outStream.writeByte(objectY - (mapRegionY * 8));
		outStream.writeByteC(objectX - (mapRegionX * 8));
		/* CREATE OBJECT */
		if (NewObjectID > -1) {
			outStream.writeByte(151);
			outStream.writeByteS(0);
			outStream.writeWordBigEndian(NewObjectID);
			outStream.writeByteA(Face); // 0= WEST | -1 = NORTH | -2 = EAST | -3
			// = SOUTH
		}
		outStream.endFrameVarSizeWord();
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
	public boolean addSkillXP(int amount, int skill) {
		int oldLevel = getLevelForXP(playerXP[skill]);
		playerXP[skill] += amount;
		if (oldLevel < getLevelForXP(playerXP[skill])) {
			animation(199, absY, absX);
			playerLevel[skill] = getLevelForXP(playerXP[skill]);
			updateRequired = true;
			appearanceUpdateRequired = true;
			levelup(skill);
			
			setSkillLevel(skill, playerLevel[skill], playerXP[skill]);
		}
		refreshSkills();
		if (skill == 2) {
			CalculateMaxHit();
		}
		return true;
	}
	public int amountOfItem(int itemID) {
		int i1 = 0;
		for (int i = 0; i < 28; i++) {
			if (playerItems[i] == (itemID + 1)) {
				i1++;
			}
		}
		return i1;
	}
	public void animation(int id, int Y, int X) {
		// ANIMATIONS AT GROUND HEIGHT
		// for (Player p : server.playerHandler.players) {
		for (Player p : server.playerHandler.players) {
			if (p != null) {
				client person = (client) p;
				if (person.playerName != null) {
					if (person.distanceToPoint(X, Y) <= 60) {
						person.animation2(id, Y, X);
					}
				}
			}
		}
	}	
	public boolean firespell(int castID, int casterY, int casterX, int offsetY, int offsetX, int angle, int speed, int movegfxID, int startHeight, int endHeight, int MageAttackIndex, int finishID, int enemyY, int enemyX) {
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
		return true;
	}
	public void animation2(int id, int Y, int X) {
		// ANIMATIONS AT GROUND HEIGHT
		outStream.createFrame(85);
		outStream.writeByteC(Y - (mapRegionY * 8));
		outStream.writeByteC(X - (mapRegionX * 8));
		outStream.createFrame(4);
		outStream.writeByte(0); // Tiles away (X >> 4 + Y & 7)
		outStream.writeWord(id); // Graphic id
		outStream.writeByte(0); // height of the spell above it's basic place, i
		// think it's written in pixels 100 pixels high
		outStream.writeWord(0); // Time before casting the graphic
	}
	public void animation3(int id, int Y, int X) {
		// ANIMATIONS AT MIDDLE HEIGHT
		// for (Player p : server.playerHandler.players) {
		for (Player p : server.playerHandler.players) {
			if (p != null) {
				client person = (client) p;
				if (person.playerName != null) {
					if (person.distanceToPoint(X, Y) <= 60) {
						person.animation4(id, Y, X);
					}
				}
			}
		}
	}
	public void animation4(int id, int Y, int X) {
		// ANIMATIONS AT GROUND HEIGHT
		outStream.createFrame(85);
		outStream.writeByteC(Y - (mapRegionY * 8));
		outStream.writeByteC(X - (mapRegionX * 8));
		outStream.createFrame(4);
		outStream.writeByte(0); // Tiles away (X >> 4 + Y & 7)
		outStream.writeWord(id); // Graphic id
		outStream.writeByte(0); // height of the spell above it's basic place, i
		// think it's written in pixels 100 pixels high
		outStream.writeWord(0); // Time before casting the graphic
	}
	public boolean antiHax() {
		
		return true;
	}
	public void appendToBanned(String player) {
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(
			new FileWriter("./bin/data/bannedusers.dat", true));
			bw.write(player);
			bw.newLine();
			bw.flush();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			if (bw != null) {
				try {
					bw.close();
				} catch (IOException ioe2) {
					CAM().sendMessage("Error banning user!");
				}
			}
		}
	}
	public boolean AreXItemsInBag(int ItemID, int Amount) {
		int ItemCount = 0;
		for (int pItem : playerItems) {
			if ((pItem - 1) == ItemID) {
				ItemCount++;
			}
			if (ItemCount == Amount) {
				return true;
			}
		}
		return false;
	}
	public boolean hasLevelReq() {
		if (AttackingOn < 1) {
			return false;
		}
		if (autoCastLevel > playerLevel[6]) {
			return false;
		}
		return true;
	}
	public boolean autoCasting() {
		if (AttackingOn < 1) {
			CAM().sendMessage("no target found");
			return false;
		}
		//checkSpellIDs();
		if (autoCastID < 1) {
			CAM().sendMessage("NO SPELL FOUND.");
			return false;
		}
		if (!spellSelected) {
			CAM().sendMessage("SPELL NOT SELECTED.");
			return false;
		}
		if (spellSelected()) {
			if (hasLevelReq()) {				
				autoCastThis();
				spellSelectedNpc = false;
			} else {		
				return false;
			}
		} else {
			return false;
		}
		return true;
	}
	public boolean spellSelected() {
		if (AttackingOn < 1) {
			return false;
		}
		if (!spellSelected) {
			return false;
		}
		spellSelectedNpc = false;
		return true;
	}
	public void autoCastThis() {
		if (AttackingOn < 1) {
			return;
		}
		switch(autoCastID) {
		case 1://wind strike
			gatherMagicInformationPVP(1152, 0, 1, 711, 90, 91, 92, 2, 558, 1, 556, 1, -1, -1, -1, -1, 100, 0);
			break;
		case 2://water strike
			gatherMagicInformationPVP(1154, 0, 5, 711, 93, 94, 95, 4, 555, 1, 556, 1, 558, 1,  -1, -1,100,0);
			break;
		case 3://earth strike
			gatherMagicInformationPVP(1156, 0, 9, 711, 96, 97, 98, 6, 558, 1, 556, 1, 557, 2,  -1, -1,100,0);
			break;
		case 4://fire strike
			gatherMagicInformationPVP(1158, 0, 13, 711, 99, 100, 101, 8, 554, 3, 556, 2, 558, 1,  -1, -1,100,0);
			break;
		case 5://wind bolt
			gatherMagicInformationPVP(1160, 0, 17, 711, 117, 118, 119, 9, 556, 2, 562, 1, -1, -1,  -1, -1,100,0);
			break;
		case 6://water bolt
			gatherMagicInformationPVP(1163, 0, 23, 711, 120, 121, 122, 10, 555, 3, 556, 2, 562, 1,  -1, -1,100,0);
			break;
		case 7://earth bolt
			gatherMagicInformationPVP(1166, 0, 29, 711, 123, 124, 125, 11, 557, 3, 556, 2, 562, 1,  -1, -1,100,0);
			break;
		case 8://fire bolt
			gatherMagicInformationPVP(1169, 0, 35, 711, 126, 127, 128, 12, 554, 4, 556, 3, 562, 1,  -1, -1,100,0);
			break;
		case 9://windblast
			gatherMagicInformationPVP(1172, 0, 41, 711, 132, 133, 134, 13, 556, 3, 560, 1, -1, -1,  -1, -1,100,0);
			break;
		case 10://waterblast
			gatherMagicInformationPVP(1175, 0, 47, 711, 135, 136, 137, 14, 555, 3, 556, 3, 560, 1, -1, -1, 100,0);
			break;
		case 11://earthblast
			gatherMagicInformationPVP(1177, 0, 53, 711, 138, 139, 140, 15, 557, 4, 556, 3, 560, 1, -1, -1, 100,0);
			break;
		case 12://fireblast
			gatherMagicInformationPVP(1181, 0, 59, 711, 129, 130, 131, 16, 554, 5, 556, 4, 560, 1, -1, -1, 100,0);
			break;
		case 13://windwave
			gatherMagicInformationPVP(1183, 0, 62, 718, 158, 159, 160, 17, 556, 5, 565, 1, -1, -1, -1, -1, 100,0);
			break;
		case 14://waterwave
			gatherMagicInformationPVP(1185, 0, 65, 718, 161, 162, 163, 18, 555, 7, 556, 5, 565, 1, -1, -1, 100,0);
			break;
		case 15://earthwave
			gatherMagicInformationPVP(1188, 0, 70, 718, 164, 165, 166, 19, 557, 7, 556, 5, 565, 1, -1, -1, 100,0);
			break;
		case 16://firewave
			gatherMagicInformationPVP(1189, 0, 75, 718, 155, 156, 157, 20, 554, 7, 556, 5, 565, 1, -1, -1, 100,0);
			break;
		case 17: //ice burst
			gatherMagicInformationPVP(12881, 20, 70, 1979, -1, -1, 363, 22, 560, 2, 562, 4, 555, 4, -1, -1,0,-900);
			break;
		case 18: //ice blitz
			gatherMagicInformationPVP(12871, 30, 82, 1978, 366, -1, 367, 26, 560, 2, 565, 2, 555, 3, -1, -1,100,1500);		
			break;
		case 19: //smoke barrage
			gatherMagicInformationPVP(12975, 0, 86, 1979, -1, -1, 391, 27, 560, 4, 565, 2, 556, 4, 554, 4, 0, 200);		
			break;
		case 20: //shadow barrage
			gatherMagicInformationPVP(13023, 0, 88, 1979, -1, -1, 383, 28, 560, 4, 565, 2, 556, 4, 566, 3, 0, 200);		
			break;
		case 21: //blood barrage
			gatherMagicInformationPVP(12929, 0, 92, 1979, -1, -1, 377, 29, 566, 1, 565, 4, 560, 4, -1, -1, 0, 200);		
			break;
		case 22: //ice barrage
			gatherMagicInformationPVP(12891, 30 + misc.random(10), 94, 1979, -1, -1, 369, 30, 555, 6, 565, 2, 560, 4, -1, -1, 0, 200);		
			break;
		} 
	}


	public void checkSpellIDs() {
		if (AttackingOn < 1) {
			return;
		}
		switch(autoCastID) {
		case 1:	
			castRune1 = 558;
			castRune1Amount = 1;
			castRune2 = 556;
			castRune2Amount = 1;				
			castRune3 = 0;
			castRune3Amount = 0;				
			castRune4 = 0;
			castRune4Amount = 0;
			autoCastLevel = 1;	
			break;
		case 2:	
			castRune1 = 555;
			castRune1Amount = 1;
			castRune2 = 556;
			castRune2Amount = 1;				
			castRune3 = 558;
			castRune3Amount = 1;				
			castRune4 = 0;
			castRune4Amount = 0;
			autoCastLevel = 5;	
			break;
		case 3:
			castRune1 = 558;
			castRune1Amount = 1;
			castRune2 = 556;
			castRune2Amount = 1;				
			castRune3 = 557;
			castRune3Amount = 2;				
			castRune4 = 0;
			castRune4Amount = 0;
			autoCastLevel = 9;	
			break;
		case 4:
			castRune1 = 554;
			castRune1Amount = 3;
			castRune2 = 556;
			castRune2Amount = 2;				
			castRune3 = 558;
			castRune3Amount = 1;				
			castRune4 = 0;
			castRune4Amount = 0;
			autoCastLevel = 13;	
			break;
		case 5:
			castRune1 = 556;
			castRune1Amount = 2;
			castRune2 = 562;
			castRune2Amount = 1;				
			castRune3 = 0;
			castRune3Amount = 0;				
			castRune4 = 0;
			castRune4Amount = 0;
			autoCastLevel = 17;	
			break;
		case 6:
			castRune1 = 556;
			castRune1Amount = 2;
			castRune2 = 562;
			castRune2Amount = 1;				
			castRune3 = 555;
			castRune3Amount = 3;				
			castRune4 = 0;
			castRune4Amount = 0;
			autoCastLevel = 23;	
			break;
		case 7:
			castRune1 = 557;
			castRune1Amount = 3;
			castRune2 = 556;
			castRune2Amount = 2;				
			castRune3 = 562;
			castRune3Amount = 1;				
			castRune4 = 0;
			castRune4Amount = 0;
			autoCastLevel = 29;	
			break;
		case 8:
			castRune1 = 554;
			castRune1Amount = 4;
			castRune2 = 556;
			castRune2Amount = 3;				
			castRune3 = 562;
			castRune3Amount = 1;				
			castRune4 = 0;
			castRune4Amount = 0;
			autoCastLevel = 35;	
			break;
		case 9:
			castRune1 = 556;
			castRune1Amount = 3;
			castRune2 = 560;
			castRune2Amount = 1;				
			castRune3 = 0;
			castRune3Amount = 0;				
			castRune4 = 0;
			castRune4Amount = 0;
			autoCastLevel = 41;	
			break;
		case 10:
			castRune1 = 555;
			castRune1Amount = 3;
			castRune2 = 556;
			castRune2Amount = 3;				
			castRune3 = 560;
			castRune3Amount = 1;				
			castRune4 = 0;
			castRune4Amount = 0;
			autoCastLevel = 47;	
			break;
		case 11:
			castRune1 = 557;
			castRune1Amount = 4;
			castRune2 = 556;
			castRune2Amount = 3;				
			castRune3 = 560;
			castRune3Amount = 1;				
			castRune4 = 0;
			castRune4Amount = 0;
			autoCastLevel = 53;	
			break;
		case 12:
			castRune1 = 554;
			castRune1Amount = 5;
			castRune2 = 556;
			castRune2Amount = 4;				
			castRune3 = 560;
			castRune3Amount = 1;				
			castRune4 = 0;
			castRune4Amount = 0;
			autoCastLevel = 59;	
			break;
		case 13:
			castRune1 = 556;
			castRune1Amount = 5;
			castRune2 = 565;
			castRune2Amount = 1;				
			castRune3 = 0;
			castRune3Amount = 0;				
			castRune4 = 0;
			castRune4Amount = 0;
			autoCastLevel = 62;	
			break;
		case 14:
			castRune1 = 556;
			castRune1Amount = 7;
			castRune2 = 565;
			castRune2Amount = 1;				
			castRune3 = 555;
			castRune3Amount = 5;				
			castRune4 = 0;
			castRune4Amount = 0;
			autoCastLevel = 65;	
			break;
		case 15:
			castRune1 = 557;
			castRune1Amount = 7;
			castRune2 = 565;
			castRune2Amount = 1;				
			castRune3 = 556;
			castRune3Amount = 5;				
			castRune4 = 0;
			castRune4Amount = 0;
			autoCastLevel = 70;	
			break;
		case 16:
			castRune1 = 554;
			castRune1Amount = 7;
			castRune2 = 565;
			castRune2Amount = 1;				
			castRune3 = 556;
			castRune3Amount = 5;				
			castRune4 = 0;
			castRune4Amount = 0;
			autoCastLevel = 75;	
			break;
		}
	}
	public int gatherAttackingRequirements2() {
		if (UseBow) {
			damageDelay3 = calcDistanceDamagePlayerRange();
		} else if (UseBow2) {
			damageDelay3 = (calcDistanceDamagePlayerRange() / 2);
		} else if (UseMelee) {
			damageDelay3 = 450;
		} else {
			damageDelay3 = calcDistanceDamagePlayerRange();
		}
		if (doubleHit) {
			damageDelay4 = 600; //normal weapon second hit (mainly for dds)
		} else if (rehitRange) {
			damageDelay4 = calcDistanceDamagePlayerRange();        //magic short bow special.
		} else if (UseBow) {
			damageDelay4 = calcDistanceDamagePlayerRange() + 600;	//basically for the dark bow
		} else {
			damageDelay4 = 0; // returns a default instant attack (also once again for second hitting)
		}
		return -1;
	}
	public void getWeaponSpecialInformation(int weapon, int animation99, int drain, int doGFX) {
		
		weapon = playerEquipment[playerWeapon];
		if (drain <= specialAmount) {	
			if (weapon == 4151 ) {
				startAnimation(animation99);
				server.npcHandler.npcs[attacknpc].gfx100(341);
				specialAmount = (specialAmount - drain);
				specOn = false;
				sendSpecialBarAmount();
			} else if (weapon == 7158) {
				startAnimation(animation99);
				gfx0(559);
				specialAmount = (specialAmount - drain);
				specOn = false;
				sendSpecialBarAmount();
			} else {
				startAnimation(animation99);
				arrowPullBack(doGFX);
				specialAmount = (specialAmount - drain);
				specOn = false;
				sendSpecialBarAmount();
			}
		} else {
			ResetAttackNPC();
			specOn = false;
		}
		
	}
	public void getWeaponSpecialInformation2(int weapon, int animation99, int drain, int doGFX) {
		
		weapon = playerEquipment[playerWeapon];
		if (drain <= specialAmount) {	
			if (weapon == 4151 ) {
				startAnimation(animation99);
				PlayerHandler.players[AttackingOn].arrowPullBack(341);
				specialAmount  = (specialAmount - drain);
				specOn = false;
				sendSpecialBarAmount();
				sendAllSpecialBars();
			} else {
				startAnimation(animation99);
				arrowPullBack(doGFX);
				specialAmount = (specialAmount - drain);
				specOn = false;
				sendSpecialBarAmount();
				sendAllSpecialBars();
			}
		} else {
			ResetAttack();
			specOn = false;
		}
		
	}
	public void checkWeaponForSpecial2() {
		if (playerEquipment[playerWeapon] == 5698)  			//dragon dagger p++
		{
			doubleHit = true;
			xtraDamage = true;
			getWeaponSpecialInformation2(5698,1062, 25, 252);
			
		}
		if (playerEquipment[playerWeapon] == 4587)
		{
			doubleHit = false;
			xtraDamage = false;
			getWeaponSpecialInformation2(4587, 1872, 50, 347);
			
		}
		if (playerEquipment[playerWeapon] == 15999)  			//dragon claw
		{
			doubleHit = true;
			xtraDamage = true;
			getWeaponSpecialInformation2(15999, 2068, 25, -1);
			
		}
		if (playerEquipment[playerWeapon] == 4151)  			//whip
		{
			xtraDamage = true;
			getWeaponSpecialInformation2(4151, CAM().GetWepAnim(), 50, 341);
			
		}
		if (playerEquipment[playerWeapon] == 5680)  			//dragon dagger p+
		{
			doubleHit = true;
			xtraDamage = true;
			getWeaponSpecialInformation2(5680,1062, 25, 252);
			
		}
		if (playerEquipment[playerWeapon] == 7158)  			//dragon 2h
		{
			
			xtraDamage = true;
			getWeaponSpecialInformation(7158, 3157, 100, 559);
			
		}
		if (playerEquipment[playerWeapon] == 1231)  			//dragon dagger p
		{
			doubleHit = true;
			xtraDamage = true;
			getWeaponSpecialInformation2(1231,1062, 25, 252);
			
		}
		if (playerEquipment[playerWeapon] == 1215)  			//dragon dagger
		{
			doubleHit = true;
			xtraDamage = true;
			getWeaponSpecialInformation2(1215,1062, 25, 252);
			
		}
		if (playerEquipment[playerWeapon] == 3204)  			//dragon hally
		{
			doubleHit = true;
			xtraDamage = true;
			getWeaponSpecialInformation2(3204, 2081, 50, 282);
		}
		if (playerEquipment[playerWeapon] == 1305) 			// dragon long
		{
			xtraDamage = true;
			getWeaponSpecialInformation2(1305,1058, 25, 248);
			
		}
		if (playerEquipment[playerWeapon] == 1434) 			 // dragon mace
		{
			xtraDamage = true;
			getWeaponSpecialInformation2(1434,1060, 25, 251);
		}
		
	}
	public void checkWeaponForSpecial() {
		if (specOn) {
			if (playerEquipment[playerWeapon] == 5698) {
				doubleHit = true;
				xtraDamage = true;
				getWeaponSpecialInformation(5698, 1062, 25, 252);
			}
			if (playerEquipment[playerWeapon] == 15999) {
				doubleHit = true;
				xtraDamage = true;
				getWeaponSpecialInformation(15999, 2068, 25, -1);
			}
			if (playerEquipment[playerWeapon] == 4587) {
				xtraDamage = true;
				getWeaponSpecialInformation(4587, 1872, 50, 347);
			}
			if (playerEquipment[playerWeapon] == 7158) {
				xtraDamage = true;
				getWeaponSpecialInformation(7158, 3157, 100, 559);
			}
			if (playerEquipment[playerWeapon] == 6739) {
				xtraDamage = true;
				getWeaponSpecialInformation(6739, 2876, 50, 479);
			}
			if (playerEquipment[playerWeapon] == 4151) {
				xtraDamage = true;
				getWeaponSpecialInformation(4151, CAM().GetWepAnim(), 50, 341);
			}
			if (playerEquipment[playerWeapon] == 5680) {
				doubleHit = true;
				xtraDamage = true;
				getWeaponSpecialInformation(5680,1062, 25, 252);
			}
			if (playerEquipment[playerWeapon] == 1231) {
				doubleHit = true;
				xtraDamage = true;
				getWeaponSpecialInformation(1231,1062, 25, 252);
			}
			if (playerEquipment[playerWeapon] == 1215) {
				doubleHit = true;
				xtraDamage = true;
				getWeaponSpecialInformation(1215,1062, 25, 252);
			}
			if (playerEquipment[playerWeapon] == 3204) {
				doubleHit = true;
				xtraDamage = true;
				getWeaponSpecialInformation(3204, 1203, 50, 282);
			}
			if (playerEquipment[playerWeapon] == 1305) {
				xtraDamage = true;
				getWeaponSpecialInformation(1305,1058, 25, 248);
			}
			if (playerEquipment[playerWeapon] == 1434) {
				xtraDamage = true;
				getWeaponSpecialInformation(1434,1060, 25, 251);
			}
		}
		return;
		
	}
	public void AttackNPC() {
		if (server.npcHandler.npcs[attacknpc].npcType != 2881) {
			if (server.npcHandler.npcs[attacknpc].playerId != playerId) {
				return;
			}
		}
		int type = server.npcHandler.npcs[attacknpc].npcType;
		
		if (type == 1616) {
			if (playerLevel[18] < 20) {
				CAM().sendMessage("You need a slayer level of 20 to attack this npc.");
				ResetAttackNPC();
				return;
			}
		} else if (type == 1637) {
			if (playerLevel[18] < 40) {
				CAM().sendMessage("You need a slayer level of 40 to attack this npc.");
				ResetAttackNPC();
				return;
			}
		} else if (type == 1624) {
			if (playerLevel[18] < 60) {
				CAM().sendMessage("You need a slayer level of 60 to attack this npc.");
				ResetAttackNPC();
				return;
			}
		} else if (type == 1615) {
			if (playerLevel[18] < 85) {
				CAM().sendMessage("You need a slayer level of 85 to attack this npc.");
				ResetAttackNPC();
				return;
			}
		}
		checkPlayersStatisics();
		if (System.currentTimeMillis() - lastAction > weaponDelay && isCloseEnough && !isStunned) {
			if (repeatSpellNpc) {
				return;
			}
			if (attacknpc != damageThisNPC) {
				damageThisNPC = attacknpc;
			}
			if (mC().usingBow()) WalkTo(0,0);
			faceNPC(attacknpc);
			if (autoCastNpc) {
				checkSpellIDsNpc();
				autoCastingNpc();
				UseBow = false;
				UseBow2 = false;
				UseMelee = false;
			} else
			runAttackNPC();
			if (specOn) {
				checkWeaponForSpecial();
			} else {
				if (!autoCastNpc) {
					startAnimation(CAM().GetWepAnim());
				}
			}
			if (UseBow && !UseBow2) {
				if (mC().usingBow()) WalkTo(0,0);
				arrowPullBack(arrowPullBack());
				startAnimation(CAM().GetWepAnim());
			}
			if (UseBow2) {
				arrowPullBack(arrowPullBack());
				kniveDelay = 1;
				knive = true;
				
			}
			lastAction = System.currentTimeMillis();
			CAM().getAttackDelay();		
		}
		return;
	}
	public boolean doubleHit() {
		switch (playerEquipment[playerWeapon]) {
		case 1215:
		case 1231:
		case 1305:
		case 1377:
		case 1434:
		case 5680:
		case 5698: 	
		case 861:
		case 15156:
			return true;
		}
		return false;
	}
	public void runAttackNPC() {
		checkPlayersStatisics();
		if (mC().usingBow()) {
			WalkTo(0,0);
		}
		if (autoCastNpc) {
			CAM().sendMessage("this");
			autoCastThisNpc();	
			CAM().sendMessage("this");
			return;
		}
		if (UseBow || UseBow2) {
			if (attackDelay3 > 0) {
				range2 = 1;
				attackDelay4 = 3;
			} else {
				range1 = 1;
				attackDelay3 = 3;	
			}
			autoCastNpc = false;
			if (doubleHit()) {
				if (playerEquipment[playerWeapon] != 861) {
					range2 = 1;
					attackDelay4 = 4;
					rehitRange = false;
				}
			}
		} else if (UseMelee) {
			melee1 = 1;
			attackDelay = 1;	
			autoCastNpc = false;
			if (specOn) {
				if (doubleHit()) {
					melee2 = 1;
					attackDelay2 = 2;
					doubleHit = false;
				}
			}
		}
	}
	public int getTheCurrentSpellPVP() { 
		switch(playerMagicID) {
		case 1152://wind strike
			gatherMagicInformationPVP(1152, 0, 1, 711, 90, 91, 92, 2, 558, 1, 556, 1, -1, -1, -1, -1, 100, 0);
			break;
		case 1153://confuse
			gatherMagicInformationPVP(1153, 0, 3, 716, 102, 103, 104, 0, 555, 3, 557, 2, 559, 1,  -1, -1,100,0);
			break;
		case 1154://water strike
			gatherMagicInformationPVP(1154, 0, 5, 711, 93, 94, 95, 4, 555, 1, 556, 1, 558, 1,  -1, -1,100,0);
			break;
		case 1156://earth strike
			gatherMagicInformationPVP(1156, 0, 9, 711, 96, 97, 98, 6, 558, 1, 556, 1, 557, 2,  -1, -1,100,0);
			break;
		case 1157://weaken
			gatherMagicInformationPVP(1157, 0, 11, 729, 105, 106, 107, 0, 555, 3, 557, 2, 559, 1,  -1, -1,100,0);
			break;
		case 1158://fire strike
			gatherMagicInformationPVP(1158, 0, 13, 711, 99, 100, 101, 8, 554, 3, 556, 2, 558, 1,  -1, -1,100,0);
			break;
		case 1160://wind bolt
			gatherMagicInformationPVP(1160, 0, 17, 711, 117, 118, 119, 9, 556, 2, 562, 1, -1, -1,  -1, -1,100,0);
			break;
		case 1161://curse
			gatherMagicInformationPVP(1161, 0, 19, 729, 108, 109, 110, 0, 555, 2, 557, 3, 559, 1,  -1, -1,100,0);
			break;
		case 1572://bind
			gatherMagicInformationPVP(1172, 10, 20, 724, 177, 178, 181, 1, 557, 3, 555, 3, 561, 2,  -1, -1,100,0);
			break;
		case 1163://water bolt
			gatherMagicInformationPVP(1163, 0, 23, 711, 120, 121, 122, 10, 555, 3, 556, 2, 562, 1,  -1, -1,100,0);
			break;
		case 1166://earth bolt
			gatherMagicInformationPVP(1166, 0, 29, 711, 123, 124, 125, 11, 557, 3, 556, 2, 562, 1,  -1, -1,100,0);
			break;
		case 1169://fire bolt
			gatherMagicInformationPVP(1169, 0, 35, 711, 126, 127, 128, 12, 554, 4, 556, 3, 562, 1,  -1, -1,100,0);
			break;
		case 1172://windblast
			gatherMagicInformationPVP(1172, 0, 41, 711, 132, 133, 134, 13, 556, 3, 560, 1, -1, -1,  -1, -1,100,0);
			break;
		case 1175://waterblast
			gatherMagicInformationPVP(1175, 0, 47, 711, 135, 136, 137, 14, 555, 3, 556, 3, 560, 1, -1, -1, 100,0);
			break;
		case 1582://snare
			gatherMagicInformationPVP(1182, 20, 50, 724, 177, 178, 180, 3, 557, 4, 555, 4, 561, 3, -1, -1, 100,0);
			break;
		case 1177://earthblast
			gatherMagicInformationPVP(1177, 0, 53, 711, 138, 139, 140, 15, 557, 4, 556, 3, 560, 1, -1, -1, 100,0);
			break;
		case 1181://fireblast
			gatherMagicInformationPVP(1181, 0, 59, 711, 129, 130, 131, 16, 554, 5, 556, 4, 560, 1, -1, -1, 100,0);
			break;
		case 1183://windwave
			gatherMagicInformationPVP(1183, 0, 62, 718, 158, 159, 160, 17, 556, 5, 565, 1, -1, -1, -1, -1, 100,0);
			break;
		case 1185://waterwave
			gatherMagicInformationPVP(1185, 0, 65, 718, 161, 162, 163, 18, 555, 7, 556, 5, 565, 1, -1, -1, 100,0);
			break;
		case 1188://earthwave
			gatherMagicInformationPVP(1188, 0, 70, 718, 164, 165, 166, 19, 557, 7, 556, 5, 565, 1, -1, -1, 100,0);
			break;
		case 1189://firewave
			gatherMagicInformationPVP(1189, 0, 75, 718, 155, 156, 157, 20, 554, 7, 556, 5, 565, 1, -1, -1, 100,0);
			break;
		case 1592://entangle
			gatherMagicInformationPVP(1192, 30, 79, 710, 177, 178, 179, 5, 557, 5, 555, 5, 561, 4, -1, -1, 100,0);
			break;
		case 12881: //ice burst
			gatherMagicInformationPVP(12881, 20, 70, 1979, -1, -1, 363, 22, 560, 2, 562, 4, 555, 4, -1, -1,0,-900);
			break;
		case 12871: //ice blitz
			gatherMagicInformationPVP(12871, 30, 82, 1978, 366, -1, 367, 26, 560, 2, 565, 2, 555, 3, -1, -1,100,1500);		
			break;
		case 12975: //smoke barrage
			gatherMagicInformationPVP(12975, 0, 86, 1979, -1, -1, 391, 27, 560, 4, 565, 2, 556, 4, 554, 4, 0, 200);		
			break;
		case 13023: //shadow barrage
			gatherMagicInformationPVP(13023, 0, 88, 1979, -1, -1, 383, 28, 560, 4, 565, 2, 556, 4, 566, 3, 0, 200);		
			break;
		case 12929: //blood barrage
			gatherMagicInformationPVP(12929, 0, 92, 1979, -1, -1, 377, 29, 566, 1, 565, 4, 560, 4, -1, -1, 0, 200);		
			break;
		case 12891: //ice barrage
			gatherMagicInformationPVP(12891, 30 + misc.random(10), 94, 1979, -1, -1, 369, 30, 555, 6, 565, 2, 560, 4, -1, -1, 0, 200);		
			break;
		} 
		return -1;
	}
	public boolean hasLevelReqNpc() {
		if (autoCastLevel > playerLevel[6]) {
			return false;
		}
		return true;
	}
	public boolean autoCastingNpc() {
		checkSpellIDsNpc();
		if (autoCastID < 1) {
			CAM().sendMessage("NO SPELL FOUND.");
			return false;
		}
		if (!spellSelectedNpc) {
			CAM().sendMessage("SPELL NOT SELECTED.");
			return false;
		}
		if (spellSelectedNpc()) {
			if (hasLevelReqNpc()) {				
				autoCastThisNpc();
				spellSelected = false;
			} else {		
				return false;
			}
		} else {
			return false;
		}
		return true;
	}
	public boolean spellSelectedNpc() {
		if (!spellSelectedNpc) {
			return false;
		}
		spellSelected = false;
		return true;
	}
	public void autoCastThisNpc() {
		switch(autoCastID) {
		case 1://wind strike
			gatherMagicInformation(1152, 100, 0, 1, 711, 90, 91, 92, 2, 558, 1, 556, 1, -1, -1, -1, -1);
			break;
		case 2://water strike
			gatherMagicInformation(1154, 100, 0, 5, 711, 93, 94, 95, 4, 555, 1, 556, 1, 558, 1,  -1, -1);
			break;
		case 3://earth strike
			gatherMagicInformation(1156, 100, 0, 9, 711, 96, 97, 98, 6, 558, 1, 556, 1, 557, 2,  -1, -1);
			break;
		case 4://fire strike
			gatherMagicInformation(1158, 100, 0, 13, 711, 99, 100, 101, 8, 554, 3, 556, 2, 558, 1,  -1, -1);
			break;
		case 5://wind bolt
			gatherMagicInformation(1160, 100, 0, 17, 711, 117, 118, 119, 9, 556, 2, 562, 1, -1, -1,  -1, -1);
			break;
		case 6://water bolt
			gatherMagicInformation(1163, 100, 0, 23, 711, 120, 121, 122, 10, 555, 3, 556, 2, 562, 1,  -1, -1);
			break;
		case 7://earth bolt
			gatherMagicInformation(1166, 100, 0, 29, 711, 123, 124, 125, 11, 557, 3, 556, 2, 562, 1,  -1, -1);
			break;
		case 8://fire bolt
			gatherMagicInformation(1169, 100, 0, 35, 711, 126, 127, 128, 12, 554, 4, 556, 3, 562, 1,  -1, -1);
			break;
		case 9://windblast
			gatherMagicInformation(1172, 100, 0, 41, 711, 132, 133, 134, 13, 556, 3, 560, 1, -1, -1,  -1, -1);
			break;
		case 10://waterblast
			gatherMagicInformation(1175, 100, 0, 47, 711, 135, 136, 137, 14, 555, 3, 556, 3, 560, 1, -1, -1);
			break;
		case 11://earthblast
			gatherMagicInformation(1177, 100, 0, 53, 711, 138, 139, 140, 15, 557, 4, 556, 3, 560, 1, -1, -1);
			break;
		case 12://fireblast
			gatherMagicInformation(1181, 100, 0, 59, 711, 129, 130, 131, 16, 554, 5, 556, 4, 560, 1, -1, -1);
			break;
		case 13://windwave
			gatherMagicInformation(1183, 100, 0, 62, 718, 158, 159, 160, 17, 556, 5, 565, 1, -1, -1, -1, -1);
			break;
		case 14://waterwave
			gatherMagicInformation(1185, 100, 0, 65, 718, 161, 162, 163, 18, 555, 7, 556, 5, 565, 1, -1, -1);
			break;
		case 15://earthwave
			gatherMagicInformation(1188, 100, 0, 70, 718, 164, 165, 166, 19, 557, 7, 556, 5, 565, 1, -1, -1);
			break;
		case 16://firewave
			gatherMagicInformation(1189, 100, 0, 75, 718, 155, 156, 157, 20, 554, 7, 556, 5, 565, 1, -1, -1);
			break;
		}
	}
	public void checkSpellIDsNpc() {
		switch(autoCastID) {
		case 1:	
			castRune1 = 558;
			castRune1Amount = 1;
			castRune2 = 556;
			castRune2Amount = 1;				
			castRune3 = 0;
			castRune3Amount = 0;				
			castRune4 = 0;
			castRune4Amount = 0;
			autoCastLevel = 1;	
			break;
		case 2:	
			castRune1 = 555;
			castRune1Amount = 1;
			castRune2 = 556;
			castRune2Amount = 1;				
			castRune3 = 558;
			castRune3Amount = 1;				
			castRune4 = 0;
			castRune4Amount = 0;
			autoCastLevel = 5;	
			break;
		case 3:
			castRune1 = 558;
			castRune1Amount = 1;
			castRune2 = 556;
			castRune2Amount = 1;				
			castRune3 = 557;
			castRune3Amount = 2;				
			castRune4 = 0;
			castRune4Amount = 0;
			autoCastLevel = 9;	
			break;
		case 4:
			castRune1 = 554;
			castRune1Amount = 3;
			castRune2 = 556;
			castRune2Amount = 2;				
			castRune3 = 558;
			castRune3Amount = 1;				
			castRune4 = 0;
			castRune4Amount = 0;
			autoCastLevel = 13;	
			break;
		case 5:
			castRune1 = 556;
			castRune1Amount = 2;
			castRune2 = 562;
			castRune2Amount = 1;				
			castRune3 = 0;
			castRune3Amount = 0;				
			castRune4 = 0;
			castRune4Amount = 0;
			autoCastLevel = 17;	
			break;
		case 6:
			castRune1 = 556;
			castRune1Amount = 2;
			castRune2 = 562;
			castRune2Amount = 1;				
			castRune3 = 555;
			castRune3Amount = 3;				
			castRune4 = 0;
			castRune4Amount = 0;
			autoCastLevel = 23;	
			break;
		case 7:
			castRune1 = 557;
			castRune1Amount = 3;
			castRune2 = 556;
			castRune2Amount = 2;				
			castRune3 = 562;
			castRune3Amount = 1;				
			castRune4 = 0;
			castRune4Amount = 0;
			autoCastLevel = 29;	
			break;
		case 8:
			castRune1 = 554;
			castRune1Amount = 4;
			castRune2 = 556;
			castRune2Amount = 3;				
			castRune3 = 562;
			castRune3Amount = 1;				
			castRune4 = 0;
			castRune4Amount = 0;
			autoCastLevel = 35;	
			break;
		case 9:
			castRune1 = 556;
			castRune1Amount = 3;
			castRune2 = 560;
			castRune2Amount = 1;				
			castRune3 = 0;
			castRune3Amount = 0;				
			castRune4 = 0;
			castRune4Amount = 0;
			autoCastLevel = 41;	
			break;
		case 10:
			castRune1 = 555;
			castRune1Amount = 3;
			castRune2 = 556;
			castRune2Amount = 3;				
			castRune3 = 560;
			castRune3Amount = 1;				
			castRune4 = 0;
			castRune4Amount = 0;
			autoCastLevel = 47;	
			break;
		case 11:
			castRune1 = 557;
			castRune1Amount = 4;
			castRune2 = 556;
			castRune2Amount = 3;				
			castRune3 = 560;
			castRune3Amount = 1;				
			castRune4 = 0;
			castRune4Amount = 0;
			autoCastLevel = 53;	
			break;
		case 12:
			castRune1 = 554;
			castRune1Amount = 5;
			castRune2 = 556;
			castRune2Amount = 4;				
			castRune3 = 560;
			castRune3Amount = 1;				
			castRune4 = 0;
			castRune4Amount = 0;
			autoCastLevel = 59;	
			break;
		case 13:
			castRune1 = 556;
			castRune1Amount = 5;
			castRune2 = 565;
			castRune2Amount = 1;				
			castRune3 = 0;
			castRune3Amount = 0;				
			castRune4 = 0;
			castRune4Amount = 0;
			autoCastLevel = 62;	
			break;
		case 14:
			castRune1 = 556;
			castRune1Amount = 7;
			castRune2 = 565;
			castRune2Amount = 1;				
			castRune3 = 555;
			castRune3Amount = 5;				
			castRune4 = 0;
			castRune4Amount = 0;
			autoCastLevel = 65;	
			break;
		case 15:
			castRune1 = 557;
			castRune1Amount = 7;
			castRune2 = 565;
			castRune2Amount = 1;				
			castRune3 = 556;
			castRune3Amount = 5;				
			castRune4 = 0;
			castRune4Amount = 0;
			autoCastLevel = 70;	
			break;
		case 16:
			castRune1 = 554;
			castRune1Amount = 7;
			castRune2 = 565;
			castRune2Amount = 1;				
			castRune3 = 556;
			castRune3Amount = 5;				
			castRune4 = 0;
			castRune4Amount = 0;
			autoCastLevel = 75;	
			break;
		}
	}
	public boolean checkPlayersStatisics() {
		int EnemyX = server.npcHandler.npcs[attacknpc].absX;
		int EnemyY = server.npcHandler.npcs[attacknpc].absY;
		int arrowgfx = 10;
		int[] arrowIds = {882, 884, 886, 888, 890, 892};
		int[] arrowGfx = {10, 9, 11, 12, 13, 15};	
		int offsetX = (absY - EnemyY) * -1;
		int offsetY = (absX - EnemyX) * -1;
		UseBow = false;
		UseBow2 = false;
		UseMelee = false;
		isCloseEnough = false;
		UseBow2 = false;
		if (specOn) {
			if (specialAmount < getWeaponsDrainAmount()) {
				specOn = false;
			} else if (specialAmount >= getWeaponsDrainAmount()) {
				specOn = true;
			}
		}
		for (int i = 0; i < NONbowStuff.length; i++) {
			if (playerEquipment[playerWeapon] == NONbowStuff[i]) {
				UseBow2 = true;
				break;
			}
		}
		if (specOn) {
			if (UseBow && specialAmount < getWeaponsDrainAmount()) {
				specOn = false;
				rehitRange = false;
			} else if (UseBow && specialAmount >= getWeaponsDrainAmount()) {
				specOn = true;
				rehitRange = true;
			}
			if (!UseBow && specialAmount < getWeaponsDrainAmount()) {
				specOn = false;
				doubleHit = false;
			} else if (!UseBow && specialAmount >= getWeaponsDrainAmount()) {
				specOn = true;
			}
		}
		
		if (absX == EnemyX && absY == EnemyY)
		{
			WalkTo(+1,0);
		}
		for (int i = 0; i < shortbow.length; i++)
		{
			if ((playerEquipment[playerWeapon] == shortbow[i]) || (playerEquipment[playerWeapon] == longbow[i]))
			{
				UseBow = true;
				break;
			}
		}	
		if (UseBow || UseBow2) {
			if (UseBow || UseBow2 && GoodDistance(EnemyX, EnemyY, absX, absY, 6) == true) {
				WalkTo(0,0);
				isCloseEnough = true;
			}
			if (!GoodDistance(EnemyX, EnemyY, absX, absY, 8) == true) {
				
				WalkTo(0,0);
				isCloseEnough = false;
			}
		}
		if (!UseBow && !UseBow2 && GoodDistance(EnemyX, EnemyY, absX, absY, 1) == true)
		{
			isCloseEnough = true;
		}
		if (!UseBow && !UseBow2 && GoodDistance(EnemyX, EnemyY, absX, absY, 1) == true)
		{
			isCloseEnough = true;
		}
		if (playerEquipment[playerArrows] < 1 && playerEquipment[playerWeapon] != 4212 && UseBow && !UseBow2)
		{
			WalkTo(0,0);
			CAM().sendMessage("You are out of arrows!");
			ResetAttackNPC();
			isCloseEnough = false;
			return false;
		}
		if ((playerEquipment[playerWeapon] == 4734) && (playerEquipment[playerArrows] != 4740) && UseBow)
		{
			WalkTo(0,0);
			CAM().sendMessage("you cannot use these arrows with this bow!");
			isCloseEnough = false;
			ResetAttackNPC();
		}
		if (playerEquipment[playerWeapon] != 4734 && playerEquipment[playerArrows] == 4740 && UseBow && !UseBow2)
		{
			WalkTo(0,0);
			CAM().sendMessage("you cannot use this bow with these arrows!");
			isCloseEnough = false;
			ResetAttackNPC();
		}		
		if ((playerEquipment[playerWeapon] == 4212) && (playerEquipment[playerArrows] != -1) && UseBow == true)
		{
			WalkTo(0,0);
			CAM().sendMessage("you cannot use arrows with this bow!");
			isCloseEnough = false;
			ResetAttackNPC();
			return false;
		}
		if (playerEquipment[playerWeapon] == 15156) {
			rehitRange = true;
			UseBow = true;
			UseBow2 = false;
			UseMelee = false;
		}
		if (!UseBow && !UseBow2) {
			UseMelee = true;
		}
		return false;
	}	
	public int maxRangeHit() {
		int rangehit = 0;
		int weapon = playerEquipment[playerWeapon];
		int Arrows = playerEquipment[playerArrows];
		if(Arrows == 4740 && weapon == 4734) {//BoltRacks
			rangehit = 3;
			rangehit += playerLevel[4]/5.5;
		}
		else if(Arrows == 892 || Arrows == 868) {//rune arrows
			rangehit = 3;
			rangehit += playerLevel[4]/6;
		}
		else if(Arrows == 890 || Arrows == 867) {//adamant arrows
			rangehit = 2;
			rangehit += playerLevel[4]/7;
		}
		else if(weapon == 4212) {//crystalbow
			rangehit = 4;
			rangehit += playerLevel[4]/5;
		}
		else if(Arrows == 888 || Arrows == 866 || Arrows == 869) {//mithril arrows
			rangehit = 2;
			rangehit += playerLevel[4]/7.5;
		}
		else if(Arrows == 886 || Arrows == 865) {//steel arrows
			rangehit = 2;
			rangehit += playerLevel[4]/8;
		}
		else if(Arrows == 884 || Arrows == 863) {//Iron arrows
			rangehit = 2;
			rangehit -= 1/8;
			rangehit += playerLevel[4]/9;
		}
		else if(Arrows == 882 || Arrows == 864) {//Bronze arrows
			rangehit = 1;
			rangehit += playerLevel[4]/9;
		}
		if(FightType != 1) {
			rangehit -= rangehit/10;
		}
		return rangehit;
	}
	public int arrowPullBackPVP() {
		if (followThisPlayer < 1) {
			return -1;
		}
		startProjectile();
		int A = playerEquipment[playerArrows];	
		int w = playerEquipment[playerWeapon];
		if (A == 892) {
			return 24;
		}
		if (A == 890) {
			return 22;
		}
		if (A == 888) {
			return 21;
		}
		if (A == 886) {
			return 20;
		}
		if (A == 884) {
			return 18;
		}
		if (w == 4212) {
			return 250;
		}
		if (A == 882) {
			return 19;
		}
		return -1;
	}
	public void startProjectile() {
		client AttackingOn2 = (client) server.playerHandler.players[followThisPlayer];
		int EnemyX = PlayerHandler.players[followThisPlayer].absX;
		int EnemyY = PlayerHandler.players[followThisPlayer].absY;
		int w = playerEquipment[playerWeapon];
		int offsetX = (absX - EnemyX) * -1;
		int offsetY = (absY - EnemyY) * -1;
		if (w == 4212) {
			Projectile2(absY, absX, offsetX, offsetY, 50, calcDistanceSpeedPlayerRange() + 5, getarrowgfxnow(), 45, 31,  followThisPlayer );
		} else if (w == 15156) {
			DeleteArrow();
			DeleteArrow();
			Projectile2(absY, absX, offsetX, offsetY, 50, calcDistanceSpeedPlayerRange() + 25, getarrowgfxnow(), 48, 35, - followThisPlayer );
			Projectile2(absY, absX, offsetX, offsetY, 50, calcDistanceSpeedPlayerRange(), getarrowgfxnow(), 45, 31, - followThisPlayer );
		} else {
			DeleteArrow();		
			Projectile2(absY, absX, offsetX, offsetY, 50, calcDistanceSpeedPlayerRange() - 3, getarrowgfxnow(), 45, 31,  followThisPlayer -1);
		}
	}
	public boolean playerRangedDmg() {
		if (followThisPlayer < 1) {	
			stopAttack = true;
			return false;
		}
		client AttackingOn2 = (client) server.playerHandler.players[followThisPlayer];
		AttackingOn2.lastDamageTaken = System.currentTimeMillis();
		thisX = AttackingOn2.absX;
		thisY = AttackingOn2.absY;
		if (AttackingOn2.tStage > 0) {
			return false;
		}
		if (isInWilderness(thisX,thisY, 1) == false) {
			return false;
		}
		if (AttackingOn2.inTeleport) {
			return false;
		}
		int MAXHIT = 0;
		int aBonus = 0;
		int dBonus = 0;
		int rand_def = (int) (misc.random(AttackingOn2.playerLevel[9]) + AttackingOn2.getPrayerDef());
		int rand_att = misc.random(playerLevel[4]);
		int random_u = misc.random(playerBonus[4]);
		int random_def = misc.random(AttackingOn2.playerBonus[9]);
		if ((random_u >= random_def) && (rand_att > rand_def)) {
			MAXHIT = misc.random(maxRangeHit());
			if (AttackingOn2.vengOn) {
				vengDamage = (MAXHIT / 2 + MAXHIT / 4);
			}
			if (PlayerHandler.players[followThisPlayer].protRange) {
				MAXHIT = (MAXHIT / 2);
			}
		} else {
			MAXHIT = 0;
		}
		int EnemyX = PlayerHandler.players[followThisPlayer].absX;
		int EnemyY = PlayerHandler.players[followThisPlayer].absY;
		int EnemyHP = PlayerHandler.players[followThisPlayer].currentHealth;	
		if (MAXHIT > EnemyHP) {
			MAXHIT = EnemyHP;
			if (AttackingOn2.vengOn) {
				AttackingOn2.plrText = "Taste Vengence!";
                                AttackingOn2.plrTextUpdateRequired = true;
				AttackingOn2.vengOn = false;
				vengDamage = 0;
				if (hitUpdateRequired) {
					hitDiff2 = vengDamage;
					dealDamage(vengDamage);
					killers[localId] += vengDamage;
					hits++;
					fighting = true;
					fightId = localId;
					hitUpdateRequired2 = true;
					updateRequired = true;
					appearanceUpdateRequired = true;
				} else {
					hitDiff = vengDamage;
					dealDamage(vengDamage);
					killers[localId] += vengDamage;
					hits++;
					fighting = true;
					fightId = localId;
					hitUpdateRequired = true;
					updateRequired = true;
					appearanceUpdateRequired = true;
				}
			}
		}
		if (EnemyHP < 1 ) {
			MAXHIT = 0;
			stopAttack = true;
			return false;
		}
		AttackingOn2.inCombat();
		if (MAXHIT >= 23 && playerWeapon != 15156) {
			MAXHIT = 23;
		}
		if (MAXHIT > 0) {//here
			if (AttackingOn2.vengOn) {
				AttackingOn2.plrText = "Taste Vengence!";
                                AttackingOn2.plrTextUpdateRequired = true;
				AttackingOn2.vengOn = false;
				if (hitUpdateRequired) {
					hitDiff2 = vengDamage;
					dealDamage(vengDamage);
					killers[localId] += vengDamage;
					hits++;
					fighting = true;
					fightId = localId;
					hitUpdateRequired2 = true;
					updateRequired = true;
					appearanceUpdateRequired = true;
				} else {
					hitDiff = vengDamage;
					dealDamage(vengDamage);
					killers[localId] += vengDamage;
					hits++;
					fighting = true;
					fightId = localId;
					hitUpdateRequired = true;
					updateRequired = true;
					appearanceUpdateRequired = true;
				}
			}
			if (AttackingOn2.playerEquipment[playerRing] == 2550) {
				if (MAXHIT >= 1 && MAXHIT <= 7) {
					recoilDamage = 1;
				} else if (MAXHIT >= 8 && MAXHIT <= 14) {
					recoilDamage = 2;
				} else if (MAXHIT >= 15 && MAXHIT <= 21) {
					recoilDamage = 3;
				} else if (MAXHIT >= 22 && MAXHIT <= 28) {
					recoilDamage = 5;
				} else if (MAXHIT >= 29 && MAXHIT <= 35) {
					recoilDamage = 6;
				} else if (MAXHIT > 35) {
					recoilDamage = 7;
				}
				if (recoilDamage > 0) {
					if (AttackingOn2.currentHealth == 0) {
						recoilDamage = 0;
					}
					if (recoilDamage > currentHealth) {
						recoilDamage = currentHealth;
					}
					if (hitUpdateRequired) {
						hitDiff2 = recoilDamage;
						dealDamage(recoilDamage);
						killers[localId] += recoilDamage;
						hits++;
						fighting = true;
						fightId = localId;
						hitUpdateRequired2 = true;
						updateRequired = true;
						appearanceUpdateRequired = true;
					} else {
						hitDiff = recoilDamage;
						dealDamage(recoilDamage);
						killers[localId] += recoilDamage;
						hits++;
						fighting = true;
						fightId = localId;
						hitUpdateRequired = true;
						updateRequired = true;
						appearanceUpdateRequired = true;
					}
					if (AttackingOn2.recoilPercent >= 100) {
						if (misc.random(5) == 5) {
							AttackingOn2.removeRing(2550, playerRing);
							AttackingOn2.CAM().sendMessage("You ring crumbles to dust.");
						}
					}
					if (AttackingOn2.recoilPercent < 100) {
						AttackingOn2.recoilPercent = (AttackingOn2.recoilPercent + 10);
					}
				}
				recoilDamage = 0;
			}
			
		}
		if (!PlayerHandler.players[followThisPlayer].hitUpdateRequired) {
			PlayerHandler.players[followThisPlayer].hitUpdateRequired = true;
			PlayerHandler.players[followThisPlayer].updateRequired = true;
			PlayerHandler.players[followThisPlayer].appearanceUpdateRequired = true;
			PlayerHandler.players[followThisPlayer].dealDamage(MAXHIT);
			PlayerHandler.players[followThisPlayer].hitDiff = MAXHIT;
			PlayerHandler.players[followThisPlayer].killers[localId] += MAXHIT;
			PlayerHandler.players[followThisPlayer].hits++;
			PlayerHandler.players[followThisPlayer].fighting = true;
			PlayerHandler.players[followThisPlayer].fightId = localId;
			PlayerHandler.players[followThisPlayer].isInCombat = true;
		} else {
			PlayerHandler.players[followThisPlayer].hitUpdateRequired2 = true;
			PlayerHandler.players[followThisPlayer].updateRequired = true;
			PlayerHandler.players[followThisPlayer].appearanceUpdateRequired = true;
			PlayerHandler.players[followThisPlayer].dealDamage(MAXHIT);
			PlayerHandler.players[followThisPlayer].hitDiff2 = MAXHIT;
			PlayerHandler.players[followThisPlayer].killers[localId] += MAXHIT;
			PlayerHandler.players[followThisPlayer].hits++;
			PlayerHandler.players[followThisPlayer].fighting = true;
			PlayerHandler.players[followThisPlayer].fightId = localId;
			PlayerHandler.players[followThisPlayer].isInCombat = true;
		}	
		addSkillXPmelee(MAXHIT * 70 * server.SERVER_EXPERIENCE, 4);
		addSkillXPmelee(MAXHIT * 25 * server.SERVER_EXPERIENCE, playerHitpoints);
		if (!UseBow2) {
			if (misc.random(3) != 1) {
				DropArrowsPLAYER();
			}
		}
		return true;
	}
	public int arrowPullBack() {
		int arrows = playerEquipment[playerArrows];	
		int weapons = playerEquipment[playerWeapon];
		if (IsAttackingNPC)
		{
			int arrowgfx = 249;
			long thisAttack = System.currentTimeMillis();
			int EnemyX = server.npcHandler.npcs[attacknpc].absX;
			int EnemyY = server.npcHandler.npcs[attacknpc].absY;
			int EnemyHP = server.npcHandler.npcs[attacknpc].HP;
			int hitDiff = 0;
			int type = server.npcHandler.npcs[attacknpc].npcType;
			int[] arrowIds = {882, 884, 886, 888, 890, 892};
			int[] arrowGfx = {10, 9, 11, 12, 13, 15};
			boolean UseBow = false;
			int offsetX = (absY - EnemyY) * -1;
			int offsetY = (absX - EnemyX) * -1;
			
			if ( thisAttack - lastAttack >= 20 )
			{
				if (weapons == 4734) {
					
					Projectile2(absY, absX, offsetX, offsetY, 50, calcDistanceSpeed2(), getarrowgfxnow(), 37, 40, attacknpc+1);
					DeleteArrow();
				} else if (UseBow2 && weapons != 15156) {
					Projectile2(absY, absX, offsetX, offsetY, 50, calcDistanceSpeed2() - 5, NONbowProjectiles(), 44, 33, attacknpc+1);
					playerEquipmentN[playerWeapon] = (playerEquipmentN[playerWeapon] - 1);
					updateRequired = true;
					appearanceUpdateRequired = true;
				} else if (specOn && weapons == 861 && !UseBow2) {
					rehitRange = true;
					Projectile2(absY, absX, offsetX, offsetY, 50, calcDistanceSpeed2(), 249, 48, 31, attacknpc+1);
					Projectile2(absY, absX, offsetX, offsetY, 50, calcDistanceSpeed2() + 25, 249, 42, 31, attacknpc+1);
					DeleteArrow();
					DeleteArrow();
					specialAmount -= 50;		
				} else  if (weapons != 4212 && weapons != 15156 && !UseBow2)  {
					int arrowgfx2 = getarrowgfxnow();
					if (!UseBow2){
						DeleteArrow();}
					Projectile2(absY, absX, offsetX, offsetY, 50, calcDistanceSpeed2(), getarrowgfxnow(), 42, 31, attacknpc+1);	
				} else if (weapons == 4212 && !UseBow2)  {	
					Projectile2(absY, absX, offsetX, offsetY, 50, calcDistanceSpeed2(), 249, 42,31, attacknpc+1);
				} else if (weapons == 15156) {
					UseBow2 = false;
					rehitRange = true;
					DeleteArrow();
					DeleteArrow();
					Projectile2(absY, absX, offsetX, offsetY,50, calcDistanceSpeed2() + 5, getarrowgfxnow(), 43, 31, attacknpc+1);
					Projectile2(absY, absX, offsetX, offsetY, 50, calcDistanceSpeed2() + 25, getarrowgfxnow(), 50, 31, attacknpc+1);
				}
			}
		}
		if (!UseBow2) {
			if (specOn) {
				return 250;
			} else if (arrows == 892)  {
				return 24;
			} else if (arrows == 890) {
				return 22;
			} else if (arrows == 888) {
				return 21;
			}  else if (arrows == 886) {
				return 20;
			} else if (arrows == 884) {
				return 18;
			} else if (weapons == 4212) {
				return 250;
			} else if (arrows == 882) {
				return 19;
			}} else if (UseBow2) {
			int W = playerEquipment[playerWeapon];
			switch(W) {
				
			case 863:
				return 220;
			case 864:
				return 219;
			case 865:
				return 221;
			case 866:
				return 223;
			case 867:
				return 224;
			case 868:
				return 225;
			case 869:
				return 222;
			case 806:
				return 232;
			case 807:
				return 233;
			case 808:
				return 234;
			case 809:
				return 235;
			case 810:
				return 236;
			case 811:
				return 237;
			case 825:
				return 206;
			case 826:
				return 207;
			case 827:
				return 208;
			case 828:
				return 209;
			case 829:
				return 210;
			case 830:
				return 211;
			case 800:
				return 42;
			case 801:
				return 43;
			case 802:
				return 44;
			case 803:
				return 45;
			case 804:
				return 46;
			case 805:
				return 48;					
			case 882:
				return 19;	
			case 884:
				return 18;
			case 886:
				return 20;
			case 888:
				return 21;		
			case 890:
				return 22;			
			case 892:
				return 24;			
			case 4212:
			case 4214:
			case 4215:
			case 4216:
			case 4217:
			case 4218:
			case 4219:
			case 4220:
			case 4221:
			case 4222:
			case 4223:
				return 250;
			}
		}
		return -1;
	}
	public int drawBack() {
		long thisAttack = System.currentTimeMillis();
		int EnemyX = server.npcHandler.npcs[attacknpc].absX;
		int EnemyY = server.npcHandler.npcs[attacknpc].absY;
		int hitDiff = 0;
		boolean UseBow2 = false;
		int offsetX = (absY - EnemyY) * -1;
		int offsetY = (absX - EnemyX) * -1;
		if (IsAttackingNPC)
		{
			if ( thisAttack - lastAttack > 0 )
			{
				if (throwable)
				{
					throwable = false;
					
					Projectile2(absY, absX, offsetX, offsetY, 50, 65, NONbowProjectiles(), 43, 31, attacknpc+1);
				}
				playerEquipmentN[playerWeapon] -= 1;
				updateRequired = true;
				appearanceUpdateRequired = true;
				lastAttack = System.currentTimeMillis();		
			}
			
		}
		int W = playerEquipment[playerWeapon];
		switch(W) {
			
		case 863:
			return 220;
		case 864:
			return 219;
		case 865:
			return 221;
		case 866:
			return 223;
		case 867:
			return 224;
		case 868:
			return 225;
		case 869:
			return 222;
		case 806:
			return 232;
		case 807:
			return 233;
		case 808:
			return 234;
		case 809:
			return 235;
		case 810:
			return 236;
		case 811:
			return 237;
		case 825:
			return 206;
		case 826:
			return 207;
		case 827:
			return 208;
		case 828:
			return 209;
		case 829:
			return 210;
		case 830:
			return 211;
		case 800:
			return 42;
		case 801:
			return 43;
		case 802:
			return 44;
		case 803:
			return 45;
		case 804:
			return 46;
		case 805:
			return 48;					
		case 882:
			return 19;	
		case 884:
			return 18;
		case 886:
			return 20;
		case 888:
			return 21;		
		case 890:
			return 22;			
		case 892:
			return 24;			
		case 4212:
		case 4214:
		case 4215:
		case 4216:
		case 4217:
		case 4218:
		case 4219:
		case 4220:
		case 4221:
		case 4222:
		case 4223:
			return 250;
		}
		return -1;
	}
	public int NONbowProjectiles() {
		int W = playerEquipment[playerWeapon];
		switch(W) {
			
		case 863:
			return 213;
		case 864:
			return 212;
		case 865:
			return 214;
		case 866:
			return 216;
		case 867:
			return 217;
		case 868:
			return 218;	
		case 869:
			return 215;
		case 806:
			return 226;
		case 807:
			return 227;
		case 808:
			return 228;
		case 809:
			return 229;
		case 810:
			return 230;
		case 811:
			return 231;	
		case 825:
			return 200;
		case 826:
			return 201;
		case 827:
			return 202;
		case 828:
			return 203;
		case 829:
			return 204;
		case 830:
			return 205;	
		case 6522:
			return 442;
		case 800:
			return 36;
		case 801:
			return 35;
		case 802:
			return 37;
		case 803:
			return 38;
		case 804:
			return 39;
		case 805:
			return 41;
		case 882:
			return 10;
		case 884:
			return 9;
		case 886:
			return 11;
		case 888:
			return 12;
		case 890:
			return 13;
		case 892:
			return 15;
		case 4212:
		case 4214:
		case 4215:
		case 4216:
		case 4217:
		case 4218:
		case 4219:
		case 4220:
		case 4221:
		case 4222:
		case 4223:
			return 249;
		}
		return -1;
	}
	public int getarrowgfxnow() {
		int W = playerEquipment[playerWeapon];
		if (playerEquipment[playerWeapon] == 4212)
		{
			return 249;
		}
		if (playerEquipment[playerArrows] == 892)
		{
			return 15;
		}
		if (playerEquipment[playerArrows] == 4740)
		{
			return 27;
		}
		if (playerEquipment[playerArrows] == 890)
		{
			return 13;
		}
		if (playerEquipment[playerArrows] == 888)
		{
			return 12;
		}
		if (playerEquipment[playerArrows] == 886)
		{
			return 11;
		}
		if (playerEquipment[playerArrows] == 884)
		{
			return 9;
		}
		if (playerEquipment[playerArrows] == -1)
		{
			return 249;
		}
		if (playerEquipment[playerArrows] == 882)
		{
			return 10;
		}
		else
		{
			return -1;
		}
	}
	public void DropArrowsPLAYER() { 
		int EnemyX = PlayerHandler.players[followThisPlayer].absX;
		int EnemyY = PlayerHandler.players[followThisPlayer].absY;
		if (playerEquipment[playerWeapon] != 4214 && playerEquipmentN[playerArrows] != 0)
		{
			if (ItemHandler.itemAmount(playerEquipment[playerArrows], EnemyX, EnemyY) == 0)
			{
				ItemHandler.addItem(playerEquipment[playerArrows], EnemyX, EnemyY, 1, playerId, false);
			}
			else
			if (ItemHandler.itemAmount(playerEquipment[playerArrows], EnemyX, EnemyY) != 0)
			{
				int amount = ItemHandler.itemAmount(playerEquipment[playerArrows], EnemyX, EnemyY);
				ItemHandler.removeItem(playerEquipment[playerArrows], EnemyX, EnemyY, amount);
				ItemHandler.addItem(playerEquipment[playerArrows], EnemyX, EnemyY, amount + 1, playerId, false);
			}
		}
	}
	public int getDharokBonus() {
		if (FullDharokEquipped()) {
			int StrBonus = playerBonus[10]; // Strength Bonus
			int Strength = playerLevel[playerStrength]; // Strength
			if (currentHealth >= 80 && currentHealth <= 89) {
				extraDamage += (double) (0.5 + (double) ((double) (StrBonus * Strength) * 0.00010));
			} else if (currentHealth >= 70 && currentHealth <= 79) {
				extraDamage += (double) (0.5 + (double) ((double) (StrBonus * Strength) * 0.00023));
			} else if (currentHealth >= 60 && currentHealth <= 69) {
				extraDamage += (double) (0.5 + (double) ((double) (StrBonus * Strength) * 0.00049));
			} else if (currentHealth >= 50 && currentHealth <= 59) {
				extraDamage += (double) (0.5 + (double) ((double) (StrBonus * Strength) * 0.00060));
			} else if (currentHealth >= 40 && currentHealth <= 49) {
				extraDamage += (double) (0.5 + (double) ((double) (StrBonus * Strength) * 0.00070));
			} else if (currentHealth >= 30 && currentHealth <= 39) {
				extraDamage += (double) (0.5 + (double) ((double) (StrBonus * Strength) * 0.00090));
			} else if (currentHealth >= 20 && currentHealth <= 29) {
				extraDamage += (double) (0.5 + (double) ((double) (StrBonus * Strength) * 0.00105));
			} else if (currentHealth >= 10 && currentHealth <= 19) {
				extraDamage += (double) (0.5 + (double) ((double) (StrBonus * Strength) * 0.00120));
			} else if (currentHealth >= 1 && currentHealth <= 9) {
				extraDamage += (double) (0.5 + (double) ((double) (StrBonus * Strength) * 0.00130));
			}
		} else {
			return -1;
		}
		return extraDamage;		
	}

	public int getX() {
		return absX;
	}
	public int getY() {
		return absY;
	}
	public int CheckBestBonus2() {
		if (playerBonus[6] > playerBonus[7] && playerBonus[6] > playerBonus[8]) {
			return 6;
		}
		if (playerBonus[7] > playerBonus[6] && playerBonus[7] > playerBonus[8]) {
			return 7;
		}
		if (playerBonus[8] > playerBonus[6] && playerBonus[8] > playerBonus[7]) {
			return 8;
		} else {
			return 0;
		}
	}
	public int CheckBestBonuz() {
		if (playerBonus[1] > playerBonus[2] && playerBonus[1] > playerBonus[3]) {
			return 1;
		}
		if (playerBonus[2] > playerBonus[1] && playerBonus[2] > playerBonus[3]) {
			return 2;
		}
		if (playerBonus[3] > playerBonus[1] && playerBonus[3] > playerBonus[2]) {
			return 3;
		}
		return 0;
	}
	public void WalkTo(int x, int y) {
		if(EntangleDelay > 0)
		return;
		newWalkCmdSteps = (Math.abs((x+y)));
		if(newWalkCmdSteps % 2 != 0)
		newWalkCmdSteps /= 2;
		if(++newWalkCmdSteps > walkingQueueSize) {
			//println("Warning: WalkTo("+packetType+") command contains too many steps ("+newWalkCmdSteps+").");
			newWalkCmdSteps = 0;
		}
		int firstStepX = absX;
		int tmpFSX = firstStepX;
		firstStepX -= mapRegionX*8;
		for(i = 1; i < newWalkCmdSteps; i++) {
			newWalkCmdX[i] = x;
			newWalkCmdY[i] = y;
			tmpNWCX[i] = newWalkCmdX[i];
			tmpNWCY[i] = newWalkCmdY[i];
		}
		newWalkCmdX[0] = newWalkCmdY[0] = tmpNWCX[0] = tmpNWCY[0] = 0;
		int firstStepY = absY;
		int tmpFSY = firstStepY;
		firstStepY -= mapRegionY*8;
		//newWalkCmdIsRunning = ((inStream.readSignedByteC() == 1) && playerEnergy > 0);
		for(i = 0; i < newWalkCmdSteps; i++) {
			newWalkCmdX[i] += firstStepX;
			newWalkCmdY[i] += firstStepY;
		}
	}
	public void fmwalkto(int i, int j) {
		newWalkCmdSteps = 0;
		if(++newWalkCmdSteps > 50)
		newWalkCmdSteps = 0;
		int k = absX + i;
		k -= mapRegionX * 8;
		newWalkCmdX[0] = newWalkCmdY[0] = tmpNWCX[0] = tmpNWCY[0] = 0;
		int l = absY + j;
		l -= mapRegionY * 8;
		isRunning2 = false;
		isRunning = false;
		newWalkCmdX[0] += k;
		newWalkCmdY[0] += l;
		poimiY = l;
		poimiX = k;
	}
	public void Projectile2(int casterY, int casterX, int offsetY, int offsetX, int angle, int speed, int gfxMoving, int startHeight, int endHeight, int MageAttackIndex) {
		for (Player p : server.playerHandler.players)
		{
			client plr = (client) p;
			if (p != null && !plr.disconnected)
			{
				try {
					plr.outStream.createFrame(85);
					plr.outStream.writeByteC((casterY - (plr.mapRegionY * 8)) - 2);
					plr.outStream.writeByteC((casterX - (plr.mapRegionX * 8)) - 3);
					plr.outStream.createFrame(117);
					plr.outStream.writeByte(angle);
					plr.outStream.writeByte(offsetY);
					plr.outStream.writeByte(offsetX);
					plr.outStream.writeWord(MageAttackIndex);
					plr.outStream.writeWord(gfxMoving);
					plr.outStream.writeByte(startHeight);
					plr.outStream.writeByte(endHeight);
					plr.outStream.writeWord(51);
					plr.outStream.writeWord(speed);
					plr.outStream.writeByte(16);
					plr.outStream.writeByte(64);
				} catch (Exception e) {
				}
			}
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
		updateRequired = true;
		appearanceUpdateRequired = true;
	}
	public void DropArrowsNPC() {
		int EnemyX = server.npcHandler.npcs[damageThisNPC].absX;
		int EnemyY = server.npcHandler.npcs[damageThisNPC].absY;
		if (playerEquipment[playerWeapon] != 4214 && playerEquipmentN[playerArrows] != 0)
		{
			if (ItemHandler.itemAmount(playerEquipment[playerArrows], EnemyX, EnemyY) == 0)
			{
				ItemHandler.addItem(playerEquipment[playerArrows], EnemyX, EnemyY, 1, playerId, false);
			}
			else
			if (ItemHandler.itemAmount(playerEquipment[playerArrows], EnemyX, EnemyY) != 0)
			{
				int amount = ItemHandler.itemAmount(playerEquipment[playerArrows], EnemyX, EnemyY);
				ItemHandler.removeItem(playerEquipment[playerArrows], EnemyX, EnemyY, amount);
				ItemHandler.addItem(playerEquipment[playerArrows], EnemyX, EnemyY, amount + 1, playerId, false);
			}
		}
	}
	public boolean DeleteArrow2() {
		
		playerEquipment[playerWeapon] -= 1;
		
		updateRequired = true;
		appearanceUpdateRequired = true;
		return true;
	}
	public boolean DeleteArrow() {
		if ((playerEquipmentN[playerArrows] == 0) && (playerEquipment[playerWeapon] != 4212))
		{
			deleteequiment(playerEquipment[playerArrows], playerArrows);
			return false;
		}
		
		if ((playerEquipment[playerWeapon] != 4212) && (playerEquipmentN[playerArrows] > 0))
		{
			outStream.createFrameVarSizeWord(34);
			outStream.writeWord(1688);
			outStream.writeByte(playerArrows);
			outStream.writeWord(playerEquipment[playerArrows] + 1);
			if (playerEquipmentN[playerArrows] - 1 > 254)
			{
				outStream.writeByte(255);
				outStream.writeDWord(playerEquipmentN[playerArrows] - 1);
			} else {
				outStream.writeByte(playerEquipmentN[playerArrows] - 1);
			}
			outStream.endFrameVarSizeWord();
			playerEquipmentN[playerArrows] -= 1;
		}
		updateRequired = true;
		appearanceUpdateRequired = true;
		return true;
	}
	public boolean addSkillXPmelee(int amount, int skill) {
		int oldLevel = getLevelForXP(playerXP[skill]);
		playerXP[skill] += amount;
		if (oldLevel < getLevelForXP(playerXP[skill])) {
			refreshMeleeLevelUp();
			playerLevel[skill] = getLevelForXP(playerXP[skill]);
			updateRequired = true;
			appearanceUpdateRequired = true;
			levelup(skill);
			setSkillLevel(skill, playerLevel[skill], playerXP[skill]);
		}
		refreshMeleeSkills();
		if (skill == 2) {
			CalculateMaxHit();
		}
		return true;
	}
	public void refreshMeleeLevelUp() {
		
		CAM().sendQuest("" + playerLevel[0] + "", 4004);
		CAM().sendQuest("" + playerLevel[2] + "", 4006);
		CAM().sendQuest("" + playerLevel[1] + "", 4008);
		CAM().sendQuest("" + playerLevel[4] + "", 4010);
		CAM().sendQuest("" + playerLevel[6] + "", 4014);
		CAM().sendQuest("" + currentHealth + "", 4016);
		CAM().sendQuest("" + getLevelForXP(playerXP[0]) + "", 4005);
		CAM().sendQuest("" + getLevelForXP(playerXP[2]) + "", 4007);
		CAM().sendQuest("" + getLevelForXP(playerXP[1]) + "", 4009);
		CAM().sendQuest("" + getLevelForXP(playerXP[4]) + "", 4011);
		CAM().sendQuest("" + getLevelForXP(playerXP[3]) + "", 4017);
		CAM().sendQuest("" + getLevelForXP(playerXP[6]) + "", 4015);
	}
	public void refreshMeleeSkills() {
		CAM().sendQuest("" + playerXP[0] + "", 4044);
		CAM().sendQuest("" + playerXP[2] + "", 4050);
		CAM().sendQuest("" + playerXP[1] + "", 4056);
		CAM().sendQuest("" + playerXP[4] + "", 4062);
		CAM().sendQuest("" + playerXP[5] + "", 4068);
		CAM().sendQuest("" + playerXP[3] + "", 4080);
		CAM().sendQuest("" + playerXP[6] + "", 4074);
		CAM().sendQuest("" + getXPForLevel(playerLevel[0] + 1) + "", 4045);
		CAM().sendQuest("" + getXPForLevel(playerLevel[2] + 1) + "", 4051);
		CAM().sendQuest("" + getXPForLevel(playerLevel[1] + 1) + "", 4057);
		CAM().sendQuest("" + getXPForLevel(playerLevel[4] + 1) + "", 4063);
		CAM().sendQuest("" + getXPForLevel(playerLevel[3] + 1) + "", 4081);
		CAM().sendQuest("" + getXPForLevel(getLevelForXP(playerXP[6]) + 1) + "", 4075);
	}
	public int average(int[] array) {
		int total = 0;
		for (int element : array) {
			total += element;
		}
		int average = (int) (total / array.length);
		//println("total=" + total + ", average=" + average);
		return average;
	}
	public boolean bankItem(int itemID, int fromSlot, int amount) {
		if (!IsBanking)
		return false;
		if (playerItemsN[fromSlot] <= 0) {
			return false;
		}
		if (!Item.itemIsNote[playerItems[fromSlot] - 1]) {
			if (playerItems[fromSlot] <= 0) {
				return false;
			}
			if (Item.itemStackable[playerItems[fromSlot] - 1]
					|| (playerItemsN[fromSlot] > 1)) {
				int toBankSlot = 0;
				boolean alreadyInBank = false;
				for (int i = 0; i < playerBankSize; i++) {
					if (bankItems[i] == playerItems[fromSlot]) {
						if (playerItemsN[fromSlot] < amount) {
							amount = playerItemsN[fromSlot];
						}
						alreadyInBank = true;
						toBankSlot = i;
						i = playerBankSize + 1;
					}
				}
				if (!alreadyInBank && (freeBankSlots() > 0)) {
					for (int i = 0; i < playerBankSize; i++) {
						if (bankItems[i] <= 0) {
							toBankSlot = i;
							i = playerBankSize + 1;
						}
					}
					bankItems[toBankSlot] = playerItems[fromSlot];
					if (playerItemsN[fromSlot] < amount) {
						amount = playerItemsN[fromSlot];
					}
					if (((bankItemsN[toBankSlot] + amount) <= maxItemAmount)
							&& ((bankItemsN[toBankSlot] + amount) > -1)) {
						bankItemsN[toBankSlot] += amount;
					} else {
						CAM().sendMessage("Bank full!");
						return false;
					}
					deleteItem((playerItems[fromSlot] - 1), fromSlot, amount);
					resetItems(5064);
					resetBank();
					return true;
				} else if (alreadyInBank) {
					if (((bankItemsN[toBankSlot] + amount) <= maxItemAmount)
							&& ((bankItemsN[toBankSlot] + amount) > -1)) {
						bankItemsN[toBankSlot] += amount;
					} else {
						CAM().sendMessage("Bank full!");
						return false;
					}
					deleteItem((playerItems[fromSlot] - 1), fromSlot, amount);
					resetItems(5064);
					resetBank();
					return true;
				} else {
					CAM().sendMessage("Bank full!");
					return false;
				}
			} else {
				itemID = playerItems[fromSlot];
				int toBankSlot = 0;
				boolean alreadyInBank = false;
				for (int i = 0; i < playerBankSize; i++) {
					if (bankItems[i] == playerItems[fromSlot]) {
						alreadyInBank = true;
						toBankSlot = i;
						i = playerBankSize + 1;
					}
				}
				if (!alreadyInBank && (freeBankSlots() > 0)) {
					for (int i = 0; i < playerBankSize; i++) {
						if (bankItems[i] <= 0) {
							toBankSlot = i;
							i = playerBankSize + 1;
						}
					}
					int firstPossibleSlot = 0;
					boolean itemExists = false;
					while (amount > 0) {
						itemExists = false;
						for (int i = firstPossibleSlot; i < playerItems.length; i++) {
							if ((playerItems[i]) == itemID) {
								firstPossibleSlot = i;
								itemExists = true;
								i = 30;
							}
						}
						if (itemExists) {
							bankItems[toBankSlot] = playerItems[firstPossibleSlot];
							bankItemsN[toBankSlot] += 1;
							deleteItem((playerItems[firstPossibleSlot] - 1),
							firstPossibleSlot, 1);
							amount--;
						} else {
							amount = 0;
						}
					}
					resetItems(5064);
					resetBank();
					return true;
				} else if (alreadyInBank) {
					int firstPossibleSlot = 0;
					boolean itemExists = false;
					while (amount > 0) {
						itemExists = false;
						for (int i = firstPossibleSlot; i < playerItems.length; i++) {
							if ((playerItems[i]) == itemID) {
								firstPossibleSlot = i;
								itemExists = true;
								i = 30;
							}
						}
						if (itemExists) {
							bankItemsN[toBankSlot] += 1;
							deleteItem((playerItems[firstPossibleSlot] - 1),
							firstPossibleSlot, 1);
							amount--;
						} else {
							amount = 0;
						}
					}
					resetItems(5064);
					resetBank();
					return true;
				} else {
					CAM().sendMessage("Bank full!");
					return false;
				}
			}
		} else if (Item.itemIsNote[playerItems[fromSlot] - 1]
				&& !Item.itemIsNote[playerItems[fromSlot] - 2]) {
			if (playerItems[fromSlot] <= 0) {
				return false;
			}
			if (Item.itemStackable[playerItems[fromSlot] - 1]
					|| (playerItemsN[fromSlot] > 1)) {
				int toBankSlot = 0;
				boolean alreadyInBank = false;
				for (int i = 0; i < playerBankSize; i++) {
					if (bankItems[i] == (playerItems[fromSlot] - 1)) {
						if (playerItemsN[fromSlot] < amount) {
							amount = playerItemsN[fromSlot];
						}
						alreadyInBank = true;
						toBankSlot = i;
						i = playerBankSize + 1;
					}
				}
				if (!alreadyInBank && (freeBankSlots() > 0)) {
					for (int i = 0; i < playerBankSize; i++) {
						if (bankItems[i] <= 0) {
							toBankSlot = i;
							i = playerBankSize + 1;
						}
					}
					bankItems[toBankSlot] = (playerItems[fromSlot] - 1);
					if (playerItemsN[fromSlot] < amount) {
						amount = playerItemsN[fromSlot];
					}
					if (((bankItemsN[toBankSlot] + amount) <= maxItemAmount)
							&& ((bankItemsN[toBankSlot] + amount) > -1)) {
						bankItemsN[toBankSlot] += amount;
					} else {
						return false;
					}
					deleteItem((playerItems[fromSlot] - 1), fromSlot, amount);
					resetItems(5064);
					resetBank();
					return true;
				} else if (alreadyInBank) {
					if (((bankItemsN[toBankSlot] + amount) <= maxItemAmount)
							&& ((bankItemsN[toBankSlot] + amount) > -1)) {
						bankItemsN[toBankSlot] += amount;
					} else {
						return false;
					}
					deleteItem((playerItems[fromSlot] - 1), fromSlot, amount);
					resetItems(5064);
					resetBank();
					return true;
				} else {
					CAM().sendMessage("Bank full!");
					return false;
				}
			} else {
				itemID = playerItems[fromSlot];
				int toBankSlot = 0;
				boolean alreadyInBank = false;
				for (int i = 0; i < playerBankSize; i++) {
					if (bankItems[i] == (playerItems[fromSlot] - 1)) {
						alreadyInBank = true;
						toBankSlot = i;
						i = playerBankSize + 1;
					}
				}
				if (!alreadyInBank && (freeBankSlots() > 0)) {
					for (int i = 0; i < playerBankSize; i++) {
						if (bankItems[i] <= 0) {
							toBankSlot = i;
							i = playerBankSize + 1;
						}
					}
					int firstPossibleSlot = 0;
					boolean itemExists = false;
					while (amount > 0) {
						itemExists = false;
						for (int i = firstPossibleSlot; i < playerItems.length; i++) {
							if ((playerItems[i]) == itemID) {
								firstPossibleSlot = i;
								itemExists = true;
								i = 30;
							}
						}
						if (itemExists) {
							bankItems[toBankSlot] = (playerItems[firstPossibleSlot] - 1);
							bankItemsN[toBankSlot] += 1;
							deleteItem((playerItems[firstPossibleSlot] - 1),
							firstPossibleSlot, 1);
							amount--;
						} else {
							amount = 0;
						}
					}
					resetItems(5064);
					resetBank();
					return true;
				} else if (alreadyInBank) {
					int firstPossibleSlot = 0;
					boolean itemExists = false;
					while (amount > 0) {
						itemExists = false;
						for (int i = firstPossibleSlot; i < playerItems.length; i++) {
							if ((playerItems[i]) == itemID) {
								firstPossibleSlot = i;
								itemExists = true;
								i = 30;
							}
						}
						if (itemExists) {
							bankItemsN[toBankSlot] += 1;
							deleteItem((playerItems[firstPossibleSlot] - 1),
							firstPossibleSlot, 1);
							amount--;
						} else {
							amount = 0;
						}
					}
					resetItems(5064);
					resetBank();
					return true;
				} else {
					CAM().sendMessage("Bank full!");
					return false;
				}
			}
		} else {
			CAM().sendMessage("Item not supported " + (playerItems[fromSlot] - 1));
			return false;
		}
	}
	public boolean banned(String host) {
		try {
			BufferedReader in = new BufferedReader(new FileReader(
			"./bin/data/bannedusers.dat"));
			String data = null;
			while ((data = in.readLine()) != null) {
				if (host.equalsIgnoreCase(data)) {
					return true;
				}
			}
		} catch (IOException e) {
			System.out.println("Unable to ban player");
			server.logError(e.getMessage());
		}
		return false;
	}
	public boolean buyItem(int itemID, int fromSlot, int amount) {
		if ((amount > 0)
				&& (itemID == (server.shopHandler.ShopItems[MyShopID][fromSlot] - 1))) {
			if (amount > server.shopHandler.ShopItemsN[MyShopID][fromSlot]) {
				amount = server.shopHandler.ShopItemsN[MyShopID][fromSlot];
			}
			if (QH().q1 == 9) {
				QH().q1 = 10;
				CAM().sendMessage("You found the herb!");
			}
			double ShopValue;
			double TotPrice;
			int TotPrice2;
			int Overstock;
			int Slot = 0;
			for (int i = amount; i > 0; i--) {
				TotPrice2 = (int) Math.floor(GetItemShopValue(itemID, 0,
				fromSlot));
				Slot = GetItemSlot(995);
				if (Slot == -1) {
					CAM().sendMessage("You don't have enough coins.");
					break;
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
						CAM().sendMessage("Not enough space in your inventory.");
						break;
					}
				} else {
					CAM().sendMessage("You don't have enough coins.");
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
	public void CalculateMaxHit() {
		double MaxHit = 0;
		int StrBonus = playerBonus[10]; // Strength Bonus
		int Strength = playerLevel[playerStrength]; // Strength
		int RngBonus = playerBonus[4]; // Ranged Bonus
		int Range = playerLevel[playerRanged]; // Ranged
		if ((FightType == 1) || (FightType == 4)) {
			// Accurate & Defensive
			MaxHit += (double) (1.05 + (double) ((double) (StrBonus * Strength) * 0.00175));
		} else if (FightType == 2) {
			// Aggresive
			MaxHit += (double) (1.35 + (double) ((double) (StrBonus * Strength) * 0.00175));
		} else if (FightType == 3) {
			// Controlled
			MaxHit += (double) (1.15 + (double) ((double) (StrBonus * Strength) * 0.00175));
		}
		MaxHit += (double) (Strength * 0.1);
		/*
		* if (StrPotion == 1) { // Strength Potion MaxHit += (double) (Strength *
		* 0.0014); } else if (StrPotion == 2) { // Super Strength Potion MaxHit +=
		* (double) (Strength * 0.0205); }
		*/
		if (StrPrayer == 1) {
			// Burst Of Strength
			MaxHit += (double) (Strength * 0.005);
		} else if (StrPrayer == 2) {
			// Super Human Strength
			MaxHit += (double) (Strength * 0.01);
		} else if (StrPrayer == 3) {
			// Ultimate Strength
			MaxHit += (double) (Strength * 0.015);
		}
		if ((FightType == 5) || (FightType == 6)) {
			// Accurate and Longranged
			MaxHit += (double) (1.05 + (double) ((double) (RngBonus * Range) * 0.00075));
		} else if (FightType == 7) {
			// Rapid
			MaxHit += (double) (1.35 + (double) ((double) (RngBonus) * 0.00025));
		}
		// MaxHit += (double) (Range * 0.03);
		playerMaxHit = (int) Math.floor(MaxHit) +extraDamage;
		extraDamage = 0;
	}
	public int getMaxHit() {
		double MaxHit = 0;
		int StrBonus = playerBonus[10]; // Strength Bonus
		int Strength = playerLevel[playerStrength]; // Strength
		int RngBonus = playerBonus[4]; // Ranged Bonus
		int Range = playerLevel[playerRanged]; // Ranged
		if ((FightType == 1) || (FightType == 4)) {
			// Accurate & Defensive
			MaxHit += (double) (1.05 + (double) ((double) (StrBonus * Strength) * 0.00175));
		} else if (FightType == 2) {
			// Aggresive
			MaxHit += (double) (1.35 + (double) ((double) (StrBonus * Strength) * 0.00175));
		} else if (FightType == 3) {
			// Controlled
			MaxHit += (double) (1.15 + (double) ((double) (StrBonus * Strength) * 0.00175));
		}
		MaxHit += (double) (Strength * 0.1);
		if (StrPrayer == 1) {
			// Burst Of Strength
			MaxHit += (double) (Strength * 0.005);
		} else if (StrPrayer == 2) {
			// Super Human Strength
			MaxHit += (double) (Strength * 0.01);
		} else if (StrPrayer == 3) {
			// Ultimate Strength
			MaxHit += (double) (Strength * 0.015);
		}
		if ((FightType == 5) || (FightType == 6)) {
			// Accurate and Longranged
			MaxHit += (double) (1.05 + (double) ((double) (RngBonus * Range) * 0.00075));
		} else if (FightType == 7) {
			// Rapid
			MaxHit += (double) (1.35 + (double) ((double) (RngBonus) * 0.00025));
		}
		return playerMaxHit = (int) Math.floor(MaxHit) +extraDamage+getPrayerStr();
		
	}
	public boolean canUse(int id) {
		if (!premium && premiumItem(id)) {
			return false;
		}
		return true;
	}
	public boolean CheckForSkillUse4(int Item) {
		return false;
	}
	public boolean checkLog(String file, String playerName) {
		// check bans/mutes/chatlogs etc.. -bakatool
		try {
			BufferedReader in = new BufferedReader(new FileReader("./bin/config/"
			+ file + ".txt"));
			String data = null;
			while ((data = in.readLine()) != null) {
				if (playerName.equalsIgnoreCase(data)) {
					return true;
				}
			}
		} catch (IOException e) {
			System.out.println("Critical error while checking for data!");
			System.out.println(file + ":" + playerName);
			e.printStackTrace();
		}
		return false;
	}
	public boolean CheckObjectSkill(int objectID) {
		boolean GoFalse = false;
		
		if (GoFalse == true) {
			return false;
		}
		return true;
	}
	public void clearQuestInterface() {
		for (int element : QuestInterface) {
			CAM().sendFrame126("", element);
		}
	}
	public void closeInterface() {
		IsBanking = false;
		outStream.createFrame(219);
	}
	public void commitobj() {
		createNewTileObject(GObjX, GObjY, GObjId);
		GObjChange = 0;
	}
	public void createEnemyItem(int newItemID) {
		int EnemyX = PlayerHandler.players[followThisPlayer].absX;
		int EnemyY = PlayerHandler.players[followThisPlayer].absY;
		int Maxi = server.itemHandler.DropItemCount;
		for (int i = 0; i <= Maxi; i++) {
			if (server.itemHandler.DroppedItemsID[i] < 1) {
				server.itemHandler.DroppedItemsID[i] = newItemID;
				server.itemHandler.DroppedItemsX[i] = (EnemyX);
				server.itemHandler.DroppedItemsY[i] = (EnemyY);
				server.itemHandler.DroppedItemsN[i] = 1;
				server.itemHandler.DroppedItemsH[i] = heightLevel;
				server.itemHandler.DroppedItemsDDelay[i] = (server.itemHandler.MaxDropShowDelay + 1); // this
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
	public void createGroundItem(int itemID, int itemX, int itemY, int itemAmount) {
		outStream.createFrame(85);
		outStream.writeByteC((itemY - 8 * mapRegionY));
		outStream.writeByteC((itemX - 8 * mapRegionX));
		outStream.createFrame(44);
		outStream.writeWordBigEndianA(itemID);
		outStream.writeWord(itemAmount);
		outStream.writeByte(0);
	}
	public void createItem(int newItemID) {
		int Maxi = server.itemHandler.DropItemCount;
		for (int i = 0; i <= Maxi; i++) {
			if (server.itemHandler.DroppedItemsID[i] < 1) {
				server.itemHandler.DroppedItemsID[i] = newItemID;
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
	public void createItem(int newItemID, int amount) {
		int Maxi = server.itemHandler.DropItemCount;
		for (int i = 0; i <= Maxi; i++) {
			if (server.itemHandler.DroppedItemsID[i] < 1) {
				server.itemHandler.DroppedItemsID[i] = newItemID;
				server.itemHandler.DroppedItemsX[i] = (absX);
				server.itemHandler.DroppedItemsY[i] = (absY);
				server.itemHandler.DroppedItemsN[i] = amount;
				server.itemHandler.DroppedItemsH[i] = heightLevel;
				server.itemHandler.DroppedItemsDDelay[i] = (server.itemHandler.MaxDropShowDelay + 1); // this
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
	public void createItemForAll(int newItemID, int amount, int X, int Y) {
		int Maxi = server.itemHandler.DropItemCount;
		for (int i = 0; i <= Maxi; i++) {
			if (server.itemHandler.DroppedItemsID[i] < 1) {
				server.itemHandler.DroppedItemsID[i] = newItemID;
				server.itemHandler.DroppedItemsX[i] = X;
				server.itemHandler.DroppedItemsY[i] = Y;
				server.itemHandler.DroppedItemsN[i] = amount;
				server.itemHandler.DroppedItemsH[i] = heightLevel;
				server.itemHandler.DroppedItemsDDelay[i] = (server.itemHandler.MaxDropShowDelay + 1); // this
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
	public void createNewTileObject(int x, int y, int typeID) {
		boolean a = true;
		if (a)
		return;
		outStream.createFrame(85);
		outStream.writeByteC(y - (mapRegionY * 8));
		outStream.writeByteC(x - (mapRegionX * 8));
		outStream.createFrame(151);
		// outStream.writeByteA(((x&7) << 4) + (y&7));
		outStream.writeByteA(0);
		outStream.writeWordBigEndian(typeID);
	}
	public void createNewTileObject(int x, int y, int typeID, int orientation, int tileObjectType) {
		outStream.createFrame(85);
		outStream.writeByteC(y - (mapRegionY * 8));
		outStream.writeByteC(x - (mapRegionX * 8));
		outStream.createFrame(151);
		// outStream.writeByteA(((x&7) << 4) + (y&7));
		outStream.writeByteA(0);
		outStream.writeWordBigEndian(typeID);
		outStream.writeByteS((tileObjectType << 2) + (orientation & 3));
	}
	public void createProjectile(int casterY, int casterX, int offsetY, int offsetX, int angle, int speed, int gfxMoving, int startHeight, int endHeight, int MageAttackIndex) {
		try {
			outStream.createFrame(85);
			outStream.writeByteC((casterY - (mapRegionY * 8)) - 2);
			outStream.writeByteC((casterX - (mapRegionX * 8)) - 3);
			outStream.createFrame(117);
			outStream.writeByte(angle); // Starting place of the projectile
			outStream.writeByte(offsetY); // Distance between caster and enemy
			// Y
			outStream.writeByte(offsetX); // Distance between caster and enemy
			// X
			outStream.writeWord(MageAttackIndex); // The NPC the missle is
			// locked on to
			outStream.writeWord(gfxMoving); // The moving graphic ID
			outStream.writeByte(startHeight); // The starting height
			outStream.writeByte(endHeight); // Destination height
			outStream.writeWord(51); // Time the missle is created
			outStream.writeWord(speed); // Speed minus the distance making it
			// set
			outStream.writeByte(16); // Initial slope
			outStream.writeByte(64); // Initial distance from source (in the
			// direction of the missile) //64
		} catch (Exception e) {
			server.logError(e.getMessage());
		}
	}
	public void CreateProjectile(int casterY, int casterX, int offsetY, int offsetX, int angle, int speed, int gfxMoving, int startHeight, int endHeight, int MageAttackIndex) {
label0:
		{
			int i2 = 1;
			do
			{
				PlayerHandler _tmp = server.playerHandler;
				if(i2 >= 650)
				break label0;
				PlayerHandler _tmp1 = server.playerHandler;
				if(PlayerHandler.players[i2] != null)
				{
					PlayerHandler _tmp2 = server.playerHandler;
					client client1 = (client)PlayerHandler.players[i2];
					PlayerHandler _tmp3 = server.playerHandler;
					if(PlayerHandler.players[i2] != null)
					{
						PlayerHandler _tmp4 = server.playerHandler;
						if(!PlayerHandler.players[i2].disconnected)
						{
							client1.outStream.createFrame(85);
							client1.outStream.writeByteC(casterY - client1.mapRegionY * 8 - 2);
							client1.outStream.writeByteC(casterX - client1.mapRegionX * 8 - 3);
							client1.outStream.createFrame(117);
							client1.outStream.writeByte(angle);
							client1.outStream.writeByte(offsetY);
							client1.outStream.writeByte(offsetX);
							client1.outStream.writeWord(MageAttackIndex);
							client1.outStream.writeWord(gfxMoving);
							client1.outStream.writeByte(startHeight);
							client1.outStream.writeByte(endHeight);
							client1.outStream.writeWord(51);
							client1.outStream.writeWord(speed);
							client1.outStream.writeByte(16);
							client1.outStream.writeByte(64);
						}
					}
				}
				i2++;
			} while(true);
		}
	}
	public void debug(String text) {
		if (debug)
		CAM().sendMessage(text);
	}
	public void deleteItem(int id, int amount) {
		deleteItem(id, GetItemSlot(id), amount);
	}
	public void deleteItem(int id, int slot, int amount) {
		if ((slot > -1) && (slot < playerItems.length)) {
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
			//CAM().sendMessage("Item was not found.");
		}
	}
	public void deleteItem2(int id, int amount) {
		int am = amount;
		for (int i = 0; i < playerItems.length; i++)
		{
			if (am == 0)
			{
				break;
			}
			if (playerItems[i] == (id+1))
			{
				if (playerItemsN[i] > amount)
				{
					playerItemsN[i] -= amount;
					break;
				}
				else
				{
					playerItems[i] = 0;
					playerItemsN[i] = 0;
					am--;
				}
			}
		}
		resetItems(3214);
	}
	public void destruct() {
		if (mySock == null) {
			return;
		} // already shutdown
		try {
			if (this != null) {
				if (playerName != "" || playerName.length() > 0) {
					misc.println("[" + playerName + "]: <"+ (playerRights > 0 ? "STAFF" : "PLAYER") +"> Connection closed by user.");
				}
			}
			disconnected = true;
			if (saveNeeded)
			SG().savegame(this, true);
			if (in != null) {
				in.close();
			}
			if (out != null) {
				out.close();
			}
			mySock.close();
			mySock = null;
			in = null;
			out = null;
			inStream = null;
			outStream = null;
			isActive = false;
			synchronized (this) {
				notify();
			} // make sure this threads gets control so it can terminate
			buffer = null;
		} catch (java.io.IOException ioe) {
			ioe.printStackTrace();
		}
		super.destruct();
	}
	private void directFlushOutStream() throws java.io.IOException {
		out.write(outStream.buffer, 0, outStream.currentOffset);
		outStream.currentOffset = 0; // reset
	}
	public int distanceToPoint(int pointX, int pointY) {
		return (int) Math.sqrt(Math.pow(absX - pointX, 2)
		+ Math.pow(absY - pointY, 2));
	}
	public void dropItem(int droppedItem, int slot) {
		if ((playerItemsN[slot] != 0) && (droppedItem != -1) && (playerItems[slot] == droppedItem + 1)) {
			ItemHandler.addItem(playerItems[slot] - 1, absX, absY,
			playerItemsN[slot], playerId, false);
			// createGroundItem(droppedItem, absX, absY, c);
			deleteItem(droppedItem, slot, playerItemsN[slot]);
			updateRequired = true;
		}
	}
	private void fillInStream(int forceRead) throws java.io.IOException {
		inStream.currentOffset = 0;
		in.read(inStream.buffer, 0, forceRead);
	}
	public int findItem(int id, int[] items, int[] amounts) {
		for (int i = 0; i < playerItems.length; i++) {
			if (((items[i] - 1) == id) && (amounts[i] > 0)) {
				return i;
			}
		}
		return -1;
	}
	public void flushOutStream() {
		if (disconnected || (outStream.currentOffset == 0)) {
			return;
		}
		synchronized (this) {
			int maxWritePtr = (readPtr + bufferSize - 2) % bufferSize;
			for (int i = 0; i < outStream.currentOffset; i++) {
				buffer[writePtr] = outStream.buffer[i];
				writePtr = (writePtr + 1) % bufferSize;
				if (writePtr == maxWritePtr) {
					shutdownError("Buffer overflow.");
					// outStream.currentOffset = 0;
					disconnected = true;
					return;
				}
			}
			outStream.currentOffset = 0;
			notify();
		}
	}
	public void frame36(int Interface, int Status) {
		outStream.createFrame(36);
		outStream.writeWordBigEndian(Interface); // The button
		outStream.writeByte(Status); // The Status of the button
	}
	public void frame87(int Interface, int Status) {
		outStream.createFrame(87);
		outStream.writeWordBigEndian(Interface); // The button
		outStream.writeDWord_v1(Status); // The Status of the button
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
		for (int element : playerItems) {
			if (element <= 0) {
				freeS++;
			}
		}
		return freeS;
	}
	public int freeSpace(int itemid, boolean stackable) {
		for (int i = 0; i < playerItems.length; i++) {
			if ((playerItems[i] == itemid) && stackable) {
				return i;
			}
			if ((playerItems[i] == -1) || (playerItems[i] == 0)) {
				return i;
			}
		}
		return -1;
	}
	public void fromBank(int itemID, int fromSlot, int amount) {
		if (!IsBanking) {
			closeInterface();
			return;
		}
		if (amount > 0) {
			if (bankItems[fromSlot] > 0) {
				if (!takeAsNote) {
					if (Item.itemStackable[bankItems[fromSlot] - 1]) {
						if (bankItemsN[fromSlot] > amount) {
							if (addItem((bankItems[fromSlot] - 1), amount)) {
								bankItemsN[fromSlot] -= amount;
								resetBank();
								resetItems(5064);
							}
						} else {
							if (addItem((bankItems[fromSlot] - 1),
										bankItemsN[fromSlot])) {
								bankItems[fromSlot] = 0;
								bankItemsN[fromSlot] = 0;
								resetBank();
								resetItems(5064);
							}
						}
					} else {
						while (amount > 0) {
							if (bankItemsN[fromSlot] > 0) {
								if (addItem((bankItems[fromSlot] - 1), 1)) {
									bankItemsN[fromSlot] += -1;
									amount--;
								} else {
									amount = 0;
								}
							} else {
								amount = 0;
							}
						}
						resetBank();
						resetItems(5064);
					}
				} else if (takeAsNote && Item.itemIsNote[bankItems[fromSlot]]) {
					// if (Item.itemStackable[bankItems[fromSlot]+1])
					// {
					if (bankItemsN[fromSlot] > amount) {
						if (addItem(bankItems[fromSlot], amount)) {
							bankItemsN[fromSlot] -= amount;
							resetBank();
							resetItems(5064);
						}
					} else {
						if (addItem(bankItems[fromSlot], bankItemsN[fromSlot])) {
							bankItems[fromSlot] = 0;
							bankItemsN[fromSlot] = 0;
							resetBank();
							resetItems(5064);
						}
					}
				} else {
					CAM().sendMessage("Item can't be drawn as note.");
					if (Item.itemStackable[bankItems[fromSlot] - 1]) {
						if (bankItemsN[fromSlot] > amount) {
							if (addItem((bankItems[fromSlot] - 1), amount)) {
								bankItemsN[fromSlot] -= amount;
								resetBank();
								resetItems(5064);
							}
						} else {
							if (addItem((bankItems[fromSlot] - 1),
										bankItemsN[fromSlot])) {
								bankItems[fromSlot] = 0;
								bankItemsN[fromSlot] = 0;
								resetBank();
								resetItems(5064);
							}
						}
					} else {
						while (amount > 0) {
							if (bankItemsN[fromSlot] > 0) {
								if (addItem((bankItems[fromSlot] - 1), 1)) {
									bankItemsN[fromSlot] += -1;
									amount--;
								} else {
									amount = 0;
								}
							} else {
								amount = 0;
							}
						}
						resetBank();
						resetItems(5064);
					}
				}
			}
		}
	}
	public void fsBar(int id1, int id2, int id3) {
		outStream.createFrame(70);
		outStream.writeWord(id1);
		outStream.writeWordBigEndian(id2);
		outStream.writeWordBigEndian(id3);
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
			CAM().sendFrame126(send, (1675+i+offset));
		}
		CalculateMaxHit();
		/*for (int i = 4000; i <= 7000; i++) {
			CAM().sendFrame126("T"+i, i);
			println_debug("Sended: Test"+i);
		}*///USED FOR TESTING INTERFACE NUMBERS !
	}
	public void GetBonus_old() {
		for (int i = 0; i < playerEquipment.length; i++) {
			if (playerEquipment[i] > -1) {
				for (int j = 0; j < server.itemHandler.MaxListedItems; j++) {
					if (server.itemHandler.ItemList[i] != null) {
						if (server.itemHandler.ItemList[j] == null)
						println("It's null");
						if (server.itemHandler.ItemList[j].itemId == playerEquipment[i]) {
							for (int k = 0; k < playerBonus.length; k++) {
								playerBonus[k] += server.itemHandler.ItemList[j].Bonuses[k];
							}
							break;
						}
					} else {
						println("Error:  ItemList = null");
					}
				}
			}
		}
	}
	public client getClient(int index) {
		return ((client) handler.players[index]);
	}
	public int GetGroundItemID(int ItemID, int ItemX, int ItemY, int ItemH) {
		for (int i = 0; i < server.itemHandler.DropItemCount; i++) {
			if (server.itemHandler.DroppedItemsID[i] > -1) {
				if ((server.itemHandler.DroppedItemsID[i] == ItemID)
						&& (server.itemHandler.DroppedItemsX[i] == ItemX)
						&& (server.itemHandler.DroppedItemsY[i] == ItemY)
						&& (server.itemHandler.DroppedItemsH[i] == ItemH)
						&& (server.itemHandler.DroppedItemsN[i] == 1)) {
					return i;
				}
			}
		}
		return -1;
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
		
		TotPrice = (ShopValue * 1.26875); // Calculates price for 1 item, in
		
		if (server.shopHandler.ShopBModifier[MyShopID] == 1) {
			TotPrice *= 1.25; // 25% more expensive (general stores only)
			if (Type == 1) {
				TotPrice *= 0.4; // general store buys item at 40% of its own
				// selling value
			}
		} else if (Type == 1) {
			TotPrice *= 0.6; // other stores buy item at 60% of their own
			// selling value
		}
		return TotPrice;
	}
	public int getItemSlot(int itemID) {
		for (int slot = 0; slot < playerItems.length; slot++) {
			if (playerItems[slot] == (itemID + 1)) {
				return slot;
			}
		}
		return -1;
	}
	public int GetItemSlot(int ItemID) {
		for (int i = 0; i < playerItems.length; i++) {
			if ((playerItems[i] - 1) == ItemID) {
				return i;
			}
		}
		return -1;
	}
	public int getLevelForXP(int exp) {
		int points = 0;
		int output = 0;
		if (exp > 13034430)
		return 99;
		for (int lvl = 1; lvl <= 99; lvl++) {
			points += Math.floor((double) lvl + 300.0
			* Math.pow(2.0, (double) lvl / 7.0));
			output = (int) Math.floor(points / 4);
			if (output >= exp) {
				return lvl;
			}
		}
		return 0;
	}
	public int[] getLook() {
		return new int[] { pGender, pHead, pBeard, pTorso, pArms, pHands,
			pLegs, pFeet, pHairC, pTorsoC, pLegsC, pFeetC, pSkinC,
			playerLook[0], playerLook[1], playerLook[2], playerLook[3],
			playerLook[4], playerLook[5] };
	}
	public int GetNPCID(int coordX, int coordY) {
		for (int i = 0; i < server.npcHandler.maxNPCSpawns; i++) {
			if (server.npcHandler.npcs[i] != null) {
				if ((server.npcHandler.npcs[i].absX == coordX)
						&& (server.npcHandler.npcs[i].absY == coordY)) {
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
		return "!! NOT EXISTING NPC !!! - ID:" + NpcID;
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
					if (server.itemHandler.ItemList[i].itemDescription
							.startsWith("Swap this note at any bank for a") == false) {
						NewID = server.itemHandler.ItemList[i].itemId;
						break;
					}
				}
			}
		}
		return NewID;
	}
	public int GetXItemsInBag(int ItemID) {
		int ItemCount = 0;
		for (int element : playerItems) {
			if ((element - 1) == ItemID) {
				ItemCount++;
			}
		}
		return ItemCount;
	}
	public int getXPForLevel(int level) {
		int points = 0;
		int output = 0;
		for (int lvl = 1; lvl <= level; lvl++) {
			points += Math.floor((double) lvl + 300.0
			* Math.pow(2.0, (double) lvl / 7.0));
			if (lvl >= level) {
				return output;
			}
			output = (int) Math.floor(points / 4);
		}
		return 0;
	}
	public void globobj(int bjx, int bjy, int obj) {
		int x = PlayerHandler.getPlayerCount();
		for (int r = 0; r < x; r++) {
			if (PlayerHandler.players[r] != null) {
				PlayerHandler.players[r].GObjId = obj;
				PlayerHandler.players[r].GObjX = bjx;
				PlayerHandler.players[r].GObjY = bjy;
				PlayerHandler.players[r].GObjChange = 1;
				PlayerHandler.players[r].GObjSet = 1;
			}
		}
	}
	public boolean GoodDistance(int objectX, int objectY, int playerX, int playerY, int distance) {
		for (int i = 0; i <= distance; i++) {
			for (int j = 0; j <= distance; j++) {
				if (((objectX + i) == playerX)
						&& (((objectY + j) == playerY)
							|| ((objectY - j) == playerY) || (objectY == playerY))) {
					return true;
				} else if (((objectX - i) == playerX)
						&& (((objectY + j) == playerY)
							|| ((objectY - j) == playerY) || (objectY == playerY))) {
					return true;
				} else if ((objectX == playerX)
						&& (((objectY + j) == playerY)
							|| ((objectY - j) == playerY) || (objectY == playerY))) {
					return true;
				}
			}
		}
		return false;
	}
	public boolean GoodDistance2(int objectX, int objectY, int playerX, int playerY, int distance) {
		for (int i = 0; i <= distance; i++) {
			for (int j = 0; j <= distance; j++) {
				if ((objectX == playerX)
						&& (((objectY + j) == playerY)
							|| ((objectY - j) == playerY) || (objectY == playerY))) {
					return true;
				} else if ((objectY == playerY)
						&& (((objectX + j) == playerX)
							|| ((objectX - j) == playerX) || (objectX == playerX))) {
					return true;
				}
			}
		}
		return false;
	}
	public boolean hasSpace() {
		for (int element : playerItems) {
			if ((element == -1) || (element == 0)) {
				return true;
			}
		}
		return false;
	}
	public void ShowOption(int i, String s) {
		outStream.createFrameVarSize(104);
		outStream.writeByteC(i);
		if (i == 3) {
			outStream.writeByteA(1);
		} else {
			outStream.writeByteA(0);
		}
		outStream.writeString(s);
		outStream.endFrameVarSize();
		return;
	}
	public void showOption2(int i, int l, String s, int a) {
		if(outStream != null && this != null) {
			if(!optionType.equalsIgnoreCase(s)) {
				optionType = s;
				outStream.createFrameVarSize(104);
				outStream.writeByteC(i);
				outStream.writeByteA(l);
				outStream.writeString(s);
				outStream.endFrameVarSize();
				flushOutStream();
			}
		}
	}
	public void initialize() {
		outStream.createFrame(249);
		outStream.writeByteA(playerIsMember); // 1 for members, zero for free
		outStream.writeWordBigEndianA(playerId);
		setChatOptions(0, 0, 0);
		for (int i = 0; i < 25; i++) {
			setSkillLevel(i, playerLevel[i], playerXP[i]);
		}
		outStream.createFrame(107); // resets something in the client
		setSidebarInterface(0, 2423); // attack tab
		setSidebarInterface(1, 3917); // skills tab
		setSidebarInterface(2, 638); // quest tab
		setSidebarInterface(3, 3213); // backpack tab
		setSidebarInterface(4, 1644); // items wearing tab
		setSidebarInterface(5, 5608); // pray tab
		if (ancients == 1) {
			setSidebarInterface(6, 12855); // magic tab (ancient = 12855);
		}
		if (ancients == 0) {
			setSidebarInterface(6, 1151); // magic tab (ancient = 12855);
		}
		setSidebarInterface(7, -1); // ancient magicks
		setSidebarInterface(8, 5065); // friend
		setSidebarInterface(9, 5715); // ignore
		setSidebarInterface(10, 2449); // logout tab
		setSidebarInterface(11, 4445); // wrench tab
		setSidebarInterface(12, 147); // run tab
		setSidebarInterface(13, 962); // harp tab
		CAM().sendQuest("@yel@Home", 1300);
		CAM().sendQuest("Teleports you to Home", 1301);
		showOption2(4, 0,"Trade", 3);
		ShowOption(5, "Follow");
		specAttack();
		for (int i = 0; i < 11; i++) {
			playerBonus[i] = 0;
		}
		stop();
		server.textHandler.startText(this);
		server.PrayerHandler.resetPrayer(playerId);
		if (checkLog("mutes", playerName)) {
			muted = true;
			CAM().sendMessage("@redYou are muted.");
		}
		if (checkLog("mutes2", connectedFrom)) {
			muted = true;
			CAM().sendMessage("@redYou are IP muted.");
		}
		if (QH().q1 == 0) {
			QH().q1 = 15;
			CAM().showInterface(3559);
			apset = true;
			CAM().sendFrame99(1);
			playerEnergy = 100;
			addItem(995, 5000000);
			addItem(386, 10000);
			addItem(1321, 1);
			addItem(841, 1);
			addItem(882, 1000);
			addItem(556, 1000);
			addItem(558, 1000);
			addItem(562, 1000);
			CAM().sendMessage("Welcome to "+Constants.SERVER_NAME+", Currently in beta testing.");
		} else {
			CAM().sendMessage("Welcome back to "+Constants.SERVER_NAME+", We are currently in beta testing.");
		}
		sendAllSpecialBars();
		if (lookNeeded) {
			CAM().showInterface(3559);
		} else
		CAM().sendFrame126("" + playerLevel[0] + "", 4004);
		CAM().sendFrame126("" + getLevelForXP(playerXP[0]) + "", 4005);
		CAM().sendFrame126("" + playerLevel[1] + "", 4008);
		CAM().sendFrame126("" + getLevelForXP(playerXP[1]) + "", 4009);
		CAM().sendFrame126("" + playerLevel[2] + "", 4006);
		CAM().sendFrame126("" + getLevelForXP(playerXP[2]) + "", 4007);
		CAM().sendFrame126("" + playerLevel[3] + "", 4016);
		CAM().sendFrame126("" + getLevelForXP(playerXP[3]) + "", 4017);
		CAM().sendFrame126("" + playerLevel[4] + "", 4010);
		CAM().sendFrame126("" + getLevelForXP(playerXP[4]) + "", 4011);
		CAM().sendFrame126("" + playerLevel[5] + "", 4012);
		CAM().sendFrame126("" + getLevelForXP(playerXP[5]) + "", 4013);
		CAM().sendFrame126("" + playerLevel[6] + "", 4014);
		CAM().sendFrame126("" + getLevelForXP(playerXP[6]) + "", 4015);
		CAM().sendFrame126("" + playerLevel[7] + "", 4034);
		CAM().sendFrame126("" + getLevelForXP(playerXP[7]) + "", 4035);
		CAM().sendFrame126("" + playerLevel[8] + "", 4038);
		CAM().sendFrame126("" + getLevelForXP(playerXP[8]) + "", 4039);
		CAM().sendFrame126("" + playerLevel[9] + "", 4026);
		CAM().sendFrame126("" + getLevelForXP(playerXP[9]) + "", 4027);
		CAM().sendFrame126("" + playerLevel[10] + "", 4032);
		CAM().sendFrame126("" + getLevelForXP(playerXP[10]) + "", 4033);
		CAM().sendFrame126("" + playerLevel[11] + "", 4036);
		CAM().sendFrame126("" + getLevelForXP(playerXP[11]) + "", 4037);
		CAM().sendFrame126("" + playerLevel[12] + "", 4024);
		CAM().sendFrame126("" + getLevelForXP(playerXP[12]) + "", 4025);
		CAM().sendFrame126("" + playerLevel[13] + "", 4030);
		CAM().sendFrame126("" + getLevelForXP(playerXP[13]) + "", 4031);
		CAM().sendFrame126("" + playerLevel[14] + "", 4028);
		CAM().sendFrame126("" + getLevelForXP(playerXP[14]) + "", 4029);
		CAM().sendFrame126("" + playerLevel[15] + "", 4020);
		CAM().sendFrame126("" + getLevelForXP(playerXP[15]) + "", 4021);
		CAM().sendFrame126("" + playerLevel[16], 4018);
		CAM().sendFrame126("" + getLevelForXP(playerXP[16]), 4019);
		CAM().sendFrame126("" + playerLevel[17], 4022);
		CAM().sendFrame126("" + getLevelForXP(playerXP[17]), 4023);
		CAM().sendFrame126("" + playerLevel[20], 4152);
		CAM().sendFrame126("" + getLevelForXP(playerXP[20]), 4153);

		WriteEnergy();
		CAM().sendFrame126("", 6067);
		CAM().sendFrame126("", 6071);
		if (playerEquipment[playerWeapon] == -1) {
			SendWeapon(-1, "Unarmed");
		} else {
			SendWeapon(playerEquipment[playerWeapon], "Unarmed");
		}
		handler.updatePlayer(this, outStream);
		handler.updateNPC(this, outStream);
		setEquipment(playerEquipment[playerHat], playerEquipmentN[playerHat], playerHat);
		setEquipment(playerEquipment[playerCape], playerEquipmentN[playerCape], playerCape);
		setEquipment(playerEquipment[playerAmulet], playerEquipmentN[playerAmulet], playerAmulet);
		setEquipment(playerEquipment[playerArrows], playerEquipmentN[playerArrows], playerArrows);
		setEquipment(playerEquipment[playerChest], playerEquipmentN[playerChest], playerChest);
		setEquipment(playerEquipment[playerShield], playerEquipmentN[playerShield], playerShield);
		setEquipment(playerEquipment[playerLegs], playerEquipmentN[playerLegs], playerLegs);
		setEquipment(playerEquipment[playerHands], playerEquipmentN[playerHands], playerHands);
		setEquipment(playerEquipment[playerFeet], playerEquipmentN[playerFeet], playerFeet);
		setEquipment(playerEquipment[playerRing], playerEquipmentN[playerRing], playerRing);
		setEquipment(playerEquipment[playerWeapon], playerEquipmentN[playerWeapon], playerWeapon);
		resetItems(3214);
		resetBank();
		GetBonus();
		WriteBonus();
		replaceDoors();
		setInterfaceWalkable(6673);
		playerLastConnect = connectedFrom;
		updateRequired = true;
		appearanceUpdateRequired = true;
		
	}
	public void addHP(int hit) {
		currentHealth = (playerLevel[playerHitpoints] + hit);
		if (currentHealth >= getLevelForXP(playerXP[playerHitpoints])) {
			currentHealth = getLevelForXP(playerXP[playerHitpoints]);
		}
		refreshSkills();
	}
	public void replaceDoors() {
		for (int d = 0; d < doorHandler.doorX.length; d++) {
			if ((doorHandler.doorX[d] > 0)
					&& (doorHandler.doorHeight[d] == heightLevel)
					&& (Math.abs(doorHandler.doorX[d] - absX) <= 120)
					&& (Math.abs(doorHandler.doorY[d] - absY) <= 120)) {
				ReplaceObject(doorHandler.doorX[d],
				doorHandler.doorY[d],
				doorHandler.doorId[d],
				doorHandler.doorFace[d], 0);
			}
		}
	}
	public void ReplaceObject(int objectX, int objectY, int NewObjectID,int Face, int ObjectType) {
		outStream.createFrame(85);
		outStream.writeByteC(objectY - (mapRegionY * 8));
		outStream.writeByteC(objectX - (mapRegionX * 8));
		outStream.createFrame(101);
		outStream.writeByteC((ObjectType << 2) + (Face & 3));
		outStream.writeByte(0);
		if (NewObjectID != -1) {
			outStream.createFrame(151);
			outStream.writeByteS(0);
			outStream.writeWordBigEndian(NewObjectID);
			outStream.writeByteS((ObjectType << 2) + (Face & 3));
		}
	}
	public void initializeClientConfiguration() {
		// TODO: this is sniffed from a session (?), yet have to figure out what
		// each of these does.
		setClientConfig(18, 1);
		setClientConfig(19, 0);
		setClientConfig(25, 0);
		setClientConfig(43, 0);
		setClientConfig(44, 0);
		setClientConfig(75, 0);
		setClientConfig(83, 0);
		setClientConfig(84, 0);
		setClientConfig(85, 0);
		setClientConfig(86, 0);
		setClientConfig(87, 0);
		setClientConfig(88, 0);
		setClientConfig(89, 0);
		setClientConfig(90, 0);
		setClientConfig(91, 0);
		setClientConfig(92, 0);
		setClientConfig(93, 0);
		setClientConfig(94, 0);
		setClientConfig(95, 0);
		setClientConfig(96, 0);
		setClientConfig(97, 0);
		setClientConfig(98, 0);
		setClientConfig(99, 0);
		setClientConfig(100, 0);
		setClientConfig(101, 0);
		setClientConfig(104, 0);
		setClientConfig(106, 0);
		setClientConfig(108, 0);
		setClientConfig(115, 0);
		setClientConfig(143, 0);
		setClientConfig(153, 0);
		setClientConfig(156, 0);
		setClientConfig(157, 0);
		setClientConfig(158, 0);
		setClientConfig(166, 0);
		setClientConfig(167, 0);
		setClientConfig(168, 0);
		setClientConfig(169, 0);
		setClientConfig(170, 0);
		setClientConfig(171, 0);
		setClientConfig(172, 0);
		setClientConfig(173, 0);
		setClientConfig(174, 0);
		setClientConfig(203, 0);
		setClientConfig(210, 0);
		setClientConfig(211, 0);
		setClientConfig(261, 0);
		setClientConfig(262, 0);
		setClientConfig(263, 0);
		setClientConfig(264, 0);
		setClientConfig(265, 0);
		setClientConfig(266, 0);
		setClientConfig(268, 0);
		setClientConfig(269, 0);
		setClientConfig(270, 0);
		setClientConfig(271, 0);
		setClientConfig(280, 0);
		setClientConfig(286, 0);
		setClientConfig(287, 0);
		setClientConfig(297, 0);
		setClientConfig(298, 0);
		setClientConfig(301, 01);
		setClientConfig(304, 01);
		setClientConfig(309, 01);
		setClientConfig(311, 01);
		setClientConfig(312, 01);
		setClientConfig(313, 01);
		setClientConfig(330, 01);
		setClientConfig(331, 01);
		setClientConfig(342, 01);
		setClientConfig(343, 01);
		setClientConfig(344, 01);
		setClientConfig(345, 01);
		setClientConfig(346, 01);
		setClientConfig(353, 01);
		setClientConfig(354, 01);
		setClientConfig(355, 01);
		setClientConfig(356, 01);
		setClientConfig(361, 01);
		setClientConfig(362, 01);
		setClientConfig(363, 01);
		setClientConfig(377, 01);
		setClientConfig(378, 01);
		setClientConfig(379, 01);
		setClientConfig(380, 01);
		setClientConfig(383, 01);
		setClientConfig(388, 01);
		setClientConfig(391, 01);
		setClientConfig(393, 01);
		setClientConfig(399, 01);
		setClientConfig(400, 01);
		setClientConfig(406, 01);
		setClientConfig(408, 01);
		setClientConfig(414, 01);
		setClientConfig(417, 01);
		setClientConfig(423, 01);
		setClientConfig(425, 01);
		setClientConfig(427, 01);
		setClientConfig(433, 01);
		setClientConfig(435, 01);
		setClientConfig(436, 01);
		setClientConfig(437, 01);
		setClientConfig(439, 01);
		setClientConfig(440, 01);
		setClientConfig(441, 01);
		setClientConfig(442, 01);
		setClientConfig(443, 01);
		setClientConfig(445, 01);
		setClientConfig(446, 01);
		setClientConfig(449, 01);
		setClientConfig(452, 01);
		setClientConfig(453, 01);
		setClientConfig(455, 01);
		setClientConfig(464, 01);
		setClientConfig(465, 01);
		setClientConfig(470, 01);
		setClientConfig(482, 01);
		setClientConfig(486, 01);
		setClientConfig(491, 01);
		setClientConfig(492, 01);
		setClientConfig(493, 01);
		setClientConfig(496, 01);
		setClientConfig(497, 01);
		setClientConfig(498, 01);
		setClientConfig(499, 01);
		setClientConfig(502, 01);
		setClientConfig(503, 01);
		setClientConfig(504, 01);
		setClientConfig(505, 01);
		setClientConfig(506, 01);
		setClientConfig(507, 01);
		setClientConfig(508, 01);
		setClientConfig(509, 01);
		setClientConfig(510, 01);
		setClientConfig(511, 01);
		setClientConfig(512, 01);
		setClientConfig(515, 01);
		setClientConfig(518, 01);
		setClientConfig(520, 01);
		setClientConfig(521, 01);
		setClientConfig(524, 01);
		setClientConfig(525, 01);
		setClientConfig(531, 01);
	}
	public boolean inRange(int x, int y) {
		if (localId > 0)
		return false;
		if ((Math.abs(absX - x) < 4) && (Math.abs(absY - y) < 4)) {
			return true;
		}
		return false;
	}
	public boolean isinpm(long l) {
		for (long element : friends) {
			if (element != 0) {
				if (l == element) {
					return true;
				}
			}
		}
		return false;
	}
	public boolean safeZones(int X, int Y) { //x first
		if ((X >= 3090) && (X <= 3099) && (Y <= 3499) && (Y >= 3488)) { //bank
			return true;
		}
		if ((X >= 3090) && (X <= 3251) && (Y <= 3203) && (Y >= 3191)) { //lumby graveyard
			return true;
		}
		if ((X >= 3291) && (X <= 3307) && (Y <= 3318) && (Y >= 3274)) { //lumby graveyard
			return true;
		}
		return false;
	}
	public boolean isInWilderness(int coordX, int coordY, int Type) {
		if (Type == 1) {
			if ((coordY >= 3520) && (coordY <= 3967) && (coordX <= 3392)
					&& (coordX >= 2942)) {
				return true;
			}
		} else if (Type == 2) {
			if ((coordY >= 3512) && (coordY <= 3967) && (coordX <= 3392)
					&& (coordX >= 2942)) {
				return true;
			}
		}
		return false;
	}
	public boolean IsItemInBag(int ItemID) {
		for (int element : playerItems) {
			if ((element - 1) == ItemID) {
				return true;
			}
		}
		return false;
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
	public void itemsToVScreen_old() {
		outStream.createFrameVarSizeWord(53);
		outStream.writeWord(6822);
		outStream.writeWord(otherOfferedItems.toArray().length);
		for (GameItem item : otherOfferedItems) {
			if (item.amount > 254) {
				outStream.writeByte(255); // item's stack count. if over 254,
				// write byte 255
				outStream.writeDWord_v2(item.amount); // and then the real
				// value with
				// writeDWord_v2
			} else {
				outStream.writeByte(item.amount);
			}
			if ((item.id > 20000) || (item.id < 0)) {
				item.id = 20000;
			}
			outStream.writeWordBigEndianA(item.id + 1); // item id
		}
		outStream.endFrameVarSizeWord();
	}
	public int itemType(int item) {
		for (int element : Item.capes) {
			if (item == element) {
				return playerCape;
			}
		}
		for (int element : Item.hats) {
			if (item == element) {
				return playerHat;
			}
		}
		for (int element : Item.boots) {
			if (item == element) {
				return playerFeet;
			}
		}
		for (int element : Item.gloves) {
			if (item == element) {
				return playerHands;
			}
		}
		for (int element : Item.shields) {
			if (item == element) {
				return playerShield;
			}
		}
		for (int element : Item.amulets) {
			if (item == element) {
				return playerAmulet;
			}
		}
		for (int element : Item.arrows) {
			if (item == element) {
				return playerArrows;
			}
		}
		for (int element : Item.rings) {
			if (item == element) {
				return playerRing;
			}
		}
		for (int element : Item.body) {
			if (item == element) {
				return playerChest;
			}
		}
		for (int element : Item.legs) {
			if (item == element) {
				return playerLegs;
			}
		}
		// Default
		return playerWeapon;
	}
	public void levelup(int skill) {
		switch (skill) {
		case 0:
			CAM().sendMessage("Congratulations, you just advanced an attack level.");
			break;
		case 1:
			CAM().sendMessage("Congratulations, you just advanced a defence level.");
			break;
		case 2:
			CAM().sendMessage("Congratulations, you just advanced a strength level.");
			break;
		case 3:
			CAM().sendMessage("Congratulations, you just advanced a hitpoints level.");
			currentHealth ++;
			break;
		case 4:
			CAM().sendMessage("Congratulations, you just advanced a ranging level.");
			break;
		case 5:
			CAM().sendMessage("Congratulations, you just advanced a prayer level.");
			break;
		case 6:
			CAM().sendMessage("Congratulations, you just advanced a magic level.");
			break;
		case 7:
			CAM().sendMessage("Congratulations, you just advanced a cooking level.");
			break;
		case 8:
			CAM().sendMessage("Congratulations, you just advanced a woodcutting level.");
			break;
		case 9:
			CAM().sendMessage("Congratulations, you just advanced a fletching level.");
			break;
		case 10:
			CAM().sendMessage("Congratulations, you just advanced a fishing level.");
			break;
		case 11:
			CAM().sendMessage("Congratulations, you just advanced a fire making level.");
			break;
		case 12:
			CAM().sendMessage("Congratulations, you just advanced a crafting level.");
			break;
		case 13:
			CAM().sendMessage("Congratulations, you just advanced a smithing level.");
			break;
		case 14:
			CAM().sendMessage("Congratulations, you just advanced a mining level.");
			break;
		case 15:
			CAM().sendMessage("Congratulations, you just advanced a herblore level.");
			break;
		case 16:
			CAM().sendMessage("Congratulations, you just advanced an agility level.");
			break;
		case 17:
			CAM().sendMessage("Congratulations, you just advanced a thieving level.");
			break;
		case 18:
			CAM().sendMessage("Congratulations, you just advanced a slayer level.");
			break;
		case 19:
			CAM().sendMessage("Congratulations, you just advanced a farming level.");
			break;
		case 20:
			CAM().sendMessage("Congratulations, you just advanced a runecrafting level.");
			break;
		}
	}
	public void loadpm(long name, int world) {
		if (world != 0) {
			world += 9;
		} else if (world == 0) {
			world = 0;
		}
		outStream.createFrame(50);
		outStream.writeQWord(name);
		outStream.writeByte(world);
	}
	public void logout() {
		if (LogoutDelay > 0) {
			CAM().sendMessage("You must wait "+ LogoutDelay /2 +" seconds until logging out.");
			return;
		}
		try {
			stop();
			for(Event e : server.eventsToAdd) {
				server.eventsToAdd.clear();
				server.eventsToRemove.add(e);
			}
			for(Event e : server.eventsToRemove) {
				server.events.remove(e);
			}
			ResetAttackNPC();
			SG().savegame(this, true);
			CAM().sendQuest("Click here to logout", 2458);
			outStream.createFrame(109);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	public void modYell(String sendMessage) {
		for (Player element : handler.players) {
			client p = (client) element;
			if ((p != null) && !p.disconnected && (p.absX > 0)
					&& (p.playerRights > 0)) {
				p.CAM().sendMessage(sendMessage);
			}
		}
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
		if ((moveWindow == 34453) && (from >= 0) && (to >= 0)
				&& (from < playerBankSize) && (to < playerBankSize)) {
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
	public void openUpBank() {
		rearrangeBank();
		resetBank();
		CAM().sendFrame248(5292, 5063);
		resetItems(5064);
		IsBanking = true;
	}
	public void openUpShop(int ShopID) {
		CAM().sendFrame126(server.shopHandler.ShopName[ShopID], 3901);
		CAM().sendFrame248(3824, 3822);
		resetItems(3823);
		resetShop(ShopID);
		IsShopping = true;
		MyShopID = ShopID;
	}
	public boolean packetProcess() {
		if (disconnected) {
			return false;
		}
		try {
			if (in == null) {
				return false;
			}
			int avail = in.available();
			if (avail == 0) {
				return false;
			}
			if (packetType == -1) {
				packetType = in.read() & 0xff;
				if (inStreamDecryption != null) {
					packetType = packetType - inStreamDecryption.getNextKey()
					& 0xff;
				}
				packetSize = packetSizes[packetType];
				avail--;
			}
			if (packetSize == -1) {
				if (avail > 0) {
					packetSize = in.read() & 0xff;
					avail--;
				} else {
					return false;
				}
			}
			if (avail < packetSize) {
				return false;
			}
			fillInStream(packetSize);
			timeOutCounter = 0;
			if (packetType > 0) {
				parseIncomingPackets();
				packetType = -1;
			}
		} catch (java.lang.Exception __ex) {
			server.logError(__ex.getMessage());
			disconnected = true;
			System.out.println("Project16 [fatal] - exception");
			SG().savegame(this, true);
		}
		return true;
	}
	public void parseIncomingPackets() {
		int i;
		int junk;
		int junk2;
		int junk3;		
		lastPacket = System.currentTimeMillis();
		switch (packetType) {
			
		case 0:
			SG().savegame(this, true);
			break;
		case 3:
			break;
		case 4:
			if (muted) {
				break;
			}
			if (System.currentTimeMillis() - lasttalk > 1000)
			{
				lasttalk = System.currentTimeMillis();
				MBTC = misc.textUnpack(chatText, packetSize - 2);
				MBBC = misc.textUnpack(chatText, packetSize - 2);
				MBHT = misc.textUnpack(chatText, packetSize - 2);
				MBID = misc.textUnpack(chatText, packetSize - 2);
				chatTextEffects = inStream.readUnsignedByteS();
				chatTextColor = inStream.readUnsignedByteS();
				chatTextSize = (byte) (packetSize - 2);
				inStream.readBytes_reverseA(chatText, chatTextSize, 0);
				//chatLog("Player:" + playerName + " Rights:" +playerRights + " " +chatTextSize+"", "chatLog");
				chatTextUpdateRequired = true;
				
			} else {
				lasttalk = System.currentTimeMillis();
				
				
			}
			break;
			
		case 14:
			break;
		case 17: // second Click npc
			NPCSlot = inStream.readUnsignedWordBigEndianA();
			faceNPC(NPCSlot);
			if ((NPCSlot < 0) || (NPCSlot >= server.npcHandler.npcs.length) || (server.npcHandler.npcs[NPCSlot] == null))
			break;
			NPCID = server.npcHandler.npcs[NPCSlot].npcType;
			thisNPC = NPCID;
			long time = System.currentTimeMillis();
			int npcX = server.npcHandler.npcs[NPCSlot].absX;
			int npcY = server.npcHandler.npcs[NPCSlot].absY;
			if (server.npcHandler.npcs[NPCSlot].IsDead) {
				CAM().sendMessage("That monster has been killed!");
				break;
			}
			boolean FishingGo = false;
			boolean PutNPCCoords = false;
			switch(NPCID) {
				//thieving starts here
			case 1:
			case 2:
			case 3:
				THNPC().pickPocket(this, 1,995,misc.random(150),25,"Man",2);
				break;
			case 1699:
				WanneShop = 1;
				break;
			case 4:
			case 5:
			case 6:
				THNPC().pickPocket(this, 1,995,misc.random(150),50,"Woman",2);	
				break;
			case 7:
				THNPC().pickPocket(this, 12,995,misc.random(300),100,"Farmer",2);	
				break;
			case 9:
			case 10:
				THNPC().pickPocket(this, 40,995,misc.random(1250),300,"Guard",3);
				break;
				//thieving ends here
			case 330:
				if ((System.currentTimeMillis() - lastFish) < fish().calculateFishingDelay(this) || !fish().hasStoppedFishing(this)) {
					break;
				}
				if (!fish().hasStoppedFishing(this)) {
					break;
				}
				if (playerLevel[10] > 10) {
					fish().fishingMain(this, playerName, playerId, 10, 307, 1, 313, 1, -1, -1, 622, 5000, 345, 60, 4);
				} else {	
					fish().fishingMain(this, playerName, playerId, 5, 307, 1, 313, 1, -1, -1, 622, 5000, 327, 60, 3);
				}
				break;
			case 309:
				if ((System.currentTimeMillis() - lastFish) < fish().calculateFishingDelay(this) || !fish().hasStoppedFishing(this)) {
					break;
				}
				if (!fish().hasStoppedFishing(this)) {
					break;
				}
				if (playerLevel[10] > 10) {
					fish().fishingMain(this, playerName, playerId, 10, 307, 1, 313, 1, -1, -1, 622, 5000, 345, 60, 4);
				} else {	
					fish().fishingMain(this, playerName, playerId, 5, 307, 1, 313, 1, -1, -1, 622, 5000, 327, 60, 3);
				}
				break;
			case 333:
				if ((System.currentTimeMillis() - lastFish) < fish().calculateFishingDelay(this) || !fish().hasStoppedFishing(this)) {
					break;
				}
				if (!fish().hasStoppedFishing(this)) {
					break;
				}
				if (playerLevel[10] > 50) {
					fish().fishingMain(this, playerName, playerId, 45, 311, 1, -1, -1, -1, -1, 618, 5000, 371, 60, 10);
				} else if (playerLevel[10] > 44) {
					fish().fishingMain(this, playerName, playerId, 45, 311, 1, -1, -1, -1, -1, 618, 5000, 371, 60, 9);
				} else {	
					fish().fishingMain(this, playerName, playerId, 35, 311, 1, -1, -1, -1, -1, 618, 5000, 359, 60, 8);
				}
				break;
			case 334:
				if ((System.currentTimeMillis() - lastFish) < fish().calculateFishingDelay(this) || !fish().hasStoppedFishing(this)) {
					break;
				}
				if (!fish().hasStoppedFishing(this)) {
					break;
				}
				fish().fishingMain(this, playerName, playerId, 76, 311, 1, -1, -1, -1, -1, 618, 4500, 383, 20, 14);	
				break;
			case 804:
				getCraft().openTan(this);
				break;
			case 531:
			case 530:
				break;
			case 1860:
				break;
			case 494:
			case 495:
			case 496:
			case 497:
			case 1702:
			case 2354:
			case 2355:
			case 2619:
			case 3198:
				skillX = server.npcHandler.npcs[NPCSlot].absX;
				skillY = server.npcHandler.npcs[NPCSlot].absY;
				WanneBank = 2;
				break;
			}	
			if (PutNPCCoords == true) {
				skillX = server.npcHandler.npcs[NPCSlot].absX;
				skillY = server.npcHandler.npcs[NPCSlot].absY;
			}
			break;
		case 21: // Thirth Click npc
			NPCID = server.npcHandler.npcs[NPCSlot].npcType;
			NPCSlot = inStream.readUnsignedWordBigEndianA();
			if ((NPCSlot < 0) || (NPCSlot >= server.npcHandler.npcs.length) || (server.npcHandler.npcs[NPCSlot] == null))
			break;
			faceNPC(NPCSlot);
			break;
		case 25: // item used on floor item (not used atm)
			break;
		case 39:
			int pIndex = inStream.readUnsignedWordBigEndian();
			follow(pIndex, playerId);
			break;
		case 40: // npc dialoge, not needed atm
			DA().case40(this);
			break;
		case 41:
			// wear item
			int wearID = inStream.readUnsignedWord();
			int wearSlot = inStream.readUnsignedWordA();
			waitingID = wearID;
			waitingSlot = wearSlot;
			int interfaceID = inStream.readUnsignedWordA();
			if (!antiHax())
			break;
			if (wearID == 6865) {//blue
				startAnimation(3004);
				gfx0(512);
				return;
			}
			if (wearID == 6866) {//green
				startAnimation(3004);
				gfx0(516);
				return;
			}
			if (wearID == 6867) {//red
				startAnimation(3004);
				gfx0(508);
				return;
			}
			//if (wearID == 6889 && QH().q2 == 15) {
			//	setSidebarInterface(6, 12855);//ancient magicks
			///	ancients = 1;
			//	CAM().sendMessage("You converted to Ancient Magicks.");
			//	wear(wearID, wearSlot);
			//}
			wear(wearID, wearSlot);
			break;
		case 43: // bank 10 items - sell 5 item
			interfaceID = inStream.readUnsignedWordBigEndian();
			int removeID = inStream.readUnsignedWordA();
			int removeSlot = inStream.readUnsignedWordA();
			if (interfaceID == 5064) {
				bankItem(removeID, removeSlot, 10);
			} else if (interfaceID == 5382) {
				fromBank(removeID, removeSlot, 10);
			} else if (interfaceID == 65436) {
				bankItem(removeID, removeSlot, 10);
				resetItems(7423);
			} else if (interfaceID == 3823) {
				sellItem(removeID, removeSlot, 5);
			} else if (interfaceID == 3900) {
				buyItem(removeID, removeSlot, 5);
			} else if (interfaceID == 3322) {
								getTrade().tradeItem(removeID, removeSlot, 10);
			} else if (interfaceID == 3415) {
				getTrade().fromTrade(removeID, removeSlot, 10);
			} else if (interfaceID == 1119) {
				new SmithingMakeItem(this, removeID, 10);
			} else if (interfaceID == 1120) {
				new SmithingMakeItem(this, removeID, 10);
			} else if (interfaceID == 1121) {
				new SmithingMakeItem(this, removeID, 10);
			} else if (interfaceID == 1122) {
				new SmithingMakeItem(this, removeID, 10);
			} else if (interfaceID == 1123) {
				new SmithingMakeItem(this, removeID, 10);
			}
			break;
		case 45: // flagged account data
			int blah = inStream.readUnsignedByte();
			int part2 = -1,
			part3 = -1,
			part4 = -1;
			try {
				part2 = inStream.readUnsignedWord();
			} catch (Exception e) {
				println("part2 not sent");
			}
			try {
				part3 = inStream.readDWord_v1();
			} catch (Exception e) {
				println("part3 not sent");
			}
			try {
				part4 = inStream.readDWord();
			} catch (Exception e) {
				println("part4 not sent");
			}
			println("blah=" + blah + ", " + "part2=" + part2 + ", part3="
			+ part3 + ", part4=" + part4);
			break;
		case 53: //item on item
			int usedWithSlot = inStream.readUnsignedWord();
			int itemUsedSlot = inStream.readUnsignedWordA();
			int interface1284 = inStream.readUnsignedWord();
			int interfacek = inStream.readUnsignedWord();
			int useWith = playerItems[usedWithSlot] - 1;
			int itemUsed = playerItems[itemUsedSlot] - 1;
			if (!playerHasItem(itemUsed) || !playerHasItem(useWith)) {
				break;
			}
			int otherItem = playerItems[usedWithSlot] - 1;
			if ((itemUsed == 590 && useWith == 1511) || (itemUsed == 1511 && useWith == 590)) {
				Fire().firemakingMain(this, 0, 1511, 25);
				woodIDs = 1511;
			}
			if ((itemUsed == 590 && useWith == 1521) || (itemUsed == 1521 && useWith == 590)) { 	
				Fire().firemakingMain(this, 15, 1521, 75);
				woodIDs = 1521;
			}
			if ((itemUsed == 590 && useWith == 1519) || (itemUsed == 1519 && useWith == 590)) {
				Fire().firemakingMain(this, 30, 1519, 150);
				woodIDs = 1519;
			}
			if ((itemUsed == 590 && useWith == 1517) || (itemUsed == 1517 && useWith == 590)) {
				Fire().firemakingMain(this, 45, 1517, 250);
				woodIDs = 1517;
			}
			if ((itemUsed == 590 && useWith == 1515) || (itemUsed == 1515 && useWith == 590)) {
				Fire().firemakingMain(this, 60, 1515, 350);
				woodIDs = 1515;
			}
			if ((itemUsed == 590 && useWith == 1513) || (itemUsed == 1513 && useWith == 590)) {
				Fire().firemakingMain(this, 75, 1513, 500);
				woodIDs = 1513;
			}
			int[]reqLvl =  { 55, 43, 34, 27, 20, 13, 1, 16, 67 };
			int[]neededGem =  { 1631, 1617, 1619, 1621, 1623, 1627, 1625, 1629, 6571 };
			int[]givenResult =  { 1615, 1601, 1603, 1605, 1607, 1611, 1609, 1613, 6573 };
			int[]expGiven =  { 137, 107, 85, 67, 50, 20, 15, 25, 168 };
			for (int h = 0; h < reqLvl.length; h++) {
				if (playerHasItem(neededGem[h], 1) && playerHasItem(1755, 1) && (itemUsed == 1755 && useWith == neededGem[h]) || (useWith == 1755 && itemUsed == neededGem[h])) {
					if (playerLevel[playerCrafting] < reqLvl[h]) {
						CAM().sendMessage("You need a level of "+reqLvl[h]+" to craft that!");
						break;
					}
					CAM().sendMessage("You craft the gem.");
					startAnimation(885);
					deleteItem(neededGem[h], 1); //Deletes the neededgem
					addItem(givenResult[h], 1); //Gives item
					addSkillXP(expGiven[h], 12); //Gives EXP
					break;
				}
			}
			for (int h = 0; h < getCraft().leathers.length; h++) {
				if (((itemUsed == 1733) || (useWith == 1733))
						&& ((itemUsed == getCraft().leathers[h]) || (useWith == getCraft().leathers[h]))) {
					getCraft().craftMenu(this, h);
					getCraft().cIndex = h;
				}
			}
			if (((itemUsed == 1733) || (useWith == 1733))
					&& ((itemUsed == 1741) || (useWith == 1741))) {
				CAM().showInterface(2311);
			}
			break;
		case 57:
			int aA1 = inStream.readSignedWordBigEndianA();
			int b1 = inStream.readSignedWordBigEndianA();
			int c1 = inStream.readSignedWordBigEndian();
			int d1 = inStream.readSignedWordBigEndianA();
			break;
		case 72: // Click to attack
			if ((System.currentTimeMillis() - lastAttack > 1500) && (deathStage < 1)) {
				attacknpc = inStream.readUnsignedWordA();
				int EnemyX = server.npcHandler.npcs[attacknpc].absX + server.npcHandler.npcs[attacknpc].npcSize;
				int EnemyY = server.npcHandler.npcs[attacknpc].absY + server.npcHandler.npcs[attacknpc].npcSize;
				if (server.npcHandler.npcs[attacknpc].npcType != 2881) {
					if (server.npcHandler.npcs[attacknpc].combatTimer > 0 && server.npcHandler.npcs[attacknpc].playerId != playerId) {
						CAM().sendMessage("This monster is already fighting someone else.");
						WalkTo(0,0);
						break;
					}
				}
				server.npcHandler.npcs[attacknpc].combatTimer = 0;
				server.npcHandler.npcs[attacknpc].playerId = playerId;
				if (mC().usingBow() && GoodDistance(EnemyX, EnemyY, absX, absY, 8) == true) {
					WalkTo(0,0);
				}
				if ((attacknpc >= 0) && (attacknpc < server.npcHandler.maxNPCSpawns)) {
					followThisPlayer = -1;
					IsAttackingNPC = true;
					if(server.npcHandler.npcs[attacknpc].absX != absX && server.npcHandler.npcs[attacknpc].absY != absY){
						faceNPC(attacknpc);
					}
					actionTimer = 5;
				} else {
					CAM().sendMessage("Exception catched, npc id was invalid.");
					ResetAttackNPC();
				}
				
			}
			break;
		case 73:
			// Attack (Wilderness)
			AttackingOn = inStream.readSignedWordBigEndian();
			followThisPlayer = AttackingOn;
			if (AttackingOn > 0) {
				try {
					int EnemyX = server.playerHandler.players[AttackingOn].absX;
					int EnemyY = server.playerHandler.players[AttackingOn].absY;
					if (mC().usingBow() && GoodDistance(EnemyX, EnemyY, absX, absY, 8) == true) {
						WalkTo(0,0);
					}
					client AttackingOn2 = (client) server.playerHandler.players[AttackingOn];
					facePlayer();
					if (isInWilderness(EnemyX, EnemyY, 1) == true && isInWilderness(absX, absY, 1) == false) {
						CAM().sendMessage("You cannot attack players outside of the wilderness.");
						break;
					}
					if (isInWilderness(EnemyX, EnemyY, 1) == false && isInWilderness(absX, absY, 1) == true) {
						CAM().sendMessage("You cannot attack players outside of the wilderness.");
						break;
					}
					if (isInWilderness(EnemyX, EnemyY, 1) == false && isInWilderness(absX, absY, 1) == false) {
						CAM().sendMessage("You cannot attack players outside of the wilderness");
						break;
					}
					if (isInWilderness(EnemyX, EnemyY, 1) == false && isInWilderness(absX, absY, 1) == true) {
						CAM().sendMessage("You cannot attack players outside of the wilderness");
						break;
					}
					if (AttackingOn2.combatLevel + AttackingOn2.wildyLevel < combatLevel || combatLevel + AttackingOn2.wildyLevel < AttackingOn2.combatLevel) {
						CAM().sendMessage("You and your opponent have to greater level difference for this area.");
						break;
					}
					if (AttackingOn2.deathStage > 0) {
						break;
					}
					if (isFrozen) {
						WalkTo(0,0);
					}
					if (deathStage > 0) {
						IsAttacking = false;
						break;
					}
					if (AttackingOn2.AttackingOn != playerId && AttackingOn2.AttackingOn != 0 && !AttackingOn2.multiZone()) {
						CAM().sendMessage("This player is already in combat.");
						IsAttacking = false;
						break;		
					}
					if ((AttackingOn >= handler.players.length) || (AttackingOn < 1)) {
						IsAttacking = false;
						break;
					}
					follow(AttackingOn, playerId);
					stopAttack = false;
					IsAttacking = true;
					getCombat().combatExecuter(playerId, followThisPlayer);	
				} catch (java.lang.Exception attackError) {
					attackError.printStackTrace();
					System.out.println("parseInComingPackets() error caught (case 73).");
				}
			} else {	
				CAM().sendMessage("This player does not exist");
				break;
			}	
			break;
			
			
		case 74: // remove ignore
			friendUpdate = true;
			long igtorem = inStream.readQWord();
			for (int i11 = 0; i11 < ignores.length; i11++) {
				if (ignores[i11] == igtorem) {
					ignores[i11] = 0;
					break;
				}
			}
			break;
		case 16://atlernet item option 2
			int item_id = inStream.readSignedWordA();
			if (item_id == 6865) {//blue
				startAnimation(3005);
				gfx0(513);
			}
			if (item_id == 6866) {//green
				startAnimation(3005);
				gfx0(517);
			}
			if (item_id == 6867) {//red
				startAnimation(3005);
				gfx0(509);
			}
			if (item_id == 7601) {
				if (flying == false) {
					setAnimation(1500);
					playerSE = 1501;
					playerSEW = 1851;
					playerSER = 1851;	
					playerSEA = 1851;
					flying = true;
					updateRequired = true;
					appearanceUpdateRequired = true;
				} else {
					updateRequired = true;
					appearanceUpdateRequired = true;
					flying = false;
					playerSE = 0x328; // SE = Standard Emotion
					playerSEA = 0x326; // SEA = Standard Emotion Attack
					playerSER = 0x338; // SER = Standard Emotion Run
					playerSEW = 0x333; // SEW = Standard Emotion Walking
				}
			}
			break;
		case 75: // atler item op1
			int item2ID = inStream.readSignedWordBigEndian();
			int item2ID3 = inStream.readSignedWordA();
			int item2ID4 = inStream.readUnsignedWord();
			switch(item2ID) {
			}
			switch(item2ID3) {
			}
			switch(item2ID4) {
			case 6737://blue
				startAnimation(3006);
				gfx0(514);
				break;
			case 6738://green
				startAnimation(3006);
				gfx0(518);
				break;
			case 6739://red
				startAnimation(3006);
				gfx0(510);
				break;
			}	
			break;
		case 78: // removes the flag from minimap
			break;
		case 86:
			// camera angle
			int a = inStream.readSignedWord();
			int a1 = inStream.readSignedWordA();
			break;
		case 87: // drop item
			int droppedItem = inStream.readUnsignedWordA();
			somejunk = inStream.readUnsignedByte() + inStream.readUnsignedByte();
			int slot = inStream.readUnsignedWordA();
			if (deathStage > 0) {
				break;
			}
			if (wearing == false) {
				dropItem(droppedItem, slot);
			}
			break;
		case 95: // update chat
			Privatechat = inStream.readUnsignedByte();
			Publicchat = inStream.readUnsignedByte();
			for (int i1 = 1; i1 < handler.maxPlayers; i1++) {
				if ((handler.players[i1] != null) && (handler.players[i1].isActive == true)) {
					handler.players[i1].pmupdate(playerId, GetWorld(playerId));
				}
			}
			break;
		case 248: // packet is for walking via mini screen
			if (inTeleport) {
				break;
			}	
			followPlayer = null;
			pauseFollow = false;
			stopAttack = true;
			if (IsAttacking) {
				IsAttacking = false;
			}
			if (IsAttackingNPC)
				ResetAttackNPC();
			if (isFrozen) {
				CAM().sendMessage("A magical force stops you from moving!");
				break;	
			}
			packetSize -= 14; // ignore the junk
			stopAttack = true;
		case 164: // packet is for walking using big screen
			if (inTeleport) {
				break;
			}
			followPlayer = null;
			pauseFollow = false;
			stopAttack = true;
			if (IsAttacking) {
				IsAttacking = false;
			}
			if (IsAttackingNPC)
				ResetAttackNPC();
			if (isFrozen) {
				CAM().sendMessage("A magical force stops you from moving!");
				break;	
			}
			//
		case 98: // walk on command
			if (inTeleport)
			break;
			
			if (!fish().hasStoppedFishing(this) ||!Cooking().hasStoppedCooking(this))
			stop();
			
			if (hasFinishedAgilityBranches || hasFinishedAgility || hasFinishedAgilityNets || hasFinishedAgilityPipes)
			break;
			
			if (!hasMoved) {
				hasFinished = true;
				hasMoved = true;
			}
			if (QH().q1 == 0) {
				CAM().sendMessage("Finish Talking to Antares's Guide!");
				break;
			}
			if (isStunned) {
				CAM().sendMessage("You're stunned!");
				break;	
			}
			getCloser = false;
			faceNPCupdate = false;
			if(faceNPC > 0) {
				faceNPC = -1;
				faceNPCupdate = true;
			}
			if (followPlayer != null) {
				followPlayer = null;
			}
			//IsAttacking = false;
			if (randomed)
			break;
			if (!validClient) {
				CAM().sendMessage("You can't move on this account");
				break;
			}
			essMine = false;
			ismining = false;
			canmine = false;
			repeatSpell = false;
			repeatSpellNpc = false;
			cancut = false;
			iscutting = false;
			updateRequired = true;
			appearanceUpdateRequired = true;
			//IsAttackingNPC = false;
			closeInterface();
			resetAnimation();
			canthiev = false;
			if (deathStage == 0) {
				newWalkCmdSteps = packetSize - 5;
				if (newWalkCmdSteps % 2 != 0) {
				}
				newWalkCmdSteps /= 2;
				if (++newWalkCmdSteps > walkingQueueSize) {
					newWalkCmdSteps = 0;
					break;
				}
				int firstStepX = inStream.readSignedWordBigEndianA();
				int tmpFSX = firstStepX;
				firstStepX -= mapRegionX * 8;
				for (i = 1; i < newWalkCmdSteps; i++) {
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
				for (i = 0; i < newWalkCmdSteps; i++) {
					newWalkCmdX[i] += firstStepX;
					newWalkCmdY[i] += firstStepY;
				}
				poimiY = firstStepY;
				poimiX = firstStepX;
				if (stairs > 0) {
					resetStairs();
				}
				if (NpcDialogue > 0) {
					NpcDialogue = 0;
					NpcTalkTo = 0;
					NpcDialogueSend = false;
					RemoveAllWindows();
				}
				if (IsBanking == true) {
					RemoveAllWindows();
				}
				if (IsShopping == true) {
					IsShopping = false;
					MyShopID = 0;
					UpdateShop = false;
					RemoveAllWindows();
				}
				updateRequired = true;
				appearanceUpdateRequired = true;
			}
			updateRequired = true;
			appearanceUpdateRequired = true;
			break;
		case 101: // Character Design Screen
			if (!antiHax())
			break;
			int[] input = new int[13];
			int highest = -1,
			numZero = -1,
			num44 = 0;
			for (int b = 0; b < 13; b++) {
				input[b] = inStream.readSignedByte();
				if (input[b] > highest)
				highest = input[b];
				if (input[b] < 1)
				numZero++;
				if (input[b] < 0)
				input[b] = 0;
				if (input[b] == 44)
				num44++;
			}
			if ((highest < 1) || (num44 == 7)) {
				if (uid > 1)
				server.bannedUid.add(new Integer(uid));
				isKicked = true;
				break;
			}
			pGender = input[0];
			pHead = input[1];
			pBeard = input[2];
			pTorso = input[3];
			pArms = input[4];
			pHands = input[5];
			pLegs = input[6];
			pFeet = input[7];
			pHairC = input[8];
			pTorsoC = input[9];
			pLegsC = input[10];
			pFeetC = input[11];
			pSkinC = input[12];
			playerLook[0] = input[0];
			playerLook[1] = input[8];
			playerLook[2] = input[9];
			playerLook[3] = input[10];
			playerLook[4] = input[11];
			playerLook[5] = input[12];
			apset = true;
			appearanceUpdateRequired = true;
			lookUpdate = true;
			break;
		case 103: // Custom player command, the ::words
			if (isInWilderness(absX,absY, 1) == true) {
				CAM().sendMessage("You cannot use this command in the wilderness!");
				break;
			}
			Command().handlePacket(this, packetType, packetSize);
			break;
		case 117: // bank 5 items - sell 1 item
			interfaceID = inStream.readSignedWordBigEndianA();
			removeID = inStream.readSignedWordBigEndianA();
			removeSlot = inStream.readSignedWordBigEndian();
			
			if (interfaceID == 5064) {
				bankItem(removeID, removeSlot, 5);
			} else if (interfaceID == 5382) {
				fromBank(removeID, removeSlot, 5);
			} else if (interfaceID == 32668) {
				bankItem(removeID, removeSlot, 5);
				resetItems(7423);
			} else if (interfaceID == 3823) {
				sellItem(removeID, removeSlot, 1);
			} else if (interfaceID == 3900) {
				buyItem(removeID, removeSlot, 1);
			} else if (interfaceID == 3322) {
				getTrade().tradeItem(removeID, removeSlot, 5);
			} else if (interfaceID == 3415) {
				getTrade().fromTrade(removeID, removeSlot, 5);
			} else if (interfaceID == 1119) {
				new SmithingMakeItem(this, removeID, 5);
			} else if (interfaceID == 1120) {
				new SmithingMakeItem(this, removeID, 5);
			} else if (interfaceID == 1121) {
				new SmithingMakeItem(this, removeID, 5);
			} else if (interfaceID == 1122) {
				new SmithingMakeItem(this, removeID, 5);
			} else if (interfaceID == 1123) {
				new SmithingMakeItem(this, removeID, 5);
			}
			break;
		case 121: //change regions
			getRegion().RegionLoader(this);
			NewObjects();
			break;
		case 122: // Call for burying bones
			long lastMunch = 0;
			junk = inStream.readSignedWordBigEndianA();
			ItemSlot = inStream.readUnsignedWordA();
			ItemID = inStream.readUnsignedWordBigEndian();
			if (System.currentTimeMillis() - lastMunch >= 6000) {
				if (System.currentTimeMillis() - lastBuild >= 1200) {
					lastBuild = System.currentTimeMillis();
					weaponDelay = (weaponDelay + 1500);
				}
				CI().buryItem(this, ItemID, ItemSlot);
				lastMunch = System.currentTimeMillis();
				actionTimer = 10;
			}
			break;
		case 126: // pm message
			long friendtosend = inStream.readQWord();
			byte pmchatText[] = new byte[100];
			int pmchatTextSize = (byte) (packetSize - 8);
			inStream.readBytes(pmchatText, pmchatTextSize, 0);
			if(muted){
				CAM().sendMessage("You are muted and cannot talk!");
				break;
			}
			for (long element : friends) {
				if (element == friendtosend) {
					boolean pmsent = false;
					for (int i2 = 1; i2 < handler.maxPlayers; i2++) {
						if ((handler.players[i2] != null)
								&& handler.players[i2].isActive
								&& (misc
									.playerNameToInt64(handler.players[i2].playerName) == friendtosend)) {
							if ((handler.players[i2].Privatechat == 0) || ((handler.players[i2].Privatechat == 1) && handler.players[i2].isinpm(misc.playerNameToInt64(playerName)))) {
								handler.players[i2].sendpm(misc.playerNameToInt64(playerName),
								playerRights, pmchatText,
								pmchatTextSize);
								pmsent = true;
							}
							break;
						}
					}
					if (!pmsent) {
						CAM().sendMessage("Player currently not available");
						break;
					}
				}
			}
			break;
			
		case 128: // Trade Request
			int temp = inStream.readUnsignedWord();
			//if (!antiHax())
			//	break;
			break;
		case 129: // bank all items - sell 10 items
			
			removeSlot = inStream.readUnsignedWordA();
			interfaceID = inStream.readUnsignedWord();
			removeID = inStream.readUnsignedWordA();
			if (interfaceID == 5064) {
				if (Item.itemStackable[removeID] == true) {
					bankItem(playerItems[removeSlot], removeSlot, playerItemsN[removeSlot]);
				} else {
					bankItem(playerItems[removeSlot], removeSlot, itemAmount(playerItems[removeSlot]));
				}
			} else if (interfaceID == 3322) {
				if(Item.itemStackable[removeID]){
					getTrade().tradeItem(removeID, removeSlot, playerItemsN[removeSlot]);
				} else {
					getTrade().tradeItem(removeID, removeSlot, 28);
				}
				
			} else if (interfaceID == 3415) {
				if(Item.itemStackable[removeID]) {
					for (GameItem item : getTrade().offeredItems) {
						if(item.id == removeID) {
							getTrade().fromTrade(removeID, removeSlot, getTrade().offeredItems.get(removeSlot).amount);
						}
					}
				} else {
					for (GameItem item : getTrade().offeredItems) {
						if(item.id == removeID) {
							getTrade().fromTrade(removeID, removeSlot, 28);
						}
					}
				}
			} else if (interfaceID == 7295) {
				if (Item.itemStackable[removeID]) {
					bankItem(playerItems[removeSlot], removeSlot, playerItemsN[removeSlot]);
					resetItems(7423);
				} else {
					bankItem(playerItems[removeSlot], removeSlot, itemAmount(playerItems[removeSlot] - 1));
					resetItems(7423);
				}
			} else if (interfaceID == 5382) {
				fromBank(bankItems[removeSlot], removeSlot, bankItemsN[removeSlot]);
			} else if (interfaceID == 3823) {
				sellItem(removeID, removeSlot, 10);
			} else if (interfaceID == 3900) {
				buyItem(removeID, removeSlot, 10);
			}
			break;
		case 130:
			// Clicking some stuff in game
			interfaceID = inStream.readSignedByte();
			if (inTrade) {
				if(!acceptedTrade) {
					getTrade().declineTrade();
				}
			}
			if (IsShopping == true) {
				IsShopping = false;
				MyShopID = 0;
				UpdateShop = false;
			}
			if (IsBanking == true) {
				IsBanking = false;
			}
			if ((misc.HexToInt(inStream.buffer, 0, packetSize) != 63363)
					&& (misc.HexToInt(inStream.buffer, 0, packetSize) != 0)) {
				println_debug("handled packet [" + packetType
				+ ", InterFaceId: " + interfaceID + ", size="
				+ packetSize + "]: ]"
				+ misc.Hex(inStream.buffer, 1, packetSize) + "[");
				println_debug("Action Button: "
				+ misc.HexToInt(inStream.buffer, 0, packetSize));
			}
			break;
		case 131://magic on npcs
			npcIndex = inStream.readSignedWordBigEndianA();
			npcMagicID = inStream.readSignedWordA();
			int EnemyXx = server.npcHandler.npcs[npcIndex].absX;
			int EnemyYx = server.npcHandler.npcs[npcIndex].absY;
			if (server.npcHandler.npcs[npcIndex].npcType != 2881) {
				if (server.npcHandler.npcs[npcIndex].combatTimer > 0 && server.npcHandler.npcs[npcIndex].playerId != playerId) {
					CAM().sendMessage("This monster is already fighting someone else.");
					WalkTo(0,0);
					break;
				}
			}
			if (GoodDistance(EnemyXx, EnemyYx, absX, absY, 8) == true) {
				isCloseEnough = true;
				WalkTo(0,0);
			} else {
				isCloseEnough = false;
			}
			//server.npcHandler.npcs[npcIndex].combatTimer = 30;
			server.npcHandler.npcs[npcIndex].playerId = playerId;
			server.npcHandler.npcs[npcIndex].RandomWalk = false;
			server.npcHandler.npcs[npcIndex].IsUnderAttack = true;
			if (autoCastNpc) {
				setClientConfig(108, 0);
				autoCastNpc = false;
			}
			getTheCurrentSpell();
			break;
		case 133: // add ignore
			friendUpdate = true;
			long igtoadd = inStream.readQWord();
			for (int i10 = 0; i10 < ignores.length; i10++) {
				if (ignores[i10] == 0) {
					ignores[i10] = igtoadd;
					break;
				}
			}
			break;
		case 135: // bank X items
			outStream.createFrame(27);
			XremoveSlot = inStream.readSignedWordBigEndian();
			XinterfaceID = inStream.readUnsignedWordA();
			XremoveID = inStream.readSignedWordBigEndian();
			Xamount = inStream.readSignedWordBigEndian();
			if(XinterfaceID == 7423) {
				bankItem(playerItems[XremoveSlot], XremoveSlot, Xamount);
				resetItems(7423);
			}	
			break;
		case 139: // Trade answer
			int tradeId = inStream.readSignedWordBigEndian();
			getTrade().requestTrade(tradeId);
			break;
		case 145: // remove item (opposite for wearing) - bank 1 item - value of item
			interfaceID = inStream.readUnsignedWordA();
			removeSlot = inStream.readUnsignedWordA();
			removeID = inStream.readUnsignedWordA();
			if (interfaceID == 1688) {
				if (playerEquipment[removeSlot] > 0) {
					remove(removeID, removeSlot);
				}
				if (removeID == 6889 && QH().q2 == 15) {
					setSidebarInterface(6, 1151);//modern magics
					ancients = 0;
					CAM().sendMessage("You converted to Modern Magics.");
				}
			} else if (interfaceID == 5064) {
				// remove from bag to bank
				bankItem(removeID, removeSlot, 1);
			} else if (interfaceID == 7423) {
				bankItem(removeID, removeSlot, 1);
				resetItems(7423);
			} else if (interfaceID == 5382) {
				// remove from bank
				fromBank(removeID, removeSlot, 1);
			} else if (interfaceID == 3322) {
				getTrade().tradeItem(removeID, removeSlot, 1);
			} else if (interfaceID == 3415) {
				getTrade().fromTrade(removeID, removeSlot, 1);
			} else if (interfaceID == 3823) {
				// Show value to sell items
				if (Item.itemSellable[removeID] == false) {
					CAM().sendMessage("I cannot sell " + getItemName(removeID) + ".");
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
						CAM().sendMessage("You cannot sell " + getItemName(removeID)
						+ " in this store.");
					} else {
						int ShopValue = (int) Math.floor(GetItemShopValue(
						removeID, 1, removeSlot));
						String ShopAdd = "";
						if ((ShopValue >= 1000) && (ShopValue < 1000000)) {
							ShopAdd = " (" + (ShopValue / 1000) + "K)";
						} else if (ShopValue >= 1000000) {
							ShopAdd = " (" + (ShopValue / 1000000)
							+ " million)";
						}
						CAM().sendMessage(getItemName(removeID)
						+ ": shop will buy for " + ShopValue + " coins"
						+ ShopAdd);
					}
				}
			} else if (interfaceID == 3900) {
				// Show value to buy items
				int ShopValue = (int) Math.floor(GetItemShopValue(removeID, 0,
				removeSlot));
				String ShopAdd = "";
				if ((ShopValue >= 1000) && (ShopValue < 1000000)) {
					ShopAdd = " (" + (ShopValue / 1000) + "K)";
				} else if (ShopValue >= 1000000) {
					ShopAdd = " (" + (ShopValue / 1000000) + " million)";
				}
				CAM().sendMessage(getItemName(removeID) + ": currently costs " + ShopValue + " gp" + ShopAdd);
			} else if (interfaceID == 1119) {
				new SmithingMakeItem(this, removeID, 1);
			} else if (interfaceID == 1120) {
				new SmithingMakeItem(this, removeID, 1);
			} else if (interfaceID == 1121) {
				new SmithingMakeItem(this, removeID, 1);
			} else if (interfaceID == 1122) {
				new SmithingMakeItem(this, removeID, 1);
			} else if (interfaceID == 1123) {
				new SmithingMakeItem(this, removeID, 1);
			}
			break;
		case 153: //follow player
			int FollowID = (misc.HexToInt(inStream.buffer, 0, packetSize) / 1000);
			break;
		case 155: // first Click npc
			NPCSlot = inStream.readSignedWordBigEndian();
			if ((NPCSlot < 0) || (NPCSlot >= server.npcHandler.npcs.length) || (server.npcHandler.npcs[NPCSlot] == null))
			break;
			NPCID = server.npcHandler.npcs[NPCSlot].npcType;
			faceNPC(NPCSlot);
			PutNPCCoords = false;
			if (NPCID == 2650) {
				skillX = server.npcHandler.npcs[NPCSlot].absX;
				skillY = server.npcHandler.npcs[NPCSlot].absY;
				NpcWanneTalk = 2650;
			}
			if (NPCID == 579) {
				if (QH().q3 == 0) {
					NpcWanneTalk = 579;
					skillX = server.npcHandler.npcs[NPCSlot].absX;
					skillY = server.npcHandler.npcs[NPCSlot].absY;	
				} else if (QH().q3 == 1) {
					NpcWanneTalk = 590;
					skillX = server.npcHandler.npcs[NPCSlot].absX;
					skillY = server.npcHandler.npcs[NPCSlot].absY;	
				} else if (QH().q3 == 2 && playerHasItem(1273)) {	
					NpcWanneTalk = 5592;
					skillX = server.npcHandler.npcs[NPCSlot].absX;
					skillY = server.npcHandler.npcs[NPCSlot].absY;
				} else if (QH().q3 == 2 && !playerHasItem(1273)) {
					NpcWanneTalk = 590;
					skillX = server.npcHandler.npcs[NPCSlot].absX;
					skillY = server.npcHandler.npcs[NPCSlot].absY;	
				}				
			}
			if (NPCID == 1463) {
				skillX = server.npcHandler.npcs[NPCSlot].absX;
				skillY = server.npcHandler.npcs[NPCSlot].absY;
				if (playerEquipment[playerAmulet] == 4021 && !playerHasItem(4024, 1) && playerHasItem(275, 1)) {
					NpcWanneTalk = 67;
				} else if (playerHasItem(4024, 1)) {
					CAM().sendMessage("This monkey handed over a key!");
					addItem(275, 1);
					deleteItem(4024, 1);
				} else {
					CAM().sendMessage("You need a Monkey Speaking Amulet, to talk to him.");
				}
			} else if (NPCID == 1702) {
				skillX = server.npcHandler.npcs[NPCSlot].absX;
				skillY = server.npcHandler.npcs[NPCSlot].absY;
				NpcWanneTalk = 65;
			} else if (NPCID == 1125) {
				skillX = server.npcHandler.npcs[NPCSlot].absX;
				skillY = server.npcHandler.npcs[NPCSlot].absY;
				NpcWanneTalk = 63;
			} else if (NPCID == 553) {
				skillX = server.npcHandler.npcs[NPCSlot].absX;
				skillY = server.npcHandler.npcs[NPCSlot].absY;
				NpcWanneTalk = 3;
			} else if (NPCID == 1597) {
				skillX = server.npcHandler.npcs[NPCSlot].absX;
				skillY = server.npcHandler.npcs[NPCSlot].absY;
				NpcWanneTalk = 9;
			} else if (NPCID == 2829) {
				if (QH().q2 == 0) {
					skillX = server.npcHandler.npcs[NPCSlot].absX;
					skillY = server.npcHandler.npcs[NPCSlot].absY;
					NpcWanneTalk = 70;
				}
				if (QH().q2 != 4 && QH().q2 != 0 && QH().q2 != 15) {
					skillX = server.npcHandler.npcs[NPCSlot].absX;
					skillY = server.npcHandler.npcs[NPCSlot].absY;
					NpcWanneTalk = 86;
				}
				if (QH().q2 == 4) {
					skillX = server.npcHandler.npcs[NPCSlot].absX;
					skillY = server.npcHandler.npcs[NPCSlot].absY;
					NpcWanneTalk = 87;
				}
				if (QH().q2 == 15 && !BankHasItem(6889, 1) && !playerHasItem(6889, 1)) {
					skillX = server.npcHandler.npcs[NPCSlot].absX;
					skillY = server.npcHandler.npcs[NPCSlot].absY;
					NpcWanneTalk = 96;
				}
				
			} else if (NPCID == 0) {
				if (QH().q1 == 1) {
					skillX = server.npcHandler.npcs[NPCSlot].absX;
					skillY = server.npcHandler.npcs[NPCSlot].absY;
					NpcWanneTalk = 22;
				}
				if (QH().q1 == 2 && playerHasItem(954, 1)) {
					skillX = server.npcHandler.npcs[NPCSlot].absX;
					skillY = server.npcHandler.npcs[NPCSlot].absY;
					NpcWanneTalk = 36;
				}
				if (QH().q1 == 2 && !playerHasItem(954, 1)) {
					skillX = server.npcHandler.npcs[NPCSlot].absX;
					skillY = server.npcHandler.npcs[NPCSlot].absY;
					NpcWanneTalk = 22;
				}
				if (QH().q1 == 3 && !playerHasItem(315, 1)) {
					skillX = server.npcHandler.npcs[NPCSlot].absX;
					skillY = server.npcHandler.npcs[NPCSlot].absY;
					NpcWanneTalk = 61;
				}
				if (QH().q1 == 3 && playerHasItem(315, 1)) {
					skillX = server.npcHandler.npcs[NPCSlot].absX;
					skillY = server.npcHandler.npcs[NPCSlot].absY;
					NpcWanneTalk = 41;
				}
				if (QH().q1 == 4) {
					skillX = server.npcHandler.npcs[NPCSlot].absX;
					skillY = server.npcHandler.npcs[NPCSlot].absY;
					NpcWanneTalk = 62;
				}
				if (QH().q1 == 5) {
					skillX = server.npcHandler.npcs[NPCSlot].absX;
					skillY = server.npcHandler.npcs[NPCSlot].absY;
					NpcWanneTalk = 47;
				}
				if (QH().q1 == 6) {
					skillX = server.npcHandler.npcs[NPCSlot].absX;
					skillY = server.npcHandler.npcs[NPCSlot].absY;
					NpcWanneTalk = 25;
				}
				if (QH().q1 == 7 && !playerHasItem(1527, 1)) {
					skillX = server.npcHandler.npcs[NPCSlot].absX;
					skillY = server.npcHandler.npcs[NPCSlot].absY;
					NpcWanneTalk = 25;
				}
				if (QH().q1 == 7 && playerHasItem(1527, 1)) {
					skillX = server.npcHandler.npcs[NPCSlot].absX;
					skillY = server.npcHandler.npcs[NPCSlot].absY;
					NpcWanneTalk = 26;
				}
				if (QH().q1 == 8) {
					skillX = server.npcHandler.npcs[NPCSlot].absX;
					skillY = server.npcHandler.npcs[NPCSlot].absY;
					NpcWanneTalk = 29;
				}
			} else if (NPCID == 309) {
				if ((System.currentTimeMillis() - lastFish) < fish().calculateFishingDelay(this) || !fish().hasStoppedFishing(this)) {
					break;
				}
				if (!fish().hasStoppedFishing(this)) {
					break;
				}	
				if (playerLevel[10] >= 30) {
					fish().fishingMain(this, playerName, playerId, 30, 309, 1, 314, 1, -1, -1, 622, 4500, 331, 120, 6);
				} else {
					fish().fishingMain(this, playerName, playerId, 20, 309, 1, 314, 1, -1, -1, 622, 4500, 335, 80, 5);
				}
			} else if (NPCID == 330) {	
				if ((System.currentTimeMillis() - lastFish) < fish().calculateFishingDelay(this) || !fish().hasStoppedFishing(this)) {
					break;
				}
				if (!fish().hasStoppedFishing(this)) {
					break;
				}
				if (playerLevel[10] < 15) {
					fish().fishingMain(this, playerName, playerId, 1, 303, 1, -1, -1, -1, -1, 0x26D, 4500, 317, 20, 1);
				} else {
					fish().fishingMain(this, playerName, playerId, 15, 303, 1, -1, -1, -1, -1, 0x26D, 4500, 321, 20, 2);
				}
			} else if (NPCID == 333) {	
				if ((System.currentTimeMillis() - lastFish) < fish().calculateFishingDelay(this) || !fish().hasStoppedFishing(this)) {
					break;
				}
				if (!fish().hasStoppedFishing(this)) {
					break;
				}
				fish().fishingMain(this, playerName, playerId, 40, 301, 1, -1, -1, -1, -1, 619, 4500, 377, 20, 7);
			} else if (NPCID == 334) {	
				if ((System.currentTimeMillis() - lastFish) < fish().calculateFishingDelay(this) || !fish().hasStoppedFishing(this)) {
					break;
				}
				if (!fish().hasStoppedFishing(this)) {
					break;
				}
				if (playerLevel[10] >= 46) {
					fish().fishingMain(this, playerName, playerId, 46, 305, 1, -1, -1, -1, -1, 0x26D, 5000, 363, 60, 13);
				} else if (playerLevel[10] >= 23) {
					fish().fishingMain(this, playerName, playerId, 23, 305, 1, -1, -1, -1, -1, 0x26D, 5000, 341, 60, 12);
				} else {	
					fish().fishingMain(this, playerName, playerId, 16, 305, 1, -1, -1, -1, -1, 0x26D, 5000, 353, 60, 11);
				}
			} else if (NPCID == 15000) {
				CAM().sendMessage("You got forcefully teleported to runcrafting area!");
				teleportToX = 3040;
				teleportToY = 4834;
				heightLevel = 0;
			}
			if (NPCID == 734) {
				WanneShop = 12;
				PutNPCCoords = true;
			}
			if (NPCID == 892) {
				PutNPCCoords = true;
				WanneShop = 2;
			}
			if (NPCID == 553) {
				PutNPCCoords = true;
				WanneShop = 1;
			}
			if (PutNPCCoords == true) {
				skillX = server.npcHandler.npcs[NPCSlot].absX;
				skillY = server.npcHandler.npcs[NPCSlot].absY;
			}
			break;
		case 188:
			// add friend
			friendUpdate = true;
			long friendtoadd = inStream.readQWord();
			boolean CanAdd = true;
			for (long element : friends) {
				if ((element != 0) && (element == friendtoadd)) {
					CanAdd = false;
					CAM().sendMessage(friendtoadd + " is already in your friendlist.");
				} 	
			}
			if (CanAdd == true) {
				for (int i1 = 0; i1 < friends.length; i1++) {
					if (friends[i1] == 0) {
						friends[i1] = friendtoadd;
						for (int i2 = 1; i2 < handler.maxPlayers; i2++) {
							if ((handler.players[i2] != null)
									&& handler.players[i2].isActive
									&& (misc
										.playerNameToInt64(handler.players[i2].playerName) == friendtoadd)) {
								if ((playerRights >= 2)
										|| (handler.players[i2].Privatechat == 0)
										|| ((handler.players[i2].Privatechat == 1) && handler.players[i2]
											.isinpm(misc
												.playerNameToInt64(playerName)))) {
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
		case 192: // Use an item on an object
			int AUTH = inStream.readSignedWordBigEndianA();
			int id = inStream.readUnsignedWordBigEndian();
			int Y = inStream.readSignedWordBigEndianA();
			slot = inStream.readSignedWordBigEndianA() - 128;
			int X = inStream.readUnsignedWordBigEndianA();
			int itemid = inStream.readUnsignedWord();
			if (!playerHasItem(itemid))
			break;
			if (AUTH == -29044)
			{
				if (id == 2781 || id == 2785 || id == 2966 || id == 3044 || id == 3294 || id == 3413 || id == 4304 || id == 4305 || id == 6189 || id == 6190 || id == 11666)
				{
					skillX = X;
					skillY = Y;
					new SmithingSmelt(this, -1, 1, -1, itemid);
				}
				
				if (id == 2782 || id == 2783 || id == 4306 || id == 6150)
				{
					skillX = X;
					skillY = Y;
					TurnPlayerTo(X, Y);
					if (playerHasItem(2347, 1))
					{
						if (itemid == 2349)
						new SmithingMakeInterface(this, id, "BRONZE", X, Y);
						else if (itemid == 2351)
						new SmithingMakeInterface(this, id, "IRON", X, Y);
						else if (itemid == 2359)
						new SmithingMakeInterface(this, id, "MITH", X, Y);
						else if (itemid == 2353)
						new SmithingMakeInterface(this, id, "STEEL", X, Y);
						else if (itemid == 2361)
						new SmithingMakeInterface(this, id, "ADDY", X, Y);
						else if (itemid == 2363)
						new SmithingMakeInterface(this, id, "RUNE", X, Y);
						else
						CAM().sendMessage("You can't use this item on an anvil!");		
					}
					else
					{
						CAM().sendMessage("You need a hammer to make items!");
					}
				}
			}
			else
			{
				CAM().sendMessage("Wrong auth, nice packet spoof.");
			}
			if (id == 4577) {
				if (itemid == 275) {
					teleportToX = absX;
					teleportToY = absY+2;
					updateRequired = true;
					appearanceUpdateRequired = true;
					deleteItem(275, 1);
					CAM().sendMessage("The key crumbled!");
				}
			}
			if (id == 2732) {
				COOKING_SPEED = 2500;
				TurnPlayerTo(X, Y);
				if (GoodDistance(X, Y, absX, absY, 1) == true) {
					if (itemid == 317) {  // shrimps lvl 1
						Cooking().cookingMain(this, playerName, playerId, 1, 317, 1, 897, 317);
					}
					if (itemid == 327) { // sardine lvl 5
						Cooking().cookingMain(this, playerName, playerId, 1, 327, 1, 897, 327);
					}
					if (itemid == 345) { // herring lvl 10
						Cooking().cookingMain(this, playerName, playerId, 5, 345, 1, 897, 345);
					}
					if (itemid == 321) {// anchovie lvl 15
						Cooking().cookingMain(this, playerName, playerId, 1, 321, 1, 897, 321);
					}
					if (itemid == 353) {  // Mackerel lvl 16
						Cooking().cookingMain(this, playerName, playerId, 10, 353, 1, 897, 353);
					}
					if (itemid == 335) { // trout lvl 20
						Cooking().cookingMain(this, playerName, playerId, 15, 335, 1, 897, 335);
					}
					if (itemid == 341) { // cod lvl 23
						Cooking().cookingMain(this, playerName, playerId, 18, 341, 1, 897, 341);
					}
					if (itemid == 331) { // salmon lvl 30
						Cooking().cookingMain(this, playerName, playerId, 25, 331, 1, 897, 331);
					}
					if (itemid == 359) { // tuna lvl 35
						Cooking().cookingMain(this, playerName, playerId, 30, 359, 1, 897, 359);
					}
					if (itemid == 377) { // lobster lvl 40
						Cooking().cookingMain(this, playerName, playerId, 40, 377, 1, 897, 377);
					}
					if (itemid == 371) { // swordfish lvl 45
						Cooking().cookingMain(this, playerName, playerId, 45, 371, 1, 897, 371);
					}
					if (itemid == 363) { // bass lvl 46 big net
						Cooking().cookingMain(this, playerName, playerId, 43, 363, 1, 897, 363);
					}
					if (itemid == 383) { // shark lvl 76 harpoon
						Cooking().cookingMain(this, playerName, playerId, 80, 383, 1, 897, 383);
					}
				}
			}
			if (id == 2728) {
				COOKING_SPEED = 3500;
				TurnPlayerTo(X, Y);
				if (GoodDistance(X, Y, absX, absY, 1) == true) {
					if (itemid == 317) {  // shrimps lvl 1
						Cooking().cookingMain(this, playerName, playerId, 1, 317, 1, 883, 317);
					}
					if (itemid == 327) { // sardine lvl 5
						Cooking().cookingMain(this, playerName, playerId, 1, 327, 1, 883, 327);
					}
					if (itemid == 345) { // herring lvl 10
						Cooking().cookingMain(this, playerName, playerId, 5, 345, 1, 883, 345);
					}
					if (itemid == 321) {// anchovie lvl 15
						Cooking().cookingMain(this, playerName, playerId, 1, 321, 1, 883, 321);
					}
					if (itemid == 353) {  // Mackerel lvl 16
						Cooking().cookingMain(this, playerName, playerId, 10, 353, 1, 883, 353);
					}
					if (itemid == 335) { // trout lvl 20
						Cooking().cookingMain(this, playerName, playerId, 15, 335, 1, 883, 335);
					}
					if (itemid == 341) { // cod lvl 23
						Cooking().cookingMain(this, playerName, playerId, 18, 341, 1, 883, 341);
					}
					if (itemid == 331) { // salmon lvl 30
						Cooking().cookingMain(this, playerName, playerId, 25, 331, 1, 883, 331);
					}
					if (itemid == 359) { // tuna lvl 35
						Cooking().cookingMain(this, playerName, playerId, 30, 359, 1, 883, 359);
					}
					if (itemid == 377) { // lobster lvl 40
						Cooking().cookingMain(this, playerName, playerId, 40, 377, 1, 883, 377);
					}
					if (itemid == 371) { // swordfish lvl 45
						Cooking().cookingMain(this, playerName, playerId, 45, 371, 1, 883, 371);
					}
					if (itemid == 363) { // bass lvl 46 big net
						Cooking().cookingMain(this, playerName, playerId, 63, 363, 1, 883, 363);
					}
					if (itemid == 383) { // shark lvl 76 harpoon
						Cooking().cookingMain(this, playerName, playerId, 80, 383, 1, 883, 383);
					}
				}
			}
			break;
		case 199: // fags using xero's client
			break;
		case 202: // idle logout packet - ignore for now
			SG().savegame(this, true);
			break;
		case 208: // Enter Amounth Part 2
			int XremoveSlot = inStream.readSignedWordBigEndian();
			int XinterfaceID = inStream.readUnsignedWordA();
			int XremoveID = inStream.readSignedWordBigEndian();
			int Xamount = inStream.readDWord();
			int EnteredAmount = inStream.readDWord();
			if (EnteredAmount < 1)
			break;
			if (XinterfaceID == 5064) {
				bankItem(playerItems[XremoveSlot], XremoveSlot, EnteredAmount);
			} else if (XinterfaceID == 5382) {
				fromBank(bankItems[XremoveSlot], XremoveSlot, EnteredAmount);
			} else if (XinterfaceID == 3322) {
				getTrade().tradeItem(XremoveID, XremoveSlot, EnteredAmount);
			} else if (XinterfaceID == 3415) {
				getTrade().fromTrade(XremoveID, XremoveSlot, EnteredAmount);
			} else if (XinterfaceID == 3900) { //Shop
				if (EnteredAmount < 10000)
				buyItem(XremoveID, XremoveSlot, EnteredAmount);
				else
				CAM().sendMessage("You cannot buy more than 10k items at a time.");
			}
			break;
		case 210:
			break;
		case 214: // change item places
			somejunk = inStream.readUnsignedWordA(); // junk
			int itemFrom = inStream.readUnsignedWordA(); // slot1
			int itemTo = (inStream.readUnsignedWordA() - 128); // slot2
			moveItems(itemFrom, itemTo, somejunk);
			break;
		case 215:
			// remove friend
			friendUpdate = true;
			long friendtorem = inStream.readQWord();
			for (int i1 = 0; i1 < friends.length; i1++) {
				if (friends[i1] == friendtorem) {
					friends[i1] = 0;
					break;
				}
			}
			break;
		case 218:
			String abuser = misc.longToPlayerName(inStream.readQWord());
			int rule = inStream.readUnsignedByte();
			int mute = inStream.readUnsignedByte();
			reportAbuse(abuser, rule, mute);
			break;
		case 236: // pickup item
			pItemY = inStream.readSignedWordBigEndian();
			pItemId = inStream.readUnsignedWord();
			pItemX = inStream.readSignedWordBigEndian();
			if (freeSlots() < 1) {
				CAM().sendMessage("You have no room in your inventory.");
				break;
			}
			int itemAmount = ItemHandler.itemAmount(pItemId, pItemX, pItemY);
			if (ClickCount <= 2) {
				ClickCount += 4;
				if(absX == pItemX && absY == pItemY) {
					addItem(pItemId, itemAmount);
					ItemHandler.removeItem(pItemId, pItemX, pItemY, itemAmount);	
				} else {
					walkingToItem = true;
				}
			}
			break;
		case 237: // Magic on Items
			break;
		case 241:
			// mouse clicks
			int in = inStream.readDWord();
			lastMouse = System.currentTimeMillis();
			boolean validClick = false;
			long diff = System.currentTimeMillis() - lastMouse;
			if (diff < 100) {
				validClick = true;
			}
			break;
			
			
		case 249: // Magic on Players
			playerIndex = inStream.readSignedWordA();
			followThisPlayer = playerIndex;
			playerMagicID = inStream.readSignedWordBigEndian();
			int EnemyX = PlayerHandler.players[followThisPlayer].absX;
			int EnemyY = PlayerHandler.players[followThisPlayer].absY;
			client playerIndex2 = (client) server.playerHandler.players[playerIndex];
			faceNPC(followThisPlayer);
				if (isInWilderness(EnemyX, EnemyY, 1) == true && isInWilderness(absX, absY, 1) == false) {
					CAM().sendMessage("You cannot attack players outside of the wilderness.");
					break;
				}
				if (isInWilderness(EnemyX, EnemyY, 1) == false && isInWilderness(absX, absY, 1) == true) {
					CAM().sendMessage("You cannot attack players outside of the wilderness.");
					break;
				}
				if (isInWilderness(EnemyX, EnemyY, 1) == false && isInWilderness(absX, absY, 1) == false) {
					CAM().sendMessage("You cannot attack players outside of the wilderness");
					break;
				}
				if (isInWilderness(EnemyX, EnemyY, 1) == false && isInWilderness(absX, absY, 1) == true) {
					CAM().sendMessage("You cannot attack players outside of the wilderness");
					break;
				}
				if (playerIndex2.combatLevel + playerIndex2.wildyLevel < combatLevel || combatLevel + playerIndex2.wildyLevel < playerIndex2.combatLevel) {
					CAM().sendMessage("You and your opponent have to greater level difference for this area.");
					break;
				}
			if (GoodDistance(EnemyX, EnemyY, absX, absY, 8) == true) {
				WalkTo(0,0);
			}
			if (isInWilderness(EnemyX, EnemyY, 1) == false) {
				CAM().sendMessage("You cannot attack players outside of the wilderness!");
				ResetAttack();
				isCloseEnough = false;
				WalkTo(0,0);
				break;
			}
			if (!mC().checkPlayersAreas()) {
				WalkTo(0,0);
				break;
			}
			if (playerIndex2.deathStage > 0) {
				break;
			}
			getTheCurrentSpellPVP();
			if (deathStage > 0) {
				break;
			}
			if (playerIndex2.lastDamageTaken - System.currentTimeMillis() < -5000) {
				playerIndex2.AttackingOn = playerId;
				AttackingOn = playerIndex2.playerId;
			}
			if (playerIndex2.AttackingOn != playerId && playerIndex2.AttackingOn != 0 && !playerIndex2.multiZone()) {
				CAM().sendMessage("This player is already in combat.");
				ResetAttack();
				break;
			}
			if (autoCast) {
				setClientConfig(108, 0);
				autoCast = false;
			}
			break;
		case 132: // first click objects
			int objectXz = inStream.readSignedWordBigEndianA();
			int objectIDz = inStream.readUnsignedWord();
			int objectYz = inStream.readUnsignedWordA();
			ATS().AtObjects1(this, objectIDz, objectXz, objectYz);
			break;
		case 252: // atobject2
			int objectID = inStream.readUnsignedWordBigEndianA();
			int objectY = inStream.readSignedWordBigEndian();
			int objectX = inStream.readUnsignedWordA();
			ATS().AtObjects2(this, objectID, objectX, objectY);
			break;
		case 70:
			// atObject3
			objectXz = inStream.readSignedWordBigEndian();
			objectYz = inStream.readUnsignedWord();
			objectIDz = inStream.readUnsignedWordBigEndianA();
			ATS().AtObjects3(this, objectIDz, objectXz, objectYz);
		case 185:
			if (getStatus().isBusy()) {
				break;
			}
			actionButtonId = misc.HexToInt(inStream.buffer, 0, packetSize);
			if (!validClient)
			break;
			//if (!antiHax())
			//	break
			AB().ActionButtons(this, actionButtonId);
			break;
			
			
		default:
			interfaceID = inStream.readUnsignedWordA();
			int actionButtonId1 = misc.HexToInt(inStream.buffer, 0, packetSize);
			
		}
		//CAM().sendMessage("packetType: "+ packetType +".");
		
	}	
	public boolean playerHasItem(int itemID, int amt) {
		itemID++;
		int found = 0;
		for (int i = 0; i < playerItems.length; i++) {
			if (playerItems[i] == itemID) {
				if (playerItemsN[i] >= amt)
				return true;
				else
				found++;
			}
		}
		if (found >= amt)
		return true;
		return false;
	}
	public boolean BankHasItem(int itemID, int amt) {	
		itemID++;
		int found = 0;
		for(int i = 0; i < bankItems.length; i++) {
			if (bankItems[i] == itemID) {
				if (bankItemsN[i] >= amt)
				return true;
				else
				found++;
			}
		}
		if (found >= amt)
		return true;
		return false;
	}
	public boolean playerHasItem(int itemID) {
		int amt = 0;
		itemID++;
		int found = 0;
		for (int i = 0; i < playerItems.length; i++) {
			if (playerItems[i] == itemID) {
				if (playerItemsN[i] >= amt)
				return true;
				else
				found++;
			}
		}
		if (found >= amt)
		return true;
		return false;
	}
	public void pmstatus(int status) {
		// status: loading = 0 connecting = 1 fine = 2
		outStream.createFrame(221);
		outStream.writeByte(status);
	}
	public void pmupdate(int pmid, int world) {
		if ((handler.players[pmid] == null)
				|| (handler.players[pmid].playerName == null)) {
			return;
		}
		long l = misc.playerNameToInt64(handler.players[pmid].playerName);
		if (handler.players[pmid].Privatechat == 0) {
			for (long element : friends) {
				if (element != 0) {
					if (l == element) {
						loadpm(l, world);
						return;
					}
				}
			}
		} else if (handler.players[pmid].Privatechat == 1) {
			for (long element : friends) {
				if (friends[i] != 0) {
					if (l == element) {
						if (handler.players[pmid].isinpm(misc
									.playerNameToInt64(playerName))
								&& (playerRights > 2)) {
							loadpm(l, world);
							return;
						} else {
							loadpm(l, 0);
							return;
						}
					}
				}
			}
		} else if (handler.players[pmid].Privatechat == 2) {
			for (long element : friends) {
				if (friends[i] != 0) {
					if ((l == element) && (playerRights < 2)) {
						loadpm(l, 0);
						return;
					}
				}
			}
		}
	}	
	public boolean FullDharokEquipped() {
		if(playerEquipment[playerHat] == 4716 && playerEquipment[playerChest] == 4720 && playerEquipment[playerLegs] == 4722 && playerEquipment[playerWeapon] == 4718)
		{
			return true;
		}
		return false;			
	}
	public boolean npcMeleeDamage() {
		if (damageThisNPC < 1) {
			ResetAttackNPC();
			return false;
		}
		if (attacknpc < 1) {
			return false;
		}
		NPC i_ = server.npcHandler.npcs[attacknpc];
		
		if (FullDharokEquipped() && misc.random(2) == 1) {
			getDharokBonus();
		}
		int i = misc.random(playerMaxHit + extraDamage);	
		int T = i_.npcType;	
		int X = i_.absX;
		int Y = i_.absY;
		int HP = i_.HP;
		int D = misc.random(server.npcHandler.npcs[attacknpc].npcDefence);
		int A = misc.random(playerBonus[CheckBestBonuz()] + playerLevel[0] / 2);
		int F = 0;
		if (HP == 0) {
			return false;
		}
		if (i >= HP) {
			i = HP;
		}
		if (X == absX && Y == absY) {	
			WalkTo(0,-1);
		}
		i_.Killing[playerId] += 1;
		
		if (A > D) {
			acceptHit(i);
		} else {
			declineHit(0);
		}
		return true;
	}
	public boolean acceptHit(int i) {
		if (server.npcHandler.npcs[damageThisNPC] == null) {
			return false;
		}
		NPC i_ = server.npcHandler.npcs[damageThisNPC];
		int T = i_.npcType;	
		int X = i_.absX;
		int Y = i_.absY;
		int HP = i_.HP;//here
		if (!i_.hitUpdateRequired) {
			i_.hit = true;
			i_.hitDiff = i;
			i_.hitUpdateRequired = true;
		} else {
			i_.hit2 = true;
			i_.hitDiff2 = i;
			i_.hitUpdateRequired2 = true;
		}
		i_.animNumber = server.NpcAnimHandler.block[T];
		i_.animUpdateRequired = true;
		i_.Killing[playerId] += i;
		i_.IsUnderAttack = true;
		i_.RandomWalk = false;
		i_.updateRequired = true;
		i_.playerName = "playerName";
		i_.playerId = playerId;
		i_.inCombat = true;
		i_.combatTimer = 30;
		addSkillXPmelee(i * 70 * server.SERVER_EXPERIENCE, SkillID);
		addSkillXPmelee(i * 25 * server.SERVER_EXPERIENCE, playerHitpoints);
		return true;
	}
	public boolean acceptHitRanged(int i) {
		if (server.npcHandler.npcs[damageThisNPC] == null) {
			ResetAttackNPC();
			return false;
		}
		NPC i_ = server.npcHandler.npcs[damageThisNPC];
		int T = i_.npcType;	
		int X = i_.absX;
		int Y = i_.absY;
		int HP = i_.HP;
		if (!i_.hitUpdateRequired) {
			i_.hit = true;
			i_.hitDiff = i;
			i_.hitUpdateRequired = true;
		} else {
			i_.hit2 = true;
			i_.hitDiff2 = i;
			i_.hitUpdateRequired2 = true;
		}
		i_.animNumber = server.NpcAnimHandler.block[T];
		i_.animUpdateRequired = true;
		i_.Killing[playerId] += 1;
		i_.IsUnderAttack = true;
		i_.RandomWalk = false;
		i_.playerName = "playerName";
		i_.playerId = playerId;
		i_.inCombat = true;
		i_.combatTimer = 30;
		i_.updateRequired = true;
		addSkillXPmelee(i * 70 * server.SERVER_EXPERIENCE, 4);
		addSkillXPmelee(i * 25 * server.SERVER_EXPERIENCE, playerHitpoints);
		return true;
	}
	public boolean declineHit(int i) {
		if (server.npcHandler.npcs[damageThisNPC] == null) {
			return false;
		}
		NPC i_ = server.npcHandler.npcs[damageThisNPC];
		int T = i_.npcType;	
		if (!i_.hitUpdateRequired) {
			i_.hit = true;
			i_.hitDiff = i;
			i_.hitUpdateRequired = true;
		} else {
			i_.hit2 = true;
			i_.hitDiff2 = i;
			i_.hitUpdateRequired2 = true;
		}
		i_.animNumber = server.NpcAnimHandler.block[T];
		i_.animUpdateRequired = true;
		i_.Killing[playerId] += i;//do not change this
		i_.IsUnderAttack = true;
		i_.RandomWalk = false;
		i_.playerName = "playerName";
		i_.playerId = playerId;
		i_.inCombat = true;
		i_.combatTimer = 30;
		i_.updateRequired = true;
		return true;
		
		
	}
	public boolean npcRangedDamage() {//hereee
		if (damageThisNPC < 1) {
			ResetAttackNPC();
			return false;
		}
		NPC i_ = server.npcHandler.npcs[damageThisNPC];
		int i = misc.random(maxRangeHit());
		int T = i_.npcType;	
		int X = i_.absX;
		int Y = i_.absY;
		int HP = i_.HP;
		int D = misc.random(server.npcHandler.npcs[damageThisNPC].npcDefence);
		int A = misc.random((playerBonus[4] + playerLevel[4]) / 2);
		if (X == absX && Y == absY) {	
			WalkTo(0,-1);
		}
		if (misc.random(2) == 1) {
			DropArrowsNPC();
		}
		if (HP == 0)
			return false;
		if (i >= HP)
		i = HP;
		i_.Killing[playerId] += 1;
		if (A > D) {
			acceptHitRanged(i);	
		} else {
			declineHit(0);
		}
		return true;
	}
	public int getPrayerStr() {
		int outcome = 0;
		int StrBonus = playerBonus[10]; // Strength Bonus
		int Strength = playerLevel[playerStrength]; // Strength
		if (strLow) {
			outcome += (double) (0.5 + (double) ((double) (StrBonus * Strength) * 0.00030));
		} else if (strMid) {
			outcome += (double) (0.5 + (double) ((double) (StrBonus * Strength) * 0.00040));
		} else if (strHigh) {
			outcome += (double) (0.5 + (double) ((double) (StrBonus * Strength) * 0.00050));
		}
		return outcome;
	}
	public int getPrayerDef() {
		int outcome = 0;
		int DefBonus = playerBonus[6]; // Strength Bonus
		int defence = playerLevel[1]; // Strength
		if (defLow) {
			outcome += (double) (0.5 + (double) ((double) (DefBonus * defence) * 0.00030));
		} else if (defMid) {
			outcome += (double) (0.5 + (double) ((double) (DefBonus * defence) * 0.00040));
		} else if (defHigh) {
			outcome += (double) (0.5 + (double) ((double) (DefBonus * defence) * 0.00050));
		}
		return outcome;
	}
	public int getPrayerAtt() {
		int outcome = 0;
		int AttBonus = playerBonus[0]; // Strength Bonus
		int Attack = playerLevel[0]; // attack
		if (atkLow) {
			outcome += (double) (0.5 + (double) ((double) (AttBonus * Attack) * 0.00030));
		} else if (atkMid) {
			outcome += (double) (0.5 + (double) ((double) (AttBonus * Attack) * 0.00040));
		} else if (atkHigh) {
			outcome += (double) (0.5 + (double) ((double) (AttBonus * Attack) * 0.00050));
		}
		return outcome;
	}
	public boolean playerMeleeDamage() {
		if (followThisPlayer < 1) {
			stopAttack = true;
			ResetAttack();
			return false;
		}
		
		client AttackingOn2 = (client) server.playerHandler.players[followThisPlayer];
		AttackingOn2.lastDamageTaken = System.currentTimeMillis();
		int MAXHIT = 0;
		int EnemyX = PlayerHandler.players[followThisPlayer].absX;
		int EnemyY = PlayerHandler.players[followThisPlayer].absY;
		if (isInWilderness(EnemyX,EnemyY, 1) == false) {
			return false;
		}
		if (AttackingOn2.inTeleport) {
			return false;
		}
		int EnemyHP = PlayerHandler.players[followThisPlayer].currentHealth;
		int aBonus = 0;
		int dBonus = 0;
		int xtra = 0;
		int rand_att = misc.random(playerLevel[0]) + getPrayerAtt();
		int rand_def = (int) (misc.random(AttackingOn2.playerLevel[1]) + AttackingOn2.getPrayerDef());
		if (FightType == 2) {
			xtra = 1;
		}
		if (FullDharokEquipped() && misc.random(1) == 1) {
			getDharokBonus();
		}
		if (rand_att > rand_def) {
			MAXHIT = misc.random(playerMaxHit + extraDamage + getPrayerStr() + xtra);	
			if (AttackingOn2.vengOn) {
				vengDamage = (MAXHIT / 2 + MAXHIT / 4);
			}
			if (PlayerHandler.players[followThisPlayer].protMelee) {
				MAXHIT = ((MAXHIT / 2) + (MAXHIT / 4));
			}
		} else {
			MAXHIT = 0;
		}
		if (MAXHIT > EnemyHP) {
			MAXHIT = EnemyHP;
			if (AttackingOn2.vengOn == true) {
				AttackingOn2.plrText = "Taste Vengence!";
                                AttackingOn2.plrTextUpdateRequired = true;
				AttackingOn2.vengOn = false;
				vengDamage = 0;
				if (hitUpdateRequired) {
					hitDiff2 = 0;
					hitUpdateRequired2 = true;
					updateRequired = true;
					appearanceUpdateRequired = true;
				} else {
					hitDiff = 0;
					hitUpdateRequired = true;
					updateRequired = true;
					appearanceUpdateRequired = true;
				}
			}
				
		}
		if (EnemyHP == 0 || currentHealth == 0 || inTeleport || AttackingOn2.inTeleport) {
			MAXHIT = 0;
			stopAttack = true;
			return false;
		}
		AttackingOn2.inCombat();
		if (MAXHIT > 44 && !FullDharokEquipped()) {
			MAXHIT = 44;
		}
		if (MAXHIT > 0) {
			if (AttackingOn2.vengOn) {
				AttackingOn2.plrText = "Taste Vengence!";
                                AttackingOn2.plrTextUpdateRequired = true;
				AttackingOn2.vengOn = false;
				if (hitUpdateRequired) {
					hitDiff2 = vengDamage;
					dealDamage(vengDamage);
					killers[localId] += vengDamage;
					hits++;
					fighting = true;
					fightId = localId;
					hitUpdateRequired2 = true;
					updateRequired = true;
					appearanceUpdateRequired = true;
				} else {
					hitDiff = vengDamage;
					dealDamage(vengDamage);
					killers[localId] += vengDamage;
					hits++;
					fighting = true;
					fightId = localId;
					hitUpdateRequired = true;
					updateRequired = true;
					appearanceUpdateRequired = true;
				}
			}
			if (AttackingOn2.playerEquipment[playerRing] == 2550) {
				if (MAXHIT >= 1 && MAXHIT <= 7) {
					recoilDamage = 1;
				} else if (MAXHIT >= 8 && MAXHIT <= 14) {
					recoilDamage = 2;
				} else if (MAXHIT >= 15 && MAXHIT <= 21) {
					recoilDamage = 3;
				} else if (MAXHIT >= 22 && MAXHIT <= 28) {
					recoilDamage = 5;
				} else if (MAXHIT >= 29 && MAXHIT <= 35) {
					recoilDamage = 6;
				} else if (MAXHIT > 35) {
					recoilDamage = 7;
				}
				if (recoilDamage > 0) {
					if (AttackingOn2.currentHealth == 0) {
						recoilDamage = 0;
					}
					if (recoilDamage > currentHealth) {
						recoilDamage = currentHealth;
					}
					if (hitUpdateRequired) {
						hitDiff2 = recoilDamage;
						dealDamage(recoilDamage);
						killers[localId] += recoilDamage;
						hits++;
						fighting = true;
						fightId = localId;
						hitUpdateRequired2 = true;
						updateRequired = true;
						appearanceUpdateRequired = true;
					} else {
						hitDiff = recoilDamage;
						dealDamage(recoilDamage);
						killers[localId] += recoilDamage;
						hits++;
						fighting = true;
						fightId = localId;
						hitUpdateRequired = true;
						updateRequired = true;
						appearanceUpdateRequired = true;
					}
					if (AttackingOn2.recoilPercent >= 100) {
						if (misc.random(5) == 5) {
							AttackingOn2.removeRing(2550, playerRing);
							AttackingOn2.CAM().sendMessage("You ring crumbles to dust.");
						}
					}
					if (AttackingOn2.recoilPercent < 100) {
						AttackingOn2.recoilPercent = (AttackingOn2.recoilPercent + 10);
					}
				}
				recoilDamage = 0;
			}
			
		}
		if (!PlayerHandler.players[followThisPlayer].hitUpdateRequired) {
			PlayerHandler.players[followThisPlayer].hitUpdateRequired = true;
			PlayerHandler.players[followThisPlayer].updateRequired = true;
			PlayerHandler.players[followThisPlayer].appearanceUpdateRequired = true;
			PlayerHandler.players[followThisPlayer].dealDamage(MAXHIT);
			PlayerHandler.players[followThisPlayer].hitDiff = MAXHIT;
			PlayerHandler.players[followThisPlayer].killers[localId] += MAXHIT;
			PlayerHandler.players[followThisPlayer].hits++;
			PlayerHandler.players[followThisPlayer].fighting = true;
			PlayerHandler.players[followThisPlayer].fightId = localId;
			PlayerHandler.players[followThisPlayer].isInCombat = true;
		} else {
			PlayerHandler.players[followThisPlayer].hitUpdateRequired2 = true;
			PlayerHandler.players[followThisPlayer].updateRequired = true;
			PlayerHandler.players[followThisPlayer].appearanceUpdateRequired = true;
			PlayerHandler.players[followThisPlayer].dealDamage(MAXHIT);
			PlayerHandler.players[followThisPlayer].hitDiff2 = MAXHIT;
			PlayerHandler.players[followThisPlayer].killers[localId] += MAXHIT;
			PlayerHandler.players[followThisPlayer].hits++;
			PlayerHandler.players[followThisPlayer].fighting = true;
			PlayerHandler.players[followThisPlayer].fightId = localId;
			PlayerHandler.players[followThisPlayer].isInCombat = true;
		}	
		addSkillXPmelee(MAXHIT * 70 * server.SERVER_EXPERIENCE, SkillID);
		addSkillXPmelee(MAXHIT * 25 * server.SERVER_EXPERIENCE, playerHitpoints);
		extraDamage = 0;
		xtra = 0;
		return true;
	}
	public void removeRing(int wearID, int slot) {
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
		updateRequired = true;
		appearanceUpdateRequired = true;
	}
	public void wildysigndisappear() {
		CAM().sendFrame126("", 13589);
		CAM().sendFrame126("", 13590);
		CAM().sendFrame126("", 13591);
		CAM().sendFrame126("", 13592);
		CAM().sendFrame126("", 13593);
		CAM().sendFrame126("", 13594);
		CAM().sendFrame126("", 13595);
		CAM().sendFrame126("", 13596);
		CAM().sendFrame126("", 13597);
		CAM().sendFrame126("", 13598);
		CAM().sendFrame126("", 13599);
		CAM().sendFrame126("", 13600);
		setInterfaceWalkable(13588);
		appearanceUpdateRequired = true;
		updateRequired = true;
	}
	public boolean premiumItem(int id) {
		if (((id >= 2583) && (id <= 2630)) || (id == 1037) || (id == 1419)
				|| (id == 4675) || (id == 4676)) {
			return true;
		}
		return false;
	}
	public void println(String str) {
		System.out.println("["+ playerName + "]: " + str);
	}
	public void println_debug(String str) {
		System.out.println("["+ playerName + "]: " + str);
	}
	public void castEmote(int emoteID, int refreshTime) {
		refreshTime = 0;
		startAnimation(emoteID);
		updateRequired = true;
		appearanceUpdateRequired = true;
		
	}
	public boolean multiZone() {
		return absX >= 3287 && absX <= 3298 && absY >= 3167 && absY <= 3178 || absX >= 3070 && absX <= 3095 && absY >= 3405 && absY <= 3448 || absX >= 2961 && absX <= 2981 && absY >= 3330 && absY <= 3354 || absX >= 2510 && absX <= 2537 && absY >= 4632 && absY <= 4660 || absX >= 3012 && absX <= 3066 && absY >= 4805 && absY <= 4858 || absX >= 2794 && absX <= 2813 && absY >= 9281 && absY <= 9305 || absX >= 3546 && absX <= 3557 && absY >= 9689 && absY <= 9700 || absX >= 2708 && absX <= 2729 && absY >= 9801 && absY <= 9829 || absX >= 3450 && absX <= 3525 && absY >= 9470 && absY <= 9535 || absX >= 3207 && absX <= 3395 && absY >= 3904 && absY <= 3903 || absX >= 3006 && absX <= 3072 && absY >= 3611 && absY <= 3712 || absX >= 3149 && absX <= 3395 && absY >= 3520 && absY <= 4000 || absX >= 2365 && absX <= 2420 && absY >= 5065 && absY <= 5120 || absX >= 2890 && absX <= 2935 && absY >= 4425 && absY <= 4470 || absX >= 2250 && absX <= 2290 && absY >= 4675 && absY <= 4715 || absX >= 2690 && absX <= 2825 && absY >= 2680 && absY <=2810;
	}
	public int searchForPoisonDamageAmount() {
		switch(playerEquipment[playerWeapon]) {
		case 5698:
			return 6;
		}
		return -1;
	}
	public void resetOtherAtk() {	
		for(int i = 1; i < killers.length; i++) {
			killers[i] = 0;
		}
	}
	public void endTele() {
		if (ancients == 0) {
			setAnimation(715);
		} else if (ancients == 1) {
			gfx0(455);
		}
		resetWalkingQueue();
	}
	public void startTeleGrahpics() {
		if (ancients == 0)
		arrowPullBack(111);
		else if (ancients == 1)
		gfx0(392);
	}	
	public void checkDamages() {
		if (skullTimer > 0) {
			skullTimer --;
		}
		if (skullTimer == 0 && isSkulled) {
			isSkulled = false;
			getHead();
			skullTimer--;
		}
		if (LogoutDelay > 0) {
        		LogoutDelay--;
		}
		if ((attackDelay > 0 ? --attackDelay : 0) == 0 && melee1 > 0) {
			if (followThisPlayer > 0) 
				playerMeleeDamage();
			else 
				npcMeleeDamage();
			melee1 = 0;
		}
		if ((attackDelay2 > 0 ? --attackDelay2 : 0) == 0 && melee2 > 0) {
			if (followThisPlayer > 0) 
				playerMeleeDamage();
			else 
				npcMeleeDamage();
			melee2 = 0;
		}
		if ((attackDelay3 > 0 ? --attackDelay3 : 0) == 0 && range1 > 0) {
			if (followThisPlayer > 0) 
				playerRangedDmg();
			else 
				npcRangedDamage();
			range1 = 0;
		}
		if ((attackDelay4 > 0 ? --attackDelay4 : 0) == 0 && range2 > 0) {
			if (followThisPlayer > 0) 
				playerRangedDmg();
			else 
				npcRangedDamage();
			range2 = 0;
		}
		if (isFrozen) {
			WalkTo(0,0);
		}
		if (delayMovement > 0) --delayMovement;
		if(isFrozen && delayMovement == 1) {
			isFrozen = false;
		}
		if (runningTeleport && teleportThis == 0) {
			runningTeleport = false;
			startTeleGrahpics();
		}
		if (runningTeleport2 && teleportThis2 == 2) {
			teleportToX = TX;
			teleportToY = TY;
			inTeleport = false;
			updateRequired = true;
			appearanceUpdateRequired = true;	
		}
		if (runningTeleport2 && teleportThis2 == 0) {
			runningTeleport2 = false;
			endTele();
		}
		if (knive && kniveDelay == 0) {
			startAnimation(CAM().GetWepAnim());
			knive = false;
		}
	}	

	public boolean process()  {
		try {
			
			if (timeOutCounter++ > 20) {
				disconnected = true;
				if (saveNeeded)
				SG().savegame(this, true);
				return false;
			}
			if (in == null) {
				return false;
			}
			int avail = in.available();
			if (avail == 0) {
				return false;
			}
			if (packetType == -1) {
				packetType = in.read() & 0xff;
				if (inStreamDecryption != null) {
					packetType = packetType - inStreamDecryption.getNextKey() & 0xff;
				}
				packetSize = packetSizes[packetType];
				avail--;
			}
			if (packetSize == -1) {
				if (avail > 0) {
					packetSize = in.read() & 0xff;
					avail--;
				} else {
					return false;
				}
			}
			if (avail < packetSize) {
				return false;
			}
			fillInStream(packetSize);
			timeOutCounter = 0; // reset
			parseIncomingPackets();
			packetType = -1;
		} catch (java.lang.Exception __ex) {
			__ex.printStackTrace();
			System.out.println("Process error caught.");
			SG().savegame(this, true);
			disconnected = true;
		}
		return true;
	}
	public int randomItem(int[] array) {
		int len = array.length;
		int ran = misc.random(len);
		return array[ran];
	}
	public void refreshSkills() {
		CAM().sendQuest("" + playerLevel[0] + "", 4004);
		CAM().sendQuest("" + playerLevel[2] + "", 4006);
		CAM().sendQuest("" + playerLevel[1] + "", 4008);
		CAM().sendQuest("" + playerLevel[4] + "", 4010);
		CAM().sendQuest("" + playerLevel[5] + "", 4012);
		CAM().sendQuest("" + playerLevel[6] + "", 4014);
		CAM().sendQuest("" + currentHealth + "", 4016);
		CAM().sendQuest("" + playerLevel[16] + "", 4018);
		CAM().sendQuest("" + playerLevel[15] + "", 4020);
		CAM().sendQuest("" + playerLevel[17] + "", 4022);
		CAM().sendQuest("" + playerLevel[12] + "", 4024);
		CAM().sendQuest("" + playerLevel[9] + "", 4026);
		CAM().sendQuest("" + playerLevel[14] + "", 4028);
		CAM().sendQuest("" + playerLevel[13] + "", 4030);
		CAM().sendQuest("" + playerLevel[10] + "", 4032);
		CAM().sendQuest("" + playerLevel[7] + "", 4034);
		CAM().sendQuest("" + playerLevel[11] + "", 4036);
		CAM().sendQuest("" + playerLevel[8] + "", 4038);
		CAM().sendQuest("" + playerLevel[20] + "", 4152);
		CAM().sendQuest("" + playerLevel[18] + "", 12166);
		CAM().sendQuest("" + playerLevel[19] + "", 13926);
		CAM().sendQuest("" + getLevelForXP(playerXP[0]) + "", 4005);
		CAM().sendQuest("" + getLevelForXP(playerXP[2]) + "", 4007);
		CAM().sendQuest("" + getLevelForXP(playerXP[1]) + "", 4009);
		CAM().sendQuest("" + getLevelForXP(playerXP[4]) + "", 4011);
		CAM().sendQuest("" + getLevelForXP(playerXP[5]) + "", 4013);
		CAM().sendQuest("" + getLevelForXP(playerXP[6]) + "", 4015);
		CAM().sendQuest("" + getLevelForXP(playerXP[3]) + "", 4017);
		CAM().sendQuest("" + getLevelForXP(playerXP[16]) + "", 4019);
		CAM().sendQuest("" + getLevelForXP(playerXP[15]) + "", 4021);
		CAM().sendQuest("" + getLevelForXP(playerXP[17]) + "", 4023);
		CAM().sendQuest("" + getLevelForXP(playerXP[12]) + "", 4025);
		CAM().sendQuest("" + getLevelForXP(playerXP[9]) + "", 4027);
		CAM().sendQuest("" + getLevelForXP(playerXP[14]) + "", 4029);
		CAM().sendQuest("" + getLevelForXP(playerXP[13]) + "", 4031);
		CAM().sendQuest("" + getLevelForXP(playerXP[10]) + "", 4033);
		CAM().sendQuest("" + getLevelForXP(playerXP[7]) + "", 4035);
		CAM().sendQuest("" + getLevelForXP(playerXP[11]) + "", 4037);
		CAM().sendQuest("" + getLevelForXP(playerXP[8]) + "", 4039);
		CAM().sendQuest("" + getLevelForXP(playerXP[20]) + "", 4153);
		CAM().sendQuest("" + getLevelForXP(playerXP[18]) + "", 12167);
		CAM().sendQuest("" + getLevelForXP(playerXP[19]) + "", 13927);
		CAM().sendQuest("" + playerXP[0] + "", 4044);
		CAM().sendQuest("" + playerXP[2] + "", 4050);
		CAM().sendQuest("" + playerXP[1] + "", 4056);
		CAM().sendQuest("" + playerXP[4] + "", 4062);
		CAM().sendQuest("" + playerXP[5] + "", 4068);
		CAM().sendQuest("" + playerXP[6] + "", 4074);
		CAM().sendQuest("" + playerXP[3] + "", 4080);
		CAM().sendQuest("" + playerXP[16] + "", 4086);
		CAM().sendQuest("" + playerXP[15] + "", 4092);
		CAM().sendQuest("" + playerXP[17] + "", 4098);
		CAM().sendQuest("" + playerXP[12] + "", 4104);
		CAM().sendQuest("" + playerXP[9] + "", 4110);
		CAM().sendQuest("" + playerXP[14] + "", 4116);
		CAM().sendQuest("" + playerXP[13] + "", 4122);
		CAM().sendQuest("" + playerXP[10] + "", 4128);
		CAM().sendQuest("" + playerXP[7] + "", 4134);
		CAM().sendQuest("" + playerXP[11] + "", 4140);
		CAM().sendQuest("" + playerXP[8] + "", 4146);
		CAM().sendQuest("" + playerXP[20] + "", 4157);
		CAM().sendQuest("" + playerXP[18] + "", 12171);
		CAM().sendQuest("" + playerXP[19] + "", 13921);
		CAM().sendQuest("" + getXPForLevel(playerLevel[0] + 1) + "", 4045);
		CAM().sendQuest("" + getXPForLevel(playerLevel[2] + 1) + "", 4051);
		CAM().sendQuest("" + getXPForLevel(playerLevel[1] + 1) + "", 4057);
		CAM().sendQuest("" + getXPForLevel(playerLevel[4] + 1) + "", 4063);
		CAM().sendQuest("" + getXPForLevel(playerLevel[5] + 1) + "", 4069);
		CAM().sendQuest("" + getXPForLevel(playerLevel[6] + 1) + "", 4075);
		CAM().sendQuest("" + getXPForLevel(playerLevel[3] + 1) + "", 4081);
		CAM().sendQuest("" + getXPForLevel(playerLevel[16] + 1) + "", 4087);
		CAM().sendQuest("" + getXPForLevel(playerLevel[15] + 1) + "", 4093);
		CAM().sendQuest("" + getXPForLevel(playerLevel[17] + 1) + "", 4099);
		CAM().sendQuest("" + getXPForLevel(playerLevel[12] + 1) + "", 4105);
		CAM().sendQuest("" + getXPForLevel(playerLevel[9] + 1) + "", 4111);
		CAM().sendQuest("" + getXPForLevel(playerLevel[14] + 1) + "", 4117);
		CAM().sendQuest("" + getXPForLevel(playerLevel[13] + 1) + "", 4123);
		CAM().sendQuest("" + getXPForLevel(playerLevel[10] + 1) + "", 4129);
		CAM().sendQuest("" + getXPForLevel(playerLevel[7] + 1) + "", 4135);
		CAM().sendQuest("" + getXPForLevel(playerLevel[11] + 1) + "", 4141);
		CAM().sendQuest("" + getXPForLevel(playerLevel[8] + 1) + "", 4147);
		CAM().sendQuest("" + getXPForLevel(playerLevel[20] + 1) + "", 4158);
		CAM().sendQuest("" + getXPForLevel(playerLevel[18] + 1) + "", 12172);
		CAM().sendQuest("" + getXPForLevel(playerLevel[19] + 1) + "", 13922);
	}
	public void remove(int wearID, int slot) {
		if (addItem(playerEquipment[slot], playerEquipmentN[slot])) {
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
			updateRequired = true;
			appearanceUpdateRequired = true;
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
		updateRequired = true;
		appearanceUpdateRequired = true;
	}
	public void RemoveAllWindows() {
		outStream.createFrame(219);
		flushOutStream();
	}
	public void removeGroundItem(int itemX, int itemY, int itemID) {
		outStream.createFrame(85); // Phate: Item Position Frame
		outStream.writeByteC((itemY - 8 * mapRegionY));
		outStream.writeByteC((itemX - 8 * mapRegionX));
		outStream.createFrame(156); // Phate: Item Action: Delete
		outStream.writeByteS(0); // x(4 MSB) y(LSB) coords
		outStream.writeWord(itemID); // Phate: Item ID
	}
	public void reportAbuse(String abuser, int rule, int muted) {
		if (playerRights == 0) {
			writeLog("abuser = " + abuser + " for rule " + rule + " muted? "
			+ (muted > 0 ? "yes" : "no"), "reports");
			CAM().sendMessage("Your report has been stored and online moderators have also been notified");
		} else {
			boolean online = false;
			// int duration = misc.times[rule];
			for (int i = 0; i < handler.players.length; i++) {
				client other = getClient(i);
				if (!validClient(i))
				continue;
				if (other.playerName.equalsIgnoreCase(abuser)) {
					online = true;
					other.kick();
					break;
				}
			}
			if (online) {
				CAM().sendMessage("Player kicked off server");
			} else {
				CAM().sendMessage("Player was not online");
			}
		}
		if (playerRights < 1)
		modYell("[Abuse] " + abuser + " reported by " + playerName
		+ " for rule " + rule + " (" + misc.rules[rule] + ")");
		else
		modYell("[Mod Chat] " + playerName + " banned " + abuser + " ("
		+ misc.rules[rule] + ")");
	}
	public void resetAnimation() {
		pEmote = playerSE;
		updateRequired = true;
		appearanceUpdateRequired = true;
	}
	public void resetArray(int[] array) {
		for (int i = 0; i < array.length; i++) {
			array[i] = 0;
		}
	}
	public boolean ResetAttack() {
		WalkTo(0,0);
		AttackingOn = 0;
		followPlayer = null;
		isattackingaplayer = false;
		IsAttacking = false;
		IsAttackingNPC = false;
		resetAnimation();
		IsUsingSkill = false;
		faceNPC = 65535;
		faceNPCupdate = true;
		return true;
	}
	public boolean ResetAttackNPC() {
		IsAttackingNPC = false;
		IsAttacking = false;
		attacknpc = -1;
		resetAnimation();
		faceNPC = 65535;
		faceNPCupdate = true;
		return true;
	}
	public boolean ResetAttackPlayer(int NPCID) {
		server.npcHandler.npcs[NPCID].IsUnderAttack = false;
		server.npcHandler.npcs[NPCID].StartKilling = 0;
		server.npcHandler.npcs[NPCID].RandomWalk = true;
		server.npcHandler.npcs[NPCID].animNumber = 0x328;
		server.npcHandler.npcs[NPCID].animUpdateRequired = true;
		server.npcHandler.npcs[NPCID].updateRequired = true;
		faceNPC = 65535;
		faceNPCupdate = true;
		return true;
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
				outStream.writeByte(bankItemsN[i]); // amount
			}
			if (bankItemsN[i] < 1) {
				bankItems[i] = 0;
			}
			if ((bankItems[i] > 20000) || (bankItems[i] < 0)) {
				bankItems[i] = 20000;
			}
			outStream.writeWordBigEndianA(bankItems[i]); // itemID
		}
		outStream.endFrameVarSizeWord();
	}
	public void ResetBonus() {
		for (int i = 0; i < playerBonus.length; i++) {
			playerBonus[i] = 0;
		}
	}
	public void resetGFX(int id, int X, int Y) {
		GraphicsHandler.removeGFX(id, X, Y);
		firingspell = false;
		cast = false;
		fired = false;
	}
	public void resetItems(int WriteFrame) {
		outStream.createFrameVarSizeWord(53);
		outStream.writeWord(WriteFrame);
		outStream.writeWord(playerItems.length);
		for (int i = 0; i < playerItems.length; i++) {
			if (playerItemsN[i] > 254) {
				outStream.writeByte(255); // item's stack count. if over 254,
				// write byte 255
				outStream.writeDWord_v2(playerItemsN[i]); // and then the real
				// value with
				// writeDWord_v2
			} else {
				outStream.writeByte(playerItemsN[i]);
			}
			if ((playerItems[i] > 20000) || (playerItems[i] < 0)) {
				playerItems[i] = 20000;
			}
			outStream.writeWordBigEndianA(playerItems[i]); // item id
		}
		outStream.endFrameVarSizeWord();
	}
	public void resetPos() { //yanille
		teleportToX = 3087;
		teleportToY = 3502;
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
			if ((server.shopHandler.ShopItems[ShopID][i] > 0)
					|| (i <= server.shopHandler.ShopItemsStandard[ShopID])) {
				if (server.shopHandler.ShopItemsN[ShopID][i] > 254) {
					outStream.writeByte(255); // item's stack count. if over
					outStream.writeDWord_v2(server.shopHandler.ShopItemsN[ShopID][i]); 
				} else {
					outStream.writeByte(server.shopHandler.ShopItemsN[ShopID][i]);
				}
				if ((server.shopHandler.ShopItems[ShopID][i] > 20000)
						|| (server.shopHandler.ShopItems[ShopID][i] < 0)) {
					server.shopHandler.ShopItems[ShopID][i] = 20000;
				}
				outStream.writeWordBigEndianA(server.shopHandler.ShopItems[ShopID][i]);
				TotalCount++;
			}
			if (TotalCount > TotalItems) {
				break;
			}
		}
		outStream.endFrameVarSizeWord();
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
	public void resetTItems(int WriteFrame) {
		outStream.createFrameVarSizeWord(53);
		outStream.writeWord(WriteFrame);
		int len = offeredItems.toArray().length;
		int current = 0;
		outStream.writeWord(len);
		for (GameItem item : offeredItems) {
			if (item.amount > 254) {
				outStream.writeByte(255);
				outStream.writeDWord_v2(item.amount);
			} else {
				outStream.writeByte(item.amount);
			}
			outStream.writeWordBigEndianA(item.id + 1);
			current++;
		}
		if (current < 27) {
			for (int i = current; i < 28; i++) {
				outStream.writeByte(1);
				outStream.writeWordBigEndianA(-1);
			}
		}
		outStream.endFrameVarSizeWord();
	}
	public void ResetWalkTo() {
		ActionType = -1;
		destinationX = -1;
		destinationY = -1;
		destinationID = -1;
		destinationRange = 1;
		WalkingTo = false;
	}
	public void run() {
		isActive = false;
		long serverSessionKey = 0, clientSessionKey = 0;
		serverSessionKey = ((long) (java.lang.Math.random() * 99999999D) << 32)
		+ (long) (java.lang.Math.random() * 99999999D);
		try {
			fillInStream(2);
			if (inStream.readUnsignedByte() != 14) {
				mySock.close();
				shutdownError("Expected login Id 14 from client.");
				disconnected = true;
				return;
			}
			int namePart = inStream.readUnsignedByte();
			for (int i = 0; i < 8; i++) {
				out.write(1);
			}
			out.write(0);
			outStream.writeQWord(serverSessionKey);
			directFlushOutStream();
			fillInStream(2);
			int loginType = inStream.readUnsignedByte();
			if ((loginType != 16) && (loginType != 18)) {
				shutdownError("Unexpected login type " + loginType);
				return;
			}
			int loginPacketSize = inStream.readUnsignedByte();
			int loginEncryptPacketSize = loginPacketSize - (36 + 1 + 1 + 2);
			if (loginEncryptPacketSize <= 0) {
				shutdownError("Zero RSA packet size!");
				return;
			}
			fillInStream(loginPacketSize);
			if ((inStream.readUnsignedByte() != 255)
					|| (inStream.readUnsignedWord() == 0)) {
				return;
			}
			lowMemoryVersion = inStream.readUnsignedByte();
			for (int i = 0; i < 9; i++) {
				String junk = Integer.toHexString(inStream.readDWord());
			}
			loginEncryptPacketSize--; // don't count length byte
			int tmp = inStream.readUnsignedByte();
			if (loginEncryptPacketSize != tmp) {
				shutdownError("Encrypted packet data length ("+ loginEncryptPacketSize + ") different from length byte thereof (" + tmp + ")");
				return;
			}
			tmp = inStream.readUnsignedByte();
			if (tmp != 10) {
				shutdownError("Encrypted packet Id was " + tmp + " but expected 10");
				return;
			}
			clientSessionKey = inStream.readQWord();
			serverSessionKey = inStream.readQWord();
			int junk = inStream.readDWord();
			playerName = inStream.readString();
			int expectedUid = 1;
			if (junk == expectedUid) {
				officialClient = true;
			}
			//if (junk < 1) {		
				//writeLog(connectedFrom, "ipbans");
			//}
			uid = junk;
			if ((playerName == null) || (playerName.length() == 0)) {
				disconnected = true;
				savefile = false;
				return;
			}
			playerPass = inStream.readString();
			try {
				playerServer = inStream.readString();
			} catch (Exception e) {
				playerServer = "antares.servegame.com";
			}
			playerName = playerName.toLowerCase();
			playerPass = playerPass.toLowerCase();
			char[] validChars = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
				'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u',
				'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G',
				'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S',
				'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '1', '2', '3', '4', '5',
				'6', '7', '8', '9', '0', ' ' };
			playerName = playerName.trim();
			int sessionKey[] = new int[4];
			sessionKey[0] = (int) (clientSessionKey >> 32);
			sessionKey[1] = (int) clientSessionKey;
			sessionKey[2] = (int) (serverSessionKey >> 32);
			sessionKey[3] = (int) serverSessionKey;
			for (int i = 0; i < 4; i++) {
			}
			inStreamDecryption = new Cryption(sessionKey);
			for (int i = 0; i < 4; i++) {
				sessionKey[i] += 50;
			}
			for (int i = 0; i < 4; i++) {
			}
			outStreamDecryption = new Cryption(sessionKey);
			outStream.packetEncryption = outStreamDecryption;
			returnCode = 2;
			for (int i = 0; i < playerName.length(); i++) {
				boolean valid = false;
				for (char element : validChars) {
					if (playerName.charAt(i) == element) {
						valid = true;
					}
				}
				if (!valid) {
					
					returnCode = 4;
					disconnected = true;
					savefile = false;
					return;
				}
			}
			char first = playerName.charAt(0);
			
			properName = Character.toUpperCase(first)
			+ playerName.substring(1, playerName.length());
			playerName = properName;
			if (PlayerHandler.updateRunning) {
				returnCode = 14;
				disconnected = true;
				savefile = false;
				return;
			}
			if (!server.loginServerConnected) {
				returnCode = 8;
				disconnected = true;
				return;
			}
			boolean found = false;
			int type = 5;
			if (checkLog("tempbans", playerName)) {
				returnCode = 4;
				disconnected = true;
				return;
			}
			if (checkLog("bans", playerName)) {
				returnCode = 4;
				disconnected = true;
				return;
			}
			int loadgame = SG().loadgame(this, playerName, (playerPass));
			if (loadgame == 3) {
				returnCode = 3;
				disconnected = true;
				return;
			}
			if (server.playerHandler.isPlayerOn(playerName)) {
				returnCode = 5;
				disconnected = true;
				return;
			}

			if (server.enforceClient && !officialClient) {
				returnCode = 12;
				disconnected = true;
				return;
			} else {
				for (int i = 0; i < playerEquipment.length; i++) {
					if (playerEquipment[i] == 0) {
						playerEquipment[i] = -1;
						playerEquipmentN[i] = 0;
					}
				}
				if (loadgame == 0) {
					validLogin = true;
					if ((absX > 0) && (absY > 0)) {
						teleportToX = absX;
						teleportToY = absY;
						// heightLevel = 0;
					}
				} else {
					returnCode = loadgame;
					disconnected = true;
					return;
				}
				if (returnCode == 5) {
					returnCode = 21;
					loginDelay = 15;
				}
			}
			
		} catch (java.lang.Exception __ex) {
			server.logError(__ex.getMessage());
			__ex.printStackTrace();
		} finally {
			try {
				if (playerId == -1)
				out.write(7);
				else if (playerServer.equals("INVALID"))
				out.write(10);
				else
				out.write(returnCode);
				if (returnCode == 21)
				out.write(loginDelay);
				if ((playerId == -1) || (returnCode != 2)) {
					playerName = null;
					disconnected = true;
					destruct();
				}
				if (playerRights == 3)
				out.write(2);
				else
				out.write(playerRights);
				out.write(0);
				updateRequired = true;
				appearanceUpdateRequired = true;
			} catch (java.lang.Exception __ex) {
				destruct();
			}
		}
		isActive = true;
		packetSize = 0;
		packetType = -1;
		readPtr = 0;
		writePtr = 0;
		int numBytesInBuffer, offset;
		while (!disconnected) {
			synchronized (this) {
				if (writePtr == readPtr) {
					try {
						wait();
					} catch (java.lang.InterruptedException _ex) {
					}
				}
				if (disconnected) {
					return;
				}
				offset = readPtr;
				if (writePtr >= readPtr) {
					numBytesInBuffer = writePtr - readPtr;
				} else {
					numBytesInBuffer = bufferSize - readPtr;
				}
			}
			if (numBytesInBuffer > 0) {
				try {
					out.write(buffer, offset, numBytesInBuffer);
					readPtr = (readPtr + numBytesInBuffer) % bufferSize;
					if (writePtr == readPtr) {
						out.flush();
					}
				} catch (java.net.SocketException e) {
					disconnected = true;
					if (saveNeeded)
					SG().savegame(this, true);
				} catch (java.lang.Exception __ex) {
					server.logError(__ex.getMessage());
					disconnected = true;
					if (saveNeeded)
					SG().savegame(this, true);
				}
			}
		}
	}



	


	public void saveStats(boolean logout) {
		String[] parts = new String[] { "pGender", "pHead", "pTorso", "pArms",
			"pHands", "pLegs", "pFeet", "pBeard", "pHairC", "pTorsoC",
			"pLegsC", "pFeetC", "pSkinC" };
		try {
			int[] look = { pGender, pHead, pBeard, pTorso, pArms, pHands,
				pLegs, pFeet, pHairC, pTorsoC, pLegsC, pFeetC, pSkinC,
				playerLook[0], playerLook[1], playerLook[2], playerLook[3],
				playerLook[4], playerLook[5] };
			boolean DoInsert = true;
			int ID = -1;
			int ID2 = -1;
			int test1 = -1;
			int test2 = -1;
			// mysql_connect();
			long allxp = 0;
			for (int i = 0; i < 21; i++) {
				if (i != 18) {
					allxp += playerXP[i];
				}
			}
			int totallvl = playerLevel[0] + playerLevel[1] + playerLevel[2]
			+ playerLevel[3] + playerLevel[4] + playerLevel[5]
			+ playerLevel[6] + playerLevel[7] + playerLevel[8]
			+ playerLevel[9] + playerLevel[10] + playerLevel[11]
			+ playerLevel[12] + playerLevel[13] + playerLevel[14]
			+ playerLevel[15] + playerLevel[16] + playerLevel[17]
			+ playerLevel[18] + playerLevel[19] + playerLevel[20];
			int combatLevel = (int) ((double) playerLevel[0] * 0.32707
			+ (double) playerLevel[1] * 0.249 + (double) playerLevel[2]
			* 0.324 + (double) playerLevel[3] * 0.25 + (double) playerLevel[5] * 0.124);
			String online = "online = 0";
			if (!logout)
			online = "online = " + server.world;
			else
			saveNeeded = false;
		} catch (Exception e) {
		}
	}
	public boolean sellItem(int itemID, int fromSlot, int amount) {
		if ((amount > 0) && (itemID == (playerItems[fromSlot] - 1))) {
			if (server.shopHandler.ShopSModifier[MyShopID] > 1) {
				boolean IsIn = false;
				for (int i = 0; i <= server.shopHandler.ShopItemsStandard[MyShopID]; i++) {
					if (itemID == (server.shopHandler.ShopItems[MyShopID][i] - 1)) {
						IsIn = true;
						break;
					}
				}
				if (IsIn == false) {
					CAM().sendMessage("You cannot sell " + getItemName(itemID)
					+ " in this store.");
					return false;
				}
			}
			if (Item.itemSellable[(playerItems[fromSlot] - 1)] == false) {
				CAM().sendMessage("I cannot sell " + getItemName(itemID) + ".");
				return false;
			}
			if ((amount > playerItemsN[fromSlot])
					&& ((Item.itemIsNote[(playerItems[fromSlot] - 1)] == true) || (Item.itemStackable[(playerItems[fromSlot] - 1)] == true))) {
				amount = playerItemsN[fromSlot];
			} else if ((amount > GetXItemsInBag(itemID))
					&& (Item.itemIsNote[(playerItems[fromSlot] - 1)] == false)
					&& (Item.itemStackable[(playerItems[fromSlot] - 1)] == false)) {
				amount = GetXItemsInBag(itemID);
			}
			double ShopValue;
			double TotPrice;
			int TotPrice2;
			int Overstock;
			for (int i = amount; i > 0; i--) {
				TotPrice2 = (int) Math.floor(GetItemShopValue(itemID, 1,
				fromSlot));
				if (freeSlots() > 0) {
					if (Item.itemIsNote[itemID] == false) {
						deleteItem(itemID, GetItemSlot(itemID), 1);
					} else {
						deleteItem(itemID, fromSlot, 1);
					}
					addItem(995, TotPrice2);
					addShopItem(itemID, 1);
				} else {
					CAM().sendMessage("Not enough space in your inventory.");
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
	public void sendpm(long name, int rights, byte[] chatmessage, int messagesize) {
		outStream.createFrameVarSize(196);
		outStream.writeQWord(name);
		outStream.writeDWord(handler.lastchatid++); // must be different for
		// each message
		outStream.writeByte(rights);
		outStream.writeBytes(chatmessage, messagesize, 0);
		outStream.endFrameVarSize();
	}
	public void SendWeapon(int Weapon, String WeaponName) {
		String WeaponName2 = WeaponName.replaceAll("Bronze", "");
		WeaponName2 = WeaponName2.replaceAll("Iron", "");
		WeaponName2 = WeaponName2.replaceAll("Steel", "");
		WeaponName2 = WeaponName2.replaceAll("Scythe", "");
		WeaponName2 = WeaponName2.replaceAll("Black", "");
		WeaponName2 = WeaponName2.replaceAll("Mithril", "");
		WeaponName2 = WeaponName2.replaceAll("Adamant", "");
		WeaponName2 = WeaponName2.replaceAll("Rune", "");
		WeaponName2 = WeaponName2.replaceAll("Granite", "");
		WeaponName2 = WeaponName2.replaceAll("Dragon", "");
		WeaponName2 = WeaponName2.replaceAll("Crystal", "");
		WeaponName2 = WeaponName2.trim();
		if (WeaponName.equals("Unarmed")) {
			setSidebarInterface(0, 5855); // punch, kick, block
			CAM().sendFrame126(WeaponName, 5857);
		} else if (WeaponName.endsWith("whip")) {
			setSidebarInterface(0, 12290); // flick, lash, deflect
			CAM().sendFrame246(12291, 200, Weapon);
			CAM().sendFrame126(WeaponName, 12293);
		} else if (WeaponName.endsWith("Scythe")) {
			setSidebarInterface(0, 776); // flick, lash, deflect
			CAM().sendFrame246(12291, 200, Weapon);
			CAM().sendFrame126(WeaponName, 778);
		} else if (WeaponName.endsWith("bow")) {
			setSidebarInterface(0, 1764); // accurate, rapid, longrange
			CAM().sendFrame246(1765, 200, Weapon);
			CAM().sendFrame126(WeaponName, 1767);
		} else if (WeaponName.startsWith("Staff")
				|| WeaponName.endsWith("staff")) {
			setSidebarInterface(0, 328); // spike, impale, smash, block
			CAM().sendFrame246(329, 200, Weapon);
			CAM().sendFrame126(WeaponName, 331);
		} else if (WeaponName2.startsWith("dart")) {
			setSidebarInterface(0, 4446); // accurate, rapid, longrange
			CAM().sendFrame246(4447, 200, Weapon);
			CAM().sendFrame126(WeaponName, 4449);
		} else if (WeaponName2.startsWith("dagger")) {
			setSidebarInterface(0, 2276); // stab, lunge, slash, block
			CAM().sendFrame246(2277, 200, Weapon);
			CAM().sendFrame126(WeaponName, 2279);
		} else if (WeaponName2.startsWith("pickaxe")) {
			setSidebarInterface(0, 5570); // spike, impale, smash, block
			CAM().sendFrame246(5571, 200, Weapon);
			CAM().sendFrame126(WeaponName, 5573);
		} else if (WeaponName2.startsWith("axe")
				|| WeaponName2.startsWith("battleaxe")) {
			setSidebarInterface(0, 1698); // chop, hack, smash, block
			CAM().sendFrame246(1699, 200, Weapon);
			CAM().sendFrame126(WeaponName, 1701);
		} else if (WeaponName2.startsWith("Axe")
				|| WeaponName2.startsWith("Battleaxe")) {
			setSidebarInterface(0, 1698); // chop, hack, smash, block
			CAM().sendFrame246(1699, 200, Weapon);
			CAM().sendFrame126(WeaponName, 1701);
		} else if (WeaponName2.startsWith("halberd")) {
			setSidebarInterface(0, 8460); // jab, swipe, fend
			CAM().sendFrame246(8461, 200, Weapon);
			CAM().sendFrame126(WeaponName, 8463);
		} else if (WeaponName2.startsWith("spear")) {
			setSidebarInterface(0, 4679); // lunge, swipe, pound, block
			CAM().sendFrame246(4680, 200, Weapon);
			CAM().sendFrame126(WeaponName, 4682);
		} else {
			setSidebarInterface(0, 2423); // chop, slash, lunge, block
			CAM().sendFrame246(2424, 200, Weapon);
			CAM().sendFrame126(WeaponName, 2426);
		}
	}
	public void ServerHelp() {
		CAM().sendMessage("Kill monsters for all items.");
	}
	public void setAnimation(int i) {
		startAnimation(i);
	}
	public void setChatOptions(int publicChat, int privateChat, int tradeBlock) {
		outStream.createFrame(206);
		outStream.writeByte(publicChat); // On = 0, Friends = 1, Off = 2,
		// Hide = 3
		outStream.writeByte(privateChat); // On = 0, Friends = 1, Off = 2
		outStream.writeByte(tradeBlock); // On = 0, Friends = 1, Off = 2
	}
	public void setClientConfig(int id, int state) {
		outStream.createFrame(36);
		outStream.writeWordBigEndian(id);
		outStream.writeByte(state);
	}
	public boolean firearrowproj(int castID, int casterY, int casterX, int offsetY, int offsetX, int angle, int speed, int movegfxID, int startHeight, int endHeight, int finishID, int enemyY, int enemyX, int Lockon, int Slope) {
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
		if (cast == false && actionTimer <= 0) {
			cast = true;
			firingspell = true;
		}
		if (cast == true && fired == false && actionTimer <= 0) {
			createProjectile2(casterY, casterX, offsetY, offsetX, angle, speed,
			movegfxID, startHeight, endHeight, Lockon, Slope);
			fired = true;
		}
		if (fired == true && actionTimer <= 0) {
			stillgfx3(finishID, 0, enemyY, enemyX, 95);
			resetGFX(castID, enemyX, enemyY);
			return false;
		}
		return true;
	}
	public boolean fireArrow(int castID, int casterY, int casterX, int offsetY, int offsetX, int angle, int speed, int movegfxID, int startHeight, int endHeight, int MageAttackIndex, int enemyY, int enemyX) {
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
		fenemyY = enemyY;
		fenemyX = enemyX;
		MageAttackIndex = MageAttackIndex;
		actionTimer = 0;
		if (cast == false && actionTimer <= 0) {
			cast = true;
			firingspell = true;
		}
		if (cast == true && fired == false && actionTimer <= 0) {
			createProjectile(casterY, casterX, offsetY, offsetX, angle, speed,
			movegfxID, startHeight, endHeight, MageAttackIndex);
			fired = true;
			resetGFX(castID, enemyX, enemyY);
			return false;
		}
		return true;
	}
	public void createProjectile2(int casterY, int casterX, int offsetY, int offsetX, int angle, int speed, int gfxMoving,int startHeight, int endHeight, int lockon, int slope) {
		outStream.createFrame(85);
		outStream.writeByteC((casterY - (mapRegionY * 8)) - 2);
		outStream.writeByteC((casterX - (mapRegionX * 8)) - 3);
		outStream.createFrame(117);
		outStream.writeByte(angle); // Starting place of the projectile
		outStream.writeByte(offsetY); // Distance between caster and enemy Y
		outStream.writeByte(offsetX); // Distance between caster and enemy X
		outStream.writeWord(lockon); // The NPC the missle is locked on to
		outStream.writeWord(gfxMoving); // The moving graphic ID
		outStream.writeByte(startHeight); // The starting height
		outStream.writeByte(endHeight); // Destination height
		outStream.writeWord(51); // Time the missle is created
		int OFFSETX = offsetX;
		int OFFSETY = offsetY;
		if(offsetY > 0) {
			OFFSETY = -OFFSETY;
		}
		if(offsetX > 0) {
			OFFSETX = -OFFSETX;
		}
		outStream.writeWord(speed-(OFFSETY+OFFSETX));
		outStream.writeByte(slope); // Initial slope //16
		outStream.writeByte(64); // Initial distance from source (in the direction of the missile) //64
	}
	public void createPlayersProjectile(int casterY, int casterX, int offsetY, int offsetX, int angle, int speed, int gfxMoving, int startHeight, int endHeight, int lockon) {
		for (Player p : server.playerHandler.players) {
			if (p != null) {
				client c = (client)p;

				if (c.distanceToPoint(casterX, casterY) <= 25){	
					c.fireProjectile(casterY, casterX, offsetY, offsetX, angle, speed, gfxMoving, startHeight, endHeight, lockon);	
				}
			}
		}
	}
	public void fireProjectile(int casterY, int casterX, int offsetY, int offsetX, int angle, int speed, int gfxMoving, int startHeight, int endHeight, int lockon) {      
		outStream.createFrame(85);
                outStream.writeByteC((casterY - (mapRegionY * 8)) - 2);
                outStream.writeByteC((casterX - (mapRegionX * 8)) - 3);
		outStream.createFrame(117);
		outStream.writeByte(angle);
		outStream.writeByte(offsetY);
		outStream.writeByte(offsetX);
		outStream.writeWord(lockon);
		outStream.writeWord(gfxMoving);
		outStream.writeByte(startHeight);
		outStream.writeByte(endHeight);

		if (playerEquipment[3] == 4734) {
			outStream.writeWord(44);
		} else if (playerEquipment[3] == 868) {
			outStream.writeWord(40);
		} else {
			outStream.writeWord(49);
		}
		//outStream.writeWord(51);
		outStream.writeWord(speed);
		outStream.writeByte(16);
		
		if (playerEquipment[3] == 4734) {
			outStream.writeByte(14);
		} else {
			outStream.writeByte(64);
		}
	}
	public void createProjectile22(int i, int j, int k, int l, int i1, int j1, int k1, int l1, int i2) {
		for (Player p : server.playerHandler.players) {
			if (p != null) {
				client c = (client) p;

				c.outStream.createFrame(85);
                                c.outStream.writeByteC(i - c.mapRegionY * 8 - 2);
                                c.outStream.writeByteC(j - c.mapRegionX * 8 - 3);
                                c.outStream.createFrame(117);
                                c.outStream.writeByte(50);
                                c.outStream.writeByte(k);
                                c.outStream.writeByte(l);
                                c.outStream.writeWord(i2);
                                c.outStream.writeWord(i1);
                                c.outStream.writeByte(j1);
                                c.outStream.writeByte(k1);
                                c.outStream.writeWord(51);
                                c.outStream.writeWord(l1);
                                c.outStream.writeByte(16);
                                c.outStream.writeByte(64);
			}
		}
	}
	public void createProjectile1337(int casterY, int casterX, int offsetY, int offsetX, int angle, int speed, int gfxMoving,int startHeight, int endHeight, int lockon, int slope, int endGfx) {
		outStream.createFrame(85);
		outStream.writeByteC((casterY - (mapRegionY * 8)) - 2);
		outStream.writeByteC((casterX - (mapRegionX * 8)) - 3);
		outStream.createFrame(117);
		outStream.writeByte(angle);
		outStream.writeByte(offsetY);
		outStream.writeByte(offsetX);
		outStream.writeWord(lockon);
		outStream.writeWord(gfxMoving);
		outStream.writeByte(startHeight);
		outStream.writeByte(endHeight);
		outStream.writeWord(51);
		int OFFSETX = offsetX;
		int OFFSETY = offsetY;
		if(offsetY > 0) {
			OFFSETY = -OFFSETY;
		}
		if(offsetX > 0) {
			OFFSETX = -OFFSETX;
		}
		outStream.writeWord(speed-(OFFSETY+OFFSETX));
		outStream.writeByte(slope);
		outStream.writeByte(64);
		outStream.writeWord(endGfx);
	}
	public void stillgfx3(int id, int height, int Y, int X, int delay) {
		for (Player p : server.playerHandler.players) {
			if (p != null) {
				client person = (client) p;
				if ((person.playerName != null || person.playerName != "null")) {
					if (person.distanceToPoint(X, Y) <= 60) {
						person.stillgfx4(id, height, Y, X, delay);
					}
				}
			}
		}
	}
	public void stillgfx4(int id, int height, int Y, int X, int delay) {
		outStream.createFrame(85);
		outStream.writeByteC(Y - (mapRegionY * 8));
		outStream.writeByteC(X - (mapRegionX * 8));
		outStream.createFrame(4);
		outStream.writeByte(0);
		outStream.writeWord(id);
		outStream.writeByte(height);
		outStream.writeWord(delay);
	}
	public void runEnergy() {
		if (System.currentTimeMillis() - updateEnergy > 500) {
			if (System.currentTimeMillis() - lastEnergyUpdate > getEnergyRestoreRate()) { 
				if (playerEnergy <= 99) {
					playerEnergy = (playerEnergy + 1);
				}
				lastEnergyUpdate = System.currentTimeMillis();
			}
			if (playerEnergy >= 25)
				WriteEnergy();
			else if (playerEnergy >= 0 && playerEnergy <= 24)
				CAM().sendFrame126("@red@"+playerEnergy +" %", 149);
			updateEnergy = System.currentTimeMillis();
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
			playerSE = CAM().GetStandAnim(wearID);
			playerSEW = CAM().GetWalkAnim(wearID);
			playerSER = CAM().GetRunAnim(wearID);
			playerSEA = 0x326;
			
			if (wearID == 4747) { //Torag Hammers
				playerSEA = 0x814;
			}
			if (wearID == 4151) { //Whip
				playerSER = 1661;
				playerSEW = 1660;
			}
			if (wearID == 8447) { //cat toy
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
	public void setInterfaceWalkable(int ID) {
		outStream.createFrame(208);
		outStream.writeWordBigEndian_dup(ID);
		flushOutStream();
	}
	public int GetWorld(int PlayerID) {
		String Server = PlayerHandler.players[PlayerID].playerServer;
		return 1;
	}
	public void setLook(int[] parts) {
		if (parts.length != 19) {
			println("setLook:  Invalid array length!");
			return;
		}
		pGender = parts[0];
		pHead = parts[1];
		pBeard = parts[2];
		pTorso = parts[3];
		pArms = parts[4];
		pHands = parts[5];
		pLegs = parts[6];
		pFeet = parts[7];
		pHairC = parts[8];
		pTorsoC = parts[9];
		pLegsC = parts[10];
		pFeetC = parts[11];
		pSkinC = parts[12];
		playerLook[0] = parts[13];
		playerLook[1] = parts[14];
		playerLook[2] = parts[15];
		playerLook[3] = parts[16];
		playerLook[4] = parts[17];
		playerLook[5] = parts[18];
		apset = true;
		appearanceUpdateRequired = true;
		lookUpdate = true;
		updateRequired = true;
	}
	public void setSidebarInterface(int menuId, int form) {
		outStream.createFrame(71);
		outStream.writeWord(form);
		outStream.writeByteA(menuId);
	}
	public void setSkillLevel(int skillNum, int currentLevel, int XP) {
		if (skillNum == 0) {
			CAM().sendQuest("" + playerLevel[0] + "", 4004);
			CAM().sendQuest("" + getLevelForXP(playerXP[0]) + "", 4005);
		}
		if (skillNum == 2) {
			CAM().sendQuest("" + playerLevel[2] + "", 4006);
			CAM().sendQuest("" + getLevelForXP(playerXP[2]) + "", 4007);
		}
		if (skillNum == 1) {
			CAM().sendQuest("" + playerLevel[1] + "", 4008);
			CAM().sendQuest("" + getLevelForXP(playerXP[1]) + "", 4009);
		}
		if (skillNum == 4) {
			CAM().sendQuest("" + playerLevel[4] + "", 4010);
			CAM().sendQuest("" + getLevelForXP(playerXP[4]) + "", 4011);
		}
		if (skillNum == 5) {
			CAM().sendQuest("" + playerLevel[5] + "", 4012);
			CAM().sendQuest("" + getLevelForXP(playerXP[5]) + "", 4013);
		}
		if (skillNum == 6) {
			CAM().sendQuest("" + playerLevel[6] + "", 4014);
			CAM().sendQuest("" + getLevelForXP(playerXP[6]) + "", 4015);
		}
		if (skillNum == 3) {
			CAM().sendQuest("" + currentHealth + "", 4016);
			CAM().sendQuest("" + getLevelForXP(playerXP[3]) + "", 4017);
		}
		if (skillNum == 16) {
			CAM().sendQuest("" + playerLevel[16] + "", 4018);
			CAM().sendQuest("" + getLevelForXP(playerXP[16]) + "", 4019);
		}
		if (skillNum == 15) {
			CAM().sendQuest("" + playerLevel[15] + "", 4020);
			CAM().sendQuest("" + getLevelForXP(playerXP[15]) + "", 4021);
		}
		if (skillNum == 17) {
			CAM().sendQuest("" + playerLevel[17] + "", 4022);
			CAM().sendQuest("" + getLevelForXP(playerXP[17]) + "", 4023);
		}
		if (skillNum == 12) {
			CAM().sendQuest("" + playerLevel[12] + "", 4024);
			CAM().sendQuest("" + getLevelForXP(playerXP[12]) + "", 4025);
		}
		if (skillNum == 9) {
			CAM().sendQuest("" + playerLevel[9] + "", 4026);
			CAM().sendQuest("" + getLevelForXP(playerXP[9]) + "", 4027);
		}
		if (skillNum == 14) {
			CAM().sendQuest("" + playerLevel[14] + "", 4028);
			CAM().sendQuest("" + getLevelForXP(playerXP[14]) + "", 4029);
		}
		if (skillNum == 13) {
			CAM().sendQuest("" + playerLevel[13] + "", 4030);
			CAM().sendQuest("" + getLevelForXP(playerXP[13]) + "", 4031);
		}
		if (skillNum == 10) {
			CAM().sendQuest("" + playerLevel[10] + "", 4032);
			CAM().sendQuest("" + getLevelForXP(playerXP[10]) + "", 4033);
		}
		if (skillNum == 7) {
			CAM().sendQuest("" + playerLevel[7] + "", 4034);
			CAM().sendQuest("" + getLevelForXP(playerXP[7]) + "", 4035);
		}
		if (skillNum == 11) {
			CAM().sendQuest("" + playerLevel[11] + "", 4036);
			CAM().sendQuest("" + getLevelForXP(playerXP[11]) + "", 4037);
		}
		if (skillNum == 8) {
			CAM().sendQuest("" + playerLevel[8] + "", 4038);
			CAM().sendQuest("" + getLevelForXP(playerXP[8]) + "", 4039);
		}
		if (skillNum == 20) {
			CAM().sendQuest("" + playerLevel[20] + "", 4152);
			CAM().sendQuest("" + getLevelForXP(playerXP[20]) + "", 4153);
		}
		if (skillNum == 18) {
			CAM().sendQuest("" + playerLevel[18] + "", 12166);
			CAM().sendQuest("" + getLevelForXP(playerXP[18]) + "", 12167);
		}
		if (skillNum == 19) {
			CAM().sendQuest("" + playerLevel[19] + "", 13926);
			CAM().sendQuest("" + getLevelForXP(playerXP[19]) + "", 13927);
		} else {
			outStream.createFrame(134);
			outStream.writeByte(skillNum);
			outStream.writeDWord_v1(XP);
			outStream.writeByte(currentLevel);
		}
	}
	public void shutdownError(String errorMessage) {
		// misc.println(": " + errorMessage);
		destruct();
	}
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
			if ((stairs == 3) || (stairs == 5) || (stairs == 9)) {
				teleportToY += 6400;
			} else if ((stairs == 4) || (stairs == 6) || (stairs == 10)) {
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
			} else if (stairs == 27) {
				teleportToX = 2453;
				teleportToY = 4468;
			} else if (stairs == 28) {
				teleportToX = 3201;
				teleportToY = 3169;
			}
			if ((stairs == 5) || (stairs == 10)) {
				teleportToX += (stairDistance + stairDistanceAdd);
			}
			if ((stairs == 6) || (stairs == 9)) {
				teleportToX -= (stairDistance - stairDistanceAdd);
			}
		}
		resetStairs();
		return true;
	}
	public void stillgfx(int id, int y, int x) {
		stillgfx(id, y, x, 0, 0);
	}
	public void stillgfx(int id, int Y, int X, int height, int time) {
		// for (Player p : server.playerHandler.players) {
		for (Player p : server.playerHandler.players) {
			if (p != null) {
				client person = (client) p;
				if (person.playerName != null) {
					if (person.distanceToPoint(X, Y) <= 60 && person.heightLevel == heightLevel) {
						person.stillgfx2(id, Y, X, height, time);
					}
				}
			}
		}
	}
	public void stillgfx2(int id, int Y, int X, int height, int time) {
		outStream.createFrame(85);
		outStream.writeByteC(Y - (mapRegionY * 8));
		outStream.writeByteC(X - (mapRegionX * 8));
		outStream.createFrame(4);
		outStream.writeByte(0); // Tiles away (X >> 4 + Y & 7)
		outStream.writeWord(id); // Graphic id
		outStream.writeByte(height); // height of the spell above it's basic
		// place, i think it's written in pixels
		// 100 pixels higher
		outStream.writeWord(time); // Time before casting the graphic
	}
	public void teleport() {
		teleport = true;
		if (ancientstele == true) {
			animation(392, absY, absX);
			teletimer = 12;
		} else if (ancientstele == false) {
			setAnimation(714);
			animation(308, absY, absX);
			teletimer = 5;
		}
	}
	public void TeleTo(String s, int level) {
		if (actionTimer == 0) {
			teleX = absX;
			teleY = absY;
			newheightLevel = heightLevel;
			closeInterface();
			teleport();
			actionTimer = 10;
			updateRequired = true;
			appearanceUpdateRequired = true;
		}
	}
	public void update() {
		handler.updatePlayer(this, outStream);
		handler.updateNPC(this, outStream);
		flushOutStream();
	}
	public void updateCharAppearance(int[] styles, int[] colors) {
		for (int j = 0; j < 7; j++) {
			if (styles[j] > 0) {
				styles[j] += 0x100;
				pCHead = styles[0];
				pCBeard = styles[1];
				pCTorso = styles[2];
				pCArms = styles[3];
				pCHands = styles[4];
				pCLegs = styles[5];
				pCFeet = styles[6];
			}
		}
		for (int i = 0; i < 5; i++) {
			pColor = colors[i];
		}
	}
	public void UpdatePlayerShop() {
		for (int i = 1; i < PlayerHandler.maxPlayers; i++) {
			if (PlayerHandler.players[i] != null) {
				if ((PlayerHandler.players[i].IsShopping == true)
						&& (PlayerHandler.players[i].MyShopID == MyShopID)
						&& (i != playerId)) {
					PlayerHandler.players[i].UpdateShop = true;
				}
			}
		}
	}
	public void updateRating() {
		CAM().sendQuest("QP: "+QH().tqp, 3985);
	}
	public boolean validClient(int index) {
		client p = (client) handler.players[index];
		if ((p != null) && !p.disconnected) {
			return true;
		}
		return false;
	}
	public void viewTo(int coordX, int coordY) {
		viewToX = ((2 * coordX) + 1);
		viewToY = ((2 * coordY) + 1);
		dirUpdate2Required = true;
		updateRequired = true;
	}
	public void walkTo(int x, int y) {
		newWalkCmdX[1] += x;
		newWalkCmdY[1] += y;
		tmpNWCX[1] = newWalkCmdX[i];
		tmpNWCY[1] = newWalkCmdY[i];
		newWalkCmdIsRunning = true;
	}
	public void walkTo_old(int X, int Y) {
		int firstStepX = inStream.readSignedWordBigEndianA();
		int tmpFSX = X;
		X -= mapRegionX * 8;
		for (i = 1; i < newWalkCmdSteps; i++) {
			newWalkCmdX[i] = inStream.readSignedByte();
			newWalkCmdY[i] = inStream.readSignedByte();
			tmpNWCX[i] = newWalkCmdX[i];
			tmpNWCY[i] = newWalkCmdY[i];
		}
		newWalkCmdX[0] = newWalkCmdY[0] = tmpNWCX[0] = tmpNWCY[0] = 0;
		int firstStepY = inStream.readSignedWordBigEndian();
		int tmpFSY = Y;
		Y -= mapRegionY * 8;
		newWalkCmdIsRunning = inStream.readSignedByteC() == 1;
		for (i = 0; i < newWalkCmdSteps; i++) {
			newWalkCmdX[i] += X;
			newWalkCmdY[i] += Y;
		}
	}
	public boolean wear(int wearID, int slot) {
		if (spellSelected) {
			autoCast = false;
			setClientConfig(108, 0);
			spellSelected = false;
			spellSelectedNpc = false;
			autoCastID = -1;
			//cancelAttack();
		}
		if (spellSelectedNpc) {
			autoCast = false;
			setClientConfig(108, 0);
			spellSelectedNpc = false;
			spellSelected = false;
			autoCastID = -1;
			//cancelAttack();
		}
		int targetSlot = 0;
		targetSlot = itemType(wearID);
		int[] two_hand = { 1319, 4718, 4726, 1409, 4710, 7158 };
		for (int element : two_hand) {
			if ((wearID == element) && (playerEquipment[playerShield] > 0)) {
				if (playerHasItem(-1)) {
					addItem(playerEquipment[playerShield], 1);
					playerEquipment[playerShield] = -1;
				} else {
					CAM().sendMessage("You can't wear this weapon with a shield");
					return false;
				}
			}
			if ((itemType(wearID) == playerShield)
					&& (playerEquipment[playerWeapon] == element)) {
				if (playerHasItem(-1)) {
					addItem(playerEquipment[playerWeapon], 1);
					playerEquipment[playerWeapon] = -1;
				} else {
					CAM().sendMessage("You can't wear a shield with this weapon");
					return false;
				}
			}
			/*if (!canUse(wearID)) {
				CAM().sendMessage("You must be a premium member to use this item");
				return false;
			}*/
		}
		specOn = false;
		specAttack();
		if ((playerItems[slot] - 1) == wearID) {
			targetSlot = itemType(wearID);
			
			int CLAttack = server.lvlHandler.GetCLAttack(wearID);
			int CLStrength = server.lvlHandler.GetCLStrength(wearID);
			int CLDefence = server.lvlHandler.GetCLDefence(wearID);
			int CLRanged = server.lvlHandler.GetCLRanged(wearID);
			//int CLPrayer = server.lvlHandler.GetCLPrayer(wearID);
			int CLMagic = server.lvlHandler.GetCLMagic(wearID);
			//int CLHitpoints = server.lvlHandler.GetCLHitpoints(wearID);
			//int CLismember = server.lvlHandler.GetCLismember(wearID);
			boolean GoFalse = false;
			/*[) ----- Attack ----- (]*/
			if (CLAttack > getLevelForXP(playerXP[0])) {
				CAM().sendMessage("You need "+CLAttack+" "+statName[playerAttack]+" to equip this item.");
				GoFalse = true;
			}
			
			/*if (CLismember == 0){
				if (playerIsMember == 0){
					CAM().sendMessage("This item is for members only.");
					GoFalse = true;
				}}*/
			/*[) ----- Strength----- (]*/
			if (CLStrength > getLevelForXP(playerXP[2])) {
				CAM().sendMessage("You need "+CLStrength+" "+statName[playerStrength]+" to equip this item.");
				GoFalse = true;
			}
			/*[) ----- Defence ----- (]*/
			if (CLDefence > getLevelForXP(playerXP[1])) {
				CAM().sendMessage("You need "+CLDefence+" " +statName[playerDefence]+" to equip this item.");
				GoFalse = true;
			}
			/*[) ----- Ranged ----- (]*/
			if (CLRanged > getLevelForXP(playerXP[4])) {
				CAM().sendMessage("You need "+CLRanged+" "+statName[playerRanged]+" to equip this item.");
				GoFalse = true;
			}
			/*[) ----- Prayer ----- (]*/
			/*if (CLPrayer > getLevelForXP(playerXP[5])) {
				CAM().sendMessage("You need "+CLPrayer+" "+statName[playerPrayer]+" to equip this item.");
				GoFalse = true;
			}*/
			/*[) ----- Magic ----- (]*/
			if (CLMagic > getLevelForXP(playerXP[6])) {
				CAM().sendMessage("You need "+CLMagic+" "+statName[playerMagic]+" to equip this item.");
				GoFalse = true;
			}
			/*[) ----- Hitpoints ----- (]*/
			/*if (CLHitpoints > getLevelForXP(playerXP[3])) {
				CAM().sendMessage("You need "+CLHitpoints+" "+statName[playerHitpoints]+" to equip this item.");
				GoFalse = true;
			}*/
			if (GoFalse == true) {
				return false;
			}
			int wearAmount = playerItemsN[slot];
			if (wearAmount < 1) {
				return false;
			}
			if ((slot >= 0) && (wearID >= 0)) {
				deleteItem(wearID, slot, wearAmount);
				if ((playerEquipment[targetSlot] != wearID) && (playerEquipment[targetSlot] >= 0)) {
					addItem(playerEquipment[targetSlot], playerEquipmentN[targetSlot]);
				} else if (Item.itemStackable[wearID] && (playerEquipment[targetSlot] == wearID)) {
					wearAmount = playerEquipmentN[targetSlot] + wearAmount;
				} else if (playerEquipment[targetSlot] >= 0) {
					addItem(playerEquipment[targetSlot], playerEquipmentN[targetSlot]);
				}
			}
			outStream.createFrameVarSizeWord(34);
			outStream.writeWord(1688);
			outStream.writeByte(targetSlot);
			outStream.writeWord(wearID + 1);
			if (wearAmount > 254) {
				outStream.writeByte(255);
				outStream.writeDWord(wearAmount);
			} else {
				outStream.writeByte(wearAmount); // amount
			}
			outStream.endFrameVarSizeWord();
			playerEquipment[targetSlot] = wearID;
			playerEquipmentN[targetSlot] = wearAmount;
			if ((targetSlot == playerWeapon)
					&& (playerEquipment[playerShield] != -1)
					&& (Item.itemTwoHanded[wearID] == true)) {
				remove(playerEquipment[playerShield], playerShield);
			}
			if (targetSlot == playerWeapon) {
				SendWeapon(wearID, getItemName(wearID));
				playerSE = CAM().GetStandAnim(wearID);
				playerSEW = CAM().GetWalkAnim(wearID);//gobackhere
				playerSER = CAM().GetRunAnim(wearID);
				playerSEA = 0x326;
				
				if (wearID == 4747) { //Torag Hammers
					playerSEA = 0x814;
				}
				if (wearID == 4151) { //Whip
					playerSER = 1661;
					playerSEW = 1660;
				}
				if (wearID == 8447) { //cat toy
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
			sendSpecialBarAmount();
			sendAllSpecialBars();
			return true;
			
		}
		return false;
	}
	public void WriteEnergy() {
		CAM().sendFrame126(playerEnergy + "%", 149);
	}
	public void chatLog(String data, String file) {
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter("./bin/config/" + file + ".txt",
			true));
			bw.write(data);
			bw.newLine();
			bw.flush();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			if (bw != null)
			try {
				bw.close();
			} catch (IOException ioe2) {
				System.out.println("Error writing system log.");
				ioe2.printStackTrace();
			}
		}
	}
	public void writeLog(String data, String file) {
		BufferedWriter bw = null;
		try {
			bw = new BufferedWriter(new FileWriter("./bin/config/" + file + ".txt",
			true));
			bw.write(data);
			bw.newLine();
			bw.flush();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			if (bw != null)
			try {
				bw.close();
			} catch (IOException ioe2) {
				System.out.println("Error writing system log.");
				ioe2.printStackTrace();
			}
		}
	}
	public void yell(String message) {
		for (Player p : handler.players) {
			if ((p == null) || !p.isActive)
			continue;
			client temp = (client) p;
			if ((temp.absX > 0) && (temp.absY > 0))
			if ((temp != null) && !temp.disconnected && p.isActive)
			temp.CAM().sendMessage(message);
		}
	}
	public void rearrangeBank() {
		int totalItems = 0;
		int highestSlot = 0;
		for (int i = 0; i < playerBankSize; i++) {
			if (bankItems[i] != 0) {
				totalItems ++;
				if (highestSlot <= i) highestSlot = i;
			} }
		
		for (int i = 0; i <= highestSlot; i++) {
			if (bankItems[i] == 0) {
				boolean stop = false;
				
				for (int k = i; k <= highestSlot; k++) {
					if (bankItems[k] != 0 && !stop) {
						int spots = k - i;
						for (int j = k; j <= highestSlot; j++) {
							bankItems[j-spots] = bankItems[j];
							bankItemsN[j-spots] = bankItemsN[j];
							stop = true;
							bankItems[j] = 0; bankItemsN[j] = 0;
						}
					}
				}					
			}
		}
		
		int totalItemsAfter = 0;
		for (int i = 0; i < playerBankSize; i++) {
			if (bankItems[i] != 0) { totalItemsAfter ++; } }
		
		if (totalItems != totalItemsAfter) outStream.createFrame(109); //disconnects when duping
		
	}
	public void CreateObj(int x, int y, int id, int face, int type)
	{
		outStream.createFrame(85);
		outStream.writeByteC(y - (mapRegionY * 8));
		outStream.writeByteC(x - (mapRegionX * 8));
		outStream.createFrame(151);
		outStream.writeByteA(0);
		outStream.writeWordBigEndian(id);
		outStream.writeByteS((type << 2) + (face & 3));
	}
	/* skill handlers*/
	public AgilityLog AgilityLog = new AgilityLog(this);
	public AgilityNet AgilityNet = new AgilityNet(this);
	public AgilityPipes AgilityPipes = new AgilityPipes(this);
	public AgilityBranches AgilityBranches = new AgilityBranches(this);
	public Woodcutting wc = new Woodcutting(this);
	public Mining mn = new Mining(this);
	public FireMaking FireMaking = new FireMaking(this);
	public Fishing Fishing = new Fishing(this);
	public Runecrafting Runecrafting = new Runecrafting(this);
	public Crafting Crafting = new Crafting(this);
	public SmithingSmelt smith = new SmithingSmelt(this);
	public ThievingNPC ThievingNPC = new ThievingNPC(this);
	public ThievingStalls ThievingStalls = new ThievingStalls(this);
	public Cooking Cooking = new Cooking(this);
	public Herblore Herblore = new Herblore(this);
	public Manager Manager = new Manager(this);
	public DialogueAssistant DialogueAssistant = new DialogueAssistant(this);
	public SaveGame SaveGame = new SaveGame(this);
	public DoorHandler doorHandler = new DoorHandler();
	private Trade trade = new  Trade(this);
	public busy busy = new  busy(this);
	public SkillInterfaces SkillInterfaces = new SkillInterfaces(this);
	
	public Distances Distances = new  Distances(this);
	public MeleeEvents MeleeEvents = new  MeleeEvents(this);
	public RangingEvents RangingEvents = new  RangingEvents(this);
	public scanWeapon scanWeapon = new  scanWeapon(this);
	public miscCombat miscCombat = new  miscCombat(this);
	public defenceAnimations defenceAnimations = new defenceAnimations(this);
	public combatSystem combatSystem = new  combatSystem(this);
	public CustomCommand CustomCommand = new CustomCommand();
	public ClickItem ClickItem = new ClickItem(this);
	public RegionLoader RegionLoader = new RegionLoader(this);
	public ActionButtons ActionButtons = new ActionButtons(this);
	public AtObjects AtObjects = new AtObjects(this);
	public QuestHandler QuestHandler = new QuestHandler(this);
	public StarterQuest StarterQuest = new StarterQuest(this);
	public DrogosQuest DrogosQuest = new DrogosQuest(this);
	public AncientsRule AncientsRule = new AncientsRule(this);
	public void npt() {
		CAM().sendFrame200(4883, 591);
		CAM().sendFrame126(GetNpcName(NpcTalkTo), 4884);
		CAM().sendFrame75(NpcTalkTo, 4883);
		CAM().sendFrame164(4882);
		NpcDialogueSend = true;
	}
	public void sSh() {
		skillX = server.npcHandler.npcs[NPCSlot].absX;
		skillY = server.npcHandler.npcs[NPCSlot].absY;
	}
	public ActionButtons AB() {
		return ActionButtons;
	}
	public DialogueAssistant DA() {
		return DialogueAssistant;
	}
	public SaveGame SG() {
		return SaveGame;
	}
	public defenceAnimations dA() {
		return defenceAnimations;
	}
	public RegionLoader getRegion() {
		return RegionLoader;
	}
	public Manager CAM() {
		return Manager;
	}
	public ClickItem CI() {
		return ClickItem;
	}
	public CustomCommand Command() {
		return CustomCommand;
	}
	public Trade getTrade() {
		return trade;
	}
	public combatSystem getCombat() {
		return combatSystem;
	}
	public RangingEvents rE() {
		return RangingEvents;
	}
	public MeleeEvents mE() {
		return MeleeEvents;
	}
	public busy getStatus() {
		return busy;
	}
	public miscCombat mC() {
		return miscCombat;
	}
	public scanWeapon sW() {
		return scanWeapon;
	}
	public Distances getDistance() {
		return Distances;
	}
	public AtObjects ATS() {
		return AtObjects;
	}
	public SkillInterfaces SK() {
		return SkillInterfaces;
	}
	public AgilityLog AL() {
		return AgilityLog;
	}
	public AgilityNet AN() {
		return AgilityNet;
	}
	public AgilityBranches ABS() {
		return AgilityBranches;
	}
	public AgilityPipes AP() {
		return AgilityPipes;
	}
	public Woodcutting wc() {
		return wc;
	}
	public Runecrafting getRuneCraft() {
		return Runecrafting;
	}
	public Crafting getCraft() {
		return Crafting;
	}
	public ThievingNPC THNPC() {
		return ThievingNPC;
	}
	public ThievingStalls THSTALLS() {
		return ThievingStalls;
	}
	public FireMaking Fire() {
		return FireMaking;
	}
	public Cooking Cooking() {
		return Cooking;
	}
	public QuestHandler QH() {
		return QuestHandler;
	}
	public Herblore getHerb() {
		return Herblore;
	}
	public Fishing fish() {
		return Fishing;
	}
	public boolean playerProcess() {
		if (delayA > 0 && delayAA) {
			delayA--;
		}
		if (delayA == 0 && delayAA) {
			AL().RunAgilityEnding(this);
		}
		if (stunDelay > 0 && isStunned) {
			stunDelay--;
		}
		if (stunDelay == 0 && isStunned) {
			isStunned = false;
		}			
		flushOutStream();
		if (globalMessage.length() > 0) {
			CAM().sendMessage(globalMessage);
			globalMessage = "";
		}
		CAM().sendQuest("@or1@Uptime: @gre@ "+ (server.uptime / 2 / 60) +" Minutes", 5450);//nex2 heade
		if (stallstun == true && stuntimer == 1) 
			stallstun = false;
		if (System.currentTimeMillis() - lastSpecialUpdate > 15000) {
			lastSpecialUpdate = System.currentTimeMillis();
			if (specialAmount < 100) {
				specialAmount = (specialAmount + 10);
				if (specialAmount > 95) 
					specialAmount = 100;
				
				sendSpecialBarAmount();
				specAttack();
			}
		} 
		if ((IsAttacking == true) && (deathStage == 0)) {
			if (PlayerHandler.players[AttackingOn] != null) {
				if (PlayerHandler.players[AttackingOn].currentHealth > 0) {
					getCombat().combatExecuter(playerId, AttackingOn);
					inCombat();
				} else {
					followPlayer = null;
					ResetAttack();
				}
			} else {
				followPlayer = null;
				ResetAttack();
			}
		}

		checkDamages();
		runEnergy();
		if (repeatSpell) {
			repeatSpell = false;
			getTheCurrentSpellPVP();
		}
		if (repeatSpellNpc) {
			repeatSpellNpc = false;
			getTheCurrentSpell();
		}
		if(inTrade && tradeResetNeeded){
			client o = (client) server.playerHandler.players[tradeWith];
			if(o != null){
				if(o.tradeResetNeeded){
					getTrade().resetTrade();
					o.getTrade().resetTrade();
				}
			}
		}
		if(walkingToItem) {
			int itemAmount = ItemHandler.itemAmount(pItemId, pItemX, pItemY);
			if(getX() == pItemX &&getY() == pItemY) {
				walkingToItem = false;
				addItem(pItemId, itemAmount);
				ItemHandler.removeItem(pItemId, pItemX, pItemY, itemAmount);
			}
		}
		//if (iscutting && cancut && wcwait >= 0) {

		if ((iscutting && cancut? true: false)== true && wcwait >= 0) {
			startAnimation(wcanim);
			CAM().sendMessage("wcwait= "+wcwait+".");
			wcwait = 5;
		}
		if (iscutting && cancut && wcwait >= 0 && GoodDistance(skillX, skillY, getX(), getY(), 1))
			wcwait = 5;
		if (ismining && canmine && minewait <= 0 && GoodDistance(skillX, skillY, getX(), getY(), 1)){
			startAnimation(mineanim);
			minewait = 5;
		}
		if (essMine && (System.currentTimeMillis() - lastAction >= 1500) && freeSlots() != 0) {
			lastAction = System.currentTimeMillis();
			addItem(7936, 1);
			startAnimation(mineanim);
			addSkillXP(5 * server.SERVER_EXPERIENCE, 14);
			CAM().sendMessage("You got a ore");
		}
		if (falltimer == 1) {
			teleportToX = 2478;
			if (absY + 1 == 3436) {
				teleportToY = absY;
			} else {
				teleportToY = absY + 1;
			}
			cumb2();
			resetCumb2();
		}
		if (falltimer2 == 1) {
			teleportToX = absX;
			teleportToY = absY;
			updateRequired = true;
			appearanceUpdateRequired = true;
			hasFinishedAgility = false;
				if(heightLevel == 1) {
					heightLevel = 0;
				} else if (heightLevel == 2) {
					heightLevel = 0;
				}
		}
		if (canthiev == true && thTimer > 0) 
			startAnimation(881);
		if (newDrain != 0 && drainPray) {
			if (System.currentTimeMillis() - lastdeduct > 100) {
				server.PrayerHandler.drainPrayer(playerId);
				CAM().sendQuest(" Prayer: "+playerLevel[5]+"/"+getLevelForXP(playerXP[5])+"", 687);
				CAM().sendFrame126(""+playerLevel[5]+"", 4012);
				lastdeduct = System.currentTimeMillis();	
			}
		}
		if (deathStage == 0 && currentHealth <= 0) {
			followThisPlayer = -1;
			deathStage = 1;
		}
		if (deathStage == 1) {
			WalkTo(0,0);
			deathStage = 2;
			delayDeath = System.currentTimeMillis();
		}
		if (deathStage == 2) {
			if (System.currentTimeMillis() - delayDeath > 1500) { 
				updateRequired = true;
				appearanceUpdateRequired = true;
				startAnimation(2304);				
				deathStage = 3;
			}
		}
		if (deathStage == 3) {
			if (System.currentTimeMillis() - delayDeath > 3500) {
				if (QH().q1 == 15) {
					teleportToX = Constants.DEATH_SPAWN_POINT_X + misc.random(1);
					teleportToY = Constants.DEATH_SPAWN_POINT_Y - misc.random(1);
				} else {
					teleportToX = Constants.STARTING_SPAWN_POINT_X;
					teleportToY = Constants.STARTING_SPAWN_POINT_Y;
				}
				youdied();
				followPlayer = null;
				server.PrayerHandler.resetPrayer(playerId);
				resetWalkingQueue();
				resetAnimation();
				specialAmount = 100;
				isFrozen = false;
				isPoisoned = false;
				skullTimer = 0;
				deathStage = 4;
				currentHealth = playerLevel[playerHitpoints];
				ResetAttackNPC();
				ResetAttack();
				frame1();
				heightLevel = 0;
				playerEnergy = 100;
				updateRequired = true;
				appearanceUpdateRequired = true;
				refreshhps();
				CAM().sendMessage("Oh dear you have died!.");
			}
		}
		if (getCraft().spinning && (System.currentTimeMillis() - lastAction >= 1900)) {
			lastAction = System.currentTimeMillis();
			getCraft().spin(this);
		}
		if (getCraft().crafting && (System.currentTimeMillis() - lastAction >= 1500)) {
			lastAction = System.currentTimeMillis();
			getCraft().craft(this);
		}
		if (hasFinishedAgilityNets) {
			AN().RunMiddleNets(this);
		}
		if (!fish().hasStoppedFishing(this)) {
			if (absX != getX && absY != getY) {
				isBusy = false;
			}
		}
		if (wctime <= 0 && iscutting)
		{
			if (GoodDistance(skillX, skillY, getX(), getY(), 2))
			{
				
			wc.doaction(this, wc.toadd, -1, -1, skillX, skillY, -1, wc.xp);
			}
			else 
			{
				iscutting = false;
				cancut = false;
			}
		}
		if (minetime <= 0 && ismining && GoodDistance(skillX, skillY, getX(), getY(), 1))
		{
			if (GoodDistance(skillX, skillY, getX(), getY(), 1))
			{
				mn.doaction(this, mn.toadd, -1, -1, skillX, skillY, -1, mn.xp);
			}
			else
			{
				ismining = false;
				canmine = false;
			}
		}
		if (hasFinishedAgilityBranches) {
			ABS().RunMiddleBranches(this);
		}
		if (hasFinishedAgilityPipes) {
			AP().RunMiddlePipes(this);
		}
			
		if ((NpcDialogue > 0) && (NpcDialogueSend == false)) {
			DA().UpdateNPCChat(this);
		}
		
		if (deathStage == 4 && System.currentTimeMillis() - delayDeath > 4500) { 
			deathStage = 0;
			frame1();
			stop();
			ResetAttackNPC();
			//setSidebarInterface(6, 1151); 
			//ancients = 0;
			followPlayer = null;
			updateRequired = true;
			appearanceUpdateRequired = true;
		}
		sendAllSpecialBars();
		rightClickCheck();
		if (isFrozen) 
			WalkTo(0,0);
		if (spamButton && (System.currentTimeMillis() - lastButton > 1100)) {
			lastButton = System.currentTimeMillis();
			if (currentButton >= 700) {
				currentButton = 1;
				currentStatus++;
			}
			if (currentStatus >= 255) {
				spamButton = false;
			}
			frame36(currentButton, currentStatus);
			currentButton++;
		}
		if ((animationReset > 0) && (System.currentTimeMillis() >= animationReset)) {
			animationReset = 0;
			resetAnimation();
			if (originalS > 0) {
				wear(originalS, playerShield);
			}
		}
		if (emoting && (System.currentTimeMillis() - lastAction > 300)) {
			lastAction = System.currentTimeMillis();
			pEmote++;
			updateRequired = true;
			appearanceUpdateRequired = true;
			CAM().sendMessage("Trying emote " + pEmote + "... ");
		}
		if (absY == 3518 || absY == 3519) 
		{
			if (showWarning == true) 
			{	
				CAM().showInterface(1908);
				showWarning = false;
				WalkTo(0,0);
				
			}
		}
		if (isInWilderness(absX, absY, 1)) 
		{
			if (multiZone()) 
				CAM().sendQuest("@yel@Multi: " + wildyLevel, 199);
			else 
				CAM().sendQuest("@yel@Level: " + wildyLevel, 199);
			
			if (!hasWildySign) 
			{
				hasWildySign = true;
				outStream.createFrame(208);
				outStream.writeWordBigEndian_dup(197);
				if (multiZone()) 
					CAM().sendQuest("@yel@Multi: " + wildyLevel, 199);
				else 
					CAM().sendQuest("@yel@Level: " + wildyLevel, 199);
				
				
			}
			int level = ((absY - 3520) / 8) + 1;
			if (level != wildyLevel) 
			{
				wildyLevel = level;
				if (multiZone()) 
					CAM().sendQuest("@yel@Multi: " + wildyLevel, 199);
				 else 
					CAM().sendQuest("@yel@Level: " + wildyLevel, 199);
				

			}
		}
		if (!isInWilderness(absX, absY, 1)) 
		{
			wildysigndisappear();
			hasWildySign = false;
		}
		CAM().sendQuest("" + currentHealth, 4016);
		long current = System.currentTimeMillis();
		if (inCombat && (current - lastCombat >= 20000)) 
			inCombat = false;
		if ((GObjChange == 1) && (GObjSet == 1)) {
			commitobj();
			GObjChange = 0;
		}
		if (resetanim <= 0) {
			resetAnimation();
			resetanim = 8;
		}
		if ((AnimationReset == true) && (actionTimer <= 0)) {
			resetAnimation();
			AnimationReset = false;
			emotes = 0;
			pEmote = playerSE;
			updateRequired = true;
			appearanceUpdateRequired = true;
		}
		AddDroppedItems();
		if (UpdateShop == true) {
			resetItems(3823);
			resetShop(MyShopID);
		}
		if (stairs > 0) {
			if (GoodDistance(skillX, skillY, absX, absY, stairDistance) == true) 
				stairs(stairs, absX, absY);
			
		}
		if (WanneBank > 0) {
			if (GoodDistance(skillX, skillY, absX, absY, WanneBank) == true) {
				openUpBank();
				WanneBank = 0;
			}
		}
		if (WanneShop > 0) {
			if (GoodDistance(skillX, skillY, absX, absY, 1) == true) {
				openUpShop(WanneShop);
				WanneShop = 0;
			}
		}
		long thisTime = System.currentTimeMillis();
		if (IsAttackingNPC == true && deathStage == 0) {
			if (server.npcHandler.npcs[attacknpc] != null) {
				if (server.npcHandler.npcs[attacknpc].IsDead == false && server.npcHandler.npcs[attacknpc].MaxHP > 0) {
					AttackNPC();
					inCombat();
				} else {
					ResetAttackNPC();
				}
			} else {
				ResetAttackNPC();	
			}
		}
		if (NpcWanneTalk == 2) {
			// Bank Booth
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
		return true;

		
	}


	public boolean timers() {
		if (vengDelay > 0) vengDelay--;
		if (actionTimer > 0) actionTimer--;
		if (teleportThis > 0) teleportThis--;
		if (teleportThis2 > 0) teleportThis2--;
		if (kniveDelay > 0) kniveDelay--;
		if (yellTimer > 0) yellTimer--;
		if (stuntimer > 0) stuntimer--;
		if (ClickCount > 0) ClickCount -= 1;
		if (wctime > 0) wctime--;
		if (minewait > 0) minewait--;
		if (minetime > 0) minetime--;
		if (falltimer > 0) falltimer--;
		if (falltimer2 > 0) falltimer2--;
		if (upNetTimer > 0) upNetTimer--;
		if (branchTimer > 0) branchTimer--;
		if (pipesTimer > 0) pipesTimer--;
		if (thTimer > 0) thTimer--;
		actionAmount--;
		if (actionAmount < 0) actionAmount = 0;
		if (actionTimer > 0) actionTimer -= 1;
		return true;
	}
}