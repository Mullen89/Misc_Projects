package edu.iastate.cs228.hw2;

import java.util.Arrays;
import java.util.Comparator;

public class QuickSort extends SorterWithStatistics {

	//This method will be called by the base class sort() method to 
	// actually perform the sort. 
	
	@Override
	public void sortHelper(String[] words, Comparator<String> comp) {
		//TODO: implement QuickSort;
		int first = 0;
		int last = words.length - 1;
		
		quickSort(words, first, last, comp);
		
	}
	public void quickSort(String[] words, int first, int last, Comparator<String> comp) {
		
		if(first < last)
		{
			int p = partition(words, first, last, comp);

			quickSort(words, first, p-1, comp);
			quickSort(words, p+1, last, comp);
		}
	}

	public int partition(String[] words, int first, int last, Comparator<String> comp) {

		int p = first;
		for(int n = p+1; n <= last; n++)
			if(comp.compare(words[n], words[p]) <0)
			{
				swap(words, n, p+1);
				swap(words, p, p+1);
				p++;
			}
		return p;
	}

	private void swap(String[] words, int index1, int index2) {
		String temp = words[index1];
		words[index1] = words[index2];
		words[index2] = temp;
	}
}

