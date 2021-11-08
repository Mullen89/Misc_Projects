import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

/**
 * @author Eric Mullen
 */

public class RobotPath {
    private String[][] grid;
    private HashSet<Point> pathPoints = new HashSet<>();
    private HashSet<Point> obstacles;
    private int numRow;
    private int numCol;
    private Point dest;
    private Point start;
    private int counter;
    private boolean pathExists;

    public RobotPath(){}

    public void readInput(String FileName) throws IOException {
        try {
            if(FileName != null){
                obstacles  = new HashSet<>();
                File file = new File(FileName);
                int x;
                int y;
                Scanner scan = new Scanner(file);

                /**
                 * Reads through and parses the file
                 */
                while(scan.hasNextLine()){
                    String inputFile = scan.nextLine();
                    String[] tokens = inputFile.split(" ");

                    /**
                     * Initializes the grid as all 0's in the numRow/numCol length
                     */
                    if(tokens[0].equals("nrows")){
                        numRow = Integer.parseInt(tokens[1]);
                        numCol = Integer.parseInt(tokens[3]);

                        /**
                         * Sets Start position
                         */
                    } else if(tokens[0].equals("start")){
                        x = Integer.parseInt(tokens[1]);
                        y = Integer.parseInt(tokens[2]);
                        start = new Point(x, y);

                        /**
                         * Sets Destination position
                         */
                    } else if(tokens[0].equals("dest")){
                        x = Integer.parseInt(tokens[1]);
                        y = Integer.parseInt(tokens[2]);
                        dest = new Point(x, y);

                        /**
                         * Sets obstacles
                         */
                    } else if(tokens.length == 2){
                        x = Integer.parseInt(tokens[0]);
                        y = Integer.parseInt(tokens[1]);
                        obstacles.add(new Point(x,y));
                    }
                }
                scan.close();
            } else {
                throw new IOException();
            }
        } catch(IOException e){
            System.out.println("ERROR: " + e);
        }
    }

    /**
     * Creates the grid which will be output
     * @return
     */
    public void createGrid(){
        grid = new String[numRow][numCol];
        for(String[] a : grid){
            Arrays.fill(a,"0");
        }
        /**
         * Sets start, destination, and populates grid
         * with obstacles
         */
        grid[start.x][start.y] = "S";
        grid[dest.x][dest.y] = "D";
        for(Point b : obstacles){
            grid[b.x][b.y] = "*";
        }

        /**
         * Populates grid with paths that can be taken to reach destination.
         * For use in planShortest().
         */
        if(!pathPoints.isEmpty()){
            for(Point c : pathPoints){
                grid[c.x][c.y] = "P";
            }
            pathPoints.clear();
        }
    }

    /**
     * Helper method that does basic BFS on the grid given
     * @param s
     *      the 2D String[][] grid to be passed
     * */
    public void BFS(Point s, Point d){
        /**
         * North, South, East, West coordinates
         */
        int[] x = {0,0,1,-1};
        int[] y = {1,-1,0,0};

        LinkedList<Point> q = new LinkedList<>();
        q.add(s);

        /**
         * 2D int[][] grid that stores the distances of each point on the grid
         * from the start
         */
        int[][] dist = new int[numRow][numCol];
        for(int[] a : dist){
            Arrays.fill(a,-1);
        }
        for(Point ob : obstacles){
            dist[ob.x][ob.y] = -2;
        }
        // Start point
        dist[s.x][s.y] = 0;

        /**
         * Loops over dist[][] from starting point, changing each [x][y] coordinate to the
         * value that is the distance from S.
         */
        while(!q.isEmpty()){                                        // n
            Point p = q.removeFirst();
            for(int i = 0; i < 4; i++){                             // n
                int a = p.x + x[i];
                int b = p.y + y[i];
                if(a >= 0 && b >= 0 && a < numRow && b < numCol && dist[a][b] == -1){
                    dist[a][b] = 1 + dist[p.x][p.y];
                    Point tempPoint = new Point(a, b);
                    q.add(tempPoint);
                }
            }
        }

        /**
         * Works backwards to find all shortest path points between S and D, and adds each
         * point to the pathPoints array
         */
        q.add(d);
        while(!q.isEmpty()){
            Point p = q.removeFirst();
            for(int i = 0; i < 4; i++){
                int curX = p.x + x[i];
                int curY = p.y + y[i];
                if(curX >= 0 && curY >= 0 && !(curX == start.x && curY == start.y) && curX < numRow && curY < numCol){
                    if(dist[curX][curY] < dist[p.x][p.y] && dist[curX][curY] != -2){
                        Point tempPoint = new Point(curX, curY);
                        if(!pathPoints.contains(tempPoint)){
                            pathPoints.add(tempPoint);
                            q.add(tempPoint);
                        }
                    }
                }
            }
        }
    }

    /**
     * This method runs a modified version of the shortest path algorithm of BFS.
     * First, it calls the helper method "BFS" from above, which runs BFS twice: first
     * from the start point to locate the destination point, then again from the destination
     * point in order to find all the paths of the same length to the start point, and labels
     * each of the points along the path as "P".
     *
     * The main portion of this method changes all the "P"s on the grid to the direction from
     * which one could travel along the path to reach the destination.
     *
     * Since this method utilizes the BFS helper, as well as utilizes its own BFS shortest path
     * algorithm, the runtime should be O(|V| + |E|).
     */
    public void planShortest(){
        /**
         * Constructs the dist array and populates the pathPoints array with the points
         * which can be traversed from S to D.
         */
        if(start.x == dest.x && start.y == dest.y){
            grid[start.x][start.y] = "SD";
        } else {
            BFS(start, dest);
            createGrid();

            LinkedList<Point> path = new LinkedList<>();
            path.add(start);

            while(!path.isEmpty()){
                Point p = path.removeFirst();
                String directions = "";
                int curX = p.x;
                int curY = p.y;
                int north = p.x - 1;
                int south = p.x + 1;
                int east = p.y + 1;
                int west = p.y - 1;

                if(north >= 0 && north < numRow){
                    if((!(grid[north][curY].equals("*")) && grid[north][curY].equals("P")) || grid[north][curY].equals("D")){
                        directions += "n";
                        if(!path.contains(new Point(north, curY))){
                            path.add(new Point(north,curY));
                        }
                    }
                }
                if(south >= 0 && south < numRow){
                    if((!(grid[south][curY].equals("*")) && grid[south][curY].equals("P")) || grid[south][curY].equals("D")){
                        directions += "s";
                        if(!path.contains(new Point(south, curY))){
                            path.add(new Point(south,curY));
                        }
                    }
                }
                if(east >= 0 && east < numCol){
                    if((!(grid[curX][east].equals("*")) && grid[curX][east].equals("P")) || grid[curX][east].equals("D")){
                        directions += "e";
                        if(!path.contains(new Point(curX, east))){
                            path.add(new Point(curX,east));
                        }
                    }
                }
                if(west >= 0 && west < numCol){
                    if((!(grid[curX][west].equals("*")) && grid[curX][west].equals("P")) || grid[curX][west].equals("D")){
                        directions += "w";
                        if(!path.contains(new Point(curX, west))){
                            path.add(new Point(curX,west));
                        }
                    }
                }
                if(!(grid[curX][curY].equals("S")) && !(grid[curX][curY].equals("D"))){
                    grid[curX][curY] = directions;
                }
            }
        }
    }

    /**
     * Finds the euclidean distance between two points on the grid
     */
    public int distance(int x, int y) {
        return (x-dest.x)*(x-dest.x) + (y-dest.y)*(y-dest.y);
    }

    public boolean checkValid(Point p){
        if((p.x >= 0 && p.y >= 0 && p.x < numRow && p.y < numCol) && !grid[p.x][p.y].equals("*") && (grid[p.x][p.y].equals("0") || grid[p.x][p.y].equals("D"))){
            return true;
        } else {
            return false;
        }
    }

    public Point smallest(Point n, Point s, Point e, Point w, HashSet<Point> h){
        HashSet<Point> temp = new HashSet<>(h);
        int distN = distance(n.x, n.y);
        int distS = distance(s.x, s.y);
        int distE = distance(e.x, e.y);
        int distW = distance(w.x, w.y);

        if(checkValid(n) && checkValid(w) && checkValid(e) && checkValid(s)){
            if(distN <= distW && distN <= distE && distN <= distS && isPathValid(n, temp)){
                return n;
            } else if(distW <= distE && distW <= distS && isPathValid(w, temp)){
                return w;
            } else if(distE <= distS && isPathValid(e, temp)){
                return e;
            } else if(isPathValid(s, temp)){
                return s;
            } else if(isPathValid(n, temp)){
                return n;
            } else if(isPathValid(w, temp)){
                return w;
            } else if(isPathValid(e, temp)){
                return e;
            }
        } else if(checkValid(n) && checkValid(w) && checkValid(e)){
            if(distN <= distW && distN <= distE && isPathValid(n, temp)){
                return n;
            } else if(distW <= distE && isPathValid(w, temp)){
                return w;
            } else if(isPathValid(e, temp)){
                return e;
            } else if(isPathValid(n, temp)){
                return n;
            } else if(isPathValid(w, temp)){
                return w;
            }
        } else if(checkValid(n) && checkValid(w) && checkValid(s)){
            if(distN <= distW && distN <= distS && isPathValid(n, temp)){
                return n;
            } else if(distW <= distS && isPathValid(w, temp)){
                return w;
            } else if(isPathValid(s, temp)){
                return s;
            } else if(isPathValid(n, temp)){
                return n;
            } else if(isPathValid(w, temp)){
                return w;
            }
        } else if(checkValid(n) && checkValid(e) && checkValid(s)){
            if(distN <= distE && distN <= distS && isPathValid(n, temp)){
                return n;
            } else if(distE <= distS && isPathValid(e, temp)){
                return e;
            } else if(isPathValid(s, temp)){
                return s;
            } else if(isPathValid(n, temp)){
                return n;
            } else if(isPathValid(e, temp)){
                return e;
            }
        } else if(checkValid(w) && checkValid(e) && checkValid(s)){
            if(distW <= distE && distW <= distS && isPathValid(w, temp)){
                return w;
            } else if(distE <= distS && isPathValid(e, temp)){
                return e;
            } else if(isPathValid(s, temp)){
                return s;
            } else if(isPathValid(w, temp)){
                return w;
            } else if(isPathValid(e, temp)){
                return e;
            }
        } else if(checkValid(n) && checkValid(w)){
            if(distN <= distW && isPathValid(n, temp)){
                return n;
            } else if(isPathValid(w, temp)){
                return w;
            } else if(isPathValid(n, temp)){
                return n;
            }
        } else if(checkValid(n) && checkValid(e)){
            if(distN <= distE && isPathValid(n, temp)){
                return n;
            } else if(isPathValid(e, temp)){
                return e;
            } else if(isPathValid(n, temp)){
                return n;
            }
        } else if(checkValid(n) && checkValid(s)){
            if(distN <= distS && isPathValid(n, temp)){
                return n;
            } else if(isPathValid(s, temp)){
                return s;
            } else if(isPathValid(n, temp)){
                return n;
            }
        } else if(checkValid(w) && checkValid(e)){
            if(distW <= distE && isPathValid(w, temp)){
                return w;
            } else if(isPathValid(e, temp)){
                return e;
            } else if(isPathValid(w, temp)){
                return w;
            }
        } else if(checkValid(w) && checkValid(s)){
            if(distW <= distS && isPathValid(w, temp)){
                return w;
            } else if(isPathValid(s, temp)){
                return s;
            } else if(isPathValid(w, temp)){
                return w;
            }
        } else if(checkValid(e) && checkValid(s)){
            if(distE <= distS && isPathValid(e, temp)){
                return e;
            } else if(isPathValid(s, temp)){
                return s;
            } else if(isPathValid(e, temp)){
                return e;
            }
        } else if(checkValid(n) && isPathValid(n, temp)){
            return n;
        } else if(checkValid(w) && isPathValid(w, temp)){
            return w;
        } else if(checkValid(e) && isPathValid(e, temp)){
            return e;
        } else if(checkValid(s) && isPathValid(s, temp)){
            return s;
        } else {
            return null;
        }
        return null;
    }

    public Point smallest2(Point n, Point s, Point e, Point w){
        int distN = distance(n.x, n.y);
        int distS = distance(s.x, s.y);
        int distE = distance(e.x, e.y);
        int distW = distance(w.x, w.y);

        if(checkValid(n) && checkValid(w) && checkValid(e) && checkValid(s)){
            if(distN <= distW && distN <= distE && distN <= distS){
                return n;
            } else if(distW <= distE && distW <= distS){
                return w;
            } else if(distE <= distS){
                return e;
            } else {
                return s;
            }
        } else if(checkValid(n) && checkValid(w) && checkValid(e)){
            if(distN <= distW && distN <= distE){
                return n;
            } else if(distW <= distE){
                return w;
            } else {
                return e;
            }
        } else if(checkValid(n) && checkValid(w) && checkValid(s)){
            if(distN <= distW && distN <= distS){
                return n;
            } else if(distW <= distS){
                return w;
            } else {
                return s;
            }
        } else if(checkValid(n) && checkValid(e) && checkValid(s)){
            if(distN <= distE && distN <= distS){
                return n;
            } else if(distE <= distS){
                return e;
            } else {
                return s;
            }
        } else if(checkValid(w) && checkValid(e) && checkValid(s)){
            if(distW <= distE && distW <= distS){
                return w;
            } else if(distE <= distS){
                return e;
            } else {
                return s;
            }
        } else if(checkValid(n) && checkValid(w)){
            if(distN <= distW){
                return n;
            } else {
                return w;
            }
        } else if(checkValid(n) && checkValid(e)){
            if(distN <= distE){
                return n;
            } else {
                return e;
            }
        } else if(checkValid(n) && checkValid(s)){
            if(distN <= distS){
                return n;
            } else {
                return s;
            }
        } else if(checkValid(w) && checkValid(e)){
            if(distW <= distE){
                return w;
            } else {
                return e;
            }
        } else if(checkValid(w) && checkValid(s)){
            if(distW <= distS){
                return w;
            } else {
                return s;
            }
        } else if(checkValid(e) && checkValid(s)){
            if(distE <= distS){
                return e;
            } else {
                return s;
            }
        } else if(checkValid(n)){
            return n;
        } else if(checkValid(w)){
            return w;
        } else if(checkValid(e)){
            return e;
        } else if(checkValid(s)){
            return s;
        } else {
            return null;
        }
    }

    public boolean isPathValid(Point p, HashSet<Point> t){
        HashSet<Point> set = new HashSet<Point>(t);
        Point n = new Point(p.x-1, p.y);
        Point s = new Point(p.x+1, p.y);
        Point e = new Point(p.x, p.y+1);
        Point w = new Point(p.x, p.y-1);
        int distN = distance(n.x, n.y);
        int distS = distance(s.x, s.y);
        int distE = distance(e.x, e.y);
        int distW = distance(w.x, w.y);
        set.add(p);

        if(p.x == dest.x && p.y == dest.y){
            pathExists = true;
            return true;

            // Used to prevent stack overflow error
        } else if(counter == 100) {
            return true;
        } else if(pathExists) {
            return true;
        } else {
            if(checkValid(n) && checkValid(w) && checkValid(e) && checkValid(s) && !set.contains(n) && !set.contains(s) && !set.contains(e) && !set.contains(w)){
                if(distN <= distW && distN <= distE && distN <= distS){
                    set.add(n);
                    counter++;
                    return isPathValid(n, set);
                } else if(distW <= distE && distW <= distS){
                    set.add(w);
                    counter++;
                    return isPathValid(w, set);
                } else if(distE <= distS){
                    set.add(e);
                    counter++;
                    return isPathValid(e, set);
                } else {
                    set.add(s);
                    counter++;
                    return isPathValid(s, set);
                }
            } else if(checkValid(n) && checkValid(w) && checkValid(e) && !set.contains(n) && !set.contains(w) && !set.contains(e)){
                if(distN <= distW && distN <= distE){
                    set.add(n);
                    counter++;
                    return isPathValid(n, set);
                } else if(distW <= distE){
                    set.add(w);
                    counter++;
                    return isPathValid(w, set);
                } else {
                    set.add(e);
                    counter++;
                    return isPathValid(e, set);
                }
            } else if(checkValid(n) && checkValid(w) && checkValid(s) && !set.contains(n) && !set.contains(w) && !set.contains(s)){
                if(distN <= distW && distN <= distS){
                    set.add(n);
                    counter++;
                    return isPathValid(n, set);
                } else if(distW <= distS){
                    set.add(w);
                    counter++;
                    return isPathValid(w, set);
                } else {
                    set.add(s);
                    counter++;
                    return isPathValid(s, set);
                }
            } else if(checkValid(n) && checkValid(e) && checkValid(s) && !set.contains(n) && !set.contains(e) && !set.contains(s)){
                if(distN <= distE && distN <= distS){
                    set.add(n);
                    counter++;
                    return isPathValid(n, set);
                } else if(distE <= distS){
                    set.add(e);
                    counter++;
                    return isPathValid(e, set);
                } else {
                    set.add(s);
                    counter++;
                    return isPathValid(s, set);
                }
            } else if(checkValid(w) && checkValid(e) && checkValid(s) && !set.contains(w) && !set.contains(e) && !set.contains(s)){
                if(distW <= distE && distW <= distS){
                    set.add(w);
                    counter++;
                    return isPathValid(w, set);
                } else if(distE <= distS){
                    set.add(e);
                    counter++;
                    return isPathValid(e, set);
                } else {
                    set.add(s);
                    counter++;
                    return isPathValid(s, set);
                }
            } else if(checkValid(n) && checkValid(w) && !set.contains(n) && !set.contains(w)){
                if(distN <= distW){
                    set.add(n);
                    counter++;
                    return isPathValid(n, set);
                } else {
                    set.add(w);
                    counter++;
                    return isPathValid(w, set);
                }
            }else if(checkValid(n) && checkValid(e) && !set.contains(n) && !set.contains(e)){
                if(distN <= distE){
                    set.add(n);
                    counter++;
                    return isPathValid(n, set);
                } else {
                    set.add(e);
                    counter++;
                    return isPathValid(e, set);
                }
            } else if(checkValid(n) && checkValid(s) && !set.contains(n) && !set.contains(s)){
                if(distN <= distS){
                    set.add(n);
                    counter++;
                    return isPathValid(n, set);
                } else {
                    set.add(s);
                    counter++;
                    return isPathValid(s, set);
                }
            } else if(checkValid(w) && checkValid(e) && !set.contains(e) && !set.contains(w)){
                if(distW <= distE){
                    set.add(w);
                    counter++;
                    return isPathValid(w, set);
                } else {
                    set.add(e);
                    counter++;
                    return isPathValid(e, set);
                }
            } else if(checkValid(w) && checkValid(s) && !set.contains(s) && !set.contains(w)){
                if(distW <= distS){
                    set.add(w);
                    counter++;
                    return isPathValid(w, set);
                } else {
                    set.add(s);
                    counter++;
                    return isPathValid(s, set);
                }
            } else if(checkValid(e) && checkValid(s) && !set.contains(e) && !set.contains(s)){
                if(distE <= distS){
                    set.add(e);
                    counter++;
                    return isPathValid(e, set);
                } else {
                    set.add(s);
                    counter++;
                    return isPathValid(s, set);
                }
            } else if(checkValid(n) && !set.contains(n)){
                set.add(n);
                counter++;
                return isPathValid(n, set);
            } else if(checkValid(w) && !set.contains(w)){
                set.add(w);
                counter++;
                return isPathValid(w, set);
            } else if(checkValid(e) && !set.contains(e)){
                set.add(e);
                counter++;
                return isPathValid(e, set);
            } else if(checkValid(s) && !set.contains(s)){
                set.add(s);
                counter++;
                return isPathValid(s, set);
            } else {
                return false;
            }
        }
    }

    /**
     * Okay so this is not my proudest work, but it seems to work correctly and efficiently enough.
     * Basically it calls 5 helper functions: distance(), checkValid(), smallest(), smallest2(), and isPathValid()
     * to check if there is a way to get to D from S.
     *
     * To begin, it sets Point "cur" to S, and checks its neighbors. If any neighbor is a valid location, it grabs the
     * distance to D from each location. It then calls "smallest()" on that location which recursively checks to see
     * if D can be reached from that location. If yes, it sets a boolean value that will remove the recursive aspect
     * of "smallest()", and will go through the grid until it reaches D.
     *
     * If "smallest()" returns null, that means that there is no path to D, however, if the "counter" variable is > 0,
     * then that means there was at least 1 space which was reached before the function returned null, so maybe D is
     * reachable, but we just didn't choose the correct path. So in this case, "smallest2" is called, which moves our
     * robot one grid at a time in the while loop. This removes a failure possibility if there is a path close to D,
     * but is a dead-end, since we can't back-track.
     *
     * Not gonna lie, this code is very messy, but "smallest()", "smallest2()", and "isPathValid()" are all very
     * similar.
     */
    public void quickPlan() {
        createGrid();
        boolean reachedD = false;
        if (start.x == dest.x && start.y == dest.y) {
            grid[start.x][start.y] = "SD";
        } else {
            Point cur = start;
            HashSet<Point> visited = new HashSet<>();
            LinkedList<Point> q = new LinkedList<>();
            q.add(cur);
            visited.add(cur);

            while (!q.isEmpty() && !reachedD) {
                Point p = q.removeFirst();
                cur = p;
                Point north = new Point(p.x - 1, p.y);
                Point south = new Point(p.x + 1, p.y);
                Point east = new Point(p.x, p.y + 1);
                Point west = new Point(p.x, p.y - 1);
                counter = 0;
                p = smallest(north, south, east, west, visited);
                visited.add(p);

                /**
                 * D might be reached from S, so iterate one space at a time to make sure we're not running
                 * into any dead-ends.
                 */
                if (p == null && counter >= 1) {
                    p = cur;
                    cur = p;
                    north = new Point(p.x - 1, p.y);
                    south = new Point(p.x + 1, p.y);
                    east = new Point(p.x, p.y + 1);
                    west = new Point(p.x, p.y - 1);
                    p = smallest2(north, south, east, west);
                    visited.add(p);
                    if (!(cur.x == start.x && cur.y == start.y)) {
                        if (p.x == north.x && p.y == north.y) {
                            grid[cur.x][cur.y] = "n";

                            // If south is closest
                        } else if (p.x == south.x && p.y == south.y) {
                            grid[cur.x][cur.y] = "s";

                            // If west is closest
                        } else if (p.x == west.x && p.y == west.y) {
                            grid[cur.x][cur.y] = "w";

                            // If east closest
                        } else if (p.x == east.x && p.y == east.y) {
                            grid[cur.x][cur.y] = "e";
                        }
                    }
                    counter = 0;

                    /**
                     * D cannot be reached from S
                     */
                } else if(p == null){
                    break;

                    /**
                     * Path to D found, continue iterating.
                     */
                } else if (!(cur.x == start.x && cur.y == start.y)) {
                    if (p.x == north.x && p.y == north.y) {
                        grid[cur.x][cur.y] = "n";

                        // If south is closest
                    } else if (p.x == south.x && p.y == south.y) {
                        grid[cur.x][cur.y] = "s";

                        // If west is closest
                    } else if (p.x == west.x && p.y == west.y) {
                        grid[cur.x][cur.y] = "w";

                        // If east closest
                    } else if (p.x == east.x && p.y == east.y) {
                        grid[cur.x][cur.y] = "e";
                    }
                    counter = 0;
                }
                if (grid[p.x][p.y].equals("D")) {
                    reachedD = true;
                } else {
                    q.add(p);
                }
            }
            /**
            If no valid path is found, in order to prevent location directions from showing up on an invalid grid,
             this just recreates the grid with all 0's, *'s, S, and D.
             */
            if (!reachedD) {
                createGrid();
            }
            pathExists = false;
            counter = 0;
        }
    }

    public void output(){
        for(int i = 0; i < numRow; i++){
            for(int j = 0; j < numCol; j++){
                System.out.printf("%5s", grid[i][j]);
            }
            System.out.println();
        }
    }
}