package com.putoet.day6;

import java.util.function.Function;

public class LightInstruction<T> {
    private final Function<T,T> function;
    private final int minX, maxX, minY, maxY;

    protected LightInstruction(int minX, int maxX, int minY, int maxY, Function<T,T> function) {
        this.minX = minX;
        this.maxX = maxX;
        this.minY = minY;
        this.maxY = maxY;
        this.function = function;
    }

    public T apply(T currentValue) {
        return function.apply(currentValue);
    }

    public int minX() { return minX; }
    public int maxX() { return maxX; }
    public int minY() { return minY; }
    public int maxY() { return maxY; }
}
