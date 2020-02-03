package com.putoet.day8;

import utilities.ResourceLines;
import utilities.StringUtils;

import java.sql.SQLOutput;

public class Day8 {
    public static void main(String[] args) {
        int stringSizeSum = ResourceLines.stream("/day8.txt").mapToInt(s -> s.length()).sum();
        int sanitizedSizeSum = ResourceLines.stream("/day8.txt").mapToInt(s -> StringUtils.sanitize(s).length()).sum();

        System.out.println("Solution day 8a");
        System.out.println("Total length of the strings: " + stringSizeSum);
        System.out.println("Total length of the sanitized strings: " + sanitizedSizeSum);
        System.out.println("The difference: " + (stringSizeSum - sanitizedSizeSum));

        int encodedSizeSum = ResourceLines.stream("/day8.txt").mapToInt(s -> StringUtils.encode(s).length()).sum();
        System.out.println("Solution day 8b");
        System.out.println("Total length of the strings: " + stringSizeSum);
        System.out.println("Total length of the encoded strings: " + encodedSizeSum);
        System.out.println("The difference: " + (encodedSizeSum - stringSizeSum));
    }
}
