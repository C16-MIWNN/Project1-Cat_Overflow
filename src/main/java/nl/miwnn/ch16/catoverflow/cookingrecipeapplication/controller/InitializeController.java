package nl.miwnn.ch16.catoverflow.cookingrecipeapplication.controller;

import nl.miwnn.ch16.catoverflow.cookingrecipeapplication.model.*;
import nl.miwnn.ch16.catoverflow.cookingrecipeapplication.repositories.IngredientRecipeRepository;
import nl.miwnn.ch16.catoverflow.cookingrecipeapplication.repositories.InstructionRepository;
import nl.miwnn.ch16.catoverflow.cookingrecipeapplication.repositories.RecipeRepository;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Robyn Blignaut & Bas Folkers
 * Set some intitial data in the database for (manual) testing purposes.
 */

@Controller
public class InitializeController {
    private final RecipeRepository recipeRepository;
    private final IngredientRecipeRepository ingredientRecipeRepository;
    private final InstructionRepository instructionRepository;

    public InitializeController(RecipeRepository recipeRepository, IngredientRecipeRepository ingredientRecipeRepository, InstructionRepository instructionRepository) {
        this.recipeRepository = recipeRepository;
        this.ingredientRecipeRepository = ingredientRecipeRepository;
        this.instructionRepository = instructionRepository;
    }

//    private void intializeDB() {
//
//    }

    private Recipe makeRecipe(
            String title,
            String summary,
            String description,
            int portionQuantity,
            String portionUnit,
            int totalCookingTime,
            Image image,
            List<IngredientRecipe> ingredientRecipeList,
            List<Instruction> instructionList) {

        Recipe recipe = new Recipe();

        recipe.setTitle(title);
        recipe.setSummary(summary);
        recipe.setDescription(description);
        recipe.setPortionQuantity(portionQuantity);
        recipe.setPortionUnit(portionUnit);
        recipe.setTotalCookingTime(totalCookingTime);
        recipe.setImage(image);

        List<IngredientRecipe> IngredientRecipes = new ArrayList<>(ingredientRecipeList);
        recipe.setIngredients(IngredientRecipes);

        List<Instruction> instructions = new ArrayList<>(instructionList);
        recipe.setInstructions(instructions);

        recipeRepository.save(recipe);
        return recipe;
    }

    private IngredientRecipe makeIngredientRecipes(
            Recipe recipe,
            Ingredient ingredient,
            int quantity,
            String unit,
            String notes) {

        IngredientRecipe ingredientRecipe = new IngredientRecipe();

        ingredientRecipe.setRecipe(recipe);
        ingredientRecipe.setIngredient(ingredient);
        ingredientRecipe.setQuantity(quantity);
        ingredientRecipe.setUnit(unit);
        ingredientRecipe.setNotes(notes);

        ingredientRecipeRepository.save(ingredientRecipe);
        return ingredientRecipe;
    }

    private Instruction makeInstruction(
            Recipe recipe,
            Image image,
            String description) {

        Instruction instruction = new Instruction();

        instruction.setRecipe(recipe);
        instruction.setImage(image);
        instruction.setDescription(description);

        instructionRepository.save(instruction);

        return instruction;
    }
}