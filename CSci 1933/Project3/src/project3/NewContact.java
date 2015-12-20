package project3;

@SuppressWarnings("serial")
public class NewContact extends Contact {
	
	private String email;
	private String group;
	private String quickRef;
	
	public void setEmail(String email) {
		this.email = email;
	}
	public void setGroup(String group) {
		this.group = group;
	}
	public void setQuickRef(String quickRef) {
		this.quickRef = quickRef;
	}
	
	public String getEmail() {
		return email;
	}
	public String getGroup() {
		return group;
	}
	public String getQuickRef() {
		return quickRef;
	}
	
	// NewContact does not override Contact's .equals(contact) method.
	
	public String toString() {
		return super.toString() + email + "\n" + group + "\n" + quickRef + "\n";
	}
	
}
