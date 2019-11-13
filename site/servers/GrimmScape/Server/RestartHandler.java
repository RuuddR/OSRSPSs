/*___________________
|  __     ___     __ |
| |__||\ | | |\/||   |
| |  || \| | |  ||-  |
| |  ||  |_|_|  ||__ |
|____________________|
*/

import java.io.*;

public class RestartHandler
{
	
	public int schedule = 3600;  	// 3600 = 30 mins,  7200 = 1 hr,  43200 = 6 hrs
					// 86400 = 12 hrs,  172800 = 1 day,  1209600 = 7 days
	public int restarttimer = 0; 

	RestartHandler() 
	{
		misc.println("[RESTARTHANDLER]:Created by Anime Used On GrimmScapeV5.0");
	}

	public void process() // Called every 500ms
	{
		restarttimer++;
		if(restarttimer >= schedule) // Matches the timer with your schedule.
		{
			Restart();
		}
	}

	public void Restart() // Kicks(Saves) all players & initializes the restart method.
	{
		PlayerHandler.kickAllPlayers = true;
		misc.println("[RESTARTHANDLER]: Initalizing restart..."); // Informs you the restart handler is initializing.
		restarttimer =0;
		Terminate();
		Execute();
	}

	public void Execute() // Finds the file specified (defualt: Restart.bat) and executes it.
	{
  		try 
		{
			String File = "Restart.bat"; 	// File specified
			String Dir = "./"+File;    	// Directory specified
			Runtime.getRuntime().exec(Dir);
		} 
		catch (IOException ioe) 
		{
	 		ioe.printStackTrace();
			misc.println("[RESTARTHANDLER]: Couldn't find the file."); // Gives a warning if the file wasn't found.
		} 
	}

	public void Terminate() // Terminates the client listener so that we can run a new process of it
	{
		try {
			server.shutdownClientHandler = true;
			if(server.clientListener != null) server.clientListener.close();
			server.clientListener = null;
		} 
		catch(java.lang.Exception __ex) 
		{
			__ex.printStackTrace();
			misc.println("[RESTARTHANDLER]: Failed to restart the server."); // Informs you the server failed to restart.
		}
	}
}