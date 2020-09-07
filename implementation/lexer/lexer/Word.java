package lexer;

public class Word extends Token {
	private String lexeme;
	
	public Word(String lexeme, int tag) {
		super(tag);
		this.lexeme = lexeme;
	}
	
	public String getLexeme() {
		return lexeme;
	}
	
	public String toString() {
		return "WORD - LEXEME = " + lexeme;
	}
	
	public static final Word 
		eq  = new Word( "==", Tag.EQ  ),  ne = new Word( "<>", Tag.NEQ ),
		le  = new Word( "<=", Tag.LE  ),  ge = new Word( ">=", Tag.GE ),
		minus  = new Word( "minus", Tag.MINUS ),
		assing = new Word( ":=", Tag.ASSIGN ),
		True   = new Word( "true",  Tag.TRUE  ),
		False  = new Word( "false", Tag.FALSE );
}
