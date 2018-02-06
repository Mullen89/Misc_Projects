package hw4;

import api.Card;

/**
 * Evaluator satisfied by any set of cards.  The number of
 * main cards is equal to the total cards.
 * 
 * The name of this evaluator is "Catch All".
 * @author Eric Mullen
 */
//Note: You must edit this declaration to extend AbstractEvaluator
//or to extend some other class that extends AbstractEvaluator
public class CatchAllEvaluator extends AbstractEvaluator
{
  /**
   * Constructs the evaluator.
   * @param ranking
   *   ranking of this type of hand
   * @param handSize
   *   total number of cards in a hand
   */
  public CatchAllEvaluator(int ranking, int handSize)
  {
	  super("Catch All", ranking, handSize, handSize, 0);
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
	  else{return true;}
  }
}
