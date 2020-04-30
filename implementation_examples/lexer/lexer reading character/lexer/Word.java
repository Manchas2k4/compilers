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
		return super.toString() + " <-> lexeme = " + lexeme;
	}
	
	public static final Word
		And = new Word( "and", Tag.AND ),  			Or = new Word( "or", Tag.OR ),
		eq = new Word("==", Tag.EQ), 				ne = new Word("!=", Tag.NE),
		le  = new Word( "<=", Tag.LE  ),  			ge = new Word( ">=", Tag.GE ),
		minus  = new Word( "minus", Tag.MINUS ),	Not = new Word( "not", Tag.NOT ),
		True   = new Word( "true",  Tag.TRUE  ),	False  = new Word( "false", Tag.FALSE );
}
