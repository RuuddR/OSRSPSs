package server.players.MiscHandlers;



import server.*;
import server.npcs.*;
import server.util.*;
import server.items.*;
import server.world.*;
import server.players.MiscHandlers.*;
import server.players.*;

public class busy {

	private client c;
	public busy(client client) {
		this.c = client;
	}
	public boolean isBusy() {
		if (c.isBusy) {
			return true;
		}
		return false;	
	}
	public void setBusy() {
		c.isBusy = true;
	}
	public void setNotBusy() {
		c.isBusy = false;
	}
}