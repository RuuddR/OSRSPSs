/*
*
* Author - Xerozcheez
* Comments - Handles player skills
*
*/

public class SkillHandler {

public SkillHandler() {
 summoning = new Summoning();
}

public static void print(String prt, String name) {
 System.out.println(name+": "+prt);
}

public String statName[] = {"attack", "defence", "strength", "hitpoints", "ranged", "prayer", "magic", "cooking",
	"woodcutting", "fletching", "fishing", "firemaking", "crafting", "smithing", "mining", "herblore", "agility",
	"thieving", "slayer", "farming", "runecrafting", "summoning"
};

public static Summoning summoning = null;

}