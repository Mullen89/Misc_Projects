    package postage2;
    public class PostageUtil
    {
      /**
       * Returns the cost of postage for a letter of the given weight.
       * @param weight
       *   given weight in ounces
       * @return
       *   cost of postage for the weight
       */
    	
    	// This uses flowchart #2, which will give an incorrect 
    	// cost of postage if the weight is > 3.5.
      public static double computePostage(double weight)
      {
    	  double cost = 0.0;
    	  
    	  if (weight <= 1) {
    		  cost = 0.47;
    	  }
    	  else {
    		  if (weight > 1) {
    			  cost = 0.47 + Math.ceil(weight - 1) * 0.21;
    		  }
    		  else { 
    			  if (weight > 3.5) {
    				  cost = 0.47 + Math.ceil(weight - 1) * 0.21;  
    			  }
    			  else {
    				  cost = 0.47 + Math.ceil(weight - 1) * 0.21;
    			  }
    		  }
    	  }
       
        return cost;
      }
    }