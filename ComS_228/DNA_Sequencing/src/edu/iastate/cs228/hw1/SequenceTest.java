package edu.iastate.cs228.hw1;

import org.junit.Test;
import org.junit.rules.ExpectedException;
import static org.junit.Assert.*;
import org.junit.Rule;
import edu.iastate.cs228.hw1.Sequence;

public class SequenceTest {
	private char[] sarr = new char[]{'A','A','T','G','C','C','A','G','T','C','A','G','C','A','T','A','G','C','G','T','A'};
	private char[] sarr2 = new char[]{'A','A','T','G','C','C','A','G','$','C','A','G','C','A','T','A','G','C','G','T','A'};
	private char[] sarr3 = new char[]{'A','A','T','G','C','C','A','G','x','C','A','G','C','A','T','A','G','C','G','T','A'};
	
	private Sequence a;
	private Sequence b;
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Test
	public void testConstructor(){
		a = new Sequence(sarr);
	}
	
	@Test
	public void testExceptionConstructor(){
		exception.expect(IllegalArgumentException.class);
	    exception.expectMessage("Invalid sequence letter for class edu.iastate.cs228.hw1.Sequence");
		a = new Sequence(sarr2);
	}
	
	@Test
	public void testIsValidLetter(){
		a = new Sequence(sarr);
		assertTrue(a.isValidLetter('Z'));
		assertTrue(a.isValidLetter('z'));
		assertFalse(a.isValidLetter('&'));
	}
	
	@Test
	public void testSeqLength(){
		a = new Sequence(sarr);
		assertEquals(21, a.seqLength());
	}
	
	@Test
	public void testGetSeq(){
		a = new Sequence(sarr);
		assertArrayEquals(sarr, a.getSeq());
	}
	
	@Test
	public void testToString(){
		a = new Sequence(sarr);
		b = new Sequence(sarr3);
		String tempStr ="AATGCCAGTCAGCATAGCGTA";
		String tempStr2 ="AATGCCAGxCAGCATAGCGTA";
		assertEquals(tempStr, a.toString());
		assertEquals(tempStr2, b.toString());
	}
	
	@Test
	public void testEquals(){
		b = new Sequence(sarr);
		a = new Sequence(sarr3);
		assertTrue(b.equals((Object) b));
		assertEquals(b.equals((Object) b), a.equals((Object) a));
	}
}
