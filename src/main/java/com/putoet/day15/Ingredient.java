package com.putoet.day15;

public record Ingredient(String name, int capacity, int durability, int flavor, int texture, int calories) {
    public static Ingredient fromDescription(String description) {
        final String[] words = description.split(" ");
        final String name = trimLastChar(words[0]);
        final int capacity = Integer.parseInt(trimLastChar(words[2]));
        final int durability = Integer.parseInt(trimLastChar(words[4]));
        final int flavor = Integer.parseInt(trimLastChar(words[6]));
        final int texture = Integer.parseInt(trimLastChar(words[8]));
        final int calories = Integer.parseInt(words[10]);

        return new Ingredient(name, capacity, durability, flavor, texture, calories);
    }

    private static String trimLastChar(String word) {
        return word.substring(0, word.length() - 1);
    }

    @Override
    public String toString() {
        return name;
    }
}
