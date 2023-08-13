package com.putoet.day18;

import com.putoet.grid.Grid;
import com.putoet.grid.GridUtils;

import java.util.List;

class BrokenLightGrid extends LightGrid {
    public BrokenLightGrid(Grid grid) {
        super(grid);
    }

    private void broken() {
        set(0, 0, true);
        set(0, width() - 1, true);
        set(height() - 1, 0, true);
        set(height() - 1, width ()- 1, true);
    }

    public static BrokenLightGrid of(List<String> lines) {
        final BrokenLightGrid lightGrid = new BrokenLightGrid(new Grid(GridUtils.of(lines)));
        lightGrid.broken();

        return lightGrid;
    }

    @Override
    public BrokenLightGrid next() {
        final var nextGrid = new BrokenLightGrid(grid.copy());
        next(nextGrid);
        nextGrid.broken();
        return nextGrid;
    }
}
