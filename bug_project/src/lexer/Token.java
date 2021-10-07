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
		case Tag.EQ		: return "==";
		case Tag.GEQ	: return ">=";
		case Tag.LEQ	: return "<=";
		case Tag.NEQ	: return "!="; 
		case Tag.ID		: return "ID";
		case Tag.NUMBER	: return "NUMBER";
		case Tag.STRING	: return "STRING";
		case Tag.TRUE	: return "TRUE"; 
		case Tag.FALSE	: return "FALSE";
		case Tag.VAR	: return "VAR";
		case Tag.PRINT	: return "PRINT";
		case Tag.WHILE	: return "WHILE";
		case Tag.IF		: return "IF";
		case Tag.ELSE	: return "ELSE";
		case Tag.EOF	: return "EOF";
		case Tag.HOME: return "HOME";
		case Tag.PENUP: return "PENUP";
		case Tag.PENDOWN: return "PENDOWN";
		case Tag.FORWARD: return "FORWARD";
		case Tag.BACKWARD: return "BACKWARD";
		case Tag.LEFT: return "LEFT";
		case Tag.RIGHT: return "RIGHT";
		case Tag.MOVE: return "MOVE";
		default			: return "TOKEN <-> " + (char) tag;
		}
	}
}
