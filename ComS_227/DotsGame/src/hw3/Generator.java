package hw3;
import java.util.Random;

import api.Dot;

/**
 * Class encapsulating a mechanism for producing new Dot objects
 * for a game.
 * @author ESMullen
 */
public class Generator
{
	/**
	 * A dot with type that is random based on the number of types (colors) available.
	 */
	private Random randomDot;
	/**
	 * The number of types (colors) of dots available for a generated game grid.
	 * Used in the random generator.
	 */
	private int numberOfTypes;
	/**
	 * The number given which determines the type (color) of the dot.
	 */
	private int givenType;
	
	/**
	 * The current type of dot generated.
	 */

	/**
	 * determines (True or False) if A generator is using a given type of dots, or random types
	 * of dots.
	 */
	private boolean isRandom;
	
	/**
	 * Constructs a Generator whose generate() method always
	 * returns a Dot of the given type.  (This method is intended
	 * for testing.)
	 * @param givenType
	 *   type of Dot objects to be generated
	 */

	public Generator(int givenType)
	{
		this.givenType = givenType;	
		isRandom = false;
	}

	/**
	 * Constructs a Generator that will create
	 * random Dots with types 0 through numTypes - 1,
	 * using the given Random instance.
	 * @param numTypes
	 *   number of types of dots
	 * @param rand
	 *   random number generator to use
	 */

	public Generator(int numTypes, Random rand)
	{
		isRandom = true;
		randomDot = rand;
		numberOfTypes = numTypes;
	}

	/**
	 * Returns an instance of Dot according to this generator's rules
	 * (Random or fixed value).
	 * @return
	 *   a new Dot object
	 */
	public Dot generate()
	{
		if (isRandom == false) {
			return new Dot(givenType);
		}
		else {
			return new Dot(randomDot.nextInt(numberOfTypes));
		}
	}

	/**
	 * Initializes the given 2D array of Dot objects
	 * with values produced by this generator.
	 * @param grid
	 *   a 2D array to be initialized
	 */
	public void initialize(Dot[][] grid)
	{ 
		for (int row = 0; row < grid.length; ++row)
		{
			for (int col = 0; col < grid[0].length; ++col)
			{
				grid[row][col] = generate();
			}
		}
	}	
}