package edu.iastate.cs228.hw1;

/*
 * The public class ProteinSequence extends the class Sequence. This class has a
 * constructor and a method, but has no additional field.
 * @author Eric Mullen
*/

public class ProteinSequence extends Sequence
{
/*
* If the character array argument psarr contains a character on which the method
* isValidLetter() returns false, then it throws an IllegalArgumentException.
* Otherwise, the constructor saves a copy of the character array argument in the field
* of its superclass Sequence.
* 
* @param	psarr	This is the char array used by the constructor.
*/
  public ProteinSequence(char[] psarr)
  {
	super(psarr);
	int count = 0;
    for(char c : psarr){
    	if(isValidLetter(c) == false){
    		throw new IllegalArgumentException("Invalid sequence letter for class edu.iastate.cs228.hw1.ProteinSequence");
    	}else{
        	seqarr[count] = c;
        	count++;
        }
    }
   }

/*
 * The method returns true if the character argument is equal to one of the 20
 * English letters that are not in the set {B, b, J, j, O, o, U, u, X, x, Z, z}. Otherwise,
 * it returns false. This method overrides the one in its superclass.
 * 
 * @param	aa		the char that is to be compared to the given char[] in the method.
 * @return	boolean		true if the given char DOES NOT match the characters in the given
 * char[], false if otherwise.
 */
  @Override
  public boolean isValidLetter(char aa)
  {
    char[] tempArr = new char[]{'B', 'b', 'J', 'j', 'O', 'o', 'U', 'u', 'X', 'x', 'Z', 'z', '$'};
    
    for (char c : tempArr){
    	if(aa == c)
    		return false;
    }
    return true;
  }
}
