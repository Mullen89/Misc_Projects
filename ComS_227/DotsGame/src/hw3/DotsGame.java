
package hw3;

import java.util.ArrayList;

import api.*;

/**
 * This class encapsulates the game logic for a video game called Dots.
 * The game consists of a 2D array or grid of colored icons, called dots, 
 * along with an ordered list that we will call the selection list.  Intuitively, 
 * the selection list represents a set of adjacent dots, all of the same color, 
 * that have been selected by the player.  When selection is complete (e.g. the 
 * mouse is released), the selected dots disappear from the grid, and the dots 
 * above shift down  to take their places.  Then new dots fill in each column from the top.
 * A point is scored for each dot that disappears from the grid.  There
 * is a special rule for the case that the selected dots form a loop;
 * then all dots in the grid of the same color disappear too.
 * @author ESMullen
 */
public class DotsGame
{
	/**
	 * the score that is keep track and shown on the gameboard.
	 */
  private int score;
  
  /**
   * the width of the game grid.
   */
  private int width;
  /**
   * height of the game grid.
   */
  private int height;
  /**
   * the 2d array on which the game is played.
   */
  private Dot[][] gameArray;
  /**
   * generator used to make a game with random dots or given dots.
   */
  private Generator tempGen;
  /**
   * the list that stores the dots selected.
   */
  private ArrayList<Descriptor> list = new ArrayList<Descriptor>();
  /**
   *  the current dot that is selected.
   */
  private Descriptor currentDot;
  /**
   * returns true if a full loop is selected.
   */
  private boolean completesLoop;
	
  /**
   * Constructs a game with the given number of columns and rows that will use
   * the given Generator instance to create new icons.  The dots
   * in the initial grid are produced by the generator.
   * 
   * @param width
   *          number of columns
   * @param height
   *          number of rows
   * @param generator
   *          generator for new icons
   */
  public DotsGame(int width, int height, Generator generator)
  {
	  gameArray = new Dot[height][width];
	  tempGen = generator;
	  this.width = width;
	  this.height = height;
	  for (int i = 0; i < height; i++) {
		  for (int j = 0; j < width; j++) {
			  gameArray [i][j] = tempGen.generate();
			  
		  }
	  }
  }

  /**
   * Constructs a game based on the given string array according to
   * the conventions of Util.createGridFromString. The given Generator 
   * instance is used to create new dots.  
   * 
   * @param data
   *          string indicating initial configuration of grid
   * @param generator
   *          generator for new icons
   */
  public DotsGame(String[] data, Generator generator)
  {
	  gameArray = Util.createGridFromString(data);
	  tempGen = generator;
	  list = new ArrayList<Descriptor>();
	  width = gameArray[0].length;
	  height = gameArray.length;
  } 
  
  /**
   * Returns the Dot object at the given row and column.
   * @param row
   *   row within the grid
   * @param col
   *   column within the grid
   * @return
   *   Dot object at the given row and column
   */
  public Dot getDot(int row, int col)
  {
    return gameArray[row][col];
  }

  /**
   * Sets the Dot object at the given row and column.
   * @param row
   *   row of the grid to be modified
   * @param col
   *   column of the grid to be modified
   * @param dot
   *   the given Dot object to set
   */
  public void setDot(int row, int col, Dot dot)
  {
	  gameArray[row][col] = dot;
  }

  /**
   * Returns the number of columns in this game.
   * @return
   *   number of columns
   */
  public int getWidth()
  {
    return gameArray[0].length;
  }

  /**
   * Returns the number of rows in this game.
   * @return
   *   number of rows
   */
  public int getHeight()
  {
    return gameArray.length;
  }

  /**
   * Returns the current score for this game.
   * @return
   *   score for this game
   */
  public int getScore()
  {
    return score;
  }

  /**
   * Attempts to select the dot at given position. A descriptor for the dot is
   * added to the selection list provided that a) the given position is
   * adjacent to the last one added to the selection list, and b) its type matches
   * the type of those already in the selection list, and c) the given position
   * is not already in the selection list OR it completes a loop.
   * Completing a loop means that the given position matches the first one in
   * the selection list, the list has length at least 3, and the given position does
   * not already occur twice in the list.
   * @param row
   *   row of the dot to be selected
   * @param col
   *   column of the dot to be selected
   */
  public void select(int row, int col)
  {  
	  Dot d = gameArray[row][col];
	  currentDot = new Descriptor (row, col, d);
	  
	  if (list.size() == 0) {
		  list.add(currentDot);
	  }
	  Descriptor D2 = list.get(list.size()-1);	
	  Dot d2 = D2.getDot();
	  boolean adjacent = isAdjacent();
	  boolean firstEqualsCurrent = list.get(0).equals(currentDot);
	  boolean longEnough = list.size() > 3;
	  boolean sameColor = d.getType() == d2.getType();//check type
	  	  
	  if (adjacent && sameColor && !list.contains(currentDot)) {
		  list.add(currentDot);
	  }
	  if (longEnough && adjacent && firstEqualsCurrent) {//check type & only ever add dot twice
		  completesLoop = true;
	  }	   
	  if (completesLoop) {
		  list.add(currentDot);
		  completesLoop = false;
	  }
  }
  
  /**
   * This method checks to see whether or not the currently selected dot is adjacent
   * to the previously selected dot. If they are adjacent, this method returns
   * true. If the two dots are not adjacent, this method returns false.
   * @return
   *   the boolean 'adjacent'. True if the dot is adjacent to the previous dot, false if otherwise.
   */
  private boolean isAdjacent() 
  {
	  boolean adjacent = false;
	  if (list.size() > 0) {
		  Descriptor D2 = list.get(list.size()-1);
		  
		  if (Math.abs(D2.row() - currentDot.row()) + Math.abs(D2.col() - currentDot.col()) == 1) {
			  adjacent = true;
		  }
		  else {
			  adjacent = false;
		  }
	  } return adjacent;
  }
  
  /**
   * Returns a list of descriptors for currently selected dots.
   * @return
   *   the selection list
   */
  public ArrayList<Descriptor> getSelectionList()
  {
    return list;
  }
    
  /**
   * If the selection list has at least two elements, replaces all selected positions 
   * with null, clears the selection list, and updates the score.  If the selection 
   * list does not contain at least two elements, no positions are nulled but the
   * selection list is still cleared.  If the selection list includes a completed loop,
   * then all dots of matching type are also nulled and the score is updated accordingly.  
   * The method returns a list containing all nulled positions.  (The list is in
   * no particular order but should not contain duplicates.)
   * @return
   *   list of descriptors for cells that are nulled as a result of this operation
   */
  public ArrayList<Descriptor> release() {
	  ArrayList<Descriptor> nulledList = new ArrayList<Descriptor>();
	  if (list.size() > 1) {
		  Descriptor D = list.get(0);
		  Descriptor D2 = list.get(list.size() - 1);
		  Dot d = D2.getDot();
		  int dotType = d.getType();
		  
		  if(D2.row() == D.row() && D2.col() == D.col()){
			  for (int i = 0; i < height; i++){
				  for (int j = 0; j < width; j++){
					  
					  Dot d2 = gameArray[i][j];
					  Descriptor D3 = new Descriptor(i, j, d2);
					  int dot2Type = d2.getType();
					  
					  if (dotType == dot2Type){
						  gameArray[i][j] = null;
						  nulledList.add(D3);
					  }
				  }
			  }list.clear();
		  }
		  else{
			  for (int i = 0; i < list.size(); i++) {
				  Descriptor D4 = list.get(i);
				  gameArray[D4.row()][D4.col()] = null;
				  nulledList.add(D4); 
			  }list.clear();
		  }
	  	}
	  else{
	  		list.clear();
	 }
	  	score += nulledList.size();
	  	return nulledList;
  }

  
  /**
   * Collapses the dots in the given column of the current game grid such 
   * that all null dots, if any, are at the top of the column 
   * and non-null dots are shifted toward the bottom (i.e., as if by gravity).  
   * The returned list contains Descriptors representing dots that were moved (if any)
   * with their new row and column; moreover, each Descriptor's <code>getPreviousRow</code>
   * method returns the original row of the dot.  The returned list is 
   * in no particular order.
   * @param col
   *   column to be collapsed
   * @return
   *   list of descriptors for moved dots
   */
  public ArrayList<Descriptor> collapseColumn(int col)
  {
	ArrayList<Descriptor> movedList = new ArrayList<Descriptor>();
	ArrayList<Descriptor> newLocal = new ArrayList<Descriptor>();
	int nullCounter = 0;
	for (int i = 0; i < height; i++) {
		boolean tempBool = false;
		if (gameArray[i][col] != null ) {
			for (int j = i + 1; j < height; j++ ) {
				if (gameArray[j][col] == null) {
					tempBool = true;
				}
			}
			if (tempBool) {
				Descriptor tempDesc = new Descriptor (i, col, gameArray[i][col]);
				movedList.add(tempDesc);
				tempDesc.setPreviousRow(i);
			}
	} else {
		nullCounter++;
	}
	}
    for (int i = 0; i < height; i++) {
    	for (int j = i; j > 0; j-- ) {
    		if(gameArray[j][col] == null) {
    			Dot tempDot = gameArray[j][col];
    			gameArray[j][col] = gameArray[j-1][col];
    			gameArray[j-1][col] = tempDot; 
    			}    			
    		}
    	}
    for (int i = 0; i < movedList.size() - 1; i++) {
    	Descriptor tempDesc2 = new Descriptor (i + nullCounter, col, gameArray[i][col]);
    	newLocal.add(tempDesc2);
    	
    }return newLocal;
    }
  

  
  /**
   * Fills the null grid positions (if any) at the top of the given column in the
   * current game grid.  The returned list contains Descriptors representing new
   * dots added to the column with their new row and column. The previous row
   * for all descriptors is set to -1. The new dots are
   * produced by the generator's <code>generate</code> method. The list is 
   * in no particular order.
   * 
   * @param col
   *   column to be filled
   * @return
   *   list of new descriptors for dots added to the column
   */
  public ArrayList<Descriptor> fillColumn(int col) 
  {
  ArrayList<Descriptor> list2 = new ArrayList<Descriptor>();
  for (int i = 0; i < gameArray.length; i++) {
	  if (gameArray[i][col] == null) {
		  gameArray[i][col] = tempGen.generate();
		  Dot d = new Dot(gameArray[i][col].getType());
		  Descriptor D = new Descriptor(i, col, d);
		  list2.add(D);
	  	}
  	} return list2;
 }


}
