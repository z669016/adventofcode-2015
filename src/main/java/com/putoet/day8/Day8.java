package com.putoet.day8;

import com.putoet.Timer;
import com.putoet.resources.ResourceLines;
import utilities.StringUtils;

import java.util.List;

public class Day8 {
    public static void main(String[] args) {
        final List<String> input = ResourceLines.list("/day8.txt");
        final int stringSizeSum = input.stream().mapToInt(String::length).sum();

        Timer.run(() -> {
            final int sanitizedSizeSum = input.stream().mapToInt(s -> StringUtils.sanitize(s).length()).sum();
            System.out.println("Solution day 8a");
            System.out.println("Total length of the strings: " + stringSizeSum);
            System.out.println("Total length of the sanitized strings: " + sanitizedSizeSum);
            System.out.println("The difference: " + (stringSizeSum - sanitizedSizeSum));
        });

        Timer.run(() -> {
            final int encodedSizeSum = input.stream().mapToInt(s -> StringUtils.encode(s).length()).sum();
            System.out.println("Solution day 8b");
            System.out.println("Total length of the strings: " + stringSizeSum);
            System.out.println("Total length of the encoded strings: " + encodedSizeSum);
            System.out.println("The difference: " + (encodedSizeSum - stringSizeSum));
        });
    }
}
