package lab7;
import java.util.Arrays;
import java.util.Random;


public class RandomExperimant {

	public static int[] randomExperiment(int max, int iters) {
		
		Random rand = new Random();
		int[] counts = new int[max];

		for (int i = 0; i < iters; i++) {
			int randNum = rand.nextInt(max);
			counts[randNum]++;
		}
		return counts;
	}
		
	public static void main(String[] args) {
		System.out.println(Arrays.toString(randomExperiment(10, 1000)));
	}
}
