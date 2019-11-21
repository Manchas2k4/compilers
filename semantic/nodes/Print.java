package nodes;

import errors.*;

public class Print implements VoidNode {
	private String msg;
	private FactorNode val;
	
	public Print(String msg, FactorNode val) {
		this.msg = msg;
		this.val = val;
	}
	
	public void eval(SymbolTable table) throws SemanticError {
		double result = val.eval(table);
		System.out.println(msg + result);
	}
}
