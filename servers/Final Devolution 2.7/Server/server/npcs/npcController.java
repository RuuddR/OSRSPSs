package server.npcs;

import server.*;
import server.players.*;
import server.util.*;
import server.items.*;
import server.world.*;
import server.npcs.Combat.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
public class npcController {


	public int[] maxHit = new int[3851], meleeDefense = new int[99], rangeDefense = new int[99], magicDefense = new int[99];

	public void println(String str) {
		System.out.println(str);
	}
	public void loadanim() {
		String line = "";
		String token = "";
		String token2 = "";
		String token2_2 = "";
		String[] token3 = new String[4];
		boolean EndOfFile = false;
		int ReadMode = 0;
		BufferedReader characterfile = null;
		println("Npc Controller loaded");
		try {
			characterfile = new BufferedReader(new FileReader("./bin/config/NPCSystem.cfg"));
		} catch(FileNotFoundException fileex) {
			println("NPCSystem.cfg was not found.");
		} try {
			line = characterfile.readLine();
		} catch(IOException ioexception) {
			println("NPCSystem.cfg: error loading file.");
		} 
		while(EndOfFile == false && line != null) {
		line = line.trim();
		int spot = line.indexOf("=");
		if (spot > -1) {
			token = line.substring(0, spot);
			token = token.trim();
			token2 = line.substring(spot + 1);
			token2 = token2.trim();token2_2 = token2.replaceAll("\t\t", "\t");
			token2_2 = token2_2.replaceAll("\t\t", "\t");
			token2_2 = token2_2.replaceAll("\t\t", "\t");
			token2_2 = token2_2.replaceAll("\t\t", "\t");
			token2_2 = token2_2.replaceAll("\t\t", "\t");
			token3 = token2_2.split("\t");
			if (token.equals("NPCID")) {
				maxHit[Integer.parseInt(token3[0])] = Integer.parseInt(token3[1]);
				meleeDefense[Integer.parseInt(token3[0])] = Integer.parseInt(token3[2]);
				rangeDefense[Integer.parseInt(token3[0])] = Integer.parseInt(token3[3]);
				magicDefense[Integer.parseInt(token3[0])] = Integer.parseInt(token3[4]);
			}
		} else { 
			if (line.equals("[ENDOFNPCSYSTEM]")) {
				try { 
					characterfile.close();
				} catch(IOException ioexception) {
 			}
		}
	} try {
	line = characterfile.readLine();
	} catch(IOException ioexception1) {
	EndOfFile = true; 
	}
	}
	try { 
	characterfile.close(); 
	} catch(IOException ioexception) {
 	}
	}
	}