package lab9;

public class HanoiRecord extends ActivationRecord {

	// *1* formal parameters
	private int n; // value of parameter n for this instance of hanoi
	private char source;
	private char dest;
	private char temp;

	// *2* no local variables for hanoi

	/*
	*3* resume location
	Note that any method that contains a recursive call will have at 
	least two possible "resume" locations. 
	1 - The start of the method (entry point when the method is 
	first invoked)
	2 - The location immediately after returning from the 
	recursive call
	However, methods that have more than one recursive call, will have
	an additional entry point for each additional recursive call. 
	This is needed because once a recursive call is complete, the 
	program must "know" where to come back to in order to resume 
	execution immediately after the point of the recursive call
	*/

	private int line = 0; // methods always start at the beginning

	// constructor for new instance of an activation record
	public HanoiRecord(int newN, char newSource, char newDest, char newTemp) {
	n = newN;
	source = newSource;
	dest = newDest;
	temp = newTemp;
	}

	// *4* reference to code for hanoi is kept within the run() method
	public void run() {

	switch (line) {
		case 0: // no resume point, this call is done when n==1
		if (n == 1) {
		System.out.println(" Move disk 1 from " + source + " to " + dest);
		HanoiR.count++;
		HanoiR.s.pop(); // this call done, remove frame
		return;
		}
		// if we don't quit, fall into the "else" case below
		// else clause from fact
		HanoiR.s.push(new HanoiRecord(n - 1, source, temp, dest));
		HanoiR.count++;
		line = 1; // set resume point after recursive call
		return;
		case 1:
		System.out.println(" Move disk " + n + " from " + source + " to " + dest);
		HanoiR.s.push(new HanoiRecord(n - 1, temp, dest, source));
		line = 2;
		return;
		case 2: // resume point after hanoi value for n-1 case
		// no need to set resume line: call will be complete
		HanoiR.s.pop(); // this call done, remove frame
		return;
		default: System.out.println("\nWe should not get here.");
		}
	}
}