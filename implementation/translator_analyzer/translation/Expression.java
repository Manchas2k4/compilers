package translation;

public class Expression extends Symbol {
	protected String synthesized;
	protected Term term;
	protected EPrime ePrime;
	
	public Expression(Term term, EPrime ePrime) {
		this.type = "E";
		this.synthesized = null;
		this.term = term;
		this.ePrime = ePrime;
	}
	
	public String eval() {
		if (synthesized == null) {
			ePrime.inherited = term.eval();
			synthesized = ePrime.eval();
		}
		return synthesized;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((ePrime == null) ? 0 : ePrime.hashCode());
		result = prime * result + ((synthesized == null) ? 0 : synthesized.hashCode());
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
		if (!(obj instanceof Expression)) {
			return false;
		}
		Expression other = (Expression) obj;
		if (ePrime == null) {
			if (other.ePrime != null) {
				return false;
			}
		} else if (!ePrime.equals(other.ePrime)) {
			return false;
		}
		if (synthesized == null) {
			if (other.synthesized != null) {
				return false;
			}
		} else if (!synthesized.equals(other.synthesized)) {
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
