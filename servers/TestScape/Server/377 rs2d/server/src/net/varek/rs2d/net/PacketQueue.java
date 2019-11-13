package net.varek.rs2d.net;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Synchronized packet queue
 */
public class PacketQueue {
	/**
	 * The list of packets in the queue
	 */
	private Queue<Packet> packets = new LinkedBlockingQueue<Packet>();

	/**
	 * Adds a packet to the queue
	 */
	public void add(Packet p) {
		packets.add(p);
	}

	/**
	 * Returns if there is packets to process
	 */
	public boolean hasPackets() {
		return !packets.isEmpty();
	}

	/**
	 * Returns the packets currently in the list
	 * and removes them from the backing store
	 */
	public List<Packet> getPackets() {
		List<Packet> tmpList = null;
		synchronized (packets) {
			tmpList = new ArrayList<Packet>(packets.size());
			tmpList.addAll(packets);
			packets.clear();
		}
		return tmpList;
	}
}
