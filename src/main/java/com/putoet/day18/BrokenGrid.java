package com.putoet.day18;

import java.util.List;

public class BrokenGrid extends Grid {
    public BrokenGrid(int height, int width) {
        super(height, width);
   }

    public static BrokenGrid fromLines(List<String> lines) {
        assert lines != null && lines.size() > 0;
        assert lines.get(0).length() > 0;

        final BrokenGrid grid = new BrokenGrid(lines.size(), lines.get(0).length());
        fromLines(lines, lines.get(0).length(), grid);

        grid.broken();

        return grid;
    }

    private void broken() {
        set(0, 0, true);
        set(0, width() - 1, true);
        set(height() - 1, 0, true);
        set(height() - 1, width ()- 1, true);
    }

    @Override
    public Grid next() {
        final BrokenGrid nextGrid = new BrokenGrid(height(), width());
        next(nextGrid);
        nextGrid.broken();
        return nextGrid;
    }
}
