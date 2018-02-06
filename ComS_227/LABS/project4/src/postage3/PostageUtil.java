package postage3;

import java.util.Scanner;

public class PostageUtil
    {
      /**
       * Returns the cost of postage for a letter of the given weight.
       * @param weight
       *   given weight in ounces
       * @return
       *   cost of postage for the weight
       */
	  public static void main(String[] args)
	  {

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter weight of postage: ");
        double weight = scanner.nextDouble();
        double cost = 0.47;
        
        if (weight > 1) {
        	cost = cost + Math.ceil(weight - 1) * 0.21;
        }
        if (weight > 3.5) {
        	cost = cost + 0.47;
        }
        System.out.printf("The cost of the package with a weight of " + weight + " ounces is: $" + cost + ".");
      }
   }