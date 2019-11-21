package nodes;

import errors.*;

public class NumberNode implements FactorNode {
	private double val;
	
	public NumberNode(double val) {
		this.val = val;
	}
	
	public double eval(SymbolTable table) throws SemanticError {
		return val;
	}
}
