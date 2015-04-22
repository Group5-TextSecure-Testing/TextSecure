package org.thoughtcrime.securesms.util;

import org.thoughtcrime.securesms.util.CharacterCalculator.CharacterState;
import org.junit.Test;
import static org.junit.Assert.*;

public final class EncryptedSmsCharacterCalculatorTest {
	public static final int SINGLE_DATA_SIZE = 63; //SmsTransportDetails.ENCRYPTED_SINGLE_MESSAGE_BODY_MAX_SIZE;
	public static final int MULTIPART_DATA_SIZE = 114; //SmsTransportDetails.MULTI_MESSAGE_MAX_BYTES;
	
	
	@Test
	public void calculateCharacters_neg1() {
		final EncryptedSmsCharacterCalculator dut = new EncryptedSmsCharacterCalculator();
		
		try {
			final CharacterState result = dut.calculateCharacters(-1);
			fail("Negative message size");
		} catch (IllegalArgumentException e) {
			// success
		}
	}
	
	@Test
	public void calculateCharacters_0() {
		final EncryptedSmsCharacterCalculator dut = new EncryptedSmsCharacterCalculator();
		
		final CharacterState result = dut.calculateCharacters(0);
		assertEquals(1, result.messagesSpent);
		assertEquals(SINGLE_DATA_SIZE, result.charactersRemaining);
		assertEquals(SINGLE_DATA_SIZE, result.maxMessageSize);
	}
	
	@Test
	public void calculateCharacters_1() {
		final EncryptedSmsCharacterCalculator dut = new EncryptedSmsCharacterCalculator();
		
		final CharacterState result = dut.calculateCharacters(1);
		assertEquals(1, result.messagesSpent);
		assertEquals(SINGLE_DATA_SIZE - 1, result.charactersRemaining);
		assertEquals(SINGLE_DATA_SIZE, result.maxMessageSize);
	}
	
	@Test
	public void calculateCharacters_34() {
		final EncryptedSmsCharacterCalculator dut = new EncryptedSmsCharacterCalculator();
		
		final CharacterState result = dut.calculateCharacters(34);
		assertEquals(1, result.messagesSpent);
		assertEquals(SINGLE_DATA_SIZE - 34, result.charactersRemaining);
		assertEquals(SINGLE_DATA_SIZE, result.maxMessageSize);
	}
	
	@Test
	public void calculateCharacters_62() {
		final EncryptedSmsCharacterCalculator dut = new EncryptedSmsCharacterCalculator();
		
		final CharacterState result = dut.calculateCharacters(SINGLE_DATA_SIZE - 1);
		assertEquals(1, result.messagesSpent);
		assertEquals(1, result.charactersRemaining);
		assertEquals(SINGLE_DATA_SIZE, result.maxMessageSize);
	}
	
	@Test
	public void calculateCharacters_63() {
		final EncryptedSmsCharacterCalculator dut = new EncryptedSmsCharacterCalculator();
		
		final CharacterState result = dut.calculateCharacters(SINGLE_DATA_SIZE);
		assertEquals(1, result.messagesSpent);
		assertEquals(0, result.charactersRemaining);
		assertEquals(SINGLE_DATA_SIZE, result.maxMessageSize);
	}
	
	@Test
	public void calculateCharacters_64() {
		final EncryptedSmsCharacterCalculator dut = new EncryptedSmsCharacterCalculator();
		
		final CharacterState result = dut.calculateCharacters(SINGLE_DATA_SIZE + 1);
		assertEquals(2, result.messagesSpent);
		assertEquals(MULTIPART_DATA_SIZE - 1, result.charactersRemaining);
		assertEquals(MULTIPART_DATA_SIZE, result.maxMessageSize);
	}
	
	@Test
	public void calculateCharacters_176() {
		final EncryptedSmsCharacterCalculator dut = new EncryptedSmsCharacterCalculator();
		
		final CharacterState result = dut.calculateCharacters(SINGLE_DATA_SIZE + MULTIPART_DATA_SIZE - 1);
		assertEquals(2, result.messagesSpent);
		assertEquals(1, result.charactersRemaining);
		assertEquals(MULTIPART_DATA_SIZE, result.maxMessageSize);
	}
	
	@Test
	public void calculateCharacters_177() {
		final EncryptedSmsCharacterCalculator dut = new EncryptedSmsCharacterCalculator();
		
		final CharacterState result = dut.calculateCharacters(SINGLE_DATA_SIZE + MULTIPART_DATA_SIZE);
		assertEquals(2, result.messagesSpent);
		assertEquals(0, result.charactersRemaining);
		assertEquals(MULTIPART_DATA_SIZE, result.maxMessageSize);
	}
	
	@Test
	public void calculateCharacters_178() {
		final EncryptedSmsCharacterCalculator dut = new EncryptedSmsCharacterCalculator();
		
		final CharacterState result = dut.calculateCharacters(SINGLE_DATA_SIZE + MULTIPART_DATA_SIZE + 1);
		assertEquals(3, result.messagesSpent);
		assertEquals(MULTIPART_DATA_SIZE - 1, result.charactersRemaining);
		assertEquals(MULTIPART_DATA_SIZE, result.maxMessageSize);
	}
	
	@Test
	public void calculateCharacters_500() {
		final EncryptedSmsCharacterCalculator dut = new EncryptedSmsCharacterCalculator();
		
		final CharacterState result = dut.calculateCharacters(500);
		assertEquals(5, result.messagesSpent);
		assertEquals(19, result.charactersRemaining);
		assertEquals(MULTIPART_DATA_SIZE, result.maxMessageSize);
	}
}
