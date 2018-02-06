package lab9;

public class RecursiveDeli
{

  public static void main(String[] args)
  {
    int result = fib(5);
    System.out.println(result);
  }

  public static String makeDigitSandwich(int outerDigit)
  {
    if (outerDigit == 0)
    {
      // Base case
      return "0";
    }
    else
    {
      // Recursive case
      String middle = makeDigitSandwich(outerDigit - 1); 
      String result = outerDigit + middle + outerDigit;
      return result;
    }
  }
  public static int fib(int n)
  {
    if (n == 0 || n == 1)
    {
      // base cases: return n
      return n;
    }
    else
    {
      // recursive case: sum of previous two values
      return fib(n - 1) + fib(n - 2);
    }
  }
}