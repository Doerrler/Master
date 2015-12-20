package project3;

public interface MyList {
	
    //adds the given object to the end of the list
    public boolean add(Object o);


    //inserts the given object at the given index in the list
    public boolean insert(int index, Object o);
   

    //clears all data from the list
    public void clear();
   

    //returns true if the list contains the given object
    public boolean contains(Object o);
   

    //returns the object at the given index
    public Object get(int index);
   

    //returns the index of the given object
    public int indexOf(Object o);
   

    //returns true if the list is empty, false if otherwise
    public boolean isEmpty();
   

    //removes and returns the object at the given index
    public Object remove(int index);
   

    //removes the first instance of the object in the list.
    //returns true if an object is successfully removed, false if otherwise
    public boolean remove(Object o);
   

    //replaces the object at the given index with the given object
    public void set(int index, Object o);
   

    //returns the number of elements in the list
    public int size();
    
}
