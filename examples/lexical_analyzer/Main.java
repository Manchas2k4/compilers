

import java.io.FileInputStream;

import lexer.Lexer;
import lexer.Tag;
import lexer.Token;

public class Main {
	public static void main(String args[]) throws Exception {
		if (args.length != 1) {
			System.out.println("usage: java ConsoleApp file");
			System.exit(0);
		}
		
		FileInputStream input = new FileInputStream(args[0]); 
		Lexer lex = new Lexer(input);
		Token token = lex.scan();
		while (token.getTag() != Tag.EOF) {
			System.out.println(token.toString());
			token = lex.scan();
		}
	}
}
