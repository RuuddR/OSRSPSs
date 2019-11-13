package server.players.Combat;


import server.*;
import server.util.*;
import server.world.*;
import server.players.*;
import server.players.Combat.*;

public class scanWeapon {

	private client c;
	public scanWeapon(client client) {
		this.c = client;
	}
	public boolean checkWeaponForSpecial(client i) {
		if (i.mC().isNull(i)) {
			return false;
		}
		for (int id = 0; id < i.specialWeapons.length; id++) {
			if (i.playerEquipment[i.playerWeapon] == i.specialWeapons[id]) {
                		return true;
            		} 
        	}
		return false;
	}
	public int getWeaponsDrainAmount(client i) {
		if (i.mC().isNull(i)) {
			return -1;
		}
		
		int drain = 0;
		switch (i.playerEquipment[i.playerWeapon]) {
		case 1215:
		case 1231: 
		case 1305:
		case 1434:
		case 5680:
		case 5698: 
 			drain = 25;
                break;

		case 861:
		case 3204:
          	case 4151:
		case 4153:
		case 4586:
		case 6739: 
 			drain = 50;
                break;

            	case 1377:
 		case 7158:
                	drain = 100;
                break;

        	}
		return drain;
	}
	public boolean doubleHitter(client i) {
		if (i.mC().isNull(i)) {
			return false;
		}
		switch (i.playerEquipment[i.playerWeapon]) {
		case 1215:
		case 1231: 
		case 1305:
            	case 1377:
		case 1434:
		case 5680:
		case 5698: 
		case 15156:
 			return true;
 		}
		return false;
	}
	public boolean hasEnoughSpecial(client i) {
		if (i.mC().isNull(i)) {
			return false;
		}
		if (i.specialAmount >= getWeaponsDrainAmount(i)) {	
			return true;
		}
		return false;
	}
	public int getWeaponAnimation(client i) {
		if (i.mC().isNull(i)) {
			return -1;
		}
		switch(i.playerEquipment[i.playerWeapon]) {
			case 5698:
			case 5680:
			case 1231:  			
			case 1215:  			
				return 1062;
        		
			case 4587:
				return 1872;
        			
        		case 4151:
				return i.CAM().GetWepAnim();
        			
        		case 7158:	
				return 3157;
        		
        		case 3204:
				return 2081;
        		
			case 1305:
				return 1058;
        		
			case 4134:
				return 1060;
        		

           	}
		return -1;
	}
	public int getWeaponGrahpics(client i) {
		if (i.mC().isNull(i)) {
			return -1;
		}
		c.sendSpecialBarAmount();
		c.sendAllSpecialBars();
		switch(i.playerEquipment[i.playerWeapon]) {
			case 5698:
			case 5680:
			case 1231:  			
			case 1215:  			
				return 252;
        		case 4587:
				return 347;
        		case 4151:
				return 341;
        		case 7158:	
				return 559;
        		case 3204:
				return 282;
        		case 1305:
				return 248;
        		case 4134:
				return 251;

           	}
		return -1;
	}

}