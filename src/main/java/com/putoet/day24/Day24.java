package com.putoet.day24;

import com.putoet.resources.ResourceLines;

import java.util.*;

public class Day24 {
    public static void main(String[] args) {
        final List<Integer> list =
                ResourceLines.stream("/day24.txt")
                        .map(Integer::parseInt)
                        .toList();

        final WeightBalancer wb3 = new WeightBalancer(3, list);;
        Cargo cargo = wb3.loadBalancing().stream().findFirst().orElseThrow();
        System.out.println("(QE=" + WeightBalancer.quantumEntanglement(cargo) + ") " + cargo);

        final WeightBalancer wb4 = new WeightBalancer(4, list);
        cargo = wb4.loadBalancingOnFour().stream().findFirst().orElseThrow();
        System.out.println("(QE=" + WeightBalancer.quantumEntanglement(cargo) + ") " + cargo);
    }
}

