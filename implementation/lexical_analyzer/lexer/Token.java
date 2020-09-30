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
		String s = "TOKEN, symbol = ";
		
		switch (tag) {
		case Tag.EOF		: s += "EOF"; break;
		default				: s += (char) tag; break;
		}
		
		return s;
	}
}