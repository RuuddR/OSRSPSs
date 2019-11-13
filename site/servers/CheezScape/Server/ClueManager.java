/*
*
* Author - Xerozcheez
* Comments - Handles clues
*
*/

import java.io.*;
import java.util.StringTokenizer;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class ClueManager extends SkillHandler {

public ClueManager() {
		for(int i = 0; i < maxClues; i++) {
                        ClueID[i] = 0;
			ClueX[i] = 0;
			ClueY[i] = 0;
			ClueObject[i] = 0;
			ClueNPC[i] = 0;
			ClueString1[i] = "";
			ClueString2[i] = "";
		        ClueString3[i] = "";
			ClueString4[i] = "";
			ClueString5[i] = "";
                }
                CluesLoaded = 0;
                super.print("Loading monsters from "+cfgName+" ...", "Summoning");
		LoadClues(cfgName);
                super.print("Loaded "+CluesLoaded+" monsters from "+cfgName+"!", "Summoning");
}


public void applyConfig(int clueID, int clueX, int clueY, int clueObject, int clueNPC, String str1, String str2, String str3, String str4, String str5) {
    ClueID[clueID] = clueID;
    ClueX[clueID] = clueX;
    ClueY[clueID] = clueY;
    ClueObject[clueID] = clueObject;
    ClueNPC[clueID] = clueNPC;
    ClueString1[clueID] = str1;
    ClueString2[clueID] = str2;
    ClueString3[clueID] = str3;
    ClueString4[clueID] = str4;
    ClueString5[clueID] = str5;
}

	public void LoadClues(String fileName) {
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

   		if (token.equals("clue")) {
            		int clueID = Integer.parseInt(token3[0]);
			int clueX = Integer.parseInt(token3[1]);
			int clueY = Integer.parseInt(token3[2]);
			int clueObject = Integer.parseInt(token3[3]);
			int clueNPC = Integer.parseInt(token3[4]);
		        String str1 = token3[5];
		        String str2 = token3[6];
		        String str3 = token3[7];
		        String str4 = token3[8];
		        String str5 = token3[9];
                        applyConfig(clueID, clueX, clueY, clueObject, clueNPC, str1, str2, str3, str4, str5);
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

    private String cfgName = "Clues.cfg";
    private int playerId;
    private int CluesLoaded = 0;
    public int maxClues = 10000;
    public int ClueID[] = new int[maxClues];
    public int ClueX[] = new int[maxClues];
    public int ClueY[] = new int[maxClues];
    public int ClueObject[] = new int[maxClues];
    public int ClueNPC[] = new int[maxClues];
    public String ClueString1[] = new String[maxClues];
    public String ClueString2[] = new String[maxClues];
    public String ClueString3[] = new String[maxClues];
    public String ClueString4[] = new String[maxClues];
    public String ClueString5[] = new String[maxClues];

}