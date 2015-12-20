package lab5;

public class Contact {

	private String name;
	private long phone;
	private String address;
	private String comments;
	
	public void setName(String name) {
		this.name = name;
	}
	public void setPhone(long phone) {
		this.phone = phone;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	public String getName() {
		return name;
	}
	public long getPhone() {
		return phone;
	}
	public String getAddress() {
		return address;
	}
	public String getComments() {
		return comments;
	}
	
	public String toString() {
		return name + "\n" + phone + "\n" + address + "\n" + comments;
	}
	
	public boolean equals(Contact other) {
/*		if ((this.name).equals(other.getName()) && this.phone == other.getPhone() && (this.address).equals(other.getAddress()) && this.comments.equals(other.getComments())) {
			return true;
		}
*/
		if (this.toString().equals(other.toString())) {
			return true;
		}
		else return false;
	}

	public static void main(String[] args) {
		Contact c1 = new Contact();
		Contact c2 = new Contact();
		Contact c3 = new Contact();
		
		c1.setName("Bob");
		c1.setPhone(6514888888L);
		c1.setAddress("123 Happy St");
		c1.setComments("Bob smells. Don't call Bob.");
		
		c2.setName("Ellen");
		c2.setPhone(123456789L);
		c2.setAddress("456 Sad St");
		c2.setComments("Ellen is a good person.");
		
		System.out.println(c1.toString());
		System.out.println(c2.toString());
		
		if (c1.equals(c2))
			System.out.println("Contacts are equal.");
		else System.out.println("Contacts are not equal.");
		
		c3.setName("Bob");
		c3.setPhone(6514888888L);
		c3.setAddress("123 Happy St");
		c3.setComments("Bob smells. Don't call Bob.");
		
		if (c1.equals(c3))
			System.out.println("Contacts are equal.");
		else System.out.println("Contacts are not equal.");
	}
}