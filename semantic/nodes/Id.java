package nodes;

import errors.*;

public class Id implements FactorNode {
	private String val;
	
	public Id(String val) {
		this.val = val;
	}
	
	public double eval(SymbolTable table) throws SemanticError {
		return table.getValue(val);
	}
}
