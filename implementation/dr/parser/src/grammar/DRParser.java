package grammar;

import java.io.FileInputStream;
import java.io.IOException;

import errors.SyntaxError;
import lexer.Lexer;
import lexer.Tag;
import lexer.Token;

public class DRParser {
	private Lexer lexer;
	private Token token;
	
	public DRParser (FileInputStream input) {
		lexer = new Lexer(input);
	}
	
	private void check(int tag) throws SyntaxError, Exception {
		if (token.getTag() == tag) {
			token = lexer.scan();
		} else {
			throw new SyntaxError();
		}
	}
	
	public void analyze() throws Exception {
		token = lexer.scan();
		E();
		System.out.println("ACCEPTED");
	}
	
	private void E() throws Exception {
		T();
		EPrime();
	}
	
	private void T() throws Exception {
		F();
		TPrime();
	}
	
	private void EPrime() throws Exception {
		if (token.getTag() == ((int) '+')) {
			check((int) '+');
			T();
			EPrime();
		} else if (token.getTag() == ((int) '-')) {
			check((int) '-');
			T();
			EPrime();
		} else {
			// do nothing
		}
	}
	
	private void F() throws SyntaxError, Exception {
		if (token.getTag() == Tag.NUMBER) {
			check(Tag.NUMBER);
		} else if (token.getTag() == Tag.ID) {
			check(Tag.ID);
		} else if (token.getTag() == ((int) '(')) {
			check((int) '(');
			E();
			check((int) ')');
		} else {
			throw new SyntaxError();
		}
	}
	
	private void TPrime() throws Exception {
		if (token.getTag() == ((int) '*')) {
			check((int) '*');
			F();
			TPrime();
		} else if (token.getTag() == ((int) '/')) {
			check((int) '/');
			F();
			TPrime();
		} else {
			// do nothing
		}
	}
}
