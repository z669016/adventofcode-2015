package com.putoet.day15;


import com.putoet.resources.ResourceLines;

import java.util.Map;
import java.util.stream.Collectors;

public class Day15 {
    public static void main(String[] args) {
        final Map<String,Ingredient> ingredients = ResourceLines.stream("/day15.txt")
                .map(Ingredient::fromDescription)
                .collect(Collectors.toMap(Ingredient::name, i -> i));

        final IngredientList ingredientList = new IngredientList(ingredients);
        Cookie cookie = ingredientList.maxCookie(calories -> true);
        System.out.println("Maximum score is " + cookie.score());
        // 13506480 is not the right answer (attempt 1)
        // 200000000 is too high (attempt 2)

        cookie = ingredientList.maxCookie(calories -> calories <= 500);
        System.out.println("Maximum score for 500 calories is " + cookie.score());
    }
}
