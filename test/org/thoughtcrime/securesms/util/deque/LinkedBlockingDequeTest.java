package org.thoughtcrime.securesms.util.deque;

import static org.junit.Assert.*;

import java.util.Arrays;
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
	
	@Test
	/**
	 * Method to check if the current deque contains a specificobject
	 * @throws Exception
	 */
	public void testContainsMethod() throws Exception{
		final LinkedBlockingDeque<String> Deque = new LinkedBlockingDeque<String>(20);
		final String stringOfStuff = "Hello I'm a String";
		Deque.offerFirst(stringOfStuff);
		boolean hasTo = true;
		
		assertEquals(hasTo, Deque.contains(stringOfStuff));
		
	}

	@Test
	/**
	 * Method converts Deque to an Array, then checks if the First and Last elements match
	 * @throws Exception
	 */
	public void testFirstAndLast() throws Exception{
		final LinkedBlockingDeque<String> Deque = new LinkedBlockingDeque<String>(20);
		final String Joe = "Joe";
		final String Bob = "Bob";
		final String Mary = "Mary";
		final String Sue = "Sue";
		Deque.add(Joe);
		Deque.add(Bob);
		Deque.add(Sue);
		Deque.add(Mary);
		
		final Object[] names = Deque.toArray();
		
		assertEquals(names[0], Deque.getFirst());
		assertEquals(names[Deque.size()-1], Deque.getLast());
		
		assertEquals(Joe, Deque.getFirst());
		assertEquals(Mary, Deque.getLast());
	}
	
}
