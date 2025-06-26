package nl.miwnn.ch16.catoverflow.cookingrecipeapplication.repositories;

import nl.miwnn.ch16.catoverflow.cookingrecipeapplication.model.CookingRecipeUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CookingRecipeUserRepository extends JpaRepository<CookingRecipeUser, Long> {
    Optional<CookingRecipeUser> findByUsername(String username);
}
