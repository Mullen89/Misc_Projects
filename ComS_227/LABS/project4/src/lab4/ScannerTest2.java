package lab4;
import java.util.Scanner;

public class ScannerTest2
{
  public static void main(String[] args)
  {
    // construct a scanner to read from the console
    Scanner tom = new Scanner(System.in);
    
    // print a "prompt" so the user knows why the program is waiting
    System.out.print("Enter three words separated by spaces: ");
    
    // parse the input to get the three items
    String first = tom.next();
    String second = tom.next();
    String third = tom.next();
    
    // print them out
    System.out.println(first);
    System.out.println(second);
    System.out.println(third);
  }
}