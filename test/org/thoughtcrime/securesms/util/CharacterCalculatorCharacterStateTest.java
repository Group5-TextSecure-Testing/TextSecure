package org.thoughtcrime.securesms.util;

import org.thoughtcrime.securesms.util.CharacterCalculator.CharacterState;
import org.junit.Test;
import static org.junit.Assert.*;

public final class CharacterCalculatorCharacterStateTest {
	
	
	@Test
	public void constructorTest() {
		MockCharacterCalculator container = new MockCharacterCalculator();
		CharacterState dut = container.new CharacterState(15, 35, 50);
		assertEquals(15, dut.messagesSpent);
		assertEquals(35, dut.charactersRemaining);
		assertEquals(50, dut.maxMessageSize);
	}
	
	
	// Need this since CharacterState is not static, and CharacterCalculator is abstract
	private class MockCharacterCalculator extends CharacterCalculator {
		public CharacterState calculateCharacters(int charactersSpent) {
			throw new UnsupportedOperationException("Mock class");
		}
	}
	
}
