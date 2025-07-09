package nl.miwnn.ch16.catoverflow.cookingrecipeapplication;

import nl.miwnn.ch16.catoverflow.cookingrecipeapplication.model.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static nl.miwnn.ch16.catoverflow.cookingrecipeapplication.model.Recipe.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Test for recipe difficulty
 * @author Robyn Blignaut
 */
public class RecipeDifficultyTests {

    private List<Instruction> createInstructions(int count) {
        List<Instruction> list = new ArrayList<>();
        for (int index = 1; index <= count; index++) {
            Instruction step = new Instruction();
            step.setDescription("Step " + index);
            list.add(step);
        }
        return list;
    }

    private List<IngredientRecipe> createIngredients(int count) {
        List<IngredientRecipe> list = new ArrayList<>();
        for (int index = 1; index <= count; index++) {
            IngredientRecipe ingrRecipe = new IngredientRecipe();
            ingrRecipe.setIngredient(new Ingredient("Ingredient " + index));
            ingrRecipe.setQuantity(1);
            ingrRecipe.setIngredientUnit("unit");
            list.add(ingrRecipe);
        }
        return list;
    }

    private Recipe createRecipeWithCounts(int ingredientCount, int stepCount) {
        Recipe recipe = new Recipe();

        List<IngredientRecipe> ingredients = new ArrayList<>();
        for (int index = 0; index < ingredientCount; index++) {
            IngredientRecipe ingrRecipe = new IngredientRecipe();
            ingrRecipe.setIngredient(new Ingredient("ingredient " + index));
            ingredients.add(ingrRecipe);
        }

        List<Instruction> instructions = new ArrayList<>();
        for (int i = 0; i < stepCount; i++) {
            Instruction ins = new Instruction();
            ins.setDescription("Step " + i);
            instructions.add(ins);
        }

        recipe.setIngredients(ingredients);
        recipe.setInstructions(instructions);

        return recipe;
    }

    @Test
    @DisplayName("Should show all recipe difficulties")
    void shouldShowAllRecipeDifficulties() {
    //  Arrange
        Recipe recipeVeryEasy = createRecipeWithCounts(2, 2);
        Recipe recipeEasy = createRecipeWithCounts(10, 5);
        Recipe recipeMedium = createRecipeWithCounts(10, 10); // same ingredients, more steps
        Recipe recipeHard = createRecipeWithCounts(15, 10);
        Recipe recipeVeryHard = createRecipeWithCounts(15, 20);

    //  Act + Assert
        assertAll("Difficulty levels",
                () -> assertEquals("Very Easy", recipeVeryEasy.getDifficulty().getLabel()),
                () -> assertEquals("Easy", recipeEasy.getDifficulty().getLabel()),
                () -> assertEquals("Medium", recipeMedium.getDifficulty().getLabel()),
                () -> assertEquals("Hard", recipeHard.getDifficulty().getLabel()),
                () -> assertEquals("Very Hard", recipeVeryHard.getDifficulty().getLabel())
        );
    }

    @Test
    @DisplayName("Should handle null ingredients and steps gracefully")
    void shouldHandleNullIngredientsAndStepsGracefully() {
    //  Arrange
        Recipe recipe = new Recipe();
        recipe.setIngredients(null);
        recipe.setInstructions(null);

    //  Act + Assert
        assertDoesNotThrow(() -> recipe.getDifficulty());
    }

    @Test
    @DisplayName("Should classify difficulty at ingredient and step boundaries")
    void shouldClassifyDifficultyAtIngredientAndStepBoundaries() {
        // Test min ingredients and steps exactly
        Recipe recipeMin = createRecipeWithCounts((int) MIN_ING_DIFF, (int) MIN_STEP_DIFF);
        assertEquals(DifficultyLevel.VERY_EASY, recipeMin.getDifficulty());

        // Test max ingredients and steps exactly
        Recipe recipeMax = createRecipeWithCounts((int) MAX_ING_DIFF, (int) MAX_STEP_DIFF);
        assertEquals(DifficultyLevel.VERY_HARD, recipeMax.getDifficulty());

        // Test counts just below min
        Recipe recipeBelowMin = createRecipeWithCounts(0, 0);
        assertEquals(DifficultyLevel.VERY_EASY, recipeBelowMin.getDifficulty());

        // Test counts just above max
        Recipe recipeAboveMax = createRecipeWithCounts((int) MAX_ING_DIFF + 1, (int) MAX_STEP_DIFF + 1);
        assertEquals(DifficultyLevel.VERY_HARD, recipeAboveMax.getDifficulty());
    }

    @Test
    @DisplayName("Should handle ingredientRecipe with null ingredient")
    void shouldHandleNullIngredient() {
    //  Arrange
        IngredientRecipe ingrRecipe = new IngredientRecipe();
        ingrRecipe.setIngredient(null);
        ingrRecipe.setQuantity(2);
        ingrRecipe.setIngredientUnit("tbs");

        Recipe recipe = new Recipe();
        recipe.setIngredients(List.of(ingrRecipe));
        recipe.setInstructions(createInstructions(5));

    //  Act + Assert
        assertDoesNotThrow(() -> recipe.getDifficulty(), "Recipe should handle null Ingredient safely");
    }

    @Test
    @DisplayName("Should handle instruction with empty description")
    void shouldHandleEmptyInstructionDescription() {
    //  Arrange
        Instruction instruction = new Instruction();
        instruction.setDescription("");

        Recipe recipe = new Recipe();
        recipe.setIngredients(createIngredients(5));
        recipe.setInstructions(List.of(instruction));

    //  Act + Assert
        assertDoesNotThrow(() -> recipe.getDifficulty(), "Recipe should handle instruction with empty description");
    }
}
