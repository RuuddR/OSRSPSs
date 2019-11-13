import java.io.*;

public class itemspawnpoints 
{

	public int spawntimer = 0;

	public void LoadItems()
	{
		for (int i = 0; i <= 5000; i++)
		{
			if(spawntimer <= 1)
			{
				ItemHandler.addItem(347, 3066, 3952, 1, ItemHandler.globalItemController[i], false); // herring 
				ItemHandler.addItem(347, 3253, 3462, 1, ItemHandler.globalItemController[i], false); // herring
				ItemHandler.addItem(347, 3096, 3956, 1, ItemHandler.globalItemController[i], false); // herring
				ItemHandler.addItem(1351, 3259, 3433, 1, ItemHandler.globalItemController[i], false); // bronze axe
				ItemHandler.addItem(1351, 3194, 3424, 1, ItemHandler.globalItemController[i], false); // bronze axe
				ItemHandler.addItem(2339, 2760, 3481, 1, ItemHandler.globalItemController[i], false); // Leaf For Invisible Armour Quest
				ItemHandler.addItem(1944, 3229, 3299, 1, ItemHandler.globalItemController[i], false); // Egg
				spawntimer = 100;
			}
		}
	}

	public void process()
	{
		LoadItems();
		spawntimer -= 1;
	}
}