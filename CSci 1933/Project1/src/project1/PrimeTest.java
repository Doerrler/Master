package project1;

import static org.junit.Assert.*;

import org.junit.Test;

public class PrimeTest {

	@Test
	public void testMain() {
		PrimeInst PrimeCounter1 = new PrimeInst();
		PrimeInst PrimeCounter2 = new PrimeInst();
		assertEquals(2,PrimeCounter1.getPrime());
		assertEquals(2,PrimeCounter2.getPrime());
		assertEquals(15,PrimeCounter1.sumPrimes(3));
		assertEquals(3,PrimeCounter2.getPrime());
		PrimeCounter1.reset(6);
		assertEquals(7,PrimeCounter1.getPrime());
	}
}