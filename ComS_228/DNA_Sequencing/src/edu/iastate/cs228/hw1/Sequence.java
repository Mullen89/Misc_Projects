package edu.iastate.cs228.hw1;

/*
 * This class is a superclass of every other class. The class has a field named seqarr, which 
 * is a character array with protected access. 
 * @author Eric Mullen
*/

public class Sequence
{
  protected char[] seqarr;
/*
 * Constructs the sequence assay of characters that will be called by every other class.
 * It makes sure the sequence is valid, and if not, it throws an exception.
 * If the sequence array is valid, it makes a copy of it.
 * 
 * @param	sarr	This is the char array used by the constructor.
 */
  public Sequence(char[] sarr)
  {
	seqarr = new char[sarr.length];
    for(int i = 0; i < sarr.length; i++){
    	if(!isValidLetter(sarr[i])){
    		throw new IllegalArgumentException ("Invalid sequence "
    				+ "letter for " + this.getClass());
    	}else{
    		seqarr[i] = sarr[i];	
        }
    }
  }
/*
 * This method gives the length of the sequence array "seqarr".
 * 
 * @return	int		the int length of the array.
 */
  public int seqLength()
  {
    int lengthOfArray = seqarr.length;
    return lengthOfArray;
  }
  /*
   * The method makes and returns a copy of the char array "seqarr".
   * @return	char[]	the copy of the char array "seqarr" that is made.
   * 
   */
  public char[] getSeq()
  {
	  char[] seqarrCopy = new char[seqarr.length];
	  for(int i = 0; i < seqarr.length; i++){
		  seqarrCopy[i] = seqarr[i];
	  }
	  return seqarrCopy;
  }
  /*
   * The method returns the string representation of the char array "seqarr".
   * 
   * @return	String	the representation of the array as a string.
   * 
   */
  public String toString()
  {
    String arrayToString = "";
    for(char c : seqarr){
    	arrayToString += c;
    }
    return arrayToString;
  }
/*
 *The method returns true if the argument obj is not null and is of the same type
 *as this object such that both objects represent the identical sequence of characters
 *in a case insensitive mode (”ACgt” is identical to ”AcGt”).
 *
 *@param	obj		the Object argument given that is compared to the "this" object.
 *@return	boolean		true if the objects are equal, false if otherwise.
 */
  public boolean equals(Object obj)
  { 
    boolean isEqual = false;
    if( obj != null && obj.getClass().equals(this.getClass())){
    	Sequence tempObj = (Sequence) obj;
    	if( tempObj.seqarr.length == this.seqarr.length){
    		for( int i = 0; i < seqarr.length; i++){
    			if(Character.toLowerCase(tempObj.seqarr[i]) == Character.toLowerCase(this.seqarr[i])){
    				isEqual = true;
    			}else{
    				isEqual = false;
    				return isEqual;
    			}
    		}
    	}else{
			return isEqual;
		}
    }return isEqual;
  }
/*
 * The method returns true if the character let is an uppercase (Character.isUpperCase(let)
 * is true) or lowercase (Character.isLowerCase(let) is true). Otherwise, it returns
 * false.
 * 
 * @param	let		the char given which is then checked to see if it is an upper case 
 * or lower case letter.
 * @return	boolean		true if the char is an upper or lower case letter, false if otherwise.
 */
  public boolean isValidLetter(char let)
  {
	boolean isValid;
    if (Character.isUpperCase(let)){
    	isValid = true;
    }else if (Character.isLowerCase(let)){
    	isValid = true;
    }else{
    	isValid = false;
    } return isValid;
  }

}
