package com.putoet.day15;

record Ingredient(String name, int capacity, int durability, int flavor, int texture, int calories) {
    public static Ingredient of(String description) {
        final var words = description.split(" ");
        final var name = trimLastChar(words[0]);
        final var capacity = Integer.parseInt(trimLastChar(words[2]));
        final var durability = Integer.parseInt(trimLastChar(words[4]));
        final var flavor = Integer.parseInt(trimLastChar(words[6]));
        final var texture = Integer.parseInt(trimLastChar(words[8]));
        final var calories = Integer.parseInt(words[10]);

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
