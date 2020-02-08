package com.putoet.day18;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Grid {
    private final Boolean[][] grid;
    private final int height;
    private final int width;

    public Grid(int height, int width) {
        assert height > 0;
        assert width > 0;

        this.grid = new Boolean[height][width];
        this.height = height;
        this.width = width;

        for (Boolean[] line : grid)
            Arrays.fill(line, false);
    }

    public int height() {
        return height;
    }

    public int width() {
        return width;
    }

    public boolean get(int y, int x) {
        return (y < 0 || y >= height || x < 0 || x >= width) ? false : grid[y][x];
    }

    public void set(int y, int x, boolean state) {
        assert y < height;
        assert x < width;

        grid[y][x] = state;
    }

    public long burningLights() {
        return Arrays.stream(grid).flatMap(Arrays::stream).filter(b -> b).count();
    }

    public Grid next() {
        final Grid nextGrid = new Grid(height, width);
        next(nextGrid);
        return nextGrid;
    }

    protected void next(Grid nextGrid) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                final int burningAround = burningAround(y, x);

                if (grid[y][x]) nextGrid.grid[y][x] = (burningAround ==2 || burningAround == 3);
                else nextGrid.grid[y][x] = (burningAround == 3);
            }
        }
    }

    private int burningAround(int y, int x) {
        int burningAround = 0;

        if (get(y-1, x+0)) burningAround++;
        if (get(y-1, x+1)) burningAround++;
        if (get(y+0, x+1)) burningAround++;
        if (get(y+1, x+1)) burningAround++;
        if (get(y+1, x+0)) burningAround++;
        if (get(y+1, x-1)) burningAround++;
        if (get(y+0, x-1)) burningAround++;
        if (get(y-1, x-1)) burningAround++;

        return burningAround;
    }

    public static Grid fromLines(List<String> lines) {
        assert lines != null && lines.size() > 0;
        assert lines.get(0).length() > 0;

        final Grid grid = new Grid(lines.size(), lines.get(0).length());
        fromLines(lines, lines.get(0).length(), grid);

        return grid;
    }

    protected static void fromLines(List<String> lines, int width, Grid grid) {
        assert grid != null;
        assert lines.size() == grid.height;
        assert width == grid.width;

        for (int y = 0; y < grid.height; y++) {
            final String line = lines.get(y);
            if (line.length() != width)
                throw new IllegalArgumentException("Lines have different sizes ...");

            for (int x = 0; x < grid.width; x++) {
                grid.set(y, x, line.charAt(x) == '#');
            }
        }
    }

    public List<String> toList() {
        final List<String> lines = new ArrayList<>();
        for (Boolean[] line : grid) {
            final StringBuilder sb = new StringBuilder();
            for (Boolean b : line)
                sb.append(b ? "#" : ".");

            lines.add(sb.toString());
        }

        return lines;
    }
}

