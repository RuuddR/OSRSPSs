package server.npcs;

import server.*;
import server.players.*;
import server.util.*;
import server.items.*;
import server.world.*;
import server.npcs.Combat.*;


public class NPC {
	public int walkBackTimer = 0;
	public boolean needsWalkBack = false;
	public int count = 0;
	public boolean hitMage = false;
	public int hitPlayerId = 0;
	public boolean usingMagic;
	public int finishMagic = 0;
	public String playerName = "";
	public int playerId = 0;
	public boolean inCombat = false;	
	public int combatTimer = 0;
	public int combatType = 1;
	public int maxHit = 1;
	public int defenceLevel = 0;
	public int attackLevel = 0;
	public int attackDelay = 5;
	public int damageDelay = 1;//used for timing
	public int damageDelay2 = 1;//used for no combat glitchs.
	public boolean aggressive = false;
	public int aggressiveDistance = 0;
	public int handAnimation = -1;
	public int handGFX = -1;
	public int npcProjectile = -1;
	public int endGFX = -1;
	public int npcSize = 4;
	public int hitIDNPC;
	public boolean waiting = false;
	public int offTimerNPC;
    	public boolean FaceDirection;
    	public int FocusPointX;
    	public int FocusPointY;
	public int npcDefence;
	public String getWho = "";
	public int face;
	public int absX, absY;
	public int aggroRadius = 0;
	public int freezeTimer = -1;
	public boolean GraphicsUpdateRequired;
	public int hitDiff2;
	public boolean hit2;
	public boolean animUpdateRequired;
	public boolean dirUpdateRequired;
	public int walkAwayTimer = 0;
	public int[] effects = new int[10];
	public boolean faceToUpdateRequired;
	public long updateDelay;
	public int heightLevel;
	public boolean hit = false;
	public boolean faceUpdateRequired;
	public boolean hitUpdateRequired, hitUpdateRequired2;
	public int HP, MaxHP, hitDiff, MaxHit, animNumber, actionTimer, StartKilling, enemyX, enemyY;
	public boolean IsDead, DeadApply, NeedRespawn, IsUnderAttack, IsClose, Respawns, IsUnderAttackNpc, IsAttackingNPC, walkingToPlayer, followingPlayer;
	public int[] Killing = new int[PlayerHandler.maxPlayers];
	public int makeX, makeY, moverangeX1, moverangeY1, moverangeX2, moverangeY2, moveX, moveY, direction, walkingType, attacknpc, followPlayer;
	public int npcId;
	public int npcType;
	public int PoisonClear = 0;
	public int poisonDelay = -1;
	public int PoisonDelay = 999999;
	public boolean poisonDmg = false;
	public int poisonTimer = -1;
	public boolean RandomWalk;
	public int reducedAttack = -1;
	public int respawnTime = 60, attackEmote = 0x326, deathEmote = 0x900; // in
	public int spawnX, spawnY;
	public String textUpdate;
	public boolean textUpdateRequired;
	public boolean updateRequired;
	public int viewX, viewY;

	public boolean hitPlayer(int npc) {
		int playerDef = 0;
		if (npc < 1) {
			return false;
		}
		NPC thisNPC = (NPC) server.npcHandler.npcs[npc];
		int player = hitPlayerId;
		if (player < 1) {
			cB().destructNPC();
			return false;
		}
		client thisPLAYER = (client) server.playerHandler.players[player];
		if (thisPLAYER != null) {
			
			if (thisNPC.combatType == 1) {
				playerDef = misc.random(thisPLAYER.meleeDefence());
			}
			if (thisNPC.combatType == 2) {
				playerDef = misc.random(((thisPLAYER.playerLevel[1]) / 4) + thisPLAYER.playerBonus[9]);
			}
			if (thisNPC.combatType == 3) {
				playerDef = misc.random(((thisPLAYER.playerLevel[1]) / 3) + thisPLAYER.playerBonus[8]);
			}
			int npcAttack = misc.random(attackLevel);
			
			if (npcAttack > playerDef) {
				return true;
			} else {
				return false;
			}
		} 
		return true;
	}

	public Combat Combat = new Combat(this);

	public Combat cB() {
		return Combat;
	}

	public void updateface(stream Str) {
		Str.writeWord(face);
	}
	public void faceplayer(int i) {
		face = i + 32768;
		faceUpdateRequired = true;

		if (System.currentTimeMillis() - updateDelay >= 5000) {
			updateRequired = true;
			updateDelay = System.currentTimeMillis();
		}
	}

	public int GetMove(int Place1,int Place2){ // Thanks to diablo for this! Fixed my npc follow code <3
		if ((Place1 - Place2) == 0) {
			return 0;
		} else if ((Place1 - Place2) < 0) {
			return 1;
		} else if ((Place1 - Place2) > 0) {
			return -1;
		}
		return 0;
	}
	public void FollowPlayerCB(int NPCID, int playerID) {
		int playerX = server.playerHandler.players[playerID].getABSX();
		int playerY = server.playerHandler.players[playerID].getABSY(); 
		RandomWalk = false;
		if (GoodDistance(playerX, playerY, absX, absY, 1)) {
			return;
		}
		if (server.playerHandler.players[playerID] != null) {
			if (playerY < absY) {
				moveX = GetMove(absX, playerX);
				moveY = GetMove(absY, playerY + 1);
			} else if (playerY > absY) {
				moveX = GetMove(absX, playerX);
				moveY = GetMove(absY, playerY - 1);
			} else if (playerX < absX) {
				moveX = GetMove(absX, playerX + 1);
				moveY = GetMove(absY, playerY);
			} else if (playerX >absX) {
				moveX = GetMove(absX, playerX - 1);
				moveY = GetMove(absY, playerY);
			}
			getNextNPCMovement();
			updateRequired = true;
		}
	}
	public boolean toFarAway(int i, int ii) {
		if (i < 1 || ii < 1) {
			return false;	
		}
		int X = 0;
		int Y = 0;
		client other = (client) server.playerHandler.players[ii];
		NPC thisNPC = (NPC) server.npcHandler.npcs[i];	
		if (thisNPC.absX > other.absX) {
			X = thisNPC.absX - other.absX;
		} else if (thisNPC.absX < other.absX) {
			X = other.absX - thisNPC.absX;
		} else if (thisNPC.absX == other.absX) {
			return false;
		}
		if (thisNPC.absY > other.absY) {
			Y = thisNPC.absY - other.absY;
		} else if (thisNPC.absY < other.absY) {
			Y = other.absY - thisNPC.absY;
		} else if (thisNPC.absY == other.absY) {
			return false;
		}
		if (X > 1 || Y > 1) {
			return false;
		} else {	
			return true;
		}
	}

 	public NPC(int _npcId, int _npcType) {
		npcId = _npcId;
		npcType = _npcType;
		direction = -1;
		IsDead = false;
		DeadApply = false;
		actionTimer = 0;
		RandomWalk = true;
		StartKilling = 0;
		IsUnderAttack = false;
		IsClose = false;
		for (int i = 0; i < Killing.length; i++) {
			Killing[i] = 0;
		}
	}
	public void NPCss(int _npcId, int _npcType) {
		npcId = _npcId;
		npcType = _npcType;
		direction = -1;
		IsDead = false;
		DeadApply = false;
		actionTimer = 0;
		RandomWalk = true;
		StartKilling = 0;
		IsUnderAttack = false;
		IsClose = false;
		for (int i = 0; i < Killing.length; i++) {
			Killing[i] = 0;
		}
	}
 	public boolean GoodDistance(int spotX, int spotY, int playerX, int playerY, int distance) {
		for (int i = -1; i <= distance; i++) {
			for (int j = 0; j <= distance; j++) {
				if ((spotX + i) == playerX && ((spotY + j) == playerY || (spotY - j) == playerY || absY == playerY)) {
                    			return true;
                		} else if ((spotX - i) == playerX && ((spotY + j) == playerY || (spotY - j) == playerY || spotY == playerY)) {
                    			return true;
                		} else if (spotX == playerX && ((spotY + j) == playerY || (spotY - j) == playerY || spotY == playerY)) {
                    			return true;
                		}
            		}
        	}
        	return false;
    	}
	public void npcGfxHigh(int gfx, int height) {
		mask80var1 = gfx;
        		mask80var2 = 65536 + height;
		mask80update = true;
        		mask80update = true;
		updateRequired = true;
	}
	public void gfx100(int gfx){
		
		mask80var1 = gfx;
       	 	mask80var2 = 6553600;
        	mask80update = true;
		updateRequired = true;
	}
	public void Lowgfx(int I) {
        	mask80var1 = I;
        	mask80var2 = 0;
        	mask80update = true;
        	updateRequired = true;
    	}
	public boolean WithinDistance(int i, int j, int k, int l, int i1) {
        	for(int j1 = 0; j1 <= i1; j1++) {
           		for(int k1 = 0; k1 <= i1; k1++) {
                		if(i + j1 == k && (j + k1 == l || j - k1 == l || j == l)) {
                    			return true;
               	 		}
                		if(i - j1 == k && (j + k1 == l || j - k1 == l || j == l)) {
                    			return true;
                		}
                		if(i == k && (j + k1 == l || j - k1 == l || j == l)) {
                    			return true;
                		}
            		}
		}
		return false;
   	}
	private void appendSetFocusDestination(stream stream1) {
        	if(stream1 != null) {
           	 	stream1.writeWordBigEndian(FocusPointX);
            		stream1.writeWordBigEndian(FocusPointY);
        	}
    	}
	public void appendAnimUpdate(stream str) {
		str.writeWordBigEndian(animNumber);
		str.writeByte(1);
	}
	public void appendDirUpdate(stream str) {
		str.writeWord(direction);
	}
	public void appendFaceToUpdate(stream str) {
		str.writeWordBigEndian(viewX);
		str.writeWordBigEndian(viewY);
	}
	protected void appendHitUpdate(stream str) {
		try {
			if (hit == true) {HP -= hitDiff;hit = false;}
			if (HP <= 0) {
				IsDead = true;
			}
			str.writeByteC(hitDiff); // What the npc got 'hit' for
			if ((hitDiff > 0) && !poisonDmg) {
				str.writeByteS(1); // 0: red hitting - 1: blue hitting
			} else if ((hitDiff > 0) && poisonDmg) {
				str.writeByteS(2); // 0: red hitting - 1: blue hitting
			} else {
				str.writeByteS(0); // 0: red hitting - 1: blue hitting
			}
			str.writeByteS(misc.getCurrentHP(HP, MaxHP, 100));
			str.writeByteC(100);
			poisonDmg = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void appendMask80Update(stream str) {
		str.writeWord(mask80var1);
	    	str.writeDWord(mask80var2);
    	}

	public int mask80var1;
    	public int mask80var2;
    	protected boolean mask80update = false;

	public void appendNPCUpdateBlock(stream str) {	
		boolean faceUp = false;
		if (!updateRequired) return;
		int updateMask = 0;
		if (animUpdateRequired) updateMask |= 0x10;
		if (hitUpdateRequired2) updateMask |= 0x8;
       		if (mask80update)  updateMask |= 0x80;
		if (dirUpdateRequired) updateMask |= 0x20;
		if (textUpdateRequired) updateMask |= 1;
		if (hitUpdateRequired) updateMask |= 0x40;
		if (faceToUpdateRequired) updateMask |= 0x20;
            	if(FaceDirection) updateMask |= 4;
		if (faceUpdateRequired && updateMask == 0) {
			updateMask |= 0x20;
			faceUp = true;
		}
		str.writeByte(updateMask);
		if (animUpdateRequired) appendAnimUpdate(str);
		if (hitUpdateRequired2) appendHitUpdate2(str);
		if (mask80update) appendGraphicUpdate(str);
		if (dirUpdateRequired) appendDirUpdate(str);
		if (faceToUpdateRequired) appendFaceToUpdate(str);
		if (textUpdateRequired) str.writeString(textUpdate);
		if (hitUpdateRequired) appendHitUpdate(str);
		if(FaceDirection) appendSetFocusDestination(str);
		if (faceUpdateRequired && faceUp) {
			updateface(str);
		}
	}
 	public void appendGraphicUpdate(stream str) {
        	str.writeWord(mask80var1);
        	str.writeDWord(mask80var2);
	}
	private void appendHitUpdate2(stream str) {
        	if (str != null) {
            		if (HP <= 0) {
                		IsDead = true;
            		}
            		str.writeByteA(hitDiff2);
            		if (HP <= 0) {
                		IsDead = true;
            		}
            		if (hitDiff2 > 0) {
                    		str.writeByteC(1);
                	} else {
                    		str.writeByteC(0);
                	}
           		if (HP > 0) {
                		str.writeByteA(misc.getCurrentHP(HP, MaxHP, 100));
            		} else {
                		str.writeByteA(1);
            		}
            		str.writeByte(100);
        	}
    	}
	public void clearUpdateFlags() {
		updateRequired = false;
		textUpdateRequired = false;
		hitUpdateRequired = false;
		hitUpdateRequired2 = false;
		animUpdateRequired = false;
		mask80update = false;
		dirUpdateRequired = false;
		textUpdate = null;
	        FaceDirection = false;
		moveX = 0;
		moveY = 0;
		direction = -1;
	}
	public int getKiller() {
		int Killer = 0;
		int Count = 0;
		for (int i = 1; i < PlayerHandler.maxPlayers; i++) {
			if (false) {
				Killer = i;
				Count = 1;
			} else {
				if (Killing[i] > Killing[Killer]) {
					Killer = i;
					Count = 1;
				} else if (Killing[i] == Killing[Killer]) {
					Count++;
				}
			}
		}
		return Killer;
	}
	public void getNextNPCMovement() {
        	if (freezeTimer <= 0) {
			direction = -1;
			direction = getNextWalkingDirection();
		}
	}
	public int getNextWalkingDirection() {
		int dir;
		dir = misc.direction(absX, absY, (absX + moveX), (absY + moveY));
		if (dir == -1)
			return -1;
		dir >>= 1;
		absX += moveX;
		absY += moveY;
		return dir;
	}
	public void updateNPCMovement(stream str) {
		if (direction == -1) {
			if (updateRequired) {
				str.writeBits(1, 1);
				str.writeBits(2, 0);
			} else {
				str.writeBits(1, 0);
			}
		} else {
			str.writeBits(1, 1);
			str.writeBits(2, 1); // updateType
			str.writeBits(3, misc.xlateDirectionToClient[direction]);
			if (updateRequired) {
				str.writeBits(1, 1); 
			} else {
				str.writeBits(1, 0);
			}
		}
	}

}
