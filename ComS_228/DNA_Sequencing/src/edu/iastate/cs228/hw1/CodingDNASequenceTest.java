package edu.iastate.cs228.hw1;

import org.junit.Test;
import org.junit.rules.ExpectedException;
import static org.junit.Assert.*;
import org.junit.Rule;
import edu.iastate.cs228.hw1.CodingDNASequence;

public class CodingDNASequenceTest {
	private char[] cdnarr = new char[]{'A','A','T','G','C','C','A','G','T','C','A','G','C','A','T','A','G','C','G','T','A'};
	private char[] cdnarr2 = new char[]{'A','A','T','G','C','C','A','G','$','C','A','G','C','A','T','A','G','C','G','T','A'};
	private char[] cdnarr3 = new char[]{'A','A'};
	private char[] cdnarr4 = new char[]{'t','T', 'G',};
	private char[] cdnarr5 = new char[]{'A','G', 'G',};
	private char[] cdnarr6 = new char[]{'A','T', 'A',};
	private char[] cdnarr7 = new char[]{'a','T', 'g',};
	private char[] cdnarr8 = new char[]{'A','t','g','G','T','C','A','G','T','C','A','G','C','A','T','A','G','C','G','T','A','c','t'};
	private char[] checkTranslate = new char[]{'M','V','S','Q','H','S','V'};
	
	private CodingDNASequence a;
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Test
	public void testCodingDNASConstructor(){
		a = new CodingDNASequence(cdnarr);
	}
	
	@Test
	public void testExceptionConstructor(){
		exception.expect(IllegalArgumentException.class);
	    exception.expectMessage("Invalid sequence letter for class edu.iastate.cs228.hw1.CodingDNASequence");
		a = new CodingDNASequence(cdnarr2);
	}
	
	@Test
	public void testCheckStartCodon(){
		a = new CodingDNASequence(cdnarr3);
		assertFalse(a.checkStartCodon());
	}
	
	@Test
	public void testCheckStartCodon2(){
		a = new CodingDNASequence(cdnarr4);
		assertFalse(a.checkStartCodon());
	}
	
	@Test
	public void testCheckStartCodon3(){
		a = new CodingDNASequence(cdnarr5);
		assertFalse(a.checkStartCodon());
	}
	
	@Test
	public void testCheckStartCodon4(){
		a = new CodingDNASequence(cdnarr6);
		assertFalse(a.checkStartCodon());
	}
	
	@Test
	public void testCheckStartCodon5(){
		a = new CodingDNASequence(cdnarr7);
		assertTrue(a.checkStartCodon());
	}
	
	@Test
	public void testExceptionTranslate(){
		exception.expect(RuntimeException.class);
	    exception.expectMessage("No start codon");
		a = new CodingDNASequence(cdnarr6);
		a.translate();
	}
	
	@Test
	public void testTranslate(){
		a = new CodingDNASequence(cdnarr8);
		assertArrayEquals(checkTranslate, a.translate());
	}
}