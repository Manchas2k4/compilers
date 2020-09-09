package lexer;

public class Token {
	private final int tag;
	
	public Token(int tag) {
		this.tag = tag;
	}
	
	public int getTag() {
		return tag;
	}
	
	public String toString() {
		return "TOKEN - VALUE = " + ((char) tag);
	}
}