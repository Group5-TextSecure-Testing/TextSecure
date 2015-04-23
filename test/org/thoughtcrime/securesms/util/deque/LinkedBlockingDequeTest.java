package org.thoughtcrime.securesms.util.deque;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import org.junit.Test;

public class LinkedBlockingDequeTest {

	@Test
	/**
	 * Method tests to check if the capacity check is correct when constructing
	 * a LinkedBlockingDeque
	 * @throws Exception
	 */
	public void testConstructWithCap() throws Exception {
		final LinkedBlockingDeque<String> tester = new LinkedBlockingDeque<String>(20);
		assertEquals(20,tester.remainingCapacity());
	}
	
	@Test
	/**
	 * Method tests to check if the OfferFirst Methods add to its corresponding deque
	 * @throws Exception
	 */
	public void testCheckOfferFirstMethods() throws Exception {
		final LinkedBlockingDeque<String> Deque1 = new LinkedBlockingDeque<String>(20);
		final LinkedBlockingDeque<String> Deque2= new LinkedBlockingDeque<String>(20);
		final String stringOfStuff = "Hello I'm a String";
		
		Deque1.offerFirst(stringOfStuff);
		Deque2.offerFirst(stringOfStuff, 2, TimeUnit.MINUTES);
		
		assertEquals(19,Deque1.remainingCapacity());
		assertEquals(19,Deque2.remainingCapacity());
	}

}
