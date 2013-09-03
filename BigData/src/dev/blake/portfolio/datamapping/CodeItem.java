package dev.blake.portfolio.datamapping;

public class CodeItem {
    
    final protected int code;
    final protected String title;

    final protected String keyWords;
    protected String mapping;
    
    public static void main(String[] arg){
        CodeItem sic4Row = new CodeItem(100, "booze", "liquor, beer", "1022, 1044");
        System.out.println(sic4Row.toString());
    }
    

    public CodeItem(int codeX, String titleX, String keyWordX, String mappingX){
        code=codeX;
        title= titleX;
        keyWords = keyWordX;

       if(mappingX==null){
        //null out mapping since we are refilling the map fields
            mapping=new String();
           
       }
       else
           mapping = mappingX;
        
    }
    
    

    
   public void addMapping(String newVal){
       StringBuffer sb = new StringBuffer();
       if(mapping.length()<1){
           sb.append(newVal);
           mapping = sb.toString();
       }else{
           sb.append(mapping + "," + newVal);
           mapping = sb.toString();
       }
   }
   
   public boolean hasMapping(){
       if(mapping.length()>1)
           return true;
       else
           return false;
   }
   

    
  int getCode(){
        return code;
    }
   String getTitle(){
       return title;
   }
     String getMapping(){
        return mapping;
    }
     String[] getMappingArray(){
         return mapping.split(",");
     }
    String getKeyWords(){
        return keyWords;
    }
    String[] getKeyWordsArray(){
        return keyWords.split(",");
    }
    String[] getTitleArray(){
        return title.split("\\s*(,|\\s)\\s*");
    }
    
    @Override
    public String toString() {
        return "CodeItem [code=" + code + ", title=" + title + ", keyWords="
                + keyWords + ", mapping=" + mapping + "]";
    }
        

}
