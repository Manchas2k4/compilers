package grammar;

import java.util.HashMap;

public class Grammar {
	private HashMap<String, Production> productions;
	
	public Grammar() {
		productions = new HashMap<String, Production>();
		productions.put("1", new Production("E", new String[] {"T", "EPRIME"}));
		productions.put("2", new Production("EPRIME", new String[] {"+", "T", "EPRIME"}));
		productions.put("3", new Production("EPRIME", new String[] {"-", "T", "EPRIME"}));
		productions.put("4", new Production("EPRIME", new String[] {}));
		productions.put("5", new Production("T", new String[] {"F", "TPRIME"}));
		productions.put("6", new Production("TPRIME", new String[] {"*", "F", "TPRIME"}));
		productions.put("7", new Production("TPRIME", new String[] {"/", "F", "TPRIME"}));
		productions.put("8", new Production("TPRIME", new String[] {}));
		productions.put("9", new Production("F", new String[] {"ID"}));
		productions.put("10", new Production("F", new String[] {"NUMBER"}));
		productions.put("11", new Production("F", new String[] {"(", "E", ")"}));
	}
	
	public Production get(String ruleNumber) {
		return productions.get(ruleNumber);
	}
	
	public int size() {
		return productions.size();
	}
}
