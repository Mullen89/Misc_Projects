package lab6;

import balloon4.Balloon;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

public class BalloonTests {

	private Balloon bb;
	
	@Before
	public void setup() {
		bb = new Balloon(10);
		bb.blow(3);
	}
	@Test
	public void testInitial() {
		String msg = "A newly constructed balloon should have the radius it was constructed with";
		assertEquals(msg, 3, bb.getRadius());
	}
	@Test
	public void testBlow() {
		String msg = "the constructed balloon's radius should be increased by the amount given by the method 'blow'";
		bb.blow(5);
		assertEquals(msg, 8, bb.getRadius());
	}
	@Test
	public void testDeflate() {
		String msg = "the constructed balloon's radius should be 0 after being deflated";
		bb.deflate();
		assertEquals(msg, 0, bb.getRadius());
	}
	@Test 
	public void testIsPopped() {
		String msg = "the constructed balloon has not been popped, so method 'isPopped' should return false";
		assertFalse(msg, bb.isPopped());
	}
	@Test
	public void testPop() {
		String msg = "the constructed balloon is popped with method 'pop'. Method 'isPopped' should return true";
		bb.blow(5);
		bb.pop();
		assertTrue(msg, bb.isPopped());
	}
	@Test
	public void testBlowPop() {
		String msg = "the constructed balloon is inflated past it's max radius, so method 'isPopped' should return true";
		bb.blow(9);
		assertTrue(msg, bb.isPopped());
	}
	@Test
	public void testBlowAfterPop() {
		String msg = "the constructed balloon is inflated past it's max radius, popping it, and then blown again. "
				+ "Method 'getRadius' should return 0";
		bb.blow(9);
		bb.blow(5);
		assertEquals(msg, 0, bb.getRadius());
	}
	@Test
	public void testPoppedAfterDeflated() {
		String msg = "the constructed balloon should not be popped after deflated";
		bb.deflate();
		assertFalse(msg, bb.isPopped());
	}
}
