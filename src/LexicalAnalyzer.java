import identifiers.Image;

import java.util.Scanner;


public class LexicalAnalyzer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String line = new String();
		System.out.println("Bonjour");
		
		Scanner input = new Scanner(System.in).useDelimiter("\\.");
		
		while(line != "end") {
			line = input.next();
			Image.check(line);
		}
		
		input.close();
	}

}
