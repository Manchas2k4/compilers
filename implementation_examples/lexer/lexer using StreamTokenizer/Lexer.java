import java.io.StreamTokenizer;
import java.io.Reader;

public class Lexer {
	private StreamTokenizer st;
	private String previous, current;
	private int numberOfTokens, t;

	public Lexer(Reader input) {
		st = new StreamTokenizer(input);
		st.eolIsSignificant(false);
		st.lowerCaseMode(true);
		st.parseNumbers();
		st.slashSlashComments(true);
		st.	slashStarComments(true);
		st.quoteChar('\"');
		st.ordinaryChar('/');
		st.ordinaryChar('-');
		st.ordinaryChar('.');
		st.ordinaryChar(',');
		
		previous = "ERROR";
		current = "ERROR";
		numberOfTokens = 0;
	}
	
	public String getStringValue() {
		if (t == st.TT_WORD) {
			return st.sval;
		} else if (t == st.TT_NUMBER) {
			return "" + st.nval;
		} else if (t == '"') {
			return st.sval;
		} else {
			return current;
		}
	}
	
	public double getNumberValue() {
		return st.nval;
	}
	
	public int getLineNumber() {
		return st.lineno();
	}
	
	public String getNextToken() {
		numberOfTokens++;
		previous = current;
		try {
			t = st.nextToken();
			if (t == st.TT_EOF) {
				current = "EOF";
			} else if (t == st.TT_NUMBER) {
				current = "NUMBER";
			} else if (t == st.TT_WORD) {
				if (getStringValue().equals("print")) {
					current = "PRINT";
				} else if (getStringValue().equals("while")) {
					current = "WHILE";
				} else if (getStringValue().equals("if")) {
					current = "IF";
				}  else if (getStringValue().equals("else")) {
					current = "ELSE";
				} else if (getStringValue().equals("var")) {
					current = "VAR";
				} else {
					current = "ID";
				}
			} else if (t == '"') {
				current = "STRING";
			} else if (t == '+') {
				current = "+";
			} else if (t == '-') {
				current = "-";
			} else if (t == '*') {
				current = "*";
			} else if (t == '/') {
				current = "/";
			} else if (t == '^') {
				current = "^";
			} else if (t == '(') {
				current = "(";
			} else if (t == ')') {
				current = ")";
			} else if (t == '{') {
				current = "{";
			} else if (t == '}') {
				current = "}";
			} else if (t == '=') {
				t = st.nextToken();
				if (t != '=') {
					current = "=";
					st.pushBack();
				} else {
					current = "==";
				}
			} else if (t == '<') {
				t = st.nextToken();
				if (t != '=') {
					current = "<";
					st.pushBack();
				} else {
					current = "<=";
				}
			} else if (t == '>') {
				t = st.nextToken();
				if (t != '=') {
					current = ">";
					st.pushBack();
				} else {
					current = ">=";
				}
			} else if (t == '!') {
				t = st.nextToken();
				if (t != '=') {
					current = "!";
					st.pushBack();
				} else {
					current = "!=";
				}
			} else if (t == ';') {
				current = ";";
			} else if (t == ',') {
				current = ",";
			} else {
				current = getStringValue();
			}
		} catch (Exception e) {
			System.out.println("Error reading the next token");
			current = "ERROR";
		}
		return current;
	}
	
	public void pushBackToken() {
		st.pushBack();
		current = previous;
		previous = "ERROR";
	}
	
	public String getCurrent() {
		return current;
	}
}