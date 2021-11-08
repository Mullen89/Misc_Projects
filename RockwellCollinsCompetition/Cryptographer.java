package Competition;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Cryptographer {

	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		String[][] endResult = new String[26][50];
		HashMap<String, Integer> X_Col = new HashMap<String, Integer>();
		HashMap<String, Integer> Y_Row = new HashMap<String, Integer>();
		File file = new File("c:/users/eric/desktop/ComS_228/input.txt");
		Scanner scan = new Scanner(file);
		
		while (scan.hasNext()) {
			String tempInt = scan.next();
			String tempStr = scan.next();
			
			scan.next();
			double d1 = scan.nextDouble();

			
			scan.next();
			//store double
			double d2 = scan.nextDouble();
			
			scan.next();
			//store double
			double d3 = scan.nextDouble();
			
			scan.next();
			//store double
			double d4 = scan.nextDouble();
			
			
			if (tempStr.equals("X") || tempStr.equals("x")) {
				X_Col.put(tempInt, formula(d1, d2, d3, d4));
			}
			else if(tempStr.equals("Y") || tempStr.equals("y")) {
				Y_Row.put(tempInt, formula(d1, d2, d3, d4));
			}
		}

		scan.close();
		for (String ID : X_Col.keySet()) {
			for (String ID2 : Y_Row.keySet()) {
				if (ID2.equals(ID)) {
					endResult[Y_Row.get(ID2)][X_Col.get(ID)] = "@@@";
				}
			}
		}
		for (int i = 0; i < endResult.length; i++) {
			for (int j = 0; j < endResult[i].length; j++) {
				if (endResult[i][j] != "@@@") {
					endResult[i][j] = "   ";
				}
			}
		}
		for (int i = 0; i < endResult.length; i++) {
			for (int j = 0; j < endResult[i].length; j++) {
				System.out.print(endResult[i][j]);
			}
			System.out.println();
		}
	}
	public static int formula (double lat1, double lon1, double lat2, double lon2) {
		double pi = 3.141592653589793238846;
		double dLat1 = lat1 * (pi / 180);
		double dLat2 = lat2 * (pi / 180);
		double dLon1 = lon1 * (pi / 180);
		double dLon2 = lon2 * (pi / 180);
		
		double drLat = (dLat2 - dLat1);
		double drLon = (dLon2 - dLon1);
		
		Double a = (Math.pow(Math.sin(drLat/2), 2) + (Math.cos(dLat1)) * (Math.cos(dLat2)) * (Math.pow(Math.sin(drLon/2), 2)));
		Double c = 2 * (Math.atan2(Math.sqrt(a), Math.sqrt(1-a)));
		int result = (int) Math.round(6371 * c);
		
		return result;
	}
}
