package server.players.Quests;

import server.*;
import server.npcs.*;
import server.util.*;
import server.items.*;
import server.world.*;
import server.players.*;
import server.players.Packets.*;
import server.players.Skills.*;

public class AncientsRule {
	public client client;
	public int npcs = 2829;

	public AncientsRule(client c) {
		client = c;
	}

	public void Dialogue(client c) {
		switch(c.NpcDialogue) {
		/* Start of Quest */
			case 70:
				c.DA().playertalking(c, "","Hi.","","");
			break;
			case 71:
				c.DA().npctalking(c, npcs, "", "Hi. What?", "", "");
			break;
			case 72:
				c.DA().selectOption2(c, "Select An Option", "Got any Quests?", "Something else");
			break;
			case 73:
				c.DA().npctalking(c, npcs, "","What?","","");
			break;
			case 74:
				c.DA().playertalking(c, "","Nothing.","","");
			break;
			case 75:
				c.DA().playertalking(c, "","Got any Quests?","","");
				c.CAM().sendQuest("@yel@"+c.QH().q2Name, 7333);
			break;
			case 76:
				c.DA().npctalking(c, npcs, "","Yes, I actually do.","Um here take this spade.","");
				c.addItem(952, 1);
			break;
			case 77:
				c.DA().npctalking(c, npcs, "","I need you to find me a book. Um here are"," some of my notes here u can find", "the book.");
				c.addItem(4597, 1);
			break;
			case 78:
				c.DA().playertalking(c, "","What does this book do?","","");
			break;
			case 79:
				c.DA().npctalking(c, npcs, "","Um, I heard it give ancient powers, but"," who knows until you get it for me.","Oh and btw, you might be attacked.");
			break;
			case 80:
				c.DA().playertalking(c, "","Attacked?!?! By what?","","");
			break;
			case 81:
				c.DA().npctalking(c, npcs, "","I dont know, I heard that the book is cursed.","Any1 who tries to dig it up gets attacked by something.","");
			break;
			case 82:
				c.DA().playertalking(c, "","I see, so you are a chicken. ....","","");
			break;
			case 83:
				c.DA().npctalking(c, npcs, "","well... im a explorer, not a fighter. It looks like you are","a adventurer am I right?"," I think so :)");
			break;
			case 84:
				c.DA().playertalking(c, "","what ever.. let me find that book.","","");
			break;
			case 85:
				c.DA().npctalking(c, npcs, "","ok.","","");
				c.QH().q2 = 1;
				c.CAM().sendQuest("@yel@"+c.QH().q2Name, 7333);
			break;
		/* Ending */

			case 86:
				c.DA().npctalking(c, npcs, "", "You don't have the book yet :(", "", "");
			break;
			case 87:
				c.DA().npctalking(c, npcs, "", "Awsome you got the book!", "", "");
			break;
			case 88:
				c.DA().playertalking(c, "", "Yea I do!", "I had to fight a big bird made", " of stone!");
			break;
			case 89:
				c.DA().npctalking(c, npcs, "", "You mean a Gargoyle?", "", "");
			break;
			case 90:
				c.DA().playertalking(c, "", "Yes! I better get a great reward!", "", "");
			break;
			case 91:
				c.DA().npctalking(c, npcs, "", "Yes :), you can keep that book.", "I equiped it with magical powers", "You can now use ancient magicks.");
			break;
			case 92:
				c.DA().playertalking(c, "", "Kool!", "What about you?", "");
			break;
			case 93:
				c.DA().npctalking(c, npcs, "", "Im a explorer why would I need such a thing?", "Thanks for helping me.", "");
			break;
			case 94:
				c.DA().playertalking(c, "", "No problem I guess.", "", "");
			break;
			case 95:
				c.QH().questcomplete(c, c.QH().q2Name, "Ablity To Use ancients", "Spells Book", "Acients Staff givin by Davey", "", 2);
				c.QH().q2 = 15;
				c.deleteItem(4597, 1);
				c.addItem(4675, 1);
				c.CAM().sendQuest("@gre@"+c.QH().q2Name, 7333);
			break;

		}
	}
}