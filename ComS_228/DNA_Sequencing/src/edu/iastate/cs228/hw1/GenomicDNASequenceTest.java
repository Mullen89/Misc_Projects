package edu.iastate.cs228.hw1;

import org.junit.Test;
import org.junit.rules.ExpectedException;
import static org.junit.Assert.*;
import org.junit.Rule;
import edu.iastate.cs228.hw1.GenomicDNASequence;

public class GenomicDNASequenceTest {
	private char[] gdnaarr = new char[]{'A','A','T','G','C','C','A','G','T','C','A','G','C','A','T','A','G','C','G','T','A'};
	private char[] gdnaarr2 = new char[]{'A','A','T','G','C','C','A','G','$','C','A','G','C','A','T','A','G','C','G','T','A'};
	private int[] oddNumb = new int[]{5, 6, 9, 11, 13};
	private int[] belowOne = new int[]{-5, 6, 9, 11, 13, 15};
	private int[] outOfBounds = new int[]{65, 70, 83, 84};
	private int[] notInOrder = new int[]{6, 3, 9, 11, 13, 15};
	private int[] arrWorks = new int[]{5, 6, 9, 11, 13, 15};
	private int[] emptyArr = new int[]{0, 0, 0, 0, 0, 0}; 
	
	private GenomicDNASequence a;
	
	@Rule
	public ExpectedException exception = ExpectedException.none();
	
	@Test
	public void testGDNAConstructor(){
		a = new GenomicDNASequence(gdnaarr);
	}
	
	@Test
	public void testExceptionConstructor(){
		exception.expect(IllegalArgumentException.class);
	    exception.expectMessage("Invalid sequence letter for class edu.iastate.cs228.hw1.GenomicDNASequence");
		a = new GenomicDNASequence(gdnaarr2);
	}
	
	@Test
	public void testMarkCoding(){
		exception.expect(IllegalArgumentException.class);
	    exception.expectMessage("Coding border is out of bound");
		a = new GenomicDNASequence(gdnaarr);
		a.markCoding(3, 2);
	} 
	
	@Test 
	public void testMarkCoding2(){
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage("Coding border is out of bound");
		a = new GenomicDNASequence(gdnaarr);
		a.markCoding(-1, 2);
	} 
	
	@Test 
	public void testMarkCoding3(){
		exception.expect(IllegalArgumentException.class);
		exception.expectMessage("Coding border is out of bound");
		a = new GenomicDNASequence(gdnaarr);
		a.markCoding(3, 21);
	} 
	
	@Test
	public void testMarkCoding4(){
		a = new GenomicDNASequence(gdnaarr);
		a.markCoding(3, 5);
	} 
	
	@Test
	public void testExtractExons(){
		exception.expect(IllegalArgumentException.class);
	    exception.expectMessage("Empty array or odd number of array elements");
		a = new GenomicDNASequence(gdnaarr);
		a.extractExons(oddNumb);
	}
	
	@Test
	public void testExtractExons2(){
		exception.expect(IllegalArgumentException.class);
	    exception.expectMessage("Empty array or odd number of array elements");
		a = new GenomicDNASequence(gdnaarr);
		a.extractExons(emptyArr);
	}
	
	@Test
	public void testExtractExons3(){
		exception.expect(IllegalArgumentException.class);
	    exception.expectMessage("Exon position is out of bound");
		a = new GenomicDNASequence(gdnaarr);
		a.extractExons(belowOne);
	}
	
	@Test
	public void testExtractExons4(){
		exception.expect(IllegalArgumentException.class);
	    exception.expectMessage("Exon position is out of bound");
		a = new GenomicDNASequence(gdnaarr);
		a.extractExons(outOfBounds);
	}
	
	@Test
	public void testExtractExons5(){
		exception.expect(IllegalArgumentException.class);
	    exception.expectMessage("Exon positions are not in order");
		a = new GenomicDNASequence(gdnaarr);
		a.extractExons(notInOrder);
	}
	
	@Test
	public void testExtractExons6(){
		exception.expect(IllegalStateException.class);
	    exception.expectMessage("Noncoding position is found");
		a = new GenomicDNASequence(gdnaarr);
		a.extractExons(arrWorks);
	}
	
	@Test
	public void testExtractExons7(){
	    char[] tempExtracted = new char[]{'C', 'A', 'C', 'A', 'G', 'A', 'T', 'A'};
		a = new GenomicDNASequence(gdnaarr);
		a.markCoding(5, 15);
		assertArrayEquals(tempExtracted, a.extractExons(arrWorks));
	}
}