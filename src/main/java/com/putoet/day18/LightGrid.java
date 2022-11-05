package com.putoet.day18;

import com.putoet.grid.Grid;
import com.putoet.grid.GridUtils;

import java.util.List;

public class LightGrid {
    private static final char ON = '#';
    private static final char OFF = '.';

    protected final Grid grid;

    public LightGrid(Grid grid) {
        assert grid != null;

        this.grid = grid;
    }

    public int height() {
        return grid.height();
    }

    public int width() {
        return grid.width();
    }

    public boolean get(int y, int x) {
        return x >= 0 && x < width() && y >= 0 && y < height() && grid.get(x, y) == ON;
    }

    public void set(int y, int x, boolean state) {
        grid.set(x, y, state ? ON : OFF);
    }

    public long burningLights() {
        return grid.count(ON);
    }

    public LightGrid next() {
        final LightGrid nextLightGrid = new LightGrid(grid.copy());
        next(nextLightGrid);
        return nextLightGrid;
    }

    protected void next(LightGrid nextLightGrid) {
        for (int y = 0; y < grid.height(); y++) {
            for (int x = 0; x < grid.width(); x++) {
                final int burningAround = burningAround(y, x);
                if (grid.get(x,y) == ON)
                    nextLightGrid.set(y, x, (burningAround ==2 || burningAround == 3));
                else
                    nextLightGrid.set(y, x, burningAround == 3);
            }
        }
    }

    private int burningAround(int y, int x) {
        int burningAround = 0;

        if (get(y-1, x)) burningAround++;
        if (get(y-1, x+1)) burningAround++;
        if (get(y, x+1)) burningAround++;
        if (get(y+1, x+1)) burningAround++;
        if (get(y+1, x)) burningAround++;
        if (get(y+1, x-1)) burningAround++;
        if (get(y, x-1)) burningAround++;
        if (get(y-1, x-1)) burningAround++;

        return burningAround;
    }

    public static LightGrid fromLines(List<String> lines) {
        return new LightGrid(new Grid(GridUtils.of(lines)));
    }
}

