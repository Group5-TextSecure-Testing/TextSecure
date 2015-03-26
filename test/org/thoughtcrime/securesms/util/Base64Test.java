package org.thoughtcrime.securesms.util;

import android.content.Context;
import android.test.InstrumentationTestCase;
import android.test.AndroidTestCase;

public final class Base64Test extends InstrumentationTestCase {

    public void testJUnitIsActuallyDoingThings() throws Exception {
        assertEquals(0, 1);
    }

    public void testEncodeBytes_Empty() throws Exception {
        final byte[] src = {};
        final String expected = "";
        final String result = Base64.encodeBytes(src);
        assertEquals(expected, result);
    }
    
    public void testDecodeBytes_Empty() throws Exception {
        final byte[] expected = {};
        final String src = "";
        final byte[] result = Base64.decode(src);
        assertEquals(expected, result);
    }

    public void testEncodeBytes_0() throws Exception {
        final byte[] src = {(byte) 0};
        final String expected = "AA==";
        final String result = Base64.encodeBytes(src);
        assertEquals(expected, result);
    }

    public void testDecodeBytes_0() throws Exception {
        final byte[] expected = {(byte) 0};
        final String src = "AA==";
        final byte[] result = Base64.decode(src);
        assertEquals(expected, result);
    }

    public void testEncodeBytes_000() throws Exception {
        final byte[] src = {(byte) 0, (byte) 0, (byte) 0};
        final String expected = "AAAA";
        final String result = Base64.encodeBytes(src);
        assertEquals(expected, result);
    }

}