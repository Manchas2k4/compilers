package nodes;

import java.util.Hashtable;
import errors.SemanticError;

public class SymbolTable {
	private Hashtable<String, Object> table;
	
	public SymbolTable() {
		table = new Hashtable<String, Object>();
	}
	
	public void defineVariable(String var) throws SemanticError {
		if (table.containsKey(var)) {
			throw new SemanticError("variable already defined");
		} else {
			table.put(var, new Double(0.0));
		}
	}
	
	public void assignValue(String var, double val) throws SemanticError {
		if (!table.containsKey(var)) {
			throw new SemanticError("variable not defined");
		} else {
			table.put(var, new Double(val));
		}
	}
	
	public double getValue(String var) throws SemanticError {
		if (!table.containsKey(var)) {
			throw new SemanticError("-variable not defined");
		} else {
			Object val = table.get(var);
			if (val instanceof Double) {
				return ((Double) val).doubleValue();
			} else {
				throw new SemanticError("value is not numeric");
			}
		}
	}
}
	
	
	
	
	
