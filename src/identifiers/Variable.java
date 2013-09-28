package identifiers;

import java.util.regex.*;

public class Variable {
	
	static Pattern pattern;
	
	/**
	 * Checks if the input is a variable declaration.
	 * 
	 * @param input The input to be checked
	 * @return True if the input is found
	 */
	public static boolean check(String input) {
		pattern = Pattern.compile("image +([a-zA-Z]\\w*) +(s?)9\\((\\d+)\\)(\\.9\\((\\d+)\\))? *\\.", Pattern.CASE_INSENSITIVE);
		Matcher match = pattern.matcher(input);
		if (match.matches()) {
			System.out.println("trouvé");
			return true;
		}
		else {
			System.out.println("pas trouvé");
			return false;
		}
	}
	
}
