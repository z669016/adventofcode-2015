package com.putoet.day16;

import utilities.ResourceLines;

import java.util.List;
import java.util.stream.Collectors;

public class Day16 {
    public static void main(String[] args) {
        final List<String> descriptions = ResourceLines.list("/day16.txt");

        final Aunt unknownAunt = new Aunt("", 0);
        final List<String> properties = List.of(
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

        final List<Aunt> matchStrict = descriptions.stream()
                .map(Aunt::fromDescription)
                .filter(aunt -> aunt.couldMatchStrict(unknownAunt))
                .collect(Collectors.toList());
        System.out.println("Struct match: " + matchStrict);

        final List<Aunt> match = descriptions.stream()
                .map(Aunt::fromDescription)
                .filter(aunt -> aunt.couldMatch(unknownAunt))
                .collect(Collectors.toList());
        System.out.println("Struct match: " + match);
    }
}
