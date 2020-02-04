package com.putoet.day11;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PasswordTest {

    @Test
    public void isValid() {
        assertFalse(Password.isValid("hijklmmn"));
        assertFalse(Password.isValid("abbceffg"));
        assertFalse(Password.isValid("abbcegjk"));
        assertTrue(Password.isValid("abcdffaa"));
        assertTrue(Password.isValid("ghjaabcc"));
        assertTrue(Password.isValid("abcccccc"));
    }

    @Test
    public void next() {
        assertEquals("abcdffaa", new Password("abcdefgh").next().toString());
        assertEquals("ghjaabcc", new Password("ghijklmn").next().toString());
        assertNotEquals("vzbxxyzz", new Password("vzbxxyzz").next().toString());
    }
}