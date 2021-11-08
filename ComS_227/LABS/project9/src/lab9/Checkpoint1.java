package lab9;

public class Checkpoint1
{
  public static void main(String[] args)
  {
    int[] test = {3, 4, 5, 1, 2, 3, 2, 7, 9, 5, 3, 4, 13, 6, 8, 3, 2};
    int result = arraySum(test);
    System.out.println(result);
    int pyramid = getPyramidCount (3);
    System.out.println(pyramid);
  }

  public static int arraySum(int[] arr)
  {
    return arraySumRec(arr, 0, arr.length - 1);
  }
  
  private static int arraySumRec(int[] arr, int start, int end)
  {
    if (start == end)
    {
      return arr[start];
    }
    else
    {
      int mid = (start + end) / 2;
      int leftSum = arraySumRec(arr, start, mid);
      int rightSum = arraySumRec(arr, mid + 1, end);
      if( leftSum > rightSum) {
    	  return leftSum;
      }else {
    	  return rightSum;
      }
    }
  }
  
  public static int getPyramidCount (int numLevels) {
	  
	  if (numLevels == 1 || numLevels == 0) {
		  
		  return numLevels;
	  }
	  else {
		  
		  int totalNumBalls = getPyramidCount (numLevels - 1);
		  int result = (numLevels * numLevels) + totalNumBalls;
		  return result;
		  
	  }
  }
}