package com.putoet.day24;

import com.putoet.resources.ResourceLines;
import com.putoet.utils.Timer;

public class Day24 {
    public static void main(String[] args) {
        final var list = ResourceLines.list("/day24.txt", Integer::parseInt);

        Timer.run(() -> {
            final var wb3 = new WeightBalancer(3, list);
            final var cargo = wb3.loadBalancing().stream().findFirst().orElseThrow();
            System.out.println("(QE=" + WeightBalancer.quantumEntanglement(cargo) + ") " + cargo);
        });

        Timer.run(() -> {
            final var wb4 = new WeightBalancer(4, list);
            final var cargo = wb4.loadBalancingOnFour().stream().findFirst().orElseThrow();
            System.out.println("(QE=" + WeightBalancer.quantumEntanglement(cargo) + ") " + cargo);
        });
    }
}

