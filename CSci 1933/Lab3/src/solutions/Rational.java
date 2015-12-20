package solutions;

import java.util.Scanner;

public class Rational {

    private int numer = 0;  
    private int denom = 0;

    public Rational(int a, int b) {
        numer = a;
        denom = b;
    }

    // accessor methods
    public void setRational() {
    	Scanner s = new Scanner(System.in);
    	System.out.println("Enter numerator: ");
    	numer = s.nextInt();
    	System.out.println("Enter denominator: ");
    	denom = s.nextInt();
    }
    
    public String toString() {
    	return this.getNumer() + " / " + this.getDenom();
    }

    public void setNumer(int value) {
        numer = value;
    }

    public void setDenom(int value) {
        denom = value;
    }

    public int getNumer() {
        return numer;
    }
   
    public int getDenom() {
        return denom;
    }

    // operators

    public void addRational(Rational other) {
        numer = numer*other.getDenom() + other.getNumer()*denom;
        denom = denom*other.getDenom();
    }

    public void subRational(Rational other) {
        numer = numer*other.getDenom() - other.getNumer()*denom;
        denom = denom*other.getDenom();
    }
    
    public void mulRational(Rational other) {
        numer = numer*other.getNumer();
        denom = denom*other.getDenom();
    }
    
    public void divRational(Rational other) {
        numer = numer*other.getDenom();
        denom = denom*other.getNumer();
    }
    
    public static void main(String[] args) {
    	Rational frac1 = new Rational(1,1);
		Rational frac2 = new Rational(1,1);
		System.out.println("For first fraction: ");
		frac1.setRational();
		System.out.println("For second fraction: ");
		frac2.setRational();
		frac1.addRational(frac2);
		System.out.println(frac1.toString());
		}
}