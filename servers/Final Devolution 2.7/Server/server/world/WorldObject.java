package server.world;




import java.util.HashMap;
import java.util.Map;

import server.server;
import server.players.client;

/**
 * 
 * @author Livinglife
 *
 */

public class WorldObject {
	
	private boolean canregister = true;
	private int freeObjects;
	private static Map<Integer, ObjectContainer> objmap = new HashMap<Integer, ObjectContainer>();
	
	
	
	public WorldObject()
	{
        freeObjects = 199;
		//System.out.println("[WORLD-OBJECT] Built Objects.");
	}
	/**
	 * 
	 * @param health - Health, as in amount stored in object
	 * @param x - object x
	 * @param y - object y
	 * @param Id - object id
	 * @param replace - Replacement of object when the health reaches 0
	 * @param Delay - Object delay until refreshed
	 * @param type - Map type (E.g. World object is 10-11)
	 * @param face - Object Face.. not really needed for trees, but can be useful for other things in the future
	 * @param refreshas - What the object will be refreshed as
	 */
	public void registerObject(int health, int x, int y, int Id, int replace, int Delay, int type, int face, int refreshas)
	{
		try
		{
		synchronized(this) //Synchronized to prevent memory inconsistency errors. ~Living
		{
			for (int i = 0; i < 200; i++)
			{
				if (objmap.containsKey(i))
				{
					//System.out.println(":o");
				ObjectContainer obj = objmap.get(i);
				if(obj.objectId == Id && obj.objectX == x && obj.objectY == y && obj.registered)
					canregister = false;
				}
			}
		if (canregister)
		{
		//System.out.println(":oo");
		ObjectExt object = new ObjectExt(Id, x, y, health, replace, Delay, type, face, refreshas);
		objmap.put(freeObjects, object);
		//System.out.println("[WORLD-OBJECT]Object Registered! There are now " + freeObjects + " Free Objects Left before refresh!");
		freeObjects--;
			}
		}
		canregister = true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("static-access")
	public void process()
	{
		for (int i = 0; i < 200; i++)
		{
			if (objmap.containsKey(i))
			{
			ObjectContainer obj = objmap.get(i);
			if (obj.registered && obj.objectHealth <= 0 && obj.startrestore == false)
			{
				//System.out.println("Object Died");
				ReplaceObject(obj.objectX, obj.objectY, obj.replaceObject, obj.objectFace, obj.objectType);
				obj.restoreDel = obj.objRestoreDelay;
				obj.startrestore = true;
				for (int t = 0; t <= server.playerHandler.playerCount; t++)
				{
					client c = (client) server.playerHandler.players[t];
				if(c != null && c.skillX == obj.objectX && c.skillY == obj.objectY) 
				{	
					c.iscutting = false;
					c.cancut = false;
					c.ismining = false;
					c.canmine = false;
					c.startAnimation(65535);
					// -Debug System.out.println("Called");
				}
				
				}
					
			}
		}
		}
		
		if (freeObjects <= 1)
		{
			objmap.clear();
			freeObjects = 199; //Refresh, this will over write old objects that are no longer in use. (likely, there's always the possibility they will still be in use)
			//System.out.println("[WORLD-OBJECT]Objects refreshed, 199 free objects now");
		}
		
		for (int i = 0; i < 200; i++)
		{
			if (objmap.containsKey(i))
			{
			ObjectContainer obj = objmap.get(i);
			if (obj.restoreDel > 0 && obj.registered)
			{
				obj.restoreDel--;
			}
			else if (obj.restoreDel <= 0 && obj.registered && obj.startrestore)
			{
				//System.out.println("Object restored, unregistering object");
				ReplaceObject(obj.objectX, obj.objectY, obj.refreshAs, obj.objectFace, obj.objectType);
				resetObject(i);
			}
		}
		}
		
		
	}
	/**
	 * 
	 * @param x - Object x
	 * @param y - Object y
	 */
	public void reduceHealth(int x, int y)
	{
		for (int i = 0; i < 200; i++)
		{
			if (objmap.containsKey(i))
			{
			ObjectContainer obj = objmap.get(i);
			if (obj.objectX == x && obj.registered && obj.objectY == y)
			{
				obj.objectHealth--;
				//System.out.println(obj.objectHealth);
			}
		}
		}
	}
	/**
	 * 
	 * @param i - slot to reset
	 */
	public void resetObject(int i)
    {  
		if (objmap.containsKey(i))
		{
        	objmap.remove(i);
		//System.out.println("removed");
		}
    }
	/**
	 * 
	 * @param objectX - X
	 * @param objectY - Y
	 * @param NewObjectID - New Object
	 * @param Face - Face
	 * @param ObjectType - Map type, (Eg. 10-11 is world object)
	 * 
	 * Note: I did not create this method
	 */
	@SuppressWarnings("static-access")
	public void ReplaceObject(int objectX, int objectY, int NewObjectID,
			int Face, int ObjectType) {
		for (int i = 0; i <= server.playerHandler.playerCount; i++)
		{
			client c = (client) server.playerHandler.players[i];
		if(c != null) {	
			c.ReplaceServerObject(objectX, objectY, NewObjectID, Face, ObjectType);
			
	}
}
	}
}