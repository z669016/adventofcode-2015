package com.putoet.day5;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NiceTest {

    @Test
    void isNice() {
        assertTrue(Nice.isNice("ugknbfddgicrmopn"));
        assertTrue(Nice.isNice("aaa"));
        assertFalse(Nice.isNice("jchzalrnumimnmhp"));
        assertFalse(Nice.isNice("haegwjzuvuyypxyu"));
        assertFalse(Nice.isNice("dvszwmarrgswjxmb"));
    }

    @Test
    public void nicer() {
        assertTrue(Nice.isNicer("qjhvhtzxzqqjkmpb"));
        assertTrue(Nice.isNicer("xxyxx"));
        assertFalse(Nice.isNicer("uurcxstgmygtbstg"));
        assertFalse(Nice.isNicer("ieodomkazucvgmuy"));
    }
}