package dev.blake.portfolio.strings;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class StringDuplicates {

	public static void main(String[] args) {
		String s1 = "barbie is a tramp who knows where barbie goes";
		System.out.println("Iterator 1: " + containsDuplicatesLoop(s1.split(" ")));
		String s2 = "barbie is a tramp who knows where she goes";
		System.out.println("iterator 2 " + containsDuplicatesLoop(s2.split(" ")));
		System.out.println("setfltr 1: " + containsDuplicatesSetFilter(s1.split(" ")));
		System.out.println("setfltr 2: " + containsDuplicatesSetFilter(s2.split(" ")));
	}
	
	// 0(N)?
	static boolean containsDuplicatesSetFilter(String[] strings)
	{
		Set stringsUnique = new HashSet();
		stringsUnique.addAll(Arrays.asList(strings));
		if(stringsUnique.size()<strings.length){
			return true;
		}
		return false;
	}
	// O(N^2)
	static boolean containsDuplicatesLoop(String[] strings){
		for(int i = 0; i < strings.length; i++){
			for(int j = 0; j < strings.length; j++){
				if(i == j) {
					continue;
				}
				if(strings[i].equals(strings[j])){
					return true;
				}
			}
		}
		return false;
	}
}
