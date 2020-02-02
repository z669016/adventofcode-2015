package com.putoet.day1;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class Day1Test {
    @Test
    void transformOpen() {
        assertEquals(1, Day1.transform('('));
    }

    @Test
    void transformClose() {
        assertEquals(-1, Day1.transform(')'));
    }

    @Test
    void transformOther() {
        assertThrows(IllegalArgumentException.class, () -> Day1.transform('+'));
    }

    @Test
    void floor() {
        assertEquals(0, Day1.floor("(())"));
        assertEquals(0, Day1.floor("()()"));
        assertEquals(3, Day1.floor("((("));
        assertEquals(3, Day1.floor("(()(()("));
        assertEquals(3, Day1.floor("))((((("));
        assertEquals(-1, Day1.floor("())"));
        assertEquals(-1, Day1.floor("))("));
        assertEquals(-3, Day1.floor(")))"));
        assertEquals(-3, Day1.floor(")())())"));
    }

    @Test
    public void basement() {
        Optional<Integer> basement = Day1.basement(")");
        assertTrue(basement.isPresent());
        assertEquals(1, basement.get());

        basement = Day1.basement("()())");
        assertTrue(basement.isPresent());
        assertEquals(5, basement.get());
    }
}