package com.putoet.day15;

import java.util.Map;

record Cookie(Map<Ingredient,Integer> ingredients) {

    public Cookie {
        if (ingredients.values().stream().mapToInt(i->i).sum() != 100)
            throw new IllegalArgumentException("Ingredients must total up to 100 teaspoons " + ingredients);
    }

    public long score() {
        return totalCapacity() * totalDurability() * totalFlavor() * totalTexture();
    }

    long totalCapacity() {
        return Math.max(0, ingredients.entrySet().stream()
                .mapToInt(e -> e.getKey().capacity() * e.getValue())
                .sum());
    }

    long totalDurability() {
        return Math.max(0, ingredients.entrySet().stream()
                .mapToInt(e -> e.getKey().durability() * e.getValue())
                .sum());
    }

    long totalFlavor() {
        return Math.max(0, ingredients.entrySet().stream()
                .mapToInt(e -> e.getKey().flavor() * e.getValue())
                .sum());
    }

    long totalTexture() {
        return Math.max(0, ingredients.entrySet().stream()
                .mapToInt(e -> e.getKey().texture() * e.getValue())
                .sum());
    }

    long totalCalories() {
        return Math.max(0, ingredients.entrySet().stream()
                .mapToInt(e -> e.getKey().calories() * e.getValue())
                .sum());
    }
}
