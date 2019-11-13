package net.paradise.game.npc;

import net.paradise.game.player.Client;
import net.paradise.game.player.PlayerHandler;
import net.paradise.net.StreamBuffer;
import net.paradise.util.MethodCollection;

public class NPC
{

	public int index;
	public int id;
	public int PoisonDelay = 999999;
	public int PoisonClear = 0;
	public int absX, absY;
	public int heightLevel;
	public int makeX, makeY, moverangeX1, moverangeY1, moverangeX2,
			moverangeY2, moveX, moveY, direction, walkingType, attacknpc,
			followPlayer;
	public int spawnX, spawnY;
	public int viewX, viewY;
	public int HP, MaxHP, hitDiff, MaxHit, animNumber, actionTimer,
			StartKilling, enemyX, enemyY;
	public boolean IsDead, DeadApply, NeedRespawn, IsUnderAttack, IsClose,
			Respawns, IsUnderAttackNpc, IsAttackingNPC, poisondmg,
			walkingToPlayer, followingPlayer;
	public int[] Killing = new int[PlayerHandler.maxPlayers];
	public int focusPointX, focusPointY;
	public boolean turnUpdateRequired;
	public boolean RandomWalk;
	public boolean dirUpdateRequired;
	public boolean animUpdateRequired;
	public boolean hitUpdateRequired;
	public boolean updateRequired;
	public boolean textUpdateRequired;
	public boolean faceToUpdateRequired;
	public boolean attackable = true;
	public String textUpdate;
	public Client client;

	public NPC(int index, int id)
	{
		this.index = index;
		this.id = id;
		direction = -1;
		IsDead = false;
		DeadApply = false;
		actionTimer = 0;
		RandomWalk = true;
		StartKilling = 0;
		IsUnderAttack = false;
		IsClose = false;
		for (int i = 0; i < Killing.length; i++)
		{
			Killing[i] = 0;
		}
	}

	public void updateNPCMovement(StreamBuffer stream)
	{
		if (direction == -1)
		{
			// don't have to update the npc position, because the npc is just
			// standing
			if (updateRequired)
			{
				// tell client there's an update block appended at the end
				stream.writeBits(1, 1);
				stream.writeBits(2, 0);
			}
			else
			{
				stream.writeBits(1, 0);
			}
		}
		else
		{
			// send "walking packet"
			stream.writeBits(1, 1);
			stream.writeBits(2, 1); // updateType
			stream.writeBits(3,
					MethodCollection.xlateDirectionToClient[direction]);
			if (updateRequired)
			{
				stream.writeBits(1, 1); // tell client there's an update block
										// appended at the end
			}
			else
			{
				stream.writeBits(1, 0);
			}
		}
	}

	public void turnNpc(int i, int j)
	{
		focusPointX = 2 * i + 1;
		focusPointY = 2 * j + 1;
		updateRequired = true;
		turnUpdateRequired = true;
	}

	private void appendSetFocusDestination(StreamBuffer str)
	{
		str.putShortBE(focusPointX);
		str.putShortBE(focusPointY);
	}

	public void appendNPCUpdateBlock(StreamBuffer str)
	{
		if (!updateRequired)
		{
			return;
		} // nothing required
		int updateMask = 0;
		if (textUpdateRequired)
		{
			updateMask |= 1;
		}
		if (animUpdateRequired)
		{
			updateMask |= 0x10;
		}
		// if(hitUpdateRequired) updateMask |= 0x8;
		if (hitUpdateRequired)
		{
			updateMask |= 0x40;
		}
		if (dirUpdateRequired)
		{
			updateMask |= 0x20;
		}
		if (faceToUpdateRequired)
		{
			updateMask |= 0x20;
		}
		if (turnUpdateRequired)
		{
			updateMask |= 4;
		}
		str.put(updateMask);
		if (textUpdateRequired)
		{
			str.putString(textUpdate);
		}
		if (animUpdateRequired)
		{
			appendAnimUpdate(str);
		}
		if (hitUpdateRequired)
		{
			appendHitUpdate(str);
		}
		if (dirUpdateRequired)
		{
			appendDirUpdate(str);
		}
		if (faceToUpdateRequired)
		{
			appendFaceToUpdate(str);
		}
		if (turnUpdateRequired)
		{
			appendSetFocusDestination(str);
		}
	}

	public void clearUpdateFlags()
	{
		updateRequired = false;
		textUpdateRequired = false;
		hitUpdateRequired = false;
		animUpdateRequired = false;
		dirUpdateRequired = false;
		textUpdate = null;
		moveX = 0;
		moveY = 0;
		direction = -1;
	}

	public int getNextWalkingDirection()
	{
		int dir;
		dir = MethodCollection.direction(absX, absY, (absX + moveX),
				(absY + moveY));
		if (dir == -1)
		{
			return -1;
		}
		dir >>= 1;
		absX += moveX;
		absY += moveY;
		return dir;
	}

	public void getNextNPCMovement()
	{
		direction = -1;
		direction = getNextWalkingDirection();
	}

	protected void appendHitUpdate(StreamBuffer str)
	{
		try
		{
			HP -= hitDiff;
			if (HP <= 0)
			{
				IsDead = true;
			}
			str.putByteC(hitDiff); // What the perseon got 'hit' for
			if (hitDiff > 0 && !poisondmg)
			{
				str.putByteS(1); // 0: red hitting - 1: blue hitting
			}
			else if (hitDiff > 0 && poisondmg)
			{
				str.putByteS(2); // 0: red hitting - 1: blue hitting
			}
			else
			{
				str.putByteS(0); // 0: red hitting - 1: blue hitting
			}
			// HP Bar Fix by Unborn
			str.putByteS(MethodCollection.getCurrentHP(HP, MaxHP, 100));
			str.putByteC(100);
			poisondmg = false;
			// }
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	protected void appendHitUpdate2(StreamBuffer str)
	{
		try
		{
			HP -= hitDiff;
			if (HP <= 0)
			{
				IsDead = true;
			}
			str.putByteS(hitDiff); // What the perseon got 'hit' for
			if (hitDiff > 0 && !poisondmg)
			{
				str.putByteC(1); // 0: red hitting - 1: blue hitting
			}
			else if (hitDiff > 0 && poisondmg)
			{
				str.putByteC(2); // 0: red hitting - 1: blue hitting
			}
			else
			{
				str.putByteC(0); // 0: red hitting - 1: blue hitting
			}
			str.putByteS(HP); // Their current hp, for HP bar
			str.put(MaxHP); // Their max hp, for HP bar
			poisondmg = false;
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void appendAnimUpdate(StreamBuffer str)
	{
		str.putShortBE(animNumber);
		str.put(1);
	}

	public void appendDirUpdate(StreamBuffer str)
	{
		str.putShort(direction);
	}

	public void appendFaceToUpdate(StreamBuffer str)
	{
		str.putShortBE(viewX);
		str.putShortBE(viewY);
	}
}