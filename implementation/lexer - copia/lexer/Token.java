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
			case Tag.PROGRAM	: return "PROGRAM";
			case Tag.CONSTANT	: return "CONSTANT";
			case Tag.VAR		: return "VAR";
			case Tag.BEGIN		: return "BEGIN";
			case Tag.END		: return "END";
			case Tag.INTEGER	: return "INTEGER";
			case Tag.REAL		: return "REAL";
			case Tag.BOOLEAN	: return "BOOLEAN";
			case Tag.STRING		: return "STRING";
			case Tag.WRITELN	: return "WRITELN";
			case Tag.READLN		: return "READLN";
			case Tag.WHILE		: return "WHILE";
			case Tag.DO			: return "DO";
			case Tag.REPEAT		: return "REPEAT";
			case Tag.UNTIL		: return "UNTIL";
			case Tag.FOR		: return "FOR";
			case Tag.TO			: return "TO";
			case Tag.DOWNTO		: return "DOWNTO";
			case Tag.IF			: return "IF";
			case Tag.THEN		: return "THEN";
			case Tag.ELSE		: return "ELSE";
			case Tag.NOT		: return "NOT";
			case Tag.DIV		: return "DIV";
			case Tag.MOD		: return "MOD";
			case Tag.AND		: return "AND";
			case Tag.OR			: return "OR";
			case Tag.EQ			: return "==";
			case Tag.NEQ		: return "<>";
			case Tag.LE			: return "<=";
			case Tag.GE			: return ">=";
			case Tag.MINUS		: return "-";
			case Tag.ASSIGN		: return ":=";
			case Tag.ID			: return "ID";
			case Tag.EOF		: return "EOF";
			default				: return "" + (char) tag;
		}
	}
}