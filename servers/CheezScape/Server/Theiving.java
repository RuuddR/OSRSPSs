public class Theiving {

public static String Author = "Xerozcheez";
public static String Comments = "Handles player summoning";

public Theiving() {
}

public Theiving(int playerID) {
playerId = playerId;
}

static SkillHandler SkillHandler;

public void TheifStall(String stallName, String message, int lvlReq, int XPamount, int item, int itemAmount, int delay, int emote, int playerID) {

client player = (client) server.playerHandler.players[playerID];

if(player.theifTimer == 0) {
 if(player.playerLevel[17] >= lvlReq) {
   player.setAnimation(emote);
   player.sendMessage("You steal from the "+stallName);
   player.sendMessage(message);
   player.addItem(item, itemAmount);
   player.addSkillXP(XPamount, 17);
   player.theifTimer = delay;
  }
  else if(player.playerLevel[17] < lvlReq) {
  player.sendMessage("You need a theiving level of "+lvlReq+" to theif from this stall.");
  }
 }
}

public void TakeCape(String god, int reqSkillNum, int lvlReq, int XPSkillNum, int XPamount, int item, int itemAmount, int delay, int emote, int playerID) {

client player = (client) server.playerHandler.players[playerID];

if(player.theifTimer == 0) {
 if(player.playerLevel[reqSkillNum] >= lvlReq) {
   player.setAnimation(emote);
   player.sendMessage("You bow down to "+god);
   player.sendMessage("You recieve the cape of "+god+".");
   player.addSkillXP(XPamount, XPSkillNum);
   player.addItem(item, itemAmount);
   player.theifTimer = delay;
  }
  else if(player.playerLevel[reqSkillNum] < lvlReq) {
  player.sendMessage("You need a "+SkillHandler.statName[reqSkillNum]+" level of "+lvlReq+" to pray to "+god+".");
  }
 }
}


public void TakeMisc(String message1, String message2, String message3, int reqSkillNum, int lvlReq, int XPskillnum, int XPamount, int item, int itemAmount, int delay, int emote, int playerID) {

client player = (client) server.playerHandler.players[playerID];

if(player.TakeMiscTimer == 0) {
 if(player.playerLevel[reqSkillNum] >= lvlReq) {
   player.setAnimation(emote);
   player.sendMessage(message1);
   player.sendMessage(message2);
   player.addItem(item, itemAmount);
   player.addSkillXP(XPamount, XPskillnum);
   player.TakeMiscTimer = delay;
  }
  else if(player.playerLevel[reqSkillNum] < lvlReq) {
  player.sendMessage("You need a "+SkillHandler.statName[reqSkillNum]+" level of "+lvlReq+" to "+message3+".");
  }
 }
}

private int playerId;

}