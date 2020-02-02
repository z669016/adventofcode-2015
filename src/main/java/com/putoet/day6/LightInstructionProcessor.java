package com.putoet.day6;

import java.util.Arrays;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class LightInstructionProcessor<T> {
    protected final T[][] grid;

    public LightInstructionProcessor(T initialValue, Supplier<T[][]> supplier) {
        this.grid = supplier.get();

        assert grid.length == grid[0].length;

        final int gridSize = grid.length;
        for (int idy = 0; idy < gridSize; idy++)
            for (int idx = 0; idx < gridSize; idx++)
                this.grid[idy][idx] = initialValue;
    }

    public void execute(LightInstruction<T> instruction) {
        for (int idy = instruction.minY(); idy <= instruction.maxY(); idy++) {
            for (int idx = instruction.minX(); idx <= instruction.maxX(); idx++) {
                grid[idy][idx] = instruction.apply(grid[idy][idx]);
            }
        }
    }

    public long count(Predicate<T> predicate) {
        return Arrays.stream(grid).flatMap(Arrays::stream).filter(predicate).count();
    }
}
