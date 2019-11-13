package server.world;

public class ObjectExt extends ObjectContainer {
	
	public ObjectExt(int id, int x, int y, int health, int replace, int delay, int type, int face, int refreshas)
	{
		
		objectId = id;
		objectX = x;
		objectY = y;
		objectHealth = health;
		replaceObject = replace;
		objRestoreDelay = delay;
		objectFace = face;
		objectType = type;
		refreshAs = refreshas;
		registered = true;
		System.out.println(id + " " + x + " " + y + " " + health + " " + refreshas);
	}


	}