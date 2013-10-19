package identifiers;

public class Real {
	
	private String name;
	private boolean negative;
	private boolean decimal;
	private String real;
	
	public Real(String name, String real){
		this.real=real;
		this.name=name;
		
	}
	public boolean checkReal(){
		if (real.contains("-")){
			negative=true;
		}else if (real.contains("+")|| (!real.contains("-") && !real.contains("+"))){
			negative=false;
			real = real.replace("+", "");
		}
		if(real.contains(".")){
			decimal=true;
		}else {
			decimal=false;
		}
		try { 
	        Double.parseDouble(real); 
	    } catch(NumberFormatException e) { 
	        return false; 
	    }
	    // only got here if we didn't return false
	    return true;
	}

}
