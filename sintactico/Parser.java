import java.io.Scanner;
import errors.*;

public class Parser {
	private Scanner scanner;
	private String token;
	
	public Parser() {
		scanner = null;
		token = null;
	}
	
	public void error(String m) throws SintaxisError {
		int n = sca
