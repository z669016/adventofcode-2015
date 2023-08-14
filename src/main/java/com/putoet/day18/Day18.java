package com.putoet.day18;

import com.putoet.Timer;
import com.putoet.resources.ResourceLines;

public class Day18 {
    public static void main(String[] args) {
        final var lines = ResourceLines.list("/day18.txt");

        Timer.run(() -> {
            final var lightGrid = LightGrid.of(lines);
            var nextLightGrid = lightGrid.next();
            for (int idx = 0; idx < 99; idx++)
                nextLightGrid = nextLightGrid.next();

            System.out.println("Number of burning lights after 100 steps is " + nextLightGrid.burningLights());
        });

        Timer.run(() -> {
            final var brokenLightGrid = BrokenLightGrid.of(lines);
            var nextLightGrid = brokenLightGrid.next();
            for (int idx = 0; idx < 99; idx++)
                nextLightGrid = nextLightGrid.next();

            System.out.println("Number of burning lights on the broken grid after 100 steps is " + nextLightGrid.burningLights());
        });
    }
}
