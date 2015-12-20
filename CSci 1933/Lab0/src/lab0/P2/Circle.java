package lab0.P2;

public class Circle {

	public Circle(double radius) {
		this.radius = radius;
	}
	
	private double radius;
	
	public double getRadius() {
		return radius;
	}
	public double getPerimeter() {
		return 2*Math.PI*radius;
	}
	public double getArea() {
		return Math.PI*Math.pow(radius, 2);
	}
	
	public void setRadius(double value) {
		radius = value;
	}
}