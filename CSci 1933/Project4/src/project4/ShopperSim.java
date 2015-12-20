package project4;

//Some Code Adapted from CarSim.java on CSci 1933 Moodle Webpage: https://ay14.moodle.umn.edu/course/view.php?id=11909
//Kyle Doerrler 5007181 & Marshall Moberg 4729197 

public class ShopperSim {

	public static PQ agenda = new PQ();
	public static Checker checker;
	public static int serviceTime;
	
	
	//**Change Variable Values Here:
	public static Checker[] checkoutlines = new Checker[12];   //Amount of Checkout Lines (0-12)
	public static int expresslanes = 11;                       //Amount of Express Lanes (1-11)
	public static boolean employeebagging = true;              //Employee or Customer Bagging (true/false)
	public static int averagearrival = 30;					   //Average Initial Arrival Time (10-50)

	
	
	public static void main(String args[]) {

		for(int i=0; i < checkoutlines.length; i++){   //fills the array with checkers of the appropriate type
			System.out.println(ShopperSim.checkoutlines.length);

			if (employeebagging) {
				checkoutlines[i] = new Checker(i, 0);
				
			}
			else {
				checkoutlines[i] = new Checker(i, 1);
			}
		}

		agenda.add(new ShopperMaker(ShopperMaker.intRandomShopperArrival(), ShopperMaker.intRandomItemAmount()), 0); //adds the shopper maker event to the agenda
		
		while (agenda.getCurrentTime() <= 10000) { //runs the agenda with the added shoppermaker above for the total sim time specified
			agenda.remove().run();
			
		}
		
		for(int i=0; i<checkoutlines.length; i++){
			Stat.updateQueueStats(ShopperSim.agenda.getCurrentTime(),checkoutlines[i].shopperq.length());
	
		}
		//WriteData.createCsvFile("P4WorkingValidationExpress.csv"); //used to output to a CSV file
		Stat.displayStats();

	}


}