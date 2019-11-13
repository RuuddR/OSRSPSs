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
 if(i == 15066){
    Jukkyname("Admin Platebody", "An administrators platebody.");
        class8.anIntArray156 = new int[8];
        class8.anIntArray160 = new int [8];
        Jukkycolors(61, 8128, 0);
        Jukkycolors(24, 38676, 1);
        Jukkycolors(41, 8128, 2);
        Jukkycolors(10394, 8128, 3);    
        Jukkycolors(10518, 38676, 4);
        Jukkycolors(10388, 38676, 5);
        Jukkycolors(10514, 38676, 6);
        Jukkycolors(10508, 38676, 7);
    Jukkyzoom(1380, 452, 0, 0, 0, -1, -1, -1, false);
    JukkyModels(6646, 3379, 6685, 3383, 2378);
        }
if(i == 15067) { //admin legs
		class8.aStringArray189 = new String[5];
		class8.aStringArray189[1] = "Wear";
		class8.anIntArray156 = new int[2];
		class8.anIntArray160 = new int [2];
		class8.anIntArray156[0] = 926;
		class8.anIntArray160[0] = 8128;
		class8.anIntArray156[1] = 912;
		class8.anIntArray160[1] = 38676;
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
	class8.aString170 = "Admin Platelegs";
	class8.aByteArray178 = "A set of administrators platelegs.".getBytes();
}
        if(i == 1573)
        {
            class8.aStringArray189 = new String[5];
            class8.aStringArray189[1] = "Wear";
            class8.anIntArray156 = new int[2];
            class8.anIntArray160 = new int[2];
            class8.anIntArray156[0] = 10283;
            class8.anIntArray160[0] = 12;
            class8.anIntArray156[1] = 10270;
            class8.anIntArray160[1] = 908;
            class8.anInt174 = 8700;
            class8.anInt190 = 452;
            class8.anInt198 = 0;
            class8.anInt204 = 0;
            class8.anInt169 = 0;
            class8.anInt194 = 0;
            class8.anInt165 = 8726;
            class8.anInt200 = 8750;
            class8.anInt175 = -1;
            class8.anInt197 = -1;
            class8.aString170 = "2008 H'ween gloves";
            class8.aByteArray178 = "2008 Halloween event gloves.".getBytes();
        }
        if(i == 14523)
        {
            class8.aStringArray189 = new String[5];
            class8.aStringArray189[1] = "Wear";
            class8.anIntArray156 = new int[4];
            class8.anIntArray160 = new int[4];
            class8.anIntArray156[0] = 61;
            class8.anIntArray156[1] = 41;
            class8.anIntArray156[2] = 24;
            class8.anIntArray156[3] = 11187;
            class8.anIntArray160[0] = 926;
            class8.anIntArray160[1] = 926;
            class8.anIntArray160[2] = 926;
            class8.anIntArray160[3] = 926;
            class8.anInt174 = 3020;
            class8.anInt181 = 1180;
            class8.anInt190 = 452;
            class8.anInt198 = 0;
            class8.anInt204 = 0;
            class8.anInt169 = -1;
            class8.anInt194 = -1;
            class8.anInt165 = 3379;
            class8.anInt200 = 3383;
            class8.anInt188 = 164;
            class8.anInt164 = 344;
            class8.anInt175 = -1;
            class8.anInt197 = -1;
            class8.aString170 = "Dragon Platebody";
            class8.aByteArray178 = "A dragon platebody by Traxxas.".getBytes();
        }
     if(i == 1097)
        {
            class8.aStringArray189 = new String[5];
            class8.aStringArray189[1] = "Wear";
            class8.anIntArray156 = new int[4];
            class8.anIntArray160 = new int[4];
            class8.anIntArray156[0] = 61;
            class8.anIntArray156[1] = 41;
            class8.anIntArray156[2] = 24;
            class8.anIntArray156[3] = 11187;
            class8.anIntArray160[0] = 926;
            class8.anIntArray160[1] = 926;
            class8.anIntArray160[2] = 7114;
            class8.anIntArray160[3] = 926;
            class8.anInt174 = 3020;
            class8.anInt181 = 1180;
            class8.anInt190 = 452;
            class8.anInt198 = 0;
            class8.anInt204 = 0;
            class8.anInt169 = -1;
            class8.anInt194 = -1;
            class8.anInt165 = 3379;
            class8.anInt200 = 3383;
            class8.anInt188 = 164;
            class8.anInt164 = 344;
            class8.anInt175 = -1;
            class8.anInt197 = -1;
            class8.aString170 = "Dragon platebody (T)";
            class8.aByteArray178 = "A dragon platebody (T) by Traxxas.".getBytes();
        }
if(i == 10124)
        {
            class8.aStringArray189 = new String[5];
            class8.aStringArray189[1] = "Wear";
            class8.anIntArray156 = new int[3];
            class8.anIntArray160 = new int[3];
            class8.anIntArray156[0] = 22416;
            class8.anIntArray160[0] = 7097;
            class8.anIntArray156[1] = 22424;
            class8.anIntArray160[1] = 7114;
            class8.anIntArray156[2] = 24;
            class8.anIntArray160[2] = 43117;
            class8.anInt174 = 11390;
            class8.anInt181 = 1030;
            class8.anInt190 = 548;
            class8.anInt198 = 0;
            class8.anInt204 = 0;
            class8.anInt169 = 0;
            class8.anInt194 = -4;
            class8.anInt165 = 11345;
            class8.anInt200 = 11361;
            class8.anInt175 = -1;
            class8.anInt197 = -1;
            class8.aString170 = "Gilded d-hide body";
            class8.aByteArray178 = "Golden body of 100% dragon hide. Did this come from a golden dragon?".getBytes();
        }
        if(i == 10125)
        {
            class8.aStringArray189 = new String[5];
            class8.aStringArray189[1] = "Wear";
            class8.anIntArray156 = new int[3];
            class8.anIntArray160 = new int[3];
            class8.anIntArray156[0] = 22416;
            class8.anIntArray160[0] = 7097;
            class8.anIntArray156[1] = 22424;
            class8.anIntArray160[1] = 7114;
            class8.anIntArray156[2] = 24;
            class8.anIntArray160[2] = 43117;
            class8.anInt174 = 11371;
            class8.anInt181 = 1720;
            class8.anInt190 = 488;
            class8.anInt198 = 0;
            class8.anInt204 = 224;
            class8.anInt169 = 7;
            class8.anInt194 = 5;
            class8.anInt165 = 11411;
            class8.anInt200 = 11355;
            class8.anInt175 = -1;
            class8.anInt197 = -1;
            class8.aString170 = "Gilded d-hide chaps";
            class8.aByteArray178 = "Golden chaps of 100% dragon hide. Did this come from a golden dragon?".getBytes();
        }
 if(i == 15118)
        {
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
            class8.anInt164 = -1;
            class8.anInt175 = -1;
            class8.anInt197 = -1;
            class8.aString170 = "Demon tail";
            class8.aByteArray178 = "A demon tail.".getBytes();
        }
        if(i == 15119)
        {
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
            class8.anInt164 = -1;
            class8.anInt175 = -1;
            class8.anInt197 = -1;
            class8.aString170 = "Demon Horns #1";
            class8.aByteArray178 = "Horns of a demon.".getBytes();
        }
        if(i == 15120)
        {
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
            class8.anInt164 = -1;
            class8.anInt175 = -1;
            class8.anInt197 = -1;
            class8.aString170 = "Demon Horns #2";
            class8.aByteArray178 = "Horns of a demon.".getBytes();
        }
        if(i == 15121)
        {
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
            class8.anInt164 = -1;
            class8.anInt175 = -1;
            class8.anInt197 = -1;
            class8.aString170 = "Demon Head";
            class8.aByteArray178 = "The head of a demon.".getBytes();
        }
        if(i == 15122)
        {
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
            class8.anInt164 = -1;
            class8.anInt175 = -1;
            class8.anInt197 = -1;
            class8.aString170 = "Demon Feet";
            class8.aByteArray178 = "The feet of a demon.".getBytes();
        }
 if(i == 9096)
        {
            class8.aStringArray189 = new String[5];
            class8.aStringArray189[1] = "Wield";
            class8.anInt174 = 6944;
            class8.anInt181 = 1050;
            class8.anInt190 = 572;
            class8.anInt198 = 0;
            class8.anInt169 = 0;
            class8.anInt194 = 1;
            class8.anInt165 = 6940;
            class8.anInt200 = 6943;
            class8.anInt164 = 6942;
            class8.anInt188 = 6941;
            class8.aBoolean176 = false;
            class8.aString170 = "Zamorak Robe top";
            class8.aByteArray178 = "A Robe Top Worn By the Gods.".getBytes();
        }
        if(i == 9097)
        {
            class8.aStringArray189 = new String[5];
            class8.aStringArray189[1] = "Wield";
            class8.anInt174 = 6947;
            class8.anInt181 = 1800;
            class8.anInt190 = 408;
            class8.anInt198 = 0;
            class8.anInt169 = 5;
            class8.anInt194 = 0;
            class8.anInt165 = 6945;
            class8.anInt200 = 6946;
            class8.aBoolean176 = false;
            class8.aString170 = "Zamorak Robe Bottom";
            class8.aByteArray178 = "A Robe Bottom Worn By the Gods.".getBytes();
        }
        if(i == 9098)
        {
            class8.aStringArray189 = new String[5];
            class8.aStringArray189[1] = "Wield";
            class8.anInt174 = 6948;
            class8.anInt181 = 600;
            class8.anInt190 = 208;
            class8.anInt198 = 220;
            class8.anInt169 = 0;
            class8.anInt194 = -18;
            class8.anInt165 = 6949;
            class8.anInt200 = 6950;
            class8.aBoolean176 = false;
            class8.aString170 = "Zamorak Mitre";
            class8.aByteArray178 = "A Hat Worn By the Gods.".getBytes();
}
      if(i == 13602)
        {
            class8.aStringArray189 = new String[5];
            class8.aStringArray189[1] = "Wield";
            class8.anIntArray156 = new int[1];
            class8.anIntArray160 = new int[1];
            class8.anIntArray156[0] = 28;
            class8.anIntArray156[0] = 74;
            class8.anIntArray160[0] = 38676;
            class8.anIntArray160[0] = 924;
            class8.anInt174 = 4671;
            class8.anInt181 = 490;
            class8.anInt190 = 344;
            class8.anInt198 = 192;
            class8.anInt204 = 138;
            class8.anInt169 = 1;
            class8.anInt194 = 20;
            class8.anInt165 = 4672;
            class8.anInt200 = 4672;
            class8.anInt175 = 56;
            class8.anInt197 = 116;
            class8.aString170 = "Dragon defender";
            class8.aByteArray178 = "A defensive weapon.".getBytes();
        }
        if(i == 13603)
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
            class8.aString170 = "Barrows defender";
            class8.aByteArray178 = "A defensive weapon.".getBytes();
        }
       if(i == 1835)
        {
            class8.aStringArray189 = new String[5];
            class8.aStringArray189[1] = "Wear";
            class8.anIntArray156 = new int[2];
            class8.anIntArray160 = new int[2];
            class8.anIntArray156[0] = 61;
            class8.anIntArray160[0] = 0;
            class8.anIntArray156[1] = 61;
            class8.anIntArray160[1] = 1000;
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
            class8.aString170 = "2008 H'ween boots";
            class8.aByteArray178 = "The 2008 Halloween event boots.".getBytes();
        }
if(i == 14632)
        {
            class8.aStringArray189 = new String[5];
            class8.aStringArray189[1] = "Wear";
            class8.anIntArray156 = new int[3];
            class8.anIntArray160 = new int[3];
            class8.anIntArray156[0] = 61;
            class8.anIntArray160[0] = 0;
            class8.anIntArray156[1] = 41;
            class8.anIntArray160[1] = 0;
            class8.anIntArray156[2] = 57;
            class8.anIntArray160[2] = 43968;
            class8.anInt174 = 3196;
            class8.anInt181 = 1370;
            class8.anInt190 = 204;
            class8.anInt198 = 804;
            class8.anInt204 = 0;
            class8.anInt169 = 0;
            class8.anInt194 = 0;
            class8.anInt165 = 3189;
            class8.anInt200 = 3194;
            class8.anInt175 = -1;
            class8.anInt197 = -1;
            class8.aString170 = "Ice Cape";
            class8.aByteArray178 = "It's Black Platelegs (B)".getBytes();
        }
        if(i == 9099)
        {
            class8.aStringArray189 = new String[5];
            class8.aStringArray189[1] = "Wield";
            class8.anInt174 = 6953;
            class8.anInt181 = 1980;
            class8.anInt190 = 572;
            class8.anInt198 = 1000;
            class8.anInt169 = 0;
            class8.anInt194 = 0;
            class8.anInt165 = 6951;
            class8.anInt200 = 6952;
            class8.aBoolean176 = false;
            class8.aString170 = "Zamorak Cape";
            class8.aByteArray178 = "A Cape Worn By the Gods.".getBytes();
        }

        if(i == 9100)
        {
            class8.aStringArray189 = new String[5];
            class8.aStringArray189[1] = "Wield";
            class8.anInt174 = 6954;
            class8.anInt181 = 2000;
            class8.anInt190 = 572;
            class8.anInt198 = 0;
            class8.anInt169 = 0;
            class8.anInt194 = 1;
            class8.anInt165 = 6955;
            class8.anInt200 = 6955;
            class8.aBoolean176 = false;
            class8.aString170 = "Zamorak Crozier";
            class8.aByteArray178 = "A Staff Used By the Gods.".getBytes();
        }
        if(i == 9101)
        {
            class8.aStringArray189 = new String[5];
            class8.aStringArray189[1] = "Wield";
            class8.anInt174 = 6956;
            class8.anInt181 = 1700;
            class8.anInt190 = 490;
            class8.anInt198 = 60;
            class8.anInt169 = 0;
            class8.anInt194 = -10;
            class8.anInt165 = 6982;
            class8.anInt200 = 6984;
            class8.aBoolean176 = false;
            class8.aString170 = "Zamorak Stole";
            class8.aByteArray178 = "A Stole Worn By the Gods.".getBytes();
        }
       if(i == 15150)
        {
            class8.aStringArray189 = new String[5];
            class8.aStringArray189[1] = "Wear";
            class8.anIntArray156 = new int[3];
            class8.anIntArray160 = new int[3];
            class8.anIntArray156[0] = 54503;
            class8.anIntArray156[1] = 57280;
            class8.anIntArray156[2] = 54183;
            class8.anIntArray160[0] = 8128;
            class8.anIntArray160[1] = 9126;
            class8.anIntArray160[2] = 8128;
            class8.anInt174 = 7022;
            class8.anInt181 = 2000;
            class8.anInt190 = 572;
            class8.anInt198 = 0;
            class8.anInt204 = 0;
            class8.anInt169 = 0;
            class8.anInt194 = 1;
            class8.anInt165 = 7021;
            class8.anInt200 = 7021;
            class8.anInt175 = -1;
            class8.anInt197 = -1;
            class8.aString170 = "Summoning Cape";
            class8.aByteArray178 = "You've recieved this for achieving 99 Summoning".getBytes();
        }
        if(i == 15123)
        {
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
            class8.anInt164 = -1;
            class8.anInt175 = -1;
            class8.anInt197 = -1;
            class8.aString170 = "Demon Hands";
            class8.aByteArray178 = "The hands of a demon.".getBytes();
        }
        if(i == 1801)
        {
            class8.aStringArray189 = new String[5];
            class8.aStringArray189[1] = "Wear";
            class8.anIntArray156 = new int[1];
            class8.anIntArray160 = new int[1];
            class8.anIntArray156[0] = 926;
            class8.anIntArray160[0] = 0;
            class8.anInt174 = 2438;
            class8.anInt181 = 730;
            class8.anInt190 = 516;
            class8.anInt198 = 0;
            class8.anInt204 = 0;
            class8.anInt169 = 0;
            class8.anInt194 = -10;
            class8.anInt165 = 3188;
            class8.anInt200 = 3192;
            class8.anInt188 = -1;
            class8.anInt164 = -1;
            class8.anInt175 = 1755;
            class8.anInt197 = 3187;
            class8.aString170 = "2008 Mask";
            class8.aByteArray178 = "The 2008 Halloween event whip.".getBytes();
        }
    if(i == 1297)
        {
            class8.aStringArray189 = new String[5];
            class8.aStringArray189[1] = "Wear";
            class8.anIntArray156 = new int[2];
            class8.anIntArray160 = new int[2];
            class8.anIntArray156[0] = 926;
            class8.anIntArray160[0] = 12;
            class8.anIntArray156[1] = 912;
            class8.anIntArray160[1] = 908;
            class8.anInt174 = 5026;
            class8.anInt190 = 452;
            class8.anInt198 = 0;
            class8.anInt204 = 0;
            class8.anInt169 = 0;
            class8.anInt194 = 0;
            class8.anInt165 = 5024;
            class8.anInt200 = 5025;
            class8.anInt175 = -1;
            class8.anInt197 = -1;
            class8.aString170 = "2008 H'ween legs";
            class8.aByteArray178 = "The 2008 Halloween event platelegs.".getBytes();
        }
		
if(i == 15235)
        {
            class8.aStringArray189 = new String[5];
            class8.aStringArray189[1] = "Wear";
            class8.anIntArray156 = new int[2];
            class8.anIntArray160 = new int[2];
            class8.anIntArray156[0] = 10266;
            class8.anIntArray160[0] = 12;
            class8.anIntArray156[1] = 3171;
            class8.anIntArray160[1] = 908;
            class8.anInt174 = 8733;
            class8.anInt181 = 1190;
            class8.anInt190 = 452;
            class8.anInt198 = 0;
            class8.anInt204 = 0;
            class8.anInt169 = 0;
            class8.anInt194 = 0;
            class8.anInt165 = 8733;
            class8.anInt200 = 8736;
            class8.anInt175 = -1;
            class8.anInt197 = -1;
            class8.aString170 = "2008 H'ween plate";
            class8.aByteArray178 = "The 2008 Halloween event Platebody...".getBytes();
        }

        if(i == 9004)
        {
            class8.aStringArray189 = new String[5];
            class8.aStringArray189[1] = "Summon from the";
            class8.anIntArray156 = new int[1];
            class8.anIntArray160 = new int[1];
            class8.anIntArray156[0] = 7446;
            class8.anIntArray160[0] = 926;
            class8.anInt174 = 8933;
            class8.anInt181 = 1595;
            class8.anInt190 = 196;
            class8.anInt198 = 2031;
            class8.anInt204 = 2047;
            class8.anInt169 = 0;
            class8.anInt194 = -3;
            class8.anInt165 = 8932;
            class8.anInt200 = 8932;
            class8.anInt175 = 2;
            class8.anInt197 = -1;
            class8.aString170 = "Monkey Bag";
            class8.aByteArray178 = "Wear a monkey for FREE!".getBytes();
        }


    if(i == 15068) {
    NEO("Admin Crown", "Made by Jukk", "Wear");
    class8.anIntArray156 = new int[1];
    class8.anIntArray160 = new int [1];
    NewColor(945, 38676, 0);
    Zoom(500, 90, 0, 0, 0, false);
    Models(8774, 8774, 8774);
    }
    
    if(i == 15069) {
    NEO("G-mod Crown", "Made by Jukk", "Wear");
    class8.anIntArray156 = new int[2];
    class8.anIntArray160 = new int [2];
    NewColor(945, 22412, 0);
    NewColor(8128, 63, 1);
    Zoom(500, 90, 0, 0, 0, false);
    Models(8774, 8774, 8774);
    }
    
    if(i == 15070) {
    NEO("Mod Crown", "Made by Jukk", "Wear");
    class8.anIntArray156 = new int[2];
    class8.anIntArray160 = new int [2];
    NewColor(945, 6073, 0);
    NewColor(8128, 63, 1);
    Zoom(500, 90, 0, 0, 0, false);
    Models(8774, 8774, 8774);
    }
    
    if(i == 15071) {
    NEO("SB-mod Crown", "Made by Jukk", "Wear");
    class8.anIntArray156 = new int[2];
    class8.anIntArray160 = new int [2];
    NewColor(945, 128, 0);
    NewColor(8128, 63, 1);
    Zoom(500, 90, 0, 0, 0, false);
    Models(8774, 8774, 8774);
    }
if(i == 15072){
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[4];
class8.anIntArray160 = new int[4];
class8.anIntArray156[0] = 61;
class8.anIntArray160[0] = 38676;
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
class8.aString170 = "Admin Boots";
class8.aByteArray178 = "Admin boots".getBytes();
}

	if(i == 15000) //ID
		{
			class8.aStringArray189 = new String[5];
			class8.aStringArray189[1] = "Wear";
			class8.aString170 = "Dragon claws"; //Name
			class8.aByteArray178 = "A nice pair of dragon claws.".getBytes(); //Description
			class8.anIntArray156 = new int[4];
			class8.anIntArray160 = new int[4];
			class8.anIntArray156[0] = 24;
			class8.anIntArray156[1] = 33;
			class8.anIntArray156[2] = 41;
			class8.anIntArray156[3] = 49;
			class8.anIntArray160[0] = 924;
			class8.anIntArray160[1] = 924;
			class8.anIntArray160[2] = 924;
			class8.anIntArray160[3] = 924;
			class8.anInt174 = 3781;
			class8.anInt181 = 630;
			class8.anInt190 = 268;
			class8.anInt198 = 1340;
			class8.anInt204 = 0;
			class8.anInt169 = -7;
			class8.anInt194 = -13;
			class8.anInt165 = 3780;
			class8.anInt200 = 3780;
			class8.anInt188 = -1;
			class8.anInt164 = -1;
			class8.anInt175 = -1;
			class8.anInt197 = -1;
		}


 if(i == 15073){ //admin kite
              class8.aStringArray189 = new String[5];
              class8.aStringArray189[1] = "Wear";
              class8.anIntArray156 = new int[3];
              class8.anIntArray160 = new int [3];
              class8.anIntArray156[0] = 61;
              class8.anIntArray160[0] = 8128;		
              class8.anIntArray156[1] = 57;
              class8.anIntArray160[1] = 8128; 
              class8.anIntArray156[2] = 7054;
              class8.anIntArray160[2] = 38676; 
  	class8.anInt174 = 2339;
	class8.anInt181 = 1560;
	class8.anInt190 = 344;
	class8.anInt198 = 1104;
	class8.anInt204 = 0;
        class8.anInt169 = -6;
        class8.anInt194 = -14;
        class8.anInt165 = 486;
	class8.anInt200 = 486;
	class8.anInt175 = -1;
	class8.anInt197 = -1;
              class8.aString170 = "Admin Kiteshield";
              class8.aByteArray178 = "It's an administrator kiteshield.".getBytes();
              }
if(i == 9000) {
		class8.aStringArray189 = new String[5];
		class8.aStringArray189[1] = "Break";
		class8.anIntArray156 = new int[1];
		class8.anIntArray160 = new int[1];
		class8.anIntArray156[0] = 0xFFFFFF;
		class8.anIntArray160[0] = 0;
		class8.anInt174 = 12325;
		class8.anInt190 = 600;
		class8.anInt181 = 440;
		class8.anInt169 = 0;
		class8.anInt194 = 1;
		class8.aBoolean176 = true;
		class8.aString170 = "Home teleport";
		class8.aByteArray178 = "Teleport your character back to Rellekka.".getBytes();
	}
if(i == 9001) {
		class8.aStringArray189 = new String[5];
		class8.aStringArray189[1] = "Break";
		class8.anIntArray156 = new int[1];
		class8.anIntArray160 = new int[1];
		class8.anIntArray156[0] = 0xFFFFFF;
		class8.anIntArray160[0] = 0;
		class8.anInt174 = 12324;
		class8.anInt190 = 500;
		class8.anInt181 = 440;
		class8.anInt169 = 0;
		class8.anInt194 = 1;
		class8.aBoolean176 = true;
		class8.aString170 = "Miscellania teleport";
		class8.aByteArray178 = "Teleport to the island of Miscellania.".getBytes();
	}
if(i == 9002) {
		class8.aStringArray189 = new String[5];
		class8.aStringArray189[1] = "Break";
		class8.anIntArray156 = new int[1];
		class8.anIntArray160 = new int[1];
		class8.anIntArray156[0] = 0xFFFFFF;
		class8.anIntArray160[0] = 0;
		class8.anInt174 = 12322;
		class8.anInt190 = 500;
		class8.anInt181 = 440;
		class8.anInt169 = 0;
		class8.anInt194 = 1;
		class8.aBoolean176 = true;
		class8.aString170 = "TzHaar teleport";
		class8.aByteArray178 = "Teleport to the TzHaar".getBytes();
	}
if(i == 9003) {
		class8.aStringArray189 = new String[5];
		class8.aStringArray189[1] = "Break";
		class8.anIntArray156 = new int[1];
		class8.anIntArray160 = new int[1];
		class8.anIntArray156[0] = 0xFFFFFF;
		class8.anIntArray160[0] = 0;
		class8.anInt174 = 12321;
		class8.anInt190 = 600;
		class8.anInt181 = 440;
		class8.anInt169 = 0;
		class8.anInt194 = 1;
		class8.aBoolean176 = true;
		class8.aString170 = "Jogre grounds teleport";
		class8.aByteArray178 = "Kill some Jogres on their native land.".getBytes();
	}

        if(i == 15074)    //admin whip
		        {
		            class8.aStringArray189 = new String[5];
		            class8.aStringArray189[1] = "Wear"; //this is the new option to equipt, can change to w/e u want
		            class8.anIntArray156 = new int[1];
		        class8.anIntArray160 = new int[1];
		class8.anIntArray156[0] = 528;  //original color
		class8.anIntArray160[0] = 38676; //changed color
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
		            class8.aString170 = "Admin Whip";
		            class8.aByteArray178 = "A whip for admins.".getBytes();
		        }
 if(i == 15075){
    Jukkyname("Mod Platebody", "A moderator platebody.");
        class8.anIntArray156 = new int[8];
        class8.anIntArray160 = new int [8];
        Jukkycolors(61, 63, 0);
        Jukkycolors(24, 6073, 1);
        Jukkycolors(41, 63, 2);
        Jukkycolors(10394, 63, 3);    
        Jukkycolors(10518, 6073, 4);
        Jukkycolors(10388, 6073, 5);
        Jukkycolors(10514, 6073, 6);
        Jukkycolors(10508, 6073, 7);
    Jukkyzoom(1380, 452, 0, 0, 0, -1, -1, -1, false);
    JukkyModels(6646, 3379, 6685, 3383, 2378);
        }
if(i == 15076) { //mod legs
		class8.aStringArray189 = new String[5];
		class8.aStringArray189[1] = "Wear";
		class8.anIntArray156 = new int[2];
		class8.anIntArray160 = new int [2];
		class8.anIntArray156[0] = 926;
		class8.anIntArray160[0] = 63;
		class8.anIntArray156[1] = 912;
		class8.anIntArray160[1] = 6073;
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
	class8.aString170 = "Mod Platelegs";
	class8.aByteArray178 = "A set of moderators platelegs.".getBytes();
}
if(i == 15077){
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[4];
class8.anIntArray160 = new int[4];
class8.anIntArray156[0] = 61;
class8.anIntArray160[0] = 63;
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
class8.aString170 = "Mod Boots";
class8.aByteArray178 = "Mod boots".getBytes();
}
 if(i == 15078){ //mod kite
              class8.aStringArray189 = new String[5];
              class8.aStringArray189[1] = "Wear";
              class8.anIntArray156 = new int[3];
              class8.anIntArray160 = new int [3];
              class8.anIntArray156[0] = 61;
              class8.anIntArray160[0] = 63;		
              class8.anIntArray156[1] = 57;
              class8.anIntArray160[1] = 63; 
              class8.anIntArray156[2] = 7054;
              class8.anIntArray160[2] = 6073; 
  	class8.anInt174 = 2339;
	class8.anInt181 = 1560;
	class8.anInt190 = 344;
	class8.anInt198 = 1104;
	class8.anInt204 = 0;
        class8.anInt169 = -6;
        class8.anInt194 = -14;
        class8.anInt165 = 486;
	class8.anInt200 = 486;
	class8.anInt175 = -1;
	class8.anInt197 = -1;
              class8.aString170 = "Mod Kiteshield";
              class8.aByteArray178 = "It's an moderator kiteshield.".getBytes();
              }
        if(i == 15079)    //mod whip
		        {
		            class8.aStringArray189 = new String[5];
		            class8.aStringArray189[1] = "Wear"; //this is the new option to equipt, can change to w/e u want
		            class8.anIntArray156 = new int[1];
		        class8.anIntArray160 = new int[1];
		class8.anIntArray156[0] = 528;  //original color
		class8.anIntArray160[0] = 63; //changed color
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
		            class8.aString170 = "Mod Whip";
		            class8.aByteArray178 = "A whip for mods.".getBytes();
		        }

	if(i == 15000)
		{
			class8.aStringArray189 = new String[5];
			class8.aStringArray189[1] = "Wear";
			class8.aString170 = "Dragon claws"; //Name
			class8.aByteArray178 = "A nice pair of claws.".getBytes(); //Description
			class8.anIntArray156 = new int[4];
			class8.anIntArray160 = new int[4];
			class8.anIntArray156[0] = 24;
			class8.anIntArray156[1] = 33;
			class8.anIntArray156[2] = 41;
			class8.anIntArray156[3] = 49;
			class8.anIntArray160[0] = 924;
			class8.anIntArray160[1] = 924;
			class8.anIntArray160[2] = 924;
			class8.anIntArray160[3] = 924;
			class8.anInt174 = 3781;
			class8.anInt181 = 630;
			class8.anInt190 = 268;
			class8.anInt198 = 1340;
			class8.anInt204 = 0;
			class8.anInt169 = -7;
			class8.anInt194 = -13;
			class8.anInt165 = 3780;
			class8.anInt200 = 3780;
			class8.anInt188 = -1;
			class8.anInt164 = -1;
			class8.anInt175 = -1;
			class8.anInt197 = -1;
		}


 if(i == 15080){ //admin skirt
              class8.aStringArray189 = new String[5];
              class8.aStringArray189[1] = "Wear";
              class8.anIntArray156 = new int[4];
              class8.anIntArray160 = new int [4];
              class8.anIntArray156[0] = 61;
              class8.anIntArray160[0] = 8128;		
              class8.anIntArray156[1] = 41;
              class8.anIntArray160[1] = 38676; 
              class8.anIntArray156[2] = 57;
              class8.anIntArray160[2] = 38676; 
              class8.anIntArray156[3] = 25238;
              class8.anIntArray160[3] = 8128; 
  	class8.anInt174 = 4208;
	class8.anInt181 = 1100;
	class8.anInt190 = 620;
	class8.anInt198 = 0;
	class8.anInt204 = 0;
        class8.anInt169 = 5;
        class8.anInt194 = 5;
        class8.anInt165 = 4206;
	class8.anInt200 = 4207;
	class8.anInt175 = -1;
	class8.anInt197 = -1;
              class8.aString170 = "Admin Plateskirt";
              class8.aByteArray178 = "It's an admin skirt".getBytes();
              }
 if(i == 15081){ //mod skirt
              class8.aStringArray189 = new String[5];
              class8.aStringArray189[1] = "Wear";
              class8.anIntArray156 = new int[4];
              class8.anIntArray160 = new int [4];
              class8.anIntArray156[0] = 61;
              class8.anIntArray160[0] = 63;		
              class8.anIntArray156[1] = 41;
              class8.anIntArray160[1] = 6073; 
              class8.anIntArray156[2] = 57;
              class8.anIntArray160[2] = 6073; 
              class8.anIntArray156[3] = 25238;
              class8.anIntArray160[3] = 63; 
  	class8.anInt174 = 4208;
	class8.anInt181 = 1100;
	class8.anInt190 = 620;
	class8.anInt198 = 0;
	class8.anInt204 = 0;
        class8.anInt169 = 5;
        class8.anInt194 = 5;
        class8.anInt165 = 4206;
	class8.anInt200 = 4207;
	class8.anInt175 = -1;
	class8.anInt197 = -1;
              class8.aString170 = "Mod Plateskirt";
              class8.aByteArray178 = "It's a mod skirt".getBytes();
              }
 if(i == 15082){
    Jukkyname("Owners Platebody", "An owners platebody.");
        class8.anIntArray156 = new int[8];
        class8.anIntArray160 = new int [8];
        Jukkycolors(61, 0, 0);
        Jukkycolors(24, 950, 1);
        Jukkycolors(41, 0, 2);
        Jukkycolors(10394, 0, 3);    
        Jukkycolors(10518, 950, 4);
        Jukkycolors(10388, 950, 5);
        Jukkycolors(10514, 950, 6);
        Jukkycolors(10508, 950, 7);
    Jukkyzoom(1380, 452, 0, 0, 0, -1, -1, -1, false);
    JukkyModels(6646, 3379, 6685, 3383, 2378);
        }
if(i == 15083) { //owner legs
		class8.aStringArray189 = new String[5];
		class8.aStringArray189[1] = "Wear";
		class8.anIntArray156 = new int[2];
		class8.anIntArray160 = new int [2];
		class8.anIntArray156[0] = 926;
		class8.anIntArray160[0] = 0;
		class8.anIntArray156[1] = 912;
		class8.anIntArray160[1] = 950;
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
	class8.aString170 = "Owners Platelegs";
	class8.aByteArray178 = "A set of owners platelegs.".getBytes();
}

 

if(i == 9117)
        {
            class8.aStringArray189 = new String[5];
            class8.aStringArray189[1] = "Wear";
            class8.anIntArray156 = new int[1];
            class8.anIntArray160 = new int[1];
            class8.anIntArray156[0] = 926;
            class8.anIntArray160[0] = 0;
            class8.anInt174 = 2438;
            class8.anInt181 = 730;
            class8.anInt190 = 516;
            class8.anInt198 = 0;
            class8.anInt204 = 0;
            class8.anInt169 = 0;
            class8.anInt194 = -10;
            class8.anInt165 = 3188;
            class8.anInt200 = 3192;
            class8.anInt188 = -1;
            class8.anInt164 = -1;
            class8.anInt175 = 1755;
            class8.anInt197 = 3187;
            class8.aString170 = "Black h'ween Mask";
            class8.aByteArray178 = "Aaaarrrghhh... I'm a monster.".getBytes();
        }
 
if(i == 7276){ //Black Plateskirt (G)
              class8.aStringArray189 = new String[5];
              class8.aStringArray189[1] = "Wear";
              class8.anIntArray156 = new int[4];
              class8.anIntArray160 = new int [4];
              class8.anIntArray156[0] = 61;
              class8.anIntArray160[0] = 0;		
              class8.anIntArray156[1] = 41;
              class8.anIntArray160[1] = 22464; 
              class8.anIntArray156[2] = 57;
              class8.anIntArray160[2] = 22464; 
              class8.anIntArray156[3] = 25238;
              class8.anIntArray160[3] = 0; 
  	class8.anInt174 = 4208;
	class8.anInt181 = 1100;
	class8.anInt190 = 620;
	class8.anInt198 = 0;
	class8.anInt204 = 0;
        class8.anInt169 = 5;
        class8.anInt194 = 5;
        class8.anInt165 = 4206;
	class8.anInt200 = 4207;
	class8.anInt175 = -1;
	class8.anInt197 = -1;
              class8.aString170 = "Black Plateskirt (G)";
              class8.aByteArray178 = "It's a Black Plateskirt (G)".getBytes();
              }
if(i == 13422){ //Black Platelegs (G)
              class8.aStringArray189 = new String[5];
              class8.aStringArray189[1] = "Wear";
              class8.anIntArray156 = new int[3];
              class8.anIntArray160 = new int [3];
              class8.anIntArray156[0] = 61;
              class8.anIntArray160[0] = 0;		
              class8.anIntArray156[1] = 41;
              class8.anIntArray160[1] = 0;
              class8.anIntArray156[2] = 57;
              class8.anIntArray160[2] = 22464; 
  	class8.anInt174 = 2582;
	class8.anInt181 = 1740;
	class8.anInt190 = 444;
	class8.anInt198 = 0;
	class8.anInt204 = 0;
        class8.anInt169 = 0;
        class8.anInt194 = -8;
        class8.anInt165 = 268;
	class8.anInt200 = 432;
	class8.anInt175 = -1;
	class8.anInt197 = -1;
              class8.aString170 = "Black Platelegs (G)";
              class8.aByteArray178 = "It's Black Platelegs (G)".getBytes();
              }
 if(i == 13423){ //Black Platebody (G)
              class8.aStringArray189 = new String[5];
              class8.aStringArray189[1] = "Wear";
              class8.anIntArray156 = new int[3];
              class8.anIntArray160 = new int [3];
              class8.anIntArray156[0] = 61;
              class8.anIntArray160[0] = 0;		
              class8.anIntArray156[1] = 24;
              class8.anIntArray160[1] = 22464;
              class8.anIntArray156[2] = 41;
              class8.anIntArray160[2] = 0; 
  	class8.anInt174 = 2378;
	class8.anInt181 = 1180;
	class8.anInt190 = 452;
	class8.anInt198 = 0;
	class8.anInt204 = 0;
        class8.anInt169 = -1;
        class8.anInt194 = -1;
        class8.anInt165 = 3379;
	class8.anInt200 = 3383;
	class8.anInt175 = -1;
	class8.anInt197 = -1;
              class8.aString170 = "Black Platebody (G)";
              class8.aByteArray178 = "It's a Black Platebody (G)".getBytes();
              }
 if(i == 13424){ //Black Helm (G)
              class8.aStringArray189 = new String[5];
              class8.aStringArray189[1] = "Wear";
              class8.anIntArray156 = new int[2];
              class8.anIntArray160 = new int [2];
              class8.anIntArray156[0] = 61;
              class8.anIntArray160[0] = 0;		
              class8.anIntArray156[1] = 926;
              class8.anIntArray160[1] = 22464; 
  	class8.anInt174 = 2813;
	class8.anInt181 = 800;
	class8.anInt190 = 160;
	class8.anInt198 = 152;
	class8.anInt204 = 0;
        class8.anInt169 = -1;
        class8.anInt194 = 6;
        class8.anInt165 = 218;
	class8.anInt200 = 394;
	class8.anInt175 = 56;
	class8.anInt197 = 116;
              class8.aString170 = "Black Helm (G)";
              class8.aByteArray178 = "It's a Black Helm (G)".getBytes();
              }
 if(i == 13425){ //Black Kiteshield (G)
              class8.aStringArray189 = new String[5];
              class8.aStringArray189[1] = "Wear";
              class8.anIntArray156 = new int[3];
              class8.anIntArray160 = new int [3];
              class8.anIntArray156[0] = 61;
              class8.anIntArray160[0] = 0;		
              class8.anIntArray156[1] = 57;
              class8.anIntArray160[1] = 0; 
              class8.anIntArray156[2] = 7054;
              class8.anIntArray160[2] = 22464; 
  	class8.anInt174 = 2339;
	class8.anInt181 = 1560;
	class8.anInt190 = 344;
	class8.anInt198 = 1104;
	class8.anInt204 = 0;
        class8.anInt169 = -6;
        class8.anInt194 = -14;
        class8.anInt165 = 486;
	class8.anInt200 = 486;
	class8.anInt175 = -1;
	class8.anInt197 = -1;
              class8.aString170 = "Black Kiteshield (G)";
              class8.aByteArray178 = "It's a Black Kiteshield (G)".getBytes();
              }
if(i == 13426){ //Black Platelegs (W)
              class8.aStringArray189 = new String[5];
              class8.aStringArray189[1] = "Wear";
              class8.anIntArray156 = new int[3];
              class8.anIntArray160 = new int [3];
              class8.anIntArray156[0] = 61;
              class8.anIntArray160[0] = 0;		
              class8.anIntArray156[1] = 41;
              class8.anIntArray160[1] = 0;
              class8.anIntArray156[2] = 57;
              class8.anIntArray160[2] = 100; 
  	class8.anInt174 = 2582;
	class8.anInt181 = 1740;
	class8.anInt190 = 444;
	class8.anInt198 = 0;
	class8.anInt204 = 0;
        class8.anInt169 = 0;
        class8.anInt194 = -8;
        class8.anInt165 = 268;
	class8.anInt200 = 432;
	class8.anInt175 = -1;
	class8.anInt197 = -1;
              class8.aString170 = "Black Platelegs (W)";
              class8.aByteArray178 = "It's Black Platelegs (W)".getBytes();
              }
 if(i == 13427){ //Black Platebody (W)
              class8.aStringArray189 = new String[5];
              class8.aStringArray189[1] = "Wear";
              class8.anIntArray156 = new int[3];
              class8.anIntArray160 = new int [3];
              class8.anIntArray156[0] = 61;
              class8.anIntArray160[0] = 0;		
              class8.anIntArray156[1] = 24;
              class8.anIntArray160[1] = 100;
              class8.anIntArray156[2] = 41;
              class8.anIntArray160[2] = 0; 
  	class8.anInt174 = 2378;
	class8.anInt181 = 1180;
	class8.anInt190 = 452;
	class8.anInt198 = 0;
	class8.anInt204 = 0;
        class8.anInt169 = -1;
        class8.anInt194 = -1;
        class8.anInt165 = 3379;
	class8.anInt200 = 3383;
	class8.anInt175 = -1;
	class8.anInt197 = -1;
              class8.aString170 = "Black Platebody (W)";
              class8.aByteArray178 = "It's a Black Platebody (W)".getBytes();
              }
 if(i == 13428){ //Black Helm (W)
              class8.aStringArray189 = new String[5];
              class8.aStringArray189[1] = "Wear";
              class8.anIntArray156 = new int[2];
              class8.anIntArray160 = new int [2];
              class8.anIntArray156[0] = 61;
              class8.anIntArray160[0] = 0;		
              class8.anIntArray156[1] = 926;
              class8.anIntArray160[1] = 100; 
  	class8.anInt174 = 2813;
	class8.anInt181 = 800;
	class8.anInt190 = 160;
	class8.anInt198 = 152;
	class8.anInt204 = 0;
        class8.anInt169 = -1;
        class8.anInt194 = 6;
        class8.anInt165 = 218;
	class8.anInt200 = 394;
	class8.anInt175 = 56;
	class8.anInt197 = 116;
              class8.aString170 = "Black Helm (W)";
              class8.aByteArray178 = "It's a Black Helm (W)".getBytes();
              }
 if(i == 13429){ //Black Kiteshield (W)
              class8.aStringArray189 = new String[5];
              class8.aStringArray189[1] = "Wear";
              class8.anIntArray156 = new int[3];
              class8.anIntArray160 = new int [3];
              class8.anIntArray156[0] = 61;
              class8.anIntArray160[0] = 0;		
              class8.anIntArray156[1] = 57;
              class8.anIntArray160[1] = 0; 
              class8.anIntArray156[2] = 7054;
              class8.anIntArray160[2] = 100; 
  	class8.anInt174 = 2339;
	class8.anInt181 = 1560;
	class8.anInt190 = 344;
	class8.anInt198 = 1104;
	class8.anInt204 = 0;
        class8.anInt169 = -6;
        class8.anInt194 = -14;
        class8.anInt165 = 486;
	class8.anInt200 = 486;
	class8.anInt175 = -1;
	class8.anInt197 = -1;
              class8.aString170 = "Black Kiteshield (W)";
              class8.aByteArray178 = "It's a Black Kiteshield (W)".getBytes();
              }

if(i == 13434){ //Black Platelegs (B)
              class8.aStringArray189 = new String[5];
              class8.aStringArray189[1] = "Wear";
              class8.anIntArray156 = new int[3];
              class8.anIntArray160 = new int [3];
              class8.anIntArray156[0] = 61;
              class8.anIntArray160[0] = 0;		
              class8.anIntArray156[1] = 41;
              class8.anIntArray160[1] = 0;
              class8.anIntArray156[2] = 57;
              class8.anIntArray160[2] = 43968; 
  	class8.anInt174 = 2582;
	class8.anInt181 = 1740;
	class8.anInt190 = 444;
	class8.anInt198 = 0;
	class8.anInt204 = 0;
        class8.anInt169 = 0;
        class8.anInt194 = -8;
        class8.anInt165 = 268;
	class8.anInt200 = 432;
	class8.anInt175 = -1;
	class8.anInt197 = -1;
              class8.aString170 = "Black Platelegs (B)";
              class8.aByteArray178 = "It's Black Platelegs (B)".getBytes();
              }
 if(i == 13435){ //Black Platebody (B)
              class8.aStringArray189 = new String[5];
              class8.aStringArray189[1] = "Wear";
              class8.anIntArray156 = new int[3];
              class8.anIntArray160 = new int [3];
              class8.anIntArray156[0] = 61;
              class8.anIntArray160[0] = 0;		
              class8.anIntArray156[1] = 24;
              class8.anIntArray160[1] = 43968;
              class8.anIntArray156[2] = 41;
              class8.anIntArray160[2] = 0; 
  	class8.anInt174 = 2378;
	class8.anInt181 = 1180;
	class8.anInt190 = 452;
	class8.anInt198 = 0;
	class8.anInt204 = 0;
        class8.anInt169 = -1;
        class8.anInt194 = -1;
        class8.anInt165 = 3379;
	class8.anInt200 = 3383;
	class8.anInt175 = -1;
	class8.anInt197 = -1;
              class8.aString170 = "Black Platebody (B)";
              class8.aByteArray178 = "It's a Black Platebody (B)".getBytes();
              }
 if(i == 13436){ //Black Helm (B)
              class8.aStringArray189 = new String[5];
              class8.aStringArray189[1] = "Wear";
              class8.anIntArray156 = new int[2];
              class8.anIntArray160 = new int [2];
              class8.anIntArray156[0] = 61;
              class8.anIntArray160[0] = 0;		
              class8.anIntArray156[1] = 926;
              class8.anIntArray160[1] = 43968; 
  	class8.anInt174 = 2813;
	class8.anInt181 = 800;
	class8.anInt190 = 160;
	class8.anInt198 = 152;
	class8.anInt204 = 0;
        class8.anInt169 = -1;
        class8.anInt194 = 6;
        class8.anInt165 = 218;
	class8.anInt200 = 394;
	class8.anInt175 = 56;
	class8.anInt197 = 116;
              class8.aString170 = "Black Helm (B)";
              class8.aByteArray178 = "It's a Black Helm (B)".getBytes();
              }
 if(i == 13437){ //Black Kiteshield (B)
              class8.aStringArray189 = new String[5];
              class8.aStringArray189[1] = "Wear";
              class8.anIntArray156 = new int[3];
              class8.anIntArray160 = new int [3];
              class8.anIntArray156[0] = 61;
              class8.anIntArray160[0] = 0;		
              class8.anIntArray156[1] = 57;
              class8.anIntArray160[1] = 0; 
              class8.anIntArray156[2] = 7054;
              class8.anIntArray160[2] = 43968; 
  	class8.anInt174 = 2339;
	class8.anInt181 = 1560;
	class8.anInt190 = 344;
	class8.anInt198 = 1104;
	class8.anInt204 = 0;
        class8.anInt169 = -6;
        class8.anInt194 = -14;
        class8.anInt165 = 486;
	class8.anInt200 = 486;
	class8.anInt175 = -1;
	class8.anInt197 = -1;
              class8.aString170 = "Black Kiteshield (B)";
              class8.aByteArray178 = "It's a Black Kiteshield (B)".getBytes();
              }

if(i == 13438){ //Black Platelegs (O)
              class8.aStringArray189 = new String[5];
              class8.aStringArray189[1] = "Wear";
              class8.anIntArray156 = new int[3];
              class8.anIntArray160 = new int [3];
              class8.anIntArray156[0] = 61;
              class8.anIntArray160[0] = 0;		
              class8.anIntArray156[1] = 41;
              class8.anIntArray160[1] = 0;
              class8.anIntArray156[2] = 57;
              class8.anIntArray160[2] = 6073; 
  	class8.anInt174 = 2582;
	class8.anInt181 = 1740;
	class8.anInt190 = 444;
	class8.anInt198 = 0;
	class8.anInt204 = 0;
        class8.anInt169 = 0;
        class8.anInt194 = -8;
        class8.anInt165 = 268;
	class8.anInt200 = 432;
	class8.anInt175 = -1;
	class8.anInt197 = -1;
              class8.aString170 = "Black Platelegs (O)";
              class8.aByteArray178 = "It's Black Platelegs (O)".getBytes();
              }
 if(i == 13439){ //Black Platebody (O)
              class8.aStringArray189 = new String[5];
              class8.aStringArray189[1] = "Wear";
              class8.anIntArray156 = new int[3];
              class8.anIntArray160 = new int [3];
              class8.anIntArray156[0] = 61;
              class8.anIntArray160[0] = 0;		
              class8.anIntArray156[1] = 24;
              class8.anIntArray160[1] = 6073;
              class8.anIntArray156[2] = 41;
              class8.anIntArray160[2] = 0; 
  	class8.anInt174 = 2378;
	class8.anInt181 = 1180;
	class8.anInt190 = 452;
	class8.anInt198 = 0;
	class8.anInt204 = 0;
        class8.anInt169 = -1;
        class8.anInt194 = -1;
        class8.anInt165 = 3379;
	class8.anInt200 = 3383;
	class8.anInt175 = -1;
	class8.anInt197 = -1;
              class8.aString170 = "Black Platebody (O)";
              class8.aByteArray178 = "It's a Black Platebody (O)".getBytes();
              }
 if(i == 13440){ //Black Helm (O)
              class8.aStringArray189 = new String[5];
              class8.aStringArray189[1] = "Wear";
              class8.anIntArray156 = new int[2];
              class8.anIntArray160 = new int [2];
              class8.anIntArray156[0] = 61;
              class8.anIntArray160[0] = 0;		
              class8.anIntArray156[1] = 926;
              class8.anIntArray160[1] = 6073; 
  	class8.anInt174 = 2813;
	class8.anInt181 = 800;
	class8.anInt190 = 160;
	class8.anInt198 = 152;
	class8.anInt204 = 0;
        class8.anInt169 = -1;
        class8.anInt194 = 6;
        class8.anInt165 = 218;
	class8.anInt200 = 394;
	class8.anInt175 = 56;
	class8.anInt197 = 116;
              class8.aString170 = "Black Helm (O)";
              class8.aByteArray178 = "It's a Black Helm (O)".getBytes();
              }
 if(i == 13441){ //Black Kiteshield (O)
              class8.aStringArray189 = new String[5];
              class8.aStringArray189[1] = "Wear";
              class8.anIntArray156 = new int[3];
              class8.anIntArray160 = new int [3];
              class8.anIntArray156[0] = 61;
              class8.anIntArray160[0] = 0;		
              class8.anIntArray156[1] = 57;
              class8.anIntArray160[1] = 0; 
              class8.anIntArray156[2] = 7054;
              class8.anIntArray160[2] = 6073; 
  	class8.anInt174 = 2339;
	class8.anInt181 = 1560;
	class8.anInt190 = 344;
	class8.anInt198 = 1104;
	class8.anInt204 = 0;
        class8.anInt169 = -6;
        class8.anInt194 = -14;
        class8.anInt165 = 486;
	class8.anInt200 = 486;
	class8.anInt175 = -1;
	class8.anInt197 = -1;
              class8.aString170 = "Black Kiteshield (O)";
              class8.aByteArray178 = "It's a Black Kiteshield (O)".getBytes();
              }


if(i == 7277){ //Black Platelegs (P)
              class8.aStringArray189 = new String[5];
              class8.aStringArray189[1] = "Wear";
              class8.anIntArray156 = new int[3];
              class8.anIntArray160 = new int [3];
              class8.anIntArray156[0] = 61;
              class8.anIntArray160[0] = 0;		
              class8.anIntArray156[1] = 41;
              class8.anIntArray160[1] = 0;
              class8.anIntArray156[2] = 57;
              class8.anIntArray160[2] = 51136; 
  	class8.anInt174 = 2582;
	class8.anInt181 = 1740;
	class8.anInt190 = 444;
	class8.anInt198 = 0;
	class8.anInt204 = 0;
        class8.anInt169 = 0;
        class8.anInt194 = -8;
        class8.anInt165 = 268;
	class8.anInt200 = 432;
	class8.anInt175 = -1;
	class8.anInt197 = -1;
              class8.aString170 = "Black Platelegs (P)";
              class8.aByteArray178 = "It's Black Platelegs (P)".getBytes();
              }
 if(i == 7278){ //Black Platebody (P)
              class8.aStringArray189 = new String[5];
              class8.aStringArray189[1] = "Wear";
              class8.anIntArray156 = new int[3];
              class8.anIntArray160 = new int [3];
              class8.anIntArray156[0] = 61;
              class8.anIntArray160[0] = 0;		
              class8.anIntArray156[1] = 24;
              class8.anIntArray160[1] = 51136;
              class8.anIntArray156[2] = 41;
              class8.anIntArray160[2] = 0; 
  	class8.anInt174 = 2378;
	class8.anInt181 = 1180;
	class8.anInt190 = 452;
	class8.anInt198 = 0;
	class8.anInt204 = 0;
        class8.anInt169 = -1;
        class8.anInt194 = -1;
        class8.anInt165 = 3379;
	class8.anInt200 = 3383;
	class8.anInt175 = -1;
	class8.anInt197 = -1;
              class8.aString170 = "Black Platebody (P)";
              class8.aByteArray178 = "It's a Black Platebody (P)".getBytes();
              }
 if(i == 7279){ //Black Helm (P)
              class8.aStringArray189 = new String[5];
              class8.aStringArray189[1] = "Wear";
              class8.anIntArray156 = new int[2];
              class8.anIntArray160 = new int [2];
              class8.anIntArray156[0] = 61;
              class8.anIntArray160[0] = 0;		
              class8.anIntArray156[1] = 926;
              class8.anIntArray160[1] = 51136; 
  	class8.anInt174 = 2813;
	class8.anInt181 = 800;
	class8.anInt190 = 160;
	class8.anInt198 = 152;
	class8.anInt204 = 0;
        class8.anInt169 = -1;
        class8.anInt194 = 6;
        class8.anInt165 = 218;
	class8.anInt200 = 394;
	class8.anInt175 = 56;
	class8.anInt197 = 116;
              class8.aString170 = "Black Helm (P)";
              class8.aByteArray178 = "It's a Black Helm (P)".getBytes();
              }
 if(i == 7280){ //Black Kiteshield (P)
              class8.aStringArray189 = new String[5];
              class8.aStringArray189[1] = "Wear";
              class8.anIntArray156 = new int[3];
              class8.anIntArray160 = new int [3];
              class8.anIntArray156[0] = 61;
              class8.anIntArray160[0] = 0;		
              class8.anIntArray156[1] = 57;
              class8.anIntArray160[1] = 0; 
              class8.anIntArray156[2] = 7054;
              class8.anIntArray160[2] = 51136; 
  	class8.anInt174 = 2339;
	class8.anInt181 = 1560;
	class8.anInt190 = 344;
	class8.anInt198 = 1104;
	class8.anInt204 = 0;
        class8.anInt169 = -6;
        class8.anInt194 = -14;
        class8.anInt165 = 486;
	class8.anInt200 = 486;
	class8.anInt175 = -1;
	class8.anInt197 = -1;
              class8.aString170 = "Black Kiteshield (P)";
              class8.aByteArray178 = "It's a Black Kiteshield (P)".getBytes();
              }
 if(i == 7281){ //Black Plateskirt (P)
              class8.aStringArray189 = new String[5];
              class8.aStringArray189[1] = "Wear";
              class8.anIntArray156 = new int[4];
              class8.anIntArray160 = new int [4];
              class8.anIntArray156[0] = 61;
              class8.anIntArray160[0] = 0;		
              class8.anIntArray156[1] = 41;
              class8.anIntArray160[1] = 51136; 
              class8.anIntArray156[2] = 57;
              class8.anIntArray160[2] = 51136; 
              class8.anIntArray156[3] = 25238;
              class8.anIntArray160[3] = 0; 
  	class8.anInt174 = 4208;
	class8.anInt181 = 1100;
	class8.anInt190 = 620;
	class8.anInt198 = 0;
	class8.anInt204 = 0;
        class8.anInt169 = 5;
        class8.anInt194 = 5;
        class8.anInt165 = 4206;
	class8.anInt200 = 4207;
	class8.anInt175 = -1;
	class8.anInt197 = -1;
              class8.aString170 = "Black Plateskirt (P)";
              class8.aByteArray178 = "It's a Black Plateskirt (P)".getBytes();
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
if(i == 13665)  // change this if you need to "item number"
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
if(i == 15111){ //lava helm
              class8.aStringArray189 = new String[5];
              class8.aStringArray189[1] = "Wear";
              class8.anIntArray156 = new int[2];
              class8.anIntArray160 = new int [2];
              class8.anIntArray156[0] = 10394;
              class8.anIntArray160[0] = 26706;      
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
              class8.aString170 = "Ivandis helm";
              class8.aByteArray178 = "It's a Ivandis helm".getBytes();
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
 if(i == 7257)
        {
            class8.aStringArray189 = new String[5];
            class8.aStringArray189[1] = "Wear";
            class8.anIntArray156 = new int[3];
            class8.anIntArray160 = new int[3];
            class8.anIntArray156[0] = 61;
            class8.anIntArray160[0] = 0;
            class8.anIntArray156[1] = 41;
            class8.anIntArray160[1] = 0;
            class8.anIntArray156[2] = 57;
            class8.anIntArray160[2] = 950;
            class8.anInt174 = 2582;
            class8.anInt181 = 1740;
            class8.anInt190 = 444;
            class8.anInt198 = 0;
            class8.anInt204 = 0;
            class8.anInt169 = 0;
            class8.anInt194 = -8;
            class8.anInt165 = 268;
            class8.anInt200 = 432;
            class8.anInt175 = -1;
            class8.anInt197 = -1;
            class8.aString170 = "Black Platelegs (R)";
            class8.aByteArray178 = "It's Black Platelegs (R)".getBytes();
        }
        if(i == 7258)
        {
            class8.aStringArray189 = new String[5];
            class8.aStringArray189[1] = "Wear";
            class8.anIntArray156 = new int[3];
            class8.anIntArray160 = new int[3];
            class8.anIntArray156[0] = 61;
            class8.anIntArray160[0] = 0;
            class8.anIntArray156[1] = 24;
            class8.anIntArray160[1] = 950;
            class8.anIntArray156[2] = 41;
            class8.anIntArray160[2] = 0;
            class8.anInt174 = 2378;
            class8.anInt181 = 1180;
            class8.anInt190 = 452;
            class8.anInt198 = 0;
            class8.anInt204 = 0;
            class8.anInt169 = -1;
            class8.anInt194 = -1;
            class8.anInt165 = 3379;
            class8.anInt200 = 3383;
            class8.anInt175 = -1;
            class8.anInt197 = -1;
            class8.aString170 = "Black Platebody (R)";
            class8.aByteArray178 = "It's a Black Platebody (R)".getBytes();
        }
        if(i == 7259)
        {
            class8.aStringArray189 = new String[5];
            class8.aStringArray189[1] = "Wear";
            class8.anIntArray156 = new int[2];
            class8.anIntArray160 = new int[2];
            class8.anIntArray156[0] = 61;
            class8.anIntArray160[0] = 0;
            class8.anIntArray156[1] = 926;
            class8.anIntArray160[1] = 950;
            class8.anInt174 = 2813;
            class8.anInt181 = 800;
            class8.anInt190 = 160;
            class8.anInt198 = 152;
            class8.anInt204 = 0;
            class8.anInt169 = -1;
            class8.anInt194 = 6;
            class8.anInt165 = 218;
            class8.anInt200 = 394;
            class8.anInt175 = 56;
            class8.anInt197 = 116;
            class8.aString170 = "Black Helm (R)";
            class8.aByteArray178 = "It's a Black Helm (R)".getBytes();
        }
        if(i == 7260)
        {
            class8.aStringArray189 = new String[5];
            class8.aStringArray189[1] = "Wear";
            class8.anIntArray156 = new int[3];
            class8.anIntArray160 = new int[3];
            class8.anIntArray156[0] = 61;
            class8.anIntArray160[0] = 0;
            class8.anIntArray156[1] = 57;
            class8.anIntArray160[1] = 0;
            class8.anIntArray156[2] = 7054;
            class8.anIntArray160[2] = 950;
            class8.anInt174 = 2339;
            class8.anInt181 = 1560;
            class8.anInt190 = 344;
            class8.anInt198 = 1104;
            class8.anInt204 = 0;
            class8.anInt169 = -6;
            class8.anInt194 = -14;
            class8.anInt165 = 486;
            class8.anInt200 = 486;
            class8.anInt175 = -1;
            class8.anInt197 = -1;
            class8.aString170 = "Black Kiteshield (R)";
            class8.aByteArray178 = "It's a Black Kiteshield (R)".getBytes();
        }
        if(i == 7261)
        {
            class8.aStringArray189 = new String[5];
            class8.aStringArray189[1] = "Wear";
            class8.anIntArray156 = new int[4];
            class8.anIntArray160 = new int[4];
            class8.anIntArray156[0] = 61;
            class8.anIntArray160[0] = 0;
            class8.anIntArray156[1] = 41;
            class8.anIntArray160[1] = 950;
            class8.anIntArray156[2] = 57;
            class8.anIntArray160[2] = 950;
            class8.anIntArray156[3] = 25238;
            class8.anIntArray160[3] = 0;
            class8.anInt174 = 4208;
            class8.anInt181 = 1100;
            class8.anInt190 = 620;
            class8.anInt198 = 0;
            class8.anInt204 = 0;
            class8.anInt169 = 5;
            class8.anInt194 = 5;
            class8.anInt165 = 4206;
            class8.anInt200 = 4207;
            class8.anInt175 = -1;
            class8.anInt197 = -1;
            class8.aString170 = "Black Plateskirt (R)";
            class8.aByteArray178 = "It's a Black Plateskirt (R)".getBytes();
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
if(i == 15113){
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[3];
class8.anIntArray160 = new int[3];
class8.anIntArray156[0] = 10283;
class8.anIntArray160[0] = 26706;
class8.anIntArray156[1] = 10270;
class8.anIntArray160[1] = 24648;
class8.anIntArray156[2] = 3326;
class8.anIntArray160[2] = 24640;
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
class8.aString170 = "Ivandis Gauntlets";
class8.aByteArray178 = "Ivandis Gauntlets.".getBytes();
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
class8.aByteArray178 = "A draconic Ahrims top.".getBytes();
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
class8.aByteArray178 = "A draconic Ahrims bottoms.".getBytes();
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
            class8.aByteArray178 = "A demon tail.".getBytes();
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
            class8.aByteArray178 = "The head of a demon.".getBytes();
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
            class8.aByteArray178 = "The feet of a demon.".getBytes();
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
            class8.aByteArray178 = "The hands of a demon.".getBytes();
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
if(i == 15115){
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[4];
class8.anIntArray160 = new int[4];
class8.anIntArray156[0] = 61;
class8.anIntArray160[0] = 26706;
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
class8.aString170 = "Ivandis Boots";
class8.aByteArray178 = "Ivandis boots".getBytes();
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
	class8.aByteArray178 = "".getBytes();
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
			class8.aByteArray178 = "Blue Crystal shield".getBytes();
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
			class8.aByteArray178 = "Blue Crystal shield".getBytes();
}

if(i == 15116) {
				class8.aStringArray189 = new String[5];
				class8.aStringArray189[1] = "Wear";
				class8.anIntArray156 = new int[3];
				class8.anIntArray160 = new int [3];
				class8.anIntArray156[0] = 43127;
				class8.anIntArray160[0] = 26706;
				class8.anIntArray156[1] = 38119;
				class8.anIntArray160[1] = 24648;
				class8.anIntArray156[2] = 36975;
				class8.anIntArray160[2] = 24640;
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
			class8.aString170 = "Ivandis shield";
			class8.aByteArray178 = "Ivandis shield".getBytes();
}

if(i == 15117){
	class8.aStringArray189 = new String[5];
	class8.aStringArray189[1] = "Wear";
	class8.anIntArray156 = new int[2];
        class8.anIntArray160 = new int [2];
	class8.anIntArray156[0] = 933;
	class8.anIntArray160[0] = 26706;
	class8.anIntArray156[1] = 935;
	class8.anIntArray160[1] = 24648;
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
	class8.aString170 = "Ivandis Blade";
	class8.aByteArray178 = "Show no mercy, led anger rule your fury.".getBytes();
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
			class8.aByteArray178 = "Black Crystal shield".getBytes();
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

if(i == 12365)
		        {
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear"; //t
class8.anIntArray156 = new int[2];
class8.anIntArray160 = new int[2];
class8.anIntArray156[0] = 22416;  //original color
class8.anIntArray160[0] = 11200; //changed color
class8.anIntArray156[1] = 22424;  //original color
class8.anIntArray160[1] = 11200; //changed color
class8.anInt174 = 2745;
class8.anInt181 = 1030;
class8.anInt190 = 548;
class8.anInt198 = 0;
class8.anInt204 = 0;
class8.anInt169 = 0;
class8.anInt194 = -4;
class8.anInt165 = 314;
class8.anInt200 = 477;
class8.anInt188 = -1;
class8.anInt164 = -1;
class8.anInt175 = -1;
class8.anInt197 = -1;
	class8.aString170 = "Yellow d'hide body";
	class8.aByteArray178 = "really a yellow d'hide body".getBytes();
}
if(i == 9091){
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
	class8.aString170 = "Black Party Hat";
	class8.aByteArray178 = "A nice hat from a cracker.".getBytes();
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
            class8.aString170 = "Unknown Color Whip";
            class8.aByteArray178 = "a Unknown Color Whip".getBytes();
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
            class8.aByteArray178 = "a Red Whip".getBytes();
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
            class8.aByteArray178 = "a Blue Whip".getBytes();
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
            class8.aString170 = "Barrows Whip";
            class8.aByteArray178 = "a Barrows Whip".getBytes();
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
            class8.aByteArray178 = "a Gold Whip".getBytes();
        }
if(i == 10454)
        {
            class8.aStringArray189 = new String[5];
            class8.aStringArray189[1] = "Wear"; 
            class8.anIntArray156 = new int[1];
        class8.anIntArray160 = new int[1];
class8.anIntArray156[0] = 528;  
class8.anIntArray160[0] = 5959;       
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
            class8.aString170 = "Lava Whip";
            class8.aByteArray178 = "a lava Whip".getBytes();
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
            class8.aByteArray178 = "a White Whip".getBytes();
        }

if(i == 17099)
        {
            class8.aStringArray189 = new String[5];
            class8.aStringArray189[1] = "Wear"; 
            class8.anIntArray156 = new int[1];
        class8.anIntArray160 = new int[1];
class8.anIntArray156[0] = 926;  
class8.anIntArray160[0] = 924;       
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
            class8.aString170 = "Dragon Whip";
            class8.aByteArray178 = "It Is a Dragon Whip if ur rich its for u!".getBytes();
        }


 if(i == 8000){ //black phat 
 class8.aStringArray189 = new String[5];
 class8.aStringArray189[1] = "Wear";
 class8.anIntArray156 = new int[1];
 class8.anIntArray160 = new int [1];
 class8.anIntArray156[0] = 926;
 class8.anIntArray160[0] = 0;		
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
 class8.aString170 = "Black Party Hat";
 class8.aByteArray178 = "An Black Party Hat.".getBytes();
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

if(i == 10119)
        {
            class8.aStringArray189 = new String[5];
            class8.aStringArray189[1] = "Wear"; 
            class8.anIntArray156 = new int[1];
        class8.anIntArray160 = new int[1];
class8.anIntArray156[0] = 528;  
class8.anIntArray160[0] = 22464;       
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
            class8.aString170 = "Green Whip";
            class8.aByteArray178 = "a Green Whip".getBytes();
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

		if(i == 9094)    //WH1P!!!!
		        {
		            class8.aStringArray189 = new String[5];
		            class8.aStringArray189[1] = "Wear"; //this is the new option to equipt, can change to w/e u want
		            class8.anIntArray156 = new int[1];
		        class8.anIntArray160 = new int[1];
		class8.anIntArray156[0] = 528;  //original color
		class8.anIntArray160[0] = 36133; //changed color
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
		            class8.aString170 = "Rune Whip";
		            class8.aByteArray178 = "A whip made of Rune".getBytes();
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
if(i == 15114) { //ivandis chain
		class8.aStringArray189 = new String[5];
		class8.aStringArray189[1] = "Wear";
		class8.anIntArray156 = new int[5];
		class8.anIntArray160 = new int [5];
		class8.anIntArray156[0] = 914;
		class8.anIntArray160[0] = 26706;
		class8.anIntArray156[1] = 918;
		class8.anIntArray160[1] = 24648;
		class8.anIntArray156[2] = 922;
		class8.anIntArray160[2] = 24640;
		class8.anIntArray156[3] = 391;
		class8.anIntArray160[3] = 26706;
		class8.anIntArray156[4] = 917;
		class8.anIntArray160[4] = 24648;
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
	class8.aString170 = "Ivandis Chainmail";
	class8.aByteArray178 = "Its a Ivandis Chain".getBytes();
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
class8.aByteArray178 = "A draconic Ahrims Hood.".getBytes();
}
if(i == 11112){
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[1];
class8.anIntArray160 = new int[1];
class8.anIntArray156[0] = 8741;
class8.anIntArray160[0] = 41;
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
class8.aString170 = "Grey Hood";
class8.aByteArray178 = "A Ahrims Hood coloured Grey".getBytes();
}
/*if(i == 19898){
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[1];
class8.anIntArray160 = new int[1];
class8.anIntArray156[0] = 4769;
class8.anIntArray160[0] = 4769;
class8.anInt174 = 4769;
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
class8.aString170 = "hood";
class8.aByteArray178 = "A Hood used by Monks.".getBytes();
}*/


if(i == 15112) { //ivandis legs
		class8.aStringArray189 = new String[5];
		class8.aStringArray189[1] = "Wear";
		class8.anIntArray156 = new int[2];
		class8.anIntArray160 = new int [2];
		class8.anIntArray156[0] = 926;
		class8.anIntArray160[0] = 26706;
		class8.anIntArray156[1] = 912;
		class8.anIntArray160[1] = 26667;
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
	class8.aString170 = "Ivandis Platelegs";
	class8.aByteArray178 = "Stride through enemies territory in a heart beat.".getBytes();
}
        if(i == 10106)
        {
            class8.aStringArray189 = new String[5];
            class8.aStringArray189[1] = "Wear";
            class8.anIntArray156 = new int[1];
            class8.anIntArray160 = new int[1];
            class8.anIntArray156[0] = 528;
            class8.anIntArray160[0] = 7114;
            class8.anInt174 = 5412;
            class8.anInt181 = 840;
            class8.anInt190 = 280;
            class8.anInt198 = 0;
            class8.anInt204 = 0;
            class8.anInt169 = -2;
            class8.anInt194 = 56;
            class8.anInt165 = 5409;
            class8.anInt200 = 5409;
            class8.anInt188 = -1;
            class8.anInt164 = -1;
            class8.anInt175 = -1;
            class8.anInt197 = -1;
            class8.aString170 = "Gold Whip";
            class8.aByteArray178 = "a Gold Whip".getBytes();
        }
        if(i == 9118)
        {
            class8.aStringArray189 = new String[5];
            class8.aStringArray189[1] = "Wear";
            class8.anIntArray156 = new int[1];
            class8.anIntArray160 = new int[1];
            class8.anIntArray156[0] = 926;
            class8.anIntArray160[0] = 11200;
            class8.anInt174 = 2438;
            class8.anInt181 = 730;
            class8.anInt190 = 516;
            class8.anInt198 = 0;
            class8.anInt204 = 0;
            class8.anInt169 = 0;
            class8.anInt194 = -10;
            class8.anInt165 = 3188;
            class8.anInt200 = 3192;
            class8.anInt188 = -1;
            class8.anInt164 = -1;
            class8.anInt175 = 1755;
            class8.anInt197 = 3187;
            class8.aString170 = "Yellow h'ween Mask";
            class8.aByteArray178 = "Aaaarrrghhh... I'm a monster.".getBytes();
        }
       
        if(i == 1835)
        {
            class8.aStringArray189 = new String[5];
            class8.aStringArray189[1] = "Wear";
            class8.anIntArray156 = new int[2];
            class8.anIntArray160 = new int[2];
            class8.anIntArray156[0] = 61;
            class8.anIntArray160[0] = 0;
            class8.anIntArray156[1] = 61;
            class8.anIntArray160[1] = 1000;
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
            class8.aString170 = "2008 H'ween boots";
            class8.aByteArray178 = "The 2008 Halloween event boots.".getBytes();
        }
if(i == 15084){
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[4];
class8.anIntArray160 = new int[4];
class8.anIntArray156[0] = 61;
class8.anIntArray160[0] = 950;
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
class8.aString170 = "Owners Boots";
class8.aByteArray178 = "Owners boots".getBytes();
}
 if(i == 15085){ //owners kite
              class8.aStringArray189 = new String[5];
              class8.aStringArray189[1] = "Wear";
              class8.anIntArray156 = new int[3];
              class8.anIntArray160 = new int [3];
              class8.anIntArray156[0] = 61;
              class8.anIntArray160[0] = 0;		
              class8.anIntArray156[1] = 57;
              class8.anIntArray160[1] = 0; 
              class8.anIntArray156[2] = 7054;
              class8.anIntArray160[2] = 950; 
  	class8.anInt174 = 2339;
	class8.anInt181 = 1560;
	class8.anInt190 = 344;
	class8.anInt198 = 1104;
	class8.anInt204 = 0;
        class8.anInt169 = -6;
        class8.anInt194 = -14;
        class8.anInt165 = 486;
	class8.anInt200 = 486;
	class8.anInt175 = -1;
	class8.anInt197 = -1;
              class8.aString170 = "Owners Kiteshield";
              class8.aByteArray178 = "It's an owners kiteshield.".getBytes();
              }
    if(i == 15086) {
    NEO("Owners Crown", "Made by Jukk", "Wear");
    class8.anIntArray156 = new int[2];
    class8.anIntArray160 = new int [2];
    NewColor(945, 950, 0);
    NewColor(8128, 0, 1);
    Zoom(500, 90, 0, 0, 0, false);
    Models(8774, 8774, 8774);
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
class8.aString170 = "Attack cape";
class8.aByteArray178 = "Its A attack cape".getBytes();
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
class8.aString170 = "Attack cape(t)";
class8.aByteArray178 = "Its A attack cape(t)".getBytes();
}

/*if(i == 6570){
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
            class8.aString170 = "Fire cape";
            class8.aByteArray178 = "A cape of fire.".getBytes();
        }*/

if(i == 6570){
            class8.aStringArray189 = new String[5];
            class8.aStringArray189[1] = "Wear";
            class8.anIntArray156 = new int[1];
            class8.anIntArray160 = new int[1];
			class8.anIntArray156[0] = 0;
			class8.anIntArray160[0] = 0;
			class8.anInt174 = 9631;//item look
			class8.anInt181 = 2140;
			class8.anInt190 = 400;
			//class8.anInt198 = 948;
			class8.anInt198 = 0;
			class8.anInt204 = 0;
			class8.anInt169 = 3;
			class8.anInt194 = 6;
			class8.anInt197 = -1;
            class8.aString170 = "Fire cape";
            class8.aByteArray178 = "A cape of fire.".getBytes();
        }

		        if(i == 15334){
				            class8.aStringArray189 = new String[5];
				            class8.aStringArray189[1] = "Wield";
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
				            class8.aByteArray178 = "A great weapon, used by the best warriors.".getBytes();
        }


if(i == 15350){
	class8.aStringArray189 = new String[5];
	class8.aStringArray189[1] = "Wear";
	class8.anIntArray156 = new int[1];
	class8.anIntArray160 = new int[1];
	class8.anIntArray156[0] = 0;
	class8.anIntArray160[0] = 0;
	class8.anInt174 = 13422;//item look
	class8.anInt181 = 650;//Model Zoom
	class8.anInt190 = 96;//Model Rotation
	class8.anInt198 = 0;
	class8.anInt204 = 0;
	class8.anInt169 = 0;
	class8.anInt194 = 0;
	class8.anInt165 = 13423;
	class8.anInt200 = 13423;
	class8.anInt175 = 28259;
	class8.anInt197 = 28260;
	class8.aString170 = "Bandos boots";
	class8.aByteArray178 = "A nice set of Bandos boots, worn once by the god Bandos.".getBytes();
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
		            class8.aString170 = "Saradomin godsword";
		            class8.aByteArray178 = "A saradomin godsword, once used by saradomin himself.".getBytes();
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
		class8.aString170 = "Armadyl godsword";//Name of the new item
		class8.aByteArray178 = "A heavy sword".getBytes();//examin info
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
		class8.aByteArray178 = "A heavy sword".getBytes();//examin info
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
class8.aString170 = "Attack hood";
class8.aByteArray178 = "Its A attack hood".getBytes();
}
		if(i == 9102) //Replace with the id you want
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
		class8.aString170 = "Amulet of strength(t)";//Name of the new item
		class8.aByteArray178 = "An amulet that gives of a strong feeling of strength.".getBytes();//examin info
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
class8.aString170 = "Strength cape";
class8.aByteArray178 = "Its A Strength cape".getBytes();
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
class8.aString170 = "Strength cape(t)";
class8.aByteArray178 = "Its A Strenght cape(t)".getBytes();
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
class8.aString170 = "Strength hood";
class8.aByteArray178 = "Its A Strength hood".getBytes();
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
class8.aString170 = "Defence cape";
class8.aByteArray178 = "Its A Defence cape".getBytes();
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
class8.aString170 = "Defence cape(t)";
class8.aByteArray178 = "Its A Defence cape(t)".getBytes();
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
class8.aString170 = "Defence hood";
class8.aByteArray178 = "Its A Defence hood".getBytes();
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
class8.aString170 = "Ranging cape";
class8.aByteArray178 = "Its A Ranging cape".getBytes();
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
class8.aString170 = "Ranging cape(t)";
class8.aByteArray178 = "Its A Ranging cape(t)".getBytes();
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
class8.aString170 = "Range hood";
class8.aByteArray178 = "A Range hood".getBytes();
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
class8.aString170 = "Prayer cape";
class8.aByteArray178 = "Its A Prayer cape".getBytes();
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
class8.aString170 = "Prayer cape(t)";
class8.aByteArray178 = "Its A Prayer cape(t)".getBytes();
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
class8.aString170 = "Prayer hood";
class8.aByteArray178 = "A Prayer hood".getBytes();
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
class8.aString170 = "Magic cape";
class8.aByteArray178 = "Its A magic cape".getBytes();
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
class8.aString170 = "Magic cape(t)";
class8.aByteArray178 = "Its A magic cape(t)".getBytes();
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
class8.aString170 = "Magic hood";
class8.aByteArray178 = "A Magic hood".getBytes();
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
class8.aString170 = "Firemaking cape";
class8.aByteArray178 = "Its A firemaking cape(t)".getBytes();
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
class8.aString170 = "Firemaking cape(t)";
class8.aByteArray178 = "Its A firemaking cape(t)".getBytes();
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
class8.aString170 = "Firemaking hood";
class8.aByteArray178 = "Its A FireMaking hood".getBytes();
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
class8.aString170 = "Runecrafting cape";
class8.aByteArray178 = "Its a Runecrafting cape.".getBytes();
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
class8.aString170 = "Runecrafting cape(t)";
class8.aByteArray178 = "Its a Runecrafting cape(t).".getBytes();
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
class8.aString170 = "Runecrafting hood";
class8.aByteArray178 = "Its A Runecrafting hood".getBytes();
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
class8.aString170 = "Hitpoints cape";
class8.aByteArray178 = "Its A Hitpoint cape".getBytes();
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
class8.aString170 = "Hitpoints cape(t)";
class8.aByteArray178 = "Its A Hitpoint cape(t)".getBytes();
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
class8.aString170 = "Hitpoints hood";
class8.aByteArray178 = "Its A Hitpoint hood".getBytes();
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
class8.aString170 = "Agility cape";
class8.aByteArray178 = "Its A Agility cape".getBytes();
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
class8.aString170 = "Agility cape(t)";
class8.aByteArray178 = "Its A Agility cape(t)".getBytes();
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
class8.aString170 = "Agility hood";
class8.aByteArray178 = "Its A Agility hood".getBytes();
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
class8.aString170 = "Herblore cape";
class8.aByteArray178 = "Its A Herblore cape".getBytes();
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
class8.aString170 = "Herblore cape(t)";
class8.aByteArray178 = "Its A Herblore cape(t)".getBytes();
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
class8.aString170 = "Herblore hood";
class8.aByteArray178 = "Its A Herblore hood".getBytes();
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
class8.aString170 = "Thieving cape";
class8.aByteArray178 = "Its A thieving cape".getBytes();
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
class8.aString170 = "Thieving cape(t)";
class8.aByteArray178 = "Its A Thieving cape(t)".getBytes();
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
class8.aString170 = "Thieving hood";
class8.aByteArray178 = "Its A Thieving hood".getBytes();
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
class8.aString170 = "Crafting cape";
class8.aByteArray178 = "Its A Crafting cape".getBytes();
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
class8.aString170 = "Crafting cape(t)";
class8.aByteArray178 = "Its A Crafting cape(t)".getBytes();
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
class8.aString170 = "Crafting hood";
class8.aByteArray178 = "Its A Crafting hood".getBytes();
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
class8.aString170 = "Fletching cape(t)";
class8.aByteArray178 = "Its A Fletching cape(t)".getBytes();
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
class8.aString170 = "Fletching cape";
class8.aByteArray178 = "Its A Fletching cape".getBytes();
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
class8.aString170 = "Fletching hood";
class8.aByteArray178 = "Its A Fletching hood".getBytes();
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
class8.aString170 = "Slayer cape";
class8.aByteArray178 = "Its A slayer cape".getBytes();
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
class8.aString170 = "Slayer cape";
class8.aByteArray178 = "Its A slayer cape".getBytes();
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
class8.aString170 = "Slayer hood";
class8.aByteArray178 = "Its A Slayer hood".getBytes();
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
class8.aString170 = "Mining cape";
class8.aByteArray178 = "Its A mining cape".getBytes();
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
class8.aString170 = "Mining cape(t)";
class8.aByteArray178 = "Its A mining cape(t)".getBytes();
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
class8.aString170 = "Mining hood";
class8.aByteArray178 = "Its A Mining hood".getBytes();
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
class8.aString170 = "Smithing cape";
class8.aByteArray178 = "Its A smithing cape".getBytes();
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
class8.aString170 = "Smithing cape(t)";
class8.aByteArray178 = "Its A smithing cape(t)".getBytes();
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
class8.aString170 = "Smithing hood";
class8.aByteArray178 = "Its A Smithing hood".getBytes();
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
class8.aString170 = "Fishing cape";
class8.aByteArray178 = "Its A fishing cape".getBytes();
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
class8.aString170 = "Fishing cape(t)";
class8.aByteArray178 = "Its A Fishing cape(t)".getBytes();
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
class8.aString170 = "Fishing hood";
class8.aByteArray178 = "Its A Fishing hood".getBytes();
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
class8.aString170 = "Cooking cape";
class8.aByteArray178 = "Its A Cooking cape".getBytes();
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
class8.aString170 = "Cooking cape(t)";
class8.aByteArray178 = "Its A Cooking cape(t)".getBytes();
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
class8.aString170 = "Cooking hood";
class8.aByteArray178 = "Its A Cooking hood".getBytes();
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
class8.aString170 = "Woodcut. cape";
class8.aByteArray178 = "Its A woodcutting cape".getBytes();
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
class8.aString170 = "Woodcut. cape(t)";
class8.aByteArray178 = "Its A woodcutting cape".getBytes();
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
class8.aString170 = "Woodcut. hood";
class8.aByteArray178 = "Its A woodcutting hood".getBytes();
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
class8.aString170 = "Farming cape";
class8.aByteArray178 = "Its A Farming cape".getBytes();
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
class8.aString170 = "Farming cape(t)";
class8.aByteArray178 = "Its A Farming cape(t)".getBytes();
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
class8.aString170 = "Farming hood";
class8.aByteArray178 = "Its A Farming hood".getBytes();
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
class8.aString170 = "Quest cape";
class8.aByteArray178 = "Its A quest cape".getBytes();
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
class8.aString170 = "Quest hood";
class8.aByteArray178 = "Its A Quest hood".getBytes();
}


if(i == 14115) {
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[4];
class8.anIntArray160 = new int[4];
class8.anIntArray156[0] = 57280;
class8.anIntArray156[1] = 54503;
class8.anIntArray156[2] = 54183;
class8.anIntArray156[3] = 11200;
class8.anIntArray160[0] = 6340;
class8.anIntArray160[1] = 6338;
class8.anIntArray160[2] = 6338;
class8.anIntArray160[3] = 6340;
class8.anInt190 = 0;//ModelRotation1
class8.anInt198 = 0;//modelrotate2 side ways increase to move right in circle
class8.anInt169 = 0;// modeloffset1 increase to move to the right
class8.anInt194 = 0;//modeloffset2 increase to move up
class8.anInt174 = 4156; //Inv & Ground
class8.anInt181 = 2000; //Zoom
class8.anInt190 = 572;
class8.anInt198 = 0;
class8.anInt204 = 0;
class8.anInt169 = 0;
class8.anInt194 = 1;
class8.anInt165 = 4179; //Male
class8.anInt200 = 7179; //Female
class8.anInt175 = -1;
class8.anInt197 = -1;
class8.anInt199 = -1;//noteable
class8.aString170 = "Const. cape";
class8.aByteArray178 = "Its A Constructioncape".getBytes();
}
if(i == 14116) {
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
class8.anInt190 = 0;//ModelRotation1
class8.anInt198 = 0;//modelrotate2 side ways increase to move right in circle
class8.anInt169 = 0;// modeloffset1 increase to move to the right
class8.anInt194 = 0;//modeloffset2 increase to move up
class8.anInt174 = 4156; //Inv & Ground
class8.anInt181 = 2000; //Zoom
class8.anInt190 = 572;
class8.anInt198 = 0;
class8.anInt204 = 0;
class8.anInt169 = 0;
class8.anInt194 = 1;
class8.anInt165 = 4179; //Male
class8.anInt200 = 7179; //Female
class8.anInt175 = -1;
class8.anInt197 = -1;
class8.anInt199 = -1;//noteable
class8.aString170 = "Const. cape(t)";
class8.aByteArray178 = "Its A Constructioncape".getBytes();
}
if(i == 14117)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anIntArray156 = new int[3];
class8.anIntArray160 = new int[3];
class8.anIntArray156[0] = 960;
class8.anIntArray156[1] = 22464;
class8.anIntArray156[2] = 43968;
class8.anIntArray160[0] = 6340;//Back
class8.anIntArray160[1] = 6340;//front Line
class8.anIntArray160[2] = 6340;//Inside
class8.anInt174 = 4203; //Model ID
class8.aBoolean176 = false;//Stackable
class8.anInt181 = 720; //zoom increase will make it smaller
class8.anInt190 = 0; //model rotate up+down increase to move doen away from you
class8.anInt198 = 0; //model rotate side ways increase to move right in circle
class8.anInt169 = 0; // model offset increase to move to the right
class8.anInt194 = 0; //model offset increase to move up
class8.anInt165 = 4202;//male wearing
class8.anInt200 = 4202;//female wearing
class8.aString170 = "Const. hood";
class8.aByteArray178 = "Its A Quest hood".getBytes();
}

if(i == 6570)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anInt174 = 9631; //Model ID

class8.aBoolean176 = false;//Stackable
}









if(i == 6570)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anInt174 = 9631; //Model ID

class8.aBoolean176 = false;//Stackable
}
if(i == 13601)  // change this if you need to "item number"
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wield";//New option
class8.anIntArray156 = new int[1];
class8.anIntArray160 = new int[1];
class8.anIntArray156[0] = 28; // colors
class8.anIntArray156[0] = 74; // colors
class8.anIntArray160[0] = 38676; // colors
class8.anIntArray160[0] = 38313; // colors
class8.anInt174 = 4671; //Model ID
class8.anInt181 = 490;//Model Zoom
class8.anInt190 = 344;//Model Rotation
class8.anInt198 = 192;//Unknown
//class8.anInt204 = 138;//Unknown
class8.anInt204 = 0;//Unknown
class8.anInt199 = -1;//noteable
class8.anInt169 = 1;//Unknown
class8.anInt194 = 20;//Unknown
class8.anInt165 = 4672;//male wearing
class8.anInt200 = 4672;//female wearing
class8.anInt175 = 56;//Unknown
class8.anInt197 = 116;//Unknown
class8.anInt164 = -1;//Female arms/sleeves
class8.anInt188 = -1;//male arms/sleeves
class8.aBoolean176 = false;//Stackable
class8.aString170 = "Rune defender";//Name of the new item
class8.aByteArray178 = "A defensive weapon.".getBytes();//examin info
        }

              if(i == 13595)  // change this if you need to "item number"
		        {
		            class8.aStringArray189 = new String[5];
		            class8.aStringArray189[1] = "Wield";
		            class8.anIntArray156 = new int[1];
		            class8.anIntArray160 = new int[1];
		class8.anIntArray156[0] = 28; // colors
		class8.anIntArray156[0] = 74; // colors
		class8.anIntArray160[0] = 5652; // colors
		class8.anIntArray160[0] = 5652; // colors
		class8.anInt174 = 4671; //Model ID - 15335 -These are the models I over wrote for it
			class8.anInt181 = 490;
			class8.anInt190 = 344;
			class8.anInt198 = 192;
//class8.anInt204 = 138;//Unknown
class8.anInt204 = 0;//Unknown
		        class8.anInt169 = 1;
		        class8.anInt194 = 20;
		class8.anInt165 = 4672;//male wearing 15413
		class8.anInt200 = 4672;//female wearing 15413
		class8.anInt175 = 56;
		class8.anInt197 = 116;
		            class8.aString170 = "Bronze defender";
		            class8.aByteArray178 = "A defensive weapon.".getBytes();
        }
  if(i == 13596)
        {
            class8.aStringArray189 = new String[5];
            class8.aStringArray189[1] = "Wield";
            class8.anIntArray156 = new int[1];
            class8.anIntArray160 = new int[1];
            class8.anIntArray156[0] = 28;
            class8.anIntArray156[0] = 74;
            class8.anIntArray160[0] = 33;
            class8.anIntArray160[0] = 33;
            class8.anInt174 = 4671;
            class8.anInt181 = 490;
            class8.anInt190 = 344;
            class8.anInt198 = 192;
//class8.anInt204 = 138;//Unknown
class8.anInt204 = 0;//Unknown
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
            class8.aString170 = "Iron defender";
            class8.aByteArray178 = "A defensive weapon.".getBytes();
        }

  if(i == 13597)
        {
            class8.aStringArray189 = new String[5];
            class8.aStringArray189[1] = "Wield";
            class8.anIntArray156 = new int[1];
            class8.anIntArray160 = new int[1];
            class8.anIntArray156[0] = 28;
            class8.anIntArray156[0] = 74;
            class8.anIntArray160[0] = 2130;
            class8.anIntArray160[0] = 2130;
            class8.anInt174 = 4671;
            class8.anInt181 = 490;
            class8.anInt190 = 344;
            class8.anInt198 = 192;
//class8.anInt204 = 138;//Unknown
class8.anInt204 = 0;//Unknown
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
            class8.aString170 = "Steel defender";
            class8.aByteArray178 = "A defensive weapon.".getBytes();
        }

  if(i == 13598)
        {
            class8.aStringArray189 = new String[5];
            class8.aStringArray189[1] = "Wield";
            class8.anIntArray156 = new int[1];
            class8.anIntArray160 = new int[1];
            class8.anIntArray156[0] = 28;
            class8.anIntArray156[0] = 74;
            class8.anIntArray160[0] = 0;
            class8.anIntArray160[0] = 0;
            class8.anInt174 = 4671;
            class8.anInt181 = 490;
            class8.anInt190 = 344;
            class8.anInt198 = 192;
//class8.anInt204 = 138;//Unknown
class8.anInt204 = 0;//Unknown
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
            class8.aString170 = "Black defender";
            class8.aByteArray178 = "A defensive weapon.".getBytes();
        }

  if(i == 13599)
        {
            class8.aStringArray189 = new String[5];
            class8.aStringArray189[1] = "Wield";
            class8.anIntArray156 = new int[1];
            class8.anIntArray160 = new int[1];
            class8.anIntArray156[0] = 28;
            class8.anIntArray156[0] = 74;
            class8.anIntArray160[0] = 43297;
            class8.anIntArray160[0] = 43297;
            class8.anInt174 = 4671;
            class8.anInt181 = 490;
            class8.anInt190 = 344;
            class8.anInt198 = 192;
//class8.anInt204 = 138;//Unknown
class8.anInt204 = 0;//Unknown
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
            class8.aString170 = "Mithril defender";
            class8.aByteArray178 = "A defensive weapon.".getBytes();
        }

  if(i == 13600)
        {
            class8.aStringArray189 = new String[5];
            class8.aStringArray189[1] = "Wield";
            class8.anIntArray156 = new int[1];
            class8.anIntArray160 = new int[1];
            class8.anIntArray156[0] = 28;
            class8.anIntArray156[0] = 74;
            class8.anIntArray160[0] = 21662;
            class8.anIntArray160[0] = 21662;
            class8.anInt174 = 4671;
            class8.anInt181 = 490;
            class8.anInt190 = 344;
            class8.anInt198 = 192;
//class8.anInt204 = 138;//Unknown
class8.anInt204 = 0;//Unknown
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
            class8.aString170 = "Adamantite defender";
            class8.aByteArray178 = "A defensive weapon.".getBytes();
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
class8.aString170 = "Fighter torso";//Name of the new item
class8.aByteArray178 = "Its A fighter Torso".getBytes();//examin info
        }
if(i == 15156)  // change this if you need to "item number"
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wield";//New option
class8.anInt174 = 4676; //Model ID
class8.anInt181 = 2128;//Model Zoom
class8.anInt190 = 591;//Model Rotation
class8.anInt198 = 1034;//model rotate side ways increase to move right in circle
class8.anInt204 = 2006;//Unknown
class8.anInt199 = -1;//noteable
class8.anInt169 = 0;// model offset increase to move to the right
class8.anInt194 = 0;//model offset increase to move up
class8.anInt165 = 4677;//male wearing
class8.anInt200 = 4677;//female wearing
class8.anInt175 = -1;//Unknown
class8.aBoolean176 = false;//Stackable//Unknown
class8.anInt164 = -1;//Female arms/sleeves
class8.anInt188 = -1;//male arms/sleeves
class8.aBoolean176 = false;//Stackable
class8.aString170 = "Dark bow";//Name of the new item
class8.aByteArray178 = "A very powergull now.".getBytes();//examin info
        }
/*if(i == 15352)  // change this if you need to "item number"
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wield";//New option
class8.anInt174 = 4678; //Model ID
class8.anInt181 = 595;
class8.anInt190 = 169;
class8.anInt198 = 0;//model rotate side ways increase to move right in circle
class8.anInt204 = 0;//Unknown
class8.anInt199 = -1;//noteable
class8.anInt169 = 0;// model offset increase to move to the right
class8.anInt194 = 0;//model offset increase to move up
class8.anInt165 = 4679;//male wearing
class8.anInt200 = 4680;//female wearing
class8.anInt175 = -1;//Unknown
class8.anInt197 = 116;//Unknown
class8.anInt164 = -1;//Female arms/sleeves
class8.anInt188 = -1;//male arms/sleeves
class8.aBoolean176 = false;//Stackable
class8.aString170 = "Dragon boots";//Name of the new item
class8.aByteArray178 = "A nice pair of boots".getBytes();//examin info
        }*/

if(i == 15352)  // change this if you need to "item number"
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wield";//New option
class8.anInt174 = 4678; //Model ID
class8.anInt181 = 595;
class8.anInt190 = 96;
class8.anInt198 = 0;//model rotate side ways increase to move right in circle
class8.anInt204 = 0;//Unknown
class8.anInt199 = -1;//noteable
class8.anInt169 = 0;// model offset increase to move to the right
class8.anInt194 = 0;//model offset increase to move up
class8.anInt165 = 4679;//male wearing
class8.anInt200 = 4680;//female wearing
class8.anInt175 = -1;//Unknown
class8.anInt197 = 116;//Unknown
class8.anInt164 = -1;//Female arms/sleeves
class8.anInt188 = -1;//male arms/sleeves
class8.aBoolean176 = false;//Stackable
class8.aString170 = "Dragon boots";//Name of the new item
class8.aByteArray178 = "A nice pair of boots".getBytes();//examin info
        }

if(i == 15348)  // change this if you need to "item number"
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wield";//New option
class8.anInt174 = 4681; //Model ID
class8.anInt181 = 984;
class8.anInt190 = 501;
class8.anInt198 = 6;//model rotate side ways increase to move right in circle
class8.anInt199 = -1;//noteable
class8.anInt169 = 1;// model offset increase to move to the right
class8.anInt194 = 4;//model offset increase to move up
class8.anInt165 = 4682;//male wearing
class8.anInt200 = 4683;//female wearing
class8.anInt164 = 4685;//Female arms/sleeves
class8.anInt188 = 4684;//male arms/sleeves
class8.anInt175 = -1;//Unknown
class8.aBoolean176 = false;//Stackable
class8.aString170 = "Bandos chestplate";//Name of the new item
class8.aByteArray178 = "Its A Bandos Chestplate".getBytes();//examin info
        }
if(i == 15349)  // change this if you need to "item number"
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";//New option
class8.anInt174 = 4686; //Model ID
class8.anInt181 = 854;
class8.anInt190 = 540;
class8.anInt198 = 2039;//model rotate side ways increase to move right in circle
class8.anInt204 = 0;//Unknown
class8.anInt199 = -1;//noteable
class8.anInt169 = 0;// model offset increase to move to the right
class8.anInt194 = 3;//model offset increase to move up
class8.anInt165 = 4687;//male wearing
class8.anInt200 = 4688;//female wearing
class8.anInt175 = -1;//Unknown
class8.aBoolean176 = false;//Stackable
class8.anInt164 = -1;//Female arms/sleeves
class8.anInt188 = -1;//male arms/sleeves
class8.aBoolean176 = false;//Stackable
class8.aString170 = "Bandos tassets";//Name of the new item
class8.aByteArray178 = "Its A Bandos Tassets".getBytes();//examin info
        }
if(i == 15345) //id
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";
class8.anInt174 = 10094;//28139
class8.anInt181 = 789;
class8.anInt190 = 66;
class8.anInt198 = 372;
class8.anInt204 = 144;
class8.anInt169 = 9;
class8.anInt194 = -4;
class8.anInt165 = 10095;//27747
class8.anInt200 = 10096;//27757
class8.anInt188 = -1;
class8.anInt164 = -1;
class8.anInt175 = -1;
class8.anInt197 = -1;
class8.aString170 = "Armadyl helm";
class8.aByteArray178 = "Its an Armadyl Helm".getBytes();
}
if(i == 15346)  // change this if you need to "item number"
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wield";//New option
class8.anInt174 = 4689; //Model ID
class8.anInt181 = 900;//Model Zoom
class8.anInt190 = 540;//Model Rotation
class8.anInt198 = 2039;//model rotate side ways increase to move right in circle
class8.anInt199 = 0;//noteable
class8.anInt169 = 0;// model offset increase to move to the right
class8.anInt194 = 0;//model offset increase to move up
class8.anInt165 = 4690;//male wearing
class8.anInt200 = 4691;//female wearing
class8.anInt175 = -1;//Unknown
class8.aBoolean176 = false;//Stackable
class8.anInt164 = 4693;//Female arms/sleeves
class8.anInt188 = 4692;//male arms/sleeves
class8.aBoolean176 = false;//Stackable
class8.aString170 = "Armadyl chestplate";//Name of the new item
class8.aByteArray178 = "Its A Armadyl Chestplate".getBytes();//examin info
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
class8.anInt200 = 4695;//female wearing
class8.anInt175 = -1;//Unknown
class8.aBoolean176 = false;//Stackable
class8.anInt164 = -1;//Female arms/sleeves
class8.anInt188 = -1;//male arms/sleeves
class8.aBoolean176 = false;//Stackable
class8.aString170 = "Armadyl plateskirt";//Name of the new item
class8.aByteArray178 = "Its A Armadyl plateskirt".getBytes();//examin info
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
class8.aString170 = "Helm of neitiznot";//Name of the new item
class8.aByteArray178 = "Its A Helm of neitiznot".getBytes();//examin info
      }
if(i == 15186)
        {
            class8.aStringArray189 = new String[5];
            class8.aStringArray189[1] = "Wear";
            class8.anIntArray156 = new int[1];
            class8.anIntArray160 = new int[1];
            class8.anIntArray156[0] = -22390;
            class8.anIntArray160[0] = 8;
            class8.anInt174 = 10067;
            class8.anInt181 = 2022;
            class8.anInt190 = 540;
            class8.anInt198 = 123;
            class8.anInt175 = -1;
            class8.anInt169 = -1;
            class8.anInt194 = 0;
            class8.anInt197 = -1;
            class8.anInt204 = 0;
            class8.anInt165 = 6;
            class8.anInt200 = 6;
            class8.anInt164 = -1;//Female arms/sleeves
            class8.anInt188 = -1;//male arms/sleeves
            class8.aBoolean176 = false;
            class8.aString170 = "Dragonfire shield";
            class8.aByteArray178 = "A dragonfire shield.".getBytes();
        }
if(i == 14514)  // change this if you need to "item number"
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";//New option
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
class8.aByteArray178 = "A ancient Kiteshield".getBytes();//examin info
        }
if(i == 15195)
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";//New option
class8.anInt174 = 7205; //Model ID
class8.anInt181 = 801; //zoom increase will make it smaller
class8.anInt190 = 126; //model rotate up+down increase to move doen away from you
class8.anInt198 = 129; //model rotate side ways increase to move right in circle
class8.anInt169 = -1; // model offset increase to move to the right
class8.anInt194 = 1; //model offset increase to move up
class8.anInt204 = 28;
class8.anInt165 = 7204;//male wearing
class8.anInt200 = 7203;//female wearing
class8.anInt175 = -1;//Unknown
class8.aBoolean176 = false;//Stackable
class8.anInt164 = -1;//Female arms/sleeves
class8.anInt188 = -1;//male arms/sleeves
class8.aBoolean176 = false;//Stackable
class8.aString170 = "Dragon full helm";//Name of the new item
class8.aByteArray178 = "Its A Dragon full helm".getBytes();//examin info
      }
if(i == 14512)  // change this if you need to "item number"
{
class8.aStringArray189 = new String[5];
class8.aStringArray189[1] = "Wear";//New option
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
class8.aString170 = "3rd age platebody";//Name of the new item
class8.aByteArray178 = "An ancient platebody".getBytes();//examin info
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
class8.aString170 = "3rd Age plateLegs";//Name of the new item
class8.aByteArray178 = "A ancient Platelegs".getBytes();//examin info
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
class8.aByteArray178 = "A ancient Full helm".getBytes();//examin info
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
class8.aByteArray178 = "A ancient Kiteshield".getBytes();//examin info
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
class8.aByteArray178 = "A ancient Robe Top".getBytes();//examin info
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
class8.aByteArray178 = "A ancient Robe".getBytes();//examin info
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
class8.aByteArray178 = "A ancient hat".getBytes();//examin info
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
class8.aByteArray178 = "A ancient Range Top".getBytes();//examin info
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
class8.aByteArray178 = "A ancient Range Legs".getBytes();//examin info
        }
if(i == 14504)  // change this if you need to "item number"
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
class8.aByteArray178 = "A ancient Range coif".getBytes();//examin info
        }
if(i == 14505)  // change this if you need to "item number"
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
class8.aString170 = "3rd Age vambraces";//Name of the new item
class8.aByteArray178 = "A ancient vambraces".getBytes();//examin info
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
