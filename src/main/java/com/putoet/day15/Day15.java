package com.putoet.day15;

import com.putoet.utils.Timer;
import com.putoet.resources.ResourceLines;

import java.util.stream.Collectors;

public class Day15 {
    public static void main(String[] args) {
        final var ingredients = ResourceLines.stream("/day15.txt")
                .map(Ingredient::of)
                .collect(Collectors.toMap(Ingredient::name, i -> i));
        final var ingredientList = new IngredientList(ingredients);

        Timer.run(() -> {
            var cookie = ingredientList.maxCookie(calories -> true);
            System.out.println("Maximum score is " + cookie.score());
        });

        Timer.run(() -> {
            var cookie = ingredientList.maxCookie(calories -> calories <= 500);
            System.out.println("Maximum score for 500 calories is " + cookie.score());
        });
    }
}
