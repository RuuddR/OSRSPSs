import java.io.*;


public class ClanHandler {

public ClanHandler() {
println("Clan System - (C) Slysoft");
LoadClan();
}

public static boolean ClanDataChanged = false; //If yes, clan data is changed
public static ClanData cln = null; //The heart: clan data

public static void println(String s) {
misc.println("[CHANDLER] " + s);
}

public static boolean ClanExists(String name) {
for (int i = 0; i < cln.MaxClans; i++) {
if (cln.ClanName[i].equalsIgnoreCase(name)) {
return true;
}
}
return false;
}

public static int ClanID(String owner) {
for (int i = 0; i < cln.MaxClans; i++) {
if (cln.ClanOwner[i].equalsIgnoreCase(owner)) {
return i;
}
}
return -1;
}

public static boolean ClanExists(int clan) {
return (cln.ClanState[clan] != 0);
}
/*
Error codes

1: Clan doesnt exist
2: Member to kick doesnt exist
3: Same as 1, but in member creation stage
*/

/*
NOTE:

When making kick commands, ensure that the target clan is clan that kicker is!!
Make sure that player cant own multiple clans... It can cause trouble.
*/
public static int GetMemberStatus(int clan, String target) {
if (ClanExists(clan) == false) {
return -1; //Clan doesnt exist!?!?
}
if (cln.ClanOwner[clan].equalsIgnoreCase(target)) {
return -2; //Target is owner of clan!
}
for (int i = 0; i < cln.MaxMembers; i++) {
if (cln.ClanMembers[clan][i].equalsIgnoreCase(target)) {
return cln.MemberState[clan][i];
}
}
return 0;
}

public synchronized static void SetMemberStatus(int clan, String target, int mode) {
for (int i = 0; i < cln.MaxMembers; i++) {
if (cln.ClanMembers[clan][i].equalsIgnoreCase(target)) {
cln.MemberState[clan][i] = mode;
ClanDataChanged = true;
return;
}
}
}

public static synchronized void waitSync() {
//Wait that other methods are stopped (to prevent conflicts)
return;
}

public static void sendError(int ercode, client s) {
s.sendMessage("Internal error in clan system. Please contact admin.");
s.sendMessage("Error code: " + ercode);
}


public static boolean isCOwner(String n) {
for (int i = 0; i < cln.MaxMembers; i++) {
if (cln.ClanOwner[i].equalsIgnoreCase(n)) {
return true;  
}
}
return false;
}
public static boolean isCMember(int clan, String n) {
for (int i = 0; i < cln.MaxMembers; i++) {
if (cln.ClanMembers[clan][i].equalsIgnoreCase(n)) {
return true;
}
}
return false;
}

public synchronized static void zeroClan(int clan) {
waitSync();
cln.ClanOwner[clan] = "";
cln.ClanState[clan] = 0;
cln.ClanName[clan] = "";
for (int i = 0; i < cln.MaxMembers; i++) {
cln.ClanMembers[clan][i] = "";
cln.MemberState[clan][i] = 0;
}
return;
}

public synchronized static void setClanMode(int i, client c) {
try {
String plrname = server.playerHandler.players[c.playerId].playerName;
if (isCOwner(plrname) == false) {
c.sendMessage("You havent got ownership on any clan!");
}
int i2 = ClanID(plrname);
if (i > 0 && i < 4) {
cln.ClanState[i2] = i;
c.sendMessage("Clan mode set to: " + i);
}
} catch (Exception e) {
}
}

public static void inviteClan(String target, client c) {
try {
String plrname = server.playerHandler.players[c.playerId].playerName;
int id = PlayerHandler.getPlayerID(target);
if (id == -1) {
c.sendMessage("Target of invite must be online.");
return;
}
client p2 = (client) server.playerHandler.players[id];
int a = ClanID(plrname);
if (a == -1) {
c.sendMessage("You havent got a clan!!");
return;
}
p2.sendMessage(plrname + " is inviting you to " + cln.ClanName[a]);
createMember(a, p2, true);
c.sendMessage("Invite comptele.");
} catch (Exception e) {
}
}

public static int ClanAmount() {
int amount = 0;
for (int i = 0; i < cln.MaxClans; i++) {
if (cln.ClanState[i] != 0) {
amount++;
}
}
return amount;
}

public static boolean ClanMatches(String ia, String ib) { //Couldnt find a more effective solution, sorry.
for (int i = 0; i < cln.MaxClans; i++) {
if (cln.ClanState[i] != 0) {
for (int i2 = 0; i2 < cln.MaxMembers; i2++) {
if (cln.ClanMembers[i][i2].equalsIgnoreCase(ia) || cln.ClanOwner[i].equalsIgnoreCase(ia)) {
int x = i;

for (int i3 = 0; i3 < cln.MaxClans; i3++) {
if (cln.ClanState[i3] != 0) {
for (int i21 = 0; i21 < cln.MaxMembers; i21++) {
if (cln.ClanMembers[i3][i21].equalsIgnoreCase(ib) || cln.ClanOwner[i3].equalsIgnoreCase(ib)) {
int cc = i3;
if (x == cc) {
return true;
}
}
}
}
}

}
}
}
}
return false;
}

public static void setMemberMod(int clan, client c, String ac) {
try {
String plrname = server.playerHandler.players[c.playerId].playerName;
if (cln.ClanOwner[clan].equalsIgnoreCase(plrname) == false && server.playerHandler.players[c.playerId].playerRights < 2) {
c.sendMessage("You arent owner, so you cant give/remove modship to members.");
return;
}
int a = GetMemberStatus(clan,ac);
if (a == -1) {
c.sendMessage("Clan doesnt exist?");
return;
}
if (a == 0) {
c.sendMessage("Member doesnt exist?");
return;
}
if (a == 3) {
SetMemberStatus(clan, ac, 2);
c.sendMessage("Member successfully removed from mods!");
return;
}
if (a == 2) {
SetMemberStatus(clan, ac, 3);
c.sendMessage("Member successfully made a mod!.");
} else {
c.sendMessage("Hes not yet accepted!!");
}
} catch (Exception e) {
}
}

public static void setMemberIn(int clan, client c, String ac) {
try {
String plrname = server.playerHandler.players[c.playerId].playerName;
if (cln.ClanOwner[clan].equalsIgnoreCase(plrname) == false && GetMemberStatus(clan, plrname) != 3 && server.playerHandler.players[c.playerId].playerRights < 2) {
c.sendMessage("You arent a mod, so you cant accept members.");
return;
}
int a = GetMemberStatus(clan,ac);
if (a == -1) {
c.sendMessage("Clan doesnt exist?");
return;
}
if (a == 0) {
c.sendMessage("Member doesnt exist?");
return;
}
if (a == 1) {
SetMemberStatus(clan, ac, 2);
c.sendMessage("Member successfully accepted.");
} else {
c.sendMessage("He is already accepted!");
}
} catch (Exception e) {
}
}

public static void clanCommands(String command, int context, client c) {
try {
if (command.startsWith("clan-invite")) {
String inv = command.substring(12);
inviteClan(inv, c);
}
if (command.startsWith("clan-create")) {
String name = command.substring(12);
createClan(name, c);
}

if (command.startsWith("clan-join")) {
int clan2join = Integer.parseInt(command.substring(10));
createMember(clan2join, c, false);
}
if (command.startsWith("clan-modshp")) {
if (context < 1 || context > cln.MaxClans) {
c.sendMessage("You need to set current clan context by ::clan-context");
return;
}
String acceptor = command.substring(12);
setMemberMod(context - 1, c, acceptor);
}
if (command.startsWith("clan-accept")) {
if (context < 1 || context > cln.MaxClans) {
c.sendMessage("You need to set current clan context by ::clan-context");
return;
}
String acceptor = command.substring(12);
setMemberIn(context - 1, c, acceptor);
}
if (command.startsWith("clan-kick")) {
if (context < 1 || context > cln.MaxClans) {
c.sendMessage("You need to set current clan context by ::clan-context");
return;
}
String target = command.substring(10);
int id = PlayerHandler.getPlayerID(target);
if (id != -1) {
client p2 = (client) server.playerHandler.players[id];
kickMember(context - 1, target, c, p2);
} else {
kickMember(context - 1, target, c, null);
}
}
if (command.startsWith("clan-mlist")) {
if (context < 1 || context > cln.MaxClans) {
c.sendMessage("You need to set current clan context by ::clan-context");
return;
}
int a = context - 1;
if (ClanExists(a) == false) {
c.sendMessage("Clan doesnt exist.");
return;
}
c.sendMessage(cln.ClanName[a] + " - Owned by " + cln.ClanOwner[a]);
for (int i = 0; i < cln.MaxMembers; i++) {
if (cln.MemberState[a][i] >= 2) {
c.sendMessage(cln.ClanMembers[a][i]);
}
}
}
if (command.startsWith("clan-list")) {
c.sendMessage("Total of " + ClanAmount() + " clans.");
for (int i = 0; i < cln.MaxClans; i++) {
if (cln.ClanState[i] != 0) {
c.sendMessage((i + 1) + ": " +cln.ClanName[i] + " - Owned by " + cln.ClanOwner[i]);
}
}
}
if (command.startsWith("clan-mode")) {
try {
int clanmode = Integer.parseInt(command.substring(10));
ClanDataChanged = true;
setClanMode(clanmode, c);
} catch (Exception e) {
println("List of modes.");
println("1 = Join by invite");
println("2 = Join by acceptance of clan mod");
println("3 = Unlimited join.");
}
}
} catch (Exception e) {
c.sendMessage("Invalid syntax or other error.");
}
}

public synchronized static boolean removeMember(int clan, String name) {
waitSync();
if (cln.ClanOwner[clan].equalsIgnoreCase(name)) {
zeroClan(clan);
ClanDataChanged = true;
return true;
}
for (int i = 0; i < cln.MaxMembers; i++) {
if (cln.ClanMembers[clan][i].equalsIgnoreCase(name)) {
cln.ClanMembers[clan][i] = "";
cln.MemberState[clan][i] = 0;
ClanDataChanged = true;
return true;
}
return false;
}
return true;
}

public static int newMemberSD(int clan, boolean invite) {
int i = newMemberState(clan);
if (i == 1 && invite == false) {
return 1;
} else {
return 2;
}
}

public synchronized static void createClan(String name, client c) {
waitSync();
try {
String plrname = server.playerHandler.players[c.playerId].playerName;
if (isCOwner(plrname)) {
c.sendMessage("You are already owner of one clan!");
return;
}
if (ClanExists(name)) {
c.sendMessage("Clan " + name + " already exists.");
}
int spot = -1;
for (int i = 0; i < cln.MaxClans; i++) {
if (cln.ClanState[i] == 0) {
spot = i;
break;
}
}
if (spot == -1) {
c.sendMessage("Sorry, but there are already max number of clans..");
return;
}
cln.ClanOwner[spot] = plrname;
cln.ClanState[spot] = 1;
c.sendMessage("Clan successfully created.");
c.sendMessage("In default, you need to invite players to clan, but you can change that using ::clan-mode");
ClanDataChanged = true;
return;
} catch (Exception e) {
}
}

public static int newMemberState(int clan) {
return cln.ClanState[clan] - 1;
}

public synchronized static void createMember(int clan, client c, boolean invite) {
waitSync();
try {
if (newMemberState(clan) == 0 && invite == false && server.playerHandler.players[c.playerId].playerRights < 2) {
c.sendMessage("Sorry, but you cant join this clan");
c.sendMessage("Clan owner has set invitations forced.");
return;
}
if (newMemberState(clan) == -1) {
c.sendMessage("That clan ID doesnt exist?");
return;
}
String plrname = server.playerHandler.players[c.playerId].playerName;
int spot = -1;
for (int i = 0; i < cln.MaxMembers; i++) {
if (cln.MemberState[clan][i] == 0) {
spot = i;
break;
}
}
if (spot == -1) {
c.sendMessage("There isnt enough space on clan!!");
return;
}
cln.ClanMembers[clan][spot] = plrname;
if (server.playerHandler.players[c.playerId].playerRights < 2) {
cln.MemberState[clan][spot] = newMemberSD(clan, invite);
} else {
cln.MemberState[clan][spot] = 3; //Automatically become clan mod.
}
c.sendMessage("You have joined clan successfully.");
c.sendMessage("You might have to wait until clan mod approves you.");
PlayerHandler.messageToAll = plrname + " has successfully joined " + cln.ClanName[clan];
} catch (Exception e) {
}
}

public synchronized static void kickMember(int clan, String target, client kicker, client kicktarget) {
try {
int a = GetMemberStatus(clan, server.playerHandler.players[kicker.playerId].playerName);
int s = GetMemberStatus(clan, target);
if (target.equalsIgnoreCase("#removeclan#") && a == -2) {
kicker.sendMessage("Well.. As you wished..");
kicker.sendMessage("Your clan has been removed.");
removeMember(clan, server.playerHandler.players[kicker.playerId].playerName);
return;
} else if (target.startsWith("#")) {
return;
}
if (target.equalsIgnoreCase(server.playerHandler.players[kicker.playerId].playerName) && a != -2) {
kicker.sendMessage("Well.. If you want to leave clan...");
if (removeMember(clan, target)) {
kicker.sendMessage("You have been successfully booted off clan.");
} else {
kicker.sendMessage("Error booting yourself off clan!");
}
return;
}
if (target.equalsIgnoreCase(server.playerHandler.players[kicker.playerId].playerName) && a == -2) {
kicker.sendMessage("Are you really sure about this?");
kicker.sendMessage("You cant transfer clan ownership.. But you can remove clan comptelely");
kicker.sendMessage("If you want to zero your clan, type ::clan-kick #removeclan#");
return;
}
if (a < 3 && a != -2 && server.playerHandler.players[kicker.playerId].playerRights < 2) {
kicker.sendMessage("You arent a mod, so you cant kick anyone");
return;
}
if (s == 0) {
kicker.sendMessage("Member " + target + " not found on clan list.");
kicker.sendMessage("Are you sure that you typed name OK?");
return;
}
if (s == -1 || a == -1) {
kicker.sendMessage("That clan ID doesnt exist?");
return;
}
if (s == -2) {
kicker.sendMessage("You cant kick this member!");
kicker.sendMessage("Hes clan owner!!");
return;
}
if (s == 3 && a == 3 && server.playerHandler.players[kicker.playerId].playerRights < 2) { //Even if clan owner removed your modrights, you can still kick pepole
kicker.sendMessage("You cannot kick fellow mods!");
kicker.sendMessage("Only owner can do that!");
return;
}
kicker.sendMessage("Kicking clan member: " + target);
if (removeMember(clan, target) == true) {
kicker.sendMessage(target + " kicked successfully out!");
if (kicktarget != null) {
kicktarget.sendMessage("You have been booted off from " + cln.ClanName[clan] + " by " + server.playerHandler.players[kicker.playerId].playerName);
PlayerHandler.messageToAll = target + " has been booted off from " + cln.ClanName[clan] + " by " + server.playerHandler.players[kicker.playerId].playerName;

}
} else {
kicker.sendMessage("Error removing user from clan!");
}
}
catch (Exception e) {
}
}

public static synchronized boolean LoadClan() {
waitSync();
if (cln != null && ClanDataChanged == true) { //Comment this, and 2 following lines out, if you dont want to use autosaving security measure
SaveClan(false); //Just a security measure, if you accidentally use reload clandata,this autosaves data
}
try {
boolean exists = (new File("./data/ClanData.dat")).exists();
if (exists == false) {
println("Clan data not found, creating new clan array");
cln = new ClanData();
return SaveClan(true); //Change this to "return true;", if you wont want to save new data.
}
ObjectInputStream in = new ObjectInputStream(new FileInputStream("./data/ClanData.dat"));
cln = (ClanData)in.readObject();
println("Clandata loaded OK!");
return true;
} catch (Exception e) {
println("Clandata load failled!!");
if (cln == null) {
println("Cannot load required data, shutting server..");
server.shutdownClientHandler = true;
}
}
return false;
}

public static synchronized boolean SaveClan(boolean forced) //Synced, to prevent data corruption ("synchronized" forces JVM to finish other call to method, before executing the another one)
{
waitSync();
ClanData tempSave = cln;
if (ClanDataChanged == false && forced == false)
return true;

try
{
ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("./data/ClanData.dat"));
out.writeObject((ClanData)tempSave);
out.close();
}
catch(Exception e){
println("ClanData.dat: Save failled!");
return false;
}
ClanDataChanged = false;
return true;

}
}