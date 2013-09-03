package dev.blake.portfolio.datamapping;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MatchingUtility {
    

    static String[] HARDCODED_THROWOUT = {"and", "or"};
    
    static{
        Arrays.sort(HARDCODED_THROWOUT);
    }
    

    
    private static String stripPlurals(String in){
        if(in.endsWith("s") && !in .endsWith("es")){
            return in.substring(0,in .length()-1);
        }else{
            return in;
        } 
    }
    
    static boolean isMatchKeyWordToKeyWord(String keyWordAToken, String keyWordBToken){ 
        if(keyWordAToken==null || keyWordBToken == null){
            return false;
        }
        String keyWordAPrepared = keyWordAToken.toLowerCase().trim();
        String keyWordBPrepared = keyWordBToken.toLowerCase().trim();
        keyWordAPrepared= stripPlurals(keyWordAPrepared);
        keyWordBPrepared= stripPlurals(keyWordBPrepared);
        
        //exact match?
        if(keyWordAPrepared.equals(keyWordBPrepared)){
            return true;
        }else{
            return false;
        }
    }
  
    
    
    static boolean isMatchTitleWordToKeyWordSmart(String titleWordToken, String keyWordToken){
        
        if(titleWordToken==null || keyWordToken == null){
            return false;
        }
        String titlePrepared = titleWordToken.toLowerCase().trim();
        int foundBadWordIndex = Arrays.binarySearch(HARDCODED_THROWOUT , titlePrepared);
        if(foundBadWordIndex > -1){
            return false;
        }

        String fixedTitle = null;
        fixedTitle = stripPlurals(titlePrepared);
       
        //prepare keyword phrase
        String[] wordPreparedArr = keyWordToken.toLowerCase().trim().split(" ");
        String fixedWord = null;
        boolean found = false;
        for(int keyWordSubIndex =0; keyWordSubIndex < wordPreparedArr.length; keyWordSubIndex++){
            String wordPrepared= wordPreparedArr[keyWordSubIndex];
            fixedWord = stripPlurals(wordPrepared);
            foundBadWordIndex = Arrays.binarySearch(HARDCODED_THROWOUT, fixedWord);
            if(foundBadWordIndex > -1){
                continue;
            }
            Pattern p2 = Pattern.compile("\\b\\Q" +  fixedTitle+ "\\E\\b", Pattern.CASE_INSENSITIVE);
            Matcher m = p2.matcher(fixedWord);
            fixedWord = null;
            if (m.find()){
               //System.out.println("Match! Found title " + titleWordToken + " in keyword " + keyWordToken  );
               found = true;
               break;
            }  
        }
        return found;
    }
  
}
