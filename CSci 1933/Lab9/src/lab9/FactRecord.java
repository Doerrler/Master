package lab9;
public class FactRecord extends ActivationRecord {

	// *1* formal parameters
	private int n; // value of parameter n for this instance of factorial

	// *2* no local variables for factorial

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
	public FactRecord(int newN) {
	n = newN;
	}

	// *4* reference to code for fact is kept within the run() method
	public void run() {

	switch (line) {
		case 0: // no resume point, this call is done when n==0
		if (n == 0) {
		FactR.returnedValue = 1; // set global return value
		FactR.s.pop(); // this call done, remove frame
		return;
		}
		// if we don't quit, fall into the "else" case below
		// else clause from fact
		FactR.s.push(new FactRecord(n - 1));
		line = 1; // set resume point after recursive call
		return;
		case 1: // resume point after factorial value for n-1 case
		// no need to set resume line: call will be complete
		FactR.returnedValue = n * FactR.returnedValue; 
		// setglobal return value
		FactR.s.pop(); // this call done, remove frame
		return;
		default: System.out.println("\nWe should not get here.");
		}
	}
}