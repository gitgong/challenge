package intro;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;



public class HuffmanCode {

	static Map<Character,Integer> map = new HashMap<Character,Integer>();
	static Map<Character,String> letterTocodeMap = new HashMap<Character,String>();
	static Map<String,Character> codeToLetterMap = new HashMap<String,Character>();
	static boolean debug = false;

	public static void main(String args[]){

		//set up, count the characters and store in a map
		String input = "Applications of fundamental topics of information theory include lossless data compression lossy data compression and channel coding for Digital Subscriber Line The field is at the intersection of mathematics statistics computer science physics neurobiology and electrical engineering Its impact has been crucial to the success of the Voyager missions to deep space the invention of the compact disc the feasibility of mobile phones the development of the Internet the study of linguistics and of human perception the understanding of black holes and numerous other fields Important sub-fields of information theory are source coding channel coding algorithmic complexity theory algorithmic information theory information theoretic security and measures of information";	
		System.out.println("input: "+ input);
		HuffmanCode huff = new HuffmanCode();	
		huff.countAndLoadMap(input);
		System.out.println("Unsorted: "  + map.toString());

		//build the huffman tree to build codes for characters and store codes in a map
		BinaryTree tree = huff.loadCodeTree();
		huff.recurseTree(tree, new StringBuffer());	
		System.out.println("Done traversing tree.");
		System.out.println("letterTocodeMap: "  + letterTocodeMap.toString());	
		System.out.println("codeToLetterMap: "  + codeToLetterMap.toString());

		//translate input using huffman, encode from words into binary string...
		StringBuffer codes = new StringBuffer();
		char[] chars = input.toCharArray();
		for(int i =0; i < chars.length; i++){
			codes.append(letterTocodeMap.get(chars[i]));
		}	
		String codesString = codes.toString();
		System.out.println("Codes For Word: "  + codesString);		

		//decode and display the huffman encoded string using the map
		String decoded = huff.decode(codesString);
		System.out.println("Decoded String " + decoded );
		boolean pass = input.equals(decoded) ? true : false;
		System.out.println("Passed test? " + pass);		

		//results and analysis
		System.out.println("Analytics::");	
		System.out.println("Words InfoContent: " +  calculateInfoContent(input));

		System.out.println("Huffman code avg bits:");		
		System.out.println("codesString: " + codesString.length() + " bits");	
		System.out.println("words num: " +input.split("\\s+").length);	
		System.out.println("Avg Bits per word: " + (double) codesString.length()/ (double) input.split("\\s+").length);	

	}

	BinaryTree loadCodeTree(){
		Queue<BinaryTree> buildTreeQueue = new PriorityQueue<BinaryTree>();
		for(Map.Entry<Character, Integer> entry: map.entrySet()){
			Leaf tree = new Leaf();
			tree.letter = entry.getKey();
			tree.frequency = entry.getValue();
			buildTreeQueue.add(tree);
		}
		if(debug) System.out.println("loaded into queu " + buildTreeQueue.toString());		
		while(buildTreeQueue.size()>1){
			BinaryTree left = (BinaryTree) buildTreeQueue.poll();
			BinaryTree right = (BinaryTree) buildTreeQueue.poll();
			Node node = new Node(left, right);		
			int frequency=0;
			if(left != null && left.frequency!=null)
				frequency+=left.frequency;
			if(right != null && right.frequency!=null)
				frequency+=right.frequency;
			node.frequency=frequency;
			buildTreeQueue.offer(node);
		}
		if(debug)  System.out.println("processed trees in queue " + buildTreeQueue.toString());
		return buildTreeQueue.poll();
	}

	void recurseTree(BinaryTree tree, StringBuffer prefix){
		if(tree==null)return;
		if(tree.left != null){
			prefix.append('0');
			recurseTree(tree.left, prefix);
			prefix.deleteCharAt(prefix.length()-1);
		}
		if(tree instanceof Leaf){
			Leaf l = (Leaf) tree;
			if(debug)  System.out.println("Character: " + l.letter   );
			if(debug)  System.out.println(l.letter  + "\t" + l.frequency + "\t" + prefix);
			letterTocodeMap.put(l.letter, prefix.toString());
			codeToLetterMap.put(prefix.toString(), l.letter );
		}
		else if(tree instanceof Node){
			if(debug) System.out.println("Node: " + tree.frequency);
		}
		if(tree.right != null){
			prefix.append('1');
			recurseTree(tree.right, prefix);
			prefix.deleteCharAt(prefix.length()-1);
		}
	}

	static String decode(String codesString){
		StringBuffer decoded = new StringBuffer();
		while(codesString.length()>0){
			for (Map.Entry<String, Character> entry : codeToLetterMap.entrySet()){
				if(codesString.startsWith(entry.getKey())){
					if(debug) System.out.println(entry.getKey() + "/" + entry.getValue());
					codesString=codesString.substring(entry.getKey().length() );
					decoded.append(entry.getValue());
					if(debug) System.out.println("new string: " + codesString);
				}
			}
		}
		return decoded.toString();
	}

	static double calculateInfoContent(String input){
		String[] wordsArr = input.split("\\s+");
		int length = wordsArr.length;	   
		double infoContentSum = 0;
		Map<String, Integer> wordMap = getWordToFrequencyMap(wordsArr); 
		Set<String> wordSet = new HashSet<String>();
		wordSet.addAll(Arrays.asList(wordsArr)); //unique words
		for (String word: wordSet){
			double probability = (double) wordMap.get(word) / (double) length;
			double infoContent =   probability * ( logOfBase(2, probability )  ) ;
			System.out.println(word + ":\t" + wordMap.get(word) + "/" + length + "\t probability: " + probability + "\t word infocontent " + infoContent);
			infoContentSum += infoContent;
		}
		return -1 * (infoContentSum);
	}

	static Map<String, Integer> getWordToFrequencyMap(String[] input){
		Map<String, Integer> table = new HashMap<String, Integer>();
		for(int i = 0; i < input.length; i++ ){
			String s = input[i];
			if(table.containsKey(s)){
				table.put(s,  (table.get(s) + 1) );
			}else{
				table.put(s, 1) ;
			}
		}
		return table;
	}

	void countAndLoadMap(String input){
		for(int i = 0; i < input.length(); i++ ){
			Character c = input.charAt(i);
			if(map.containsKey(c)){
				map.put(c, (map.get(c) + 1) );
			}else{
				map.put(c, 1) ;
			}
		}
	}

	class BinaryTree implements Comparable<BinaryTree> {	
		BinaryTree left;
		BinaryTree right;
		Integer frequency;
		public int compareTo(BinaryTree tree) {
			if( frequency==tree.frequency)
				return 0;
			if (frequency > tree.frequency) {
				return 1;
			} else {
				return -1;
			} 
		}
	}
	class Leaf extends BinaryTree {		
		Character letter;
		public String toString(){
			return "Leaf:  " + letter + "=" + frequency;	
		}
	}
	class Node extends BinaryTree {	
		public Node(BinaryTree left, BinaryTree right){
			super.left= left;
			super.right=right;
		}
		public String toString(){
			return "Node: frequency =" + frequency;		
		}	
	}	


	public static double logOfBase(int base, double num) {
		return Math.log(num) / Math.log(base);
	}
}
