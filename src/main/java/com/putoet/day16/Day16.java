package com.putoet.day16;

import com.putoet.Timer;
import com.putoet.resources.ResourceLines;

import java.util.List;

public class Day16 {
    public static void main(String[] args) {
        final var descriptions = ResourceLines.stream("/day16.txt")
                .map(Aunt::of)
                .toList();
        final var unknownAunt = new Aunt("", 0);
        final var properties = List.of(
                "children: 3",
                "cats: 7",
                "samoyeds: 2",
                "pomeranians: 3",
                "akitas: 0",
                "vizslas: 0",
                "goldfish: 5",
                "trees: 3",
                "cars: 2",
                "perfumes: 1");
        properties.forEach(unknownAunt::setProperty);

        final var matchStrict = Timer.run(() -> descriptions.stream()
                .filter(aunt -> aunt.couldMatch(unknownAunt))
                .findFirst()
                .orElseThrow()
        );
        System.out.println("Strict match: " + matchStrict.number());

        final var match = Timer.run(() -> descriptions.stream()
                .filter(aunt -> aunt.retroencabulatorMatch(unknownAunt))
                .findFirst()
                .orElseThrow()
        );
        System.out.println("Retroencabulator match: " + match.number());
    }
}
