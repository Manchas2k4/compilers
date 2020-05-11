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
		String aux;
		
		token = lexer.scan();
		aux = E();
		System.out.println("ACCEPTED - Result " + aux);
	}
	
	private String E() throws Exception {
		String aux;
		
		aux = T();
		return EPrime(aux);
	}
	
	private String T() throws Exception {
		String aux;
		
		aux = F();
		return TPrime(aux);
	}
	
	private String EPrime(String inherited) throws Exception {
		String synthesized, aux;
		
		if (token.getTag() == ((int) '+')) {
			check((int) '+');
			aux = inherited + " " + T() + " +";
			return EPrime(aux);
		} else if (token.getTag() == ((int) '-')) {
			check((int) '-');
			aux = inherited + " " + T() + " -";
			return EPrime(aux);
		} else {
			return inherited;
		}
	}
	
	private String F() throws SyntaxError, Exception {
		String aux = token.toString();
		
		if (token.getTag() == Tag.NUMBER) {
			check(Tag.NUMBER);
			return aux;
		} else if (token.getTag() == Tag.ID) {
			check(Tag.ID);
			return aux;
		} else if (token.getTag() == ((int) '(')) {
			check((int) '(');
			aux = E();
			check((int) ')');
			return aux;
		} else {
			throw new SyntaxError();
		}
	}
	
	private String TPrime(String inherited) throws Exception {
		String aux;
		
		if (token.getTag() == ((int) '*')) {
			check((int) '*');
			aux = inherited + " " + F() + " *";
			return TPrime(aux);
		} else if (token.getTag() == ((int) '/')) {
			check((int) '/');
			aux = inherited + " " + F() + " /";
			return TPrime(aux);
		} else {
			return inherited;
		}
	}
}
