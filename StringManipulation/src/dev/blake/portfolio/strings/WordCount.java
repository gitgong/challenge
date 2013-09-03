package dev.blake.portfolio.strings;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;

public class WordCount {

	static String[] tokens = null;
	static Map<String, Integer> wordFrequency = new HashMap<String, Integer>();

	
	static void loadFile(String fileN){	
		//loads from class directory, not from c://java/dev/blake/portfolio...
		URL url = WordCount.class.getClass()
				.getResource("/dev/blake/portfolio/strings/resources/"+fileN);  
		try {
			File file = new File( url.toURI());
			tokens = getContents(file).split(" ");
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}	
	}

	static void countWords(){
		for (String word : tokens){	
			Integer wordCount = wordFrequency.get(word);
			wordFrequency.put(word, wordCount == null ? 1 : wordCount + 1);
		}	
	}
	
	static void printWordCounts(){
		for (Map.Entry<String, Integer> entry : wordFrequency.entrySet())
			System.out.println(entry);
	}
	
	public static String getContents(File aFile) {
		StringBuilder contents = new StringBuilder();
		BufferedReader input = null;	
		try {
			input =  new BufferedReader(new FileReader(aFile));
			String line = null;
			while (( line = input.readLine()) != null){
				contents.append(line);
			}
		}
		catch (IOException ex){
			ex.printStackTrace();
		}
		finally {
			try {
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return contents.toString();
	}
	
	
	public static void main(String[] args)
	{
		loadFile("file.txt");
		countWords();
		 printWordCounts();
	}
}