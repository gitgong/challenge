package dev.blake.portfolio.datamapping;


import java.util.Date;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataMappingMain
{
    static Logger log = Logger.getLogger("dev.blake.portfolio.datamapping");
    
    static List<CodeItem> naicsList = null;
    static List<CodeItem> sic4List = null;

    public static void main(String[] args) throws Exception 
    {
        log.setLevel(Level.INFO);
            //load lists
        sic4List= DataLoader.getSic4List();
        naicsList= DataLoader.getNaicsList();
        
        
        long lStartTimeMappingNaics = new Date().getTime(); //start time
        System.out.println("\nNaics keyword mapping to Sic4 title ...");
        int countMappingNaics = mapTitlesToKeyWords(sic4List, naicsList);
        System.out.println("\nMatches Found. mapped " +countMappingNaics);
        long lEndTimeMappingNaics = new Date().getTime(); //end time
        long differenceMappingNaics= lEndTimeMappingNaics  - lStartTimeMappingNaics ; //check different
  
        long lStartTimeMappingSic4 = new Date().getTime(); //start time
        System.out.println("\nSic4 keyword mapping to naics title ...");
        int countMappingSic4=mapTitlesToKeyWords(naicsList, sic4List);
        System.out.println("\nMatches Found. mapped " +countMappingSic4);
        long lEndTimeMappingSic4 = new Date().getTime(); //end time
        long differenceMappingSic4= lEndTimeMappingSic4  - lStartTimeMappingSic4 ; //check different
      
        
        //map step 3
        //put sic4code in naics mapping if sic4 keyword matches naics keyword "exactly"
        //step 4
        //put naicscode in sic4 mapping if naics keyword matches sic4 keyword 
        //thus if keyword match, it will be reciprocal, can check once do both at same time!
     //   System.out.println("\nSic4 keywords to naics keywords...");
        mapKeyWordsToKeyWords(naicsList, sic4List);
        long lEndTimeKeyWordsTtl = new Date().getTime(); //end time
        long differenceKeyWordTtl= lEndTimeKeyWordsTtl  - lEndTimeMappingSic4 ; //check different
        
        
        //evaluating data/print
       // System.out.println("\nCalculating total mappings in Naics...");
        int totalNaicsMappingToSic4Title = printTotalMappings(naicsList);
       // System.out.println("\nCalculating total mappings in sic4..");
        int totalSic4MappingToNaicsTitle = printTotalMappings(sic4List);
        
        //metrics
        System.out.println("\n\nMetrics/mapping naics keyword->sic4 title : Elapsed milliseconds: " +
                differenceMappingSic4 + " / seconds: " + (differenceMappingSic4 /1000));
        System.out.println("Metrics/mapping sic4 keyword->naics title : Elapsed milliseconds: " +
                differenceMappingNaics+ " / seconds: " + (differenceMappingNaics /1000));
        
        System.out.println("Metrics/mapping keyword->keyword : Elapsed milliseconds: " +
                differenceKeyWordTtl+ " / seconds: " + (differenceKeyWordTtl /1000));
        
        long totalTime=differenceMappingSic4+differenceMappingNaics+differenceKeyWordTtl;
        System.out.println("Metrics/Total Program: Elapsed milliseconds: " +
                totalTime+ " / seconds: " + ((totalTime /1000))+ " / min: " + ((totalTime /1000)/60));
        
        //finally show summary
        System.out.println("naics - " + naicsList.size() + " entries");
        System.out.println("sic_4 - " + sic4List.size() + " entries");
        System.out.println(" \nTotal NAICS>SIC_4 Matches: " + totalNaicsMappingToSic4Title);
        System.out.println(" \nTotal SIC_4>NAICS Matches: " + totalSic4MappingToNaicsTitle);
        

    }
    
    
    protected static void mapKeyWordsToKeyWords(List<CodeItem> keyWordListA, List<CodeItem> keyWordListB){
      
            int matchCounterKeyWordAToKeyWordB=0;
            //show output of Lists -- outer loop less words, inner loop more?
            for(int listACounter = 0; listACounter < keyWordListA.size(); listACounter++){
                long lStartTime = new Date().getTime(); //start time
                CodeItem keyWordAItem = (CodeItem) keyWordListA.get(listACounter);
                String keyWordA = keyWordAItem.getKeyWords();
               // System.out.println("\nKEYWORD SEARCH: mapping KeyWords from TableA item >> " + keyWordAItem.getCode());
                //breaks up title into separate words for matching each word
                String[] keyWordATokens = keyWordAItem.getKeyWordsArray();
                
                for(int keyWordATokenIndex =0;keyWordATokenIndex<keyWordATokens.length;keyWordATokenIndex++){
                    String keyWordAToken = keyWordATokens[keyWordATokenIndex];
                   // System.out.println(">>Searching List B keywords for List A KeyWord... <"+keyWordAToken+">");
                    for(int listBCounter = 0; listBCounter < keyWordListB.size(); listBCounter++){
                        CodeItem keyWordBItem = (CodeItem) keyWordListB.get(listBCounter);
                        String[] keyWordBTokens = keyWordBItem.getKeyWordsArray();
                        //System.out.println("before check keyWordkeywdtoken for match titletitletoken "+ titleWordTokenTitle+ " >> keyWord #"+(keyWordCounter +1)+":" + keyWordItem.getCode() + ": "+ Arrays.toString(keyWordTokens));
                        for(int keyWordBTokenIndex= 0; keyWordBTokenIndex < keyWordBTokens.length; keyWordBTokenIndex++){
                            String keyWordBToken = keyWordBTokens [keyWordBTokenIndex];
                            if (MatchingUtility.isMatchKeyWordToKeyWord(keyWordAToken, keyWordBToken)){
                                // System.out.println("Match ");
                                if(!keyWordBItem.getMapping().contains(String.valueOf(keyWordAItem.getCode()))){
                                     log.info("MATCH/MAPPING >> List A Code " + keyWordAItem.getCode() + " keyword " +
                                            keyWordATokens[keyWordATokenIndex] + " match to List B Code "+ keyWordBItem.getCode() 
                                            + " keyword " + keyWordBTokens[keyWordBTokenIndex]);
                                           
                                    //adding mapping of keywordA in list b
                                    keyWordBItem.addMapping(String.valueOf(keyWordAItem.getCode()));
                                    keyWordListB.set(listBCounter, keyWordBItem);
                                    //set mapping of keyword b in list a
                                    keyWordAItem.addMapping(String.valueOf(keyWordBItem.getCode()));
                                    keyWordListA.set(listACounter, keyWordAItem);
                                    
                                    matchCounterKeyWordAToKeyWordB++;
                                    log.info("Mapped: A code "+ keyWordAItem.getCode() + " to B Table " + keyWordBItem.getCode());
                                   log.info("Mapped: B code "+ keyWordBItem.getCode() + " to A Table " + keyWordAItem.getCode());
                                    //break means after match is found, other matches in row are redundant
                                    break; 
                                }else{
                                    //   System.out.println("MSG: Already Mapped: title "+ titleItem.getCode() + " to keyWord " + keyWordItem.getCode());
                                }
                            } 
                        }

                    }
                }
                long lEndTime = new Date().getTime(); //end time
                long differenceTitle= lEndTime - lStartTime; //check different
              //  System.out.println("Metrics/keyword-keyword "+ keyWordAItem.getCode()+ " Elapsed milliseconds: " + differenceTitle);
            }
        
    }

    protected static int mapTitlesToKeyWords(List<CodeItem> titleList, List<CodeItem> keyWordList){
        int matchCounterTitleToKeyWord=0;
        //show output of Lists -- outer loop less words, inner loop more?
        for(int titleCounter = 0; titleCounter < titleList.size(); titleCounter++){
            long lStartTimeTitle = new Date().getTime(); //start time
            CodeItem titleItem = (CodeItem) titleList.get(titleCounter);
            String title = titleItem.getTitle();
           // System.out.println("\n TITLE >> " + titleItem.getCode()  + " "+ title);
            //breaks up title into separate words for matching each word
            String[] titleTokens = titleItem.getTitleArray();
            for(int titleTokenIndex =0;titleTokenIndex<titleTokens.length;titleTokenIndex++){
                String titleWordToken = titleTokens[titleTokenIndex];
               // System.out.println(">>Searching for Title... <"+title+"> [subword = " + titleWordToken + "]");
                for(int keyWordCounter = 0; keyWordCounter < keyWordList.size(); keyWordCounter++){
                    CodeItem keyWordItem = (CodeItem) keyWordList.get(keyWordCounter);
                    String[] keyWordTokens = keyWordItem.getKeyWordsArray();
                    //System.out.println("before check keyWordkeywdtoken for match titletitletoken "+ titleWordTokenTitle+ " >> keyWord #"+(keyWordCounter +1)+":" + keyWordItem.getCode() + ": "+ Arrays.toString(keyWordTokens));
                    for(int keywordTokenIndex= 0; keywordTokenIndex < keyWordTokens.length; keywordTokenIndex++){
                        String keyWordToken = keyWordTokens[keywordTokenIndex];
                        if (MatchingUtility.isMatchTitleWordToKeyWordSmart(titleWordToken, keyWordToken)){
                            // System.out.println("Match ");
                            if(!keyWordItem.getMapping().contains(String.valueOf(titleItem.getCode()))){
                                log.info("MATCH/MAPPING >> title "+ titleItem.getCode() + "-"+
                                        titleTokens[titleTokenIndex] + " match on keyWord "+ keyWordItem.getCode() 
                                        + " keyword " + keyWordTokens[keywordTokenIndex]);
                                       
                                keyWordItem.addMapping(String.valueOf(titleItem.getCode()));
                                keyWordList.set(keyWordCounter, keyWordItem);
                                matchCounterTitleToKeyWord++;
                               log.info("Mapped: title "+ titleItem.getCode() + " to keyWord " + keyWordItem.getCode());
                                //break means after match is found, other matches in row are redundant
                                break; 
                            }else{
                                //   System.out.println("MSG: Already Mapped: title "+ titleItem.getCode() + " to keyWord " + keyWordItem.getCode());
                            }
                        } 
                    }

                }
            }
            long lEndTimeTitle = new Date().getTime(); //end time
            long differenceTitle= lEndTimeTitle - lStartTimeTitle; //check different
            //System.out.println("Metrics/Search/map title: "+title+": Elapsed milliseconds: " + differenceTitle);
        }
        return matchCounterTitleToKeyWord;
       
    } 

    protected static int printTotalMappings(List<CodeItem> codeItemList){
        int mappingCount=0;
        for(CodeItem codeRecord: codeItemList){
            if(codeRecord.hasMapping()){
                String[] codeMappingArr = codeRecord.getMappingArray();
                for(String mappedCode: codeMappingArr){
                    log.info("code: " + codeRecord.getCode() + " has mapping " + mappedCode);
                    mappingCount++;
                }
                log.info(">>Total mapping array: " + Arrays.toString(codeMappingArr));
            }
        }
        return mappingCount;
    }
}
