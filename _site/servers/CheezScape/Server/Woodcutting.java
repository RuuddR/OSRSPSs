public class Woodcutting {

public static String Author = "Xerozcheez";
public static String Comments = "Handles player summoning";

public Woodcutting() {
}

public Woodcutting(int playerID) {
playerId = playerId;
}

public boolean WC(int playerID) {

client player = (client) server.playerHandler.players[playerID];
if(player.WCTimer <= 0) {
if(player.TreeHP == 0) {
player.AddGlobalObj(player.TreeX, player.TreeY, 1341, 0, 10);
player.sendMessage("This tree has ran out of logs");
ResetWC(playerID);
return false;
}
else {
if(!player.hasAxe()) {
player.sendMessage("You need an axe to chop down this tree.");
ResetWC(playerID);
}
else if(player.hasAxe()) {
player.addSkillXP(player.WCxp, 8);
if(!player.addItem(player.logID, player.logAmount)) {
ResetWC(playerID);
return false;
}
else {
player.sendMessage("You get some logs.");
player.WCTimer = player.TreeTimer;
player.TreeHP--;
return true;
}
}
}
}
return false;
}

public void ResetWC(int playerID) {

client player = (client) server.playerHandler.players[playerID];
player.IsWcing = false;
player.WCTimer = 0;
player.logID = 0;
player.logAmount = 0;
player.TreeHP = 20;
player.TreeX = 0;
player.TreeY = 0;
player.TreeTimer = 0;
player.resetAnimation();
player.resetanim = 4;

}

public void Woodcutting(String logName,  int lvlReq, int XPamount, int log, int amount, int obj, int X, int Y, int emote, int playerID) {

client player = (client) server.playerHandler.players[playerID];
if(player.WCTimer <= 0) {
 if(player.playerLevel[8] >= lvlReq) {
   player.pEmote = emote;
   player.resetanim = 999;
   player.sendMessage("You begin to cut the "+logName+" tree.");
   player.logID = log;
   player.logAmount = amount;
   player.TreeX = X;
   player.TreeY = Y;
   player.TreeTimer = GetWCBonus(obj, player.playerLevel[8]);
   player.WCxp = XPamount;
   player.WCTimer = player.TreeTimer;
   player.IsWcing = true;
  }
  else if(player.playerLevel[8] < lvlReq) {
  player.sendMessage("You need a woodcutting level of "+lvlReq+" to chop down this tree.");
  }
 }
}

public int GetWCBonus(int objectID, int wcLvl) {

int lvlB = (wcLvl / 4);

switch (objectID) {

case 1276:
case 1277:
case 1278:
case 1279:
case 1280:
case 1282:
case 1283:
case 1284:
case 1285:
case 1286:
case 1289:
case 1290:
case 1291:
case 1315:
case 1316:
case 1318:
case 1319:
case 1330:
case 1331:
case 1332:
case 1365:
case 1383:
case 1384:
case 2409:
case 3033:
case 3034:
case 3035:
case 3036:
case 3881:
case 3882:
case 3883:
case 5902:
case 5903:
case 5904:
return 25-lvlB;

case 1281:
case 3037:
return 35-lvlB;

case 1308:
case 5551:
case 5552:
case 5553:
return 45-lvlB;

case 1307:
case 4674:
return 60-lvlB;

case 1309:
return 85-lvlB;

case 1292:
case 1306:
return 100-lvlB;

}
return 0;
}

private int playerId;

}