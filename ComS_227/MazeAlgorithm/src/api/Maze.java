package api;

import java.awt.Point;

/**
 * Maze consisting of cells arranged in a 2D grid.
 */
public class Maze
{
  /**
   * The cells for this maze.
   */
  private MazeCell[][] cells;
  
  /**
   * Starting row.
   */
   private int startRow;
   
   /**
    * Starting column.
    */
   private int startCol;

  /**
   * Constructs a maze based on a 2D grid.  The given strings
   * represent rows of the maze, where '#' represents a wall,
   * a blank represents a possible path, 'S' represents the 
   * starting cell, and '$' represents the goal.
   * @param rows
   *   array of strings, one per row of the maze
   */
  public Maze(String[] rows)
  {
    int width = rows[0].length();
    int height = rows.length;
    cells = new MazeCell[height][width];
    for (int row = 0; row < height; ++row)
    {
      String s = rows[row];
      for (int col = 0; col < width; ++col)
      {
        MazeCell current = new MazeCell();
        char c = s.charAt(col);
        if (c == '#')
        {
          current.setStatus(Status.WALL);
        }
        else if (c == '$')
        {
          current.setStatus(Status.GOAL);
        }
        else if (c == 'S')
        {
          current.setStatus(Status.UNEXPLORED);
          startRow = row;
          startCol = col;
        }
        else 
        {
          current.setStatus(Status.UNEXPLORED);
        }  
        cells[row][col] = current;
      }
    }
  }

  /**
   * Returns the cell at the given position.
   * @param row
   * @param col
   * @return
   */
  public MazeCell getCell(int row, int col)
  {
    return cells[row][col];
  }
  
  /**
   * Returns the number of rows in the grid for this maze.
   * @return
   */
  public int getHeight()
  {
    return cells.length;
  }
  
  /**
   * Returns the number of columns in the grid for this maze.
   * @return
   */
  public int getWidth()
  {
    return cells[0].length;
  }
  
  /**
   * Returns row of the starting cell for this maze.
   * @return
   *   starting row
   */
  public int getStartRow()
  {
    return startRow;
  }
  
  /**
   * Returns column of the starting cell for this maze.
   * @return
   *   starting column
   */
  public int getStartColumn()
  {
    return startCol;
  }
  
  /**
   * Set an observer for all cells in this maze.  When a 
   * cell's status changes, the observer's statusChanged 
   * method is called.
   * @param givenObserver
   *   an instance of MazeObserver
   */
  public void setObserver(MazeObserver givenObserver)
  {
    for (int row = 0; row < cells.length; row += 1)
    {
      for (int col = 0; col < cells[0].length; col += 1)
      {
        cells[row][col].setObserver(givenObserver);
      }
    }
  }
}
