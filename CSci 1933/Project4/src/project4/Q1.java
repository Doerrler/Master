package project4;
//Code Borrowed From CSci 1933 Moodle Webpage: https://ay14.moodle.umn.edu/course/view.php?id=11909
//Kyle Doerrler 5007181 & Marshall Moberg 4729197 

//Queue implementation using a linked list of nodes (see N.java)
//Posted previously, but used for simulation

public class Q1 implements Q {
	private int size;
	private N front;
	private N rear;
	public Q1() {}

	public void add(Object o) {

		if (size == 0) {
			front = new N(o, null);
			rear = front;
		}
		else {
			rear.setNext(new N(o, null));
			rear = rear.getNext();
		}
		size++;
	}

	public Object remove() {

		Object answer;

		if (size == 0)
			return null;

		answer = front.getData();
		front = front.getNext();
		size--;
		if (size == 0)
			rear = null;
		return answer;
	}

	public int length() {
		return size;
	}
	
	public boolean isBusy(){
		if (front == rear){
			return false;
		}
		else return true;
	}

}


