package api;

/**
 * Container for a 
 */
public class MazeCell 
{
  /**
   * Status of this cell.
   */
  private Status status;
  
  /**
   * Observer to be notified of changes in this cell's status.
   */
  private MazeObserver observer;
  
  /**
   * Constructs a maze cell at the given location with 
   * status WALL.
   */
  public MazeCell()
  {
    status = Status.WALL;
    observer = null;
  }

  /**
   * Sets the status of this cell.
   * @param s
   *   new status for the cell
   */
  public void setStatus(Status s)
  {
    status = s;
    if (observer != null)
    {
      observer.statusChanged(this);
    }
  }

  /**
   * Returns the status of this cell.
   * @return
   *   status of this cell
   */
  public Status getStatus()
  {
    return status;
  }

  /**
   * Set an observer for this cell.  When the 
   * cell's status changes, the observer's statusChanged 
   * method is called.
   * @param givenObserver
   *   an instance of MazeObserver
   */
  public void setObserver(MazeObserver givenObserver)
  {
    observer = givenObserver;
  }
  
}
