package nl.miwnn.ch16.catoverflow.cookingrecipeapplication.controller;

import nl.miwnn.ch16.catoverflow.cookingrecipeapplication.model.Ingredient;
import nl.miwnn.ch16.catoverflow.cookingrecipeapplication.model.Recipe;
import nl.miwnn.ch16.catoverflow.cookingrecipeapplication.repositories.IngredientRepository;
import nl.miwnn.ch16.catoverflow.cookingrecipeapplication.repositories.RecipeRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @author Robyn Blignaut
 * Handle all requests related to ingredients for recipes
 */

@Controller
@RequestMapping("/ingredient")
public class IngredientController {
    private final IngredientRepository ingredientRepository;
    private final RecipeRepository recipeRepository;

    public IngredientController(IngredientRepository ingredientRepository, RecipeRepository recipeRepository) {
        this.ingredientRepository = ingredientRepository;
        this.recipeRepository = recipeRepository;
    }

    private static void loadOverviewData(Model datamodel, List<Ingredient> allIngredients, Map<Long, Integer> recipeCounts) {
        datamodel.addAttribute("allIngredients", allIngredients);
        datamodel.addAttribute("recipeCounts", recipeCounts);
        datamodel.addAttribute("formIngredient", new Ingredient());
        datamodel.addAttribute("formModalHidden", true);
    }

    private void loadDetailData(Model model, Ingredient ingredient, List<Recipe> recipes) {
        model.addAttribute("ingredientToBeShown", ingredient);
        model.addAttribute("formIngredient", ingredient);
        model.addAttribute("formModalHidden", true);
        model.addAttribute("recipes", recipes);
        model.addAttribute("recipesCount", recipes.size());
    }

    @GetMapping("/overview")
    private String showIngredientOverview(Model datamodel) {
        List<Ingredient> allIngredients = ingredientRepository.findAll();
        Map<Long, Integer> recipeCounts = getRecipeCount(allIngredients);

        loadOverviewData(datamodel, allIngredients, recipeCounts);
        return "ingredientOverview";
    }

    @GetMapping("/detail/{ingredientName}")
    private String showIngredientDetailPage(@PathVariable("ingredientName") String ingredientName, Model datamodel) {
        Optional<Ingredient> ingredientOpt = findIngredientByName(ingredientName);

        if (ingredientOpt.isEmpty()) {
            return "redirect:/ingredient";
        }

        Ingredient ingredient = ingredientOpt.get();
        List<Recipe> recipes = findRecipesForIngredient(ingredient);

        loadDetailData(datamodel, ingredient, recipes);
        return "ingredientDetail";
    }

    @PostMapping("/save")
    private String saveOrUpdateIngredient(@ModelAttribute("formIngredient") Ingredient ingredient, BindingResult result, Model datamodel) {
        checkIngredientNameIsUnique(ingredient, result);

        if (result.hasErrors()) {
            datamodel.addAttribute("ingredientToBeShown", ingredient);
            datamodel.addAttribute("formIngredient", ingredient);
            datamodel.addAttribute("formModalHidden", false);
            datamodel.addAttribute("allIngredients", ingredientRepository.findAll());

            return "ingredientOverview";
        }
        ingredientRepository.save(ingredient);

        showIngredientDetailPage(ingredient.getIngredientName(), datamodel);
        return "redirect:/ingredient/detail/" + ingredient.getIngredientName();
    }


    @GetMapping("/delete/{ingredientName}")
    private String deleteIngredient(@PathVariable("ingredientName") String name, Model model) {
        Optional<Ingredient> optionalIngredient = findIngredientByName(name);

        if (optionalIngredient.isPresent()) {
            Ingredient ingredient = optionalIngredient.get();
            List<Recipe> linkedRecipes = findRecipesForIngredient(ingredient);

            if (!linkedRecipes.isEmpty()) {
                model.addAttribute("errorMessage", "Cannot delete ingredient: it is still used in one or more recipes.");
                loadDetailData(model, ingredient, linkedRecipes);
                return "ingredientDetail";
            }
            ingredientRepository.delete(ingredient);
        }
        return "redirect:/ingredient/overview";
    }

    private void checkIngredientNameIsUnique(Ingredient ingredient, BindingResult result) {
        Optional<Ingredient> sameName = ingredientRepository.findIngredientByIngredientName(ingredient.getIngredientName());
        if (sameName.isPresent() && !sameName.get().getIngredientId().equals(ingredient.getIngredientId())) {
            result.addError(new FieldError("formIngredient",
                    "ingredientName",
                    "there is already an ingredient with this name")
            );
        }
    }

    private Optional<Ingredient> findIngredientByName(String ingredientName) {
        return ingredientRepository.findIngredientByIngredientName(ingredientName);
    }

    private List<Recipe> findRecipesForIngredient(Ingredient ingredient) {
        return recipeRepository.findRecipesByIngredientId(ingredient.getIngredientId());
    }

    private Map<Long, Integer> getRecipeCount(List<Ingredient> allIngredients) {
        Map<Long, Integer> recipeCounts = new HashMap<>();
        for (Ingredient ingredient : allIngredients) {
            int count = findRecipesForIngredient(ingredient).size();
            recipeCounts.put(ingredient.getIngredientId(), count);
        }
        return recipeCounts;
    }
}
