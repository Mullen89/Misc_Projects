package edu.iastate.cs228.hw5.shared;

import java.util.ArrayList; 

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class PathFinder {

    /**
     * This member always holds the cost of the path (if any)
     * found by the most recently finished solving operation. 
     * MIN_VALUE is used to signal that the value is not yet valid. 
     */
    public static int lastCost = Integer.MIN_VALUE;
	/**
	 * First, computes a shortest path from a source vertex to a destination
	 * vertex in a graph by using Dijkstra's algorithm. Second, visits and saves
	 * (in a stack) each vertex in the path, in reverse order starting from the
	 * destination vertex, by using the map object pred. Third, uses a
	 * List and Stack to generate the return Integer List by first pushing 
	 * each vertex into the stack, and then poping vertices 
	 * from the stack and adding the index of each to the 
	 * return list. The vertex indices in the return object are now in the
	 * right order. Note that the getIndex() method is called from a
	 * BareV object (vertex) to get its original integer name.
	 *
	 * @param G
	 *            - The graph in which a shortest path is to be computed
	 * @param source
	 *            - The first vertex of the shortest path
	 * @param dest
	 *            - The last vertex of the shortest path
	 * @return A List of Integers corresponding the the vertices on the path
	 *         in order from source to dest. 
	 *
	 *         The contents of an example String object: Path Length: 5 Path
	 *         Cost: 4 Path: 0 4 2 5 7 9
	 *
	 * @throws NullPointerException
	 *             - If any arugment is null
	 *
	 * @throws RuntimeException
	 *             - If the given source or dest vertex is not in the graph
	 *
	 */

    public static List<Integer> findPath(BareG g, BareV source, BareV dest) {
          lastCost = Integer.MIN_VALUE;
          
          //TODO: implement dijkstra's shortest path algorithm. Use
          // the supplied heap, and stack. 
          // you may also use HashMap and HashSet from JCF. 
          // the following is only here so that the app will run (but not
          // product correct results when first unpacked from the templates.
          
          System.out.format("findPath was called with start=%d, dest=%d%n"
                  + "Now you need to give it a real implementation.%n",
                  source.getIndex(), dest.getIndex());
          return null; 
        }  
    
    /**
     * A pair class with two components of types V and C, where V is a vertex
     * type and C is a cost type.
     */

    private static class Vpair<V, C extends Comparable<? super C>> implements
            Comparable<Vpair<V, C>> {
        private V node;
        private C cost;

        Vpair(V n, C c) {
            node = n;
            cost = c;
        }

        public V getVertex() {
            return node;
        }

        public C getCost() {
            return cost;
        }

        public int compareTo(Vpair<V, C> other) {
            return cost.compareTo(other.getCost());
        }

        public String toString() {
            return "<" + node.toString() + ", " + cost.toString() + ">";
        }

        public int hashCode() {
            return node.hashCode();
        }

        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if ((obj == null) || (obj.getClass() != this.getClass()))
                return false;
            // object must be Vpair at this point
            Vpair<?, ?> test = (Vpair<?, ?>) obj;
            return (node == test.node || (node != null && node
                    .equals(test.node)));
        }
    }


}
