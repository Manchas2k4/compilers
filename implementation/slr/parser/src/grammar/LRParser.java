package grammar;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Stack;

import lexer.Lexer;
import lexer.Tag;
import lexer.Token;

public class LRParser {
	private HashMap<String, HashMap<String, Action>> actionTable;
	private HashMap<String, HashMap<String, String>> goToTable;
	private Grammar g;
	private Stack<String> stack;
	private Stack<String> symbols;
	private Lexer lexer;

	public LRParser(FileInputStream input) {
		g = new Grammar();
		lexer = new Lexer(input);
		stack = new Stack<String>();
		symbols = new Stack<String>();

		loadActionTable();
		loadGoToTable();
	}

	private void loadActionTable() {
		BufferedReader fileIn;
		String line, tokens[], state, terminal, newState;
		int size;
		ActionType type;

		try {
			fileIn = new BufferedReader(new FileReader("data/actions.csv"));

			line = fileIn.readLine();
			size = Integer.parseInt(line);
			actionTable = new HashMap<String, HashMap<String, Action>>();
			for (int i = 0; i < size; i++) {
				actionTable.put("" + i, new HashMap<String, Action>());
			}

			while ( (line = fileIn.readLine()) != null ) {
				tokens = line.split(",");
				state = tokens[0];

				terminal = tokens[1].toUpperCase();

				type = ActionType.SHIFT;
				if (tokens[2].equals("SHIFT")) {
					type = ActionType.SHIFT;
				} else if (tokens[2].equals("REDUCE")) {
					type = ActionType.REDUCE;
				} else if (tokens[2].equals("ACCEPT")) {
					type = ActionType.ACCEPT;
				}
				newState = tokens[3];

				actionTable.get(state).put(terminal, new Action(type, newState));
			}
			fileIn.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void loadGoToTable() {
		BufferedReader fileIn;
		String line, tokens[], state, nonTerminal, newState;
		int size;

		try {
			fileIn = new BufferedReader(new FileReader("data/gotos.csv"));

			line = fileIn.readLine();
			size = Integer.parseInt(line);
			goToTable = new HashMap<String, HashMap<String, String>>();
			for (int i = 0; i < size; i++) {
				goToTable.put("" + i, new HashMap<String, String>());
			}

			while ( (line = fileIn.readLine()) != null ) {
				tokens = line.split(",");

				state = tokens[0];

				nonTerminal = tokens[1].toUpperCase();

				newState = tokens[2];

				goToTable.get(state).put(nonTerminal, newState);
			}
			fileIn.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void display(String token, Stack<String> stack, Stack<String> symbols, String action) {
		System.out.println("token = " + token);
		System.out.println("stack = " + stack);
		System.out.println("symbols = " + symbols);
		System.out.println("action = " + action + "\n");
	}

	public void analyze() throws Exception {
		boolean found, error;
		String state;
		Action action;
		Token token;
		int k;

		stack.push("0"); symbols.push("$");
		found = false; error = false;
		token = lexer.scan();
		while(!found && !error) {
			state = stack.peek();
			action = actionTable.get(state).get(token.toString());

			if (action == null) {
				error = true;
			} else {
				display(token.toString(), stack, symbols, action.toString());
				if (action.getType() == ActionType.REDUCE) {
					Production p = g.get(action.getOperand());
					System.out.println("Production reduced = " + p);
					for (int i = 0; i < p.getBody().length; i++) {
						stack.pop();
						symbols.pop();
					}
					symbols.push(p.getHead());

					System.out.println("BEFORE GOTO");
					String newState = goToTable.get(stack.peek()).get(symbols.peek());
					display(token.toString(), stack, symbols, newState);

					stack.push(newState);
				} else if (action.getType() == ActionType.SHIFT) {
					stack.push(action.getOperand());
					symbols.push(token.toString());
					token = lexer.scan();
				} else if (action.getType() == ActionType.ACCEPT) {
					found = true;
				}
			}
		}
		if (found) {
			System.out.println("ACCEPTED");
		} else {
			System.out.println("ERROR");
		}
	}
}
