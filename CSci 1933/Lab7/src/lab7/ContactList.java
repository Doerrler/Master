package lab7;

import java.util.Scanner;
import java.io.*; // provide easy access to all IO Exceptions

@SuppressWarnings("unused")
public class ContactList {
	
	Contact[] contactList;
	
	public ContactList() {
		contactList = new Contact[20];
	}
	public ContactList(int l) {
		contactList = new Contact[l];
	}
	
	public int ptr = -1;
	
	public int getPtr() {
		return ptr;
	}
	public void setPtr(int newPtr) {
		ptr = newPtr;
	}
	public Contact[] getContactList() {
		return contactList;
	}
	
	public boolean add(Contact newContact) {
		if (ptr + 1 < contactList.length) {
			ptr++;
			contactList[ptr] = newContact;
			return true;
		}
		else return false;
	}
	
	public boolean isFull() {
		boolean full = false;
		for (int i = 0; i < contactList.length; i++)
			if (contactList[i] == null)
				full = false;
		return full;
	}
	
	public boolean addInOrder(Contact nContact) {
		
		if (this.isFull())
			return false;
		
		boolean added = false;
		for (int i = 0; i < contactList.length && !added; i++) {
			if (contactList[i] != null && contactList[i].getName().compareTo(nContact.getName()) >= 0) {
				for (int j = (contactList.length-1); j > i; j--) {
					contactList[j] = contactList[j-1];
				}
				contactList[i] = nContact;
				added = true;
			}
		}
		
		return added;
	}
	
	public Contact find(String subString) {
		if (contactList[ptr].getName().contains(subString))
			return contactList[ptr];
		else {
			boolean found = false;
			for (int i = ptr + 1; i != ptr && !found; i++) {
				if (contactList[i] != null && contactList[i].getName().contains(subString)) {
					ptr = i;
					return contactList[i];
				}
				if (i == (contactList.length - 1))
					i = 0;
			}
		}
		return null;
	}
	
	public Contact remove() {
		Contact temp = contactList[ptr];
		for (int i = ptr; contactList[i] != null && i < contactList.length; i++)
			contactList[i] = contactList[i+1];
		return temp;
	}
	
	@SuppressWarnings("resource")
	boolean read(String iFileName) {
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
	boolean write(String oFileName) {
		
		ObjectOutputStream o;

		try {
			o = new ObjectOutputStream(new FileOutputStream(oFileName));
			for (int i = 0; i < contactList.length && contactList[i] != null; i++) {
				o.writeObject(contactList[i]);
			}
			return true;
		}
		catch (IOException e) {
			System.out.println("File write problem to fix");
			return false;
		} 
	}
	
	public Contact getCurrent() {
		return contactList[ptr];
	}
	public Contact get(int i) {
		if (contactList.length > 0 && i < contactList.length) {
			return contactList[i];
		}
		else return null;
	}
	public Contact next() {
		if (contactList.length > 0) {
			ptr++;
			if (ptr == contactList.length)
				ptr = 0;
			return contactList[ptr];
		}
		else return null;
	}
	public Contact previous() {
		ptr--;
		if (ptr < 0)
			ptr = (contactList.length - 1);
		return contactList[ptr];
	}
	
	public void sort() {
		int i, j;
		Contact temp;
		boolean swapped = true;
		for (i = 0; i < contactList.length & swapped; i++) {
			swapped = false;
			for (j = 1; j < (contactList.length - i); j++) {
				if ((contactList[j].getName()).compareTo(contactList[j-1].getName()) < 0) {
					temp = contactList[j];
					contactList[j] = contactList[j-1];
					contactList[j-1] = temp;
					swapped = true;
				}
			}
		}
	}
	
	public static void main(String[] args) {

		ContactList a = new ContactList();
		
		Contact c1 = new Contact();
		Contact c2 = new Contact();
		Contact c3 = new Contact();
		
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
		
		a.add(c1);
		a.add(c2);
		a.add(c3);
/*	
		System.out.println("WRITING: " + a.write("data.txt"));
		
		ContactList b = new ContactList(3);
		System.out.println("READING: " + b.read("data.txt"));
		b.setPtr(0);
		
		System.out.println();
		*/
/*		
		// Steps 3 & 4 //
		System.out.println(b.getCurrent().toString());
		System.out.println(b.next().toString());
		System.out.println(b.next().toString());
		System.out.println(b.next().toString());
		System.out.println(b.previous().toString());
		System.out.println(b.get(0).toString());
		
		// Step 5 //
		b.sort();
		System.out.println(b.getCurrent().toString());
		System.out.println(b.next().toString());
		System.out.println(b.next().toString());
		
		
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
		
		c4.setName("Bob");
		c4.setPhone(6514888888L);
		c4.setAddress("123 Happy St");
		c4.setComments("Bob smells. Don't call Bob.");
		c4.setEmail("theRealBob@hotmail.com");
		c4.setGroup("Friends");
		c4.setQuickRef("B");
		
		ContactList a4 = new ContactList(10);
		
		a4.add(c3);
		a4.add(c1);
		a4.addInOrder(c2);
		
		System.out.println((a4.getContactList())[0].toString());
		System.out.println((a4.getContactList())[1].toString());
		System.out.println((a4.getContactList())[2].toString());
		
		System.out.println("WRITING: " + a.write("data.txt"));
		
		ContactList b2 = new ContactList();
		System.out.println("READING: " + b2.read("data.txt"));
		
		b2.setPtr(0);
		System.out.println(b2.next().toString());
		System.out.println(b2.next().toString());
		System.out.println(b2.next().toString());
		
		ContactList a1 = new ContactList();
		
		a1.add(c1);
		a1.add(c2);
		a1.add(c3);
		a1.add(c4);
		
		System.out.println("WRITING: " + a.write("data.txt"));
		
		ContactList b1 = new ContactList(4);
		System.out.println("READING: " + b.read("data.txt"));
		b.setPtr(0);
		
		System.out.println(b1.getCurrent().toString());
		System.out.println(b1.next().toString());
		System.out.println(b1.next().toString());
		System.out.println(b1.next().toString());
		System.out.println(b1.previous().toString());
		System.out.println(b1.get(0).toString());
		
		*/
	}

}
