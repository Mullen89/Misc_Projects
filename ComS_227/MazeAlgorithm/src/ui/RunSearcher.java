package ui;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import api.Maze;

public class RunSearcher
{

  public static final String[] MAZE0 = {
    "###",
    "#S#",
    "###",
  };
  
  public static final String[] MAZE1 = {
    "#######",
    "#  #$ #",
    "# ### #",
    "#   # #",
    "# ### #",
    "#     #",
    "#S#####",
    "#######",
};

  public static final String[] MAZE2 = {
    "#########",
    "#  #$ # #",
    "# ### # #",
    "#   # # #",
    "# ###   #",
    "#     ###",
    "#S###   #",
    "#########",
};
  
  public static final String[] MAZE3 = {
    "#######",
    "#  #  #",
    "# ### #",
    "#   # #",
    "# ### #",
    "#    S#",
    "#######",
};
  
  public static final String[] MAZE4 = {
    "#######",
    "#     #",
    "# ### #",
    "#     #",
    "# ### #",
    "#     #",
    "#S# # #",
    "#######",
  };

  // maze posted by Anthoni Weise on Piazza, slightly modified 
  // so the solution is unique
  public static final String[] MAZE5= {
  "#############################",
  "#                          S#",
  "### #### # ## # ### # # ## ##",
  "# #    # #    #     ##  ##  #",
  "# ###### ###########   #   ##",
  "#        #           ### ## #",
  "########### # #### # # #    #",
  "#           #    # #   # ####",
  "# # # # ### # ## # ##### #  #",
  "#   ### # # #    #    ## #  #",
  "## #  # # # ######### ## ## #",
  "#  ## # # #       # # ##    #",
  "##    # #   ### # # # ##    #",
  "#  ###### # # # # #   #### ##",
  "# #   #       # # ###### ####",
  "# # #### #### ###      #   ##",
  "#   #  # #      ###### # #  #",
  "#   # ## ######    # #    # #",
  "#   #  #       ###   # #### #",
  "#   # ########## ##### #    #",
  "### #                  ######",
  "# ###### # ###########      #",
  "#        #        # ####### #",
  "# ################# #     # #",
  "#                 # ### ### #",
  "# # # ######### #     #     #",
  "## # #   #   # ## ## ########",
  "#$     #   #      #         #",
  "#############################",
};
  
  
  public static void main(String[] args)
  {
    // Create the maze from the string description
    final Maze maze = new Maze(MAZE5);
    
    // Edit here to change the delay between frames (milliseconds)
    final int sleepTime = 10;

    Runnable r = new Runnable()
    {
      public void run()
      {
        createAndShow(maze, sleepTime);
      }
    };
    SwingUtilities.invokeLater(r);
  }

  protected static void createAndShow(final Maze maze, final int sleepTime)
  {

    // create the frame
    JFrame frame = new JFrame("Maze Searcher");

    // create an instance of our JPanel subclass and
    // add it to the frame
    MazePanel panel = new MazePanel(maze, sleepTime);
    frame.getContentPane().add(panel);
    panel.setPreferredSize(new Dimension(maze.getWidth() * MazePanel.cellSize,
        maze.getHeight() * MazePanel.cellSize));

    // give it a nonzero size
    frame.pack();
    // frame.setSize(300, 100);

    // we want to shut down the application if the
    // "close" button is pressed on the frame
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // make the frame visible and start the UI machinery
    frame.setVisible(true);

    // then start the searcher
    panel.startSearcher();
  }

}
