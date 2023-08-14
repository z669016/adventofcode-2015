package com.putoet.day5;

import com.putoet.resources.ResourceLines;
import com.putoet.utils.Timer;

public class Day5 {
    public static void main(String[] args) {
        Timer.run(() ->
            System.out.println("Number of nice strings: " + ResourceLines.stream("/day5.txt")
                    .filter(Nice::isNice)
                    .count())
        );

        Timer.run(() ->
            System.out.println("Number of nicer strings: " + ResourceLines.stream("/day5.txt")
                    .filter(Nice::isNicer)
                    .count())
        );
    }
}
