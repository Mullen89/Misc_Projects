
package mini2;

import api.Maze;
import api.MazeCell;
import api.Status;

/**
 * Utility class for searching a maze described by a collection
 * of MazeCell objects.
 */
public class Searcher
{
  private static boolean goalFound = false;
  /**
   * Recursively searches a given MazeCell and all of its unexplored 
   * neighbors.  Returns true if the current cell is the goal or if
   * the goal is found in a search initiated from this cell by 
   * searching a neighbor.  Returns false if this cell is not
   * unexplored and is not the goal.
   * 
   * @param maze
   *   the maze to be searched
   * @param row
   *   the row for the current cell being searched
   * @param col
   *   the column for the current cell being searched
   * @return
   *   true if a search from the current cell has reached the goal,
   *   false otherwise
   */
  public static boolean search(Maze maze, int row, int col)
  {
   MazeCell currentCell = maze.getCell(row, col);
   MazeCell up = maze.getCell(row-1, col);
   MazeCell down = maze.getCell(row+1, col);
   MazeCell left = maze.getCell(row, col-1);
   MazeCell right = maze.getCell(row, col+1);
    
    if(currentCell.getStatus() == Status.GOAL){
    	return true;
    }
    else if(currentCell.getStatus() == Status.WALL || currentCell.getStatus() != Status.UNEXPLORED){
    	return false;
    }
    else{
    	if(up.getStatus() != Status.WALL){
    		if(up.getStatus() == Status.GOAL || up.getStatus() == Status.SUCCEEDED || down.getStatus() == Status.SUCCEEDED){
    			currentCell.setStatus(Status.SUCCEEDED);
    			goalFound = true;
    			return true;
    		}
    		else if(goalFound != true){
        		currentCell.setStatus(Status.EXPLORING_UP);
        		search(maze, row-1, col);
    		}
    	}
    	if(down.getStatus() != Status.WALL){
    		if(down.getStatus() == Status.GOAL || down.getStatus() == Status.SUCCEEDED || up.getStatus() == Status.SUCCEEDED){
    			currentCell.setStatus(Status.SUCCEEDED);
    			goalFound = true;
    			return true;
    		}
    		else if(goalFound != true){
        		currentCell.setStatus(Status.EXPLORING_DOWN);
        		search(maze, row+1, col);
    		}
    	}
    	if(left.getStatus() != Status.WALL){
    		if(left.getStatus() == Status.GOAL || left.getStatus() == Status.SUCCEEDED || right.getStatus() == Status.SUCCEEDED){
    			currentCell.setStatus(Status.SUCCEEDED);
    			goalFound = true;
    			return true;
    		}
    		else if(goalFound != true){
        		currentCell.setStatus(Status.EXPLORING_LEFT);
        		search(maze, row, col-1);
    		}
    	}
    	if(right.getStatus() != Status.WALL){
    		if(right.getStatus() == Status.GOAL || right.getStatus() == Status.SUCCEEDED || left.getStatus() == Status.SUCCEEDED){
    			currentCell.setStatus(Status.SUCCEEDED);
    			goalFound = true;
    			return true;
    		}
    		else if(goalFound != true){
        		currentCell.setStatus(Status.EXPLORING_RIGHT);
        		search(maze, row, col+1);
    		}
    	}
    	if(right.getStatus() != Status.UNEXPLORED || left.getStatus() != Status.UNEXPLORED || up.getStatus() != Status.UNEXPLORED || down.getStatus() != Status.UNEXPLORED){
    		if(up.getStatus() == Status.SUCCEEDED){
    			currentCell.setStatus(Status.SUCCEEDED);
    			goalFound = true;
    			return true;
    		}
    		else if(down.getStatus() == Status.SUCCEEDED){
    			currentCell.setStatus(Status.SUCCEEDED);
    			goalFound = true;
    			return true;
    		}
    		else if(left.getStatus() == Status.SUCCEEDED){
    			currentCell.setStatus(Status.SUCCEEDED);
    			goalFound = true;
    			return true;
    		}
    		else if(right.getStatus() == Status.SUCCEEDED){
    			currentCell.setStatus(Status.SUCCEEDED);
    			goalFound = true;
    			return true;
    		}
    		else{
    			
    		}
    	}
    	currentCell.setStatus(Status.FAILED);
    	return false;
    }
  }
}
