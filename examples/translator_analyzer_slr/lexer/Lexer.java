package lexer;

import java.io.*;
import java.util.Hashtable;

public class Lexer {
	private char peek;
	private Hashtable<String, Token> words = new Hashtable<String, Token>();
	private InputStream input;
	public static int line = 1;
	
	public Lexer(InputStream input) {
		this.peek = ' ';
		this.input = input;
	}
	
	private void readch() throws IOException {
		peek = (char) input.read();
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
			
		if (Character.isDigit(peek)) {
			int v = 0;
			do {
				v = (10 * v) + Character.digit(peek, 10);
				readch();
			} while ( Character.isDigit(peek) );
			return new Number(v);
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
