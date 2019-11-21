import java.io.Reader;
import errors.*;

public class Parser {
	private Lexer lexer;
	private String token;
	
	public Parser() {
		lexer = null;
		token = null;
	}
	
	public void error(String s) throws NullPointerException, SyntaxError {
		int n;
		
		if (lexer == null) {
			throw new NullPointerException();
		}
		
		n = lexer.getLineNumber();
		throw new SyntaxError("Error in line: " + n + "\n" + s);
	}
	
	public void check(String s) throws SyntaxError {
		if (token.equals(s)) {
			token = lexer.getNextToken();
		} else {
			error("........ invalid character: " + lexer.getStringValue() + "\n");
		}
	}
	
	public void message(String s) {
		System.out.println(s);
	}
	
	public void process(Reader r) throws SyntaxError {
		lexer = new Lexer(r);
		token = lexer.getNextToken();
		LS();
		check("EOF");
	}
	
	private void E() throws SyntaxError {
		T();
		R();
	}
	
	private void R() throws SyntaxError {
		if (token.equals("+")) {
			check("+");
			T();
			R();
		} else if (token.equals("-")) {
			check("-");
			T();
			R();
		} else {
			// do nothing -> epsilon
		}
	}
	
	private void T() throws SyntaxError {
		F();
		U();
	}
	
	private void U() throws SyntaxError {
		if (token.equals("*")) { 
			check("*");
			F();
			U();
		} else if (token.equals("/")) { 
			check("/");
			F();
			U();
		} else if (token.equals("^")) { 
			check("^");
			F();
			U();
		} else {
			// do nothing -> epsilon
		}
	}
	
	private void F() throws SyntaxError {
		if (token.equals("NUMBER")) {
			check("NUMBER");
		} else if (token.equals("ID")) {
			check("ID");
		} else if (token.equals("(")) {
			check("(");
			E();
			check(")");
		} else {
			error("...... invalid expression\n");
		}
	}
	
	private void EBool() throws SyntaxError {
		E();
		if (token.equals("==")) {
			check("==");
			E();
		} else if (token.equals("<=")) {
			check("<=");
			E();
		} else if (token.equals(">=")) {
			check(">=");
			E();
		} else if (token.equals(">")) {
			check(">");
			E();
		} else if (token.equals("<")) {
			check("<");
			E();
		} else if (token.equals("!=")) {
			check("!=");
			E();
		}
	}
	
	private void S() throws SyntaxError {
		if (token.equals("IF")) {
			check("IF");
			check("(");
			EBool();
			check(")");
			S();
			X();
		} else if (token.equals("WHILE")) {
			check("WHILE");
			check("(");
			EBool();
			check(")");
			S();
		} else if (token.equals("ID")) {
			check("ID");
			check("=");
			E();
			check(";");
		} else if (token.equals("VAR")) {
			check("VAR");
			check("ID");
			check(";");
		} else if (token.equals("PRINT")) {
			check("PRINT");
			check("(");
			check("STRING");
			check(",");
			E();
			check(")");
			check(";");
		} else if (token.equals("{")) {
			check("{");
			LS();
			check("}");
		} else {
			error("..... invalid expression\n");
		}
	}
	
	private void X() throws SyntaxError {
		if (token.equals("ELSE")) {
			check("ELSE");
			S();
		} else {
			// do nothing -> epsilon
		}
	}
	
	private void LS() throws SyntaxError {
		if (token.equals("IF") || token.equals("WHILE") || token.equals("VAR") || 
			token.equals("ID") || token.equals("PRINT")) {
			S();
			LS();
		} else {
			// do nothing -> epsilon
		}
	}
}