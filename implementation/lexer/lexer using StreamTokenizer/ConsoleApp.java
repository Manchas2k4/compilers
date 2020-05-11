import java.io.*;
import errors.*;

public class ConsoleApp {
	public static String readFile(String filename) throws IOException {
		BufferedReader in;
		String result, line;
		
		result = "";
		in = new BufferedReader(new FileReader(filename));
		while ( (line = in.readLine()) != null ) {
			result = result + line + "\n";
		}
		in.close();
		return result;
	}
	
	public static void main(String args[]) throws IOException {
		if (args.length != 1) {
			System.out.println("usage: java ConsoleApp file");
			System.exit(0);
		}
		
		String token;
		Lexer lexer = new Lexer(new StringReader(readFile(args[0])));
		while ( !(token = lexer.getNextToken()).equals("EOF") ) {
			System.out.println(token);
		}
	}
}
