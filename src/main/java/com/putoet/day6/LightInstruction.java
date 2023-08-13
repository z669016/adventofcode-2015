package com.putoet.day6;

import java.util.function.Function;

record LightInstruction<T>(int minX, int maxX, int minY, int maxY, Function<T,T> function) {
    public LightInstruction {
        assert inRange(minX);
        assert inRange(maxX);
        assert inRange(minY);
        assert inRange(maxY);
        assert function != null;
    }

    private boolean inRange(int value) {
        return value >= 0 && value <= 999;
    }

    public T apply(T currentValue) {
        return function.apply(currentValue);
    }
    public int minX() { return minX; }
    public int maxX() { return maxX; }
    public int minY() { return minY; }
    public int maxY() { return maxY; }
}
