package nodes;

import errors.*;

public class While implements VoidNode {
	private BooleanNode eBool;
	private VoidNode statement;
	
	public While(BooleanNode eBool, VoidNode statement) {
		this.eBool = eBool;
		this.statement = statement;
	}
	
	public void eval(SymbolTable table) throws SemanticError {
		while(eBool.eval(table)) {
			statement.eval(table);
		}
	}
}
