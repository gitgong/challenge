package dev.blake.portfolio.strings;

import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

public class PangramImproved { 
    //initialize class variables - load alphabet into set
    static Set<Character> alphabetSet = new TreeSet<Character>();
    static  {        
        char[] alphabet = {'a','b','c','d','e','e','f','g','h',
                'i','j','k','l','m','o','p','q','r',
                's','t','u','v','w','x','y','z'}; 
        for(int i=0;i<alphabet.length;i++){
            alphabetSet.add(alphabet[i]);
        }       
    }
  //load sentence into char array, filter
    // load sentence into set and compare to alphabet set
    static String getMissingLetters(String sentence){
        char[] letters = sentence.toLowerCase().toCharArray();
        Set<Character> sentenceSet = new TreeSet<Character>();
        //1 filter and load characters
        for(int i=0;i<letters.length;i++){
            if(String.valueOf(letters[i]).matches("[a-zA-Z]")){
                sentenceSet.add(letters[i]);
            }
        } 
        //2 idiom to find set difference
        Set<Character> missing = new TreeSet<Character>(alphabetSet);
        missing.removeAll(sentenceSet);
        //3 iterate missing chars to buffer
        StringBuffer buffer = new StringBuffer();
        for (Character c:  missing){
                buffer.append(c);
        }   
        return buffer.toString();
    } 
    //run program
    public static void main(String[] args) {
       String missing = getMissingLetters( "A quick brown fox jumps over the lazy dog");
       System.out.println("0 Missing: "+missing);    
       missing = getMissingLetters("A slow yellow fox crawls under the proactive dog");
       System.out.println("1 Missing: "+missing);
       missing = getMissingLetters("Lions, and tigers, and bears, oh my!");
       System.out.println("2 Missing: "+missing);
       missing = getMissingLetters("");
       System.out.println("3 Missing: "+missing);
    }
}
