package com.putoet;

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

        return String.valueOf(split.length()) + ch;
    }
}
