/*
 * Walking.java
 * 
 * Created on 23-Dec-2007, 20:03:40
 * 
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package net.varek.rs2d.packethandler;

import net.varek.rs2d.model.Player;
import net.varek.rs2d.model.Point;
import net.varek.rs2d.net.Packet;
import org.apache.mina.common.IoSession;

/**
 *
 * @author alex
 */
public class Walking implements PacketHandler {
	
    public void handlePacket(Packet p, IoSession session) {
		Player player = (Player)session.getAttachment();
		int length = p.getLength();
        
        if(p.getId() == 213)
            length -= 14;

        player.getPathHandler().resetPath();
		
		int stepCount = length - 5;
        if (stepCount % 2 != 0) {
            System.out.println("MALFORMED WALKING COMMAND! stepCount: "+stepCount);
        }
        stepCount /= 2;
        int firstStepX = p.readLEShortA(); //absolute x coordinate
        boolean isRunning = -p.readByte() == 1; //this doesn't seem to ever be true.
        int firstStepY = p.readLEShortA(); //absolute y coordinate
        int[][] wayPointOffsets = new int[stepCount][2];
		for(int i=0; i<stepCount; i++) {
			/**o
			 * Read the steps in one by one.
			 * Steps are local coordinate values.
			 */
			wayPointOffsets[i][0] = p.readByte(); 
			wayPointOffsets[i][1] = p.readByteS();
		}


        // Add the first point to the walking queue.
		player.getPathHandler().addPoint(Point.location(firstStepX, firstStepY));
		
		for(int i=0; i<stepCount; i++) {
			wayPointOffsets[i][0] += firstStepX;
			wayPointOffsets[i][1] += firstStepY;
			player.getPathHandler().addPoint(Point.location(wayPointOffsets[i][0], wayPointOffsets[i][1]));
		}
    }

}
