package server.players.MiscHandlers;

import server.*;
import server.npcs.*;
import server.util.*;
import server.items.*;
import server.world.*;
import server.players.MiscHandlers.*;
import server.players.*;

	public class DialogueAssistant {

	public client client;
	public static int click;

	public DialogueAssistant(client c) {
		client = c;
	}

	public void UpdateNPCChat(client c) {

		c.CAM().sendFrame126("", 976);
		c.StarterQuest.Dialogue(c);
		c.AncientsRule.Dialogue(c);
		c.DrogosQuest.Dialogue(c);
		switch (c.NpcDialogue) {
		case 1:
			c.CAM().sendFrame200(4883, 591);
			c.CAM().sendFrame126(c.GetNpcName(c.NpcTalkTo), 4884);
			c.CAM().sendFrame126("Good day, how can I help you?", 4885);
			c.CAM().sendFrame75(c.NpcTalkTo, 4883);
			c.CAM().sendFrame164(4882);
			c.NpcDialogueSend = true;
			break;

		case 2:
			c.CAM().sendFrame171(1, 2465);
			c.CAM().sendFrame171(0, 2468);
			c.CAM().sendFrame126("What would you like to say?", 2460);
			c.CAM().sendFrame126("I'd like to access my bank account, please.", 2461);
			c.CAM().sendFrame126("I'd like to check my PIN settings.", 2462);
			c.CAM().sendFrame164(2459);
			c.NpcDialogueSend = true;
			break;

		case 4:
			c.CAM().sendFrame171(1, 2465);
			c.CAM().sendFrame171(0, 2468);
			c.CAM().sendFrame126("Select an Option", 2460);
			c.CAM().sendFrame126("Yes please!", 2461);
			c.CAM().sendFrame126("Oh it's a rune shop. No thank you, then.", 2462);
			c.CAM().sendFrame164(2459);
			c.NpcDialogueSend = true;
			break;

		case 5:
			c.CAM().sendFrame200(615, 974);
			c.CAM().sendFrame126(c.playerName, 975);
			c.CAM().sendFrame126("Oh it's a rune shop. No thank you, then.", 977);
			c.CAM().sendFrame185(974);
			c.CAM().sendFrame164(973);
			c.NpcDialogueSend = true;
			break;

		case 6:
			c.CAM().sendFrame200(4883, 591);
			c.CAM().sendFrame126(c.GetNpcName(c.NpcTalkTo), 4884);
			c.CAM().sendFrame126("Well, if you find somone who does want runes, please",4885);
			c.CAM().sendFrame126("send them my way.", 4886);
			c.CAM().sendFrame75(c.NpcTalkTo, 4883);
			c.CAM().sendFrame164(4882);
			c.NpcDialogueSend = true;
			break;

		case 7:
			c.CAM().sendFrame200(4883, 591);
			c.CAM().sendFrame126(c.GetNpcName(c.NpcTalkTo), 4884);
			c.CAM().sendFrame126("Well, if you find somone who does want runes, please",4885);
			c.CAM().sendFrame126("send them my way.", 4886);
			c.CAM().sendFrame75(c.NpcTalkTo, 4883);
			c.CAM().sendFrame164(4882);
			c.NpcDialogueSend = true;
			break;
		case 9:
			c.CAM().sendFrame200(4883, 1597);
			c.CAM().sendFrame126(c.GetNpcName(c.NpcTalkTo), 4884);
			c.CAM().sendFrame126("Select an Option", 2460);
			c.CAM().sendFrame126("Can you teleport me to the mage arena?", 2461);
			c.CAM().sendFrame126("Whats at the mage arena?", 2462);
			c.CAM().sendFrame164(2459);
			c.NpcDialogueSend = true;
			break;
		case 10:
			c.CAM().sendFrame200(4883, 804);
			c.CAM().sendFrame126(c.GetNpcName(804), 4884);
			c.CAM().sendFrame126(c.dsendMessage, 4885);
			c.CAM().sendFrame75(804, 4883);
			c.CAM().sendFrame164(4882);
			c.NpcDialogueSend = true;
			break;
		/*Dialogues 11-64 and  have been used in starter quest-tutorial Island.*/

		/*GHOST BANKER*/
		case 65:
			npctalking(c, 1702, "", "Welcome to Vanquish Bank!", "", "");
		break;
		case 66:
			selectOption2(c, "What would you like to say?", "I'd like to access my bank account, please.", "I'd like to check my PIN settings.");
		break;
		/* MONKEY */
		case 67:
			npctalking(c, 1463, "", "Hi, want a lift to the other side?", "", "");
		break;
		case 68:
			selectOption2(c, "What would you like to say?", "Yes please!", "No thanks.");
		break;
		case 96:
			npctalking(c, 2829, "", "I see you dont have the mage's book.", "Luckly it teleported back to me.", "Want it back?");
		break;
		case 97:
			selectOption2(c, "Want the book back?", "Yes please!", "Not now.");
		break;
		case 98:
			playertalking(c, "", "No now. Thanks anyways.", "", "");
		break;
		case 99:
			playertalking(c, "", "Yes Please!", "", "");
		break;
		case 100:
			npctalking(c, 2829, "", "I charge 10k, for my lost & found items", "", "");
		break;
		case 101:
			if (c.playerHasItem(995, 10000)) {
				c.addItem(6889, 1);
				c.deleteItem(995, 10000);
				c.closeInterface();
				c.NpcDialogue = 0;
				c.NpcDialogueSend = true;
			} else {
				c.CAM().sendMessage("You do not have 10k.");
				c.closeInterface();
				c.NpcDialogue = 0;
				c.NpcDialogueSend = false;
			}
		break;
		case 2650:
			playertalking(c,  "", "Hey, Could you please teleport me to", "the famous Gnome Agility course?","");
			break;
		case 2651:
			npctalking(c, 2650, "", "Yes i sure can, BUT...", "This will cost you 5,000gp.","");
			break;
		case 2652:
			selectOption2(c, "Pay 5,000gp?", "Sure thing!", "No thanks!");
			break;
		case 2653:
			playertalking(c, "","Sure thing, here you go","", "@blu@You pay the Gnome 5,000gp.");
			break;

		case 2654:
			c.runTeleport(0, 2469, 3435, 1, -1, -1, -1, -1, -1, -1, -1, -1, -1);
			c.closeInterface();
			c.RemoveAllWindows();
			c.NpcDialogueSend = true;
			break;
		case 2655:			
			npctalking(c, 2650, "","Sorry but you do not have enough.", "money for me to teleport you.","");
			break;
			
		case 2656:
			playertalking(c, "","No thanks ", "i got better things to do.","");
			break;
		}
	
			
	}

	public void case40(client c) {
			if ((c.NpcDialogue == 1) || c.NpcDialogue == 5 || c.NpcDialogue == 8 || c.NpcDialogue == 9 
			|| c.NpcDialogue == 11 || c.NpcDialogue == 12 || c.NpcDialogue == 13 || c.NpcDialogue == 14 
			|| c.NpcDialogue == 15 || c.NpcDialogue == 16 || c.NpcDialogue == 17 || c.NpcDialogue == 18 
			|| c.NpcDialogue == 19 || c.NpcDialogue == 20 || c.NpcDialogue == 23 || c.NpcDialogue == 26 
			|| c.NpcDialogue == 27 || c.NpcDialogue == 28 || c.NpcDialogue == 30 || c.NpcDialogue == 32 
			|| c.NpcDialogue == 33 || c.NpcDialogue == 36 || c.NpcDialogue == 37 || c.NpcDialogue == 38 
			|| c.NpcDialogue == 39 || c.NpcDialogue == 41 || c.NpcDialogue == 42 || c.NpcDialogue == 43 
			|| c.NpcDialogue == 44 || c.NpcDialogue == 45 || c.NpcDialogue == 47 || c.NpcDialogue == 48 
			|| c.NpcDialogue == 49 || c.NpcDialogue == 50 || c.NpcDialogue == 51 || c.NpcDialogue == 52 
			|| c.NpcDialogue == 53 || c.NpcDialogue == 54 || c.NpcDialogue == 55 || c.NpcDialogue == 56 
			|| c.NpcDialogue == 57 || c.NpcDialogue == 58 || c.NpcDialogue == 59 || c.NpcDialogue == 60 
			|| c.NpcDialogue == 62 || c.NpcDialogue == 63 || c.NpcDialogue == 65 || c.NpcDialogue == 67
			|| c.NpcDialogue == 70 || c.NpcDialogue == 71 || c.NpcDialogue == 73 || c.NpcDialogue == 75
			|| c.NpcDialogue == 76 || c.NpcDialogue == 77 || c.NpcDialogue == 78 || c.NpcDialogue == 79
			|| c.NpcDialogue == 80 || c.NpcDialogue == 81 || c.NpcDialogue == 82 || c.NpcDialogue == 83
			|| c.NpcDialogue == 84 || c.NpcDialogue == 87 || c.NpcDialogue == 88 || c.NpcDialogue == 89
			|| c.NpcDialogue == 90 || c.NpcDialogue == 91 || c.NpcDialogue == 92 || c.NpcDialogue == 93
			|| c.NpcDialogue == 94 || c.NpcDialogue == 96 || c.NpcDialogue == 99 || c.NpcDialogue == 100
			|| c.NpcDialogue == 2650 || c.NpcDialogue == 2651 || c.NpcDialogue == 2653 || c.NpcDialogue == 579
			|| c.NpcDialogue == 580 || c.NpcDialogue == 581 || c.NpcDialogue == 582 || c.NpcDialogue == 583
			|| c.NpcDialogue == 584 || c.NpcDialogue == 585	|| c.NpcDialogue == 586 || c.NpcDialogue == 587
			|| c.NpcDialogue == 588 || c.NpcDialogue == 590 || c.NpcDialogue == 5592 || c.NpcDialogue == 5594){//add continue talking
				c.NpcDialogue += 1;
				c.NpcDialogueSend = false;
			} else {
				c.closeInterface();
			}
		}


	public static void selectOption2(client c, String title, String op1, String op2){
		c.CAM().sendFrame126(title, 2460);
		c.CAM().sendFrame126(op1, 2461);
		c.CAM().sendFrame126(op2, 2462);
		c.CAM().sendFrame164(2459);
		c.NpcDialogueSend = true;
	}
	public static void selectOption3(client c, String title, String op1, String op2, String op3){
		c.CAM().sendFrame126(title, 2470);
		c.CAM().sendFrame126(op1, 2471);
		c.CAM().sendFrame126(op2, 2472);
		c.CAM().sendFrame126(op3, 2473);
		c.CAM().sendFrame164(2469);
		c.NpcDialogueSend = true;
	}
	public static void selectOption4(client c, String title, String op1, String op2, String op3, String op4){
 		c.CAM().sendFrame126(title, 2481);
		c.CAM().sendFrame126(op1, 2482);
		c.CAM().sendFrame126(op2, 2483);
	 	c.CAM().sendFrame126(op3, 2484);
		c.CAM().sendFrame126(op4, 2485);
		c.CAM().sendFrame164(2480);
		c.NpcDialogueSend = true;
	}
	public static void selectOption5(client c, String title, String op1, String op2, String op3, String op4, String op5){
		c.CAM().sendFrame126(title, 2493);
		c.CAM().sendFrame126(op1, 2494);
		c.CAM().sendFrame126(op2, 2495);
		c.CAM().sendFrame126(op3, 2496);
		c.CAM().sendFrame126(op4, 2497);
		c.CAM().sendFrame126(op5, 2498);
		c.CAM().sendFrame164(2492);
		c.NpcDialogueSend = true;
	}
	public static void playertalking(client c, String text, String text1, String text2, String text3){
 		c.CAM().sendFrame200(987, 595);
		c.CAM().sendFrame126(c.playerName.replaceAll("_", " "), 988);
 		c.CAM().sendFrame126(text, 989);
 		c.CAM().sendFrame126(text1, 990);
		c.CAM().sendFrame126(text2, 991);
 		c.CAM().sendFrame126(text3, 992);
 		c.CAM().sendFrame126("Click here to continue", 993);
 		c.CAM().sendFrame185(987);
 		c.CAM().sendFrame164(986);
 		c.NpcDialogueSend = true;
	}
	public static void npctalking(client c, int npc, String text, String text1, String text2, String text3) {
		c.CAM().sendFrame200(4901, 591);
		c.CAM().sendFrame126(c.GetNpcName(npc), 4902);
		c.CAM().sendFrame126(text, 4903);
		c.CAM().sendFrame126(text1, 4904);
		c.CAM().sendFrame126(text2, 4905);
		c.CAM().sendFrame126(text3, 4906);			
		c.CAM().sendFrame75(npc, 4901);
		c.CAM().sendFrame164(4900);
		c.NpcDialogueSend = true;
	}
}