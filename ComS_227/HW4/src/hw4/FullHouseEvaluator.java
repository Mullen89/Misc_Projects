package hw4;

import java.util.ArrayList;
import java.util.Arrays;

import api.Card;
import api.Hand;
import api.SubsetFinder;
import api.Util;

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
 * @author Eric Mullen
 */
//Note: You must edit this declaration to extend AbstractEvaluator
//or to extend some other class that extends AbstractEvaluator
public class FullHouseEvaluator extends AbstractEvaluator
{
	/**
	 * the ranking for this hand
	 */
	private int ranking;
  /**
   * Constructs the evaluator.
   * @param ranking
   *   ranking of this type of hand
   * @param handSize
   *   total number of cards in a hand
   */
  public FullHouseEvaluator(int ranking, int handSize)
  {
	  super("Full House", ranking, handSize, handSize, 0);
	  this.ranking = ranking;
    // TODO: call appropriate superclass constructor and 
    // perform other initialization
  }
  /**
   * private helper method that checks to make sure all cards in the given array
   * have the same rank
   * @param halves
   * @return whether the array is all equal
   */
  private boolean equalityCheck(Card[] halves){
	  boolean isEqual = true;
	  for(int i = 0; i < halves.length-1; i++){
		  if(halves[i].getRank() != halves[i+1].getRank()){
			  isEqual = false;
			  break;
		  }
	  }
	  return isEqual;
  }
  /**
   * determines whether a subset of the total cards, or the total cards themselves,
   * satisfies the criteria needed for the evaluator
   */
  @Override
  public boolean satisfiedBy(Card[] mainCards){
		if(mainCards.length != numMainCards()) return false;
		if(numMainCards() != totalCards()) return false;
		boolean satisfies = false;
		Card[] left2 = new Card[mainCards.length/2+1];
		Card[] right2 = new Card[mainCards.length/2];
		Card[] tempMain = mainCards;
		if(tempMain.length % 2 != 0){
			int count = 0;
			int count2 = 0;
			for(int c = 0; c < tempMain.length-1; c++){
				if(tempMain[c].getRank() == tempMain[c+1].getRank()){
					count++;
				}
				if(tempMain[c].getRank() != tempMain[c+1].getRank()){
					break;
				}
			}
			for(int cd = tempMain.length-1; cd >= 1; cd--){
				if(tempMain[cd].getRank() == tempMain[cd-1].getRank()){
					count2++;
				}
				if(tempMain[cd].getRank() != tempMain[cd-1].getRank()){
					break;
				}
			}
			if(count2 > count){
				for(int i = 0; i < tempMain.length / 2; i++)
				{
				    Card temp = tempMain[i];
				    tempMain[i] = tempMain[tempMain.length - i - 1];
				    tempMain[tempMain.length - i - 1] = temp;
				}
			}
			
			for(int i = tempMain.length/2; i >= 0; i--){
				left2[i] = tempMain[i];
			}
			for(int j = 0; j < tempMain.length/2; j++){
				right2[j] = tempMain[tempMain.length/2+1+j];
			}
			if(equalityCheck(left2) == true && equalityCheck(right2) == true){
				satisfies = true;
			}
		}else{
			Card[] left = new Card[tempMain.length/2];
			Card[] right = new Card[tempMain.length/2];
			
			for(int i = (tempMain.length/2-1); i >= 0; i--){
				  left[i] = tempMain[i];
			 }
			for(int j = 0; j < tempMain.length/2; j++){
				right[j] = tempMain[tempMain.length/2+j];
			}
			if(equalityCheck(left) == true && equalityCheck(right) == true){
				satisfies = true;
			}
		}
		return satisfies;
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
			if(main[0].getRank() < main[main.length-1].getRank() && main.length%2 == 0){
				int index = 0;
				Card[] temp = new Card[main.length];
				for(int k = main.length-1; k >= 0; k--){
					temp[index] = main[k];
					index++;
				}
				main = temp;	
			}
			else if(main.length%2 != 0){
				Card[] tempMain = main;
				if(tempMain.length % 2 != 0){
					int count = 0;
					int count2 = 0;
					for(int c = 0; c < tempMain.length-1; c++){
						if(tempMain[c].getRank() == tempMain[c+1].getRank()){
							count++;
						}
						if(tempMain[c].getRank() != tempMain[c+1].getRank()){
							break;
						}
					}
					for(int cd = tempMain.length-1; cd >= 1; cd--){
						if(tempMain[cd].getRank() == tempMain[cd-1].getRank()){
							count2++;
						}
						if(tempMain[cd].getRank() != tempMain[cd-1].getRank()){
							break;
						}
					}
					if(count2 > count){
						for(int i = 0; i < tempMain.length / 2; i++)
						{
						    Card temp = tempMain[i];
						    tempMain[i] = tempMain[tempMain.length - i - 1];
						    tempMain[tempMain.length - i - 1] = temp;
						}
					}
				}
			}
			Hand hand = new Hand(main, side, "Full House", ranking);
			return hand;
		}
	}
}
