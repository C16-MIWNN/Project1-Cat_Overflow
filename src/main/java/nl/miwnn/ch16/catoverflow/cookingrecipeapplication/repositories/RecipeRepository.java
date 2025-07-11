package nl.miwnn.ch16.catoverflow.cookingrecipeapplication.repositories;

import nl.miwnn.ch16.catoverflow.cookingrecipeapplication.model.Ingredient;
import nl.miwnn.ch16.catoverflow.cookingrecipeapplication.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    Optional<Recipe> findByRecipeId(Long recipeId);

    @Query("SELECT r FROM Recipe r JOIN r.ingredients ir WHERE ir.ingredient.ingredientId = :ingredientId")
    List<Recipe> findRecipesByIngredientId(@Param("ingredientId") Long ingredientId);
}
