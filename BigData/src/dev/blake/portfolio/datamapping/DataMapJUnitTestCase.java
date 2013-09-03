package dev.blake.portfolio.datamapping;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class DataMapJUnitTestCase {
    
    static Logger log = Logger.getLogger("dev.blake.portfolio.datamapping");
    //matches A:B fruit trees, fruit, fruit tree (3)
    static String keyWordsA = " fruit trees,fruit tree, fruit, banana";
    //matches B:A fruit trees, fruit, fruit tree (3)
    static String keyWordsB = "fruit trees, tree fruit, fruit tree, fruits,fruit, orange"; 
    
    static String titleA = "ores wheats grain";
    static String keyWordsC = "wheat,ore,ores,ore or ores,wheates,wheaties,wheatship wheat,we grow wheates";
    static String keyWordsD = "wheat,ore,ores,ore or ores,wheats,wheatis,gore wheat,we gro wheats";
    static List<CodeItem> naicsList = new ArrayList<CodeItem>();   
    static List<CodeItem> sic4List = new ArrayList<CodeItem>();    
   



    @Before
    public void setUp() throws Exception {
      //keyword matches A:B fruit trees, fruit, fruit tree (3)
        //title to key matches: A:B (fruit)
        CodeItem rowKeyToKeyA = new CodeItem(100, " fruit", keyWordsA, "0000, 0000");
        CodeItem rowKeyToKeyB  = new CodeItem(200, "fruits", keyWordsB, "0000, 0000"); 
        
        CodeItem rowTitleToKeyA = new CodeItem(300, titleA, "wheat", "0000");
        CodeItem rowTitleToKeyB = new CodeItem(400, titleA, keyWordsC, null); 
        naicsList.add(rowKeyToKeyA);
        sic4List.add(rowKeyToKeyB);        
       // naicsList.add(rowTitleToKeyA);
       // sic4List.add(rowTitleToKeyB);     
        log.setLevel(Level.INFO);
    }

  

    @Test
    public void testKeyToKeyMapping() {
        DataMappingMain.mapKeyWordsToKeyWords(naicsList, sic4List);
        int totalNaicsMappingToSic4Title = DataMappingMain.printTotalMappings(naicsList);
        int totalSic4MappingToNaicsTitle = DataMappingMain.printTotalMappings(sic4List);
        if(totalNaicsMappingToSic4Title!=3)
        	 fail("Not yet implemented");
        else if(totalSic4MappingToNaicsTitle !=3)
        	 fail("Not yet implemented");
        else
        	assertTrue(true);
    }
    
    
    @Test
    public void testTitleToKeyMapping() {
        DataMappingMain.mapTitlesToKeyWords(naicsList, sic4List);
        int totalNaicsMappingToSic4Title = DataMappingMain.printTotalMappings(naicsList);
        int totalSic4MappingToNaicsTitle = DataMappingMain.printTotalMappings(sic4List);
        if(totalSic4MappingToNaicsTitle==0)
            fail("Not yet implemented");
        else
            assertTrue(true);
    }
    

}
