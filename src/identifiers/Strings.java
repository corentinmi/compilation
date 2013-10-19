package identifiers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Strings {

	private String name;
	private String content;
	static Pattern pattern;
	
	public Strings(String name, String content){
		this.name=name;
		this.content=content;
	}
	public boolean checkString(){
		pattern = Pattern.compile("[a-zA-Z\\d\\+-\\*/:!\\? ]*", Pattern.CASE_INSENSITIVE);
		Matcher match = pattern.matcher(content);
		if (match.matches()) {
			System.out.println("String ok");
			return true;
		}
		
		return false;
	}
	
}
