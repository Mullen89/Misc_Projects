package api;

/**
 * Constants representing possible states of a maze cell in
 * a recursive search.
 */
public enum Status
{
  WALL, 
  GOAL,
  UNEXPLORED, 
  EXPLORING_UP, 
  EXPLORING_DOWN, 
  EXPLORING_LEFT, 
  EXPLORING_RIGHT, 
  FAILED, 
  SUCCEEDED
}
