package identifiers;

public class Integers {
	
	private String name;
	private boolean negative;
	private String integ;
	
	
	public boolean checkInteger(){
	
		if (integ.contains("-")){
			negative=true;
		}else if (integ.contains("+")|| (!integ.contains("-") && !integ.contains("+"))){
			negative=false;
			integ = integ.replace("+", "");
		}
		try { 
	        Integer.parseInt(integ); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    }
	    // only got here if we didn't return false
	    return true;
		
	}
	public Integers(String name, String integ){
		this.name= name;
		this.integ=integ;
		
	}
	
	
}
