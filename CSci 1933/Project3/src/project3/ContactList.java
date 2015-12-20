package project3;

public interface ContactList {
	
	//returns the ptr
	public int getPtr();
	
	//sets ptr to a user entered integer
	public void setPtr(int newPtr);
	
	//returns the contactList
	public Contact[] getContactList();
	
	//adds a contact to the array
	public boolean add(Contact newContact);
	
	//returns true if the list is full and false otherwise
	public boolean isFull();
	
	//adds the contact to the list in alphabetical order
	public boolean addInOrder(Contact nContact);
	
	//checks for a substring and returns the contact if its found
	public Contact find(String subString);
	
	//removes the contact at the given index, returns it, and then rearranges the array
	public Contact remove();
	
	//reads a file of contacts
	boolean read(String iFileName);

	//writes an array of contacts to a file
	boolean write(String oFileName);
	
	//returns the contact found at the current ptr
	public Contact getCurrent();
	
	//returns the contact at the given index
	public Contact get(int i);
	
	//moves the ptr to the next contact and returns it
	//if at the end of the list it returns to the first element
	//returns null if the list is empty
	public Contact next();
	
	//moves the ptr to the previous contact and returns it
	//if at the beginning of the list it moves to the last element
	//returns null if the list is empty
	public Contact previous();
	
	//alphabetically sorts the list according to the name attribute
	public void sort();
	
}
