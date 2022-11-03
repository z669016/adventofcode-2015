package com.putoet.day10;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SplitToTextTest {

    @Test
    void asText() {
        assertEquals("11", SplitToText.asText(SplitToText.splitter("1")));
        assertEquals("21", SplitToText.asText(SplitToText.splitter("11")));
        assertEquals("1211", SplitToText.asText(SplitToText.splitter("21")));
        assertEquals("111221", SplitToText.asText(SplitToText.splitter("1211")));
        assertEquals("312211", SplitToText.asText(SplitToText.splitter("111221")));
    }

    @Test
    public void splitter() {
        assertEquals(List.of(), SplitToText.splitter(""));
        assertEquals(List.of("1"), SplitToText.splitter("1"));
        assertEquals(List.of("1", "2", "3"), SplitToText.splitter("123"));
        assertEquals(List.of("1111"), SplitToText.splitter("1111"));
        assertEquals(List.of("1", "22", "1"), SplitToText.splitter("1221"));
        assertEquals(List.of("1", "22", "33"), SplitToText.splitter("12233"));
    }
}