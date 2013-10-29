import java.util.ArrayList;
import java.util.Scanner;

import util.Label;
import util.Variable;

/**
 * Main class, launches the analyzer
 * @author Corentin Misercque, Jorge Garcia Ximenez
 *
 */
public class Main {

	/**
	 * Loads the dictionary, and loops on the standard input.
	 * When a token is detected, it is checked by the DFA
	 * @param args
	 */
	public static void main(String[] args) {
		LexicalAnalyzer la = new LexicalAnalyzer();
		ArrayList<Label> labels = new ArrayList<Label>();
		ArrayList<Variable> var = new ArrayList<Variable>();
		
		String token = new String(), pToken = new String();
		Boolean eoi = false;
		int type, prev = 0;
		boolean alone = true;

		System.out.println("Introduction to Language Theory and Compilation");
		System.out.println("Project 1 - Jorge Garcia Ximenez and Corentin Misercque");
		
		
		Scanner line = new Scanner(System.in);
		Scanner input;
		int lCount = 0;
		
		while ((!(token.contentEquals("quit"))) && line.hasNextLine()) {
			input = new Scanner(line.nextLine());
			lCount++;
			while((!(token.contentEquals("quit"))) && input.hasNext()) {
				token = input.next();
				if (token.charAt(token.length() - 1) == '.') {
					token = token.substring(0, token.length() - 1);
					eoi = true;
				}
				else
					alone = false;
				if (token.charAt(0) == '\'') {
					while (token.charAt(token.length() - 1) != '\'')
						token += " " + input.next();
				}
				if ((token.charAt(0) == '/') || (token.charAt(0) == '*'))
					break;
				
				type = la.nextToken(token);
				
				if (alone && (type == 5)) {
					labels.add(new Label(token, lCount));
				}
				
				if ((prev == 5) && (type == 2)) {
					var.add(new Variable(pToken, token));
				}
				
				alone = false;
				pToken = token;
				prev = type;
				
				if (eoi) {
					System.out.println("token: . --- lexical unit: END OF INSTRUCTION");
					eoi = false;
					prev = 0;
				}
			}
			alone = true;
			System.out.println("token: \\n --- lexical unit: NEW_LINE");
		}
		
		line.close();
		
		System.out.println("\nVariables : ");
		for (Variable v : var) {
			System.out.println(v.getId() + " -- " + v.getImage());
		}
		
		System.out.println("\nLabels : ");
		for (Label l : labels) {
			System.out.println(l.getId() + " -- " + l.getLine());
		}
	}

}
