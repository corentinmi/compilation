package identifiers;

import java.util.regex.*;

public class Image {
	
	static Pattern pattern;
	
	private String name;
	private boolean signed;
	private int size;
	private int pointSize;
	
	/**
	 * Checks if the input is a variable declaration.
	 * 
	 * @param input The input to be checked
	 * @return True if the input is found
	 */
	public static Image check(String input) {
		pattern = Pattern.compile("[\n\r\t ]*image +([a-zA-Z]\\w*) +(s?)9(\\((\\d+)\\))?(v9(\\((\\d+)\\))?)?[\n\r\t ]*", Pattern.CASE_INSENSITIVE);
		Matcher match = pattern.matcher(input);
		if (match.matches()) {
			System.out.println("trouvé : " + match.group(1) + "-" + match.group(2) + "-" + match.group(4) + "-" + match.group(7));
			
			int size = (match.group(4) != null) ? Integer.valueOf(match.group(4)) : 1;
			int pointSize = (match.group(5) != null) ? (match.group(7) != null) ? Integer.valueOf(match.group(7)) : 1 : 0;
					
			Image image = new Image(match.group(1),
									(match.group(2) == "s"),
									size,
									pointSize);
			return image;
		}
		else {
			System.out.println("pas trouvé");
			return null;
		}
	}
	
	public Image(String name, boolean signed, int size, int pointSize) {
		this.name = name;
		this.signed = signed;
		this.size = size;
		this.pointSize = pointSize;
	}
	
}
