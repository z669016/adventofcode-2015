package com.putoet.day1;

import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.*;

class Day1Test {
    @Test
    void transformOpen() {
        assertEquals(new Day1.Move(1, 3), Day1.Move.of('(', new AtomicInteger(3)));
    }

    @Test
    void transformClose() {
        assertEquals(new Day1.Move(-1, 5), Day1.Move.of(')', new AtomicInteger(5)));
    }

    @Test
    void transformOther() {
        assertThrows(IllegalArgumentException.class, () -> Day1.Move.of('+', new AtomicInteger(0)));
    }

    @Test
    void floor() {
        assertEquals(0, Day1.finalFloor("(())", new AtomicInteger(1)));
        assertEquals(0, Day1.finalFloor("()()", new AtomicInteger(1)));
        assertEquals(3, Day1.finalFloor("(((", new AtomicInteger(1)));
        assertEquals(3, Day1.finalFloor("(()(()(", new AtomicInteger(1)));
        assertEquals(3, Day1.finalFloor("))(((((", new AtomicInteger(1)));
        assertEquals(-1, Day1.finalFloor("())", new AtomicInteger(1)));
        assertEquals(-1, Day1.finalFloor("))(", new AtomicInteger(1)));
        assertEquals(-3, Day1.finalFloor(")))", new AtomicInteger(1)));
        assertEquals(-3, Day1.finalFloor(")())())", new AtomicInteger(1)));
    }

    @Test
    public void basement() {
        Optional<Integer> basement = Day1.basement(")", new AtomicInteger(1));
        assertTrue(basement.isPresent());
        assertEquals(1, basement.get());

        basement = Day1.basement("()())", new AtomicInteger(1));
        assertTrue(basement.isPresent());
        assertEquals(5, basement.get());
    }
}