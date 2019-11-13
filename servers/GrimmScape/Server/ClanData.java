import java.io.*;

public class ClanData implements Serializable {

public int MaxClans = 100; //Change this for more clans.
public int MaxMembers = 50; //Change this for more members for clan
public String[] ClanOwner; //The ABSOLUTE owner of clans (That member that is unkickable)
public String[][] ClanMembers; //Members of clan
public int[][] MemberState; //0 = Not existing, 1 = Joined, but not yet accepted, 2 = Joined, accepted, 3 = Joined & accepted, clan mod
public String[] ClanName;
public int[] ClanState; //0 = Not existing, 1 = Alive, join by special invitation, 2 = Alive, join by request, and acceptance of clan mod needed, 3 = Alive, and unlimited join.


public ClanData() {
ClanOwner = new String[MaxClans];
ClanMembers = new String[MaxClans][MaxMembers];
ClanState = new int[MaxClans];
MemberState = new int[MaxClans][MaxMembers];
ClanName = new String[MaxClans];
for (int i = 0; i < MaxClans; i++) {
ClanOwner[i] = "";
ClanState[i] = 0;
ClanName[i] = "";
for (int i2 = 0; i2 < MaxMembers; i2++) {
ClanMembers[i][i2] = "";
MemberState[i][i2] = 0;
}
}
}



}