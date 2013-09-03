package dev.blake.portfolio.strings;

import java.util.Stack;

public class StringReverse {

	static String str =  "banana";
	public static void main(String[] args) {
		System.out.println(str);
		System.out.println(reverseWithStack(str));
		System.out.println(reverseCharArr(str));
		System.out.println(reverseCharAt(str));
	}
	//uses stack to do work O(2N)
	static String reverseWithStack(String s){
		Stack pile = new Stack();
		for(char c: s.toCharArray()){
			pile.push(c);
		}
		StringBuffer sb = new StringBuffer();
		while(!pile.empty()){
			sb.append(pile.pop());
		}
		return sb.toString();
	}
	//uses char array to iterate reverse O(N) ?
	static String reverseCharArr(String s){
		char[] letters = s.toCharArray();
		StringBuffer sb = new StringBuffer();
		for(int i = letters.length-1; i >= 0; i-- ){
			sb.append(letters[i]);
		}
		return sb.toString();
	}
	//maybe most efficient O(N)
	static String reverseCharAt(String s){
		StringBuffer sb = new StringBuffer();
		for(int i = s.length()-1; i >= 0; i-- ){
			sb.append(s.charAt(i));
		}
		return sb.toString();
	}
	
	

}
