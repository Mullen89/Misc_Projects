package hw4;
import java.util.ArrayList;
import java.util.Arrays;

import api.Card;
import api.Hand;
import api.IEvaluator;
import api.SubsetFinder;
import api.Util;

/**
 * The class AbstractEvaluator includes common code for all evaluator types.
 * 
 * TODO: Expand this comment with an explanation of how your class hierarchy
 * is organized.
 * @author Eric Mullen
 */
public abstract class AbstractEvaluator implements IEvaluator
{
	/**
	 * String value that denotes the name of Evaluator.
	 */
	private String name;
	/**
	 * number of total cards that can be used to create a hand.
	 */
	private int totalCards;
	/**
	 * The ranking of each hand of cards.
	 */
	private int ranking;
	/**
	 * The cards that will be evaluated.
	 */
	private int mainCards;
	/**
	 * Maximum rank allowed for a card.
	 */
	private int maxCardRank;
	
	/**
	 * This abstract evaluator is used to include common parameters for all evaluators. Each 
	 * parameter has already been stated above.
	 * 
	 * @param name
	 * @param ranking
	 * @param totalCards
	 * @param mainCards
	 * @param maxCardRank
	 */
	protected AbstractEvaluator(String name, int ranking, int totalCards, int mainCards, int maxCardRank){
		this.name = name;
		this.totalCards = totalCards;
		this.ranking = ranking;
		this.mainCards = mainCards;
		this.maxCardRank = maxCardRank;
	}
	/**
	 * returns the name of the evaluator
	 */
	public String getName(){
		return name;
	}
	/**
	 * returns the ranking of the hand
	 */
	public int getRanking(){
		return ranking;
	}
	/**
	 * returns the number of cards that are used to be evaluated
	 */
	public int numMainCards(){
		return mainCards;
	}
	/**
	 * returns the total number of cards to choose from
	 */
	public int totalCards(){
		return totalCards;
	}
	/**
	 * determines whether a subset of the total cards, or the total cards themselves,
	 * satisfies the criteria needed for the evaluator
	 */
	public boolean satisfiedBy(Card[] mainCards){
		return false;
	}
	/**
	 * determines whether any of all the possible subsets possible with the given cards can satisfy
	 * the criteria of the evaluator
	 */
	public boolean canSubsetSatisfy(Card[] allCards){
		boolean bool = false;
		if(allCards.length < numMainCards()) return bool;
		ArrayList<Card[]> tempArr = SubsetFinder.findSubsets(allCards, numMainCards());
		if(tempArr.size() == 0) return bool;
		
		for(int i = 0; i < tempArr.size(); i++){
			Card[] tmp = tempArr.get(i);
			if(satisfiedBy(tmp) == true) bool = true;
		}
		return bool;
	}
	/**
	 * creates a hand of the given num of cards allowed from the cards given along with a 
	 * chosen subset of those cards.
	 */
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
			Hand hand = new Hand(main, side, name, ranking);
			return hand;
		}
	}
	/**
	 * creates the highest value hand possible from all the given subsets.
	 */
	public Hand createBestHand(Card[] allCards){
		if(allCards.length < totalCards()) return null;
		if(canSubsetSatisfy(allCards) == false) return null;
		ArrayList<int[]> tempArr = SubsetFinder.findSubsets(allCards.length, numMainCards());
		ArrayList<int[]> mainArr = new ArrayList<int[]>();		
		if(tempArr.size() == 0) return null;
		
		for(int j = 0; j < tempArr.size(); j++){
			if(satisfiedBy(Util.getCardSubset(allCards, tempArr.get(j))) == true){
				mainArr.add(tempArr.get(j));
			}
		}
		Hand bestHand = createHand(allCards, mainArr.get(0));
		for(int i = 1; i < mainArr.size(); i++){
			Hand temp = createHand(allCards, mainArr.get(i));
			if(bestHand.compareTo(temp) >= 0) bestHand = temp;
		}
		return bestHand;
	}
}
