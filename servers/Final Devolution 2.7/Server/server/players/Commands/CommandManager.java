package server.players.Commands;

import server.players.Commands.*;


import java.util.HashMap;
import java.util.Map;

import server.players.Commands.*;
import server.players.*;

public class CommandManager {


	private static Map<String, Command> commandMap = new HashMap<String, Command>();
	static {
		//npc commands
		commandMap.put("testnpc", new testnpc());
		commandMap.put("pure", new Pure());
		commandMap.put("setlvl", new Setlvl());
		commandMap.put("pking", new Pking());
		commandMap.put("rares", new Rares());
		commandMap.put("ammys", new Ammys());
		commandMap.put("phats", new Phats());
		commandMap.put("barrows", new Barrows());
		commandMap.put("switch", new Switch());
		commandMap.put("addnpc", new addnpc());
		//max stat command
		commandMap.put("master", new Master());
		//changing appearence Commands
		commandMap.put("char", new Char());
		commandMap.put("redesign", new Char());

		//teleport related Commands
		commandMap.put("tele", new Tele());
		commandMap.put("mypos", new MyPos());
		commandMap.put("xteletome", new XTeleToMe());
		commandMap.put("xteleto", new XTeleTo());

		//staff Commands
		commandMap.put("gfx", new GFX());
		commandMap.put("gfx2", new GFX2());
		commandMap.put("pickup", new Pickup());
		commandMap.put("interface", new Interface());
		commandMap.put("kickall", new KickAll());
		commandMap.put("bank", new Bank());
		commandMap.put("emote", new Emote());
		commandMap.put("godown", new GoDown());
		commandMap.put("goup", new GoUp());
		commandMap.put("setnpc", new SetNpc());
		commandMap.put("Emotes2", new TryEmote());
		commandMap.put("spawnnpc", new SpawnNpc());
		commandMap.put("walkto", new WalkTo());

		//Regular Commands
		commandMap.put("players", new Players());
		commandMap.put("yell", new Yell());
		commandMap.put("max", new Max());
		commandMap.put("empty", new Empty());
		commandMap.put("bonus", new Bonus());
		commandMap.put("runes", new Runes());

		//banning-muting Commands
		commandMap.put("ipban", new IpBan());
		commandMap.put("banuser", new BanUser());
		commandMap.put("mute", new NameMute());
		commandMap.put("ipmute", new IpMute());

		//Disable-Enable Comamnds
		commandMap.put("enableduel", new EnableDuel());
		commandMap.put("disableduel", new DisableDuel());
		commandMap.put("enablepk", new EnablePK());
		commandMap.put("disablepk", new DisablePK());
		commandMap.put("enabletrade", new EnableTrade());
		commandMap.put("disabletrade", new DisableTrade());
		commandMap.put("exprate", new ExpRate());


		//System.out.println("Loaded " + commandMap.size() + " commands.");
	}

	public static void execute(client client, String command) {
		String name = "";
		if (command.indexOf(' ') > -1) {
			name = command.substring(0, command.indexOf(' '));
		} else {
			name = command;
		}
		name = name.toLowerCase();
		if (commandMap.get(name) != null) {
			commandMap.get(name).execute(client, command);
		} else if (name.length() == 0) {
			client.CAM().sendMessage(
					"The command does not exist.");
		} else {
			client.CAM().sendMessage(
					"The command " + name + " does not exist.");
		}
	}

}
