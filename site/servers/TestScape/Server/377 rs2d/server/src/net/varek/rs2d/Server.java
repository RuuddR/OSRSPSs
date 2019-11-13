package net.varek.rs2d;

import net.varek.rs2d.net.ConnectionHandler;
import java.net.InetSocketAddress;
import java.util.Timer;
import net.varek.rs2d.model.World;
import org.apache.mina.common.IoAcceptor;
import org.apache.mina.integration.jmx.IoServiceManager;
import org.apache.mina.transport.socket.nio.SocketAcceptor;
import org.apache.mina.transport.socket.nio.SocketAcceptorConfig;
import net.varek.rs2d.net.ConnectionThrottleFilter;
/*The entry point for RSC server.*/

public class Server {
/*The game engine*/
    private GameEngine engine;
    private Timer t;
    private IoAcceptor acceptor;
    private IoServiceManager serviceManager;
    private ConnectionHandler connectionHandler;
    private ConnectionThrottleFilter throttleFilter;
	public Server() {
	    if(World.getWorld() == null) throw new IllegalArgumentException("Cannot start server until World has been loaded.");
		engine = new GameEngine();
		acceptor = new SocketAcceptor();
		connectionHandler = new ConnectionHandler(engine);
	    }
	public void start(int port) throws Exception {
	    Server.newThread(engine, "GameEngine");
	    SocketAcceptorConfig sac = new SocketAcceptorConfig();
	    sac.getSessionConfig().setTcpNoDelay(false);
	    sac.setReuseAddress(true);
	    sac.setBacklog(100);

	    //serviceManager = new IoServiceManager( acceptor );
	    //serviceManager.startCollectingStats(5000);
	    //final SessionLoggingFilter logFilter = new SessionLoggingFilter();
	    //sac.getFilterChain().addFirst("loggingFilter", logFilter);

	    throttleFilter = new ConnectionThrottleFilter(0);
	    sac.getFilterChain().addFirst("throttleFilter", throttleFilter);
	    acceptor.bind(new InetSocketAddress(port), connectionHandler, sac);
	}
	public GameEngine getGameEngine() {
	    return engine;
	}
	public static void newThread(Runnable r, String name) {
	    Thread t = new Thread(group, r, name);
	    t.start();
	}
	public IoServiceManager getServiceManager() {
	    return serviceManager;
	}
	public ConnectionThrottleFilter getThrottleFilter() {
	    return throttleFilter;
	}
	public void unbindPorts() throws Exception {
	    Thread.sleep(200);
	    acceptor.unbindAll();
	    Thread.sleep(300);
	}
    private static ThreadGroup group;
}