package nodes;

import errors.*;

public class If implements VoidNode {
	private BooleanNode eBool;
	private VoidNode st1, st2;
	
	public If(BooleanNode eBool, VoidNode st1, VoidNode st2) {
		this.eBool = eBool;
		this.st1 = st1;
		this.st2 = st2;
	}
	
	public void eval(SymbolTable table) throws SemanticError {
		if (eBool.eval(table)) {
			st1.eval(table);
		} else {
			if (st2 != null) st2.eval(table);
		}
	}
}
