package nodes;

import errors.*;

public interface FactorNode extends Node {
	public abstract double eval(SymbolTable t) throws SemanticError;
}
