package hw4;

import java.util.ArrayList;

import api.Card;
import api.Hand;
import api.SubsetFinder;
import api.Util;

/**
 * Evaluator for a hand in which the rank of each card is a prime number.
 * The number of main cards required is equal to the total cards.
 * 
 * The name of this evaluator is "All Primes".
 * @author Eric Mullen
 */
//Note: You must edit this declaration to extend AbstractEvaluator
//or to extend some other class that extends AbstractEvaluator
public class AllPrimesEvaluator extends AbstractEvaluator
{
  /**
   * Constructs the evaluator.
   * @param ranking
   *   ranking of this type of hand
   * @param handSize
   *   total number of cards in a hand
   */
  public AllPrimesEvaluator(int ranking, int handSize)
  {
	  super("All Primes", ranking, handSize, handSize, 0);
    // TODO: call appropriate superclass constructor and 
    // perform other initialization
  }
  /**
   * determines whether a subset of the total cards, or the total cards themselves,
   * satisfies the criteria needed for the evaluator
   */
  	@Override
  	public boolean satisfiedBy(Card[] mainCards){
  		if(mainCards.length != numMainCards()) return false;
  		if(numMainCards() != totalCards()) return false;
  		
		boolean prime = true;
		for(int i = 0; i < mainCards.length; i++){
			for(int j = mainCards[i].getRank()-1; j > 1; j--){
				if(mainCards[i].getRank() % j == 0){
					prime = false;
					break;
				}
			}
			if(prime == false) break;
		}
		return prime;
	}
}
