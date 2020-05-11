package translation;

public class TPrimeZero extends TPrime {
	public TPrimeZero() {
		super();
		this.inherited = null;
		this.synthesized = null;
	}

	@Override
	public String eval() {
		if (synthesized == null) {
			synthesized = inherited;
		}
		return synthesized;
	}
}
