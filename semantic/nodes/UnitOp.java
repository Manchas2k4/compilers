package nodes;

import errors.*;

public class UnitOp implements FactorNode {
	private FactorNode left;
	private String op;
	
	public UnitOp(String op, FactorNode left) {
		this.op = op;
		this.left = left;
	}
	
	public double eval(SymbolTable table) throws SemanticError {
		double result = left.eval(table);
		if (op.equals("-")) {
			result *= -1;
		}
		return result;
	}
}
