package com.putoet.day6;

import java.util.Arrays;
import java.util.function.Supplier;
import java.util.function.ToIntFunction;

class LightInstructionProcessor<T> {
    private final T[][] grid;
    private final ToIntFunction<T> toInt;

    LightInstructionProcessor(T initialValue, Supplier<T[][]> supplier, ToIntFunction<T> toInt) {
        this.grid = supplier.get();
        this.toInt = toInt;

        assert grid.length == grid[0].length;

        final var gridSize = grid.length;
        for (int idy = 0; idy < gridSize; idy++)
            for (int idx = 0; idx < gridSize; idx++)
                this.grid[idy][idx] = initialValue;
    }

    void execute(LightInstruction<T> instruction) {
        for (int idy = instruction.minY(); idy <= instruction.maxY(); idy++) {
            for (int idx = instruction.minX(); idx <= instruction.maxX(); idx++) {
                grid[idy][idx] = instruction.apply(grid[idy][idx]);
            }
        }
    }

    long count() {
        return Arrays.stream(grid).flatMap(Arrays::stream).mapToInt(toInt).sum();
    }
}
