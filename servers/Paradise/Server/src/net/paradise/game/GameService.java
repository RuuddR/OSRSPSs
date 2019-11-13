package net.paradise.game;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import net.paradise.game.item.ItemHandler;
import net.paradise.game.npc.NPCHandler;
import net.paradise.game.player.PlayerHandler;

/**
 * 
 * 
 * @author Nouish <nouish.no@gmail.com>
 */
public class GameService implements Runnable
{

	/**
	 * 
	 */
	private final ScheduledExecutorService scheduler = Executors
			.newSingleThreadScheduledExecutor();

	/**
	 * 
	 */
	private final List<Processable> processes = new ArrayList<Processable>();

	/**
	 * 
	 */
	private int pulseCount = 0;

	/**
	 * 
	 */
	public void start()
	{
		processes.add(new PlayerHandler());
		processes.add(new NPCHandler());
		processes.add(new ItemHandler());
		processes.add(new ShopHandler());
		processes.add(new ObjectHandler());

		scheduler.scheduleAtFixedRate(this, 600, 600, TimeUnit.MILLISECONDS);
	}

	@Override
	public void run()
	{
		pulseCount = (pulseCount + 1) % 250;

		/* Process all the processable handlers. */
		for (Processable processable : processes)
		{
			try
			{
				processable.process();
			}
			catch (Exception e)
			{
				System.err.println("Exception in processable: " + processable.getClass().getSimpleName() + ":");
				System.err.println(e.getMessage());
				e.printStackTrace();
			}
		}

		/*
		 * Hint at garbage collection and run finalization every time 250 game
		 * ticks have passed. This is equal to approximately 2.5 minutes.
		 */
		if (pulseCount == 0)
		{
			System.gc();
			System.runFinalization();
		}
	}

}