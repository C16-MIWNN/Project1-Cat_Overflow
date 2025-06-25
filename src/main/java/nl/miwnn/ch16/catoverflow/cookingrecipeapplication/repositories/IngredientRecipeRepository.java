package nl.miwnn.ch16.catoverflow.cookingrecipeapplication.repositories;

import nl.miwnn.ch16.catoverflow.cookingrecipeapplication.model.Ingredient;
import nl.miwnn.ch16.catoverflow.cookingrecipeapplication.model.IngredientRecipe;
import nl.miwnn.ch16.catoverflow.cookingrecipeapplication.model.IngredientRecipePk;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IngredientRecipeRepository extends JpaRepository<IngredientRecipe, Long> {
}
