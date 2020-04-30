package translation;

public class TPrimeOne extends TPrime {
	protected Operator op;
	protected Factor factor;
	protected TPrime tPrime;
	
	public TPrimeOne(Operator op, Factor factor, TPrime tPrime) {
		super();
		this.inherited = null;
		this.synthesized = null;
		this.op = op;
		this.factor = factor;
		this.tPrime = tPrime;
	}

	@Override
	public String eval() {
		if (synthesized == null) {
			tPrime.inherited = inherited + " " + factor.eval() + " " + op.eval();
			synthesized = tPrime.eval();
		}
		return synthesized;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((factor == null) ? 0 : factor.hashCode());
		result = prime * result + ((op == null) ? 0 : op.hashCode());
		result = prime * result + ((tPrime == null) ? 0 : tPrime.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (!(obj instanceof TPrimeOne)) {
			return false;
		}
		TPrimeOne other = (TPrimeOne) obj;
		if (factor == null) {
			if (other.factor != null) {
				return false;
			}
		} else if (!factor.equals(other.factor)) {
			return false;
		}
		if (op == null) {
			if (other.op != null) {
				return false;
			}
		} else if (!op.equals(other.op)) {
			return false;
		}
		if (tPrime == null) {
			if (other.tPrime != null) {
				return false;
			}
		} else if (!tPrime.equals(other.tPrime)) {
			return false;
		}
		return true;
	}
}
