package com.putoet;

import org.junit.jupiter.api.Test;
import utilities.StringUtils;

import static org.junit.jupiter.api.Assertions.*;

class SplitToTextTest {

    @Test
    void asText() {
        assertEquals("11", SplitToText.asText(StringUtils.splitter("1")));
        assertEquals("21", SplitToText.asText(StringUtils.splitter("11")));
        assertEquals("1211", SplitToText.asText(StringUtils.splitter("21")));
        assertEquals("111221", SplitToText.asText(StringUtils.splitter("1211")));
        assertEquals("312211", SplitToText.asText(StringUtils.splitter("111221")));
    }
}