
package hw4;
import java.util.ArrayList;
import java.util.Random;
import java.util.Arrays;
import api.Card;
import api.Hand;
import api.IEvaluator;
import api.SubsetFinder;
import api.Suit;
import api.Util;
public class test {
public static void main(String[] args) {
// TODO Auto-generated method stub

/**
* one pair
*/
// AbstractEvaluator eval = new OnePairEvaluator(3, 4);
// System.out.println(eval.getName()); // "One Pair"

// Create an array of Cards to test. This is equivalent to
//Card[] cards = {new Card(2, Suit.CLUBS), new Card(2, Suit.DIAMONDS)};
// (see the Card class documentation)
// This array should satisfy the One Pair evaluator.
// Card[] cards = Card.createArray("3c, 2d");
// System.out.println(Arrays.toString(cards));
// System.out.println(eval.satisfiedBy(cards)); // true

// This one should not satisfy the one pair evaluator
// cards = Card.createArray("Kc, Qd");
// System.out.println(Arrays.toString(cards));
// System.out.println(eval.satisfiedBy(cards)); // false
// This one won't either, since it has more than the
// required number of cards
// cards = Card.createArray("2c, 2d, 3h");

// cards = Card.createArray("2c, 3d, 4d, 5c, 2c");
// System.out.println(Arrays.toString(cards));
// System.out.println(eval.satisfiedBy(cards)); // false

// However, it contains a subset that does
// System.out.println(eval.canSubsetSatisfy(cards)); // true

// Try a bigger array. We'll use Arrays.sort to get them
// in order, as required by the IEvaluator API. This
// illustrates the ordering of the Card compareTo() method
// cards = Card.createArray("6s, Jd, Ah, 10h, 6h, Js, Qc, Kh, Kh");
// Arrays.sort(cards); // now [Ah, Kh, Qh, Js, Jd, 10h, 6s, 6h, 6c]
// System.out.println(Arrays.toString(cards));
// System.out.println(eval.canSubsetSatisfy(cards)); // true
//
// // Define a subset consisting of indices 6 and 8
// // and have the evaluator create a Hand from those cards
// int[] subset = {7, 8};
// Hand hand = eval.createHand(cards, subset);
// System.out.println(hand); // One Pair (3) [6s 6c : Ah Kh]
// hand = eval.createBestHand(cards);
// System.out.println(hand); // One Pair (3) [Js Jd : Ah Kh]

/**
* 3 of a kind
*/

// IEvaluator eval = new ThreeOfAKindEvaluator(3, 5);
//// Card[] cards = {new Card(2, Suit.CLUBS), new Card(2, Suit.DIAMONDS)};
// Card[] cards = Card.createArray("2c, 2d, 2c");
// System.out.println(Arrays.toString(cards));
// System.out.println(eval.satisfiedBy(cards)); // true
//// 
// cards = Card.createArray("2c, 4d, 4d, 9c, 9h, 4s, 9s, 5c");
// Arrays.sort(cards);
// System.out.println(Arrays.toString(cards));
// System.out.println(eval.getName()); 
// System.out.println(eval.canSubsetSatisfy(cards)); // true
// int[] subset = {3,4,5};
// Hand hand = eval.createHand(cards, subset);
// hand = eval.createBestHand(cards);
// System.out.println(hand); 

/** 
* 4 of a kind
* 
*/

/*AbstractEvaluator eval = new FourOfAKindEvaluator(3, 5);
Card[] cards = Card.createArray("8c, 8d, 8c, 8s");
System.out.println(Arrays.toString(cards));
System.out.println(eval.satisfiedBy(cards)); // true

cards = Card.createArray("7c, 8d, 6c, 9c");
System.out.println(Arrays.toString(cards));
System.out.println(eval.satisfiedBy(cards)); //false
cards = Card.createArray("9s, 4d, 4d, 9c, 9c, 9s, 3c, 5c,");
Arrays.sort(cards);
System.out.println(Arrays.toString(cards));
System.out.println(eval.getName()); // 
System.out.println(eval.canSubsetSatisfy(cards)); //true
// cards = Card.createArray("9s, 4d, 5d, 9c, 9c, 9s, 3c, 5c,");
// Arrays.sort(cards);
int[] subset = {0,1,2,3};
Hand hand = eval.createHand(cards, subset);
System.out.println(hand);
// 
cards = Card.createArray("9s, 7d, 7d, 9c, 9c, 9s, 7c, 7c, Kc");
Arrays.sort(cards);
System.out.println(Arrays.toString(cards));

hand = eval.createBestHand(cards);
System.out.println(hand);

/**
* all suit
*/

// AllSuitsEvaluator eval = new AllSuitsEvaluator(3, 4);
// Card[] cards = Card.createArray("9d, 8d, 8h, 7c"); 
// //Card[] cards = Card.createArray("9s, 10s, 10c, 9h, 9d, 9s, 10c, 10c, 4c");
// //cards = Card.createArray("9c, 6d, 7s, 6h, 1s, 8s, 10d");
// Arrays.sort(cards);
// System.out.println(Arrays.toString(cards));
// System.out.println(eval.satisfiedBy(cards)); // false
//
// cards = Card.createArray("9d, 8s, 8h, 7c");
// Arrays.sort(cards);
// System.out.println(Arrays.toString(cards));
// System.out.println(eval.satisfiedBy(cards)); // true
// 
// cards = Card.createArray("1c, 2d, 3s, 4h, 8c, 10d");
// Arrays.sort(cards);
// System.out.println(Arrays.toString(cards));
// System.out.println(eval.canSubsetSatisfy(cards)); // true
// 
// cards = Card.createArray("1c, 2d, 3h, 4h, 8c, 10d");
// Arrays.sort(cards);
// System.out.println(Arrays.toString(cards));
// System.out.println(eval.canSubsetSatisfy(cards)); // false
// 
// cards = Card.createArray("1c, 2d, 3s, 4h, 8c, 10d");
// Arrays.sort(cards);
// int subset[] = {0,1,3,4};
// System.out.println(Arrays.toString(cards));
// System.out.println(eval.createHand(cards, subset));
// 
// 
// cards = Card.createArray("12d, 7d, 9c, 5c, 3h, 7h, 2s, 5s");
// Arrays.sort(cards);
// System.out.println(Arrays.toString(cards));
// System.out.println(eval.createBestHand(cards));

/**
* all prime
*/
/*IEvaluator eval = new AllPrimesEvaluator(3,4);
Card[] cards = Card.createArray("2c, 2s, 2d, 5h"); 
Arrays.sort(cards); 
System.out.println(Arrays.toString(cards));//[5,2,2,2]
System.out.println(eval.satisfiedBy(cards)); // true


cards = Card.createArray("2c, 4s, 2d, 5h"); 
Arrays.sort(cards);
System.out.println(Arrays.toString(cards)); // [5,4,2,2]
System.out.println(eval.satisfiedBy(cards)); // false


cards = Card.createArray("2c, 2c, 3s, 7s, 8s"); // [8,7,3,2,2]
Arrays.sort(cards);
System.out.println(Arrays.toString(cards));
System.out.println(eval.canSubsetSatisfy(cards)); // true

cards = Card.createArray("2c, 2c, 5s, 10s, 8s");
Arrays.sort(cards);
System.out.println(Arrays.toString(cards)); // [10,8,5,2,2]
System.out.println(eval.canSubsetSatisfy(cards)); // false

cards = Card.createArray("7c, 5d, 5c, 9c, 2d"); // [9,7,5,5,2]
Arrays.sort(cards);
int[] subset = {1,2,3,4};
System.out.println(Arrays.toString(cards));
System.out.println(eval.createHand(cards, subset)); // [7,5,5,2] 

cards = Card.createArray("3c, 6d, 7h, 2s, 7d"); 
Arrays.sort(cards);
System.out.println(Arrays.toString(cards)); // [7,7,6,3,2]
int[] subset1 = {0,1,3,4}; 
System.out.println(eval.createHand(cards, subset1)); // [7,7,3,2]

IEvaluator eval1 = new AllPrimesEvaluator(3,2);
cards = Card.createArray("2d, 6c, 8c, 5d, 7c"); 
Arrays.sort(cards);
int[] subset2 = {1,3};
System.out.println(Arrays.toString(cards)); //[8,7,6,5,2]
System.out.println(eval1.createHand(cards, subset2)); // [7,5]

cards = Card.createArray("9c, 9s, 9s, 8d, 10s, 10c"); 
Arrays.sort(cards);
System.out.println(Arrays.toString(cards)); //[10,10,9,9,9,8]
System.out.println(eval.createBestHand(cards)); // null

cards = Card.createArray("7c, 3s, 5s, 8d, 10d, 10c, 5s, 7s, 2d"); 
Arrays.sort(cards);
System.out.println(Arrays.toString(cards)); //[10,10,8,7,7,5,5,3,2]
System.out.println(eval.createBestHand(cards)); // [7,7,5,5]
*/


/**
* FullHouse
*/
//IEvaluator eval = new FullHouseEvaluator(3,5);
//Card[] cards2 = Card.createArray("Qd, Qc, 9h, 9d, 9c");
//Card[] cards1 = Card.createArray("9h, 9d, 9c, 2d, 2c");
//int[] arr = new int[]{0,1,2,3,4};
//Arrays.sort(cards1);
//Arrays.sort(cards2);
//System.out.println(Arrays.toString(cards2));
//System.out.println(Arrays.toString(cards1));
//System.out.println();
//System.out.println(Arrays.toString(cards2)); // [Q,Q,9,9,9]
//System.out.println(eval.satisfiedBy(cards2)); // true
//System.out.println(eval.createHand(cards2, arr));
//System.out.println(Arrays.toString(cards1)); // [4,4,4,2,2]
//System.out.println(eval.satisfiedBy(cards1)); // true
//System.out.println();
//System.out.println();
//System.out.println();

//IEvaluator eval1 = new FullHouseEvaluator(3,5);
//Card[] cards = Card.createArray("4h, 4h, 3h, 2s, 2s");
//System.out.println(Arrays.toString(cards)); // [4,4,3,2,2]
//System.out.println(eval1.satisfiedBy(cards)); // false

//IEvaluator eval2 = new FullHouseEvaluator(3,5);
//Card[] cards = Card.createArray("4h, 4h, 4h, 2s, 2s");
//System.out.println(Arrays.toString(cards)); // [4,4,4,2,2]
//System.out.println(eval2.satisfiedBy(cards)); // true
//
//IEvaluator eval3 = new FullHouseEvaluator(3,4);
//int[] arr = new int[]{0,1,2,3};
//Card[] cards = Card.createArray("5h, 5d, 8s, 8s");
//System.out.println(Arrays.toString(cards)); // [5,5,8,8]
//System.out.println(eval3.satisfiedBy(cards)); // true
//System.out.println(eval3.createHand(cards, arr)); 
//
//IEvaluator eval4 = new FullHouseEvaluator(3,4);
//Card[] cards = Card.createArray("5h, 5d, 9s, 8s");
//System.out.println(Arrays.toString(cards)); // [5,5,9,8]
//System.out.println(eval4.satisfiedBy(cards)); // false

// IEvaluator eval5 = new FullHouseEvaluator(3,4);
// Card[] cards = Card.createArray("9c, 7d, 1c, 7c, 9d");
// Arrays.sort(cards);
// System.out.println(Arrays.toString(cards)); // [Ac, 9d, 9c, 7d, 7c]
// System.out.println(eval5.canSubsetSatisfy(cards)); // true
// System.out.println(eval5.satisfiedBy(cards)); // false
// 
//
// 
// IEvaluator eval6 = new FullHouseEvaluator(3,4);
// Card[] cards = Card.createArray("9c, 7d, 1c, 7c, 10d");
// Arrays.sort(cards);
// System.out.println(Arrays.toString(cards)); // [Ac, 10d, 9c, 7d, 7c]
// System.out.println(eval6.canSubsetSatisfy(cards)); // false

// IEvaluator eval7 = new FullHouseEvaluator(3,5);
// Card[] cards = Card.createArray("8c, 8d, 9d, 9d, 10c, 11d, 11c, 11s"); 
// Arrays.sort(cards);
// System.out.println(Arrays.toString(cards)); // [Js, Jd, Jc, 10c, 9d, 9d, 8d, 8c]
// System.out.println(eval7.canSubsetSatisfy(cards)); // true
// 
// IEvaluator eval8 = new FullHouseEvaluator(3,5);
// Card[] cards = Card.createArray("8c, 2d, 2d, 9d, 10c, 10d, 11c, 11s"); 
// Arrays.sort(cards);
// System.out.println(Arrays.toString(cards)); // [Js, Jc, 10d, 10c, 9d, 8c, 2d, 2d]
// System.out.println(eval8.canSubsetSatisfy(cards)); // false

// IEvaluator eval9 = new FullHouseEvaluator(3,5);
// Card[] cards = Card.createArray("8c, 8d, 9d, 9d, 10c, 11d, 11c, 11s"); 
// Arrays.sort(cards);
// int[] subset = {0,1,2,4,5};
// System.out.println(Arrays.toString(cards)); // [Js, Jd, Jc, 10c, 9d, 9d, 8d, 8c]
// System.out.println(eval9.createHand(cards, subset)); //[j,j,j,9,9]
// 
// int[] subset1 = {0,1,2,3,4};
// System.out.println(eval9.createHand(cards, subset1)); //null
// 
// IEvaluator eval10 = new FullHouseEvaluator(3,5);
// Card[] cards = Card.createArray("8c, 8d, 9d, 9d, 10c, 11d, 11c, 11s"); 
// Arrays.sort(cards);
// System.out.println(Arrays.toString(cards)); // [Js, Jd, Jc, 10c, 9d, 9d, 8d, 8c]
// System.out.println(eval10.createBestHand(cards)); //[j,j,j,9,9]


// IEvaluator eval13 = new FullHouseEvaluator(3,5);
// Card[] cards = Card.createArray("11d, 11d, 11c, 4c, 4d, 4h, 2c, 2d, 7d, 7c");
// Arrays.sort(cards);
// System.out.println(Arrays.toString(cards)); // [Jd, Jd, Jc, 7d, 7c, 4h, 4d, 4c, 2d, 2c]
// System.out.println(eval13.createBestHand(cards)); // [Jd Jd Jc 7d 7c]
// 
// IEvaluator eval14 = new FullHouseEvaluator(3,5);
// Card[] cards = Card.createArray("5d, 5c, 2d, 2c, 2s, 4d, 4d, 9c, 9d, 9h");
// Arrays.sort(cards);
// System.out.println(Arrays.toString(cards)); //[9h, 9d, 9c, 5d, 5c, 4d, 4d, 2s, 2d, 2c]
// System.out.println(eval14.createBestHand(cards)); // [9h 9d 9c 5d 5c]

// IEvaluator eval15 = new FullHouseEvaluator(3,5);
 //Card[] cards = Card.createArray("3d, 3c, 2d, 2c, 2s, 9c, 9d, 9h, 10d, 7c, 4d, 2c, 2d ,4c ,6c");
 //Card[] cards = Card.createArray("12d, 12c, 11c, 10d, 9h, 9d, 9c, 7c, 6c, 4d, 2s, 2d"); //????????????
 
// int[] subset = new int[] {0,1,4,5,6};
// Arrays.sort(cards);
// System.out.println(Arrays.toString(cards));
// System.out.println(eval15.canSubsetSatisfy(cards));
// System.out.println(eval15.createBestHand(cards));

// IEvaluator eval = new FullHouseEvaluator(3,5);
// Card[] cards = Card.createArray("3c, 3c, 5c, 4d, 4h");
// System.out.println(eval.satisfiedBy(cards));
// Arrays.sort(cards);
//System.out.println(Arrays.toString(cards));
// System.out.println(eval15.createHand(cards, subset));
// System.out.println(eval15.createBestHand(cards)); 
// 

// hand size 4, max rank 10
// IEvaluator eval = new StraightEvaluator(42, 4, 10); 
// Card[] cards1 = Card.createArray("Ac, 10c, 9c, 8c"); // array is sorted
// System.out.println(eval.satisfiedBy(cards1)); // true, ace used as highest
// Card[] cards2 = Card.createArray("Ac, 7c, 6c, 5c");
// System.out.println(eval.satisfiedBy(cards2)); // false
// Card[] cards3 = Card.createArray("Ac, 4c, 3c, 2c");
// System.out.println(eval.satisfiedBy(cards3)); // true, ace used as lowest
// Card[] cards4 = Card.createArray("Ac, 5, 4c, 3c");
// System.out.println(eval.satisfiedBy(cards4)); // false
// Card[] cards5 = Card.createArray("Ac, 12d, 11c, 10d");
// System.out.println(eval.satisfiedBy(cards5)); // false
// Card[] cards6 = Card.createArray("Ac, 5d, 4d, 3c");
// System.out.println(eval.satisfiedBy(cards6)); // false
/**
* Straight Evaluator
*/
// IEvaluator eval = new StraightEvaluator(42, 4, 10); 
// Card[] cards1 = Card.createArray("Ac, 10c, 9c, 8c"); // array is sorted
// System.out.println(eval.satisfiedBy(cards1)); // true, ace used as highest
// Card[] cards2 = Card.createArray("Ac, 7c, 6c, 5c");
// System.out.println(eval.satisfiedBy(cards2)); // false
// Card[] cards3 = Card.createArray("Ac, 4c, 3c, 2c");
// System.out.println(eval.satisfiedBy(cards3)); // true, ace used as lowest
// 
// Card[] cards4 = Card.createArray("13c, 13h, 11s, 10c, 8c, 8c, 6d, 6c, 5h, 4s, 3c"); 
// System.out.println(eval.canSubsetSatisfy(cards4)); // true
// 
//
// int[] indices = {0, 1, 2, 3}; 
// Hand h = eval.createHand(cards1, indices);
// System.out.println(h); // Straight (42) [Ac 10c 9c 8c] // sorted normally
// h = eval.createHand(cards2, indices);
// System.out.println(h); // null
// h = eval.createHand(cards3, indices);
// System.out.println(h); // Straight (42) [4c 3c 2c Ac] // modified sort
//// 
// System.out.println(eval.createBestHand(cards4)); 




/**
* Straight Flush
*/

//AbstractEvaluator eval = new StraightFlushEvaluator(3, 4, 5);
//Card[] card = Card.createArray("5c, 4c, 3c, 2c"); // true
//System.out.println(eval.satisfiedBy(card));
//
//Card[] card1 = Card.createArray("5c, 4c, 3c, 2h"); // false
//System.out.println(eval.satisfiedBy(card1));
//
//Card[] card2 = Card.createArray("Ac, 4c, 3c, 2c"); //true
//System.out.println(eval.satisfiedBy(card2));
//
//Card[] card3 = Card.createArray("As, 4c, 3c, 2c"); //false
//System.out.println(eval.satisfiedBy(card3));
//
//Card[] card4 = Card.createArray("As, 9s, 8d, 7c, 6c, 5c, 4d, 3d, 2d");
//System.out.println(eval.satisfiedBy(card4)); // false
//
//AbstractEvaluator eval1 = new StraightFlushEvaluator(3, 4, 10);
//Card[] card5 = Card.createArray("As, 13d, 12d, 11c, 10s, 9d, 8c, 7d, 6d, 5d, 3c");
//System.out.println(eval1.createBestHand(card5)); // null
//
//AbstractEvaluator eval2 = new StraightFlushEvaluator(3, 4, 10);
//Card[] card6 = Card.createArray("7d, 6d, 5d, 4d");
//System.out.println(eval.canSubsetSatisfy(card6));
//System.out.println(eval2.createBestHand(card6)); // [7,6,5,4]




















}
}