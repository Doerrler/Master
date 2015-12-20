package solutions;

import java.util.Scanner;

public class MaxDigit2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		System.out.println("Enter number: ");
		String numStr = s.next();
		numStr = numStr.replace("-","");
		int number = Integer.valueOf(numStr);
		System.out.println("Largest digit is " + maxDigit2(number));
	}
	
	public static int maxDigit2(int num) {
		int potLargest = num % 10;
		if (num < 10)
			return num;
		if (potLargest > maxDigit2(num/10))
			return potLargest;
		else return maxDigit2(num/10);
	}
}
