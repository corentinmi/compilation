import identifiers.Image;
import identifiers.Letter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;


public class LexicalAnalyzer {
	
	private static ArrayList<Letter> DFA = new ArrayList<Letter>();

	/**
	 * Charge le dictionnaire, effectue une boucle qui lit l'entrée
	 * standard et la fait passer dans les DFA.
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String line = new String();
		
		LexicalAnalyzer.loadDico();
		System.out.println("Bonjour");
		
		Scanner input = new Scanner(System.in);
		//input.useDelimiter("\\.");
		
		while(line != "end") {
			line = input.next();
			if (LexicalAnalyzer.check(line))
				System.out.println("okay");
			//Image.check(line);
		}
		
		input.close();
	}
	
	/**
	 * Charge le dictionnaire des unités lexicales,
	 * crée un DFA en arbre.
	 */
	private static void loadDico() {
		try {
			BufferedReader br = new BufferedReader(new FileReader("dico.txt"));
			String line;
			char c;
			int i, j;
			ArrayList<Letter> current, old;
			Letter temp;
			
			while ((line = br.readLine()) != null) {
				current = LexicalAnalyzer.DFA;
				
				for (i = 0; i < line.length(); i++) {
					old = current;
					c = line.charAt(i);
					System.out.print("\n" + c + "---");
					for (Letter a : current)
						System.out.print(a.getValue() + "-");
					
					for (j = 0; j < current.size(); j++) {
						if (current.get(j).getValue() == c) {
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
			}
			
			br.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * Utilise le DFA en arbre pour vérifier si un input
	 * est une unité lexicale.
	 * @param input l'entrée à vérifier
	 * @return true si il appartient au langage
	 */
	private static boolean check(String input) {
		ArrayList<Letter> current = LexicalAnalyzer.DFA;
		ArrayList<Letter> old;
		boolean accept = false;
		
		for (int i = 0; i < input.length(); i++) {
			old = current;
			for (int j = 0; j < current.size(); j++) {
				if (current.get(j).getValue() == input.charAt(i)) {
					accept = current.get(j).isAccepting();
					current = current.get(j).getNext();
				}
			}
			if (old == current) {
				return false;
			}
		}
		
		return accept;
	}

}
