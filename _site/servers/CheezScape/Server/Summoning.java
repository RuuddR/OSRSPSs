
import java.io.*;
import java.util.StringTokenizer;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Summoning {

public static String Author = "Xerozcheez";
public static String Comments = "Handles player summoning";

public Summoning() {
		for(int i = 0; i < maxSummon; i++) {
			NPCID[i] = 0;
			NPCCB[i] = 0;
			LvlReq[i] = 0;
			SendQ[i] = 0;
			ActionID[i] = 0;
			XPgain[i] = 0;
			GFXset[i] = 0;
			RuneReq1[i] = 0;
			RuneReq2[i] = 0;
			RuneReq3[i] = 0;
			RuneReq4[i] = 0;
			RuneReqAM1[i] = 0;
			RuneReqAM2[i] = 0;
			RuneReqAM3[i] = 0;
			RuneReqAM4[i] = 0;
			NPCName[i] = "";
                }
                MonstersLoaded = 0;
                SkillHandler.print("Loading monsters from "+cfgName+" ...", "Summoning");
		LoadMonsters(cfgName);
                SkillHandler.print("Loaded "+MonstersLoaded+" monsters from "+cfgName+"!", "Summoning");
}

public Summoning(int playerID) {
playerId = playerId;
}

public int getSummonXPForLevel(int level) {
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

	public int getSummonLvlForXP(int exp) {
		int points = 0;
		int output = 0;

		for (int lvl = 1; lvl <= 250; lvl++) {
			points += Math.floor((double)lvl + 300.0 * Math.pow(2.0, (double)lvl / 7.0));
			output = (int)Math.floor(points / 4);
			if (output >= exp) {
				return lvl;
			}
		}
		return 250;
	}

public void playerSummon(int index, int playerID) {
    client player = (client) server.playerHandler.players[playerID];
    int maxSummoned = (player.summonLevel / 10);
    boolean continuez = true;

 if(player.summonedNPCS == maxSummoned && player.summonLevel >= 10) {
  player.sendMessage("You can only summon up to "+maxSummoned+" npcs!");
  continuez = false;
}
    boolean hasRune1 = player.playerHasItemAmount(RuneReq1[index], RuneReqAM1[index]);
    boolean hasRune2 = player.playerHasItemAmount(RuneReq2[index], RuneReqAM2[index]);
    boolean hasRune3 = player.playerHasItemAmount(RuneReq3[index], RuneReqAM3[index]);
    boolean hasRune4 = player.playerHasItemAmount(RuneReq4[index], RuneReqAM4[index]);
    boolean hasRune5 = player.playerHasItemAmount(RuneReq5[index], RuneReqAM5[index]);
 if(player.summonLevel >= LvlReq[index] && continuez) {
    if(!hasRune1 && RuneReq1[index] != 0) 
      player.sendMessage("You need "+RuneReqAM1[index]+" "+player.getItemName(RuneReq1[index]));
    if(!hasRune2 && RuneReq2[index] != 0) 
      player.sendMessage("You need "+RuneReqAM2[index]+" "+player.getItemName(RuneReq2[index]));
    if(!hasRune3 && RuneReq3[index] != 0) 
      player.sendMessage("You need "+RuneReqAM3[index]+" "+player.getItemName(RuneReq3[index]));
    if(!hasRune4 && RuneReq4[index] != 0) 
      player.sendMessage("You need "+RuneReqAM4[index]+" "+player.getItemName(RuneReq4[index]));
    if(!hasRune5 && RuneReq5[index] != 0) 
      player.sendMessage("You need "+RuneReqAM5[index]+" "+player.getItemName(RuneReq5[index]));
    else {
     if(RuneReq1[index] != 0)
      player.deleteItem(RuneReq1[index], player.getItemSlot(RuneReq1[index]), RuneReq1[index]);
     if(RuneReq2[index] != 0)
      player.deleteItem(RuneReq2[index], player.getItemSlot(RuneReq2[index]), RuneReq2[index]);
     if(RuneReq3[index] != 0)
      player.deleteItem(RuneReq3[index], player.getItemSlot(RuneReq3[index]), RuneReq3[index]);
     if(RuneReq4[index] != 0)
      player.deleteItem(RuneReq4[index], player.getItemSlot(RuneReq4[index]), RuneReq4[index]);
     if(RuneReq5[index] != 0)
      player.deleteItem(RuneReq5[index], player.getItemSlot(RuneReq5[index]), RuneReq5[index]);
    player.stillgfx(GFXset[index], player.absY-1, player.absX);
    SummonNewNPC(NPCID[index], playerID);
    addSummonXP(XPgain[index], playerID);
    player.sendMessage("You summon a "+NPCName[index]);
  }
 if(player.summonLevel < LvlReq[index]) {
    player.sendMessage("You need a summoning level of "+LvlReq[index]+" to summon this monster!");
  }
 }
}

public void addSummonXP(int amount, int playerID) {
    client player = (client) server.playerHandler.players[playerID];
    int oldLevel = getSummonLvlForXP(player.summonXP);
    player.summonXP += amount;
    RefreshSummonBook(playerID);
  if (oldLevel < getSummonLvlForXP(player.summonXP)) {
   player.summonLevel = getSummonLvlForXP(player.summonXP);
   player.sendMessage("Congratulations you have advanced a summoning level!");
   RefreshSummonBook(playerID);
 } 
}

public void SummonNewNPC(int npcID, int playerID) {
 client player = (client) server.playerHandler.players[playerID];
 server.npcHandler.newSummonedNPC(npcID, player.absX, player.absY-1, player.heightLevel, player.absX-1, player.absY-1,  player.absX+1, player.absY-1, 1, server.npcHandler.GetNpcListHP(npcID), false, player.playerId);
 player.summonedNPCS++;
 player.actionTimer = 20;
}


public void RefreshSummonBook(int playerID) { // Xerozcheez: Refreshes the summoning book

 client player = (client) server.playerHandler.players[playerID];
 player.sendQuest("", 963);
 player.sendQuest("", 8934);
 player.sendQuest("", 6272);
 player.sendQuest("", 6271);
 player.sendQuest("", 9926);
 player.sendQuest("Summoning Spells:", 5450); 
 player.sendQuest("", 4439);
 
 for(int i = 0; i < 10000; i++) {
   if(NPCID[i] != 0) {
    player.sendQuest(NPCName[i]+" (lvl "+NPCCB[i]+")", SendQ[i]);
  }
 }

}

public void applyConfig(int npcID, int npcCB, int lvlReq, int SQ, int ACTION, int XP, int GFX, int runeReq1, int runeReq2, int runeReq3, int runeReq4, int runeReqAM1, int runeReqAM2, int runeReqAM3, int runeReqAM4, String npcName) {
    NPCID[npcID] = npcID;
    NPCCB[npcID] = npcCB;
    LvlReq[npcID] = lvlReq;
    SendQ[npcID] = SQ;
    ActionID[npcID] = ACTION;
    XPgain[npcID] = XP;
    GFXset[npcID] = GFX;
    RuneReq1[npcID] = runeReq1;
    RuneReq2[npcID] = runeReq2;
    RuneReq3[npcID] = runeReq3;
    RuneReq4[npcID] = runeReq4;
    RuneReqAM1[npcID] = runeReqAM1;
    RuneReqAM2[npcID] = runeReqAM2;
    RuneReqAM3[npcID] = runeReqAM3;
    RuneReqAM4[npcID] = runeReqAM4;
    NPCName[npcID] = npcName;
    MonstersLoaded++;
}

	public void LoadMonsters(String fileName) {
		boolean isDatabased = false;
      		String line = "";
      		String token = "";
      		String token2 = "";
      		String token2_2 = "";
      		String[] token3 = new String[10];
      		boolean EndOfFile = false;
      		int ReadMode = 0;
      		BufferedReader characterfile = null;
		/*a3c29b941322c67f1af7205c0ff1fd7d*/
      		try {
         		characterfile = new BufferedReader(new FileReader("./CFG/"+fileName));
      		} catch(FileNotFoundException fileex) {
         		misc.println(fileName+": Not Found.");
         		}
      		try {
         		line = characterfile.readLine();
      		} catch(IOException ioexception) {
         		misc.println(fileName+": Error Loading.");
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

   		if (token.equals("summon")) {
            		int npcID = Integer.parseInt(token3[0]);
			int npcCB = Integer.parseInt(token3[1]);
			int lvlReq = Integer.parseInt(token3[2]);
			int SQ = Integer.parseInt(token3[3]);
			int ACTION = Integer.parseInt(token3[4]);
			int XP = Integer.parseInt(token3[5]);
			int GFX = Integer.parseInt(token3[6]);
			int runeReq1 = Integer.parseInt(token3[7]);
			int runeReq2 = Integer.parseInt(token3[8]);
			int runeReq3 = Integer.parseInt(token3[9]);
			int runeReq4 = Integer.parseInt(token3[10]);
			int runeReq5 = Integer.parseInt(token3[11]);
			int runeReqAM1 = Integer.parseInt(token3[12]);
			int runeReqAM2 = Integer.parseInt(token3[13]);
			int runeReqAM3 = Integer.parseInt(token3[14]);
			int runeReqAM4 = Integer.parseInt(token3[15]);
			int runeReqAM5 = Integer.parseInt(token3[16]);
			String npcName = token3[17];
                        applyConfig(npcID, npcCB, lvlReq, SQ, ACTION, XP, GFX, runeReq1, runeReq2, runeReq3, runeReq4, runeReqAM1, runeReqAM2, runeReqAM3, runeReqAM4, npcName);
                      }
		} else {
		if (line.equals("[EOF]")) {
		try { 
			characterfile.close(); 
			} 
		catch(IOException ioexception) {
		}}}
        	try {
            		line = characterfile.readLine();
        	} catch(IOException ioexception1) { 
			EndOfFile = true; 
			}}
      		try { 
			characterfile.close(); 
			} catch(IOException ioexception) { 
			}}

    private int playerId;
    private int MonstersLoaded = 0;
    private String cfgName = "Summon.cfg";
    public int maxSummon = 10000;
    public int NPCID[] = new int[maxSummon];
    public int NPCCB[] = new int[maxSummon];
    public int LvlReq[] = new int[maxSummon];
    public int SendQ[] = new int[maxSummon];
    public int ActionID[] = new int[maxSummon];
    public int XPgain[] = new int[maxSummon];
    public int GFXset[] = new int[maxSummon];
    public int RuneReq1[] = new int[maxSummon];
    public int RuneReq2[] = new int[maxSummon];
    public int RuneReq3[] = new int[maxSummon];
    public int RuneReq4[] = new int[maxSummon];
    public int RuneReq5[] = new int[maxSummon];
    public int RuneReqAM1[] = new int[maxSummon];
    public int RuneReqAM2[] = new int[maxSummon];
    public int RuneReqAM3[] = new int[maxSummon];
    public int RuneReqAM4[] = new int[maxSummon];
    public int RuneReqAM5[] = new int[maxSummon];
    public String NPCName[] = new String[maxSummon];
}