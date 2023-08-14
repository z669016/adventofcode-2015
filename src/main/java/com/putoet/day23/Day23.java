package com.putoet.day23;

import com.putoet.Timer;
import com.putoet.resources.ResourceLines;

public class Day23 {
    public static void main(String[] args) {
        final var processor = new Processor();
        final var program = ResourceLines.list("/day23.txt");

        Timer.run(() -> {
            processor.compile(program);
            processor.run();
            System.out.println("Value of register b after running the program: " + processor.register(Processor.B).get());
        });

        Timer.run(() -> {
            processor.compile(program);
            processor.register(Processor.A).accept(1);
            processor.run();
            System.out.println("Value of register b after running the program with a starting at 1: " + processor.register(Processor.B).get());
        });
    }
}
