package com.putoet.day18;

import utilities.ResourceLines;

import java.util.List;

public class Day18 {
    public static void main(String[] args) {
        final List<String> lines = ResourceLines.list("/day18.txt");

        final Grid grid = Grid.fromLines(lines);
        Grid nextGrid = grid.next();
        for (int idx = 0; idx < 99; idx++)
            nextGrid = nextGrid.next();

        System.out.println("Number of burning lights after 100 steps is " + nextGrid.burningLights());

        final Grid brokenGrid = BrokenGrid.fromLines(lines);
        nextGrid = brokenGrid.next();
        for (int idx = 0; idx < 99; idx++)
            nextGrid = nextGrid.next();

        System.out.println("Number of burning lights on the broken grid after 100 steps is " + nextGrid.burningLights());

    }
}
