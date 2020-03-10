package lexer;

import java.io.*;
import java.util.Hashtable;

public class Lexer {
	private char peek;
	private Hashtable<String, Token> words = new Hashtable<String, Token>();
	private InputStream input;
	public static int line = 1;
	
	private void reserve(Word w) {
		words.put(w.getLexeme(), w);
	}
	
	public Lexer(InputStream input) {
		this.peek = ' ';
		this.input = input;
		reserve(new Word("to", Tag.TO));
		reserve(new Word("end", Tag.END));
		reserve(new Word("make", Tag.MAKE));
		reserve(new Word("repeat", Tag.REPEAT));
		reserve(new Word("if", Tag.IF));
		reserve(new Word("ifelse", Tag.IFELSE));
		reserve(new Word("forward", Tag.FORWARD));
		reserve(new Word("fd", Tag.FORWARD));
		reserve(new Word("backward", Tag.BACKWARD));
		reserve(new Word("bk", Tag.BACKWARD));
		reserve(new Word("left", Tag.LEFT));
		reserve(new Word("lt", Tag.LEFT));
		reserve(new Word("right", Tag.RIGHT));
		reserve(new Word("rt", Tag.RIGHT));
		reserve(new Word("setx", Tag.SETX));
		reserve(new Word("sety", Tag.SETY));
		reserve(new Word("setxy", Tag.SETXY));
		reserve(new Word("home", Tag.HOME));
		reserve(new Word("clear", Tag.CLEAR));
		reserve(new Word("cs", Tag.CLEAR));
		reserve(new Word("circle", Tag.CIRCLE));
		reserve(new Word("arc", Tag.ARC));
		reserve(new Word("penup", Tag.PENUP));
		reserve(new Word("pu", Tag.PENUP));
		reserve(new Word("pendown", Tag.PENDOWN));
		reserve(new Word("pd", Tag.PENDOWN));
		reserve(new Word("color", Tag.COLOR));
		reserve(new Word("penwidth", Tag.PENWIDTH));
		reserve(new Word("print", Tag.PRINT));
		reserve(Word.And); reserve(Word.Or);
		reserve(Word.True); reserve(Word.False);
		reserve(Word.Not);
	}
	
	private void readch() throws IOException {
		peek = (char) input.read();
	}
	
	private boolean readch(char c) throws IOException {
		readch();
		if (peek != c) 
			return false;
		
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
			
		switch(peek) {
			case '=' : if (readch('=')) return Word.eq;
			case '!' : if (readch('=')) return Word.ne;
			case '<' : if (readch('=')) return Word.le; else return new Token('<');
			case '>' : if (readch('=')) return Word.le; else return new Token('>');
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
