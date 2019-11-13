import java.io.*;

public class CharacterInfo{
        public BufferedWriter bw = null;
	public File dir = new File("characters/");
	CharacterInfo(){
		 System.out.println("Character Info - By Miss. Silabsoft");
	}




	public void userlist(){
        	try {
            	bw = new BufferedWriter(new FileWriter("output/userlist.htm", false));
		Write("<table border=1 cellspacing=0 cellpadding=0>");
		Write("<tr><td>");
            	Write("<h1><Center>User List</h1>");
		Write("</td></tr>");
            	bw.newLine();
            	String[] children = dir.list();
            	if (children == null) {
            	} else {
                	for (int i = 0; i < children.length; i++) {
                    		Write("<tr><td>"+children[i].replace(".txt","")+"</td></tr>");
                    		bw.newLine();
				
                	}
            	}
		Write("</table>");
            	bw.close();
		System.out.println("Character Info - Userlist Created for: "+children.length+" users!");
        	} catch (IOException ioexception) {
			System.out.println("Character Info - Failed Creating Userlist");
        	}
	}

	public void users(){
            	String[] children = dir.list();
            	if (children == null) {
            	} else {
                	for (int i = 0; i < children.length; i++)
				loadGame(dir.getAbsolutePath()+"\\"+children[i]);
		}		
	}
	public void index(){
        	try {
            	bw = new BufferedWriter(new FileWriter("output/Index.htm", false));
	    	Write("<FRAMESET COLS=\"20%,*\">");
	    	Write("<FRAME SRC=\"links.htm\" NAME=\"SIDEBAR\">");
	    	Write("<FRAME SRC=\"main.htm\" NAME=\"main\">");
            	bw.newLine();
	    	bw.close();
	    	bw = new BufferedWriter(new FileWriter("output/main.htm", false));
	    	Write("<h1><Center>My Servers Characters</h1><br>");
	    	bw.close();
		bw = new BufferedWriter(new FileWriter("output/links.htm", false));
		Write("<center><A HREF=\"userlist.htm\" TARGET=\"main\">UserList</A><br>");
		bw.close();
		System.out.println("Character Info - Created Index.htm, main.htm, links.htm");
		}
         	catch (IOException ioexception) {
	     		System.out.println("Character Info - Failed Creating Index and Main, links");
        	}
	}
	public void Write(String s){
		try {
			bw.write(s,0,s.length());
		}
         	catch (IOException ioexception) {
	     		System.out.println("Character Info - Error Writing to file");
        	}
	}

	public void loadGame(String dir) {
		String line = "";
		String token = "";
		String token2 = "";
		String[] token3 = new String[3];
		BufferedReader characterfile = null;
		boolean EndOfFile = false;
		try {
			characterfile = new BufferedReader(new FileReader(dir));
		} catch(FileNotFoundException fileex2) {
			System.out.println("Character Info - Error Opening File");
		}
		try {
			line = characterfile.readLine();
		} catch(IOException ioexception) {
			System.out.println("Character Info - Error Reading File");
		}
		while(!EndOfFile &&line != null) {
			line = line.trim();
			int spot = line.indexOf("=");
			if (spot > -1) {
				token = line.substring(0, spot);
				token = token.trim();
				token2 = line.substring(spot + 1);
				token2 = token2.trim();
				token3 = token2.split("\t");
				if(token.equals("character-username")){
					try {
						bw = new BufferedWriter(new FileWriter("output/data/"+token3[0]+".htm", false));
						Write("<table border=\"1\">");
						Write("<tr><td><h1><center>"+token3[0]+"</center></h1></td></tr>");
					}
		 			catch(IOException ioexception) { }
				}

			}
		
			try {
				line = characterfile.readLine();
			} catch(IOException ioexception1) { 
				EndOfFile = true; 
			}
		}
		try { 
			Write("</table>");
			bw.close();
			characterfile.close(); 
		}
		 catch(IOException ioexception) { }
	}
	

}
