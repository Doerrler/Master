package project4;
//Code Borrowed From CSci 1933 Moodle Webpage: https://ay14.moodle.umn.edu/course/view.php?id=11909
//Kyle Doerrler 5007181 & Marshall Moberg 4729197 

//Priority Queue and Simulation

//A Priority Queue is used for an agenda

public class PQ implements PQInterface {


	public PQ() {
		seg = new Segment(0);
	}

	public void add(Event o, double time) {

		time += getCurrentTime();

		if (time < seg.getTime())
			System.out.println("Error: trying to go back in time");
		else if (time == seg.getTime())
			seg.getEvents().add(o);
		else {  // search list for correct insertion point, then insert
			Segment trailer = seg, ptr = seg.getNext();
			while (ptr != null && time > ptr.getTime()) { //search
				ptr = ptr.getNext();
				trailer = trailer.getNext();
			}  // search
			if (ptr != null && time == ptr.getTime())
				ptr.getEvents().add(o);
			else {  // add new segment after trailer and before ptr
				Segment temp = new Segment(time);
				temp.getEvents().add(o);
				temp.setNext(ptr);
				trailer.setNext(temp);
			}  // add new segment after trailer and before ptr
		}  // search list for correct insertion point, then insert
	}

	public Event remove() {
		if (this.isEmpty()) {
			System.out.println("Error: removing from empty queue");
			return null;
		}
		else if (seg.getEvents().length() == 0) {
			seg = seg.getNext();        
			return (Event) seg.getEvents().remove();
		}
		else return (Event) seg.getEvents().remove();
	}

	public boolean isEmpty() {
		return (seg.getEvents().length() == 0 && seg.getNext() == null);
	}

	public double getCurrentTime() { 
		return seg.getTime();
	}

	private Segment seg;  // front of list representing priority queue

}
