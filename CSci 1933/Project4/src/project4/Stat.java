package project4;
//Code Borrowed From CSci 1933 Moodle Webpage: https://ay14.moodle.umn.edu/course/view.php?id=11909
//Kyle Doerrler 5007181 & Marshall Moberg 4729197 

public class Stat {

	private static double lastUpdateTime;
	private static int oldQLength;
	private static double lastQUpdateTime;

	protected static int count;
	protected static double totalTime;
	protected static double busyTime;
	protected static double idleTime;
	protected static double maxWait;
	protected static double averageWait;
	protected static int maxQLength;
	protected static double averageQLength; 
	protected static double averageServiceTime;

	
	public static void updateQueueStats(double time, int qlen) { //When a shopper enters a new queue updates the lengths and time
		if (qlen > maxQLength)
			maxQLength = qlen;
		averageQLength += oldQLength * (time - lastQUpdateTime);
		totalTime += time - lastQUpdateTime;
		lastQUpdateTime = time;
		oldQLength = qlen;
	}

	public static void updateBusyTimeStats(double time) {  //keeps track of when the checker is working
		busyTime += time - lastUpdateTime;
		lastUpdateTime = time;
	}        

	public static void updateIdleTimeStats(double time) {  //keeps track of when the checker isn't working
		idleTime += time - lastUpdateTime;
		lastUpdateTime = time;
	}

	public static void updateServiceTimeStats(double st) {
		averageServiceTime += st;
	}

	public static void updateWaitTimeStats(double time, double enterTime) { //Whenever a shopper leaves a checkout line updates the waiting variables
		double wait = time - enterTime;
		if (wait > maxWait)
			maxWait = wait;
		averageWait += wait;
		count++;  // another shopper leaves the waiting queue
	} 

	public static void displayStats() {
		System.out.println("\n** Simulation Results **\n");
		System.out.println(" Calculated Simulation Time: " + totalTime);
		System.out.println(" Idle Time Per Checker: " + Math.round(((idleTime/totalTime)*100)*100)/100D);
		System.out.println(" Busy Time Per Checker: " + Math.round(((busyTime/totalTime)*100)*100)/100D);
		System.out.println(" (Busy Time based on service time: " + averageServiceTime + ")\n");
		System.out.println(" Maximum Queue Length: " + ShopperMaker.longest);
		System.out.println(" Avg. Queue Length: " + averageQLength/totalTime);
		System.out.println(" Maximum Waiting Time: " + Math.round(((averageServiceTime/count)*100)/100D)*ShopperMaker.longest);
		System.out.println(" Avg. Waiting Time for shoppers through the queue: " + Math.round(((averageServiceTime/count)*100)/100D)*averageQLength/totalTime);
		System.out.println(" Avg. Service Time: " + Math.round(((averageServiceTime/count)*100)/100D));
		System.out.println(" Number of shoppers through system: " + count);
		System.out.println("\n");
	}

}  // Stat class
