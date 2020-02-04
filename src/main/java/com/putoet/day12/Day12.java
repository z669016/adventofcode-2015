package com.putoet.day12;

import java.util.List;
import java.util.Map;

public class Day12 {
    public static void main(String[] args) {
        final Map<String, Object> json = JsonMapper.fromJson("/day12.json");

        JsonSumVisitor sumVisitor = new JsonSumVisitor();
        MapVisitor.visit(json, sumVisitor, false);
        System.out.println("Sum of all integers is: " + sumVisitor.sum());


        sumVisitor = new JsonSumVisitor();
        MapVisitor.visit(json, sumVisitor, true);
        System.out.println("Sum of all integers while ignoring 'red' is: " + sumVisitor.sum());
    }
}

class JsonSumVisitor implements MapVisitor.Visitor {
    private long sum = 0;

    public long sum() {
        return sum;
    }

    @Override
    public void accept(Map<String, Object> map) {
    }

    @Override
    public void accept(List<Object> list) {
    }

    @Override
    public void accept(String string) {
    }

    @Override
    public void accept(Integer integer) {
        sum += integer.intValue();
    }
}