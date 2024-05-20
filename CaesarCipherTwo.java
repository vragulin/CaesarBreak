
/**
 * Write a description of CaesarCipherTwo here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CaesarCipherTwo {
    private String alphabet;
    private String shiftedAlphabet1;
    private String shiftedAlphabet2;
    private int key1;
    private int key2;
    
    public CaesarCipherTwo(int k1, int k2){
        alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        shiftedAlphabet1 = alphabet.substring(key1)+ alphabet.substring(0,key1);
        shiftedAlphabet2 = alphabet.substring(key2)+ alphabet.substring(0,key2);
        key1 = k1;
        key2 = k2;
    }
    public boolean isEven(int n){
        if ((n % 2) == 0) return true; else return false; 
    }
     public String encrypt(String input){
         CaesarCipher2 cc1 = new CaesarCipher2(key1);
         CaesarCipher2 cc2 = new CaesarCipher2(key2);
         String encrypt1 = cc1.encrypt(input);
         String encrypt2 = cc2.encrypt(input);
         StringBuilder encrypted= new StringBuilder(input);
         
        for (int i=0; i< input.length();i=i+1){
            if (isEven(i))
                encrypted.setCharAt(i, encrypt1.charAt(i));
                
            if (!isEven(i))encrypted.setCharAt(i, encrypt2.charAt(i));
        }
    return encrypted.toString();   
 }
    public String decrypt(String input){
        CaesarCipherTwo cc = new CaesarCipherTwo(26-key1, 26-key2);
        return cc.encrypt(input);
    }
}
