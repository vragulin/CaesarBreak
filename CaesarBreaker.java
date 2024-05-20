/**
 * Write a description of CaesarBreaker here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class CaesarBreaker {
    static String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public int[] countLetters(String input) {
        int [] counts = new int[26];
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
    public String decrypt(String encrypted){
        CaesarCipher cc = new CaesarCipher();
        int [] freqs = countLetters(encrypted);
        int maxDex = maxIndex(freqs);
        int dkey = maxDex - 4;
        if (maxDex < 4) {
            dkey += 26;
        }
        return cc.encrypt(encrypted, 26-dkey);
    }
    public String halfOfString(String message, int start){
        String halfMessage = "";
        for(int i=start; i<message.length(); i+=2){
            halfMessage += message.charAt(i);
        }
        return halfMessage;
    }
    public int getKey(String e_message){
        int[] freqs = countLetters(e_message);
        int dkey = maxIndex(freqs) - 4; // 4 is the code of 'e'
        return dkey >= 0 ? dkey : dkey + 26;
    }
    
    public boolean isEven(int n){
        if ((n % 2) == 0) return true; else return false; 
    }
    public String decryptTwoKeys(String encrypted){
        CaesarCipher cc1 = new CaesarCipher();
        CaesarCipher cc2 = new CaesarCipher();
        
        String message1 = halfOfString(encrypted,0);
        String message2 = halfOfString(encrypted,1);
        StringBuilder theAnswer = new StringBuilder(encrypted);
        int key1 = getKey(message1);
        int key2 = getKey(message2);
        
        String d_message1=cc1.encrypt(message1,26-key1);
        String d_message2=cc2.encrypt(message2,26-key2);
        
        // build up the final answer
        for (int k =0; k<(message1.length());k++){
            theAnswer.setCharAt((2*k), d_message1.charAt(k) );
        }
        
        for (int k =0; k<(message2.length());k++){
            theAnswer.setCharAt((2*k) + 1, d_message2.charAt(k));
        }
    
        System.out.println(key1 + " " + key2 + " " + theAnswer.toString());
        
        return theAnswer.toString();
    }
     public String encryptTwoKeys(String input, int key1, int key2){
         CaesarCipher cc = new CaesarCipher();
         String encrypt1 = cc.encrypt(input, key1);
         String encrypt2 = cc.encrypt(input, key2);
         StringBuilder encrypted= new StringBuilder(input);
         
        for (int i=0; i< input.length();i=i+1){
            if (isEven(i))
                encrypted.setCharAt(i, encrypt1.charAt(i));
                
            if (!isEven(i))encrypted.setCharAt(i, encrypt2.charAt(i));
        }
    return encrypted.toString();   
 }
    // Test methods
    public void test_countLetters(){
        FileResource resource = new FileResource("data/romeo.txt");
        //FileResource resource = new FileResource("data/likeit.txt");
        String message = resource.asString();
        
        int[] counts = countLetters(message);
        for(int i=0; i<counts.length; i++){
            System.out.println("Letter " + alphabet.charAt(i) + ": " + counts[i] + " times.");
        }
        System.out.println("Most common length is " + maxIndex(counts));
    }
    public void test_decrypt(){
        FileResource resource = new FileResource("data/smallHamlet.txt");
        //FileResource resource = new FileResource("data/wordsLotsOfEs.txt");
        String message = resource.asString();
        CaesarCipher cc = new CaesarCipher();
        String encrypted = cc.encrypt(message, 20);
        String decrypted = decrypt(encrypted);
        System.out.println(encrypted  + "   " + decrypted);
    }
    public void test_halfOfString(){
       FileResource resource = new FileResource("data/smallHamlet.txt");
       //FileResource resource = new FileResource("data/wordsLotsOfEs.txt");
       String message = resource.asString();
       System.out.println(message);
       System.out.println(halfOfString(message, 0));
       System.out.println(halfOfString(message, 1));        
    }
    public void test_getKey(){
        FileResource resource = new FileResource("data/wordsLotsOfEs.txt");
        String message = resource.asString();
        CaesarCipher cc = new CaesarCipher();
        String e_message = cc.encrypt(message, 5);
        System.out.println(getKey(e_message) + " is the key for: " + message + " to: " + e_message);
    }
    public void test_decryptTwoKeys(){
        FileResource resource = new FileResource("data/mysteryTwoKeysQuiz.txt");
        String message = resource.asString();
        //String message = "Aal uttx hm aal Qtct Fhljha pl Wbdl. Pvxvxlx!";
        String d_TwoKeyMessage = decryptTwoKeys(message);
        
        System.out.println(message);
        System.out.println(d_TwoKeyMessage);
    }
     public void test_encryptTwoKeys(){
         String input = "Hfs cpwewloj loks cd Hoto kyg Cyy.";
         int key1 = 12;
         int key2 = 2;
         System.out.println(input);
         String encrypted = encryptTwoKeys(input, key1, key2);
         System.out.println(encrypted);
         //System.out.println(decryptTwoKeys(encrypted));         
    }
}
