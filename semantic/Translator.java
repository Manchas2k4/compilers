import java.io.StreamTokenizer;
import java.io.Reader;
import errors.*;
import nodes.*;
import bugs.*;

public class Translator {
	private Lexer lexer;
	private String token;
	private VoidNode tree;
	
	public Translator() {
		lexer = null;
		token = null;
		tree = null;
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
	
	public VoidNode process(Reader r) throws SyntaxError {
		lexer = new Lexer(r);
		token = lexer.getNextToken();
		tree = LS();
		check("EOF");
		return tree;
	}
	
	public void eval() throws SemanticError {
		Bug.getInstance().forward(10);
		if (tree != null) {
			tree.eval(new SymbolTable());
		}
	}
	
	private FactorNode E() throws SyntaxError {
		FactorNode val = T();
		return R(val);
	}
	
	private FactorNode R(FactorNode left) throws SyntaxError {
		if (token.equals("+")) {
			check("+");
			FactorNode val = T();
			return R(new BinaryOp("+", left, val));
		} else if (token.equals("-")) {
			check("-");
			FactorNode val = T();
			return R(new BinaryOp("-", left, val));
		} else {
			return left;
		}
	}
	
	private FactorNode T() throws SyntaxError {
		FactorNode val = F();
		return U(val);
	}
	
	private FactorNode U(FactorNode left) throws SyntaxError {
		if (token.equals("*")) { 
			check("*");
			FactorNode val = F();
			return U(new BinaryOp("*", left, val));
		} else if (token.equals("/")) { 
			check("/");
			FactorNode val = F();
			return U(new BinaryOp("/", left, val));
		} else if (token.equals("^")) { 
			check("^");
			FactorNode val = F();
			return U(new BinaryOp("^", left, val));
		} else {
			return left;
		}
	}
	
	private FactorNode F() throws SyntaxError {
		if (token.equals("NUMBER")) {
			double val = lexer.getNumberValue();
			check("NUMBER");
			return new NumberNode(val);
		} else if (token.equals("ID")) {
			String val = lexer.getStringValue();
			check("ID");
			return new Id(val);
		} else if (token.equals("(")) {
			check("(");
			FactorNode val = E();
			check(")");
			return val;
		} else {
			error("...... invalid expression\n");
			return null;
		}
	}
	
	private RelationalOp EBool() throws SyntaxError {
		FactorNode val1 = E();
		if (token.equals("==")) {
			check("==");
			FactorNode val2 = E();
			return new RelationalOp("==", val1, val2);
		} else if (token.equals("<=")) {
			check("<=");
			FactorNode val2 = E();
			return new RelationalOp("<=", val1, val2);
		} else if (token.equals(">=")) {
			check(">=");
			FactorNode val2 = E();
			return new RelationalOp(">=", val1, val2);
		} else if (token.equals(">")) {
			check(">");
			FactorNode val2 = E();
			return new RelationalOp(">", val1, val2);
		} else if (token.equals("<")) {
			check("<");
			FactorNode val2 = E();
			return new RelationalOp("<", val1, val2);
		} else if (token.equals("!=")) {
			check("!=");
			FactorNode val2 = E();
			return new RelationalOp("!=", val1, val2);
		} else {
			error("...... invalid boolean expression\n");
			return null;
		}
	}
	
	private VoidNode S() throws SyntaxError {
		if (token.equals("IF")) {
			check("IF");
			check("(");
			BooleanNode val1 = EBool();
			check(")");
			VoidNode val2 = S();
			VoidNode val3 = X();
			return new If(val1, val2, val3);
		} else if (token.equals("WHILE")) {
			check("WHILE");
			check("(");
			BooleanNode val1 = EBool();
			check(")");
			VoidNode val2 = S();
			return new While(val1, val2);
		} else if (token.equals("ID")) {
			String val1 = lexer.getStringValue();
			check("ID");
			check("=");
			FactorNode val2 = E();
			check(";");
			return new Assignment(val1, val2);
		} else if (token.equals("VAR")) {
			check("VAR");
			String val1 = lexer.getStringValue();
			check("ID");
			check(";");
			return new Declaration(val1);
		} else if (token.equals("PRINT")) {
			check("PRINT");
			check("(");
			String val1 = lexer.getStringValue();
			check("STRING");
			check(",");
			FactorNode val2 = E();
			check(")");
			check(";");
			return new Print(val1, val2);
		} else if (token.equals("{")) {
			check("{");
			VoidNode val1 = LS();
			check("}");
			return val1;
		} else {
			error("..... invalid expression\n");
			return null;
		}
	}
	
	private VoidNode X() throws SyntaxError {
		if (token.equals("ELSE")) {
			check("ELSE");
			return S();
		} else {
			return null;
		}
	}
	
	private VoidNode LS() throws SyntaxError {
		if (token.equals("IF") || token.equals("WHILE") || token.equals("VAR") || 
			token.equals("ID") || token.equals("PRINT")) {
			VoidNode val1 = S();
			VoidNode val2 = LS();
			return new Sequence(val1, val2);
		} else {
			return null;
		}
	}
}
	
