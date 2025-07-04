package nl.miwnn.ch16.catoverflow.cookingrecipeapplication.controller;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import nl.miwnn.ch16.catoverflow.cookingrecipeapplication.model.*;
import nl.miwnn.ch16.catoverflow.cookingrecipeapplication.repositories.*;
import nl.miwnn.ch16.catoverflow.cookingrecipeapplication.service.AdminUserService;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

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
    private final AdminUserService adminUserService;

    private final Map<String, Ingredient> ingredientCache = new HashMap<>();
    private final Map<String, IngredientRecipe> ingredientRecipeCache = new HashMap<>();
    private final Map<String, Instruction> instructionCache = new HashMap<>();
    private final Map<String, Image> imageCache = new HashMap<>();

    public InitializeController(RecipeRepository recipeRepository,
                                IngredientRecipeRepository ingredientRecipeRepository,
                                InstructionRepository instructionRepository,
                                ImageRepository imageRepository,
                                IngredientRepository ingredientRepository,
                                AdminUserService adminUserService) {
        this.recipeRepository = recipeRepository;
        this.ingredientRecipeRepository = ingredientRecipeRepository;
        this.instructionRepository = instructionRepository;
        this.imageRepository = imageRepository;
        this.ingredientRepository = ingredientRepository;
        this.adminUserService = adminUserService;
    }

    @EventListener
    private void seed(ContextRefreshedEvent ignoredEvent) {
        if (recipeRepository.count() == 0) {
            initializeDB();
        }
    }

    private void initializeDB() {
        try {
            AdminUser adminUser = new AdminUser();
            adminUser.setUsername("Test");
            adminUser.setPassword("TestPW");
            adminUser.setEmail("email@email.org");
            adminUser.setStatus("Admin");
            adminUserService.saveUser(adminUser);

            loadImage();
            loadIngredient();
            loadIngredientRecipes();
            loadInstruction();
            loadRecipe();
        } catch (IOException | CsvValidationException csvValidationException) {
            throw new RuntimeException("Failed to initialize database from CSV files", csvValidationException);
        }
    }

    private void loadRecipe() throws IOException, CsvValidationException {
        try (CSVReader reader = new CSVReader(new InputStreamReader(
                new ClassPathResource("/example_data/recipe.csv").getInputStream()))) {

            reader.skip(1);

            for (String[] recipeLine : reader) {
                Recipe recipe = new Recipe();

                recipe.setTitle(recipeLine[0]);
                recipe.setSummary(recipeLine[1]);
                recipe.setDescription(recipeLine[2]);
                recipe.setPortionQuantity(Integer.parseInt(recipeLine[3]));
                recipe.setPortionUnit(recipeLine[4]);
                recipe.setTotalCookingTime(Integer.parseInt(recipeLine[5]));

                Image imageRecipe = imageRepository.findByImageId(Integer.parseInt(recipeLine[6]));
                recipe.setImage(imageRecipe);

                List<IngredientRecipe> ingredientRecipes = new ArrayList<>();
                String[] ingredientRecipeIds = recipeLine[7].split(",");
                for (String ingredientRecipeId : ingredientRecipeIds) {
                    IngredientRecipe ingredientRecipe = ingredientRecipeCache.get(ingredientRecipeId.trim());

                    if (ingredientRecipe == null) {
                        throw new RuntimeException("IngredientRecipe not found in cache for ID: " +
                                ingredientRecipeId.trim());
                    }

                    ingredientRecipe.setRecipe(recipe);
                    ingredientRecipes.add(ingredientRecipe);
                }
                recipe.setIngredients(ingredientRecipes);

                List<Instruction> instructions = new ArrayList<>();
                for (String instructionId : recipeLine[8].split(",")) {
                    Instruction instruction = instructionCache.get(instructionId.trim());

                    if (instruction == null) {
                        throw new RuntimeException("Instruction not found in cache for ID: " +
                                instructionId.trim());
                    }

                    instruction.setRecipe(recipe);
                    instructions.add(instruction);
                }
                recipe.setInstructions(instructions);

                recipeRepository.save(recipe);
            }
        }
    }

    private void loadIngredientRecipes() throws IOException, CsvValidationException {
        try (CSVReader reader = new CSVReader(new InputStreamReader(
                new ClassPathResource("example_data/ingredientRecipe.csv").getInputStream()))) {

            reader.skip(1);

            for (String[] ingredientRecipeLine : reader) {
                Long ingredientId = Long.valueOf((ingredientRecipeLine[0]));

                Optional<Ingredient> ingredients = ingredientRepository.findByIngredientId(ingredientId);

                if (ingredients.isEmpty()) {
                    throw new RuntimeException("Ingredient not found: " +
                            ingredientId);
                }
                Ingredient ingredient = ingredients.get();

                IngredientRecipe ingredientRecipe = new IngredientRecipe();
                ingredientRecipe.setIngredient(ingredient);
                ingredientRecipe.setQuantity(Integer.parseInt(ingredientRecipeLine[1]));
                ingredientRecipe.setIngredientUnit(ingredientRecipeLine[2]);
                ingredientRecipe.setNotes(ingredientRecipeLine[3]);

                ingredientRecipeCache.put(ingredientRecipeLine[0], ingredientRecipe);
            }
        }
    }

    private void loadIngredient() throws IOException, CsvValidationException {
        try (CSVReader reader = new CSVReader(new InputStreamReader(
                new ClassPathResource("example_data/ingredient.csv").getInputStream()))) {

            reader.skip(1);

            for (String[] ingredientLine : reader) {

                Ingredient ingredient = new Ingredient();

                ingredient.setIngredientName(ingredientLine[0]);

                ingredientRepository.save(ingredient);
                ingredientCache.put(ingredient.getIngredientName(), ingredient);
            }
        }
    }

    private void loadInstruction() throws IOException, CsvValidationException {
        try (CSVReader reader = new CSVReader(new InputStreamReader(
                new ClassPathResource("example_data/instruction.csv").getInputStream()))) {

            reader.skip(1);

            for (String[] instructionLine : reader) {
                int instructionId = Integer.parseInt((instructionLine[1]));

                Instruction instruction = new Instruction();
                instruction.setDescription(instructionLine[0]);

                Image imageInstruction = imageRepository.findByImageId(Integer.parseInt(instructionLine[2]));
                instruction.setImage(imageInstruction);

                instructionCache.put(String.valueOf(instructionId), instruction);
            }
        }
    }

    private void loadImage() throws IOException, CsvValidationException {
        try (CSVReader reader = new CSVReader(new InputStreamReader(
                new ClassPathResource("example_data/image.csv").getInputStream()))) {

            reader.skip(1);

            for (String[] imageLine : reader) {
                Image image = new Image();

                image.setImageName(imageLine[1]);

                imageRepository.save(image);
                imageCache.put(imageLine[0], image);
            }
        }
    }
}