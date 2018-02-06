package lab8;

import java.awt.Point;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import plotter.Polyline;

import plotter.Plotter;

public class CheckPoint2 {
	public static void main(String[] args) throws FileNotFoundException {
		Plotter plot = new Plotter();
		ArrayList<Polyline> polyList= new ArrayList<Polyline>();
		polyList = readFile("hello.txt");
		System.out.println(polyList);

		for(int i = 0; i < polyList.size(); i++) {
			System.out.println(polyList.get(i));
			plot.plot(polyList.get(i));
		}
		
		
	}
	

	private static ArrayList<Polyline> readFile(String filename) throws FileNotFoundException {
		File file = new File(filename);
		Scanner scan = new Scanner(file);
		ArrayList<Polyline> ourList= new ArrayList<Polyline>();
		
		while(scan.hasNextLine()) {
			Polyline temp = parseOneLine(scan.nextLine());
			if(temp != null) {
				ourList.add(temp);
			}
		}
		scan.close();
		return ourList;
	}
	

	public static Polyline parseOneLine(String line) {
		line = line.trim();
		Scanner temp = new Scanner(line);
		if(line.equals("")) {
			temp.close();
			return null;
		}
		char first = line.charAt(0);
		Polyline ourPoly = null;
		if(line.startsWith("#")) {
			temp.close();
			return null;
		}
		boolean isDigit = (first >= '0' && first <= '9');
		
		int width = 0;
		if(isDigit) {
			width = temp.nextInt();
			}
		String color = temp.next();
		
		if(isDigit) {
			ourPoly = new Polyline(color, width);
		}
		else {
			ourPoly = new Polyline(color);
		}
		while(temp.hasNextInt()){
			ourPoly.addPoint(new Point(temp.nextInt(), temp.nextInt()));
			
		}
		temp.close();
		return ourPoly;
	}
		
}