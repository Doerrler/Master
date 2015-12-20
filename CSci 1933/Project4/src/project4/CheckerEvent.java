package project4;
//Kyle Doerrler 5007181 & Marshall Moberg 4729197 

//This implements the Event interface. A CheckerEvent is created for each completion of a customer's checkout. 
//When a customer leaves with their bagged groceries, the CheckerEvent causes the Checker that created it to 
//look to its queue to see if there is a Shopper waiting. If there is a Shopper queued, the associated Checker 
//will create a new CheckerEvent and schedule it (via the agenda) for the future at time depending on the number 
//of items in the queued Shopper's cart and whether customer or employee bagging is done. 
//(The CheckerEvent will need to have some way of associating with the Checker that initiated it.)

public class CheckerEvent implements Event {
	
	 private static boolean busy;
	 private int checkernumber;
	 private int numberitems;
	 private int serviceTime;
	 private int bagType;
	 private static Checker checkerline;
	 
	 public CheckerEvent() {
		 numberitems = 0;
		 serviceTime = 0;
	 }
	
	 public CheckerEvent(int checkernum, int servtime, int baggingType) {
		 checkernumber = checkernum;
		 checkerline = ShopperSim.checkoutlines[checkernum];
		 serviceTime = servtime;
	 }
	
	 public static void enter(Checker bestOption, Shopper c) { //used to update the array and stats when a shopper is added
		 bestOption.shopperq.add(c);
		 Stat.updateQueueStats(ShopperSim.agenda.getCurrentTime(),bestOption.shopperq.length());
	 }
	
	 public static boolean isBusy() {
	     return busy;
	 }
	
	 public void run() {
	
	     Shopper shopper;
	
	     if (busy == true) {  // this instance invoked because a shopper finished
	       Stat.updateBusyTimeStats(ShopperSim.agenda.getCurrentTime());
	     }
	     else  // this instance invoked because a new shopper arrived at an idle line
	       Stat.updateIdleTimeStats(ShopperSim.agenda.getCurrentTime());
	
	     if (checkerline.shopperq.length() == 0) {
	       busy = false;  // do nothing until notified of a new shopper arrival
	     }
	     else {
	       busy = true;  // start on next shopper in queue
	       shopper = (Shopper) checkerline.shopperq.remove();
	       Stat.updateQueueStats(ShopperSim.agenda.getCurrentTime(), checkerline.shopperq.length());
	       Stat.updateWaitTimeStats(ShopperSim.agenda.getCurrentTime(),shopper.getArrivalTime());
	       numberitems = shopper.getNumItems();
	       ShopperSim.agenda.add(new CheckerEvent(checkernumber,numberitems,this.bagType), serviceTime);
	     }
	
	 }
       
}