package translation;

public class EPrimeZero extends EPrime {
	public EPrimeZero() {
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
