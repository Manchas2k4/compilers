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
		case Tag.AND		: return "AND";
		case Tag.OR			: return "OR";
		case Tag.GE			: return ">=";
		case Tag.LE			: return "<=";
		case Tag.EQ			: return "==";
		case Tag.NE			: return "!="; 
		case Tag.NOT		: return "NOT"; 
		case Tag.TRUE		: return "TRUE"; 
		case Tag.FALSE		: return "FALSE";
		case Tag.MINUS		: return "MINUS"; 
		case Tag.MOD		: return "MOD"; 
		case Tag.TO			: return "TO";  
		case Tag.END		: return "END";  
		case Tag.MAKE		: return "MAKE";
		case Tag.REPEAT		: return "REPEAT";
		case Tag.IF			: return "IF";
		case Tag.IFELSE		: return "IFELSE";
		case Tag.FORWARD	: return "FORWARD";
		case Tag.BACKWARD	: return "BACKWARD";
		case Tag.LEFT		: return "LEFT";
		case Tag.RIGHT		: return "RIGHT";
		case Tag.SETX		: return "SETX";
		case Tag.SETY		: return "SETY";
		case Tag.SETXY		: return "SETXY";
		case Tag.HOME		: return "HOME";
		case Tag.CLEAR		: return "CLEAR";
		case Tag.CIRCLE		: return "CIRCLE";
		case Tag.ARC		: return "ARC";
		case Tag.PENUP		: return "PENUP";
		case Tag.PENDOWN	: return "PENDOWN";
		case Tag.COLOR		: return "COLOR";
		case Tag.PENWIDTH	: return "PENDWIDTH";
		case Tag.PRINT		: return "PRINT";
		case Tag.NUMBER		: return "NUMBER";
		case Tag.ID			: return "ID";
		case Tag.EOF		: return "EOF";
		default				: return "" + (char) tag;
		}
	}
}