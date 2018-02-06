package hw4;

/**
 * Evaluator for a generalized full house.  The number of main
 * cards is equal to the total cards.  If the total cards is an odd number
 * n, then there must be (n / 2) + 1 cards of matching rank and the
 * remaining (n / 2) cards must be of matching rank. In this case, when creating
 * a hand, <strong>the larger group must be listed first even if of lower rank
 * than the smaller group</strong> (e.g. as [3 3 3 5 5] rather than [5 5 3 3 3]).
 * If the hand size is even, then half the cards must be of matching 
 * rank and the remaining half of matching rank.  A group of cards
 * of the same rank always satisfies this
 * evaluator.
 * 
 * The name of this evaluator is "Full House".
 */
//Note: You must edit this declaration to extend AbstractEvaluator
//or to extend some other class that extends AbstractEvaluator
public class FullHouseEvaluator
{
  /**
   * Constructs the evaluator.
   * @param ranking
   *   ranking of this type of hand
   * @param handSize
   *   total number of cards in a hand
   */
  public FullHouseEvaluator(int ranking, int handSize)
  {
    // TODO: call appropriate superclass constructor and 
    // perform other initialization
  }
}
