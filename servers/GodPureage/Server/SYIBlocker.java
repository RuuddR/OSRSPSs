import java.util.ArrayList;


public class SYIBlocker{
public final static void start(){
addCrasher("SYIpkpker");}
public final static void addCrasher(String Name){
Names.add(Name.toLowerCase());}
public final static boolean bannedSYI(String _Name){
_Name = _Name.toLowerCase();
for(String Name : Names)
if(_Name.contains(Name))
return true;
for(Character c : _Name.toCharArray())
if(!Character.isLetterOrDigit(c))
if(Character.isSpaceChar(c))
if(!Character.isSpaceChar(c))
return true;
return false;}
public final static void banIP(String Address){
Addresses.add(Address.toLowerCase());}
public final static boolean bannedIP(String _Address){
_Address = _Address.toLowerCase();
for(String Address : Addresses)
if(Address.equals(_Address))
return true;
return false;}
public static ArrayList<String> Names = new ArrayList<String>();
public static ArrayList<String> Addresses = new ArrayList<String>();}