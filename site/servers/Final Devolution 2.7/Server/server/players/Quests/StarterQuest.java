package server.players.Quests;

import server.players.*;
import server.*;
import server.util.*;


public class StarterQuest {
	public client client;
	public int npcs = 0;

	public StarterQuest(client c) {
		client = c;
	}


	public void Dialogue(client c) {
		switch(c.NpcDialogue) {
//begining
		case 11:
			c.DA().playertalking(c, "", "Where am I?", "I dont remember this place...", "Was I kidnapped?");
		break;
		case 12:
			c.DA().npctalking(c, npcs, "", "You on a fishing Dock.", "", "");
		break;
		case 13:
			c.DA().playertalking(c, "", "Who are you?", "Why am I here?", "Was I kidnapped?");
		break;
		case 14:
			c.DA().npctalking(c, npcs, "First, you ask to many questions.", "Second, Im "+Constants.SERVER_NAME+" Guide", "Thirdly, You just magicaly appeared here", "Finally, You probably were kidnapped.");
		break;
		case 15:
			c.DA().playertalking(c, "", "Ok... How do I get out of here???!?!?!?!?!?", "", "");
		break;
		case 16:
			c.DA().npctalking(c, npcs, "", "No idea... But, I might", "be able to teleport you back to", ""+Constants.SERVER_NAME+"'s Main land. But...");
		break;
		case 17:
			c.DA().playertalking(c, "", "But!! BUT!! WHAT DO YOU MEAN BUT???!?!?!", "", "");
		break;
		case 18:
			c.DA().npctalking(c, npcs, "Geez... Getting pushy huh.", "I need rope, and herbs.", "I'll be able to make runes, for you to use.", "so you can teleport back to the Main Land.");
		break;
		case 19:
			c.DA().playertalking(c, "Ok.. One thing... ", "WHERE DO I GET ROPE?!!?! and HERBS????!?!?", "", "");
		break;
		case 20:
			c.DA().npctalking(c, npcs, "Um i left the rope in storage ", "at end of the docks..", "No idea about the Herbs just come back", "after you get the rope.");
		break;
		case 21:
			c.DA().playertalking(c, "", "Sure..", "", "");
			c.QH().q1 = 1;
			c.CAM().sendQuest("@yel@"+c.QH().q1Name, 7332);
		break;
		case 22:
			c.DA().npctalking(c, npcs, "", "You dont have rope yet...", "", "");
		break;


		//ending
		case 25:
			c.DA().npctalking(c, npcs, "", "You need to speak to Drogos.", "", "");
		break;
		case 26:
			c.DA().npctalking(c, npcs, "You found it!", "Where did u find it?", "And awesome time to make that teleport!", "");
		break;
		case 27:
			c.DA().playertalking(c, "", "Your miner friend was selling it.", "", "");
		break;
		case 28:
			c.DA().npctalking(c, npcs, "", "Ok, Yes! I finally done.", "Want to go now?", "");
		break;
		case 29:
			c.DA().selectOption2(c, "Select An Option", "Yes let me out of here!", "I'll talk to you after.");
		break;
		case 30:
			c.DA().playertalking(c, "", "I'll talk to you after.", "", "");
		break;
		case 31:
			c.DA().npctalking(c, npcs, "", "OK, talk to me later then", "", "");
			c.QH().q1 = 11;
			c.CAM().sendQuest("@gre@"+c.QH().q1Name, 7332);
		break;
		case 32:
			c.DA().playertalking(c, "", "Yes let me out of here!", "", "");
		break;
		case 33:
			c.DA().npctalking(c, npcs, "", "OK, ready 1... 2... 3...", "POOFFFFF", "");
			//addgfxs
		break;
		case 34:
			c.teleportToX = Constants.STARTING_SPAWN_POINT_X_CITY+misc.random(1);
			c.teleportToY = Constants.STARTING_SPAWN_POINT_Y_CITY+misc.random(1);
			c.heightLevel = 0;
			c.QH().q1 = 15;
			c.CAM().sendQuest("@gre@"+c.QH().q1Name, 7332);
			c.QH().questcomplete(c, ""+c.QH().q1Name, "50K", "Runes", "Weapons", "Food", 1);
			c.addItem(995, 50000);
			c.addItem(380, 50);
			c.addItem(1321, 1);
			c.addItem(841, 1);
			c.addItem(882, 75);
			c.addItem(556, 100);
			c.addItem(558, 100);
			c.addItem(562, 50);
			if (c.playerHasItem(1527)) {
				c.deleteItem(1527, 1);
			}
		break;

		//learning new things
		case 36:
			c.DA().npctalking(c, npcs, "", "I see you got some rope.", "", "");
		break;
		case 37:
			c.DA().playertalking(c, "", "Yea.. Now what? You know", "where any Herbs are?", "");
		break;
		case 38:
			c.DA().npctalking(c, npcs, "", "No.. But in the mean while, I'll teach you some stuff.", "First catch a fish, then", " make a fire and cook the fish.");
		break;
		case 39:
			c.DA().playertalking(c, "", "Ok, what should I use to catch", "these fishes?", "");
		break;
		case 40:
			c.DA().npctalking(c, npcs, "", "Use these things.", "", "");
			c.deleteItem(954, 1);
			c.addItem(303, 1);
			c.addItem(1511, 1);
			c.addItem(590, 1);
			c.QH().q1 = 3;
		break;
		case 41:
			c.DA().npctalking(c, npcs, "", "Alright Cool! You got that shrimp.", "Now We need to learn some combat.", "");
		break;
		case 42:
			c.DA().playertalking(c, "", "Why do I need to learn combat?", "", "");
		break;
		case 43:
			c.DA().npctalking(c, npcs, "", "So this wont happen again -.-.", "", "");		
		break;
		case 44:
			c.DA().playertalking(c, "", "Good Point. Where do I learn?", "", "");	
		break;
		case 45:
			c.DA().npctalking(c, npcs, "Go to other side of the dock and head upstairs.", "There men you can attack. Once your done killing", "one, bury the bones and come back here.", "This will train prayer and your combat skills.");		
		break;
		case 46:
			c.DA().playertalking(c, "", "OK, be right back!", "", "");
			c.QH().q1 = 4;
			c.deleteItem(303, 1);
			c.deleteItem(590, 1);
		break;
		case 47:
			c.DA().npctalking(c, npcs, "", "Oh, your back already?", "", "");		
		break;
		case 48:
			c.DA().playertalking(c, "", "Yes, I did. Do you remember about that herb?", "", "");		
		break;
		case 49:
			c.DA().npctalking(c, npcs, "Yea, I know, but I cant remember.", "But I'll teach you some other things.", "Like the Smilies on the bottem, its for your", "friends/ignores. You can private message them.");
		break;
		case 50:
			c.DA().npctalking(c, npcs, "If you want to use Spells, see that book on the corner", "its spells you can use.", "You can't teleport until your done here.", "Next we learn about Quests.");		
		break;
		case 51:
			c.DA().npctalking(c, npcs, "", "Quests, can but very helpful to earn money, and", "help "+Constants.SERVER_NAME+" Citizens.", "Prayers can be acessed through the Cross shape thing.");		
		break;
		case 52:
			c.DA().npctalking(c, npcs, "", "Then The wrentch has game options you can use.", "The litte person on the bottem, have more options", "and emotions you can use through out your adventure.");		
		break;
		case 53:
			c.DA().npctalking(c, npcs, "", "Next we look at the equipments.", "The icon is the man above you can see all the things", "you equip, armours/weapons and what not.");		
		break;
		case 54:
			c.DA().npctalking(c, npcs, "", "Now that is done, we need to know about the", "status bars. This shows all your", "skills and stat lvls. This is very useful.");		
		break;
		case 55:
			c.DA().npctalking(c, npcs, "Finally, we learn about different attaking option.", "The icon is the dual swords. You can change attaking", "options to defensive/attacking/strength,", " this changes for each weapon.");		
		break;
		case 56:
			c.DA().npctalking(c, npcs, "", "You get that? Hope so because", "im not repeating it all again!", "");		
		break;
		case 57:
			c.DA().playertalking(c, "", "I think I get it. Thanks for the explainations.", "Can you repeat Quests?", "Please!");		
		break;
		case 58:
			c.DA().npctalking(c, npcs, "", "Sure. Quests can be a great way to", "earn money, and help citizens of "+Constants.SERVER_NAME+".", "");		
		break;
		case 59:
			c.DA().playertalking(c, "", "KK, thanks. Now then remember the Herbs?", "", "");		
		break;
		case 60:
			c.DA().npctalking(c, npcs, "Ehh no i don't but talk to me friend Drogos for", "some minning help. He maybe remind me", "where the herb is.", "");
			c.QH().q1 = 6;
		break;
		case 61:
			c.DA().playertalking(c, "", "ok, i'll see if he will talk to me.", "", "");		
		break;

		case 63:
			c.DA().npctalking(c, npcs, "", "Use the stuff I gave you to", "fish and cook. Then talk to me.", "");
		break;
		case 64:
			c.DA().npctalking(c, npcs, "", "Kill the men and bury a bone.", "Then come back and talk to me.", "");
		break;
		case 130:
			c.DA().npctalking(c, npcs, "", "Talk to Drogos", "", "");
		break;


		}
	}
}