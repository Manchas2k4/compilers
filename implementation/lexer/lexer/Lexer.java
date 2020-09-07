package lexer;

import java.util.Hashtable;

import errors.EOFException;

public class Lexer {
	private char peek;
	private Hashtable<String, Token> words = new Hashtable<String, Token>();
	private InputFile input;

	public Lexer(String filename) throws Exception {
		input = new InputFile(filename);

		reserve( new Word("program", Tag.PROGRAM) );
		reserve( new Word("constante", Tag.CONSTANT) );
		reserve( new Word("var", Tag.VAR) );
		reserve( new Word("begin", Tag.BEGIN) );
		reserve( new Word("end", Tag.END) );
		reserve( new Word("integer", Tag.INTEGER) );
		reserve( new Word("real", Tag.REAL) );
		reserve( new Word("boolean", Tag.BOOLEAN) );
		reserve( new Word("string", Tag.STRING) );
		reserve( new Word("writeln", Tag.WRITELN) );
		reserve( new Word("readln", Tag.READLN) );
		reserve( new Word("while", Tag.WHILE) );
		reserve( new Word("do", Tag.DO) );
		reserve( new Word("repeat", Tag.REPEAT) );
		reserve( new Word("until", Tag.UNTIL) );
		reserve( new Word("for", Tag.FOR) );
		reserve( new Word("to", Tag.TO) );
		reserve( new Word("downto", Tag.DOWNTO) );
		reserve( new Word("if", Tag.IF) );
		reserve( new Word("then", Tag.THEN) );
		reserve( new Word("else", Tag.ELSE) );
		reserve( new Word("not", Tag.NOT) );
		reserve( new Word("div", Tag.DIV) );
		reserve( new Word("mod", Tag.MOD) );
		reserve( new Word("and", Tag.AND) );
		reserve( new Word("or", Tag.OR) );
	}
	
	private void reserve(Word w) {
		words.put(w.getLexeme(), w);
	}

	public boolean isReserved(Token t) {
		return (words.containsKey(t.toString().toLowerCase()));
	}
	
	private void readch() throws EOFException {
		peek = input.getChar();
	}

	private boolean readch(char c) throws EOFException {
		readch();
		if( peek != c ) 
			return false;
		return true;
	}
	
	private void skipWhiteSpaces() throws EOFException {
		peek = input.peekChar();
		while ( Character.isWhitespace(peek) ) {
			peek = input.getChar();
		}
	}
	
	private Token readCharacterString() throws EOFException {
		String cs = "" + peek;
		
		peek = input.getChar();
		while ( peek != '"') {
			cs += peek;
			peek = input.getChar();
		}
		cs += peek;
		readch();
		
		return new CharacterString(cs);
	}
	
	private Token readComments() throws EOFException {
		int prev = input.position;
		int current = input.position + 1;
		
		 while (current < input.size && input.data[prev] != '*' && input.data[current] != ')') {
			prev = current;
			current++;
		}
		
		if (current >= input.size) throw new EOFException();
		
		input.position = current + 1;
		return new Token(Tag.COMMMENTS);
	}

	public Token scan() throws EOFException {
		skipWhiteSpaces();
		
		switch( peek ) {
			case '(' :	if( readch('*') ) {
							readch();
							return readComments();
						} else 
							return new Token('(');
			
			case '<' :	if( readch('=') ) 
							return Word.le;   
						else if ( readch('>') )
							return Word.ne;
						else 
							return new Token('<');
			
			case '>' :	if( readch('=') ) 
							return Word.ge;   
						else 
							return new Token('>');
			
			case '=' : if( readch('=') ) 
							return Word.eq;   
						else 
							return new Token('=');
			
			case ':' : 	if( readch('=') ) 
							return Word.assing;
						else 
							return new Token(':');
			
			case '"' :	return readCharacterString();
		}

		if (Character.isDigit(peek)) {
			int v = 0;
			do {
				v = (10 * v) + Character.digit(peek, 10);
				readch();
			} while ( Character.isDigit(peek) );
			if( peek != '.' ) return new Integer(v);
			float x = v; float d = 10;
			for(;;) {
				readch();
				if( !Character.isDigit(peek) ) break;
				x = x + Character.digit(peek, 10) / d; d = d * 10;
			}
			return new Real(x);
		}

		if (Character.isLetter(peek)) {
			StringBuffer b = new StringBuffer();

			do {
				b.append(Character.toLowerCase(peek));
				readch();
			} while ( Character.isJavaIdentifierPart(peek) ) ;
			String s = b.toString();
			Word w = (Word) words.get(s);
			if (w != null)
				return w;

			w = new Word(s, Tag.ID);
			words.put(s, w);
			return w;
		}

		Token tok = new Token(peek); 
		readch();
		return tok;
	}
}
