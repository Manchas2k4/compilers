package translation;

public abstract class EPrime extends Symbol {
	protected String inherited, synthesized;
	
	public EPrime() {
		this.type = "EPRIME";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
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
		if (!(obj instanceof EPrime)) {
			return false;
		}
		EPrime other = (EPrime) obj;
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
