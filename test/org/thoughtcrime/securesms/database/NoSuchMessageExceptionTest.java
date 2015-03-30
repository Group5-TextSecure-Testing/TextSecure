package org.thoughtcrime.securesms.database;

import java.io.IOException;
import static java.nio.charset.StandardCharsets.US_ASCII;
import android.content.Context;
import android.test.InstrumentationTestCase;
import android.test.AndroidTestCase;

import org.junit.Test;

import static org.junit.Assert.*;

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
