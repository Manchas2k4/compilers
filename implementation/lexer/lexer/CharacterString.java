package lexer;

public class CharacterString extends Token {
	private String value;
	
	public CharacterString(String value) {
		super(Tag.CHARACTERSTRING);
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
	public String toString() {
		return "STRING - VALUE = " + value;
	}
}
