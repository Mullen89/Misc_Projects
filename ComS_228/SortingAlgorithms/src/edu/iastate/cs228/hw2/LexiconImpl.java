package edu.iastate.cs228.hw2;

import java.util.Arrays;
import java.util.Comparator;

public class LexiconImpl implements Lexicon, Comparator<String> {

    /***
     * Lookup table mapping characters in lexicographical order to
     * to their input order. This is public to support automated grading. 
     */
	public CharacterValue[] characterOrdering; 

    /***
     * Creates an array of CharacterValue from characterOrdering.  Sorts
     * it using Arrays.sort().
     * @param characterOrdering character order from configuration file
     */	
	public LexiconImpl(char[] characterOrdering) {
		//TODO:
		
	}


    /***
     * Compares two words based on the configuration
     * @param a first word
     * @param b second word
     * @return negative if a<b, 0 if equal, postive if a>b
     */
	@Override
	public int compare(String a, String b) {
		//TODO: write it and return something meaningful. 

	}
	
	/**
	 * Uses binary search to find the order of key.
	 * @param key
	 * @return ordering value for key or -1 if key is an invalid character.
	 */
	public int getCharacterOrdering(char key) {
		//TODO: write it and return something meaningful. 
		return -1; 

	}

	/**
	 * Searches characterOrdering for key via binary search.
	 * This is public only to facilitate automated grading. 
	 * @param characterOrdering the specified sort order
         * @param key the search term
	 * @return ordering value for key or -1 if key is an invalid character.
	 */
	public static class CharacterValue {
		//TODO: 
		 public int value;
		 public char character;
		 public CharacterValue(int value, char character) {
			 this.value = value;
			 this.character = character;
		 }
		 public boolean equals(Object o) {
		 //TODO: implement equals for CharacterValue.
			 
		 }
		 
		 public static class characterValueComparator implements Comparator<CharacterValue> {

			@Override
			public int compare(CharacterValue o1, CharacterValue o2) {
				return Character.compare(o1.character,  o2.character);
			}	 
		 }
	}
	
	/**
	 * Returns whether or not word is valid according to the alphabet
	 * known to this lexicon. 
	 * 
	 * @param word word to be checked.
	 *
	 * @return true if valid. false otherwise
	 */
	public boolean isValid(String word) {
		//TODO: write it and return something meaningful. 
		return false; 

	}
	
}
