package project4;
//Some Code Adapted from CarMaker.java on CSci 1933 Moodle Webpage: https://ay14.moodle.umn.edu/course/view.php?id=11909
// Kyle Doerrler 5007181 & Marshall Moberg 4729197 

import java.util.Random;

public class ShopperMaker implements Event {

	private int interval;
	private int numberofitems;
	private int servTime;
	static int longest = 0; //Keeps track of maximum queue length

	public ShopperMaker(int intval, int ni) {
		interval = intval;
		numberofitems = ni;
		
	}

	public static int intRandomShopperArrival() {  //used to generate each shoppers arrival time bassed on the average arrival specified
		double randnum = Math.random();  //returns a value in the interval [0,1]
		if (randnum > .9) return (int) (ShopperSim.averagearrival + (.75 * ShopperSim.averagearrival));
		else if ( .90 >= randnum && randnum > .75) return (int) (ShopperSim.averagearrival + (.50 * ShopperSim.averagearrival));
		else if ( .75 >= randnum && randnum > .55) return (int) (ShopperSim.averagearrival + (.20 * ShopperSim.averagearrival));
		else if ( .55 >= randnum && randnum >= .45) return (int) ShopperSim.averagearrival;
		else if ( .45 > randnum && randnum >= .25) return (int) (ShopperSim.averagearrival - (.20 * ShopperSim.averagearrival));
		else if ( .25 > randnum && randnum >= .10) return (int) (ShopperSim.averagearrival - (.50 * ShopperSim.averagearrival));
		else if ( .10 > randnum) return (int) (ShopperSim.averagearrival - (.75 * ShopperSim.averagearrival));
		else return 0;
	}

	public static int intRandomItemAmount() {   //generates the amount of items each shopper has with appropriate probabilities
		int[] itemarray = {10, 10, 10, 20, 20, 20, 20, 20, 30, 30, 30, 30, 30, 30, 40, 40, 40, 40, 50, 50, 50, 50, 60, 60, 70, 70, 80, 80, 90, 100};
		Random r = new Random();
		int index = r.nextInt(30);
		int max = itemarray[index];
		int onesplace = r.nextInt(9);
		int numitems = max - onesplace;
		return numitems;
	}

	public void run() {  //the run method which creates a shopper and picks the best line (the shortest line)
		
		ShopperSim.agenda.add(new ShopperMaker(intRandomShopperArrival(), numberofitems), interval);
		Shopper newShopper = new Shopper(ShopperSim.agenda.getCurrentTime(), intRandomItemAmount());
		boolean expresspossible = false;
		int shortest = 1000000;
		int expresscheck = ShopperSim.expresslanes;
		Checker bestline = ShopperSim.checkoutlines[0];
		
		//PICKING BEST CHECKOUTLINE
		if (newShopper.getNumItems() <= 10 && expresscheck != 0){
			expresspossible = true;
		}
		if (expresspossible){  //if expresslanes are used checks to see if the person is eligable then places them appropriately
			for(int i=0; i < ShopperSim.checkoutlines.length; i++){
				if (ShopperSim.checkoutlines[i].shopperq.length() < shortest){
					shortest = ShopperSim.checkoutlines[i].shopperq.length();
					bestline = ShopperSim.checkoutlines[i];
				}
				else if (ShopperSim.checkoutlines[i].shopperq.length() > longest){
					longest = ShopperSim.checkoutlines[i].shopperq.length();
				}
			}
		}
		else {
			for(int j=expresscheck; j < ShopperSim.checkoutlines.length; j++){
				if (ShopperSim.checkoutlines[j].shopperq.length() < shortest){
					shortest = ShopperSim.checkoutlines[j].shopperq.length();
					bestline = ShopperSim.checkoutlines[j];
				}
				else if (ShopperSim.checkoutlines[j].shopperq.length() > longest){
					longest = ShopperSim.checkoutlines[j].shopperq.length();
				}
			}
		if (longest == shortest){
			longest ++;
		}
		}
		
		//adds the shopper to the line once the best option is determined
		if(bestline.isBusy() == true){  //if the checker is busy it will automatically start on the next shopper when it finishes
			CheckerEvent.enter(bestline,newShopper);
		}
		else{	// if the checkout lane is not already busy it will make the checker begin to work by scheduling an event
			CheckerEvent.enter(bestline,newShopper);
			int numberitems = newShopper.getNumItems();
			if (bestline.getBagType() == 0) {  //determines the service time based on bagging type and item amount
				servTime = 5*numberitems;
			}
			else {
				servTime = 9*numberitems;
			}
		    Stat.updateServiceTimeStats((double) servTime);
			ShopperSim.agenda.add(new CheckerEvent(bestline.getCheckerNum(),servTime,bestline.getBagType()), servTime);
		}
	}
}













