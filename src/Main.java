import java.util.Scanner;


public class Main {

	/**
	 * Loads the dictionary, and loops on the standard input.
	 * When a token is detected, it is checked by the DFA
	 * @param args
	 */
	public static void main(String[] args) {
		String line = new String();
		String instruction = null;
		
		LexicalAnalyzer.loadDico();
		System.out.println("Introduction to Language Theory and Compilation");
		System.out.println("Project 1 - Jorge Garcia Ximenez and Corentin Misercque");
		
		Scanner input = new Scanner(System.in);
		Scanner ls;
		Scanner nocomment, nodot;
		
		while(input.hasNextLine()) {
			line = input.nextLine();
			nocomment = new Scanner(line).useDelimiter("/");
			if (nocomment.hasNext()) {
				nodot = new Scanner(nocomment.next()).useDelimiter("\\.");
				while (nodot.hasNext()) {
					instruction = nodot.next();
					ls = new Scanner(instruction);
					while (ls.hasNext()) {
						LexicalAnalyzer.nextToken(ls.next());
					}
					LexicalAnalyzer.checkString(instruction);
				}
			}
			System.out.println("token: .\\n --- lexical unit: END_OF_INSTRUCTION");
		}
		
		input.close();
	}

}
