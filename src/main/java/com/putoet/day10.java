package com.putoet;

import utilities.StringUtils;

public class day10 {
    public static void main(String[] args) {
        String text = "3113322113";
        for (int idx = 0; idx < 40; idx++)
            text = SplitToText.asText(StringUtils.splitter(text));

        System.out.println("Day 10a solution is " + text.length());


        text = "3113322113";
        for (int idx = 0; idx < 50; idx++)
            text = SplitToText.asText(StringUtils.splitter(text));

        System.out.println("Day 10b solution is " + text.length());
    }
}
