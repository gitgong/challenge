package dev.blake.portfolio.strings;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class Pangram {
    
    
    
    static Map<Character, Boolean> letterToIsInSentenceMap = new TreeMap<Character, Boolean>();
    static  {        
        char[] alphabet = {'a','b','c','d','e','e','f','g','h',
                'i','j','k','l','m','o','p','q','r',
                's','t','u','v','w','x','y','z'}; 
        for(int i=0;i<alphabet.length;i++){
            letterToIsInSentenceMap.put(alphabet[i], false);
        }       
    }
    
    /**
     * @param args
     */
    public static void main(String[] args) {
       String missing = getMissingLetters( "A quick brown fox jumps over the lazy dog");
       System.out.println("0 Missing: "+missing);
       resetMap();       
       missing = getMissingLetters("A slow yellow fox crawls under the proactive dog");
       System.out.println("1 Missing: "+missing);
       resetMap();
       missing = getMissingLetters("Lions, and tigers, and bears, oh my!");
       System.out.println("2 Missing: "+missing);
       resetMap();
       missing = getMissingLetters("");
       System.out.println("3 Missing: "+missing);
       

    }
    
    static void resetMap(){
        for (Map.Entry<Character, Boolean> e : letterToIsInSentenceMap.entrySet()){
            e.setValue(false);
        }      
    }
    
    
    static String getMissingLetters(String sentence){
        String s= "";
        
        char[] letters = sentence.toLowerCase().toCharArray();

        Arrays.sort(letters);
        for(int i=0; i < letters.length; i++){
            char letter = letters[i];
            if(String.valueOf(letter).matches("[a-zA-Z]")){
                letterToIsInSentenceMap.put(letter, true);
            }
        } 
        StringBuffer buffer = new StringBuffer();
        for (Map.Entry<Character, Boolean> e : letterToIsInSentenceMap.entrySet()){
            //System.out.println(e.getKey() + ": " + e.getValue());
            if(e.getValue()==false){
                buffer.append(e.getKey());
            }
        }   
        return buffer.toString();
    }

}
