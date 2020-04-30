package translation;

public class Operator extends Symbol {
	public Operator(String type) {
		this.type = type;
	}
	
	public String eval() {
		return type;
	}
}
