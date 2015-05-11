package org.thoughtcrime.securesms.util;

import org.junit.Test;
import ws.com.google.android.mms.pdu.PduBody;
import static org.junit.Assert.*;

public class SmilUtilTest {
	
	@Test
	public void initialConditions() {
		final PduBody collection = new PduBody();
		assertEquals(0, collection.getPartsNum());
	}
	
	@Test
	public void getSmilBody_getPartsNum() {
		final PduBody collection = new PduBody();
		SmilUtil.getSmilBody(collection);
		
		assertEquals(1, collection.getPartsNum());
	}
	
	@Test
	public void getSmilBody_getById() {
		final PduBody collection = new PduBody();
		SmilUtil.getSmilBody(collection);
		
		assertNotNull(collection.getPartByContentId("smil"));
	}
	
	@Test
	public void getSmilBody_getByLocation() {
		final PduBody collection = new PduBody();
		SmilUtil.getSmilBody(collection);
		
		assertNotNull(collection.getPartByContentLocation("smil.xml"));
	}
	
	@Test
	public void getSmilBody_retVal() {
		final PduBody c1 = new PduBody();
		final PduBody c2 = SmilUtil.getSmilBody(c1);
		
		assertEquals(c1, c2);
	}
}
