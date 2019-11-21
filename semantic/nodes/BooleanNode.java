package nodes;

import errors.*;

public interface BooleanNode extends Node {
	public abstract boolean eval(SymbolTable t) throws SemanticError;
}
