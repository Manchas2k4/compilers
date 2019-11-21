package nodes;

import errors.*;

public interface VoidNode extends Node {
	public abstract void eval(SymbolTable t) throws SemanticError;
}
