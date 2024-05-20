
/**
 * Write a description of TestCaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class TestCaesarCipherTwo {
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
    public String breakCaesarCipher(String input){
        CaesarCipher cc1 = new CaesarCipher();
        CaesarCipher cc2 = new CaesarCipher();
        
        String message1 = halfOfString(input,0);
        String message2 = halfOfString(input,1);
        int key1 = getKey(message1);
        int key2 = getKey(message2);
        CaesarCipherTwo cc = new CaesarCipherTwo(key1, key2);
        String theAnswer = cc.decrypt(input);
        System.out.println(key1 + " " + key2 + "\n" + theAnswer);        
        return theAnswer;        
    }
    public void simpleTests(){
        int k1 = 17;
        int k2 = 3;
       FileResource fr = new FileResource();
       String message = fr.asString();  
       CaesarCipherTwo cc = new CaesarCipherTwo(k1, k2);
       String encrypted = cc.encrypt(message);
       String decrypted = cc.decrypt(encrypted);       
       System.out.println("message: " + message + "\nencrypted: " + encrypted + "\ndecrypted: " + decrypted);
       System.out.println("broken: " + breakCaesarCipher(encrypted));
    }
}
