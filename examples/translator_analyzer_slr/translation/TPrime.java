package translation;

public abstract class TPrime extends Symbol {
	protected String inherited, synthesized;
	
	public TPrime() {
		this.type = "TPRIME";
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
		if (!(obj instanceof TPrime)) {
			return false;
		}
		TPrime other = (TPrime) obj;
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
