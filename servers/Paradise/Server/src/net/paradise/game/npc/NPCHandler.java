package net.paradise.game.npc;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

import net.paradise.game.Processable;
import net.paradise.game.item.Item;
import net.paradise.game.item.Item2;
import net.paradise.game.item.Item3;
import net.paradise.game.item.ItemHandler;
import net.paradise.game.player.Client;
import net.paradise.game.player.Player;
import net.paradise.game.player.PlayerHandler;
import net.paradise.util.MethodCollection;

public class NPCHandler implements Processable
{

	private static NPCHandler singleton;

	private static final Random random = new Random();
	public static int maxNPCs = 10000;
	public static int maxListedNPCs = 10000;
	public static int maxNPCDrops = 10000;
	public static NPC npcs[] = new NPC[maxNPCs];
	public static NPCList NpcList[] = new NPCList[maxListedNPCs];
	private static NPCDrops NpcDrops[] = new NPCDrops[maxNPCDrops];

	public NPCHandler()
	{
		singleton = this;

		for (int i = 0; i < maxNPCs; i++)
		{
			npcs[i] = null;
		}

		for (int i = 0; i < maxListedNPCs; i++)
		{
			NpcList[i] = null;
		}

		for (int i = 0; i < maxNPCDrops; i++)
		{
			NpcDrops[i] = null;
		}

		loadNPCList("config/npc.cfg");
		loadNPCDrops("config/npcdrops.cfg");
		loadAutoSpawn("config/autospawn.cfg");
	}

	public static NPCHandler getSingleton()
	{
		return singleton;
	}

	public void newNPC(int npcType, int x, int y, int heightLevel, int rangex1,
			int rangey1, int rangex2, int rangey2, int WalkingType, int HP,
			boolean Respawns)
	{
		int slot = -1;
		for (int i = 1; i < maxNPCs; i++)
		{
			if (npcs[i] == null)
			{
				slot = i;
				break;
			}
		}
		if (slot == -1)
		{
			return;
		}

		if (HP <= 0)
		{
			HP = 3000;
		}
		NPC npc = new NPC(slot, npcType);
		npc.absX = x;
		npc.absY = y;
		npc.makeX = x;
		npc.makeY = y;
		npc.moverangeX1 = rangex1;
		npc.moverangeY1 = rangey1;
		npc.moverangeX2 = rangex2;
		npc.moverangeY2 = rangey2;
		npc.walkingType = WalkingType;
		npc.HP = HP;
		npc.MaxHP = HP;
		npc.MaxHit = (int) Math.floor((HP / 100));
		if (npc.MaxHit < 1)
		{
			npc.MaxHit = 1;
		}
		npc.heightLevel = heightLevel;
		npc.Respawns = Respawns;
		npcs[slot] = npc;
	}

	public void newSummonedNPC(int npcType, int x, int y, int heightLevel,
			int rangex1, int rangey1, int rangex2, int rangey2,
			int WalkingType, int HP, boolean Respawns, int summonedBy)
	{
		int slot = -1;
		for (int i = 1; i < maxNPCs; i++)
		{
			if (npcs[i] == null)
			{
				slot = i;
				break;
			}
		}
		if (slot == -1)
		{
			return;
		}
		if (HP <= 0)
		{
			HP = 3000;
		}
		NPC newNPC = new NPC(slot, npcType);
		newNPC.absX = x;
		newNPC.absY = y;
		newNPC.makeX = x;
		newNPC.makeY = y;
		newNPC.moverangeX1 = rangex1;
		newNPC.moverangeY1 = rangey1;
		newNPC.moverangeX2 = rangex2;
		newNPC.moverangeY2 = rangey2;
		newNPC.walkingType = WalkingType;
		newNPC.HP = HP;
		newNPC.MaxHP = HP;
		newNPC.MaxHit = (int) Math.floor((HP / 100));
		if (newNPC.MaxHit < 1)
		{
			newNPC.MaxHit = 10;
		}
		newNPC.heightLevel = heightLevel;
		newNPC.Respawns = Respawns;
		newNPC.followPlayer = summonedBy;
		newNPC.followingPlayer = true;
		npcs[slot] = newNPC;
	}

	public void newNPCList(int npcType, String npcName, int combat, int HP)
	{
		int slot = -1;
		for (int i = 0; i < maxListedNPCs; i++)
		{
			if (NpcList[i] == null)
			{
				slot = i;
				break;
			}
		}
		if (slot == -1)
		{
			return;
		}
		NPCList newNPCList = new NPCList(npcType);
		newNPCList.npcName = npcName;
		newNPCList.npcCombat = combat;
		newNPCList.npcHealth = HP;
		NpcList[slot] = newNPCList;
	}

	public void newNPCDrop(int npcType, int dropType, int Items[], int ItemsN[])
	{
		int slot = -1;
		for (int i = 0; i < maxNPCDrops; i++)
		{
			if (NpcDrops[i] == null)
			{
				slot = i;
				break;
			}
		}
		if (slot == -1)
		{
			return;
		}
		NPCDrops newNPCDrop = new NPCDrops(npcType);
		newNPCDrop.DropType = dropType;
		newNPCDrop.Items = Items;
		newNPCDrop.ItemsN = ItemsN;
		NpcDrops[slot] = newNPCDrop;
	}

	public int GetMove(int Place1, int Place2)
	{
		if ((Place1 - Place2) == 0)
		{
			return 0;
		}
		else if ((Place1 - Place2) < 0)
		{
			return 1;
		}
		else if ((Place1 - Place2) > 0)
		{
			return -1;
		}
		return 0;
	}

	public void FollowPlayer(int NPCID)
	{
		int follow = npcs[NPCID].followPlayer;
		int playerX = PlayerHandler.players[follow].absX;
		int playerY = PlayerHandler.players[follow].absY;
		npcs[NPCID].RandomWalk = false;
		if (PlayerHandler.players[follow] != null)
		{
			if (playerY < npcs[NPCID].absY)
			{
				npcs[NPCID].moveX = GetMove(npcs[NPCID].absX, playerX);
				npcs[NPCID].moveY = GetMove(npcs[NPCID].absY, playerY + 1);
			}
			else if (playerY > npcs[NPCID].absY)
			{
				npcs[NPCID].moveX = GetMove(npcs[NPCID].absX, playerX);
				npcs[NPCID].moveY = GetMove(npcs[NPCID].absY, playerY - 1);
			}
			else if (playerX < npcs[NPCID].absX)
			{
				npcs[NPCID].moveX = GetMove(npcs[NPCID].absX, playerX + 1);
				npcs[NPCID].moveY = GetMove(npcs[NPCID].absY, playerY);
			}
			else if (playerX > npcs[NPCID].absX)
			{
				npcs[NPCID].moveX = GetMove(npcs[NPCID].absX, playerX - 1);
				npcs[NPCID].moveY = GetMove(npcs[NPCID].absY, playerY);
			}
			npcs[NPCID].getNextNPCMovement();
			npcs[NPCID].updateRequired = true;
		}
	}

	public void FollowPlayerCB(int id, int playerID)
	{
		int playerX = PlayerHandler.players[playerID].absX;
		int playerY = PlayerHandler.players[playerID].absY;
		npcs[id].RandomWalk = false;
		if (PlayerHandler.players[playerID] != null)
		{
			if (playerY < npcs[id].absY)
			{
				npcs[id].moveX = GetMove(npcs[id].absX, playerX);
				npcs[id].moveY = GetMove(npcs[id].absY, playerY + 1);
			}
			else if (playerY > npcs[id].absY)
			{
				npcs[id].moveX = GetMove(npcs[id].absX, playerX);
				npcs[id].moveY = GetMove(npcs[id].absY, playerY - 1);
			}
			else if (playerX < npcs[id].absX)
			{
				npcs[id].moveX = GetMove(npcs[id].absX, playerX + 1);
				npcs[id].moveY = GetMove(npcs[id].absY, playerY);
			}
			else if (playerX > npcs[id].absX)
			{
				npcs[id].moveX = GetMove(npcs[id].absX, playerX - 1);
				npcs[id].moveY = GetMove(npcs[id].absY, playerY);
			}
			npcs[id].getNextNPCMovement();
			npcs[id].updateRequired = true;
		}
	}

	public boolean IsInWorldMap(int coordX, int coordY)
	{
		return true;
	}

	public boolean IsInWorldMap2(int coordX, int coordY)
	{
		for (int i = 0; i < worldmap2[0].length; i++)
		{
			if (worldmap2[0][i] == coordX && worldmap2[1][i] == coordY)
			{
				return true;
			}
		}
		return false;
	}

	public boolean IsInRange(int NPCID, int MoveX, int MoveY)
	{
		int NewMoveX = (npcs[NPCID].absX + MoveX);
		int NewMoveY = (npcs[NPCID].absY + MoveY);
		if (NewMoveX <= npcs[NPCID].moverangeX1
				&& NewMoveX >= npcs[NPCID].moverangeX2
				&& NewMoveY <= npcs[NPCID].moverangeY1
				&& NewMoveY >= npcs[NPCID].moverangeY2)
		{
			if ((npcs[NPCID].walkingType == 1 && IsInWorldMap(NewMoveX,
					NewMoveY) == true)
					|| (npcs[NPCID].walkingType == 2 && IsInWorldMap2(NewMoveX,
							NewMoveY) == false))
			{
				if (MoveX == MoveY)
				{
					if ((IsInWorldMap(NewMoveX, npcs[NPCID].absY) == true && IsInWorldMap(
							npcs[NPCID].absX, NewMoveY) == true)
							|| (IsInWorldMap2(NewMoveX, npcs[NPCID].absY) == false && IsInWorldMap2(
									npcs[NPCID].absX, NewMoveY) == false))
					{
						return true;
					}
					return false;
				}
				return true;
			}
		}
		return false;
	}

	public void PoisonNPC(int NPCID)
	{
		npcs[NPCID].PoisonClear = 0;
		npcs[NPCID].PoisonDelay = 40;
	}

	public void Poison(int NPCID)
	{
		if (npcs[NPCID].PoisonDelay <= 1)
		{
			int hitDiff = 3 + MethodCollection.random(15);
			npcs[NPCID].poisondmg = true;
			NPCHandler.npcs[NPCID].hitDiff = hitDiff;
			npcs[NPCID].updateRequired = true;
			npcs[NPCID].hitUpdateRequired = true;
			npcs[NPCID].PoisonClear++;
			npcs[NPCID].PoisonDelay = 40;
		}
	}

	public void process()
	{
		for (int i = 0; i < maxNPCs; i++)
		{
			if (npcs[i] == null)
			{
				continue;
			}
			npcs[i].clearUpdateFlags();
		}
		for (int i = 0; i < maxNPCs; i++)
		{
			if (npcs[i] != null)
			{
				if (npcs[i].actionTimer > 0)
				{
					npcs[i].actionTimer--;
				}
				Poison(i);
				npcs[i].PoisonDelay -= 1;
				if (npcs[i].PoisonClear >= 15)
				{
					npcs[i].PoisonDelay = 9999999;
				}
				if (npcs[i].IsDead == false)
				{
					if (npcs[i].id == 1268 || npcs[i].id == 1266)
					{
						for (int j = 1; j < PlayerHandler.maxPlayers; j++)
						{
							if (PlayerHandler.players[j] != null)
							{
								if (GoodDistance(npcs[i].absX, npcs[i].absY,
										PlayerHandler.players[j].absX,
										PlayerHandler.players[j].absY, 2) == true
										&& npcs[i].IsClose == false)
								{
									npcs[i].actionTimer = 2;
									npcs[i].IsClose = true;
								}
							}
						}
						if (npcs[i].actionTimer == 0 && npcs[i].IsClose == true)
						{
							for (int j = 1; j < PlayerHandler.maxPlayers; j++)
							{
								if (PlayerHandler.players[j] != null)
								{
									PlayerHandler.players[j].RebuildNPCList = true;
								}
							}
							if (npcs[i].Respawns)
							{
								int old1 = (npcs[i].id - 1);
								int old2 = npcs[i].makeX;
								int old3 = npcs[i].makeY;
								int old4 = npcs[i].heightLevel;
								int old5 = npcs[i].moverangeX1;
								int old6 = npcs[i].moverangeY1;
								int old7 = npcs[i].moverangeX2;
								int old8 = npcs[i].moverangeY2;
								int old9 = npcs[i].walkingType;
								int old10 = npcs[i].MaxHP;
								npcs[i] = null;
								newNPC(old1, old2, old3, old4, old5, old6,
										old7, old8, old9, old10, true);
							}
						}
					}
					else if (npcs[i].RandomWalk == true
							&& MethodCollection.random2(10) == 1
							&& npcs[i].moverangeX1 > 0
							&& npcs[i].moverangeY1 > 0
							&& npcs[i].moverangeX2 > 0
							&& npcs[i].moverangeY2 > 0)
					{
						int MoveX = MethodCollection.random(1);
						int MoveY = MethodCollection.random(1);
						int Rnd = MethodCollection.random2(4);
						if (Rnd == 1)
						{
							MoveX = -(MoveX);
							MoveY = -(MoveY);
						}
						else if (Rnd == 2)
						{
							MoveX = -(MoveX);
						}
						else if (Rnd == 3)
						{
							MoveY = -(MoveY);
						}
						if (IsInRange(i, MoveX, MoveY) == true)
						{
							npcs[i].moveX = MoveX;
							npcs[i].moveY = MoveY;
						}
						npcs[i].updateRequired = true;
					}
					else if (npcs[i].RandomWalk == false
							&& npcs[i].IsUnderAttack == true)
					{
						if (npcs[i].id == 1645 || npcs[i].id == 1241
								|| npcs[i].id == 1246 || npcs[i].id == 1159
								|| npcs[i].id == 54)
						{
							AttackPlayerMage(i);
						}
						else
						{
							AttackPlayer(i);
						}
					}
					else if (npcs[i].followingPlayer
							&& npcs[i].followPlayer > 0
							&& PlayerHandler.players[npcs[i].followPlayer] != null)
					{
						if (PlayerHandler.players[npcs[i].followPlayer].AttackingOn > 0)
						{
							int follow = npcs[i].followPlayer;
							npcs[i].StartKilling = PlayerHandler.players[follow].AttackingOn;
							npcs[i].RandomWalk = true;
							npcs[i].IsUnderAttack = true;
							if (npcs[i].StartKilling > 0)
							{
								if (npcs[i].id == 1645 || npcs[i].id == 509
										|| npcs[i].id == 1241
										|| npcs[i].id == 1246
										|| npcs[i].id == 54)
								{
									AttackPlayerMage(i);
								}
								else
								{
									AttackPlayer(i);
								}
							}
						}
						else
						{
							FollowPlayer(i);
						}
					}
					else if (npcs[i].followingPlayer
							&& npcs[i].followPlayer > 0
							&& PlayerHandler.players[npcs[i].followPlayer] != null)
					{
						if (PlayerHandler.players[npcs[i].followPlayer].attacknpc > 0)
						{
							int follow = npcs[i].followPlayer;
							npcs[i].attacknpc = PlayerHandler.players[follow].attacknpc;
							npcs[i].IsUnderAttackNpc = true;
							npcs[npcs[i].attacknpc].IsUnderAttackNpc = true;
							if (npcs[i].attacknpc > 0)
							{
								if (npcs[i].id == 1645 || npcs[i].id == 1241
										|| npcs[i].id == 1246)
								{
									AttackNPCMage(i);
								}
								else
								{
									AttackNPC(i);
								}
							}
						}
						else
						{
							FollowPlayer(i);
						}
					}
					else if (npcs[i].IsUnderAttackNpc == true)
					{
						AttackNPC(i);
					}
					if (npcs[i].RandomWalk == true)
					{
						npcs[i].getNextNPCMovement();
					}
					if (npcs[i].id == 81 || npcs[i].id == 397
							|| npcs[i].id == 1766 || npcs[i].id == 1767
							|| npcs[i].id == 1768)
					{
						if (MethodCollection.random2(50) == 1)
						{
							npcs[i].updateRequired = true;
							npcs[i].textUpdateRequired = true;
							npcs[i].textUpdate = "Moo!";
						}
					}
					if (npcs[i].id == 1063)
					{
						if (MethodCollection.random2(30) <= 2)
						{
							npcs[i].updateRequired = true;
							npcs[i].textUpdateRequired = true;
							npcs[i].textUpdate = "Welcome to Paradise 317!";
						}
					}
					if (npcs[i].id == 541)
					{
						if (MethodCollection.random2(30) <= 2)
						{
							npcs[i].updateRequired = true;
							npcs[i].textUpdateRequired = true;
							npcs[i].textUpdate = "Fun Pk Area, Items Not Lost!";
						}
					}
					if (npcs[i].id == 2862)
					{
						if (MethodCollection.random2(30) <= 2)
						{
							npcs[i].updateRequired = true;
							npcs[i].textUpdateRequired = true;
							npcs[i].textUpdate = "Welcome To Paradise!";
						}
					}
					if (npcs[i].id == 551)
					{
						if (MethodCollection.random2(30) <= 2)
						{
							npcs[i].updateRequired = true;
							npcs[i].textUpdateRequired = true;
							npcs[i].textUpdate = "Get Mantas Here!";
						}
					}
					if (npcs[i].id == 268)
					{
						if (MethodCollection.random2(30) <= 2)
						{
							npcs[i].updateRequired = true;
							npcs[i].textUpdateRequired = true;
							npcs[i].textUpdate = "BEWARE: NO RULES IF YOU ENTER THIS PORTAL!";
						}
					}
					if (npcs[i].id == 2253)
					{
						if (MethodCollection.random2(30) <= 2)
						{
							npcs[i].updateRequired = true;
							npcs[i].textUpdateRequired = true;
							npcs[i].textUpdate = "Click Ladder To Fish and Thieve!";
						}
					}
					if (npcs[i].id == 2821)
					{
						if (MethodCollection.random2(30) <= 2)
						{
							npcs[i].updateRequired = true;
							npcs[i].textUpdateRequired = true;
							npcs[i].textUpdate = "Fishing Portal!";
						}
					}
					if (npcs[i].id == 2304)
					{
						if (MethodCollection.random2(30) <= 2)
						{
							npcs[i].updateRequired = true;
							npcs[i].textUpdateRequired = true;
							npcs[i].textUpdate = "Farming Shop! Buy seed's for patch's!";
						}
					}
					if (npcs[i].id == 461)
					{
						if (MethodCollection.random2(30) <= 2)
						{
							npcs[i].updateRequired = true;
							npcs[i].textUpdateRequired = true;
							npcs[i].textUpdate = "Magic Runes!";
						}
					}
					if (npcs[i].id == 522)
					{
						if (MethodCollection.random2(30) <= 2)
						{
							npcs[i].updateRequired = true;
							npcs[i].textUpdateRequired = true;
							npcs[i].textUpdate = "Last Minute Pk Shop!";
						}
					}
					if (npcs[i].id == 57)
					{
						if (MethodCollection.random2(20) == 1)
						{
							npcs[i].updateRequired = true;
							npcs[i].textUpdateRequired = true;
							int players = PlayerHandler.getPlayerCount();
							npcs[i].textUpdate = "Players Online: " + players;
						}
					}
					if (npcs[i].id == 550)
					{
						if (MethodCollection.random2(30) <= 2)
						{
							npcs[i].updateRequired = true;
							npcs[i].textUpdateRequired = true;
							npcs[i].textUpdate = "Pk Shop!";
						}
					}
					if (npcs[i].id == 1596)
					{
						if (MethodCollection.random2(30) <= 2)
						{
							npcs[i].updateRequired = true;
							npcs[i].textUpdateRequired = true;
							npcs[i].textUpdate = "PK Rewards Shop!";
						}
					}
					if (npcs[i].id == 949)
					{
						if (MethodCollection.random2(30) <= 2)
						{
							npcs[i].updateRequired = true;
							npcs[i].textUpdateRequired = true;
							npcs[i].textUpdate = "Chill Here!";
						}
					}
					if (npcs[i].id == 1759)
					{
						if (MethodCollection.random2(30) <= 2)
						{
							npcs[i].updateRequired = true;
							npcs[i].textUpdateRequired = true;
							npcs[i].textUpdate = "Shops Here!";
						}
					}
					if (npcs[i].id == 1699)
					{
						if (MethodCollection.random2(30) <= 2)
						{
							npcs[i].updateRequired = true;
							npcs[i].textUpdateRequired = true;
							npcs[i].textUpdate = "Pure Shop!";
						}
					}
					if (npcs[i].id == 2475)
					{
						if (MethodCollection.random2(30) <= 2)
						{
							npcs[i].updateRequired = true;
							npcs[i].textUpdateRequired = true;
							npcs[i].textUpdate = "Training Portal!";
						}
					}
					if (npcs[i].id == 28)
					{
						if (MethodCollection.random2(30) <= 2)
						{
							npcs[i].updateRequired = true;
							npcs[i].textUpdateRequired = true;
							npcs[i].textUpdate = "Talk to me for summoning, drop the nulls to summon.";
						}
					}
					if (npcs[i].id == 2262)
					{
						if (MethodCollection.random2(30) <= 2)
						{
							npcs[i].updateRequired = true;
							npcs[i].textUpdateRequired = true;
							npcs[i].textUpdate = "Magic Equipment!";
						}
					}
					if (npcs[i].id == 548)
					{
						if (MethodCollection.random2(30) <= 2)
						{
							npcs[i].updateRequired = true;
							npcs[i].textUpdateRequired = true;
							npcs[i].textUpdate = "Good armour and weapons!";
						}
					}
					if (npcs[i].id == 2169)
					{
						if (MethodCollection.random2(30) <= 2)
						{
							npcs[i].updateRequired = true;
							npcs[i].textUpdateRequired = true;
							npcs[i].textUpdate = "Gold and Trimmed Armour!";
						}
					}
					if (npcs[i].id == 530)
					{
						if (MethodCollection.random2(30) <= 2)
						{
							npcs[i].updateRequired = true;
							npcs[i].textUpdateRequired = true;
							npcs[i].textUpdate = "Skillers Shop!";
						}
					}
					if (npcs[i].id == 2244)
					{
						if (MethodCollection.random2(30) <= 2)
						{
							npcs[i].updateRequired = true;
							npcs[i].textUpdateRequired = true;
							npcs[i].textUpdate = "Members Zone! Apply On Forums For Member, Or Win A Contest To Enter!";
						}
					}
					if (npcs[i].id == 528)
					{
						if (MethodCollection.random2(30) <= 2)
						{
							npcs[i].updateRequired = true;
							npcs[i].textUpdateRequired = true;
							npcs[i].textUpdate = "Helmet Shop!";
						}
					}
					if (npcs[i].id == 2167)
					{
						if (MethodCollection.random2(30) <= 2)
						{
							npcs[i].updateRequired = true;
							npcs[i].textUpdateRequired = true;
							npcs[i].textUpdate = "PKing Shop!";
						}
					}
					if (npcs[i].id == 3117)
					{
						if (MethodCollection.random2(30) <= 2)
						{
							npcs[i].updateRequired = true;
							npcs[i].textUpdateRequired = true;
							npcs[i].textUpdate = "Beginner Armor Shop!";
						}
					}
					if (npcs[i].id == 555)
					{
						if (MethodCollection.random2(30) <= 2)
						{
							npcs[i].updateRequired = true;
							npcs[i].textUpdateRequired = true;
							npcs[i].textUpdate = "Skill Cape Shop!";
						}
					}
					if (npcs[i].id == 561)
					{
						if (MethodCollection.random2(30) <= 2)
						{
							npcs[i].updateRequired = true;
							npcs[i].textUpdateRequired = true;
							npcs[i].textUpdate = "Hood Shop!";
						}
					}
					if (npcs[i].id == 538)
					{
						if (MethodCollection.random2(30) <= 2)
						{
							npcs[i].updateRequired = true;
							npcs[i].textUpdateRequired = true;
							npcs[i].textUpdate = "Slayer Shop!";
						}
					}
					if (npcs[i].id == 596)
					{
						if (MethodCollection.random2(30) <= 2)
						{
							npcs[i].updateRequired = true;
							npcs[i].textUpdateRequired = true;
							npcs[i].textUpdate = "Hello There!";
						}
					}
					if (npcs[i].id == 1986)
					{
						if (MethodCollection.random2(30) <= 2)
						{
							npcs[i].updateRequired = true;
							npcs[i].textUpdateRequired = true;
							npcs[i].textUpdate = "DROP PARTY AREA!";
						}
					}
					if (npcs[i].id == 529)
					{
						if (MethodCollection.random2(30) <= 2)
						{
							npcs[i].updateRequired = true;
							npcs[i].textUpdateRequired = true;
							npcs[i].textUpdate = "Farming Shop!";
						}
					}
					if (npcs[i].id == 209)
					{
						if (MethodCollection.random2(30) <= 2)
						{
							npcs[i].updateRequired = true;
							npcs[i].textUpdateRequired = true;
							npcs[i].textUpdate = "Dragon Shop!";
						}
					}
					if (npcs[i].id == 23)
					{
						if (MethodCollection.random2(30) <= 2)
						{
							npcs[i].updateRequired = true;
							npcs[i].textUpdateRequired = true;
							npcs[i].textUpdate = "Beginners Armor!";
						}
					}
					if (npcs[i].id == 944)
					{
						if (MethodCollection.random2(30) <= 2)
						{
							npcs[i].updateRequired = true;
							npcs[i].textUpdateRequired = true;
							npcs[i].textUpdate = "Special Armour!";
						}
					}
					if (npcs[i].id == 1917)
					{
						if (MethodCollection.random2(30) <= 2)
						{
							npcs[i].updateRequired = true;
							npcs[i].textUpdateRequired = true;
							npcs[i].textUpdate = "Rune Armour Shop!";
						}
					}
					if (npcs[i].id == 558)
					{
						if (MethodCollection.random2(30) <= 2)
						{
							npcs[i].updateRequired = true;
							npcs[i].textUpdateRequired = true;
							npcs[i].textUpdate = "Food and Potions";
						}
					}
					for (Player p : PlayerHandler.players)
					{
						Client person = (Client) p;
						if (p != null && person != null)
						{
							if (p != null && person != null)
							{
								if (person.distanceToPoint(npcs[i].absX,
										npcs[i].absY) >= 5)
								{
									npcs[i].RandomWalk = true;
								}
							}
						}
					}
					for (Player p : PlayerHandler.players)
					{
						Client person = (Client) p;
						if (p != null && person != null)
						{
							if (p != null && person != null)
							{
								if (person.distanceToPoint(npcs[i].absX,
										npcs[i].absY) >= 5)
								{
									if (npcs[i].id != 1158
											|| npcs[i].id == 2745
											|| npcs[i].id == 360
											|| npcs[i].id == 19
											|| npcs[i].id == 999
											|| npcs[i].id == 81)
									{
										npcs[i].RandomWalk = true;
									}
								}
							}
						}
					}
					for (Player player : PlayerHandler.players)
					{
						Client client = (Client) player;
						if (player != null && client != null)
						{
							if (player != null && client != null)
							{
								if (client.distanceToPoint(npcs[i].absX,
										npcs[i].absY) <= 20
										&& player.heightLevel == npcs[i].heightLevel)
								{
									if (npcs[i].id == 1158
											|| npcs[i].id == 2745
											|| npcs[i].id == 360
											|| npcs[i].id == 1472
											|| npcs[i].id == 19
											|| npcs[i].id == 999
											|| npcs[i].id == 81)
									{
										if (client.playerRights < 3)
										{
											npcs[i].StartKilling = client.playerId;
											npcs[i].IsUnderAttack = true;
										}
										npcs[i].RandomWalk = false;
									}
									else if (client.distanceToPoint(
											npcs[i].absX, npcs[i].absY) >= 21
											|| client.heightLevel != npcs[i].heightLevel)
									{
										if (npcs[i].id == 1158
												|| npcs[i].id == 2745
												|| npcs[i].id == 360
												|| npcs[i].id == 1472
												|| npcs[i].id == 19
												|| npcs[i].id == 999
												|| npcs[i].id == 81)
										{
											npcs[i].RandomWalk = true;
										}
									}
								}
							}
						}
					}
					for (Player p : PlayerHandler.players)
					{
						Client client = (Client) p;
						if (p != null && client != null)
						{
							if (p != null && client != null)
							{
								if (client.distanceToPoint(npcs[i].absX,
										npcs[i].absY) <= 20
										&& p.heightLevel == npcs[i].heightLevel)
								{
									if (npcs[i].id == 1974
											|| npcs[i].id == 1974)
									{
										if (client.playerRights < 3)
										{
											npcs[i].StartKilling = client.playerId;
											npcs[i].IsUnderAttack = true;
										}
										npcs[i].IsUnderAttack = true;
									}
									else if (client.distanceToPoint(
											npcs[i].absX, npcs[i].absY) >= 21
											|| client.heightLevel != npcs[i].heightLevel)
									{
										if (npcs[i].id == 1974
												|| npcs[i].id == 1974)
										{
											npcs[i].RandomWalk = true;
										}
									}
								}
							}
						}
					}
					for (Player p : PlayerHandler.players)
					{
						Client client = (Client) p;
						if (p != null && client != null)
						{
							if (p != null && client != null)
							{
								if (client.distanceToPoint(npcs[i].absX,
										npcs[i].absY) <= 20
										&& p.heightLevel == npcs[i].heightLevel)
								{
									if (npcs[i].id == 2060/*
														 * || npcs[i].id == 912
														 * || npcs[i].id == 913
														 * || npcs[i].id == 914
														 */)
									{
										if (client.playerRights < 3)
										{
											npcs[i].StartKilling = client.playerId;
											npcs[i].IsUnderAttack = true;
										}
										npcs[i].IsUnderAttack = true;
									}
									else if (client.distanceToPoint(
											npcs[i].absX, npcs[i].absY) >= 21
											|| client.heightLevel != npcs[i].heightLevel)
									{
										if (npcs[i].id == 2060)
										{
											npcs[i].RandomWalk = true;
										}
									}
								}
							}
						}
					}
					if (npcs[i].id == 33)
					{
						if (MethodCollection.random2(30) == 1)
						{
							npcs[i].updateRequired = true;
							npcs[i].textUpdateRequired = true;
							npcs[i].textUpdate = "Tele to Varrock";
						}
					}
					if (npcs[i].id == 245)
					{
						if (MethodCollection.random2(30) == 1)
						{
							npcs[i].updateRequired = true;
							npcs[i].textUpdateRequired = true;
							npcs[i].textUpdate = "Use Our Official Client!";
						}
					}
					if (npcs[i].id == 1199)
					{
						if (MethodCollection.random2(30) == 1)
						{
							npcs[i].updateRequired = true;
							npcs[i].textUpdateRequired = true;
							npcs[i].textUpdate = "Have fun!";
						}
					}
					if (npcs[i].id == 2301)
					{
						if (MethodCollection.random2(30) == 1)
						{
							npcs[i].updateRequired = true;
							npcs[i].textUpdateRequired = true;
							npcs[i].textUpdate = "Tele to the monkey training area";
						}
					}
					else if (npcs[i].id == 659)
					{
						if (MethodCollection.random2(10) <= 3) // this is the
																// time delay
						{
							npcs[i].updateRequired = true;
							npcs[i].textUpdateRequired = true;
							if (PlayerHandler.isPlayerOn("Dokan"))
							{
								npcs[i].textUpdate = "Forums: paradise317.proboards.com";
							}
							else
							{
								npcs[i].textUpdate = "Forums: paradise317.proboards.com";
							}
						}
					}
				}
				else if (npcs[i].IsDead == true)
				{
					if (npcs[i].actionTimer == 0 && npcs[i].DeadApply == false
							&& npcs[i].NeedRespawn == false)
					{
						if (npcs[i].id == 81 || npcs[i].id == 397
								|| npcs[i].id == 1766 || npcs[i].id == 1767
								|| npcs[i].id == 1768)
						{
							npcs[i].animNumber = 0x03E; // cow dead
						}
						else if (npcs[i].id == 41)
						{
							npcs[i].animNumber = 0x039; // chicken dead
						}
						else if (npcs[i].id == 87)
						{
							npcs[i].animNumber = 0x08D; // rat dead
						}
						else if (npcs[i].id == 75)
						{
							npcs[i].animNumber = 466; // chicken dead
						}
						else if (npcs[i].id == 2026)
						{ // Barrows Dharok
							npcs[i].animNumber = 0x900;
						}
						else if (npcs[i].id == 1158)
						{
							npcs[i].animNumber = 1187; // chicken dead
						}
						else if (npcs[i].id == 2025)
						{ // Barrows Ahrim
							npcs[i].animNumber = 0x900;
						}
						else if (npcs[i].id == 2027)
						{ // Barrows Guthan
							npcs[i].animNumber = 0x900;
						}
						else if (npcs[i].id == 2028)
						{ // Barrows Karil
							npcs[i].animNumber = 0x900;
						}
						else if (npcs[i].id == 2029)
						{ // Barrows Torag
							npcs[i].animNumber = 0x900;
						}
						else if (npcs[i].id == 2030)
						{ // Barrows Verac
							npcs[i].animNumber = 0x900;
						}
						else if (npcs[i].id == 50)
						{ // KBD
							npcs[i].animNumber = 0x900;
						}
						else if (npcs[i].id == 2036)
						{ // Barrows
							// Skeletons
							npcs[i].animNumber = 0x900;
						}
						else if (npcs[i].id == 83)
						{ // Greater Demon
							npcs[i].animNumber = 804;
						}
						else if (npcs[i].id == 1472)
						{ // Jungle Demon
							npcs[i].animNumber = 804;
						}
						else if (npcs[i].id == 999)
						{ // Dragon Claw Boss
							npcs[i].animNumber = 804;
						}
						else if (npcs[i].id == 3200)
						{
							npcs[i].animNumber = 777; // drags: chaos ele emote
														// ( YESSS )
						}
						else if (npcs[i].id == 1605)
						{
							npcs[i].animNumber = 1508; // drags: abberant
														// spector ( YAY )
						}
						else if (npcs[i].id == 55)
						{
							npcs[i].animNumber = 92; // KBD dead
						}
						else if (npcs[i].id == 55)
						{
							npcs[i].animNumber = 92; // blue dragon
						}
						else if (npcs[i].id == 941)
						{
							npcs[i].animNumber = 92; // green dragon
						}
						else if (npcs[i].id == 1590)
						{
							npcs[i].animNumber = 92; // bronze dragon
						}
						else if (npcs[i].id == 1591)
						{
							npcs[i].animNumber = 92; // iron dragon
						}
						else if (npcs[i].id == 1592)
						{
							npcs[i].animNumber = 92; // steel dragon
						}
						else if (npcs[i].id == 53)
						{
							npcs[i].animNumber = 92; // red dragon
						}
						else if (npcs[i].id == 54)
						{
							npcs[i].animNumber = 92; // black dragon
						}
						else if (npcs[i].id == 2745)
						{
							npcs[i].animNumber = 2654; // jad dead
						}
						else
						{
							npcs[i].animNumber = 0x900; // human dead
						}
						npcs[i].updateRequired = true;
						npcs[i].animUpdateRequired = true;
						npcs[i].DeadApply = true;
						npcs[i].actionTimer = 10;
						if (npcs[i].followingPlayer
								&& PlayerHandler.players[npcs[i].followPlayer] != null)
						{
							PlayerHandler.players[npcs[i].followPlayer].summonedNPCS--;
						}
					}
					else if (npcs[i].actionTimer == 0
							&& npcs[i].DeadApply == true
							&& npcs[i].NeedRespawn == false && npcs[i] != null)
					{
						MonsterDropItem(i);
						npcs[i].NeedRespawn = true;
						npcs[i].actionTimer = 60;
						npcs[i].absX = npcs[i].makeX;
						npcs[i].absY = npcs[i].makeY;
						npcs[i].animNumber = 0x328;
						npcs[i].HP = npcs[i].MaxHP;
						npcs[i].updateRequired = true;
						npcs[i].animUpdateRequired = true;
					}
					else if (npcs[i].actionTimer == 0
							&& npcs[i].NeedRespawn == true)
					{
						for (int j = 1; j < PlayerHandler.maxPlayers; j++)
						{
							if (PlayerHandler.players[j] != null)
							{
								PlayerHandler.players[j].RebuildNPCList = true;
							}
						}
						if (npcs[i].Respawns)
						{
							int old1 = npcs[i].id;
							if (old1 == 1267 || old1 == 1265)
							{
								old1 += 1;
							}
							int old2 = npcs[i].makeX;
							int old3 = npcs[i].makeY;
							int old4 = npcs[i].heightLevel;
							int old5 = npcs[i].moverangeX1;
							int old6 = npcs[i].moverangeY1;
							int old7 = npcs[i].moverangeX2;
							int old8 = npcs[i].moverangeY2;
							int old9 = npcs[i].walkingType;
							int old10 = npcs[i].MaxHP;
							npcs[i] = null;
							newNPC(old1, old2, old3, old4, old5, old6, old7,
									old8, old9, old10, true);
						}
					}
				}
			}
		}
	}

	public static boolean IsDropping = false;

	public void MonsterDropItem(int id)
	{
		{
			if (IsDropping == false)
			{
				IsDropping = true;
				int pid = getNpcKiller(id);
				int Maxi = ItemHandler.DropItemCount;
				Client client = (Client) PlayerHandler.players[pid];

				for (int i = 0; i <= Maxi; i++)
				{
					if (ItemHandler.DroppedItemsID[i] > 0)
					{
					}
					else
					{
						if (npcs[id] != null
								&& PlayerHandler.players[pid] != null
								&& PlayerHandler.players[getNpcKiller(id)] != null)
						{
							if (npcs[id].id == 275)
							{
								ItemHandler.addItem(4273, npcs[id].absX,
										npcs[id].absY, 1, getNpcKiller(id),
										false);
							}
							if (npcs[id].id == 18)
							{
								ItemHandler.addItem(Item3.randomguard(),
										npcs[id].absX, npcs[id].absY, 1,
										getNpcKiller(id), false);
							}
							if (npcs[id].id == 21)
							{
								ItemHandler.addItem(Item3.randomhero(),
										npcs[id].absX, npcs[id].absY, 1,
										getNpcKiller(id), false);
							}
							if (npcs[id].id == 2256)
							{
								ItemHandler.addItem(Item3.randomguardz(),
										npcs[id].absX, npcs[id].absY, 1,
										getNpcKiller(id), false);
							}
							if (npcs[id].id == 1021)
							{
								client.teleportToX = 2660;
								client.teleportToY = 4839;
							}
							if (npcs[id].id == 110)
							{
								client.teleportToX = 2679;
								client.teleportToY = 9590;
							}
							if (npcs[id].id == 2274)
							{
								client.teleportToX = 2658;
								client.teleportToY = 9545;
							}
							if (npcs[id].id == 82)
							{
								client.teleportToX = 2670;
								client.teleportToY = 9545;
							}
							if (npcs[id].id == 83)
							{
								client.teleportToX = 2269;
								client.teleportToY = 4693;
								client.sendMessage("Woah! you made it to the last level! Slay the monster and get a Dark bow!");
							}
							if (npcs[id].id == 84)
							{
								client.teleportToX = 2907;
								client.teleportToY = 9709;
								client.addItem(15156, 1);
								client.addItem(561, 1);
								client.addItem(554, 5);
								client.sendMessage("Congratulations., You have slain all monsters and got the Darkbow");
								client.sendMessage("If you have 155 Mage, alch the dark bow for 175m!");
							}
							if (npcs[id].id == 1020)
							{
								client.teleportToX = 2713;
								client.teleportToY = 4836;
							}
							if (npcs[id].id == 752)
							{
								client.teleportToX = 2542;
								client.teleportToY = 3029;
							}
							if (npcs[id].id == 275)
							{
								client.teleportToX = 2608;
								client.teleportToY = 3163;
							}
							if (npcs[id].id == 477)
							{
								client.teleportToX = 2608;
								client.teleportToY = 3159;
							}
							if (npcs[id].id == 1919)
							{
								client.teleportToX = 2866;
								client.teleportToY = 9952;
							}
							if (npcs[id].id == 509)
							{
								client.teleportToX = 2792;
								client.teleportToY = 9325;
							}
							if (npcs[id].id == 274)
							{
								client.teleportToX = 2540;
								client.teleportToY = 3019;
							}
							if (npcs[id].id == 795)
							{
								client.teleportToX = 2551;
								client.teleportToY = 3043;
							}
							if (npcs[id].id == 1022)
							{
								client.teleportToX = 2584;
								client.teleportToY = 4836;
							}
							if (npcs[id].id == 1019)
							{
								client.teleportToX = 2464;
								client.teleportToY = 4834;
							}
							if (npcs[id].id == 1600)
							{
								client.teleportToX = 2479;
								client.teleportToY = 5167;
							}
							if (npcs[id].id == 2591)
							{
								client.teleportToX = 3309;
								client.teleportToY = 2800;
							}
							if (npcs[id].id == 3494)
							{
								client.teleportToX = 3179;
								client.teleportToY = 9631;
							}
							if (npcs[id].id == 3200)
							{
								ItemHandler.addItem(Item2.randomSilvchest(),
										npcs[id].absX, npcs[id].absY, 1,
										getNpcKiller(id), false);
							}
							if (npcs[id].id == 1007)
							{
								ItemHandler.addItem(6754, npcs[id].absX,
										npcs[id].absY, 1, getNpcKiller(id),
										false);
							}
							if (npcs[id].id == 795)
							{
								ItemHandler.addItem(4078, npcs[id].absX,
										npcs[id].absY, 1, getNpcKiller(id),
										false);
							}
							if (npcs[id].id == 509)
							{
								ItemHandler.addItem(6104, npcs[id].absX,
										npcs[id].absY, 1, getNpcKiller(id),
										false);
							}
							if (npcs[id].id == 2880)
							{
								ItemHandler.addItem(5585, npcs[id].absX,
										npcs[id].absY, 1, getNpcKiller(id),
										false);
							}
							if (npcs[id].id == 1859)
							{
								ItemHandler.addItem(6529, npcs[id].absX,
										npcs[id].absY, 10000000,
										getNpcKiller(id), false);
							}
							if (npcs[id].id == 1158)
							{
								ItemHandler.addItem(Item2.randomKQ(),
										npcs[id].absX, npcs[id].absY, 1,
										getNpcKiller(id), false);
							}
							if (npcs[id].id == 41)
							{
								ItemHandler.addItem(Item2.randomchicken(),
										npcs[id].absX, npcs[id].absY, 1,
										getNpcKiller(id), false);
							}
							if (npcs[id].id == 90)
							{
								ItemHandler.addItem(Item2.randomskeleton(),
										npcs[id].absX, npcs[id].absY, 1,
										getNpcKiller(id), false);
							}
							if (npcs[id].id == 1648)
							{
								ItemHandler.addItem(Item2.randomcrawlinghand(),
										npcs[id].absX, npcs[id].absY, 1,
										getNpcKiller(id), false);
							}
							if (npcs[id].id == 125)
							{
								ItemHandler.addItem(Item2.randomdarkbeast(),
										npcs[id].absX, npcs[id].absY, 1,
										getNpcKiller(id), false);
							}
							if (npcs[id].id == 1637)
							{
								ItemHandler.addItem(Item2.randomjelly(),
										npcs[id].absX, npcs[id].absY, 1,
										getNpcKiller(id), false);
							}
							if (npcs[id].id == 1604)
							{
								ItemHandler.addItem(
										Item2.randomaberrantspecter(),
										npcs[id].absX, npcs[id].absY, 1,
										getNpcKiller(id), false);
							}
							if (npcs[id].id == 1615)
							{
								ItemHandler.addItem(Item2.randomabyssaldemon(),
										npcs[id].absX, npcs[id].absY, 1,
										getNpcKiller(id), false);
							}
							if (npcs[id].id == 2783)
							{
								ItemHandler.addItem(Item2.randomdarkbeast(),
										npcs[id].absX, npcs[id].absY, 1,
										getNpcKiller(id), false);
							}
							if (npcs[id].id == 89)
							{
								ItemHandler.addItem(Item2.randomunicorn(),
										npcs[id].absX, npcs[id].absY, 1,
										getNpcKiller(id), false);
							}
							if (npcs[id].id == 912)
							{
								ItemHandler.addItem(
										Item2.randombattlemagesara(),
										npcs[id].absX, npcs[id].absY, 1,
										getNpcKiller(id), false);
							}
							if (npcs[id].id == 913)
							{
								ItemHandler.addItem(
										Item2.randombattlemagezammy(),
										npcs[id].absX, npcs[id].absY, 1,
										getNpcKiller(id), false);
							}
							if (npcs[id].id == 914)
							{
								ItemHandler.addItem(
										Item2.randombattlemageguthix(),
										npcs[id].absX, npcs[id].absY, 1,
										getNpcKiller(id), false);
							}
							if (npcs[id].id == 86)
							{
								ItemHandler.addItem(Item2.randomrat(),
										npcs[id].absX, npcs[id].absY, 1,
										getNpcKiller(id), false);
							}
							if (npcs[id].id == 35)
							{
								ItemHandler.addItem(Item2.randomsoldier(),
										npcs[id].absX, npcs[id].absY, 1,
										getNpcKiller(id), false);
							}
							if (npcs[id].id == 114)
							{
								ItemHandler.addItem(Item2.randomogre(),
										npcs[id].absX, npcs[id].absY, 1,
										getNpcKiller(id), false);
							}
							if (npcs[id].id == 17)
							{
								ItemHandler.addItem(Item2.randombarbarian(),
										npcs[id].absX, npcs[id].absY, 1,
										getNpcKiller(id), false);
							}
							if (npcs[id].id == 1614)
							{
								ItemHandler.addItem(Item2.randomJogre(),
										npcs[id].absX, npcs[id].absY, 1,
										getNpcKiller(id), false);
							}
							if (npcs[id].id == 1859)
							{
								ItemHandler.addItem(Item2
										.randomArzinian_Being_of_Bordanzan(),
										npcs[id].absX, npcs[id].absY, 1,
										getNpcKiller(id), false);
							}
							if (npcs[id].id == 1575)
							{
								ItemHandler.addItem(
										Item2.randomSkeleton_Hellhound(),
										npcs[id].absX, npcs[id].absY, 1,
										getNpcKiller(id), false);
							}
							if (npcs[id].id == 111)
							{
								ItemHandler.addItem(Item2.randomIce_giant(),
										npcs[id].absX, npcs[id].absY, 1,
										getNpcKiller(id), false);
							}
							if (npcs[id].id == 2919)
							{
								ItemHandler.addItem(Item2.randomAgrith_Naar(),
										npcs[id].absX, npcs[id].absY, 1,
										getNpcKiller(id), false);
							}
							if (npcs[id].id == 113)
							{
								ItemHandler.addItem(Item2.randomJogre(),
										npcs[id].absX, npcs[id].absY, 1,
										getNpcKiller(id), false);
							}
							if (npcs[id].id == 113)
							{
								ItemHandler.addItem(Item2.randomJogre(),
										npcs[id].absX, npcs[id].absY, 1,
										getNpcKiller(id), false);
							}
							if (npcs[id].id == 2881)
							{
								ItemHandler.addItem(
										Item2.randomDagannoth_Supreme(),
										npcs[id].absX, npcs[id].absY, 1,
										getNpcKiller(id), false);
							}
							if (npcs[id].id == 2882)
							{
								ItemHandler.addItem(
										Item2.randomDagannoth_Prime(),
										npcs[id].absX, npcs[id].absY, 1,
										getNpcKiller(id), false);
							}
							if (npcs[id].id == 2883)
							{
								ItemHandler.addItem(
										Item2.randomDagannoth_Rex(),
										npcs[id].absX, npcs[id].absY, 1,
										getNpcKiller(id), false);
							}
							if (npcs[id].id == 50)
							{
								ItemHandler.addItem(Item2.randomKBD(),
										npcs[id].absX, npcs[id].absY, 1,
										getNpcKiller(id), false);
							}
							if (npcs[id].id == 1974)
							{
								ItemHandler.addItem(Item2.randombandos(),
										npcs[id].absX, npcs[id].absY, 1,
										getNpcKiller(id), false);
							}
							if (npcs[id].id == 81)
							{
								ItemHandler.addItem(Item2.randomcow(),
										npcs[id].absX, npcs[id].absY, 1,
										getNpcKiller(id), false);										
							}
							if (npcs[id].id == 2060)
							{
								ItemHandler.addItem(Item2.randomphat(),
										npcs[id].absX, npcs[id].absY, 1,
										getNpcKiller(id), false);
							}
							if (npcs[id].id == 182)
							{
								ItemHandler.addItem(Item2.randomattackguild(),
										npcs[id].absX, npcs[id].absY, 1,
										getNpcKiller(id), false);										
							}
							if (npcs[id].id == 2745)
							{
								if (random.nextInt(16) == 0)
								{
									int lootId = Item2.getRandomJadLoot();

									while ((lootId == 526)
											&& (client.playerEquipment[Player.playerRing] == 2572))
									{
										lootId = Item2.getRandomJadLoot();
									}

									ItemHandler.addItem(lootId, npcs[id].absX,
											npcs[id].absY, 1, getNpcKiller(id),
											false);

									if (lootId != 526)
									{

										PlayerHandler.messageToAll = "@red@News:@bla@ "
												+ client.username
												+ " has received a "
												+ client.getItemName(lootId)
												+ " drop!";
									}
								}

								ItemHandler.addItem(526, npcs[id].absX,
										npcs[id].absY, 1, getNpcKiller(id),
										false);
							}
							if (npcs[id].id == 360)
							{
								ItemHandler.addItem(526, npcs[id].absX,
										npcs[id].absY, 1, getNpcKiller(id),
										false);

								if (random.nextInt(22) == 0)
								{
									ItemHandler.addItem(1283, npcs[id].absX,
											npcs[id].absY, 1, getNpcKiller(id),
											false);

									PlayerHandler.messageToAll = "@red@News:@bla@ "
											+ client.username
											+ " has received a Double dildo drop!";
								}
							}
							if (npcs[id].id == 19)
							{
								ItemHandler.addItem(Item2.randomdusty(),
										npcs[id].absX, npcs[id].absY, 1,
										getNpcKiller(id), false);
							}
							if (npcs[id].id == 1472)
							{
								ItemHandler.addItem(Item2.randomjungle(),
										npcs[id].absX, npcs[id].absY, 1,
										getNpcKiller(id), false);
							}
							if (npcs[id].id == 85)
							{
								ItemHandler.addItem(Item2.randomstrguild(),
										npcs[id].absX, npcs[id].absY, 1,
										getNpcKiller(id), false);
							}
							if (npcs[id].id == 20)
							{
								ItemHandler.addItem(Item2.randomdefguild(),
										npcs[id].absX, npcs[id].absY, 1,
										getNpcKiller(id), false);										
							}
							if (npcs[id].id == 999)
							{
								ItemHandler.addItem(Item2.randomdclaw(),
										npcs[id].absX, npcs[id].absY, 1,
										getNpcKiller(id), false);
							}
							if (npcs[id].id == 54)
							{
								ItemHandler.addItem(Item2.randomblackdragon(),
										npcs[id].absX, npcs[id].absY, 1,
										getNpcKiller(id), false);
							}
							if (npcs[id].id == 53)
							{
								ItemHandler.addItem(Item2.randomreddragon(),
										npcs[id].absX, npcs[id].absY, 1,
										getNpcKiller(id), false);
							}
							if (npcs[id].id == 55)
							{
								ItemHandler.addItem(Item2.randombluedragon(),
										npcs[id].absX, npcs[id].absY, 1,
										getNpcKiller(id), false);
							}
							if (npcs[id].id == 1590)
							{
								ItemHandler.addItem(Item2.randombronzedragon(),
										npcs[id].absX, npcs[id].absY, 1,
										getNpcKiller(id), false);
							}
							if (npcs[id].id == 1591)
							{
								ItemHandler.addItem(Item2.randomirondragon(),
										npcs[id].absX, npcs[id].absY, 1,
										getNpcKiller(id), false);
							}
							if (npcs[id].id == 1592)
							{
								ItemHandler.addItem(Item2.randomsteeldragon(),
										npcs[id].absX, npcs[id].absY, 1,
										getNpcKiller(id), false);
							}
							if (npcs[id].id == 941)
							{
								ItemHandler.addItem(Item2.randomgreendragon(),
										npcs[id].absX, npcs[id].absY, 1,
										getNpcKiller(id), false);
							}
							if (npcs[id].id == 188)
							{
								ItemHandler.addItem(16, npcs[id].absX,
										npcs[id].absY, 1, getNpcKiller(id),
										false);
							}
							if (npcs[id].id == 1625 || npcs[id].id == 1604
									|| npcs[id].id == 2035)
							{
								ItemHandler.addItem(Item.randomSlayeritem65(),
										npcs[id].absX, npcs[id].absY, 1,
										getNpcKiller(id), false);
							}
							if (npcs[id].id == 1625 || npcs[id].id == 1604)
							{
								ItemHandler.addItem(Item.randomSlayeritem75(),
										npcs[id].absX, npcs[id].absY, 1,
										getNpcKiller(id), false);
							}
							if (npcs[id].id == 1605)
							{
								ItemHandler.addItem(Item.randomSlayer99item(),
										npcs[id].absX, npcs[id].absY, 1,
										getNpcKiller(id), false);
							}
							if (npcs[id].id == 2026)
							{
								ItemHandler.addItem(Item2.randomDharok(),
										npcs[id].absX, npcs[id].absY, 1,
										getNpcKiller(id), false);
							}
							if (npcs[id].id == 2030)
							{
								ItemHandler.addItem(Item2.randomVerac(),
										npcs[id].absX, npcs[id].absY, 1,
										getNpcKiller(id), false);
							}
							if (npcs[id].id == 2029)
							{
								ItemHandler.addItem(Item2.randomTorag(),
										npcs[id].absX, npcs[id].absY, 1,
										getNpcKiller(id), false);
							}
							if (npcs[id].id == 2028)
							{
								ItemHandler.addItem(Item2.randomKaril(),
										npcs[id].absX, npcs[id].absY, 1,
										getNpcKiller(id), false);
							}
							if (npcs[id].id == 2027)
							{
								ItemHandler.addItem(Item2.randomGuthan(),
										npcs[id].absX, npcs[id].absY, 1,
										getNpcKiller(id), false);
							}
							if (npcs[id].id == 2025)
							{
								ItemHandler.addItem(Item2.randomAhrim(),
										npcs[id].absX, npcs[id].absY, 1,
										getNpcKiller(id), false);
							}
							if (npcs[id].id >= 0)
							{
								if (MethodCollection.random(400) == 0)
								{
									ItemHandler.addItem(6199, npcs[id].absX,
											npcs[id].absY, 1, getNpcKiller(id),
											false);
									PlayerHandler.messageToAll = "@red@News:@bla@ "
											+ client.username
											+ " has received a Mystery box from "
											+ client.getNpcName(npcs[id].id)
											+ "!";
								}
							}
						}
						if (i == Maxi)
						{
							if (ItemHandler.DropItemCount >= (ItemHandler.MaxDropItems + 1))
							{
								ItemHandler.DropItemCount = 0;
							}
						}
						break;
					}
				}
				IsDropping = false;
			}
		}
	}

	public int getNpcKiller(int id)
	{
		int killer = 0;
		int count = 0;
		for (int i = 1; i < PlayerHandler.maxPlayers; i++)
		{
			if (killer == 0)
			{
				killer = i;
				count = 1;
			}
			else
			{
				if (npcs[id].Killing[i] > npcs[id].Killing[killer])
				{
					killer = i;
					count = 1;
				}
				else if (npcs[id].Killing[i] == npcs[id].Killing[killer])
				{
					count++;
				}
			}
		}
		if (count > 1
				&& npcs[id].Killing[npcs[id].StartKilling] == npcs[id].Killing[killer])
		{
			killer = npcs[id].StartKilling;
		}
		return killer;
	}

	private static int worldmap2[][] = {
			{
			/* 01 */3257, 3258, 3260, 3261, 3261, 3262, 3261, 3262, 3257, 3258,
					3257, 3258, 3254, 3255, 3254, 3255, 3252, 3252, 3250, 3251,
					3250, 3251, 3249, 3250, 3249, 3250, 3242, 3242, 3243, 3243,
					3257, 3244, 3245, 3244, 3245, 3247, 3248, 3250, 3251, 3255,
					3256, 3255, 3256, 3259, 3260, },
			{
			/* 01 */3256, 3256, 3256, 3256, 3266, 3266, 3267, 3267, 3270, 3270,
					3271, 3271, 3272, 3272, 3273, 3273, 3275, 3276, 3277, 3277,
					3278, 3278, 3279, 3279, 3280, 3280, 3285, 3286, 3289, 3290,
					3289, 3297, 3297, 3298, 3298, 3298, 3298, 3297, 3297, 3297,
					3297, 3298, 3298, 3297, 3297, }, };
	private int remove = 2;
	private static int removeschaos[] = { 1, 2, 2, 2 };

	private static int randomremoveschaos()
	{
		return removeschaos[(int) (Math.random() * removeschaos.length)];
	}

	private void gfxAll(int id, int Y, int X)
	{
		for (Player p : PlayerHandler.players)
		{
			if (p != null)
			{
				Client person = (Client) p;
				if ((person.username != null || person.username != "null"))
				{
					if (person.distanceToPoint(X, Y) <= 60)
					{
						person.stillgfx2(id, Y, X);
					}
				}
			}
		}
	}

	public static int getNPCBlockAnim(int id)
	{
		switch (id)
		{
		case 50: // dragons
		case 53:
		case 1158:
			return 1186;
		case 54:
		case 2256:
			return 403;
		case 21:
			return 403;
		case 2745:
			return 2653;
		case 18:
			return 403;
		case 92:
			return 0;
		case 55:
			return 89;
		default:
			return 1834;
		}
	}

	public boolean AttackPlayerRanged(int NPCID)
	{
		int Player = npcs[NPCID].StartKilling;
		if (PlayerHandler.players[Player] == null)
		{
			ResetAttackPlayer(NPCID);
			return false;
		}
		else if (PlayerHandler.players[Player].DirectionCount < 2)
		{
			return false;
		}
		Client plr = (Client) PlayerHandler.players[Player];
		int EnemyX = PlayerHandler.players[Player].absX;
		int EnemyY = PlayerHandler.players[Player].absY;
		npcs[NPCID].enemyX = EnemyX;
		npcs[NPCID].enemyY = EnemyY;
		int EnemyHP = PlayerHandler.players[Player].playerLevel[net.paradise.game.player.Player.HITPOINTS];
		int EnemyMaxHP = getLevelForXP(PlayerHandler.players[Player].playerXP[net.paradise.game.player.Player.HITPOINTS]);
		boolean RingOfLife = false;
		if (PlayerHandler.players[Player].playerEquipment[net.paradise.game.player.Player.playerRing] == 2570)
		{
			RingOfLife = true;
		}
		if (PlayerHandler.players[Player].attacknpc == NPCID)
		{
			PlayerHandler.players[Player].faceNPC = NPCID;
			PlayerHandler.players[Player].faceNPCupdate = true;
			PlayerHandler.players[Player].attacknpc = NPCID;
			PlayerHandler.players[Player].IsAttackingNPC = true;
		}
		int hitDiff = 0;
		hitDiff = MethodCollection.random(npcs[NPCID].MaxHit);
		if (npcs[NPCID].id != 3200 && npcs[NPCID].id != 1645)
		{
			FollowPlayerCB(NPCID, Player);
		}
		if (GoodDistance(npcs[NPCID].absX, npcs[NPCID].absY, EnemyX, EnemyY, 1) == true
				|| npcs[NPCID].id == 3200)
		{
			if (npcs[NPCID].actionTimer == 0)
			{
				if (RingOfLife == true
						&& EnemyHP <= (int) ((double) ((double) EnemyMaxHP / 10.0) + 0.5))
				{
					PlayerHandler.players[Player].SafeMyLife = true;
				}
				else
				{
					if (PlayerHandler.players[Player].IsDead == true)
					{
						ResetAttackPlayer(NPCID);
					}
					else
					{
						if (npcs[NPCID].id == 3200)
						{
							npcs[NPCID].animNumber = 0x326;
							remove = randomremoveschaos();
							if (remove == 1)
							{
								PlayerHandler.players[Player].removeequipped();
							}
						}
						else if (npcs[NPCID].id == 752)
						{
							npcs[NPCID].animNumber = 0x326;
						}
						else if (npcs[NPCID].id == 50)
						{
							npcs[NPCID].animNumber = 91; // KBD attack
						}
						else if (npcs[NPCID].id == 35)
						{
							npcs[NPCID].animNumber = 1080; // Guard Attack
						}
						else if (npcs[NPCID].id == 53)
						{
							npcs[NPCID].animNumber = 80; // Dragon
						}
						else if (npcs[NPCID].id == 54)
						{
							npcs[NPCID].animNumber = 80; // Dragon
						}
						else if (npcs[NPCID].id == 55)
						{
							npcs[NPCID].animNumber = 80; // Dragon
						}
						else if (npcs[NPCID].id == 941)
						{
							npcs[NPCID].animNumber = 80; // Dragon
						}
						else if (npcs[NPCID].id == 1590)
						{
							npcs[NPCID].animNumber = 80; // Dragon
						}
						else if (npcs[NPCID].id == 1591)
						{
							npcs[NPCID].animNumber = 80; // Dragon
						}
						else if (npcs[NPCID].id == 1592)
						{
							npcs[NPCID].animNumber = 80; // Dragon
						}
						else if (npcs[NPCID].id == 3647)
						{
							npcs[NPCID].animNumber = 0x03B; // cow attack
						}
						else if (npcs[NPCID].id == 451)
						{
							npcs[NPCID].animNumber = 2363; // paladin attack
						}
						else if (npcs[NPCID].id == 41)
						{
							npcs[NPCID].animNumber = 0x037; // chicken attack
						}
						else if (npcs[NPCID].id == 87)
						{
							npcs[NPCID].animNumber = 0x08A; // rat attack
						}
						else if (npcs[NPCID].id == 3200)
						{
							npcs[NPCID].animNumber = 0x326;
							remove = randomremoveschaos();
							if (remove == 1)
							{
								PlayerHandler.players[Player].removeequipped();
							}
						}
						else if (npcs[NPCID].id == 752)
						{
							npcs[NPCID].animNumber = 0x326;
						}
						else
						{
							npcs[NPCID].animNumber = 0x326; // human attack
						}
						plr.startAnimation(plr
								.GetBlockAnim(plr.playerEquipment[net.paradise.game.player.Player.playerWeapon]));
						npcs[NPCID].animUpdateRequired = true;
						npcs[NPCID].updateRequired = true;
						if ((EnemyHP - hitDiff) < 0)
						{
							hitDiff = EnemyHP;
						}
						PlayerHandler.players[Player].hitDiff = hitDiff;
						PlayerHandler.players[Player].updateRequired = true;
						PlayerHandler.players[Player].hitUpdateRequired = true;
						PlayerHandler.players[Player].appearanceUpdateRequired = true;
						npcs[NPCID].actionTimer = 7;
					}
				}
				return true;
			}
		}
		return false;
	}

	public boolean AttackPlayer(int id)
	{
		int pid = npcs[id].StartKilling;
		Client client = (Client) PlayerHandler.players[npcs[id].StartKilling];
		if (PlayerHandler.players[pid] == null)
		{
			ResetAttackPlayer(id);
			return false;
		}
		else if (PlayerHandler.players[pid].DirectionCount < 2)
		{
			return false;
		}
		int EnemyX = client.absX;
		int EnemyY = client.absY;
		npcs[id].enemyX = EnemyX;
		npcs[id].enemyY = EnemyY;
		int EnemyHP = PlayerHandler.players[pid].playerLevel[Player.HITPOINTS];
		int EnemyMaxHP = getLevelForXP(PlayerHandler.players[pid].playerXP[Player.HITPOINTS]);
		boolean RingOfLife = false;
		if (PlayerHandler.players[pid].playerEquipment[Player.playerRing] == 2570)
		{
			RingOfLife = true;
		}
		if (PlayerHandler.players[pid].attacknpc == id)
		{
			PlayerHandler.players[pid].faceNPC = id;
			PlayerHandler.players[pid].faceNPCupdate = true;
			PlayerHandler.players[pid].attacknpc = id;
			PlayerHandler.players[pid].IsAttackingNPC = true;
		}
		int hitDiff = 0;
		hitDiff = MethodCollection.random(npcs[id].MaxHit);
		if (npcs[id].id != 3200 && npcs[id].id != 1645)
		{
			FollowPlayerCB(id, pid);
		}
		if (GoodDistance(npcs[id].absX, npcs[id].absY, EnemyX, EnemyY, 1) == true
				|| npcs[id].id == 3200)
		{
			if (npcs[id].actionTimer == 0)
			{
				if (RingOfLife == true
						&& EnemyHP <= (int) ((double) ((double) EnemyMaxHP / 10.0) + 0.5))
				{
					PlayerHandler.players[pid].SafeMyLife = true;
				}
				else
				{
					if (PlayerHandler.players[pid].IsDead == true)
					{
						ResetAttackPlayer(id);
					}
					else
					{
						if (npcs[id].id == 81 || npcs[id].id == 397
								|| npcs[id].id == 1766 || npcs[id].id == 1767
								|| npcs[id].id == 1768)
						{
							npcs[id].animNumber = 0x03B; // cow attack
						}
						else if (npcs[id].id == 41)
						{
							npcs[id].animNumber = 0x037; // chicken attack
						}
						else if (npcs[id].id == 87)
						{
							npcs[id].animNumber = 0x08A; // rat attack
						}
						else if (npcs[id].id == 21)
						{
							npcs[id].animNumber = 451; // Hero attack
							hitDiff = 4 + MethodCollection.random(9);
						}
						else if (npcs[id].id == 1958)
						{
							npcs[id].animNumber = 422; // Hero attack
							hitDiff = 4 + MethodCollection.random(18);
						}
						else if (npcs[id].id == 360)
						{
							npcs[id].animNumber = 422; // Dildo attack
							hitDiff = 10 + MethodCollection.random(35);
						}
						else if (npcs[id].id == 2256)
						{
							npcs[id].animNumber = 451; // Paladin attack
							hitDiff = 4 + MethodCollection.random(8);
						}
						else if (npcs[id].id == 50)
						{
							npcs[id].animNumber = 91; // KBD attack
						}
						else if (npcs[id].id == 18)
						{
							npcs[id].animNumber = 451; // Al-K'harid Warrior
														// Attack
							hitDiff = 4 + MethodCollection.random(2);
						}
						else if (npcs[id].id == 53)
						{
							npcs[id].animNumber = 80; // Dragon
						}
						else if (npcs[id].id == 54)
						{
							npcs[id].animNumber = 80; // Dragon
						}
						else if (npcs[id].id == 1158)
						{
							npcs[id].animNumber = 1184; // KQ attack
							hitDiff = 4 + MethodCollection.random(45);
						}
						else if (npcs[id].id == 1974)
						{
							npcs[id].animNumber = 1184; // Bandos attack
							hitDiff = 14 + MethodCollection.random(14);
						}
						else if (npcs[id].id == 2060)
						{
							npcs[id].animNumber = 1184; // KQ attack
							hitDiff = 4 + MethodCollection.random(20);
						}
						// else if (npcs[id].id == 912 || npcs[id].id == 913
						// || npcs[id].id == 914)
						// {
						// if (MethodCollection.random(4) == 0)
						// {
						// hitDiff = MethodCollection.random(4);
						// hitDiff += MethodCollection
						// .random(50 - (client.playerLevel[1] / 4));
						// }
						// else
						// {
						// if (hitDiff > 0)
						// {
						// hitDiff = MethodCollection.random(10);
						// }
						// }
						// if (hitDiff < 0)
						// {
						// hitDiff = 0;
						// }
						// }
						else if (npcs[id].id == 55)
						{
							npcs[id].animNumber = 80; // Dragon
						}
						else if (npcs[id].id == 941)
						{
							npcs[id].animNumber = 80; // Dragon
						}
						else if (npcs[id].id == 1590)
						{
							npcs[id].animNumber = 80; // Dragon
						}
						else if (npcs[id].id == 1591)
						{
							npcs[id].animNumber = 80; // Dragon
						}
						else if (npcs[id].id == 1158)
						{
							npcs[id].animNumber = 1184; // KQ attack
							hitDiff = 4 + MethodCollection.random(35);
						}
						else if (npcs[id].id == 2026)
						{
							npcs[id].animNumber = 2067; // Dharok
							hitDiff = 4 + MethodCollection.random(15);
						}
						else if (npcs[id].id == 2027)
						{
							npcs[id].animNumber = 2080; // Guthan
							hitDiff = 4 + MethodCollection.random(15);
						}
						else if (npcs[id].id == 2029)
						{
							npcs[id].animNumber = 2068; // Torag
							hitDiff = 4 + MethodCollection.random(15);
						}
						else if (npcs[id].id == 19)
						{
							npcs[id].animNumber = 2068; // white knight
							hitDiff = 10 + MethodCollection.random(40);
						}
						else if (npcs[id].id == 2030)
						{
							npcs[id].animNumber = 2062; // Verac
							hitDiff = 4 + MethodCollection.random(15);
						}
						else if (npcs[id].id == 83)
						{
							npcs[id].animNumber = 64; // Greater Demon
						}
						else if (npcs[id].id == 1472)
						{
							npcs[id].animNumber = 64;
							hitDiff = 10 + MethodCollection.random(20);
						}
						else if (npcs[id].id == 999)
						{
							npcs[id].animNumber = 64;
							hitDiff = 10 + MethodCollection.random(32);
						}
						else if (npcs[id].id == 82)
						{
							npcs[id].animNumber = 64; // Lesser Demon
							hitDiff = 4 + MethodCollection.random(13);
						}
						else if (npcs[id].id == 2745
								&& MethodCollection.random(2) == 1)
						{
							npcs[id].animNumber = 2655; // poke
							npcs[id].actionTimer = 7;
							hitDiff = 2 + MethodCollection.random(45);
						}
						else if (npcs[id].id == 2745
								&& MethodCollection.random(3) == 2)
						{
							npcs[id].animNumber = 2652; // slash
							gfxAll(451, EnemyY, EnemyX);
							npcs[id].actionTimer = 1;
							hitDiff = 10 + MethodCollection.random(32);
						}
						else if (npcs[id].id == 2745
								&& MethodCollection.random(5) == 3)
						{
							npcs[id].animNumber = 2656; // crush
							gfxAll(451, EnemyY, EnemyX);
							npcs[id].actionTimer = 1;
							hitDiff = 10 + MethodCollection.random(35);
						}
						else if (npcs[id].id == 64)
						{
							npcs[id].animNumber = 0x326; // Ogre
						}
						else if (npcs[id].id == 3647)
						{
						}
						else if (npcs[id].id == 3200)
						{
							npcs[id].animNumber = 0x326;
							remove = randomremoveschaos();
							if (remove == 1)
							{
								PlayerHandler.players[pid].removeequipped();
							}
						}
						else if (npcs[id].id == 2745)
						{
							npcs[id].animNumber = 2656;
							npcs[id].actionTimer = 7;
							hitDiff = 1 + MethodCollection.random(75);
						}
						else if (npcs[id].id == 752)
						{
							npcs[id].animNumber = 0x326;
						}
						else
						{
							npcs[id].animNumber = 0x326; // human attack
						}
						client.startAnimation(client
								.GetBlockAnim(client.playerEquipment[Player.playerWeapon]));
						npcs[id].animUpdateRequired = true;
						npcs[id].updateRequired = true;
						if ((EnemyHP - hitDiff) < 0)
						{
							hitDiff = EnemyHP;
						}
						PlayerHandler.players[pid].hitDiff = hitDiff;
						PlayerHandler.players[pid].updateRequired = true;
						PlayerHandler.players[pid].hitUpdateRequired = true;
						PlayerHandler.players[pid].appearanceUpdateRequired = true;
						npcs[id].actionTimer = 7;
					}
				}
				return true;
			}
		}
		return false;
	}

	public boolean AttackPlayerMage(int id)
	{
		Client client = (Client) PlayerHandler.players[npcs[id].StartKilling];

		if (client == null)
		{
			ResetAttackPlayer(id);
			return false;
		}
		else if (client.DirectionCount < 2)
		{
			return false;
		}
		int EnemyHP = client.playerLevel[Player.HITPOINTS];
		int EnemyMaxHP = getLevelForXP(client.playerXP[Player.HITPOINTS]);
		boolean RingOfLife = false;
		if (client.playerEquipment[Player.playerRing] == 2570)
		{
			RingOfLife = true;
		}
		int hitDiff = 0;
		if (npcs[id].actionTimer == 0)
		{
			if (RingOfLife == true
					&& EnemyHP <= (int) ((double) ((double) EnemyMaxHP / 10.0) + 0.5))
			{
				client.SafeMyLife = true;
			}
			else
			{
				if (client.IsDead == true)
				{
					ResetAttackPlayer(id);
				}
				else
				{
					npcs[id].animNumber = 711; // mage attack
					if (npcs[id].id == 1645)
					{
						client.stillgfx(369, client.absY, client.absX);
						hitDiff = 6 + MethodCollection.random(43);
					}
					if (npcs[id].id == 509)
					{
						hitDiff = 8 + MethodCollection.random(20);
					}
					if (npcs[id].id == 1241)
					{
						client.stillgfx(363, client.absY, client.absX);
						hitDiff = 2 + MethodCollection.random(19);
					}
					if (npcs[id].id == 124)
					{
						npcs[id].animNumber = 1833;
						hitDiff = 4 + MethodCollection.random(35);
					}
					if (npcs[id].id == 1246)
					{
						client.stillgfx(368, npcs[id].absY, npcs[id].absX);
						client.stillgfx(367, client.absY, client.absX);
						hitDiff = 4 + MethodCollection.random(35);
					}
					if (npcs[id].id == 1159)
					{
						client.stillgfx(552, client.absY, client.absX);
						hitDiff = 2 + MethodCollection.random(88);
					}
					if (npcs[id].id == 54)
					{
						client.stillgfx(197, client.absY, client.absX);
						hitDiff = 2 + MethodCollection.random(96);
					}
					client.setPlrAnimation(client
							.GetPlrBlockAnim(client.playerEquipment[Player.playerWeapon]));
					npcs[id].animUpdateRequired = true;
					npcs[id].updateRequired = true;
					if ((EnemyHP - hitDiff) < 0)
					{
						hitDiff = EnemyHP;
					}
					client.hitDiff = hitDiff;
					client.updateRequired = true;
					client.hitUpdateRequired = true;
					client.appearanceUpdateRequired = true;
					npcs[id].actionTimer = 7;
				}
			}
			return true;
		}
		return false;
	}

	public boolean AttackNPCMage(int NPCID)
	{
		int EnemyX = npcs[npcs[NPCID].attacknpc].absX;
		int EnemyY = npcs[npcs[NPCID].attacknpc].absY;
		int EnemyHP = npcs[npcs[NPCID].attacknpc].HP;
		int hitDiff = 0;
		if (npcs[NPCID].actionTimer == 0)
		{
			if (npcs[npcs[NPCID].attacknpc].IsDead == true)
			{
				ResetAttackNPC(NPCID);
				npcs[NPCID].animNumber = 2103;
				npcs[NPCID].animUpdateRequired = true;
				npcs[NPCID].updateRequired = true;
			}
			else
			{
				npcs[NPCID].animNumber = 711; // mage attack
				if (npcs[NPCID].id == 1645)
				{
					gfxAll(369, EnemyY, EnemyX);
					hitDiff = 6 + MethodCollection.random(43);
				}
				if (npcs[NPCID].id == 1645)
				{
					gfxAll(369, EnemyY, EnemyX);
					hitDiff = 6 + MethodCollection.random(43);
				}
				if (npcs[NPCID].id == 509)
				{
					hitDiff = 8 + MethodCollection.random(20);
				}
				if (npcs[NPCID].id == 1241)
				{
					gfxAll(363, EnemyY, EnemyX);
					hitDiff = 2 + MethodCollection.random(19);
				}
				if (npcs[NPCID].id == 1246)
				{
					gfxAll(368, npcs[NPCID].absY, npcs[NPCID].absX);
					gfxAll(367, EnemyY, EnemyX);
					hitDiff = 4 + MethodCollection.random(35);
				}
				if (npcs[NPCID].id == 1159)
				{
					gfxAll(552, EnemyY, EnemyX);
					hitDiff = 2 + MethodCollection.random(88);
				}
				if (npcs[NPCID].id == 54)
				{
					gfxAll(197, EnemyY, EnemyX);
					hitDiff = 2 + MethodCollection.random(96);
				}
				npcs[NPCID].animUpdateRequired = true;
				npcs[NPCID].updateRequired = true;
				if ((EnemyHP - hitDiff) < 0)
				{
					hitDiff = EnemyHP;
				}
				npcs[npcs[NPCID].attacknpc].hitDiff = hitDiff;
				npcs[npcs[NPCID].attacknpc].attacknpc = NPCID;
				npcs[npcs[NPCID].attacknpc].updateRequired = true;
				npcs[npcs[NPCID].attacknpc].hitUpdateRequired = true;
				npcs[NPCID].actionTimer = 7;
				return true;
			}
			return false;
		}
		return false;
	}

	public boolean AttackNPC(int NPCID)
	{
		if (npcs[npcs[NPCID].attacknpc] != null)
		{
			int EnemyX = npcs[npcs[NPCID].attacknpc].absX;
			int EnemyY = npcs[npcs[NPCID].attacknpc].absY;
			int EnemyHP = npcs[npcs[NPCID].attacknpc].HP;
			int hitDiff = 0;
			hitDiff = MethodCollection.random(npcs[NPCID].MaxHit);
			if (GoodDistance(EnemyX, EnemyY, npcs[NPCID].absX,
					npcs[NPCID].absY, 1) == true)
			{
				if (npcs[npcs[NPCID].attacknpc].IsDead == true)
				{
					ResetAttackNPC(NPCID);
					npcs[NPCID].textUpdate = "Oh yeah I win bitch!";
					npcs[NPCID].textUpdateRequired = true;
					npcs[NPCID].animNumber = 2103;
					npcs[NPCID].animUpdateRequired = true;
					npcs[NPCID].updateRequired = true;
				}
				else
				{
					if ((EnemyHP - hitDiff) < 0)
					{
						hitDiff = EnemyHP;
					}
					if (npcs[NPCID].id == 9)
					{
						npcs[NPCID].animNumber = 386;
					}
					if (npcs[NPCID].id == 3200)
					{
						npcs[NPCID].animNumber = 0x326;
					}
					if (npcs[NPCID].id == 1605)
					{
						npcs[NPCID].animNumber = 386;
					}
					npcs[NPCID].animUpdateRequired = true;
					npcs[NPCID].updateRequired = true;
					npcs[npcs[NPCID].attacknpc].hitDiff = hitDiff;
					npcs[npcs[NPCID].attacknpc].attacknpc = NPCID;
					npcs[npcs[NPCID].attacknpc].updateRequired = true;
					npcs[npcs[NPCID].attacknpc].hitUpdateRequired = true;
					npcs[NPCID].actionTimer = 7;
					return true;
				}
			}
		}
		return false;
	}

	public boolean ResetAttackNPC(int NPCID)
	{
		npcs[NPCID].IsUnderAttackNpc = false;
		npcs[NPCID].IsAttackingNPC = false;
		npcs[NPCID].attacknpc = -1;
		npcs[NPCID].RandomWalk = true;
		npcs[NPCID].animNumber = 0x328;
		npcs[NPCID].animUpdateRequired = true;
		npcs[NPCID].updateRequired = true;
		return true;
	}

	public int getLevelForXP(int exp)
	{
		int points = 0;
		int output = 0;
		for (int lvl = 1; lvl <= 135; lvl++)
		{
			points += Math.floor((double) lvl + 300.0
					* Math.pow(2.0, (double) lvl / 7.0));
			output = (int) Math.floor(points / 4);
			if (output >= exp)
			{
				return lvl;
			}
		}
		return 0;
	}

	public boolean GoodDistance(int objectX, int objectY, int playerX,
			int playerY, int distance)
	{
		for (int i = 0; i <= distance; i++)
		{
			for (int j = 0; j <= distance; j++)
			{
				if ((objectX + i) == playerX
						&& ((objectY + j) == playerY
								|| (objectY - j) == playerY || objectY == playerY))
				{
					return true;
				}
				else if ((objectX - i) == playerX
						&& ((objectY + j) == playerY
								|| (objectY - j) == playerY || objectY == playerY))
				{
					return true;
				}
				else if (objectX == playerX
						&& ((objectY + j) == playerY
								|| (objectY - j) == playerY || objectY == playerY))
				{
					return true;
				}
			}
		}
		return false;
	}

	private boolean ResetAttackPlayer(int NPCID)
	{
		npcs[NPCID].IsUnderAttack = false;
		npcs[NPCID].StartKilling = 0;
		npcs[NPCID].RandomWalk = true;
		npcs[NPCID].animNumber = 0x328;
		npcs[NPCID].animUpdateRequired = true;
		npcs[NPCID].updateRequired = true;
		return true;
	}

	private boolean loadAutoSpawn(String fileName)
	{
		String line = "";
		String token = "";
		String token2 = "";
		String token2_2 = "";
		String[] token3 = new String[10];
		boolean endReached = false;
		BufferedReader reader = null;
		try
		{
			reader = new BufferedReader(new FileReader("./" + fileName));
		}
		catch (FileNotFoundException fileex)
		{
			MethodCollection.println(fileName + ": file not found.");
			return false;
		}
		try
		{
			line = reader.readLine();
		}
		catch (IOException ioexception)
		{
			MethodCollection.println(fileName + ": error loading file.");
			try
			{
				reader.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			return false;
		}
		while (endReached == false && line != null)
		{
			line = line.trim();
			int spot = line.indexOf("=");
			if (spot > -1)
			{
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
				if (token.equals("spawn"))
				{
					newNPC(Integer.parseInt(token3[0]),
							Integer.parseInt(token3[1]),
							Integer.parseInt(token3[2]),
							Integer.parseInt(token3[3]),
							Integer.parseInt(token3[4]),
							Integer.parseInt(token3[5]),
							Integer.parseInt(token3[6]),
							Integer.parseInt(token3[7]),
							Integer.parseInt(token3[8]),
							GetNpcListHP(Integer.parseInt(token3[0])), true);
				}
			}
			else
			{
				if (line.equals("[ENDOFSPAWNLIST]"))
				{
					try
					{
						reader.close();
					}
					catch (IOException ioexception)
					{
					}
					return true;
				}
			}
			try
			{
				line = reader.readLine();
			}
			catch (IOException ioexception1)
			{
				endReached = true;
			}
		}
		try
		{
			reader.close();
		}
		catch (IOException ioexception)
		{
		}
		return false;
	}

	public int GetNpcListHP(int NpcID)
	{
		for (int i = 0; i < maxListedNPCs; i++)
		{
			if (NpcList[i] != null)
			{
				if (NpcList[i].npcId == NpcID)
				{
					return NpcList[i].npcHealth;
				}
			}
		}
		return 0;
	}

	private boolean loadNPCList(String FileName)
	{
		String line = "";
		String token = "";
		String token2 = "";
		String token2_2 = "";
		String[] token3 = new String[10];
		boolean EndOfFile = false;
		BufferedReader characterfile = null;
		try
		{
			characterfile = new BufferedReader(new FileReader("./" + FileName));
		}
		catch (FileNotFoundException fileex)
		{
			MethodCollection.println(FileName + ": file not found.");
			return false;
		}
		try
		{
			line = characterfile.readLine();
		}
		catch (IOException ioexception)
		{
			MethodCollection.println(FileName + ": error loading file.");
			try
			{
				characterfile.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			return false;
		}
		while (EndOfFile == false && line != null)
		{
			line = line.trim();
			int spot = line.indexOf("=");
			if (spot > -1)
			{
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
				if (token.equals("npc"))
				{
					newNPCList(Integer.parseInt(token3[0]), token3[1],
							Integer.parseInt(token3[2]),
							Integer.parseInt(token3[3]));
				}
			}
			else
			{
				if (line.equals("[ENDOFNPCLIST]"))
				{
					try
					{
						characterfile.close();
					}
					catch (IOException ioexception)
					{
					}
					return true;
				}
			}
			try
			{
				line = characterfile.readLine();
			}
			catch (IOException ioexception1)
			{
				EndOfFile = true;
			}
		}
		try
		{
			characterfile.close();
		}
		catch (IOException ioexception)
		{
		}
		return false;
	}

	private boolean loadNPCDrops(String FileName)
	{
		String line = "";
		String token = "";
		String token2 = "";
		String token2_2 = "";
		String[] token3 = new String[10];
		boolean EndOfFile = false;
		BufferedReader characterfile = null;
		try
		{
			characterfile = new BufferedReader(new FileReader("./" + FileName));
		}
		catch (FileNotFoundException fileex)
		{
			MethodCollection.println(FileName + ": file not found.");
			return false;
		}
		try
		{
			line = characterfile.readLine();
		}
		catch (IOException ioexception)
		{
			MethodCollection.println(FileName + ": error loading file.");
			try
			{
				characterfile.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
			return false;
		}
		while (EndOfFile == false && line != null)
		{
			line = line.trim();
			int spot = line.indexOf("=");
			if (spot > -1)
			{
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
				if (token.equals("npcdrop"))
				{
					int[] Items = new int[10000];
					int[] ItemsN = new int[10000];
					for (int i = 0; i < ((token3.length - 2) / 2); i++)
					{
						if (token3[(2 + (i * 2))] != null)
						{
							Items[i] = Integer.parseInt(token3[(2 + (i * 2))]);
							ItemsN[i] = Integer.parseInt(token3[(3 + (i * 2))]);
						}
						else
						{
							break;
						}
					}
					newNPCDrop(Integer.parseInt(token3[0]),
							Integer.parseInt(token3[1]), Items, ItemsN);
				}
			}
			else
			{
				if (line.equals("[ENDOFNPCDROPLIST]"))
				{
					try
					{
						characterfile.close();
					}
					catch (IOException ioexception)
					{
					}
					return true;
				}
			}
			try
			{
				line = characterfile.readLine();
			}
			catch (IOException ioexception1)
			{
				EndOfFile = true;
			}
		}
		try
		{
			characterfile.close();
		}
		catch (IOException ioexception)
		{
		}
		return false;
	}

	public int GetNPCDropArrayID(int NPCID, int DropType)
	{
		for (int i = 0; i < maxNPCDrops; i++)
		{
			if (NpcDrops[i] != null)
			{
				if (NpcDrops[i].npcId == NPCID
						&& NpcDrops[i].DropType == DropType)
				{
					return i;
				}
			}
		}
		return -1;
	}

}