import java.io.FileInputStream;
import java.io.IOException;
import java.util.Hashtable;

import lexer.Lexer;
import lexer.Tag;
import lexer.Token;
import lexer.Integer;
import lexer.Real;

public class Main {
	public static void main(String args[]) throws IOException {
		if (args.length != 1) {
			System.out.println("usage: java ConsoleApp file");
			System.exit(0);
		}
		
		FileInputStream input = new FileInputStream(args[0]); 
		Lexer lex = new Lexer(input);
		Token token = lex.scan();
		while (token.getTag() != Tag.EOF) {
			System.out.print("" + token.toString());
			if (lex.isReserved(token)) {
				System.out.print(" - RESERVED");
				if (token instanceof Integer) {
					System.out.print(" - lexeme = " + ((Integer) token).getValue());
				} else if (token instanceof Real) {
					System.out.print(" - lexeme = " + ((Real) token).getValue());
				} 
			} else {
				System.out.print(" - TOKEN");
			}
			System.out.println();
			token = lex.scan();
		}
		
		System.out.println("EOF");
	}
}
