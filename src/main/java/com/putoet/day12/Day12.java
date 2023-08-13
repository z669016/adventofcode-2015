package com.putoet.day12;

import com.putoet.Timer;

import java.util.Map;

public class Day12 {
    public static void main(String[] args) {
        final Map<String, Object> json = JsonMapper.fromJson("/day12.json");

        Timer.run(() -> {
            var sumVisitor = new SumVisitor();
            MapWalker.walk(json, sumVisitor, false);
            System.out.println("Sum of all integers is: " + sumVisitor.sum());
            return null;
        });

        Timer.run(() -> {
            var sumVisitor = new SumVisitor();
            MapWalker.walk(json, sumVisitor, true);
            System.out.println("Sum of all integers while ignoring 'red' is: " + sumVisitor.sum());
            return null;
        });
    }
}

class SumVisitor implements Visitor {
    private long sum = 0;

    public long sum() {
        return sum;
    }

    @Override
    public void accept(Integer integer) {
        sum += integer;
    }
}