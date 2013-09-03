package dev.blake.portfolio.strings;

import java.util.*;

/**
 * Sorts a list of words into a map of lists keyed on an alphabetically sorted letters (chars)
 * each list contains all anagrams for that key
 * shows how to use a multimap
 */
public class AnagramMultimap {
     //data structures and constant min-grouping
    private static final int MIN_GROUP_SIZE = 2;
    private static List<String> words = new ArrayList<String>();
    private static Map<String, List<String>> m = new HashMap<String, List<String>>();
   //initialize list with words to be searched for anagrams
    static {
        words.add("bad");
        words.add("ass");
        words.add("dog");
        words.add("panels");
        words.add("canals");
        words.add("dab");
        words.add("gape");
        words.add("pale");
        words.add("leap");
        words.add("god");
        words.add("page");
        words.add("peal");
    }

    public static void main(String[] args) {
        // get list of strings
            Iterator<String> i = words.iterator();
        //loop through words
            while (i.hasNext()) {
                String word =  i.next();
                // word is resorted in alphabetical order
                String alpha = alphabetize(word);
                //we get the list of words for that key of resorted letters
                List<String> l = m.get(alpha);
                if (l == null)
                    //if the key is new, its a unique new set of letters, so a new list is created
                    m.put(alpha, l=new ArrayList<String>());
                // word is added to the list, whether already in the map, or new
                l.add(word);
            }
            printAnagrams(MIN_GROUP_SIZE);
    }

    static void printAnagrams(int groupSize){
        for (List<String> l : m.values())
            if (l.size() >= groupSize)
                System.out.println(l.size() + ": " + l);
    }

    static String alphabetize(String s) {
        char[] a = s.toCharArray();
        Arrays.sort(a);
        return new String(a);
    }
}
