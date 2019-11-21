package nodes;

import errors.*;

public class Assignment implements VoidNode {
	private String var;
	private FactorNode expr;
	
	public Assignment(String var, FactorNode expr) {
		this.var = var;
		this.expr = expr;
	}
	
	public void eval(SymbolTable table) throws SemanticError {
		table.assignValue(var, expr.eval(table));
	}
}
