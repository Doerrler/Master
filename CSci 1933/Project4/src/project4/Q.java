package project4;
//Code Borrowed From CSci 1933 Moodle Webpage: https://ay14.moodle.umn.edu/course/view.php?id=11909
//Kyle Doerrler 5007181 & Marshall Moberg 4729197 

public interface Q {

	//Interface for a Queue

	void add(Object o);

	/* adds an object o to a queue placing it in the order of arrival 
    relative to other items added to the queue--first in, first out
    (FIFO) */

	Object remove();

	/* removes and returns the object placed in a queue prior
    to any other items presently in the queue */

	int length();

	/* returns the integer quantity of items currently present in the 
    queue */ 

}  // Q Interface
