package Lab1.src.lab1.S4;

public class Rational {

    private int numer = 0;  
    private int denom = 0;

    public Rational(int a, int b) {
        numer = a;
        denom = b;
    }

    // accessor methods

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
}