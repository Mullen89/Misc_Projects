package lab8;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class LineNumberer
{
  public static void main(String[] args) throws FileNotFoundException
  {	  
	File file = new File("../project7/Deck.java");
    Scanner scanner = new Scanner(file);
    File outFile = new File("mydocumentlab8.txt");
    PrintWriter out = new PrintWriter(outFile);
    int lineCount = 1;

    while (scanner.hasNextLine())
    {
      String line = scanner.nextLine();
      out.print(lineCount + " ");
      out.println(line);
      lineCount += 1;
    }
    out.println(file.exists());          // true if the file exists
    out.println(file.getName());         // name of the file 
    out.println(file.getAbsolutePath()); // absolute path to the file
    out.println(file.length());          // size of the file
    
    System.out.println("Done");
    scanner.close();
    out.close();
  }
}