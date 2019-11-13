import javax.swing.*;
import java.awt.*;
import java.lang.String;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.net.InetAddress;
import java.net.URL;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;
import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.plaf.metal.MetalLookAndFeel;
import sign.signlink;
import javax.sound.midi.*;
import java.util.List;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class Gui extends client
implements ActionListener, ItemListener, WindowListener
{
	private static FileOutputStream logFileOut;
	private static boolean isApplet = false;
	private MP3Player player;
	public int midiCount;
	public Sequencer sequencer;





	public static void main(String args[])
	{
		new Gui(args);

	}

	private Gui(String args[])
	{
		super();
		try
		{

			System.out.println("Have A Great Time!");
			Serverip = "";
			sign.signlink.startpriv(InetAddress.getByName(Serverip));
			initUI();
		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
	}


	public int test()
	{
		//return(JFrame.DO_NOTHING_ON_CLOSE);
		return (JFrame.EXIT_ON_CLOSE);
	}

	public void initUI()
	{
		try
		{
		
           			UIManager.setLookAndFeel("org.jvnet.substance.skin.SubstanceMagmaLookAndFeel");
            			JFrame.setDefaultLookAndFeelDecorated(true);
         			JDialog.setDefaultLookAndFeelDecorated(true);
            			JPopupMenu.setDefaultLightWeightPopupEnabled(false);
			JFrame.setDefaultLookAndFeelDecorated(true);
			JFrame.setDefaultLookAndFeelDecorated(true);
			JPopupMenu.setDefaultLightWeightPopupEnabled(false);

			sign.signlink.mainapp = this; // Dangerous reference?

			frame = new JFrame(frameTitle);
			frame.setLayout(new BorderLayout());

			frame.setResizable(false);
			frame.setDefaultCloseOperation(test());

			JPanel gamePanel = new JPanel();
			gamePanel.setLayout(new BorderLayout());

			gamePanel.add(this);
			gamePanel.setPreferredSize(new Dimension(765, 503));

			JMenuBar menuBar = new JMenuBar();

			JMenu fileMenu = new JMenu("File");
			JMenu ClientMenu = new JMenu("Forums");
			JMenu SitesMenu = new JMenu("Vote");
			JMenu musicmenu = new JMenu("Music");
			JButton shotMenu = new JButton("Screenshot");
			
			shotMenu.setActionCommand("Screenshot");
			shotMenu.addActionListener(this);



			String[] ClientButtons = new String[]
				{
					"Forums"
				};

			String[] musicButtons = new String[]
				{
					"Play", "Stop", "Next", "Previous"
				};

			String[] SitesButtons = new String[]
				{
					"Vote"
				};
			String[] mainButtons = new String[]
				{
					 "Server ip","Change Port","Item IDs", "NPC IDs", "Object IDs", "-", "Quit"
				};
			for (String name : mainButtons)
			{
				JMenuItem menuItem = new JMenuItem(name);
				if (name.equalsIgnoreCase("-"))
					fileMenu.addSeparator();
				else
				{
					menuItem.addActionListener(this);
					fileMenu.add(menuItem);
				}
			}
		



			for (String name : musicButtons)
						{
							JMenuItem menuItem = new JMenuItem(name);
							if (name.equalsIgnoreCase("-"))
								musicmenu.addSeparator();
							else
							{
								menuItem.addActionListener(this);
								musicmenu.add(menuItem);
							}
			}
for (String name : ClientButtons)
						{
							JMenuItem menuItem = new JMenuItem(name);
							if (name.equalsIgnoreCase("-"))
								ClientMenu.addSeparator();
							else
							{
								menuItem.addActionListener(this);
								ClientMenu.add(menuItem);
							}
			
	}
for (String name : SitesButtons)
						{
							JMenuItem menuItem = new JMenuItem(name);
							if (name.equalsIgnoreCase("-"))
								SitesMenu.addSeparator();
							else
							{
								menuItem.addActionListener(this);
								SitesMenu.add(menuItem);
							}
			
	}
			
			menuBar.add(fileMenu);
			menuBar.add(ClientMenu);
			menuBar.add(SitesMenu);
			menuBar.add(musicmenu);
			menuBar.add(shotMenu);
			



			frame.getContentPane().add(menuBar, BorderLayout.NORTH);
			frame.getContentPane().add(gamePanel, BorderLayout.CENTER);
			frame.pack();
			frame.setVisible(true);
			init();
			midiPlayer("Play");
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	public JDialog createFileChooserDialog(JFileChooser jfilechooser, String s, Container container)
	{
		JDialog jdialog = new JDialog(frame, s, true);
		jdialog.setDefaultCloseOperation(2);
		jdialog.add(jfilechooser);
		jdialog.pack();
		jdialog.setLocationRelativeTo(container);
		return jdialog;
	}
	public void itemStateChanged(ItemEvent itemevent)
	{
		String s = itemevent.paramString();
		s = s.substring(s.indexOf("item=") + 5);
		int i = itemevent.getStateChange();
		if (s != null)
		{

		}
	}
	public static synchronized void log(Object object)
	{
		System.out.println(object.toString());
	}
	public static void chkDir(String fileName)
	{
		try
		{
			File f = new File(fileName);
			if (!f.exists())
				f.mkdir();
		}
		catch (Exception e)
		{
			log((new StringBuilder()).append("Error making file: ").append(e).toString());
		}
	}
	private void midiPlayer(String command)
	{
		String dir = "./music/";
		chkDir(dir);
		if (player != null && player.isRunning())
		{
			player.close();
		}
		if (sequencer != null && sequencer.isRunning())
		{
			sequencer.stop();
			sequencer.close();
		}
		if (command.equals("Next"))
		{
			midiCount++;
		}
		else
			if (command.equals("Previous"))
			{
				midiCount--;
			}
		if (!command.equals("Stop"))
		{
			File index = new File(dir);
			String fileList[] = index.list();
			if (fileList.length > 0)
			{
				if (midiCount > fileList.length - 1)
				{
					midiCount = 0;
				}
				if (midiCount < 0)
				{
					midiCount = fileList.length - 1;
				}
				if (fileList[midiCount].endsWith(".mp3"))
				{
					log((new StringBuilder()).append("Playing ").append(fileList[midiCount]).toString());
					player = new MP3Player((new StringBuilder()).append(dir).append(fileList[midiCount]).toString(), true);
				}
				else
					if (fileList[midiCount].endsWith(".mid"))
					{
						log((new StringBuilder()).append("Playing ").append(fileList[midiCount]).toString());
						try
						{
							File midiFile = new File((new StringBuilder()).append(dir).append(fileList[midiCount]).toString());
							sequencer = MidiSystem.getSequencer();
							sequencer.setSequence(MidiSystem.getSequence(midiFile));
							sequencer.setLoopCount(-1);
							sequencer.open();
							sequencer.start();
						}
						catch (MidiUnavailableException mue)
						{
							log("Midi device unavailable!");
						}
						catch (InvalidMidiDataException imde)
						{
							log("Invalid Midi data!");
						}
						catch (IOException ioe)
						{
							log("I/O Error!");
						}
					}
			}
			else
			{
				log("no midi or mp3 files in folder");
			}
		}
	}

	public void takeScreenshot(boolean flag)
	{
		BufferedImage bufferedimage;
		try
		{
			Robot robot = new Robot();
			Point point = getLocationOnScreen();
			Rectangle rectangle = new Rectangle(point.x, point.y, getWidth(), getHeight());
			bufferedimage = robot.createScreenCapture(rectangle);
		}
		catch (Throwable throwable)
		{
			JOptionPane.showMessageDialog(frame, "An error occured while trying to create a screenshot!", "Screenshot Error", 0);
			return;
		}
		String s = null;
		try
		{
			s = getNearestScreenshotFilename();
		}
		catch (IOException ioexception)
		{
			if (flag)
			{
				JOptionPane.showMessageDialog(frame, "A screenshot directory does not exist, and could not be created!", "No Screenshot Directory", 0);
				return;
			}
		}
		if (s == null && flag)
		{
			JOptionPane.showMessageDialog(frame, "There are too many screenshots in the screenshot directory!\n" + "Delete some screen\n" + "shots and try again.", "Screenshot Directory Full", 0);
			return;
		}
		if (!flag)
		{
			final JFileChooser fileChooser = new JFileChooser();
			final JDialog fileDialog = createFileChooserDialog(fileChooser, "Save Screenshot", this);
			final BufferedImage si = bufferedimage;
			JFileChooser _tmp = fileChooser;
			fileChooser.setFileSelectionMode(0);
			fileChooser.addChoosableFileFilter(new imageFileFilter());
			fileChooser.setCurrentDirectory(new File("./screenshots/"));
			fileChooser.setSelectedFile(new File(s));
			JFileChooser _tmp1 = fileChooser;
			fileChooser.setDialogType(1);
			fileChooser.addActionListener(new ActionListener()
			{

				public void actionPerformed(ActionEvent actionevent)
				{
					String s1 = actionevent.getActionCommand();
					if (s1.equals("ApproveSelection"))
					{
						File file = fileChooser.getSelectedFile();
						if (file != null && file.isFile())
						{
							int i = JOptionPane.showConfirmDialog(frame, (new StringBuilder()).append(file.getAbsolutePath()).append(" already exists.\n" + "Do you want to replace it?").toString(), "Save Screenshot", 2);
							if (i != 0)
							{
								return;
							}
						}
						try
						{
							ImageIO.write(si, "png", file);
						}
						catch (IOException ioexception2)
						{
							JOptionPane.showMessageDialog(frame, "An error occured while trying to save the screenshot!\n" + "Please make sure you have\n" + " write access to the screenshot directory.", "Screenshot Error", 0);
						}
						fileDialog.dispose();
					}
					else
						if (s1.equals("CancelSelection"))
						{
							fileDialog.dispose();
						}
				}
				{

				}
			});
			fileDialog.setVisible(true);
		}
		else
		{
			try
			{
				ImageIO.write(bufferedimage, "png", new File((new StringBuilder()).append("./screenshots/").append(s).toString()));
				System.out.println("You Take A Screenshot.");
			}
			catch (IOException ioexception1)
			{
				JOptionPane.showMessageDialog(frame, "An error occured while trying to save the screenshot!\n" + "Please make sure you have\n" + " write access to the screenshot directory.", "Screenshot Error", 0);
			}
		}
	}

	public static String getNearestScreenshotFilename()
        throws IOException
	{
		File file = new File("./screenshots");
		int i = 0;
		do
		{
			String s = "evolved .png";
			if (i < 10)
			{
				s = s.replaceFirst(" ", (new StringBuilder()).append(" 000").append(i).toString());
			}
			else
				if (i < 100)
				{
					s = s.replaceFirst(" ", (new StringBuilder()).append(" 00").append(i).toString());
				}
				else
					if (i < 1000)
					{
						s = s.replaceFirst(" ", (new StringBuilder()).append(" 0").append(i).toString());
					}
					else
						if (i < 10000)
						{
							s = s.replaceFirst(" ", (new StringBuilder()).append(" ").append(i).toString());
						}
			if ((new File(file, s)).isFile())
			{
				i++;
			}
			else
			{
				return s;
			}
		} while (i < 10000);
		return null;
	}


	class imageFileFilter extends FileFilter
	{

		imageFileFilter()
		{
		}

		public boolean accept(File file)
		{
			String s = file.getName();
			return file.isDirectory() || s.toLowerCase().endsWith(".png") && s.indexOf("$") == -1;
		}

		public String getDescription()
		{
			return "PNG (*.png)";
		}
	}

	public URL getCodeBase()
	{
		try
		{
			System.out.println("Connected to server.");
			sign.signlink.startpriv(InetAddress.getByName(Serverip));
			return new URL("http://www.xlinescape.net84.net/Updateserver/updater.hex");


		}
		catch (Exception e)
		{
			return super.getCodeBase();
		}
	}


	public URL getDocumentBase()
	{
		return getCodeBase();
	}

	public void loadError(String s)
	{
		System.out.println("loadError: " + s);
	}
	public String getParameter(String key)
	{
		if (key.equals("nodeid"))
			return "10";
		else if (key.equals("portoff"))
			return "0";
		else if (key.equals("lowmem"))
			return "1";
		else if (key.equals("free"))
			return "0";
		else
			return "";
	}

	public void displayWorldSelect()
	{
		try
		{
			String s1 = JOptionPane.showInputDialog(this, (new StringBuilder()).append("Current server: " + server), "Enter Server", 3);
			if (s1 == null)
			{
				log("Canceled");
			}
			else
				if (s1.equalsIgnoreCase(""))
				{
					log("Sorry, have to have some input");
				}
				else
				{
					log((new StringBuilder()).append("Set world to: ").append(s1).toString());
					server = s1;
				}
		}
		catch (Exception e)
		{
			log((new StringBuilder()).append("You must enter a numeric value!: ").append(e).toString());

		}
	}
	public void actionPerformed(ActionEvent evt)
	{
		String cmd = evt.getActionCommand();
		if (cmd != null)
		{
			if (cmd.equalsIgnoreCase("Quit"))
			{
				int i1;
				if ((i1 = JOptionPane.showConfirmDialog(this, "Are you sure you want to close the Client?")) == 0)
					System.exit(0);
				return;
			}
			if (cmd.equalsIgnoreCase("Next"))
			{
				midiPlayer("Next");
			}
			if (cmd.equalsIgnoreCase("Previous"))
			{
				midiPlayer("Previous");
			}
			if (cmd.equalsIgnoreCase("Play"))
			{
				midiPlayer("Play");
			}
			if (cmd.equalsIgnoreCase("Stop"))
			{
				midiPlayer("Stop");
			}
			if (cmd.equalsIgnoreCase("Vote"))
			{
				launchURL("http://www.vote4paradise.tk");
				log("Loading Voting Page...");
			}


			if (cmd.equalsIgnoreCase("Save account"))
			{
				System.out.println("Saving file");
			}
			if (cmd.equalsIgnoreCase("auto talker"))
			{

				settalk = JOptionPane.showInputDialog(null, "Enter Your text", "Enter what you want say", 1);
				if (settalk != null && settalk != "")
				{

					loop = JOptionPane.showInputDialog(null, "Enter a Number", "How many times you want loop", 1);
					if (loop != null && loop != "")
					{

						String h = loop;
						int theloop = Integer.parseInt(h);

					}
				}
			}


			if (cmd.equalsIgnoreCase("Forums"))
			{
				launchURL("http://www.paradise317.proboards.com");
				log("Loading Forums!");
			}
			if (cmd.equalsIgnoreCase("Add your own Music"))
			{

				JOptionPane.showMessageDialog(
				   null, "To add your own music go into the user folder \nThen into the music folder now you can drop and drag your own  \nMp3 music files into here once you done that  \nReload the client and press next and bang away to your tunes =0");

			}
			if (cmd.equalsIgnoreCase("Change Port"))
			{
				String port = JOptionPane.showInputDialog(this, "Server Port:", "");
				if (port == null)
				{
					JOptionPane.showMessageDialog(this, "You must specify a port.", "No port specified.", JOptionPane.WARNING_MESSAGE);
					return;
				}
				int Port = Integer.parseInt(port);
				super.port = port;
			}
			if (cmd.equalsIgnoreCase("Find your screenshots"))
			{

				JOptionPane.showMessageDialog(
				   null, "Click the screenshot button on the menu bar \nNext go into the user folder and then into the screenshot folder all screen shots  \nYou take like this will be saved here");

			}

			if (cmd.equalsIgnoreCase("Server IP"))
			{
				displayWorldSelect();
	}
	}

			if (cmd.equals("Item IDs"))
			{
				if (isApplet)
				{
					checkIDs();
					new Xml$((new StringBuilder()).append(findcachedir()).append("Files/all_IDs/Items.xml").toString());
				}
				else
				{
					new Xml$("evolved_cache_32/all_IDs/Items.xml");
				}
			}
			if (cmd.equals("NPC IDs"))
			{
				if (isApplet)
				{
					checkIDs();
					new Xml$((new StringBuilder()).append(findcachedir()).append("Files/all_IDs/NPCs.xml").toString());
				}
				else
				{
					new Xml$("evolved_cache_32/all_IDs/NPCs.xml");
				}
			}
			if (cmd.equals("New Item IDs"))
			{
				if (isApplet)
				{
					checkIDs();
					new Xml$((new StringBuilder()).append(findcachedir()).append("Files/all_IDs/NewItems.xml").toString());
				}
				else
				{
					new Xml$("evolved_cache_32/all_IDs/NewItems.xml");
				}
			}
			if (cmd.equals("Object IDs"))
			{
				if (isApplet)
				{
					checkIDs();
					new Xml$((new StringBuilder()).append(findcachedir()).append("Files/all_IDs/Objects.xml").toString());
				}
				else
				{
					new Xml$("evolved_cache_32/all_IDs/Objects.xml");
				}
			}

		
		if (cmd.equalsIgnoreCase("Screenshot"))
		{
			takeScreenshot(true);
		}


	}

	private void checkIDs()
	{
		/*
        File exist = new File((new StringBuilder()).append(findcachedir()).append("/all_IDs/").toString());
        if(!exist.exists())
        {
            new Update("http://www.moparscape.org/cache/all_IDs.zip", "all_IDs.zip", findcachedir(), true, false);
        }
*/
	}
	private void newIP()
	{
		try
		{
			String sServer = JOptionPane.showInputDialog(this, "Server IP #:");
			server = sServer;
			sign.signlink.startpriv(InetAddress.getByName(sServer));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public static final String findcachedir()
	{
		try
		{
			String s = "./";
			String s1 = "cache";
			File file = new File(s1 + s);
			if (file.exists() || file.mkdir())
				return s1 + s + "/";
		}
		catch (Exception _ex) { }

		return null;
	}

	public void launchURL(String s)
	{
		String s1 = System.getProperty("os.name");
		try
		{

			if (s1.startsWith("Windows"))
			{
				Runtime.getRuntime().exec((new StringBuilder()).append("rundll32 url.dll,FileProtocolHandler ").append(s).toString());
			}
			else
			{
				String as[] = {
                    "firefox", "opera", "konqueror", "epiphany", "mozilla", "netscape"
                };
				String s2 = null;
				for (int i = 0; i < as.length && s2 == null; i++)
					if (Runtime.getRuntime().exec(new String[] {
    "which", as[i]
}).waitFor() == 0)
						s2 = as[i];

				if (s2 == null)
					throw new Exception("Could not find web browser");
				Runtime.getRuntime().exec(new String[] {
                    s2, s
                });
			}
		}
		catch (Exception exception)
		{
			System.out.println("An error occured while trying to open the web browser!\n");
		}
	}

	public static String detail = null;
	int world = 1;
	public static String loop = null;
	public static String settalk = null;
	public static String setchateffects = null;
	public static String setname = null;
	public static String setchat = null;
	public JFrame frame;
	public String Serverip;
	public static double version = 6.2;

	public static String frameTitle = "Paradise Client.";
}