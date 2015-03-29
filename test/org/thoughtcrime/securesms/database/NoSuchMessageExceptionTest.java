package org.thoughtcrime.securesms.database;

import java.io.IOException;
import static java.nio.charset.StandardCharsets.US_ASCII;
import android.content.Context;
import android.test.InstrumentationTestCase;
import android.test.AndroidTestCase;

public class NoSuchMessageExceptionTest extends InstrumentationTestCase {
	
	public void testConstructorString() {
		final String msg = "abcde";
		assertEquals(msg, (new NoSuchMessageException(msg).getMessage()));
	}
	
	public void testConstructorException() {
		final Exception cause = new IndexOutOfBoundsException();
		assertEquals(cause, (new NoSuchMessageException(cause).getCause()));
	}
}
