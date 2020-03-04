package com.putoet.day24;

import utilities.ResourceLines;

import java.util.*;
import java.util.stream.Collectors;

public class Day24 {
    public static void main(String[] args) {
        final List<Integer> list =
                ResourceLines.list("/day24.txt").stream()
                        .map(Integer::parseInt)
                        .collect(Collectors.toList());

        final WeightBalancer wb3 = new WeightBalancer(3, list);
        final List<Cargo> cargoList3 = wb3.loadBalancing();
        cargoList3.forEach(cargo -> System.out.println("(QE=" + WeightBalancer.quantumEntanglement(cargo) + ") " + cargo));

        final WeightBalancer wb4 = new WeightBalancer(4, list);
        final List<Cargo> cargoList4 = wb4.loadBalancingOnFour();
        cargoList4.forEach(cargo -> System.out.println("(QE=" + WeightBalancer.quantumEntanglement(cargo) + ") " + cargo));
    }
}

