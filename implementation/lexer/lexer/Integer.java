package lexer;

public class Integer extends Token {
	private int value;
	
	public Integer(int value) {
		super(Tag.INTEGER);
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
	
	public String toString() {
		return "INTEGER - VALUE = " + value;
	}
}
