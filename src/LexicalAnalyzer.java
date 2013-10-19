import identifiers.Letter;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class LexicalAnalyzer {
	
	private static ArrayList<Letter> DFA = new ArrayList<Letter>();

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
		
		Scanner input = new Scanner(System.in);
		Scanner ls;
		
		while(!line.contentEquals("end")) {
			line = input.nextLine();
			ls = new Scanner(line);
			while (ls.hasNext()) {
				LexicalAnalyzer.check(ls.next());
			}
			LexicalAnalyzer.checkString(line);
			System.out.println("token: \\n --- lexical unit: END_OF_INSTRUCTION");
		}
		
		input.close();
	}
	
	/**
	 * Loads the keywords dictionary, and creates
	 * the DFA.
	 */
	private static void loadDico() {
		try {
			Scanner br = new Scanner(new File("dico.txt"));
			String line;
			char c;
			int i, j;
			ArrayList<Letter> current, old;
			Letter temp = null;
			
			while (br.hasNext()) {
				line = br.next();
				current = LexicalAnalyzer.DFA;
				
				for (i = 0; i < line.length(); i++) {
					old = current;
					c = line.charAt(i);
					for (j = 0; j < current.size(); j++) {
						if (current.get(j).getValue() == c) {
							temp = current.get(j);
							current = current.get(j).getNext();
							break;
						}
					}
					
					if (old == current) {
						temp = new Letter(c, (i == line.length()-1));
						current.add(temp);
						current = temp.getNext();
					}
					
				}
				
				temp.setUnit(br.next());
				
				
			}
			
			br.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Checks if the given token is a Keyword
	 * @param input the token
	 * @return true if the token is a Keyword
	 */
	private static boolean checkKeyword(String input) {
		ArrayList<Letter> current = LexicalAnalyzer.DFA;
		ArrayList<Letter> old;
		Letter temp = null;
		boolean accept = false;
		
		for (int i = 0; i < input.length(); i++) {
			old = current;
			for (int j = 0; j < current.size(); j++) {
				if (current.get(j).getValue() == input.charAt(i)) {
					accept = current.get(j).isAccepting();
					temp = current.get(j);
					current = current.get(j).getNext();
				}
			}
			if (old == current) {
				accept = false;
				break;
			}
		}
		
		if (accept) {
			System.out.println("token: "+input+" --- lexical unit: "+temp.getUnit());
		}
		
		return accept;
	}
	
	/**
	 * Checks if the input is an Image type declaration
	 * @param input the input to check
	 * @return true if the input is an image type declaration
	 */
	private static boolean checkImageType(String input) {
		boolean accept = false;
		
		Pattern pattern = Pattern.compile("(s?)9(\\((\\d+)\\))?(v9(\\((\\d+)\\))?)?", Pattern.CASE_INSENSITIVE);
		Matcher match = pattern.matcher(input);
		if (match.matches()) {
			accept = true;
			System.out.println("token: "+input+" --- lexical unit: IMAGE_TYPE");
		};
		
		return accept;
	}
	
	/**
	 * Checks if the given input is an Integer
	 * @param input the input to check
	 * @return true if the input is an integer
	 */
	private static boolean checkInteger(String input) {
		boolean accept = false;
		boolean meaning = false;
		if ((input.charAt(0) == '+') || (input.charAt(0) == '-') ||
				((input.charAt(0) >= '1') && (input.charAt(0) <= '9'))) {
			meaning = ((input.charAt(0) >= '1') && (input.charAt(0) <= '9'));
			accept = true;
			for (int i = 1; i < input.length(); i++) {
				if (! (((input.charAt(i) >= '1') && (input.charAt(i) <= '9')) ||
						((input.charAt(i) >= '0') && (input.charAt(i) <= '9') && meaning))){
					accept = false;
				}
				if (!meaning)
					meaning = ((input.charAt(i) >= '1') && (input.charAt(i) <= '9'));
			}
			
			if (accept) {
				System.out.println("token: "+input+" --- lexical unit: INTEGER");
			}
		}
		
		return accept;
	}
	
	/**
	 * Checks if the given input is a Real
	 * @param input the input to check
	 * @return true if the input is a Real
	 */
	private static boolean checkReal(String input) {
		boolean accept = false;
		boolean point = true;
		if ((input.charAt(0) == '+') || (input.charAt(0) == '-') ||
				((input.charAt(0) >= '1') && (input.charAt(0) <= '9'))) {
			boolean meaning = ((input.charAt(0) >= '1') && (input.charAt(0) <= '9'));
			accept = true;
			for (int i = 1; i < input.length(); i++) {
				if (! (((input.charAt(i) >= '1') && (input.charAt(i) <= '9')) ||
						(((input.charAt(i) >= '0') && (input.charAt(i) <= '9')) && meaning) ||
						((input.charAt(i) == '.') && point)) ){
					if (input.charAt(i) == '.')
						point = false;
					accept = false;
				}
				if (!meaning)
					meaning = ((input.charAt(i) >= '1') && (input.charAt(i) <= '9'));
			}
			
			if (accept) {
				System.out.println("token: "+input+" --- lexical unit: REAL");
			}
		}
		
		return accept;
	}
	
	/**
	 * Checks if the given input is an identifier
	 * @param input the input to check
	 * @return true if the input is an identifier
	 */
	private static boolean checkIdentifier(String input) {
		boolean accept = false;
		if (((input.charAt(0) >= 'a') && (input.charAt(0) <= 'z')) ||
				((input.charAt(0) >= 'A') && (input.charAt(0) <= 'Z'))) {
			accept = true;
			for (int i = 1; i < input.length(); i++) {
				if (! (((input.charAt(i) >= 'a') && (input.charAt(i) <= 'z')) ||
						((input.charAt(i) >= 'A') && (input.charAt(i) <= 'Z')) ||
						((input.charAt(i) >= '0') && (input.charAt(i) <= '9')) ||
						(input.charAt(i) == '_') || (input.charAt(i) == '-'))) {
					accept = false;
				}
			}
			
			if (accept) {
				System.out.println("token: "+input+" --- lexical unit: IDENTIFIER");
			}
		}
		
		return accept;
	}
	
	/**
	 * Utilise le DFA en arbre pour vérifier si un input
	 * est une unité lexicale.
	 * @param input l'entrée à vérifier
	 * @return true si il appartient au langage
	 */
	private static boolean check(String input) {
		System.out.println(input);
		boolean accept = false;
		
		accept = LexicalAnalyzer.checkKeyword(input);
		
		if (!accept) {
			accept = LexicalAnalyzer.checkImageType(input);
		}
		
		if (!accept) {
			accept = LexicalAnalyzer.checkInteger(input);
		}
		
		if (!accept) {
			accept = LexicalAnalyzer.checkReal(input);
		}
		
		if (!accept) {
			accept = LexicalAnalyzer.checkIdentifier(input);
		}
		
		return accept;
	}
	
	/** 
	 * Checks if the line contains a string
	 * @param input the input line
	 */
	public static void checkString(String input) {
		Pattern pattern = Pattern.compile("\\'[a-zA-Z\\d\\+\\-\\*/:!? ]*\\'", Pattern.CASE_INSENSITIVE);
		Matcher match = pattern.matcher(input);
		while (match.find()) {
			System.out.println("token: "+match.group()+" --- lexical unit: STRING");
		}
	}

}
