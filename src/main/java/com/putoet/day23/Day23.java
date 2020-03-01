package com.putoet.day23;

import utilities.ResourceLines;

import java.util.List;

public class Day23 {
    public static void main(String[] args) {
        final Processor processor = new Processor();
        final List<String> program = ResourceLines.list("/day23.txt");

        processor.compile(program);
        processor.run();
        System.out.println("Value of register b after running the program: " + processor.register(Processor.B).get());

        processor.compile(program);
        final Register a = processor.register(Processor.A);
        a.accept(1);
        processor.dump();
        processor.run();
        System.out.println("Value of register b after running the program with a starting at 1: " + processor.register(Processor.B).get());
    }
}
