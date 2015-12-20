package lab0.P2;

public class Rectangle {
	
	public Rectangle(double length, double width) {
		this.length = length;
		this.width = width;
	}

	private double length;
	private double width;
	
	public double getLength() {
		return length;
	}
	public double getWidth() {
		return width;
	}
	public double getPerimeter() {
		return 2*width + 2*length;
	}
	public double getArea() {
		return width*length;
	}
	
	public void setLength(double value) {
		length = value;
	}
	public void setWidth(double value) {
		width = value;
	}
}