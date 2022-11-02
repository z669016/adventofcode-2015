package com.putoet.day7;

import com.putoet.resources.ResourceLines;

public class Day7 {
    public static void main(String[] args) {
        final Circuit circuit = Circuit.from(ResourceLines.list("/day7.txt"));
        Integer a = circuit.get("a");
        System.out.println("The value for wire a is " + a);

        circuit.replaceEncoding("b", String.valueOf(a));
        Integer a2 = circuit.get("a");
        Integer b = circuit.get("b");
        System.out.println("The value for wire a after is resetting b to '" + b + "' is " + a2);
    }
}
