package lab7;

public class PositiveNumbers {
	
	public static int[] getPositiveNumbers(int[] numbers) {
		int count = 0;
		for (int i = 0; i < numbers.length; i += 1) {	
			if (numbers[i] > 0) {
				count += 1;
			}
		}
		
		
		int[] arr = new int[count];
		int index = 0;
		for (int i = 0; i < numbers.length; i += 1) {
			if (numbers[i] > 0) {
				arr[index] = numbers[i];
				index += 1; 
			}
		}
		return arr;
	}
}