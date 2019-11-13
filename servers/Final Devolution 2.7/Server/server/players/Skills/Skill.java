package server.players.Skills;

import server.*;
import server.util.*;
import server.players.*;

public interface Skill
{

	boolean doaction(client c, int toadd, int toremove, int toremove2, int x, int y, int objectid, int xp);

	void readInput(int level, String type, client c, int objectx, int objecty, int objectid);

	void out(String s, client c);
}