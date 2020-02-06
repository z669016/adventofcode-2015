package com.putoet.day15;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class IngredientTest {

    @Test
    void fromDescription() {
        final Ingredient ingredient = Ingredient.fromDescription("Butterscotch: capacity -1, durability 1, flavor 5, texture 0, calories 6");
        assertEquals("Butterscotch", ingredient.name());
        assertEquals(-1, ingredient.capacity());
        assertEquals(1, ingredient.durability());
        assertEquals(5, ingredient.flavor());
        assertEquals(0, ingredient.texture());
        assertEquals(6, ingredient.calories());
    }
}