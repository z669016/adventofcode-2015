package com.putoet.day19;

import com.putoet.resources.ResourceLines;

import java.util.List;
import java.util.Optional;

public class Day19 {
    public static void main(String[] args) {
        final List<String> list = ResourceLines.list("/day19.txt");
        final Transformations transformations = Transformations.fromList(list);
        final Optional<String> molecule = Molecule.formList(list);

        if (molecule.isEmpty()) {
            System.out.println("No molecule found in the provided list...");
            return;
        }

        final List<String> transformed = transformations.apply(molecule.get());
        System.out.println("Number of possible molecules is " + transformed.size());

        final Tokenizer tokenizer = new Tokenizer(molecule.get());
        while (tokenizer.hasNext())
            tokenizer.next();

        // count(tokens) - count("(" or ")") - 2*count(",") - 1
        System.out.println("Number of steps is " + (tokenizer.tokenCount() - tokenizer.parenthesesCount() - 2 * tokenizer.commaCount() - 1));
        System.out.println();
    }
}
