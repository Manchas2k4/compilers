package grammar;

import java.util.Arrays;

public class Production {
	private String head;
	private String[] body;
	
	public Production(String head, String[] body) {
		this.head = head;
		this.body = body;
	}

	public String getHead() {
		return head;
	}

	public String[] getBody() {
		return body;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(body);
		result = prime * result + ((head == null) ? 0 : head.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Production)) {
			return false;
		}
		Production other = (Production) obj;
		if (!Arrays.equals(body, other.body)) {
			return false;
		}
		if (head == null) {
			if (other.head != null) {
				return false;
			}
		} else if (!head.equals(other.head)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "Production [head=" + head + ", body=" + Arrays.toString(body) + "]";
	}
}
