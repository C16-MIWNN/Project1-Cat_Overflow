package nl.miwnn.ch16.catoverflow.cookingrecipeapplication;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import jakarta.validation.ConstraintViolation;

import nl.miwnn.ch16.catoverflow.cookingrecipeapplication.model.Ingredient;
import nl.miwnn.ch16.catoverflow.cookingrecipeapplication.model.IngredientRecipe;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Bas Folkers
 * This validates Ingredient by existance and IngredientName by not being empty
 */

public class IngredientValidationTest {
    private static Validator validator;

    @BeforeAll
    static void setupValidator() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    void ingredientMayNotBeNull() {
        IngredientRecipe ingredientRecipe = new IngredientRecipe();
        ingredientRecipe.setIngredient(null);

        Set<ConstraintViolation<IngredientRecipe>> violations = validator.validate(ingredientRecipe);

        assertFalse(violations.isEmpty(), "Ingredient may not be null");
    }

    @Test
    void ingredientNameMayNotBeBlank() {
        Ingredient ingredient = new Ingredient();
        ingredient.setIngredientName("");

        Set<ConstraintViolation<Ingredient>> violations = validator.validate(ingredient);

        assertFalse(violations.isEmpty(), "Ingredient name may not be empty");
    }
}
