package com.putoet.day15;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class CookieTest {
    private final Ingredient butterscotch = Ingredient.fromDescription("Butterscotch: capacity -1, durability -2, flavor 6, texture 3, calories 8");
    private final Ingredient cinnamon = Ingredient.fromDescription("Cinnamon: capacity 2, durability 3, flavor -2, texture -1, calories 3");
    private Cookie cookie = null;

    @BeforeEach
    void setup() {
        cookie = new Cookie(Map.of(butterscotch, 44, cinnamon, 56));
    }

    @Test
    void newCookie() {
        assertThrows(IllegalArgumentException.class, () -> new Cookie(Map.of(butterscotch, 44, cinnamon, 55)));
    }

    @Test
    void score() {
        assertEquals(62842880, cookie.score());
    }
}