package lab5;

public class Lab5Example
{
  public static void main(String[] args)
  {
    System.out.println(longestRun("aabbbccd"));
    System.out.println("Expected 3");
    System.out.println(longestRun("aaa"));
    System.out.println("Expected 3");
    System.out.println(longestRun("aabbbb"));
    System.out.println("Expected 4");
  }
 
  public static int longestRun(String s)
  {
    int count = 1;
    int max = 1;
    
    for (int i = 0; i < s.length() - 1; i += 1) {
        char c = s.charAt(i);
        char current = s.charAt(i + 1);
        if (c == current) {
            count += 1;
        }
        else {
            if (count > max) {
                max = count;
            }
            count = 1;
        }
    }
    if(count > max) {
        max = count;
    }
    return max;
  }
}