package com.putoet.day5;

import utilities.StringUtils;

import java.util.List;
class Nice {
    static boolean isNice(String text) {
        assert text != null;

        return !StringUtils.containsAny(text, List.of("ab", "cd", "pq", "xy"))
                && (StringUtils.vowels(text) >= 3)
                && StringUtils.containsDouble(text);
    }

    static boolean isNicer(String text) {
        assert text != null;

        return StringUtils.containsSeperatedDouble(text) && StringUtils.containsPairWithoutOverlap(text);
    }
}
