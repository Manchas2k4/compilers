package nodes;

import errors.*;

public class Declaration implements VoidNode {
	private String var;
	
	public Declaration(String var) {
		this.var = var;
	}
	
	public void eval(SymbolTable table) throws SemanticError {
		table.defineVariable(var);
	}
}
