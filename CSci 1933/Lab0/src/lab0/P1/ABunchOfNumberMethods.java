package lab0.P1;

/**
 * Lab 0 Part 1
 * There are a number of problems with the classes syntax
 */
public class ABunchOfNumberMethods {
	
	public int add(int a, int b) {
		return a + b;
	}

	public int convertToInt(double someDouble) {
		return (int)someDouble;
	}

	public int convertToInt(String number) {
		return Integer.parseInt(number);
	}

	public double divide(int numerator, int denominator) {
		return (double)numerator / denominator;
	}

	public int remainder(int dividend, int divisor) {
		int closestMultiple = 0;
		while (closestMultiple + divisor < dividend) {
			closestMultiple += divisor;
		}
		return dividend - closestMultiple;
	}
}
