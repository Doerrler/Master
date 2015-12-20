package solutions;

import static org.junit.Assert.*;

import org.junit.Test;

public class RationalTest {

	@Test
	public void testRational() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetRational() {
		Rational frac1 = new Rational(1,1);
		frac1.setRational();
		assertEquals(4,frac1.getNumer());
	}
	
	@Test
	public void testSetNumer() {
		fail("Not yet implemented");
	}

	@Test
	public void testSetDenom() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetNumer() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetDenom() {
		fail("Not yet implemented");
	}

	@Test
	public void testAddRational() {
		Rational frac1 = new Rational(1,3);
		Rational frac2 = new Rational(2,5);
		
		double expectedNumer = 11;
		double expectedDenom = 15;
		
		frac1.addRational(frac2);
		
		assertEquals(expectedNumer,frac1.getNumer(),0.1);
		assertEquals(expectedDenom,frac1.getDenom(),0.1);
	}

	@Test
	public void testSubRational() {
		fail("Not yet implemented");
	}

	@Test
	public void testMulRational() {
		fail("Not yet implemented");
	}

	@Test
	public void testDivRational() {
		fail("Not yet implemented");
	}

}
