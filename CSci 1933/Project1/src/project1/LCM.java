package project1;

public class LCM {
	//Part 3: Number with LCM
	//Note: As per the instructions, make sure this class is instantiable 
	
	public static void main(String[] args) {  
	}
	
	int num;
	
	public LCM(int num) {
		if (num < 1) {
			System.out.println("Value must be positive integer.");
		}
		else {
			this.num = num;
		}
	}
	
	public int getLCM(int n) {
		int iterator = 2;
		while (iterator < (num*n)) {
			if ((iterator % num == 0) && (iterator % n == 0)) {
				return iterator;
			}
			else {
				iterator ++;
			}
		}
		return num*n; 
	}
	public int getGCD(int n) {
		int iterator = 2;
		while ((iterator < num) && (iterator < n)) {
			if ((num % iterator == 0) && (n % iterator == 0)) {
				return iterator;
			}
			else {
				iterator ++;
			}
		}
		if (iterator + 1 == num) {
			return num;
		}
		else {
			return n;
		}
	}
	public double getRatio(int n) {
		int ratio = (int)((int)getLCM(n)/(int)getGCD(n));
		return ratio;
	}
}
