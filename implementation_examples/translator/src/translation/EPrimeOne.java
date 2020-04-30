package translation;

public class EPrimeOne extends EPrime {
	protected Operator op;
	protected Term term;
	protected EPrime ePrime;
	
	public EPrimeOne(Operator op, Term term, EPrime ePrime) {
		super();
		this.inherited = null;
		this.synthesized = null;
		this.op = op;
		this.term = term;
		this.ePrime = ePrime;
	}

	@Override
	public String eval() {
		if (synthesized == null) {
			ePrime.inherited = inherited + " " + term.eval() + " " + op.eval();
			synthesized = ePrime.eval();
		}
		return synthesized;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((ePrime == null) ? 0 : ePrime.hashCode());
		result = prime * result + ((op == null) ? 0 : op.hashCode());
		result = prime * result + ((term == null) ? 0 : term.hashCode());
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
		if (!(obj instanceof EPrimeOne)) {
			return false;
		}
		EPrimeOne other = (EPrimeOne) obj;
		if (ePrime == null) {
			if (other.ePrime != null) {
				return false;
			}
		} else if (!ePrime.equals(other.ePrime)) {
			return false;
		}
		if (op == null) {
			if (other.op != null) {
				return false;
			}
		} else if (!op.equals(other.op)) {
			return false;
		}
		if (term == null) {
			if (other.term != null) {
				return false;
			}
		} else if (!term.equals(other.term)) {
			return false;
		}
		return true;
	}
}
