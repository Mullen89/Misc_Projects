package lab7;
import java.util.Arrays;

public class ArrayTest {

	public static void main(String[] args) {
		
		int[] numbers = new int[7];
		numbers[0] = -22;
		numbers[1] = 8;
		numbers[2] = 45;
		numbers[3] = 0;
		numbers[4] = 16;
		numbers[5] = -1;
		numbers[6] =3;
		System.out.println(Arrays.toString(PositiveNumbers.getPositiveNumbers(numbers)));
	}

}
