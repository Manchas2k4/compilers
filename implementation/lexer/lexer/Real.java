package lexer;

public class Real extends Token {
	private float value;
	
	public Real(float value) {
		super(Tag.REAL);
		this.value = value;
	}
	
	public float getValue() {
		return value;
	}
	
	public String toString() {
		return "REAL - VALUE = " + value;
	}
}
