package edu.iastate.cs228.hw1;

/*
 * The public class GenomicDNASequence extends the class DNASequence. The class
 * GenomicDNASequence has a constructor, two methods, and a private field named
 * iscoding, which is a boolean array.
 * @author Eric Mullen
*/

public class GenomicDNASequence extends DNASequence
{
  private boolean[] iscoding;
/*
 * If the character array argument has a character on which the method isValidLetter()
 * returns false, then it throws an IllegalArgumentException. Otherwise,
 * the constructor saves a copy of the character array argument in the field of its
 * superclass Sequence. And it creates a boolean array of the same length as gdnaarr,
 * saves its reference in the field iscoding, and sets the boolean array to false at each
 * index.
 * 
 * @param	gdnaarr		This is the char array used by the constructor.
 */
  public GenomicDNASequence(char[] gdnaarr)
  {
		super(gdnaarr);
		int count = 0;
		iscoding = new boolean[gdnaarr.length];
	    for(char c : gdnaarr){
	    	if(isValidLetter(c) == false){
	    		throw new IllegalArgumentException("Invalid sequence letter for class edu.iastate.cs228.hw1.GenomicDNASequence");
	    	}else{
	        	seqarr[count] = c;
	    		iscoding[count] = false;
	    		count++;
	    	}
	    }
 }
  
/*
 * The method throws an IllegalArgumentException exception if first is greater than
 *  last, or first is less than 0, or last is greater than or equal to the length 
 *  returned by the seqLength() method. Otherwise, it sets the boolean array iscoding 
 *  to true at each index between first and last inclusive.
 *  
 *  @param	first	the 1st int in the exonpos array.
 *  @param	last	the last int in the exonpos array.
 */
  public void markCoding(int first, int last)
  {
    if(first > last || first < 0 || last >= seqLength()){
    	throw new IllegalArgumentException ("Coding border is out of bound");
    }else{
    	for(int i = first; i <= last; i++){
    		iscoding[i] = true;
    	}
    }
  }
/*
 * The integer array argument exonpos is used to specify the start and end positions
 * of every coding exon in the genomic sequence. The method throws
 * an IllegalArgumentException exception with the message “Empty array or odd
 * number of array elements” if the length of exonpos is 0 or an odd number. It throws
 * an IllegalArgumentException exception with the message “Exon position is out of
 * bound” if an element in the exonpos is less than 0 or is greater than or equal to the
 * length returned by the seqLength() method. It throws an IllegalArgumentException
 * exception with the message “Exon positions are not in order” if an element in exonpos
 * is greater than its right neighbor element. It throws an IllegalStateException
 * exception with the message “Noncoding position is found” if the boolean array
 * iscoding is false at a coding exon position. Otherwise, the method takes all the
 * coding exons specified by the array exonpos, concatenates them in order, and returns
 * the resulting sequence in a new character array.
 * 
 * @param	exonpos		the int[] used to specify the start and end positions of the coding exons.
 * @return	char[]		a char[] that contains all coding exons (specified by the exonpos array)
 * concatenated in order.
 */
  public char[] extractExons(int[] exonpos)
  {
	int tempCount2 = 0;
    if(!(exonpos.length % 2 == 0)){
    	throw new IllegalArgumentException ("Empty array or odd number of array elements");
    }
    for(int i : exonpos){
    	tempCount2 += i;
    }
    if(tempCount2 == 0){
    	throw new IllegalArgumentException ("Empty array or odd number of array elements");
    }
    for(int i = 1; i < exonpos.length; i++){
    	if(exonpos[i - 1] < 0 || exonpos[i - 1] >= seqLength()){
    		throw new IllegalArgumentException ("Exon position is out of bound");
    	}
    	if(exonpos[i - 1] > exonpos[i]){
    		throw new IllegalArgumentException ("Exon positions are not in order");
    	}
    	if(iscoding[exonpos[i - 1]] == false){
    		throw new IllegalStateException ("Noncoding position is found");
    	}
    }
	int seqLenCount = 0;
    for(int i = 1; i < exonpos.length; i+=2){
    	seqLenCount += (exonpos[i] - exonpos[i - 1]) + 1;
    }
    char[] extracted = new char[seqLenCount];
    int tempCount = 0;
    for(int j = 0; j < exonpos.length; j += 2){
    	for(int i = exonpos[j]; i <= exonpos[j +1]; i++){
    		extracted[tempCount] = seqarr[i];
    		tempCount++;
    	}  	
    }return extracted;  
  }
}
