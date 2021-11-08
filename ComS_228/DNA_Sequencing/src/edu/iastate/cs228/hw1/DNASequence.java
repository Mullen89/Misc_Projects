package edu.iastate.cs228.hw1;

/*
 * The public class DNASequence extends the class Sequence. The class DNASequence
 * has a constructor and a method, but has no additional field.
 * @author Eric Mullen
*/

public class DNASequence extends Sequence
{
/*
 *If the character array argument has a character on which the method isValidLetter()
 *returns false, then it throws an IllegalArgumentException. Otherwise, the constructor
 *saves a copy of the character array argument in the field of its superclass.
 *
 *@param	dnaarr	This is the char array used by the constructor.
 */
  public DNASequence(char[] dnaarr)
  {
    super(dnaarr);
    int count = 0;
    for(char c : dnaarr){
    	if(isValidLetter(c) == false){
    		throw new IllegalArgumentException("Invalid sequence letter for class edu.iastate.cs228.hw1.DNASequence");
    	}else{
    	seqarr[count] = c;
    	count++;
    	}
    }
  }
/*
 *The method returns true if the character argument is equal to one of the eight
 *characters ’a’, ’A’, ’c’, ’C’, ’g’, ’G’, ’t’ and ’T’. Otherwise, it returns false. This
 *method overrides the one in its superclass.
 *
 *@param	let		the char that is to be compared to the given char[] in the method.
 *@return	boolean		true if the given char matches with the characters given in
 *the char[], false if otherwise.
 */
  @Override
  public boolean isValidLetter(char let)
  {
	    char[] tempArr = new char[]{'A', 'a', 'C', 'c', 'G', 'g', 'T', 't'};
	    
	    for (char c : tempArr){
	    	if(let == c)
	    		return true;
	    }
	    return false;
  }

}
