package edu.iastate.cs228.hw1;

/*The public class CodingDNASequence extends the class DNASequence. The class
CodingDNASequence has a constructor and three methods, but has no additional
field.
 * @author Eric Mullen
*/

public class CodingDNASequence extends DNASequence
{
/*
* If the character array argument has a character on which the method isValidLetter()
* returns false, then it throws an IllegalArgumentException. Otherwise, the
* constructor saves a copy of the character array argument in the field of its superclass
* Sequence.
* 
* @param	cdnaarr		This is the char array used by the constructor.
*/
  public CodingDNASequence(char[] cdnaarr)
  {
	    super(cdnaarr);
	    int count = 0;
	    for(char c : cdnaarr){
	    	if(isValidLetter(c) == false){
	    		throw new IllegalArgumentException("Invalid sequence letter for class edu.iastate.cs228.hw1.CodingDNASequence");
	    	}else{
	        	seqarr[count] = c;
	        	count++;
	        }
	    }
  }
/*
 * If the length of the field character array seqarr is less than 3, then the method
 * returns false. Otherwise, if the first three characters in the array seqarr are A/a,
 * T/t, G/g in this order (case insensitive), then the method returns true. Otherwise,
 * it returns false
 * 
 * @return	boolean		true if the array length is 3 or greater, and the first 3 letters
 * are a, t, g (case insensitive), false if otherwise.
 */
  public boolean checkStartCodon()
  {
    if(seqarr.length < 3){
    	return false;
    }else{
    	if(seqarr[0] == 'A' || seqarr[0] == 'a'){
    		if(seqarr[1] == 'T' || seqarr[1] == 't'){
    			if(seqarr[2] == 'G' || seqarr[2] == 'g'){
    				return true;
    			}else{
    				return false;
    			}
    		}else{
    			return false;
    		}
    	}else{
    		return false;
    	}
    }
  }
/*
 * The method throws an RuntimeException exception if a call to the method checkStartCodon() 
 * returns false. Otherwise, the method uses the private method translates the coding sequence 
 * in the character array seqarr into a protein sequence by calling the private method getAminoAcid on every
 * codon in the coding sequence. The translation stops if the method getAminoAcid
 * returns the character ‘$’, which is not part of the protein sequence. Otherwise, the
 * translation stops when the end of the array seqarr is reached. The method returns
 * the protein sequence in a new character array, where the length of the protein sequence
 * is equal to the length of the array
 * 
 * @return	char[]		a char[] with the characters that matched with the string arguments given
 * in the method "getAminoAcid".
 */
  public char[] translate()
  {
    if(checkStartCodon() == false){
    	throw new RuntimeException ("No start codon");
    }else{
    	String tempStr = "";
    	char[] translated = new char[seqarr.length / 3];
    	int arrIndex = 0;
    	for(int i = 0; i < seqarr.length; i++){
    		tempStr += seqarr[i];
    		if(tempStr.length() == 3){
    			translated[arrIndex] = getAminoAcid(tempStr);
    			tempStr = "";
    			arrIndex ++;
    		}else{
    			if(i == seqarr.length - 1 && tempStr.length() < 3){
    			return translated;
    			}
    		}
    	}return translated;
    }
  }
/*
 * If the string argument codon encodes an amino acid, then the method returns the
 * character representing the amino acid. Otherwise, it returns the character ‘$’.
 * 
 * @param	codon	the string representation of part of the char[] used in the "translate" method.
 * @return	char	the char that matches with the 3-letter string given in the argument. Returns
 * "$" if otherwise.
 */
  private char getAminoAcid(String codon)
  {
    if ( codon == null ) return '$';
    char aa = '$';
    switch ( codon.toUpperCase() )
    {
      case "AAA": aa = 'K'; break;
      case "AAC": aa = 'N'; break;
      case "AAG": aa = 'K'; break;
      case "AAT": aa = 'N'; break;

      case "ACA": aa = 'T'; break;
      case "ACC": aa = 'T'; break;
      case "ACG": aa = 'T'; break;
      case "ACT": aa = 'T'; break;

      case "AGA": aa = 'R'; break;
      case "AGC": aa = 'S'; break;
      case "AGG": aa = 'R'; break;
      case "AGT": aa = 'S'; break;

      case "ATA": aa = 'I'; break;
      case "ATC": aa = 'I'; break;
      case "ATG": aa = 'M'; break;
      case "ATT": aa = 'I'; break;

      case "CAA": aa = 'Q'; break;
      case "CAC": aa = 'H'; break;
      case "CAG": aa = 'Q'; break;
      case "CAT": aa = 'H'; break;

      case "CCA": aa = 'P'; break;
      case "CCC": aa = 'P'; break;
      case "CCG": aa = 'P'; break;
      case "CCT": aa = 'P'; break;

      case "CGA": aa = 'R'; break;
      case "CGC": aa = 'R'; break;
      case "CGG": aa = 'R'; break;
      case "CGT": aa = 'R'; break;

      case "CTA": aa = 'L'; break;
      case "CTC": aa = 'L'; break;
      case "CTG": aa = 'L'; break;
      case "CTT": aa = 'L'; break;

      case "GAA": aa = 'E'; break;
      case "GAC": aa = 'D'; break;
      case "GAG": aa = 'E'; break;
      case "GAT": aa = 'D'; break;

      case "GCA": aa = 'A'; break;
      case "GCC": aa = 'A'; break;
      case "GCG": aa = 'A'; break;
      case "GCT": aa = 'A'; break;

      case "GGA": aa = 'G'; break;
      case "GGC": aa = 'G'; break;
      case "GGG": aa = 'G'; break;
      case "GGT": aa = 'G'; break;

      case "GTA": aa = 'V'; break;
      case "GTC": aa = 'V'; break;
      case "GTG": aa = 'V'; break;
      case "GTT": aa = 'V'; break;

      case "TAA": aa = '$'; break;
      case "TAC": aa = 'Y'; break;
      case "TAG": aa = '$'; break;
      case "TAT": aa = 'Y'; break;

      case "TCA": aa = 'S'; break;
      case "TCC": aa = 'S'; break;
      case "TCG": aa = 'S'; break;
      case "TCT": aa = 'S'; break;

      case "TGA": aa = '$'; break;
      case "TGC": aa = 'C'; break;
      case "TGG": aa = 'W'; break;
      case "TGT": aa = 'C'; break;

      case "TTA": aa = 'L'; break;
      case "TTC": aa = 'F'; break;
      case "TTG": aa = 'L'; break;
      case "TTT": aa = 'F'; break;
      default:    aa = '$'; break;
    }
    return aa;
  }
}
