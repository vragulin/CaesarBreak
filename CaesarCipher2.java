
/**
 * Write a description of CeasarCipher2 here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import edu.duke.*;

public class CaesarCipher2 {
    
    private String alphabet;
    private String shiftedAlphabet;
    private int mainKey;
    
    public CaesarCipher2(int key){
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet = alphabet.substring(key)+ alphabet.substring(0,key);
        mainKey = key;
    }
    public String encrypt(String input) {
        //Make a StringBuilder with message (encrypted)
        StringBuilder encrypted = new StringBuilder(input);
        char newChar;
        for(int i = 0; i < encrypted.length(); i++) {
            //Look at the ith character of encrypted (call it currChar)
            char currChar = encrypted.charAt(i);
            //Find the index of currChar in the alphabet (call it idx)
            int idx = alphabet.indexOf(currChar);
            //If currChar is in the alphabet
            if(idx != -1){
                //Get the idxth character of shiftedAlphabet (newChar)
                newChar = shiftedAlphabet.charAt(idx);
                //Replace the ith character of encrypted with newChar
                encrypted.setCharAt(i, newChar);
            } else {
                idx = alphabet.indexOf(Character.toUpperCase(currChar));
                if (idx != -1){
                    newChar = Character.toLowerCase(shiftedAlphabet.charAt(idx));    
                    encrypted.setCharAt(i, newChar);
                }
            }
        }
        //Your answer is the String inside of encrypted
        return encrypted.toString();
    }
    public String decrypt(String input){
        CaesarCipher2 cc = new CaesarCipher2(26-mainKey);
        return cc.encrypt(input);
    }
}
