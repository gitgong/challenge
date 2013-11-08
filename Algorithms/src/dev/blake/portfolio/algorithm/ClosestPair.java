package dev.blake.portfolio.algorithm;

import java.util.Arrays;

//O(n2/2) exponential time /2
public class ClosestPair {
	public static void main(String[] arg){
		int[] pairs = {11,20, 42,7,17, 14, 3, 25,0,-1};
		findClosePair(pairs);
	}
	//makes all comparisons except self x2
	public static void findClosePair(int[] arrayIn){
		int min = 0;
		boolean start = true;
		System.out.println(Arrays.toString(arrayIn));
		int iterationCounter=0;
		for(int i=0;i<arrayIn.length; i++){
			for(int j = i+1; j < arrayIn.length; j++){
				if(i==j)
					continue; // don't compare same element
				int difference = Math.abs(arrayIn[i] - arrayIn[j]);
				if(start || difference < min ){
					start=false;
					min=difference;
				}
				iterationCounter++;
				System.out.println("pairs[i] "+arrayIn[i]+" - pairs[j] "+ arrayIn[j] +" = (min) "+min);
			}
		}
		System.out.println("printing minimum " + min);
		System.out.println("printing list count " + arrayIn.length);
		System.out.println("printing iterations " + iterationCounter);
	}
}
