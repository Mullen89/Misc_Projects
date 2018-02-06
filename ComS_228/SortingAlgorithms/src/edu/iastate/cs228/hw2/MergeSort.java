package edu.iastate.cs228.hw2;

import java.util.Arrays;
import java.util.Comparator;

public class MergeSort extends SorterWithStatistics {
	
	//This method will be called by the base class sort() method to 
	// actually perform the sort. 
	@Override
	public void sortHelper(String[] words, Comparator<String> comp) 
		{
			if(words.length < 2){
				return;
			}
			int midIndex = words.length / 2;
			int leftSideLength = midIndex;
			int rightSideLength = words.length - midIndex;
			String[] leftArray = new String[leftSideLength];
			String[] rightArray = new String[rightSideLength];
			
			for(int i = 0; i < midIndex; i++){
				leftArray[i] = words[i];
			}
			for(int j = midIndex; j < words.length; j++){
				rightArray[j - midIndex] = words[j];
			}
			sortHelper(leftArray, comp);
			sortHelper(rightArray, comp);
			performMerge(leftArray, rightArray, words, comp);	
		}
		public static void performMerge(String[] left, String[] right, String[] words, Comparator<String> comp)
		{
			int leftSideLength = left.length;
			int rightSideLength = right.length;
			int x = 0, y = 0, z = 0;
			
			while(x < leftSideLength && y < rightSideLength){
				if(comp.compare(left[x], right[y]) < 0){
					words[z] = left[x];
					x++;
					z++;
				}else{
					words[z] = right[y];
					y++;
					z++;
				}
			}
			while(x < leftSideLength){
				words[z] = left[x];
				x++;
				z++;
			}
			while(y < rightSideLength){
				words[z] = right[y];
				y++;
				z++;
			}
		}

}
