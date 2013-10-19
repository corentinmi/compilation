import java.util.Scanner;


public class Main {

	/**
	 * Loads the dictionary, and loops on the standard input.
	 * When a token is detected, it is checked by the DFA
	 * @param args
	 */
	public static void main(String[] args) {
		String line = new String();
		
		LexicalAnalyzer.loadDico();
		System.out.println("Introduction to Language Theory and Compilation");
		System.out.println("Project 1 - Jorge Garcia Ximenez and Corentin Misercque");
		
		Scanner input = new Scanner(System.in).useDelimiter("\\.");
		Scanner ls;
		
		while(!line.contentEquals("end")) {
			line = input.nextLine();
			ls = new Scanner(line);
			while (ls.hasNext()) {
				LexicalAnalyzer.nextToken(ls.next());
			}
			LexicalAnalyzer.checkString(line);
			System.out.println("token: .\\n --- lexical unit: END_OF_INSTRUCTION");
		}
		
		input.close();
	}

}
