package org.thoughtcrime.securesms.util.deque;

import static org.junit.Assert.*;

import org.junit.Test;

public class LinkedBlockingDequeTest {

	@Test
	public void testConstructWithCap() throws Exception {
		final LinkedBlockingDeque<String> tester = new LinkedBlockingDeque<String>(20);
		assertEquals(20,tester.getCapacity());
	}

}
