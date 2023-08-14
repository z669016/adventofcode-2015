package com.putoet.day19;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TokenizerTest {
    @Test
    void next() {
        final var line = "AbCdEFGhI";
        final var tokenizer = new Tokenizer(line);
        assertEquals("Ab", tokenizer.next());
        assertEquals("Cd", tokenizer.next());
        assertEquals("E", tokenizer.next());
        assertEquals("F", tokenizer.next());
        assertEquals("Gh", tokenizer.next());
        assertEquals("I", tokenizer.next());
        assertFalse(tokenizer.hasNext());
    }

    @Test
    void count() {
        final var line = "CRnFYFYFAr";
        final var tokenizer = new Tokenizer(line);
        while (tokenizer.hasNext())
            tokenizer.next();

        assertEquals(8, tokenizer.tokenCount());
        assertEquals(2, tokenizer.commaCount());
        assertEquals(2, tokenizer.parenthesesCount());
    }
}