package com.putoet.day19;

import com.putoet.Timer;
import com.putoet.resources.ResourceLines;

import java.util.List;

public class Day19 {
    public static void main(String[] args) {
        final var list = ResourceLines.list("/day19.txt");
        final var transformations = Transformations.of(list);
        final var molecule = Molecule.formList(list);
        if (molecule.isEmpty())
            throw new IllegalArgumentException("No molecule found in the provided list...");

        Timer.run(() -> {
            final var transformed = transformations.apply(molecule.get());
            System.out.println("Number of possible molecules is " + transformed.size());
        });

        Timer.run(() -> {
            final var tokenizer = new Tokenizer(molecule.get());
            while (tokenizer.hasNext())
                tokenizer.next();

            // count(tokens) - count("(" or ")") - 2*count(",") - 1
            System.out.println("Number of steps is " + (tokenizer.tokenCount() - tokenizer.parenthesesCount() - 2 * tokenizer.commaCount() - 1));
        });
    }
}
