package grammar;

public class Action {
	private ActionType type;
	private String operand;
	
	public Action(ActionType type, String operand) {
		this.type = type;
		this.operand = operand;
	}

	public ActionType getType() {
		return type;
	}

	public String getOperand() {
		return operand;
	}

	@Override
	public String toString() {
		return "Action [type=" + type + ", operand=" + operand + "]";
	}
}
