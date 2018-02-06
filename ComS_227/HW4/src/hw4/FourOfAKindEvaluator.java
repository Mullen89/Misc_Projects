package hw4;

import api.Card;

/**
 * Evaluator for a hand containing (at least) four cards of the same rank.
 * The number of main cards is four.
 * 
 * The name of this evaluator is "Four of a Kind".
 * @author Eric Mullen
 */
//Note: You must edit this declaration to extend AbstractEvaluator
//or to extend some other class that extends AbstractEvaluator
public class FourOfAKindEvaluator extends AbstractEvaluator
{
  /**
   * Constructs the evaluator.
   * @param ranking
   *   ranking of this type of hand
   * @param handSize
   *   total number of cards in a hand
   */
  public FourOfAKindEvaluator(int ranking, int handSize)
  {
	  super("Four of a Kind", ranking, handSize, 4, 0);
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
	  if(mainCards[0].compareToIgnoreSuit(mainCards[1]) == 0 && mainCards[0].compareToIgnoreSuit(mainCards[2]) == 0 && mainCards[0].compareToIgnoreSuit(mainCards[3]) == 0) return true;
	  else{return false;}
  }
}
