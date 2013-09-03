package dev.blake.portfolio.datamapping;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import dev.blake.portfolio.strings.WordCount;



public class DataLoader
{
    
    //number of rows to load from table
    static int ROWLIMIT_NAICS = 10;
    static int ROWLIMIT_SIC4 = 10;
    //database info
    static String url = null;
    static String user = null;
    static String pass = null;
    static String driverClass = null;
    
	static Properties defaultProps = new Properties();
    
    public static List<CodeItem> getNaicsList() {
        return naicsList;
    }

    public static List<CodeItem> getSic4List() {
        return sic4List;
    }


    private static  List<CodeItem> naicsList =    null;
    private static  List<CodeItem> sic4List =   null;
    
    private static void loadNaicsTable(ResultSet rsNaics){
        try{
        while(rsNaics.next()){
            int code = rsNaics.getInt("code");
            String title = rsNaics.getString("title");
            String keyWords = rsNaics.getString("keywords");
            String map = rsNaics.getString("mapping");
            //stuffing the row object
            CodeItem naicsRow = new CodeItem(code, title, keyWords, map);
            naicsList.add(naicsRow);
        } 
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }   
    }
    
    private static void loadSic4Table(ResultSet rsSic_4){
        try{
            while(rsSic_4.next()){
                int code = rsSic_4.getInt("code");
                String keyWords = rsSic_4.getString("keywords");
                String title = rsSic_4.getString("title");
                String map = rsSic_4.getString("mapping");
                CodeItem sic4Row = new CodeItem(code, title, keyWords, map);
                sic4List.add(sic4Row);
             
             } 
        }catch(SQLException e){
            System.out.println(e.getMessage());
        }   
    }
    

    static { 
        
       naicsList =    new ArrayList<CodeItem>();
        sic4List =   new ArrayList<CodeItem>();
        
        Connection conn = null;
        Statement stmt = null;
        ResultSet rsNaics = null;
        ResultSet rsSic_4 = null;
  
        try{
            
        	loadProps();


            Class.forName(driverClass).newInstance();
            conn = DriverManager.getConnection(url, user, pass);
            if(conn != null){
                System.out.println("connected!");
            }
   
            stmt = conn.createStatement();
            
            long lStartTimeLoad = new Date().getTime(); //start time
       
            rsNaics = stmt.executeQuery("select * from naics_codes limit "+ ROWLIMIT_NAICS + ";");
             // rsNaics = stmt.executeQuery("select * from naics_codes ;");
        
            loadNaicsTable(rsNaics); 
         
            rsSic_4 = stmt.executeQuery("select * from sic_4_codes limit "+ ROWLIMIT_SIC4+ ";");
             //   rsSic_4 = stmt.executeQuery("select * from sic_4_codes ;");
            loadSic4Table(rsSic_4); 
   
            
            long lEndTimeLoad = new Date().getTime(); //end time
            long differenceLoad = lEndTimeLoad - lStartTimeLoad; //check different


        }catch (SQLException e){
            System.out.println(e.getMessage());
        }catch(ClassNotFoundException e){
            System.out.println(e.getMessage());
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        finally{
            if(rsNaics != null){
                try{
                    rsNaics.close();
                }catch(SQLException e){
                    //do nothing
                }
                rsNaics = null;
            }
            if(rsSic_4 != null){
                try{
                    rsSic_4.close();
                }catch(SQLException e){
                    //do nothing
                }
                rsSic_4 = null;
            }
            if(stmt != null){
                try{
                    stmt.close();
                }catch(SQLException e){
                    //do nothing
                }
                stmt = null;
            }
            if(conn != null){
                try{
                    conn.close();
                }catch(Exception e){
                    System.out.println(e.toString());
                }
                conn = null;
            }
        }
    }

    public static void loadProps(){
    	// create and load default properties

    	FileInputStream in=null;
    	File f  = null;
    		URL u = WordCount.class.getClass()
    				.getResource("/dev/blake/portfolio/datamapping/db.ini");  
    		try {
    			f = new File( u.toURI());
    			in = new FileInputStream(f);
    			defaultProps.load(in);
    		    //number of rows to load from table
    		    ROWLIMIT_NAICS =  Integer.valueOf((String) defaultProps.get("ROWLIMIT_NAICS"));
    		    ROWLIMIT_SIC4 =  Integer.valueOf((String) defaultProps.get("ROWLIMIT_SIC4"));
    		    //database info
    		    url = (String) defaultProps.get("url");
    		    user = (String) defaultProps.get("user");
    		    pass = (String) defaultProps.get("pass");
    		    driverClass = (String) defaultProps.get("driverClass");

    
    		} catch (URISyntaxException | IOException e) {
    			e.printStackTrace();
    		}	
   
    	try {
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    } 
    
    
}
