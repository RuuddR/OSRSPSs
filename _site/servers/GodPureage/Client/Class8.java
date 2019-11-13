import sign.signlink;
import java.io.*;


final class Class8 {



public static final void method191(int i) {
aClass12_159 = null;
aClass12_158 = null;
anIntArray195 = null;
if(i >= 0)
aBoolean187 = !aBoolean187;
aClass8Array172 = null;
aClass30_Sub2_Sub2_183 = null;
}


public final boolean method192(int i, int j) {
if(i != -2836)
aBoolean186 = !aBoolean186;
int k = anInt175;
int l = anInt166;
if(j == 1)
{
k = anInt197;
l = anInt173;
}
if(k == -1)
return true;
boolean flag = true;
if(!Class30_Sub2_Sub4_Sub6.method463(k))
flag = false;
if(l != -1 && !Class30_Sub2_Sub4_Sub6.method463(l))
flag = false;
return flag;
}

public static void dumpCfg() {
boolean delete = (new File("item.cfg")).delete();
for(int i = 0; i < 9200; i++) {
Class8 class8 = method198(i);
BufferedWriter bw = null;
String des = "";
if(class8.aByteArray178 != null)
des = new String(class8.aByteArray178);
else
des = "Its a "+class8.aString170;
try {
BufferedWriter bufferedwriter;
bufferedwriter = null;
bufferedwriter = new BufferedWriter(new FileWriter("item.cfg", true));
bufferedwriter.write((new StringBuilder()).append("item = ").append(i).append("	").append(class8.aString170).append("	").append(des).append("	").append(class8.anInt155).append("	").append(class8.anInt155).append("	").append(class8.anInt155).append("	").append("0").append("	").append("0").append("	").append("0").append("	").append("0").append("	").append("0").append("	").append("0").append("	").append("0").append("	").append("0").append("	").append("0").append("	").append("0").append("	").append("0").append("	").append("0").toString());
bufferedwriter.newLine();
bufferedwriter.flush();
} catch(Exception e) {
}
}
}
public static void dumpItemsList() {
for(int i = 0; i < 9200; i++) {
Class8 class8 = method198(i);
BufferedWriter bw = null;
try {
bw = new BufferedWriter(new FileWriter("ItemList.txt", true));
if(class8.aString170 != null) {
bw.write("ID: "+i+"\t\tName: "+class8.aString170);
bw.newLine();
bw.flush();
bw.close();
}
} catch (IOException ioe2) {
}
}
}
public static void dumpNewItems() {
for(int i = 9000; i < 9200; i++) {
Class8 class8 = method198(i);
BufferedWriter bw = null;
try {
bw = new BufferedWriter(new FileWriter("Item Dump.txt", true));
if(class8.aString170 != null) {
bw.write("<item members='true'  name='" + class8.aString170 + "'  type='"+i+"'> </item>");
bw.newLine();
bw.flush();
bw.close();
}
} catch (IOException ioe2) {
}
}
}
public static void dumpItems() {
for(int i = 0; i < 9050; i++) {
Class8 class8 = method198(i);
BufferedWriter bw = null;

try {
bw = new BufferedWriter(new FileWriter("itemdump.txt", true));
bw.write("====================");
bw.newLine();
bw.write("Item Name: "+class8.aString170);
bw.newLine();
bw.write("Item ID: "+i);
bw.newLine();
if(class8.anIntArray156 != null) {
for(int i2 = 0; i2 < class8.anIntArray156.length; i2++) {
if(i2 == 0) {
bw.write("Original model colors: ");
}
if(i2 != class8.anIntArray156.length - 1) {
bw.write(""+class8.anIntArray156[i2]+", ");
} else {
bw.write(""+class8.anIntArray156[i2]);
bw.newLine();
}
}
}
if(class8.anIntArray160 != null) {
for(int i2 = 0; i2 < class8.anIntArray160.length; i2++) {
if(i2 == 0) {
bw.write("Changed model colors: ");
}
if(i2 != class8.anIntArray160.length - 1) {
bw.write(""+class8.anIntArray160[i2]+", ");
} else {
bw.write(""+class8.anIntArray160[i2]);
bw.newLine();
}
}
}
bw.write("anInt174: "+class8.anInt174);
bw.newLine();
bw.write("anInt181: "+class8.anInt181);
bw.newLine();
bw.write("anInt190: "+class8.anInt190);
bw.newLine();
bw.write("anInt198: "+class8.anInt198);
bw.newLine();
bw.write("anInt204: "+class8.anInt204);
bw.newLine();
bw.write("anInt169: "+class8.anInt169);
bw.newLine();
bw.write("anInt194: "+class8.anInt194);
bw.newLine();
bw.write("anInt165: "+class8.anInt165);
bw.newLine();
bw.write("anInt200: "+class8.anInt200);
bw.newLine();
bw.write("anInt188: "+class8.anInt188);
bw.newLine();
bw.write("anInt164: "+class8.anInt164);
bw.newLine();
bw.write("anInt175: "+class8.anInt175);
bw.newLine();
bw.write("anInt197: "+class8.anInt197);
bw.newLine();
bw.flush();
} catch (IOException ioe) {
ioe.printStackTrace();
} finally {
if (bw != null) try {
bw.close();
} catch (IOException ioe2) {
}
}
}
}

public static final void method193(Class44 class44) {
aClass30_Sub2_Sub2_183 = new Class30_Sub2_Sub2(class44.method571("obj.dat", null), 891);
Class30_Sub2_Sub2 class30_sub2_sub2 = new Class30_Sub2_Sub2(class44.method571("obj.idx", null), 891);
anInt203 = class30_sub2_sub2.method410();
anIntArray195 = new int[anInt203+400000];
int i = 2;
for(int j = 0; j < anInt203; j++)
{
anIntArray195[j] = i;
i += class30_sub2_sub2.method410();
}

aClass8Array172 = new Class8[10];
for(int k = 0; k < 10; k++)
aClass8Array172[k] = new Class8();
}

public final Class30_Sub2_Sub4_Sub6 method194(int i, int j) {
while(i >= 0)
aBoolean186 = !aBoolean186;
int k = anInt175;
int l = anInt166;
if(j == 1) {
k = anInt197;
l = anInt173;
}
if(k == -1)
return null;
Class30_Sub2_Sub4_Sub6 class30_sub2_sub4_sub6 = Class30_Sub2_Sub4_Sub6.method462(anInt171, k);
if(l != -1) {
Class30_Sub2_Sub4_Sub6 class30_sub2_sub4_sub6_1 = Class30_Sub2_Sub4_Sub6.method462(anInt171, l);
Class30_Sub2_Sub4_Sub6 aclass30_sub2_sub4_sub6[] = {
class30_sub2_sub4_sub6, class30_sub2_sub4_sub6_1
};
class30_sub2_sub4_sub6 = new Class30_Sub2_Sub4_Sub6(2, aclass30_sub2_sub4_sub6, -38);
}
if(anIntArray156 != null) {
for(int i1 = 0; i1 < anIntArray156.length; i1++)
class30_sub2_sub4_sub6.method476(anIntArray156[i1], anIntArray160[i1]);

}
return class30_sub2_sub4_sub6;
}

public final boolean method195(int i, int j) {
if(i != 40903)
aBoolean206 = !aBoolean206;
int k = anInt165;
int l = anInt188;
int i1 = anInt185;
if(j == 1) {
k = anInt200;
l = anInt164;
i1 = anInt162;
}
if(k == -1)
return true;
boolean flag = true;
if(!Class30_Sub2_Sub4_Sub6.method463(k))
flag = false;
if(l != -1 && !Class30_Sub2_Sub4_Sub6.method463(l))
flag = false;
if(i1 != -1 && !Class30_Sub2_Sub4_Sub6.method463(i1))
flag = false;
return flag;
}

public final Class30_Sub2_Sub4_Sub6 method196(boolean flag, int i) {
if(flag)
throw new NullPointerException();
int j = anInt165;
int k = anInt188;
int l = anInt185;
if(i == 1) {
j = anInt200;
k = anInt164;
l = anInt162;
}
if(j == -1)
return null;
Class30_Sub2_Sub4_Sub6 class30_sub2_sub4_sub6 = Class30_Sub2_Sub4_Sub6.method462(anInt171, j);
if(k != -1)
if(l != -1) {
Class30_Sub2_Sub4_Sub6 class30_sub2_sub4_sub6_1 = Class30_Sub2_Sub4_Sub6.method462(anInt171, k);
Class30_Sub2_Sub4_Sub6 class30_sub2_sub4_sub6_3 = Class30_Sub2_Sub4_Sub6.method462(anInt171, l);
Class30_Sub2_Sub4_Sub6 aclass30_sub2_sub4_sub6_1[] = {
class30_sub2_sub4_sub6, class30_sub2_sub4_sub6_1, class30_sub2_sub4_sub6_3
};
class30_sub2_sub4_sub6 = new Class30_Sub2_Sub4_Sub6(3, aclass30_sub2_sub4_sub6_1, -38);
} else {
Class30_Sub2_Sub4_Sub6 class30_sub2_sub4_sub6_2 = Class30_Sub2_Sub4_Sub6.method462(anInt171, k);
Class30_Sub2_Sub4_Sub6 aclass30_sub2_sub4_sub6[] = {
class30_sub2_sub4_sub6, class30_sub2_sub4_sub6_2
};
class30_sub2_sub4_sub6 = new Class30_Sub2_Sub4_Sub6(2, aclass30_sub2_sub4_sub6, -38);
}
if(i == 0 && aByte205 != 0)
class30_sub2_sub4_sub6.method475(0, aByte205, 16384, 0);
if(i == 1 && aByte154 != 0)
class30_sub2_sub4_sub6.method475(0, aByte154, 16384, 0);
if(anIntArray156 != null) {
for(int i1 = 0; i1 < anIntArray156.length; i1++)
class30_sub2_sub4_sub6.method476(anIntArray156[i1], anIntArray160[i1]);

}
return class30_sub2_sub4_sub6;
}

public final void method197()
{
anInt174 = 0;
aString170 = null;
aByteArray178 = null;
anIntArray156 = null;
anIntArray160 = null;
anInt181 = 12000;
anInt190 = 0;
anInt198 = 0;
anInt204 = 0;
anInt169 = 0;
anInt194 = 0;
anInt199 = -1;
aBoolean176 = false;
anInt155 = 1;
aBoolean161 = false;
aStringArray168 = null;
aStringArray189 = null;
anInt165 = -1;
anInt188 = -1;
aByte205 = 0;
anInt200 = -1;
anInt164 = -1;
aByte154 = 0;
anInt185 = -1;
anInt162 = -1;
anInt175 = -1;
anInt166 = -1;
anInt197 = -1;
anInt173 = -1;
anIntArray193 = null;
anIntArray201 = null;
anInt179 = -1;
anInt163 = -1;
anInt167 = 128;
anInt192 = 128;
anInt191 = 128;
anInt196 = 0;
anInt184 = 0;
anInt202 = 0;
}

public static final Class8 method198(int i)
{
for(int j = 0; j < 10; j++)
if(aClass8Array172[j].anInt157 == i)
return aClass8Array172[j];

anInt180 = (anInt180 + 1) % 10;
Class8 class8 = aClass8Array172[anInt180];
aClass30_Sub2_Sub2_183.anInt1406 = anIntArray195[i];
class8.anInt157 = i;
class8.method197();
class8.method203(true, aClass30_Sub2_Sub2_183);

if(i == 14539)
        {
            class8.aStringArray189 = new String[5];
            class8.aStringArray189[1] = "Wield";
            class8.anIntArray156 = new int[6];
            class8.anIntArray160 = new int[6];
            class8.anIntArray156[0] = 22168;
            class8.anIntArray160[0] = -21846;
            class8.anIntArray156[1] = 8070;
            class8.anIntArray160[1] = -24771;
            class8.anIntArray156[2] = 24082;
            class8.anIntArray160[2] = -24046;
            class8.anIntArray156[3] = 22156;
            class8.anIntArray160[3] = -21876;
            class8.anIntArray156[4] = 920;
            class8.anIntArray160[4] = -25788;
            class8.anIntArray156[5] = 912;
            class8.anIntArray160[5] = -27983;
            class8.anInt174 = 10119;
            class8.anInt169 = -1;
            class8.anInt181 = 1827;
            class8.anInt190 = 444;
            class8.anInt198 = 1259;
            class8.anInt194 = -1;
            class8.anInt204 = 2047;
            class8.anInt165 = 10120;
            class8.anInt200 = 10121;
            class8.anInt164 = -1;
            class8.anInt188 = -1;
            class8.aBoolean176 = false;
            class8.aString170 = "Black ele' legs";
            class8.aByteArray178 = "A well made elegant bottom.".getBytes();
        }
	if(i == 13634) {
	class8.aStringArray189 = new String[5];
	class8.aStringArray189[1] = "Wear";
	class8.anInt174 = 14041; //Inv & Ground
	class8.anInt181 = 2000; //Zoom
	class8.anInt190 = 572;
	class8.anInt198 = 1200;
	class8.anInt204 = 0;
	class8.anInt169 = 16;
	class8.anInt194 = 1;
	class8.anInt165 = 14042; //Male
	class8.anInt200 = 14042; //Female
	class8.anInt175 = -1;
	class8.anInt197 = -1;
	class8.anInt199 = -1;//noteable
	class8.aString170 = "Bandos Shield";
	class8.aByteArray178 = "A Defence tool used by Bandos Warriors.".getBytes();
	}
                if(i == 15000){
                            class8.aStringArray189 = new String[5];
                            class8.aStringArray189[1] = "Wear";
                            class8.anIntArray156 = new int[1];
                            class8.anIntArray160 = new int[1];
                            class8.anIntArray156[0] = 0;
                            class8.anIntArray160[0] = 0;
                            class8.anInt174 = 14050;//item look
                            class8.anInt181 = 1957;
                            class8.anInt190 = 498;
                            class8.anInt198 = 484;
                            class8.anInt204 = 0;
                            class8.anInt169 = -1;
                            class8.anInt194 = -1;
                            class8.anInt165 = 14051;
                            class8.anInt200 = 14051;
                            class8.anInt175 = -1;
                            class8.anInt197 = -1;
                            class8.aString170 = "Bandos Scimitar ";
                            class8.aByteArray178 = "A Scimitar Belonging To Bandos Warriors".getBytes();
        }
if(i == 8765)  // change this if you need to "item number"
	{
	    class8.aStringArray189 = new String[5];
	    class8.aStringArray189[1] = "Wield";//New option
	    class8.anInt174 = 14125; //
	    class8.aBoolean176 = false;//Stackable
	    class8.anInt181 = 750; //zoom increase will make it smaller
	    class8.anInt190 = 126; //model rotate up+down increase to move doen away from you
	    class8.anInt198 = 129; //model rotate side ways increase to move right in circle
	    class8.anInt169 = -1; // model offset increase to move to the right
	    class8.anInt194 = 1; //model offset increase to move up
	    class8.anInt204 = 28;
	    class8.anInt165 = 14123;//male wearing
	    class8.anInt200 = 14123;//female wearing
	    class8.anInt204 = 28;
	    class8.anInt175 = -1;//Unknown
	    class8.anInt164 = -1;//Female arms/sleeves
	    class8.anInt188 = -1;//male arms/sleeves
	    class8.aBoolean176 = false;//Stackable
	    class8.aString170 = "Chopper Hat";//Name of the new item
	    class8.aByteArray178 = "A Hat worn by A Chopper".getBytes();
      }
if(i == 14294)
        {
            class8.aStringArray189 = new String[5];
            class8.aStringArray189[1] = "Wield";
            class8.anIntArray156 = new int[6];
            class8.anIntArray160 = new int[6];
            class8.anIntArray156[0] = 5169;
            class8.anIntArray160[0] = 3988;
            class8.anIntArray156[1] = 7343;
            class8.anIntArray160[1] = 3988;
            class8.anIntArray156[2] = 7335;
            class8.anIntArray160[2] = 3982;
            class8.anIntArray156[3] = 7339;
            class8.anIntArray160[3] = 3986;
            class8.anIntArray156[4] = 7343;
            class8.anIntArray160[4] = 5014;
            class8.anIntArray156[5] = 5165;
            class8.anIntArray160[5] = 3988;
            class8.anInt174 = 10133;
            class8.anInt181 = 1000;
            class8.anInt190 = 284;
            class8.anInt198 = 1800;
            class8.anInt204 = 69;
            class8.aBoolean161 = true;
            class8.anInt169 = 1;
            class8.anInt194 = 27;
            class8.anInt175 = -1;
            class8.anInt197 = -1;
            class8.anInt165 = 10134;
            class8.anInt200 = 10134;
            class8.aBoolean176 = false;
            class8.aString170 = "Red chinchompa";
            class8.aByteArray178 = "It explodes.".getBytes();
        }
if(i == 14590)  // change this if you need to "item number"
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";//New option
class8.anInt174 = 3000; //Model ID
class8.anInt181 = 730;//Model Zoom
class8.anInt190 = 567;//Model Rotation
class8.anInt198 = 1120;//
class8.anInt169 = -4;// 
class8.anInt194 = -1;//
class8.anInt165 = 3001;
class8.anInt200 = 3002;
class8.anInt166 = 63;
class8.anInt173 = 120;
class8.anInt167 = 100;
class8.anInt192 = 100;
class8.anInt191 = 100;
class8.anInt196 = 15;
class8.anInt184 = 100;
class8.anInt204 = 1923;
class8.aBoolean176 = false;
class8.aString170 = "Ava's Accumulator";
class8.aByteArray178 = "A Ava's Accumalator".getBytes();
}
if(i == 6891)
        {
            class8.aStringArray189 = new String[5];
            class8.aStringArray189[1] = "Wield";
            class8.anIntArray156 = new int[6];
            class8.anIntArray160 = new int[6];
	    class8.anIntArray156[0] = 926;
	    class8.anIntArray160[0] = 10388;
	    class8.anInt174 = 2635;
	    class8.anInt181 = 440;
	    class8.anInt190 = 76;
	    class8.anInt198 = 1850;
	    class8.anInt204 = 0;
	    class8.anInt169 = 1;
	    class8.anInt194 = 1;
	    class8.anInt165 = 187;
	    class8.anInt200 = 363;
	    class8.anInt175 = 29;
	    class8.anInt197 = 87;
            class8.aBoolean176 = false;
            class8.aString170 = "Barrows party hat";
            class8.aByteArray178 = "A Party Hat Worn At Barrow's Brothers Partys.".getBytes();
        }
if(i == 14538)
        {
            class8.aStringArray189 = new String[5];
            class8.aStringArray189[1] = "Wield";
            class8.anIntArray156 = new int[6];
            class8.anIntArray160 = new int[6];
            class8.anIntArray156[0] = 22168;
            class8.anIntArray160[0] = -21846;
            class8.anIntArray156[1] = 8070;
            class8.anIntArray160[1] = -24771;
            class8.anIntArray156[2] = 24082;
            class8.anIntArray160[2] = -24046;
            class8.anIntArray156[3] = 22156;
            class8.anIntArray160[3] = -21876;
            class8.anIntArray156[4] = 920;
            class8.anIntArray160[4] = -25788;
            class8.anIntArray156[5] = 912;
            class8.anIntArray160[5] = -27983;
            class8.anInt174 = 10114;
            class8.anInt169 = -1;
            class8.anInt181 = 1373;
            class8.anInt190 = 452;
            class8.anInt198 = 0;
            class8.anInt194 = 7;
            class8.anInt204 = 2047;
            class8.anInt165 = 10115;
            class8.anInt200 = 10117;
            class8.anInt164 = 10118;
            class8.anInt188 = 10116;
            class8.aBoolean176 = false;
            class8.aString170 = "Black ele' shirt";
            class8.aByteArray178 = "A well made elegant shirt.".getBytes();
        }
if(i == 7621){
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[1];
class8.anIntArray160 = new int [1];
//Purple: 926-127
class8.anIntArray156[0] = 926;
class8.anIntArray160[0] = 128; 
class8.anInt174 = 2635;//Item Look
class8.anInt181 = 440;
class8.anInt190 = 76;
class8.anInt198 = 1850;
class8.anInt204 = 0;
class8.anInt169 = 1;
class8.anInt194 = 1;
class8.anInt165 = 187;
class8.anInt200 = 363;
class8.anInt175 = 29;
class8.anInt197 = 87;
class8.aString170 = "Black PartyHat";
class8.aByteArray178 = "A Black Partyhat, A Strange Find.".getBytes();
        }

 if(i == 14869)
        {
            class8.aStringArray189 = new String[5];
            class8.aStringArray189[1] = "Wield";
            class8.anIntArray156 = new int[2];
            class8.anIntArray160 = new int[2];
            class8.anIntArray156[0] = 9525;
            class8.anIntArray160[0] = 9659;
            class8.anIntArray156[1] = 9517;
            class8.anIntArray160[1] = 9651;
            class8.anInt174 = 13083;
            class8.anInt169 = 3;
            class8.anInt181 = 1243;
            class8.anInt190 = 567;
            class8.anInt198 = 1867;
            class8.anInt194 = -7;
            class8.anInt204 = 2047;
            class8.anInt165 = 13084;
            class8.anInt200 = 13086;
            class8.anInt164 = 13085;
            class8.anInt188 = 13087;
            class8.aBoolean176 = false;
            class8.aString170 = "Jester top";
            class8.aByteArray178 = "A jester's jangly top. ".getBytes();
        }
if(i == 14740)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[4];
class8.anIntArray160 = new int[4];
class8.anIntArray156[0] = 57280;
class8.anIntArray156[1] = 54503;
class8.anIntArray156[2] = 54183;
class8.anIntArray156[3] = 11200;
class8.anIntArray160[0] = 38226;
class8.anIntArray160[1] = 38226;
class8.anIntArray160[2] = 38226;
class8.anIntArray160[3] = 38226;
class8.anInt174 = 4172; //Inv & Ground
class8.anInt181 = 2000; //Zoom
class8.anInt190 = 572;
class8.anInt198 = 0;
class8.anInt204 = 0;
class8.anInt169 = 0;
class8.anInt194 = 1;
class8.anInt165 = 4194; //Male
class8.anInt200 = 7192; //Female
class8.anInt175 = -1;
class8.anInt197 = -1;
class8.anInt199 = -1;//noteable
class8.aString170 = "Quest Cape";
class8.aByteArray178 = "Its A Quest Cape".getBytes();
}
if(i == 15714) {
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[3];
class8.anIntArray160 = new int [3];
class8.anIntArray156[0] = 0xD4E7;
class8.anIntArray156[1] = 0xDFC0;
class8.anIntArray156[2] = 0xD3A7;
class8.anIntArray160[0] = 8128;
class8.anIntArray160[1] = 9126;
class8.anIntArray160[2] = 8128;
class8.anInt174 = 7022; //Inv & Ground
class8.anInt181 = 2000; //Zoom
class8.anInt190 = 572;
class8.anInt198 = 0;
class8.anInt204 = 0;
class8.anInt169 = 0;
class8.anInt194 = 1;
class8.anInt165 = 7021; //Male
class8.anInt200 = 7021; //Female
class8.anInt175 = -1;
class8.anInt197 = -1;
class8.aString170 = "Summoning Cape(t)";
class8.aByteArray178 = "A Summoning Cape(t)".getBytes();
}


if(i == 16)
        {
            class8.aStringArray189 = new String[5];
            class8.aStringArray189[1] = "Wield";
            class8.anIntArray156 = new int[1];
            class8.anIntArray160 = new int[1];
            class8.anIntArray156[0] = 28;
            class8.anIntArray156[0] = 74;
            class8.anIntArray160[0] = 10512;
            class8.anIntArray160[0] = 924;
            class8.anInt174 = 4671;
            class8.anInt181 = 490;
            class8.anInt190 = 344;
            class8.anInt198 = 192;
            class8.anInt204 = 138;
            class8.anInt199 = -1;
            class8.anInt169 = 1;
            class8.anInt194 = 20;
            class8.anInt165 = 4672;
            class8.anInt200 = 4672;
            class8.anInt175 = 56;
            class8.anInt197 = 116;
            class8.anInt164 = -1;
            class8.anInt188 = -1;
            class8.aBoolean176 = false;
            class8.aString170 = "Dragon Defender";
            class8.aByteArray178 = "A Rare Defensive weapon.".getBytes();
        }

        if(i == 15)
        {
            class8.aStringArray189 = new String[5];
            class8.aStringArray189[1] = "Wield";
            class8.anIntArray156 = new int[1];
            class8.anIntArray160 = new int[1];
            class8.anIntArray156[0] = 28;
            class8.anIntArray156[0] = 74;
            class8.anIntArray160[0] = 10512;
            class8.anIntArray160[0] = 10512;
            class8.anInt174 = 4671;
            class8.anInt181 = 490;
            class8.anInt190 = 344;
            class8.anInt198 = 192;
            class8.anInt204 = 138;
            class8.anInt199 = -1;
            class8.anInt169 = 1;
            class8.anInt194 = 20;
            class8.anInt165 = 4672;
            class8.anInt200 = 4672;
            class8.anInt175 = 56;
            class8.anInt197 = 116;
            class8.anInt164 = -1;
            class8.anInt188 = -1;
            class8.aBoolean176 = false;
            class8.aString170 = "Barrows Defender";
            class8.aByteArray178 = "A Rare Defensive Weapon.".getBytes();
        }
         if(i == 14870)
		        {
		            class8.aStringArray189 = new String[5];
		            class8.aStringArray189[1] = "Wield";
		            class8.anIntArray156 = new int[2];
		            class8.anIntArray160 = new int[2];
		            class8.anIntArray156[0] = 9525;
		            class8.anIntArray160[0] = 9659;
		            class8.anIntArray156[1] = 9517;
		            class8.anIntArray160[1] = 9651;
		            class8.anInt174 = 13088;
		            class8.anInt169 = -1;
		            class8.anInt181 = 1892;
		            class8.anInt190 = 456;
		            class8.anInt198 = 1909;
		            class8.anInt194 = 12;
		            class8.anInt204 = 2047;
		            class8.anInt165 = 13089;
		            class8.anInt200 = 13090;
		            class8.aBoolean176 = false;
		            class8.aString170 = "Jester tights";
		            class8.aByteArray178 = "Jester tights.".getBytes();
        }
         if(i == 14871)
		        {
		            class8.aStringArray189 = new String[5];
		            class8.aStringArray189[1] = "Wield";
		            class8.anIntArray156 = new int[2];
		            class8.anIntArray160 = new int[2];
		            class8.anIntArray156[0] = 9525;
		            class8.anIntArray160[0] = 9659;
		            class8.anIntArray156[1] = 9517;
		            class8.anIntArray160[1] = 9651;
		            class8.anInt174 = 13094;
		            class8.anInt169 = -1;
		            class8.anInt181 = 984;
		            class8.anInt190 = 304;
		            class8.anInt198 = 124;
		            class8.anInt194 = 12;
		            class8.anInt204 = 2047;
		            class8.anInt165 = 13095;
		            class8.anInt200 = 13096;
		            class8.aBoolean176 = false;
		            class8.aString170 = "Jester boots";
		            class8.aByteArray178 = "Silly Jester boots.".getBytes();
        }
         if(i == 14872)
		        {
		            class8.aStringArray189 = new String[5];
		            class8.aStringArray189[1] = "Wield";
		            class8.anIntArray156 = new int[1];
		            class8.anIntArray160 = new int[1];
		            class8.anIntArray156[0] = 61;
		            class8.anIntArray160[0] = -29403;
		            class8.anInt174 = 13097;
		            class8.anInt169 = 4;
		            class8.anInt181 = 1892;
		            class8.anInt190 = 415;
		            class8.anInt198 = 1770;
		            class8.anInt194 = 32;
		            class8.anInt204 = 844;
		            class8.anInt165 = 13098;
		            class8.anInt200 = 13099;
		            class8.aBoolean176 = false;
		            class8.aString170 = "Jester stick";
		            class8.aByteArray178 = "Silly jester stick".getBytes();
        }
        if(i == 14868)
		        {
		            class8.aStringArray189 = new String[5];
		            class8.aStringArray189[1] = "Wield";
		            class8.anIntArray156 = new int[2];
		            class8.anIntArray160 = new int[2];
		            class8.anIntArray156[0] = 9525;
		            class8.anIntArray160[0] = 9659;
		            class8.anIntArray156[1] = 9517;
		            class8.anIntArray160[1] = 9651;
		            class8.anInt174 = 13091;
		            class8.anInt169 = 11;
		            class8.anInt181 = 919;
		            class8.anInt190 = 539;
		            class8.anInt198 = 166;
		            class8.anInt194 = -3;
		            class8.anInt204 = 235;
		            class8.anInt165 = 13092;
		            class8.anInt200 = 13093;
		            class8.aBoolean176 = false;
		            class8.aString170 = "Jester hat";
		            class8.aByteArray178 = "A Silly Hat With Bells.".getBytes();
        }
if(i == 15351) //Saradomin sword
 {
    	class8.aStringArray189 = new String[5];
    	class8.aStringArray189[1] ="Wield";
class8.anIntArray156 = new int[1];
class8.anIntArray160 = new int[1];
class8.anIntArray156[0] = 0;
class8.anIntArray160[0] = 0;
    	class8.anInt174 = 32;//Item Look
    	class8.anInt181 = 2151;
    	class8.anInt190 = 1521;
    	class8.anInt198 = 484;
    	class8.anInt169 = -1;
    	class8.anInt194 = -1;
    	class8.anInt165 = 8032;
    	class8.anInt200 = 8032;
    	class8.aString170 = "Saradomin sword";
    	class8.aByteArray178 = "The Magnificent Blade Of Saradomin!".getBytes();
    }
if(i == 15309)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[1];
class8.anIntArray160 = new int[1];
class8.anIntArray156[0] = 0;
class8.anIntArray160[0] = 0;
class8.anInt174 = 7227;
class8.anInt181 = 919;
class8.anInt190 = 429;
class8.anInt204 = 318;
class8.anInt169 = 1;
class8.anInt194 = 5;
class8.anInt165 = 230;
class8.anInt188 = 7242;
class8.anInt200 = 403;
class8.anInt164 = 7241;
class8.anInt175 = 63;
class8.anInt166 = 7243;
class8.anInt197 = 120;
class8.anInt173 = 7243;
class8.aString170 = "Void Mage Hood";
class8.aByteArray178 = "A Void Knight Magic Hood.".getBytes();
}
if(i == 15310)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Weild";
class8.anIntArray156 = new int[1];
class8.anIntArray160 = new int[1];
class8.anIntArray156[0] = 0;
class8.anIntArray160[0] = 0;
class8.anInt174 = 7228;
class8.anInt181 = 622;
class8.anInt190 = 88;
class8.anInt204 = 318;
class8.anInt169 = 1;
class8.anInt194 = -1;
class8.anInt165 = 230;
class8.anInt188 = 7229;
class8.anInt200 = 403;
class8.anInt164 = 7230;
class8.anInt175 = 63;
class8.anInt166 = 3624;
class8.anInt197 = 120;
class8.anInt173 = 3624;
class8.aString170 = "Void Ranger Helm";
class8.aByteArray178 = "A Void Knight Ranger Helmet".getBytes();
}
if(i == 15311)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[1];
class8.anIntArray160 = new int[1];
class8.anIntArray156[0] = 0;
class8.anIntArray160[0] = 0;
class8.anInt174 = 7231;
class8.anInt181 = 622;
class8.anInt190 = 88;
class8.anInt204 = 318;
class8.anInt169 = 3;
class8.anInt194 = -1;
class8.anInt165 = 230;
class8.anInt188 = 7232;
class8.anInt200 = 403;
class8.anInt164 = 7233;
class8.anInt175 = 63;
class8.anInt166 = 7234;
class8.anInt197 = 120;
class8.anInt173 = 7234;
class8.aString170 = "Void Melee Helm";
class8.aByteArray178 = "A Void Nnight Melee Helmet.".getBytes();
}
if(i == 14635)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wield";
class8.anIntArray156 = new int[2];
class8.anIntArray160 = new int[2];
class8.anIntArray156[0] = 175;
class8.anIntArray160[0] = 21679;
class8.anIntArray156[1] = 4506;
class8.anIntArray160[1] = 21914;
class8.anInt174 = 10122;
class8.anInt169 = 1;
class8.anInt181 = 724;
class8.anInt190 = 69;
class8.anInt198 = 194;
class8.anInt194 = -1;
class8.anInt204 = 0;
class8.anInt165 = 10123;
class8.anInt200 = 10124;
class8.aBoolean176 = false;
class8.aString170 = "Fighter hat";
class8.aByteArray178 = "A Fighter Hat.".getBytes();
}
if(i == 14637)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wield";
class8.anIntArray156 = new int[2];
class8.anIntArray160 = new int[2];
class8.anIntArray156[0] = 175;
class8.anIntArray160[0] = 21679;
class8.anIntArray156[1] = 4506;
class8.anIntArray160[1] = 21914;
class8.anInt174 = 10125;
class8.anInt169 = 7;
class8.anInt181 = 1114;
class8.anInt190 = 14;
class8.anInt198 = 2047;
class8.anInt194 = -1;
class8.anInt204 = 0;
class8.anInt165 = 10126;
class8.anInt200 = 10127;
class8.aBoolean176 = false;
class8.aString170 = "Ranger hat";
class8.aByteArray178 = "A Ranger Hat.".getBytes();
}
if(i == 14634)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wield";
class8.anIntArray156 = new int[2];
class8.anIntArray160 = new int[2];
class8.anIntArray156[0] = 175;
class8.anIntArray160[0] = 21679;
class8.anIntArray156[1] = 4506;
class8.anIntArray160[1] = 21914;
class8.anInt174 = 13077;
class8.anInt169 = 7;
class8.anInt181 = 1114;
class8.anInt190 = 166;
class8.anInt198 = 194;
class8.anInt194 = -4;
class8.anInt204 = 0;
class8.anInt165 = 13078;
class8.anInt200 = 13079;
class8.aBoolean176 = false;
class8.aString170 = "Healer hat";
class8.aByteArray178 = "A Healer Hat.".getBytes();
}
if(i == 14539)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wield";
class8.anIntArray156 = new int[6];
class8.anIntArray160 = new int[6];
class8.anIntArray156[0] = 22168;
class8.anIntArray160[0] = -21846;
class8.anIntArray156[1] = 8070;
class8.anIntArray160[1] = -24771;
class8.anIntArray156[2] = 24082;
class8.anIntArray160[2] = -24046;
class8.anIntArray156[3] = 22156;
class8.anIntArray160[3] = -21876;
class8.anIntArray156[4] = 920;
class8.anIntArray160[4] = -25788;
class8.anIntArray156[5] = 912;
class8.anIntArray160[5] = -27983;
class8.anInt174 = 10119;
class8.anInt169 = -1;
class8.anInt181 = 1827;
class8.anInt190 = 444;
class8.anInt198 = 1259;
class8.anInt194 = -1;
class8.anInt204 = 2047;
class8.anInt165 = 10120;
class8.anInt200 = 10121;
class8.anInt164 = -1;
class8.anInt188 = -1;
class8.aBoolean176 = false;
class8.aString170 = "Black ele' legs";
class8.aByteArray178 = "A well Made Elegant Bottom.".getBytes();
        }
if(i == 15341)  // change this if you need to "item number"
{
class8.anIntArray156 = new int[0];
class8.anIntArray160 = new int[0];
class8.anInt174 = 7285; //Model ID
class8.anInt181 = 984;
class8.anInt190 = 549;
class8.anInt198 = 249;
class8.anInt169 = 3;
class8.anInt194 = 1;
class8.anInt204 = 0;
class8.anInt175 = -1;
class8.anInt197 = -1;
class8.anInt165 = -1;//male wearing
class8.anInt200 = -1;//female wearing
class8.aBoolean176 = false;//Stackable
class8.aString170 = "Godsword Shard 1";//Name of the new item
class8.aByteArray178 = "A terrifying Shard.".getBytes();//examin info
        }
if(i == 15342)  // change this if you need to "item number"
{
class8.anIntArray156 = new int[0];
class8.anIntArray160 = new int[0];
class8.anInt174 = 7288; //Model ID
class8.anInt181 = 984;
class8.anInt190 = 549;
class8.anInt198 = 249;
class8.anInt169 = 3;
class8.anInt194 = 1;
class8.anInt204 = 0;
class8.anInt175 = -1;
class8.anInt197 = -1;
class8.anInt165 = -1;//male wearing
class8.anInt200 = -1;//female wearing
class8.aBoolean176 = false;//Stackable
class8.aString170 = "Godsword Shard 2";//Name of the new item
class8.aByteArray178 = "A terrifying Shard.".getBytes();//examin info
        }
if(i == 15343)  // change this if you need to "item number"
{
class8.anIntArray156 = new int[0];
class8.anIntArray160 = new int[0];
class8.anInt174 = 7286; //Model ID
class8.anInt181 = 984;
class8.anInt190 = 549;
class8.anInt198 = 249;
class8.anInt169 = 3;
class8.anInt194 = 1;
class8.anInt204 = 0;
class8.anInt175 = -1;
class8.anInt197 = -1;
class8.anInt165 = -1;//male wearing
class8.anInt200 = -1;//female wearing
class8.aBoolean176 = false;//Stackable
class8.aString170 = "Godsword shard 3";//Name of the new item
class8.aByteArray178 = "A terrifying Shard.".getBytes();//examin info
        }
if(i == 2777)  // change this if you need to "item number"
{
class8.aStringArray189 = new String[5];
class8.anInt174 = 7281; //Model ID
class8.anInt181 = 800; //zoom increase will make it smaller
class8.anInt190 = 498; //model rotate up+down increase to move doen away from you
class8.anInt198 = 263; //model rotate side ways increase to move right in circle
class8.anInt169 = -3; // model offset increase to move to the right
class8.anInt194 = -1; //model offset increase to move up
class8.anInt165 = -1;//male wearing
class8.anInt200 = -1;//female wearing
class8.anInt164 = -1;//Female arms/sleeves
class8.anInt188 = -1;//male arms/sleeves
class8.aBoolean176 = false;//Stackable
class8.aString170 = "Bandos hilt";//Name of the new item
class8.aByteArray178 = "Brimming with potential".getBytes();//examin info
        }
if(i == 2778)  // change this if you need to "item number"
{
class8.aStringArray189 = new String[5];
class8.anInt174 = 7282; //Model ID
class8.anInt181 = 800; //zoom increase will make it smaller
class8.anInt190 = 498; //model rotate up+down increase to move doen away from you
class8.anInt198 = 263; //model rotate side ways increase to move right in circle
class8.anInt169 = -3; // model offset increase to move to the right
class8.anInt194 = -1; //model offset increase to move up
class8.anInt165 = -1;//male wearing
class8.anInt200 = -1;//female wearing
class8.anInt164 = -1;//Female arms/sleeves
class8.anInt188 = -1;//male arms/sleeves
class8.aBoolean176 = false;//Stackable
class8.aString170 = "Armadyl hilt";//Name of the new item
class8.aByteArray178 = "Brimming with potential".getBytes();//examin info
        }
if(i == 2779)  // change this if you need to "item number"
{
class8.aStringArray189 = new String[5];
class8.anInt174 = 7283; //Model ID
class8.anInt181 = 900; //zoom increase will make it smaller
class8.anInt190 = 498; //model rotate up+down increase to move doen away from you
class8.anInt198 = 263; //model rotate side ways increase to move right in circle
class8.anInt169 = -3; // model offset increase to move to the right
class8.anInt194 = -1; //model offset increase to move up
class8.anInt165 = -1;//male wearing
class8.anInt200 = -1;//female wearing
class8.anInt164 = -1;//Female arms/sleeves
class8.anInt188 = -1;//male arms/sleeves
class8.aBoolean176 = false;//Stackable
class8.aString170 = "Saradomin hilt";//Name of the new item
class8.aByteArray178 = "Brimming with potential".getBytes();//examin info
        }
if(i == 2780)  // change this if you need to "item number"
{
class8.aStringArray189 = new String[5];
class8.anInt174 = 7284; //Model ID
class8.anInt181 = 900; //zoom increase will make it smaller
class8.anInt190 = 498; //model rotate up+down increase to move doen away from you
class8.anInt198 = 263; //model rotate side ways increase to move right in circle
class8.anInt169 = -3; // model offset increase to move to the right
class8.anInt194 = -1; //model offset increase to move up
class8.anInt165 = -1;//male wearing
class8.anInt200 = -1;//female wearing
class8.anInt164 = -1;//Female arms/sleeves
class8.anInt188 = -1;//male arms/sleeves
class8.aBoolean176 = false;//Stackable
class8.aString170 = "Zamorak hilt";//Name of the new item
class8.aByteArray178 = "Brimming with potential".getBytes();//examin info
        }
if(i == 14563)
        {
            class8.aStringArray189 = new String[5];
            class8.aStringArray189[1] = "Wield";
            class8.anIntArray156 = new int[5];
            class8.anIntArray160 = new int[5];
            class8.anIntArray156[0] = 105;
            class8.anIntArray160[0] = -20175;
            class8.anIntArray156[1] = 64;
            class8.anIntArray160[1] = -16190;
            class8.anIntArray156[2] = 47;
            class8.anIntArray160[2] = -20297;
            class8.anIntArray156[3] = 26;
            class8.anIntArray160[3] = -15848;
            class8.anIntArray156[4] = -10364;
            class8.anIntArray160[4] = -14452;
            class8.anInt174 = 14100;
            class8.anInt181 = 1827;
            class8.anInt190 = 364;
            class8.anInt198 = 992;
            class8.anInt169 = 3;
            class8.anInt194 = 12;
            class8.anInt204 = 2047;
            class8.anInt165 = 14101;
            class8.anInt200 = 14102;
            class8.aBoolean176 = false;
            class8.aString170 = "Zamorak cloak";
            class8.aByteArray178 = "Emblazoned with Zamorak's symbol, a curly M-shape. ".getBytes();
        }
if(i == 14561)
        {
            class8.aStringArray189 = new String[5];
            class8.aStringArray189[1] = "Wield";
            class8.anIntArray156 = new int[5];
            class8.anIntArray160 = new int[5];
            class8.anIntArray156[0] = 105;
            class8.anIntArray160[0] = -20175;
            class8.anIntArray156[1] = 64;
            class8.anIntArray160[1] = -16190;
            class8.anIntArray156[2] = 47;
            class8.anIntArray160[2] = -20297;
            class8.anIntArray156[3] = 26;
            class8.anIntArray160[3] = -15848;
            class8.anIntArray156[4] = -10364;
            class8.anIntArray160[4] = -14452;
            class8.anInt174 = 14103;
            class8.anInt181 = 1827;
            class8.anInt190 = 364;
            class8.anInt198 = 992;
            class8.anInt169 = 3;
            class8.anInt194 = 12;
            class8.anInt204 = 2047;
            class8.anInt165 = 14104;
            class8.anInt200 = 14105;
            class8.aBoolean176 = false;
            class8.aString170 = "Saradomin cloak";
            class8.aByteArray178 = "Emblazoned with Saradomin's symbol, a Four-pointed star. ".getBytes();
        }
if(i == 14562)
        {
            class8.aStringArray189 = new String[5];
            class8.aStringArray189[1] = "Wield";
            class8.anIntArray156 = new int[5];
            class8.anIntArray160 = new int[5];
            class8.anIntArray156[0] = 105;
            class8.anIntArray160[0] = -20175;
            class8.anIntArray156[1] = 64;
            class8.anIntArray160[1] = -16190;
            class8.anIntArray156[2] = 47;
            class8.anIntArray160[2] = -20297;
            class8.anIntArray156[3] = 26;
            class8.anIntArray160[3] = -15848;
            class8.anIntArray156[4] = -10364;
            class8.anIntArray160[4] = -14452;
            class8.anInt174 = 14106;
            class8.anInt181 = 1827;
            class8.anInt190 = 364;
            class8.anInt198 = 992;
            class8.anInt169 = 3;
            class8.anInt194 = 12;
            class8.anInt204 = 2047;
            class8.anInt165 = 14107;
            class8.anInt200 = 14108;
            class8.aBoolean176 = false;
            class8.aString170 = "Guthix cloak";
            class8.aByteArray178 = "Emblazoned with Guthix's symbol. ".getBytes();
        }
if(i == 14510)
        {
            class8.aStringArray189 = new String[5];
            class8.aStringArray189[1] = "Wield";
            class8.anIntArray156 = new int[3];
            class8.anIntArray160 = new int[3];
            class8.anIntArray156[0] = 5559;
            class8.anIntArray160[0] = -12871;
            class8.anIntArray156[1] = 5665;
            class8.anIntArray160[1] = -15808;
            class8.anIntArray156[2] = 5784;
            class8.anIntArray160[2] = -15689;
            class8.anInt174 = 13047;
            class8.anInt181 = 543;
            class8.anInt190 = 282;
            class8.anInt198 = 73;
            class8.anInt169 = 4;
            class8.anInt175 = -1;
            class8.anInt194 = 100;
            class8.anInt197 = -1;
            class8.anInt204 = 69;
            class8.anInt165 = 13048;
            class8.anInt200 = 13049;
            class8.anInt188 = -1;
            class8.anInt164 = -1;
            class8.aBoolean176 = false;
            class8.aString170 = "3rd age amulet";
            class8.aByteArray178 = "Fabulously ancient magic protection enchanted in the 3rd age. ".getBytes();
        }
if(i == 14538)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wield";
class8.anIntArray156 = new int[6];
class8.anIntArray160 = new int[6];
class8.anIntArray156[0] = 22168;
class8.anIntArray160[0] = -21846;
class8.anIntArray156[1] = 8070;
class8.anIntArray160[1] = -24771;
class8.anIntArray156[2] = 24082;
class8.anIntArray160[2] = -24046;
class8.anIntArray156[3] = 22156;
class8.anIntArray160[3] = -21876;
class8.anIntArray156[4] = 920;
class8.anIntArray160[4] = -25788;
class8.anIntArray156[5] = 912;
class8.anIntArray160[5] = -27983;
class8.anInt174 = 10114;
class8.anInt169 = -1;
class8.anInt181 = 1373;
class8.anInt190 = 452;
class8.anInt198 = 0;
class8.anInt194 = 7;
class8.anInt204 = 2047;
class8.anInt165 = 10115;
class8.anInt200 = 10117;
class8.anInt164 = 10118;
class8.anInt188 = 10116;
class8.aBoolean176 = false;
class8.aString170 = "Black ele' shirt";
class8.aByteArray178 = "A well made elegant shirt.".getBytes();
}
if(i == 14651)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wield";
class8.anIntArray156 = new int[1];
class8.anIntArray160 = new int[1];
class8.anIntArray156[0] = 4696;
class8.anIntArray160[0] = 4;
class8.anInt174 = 13072;
class8.anInt169 = 1;
class8.anInt181 = 1280;
class8.anInt190 = 336;
class8.anInt198 = 2016;
class8.anInt194 = 5;
class8.anInt204 = 0;
class8.anInt165 = 13073;
class8.anInt200 = 13075;
class8.anInt164 = 13074;
class8.anInt188 = 13076;
class8.aBoolean176 = false;
class8.aString170 = "Granite body";
class8.aByteArray178 = "A Stone Platebody ".getBytes();
}
if(i == 14668)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wield";
class8.anIntArray156 = new int[2];
class8.anIntArray160 = new int[2];
class8.anIntArray156[0] = 5916;
class8.anIntArray160[0] = 127;
class8.anIntArray156[1] = -11359;
class8.anIntArray160[1] = -3504;
class8.anInt174 = 13069;
class8.anInt169 = 0;
class8.anInt181 = 480;
class8.anInt190 = 120;
class8.anInt198 = 1944;
class8.anInt194 = 4;
class8.anInt204 = 0;
class8.anInt165 = 13070;
class8.anInt200 = 13071;
class8.aBoolean176 = false;
class8.aString170 = "Granite helm";
class8.aByteArray178 = "A Stone Helmet. ".getBytes();
}
if(i == 14519 || i == 14797)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wield";
class8.anIntArray156 = new int[2];
class8.anIntArray160 = new int[2];
class8.anIntArray156[0] = 5916;
class8.anIntArray160[0] = 127;
class8.anIntArray156[1] = 4882;
class8.anIntArray160[1] = 127;
class8.anInt174 = 13054;
class8.anInt169 = 4;
class8.anInt181 = 415;
class8.anInt190 = 282;
class8.anInt198 = 73;
class8.anInt194 = 100;
class8.anInt204 = 69;
class8.anInt165 = 13055;
class8.anInt200 = 13056;
class8.aBoolean176 = false;
class8.aString170 = "Amulet of glory(t)";
class8.aByteArray178 = "A very powerful dragonstone amulet.".getBytes();
}
if(i == 14520 || i == 14814)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wield";
class8.anIntArray156 = new int[2];
class8.anIntArray160 = new int[2];
class8.anIntArray156[0] = 5916;
class8.anIntArray160[0] = 127;
class8.anIntArray156[1] = 4882;
class8.anIntArray160[1] = 127;
class8.anInt174 = 13057;
class8.anInt169 = 5;
class8.anInt181 = 465;
class8.anInt190 = 292;
class8.anInt198 = 74;
class8.anInt194 = 100;
class8.anInt204 = 69;
class8.anInt165 = 13058;
class8.anInt200 = 13059;
class8.aBoolean176 = false;
class8.aString170 = "Strength amulet(t)";
class8.aByteArray178 = "An enchanted ruby amulet. ".getBytes();
}
if(i == 14521 || i == 14815)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wield";
class8.anIntArray156 = new int[2];
class8.anIntArray160 = new int[2];
class8.anIntArray156[0] = 5916;
class8.anIntArray160[0] = 127;
class8.anIntArray156[1] = 4882;
class8.anIntArray160[1] = 127;
class8.anInt174 = 13060;
class8.anInt169 = 4;
class8.anInt181 = 400;
class8.anInt190 = 284;
class8.anInt198 = 90;
class8.anInt194 = 100;
class8.anInt204 = 111;
class8.anInt165 = 13061;
class8.anInt200 = 13062;
class8.aBoolean176 = false;
class8.aString170 = "Amulet Of Magic(T)";
class8.aByteArray178 = "An enchanted ruby amulet. ".getBytes();
}
if(i == 13640)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wield";
class8.anInt174 = 10070; //Model Drop/inv view
class8.anInt181 = 854;
class8.anInt190 = 387;
class8.anInt198 = 1892;
class8.anInt199 = -1;
class8.anInt169 = -1;
class8.anInt194 = 3;
class8.anInt197 = 120;
class8.anInt204 = 138;
class8.anInt165 = 10071; //male wearing
class8.anInt200 = 10072; //female wearing
class8.anInt164 = -1; //Female arms/sleeves
class8.anInt188 = -1; //male arms/sleeves
class8.anInt175 = -1;
class8.aBoolean176 = false;
class8.aString170 = "Black mask";
class8.aByteArray178 = "A Magic Horror Mask.".getBytes();
}
if(i == 13800)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wield";
class8.anIntArray156 = new int[6];
class8.anIntArray160 = new int[6];
class8.anIntArray156[0] = 6447;
class8.anIntArray160[0] = 6806;
class8.anIntArray156[1] = 6443;
class8.anIntArray160[1] = 7054;
class8.anIntArray156[2] = 6439;
class8.anIntArray160[2] = 5002;
class8.anIntArray156[3] = 5656;
class8.anIntArray160[3] = -29395;
class8.anIntArray156[4] = 5652;
class8.anIntArray160[4] = -29403;
class8.anIntArray156[5] = 5904;
class8.anIntArray160[5] = -29284;
class8.anInt174 = 10128;
class8.anInt181 = 926;
class8.anInt190 = 432;
class8.anInt198 = 258;
class8.anInt169 = 3;
class8.anInt194 = 3;
class8.anInt204 = 83;
class8.anInt165 = 10129;
class8.anInt200 = 10130;
class8.aBoolean176 = false;
class8.aString170 = "Rune c'bow";
class8.aByteArray178 = "A Runite Crossbow.".getBytes();
}
if(i == 13799)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wield";
class8.anIntArray156 = new int[6];
class8.anIntArray160 = new int[6];
class8.anIntArray156[0] = 6447;
class8.anIntArray160[0] = 6585;
class8.anIntArray156[1] = 6443;
class8.anIntArray160[1] = 6705;
class8.anIntArray156[2] = 6439;
class8.anIntArray160[2] = 6697;
class8.anIntArray156[3] = 5656;
class8.anIntArray160[3] = 21671;
class8.anIntArray156[4] = 5652;
class8.anIntArray160[4] = 21662;
class8.anIntArray156[5] = 5904;
class8.anIntArray160[5] = 21782;
class8.anInt174 = 10128;
class8.anInt181 = 926;
class8.anInt190 = 432;
class8.anInt198 = 258;
class8.anInt169 = 3;
class8.anInt194 = 3;
class8.anInt204 = 83;
class8.anInt165 = 10129;
class8.anInt200 = 10130;
class8.aBoolean176 = false;
class8.aString170 = "Adamantite c'bow";
class8.aByteArray178 = "An Adamant Crossbow.".getBytes();
}
if (i == 10007)
{
	class8.aStringArray189 = new String[5];
	class8.aStringArray189[1] = "Wield";//New option
	class8.anIntArray156 = new int[2];
	class8.anIntArray160 = new int[2];
	class8.anIntArray156[0] = 43072;
	class8.anIntArray160[0] = 925;
	class8.anIntArray156[1] = 4550;
	class8.anIntArray160[1] = 925;
	class8.anInt174 = 14104; //Model ID
	class8.anInt181 = 1330; //zoom increase will make it smaller
	class8.anInt190 = 310; //model rotate up+down increase to move doen away from you
	class8.anInt198 = 1800; //model rotate side ways increase to move right in circle
	class8.anInt169 = 0; // model offset increase to move to the right
	class8.anInt194 = 1; //model offset increase to move up
	class8.anInt165 = 14105;//male wearing
	class8.anInt200 = 14105;//female wearing
	class8.anInt164 = 14106;//Female arms/sleeves
	class8.anInt188 = 14106;//male arms/sleeves
	class8.aBoolean176 = false;//Stackable
	class8.aString170 = "Dragon platebody";//Name of the new item
	class8.aByteArray178 = "Provides excellent protection".getBytes();//examin info

}
if (i == 16600)  // change this if you need to "item number"
{
	class8.aStringArray189 = new String[5];
	class8.aStringArray189[1] = "Wear";//New option
	class8.anIntArray156 = new int[9];
	class8.anIntArray160 = new int[9];
	class8.anIntArray156[0] = 10266;
	class8.anIntArray160[0] = 925;
	class8.anIntArray156[1] = 10275;
	class8.anIntArray160[1] = 925;
	class8.anIntArray156[2] = 10291;
	class8.anIntArray160[2] = 924;
	class8.anIntArray156[3] = 10283;
	class8.anIntArray160[3] = 924;
	class8.anIntArray156[4] = 4550;
	class8.anIntArray160[4] = 925;
	class8.anIntArray156[5] = 10287;
	class8.anIntArray160[5] = 924;
	class8.anIntArray156[6] = 8375;
	class8.anIntArray160[6] = 80;
	class8.anIntArray156[7] = 8384;
	class8.anIntArray160[7] = 75;
	class8.anIntArray156[8] = 8367;
	class8.anIntArray160[8] = 70;
	class8.anInt174 = 14081; //Model ID // 20233
	class8.anInt181 = 1330; //zoom increase will make it smaller
	class8.anInt190 = 572; //model rotate up+down increase to move doen away from you
	class8.anInt198 = 302; //model rotate side ways increase to move right in circle
	class8.anInt169 = 0; // model offset increase to move to the right
	class8.anInt194 = 1; //model offset increase to move up
	class8.anInt165 = 14079;//male wearing //
	class8.anInt200 = 14079;//female wearing //
	class8.anInt164 = 14080;//Female arms/sleeves //
	class8.anInt188 = 14080;//male arms/sleeves //
	class8.aBoolean176 = false;//Stackable
	class8.aString170 = "Dragon platebody";//Name of the new item
	class8.aByteArray178 = "An Ancient Platebody ".getBytes();//examin info		        
}
if (i == 13665) // Your desired item id (the one you use after pickup ##### #)
{
	class8.aStringArray189 = new String[5];
	class8.aStringArray189[1] = "Wield"; //
	class8.anInt174 = 9998; //DropInv Model
	class8.anInt165 = 9999; //Male Wield Model
	class8.anInt200 = 9999; //Female Wield
	class8.anInt188 = -1; //Male armssleeves (Leave as -1 if not used)
	class8.anInt164 = -1; //Female armssleeves (Leave as -1 if not used)
	class8.anInt181 = 800; //Zoom - Increase to make inv model smaller
	class8.anInt190 = 498; //Rotate updown - Increase to rotate upwards
	class8.anInt198 = 1300; //Rotate rightleft - Increase to rotate right
	class8.anInt169 = -1; //Position in inv, increase to move right
	class8.anInt194 = -1; //Position in inv, increase to move up
	class8.aString170 = "Tri God WarSpear"; //Item Name
	class8.aByteArray178 = "Has_The_Souls_Of_The_Gods".getBytes(); //Item Examine
}
if (i == 11115) // Your desired item id (the one you use after ::pickup ##### #)
{
	class8.aStringArray189 = new String[5];
	class8.aStringArray189[1] = "Wear"; // String name, this can be changed to wield, or attach, or whatever you want
	class8.anInt174 = 10000; // Drop/Inv Model
	class8.anInt165 = 10001; // Male Wield Model
	class8.anInt200 = 10001; // Female Wield
	class8.anInt188 = -1; // Male arms/sleeves (Leave as -1 if not used)
	class8.anInt164 = -1; // Female arms/sleeves (Leave as -1 if not used)
	class8.anInt181 = 800; // Zoom - Increase to make inv model smaller
	class8.anInt190 = 498; // Rotate up/down -  Increase to rotate upwards
	class8.anInt198 = 1300; // Rotate right/left - Increase to rotate right
	class8.anInt169 = -1; // Position in inv, increase to move right
	class8.anInt194 = -1; // Position in inv, increase to move up                        
	class8.aString170 = "Deadly Scythe"; // Item Name
	class8.aByteArray178 = "A Deadly Scythe".getBytes(); // Item Examine
	
}
if (i == 10011)
{
	class8.aStringArray189 = new String[5];
	class8.aStringArray189[1] = "Wield";
	class8.aString170 = "Torn Dragon Shield";
	class8.aByteArray178 = "A torn war shield from the Elite Dragon Warriors.".getBytes();
	class8.anInt174 = 13701;
	class8.anInt181 = 1560;
	class8.anInt190 = 344;
	class8.anInt198 = 1104;
	class8.anInt204 = 0;
	class8.anInt169 = -6;
	class8.anInt194 = -14;
	class8.anInt165 = 13700;
	class8.anInt200 = 13700;
	class8.anInt188 = -1;
	class8.anInt164 = -1;
	class8.anInt175 = -1;
	class8.anInt197 = -1;
}
if (i == 9999)    //item id
{
	class8.aStringArray189 = new String[5];
	class8.aStringArray189[1] = "Wield";
	class8.anIntArray156 = new int[1];
	class8.anIntArray160 = new int[1];
	class8.anIntArray156[0] = 561;
	class8.anIntArray160[0] = 926;
	class8.anInt174 = 13037;
	class8.anInt181 = 1260;
	class8.anInt190 = 464;
	class8.anInt198 = 0;
	class8.anInt204 = 0;
	class8.anInt169 = 1;
	class8.anInt194 = 4;
	class8.anInt165 = 12824;
	class8.anInt200 = 12824;
	class8.anInt175 = -1;
	class8.anInt197 = -1;
	class8.aString170 = "Dragon kiteshield";
	class8.aByteArray178 = "A Dragon KiteShield received from the Dragon Warriors.".getBytes();
}
if (i == 13459) //id
{
	class8.aStringArray189 = new String[5];
	class8.aStringArray189[1] = "Wield";
	class8.anInt174 = 1510;
	class8.anInt181 = 1000;
	class8.anInt190 = 498;
	class8.anInt198 = 484;
	class8.anInt204 = 318;
	class8.anInt169 = -1;
	class8.anInt194 = -1;
	class8.anInt165 = 1509;
	class8.anInt200 = 1509;
	class8.anInt188 = -1;
	class8.anInt164 = -1;
	class8.anInt175 = -1;
	class8.anInt197 = -1;
	class8.aString170 = "Blue Lightsaber";
	class8.aByteArray178 = "May the force be with you...".getBytes();
}
if (i == 10069)  // change this if you need to "item number"
{
	class8.aStringArray189 = new String[5];
	class8.aStringArray189[1] = "Wield";//New option
	class8.anInt174 = 12121; // 21938
	class8.aBoolean176 = false;//Stackable
	class8.anInt181 = 1000; //zoom increase will make it smaller
	class8.anInt190 = 126; //model rotate up+down increase to move doen away from you
	class8.anInt198 = 129; //model rotate side ways increase to move right in circle
	class8.anInt169 = -1; // model offset increase to move to the right
	class8.anInt194 = 1; //model offset increase to move up
	class8.anInt204 = 28;
	class8.anInt165 = 12122;//male wearing
	class8.anInt200 = 12122;//female wearing
	class8.anInt204 = 28;
	class8.anInt175 = -1;//Unknown
	class8.anInt164 = -1;//Female arms/sleeves
	class8.anInt188 = -1;//male arms/sleeves
	class8.aBoolean176 = false;//Stackable
	class8.aString170 = "Dragon Light";//Name of the new item
	class8.aByteArray178 = "From the Force of Darkness!".getBytes();//examin info
}
if (i == 13643)  // change this if you need to "item number"
{
	class8.aStringArray189 = new String[5];
	class8.aStringArray189[1] = "Wield";//New option
	class8.anInt174 = 14059; //
	class8.aBoolean176 = false;//Stackable
	class8.anInt181 = 1300; //zoom increase will make it smaller
	class8.anInt190 = 470; //model rotate up+down increase to move doen away from you
	class8.anInt198 = 129; //model rotate side ways increase to move right in circle
	class8.anInt169 = -1; // model offset increase to move to the right
	class8.anInt194 = -2; //model offset increase to move up
	class8.anInt204 = 28;
	class8.anInt165 = 14060;//male wearing
	class8.anInt200 = 14060;//female wearing
	class8.anInt204 = 28;
	class8.anInt175 = -1;//Unknown
	class8.anInt164 = -1;//Female arms/sleeves
	class8.anInt188 = -1;//male arms/sleeves
	class8.aBoolean176 = false;//Stackable
	class8.aString170 = "Statius's warhammer";//Name of the new item
	class8.aByteArray178 = "A Warhammer obtained by unknown means.".getBytes();//examin info
}
if(i == 8002){
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wield";
class8.aString170 = "Dragon claws";
class8.aByteArray178 = "A set of Fighting Claws.".getBytes();
class8.anInt174 = 13699;
class8.anInt181 = 630;
class8.anInt190 = 268;
class8.anInt198 = 1340;
class8.anInt204 = 0;
class8.anInt169 = -7;
class8.anInt194 = -13;
class8.anInt165 = 13698;
class8.anInt200 = 13698;
class8.anInt188 = -1;
class8.anInt164 = -1;
class8.anInt175 = -1;
class8.anInt197 = -1;
}
if(i == 13798)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wield";
class8.anIntArray156 = new int[6];
class8.anIntArray160 = new int[6];
class8.anIntArray156[0] = 6447;
class8.anIntArray160[0] = 6577;
class8.anIntArray156[1] = 6443;
class8.anIntArray160[1] = 6697;
class8.anIntArray156[2] = 6439;
class8.anIntArray160[2] = 6817;
class8.anIntArray156[3] = 5656;
class8.anIntArray160[3] = -22359;
class8.anIntArray156[4] = 5652;
class8.anIntArray160[4] = -22239;
class8.anIntArray156[5] = 5904;
class8.anIntArray160[5] = -22248;
class8.anInt174 = 10128;
class8.anInt181 = 926;
class8.anInt190 = 432;
class8.anInt198 = 258;
class8.anInt169 = 3;
class8.anInt194 = 3;
class8.anInt204 = 83;
class8.anInt165 = 10129;
class8.anInt200 = 10130;
class8.aBoolean176 = false;
class8.aString170 = "Mith c'bow";
class8.aByteArray178 = "A Mithril Crossbow.".getBytes();
}
if(i == 13797)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wield";
class8.anIntArray156 = new int[6];
class8.anIntArray160 = new int[6];
class8.anIntArray156[0] = 6447;
class8.anIntArray160[0] = 6602;
class8.anIntArray156[1] = 6443;
class8.anIntArray160[1] = 6594;
class8.anIntArray156[2] = 6439;
class8.anIntArray160[2] = 6585;
class8.anIntArray156[3] = 5656;
class8.anIntArray160[3] = 70;
class8.anIntArray156[4] = 5652;
class8.anIntArray160[4] = 61;
class8.anIntArray156[5] = 5904;
class8.anIntArray160[5] = 53;
class8.anInt174 = 10128;
class8.anInt181 = 926;
class8.anInt190 = 432;
class8.anInt198 = 258;
class8.anInt169 = 3;
class8.anInt194 = 3;
class8.anInt204 = 83;
class8.anInt165 = 10129;
class8.anInt200 = 10130;
class8.aBoolean176 = false;
class8.aString170 = "Steel c'bow";
class8.aByteArray178 = "A Steel Crossbow.".getBytes();
}
if (i == 15602)
{
	class8.aStringArray189 = new String[5];
	class8.aStringArray189[1] = "Equip"; // String name, this can be changed to wield, or attach, or whatever you want
	class8.anInt174 = 11111; // Drop/Inv Model
	class8.anInt165 = 11111; // Male Wield Model
	class8.anInt200 = 11111; // Female Wield
	class8.anInt188 = -1; // Male arms/sleeves (Leave as -1 if not used)
	class8.anInt164 = -1; // Female arms/sleeves (Leave as -1 if not used)
	class8.anInt181 = 400; // Zoom - Increase to make inv model smaller
	class8.anInt190 = 498; // Rotate up/down -  Increase to rotate upwards
	class8.anInt198 = 1300; // Rotate right/left - Increase to rotate right
	class8.anInt169 = -1; // Position in inv, increase to move right
	class8.anInt194 = -1; // Position in inv, increase to move up						
	class8.aString170 = "Dragon Cape";// Item Name
	class8.aByteArray178 = "A Cape Designed Like a Dragon.".getBytes(); // Item Examine
}
if (i == 15603)
{
	class8.aStringArray189 = new String[5];
	class8.aStringArray189[1] = "Equip"; // String name, this can be changed to wield, or attach, or whatever you want
	class8.anInt174 = 11112; // Drop/Inv Model
	class8.anInt165 = 11112; // Male Wield Model
	class8.anInt200 = 11112; // Female Wield
	class8.anInt188 = -1; // Male arms/sleeves (Leave as -1 if not used)
	class8.anInt164 = -1; // Female arms/sleeves (Leave as -1 if not used)
	class8.anInt181 = 400; // Zoom - Increase to make inv model smaller
	class8.anInt190 = 498; // Rotate up/down -  Increase to rotate upwards
	class8.anInt198 = 1300; // Rotate right/left - Increase to rotate right
	class8.anInt169 = -1; // Position in inv, increase to move right
	class8.anInt194 = -1; // Position in inv, increase to move up						
	class8.aString170 = "Beautiful Cape"; // Item Name
	class8.aByteArray178 = "A Beautiful Cape From Fancy Crafters.".getBytes(); // Item Examine
}
if (i == 1283)
{
	class8.aStringArray189 = new String[5];
	class8.aStringArray189[1] = "Wield";
	class8.anIntArray156 = new int[1];
	class8.anIntArray160 = new int[1];
	class8.anIntArray156[0] = 0;
	class8.anIntArray160[0] = 0;
	class8.anInt174 = 14053;//item look
	class8.anInt181 = 1957;
	class8.anInt190 = 498;
	class8.anInt198 = 484;
	class8.anInt204 = 0;
	class8.anInt169 = -1;
	class8.anInt194 = -1;
	class8.anInt165 = 14052;
	class8.anInt200 = 14052;
	class8.anInt175 = -1;
	class8.anInt197 = -1;
	class8.aString170 = "Double Dildo";
	class8.aByteArray178 = "The True Man's Toy.".getBytes();	
}
if(i == 9035)  // change this if you need to "item number"
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wield";
class8.anInt174 = 9111;
class8.anInt181 = 2000;
class8.anInt190 = 591;
class8.anInt198 = 100;
class8.anInt204 = 138;
class8.anInt199 = -1;
class8.anInt169 = 1;
class8.anInt194 = 3;
class8.anInt165 = 9110;
class8.anInt200 = 9110;
class8.anInt175 = -1;
class8.aBoolean176 = false;
class8.anInt164 = -1;
class8.anInt188 = -1;
class8.aBoolean176 = false;
class8.aString170 = "Sword Of Fear";
class8.aByteArray178 = "Strikes Fear Into An Opponent.".getBytes();
}
if(i == 11329){
        class8.aStringArray189 = new String[5];
        class8.aStringArray189[1] = "Drive";
	class8.aString170 = "Car";
	class8.aByteArray178 = "Driving and stuff.".getBytes();
	class8.anInt181 = 1957;
	class8.anInt190 = 498;
	class8.anInt198 = 484;
	class8.anInt204 = 0;
	class8.anInt169 = -1;
	class8.anInt194 = -1;
	class8.anInt174 = 13006;//inventory/drop
	class8.anInt165 = 13006;//male equip
	class8.anInt200 = 13006;//female equip
}
        if(i == 11328)
        {
            class8.aStringArray189 = new String[5];
            class8.aStringArray189[1] = "Wear";
            class8.anInt174 = 13010;
            class8.anInt165 = 13009;
            class8.anInt200 = 13009;
            class8.anInt181 = 800;
            class8.anInt190 = 160;
            class8.anInt198 = 152;
            class8.anInt204 = 0;
            class8.anInt169 = -1;
            class8.anInt194 = 6;
            class8.anInt188 = -1;
            class8.anInt164 = -1;
            class8.anInt175 = 56;
            class8.anInt197 = 116;
            class8.aString170 = "Halo Helmet";
            class8.aByteArray178 = "A Halo Helmet, Crafted By Fine Engineers.".getBytes();
        }
if(i == 1702){
            class8.aStringArray189 = new String[5];
            class8.aStringArray189[1] = "Wear";
            class8.anIntArray156 = new int[1];
            class8.anIntArray160 = new int[1];
			class8.anInt174 = 105;//item look
			class8.anInt181 = 1957;
			class8.anInt190 = 498;
			class8.anInt198 = 484;
			class8.anInt204 = 0;
			class8.anInt169 = -1;
			class8.anInt194 = -1;
			class8.anInt165 = 106;
			class8.anInt200 = 106;
			class8.anInt175 = -1;
			class8.anInt197 = -1;
            class8.aString170 = "Legionary Godsword";
            class8.aByteArray178 = "A very sleek, destructive Godsword created by Legionaries.".getBytes();
        }	
if(i == 11034){
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = null;
class8.aString170 = "Ghost Claws";
class8.aByteArray178 = "Mother Of God.".getBytes();
class8.anIntArray156 = new int[0];
class8.anIntArray160 = new int[0];
class8.anInt174 = 2595;//Inv & Ground
class8.anInt181 = 1780;
class8.anInt190 = 320;
class8.anInt198 = 436;
class8.anInt204 = 0;
class8.anInt169 = -4;
class8.anInt194 = 12;
class8.anInt165 = -1;//Male Wield View
class8.anInt200 = -1;//Female Wield View
class8.anInt188 = -1;
class8.anInt164 = -1;
class8.anInt175 = -1;
class8.anInt197 = -1;
}		
      if(i == 15064)
        {
            class8.aStringArray189 = new String[5];
            class8.aStringArray189[1] = "Wear";
            class8.anIntArray156 = new int[2];
            class8.anIntArray160 = new int[2];
            class8.anIntArray156[0] = 926;
            class8.anIntArray160[0] = 10266;
            class8.anIntArray156[1] = 912;
            class8.anIntArray160[1] = 9523;
            class8.anInt181 = 1740;
            class8.anInt190 = 444;
            class8.anInt198 = 0;
            class8.anInt204 = 0;
            class8.anInt169 = 0;
            class8.anInt194 = -8;
            class8.anInt165 = 5024;
            class8.anInt200 = 5025;
            class8.anInt174 = 5026;
            class8.anInt188 = -1;
            class8.anInt164 = -1;
            class8.anInt175 = -1;
            class8.anInt197 = -1;
            class8.aString170 = "Antarix's PlateLegs";
            class8.aByteArray178 = "Forged by our Lord And Savior.".getBytes();
        }
if(i == 12510) // Your desired item id (the one you use after ::pickup ##### #)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear"; // String name, this can be changed to wield, or attach, or whatever you want
class8.anInt174 = 12511; // Drop/Inv Model
class8.anInt165 = 12510; // Male Wield Model
class8.anInt200 = 12510; // Female Wield
class8.anInt188 = -1; // Male arms/sleeves (Leave as -1 if not used)
class8.anInt164 = -1; // Female arms/sleeves (Leave as -1 if not used)
class8.anInt181 = 800; // Zoom - Increase to make inv model smaller
class8.anInt190 = 498; // Rotate up/down -  Increase to rotate upwards
class8.anInt198 = 1300; // Rotate right/left - Increase to rotate right
class8.anInt169 = -1; // Position in inv, increase to move right
class8.anInt194 = -1; // Position in inv, increase to move up
class8.aString170 = "Kronic Longsword"; // Item Name
class8.aByteArray178 = "A powerful long sword!".getBytes(); // Item Examine
      }		
if(i == 9114) {
		class8.aStringArray189 = new String[5];
		class8.aStringArray189[1] = "Wear";
		class8.anIntArray156 = new int[2];
		class8.anIntArray160 = new int [2];
		class8.anIntArray156[0] = 29456;
		class8.anIntArray160[0] = 0;
		class8.anIntArray156[1] = 127;
		class8.anIntArray160[1] = 7097;
	class8.anInt181 = 1000;
	class8.anInt190 = 0;
	class8.anInt198 = 300;
	class8.anInt204 = 0;
        class8.anInt169 = -14;
        class8.anInt194 = 27;
		class8.anInt174 = 1;
		class8.anInt200 = 1;
		class8.anInt165 = 1;
        class8.anInt175 = -1;
        class8.anInt197 = -1;
	class8.aString170 = "Briefcase";
	class8.aByteArray178 = "The Businessmen wear this.".getBytes();
}	  
if(i == 15960)
        {
            class8.aStringArray189 = new String[5];
            class8.aStringArray189[1] = "Wield";
            class8.anIntArray156 = new int[12];
            class8.anIntArray160 = new int[12];
            class8.anIntArray156[0] = 14503;
            class8.anIntArray160[0] = 926;
            class8.anIntArray156[1] = 14499;
            class8.anIntArray160[1] = 127;
            class8.anIntArray156[2] = 14494;
            class8.anIntArray160[2] = 926;
            class8.anIntArray156[3] = 14490;
            class8.anIntArray160[3] = 127;
            class8.anInt174 = 6590;
            class8.anInt181 = 1550;
            class8.anInt190 = 224;
            class8.anInt198 = 116;
            class8.anInt204 = 0;
            class8.anInt169 = -4;
            class8.anInt194 = -19;
            class8.anInt165 = 6706;
            class8.anInt200 = 6706;
            class8.anInt188 = -1;
            class8.anInt164 = -1;
            class8.anInt175 = -1;
            class8.anInt197 = -1;
            class8.aBoolean176 = false;
            class8.aString170 = "Candy Cane";
            class8.aByteArray178 = "So minty fresh".getBytes();
        }
if(i == 15403)
{
class8.aString170 = "Balmung";
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wield";
class8.anInt174 = 50855;
class8.anInt165 = 50939;
class8.anInt188 = -1;
class8.anInt202 = 6;
class8.aBoolean176 = false;
class8.anInt198 = 2004;
class8.anInt181 = 1710;
class8.anInt200 = 50939;
class8.anInt164 = -1;
class8.anInt169 = 0;
class8.anInt194 = 0;
class8.anInt190 = 280;
class8.aByteArray178 = "A Axe from the most legendary warriors.".getBytes();
}		
if(i == 13796)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wield";
class8.anIntArray156 = new int[6];
class8.anIntArray160 = new int[6];
class8.anIntArray156[0] = 6447;
class8.anIntArray160[0] = 8478;
class8.anIntArray156[1] = 6443;
class8.anIntArray160[1] = 8598;
class8.anIntArray156[2] = 6439;
class8.anIntArray160[2] = 8846;
class8.anIntArray156[3] = 5656;
class8.anIntArray160[3] = 41;
class8.anIntArray156[4] = 5652;
class8.anIntArray160[4] = 33;
class8.anIntArray156[5] = 5904;
class8.anIntArray160[5] = 24;
class8.anInt174 = 10128;
class8.anInt181 = 926;
class8.anInt190 = 432;
class8.anInt198 = 258;
class8.anInt169 = 3;
class8.anInt194 = 3;
class8.anInt204 = 83;
class8.anInt165 = 10129;
class8.anInt200 = 10130;
class8.aBoolean176 = false;
class8.aString170 = "Iron c'bow";
class8.aByteArray178 = "An Iron Crossbow.".getBytes();
}
if(i == 13795)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wield";
class8.anIntArray156 = new int[6];
class8.anIntArray160 = new int[6];
class8.anIntArray156[0] = 6447;
class8.anIntArray160[0] = 5425;
class8.anIntArray156[1] = 6443;
class8.anIntArray160[1] = 5545;
class8.anIntArray156[2] = 6439;
class8.anIntArray160[2] = 5665;
class8.anIntArray156[3] = 5656;
class8.anIntArray160[3] = -22064;
class8.anIntArray156[4] = 5652;
class8.anIntArray160[4] = -22072;
class8.anIntArray156[5] = 5904;
class8.anIntArray160[5] = -22208;
class8.anInt174 = 10128;
class8.anInt181 = 926;
class8.anInt190 = 432;
class8.anInt198 = 258;
class8.anInt169 = 3;
class8.anInt194 = 3;
class8.anInt204 = 83;
class8.anInt165 = 10129;
class8.anInt200 = 10130;
class8.aBoolean176 = false;
class8.aString170 = "Blurite c'bow";
class8.aByteArray178 = "A Blurite Crossbow.".getBytes();
}
if(i == 13794)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wield";
class8.anIntArray156 = new int[6];
class8.anIntArray160 = new int[6];
class8.anIntArray156[0] = 6447;
class8.anIntArray160[0] = 5425;
class8.anIntArray156[1] = 6443;
class8.anIntArray160[1] = 5545;
class8.anIntArray156[2] = 6439;
class8.anIntArray160[2] = 5665;
class8.anIntArray156[3] = 5656;
class8.anIntArray160[3] = 5652;
class8.anIntArray156[4] = 5652;
class8.anIntArray160[4] = 5652;
class8.anIntArray156[5] = 5904;
class8.anIntArray160[5] = 5652;
class8.anInt174 = 10128;
class8.anInt181 = 926;
class8.anInt190 = 432;
class8.anInt198 = 258;
class8.anInt169 = 3;
class8.anInt194 = 3;
class8.anInt204 = 83;
class8.anInt165 = 10129;
class8.anInt200 = 10130;
class8.aBoolean176 = false;
class8.aString170 = "Bronze c'bow";
class8.aByteArray178 = "A Bronze Crossbow.".getBytes();
}
if(i == 2550)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[1];
class8.anIntArray160 = new int[1];
class8.anIntArray156[0] = 127;
class8.anIntArray160[0] = 43951;
class8.anInt174 = 2677;
class8.anInt181 = 830;
class8.anInt190 = 322;
class8.anInt198 = 135;
class8.anInt204 = 2024;
class8.anInt169 = 3;
class8.anInt194 = 1;
class8.anInt175 = 29;
class8.aString170 = "Ring Of Recoil";
class8.aByteArray178 = "An Enchanted Ring".getBytes();
}
if(i == 2566)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[1];
class8.anIntArray160 = new int[1];
class8.anIntArray156[0] = 127;
class8.anIntArray160[0] = 22443;
class8.anInt174 = 2677;
class8.anInt181 = 830;
class8.anInt190 = 322;
class8.anInt198 = 135;
class8.anInt204 = 2024;
class8.anInt169 = 3;
class8.anInt194 = 1;
class8.anInt175 = 29;
class8.aString170 = "Ring of dueling(1)";
class8.aByteArray178 = "An enchanted ring.".getBytes();
}
if(i == 2564)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[1];
class8.anIntArray160 = new int[1];
class8.anIntArray156[0] = 127;
class8.anIntArray160[0] = 22443;
class8.anInt174 = 2677;
class8.anInt181 = 830;
class8.anInt190 = 322;
class8.anInt198 = 135;
class8.anInt204 = 2024;
class8.anInt169 = 3;
class8.anInt194 = 1;
class8.anInt175 = 29;
class8.aString170 = "Ring of dueling(2)";
class8.aByteArray178 = "An enchanted ring.".getBytes();
}
if(i == 2562)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[1];
class8.anIntArray160 = new int[1];
class8.anIntArray156[0] = 127;
class8.anIntArray160[0] = 22443;
class8.anInt174 = 2677;
class8.anInt181 = 830;
class8.anInt190 = 322;
class8.anInt198 = 135;
class8.anInt204 = 2024;
class8.anInt169 = 3;
class8.anInt194 = 1;
class8.anInt175 = 29;
class8.aString170 = "Ring of dueling(3)";
class8.aByteArray178 = "An enchanted ring.".getBytes();
}
if(i == 2560)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[1];
class8.anIntArray160 = new int[1];
class8.anIntArray156[0] = 127;
class8.anIntArray160[0] = 22443;
class8.anInt174 = 2677;
class8.anInt181 = 830;
class8.anInt190 = 322;
class8.anInt198 = 135;
class8.anInt204 = 2024;
class8.anInt169 = 3;
class8.anInt194 = 1;
class8.anInt175 = 29;
class8.aString170 = "Ring of dueling(4)";
class8.aByteArray178 = "An enchanted ring.".getBytes();
}
if(i == 2558)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[1];
class8.anIntArray160 = new int[1];
class8.anIntArray156[0] = 127;
class8.anIntArray160[0] = 22443;
class8.anInt174 = 2677;
class8.anInt181 = 830;
class8.anInt190 = 322;
class8.anInt198 = 135;
class8.anInt204 = 2024;
class8.anInt169 = 3;
class8.anInt194 = 1;
class8.anInt175 = 29;
class8.aString170 = "Ring of dueling(5)";
class8.aByteArray178 = "An enchanted ring.".getBytes();
}
if(i == 2556)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[1];
class8.anIntArray160 = new int[1];
class8.anIntArray156[0] = 127;
class8.anIntArray160[0] = 22443;
class8.anInt174 = 2677;
class8.anInt181 = 830;
class8.anInt190 = 322;
class8.anInt198 = 135;
class8.anInt204 = 2024;
class8.anInt169 = 3;
class8.anInt194 = 1;
class8.anInt175 = 29;
class8.aString170 = "Ring of dueling(6)";
class8.aByteArray178 = "An enchanted ring.".getBytes();
}
if(i == 2554)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[1];
class8.anIntArray160 = new int[1];
class8.anIntArray156[0] = 127;
class8.anIntArray160[0] = 22443;
class8.anInt174 = 2677;
class8.anInt181 = 830;
class8.anInt190 = 322;
class8.anInt198 = 135;
class8.anInt204 = 2024;
class8.anInt169 = 3;
class8.anInt194 = 1;
class8.anInt175 = 29;
class8.aString170 = "Ring of dueling(7)";
class8.aByteArray178 = "An enchanted ring.".getBytes();
}
if(i == 2552)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[1];
class8.anIntArray160 = new int[1];
class8.anIntArray156[0] = 127;
class8.anIntArray160[0] = 22443;
class8.anInt174 = 2677;
class8.anInt181 = 830;
class8.anInt190 = 322;
class8.anInt198 = 135;
class8.anInt204 = 2024;
class8.anInt169 = 3;
class8.anInt194 = 1;
class8.anInt175 = 29;
class8.aString170 = "Ring of dueling(8)";
class8.aByteArray178 = "An enchanted ring.".getBytes();
}
if(i == 2572)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[1];
class8.anIntArray160 = new int[1];
class8.anIntArray156[0] = 127;
class8.anIntArray160[0] = 51111;
class8.anInt174 = 2677;
class8.anInt181 = 830;
class8.anInt190 = 322;
class8.anInt198 = 135;
class8.anInt204 = 2024;
class8.anInt169 = 3;
class8.anInt194 = 1;
class8.anInt175 = 29;
class8.aString170 = "Ring of wealth";
class8.aByteArray178 = "An Enchanted Ring.".getBytes();
}
if(i == 2570)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[1];
class8.anIntArray160 = new int[1];
class8.anIntArray156[0] = 127;
class8.anIntArray160[0] = 127;
class8.anInt174 = 2677;
class8.anInt181 = 830;
class8.anInt190 = 322;
class8.anInt198 = 135;
class8.anInt204 = 2024;
class8.anInt169 = 3;
class8.anInt194 = 1;
class8.anInt175 = 29;
class8.aString170 = "Ring of life";
class8.aByteArray178 = "An Enchanted Ring.".getBytes();
}
if(i == 2568)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[1];
class8.anIntArray160 = new int[1];
class8.anIntArray156[0] = 127;
class8.anIntArray160[0] = 960;
class8.anInt174 = 2677;
class8.anInt181 = 830;
class8.anInt190 = 322;
class8.anInt198 = 135;
class8.anInt204 = 2024;
class8.anInt169 = 3;
class8.anInt194 = 1;
class8.anInt175 = 29;
class8.aString170 = "Ring Of Forging";
class8.aByteArray178 = "An Enchanted Ring.".getBytes();
}
if(i == 6583)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[2];
class8.anIntArray160 = new int[2];
class8.anIntArray156[0] = 127;
class8.anIntArray160[0] = 43528;
class8.anIntArray156[1] = 9152;
class8.anIntArray160[1] = 43045;
class8.anInt174 = 2677;
class8.anInt181 = 830;
class8.anInt190 = 322;
class8.anInt198 = 135;
class8.anInt204 = 2024;
class8.anInt169 = 3;
class8.anInt194 = 1;
class8.anInt175 = 29;
class8.aString170 = "Ring Of stone";
class8.aByteArray178 = "An Enchanted Ring.".getBytes();
}
if(i == 1635)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[1];
class8.anIntArray160 = new int[1];
class8.anIntArray156[0] = 127;
class8.anIntArray160[0] = 22443;
class8.anInt174 = 2677;
class8.anInt181 = 830;
class8.anInt190 = 322;
class8.anInt198 = 135;
class8.anInt204 = 2024;
class8.anInt169 = 3;
class8.anInt194 = 1;
class8.anInt175 = 29;
class8.aString170 = "Gold Ring";
class8.aByteArray178 = "A Valuable Ring.".getBytes();
}
if(i == 1639)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[1];
class8.anIntArray160 = new int[1];
class8.anIntArray156[0] = 127;
class8.anIntArray160[0] = 22443;
class8.anInt174 = 2677;
class8.anInt181 = 830;
class8.anInt190 = 322;
class8.anInt198 = 135;
class8.anInt204 = 2024;
class8.anInt169 = 3;
class8.anInt194 = 1;
class8.anInt175 = 29;
class8.aString170 = "Emerald Ring";
class8.aByteArray178 = "A Valuable Ring.".getBytes();
}
if(i == 1637)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[1];
class8.anIntArray160 = new int[1];
class8.anIntArray156[0] = 127;
class8.anIntArray160[0] = 43951;
class8.anInt174 = 2677;
class8.anInt181 = 830;
class8.anInt190 = 322;
class8.anInt198 = 135;
class8.anInt204 = 2024;
class8.anInt169 = 3;
class8.anInt194 = 1;
class8.anInt175 = 29;
class8.aString170 = "1Hit Ring";
class8.aByteArray178 = "A Ring Giving K0 Powers.".getBytes();
}
if(i == 1641)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[1];
class8.anIntArray160 = new int[1];
class8.anIntArray156[0] = 127;
class8.anIntArray160[0] = 960;
class8.anInt174 = 2677;
class8.anInt181 = 830;
class8.anInt190 = 322;
class8.anInt198 = 135;
class8.anInt204 = 2024;
class8.anInt169 = 3;
class8.anInt194 = 1;
class8.anInt175 = 29;
class8.aString170 = "Ruby Ring";
class8.aByteArray178 = "A Valuable Ring.".getBytes();
}
if(i == 1643)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[1];
class8.anIntArray160 = new int[1];
class8.anIntArray156[0] = 127;
class8.anIntArray160[0] = 127;
class8.anInt174 = 2677;
class8.anInt181 = 830;
class8.anInt190 = 322;
class8.anInt198 = 135;
class8.anInt204 = 2024;
class8.anInt169 = 3;
class8.anInt194 = 1;
class8.anInt175 = 29;
class8.aString170 = "Diamond Ring";
class8.aByteArray178 = "A Valuable Ring.".getBytes();
}
if(i == 1645)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[1];
class8.anIntArray160 = new int[1];
class8.anIntArray156[0] = 127;
class8.anIntArray160[0] = 51111;
class8.anInt174 = 2677;
class8.anInt181 = 830;
class8.anInt190 = 322;
class8.anInt198 = 135;
class8.anInt204 = 2024;
class8.anInt169 = 3;
class8.anInt194 = 1;
class8.anInt175 = 29;
class8.aString170 = "Dragonstone Ring";
class8.aByteArray178 = "A Valuable Ring.".getBytes();
}
if(i == 6564)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[2];
class8.anIntArray160 = new int[2];
class8.anIntArray156[0] = 127;
class8.anIntArray160[0] = 43528;
class8.anIntArray156[1] = 9152;
class8.anIntArray160[1] = 43045;
class8.anInt174 = 2677;
class8.anInt181 = 830;
class8.anInt190 = 322;
class8.anInt198 = 135;
class8.anInt204 = 2024;
class8.anInt169 = 3;
class8.anInt194 = 1;
class8.anInt175 = 29;
class8.aString170 = "Onyx ring";
class8.aByteArray178 = "A Valuable Ring.".getBytes();
}
if(i == 773)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[1];
class8.anIntArray160 = new int[1];
class8.anIntArray156[0] = 127;
class8.anIntArray160[0] = 960;
class8.anInt174 = 2677;
class8.anInt181 = 830;
class8.anInt190 = 322;
class8.anInt198 = 135;
class8.anInt204 = 2024;
class8.anInt169 = 3;
class8.anInt194 = 1;
class8.anInt175 = 29;
class8.aString170 = "InfHealth Ring";
class8.aByteArray178 = "Keeping Those Who Wear It Completely Healthy.".getBytes();
}
if(i == 13591)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wield";
class8.anIntArray156 = new int[2];
class8.anIntArray160 = new int[2];
class8.anInt174 = 10050;
class8.anInt181 = 919;
class8.anInt190 = 459;
class8.anInt198 = 6;
class8.anInt169 = 1;
class8.anInt175 = 56;
class8.anInt194 = 3;
class8.anInt197 = 116;
class8.anInt204 = 138;
class8.anInt165 = 10053;
class8.anInt200 = 10052;
class8.anInt188 = 10051;
class8.anInt164 = 10054;
class8.aBoolean176 = false;
class8.aString170 = "Void Knight Top";
class8.aByteArray178 = "Torso Armour of the Order Of The Void Knights.".getBytes();
}
if(i == 13592)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[4];
class8.anIntArray160 = new int[4];
class8.anInt174 = 10055;
class8.anInt181 = 2022;
class8.anInt190 = 408;
class8.anInt198 = 0;
class8.anInt169 = 1;
class8.anInt194 = 3;
class8.anInt188 = -1;
class8.anInt165 = 10057;
class8.anInt200 = 10056;
class8.aString170 = "Void Knight Robe";
class8.aByteArray178 = "Leg armour of the order of the Void Knights.".getBytes();
}
if(i == 13594)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[2];
class8.anIntArray160 = new int[2];
class8.anIntArray156[0] = 10004;
class8.anIntArray156[1] = 5532;
class8.anIntArray160[0] = 0;
class8.anIntArray160[1] = 0;
class8.anInt174 = 2649; //Model ID
class8.anInt165 = 179; //Male equip
class8.anInt200 = 13041; //Female equip
class8.anInt190 = 420;
class8.anInt198 = 828;
class8.anInt169 = 5;
class8.anInt194 = -8;
class8.anInt181 = 930;
class8.anInt204 = 138;
class8.anInt188 = -1;
class8.anInt164 = -1;
class8.anInt175 = -1;
class8.anInt197 = -1;
class8.aString170 = "Void Knight Gloves";
class8.aByteArray178 = "Gloves as used by the order of the void Knights.".getBytes();
}
if(i == 13593)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[1];
class8.anIntArray160 = new int[1];
class8.anIntArray156[0] = 0;
class8.anIntArray160[0] = 0;
class8.anInt174 = 13043;
class8.anInt181 = 1308;
class8.anInt190 = 402;
class8.anInt204 = 138;
class8.anInt194 = -10;
class8.anInt198 = 258;
class8.anInt165 = 13044;
class8.anInt188 = -1;
class8.anInt200 = 13045;
class8.anInt185 = 176;
class8.anInt162 = 353;
class8.anInt175 = 56;
class8.anInt197 = 116;
class8.anInt173 = 120;
class8.aBoolean176 = false;
class8.aString170 = "Void Knight Mace";
class8.aByteArray178 = "A mace used by the order of the Void Knights.".getBytes();
}
if(i == 15187)
{
class8.aStringArray189 = new String[5];
class8.anIntArray156 = new int[1];
class8.anIntArray160 = new int[1];
class8.anIntArray156[0] = 25238;
class8.anIntArray160[0] = -2608;
class8.anInt174 = 10069;
class8.anInt181 = 1697;
class8.anInt169 = -5;
class8.anInt190 = 567;
class8.anInt198 = 152;
class8.anInt204 = 2047;
class8.anInt194 = -5;
class8.anInt165 = -1;
class8.anInt200 = -1;
class8.aBoolean176 = false;
class8.aString170 = "Draconic Visage";
class8.aByteArray178 = "A Dragonic Visage.".getBytes();
}
if(i == 15185)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wield";
class8.anIntArray156 = new int[1];
class8.anIntArray160 = new int[1];
class8.anIntArray156[0] = -22390;
class8.anIntArray160[0] = 8;
class8.anInt174 = 10067;
class8.anInt181 = 2022;
class8.anInt190 = 540;
class8.anInt198 = 123;
class8.anInt169 = 1;
class8.anInt194 = 0;
class8.anInt204 = 2006;
class8.anInt165 = 10068;
class8.anInt200 = 10068;
class8.aBoolean176 = false;
class8.aString170 = "DragonFire Shield";
class8.aByteArray178 = "A Dragonfire Shield.".getBytes();
}

if(i == 6541) // change this if you need to "item number"
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[1];
class8.anIntArray160 = new int[1];
class8.anIntArray156[0] = 600;
class8.anIntArray160[0] = 927;
class8.anInt181 = 1400;//Zoom
class8.anInt190 = 0;//Rotation1
class8.anInt198 = 1400;//Rotaion2
class8.anInt169 = 60;//Offset1
class8.anInt194 = 78;//Offset2
class8.aString170 = "Angel Wings";
class8.aByteArray178 = "Sent From A Diety.".getBytes();
class8.anInt174 = 56;//Inv look
class8.anInt165 = 56;//Guy equip
class8.anInt200 = 56;//Girl equip
class8.anInt199 = -1;//Stackable
class8.anInt204 = 14;
class8.anInt175 = -1;//Male head
class8.anInt197 = -1;//Female head
} 

if(i == 6542) // change this if you need to "item number"
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[1];
class8.anIntArray160 = new int[1];
class8.anIntArray156[0] = 600;
class8.anIntArray160[0] = 927;
class8.anInt181 = 1400;//Zoom
class8.anInt190 = 0;//Rotation1
class8.anInt198 = 1400;//Rotaion2
class8.anInt169 = 60;//Offset1
class8.anInt194 = 78;//Offset2
class8.aString170 = "Angel Wings";
class8.aByteArray178 = "Sent From A Diety.".getBytes();
class8.anInt174 = 56;//Inv look
class8.anInt165 = 56;//Guy equip
class8.anInt200 = 56;//Girl equip
class8.anInt199 = -1;//Stackable
class8.anInt204 = 14;
class8.anInt175 = -1;//Male head
class8.anInt197 = -1;//Female head
} 
if(i == 14073) {
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[4];
class8.anIntArray160 = new int[4];
class8.anIntArray156 = new int[4];
class8.anIntArray160 = new int[4];
class8.anIntArray156[0] = 57280;
class8.anIntArray160[0] = 926;
class8.anIntArray156[1] = 54503;
class8.anIntArray160[1] = 926;
class8.anIntArray156[2] = 54183;
class8.anIntArray160[2] = 926;
class8.anIntArray156[3] = 11200;
class8.anIntArray160[3] = 926;
class8.anInt174 = 4158; //Inv & Ground
class8.anInt181 = 2000; //Zoom
class8.anInt190 = 572;
class8.anInt198 = 0;
class8.anInt204 = 0;
class8.anInt169 = 0;
class8.anInt194 = 1;
class8.anInt165 = 4181; //Male
class8.anInt200 = 7178; //Female
class8.anInt175 = -1;
class8.anInt197 = -1;
class8.anInt199 = -1;//noteable
class8.aString170 = "Attack Cape";
class8.aByteArray178 = "Its A attack Cape".getBytes();
}
if(i == 14074) {
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[4];
class8.anIntArray160 = new int[4];
class8.anIntArray156[0] = 57280;
class8.anIntArray156[1] = 54503;
class8.anIntArray156[2] = 54183;
class8.anIntArray156[3] = 11200;
class8.anIntArray160[0] = 9152;
class8.anIntArray160[1] = 7104;
class8.anIntArray160[2] = 7104;
class8.anIntArray160[3] = 796;
class8.anInt174 = 4158; //Inv & Ground
class8.anInt181 = 2000; //Zoom
class8.anInt190 = 572;
class8.anInt198 = 0;
class8.anInt204 = 0;
class8.anInt169 = 0;
class8.anInt194 = 1;
class8.anInt165 = 4181; //Male
class8.anInt200 = 7178; //Female
class8.anInt175 = -1;
class8.anInt197 = -1;
class8.anInt199 = -1;//noteable
class8.aString170 = "Attack Cape(t)";
class8.aByteArray178 = "Its A attack Cape(t)".getBytes();
}

if(i == 6570){
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[1];
class8.anIntArray160 = new int[1];
class8.anIntArray156[0] = 0;
class8.anIntArray160[0] = 0;
class8.anInt174 = 9631;//item look
class8.anInt181 = 2000;
class8.anInt190 = 400;
class8.anInt198 = 0;
class8.anInt204 = 2047;
class8.anInt169 = -8;
class8.anInt194 = 12;
class8.anInt197 = -1;
class8.aString170 = "Fire Cape";
class8.aByteArray178 = "A Very Sexy Fire Cape From Jad.".getBytes();
}

if(i == 15334){
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[1];
class8.anIntArray160 = new int[1];
class8.anIntArray156[0] = 0;
class8.anIntArray160[0] = 0;
class8.anInt174 = 7354;//item look
class8.anInt181 = 1957;
class8.anInt190 = 498;
class8.anInt198 = 484;
class8.anInt204 = 0;
class8.anInt169 = -1;
class8.anInt194 = -1;
class8.anInt165 = 7355;
class8.anInt200 = 7244;
class8.anInt175 = -1;
class8.anInt197 = -1;
class8.aString170 = "Bandos godsword ";
class8.aByteArray178 = "A GodSword From The Gods.".getBytes();
}
if(i == 3757)
        {
            class8.aStringArray189 = new String[5];
            class8.aStringArray189[1] = "Wear";
            class8.anIntArray156 = new int[1];
            class8.anIntArray160 = new int[1];
            class8.anIntArray156[0] = 600;
            class8.anIntArray160[0] = 600;
            class8.anInt174 = 188;
            class8.anInt181 = 1957;
            class8.anInt190 = 498;
            class8.anInt198 = 484;
            class8.anInt204 = 0;
            class8.anInt169 = -1;
            class8.anInt194 = -1;
            class8.anInt165 = 56;
            class8.anInt200 = 56;
            class8.anInt175 = -1;
            class8.anInt197 = -1;
            class8.aString170 = "Ultimanum Godsword";
            class8.aByteArray178 = "The Ultimanum Godsword, Based On Legends.".getBytes();
        }
if(i == 15350){
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[1];
class8.anIntArray160 = new int[1];
class8.anIntArray156[0] = 0;
class8.anIntArray160[0] = 0;
class8.anInt174 = 13422;//item look
class8.anInt181 = 724;
class8.anInt190 = 175;
class8.anInt198 = 2045;
class8.anInt204 = 144;
class8.anInt169 = 3;
class8.anInt194 = 3;
class8.anInt165 = 13423;
class8.anInt200 = 13423;
class8.anInt175 = 28259;
class8.anInt197 = 28260;
class8.aString170 = "Bandos Boots";
class8.aByteArray178 = "Bandos Boots, recovered from the Bandos Gods.".getBytes();
}
if(i == 15335)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[1];
class8.anIntArray160 = new int[1];
class8.anIntArray156[0] = 0;
class8.anIntArray160[0] = 0;
class8.anInt174 = 13426;
class8.anInt181 = 1957;
class8.anInt190 = 498;
class8.anInt198 = 484;
class8.anInt204 = 0;
class8.anInt169 = -1;
class8.anInt194 = -1;
class8.anInt165 = 13427;
class8.anInt200 = 7295;
class8.anInt175 = -1;
class8.anInt197 = -1;
class8.aString170 = "Saradomin Godsword";
class8.aByteArray178 = "A Saradomin Godsword, Very Mighty.".getBytes();
}

if(i == 15333)  // change this if you need to "item number"
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wield";//New option
class8.anIntArray156 = new int[0];
class8.anIntArray160 = new int[0];
class8.anInt174 = 7212; //Model ID
class8.anInt181 = 1957;
class8.anInt190 = 498;
class8.anInt198 = 484;
class8.anInt204 = 0;
class8.anInt169 = -1;
class8.anInt194 = -1;
class8.anInt175 = -1;
class8.anInt197 = -1;
class8.anInt165 = 7209;//male wearing
class8.anInt200 = 7209;//female wearing
class8.aBoolean176 = false;//Stackable
class8.aString170 = "Armadyl Godsword";//Name of the new item
class8.aByteArray178 = "A Sword Of The Armadyl God.".getBytes();//examin info
}
if(i == 15336)  // change this if you need to "item number"
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wield";//New option
class8.anIntArray156 = new int[0];
class8.anIntArray160 = new int[0];
class8.anInt174 = 7213; //Model ID
class8.anInt181 = 1957;
class8.anInt190 = 498;
class8.anInt198 = 484;
class8.anInt204 = 0;
class8.anInt169 = -1;
class8.anInt194 = -1;
class8.anInt175 = -1;
class8.anInt197 = -1;
class8.anInt165 = 7206;//male wearing
class8.anInt200 = 7206;//female wearing
class8.aBoolean176 = false;//Stackable
class8.aString170 = "Zamorak Godsword";//Name of the new item
class8.aByteArray178 = "A Sword Salvaged From Zamarok Warriors.".getBytes();//examin info
}
if(i == 15234) // change this if you need to "item number"
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wield";
class8.anIntArray156 = new int[1];
class8.anIntArray160 = new int[1];
class8.anIntArray156[0] = 28;
class8.anIntArray156[0] = 74;
class8.anIntArray160[0] = 38676;
class8.anIntArray160[0] = 760;
class8.anInt174 = 5139;
class8.anInt181 = 490;
class8.anInt190 = 344;
class8.anInt198 = 192;
class8.anInt204 = 138;
class8.anInt169 = 1;
class8.anInt194 = 20;
class8.anInt165 = 5114;//male wearing 15413
class8.anInt200 = 5114;//female wearing 15413
class8.anInt175 = 56;
class8.anInt197 = 116;
class8.aString170 = "Slayer Sword";
class8.aByteArray178 = "A Sword From The Slayer Masters.".getBytes();
}
if(i == 14075)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[3];
class8.anIntArray160 = new int[3];
class8.anIntArray156[0] = 960;
class8.anIntArray160[0] = 912;
class8.anIntArray156[1] = 22464;
class8.anIntArray160[1] = 912;
class8.anIntArray156[2] = 43968;
class8.anIntArray160[2] = 912;
class8.anInt174 = 4203; //Model ID
class8.anInt181 = 720; //zoom increase will make it smaller
class8.anInt190 = 0; //model rotate up+down increase to move doen away from you
class8.anInt198 = 0; //model rotate side ways increase to move right in circle
class8.anInt169 = 0; // model offset increase to move to the right
class8.anInt194 = 0; //model offset increase to move up
class8.anInt165 = 4202;//male wearing
class8.anInt200 = 4202;//female wearing
class8.aString170 = "Attack Hood";
class8.aByteArray178 = "Its A Attack Hood".getBytes();
}
if(i == 15150) {
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[3];
class8.anIntArray160 = new int [3];
class8.anIntArray156[0] = 0xD4E7;
class8.anIntArray156[1] = 0xDFC0;
class8.anIntArray156[2] = 0xD3A7;
class8.anIntArray160[0] = 8128;
class8.anIntArray160[1] = 9126;
class8.anIntArray160[2] = 8128;
class8.anInt174 = 7022; //Inv & Ground
class8.anInt181 = 2000; //Zoom
class8.anInt190 = 572;
class8.anInt198 = 0;
class8.anInt204 = 0;
class8.anInt169 = 0;
class8.anInt194 = 1;
class8.anInt165 = 7021; //Male
class8.anInt200 = 7021; //Female
class8.anInt175 = -1;
class8.anInt197 = -1;
class8.aString170 = "Summoning Cape";
class8.aByteArray178 = "A Very Stylish Cape.".getBytes();
}
if(i == 14568) //Replace with the id you want
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wield";//New option
class8.anInt174 = 6944; //Model ID
class8.anInt181 = 1050; //1200 zoom increase will make it smaller
class8.anInt190 = 572; //model rotate up+down increase to move doen away from you
class8.anInt198 = 0; //model rotate side ways increase to move right in circle
class8.anInt169 = 0; // model offset increase to move to the right
class8.anInt194 = 1; //model offset increase to move up
class8.anInt165 = 6940;//male wearing
class8.anInt200 = 6943;//female wearing
class8.anInt164 = 6942;//Female arms/sleeves
class8.anInt188 = 6941;//male arms/sleeves
class8.aBoolean176 = false;//Stackable
class8.aString170 = "Zamorak Robe top";//Name of the new item
class8.aByteArray178 = "A Robe Top Worn By the Gods.".getBytes();//examin info
}
if(i == 14572) //Replace with the id you want
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wield";//New option
class8.anInt174 = 6947; //Model ID
class8.anInt181 = 1800; //1690 zoom increase will make it smaller
class8.anInt190 = 408; //model rotate up+down increase to move doen away from you
class8.anInt198 = 0; //2024 model rotate side ways increase to move right in circle
class8.anInt169 = 5; // model offset increase to move to the right
class8.anInt194 = 0; //model offset increase to move up
class8.anInt165 = 6945;//male wearing
class8.anInt200 = 6946;//female wearing
class8.aBoolean176 = false;//Stackable
class8.aString170 = "Zamorak Robe Bottom";//Name of the new item
class8.aByteArray178 = "A Robe Bottom Worn By the Gods.".getBytes();//examin info
}
if(i == 14566) //Replace with the id you want
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wield";//New option
class8.anInt174 = 6948; //Model ID
class8.anInt181 = 600; //980 zoom increase will make it smaller
class8.anInt190 = 208; //model rotate up+down increase to move doen away from you
class8.anInt198 = 220; //model rotate side ways increase to move right in circle
class8.anInt169 = 0; // model offset increase to move to the right
class8.anInt194 = -18; //model offset increase to move up
class8.anInt165 = 6949;//male wearing
class8.anInt200 = 6950;//female wearing
class8.aBoolean176 = false;//Stackable
class8.aString170 = "Zamorak Mitre";//Name of the new item
class8.aByteArray178 = "A Hat Worn By the Gods.".getBytes();//examin info
}
if(i == 14563) //Replace with the id you want
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wield";//New option
class8.anInt174 = 6953; //Model ID
class8.anInt181 = 1980; //zoom increase will make it smaller
class8.anInt190 = 572; //model rotate up+down increase to move doen away from you
class8.anInt198 = 1000; //model rotate side ways increase to move right in circle
class8.anInt169 = 0; // model offset increase to move to the right
class8.anInt194 = 0; //model offset increase to move up
class8.anInt165 = 6951;//male wearing
class8.anInt200 = 6952;//female wearing
class8.aBoolean176 = false;//Stackable
class8.aString170 = "Zamorak Cape";//Name of the new item
class8.aByteArray178 = "A Cape Worn By the Gods.".getBytes();//examin info
}
if(i == 14560) //Replace with the id you want
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wield";//New option
class8.anInt174 = 6954; //Model ID
class8.anInt181 = 2000; //1200 zoom increase will make it smaller
class8.anInt190 = 572; //model rotate up+down increase to move doen away from you
class8.anInt198 = 0; //model rotate side ways increase to move right in circle
class8.anInt169 = 0; // model offset increase to move to the right
class8.anInt194 = 1; //model offset increase to move up
class8.anInt165 = 6955;//male wearing
class8.anInt200 = 6955;//female wearing
class8.aBoolean176 = false;//Stackable
class8.aString170 = "Zamorak Crozier";//Name of the new item
class8.aByteArray178 = "A Staff Used By the Gods.".getBytes();//examin info
}
if(i == 14575) //Replace with the id you want
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wield";//New option
class8.anInt174 = 6956; //Model ID
class8.anInt181 = 1700; //1200 zoom increase will make it smaller
class8.anInt190 = 490; //572 model rotate up+down increase to move doen away from you
class8.anInt198 = 60; //0 model rotate side ways increase to move right in circle
class8.anInt169 = 0; // model offset increase to move to the right
class8.anInt194 = -10; //model offset increase to move up
class8.anInt165 = 6982;//male wearing
class8.anInt200 = 6984;//female wearing
class8.aBoolean176 = false;//Stackable
class8.aString170 = "Zamorak Stole";//Name of the new item
class8.aByteArray178 = "A Stole Worn By the Gods.".getBytes();//examin info
}
if(i == 14520) //Replace with the id you want
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wield";//New option
class8.anInt174 = 6983; //Model ID
class8.anInt181 = 620; //620 zoom increase will make it smaller
class8.anInt190 = 424; //424 model rotate up+down increase to move doen away from you
class8.anInt198 = 8; //model rotate side ways increase to move right in circle
class8.anInt169 = 1; // model offset increase to move to the right
class8.anInt194 = 16; //model offset increase to move up
class8.anInt165 = 6986;//male wearing
class8.anInt200 = 6987;//female wearing
class8.anInt175 = -1;
class8.anInt197 = -1;
class8.aBoolean176 = false;//Stackable
class8.aString170 = "Amulet of Strength (t)";//Name of the new item
class8.aByteArray178 = "An Amulet That Enhances Physical Ability.".getBytes();//examin info
}
if(i == 14076)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[6];
class8.anIntArray160 = new int[6];
class8.anIntArray156 = new int[6];
class8.anIntArray160 = new int[6];
class8.anIntArray156[0] = 57280;
class8.anIntArray156[1] = 54503;
class8.anIntArray156[2] = 54183;
class8.anIntArray160[0] = 22424;
class8.anIntArray160[1] = 22424;
class8.anIntArray160[2] = 22424;
class8.anInt174 = 4177; //Inv & Ground
class8.anInt181 = 2000; //Zoom
class8.anInt190 = 572;
class8.anInt198 = 0;
class8.anInt204 = 0;
class8.anInt169 = 0;
class8.anInt194 = 1;
class8.anInt165 = 4199; //Male
class8.anInt200 = 7197; //Female
class8.anInt175 = -1;
class8.anInt197 = -1;
class8.aString170 = "Strength Cape";
class8.aByteArray178 = "Its A Strength Cape".getBytes();
}
if(i == 14077) {
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[6];
class8.anIntArray160 = new int[6];
class8.anIntArray156 = new int[6];
class8.anIntArray160 = new int[6];
class8.anIntArray156[0] = 8256;
class8.anIntArray160[0] = 926;
class8.anIntArray156[1] = 54183;
class8.anIntArray160[1] = 926;
class8.anIntArray156[2] = 57280;
class8.anIntArray160[2] = 926;
class8.anIntArray156[3] = 960;
class8.anIntArray160[3] = 926;
class8.anIntArray156[4] = 22464;
class8.anIntArray160[4] = 926;
class8.anIntArray156[5] = 21568;
class8.anIntArray160[5] = 926;
class8.anInt174 = 4177; //Inv & Ground
class8.anInt181 = 2000; //Zoom
class8.anInt190 = 572;
class8.anInt198 = 0;
class8.anInt204 = 0;
class8.anInt169 = 0;
class8.anInt194 = 1;
class8.anInt165 = 4199; //Male
class8.anInt200 = 7197; //Female
class8.anInt175 = -1;
class8.anInt197 = -1;
class8.aString170 = "Strength Cape(t)";
class8.aByteArray178 = "Its A Strenght Cape(t)".getBytes();
}
if(i == 14078)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[3];
class8.anIntArray160 = new int[3];
class8.anIntArray156[0] = 960;
class8.anIntArray156[1] = 22464;
class8.anIntArray156[2] = 43968;
class8.anIntArray160[0] = 22424;
class8.anIntArray160[1] = 22424;
class8.anIntArray160[2] = 22424;
class8.anInt174 = 4203; //Model ID
class8.anInt181 = 720; //zoom increase will make it smaller
class8.anInt190 = 0; //model rotate up+down increase to move doen away from you
class8.anInt198 = 0; //model rotate side ways increase to move right in circle
class8.anInt169 = 0; // model offset increase to move to the right
class8.anInt194 = 0; //model offset increase to move up
class8.anInt165 = 4202;//male wearing
class8.anInt200 = 4202;//female wearing
class8.aString170 = "Strength Hood";
class8.aByteArray178 = "Its A Strength Hood".getBytes();
}
if(i == 14079) {
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[4];
class8.anIntArray160 = new int[4];
class8.anIntArray156[0] = 57280;
class8.anIntArray160[0] = 41416;
class8.anIntArray156[1] = 54503;
class8.anIntArray160[1] = 41416;
class8.anIntArray156[2] = 54183;
class8.anIntArray160[2] = 41416;
class8.anIntArray156[3] = 127;
class8.anIntArray160[3] = 41416;
class8.anInt174 = 4162; //Inv & Ground
class8.anInt181 = 2000; //Zoom
class8.anInt190 = 572;
class8.anInt198 = 0;
class8.anInt204 = 0;
class8.anInt169 = 0;
class8.anInt194 = 1;
class8.anInt165 = 4184; //Male
class8.anInt200 = 7182; //Female
class8.anInt175 = -1;
class8.anInt197 = -1;
class8.anInt199 = -1;//noteable
class8.aString170 = "Defence Cape";
class8.aByteArray178 = "Its A Defence Cape".getBytes();
}
if(i == 14080) {
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[3];
class8.anIntArray160 = new int[3];
class8.anIntArray156[0] = 57280;
class8.anIntArray160[0] = 107;
class8.anIntArray156[1] = 54503;
class8.anIntArray160[1] = 107;
class8.anIntArray156[2] = 54183;
class8.anIntArray160[2] = 107;
class8.anInt174 = 4162; //Inv & Ground
class8.anInt181 = 2000; //Zoom
class8.anInt190 = 572;
class8.anInt198 = 0;
class8.anInt204 = 0;
class8.anInt169 = 0;
class8.anInt194 = 1;
class8.anInt165 = 4184; //Male
class8.anInt200 = 7182; //Female
class8.anInt175 = -1;
class8.anInt197 = -1;
class8.anInt199 = -1;//noteable
class8.aString170 = "Defence Cape(t)";
class8.aByteArray178 = "Its A Defence Cape(t)".getBytes();
}
if(i == 14081)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[3];
class8.anIntArray160 = new int[3];
class8.anIntArray156[0] = 960;
class8.anIntArray160[0] = 41416;
class8.anIntArray156[1] = 22464;
class8.anIntArray160[1] = 41416;
class8.anIntArray156[2] = 43968;
class8.anIntArray160[2] = 41416;
class8.anInt174 = 4203; //Model ID
class8.anInt181 = 720; //zoom increase will make it smaller
class8.anInt190 = 0; //model rotate up+down increase to move doen away from you
class8.anInt198 = 0; //model rotate side ways increase to move right in circle
class8.anInt169 = 0; // model offset increase to move to the right
class8.anInt194 = 0; //model offset increase to move up
class8.anInt165 = 4202;//male wearing
class8.anInt200 = 4202;//female wearing
class8.aString170 = "Defence Hood";
class8.aByteArray178 = "Its A Defence Hood".getBytes();
}
if(i == 14082) {
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[6];
class8.anIntArray160 = new int[6];
class8.anIntArray156 = new int[3];
class8.anIntArray160 = new int[3];
class8.anIntArray156[0] = 57280;
class8.anIntArray156[1] = 54503;
class8.anIntArray156[2] = 54183;
class8.anIntArray160[0] = 13214;
class8.anIntArray160[1] = 13214;
class8.anIntArray160[2] = 13214;
class8.anInt174 = 4173; //Inv & Ground
class8.anInt181 = 2000; //Zoom
class8.anInt190 = 572;
class8.anInt198 = 0;
class8.anInt204 = 0;
class8.anInt169 = 0;
class8.anInt194 = 1;
class8.anInt165 = 4195; //Male
class8.anInt200 = 7193; //Female
class8.anInt175 = -1;
class8.anInt197 = -1;
class8.anInt199 = -1;//noteable
class8.aString170 = "Ranging Cape";
class8.aByteArray178 = "Its A Ranging Cape".getBytes();
}
if(i == 14083) {
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[3];
class8.anIntArray160 = new int[3];
class8.anIntArray156[0] = 57280;
class8.anIntArray156[1] = 54503;
class8.anIntArray156[2] = 54183;
class8.anIntArray160[0] = 8111;
class8.anIntArray160[1] = 8111;
class8.anIntArray160[2] = 8111;
class8.anInt174 = 4173; //Inv & Ground
class8.anInt181 = 2000; //Zoom
class8.anInt190 = 572;
class8.anInt198 = 0;
class8.anInt204 = 0;
class8.anInt169 = 0;
class8.anInt194 = 1;
class8.anInt165 = 4195; //Male
class8.anInt200 = 7193; //Female
class8.anInt175 = -1;
class8.anInt197 = -1;
class8.anInt199 = -1;//noteable
class8.aString170 = "Ranging Cape(t)";
class8.aByteArray178 = "Its A Ranging Cape(t)".getBytes();
}
if(i == 14084)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[3];
class8.anIntArray160 = new int[3];
class8.anIntArray156[0] = 960;
class8.anIntArray156[1] = 22464;
class8.anIntArray156[2] = 43968;
class8.anIntArray160[0] = 13214;
class8.anIntArray160[1] = 13214;
class8.anIntArray160[2] = 13214;
class8.anInt174 = 4203; //Model ID
class8.anInt181 = 720; //zoom increase will make it smaller
class8.anInt190 = 0; //model rotate up+down increase to move doen away from you
class8.anInt198 = 0; //model rotate side ways increase to move right in circle
class8.anInt169 = 0; // model offset increase to move to the right
class8.anInt194 = 0; //model offset increase to move up
class8.anInt165 = 4202;//male wearing
class8.anInt200 = 4202;//female wearing
class8.aString170 = "Range Hood";
class8.aByteArray178 = "A Range Hood".getBytes();
}
if(i == 14085) {
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[4];
class8.anIntArray160 = new int[4];
class8.anIntArray156[0] = 57280;
class8.anIntArray156[1] = 54503;
class8.anIntArray156[2] = 54183;
class8.anIntArray156[3] = 11200;
class8.anIntArray160[0] = 107;
class8.anIntArray160[1] = 107;
class8.anIntArray160[2] = 107;
class8.anIntArray160[3] = 107;
class8.anInt174 = 4171; //Inv & Ground
class8.anInt181 = 2000; //Zoom
class8.anInt190 = 572;
class8.anInt198 = 0;
class8.anInt204 = 0;
class8.anInt169 = 0;
class8.anInt194 = 1;
class8.anInt165 = 4193; //Male
class8.anInt200 = 7191; //Female
class8.anInt175 = -1;
class8.anInt197 = -1;
class8.aString170 = "Prayer Cape";
class8.aByteArray178 = "Its A Prayer Cape".getBytes();
}
if(i == 14086) {
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[4];
class8.anIntArray160 = new int[4];
class8.anIntArray156[0] = 57280;
class8.anIntArray156[1] = 54503;
class8.anIntArray156[2] = 54183;
class8.anIntArray156[3] = 11200;
class8.anIntArray160[0] = 9152;
class8.anIntArray160[1] = 7104;
class8.anIntArray160[2] = 7104;
class8.anIntArray160[3] = 796;
class8.anInt174 = 4171; //Inv & Ground
class8.anInt181 = 2000; //Zoom
class8.anInt190 = 572;
class8.anInt198 = 0;
class8.anInt204 = 0;
class8.anInt169 = 0;
class8.anInt194 = 1;
class8.anInt165 = 4193; //Male
class8.anInt200 = 7191; //Female
class8.anInt175 = -1;
class8.anInt197 = -1;
class8.anInt199 = -1;//noteable
class8.aString170 = "Prayer Cape(t)";
class8.aByteArray178 = "Its A Prayer Cape(t)".getBytes();
}
if(i == 14087)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[3];
class8.anIntArray160 = new int[3];
class8.anIntArray156[0] = 960;
class8.anIntArray156[1] = 22464;
class8.anIntArray156[2] = 43968;
class8.anIntArray160[0] = 107;
class8.anIntArray160[1] = 107;
class8.anIntArray160[2] = 107;
class8.anInt174 = 4203; //Model ID
class8.anInt181 = 720; //zoom increase will make it smaller
class8.anInt190 = 0; //model rotate up+down increase to move doen away from you
class8.anInt198 = 0; //model rotate side ways increase to move right in circle
class8.anInt169 = 0; // model offset increase to move to the right
class8.anInt194 = 0; //model offset increase to move up
class8.anInt165 = 4202;//male wearing
class8.anInt200 = 4202;//female wearing
class8.aString170 = "Prayer Hood";
class8.aByteArray178 = "A Prayer Hood".getBytes();
}
if(i == 14088) {
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[4];
class8.anIntArray160 = new int[4];
class8.anIntArray156[0] = 57280;
class8.anIntArray156[1] = 54503;
class8.anIntArray156[2] = 54183;
class8.anIntArray156[3] = 11200;
class8.anIntArray160[0] = 6348;
class8.anIntArray160[1] = 6340;
class8.anIntArray160[2] = 6331;
class8.anIntArray160[3] = 6331;
class8.anInt174 = 4169; //Inv & Ground
class8.anInt181 = 2000; //Zoom
class8.anInt190 = 572;
class8.anInt198 = 0;
class8.anInt204 = 0;
class8.anInt169 = 0;
class8.anInt194 = 1;
class8.anInt165 = 4191; //Male
class8.anInt200 = 7189; //Female
class8.anInt175 = -1;
class8.anInt197 = -1;
class8.anInt199 = -1;//noteable
class8.aString170 = "Magic Cape";
class8.aByteArray178 = "Its A magic Cape".getBytes();
}
if(i == 14089) {
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[4];
class8.anIntArray160 = new int[4];
class8.anIntArray156[0] = 57280;
class8.anIntArray156[1] = 54503;
class8.anIntArray156[2] = 54183;
class8.anIntArray156[3] = 11200;
class8.anIntArray160[0] = 43934;
class8.anIntArray160[1] = 43934;
class8.anIntArray160[2] = 43934;
class8.anIntArray160[3] = 43934;
class8.anInt174 = 4169; //Inv & Ground
class8.anInt181 = 2000; //Zoom
class8.anInt190 = 572;
class8.anInt198 = 0;
class8.anInt204 = 0;
class8.anInt169 = 0;
class8.anInt194 = 1;
class8.anInt165 = 4191; //Male
class8.anInt200 = 7189; //Female
class8.anInt175 = -1;
class8.anInt197 = -1;
class8.anInt199 = -1;//noteable
class8.aString170 = "Magic Cape(t)";
class8.aByteArray178 = "Its A magic Cape(t)".getBytes();
}
if(i == 14090)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[3];
class8.anIntArray160 = new int[3];
class8.anIntArray156[0] = 960;
class8.anIntArray156[1] = 22464;
class8.anIntArray156[2] = 43968;
class8.anIntArray160[0] = 6348;
class8.anIntArray160[1] = 6340;
class8.anIntArray160[2] = 6331;
class8.anInt174 = 4203; //Model ID
class8.anInt181 = 720; //zoom increase will make it smaller
class8.anInt190 = 0; //model rotate up+down increase to move doen away from you
class8.anInt198 = 0; //model rotate side ways increase to move right in circle
class8.anInt169 = 0; // model offset increase to move to the right
class8.anInt194 = 0; //model offset increase to move up
class8.anInt165 = 4202;//male wearing
class8.anInt200 = 4202;//female wearing
class8.aString170 = "Magic Hood";
class8.aByteArray178 = "A Magic Hood".getBytes();
}
if(i == 14130) {
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[4];
class8.anIntArray160 = new int[4];
class8.anIntArray156[0] = 57280;
class8.anIntArray156[1] = 54503;
class8.anIntArray156[2] = 54183;
class8.anIntArray156[3] = 11200;
class8.anIntArray160[0] = 6073;
class8.anIntArray160[1] = 6073;
class8.anIntArray160[2] = 6073;
class8.anIntArray160[3] = 6073;
class8.anInt174 = 4159; //Inv & Ground
class8.anInt181 = 2000; //Zoom
class8.anInt190 = 572;
class8.anInt198 = 0;
class8.anInt204 = 0;
class8.anInt169 = 0;
class8.anInt194 = 1;
class8.anInt165 = 4186; //Male
class8.anInt200 = 7184; //Female
class8.anInt175 = -1;
class8.anInt197 = -1;
class8.anInt199 = -1;//noteable
class8.aString170 = "Firemaking Cape";
class8.aByteArray178 = "Its A firemaking Cape(t)".getBytes();
}
if(i == 14131) {
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[4];
class8.anIntArray160 = new int[4];
class8.anIntArray156[0] = 57280;
class8.anIntArray156[1] = 54503;
class8.anIntArray156[2] = 54183;
class8.anIntArray156[3] = 11200;
class8.anIntArray160[0] = 9152;
class8.anIntArray160[1] = 7104;
class8.anIntArray160[2] = 7104;
class8.anIntArray160[3] = 796;
class8.anInt174 = 4159; //Inv & Ground
class8.anInt181 = 2000; //Zoom
class8.anInt190 = 572;
class8.anInt198 = 0;
class8.anInt204 = 0;
class8.anInt169 = 0;
class8.anInt194 = 1;
class8.anInt165 = 4186; //Male
class8.anInt200 = 7184; //Female
class8.anInt175 = -1;
class8.anInt197 = -1;
class8.anInt199 = -1;//noteable
class8.aString170 = "Firemaking Cape(t)";
class8.aByteArray178 = "Its A firemaking Cape(t)".getBytes();
}
if(i == 14132)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[3];
class8.anIntArray160 = new int[3];
class8.anIntArray156[0] = 960;
class8.anIntArray156[1] = 22464;
class8.anIntArray156[2] = 43968;
class8.anIntArray160[0] = 6073;
class8.anIntArray160[1] = 6073;
class8.anIntArray160[2] = 6073;
class8.anInt174 = 4203; //Model ID
class8.anInt181 = 720; //zoom increase will make it smaller
class8.anInt190 = 0; //model rotate up+down increase to move doen away from you
class8.anInt198 = 0; //model rotate side ways increase to move right in circle
class8.anInt169 = 0; // model offset increase to move to the right
class8.anInt194 = 0; //model offset increase to move up
class8.anInt165 = 4202;//male wearing
class8.anInt200 = 4202;//female wearing
class8.aString170 = "FireMaking Hood";
class8.aByteArray178 = "Its A FireMaking Hood".getBytes();
}
if(i == 14091) {
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[4];
class8.anIntArray160 = new int[4];
class8.anIntArray156[0] = 57280;
class8.anIntArray156[1] = 54503;
class8.anIntArray156[2] = 54183;
class8.anIntArray156[3] = 11200;
class8.anIntArray160[0] = 99;
class8.anIntArray160[1] = 99;
class8.anIntArray160[2] = 99;
class8.anIntArray160[3] = 99;
class8.anInt174 = 4164; //Inv & Ground
class8.anInt181 = 2000; //Zoom
class8.anInt190 = 572;
class8.anInt198 = 0;
class8.anInt204 = 0;
class8.anInt169 = 0;
class8.anInt194 = 1;
class8.anInt165 = 4196; //Male
class8.anInt200 = 7194; //Female
class8.anInt175 = -1;
class8.anInt197 = -1;
class8.anInt199 = -1;//noteable
class8.aString170 = "RuneCrafting Cape";
class8.aByteArray178 = "Its A firemaking Cape(t)".getBytes();
}
if(i == 14092) {
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[4];
class8.anIntArray160 = new int[4];
class8.anIntArray156[0] = 57280;
class8.anIntArray156[1] = 54503;
class8.anIntArray156[2] = 54183;
class8.anIntArray156[3] = 11200;
class8.anIntArray160[0] = 9152;
class8.anIntArray160[1] = 7104;
class8.anIntArray160[2] = 7104;
class8.anIntArray160[3] = 796;
class8.anInt174 = 4164; //Inv & Ground
class8.anInt181 = 2000; //Zoom
class8.anInt190 = 572;
class8.anInt198 = 0;
class8.anInt204 = 0;
class8.anInt169 = 0;
class8.anInt194 = 1;
class8.anInt165 = 4196; //Male
class8.anInt200 = 7194; //Female
class8.anInt175 = -1;
class8.anInt197 = -1;
class8.anInt199 = -1;//noteable
class8.aString170 = "RuneCrafting Cape(t)";
class8.aByteArray178 = "Its A firemaking Cape(t)".getBytes();
}
if(i == 14093)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[3];
class8.anIntArray160 = new int[3];
class8.anIntArray156[0] = 960;
class8.anIntArray156[1] = 22464;
class8.anIntArray156[2] = 43968;
class8.anIntArray160[0] = 99;
class8.anIntArray160[1] = 99;
class8.anIntArray160[2] = 99;
class8.anInt174 = 4203; //Model ID
class8.anInt181 = 720; //zoom increase will make it smaller
class8.anInt190 = 0; //model rotate up+down increase to move doen away from you
class8.anInt198 = 0; //model rotate side ways increase to move right in circle
class8.anInt169 = 0; // model offset increase to move to the right
class8.anInt194 = 0; //model offset increase to move up
class8.anInt165 = 4202;//male wearing
class8.anInt200 = 4202;//female wearing
class8.aString170 = "RuneCrafting Hood";
class8.aByteArray178 = "Its A RuneCrafting Hood".getBytes();
}
if(i == 14094) {
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[4];
class8.anIntArray160 = new int[4];
class8.anIntArray156[0] = 57280;
class8.anIntArray156[1] = 54503;
class8.anIntArray156[2] = 54183;
class8.anIntArray156[3] = 11200;
class8.anIntArray160[0] = 99;
class8.anIntArray160[1] = 99;
class8.anIntArray160[2] = 99;
class8.anIntArray160[3] = 99;
class8.anInt174 = 4168; //Inv & Ground
class8.anInt181 = 2000; //Zoom
class8.anInt190 = 572;
class8.anInt198 = 0;
class8.anInt204 = 0;
class8.anInt169 = 0;
class8.anInt194 = 1;
class8.anInt165 = 4190; //Male
class8.anInt200 = 7188; //Female
class8.anInt175 = -1;
class8.anInt197 = -1;
class8.anInt199 = -1;//noteable
class8.aString170 = "Hitpoint Cape";
class8.aByteArray178 = "Its A Hitpoint Cape".getBytes();
}
if(i == 14095) {
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[4];
class8.anIntArray160 = new int[4];
class8.anIntArray156[0] = 57280;
class8.anIntArray156[1] = 54503;
class8.anIntArray156[2] = 54183;
class8.anIntArray156[3] = 11200;
class8.anIntArray160[0] = 926;
class8.anIntArray160[1] = 926;
class8.anIntArray160[2] = 926;
class8.anIntArray160[3] = 926;
class8.anInt174 = 4168; //Inv & Ground
class8.anInt181 = 2000; //Zoom
class8.anInt190 = 572;
class8.anInt198 = 0;
class8.anInt204 = 0;
class8.anInt169 = 0;
class8.anInt194 = 1;
class8.anInt165 = 4190; //Male
class8.anInt200 = 7188; //Female
class8.anInt175 = -1;
class8.anInt197 = -1;
class8.anInt199 = -1;//noteable
class8.aString170 = "Hitpoint Cape(t)";
class8.aByteArray178 = "Its A Hitpoint Cape(t)".getBytes();
}
if(i == 14096)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[3];
class8.anIntArray160 = new int[3];
class8.anIntArray156[0] = 960;
class8.anIntArray156[1] = 22464;
class8.anIntArray156[2] = 43968;
class8.anIntArray160[0] = 99;
class8.anIntArray160[1] = 99;
class8.anIntArray160[2] = 99;
class8.anInt174 = 4203; //Model ID
class8.anInt181 = 720; //zoom increase will make it smaller
class8.anInt190 = 0; //model rotate up+down increase to move doen away from you
class8.anInt198 = 0; //model rotate side ways increase to move right in circle
class8.anInt169 = 0; // model offset increase to move to the right
class8.anInt194 = 0; //model offset increase to move up
class8.anInt165 = 4202;//male wearing
class8.anInt200 = 4202;//female wearing
class8.aString170 = "Hitpoint Hood";
class8.aByteArray178 = "Its A Hitpoint Hood".getBytes();
}
if(i == 14097) {
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[4];
class8.anIntArray160 = new int[4];
class8.anIntArray156[0] = 57280;
class8.anIntArray156[1] = 54503;
class8.anIntArray156[2] = 54183;
class8.anIntArray156[3] = 11200;
class8.anIntArray160[0] = 43932;
class8.anIntArray160[1] = 43932;
class8.anIntArray160[2] = 43932;
class8.anIntArray160[3] = 43932;
class8.anInt174 = 4157; //Inv & Ground
class8.anInt181 = 2000; //Zoom
class8.anInt190 = 572;
class8.anInt198 = 0;
class8.anInt204 = 0;
class8.anInt169 = 0;
class8.anInt194 = 1;
class8.anInt165 = 4180; //Male
class8.anInt200 = 7177; //Female
class8.anInt175 = -1;
class8.anInt197 = -1;
class8.anInt199 = -1;//noteable
class8.aString170 = "Agility Cape";
class8.aByteArray178 = "Its A Agility Cape".getBytes();
}
if(i == 14098) {
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[4];
class8.anIntArray160 = new int[4];
class8.anIntArray156[0] = 57280;
class8.anIntArray156[1] = 54503;
class8.anIntArray156[2] = 54183;
class8.anIntArray156[3] = 11200;
class8.anIntArray160[0] = 924;
class8.anIntArray160[1] = 924;
class8.anIntArray160[2] = 924;
class8.anIntArray160[3] = 924;
class8.anInt174 = 4157; //Inv & Ground
class8.anInt181 = 2000; //Zoom
class8.anInt190 = 572;
class8.anInt198 = 0;
class8.anInt204 = 0;
class8.anInt169 = 0;
class8.anInt194 = 1;
class8.anInt165 = 4180; //Male
class8.anInt200 = 7177; //Female
class8.anInt175 = -1;
class8.anInt197 = -1;
class8.anInt199 = -1;//noteable
class8.aString170 = "Agility Cape(t)";
class8.aByteArray178 = "Its A Agility Cape(t)".getBytes();
}
if(i == 14099)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[3];
class8.anIntArray160 = new int[3];
class8.anIntArray156[0] = 960;
class8.anIntArray156[1] = 22464;
class8.anIntArray156[2] = 43968;
class8.anIntArray160[0] = 43934;
class8.anIntArray160[1] = 43934;
class8.anIntArray160[2] = 43934;
class8.anInt174 = 4203; //Model ID
class8.anInt181 = 720; //zoom increase will make it smaller
class8.anInt190 = 0; //model rotate up+down increase to move doen away from you
class8.anInt198 = 0; //model rotate side ways increase to move right in circle
class8.anInt169 = 0; // model offset increase to move to the right
class8.anInt194 = 0; //model offset increase to move up
class8.anInt165 = 4202;//male wearing
class8.anInt200 = 4202;//female wearing
class8.aString170 = "Agility Hood";
class8.aByteArray178 = "Its A Agility Hood".getBytes();
}
if(i == 14100) {
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[4];
class8.anIntArray160 = new int[4];
class8.anIntArray156[0] = 57280;
class8.anIntArray156[1] = 54503;
class8.anIntArray156[2] = 54183;
class8.anIntArray156[3] = 11200;
class8.anIntArray160[0] = 22416;
class8.anIntArray160[1] = 22416;
class8.anIntArray160[2] = 22416;
class8.anIntArray160[3] = 22416;
class8.anInt174 = 4167; //Inv & Ground
class8.anInt181 = 2000; //Zoom
class8.anInt190 = 572;
class8.anInt198 = 0;
class8.anInt204 = 0;
class8.anInt169 = 0;
class8.anInt194 = 1;
class8.anInt165 = 4189; //Male
class8.anInt200 = 7187; //Female
class8.anInt175 = -1;
class8.anInt197 = -1;
class8.anInt199 = -1;//noteable
class8.aString170 = "Herblore Cape";
class8.aByteArray178 = "Its A Herblore Cape".getBytes();
}
if(i == 14101) {
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[4];
class8.anIntArray160 = new int[4];
class8.anIntArray156[0] = 57280;
class8.anIntArray156[1] = 54503;
class8.anIntArray156[2] = 54183;
class8.anIntArray156[3] = 11200;
class8.anIntArray160[0] = 9152;
class8.anIntArray160[1] = 7104;
class8.anIntArray160[2] = 7104;
class8.anIntArray160[3] = 796;
class8.anInt174 = 4167; //Inv & Ground
class8.anInt181 = 2000; //Zoom
class8.anInt190 = 572;
class8.anInt198 = 0;
class8.anInt204 = 0;
class8.anInt169 = 0;
class8.anInt194 = 1;
class8.anInt165 = 4189; //Male
class8.anInt200 = 7187; //Female
class8.anInt175 = -1;
class8.anInt197 = -1;
class8.anInt199 = -1;//noteable
class8.aString170 = "Herblore Cape(t)";
class8.aByteArray178 = "Its A Herblore Cape(t)".getBytes();
}
if(i == 14102)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[3];
class8.anIntArray160 = new int[3];
class8.anIntArray156[0] = 960;
class8.anIntArray156[1] = 22464;
class8.anIntArray156[2] = 43968;
class8.anIntArray160[0] = 22416;
class8.anIntArray160[1] = 22416;
class8.anIntArray160[2] = 22416;
class8.anInt174 = 4203; //Model ID
class8.anInt181 = 720; //zoom increase will make it smaller
class8.anInt190 = 0; //model rotate up+down increase to move doen away from you
class8.anInt198 = 0; //model rotate side ways increase to move right in circle
class8.anInt169 = 0; // model offset increase to move to the right
class8.anInt194 = 0; //model offset increase to move up
class8.anInt165 = 4202;//male wearing
class8.anInt200 = 4202;//female wearing
class8.aString170 = "Herblore Hood";
class8.aByteArray178 = "Its A Herblore Hood".getBytes();
}
if(i == 14103)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[4];
class8.anIntArray160 = new int[4];
class8.anIntArray156[0] = 57280;
class8.anIntArray156[1] = 54503;
class8.anIntArray156[2] = 54183;
class8.anIntArray156[3] = 11200;
class8.anIntArray160[0] = 51120;
class8.anIntArray160[1] = 51120;
class8.anIntArray160[2] = 51120;
class8.anIntArray160[3] = 51120;
class8.anInt174 = 4178; //Inv & Ground
class8.anInt181 = 2000; //Zoom
class8.anInt190 = 572;
class8.anInt198 = 0;
class8.anInt204 = 0;
class8.anInt169 = 0;
class8.anInt194 = 1;
class8.anInt165 = 4201; //Male
class8.anInt200 = 7198; //Female
class8.anInt175 = -1;
class8.anInt197 = -1;
class8.anInt199 = -1;//noteable
class8.aString170 = "Thieving Cape";
class8.aByteArray178 = "Its A thieving Cape".getBytes();
}
if(i == 14104)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[4];
class8.anIntArray160 = new int[4];
class8.anIntArray156[0] = 57280;
class8.anIntArray156[1] = 54503;
class8.anIntArray156[2] = 54183;
class8.anIntArray156[3] = 11200;
class8.anIntArray160[0] = 16000;
class8.anIntArray160[1] = 16000;
class8.anIntArray160[2] = 16000;
class8.anIntArray160[3] = 16000;
class8.anInt174 = 4178; //Inv & Ground
class8.anInt181 = 2000; //Zoom
class8.anInt190 = 572;
class8.anInt198 = 0;
class8.anInt204 = 0;
class8.anInt169 = 0;
class8.anInt194 = 1;
class8.anInt165 = 4201; //Male
class8.anInt200 = 7198; //Female
class8.anInt175 = -1;
class8.anInt197 = -1;
class8.anInt199 = -1;//noteable
class8.aString170 = "Thieving Cape(t)";
class8.aByteArray178 = "Its A Thieving Cape(t)".getBytes();
}
if(i == 14105)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[3];
class8.anIntArray160 = new int[3];
class8.anIntArray156[0] = 960;
class8.anIntArray156[1] = 22464;
class8.anIntArray156[2] = 43968;
class8.anIntArray160[0] = 51120;
class8.anIntArray160[1] = 51120;
class8.anIntArray160[2] = 51120;
class8.anInt174 = 4203; //Model ID
class8.anInt181 = 720; //zoom increase will make it smaller
class8.anInt190 = 0; //model rotate up+down increase to move doen away from you
class8.anInt198 = 0; //model rotate side ways increase to move right in circle
class8.anInt169 = 0; // model offset increase to move to the right
class8.anInt194 = 0; //model offset increase to move up
class8.anInt165 = 4202;//male wearing
class8.anInt200 = 4202;//female wearing
class8.aString170 = "Thieving Hood";
class8.aByteArray178 = "Its A Thieving Hood".getBytes();
}
if(i == 14106)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[4];
class8.anIntArray160 = new int[4];
class8.anIntArray156[0] = 57280;
class8.anIntArray156[1] = 54503;
class8.anIntArray156[2] = 54183;
class8.anIntArray156[3] = 11200;
class8.anIntArray160[0] = 4769;
class8.anIntArray160[1] = 4769;
class8.anIntArray160[2] = 4769;
class8.anIntArray160[3] = 4769;
class8.anInt174 = 4161; //Inv & Ground
class8.anInt181 = 2000; //Zoom
class8.anInt190 = 572;
class8.anInt198 = 0;
class8.anInt204 = 0;
class8.anInt169 = 0;
class8.anInt194 = 1;
class8.anInt165 = 4183; //Male
class8.anInt200 = 7181; //Female
class8.anInt175 = -1;
class8.anInt197 = -1;
class8.anInt199 = -1;//noteable
class8.aString170 = "Crafting Cape";
class8.aByteArray178 = "Its A Crafting Cape".getBytes();
}
if(i == 14107)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[4];
class8.anIntArray160 = new int[4];
class8.anIntArray156[0] = 57280;
class8.anIntArray156[1] = 54503;
class8.anIntArray156[2] = 54183;
class8.anIntArray156[3] = 11200;
class8.anIntArray160[0] = 9152;
class8.anIntArray160[1] = 7104;
class8.anIntArray160[2] = 7104;
class8.anIntArray160[3] = 796;
class8.anInt174 = 4161; //Inv & Ground
class8.anInt181 = 2000; //Zoom
class8.anInt190 = 572;
class8.anInt198 = 0;
class8.anInt204 = 0;
class8.anInt169 = 0;
class8.anInt194 = 1;
class8.anInt165 = 4183; //Male
class8.anInt200 = 7181; //Female
class8.anInt175 = -1;
class8.anInt197 = -1;
class8.anInt199 = -1;//noteable
class8.aString170 = "Crafting Cape(t)";
class8.aByteArray178 = "Its A Crafting Cape(t)".getBytes();
}
if(i == 14108)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[3];
class8.anIntArray160 = new int[3];
class8.anIntArray156[0] = 960;
class8.anIntArray156[1] = 22464;
class8.anIntArray156[2] = 43968;
class8.anIntArray160[0] = 4769;
class8.anIntArray160[1] = 4769;
class8.anIntArray160[2] = 4769;
class8.anInt174 = 4203; //Model ID
class8.aBoolean176 = false;//Stackable
class8.anInt181 = 720; //zoom increase will make it smaller
class8.anInt190 = 0; //model rotate up+down increase to move doen away from you
class8.anInt198 = 0; //model rotate side ways increase to move right in circle
class8.anInt169 = 0; // model offset increase to move to the right
class8.anInt194 = 0; //model offset increase to move up
class8.anInt165 = 4202;//male wearing
class8.anInt200 = 4202;//female wearing
class8.aString170 = "Crafting Hood";
class8.aByteArray178 = "Its A Crafting Hood".getBytes();
}
if(i == 14109)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[4];
class8.anIntArray160 = new int[4];
class8.anIntArray156[0] = 57280;
class8.anIntArray156[1] = 54503;
class8.anIntArray156[2] = 54183;
class8.anIntArray156[3] = 11200;
class8.anIntArray160[0] = 9152;
class8.anIntArray160[1] = 7104;
class8.anIntArray160[2] = 7104;
class8.anIntArray160[3] = 796;
class8.anInt174 = 4166; //Inv & Ground
class8.anInt181 = 2000; //Zoom
class8.anInt190 = 572;
class8.anInt198 = 0;
class8.anInt204 = 0;
class8.anInt169 = 0;
class8.anInt194 = 1;
class8.anInt165 = 4188; //Male
class8.anInt200 = 7186; //Female
class8.anInt175 = -1;
class8.anInt197 = -1;
class8.anInt199 = -1;//noteable
class8.aString170 = "Fletching Cape(t)";
class8.aByteArray178 = "Its A Fletching Cape(t)".getBytes();
}
if(i == 14110)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[4];
class8.anIntArray160 = new int[4];
class8.anIntArray156[0] = 57280;
class8.anIntArray156[1] = 54503;
class8.anIntArray156[2] = 54183;
class8.anIntArray156[3] = 11200;
class8.anIntArray160[0] = 22430;
class8.anIntArray160[1] = 22430;
class8.anIntArray160[2] = 22430;
class8.anIntArray160[3] = 22430;
class8.anInt174 = 4166; //Inv & Ground
class8.anInt181 = 2000; //Zoom
class8.anInt190 = 572;
class8.anInt198 = 0;
class8.anInt204 = 0;
class8.anInt169 = 0;
class8.anInt194 = 1;
class8.anInt165 = 4188; //Male
class8.anInt200 = 7186; //Female
class8.anInt175 = -1;
class8.anInt197 = -1;
class8.anInt199 = -1;//noteable
class8.aString170 = "Fletching Cape";
class8.aByteArray178 = "Its A Fletching Cape".getBytes();
}
if(i == 14111)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[3];
class8.anIntArray160 = new int[3];
class8.anIntArray156[0] = 960;
class8.anIntArray156[1] = 22464;
class8.anIntArray156[2] = 43968;
class8.anIntArray160[0] = 22430;
class8.anIntArray160[1] = 22430;
class8.anIntArray160[2] = 22430;
class8.anInt174 = 4203; //Model ID
class8.aBoolean176 = false;//Stackable
class8.anInt181 = 720; //zoom increase will make it smaller
class8.anInt190 = 0; //model rotate up+down increase to move doen away from you
class8.anInt198 = 0; //model rotate side ways increase to move right in circle
class8.anInt169 = 0; // model offset increase to move to the right
class8.anInt194 = 0; //model offset increase to move up
class8.anInt165 = 4202;//male wearing
class8.anInt200 = 4202;//female wearing
class8.aString170 = "Fletching Hood";
class8.aByteArray178 = "Its A Fletching Hood".getBytes();
}
if(i == 14112)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[4];
class8.anIntArray160 = new int[4];
class8.anIntArray156[0] = 57280;
class8.anIntArray156[1] = 54503;
class8.anIntArray156[2] = 54183;
class8.anIntArray156[3] = 11200;
class8.anIntArray160[0] = 6;
class8.anIntArray160[1] = 6;
class8.anIntArray160[2] = 6;
class8.anIntArray160[3] = 6;
class8.anInt174 = 4175; //Inv & Ground
class8.anInt181 = 2000; //Zoom
class8.anInt190 = 572;
class8.anInt198 = 0;
class8.anInt204 = 0;
class8.anInt169 = 0;
class8.anInt194 = 1;
class8.anInt165 = 4197; //Male
class8.anInt200 = 7195; //Female
class8.anInt175 = -1;
class8.anInt197 = -1;
class8.anInt199 = -1;//noteable
class8.aString170 = "Slayer Cape";
class8.aByteArray178 = "Its A slayer Cape".getBytes();
}
if(i == 14113)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[4];
class8.anIntArray160 = new int[4];
class8.anIntArray156[0] = 57280;
class8.anIntArray156[1] = 54503;
class8.anIntArray156[2] = 54183;
class8.anIntArray156[3] = 11200;
class8.anIntArray160[0] = 912;
class8.anIntArray160[1] = 912;
class8.anIntArray160[2] = 912;
class8.anIntArray160[3] = 912;
class8.anInt174 = 4175; //Inv & Ground
class8.anInt181 = 2000; //Zoom
class8.anInt190 = 572;
class8.anInt198 = 0;
class8.anInt204 = 0;
class8.anInt169 = 0;
class8.anInt194 = 1;
class8.anInt165 = 4197; //Male
class8.anInt200 = 7195; //Female
class8.anInt175 = -1;
class8.anInt197 = -1;
class8.anInt199 = -1;//noteable
class8.aString170 = "Slayer Cape";
class8.aByteArray178 = "Its A slayer Cape".getBytes();
}
if(i == 14114)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[3];
class8.anIntArray160 = new int[3];
class8.anIntArray156[0] = 960;
class8.anIntArray156[1] = 22464;
class8.anIntArray156[2] = 43968;
class8.anIntArray160[0] = 0;//Back
class8.anIntArray160[1] = 0;//front Line
class8.anIntArray160[2] = 6;//Inside
class8.anInt174 = 4203; //Model ID
class8.aBoolean176 = false;//Stackable
class8.anInt181 = 720; //zoom increase will make it smaller
class8.anInt190 = 0; //model rotate up+down increase to move doen away from you
class8.anInt198 = 0; //model rotate side ways increase to move right in circle
class8.anInt169 = 0; // model offset increase to move to the right
class8.anInt194 = 0; //model offset increase to move up
class8.anInt165 = 4202;//male wearing
class8.anInt200 = 4202;//female wearing
class8.aString170 = "Slayer Hood";
class8.aByteArray178 = "Its A Slayer Hood".getBytes();
}
if(i == 14118) {
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[4];
class8.anIntArray160 = new int[4];
class8.anIntArray156[0] = 57280;
class8.anIntArray156[1] = 54503;
class8.anIntArray156[2] = 54183;
class8.anIntArray156[3] = 11200;
class8.anIntArray160[0] = 10512;
class8.anIntArray160[1] = 10512;
class8.anIntArray160[2] = 10512;
class8.anIntArray160[3] = 10512;
class8.anInt174 = 4170; //Inv & Ground
class8.anInt181 = 2000; //Zoom
class8.anInt190 = 572;
class8.anInt198 = 0;
class8.anInt204 = 0;
class8.anInt169 = 0;
class8.anInt194 = 1;
class8.anInt165 = 4192; //Male
class8.anInt200 = 7190; //Female
class8.anInt175 = -1;
class8.anInt197 = -1;
class8.anInt199 = -1;//noteable
class8.aString170 = "Mining Cape";
class8.aByteArray178 = "Its A mining Cape".getBytes();
}
if(i == 14119) {
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[4];
class8.anIntArray160 = new int[4];
class8.anIntArray156[0] = 57280;
class8.anIntArray156[1] = 54503;
class8.anIntArray156[2] = 54183;
class8.anIntArray156[3] = 11200;
class8.anIntArray160[0] = 38226;
class8.anIntArray160[1] = 38226;
class8.anIntArray160[2] = 38226;
class8.anIntArray160[3] = 38226;
class8.anInt174 = 4170; //Inv & Ground
class8.anInt181 = 2000; //Zoom
class8.anInt190 = 572;
class8.anInt198 = 0;
class8.anInt204 = 0;
class8.anInt169 = 0;
class8.anInt194 = 1;
class8.anInt165 = 4192; //Male
class8.anInt200 = 7190; //Female
class8.anInt175 = -1;
class8.anInt197 = -1;
class8.anInt199 = -1;//noteable
class8.aString170 = "Mining Cape(t)";
class8.aByteArray178 = "Its A mining Cape(t)".getBytes();
}

if(i == 14120)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[3];
class8.anIntArray160 = new int[3];
class8.anIntArray156[0] = 960;
class8.anIntArray156[1] = 22464;
class8.anIntArray156[2] = 43968;
class8.anIntArray160[0] = 10512;//Back
class8.anIntArray160[1] = 10512;//front Line
class8.anIntArray160[2] = 10512;//Inside
class8.anInt174 = 4203; //Model ID
class8.aBoolean176 = false;//Stackable
class8.anInt181 = 720; //zoom increase will make it smaller
class8.anInt190 = 0; //model rotate up+down increase to move doen away from you
class8.anInt198 = 0; //model rotate side ways increase to move right in circle
class8.anInt169 = 0; // model offset increase to move to the right
class8.anInt194 = 0; //model offset increase to move up
class8.anInt165 = 4202;//male wearing
class8.anInt200 = 4202;//female wearing
class8.aString170 = "Mining Hood";
class8.aByteArray178 = "Its A Mining Hood".getBytes();
}

if(i == 14121) {
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[4];
class8.anIntArray160 = new int[4];
class8.anIntArray156[0] = 57280;
class8.anIntArray156[1] = 54503;
class8.anIntArray156[2] = 54183;
class8.anIntArray156[3] = 11200;
class8.anIntArray160[0] = 10512;
class8.anIntArray160[1] = 10512;
class8.anIntArray160[2] = 10512;
class8.anIntArray160[3] = 10512;
class8.anInt174 = 4176; //Inv & Ground
class8.anInt181 = 2000; //Zoom
class8.anInt190 = 572;
class8.anInt198 = 0;
class8.anInt204 = 0;
class8.anInt169 = 0;
class8.anInt194 = 1;
class8.anInt165 = 4198; //Male
class8.anInt200 = 7196; //Female
class8.anInt175 = -1;
class8.anInt197 = -1;
class8.anInt199 = -1;//noteable
class8.aString170 = "Smithing Cape";
class8.aByteArray178 = "Its A smithing Cape".getBytes();
}
if(i == 14122) {
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[4];
class8.anIntArray160 = new int[4];
class8.anIntArray156[0] = 57280;
class8.anIntArray156[1] = 54503;
class8.anIntArray156[2] = 54183;
class8.anIntArray156[3] = 11200;
class8.anIntArray160[0] = 9152;
class8.anIntArray160[1] = 7104;
class8.anIntArray160[2] = 7104;
class8.anIntArray160[3] = 796;
class8.anInt174 = 4176; //Inv & Ground
class8.anInt181 = 2000; //Zoom
class8.anInt190 = 572;
class8.anInt198 = 0;
class8.anInt204 = 0;
class8.anInt169 = 0;
class8.anInt194 = 1;
class8.anInt165 = 4198; //Male
class8.anInt200 = 7196; //Female
class8.anInt175 = -1;
class8.anInt197 = -1;
class8.anInt199 = -1;//noteable
class8.aString170 = "Smithing Cape(t)";
class8.aByteArray178 = "Its A smithing Cape(t)".getBytes();
}
if(i == 14123)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[3];
class8.anIntArray160 = new int[3];
class8.anIntArray156[0] = 960;
class8.anIntArray156[1] = 22464;
class8.anIntArray156[2] = 43968;
class8.anIntArray160[0] = 10512;//Back
class8.anIntArray160[1] = 10512;//front Line
class8.anIntArray160[2] = 10512;//Inside
class8.anInt174 = 4203; //Model ID
class8.aBoolean176 = false;//Stackable
class8.anInt181 = 720; //zoom increase will make it smaller
class8.anInt190 = 0; //model rotate up+down increase to move doen away from you
class8.anInt198 = 0; //model rotate side ways increase to move right in circle
class8.anInt169 = 0; // model offset increase to move to the right
class8.anInt194 = 0; //model offset increase to move up
class8.anInt165 = 4202;//male wearing
class8.anInt200 = 4202;//female wearing
class8.aString170 = "Smithing Hood";
class8.aByteArray178 = "Its A Smithing Hood".getBytes();
}
if(i == 14124) {
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[4];
class8.anIntArray160 = new int[4];
class8.anIntArray156[0] = 57280;
class8.anIntArray156[1] = 54503;
class8.anIntArray156[2] = 54183;
class8.anIntArray156[3] = 11200;
class8.anIntArray160[0] = 38226;
class8.anIntArray160[1] = 38226;
class8.anIntArray160[2] = 38226;
class8.anIntArray160[3] = 38226;
class8.anInt174 = 4165; //Inv & Ground
class8.anInt181 = 2000; //Zoom
class8.anInt190 = 572;
class8.anInt198 = 0;
class8.anInt204 = 0;
class8.anInt169 = 0;
class8.anInt194 = 1;
class8.anInt165 = 4187; //Male
class8.anInt200 = 7185; //Female
class8.anInt175 = -1;
class8.anInt197 = -1;
class8.anInt199 = -1;//noteable
class8.aString170 = "Fishing Cape";
class8.aByteArray178 = "Its A fishing Cape".getBytes();
}
if(i == 14125) {
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[4];
class8.anIntArray160 = new int[4];
class8.anIntArray156[0] = 57280;
class8.anIntArray156[1] = 54503;
class8.anIntArray156[2] = 54183;
class8.anIntArray156[3] = 11200;
class8.anIntArray160[0] = 9152;
class8.anIntArray160[1] = 7104;
class8.anIntArray160[2] = 7104;
class8.anIntArray160[3] = 796;
class8.anInt174 = 4165; //Inv & Ground
class8.anInt181 = 2000; //Zoom
class8.anInt190 = 572;
class8.anInt198 = 0;
class8.anInt204 = 0;
class8.anInt169 = 0;
class8.anInt194 = 1;
class8.anInt165 = 4187; //Male
class8.anInt200 = 7185; //Female
class8.anInt175 = -1;
class8.anInt197 = -1;
class8.anInt199 = -1;//noteable
class8.aString170 = "Fishing Cape(t)";
class8.aByteArray178 = "Its A Fishing Cape(t)".getBytes();
}
if(i == 14126)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[3];
class8.anIntArray160 = new int[3];
class8.anIntArray156[0] = 960;
class8.anIntArray156[1] = 22464;
class8.anIntArray156[2] = 43968;
class8.anIntArray160[0] = 38226;//Back
class8.anIntArray160[1] = 38226;//front Line
class8.anIntArray160[2] = 38226;//Inside
class8.anInt174 = 4203; //Model ID
class8.aBoolean176 = false;//Stackable
class8.anInt181 = 720; //zoom increase will make it smaller
class8.anInt190 = 0; //model rotate up+down increase to move doen away from you
class8.anInt198 = 0; //model rotate side ways increase to move right in circle
class8.anInt169 = 0; // model offset increase to move to the right
class8.anInt194 = 0; //model offset increase to move up
class8.anInt165 = 4202;//male wearing
class8.anInt200 = 4202;//female wearing
class8.aString170 = "Fishing Hood";
class8.aByteArray178 = "Its A Fishing Hood".getBytes();
}
if(i == 14127) {
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[4];
class8.anIntArray160 = new int[4];
class8.anIntArray156[0] = 57280;
class8.anIntArray156[1] = 54503;
class8.anIntArray156[2] = 54183;
class8.anIntArray156[3] = 11200;
class8.anIntArray160[0] = 51000;
class8.anIntArray160[1] = 51000;
class8.anIntArray160[2] = 51000;
class8.anIntArray160[3] = 51000;
class8.anInt174 = 4160; //Inv & Ground
class8.anInt181 = 2000; //Zoom
class8.anInt190 = 572;
class8.anInt198 = 0;
class8.anInt204 = 0;
class8.anInt169 = 0;
class8.anInt194 = 1;
class8.anInt165 = 4182; //Male
class8.anInt200 = 7180; //Female
class8.anInt175 = -1;
class8.anInt197 = -1;
class8.anInt199 = -1;//noteable
class8.aString170 = "Cooking Cape";
class8.aByteArray178 = "Its A Cooking Cape".getBytes();
}
if(i == 14128) {
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[4];
class8.anIntArray160 = new int[4];
class8.anIntArray156[0] = 57280;
class8.anIntArray156[1] = 54503;
class8.anIntArray156[2] = 54183;
class8.anIntArray156[3] = 11200;
class8.anIntArray160[0] = 926;
class8.anIntArray160[1] = 926;
class8.anIntArray160[2] = 926;
class8.anIntArray160[3] = 926;
class8.anInt174 = 4160; //Inv & Ground
class8.anInt181 = 2000; //Zoom
class8.anInt190 = 572;
class8.anInt198 = 0;
class8.anInt204 = 0;
class8.anInt169 = 0;
class8.anInt194 = 1;
class8.anInt165 = 4182; //Male
class8.anInt200 = 7180; //Female
class8.anInt175 = -1;
class8.anInt197 = -1;
class8.anInt199 = -1;//noteable
class8.aString170 = "Cooking Cape(t)";
class8.aByteArray178 = "Its A Cooking Cape(t)".getBytes();
}
if(i == 14129)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[3];
class8.anIntArray160 = new int[3];
class8.anIntArray156[0] = 960;
class8.anIntArray156[1] = 22464;
class8.anIntArray156[2] = 43968;
class8.anIntArray160[0] = 51136;//Back
class8.anIntArray160[1] = 51136;//front Line
class8.anIntArray160[2] = 51136;//Inside
class8.anInt174 = 4203; //Model ID
class8.aBoolean176 = false;//Stackable
class8.anInt181 = 720; //zoom increase will make it smaller
class8.anInt190 = 0; //model rotate up+down increase to move doen away from you
class8.anInt198 = 0; //model rotate side ways increase to move right in circle
class8.anInt169 = 0; // model offset increase to move to the right
class8.anInt194 = 0; //model offset increase to move up
class8.anInt165 = 4202;//male wearing
class8.anInt200 = 4202;//female wearing
class8.aString170 = "Cooking Hood";
class8.aByteArray178 = "Its A Cooking Hood".getBytes();
}

if(i == 14133)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[4];
class8.anIntArray160 = new int[4];
class8.anIntArray156[0] = 57280;
class8.anIntArray156[1] = 54503;
class8.anIntArray156[2] = 54183;
class8.anIntArray156[3] = 11200;
class8.anIntArray160[0] = 5560;
class8.anIntArray160[1] = 5560;
class8.anIntArray160[2] = 5560;
class8.anIntArray160[3] = 5560;
class8.anInt174 = 4174; //Inv & Ground
class8.anInt181 = 2000; //Zoom
class8.anInt190 = 572;
class8.anInt198 = 0;
class8.anInt204 = 0;
class8.anInt169 = 0;
class8.anInt194 = 1;
class8.anInt165 = 4200; //Male
class8.anInt200 = 7199; //Female
class8.anInt175 = -1;
class8.anInt197 = -1;
class8.aString170 = "Woodcutting Cape";
class8.aByteArray178 = "Its A Woodcutting Cape".getBytes();
}
if(i == 14134)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[4];
class8.anIntArray160 = new int[4];
class8.anIntArray156[0] = 57280;
class8.anIntArray156[1] = 54503;
class8.anIntArray156[2] = 54183;
class8.anIntArray156[3] = 11200;
class8.anIntArray160[0] = 22430;
class8.anIntArray160[1] = 22430;
class8.anIntArray160[2] = 22430;
class8.anIntArray160[3] = 22430;
class8.anInt174 = 4174; //Inv & Ground
class8.anInt181 = 2000; //Zoom
class8.anInt190 = 572;
class8.anInt198 = 0;
class8.anInt204 = 0;
class8.anInt169 = 0;
class8.anInt194 = 1;
class8.anInt165 = 4200; //Male
class8.anInt200 = 7199; //Female
class8.anInt175 = -1;
class8.anInt197 = -1;
class8.aString170 = "Woodcutting Cape";
class8.aByteArray178 = "Its A Woodcutting Cape".getBytes();
}
if(i == 14135)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[3];
class8.anIntArray160 = new int[3];
class8.anIntArray156[0] = 960;
class8.anIntArray156[1] = 22464;
class8.anIntArray156[2] = 43968;
class8.anIntArray160[0] = 5560;//Back
class8.anIntArray160[1] = 5560;//front Line
class8.anIntArray160[2] = 5560;//Inside
class8.anInt174 = 4203; //Model ID
class8.aBoolean176 = false;//Stackable
class8.anInt181 = 720; //zoom increase will make it smaller
class8.anInt190 = 0; //model rotate up+down increase to move doen away from you
class8.anInt198 = 0; //model rotate side ways increase to move right in circle
class8.anInt169 = 0; // model offset increase to move to the right
class8.anInt194 = 0; //model offset increase to move up
class8.anInt165 = 4202;//male wearing
class8.anInt200 = 4202;//female wearing
class8.aString170 = "Woodcutting Hood";
class8.aByteArray178 = "Its A Woodcutting Hood".getBytes();
}
if(i == 14136)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[4];
class8.anIntArray160 = new int[4];
class8.anIntArray156[0] = 57280;
class8.anIntArray156[1] = 54503;
class8.anIntArray156[2] = 54183;
class8.anIntArray156[3] = 11200;
class8.anIntArray160[0] = 22416;
class8.anIntArray160[1] = 22416;
class8.anIntArray160[2] = 22416;
class8.anIntArray160[3] = 22416;
class8.anInt174 = 4163; //Inv & Ground
class8.anInt181 = 2000; //Zoom
class8.anInt190 = 572;
class8.anInt198 = 0;
class8.anInt204 = 0;
class8.anInt169 = 0;
class8.anInt194 = 1;
class8.anInt165 = 4185; //Male
class8.anInt200 = 7183; //Female
class8.anInt175 = -1;
class8.anInt197 = -1;
class8.anInt199 = -1;//noteable
class8.aString170 = "Farming Cape";
class8.aByteArray178 = "Its A Farming Cape".getBytes();
}

if(i == 14137)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[6];
class8.anIntArray160 = new int[6];
class8.anIntArray156 = new int[6];
class8.anIntArray160 = new int[6];
class8.anIntArray156[0] = 8256;
class8.anIntArray160[0] = 22416;
class8.anIntArray156[1] = 54183;
class8.anIntArray160[1] = 22416;
class8.anIntArray156[2] = 57280;
class8.anIntArray160[2] = 22416;
class8.anIntArray156[3] = 960;
class8.anIntArray160[3] = 22416;
class8.anIntArray156[4] = 22464;
class8.anIntArray160[4] = 22416;
class8.anIntArray156[5] = 21568;
class8.anIntArray160[5] = 22416;
class8.anInt174 = 4163; //Inv & Ground
class8.anInt181 = 2000; //Zoom
class8.anInt190 = 572;
class8.anInt198 = 0;
class8.anInt204 = 0;
class8.anInt169 = 0;
class8.anInt194 = 1;
class8.anInt165 = 4185; //Male
class8.anInt200 = 7183; //Female
class8.anInt175 = -1;
class8.anInt197 = -1;
class8.anInt199 = -1;//noteable
class8.aString170 = "Farming Cape(t)";
class8.aByteArray178 = "Its A Farming Cape(t)".getBytes();
}
if(i == 14138)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[3];
class8.anIntArray160 = new int[3];
class8.anIntArray156[0] = 960;
class8.anIntArray156[1] = 22464;
class8.anIntArray156[2] = 43968;
class8.anIntArray160[0] = 22416;//Back
class8.anIntArray160[1] = 22416;//front Line
class8.anIntArray160[2] = 22416;//Inside
class8.anInt174 = 4203; //Model ID
class8.aBoolean176 = false;//Stackable
class8.anInt181 = 720; //zoom increase will make it smaller
class8.anInt190 = 0; //model rotate up+down increase to move doen away from you
class8.anInt198 = 0; //model rotate side ways increase to move right in circle
class8.anInt169 = 0; // model offset increase to move to the right
class8.anInt194 = 0; //model offset increase to move up
class8.anInt165 = 4202;//male wearing
class8.anInt200 = 4202;//female wearing
class8.aString170 = "Farming Hood";
class8.aByteArray178 = "Its A Farming Hood".getBytes();
}
if(i == 14139)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[4];
class8.anIntArray160 = new int[4];
class8.anIntArray156[0] = 57280;
class8.anIntArray156[1] = 54503;
class8.anIntArray156[2] = 54183;
class8.anIntArray156[3] = 11200;
class8.anIntArray160[0] = 38226;
class8.anIntArray160[1] = 38226;
class8.anIntArray160[2] = 38226;
class8.anIntArray160[3] = 38226;
class8.anInt174 = 4172; //Inv & Ground
class8.anInt181 = 2000; //Zoom
class8.anInt190 = 572;
class8.anInt198 = 0;
class8.anInt204 = 0;
class8.anInt169 = 0;
class8.anInt194 = 1;
class8.anInt165 = 4194; //Male
class8.anInt200 = 7192; //Female
class8.anInt175 = -1;
class8.anInt197 = -1;
class8.anInt199 = -1;//noteable
class8.aString170 = "Quest Cape";
class8.aByteArray178 = "Its A Quest Cape".getBytes();
}
if(i == 14140)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[3];
class8.anIntArray160 = new int[3];
class8.anIntArray156[0] = 960;
class8.anIntArray156[1] = 22464;
class8.anIntArray156[2] = 43968;
class8.anIntArray160[0] = 38226;//Back
class8.anIntArray160[1] = 38226;//front Line
class8.anIntArray160[2] = 38226;//Inside
class8.anInt174 = 4203; //Model ID
class8.aBoolean176 = false;//Stackable
class8.anInt181 = 720; //zoom increase will make it smaller
class8.anInt190 = 0; //model rotate up+down increase to move doen away from you
class8.anInt198 = 0; //model rotate side ways increase to move right in circle
class8.anInt169 = 0; // model offset increase to move to the right
class8.anInt194 = 0; //model offset increase to move up
class8.anInt165 = 4202;//male wearing
class8.anInt200 = 4202;//female wearing
class8.aString170 = "Quest Hood";
class8.aByteArray178 = "Its A Quest Hood".getBytes();
}



if(i == 6570)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anInt174 = 9631; //Model ID

class8.aBoolean176 = false;//Stackable
}


if(i == 14638)  // change this if you need to "item number"
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wield";//New option
class8.anInt174 = 4673; //Model ID
class8.anInt181 = 1200; //zoom increase will make it smaller
class8.anInt190 = 510; //model rotate up+down increase to move doen away from you
class8.anInt198 = 1840; //model rotate side ways increase to move right in circle
class8.anInt169 = 0; // model offset increase to move to the right
class8.anInt194 = 5; //model offset increase to move up
class8.anInt165 = 4674;//male wearing
class8.anInt200 = 4675;//female wearing
class8.anInt164 = 337;//Female arms/sleeves
class8.anInt188 = 156;//male arms/sleeves
class8.aBoolean176 = false;//Stackable
class8.aString170 = "Fighter Torso";//Name of the new item
class8.aByteArray178 = "Its A Fighter Torso".getBytes();//examin info
}

if(i == 15346)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wield";
class8.anInt174 = 2141; //Model Drop/inv view
class8.anInt181 = 854;
class8.anInt190 = 453;
class8.anInt198 = 2039;
class8.anInt199 = -1;
class8.anInt169 = 0;
class8.anInt194 = -5;
class8.anInt165 = 2734; //male wearing
class8.anInt200 = 2752; //female wearing
class8.anInt164 = 2758; //Female arms/sleeves
class8.anInt188 = 2750; //male arms/sleeves
class8.anInt175 = -1;
class8.aBoolean176 = false;
class8.aString170 = "Armadyl chestplate";
class8.aByteArray178 = "A Chestplate From Armadyl".getBytes();
}
if(i == 15347)  // change this if you need to "item number"
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wield";//New option
class8.anInt174 = 4694; //Model ID
class8.anInt181 = 1600;//Model Zoom
class8.anInt190 = 510;//Model Rotation
class8.anInt198 = 0;//model rotate side ways increase to move right in circle
class8.anInt204 = 138;//Unknown
class8.anInt199 = -1;//noteable
class8.anInt169 = 0;// model offset increase to move to the right
class8.anInt194 = 0;//model offset increase to move up
class8.anInt165 = 4695;//male wearing
class8.anInt200 = 4696;//female wearing
class8.anInt175 = -1;//Unknown
class8.aBoolean176 = false;//Stackable
class8.anInt164 = -1;//Female arms/sleeves
class8.anInt188 = -1;//male arms/sleeves
class8.aBoolean176 = false;//Stackable
class8.aString170 = "Armadyl Plateskirt";//Name of the new item
class8.aByteArray178 = "Its A Armadyl Plateskirt".getBytes();//examin info
      }
if(i == 15345)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wield";
class8.anInt174 = 2139; //Model Drop/inv view
class8.anInt181 = 789;
class8.anInt190 = 66;
class8.anInt198 = 372;
class8.anInt199 = -1;
class8.anInt169 = 9;
class8.anInt194 = -4;
class8.anInt165 = 2747; //male wearing
class8.anInt200 = 2757; //female wearing
class8.anInt164 = -1; //Female arms/sleeves
class8.anInt188 = -1; //male arms/sleeves
class8.anInt175 = -1;
class8.aBoolean176 = false;
class8.aString170 = "Armadyl Helm";
class8.aByteArray178 = "Helm From Armadyl.".getBytes();
}


if(i == 13595)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wield";
class8.anIntArray156 = new int[2];
class8.anIntArray160 = new int[2];
class8.anIntArray156[0] = 28;
class8.anIntArray156[1] = 74;
class8.anIntArray160[0] = 5642;
class8.anIntArray160[1] = 5652;
class8.anInt174 = 5001; //Model ID
class8.anInt181 = 490;
class8.anInt190 = 344;
class8.anInt198 = 192;
class8.anInt204 = 0;
class8.anInt199 = -1;
class8.anInt169 = 1;
class8.anInt194 = 20;
class8.anInt165 = 5000; //Male equip
class8.anInt200 = 5000; //Female equip
class8.anInt175 = 56;
class8.anInt197 = 116;
class8.anInt164 = -1;
class8.anInt188 = -1;
class8.aBoolean176 = false;
class8.aString170 = "Bronze Defender";
class8.aByteArray178 = "A Defensive weapon.".getBytes();
}
if(i == 13596)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wield";
class8.anIntArray156 = new int[2];
class8.anIntArray160 = new int[2];
class8.anIntArray156[0] = 28;
class8.anIntArray156[1] = 74;
class8.anIntArray160[0] = 23;
class8.anIntArray160[1] = 33;
class8.anInt174 = 5001; //Model ID
class8.anInt181 = 490;
class8.anInt190 = 344;
class8.anInt198 = 192;
class8.anInt204 = 0;
class8.anInt199 = -1;
class8.anInt169 = 1;
class8.anInt194 = 20;
class8.anInt165 = 5000; //Male equip
class8.anInt200 = 5000; //Female equip
class8.anInt175 = 56;
class8.anInt197 = 116;
class8.anInt164 = -1;
class8.anInt188 = -1;
class8.aBoolean176 = false;
class8.aString170 = "Iron Defender";
class8.aByteArray178 = "A Defensive weapon.".getBytes();
}
if(i == 13597)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wield";
class8.anIntArray156 = new int[2];
class8.anIntArray160 = new int[2];
class8.anIntArray156[0] = 28;
class8.anIntArray156[1] = 74;
class8.anIntArray160[0] = 43042;
class8.anIntArray160[1] = 43072;
class8.anInt174 = 5001; //Model ID
class8.anInt181 = 490;
class8.anInt190 = 344;
class8.anInt198 = 192;
class8.anInt204 = 0;
class8.anInt199 = -1;
class8.anInt169 = 1;
class8.anInt194 = 20;
class8.anInt165 = 5000; //Male equip
class8.anInt200 = 5000; //Female equip
class8.anInt175 = 56;
class8.anInt197 = 116;
class8.anInt164 = -1;
class8.anInt188 = -1;
class8.aBoolean176 = false;
class8.aString170 = "Steel Defender";
class8.aByteArray178 = "A Defensive weapon.".getBytes();
}
if(i == 13598)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wield";
class8.anIntArray156 = new int[1];
class8.anIntArray160 = new int[1];
class8.anIntArray156 = new int[2];
class8.anIntArray160 = new int[2];
class8.anIntArray156[0] = 28;
class8.anIntArray156[1] = 74;
class8.anIntArray160[0] = 0;
class8.anIntArray160[1] = 10;
class8.anInt174 = 5001; //Model ID
class8.anInt181 = 490;
class8.anInt190 = 344;
class8.anInt198 = 192;
class8.anInt204 = 0;
class8.anInt199 = -1;
class8.anInt169 = 1;
class8.anInt194 = 20;
class8.anInt165 = 5000; //Male equip
class8.anInt200 = 5000; //Female equip
class8.anInt175 = 56;
class8.anInt197 = 116;
class8.anInt164 = -1;
class8.anInt188 = -1;
class8.aBoolean176 = false;
class8.aString170 = "Black Defender";
class8.aByteArray178 = "A Defensive weapon.".getBytes();
}
if(i == 13599)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wield";
class8.anIntArray156 = new int[2];
class8.anIntArray160 = new int[2];
class8.anIntArray156[0] = 28;
class8.anIntArray156[1] = 74;
class8.anIntArray160[0] = 43277;
class8.anIntArray160[1] = 43297;
class8.anInt174 = 5001; //Model ID
class8.anInt181 = 490;
class8.anInt190 = 344;
class8.anInt198 = 192;
class8.anInt204 = 0;
class8.anInt199 = -1;
class8.anInt169 = 1;
class8.anInt194 = 20;
class8.anInt165 = 5000; //Male equip
class8.anInt200 = 5000; //Female equip
class8.anInt175 = 56;
class8.anInt197 = 116;
class8.anInt164 = -1;
class8.anInt188 = -1;
class8.aBoolean176 = false;
class8.aString170 = "Mithril Defender";
class8.aByteArray178 = "A Defensive weapon.".getBytes();
}
if(i == 13600)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wield";
class8.anIntArray156 = new int[2];
class8.anIntArray160 = new int[2];
class8.anIntArray156[0] = 28;
class8.anIntArray156[1] = 74;
class8.anIntArray160[0] = 21642;
class8.anIntArray160[1] = 21662;
class8.anInt174 = 5001; //Model ID
class8.anInt181 = 490;
class8.anInt190 = 344;
class8.anInt198 = 192;
class8.anInt204 = 0;
class8.anInt199 = -1;
class8.anInt169 = 1;
class8.anInt194 = 20;
class8.anInt165 = 5000; //Male equip
class8.anInt200 = 5000; //Female equip
class8.anInt175 = 56;
class8.anInt197 = 116;
class8.anInt164 = -1;
class8.anInt188 = -1;
class8.aBoolean176 = false;
class8.aString170 = "Adamant Defender";
class8.aByteArray178 = "A Defensive weapon.".getBytes();
}
if(i == 13601)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wield";
class8.anIntArray156 = new int[2];
class8.anIntArray160 = new int[2];
class8.anIntArray156[0] = 28;
class8.anIntArray156[1] = 74;
class8.anIntArray160[0] = 38676;
class8.anIntArray160[1] = 38313;
class8.anInt174 = 5001; //Model ID
class8.anInt181 = 490;
class8.anInt190 = 344;
class8.anInt198 = 192;
class8.anInt204 = 0;
class8.anInt199 = -1;
class8.anInt169 = 1;
class8.anInt194 = 20;
class8.anInt165 = 5000; //Male equip
class8.anInt200 = 5000; //Female equip
class8.anInt175 = 56;
class8.anInt197 = 116;
class8.anInt164 = -1;
class8.anInt188 = -1;
class8.aBoolean176 = false;
class8.aString170 = "Rune Defender";
class8.aByteArray178 = "A Defensive weapon.".getBytes();
}
if(i == 15156)  // change this if you need to "item number"
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wield";//New option
class8.anInt174 = 4676; //Model ID
class8.anInt181 = 2128;//Model Zoom
class8.anInt190 = 591;//Model Rotation
class8.anInt198 = 1034;//model rotate side ways increase to move right in circle
class8.anInt204 = 138;//Unknown
class8.anInt199 = -1;//noteable
class8.anInt169 = 8;// model offset increase to move to the right
class8.anInt194 = 11;//model offset increase to move up
class8.anInt165 = 4677;//male wearing
class8.anInt200 = 4677;//female wearing
class8.anInt175 = -1;//Unknown
class8.aBoolean176 = false;//Stackable//Unknown
class8.anInt164 = -1;//Female arms/sleeves
class8.anInt188 = -1;//male arms/sleeves
class8.aBoolean176 = false;//Stackable
class8.aString170 = "Dark bow";//Name of the new item
class8.aByteArray178 = "A Great Bow For Rangers.".getBytes();//examin info
}
if(i == 15352)  // change this if you need to "item number"
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wield";//New option
class8.anInt174 = 4678; //Model ID
class8.anInt181 = 595;//Model Zoom
class8.anInt190 = 0;//Model Rotation
class8.anInt198 = 0;//model rotate side ways increase to move right in circle
class8.anInt204 = 0;//Unknown
class8.anInt199 = 0;//noteable
class8.anInt169 = 0;// model offset increase to move to the right
class8.anInt194 = 0;//model offset increase to move up
class8.anInt165 = 4679;//male wearing
class8.anInt200 = 4680;//female wearing
class8.anInt175 = 56;//Unknown
class8.anInt197 = 116;//Unknown
class8.anInt164 = -1;//Female arms/sleeves
class8.anInt188 = -1;//male arms/sleeves
class8.aBoolean176 = false;//Stackable
class8.aString170 = "Dragon Boots";//Name of the new item
class8.aByteArray178 = "A Nice Pair Of Boots.".getBytes();//examin info
}
if(i == 15348)  // change this if you need to "item number"
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wield";//New option
class8.anInt174 = 4681; //Model ID
class8.anInt181 = 900;//Model Zoom
class8.anInt190 = 540;//Model Rotation
class8.anInt198 = 2039;//model rotate side ways increase to move right in circle
class8.anInt199 = 0;//noteable
class8.anInt169 = 0;// model offset increase to move to the right
class8.anInt194 = 0;//model offset increase to move up
class8.anInt165 = 4682;//male wearing
class8.anInt200 = 4683;//female wearing
class8.anInt164 = 4685;//Female arms/sleeves
class8.anInt188 = 4684;//male arms/sleeves
class8.anInt175 = -1;//Unknown
class8.aBoolean176 = false;//Stackable
class8.aString170 = "Bandos Chestplate";//Name of the new item
class8.aByteArray178 = "Its A Bandos Chestplate".getBytes();//examin info
}
if(i == 15349)  // change this if you need to "item number"
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wield";//New option
class8.anInt174 = 4686; //Model ID
class8.anInt181 = 854;//Model Zoom
class8.anInt190 = 540;//Model Rotation
class8.anInt198 = 2039;//model rotate side ways increase to move right in circle
class8.anInt204 = 144;//Unknown
class8.anInt199 = -1;//noteable
class8.anInt169 = 9;// model offset increase to move to the right
class8.anInt194 = 5;//model offset increase to move up
class8.anInt165 = 4687;//male wearing
class8.anInt200 = 4688;//female wearing
class8.anInt175 = -1;//Unknown
class8.aBoolean176 = false;//Stackable
class8.anInt164 = -1;//Female arms/sleeves
class8.anInt188 = -1;//male arms/sleeves
class8.aBoolean176 = false;//Stackable
class8.aString170 = "Bandos Tassets";//Name of the new item
class8.aByteArray178 = "Its A Bandos Tassets".getBytes();//examin info
}

if(i == 14860)  // change this if you need to "item number"
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wield";//New option
class8.anInt174 = 7202; //Model ID
class8.aBoolean176 = false;//Stackable
class8.anInt181 = 984; //zoom increase will make it smaller
class8.anInt190 = 126; //model rotate up+down increase to move doen away from you
class8.anInt198 = 129; //model rotate side ways increase to move right in circle
class8.anInt169 = -1; // model offset increase to move to the right
class8.anInt194 = 1; //model offset increase to move up
class8.anInt204 = 28;
class8.anInt165 = 7200;//male wearing
class8.anInt200 = 7201;//female wearing
class8.anInt204 = 28;
class8.anInt175 = -1;//Unknown
class8.anInt164 = -1;//Female arms/sleeves
class8.anInt188 = -1;//male arms/sleeves
class8.aBoolean176 = false;//Stackable
class8.aString170 = "Helm of Neitiznot";//Name of the new item
class8.aByteArray178 = "Its A Helm Of Neitiznot".getBytes();//examin info
}
if(i == 14915)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[1];
class8.anIntArray160 = new int[1];
class8.anIntArray156[0] = 0;
class8.anIntArray160[0] = 0;
class8.anInt174 = 13039; //Model ID
class8.anInt181 = 1000;
class8.anInt169 = -5;
class8.anInt194 = 2;
class8.anInt190 = 321;
class8.anInt198 = 24;
class8.anInt197 = -1;
class8.anInt164 = -1;
class8.anInt165 = 13040; //Male equip
class8.anInt200 = 13040; //Female equip
class8.anInt188 = -1;
class8.aBoolean176 = false;
class8.aString170 = "Barrelchest Anchor";
class8.aByteArray178 = "Very Very Heavy.".getBytes();
}
if(i == 15195)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wield";
class8.anInt174 = 2662; //Model Drop/inv view
class8.anInt181 = 789;
class8.anInt190 = 135;
class8.anInt198 = 123;
class8.anInt199 = -1;
class8.anInt169 = -1;
class8.anInt194 = -1;
class8.anInt165 = 2632; //male wearing
class8.anInt200 = 2658; //female wearing
class8.anInt164 = -1; //Female arms/sleeves
class8.anInt188 = -1; //male arms/sleeves
class8.anInt175 = -1;
class8.aBoolean176 = false;
class8.aString170 = "Dragon Full Helm";
class8.aByteArray178 = "A Full Face Helmet.".getBytes();
}
if(i == 14503)  // change this if you need to "item number"
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wield";//New option
class8.anInt174 = 7175; //Model ID
class8.anInt181 = 1200; //zoom increase will make it smaller
class8.anInt190 = 572; //model rotate up+down increase to move doen away from you
class8.anInt198 = 0; //model rotate side ways increase to move right in circle
class8.anInt169 = 0; // model offset increase to move to the right
class8.anInt194 = 1; //model offset increase to move up
class8.anInt165 = 7150;//male wearing
class8.anInt200 = 7165;//female wearing
class8.anInt164 = 7154;//Female arms/sleeves
class8.anInt188 = 7140;//male arms/sleeves
class8.aBoolean176 = false;//Stackable
class8.aString170 = "3rd Age Range Top";//Name of the new item
class8.aByteArray178 = "An Ancient Range Top".getBytes();//examin info
}
if(i == 14504)  // change this if you need to "item number"
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wield";//New option
class8.anInt174 = 7174; //Model ID
class8.anInt181 = 1827; //zoom increase will make it smaller
class8.anInt190 = 572; //model rotate up+down increase to move doen away from you
class8.anInt198 = 0; //model rotate side ways increase to move right in circle
class8.anInt169 = 0; // model offset increase to move to the right
class8.anInt194 = 1; //model offset increase to move up
class8.anInt165 = 7147;//male wearing
class8.anInt200 = 7161;//female wearing
class8.anInt164 = -1;//Female arms/sleeves
class8.anInt188 = -1;//male arms/sleeves
class8.aBoolean176 = false;//Stackable
class8.aString170 = "3rd Age Range Legs";//Name of the new item
class8.aByteArray178 = "Ancient Range Legs".getBytes();//examin info
}
if(i == 14505)  // change this if you need to "item number"
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wield";//New option
class8.anInt174 = 7173; //Model ID
class8.anInt181 = 917; //zoom increase will make it smaller
class8.anInt190 = 212; //model rotate up+down increase to move doen away from you
class8.anInt198 = 1883; //model rotate side ways increase to move right in circle
class8.anInt169 = 0; // model offset increase to move to the right
class8.anInt194 = 0; //model offset increase to move up
class8.anInt165 = 7144;//male wearing
class8.anInt200 = 7158;//female wearing
class8.anInt164 = -1;//Female arms/sleeves
class8.anInt188 = -1;//male arms/sleeves
class8.aBoolean176 = false;//Stackable
class8.aString170 = "3rd Age Range Coif";//Name of the new item
class8.aByteArray178 = "An Ancient Range Coif.".getBytes();//examin info
}
if(i == 14506)  // change this if you need to "item number"
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wield";//New option
class8.anInt174 = 7176; //Model ID
class8.anInt181 = 740; //zoom increase will make it smaller
class8.anInt190 = 196; //model rotate up+down increase to move doen away from you
class8.anInt198 = 1784; //model rotate side ways increase to move right in circle
class8.anInt169 = 0; // model offset increase to move to the right
class8.anInt194 = 0; //model offset increase to move up
class8.anInt165 = 7141;//male wearing
class8.anInt200 = 7155;//female wearing
class8.anInt164 = -1;//Female arms/sleeves
class8.anInt188 = -1;//male arms/sleeves
class8.aBoolean176 = false;//Stackable
class8.aString170 = "3rd Age Vambraces";//Name of the new item
class8.aByteArray178 = "Ancient 3rd Age Vambrances.".getBytes();//examin info
}
if(i == 14507)  // change this if you need to "item number"
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wield";//New option
class8.anInt174 = 7172; //Model ID
class8.anInt181 = 2083; //zoom increase will make it smaller
class8.anInt190 = 572; //model rotate up+down increase to move doen away from you
class8.anInt198 = 0; //model rotate side ways increase to move right in circle
class8.anInt169 = 0; // model offset increase to move to the right
class8.anInt194 = 0; //model offset increase to move up
class8.anInt165 = 7149;//male wearing
class8.anInt200 = 7164;//female wearing
class8.anInt164 = 7153;//Female arms/sleeves
class8.anInt188 = 7139;//male arms/sleeves
class8.aBoolean176 = false;//Stackable
class8.aString170 = "3rd Age Robe Top";//Name of the new item
class8.aByteArray178 = "A Ancient Robe Top.".getBytes();//examin info
}
if(i == 14508)  // change this if you need to "item number"
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wield";//New option
class8.anInt174 = 7171; //Model ID
class8.anInt181 = 2083; //zoom increase will make it smaller
class8.anInt190 = 572; //model rotate up+down increase to move doen away from you
class8.anInt198 = 0; //model rotate side ways increase to move right in circle
class8.anInt169 = 0; // model offset increase to move to the right
class8.anInt194 = 1; //model offset increase to move up
class8.anInt165 = 7146;//male wearing
class8.anInt200 = 7160;//female wearing
class8.anInt164 = -1;//Female arms/sleeves
class8.anInt188 = -1;//male arms/sleeves
class8.aBoolean176 = false;//Stackable
class8.aString170 = "3rd Age Robe";//Name of the new item
class8.aByteArray178 = "An Ancient Robe.".getBytes();//examin info
}
if(i == 14509)  // change this if you need to "item number"
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wield";//New option
class8.anInt174 = 7170; //Model ID
class8.anInt181 = 917; //zoom increase will make it smaller
class8.anInt190 = 212; //model rotate up+down increase to move doen away from you
class8.anInt198 = 1883; //model rotate side ways increase to move right in circle
class8.anInt169 = 0; // model offset increase to move to the right
class8.anInt194 = 0; //model offset increase to move up
class8.anInt165 = 7143;//male wearing
class8.anInt200 = 7157;//female wearing
class8.anInt164 = -1;//Female arms/sleeves
class8.anInt188 = -1;//male arms/sleeves
class8.aBoolean176 = false;//Stackable
class8.aString170 = "3rd Age Mage Hat";//Name of the new item
class8.aByteArray178 = "An Ancient Hat".getBytes();//examin info
}
if(i == 14511)  // change this if you need to "item number"
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wield";//New option
class8.anInt174 = 7167; //Model ID
class8.anInt181 = 1740; //zoom increase will make it smaller
class8.anInt190 = 572; //model rotate up+down increase to move doen away from you
class8.anInt198 = 0; //model rotate side ways increase to move right in circle
class8.anInt169 = 4; // model offset increase to move to the right
class8.anInt194 = 11; //model offset increase to move up
class8.anInt165 = 7145;//male wearing
class8.anInt200 = 7159;//female wearing
class8.anInt164 = -1;//Female arms/sleeves
class8.anInt188 = -1;//male arms/sleeves
class8.aBoolean176 = false;//Stackable
class8.aString170 = "3rd Age PlateLegs";//Name of the new item
class8.aByteArray178 = "Ancient Set Of PlateLegs".getBytes();//examin info
}
if(i == 14512)  // change this if you need to "item number"
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wield";//New option
class8.anInt174 = 7169; //Model ID
class8.anInt181 = 1200; //zoom increase will make it smaller
class8.anInt190 = 572; //model rotate up+down increase to move doen away from you
class8.anInt198 = 0; //model rotate side ways increase to move right in circle
class8.anInt169 = 0; // model offset increase to move to the right
class8.anInt194 = 1; //model offset increase to move up
class8.anInt165 = 7148;//male wearing
class8.anInt200 = 7163;//female wearing
class8.anInt164 = 7152;//Female arms/sleeves
class8.anInt188 = 7138;//male arms/sleeves
class8.aBoolean176 = false;//Stackable
class8.aString170 = "3rd Age PlateBody";//Name of the new item
class8.aByteArray178 = "An Ancient PlateBody".getBytes();//examin info
}
if(i == 14513)  // change this if you need to "item number"
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wield";//New option
class8.anInt174 = 7166; //Model ID
class8.anInt181 = 720; //zoom increase will make it smaller
class8.anInt190 = 28; //model rotate up+down increase to move doen away from you
class8.anInt198 = 1892; //model rotate side ways increase to move right in circle
class8.anInt169 = 0; // model offset increase to move to the right
class8.anInt194 = 0; //model offset increase to move up
class8.anInt165 = 7142;//male wearing
class8.anInt200 = 7156;//female wearing
class8.anInt164 = -1;//Female arms/sleeves
class8.anInt188 = -1;//male arms/sleeves
class8.aBoolean176 = false;//Stackable
class8.aString170 = "3rd Age Full helm";//Name of the new item
class8.aByteArray178 = "A Ancient Full helm".getBytes();//examin info
}
if(i == 14514)  // change this if you need to "item number"
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wield";//New option
class8.anInt174 = 7168; //Model ID
class8.anInt181 = 2030; //zoom increase will make it smaller
class8.anInt190 = 364; //model rotate up+down increase to move doen away from you
class8.anInt198 = 56; //model rotate side ways increase to move right in circle
class8.anInt169 = 0; // model offset increase to move to the right
class8.anInt194 = 0; //model offset increase to move up
class8.anInt165 = 7151;//male wearing
class8.anInt200 = 7151;//female wearing
class8.anInt164 = -1;//Female arms/sleeves
class8.anInt188 = -1;//male arms/sleeves
class8.aBoolean176 = false;//Stackable
class8.aString170 = "3rd Age KiteShield";//Name of the new item
class8.aByteArray178 = "A Ancient Kiteshield".getBytes();//examin info
}

 if(i == 10109){ //orange phat
 class8.aStringArray189 = new String[5];
 class8.aStringArray189[1] = "Wear";
 class8.anIntArray156 = new int[1];
 class8.anIntArray160 = new int[1];
 class8.anIntArray156[0] = 926;
 class8.anIntArray160[0] = 6073;
 class8.anInt174 = 2635;
 class8.anInt181 = 440;
 class8.anInt190 = 76;
 class8.anInt198 = 1850;
 class8.anInt204 = 0;
 class8.anInt169 = 1;
 class8.anInt194 = 1;
 class8.anInt165 = 187;
 class8.anInt200 = 363;
 class8.anInt175 = 29;
 class8.anInt197 = 87;
 class8.aString170 = "Orange Party Hat";
 class8.aByteArray178 = "An Orange Party Hat.".getBytes();
 }

 if(i == 10110){ //Gold phat 
 class8.aStringArray189 = new String[5];
 class8.aStringArray189[1] = "Wear";
 class8.anIntArray156 = new int[1];
 class8.anIntArray160 = new int [1];
 class8.anIntArray156[0] = 926;
 class8.anIntArray160[0] = 8128;		
 class8.anInt174 = 2635;
 class8.anInt181 = 440;
 class8.anInt190 = 76;
 class8.anInt198 = 1850;
 class8.anInt204 = 0;
 class8.anInt169 = 1;
 class8.anInt194 = 1;
 class8.anInt165 = 187;
 class8.anInt200 = 363;
 class8.anInt175 = 29;
 class8.anInt197 = 87;
 class8.aString170 = "Gold Party Hat";
 class8.aByteArray178 = "A Gold Party Hat.".getBytes();
 }

 if(i == 10111){ 
 class8.aStringArray189 = new String[5];
 class8.aStringArray189[1] = "Wear";
 class8.anIntArray156 = new int[1];
 class8.anIntArray160 = new int [1];
 class8.anIntArray156[0] = 926;
 class8.anIntArray160[0] = 50;		
 class8.anInt174 = 2635;
 class8.anInt181 = 440;
 class8.anInt190 = 76;
 class8.anInt198 = 1850;
 class8.anInt204 = 0;
 class8.anInt169 = 1;
 class8.anInt194 = 1;
 class8.anInt165 = 187;
 class8.anInt200 = 363;
 class8.anInt175 = 29;
 class8.anInt197 = 87;
 class8.aString170 = "Grey Party Hat";
 class8.aByteArray178 = "A Grey Party Hat.".getBytes();
 }

 if(i == 10112){ //Bronze phat 
 class8.aStringArray189 = new String[5];
 class8.aStringArray189[1] = "Wear";
 class8.anIntArray156 = new int[1];
 class8.anIntArray160 = new int [1];
 class8.anIntArray156[0] = 926;
 class8.anIntArray160[0] = 5652;		
 class8.anInt174 = 2635;
 class8.anInt181 = 440;
 class8.anInt190 = 76;
 class8.anInt198 = 1850;
 class8.anInt204 = 0;
 class8.anInt169 = 1;
 class8.anInt194 = 1;
 class8.anInt165 = 187;
 class8.anInt200 = 363;
 class8.anInt175 = 29;
 class8.anInt197 = 87;
 class8.aString170 = "Bronze Party Hat";
 class8.aByteArray178 = "A Bronze Party Hat.".getBytes();
 }

 if(i == 10113){ //Steel phat 
 class8.aStringArray189 = new String[5];
 class8.aStringArray189[1] = "Wear";
 class8.anIntArray156 = new int[1];
 class8.anIntArray160 = new int [1];
 class8.anIntArray156[0] = 926;
 class8.anIntArray160[0] = 41;		
 class8.anInt174 = 2635;
 class8.anInt181 = 440;
 class8.anInt190 = 76;
 class8.anInt198 = 1850;
 class8.anInt204 = 0;
 class8.anInt169 = 1;
 class8.anInt194 = 1;
 class8.anInt165 = 187;
 class8.anInt200 = 363;
 class8.anInt175 = 29;
 class8.anInt197 = 87;
 class8.aString170 = "Steel Party Hat";
 class8.aByteArray178 = "A Steel Party Hat.".getBytes();
 }

 if(i == 10114){ //Mithril phat 
 class8.aStringArray189 = new String[5];
 class8.aStringArray189[1] = "Wear";
 class8.anIntArray156 = new int[1];
 class8.anIntArray160 = new int [1];
 class8.anIntArray156[0] = 926;
 class8.anIntArray160[0] = 43297;		
 class8.anInt174 = 2635;
 class8.anInt181 = 440;
 class8.anInt190 = 76;
 class8.anInt198 = 1850;
 class8.anInt204 = 0;
 class8.anInt169 = 1;
 class8.anInt194 = 1;
 class8.anInt165 = 187;
 class8.anInt200 = 363;
 class8.anInt175 = 29;
 class8.anInt197 = 87;
 class8.aString170 = "Mithril Party Hat";
 class8.aByteArray178 = "A Mithril Party Hat.".getBytes();
 }

 if(i == 10115){ //Adamant phat 
 class8.aStringArray189 = new String[5];
 class8.aStringArray189[1] = "Wear";
 class8.anIntArray156 = new int[1];
 class8.anIntArray160 = new int [1];
 class8.anIntArray156[0] = 926;
 class8.anIntArray160[0] = 21662;		
 class8.anInt174 = 2635;
 class8.anInt181 = 440;
 class8.anInt190 = 76;
 class8.anInt198 = 1850;
 class8.anInt204 = 0;
 class8.anInt169 = 1;
 class8.anInt194 = 1;
 class8.anInt165 = 187;
 class8.anInt200 = 363;
 class8.anInt175 = 29;
 class8.anInt197 = 87;
 class8.aString170 = "Adamant Party Hat";
 class8.aByteArray178 = "An Addy Party Hat.".getBytes();
 }

 if(i == 10116){ //Rune phat 
 class8.aStringArray189 = new String[5];
 class8.aStringArray189[1] = "Wear";
 class8.anIntArray156 = new int[1];
 class8.anIntArray160 = new int [1];
 class8.anIntArray156[0] = 926;
 class8.anIntArray160[0] = 36133;		
 class8.anInt174 = 2635;
 class8.anInt181 = 440;
 class8.anInt190 = 76;
 class8.anInt198 = 1850;
 class8.anInt204 = 0;
 class8.anInt169 = 1;
 class8.anInt194 = 1;
 class8.anInt165 = 187;
 class8.anInt200 = 363;
 class8.anInt175 = 29;
 class8.anInt197 = 87;
 class8.aString170 = "Rune Party Hat";
 class8.aByteArray178 = "A Rune Party Hat.".getBytes();
 }

 if(i == 10117){ //Dragon phat 
 class8.aStringArray189 = new String[5];
 class8.aStringArray189[1] = "Wear";
 class8.anIntArray156 = new int[1];
 class8.anIntArray160 = new int [1];
 class8.anIntArray156[0] = 926;
 class8.anIntArray160[0] = 924;		
 class8.anInt174 = 2635;
 class8.anInt181 = 440;
 class8.anInt190 = 76;
 class8.anInt198 = 1850;
 class8.anInt204 = 0;
 class8.anInt169 = 1;
 class8.anInt194 = 1;
 class8.anInt165 = 187;
 class8.anInt200 = 363;
 class8.anInt175 = 29;
 class8.anInt197 = 87;
 class8.aString170 = "Dragon Party Hat";
 class8.aByteArray178 = "A Dragon Party Hat.".getBytes();
 }

 if(i == 10118){ //Barrows phat 
 class8.aStringArray189 = new String[5];
 class8.aStringArray189[1] = "Wear";
 class8.anIntArray156 = new int[1];
 class8.anIntArray160 = new int [1];
 class8.anIntArray156[0] = 926;
 class8.anIntArray160[0] = 10388;		
 class8.anInt174 = 2635;
 class8.anInt181 = 440;
 class8.anInt190 = 76;
 class8.anInt198 = 1850;
 class8.anInt204 = 0;
 class8.anInt169 = 1;
 class8.anInt194 = 1;
 class8.anInt165 = 187;
 class8.anInt200 = 363;
 class8.anInt175 = 29;
 class8.anInt197 = 87;
 class8.aString170 = "Barrows Party Hat";
 class8.aByteArray178 = "A Party Hat worn by the Barrows Brothers.".getBytes();
 }
if(i == 10090)
        {
            class8.aStringArray189 = new String[5];
            class8.aStringArray189[1] = "Wear";
            class8.anIntArray156 = new int[2];
            class8.anIntArray160 = new int[2];
            class8.anIntArray156[0] = 933;
            class8.anIntArray160[0] = 51136;
            class8.anIntArray156[1] = 10351;
            class8.anIntArray160[1] = 7097;
            class8.anInt174 = 2537;
            class8.anInt181 = 540;
            class8.anInt190 = 72;
            class8.anInt198 = 136;
            class8.anInt204 = 0;
            class8.anInt169 = 0;
            class8.anInt194 = -3;
            class8.anInt165 = 189;
            class8.anInt188 = -1;
            class8.anInt200 = 366;
            class8.anInt164 = -1;
            class8.anInt175 = 69;
            class8.anInt197 = 127;
            class8.aBoolean176 = false;
            class8.aString170 = "Purple santa hat ";
            class8.aByteArray178 = "Purple santa hat.".getBytes();
        }
if(i == 10091)
        {
            class8.aStringArray189 = new String[5];
            class8.aStringArray189[1] = "Wear";
            class8.anIntArray156 = new int[2];
            class8.anIntArray160 = new int[2];
            class8.anIntArray156[0] = 933;
            class8.anIntArray160[0] = 6;
            class8.anIntArray156[1] = 10351;
            class8.anIntArray160[1] = 7097;
            class8.anInt174 = 2537;
            class8.anInt181 = 540;
            class8.anInt190 = 72;
            class8.anInt198 = 136;
            class8.anInt204 = 0;
            class8.anInt169 = 0;
            class8.anInt194 = -3;
            class8.anInt165 = 189;
            class8.anInt188 = -1;
            class8.anInt200 = 366;
            class8.anInt164 = -1;
            class8.anInt175 = 69;
            class8.anInt197 = 127;
            class8.aBoolean176 = false;
            class8.aString170 = "Black santa hat ";
            class8.aByteArray178 = "Black santa hat.".getBytes();
        }

if(i == 10092)
        {
            class8.aStringArray189 = new String[5];
            class8.aStringArray189[1] = "Wear";
            class8.anIntArray156 = new int[2];
            class8.anIntArray160 = new int[2];
            class8.anIntArray156[0] = 933;
            class8.anIntArray160[0] = 43297;
            class8.anIntArray156[1] = 10351;
            class8.anIntArray160[1] = 7097;
            class8.anInt174 = 2537;
            class8.anInt181 = 540;
            class8.anInt190 = 72;
            class8.anInt198 = 136;
            class8.anInt204 = 0;
            class8.anInt169 = 0;
            class8.anInt194 = -3;
            class8.anInt165 = 189;
            class8.anInt188 = -1;
            class8.anInt200 = 366;
            class8.anInt164 = -1;
            class8.anInt175 = 69;
            class8.anInt197 = 127;
            class8.aBoolean176 = false;
            class8.aString170 = "Mithril santa hat ";
            class8.aByteArray178 = "Mithril santa hat.".getBytes();
        }

 if(i == 10093)
        {
            class8.aStringArray189 = new String[5];
            class8.aStringArray189[1] = "Wear";
            class8.anIntArray156 = new int[2];
            class8.anIntArray160 = new int[2];
            class8.anIntArray156[0] = 933;
            class8.anIntArray160[0] = 36133;
            class8.anIntArray156[1] = 10351;
            class8.anIntArray160[1] = 7097;
            class8.anInt174 = 2537;
            class8.anInt181 = 540;
            class8.anInt190 = 72;
            class8.anInt198 = 136;
            class8.anInt204 = 0;
            class8.anInt169 = 0;
            class8.anInt194 = -3;
            class8.anInt165 = 189;
            class8.anInt188 = -1;
            class8.anInt200 = 366;
            class8.anInt164 = -1;
            class8.anInt175 = 69;
            class8.anInt197 = 127;
            class8.aBoolean176 = false;
            class8.aString170 = "Rune santa hat ";
            class8.aByteArray178 = "Rune santa hat.".getBytes();
        }

        if(i == 10094)
        {
            class8.aStringArray189 = new String[5];
            class8.aStringArray189[1] = "Wear";
            class8.anIntArray156 = new int[2];
            class8.anIntArray160 = new int[2];
            class8.anIntArray156[0] = 933;
            class8.anIntArray160[0] = 1000;
            class8.anIntArray156[1] = 10351;
            class8.anIntArray160[1] = 7097;
            class8.anInt174 = 2537;
            class8.anInt181 = 540;
            class8.anInt190 = 72;
            class8.anInt198 = 136;
            class8.anInt204 = 0;
            class8.anInt169 = 0;
            class8.anInt194 = -3;
            class8.anInt165 = 189;
            class8.anInt188 = -1;
            class8.anInt200 = 366;
            class8.anInt164 = -1;
            class8.anInt175 = 69;
            class8.anInt197 = 127;
            class8.aBoolean176 = false;
            class8.aString170 = "Dragon santa hat ";
            class8.aByteArray178 = "Dragon santa hat.".getBytes();
        }

        if(i == 10095)
        {
            class8.aStringArray189 = new String[5];
            class8.aStringArray189[1] = "Wear";
            class8.anIntArray156 = new int[2];
            class8.anIntArray160 = new int[2];
            class8.anIntArray156[0] = 933;
            class8.anIntArray160[0] = 21662 ;
            class8.anIntArray156[1] = 10351;
            class8.anIntArray160[1] = 7097;
            class8.anInt174 = 2537;
            class8.anInt181 = 540;
            class8.anInt190 = 72;
            class8.anInt198 = 136;
            class8.anInt204 = 0;
            class8.anInt169 = 0;
            class8.anInt194 = -3;
            class8.anInt165 = 189;
            class8.anInt188 = -1;
            class8.anInt200 = 366;
            class8.anInt164 = -1;
            class8.anInt175 = 69;
            class8.anInt197 = 127;
            class8.aBoolean176 = false;
            class8.aString170 = "Addy santa hat ";
            class8.aByteArray178 = "Addy santa hat.".getBytes();
        }

        if(i == 10096)
        {
            class8.aStringArray189 = new String[5];
            class8.aStringArray189[1] = "Wear";
            class8.anIntArray156 = new int[2];
            class8.anIntArray160 = new int[2];
            class8.anIntArray156[0] = 933;
            class8.anIntArray160[0] = 7114;
            class8.anIntArray156[1] = 10351;
            class8.anIntArray160[1] = 7097;
            class8.anInt174 = 2537;
            class8.anInt181 = 540;
            class8.anInt190 = 72;
            class8.anInt198 = 136;
            class8.anInt204 = 0;
            class8.anInt169 = 0;
            class8.anInt194 = -3;
            class8.anInt165 = 189;
            class8.anInt188 = -1;
            class8.anInt200 = 366;
            class8.anInt164 = -1;
            class8.anInt175 = 69;
            class8.anInt197 = 127;
            class8.aBoolean176 = false;
            class8.aString170 = "Gold santa hat ";
            class8.aByteArray178 = "Gold santa hat.".getBytes();
        }

        if(i == 10097)
        {
            class8.aStringArray189 = new String[5];
            class8.aStringArray189[1] = "Wear";
            class8.anIntArray156 = new int[2];
            class8.anIntArray160 = new int[2];
            class8.anIntArray156[0] = 933;
            class8.anIntArray160[0] = 10394;
            class8.anIntArray156[1] = 10351;
            class8.anIntArray160[1] = 7097;
            class8.anInt174 = 2537;
            class8.anInt181 = 540;
            class8.anInt190 = 72;
            class8.anInt198 = 136;
            class8.anInt204 = 0;
            class8.anInt169 = 0;
            class8.anInt194 = -3;
            class8.anInt165 = 189;
            class8.anInt188 = -1;
            class8.anInt200 = 366;
            class8.anInt164 = -1;
            class8.anInt175 = 69;
            class8.anInt197 = 127;
            class8.aBoolean176 = false;
            class8.aString170 = "Barrows santa hat ";
            class8.aByteArray178 = "Barrows santa hat.".getBytes();
        }

        if(i == 10098)
        {
            class8.aStringArray189 = new String[5];
            class8.aStringArray189[1] = "Wear";
            class8.anIntArray156 = new int[2];
            class8.anIntArray160 = new int[2];
            class8.anIntArray156[0] = 933;
            class8.anIntArray160[0] = 43968;
            class8.anIntArray156[1] = 10351;
            class8.anIntArray160[1] = 7097;
            class8.anInt174 = 2537;
            class8.anInt181 = 540;
            class8.anInt190 = 72;
            class8.anInt198 = 136;
            class8.anInt204 = 0;
            class8.anInt169 = 0;
            class8.anInt194 = -3;
            class8.anInt165 = 189;
            class8.anInt188 = -1;
            class8.anInt200 = 366;
            class8.anInt164 = -1;
            class8.anInt175 = 69;
            class8.anInt197 = 127;
            class8.aBoolean176 = false;
            class8.aString170 = "Blue santa hat ";
            class8.aByteArray178 = "Blue santa hat.".getBytes();
        }

if(i == 10099)
        {
            class8.aStringArray189 = new String[5];
            class8.aStringArray189[1] = "Wear";
            class8.anIntArray156 = new int[2];
            class8.anIntArray160 = new int[2];
            class8.anIntArray156[0] = 933;
            class8.anIntArray160[0] = 51136;
            class8.anIntArray156[1] = 10351;
            class8.anIntArray160[1] = 7097;
            class8.anInt174 = 2537;
            class8.anInt181 = 540;
            class8.anInt190 = 72;
            class8.anInt198 = 136;
            class8.anInt204 = 0;
            class8.anInt169 = 0;
            class8.anInt194 = -3;
            class8.anInt165 = 189;
            class8.anInt188 = -1;
            class8.anInt200 = 366;
            class8.anInt164 = -1;
            class8.anInt175 = 69;
            class8.anInt197 = 127;
            class8.aBoolean176 = false;
            class8.aString170 = "Purple santa hat ";
            class8.aByteArray178 = "Purple santa hat.".getBytes();
        }

if(i == 10100)
        {
            class8.aStringArray189 = new String[5];
            class8.aStringArray189[1] = "Wear"; 
            class8.anIntArray156 = new int[1];
        class8.anIntArray160 = new int[1];
class8.anIntArray156[0] = 528;  
class8.anIntArray160[0] = 6073;       
class8.anInt174 =  5412;
class8.anInt181 =  840;
class8.anInt190 =  280;
class8.anInt198 =  0;
class8.anInt204 =  0;
class8.anInt169 =  -2;
class8.anInt194 =  56;
class8.anInt165 =  5409;
class8.anInt200 =  5409;
class8.anInt188 =  -1;
class8.anInt164 =  -1;
class8.anInt175 =  -1;
class8.anInt197 =  -1;
            class8.aString170 = "Orange Whip";
            class8.aByteArray178 = "a Orange Whip".getBytes();
        }

if(i == 10101)
        {
            class8.aStringArray189 = new String[5];
            class8.aStringArray189[1] = "Wear"; 
            class8.anIntArray156 = new int[1];
        class8.anIntArray160 = new int[1];
class8.anIntArray156[0] = 528;  
class8.anIntArray160[0] = 51136;       
class8.anInt174 =  5412;
class8.anInt181 =  840;
class8.anInt190 =  280;
class8.anInt198 =  0;
class8.anInt204 =  0;
class8.anInt169 =  -2;
class8.anInt194 =  56;
class8.anInt165 =  5409;
class8.anInt200 =  5409;
class8.anInt188 =  -1;
class8.anInt164 =  -1;
class8.anInt175 =  -1;
class8.anInt197 =  -1;
            class8.aString170 = "Purple Whip";
            class8.aByteArray178 = "a Purple Whip".getBytes();
        }

if(i == 10102)
        {
            class8.aStringArray189 = new String[5];
            class8.aStringArray189[1] = "Wear"; 
            class8.anIntArray156 = new int[1];
        class8.anIntArray160 = new int[1];
class8.anIntArray156[0] = 528;  
class8.anIntArray160[0] = 25;       
class8.anInt174 =  5412;
class8.anInt181 =  840;
class8.anInt190 =  280;
class8.anInt198 =  0;
class8.anInt204 =  0;
class8.anInt169 =  -2;
class8.anInt194 =  56;
class8.anInt165 =  5409;
class8.anInt200 =  5409;
class8.anInt188 =  -1;
class8.anInt164 =  -1;
class8.anInt175 =  -1;
class8.anInt197 =  -1;
            class8.aString170 = "Unknown Whip";
            class8.aByteArray178 = "A Unknown Whip".getBytes();
        }

if(i == 10103)
        {
            class8.aStringArray189 = new String[5];
            class8.aStringArray189[1] = "Wear"; 
            class8.anIntArray156 = new int[1];
        class8.anIntArray160 = new int[1];
class8.anIntArray156[0] = 528;  
class8.anIntArray160[0] = 950;       
class8.anInt174 =  5412;
class8.anInt181 =  840;
class8.anInt190 =  280;
class8.anInt198 =  0;
class8.anInt204 =  0;
class8.anInt169 =  -2;
class8.anInt194 =  56;
class8.anInt165 =  5409;
class8.anInt200 =  5409;
class8.anInt188 =  -1;
class8.anInt164 =  -1;
class8.anInt175 =  -1;
class8.anInt197 =  -1;
            class8.aString170 = "Red Whip";
            class8.aByteArray178 = "A Red Whip".getBytes();
        }

if(i == 10104)
        {
            class8.aStringArray189 = new String[5];
            class8.aStringArray189[1] = "Wear"; 
            class8.anIntArray156 = new int[1];
        class8.anIntArray160 = new int[1];
class8.anIntArray156[0] = 528;  
class8.anIntArray160[0] = 43968;       
class8.anInt174 =  5412;
class8.anInt181 =  840;
class8.anInt190 =  280;
class8.anInt198 =  0;
class8.anInt204 =  0;
class8.anInt169 =  -2;
class8.anInt194 =  56;
class8.anInt165 =  5409;
class8.anInt200 =  5409;
class8.anInt188 =  -1;
class8.anInt164 =  -1;
class8.anInt175 =  -1;
class8.anInt197 =  -1;
            class8.aString170 = "Blue Whip";
            class8.aByteArray178 = "A Blue Whip".getBytes();
        }

if(i == 10105)
        {
            class8.aStringArray189 = new String[5];
            class8.aStringArray189[1] = "Wear"; 
            class8.anIntArray156 = new int[1];
        class8.anIntArray160 = new int[1];
class8.anIntArray156[0] = 528;  
class8.anIntArray160[0] = 10394;       
class8.anInt174 =  5412;
class8.anInt181 =  840;
class8.anInt190 =  280;
class8.anInt198 =  0;
class8.anInt204 =  0;
class8.anInt169 =  -2;
class8.anInt194 =  56;
class8.anInt165 =  5409;
class8.anInt200 =  5409;
class8.anInt188 =  -1;
class8.anInt164 =  -1;
class8.anInt175 =  -1;
class8.anInt197 =  -1;
            class8.aString170 = " Barrows Whip";
            class8.aByteArray178 = "A Barrows Whip".getBytes();
        }

if(i == 10106)
        {
            class8.aStringArray189 = new String[5];
            class8.aStringArray189[1] = "Wear"; 
            class8.anIntArray156 = new int[1];
        class8.anIntArray160 = new int[1];
class8.anIntArray156[0] = 528;  
class8.anIntArray160[0] = 7114;       
class8.anInt174 =  5412;
class8.anInt181 =  840;
class8.anInt190 =  280;
class8.anInt198 =  0;
class8.anInt204 =  0;
class8.anInt169 =  -2;
class8.anInt194 =  56;
class8.anInt165 =  5409;
class8.anInt200 =  5409;
class8.anInt188 =  -1;
class8.anInt164 =  -1;
class8.anInt175 =  -1;
class8.anInt197 =  -1;
            class8.aString170 = "Gold Whip";
            class8.aByteArray178 = "A Gold Whip".getBytes();
        }

if(i == 10107)
        {
            class8.aStringArray189 = new String[5];
            class8.aStringArray189[1] = "Wear"; 
            class8.anIntArray156 = new int[1];
        class8.anIntArray160 = new int[1];
class8.anIntArray156[0] = 528;  
class8.anIntArray160[0] = 100;       
class8.anInt174 =  5412;
class8.anInt181 =  840;
class8.anInt190 =  280;
class8.anInt198 =  0;
class8.anInt204 =  0;
class8.anInt169 =  -2;
class8.anInt194 =  56;
class8.anInt165 =  5409;
class8.anInt200 =  5409;
class8.anInt188 =  -1;
class8.anInt164 =  -1;
class8.anInt175 =  -1;
class8.anInt197 =  -1;
            class8.aString170 = "White Whip";
            class8.aByteArray178 = "A White Whip".getBytes();
        }
if(i == 15343){ //lava helm
              class8.aStringArray189 = new String[5];
              class8.aStringArray189[1] = "Wear";
              class8.anIntArray156 = new int[2];
              class8.anIntArray160 = new int [2];
              class8.anIntArray156[0] = 10394;
              class8.anIntArray160[0] = 6069;      
              class8.anIntArray156[1] = 6020;
              class8.anIntArray160[1] = 6020;
 class8.anInt174= 6583;
class8.anInt181= 860;
class8.anInt190= 2012;
class8.anInt198= 188;
class8.anInt204= 0;
class8.anInt169= 17;
class8.anInt194= 0;
class8.anInt165= 6653;
class8.anInt200= 6687;
class8.anInt188= -1;
class8.anInt164= -1;
class8.anInt175= 6570;
class8.anInt197= 6575;
              class8.aString170 = "Lava Helm";
              class8.aByteArray178 = "It's a Lava helm".getBytes();
 
}
if(i == 15107) { //ladies legs
		class8.aStringArray189 = new String[5];
		class8.aStringArray189[1] = "Wear";
		class8.anIntArray156 = new int[2];
		class8.anIntArray160 = new int [2];
		class8.anIntArray156[0] = 926;
		class8.anIntArray160[0] = 62920;
		class8.anIntArray156[1] = 912;
		class8.anIntArray160[1] = 62928;
	class8.anInt181 = 1740;
	class8.anInt190 = 444;
	class8.anInt198 = 0;
	class8.anInt204 = 0;
	class8.anInt169 = 0;
	class8.anInt194 = -8;
		class8.anInt165 = 5024;
		class8.anInt200 = 5025;
		class8.anInt174 = 5026;
	class8.anInt188 = -1;
	class8.anInt164 = -1;
	class8.anInt175 = -1;
	class8.anInt197 = -1;
	class8.aString170 = "Ladies Platelegs";
	class8.aByteArray178 = "Ladies platelegs".getBytes();
}
if(i == 15339) { //lava
		class8.aStringArray189 = new String[5];
		class8.aStringArray189[1] = "Wear";
		class8.anIntArray156 = new int[2];
		class8.anIntArray160 = new int [2];
		class8.anIntArray156[0] = 926;
		class8.anIntArray160[0] = 6072;
		class8.anIntArray156[1] = 912;
		class8.anIntArray160[1] = 6054;
	class8.anInt181 = 1740;
	class8.anInt190 = 444;
	class8.anInt198 = 0;
	class8.anInt204 = 0;
	class8.anInt169 = 0;
	class8.anInt194 = -8;
		class8.anInt165 = 5024;
		class8.anInt200 = 5025;
		class8.anInt174 = 5026;
	class8.anInt188 = -1;
	class8.anInt164 = -1;
	class8.anInt175 = -1;
	class8.anInt197 = -1;
	class8.aString170 = "Lava Platelegs";
	class8.aByteArray178 = "Lava platelegs".getBytes();
}

if(i == 15109){ //ladies helm
              class8.aStringArray189 = new String[5];
              class8.aStringArray189[1] = "Wear";
              class8.anIntArray156 = new int[2];
              class8.anIntArray160 = new int [2];
              class8.anIntArray156[0] = 10394;
              class8.anIntArray160[0] = 62928;      
              class8.anIntArray156[1] = 6020;
              class8.anIntArray160[1] = 6020;
 class8.anInt174= 6583;
class8.anInt181= 860;
class8.anInt190= 2012;
class8.anInt198= 188;
class8.anInt204= 0;
class8.anInt169= 17;
class8.anInt194= 0;
class8.anInt165= 6653;
class8.anInt200= 6687;
class8.anInt188= -1;
class8.anInt164= -1;
class8.anInt175= 6570;
class8.anInt197= 6575;
              class8.aString170 = "Ladies Helm";
              class8.aByteArray178 = "It's a Ladies helm".getBytes();
              }

if(i == 15342){
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[3];
class8.anIntArray160 = new int[3];
class8.anIntArray156[0] = 10283;
class8.anIntArray160[0] = 6069;
class8.anIntArray156[1] = 10270;
class8.anIntArray160[1] = 6073;
class8.anIntArray156[2] = 3326;
class8.anIntArray160[2] = 6074;
class8.anInt174 = 8700;
class8.anInt181 = 710;
class8.anInt190 = 332;
class8.anInt198 = 2000;
class8.anInt204 = 0;
class8.anInt169 = 2;
class8.anInt194 = -14;
class8.anInt165 = 8726;
class8.anInt200 = 8750;
class8.anInt188 = -1;
class8.anInt164 = -1;
class8.anInt175 = -1;
class8.anInt197 = -1;
class8.aString170 = "Lava Gauntlets";
class8.aByteArray178 = "Lava Gauntlets.".getBytes();
}
if(i == 15215){
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[2];
class8.anIntArray160 = new int[2];
class8.anIntArray156[0] = 8741;
class8.anIntArray160[0] = 924;
class8.anIntArray156[1] = 14490;
class8.anIntArray160[1] = 921;
class8.anInt174 = 6578;
class8.anInt181 = 1250;
class8.anInt190 = 468;
class8.anInt198 = 0;
class8.anInt204 = 0;
class8.anInt169 = 0;
class8.anInt194 = 3;
class8.anInt165 = 6669;
class8.anInt200 = 6697;
class8.anInt188 = 170;
class8.anInt164 = 348;
class8.anInt175 = -1;
class8.anInt197 = -1;
class8.aString170 = "Draconic Top";
class8.aByteArray178 = "A Draconic Ahrims Top.".getBytes();
}
if(i == 15216){
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[2];
class8.anIntArray160 = new int[2];
class8.anIntArray156[0] = 14490;
class8.anIntArray160[0] = 924;
class8.anIntArray156[1] = 45468;
class8.anIntArray160[1] = 921;
class8.anInt174 = 6577;
class8.anInt181 = 1730;
class8.anInt190 = 504;
class8.anInt198 = 0;
class8.anInt204 = 0;
class8.anInt169 = 0;
class8.anInt194 = -4;
class8.anInt165 = 6659;
class8.anInt200 = 6691;
class8.anInt188 = -1;
class8.anInt164 = -1;
class8.anInt175 = -1;
class8.anInt197 = -1;
class8.aString170 = "Draconic Bottoms";
class8.aByteArray178 = "Draconic Ahrims Bottoms.".getBytes();
}
if(i == 15217){
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[1];
class8.anIntArray160 = new int[1];
class8.anIntArray156[0] = 8741;
class8.anIntArray160[0] = 924;
class8.anInt174 = 5419;
class8.anInt181 = 730;
class8.anInt190 = 0;
class8.anInt198 = 2036;
class8.anInt204 = 0;
class8.anInt169 = 0;
class8.anInt194 = 0;
class8.anInt165 = 5430;
class8.anInt200 = 5435;
class8.anInt188 = -1;
class8.anInt164 = -1;
class8.anInt175 = 5428;
class8.anInt197 = 5429;
class8.aString170 = "Draconic Hood";
class8.aByteArray178 = "A Draconic Ahrims Hood.".getBytes();
}
 if(i == 15234) // change this if you need to "item number"
        {
            class8.aStringArray189 = new String[5];
            class8.aStringArray189[1] = "Wield";
            class8.anIntArray156 = new int[1];
            class8.anIntArray160 = new int[1];
class8.anIntArray156[0] = 28;
class8.anIntArray156[0] = 74;
class8.anIntArray160[0] = 38676;
class8.anIntArray160[0] = 760;
class8.anInt174 = 5139;
	class8.anInt181 = 490;
	class8.anInt190 = 344;
	class8.anInt198 = 192;
	class8.anInt204 = 138;
        class8.anInt169 = 1;
        class8.anInt194 = 20;
class8.anInt165 = 5114;//male wearing 15413
class8.anInt200 = 5114;//female wearing 15413
class8.anInt175 = 56;
class8.anInt197 = 116;
            class8.aString170 = "Slayer Sword";
            class8.aByteArray178 = "A Slayer Sword From The Slayer Masters.".getBytes();
        }
if(i == 15106){//ladies gaunts
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[3];
class8.anIntArray160 = new int[3];
class8.anIntArray156[0] = 10283;
class8.anIntArray160[0] = 62928;
class8.anIntArray156[1] = 10270;
class8.anIntArray160[1] = 62928;
class8.anIntArray156[2] = 3326;
class8.anIntArray160[2] = 62928;
class8.anInt174 = 8700;
class8.anInt181 = 710;
class8.anInt190 = 332;
class8.anInt198 = 2000;
class8.anInt204 = 0;
class8.anInt169 = 2;
class8.anInt194 = -14;
class8.anInt165 = 8726;
class8.anInt200 = 8750;
class8.anInt188 = -1;
class8.anInt164 = -1;
class8.anInt175 = -1;
class8.anInt197 = -1;
class8.aString170 = "Ladies Gauntlets";
class8.aByteArray178 = "Ladies Gauntlets.".getBytes();
}
if(i == 15118){
            class8.aStringArray189 = new String[5];
            class8.aStringArray189[1] = "Equip";
            class8.anIntArray156 = new int[1];
            class8.anIntArray160 = new int[1];
            class8.anInt174 = 153;
            class8.anInt181 = 1740;
            class8.anInt190 = 444;
            class8.anInt198 = 0;
            class8.anInt204 = 0;
            class8.anInt169 = 0;
            class8.anInt194 = -8;
            class8.anInt165 = 278;
            class8.anInt200 = 278;
            class8.anInt175 = 0;
            class8.anInt188 = -1;
            class8.anInt164 =  -1;
            class8.anInt175 =  -1;
            class8.anInt197 =  -1;
            class8.aString170 = "Demon tail";
            class8.aByteArray178 = "A Demon Tail.".getBytes();
}

if(i == 15119){
            class8.aStringArray189 = new String[5];
            class8.aStringArray189[1] = "Equip";
            class8.anIntArray156 = new int[1];
            class8.anIntArray160 = new int[1];
            class8.anInt174 = 153;
            class8.anInt181 = 760;
            class8.anInt190 = 552;
            class8.anInt198 = 28;
            class8.anInt204 = 0;
            class8.anInt169 = 0;
            class8.anInt194 = 2;
            class8.anInt165 = 242;
            class8.anInt200 = 242;
            class8.anInt175 = 0;
            class8.anInt188 = -1;
            class8.anInt164 =  -1;
            class8.anInt175 =  -1;
            class8.anInt197 =  -1;
            class8.aString170 = "Demon Horns #1";
            class8.aByteArray178 = "Horns of a demon.".getBytes();
}

if(i == 15120){
            class8.aStringArray189 = new String[5];
            class8.aStringArray189[1] = "Equip";
            class8.anIntArray156 = new int[1];
            class8.anIntArray160 = new int[1];
            class8.anInt174 = 153;
            class8.anInt181 = 760;
            class8.anInt190 = 552;
            class8.anInt198 = 28;
            class8.anInt204 = 0;
            class8.anInt169 = 0;
            class8.anInt194 = 2;
            class8.anInt165 = 239;
            class8.anInt200 = 239;
            class8.anInt175 = 0;
            class8.anInt188 = -1;
            class8.anInt164 =  -1;
            class8.anInt175 =  -1;
            class8.anInt197 =  -1;
            class8.aString170 = "Demon Horns #2";
            class8.aByteArray178 = "Horns of a demon.".getBytes();
}

if(i == 15121){
            class8.aStringArray189 = new String[5];
            class8.aStringArray189[1] = "Equip";
            class8.anIntArray156 = new int[1];
            class8.anIntArray160 = new int[1];
            class8.anInt174 = 153;
            class8.anInt181 = 760;
            class8.anInt190 = 552;
            class8.anInt198 = 28;
            class8.anInt204 = 0;
            class8.anInt169 = 0;
            class8.anInt194 = 2;
            class8.anInt165 = 229;
            class8.anInt200 = 229;
            class8.anInt175 = 0;
            class8.anInt188 = -1;
            class8.anInt164 =  -1;
            class8.anInt175 =  -1;
            class8.anInt197 =  -1;
            class8.aString170 = "Demon Head";
            class8.aByteArray178 = "The Head Of A Demon.".getBytes();
}

if(i == 15122){
            class8.aStringArray189 = new String[5];
            class8.aStringArray189[1] = "Equip";
            class8.anIntArray156 = new int[1];
            class8.anIntArray160 = new int[1];
            class8.anInt174 = 153;
            class8.anInt181 = 770;
            class8.anInt190 = 152;
            class8.anInt198 = 160;
            class8.anInt204 = 0;
            class8.anInt169 = 1;
            class8.anInt194 = -6;
            class8.anInt165 = 182;
            class8.anInt200 = 182;
            class8.anInt175 = -1;
            class8.anInt188 = -1;
            class8.anInt164 =  -1;
            class8.anInt175 =  -1;
            class8.anInt197 =  -1;
            class8.aString170 = "Demon Feet";
            class8.aByteArray178 = "The Feet Of A Demon.".getBytes();
}

if(i == 15123){
            class8.aStringArray189 = new String[5];
            class8.aStringArray189[1] = "Equip";
            class8.anIntArray156 = new int[1];
            class8.anIntArray160 = new int[1];
            class8.anInt174 = 153;
            class8.anInt181 = 770;
            class8.anInt190 = 152;
            class8.anInt198 = 160;
            class8.anInt204 = 0;
            class8.anInt169 = 1;
            class8.anInt194 = -6;
            class8.anInt165 = 180;
            class8.anInt200 = 180;
            class8.anInt175 = -1;
            class8.anInt188 = -1;
            class8.anInt164 =  -1;
            class8.anInt175 =  -1;
            class8.anInt197 =  -1;
            class8.aString170 = "Demon Hands";
            class8.aByteArray178 = "The Hands Of A Demon.".getBytes();
}
if(i == 15338) { //lava chain
		class8.aStringArray189 = new String[5];
		class8.aStringArray189[1] = "Wear";
		class8.anIntArray156 = new int[5];
		class8.anIntArray160 = new int [5];
		class8.anIntArray156[0] = 914;
		class8.anIntArray160[0] = 6070;
		class8.anIntArray156[1] = 918;
		class8.anIntArray160[1] = 6070;
		class8.anIntArray156[2] = 922;
		class8.anIntArray160[2] = 6071;
		class8.anIntArray156[3] = 391;
		class8.anIntArray160[3] = 6070;
		class8.anIntArray156[4] = 917;
		class8.anIntArray160[4] = 6069;
	class8.anInt181 = 1100;
	class8.anInt190 = 568;
	class8.anInt198 = 0;
	class8.anInt204 = 0;
	class8.anInt169 = 0;
	class8.anInt194 = 2;
		class8.anInt165 = 3820;
		class8.anInt200 = 3821;
		class8.anInt174 = 3823;
	class8.anInt188 = 156;
	class8.anInt164 = 337;
	class8.anInt175 = -1;
	class8.anInt197 = -1;
	class8.aString170 = "Lava Chainmail";
	class8.aByteArray178 = "It's a Lava Chain".getBytes();
}
if(i == 15150) {
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[3];
class8.anIntArray160 = new int [3];
class8.anIntArray156[0] = 0xD4E7;
class8.anIntArray156[1] = 0xDFC0;
class8.anIntArray156[2] = 0xD3A7;
class8.anIntArray160[0] = 8128;
class8.anIntArray160[1] = 9126;
class8.anIntArray160[2] = 8128;
class8.anInt174 = 7022; //Inv & Ground
class8.anInt181 = 2000; //Zoom
class8.anInt190 = 572;
class8.anInt198 = 0;
class8.anInt204 = 0;
class8.anInt169 = 0;
class8.anInt194 = 1;
class8.anInt165 = 7021; //Male
class8.anInt200 = 7021; //Female
class8.anInt175 = -1;
class8.anInt197 = -1;
class8.aString170 = "Summoning Cape";
class8.aByteArray178 = "A Sexy Cape.".getBytes();
}

if(i == 15105) { //ladies chain
		class8.aStringArray189 = new String[5];
		class8.aStringArray189[1] = "Wear";
		class8.anIntArray156 = new int[5];
		class8.anIntArray160 = new int [5];
		class8.anIntArray156[0] = 914;
		class8.anIntArray160[0] = 62928;
		class8.anIntArray156[1] = 918;
		class8.anIntArray160[1] = 62931;
		class8.anIntArray156[2] = 922;
		class8.anIntArray160[2] = 62928;
		class8.anIntArray156[3] = 391;
		class8.anIntArray160[3] = 62930;
		class8.anIntArray156[4] = 917;
		class8.anIntArray160[4] = 62928;
	class8.anInt181 = 1100;
	class8.anInt190 = 568;
	class8.anInt198 = 0;
	class8.anInt204 = 0;
	class8.anInt169 = 0;
	class8.anInt194 = 2;
		class8.anInt165 = 3820;
		class8.anInt200 = 3821;
		class8.anInt174 = 3823;
	class8.anInt188 = 156;
	class8.anInt164 = 337;
	class8.anInt175 = -1;
	class8.anInt197 = -1;
	class8.aString170 = "Ladies Chainmail";
	class8.aByteArray178 = "Its a Ladies Chain".getBytes();
}
if(i == 15340) {
				class8.aStringArray189 = new String[5];
				class8.aStringArray189[1] = "Wear";
				class8.anIntArray156 = new int[3];
				class8.anIntArray160 = new int [3];
				class8.anIntArray156[0] = 43127;
				class8.anIntArray160[0] = 6073;
				class8.anIntArray156[1] = 38119;
				class8.anIntArray160[1] = 6069;
				class8.anIntArray156[2] = 36975;
				class8.anIntArray160[2] = 6069;
			class8.anInt174 = 5198;//Drop model & inv model
			class8.anInt181 = 1900;
			class8.anInt190 = 500;
			class8.anInt198 = 500;
			class8.anInt204 = 0;
		        class8.anInt169 = 3;
		        class8.anInt194 = 6;
			class8.anInt200 = 5196;//female wearing
			class8.anInt165 = 5196;//male wearing
		        class8.anInt175 = -1;
		        class8.anInt197 = -1;
			class8.aString170 = "Lava shield";
			class8.aByteArray178 = "Lava shield".getBytes();
}

if(i == 15104) {
				class8.aStringArray189 = new String[5];
				class8.aStringArray189[1] = "Wear";
				class8.anIntArray156 = new int[3];
				class8.anIntArray160 = new int [3];
				class8.anIntArray156[0] = 43127;
				class8.anIntArray160[0] = 62928;
				class8.anIntArray156[1] = 38119;
				class8.anIntArray160[1] = 62928;
				class8.anIntArray156[2] = 36975;
				class8.anIntArray160[2] = 62928;
			class8.anInt174 = 5198;//Drop model & inv model
			class8.anInt181 = 1900;
			class8.anInt190 = 500;
			class8.anInt198 = 500;
			class8.anInt204 = 0;
		        class8.anInt169 = 3;
		        class8.anInt194 = 6;
			class8.anInt200 = 5196;//female wearing
			class8.anInt165 = 5196;//male wearing
		        class8.anInt175 = -1;
		        class8.anInt197 = -1;
			class8.aString170 = "Ladies shield";
			class8.aByteArray178 = "Ladies shield".getBytes();
}


if(i == 15341){
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[4];
class8.anIntArray160 = new int[4];
class8.anIntArray156[0] = 61;
class8.anIntArray160[0] = 6073;
class8.anInt174 = 5037;
class8.anInt181 = 770;
class8.anInt190 = 164;
class8.anInt198 = 156;
class8.anInt204 = 0;
class8.anInt169 = 3;
class8.anInt194 = -3;
class8.anInt165 = 4954;
class8.anInt200 = 5031;
class8.anInt188 = -1;
class8.anInt164 = -1;
class8.anInt175 = -1;
class8.anInt197 = -1;
class8.aString170 = "Lava Boots";
class8.aByteArray178 = "Lava boots".getBytes();
}


if(i == 15103){
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[4];
class8.anIntArray160 = new int[4];
class8.anIntArray156[0] = 61;
class8.anIntArray160[0] = 62929;
class8.anInt174 = 5037;
class8.anInt181 = 770;
class8.anInt190 = 164;
class8.anInt198 = 156;
class8.anInt204 = 0;
class8.anInt169 = 3;
class8.anInt194 = -3;
class8.anInt165 = 4954;
class8.anInt200 = 5031;
class8.anInt188 = -1;
class8.anInt164 = -1;
class8.anInt175 = -1;
class8.anInt197 = -1;
class8.aString170 = "Ladies Boots";
class8.aByteArray178 = "Ladies boots".getBytes();
}

if(i == 15337){
	class8.aStringArray189 = new String[5];
	class8.aStringArray189[1] = "Wear";
	class8.anIntArray156 = new int[2];
        class8.anIntArray160 = new int [2];
	class8.anIntArray156[0] = 933;
	class8.anIntArray160[0] = 6073;
	class8.anIntArray156[1] = 935;
	class8.anIntArray160[1] = 5959;
	class8.anInt174 = 6033;//Item Look
	class8.anInt181 = 980;
	class8.anInt190 = 350;
	class8.anInt198 = 1020;
	class8.anInt204 = 324;
	class8.anInt169 = -2;
	class8.anInt194 = 0;
	class8.anInt165 = 6031;
	class8.anInt200 = 6031;
	class8.anInt175 = -1;
	class8.anInt197 = -1;
	class8.aString170 = "Lava Blade";
	class8.aByteArray178 = "".getBytes();
        	}
if(i == 15102){
	class8.aStringArray189 = new String[5];
	class8.aStringArray189[1] = "Wear";
	class8.anIntArray156 = new int[2];
        class8.anIntArray160 = new int [2];
	class8.anIntArray156[0] = 933;
	class8.anIntArray160[0] = 62928;
	class8.anIntArray156[1] = 935;
	class8.anIntArray160[1] = 62930;
	class8.anInt174 = 6033;//Item Look
	class8.anInt181 = 980;
	class8.anInt190 = 350;
	class8.anInt198 = 1020;
	class8.anInt204 = 324;
	class8.anInt169 = -2;
	class8.anInt194 = 0;
	class8.anInt165 = 6031;
	class8.anInt200 = 6031;
	class8.anInt175 = -1;
	class8.anInt197 = -1;
	class8.aString170 = "Ladies Blade";
	class8.aByteArray178 = "A Ladies Blade.".getBytes();
        	}

        if(i == 9109) {
				class8.aStringArray189 = new String[5];
				class8.aStringArray189[1] = "Wear";
				class8.anIntArray156 = new int[3];
				class8.anIntArray160 = new int [3];
				class8.anIntArray156[0] = 43127;
				class8.anIntArray160[0] = 43968;
				class8.anIntArray156[1] = 38119;
				class8.anIntArray160[1] = 43968;
				class8.anIntArray156[2] = 36975;
				class8.anIntArray160[2] = 43968;
			class8.anInt174 = 5198;//Drop model & inv model
			class8.anInt181 = 1900;
			class8.anInt190 = 500;
			class8.anInt198 = 500;
			class8.anInt204 = 0;
		        class8.anInt169 = 3;
		        class8.anInt194 = 6;
			class8.anInt200 = 5196;//female wearing
			class8.anInt165 = 5196;//male wearing
		        class8.anInt175 = -1;
		        class8.anInt197 = -1;
			class8.aString170 = "Blue Crystal shield";
			class8.aByteArray178 = "A Blue Crystal shield".getBytes();
}

        if(i == 15203) {
				class8.aStringArray189 = new String[5];
				class8.aStringArray189[1] = "Wear";
				class8.anIntArray156 = new int[3];
				class8.anIntArray160 = new int [3];
				class8.anIntArray156[0] = 43127;
				class8.anIntArray160[0] = 43968;
				class8.anIntArray156[1] = 38119;
				class8.anIntArray160[1] = 43968;
				class8.anIntArray156[2] = 36975;
				class8.anIntArray160[2] = 43968;
			class8.anInt174 = 3902;//Drop model & inv model
			class8.anInt181 = 1570;
			class8.anInt190 = 192;
			class8.anInt198 = 76;
			class8.anInt204 = 0;
		        class8.anInt169 = 1;
		        class8.anInt194 = 6;
			class8.anInt200 = 5197;//female wearing
			class8.anInt165 = 5197;//male wearing
class8.anInt188 = -1;
		        class8.anInt164 = -1;
		        class8.anInt175 = -1;
		        class8.anInt197 = -1;
			class8.aString170 = "Blue Crystal shield";
			class8.aByteArray178 = "A Blue Crystal shield".getBytes();
        	}
if(i == 9115) {
				class8.aStringArray189 = new String[5];
				class8.aStringArray189[1] = "Wear";
				class8.anIntArray156 = new int[3];
				class8.anIntArray160 = new int [3];
				class8.anIntArray156[0] = 43127;
				class8.anIntArray160[0] = 128;
				class8.anIntArray156[1] = 38119;
				class8.anIntArray160[1] = 128;
				class8.anIntArray156[2] = 36975;
				class8.anIntArray160[2] = 128;
			class8.anInt174 = 5198;//Drop model & inv model
			class8.anInt181 = 1900;
			class8.anInt190 = 500;
			class8.anInt198 = 500;
			class8.anInt204 = 0;
		        class8.anInt169 = 3;
		        class8.anInt194 = 6;
			class8.anInt200 = 5196;//female wearing
			class8.anInt165 = 5196;//male wearing
		        class8.anInt175 = -1;
		        class8.anInt197 = -1;
			class8.aString170 = "Black Crystal shield";
			class8.aByteArray178 = "A Black Crystal shield".getBytes();
}
		if(i == 9117)
		        {
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear"; //this is the new option to equipt, can change to w/e u want
class8.anIntArray156 = new int[1];
class8.anIntArray160 = new int[1];
class8.anIntArray156[0] = 926;  //original color
class8.anIntArray160[0] = 0; //changed color
class8.anInt174 =   2438;
class8.anInt181 =   730;
class8.anInt190 =   516;
class8.anInt198 =   0;
class8.anInt204 =   0;
class8.anInt169 =   0;
class8.anInt194 =   -10;
class8.anInt165 =   3188;
class8.anInt200 =   3192;
class8.anInt188 =   -1;
class8.anInt164 =   -1;
class8.anInt175 =   1755;
class8.anInt197 =   3187;
	class8.aString170 = "Black h'ween Mask";
	class8.aByteArray178 = "Aaaarrrghhh... I'm a monster.".getBytes();
}
if(i == 9118)
		        {
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear"; //this is the new option to equipt, can change to w/e u want
class8.anIntArray156 = new int[1];
class8.anIntArray160 = new int[1];
class8.anIntArray156[0] = 926;  //original color
class8.anIntArray160[0] = 11200; //changed color
class8.anInt174 =   2438;
class8.anInt181 =   730;
class8.anInt190 =   516;
class8.anInt198 =   0;
class8.anInt204 =   0;
class8.anInt169 =   0;
class8.anInt194 =   -10;
class8.anInt165 =   3188;
class8.anInt200 =   3192;
class8.anInt188 =   -1;
class8.anInt164 =   -1;
class8.anInt175 =   1755;
class8.anInt197 =   3187;
	class8.aString170 = "Yellow h'ween Mask";
	class8.aByteArray178 = "Aaaarrrghhh... I'm a monster.".getBytes();
}
if(class8.anInt163 != -1)
class8.method199((byte)61);
if(!aBoolean182 && class8.aBoolean161)
{
class8.aString170 = "Members Object";
class8.aByteArray178 = "Login to a members' server to use this object.".getBytes();
class8.aStringArray168 = null;
class8.aStringArray189 = null;
class8.anInt202 = 0;
}

return class8;
}

public static void Models(int Ground, int Male, int Female)
{
Class8 class8 = aClass8Array172[anInt180];
class8.anInt174 = Ground;
class8.anInt165 = Male;
class8.anInt200 = Female;
}

public static void NewColor(int Old, int New, int Num)
{
Class8 class8 = aClass8Array172[anInt180];
class8.anIntArray156[Num] = Old;
class8.anIntArray160[Num] = New;
}

public static void NEO(String Name, String Examine, String Option)// NEO = Name Examine Option
{
Class8 class8 = aClass8Array172[anInt180];
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = Option;
class8.aString170 = Name;
class8.aByteArray178 = Examine.getBytes();
}

public static void Zoom(int zoom, int X, int Y, int RotateUp, int RotateRight, boolean Stackable)
{
Class8 class8 = aClass8Array172[anInt180];
class8.anInt181 = zoom;
class8.anInt190 = RotateUp;
class8.anInt198 = RotateRight;
class8.anInt169 = Y;
class8.anInt194 = X;
class8.aBoolean176 = Stackable;
}

public static void Jukkycolors(int old, int neww, int num) {
Class8 class8 = aClass8Array172[anInt180];
class8.anIntArray156[num] = old;
class8.anIntArray160[num] = neww;
}
public static void Jukkyzoom(int zoom, int rotation, int rotateright, int offsetY, int offsetX, int Brightness, int msc, int msc1, boolean stackable) {
Class8 class8 = aClass8Array172[anInt180];
class8.anInt181 = zoom;
class8.anInt190 = rotation;
class8.anInt198 = rotateright;
class8.anInt204 = offsetY;
class8.anInt169 = offsetX;
class8.anInt194 = Brightness;
class8.aBoolean176 = stackable;//Stackable
class8.anInt175 = msc;//Unknown
class8.anInt197 = msc1;//Unknown
}
public static void Jukkyname(String name, String examine) {
Class8 class8 = aClass8Array172[anInt180];
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.aString170 = name;
class8.aByteArray178 = examine.getBytes();
}
public static void JukkyModels(int male, int malearms, int female, int femalearms, int dropmdl) {
Class8 class8 = aClass8Array172[anInt180];
class8.anInt165 = male;
class8.anInt188 = malearms;
class8.anInt200 = female;
class8.anInt164 = femalearms;
class8.anInt174 = dropmdl;
}

public void method199(byte byte0)
{
Class8 class8 = method198(anInt163);
anInt174 = class8.anInt174;
anInt181 = class8.anInt181;
anInt190 = class8.anInt190;
anInt198 = class8.anInt198;
anInt204 = class8.anInt204;
anInt169 = class8.anInt169;
anInt194 = class8.anInt194;
if(byte0 != 61)
aBoolean186 = !aBoolean186;
anIntArray156 = class8.anIntArray156;
anIntArray160 = class8.anIntArray160;
Class8 class8_1 = method198(anInt179);
aString170 = class8_1.aString170;
aBoolean161 = class8_1.aBoolean161;
anInt155 = class8_1.anInt155;
String s = "a";
char c = class8_1.aString170.charAt(0);
if(c == 'A' || c == 'E' || c == 'I' || c == 'O' || c == 'U')
s = "an";
aByteArray178 = ("Swap this note at any bank for " + s + " " + class8_1.aString170 + ".").getBytes();
aBoolean176 = true;
}

public static final Class30_Sub2_Sub1_Sub1 method200(int i, int j, int k, int l)
{
if(k == 0)
{
Class30_Sub2_Sub1_Sub1 class30_sub2_sub1_sub1 = (Class30_Sub2_Sub1_Sub1)aClass12_158.method222(i);
if(class30_sub2_sub1_sub1 != null && class30_sub2_sub1_sub1.anInt1445 != j && class30_sub2_sub1_sub1.anInt1445 != -1)
{
class30_sub2_sub1_sub1.method329();
class30_sub2_sub1_sub1 = null;
}
if(class30_sub2_sub1_sub1 != null)
return class30_sub2_sub1_sub1;
}
Class8 class8 = method198(i);
if(class8.anIntArray193 == null)
j = -1;
if(j > 1)
{
int i1 = -1;
for(int j1 = 0; j1 < 10; j1++)
if(j >= class8.anIntArray201[j1] && class8.anIntArray201[j1] != 0)
i1 = class8.anIntArray193[j1];

if(i1 != -1)
class8 = method198(i1);
}
Class30_Sub2_Sub4_Sub6 class30_sub2_sub4_sub6 = class8.method201(1);
if(class30_sub2_sub4_sub6 == null)
return null;
Class30_Sub2_Sub1_Sub1 class30_sub2_sub1_sub1_2 = null;
if(class8.anInt163 != -1)
{
class30_sub2_sub1_sub1_2 = method200(class8.anInt179, 10, -1, 9);
if(class30_sub2_sub1_sub1_2 == null)
return null;
}
Class30_Sub2_Sub1_Sub1 class30_sub2_sub1_sub1_1 = new Class30_Sub2_Sub1_Sub1(32, 32);
int k1 = Class30_Sub2_Sub1_Sub3.anInt1466;
int l1 = Class30_Sub2_Sub1_Sub3.anInt1467;
int ai[] = Class30_Sub2_Sub1_Sub3.anIntArray1472;
int ai1[] = Class30_Sub2_Sub1.anIntArray1378;
int i2 = Class30_Sub2_Sub1.anInt1379;
int j2 = Class30_Sub2_Sub1.anInt1380;
int k2 = Class30_Sub2_Sub1.anInt1383;
int l2 = Class30_Sub2_Sub1.anInt1384;
int i3 = Class30_Sub2_Sub1.anInt1381;
int j3 = Class30_Sub2_Sub1.anInt1382;
Class30_Sub2_Sub1_Sub3.aBoolean1464 = false;
Class30_Sub2_Sub1.method331(32, 32, -293, class30_sub2_sub1_sub1_1.anIntArray1439);
Class30_Sub2_Sub1.method336(32, 0, 0, 0, 32, 0);
Class30_Sub2_Sub1_Sub3.method364((byte)6);
int k3 = class8.anInt181;
if(k == -1)
k3 = (int)((double)k3 * 1.5D);
if(k > 0)
k3 = (int)((double)k3 * 1.04D);
int l3 = Class30_Sub2_Sub1_Sub3.anIntArray1470[class8.anInt190] * k3 >> 16;
int i4 = Class30_Sub2_Sub1_Sub3.anIntArray1471[class8.anInt190] * k3 >> 16;
class30_sub2_sub4_sub6.method482(0, class8.anInt198, class8.anInt204, class8.anInt190, class8.anInt169, l3 + ((Class30_Sub2_Sub4) (class30_sub2_sub4_sub6)).anInt1426 / 2 + class8.anInt194, i4 + class8.anInt194);
for(int i5 = 31; i5 >= 0; i5--)
{
for(int j4 = 31; j4 >= 0; j4--)
if(class30_sub2_sub1_sub1_1.anIntArray1439[i5 + j4 * 32] == 0)
if(i5 > 0 && class30_sub2_sub1_sub1_1.anIntArray1439[(i5 - 1) + j4 * 32] > 1)
class30_sub2_sub1_sub1_1.anIntArray1439[i5 + j4 * 32] = 1;
else
if(j4 > 0 && class30_sub2_sub1_sub1_1.anIntArray1439[i5 + (j4 - 1) * 32] > 1)
class30_sub2_sub1_sub1_1.anIntArray1439[i5 + j4 * 32] = 1;
else
if(i5 < 31 && class30_sub2_sub1_sub1_1.anIntArray1439[i5 + 1 + j4 * 32] > 1)
class30_sub2_sub1_sub1_1.anIntArray1439[i5 + j4 * 32] = 1;
else
if(j4 < 31 && class30_sub2_sub1_sub1_1.anIntArray1439[i5 + (j4 + 1) * 32] > 1)
class30_sub2_sub1_sub1_1.anIntArray1439[i5 + j4 * 32] = 1;

}

if(k > 0)
{
for(int j5 = 31; j5 >= 0; j5--)
{
for(int k4 = 31; k4 >= 0; k4--)
if(class30_sub2_sub1_sub1_1.anIntArray1439[j5 + k4 * 32] == 0)
if(j5 > 0 && class30_sub2_sub1_sub1_1.anIntArray1439[(j5 - 1) + k4 * 32] == 1)
class30_sub2_sub1_sub1_1.anIntArray1439[j5 + k4 * 32] = k;
else
if(k4 > 0 && class30_sub2_sub1_sub1_1.anIntArray1439[j5 + (k4 - 1) * 32] == 1)
class30_sub2_sub1_sub1_1.anIntArray1439[j5 + k4 * 32] = k;
else
if(j5 < 31 && class30_sub2_sub1_sub1_1.anIntArray1439[j5 + 1 + k4 * 32] == 1)
class30_sub2_sub1_sub1_1.anIntArray1439[j5 + k4 * 32] = k;
else
if(k4 < 31 && class30_sub2_sub1_sub1_1.anIntArray1439[j5 + (k4 + 1) * 32] == 1)
class30_sub2_sub1_sub1_1.anIntArray1439[j5 + k4 * 32] = k;

}

} else
if(k == 0)
{
for(int k5 = 31; k5 >= 0; k5--)
{
for(int l4 = 31; l4 >= 0; l4--)
if(class30_sub2_sub1_sub1_1.anIntArray1439[k5 + l4 * 32] == 0 && k5 > 0 && l4 > 0 && class30_sub2_sub1_sub1_1.anIntArray1439[(k5 - 1) + (l4 - 1) * 32] > 0)
class30_sub2_sub1_sub1_1.anIntArray1439[k5 + l4 * 32] = 0x302020;

}

}
if(class8.anInt163 != -1)
{
int l5 = class30_sub2_sub1_sub1_2.anInt1444;
int j6 = class30_sub2_sub1_sub1_2.anInt1445;
class30_sub2_sub1_sub1_2.anInt1444 = 32;
class30_sub2_sub1_sub1_2.anInt1445 = 32;
class30_sub2_sub1_sub1_2.method348(0, 16083, 0);
class30_sub2_sub1_sub1_2.anInt1444 = l5;
class30_sub2_sub1_sub1_2.anInt1445 = j6;
}
if(k == 0)
aClass12_158.method223(class30_sub2_sub1_sub1_1, i, (byte)2);
Class30_Sub2_Sub1.method331(j2, i2, -293, ai1);
Class30_Sub2_Sub1.method333(j3, k2, false, l2, i3);
Class30_Sub2_Sub1_Sub3.anInt1466 = k1;
Class30_Sub2_Sub1_Sub3.anInt1467 = l1;
Class30_Sub2_Sub1_Sub3.anIntArray1472 = ai;
Class30_Sub2_Sub1_Sub3.aBoolean1464 = true;
if(l < 9 || l > 9)
{
for(int i6 = 1; i6 > 0; i6++);
}
if(class8.aBoolean176)
class30_sub2_sub1_sub1_1.anInt1444 = 33;
else
class30_sub2_sub1_sub1_1.anInt1444 = 32;
class30_sub2_sub1_sub1_1.anInt1445 = j;
return class30_sub2_sub1_sub1_1;
}

public final Class30_Sub2_Sub4_Sub6 method201(int i)
{
if(anIntArray193 != null && i > 1)
{
int j = -1;
for(int k = 0; k < 10; k++)
if(i >= anIntArray201[k] && anIntArray201[k] != 0)
j = anIntArray193[k];

if(j != -1)
return method198(j).method201(1);
}
Class30_Sub2_Sub4_Sub6 class30_sub2_sub4_sub6 = (Class30_Sub2_Sub4_Sub6)aClass12_159.method222(anInt157);
if(class30_sub2_sub4_sub6 != null)
return class30_sub2_sub4_sub6;
class30_sub2_sub4_sub6 = Class30_Sub2_Sub4_Sub6.method462(anInt171, anInt174);
if(class30_sub2_sub4_sub6 == null)
return null;
if(anInt167 != 128 || anInt192 != 128 || anInt191 != 128)
class30_sub2_sub4_sub6.method478(anInt167, anInt191, anInt177, anInt192);
if(anIntArray156 != null)
{
for(int l = 0; l < anIntArray156.length; l++)
class30_sub2_sub4_sub6.method476(anIntArray156[l], anIntArray160[l]);

}
class30_sub2_sub4_sub6.method479(64 + anInt196, 768 + anInt184, -50, -10, -50, true);
class30_sub2_sub4_sub6.aBoolean1659 = true;
aClass12_159.method223(class30_sub2_sub4_sub6, anInt157, (byte)2);
return class30_sub2_sub4_sub6;
}

public final Class30_Sub2_Sub4_Sub6 method202(int i, boolean flag)
{
if(anIntArray193 != null && i > 1)
{
int j = -1;
for(int k = 0; k < 10; k++)
if(i >= anIntArray201[k] && anIntArray201[k] != 0)
j = anIntArray193[k];

if(j != -1)
return method198(j).method202(1, true);
}
Class30_Sub2_Sub4_Sub6 class30_sub2_sub4_sub6 = Class30_Sub2_Sub4_Sub6.method462(anInt171, anInt174);
if(!flag)
throw new NullPointerException();
if(class30_sub2_sub4_sub6 == null)
return null;
if(anIntArray156 != null)
{
for(int l = 0; l < anIntArray156.length; l++)
class30_sub2_sub4_sub6.method476(anIntArray156[l], anIntArray160[l]);

}
return class30_sub2_sub4_sub6;
}

public final void method203(boolean flag, Class30_Sub2_Sub2 class30_sub2_sub2)
{
if(!flag)
throw new NullPointerException();
do
{
int i = class30_sub2_sub2.method408();
if(i == 0)
return;
if(i == 1)
anInt174 = class30_sub2_sub2.method410();
else
if(i == 2)
aString170 = class30_sub2_sub2.method415();
else
if(i == 3)
aByteArray178 = class30_sub2_sub2.method416((byte)30);
else
if(i == 4)
anInt181 = class30_sub2_sub2.method410();
else
if(i == 5)
anInt190 = class30_sub2_sub2.method410();
else
if(i == 6)
anInt198 = class30_sub2_sub2.method410();
else
if(i == 7)
{
anInt169 = class30_sub2_sub2.method410();
if(anInt169 > 32767)
anInt169 -= 0x10000;
} else
if(i == 8)
{
anInt194 = class30_sub2_sub2.method410();
if(anInt194 > 32767)
anInt194 -= 0x10000;
} else
if(i == 10)
anInt199 = class30_sub2_sub2.method410();
else
if(i == 11)
aBoolean176 = true;
else
if(i == 12)
anInt155 = class30_sub2_sub2.method413();
else
if(i == 16)
aBoolean161 = true;
else
if(i == 23)
{
anInt165 = class30_sub2_sub2.method410();
aByte205 = class30_sub2_sub2.method409();
} else
if(i == 24)
anInt188 = class30_sub2_sub2.method410();
else
if(i == 25)
{
anInt200 = class30_sub2_sub2.method410();
aByte154 = class30_sub2_sub2.method409();
} else
if(i == 26)
anInt164 = class30_sub2_sub2.method410();
else
if(i >= 30 && i < 35)
{
if(aStringArray168 == null)
aStringArray168 = new String[5];
aStringArray168[i - 30] = class30_sub2_sub2.method415();
if(aStringArray168[i - 30].equalsIgnoreCase("hidden"))
aStringArray168[i - 30] = null;
} else
if(i >= 35 && i < 40)
{
if(aStringArray189 == null)
aStringArray189 = new String[5];
aStringArray189[i - 35] = class30_sub2_sub2.method415();
} else
if(i == 40)
{
int j = class30_sub2_sub2.method408();
anIntArray156 = new int[j];
anIntArray160 = new int[j];
for(int k = 0; k < j; k++)
{
anIntArray156[k] = class30_sub2_sub2.method410();
anIntArray160[k] = class30_sub2_sub2.method410();
}

} else
if(i == 78)
anInt185 = class30_sub2_sub2.method410();
else
if(i == 79)
anInt162 = class30_sub2_sub2.method410();
else
if(i == 90)
anInt175 = class30_sub2_sub2.method410();
else
if(i == 91)
anInt197 = class30_sub2_sub2.method410();
else
if(i == 92)
anInt166 = class30_sub2_sub2.method410();
else
if(i == 93)
anInt173 = class30_sub2_sub2.method410();
else
if(i == 95)
anInt204 = class30_sub2_sub2.method410();
else
if(i == 97)
anInt179 = class30_sub2_sub2.method410();
else
if(i == 98)
anInt163 = class30_sub2_sub2.method410();
else
if(i >= 100 && i < 110)
{
if(anIntArray193 == null)
{
anIntArray193 = new int[10];
anIntArray201 = new int[10];
}
anIntArray193[i - 100] = class30_sub2_sub2.method410();
anIntArray201[i - 100] = class30_sub2_sub2.method410();
} else
if(i == 110)
anInt167 = class30_sub2_sub2.method410();
else
if(i == 111)
anInt192 = class30_sub2_sub2.method410();
else
if(i == 112)
anInt191 = class30_sub2_sub2.method410();
else
if(i == 113)
anInt196 = class30_sub2_sub2.method409();
else
if(i == 114)
anInt184 = class30_sub2_sub2.method409() * 5;
else
if(i == 115)
anInt202 = class30_sub2_sub2.method408();
} while(true);
}

Class8()
{
anInt157 = -1;
anInt171 = 9;
anInt177 = 9;
aBoolean186 = false;
aBoolean206 = false;
}

private byte aByte154;
public int anInt155;
private int anIntArray156[];
public int anInt157;
static Class12 aClass12_158 = new Class12(false, 100);
public static Class12 aClass12_159 = new Class12(false, 50);
private int anIntArray160[];
public boolean aBoolean161;
public int anInt162;
public int anInt163;
private int anInt164;
private int anInt165;
public int anInt166;
private int anInt167;
public String aStringArray168[];
private int anInt169;
public String aString170;
private int anInt171;
private static Class8 aClass8Array172[];
public int anInt173;
private int anInt174;
private int anInt175;
public boolean aBoolean176;
private int anInt177;
public byte aByteArray178[];
public int anInt179;
private static int anInt180;
public int anInt181;
public static boolean aBoolean182 = true;
private static Class30_Sub2_Sub2 aClass30_Sub2_Sub2_183;
private int anInt184;
public int anInt185;
private boolean aBoolean186;
private static boolean aBoolean187;
private int anInt188;
public String aStringArray189[];
public int anInt190;
private int anInt191;
private int anInt192;
public int anIntArray193[];
private int anInt194;
private static int anIntArray195[];
private int anInt196;
public int anInt197;
public int anInt198;
public int anInt199;
private int anInt200;
public int anIntArray201[];
public int anInt202;
public static int anInt203;
public static int frugooItems = 5;
private int anInt204;
private byte aByte205;
private boolean aBoolean206;

}
