package project1;

public class PrimeInst {

	public static void main(String[] args) {
	}
	
	int prime = 2;
	
	public boolean isPrime(int p) {
		if (p < 2) {
			return false;
		}
		else {
			int iterator = 1;
			while (iterator < p-1) {
				iterator ++;
				if (p % iterator == 0) {
					return false;
				}
			}
			return true;
		}
	}
	public int getPrime() {
		while (isPrime(prime) == false) {
			prime ++;
		}
		prime ++;
		return prime -1;
	}
	public void reset() {
		prime = 2;
	}
	public void reset(int n) {
		prime = n;
	}
	public int sumPrimes(int n) {
		int primesum = 0;
		int primeamount = 0;
		while (primeamount < n) {
			if (isPrime(prime) == true) {
				primesum += prime;
				primeamount ++;
				prime ++;
			}
			else prime ++;
		}
		return primesum;
	}
}
