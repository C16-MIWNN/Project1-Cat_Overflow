package nl.miwnn.ch16.catoverflow.cookingrecipeapplication.controller;

import nl.miwnn.ch16.catoverflow.cookingrecipeapplication.model.Recipe;
import nl.miwnn.ch16.catoverflow.cookingrecipeapplication.repositories.ImageRepository;
import nl.miwnn.ch16.catoverflow.cookingrecipeapplication.repositories.IngredientRecipeRepository;
import nl.miwnn.ch16.catoverflow.cookingrecipeapplication.repositories.InstructionRepository;
import nl.miwnn.ch16.catoverflow.cookingrecipeapplication.repositories.RecipeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

/**
 * @author Robyn Blignaut, Bas Folkers
 * Handle all requests related to recipes
 */

@Controller
public class RecipeController {

    private final RecipeRepository recipeRepository;
    private final IngredientRecipeRepository ingredientRecipeRepository;
    private final InstructionRepository instructionRepository;
    private final ImageRepository imageRepository;

    public RecipeController(
            RecipeRepository recipeRepository,
            IngredientRecipeRepository ingredientRecipeRepository,
            InstructionRepository instructionRepository, ImageRepository imageRepository) {
        this.recipeRepository = recipeRepository;
        this.ingredientRecipeRepository = ingredientRecipeRepository;
        this.instructionRepository = instructionRepository;
        this.imageRepository = imageRepository;
    }

    private String setupRecipeOverview(Model datamodel, Recipe formRecipe, boolean formModalHidden) {
        datamodel.addAttribute("allRecipes", recipeRepository.findAll());
        datamodel.addAttribute("formRecipe", new Recipe());
        datamodel.addAttribute("formModalHidden", formModalHidden);

        return "recipeOverview";
    }

    private String setupRecipeDetail(Model datamodel, Recipe recipeToShow, Recipe formRecipe, boolean formModalHidden) {
        datamodel.addAttribute("recipe", recipeToShow);
        datamodel.addAttribute("formRecipe", formRecipe);
        datamodel.addAttribute("formModalHidden", formModalHidden);
        datamodel.addAttribute("allIngredientRecipes", recipeToShow.getIngredients());
        datamodel.addAttribute("allInstructions", recipeToShow.getInstructions());

        return "recipeDetail";
    }

    @GetMapping({"/", "/recipe/overview"})
    private String showRecipeOverview(Model datamodel) {
        return setupRecipeOverview(datamodel, new Recipe(), true);
    }

    @GetMapping("/recipe/detail/{recipeId}")
    private String showRecipeDetailPage(@PathVariable("recipeId") Long recipeId, Model datamodel) {
        Optional<Recipe> recipeOptional = recipeRepository.findByRecipeId(recipeId);

        if (recipeOptional.isEmpty()) {
            return "redirect:/recipe/overview";
        }

        return setupRecipeDetail(datamodel, recipeOptional.get(), recipeOptional.get(), true);
    }

    @PostMapping("/recipe/save")
    private String saveOrUpdateRecipe(
            @ModelAttribute("formRecipe") Recipe recipeToBeSaved,
            BindingResult bindingResult,
            Model datamodel) {

        if (bindingResult.hasErrors() && recipeToBeSaved.getRecipeId() != null) {
            Recipe originalRecipeDetails = recipeRepository.findById(recipeToBeSaved.getRecipeId()).orElse(new Recipe());
            return setupRecipeDetail(datamodel, originalRecipeDetails, recipeToBeSaved, false);
        } else if (bindingResult.hasErrors()){
            return setupRecipeOverview(datamodel,recipeToBeSaved, false);
        }
        recipeRepository.save(recipeToBeSaved);

        return "redirect:/recipe/detail/" + recipeToBeSaved.getRecipeId();
    }

    @GetMapping("/recipe/delete/{recipeId}")
    private String deleteRecipe(@PathVariable("recipeId") Long recipeId) {
        recipeRepository.findById(recipeId).ifPresent(recipeRepository::delete);

        return "redirect:/recipe/overview";
    }
}
