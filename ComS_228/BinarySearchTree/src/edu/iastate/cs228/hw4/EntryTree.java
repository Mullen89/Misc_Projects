package edu.iastate.cs228.hw4;

import java.util.Arrays;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.io.File;

/**
 * @author Eric Mullen
 *
 * An entry tree class
 * Add Javadoc comments to each method
 */
public class EntryTree<K, V> {
	/**
	 * dummy root node made public for grading
	 */
	protected Node root;
	
	/**
	 * prefixlen is the largest index such that the keys in the subarray keyarr
	 * from index 0 to index prefixlen - 1 are, respectively, with the nodes on
	 * the path from the root to a node. prefixlen is computed by a private
	 * method shared by both search() and prefix() to avoid duplication of code.
	 */
	protected int prefixlen;
	/**
	 * the K[] returned by prefix
	 */
	private K[] list;
	/**
	 * val is the value that is returned by the "search" and "remove" methods.
	 */
	private V val;
	/**
	 * val2 is value returned by a prefix helper method
	 */
	private K val2;
	/**
	 * added is the value (true or false) that is returned by the "add" method.
	 */
	private boolean added;
	
	protected class Node implements EntryNode<K, V> {
		protected Node child; // link to the first child node
		protected Node parent; // link to the parent node
		protected Node prev; // link to the previous sibling
		protected Node next; // link to the next sibling
		protected K key; // the key for this node
		protected V value; // the value at this node

		public Node(K aKey, V aValue) {
			key = aKey;
			value = aValue;
			child = this.child;
			parent = this.parent;
			prev = this.prev;
			next = this.next;
		}

		@Override
		public EntryNode<K, V> parent() {
			// TODO Auto-generated method stub
			return parent;
		}

		@Override
		public EntryNode<K, V> child() {
			// TODO Auto-generated method stub
			return child;
		}

		@Override
		public EntryNode<K, V> next() {
			// TODO Auto-generated method stub
			return next;
		}

		@Override
		public EntryNode<K, V> prev() {

			return prev;
		}

		@Override
		public K key() {

			return key;
		}

		@Override
		public V value() {

			return value;
		}
	}

	public EntryTree() {
		root = new Node(null, null);
	}

	/**This method searches through the tree for the key at the end of the K[] and returns its value and 
	 * 
	 * The child (if it exists) of the root node
	 * @param n
	 * the K[] given
	 * @param karr
	 * index number for each value in K[]
	 * @param idx
	 * the value of the key in K[]
	 * @return
	 */
	private V searchHelper(Node n, K[] karr, int idx) {
		if(karr == null || karr.length == 0) {
			return val = null;
		}
		if(karr[idx] == null) {
			throw new NullPointerException();
		}
		if(n.value == null && n.key == null) {					//Checks if root node				//Starts at child if at root node
			if(n.child == null){
				return val = null;
			}
			else if(n.child != null && idx <= karr.length-1) {
				searchHelper(n.child, karr, idx);
			}
			return val;
		}
//*****************************************BEGIN SEARCH FUNCTION BELOW***************************************************************
		if(n.key != karr[idx]) {									//If key doesn't match keyarr, calls method on next,
			if(n.next != null) {
				searchHelper(n.next, karr, idx);
			}else{													//Or if next doesn't exists, returns null
				return val = null;
			}
			return val;
		}
		if(n.key == karr[idx] && idx < karr.length-1) {			//If key matches keyarr and is not the last key in keyarr,
			if(n.child != null) {									//Method is called on node's child with the next key and keyarr
				searchHelper(n.child, karr, idx+1);
			}else{													//Or if node doesn't have a child, returns null as key
				return val = null;									//doesn't exist
			}
			return val;
		}
		if(n.key == karr[idx] && idx == karr.length-1) {			//If key matches keyarr and is last key in keyarr,
			return val = n.value;									//returns the value of the node
		}
		return val;													
	}
//*********************************************END SEARCH FUNCTION******************************************************************
	/**
	 * Returns the value of the entry with a specified key sequence, or null if
	 * this tree contains no entry with the key sequence.
	 * 
	 * @param keyarr
	 * @return
	 */
	public V search(K[] keyarr) {
		return searchHelper(root, keyarr, 0);
	}
//**********************************************************************************************************************************
	private K[] prefixHelper(Node n, K[] karr, int idx) {
		if(karr == null || karr.length == 0) {
			return list = null;
		}
		if(karr[idx] == null) {
			throw new NullPointerException();
		}
		if(n.key == null && n.value == null) {					//Checks if root node
			if(n.child == null) {
				return list = null;
			}
			else if(n.key == null && n.value == null) {
				if(n.child != null) {
					prefixHelper(n.child, karr, idx);
				}
			}
			return list;
		}
		if(n.key != karr[idx]) {
			if(n.next != null) {
				prefixHelper(n.next, karr, idx);
			}
			else{
				list = (K[]) new Object[idx+1];
				for(int i = 0; i < idx+1; i++) {
					list[i] = karr[i];
				}
			}
			return list;
		}
		if(n.key == karr[idx]) {			
			if(n.child != null && idx < karr.length-1) {			
				prefixHelper(n.child, karr, idx+1);
			}else{													
				list = (K[]) new Object[idx+1];
				for(int i = 0; i < idx+1; i++) {
					list[i] = karr[i];
				}
			}
			return list;
		}
		return list;
	}
	/**
	 * The method returns an array of type K[] with the longest prefix of the
	 * key sequence specified in keyarr such that the keys in the prefix label
	 * the nodes on the path from the root to a node. The length of the returned
	 * array is the length of the longest prefix.
	 * 
	 * @param keyarr
	 * @return
	 */
	public K[] prefix(K[] keyarr) {
		return prefixHelper(root, keyarr, 0);
		// Hint: An array of the same type as keyarr can be created with
		// Arrays.copyOf().

	}

	private void addChild(Node n, K[] karr, V val, int idx) {
		Node tempNode = new Node(karr[idx], val);
		n.child = tempNode;
		tempNode.parent = n;
		tempNode.prev = null;
		tempNode.next = null;
		tempNode.child = null;
	}
	private void addSibling(Node n, K[] karr, V val, int idx) {
		Node tempNode = new Node(karr[idx], val);
		n.next = tempNode;
		tempNode.prev = n;
		tempNode.parent = n.parent;
		tempNode.child = null;
		tempNode.next = null;
	}
	
	private boolean addHelper(K[] karr, V val, Node n, int idx) {				
		if(karr == null || karr.length == 0 || val == null) {
			return added = false;
		}
		if(karr[idx] == null) {
			throw new NullPointerException();
		}
		if(n.key == null && n.value == null) {						//When starting at root
			if(idx == karr.length-1 && n.child == null) {
				addChild(n, karr, val, idx);
				idx = karr.length;
				return added = true;
			}
			else if(idx < karr.length-1 && n.child == null) {
				addChild(n, karr, null, idx);
				addHelper(karr, val, n.child, idx);
			}
			else if(n.child != null && idx <= karr.length-1) {
				addHelper(karr, val, n.child, idx);
			}
			return added;
		}
		else if(n.key == null && n.value == null && n.child != null) {
			addHelper(karr, val, n.child, idx);
		}
		if(n.key == karr[idx]) {										//Starting below root
			if(idx == karr.length-1) {
				n.value = val;
				idx = karr.length;
				return added = true;
			}
			else if(idx < karr.length-1 && n.child == null) {
				addChild(n, karr, null, idx+1);
				addHelper(karr, val, n.child, idx+1);
			}
			else if(idx < karr.length-1 && n.child != null) {
				addHelper(karr, val, n.child, idx+1);
			}
			return added;
		}
		else if(n.key != karr[idx] && idx <= karr.length) {
			if(n.next != null && idx < karr.length-1) {
				addHelper(karr, val, n.next, idx);
			}
			else if(idx < karr.length-1 && n.next == null) {
				addSibling(n, karr, null, idx);
				addHelper(karr, val, n.next, idx);
			}
			else if(idx == karr.length-1 && n.next == null) {
				addSibling(n, karr, val, idx);
				idx = karr.length;
				return added = true;
			}
			return added;
		}
		return added;
	}
	/**
	 * The method locates the node P corresponding to the longest prefix of the
	 * key sequence specified in keyarr such that the keys in the prefix label
	 * the nodes on the path from the root to the node. If the length of the
	 * prefix is equal to the length of keyarr, then the method places aValue at
	 * the node P and returns true. Otherwise, the method creates a new path of
	 * nodes (starting at a node S) labeled by the corresponding suffix for the
	 * prefix, connects the prefix path and suffix path together by making the
	 * node S a child of the node P, and returns true.
	 * 
	 * @param keyarr
	 * @param aValue
	 * @return
	 */
	public boolean add(K[] keyarr, V aValue) {
		return addHelper(keyarr, aValue, root, 0);
	}

	/**
	 * This method unlinks a leaf node that has a value of "null" from it's parent (and sibling).
	 * 
	 * The node to be unlinked
	 * @param n
	 */
	private void unlink(Node n) {
		if(n.parent != null && n.child == null && n.value == null) {
			if(n.next != null) {
				n.next.prev = null;
				n.parent.child = n.next;				
			}
			else{
				n.parent.child = null;
				unlink(n.parent);
			}
		}
	}
	
	/**This method searches through the tree for the key at the end of the K[] and returns its value and 
	 * then changes that key's value to "null" as well as removes any empty leafs.
	 * 
	 * The child (if it exists) of the root node
	 * @param n
	 * the K[] given
	 * @param karr
	 * index number for each value in K[]
	 * @param idx
	 * the value of the key in K[]
	 * @return
	 */
	private V removeHelper(Node n, K[] karr, int idx) {
		if(karr == null || karr.length == 0) {
			return val = null;
		}
		if(karr[idx] == null) {
			throw new NullPointerException();
		}
		if(n.key == null && n.value == null) {					//Checks if root node
			if(n.child == null) {
				return val = null;
			}
			else if(n.child != null) {
				removeHelper(n.child, karr, idx);
			}
			return val;
		}
//*******************************************BEGIN REMOVE FUNCTION BELOW******************************************************************
		if(n.key != karr[idx] && idx < karr.length-1) {				//If key doesn't match keyarr and is not last key,
			if(n.next != null) {									//calls method on next node,
				removeHelper(n.next, karr, idx);
			}else{													//or if there is no next node, returns null
				return val = null;
			}
			return val;
		}
		else if(n.key != karr[idx] && idx == karr.length-1) {		//if key doesn't match and is last key in keyarr
			if(n.next != null) {									//checks if next and calls method on next node
				removeHelper(n.next, karr, idx);
			}else{													
				return val = null;									//or returns null if next node doesn't exist
			}
			return val;
		}
		if(n.key == karr[idx] && idx < karr.length-1) {				//If key matches keyarr and is not node to be removed,
			if(n.child != null) {									//calls the method on the child and the next keyarr
				removeHelper(n.child, karr, idx+1);
			}else{													//or if there is no child, returns null as key to be
				return val = null;									//removed doesn't exist
			}
			return val;
		}
		if(n.key == karr[idx] && idx == karr.length-1) {			//If key matches keyarr and is node to be removed,								
				val = n.value;										//copies the value of the node
				n.value = null;										//then sets the node's value to null
				unlink(n);											//calls unlink method to remove unneeded nodes from tree
				return val;
		}
		return val;
	}
//***********************************************END REMOVE FUNCTION**********************************************************************
	/**
	 * Removes the entry for a key sequence from this tree and returns its value
	 * if it is present. Otherwise, it makes no change to the tree and returns
	 * null.
	 * 
	 * @param keyarr
	 * @return
	 */
	public V remove(K[] keyarr) {
		return removeHelper(root, keyarr, 0);
	}
	/**
	 * This is a helper method that keeps track of spacing so that the method
	 * showTree() can print the list recursively.
	 * 
	 * The node at the head of the list.
	 * @param n
	 * The string that keeps spacing in the tree properly aligned.
	 * @param s
	 */
	private void showTreeHelper(Node n, String s) {
		
		System.out.println(s + n.key + "->" + n.value);
		
		if(n.child != null) {
			showTreeHelper(n.child, s+" ");
		}
		if(n.next != null) {
			showTreeHelper(n.next, s);
		}
	}
	/**
	 * The method prints the tree on the console in the output format shown in
	 * an example output file.
	 */
	public void showTree() {
		String s = "";
		showTreeHelper(root, s);
	}

}
