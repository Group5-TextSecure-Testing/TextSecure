package org.thoughtcrime.securesms.util;

import java.io.IOException;
import static java.nio.charset.StandardCharsets.US_ASCII;
import android.content.Context;
import android.test.InstrumentationTestCase;
import android.test.AndroidTestCase;

import org.junit.Test;

import static org.junit.Assert.*;

public final class LRUCacheTest {
	
	@Test
	public void add_0_max5() throws Exception {
		final LRUCache<String, String> dut = new LRUCache<String, String>(5);
		assertEquals(0, dut.size());
		assertFalse(dut.containsKey("AGGGLEFE"));
	}
	
	@Test
	public void add_1_max5() throws Exception {
		final LRUCache<String, String> dut = new LRUCache<String, String>(5);
		dut.put("One", "First");
		assertEquals(1, dut.size());
		assertEquals("First", dut.get("One"));
		assertFalse(dut.containsKey("AGGGLEFE"));
	}
	
	@Test
	public void add_5_max5() throws Exception {
		final LRUCache<String, String> dut = new LRUCache<String, String>(5);
		dut.put("One", "1");
		dut.put("Two", "2");
		dut.put("Three", "3");
		dut.put("Four", "4");
		dut.put("Five", "5");
		assertEquals(5, dut.size());
		assertTrue(dut.containsKey("One"));
		assertTrue(dut.containsKey("Two"));
		assertTrue(dut.containsKey("Three"));
		assertTrue(dut.containsKey("Four"));
		assertTrue(dut.containsKey("Five"));
		assertFalse(dut.containsKey("AGGGLEFE"));
	}
	
	@Test
	public void add_6_max5() throws Exception {
		final LRUCache<String, String> dut = new LRUCache<String, String>(5);
		dut.put("One", "1");
		dut.put("Two", "2");
		dut.put("Three", "3");
		dut.put("Four", "4");
		dut.put("Five", "5");
		dut.put("Six", "6");
		assertEquals(5, dut.size());
		assertFalse(dut.containsKey("One"));
		assertTrue(dut.containsKey("Two"));
		assertTrue(dut.containsKey("Three"));
		assertTrue(dut.containsKey("Four"));
		assertTrue(dut.containsKey("Five"));
		assertTrue(dut.containsKey("Six"));
		assertFalse(dut.containsKey("AGGGLEFE"));
	}
	
	
	
	
	
	@Test
	public void add_0_max1() throws Exception {
		final LRUCache<String, String> dut = new LRUCache<String, String>(1);
		assertEquals(0, dut.size());
		assertFalse(dut.containsKey("AGGGLEFE"));
	}
	
	@Test
	public void add_1_max1() throws Exception {
		final LRUCache<String, String> dut = new LRUCache<String, String>(1);
		dut.put("000", "111");
		assertEquals(1, dut.size());
		assertEquals("111", dut.get("000"));
		assertFalse(dut.containsKey("AGGGLEFE"));
	}
	
	@Test
	public void add_2_max1() throws Exception {
		final LRUCache<String, String> dut = new LRUCache<String, String>(1);
		dut.put("A", "A");
		dut.put("B", "B");
		assertEquals(1, dut.size());
		assertFalse(dut.containsKey("A"));
		assertTrue(dut.containsKey("B"));
		assertEquals("B", dut.get("B"));
		assertFalse(dut.containsKey("AGGGLEFE"));
	}
	
	
	
	@Test
	public void add_0_max0() throws Exception {
		final LRUCache<String, String> dut = new LRUCache<String, String>(0);
		assertEquals(0, dut.size());
	}
	
	@Test
	public void add_1_max0() throws Exception {
		final LRUCache<String, String> dut = new LRUCache<String, String>(0);
		dut.put("000", "111");
		assertEquals(0, dut.size());
		assertFalse(dut.containsKey("000"));
		assertFalse(dut.containsKey("111"));
	}
}
