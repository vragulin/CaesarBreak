
/**
 * Write a description of WordLengths here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import edu.duke.*;
import org.apache.commons.csv.*;
import java.io.*;

public class WordLengths {

    public void countWordLengths(FileResource resource, int[] counts){
        for(String word : resource.words()){
            int adjLength = word.length();
            for(int k =0; k<word.length(); k++){
                if((k==0) || (k==(word.length()-1) )) {
                    if (!Character.isLetter(word.charAt(k))) adjLength--;
                }
            }
            counts[(adjLength >= counts.length) ? counts.length-1 : adjLength]++;
        }
    }
    
    public int indexOfMax(int[] values){
        int maxLength=0, indexOfMax=0;
        
        for (int k=0; k<values.length; k++){
            if (values[k]>maxLength){
                maxLength = values[k];
                indexOfMax = k;
            }
        }
        return indexOfMax;
    }
   
    //Test methods
    void test_countWordLengths0(){
        String[] plays = {"errors.txt"};
        int[] counts = new int[30];
        for(int k=0; k < plays.length; k++){
            FileResource resource = new FileResource("data/" + plays[k]);
            countWordLengths(resource, counts);
            System.out.println("done with " + plays[k]);
        }
        
        for (int k=0; k < counts.length; k++){
            System.out.println(k + " " + counts[k]);
        }
        
        //System.out.println("Index of Max count is " + indexOfMax(counts));
    }
    public void test_countWordLengths(){
        FileResource resource = new FileResource("data/manywords.txt");
        //FileResource resource = new FileResource("data/lotsOfWords.txt");
        //FileResource resource = new FileResource("data/smallHamlet.txt");       
        int[] counts = new int[30];
        countWordLengths(resource, counts);
        System.out.println("The End.");
        for (int k=0; k < counts.length; k++){
            System.out.println(k + " " + counts[k]);
        }
        System.out.println("Index of Max count is " + indexOfMax(counts));        
    }
}
