package org.thoughtcrime.securesms.util;

import java.io.IOException;
import static java.nio.charset.StandardCharsets.US_ASCII;
import android.content.Context;
import android.test.InstrumentationTestCase;
import android.test.AndroidTestCase;

public final class Base64Test extends InstrumentationTestCase {

    public void testJUnitIsActuallyDoingThings() throws Exception {
        assertEquals(0, 1);
    }

    // I want a source generator with an input of a three-dimensional array
    
    public void testEncode_Empty() throws Exception {
        final byte[] src = {};
        final String expected = "";
        final String result = Base64.encodeBytes(src);
        assertEquals(expected, result);
    }
    
    public void testDecode_Empty() throws Exception {
        final byte[] expected = {};
        final String src = "";
        final byte[] result = Base64.decode(src);
        assertEquals(expected, result);
    }
    
    public void testDecodeArray_Empty() throws Exception {
        final byte[] expected = {};
        final byte[] src = {};
        final byte[] result = Base64.decode(src);
        assertEquals(expected, result);
    }

    public void testEncode_0() throws Exception {
        final byte[] src = {(byte) 0};
        final String expected = "AA==";
        final String result = Base64.encodeBytes(src);
        assertEquals(expected, result);
    }

    public void testEncodeWithoutPadding_0() throws Exception {
        final byte[] src = {(byte) 0};
        final String expected = "AA";
        final String result = Base64.encodeBytesWithoutPadding(src);
        assertEquals(expected, result);
    }

    public void testEncode_url_0() throws Exception {
        final byte[] src = {(byte) 0};
        final String expected = "AA==";
        final String result = Base64.encodeBytes(src, Base64.URL_SAFE);
        assertEquals(expected, result);
    }

    public void testEncodeWithoutPadding_url_0() throws Exception {
        final byte[] src = {(byte) 0};
        final String expected = "AA";
        final String result = Base64.encodeBytesWithoutPadding(src);
        assertEquals(expected, result);
    }

    public void testEncode_ordered_0() throws Exception {
        final byte[] src = {(byte) 0};
        final String expected = "--==";
        final String result = Base64.encodeBytes(src, Base64.ORDERED);
        assertEquals(expected, result);
    }

    public void testEncodeWithoutPadding_ordred_0() throws Exception {
        final byte[] src = {(byte) 0};
        final String expected = "--";
        final String result = Base64.encodeBytesWithoutPadding(src);
        assertEquals(expected, result);
    }

    public void testDecode_0() throws Exception {
        final byte[] expected = {(byte) 0};
        final String src = "AA==";
        final byte[] result = Base64.decode(src);
        assertEquals(expected, result);
    }

    public void testDecode_url_0() throws Exception {
        final byte[] expected = {(byte) 0};
        final String src = "AA==";
        final byte[] result = Base64.decode(src, Base64.URL_SAFE);
        assertEquals(expected, result);
    }

    public void testDecode_ordered_0() throws Exception {
        final byte[] expected = {(byte) 0};
        final String src = "--==";
        final byte[] result = Base64.decode(src, Base64.ORDERED);
        assertEquals(expected, result);
    }

    public void testDecodeArray_0() throws Exception {
        final byte[] expected = {(byte) 0};
        final byte[] src = {(byte) 'A', (byte) 'A', (byte) '=', (byte) '='};
        final byte[] result = Base64.decode(src);
        assertEquals(expected, result);
    }

    public void testEncode_000() throws Exception {
        final byte[] src = {(byte) 0, (byte) 0, (byte) 0};
        final String expected = "AAAA";
        final String result = Base64.encodeBytes(src);
        assertEquals(expected, result);
    }

    public void testEncodeWithoutPadding_000() throws Exception {
        final byte[] src = {(byte) 0, (byte) 0, (byte) 0};
        final String expected = "AAAA";
        final String result = Base64.encodeBytesWithoutPadding(src);
        assertEquals(expected, result);
    }

    public void testDecode_000() throws Exception {
        final byte[] expected = {(byte) 0, (byte) 0, (byte) 0};
        final String src = "AAAA";
        final byte[] result = Base64.decode(src);
        assertEquals(expected, result);
    }

    public void testDecodeArray_000() throws Exception {
        final byte[] expected = {(byte) 0, (byte) 0, (byte) 0};
        final byte[] src = {(byte) 'A', (byte) 'A', (byte) 'A', (byte) 'A'};
        final byte[] result = Base64.decode(src);
        assertEquals(expected, result);
    }

    public void testEncode_0000() throws Exception {
        final byte[] src = {(byte) 0, (byte) 0, (byte) 0, (byte) 0};
        final String expected = "AAAAAA==";
        final String result = Base64.encodeBytes(src);
        assertEquals(expected, result);
    }

    public void testDecode_0000() throws Exception {
        final byte[] expected = {(byte) 0, (byte) 0, (byte) 0, (byte) 0};
        final String src = "AAAAAA==";
        final byte[] result = Base64.decode(src);
        assertEquals(expected, result);
    }

    public void testDecodeArray_0000() throws Exception {
        final byte[] expected = {(byte) 0, (byte) 0, (byte) 0, (byte) 0};
        final byte[] src = "AAAAAA==".getBytes(US_ASCII);
        final byte[] result = Base64.decode(src);
        assertEquals(expected, result);
    }

    public void testEncode_103() throws Exception {
        final byte[] src = {(byte) 103};
        final String expected = "Zw==";
        final String result = Base64.encodeBytes(src);
        assertEquals(expected, result);
    }

    public void testDecode_103() throws Exception {
        final byte[] expected = {(byte) 103};
        final String src = "Zw==";
        final byte[] result = Base64.decode(src);
        assertEquals(expected, result);
    }

    public void testEncode_104() throws Exception {
        final byte[] src = {(byte) 104};
        final String expected = "aA==";
        final String result = Base64.encodeBytes(src);
        assertEquals(expected, result);
    }

    public void testDecode_104() throws Exception {
        final byte[] expected = {(byte) 104};
        final String src = "aA==";
        final byte[] result = Base64.decode(src);
        assertEquals(expected, result);
    }

    public void testEncode_max() throws Exception {
        final byte[] src = {(byte) 255, (byte) 255, (byte) 255};
        final String expected = "////";
        final String result = Base64.encodeBytes(src);
        assertEquals(expected, result);
    }

    public void testEncode_url_max() throws Exception {
        final byte[] src = {(byte) 255, (byte) 255, (byte) 255};
        final String expected = "____";
        final String result = Base64.encodeBytes(src, Base64.URL_SAFE);
        assertEquals(expected, result);
    }

    public void testEncode_ordered_max() throws Exception {
        final byte[] src = {(byte) 255, (byte) 255, (byte) 255};
        final String expected = "zzzz";
        final String result = Base64.encodeBytes(src, Base64.ORDERED);
        assertEquals(expected, result);
    }

    public void testDecode_max() throws Exception {
        final byte[] expected = {(byte) 255, (byte) 255, (byte) 255};
        final String src = "////";
        final byte[] result = Base64.decode(src);
        assertEquals(expected, result);
    }

    public void testDecodeArray_max() throws Exception {
        final byte[] expected = {(byte) 255, (byte) 255, (byte) 255};
        final byte[] src = "////".getBytes(US_ASCII);
        final byte[] result = Base64.decode(src);
        assertEquals(expected, result);
    }

    public void testDecode_url_max() throws Exception {
        final byte[] expected = {(byte) 255, (byte) 255, (byte) 255};
        final String src = "____";
        final byte[] result = Base64.decode(src, Base64.URL_SAFE);
        assertEquals(expected, result);
    }

    public void testDecode_ordered_max() throws Exception {
        final byte[] expected = {(byte) 255, (byte) 255, (byte) 255};
        final String src = "zzzz";
        final byte[] result = Base64.decode(src, Base64.ORDERED);
        assertEquals(expected, result);
    }

    /**
     * Since this is white box, and I know that this class does everything
     * via lookup tables, so even though black-box makes it seem like [A-Z]
     * [a-z], [0-9], +, and / are different equivalence cases, since everything
     * is lookup, they're not.
     */


    public void testDecode_canHandleWhitespace() throws Exception {
        final byte[] expected = {(byte) 0, (byte) 0, (byte) 0};
        final String src = "A A A A";
        final byte[] result = Base64.decode(src);
        assertEquals(expected, result);
    }

    public void testDecode_errorsOnIllegalChar() throws Exception {
    	try {
    		final byte[] result = Base64.decode("$A==");
    		fail("Expected exception to be thrown");
    	} catch (IOException e) {
    		// succeed
    	} catch (Exception e) {
    		fail("Expected IOException to be thrown; not " + e);
    	}
    }

    public void testDecode_errorsOnIllegalChar2() throws Exception {
    	try {
    		final byte[] result = Base64.decode("AAA&");
    		fail("Expected exception to be thrown");
    	} catch (IOException e) {
    		// succeed
    	} catch (Exception e) {
    		fail("Expected IOException to be thrown; not " + e);
    	}
    }
    
    public void testDecode_errorOnIllegalChar_url() throws Exception {
    	try {
    		final byte[] result = Base64.decode("AAA+", Base64.URL_SAFE);
    		fail("Expected exception to be thrown");
    	} catch (IOException e) {
    		// succeed
    	} catch (Exception e) {
    		fail("Expected IOException to be thrown; not " + e);
    	}
    }
    
    public void testDecode_errorOnIllegalChar_normal() throws Exception {
    	try {
    		final byte[] result = Base64.decode("AAA_");
    		fail("Expected exception to be thrown");
    	} catch (IOException e) {
    		// succeed
    	} catch (Exception e) {
    		fail("Expected IOException to be thrown; not " + e);
    	}
    }

}