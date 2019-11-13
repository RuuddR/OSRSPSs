package server.players.MiscHandlers;

import java.io.*;
import server.util.*;
import server.npcs.*;
import server.items.*;
import server.*;
import server.players.MiscHandlers.*;
import server.players.*;

import java.util.concurrent.CopyOnWriteArrayList;


public class Trade {

	private client c;
	public Trade(client client) {
		this.c = client;
	}
	
	/**
	* Trading
	**/
	
	public CopyOnWriteArrayList<GameItem> offeredItems = new CopyOnWriteArrayList<GameItem>();
	
	public void requestTrade(int id){
		try {
			client o = (client) server.playerHandler.players[id];
			
			c.tradeWith = id;
			if(!c.inTrade && o.tradeRequested && o.tradeWith == c.playerId) {
				c.getTrade().openTrade();
				o.getTrade().openTrade();			
			} else if(!c.inTrade) {
				
				c.tradeRequested = true;
				c.CAM().sendMessage("Sending trade request...");
				o.CAM().sendMessage(c.playerName + ":tradereq:");
			}
		} 
		catch (Exception e) {
			misc.println("Error requesting trade.");
		}
	}
	
	public void openTrade() {
		client o = (client) server.playerHandler.players[c.tradeWith];
		
		if(o == null) {
			return;
		}
		c.inTrade = true;
		c.canOffer = true;
		c.tradeStatus = 1;
		c.tradeRequested = false;
		c.resetItems(3322);
		resetTItems(3415);
		resetOTItems(3416);
		String out = o.playerName;
		
		if(o.playerRights == 1) {
			out = "@cr1@" + out;
		} 
		else if(o.playerRights == 2) {
			out = "@cr2@" + out;
		}
		c.CAM().sendFrame126("Trading with: " + o.playerName+" who has @gre@"+o.freeSlots()+" free slots" ,3417);
		c.CAM().sendFrame126("", 3431);
		c.CAM().sendFrame126("Are you sure you want to make this trade?", 3535);
		c.CAM().sendFrame248(3323, 3321);
	}	
	
	
	
	public void resetTItems(int WriteFrame) {
        c.outStream.createFrameVarSizeWord(53);
        c.outStream.writeWord(WriteFrame);
		int len = offeredItems.toArray().length;
		int current = 0;
        c.outStream.writeWord(len);
        	for (GameItem item : offeredItems) {
				if (item.amount > 254) {
					c.outStream.writeByte(255);
					c.outStream.writeDWord_v2(item.amount);
				} else {
					c.outStream.writeByte(item.amount);
				}
			c.outStream.writeWordBigEndianA(item.id + 1);
			current++;
        	}
			if(current < 27) {
				for(int i = current; i < 28; i++) {
					c.outStream.writeByte(1);
					c.outStream.writeWordBigEndianA(-1);
				}
			}
		c.outStream.endFrameVarSizeWord();
		c.flushOutStream();
		
    }

	public boolean fromTrade(int itemID, int fromSlot, int amount) {
		client o = (client) server.playerHandler.players[c.tradeWith];
		if(o == null) {
			return false;
		}
		try {
			if (!c.inTrade || !c.canOffer) {
				declineTrade();
				return false;
			}
		c.tradeConfirmed = false;
		o.tradeConfirmed = false;
			if(!Item.itemStackable[itemID]) {
				for(int a = 0; a < amount; a++) {
					for (GameItem item : offeredItems) {
						if(item.id == itemID) {	
							if(!item.stackable) {	
								offeredItems.remove(item);	
								c.addItem(itemID, 1);	
								o.CAM().sendFrame126("Trading with: " + c.playerName+" who has @gre@"+c.freeSlots()+" free slots" ,3417);		
							} else {
								if(item.amount > amount) {
									item.amount -= amount;
									c.addItem(itemID, amount);
									o.CAM().sendFrame126("Trading with: " + c.playerName+" who has @gre@"+c.freeSlots()+" free slots" ,3417);								
								} else {
									amount = item.amount;
									offeredItems.remove(item);
									c.addItem(itemID, amount);
									o.CAM().sendFrame126("Trading with: " + c.playerName+" who has @gre@"+c.freeSlots()+" free slots" ,3417);	
								}
							}
						break;
						}
					o.CAM().sendFrame126("Trading with: " + c.playerName+" who has @gre@"+c.freeSlots()+" free slots" ,3417);	
					c.tradeConfirmed = false;
					o.tradeConfirmed = false;
					c.resetItems(3322);
					resetTItems(3415);
					o.getTrade().resetOTItems(3416);
					c.CAM().sendFrame126("", 3431);
					o.CAM().sendFrame126("", 3431);
					}
				}	
			}
			for (GameItem item : offeredItems) {
				if(item.id == itemID) {
					if(!item.stackable) {
					} else  {
						if(item.amount > amount) {
							item.amount -= amount;
							c.addItem(itemID, amount);
							o.CAM().sendFrame126("Trading with: " + c.playerName+" who has @gre@"+c.freeSlots()+" free slots" ,3417);	
						} else  {
							amount = item.amount;
							offeredItems.remove(item);
							c.addItem(itemID, amount);
							o.CAM().sendFrame126("Trading with: " + c.playerName+" who has @gre@"+c.freeSlots()+" free slots" ,3417);	
						}
					}
					break;
				}
			}

		o.CAM().sendFrame126("Trading with: " + c.playerName+" who has @gre@"+c.freeSlots()+" free slots" ,3417);	
		c.tradeConfirmed = false;
		o.tradeConfirmed = false;
		c.resetItems(3322);
		resetTItems(3415);
		o.getTrade().resetOTItems(3416);
		c.CAM().sendFrame126("", 3431);
		o.CAM().sendFrame126("", 3431);
		} catch(Exception e){}
        return true;
    }
		
	public boolean tradeItem(int itemID, int fromSlot, int amount) {
		client o = (client) server.playerHandler.players[c.tradeWith];
		if(o == null) {
			return false;
		}
		
		for (int i : Constants.ITEM_TRADEABLE) {
			if(i == itemID) {
				c.CAM().sendMessage("You can't trade this item.");
				return false;
			}		
		}
		c.tradeConfirmed = false;
		o.tradeConfirmed = false;
		if(!Item.itemStackable[itemID]) {
			for(int a = 0; a < amount; a++) {
				if(c.playerHasItem(itemID, 1)) {
					offeredItems.add(new GameItem(itemID, 1));	
					c.deleteItem(itemID, c.getItemSlot(itemID), 1);
					o.CAM().sendFrame126("Trading with: " + c.playerName+" who has @gre@"+c.freeSlots()+" free slots" ,3417);	
				}
			}
			o.CAM().sendFrame126("Trading with: " + c.playerName+" who has @gre@"+c.freeSlots()+" free slots" ,3417);	
			c.resetItems(3322);
			resetTItems(3415);
			o.getTrade().resetOTItems(3416);
			c.CAM().sendFrame126("", 3431);
			o.CAM().sendFrame126("", 3431);

		}
	
        if (!c.inTrade || !c.canOffer) {
			declineTrade();
			return false;
		}
	
		if(!c.playerHasItem(itemID, amount)) {
			return false;
		}
		
		if(Item.itemStackable[itemID] || Item.itemIsNote[itemID]) {
			boolean inTrade = false;
			for(GameItem item : offeredItems) {
				if(item.id == itemID) {
					inTrade = true;
					item.amount += amount;
					c.deleteItem(itemID, fromSlot, amount);
					o.CAM().sendFrame126("Trading with: " + c.playerName+" who has @gre@"+c.freeSlots()+" free slots" ,3417);	
					break;
	
				}
			}

			if(!inTrade) {
				offeredItems.add(new GameItem(itemID, amount));
				c.deleteItem(itemID, fromSlot, amount);
				o.CAM().sendFrame126("Trading with: " + c.playerName+" who has @gre@"+c.freeSlots()+" free slots" ,3417);	
			}
		}
		o.CAM().sendFrame126("Trading with: " + c.playerName+" who has @gre@"+c.freeSlots()+" free slots" ,3417);	
		c.resetItems(3322);
		resetTItems(3415);
		o.getTrade().resetOTItems(3416);
		c.CAM().sendFrame126("", 3431);
		o.CAM().sendFrame126("", 3431);
		return true;
		}
	
	
	public void resetTrade() {
		offeredItems.clear();
		c.inTrade = false;
		c.tradeWith = 0;
		c.canOffer = true;
		c.tradeConfirmed = false;
		c.tradeConfirmed2 = false;
		c.acceptedTrade = false;
		c.RemoveAllWindows();
		c.tradeResetNeeded = false;
		c.CAM().sendFrame126("Are you sure you want to make this trade?", 3535);
	}
	public void declineTrade() {
		c.tradeStatus = 0;
		declineTrade(true);
	}
	

	public void declineTrade(boolean tellOther) {
		c.RemoveAllWindows();
		client o = (client) server.playerHandler.players[c.tradeWith];
		if (o == null) {
			return;
		}
		
		if(tellOther){
			o.getTrade().declineTrade(false);
			o.getTrade().c.RemoveAllWindows();
		}
			
		for(GameItem item : offeredItems) {
			if(item.amount < 1) {
				continue;
			}
			if(item.stackable) {
				c.addItem(item.id, item.amount);
			} else {
				for(int i = 0; i < item.amount; i++) {
					c.addItem(item.id, 1);
				}
			}
		}
		c.canOffer = true;
		c.tradeConfirmed = false;
		c.tradeConfirmed2 = false;
		offeredItems.clear();
		c.inTrade = false;
		c.tradeWith = 0;
	}
	
		
	public void resetOTItems(int WriteFrame) {
		client o = (client) server.playerHandler.players[c.tradeWith];
		if(o == null) {
			return;
		}	
		c.outStream.createFrameVarSizeWord(53);
        c.outStream.writeWord(WriteFrame);
		int len = o.getTrade().offeredItems.toArray().length;
		int current = 0;
        c.outStream.writeWord(len);
        	for (GameItem item : o.getTrade().offeredItems) {
            	if (item.amount > 254) {
                	c.outStream.writeByte(255); // item's stack count. if over 254, write byte 255
                	c.outStream.writeDWord_v2(item.amount); 
            	} else {
                	c.outStream.writeByte(item.amount);
            	}
				c.outStream.writeWordBigEndianA(item.id + 1); // item id
				current++;
        	}
		if(current < 27) {
			for(int i = current; i < 28; i++) {
				c.outStream.writeByte(1);
				c.outStream.writeWordBigEndianA(-1);
			}
		}
		c.outStream.endFrameVarSizeWord();
		c.flushOutStream();
		
    }
	
	
	public void confirmScreen() {
		client o = (client) server.playerHandler.players[c.tradeWith];
		if(o == null) {
			return;
		}
		c.canOffer = false;
		c.resetItems(3214);
		String SendTrade = "Absolutely nothing!";
		String SendAmount = "";
		int Count = 0;
		for (GameItem item : offeredItems) {
		    if (item.id > 0) {
				if (item.amount >= 1000 && item.amount < 1000000) {
					SendAmount = "@cya@" + (item.amount / 1000) + "K @whi@(" + misc.format(item.amount) + ")";
				}  else if (item.amount >= 1000000) {
					SendAmount = "@gre@" + (item.amount / 1000000) + " million @whi@(" + misc.format(item.amount) + ")";
				} else {
					SendAmount = "" + misc.format(item.amount);
				}

					if(Count == 0) {	
						SendTrade = c.getItemName(item.id);		
					} else {
						SendTrade = SendTrade + "\\n" + c.getItemName(item.id);
					}
					
						if (item.stackable) {
							SendTrade = SendTrade + " x " + SendAmount;
						}
				Count++;
		    }
		}
		
		c.CAM().sendFrame126(SendTrade, 3557);
		SendTrade = "Absolutely nothing!";
		SendAmount = "";
		Count = 0;
		
		for (GameItem item : o.getTrade().offeredItems) {
		    if (item.id > 0) {
				if (item.amount >= 1000 && item.amount < 1000000) {
					SendAmount = "@cya@" + (item.amount / 1000) + "K @whi@(" + misc.format(item.amount) + ")";
				}  else if (item.amount >= 1000000) {
					SendAmount = "@gre@" + (item.amount / 1000000) + " million @whi@(" + misc.format(item.amount) + ")";
				} else {
					SendAmount = "" + misc.format(item.amount);
				}
				SendAmount = SendAmount;
				
					if (Count == 0) {
						SendTrade = c.getItemName(item.id);		
					} else {
						SendTrade = SendTrade + "\\n" + c.getItemName(item.id);
					}
						if (item.stackable) {
						SendTrade = SendTrade + " x " + SendAmount;
						}
				Count++;
		    }
		}
		c.CAM().sendFrame126(SendTrade, 3558);
		c.CAM().sendFrame248(3443, 3213);
	}
	
	
	public void giveItems() {
		client o = (client) server.playerHandler.players[c.tradeWith];
		if(o == null) {
			return;
		}	
		try{	
			for(GameItem item : o.getTrade().offeredItems){
				if (item.id > 0) {
					c.addItem(item.id, item.amount);
			   }
			}
			
			c.RemoveAllWindows();
			c.tradeResetNeeded = true;
			} catch(Exception e){
			}
		}
	}
