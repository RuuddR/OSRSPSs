package server.players.Quests;

import server.players.*;
import server.*;
import server.util.*;


public class DrogosQuest {
	public client client;
	public int npcs = 579;

	public DrogosQuest(client c) {
		client = c;
	}


	public void Dialogue(client c) {
		switch(c.NpcDialogue) {
		//begining
		case 579:
			c.DA().playertalking(c, "", "HELP!", "I HAVE BEEN KIDNAPPED!!", "HELP ME ESCAPE PLEASE!");
		break;
		case 580:
			c.DA().npctalking(c, npcs, "",""+c.playerName+" settle down", "I do not know what you seem to be talking about","");
		break;
		case 581:
			c.DA().playertalking(c, "", "LongStory short, I think ", "that i have been Kidnapped by some strange man","");
		break;
		case 582:
			c.DA().npctalking(c, npcs, "","Well that is just STUPID, HAHA!", "You have not been kidnapped, you have been selected", "to become a mighty warrior one day.");
		break;
		case 583:
			c.DA().playertalking(c, "", "REALY, ME?", "HAHAHA! i am no warrior sir.", "");
		break;
		case 584:
			c.DA().npctalking(c, npcs, "", "listern... I'll tell you what..", "Find me my pick-axe and i will", "supply you with food and pick-axes.");
		break;
		case 585:
			c.DA().playertalking(c, "", "Hmmm... i guess i am realy hungry...", "where could i possibly find this pick-axe??","");
		break;
		case 586:
			c.DA().npctalking(c, npcs, "", "Well... im not sure, I know i was cooking just before", "i had misplaced it somewhere.", "");
		break;
		case 587:
			c.DA().playertalking(c, "", "Well.. I guess i better take off", "to find your pick-axe. Cya later.", "");
		break;
		case 588:
			c.DA().npctalking(c, npcs, "", "Hey thanks alot!, I'll be waiting right here", "until you return with my pick-axe.", "");
		break;
		case 589:
			c.DA().playertalking(c, "", "I'll see you soon.", "", "");
			c.QH().q1 = 7;
		break;
		case 590:
			c.DA().npctalking(c, npcs, "", "Found my pick-axe yet?", "", "");
		break;
		case 591:
			c.DA().playertalking(c, "", "No sorry i'll keep looking.", "", "");
		break;
		case 5592:
			c.DA().npctalking(c, npcs, "", "Have you had any luck with", "finding my pick-axe?", "");
		break;
		case 5593:
			if (!c.playerHasItem(1273)) {
				c.NpcDialogue = 590;
			} else {
				c.DA().playertalking(c, "", "Yes i have, here you go.", "", "");
				c.NpcDialogue = 5594;
			}
		break;
		case 5594:
			c.DA().npctalking(c, npcs, "", "AHHH thank you, have this", "you deserved it!", "@blu@ You have gained access to Drogo's shop.");
		break;
		case 5595:
			c.QH().q1 = 9;
			c.addItem(995, 2000);
			c.addItem(1267, 1);
			c.addItem(333, 1);
			c.addItem(333, 1);
			c.addItem(333, 1);
			c.addItem(333, 1);
			c.deleteItem(1273, 1);
			c.NpcDialogue = 0;
			c.closeInterface();
		break;
		case 5597:
			c.DA().npctalking(c, npcs, "", "Please bring me my pick-axe", "","");
			break;
	
		//ending
		
		
		}
	}
}