package identifiers;

import java.util.ArrayList;

public class Letter {

	private char value;
	private boolean accept;
	
	public char getValue() {
		return value;
	}

	private ArrayList<Letter> next = new ArrayList<Letter>();
	
	public Letter(char value, boolean accept) {
		this.value = value;
		this.accept = accept;
	}
	
	public boolean isAccepting() {
		return this.accept;
	}
	
	public void addNext(Letter next) {
		this.next.add(next);
	}
	
	public ArrayList<Letter> getNext() {
		return this.next;
	}
	
}
