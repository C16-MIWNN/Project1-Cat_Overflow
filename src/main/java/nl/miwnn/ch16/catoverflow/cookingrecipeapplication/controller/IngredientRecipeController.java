package nl.miwnn.ch16.catoverflow.cookingrecipeapplication.controller;

import nl.miwnn.ch16.catoverflow.cookingrecipeapplication.repositories.IngredientRecipeRepository;
import org.springframework.stereotype.Controller;

/**
 * @author Bas Folkers
 * Schrijf hier wat je programma doet
 */

@Controller
public class IngredientRecipeController {
    private final IngredientRecipeRepository ingredientRecipeRepository;

    public IngredientRecipeController(IngredientRecipeRepository ingredientRecipeRepository) {
        this.ingredientRecipeRepository = ingredientRecipeRepository;
    }


}
