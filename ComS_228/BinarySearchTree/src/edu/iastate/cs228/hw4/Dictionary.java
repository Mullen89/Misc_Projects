package edu.iastate.cs228.hw4;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;

/**
 * @author Eric Mullen
 * 
 *         An application class
 */
public class Dictionary {
	public static void main(String[] args) {
		try {EntryTree<Character, String> tree = new EntryTree<Character, String>();
		File file = new File(args[0]);
		Scanner scan = new Scanner(file);
		
		Character[] keyList;
		String methodStr = "";
		String valueStr = "";
		String charStr = "";
		int lineCounter = 1;
		
		while(scan.hasNext()) {
			methodStr = scan.next();
			if(methodStr.equals("add")) {
				charStr = scan.next();
				keyList = new Character[charStr.length()];
				int i = 0;
				for (Character c : charStr.toCharArray()) {
					keyList[i] = c;
					i++;
				}
				valueStr = scan.next();	
				System.out.println("Command: " + methodStr + " " + charStr + " " + valueStr);
				System.out.println("Result from add: " + tree.add(keyList, valueStr));
				System.out.println();
			}
			else if(methodStr.equals("showTree")) {
				System.out.println("Command: " + methodStr);
				System.out.println("Result from showTree: ");
				tree.showTree();
				System.out.println();
			}
			else if(methodStr.equals("remove")) {
				charStr = scan.next();
				keyList = new Character[charStr.length()];
				int i = 0;
				for (Character c : charStr.toCharArray()) {
					keyList[i] = c;
					i++;
				}
				System.out.println("Command: " + methodStr + " " + charStr);
				System.out.println("Result from remove: " + tree.remove(keyList));
				System.out.println();
			}
			else if(methodStr.equals("prefix")) {
					charStr = scan.next();
					keyList = new Character[charStr.length()];
					int i = 0;
					for (Character c : charStr.toCharArray()) {
						keyList[i] = c;
						i++;
					}
					System.out.println("Command: " + methodStr + " " + charStr);
					Object[] list2 = tree.prefix(keyList);
					String result = "";
					for (int j = 0; j < list2.length; j++) {
						result += list2[j].toString();
					}
					System.out.println("Result from prefix: " + result);
					System.out.println();
			}
			else if(methodStr.equals("search")) {
				charStr = scan.next();
				keyList = new Character[charStr.length()];
				int i = 0;
				for (Character c : charStr.toCharArray()) {
					keyList[i] = c;
					i++;
				}
				System.out.println("Command: " + methodStr + " " + charStr);
				System.out.println("Result from search: " + tree.search(keyList));
				System.out.println();
			}
			else {
				System.out.println("Invalid command on line " + lineCounter);
				System.out.println();
				if(scan.hasNextLine()) {
					scan.nextLine();
				}
			}
			lineCounter++;
		}
		scan.close();
	}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

