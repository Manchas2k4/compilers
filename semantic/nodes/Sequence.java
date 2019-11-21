package nodes;

import errors.*;

public class Sequence implements VoidNode {
	private VoidNode s, ls;
	
	public Sequence(VoidNode s, VoidNode ls) {
		this.s = s;
		this.ls = ls;
	}
	
	public void eval(SymbolTable table) throws SemanticError {
		s.eval(table);
		if (ls != null) ls.eval(table);
	}
}
