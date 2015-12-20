package lab5;

import java.util.Scanner;
import java.io.PrintWriter;
import java.io.File;


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
	public Contact getCurrent() {
		return contactList[ptr];
	}
	public Contact get(int ind) {
		if (ind >= contactList.length) {
			return null;
		}
		else if (contactList[ind] == null) {
			return null;
		}
		else return contactList[ind];
	}
	
	public Contact next() {
		if (ptr +1 < contactList.length) {
			ptr ++;
			if (contactList[ptr] == null) {
				return null;
			}
			else {
				return contactList[ptr];
			}
		}
		else {
			ptr = 0;
			return contactList[ptr];
		}
	}
	
	public void sort() {
		 int i;
		 int j;
		 Contact temp = new Contact();
		 boolean swapped = true;
		 for (i = 0; i < contactList.length && swapped == true; i++) {
			 swapped = false;
		     for (j = 1; j < contactList.length - i && contactList[j] != null; j++) {
		    	 int test = (int) contactList[j].toString().compareTo(contactList[j-1].toString());
		         if (test < 0) {
		    	 	  swapped = true;
		              temp = contactList[j];
		              contactList[j] = contactList[j-1];
		              contactList[j-1] = temp;
		    	 }
		     }
		 }
	}
	
	public boolean add(Contact newContact) {
		if (ptr + 1 < contactList.length) {
			ptr++;
			contactList[ptr] = newContact;
			return true;
		}
		else return false;
	}
	
	public boolean write(String fileName) {
		try {   // test for Exception in call to File constructor
			PrintWriter p = new PrintWriter(new File(fileName));
			for(int i = 0; i < contactList.length; i++) {
				p.println(contactList[i].toString());
			}
			p.close();
			return true;
			}
			catch (Exception e) {
			return false;
			}
	}
	
	public boolean read(String fileName) {
		try {
			@SuppressWarnings("resource")
			Scanner s = new Scanner(new File(fileName));
			Contact c = new Contact();
			while(s.hasNextLine()){
				String name = s.nextLine();
				Long phone = Long.parseLong(s.nextLine());
				String address = s.nextLine();
				String comments = s.nextLine();
				c.setName(name);
				c.setPhone(phone);
				c.setAddress(address);
				c.setComments(comments);
				add(c);
				System.out.print(c.toString());
				System.out.println();
				
			}
			return true;
			}
		catch (Exception e) {
		return false;
		}
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
	
	public static void main(String[] args) {
		ContactList a = new ContactList(4);
		
		Contact c1 = new Contact();
		Contact c2 = new Contact();
		Contact c3 = new Contact();
		Contact c4 = new Contact();
		
		c1.setName("Bob");
		c1.setPhone(6514888888L);
		c1.setAddress("123 Happy St");
		c1.setComments("Bob smells. Don't call Bob.");
		
		c2.setName("Ellen");
		c2.setPhone(123456789L);
		c2.setAddress("456 Sad St");
		c2.setComments("Ellen is a good person.");
		
		c3.setName("Thomas");
		c3.setPhone(9876543211L);
		c3.setAddress("523 Blue St");
		c3.setComments("Thomas is a nice guy.");
		
		c4.setName("Fred");
		c4.setPhone(9123120512L);
		c4.setAddress("123 Wacky St");
		c4.setComments("Fred is a Flinstone.");
		
		a.add(c1);
		a.add(c2);
		a.add(c3);
		a.add(c4);
		
		a.sort();

		
		
		@SuppressWarnings("resource")
		Scanner s = new Scanner(System.in);
		System.out.print("What file do you wish to write to? ");
		String fileName = s.next();
		
		a.write(fileName);
		
		System.out.print("What file do you wish to read? ");
		String fileName2 = s.next();
		
		a.read(fileName2);
		

		
	}

}
