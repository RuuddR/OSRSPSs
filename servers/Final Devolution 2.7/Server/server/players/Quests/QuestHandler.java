package server.players.Quests;

import server.*;
import server.players.*;
import server.items.*;
import server.util.*;
import server.world.*;
import server.npcs.*;

import java.io.*;

public class QuestHandler {

	public client client;
	
	/* QuestNames */
	public String q1Name = "Where Am I?";
	public String q2Name = "Ancients Rule!";
	public String q3Name = "";
	public String q4Name = "";
	public String q5Name = "";
	public String q6Name = "";
	public String q7Name = "";
	public String q8Name = "";
	public String q9Name = "";
	public String q10Name = "";
	public String q11Name = "";
	public String q12Name = "";
	public String q13Name = "";
	public String q14Name = "";
	public String q15Name = "";
	public String q16Name = "";
	public String q17Name = "";
	public String q18Name = "";

		public int q1, q2, q3, q4, q5, q6, q7, q8, q9, q10,
			 q11, q12, q13, q14, q15, q16, q17, q18, tqp = 0;

	public QuestHandler(client c) {
		client = c;
	}

	public void questcomplete(client c, String questN, String reward1, String reward2, String reward3, String reward4, int eqp) {
                tqp += eqp;
	c.CAM().showInterface(12140);
	c.CAM().sendFrame126(questN, 12144);
	c.CAM().sendFrame126(reward1, 12150);
	c.CAM().sendFrame126(reward2, 12151);
	c.CAM().sendFrame126(reward3, 12152);
	c.CAM().sendFrame126(reward4, 12153);
	c.CAM().sendFrame126("Earned QP: " +eqp, 12154);
	c.CAM().sendFrame126("Total QP: " +tqp, 12155);
	c.CAM().sendFrame126("Journal QP: " +tqp, 640);
	c.CAM().sendQuest("QP: "+tqp, 3985);
	c.NpcDialogueSend = true;
	}

	public void questcolorloginchanger(client c) {
		if (Constants.CLEAR_QUEST_SIDE_INTERFACE == false) {
			c.CAM().sendFrame126("Quest Points: "+tqp, 640);
			c.CAM().sendQuest("", 3985);
			c.CAM().sendFrame126("@yel@Quest Jorunal:", 663);
			if (q1 == 0) {
				c.CAM().sendQuest(""+q1Name, 7332);//q1
			}
			if (q1 > 0) {
				c.CAM().sendQuest("@yel@"+q1Name, 7332);//q10
			}
			if (q1 == 15) {
				c.CAM().sendQuest("@gre@"+q1Name, 7332);//q10
			}

			if (q2 == 0) {
				c.CAM().sendQuest(""+q2Name, 7333);//q2
			}
			if (q2 > 0) {
				c.CAM().sendQuest("@yel@"+q2Name, 7333);//q2
			}
			if (q2 == 15) {
				c.CAM().sendQuest("@gre@"+q2Name, 7333);//q2
			}

			if (q3 == 0) {
				c.CAM().sendQuest(""+q3Name, 7334);//q3
			}
			if (q3 > 0) {
				c.CAM().sendQuest("@yel@"+q3Name, 7334);//q3
			}
			if (q3 == 3) {
				c.CAM().sendQuest("@gre@"+q3Name, 7334);//q3
			}

			if (q4 == 0) {
				c.CAM().sendQuest(""+q4Name, 7336);//q4
			}
			if (q4 > 0) {
				c.CAM().sendQuest("@yel@"+q4Name, 7336);//q4
			}
			if (q4 == 15) {
				c.CAM().sendQuest("@gre@"+q4Name, 7336);//q4
			}

			if (q5 == 0) {
				c.CAM().sendQuest(""+q5Name, 7383);//q5
			}
			if (q5 > 0) {
				c.CAM().sendQuest("@yel@"+q5Name, 7383);//q5
			}
			if (q5 == 15) {
				c.CAM().sendQuest("@gre@"+q5Name, 7383);//q5
			}

			if (q6 == 0) {
				c.CAM().sendQuest(""+q6Name, 7339);//q6
			}
			if (q6 > 0) {
				c.CAM().sendQuest("@yel@"+q6Name, 7339);//q6
			}
			if (q6 == 15) {
				c.CAM().sendQuest("@gre@"+q6Name, 7339);//q6
			}

			if (q7 == 0) {
				c.CAM().sendQuest(""+q7Name, 7338);//q7
			}
			if (q7 > 0) {
				c.CAM().sendQuest("@yel@"+q7Name, 7338);//q7
			}
			if (q7 == 15) {
				c.CAM().sendQuest("@gre@"+q7Name, 7338);//q7
			}

			if (q8 == 0) {
				c.CAM().sendQuest(""+q8Name, 7340);//q8
			}
			if (q8 > 0) {
				c.CAM().sendQuest("@yel@"+q8Name, 7340);//q8
			}
			if (q8 == 15) {
				c.CAM().sendQuest("@gre@"+q8Name, 7340);//q8
			}

			if (q9 == 0) {
				c.CAM().sendQuest(""+q9Name, 7346);//q9
			}
			if (q9 > 0) {
				c.CAM().sendQuest("@yel@"+q9Name, 7346);//q9
			}
			if (q9 == 15) {
				c.CAM().sendQuest("@gre@"+q9Name, 7346);//q9
			}

			if (q10 == 0) {
				c.CAM().sendQuest(""+q10Name, 7341);//q10
			}
			if (q10 > 0) {
				c.CAM().sendQuest("@yel@"+q10Name, 7341);//q10
			}
			if (q10 == 15) {
				c.CAM().sendQuest("@gre@"+q10Name, 7341);//q10
			}

			if (q11 == 0) {
				c.CAM().sendQuest(""+q11Name, 7342);//q11
			}
			if (q11 > 0) {
				c.CAM().sendQuest("@yel@"+q11Name, 7342);//q11
			}
			if (q11 == 15) {
				c.CAM().sendQuest("@gre@"+q11Name, 7342);//q11
			}

			if (q12 == 0) {
				c.CAM().sendQuest(""+q12Name, 7337);//q12
			}
			if (q12 > 0) {
				c.CAM().sendQuest("@yel@"+q12Name, 7337);//q12
			}
			if (q12 == 15) {
				c.CAM().sendQuest("@gre@"+q12Name, 7337);//q12
			}

			if (q13 == 0) {
				c.CAM().sendQuest(""+q13Name, 7343);//q13
			}
			if (q13 > 0) {
				c.CAM().sendQuest("@yel@"+q13Name, 7343);//q13
			}
			if (q13 == 15) {
				c.CAM().sendQuest("@gre@"+q13Name, 7343);//q13
			}

			if (q14 == 0) {
				c.CAM().sendQuest(""+q14Name, 7335);//q14
			}
			if (q14 > 0) {
				c.CAM().sendQuest("@yel@"+q14Name, 7335);//q14
			}
			if (q14 == 15) {
				c.CAM().sendQuest("@gre@"+q14Name, 7335);//q14
			}

			if (q15 == 0) {
				c.CAM().sendQuest(""+q15Name, 7344);//q15
			}
			if (q15 > 0) {
				c.CAM().sendQuest("@yel@"+q15Name, 7344);//q15
			}
			if (q15 == 15) {
				c.CAM().sendQuest("@gre@"+q15Name, 7344);//q15
			}

			if (q16 == 0) {
				c.CAM().sendQuest(""+q16Name, 7345);//q16
			}
			if (q16 > 0) {
				c.CAM().sendQuest("@yel@"+q16Name, 7345);//q16
			}
			if (q16 == 15) {
				c.CAM().sendQuest("@gre@"+q16Name, 7345);//q16
			}

			if (q17 == 0) {
				c.CAM().sendQuest(""+q17Name, 7347);//q17
			}
			if (q17 > 0) {
				c.CAM().sendQuest("@yel@"+q17Name, 7347);//q17
			}
			if (q17 == 15) {
				c.CAM().sendQuest("@gre@"+q17Name, 7347);//q17
			}

			if (q18 == 0) {
				c.CAM().sendQuest(""+q18Name, 7348);//q18
			}
			if (q18 > 0) {
				c.CAM().sendQuest("@yel@"+q18Name, 7348);//q18
			}
			if (q18 == 15) {
				c.CAM().sendQuest("@gre@"+q18Name, 7348);//q18
			}
		} else {
			c.CAM().sendQuest("", 7332);//q1
			c.CAM().sendQuest("", 7333);//q2
			c.CAM().sendQuest("", 7334);//q3
			c.CAM().sendQuest("", 7336);//q4
			c.CAM().sendQuest("", 7383);//q5
			c.CAM().sendQuest("", 7339);//q6
			c.CAM().sendQuest("", 7338);//q7
			c.CAM().sendQuest("", 7340);//q8
			c.CAM().sendQuest("", 7346);//q9
			c.CAM().sendQuest("", 7341);//q10
			c.CAM().sendQuest("", 7342);//q11
			c.CAM().sendQuest("", 7337);//q12
			c.CAM().sendQuest("", 7343);//q13
			c.CAM().sendQuest("", 7335);//q14
			c.CAM().sendQuest("", 7344);//q15
			c.CAM().sendQuest("", 7345);//q16
			c.CAM().sendQuest("", 7347);//q17
			c.CAM().sendQuest("", 7348);//q18
		}
	}
	public void questsbuttons(client c, int actionButtonId) {
		//quest buttons
		if (Constants.CLEAR_QUEST_BUTTONS == false) {
			switch(actionButtonId) {
				case 28164://q1
					q1(c);	
				break;
				case 28165://q2
					q2(c);
				break;
				case 28166://q3
					q3(c);
				break;
			//	case 28168://q4
			//		q4(c);
			//	break;
			//	case 28215://q5
			//		q5(c);
			//	break;
			//	case 28171://q6
			//		q6(c);
			//	break;
			//	case 28170://q7
			//		q7(c);
			//	break;
			//	case 28172://q8
			//		q8(c);
			//	break;
			//	case 28178://q9
			//		q9(c);
			//	break;
			//	case 28173://q10
			//		q10(c);
			//	break;
			//	case 28174://q11
			//		q11(c);
			//	break;
			//	case 28169://q12
			//		q12(c);
			//	break;
			//	case 28175://q13
			//		q13(c);
			//	break;
			//	case 28167://q14
			//		q14(c);
			//	break;
			//	case 28176://q15
			//		q15(c);
			//	break;
			//	case 28177://q16
			//		q16(c);
			//	break;
			//	case 28179://q17
			//		q17(c);
			//	break;
			//	case 28180://q18
			//		q18(c);
			//	break;
			} if (Constants.CLEAR_QUEST_BUTTONS == false) {
				switch(actionButtonId) {
					case 28164://q1
					case 28165://q2
					case 28166://q3
					case 28168://q4
					case 28215://q5
					case 28171://q6
					case 28170://q7
					case 28172://q8
					case 28178://q9
					case 28173://q10
					case 28174://q11
					case 28169://q12
					case 28175://q13
					case 28167://q14
					case 28176://q15
					case 28177://q16
					case 28179://q17
					case 28180://q18
					break;
				}
			}
		}
	}



	public void q1(client c) {
		if(q1 == 0){
			loadMenus("q1ps", c);
		}
		if(q1 == 1){
			loadMenus("q1p1", c);
		}
		if(q1 == 2){
			loadMenus("q1p2", c);
		}
		if(q1 == 3){
			loadMenus("q1p3", c);
		}
		if(q1 == 4){
			loadMenus("q1p4", c);
		}
		if(q1 == 5){
			loadMenus("q1p5", c);
		}
		if(q1 == 6){
			loadMenus("q1p6", c);
		}
		if(q1 == 7){
			loadMenus("q1p7", c);
		}
		if(q1 == 8){
			loadMenus("q1p8", c);
		}
		if(q1 == 15){
			loadMenus("q1pf", c);
		}
			c.CAM().sendFrame79(8143);
			c.CAM().showInterface(8134);
			c.flushOutStream();
	}
	public void q2(client c) {
		if(q2 == 0){
		loadMenus("q2ps", c);
		}
		if(q2 == 1){
		loadMenus("q2p1", c);
		}
		if(q2 == 2){
		loadMenus("q2p2", c);
		}
		if(q2 == 3){
		loadMenus("q2p3", c);
		}
		if(q2 == 4){
		loadMenus("q2p4", c);
		}
		if(q2 == 15){
		loadMenus("q2pf", c);
		}
				c.CAM().sendFrame79(8143);
				c.CAM().showInterface(8134);
				c.flushOutStream();
	}
	public void q3(client c) {
		if(q3 == 1){
		loadMenus("q3s", c);
		}
		if(q3 == 2){
		loadMenus("q3p1", c);
		}
		if(q3 == 3){
		loadMenus("q3pf", c);
		}
		c.CAM().sendFrame79(8143);
		c.CAM().showInterface(8134);
		c.flushOutStream();
	}
	public void q4(client c) {
		if(q4 == 0){
		loadMenus("q4s", c);
		}
		if(q4 == 1){
		loadMenus("q4p1", c);
		}
		if(q4 == 2){
		loadMenus("q4p2", c);
		}
		if(q4 == 3){
		loadMenus("q4p3", c);
		}
		if(q4 == 15){
		loadMenus("q4f", c);
		}
		c.CAM().sendFrame79(8143);
		c.CAM().showInterface(8134);
		c.flushOutStream();
	}
	public void q5(client c) {
		if(q5 == 0){
		loadMenus("q5s", c);
		}
		if(q5 == 1){
		loadMenus("q5p1", c);
		}
		if(q5 == 2){
		loadMenus("q5p2", c);
		}
		if(q5 == 3){
		loadMenus("q5p3", c);
		}
		if(q5 == 15){
		loadMenus("q5f", c);
		}
		c.CAM().sendFrame79(8143);
		c.CAM().showInterface(8134);
		c.flushOutStream();
	}
	public void q6(client c) {
		if(q6 == 0){
		loadMenus("q6s", c);
		}
		if(q6 == 1){
		loadMenus("q6p1", c);
		}
		if(q6 == 2){
		loadMenus("q6p2", c);
		}
		if(q6 == 3){
		loadMenus("q6p3", c);
		}
		if(q6 == 15){
		loadMenus("q6f", c);
		}
		c.CAM().sendFrame79(8143);
		c.CAM().showInterface(8134);
		c.flushOutStream();
	}
	public void q7(client c) {
		if(q7 == 0){
		loadMenus("q7s", c);
		}
		if(q7 == 1){
		loadMenus("q7p1", c);
		}
		if(q7 == 2){
		loadMenus("q7p2", c);
		}
		if(q7 == 3){
		loadMenus("q7p3", c);
		}
		if(q7 == 15){
		loadMenus("q7f", c);
		}
		c.CAM().sendFrame79(8143);
		c.CAM().showInterface(8134);
		c.flushOutStream();
	}
	public void q8(client c) {
		if(q8 == 0){
		loadMenus("q8s", c);
		}
		if(q8 == 1){
		loadMenus("q8p1", c);
		}
		if(q8 == 2){
		loadMenus("q8p2", c);
		}
		if(q8 == 3){
		loadMenus("q8p3", c);
		}
		if(q8 == 15){
		loadMenus("q8f", c);
		}
		c.CAM().sendFrame79(8143);
		c.CAM().showInterface(8134);
		c.flushOutStream();
	}
	public void q9(client c) {
		if(q9 == 0){
		loadMenus("q9s", c);
		}
		if(q9 == 1){
		loadMenus("q9p1", c);
		}
		if(q9 == 2){
		loadMenus("q9p2", c);
		}
		if(q9 == 3){
		loadMenus("q9p3", c);
		}
		if(q9 == 15){
		loadMenus("q9f", c);
		}
		c.CAM().sendFrame79(8143);
		c.CAM().showInterface(8134);
		c.flushOutStream();
	}
	public void q10(client c) {
		if(q10 == 0){
		loadMenus("q10s", c);
		}
		if(q10 == 1){
		loadMenus("q10p1", c);
		}
		if(q10 == 2){
		loadMenus("q10p2", c);
		}
		if(q10 == 3){
		loadMenus("q10p3", c);
		}
		if(q10 == 15){
		loadMenus("q10f", c);
		}
		c.CAM().sendFrame79(8143);
		c.CAM().showInterface(8134);
		c.flushOutStream();
	}
	public void q11(client c) {
		if(q11 == 0){
		loadMenus("q11s", c);
		}
		if(q11 == 1){
		loadMenus("q11p1", c);
		}
		if(q11 == 2){
		loadMenus("q11p2", c);
		}
		if(q11 == 3){
		loadMenus("q11p3", c);
		}
		if(q11 == 15){
		loadMenus("q11f", c);
		}
		c.CAM().sendFrame79(8143);
		c.CAM().showInterface(8134);
		c.flushOutStream();
	}
	public void q12(client c) {
		if(q12 == 0){
		loadMenus("q12s", c);
		}
		if(q12 == 1){
		loadMenus("q12p1", c);
		}
		if(q12 == 2){
		loadMenus("q12p2", c);
		}
		if(q12 == 3){
		loadMenus("q12p3", c);
		}
		if(q12 == 15){
		loadMenus("q12f", c);
		}
		c.CAM().sendFrame79(8143);
		c.CAM().showInterface(8134);
		c.flushOutStream();
	}
	public void q13(client c) {
		if(q13 == 0){
		loadMenus("q13s", c);
		}
		if(q13 == 1){
		loadMenus("q13p1", c);
		}
		if(q13 == 2){
		loadMenus("q13p2", c);
		}
		if(q13 == 3){
		loadMenus("q13p3", c);
		}
		if(q13 == 15){
		loadMenus("q13f", c);
		}
		c.CAM().sendFrame79(8143);
		c.CAM().showInterface(8134);
		c.flushOutStream();
	}
	public void q14(client c) {
		if(q14 == 0){
		loadMenus("q14s", c);
		}
		if(q14 == 1){
		loadMenus("q14p1", c);
		}
		if(q14 == 2){
		loadMenus("q14p2", c);
		}
		if(q14 == 3){
		loadMenus("q14p3", c);
		}
		if(q14 == 15){
		loadMenus("q14f", c);
		}
		c.CAM().sendFrame79(8143);
		c.CAM().showInterface(8134);
		c.flushOutStream();
	}
	public void q15(client c) {
		if(q15 == 0){
		loadMenus("q15s", c);
		}
		if(q15 == 1){
		loadMenus("q15p1", c);
		}
		if(q15 == 2){
		loadMenus("q15p2", c);
		}
		if(q15 == 3){
		loadMenus("q15p3", c);
		}
		if(q15 == 15){
		loadMenus("q15f", c);
		}
		c.CAM().sendFrame79(8143);
		c.CAM().showInterface(8134);
		c.flushOutStream();
	}
	public void q16(client c) {
		if(q16 == 0){
		loadMenus("q16s", c);
		}
		if(q16 == 1){
		loadMenus("q16p1", c);
		}
		if(q16 == 2){
		loadMenus("q16p2", c);
		}
		if(q16 == 3){
		loadMenus("q16p3", c);
		}
		if(q16 == 15){
		loadMenus("q16f", c);
		}
		c.CAM().sendFrame79(8143);
		c.CAM().showInterface(8134);
		c.flushOutStream();
	}
	public void q17(client c) {
		if(q17 == 0){
		loadMenus("q17s", c);
		}
		if(q17 == 1){
		loadMenus("q17p1", c);
		}
		if(q17 == 2){
		loadMenus("q17p2", c);
		}
		if(q17 == 3){
		loadMenus("q17p3", c);
		}
		if(q17 == 15){
		loadMenus("q17f", c);
		}
		c.CAM().sendFrame79(8143);
		c.CAM().showInterface(8134);
		c.flushOutStream();
	}
	public void q18(client c) {
		if(q18 == 0){
		loadMenus("q18s", c);
		}
		if(q18 == 1){
		loadMenus("q18p1", c);
		}
		if(q18 == 2){
		loadMenus("q18p2", c);
		}
		if(q18 == 3){
		loadMenus("q18p3", c);
		}
		if(q18 == 15){
		loadMenus("q1f", c);
		}
		c.CAM().sendFrame79(8143);
		c.CAM().showInterface(8134);
		c.flushOutStream();
	}



public void loadMenus(String FileName, client c){
		c.clearQuestInterface();
		String line = "";
		String token = "";
		String token2 = "";
		String token2_2 = "";
		String[] token3 = new String[10];
		boolean EndOfFile = false;
		int ReadMode = 0;
		BufferedReader characterfile = null;
	try {
			characterfile = new BufferedReader(new FileReader("bin/quests/"+FileName+".txt"));

		} catch(FileNotFoundException fileex) {

	}
	try {
		line = characterfile.readLine();
	} catch(IOException ioexception) {
		misc.println("Menu: error loading file.");
			
	}	
		int line2 = 8144;
		while(EndOfFile == false && line != null) {
			if (!line.equals("[END]")) {
				if(line2 == 8144){
					c.clearQuestInterface();
			}
			if(line2 == 8146) {
				line2 = line2+1;
			}
			c.CAM().sendFrame126(line,line2);
			line2 = line2+1;
		} else if(line.equals("[END]")) {
	try {
		characterfile.close(); 
	} catch(IOException ioexception) { 
	}					
}
	try {
		line = characterfile.readLine();
	} catch(IOException ioexception1) { EndOfFile = true; }
	}
		try { 
			characterfile.close();
		} catch(IOException ioexception) {
		}
			c.CAM().sendFrame79(8143);
			c.CAM().showInterface(8134);
			c.flushOutStream();
	}
}