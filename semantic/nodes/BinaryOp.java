package nodes;

import errors.*;

public class BinaryOp implements FactorNode {
	private FactorNode left, right;
	private String op;
	
	public BinaryOp(String op, FactorNode left, FactorNode right) {
		this.op = op;
		this.left = left;
		this.right = right;
	}
	
	public double eval(SymbolTable table) throws SemanticError {
		double aux1 = left.eval(table);
		double aux2 = right.eval(table);
		
		if (op.equals("+")) {
			return (aux1 + aux2);
		} else if (op.equals("-")) {
			return (aux1 - aux2);
		} else if (op.equals("*")) {
			return (aux1 * aux2);
		} else { //if (op.equals("+")) {
			return (aux1 / aux2);
		}
	}
}
	
