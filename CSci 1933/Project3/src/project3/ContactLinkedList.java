package project3;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ContactLinkedList implements ContactList{

	MyNodeList contactList;
	
	public ContactLinkedList() {
		contactList = new MyNodeList();
	}
	
	public int ptr = -1;
	
	public int getPtr() {
		return ptr;
	}
	public void setPtr(int newPtr) {
		if (newPtr < -1){
			newPtr = ptr;
		}
		else{
			ptr = newPtr;	
		}
	}
	public Contact[] getContactList() {
		Contact[] tempList = new Contact[contactList.getLength()];
		for(int i=0; i<contactList.getLength(); i++){
			tempList[i] = (Contact) contactList.get(i);
		}
		return tempList;
	}
	
	public boolean add(Contact newContact) {
		if(newContact != null){
			ptr ++;
			contactList.add(newContact);
			return true;
		}
		else{
			return false;
		}
	}
	
	public boolean isFull() {
		boolean full = true;
		if(contactList.isEmpty() == true){
			return false;
		}
		for (int i = 0; i < contactList.getLength(); i++)
			if (contactList.get(i) == null)
				return false;
			else{
				full = true;
			}
		return full;
	}
	
	public boolean addInOrder(Contact nContact) {
		if (this.isFull())
			return false;
		boolean added = false;
		for (int i = 0; i < contactList.getLength() && !added; i++) {
			if (contactList.get(i) != null && ((Contact) contactList.get(i)).getName().compareTo(nContact.getName()) >= 0) {
				contactList.insert(i, nContact);
				added = true;
			}
		}
		
		return added;
	}
	
	public Contact find(String subString) {
		if (subString == null){
			return null;
		}
		else if (((Contact) contactList.get(ptr)).getName().contains(subString))
			return (Contact) contactList.get(ptr);
		else {
			boolean found = false;
			for (int i = ptr + 1; i != ptr && !found; i++) {
				if (contactList.get(i) != null && ((Contact) contactList.get(i)).getName().contains(subString)) {
					ptr = i;
					return (Contact) contactList.get(i);
				}
				if (i == (contactList.getLength() - 1))
					i = 0;
			}
		}
		return null;
	}
	
	public Contact remove() {
		if(ptr < contactList.getLength() && contactList.get(ptr) != null){
			return (Contact) contactList.remove(ptr);
		}
		return null;
	}
	
	@SuppressWarnings("resource")
	public boolean read(String iFileName) {
		ObjectInputStream i;

		try {
			i = new ObjectInputStream(new FileInputStream(iFileName));
			
			while (true) { // will break upon end of contact input
				Contact read = (Contact) i.readObject(); // where read1 is a Contact
				this.add(read);
			}
		}
		
		catch (EOFException e) {
		System.out.println("End of File read");
		return true;
		}
		catch (IOException e) { 
		System.out.println("IO problem to fix");
		return false;
		}

		catch (ClassNotFoundException e) { 
		System.out.println("Class does not exist");
		return false;
		}
	}
	
	@SuppressWarnings("resource")
	public boolean write(String oFileName) {
		
		ObjectOutputStream o;

		try {
			o = new ObjectOutputStream(new FileOutputStream(oFileName));
			for (int i = 0; i < contactList.getLength() && contactList.get(i) != null; i++) {
				o.writeObject(contactList.get(i));
			}
			return true;
		}
		catch (IOException e) {
			System.out.println("File write problem to fix");
			return false;
		} 
	}
	
	public Contact getCurrent() {
		return (Contact) contactList.get(ptr);
	}
	
	public Contact get(int i) {
		if (contactList.getLength() > 0 && i < contactList.getLength()) {
			Contact c = (Contact) contactList.get(i);
			return c;
		}
		else return null;
	}
	public Contact next() {
		if (contactList.getLength() > 0 && contactList.isEmpty() == false) {
			ptr++;
			if (ptr == contactList.getLength())
				ptr = 0;
				return (Contact) contactList.get(ptr);
		}
		else return null;
	}
	public Contact previous() {
		if (contactList.getLength() > 0 && contactList.isEmpty() == false) {
			ptr--;
			if (ptr < 0){
				ptr = (contactList.getLength() - 1);
				return (Contact) contactList.get(ptr);
			}
			else if (ptr < contactList.getLength()){
				return (Contact) contactList.get(ptr);
			}
		}
		return null;
	}
	
	public void sort() {
		 int i, j;
		 Contact temp;
		 boolean swapped = true;
		 for (i = 0; i < contactList.getLength() && swapped == true; i++) {
			 swapped = false;
			 for (j = 1; j < contactList.getLength() - i; j++) {
				 if ((((Contact) contactList.get(j)).getName()).compareTo(((Contact)contactList.get(j-1)).getName()) < 0){
					  swapped = true;
		              temp = (Contact) contactList.get(j);
		              contactList.set(j, contactList.get(j-1));
		              contactList.set(j-1, temp);
		            }
		        }
		    }
	}
	public static void main(String[] args){
		
		ContactArrayList a = new ContactArrayList();
		
		Contact c1 = new Contact();
		Contact c2 = new Contact();
		NewContact c3 = new NewContact();
		NewContact c4 = new NewContact();
		
		c1.setName("Roger");
		c1.setPhone(6121111111L);
		c1.setAddress("485 New Ln");
		c1.setComments("Roger Dodger.");
		
		c2.setName("Ellen");
		c2.setPhone(123456789L);
		c2.setAddress("456 Sad St");
		c2.setComments("Ellen is a good person.");
		
		c3.setName("Bob");
		c3.setPhone(6514888888L);
		c3.setAddress("123 Happy St");
		c3.setComments("Bob smells. Don't call Bob.");
		c3.setEmail("theRealBob@hotmail.com");
		c3.setGroup("Friends");
		c3.setQuickRef("B");
		
		c4.setName("Steve");
		c4.setPhone(8144411082L);
		c4.setAddress("222 Holly St");
		c4.setComments("Steve is a kind soul.");
		c4.setEmail("steve@gmail.com");
		c4.setGroup("Frenemies");
		c4.setQuickRef("S");
		
		System.out.println("----Test Part 1----");
		a.add(c3);
		a.add(c1);
		a.add(c4);
		a.addInOrder(c2);
		for(int i=0; i < a.getContactList().length; i++){
			System.out.print(a.getContactList()[i]);
			System.out.println();
		} //Tests add and addInOrder methods
		
		System.out.println("----Test Part 2----");
		System.out.println(a.isFull());
		System.out.println(a.find("Ste"));
		a.setPtr(2);
		a.remove();
		System.out.println(a.get(1));
		System.out.println(a.isFull());
		System.out.println();
		for(int j=0; j < a.getContactList().length; j++){
			System.out.println(a.getContactList()[j]);
		System.out.println();
		} //Tests isFull, find, setPtr, get, and remove methods
			
		System.out.println("----Test Part 3----");
		a.add(c1);
		a.sort();
		for(int j=0; j < a.getContactList().length; j++){
			System.out.print(a.getContactList()[j]);
			System.out.println();
		} //Tests sort
		
		System.out.println("----Test Part 4----");
		a.setPtr(1);
		System.out.println(a.getPtr());
		System.out.println(a.getCurrent());
		System.out.println(a.next());
		System.out.println(a.previous());
		//Tests ptr positioning/moving and retrieval 
		
		System.out.println("----Testing Bad Cases-----");
		System.out.println(a.add(null));
		System.out.println(a.addInOrder(null));
		System.out.println(a.find(null));
		a.setPtr(-4);
		System.out.println(a.getPtr());
		System.out.println();
		//Tests the bad cases for the applicable methods
		
		System.out.println("----Testing Read/Write Functionality----");
		System.out.println("WRITING: " + a.write("data.txt"));
		ContactArrayList b = new ContactArrayList();
		System.out.println("READING: " + b.read("data.txt"));
		System.out.println();
		for(int j=0; j < a.getContactList().length; j++){
			System.out.print(b.getContactList()[j]);
			System.out.println();
			}
		//Tests the write and read methods
	}
	
}
