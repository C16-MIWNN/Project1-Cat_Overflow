package nl.miwnn.ch16.catoverflow.cookingrecipeapplication.controller;

import nl.miwnn.ch16.catoverflow.cookingrecipeapplication.model.*;
import nl.miwnn.ch16.catoverflow.cookingrecipeapplication.repositories.*;
import org.springframework.stereotype.Controller;

import java.lang.reflect.Array;
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
    private final ImageRepository imageRepository;
    private final IngredientRepository ingredientRepository;

    public InitializeController(RecipeRepository recipeRepository, IngredientRecipeRepository ingredientRecipeRepository, InstructionRepository instructionRepository, ImageRepository imageRepository, IngredientRepository ingredientRepository) {
        this.recipeRepository = recipeRepository;
        this.ingredientRecipeRepository = ingredientRecipeRepository;
        this.instructionRepository = instructionRepository;
        this.imageRepository = imageRepository;
        this.ingredientRepository = ingredientRepository;
    }

    private void intializeDB() {
        Image image = makeImage("example_data/images/placeholderPastaImage.jpg");

        Ingredient ingredientPasta = makeIngredient("Pasta");
        Ingredient ingredientTomato = makeIngredient("Tomato");

        Instruction instruction1 = makeInstruction(image, "Boil water");
        Instruction instruction2 = makeInstruction(image, "Add pasta");
        Instruction instruction3 = makeInstruction(image, "Cook for 10 minutes");

        List<Instruction> instructionList = List.of(instruction1, instruction2, instruction3);

        IngredientRecipe ingredientRecipePasta = makeIngredientRecipes(ingredientPasta, 1, "Hand",
                "Type of pasta of your choice");
        IngredientRecipe ingredientRecipeTomaat = makeIngredientRecipes(ingredientTomato, 600, "Pieces",
                "Could add less");

        List<IngredientRecipe> ingredientRecipeList = List.of(ingredientRecipePasta, ingredientRecipeTomaat);

        makeRecipe("Pasta with tomatoes",
                "Best Pasta in the world",
                "You know, amazing pasta",
                1,
                "Big unit",
                600,
                image,
                ingredientRecipeList,
                instructionList);
    }

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
            Ingredient ingredient,
            int quantity,
            String unit,
            String notes) {

        IngredientRecipe ingredientRecipe = new IngredientRecipe();

        ingredientRecipe.setIngredient(ingredient);
        ingredientRecipe.setQuantity(quantity);
        ingredientRecipe.setUnit(unit);
        ingredientRecipe.setNotes(notes);

        ingredientRecipeRepository.save(ingredientRecipe);
        return ingredientRecipe;
    }

    private Instruction makeInstruction(
            Image image,
            String description) {

        Instruction instruction = new Instruction();

        instruction.setImage(image);
        instruction.setDescription(description);

        instructionRepository.save(instruction);

        return instruction;
    }

    private Image makeImage(String imageName) {
        Image image = new Image();

        image.setImageName(imageName);

        imageRepository.save(image);

        return image;
    }

    private Ingredient makeIngredient(String ingredientName) {
        Ingredient ingredient = new Ingredient();

        ingredient.setIngredientName(ingredientName);

        ingredientRepository.save(ingredient);

        return ingredient;
    }
}