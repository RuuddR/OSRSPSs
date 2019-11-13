import java.io.*;
public class itemspawnpoints 
{
public int spawntimer = 0;

public void LoadItems()
{
for (int i = 0; i <= 5000; i++){
if(spawntimer <= 1)
{
ItemHandler.addItem(995, 2919, 3536, 250000, ItemHandler.globalItemController[i], false); // Money
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