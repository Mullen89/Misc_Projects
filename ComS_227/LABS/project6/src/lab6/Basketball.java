package lab6;

public class Basketball
{
	public double diameter;
	public boolean inflated;
  public Basketball(double givenDiameter)
  {
	  diameter = givenDiameter;
	  inflated = false;
  }

  public boolean isDribbleable()
  {
	  if (inflated == true) {
		  return true;
	  }else {
		  return false;
	  }
  }

  public double getDiameter()
  {
	  return diameter;
  }

  public double getCircumference()
  {
	  if (inflated == true){
		  return diameter * 3.14;
	  }
	  else{
		  return 0;
	  }
  }

  public void inflate()
  {
	  inflated = true;
  }
}