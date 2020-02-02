package com.putoet.day2;

import utilities.IntListUtils;

import java.util.Arrays;
import java.util.List;

public class Box {
    private final int l;
    private final int w;
    private final int h;

    private Box(int l, int w, int h) {
        this.l = l;
        this.w = w;
        this.h = h;
    }

    public int length() { return l; }
    public int height() { return h; }
    public int width() { return w; }

    public int wrapSize() {
        return (2 * l * w) + (2 * w * h) + (2 * h * l);
    }

    public int additionalWrap() {
        return List.of(l * w, l * h, w * h).stream().mapToInt(i -> i).min().getAsInt();
    }

    public int totalWrap() {
        return wrapSize() + additionalWrap();
    }

    public int ribbonLength() {
        return IntListUtils.exludeMax(List.of(l, w, h)).stream().mapToInt(i -> i).sum() * 2;
    }

    public int ribbonBowLength() {
        return l * w * h;
    }

    public int totalRibbon() {
        return ribbonLength() + ribbonBowLength();
    }

    public static Box of(String format) {
        final int[] lwh = Arrays.stream(format.split("x")).mapToInt(Integer::parseInt).toArray();
        if (lwh.length != 3)
            throw new IllegalArgumentException("Invalid format provided '" + format + "'");

        return new Box(lwh[0], lwh[1], lwh[2]);
    }
}
