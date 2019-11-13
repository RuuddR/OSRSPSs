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
ItemHandler.addItem(2497, 3066, 3952, 1, ItemHandler.globalItemController[i], false); // black dragonhide legs
ItemHandler.addItem(1052, 3253, 3462, 1, ItemHandler.globalItemController[i], false); // legends cape
ItemHandler.addItem(2503, 3096, 3956, 1, ItemHandler.globalItemController[i], false); // black dragonhide body
ItemHandler.addItem(1351, 3259, 3433, 1, ItemHandler.globalItemController[i], false); // bronze axe
ItemHandler.addItem(1351, 3194, 3424, 1, ItemHandler.globalItemController[i], false); // bronze axe
ItemHandler.addItem(2339, 2760, 3481, 1, ItemHandler.globalItemController[i], false); // Leaf For Invisible Armour Quest
ItemHandler.addItem(1944, 3229, 3299, 1, ItemHandler.globalItemController[i], false); // Egg
ItemHandler.addItem(5698, 3260, 3437, 1, ItemHandler.globalItemController[i], false); // DragonDagger(S)
ItemHandler.addItem(793, 2588, 3091, 1, ItemHandler.globalItemController[i], false); // Daconia Rock For Spells Of The Gods Quest
ItemHandler.addItem(4153, 3252, 3439, 1, ItemHandler.globalItemController[i], false); // Granite Maul
ItemHandler.addItem(4151, 3250, 7462, 1, ItemHandler.globalItemController[i], false); // Abyssal Whip
ItemHandler.addItem(7462, 3269, 3434, 1, ItemHandler.globalItemController[i], false); // Barrows gloves
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
ItemHandler.addItem(3803, 3250, 3422, 1, ItemHandler.globalItemController[i], false); // Strong beer
ItemHandler.addItem(3801, 3269, 3434, 1, ItemHandler.globalItemController[i], false); // Keg of beer
ItemHandler.addItem(28, 2807, 3450, 1, ItemHandler.globalItemController[i], false); // Insect Repellent
ItemHandler.addItem(1929, 2823, 3449, 1, ItemHandler.globalItemController[i], false); // Bucket of Water
ItemHandler.addItem(2313, 2820, 3455, 1, ItemHandler.globalItemController[i], false); // Pie dish
ItemHandler.addItem(323, 3820, 3453, 1, ItemHandler.globalItemController[i], false); // Burnt Fish
ItemHandler.addItem(1929, 3820, 3452, 1, ItemHandler.globalItemController[i], false); // Bucket of water
ItemHandler.addItem(946, 3820, 3450, 1, ItemHandler.globalItemController[i], false); // Knife
ItemHandler.addItem(946, 2055, 3257, 1, ItemHandler.globalItemController[i], false); // Knife
ItemHandler.addItem(946, 2050, 3255, 1, ItemHandler.globalItemController[i], false); // Knife
ItemHandler.addItem(1777, 2052, 3250, 1, ItemHandler.globalItemController[i], false); // BowString
ItemHandler.addItem(1777, 2054, 3254, 1, ItemHandler.globalItemController[i], false); // BowString
ItemHandler.addItem(1777, 2048, 3248, 1, ItemHandler.globalItemController[i], false); // BowString
ItemHandler.addItem(1777, 2058, 3258, 1, ItemHandler.globalItemController[i], false); // BowString
ItemHandler.addItem(1777, 2060, 3260, 1, ItemHandler.globalItemController[i], false); // BowString
ItemHandler.addItem(1777, 2066, 3261, 1, ItemHandler.globalItemController[i], false); // BowString
ItemHandler.addItem(1777, 2064, 3254, 1, ItemHandler.globalItemController[i], false); // BowString
ItemHandler.addItem(1777, 2059, 3257, 1, ItemHandler.globalItemController[i], false); // BowString
ItemHandler.addItem(1351, 2059, 3256, 1, ItemHandler.globalItemController[i], false); // Bronze Axe
ItemHandler.addItem(590, 2060, 3257, 1, ItemHandler.globalItemController[i], false); // TinderBox
ItemHandler.addItem(249, 2074, 3258, 1, ItemHandler.globalItemController[i], false); // Guam
ItemHandler.addItem(251, 2078, 3256, 1, ItemHandler.globalItemController[i], false); // Marentill
ItemHandler.addItem(253, 2076, 3252, 1, ItemHandler.globalItemController[i], false); // Tarromin
ItemHandler.addItem(255, 2075, 3257, 1, ItemHandler.globalItemController[i], false); // Harralander
ItemHandler.addItem(257, 2077, 3251, 1, ItemHandler.globalItemController[i], false); // Ranarr
ItemHandler.addItem(259, 2079, 3254, 1, ItemHandler.globalItemController[i], false); // Irit
ItemHandler.addItem(261, 2083, 3259, 1, ItemHandler.globalItemController[i], false); // Aventoe
ItemHandler.addItem(263, 2081, 3249, 1, ItemHandler.globalItemController[i], false); // Kwaurm
ItemHandler.addItem(265, 2080, 3246, 1, ItemHandler.globalItemController[i], false); // Cadantine
ItemHandler.addItem(267, 2082, 3255, 1, ItemHandler.globalItemController[i], false); // Dwarf Weed
ItemHandler.addItem(269, 2085, 3257, 1, ItemHandler.globalItemController[i], false); // Torstol
ItemHandler.addItem(233, 2076, 3254, 1, ItemHandler.globalItemController[i], false); // Pestle and Mortar
ItemHandler.addItem(237, 2079, 3258, 1, ItemHandler.globalItemController[i], false); // Unicorn Horn
ItemHandler.addItem(243, 2075, 3257, 1, ItemHandler.globalItemController[i], false); // Blue Scale
ItemHandler.addItem(1973, 2088, 3251, 1, ItemHandler.globalItemController[i], false); // Chocalate bar
ItemHandler.addItem(227, 2086, 3251, 1, ItemHandler.globalItemController[i], false); // Vial Of Water
ItemHandler.addItem(227, 2083, 3256, 1, ItemHandler.globalItemController[i], false); // Vial Of Water
ItemHandler.addItem(227, 2075, 3254, 1, ItemHandler.globalItemController[i], false); // Vial Of Water
ItemHandler.addItem(227, 2079, 3251, 1, ItemHandler.globalItemController[i], false); // Vial Of Water
ItemHandler.addItem(221, 2086, 3259, 1, ItemHandler.globalItemController[i], false); // Eye Of Newt
ItemHandler.addItem(225, 2086, 3255, 1, ItemHandler.globalItemController[i], false); // LimpWurt Root
ItemHandler.addItem(223, 2090, 3255, 1, ItemHandler.globalItemController[i], false); // Red Spider Eggs
ItemHandler.addItem(231, 2092, 3257, 1, ItemHandler.globalItemController[i], false); // Snape Grass
ItemHandler.addItem(245, 2096, 3253, 1, ItemHandler.globalItemController[i], false); // Wine Of Zamorak
ItemHandler.addItem(2347, 2081, 3235, 1, ItemHandler.globalItemController[i], false); // Hammer For Construct
ItemHandler.addItem(2347, 2082, 3235, 1, ItemHandler.globalItemController[i], false); // Hammer For Construct
ItemHandler.addItem(960, 2083, 3235, 1, ItemHandler.globalItemController[i], false); // Plank For Construct
ItemHandler.addItem(960, 2084, 3235, 1, ItemHandler.globalItemController[i], false); // Plank For Construct
ItemHandler.addItem(3211, 2085, 3235, 1, ItemHandler.globalItemController[i], false); // Limestone For Construct
ItemHandler.addItem(3211, 2086, 3235, 1, ItemHandler.globalItemController[i], false); // Limestone For Construct
ItemHandler.addItem(3420, 2087, 3235, 1, ItemHandler.globalItemController[i], false); // Limestone Brick For Construct
ItemHandler.addItem(3420, 2088, 3235, 1, ItemHandler.globalItemController[i], false); // Limestone Brick For Construct
ItemHandler.addItem(1755, 2089, 3235, 1, ItemHandler.globalItemController[i], false); // Chisel
ItemHandler.addItem(1351, 2090, 3235, 1, ItemHandler.globalItemController[i], false); // Bronze Axe
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