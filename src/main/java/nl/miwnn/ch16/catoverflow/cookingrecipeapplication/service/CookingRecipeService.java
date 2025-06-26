package nl.miwnn.ch16.catoverflow.cookingrecipeapplication.service;

import nl.miwnn.ch16.catoverflow.cookingrecipeapplication.model.CookingRecipeUser;
import nl.miwnn.ch16.catoverflow.cookingrecipeapplication.repositories.CookingRecipeUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author Robyn Blignaut & Bas Folkers
 */

@Service
public class CookingRecipeService implements UserDetailsService {
    private final CookingRecipeUserRepository cookingRecipeUserRepository;

    public CookingRecipeService(CookingRecipeUserRepository cookingRecipeUserRepository) {
        this.cookingRecipeUserRepository = cookingRecipeUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return cookingRecipeUserRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User %s was not found.", username)));
    }

    public void saveUser(CookingRecipeUser cookingRecipeUser) {
        cookingRecipeUserRepository.save(cookingRecipeUser);
    }
}
