package solutions;

import java.util.Scanner;

public class Reverse {

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		Scanner s = new Scanner(System.in); 
		System.out.println("Enter number: ");
		String numStr = s.next();
		numStr = numStr.replace("-","");
		int number = Integer.valueOf(numStr);
		System.out.println(reverse2(number));
		System.out.println(reverse2(reverse2(number)));
	}
	
	public static int reverse(int num) {
			int iterator = 1;
			int newnum = 0;
			int digits = (int)Math.ceil(Math.log10(num));
			while (iterator -1 < digits) {
				newnum += ((num / (int)Math.pow(10, digits -iterator)) % 10) * (int)Math.pow(10,iterator-1);
				iterator ++;
			}
			return newnum;
		}
	
	public static int num2 = 0;
	public static int reverse2(int num) {
	     if (num < 10) {
	         num2 = num2*10 + num;
	     }
	     else {
	    	 num2 = num2*10 + (num % 10);
	        num = reverse2(num / 10);

	     }
	     return num2;
	 }
}
