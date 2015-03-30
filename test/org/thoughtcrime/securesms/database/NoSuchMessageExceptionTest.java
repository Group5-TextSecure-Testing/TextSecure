package org.thoughtcrime.securesms.database;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NoSuchMessageExceptionTest {

    @Test
	public void testConstructorString() {
		final String msg = "abcde";
		assertEquals(msg, (new NoSuchMessageException(msg).getMessage()));
	}

    @Test
	public void testConstructorException() {
		final Exception cause = new IndexOutOfBoundsException();
		assertEquals(cause, (new NoSuchMessageException(cause).getCause()));
	}
}
