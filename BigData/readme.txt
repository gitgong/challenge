Big Data

Readme

Challenge Description
_____________________

1) write a java program that creates two-way mapping between the mysql entries following this simple algorithm:

-- any keyword for a naics code matching any word on a sic_4 title ==> put in that sic_4 code into naics's mapping field
-- any keyword for a sic_4 code matching any word on a naics title ==> put in that naics code into sic_4's mapping field
-- any keyword for a naics code matching any keyword of a sic_4 ==> put in that sic_4 code into naics's mapping field
-- any keyword for a sic_4 code matching any keyword of a naics ==> put in that naics code into sic_4's mapping field
-- put multiple mapping entries as comma-separated (similar to keywords field)

2) make sure your program handles mysql db interface via jdbc

3) make sure your program is compilable and runnable from command-line (and from Eclipse)

4) displays the total number of mapping found on the screen/console like this:

Total NAICS>SIC_4 Matches: ....
Total SIC_4>NAICS Matches: ....


Solution Critique
________________

Dependency on database (mysql) and mysql jdbc driver (Connector J)

Databases are read into CodeItem objects, which are placed into respective lists. 
The lists are compared, in memory. This solution, though functional,
 is not optimal performance-wise as data increases in memory,
  and should be released. 
  
  Code gives example of basic JDBC, List, array, string-matching, 
  some Object oriented implementation. 



Instructions
______________


Readme for DataMapper application

1. ensure mysql/jdbc driver/lib installed, modify db settings in db.ini 
2. naics_codes and sic_4_codes tables are loaded in mysql database
* set database uri/login/pass in DataMappingProgram.java and recompile
2. install included java package
3. run DataMappingMain.java

