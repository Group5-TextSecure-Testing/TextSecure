package org.thoughtcrime.securesms.util;

import java.io.IOException;
import static java.nio.charset.StandardCharsets.US_ASCII;
import android.content.Context;
import android.test.InstrumentationTestCase;
import android.test.AndroidTestCase;

public final class ConversionsTest extends InstrumentationTestCase {
	
/***** lowBitsToInt */
	
	public void lowBitsToInt_0() throws Exception {
        assertEquals(0, Conversions.lowBitsToInt((byte) 0));
    }
	public void lowBitsToInt_5() throws Exception {
        assertEquals(5, Conversions.lowBitsToInt((byte) 5));
    }
	public void lowBitsToInt_15() throws Exception {
        assertEquals(15, Conversions.lowBitsToInt((byte) 15));
    }
	public void lowBitsToInt_16() throws Exception {
        assertEquals(0, Conversions.lowBitsToInt((byte) 16));
    }
	public void lowBitsToInt_56() throws Exception {
        assertEquals(8, Conversions.lowBitsToInt((byte) 56));
    }
	public void lowBitsToInt_255() throws Exception {
        assertEquals(15, Conversions.lowBitsToInt((byte) 255));
    }
    
/***** highBitsToInt */
	
	public void highBitsToInt_0() throws Exception {
        assertEquals(0, Conversions.highBitsToInt((byte) 0));
    }
	public void highBitsToInt_15() throws Exception {
        assertEquals(0, Conversions.highBitsToInt((byte) 15));
    }
	public void highBitsToInt_16() throws Exception {
        assertEquals(1, Conversions.highBitsToInt((byte) 16));
    }
	public void highBitsToInt_56() throws Exception {
        assertEquals(3, Conversions.highBitsToInt((byte) 56));
    }
	public void highBitsToInt_255() throws Exception {
        assertEquals(15, Conversions.highBitsToInt((byte) 255));
    }
    
/***** lowBitsToMedium */
	
	public void lowBitsToMedium_0() throws Exception {
        assertEquals(0, Conversions.lowBitsToMedium(0));
    }
	public void lowBitsToMedium_255() throws Exception {
        assertEquals(0, Conversions.lowBitsToMedium(255));
    }
	public void lowBitsToMedium_256() throws Exception {
        assertEquals(1, Conversions.lowBitsToMedium(256));
    }
	public void lowBitsToMedium_xFFF() throws Exception {
        assertEquals(15, Conversions.lowBitsToMedium(0xFFF));
    }
	public void lowBitsToMedium_x417() throws Exception {
        assertEquals(4, Conversions.lowBitsToMedium(0x417));
    }
	public void lowBitsToMedium_x1000() throws Exception {
        assertEquals(0, Conversions.lowBitsToMedium(0x1000));
    }
	public void lowBitsToMedium_x1A53() throws Exception {
        assertEquals(0xA, Conversions.lowBitsToMedium(0x1A53));
    }
	public void lowBitsToMedium_neg1() throws Exception {
        assertEquals(15, Conversions.lowBitsToMedium(-1));
    }
	
/***** intsToByteHighAndLow */
	
	public void intsToByteHighAndLow_0_0() throws Exception {
		assertEquals(0, Conversions.intsToByteHighAndLow(0,0));
	}
	public void intsToByteHighAndLow_0_1() throws Exception {
		assertEquals(1, Conversions.intsToByteHighAndLow(0,1));
	}
	public void intsToByteHighAndLow_1_0() throws Exception {
		assertEquals(16, Conversions.intsToByteHighAndLow(1,0));
	}
	public void intsToByteHighAndLow_5_7() throws Exception {
		assertEquals(0x57, Conversions.intsToByteHighAndLow(5,7));
	}
	public void intsToByteHighAndLow_F_F() throws Exception {
		assertEquals(0xFF, Conversions.intsToByteHighAndLow(15,15));
	}
	public void intsToByteHighAndLow_x10_0() throws Exception {
		assertEquals(0x00, Conversions.intsToByteHighAndLow(16,0));
	}
	public void intsToByteHighAndLow_0_x10() throws Exception {
		assertEquals(0x00, Conversions.intsToByteHighAndLow(0,16));
	}
	public void intsToByteHighAndLow_neg1_neg1() throws Exception {
		assertEquals(0xFF, Conversions.intsToByteHighAndLow(-1,-1));
	}
	
	
/***** shortToByteArray */
	
	public void shortToByteArray_0() throws Exception {
		final byte[] expected = {(byte) 0, (byte) 0};
		assertEquals(expected, Conversions.shortToByteArray((short) 0));
	}
	public void shortToByteArray_255() throws Exception {
		final byte[] expected = {(byte) 0, (byte) 255};
		assertEquals(expected, Conversions.shortToByteArray((short) 255));
	}
	public void shortToByteArray_256() throws Exception {
		final byte[] expected = {(byte) 1, (byte) 0};
		assertEquals(expected, Conversions.shortToByteArray((short) 256));
	}
	public void shortToByteArray_xFFFF() throws Exception {
		final byte[] expected = {(byte) 255, (byte) 255};
		assertEquals(expected, Conversions.shortToByteArray((short) 0xFFFF));
	}
	public void shortToByteArray_x4567() throws Exception {
		final byte[] expected = {(byte) 0x45, (byte) 0x67};
		assertEquals(expected, Conversions.shortToByteArray((short) 0x4567));
	}
	public void shortToByteArray_x10000() throws Exception {
		// NOTE: the fact that this compiles at all is a bug
		final byte[] expected = {(byte) 0x00, (byte) 0x00};
		assertEquals(expected, Conversions.shortToByteArray(0x10000));
	}
	
	public void shortToLittleEndianByteArray_x4567() throws Exception {
		final byte[] expected = {(byte) 0x67, (byte) 0x45};
		final byte[] bytes = new byte[2];
		assertEquals(expected, Conversions.shortToLittleEndianByteArray(bytes, 0, (short) 0x4567));
	}
	
	
/***** shortToByteArray 2 */
	
	public void shortToByteArray_off0() throws Exception {
		final byte[] bytes = new byte[5];
		final byte[] expected = {(byte) 0x45, (byte) 0x67, (byte) 0, (byte) 0, (byte) 0};
		Conversions.shortToByteArray(bytes, 0, (short) 0x4567);
		assertEquals(expected, bytes);
	}
	public void shortToByteArray_off1() throws Exception {
		final byte[] bytes = new byte[5];
		final byte[] expected = {(byte) 0, (byte) 0x45, (byte) 0x67, (byte) 0, (byte) 0};
		Conversions.shortToByteArray(bytes, 1, (short) 0x4567);
		assertEquals(expected, bytes);
	}
	public void shortToByteArray_offMax() throws Exception {
		final byte[] bytes = new byte[5];
		final byte[] expected = {(byte) 0, (byte) 0, (byte) 0, (byte) 0x45, (byte) 0x67};
		Conversions.shortToByteArray(bytes, 3, (short) 0x4567);
		assertEquals(expected, bytes);
	}
	public void shortToByteArray_offOver() throws Exception {
		final byte[] bytes = new byte[5];
		try {
			Conversions.shortToByteArray(bytes, 4, (short) 0x4567);
			fail("IndexOutOfBoundsException exception to be thrown");
		} catch (IndexOutOfBoundsException e) {
			// success
		}
	}
	public void shortToByteArray_offUnder() throws Exception {
		final byte[] bytes = new byte[5];
		try {
			Conversions.shortToByteArray(bytes, -1, (short) 0x4567);
			fail("IndexOutOfBoundsException exception to be thrown");
		} catch (IndexOutOfBoundsException e) {
			// success
		}
	}
	public void shortToByteArray_smallArray() throws Exception {
		final byte[] bytes = new byte[1];
		try {
			Conversions.shortToByteArray(bytes, 0, (short) 0x4567);
			fail("IndexOutOfBoundsException exception to be thrown");
		} catch (IndexOutOfBoundsException e) {
			// success
		}
	}
	
/***** intToByteArray */
	
	public void intToByteArray_0() throws Exception {
		final byte[] expected = {(byte) 0,(byte) 0,(byte) 0,(byte) 0};
		assertEquals(expected, Conversions.intToByteArray(0));
	}
	public void intToByteArray_max() throws Exception {
		final byte[] expected = {(byte) 255,(byte) 255,(byte) 255,(byte) 255};
		assertEquals(expected, Conversions.intToByteArray(-1));
	}
	public void intToByteArray_mid() throws Exception {
		final byte[] expected = {(byte) 0x12, (byte) 0x34, (byte) 0x56,(byte) 0x78};
		assertEquals(expected, Conversions.intToByteArray(0x12345678));
	}
	public void intToLittleEndianByteArray_mid() throws Exception {
		final byte[] expected = {(byte) 0x78, (byte) 0x56, (byte) 0x34,(byte) 0x12};
		assertEquals(expected, Conversions.intToByteArray(0x12345678));
	}
	
	public void intToByteArray_off0() throws Exception {
		final byte[] bytes = new byte[10];
		final byte[] expected = {(byte) 0x12, (byte) 0x34, (byte) 0x56, (byte) 0x78, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0};
		Conversions.intToByteArray(bytes, 0, 0x12345678);
		assertEquals(expected, bytes);
	}
	public void intToByteArray_off3() throws Exception {
		final byte[] bytes = new byte[10];
		final byte[] expected = {(byte) 0, (byte) 0, (byte) 0, (byte) 0x12, (byte) 0x34, (byte) 0x56, (byte) 0x78, (byte) 0, (byte) 0, (byte) 0};
		Conversions.intToByteArray(bytes, 3, 0x12345678);
		assertEquals(expected, bytes);
	}
	public void intToByteArray_offMax() throws Exception {
		final byte[] bytes = new byte[10];
		final byte[] expected = {(byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0x12, (byte) 0x34, (byte) 0x56, (byte) 0x78};
		Conversions.intToByteArray(bytes, 6, 0x12345678);
		assertEquals(expected, bytes);
	}
	public void intToByteArray_offOver() throws Exception {
		final byte[] bytes = new byte[10];
		try {
			Conversions.intToByteArray(bytes, 7, 0x12345678);
			fail("IndexOutOfBoundsException exception to be thrown");
		} catch (IndexOutOfBoundsException e) {
			// success
		}
	}
	public void intToByteArray_offUnder() throws Exception {
		final byte[] bytes = new byte[10];
		try {
			Conversions.intToByteArray(bytes, -1, 0x12345678);
			fail("IndexOutOfBoundsException exception to be thrown");
		} catch (IndexOutOfBoundsException e) {
			// success
		}
	}
	public void intToByteArray_smallArray() throws Exception {
		final byte[] bytes = new byte[3];
		try {
			Conversions.intToByteArray(bytes, 0, (short) 0x12345678);
			fail("IndexOutOfBoundsException exception to be thrown");
		} catch (IndexOutOfBoundsException e) {
			// success
		}
	}
	
	
	
/***** longToByteArray */
	
	public void longToByteArray_0() throws Exception {
		final byte[] expected = {(byte) 0,(byte) 0,(byte) 0,(byte) 0,(byte) 0,(byte) 0,(byte) 0,(byte) 0};
		assertEquals(expected, Conversions.longToByteArray(0));
	}
	public void longToByteArray_max() throws Exception {
		final byte[] expected = {(byte) 255,(byte) 255,(byte) 255,(byte) 255,(byte) 255,(byte) 255,(byte) 255,(byte) 255};
		assertEquals(expected, Conversions.longToByteArray(-1l));
	}
	public void longToByteArray_mid() throws Exception {
		final byte[] expected = {(byte) 0x12, (byte) 0x34, (byte) 0x56,(byte) 0x78,(byte) 0x90,(byte) 0xAB,(byte) 0xCD,(byte) 0xEF};
		assertEquals(expected, Conversions.longToByteArray(0x1234567890ABCDEFl));
	}
	public void longToLittleEndianByteArray_mid() throws Exception {
		final byte[] expected = {(byte) 0xEF, (byte) 0xCD, (byte) 0xAB, (byte) 0x90, 78, (byte) 0x56, (byte) 0x34,(byte) 0x12};
		assertEquals(expected, Conversions.longToByteArray(0x1234567890ABCDEFl));
	}
	
	public void longToByteArray_off0() throws Exception {
		final byte[] bytes = new byte[16];
		final byte[] expected = {(byte) 0x12, (byte) 0x34, (byte) 0x56,(byte) 0x78,(byte) 0x90,(byte) 0xAB,(byte) 0xCD,(byte) 0xEF, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0};
		Conversions.longToByteArray(bytes, 0, 0x1234567890ABCDEFl);
		assertEquals(expected, bytes);
	}
	public void longToByteArray_off3() throws Exception {
		final byte[] bytes = new byte[16];
		final byte[] expected = { (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0,(byte) 0x12, (byte) 0x34, (byte) 0x56,(byte) 0x78,(byte) 0x90,(byte) 0xAB,(byte) 0xCD,(byte) 0xEF};
		Conversions.longToByteArray(bytes, 8, 0x1234567890ABCDEFl);
		assertEquals(expected, bytes);
	}
	public void longToByteArray_offMax() throws Exception {
		final byte[] bytes = new byte[16];
		final byte[] expected = {(byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0x12, (byte) 0x34, (byte) 0x56, (byte) 0x78,(byte) 0x90,(byte) 0xAB,(byte) 0xCD,(byte) 0xEF, (byte)0, (byte)0};
		Conversions.longToByteArray(bytes, 6, 0x1234567890ABCDEFl);
		assertEquals(expected, bytes);
	}
	public void longToByteArray_offOver() throws Exception {
		final byte[] bytes = new byte[16];
		try {
			Conversions.longToByteArray(bytes, 9, 0x1234567890ABCDEFl);
			fail("IndexOutOfBoundsException exception to be thrown");
		} catch (IndexOutOfBoundsException e) {
			// success
		}
	}
	public void longToByteArray_offUnder() throws Exception {
		final byte[] bytes = new byte[16];
		try {
			Conversions.longToByteArray(bytes, -1, 0x1234567890ABCDEFl);
			fail("IndexOutOfBoundsException exception to be thrown");
		} catch (IndexOutOfBoundsException e) {
			// success
		}
	}
	public void longToByteArray_smallArray() throws Exception {
		final byte[] bytes = new byte[7];
		try {
			Conversions.longToByteArray(bytes, 0, (short) 0x1234567890ABCDEFl);
			fail("IndexOutOfBoundsException exception to be thrown");
		} catch (IndexOutOfBoundsException e) {
			// success
		}
	}
	
/***** byteArrayToShort */
	
	public void byteArrayToShort_0() throws Exception {
		final byte[] inputs = {(byte) 0,(byte) 0};
		assertEquals(0, Conversions.byteArrayToShort(inputs));
	}
	public void byteArrayToShort_max() throws Exception {
		final byte[] inputs = {(byte) 255,(byte) 255};
		assertEquals(0xFFFF, Conversions.byteArrayToShort(inputs));
	}
	public void byteArrayToShort_mid() throws Exception {
		final byte[] inputs = {(byte) 0x12, (byte) 0x34};
		assertEquals(0x1234, Conversions.byteArrayToShort(inputs));
	}
	public void shortToLittleEndianByteArray_mid() throws Exception {
		final byte[] inputs = {(byte) 0x34,(byte) 0x12};
		assertEquals(0x1234, Conversions.byteArrayToShort(inputs));
	}
	
	public void byteArrayToShort_off0() throws Exception {
		final byte[] inputs = {(byte) 0x12, (byte) 0, (byte) 0,(byte) 0x78,(byte) 0,(byte) 0,(byte) 0,(byte) 0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0};
		assertEquals(0x1234, Conversions.byteArrayToShort(inputs, 0));
	}
	public void byteArrayToShort_off3() throws Exception {
		final byte[] inputs = { (byte)0,(byte) 0,(byte) 0,(byte) 0,(byte) 0,(byte) 0,(byte) 0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0,(byte) 0x12, (byte) 0x34};
		assertEquals(0x1234, Conversions.byteArrayToShort(inputs, 12));
	}
	public void byteArrayToShort_offMax() throws Exception {
		final byte[] inputs = {(byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0x12, (byte) 0x34, (byte) 0x56, (byte) 0x78,(byte) 0,(byte) 0,(byte) 0,(byte) 0, (byte)0, (byte)0};
		assertEquals(0x1234, Conversions.byteArrayToShort(inputs, 6));
	}
	public void byteArrayToShort_offOver() throws Exception {
		final byte[] inputs = new byte[16];
		try {
			Conversions.byteArrayToShort(inputs, 15);
			fail("IndexOutOfBoundsException exception to be thrown");
		} catch (IndexOutOfBoundsException e) {
			// success
		}
	}
	public void byteArrayToShort_offUnder() throws Exception {
		final byte[] inputs = new byte[16];
		try {
			Conversions.byteArrayToShort(inputs, -1);
			fail("IndexOutOfBoundsException exception to be thrown");
		} catch (IndexOutOfBoundsException e) {
			// success
		}
	}
	public void byteArrayToShort_smallArray() throws Exception {
		final byte[] bytes = new byte[1];
		try {
			Conversions.byteArrayToShort(bytes, 0);
			fail("IndexOutOfBoundsException exception to be thrown");
		} catch (IndexOutOfBoundsException e) {
			// success
		}
	}
	
/***** byteArrayToInt */
	
	public void byteArrayToInt_0() throws Exception {
		final byte[] inputs = {(byte) 0,(byte) 0,(byte) 0,(byte) 0};
		assertEquals(0, Conversions.byteArrayToInt(inputs));
	}
	public void byteArrayToInt_max() throws Exception {
		final byte[] inputs = {(byte) 255,(byte) 255,(byte) 255,(byte) 255,};
		assertEquals(-1, Conversions.byteArrayToInt(inputs));
	}
	public void byteArrayToInt_mid() throws Exception {
		final byte[] inputs = {(byte) 0x12, (byte) 0x34, (byte) 0x56,(byte) 0x78};
		assertEquals(0x12345678, Conversions.byteArrayToInt(inputs));
	}
	
	public void byteArrayToInt_off0() throws Exception {
		final byte[] inputs = {(byte) 0x12, (byte) 0x34, (byte) 0x56,(byte) 0x78,(byte) 0,(byte) 0,(byte) 0,(byte) 0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0};
		assertEquals(0x12345678, Conversions.byteArrayToInt(inputs, 0));
	}
	public void byteArrayToInt_off3() throws Exception {
		final byte[] inputs = { (byte)0,(byte) 0,(byte) 0,(byte) 0,(byte) 0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0,(byte) 0x12, (byte) 0x34, (byte) 0x56,(byte) 0x78};
		assertEquals(0x12345678, Conversions.byteArrayToInt(inputs, 12));
	}
	public void byteArrayToInt_offMax() throws Exception {
		final byte[] inputs = {(byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0x12, (byte) 0x34, (byte) 0x56, (byte) 0x78,(byte) 0,(byte) 0,(byte) 0,(byte) 0, (byte)0, (byte)0};
		assertEquals(0x12345678, Conversions.byteArrayToInt(inputs, 6));
	}
	public void byteArrayToInt_offOver() throws Exception {
		final byte[] inputs = new byte[16];
		try {
			Conversions.byteArrayToInt(inputs, 13);
			fail("IndexOutOfBoundsException exception to be thrown");
		} catch (IndexOutOfBoundsException e) {
			// success
		}
	}
	public void byteArrayToInt_offUnder() throws Exception {
		final byte[] inputs = new byte[16];
		try {
			Conversions.byteArrayToInt(inputs, -1);
			fail("IndexOutOfBoundsException exception to be thrown");
		} catch (IndexOutOfBoundsException e) {
			// success
		}
	}
	public void byteArrayToInt_smallArray() throws Exception {
		final byte[] bytes = new byte[3];
		try {
			Conversions.byteArrayToInt(bytes, 0);
			fail("IndexOutOfBoundsException exception to be thrown");
		} catch (IndexOutOfBoundsException e) {
			// success
		}
	}
	
/***** byteArrayToLong */
	
	public void byteArrayToLong_0() throws Exception {
		final byte[] inputs = {(byte) 0,(byte) 0,(byte) 0,(byte) 0,(byte) 0,(byte) 0,(byte) 0,(byte) 0};
		assertEquals(0, Conversions.byteArrayToLong(inputs));
	}
	public void byteArrayToLong_max() throws Exception {
		final byte[] inputs = {(byte) 255,(byte) 255,(byte) 255,(byte) 255,(byte) 255,(byte) 255,(byte) 255,(byte) 255};
		assertEquals(-1l, Conversions.byteArrayToLong(inputs));
	}
	public void byteArrayToLong_mid() throws Exception {
		final byte[] inputs = {(byte) 0x12, (byte) 0x34, (byte) 0x56,(byte) 0x78,(byte) 0x90,(byte) 0xAB,(byte) 0xCD,(byte) 0xEF};
		assertEquals(0x1234567890ABCDEFl, Conversions.byteArrayToLong(inputs));
	}
	
	public void byteArrayToLong_off0() throws Exception {
		final byte[] inputs = {(byte) 0x12, (byte) 0x34, (byte) 0x56,(byte) 0x78,(byte) 0x90,(byte) 0xAB,(byte) 0xCD,(byte) 0xEF, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0};
		assertEquals(0x1234567890ABCDEFl, Conversions.byteArrayToLong(inputs, 0));
	}
	public void byteArrayToLong_off3() throws Exception {
		final byte[] inputs = { (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0, (byte)0,(byte) 0x12, (byte) 0x34, (byte) 0x56,(byte) 0x78,(byte) 0x90,(byte) 0xAB,(byte) 0xCD,(byte) 0xEF};
		assertEquals(0x1234567890ABCDEFl, Conversions.byteArrayToLong(inputs, 8));
	}
	public void byteArrayToLong_offMax() throws Exception {
		final byte[] inputs = {(byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0, (byte) 0x12, (byte) 0x34, (byte) 0x56, (byte) 0x78,(byte) 0x90,(byte) 0xAB,(byte) 0xCD,(byte) 0xEF, (byte)0, (byte)0};
		assertEquals(0x1234567890ABCDEFl, Conversions.byteArrayToLong(inputs, 6));
	}
	public void byteArrayToLong_offOver() throws Exception {
		final byte[] inputs = new byte[16];
		try {
			Conversions.byteArrayToLong(inputs, 9);
			fail("IndexOutOfBoundsException exception to be thrown");
		} catch (IndexOutOfBoundsException e) {
			// success
		}
	}
	public void byteArrayToLong_offUnder() throws Exception {
		final byte[] inputs = new byte[16];
		try {
			Conversions.byteArrayToLong(inputs, -1);
			fail("IndexOutOfBoundsException exception to be thrown");
		} catch (IndexOutOfBoundsException e) {
			// success
		}
	}
	public void byteArrayToLong_smallArray() throws Exception {
		final byte[] bytes = new byte[7];
		try {
			Conversions.byteArrayToLong(bytes, 0);
			fail("IndexOutOfBoundsException exception to be thrown");
		} catch (IndexOutOfBoundsException e) {
			// success
		}
	}
	
	
}
