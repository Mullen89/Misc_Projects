package edu.iastate.cs228.hw1;

import org.junit.Test;
import org.junit.rules.ExpectedException;
import static org.junit.Assert.*;
import org.junit.Rule;
import edu.iastate.cs228.hw1.DNASequence;

public class DNASequenceTest {
	private char[] dnaarr = new char[]{'A','A','T','G','C','C','A','G','T','C','A','G','C','A','T','A','G','C','G','T','A'};
	private char[] dnaarr3 = new char[]{'A','A','T','G','C','C','A','G','x','C','A','G','C','A','T','A','G','C','G','T','A'};
	
	private DNASequence a;
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Test
	public void testDNASConstructor(){
		a = new DNASequence(dnaarr);
	}
	
	@Test
	public void testExceptionDNASConstructor(){
		exception.expect(IllegalArgumentException.class);
	    exception.expectMessage("Invalid sequence letter for class edu.iastate.cs228.hw1.DNASequence");
		a = new DNASequence(dnaarr3);
	}
	
	@Test
	public void testDNASisValidLetter(){
		a = new DNASequence(dnaarr);
		assertTrue(a.isValidLetter('a'));
		assertFalse(a.isValidLetter('J'));
		assertTrue(a.isValidLetter('c'));
		assertFalse(a.isValidLetter('$'));
		assertTrue(a.isValidLetter('T'));
	}
}	