package com.putoet.day10;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class SplitToText {
    public static String asText(List<String> split) {
        return split.stream().map(SplitToText::asText).collect(Collectors.joining());
    }

    private static String asText(String split) {
        final String ch = split.substring(0, 1);
        if(!"123".contains(ch))
            throw new IllegalArgumentException("Can only translate to text for 1, 2, ane 3");

        return split.length() + ch;
    }

    /**
     * Spilt a text into a list that contains sequences (String) of continuous identical characters of the original text.
     * So, for example, "112233322" becomes List.of("11","22","333","33".
     *
     * @param text String test to split
     * @return List<String>
     */
    public static List<String> splitter(String text) {
        assert text != null;

        if (text.length() == 0)
            return List.of();

        if (text.length() == 1)
            return List.of(text);

        final List<String> split = new ArrayList<>();
        int prev = 0;
        for (int next = 1; next < text.length(); next++) {
            if (text.charAt(next) != text.charAt(prev)) {
                split.add(text.substring(prev, next));
                prev = next;
            }
        }
        split.add(text.substring(prev));

        return Collections.unmodifiableList(split);
    }
}
