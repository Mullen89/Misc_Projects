package lab5;
import java.util.Scanner;

public class CheckPoint1 {
	
//	Write a static method that takes a string argument containing a person's full name as a parameter, 
//	and returns a string containing just their initials. The name may have many parts or just one, 
//	e.g., for the name "Cher" the method returns "C", for the name "Edna del Humboldt von der Schooch" 
//	the method returns "EdHvdS". Test your code.
	
	public static String getInitials(String full_name) {
		
		Scanner in = new Scanner (full_name);
		String initials = "";
		while (in.hasNext()) {
			char first_letter = in.next().charAt(0);
			initials += first_letter;		
		}
		return initials;
	}
	
//	Write a static method to find the index of the first vowel in a string (returning -1 if there are no vowels). 
//	Note an easy way to write the boolean condition "character ch is a vowel" is...
	
	public static int firstVowel(String s) {
		
		int index_of_vowel = 0;
		String vowels = "aeiouAEIOU";
		
		for (int i = 0; i < s.length(); i++) {
			if (vowels.contains("" + s.charAt(i))) {
				index_of_vowel = i;
				break;
			}
			else {
				index_of_vowel = -1;
			}
		}
		return index_of_vowel;
	}
	public static void main(String[] args) {
		
		String result = getInitials("Edna del Humboldt von der Schooch");
		System.out.println(result);
		int result2 = firstVowel("characters");
		System.out.println(result2);
	}
}
