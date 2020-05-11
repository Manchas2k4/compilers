import java.io.FileInputStream;

import grammar.LRParser;

public class Main {
	public static void main(String args[]) throws Exception {
		if (args.length != 1) {
			System.out.println("usage: java ConsoleApp file");
			System.exit(0);
		}
		
		FileInputStream input = new FileInputStream(args[0]); 
		LRParser parser = new LRParser(input);
		parser.analyze();
	}
}
