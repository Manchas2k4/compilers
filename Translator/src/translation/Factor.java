package translation;

public class Factor extends Symbol {
	protected Symbol body;
	protected String synthesized;
	
	public Factor(Symbol symbol) {
		this.type = "F";
		this.body = symbol;
		this.synthesized = null;
	}
	
	public String eval() {
		if (synthesized == null) {
			synthesized = body.eval();
		}
		return synthesized;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((body == null) ? 0 : body.hashCode());
		result = prime * result + ((synthesized == null) ? 0 : synthesized.hashCode());
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
		if (!(obj instanceof Factor)) {
			return false;
		}
		Factor other = (Factor) obj;
		if (body == null) {
			if (other.body != null) {
				return false;
			}
		} else if (!body.equals(other.body)) {
			return false;
		}
		if (synthesized == null) {
			if (other.synthesized != null) {
				return false;
			}
		} else if (!synthesized.equals(other.synthesized)) {
			return false;
		}
		return true;
	}
}
