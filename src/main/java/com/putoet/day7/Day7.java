package com.putoet.day7;

import com.putoet.resources.ResourceLines;
import com.putoet.utils.Timer;

public class Day7 {
    public static void main(String[] args) {
        final Circuit circuit = Circuit.from(ResourceLines.list("/day7.txt"));

        final var value = Timer.run(() -> {
            final var a = circuit.get("a");
            System.out.println("The value for wire a is " + a);
            return a;
        });

        Timer.run(() -> {
            circuit.replaceEncoding("b", String.valueOf(value));
            final var a = circuit.get("a");
            final var b = circuit.get("b");
            System.out.println("The value for wire a after is resetting b to '" + b + "' is " + a);
        });
    }
}
