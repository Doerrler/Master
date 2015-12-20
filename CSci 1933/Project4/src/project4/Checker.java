package project4;
//Kyle Doerrler 5007181 & Marshall Moberg 4729197 

public class Checker {
	private int checkerNum;
	private int bagType;
	Q1 shopperq;

	public double busyTime;
	public double idleTime;
	public double totalTime;
	public double lastUpdateTime;

	public Checker(int checkoutnum, int typeofbagging) {
		checkerNum = checkoutnum;
		bagType = typeofbagging;
		shopperq = new Q1();
	}

	public boolean isBusy() {
		return shopperq.isBusy();
	}

	public int getCheckerNum() {
		return checkerNum;
	}
	public int getBagType() {
		return bagType;
	}
	public int length() {  //returns the length of the checkout line
		return shopperq.length();
	}
	public void add(Object o) {
		shopperq.add(o);
	}
	public void remove() {
		shopperq.remove();
	}
}
