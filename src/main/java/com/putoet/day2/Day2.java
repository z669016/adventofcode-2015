package com.putoet.day2;

import com.putoet.resources.ResourceLines;

import java.util.List;

public class Day2 {
    public static void main(String[] args)  {
        final List<String> lines = ResourceLines.list("/day2.txt");

        System.out.println("Total wrapping paper required is " + lines.stream().map(Box::of).mapToInt(Box::totalWrap).sum());
        System.out.println("Total feet of ribbon required is " + lines.stream().map(Box::of).mapToInt(Box::totalRibbon).sum());
    }
}
