package hw4;

import api.Card;
import api.Suit;

/**
 * Evaluator for a hand that contains at least one card from
 * each suit.  The number of main cards is four.
 * 
 * The name of this evaluator is "All Suits".
 * @author Eric Mullen
 */
//Note: You must edit this declaration to extend AbstractEvaluator
//or to extend some other class that extends AbstractEvaluator
public class AllSuitsEvaluator extends AbstractEvaluator
{
  /**
   * Constructs the evaluator.
   * @param ranking
   *   ranking of this type of hand
   * @param handSize
   *   total number of cards in a hand
   */
  public AllSuitsEvaluator(int ranking, int handSize)
  {
	  super("All Suits", ranking, handSize, 4, 0);
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
		boolean diamond = false;
		boolean heart = false;
		boolean club = false;
		boolean spade = false;
		
		for(int i = 0; i < mainCards.length; i++){
			if(mainCards[i].getSuit() == Suit.DIAMONDS) diamond = true;
			else if(mainCards[i].getSuit() == Suit.HEARTS) heart = true;
			else if(mainCards[i].getSuit() == Suit.CLUBS) club = true;
			else if(mainCards[i].getSuit() == Suit.SPADES) spade = true;
		}
		if(diamond == true && club == true && heart == true && spade == true) return true;
		else{return false;}
	} 
}
