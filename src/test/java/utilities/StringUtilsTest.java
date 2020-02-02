package utilities;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StringUtilsTest {
    @Test
    public void vowels() {
        assertEquals(3, StringUtils.vowels("aei"));
        assertEquals(3, StringUtils.vowels("xazegov"));
        assertEquals(15, StringUtils.vowels("aeiouaeiouaeiou"));
    }

    @Test
    public void containsDOuble() {
        assertFalse(StringUtils.containsDouble("abcd"));
        assertTrue(StringUtils.containsDouble("aabcd"));
        assertTrue(StringUtils.containsDouble("abbcd"));
        assertTrue(StringUtils.containsDouble("abcdd"));
    }

    @Test
    public void containsAny() {
        final String text = "abcdefg";

        assertFalse(StringUtils.containsAny(text, List.of()));
        assertTrue(StringUtils.containsAny(text, List.of("fg", "xq")));
        assertTrue(StringUtils.containsAny(text, List.of("xq", "ef")));
        assertFalse(StringUtils.containsAny(text, List.of("xq", "yz")));
    }

    @Test
    public void containsPairWithoutOverlap() {
        assertTrue(StringUtils.containsPairWithoutOverlap("qjhvhtzxzqqjkmpb"));
        assertFalse(StringUtils.containsPairWithoutOverlap("aaa"));
        assertTrue(StringUtils.containsPairWithoutOverlap("aaaa"));
        assertTrue(StringUtils.containsPairWithoutOverlap("xyxy"));
        assertTrue(StringUtils.containsPairWithoutOverlap("aabcdefgaa"));
    }

    @Test
    public void containsSeperatedDouble() {
        assertFalse(StringUtils.containsSeperatedDouble(""));
        assertFalse(StringUtils.containsSeperatedDouble("a"));
        assertFalse(StringUtils.containsSeperatedDouble("aa"));
        assertTrue(StringUtils.containsSeperatedDouble("xyx"));
        assertTrue(StringUtils.containsSeperatedDouble("abcdefeghi"));
        assertFalse(StringUtils.containsSeperatedDouble("abcdefghi"));
    }
}