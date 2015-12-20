package solutions;

import java.util.Scanner;

public class MaxDigit {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		System.out.println("Enter number: ");
		String numStr = s.next();
		numStr = numStr.replace("-","");
		int number = Integer.valueOf(numStr);
		System.out.println("Largest digit is " + maxDigit(number));
	}
	
	public static int maxDigit(int num) {
		int digits = (int)Math.ceil(Math.log10(num));
		int power = digits - 1;
		int largest = 0;
		int potLargest = 0;
		while (power >= 0) {
			potLargest = num / (int)Math.pow(10, power);
			if (potLargest > largest)
				largest = potLargest;
			num -= (int)potLargest*Math.pow(10, power);
			power--;
		}
		return largest;
	}
}
