package com.putoet.day2;

import com.putoet.resources.ResourceLines;

import java.util.List;

public class Day2 {
    public static void main(String[] args)  {
        final List<Box> boxes = ResourceLines.stream("/day2.txt").map(Box::of).toList();

        System.out.println("Total wrapping paper required is " + boxes.stream().mapToInt(Box::totalWrap).sum());
        System.out.println("Total feet of ribbon required is " + boxes.stream().mapToInt(Box::totalRibbon).sum());
    }
}
