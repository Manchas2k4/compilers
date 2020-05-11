package grammar;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Stack;

import lexer.*;
import lexer.Number;
import translation.*;

public class LRParser {
	private HashMap<String, HashMap<String, Action>> actionTable;
	private HashMap<String, HashMap<String, String>> goToTable;
	private Grammar g;
	private Stack<String> stack;
	private Stack<Symbol> symbols;
	private Lexer lexer;

	public LRParser(FileInputStream input) {
		g = new Grammar();
		lexer = new Lexer(input);
		stack = new Stack<String>();
		symbols = new Stack<Symbol>();

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
				// 0,E,1
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

	private void display(String token, Stack<String> stack, Stack<Symbol> symbols, String action) {
		System.out.println("token = " + token);
		System.out.println("stack = " + stack);
		System.out.println("symbols = " + symbols);
		System.out.println("action = " + action + "\n");
	}

	private Symbol getInput(Token token) {
		Symbol aux = null;
		if (token.toString().contentEquals("ID")) {
			Word w = (Word) token;
			aux = new Id(w.getLexeme());
		} else if (token.toString().contentEquals("NUMBER")) {
			Number n = (Number) token;
			aux = new Num(n.getValue());
		} else {
			aux = new Operator(token.toString());
		}
		return aux;
	}

	private void doReduce(Action action) {
		Production p = g.get(action.getOperand());
		if (p.getHead().contentEquals("F")) {
			Symbol s = null;
			if (p.getBody().length == 1) {
				s = symbols.pop(); stack.pop();
			} else {
				symbols.pop(); stack.pop();
				s = symbols.pop(); stack.pop();
				symbols.pop(); stack.pop();
			}
			/*
			Factor f = new Factor(s);
			f.eval();
			symbols.push(f);
			*/
			symbols.push(new Factor(s));
		} else if (p.getHead().contentEquals("TPRIME")) {
			TPrime aux = null;
			if (p.getBody().length == 0) {
				aux = new TPrimeZero();
			} else {
				TPrime tPrime = (TPrime) symbols.pop(); stack.pop();
				Factor factor = (Factor) symbols.pop(); stack.pop();
				Operator op = (Operator) symbols.pop(); stack.pop();
				aux = new TPrimeOne(op, factor, tPrime);
			}
			symbols.push(aux);
		} else if (p.getHead().contentEquals("T")) {
			TPrime tPrime = (TPrime) symbols.pop(); stack.pop();
			Factor factor = (Factor) symbols.pop(); stack.pop();
			symbols.push(new Term(factor, tPrime));
		} else if (p.getHead().contentEquals("EPRIME")) {
			EPrime aux = null;
			if (p.getBody().length == 0) {
				aux = new EPrimeZero();
			} else {
				EPrime ePrime = (EPrime) symbols.pop(); stack.pop();
				Term term = (Term) symbols.pop(); stack.pop();
				Operator op = (Operator) symbols.pop(); stack.pop();
				aux = new EPrimeOne(op, term, ePrime);
			}
			symbols.push(aux);
		} else if (p.getHead().contentEquals("E")) {
			EPrime ePrime = (EPrime) symbols.pop(); stack.pop();
			Term term = (Term) symbols.pop(); stack.pop();
			symbols.push(new Expression(term, ePrime));
		}
	}

	public void analyze() throws Exception {
		boolean found, error;
		String state;
		Action action;
		Token token;

		stack.push("0"); symbols.push(new Operator("$"));
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
					doReduce(action);

					System.out.println("BEFORE GOTO");
					String newState = goToTable.get(stack.peek()).get(symbols.peek().getType());
					display(token.toString(), stack, symbols, newState);

					//String newState = goToTable.get(stack.peek()).get(symbols.peek());
					stack.push(newState);
				} else if (action.getType() == ActionType.SHIFT) {
					stack.push(action.getOperand());
					symbols.push(getInput(token));
					token = lexer.scan();
				} else if (action.getType() == ActionType.ACCEPT) {
					found = true;
				}
			}
		}
		if (found) {
			System.out.println("ACCEPTED, TRANSLATE = " + symbols.pop().eval());
		} else {
			System.out.println("ERROR");
		}
	}
}
