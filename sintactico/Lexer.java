import java.io.StreamTokenizer;
import java.io.Reader;

public class Lexer {
	private StreamTokenizer st;
	private String previous, current;
	private int numberOfTokens, t;

	public Scanner(Reader input) {
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
	}
	
