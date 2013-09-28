import identifiers.Variable;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class LexicalAnalyzer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String line = new String();
		System.out.println("Bonjour");
		
		while(true) {
			try{
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				while((line=br.readLine())!=null){
					Variable.check(line);
				}
		 
			}catch(IOException io){
				io.printStackTrace();
			}	
		}
	}

}
