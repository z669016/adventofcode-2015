package com.putoet.day18;

import com.putoet.resources.ResourceLines;

import java.util.List;

public class Day18 {
    public static void main(String[] args) {
        final List<String> lines = ResourceLines.list("/day18.txt");

        final LightGrid lightGrid = LightGrid.fromLines(lines);
        LightGrid nextLightGrid = lightGrid.next();
        for (int idx = 0; idx < 99; idx++)
            nextLightGrid = nextLightGrid.next();

        System.out.println("Number of burning lights after 100 steps is " + nextLightGrid.burningLights());

        final BrokenLightGrid brokenLightGrid = BrokenLightGrid.fromLines(lines);
        nextLightGrid = brokenLightGrid.next();
        for (int idx = 0; idx < 99; idx++)
            nextLightGrid = nextLightGrid.next();

        System.out.println("Number of burning lights on the broken grid after 100 steps is " + nextLightGrid.burningLights());

    }
}
