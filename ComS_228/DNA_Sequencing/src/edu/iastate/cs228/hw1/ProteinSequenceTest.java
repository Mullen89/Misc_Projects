package edu.iastate.cs228.hw1;

import org.junit.Test;
import org.junit.rules.ExpectedException;
import static org.junit.Assert.*;
import org.junit.Rule;
import edu.iastate.cs228.hw1.ProteinSequence;

public class ProteinSequenceTest {
	private char[] psarr = new char[]{'A','A','T','G','C','C','A','G','T','C','A','G','C','A','T','A','G','C','G','T','A'};
	private char[] psarr3 = new char[]{'A','A','T','G','C','C','A','G','x','C','A','G','C','A','T','A','G','C','G','T','A'};
	
	private ProteinSequence a;
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Test
	public void testPSConstructor(){
		a = new ProteinSequence(psarr);
	}
	
	@Test
	public void testExceptionPSConstructor(){
		exception.expect(IllegalArgumentException.class);
	    exception.expectMessage("Invalid sequence letter for class edu.iastate.cs228.hw1.ProteinSequence");
		a = new ProteinSequence(psarr3);
	}
	
	@Test
	public void testPSisValidLetter(){
		a = new ProteinSequence(psarr);
		assertTrue(a.isValidLetter('a'));
		assertFalse(a.isValidLetter('J'));
		assertFalse(a.isValidLetter('$'));
		assertTrue(a.isValidLetter('&'));
	}
}	