
/**
 * Write a description of TestCaesarCipher2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;

public class TestCaesarCipher2 {
    public int[] countLetters(String input) {
        int [] counts = new int[26];
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for(int k=0; k < input.length(); k++){
            char ch = Character.toUpperCase(input.charAt(k));
            int idx = alphabet.indexOf(ch);
            if (idx != -1){
                counts[idx] += 1;
            }
        }
        return counts;
    }
    public int maxIndex(int[] vals) {
        int maxDex = 0;
        for(int k=0; k < vals.length; k++){
            if (vals[k] > vals[maxDex]){
                maxDex = k;
            }
        }
        return maxDex;
    }
    public String breakCaesarCipher(String encrypted){
        int [] freqs = countLetters(encrypted);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4;
        if (maxDex < 4) {
            dkey += 26;
        }
        CaesarCipher2 cc = new CaesarCipher2(26-dkey);
        return cc.encrypt(encrypted);        
    }
    // Test methods
    public void simpleTests() {
        int key = 18;
        //FileResource fr = new FileResource();
        //String message = fr.asString();
        String message = "At noon be in the conference room with your hat on for a surprise party. YELL LOUD!eeeeee";
        CaesarCipher2 cc = new CaesarCipher2(key);
        String encrypted = cc.encrypt(message);
        System.out.println("key is " + key + "\n" + encrypted);
        String decrypted = cc.decrypt(encrypted);
        System.out.println(decrypted);
        String decrypted2 = breakCaesarCipher(encrypted);
        System.out.println(decrypted2);
    }
}
