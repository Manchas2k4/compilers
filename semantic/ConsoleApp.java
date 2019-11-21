import java.io.*;
import errors.*;
import nodes.*;

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
	
	public static void main(String args[]) {
		if (args.length != 1) {
			System.out.println("usage: java ConsoleApp file");
			System.exit(0);
		}
		
		Translator t = new Translator();
		VoidNode result = null;
		try {
			result = t.process(new StringReader(readFile(args[0])));
			t.eval();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
