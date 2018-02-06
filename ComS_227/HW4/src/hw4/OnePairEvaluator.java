package hw4;

import api.Card;

/**
 * Evaluator for a hand containing (at least) two cards of the same rank.
 * The number of main cards is two.
 * 
 * The name of this evaluator is "One Pair".
 * @author Eric Mullen
 */
//Note: You must edit this declaration to extend AbstractEvaluator
//or to extend some other class that extends AbstractEvaluator
public class OnePairEvaluator extends AbstractEvaluator
{
  /**
   * Constructs the evaluator.
   * @param ranking
   *   ranking of this type of hand
   * @param handSize
   *   total number of cards in a hand
   */
  public OnePairEvaluator(int ranking, int handSize)
  {
	  super("One Pair", ranking, handSize, 2, 0);
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
		if(mainCards[0].compareToIgnoreSuit(mainCards[1]) == 0) return true;
		else{return false;}
	}
}
