package com.putoet.day5;

import com.putoet.resources.ResourceLines;

public class Day5 {
    public static void main(String[] args) {
        System.out.println("Number of nice strings: " + ResourceLines.stream("/day5.txt")
                .filter(Nice::isNice)
                .count());
        System.out.println("Number of nicer strings: " + ResourceLines.stream("/day5.txt")
                .filter(Nice::isNicer)
                .count());
    }
}
