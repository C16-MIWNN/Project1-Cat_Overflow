package nl.miwnn.ch16.catoverflow.cookingrecipeapplication.repositories;

import nl.miwnn.ch16.catoverflow.cookingrecipeapplication.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    Optional<Recipe> findByRecipeId(Long recipeId);
}
