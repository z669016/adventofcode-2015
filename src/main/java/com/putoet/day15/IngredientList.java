package com.putoet.day15;

import java.util.Map;
import java.util.function.Predicate;

public class IngredientList {
    private final Map<String, Ingredient> ingredients;

    public IngredientList(Map<String, Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    private static Cookie cookie(Ingredient frosting, Ingredient candy, Ingredient butterscotch, Ingredient sugar,
                                 int iFrosting, int iCandy, int iButterscotch, int iSugar) {
        return new Cookie(Map.of(frosting, iFrosting, candy, iCandy, butterscotch, iButterscotch, sugar, iSugar));
    }

    public Cookie maxCookie(Predicate<Long> predicate) {
        final Ingredient frosting = ingredients.get("Frosting");
        final Ingredient candy = ingredients.get("Candy");
        final Ingredient butterscotch = ingredients.get("Butterscotch");
        final Ingredient sugar = ingredients.get("Sugar");

        long maxScore = 0;
        Cookie maxCookie = null;
        for (int iFrosting = 0; iFrosting <= 100; iFrosting++) {
            for (int iCandy = 0; iCandy <= (100 - iFrosting); iCandy++) {
                for (int iButterscotch = 0; iButterscotch <= (100 - iFrosting - iCandy); iButterscotch++) {
                    final int iSugar = 100 - iFrosting - iCandy - iButterscotch;
                    final Cookie cookie = cookie(frosting, candy, butterscotch, sugar, iFrosting, iCandy, iButterscotch, iSugar);
                    if (predicate.test(cookie.totalCalories()) && (cookie.score() > maxScore)) {
                        maxScore = cookie.score();
                        maxCookie = cookie;
                    }
                }
            }
        }

        return maxCookie;
    }
}
