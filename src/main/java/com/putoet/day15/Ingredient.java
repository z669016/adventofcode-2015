package com.putoet.day15;

public class Ingredient {
    private final String name;
    private final int capacity;
    private final int durability;
    private final int flavor;
    private final int texture;
    private final int calories;

    private Ingredient(String name, int capacity, int durability, int flavor, int texture, int calories) {
        this.name = name;
        this.capacity = capacity;
        this.durability = durability;
        this.flavor = flavor;
        this.texture = texture;
        this.calories = calories;
    }

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

    public String name() {
        return name;
    }

    public int capacity() {
        return capacity;
    }

    public int durability() {
        return durability;
    }

    public int flavor() {
        return flavor;
    }

    public int texture() {
        return texture;
    }

    public int calories() {
        return calories;
    }

    @Override
    public String toString() {
        return name;
    }
}
