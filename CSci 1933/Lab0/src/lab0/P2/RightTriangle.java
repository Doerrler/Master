package lab0.P2;

public class RightTriangle {
	
	public RightTriangle(double height, double base) {
		this.height = height;
		this.base = base;
	}
	
	private double height;
	private double base;
	
	public double getHeight() {
		return height;
	}
	public double getBase() {
		return base;
	}
	public double getPerimeter() {
		return height + base + Math.sqrt(Math.pow(height, 2) + Math.pow(base, 2));
	}
	public double getArea() {
		return .5*base*height;
	}
	
	public void setHeight(double value) {
		height = value;
	}
	public void setBase(double value) {
		base = value;
	}
}
