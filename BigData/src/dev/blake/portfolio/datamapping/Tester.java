package dev.blake.portfolio.datamapping;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Tester {   
    static Logger log = Logger.getLogger("dev.blake.portfolio.datamapping");
    static String keyWords1 = " fruit trees,fruit tree, fruit";
    static String keyWords2 = "fruit trees, tree fruit, fruit tree, fruits,fruit";    
    static String title1 = "ores wheats grain";
    static String keyWords3 = "wheat,ore,ores,ore or ores,wheates,wheaties,wheatship wheat,we grow wheates, wheats,wheatabix,wheatship wheats,we grain grainola, wheatabix rows,rows wheats rows,rows wheates rows,grain,sunflowers,barleys,old graines farm, wheatbox, wheates, grains is wheats, good wheates grow,good graines";
    static List<CodeItem> naicsList = new ArrayList<CodeItem>();   
    static List<CodeItem> sic4List = new ArrayList<CodeItem>();    
     static {
         CodeItem rowKeyToKeyA = new CodeItem(100, " fruit", keyWords1, "1022, 1044");
         CodeItem rowKeyToKeyB  = new CodeItem(200, "fruits", keyWords2, "1062, 1047");        
         CodeItem rowTitleToKeyA = new CodeItem(300, title1, "wheat", "1092, 1093");
         CodeItem rowTitleToKeyB = new CodeItem(400, title1, keyWords3, "1099, 1093"); 
         naicsList.add(rowKeyToKeyA);
         sic4List.add(rowKeyToKeyB);        
         naicsList.add(rowTitleToKeyA);
         sic4List.add(rowTitleToKeyB);     
         log.setLevel(Level.INFO);
     }     
     public static void main(String[] arg){     
         DataMappingMain.mapKeyWordsToKeyWords(naicsList, sic4List);
         DataMappingMain.mapTitlesToKeyWords(naicsList, sic4List);
         int totalNaicsMappingToSic4Title = DataMappingMain.printTotalMappings(naicsList);
         int totalSic4MappingToNaicsTitle = DataMappingMain.printTotalMappings(sic4List);
         log.info("naics - " + naicsList.size() + " entries");
         log.info("sic_4 - " + sic4List.size() + " entries");
         System.out.println(" \nTotal NAICS>SIC_4 Matches: " + totalNaicsMappingToSic4Title);
         System.out.println(" \nTotal SIC_4>NAICS Matches: " + totalSic4MappingToNaicsTitle);
     }
}
