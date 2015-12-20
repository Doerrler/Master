package lab9;

public class HanoiR {

	// static system stack
	public static Stack1 s = new Stack1();
	
	public static int count;
	
	static int n = 5;

	public static void main(String args[]) {

	System.out.println("\nTower of Hanoi Solution for " + n + " disks:");
	
	s.push(new HanoiRecord(n, 'a', 'b', 'c'));
	
//	System.out.println("\nTower of Hanoi Solution for " + n + " disks:");
	
	while(!s.isEmpty()) 
	{ // system loop goes until stack is empty
	((HanoiRecord) s.top()).run();
	} // end system loop

	// display final return value, if any
	System.out.println("\nSolution complete in " + count + " moves");
	} // main method

} // HanoiR class