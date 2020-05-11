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
		switch (tag) {
		case Tag.NUMBER		: return "NUMBER";
		case Tag.ID			: return "ID";
		case Tag.EOF		: return "EOF";
		default				: return "" + (char) tag;
		}
	}
}