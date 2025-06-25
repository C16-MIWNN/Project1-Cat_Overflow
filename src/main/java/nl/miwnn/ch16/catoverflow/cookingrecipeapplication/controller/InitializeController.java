package nl.miwnn.ch16.catoverflow.cookingrecipeapplication.controller;

import nl.miwnn.ch16.catoverflow.cookingrecipeapplication.model.*;
import nl.miwnn.ch16.catoverflow.cookingrecipeapplication.repositories.*;
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
        Ingredient ingredientTomaat = makeIngredient("Tomaat");
        Instruction instruction = makeInstruction(image, "A description of the most magnificent pasts on earth");
        IngredientRecipe ingredientRecipePasta = makeIngredientRecipes(ingredientPasta, 1, "Hand", "Pasta naar eigen keuze");
        IngredientRecipe ingredientRecipeTomaat = makeIngredientRecipes(ingredientTomaat, 600, "Stuks", "Mag ook minder zijn dan 600");
        Recipe recipePastaTomaat = makeRecipe("Pasta with tomatoes", "Best Pasta in the world", "You know, amazing pasta",
                1, "Big unit", 600, image, new ArrayList<>(ingredientRecipePasta, ingredientRecipeTomaat), new ArrayList<>(instruction));
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