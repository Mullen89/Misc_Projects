package edu.iastate.cs228.hw4;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
/**
 * 
 * @author Eric Mullen
 *
 *A test class
 */
public class EntryTreeTest {
	static EntryTree<Character, String> tree;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		tree = new EntryTree<Character, String>();
	}
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Test
	public void testSearch() {
		Character[] char1 = {'a', 'b', 'c'};
		Character[] char2 = {'a', 'd', 'e'};
		Character[] char3 = {'H', 'o', 's', 'p', 'i', 't', 'a', 'l'};
		
		
		assertEquals(true, tree.add(char1, "hello"));
		assertEquals(true, tree.add(char2, "world"));
		tree.add(char3, "doctor");
		assertEquals("doctor", tree.search(char3));
		
	}

	@Test
	public void testPrefix() {
		Character[] char3 = {'H', 'o', 's', 'p', 'i', 't', 'a', 'l'};
		Character[] char4 = {'H', 'o', 's', 'p', 'i', 'c', 'e'};
		tree.add(char3, "doctor");
		Object[] list2 = tree.prefix(char4);
		String result = "";
		for (int j = 0; j < list2.length; j++) {
			result += list2[j].toString();
		}
		assertEquals("Hospic", result);
	}
	
	@Test 
	public void testAdd() {
		Character[] char1 = {'a', 'b', 'c'};
		Character[] char2 = {'a', null, 'e'};
		Character[] char3 = {'H', 'o', 's', 'p', 'i', 't', 'a', 'l'};
		Character[] char4 = {};
		
		assertEquals(true, tree.add(char3, "surgeon"));
		assertFalse(tree.add(char4, "tree"));
		exception.expect(NullPointerException.class);
		tree.add(char2, "tree");
	}

	@Test
	public void testRemove() {
		Character[] char1 = {'a', 'b', 'c'};
		Character[] char2 = {'a', null, 'e'};
		Character[] char3 = {'H', 'o', 's', 'p', 'i', 't', 'a', 'l'};
		Character[] char4 = {};
		
		tree.add(char3, "doctor");
		tree.add(char1, "hello");
		assertEquals("doctor", tree.remove(char3));
		exception.expect(NullPointerException.class);
		tree.add(char2, "world");
		
	}

}
