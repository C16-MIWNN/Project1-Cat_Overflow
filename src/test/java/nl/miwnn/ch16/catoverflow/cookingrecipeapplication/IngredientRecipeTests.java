package nl.miwnn.ch16.catoverflow.cookingrecipeapplication;

import nl.miwnn.ch16.catoverflow.cookingrecipeapplication.model.Ingredient;
import nl.miwnn.ch16.catoverflow.cookingrecipeapplication.model.IngredientRecipe;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Bas Folkers
 */

public class IngredientRecipeTests {

    @Test
    void testToString() {
//        Arrange
        IngredientRecipe ingredientRecipe = new IngredientRecipe();
        ingredientRecipe.setQuantity(3);
        ingredientRecipe.setIngredientUnit("tablespoons");

        Ingredient ingredient = new Ingredient();
        ingredient.setIngredientName("butter");
        ingredientRecipe.setIngredient(ingredient);

        ingredientRecipe.setNotes("unsalted");

        String expected = "3 tablespoons butter (unsalted)";

//        Act
        String actual = ingredientRecipe.toString();
//        Assert
        assertEquals(expected, actual);
    }

    @Test
    void testToString_QuantityZero_UnitNull() {
        IngredientRecipe ingredientRecipe = new IngredientRecipe();
        ingredientRecipe.setQuantity(0);
        ingredientRecipe.setIngredientUnit(null);

        Ingredient ingredient = new Ingredient();
        ingredient.setIngredientName("Sugar");
        ingredientRecipe.setIngredient(ingredient);

        ingredientRecipe.setNotes("add to taste");

        String expected = "Sugar (add to taste)";

        String actual = ingredientRecipe.toString();
        assertEquals(expected, actual);
    }

    @Test
    void testToString_QuantityZero_UnitBlank() {
        IngredientRecipe ingredientRecipe = new IngredientRecipe();
        ingredientRecipe.setQuantity(0);
        ingredientRecipe.setIngredientUnit("");

        Ingredient ingredient = new Ingredient();
        ingredient.setIngredientName("Sugar");
        ingredientRecipe.setIngredient(ingredient);

        ingredientRecipe.setNotes("add to taste");

        String expected = "Sugar (add to taste)";

        String actual = ingredientRecipe.toString();
        assertEquals(expected, actual);
    }

    @Test
    void testToString_UnitNull() {
        IngredientRecipe ingredientRecipe = new IngredientRecipe();
        ingredientRecipe.setQuantity(15);
        ingredientRecipe.setIngredientUnit(null);

        Ingredient ingredient = new Ingredient();
        ingredient.setIngredientName("baby carrots");
        ingredientRecipe.setIngredient(ingredient);

        ingredientRecipe.setNotes("peeled");

        String expected = "15 baby carrots (peeled)";

        String actual = ingredientRecipe.toString();
        assertEquals(expected, actual);
    }

    @Test
    void testToString_UnitBlank() {
        IngredientRecipe ingredientRecipe = new IngredientRecipe();
        ingredientRecipe.setQuantity(15);
        ingredientRecipe.setIngredientUnit("");

        Ingredient ingredient = new Ingredient();
        ingredient.setIngredientName("baby carrots");
        ingredientRecipe.setIngredient(ingredient);

        ingredientRecipe.setNotes("peeled");

        String expected = "15 baby carrots (peeled)";

        String actual = ingredientRecipe.toString();
        assertEquals(expected, actual);
    }



    @Test
    void testToString_NotesBlank() {
        IngredientRecipe ingredientRecipe = new IngredientRecipe();
        ingredientRecipe.setQuantity(1);
        ingredientRecipe.setIngredientUnit("tablespoon");

        Ingredient ingredient = new Ingredient();
        ingredient.setIngredientName("all-purpose flour");
        ingredientRecipe.setIngredient(ingredient);

        ingredientRecipe.setNotes("");

        String expected = "1 tablespoon all-purpose flour";

        String actual = ingredientRecipe.toString();
        assertEquals(expected, actual);
    }
}
