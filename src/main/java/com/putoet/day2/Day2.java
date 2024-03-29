package com.putoet.day2;

import com.putoet.resources.ResourceLines;
import com.putoet.utils.Timer;

public class Day2 {
    public static void main(String[] args) {
        final var boxes = ResourceLines.stream("/day2.txt").map(Box::of).toList();

        Timer.run(() ->
            System.out.println("Total wrapping paper required is " + boxes.stream().mapToInt(Box::totalWrap).sum())
        );

        Timer.run(() ->
            System.out.println("Total feet of ribbon required is " + boxes.stream().mapToInt(Box::totalRibbon).sum())
        );
    }
}
