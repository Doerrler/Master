package project4;
//Some Code Adapted from Car.java on CSci 1933 Moodle Webpage: https://ay14.moodle.umn.edu/course/view.php?id=11909
//Kyle Doerrler 5007181 & Marshall Moberg 4729197 

public class Shopper {

    
private double arrivalTime;
private int numItems;


 public Shopper(double time, int numberofitems) {
     arrivalTime = time;
     numItems = numberofitems;
 }

 public double getArrivalTime() {
     return arrivalTime;
 }

 public int getNumItems() {
     return numItems;
 }

}