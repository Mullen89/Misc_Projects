package lab7;

import java.util.ArrayList;

public class RemoveDuplicates
{
  public static void main(String[] args)
  {
    ArrayList<String> test = new ArrayList<String>();
    test.add("apple");
    test.add("of");
    test.add("a");
    test.add("boat");
    test.add("boat");

    removeShortWords(test);
    System.out.println(test);
  }
  
  // This is better
  public static void removeShortWords(ArrayList<String> words)
  {
    ArrayList<String> temp = new ArrayList<String>();
    for (String word : words)
    {
      if (!temp.contains(word))
      {
        temp.add(word);
      }
    }
    words.clear();
    words.addAll(temp);
  }
}