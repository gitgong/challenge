package dev.blake.portfolio.strings;

import java.util.Arrays;
import java.util.Comparator;

/**
    * Sorts strings in order by descending length, using comparator interface
 *      from longest to shortest words
 */
public class StringComparatorSort implements Comparator
{
    public static void main(String args[])   {
        StringComparatorSort p = new StringComparatorSort();
        System.out.println( Arrays.toString(p.parseString(parseMe)));
    }

    static String parseMe = "Please help us from being pecked to death by penguins";

    String[] parseString(String s){
        String[] p = s.split(" ");
        Arrays.sort(p, this);
        return p;
    }

    //once overridden, you can pass the main object (this) to the Arrays.sort() method
    @Override
    public int compare(Object o1, Object o2) {
        if(!(o1 instanceof String) || !(o2 instanceof String )){
            throw new IllegalArgumentException("Sorts strings only. Both arguments must be string type.");
        }
        int val;
        String s1= (String) o1;
        String s2= (String) o2;
        //if first word is longer than second word, then return -1
        if(s1.length() > s2.length()) {
            val = -1;
        }
        //if first word is shorter than second word, then return 1
        else if (s1.length() < s2.length()){
            val = 1;
        } else {
            val =0;
        }
        return val;
    }
}

