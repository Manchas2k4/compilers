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
}
