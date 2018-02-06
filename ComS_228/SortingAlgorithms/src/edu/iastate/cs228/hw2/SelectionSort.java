package edu.iastate.cs228.hw2;

import java.util.Arrays;
import java.util.Comparator;

public class SelectionSort extends SorterWithStatistics {
	
	//This method will be called by the base class sort() method to 
	// actually perform the sort. 
	@Override
	public void sortHelper(String[] words, Comparator<String> comp) {
		
		for(int i = 0; i < words.length - 1; i++){
			int index = i;
			for(int j = i; j < words.length; j++){
				if(comp.compare(words[j], words[index]) < 0){
					index = j;
				}
			}
			String shortestString = words[index];
			words[index] = words[i];
			words[i] = shortestString;
		}
	}
}
