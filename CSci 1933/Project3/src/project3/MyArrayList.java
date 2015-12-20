package project3;

public class MyArrayList implements MyList{
	
	Object[] arrayList;
	int ptr = -1;
	
public MyArrayList() {
	arrayList = new Object[2];
}

	//adds the given object to the end of the list
	public boolean add(Object o) {
		boolean objectadded = false;
		while (objectadded == false){
			if (o == null) {
				return objectadded;
			}
			else {
				if (ptr + 1 < arrayList.length) {
					ptr++;
					if (arrayList[ptr] == null) {
						arrayList[ptr] = o;
						objectadded = true;
						return objectadded;
					}
					else {
						ptr ++;
					}
				}
				else {
					Object[] tempArrayList = arrayList;
					arrayList = new Object[2*tempArrayList.length];
					int iterator = 0;
					while (iterator < tempArrayList.length) {
						arrayList[iterator] = tempArrayList[iterator];
						iterator ++;
					}
					arrayList[iterator] = o;
					ptr ++;
					objectadded = true;
					return objectadded;
				}
			}
		}
		return objectadded;
	}
	
	//inserts the given object at the given index in the list
	public boolean insert(int index, Object o) {
		if(o == null){
			return false;
		}
		else {
			if(index < arrayList.length -1 && index >= 0){
				if(arrayList[index] == null) {
					arrayList[index] = o;
					return true;
				}
				else {
					Object[] tempArrayList = arrayList;
					arrayList = new Object[tempArrayList.length];
					int iterator = 0;
					while (iterator < index) {
						arrayList[iterator] = tempArrayList[iterator];
						iterator ++;
					}
					arrayList[iterator] = o;
					iterator ++;
					while (iterator <= tempArrayList.length && iterator < arrayList.length && tempArrayList[iterator-1] != null){
						arrayList[iterator] = tempArrayList[iterator-1];
						iterator ++;
					}
					ptr ++;
					return true;
				}
			}
			else if(index == arrayList.length){
				Object[] tempArrayList = arrayList;
				arrayList = new Object[2*tempArrayList.length];
				int iterator = 0;
				while (iterator < tempArrayList.length) {
					arrayList[iterator] = tempArrayList[iterator];
					iterator ++;
				}
				arrayList[index] = o;
				return true;
			}
			else {
				return false;
			}
		}
	}
	
	 //clears all data from the list
	 public void clear() {
		arrayList = new Object[2];
		ptr = -1;
		
	 }
	
	 //returns true if the list contains the given object
	 public boolean contains(Object o){
		 if(o == null) {
			 return false;
		 }
		 else {
			 for(int i=0; i<arrayList.length; i++){
				 if(arrayList[i].equals(o)){
					 return true;
				 }
			 }
			 return false;	 
		 }
	 }
	 
	 //returns the object at the given index
	 public Object get(int index){ 
		 if (index < 0 || index >= arrayList.length){
			 return null;
		 }
		 else {
			 return arrayList[index];
		 }
	 }
	 
	 //returns the index of the given object
	 public int indexOf(Object o){
		 if(o == null) {
			 return -1;
		 }
		 else {
			 for(int i=0; i<arrayList.length; i++){
				 if(arrayList[i].equals(o)){
					 return i;
				 }
			 }
			 return -1;
		 }
	 }
	
	 //returns true if the list is empty, false if otherwise
	 public boolean isEmpty(){
		 boolean emptycheck = true;
		 int iterator = 0;
		 while (iterator < arrayList.length && emptycheck == true){
			 if(arrayList[iterator] == (null)){
				 emptycheck = true;
				 return emptycheck;
			 }
			 else{
				 emptycheck = false;
				 return emptycheck;
			 }
		 }
		return emptycheck;
	 }
	 
	 //removes and returns the object at the given index
	 public Object remove(int index){
		 if(index < 0 || index >= arrayList.length){
			 return null;
		 }
		 else {
		 Object tempObject = arrayList[index];
		 int iterator = index;
		 while(iterator < arrayList.length -1){
				arrayList[iterator] = arrayList[iterator+1];
				iterator ++;
			}
		 arrayList[iterator] = null;
		 ptr --;
		 return tempObject;
		 }
	 }
	 
	 //removes the first instance of the object in the list.
	 //returns true if an object is successfully removed, false if otherwise
	 public boolean remove(Object o){
		 if(o == null) {
			 return false;
		 }
		 else {
			 for(int i=0; i<arrayList.length; i++){
				 if(arrayList[i].equals(o)){
					 int iterator = i;
					 while(iterator < arrayList.length -1){
							arrayList[iterator] = arrayList[iterator+1];
							iterator ++;
					 }
					 ptr--;
					 return true;
				 }
			 }
			 return false;
		 }
	    }
	 
	 //replaces the object at the given index with the given object
	 public void set(int index, Object o){
		 if (index >= 0 && index < arrayList.length){
			 arrayList[index] = o;
		 }
	 }
	 
	 //returns the number of elements in the list
	 public int size(){
		 int counter = 0;
		 for(int i=0; i < arrayList.length; i++){
			 if(arrayList[i] != (null)){
				 counter ++;
			 }
		 }
		 return counter;
	 }
	 
	 //returns the index of arrayList, used for the ContactArrayList
	 public int getLength(){
		 return arrayList.length;
	 }
	 
	 //print the array, I added this for testing
	 public String toString(){
		 for(int i=0; i<arrayList.length; i++){
			 System.out.println(arrayList[i]);
		 }
		 System.out.println();
		 return "done";
	 }
	 
	 
	 public static void main(String[] args){
		//Used to test all the methods
		MyArrayList TestList = new MyArrayList();
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
