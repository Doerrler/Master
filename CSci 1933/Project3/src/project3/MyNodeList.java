package project3;

public class MyNodeList implements MyList {

	//ADD CONSTRUCTOR HEADER NODE
	private Node head;
	private Node link;
	private int counter;
	
	MyNodeList() {
		head = new Node();
		counter = 0;
	}
	
	MyNodeList(Node h){
		head = h;
		counter = 0;
	}
	
	
	//adds the given object to the end of the list
	public boolean add(Object o) {
		if(o == null){
			return false;
		}
		else{
			Node tempNode = new Node(o, link);
			Node current = head;
			while(current.getNext() != null){
				current = current.getNext();
			}
			current.setNext(tempNode);
			counter ++;
			return true;
		}
	}

	//inserts the given object at the given index in the list
	public boolean insert(int index, Object o) {
		if(index < 0 || o == null) {
			return false;
		}
		else {
			Node tempNode = new Node(o, link);
			Node current = head;
			for(int i = 1; i < index+1 && current.getNext() != null; i++){
				current = current.getNext();
			}
			tempNode.setNext(current.getNext());
			current.setNext(tempNode);
			counter ++;
			return true;
		}
	}

	//clears all data from the list
	public void clear() {
		head = new Node();
		counter = 0;
	}

	//returns true if the list contains the given object
	public boolean contains(Object o) {
		if(o == null){
			return false;
		}
		else {
			Node current = head;
			while(current.getNext() != null){
				current = current.getNext();
				if(current.getData() == o) {
					return true;
				}
			}
			return false;
		}
	}

	//returns the object at the given index
	public Object get(int index) {
		if(index < 0) {
			return null;
		}
		else {
			int indexcount = 0;
			Node current = head;
			while(indexcount < index+1){
				current = current.getNext();
				indexcount ++;
			}
			return current.getData();
		}
	}

	//returns the index of the given object
	public int indexOf(Object o) {
		 if(o == null) {
			 return -1;
		 }
		 else {
			 Node current = head;
			 int indexcount = 0;
			 while(current.getData() != o && current != null){
				current = current.getNext();
				indexcount ++;
			 }
			 if(current.getData() == o){
				 return indexcount -1; 
			 }
			 else {
				 return -1;
			 }
		 }
	}

	//returns true if the list is empty, false if otherwise
	public boolean isEmpty() {
		if (head.getNext() == null){
			return true;
		}
		else {
			return false;
		}
	}

	//removes and returns the object at the given index
	public Object remove(int index) {
		if(index < 0) {
			return null;
		}
		else {
			int indexcount = 0;
			Node current = head;
			while(indexcount < index && current != null){
				current = current.getNext();
				indexcount ++;
			}
			if (current == null) {
				return null;
			}
			else{ 
				Node tempCurrent = current.getNext();
				counter --;
				current.setNext(current.getNext().getNext());
			return tempCurrent.getData();
			}
		}
	}

	//removes the first instance of the object in the list.
    //returns true if an object is successfully removed, false if otherwise
	public boolean remove(Object o) {
		if(o == null){
			return false;
		}
		else{
			 Node current = head;
			 while(current.getNext().getData() != o && current != null){
				current = current.getNext();
			 }
			 if(current.getNext().getData() == o){
				 current.setNext(current.getNext().getNext());
				 counter --;
				 return true;
			}
			else{
				return false;
			}
		}
	
	}

	//replaces the object at the given index with the given object
	public void set(int index, Object o) {
		int indexcount = -1;
		Node current = head;
		while(indexcount < index-1){
			current = current.getNext();
			indexcount ++;
		}
		Node newReplacement = new Node(o, current.getNext().getNext());
		current.setNext(newReplacement);
	}

	//returns the number of elements in the list
	public int size() {
		return counter;
	}
	
	 //returns the length of the linked list
	 public int getLength() {
		 int indexcount = 0;
		 Node current = head;
		 while(current != null){
				current = current.getNext();
				indexcount ++;
		 }
		 return indexcount;
	 }
	
	 //print the array, I added this for testing
	 public String toString(){
		Node current = head;
		current = current.getNext();
		while(current != null)
		{
			System.out.println(current.getData());
			current = current.getNext();
		}
		System.out.println();
		return "NotEmpty";
	 }

	 
	public static void main(String[] args){
		//Used to test all the methods
		
		MyNodeList TestList = new MyNodeList();
		TestList.add("0hippo");
		TestList.add("1tiger");
		TestList.add("3panther");
		TestList.insert(2, "2zebra");
		TestList.add("5frog");
		TestList.insert(4, "4mouse");
		TestList.add("6rabbit");
		TestList.toString(); //Tests adding and inserting, sets up the list for further tests below
		
		System.out.println(TestList.contains("3panther"));
		System.out.println(TestList.get(4));
		System.out.println(TestList.indexOf("5frog"));
		System.out.println(TestList.isEmpty());
		System.out.println(); //Tests a batch of methods
		
		TestList.add("7unicorn");
		System.out.println(TestList.remove(6));		
		System.out.println(TestList.remove("1tiger"));
		System.out.println();
		TestList.toString(); //Tests the remove methods
		
		TestList.clear();
		System.out.println(TestList.isEmpty());
		TestList.toString(); //Tests the clear method and verifies isEmpty() works
		
		TestList.add("0hippo");
		TestList.add("1tiger");
		TestList.add("2zebra");
		TestList.set(1, "1leopard");
		System.out.println(TestList.size());
		TestList.toString(); //Tests the set and size methods
		
		System.out.println("Testing Bad Cases:");
		System.out.println(TestList.add(null));
		System.out.println(TestList.insert(-2, "2zebra"));
		System.out.println(TestList.contains(null));
		System.out.println(TestList.get(-1));
		System.out.println(TestList.indexOf(null));
		System.out.println(TestList.remove(-5));		
		System.out.println(TestList.remove(null));
		TestList.set(-20, "1leopard"); //Tests the bad cases for the applicable methods
	}
	
}
