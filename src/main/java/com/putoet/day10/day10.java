package com.putoet.day10;

public class day10 {
    public static void main(String[] args) {
        String text = "3113322113";
        
        for (int idx = 0; idx < 40; idx++)
            text = SplitToText.asText(SplitToText.splitter(text));
        System.out.println("Day 10a solution is " + text.length());

        for (int idx = 40; idx < 50; idx++)
            text = SplitToText.asText(SplitToText.splitter(text));
        System.out.println("Day 10b solution is " + text.length());
    }
}
