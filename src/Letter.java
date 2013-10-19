


import java.util.ArrayList;

public class Letter {

	private char value;
	private boolean accept;
	private String unit;
	
	/**
	 * Returns the corresponding lexical unit to an accepting state
	 * @return the lexical unit
	 */
	public String getUnit() {
		return unit;
	}

	/**
	 * Sets the lexical unit to an accepting state
	 * @param unit the lexical unit
	 */
	public void setUnit(String unit) {
		this.unit = unit;
	}

	/**
	 * Returns the letter required to pass to this state
	 * @return the state letter
	 */
	public char getValue() {
		return value;
	}

	private ArrayList<Letter> next = new ArrayList<Letter>();
	
	/**
	 * Creates the DFA state
	 * @param value value of the letter required to enter this state
	 * @param accept determines if the state is accepting
	 */
	public Letter(char value, boolean accept) {
		this.value = value;
		this.accept = accept;
	}
	
	/**
	 * Determines if the state is accepting
	 * @return true if this is an accepting state
	 */
	public boolean isAccepting() {
		return this.accept;
	}
	
	/**
	 * Adds a relation between this state and a next state
	 * @param next the next state
	 */
	public void addNext(Letter next) {
		this.next.add(next);
	}
	
	/**
	 * Obtains the list of connected states
	 * @return the list of states
	 */
	public ArrayList<Letter> getNext() {
		return this.next;
	}
	
}
