package errors;

public class SintaxisError extends Exception {
	public SintaxisError() {
		super();
	}
	
	public SintaxisError(String s) {
		super(s);
	}
}
