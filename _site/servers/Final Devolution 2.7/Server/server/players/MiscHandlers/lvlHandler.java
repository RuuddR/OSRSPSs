package server.players.MiscHandlers;

import server.*;
import server.npcs.*;
import server.util.*;
import server.items.*;
import server.world.*;
import server.players.MiscHandlers.*;
import server.players.*;

import java.io.*;
public class lvlHandler { //-killamess

public int GetCLAttack(int ItemID) {
if(ItemID == 4151){
return 70;
}
if(ItemID == 4587 || ItemID == 5698){
return 60;
}
if(ItemID == 4153){
return 50;
}
if(ItemID == 3101){
return 40;
}
return 1;
}

public int GetCLStrength(int ItemID) {
if(ItemID == 4153){
return 50;
}
return 1;
}

public int GetCLDefence(int ItemID) {
if(ItemID == 2904){
return 60;
}
if(ItemID == 3751 || ItemID == 3749 || ItemID == 7462){
return 45;
}
if(ItemID == 3101 || ItemID == 1079 || ItemID == 1127 || ItemID == 2503 || ItemID == 4131){
return 40;
}
if(ItemID == 6916 || ItemID == 6918 || ItemID == 6920 || ItemID == 6922 || ItemID == 6924){
return 25;
}
return 1;
}

public int GetCLRanged(int ItemID) {
if(ItemID == 2503 || ItemID == 2497 || ItemID == 2491){
return 70;
}
if(ItemID == 2581 || ItemID == 2577){
return 40;
}
return 1;
}

public int GetCLMagic(int ItemID) {
if(ItemID == 6889){
return 60;
}
if(ItemID == 6916 || ItemID == 6918 || ItemID == 6920 || ItemID == 6922 || ItemID == 6924){
return 50;
}
return 1;
}
}