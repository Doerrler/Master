package lab6;

public class Polynomial {
	
	private double a = 0;
	private double b = 0;
	private double c = 0;
	
	public Polynomial() {
		this.a = 0;
		this.b = 0;
		this.c = 0;
	}
	
	public Polynomial(double a, double b, double c) {
		this.a = a;
		this.b = b;
		this.c = c;
	}
	
	public double getA() {
		return a;
	}
	public double getB() {
		return b;
	}
	public double getC() {
		return c;
	}
	
	public Polynomial add(Polynomial p) {
		double newA = this.a + p.getA();
		double newB = this.b + p.getB();
		double newC = this.c + p.getC();
		Polynomial newP = new Polynomial(newA, newB, newC);
		return newP;
	}
	
	public double evaluate(double x) {
		return a * Math.pow(x,2) + b*x + c;
	}
	
	public static void main(String[] args) {
		Polynomial ploy1 = new Polynomial(2,3,4);
		Polynomial ploy2 = new Polynomial(1,2,3);
		
		Polynomial ploy3 = ploy1.add(ploy2);
		System.out.println(ploy3.evaluate(1));
	}

}
