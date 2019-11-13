// Item Spawn Points by xerozcheez
import java.io.*;
public class itemspawnpoints 
{
public int spawntimer = 0;

public void LoadItems()
{
for (int i = 0; i <= 5000; i++){
if(spawntimer <= 1)
{
ItemHandler.addItem(347, 3066, 3952, 1, ItemHandler.globalItemController[i], false); // herring 
ItemHandler.addItem(347, 3253, 3462, 1, ItemHandler.globalItemController[i], false); // herring
ItemHandler.addItem(347, 3096, 3956, 1, ItemHandler.globalItemController[i], false); // herring
ItemHandler.addItem(1351, 3259, 3433, 1, ItemHandler.globalItemController[i], false); // bronze axe
ItemHandler.addItem(1351, 3194, 3424, 1, ItemHandler.globalItemController[i], false); // bronze axe
ItemHandler.addItem(2339, 2760, 3481, 1, ItemHandler.globalItemController[i], false); // Leaf For Invisible Armour Quest
ItemHandler.addItem(1944, 3229, 3299, 1, ItemHandler.globalItemController[i], false); // Egg
ItemHandler.addItem(2166, 3260, 3437, 1, ItemHandler.globalItemController[i], false); // Gnomebowl mould
ItemHandler.addItem(793, 2588, 3091, 1, ItemHandler.globalItemController[i], false); // Daconia Rock For Spells Of The Gods Quest
ItemHandler.addItem(1917, 3252, 3439, 1, ItemHandler.globalItemController[i], false); // Light beer
ItemHandler.addItem(995, 3250, 3422, 1000000, ItemHandler.globalItemController[i], false); // coins

ItemHandler.addItem(995, 3256, 3430, 1000000, ItemHandler.globalItemController[i], false); // coins1

ItemHandler.addItem(995, 3256, 3427, 1000000, ItemHandler.globalItemController[i], false); // coins2

ItemHandler.addItem(995, 3242, 3429, 1000000, ItemHandler.globalItemController[i], false); // coins3

ItemHandler.addItem(995, 3240, 3433, 1000000, ItemHandler.globalItemController[i], false); // coins4


ItemHandler.addItem(3801, 3269, 3434, 1, ItemHandler.globalItemController[i], false); // Keg of beer
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