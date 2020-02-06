package com.putoet.day15;

import java.util.HashMap;
import java.util.Map;

public class Cookie {
    private Map<Ingredient,Integer> ingredients = new HashMap<>();

    public Cookie(Map<Ingredient,Integer> ingredients) {
        if (ingredients.values().stream().mapToInt(i->i).sum() != 100)
            throw new IllegalArgumentException("Ingredients must total up to 100 teaspoons " + ingredients);

        this.ingredients = ingredients;
    }

    public long score() {
        return totalCapacity() * totalDurability() * totalFlavor() * totalTexture();
    }

    protected long totalCapacity() {
        return Math.max(0, ingredients.entrySet().stream()
                .mapToInt(e -> e.getKey().capacity() * e.getValue())
                .sum());
    }

    protected long totalDurability() {
        return Math.max(0, ingredients.entrySet().stream()
                .mapToInt(e -> e.getKey().durability() * e.getValue())
                .sum());
    }

    protected long totalFlavor() {
        return Math.max(0, ingredients.entrySet().stream()
                .mapToInt(e -> e.getKey().flavor() * e.getValue())
                .sum());
    }

    protected long totalTexture() {
        return Math.max(0, ingredients.entrySet().stream()
                .mapToInt(e -> e.getKey().texture() * e.getValue())
                .sum());
    }

    protected long totalCalories() {
        return Math.max(0, ingredients.entrySet().stream()
                .mapToInt(e -> e.getKey().calories() * e.getValue())
                .sum());
    }
}
