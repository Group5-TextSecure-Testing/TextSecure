package org.thoughtcrime.securesms.util;

import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.*;

public class HexTest {

    @Test
    public void testToString() throws Exception {
        byte[] bytes = new byte[1];
        bytes[0] = 0x4A;
        assertEquals("4a", Hex.toString(bytes));
    }

    @Test
    public void testToString1() throws Exception {
        byte[] bytes = new byte[2];
        bytes[0] = 0x4A;
        bytes[1] = (byte)0xFF;
        assertEquals("4a ff", Hex.toString(bytes));
    }

    @Test
    public void testToString2() throws Exception {
        byte[] bytes = new byte[0];
        assertEquals("", Hex.toString(bytes));
    }

    @Test
    public void testToStringCondensed() throws Exception {
        byte[] bytes = new byte[1];
        bytes[0] = 0x4A;
        assertEquals("4a", Hex.toStringCondensed(bytes));
    }

    @Test
    public void testToStringCondensed1() throws Exception {
        byte[] bytes = new byte[2];
        bytes[0] = 0x4A;
        bytes[1] = (byte)0xFF;
        assertEquals("4aff", Hex.toStringCondensed(bytes));
    }

    @Test
    public void testToStringCondensed2() throws Exception {
        byte[] bytes = new byte[0];
        assertEquals("", Hex.toStringCondensed(bytes));
    }

    @Test(expected=IOException.class)
    public void testFromStringCondensed() throws Exception {
        Hex.fromStringCondensed("a");
    }

    @Test
    public void testFromStringCondensed1() throws Exception {
        assertArrayEquals(new byte[0], Hex.fromStringCondensed(""));
    }

    @Test
    public void testFromStringCondensed2() throws Exception {
        byte[] bytes = new byte[1];
        bytes[0] = 0x12;
        assertArrayEquals(bytes, Hex.fromStringCondensed("12"));
    }

    @Test
    public void testFromStringCondensed3() throws Exception {
        byte[] bytes = new byte[3];
        bytes[0] = 0x12;
        bytes[1] = 0x34;
        bytes[2] = (byte)0xFF;
        assertArrayEquals(bytes, Hex.fromStringCondensed("1234FF"));
        assertArrayEquals(bytes, Hex.fromStringCondensed("1234ff"));
    }

    @Test
    public void testDump() throws Exception {

    }

    @Test
    public void testDump1() throws Exception {

    }
}