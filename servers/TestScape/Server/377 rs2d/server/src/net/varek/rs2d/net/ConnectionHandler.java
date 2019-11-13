package net.varek.rs2d.net;

import net.varek.rs2d.*;
import org.apache.mina.common.IdleStatus;
import org.apache.mina.common.IoHandler;
import org.apache.mina.common.IoSession;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import net.varek.rs2d.model.Player;
import net.varek.rs2d.model.World;
import net.varek.rs2d.net.Packet;
import net.varek.rs2d.net.PacketQueue;
import net.varek.rs2d.net.codec.LoginCodecFactory;
import net.varek.rs2d.net.codec.RS2CodecFactory;
import net.varek.rs2d.util.Logger;

/**
 * Handles the protocol events fired from MINA.
 */
public class ConnectionHandler implements IoHandler {
	/**
	 * The game engine
	 */
	private GameEngine engine;
	/**
	 * A reference to the game engine's packet queue
	 */
	private PacketQueue packets;

	/**
	 * Creates a new connection handler for the given engine.
	 *
	 * @param engine The engine in use
	 */
	public ConnectionHandler(GameEngine engine) {
		this.engine = engine;
		packets = engine.getPacketQueue();
	}

	/**
	 * Invoked whenever an exception is thrown by MINA or this IoHandler.
	 *
	 * @param session The associated session
	 * @param cause   The exception thrown
	 */
	public void exceptionCaught(IoSession session, Throwable cause) {
		Player p = (Player)session.getAttachment();
		Logger.log("exception caught: "+session);
		Logger.err(cause);
	}

	/**
	 * Invoked whenever a packet is ready to be added to the queue.
	 *
	 * @param session The IO session on which the packet was received
	 * @param message The packet
	 */
	public void messageReceived(IoSession session, Object message) {
		Packet p = (Packet) message;
		packets.add(p);
	}

	/**
	 * Invoked whenever a packet is sent.
	 *
	 * @param session The associated session
	 * @param message The packet sent
	 */
	public void messageSent(IoSession session, Object message) {
	}

	public void sessionClosed(IoSession session) {
		Player p = (Player)session.getAttachment();
		p.setLoggedIn(false);
	}

	/**
	 * Invoked whenever an IO session is created.
	 *
	 * @param session The session opened
	 */
	public void sessionCreated(IoSession session) {
		session.getFilterChain().addLast("protocolFilter", new ProtocolCodecFilter(new LoginCodecFactory()));
	}

	/**
	 * Invoked when the idle status of a session changes.
	 *
	 * @param session The session in question
	 * @param status  The new idle status
	 */
	public void sessionIdle(IoSession session, IdleStatus status) {
		Player p = (Player)session.getAttachment();
		session.close();
	}

	/**
	 * Invoked when a new session is opened.
	 *
	 * @param session The session opened
	 */
	public void sessionOpened(IoSession session) {
		Player p = new Player(session);
		session.setAttachment(p);
		session.setIdleTime(IdleStatus.READER_IDLE, 5);
		session.setIdleTime(IdleStatus.WRITER_IDLE, 5);
	}
}
