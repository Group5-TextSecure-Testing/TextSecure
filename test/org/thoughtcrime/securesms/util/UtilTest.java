package org.thoughtcrime.securesms.util;

import android.widget.EditText;
import android.widget.TextView;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import ws.com.google.android.mms.pdu.CharacterSets;
import ws.com.google.android.mms.pdu.EncodedStringValue;

import static org.junit.Assert.*;

public class UtilTest {

    @Test
    public void testJoin() throws Exception {
        Collection<String> strings = new ArrayList<String>();
        strings.add("1");
        strings.add("2");
        strings.add("3");
        String result = Util.join(strings, " ^^^ ");
        assertEquals("1 ^^^ 2 ^^^ 3", result);
    }

    @Test
    public void testNewSingleThreadedLifoExecutor() throws Exception {
        //WARN in the sourcecode, there is a commented out line regarding to execution
        //priority
        ExecutorService ex = Util.newSingleThreadedLifoExecutor();
        assertFalse(ex.isShutdown());

        //@TODO execute something...

        ex.shutdown();
        assertTrue(ex.awaitTermination(10, TimeUnit.SECONDS));
        assertTrue(ex.isShutdown());

    }

    @Test
    public void testIsEmpty() throws Exception {
        EncodedStringValue[] val = null;
        assertTrue(Util.isEmpty(val));
        val = new EncodedStringValue[10];
        assertFalse(Util.isEmpty(val));
    }

    @Test
    public void testIsEmpty1() throws Exception {
        EditText text = null;
        assertTrue(Util.isEmpty(text));
        text = new EditText(null);
        assertTrue(Util.isEmpty(text));

        //Text below fails due to a lack of Android libraries for testing purposes
        //@TODO It could be possible to mock text for the purposes of this test
        //text.setText("abc", TextView.BufferType.NORMAL);
        //assertFalse(Util.isEmpty(text));
    }

    @Test
    public void testGetBoldedString() throws Exception {
        fail("Method depends heavily on Android components-- can't decouple for testing.");
    }

    @Test
    public void testToIsoString() throws Exception {
        byte[] bin = new byte[3];
        //Constant values gathered from 8859-1 documentation
        bin[0] = 97; //a
        bin[1] = 98; //b
        bin[2] = 99; //c
        String str = Util.toIsoString(bin);
        assertEquals("abc", str);

        bin[0] = 127; //undef
        bin[1] = -128; //undef, 0x80
        bin[2] = -127;//undef, 0x81
        str = Util.toIsoString(bin);
        assertEquals("\u007F\u0080\u0081", str);
    }

    @Test
    public void testToIsoBytes() throws Exception {
        String str = "abc";
        byte[] bin = new byte[3];
        //Constant values gathered from 8859-1 documentation
        bin[0] = 97; //a
        bin[1] = 98; //b
        bin[2] = 99; //c
        assertArrayEquals(bin, Util.toIsoBytes(str));
    }

    @Test
    public void testToUtf8Bytes() throws Exception {
        String str = "abc";
        byte[] bin = new byte[3];
        //Constant values gathered from utf8 documentation
        bin[0] = 97; //a
        bin[1] = 98; //b
        bin[2] = 99; //c
        assertArrayEquals(bin, Util.toUtf8Bytes(str));
    }

    @Test
    public void testWait() throws Exception {
        //Not 100% sure how to test this, but at least it will fail if an exception is thrown
        Object lock = "";
        synchronized (lock) {
            Util.wait(lock, 1);
        }
    }

    @Test
    public void testCanonicalizeNumber() throws Exception {
        fail("Method depends heavily on Android components-- can't decouple for testing.");
    }

    @Test
    public void testCanonicalizeNumberOrGroup() throws Exception {
        fail("Method depends heavily on Android components-- can't decouple for testing.");
    }

    @Test
    public void testReadFully() throws Exception {
        String str = "abcdefghijklmno";
        ByteArrayInputStream stream = new ByteArrayInputStream(str.getBytes());
        assertArrayEquals(str.getBytes(), Util.readFully(stream));
    }

    @Test
    public void testReadFullyAsString() throws Exception {
        String str = "abcdefghijklmno";
        ByteArrayInputStream stream = new ByteArrayInputStream(str.getBytes());
        assertEquals(str, Util.readFullyAsString(stream));
    }

    @Test
    public void testCopy() throws Exception {
        String str = "abcdefghijklmno";
        ByteArrayInputStream stream = new ByteArrayInputStream(str.getBytes());
        ByteArrayOutputStream oStream = new ByteArrayOutputStream();
        assertEquals(str.length(), Util.copy(stream, oStream));
        assertEquals(str, oStream.toString());
    }

    @Test
    public void testGetDeviceE164Number() throws Exception {
        fail("Method depends heavily on Android components-- can't decouple for testing.");
    }

    @Test
    public void testSplit() throws Exception {
        List<String> list, actual = new ArrayList<String>();
        actual.add("123");
        actual.add("345");
        actual.add("\n");
        actual.add("678,");
        actual.add("910\t1");
        list = Util.split("123 345 \n 678, 910\t1", " ");
        assertEquals(actual, list);

        actual.clear();
        actual.add("123 345 ");
        actual.add(" 678, 910\t1");
        list = Util.split("123 345 \n 678, 910\t1", "\n");
        assertEquals(actual, list);

        actual.clear();
        actual.add("123 345");
        actual.add("678, 910\t1");
        list = Util.split("123 345 \n 678, 910\t1", " \n ");
        assertEquals(actual, list);


    }

    @Test
    public void testSplit1() throws Exception {
        //@TODO
    }

    @Test
    public void testCombine() throws Exception {
        String str1 = "123";
        String str2 = "abc";
        String result = "123abc";
        assertArrayEquals(result.getBytes(), Util.combine(str1.getBytes(), str2.getBytes()));
    }

    @Test
    public void testTrim() throws Exception {
        assertArrayEquals(" a".getBytes(), Util.trim(" asd ".getBytes(),2));
        assertArrayEquals(" ".getBytes(), Util.trim(" asd ".getBytes(),1));
        assertArrayEquals("".getBytes(), Util.trim(" asd ".getBytes(),0));
        assertArrayEquals(" asd ".getBytes(), Util.trim(" asd ".getBytes(),5));
        try {
            assertArrayEquals(" asd ".getBytes(), Util.trim(" asd ".getBytes(), 6));
            fail("IndexOutOfBoundsException not found when it should have been found.");
        } catch ( IndexOutOfBoundsException e) {

        }
    }

    @Test
    public void testIsDefaultSmsProvider() throws Exception {
        fail("Method depends heavily on Android components-- can't decouple for testing.");
    }

    @Test
    public void testGetCurrentApkReleaseVersion() throws Exception {
        fail("Method depends heavily on Android components-- can't decouple for testing.");
    }

    @Test
    public void testGetSecret() throws Exception {
        //Not sure how to test this, since get secret appears to return random data
    }

    @Test
    public void testGetSecretBytes() throws Exception {
        //Not sure how to test this, since get secret appears to return random data
    }

    @Test
    public void testGetSecureRandom() throws Exception {
        //Not sure how to test this, since this just returns a PRNG
    }

    @Test
    public void testIsBuildFresh() throws Exception {
        fail("Method depends heavily on Android components-- can't decouple for testing.");
    }
}