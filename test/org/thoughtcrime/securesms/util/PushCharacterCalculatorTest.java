package org.thoughtcrime.securesms.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class PushCharacterCalculatorTest {

    @Test
    public void testCalculateCharacters() throws Exception {
        PushCharacterCalculator calc = new PushCharacterCalculator();
        CharacterCalculator.CharacterState st = calc.calculateCharacters(10);
        assertEquals(1, st.messagesSpent); //WARN The fact that st.messagesSpent is always 1 is suspicious
        assertEquals(st.maxMessageSize, st.charactersRemaining + 10);
    }

}