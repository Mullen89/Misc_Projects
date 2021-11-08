package hw4;

import api.Card;
import api.Hand;
import api.Suit;
import api.Util;

/**
 * Evaluator for a hand consisting of a "straight" in which the
 * card ranks are consecutive numbers AND the cards all
 * have the same suit.  The number of main 
 * cards is equal to the total cards.  An ace (card of rank 1) 
 * may be treated as the highest possible card or as the lowest
 * (not both) To evaluate a straight containing an ace it is
 * necessary to know what the highest card rank will be in a
 * given game; therefore, this value must be specified when the
 * evaluator is constructed.  In a hand created by this
 * evaluator the cards are listed in descending order with high 
 * card first, e.g. [10 9 8 7 6] or [A K Q J 10], with
 * one exception: In case of an ace-low straight, the ace
 * must appear last, as in [5 4 3 2 A]
 * 
 * The name of this evaluator is "Straight Flush".
 * @author Eric Mullen
 */
//Note: You must edit this declaration to extend AbstractEvaluator
//or to extend some other class that extends AbstractEvaluator
public class StraightFlushEvaluator extends AbstractEvaluator
{
	/**
	 * the ranking of the hand
	 */
	private int ranking;
	/**
	 * the maximum rank a card can have
	 */
   private int maxCardRank;
  /**
   * Constructs the evaluator. Note that the maximum rank of
   * the cards to be used must be specified in order to 
   * correctly evaluate a straight with ace high.
   * @param ranking
   *   ranking of this type of hand
   * @param handSize
   *   total number of cards in a hand
   * @param maxCardRank
   *   largest rank of any card to be used
   */
  public StraightFlushEvaluator(int ranking, int handSize, int maxCardRank)
  {
	  super("Straight", ranking, handSize, handSize, maxCardRank);
	  this.ranking = ranking;
	  this.maxCardRank = maxCardRank;
  }
  /**
   * private helper method that, if there is an Ace in front, and a 2 at the end,
   * it places the Ace in the back of the Card[].
   * @param mainCards
   * @return a modified Card[] with the Ace in the back
   */
  private Card[] modifyOrder(Card[] mainCards){
	  Card[] temp = new Card[mainCards.length];
	  if(mainCards[0].getRank() == 1){		  
		  if(!((mainCards[1].getRank() == maxCardRank)||(mainCards[0].getRank() - mainCards[1].getRank() == 1))){
			  if(mainCards[mainCards.length-1].getRank() == 2){
				  temp[mainCards.length-1] = mainCards[0];
				  for(int i = 1; i < mainCards.length; i++){
					  temp[i-1] = mainCards[i];
				  }
				  mainCards = temp;
			  }
		  }
	  }
	  return mainCards;
  }
  /**
   * determines whether a subset of the total cards, or the total cards themselves,
   * satisfies the criteria needed for the evaluator
   */
  @Override
  public boolean satisfiedBy(Card[] mainCards){
	  if(mainCards.length != numMainCards()) return false;
	  if(numMainCards() != totalCards()) return false;
	  boolean sameSuit = true;
	  boolean straight = false;
	  boolean descending = true;
	  for(int i = 0; i < mainCards.length-1; i++){
		  if(mainCards[i].getSuit() != mainCards[i+1].getSuit()){
			  sameSuit = false;
			  break;
		  }
	  }
	  if(sameSuit == false) return sameSuit;
	  if(mainCards[0].getRank() == 1){
		  for(int i = 1; i < mainCards.length-1; i++){
			  if(mainCards[i].getRank() - mainCards[i+1].getRank() != 1){
				  descending = false;
				  break;
			  }
		  }
		  if((mainCards[1].getRank() == maxCardRank && descending == true)||(mainCards[0].getRank() - mainCards[1].getRank() == 1 && descending == true)){
			  straight = true;
		  }else{
			  if(mainCards[mainCards.length-1].getRank() == 2 && descending == true){
				  straight = true;
			  }
		  }
	  }
	  else{
		  for(int i = 0; i < mainCards.length-1; i++){
			  if(mainCards[i].getRank() - mainCards[i+1].getRank() != 1){
				  descending = false;
				  break;
			  }
		  }
		  if(descending == true){
			  straight = true;
		  }
	  }
	  return straight;
  }
  /**
	 * creates a hand of the given num of cards allowed from the cards given along with a 
	 * chosen subset of those cards.
	 */
  @Override
  public Hand createHand(Card[] allCards, int[] subset){
		if(allCards.length < totalCards() || subset.length < numMainCards()) return null;
		if(canSubsetSatisfy(allCards) == false) return null;
		if(satisfiedBy(Util.getCardSubset(allCards, subset)) == false) return null;
		else{
			Card[] main = Util.getCardSubset(allCards, subset);
			Card[] tempSide = Util.getCardNonSubset(allCards, subset);
			Card[] side = new Card[totalCards() - numMainCards()];
			for(int i = 0; i < side.length; i++){
				side[i] = tempSide[i];
			}
			main = modifyOrder(main);
			Hand hand = new Hand(main, side, "Straight Flush", ranking);
			return hand;
		}
	}

}
