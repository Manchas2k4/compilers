package lexer;

import java.io.*;
import java.util.Hashtable;

public class Lexer {
	private char peek;
	private Hashtable<String, Token> words = new Hashtable<String, Token>();
	private InputStream input;
	public static int line = 1;
	
	public void reserve(Word w) {
		words.put(w.getLexeme(), w);
	}
	
	public boolean isReserved(Token t) {
		return (words.containsKey(t.toString().toLowerCase()));
	}
	
	public Lexer(InputStream input) {
		this.peek = ' ';
		this.input = input;
		
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
	
	private void readch() throws IOException {
		peek = (char) input.read();
	}
	
	boolean readch(char c) throws IOException {
      readch();
      if( peek != c ) return false;
      peek = ' ';
      return true;
   }
	
	public Token scan() throws IOException {
		for ( ; ; readch() ) {
			if (peek == ' ' || peek == '\t') {
				continue;
			} else if (peek == '\n') {
				line = line + 1;
			} else {
				break;
			}
		}
		switch( peek ) {
			case '=':
			if( readch('=') ) return Word.eq;   else return new Token('=');
			case '<':
			if( readch('=') ) return Word.le;   else return new Token('<');
			case '>':
			if( readch('=') ) return Word.ge;   else return new Token('>');
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
			} while ( Character.isLetterOrDigit(peek) ) ;
			String s = b.toString();
			Word w = (Word) words.get(s);
			if (w != null) 
				return w;
		
			w = new Word(s, Tag.ID);
			words.put(s, w);
			return w;
		}
		
		Token tok = new Token(peek); peek = ' ';
		return tok;
	}
}
