package ui;


import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.SwingWorker;

import api.Maze;
import api.MazeCell;
import api.MazeObserver;
import api.Status;
import mini2.Searcher;

/**
 * UI for a maze search application.  A client (in this case the 
 * SwingWorker thread) will periodically call
 * updateStatus to trigger a repaint of the maze.  
 */
public class MazePanel extends JPanel implements MazeObserver
{
  /**
   * Size in pixels of the cells for the grid.
   */
  public static final int cellSize = 20;
  
  /**
   * The grid to be displayed by this panel.
   */
  private Maze maze; 
  
  /**
   * Delay between animation steps, in milliseconds.
   */
  private int sleepTime;
  
  /**
   * Constructs a panel to display the given maze, using 
   * the given value <code>sleepTime</code> as a delay 
   * between frames.
   */
  public MazePanel(Maze maze, int sleepTime)
  {
    this.maze = maze;
    this.sleepTime = sleepTime;
  }
  
  /**
   * Start up the maze search algorithm using an
   * auxiliary thread.
   */
  public void startSearcher()
  {
    maze.setObserver(this);
    new SearcherThread().execute();
  }


  /**
   * Triggers a repaint and pauses the auxiliary execution
   * thread for 'sleepTime' milliseconds.
   */
  @Override
  public void statusChanged(MazeCell cell)
  {
    repaint();
    try
    {
      Thread.sleep(sleepTime);
    }
    catch (InterruptedException shouldNotHappen)
    {
    }    
  }

  @Override
  public void paintComponent(Graphics g)
  {
    // clear background
    g.clearRect(0, 0, getWidth(), getHeight());
    
    for (int row = 0; row < maze.getHeight(); ++row)
    {
      for (int col = 0; col < maze.getWidth(); ++col)
      {
        Color color = getColor(row, col);
        g.setColor(color);
        g.fillRect(col * cellSize, row * cellSize, cellSize, cellSize);
      }
    }
    
    // draw grid
    g.setColor(Color.WHITE);
    for (int row = 0; row < maze.getHeight(); ++row)
    {
      for (int col = 0; col < maze.getWidth(); ++col)
      {
        g.drawRect(col * cellSize, row * cellSize, cellSize, cellSize);
      }
    }

    // draw arrows
    if (sleepTime > 0)
    {
      drawArrows(g);
    }
    
   }
  
  /**
   * Returns a color for cells with the given Status.
   * @param status
   * @return
   */
  private Color getColor(int row, int col)
  {
    MazeCell m = maze.getCell(row, col);
    if (m == null) return Color.WHITE;
    if (maze.getStartColumn() == col && maze.getStartRow() == row) return Color.BLUE;
    switch(m.getStatus())
    {     
      case WALL: return Color.BLACK;
      case UNEXPLORED: return Color.LIGHT_GRAY;
      case EXPLORING_UP:
      case EXPLORING_LEFT:
      case EXPLORING_DOWN:
      case EXPLORING_RIGHT: return Color.ORANGE;
      case FAILED: return Color.RED;
      case SUCCEEDED: return Color.GREEN;
      case GOAL: return Color.YELLOW;
    }
    return Color.WHITE;
  }
  
  /**
   * Returns true if c is in one of the EXPLORING states.
   * @param c
   * @return
   */
  private boolean isExploring(MazeCell c)
  {
    Status s = c.getStatus();
    return (s == Status.EXPLORING_UP || s == Status.EXPLORING_LEFT
        || s == Status.EXPLORING_DOWN || s == Status.EXPLORING_RIGHT);
  }
  
  /**
   * Draws a line from each cell being explored to the adjacent cell being
   * explored.
   * @param g
   */
  private void drawArrows(Graphics g)
  {
    g.setColor(Color.BLACK);
    ((Graphics2D)g).setStroke(new BasicStroke(2.0f));

    for (int row = 0; row < maze.getHeight(); ++row)
    {
      for (int col = 0; col < maze.getWidth(); ++col)
      {
        MazeCell c = maze.getCell(row, col);
        {
          if (isExploring(c))
          {
            int x = col * cellSize + cellSize / 2; // - (maze.getColumns() / 2);
            int y = row * cellSize + cellSize / 2; // + (maze.getRows() / 2);
            int x2 = x;
            int y2 = y;
            switch (c.getStatus())
            {
              case EXPLORING_UP:
                y2 = y - cellSize;
                break;
              case EXPLORING_LEFT:
                x2 = x - cellSize;
                break;
              case EXPLORING_DOWN:
                y2 = y + cellSize;
                break;
              case EXPLORING_RIGHT:
                x2 = x + cellSize;
                break;
              default:
            }
            g.drawLine(x, y, x2, y2);
          }
        }
      }
    } 
  }


  /**
   * Auxiliary thread runs the search method.
   */
  private class SearcherThread extends SwingWorker<Void, Void>
  {

    @Override
    protected Void doInBackground() throws Exception
    {
      // TODO Auto-generated method stub
      Searcher.search(maze, maze.getStartRow(), maze.getStartColumn());

      return null;
    }    
  }

}
