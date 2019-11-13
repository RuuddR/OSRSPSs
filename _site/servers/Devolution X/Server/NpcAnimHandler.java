import java.io.*;

//My animation handler for all npcs - killamess

	public class NpcAnimHandler {
public int[] block = new int[3851], atk = new int[3851], die = new int[3851];public void println(String str) {System.out.println(str);}public void loadanim() {String line = "";String token = "";String token2 = "";String token2_2 = "";String[] token3 = new String[5];boolean EndOfFile = false;int ReadMode = 0;BufferedReader characterfile = null;try {characterfile = new BufferedReader(new FileReader("config\\NPCEmotes.cfg"));} catch(FileNotFoundException fileex) {println("NPCEmotes.cfg was not found.");} try {line = characterfile.readLine();} catch(IOException ioexception) {println("NPCEmotes.cfg: error loading file.");} while(EndOfFile == false && line != null) {line = line.trim();int spot = line.indexOf("=");if (spot > -1) {
token = line.substring(0, spot);token = token.trim();token2 = line.substring(spot + 1);token2 = token2.trim();token2_2 = token2.replaceAll("\t\t", "\t");token2_2 = token2_2.replaceAll("\t\t", "\t");token2_2 = token2_2.replaceAll("\t\t", "\t");token2_2 = token2_2.replaceAll("\t\t", "\t");token2_2 = token2_2.replaceAll("\t\t", "\t");token3 = token2_2.split("\t");if (token.equals("npcID")) {
atk[Integer.parseInt(token3[0])] = Integer.parseInt(token3[1]);block[Integer.parseInt(token3[0])] = Integer.parseInt(token3[2]);die[Integer.parseInt(token3[0])] = Integer.parseInt(token3[3]);
}} else { if (line.equals("[ENDOFNPCEMOTES]")) {try { characterfile.close(); } catch(IOException ioexception) { }}} try {
line = characterfile.readLine();} catch(IOException ioexception1) { EndOfFile = true; }}try { characterfile.close(); } catch(IOException ioexception) { }}}