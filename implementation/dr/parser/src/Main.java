import java.io.FileInputStream;

import grammar.DRParser;

public class Main {
	public static void main(String args[]) throws Exception {
		if (args.length != 1) {
			System.out.println("usage: java ConsoleApp file");
			System.exit(0);
		}
		
		FileInputStream input = new FileInputStream(args[0]); 
		DRParser parser = new DRParser(input);
		parser.analyze();
	}
}
