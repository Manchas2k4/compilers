import errors.EOFException;
import lexer.Lexer;
import lexer.Token;
import lexer.Integer;
import lexer.Real;

public class Main {
	public static void main(String args[]) throws Exception {
		if (args.length != 1) {
			System.out.println("usage: java ConsoleApp file");
			System.exit(0);
		}
		 
		Lexer lex = new Lexer(args[0]);
		Token token = lex.scan();
		try {
			while (true) {
				System.out.println("" + token.toString());
				token = lex.scan();
			}
		} catch(EOFException eofe) {
		}
		System.out.println("EOF");
	}
}
