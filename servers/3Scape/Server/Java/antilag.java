import java.io.*;

public class antilag
{

public int ResetTimer = 0;

public void resetserver()
{
PlayerHandler.kickAllPlayers = true;
misc.println("Restarting <3Scape.");
ResetTimer = 0;
closeListener();
runserver();
}
public void process()
{
ResetTimer += 1;
if(ResetTimer >= 7000)
{
resetserver();
}
}

public void runserver()
{
try 
{
String run = "Run.bat";
String xstr = "./"+run;
Runtime.getRuntime().exec(xstr);
} 
catch (IOException ioe) 
{
ioe.printStackTrace();
} 
}

public void closeListener()
{
try {
server.shutdownClientHandler = true;
if(server.clientListener != null) server.clientListener.close();
server.clientListener = null;
} 
catch(java.lang.Exception __ex) 
{
__ex.printStackTrace();
}
}
}