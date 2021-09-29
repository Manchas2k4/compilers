package translation;

public class Term extends Symbol {
	protected String synthesized;
	protected Factor factor;
	protected TPrime tPrime;
	
	public Term(Factor factor, TPrime tPrime) {
		this.type = "T";
		this.synthesized = null;
		this.factor = factor;
		this.tPrime = tPrime;
	}
	
	public String eval() {
		if (synthesized == null) {
			tPrime.inherited = factor.eval();
			synthesized = tPrime.eval();
		}
		return synthesized;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((factor == null) ? 0 : factor.hashCode());
		result = prime * result + ((synthesized == null) ? 0 : synthesized.hashCode());
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
		if (!(obj instanceof Term)) {
			return false;
		}
		Term other = (Term) obj;
		if (factor == null) {
			if (other.factor != null) {
				return false;
			}
		} else if (!factor.equals(other.factor)) {
			return false;
		}
		if (synthesized == null) {
			if (other.synthesized != null) {
				return false;
			}
		} else if (!synthesized.equals(other.synthesized)) {
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
